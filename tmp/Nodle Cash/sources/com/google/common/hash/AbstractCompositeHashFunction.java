package com.google.common.hash;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.Immutable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

@ElementTypesAreNonnullByDefault
@Immutable
abstract class AbstractCompositeHashFunction extends AbstractHashFunction {
    private static final long serialVersionUID = 0;
    final HashFunction[] functions;

    public AbstractCompositeHashFunction(HashFunction... hashFunctionArr) {
        for (HashFunction checkNotNull : hashFunctionArr) {
            Preconditions.checkNotNull(checkNotNull);
        }
        this.functions = hashFunctionArr;
    }

    private Hasher fromHashers(final Hasher[] hasherArr) {
        return new Hasher() {
            public HashCode hash() {
                return AbstractCompositeHashFunction.this.makeHash(hasherArr);
            }

            public <T> Hasher putObject(@ParametricNullness T t2, Funnel<? super T> funnel) {
                for (Hasher putObject : hasherArr) {
                    putObject.putObject(t2, funnel);
                }
                return this;
            }

            public Hasher putBoolean(boolean z2) {
                for (Hasher putBoolean : hasherArr) {
                    putBoolean.putBoolean(z2);
                }
                return this;
            }

            public Hasher putByte(byte b3) {
                for (Hasher putByte : hasherArr) {
                    putByte.putByte(b3);
                }
                return this;
            }

            public Hasher putChar(char c3) {
                for (Hasher putChar : hasherArr) {
                    putChar.putChar(c3);
                }
                return this;
            }

            public Hasher putDouble(double d2) {
                for (Hasher putDouble : hasherArr) {
                    putDouble.putDouble(d2);
                }
                return this;
            }

            public Hasher putFloat(float f2) {
                for (Hasher putFloat : hasherArr) {
                    putFloat.putFloat(f2);
                }
                return this;
            }

            public Hasher putInt(int i3) {
                for (Hasher putInt : hasherArr) {
                    putInt.putInt(i3);
                }
                return this;
            }

            public Hasher putLong(long j2) {
                for (Hasher putLong : hasherArr) {
                    putLong.putLong(j2);
                }
                return this;
            }

            public Hasher putShort(short s3) {
                for (Hasher putShort : hasherArr) {
                    putShort.putShort(s3);
                }
                return this;
            }

            public Hasher putString(CharSequence charSequence, Charset charset) {
                for (Hasher putString : hasherArr) {
                    putString.putString(charSequence, charset);
                }
                return this;
            }

            public Hasher putUnencodedChars(CharSequence charSequence) {
                for (Hasher putUnencodedChars : hasherArr) {
                    putUnencodedChars.putUnencodedChars(charSequence);
                }
                return this;
            }

            public Hasher putBytes(byte[] bArr) {
                for (Hasher putBytes : hasherArr) {
                    putBytes.putBytes(bArr);
                }
                return this;
            }

            public Hasher putBytes(byte[] bArr, int i3, int i4) {
                for (Hasher putBytes : hasherArr) {
                    putBytes.putBytes(bArr, i3, i4);
                }
                return this;
            }

            public Hasher putBytes(ByteBuffer byteBuffer) {
                int position = byteBuffer.position();
                for (Hasher putBytes : hasherArr) {
                    Java8Compatibility.position(byteBuffer, position);
                    putBytes.putBytes(byteBuffer);
                }
                return this;
            }
        };
    }

    public abstract HashCode makeHash(Hasher[] hasherArr);

    public Hasher newHasher() {
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i3 = 0; i3 < length; i3++) {
            hasherArr[i3] = this.functions[i3].newHasher();
        }
        return fromHashers(hasherArr);
    }

    public Hasher newHasher(int i3) {
        Preconditions.checkArgument(i3 >= 0);
        int length = this.functions.length;
        Hasher[] hasherArr = new Hasher[length];
        for (int i4 = 0; i4 < length; i4++) {
            hasherArr[i4] = this.functions[i4].newHasher(i3);
        }
        return fromHashers(hasherArr);
    }
}
