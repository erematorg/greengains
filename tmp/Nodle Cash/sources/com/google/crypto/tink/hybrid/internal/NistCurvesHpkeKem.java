package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

@Immutable
final class NistCurvesHpkeKem implements HpkeKem {
    private final EllipticCurves.CurveType curve;
    private final HkdfHpkeKdf hkdf;

    /* renamed from: com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.crypto.tink.subtle.EllipticCurves$CurveType[] r0 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType = r0
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r1 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P256     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r1 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P384     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.crypto.tink.subtle.EllipticCurves$CurveType r1 = com.google.crypto.tink.subtle.EllipticCurves.CurveType.NIST_P521     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.hybrid.internal.NistCurvesHpkeKem.AnonymousClass1.<clinit>():void");
        }
    }

    private NistCurvesHpkeKem(HkdfHpkeKdf hkdfHpkeKdf, EllipticCurves.CurveType curveType) {
        this.hkdf = hkdfHpkeKdf;
        this.curve = curveType;
    }

    private byte[] deriveKemSharedSecret(byte[] bArr, byte[] bArr2, byte[] bArr3) throws GeneralSecurityException {
        byte[] concat = Bytes.concat(bArr2, bArr3);
        byte[] kemSuiteId = HpkeUtil.kemSuiteId(getKemId());
        HkdfHpkeKdf hkdfHpkeKdf = this.hkdf;
        return hkdfHpkeKdf.extractAndExpand((byte[]) null, bArr, "eae_prk", concat, "shared_secret", kemSuiteId, hkdfHpkeKdf.getMacLength());
    }

    public static NistCurvesHpkeKem fromCurve(EllipticCurves.CurveType curveType) throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[curveType.ordinal()];
        if (i3 == 1) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha256"), EllipticCurves.CurveType.NIST_P256);
        }
        if (i3 == 2) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha384"), EllipticCurves.CurveType.NIST_P384);
        }
        if (i3 == 3) {
            return new NistCurvesHpkeKem(new HkdfHpkeKdf("HmacSha512"), EllipticCurves.CurveType.NIST_P521);
        }
        throw new GeneralSecurityException("invalid curve type: " + curveType);
    }

    public byte[] decapsulate(byte[] bArr, HpkeKemPrivateKey hpkeKemPrivateKey) throws GeneralSecurityException {
        return deriveKemSharedSecret(EllipticCurves.computeSharedSecret(EllipticCurves.getEcPrivateKey(this.curve, hpkeKemPrivateKey.getSerializedPrivate().toByteArray()), EllipticCurves.getEcPublicKey(this.curve, EllipticCurves.PointFormatType.UNCOMPRESSED, bArr)), bArr, hpkeKemPrivateKey.getSerializedPublic().toByteArray());
    }

    public HpkeKemEncapOutput encapsulate(byte[] bArr, KeyPair keyPair) throws GeneralSecurityException {
        EllipticCurves.CurveType curveType = this.curve;
        EllipticCurves.PointFormatType pointFormatType = EllipticCurves.PointFormatType.UNCOMPRESSED;
        byte[] computeSharedSecret = EllipticCurves.computeSharedSecret((ECPrivateKey) keyPair.getPrivate(), EllipticCurves.getEcPublicKey(curveType, pointFormatType, bArr));
        byte[] pointEncode = EllipticCurves.pointEncode(this.curve, pointFormatType, ((ECPublicKey) keyPair.getPublic()).getW());
        return new HpkeKemEncapOutput(deriveKemSharedSecret(computeSharedSecret, pointEncode, bArr), pointEncode);
    }

    public byte[] getKemId() throws GeneralSecurityException {
        int i3 = AnonymousClass1.$SwitchMap$com$google$crypto$tink$subtle$EllipticCurves$CurveType[this.curve.ordinal()];
        if (i3 == 1) {
            return HpkeUtil.P256_HKDF_SHA256_KEM_ID;
        }
        if (i3 == 2) {
            return HpkeUtil.P384_HKDF_SHA384_KEM_ID;
        }
        if (i3 == 3) {
            return HpkeUtil.P521_HKDF_SHA512_KEM_ID;
        }
        throw new GeneralSecurityException("Could not determine HPKE KEM ID");
    }

    public HpkeKemEncapOutput encapsulate(byte[] bArr) throws GeneralSecurityException {
        return encapsulate(bArr, EllipticCurves.generateKeyPair(this.curve));
    }
}
