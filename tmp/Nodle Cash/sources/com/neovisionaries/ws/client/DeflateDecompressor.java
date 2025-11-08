package com.neovisionaries.ws.client;

class DeflateDecompressor {
    public static void decompress(ByteArray byteArray, ByteArray byteArray2) throws FormatException {
        decompress(byteArray, 0, byteArray2);
    }

    private static void duplicate(int i3, int i4, ByteArray byteArray) {
        int length = byteArray.length();
        byte[] bArr = new byte[i3];
        int i5 = length - i4;
        int i6 = 0;
        int i7 = i5;
        while (i6 < i3) {
            if (length <= i7) {
                i7 = i5;
            }
            bArr[i6] = byteArray.get(i7);
            i6++;
            i7++;
        }
        byteArray.put(bArr);
    }

    private static boolean inflateBlock(ByteArray byteArray, int[] iArr, ByteArray byteArray2) throws FormatException {
        boolean readBit = byteArray.readBit(iArr);
        int readBits = byteArray.readBits(iArr, 2);
        if (readBits == 0) {
            inflatePlainBlock(byteArray, iArr, byteArray2);
        } else if (readBits == 1) {
            inflateFixedBlock(byteArray, iArr, byteArray2);
        } else if (readBits == 2) {
            inflateDynamicBlock(byteArray, iArr, byteArray2);
        } else {
            throw new FormatException(String.format("[%s] Bad compression type '11' at the bit index '%d'.", new Object[]{"DeflateDecompressor", Integer.valueOf(iArr[0])}));
        }
        if (byteArray.length() <= iArr[0] / 8) {
            readBit = true;
        }
        return !readBit;
    }

    private static void inflateData(ByteArray byteArray, int[] iArr, ByteArray byteArray2, Huffman huffman, Huffman huffman2) throws FormatException {
        while (true) {
            int readSym = huffman.readSym(byteArray, iArr);
            if (readSym != 256) {
                if (readSym < 0 || readSym > 255) {
                    duplicate(DeflateUtil.readLength(byteArray, iArr, readSym), DeflateUtil.readDistance(byteArray, iArr, huffman2), byteArray2);
                } else {
                    byteArray2.put(readSym);
                }
            } else {
                return;
            }
        }
    }

    private static void inflateDynamicBlock(ByteArray byteArray, int[] iArr, ByteArray byteArray2) throws FormatException {
        Huffman[] huffmanArr = new Huffman[2];
        DeflateUtil.readDynamicTables(byteArray, iArr, huffmanArr);
        inflateData(byteArray, iArr, byteArray2, huffmanArr[0], huffmanArr[1]);
    }

    private static void inflateFixedBlock(ByteArray byteArray, int[] iArr, ByteArray byteArray2) throws FormatException {
        inflateData(byteArray, iArr, byteArray2, FixedLiteralLengthHuffman.getInstance(), FixedDistanceHuffman.getInstance());
    }

    private static void inflatePlainBlock(ByteArray byteArray, int[] iArr, ByteArray byteArray2) {
        int i3 = ((iArr[0] + 7) & -8) / 8;
        int i4 = ((byteArray.get(i3 + 1) & 255) * 256) + (byteArray.get(i3) & 255);
        int i5 = i3 + 4;
        byteArray2.put(byteArray, i5, i4);
        iArr[0] = (i5 + i4) * 8;
    }

    private static void decompress(ByteArray byteArray, int i3, ByteArray byteArray2) throws FormatException {
        do {
        } while (inflateBlock(byteArray, new int[]{i3 * 8}, byteArray2));
    }
}
