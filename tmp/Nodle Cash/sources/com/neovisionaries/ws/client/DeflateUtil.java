package com.neovisionaries.ws.client;

class DeflateUtil {
    private static int[] INDICES_FROM_CODE_LENGTH_ORDER = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};

    private static int codeLengthOrderToIndex(int i3) {
        return INDICES_FROM_CODE_LENGTH_ORDER[i3];
    }

    private static void readCodeLengths(ByteArray byteArray, int[] iArr, int[] iArr2, Huffman huffman) throws FormatException {
        int i3;
        int i4;
        int readBits;
        int i5 = 0;
        while (i5 < iArr2.length) {
            int readSym = huffman.readSym(byteArray, iArr);
            if (readSym < 0 || readSym > 15) {
                switch (readSym) {
                    case 16:
                        i4 = iArr2[i5 - 1];
                        i3 = 3 + byteArray.readBits(iArr, 2);
                        break;
                    case 17:
                        readBits = byteArray.readBits(iArr, 3) + 3;
                        break;
                    case 18:
                        readBits = byteArray.readBits(iArr, 7) + 11;
                        break;
                    default:
                        throw new FormatException(String.format("[%s] Bad code length '%d' at the bit index '%d'.", new Object[]{"DeflateUtil", Integer.valueOf(readSym), iArr}));
                }
                i3 = readBits;
                i4 = 0;
                for (int i6 = 0; i6 < i3; i6++) {
                    iArr2[i5 + i6] = i4;
                }
                i5 += i3 - 1;
            } else {
                iArr2[i5] = readSym;
            }
            i5++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0049, code lost:
        r0 = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
        r0 = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0058, code lost:
        r0 = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005f, code lost:
        r0 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        r0 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        r0 = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0074, code lost:
        r0 = 5;
        r9 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007c, code lost:
        r0 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0083, code lost:
        r0 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0095, code lost:
        return r9 + r13.readBits(r14, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0039, code lost:
        r0 = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0041, code lost:
        r0 = 12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int readDistance(com.neovisionaries.ws.client.ByteArray r13, int[] r14, com.neovisionaries.ws.client.Huffman r15) throws com.neovisionaries.ws.client.FormatException {
        /*
            int r15 = r15.readSym(r13, r14)
            r0 = 2
            r1 = 3
            r2 = 4
            r3 = 6
            r4 = 8
            r5 = 10
            r6 = 11
            r7 = 12
            r8 = 1
            r9 = 5
            r10 = 7
            r11 = 9
            r12 = 13
            switch(r15) {
                case 0: goto L_0x0096;
                case 1: goto L_0x0096;
                case 2: goto L_0x0096;
                case 3: goto L_0x0096;
                case 4: goto L_0x008f;
                case 5: goto L_0x008c;
                case 6: goto L_0x008a;
                case 7: goto L_0x0088;
                case 8: goto L_0x0085;
                case 9: goto L_0x0081;
                case 10: goto L_0x007e;
                case 11: goto L_0x007a;
                case 12: goto L_0x0077;
                case 13: goto L_0x0072;
                case 14: goto L_0x006f;
                case 15: goto L_0x006b;
                case 16: goto L_0x0068;
                case 17: goto L_0x0064;
                case 18: goto L_0x0061;
                case 19: goto L_0x005d;
                case 20: goto L_0x005a;
                case 21: goto L_0x0056;
                case 22: goto L_0x0053;
                case 23: goto L_0x004f;
                case 24: goto L_0x004c;
                case 25: goto L_0x0047;
                case 26: goto L_0x0044;
                case 27: goto L_0x003f;
                case 28: goto L_0x003c;
                case 29: goto L_0x0037;
                default: goto L_0x001a;
            }
        L_0x001a:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r15)
            r15 = 0
            r14 = r14[r15]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            java.lang.String r15 = "DeflateUtil"
            java.lang.Object[] r13 = new java.lang.Object[]{r15, r13, r14}
            java.lang.String r14 = "[%s] Bad distance code '%d' at the bit index '%d'."
            java.lang.String r13 = java.lang.String.format(r14, r13)
            com.neovisionaries.ws.client.FormatException r14 = new com.neovisionaries.ws.client.FormatException
            r14.<init>(r13)
            throw r14
        L_0x0037:
            r9 = 24577(0x6001, float:3.444E-41)
        L_0x0039:
            r0 = r12
            goto L_0x0090
        L_0x003c:
            r9 = 16385(0x4001, float:2.296E-41)
            goto L_0x0039
        L_0x003f:
            r9 = 12289(0x3001, float:1.722E-41)
        L_0x0041:
            r0 = r7
            goto L_0x0090
        L_0x0044:
            r9 = 8193(0x2001, float:1.1481E-41)
            goto L_0x0041
        L_0x0047:
            r9 = 6145(0x1801, float:8.611E-42)
        L_0x0049:
            r0 = r6
            goto L_0x0090
        L_0x004c:
            r9 = 4097(0x1001, float:5.741E-42)
            goto L_0x0049
        L_0x004f:
            r9 = 3073(0xc01, float:4.306E-42)
        L_0x0051:
            r0 = r5
            goto L_0x0090
        L_0x0053:
            r9 = 2049(0x801, float:2.871E-42)
            goto L_0x0051
        L_0x0056:
            r9 = 1537(0x601, float:2.154E-42)
        L_0x0058:
            r0 = r11
            goto L_0x0090
        L_0x005a:
            r9 = 1025(0x401, float:1.436E-42)
            goto L_0x0058
        L_0x005d:
            r9 = 769(0x301, float:1.078E-42)
        L_0x005f:
            r0 = r4
            goto L_0x0090
        L_0x0061:
            r9 = 513(0x201, float:7.19E-43)
            goto L_0x005f
        L_0x0064:
            r9 = 385(0x181, float:5.4E-43)
        L_0x0066:
            r0 = r10
            goto L_0x0090
        L_0x0068:
            r9 = 257(0x101, float:3.6E-43)
            goto L_0x0066
        L_0x006b:
            r9 = 193(0xc1, float:2.7E-43)
        L_0x006d:
            r0 = r3
            goto L_0x0090
        L_0x006f:
            r9 = 129(0x81, float:1.81E-43)
            goto L_0x006d
        L_0x0072:
            r15 = 97
        L_0x0074:
            r0 = r9
            r9 = r15
            goto L_0x0090
        L_0x0077:
            r15 = 65
            goto L_0x0074
        L_0x007a:
            r9 = 49
        L_0x007c:
            r0 = r2
            goto L_0x0090
        L_0x007e:
            r9 = 33
            goto L_0x007c
        L_0x0081:
            r9 = 25
        L_0x0083:
            r0 = r1
            goto L_0x0090
        L_0x0085:
            r9 = 17
            goto L_0x0083
        L_0x0088:
            r9 = r12
            goto L_0x0090
        L_0x008a:
            r9 = r11
            goto L_0x0090
        L_0x008c:
            r0 = r8
            r9 = r10
            goto L_0x0090
        L_0x008f:
            r0 = r8
        L_0x0090:
            int r13 = r13.readBits(r14, r0)
            int r9 = r9 + r13
            return r9
        L_0x0096:
            int r15 = r15 + r8
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.DeflateUtil.readDistance(com.neovisionaries.ws.client.ByteArray, int[], com.neovisionaries.ws.client.Huffman):int");
    }

    public static void readDynamicTables(ByteArray byteArray, int[] iArr, Huffman[] huffmanArr) throws FormatException {
        int readBits = byteArray.readBits(iArr, 5) + 257;
        int readBits2 = byteArray.readBits(iArr, 5) + 1;
        int readBits3 = byteArray.readBits(iArr, 4) + 4;
        int[] iArr2 = new int[19];
        for (int i3 = 0; i3 < readBits3; i3++) {
            iArr2[codeLengthOrderToIndex(i3)] = (byte) byteArray.readBits(iArr, 3);
        }
        Huffman huffman = new Huffman(iArr2);
        int[] iArr3 = new int[readBits];
        readCodeLengths(byteArray, iArr, iArr3, huffman);
        Huffman huffman2 = new Huffman(iArr3);
        int[] iArr4 = new int[readBits2];
        readCodeLengths(byteArray, iArr, iArr4, huffman);
        Huffman huffman3 = new Huffman(iArr4);
        huffmanArr[0] = huffman2;
        huffmanArr[1] = huffman3;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        r0 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r0 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        r0 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006c, code lost:
        return r7 + r5.readBits(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002a, code lost:
        r0 = 5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int readLength(com.neovisionaries.ws.client.ByteArray r5, int[] r6, int r7) throws com.neovisionaries.ws.client.FormatException {
        /*
            r0 = 1
            r1 = 2
            r2 = 3
            r3 = 4
            r4 = 5
            switch(r7) {
                case 257: goto L_0x006d;
                case 258: goto L_0x006d;
                case 259: goto L_0x006d;
                case 260: goto L_0x006d;
                case 261: goto L_0x006d;
                case 262: goto L_0x006d;
                case 263: goto L_0x006d;
                case 264: goto L_0x006d;
                case 265: goto L_0x0065;
                case 266: goto L_0x0062;
                case 267: goto L_0x005f;
                case 268: goto L_0x005c;
                case 269: goto L_0x0059;
                case 270: goto L_0x0056;
                case 271: goto L_0x0053;
                case 272: goto L_0x004f;
                case 273: goto L_0x004c;
                case 274: goto L_0x0049;
                case 275: goto L_0x0046;
                case 276: goto L_0x0042;
                case 277: goto L_0x003f;
                case 278: goto L_0x003c;
                case 279: goto L_0x0039;
                case 280: goto L_0x0035;
                case 281: goto L_0x0032;
                case 282: goto L_0x002f;
                case 283: goto L_0x002c;
                case 284: goto L_0x0028;
                case 285: goto L_0x0025;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)
            r7 = 0
            r6 = r6[r7]
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r7 = "DeflateUtil"
            java.lang.Object[] r5 = new java.lang.Object[]{r7, r5, r6}
            java.lang.String r6 = "[%s] Bad literal/length code '%d' at the bit index '%d'."
            java.lang.String r5 = java.lang.String.format(r6, r5)
            com.neovisionaries.ws.client.FormatException r6 = new com.neovisionaries.ws.client.FormatException
            r6.<init>(r5)
            throw r6
        L_0x0025:
            r5 = 258(0x102, float:3.62E-43)
            return r5
        L_0x0028:
            r7 = 227(0xe3, float:3.18E-43)
        L_0x002a:
            r0 = r4
            goto L_0x0067
        L_0x002c:
            r7 = 195(0xc3, float:2.73E-43)
            goto L_0x002a
        L_0x002f:
            r7 = 163(0xa3, float:2.28E-43)
            goto L_0x002a
        L_0x0032:
            r7 = 131(0x83, float:1.84E-43)
            goto L_0x002a
        L_0x0035:
            r7 = 115(0x73, float:1.61E-43)
        L_0x0037:
            r0 = r3
            goto L_0x0067
        L_0x0039:
            r7 = 99
            goto L_0x0037
        L_0x003c:
            r7 = 83
            goto L_0x0037
        L_0x003f:
            r7 = 67
            goto L_0x0037
        L_0x0042:
            r7 = 59
        L_0x0044:
            r0 = r2
            goto L_0x0067
        L_0x0046:
            r7 = 51
            goto L_0x0044
        L_0x0049:
            r7 = 43
            goto L_0x0044
        L_0x004c:
            r7 = 35
            goto L_0x0044
        L_0x004f:
            r7 = 31
        L_0x0051:
            r0 = r1
            goto L_0x0067
        L_0x0053:
            r7 = 27
            goto L_0x0051
        L_0x0056:
            r7 = 23
            goto L_0x0051
        L_0x0059:
            r7 = 19
            goto L_0x0051
        L_0x005c:
            r7 = 17
            goto L_0x0067
        L_0x005f:
            r7 = 15
            goto L_0x0067
        L_0x0062:
            r7 = 13
            goto L_0x0067
        L_0x0065:
            r7 = 11
        L_0x0067:
            int r5 = r5.readBits(r6, r0)
            int r7 = r7 + r5
            return r7
        L_0x006d:
            int r7 = r7 + -254
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neovisionaries.ws.client.DeflateUtil.readLength(com.neovisionaries.ws.client.ByteArray, int[], int):int");
    }
}
