package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP;
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final int MODE_DIGIT = 2;
    static final int MODE_LOWER = 1;
    static final int MODE_MIXED = 3;
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int MODE_PUNCT = 4;
    static final int MODE_UPPER = 0;
    static final int[][] SHIFT_TABLE;
    private final Charset charset;
    private final byte[] text;

    static {
        int[] iArr = new int[2];
        iArr[1] = 256;
        iArr[0] = 5;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        CHAR_MAP = iArr2;
        iArr2[0][32] = 1;
        for (int i3 = 65; i3 <= 90; i3++) {
            CHAR_MAP[0][i3] = i3 - 63;
        }
        CHAR_MAP[1][32] = 1;
        for (int i4 = 97; i4 <= 122; i4++) {
            CHAR_MAP[1][i4] = i4 - 95;
        }
        CHAR_MAP[2][32] = 1;
        for (int i5 = 48; i5 <= 57; i5++) {
            CHAR_MAP[2][i5] = i5 - 46;
        }
        int[] iArr3 = CHAR_MAP[2];
        iArr3[44] = 12;
        iArr3[46] = 13;
        int[] iArr4 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i6 = 0; i6 < 28; i6++) {
            CHAR_MAP[3][iArr4[i6]] = i6;
        }
        int[] iArr5 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i7 = 0; i7 < 31; i7++) {
            int i8 = iArr5[i7];
            if (i8 > 0) {
                CHAR_MAP[4][i8] = i7;
            }
        }
        int[] iArr6 = new int[2];
        iArr6[1] = 6;
        iArr6[0] = 6;
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, iArr6);
        SHIFT_TABLE = iArr7;
        for (int[] fill : iArr7) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr8 = SHIFT_TABLE;
        iArr8[0][4] = 0;
        int[] iArr9 = iArr8[1];
        iArr9[4] = 0;
        iArr9[0] = 28;
        iArr8[3][4] = 0;
        int[] iArr10 = iArr8[2];
        iArr10[4] = 0;
        iArr10[0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.text = bArr;
        this.charset = null;
    }

    private static Collection<State> simplifyStates(Iterable<State> iterable) {
        LinkedList linkedList = new LinkedList();
        for (State next : iterable) {
            Iterator it = linkedList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    linkedList.addFirst(next);
                    break;
                }
                State state = (State) it.next();
                if (state.isBetterThanOrEqualTo(next)) {
                    break;
                } else if (next.isBetterThanOrEqualTo(state)) {
                    it.remove();
                }
            }
        }
        return linkedList;
    }

    private void updateStateForChar(State state, int i3, Collection<State> collection) {
        char c3 = (char) (this.text[i3] & 255);
        boolean z2 = CHAR_MAP[state.getMode()][c3] > 0;
        State state2 = null;
        for (int i4 = 0; i4 <= 4; i4++) {
            int i5 = CHAR_MAP[i4][c3];
            if (i5 > 0) {
                if (state2 == null) {
                    state2 = state.endBinaryShift(i3);
                }
                if (!z2 || i4 == state.getMode() || i4 == 2) {
                    collection.add(state2.latchAndAppend(i4, i5));
                }
                if (!z2 && SHIFT_TABLE[state.getMode()][i4] >= 0) {
                    collection.add(state2.shiftAndAppend(i4, i5));
                }
            }
        }
        if (state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][c3] == 0) {
            collection.add(state.addBinaryShiftChar(i3));
        }
    }

    private static void updateStateForPair(State state, int i3, int i4, Collection<State> collection) {
        State endBinaryShift = state.endBinaryShift(i3);
        collection.add(endBinaryShift.latchAndAppend(4, i4));
        if (state.getMode() != 4) {
            collection.add(endBinaryShift.shiftAndAppend(4, i4));
        }
        if (i4 == 3 || i4 == 4) {
            collection.add(endBinaryShift.latchAndAppend(2, 16 - i4).latchAndAppend(2, 1));
        }
        if (state.getBinaryShiftByteCount() > 0) {
            collection.add(state.addBinaryShiftChar(i3).addBinaryShiftChar(i3 + 1));
        }
    }

    private Collection<State> updateStateListForChar(Iterable<State> iterable, int i3) {
        LinkedList linkedList = new LinkedList();
        for (State updateStateForChar : iterable) {
            updateStateForChar(updateStateForChar, i3, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private static Collection<State> updateStateListForPair(Iterable<State> iterable, int i3, int i4) {
        LinkedList linkedList = new LinkedList();
        for (State updateStateForPair : iterable) {
            updateStateForPair(updateStateForPair, i3, i4, linkedList);
        }
        return simplifyStates(linkedList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.common.BitArray encode() {
        /*
            r8 = this;
            com.google.zxing.aztec.encoder.State r0 = com.google.zxing.aztec.encoder.State.INITIAL_STATE
            java.nio.charset.Charset r1 = r8.charset
            if (r1 == 0) goto L_0x002b
            com.google.zxing.common.CharacterSetECI r1 = com.google.zxing.common.CharacterSetECI.getCharacterSetECI(r1)
            if (r1 == 0) goto L_0x0015
            int r1 = r1.getValue()
            com.google.zxing.aztec.encoder.State r0 = r0.appendFLGn(r1)
            goto L_0x002b
        L_0x0015:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "No ECI code for character set "
            r1.<init>(r2)
            java.nio.charset.Charset r8 = r8.charset
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x002b:
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = r1
        L_0x0031:
            byte[] r3 = r8.text
            int r4 = r3.length
            if (r2 >= r4) goto L_0x0075
            int r4 = r2 + 1
            int r5 = r3.length
            if (r4 >= r5) goto L_0x003e
            byte r5 = r3[r4]
            goto L_0x003f
        L_0x003e:
            r5 = r1
        L_0x003f:
            byte r3 = r3[r2]
            r6 = 13
            if (r3 == r6) goto L_0x0061
            r6 = 44
            r7 = 32
            if (r3 == r6) goto L_0x005d
            r6 = 46
            if (r3 == r6) goto L_0x0059
            r6 = 58
            if (r3 == r6) goto L_0x0055
        L_0x0053:
            r3 = r1
            goto L_0x0066
        L_0x0055:
            if (r5 != r7) goto L_0x0053
            r3 = 5
            goto L_0x0066
        L_0x0059:
            if (r5 != r7) goto L_0x0053
            r3 = 3
            goto L_0x0066
        L_0x005d:
            if (r5 != r7) goto L_0x0053
            r3 = 4
            goto L_0x0066
        L_0x0061:
            r3 = 10
            if (r5 != r3) goto L_0x0053
            r3 = 2
        L_0x0066:
            if (r3 <= 0) goto L_0x006e
            java.util.Collection r0 = updateStateListForPair(r0, r2, r3)
            r2 = r4
            goto L_0x0072
        L_0x006e:
            java.util.Collection r0 = r8.updateStateListForChar(r0, r2)
        L_0x0072:
            int r2 = r2 + 1
            goto L_0x0031
        L_0x0075:
            com.google.zxing.aztec.encoder.HighLevelEncoder$1 r1 = new com.google.zxing.aztec.encoder.HighLevelEncoder$1
            r1.<init>()
            java.lang.Object r0 = java.util.Collections.min(r0, r1)
            com.google.zxing.aztec.encoder.State r0 = (com.google.zxing.aztec.encoder.State) r0
            byte[] r8 = r8.text
            com.google.zxing.common.BitArray r8 = r0.toBitArray(r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }

    public HighLevelEncoder(byte[] bArr, Charset charset2) {
        this.text = bArr;
        this.charset = charset2;
    }
}
