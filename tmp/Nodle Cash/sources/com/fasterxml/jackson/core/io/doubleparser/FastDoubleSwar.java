package com.fasterxml.jackson.core.io.doubleparser;

class FastDoubleSwar {
    public static long readLongFromByteArrayBigEndian(byte[] bArr, int i3) {
        return (((long) bArr[i3 + 7]) & 255) | ((((long) bArr[i3]) & 255) << 56) | ((((long) bArr[i3 + 1]) & 255) << 48) | ((((long) bArr[i3 + 2]) & 255) << 40) | ((((long) bArr[i3 + 3]) & 255) << 32) | ((((long) bArr[i3 + 4]) & 255) << 24) | ((((long) bArr[i3 + 5]) & 255) << 16) | ((((long) bArr[i3 + 6]) & 255) << 8);
    }

    public static long readLongFromByteArrayLittleEndian(byte[] bArr, int i3) {
        return (((long) bArr[i3]) & 255) | ((((long) bArr[i3 + 7]) & 255) << 56) | ((((long) bArr[i3 + 6]) & 255) << 48) | ((((long) bArr[i3 + 5]) & 255) << 40) | ((((long) bArr[i3 + 4]) & 255) << 32) | ((((long) bArr[i3 + 3]) & 255) << 24) | ((((long) bArr[i3 + 2]) & 255) << 16) | ((((long) bArr[i3 + 1]) & 255) << 8);
    }

    public static int tryToParseEightDigitsUtf16(long j2, long j3) {
        long j4 = j2 - 13511005043687472L;
        long j5 = j3 - 13511005043687472L;
        if ((((j2 + 19703549022044230L) | j4 | (j3 + 19703549022044230L) | j5) & -35747867511423104L) != 0) {
            return -1;
        }
        return (((int) ((j4 * 281475406208040961L) >>> 48)) * 10000) + ((int) ((j5 * 281475406208040961L) >>> 48));
    }

    public static int tryToParseEightDigitsUtf8(long j2) {
        long j3 = j2 - 3472328296227680304L;
        if ((((j2 + 5063812098665367110L) | j3) & -9187201950435737472L) != 0) {
            return -1;
        }
        long j4 = j3 * 2561;
        return (int) (((((j4 >>> 24) & 1095216660735L) * 42949672960001L) + (((j4 >>> 8) & 1095216660735L) * 4294967296000100L)) >>> 32);
    }

    public static long tryToParseEightHexDigitsUtf16(char[] cArr, int i3) {
        return tryToParseEightHexDigitsUtf16((((long) cArr[i3]) << 48) | (((long) cArr[i3 + 1]) << 32) | (((long) cArr[i3 + 2]) << 16) | ((long) cArr[i3 + 3]), ((long) cArr[i3 + 7]) | (((long) cArr[i3 + 4]) << 48) | (((long) cArr[i3 + 5]) << 32) | (((long) cArr[i3 + 6]) << 16));
    }

    public static long tryToParseEightHexDigitsUtf8(long j2) {
        long j3 = j2 - 3472328296227680304L;
        long j4 = (j2 - -5063812098665367110L) & -9187201950435737472L;
        if (j4 != ((j2 - -2242545357980376863L) & -9187201950435737472L & ((9187201950435737471L ^ j3) + 3978709506094217015L))) {
            return -1;
        }
        long j5 = (j4 >>> 7) * 255;
        long j6 = ((~j5) & j3) | (j3 - (j5 & 2821266740684990247L));
        long j7 = (j6 | (j6 >>> 4)) & 71777214294589695L;
        long j8 = j7 | (j7 >>> 8);
        return (j8 & 65535) | ((j8 >>> 16) & 4294901760L);
    }

    public static long tryToParseFourHexDigitsUtf16(long j2) {
        long j3 = j2 - 13511005043687472L;
        long j4 = (j2 - -9207186978729525190L) & -9223231297218904064L;
        if (j4 != ((j2 - -9196209287131529119L) & -9223231297218904064L & ((9223231297218904063L ^ j3) + 15481359945891895L))) {
            return -1;
        }
        long j5 = (j4 >>> 15) * 65535;
        long j6 = ((~j5) & j3) | (j3 - (j5 & 10977691597996071L));
        long j7 = j6 | (j6 >>> 12);
        return (j7 | (j7 >>> 24)) & 65535;
    }

    public static int tryToParseEightDigitsUtf16(char[] cArr, int i3) {
        return tryToParseEightDigitsUtf16(((long) cArr[i3]) | (((long) cArr[i3 + 1]) << 16) | (((long) cArr[i3 + 2]) << 32) | (((long) cArr[i3 + 3]) << 48), (((long) cArr[i3 + 7]) << 48) | ((long) cArr[i3 + 4]) | (((long) cArr[i3 + 5]) << 16) | (((long) cArr[i3 + 6]) << 32));
    }

    public static int tryToParseEightDigitsUtf8(byte[] bArr, int i3) {
        return tryToParseEightDigitsUtf8(readLongFromByteArrayLittleEndian(bArr, i3));
    }

    public static long tryToParseEightHexDigitsUtf8(byte[] bArr, int i3) {
        return tryToParseEightHexDigitsUtf8(readLongFromByteArrayBigEndian(bArr, i3));
    }

    public static long tryToParseEightHexDigitsUtf16(long j2, long j3) {
        return (tryToParseFourHexDigitsUtf16(j2) << 16) | tryToParseFourHexDigitsUtf16(j3);
    }
}
