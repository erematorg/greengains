package com.caverock.androidsvg;

class NumberParser {
    private static final float[] negativePowersOf10 = {1.0f, 0.1f, 0.01f, 0.001f, 1.0E-4f, 1.0E-5f, 1.0E-6f, 1.0E-7f, 1.0E-8f, 1.0E-9f, 1.0E-10f, 1.0E-11f, 1.0E-12f, 1.0E-13f, 1.0E-14f, 1.0E-15f, 1.0E-16f, 1.0E-17f, 1.0E-18f, 1.0E-19f, 1.0E-20f, 1.0E-21f, 1.0E-22f, 1.0E-23f, 1.0E-24f, 1.0E-25f, 1.0E-26f, 1.0E-27f, 1.0E-28f, 1.0E-29f, 1.0E-30f, 1.0E-31f, 1.0E-32f, 1.0E-33f, 1.0E-34f, 1.0E-35f, 1.0E-36f, 1.0E-37f, 1.0E-38f};
    private static final float[] positivePowersOf10 = {1.0f, 10.0f, 100.0f, 1000.0f, 10000.0f, 100000.0f, 1000000.0f, 1.0E7f, 1.0E8f, 1.0E9f, 1.0E10f, 9.9999998E10f, 1.0E12f, 9.9999998E12f, 1.0E14f, 9.9999999E14f, 1.00000003E16f, 9.9999998E16f, 9.9999998E17f, 1.0E19f, 1.0E20f, 1.0E21f, 1.0E22f, 1.0E23f, 1.0E24f, 1.0E25f, 1.0E26f, 1.0E27f, 1.0E28f, 1.0E29f, 1.0E30f, 1.0E31f, 1.0E32f, 1.0E33f, 1.0E34f, 1.0E35f, 1.0E36f, 1.0E37f, 1.0E38f};
    private int pos;

