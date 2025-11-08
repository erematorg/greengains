package com.google.common.hash;

import android.support.v4.media.session.a;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@Immutable
final class SipHashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction SIP_HASH_24 = new SipHashFunction(2, 4, 506097522914230528L, 1084818905618843912L);
    private static final long serialVersionUID = 0;

    /* renamed from: c  reason: collision with root package name */
    private final int f6918c;

    /* renamed from: d  reason: collision with root package name */
    private final int f6919d;

    /* renamed from: k0  reason: collision with root package name */
    private final long f6920k0;

    /* renamed from: k1  reason: collision with root package name */
    private final long f6921k1;

    public static final class SipHasher extends AbstractStreamingHasher {
        private static final int CHUNK_SIZE = 8;

        /* renamed from: b  reason: collision with root package name */
        private long f6922b = 0;

        /* renamed from: c  reason: collision with root package name */
        private final int f6923c;

        /* renamed from: d  reason: collision with root package name */
        private final int f6924d;
        private long finalM = 0;

        /* renamed from: v0  reason: collision with root package name */
        private long f6925v0;
        private long v1;
        private long v2;
        private long v3;

        public SipHasher(int i3, int i4, long j2, long j3) {
            super(8);
            this.f6923c = i3;
            this.f6924d = i4;
            this.f6925v0 = 8317987319222330741L ^ j2;
            this.v1 = 7237128888997146477L ^ j3;
            this.v2 = 7816392313619706465L ^ j2;
            this.v3 = 8387220255154660723L ^ j3;
        }

        private void processM(long j2) {
            this.v3 ^= j2;
            sipRound(this.f6923c);
            this.f6925v0 = j2 ^ this.f6925v0;
        }

        private void sipRound(int i3) {
            for (int i4 = 0; i4 < i3; i4++) {
                long j2 = this.f6925v0;
                long j3 = this.v1;
                this.f6925v0 = j2 + j3;
                this.v2 += this.v3;
                this.v1 = Long.rotateLeft(j3, 13);
                long rotateLeft = Long.rotateLeft(this.v3, 16);
                long j4 = this.v1;
                long j5 = this.f6925v0;
                this.v1 = j4 ^ j5;
                this.v3 = rotateLeft ^ this.v2;
                long rotateLeft2 = Long.rotateLeft(j5, 32);
                long j6 = this.v2;
                long j7 = this.v1;
                this.v2 = j6 + j7;
                this.f6925v0 = rotateLeft2 + this.v3;
                this.v1 = Long.rotateLeft(j7, 17);
                long rotateLeft3 = Long.rotateLeft(this.v3, 21);
                long j8 = this.v1;
                long j9 = this.v2;
                this.v1 = j8 ^ j9;
                this.v3 = rotateLeft3 ^ this.f6925v0;
                this.v2 = Long.rotateLeft(j9, 32);
            }
        }

        public HashCode makeHash() {
            long j2 = this.finalM ^ (this.f6922b << 56);
            this.finalM = j2;
            processM(j2);
            this.v2 ^= 255;
            sipRound(this.f6924d);
            return HashCode.fromLong(((this.f6925v0 ^ this.v1) ^ this.v2) ^ this.v3);
        }

        public void process(ByteBuffer byteBuffer) {
            this.f6922b += 8;
            processM(byteBuffer.getLong());
        }

        public void processRemaining(ByteBuffer byteBuffer) {
            this.f6922b += (long) byteBuffer.remaining();
            int i3 = 0;
            while (byteBuffer.hasRemaining()) {
                this.finalM ^= (((long) byteBuffer.get()) & 255) << i3;
                i3 += 8;
            }
        }
    }

    public SipHashFunction(int i3, int i4, long j2, long j3) {
        boolean z2 = false;
        Preconditions.checkArgument(i3 > 0, "The number of SipRound iterations (c=%s) during Compression must be positive.", i3);
        Preconditions.checkArgument(i4 > 0 ? true : z2, "The number of SipRound iterations (d=%s) during Finalization must be positive.", i4);
        this.f6918c = i3;
        this.f6919d = i4;
        this.f6920k0 = j2;
        this.f6921k1 = j3;
    }

    public int bits() {
        return 64;
    }

    public boolean equals(@CheckForNull Object obj) {
        if (!(obj instanceof SipHashFunction)) {
            return false;
        }
        SipHashFunction sipHashFunction = (SipHashFunction) obj;
        return this.f6918c == sipHashFunction.f6918c && this.f6919d == sipHashFunction.f6919d && this.f6920k0 == sipHashFunction.f6920k0 && this.f6921k1 == sipHashFunction.f6921k1;
    }

    public int hashCode() {
        return (int) ((((long) ((SipHashFunction.class.hashCode() ^ this.f6918c) ^ this.f6919d)) ^ this.f6920k0) ^ this.f6921k1);
    }

    public Hasher newHasher() {
        return new SipHasher(this.f6918c, this.f6919d, this.f6920k0, this.f6921k1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Hashing.sipHash");
        sb.append(this.f6918c);
        sb.append("");
        sb.append(this.f6919d);
        sb.append("(");
        sb.append(this.f6920k0);
        sb.append(", ");
        return a.k(this.f6921k1, ")", sb);
    }
}
