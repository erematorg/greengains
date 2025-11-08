package com.google.crypto.tink.signature;

import com.google.crypto.tink.AccessesPartialKey;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.KeyParser;
import com.google.crypto.tink.internal.KeySerializer;
import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ParametersParser;
import com.google.crypto.tink.internal.ParametersSerializer;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.internal.ProtoParametersSerialization;
import com.google.crypto.tink.internal.Util;
import com.google.crypto.tink.proto.EcdsaKeyFormat;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPrivateKey;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyTemplate;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.signature.EcdsaParameters;
import com.google.crypto.tink.util.Bytes;
import com.google.crypto.tink.util.SecretBigInteger;
import java.security.GeneralSecurityException;
import java.security.spec.ECPoint;
import javax.annotation.Nullable;

@AccessesPartialKey
final class EcdsaProtoSerialization {
    private static final ParametersParser<ProtoParametersSerialization> PARAMETERS_PARSER;
    private static final ParametersSerializer<EcdsaParameters, ProtoParametersSerialization> PARAMETERS_SERIALIZER;
    private static final KeyParser<ProtoKeySerialization> PRIVATE_KEY_PARSER;
    private static final KeySerializer<EcdsaPrivateKey, ProtoKeySerialization> PRIVATE_KEY_SERIALIZER;
    private static final String PRIVATE_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPrivateKey";
    private static final Bytes PRIVATE_TYPE_URL_BYTES;
    private static final KeyParser<ProtoKeySerialization> PUBLIC_KEY_PARSER;
    private static final KeySerializer<EcdsaPublicKey, ProtoKeySerialization> PUBLIC_KEY_SERIALIZER;
    private static final String PUBLIC_TYPE_URL = "type.googleapis.com/google.crypto.tink.EcdsaPublicKey";
    private static final Bytes PUBLIC_TYPE_URL_BYTES;

