package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
@Immutable
abstract class AbstractHashFunction implements HashFunction {
    public HashCode hashBytes(byte[] bArr) {
        return hashBytes(bArr, 0, bArr.length);
    }

    public HashCode hashInt(int i3) {
        return newHasher(4).putInt(i3).hash();
    }

    public HashCode hashLong(long j2) {
        return newHasher(8).putLong(j2).hash();
    }

    public <T> HashCode hashObject(@ParametricNullness T t2, Funnel<? super T> funnel) {
        return newHasher().putObject(t2, funnel).hash();
    }

    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return newHasher().putString(charSequence, charset).hash();
    }

    public HashCode hashUnencodedChars(CharSequence charSequence) {
        return newHasher(charSequence.length() * 2).putUnencodedChars(charSequence).hash();
    }

    public Hasher newHasher(int i3) {
        Preconditions.checkArgument(i3 >= 0, "expectedInputSize must be >= 0 but was %s", i3);
        return newHasher();
    }

    public HashCode hashBytes(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        return newHasher(i4).putBytes(bArr, i3, i4).hash();
    }

    public HashCode hashBytes(ByteBuffer byteBuffer) {
        return newHasher(byteBuffer.remaining()).putBytes(byteBuffer).hash();
    }
}
