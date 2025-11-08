package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
@Beta
public interface Hasher extends PrimitiveSink {
    HashCode hash();

    @Deprecated
    int hashCode();

    @CanIgnoreReturnValue
    Hasher putBoolean(boolean z2);

    @CanIgnoreReturnValue
    Hasher putByte(byte b3);

    @CanIgnoreReturnValue
    Hasher putBytes(ByteBuffer byteBuffer);

    @CanIgnoreReturnValue
    Hasher putBytes(byte[] bArr);

    @CanIgnoreReturnValue
    Hasher putBytes(byte[] bArr, int i3, int i4);

    @CanIgnoreReturnValue
    Hasher putChar(char c3);

    @CanIgnoreReturnValue
    Hasher putDouble(double d2);

    @CanIgnoreReturnValue
    Hasher putFloat(float f2);

    @CanIgnoreReturnValue
    Hasher putInt(int i3);

    @CanIgnoreReturnValue
    Hasher putLong(long j2);

    @CanIgnoreReturnValue
    <T> Hasher putObject(@ParametricNullness T t2, Funnel<? super T> funnel);

    @CanIgnoreReturnValue
    Hasher putShort(short s3);

    @CanIgnoreReturnValue
    Hasher putString(CharSequence charSequence, Charset charset);

    @CanIgnoreReturnValue
    Hasher putUnencodedChars(CharSequence charSequence);
}
