package com.neovisionaries.ws.client;

class FixedDistanceHuffman extends Huffman {
    private static final FixedDistanceHuffman INSTANCE = new FixedDistanceHuffman();

    private FixedDistanceHuffman() {
        super(buildCodeLensFromSym());
    }

    private static int[] buildCodeLensFromSym() {
        int[] iArr = new int[32];
        for (int i3 = 0; i3 < 32; i3++) {
            iArr[i3] = 5;
        }
        return iArr;
    }

    public static FixedDistanceHuffman getInstance() {
        return INSTANCE;
    }
}
