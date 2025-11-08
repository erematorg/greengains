package com.google.zxing.datamatrix.encoder;

import androidx.constraintlayout.core.state.b;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.zxing.common.MinimalECIInput;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.provider.jffi.JNINativeInterface;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public final class MinimalEncoder {
    static final char[] C40_SHIFT2_CHARS = {'!', '\"', '#', '$', '%', Typography.amp, '\'', '(', ')', '*', SignatureVisitor.EXTENDS, AbstractJsonLexerKt.COMMA, SignatureVisitor.SUPER, ClassUtils.PACKAGE_SEPARATOR_CHAR, JsonPointer.SEPARATOR, AbstractJsonLexerKt.COLON, ';', Typography.less, SignatureVisitor.INSTANCEOF, Typography.greater, '?', '@', AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.STRING_ESC, AbstractJsonLexerKt.END_LIST, '^', '_'};

    /* renamed from: com.google.zxing.datamatrix.encoder.MinimalEncoder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode;
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(19:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0059 */
        static {
            /*
                com.google.zxing.datamatrix.encoder.SymbolShapeHint[] r0 = com.google.zxing.datamatrix.encoder.SymbolShapeHint.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint = r0
                r1 = 1
                com.google.zxing.datamatrix.encoder.SymbolShapeHint r2 = com.google.zxing.datamatrix.encoder.SymbolShapeHint.FORCE_SQUARE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.zxing.datamatrix.encoder.SymbolShapeHint r3 = com.google.zxing.datamatrix.encoder.SymbolShapeHint.FORCE_RECTANGLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode[] r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode = r2
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r3 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.B256     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode     // Catch:{ NoSuchFieldError -> 0x004e }
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT     // Catch:{ NoSuchFieldError -> 0x004e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode     // Catch:{ NoSuchFieldError -> 0x0059 }
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12     // Catch:{ NoSuchFieldError -> 0x0059 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0059 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0059 }
            L_0x0059:
                int[] r0 = $SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.EDF     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.MinimalEncoder.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class Edge {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int[] allCodewordCapacities = {3, 5, 8, 10, 12, 16, 18, 22, 30, 32, 36, 44, 49, 62, 86, 114, 144, 174, 204, 280, 368, 456, 576, 696, 816, 1050, 1304, 1558};
        private static final int[] rectangularCodewordCapacities = {5, 10, 16, 33, 32, 49};
        private static final int[] squareCodewordCapacities = {3, 5, 8, 12, 18, 22, 30, 36, 44, 62, 86, 114, 144, 174, 204, 280, 368, 456, 576, 696, 816, 1050, 1304, 1558};
        /* access modifiers changed from: private */
        public final int cachedTotalSize;
        /* access modifiers changed from: private */
        public final int characterLength;
        /* access modifiers changed from: private */
        public final int fromPosition;
        /* access modifiers changed from: private */
        public final Input input;
        /* access modifiers changed from: private */
        public final Mode mode;
        /* access modifiers changed from: private */
        public final Edge previous;

        public /* synthetic */ Edge(Input input2, Mode mode2, int i3, int i4, Edge edge, AnonymousClass1 r6) {
            this(input2, mode2, i3, i4, edge);
        }

        public static byte[] getBytes(int i3) {
            return new byte[]{(byte) i3};
        }

        private static int getC40Value(boolean z2, int i3, char c3, int i4) {
            if (c3 == i4) {
                return 27;
            }
            if (!z2) {
                if (c3 != 0) {
                    if (i3 == 0 && c3 <= 3) {
                        return c3 - 1;
                    }
                    if (i3 == 1 && c3 <= 31) {
                        return c3;
                    }
                    if (c3 == ' ') {
                        return 3;
                    }
                    if (c3 >= '!' && c3 <= '/') {
                        return c3 - '!';
                    }
                    if (c3 >= '0' && c3 <= '9') {
                        return c3 - ',';
                    }
                    if (c3 >= ':' && c3 <= '@') {
                        return c3 - '+';
                    }
                    if (c3 >= 'A' && c3 <= 'Z') {
                        return c3 - '@';
                    }
                    if (c3 >= '[' && c3 <= '_') {
                        return c3 - 'E';
                    }
                    if (c3 != '`') {
                        return (c3 < 'a' || c3 > 'z') ? (c3 < '{' || c3 > 127) ? c3 : c3 - '`' : c3 - 'S';
                    }
                }
                return 0;
            } else if (c3 <= 31) {
                return c3;
            } else {
                if (c3 == ' ') {
                    return 3;
                }
                return c3 <= '/' ? c3 - '!' : c3 <= '9' ? c3 - ',' : c3 <= '@' ? c3 - '+' : c3 <= 'Z' ? c3 - '3' : c3 <= '_' ? c3 - 'E' : c3 <= 127 ? c3 - '`' : c3;
            }
        }

        public static int getShiftValue(char c3, boolean z2, int i3) {
            if ((!z2 || !MinimalEncoder.isInC40Shift1Set(c3)) && (z2 || !MinimalEncoder.isInTextShift1Set(c3))) {
                return ((!z2 || !MinimalEncoder.isInC40Shift2Set(c3, i3)) && (z2 || !MinimalEncoder.isInTextShift2Set(c3, i3))) ? 2 : 1;
            }
            return 0;
        }

        private static int getX12Value(char c3) {
            if (c3 == 13) {
                return 0;
            }
            if (c3 == '*') {
                return 1;
            }
            if (c3 == '>') {
                return 2;
            }
            if (c3 == ' ') {
                return 3;
            }
            return (c3 < '0' || c3 > '9') ? (c3 < 'A' || c3 > 'Z') ? c3 : c3 - '3' : c3 - ',';
        }

        public static void setC40Word(byte[] bArr, int i3, int i4, int i5, int i6) {
            int i7 = ((i5 & 255) * 40) + ((i4 & 255) * 1600) + (i6 & 255) + 1;
            bArr[i3] = (byte) (i7 / 256);
            bArr[i3 + 1] = (byte) (i7 % 256);
        }

        public int getB256Size() {
            int i3 = 0;
            while (this != null && this.mode == Mode.B256 && i3 <= 250) {
                i3++;
                this = this.previous;
            }
            return i3;
        }

        public byte[] getC40Words(boolean z2, int i3) {
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < this.characterLength; i4++) {
                char charAt = this.input.charAt(this.fromPosition + i4);
                if ((z2 && HighLevelEncoder.isNativeC40(charAt)) || (!z2 && HighLevelEncoder.isNativeText(charAt))) {
                    arrayList.add(Byte.valueOf((byte) getC40Value(z2, 0, charAt, i3)));
                } else if (!MinimalEncoder.isExtendedASCII(charAt, i3)) {
                    int shiftValue = getShiftValue(charAt, z2, i3);
                    arrayList.add(Byte.valueOf((byte) shiftValue));
                    arrayList.add(Byte.valueOf((byte) getC40Value(z2, shiftValue, charAt, i3)));
                } else {
                    char c3 = (char) ((charAt & 255) - 128);
                    if ((!z2 || !HighLevelEncoder.isNativeC40(c3)) && (z2 || !HighLevelEncoder.isNativeText(c3))) {
                        arrayList.add((byte) 1);
                        arrayList.add((byte) 30);
                        int shiftValue2 = getShiftValue(c3, z2, i3);
                        arrayList.add(Byte.valueOf((byte) shiftValue2));
                        arrayList.add(Byte.valueOf((byte) getC40Value(z2, shiftValue2, c3, i3)));
                    } else {
                        arrayList.add((byte) 1);
                        arrayList.add((byte) 30);
                        arrayList.add(Byte.valueOf((byte) getC40Value(z2, 0, c3, i3)));
                    }
                }
            }
            if (arrayList.size() % 3 != 0) {
                arrayList.add((byte) 0);
            }
            byte[] bArr = new byte[((arrayList.size() / 3) * 2)];
            int i5 = 0;
            for (int i6 = 0; i6 < arrayList.size(); i6 += 3) {
                setC40Word(bArr, i5, ((Byte) arrayList.get(i6)).byteValue() & 255, ((Byte) arrayList.get(i6 + 1)).byteValue() & 255, ((Byte) arrayList.get(i6 + 2)).byteValue() & 255);
                i5 += 2;
            }
            return bArr;
        }

        public int getCodewordsRemaining(int i3) {
            return getMinSymbolSize(i3) - i3;
        }

        public byte[] getDataBytes() {
            switch (AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode[this.mode.ordinal()]) {
                case 1:
                    if (this.input.isECI(this.fromPosition)) {
                        return getBytes(241, this.input.getECIValue(this.fromPosition) + 1);
                    }
                    if (MinimalEncoder.isExtendedASCII(this.input.charAt(this.fromPosition), this.input.getFNC1Character())) {
                        return getBytes(235, this.input.charAt(this.fromPosition) - 127);
                    }
                    if (this.characterLength != 2) {
                        return this.input.isFNC1(this.fromPosition) ? getBytes(JNINativeInterface.GetObjectRefType) : getBytes(this.input.charAt(this.fromPosition) + 1);
                    }
                    return getBytes(this.input.charAt(this.fromPosition + 1) + ((this.input.charAt(this.fromPosition) - '0') * 10) + 82);
                case 2:
                    return getBytes(this.input.charAt(this.fromPosition));
                case 3:
                    return getC40Words(true, this.input.getFNC1Character());
                case 4:
                    return getC40Words(false, this.input.getFNC1Character());
                case 5:
                    return getX12Words();
                case 6:
                    return getEDFBytes();
                default:
                    return new byte[0];
            }
        }

        public byte[] getEDFBytes() {
            int ceil = (int) Math.ceil(((double) this.characterLength) / 4.0d);
            byte[] bArr = new byte[(ceil * 3)];
            int i3 = this.fromPosition;
            int min = Math.min((this.characterLength + i3) - 1, this.input.length() - 1);
            for (int i4 = 0; i4 < ceil; i4 += 3) {
                int[] iArr = new int[4];
                for (int i5 = 0; i5 < 4; i5++) {
                    if (i3 <= min) {
                        iArr[i5] = this.input.charAt(i3) & '?';
                        i3++;
                    } else {
                        iArr[i5] = i3 == min + 1 ? 31 : 0;
                    }
                }
                int i6 = (iArr[0] << 18) | (iArr[1] << 12) | (iArr[2] << 6) | iArr[3];
                bArr[i4] = (byte) ((i6 >> 16) & 255);
                bArr[i4 + 1] = (byte) ((i6 >> 8) & 255);
                bArr[i4 + 2] = (byte) (i6 & 255);
            }
            return bArr;
        }

        public Mode getEndMode() {
            if (this.mode == Mode.EDF) {
                if (this.characterLength < 4) {
                    return Mode.ASCII;
                }
                int lastASCII = getLastASCII();
                if (lastASCII > 0 && getCodewordsRemaining(this.cachedTotalSize + lastASCII) <= 2 - lastASCII) {
                    return Mode.ASCII;
                }
            }
            Mode mode2 = this.mode;
            if (mode2 == Mode.C40 || mode2 == Mode.TEXT || mode2 == Mode.X12) {
                if (this.fromPosition + this.characterLength >= this.input.length() && getCodewordsRemaining(this.cachedTotalSize) == 0) {
                    return Mode.ASCII;
                }
                if (getLastASCII() == 1 && getCodewordsRemaining(this.cachedTotalSize + 1) == 0) {
                    return Mode.ASCII;
                }
            }
            return this.mode;
        }

        public int getLastASCII() {
            int length = this.input.length();
            int i3 = this.fromPosition + this.characterLength;
            int i4 = length - i3;
            if (i4 <= 4 && i3 < length) {
                if (i4 == 1) {
                    return MinimalEncoder.isExtendedASCII(this.input.charAt(i3), this.input.getFNC1Character()) ? 0 : 1;
                }
                if (i4 == 2) {
                    if (!MinimalEncoder.isExtendedASCII(this.input.charAt(i3), this.input.getFNC1Character())) {
                        int i5 = i3 + 1;
                        if (!MinimalEncoder.isExtendedASCII(this.input.charAt(i5), this.input.getFNC1Character())) {
                            return (!HighLevelEncoder.isDigit(this.input.charAt(i3)) || !HighLevelEncoder.isDigit(this.input.charAt(i5))) ? 2 : 1;
                        }
                    }
                    return 0;
                } else if (i4 != 3) {
                    return (!HighLevelEncoder.isDigit(this.input.charAt(i3)) || !HighLevelEncoder.isDigit(this.input.charAt(i3 + 1)) || !HighLevelEncoder.isDigit(this.input.charAt(i3 + 2)) || !HighLevelEncoder.isDigit(this.input.charAt(i3 + 3))) ? 0 : 2;
                } else {
                    if (!HighLevelEncoder.isDigit(this.input.charAt(i3)) || !HighLevelEncoder.isDigit(this.input.charAt(i3 + 1)) || MinimalEncoder.isExtendedASCII(this.input.charAt(i3 + 2), this.input.getFNC1Character())) {
                        return (!HighLevelEncoder.isDigit(this.input.charAt(i3 + 1)) || !HighLevelEncoder.isDigit(this.input.charAt(i3 + 2)) || MinimalEncoder.isExtendedASCII(this.input.charAt(i3), this.input.getFNC1Character())) ? 0 : 2;
                    }
                    return 2;
                }
            }
        }

        public byte[] getLatchBytes() {
            int[] iArr = AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode;
            switch (iArr[getPreviousMode().ordinal()]) {
                case 1:
                case 2:
                    int i3 = iArr[this.mode.ordinal()];
                    if (i3 == 2) {
                        return getBytes(JNINativeInterface.GetDirectBufferCapacity);
                    }
                    if (i3 == 3) {
                        return getBytes(JNINativeInterface.GetDirectBufferAddress);
                    }
                    if (i3 == 4) {
                        return getBytes(239);
                    }
                    if (i3 == 5) {
                        return getBytes(238);
                    }
                    if (i3 == 6) {
                        return getBytes(240);
                    }
                    break;
                case 3:
                case 4:
                case 5:
                    if (this.mode != getPreviousMode()) {
                        switch (iArr[this.mode.ordinal()]) {
                            case 1:
                                return getBytes(254);
                            case 2:
                                return getBytes(254, JNINativeInterface.GetDirectBufferCapacity);
                            case 3:
                                return getBytes(254, JNINativeInterface.GetDirectBufferAddress);
                            case 4:
                                return getBytes(254, 239);
                            case 5:
                                return getBytes(254, 238);
                            case 6:
                                return getBytes(254, 240);
                        }
                    }
                    break;
            }
            return new byte[0];
        }

        public int getMinSymbolSize(int i3) {
            int i4 = AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$encoder$SymbolShapeHint[this.input.getShapeHint().ordinal()];
            if (i4 == 1) {
                for (int i5 : squareCodewordCapacities) {
                    if (i5 >= i3) {
                        return i5;
                    }
                }
            } else if (i4 == 2) {
                for (int i6 : rectangularCodewordCapacities) {
                    if (i6 >= i3) {
                        return i6;
                    }
                }
            }
            for (int i7 : allCodewordCapacities) {
                if (i7 >= i3) {
                    return i7;
                }
            }
            int[] iArr = allCodewordCapacities;
            return iArr[iArr.length - 1];
        }

        public Mode getMode() {
            return this.mode;
        }

        public Mode getPreviousMode() {
            Edge edge = this.previous;
            return edge == null ? Mode.ASCII : edge.getEndMode();
        }

        public Mode getPreviousStartMode() {
            Edge edge = this.previous;
            return edge == null ? Mode.ASCII : edge.mode;
        }

        public byte[] getX12Words() {
            int i3 = (this.characterLength / 3) * 2;
            byte[] bArr = new byte[i3];
            for (int i4 = 0; i4 < i3; i4 += 2) {
                int i5 = (i4 / 2) * 3;
                setC40Word(bArr, i4, getX12Value(this.input.charAt(this.fromPosition + i5)), getX12Value(this.input.charAt(this.fromPosition + i5 + 1)), getX12Value(this.input.charAt(this.fromPosition + i5 + 2)));
            }
            return bArr;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0072, code lost:
            if (r0 == r1) goto L_0x0074;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0074, code lost:
            r10 = r10 + 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x009c, code lost:
            if (r0 != com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12) goto L_0x00c7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c4, code lost:
            if (r0 != com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12) goto L_0x00c7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00c7, code lost:
            r5.cachedTotalSize = r10;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c9, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Edge(com.google.zxing.datamatrix.encoder.MinimalEncoder.Input r6, com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode r7, int r8, int r9, com.google.zxing.datamatrix.encoder.MinimalEncoder.Edge r10) {
            /*
                r5 = this;
                r5.<init>()
                r5.input = r6
                r5.mode = r7
                r5.fromPosition = r8
                r5.characterLength = r9
                r5.previous = r10
                r9 = 0
                if (r10 == 0) goto L_0x0013
                int r10 = r10.cachedTotalSize
                goto L_0x0014
            L_0x0013:
                r10 = r9
            L_0x0014:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r0 = r5.getPreviousMode()
                int[] r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.AnonymousClass1.$SwitchMap$com$google$zxing$datamatrix$encoder$MinimalEncoder$Mode
                int r2 = r7.ordinal()
                r1 = r1[r2]
                switch(r1) {
                    case 1: goto L_0x009f;
                    case 2: goto L_0x007a;
                    case 3: goto L_0x0048;
                    case 4: goto L_0x0048;
                    case 5: goto L_0x0048;
                    case 6: goto L_0x0025;
                    default: goto L_0x0023;
                }
            L_0x0023:
                goto L_0x00c7
            L_0x0025:
                int r6 = r10 + 3
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
                if (r0 == r7) goto L_0x0044
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.B256
                if (r0 != r7) goto L_0x0030
                goto L_0x0044
            L_0x0030:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
                if (r0 == r7) goto L_0x0040
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT
                if (r0 == r7) goto L_0x0040
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12
                if (r0 != r7) goto L_0x003d
                goto L_0x0040
            L_0x003d:
                r10 = r6
                goto L_0x00c7
            L_0x0040:
                int r10 = r10 + 5
                goto L_0x00c7
            L_0x0044:
                int r10 = r10 + 4
                goto L_0x00c7
            L_0x0048:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12
                if (r7 != r1) goto L_0x004f
                int r10 = r10 + 2
                goto L_0x005f
            L_0x004f:
                r2 = 1
                int[] r3 = new int[r2]
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r4 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
                if (r7 != r4) goto L_0x0057
                r9 = r2
            L_0x0057:
                int r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.getNumberOfC40Words(r6, r8, r9, r3)
                int r6 = r6 * 2
                int r6 = r6 + r10
                r10 = r6
            L_0x005f:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
                if (r0 == r6) goto L_0x0077
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.B256
                if (r0 != r6) goto L_0x0068
                goto L_0x0077
            L_0x0068:
                if (r0 == r7) goto L_0x00c7
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
                if (r0 == r6) goto L_0x0074
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT
                if (r0 == r6) goto L_0x0074
                if (r0 != r1) goto L_0x00c7
            L_0x0074:
                int r10 = r10 + 2
                goto L_0x00c7
            L_0x0077:
                int r10 = r10 + 1
                goto L_0x00c7
            L_0x007a:
                int r6 = r10 + 1
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r7 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.B256
                if (r0 == r7) goto L_0x0083
            L_0x0080:
                int r10 = r10 + 2
                goto L_0x008d
            L_0x0083:
                int r7 = r5.getB256Size()
                r8 = 250(0xfa, float:3.5E-43)
                if (r7 != r8) goto L_0x008c
                goto L_0x0080
            L_0x008c:
                r10 = r6
            L_0x008d:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
                if (r0 != r6) goto L_0x0092
                goto L_0x0077
            L_0x0092:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
                if (r0 == r6) goto L_0x0074
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT
                if (r0 == r6) goto L_0x0074
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12
                if (r0 != r6) goto L_0x00c7
                goto L_0x0074
            L_0x009f:
                int r7 = r10 + 1
                boolean r9 = r6.isECI(r8)
                if (r9 != 0) goto L_0x00b8
                char r8 = r6.charAt(r8)
                int r6 = r6.getFNC1Character()
                boolean r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.isExtendedASCII(r8, r6)
                if (r6 == 0) goto L_0x00b6
                goto L_0x00b8
            L_0x00b6:
                r10 = r7
                goto L_0x00ba
            L_0x00b8:
                int r10 = r10 + 2
            L_0x00ba:
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
                if (r0 == r6) goto L_0x0077
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT
                if (r0 == r6) goto L_0x0077
                com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r6 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12
                if (r0 != r6) goto L_0x00c7
                goto L_0x0077
            L_0x00c7:
                r5.cachedTotalSize = r10
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.MinimalEncoder.Edge.<init>(com.google.zxing.datamatrix.encoder.MinimalEncoder$Input, com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode, int, int, com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge):void");
        }

        public static byte[] getBytes(int i3, int i4) {
            return new byte[]{(byte) i3, (byte) i4};
        }
    }

    public static final class Input extends MinimalECIInput {
        private final int macroId;
        private final SymbolShapeHint shape;

        public /* synthetic */ Input(String str, Charset charset, int i3, SymbolShapeHint symbolShapeHint, int i4, AnonymousClass1 r6) {
            this(str, charset, i3, symbolShapeHint, i4);
        }

        /* access modifiers changed from: private */
        public int getMacroId() {
            return this.macroId;
        }

        /* access modifiers changed from: private */
        public SymbolShapeHint getShapeHint() {
            return this.shape;
        }

        private Input(String str, Charset charset, int i3, SymbolShapeHint symbolShapeHint, int i4) {
            super(str, charset, i3);
            this.shape = symbolShapeHint;
            this.macroId = i4;
        }
    }

    public enum Mode {
        ASCII,
        C40,
        TEXT,
        X12,
        EDF,
        B256
    }

    public static final class Result {
        private final byte[] bytes;

        public Result(Edge edge) {
            int i3;
            Input access$1000 = edge.input;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i4 = 0;
            int prepend = ((edge.mode == Mode.C40 || edge.mode == Mode.TEXT || edge.mode == Mode.X12) && edge.getEndMode() != Mode.ASCII) ? prepend(Edge.getBytes(254), arrayList) : 0;
            for (Edge edge2 = edge; edge2 != null; edge2 = edge2.previous) {
                int prepend2 = prepend + prepend(edge2.getDataBytes(), arrayList);
                if (edge2.previous == null || edge2.getPreviousStartMode() != edge2.getMode()) {
                    if (edge2.getMode() == Mode.B256) {
                        if (prepend2 <= 249) {
                            arrayList.add(0, Byte.valueOf((byte) prepend2));
                            i3 = prepend2 + 1;
                        } else {
                            arrayList.add(0, Byte.valueOf((byte) (prepend2 % ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION)));
                            arrayList.add(0, Byte.valueOf((byte) ((prepend2 / ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + 249)));
                            i3 = prepend2 + 2;
                        }
                        arrayList2.add(Integer.valueOf(arrayList.size()));
                        arrayList3.add(Integer.valueOf(i3));
                    }
                    prepend(edge2.getLatchBytes(), arrayList);
                    prepend2 = 0;
                }
            }
            if (access$1000.getMacroId() == 5) {
                prepend(Edge.getBytes(236), arrayList);
            } else if (access$1000.getMacroId() == 6) {
                prepend(Edge.getBytes(237), arrayList);
            }
            if (access$1000.getFNC1Character() > 0) {
                prepend(Edge.getBytes(JNINativeInterface.GetObjectRefType), arrayList);
            }
            for (int i5 = 0; i5 < arrayList2.size(); i5++) {
                applyRandomPattern(arrayList, arrayList.size() - ((Integer) arrayList2.get(i5)).intValue(), ((Integer) arrayList3.get(i5)).intValue());
            }
            int minSymbolSize = edge.getMinSymbolSize(arrayList.size());
            if (arrayList.size() < minSymbolSize) {
                arrayList.add((byte) -127);
            }
            while (arrayList.size() < minSymbolSize) {
                arrayList.add(Byte.valueOf((byte) randomize253State(arrayList.size() + 1)));
            }
            this.bytes = new byte[arrayList.size()];
            while (true) {
                byte[] bArr = this.bytes;
                if (i4 < bArr.length) {
                    bArr[i4] = ((Byte) arrayList.get(i4)).byteValue();
                    i4++;
                } else {
                    return;
                }
            }
        }

        public static void applyRandomPattern(List<Byte> list, int i3, int i4) {
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = i3 + i5;
                int byteValue = (((i6 + 1) * 149) % 255) + 1 + (list.get(i6).byteValue() & 255);
                if (byteValue > 255) {
                    byteValue -= 256;
                }
                list.set(i6, Byte.valueOf((byte) byteValue));
            }
        }

        public static int prepend(byte[] bArr, List<Byte> list) {
            for (int length = bArr.length - 1; length >= 0; length--) {
                list.add(0, Byte.valueOf(bArr[length]));
            }
            return bArr.length;
        }

        private static int randomize253State(int i3) {
            int i4 = (i3 * 149) % 253;
            int i5 = i4 + 130;
            return i5 <= 254 ? i5 : i4 - 124;
        }

        public byte[] getBytes() {
            return this.bytes;
        }
    }

    private MinimalEncoder() {
    }

    public static void addEdge(Edge[][] edgeArr, Edge edge) {
        int access$100 = edge.fromPosition + edge.characterLength;
        if (edgeArr[access$100][edge.getEndMode().ordinal()] == null || edgeArr[access$100][edge.getEndMode().ordinal()].cachedTotalSize > edge.cachedTotalSize) {
            edgeArr[access$100][edge.getEndMode().ordinal()] = edge;
        }
    }

    /* JADX WARNING: type inference failed for: r11v0 */
    /* JADX WARNING: type inference failed for: r11v4 */
    /* JADX WARNING: type inference failed for: r11v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void addEdges(com.google.zxing.datamatrix.encoder.MinimalEncoder.Input r17, com.google.zxing.datamatrix.encoder.MinimalEncoder.Edge[][] r18, int r19, com.google.zxing.datamatrix.encoder.MinimalEncoder.Edge r20) {
        /*
            r7 = r17
            r8 = r18
            r9 = r19
            boolean r0 = r7.isECI(r9)
            if (r0 == 0) goto L_0x0020
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r10 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
            r4 = 1
            r6 = 0
            r0 = r10
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r10)
            return
        L_0x0020:
            char r0 = r7.charAt(r9)
            r10 = 3
            r11 = 0
            r12 = 1
            if (r20 == 0) goto L_0x0031
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = r20.getEndMode()
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.EDF
            if (r1 == r2) goto L_0x00f5
        L_0x0031:
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isDigit(r0)
            r13 = 2
            if (r0 == 0) goto L_0x005e
            boolean r0 = r7.haveNCharacters(r9, r13)
            if (r0 == 0) goto L_0x005e
            int r0 = r9 + 1
            char r0 = r7.charAt(r0)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isDigit(r0)
            if (r0 == 0) goto L_0x005e
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r14 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
            r4 = 2
            r6 = 0
            r0 = r14
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r14)
            goto L_0x0071
        L_0x005e:
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r14 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.ASCII
            r4 = 1
            r6 = 0
            r0 = r14
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r14)
        L_0x0071:
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r0 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.TEXT
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode[] r14 = new com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode[]{r0, r1}
            r15 = r11
        L_0x007a:
            if (r15 >= r13) goto L_0x00a7
            r2 = r14[r15]
            int[] r0 = new int[r12]
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r1 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.C40
            if (r2 != r1) goto L_0x0086
            r1 = r12
            goto L_0x0087
        L_0x0086:
            r1 = r11
        L_0x0087:
            int r1 = getNumberOfC40Words(r7, r9, r1, r0)
            if (r1 <= 0) goto L_0x00a3
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r6 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            r4 = r0[r11]
            r16 = 0
            r0 = r6
            r1 = r17
            r3 = r19
            r5 = r20
            r11 = r6
            r6 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r11)
        L_0x00a3:
            int r15 = r15 + 1
            r11 = 0
            goto L_0x007a
        L_0x00a7:
            boolean r0 = r7.haveNCharacters(r9, r10)
            if (r0 == 0) goto L_0x00e2
            char r0 = r7.charAt(r9)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isNativeX12(r0)
            if (r0 == 0) goto L_0x00e2
            int r0 = r9 + 1
            char r0 = r7.charAt(r0)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isNativeX12(r0)
            if (r0 == 0) goto L_0x00e2
            int r0 = r9 + 2
            char r0 = r7.charAt(r0)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isNativeX12(r0)
            if (r0 == 0) goto L_0x00e2
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r11 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.X12
            r4 = 3
            r6 = 0
            r0 = r11
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r11)
        L_0x00e2:
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r11 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.B256
            r4 = 1
            r6 = 0
            r0 = r11
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r11)
        L_0x00f5:
            r11 = 0
        L_0x00f6:
            if (r11 >= r10) goto L_0x0120
            int r0 = r9 + r11
            boolean r1 = r7.haveNCharacters(r0, r12)
            if (r1 == 0) goto L_0x0120
            char r0 = r7.charAt(r0)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isNativeEDIFACT(r0)
            if (r0 == 0) goto L_0x0120
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r13 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.EDF
            int r11 = r11 + 1
            r6 = 0
            r0 = r13
            r1 = r17
            r3 = r19
            r4 = r11
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r13)
            goto L_0x00f6
        L_0x0120:
            if (r11 != r10) goto L_0x0148
            r0 = 4
            boolean r0 = r7.haveNCharacters(r9, r0)
            if (r0 == 0) goto L_0x0148
            int r0 = r9 + 3
            char r0 = r7.charAt(r0)
            boolean r0 = com.google.zxing.datamatrix.encoder.HighLevelEncoder.isNativeEDIFACT(r0)
            if (r0 == 0) goto L_0x0148
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge r10 = new com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge
            com.google.zxing.datamatrix.encoder.MinimalEncoder$Mode r2 = com.google.zxing.datamatrix.encoder.MinimalEncoder.Mode.EDF
            r4 = 4
            r6 = 0
            r0 = r10
            r1 = r17
            r3 = r19
            r5 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6)
            addEdge(r8, r10)
        L_0x0148:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.datamatrix.encoder.MinimalEncoder.addEdges(com.google.zxing.datamatrix.encoder.MinimalEncoder$Input, com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge[][], int, com.google.zxing.datamatrix.encoder.MinimalEncoder$Edge):void");
    }

    public static byte[] encode(String str, Charset charset, int i3, SymbolShapeHint symbolShapeHint, int i4) {
        return encodeMinimally(new Input(str, charset, i3, symbolShapeHint, i4, (AnonymousClass1) null)).getBytes();
    }

    public static String encodeHighLevel(String str) {
        return encodeHighLevel(str, (Charset) null, -1, SymbolShapeHint.FORCE_NONE);
    }

    public static Result encodeMinimally(Input input) {
        int length = input.length();
        int[] iArr = new int[2];
        iArr[1] = 6;
        int i3 = 0;
        iArr[0] = length + 1;
        Edge[][] edgeArr = (Edge[][]) Array.newInstance(Edge.class, iArr);
        addEdges(input, edgeArr, 0, (Edge) null);
        for (int i4 = 1; i4 <= length; i4++) {
            for (int i5 = 0; i5 < 6; i5++) {
                Edge edge = edgeArr[i4][i5];
                if (edge != null && i4 < length) {
                    addEdges(input, edgeArr, i4, edge);
                }
            }
            for (int i6 = 0; i6 < 6; i6++) {
                edgeArr[i4 - 1][i6] = null;
            }
        }
        int i7 = -1;
        int i8 = Integer.MAX_VALUE;
        while (i3 < 6) {
            Edge edge2 = edgeArr[length][i3];
            if (edge2 != null) {
                int access$300 = (i3 < 1 || i3 > 3) ? edge2.cachedTotalSize : edge2.cachedTotalSize + 1;
                if (access$300 < i8) {
                    i7 = i3;
                    i8 = access$300;
                }
            }
            i3++;
        }
        if (i7 >= 0) {
            return new Result(edgeArr[length][i7]);
        }
        throw new IllegalStateException("Failed to encode \"" + input + "\"");
    }

    public static int getNumberOfC40Words(Input input, int i3, boolean z2, int[] iArr) {
        int i4 = 0;
        for (int i5 = i3; i5 < input.length(); i5++) {
            if (input.isECI(i5)) {
                iArr[0] = 0;
                return 0;
            }
            char charAt = input.charAt(i5);
            if ((z2 && HighLevelEncoder.isNativeC40(charAt)) || (!z2 && HighLevelEncoder.isNativeText(charAt))) {
                i4++;
            } else if (!isExtendedASCII(charAt, input.getFNC1Character())) {
                i4 += 2;
            } else {
                char c3 = charAt & 255;
                i4 = (c3 < 128 || ((!z2 || !HighLevelEncoder.isNativeC40((char) (c3 + 65408))) && (z2 || !HighLevelEncoder.isNativeText((char) (c3 + 65408))))) ? i4 + 4 : i4 + 3;
            }
            if (i4 % 3 == 0 || ((i4 - 2) % 3 == 0 && i5 + 1 == input.length())) {
                iArr[0] = (i5 - i3) + 1;
                return (int) Math.ceil(((double) i4) / 3.0d);
            }
        }
        iArr[0] = 0;
        return 0;
    }

    public static boolean isExtendedASCII(char c3, int i3) {
        return c3 != i3 && c3 >= 128 && c3 <= 255;
    }

    /* access modifiers changed from: private */
    public static boolean isInC40Shift1Set(char c3) {
        return c3 <= 31;
    }

    /* access modifiers changed from: private */
    public static boolean isInC40Shift2Set(char c3, int i3) {
        for (char c4 : C40_SHIFT2_CHARS) {
            if (c4 == c3) {
                return true;
            }
        }
        return c3 == i3;
    }

    /* access modifiers changed from: private */
    public static boolean isInTextShift1Set(char c3) {
        return isInC40Shift1Set(c3);
    }

    /* access modifiers changed from: private */
    public static boolean isInTextShift2Set(char c3, int i3) {
        return isInC40Shift2Set(c3, i3);
    }

    public static String encodeHighLevel(String str, Charset charset, int i3, SymbolShapeHint symbolShapeHint) {
        int i4;
        if (str.startsWith("[)>\u001e05\u001d") && str.endsWith("\u001e\u0004")) {
            str = b.y(str, 2, 7);
            i4 = 5;
        } else if (!str.startsWith("[)>\u001e06\u001d") || !str.endsWith("\u001e\u0004")) {
            i4 = 0;
        } else {
            str = b.y(str, 2, 7);
            i4 = 6;
        }
        return new String(encode(str, charset, i3, symbolShapeHint, i4), StandardCharsets.ISO_8859_1);
    }
}