    /* renamed from: com.google.crypto.tink.signature.EcdsaProtoSerialization$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$HashType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|(2:15|16)|17|19|20|21|22|23|24|(2:25|26)|27|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|12|13|15|16|17|19|20|21|22|23|24|25|26|27|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0068 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008e */
        static {
            /*
                com.google.crypto.tink.proto.EcdsaSignatureEncoding[] r0 = com.google.crypto.tink.proto.EcdsaSignatureEncoding.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding = r0
                r1 = 1
                com.google.crypto.tink.proto.EcdsaSignatureEncoding r2 = com.google.crypto.tink.proto.EcdsaSignatureEncoding.IEEE_P1363     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.proto.EcdsaSignatureEncoding r3 = com.google.crypto.tink.proto.EcdsaSignatureEncoding.DER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.google.crypto.tink.proto.EllipticCurveType[] r2 = com.google.crypto.tink.proto.EllipticCurveType.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType = r2
                com.google.crypto.tink.proto.EllipticCurveType r3 = com.google.crypto.tink.proto.EllipticCurveType.NIST_P256     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r2 = $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.google.crypto.tink.proto.EllipticCurveType r3 = com.google.crypto.tink.proto.EllipticCurveType.NIST_P384     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$EllipticCurveType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.crypto.tink.proto.EllipticCurveType r4 = com.google.crypto.tink.proto.EllipticCurveType.NIST_P521     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                com.google.crypto.tink.proto.OutputPrefixType[] r3 = com.google.crypto.tink.proto.OutputPrefixType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType = r3
                com.google.crypto.tink.proto.OutputPrefixType r4 = com.google.crypto.tink.proto.OutputPrefixType.TINK     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x005e }
                com.google.crypto.tink.proto.OutputPrefixType r4 = com.google.crypto.tink.proto.OutputPrefixType.CRUNCHY     // Catch:{ NoSuchFieldError -> 0x005e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x005e }
            L_0x005e:
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0068 }
                com.google.crypto.tink.proto.OutputPrefixType r4 = com.google.crypto.tink.proto.OutputPrefixType.LEGACY     // Catch:{ NoSuchFieldError -> 0x0068 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0068 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0068 }
            L_0x0068:
                int[] r3 = $SwitchMap$com$google$crypto$tink$proto$OutputPrefixType     // Catch:{ NoSuchFieldError -> 0x0073 }
                com.google.crypto.tink.proto.OutputPrefixType r4 = com.google.crypto.tink.proto.OutputPrefixType.RAW     // Catch:{ NoSuchFieldError -> 0x0073 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0073 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0073 }
            L_0x0073:
                com.google.crypto.tink.proto.HashType[] r3 = com.google.crypto.tink.proto.HashType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$proto$HashType = r3
                com.google.crypto.tink.proto.HashType r4 = com.google.crypto.tink.proto.HashType.SHA256     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r1 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x008e }
                com.google.crypto.tink.proto.HashType r3 = com.google.crypto.tink.proto.HashType.SHA384     // Catch:{ NoSuchFieldError -> 0x008e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x008e }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x008e }
            L_0x008e:
                int[] r0 = $SwitchMap$com$google$crypto$tink$proto$HashType     // Catch:{ NoSuchFieldError -> 0x0098 }
                com.google.crypto.tink.proto.HashType r1 = com.google.crypto.tink.proto.HashType.SHA512     // Catch:{ NoSuchFieldError -> 0x0098 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0098 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0098 }
            L_0x0098:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.signature.EcdsaProtoSerialization.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        Bytes bytesFromPrintableAscii = Util.toBytesFromPrintableAscii(PRIVATE_TYPE_URL);
        PRIVATE_TYPE_URL_BYTES = bytesFromPrintableAscii;
        Bytes bytesFromPrintableAscii2 = Util.toBytesFromPrintableAscii(PUBLIC_TYPE_URL);
        PUBLIC_TYPE_URL_BYTES = bytesFromPrintableAscii2;
        Class<ProtoParametersSerialization> cls = ProtoParametersSerialization.class;
        PARAMETERS_SERIALIZER = ParametersSerializer.create(new a(0), EcdsaParameters.class, cls);
        PARAMETERS_PARSER = ParametersParser.create(new a(1), bytesFromPrintableAscii, cls);
        Class<ProtoKeySerialization> cls2 = ProtoKeySerialization.class;
        PUBLIC_KEY_SERIALIZER = KeySerializer.create(new a(2), EcdsaPublicKey.class, cls2);
        PUBLIC_KEY_PARSER = KeyParser.create(new a(3), bytesFromPrintableAscii2, cls2);
        PRIVATE_KEY_SERIALIZER = KeySerializer.create(new a(4), EcdsaPrivateKey.class, cls2);
        PRIVATE_KEY_PARSER = KeyParser.create(new a(5), bytesFromPrintableAscii, cls2);
    }

    private EcdsaProtoSerialization() {
    }

    private static int getEncodingLength(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return 33;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return 49;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return 67;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static EcdsaParams getProtoParams(EcdsaParameters ecdsaParameters) throws GeneralSecurityException {
        return (EcdsaParams) EcdsaParams.newBuilder().setHashType(toProtoHashType(ecdsaParameters.getHashType())).setCurve(toProtoCurveType(ecdsaParameters.getCurveType())).setEncoding(toProtoSignatureEncoding(ecdsaParameters.getSignatureEncoding())).build();
    }

    private static EcdsaPublicKey getProtoPublicKey(EcdsaPublicKey ecdsaPublicKey) throws GeneralSecurityException {
        int encodingLength = getEncodingLength(ecdsaPublicKey.getParameters().getCurveType());
        ECPoint publicPoint = ecdsaPublicKey.getPublicPoint();
        return (EcdsaPublicKey) EcdsaPublicKey.newBuilder().setParams(getProtoParams(ecdsaPublicKey.getParameters())).setX(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineX(), encodingLength))).setY(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(publicPoint.getAffineY(), encodingLength))).build();
    }

    /* access modifiers changed from: private */
    public static EcdsaParameters parseParameters(ProtoParametersSerialization protoParametersSerialization) throws GeneralSecurityException {
        if (protoParametersSerialization.getKeyTemplate().getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            try {
                EcdsaKeyFormat parseFrom = EcdsaKeyFormat.parseFrom(protoParametersSerialization.getKeyTemplate().getValue(), ExtensionRegistryLite.getEmptyRegistry());
                return EcdsaParameters.builder().setHashType(toHashType(parseFrom.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(parseFrom.getParams().getEncoding())).setCurveType(toCurveType(parseFrom.getParams().getCurve())).setVariant(toVariant(protoParametersSerialization.getKeyTemplate().getOutputPrefixType())).build();
            } catch (InvalidProtocolBufferException e3) {
                throw new GeneralSecurityException("Parsing EcdsaParameters failed: ", e3);
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parseParameters: " + protoParametersSerialization.getKeyTemplate().getTypeUrl());
        }
    }

    /* access modifiers changed from: private */
    public static EcdsaPrivateKey parsePrivateKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (protoKeySerialization.getTypeUrl().equals(PRIVATE_TYPE_URL)) {
            try {
                EcdsaPrivateKey parseFrom = EcdsaPrivateKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if (parseFrom.getVersion() == 0) {
                    EcdsaPublicKey publicKey = parseFrom.getPublicKey();
                    return EcdsaPrivateKey.builder().setPublicKey(EcdsaPublicKey.builder().setParameters(EcdsaParameters.builder().setHashType(toHashType(publicKey.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(publicKey.getParams().getEncoding())).setCurveType(toCurveType(publicKey.getParams().getCurve())).setVariant(toVariant(protoKeySerialization.getOutputPrefixType())).build()).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(publicKey.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(publicKey.getY().toByteArray()))).setIdRequirement(protoKeySerialization.getIdRequirementOrNull()).build()).setPrivateValue(SecretBigInteger.fromBigInteger(BigIntegerEncoding.fromUnsignedBigEndianBytes(parseFrom.getKeyValue().toByteArray()), SecretKeyAccess.requireAccess(secretKeyAccess))).build();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPrivateKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePrivateKey: " + protoKeySerialization.getTypeUrl());
        }
    }

    /* access modifiers changed from: private */
    public static EcdsaPublicKey parsePublicKey(ProtoKeySerialization protoKeySerialization, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        if (protoKeySerialization.getTypeUrl().equals(PUBLIC_TYPE_URL)) {
            try {
                EcdsaPublicKey parseFrom = EcdsaPublicKey.parseFrom(protoKeySerialization.getValue(), ExtensionRegistryLite.getEmptyRegistry());
                if (parseFrom.getVersion() == 0) {
                    return EcdsaPublicKey.builder().setParameters(EcdsaParameters.builder().setHashType(toHashType(parseFrom.getParams().getHashType())).setSignatureEncoding(toSignatureEncoding(parseFrom.getParams().getEncoding())).setCurveType(toCurveType(parseFrom.getParams().getCurve())).setVariant(toVariant(protoKeySerialization.getOutputPrefixType())).build()).setPublicPoint(new ECPoint(BigIntegerEncoding.fromUnsignedBigEndianBytes(parseFrom.getX().toByteArray()), BigIntegerEncoding.fromUnsignedBigEndianBytes(parseFrom.getY().toByteArray()))).setIdRequirement(protoKeySerialization.getIdRequirementOrNull()).build();
                }
                throw new GeneralSecurityException("Only version 0 keys are accepted");
            } catch (InvalidProtocolBufferException | IllegalArgumentException unused) {
                throw new GeneralSecurityException("Parsing EcdsaPublicKey failed");
            }
        } else {
            throw new IllegalArgumentException("Wrong type URL in call to EcdsaProtoSerialization.parsePublicKey: " + protoKeySerialization.getTypeUrl());
        }
    }

    public static void register() throws GeneralSecurityException {
        register(MutableSerializationRegistry.globalInstance());
    }

    /* access modifiers changed from: private */
    public static ProtoParametersSerialization serializeParameters(EcdsaParameters ecdsaParameters) throws GeneralSecurityException {
        return ProtoParametersSerialization.create((KeyTemplate) KeyTemplate.newBuilder().setTypeUrl(PRIVATE_TYPE_URL).setValue(((EcdsaKeyFormat) EcdsaKeyFormat.newBuilder().setParams(getProtoParams(ecdsaParameters)).build()).toByteString()).setOutputPrefixType(toProtoOutputPrefixType(ecdsaParameters.getVariant())).build());
    }

    /* access modifiers changed from: private */
    public static ProtoKeySerialization serializePrivateKey(EcdsaPrivateKey ecdsaPrivateKey, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        return ProtoKeySerialization.create(PRIVATE_TYPE_URL, ((EcdsaPrivateKey) EcdsaPrivateKey.newBuilder().setPublicKey(getProtoPublicKey(ecdsaPrivateKey.getPublicKey())).setKeyValue(ByteString.copyFrom(BigIntegerEncoding.toBigEndianBytesOfFixedLength(ecdsaPrivateKey.getPrivateValue().getBigInteger(SecretKeyAccess.requireAccess(secretKeyAccess)), getEncodingLength(ecdsaPrivateKey.getParameters().getCurveType())))).build()).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PRIVATE, toProtoOutputPrefixType(ecdsaPrivateKey.getParameters().getVariant()), ecdsaPrivateKey.getIdRequirementOrNull());
    }

    /* access modifiers changed from: private */
    public static ProtoKeySerialization serializePublicKey(EcdsaPublicKey ecdsaPublicKey, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        return ProtoKeySerialization.create(PUBLIC_TYPE_URL, getProtoPublicKey(ecdsaPublicKey).toByteString(), KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC, toProtoOutputPrefixType(ecdsaPublicKey.getParameters().getVariant()), ecdsaPublicKey.getIdRequirementOrNull());
    }

    private static EcdsaParameters.CurveType toCurveType(EllipticCurveType ellipticCurveType) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$EllipticCurveType[ellipticCurveType.ordinal()];
        if (i3 == 1) {
            return EcdsaParameters.CurveType.NIST_P256;
        }
        if (i3 == 2) {
            return EcdsaParameters.CurveType.NIST_P384;
        }
        if (i3 == 3) {
            return EcdsaParameters.CurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unable to parse EllipticCurveType: " + ellipticCurveType.getNumber());
    }

    private static EcdsaParameters.HashType toHashType(HashType hashType) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$HashType[hashType.ordinal()];
        if (i3 == 1) {
            return EcdsaParameters.HashType.SHA256;
        }
        if (i3 == 2) {
            return EcdsaParameters.HashType.SHA384;
        }
        if (i3 == 3) {
            return EcdsaParameters.HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to parse HashType: " + hashType.getNumber());
    }

    private static EllipticCurveType toProtoCurveType(EcdsaParameters.CurveType curveType) throws GeneralSecurityException {
        if (EcdsaParameters.CurveType.NIST_P256.equals(curveType)) {
            return EllipticCurveType.NIST_P256;
        }
        if (EcdsaParameters.CurveType.NIST_P384.equals(curveType)) {
            return EllipticCurveType.NIST_P384;
        }
        if (EcdsaParameters.CurveType.NIST_P521.equals(curveType)) {
            return EllipticCurveType.NIST_P521;
        }
        throw new GeneralSecurityException("Unable to serialize CurveType " + curveType);
    }

    private static HashType toProtoHashType(EcdsaParameters.HashType hashType) throws GeneralSecurityException {
        if (EcdsaParameters.HashType.SHA256.equals(hashType)) {
            return HashType.SHA256;
        }
        if (EcdsaParameters.HashType.SHA384.equals(hashType)) {
            return HashType.SHA384;
        }
        if (EcdsaParameters.HashType.SHA512.equals(hashType)) {
            return HashType.SHA512;
        }
        throw new GeneralSecurityException("Unable to serialize HashType " + hashType);
    }

    private static OutputPrefixType toProtoOutputPrefixType(EcdsaParameters.Variant variant) throws GeneralSecurityException {
        if (EcdsaParameters.Variant.TINK.equals(variant)) {
            return OutputPrefixType.TINK;
        }
        if (EcdsaParameters.Variant.CRUNCHY.equals(variant)) {
            return OutputPrefixType.CRUNCHY;
        }
        if (EcdsaParameters.Variant.NO_PREFIX.equals(variant)) {
            return OutputPrefixType.RAW;
        }
        if (EcdsaParameters.Variant.LEGACY.equals(variant)) {
            return OutputPrefixType.LEGACY;
        }
        throw new GeneralSecurityException("Unable to serialize variant: " + variant);
    }

    private static EcdsaSignatureEncoding toProtoSignatureEncoding(EcdsaParameters.SignatureEncoding signatureEncoding) throws GeneralSecurityException {
        if (EcdsaParameters.SignatureEncoding.IEEE_P1363.equals(signatureEncoding)) {
            return EcdsaSignatureEncoding.IEEE_P1363;
        }
        if (EcdsaParameters.SignatureEncoding.DER.equals(signatureEncoding)) {
            return EcdsaSignatureEncoding.DER;
        }
        throw new GeneralSecurityException("Unable to serialize SignatureEncoding " + signatureEncoding);
    }

    private static EcdsaParameters.SignatureEncoding toSignatureEncoding(EcdsaSignatureEncoding ecdsaSignatureEncoding) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$EcdsaSignatureEncoding[ecdsaSignatureEncoding.ordinal()];
        if (i3 == 1) {
            return EcdsaParameters.SignatureEncoding.IEEE_P1363;
        }
        if (i3 == 2) {
            return EcdsaParameters.SignatureEncoding.DER;
        }
        throw new GeneralSecurityException("Unable to parse EcdsaSignatureEncoding: " + ecdsaSignatureEncoding.getNumber());
    }

    private static EcdsaParameters.Variant toVariant(OutputPrefixType outputPrefixType) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$proto$OutputPrefixType[outputPrefixType.ordinal()];
        if (i3 == 1) {
            return EcdsaParameters.Variant.TINK;
        }
        if (i3 == 2) {
            return EcdsaParameters.Variant.CRUNCHY;
        }
        if (i3 == 3) {
            return EcdsaParameters.Variant.LEGACY;
        }
        if (i3 == 4) {
            return EcdsaParameters.Variant.NO_PREFIX;
        }
        throw new GeneralSecurityException("Unable to parse OutputPrefixType: " + outputPrefixType.getNumber());
    }

    public static void register(MutableSerializationRegistry mutableSerializationRegistry) throws GeneralSecurityException {
        mutableSerializationRegistry.registerParametersSerializer(PARAMETERS_SERIALIZER);
        mutableSerializationRegistry.registerParametersParser(PARAMETERS_PARSER);
        mutableSerializationRegistry.registerKeySerializer(PUBLIC_KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(PUBLIC_KEY_PARSER);
        mutableSerializationRegistry.registerKeySerializer(PRIVATE_KEY_SERIALIZER);
        mutableSerializationRegistry.registerKeyParser(PRIVATE_KEY_PARSER);
    }
}