    public int getEndPos() {
        return this.pos;
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x008a A[EDGE_INSN: B:108:0x008a->B:39:0x008a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0092 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float parseNumber(java.lang.String r23, int r24, int r25) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r0.pos = r2
            r4 = 2143289344(0x7fc00000, float:NaN)
            if (r2 < r3) goto L_0x000f
            return r4
        L_0x000f:
            char r2 = r23.charAt(r24)
            r5 = 45
            r6 = 43
            r7 = 1
            if (r2 == r6) goto L_0x0020
            if (r2 == r5) goto L_0x001e
            r2 = 0
            goto L_0x0026
        L_0x001e:
            r2 = r7
            goto L_0x0021
        L_0x0020:
            r2 = 0
        L_0x0021:
            int r9 = r0.pos
            int r9 = r9 + r7
            r0.pos = r9
        L_0x0026:
            int r9 = r0.pos
            r10 = 0
            r17 = r10
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0032:
            int r8 = r0.pos
            r5 = 57
            r6 = 48
            r19 = 922337203685477580(0xccccccccccccccc, double:5.1488004017107686E-247)
            if (r8 >= r3) goto L_0x008a
            char r8 = r1.charAt(r8)
            if (r8 != r6) goto L_0x004d
            if (r12 != 0) goto L_0x004a
            int r14 = r14 + 1
            goto L_0x0080
        L_0x004a:
            int r13 = r13 + 1
            goto L_0x0080
        L_0x004d:
            r6 = 49
            if (r8 < r6) goto L_0x0074
            if (r8 > r5) goto L_0x0074
            int r12 = r12 + r13
        L_0x0054:
            r5 = 10
            if (r13 <= 0) goto L_0x0062
            int r21 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r21 <= 0) goto L_0x005d
            return r4
        L_0x005d:
            long r17 = r17 * r5
            int r13 = r13 + -1
            goto L_0x0054
        L_0x0062:
            int r19 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r19 <= 0) goto L_0x0067
            return r4
        L_0x0067:
            long r17 = r17 * r5
            int r8 = r8 + -48
            long r5 = (long) r8
            long r17 = r17 + r5
            int r12 = r12 + r7
            int r5 = (r17 > r10 ? 1 : (r17 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0080
            return r4
        L_0x0074:
            r6 = 46
            if (r8 != r6) goto L_0x008a
            if (r15 == 0) goto L_0x007b
            goto L_0x008a
        L_0x007b:
            int r5 = r0.pos
            int r16 = r5 - r9
            r15 = r7
        L_0x0080:
            int r5 = r0.pos
            int r5 = r5 + r7
            r0.pos = r5
            r5 = 45
            r6 = 43
            goto L_0x0032
        L_0x008a:
            if (r15 == 0) goto L_0x0093
            int r6 = r0.pos
            int r8 = r16 + 1
            if (r6 != r8) goto L_0x0093
            return r4
        L_0x0093:
            if (r12 != 0) goto L_0x0099
            if (r14 != 0) goto L_0x0098
            return r4
        L_0x0098:
            r12 = r7
        L_0x0099:
            if (r15 == 0) goto L_0x009f
            int r16 = r16 - r14
            int r13 = r16 - r12
        L_0x009f:
            int r6 = r0.pos
            if (r6 >= r3) goto L_0x010a
            char r6 = r1.charAt(r6)
            r8 = 69
            if (r6 == r8) goto L_0x00af
            r8 = 101(0x65, float:1.42E-43)
            if (r6 != r8) goto L_0x010a
        L_0x00af:
            int r6 = r0.pos
            int r6 = r6 + r7
            r0.pos = r6
            if (r6 != r3) goto L_0x00b7
            return r4
        L_0x00b7:
            char r6 = r1.charAt(r6)
            r8 = 43
            if (r6 == r8) goto L_0x00d3
            r8 = 45
            if (r6 == r8) goto L_0x00d1
            switch(r6) {
                case 48: goto L_0x00ce;
                case 49: goto L_0x00ce;
                case 50: goto L_0x00ce;
                case 51: goto L_0x00ce;
                case 52: goto L_0x00ce;
                case 53: goto L_0x00ce;
                case 54: goto L_0x00ce;
                case 55: goto L_0x00ce;
                case 56: goto L_0x00ce;
                case 57: goto L_0x00ce;
                default: goto L_0x00c6;
            }
        L_0x00c6:
            int r6 = r0.pos
            int r6 = r6 - r7
            r0.pos = r6
            r8 = r7
            r6 = 0
            goto L_0x00da
        L_0x00ce:
            r6 = 0
        L_0x00cf:
            r8 = 0
            goto L_0x00da
        L_0x00d1:
            r6 = r7
            goto L_0x00d4
        L_0x00d3:
            r6 = 0
        L_0x00d4:
            int r8 = r0.pos
            int r8 = r8 + r7
            r0.pos = r8
            goto L_0x00cf
        L_0x00da:
            if (r8 != 0) goto L_0x010a
            int r8 = r0.pos
            r9 = 0
        L_0x00df:
            int r14 = r0.pos
            if (r14 >= r3) goto L_0x0100
            char r14 = r1.charAt(r14)
            r15 = 48
            if (r14 < r15) goto L_0x0100
            if (r14 > r5) goto L_0x0100
            long r10 = (long) r9
            int r10 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r10 <= 0) goto L_0x00f3
            return r4
        L_0x00f3:
            int r9 = r9 * 10
            int r14 = r14 + -48
            int r9 = r9 + r14
            int r10 = r0.pos
            int r10 = r10 + r7
            r0.pos = r10
            r10 = 0
            goto L_0x00df
        L_0x0100:
            int r0 = r0.pos
            if (r0 != r8) goto L_0x0105
            return r4
        L_0x0105:
            if (r6 == 0) goto L_0x0109
            int r13 = r13 - r9
            goto L_0x010a
        L_0x0109:
            int r13 = r13 + r9
        L_0x010a:
            int r12 = r12 + r13
            r0 = 39
            if (r12 > r0) goto L_0x013f
            r0 = -44
            if (r12 >= r0) goto L_0x0114
            goto L_0x013f
        L_0x0114:
            r10 = r17
            float r0 = (float) r10
            r3 = 0
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x013b
            if (r13 <= 0) goto L_0x0125
            float[] r1 = positivePowersOf10
            r1 = r1[r13]
        L_0x0123:
            float r0 = r0 * r1
            goto L_0x013b
        L_0x0125:
            if (r13 >= 0) goto L_0x013b
            r1 = -38
            if (r13 >= r1) goto L_0x0135
            double r0 = (double) r0
            r3 = 4307583784117748259(0x3bc79ca10c924223, double:1.0E-20)
            double r0 = r0 * r3
            float r0 = (float) r0
            int r13 = r13 + 20
        L_0x0135:
            float[] r1 = negativePowersOf10
            int r3 = -r13
            r1 = r1[r3]
            goto L_0x0123
        L_0x013b:
            if (r2 == 0) goto L_0x013e
            float r0 = -r0
        L_0x013e:
            return r0
        L_0x013f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.caverock.androidsvg.NumberParser.parseNumber(java.lang.String, int, int):float");
    }
}
