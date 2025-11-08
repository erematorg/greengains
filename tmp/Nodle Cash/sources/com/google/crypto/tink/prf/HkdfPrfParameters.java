package com.google.crypto.tink.prf;

import A.a;
import com.google.crypto.tink.annotations.Alpha;
import com.google.crypto.tink.util.Bytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Objects;
import javax.annotation.Nullable;

@Alpha
public final class HkdfPrfParameters extends PrfParameters {
    private static final int MIN_KEY_SIZE = 16;
    private final HashType hashType;
    private final int keySizeBytes;
    @Nullable
    private final Bytes salt;

    public static final class Builder {
        @Nullable
        private HashType hashType;
        @Nullable
        private Integer keySizeBytes;
        @Nullable
        private Bytes salt;

        public HkdfPrfParameters build() throws GeneralSecurityException {
            Integer num = this.keySizeBytes;
            if (num == null) {
                throw new GeneralSecurityException("key size is not set");
            } else if (this.hashType != null) {
                return new HkdfPrfParameters(num.intValue(), this.hashType, this.salt);
            } else {
                throw new GeneralSecurityException("hash type is not set");
            }
        }

        @CanIgnoreReturnValue
        public Builder setHashType(HashType hashType2) {
            this.hashType = hashType2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setKeySizeBytes(int i3) throws GeneralSecurityException {
            if (i3 >= 16) {
                this.keySizeBytes = Integer.valueOf(i3);
                return this;
            }
            throw new InvalidAlgorithmParameterException(String.format("Invalid key size %d; only 128-bit or larger are supported", new Object[]{Integer.valueOf(i3 * 8)}));
        }

        @CanIgnoreReturnValue
        public Builder setSalt(Bytes bytes) {
            if (bytes.size() == 0) {
                return this;
            }
            this.salt = bytes;
            return this;
        }

        private Builder() {
            this.keySizeBytes = null;
            this.hashType = null;
            this.salt = null;
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

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HkdfPrfParameters)) {
            return false;
        }
        HkdfPrfParameters hkdfPrfParameters = (HkdfPrfParameters) obj;
        return hkdfPrfParameters.getKeySizeBytes() == getKeySizeBytes() && hkdfPrfParameters.getHashType() == getHashType() && Objects.equals(hkdfPrfParameters.getSalt(), getSalt());
    }

    public HashType getHashType() {
        return this.hashType;
    }

    public int getKeySizeBytes() {
        return this.keySizeBytes;
    }

    @Nullable
    public Bytes getSalt() {
        return this.salt;
    }

    public boolean hasIdRequirement() {
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.keySizeBytes), this.hashType, this.salt});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("HKDF PRF Parameters (hashType: ");
        sb.append(this.hashType);
        sb.append(", salt: ");
        sb.append(this.salt);
        sb.append(", and ");
        return a.m(sb, "-byte key)", this.keySizeBytes);
    }

    private HkdfPrfParameters(int i3, HashType hashType2, Bytes bytes) {
        this.keySizeBytes = i3;
        this.hashType = hashType2;
        this.salt = bytes;
    }
}
