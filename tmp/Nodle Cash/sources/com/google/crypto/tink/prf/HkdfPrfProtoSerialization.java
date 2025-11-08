package com.google.crypto.tink.prf;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.prf.HkdfPrfParameters;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.HkdfPrfKey;
import com.google.crypto.tink.proto.HkdfPrfKeyFormat;
import com.google.crypto.tink.proto.HkdfPrfParams;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBytes;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

@AccessesPartialKey
final class HkdfPrfProtoSerialization {
    private static final KeyParser<ProtoKeySerialization> KEY_PARSER;
    private static final KeySerializer<HkdfPrfKey, ProtoKeySerialization> KEY_SERIALIZER;
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<HkdfPrfParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final String TYPE_URL = "type.googleapis.com/google.crypto.tink.HkdfPrfKey";
    private static final Bytes TYPE_URL_BYTES;

    /* renamed from: com.google.crypto.tink.prf.HkdfPrfProtoSerialization$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
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
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA224     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA384     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA512     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.prf.HkdfPrfProtoSerialization.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, com.google.crypto.tink.internal.ParametersSerializer$ParametersSerializationFunction] */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.google.crypto.tink.internal.ParametersParser$ParametersParsingFunction, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Object, com.google.crypto.tink.internal.KeySerializer$KeySerializationFunction] */
    /* JADX WARNING: type inference failed for: r1v6, types: [com.google.crypto.tink.internal.KeyParser$KeyParsingFunction, java.lang.Object] */
    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(TYPE_URL);
        TYPE_URL_BYTES = bytesFromPrintableAscii;
        Class<ProtoParametersSerialization> cls = ProtoParametersSerialization.class;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new Object(), HkdfPrfParameters.class, cls);
        PARAMETERS_PARSER = ParametersParser.create(new Object(), bytesFromPrintableAscii, cls);
        Class<ProtoKeySerialization> cls2 = ProtoKeySerialization.class;
        KEY_SERIALIZER = KeySerializer.create(new Object(), HkdfPrfKey.class, cls2);
        KEY_PARSER = KeyParser.create(new Object(), bytesFromPrintableAscii, cls2);
    }

    private HkdfPrfProtoSerialization() {
    }

    private static HkdfPrfParams getProtoParams(HkdfPrfParameters hkdfPrfParameters) throws GeneralSecurityException {
        HkdfPrfParams.Builder hash = HkdfPrfParams.newBuilder().setHash(toProtoHashType(hkdfPrfParameters.getHashType()));
        if (hkdfPrfParameters.getSalt() != null && hkdfPrfParameters.getSalt().size() > 0) {
            hash.setSalt(ByteString.copyFrom(hkdfPrfParameters.getSalt().toByteArray()));
        }
        return (HkdfPrfParams) hash.build();
    }

    /* access modifiers changed from: private */
    public static HkdfPrfKey parseKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (protoKeySerialization.getTypeUrl().equals(TYPE_URL)) {
            try {
                HkdfPrfKey parseFrom = HkdfPrfKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if (parseFrom.getVersion() != 0) {
                    throw new GeneralSecurityException("Only version 0 keys are accepted");
                } else if (protoKeySerialization.getIdRequirementOrNull() == null) {
                    return HkdfPrfKey.builder().setParameters(HkdfPrfParameters.builder().setKeySizeBytes(parseFrom.getKeyValue().size()).setHashType(toHashType(parseFrom.getParams().getHash())).setSalt(Bytes.copyFrom(parseFrom.getParams().getSalt().toByteArray())).build()).setKeyBytes(SecretBytes.copyFrom(parseFrom.getKeyValue().toByteArray(), SecretKeyAccess.requireAccess(secretKeyAccess))).build();
                } else {
                    throw new GeneralSecurityException("ID requirement must be null.");
                }
            } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing HkdfPrfKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseKey");
        }
    }

    /* access modifiers changed from: private */
    public static HkdfPrfParameters parseParameters(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
        if (protoParametersSerialization.getKeyTemplate().getTypeUrl().equals(TYPE_URL)) {
            try {
                HkdfPrfKeyFormat parseFrom = HkdfPrfKeyFormat.parseFrom(protoParametersSerialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if (parseFrom.getVersion() != 0) {
                    throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: unknown Version " + parseFrom.getVersion());
                } else if (protoParametersSerialization.getKeyTemplate().getOutputPrefixType() == OutputPrefixType.RAW) {
                    return HkdfPrfParameters.builder().setKeySizeBytes(parseFrom.getKeySize()).setHashType(toHashType(parseFrom.getParams().getHash())).setSalt(Bytes.copyFrom(parseFrom.getParams().getSalt().toByteArray())).build();
                } else {
                    throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: only RAW output prefix type is accepted");
                }
            } catch (InvalidProtocolBufferException e3) {
                throw new GeneralSecurityException("Parsing HkdfPrfParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to HkdfPrfProtoSerialization.parseParameters: " + protoParametersSerialization.getKeyTemplate().getTypeUrl());
        }
    }

    public static void register() throws GeneralSecurityException {
        register(MutableSerializationRegistry.globalInstance());
    }

    /* access modifiers changed from: private */
    public static ProtoKeySerialization serializeKey(HkdfPrfKey hkdfPrfKey, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        return ProtoKeySerialization.create(TYPE_URL, ((HkdfPrfKey) HkdfPrfKey.newBuilder().setParams(getProtoParams(hkdfPrfKey.getParameters())).setKeyValue(ByteString.copyFrom(hkdfPrfKey.getKeyBytes().toByteArray(SecretKeyAccess.requireAccess(secretKeyAccess)))).build()).toByteString(), KeyData.KeyMaterialType.SYMMETRIC, OutputPrefixType.RAW, hkdfPrfKey.getIdRequirementOrNull());
    }

    /* access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(HkdfPrfParameters hkdfPrfParameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(TYPE_URL).setValue(((HkdfPrfKeyFormat) HkdfPrfKeyFormat.newBuilder().setParams(getProtoParams(hkdfPrfParameters)).setKeySize(hkdfPrfParameters.getKeySizeBytes()).build()).toByteString()).setOutputPrefixType(OutputPrefixType.RAW).build());
    }

    private static HkdfPrfParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i3 == 1) {
            return HkdfPrfParameters.HashType.SHA1;
        }
        if (i3 == 2) {
            return HkdfPrfParameters.HashType.SHA224;
        }
        if (i3 == 3) {
            return HkdfPrfParameters.HashType.SHA256;
        }
        if (i3 == 4) {
            return HkdfPrfParameters.HashType.SHA384;
        }
        if (i3 == 5) {
            return HkdfPrfParameters.HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
    }

    private static HashType toProtoHashType(HkdfPrfParameters.HashType hashType) throws GeneralSecurityException {
        if (HkdfPrfParameters.HashType.SHA1.equals(hashType)) {
            return HashType.SHA1;
        }
        if (HkdfPrfParameters.HashType.SHA224.equals(hashType)) {
            return HashType.SHA224;
        }
        if (HkdfPrfParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (HkdfPrfParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (HkdfPrfParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry) throws GeneralSecurityException {
        mutableSerializationRegistry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        mutableSerializationRegistry.registerParametersParser(PARAMETERS_PARSER);
        mutableSerializationRegistry.registerKeySerializer(KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(KEY_PARSER);
    }
}
