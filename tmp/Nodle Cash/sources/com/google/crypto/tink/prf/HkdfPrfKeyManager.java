package com.google.crypto.tink.prf;

import com.google.crypto.tink.KeyTemplate;
import com.google.crypto.tink.Registry;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrimitiveFactory;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKey;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.crypto.tink.subtle.prf.HkdfStreamingPrf;
import com.google.crypto.tink.subtle.prf.PrfImpl;
import com.google.crypto.tink.subtle.prf.StreamingPrf;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HkdfPrfKeyManager extends KeyTypeManager<HkdfPrfKey> {
    private static final int MIN_KEY_SIZE = 32;

    /* renamed from: com.google.crypto.tink.prf.HkdfPrfKeyManager$4  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.crypto.tink.proto.HashType[] r0 = com.google.crypto.tink.proto.HashType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$HashType = r0
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA1     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA384     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA512     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.prf.HkdfPrfKeyManager.AnonymousClass4.<clinit>():void");
        }
    }

    public HkdfPrfKeyManager() {
        super(HkdfPrfKey.class, new PrimitiveFactory<StreamingPrf, HkdfPrfKey>(StreamingPrf.class) {
            public StreamingPrf getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
                return new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray());
            }
        }, new PrimitiveFactory<Prf, HkdfPrfKey>(Prf.class) {
            public Prf getPrimitive(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
                return PrfImpl.wrap(new HkdfStreamingPrf(HkdfPrfKeyManager.convertHash(hkdfPrfKey.getParams().getHash()), hkdfPrfKey.getKeyValue().toByteArray(), hkdfPrfKey.getParams().getSalt().toByteArray()));
            }
        });
    }

    /* access modifiers changed from: private */
    public static Enums.HashType convertHash(HashType hashType) throws GeneralSecurityException {
        int i3 = AnonymousClass4.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i3 == 1) {
            return Enums.HashType.SHA1;
        }
        if (i3 == 2) {
            return Enums.HashType.SHA256;
        }
        if (i3 == 3) {
            return Enums.HashType.SHA384;
        }
        if (i3 == 4) {
            return Enums.HashType.SHA512;
        }
        throw new GeneralSecurityException("HashType " + hashType.name() + " not known in");
    }

    public static final KeyTemplate hkdfSha256Template() {
        return KeyTemplate.create(staticKeyType(), ((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build()).toByteArray(), KeyTemplate.OutputPrefixType.RAW);
    }

    public static void register(boolean z2) throws GeneralSecurityException {
        Registry.registerKeyManager(new HkdfPrfKeyManager(), z2);
        HkdfPrfProtoSerialization.register();
    }

    public static String staticKeyType() {
        return new HkdfPrfKeyManager().getKeyType();
    }

    /* access modifiers changed from: private */
    public static void validateKeySize(int i3) throws GeneralSecurityException {
        if (i3 < 32) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Key size too short");
        }
    }

    /* access modifiers changed from: private */
    public static void validateParams(HkdfPrfParams hkdfPrfParams) throws GeneralSecurityException {
        if (hkdfPrfParams.getHash() != HashType.SHA256 && hkdfPrfParams.getHash() != HashType.SHA512) {
            throw new GeneralSecurityException("Invalid HkdfPrfKey/HkdfPrfKeyFormat: Unsupported hash");
        }
    }

    public String getKeyType() {
        return "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    }

    public int getVersion() {
        return 0;
    }

    public KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, HkdfPrfKey> keyFactory() {
        return new KeyTypeManager.KeyFactory<HkdfPrfKeyFormat, HkdfPrfKey>(HkdfPrfKeyFormat.class) {
            public Map<String, KeyTypeManager.KeyFactory.KeyFormat<HkdfPrfKeyFormat>> keyFormats() throws GeneralSecurityException {
                HashMap hashMap = new HashMap();
                hashMap.put("HKDF_SHA256", new KeyTypeManager.KeyFactory.KeyFormat((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setKeySize(32).setParams(HkdfPrfParams.newBuilder().setHash(HashType.SHA256)).build(), KeyTemplate.OutputPrefixType.RAW));
                return Collections.unmodifiableMap(hashMap);
            }

            public HkdfPrfKey createKey(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
                return (HkdfPrfKey) HkdfPrfKey.newBuilder().setKeyValue(ByteString.copyFrom(Random.randBytes(hkdfPrfKeyFormat.getKeySize()))).setVersion(HkdfPrfKeyManager.this.getVersion()).setParams(hkdfPrfKeyFormat.getParams()).build();
            }

            public HkdfPrfKeyFormat parseKeyFormat(ByteString byteString) throws InvalidProtocolBufferException {
                return HkdfPrfKeyFormat.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
            }

            public void validateKeyFormat(HkdfPrfKeyFormat hkdfPrfKeyFormat) throws GeneralSecurityException {
                HkdfPrfKeyManager.validateKeySize(hkdfPrfKeyFormat.getKeySize());
                HkdfPrfKeyManager.validateParams(hkdfPrfKeyFormat.getParams());
            }
        };
    }

    public KeyData.KeyMaterialType keyMaterialType() {
        return KeyData.KeyMaterialType.SYMMETRIC;
    }

    public HkdfPrfKey parseKey(ByteString byteString) throws InvalidProtocolBufferException {
        return HkdfPrfKey.parseFrom(byteString, ExtensionRegistryLite.getEmptyRegistry());
    }

    public void validateKey(HkdfPrfKey hkdfPrfKey) throws GeneralSecurityException {
        Validators.validateVersion(hkdfPrfKey.getVersion(), getVersion());
        validateKeySize(hkdfPrfKey.getKeyValue().size());
        validateParams(hkdfPrfKey.getParams());
    }
}
