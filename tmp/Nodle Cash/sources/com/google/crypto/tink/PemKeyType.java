package com.google.crypto.tink;

import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.interfaces.ECKey;
import java.security.interfaces.RSAKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public enum PemKeyType {
    RSA_PSS_2048_SHA256("RSA", "RSASSA-PSS", 2048, r6),
    RSA_PSS_3072_SHA256("RSA", "RSASSA-PSS", 3072, r6),
    RSA_PSS_4096_SHA256("RSA", "RSASSA-PSS", 4096, r6),
    RSA_PSS_4096_SHA512("RSA", "RSASSA-PSS", 4096, r19),
    RSA_SIGN_PKCS1_2048_SHA256("RSA", "RSASSA-PKCS1-v1_5", 2048, r6),
    RSA_SIGN_PKCS1_3072_SHA256("RSA", "RSASSA-PKCS1-v1_5", 3072, r6),
    RSA_SIGN_PKCS1_4096_SHA256("RSA", "RSASSA-PKCS1-v1_5", 4096, r6),
    RSA_SIGN_PKCS1_4096_SHA512("RSA", "RSASSA-PKCS1-v1_5", 4096, r19),
    ECDSA_P256_SHA256("EC", "ECDSA", 256, r8),
    ECDSA_P384_SHA384("EC", "ECDSA", KyberEngine.KyberPolyBytes, Enums.HashType.SHA384),
    ECDSA_P521_SHA512("EC", "ECDSA", 521, r19);
    
    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";
    private static final String MARKER = "-----";
    private static final String PRIVATE_KEY = "PRIVATE KEY";
    private static final String PUBLIC_KEY = "PUBLIC KEY";
    public final String algorithm;
    public final Enums.HashType hash;
    public final int keySizeInBits;
    public final String keyType;

    private PemKeyType(String str, String str2, int i3, Enums.HashType hashType) {
        this.keyType = str;
        this.algorithm = str2;
        this.keySizeInBits = i3;
        this.hash = hashType;
    }

    private Key getPrivateKey(byte[] bArr) throws GeneralSecurityException {
        return validate(EngineFactory.KEY_FACTORY.getInstance(this.keyType).generatePrivate(new PKCS8EncodedKeySpec(bArr)));
    }

    private Key getPublicKey(byte[] bArr) throws GeneralSecurityException {
        return validate(EngineFactory.KEY_FACTORY.getInstance(this.keyType).generatePublic(new X509EncodedKeySpec(bArr)));
    }

    private Key validate(Key key) throws GeneralSecurityException {
        if (this.keyType.equals("RSA")) {
            int bitLength = ((RSAKey) key).getModulus().bitLength();
            if (bitLength != this.keySizeInBits) {
                throw new GeneralSecurityException(String.format("invalid RSA key size, want %d got %d", new Object[]{Integer.valueOf(this.keySizeInBits), Integer.valueOf(bitLength)}));
            }
        } else {
            ECParameterSpec params = ((ECKey) key).getParams();
            if (EllipticCurves.isNistEcParameterSpec(params)) {
                int fieldSizeInBits = EllipticCurves.fieldSizeInBits(params.getCurve());
                if (fieldSizeInBits != this.keySizeInBits) {
                    throw new GeneralSecurityException(String.format("invalid EC key size, want %d got %d", new Object[]{Integer.valueOf(this.keySizeInBits), Integer.valueOf(fieldSizeInBits)}));
                }
            } else {
                throw new GeneralSecurityException("unsupport EC spec: " + params.toString());
            }
        }
        return key;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r0 = r0.trim().substring(11);
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.Key readKey(java.io.BufferedReader r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = r8.readLine()
        L_0x0004:
            if (r0 == 0) goto L_0x0013
            java.lang.String r1 = "-----BEGIN "
            boolean r1 = r0.startsWith(r1)
            if (r1 != 0) goto L_0x0013
            java.lang.String r0 = r8.readLine()
            goto L_0x0004
        L_0x0013:
            r1 = 0
            if (r0 != 0) goto L_0x0017
            return r1
        L_0x0017:
            java.lang.String r0 = r0.trim()
            r2 = 11
            java.lang.String r0 = r0.substring(r2)
            java.lang.String r2 = "-----"
            int r3 = r0.indexOf(r2)
            if (r3 >= 0) goto L_0x002a
            return r1
        L_0x002a:
            r4 = 0
            java.lang.String r0 = r0.substring(r4, r3)
            java.lang.String r3 = "-----END "
            java.lang.String r2 = A.a.l(r3, r0, r2)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
        L_0x003a:
            java.lang.String r5 = r8.readLine()
            if (r5 == 0) goto L_0x0054
            java.lang.String r6 = ":"
            int r6 = r5.indexOf(r6)
            if (r6 <= 0) goto L_0x0049
            goto L_0x003a
        L_0x0049:
            boolean r6 = r5.contains(r2)
            if (r6 == 0) goto L_0x0050
            goto L_0x0054
        L_0x0050:
            r3.append(r5)
            goto L_0x003a
        L_0x0054:
            java.lang.String r8 = r3.toString()     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            byte[] r8 = com.google.crypto.tink.subtle.Base64.decode((java.lang.String) r8, (int) r4)     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            java.lang.String r2 = "PUBLIC KEY"
            boolean r2 = r0.contains(r2)     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            if (r2 == 0) goto L_0x0069
            java.security.Key r7 = r7.getPublicKey(r8)     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            return r7
        L_0x0069:
            java.lang.String r2 = "PRIVATE KEY"
            boolean r0 = r0.contains(r2)     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            if (r0 == 0) goto L_0x0076
            java.security.Key r7 = r7.getPrivateKey(r8)     // Catch:{ IllegalArgumentException | GeneralSecurityException -> 0x0076 }
            return r7
        L_0x0076:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.PemKeyType.readKey(java.io.BufferedReader):java.security.Key");
    }
}
