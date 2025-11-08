package com.google.crypto.tink;

import A.a;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class Registry {
    private static final ConcurrentMap<String, Catalogue<?>> catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, KeyDeriverContainer> keyDeriverMap = new ConcurrentHashMap();
    private static final AtomicReference<KeyManagerRegistry> keyManagerRegistry = new AtomicReference<>(new KeyManagerRegistry());
    private static final ConcurrentMap<String, KeyTemplate> keyTemplateMap = new ConcurrentHashMap();
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final ConcurrentMap<String, Boolean> newKeyAllowedMap = new ConcurrentHashMap();

    public interface KeyDeriverContainer {
        KeyData deriveKey(ByteString byteString, InputStream inputStream) throws GeneralSecurityException;
    }

    private Registry() {
    }

    @Deprecated
    public static synchronized void addCatalogue(String str, Catalogue<?> catalogue) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (str == null) {
                throw new IllegalArgumentException("catalogueName must be non-null.");
            } else if (catalogue != null) {
                try {
                    ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
                    Locale locale = Locale.US;
                    if (concurrentMap.containsKey(str.toLowerCase(locale))) {
                        if (!catalogue.getClass().getName().equals(concurrentMap.get(str.toLowerCase(locale)).getClass().getName())) {
                            logger.warning("Attempted overwrite of a catalogueName catalogue for name ".concat(str));
                            throw new GeneralSecurityException("catalogue for name " + str + " has been already registered");
                        }
                    }
                    concurrentMap.put(str.toLowerCase(locale), catalogue);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("catalogue must be non-null.");
            }
        }
    }

    private static <KeyProtoT extends MessageLite> KeyDeriverContainer createDeriverFor(final KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new KeyDeriverContainer() {
            private <KeyFormatProtoT extends MessageLite> MessageLite deriveKeyWithFactory(ByteString byteString, InputStream inputStream, KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) throws GeneralSecurityException {
                try {
                    KeyFormatProtoT parseKeyFormat = keyFactory.parseKeyFormat(byteString);
                    keyFactory.validateKeyFormat(parseKeyFormat);
                    return keyFactory.deriveKey(parseKeyFormat, inputStream);
                } catch (InvalidProtocolBufferException e3) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", e3);
                }
            }

            public KeyData deriveKey(ByteString byteString, InputStream inputStream) throws GeneralSecurityException {
                return (KeyData) KeyData.newBuilder().setTypeUrl(KeyTypeManager.this.getKeyType()).setValue(deriveKeyWithFactory(byteString, inputStream, KeyTypeManager.this.keyFactory()).toByteString()).setKeyMaterialType(KeyTypeManager.this.keyMaterialType()).build();
            }
        };
    }

    public static synchronized KeyData deriveKey(KeyTemplate keyTemplate, InputStream inputStream) throws GeneralSecurityException {
        KeyData deriveKey;
        synchronized (Registry.class) {
            String typeUrl = keyTemplate.getTypeUrl();
            ConcurrentMap<String, KeyDeriverContainer> concurrentMap = keyDeriverMap;
            if (concurrentMap.containsKey(typeUrl)) {
                deriveKey = concurrentMap.get(typeUrl).deriveKey(keyTemplate.getValue(), inputStream);
            } else {
                throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for " + typeUrl);
            }
        }
        return deriveKey;
    }

    private static synchronized <KeyProtoT extends MessageLite, KeyFormatProtoT extends MessageLite> void ensureKeyManagerInsertable(String str, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> map, boolean z2) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (z2) {
                try {
                    ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
                    if (concurrentMap.containsKey(str)) {
                        if (!concurrentMap.get(str).booleanValue()) {
                            throw new GeneralSecurityException("New keys are already disallowed for key type " + str);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z2) {
                if (keyManagerRegistry.get().typeUrlExists(str)) {
                    for (Map.Entry next : map.entrySet()) {
                        if (!keyTemplateMap.containsKey(next.getKey())) {
                            throw new GeneralSecurityException("Attempted to register a new key template " + ((String) next.getKey()) + " from an existing key manager of type " + str);
                        }
                    }
                } else {
                    for (Map.Entry next2 : map.entrySet()) {
                        if (keyTemplateMap.containsKey(next2.getKey())) {
                            throw new GeneralSecurityException("Attempted overwrite of a registered key template " + ((String) next2.getKey()));
                        }
                    }
                }
            }
        }
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String str) throws GeneralSecurityException {
        if (str != null) {
            ConcurrentMap<String, Catalogue<?>> concurrentMap = catalogueMap;
            Locale locale = Locale.US;
            Catalogue<?> catalogue = concurrentMap.get(str.toLowerCase(locale));
            if (catalogue != null) {
                return catalogue;
            }
            String l2 = a.l("no catalogue found for ", str, ". ");
            if (str.toLowerCase(locale).startsWith("tinkaead")) {
                l2 = l2.concat("Maybe call AeadConfig.register().");
            }
            if (str.toLowerCase(locale).startsWith("tinkdeterministicaead")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call DeterministicAeadConfig.register().");
            } else if (str.toLowerCase(locale).startsWith("tinkstreamingaead")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call StreamingAeadConfig.register().");
            } else if (str.toLowerCase(locale).startsWith("tinkhybriddecrypt") || str.toLowerCase(locale).startsWith("tinkhybridencrypt")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call HybridConfig.register().");
            } else if (str.toLowerCase(locale).startsWith("tinkmac")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call MacConfig.register().");
            } else if (str.toLowerCase(locale).startsWith("tinkpublickeysign") || str.toLowerCase(locale).startsWith("tinkpublickeyverify")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call SignatureConfig.register().");
            } else if (str.toLowerCase(locale).startsWith("tink")) {
                l2 = android.support.v4.media.session.a.m(l2, "Maybe call TinkConfig.register().");
            }
            throw new GeneralSecurityException(l2);
        }
        throw new IllegalArgumentException("catalogueName must be non-null.");
    }

    public static <KeyT extends Key, P> P getFullPrimitive(KeyT keyt, Class<P> cls) throws GeneralSecurityException {
        return MutablePrimitiveRegistry.globalInstance().getPrimitive(keyt, cls);
    }

    @Nullable
    public static Class<?> getInputPrimitive(Class<?> cls) {
        try {
            return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(cls);
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String str) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, MessageLite messageLite) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str).getPrimitive(messageLite);
    }

    public static KeyData getPublicKeyData(String str, ByteString byteString) throws GeneralSecurityException {
        KeyManager keyManager = getKeyManager(str);
        if (keyManager instanceof PrivateKeyManager) {
            return ((PrivateKeyManager) keyManager).getPublicKeyData(byteString);
        }
        throw new GeneralSecurityException(a.l("manager for key type ", str, " is not a PrivateKeyManager"));
    }

    public static KeyManager<?> getUntypedKeyManager(String str) throws GeneralSecurityException {
        return keyManagerRegistry.get().getUntypedKeyManager(str);
    }

    public static synchronized Map<String, KeyTemplate> keyTemplateMap() {
        Map<String, KeyTemplate> unmodifiableMap;
        synchronized (Registry.class) {
            unmodifiableMap = Collections.unmodifiableMap(keyTemplateMap);
        }
        return unmodifiableMap;
    }

    public static synchronized List<String> keyTemplates() {
        List<String> unmodifiableList;
        synchronized (Registry.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(keyTemplateMap.keySet());
            unmodifiableList = Collections.unmodifiableList(arrayList);
        }
        return unmodifiableList;
    }

    public static synchronized MessageLite newKey(KeyTemplate keyTemplate) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKey = untypedKeyManager.newKey(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKey;
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            KeyManager<?> untypedKeyManager = getUntypedKeyManager(keyTemplate.getTypeUrl());
            if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
                newKeyData = untypedKeyManager.newKeyData(keyTemplate.getValue());
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
            }
        }
        return newKeyData;
    }

    public static MessageLite parseKeyData(KeyData keyData) throws GeneralSecurityException, InvalidProtocolBufferException {
        return keyManagerRegistry.get().parseKeyData(keyData);
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager, boolean z2) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (privateKeyTypeManager == null || keyTypeManager == null) {
                throw new IllegalArgumentException("given key managers must be non-null.");
            }
            try {
                AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
                keyManagerRegistry2.registerAsymmetricKeyManagers(privateKeyTypeManager, keyTypeManager);
                String keyType = privateKeyTypeManager.getKeyType();
                String keyType2 = keyTypeManager.getKeyType();
                ensureKeyManagerInsertable(keyType, z2 ? privateKeyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), z2);
                ensureKeyManagerInsertable(keyType2, Collections.emptyMap(), false);
                if (!atomicReference.get().typeUrlExists(keyType)) {
                    keyDeriverMap.put(keyType, createDeriverFor(privateKeyTypeManager));
                    if (z2) {
                        registerKeyTemplates(privateKeyTypeManager.getKeyType(), privateKeyTypeManager.keyFactory().keyFormats());
                    }
                }
                ConcurrentMap<String, Boolean> concurrentMap = newKeyAllowedMap;
                concurrentMap.put(keyType, Boolean.valueOf(z2));
                concurrentMap.put(keyType2, Boolean.FALSE);
                atomicReference.set(keyManagerRegistry2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager(keyManager, true);
        }
    }

    private static <KeyFormatProtoT extends MessageLite> void registerKeyTemplates(String str, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> map) {
        for (Map.Entry next : map.entrySet()) {
            keyTemplateMap.put((String) next.getKey(), KeyTemplate.create(str, ((MessageLite) ((KeyTypeManager.KeyFactory.KeyFormat) next.getValue()).keyFormat).toByteArray(), ((KeyTypeManager.KeyFactory.KeyFormat) next.getValue()).outputPrefixType));
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(PrimitiveWrapper<B, P> primitiveWrapper) throws GeneralSecurityException {
        synchronized (Registry.class) {
            MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(primitiveWrapper);
        }
    }

    public static synchronized void reset() {
        synchronized (Registry.class) {
            keyManagerRegistry.set(new KeyManagerRegistry());
            MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
            keyDeriverMap.clear();
            newKeyAllowedMap.clear();
            catalogueMap.clear();
            keyTemplateMap.clear();
        }
    }

    public static synchronized void restrictToFipsIfEmpty() throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (!TinkFipsUtil.useOnlyFips()) {
                if (keyManagerRegistry.get().isEmpty()) {
                    TinkFipsUtil.setFipsRestricted();
                    return;
                }
                throw new GeneralSecurityException("Could not enable FIPS mode as Registry is not empty.");
            }
        }
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) throws GeneralSecurityException {
        return MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet, cls);
    }

    public static <P> KeyManager<P> getKeyManager(String str, Class<P> cls) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str, cls);
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) throws GeneralSecurityException {
        return wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    public static <P> P getPrimitive(String str, MessageLite messageLite, Class<P> cls) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str, cls).getPrimitive(messageLite);
    }

    public static synchronized <P> void registerKeyManager(KeyManager<P> keyManager, boolean z2) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (keyManager != null) {
                AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
                KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
                keyManagerRegistry2.registerKeyManager(keyManager);
                if (TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                    String keyType = keyManager.getKeyType();
                    ensureKeyManagerInsertable(keyType, Collections.emptyMap(), z2);
                    newKeyAllowedMap.put(keyType, Boolean.valueOf(z2));
                    atomicReference.set(keyManagerRegistry2);
                } else {
                    throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
                }
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    @Deprecated
    public static <P> P getPrimitive(String str, ByteString byteString) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str).getPrimitive(byteString);
    }

    public static synchronized MessageLite newKey(String str, MessageLite messageLite) throws GeneralSecurityException {
        MessageLite newKey;
        synchronized (Registry.class) {
            KeyManager keyManager = getKeyManager(str);
            if (newKeyAllowedMap.get(str).booleanValue()) {
                newKey = keyManager.newKey(messageLite);
            } else {
                throw new GeneralSecurityException("newKey-operation not permitted for key type " + str);
            }
        }
        return newKey;
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyData newKeyData;
        synchronized (Registry.class) {
            newKeyData = newKeyData(keyTemplate.getProto());
        }
        return newKeyData;
    }

    public static <P> P getPrimitive(String str, ByteString byteString, Class<P> cls) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(str, cls).getPrimitive(byteString);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) throws GeneralSecurityException {
        return getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) throws GeneralSecurityException {
        return getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) throws GeneralSecurityException {
        return getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(KeyTypeManager<KeyProtoT> keyTypeManager, boolean z2) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (keyTypeManager != null) {
                try {
                    AtomicReference<KeyManagerRegistry> atomicReference = keyManagerRegistry;
                    KeyManagerRegistry keyManagerRegistry2 = new KeyManagerRegistry(atomicReference.get());
                    keyManagerRegistry2.registerKeyManager(keyTypeManager);
                    String keyType = keyTypeManager.getKeyType();
                    ensureKeyManagerInsertable(keyType, z2 ? keyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), z2);
                    if (!atomicReference.get().typeUrlExists(keyType)) {
                        keyDeriverMap.put(keyType, createDeriverFor(keyTypeManager));
                        if (z2) {
                            registerKeyTemplates(keyType, keyTypeManager.keyFactory().keyFormats());
                        }
                    }
                    newKeyAllowedMap.put(keyType, Boolean.valueOf(z2));
                    atomicReference.set(keyManagerRegistry2);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
        }
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager) throws GeneralSecurityException {
        synchronized (Registry.class) {
            registerKeyManager(str, keyManager, true);
        }
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String str, KeyManager<P> keyManager, boolean z2) throws GeneralSecurityException {
        synchronized (Registry.class) {
            if (keyManager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            } else if (str.equals(keyManager.getKeyType())) {
                registerKeyManager(keyManager, z2);
            } else {
                throw new GeneralSecurityException("Manager does not support key type " + str + JwtUtilsKt.JWT_DELIMITER);
            }
        }
    }
}
