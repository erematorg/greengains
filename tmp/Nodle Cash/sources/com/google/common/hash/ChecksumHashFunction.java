package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.zip.Checksum;

@ElementTypesAreNonnullByDefault
@Immutable
final class ChecksumHashFunction extends AbstractHashFunction implements Serializable {
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final int bits;
    private final ImmutableSupplier<? extends Checksum> checksumSupplier;
    private final String toString;

    public final class ChecksumHasher extends AbstractByteHasher {
        private final Checksum checksum;

        public HashCode hash() {
            long value = this.checksum.getValue();
            return ChecksumHashFunction.this.bits == 32 ? HashCode.fromInt((int) value) : HashCode.fromLong(value);
        }

        public void update(byte b3) {
            this.checksum.update(b3);
        }

        private ChecksumHasher(Checksum checksum2) {
            this.checksum = (Checksum) Preconditions.checkNotNull(checksum2);
        }

        public void update(byte[] bArr, int i3, int i4) {
            this.checksum.update(bArr, i3, i4);
        }
    }

    public ChecksumHashFunction(ImmutableSupplier<? extends Checksum> immutableSupplier, int i3, String str) {
        this.checksumSupplier = (ImmutableSupplier) Preconditions.checkNotNull(immutableSupplier);
        Preconditions.checkArgument(i3 == 32 || i3 == 64, "bits (%s) must be either 32 or 64", i3);
        this.bits = i3;
        this.toString = (String) Preconditions.checkNotNull(str);
    }

    public int bits() {
        return this.bits;
    }

    public Hasher newHasher() {
        return new ChecksumHasher((Checksum) this.checksumSupplier.get());
    }

    public String toString() {
        return this.toString;
    }
}
