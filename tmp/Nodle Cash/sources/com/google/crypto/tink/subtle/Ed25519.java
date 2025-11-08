package com.google.crypto.tink.subtle;

import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import org.msgpack.core.MessagePack;

final class Ed25519 {
    private static final CachedXYT CACHED_NEUTRAL = new CachedXYT(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    static final byte[] GROUP_ORDER = {-19, MessagePack.Code.INT64, CBORConstants.BYTE_TRUE, 92, Ascii.SUB, 99, Ascii.DC2, 88, MessagePack.Code.FIXEXT4, -100, -9, -94, MessagePack.Code.MAP16, -7, MessagePack.Code.MAP16, Ascii.DC4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};
    private static final PartialXYZT NEUTRAL = new PartialXYZT(new XYZ(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SECRET_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;

    public static class CachedXYT {
        final long[] t2d;
        final long[] yMinusX;
        final long[] yPlusX;

        public CachedXYT() {
            this(new long[10], new long[10], new long[10]);
        }

        public void copyConditional(CachedXYT cachedXYT, int i3) {
            Curve25519.copyConditional(this.yPlusX, cachedXYT.yPlusX, i3);
            Curve25519.copyConditional(this.yMinusX, cachedXYT.yMinusX, i3);
            Curve25519.copyConditional(this.t2d, cachedXYT.t2d, i3);
        }

        public void multByZ(long[] jArr, long[] jArr2) {
            System.arraycopy(jArr2, 0, jArr, 0, 10);
        }

        public CachedXYT(long[] jArr, long[] jArr2, long[] jArr3) {
            this.yPlusX = jArr;
            this.yMinusX = jArr2;
            this.t2d = jArr3;
        }

        public CachedXYT(CachedXYT cachedXYT) {
            this.yPlusX = Arrays.copyOf(cachedXYT.yPlusX, 10);
            this.yMinusX = Arrays.copyOf(cachedXYT.yMinusX, 10);
            this.t2d = Arrays.copyOf(cachedXYT.t2d, 10);
        }
    }

    public static class CachedXYZT extends CachedXYT {

        /* renamed from: z  reason: collision with root package name */
        private final long[] f7026z;

        public CachedXYZT() {
            this(new long[10], new long[10], new long[10], new long[10]);
        }

        public void multByZ(long[] jArr, long[] jArr2) {
            Field25519.mult(jArr, jArr2, this.f7026z);
        }

        public CachedXYZT(XYZT xyzt) {
            this();
            long[] jArr = this.yPlusX;
            XYZ xyz = xyzt.xyz;
            Field25519.sum(jArr, xyz.f7029y, xyz.f7028x);
            long[] jArr2 = this.yMinusX;
            XYZ xyz2 = xyzt.xyz;
            Field25519.sub(jArr2, xyz2.f7029y, xyz2.f7028x);
            System.arraycopy(xyzt.xyz.f7030z, 0, this.f7026z, 0, 10);
            Field25519.mult(this.t2d, xyzt.f7031t, Ed25519Constants.D2);
        }

        public CachedXYZT(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
            super(jArr, jArr2, jArr4);
            this.f7026z = jArr3;
        }
    }

    public static class PartialXYZT {

        /* renamed from: t  reason: collision with root package name */
        final long[] f7027t;
        final XYZ xyz;

        public PartialXYZT() {
            this(new XYZ(), new long[10]);
        }

        public PartialXYZT(XYZ xyz2, long[] jArr) {
            this.xyz = xyz2;
            this.f7027t = jArr;
        }

        public PartialXYZT(PartialXYZT partialXYZT) {
            this.xyz = new XYZ(partialXYZT.xyz);
            this.f7027t = Arrays.copyOf(partialXYZT.f7027t, 10);
        }
    }

    public static class XYZ {

        /* renamed from: x  reason: collision with root package name */
        final long[] f7028x;

        /* renamed from: y  reason: collision with root package name */
        final long[] f7029y;

        /* renamed from: z  reason: collision with root package name */
        final long[] f7030z;

        public XYZ() {
            this(new long[10], new long[10], new long[10]);
        }

        @CanIgnoreReturnValue
        public static XYZ fromPartialXYZT(XYZ xyz, PartialXYZT partialXYZT) {
            Field25519.mult(xyz.f7028x, partialXYZT.xyz.f7028x, partialXYZT.f7027t);
            long[] jArr = xyz.f7029y;
            XYZ xyz2 = partialXYZT.xyz;
            Field25519.mult(jArr, xyz2.f7029y, xyz2.f7030z);
            Field25519.mult(xyz.f7030z, partialXYZT.xyz.f7030z, partialXYZT.f7027t);
            return xyz;
        }

        public boolean isOnCurve() {
            long[] jArr = new long[10];
            Field25519.square(jArr, this.f7028x);
            long[] jArr2 = new long[10];
            Field25519.square(jArr2, this.f7029y);
            long[] jArr3 = new long[10];
            Field25519.square(jArr3, this.f7030z);
            long[] jArr4 = new long[10];
            Field25519.square(jArr4, jArr3);
            long[] jArr5 = new long[10];
            Field25519.sub(jArr5, jArr2, jArr);
            Field25519.mult(jArr5, jArr5, jArr3);
            long[] jArr6 = new long[10];
            Field25519.mult(jArr6, jArr, jArr2);
            Field25519.mult(jArr6, jArr6, Ed25519Constants.f7032D);
            Field25519.sum(jArr6, jArr4);
            Field25519.reduce(jArr6, jArr6);
            return Bytes.equal(Field25519.contract(jArr5), Field25519.contract(jArr6));
        }

        public byte[] toBytes() {
            long[] jArr = new long[10];
            long[] jArr2 = new long[10];
            long[] jArr3 = new long[10];
            Field25519.inverse(jArr, this.f7030z);
            Field25519.mult(jArr2, this.f7028x, jArr);
            Field25519.mult(jArr3, this.f7029y, jArr);
            byte[] contract = Field25519.contract(jArr3);
            contract[31] = (byte) (contract[31] ^ (Ed25519.getLsb(jArr2) << 7));
            return contract;
        }

        public XYZ(long[] jArr, long[] jArr2, long[] jArr3) {
            this.f7028x = jArr;
            this.f7029y = jArr2;
            this.f7030z = jArr3;
        }

        public XYZ(XYZ xyz) {
            this.f7028x = Arrays.copyOf(xyz.f7028x, 10);
            this.f7029y = Arrays.copyOf(xyz.f7029y, 10);
            this.f7030z = Arrays.copyOf(xyz.f7030z, 10);
        }

        public XYZ(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }
    }

    public static class XYZT {

        /* renamed from: t  reason: collision with root package name */
        final long[] f7031t;
        final XYZ xyz;

        public XYZT() {
            this(new XYZ(), new long[10]);
        }

        /* access modifiers changed from: private */
        public static XYZT fromBytesNegateVarTime(byte[] bArr) throws GeneralSecurityException {
            long[] jArr = new long[10];
            long[] expand = Field25519.expand(bArr);
            long[] jArr2 = new long[10];
            jArr2[0] = 1;
            long[] jArr3 = new long[10];
            long[] jArr4 = new long[10];
            long[] jArr5 = new long[10];
            long[] jArr6 = new long[10];
            long[] jArr7 = new long[10];
            Field25519.square(jArr4, expand);
            Field25519.mult(jArr5, jArr4, Ed25519Constants.f7032D);
            Field25519.sub(jArr4, jArr4, jArr2);
            Field25519.sum(jArr5, jArr5, jArr2);
            long[] jArr8 = new long[10];
            Field25519.square(jArr8, jArr5);
            Field25519.mult(jArr8, jArr8, jArr5);
            Field25519.square(jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr5);
            Field25519.mult(jArr, jArr, jArr4);
            Ed25519.pow2252m3(jArr, jArr);
            Field25519.mult(jArr, jArr, jArr8);
            Field25519.mult(jArr, jArr, jArr4);
            Field25519.square(jArr6, jArr);
            Field25519.mult(jArr6, jArr6, jArr5);
            Field25519.sub(jArr7, jArr6, jArr4);
            if (Ed25519.isNonZeroVarTime(jArr7)) {
                Field25519.sum(jArr7, jArr6, jArr4);
                if (!Ed25519.isNonZeroVarTime(jArr7)) {
                    Field25519.mult(jArr, jArr, Ed25519Constants.SQRTM1);
                } else {
                    throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
                }
            }
            if (Ed25519.isNonZeroVarTime(jArr) || ((bArr[31] & 255) >> 7) == 0) {
                if (Ed25519.getLsb(jArr) == ((bArr[31] & 255) >> 7)) {
                    Ed25519.neg(jArr, jArr);
                }
                Field25519.mult(jArr3, jArr, expand);
                return new XYZT(new XYZ(jArr, expand, jArr2), jArr3);
            }
            throw new GeneralSecurityException("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        }

        /* access modifiers changed from: private */
        @CanIgnoreReturnValue
        public static XYZT fromPartialXYZT(XYZT xyzt, PartialXYZT partialXYZT) {
            Field25519.mult(xyzt.xyz.f7028x, partialXYZT.xyz.f7028x, partialXYZT.f7027t);
            long[] jArr = xyzt.xyz.f7029y;
            XYZ xyz2 = partialXYZT.xyz;
            Field25519.mult(jArr, xyz2.f7029y, xyz2.f7030z);
            Field25519.mult(xyzt.xyz.f7030z, partialXYZT.xyz.f7030z, partialXYZT.f7027t);
            long[] jArr2 = xyzt.f7031t;
            XYZ xyz3 = partialXYZT.xyz;
            Field25519.mult(jArr2, xyz3.f7028x, xyz3.f7029y);
            return xyzt;
        }

        public XYZT(XYZ xyz2, long[] jArr) {
            this.xyz = xyz2;
            this.f7031t = jArr;
        }

        public XYZT(PartialXYZT partialXYZT) {
            this();
            fromPartialXYZT(this, partialXYZT);
        }
    }

    private Ed25519() {
    }

    private static void add(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.xyz.f7028x;
        XYZ xyz = xyzt.xyz;
        Field25519.sum(jArr2, xyz.f7029y, xyz.f7028x);
        long[] jArr3 = partialXYZT.xyz.f7029y;
        XYZ xyz2 = xyzt.xyz;
        Field25519.sub(jArr3, xyz2.f7029y, xyz2.f7028x);
        long[] jArr4 = partialXYZT.xyz.f7029y;
        Field25519.mult(jArr4, jArr4, cachedXYT.yMinusX);
        XYZ xyz3 = partialXYZT.xyz;
        Field25519.mult(xyz3.f7030z, xyz3.f7028x, cachedXYT.yPlusX);
        Field25519.mult(partialXYZT.f7027t, xyzt.f7031t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.f7028x, xyzt.xyz.f7030z);
        long[] jArr5 = partialXYZT.xyz.f7028x;
        Field25519.sum(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f7028x, xyz4.f7030z, xyz4.f7029y);
        XYZ xyz5 = partialXYZT.xyz;
        long[] jArr6 = xyz5.f7029y;
        Field25519.sum(jArr6, xyz5.f7030z, jArr6);
        Field25519.sum(partialXYZT.xyz.f7030z, jArr, partialXYZT.f7027t);
        long[] jArr7 = partialXYZT.f7027t;
        Field25519.sub(jArr7, jArr, jArr7);
    }

    private static XYZ doubleScalarMultVarTime(byte[] bArr, XYZT xyzt, byte[] bArr2) {
        CachedXYZT[] cachedXYZTArr = new CachedXYZT[8];
        cachedXYZTArr[0] = new CachedXYZT(xyzt);
        PartialXYZT partialXYZT = new PartialXYZT();
        doubleXYZT(partialXYZT, xyzt);
        XYZT xyzt2 = new XYZT(partialXYZT);
        for (int i3 = 1; i3 < 8; i3++) {
            add(partialXYZT, xyzt2, cachedXYZTArr[i3 - 1]);
            cachedXYZTArr[i3] = new CachedXYZT(new XYZT(partialXYZT));
        }
        byte[] slide = slide(bArr);
        byte[] slide2 = slide(bArr2);
        PartialXYZT partialXYZT2 = new PartialXYZT(NEUTRAL);
        XYZT xyzt3 = new XYZT();
        int i4 = 255;
        while (i4 >= 0 && slide[i4] == 0 && slide2[i4] == 0) {
            i4--;
        }
        while (i4 >= 0) {
            doubleXYZ(partialXYZT2, new XYZ(partialXYZT2));
            byte b3 = slide[i4];
            if (b3 > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[slide[i4] / 2]);
            } else if (b3 < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), cachedXYZTArr[(-slide[i4]) / 2]);
            }
            byte b4 = slide2[i4];
            if (b4 > 0) {
                add(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.B2[slide2[i4] / 2]);
            } else if (b4 < 0) {
                sub(partialXYZT2, XYZT.fromPartialXYZT(xyzt3, partialXYZT2), Ed25519Constants.B2[(-slide2[i4]) / 2]);
            }
            i4--;
        }
        return new XYZ(partialXYZT2);
    }

    private static void doubleXYZ(PartialXYZT partialXYZT, XYZ xyz) {
        long[] jArr = new long[10];
        Field25519.square(partialXYZT.xyz.f7028x, xyz.f7028x);
        Field25519.square(partialXYZT.xyz.f7030z, xyz.f7029y);
        Field25519.square(partialXYZT.f7027t, xyz.f7030z);
        long[] jArr2 = partialXYZT.f7027t;
        Field25519.sum(jArr2, jArr2, jArr2);
        Field25519.sum(partialXYZT.xyz.f7029y, xyz.f7028x, xyz.f7029y);
        Field25519.square(jArr, partialXYZT.xyz.f7029y);
        XYZ xyz2 = partialXYZT.xyz;
        Field25519.sum(xyz2.f7029y, xyz2.f7030z, xyz2.f7028x);
        XYZ xyz3 = partialXYZT.xyz;
        long[] jArr3 = xyz3.f7030z;
        Field25519.sub(jArr3, jArr3, xyz3.f7028x);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f7028x, jArr, xyz4.f7029y);
        long[] jArr4 = partialXYZT.f7027t;
        Field25519.sub(jArr4, jArr4, partialXYZT.xyz.f7030z);
    }

    private static void doubleXYZT(PartialXYZT partialXYZT, XYZT xyzt) {
        doubleXYZ(partialXYZT, xyzt.xyz);
    }

    private static int eq(int i3, int i4) {
        int i5 = (~(i3 ^ i4)) & 255;
        int i6 = i5 & (i5 << 4);
        int i7 = i6 & (i6 << 2);
        return ((i7 & (i7 << 1)) >> 7) & 1;
    }

    public static byte[] getHashedScalar(byte[] bArr) throws GeneralSecurityException {
        MessageDigest instance = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        instance.update(bArr, 0, 32);
        byte[] digest = instance.digest();
        digest[0] = (byte) (digest[0] & 248);
        byte b3 = (byte) (digest[31] & Byte.MAX_VALUE);
        digest[31] = b3;
        digest[31] = (byte) (b3 | SignedBytes.MAX_POWER_OF_TWO);
        return digest;
    }

    /* access modifiers changed from: private */
    public static int getLsb(long[] jArr) {
        return Field25519.contract(jArr)[0] & 1;
    }

    /* access modifiers changed from: private */
    public static boolean isNonZeroVarTime(long[] jArr) {
        long[] jArr2 = new long[(jArr.length + 1)];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Field25519.reduceCoefficients(jArr2);
        for (byte b3 : Field25519.contract(jArr2)) {
            if (b3 != 0) {
                return true;
            }
        }
        return false;
    }

    private static boolean isSmallerThanGroupOrder(byte[] bArr) {
        for (int i3 = 31; i3 >= 0; i3--) {
            byte b3 = bArr[i3] & 255;
            byte b4 = GROUP_ORDER[i3] & 255;
            if (b3 != b4) {
                return b3 < b4;
            }
        }
        return false;
    }

    private static long load3(byte[] bArr, int i3) {
        return (((long) (bArr[i3 + 2] & 255)) << 16) | (((long) bArr[i3]) & 255) | (((long) (bArr[i3 + 1] & 255)) << 8);
    }

    private static long load4(byte[] bArr, int i3) {
        return (((long) (bArr[i3 + 3] & 255)) << 24) | load3(bArr, i3);
    }

    private static void mulAdd(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = bArr2;
        byte[] bArr6 = bArr3;
        byte[] bArr7 = bArr4;
        long load3 = load3(bArr5, 0) & 2097151;
        long load4 = (load4(bArr5, 2) >> 5) & 2097151;
        long load32 = (load3(bArr5, 5) >> 2) & 2097151;
        long load42 = (load4(bArr5, 7) >> 7) & 2097151;
        long load43 = (load4(bArr5, 10) >> 4) & 2097151;
        long load33 = (load3(bArr5, 13) >> 1) & 2097151;
        long load44 = (load4(bArr5, 15) >> 6) & 2097151;
        long load34 = (load3(bArr5, 18) >> 3) & 2097151;
        long load35 = load3(bArr5, 21) & 2097151;
        long load45 = (load4(bArr5, 23) >> 5) & 2097151;
        long load36 = (load3(bArr5, 26) >> 2) & 2097151;
        long load46 = load4(bArr5, 28) >> 7;
        long load37 = load3(bArr6, 0) & 2097151;
        long load47 = (load4(bArr6, 2) >> 5) & 2097151;
        long load38 = (load3(bArr6, 5) >> 2) & 2097151;
        long load48 = (load4(bArr6, 7) >> 7) & 2097151;
        long load49 = (load4(bArr6, 10) >> 4) & 2097151;
        long load39 = (load3(bArr6, 13) >> 1) & 2097151;
        long load410 = (load4(bArr6, 15) >> 6) & 2097151;
        long load310 = (load3(bArr6, 18) >> 3) & 2097151;
        long load311 = load3(bArr6, 21) & 2097151;
        long load411 = (load4(bArr6, 23) >> 5) & 2097151;
        long load312 = (load3(bArr6, 26) >> 2) & 2097151;
        long load412 = load4(bArr6, 28) >> 7;
        long load313 = (load3(bArr7, 26) >> 2) & 2097151;
        long load314 = (load3 * load37) + (load3(bArr7, 0) & 2097151);
        long load413 = (load4 * load37) + (load3 * load47) + ((load4(bArr7, 2) >> 5) & 2097151);
        long load315 = (load32 * load37) + (load4 * load47) + (load3 * load38) + ((load3(bArr7, 5) >> 2) & 2097151);
        long load414 = (load42 * load37) + (load32 * load47) + (load4 * load38) + (load3 * load48) + ((load4(bArr7, 7) >> 7) & 2097151);
        long load415 = (load43 * load37) + (load42 * load47) + (load32 * load38) + (load4 * load48) + (load3 * load49) + ((load4(bArr7, 10) >> 4) & 2097151);
        long load316 = (load33 * load37) + (load43 * load47) + (load42 * load38) + (load32 * load48) + (load4 * load49) + (load3 * load39) + ((load3(bArr7, 13) >> 1) & 2097151);
        long load416 = (load44 * load37) + (load33 * load47) + (load43 * load38) + (load42 * load48) + (load32 * load49) + (load4 * load39) + (load3 * load410) + ((load4(bArr7, 15) >> 6) & 2097151);
        long load317 = (load34 * load37) + (load44 * load47) + (load33 * load38) + (load43 * load48) + (load42 * load49) + (load32 * load39) + (load4 * load410) + (load3 * load310) + ((load3(bArr7, 18) >> 3) & 2097151);
        long load318 = (load35 * load37) + (load34 * load47) + (load44 * load38) + (load33 * load48) + (load43 * load49) + (load42 * load39) + (load32 * load410) + (load4 * load310) + (load3 * load311) + (load3(bArr7, 21) & 2097151);
        long load417 = (load45 * load37) + (load35 * load47) + (load34 * load38) + (load44 * load48) + (load33 * load49) + (load43 * load39) + (load42 * load410) + (load32 * load310) + (load4 * load311) + (load3 * load411) + ((load4(bArr7, 23) >> 5) & 2097151);
        long j2 = (load36 * load37) + (load45 * load47) + (load35 * load38) + (load34 * load48) + (load44 * load49) + (load33 * load39) + (load43 * load410) + (load42 * load310) + (load32 * load311) + (load4 * load411) + (load3 * load312) + load313;
        long j3 = load37 * load46;
        long load418 = j3 + (load36 * load47) + (load45 * load38) + (load35 * load48) + (load34 * load49) + (load44 * load39) + (load33 * load410) + (load43 * load310) + (load42 * load311) + (load32 * load411) + (load4 * load312) + (load3 * load412) + (load4(bArr7, 28) >> 7);
        long j4 = load42 * load411;
        long j5 = load47 * load46;
        long j6 = j5 + (load36 * load38) + (load45 * load48) + (load35 * load49) + (load34 * load39) + (load44 * load410) + (load33 * load310) + (load43 * load311) + j4 + (load32 * load312) + (load4 * load412);
        long j7 = load43 * load411;
        long j8 = load38 * load46;
        long j9 = j8 + (load36 * load48) + (load45 * load49) + (load35 * load39) + (load34 * load410) + (load44 * load310) + (load33 * load311) + j7 + (load42 * load312) + (load32 * load412);
        long j10 = load33 * load411;
        long j11 = load48 * load46;
        long j12 = j11 + (load36 * load49) + (load45 * load39) + (load35 * load410) + (load34 * load310) + (load44 * load311) + j10 + (load43 * load312) + (load42 * load412);
        long j13 = load44 * load411;
        long j14 = load49 * load46;
        long j15 = j14 + (load36 * load39) + (load45 * load410) + (load35 * load310) + (load34 * load311) + j13 + (load33 * load312) + (load43 * load412);
        long j16 = load34 * load411;
        long j17 = load39 * load46;
        long j18 = j17 + (load36 * load410) + (load45 * load310) + (load35 * load311) + j16 + (load44 * load312) + (load33 * load412);
        long j19 = load35 * load411;
        long j20 = load410 * load46;
        long j21 = j20 + (load36 * load310) + (load45 * load311) + j19 + (load34 * load312) + (load44 * load412);
        long j22 = load45 * load411;
        long j23 = load310 * load46;
        long j24 = j23 + (load36 * load311) + j22 + (load35 * load312) + (load34 * load412);
        long j25 = load45 * load312;
        long j26 = load36 * load411;
        long j27 = load311 * load46;
        long j28 = load411 * load46;
        long j29 = j28 + (load36 * load312) + (load45 * load412);
        long j30 = load312 * load46;
        long j31 = load46 * load412;
        long j32 = (load314 + 1048576) >> 21;
        long j33 = load413 + j32;
        long j34 = load314 - (j32 << 21);
        long j35 = (load315 + 1048576) >> 21;
        long j36 = load414 + j35;
        long j37 = load315 - (j35 << 21);
        long j38 = (load415 + 1048576) >> 21;
        long j39 = load316 + j38;
        long j40 = load415 - (j38 << 21);
        long j41 = (load416 + 1048576) >> 21;
        long j42 = load317 + j41;
        long j43 = load416 - (j41 << 21);
        long j44 = (load318 + 1048576) >> 21;
        long j45 = load417 + j44;
        long j46 = load318 - (j44 << 21);
        long j47 = (j2 + 1048576) >> 21;
        long j48 = load418 + j47;
        long j49 = j2 - (j47 << 21);
        long j50 = (j6 + 1048576) >> 21;
        long j51 = j9 + j50;
        long j52 = j6 - (j50 << 21);
        long j53 = (j12 + 1048576) >> 21;
        long j54 = j15 + j53;
        long j55 = j12 - (j53 << 21);
        long j56 = (j18 + 1048576) >> 21;
        long j57 = j21 + j56;
        long j58 = j18 - (j56 << 21);
        long j59 = (j24 + 1048576) >> 21;
        long j60 = j27 + j26 + j25 + (load35 * load412) + j59;
        long j61 = j24 - (j59 << 21);
        long j62 = (j29 + 1048576) >> 21;
        long j63 = j30 + (load36 * load412) + j62;
        long j64 = j29 - (j62 << 21);
        long j65 = (j31 + 1048576) >> 21;
        long j66 = (j33 + 1048576) >> 21;
        long j67 = j37 + j66;
        long j68 = j33 - (j66 << 21);
        long j69 = (j36 + 1048576) >> 21;
        long j70 = j40 + j69;
        long j71 = j36 - (j69 << 21);
        long j72 = (j39 + 1048576) >> 21;
        long j73 = j43 + j72;
        long j74 = j39 - (j72 << 21);
        long j75 = (j42 + 1048576) >> 21;
        long j76 = j46 + j75;
        long j77 = j42 - (j75 << 21);
        long j78 = (j45 + 1048576) >> 21;
        long j79 = j49 + j78;
        long j80 = j45 - (j78 << 21);
        long j81 = (j48 + 1048576) >> 21;
        long j82 = j52 + j81;
        long j83 = j48 - (j81 << 21);
        long j84 = (j51 + 1048576) >> 21;
        long j85 = j55 + j84;
        long j86 = j51 - (j84 << 21);
        long j87 = (j54 + 1048576) >> 21;
        long j88 = j58 + j87;
        long j89 = j54 - (j87 << 21);
        long j90 = (j57 + 1048576) >> 21;
        long j91 = j61 + j90;
        long j92 = j57 - (j90 << 21);
        long j93 = (j60 + 1048576) >> 21;
        long j94 = j64 + j93;
        long j95 = j60 - (j93 << 21);
        long j96 = (j63 + 1048576) >> 21;
        long j97 = (j31 - (j65 << 21)) + j96;
        long j98 = j63 - (j96 << 21);
        long j99 = j88 - (j65 * 683901);
        long j100 = (j97 * 654183) + (j65 * 470296) + j82;
        long j101 = ((j65 * 654183) + j86) - (j97 * 997805);
        long j102 = j97 * 136657;
        long j103 = ((j65 * 136657) + j89) - (j97 * 683901);
        long j104 = (j98 * 654183) + (j97 * 470296) + (j65 * 666643) + j83;
        long j105 = (j98 * 136657) + j101;
        long j106 = (j102 + (j85 - (j65 * 997805))) - (j98 * 683901);
        long j107 = (j94 * 654183) + (j98 * 470296) + (j97 * 666643) + j79;
        long j108 = (j95 * 654183) + (j94 * 470296) + (j98 * 666643) + j80;
        long j109 = (j95 * 136657) + (j104 - (j94 * 997805));
        long j110 = ((j94 * 136657) + (j100 - (j98 * 997805))) - (j95 * 683901);
        long j111 = (j91 * 666643) + j73;
        long j112 = (j91 * 654183) + (j95 * 470296) + (j94 * 666643) + j76;
        long j113 = (j91 * 136657) + (j107 - (j95 * 997805));
        long j114 = (j111 + 1048576) >> 21;
        long j115 = (j91 * 470296) + (j95 * 666643) + j77 + j114;
        long j116 = j111 - (j114 << 21);
        long j117 = (j112 + 1048576) >> 21;
        long j118 = (j108 - (j91 * 997805)) + j117;
        long j119 = j112 - (j117 << 21);
        long j120 = (j113 + 1048576) >> 21;
        long j121 = (j109 - (j91 * 683901)) + j120;
        long j122 = j113 - (j120 << 21);
        long j123 = (j110 + 1048576) >> 21;
        long j124 = (j105 - (j94 * 683901)) + j123;
        long j125 = j110 - (j123 << 21);
        long j126 = (j106 + 1048576) >> 21;
        long j127 = j103 + j126;
        long j128 = j106 - (j126 << 21);
        long j129 = (j99 + 1048576) >> 21;
        long j130 = j92 + j129;
        long j131 = j99 - (j129 << 21);
        long j132 = (j115 + 1048576) >> 21;
        long j133 = j119 + j132;
        long j134 = j115 - (j132 << 21);
        long j135 = (j118 + 1048576) >> 21;
        long j136 = j122 + j135;
        long j137 = j118 - (j135 << 21);
        long j138 = (j121 + 1048576) >> 21;
        long j139 = j125 + j138;
        long j140 = j121 - (j138 << 21);
        long j141 = (j124 + 1048576) >> 21;
        long j142 = j128 + j141;
        long j143 = j124 - (j141 << 21);
        long j144 = (j127 + 1048576) >> 21;
        long j145 = j131 + j144;
        long j146 = j127 - (j144 << 21);
        long j147 = (j130 * 470296) + j116;
        long j148 = (j130 * 654183) + j134;
        long j149 = j136 - (j130 * 683901);
        long j150 = (j145 * 654183) + j147;
        long j151 = j148 - (j145 * 997805);
        long j152 = (j145 * 136657) + (j133 - (j130 * 997805));
        long j153 = ((j130 * 136657) + j137) - (j145 * 683901);
        long j154 = (j146 * 654183) + (j145 * 470296) + (j130 * 666643) + j74;
        long j155 = j150 - (j146 * 997805);
        long j156 = (j146 * 136657) + j151;
        long j157 = j152 - (j146 * 683901);
        long j158 = (j142 * 654183) + (j146 * 470296) + (j145 * 666643) + j70;
        long j159 = (j142 * 136657) + j155;
        long j160 = j156 - (j142 * 683901);
        long j161 = (j143 * 654183) + (j142 * 470296) + (j146 * 666643) + j71;
        long j162 = j158 - (j143 * 997805);
        long j163 = (j143 * 136657) + (j154 - (j142 * 997805));
        long j164 = j159 - (j143 * 683901);
        long j165 = (j139 * 666643) + j34;
        long j166 = (j139 * 654183) + (j143 * 470296) + (j142 * 666643) + j67;
        long j167 = (j139 * 136657) + j162;
        long j168 = (j165 + 1048576) >> 21;
        long j169 = (j139 * 470296) + (j143 * 666643) + j68 + j168;
        long j170 = j165 - (j168 << 21);
        long j171 = (j166 + 1048576) >> 21;
        long j172 = (j161 - (j139 * 997805)) + j171;
        long j173 = j166 - (j171 << 21);
        long j174 = (j167 + 1048576) >> 21;
        long j175 = (j163 - (j139 * 683901)) + j174;
        long j176 = j167 - (j174 << 21);
        long j177 = (j164 + 1048576) >> 21;
        long j178 = j160 + j177;
        long j179 = j164 - (j177 << 21);
        long j180 = (j157 + 1048576) >> 21;
        long j181 = j153 + j180;
        long j182 = j157 - (j180 << 21);
        long j183 = (j149 + 1048576) >> 21;
        long j184 = j140 + j183;
        long j185 = j149 - (j183 << 21);
        long j186 = (j169 + 1048576) >> 21;
        long j187 = j173 + j186;
        long j188 = j169 - (j186 << 21);
        long j189 = (j172 + 1048576) >> 21;
        long j190 = j176 + j189;
        long j191 = j172 - (j189 << 21);
        long j192 = (j175 + 1048576) >> 21;
        long j193 = j179 + j192;
        long j194 = j175 - (j192 << 21);
        long j195 = (j178 + 1048576) >> 21;
        long j196 = j182 + j195;
        long j197 = j178 - (j195 << 21);
        long j198 = (j181 + 1048576) >> 21;
        long j199 = j185 + j198;
        long j200 = j181 - (j198 << 21);
        long j201 = (j184 + 1048576) >> 21;
        long j202 = (j201 * 666643) + j170;
        long j203 = (j201 * 470296) + j188;
        long j204 = (j201 * 654183) + j187;
        long j205 = (j201 * 136657) + j190;
        long j206 = j202 >> 21;
        long j207 = j203 + j206;
        long j208 = j202 - (j206 << 21);
        long j209 = j207 >> 21;
        long j210 = j204 + j209;
        long j211 = j207 - (j209 << 21);
        long j212 = j210 >> 21;
        long j213 = (j191 - (j201 * 997805)) + j212;
        long j214 = j210 - (j212 << 21);
        long j215 = j213 >> 21;
        long j216 = j205 + j215;
        long j217 = j213 - (j215 << 21);
        long j218 = j216 >> 21;
        long j219 = (j194 - (j201 * 683901)) + j218;
        long j220 = j216 - (j218 << 21);
        long j221 = j219 >> 21;
        long j222 = j193 + j221;
        long j223 = j219 - (j221 << 21);
        long j224 = j222 >> 21;
        long j225 = j197 + j224;
        long j226 = j222 - (j224 << 21);
        long j227 = j225 >> 21;
        long j228 = j196 + j227;
        long j229 = j225 - (j227 << 21);
        long j230 = j228 >> 21;
        long j231 = j200 + j230;
        long j232 = j228 - (j230 << 21);
        long j233 = j231 >> 21;
        long j234 = j199 + j233;
        long j235 = j231 - (j233 << 21);
        long j236 = j234 >> 21;
        long j237 = (j184 - (j201 << 21)) + j236;
        long j238 = j234 - (j236 << 21);
        long j239 = j237 >> 21;
        long j240 = (666643 * j239) + j208;
        long j241 = j240 >> 21;
        long j242 = (470296 * j239) + j211 + j241;
        long j243 = j240 - (j241 << 21);
        long j244 = j242 >> 21;
        long j245 = (654183 * j239) + j214 + j244;
        long j246 = j242 - (j244 << 21);
        long j247 = j245 >> 21;
        long j248 = (j217 - (997805 * j239)) + j247;
        long j249 = j245 - (j247 << 21);
        long j250 = j248 >> 21;
        long j251 = (136657 * j239) + j220 + j250;
        long j252 = j248 - (j250 << 21);
        long j253 = j251 >> 21;
        long j254 = (j223 - (j239 * 683901)) + j253;
        long j255 = j251 - (j253 << 21);
        long j256 = j254 >> 21;
        long j257 = j226 + j256;
        long j258 = j254 - (j256 << 21);
        long j259 = j257 >> 21;
        long j260 = j229 + j259;
        long j261 = j257 - (j259 << 21);
        long j262 = j260 >> 21;
        long j263 = j232 + j262;
        long j264 = j260 - (j262 << 21);
        long j265 = j263 >> 21;
        long j266 = j235 + j265;
        long j267 = j263 - (j265 << 21);
        long j268 = j266 >> 21;
        long j269 = j238 + j268;
        long j270 = j266 - (j268 << 21);
        long j271 = j269 >> 21;
        long j272 = (j237 - (j239 << 21)) + j271;
        long j273 = j269 - (j271 << 21);
        bArr[0] = (byte) ((int) j243);
        bArr[1] = (byte) ((int) (j243 >> 8));
        bArr[2] = (byte) ((int) ((j243 >> 16) | (j246 << 5)));
        bArr[3] = (byte) ((int) (j246 >> 3));
        bArr[4] = (byte) ((int) (j246 >> 11));
        bArr[5] = (byte) ((int) ((j246 >> 19) | (j249 << 2)));
        bArr[6] = (byte) ((int) (j249 >> 6));
        bArr[7] = (byte) ((int) ((j249 >> 14) | (j252 << 7)));
        bArr[8] = (byte) ((int) (j252 >> 1));
        bArr[9] = (byte) ((int) (j252 >> 9));
        bArr[10] = (byte) ((int) ((j252 >> 17) | (j255 << 4)));
        bArr[11] = (byte) ((int) (j255 >> 4));
        bArr[12] = (byte) ((int) (j255 >> 12));
        bArr[13] = (byte) ((int) ((j255 >> 20) | (j258 << 1)));
        bArr[14] = (byte) ((int) (j258 >> 7));
        bArr[15] = (byte) ((int) ((j258 >> 15) | (j261 << 6)));
        bArr[16] = (byte) ((int) (j261 >> 2));
        bArr[17] = (byte) ((int) (j261 >> 10));
        bArr[18] = (byte) ((int) ((j261 >> 18) | (j264 << 3)));
        bArr[19] = (byte) ((int) (j264 >> 5));
        bArr[20] = (byte) ((int) (j264 >> 13));
        bArr[21] = (byte) ((int) j267);
        bArr[22] = (byte) ((int) (j267 >> 8));
        bArr[23] = (byte) ((int) ((j267 >> 16) | (j270 << 5)));
        bArr[24] = (byte) ((int) (j270 >> 3));
        bArr[25] = (byte) ((int) (j270 >> 11));
        bArr[26] = (byte) ((int) ((j270 >> 19) | (j273 << 2)));
        bArr[27] = (byte) ((int) (j273 >> 6));
        bArr[28] = (byte) ((int) ((j273 >> 14) | (j272 << 7)));
        bArr[29] = (byte) ((int) (j272 >> 1));
        bArr[30] = (byte) ((int) (j272 >> 9));
        bArr[31] = (byte) ((int) (j272 >> 17));
    }

    /* access modifiers changed from: private */
    public static void neg(long[] jArr, long[] jArr2) {
        for (int i3 = 0; i3 < jArr2.length; i3++) {
            jArr[i3] = -jArr2[i3];
        }
    }

    /* access modifiers changed from: private */
    public static void pow2252m3(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[10];
        long[] jArr4 = new long[10];
        long[] jArr5 = new long[10];
        Field25519.square(jArr3, jArr2);
        Field25519.square(jArr4, jArr3);
        Field25519.square(jArr4, jArr4);
        Field25519.mult(jArr4, jArr2, jArr4);
        Field25519.mult(jArr3, jArr3, jArr4);
        Field25519.square(jArr3, jArr3);
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i3 = 1; i3 < 5; i3++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i4 = 1; i4 < 10; i4++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (int i5 = 1; i5 < 20; i5++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (int i6 = 1; i6 < 10; i6++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr4, jArr3);
        for (int i7 = 1; i7 < 50; i7++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr4, jArr4, jArr3);
        Field25519.square(jArr5, jArr4);
        for (int i8 = 1; i8 < 100; i8++) {
            Field25519.square(jArr5, jArr5);
        }
        Field25519.mult(jArr4, jArr5, jArr4);
        Field25519.square(jArr4, jArr4);
        for (int i9 = 1; i9 < 50; i9++) {
            Field25519.square(jArr4, jArr4);
        }
        Field25519.mult(jArr3, jArr4, jArr3);
        Field25519.square(jArr3, jArr3);
        Field25519.square(jArr3, jArr3);
        Field25519.mult(jArr, jArr3, jArr2);
    }

    private static void reduce(byte[] bArr) {
        byte[] bArr2 = bArr;
        long load3 = (load3(bArr2, 47) >> 2) & 2097151;
        long load4 = (load4(bArr2, 49) >> 7) & 2097151;
        long load42 = (load4(bArr2, 52) >> 4) & 2097151;
        long load32 = (load3(bArr2, 55) >> 1) & 2097151;
        long load43 = (load4(bArr2, 57) >> 6) & 2097151;
        long load44 = load4(bArr2, 60) >> 3;
        long j2 = load44 * 666643;
        long j3 = load44 * 470296;
        long j4 = load44 * 654183;
        long j5 = load44 * 136657;
        long load33 = (load3(bArr2, 42) & 2097151) - (load44 * 683901);
        long load34 = (load43 * 666643) + ((load3(bArr2, 26) >> 2) & 2097151);
        long load45 = (load43 * 470296) + j2 + ((load4(bArr2, 28) >> 7) & 2097151);
        long load46 = (load43 * 654183) + j3 + ((load4(bArr2, 31) >> 4) & 2097151);
        long load35 = (j4 + ((load3(bArr2, 34) >> 1) & 2097151)) - (load43 * 997805);
        long load47 = (load43 * 136657) + (((load4(bArr2, 36) >> 6) & 2097151) - (load44 * 997805));
        long load36 = (j5 + ((load3(bArr2, 39) >> 3) & 2097151)) - (load43 * 683901);
        long load48 = (load32 * 666643) + ((load4(bArr2, 23) >> 5) & 2097151);
        long j6 = (load32 * 654183) + load45;
        long j7 = load46 - (load32 * 997805);
        long j8 = load47 - (load32 * 683901);
        long load37 = (load42 * 666643) + (load3(bArr2, 21) & 2097151);
        long j9 = (load42 * 470296) + load48;
        long j10 = (load42 * 654183) + (load32 * 470296) + load34;
        long j11 = j6 - (load42 * 997805);
        long j12 = ((load32 * 136657) + load35) - (load42 * 683901);
        long load38 = (load4 * 666643) + ((load3(bArr2, 18) >> 3) & 2097151);
        long j13 = (load4 * 470296) + load37;
        long j14 = (load4 * 654183) + j9;
        long j15 = j10 - (load4 * 997805);
        long j16 = (load4 * 136657) + j11;
        long j17 = ((load42 * 136657) + j7) - (load4 * 683901);
        long load49 = (load3 * 666643) + ((load4(bArr2, 15) >> 6) & 2097151);
        long j18 = (load3 * 654183) + j13;
        long j19 = j14 - (load3 * 997805);
        long j20 = (load3 * 136657) + j15;
        long j21 = j16 - (load3 * 683901);
        long j22 = (load49 + 1048576) >> 21;
        long j23 = (load3 * 470296) + load38 + j22;
        long j24 = load49 - (j22 << 21);
        long j25 = (j18 + 1048576) >> 21;
        long j26 = j19 + j25;
        long j27 = j18 - (j25 << 21);
        long j28 = (j20 + 1048576) >> 21;
        long j29 = j21 + j28;
        long j30 = j20 - (j28 << 21);
        long j31 = (j17 + 1048576) >> 21;
        long j32 = j12 + j31;
        long j33 = j17 - (j31 << 21);
        long j34 = (j8 + 1048576) >> 21;
        long j35 = load36 + j34;
        long j36 = j8 - (j34 << 21);
        long j37 = (load33 + 1048576) >> 21;
        long load410 = ((load4(bArr2, 44) >> 5) & 2097151) + j37;
        long j38 = load33 - (j37 << 21);
        long j39 = (j23 + 1048576) >> 21;
        long j40 = j27 + j39;
        long j41 = j23 - (j39 << 21);
        long j42 = (j26 + 1048576) >> 21;
        long j43 = j30 + j42;
        long j44 = j26 - (j42 << 21);
        long j45 = (j29 + 1048576) >> 21;
        long j46 = j33 + j45;
        long j47 = j29 - (j45 << 21);
        long j48 = (j32 + 1048576) >> 21;
        long j49 = j36 + j48;
        long j50 = j32 - (j48 << 21);
        long j51 = (j35 + 1048576) >> 21;
        long j52 = j38 + j51;
        long j53 = j35 - (j51 << 21);
        long j54 = (load410 * 470296) + j24;
        long j55 = (load410 * 654183) + j41;
        long j56 = j40 - (load410 * 997805);
        long j57 = j43 - (load410 * 683901);
        long j58 = j52 * 666643;
        long load39 = (j52 * 470296) + (load410 * 666643) + ((load3(bArr2, 13) >> 1) & 2097151);
        long j59 = (j52 * 654183) + j54;
        long j60 = j55 - (j52 * 997805);
        long j61 = ((load410 * 136657) + j44) - (j52 * 683901);
        long load411 = (j53 * 666643) + ((load4(bArr2, 7) >> 7) & 2097151);
        long load412 = (j53 * 470296) + j58 + ((load4(bArr2, 10) >> 4) & 2097151);
        long j62 = (j53 * 654183) + load39;
        long j63 = (j53 * 136657) + j60;
        long j64 = ((j52 * 136657) + j56) - (j53 * 683901);
        long load310 = (j49 * 666643) + ((load3(bArr2, 5) >> 2) & 2097151);
        long j65 = (j49 * 470296) + load411;
        long j66 = (j49 * 654183) + load412;
        long j67 = (j49 * 136657) + (j59 - (j53 * 997805));
        long load413 = (j50 * 666643) + ((load4(bArr2, 2) >> 5) & 2097151);
        long j68 = (j50 * 470296) + load310;
        long j69 = (j50 * 654183) + j65;
        long j70 = j66 - (j50 * 997805);
        long j71 = (j50 * 136657) + (j62 - (j49 * 997805));
        long j72 = j67 - (j50 * 683901);
        long load311 = (j46 * 666643) + (load3(bArr2, 0) & 2097151);
        long j73 = (j46 * 654183) + j68;
        long j74 = (j46 * 136657) + j70;
        long j75 = (load311 + 1048576) >> 21;
        long j76 = (j46 * 470296) + load413 + j75;
        long j77 = load311 - (j75 << 21);
        long j78 = (j73 + 1048576) >> 21;
        long j79 = (j69 - (j46 * 997805)) + j78;
        long j80 = j73 - (j78 << 21);
        long j81 = (j74 + 1048576) >> 21;
        long j82 = (j71 - (j46 * 683901)) + j81;
        long j83 = j74 - (j81 << 21);
        long j84 = (j72 + 1048576) >> 21;
        long j85 = (j63 - (j49 * 683901)) + j84;
        long j86 = j72 - (j84 << 21);
        long j87 = (j64 + 1048576) >> 21;
        long j88 = j61 + j87;
        long j89 = j64 - (j87 << 21);
        long j90 = (j57 + 1048576) >> 21;
        long j91 = j47 + j90;
        long j92 = j57 - (j90 << 21);
        long j93 = (j76 + 1048576) >> 21;
        long j94 = j80 + j93;
        long j95 = j76 - (j93 << 21);
        long j96 = (j79 + 1048576) >> 21;
        long j97 = j83 + j96;
        long j98 = j79 - (j96 << 21);
        long j99 = (j82 + 1048576) >> 21;
        long j100 = j86 + j99;
        long j101 = j82 - (j99 << 21);
        long j102 = (j85 + 1048576) >> 21;
        long j103 = j89 + j102;
        long j104 = j85 - (j102 << 21);
        long j105 = (j88 + 1048576) >> 21;
        long j106 = (j91 + 1048576) >> 21;
        long j107 = (j106 * 666643) + j77;
        long j108 = (j106 * 136657) + j97;
        long j109 = j107 >> 21;
        long j110 = (j106 * 470296) + j95 + j109;
        long j111 = j107 - (j109 << 21);
        long j112 = j110 >> 21;
        long j113 = (j106 * 654183) + j94 + j112;
        long j114 = j110 - (j112 << 21);
        long j115 = j113 >> 21;
        long j116 = (j98 - (j106 * 997805)) + j115;
        long j117 = j113 - (j115 << 21);
        long j118 = j116 >> 21;
        long j119 = j108 + j118;
        long j120 = j116 - (j118 << 21);
        long j121 = j119 >> 21;
        long j122 = (j101 - (j106 * 683901)) + j121;
        long j123 = j119 - (j121 << 21);
        long j124 = j122 >> 21;
        long j125 = j100 + j124;
        long j126 = j122 - (j124 << 21);
        long j127 = j125 >> 21;
        long j128 = j104 + j127;
        long j129 = j125 - (j127 << 21);
        long j130 = j128 >> 21;
        long j131 = j103 + j130;
        long j132 = j128 - (j130 << 21);
        long j133 = j131 >> 21;
        long j134 = (j88 - (j105 << 21)) + j133;
        long j135 = j131 - (j133 << 21);
        long j136 = j134 >> 21;
        long j137 = j92 + j105 + j136;
        long j138 = j134 - (j136 << 21);
        long j139 = j137 >> 21;
        long j140 = (j91 - (j106 << 21)) + j139;
        long j141 = j137 - (j139 << 21);
        long j142 = j140 >> 21;
        long j143 = (666643 * j142) + j111;
        long j144 = j126 - (j142 * 683901);
        long j145 = j143 >> 21;
        long j146 = (470296 * j142) + j114 + j145;
        long j147 = j143 - (j145 << 21);
        long j148 = j146 >> 21;
        long j149 = (654183 * j142) + j117 + j148;
        long j150 = j146 - (j148 << 21);
        long j151 = j149 >> 21;
        long j152 = (j120 - (997805 * j142)) + j151;
        long j153 = j149 - (j151 << 21);
        long j154 = j152 >> 21;
        long j155 = (136657 * j142) + j123 + j154;
        long j156 = j152 - (j154 << 21);
        long j157 = j155 >> 21;
        long j158 = j144 + j157;
        long j159 = j155 - (j157 << 21);
        long j160 = j158 >> 21;
        long j161 = j129 + j160;
        long j162 = j158 - (j160 << 21);
        long j163 = j161 >> 21;
        long j164 = j132 + j163;
        long j165 = j161 - (j163 << 21);
        long j166 = j164 >> 21;
        long j167 = j135 + j166;
        long j168 = j164 - (j166 << 21);
        long j169 = j167 >> 21;
        long j170 = j138 + j169;
        long j171 = j167 - (j169 << 21);
        long j172 = j170 >> 21;
        long j173 = j141 + j172;
        long j174 = j170 - (j172 << 21);
        long j175 = j173 >> 21;
        long j176 = (j140 - (j142 << 21)) + j175;
        long j177 = j173 - (j175 << 21);
        bArr2[0] = (byte) ((int) j147);
        long j178 = j165;
        bArr2[1] = (byte) ((int) (j147 >> 8));
        bArr2[2] = (byte) ((int) ((j147 >> 16) | (j150 << 5)));
        bArr2[3] = (byte) ((int) (j150 >> 3));
        bArr2[4] = (byte) ((int) (j150 >> 11));
        bArr2[5] = (byte) ((int) ((j150 >> 19) | (j153 << 2)));
        bArr2[6] = (byte) ((int) (j153 >> 6));
        bArr2[7] = (byte) ((int) ((j153 >> 14) | (j156 << 7)));
        bArr2[8] = (byte) ((int) (j156 >> 1));
        bArr2[9] = (byte) ((int) (j156 >> 9));
        bArr2[10] = (byte) ((int) ((j156 >> 17) | (j159 << 4)));
        bArr2[11] = (byte) ((int) (j159 >> 4));
        bArr2[12] = (byte) ((int) (j159 >> 12));
        bArr2[13] = (byte) ((int) ((j159 >> 20) | (j162 << 1)));
        bArr2[14] = (byte) ((int) (j162 >> 7));
        bArr2[15] = (byte) ((int) ((j162 >> 15) | (j178 << 6)));
        bArr2[16] = (byte) ((int) (j178 >> 2));
        bArr2[17] = (byte) ((int) (j178 >> 10));
        bArr2[18] = (byte) ((int) ((j178 >> 18) | (j168 << 3)));
        bArr2[19] = (byte) ((int) (j168 >> 5));
        bArr2[20] = (byte) ((int) (j168 >> 13));
        bArr2[21] = (byte) ((int) j171);
        bArr2[22] = (byte) ((int) (j171 >> 8));
        bArr2[23] = (byte) ((int) ((j171 >> 16) | (j174 << 5)));
        bArr2[24] = (byte) ((int) (j174 >> 3));
        bArr2[25] = (byte) ((int) (j174 >> 11));
        bArr2[26] = (byte) ((int) ((j174 >> 19) | (j177 << 2)));
        bArr2[27] = (byte) ((int) (j177 >> 6));
        bArr2[28] = (byte) ((int) ((j177 >> 14) | (j176 << 7)));
        bArr2[29] = (byte) ((int) (j176 >> 1));
        bArr2[30] = (byte) ((int) (j176 >> 9));
        bArr2[31] = (byte) ((int) (j176 >> 17));
    }

    private static XYZ scalarMultWithBase(byte[] bArr) {
        int i3;
        byte[] bArr2 = new byte[64];
        int i4 = 0;
        while (true) {
            if (i4 >= 32) {
                break;
            }
            int i5 = i4 * 2;
            bArr2[i5] = (byte) (bArr[i4] & Ascii.SI);
            bArr2[i5 + 1] = (byte) (((bArr[i4] & 255) >> 4) & 15);
            i4++;
        }
        int i6 = 0;
        byte b3 = 0;
        while (i6 < 63) {
            byte b4 = (byte) (bArr2[i6] + b3);
            bArr2[i6] = b4;
            int i7 = (b4 + 8) >> 4;
            bArr2[i6] = (byte) (b4 - (i7 << 4));
            i6++;
            b3 = i7;
        }
        bArr2[63] = (byte) (bArr2[63] + b3);
        PartialXYZT partialXYZT = new PartialXYZT(NEUTRAL);
        XYZT xyzt = new XYZT();
        for (i3 = 1; i3 < 64; i3 += 2) {
            CachedXYT cachedXYT = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT, i3 / 2, bArr2[i3]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT);
        }
        XYZ xyz = new XYZ();
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        doubleXYZ(partialXYZT, XYZ.fromPartialXYZT(xyz, partialXYZT));
        for (int i8 = 0; i8 < 64; i8 += 2) {
            CachedXYT cachedXYT2 = new CachedXYT(CACHED_NEUTRAL);
            select(cachedXYT2, i8 / 2, bArr2[i8]);
            add(partialXYZT, XYZT.fromPartialXYZT(xyzt, partialXYZT), cachedXYT2);
        }
        XYZ xyz2 = new XYZ(partialXYZT);
        if (xyz2.isOnCurve()) {
            return xyz2;
        }
        throw new IllegalStateException("arithmetic error in scalar multiplication");
    }

    public static byte[] scalarMultWithBaseToBytes(byte[] bArr) {
        return scalarMultWithBase(bArr).toBytes();
    }

    private static void select(CachedXYT cachedXYT, int i3, byte b3) {
        int i4 = (b3 & 255) >> 7;
        int i5 = b3 - (((-i4) & b3) << 1);
        CachedXYT[][] cachedXYTArr = Ed25519Constants.B_TABLE;
        cachedXYT.copyConditional(cachedXYTArr[i3][0], eq(i5, 1));
        cachedXYT.copyConditional(cachedXYTArr[i3][1], eq(i5, 2));
        cachedXYT.copyConditional(cachedXYTArr[i3][2], eq(i5, 3));
        cachedXYT.copyConditional(cachedXYTArr[i3][3], eq(i5, 4));
        cachedXYT.copyConditional(cachedXYTArr[i3][4], eq(i5, 5));
        cachedXYT.copyConditional(cachedXYTArr[i3][5], eq(i5, 6));
        cachedXYT.copyConditional(cachedXYTArr[i3][6], eq(i5, 7));
        cachedXYT.copyConditional(cachedXYTArr[i3][7], eq(i5, 8));
        long[] copyOf = Arrays.copyOf(cachedXYT.yMinusX, 10);
        long[] copyOf2 = Arrays.copyOf(cachedXYT.yPlusX, 10);
        long[] copyOf3 = Arrays.copyOf(cachedXYT.t2d, 10);
        neg(copyOf3, copyOf3);
        cachedXYT.copyConditional(new CachedXYT(copyOf, copyOf2, copyOf3), i4);
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, bArr.length);
        MessageDigest instance = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        instance.update(bArr3, 32, 32);
        instance.update(copyOfRange);
        byte[] digest = instance.digest();
        reduce(digest);
        byte[] copyOfRange2 = Arrays.copyOfRange(scalarMultWithBase(digest).toBytes(), 0, 32);
        instance.reset();
        instance.update(copyOfRange2);
        instance.update(bArr2);
        instance.update(copyOfRange);
        byte[] digest2 = instance.digest();
        reduce(digest2);
        byte[] bArr4 = new byte[32];
        mulAdd(bArr4, digest2, bArr3, digest);
        return Bytes.concat(copyOfRange2, bArr4);
    }

