package com.google.android.gms.internal.p002firebaseauthapi;

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
import java.util.Arrays;
import javax.crypto.KeyAgreement;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzwr  reason: invalid package */
public final class zzwr {
    public static int zza(EllipticCurve ellipticCurve) throws GeneralSecurityException {
        return (zzmf.zza(ellipticCurve).subtract(BigInteger.ONE).bitLength() + 7) / 8;
    }

    private static void zzb(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        try {
            if (!zzmf.zza(eCPublicKey.getParams(), eCPrivateKey.getParams())) {
                throw new GeneralSecurityException("invalid public key spec");
            }
        } catch (IllegalArgumentException | NullPointerException e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    private static BigInteger zza(BigInteger bigInteger, boolean z2, EllipticCurve ellipticCurve) throws GeneralSecurityException {
        BigInteger bigInteger2;
        BigInteger zza = zzmf.zza(ellipticCurve);
        BigInteger mod = bigInteger.multiply(bigInteger).add(ellipticCurve.getA()).multiply(bigInteger).add(ellipticCurve.getB()).mod(zza);
        if (zza.signum() == 1) {
            BigInteger mod2 = mod.mod(zza);
            BigInteger bigInteger3 = BigInteger.ZERO;
            if (!mod2.equals(bigInteger3)) {
                if (zza.testBit(0) && zza.testBit(1)) {
                    bigInteger2 = mod2.modPow(zza.add(BigInteger.ONE).shiftRight(2), zza);
                } else if (!zza.testBit(0) || zza.testBit(1)) {
                    bigInteger2 = null;
                } else {
                    bigInteger3 = BigInteger.ONE;
                    BigInteger shiftRight = zza.subtract(bigInteger3).shiftRight(1);
                    int i3 = 0;
                    while (true) {
                        BigInteger mod3 = bigInteger3.multiply(bigInteger3).subtract(mod2).mod(zza);
                        if (mod3.equals(BigInteger.ZERO)) {
                            break;
                        }
                        BigInteger modPow = mod3.modPow(shiftRight, zza);
                        BigInteger bigInteger4 = BigInteger.ONE;
                        if (modPow.add(bigInteger4).equals(zza)) {
                            BigInteger shiftRight2 = zza.add(bigInteger4).shiftRight(1);
                            BigInteger bigInteger5 = bigInteger3;
                            for (int bitLength = shiftRight2.bitLength() - 2; bitLength >= 0; bitLength--) {
                                BigInteger multiply = bigInteger5.multiply(bigInteger4);
                                bigInteger5 = bigInteger5.multiply(bigInteger5).add(bigInteger4.multiply(bigInteger4).mod(zza).multiply(mod3)).mod(zza);
                                BigInteger mod4 = multiply.add(multiply).mod(zza);
                                if (shiftRight2.testBit(bitLength)) {
                                    BigInteger mod5 = bigInteger5.multiply(bigInteger3).add(mod4.multiply(mod3)).mod(zza);
                                    bigInteger4 = bigInteger3.multiply(mod4).add(bigInteger5).mod(zza);
                                    bigInteger5 = mod5;
                                } else {
                                    bigInteger4 = mod4;
                                }
                            }
                            bigInteger2 = bigInteger5;
                        } else if (modPow.equals(bigInteger4)) {
                            bigInteger3 = bigInteger3.add(bigInteger4);
                            i3++;
                            if (i3 == 128 && !zza.isProbablePrime(80)) {
                                throw new InvalidAlgorithmParameterException("p is not prime");
                            }
                        } else {
                            throw new InvalidAlgorithmParameterException("p is not prime");
                        }
                    }
                }
                if (bigInteger3 != null && bigInteger3.multiply(bigInteger3).mod(zza).compareTo(mod2) != 0) {
                    throw new GeneralSecurityException("Could not find a modular square root");
                }
            }
            return z2 != bigInteger3.testBit(0) ? zza.subtract(bigInteger3).mod(zza) : bigInteger3;
        }
        throw new InvalidAlgorithmParameterException("p must be positive");
    }

    public static KeyPair zza(ECParameterSpec eCParameterSpec) throws GeneralSecurityException {
        KeyPairGenerator zza = zzwv.zzd.zza("EC");
        zza.initialize(eCParameterSpec);
        return zza.generateKeyPair();
    }

    public static ECPrivateKey zza(zzwq zzwq, byte[] bArr) throws GeneralSecurityException {
        return (ECPrivateKey) zzwv.zze.zza("EC").generatePrivate(new ECPrivateKeySpec(zzmd.zza(bArr), zza(zzwq)));
    }

    public static ECPublicKey zza(zzwq zzwq, zzwt zzwt, byte[] bArr) throws GeneralSecurityException {
        return zza(zza(zzwq), zzwt, bArr);
    }

    public static ECPublicKey zza(ECParameterSpec eCParameterSpec, zzwt zzwt, byte[] bArr) throws GeneralSecurityException {
        return (ECPublicKey) zzwv.zze.zza("EC").generatePublic(new ECPublicKeySpec(zza(eCParameterSpec.getCurve(), zzwt, bArr), eCParameterSpec));
    }

    public static ECParameterSpec zza(zzwq zzwq) throws NoSuchAlgorithmException {
        int ordinal = zzwq.ordinal();
        if (ordinal == 0) {
            return zzmf.zza;
        }
        if (ordinal == 1) {
            return zzmf.zzb;
        }
        if (ordinal == 2) {
            return zzmf.zzc;
        }
        throw new NoSuchAlgorithmException("curve not implemented:".concat(String.valueOf(zzwq)));
    }

    public static ECPoint zza(EllipticCurve ellipticCurve, zzwt zzwt, byte[] bArr) throws GeneralSecurityException {
        int zza = zza(ellipticCurve);
        int ordinal = zzwt.ordinal();
        boolean z2 = false;
        if (ordinal != 0) {
            if (ordinal == 1) {
                BigInteger zza2 = zzmf.zza(ellipticCurve);
                if (bArr.length == zza + 1) {
                    byte b3 = bArr[0];
                    if (b3 != 2) {
                        if (b3 == 3) {
                            z2 = true;
                        } else {
                            throw new GeneralSecurityException("invalid format");
                        }
                    }
                    BigInteger bigInteger = new BigInteger(1, Arrays.copyOfRange(bArr, 1, bArr.length));
                    if (bigInteger.signum() != -1 && bigInteger.compareTo(zza2) < 0) {
                        return new ECPoint(bigInteger, zza(bigInteger, z2, ellipticCurve));
                    }
                    throw new GeneralSecurityException("x is out of range");
                }
                throw new GeneralSecurityException("compressed point has wrong length");
            } else if (ordinal != 2) {
                throw new GeneralSecurityException("invalid format:".concat(String.valueOf(zzwt)));
            } else if (bArr.length == zza * 2) {
                ECPoint eCPoint = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 0, zza)), new BigInteger(1, Arrays.copyOfRange(bArr, zza, bArr.length)));
                zzmf.zza(eCPoint, ellipticCurve);
                return eCPoint;
            } else {
                throw new GeneralSecurityException("invalid point size");
            }
        } else if (bArr.length != (zza * 2) + 1) {
            throw new GeneralSecurityException("invalid point size");
        } else if (bArr[0] == 4) {
            int i3 = zza + 1;
            ECPoint eCPoint2 = new ECPoint(new BigInteger(1, Arrays.copyOfRange(bArr, 1, i3)), new BigInteger(1, Arrays.copyOfRange(bArr, i3, bArr.length)));
            zzmf.zza(eCPoint2, ellipticCurve);
            return eCPoint2;
        } else {
            throw new GeneralSecurityException("invalid point format");
        }
    }

    public static void zza(ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey) throws GeneralSecurityException {
        zzb(eCPublicKey, eCPrivateKey);
        zzmf.zza(eCPublicKey.getW(), eCPrivateKey.getParams().getCurve());
    }

    public static byte[] zza(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws GeneralSecurityException {
        zzb(eCPublicKey, eCPrivateKey);
        return zza(eCPrivateKey, eCPublicKey.getW());
    }

    private static byte[] zza(ECPrivateKey eCPrivateKey, ECPoint eCPoint) throws GeneralSecurityException {
        zzmf.zza(eCPoint, eCPrivateKey.getParams().getCurve());
        PublicKey generatePublic = zzwv.zze.zza("EC").generatePublic(new ECPublicKeySpec(eCPoint, eCPrivateKey.getParams()));
        KeyAgreement zza = zzwv.zzc.zza("ECDH");
        zza.init(eCPrivateKey);
        try {
            zza.doPhase(generatePublic, true);
            byte[] generateSecret = zza.generateSecret();
            EllipticCurve curve = eCPrivateKey.getParams().getCurve();
            BigInteger bigInteger = new BigInteger(1, generateSecret);
            if (bigInteger.signum() == -1 || bigInteger.compareTo(zzmf.zza(curve)) >= 0) {
                throw new GeneralSecurityException("shared secret is out of range");
            }
            zza(bigInteger, true, curve);
            return generateSecret;
        } catch (IllegalStateException e3) {
            throw new GeneralSecurityException(e3);
        }
    }
}
