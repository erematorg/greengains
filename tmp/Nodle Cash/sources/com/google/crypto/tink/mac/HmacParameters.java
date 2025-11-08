package com.google.crypto.tink.mac;

import A.a;
import com.google.crypto.tink.annotations.Alpha;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

@Alpha
public final class HmacParameters extends MacParameters {
    private final HashType hashType;
    private final int keySizeBytes;
    private final int tagSizeBytes;
    private final Variant variant;

    public static final class Builder {
        private HashType hashType;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Integer tagSizeBytes;
        private Variant variant;

        private static void validateTagSizeBytes(int i3, HashType hashType2) throws GeneralSecurityException {
            if (i3 < 10) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; must be at least 10 bytes", new Object[]{Integer.valueOf(i3)}));
            } else if (hashType2 == HashType.SHA1) {
                if (i3 > 20) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 20 bytes for SHA1", new Object[]{Integer.valueOf(i3)}));
                }
            } else if (hashType2 == HashType.SHA224) {
                if (i3 > 28) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 28 bytes for SHA224", new Object[]{Integer.valueOf(i3)}));
                }
            } else if (hashType2 == HashType.SHA256) {
                if (i3 > 32) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 32 bytes for SHA256", new Object[]{Integer.valueOf(i3)}));
                }
            } else if (hashType2 == HashType.SHA384) {
                if (i3 > 48) {
                    throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 48 bytes for SHA384", new Object[]{Integer.valueOf(i3)}));
                }
            } else if (hashType2 != HashType.SHA512) {
                throw new GeneralSecurityException("unknown hash type; must be SHA256, SHA384 or SHA512");
            } else if (i3 > 64) {
                throw new GeneralSecurityException(String.format("Invalid tag size in bytes %d; can be at most 64 bytes for SHA512", new Object[]{Integer.valueOf(i3)}));
            }
        }

        public HmacParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("key size is not set");
            } else if (this.tagSizeBytes == null) {
                throw new GeneralSecurityException("tag size is not set");
            } else if (this.hashType == null) {
                throw new GeneralSecurityException("hash type is not set");
            } else if (this.variant == null) {
                throw new GeneralSecurityException("variant is not set");
            } else if (num.intValue() >= 16) {
                validateTagSizeBytes(this.tagSizeBytes.intValue(), this.hashType);
                return new HmacParameters(this.keySizeBytes.intValue(), this.tagSizeBytes.intValue(), this.variant, this.hashType);
            } else {
                throw new InvalidAlgorithmParameterException(String.format("Invalid key size in bytes %d; must be at least 16 bytes", new Object[]{this.keySizeBytes}));
            }
        }

        @CanIgnoreReturnValue
        public Builder setHashType(HashType hashType2) {
            this.hashType = hashType2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i3) throws GeneralSecurityException {
            this.keySizeBytes = Integer.valueOf(i3);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setTagSizeBytes(int i3) throws GeneralSecurityException {
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
            this.hashType = null;
            this.variant = Variant.NO_PREFIX;
        }
    }

    @Immutable
    public static final class HashType {
        public static final HashType SHA1 = new HashType("SHA1");
        public static final HashType SHA224 = new HashType("SHA224");
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
        if (!(obj instanceof HmacParameters)) {
            return false;
        }
        HmacParameters hmacParameters = (HmacParameters) obj;
        return hmacParameters.getKeySizeBytes() == getKeySizeBytes() && hmacParameters.getTotalTagSizeBytes() == getTotalTagSizeBytes() && hmacParameters.getVariant() == getVariant() && hmacParameters.getHashType() == getHashType();
    }

    public int getCryptographicTagSizeBytes() {
        return this.tagSizeBytes;
    }

    public HashType getHashType() {
        return this.hashType;
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
        return Objects.hash(new Object[]{Integer.valueOf(this.keySizeBytes), Integer.valueOf(this.tagSizeBytes), this.variant, this.hashType});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HMAC Parameters (variant: ");
        sb.append(this.variant);
        sb.append(", hashType: ");
        sb.append(this.hashType);
        sb.append(", ");
        sb.append(this.tagSizeBytes);
        sb.append("-byte tags, and ");
        return a.m(sb, "-byte key)", this.keySizeBytes);
    }

    private HmacParameters(int i3, int i4, Variant variant2, HashType hashType2) {
        this.keySizeBytes = i3;
        this.tagSizeBytes = i4;
        this.variant = variant2;
        this.hashType = hashType2;
    }
}
