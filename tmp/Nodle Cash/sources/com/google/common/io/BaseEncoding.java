package com.google.common.io;

import android.support.v4.media.session.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.CheckForNull;
import okio.Utf8;
import org.objectweb.asm.signature.SignatureVisitor;

@GwtCompatible(emulated = true)
@ElementTypesAreNonnullByDefault
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new Base16Encoding("base16()", "0123456789ABCDEF");
    private static final BaseEncoding BASE32;
    private static final BaseEncoding BASE32_HEX;
    private static final BaseEncoding BASE64;
    private static final BaseEncoding BASE64_URL;

    public static final class Alphabet {
        final int bitsPerChar;
        final int bytesPerChunk;
        /* access modifiers changed from: private */
        public final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        private final boolean ignoreCase;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        public Alphabet(String str, char[] cArr) {
            this(str, cArr, decodabetFor(cArr), false);
        }

        private static byte[] decodabetFor(char[] cArr) {
            byte[] bArr = new byte[128];
            Arrays.fill(bArr, (byte) -1);
            for (int i3 = 0; i3 < cArr.length; i3++) {
                char c3 = cArr[i3];
                boolean z2 = true;
                Preconditions.checkArgument(c3 < 128, "Non-ASCII character: %s", c3);
                if (bArr[c3] != -1) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2, "Duplicate character: %s", c3);
                bArr[c3] = (byte) i3;
            }
            return bArr;
        }

        private boolean hasLowerCase() {
            for (char isLowerCase : this.chars) {
                if (Ascii.isLowerCase(isLowerCase)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char isUpperCase : this.chars) {
                if (Ascii.isUpperCase(isUpperCase)) {
                    return true;
                }
            }
            return false;
        }

        public boolean canDecode(char c3) {
            return c3 <= 127 && this.decodabet[c3] != -1;
        }

        public int decode(char c3) throws DecodingException {
            if (c3 <= 127) {
                byte b3 = this.decodabet[c3];
                if (b3 != -1) {
                    return b3;
                }
                if (c3 <= ' ' || c3 == 127) {
                    throw new DecodingException(a.j(c3, new StringBuilder("Unrecognized character: 0x")));
                }
                throw new DecodingException(androidx.compose.animation.core.a.p("Unrecognized character: ", c3));
            }
            throw new DecodingException(a.j(c3, new StringBuilder("Unrecognized character: 0x")));
        }

        public char encode(int i3) {
            return this.chars[i3];
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof Alphabet)) {
                return false;
            }
            Alphabet alphabet = (Alphabet) obj;
            return this.ignoreCase == alphabet.ignoreCase && Arrays.equals(this.chars, alphabet.chars);
        }

        public int hashCode() {
            return Arrays.hashCode(this.chars) + (this.ignoreCase ? 1231 : 1237);
        }

        public Alphabet ignoreCase() {
            if (this.ignoreCase) {
                return this;
            }
            byte[] bArr = this.decodabet;
            byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
            int i3 = 65;
            while (true) {
                boolean z2 = true;
                if (i3 > 90) {
                    return new Alphabet(A.a.n(new StringBuilder(), this.name, ".ignoreCase()"), this.chars, copyOf, true);
                }
                int i4 = i3 | 32;
                byte[] bArr2 = this.decodabet;
                byte b3 = bArr2[i3];
                byte b4 = bArr2[i4];
                if (b3 == -1) {
                    copyOf[i3] = b4;
                } else {
                    if (b4 != -1) {
                        z2 = false;
                    }
                    Preconditions.checkState(z2, "Can't ignoreCase() since '%s' and '%s' encode different values", (char) i3, (char) i4);
                    copyOf[i4] = b3;
                }
                i3++;
            }
        }

        public boolean isValidPaddingStartPosition(int i3) {
            return this.validPadding[i3 % this.charsPerChunk];
        }

        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i3 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i3 >= cArr2.length) {
                    break;
                }
                cArr[i3] = Ascii.toLowerCase(cArr2[i3]);
                i3++;
            }
            Alphabet alphabet = new Alphabet(A.a.n(new StringBuilder(), this.name, ".lowerCase()"), cArr);
            return this.ignoreCase ? alphabet.ignoreCase() : alphabet;
        }

        public boolean matches(char c3) {
            byte[] bArr = this.decodabet;
            return c3 < bArr.length && bArr[c3] != -1;
        }

        public String toString() {
            return this.name;
        }

        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.chars.length];
            int i3 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (i3 >= cArr2.length) {
                    break;
                }
                cArr[i3] = Ascii.toUpperCase(cArr2[i3]);
                i3++;
            }
            Alphabet alphabet = new Alphabet(A.a.n(new StringBuilder(), this.name, ".upperCase()"), cArr);
            return this.ignoreCase ? alphabet.ignoreCase() : alphabet;
        }

        private Alphabet(String str, char[] cArr, byte[] bArr, boolean z2) {
            this.name = (String) Preconditions.checkNotNull(str);
            this.chars = (char[]) Preconditions.checkNotNull(cArr);
            try {
                int log2 = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                this.bitsPerChar = log2;
                int numberOfTrailingZeros = Integer.numberOfTrailingZeros(log2);
                int i3 = 1 << (3 - numberOfTrailingZeros);
                this.charsPerChunk = i3;
                this.bytesPerChunk = log2 >> numberOfTrailingZeros;
                this.mask = cArr.length - 1;
                this.decodabet = bArr;
                boolean[] zArr = new boolean[i3];
                for (int i4 = 0; i4 < this.bytesPerChunk; i4++) {
                    zArr[IntMath.divide(i4 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                }
                this.validPadding = zArr;
                this.ignoreCase = z2;
            } catch (ArithmeticException e3) {
                throw new IllegalArgumentException("Illegal alphabet length " + cArr.length, e3);
            }
        }
    }

    public static final class Base16Encoding extends StandardBaseEncoding {
        final char[] encoding;

        public Base16Encoding(String str, String str2) {
            this(new Alphabet(str, str2.toCharArray()));
        }

        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 != 1) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < charSequence.length()) {
                    bArr[i4] = (byte) ((this.alphabet.decode(charSequence.charAt(i3)) << 4) | this.alphabet.decode(charSequence.charAt(i3 + 1)));
                    i3 += 2;
                    i4++;
                }
                return i4;
            }
            throw new DecodingException("Invalid input length " + charSequence.length());
        }

        public void encodeTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
            for (int i5 = 0; i5 < i4; i5++) {
                byte b3 = bArr[i3 + i5] & 255;
                appendable.append(this.encoding[b3]);
                appendable.append(this.encoding[b3 | 256]);
            }
        }

        public BaseEncoding newInstance(Alphabet alphabet, @CheckForNull Character ch2) {
            return new Base16Encoding(alphabet);
        }

        private Base16Encoding(Alphabet alphabet) {
            super(alphabet, (Character) null);
            this.encoding = new char[512];
            Preconditions.checkArgument(alphabet.chars.length == 16);
            for (int i3 = 0; i3 < 256; i3++) {
                this.encoding[i3] = alphabet.encode(i3 >>> 4);
                this.encoding[i3 | 256] = alphabet.encode(i3 & 15);
            }
        }
    }

    public static final class Base64Encoding extends StandardBaseEncoding {
        public Base64Encoding(String str, String str2, @CheckForNull Character ch2) {
            this(new Alphabet(str, str2.toCharArray()), ch2);
        }

        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < trimTrailingPadding.length()) {
                    int i5 = i3 + 2;
                    int decode = (this.alphabet.decode(trimTrailingPadding.charAt(i3)) << 18) | (this.alphabet.decode(trimTrailingPadding.charAt(i3 + 1)) << 12);
                    int i6 = i4 + 1;
                    bArr[i4] = (byte) (decode >>> 16);
                    if (i5 < trimTrailingPadding.length()) {
                        int i7 = i3 + 3;
                        int decode2 = decode | (this.alphabet.decode(trimTrailingPadding.charAt(i5)) << 6);
                        int i8 = i4 + 2;
                        bArr[i6] = (byte) ((decode2 >>> 8) & 255);
                        if (i7 < trimTrailingPadding.length()) {
                            i3 += 4;
                            i4 += 3;
                            bArr[i8] = (byte) ((decode2 | this.alphabet.decode(trimTrailingPadding.charAt(i7))) & 255);
                        } else {
                            i4 = i8;
                            i3 = i7;
                        }
                    } else {
                        i4 = i6;
                        i3 = i5;
                    }
                }
                return i4;
            }
            throw new DecodingException("Invalid input length " + trimTrailingPadding.length());
        }

        public void encodeTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
            Preconditions.checkNotNull(appendable);
            int i5 = i3 + i4;
            Preconditions.checkPositionIndexes(i3, i5, bArr.length);
            while (i4 >= 3) {
                int i6 = i3 + 2;
                int i7 = (bArr[i3 + 1] & 255) << 8;
                i3 += 3;
                byte b3 = i7 | ((bArr[i3] & 255) << 16) | (bArr[i6] & 255);
                appendable.append(this.alphabet.encode(b3 >>> Ascii.DC2));
                appendable.append(this.alphabet.encode((b3 >>> Ascii.FF) & 63));
                appendable.append(this.alphabet.encode((b3 >>> 6) & 63));
                appendable.append(this.alphabet.encode(b3 & Utf8.REPLACEMENT_BYTE));
                i4 -= 3;
            }
            if (i3 < i5) {
                encodeChunkTo(appendable, bArr, i3, i5 - i3);
            }
        }

        public BaseEncoding newInstance(Alphabet alphabet, @CheckForNull Character ch2) {
            return new Base64Encoding(alphabet, ch2);
        }

        private Base64Encoding(Alphabet alphabet, @CheckForNull Character ch2) {
            super(alphabet, ch2);
            Preconditions.checkArgument(alphabet.chars.length == 64);
        }
    }

    public static final class DecodingException extends IOException {
        public DecodingException(String str) {
            super(str);
        }

        public DecodingException(Throwable th) {
            super(th);
        }
    }

    public static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;

        public SeparatedBaseEncoding(BaseEncoding baseEncoding, String str, int i3) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.separator = (String) Preconditions.checkNotNull(str);
            this.afterEveryChars = i3;
            Preconditions.checkArgument(i3 > 0, "Cannot add a separator after every %s chars", i3);
        }

        public boolean canDecode(CharSequence charSequence) {
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < charSequence.length(); i3++) {
                char charAt = charSequence.charAt(i3);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.canDecode(sb);
        }

        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            StringBuilder sb = new StringBuilder(charSequence.length());
            for (int i3 = 0; i3 < charSequence.length(); i3++) {
                char charAt = charSequence.charAt(i3);
                if (this.separator.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.delegate.decodeTo(bArr, sb);
        }

        @GwtIncompatible
        @J2ktIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.delegate.decodingStream(BaseEncoding.ignoringReader(reader, this.separator));
        }

        public void encodeTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
            this.delegate.encodeTo(BaseEncoding.separatingAppendable(appendable, this.separator, this.afterEveryChars), bArr, i3, i4);
        }

        @GwtIncompatible
        @J2ktIncompatible
        public OutputStream encodingStream(Writer writer) {
            return this.delegate.encodingStream(BaseEncoding.separatingWriter(writer, this.separator, this.afterEveryChars));
        }

        public BaseEncoding ignoreCase() {
            return this.delegate.ignoreCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public int maxDecodedSize(int i3) {
            return this.delegate.maxDecodedSize(i3);
        }

        public int maxEncodedSize(int i3) {
            int maxEncodedSize = this.delegate.maxEncodedSize(i3);
            return (IntMath.divide(Math.max(0, maxEncodedSize - 1), this.afterEveryChars, RoundingMode.FLOOR) * this.separator.length()) + maxEncodedSize;
        }

        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.delegate);
            sb.append(".withSeparator(\"");
            sb.append(this.separator);
            sb.append("\", ");
            return A.a.m(sb, ")", this.afterEveryChars);
        }

        public CharSequence trimTrailingPadding(CharSequence charSequence) {
            return this.delegate.trimTrailingPadding(charSequence);
        }

        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withPadChar(char c3) {
            return this.delegate.withPadChar(c3).withSeparator(this.separator, this.afterEveryChars);
        }

        public BaseEncoding withSeparator(String str, int i3) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    public static class StandardBaseEncoding extends BaseEncoding {
        final Alphabet alphabet;
        @CheckForNull
        @LazyInit
        private volatile BaseEncoding ignoreCase;
        @CheckForNull
        @LazyInit
        private volatile BaseEncoding lowerCase;
        @CheckForNull
        final Character paddingChar;
        @CheckForNull
        @LazyInit
        private volatile BaseEncoding upperCase;

        public StandardBaseEncoding(String str, String str2, @CheckForNull Character ch2) {
            this(new Alphabet(str, str2.toCharArray()), ch2);
        }

        public boolean canDecode(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (!this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                return false;
            }
            for (int i3 = 0; i3 < trimTrailingPadding.length(); i3++) {
                if (!this.alphabet.canDecode(trimTrailingPadding.charAt(i3))) {
                    return false;
                }
            }
            return true;
        }

        public int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Alphabet alphabet2;
            Preconditions.checkNotNull(bArr);
            CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
            if (this.alphabet.isValidPaddingStartPosition(trimTrailingPadding.length())) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < trimTrailingPadding.length()) {
                    long j2 = 0;
                    int i5 = 0;
                    int i6 = 0;
                    while (true) {
                        alphabet2 = this.alphabet;
                        if (i5 >= alphabet2.charsPerChunk) {
                            break;
                        }
                        j2 <<= alphabet2.bitsPerChar;
                        if (i3 + i5 < trimTrailingPadding.length()) {
                            j2 |= (long) this.alphabet.decode(trimTrailingPadding.charAt(i6 + i3));
                            i6++;
                        }
                        i5++;
                    }
                    int i7 = alphabet2.bytesPerChunk;
                    int i8 = (i7 * 8) - (i6 * alphabet2.bitsPerChar);
                    int i9 = (i7 - 1) * 8;
                    while (i9 >= i8) {
                        bArr[i4] = (byte) ((int) ((j2 >>> i9) & 255));
                        i9 -= 8;
                        i4++;
                    }
                    i3 += this.alphabet.charsPerChunk;
                }
                return i4;
            }
            throw new DecodingException("Invalid input length " + trimTrailingPadding.length());
        }

        @GwtIncompatible
        @J2ktIncompatible
        public InputStream decodingStream(final Reader reader) {
            Preconditions.checkNotNull(reader);
            return new InputStream() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                boolean hitPadding = false;
                int readChars = 0;

                public void close() throws IOException {
                    reader.close();
                }

                public int read() throws IOException {
                    int i3;
                    while (true) {
                        int read = reader.read();
                        if (read != -1) {
                            this.readChars++;
                            char c3 = (char) read;
                            Character ch2 = StandardBaseEncoding.this.paddingChar;
                            if (ch2 == null || ch2.charValue() != c3) {
                                if (!this.hitPadding) {
                                    int i4 = this.bitBuffer;
                                    Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                                    int i5 = i4 << alphabet.bitsPerChar;
                                    this.bitBuffer = i5;
                                    int decode = alphabet.decode(c3) | i5;
                                    this.bitBuffer = decode;
                                    int i6 = this.bitBufferLength + StandardBaseEncoding.this.alphabet.bitsPerChar;
                                    this.bitBufferLength = i6;
                                    if (i6 >= 8) {
                                        int i7 = i6 - 8;
                                        this.bitBufferLength = i7;
                                        return (decode >> i7) & 255;
                                    }
                                } else {
                                    throw new DecodingException("Expected padding character but found '" + c3 + "' at index " + this.readChars);
                                }
                            } else if (this.hitPadding || ((i3 = this.readChars) != 1 && StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(i3 - 1))) {
                                this.hitPadding = true;
                            }
                        } else if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                            return -1;
                        } else {
                            throw new DecodingException("Invalid input length " + this.readChars);
                        }
                    }
                    throw new DecodingException("Padding cannot start at index " + this.readChars);
                }

                public int read(byte[] bArr, int i3, int i4) throws IOException {
                    int i5 = i4 + i3;
                    Preconditions.checkPositionIndexes(i3, i5, bArr.length);
                    int i6 = i3;
                    while (i6 < i5) {
                        int read = read();
                        if (read == -1) {
                            int i7 = i6 - i3;
                            if (i7 == 0) {
                                return -1;
                            }
                            return i7;
                        }
                        bArr[i6] = (byte) read;
                        i6++;
                    }
                    return i6 - i3;
                }
            };
        }

        public void encodeChunkTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
            int i5 = 0;
            Preconditions.checkArgument(i4 <= this.alphabet.bytesPerChunk);
            long j2 = 0;
            for (int i6 = 0; i6 < i4; i6++) {
                j2 = (j2 | ((long) (bArr[i3 + i6] & 255))) << 8;
            }
            int i7 = ((i4 + 1) * 8) - this.alphabet.bitsPerChar;
            while (i5 < i4 * 8) {
                Alphabet alphabet2 = this.alphabet;
                appendable.append(alphabet2.encode(((int) (j2 >>> (i7 - i5))) & alphabet2.mask));
                i5 += this.alphabet.bitsPerChar;
            }
            if (this.paddingChar != null) {
                while (i5 < this.alphabet.bytesPerChunk * 8) {
                    appendable.append(this.paddingChar.charValue());
                    i5 += this.alphabet.bitsPerChar;
                }
            }
        }

        public void encodeTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
            int i5 = 0;
            while (i5 < i4) {
                encodeChunkTo(appendable, bArr, i3 + i5, Math.min(this.alphabet.bytesPerChunk, i4 - i5));
                i5 += this.alphabet.bytesPerChunk;
            }
        }

        @GwtIncompatible
        @J2ktIncompatible
        public OutputStream encodingStream(final Writer writer) {
            Preconditions.checkNotNull(writer);
            return new OutputStream() {
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                public void close() throws IOException {
                    int i3 = this.bitBufferLength;
                    if (i3 > 0) {
                        int i4 = this.bitBuffer;
                        Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                        writer.write(alphabet.encode((i4 << (alphabet.bitsPerChar - i3)) & alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (true) {
                                int i5 = this.writtenChars;
                                StandardBaseEncoding standardBaseEncoding = StandardBaseEncoding.this;
                                if (i5 % standardBaseEncoding.alphabet.charsPerChunk == 0) {
                                    break;
                                }
                                writer.write(standardBaseEncoding.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    writer.close();
                }

                public void flush() throws IOException {
                    writer.flush();
                }

                public void write(int i3) throws IOException {
                    this.bitBuffer = (i3 & 255) | (this.bitBuffer << 8);
                    this.bitBufferLength += 8;
                    while (true) {
                        int i4 = this.bitBufferLength;
                        Alphabet alphabet = StandardBaseEncoding.this.alphabet;
                        int i5 = alphabet.bitsPerChar;
                        if (i4 >= i5) {
                            writer.write(alphabet.encode((this.bitBuffer >> (i4 - i5)) & alphabet.mask));
                            this.writtenChars++;
                            this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                        } else {
                            return;
                        }
                    }
                }
            };
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof StandardBaseEncoding)) {
                return false;
            }
            StandardBaseEncoding standardBaseEncoding = (StandardBaseEncoding) obj;
            return this.alphabet.equals(standardBaseEncoding.alphabet) && Objects.equals(this.paddingChar, standardBaseEncoding.paddingChar);
        }

        public int hashCode() {
            return Objects.hashCode(this.paddingChar) ^ this.alphabet.hashCode();
        }

        public BaseEncoding ignoreCase() {
            BaseEncoding baseEncoding = this.ignoreCase;
            if (baseEncoding == null) {
                Alphabet ignoreCase2 = this.alphabet.ignoreCase();
                baseEncoding = ignoreCase2 == this.alphabet ? this : newInstance(ignoreCase2, this.paddingChar);
                this.ignoreCase = baseEncoding;
            }
            return baseEncoding;
        }

        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.lowerCase;
            if (baseEncoding == null) {
                Alphabet lowerCase2 = this.alphabet.lowerCase();
                baseEncoding = lowerCase2 == this.alphabet ? this : newInstance(lowerCase2, this.paddingChar);
                this.lowerCase = baseEncoding;
            }
            return baseEncoding;
        }

        public int maxDecodedSize(int i3) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) i3)) + 7) / 8);
        }

        public int maxEncodedSize(int i3) {
            Alphabet alphabet2 = this.alphabet;
            return IntMath.divide(i3, alphabet2.bytesPerChunk, RoundingMode.CEILING) * alphabet2.charsPerChunk;
        }

        public BaseEncoding newInstance(Alphabet alphabet2, @CheckForNull Character ch2) {
            return new StandardBaseEncoding(alphabet2, ch2);
        }

        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : newInstance(this.alphabet, (Character) null);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.alphabet);
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.paddingChar);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        public CharSequence trimTrailingPadding(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            Character ch2 = this.paddingChar;
            if (ch2 == null) {
                return charSequence;
            }
            char charValue = ch2.charValue();
            int length = charSequence.length() - 1;
            while (length >= 0 && charSequence.charAt(length) == charValue) {
                length--;
            }
            return charSequence.subSequence(0, length + 1);
        }

        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.upperCase;
            if (baseEncoding == null) {
                Alphabet upperCase2 = this.alphabet.upperCase();
                baseEncoding = upperCase2 == this.alphabet ? this : newInstance(upperCase2, this.paddingChar);
                this.upperCase = baseEncoding;
            }
            return baseEncoding;
        }

        public BaseEncoding withPadChar(char c3) {
            if (8 % this.alphabet.bitsPerChar == 0) {
                return this;
            }
            Character ch2 = this.paddingChar;
            return (ch2 == null || ch2.charValue() != c3) ? newInstance(this.alphabet, Character.valueOf(c3)) : this;
        }

        public BaseEncoding withSeparator(String str, int i3) {
            boolean z2 = false;
            for (int i4 = 0; i4 < str.length(); i4++) {
                Preconditions.checkArgument(!this.alphabet.matches(str.charAt(i4)), "Separator (%s) cannot contain alphabet characters", (Object) str);
            }
            Character ch2 = this.paddingChar;
            if (ch2 != null) {
                if (str.indexOf(ch2.charValue()) < 0) {
                    z2 = true;
                }
                Preconditions.checkArgument(z2, "Separator (%s) cannot contain padding character", (Object) str);
            }
            return new SeparatedBaseEncoding(this, str, i3);
        }

        public StandardBaseEncoding(Alphabet alphabet2, @CheckForNull Character ch2) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet2);
            Preconditions.checkArgument(ch2 == null || !alphabet2.matches(ch2.charValue()), "Padding character %s was already in alphabet", (Object) ch2);
            this.paddingChar = ch2;
        }
    }

    static {
        Character valueOf = Character.valueOf(SignatureVisitor.INSTANCEOF);
        BASE64 = new Base64Encoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", valueOf);
        BASE64_URL = new Base64Encoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", valueOf);
        BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", valueOf);
        BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", valueOf);
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    private static byte[] extract(byte[] bArr, int i3) {
        if (i3 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, 0, bArr2, 0, i3);
        return bArr2;
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Reader ignoringReader(final Reader reader, final String str) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(str);
        return new Reader() {
            public void close() throws IOException {
                reader.close();
            }

            /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: 
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                */
            public int read() throws java.io.IOException {
                /*
                    r3 = this;
                L_0x0000:
                    java.io.Reader r0 = r1
                    int r0 = r0.read()
                    r1 = -1
                    if (r0 == r1) goto L_0x0012
                    java.lang.String r1 = r2
                    char r2 = (char) r0
                    int r1 = r1.indexOf(r2)
                    if (r1 >= 0) goto L_0x0000
                L_0x0012:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.AnonymousClass3.read():int");
            }

            public int read(char[] cArr, int i3, int i4) throws IOException {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static Appendable separatingAppendable(Appendable appendable, String str, int i3) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i3 > 0);
        return new Appendable(i3, appendable, str) {
            int charsUntilSeparator;
            final /* synthetic */ int val$afterEveryChars;
            final /* synthetic */ Appendable val$delegate;
            final /* synthetic */ String val$separator;

            {
                this.val$afterEveryChars = r1;
                this.val$delegate = r2;
                this.val$separator = r3;
                this.charsUntilSeparator = r1;
            }

            public Appendable append(char c3) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    this.val$delegate.append(this.val$separator);
                    this.charsUntilSeparator = this.val$afterEveryChars;
                }
                this.val$delegate.append(c3);
                this.charsUntilSeparator--;
                return this;
            }

            public Appendable append(@CheckForNull CharSequence charSequence, int i3, int i4) {
                throw new UnsupportedOperationException();
            }

            public Appendable append(@CheckForNull CharSequence charSequence) {
                throw new UnsupportedOperationException();
            }
        };
    }

    @GwtIncompatible
    @J2ktIncompatible
    public static Writer separatingWriter(final Writer writer, String str, int i3) {
        final Appendable separatingAppendable = separatingAppendable(writer, str, i3);
        return new Writer() {
            public void close() throws IOException {
                writer.close();
            }

            public void flush() throws IOException {
                writer.flush();
            }

            public void write(int i3) throws IOException {
                separatingAppendable.append((char) i3);
            }

            public void write(char[] cArr, int i3, int i4) throws IOException {
                throw new UnsupportedOperationException();
            }
        };
    }

    public abstract boolean canDecode(CharSequence charSequence);

    public final byte[] decode(CharSequence charSequence) {
        try {
            return decodeChecked(charSequence);
        } catch (DecodingException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public final byte[] decodeChecked(CharSequence charSequence) throws DecodingException {
        CharSequence trimTrailingPadding = trimTrailingPadding(charSequence);
        byte[] bArr = new byte[maxDecodedSize(trimTrailingPadding.length())];
        return extract(bArr, decodeTo(bArr, trimTrailingPadding));
    }

    public abstract int decodeTo(byte[] bArr, CharSequence charSequence) throws DecodingException;

    @GwtIncompatible
    @J2ktIncompatible
    public final ByteSource decodingSource(final CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new ByteSource() {
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(charSource.openStream());
            }
        };
    }

    @GwtIncompatible
    @J2ktIncompatible
    public abstract InputStream decodingStream(Reader reader);

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public abstract void encodeTo(Appendable appendable, byte[] bArr, int i3, int i4) throws IOException;

    @GwtIncompatible
    @J2ktIncompatible
    public final ByteSink encodingSink(final CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new ByteSink() {
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(charSink.openStream());
            }
        };
    }

    @GwtIncompatible
    @J2ktIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract BaseEncoding ignoreCase();

    public abstract BaseEncoding lowerCase();

    public abstract int maxDecodedSize(int i3);

    public abstract int maxEncodedSize(int i3);

    public abstract BaseEncoding omitPadding();

    public CharSequence trimTrailingPadding(CharSequence charSequence) {
        return (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c3);

    public abstract BaseEncoding withSeparator(String str, int i3);

    public final String encode(byte[] bArr, int i3, int i4) {
        Preconditions.checkPositionIndexes(i3, i3 + i4, bArr.length);
        StringBuilder sb = new StringBuilder(maxEncodedSize(i4));
        try {
            encodeTo(sb, bArr, i3, i4);
            return sb.toString();
        } catch (IOException e3) {
            throw new AssertionError(e3);
        }
    }
}