    private static byte[] slide(byte[] bArr) {
        int i3;
        byte[] bArr2 = new byte[256];
        for (int i4 = 0; i4 < 256; i4++) {
            bArr2[i4] = (byte) (1 & ((bArr[i4 >> 3] & 255) >> (i4 & 7)));
        }
        int i5 = 0;
        while (i5 < 256) {
            if (bArr2[i5] != 0) {
                int i6 = 1;
                while (i6 <= 6 && (i3 = i5 + i6) < 256) {
                    byte b3 = bArr2[i3];
                    if (b3 != 0) {
                        byte b4 = bArr2[i5];
                        if ((b3 << i6) + b4 > 15) {
                            if (b4 - (b3 << i6) < -15) {
                                break;
                            }
                            bArr2[i5] = (byte) (b4 - (b3 << i6));
                            while (true) {
                                if (i3 >= 256) {
                                    break;
                                } else if (bArr2[i3] == 0) {
                                    bArr2[i3] = 1;
                                    break;
                                } else {
                                    bArr2[i3] = 0;
                                    i3++;
                                }
                            }
                        } else {
                            bArr2[i5] = (byte) (b4 + (b3 << i6));
                            bArr2[i3] = 0;
                        }
                    }
                    i6++;
                }
            }
            i5++;
        }
        return bArr2;
    }

