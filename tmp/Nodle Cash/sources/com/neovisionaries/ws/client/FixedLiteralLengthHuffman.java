package com.neovisionaries.ws.client;

class FixedLiteralLengthHuffman extends Huffman {
    private static final FixedLiteralLengthHuffman INSTANCE = new FixedLiteralLengthHuffman();

    private FixedLiteralLengthHuffman() {
        super(buildCodeLensFromSym());
    }

    private static int[] buildCodeLensFromSym() {
        int[] iArr = new int[288];
        int i3 = 0;
        while (i3 < 144) {
            iArr[i3] = 8;
            i3++;
        }
        while (i3 < 256) {
            iArr[i3] = 9;
            i3++;
        }
        while (i3 < 280) {
            iArr[i3] = 7;
            i3++;
        }
        while (i3 < 288) {
            iArr[i3] = 8;
            i3++;
        }
        return iArr;
    }

    public static FixedLiteralLengthHuffman getInstance() {
        return INSTANCE;
    }
}
