package com.google.crypto.tink.signature;

import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.spec.ECParameterSpec;
import java.util.Objects;

@Alpha
public final class EcdsaParameters extends SignatureParameters {
    private final CurveType curveType;
    private final HashType hashType;
    private final SignatureEncoding signatureEncoding;
    private final Variant variant;

    public static final class Builder {
        private CurveType curveType;
        private HashType hashType;
        private SignatureEncoding signatureEncoding;
        private Variant variant;

        public EcdsaParameters build() throws GeneralSecurityException {
            SignatureEncoding signatureEncoding2 = this.signatureEncoding;
            if (signatureEncoding2 != null) {
                CurveType curveType2 = this.curveType;
                if (curveType2 != null) {
                    HashType hashType2 = this.hashType;
                    if (hashType2 != null) {
                        Variant variant2 = this.variant;
                        if (variant2 == null) {
                            throw new GeneralSecurityException("variant is not set");
                        } else if (curveType2 == CurveType.NIST_P256 && hashType2 != HashType.SHA256) {
                            throw new GeneralSecurityException("NIST_P256 requires SHA256");
                        } else if (curveType2 == CurveType.NIST_P384 && hashType2 != HashType.SHA384 && hashType2 != HashType.SHA512) {
                            throw new GeneralSecurityException("NIST_P384 requires SHA384 or SHA512");
                        } else if (curveType2 != CurveType.NIST_P521 || hashType2 == HashType.SHA512) {
                            return new EcdsaParameters(signatureEncoding2, curveType2, hashType2, variant2);
                        } else {
                            throw new GeneralSecurityException("NIST_P521 requires SHA512");
                        }
                    } else {
                        throw new GeneralSecurityException("hash type is not set");
                    }
                } else {
                    throw new GeneralSecurityException("EC curve type is not set");
                }
            } else {
                throw new GeneralSecurityException("signature encoding is not set");
            }
        }

        @CanIgnoreReturnValue
        public Builder setCurveType(CurveType curveType2) {
            this.curveType = curveType2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHashType(HashType hashType2) {
            this.hashType = hashType2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setSignatureEncoding(SignatureEncoding signatureEncoding2) {
            this.signatureEncoding = signatureEncoding2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setVariant(Variant variant2) {
            this.variant = variant2;
            return this;
        }

        private Builder() {
            this.signatureEncoding = null;
            this.curveType = null;
            this.hashType = null;
            this.variant = Variant.NO_PREFIX;
        }
    }

    @Immutable
    public static final class CurveType {
        public static final CurveType NIST_P256 = new CurveType("NIST_P256", EllipticCurvesUtil.NIST_P256_PARAMS);
        public static final CurveType NIST_P384 = new CurveType("NIST_P384", EllipticCurvesUtil.NIST_P384_PARAMS);
        public static final CurveType NIST_P521 = new CurveType("NIST_P521", EllipticCurvesUtil.NIST_P521_PARAMS);
        private final String name;
        private final ECParameterSpec spec;

        private CurveType(String str, ECParameterSpec eCParameterSpec) {
            this.name = str;
            this.spec = eCParameterSpec;
        }

        public static CurveType fromParameterSpec(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
            CurveType curveType = NIST_P256;
            if (EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec, curveType.toParameterSpec())) {
                return curveType;
            }
            CurveType curveType2 = NIST_P384;
            if (EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec, curveType2.toParameterSpec())) {
                return curveType2;
            }
            CurveType curveType3 = NIST_P521;
            if (EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec, curveType3.toParameterSpec())) {
                return curveType3;
            }
            throw new GeneralSecurityException("unknown ECParameterSpec");
        }

        public ECParameterSpec toParameterSpec() {
            return this.spec;
        }

        public String toString() {
            return this.name;
        }
    }

    @Immutable
    public static final class HashType {
        public static final HashType SHA256 = new HashType("SHA256");
        public static final HashType SHA384 = new HashType("SHA384");
        public static final HashType SHA512 = new HashType("SHA512");
        private final String name;

        private HashType(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    @Immutable
    public static final class SignatureEncoding {
        public static final SignatureEncoding DER = new SignatureEncoding("DER");
        public static final SignatureEncoding IEEE_P1363 = new SignatureEncoding("IEEE_P1363");
        private final String name;

        private SignatureEncoding(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY = new Variant("CRUNCHY");
        public static final Variant LEGACY = new Variant("LEGACY");
        public static final Variant NO_PREFIX = new Variant("NO_PREFIX");
        public static final Variant TINK = new Variant("TINK");
        private final String name;

        private Variant(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EcdsaParameters)) {
            return false;
        }
        EcdsaParameters ecdsaParameters = (EcdsaParameters) obj;
        return ecdsaParameters.getSignatureEncoding() == getSignatureEncoding() && ecdsaParameters.getCurveType() == getCurveType() && ecdsaParameters.getHashType() == getHashType() && ecdsaParameters.getVariant() == getVariant();
    }

    public CurveType getCurveType() {
        return this.curveType;
    }

    public HashType getHashType() {
        return this.hashType;
    }

    public SignatureEncoding getSignatureEncoding() {
        return this.signatureEncoding;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.signatureEncoding, this.curveType, this.hashType, this.variant});
    }

    public String toString() {
        return "ECDSA Parameters (variant: " + this.variant + ", hashType: " + this.hashType + ", encoding: " + this.signatureEncoding + ", curve: " + this.curveType + ")";
    }

    private EcdsaParameters(SignatureEncoding signatureEncoding2, CurveType curveType2, HashType hashType2, Variant variant2) {
        this.signatureEncoding = signatureEncoding2;
        this.curveType = curveType2;
        this.hashType = hashType2;
        this.variant = variant2;
    }
}
