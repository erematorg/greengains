package com.google.crypto.tink.subtle;

import android.support.v4.media.session.a;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import io.zksync.transaction.type.Transaction712;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i3);

        public abstract boolean process(byte[] bArr, int i3, int i4, boolean z2);
    }

    public static class Decoder extends Coder {
        private static final int[] DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        public Decoder(int i3, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i3 & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int i3) {
            return b.b(i3, 3, 4, 10);
        }

        /* JADX WARNING: Removed duplicated region for block: B:73:0x00eb A[EDGE_INSN: B:73:0x00eb->B:54:0x00eb ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r17, int r18, int r19, boolean r20) {
            /*
                r16 = this;
                r0 = r16
                int r1 = r0.state
                r2 = 0
                r3 = 6
                if (r1 != r3) goto L_0x0009
                return r2
            L_0x0009:
                int r4 = r19 + r18
                int r5 = r0.value
                byte[] r6 = r0.output
                int[] r7 = r0.alphabet
                r9 = r2
                r8 = r5
                r5 = r1
                r1 = r18
            L_0x0016:
                r10 = 3
                r11 = 4
                r12 = 2
                r13 = 1
                if (r1 >= r4) goto L_0x00eb
                if (r5 != 0) goto L_0x0063
            L_0x001e:
                int r14 = r1 + 4
                if (r14 > r4) goto L_0x005f
                byte r8 = r17[r1]
                r8 = r8 & 255(0xff, float:3.57E-43)
                r8 = r7[r8]
                int r8 = r8 << 18
                int r15 = r1 + 1
                byte r15 = r17[r15]
                r15 = r15 & 255(0xff, float:3.57E-43)
                r15 = r7[r15]
                int r15 = r15 << 12
                r8 = r8 | r15
                int r15 = r1 + 2
                byte r15 = r17[r15]
                r15 = r15 & 255(0xff, float:3.57E-43)
                r15 = r7[r15]
                int r15 = r15 << r3
                r8 = r8 | r15
                int r15 = r1 + 3
                byte r15 = r17[r15]
                r15 = r15 & 255(0xff, float:3.57E-43)
                r15 = r7[r15]
                r8 = r8 | r15
                if (r8 < 0) goto L_0x005f
                int r1 = r9 + 2
                byte r15 = (byte) r8
                r6[r1] = r15
                int r1 = r9 + 1
                int r15 = r8 >> 8
                byte r15 = (byte) r15
                r6[r1] = r15
                int r1 = r8 >> 16
                byte r1 = (byte) r1
                r6[r9] = r1
                int r9 = r9 + 3
                r1 = r14
                goto L_0x001e
            L_0x005f:
                if (r1 < r4) goto L_0x0063
                goto L_0x00eb
            L_0x0063:
                int r14 = r1 + 1
                byte r1 = r17[r1]
                r1 = r1 & 255(0xff, float:3.57E-43)
                r1 = r7[r1]
                r15 = -1
                if (r5 == 0) goto L_0x00e0
                if (r5 == r13) goto L_0x00d8
                r13 = -2
                if (r5 == r12) goto L_0x00be
                r12 = 5
                if (r5 == r10) goto L_0x008c
                if (r5 == r11) goto L_0x0081
                if (r5 == r12) goto L_0x007c
                goto L_0x00e8
            L_0x007c:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x0081:
                if (r1 != r13) goto L_0x0087
                int r5 = r5 + 1
                goto L_0x00e8
            L_0x0087:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x008c:
                if (r1 < 0) goto L_0x00a7
                int r5 = r8 << 6
                r1 = r1 | r5
                int r5 = r9 + 2
                byte r8 = (byte) r1
                r6[r5] = r8
                int r5 = r9 + 1
                int r8 = r1 >> 8
                byte r8 = (byte) r8
                r6[r5] = r8
                int r5 = r1 >> 16
                byte r5 = (byte) r5
                r6[r9] = r5
                int r9 = r9 + 3
                r8 = r1
                r5 = r2
                goto L_0x00e8
            L_0x00a7:
                if (r1 != r13) goto L_0x00b9
                int r1 = r9 + 1
                int r5 = r8 >> 2
                byte r5 = (byte) r5
                r6[r1] = r5
                int r1 = r8 >> 10
                byte r1 = (byte) r1
                r6[r9] = r1
                int r9 = r9 + 2
                r5 = r12
                goto L_0x00e8
            L_0x00b9:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x00be:
                if (r1 < 0) goto L_0x00c7
            L_0x00c0:
                int r8 = r8 << 6
                r1 = r1 | r8
            L_0x00c3:
                int r5 = r5 + 1
                r8 = r1
                goto L_0x00e8
            L_0x00c7:
                if (r1 != r13) goto L_0x00d3
                int r1 = r9 + 1
                int r5 = r8 >> 4
                byte r5 = (byte) r5
                r6[r9] = r5
                r9 = r1
                r5 = r11
                goto L_0x00e8
            L_0x00d3:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x00d8:
                if (r1 < 0) goto L_0x00db
                goto L_0x00c0
            L_0x00db:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x00e0:
                if (r1 < 0) goto L_0x00e3
                goto L_0x00c3
            L_0x00e3:
                if (r1 == r15) goto L_0x00e8
                r0.state = r3
                return r2
            L_0x00e8:
                r1 = r14
                goto L_0x0016
            L_0x00eb:
                if (r20 != 0) goto L_0x00f4
                r0.state = r5
                r0.value = r8
                r0.op = r9
                return r13
            L_0x00f4:
                if (r5 == r13) goto L_0x011c
                if (r5 == r12) goto L_0x010f
                if (r5 == r10) goto L_0x0100
                if (r5 == r11) goto L_0x00fd
                goto L_0x0117
            L_0x00fd:
                r0.state = r3
                return r2
            L_0x0100:
                int r1 = r9 + 1
                int r2 = r8 >> 10
                byte r2 = (byte) r2
                r6[r9] = r2
                int r9 = r9 + 2
                int r2 = r8 >> 2
                byte r2 = (byte) r2
                r6[r1] = r2
                goto L_0x0117
            L_0x010f:
                int r1 = r9 + 1
                int r2 = r8 >> 4
                byte r2 = (byte) r2
                r6[r9] = r2
                r9 = r1
            L_0x0117:
                r0.state = r5
                r0.op = r9
                return r13
            L_0x011c:
                r0.state = r3
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    public static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, Transaction712.EIP_712_TX_TYPE, 114, 115, 116, 117, 118, 119, CBORConstants.BYTE_STRING_1BYTE_LEN, CBORConstants.BYTE_STRING_2BYTE_LEN, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, Transaction712.EIP_712_TX_TYPE, 114, 115, 116, 117, 118, 119, CBORConstants.BYTE_STRING_1BYTE_LEN, CBORConstants.BYTE_STRING_2BYTE_LEN, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean doCr;
        public final boolean doNewline;
        public final boolean doPadding;
        private final byte[] tail;
        int tailLen;

        public Encoder(int i3, byte[] bArr) {
            this.output = bArr;
            boolean z2 = true;
            this.doPadding = (i3 & 1) == 0;
            boolean z3 = (i3 & 2) == 0;
            this.doNewline = z3;
            this.doCr = (i3 & 4) == 0 ? false : z2;
            this.alphabet = (i3 & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = z3 ? 19 : -1;
        }

        public int maxOutputSize(int i3) {
            return b.b(i3, 8, 5, 10);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0094  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x00e8  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x01b4  */
        /* JADX WARNING: Removed duplicated region for block: B:85:0x00e6 A[SYNTHETIC] */
        @com.google.errorprone.annotations.CanIgnoreReturnValue
        public boolean process(byte[] r18, int r19, int r20, boolean r21) {
            /*
                r17 = this;
                r0 = r17
                byte[] r1 = r0.alphabet
                byte[] r2 = r0.output
                int r3 = r0.count
                int r4 = r20 + r19
                int r5 = r0.tailLen
                r6 = 2
                r7 = 1
                r8 = 0
                r9 = -1
                if (r5 == r7) goto L_0x0031
                if (r5 == r6) goto L_0x0015
                goto L_0x0050
            L_0x0015:
                int r5 = r19 + 1
                if (r5 > r4) goto L_0x0050
                byte[] r10 = r0.tail
                byte r11 = r10[r8]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                byte r10 = r10[r7]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r10 = r10 | r11
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r10 = r10 | r11
                r0.tailLen = r8
                r11 = r5
                goto L_0x0053
            L_0x0031:
                int r5 = r19 + 2
                if (r5 > r4) goto L_0x0050
                byte[] r5 = r0.tail
                byte r5 = r5[r8]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 16
                int r10 = r19 + 1
                byte r11 = r18[r19]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 8
                r5 = r5 | r11
                int r11 = r19 + 2
                byte r10 = r18[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r10 = r10 | r5
                r0.tailLen = r8
                goto L_0x0053
            L_0x0050:
                r11 = r19
                r10 = r9
            L_0x0053:
                r12 = 13
                r13 = 4
                r14 = 10
                if (r10 == r9) goto L_0x008f
                int r9 = r10 >> 18
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r8] = r9
                int r9 = r10 >> 12
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r7] = r9
                int r9 = r10 >> 6
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r6] = r9
                r9 = r10 & 63
                byte r9 = r1[r9]
                r10 = 3
                r2[r10] = r9
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x008d
                boolean r3 = r0.doCr
                if (r3 == 0) goto L_0x0085
                r2[r13] = r12
                r3 = 5
                goto L_0x0086
            L_0x0085:
                r3 = r13
            L_0x0086:
                int r9 = r3 + 1
                r2[r3] = r14
            L_0x008a:
                r3 = 19
                goto L_0x0090
            L_0x008d:
                r9 = r13
                goto L_0x0090
            L_0x008f:
                r9 = r8
            L_0x0090:
                int r10 = r11 + 3
                if (r10 > r4) goto L_0x00e6
                byte r15 = r18[r11]
                r15 = r15 & 255(0xff, float:3.57E-43)
                int r15 = r15 << 16
                int r16 = r11 + 1
                byte r5 = r18[r16]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 8
                r5 = r5 | r15
                int r11 = r11 + 2
                byte r11 = r18[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r5 = r5 | r11
                int r11 = r5 >> 18
                r11 = r11 & 63
                byte r11 = r1[r11]
                r2[r9] = r11
                int r11 = r9 + 1
                int r15 = r5 >> 12
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r9 + 2
                int r15 = r5 >> 6
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r11] = r15
                int r11 = r9 + 3
                r5 = r5 & 63
                byte r5 = r1[r5]
                r2[r11] = r5
                int r5 = r9 + 4
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x00e3
                boolean r3 = r0.doCr
                if (r3 == 0) goto L_0x00dd
                int r9 = r9 + 5
                r2[r5] = r12
                r5 = r9
            L_0x00dd:
                int r9 = r5 + 1
                r2[r5] = r14
                r11 = r10
                goto L_0x008a
            L_0x00e3:
                r9 = r5
                r11 = r10
                goto L_0x0090
            L_0x00e6:
                if (r21 == 0) goto L_0x01b4
                int r5 = r0.tailLen
                int r10 = r11 - r5
                int r15 = r4 + -1
                r16 = 61
                if (r10 != r15) goto L_0x0137
                if (r5 <= 0) goto L_0x00fa
                byte[] r4 = r0.tail
                byte r4 = r4[r8]
                r8 = r7
                goto L_0x00fc
            L_0x00fa:
                byte r4 = r18[r11]
            L_0x00fc:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r13
                int r5 = r5 - r8
                r0.tailLen = r5
                int r5 = r9 + 1
                int r6 = r4 >> 6
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r9] = r6
                int r6 = r9 + 2
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r5] = r1
                boolean r1 = r0.doPadding
                if (r1 == 0) goto L_0x0120
                int r1 = r9 + 3
                r2[r6] = r16
                int r6 = r9 + 4
                r2[r1] = r16
            L_0x0120:
                boolean r1 = r0.doNewline
                if (r1 == 0) goto L_0x0134
                boolean r1 = r0.doCr
                if (r1 == 0) goto L_0x012d
                int r1 = r6 + 1
                r2[r6] = r12
                r6 = r1
            L_0x012d:
                int r1 = r6 + 1
                r2[r6] = r14
            L_0x0131:
                r9 = r1
                goto L_0x01dc
            L_0x0134:
                r9 = r6
                goto L_0x01dc
            L_0x0137:
                int r10 = r11 - r5
                int r4 = r4 - r6
                if (r10 != r4) goto L_0x019b
                if (r5 <= r7) goto L_0x0144
                byte[] r4 = r0.tail
                byte r4 = r4[r8]
                r8 = r7
                goto L_0x014a
            L_0x0144:
                int r4 = r11 + 1
                byte r10 = r18[r11]
                r11 = r4
                r4 = r10
            L_0x014a:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r14
                if (r5 <= 0) goto L_0x0156
                byte[] r10 = r0.tail
                int r11 = r8 + 1
                byte r8 = r10[r8]
                goto L_0x015a
            L_0x0156:
                byte r10 = r18[r11]
                r11 = r8
                r8 = r10
            L_0x015a:
                r8 = r8 & 255(0xff, float:3.57E-43)
                int r6 = r8 << 2
                r4 = r4 | r6
                int r5 = r5 - r11
                r0.tailLen = r5
                int r5 = r9 + 1
                int r6 = r4 >> 12
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r9] = r6
                int r6 = r9 + 2
                int r8 = r4 >> 6
                r8 = r8 & 63
                byte r8 = r1[r8]
                r2[r5] = r8
                int r5 = r9 + 3
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r6] = r1
                boolean r1 = r0.doPadding
                if (r1 == 0) goto L_0x0187
                int r9 = r9 + 4
                r2[r5] = r16
                r5 = r9
            L_0x0187:
                boolean r1 = r0.doNewline
                if (r1 == 0) goto L_0x0199
                boolean r1 = r0.doCr
                if (r1 == 0) goto L_0x0194
                int r1 = r5 + 1
                r2[r5] = r12
                r5 = r1
            L_0x0194:
                int r1 = r5 + 1
                r2[r5] = r14
                goto L_0x0131
            L_0x0199:
                r9 = r5
                goto L_0x01dc
            L_0x019b:
                boolean r1 = r0.doNewline
                if (r1 == 0) goto L_0x01dc
                if (r9 <= 0) goto L_0x01dc
                r1 = 19
                if (r3 == r1) goto L_0x01dc
                boolean r1 = r0.doCr
                if (r1 == 0) goto L_0x01ae
                int r1 = r9 + 1
                r2[r9] = r12
                r9 = r1
            L_0x01ae:
                int r1 = r9 + 1
                r2[r9] = r14
                goto L_0x0131
            L_0x01b4:
                int r1 = r4 + -1
                if (r11 != r1) goto L_0x01c5
                byte[] r1 = r0.tail
                int r2 = r0.tailLen
                int r4 = r2 + 1
                r0.tailLen = r4
                byte r4 = r18[r11]
                r1[r2] = r4
                goto L_0x01dc
            L_0x01c5:
                int r4 = r4 - r6
                if (r11 != r4) goto L_0x01dc
                byte[] r1 = r0.tail
                int r2 = r0.tailLen
                int r4 = r2 + 1
                r0.tailLen = r4
                byte r5 = r18[r11]
                r1[r2] = r5
                int r2 = r2 + r6
                r0.tailLen = r2
                int r11 = r11 + r7
                byte r2 = r18[r11]
                r1[r4] = r2
            L_0x01dc:
                r0.op = r9
                r0.count = r3
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    private Base64() {
    }

    public static byte[] decode(String str) {
        return decode(str, 2);
    }

    public static String encode(byte[] bArr) {
        return encodeToString(bArr, 2);
    }

    public static String encodeToString(byte[] bArr, int i3) {
        try {
            return new String(encode(bArr, i3), "US-ASCII");
        } catch (UnsupportedEncodingException e3) {
            throw new AssertionError(e3);
        }
    }

    public static byte[] urlSafeDecode(String str) {
        return decode(str, 11);
    }

    public static String urlSafeEncode(byte[] bArr) {
        return encodeToString(bArr, 11);
    }

    public static byte[] decode(String str, int i3) {
        return decode(str.getBytes(UTF_8), i3);
    }

    public static byte[] encode(byte[] bArr, int i3) {
        return encode(bArr, 0, bArr.length, i3);
    }

    public static byte[] decode(byte[] bArr, int i3) {
        return decode(bArr, 0, bArr.length, i3);
    }

    public static byte[] encode(byte[] bArr, int i3, int i4, int i5) {
        Encoder encoder = new Encoder(i5, (byte[]) null);
        int i6 = (i4 / 3) * 4;
        int i7 = 2;
        if (!encoder.doPadding) {
            int i8 = i4 % 3;
            if (i8 == 1) {
                i6 += 2;
            } else if (i8 == 2) {
                i6 += 3;
            }
        } else if (i4 % 3 > 0) {
            i6 += 4;
        }
        if (encoder.doNewline && i4 > 0) {
            int d2 = a.d(i4, 1, 57, 1);
            if (!encoder.doCr) {
                i7 = 1;
            }
            i6 += d2 * i7;
        }
        encoder.output = new byte[i6];
        encoder.process(bArr, i3, i4, true);
        return encoder.output;
    }

    public static String encodeToString(byte[] bArr, int i3, int i4, int i5) {
        try {
            return new String(encode(bArr, i3, i4, i5), "US-ASCII");
        } catch (UnsupportedEncodingException e3) {
            throw new AssertionError(e3);
        }
    }

    public static byte[] decode(byte[] bArr, int i3, int i4, int i5) {
        Decoder decoder = new Decoder(i5, new byte[((i4 * 3) / 4)]);
        if (decoder.process(bArr, i3, i4, true)) {
            int i6 = decoder.op;
            byte[] bArr2 = decoder.output;
            if (i6 == bArr2.length) {
                return bArr2;
            }
            byte[] bArr3 = new byte[i6];
            System.arraycopy(bArr2, 0, bArr3, 0, i6);
            return bArr3;
        }
        throw new IllegalArgumentException("bad base-64");
    }
}
