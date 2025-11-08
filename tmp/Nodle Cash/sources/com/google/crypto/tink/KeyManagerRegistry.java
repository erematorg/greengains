package com.google.crypto.tink;

import androidx.constraintlayout.core.state.b;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

final class KeyManagerRegistry {
    private static final Logger logger = Logger.getLogger(KeyManagerRegistry.class.getName());
    private final ConcurrentMap<String, KeyManagerContainer> keyManagerMap;

    public interface KeyManagerContainer {
        Class<?> getImplementingClass();

        <P> KeyManager<P> getKeyManager(Class<P> cls) throws GeneralSecurityException;

        KeyManager<?> getUntypedKeyManager();

        MessageLite parseKey(ByteString byteString) throws GeneralSecurityException, InvalidProtocolBufferException;

        Class<?> publicKeyManagerClassOrNull();

        Set<Class<?>> supportedPrimitives();
    }

    public KeyManagerRegistry(KeyManagerRegistry keyManagerRegistry) {
        this.keyManagerMap = new ConcurrentHashMap(keyManagerRegistry.keyManagerMap);
    }

    private static <T> T checkNotNull(T t2) {
        t2.getClass();
        return t2;
    }

    private static <P> KeyManagerContainer createContainerFor(final KeyManager<P> keyManager) {
        return new KeyManagerContainer() {
            public Class<?> getImplementingClass() {
                return KeyManager.this.getClass();
            }

            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) throws GeneralSecurityException {
                if (KeyManager.this.getPrimitiveClass().equals(cls)) {
                    return KeyManager.this;
                }
                throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
            }

            public KeyManager<?> getUntypedKeyManager() {
                return KeyManager.this;
            }

            public MessageLite parseKey(ByteString byteString) throws GeneralSecurityException, InvalidProtocolBufferException {
                return null;
            }

            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            public Set<Class<?>> supportedPrimitives() {
                return Collections.singleton(KeyManager.this.getPrimitiveClass());
            }
        };
    }

    private static <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> KeyManagerContainer createPrivateKeyContainerFor(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> keyTypeManager) {
        return new KeyManagerContainer() {
            public Class<?> getImplementingClass() {
                return PrivateKeyTypeManager.this.getClass();
            }

            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) throws GeneralSecurityException {
                try {
                    return new PrivateKeyManagerImpl(PrivateKeyTypeManager.this, keyTypeManager, cls);
                } catch (IllegalArgumentException e3) {
                    throw new GeneralSecurityException("Primitive type not supported", e3);
                }
            }

            public KeyManager<?> getUntypedKeyManager() {
                PrivateKeyTypeManager privateKeyTypeManager = PrivateKeyTypeManager.this;
                return new PrivateKeyManagerImpl(privateKeyTypeManager, keyTypeManager, privateKeyTypeManager.firstSupportedPrimitiveClass());
            }

            public MessageLite parseKey(ByteString byteString) throws GeneralSecurityException, InvalidProtocolBufferException {
                MessageLite parseKey = PrivateKeyTypeManager.this.parseKey(byteString);
                PrivateKeyTypeManager.this.validateKey(parseKey);
                return parseKey;
            }

            public Class<?> publicKeyManagerClassOrNull() {
                return keyTypeManager.getClass();
            }

            public Set<Class<?>> supportedPrimitives() {
                return PrivateKeyTypeManager.this.supportedPrimitives();
            }
        };
    }

    private synchronized KeyManagerContainer getKeyManagerContainerOrThrow(String str) throws GeneralSecurityException {
        if (this.keyManagerMap.containsKey(str)) {
        } else {
            throw new GeneralSecurityException("No key manager found for key type " + str);
        }
        return this.keyManagerMap.get(str);
    }

    private <P> KeyManager<P> getKeyManagerInternal(String str, Class<P> cls) throws GeneralSecurityException {
        KeyManagerContainer keyManagerContainerOrThrow = getKeyManagerContainerOrThrow(str);
        if (cls == null) {
            return keyManagerContainerOrThrow.getUntypedKeyManager();
        }
        if (keyManagerContainerOrThrow.supportedPrimitives().contains(cls)) {
            return keyManagerContainerOrThrow.getKeyManager(cls);
        }
        StringBuilder sb = new StringBuilder("Primitive type ");
        b.t(cls, sb, " not supported by key manager of type ");
        sb.append(keyManagerContainerOrThrow.getImplementingClass());
        sb.append(", supported primitives: ");
        sb.append(toCommaSeparatedString(keyManagerContainerOrThrow.supportedPrimitives()));
        throw new GeneralSecurityException(sb.toString());
    }

    private synchronized <P> void registerKeyManagerContainer(KeyManagerContainer keyManagerContainer, boolean z2) throws GeneralSecurityException {
        try {
            String keyType = keyManagerContainer.getUntypedKeyManager().getKeyType();
            KeyManagerContainer keyManagerContainer2 = this.keyManagerMap.get(keyType);
            if (keyManagerContainer2 != null) {
                if (!keyManagerContainer2.getImplementingClass().equals(keyManagerContainer.getImplementingClass())) {
                    Logger logger2 = logger;
                    logger2.warning("Attempted overwrite of a registered key manager for key type " + keyType);
                    String name = keyManagerContainer2.getImplementingClass().getName();
                    String name2 = keyManagerContainer.getImplementingClass().getName();
                    throw new GeneralSecurityException("typeUrl (" + keyType + ") is already registered with " + name + ", cannot be re-registered with " + name2);
                }
            }
            if (!z2) {
                this.keyManagerMap.putIfAbsent(keyType, keyManagerContainer);
            } else {
                this.keyManagerMap.put(keyType, keyManagerContainer);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private static String toCommaSeparatedString(Set<Class<?>> set) {
        StringBuilder sb = new StringBuilder();
        boolean z2 = true;
        for (Class next : set) {
            if (!z2) {
                sb.append(", ");
            }
            sb.append(next.getCanonicalName());
            z2 = false;
        }
        return sb.toString();
    }

    @Deprecated
    public <P> KeyManager<P> getKeyManager(String str) throws GeneralSecurityException {
        return getKeyManagerInternal(str, (Class) null);
    }

    public KeyManager<?> getUntypedKeyManager(String str) throws GeneralSecurityException {
        return getKeyManagerContainerOrThrow(str).getUntypedKeyManager();
    }

    public boolean isEmpty() {
        return this.keyManagerMap.isEmpty();
    }

    public MessageLite parseKeyData(KeyData keyData) throws GeneralSecurityException, InvalidProtocolBufferException {
        return getKeyManagerContainerOrThrow(keyData.getTypeUrl()).parseKey(keyData.getValue());
    }

    public synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, KeyTypeManager<PublicKeyProtoT> keyTypeManager) throws GeneralSecurityException {
        Class<?> publicKeyManagerClassOrNull;
        try {
            TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus = privateKeyTypeManager.fipsStatus();
            TinkFipsUtil.AlgorithmFipsCompatibility fipsStatus2 = keyTypeManager.fipsStatus();
            if (!fipsStatus.isCompatible()) {
                throw new GeneralSecurityException("failed to register key manager " + privateKeyTypeManager.getClass() + " as it is not FIPS compatible.");
            } else if (fipsStatus2.isCompatible()) {
                String keyType = privateKeyTypeManager.getKeyType();
                String keyType2 = keyTypeManager.getKeyType();
                if (!(!this.keyManagerMap.containsKey(keyType) || this.keyManagerMap.get(keyType).publicKeyManagerClassOrNull() == null || (publicKeyManagerClassOrNull = this.keyManagerMap.get(keyType).publicKeyManagerClassOrNull()) == null)) {
                    if (!publicKeyManagerClassOrNull.getName().equals(keyTypeManager.getClass().getName())) {
                        Logger logger2 = logger;
                        logger2.warning("Attempted overwrite of a registered key manager for key type " + keyType + " with inconsistent public key type " + keyType2);
                        String name = privateKeyTypeManager.getClass().getName();
                        String name2 = publicKeyManagerClassOrNull.getName();
                        String name3 = keyTypeManager.getClass().getName();
                        throw new GeneralSecurityException("public key manager corresponding to " + name + " is already registered with " + name2 + ", cannot be re-registered with " + name3);
                    }
                }
                registerKeyManagerContainer(createPrivateKeyContainerFor(privateKeyTypeManager, keyTypeManager), true);
                registerKeyManagerContainer(createContainerFor(keyTypeManager), false);
            } else {
                throw new GeneralSecurityException("failed to register key manager " + keyTypeManager.getClass() + " as it is not FIPS compatible.");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized <P> void registerKeyManager(KeyManager<P> keyManager) throws GeneralSecurityException {
        if (TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
            registerKeyManagerContainer(createContainerFor(keyManager), false);
        } else {
            throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
        }
    }

    public boolean typeUrlExists(String str) {
        return this.keyManagerMap.containsKey(str);
    }

    private static <KeyProtoT extends MessageLite> KeyManagerContainer createContainerFor(final KeyTypeManager<KeyProtoT> keyTypeManager) {
        return new KeyManagerContainer() {
            public Class<?> getImplementingClass() {
                return KeyTypeManager.this.getClass();
            }

            public <Q> KeyManager<Q> getKeyManager(Class<Q> cls) throws GeneralSecurityException {
                try {
                    return new KeyManagerImpl(KeyTypeManager.this, cls);
                } catch (IllegalArgumentException e3) {
                    throw new GeneralSecurityException("Primitive type not supported", e3);
                }
            }

            public KeyManager<?> getUntypedKeyManager() {
                KeyTypeManager keyTypeManager = KeyTypeManager.this;
                return new KeyManagerImpl(keyTypeManager, keyTypeManager.firstSupportedPrimitiveClass());
            }

            public MessageLite parseKey(ByteString byteString) throws GeneralSecurityException, InvalidProtocolBufferException {
                MessageLite parseKey = KeyTypeManager.this.parseKey(byteString);
                KeyTypeManager.this.validateKey(parseKey);
                return parseKey;
            }

            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            public Set<Class<?>> supportedPrimitives() {
                return KeyTypeManager.this.supportedPrimitives();
            }
        };
    }

    public <P> KeyManager<P> getKeyManager(String str, Class<P> cls) throws GeneralSecurityException {
        return getKeyManagerInternal(str, (Class) checkNotNull(cls));
    }

    public KeyManagerRegistry() {
        this.keyManagerMap = new ConcurrentHashMap();
    }

    public synchronized <KeyProtoT extends MessageLite> void registerKeyManager(KeyTypeManager<KeyProtoT> keyTypeManager) throws GeneralSecurityException {
        if (keyTypeManager.fipsStatus().isCompatible()) {
            registerKeyManagerContainer(createContainerFor(keyTypeManager), false);
        } else {
            throw new GeneralSecurityException("failed to register key manager " + keyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
    }
}
