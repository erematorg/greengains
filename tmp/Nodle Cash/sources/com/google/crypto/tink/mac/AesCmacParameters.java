package com.google.crypto.tink.mac;

import A.a;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

public final class AesCmacParameters extends MacParameters {
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        public AesCmacParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("key size not set");
            } else if (this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size not set");
            } else if (this.variant != null) {
                return new AesCmacParameters(num.intValue(), this.tagSizeBytes.intValue(), this.variant);
            } else {
                throw new GeneralSecurityException("variant not set");
            }
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i3) throws GeneralSecurityException {
            if (i3 == 16 || i3 == 32) {
                this.keySizeBytes = Integer.valueOf(i3);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit and 256-bit AES keys are supported", new Object[]{Integer.valueOf(i3 * 8)}));
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i3) throws GeneralSecurityException {
            if (i3 < 10 || 16 < i3) {
                throw new GeneralSecurityException(a.k("Invalid tag size for AesCmacParameters: ", i3));
            }
            this.tagSizeBytes = Integer.valueOf(i3);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setVariant(Variant variant2) {
            this.variant = variant2;
            return this;
        }

        private Builder() {
            this.keySizeBytes = null;
            this.tagSizeBytes = null;
            this.variant = Variant.NO_PREFIX;
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
        if (!(obj instanceof AesCmacParameters)) {
            return false;
        }
        AesCmacParameters aesCmacParameters = (AesCmacParameters) obj;
        return aesCmacParameters.getKeySizeBytes() == getKeySizeBytes() && aesCmacParameters.getTotalTagSizeBytes() == getTotalTagSizeBytes() && aesCmacParameters.getVariant() == getVariant();
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    public int getTotalTagSizeBytes() {
        int cryptographicTagSizeBytes;
        Variant variant2 = this.variant;
        if (variant2 == Variant.NO_PREFIX) {
            return getCryptographicTagSizeBytes();
        }
        if (variant2 == Variant.TINK) {
            cryptographicTagSizeBytes = getCryptographicTagSizeBytes();
        } else if (variant2 == Variant.CRUNCHY) {
            cryptographicTagSizeBytes = getCryptographicTagSizeBytes();
        } else if (variant2 == Variant.LEGACY) {
            cryptographicTagSizeBytes = getCryptographicTagSizeBytes();
        } else {
            throw new IllegalStateException("Unknown variant");
        }
        return cryptographicTagSizeBytes + 5;
    }

    public Variant getVariant() {
        return this.variant;
    }

    public boolean hasIdRequirement() {
        return this.variant != Variant.NO_PREFIX;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AES-CMAC Parameters (variant: ");
        sb.append(this.variant);
        sb.append(", ");
        sb.append(this.tagSizeBytes);
        sb.append("-byte tags, and ");
        return a.m(sb, "-byte key)", this.keySizeBytes);
    }

    private AesCmacParameters(int i3, int i4, Variant variant2) {
        this.keySizeBytes = i3;
        this.tagSizeBytes = i4;
        this.variant = variant2;
    }
}
