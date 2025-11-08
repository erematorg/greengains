package com.google.crypto.tink.subtle;

import com.google.crypto.tink.internal.BigIntegerEncoding;
import com.google.crypto.tink.internal.EllipticCurvesUtil;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.KeyAgreement;

public final class EllipticCurves {

    /* renamed from: com.google.crypto.tink.subtle.EllipticCurves$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        static {
            /*
                com.google.crypto.tink.subtle.EllipticCurves$CurveType[] r0 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = r0
                r1 = 1
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r2 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r3 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r4 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P521     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.crypto.tink.subtle.EllipticCurves$PointFormatType[] r3 = com.google.crypto.tink.subtle.EllipticCurves.PointFormatType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType = r3
                com.google.crypto.tink.subtle.EllipticCurves$PointFormatType r4 = com.google.crypto.tink.subtle.EllipticCurves.PointFormatType.UNCOMPRESSED     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.crypto.tink.subtle.EllipticCurves$PointFormatType r3 = com.google.crypto.tink.subtle.EllipticCurves.PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.crypto.tink.subtle.EllipticCurves$PointFormatType r1 = com.google.crypto.tink.subtle.EllipticCurves.PointFormatType.COMPRESSED     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.EllipticCurves.AnonymousClass1.<clinit>():void");
        }
    }

    public enum CurveType {
        NIST_P256,
        NIST_P384,
        NIST_P521
    }

    public enum EcdsaEncoding {
        IEEE_P1363,
        DER
    }

    public enum PointFormatType {
        UNCOMPRESSED,
        COMPRESSED,
        DO_NOT_USE_CRUNCHY_UNCOMPRESSED
    }

    private EllipticCurves() {
    }

    public static void checkPublicKey(ECPublicKey eCPublicKey) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        return computeSharedSecret(eCPrivateKey, eCPublicKey.getW());
    }

    public static ECPoint ecPointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return pointDecode(ellipticCurve, pointFormatType, bArr);
    }

    public static byte[] ecdsaDer2Ieee(byte[] bArr, int i3) throws GeneralSecurityException {
        if (isValidDerEncoding(bArr)) {
            byte[] bArr2 = new byte[i3];
            byte b3 = 1;
            int i4 = (bArr[1] & 255) >= 128 ? 3 : 2;
            int i5 = i4 + 1;
            int i6 = i4 + 2;
            byte b4 = bArr[i5];
            byte b5 = bArr[i6] == 0 ? (byte) 1 : 0;
            System.arraycopy(bArr, i6 + b5, bArr2, ((i3 / 2) - b4) + b5, b4 - b5);
            int i7 = b4 + 1 + i6;
            int i8 = i7 + 1;
            byte b6 = bArr[i7];
            if (bArr[i8] != 0) {
                b3 = 0;
            }
            System.arraycopy(bArr, i8 + b3, bArr2, (i3 - b6) + b3, b6 - b3);
            return bArr2;
        }
        throw new GeneralSecurityException("Invalid DER encoding");
    }

    public static byte[] ecdsaIeee2Der(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2;
        int i3;
        if (bArr.length % 2 != 0 || bArr.length == 0 || bArr.length > 132) {
            throw new GeneralSecurityException("Invalid IEEE_P1363 encoding");
        }
        byte[] minimalSignedNumber = toMinimalSignedNumber(Arrays.copyOf(bArr, bArr.length / 2));
        byte[] minimalSignedNumber2 = toMinimalSignedNumber(Arrays.copyOfRange(bArr, bArr.length / 2, bArr.length));
        int length = minimalSignedNumber.length + 4 + minimalSignedNumber2.length;
        if (length >= 128) {
            bArr2 = new byte[(length + 3)];
            bArr2[0] = 48;
            bArr2[1] = -127;
            bArr2[2] = (byte) length;
            i3 = 3;
        } else {
            bArr2 = new byte[(length + 2)];
            bArr2[0] = 48;
            bArr2[1] = (byte) length;
            i3 = 2;
        }
        int i4 = i3 + 1;
        bArr2[i3] = 2;
        int i5 = i3 + 2;
        bArr2[i4] = (byte) minimalSignedNumber.length;
        System.arraycopy(minimalSignedNumber, 0, bArr2, i5, minimalSignedNumber.length);
        int length2 = i5 + minimalSignedNumber.length;
        bArr2[length2] = 2;
        bArr2[length2 + 1] = (byte) minimalSignedNumber2.length;
        System.arraycopy(minimalSignedNumber2, 0, bArr2, length2 + 2, minimalSignedNumber2.length);
        return bArr2;
    }

    public static int encodingSizeInBytes(EllipticCurve ellipticCurve, PointFormatType pointFormatType) throws GeneralSecurityException {
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        if (i3 == 1) {
            return (fieldSizeInBytes * 2) + 1;
        }
        if (i3 == 2) {
            return fieldSizeInBytes * 2;
        }
        if (i3 == 3) {
            return fieldSizeInBytes + 1;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }

    public static int fieldSizeInBits(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return getModulus(ellipticCurve).subtract(BigInteger.ONE).bitLength();
    }

    public static int fieldSizeInBytes(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (fieldSizeInBits(ellipticCurve) + 7) / 8;
    }

    public static KeyPair generateKeyPair(CurveType curveType) throws GeneralSecurityException {
        return generateKeyPair(getCurveSpec(curveType));
    }

    public static ECParameterSpec getCurveSpec(CurveType curveType) throws NoSuchAlgorithmException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curveType.ordinal()];
        if (i3 == 1) {
            return getNistP256Params();
        }
        if (i3 == 2) {
            return getNistP384Params();
        }
        if (i3 == 3) {
            return getNistP521Params();
        }
        throw new NoSuchAlgorithmException("curve not implemented:" + curveType);
    }

    public static ECPrivateKey getEcPrivateKey(byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static ECPublicKey getEcPublicKey(byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static BigInteger getModulus(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return EllipticCurvesUtil.getModulus(ellipticCurve);
    }

    public static ECParameterSpec getNistP256Params() {
        return EllipticCurvesUtil.NIST_P256_PARAMS;
    }

    public static ECParameterSpec getNistP384Params() {
        return EllipticCurvesUtil.NIST_P384_PARAMS;
    }

    public static ECParameterSpec getNistP521Params() {
        return EllipticCurvesUtil.NIST_P521_PARAMS;
    }

    public static BigInteger getY(BigInteger bigInteger, boolean z2, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger modulus = getModulus(ellipticCurve);
        BigInteger modSqrt = modSqrt(bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(modulus), modulus);
        return z2 != modSqrt.testBit(0) ? modulus.subtract(modSqrt).mod(modulus) : modSqrt;
    }

    public static boolean isNistEcParameterSpec(ECParameterSpec eCParameterSpec) {
        return EllipticCurvesUtil.isNistEcParameterSpec(eCParameterSpec);
    }

    public static boolean isSameEcParameterSpec(ECParameterSpec eCParameterSpec, ECParameterSpec eCParameterSpec2) {
        return EllipticCurvesUtil.isSameEcParameterSpec(eCParameterSpec, eCParameterSpec2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r1 = r11[r3 + 2] & 255;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isValidDerEncoding(byte[] r11) {
        /*
            int r0 = r11.length
            r1 = 8
            r2 = 0
            if (r0 >= r1) goto L_0x0007
            return r2
        L_0x0007:
            byte r0 = r11[r2]
            r1 = 48
            if (r0 == r1) goto L_0x000e
            return r2
        L_0x000e:
            r0 = 1
            byte r1 = r11[r0]
            r1 = r1 & 255(0xff, float:3.57E-43)
            r3 = 129(0x81, float:1.81E-43)
            r4 = 128(0x80, float:1.794E-43)
            r5 = 2
            if (r1 != r3) goto L_0x0023
            byte r1 = r11[r5]
            r1 = r1 & 255(0xff, float:3.57E-43)
            if (r1 >= r4) goto L_0x0021
            return r2
        L_0x0021:
            r3 = r5
            goto L_0x0029
        L_0x0023:
            if (r1 == r4) goto L_0x008a
            if (r1 <= r3) goto L_0x0028
            goto L_0x008a
        L_0x0028:
            r3 = r0
        L_0x0029:
            int r6 = r11.length
            int r6 = r6 - r0
            int r6 = r6 - r3
            if (r1 == r6) goto L_0x002f
            return r2
        L_0x002f:
            int r1 = r3 + 1
            byte r1 = r11[r1]
            if (r1 == r5) goto L_0x0036
            return r2
        L_0x0036:
            int r1 = r3 + 2
            byte r1 = r11[r1]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r6 = r3 + 3
            int r6 = r6 + r1
            int r7 = r6 + 1
            int r8 = r11.length
            if (r7 < r8) goto L_0x0045
            return r2
        L_0x0045:
            if (r1 != 0) goto L_0x0048
            return r2
        L_0x0048:
            int r8 = r3 + 3
            byte r9 = r11[r8]
            r10 = r9 & 255(0xff, float:3.57E-43)
            if (r10 < r4) goto L_0x0051
            return r2
        L_0x0051:
            if (r1 <= r0) goto L_0x005e
            if (r9 != 0) goto L_0x005e
            int r9 = r3 + 4
            byte r9 = r11[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            if (r9 >= r4) goto L_0x005e
            return r2
        L_0x005e:
            int r8 = r8 + r1
            byte r8 = r11[r8]
            if (r8 == r5) goto L_0x0064
            return r2
        L_0x0064:
            byte r7 = r11[r7]
            r7 = r7 & 255(0xff, float:3.57E-43)
            int r6 = r6 + r5
            int r6 = r6 + r7
            int r5 = r11.length
            if (r6 == r5) goto L_0x006e
            return r2
        L_0x006e:
            if (r7 != 0) goto L_0x0071
            return r2
        L_0x0071:
            int r5 = r3 + 5
            int r5 = r5 + r1
            byte r5 = r11[r5]
            r6 = r5 & 255(0xff, float:3.57E-43)
            if (r6 < r4) goto L_0x007b
            return r2
        L_0x007b:
            if (r7 <= r0) goto L_0x0089
            if (r5 != 0) goto L_0x0089
            int r3 = r3 + 6
            int r3 = r3 + r1
            byte r11 = r11[r3]
            r11 = r11 & 255(0xff, float:3.57E-43)
            if (r11 >= r4) goto L_0x0089
            return r2
        L_0x0089:
            return r0
        L_0x008a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.subtle.EllipticCurves.isValidDerEncoding(byte[]):boolean");
    }

    public static BigInteger modSqrt(BigInteger bigInteger, BigInteger bigInteger2) throws GeneralSecurityException {
        BigInteger bigInteger3;
        if (bigInteger2.signum() == 1) {
            BigInteger mod = bigInteger.mod(bigInteger2);
            BigInteger bigInteger4 = BigInteger.ZERO;
            if (mod.equals(bigInteger4)) {
                return bigInteger4;
            }
            int i3 = 0;
            if (bigInteger2.testBit(0) && bigInteger2.testBit(1)) {
                bigInteger3 = mod.modPow(bigInteger2.add(BigInteger.ONE).shiftRight(2), bigInteger2);
            } else if (!bigInteger2.testBit(0) || bigInteger2.testBit(1)) {
                bigInteger3 = null;
            } else {
                BigInteger bigInteger5 = BigInteger.ONE;
                BigInteger shiftRight = bigInteger2.subtract(bigInteger5).shiftRight(1);
                while (true) {
                    BigInteger mod2 = bigInteger5.multiply(bigInteger5).subtract(mod).mod(bigInteger2);
                    if (mod2.equals(BigInteger.ZERO)) {
                        return bigInteger5;
                    }
                    BigInteger modPow = mod2.modPow(shiftRight, bigInteger2);
                    BigInteger bigInteger6 = BigInteger.ONE;
                    if (modPow.add(bigInteger6).equals(bigInteger2)) {
                        BigInteger shiftRight2 = bigInteger2.add(bigInteger6).shiftRight(1);
                        BigInteger bigInteger7 = bigInteger5;
                        for (int bitLength = shiftRight2.bitLength() - 2; bitLength >= 0; bitLength--) {
                            BigInteger multiply = bigInteger7.multiply(bigInteger6);
                            bigInteger7 = bigInteger7.multiply(bigInteger7).add(bigInteger6.multiply(bigInteger6).mod(bigInteger2).multiply(mod2)).mod(bigInteger2);
                            BigInteger mod3 = multiply.add(multiply).mod(bigInteger2);
                            if (shiftRight2.testBit(bitLength)) {
                                BigInteger mod4 = bigInteger7.multiply(bigInteger5).add(mod3.multiply(mod2)).mod(bigInteger2);
                                bigInteger6 = bigInteger5.multiply(mod3).add(bigInteger7).mod(bigInteger2);
                                bigInteger7 = mod4;
                            } else {
                                bigInteger6 = mod3;
                            }
                        }
                        bigInteger3 = bigInteger7;
                    } else if (modPow.equals(bigInteger6)) {
                        bigInteger5 = bigInteger5.add(bigInteger6);
                        i3++;
                        if (i3 == 128 && !bigInteger2.isProbablePrime(80)) {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                    } else {
                        throw new InvalidAlgorithmParameterException("p is not prime");
                    }
                }
            }
            if (bigInteger3 == null || bigInteger3.multiply(bigInteger3).mod(bigInteger2).compareTo(mod) == 0) {
                return bigInteger3;
            }
            throw new GeneralSecurityException("Could not find a modular square root");
        }
        throw new InvalidAlgorithmParameterException("p must be positive");
    }

    public static ECPoint pointDecode(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return pointDecode(getCurveSpec(curveType).getCurve(), pointFormatType, bArr);
    }

    public static byte[] pointEncode(CurveType curveType, PointFormatType pointFormatType, ECPoint eCPoint) throws GeneralSecurityException {
        return pointEncode(getCurveSpec(curveType).getCurve(), pointFormatType, eCPoint);
    }

    private static byte[] toMinimalSignedNumber(byte[] bArr) {
        int i3 = 0;
        int i4 = 0;
        while (i4 < bArr.length && bArr[i4] == 0) {
            i4++;
        }
        if (i4 == bArr.length) {
            i4 = bArr.length - 1;
        }
        if ((bArr[i4] & 128) == 128) {
            i3 = 1;
        }
        byte[] bArr2 = new byte[((bArr.length - i4) + i3)];
        System.arraycopy(bArr, i4, bArr2, i3, bArr.length - i4);
        return bArr2;
    }

    public static void validatePublicKey(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        validatePublicKeySpec(eCPublicKey, eCPrivateKey);
        EllipticCurvesUtil.checkPointOnCurve(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    public static void validatePublicKeySpec(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        try {
            if (!isSameEcParameterSpec(eCPublicKey.getParams(), eCPrivateKey.getParams())) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    private static void validateSharedSecret(byte[] bArr, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        EllipticCurve curve = eCPrivateKey.getParams().getCurve();
        BigInteger bigInteger = new BigInteger(1, bArr);
        if (bigInteger.signum() == -1 || bigInteger.compareTo(getModulus(curve)) >= 0) {
            throw new GeneralSecurityException("shared secret is out of range");
        }
        getY(bigInteger, true, curve);
    }

    public static KeyPair generateKeyPair(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator instance = EngineFactory.KEY_PAIR_GENERATOR.getInstance("EC");
        instance.initialize(eCParameterSpec);
        return instance.generateKeyPair();
    }

    public static ECPoint pointDecode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        boolean z2 = false;
        if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    BigInteger modulus = getModulus(ellipticCurve);
                    if (bArr.length == fieldSizeInBytes + 1) {
                        byte b3 = bArr[0];
                        if (b3 != 2) {
                            if (b3 == 3) {
                                z2 = true;
                            } else {
                                throw new GeneralSecurityException("invalid format");
                            }
                        }
                        BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
                        if (bigInteger.signum() != -1 && bigInteger.compareTo(modulus) < 0) {
                            return new ECPoint(bigInteger, getY(bigInteger, z2, ellipticCurve));
                        }
                        throw new GeneralSecurityException("x is out of range");
                    }
                    throw new GeneralSecurityException("compressed point has wrong length");
                }
                throw new GeneralSecurityException("invalid format:" + pointFormatType);
            } else if (bArr.length == fieldSizeInBytes * 2) {
                ECPoint eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 0, fieldSizeInBytes)), new BigInteger(1, Arrays.copyOfRange(bArr, fieldSizeInBytes, bArr.length)));
                EllipticCurvesUtil.checkPointOnCurve(eCPoint, ellipticCurve);
                return eCPoint;
            } else {
                throw new GeneralSecurityException("invalid point size");
            }
        } else if (bArr.length != (fieldSizeInBytes * 2) + 1) {
            throw new GeneralSecurityException("invalid point size");
        } else if (bArr[0] == 4) {
            int i4 = fieldSizeInBytes + 1;
            ECPoint eCPoint2 = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i4)), new BigInteger(1, Arrays.copyOfRange(bArr, i4, bArr.length)));
            EllipticCurvesUtil.checkPointOnCurve(eCPoint2, ellipticCurve);
            return eCPoint2;
        } else {
            throw new GeneralSecurityException("invalid point format");
        }
    }

    public static byte[] pointEncode(EllipticCurve ellipticCurve, PointFormatType pointFormatType, ECPoint eCPoint) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, ellipticCurve);
        int fieldSizeInBytes = fieldSizeInBytes(ellipticCurve);
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$PointFormatType[pointFormatType.ordinal()];
        if (i3 != 1) {
            int i4 = 2;
            if (i3 == 2) {
                int i5 = fieldSizeInBytes * 2;
                byte[] bArr = new byte[i5];
                byte[] bigEndianBytes = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
                if (bigEndianBytes.length > fieldSizeInBytes) {
                    bigEndianBytes = Arrays.copyOfRange(bigEndianBytes, bigEndianBytes.length - fieldSizeInBytes, bigEndianBytes.length);
                }
                byte[] bigEndianBytes2 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineY());
                if (bigEndianBytes2.length > fieldSizeInBytes) {
                    bigEndianBytes2 = Arrays.copyOfRange(bigEndianBytes2, bigEndianBytes2.length - fieldSizeInBytes, bigEndianBytes2.length);
                }
                System.arraycopy(bigEndianBytes2, 0, bArr, i5 - bigEndianBytes2.length, bigEndianBytes2.length);
                System.arraycopy(bigEndianBytes, 0, bArr, fieldSizeInBytes - bigEndianBytes.length, bigEndianBytes.length);
                return bArr;
            } else if (i3 == 3) {
                int i6 = fieldSizeInBytes + 1;
                byte[] bArr2 = new byte[i6];
                byte[] bigEndianBytes3 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
                System.arraycopy(bigEndianBytes3, 0, bArr2, i6 - bigEndianBytes3.length, bigEndianBytes3.length);
                if (eCPoint.getAffineY().testBit(0)) {
                    i4 = 3;
                }
                bArr2[0] = (byte) i4;
                return bArr2;
            } else {
                throw new GeneralSecurityException("invalid format:" + pointFormatType);
            }
        } else {
            int i7 = (fieldSizeInBytes * 2) + 1;
            byte[] bArr3 = new byte[i7];
            byte[] bigEndianBytes4 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineX());
            byte[] bigEndianBytes5 = BigIntegerEncoding.toBigEndianBytes(eCPoint.getAffineY());
            System.arraycopy(bigEndianBytes5, 0, bArr3, i7 - bigEndianBytes5.length, bigEndianBytes5.length);
            System.arraycopy(bigEndianBytes4, 0, bArr3, (fieldSizeInBytes + 1) - bigEndianBytes4.length, bigEndianBytes4.length);
            bArr3[0] = 4;
            return bArr3;
        }
    }

    public static byte[] computeSharedSecret(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, eCPrivateKey.getParams().getCurve());
        PublicKey generatePublic = EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCPrivateKey.getParams()));
        KeyAgreement instance = EngineFactory.KEY_AGREEMENT.getInstance("ECDH");
        instance.init(eCPrivateKey);
        try {
            instance.doPhase(generatePublic, true);
            byte[] generateSecret = instance.generateSecret();
            validateSharedSecret(generateSecret, eCPrivateKey);
            return generateSecret;
        } catch (IllegalStateException e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    public static ECPrivateKey getEcPrivateKey(CurveType curveType, byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePrivate(new ECPrivateKeySpec(BigIntegerEncoding.fromUnsignedBigEndianBytes(bArr), getCurveSpec(curveType)));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return getEcPublicKey(getCurveSpec(curveType), pointFormatType, bArr);
    }

    public static ECPublicKey getEcPublicKey(ECParameterSpec eCParameterSpec, PointFormatType pointFormatType, byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(pointDecode(eCParameterSpec.getCurve(), pointFormatType, bArr), eCParameterSpec));
    }

    public static ECPublicKey getEcPublicKey(CurveType curveType, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        ECParameterSpec curveSpec = getCurveSpec(curveType);
        ECPoint eCPoint = new ECPoint(new BigInteger(1, bArr), new BigInteger(1, bArr2));
        EllipticCurvesUtil.checkPointOnCurve(eCPoint, curveSpec.getCurve());
        return (ECPublicKey) EngineFactory.KEY_FACTORY.getInstance("EC").generatePublic(new ECPublicKeySpec(eCPoint, curveSpec));
    }
}
