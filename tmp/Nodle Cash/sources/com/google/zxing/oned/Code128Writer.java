package com.google.zxing.oned;

import A.a;
import androidx.exifinterface.media.ExifInterface;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final char ESCAPE_FNC_1 = 'ñ';
    private static final char ESCAPE_FNC_2 = 'ò';
    private static final char ESCAPE_FNC_3 = 'ó';
    private static final char ESCAPE_FNC_4 = 'ô';

    /* renamed from: com.google.zxing.oned.Code128Writer$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset;
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        static {
            /*
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset[] r0 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset = r0
                r1 = 1
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r2 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.f7198A     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.B     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r4 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.C     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch[] r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch = r3
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r4 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.f7201A     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.B     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r1 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.C     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r1 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.SHIFT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.AnonymousClass1.<clinit>():void");
        }
    }

    public enum CType {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public static final class MinimalEncoder {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        /* renamed from: A  reason: collision with root package name */
        static final String f7196A = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fÿ";

        /* renamed from: B  reason: collision with root package name */
        static final String f7197B = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ÿ";
        private static final int CODE_SHIFT = 98;
        private int[][] memoizedCost;
        private Latch[][] minPath;

        public enum Charset {
            f7198A,
            B,
            C,
            NONE
        }

        public enum Latch {
            f7201A,
            B,
            C,
            SHIFT,
            NONE
        }

        private MinimalEncoder() {
        }

        private static void addPattern(Collection<int[]> collection, int i3, int[] iArr, int[] iArr2, int i4) {
            collection.add(Code128Reader.CODE_PATTERNS[i3]);
            if (i4 != 0) {
                iArr2[0] = iArr2[0] + 1;
            }
            iArr[0] = (i3 * iArr2[0]) + iArr[0];
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0021, code lost:
            r10 = r10 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean canEncode(java.lang.CharSequence r8, com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset r9, int r10) {
            /*
                r7 = this;
                char r7 = r8.charAt(r10)
                int[] r0 = com.google.zxing.oned.Code128Writer.AnonymousClass1.$SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Charset
                int r9 = r9.ordinal()
                r9 = r0[r9]
                r0 = 244(0xf4, float:3.42E-43)
                r1 = 243(0xf3, float:3.4E-43)
                r2 = 242(0xf2, float:3.39E-43)
                r3 = 241(0xf1, float:3.38E-43)
                r4 = 0
                r5 = 1
                if (r9 == r5) goto L_0x004c
                r6 = 2
                if (r9 == r6) goto L_0x003a
                r0 = 3
                if (r9 == r0) goto L_0x001f
                return r4
            L_0x001f:
                if (r7 == r3) goto L_0x0038
                int r10 = r10 + r5
                int r9 = r8.length()
                if (r10 >= r9) goto L_0x0039
                boolean r7 = isDigit(r7)
                if (r7 == 0) goto L_0x0039
                char r7 = r8.charAt(r10)
                boolean r7 = isDigit(r7)
                if (r7 == 0) goto L_0x0039
            L_0x0038:
                r4 = r5
            L_0x0039:
                return r4
            L_0x003a:
                if (r7 == r3) goto L_0x004a
                if (r7 == r2) goto L_0x004a
                if (r7 == r1) goto L_0x004a
                if (r7 == r0) goto L_0x004a
                java.lang.String r8 = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ÿ"
                int r7 = r8.indexOf(r7)
                if (r7 < 0) goto L_0x004b
            L_0x004a:
                r4 = r5
            L_0x004b:
                return r4
            L_0x004c:
                if (r7 == r3) goto L_0x005c
                if (r7 == r2) goto L_0x005c
                if (r7 == r1) goto L_0x005c
                if (r7 == r0) goto L_0x005c
                java.lang.String r8 = " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001fÿ"
                int r7 = r8.indexOf(r7)
                if (r7 < 0) goto L_0x005d
            L_0x005c:
                r4 = r5
            L_0x005d:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.MinimalEncoder.canEncode(java.lang.CharSequence, com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset, int):boolean");
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:27:0x009b  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00ba  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean[] encode(java.lang.String r17) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                int r2 = r17.length()
                r3 = 2
                int[] r4 = new int[r3]
                r5 = 1
                r4[r5] = r2
                r2 = 0
                r6 = 4
                r4[r2] = r6
                java.lang.Class r7 = java.lang.Integer.TYPE
                java.lang.Object r4 = java.lang.reflect.Array.newInstance(r7, r4)
                int[][] r4 = (int[][]) r4
                r0.memoizedCost = r4
                int r4 = r17.length()
                int[] r7 = new int[r3]
                r7[r5] = r4
                r7[r2] = r6
                java.lang.Class<com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch> r4 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.class
                java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r7)
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch[][] r4 = (com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch[][]) r4
                r0.minPath = r4
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r4 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.NONE
                r0.encode(r1, r4, r2)
                java.util.ArrayList r7 = new java.util.ArrayList
                r7.<init>()
                int[] r8 = new int[]{r2}
                int[] r9 = new int[]{r5}
                int r10 = r17.length()
                r11 = r2
            L_0x0047:
                if (r11 >= r10) goto L_0x00fd
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch[][] r12 = r0.minPath
                int r13 = r4.ordinal()
                r12 = r12[r13]
                r12 = r12[r11]
                int[] r13 = com.google.zxing.oned.Code128Writer.AnonymousClass1.$SwitchMap$com$google$zxing$oned$Code128Writer$MinimalEncoder$Latch
                int r14 = r12.ordinal()
                r13 = r13[r14]
                r14 = 101(0x65, float:1.42E-43)
                r15 = 100
                if (r13 == r5) goto L_0x0089
                if (r13 == r3) goto L_0x007d
                r3 = 3
                if (r13 == r3) goto L_0x006f
                if (r13 == r6) goto L_0x0069
                goto L_0x0095
            L_0x0069:
                r3 = 98
                addPattern(r7, r3, r8, r9, r11)
                goto L_0x0095
            L_0x006f:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.C
                if (r11 != 0) goto L_0x0076
                r4 = 105(0x69, float:1.47E-43)
                goto L_0x0078
            L_0x0076:
                r4 = 99
            L_0x0078:
                addPattern(r7, r4, r8, r9, r11)
            L_0x007b:
                r4 = r3
                goto L_0x0095
            L_0x007d:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.B
                if (r11 != 0) goto L_0x0084
                r4 = 104(0x68, float:1.46E-43)
                goto L_0x0085
            L_0x0084:
                r4 = r15
            L_0x0085:
                addPattern(r7, r4, r8, r9, r11)
                goto L_0x007b
            L_0x0089:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.f7198A
                if (r11 != 0) goto L_0x0090
                r4 = 103(0x67, float:1.44E-43)
                goto L_0x0091
            L_0x0090:
                r4 = r14
            L_0x0091:
                addPattern(r7, r4, r8, r9, r11)
                goto L_0x007b
            L_0x0095:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.C
                r13 = 102(0x66, float:1.43E-43)
                if (r4 != r3) goto L_0x00ba
                char r3 = r1.charAt(r11)
                r12 = 241(0xf1, float:3.38E-43)
                if (r3 != r12) goto L_0x00a7
                addPattern(r7, r13, r8, r9, r11)
                goto L_0x00f9
            L_0x00a7:
                int r3 = r11 + 2
                java.lang.String r3 = r1.substring(r11, r3)
                int r3 = java.lang.Integer.parseInt(r3)
                addPattern(r7, r3, r8, r9, r11)
                int r3 = r11 + 1
                if (r3 >= r10) goto L_0x00f9
                r11 = r3
                goto L_0x00f9
            L_0x00ba:
                char r3 = r1.charAt(r11)
                switch(r3) {
                    case 241: goto L_0x00e1;
                    case 242: goto L_0x00de;
                    case 243: goto L_0x00db;
                    case 244: goto L_0x00c8;
                    default: goto L_0x00c1;
                }
            L_0x00c1:
                char r3 = r1.charAt(r11)
                int r14 = r3 + -32
                goto L_0x00e2
            L_0x00c8:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.f7198A
                if (r4 != r3) goto L_0x00d0
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.SHIFT
                if (r12 != r3) goto L_0x00e2
            L_0x00d0:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.B
                if (r4 != r3) goto L_0x00d9
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.SHIFT
                if (r12 != r3) goto L_0x00d9
                goto L_0x00e2
            L_0x00d9:
                r14 = r15
                goto L_0x00e2
            L_0x00db:
                r14 = 96
                goto L_0x00e2
            L_0x00de:
                r14 = 97
                goto L_0x00e2
            L_0x00e1:
                r14 = r13
            L_0x00e2:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.f7198A
                if (r4 != r3) goto L_0x00ea
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.SHIFT
                if (r12 != r3) goto L_0x00f2
            L_0x00ea:
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Charset r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Charset.B
                if (r4 != r3) goto L_0x00f6
                com.google.zxing.oned.Code128Writer$MinimalEncoder$Latch r3 = com.google.zxing.oned.Code128Writer.MinimalEncoder.Latch.SHIFT
                if (r12 != r3) goto L_0x00f6
            L_0x00f2:
                if (r14 >= 0) goto L_0x00f6
                int r14 = r14 + 96
            L_0x00f6:
                addPattern(r7, r14, r8, r9, r11)
            L_0x00f9:
                int r11 = r11 + r5
                r3 = 2
                goto L_0x0047
            L_0x00fd:
                r1 = 0
                r0.memoizedCost = r1
                r0.minPath = r1
                r0 = r8[r2]
                boolean[] r0 = com.google.zxing.oned.Code128Writer.produceResult(r7, r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Writer.MinimalEncoder.encode(java.lang.String):boolean[]");
        }

        private static boolean isDigit(char c3) {
            return c3 >= '0' && c3 <= '9';
        }

        public /* synthetic */ MinimalEncoder(AnonymousClass1 r12) {
            this();
        }

        private int encode(CharSequence charSequence, Charset charset, int i3) {
            int i4;
            int i5;
            int i6 = this.memoizedCost[charset.ordinal()][i3];
            if (i6 > 0) {
                return i6;
            }
            Latch latch = Latch.NONE;
            int i7 = i3 + 1;
            int i8 = 0;
            int i9 = 1;
            boolean z2 = i7 >= charSequence.length();
            Charset[] charsetArr = {Charset.f7198A, Charset.B};
            int i10 = Integer.MAX_VALUE;
            while (true) {
                int i11 = 2;
                if (i8 > 1) {
                    break;
                }
                if (canEncode(charSequence, charsetArr[i8], i3)) {
                    Latch latch2 = Latch.NONE;
                    Charset charset2 = charsetArr[i8];
                    if (charset != charset2) {
                        latch2 = Latch.valueOf(charset2.toString());
                        i5 = 2;
                    } else {
                        i5 = 1;
                    }
                    if (!z2) {
                        i5 += encode(charSequence, charsetArr[i8], i7);
                    }
                    if (i5 < i10) {
                        latch = latch2;
                        i10 = i5;
                    }
                    if (charset == charsetArr[(i8 + 1) % 2]) {
                        Latch latch3 = Latch.SHIFT;
                        if (!z2) {
                            i11 = 2 + encode(charSequence, charset, i7);
                        }
                        if (i11 < i10) {
                            i10 = i11;
                            latch = latch3;
                        }
                    }
                }
                i8++;
            }
            Charset charset3 = Charset.C;
            if (canEncode(charSequence, charset3, i3)) {
                Latch latch4 = Latch.NONE;
                if (charset != charset3) {
                    latch4 = Latch.C;
                    i4 = 2;
                } else {
                    i4 = 1;
                }
                if (charSequence.charAt(i3) != 241) {
                    i9 = 2;
                }
                int i12 = i9 + i3;
                if (i12 < charSequence.length()) {
                    i4 += encode(charSequence, charset3, i12);
                }
                if (i4 < i10) {
                    latch = latch4;
                    i10 = i4;
                }
            }
            if (i10 != Integer.MAX_VALUE) {
                this.memoizedCost[charset.ordinal()][i3] = i10;
                this.minPath[charset.ordinal()][i3] = latch;
                return i10;
            }
            throw new IllegalArgumentException("Bad character in input: ASCII value=" + charSequence.charAt(i3));
        }
    }

    private static int check(String str, Map<EncodeHintType, ?> map) {
        int i3 = -1;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.FORCE_CODE_SET;
            if (map.containsKey(encodeHintType)) {
                String obj = map.get(encodeHintType).toString();
                obj.getClass();
                switch (obj.hashCode()) {
                    case 65:
                        if (obj.equals(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS)) {
                            i3 = 0;
                            break;
                        }
                        break;
                    case 66:
                        if (obj.equals("B")) {
                            i3 = 1;
                            break;
                        }
                        break;
                    case 67:
                        if (obj.equals("C")) {
                            i3 = 2;
                            break;
                        }
                        break;
                }
                switch (i3) {
                    case 0:
                        i3 = 101;
                        break;
                    case 1:
                        i3 = 100;
                        break;
                    case 2:
                        i3 = 99;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported code set hint: ".concat(obj));
                }
            }
        }
        int length = str.length();
        for (int i4 = 0; i4 < length; i4++) {
            char charAt = str.charAt(i4);
            switch (charAt) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (charAt > 127) {
                        throw new IllegalArgumentException(a.k("Bad character in input: ASCII value=", charAt));
                    }
                    break;
            }
            switch (i3) {
                case 99:
                    if (charAt >= '0' && ((charAt <= '9' || charAt > 127) && charAt != 242 && charAt != 243 && charAt != 244)) {
                        break;
                    } else {
                        throw new IllegalArgumentException(a.k("Bad character in input for forced code set C: ASCII value=", charAt));
                    }
                case 100:
                    if (charAt >= ' ') {
                        break;
                    } else {
                        throw new IllegalArgumentException(a.k("Bad character in input for forced code set B: ASCII value=", charAt));
                    }
                case 101:
                    if (charAt > '_' && charAt <= 127) {
                        throw new IllegalArgumentException(a.k("Bad character in input for forced code set A: ASCII value=", charAt));
                    }
            }
        }
        return i3;
    }

    private static int chooseCode(CharSequence charSequence, int i3, int i4) {
        CType findCType;
        CType findCType2;
        char charAt;
        CType findCType3 = findCType(charSequence, i3);
        CType cType = CType.ONE_DIGIT;
        if (findCType3 == cType) {
            return i4 == 101 ? 101 : 100;
        }
        CType cType2 = CType.UNCODABLE;
        if (findCType3 == cType2) {
            return (i3 >= charSequence.length() || ((charAt = charSequence.charAt(i3)) >= ' ' && (i4 != 101 || (charAt >= '`' && (charAt < 241 || charAt > 244))))) ? 100 : 101;
        }
        if (i4 == 101 && findCType3 == CType.FNC_1) {
            return 101;
        }
        if (i4 == 99) {
            return 99;
        }
        if (i4 == 100) {
            CType cType3 = CType.FNC_1;
            if (findCType3 == cType3 || (findCType = findCType(charSequence, i3 + 2)) == cType2 || findCType == cType) {
                return 100;
            }
            if (findCType == cType3) {
                return findCType(charSequence, i3 + 3) == CType.TWO_DIGITS ? 99 : 100;
            }
            int i5 = i3 + 4;
            while (true) {
                findCType2 = findCType(charSequence, i5);
                if (findCType2 != CType.TWO_DIGITS) {
                    break;
                }
                i5 += 2;
            }
            return findCType2 == CType.ONE_DIGIT ? 100 : 99;
        }
        if (findCType3 == CType.FNC_1) {
            findCType3 = findCType(charSequence, i3 + 1);
        }
        return findCType3 == CType.TWO_DIGITS ? 99 : 100;
    }

    private static boolean[] encodeFast(String str, int i3) {
        int i4;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 1;
        while (i5 < length) {
            int chooseCode = i3 == -1 ? chooseCode(str, i5, i7) : i3;
            int i9 = 100;
            if (chooseCode == i7) {
                switch (str.charAt(i5)) {
                    case 241:
                        i9 = 102;
                        break;
                    case 242:
                        i9 = 97;
                        break;
                    case 243:
                        i9 = 96;
                        break;
                    case 244:
                        if (i7 == 101) {
                            i9 = 101;
                            break;
                        }
                        break;
                    default:
                        if (i7 != 100) {
                            if (i7 == 101) {
                                char charAt = str.charAt(i5);
                                i9 = charAt - ' ';
                                if (i9 < 0) {
                                    i9 = charAt + '@';
                                    break;
                                }
                            } else {
                                int i10 = i5 + 1;
                                if (i10 != length) {
                                    i9 = Integer.parseInt(str.substring(i5, i5 + 2));
                                    i5 = i10;
                                    break;
                                } else {
                                    throw new IllegalArgumentException("Bad number of characters for digit only encoding.");
                                }
                            }
                        } else {
                            i9 = str.charAt(i5) - ' ';
                            break;
                        }
                        break;
                }
                i5++;
            } else {
                if (i7 == 0) {
                    i4 = chooseCode != 100 ? chooseCode != 101 ? 105 : 103 : 104;
                } else {
                    i4 = chooseCode;
                }
                i7 = chooseCode;
            }
            arrayList.add(Code128Reader.CODE_PATTERNS[i4]);
            i6 += i4 * i8;
            if (i5 != 0) {
                i8++;
            }
        }
        return produceResult(arrayList, i6);
    }

    private static CType findCType(CharSequence charSequence, int i3) {
        int length = charSequence.length();
        if (i3 >= length) {
            return CType.UNCODABLE;
        }
        char charAt = charSequence.charAt(i3);
        if (charAt == 241) {
            return CType.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return CType.UNCODABLE;
        }
        int i4 = i3 + 1;
        if (i4 >= length) {
            return CType.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i4);
        return (charAt2 < '0' || charAt2 > '9') ? CType.ONE_DIGIT : CType.TWO_DIGITS;
    }

    public static boolean[] produceResult(Collection<int[]> collection, int i3) {
        int i4 = i3 % 103;
        if (i4 >= 0) {
            int[][] iArr = Code128Reader.CODE_PATTERNS;
            collection.add(iArr[i4]);
            collection.add(iArr[106]);
            int i5 = 0;
            int i6 = 0;
            for (int[] next : collection) {
                for (int i7 : r7.next()) {
                    i6 += i7;
                }
            }
            boolean[] zArr = new boolean[i6];
            for (int[] appendPattern : collection) {
                i5 += OneDimensionalCodeWriter.appendPattern(zArr, i5, appendPattern, true);
            }
            return zArr;
        }
        throw new IllegalArgumentException("Unable to compute a valid input checksum");
    }

    public boolean[] encode(String str) {
        return encode(str, (Map<EncodeHintType, ?>) null);
    }

    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_128);
    }

    public boolean[] encode(String str, Map<EncodeHintType, ?> map) {
        int check = check(str, map);
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.CODE128_COMPACT;
            if (map.containsKey(encodeHintType) && Boolean.parseBoolean(map.get(encodeHintType).toString())) {
                return new MinimalEncoder((AnonymousClass1) null).encode(str);
            }
        }
        return encodeFast(str, check);
    }
}
