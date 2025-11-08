package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.msgpack.core.MessagePack;

final class State {
    static final State INITIAL_STATE = new State(Token.EMPTY, 0, 0, 0);
    private final int binaryShiftByteCount;
    private final int binaryShiftCost;
    private final int bitCount;
    private final int mode;
    private final Token token;

    private State(Token token2, int i3, int i4, int i5) {
        this.token = token2;
        this.mode = i3;
        this.binaryShiftByteCount = i4;
        this.bitCount = i5;
        this.binaryShiftCost = calculateBinaryShiftCost(i4);
    }

    private static int calculateBinaryShiftCost(int i3) {
        if (i3 > 62) {
            return 21;
        }
        if (i3 > 31) {
            return 20;
        }
        return i3 > 0 ? 10 : 0;
    }

    public State addBinaryShiftChar(int i3) {
        Token token2 = this.token;
        int i4 = this.mode;
        int i5 = this.bitCount;
        if (i4 == 4 || i4 == 2) {
            int i6 = HighLevelEncoder.LATCH_TABLE[i4][0];
            int i7 = 65535 & i6;
            int i8 = i6 >> 16;
            token2 = token2.add(i7, i8);
            i5 += i8;
            i4 = 0;
        }
        int i9 = this.binaryShiftByteCount;
        State state = new State(token2, i4, i9 + 1, i5 + ((i9 == 0 || i9 == 31) ? 18 : i9 == 62 ? 9 : 8));
        return state.binaryShiftByteCount == 2078 ? state.endBinaryShift(i3 + 1) : state;
    }

    public State appendFLGn(int i3) {
        Token token2;
        Token token3 = shiftAndAppend(4, 0).token;
        int i4 = 3;
        if (i3 < 0) {
            token2 = token3.add(0, 3);
        } else if (i3 <= 999999) {
            byte[] bytes = Integer.toString(i3).getBytes(StandardCharsets.ISO_8859_1);
            Token add = token3.add(bytes.length, 3);
            for (byte b3 : bytes) {
                add = add.add(b3 + MessagePack.Code.INT32, 4);
            }
            i4 = 3 + (bytes.length * 4);
            token2 = add;
        } else {
            throw new IllegalArgumentException("ECI code must be between 0 and 999999");
        }
        return new State(token2, this.mode, 0, this.bitCount + i4);
    }

    public State endBinaryShift(int i3) {
        int i4 = this.binaryShiftByteCount;
        return i4 == 0 ? this : new State(this.token.addBinaryShift(i3 - i4, i4), this.mode, 0, this.bitCount);
    }

    public int getBinaryShiftByteCount() {
        return this.binaryShiftByteCount;
    }

    public int getBitCount() {
        return this.bitCount;
    }

    public int getMode() {
        return this.mode;
    }

    public Token getToken() {
        return this.token;
    }

    public boolean isBetterThanOrEqualTo(State state) {
        int i3 = this.bitCount + (HighLevelEncoder.LATCH_TABLE[this.mode][state.mode] >> 16);
        int i4 = this.binaryShiftByteCount;
        int i5 = state.binaryShiftByteCount;
        if (i4 < i5) {
            i3 += state.binaryShiftCost - this.binaryShiftCost;
        } else if (i4 > i5 && i5 > 0) {
            i3 += 10;
        }
        return i3 <= state.bitCount;
    }

    public State latchAndAppend(int i3, int i4) {
        int i5 = this.bitCount;
        Token token2 = this.token;
        int i6 = this.mode;
        if (i3 != i6) {
            int i7 = HighLevelEncoder.LATCH_TABLE[i6][i3];
            int i8 = 65535 & i7;
            int i9 = i7 >> 16;
            token2 = token2.add(i8, i9);
            i5 += i9;
        }
        int i10 = i3 == 2 ? 4 : 5;
        return new State(token2.add(i4, i10), i3, 0, i5 + i10);
    }

    public State shiftAndAppend(int i3, int i4) {
        Token token2 = this.token;
        int i5 = this.mode;
        int i6 = i5 == 2 ? 4 : 5;
        return new State(token2.add(HighLevelEncoder.SHIFT_TABLE[i5][i3], i6).add(i4, 5), this.mode, 0, this.bitCount + i6 + 5);
    }

    public BitArray toBitArray(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        for (Token token2 = endBinaryShift(bArr.length).token; token2 != null; token2 = token2.getPrevious()) {
            arrayList.add(token2);
        }
        BitArray bitArray = new BitArray();
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((Token) arrayList.get(size)).appendTo(bitArray, bArr);
        }
        return bitArray;
    }

    public String toString() {
        return String.format("%s bits=%d bytes=%d", new Object[]{HighLevelEncoder.MODE_NAMES[this.mode], Integer.valueOf(this.bitCount), Integer.valueOf(this.binaryShiftByteCount)});
    }
}
