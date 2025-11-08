package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

@ElementTypesAreNonnullByDefault
@Immutable
final class MacHashFunction extends AbstractHashFunction {
    private final int bits;
    private final Key key;
    private final Mac prototype;
    private final boolean supportsClone;
    private final String toString;

    public static final class MacHasher extends AbstractByteHasher {
        private boolean done;
        private final Mac mac;

        private void checkNotDone() {
            Preconditions.checkState(!this.done, "Cannot re-use a Hasher after calling hash() on it");
        }

        public HashCode hash() {
            checkNotDone();
            this.done = true;
            return HashCode.fromBytesNoCopy(this.mac.doFinal());
        }

        public void update(byte b3) {
            checkNotDone();
            this.mac.update(b3);
        }

        private MacHasher(Mac mac2) {
            this.mac = mac2;
        }

        public void update(byte[] bArr) {
            checkNotDone();
            this.mac.update(bArr);
        }

        public void update(byte[] bArr, int i3, int i4) {
            checkNotDone();
            this.mac.update(bArr, i3, i4);
        }

        public void update(ByteBuffer byteBuffer) {
            checkNotDone();
            Preconditions.checkNotNull(byteBuffer);
            this.mac.update(byteBuffer);
        }
    }

    public MacHashFunction(String str, Key key2, String str2) {
        Mac mac = getMac(str, key2);
        this.prototype = mac;
        this.key = (Key) Preconditions.checkNotNull(key2);
        this.toString = (String) Preconditions.checkNotNull(str2);
        this.bits = mac.getMacLength() * 8;
        this.supportsClone = supportsClone(mac);
    }

    private static Mac getMac(String str, Key key2) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(key2);
            return instance;
        } catch (NoSuchAlgorithmException e3) {
            throw new IllegalStateException(e3);
        } catch (InvalidKeyException e4) {
            throw new IllegalArgumentException(e4);
        }
    }

    private static boolean supportsClone(Mac mac) {
        try {
            mac.clone();
            return true;
        } catch (CloneNotSupportedException unused) {
            return false;
        }
    }

    public int bits() {
        return this.bits;
    }

    public Hasher newHasher() {
        if (this.supportsClone) {
            try {
                return new MacHasher((Mac) this.prototype.clone());
            } catch (CloneNotSupportedException unused) {
            }
        }
        return new MacHasher(getMac(this.prototype.getAlgorithm(), this.key));
    }

    public String toString() {
        return this.toString;
    }
}
