package com.google.crypto.tink.integration.android;

import android.util.Log;
import androidx.browser.trusted.c;
import com.google.crypto.tink.Aead;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.ProviderException;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public final class AndroidKeystoreAesGcm implements Aead {
    private static final int IV_SIZE_IN_BYTES = 12;
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 100;
    private static final String TAG = "AndroidKeystoreAesGcm";
    private static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey key;

    public AndroidKeystoreAesGcm(String str) throws GeneralSecurityException, IOException {
        KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
        instance.load((KeyStore.LoadStoreParameter) null);
        SecretKey secretKey = (SecretKey) instance.getKey(str, (char[]) null);
        this.key = secretKey;
        if (secretKey == null) {
            throw new InvalidKeyException(c.a("Keystore cannot load the key with ID: ", str));
        }
    }

    private byte[] decryptInternal(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        GCMParameterSpec gCMParameterSpec = new GCMParameterSpec(128, bArr, 0, 12);
        Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
        instance.init(2, this.key, gCMParameterSpec);
        instance.updateAAD(bArr2);
        return instance.doFinal(bArr, 12, bArr.length - 12);
    }

    private byte[] encryptInternal(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length <= 2147483619) {
            byte[] bArr3 = new byte[(bArr.length + 28)];
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(1, this.key);
            instance.updateAAD(bArr2);
            instance.doFinal(bArr, 0, bArr.length, bArr3, 12);
            System.arraycopy(instance.getIV(), 0, bArr3, 0, 12);
            return bArr3;
        }
        throw new GeneralSecurityException("plaintext too long");
    }

    private static void sleepRandomAmount() {
        try {
            Thread.sleep((long) ((int) (Math.random() * 100.0d)));
        } catch (InterruptedException unused) {
        }
    }

    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length >= 28) {
            try {
                return decryptInternal(bArr, bArr2);
            } catch (AEADBadTagException e3) {
                throw e3;
            } catch (GeneralSecurityException | ProviderException e4) {
                Log.w(TAG, "encountered a potentially transient KeyStore error, will wait and retry", e4);
                sleepRandomAmount();
                return decryptInternal(bArr, bArr2);
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }

    public byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            return encryptInternal(bArr, bArr2);
        } catch (GeneralSecurityException | ProviderException e3) {
            Log.w(TAG, "encountered a potentially transient KeyStore error, will wait and retry", e3);
            sleepRandomAmount();
            return encryptInternal(bArr, bArr2);
        }
    }

    public AndroidKeystoreAesGcm(String str, KeyStore keyStore) throws GeneralSecurityException {
        SecretKey secretKey = (SecretKey) keyStore.getKey(str, (char[]) null);
        this.key = secretKey;
        if (secretKey == null) {
            throw new InvalidKeyException(c.a("Keystore cannot load the key with ID: ", str));
        }
    }
}
