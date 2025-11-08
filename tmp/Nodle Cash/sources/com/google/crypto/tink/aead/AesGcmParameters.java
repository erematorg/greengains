package com.google.crypto.tink.aead;

import A.a;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesGcmParameters extends AeadParameters {
    private final int ivSizeBytes;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {
        @Nullable
        private Integer ivSizeBytes;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        public AesGcmParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("Key size is not set");
            } else if (this.variant == null) {
                throw new GeneralSecurityException("Variant is not set");
            } else if (this.ivSizeBytes == null) {
                throw new GeneralSecurityException("IV size is not set");
            } else if (this.tagSizeBytes != null) {
                return new AesGcmParameters(num.intValue(), this.ivSizeBytes.intValue(), this.tagSizeBytes.intValue(), this.variant);
            } else {
                throw new GeneralSecurityException("Tag size is not set");
            }
        }

        @CanIgnoreReturnValue
        public Builder setIvSizeBytes(int i3) throws GeneralSecurityException {
            if (i3 > 0) {
                this.ivSizeBytes = Integer.valueOf(i3);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid IV size in bytes %d; IV size must be positive", new Object[]{Integer.valueOf(i3)}));
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i3) throws GeneralSecurityException {
            if (i3 == 16 || i3 == 24 || i3 == 32) {
                this.keySizeBytes = Integer.valueOf(i3);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 16-byte, 24-byte and 32-byte AES keys are supported", new Object[]{Integer.valueOf(i3)}));
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i3) throws GeneralSecurityException {
            if (i3 == 12 || i3 == 13 || i3 == 14 || i3 == 15 || i3 == 16) {
                this.tagSizeBytes = Integer.valueOf(i3);
                return this;
            }
            throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; value must be one of the following: 12, 13, 14, 15 or 16 bytes", new Object[]{Integer.valueOf(i3)}));
        }

        @CanIgnoreReturnValue
        public Builder setVariant(Variant variant2) {
            this.variant = variant2;
            return this;
        }

        private Builder() {
            this.keySizeBytes = null;
            this.ivSizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
        }
    }

    @Immutable
    public static final class Variant {
        public static final Variant CRUNCHY = new Variant("CRUNCHY");
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
        if (!(obj instanceof AesGcmParameters)) {
            return false;
        }
        AesGcmParameters aesGcmParameters = (AesGcmParameters) obj;
        return aesGcmParameters.getKeySizeBytes() == getKeySizeBytes() && aesGcmParameters.getIvSizeBytes() == getIvSizeBytes() && aesGcmParameters.getTagSizeBytes() == getTagSizeBytes() && aesGcmParameters.getVariant() == getVariant();
    }

    public int getIvSizeBytes() {
        return this.ivSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.ivSizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AesGcm Parameters (variant: ");
        sb.append(this.variant);
        sb.append(", ");
        sb.append(this.ivSizeBytes);
        sb.append("-byte IV, ");
        sb.append(this.tagSizeBytes);
        sb.append("-byte tag, and ");
        return a.m(sb, "-byte key)", this.keySizeBytes);
    }

    private AesGcmParameters(int i3, int i4, int i5, Variant variant2) {
        this.keySizeBytes = i3;
        this.ivSizeBytes = i4;
        this.tagSizeBytes = i5;
        this.variant = variant2;
    }
}