    private static void sub(PartialXYZT partialXYZT, XYZT xyzt, CachedXYT cachedXYT) {
        long[] jArr = new long[10];
        long[] jArr2 = partialXYZT.xyz.f7028x;
        XYZ xyz = xyzt.xyz;
        Field25519.sum(jArr2, xyz.f7029y, xyz.f7028x);
        long[] jArr3 = partialXYZT.xyz.f7029y;
        XYZ xyz2 = xyzt.xyz;
        Field25519.sub(jArr3, xyz2.f7029y, xyz2.f7028x);
        long[] jArr4 = partialXYZT.xyz.f7029y;
        Field25519.mult(jArr4, jArr4, cachedXYT.yPlusX);
        XYZ xyz3 = partialXYZT.xyz;
        Field25519.mult(xyz3.f7030z, xyz3.f7028x, cachedXYT.yMinusX);
        Field25519.mult(partialXYZT.f7027t, xyzt.f7031t, cachedXYT.t2d);
        cachedXYT.multByZ(partialXYZT.xyz.f7028x, xyzt.xyz.f7030z);
        long[] jArr5 = partialXYZT.xyz.f7028x;
        Field25519.sum(jArr, jArr5, jArr5);
        XYZ xyz4 = partialXYZT.xyz;
        Field25519.sub(xyz4.f7028x, xyz4.f7030z, xyz4.f7029y);
        XYZ xyz5 = partialXYZT.xyz;
        long[] jArr6 = xyz5.f7029y;
        Field25519.sum(jArr6, xyz5.f7030z, jArr6);
        Field25519.sub(partialXYZT.xyz.f7030z, jArr, partialXYZT.f7027t);
        long[] jArr7 = partialXYZT.f7027t;
        Field25519.sum(jArr7, jArr, jArr7);
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        if (bArr2.length != 64) {
            return false;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr2, 32, 64);
        if (!isSmallerThanGroupOrder(copyOfRange)) {
            return false;
        }
        MessageDigest instance = EngineFactory.MESSAGE_DIGEST.getInstance("SHA-512");
        instance.update(bArr2, 0, 32);
        instance.update(bArr3);
        instance.update(bArr);
        byte[] digest = instance.digest();
        reduce(digest);
        byte[] bytes = doubleScalarMultVarTime(digest, XYZT.fromBytesNegateVarTime(bArr3), copyOfRange).toBytes();
        for (int i3 = 0; i3 < 32; i3++) {
            if (bytes[i3] != bArr2[i3]) {
                return false;
            }
        }
        return true;
    }
}
