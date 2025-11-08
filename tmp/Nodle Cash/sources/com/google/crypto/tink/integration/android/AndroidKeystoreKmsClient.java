package com.google.crypto.tink.integration.android;

import android.security.keystore.KeyGenParameterSpec;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.RequiresApi;
import com.google.android.gms.stats.CodePackage;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KmsClient;
import com.google.crypto.tink.subtle.Random;
import com.google.crypto.tink.subtle.Validators;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.concurrent.GuardedBy;
import javax.crypto.KeyGenerator;

public final class AndroidKeystoreKmsClient implements KmsClient {
    private static final int MAX_WAIT_TIME_MILLISECONDS_BEFORE_RETRY = 40;
    public static final String PREFIX = "android-keystore://";
    private static final String TAG = "AndroidKeystoreKmsClient";
    private static final Object keyCreationLock = new Object();
    @GuardedBy("this")
    private KeyStore keyStore;
    private final String keyUri;

    public static final class Builder {
        KeyStore keyStore = null;
        String keyUri = null;

        @RequiresApi(23)
        public Builder() {
            if (AndroidKeystoreKmsClient.isAtLeastM()) {
                try {
                    KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                    this.keyStore = instance;
                    instance.load((KeyStore.LoadStoreParameter) null);
                } catch (IOException | GeneralSecurityException e3) {
                    throw new IllegalStateException(e3);
                }
            } else {
                throw new IllegalStateException("need Android Keystore on Android M or newer");
            }
        }

        public AndroidKeystoreKmsClient build() {
            return new AndroidKeystoreKmsClient(this);
        }

        @RequiresApi(23)
        @CanIgnoreReturnValue
        public Builder setKeyStore(KeyStore keyStore2) {
            if (keyStore2 != null) {
                this.keyStore = keyStore2;
                return this;
            }
            throw new IllegalArgumentException("val cannot be null");
        }

        @RequiresApi(23)
        @CanIgnoreReturnValue
        public Builder setKeyUri(String str) {
            if (str == null || !str.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX)) {
                throw new IllegalArgumentException("val must start with android-keystore://");
            }
            this.keyUri = str;
            return this;
        }
    }

    @RequiresApi(23)
    public static boolean generateKeyIfNotExist(String str) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            try {
                if (androidKeystoreKmsClient.hasKey(str)) {
                    return false;
                }
                generateNewAesGcmKeyWithoutExistenceCheck(str);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RequiresApi(23)
    public static void generateNewAeadKey(String str) throws GeneralSecurityException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            try {
                if (!androidKeystoreKmsClient.hasKey(str)) {
                    generateNewAesGcmKeyWithoutExistenceCheck(str);
                } else {
                    throw new IllegalArgumentException("cannot generate a new key " + str + " because it already exists; please delete it with deleteKey() and try again");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @RequiresApi(23)
    public static void generateNewAesGcmKeyWithoutExistenceCheck(String str) throws GeneralSecurityException {
        String validateKmsKeyUriAndRemovePrefix = Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str);
        KeyGenerator instance = KeyGenerator.getInstance(BouncyCastleKeyManagementRepository.AES, "AndroidKeyStore");
        instance.init(new KeyGenParameterSpec.Builder(validateKmsKeyUriAndRemovePrefix, 3).setKeySize(256).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
        instance.generateKey();
    }

    @RequiresApi(23)
    public static Aead getOrGenerateNewAeadKey(String str) throws GeneralSecurityException, IOException {
        AndroidKeystoreKmsClient androidKeystoreKmsClient = new AndroidKeystoreKmsClient();
        synchronized (keyCreationLock) {
            try {
                if (!androidKeystoreKmsClient.hasKey(str)) {
                    generateNewAesGcmKeyWithoutExistenceCheck(str);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return androidKeystoreKmsClient.getAead(str);
    }

    /* access modifiers changed from: private */
    @ChecksSdkIntAtLeast(api = 23)
    public static boolean isAtLeastM() {
        return true;
    }

    private static void sleepRandomAmount() {
        try {
            Thread.sleep((long) ((int) (Math.random() * 40.0d)));
        } catch (InterruptedException unused) {
        }
    }

    private static Aead validateAead(Aead aead) throws GeneralSecurityException {
        byte[] randBytes = Random.randBytes(10);
        byte[] bArr = new byte[0];
        if (Arrays.equals(randBytes, aead.decrypt(aead.encrypt(randBytes, bArr), bArr))) {
            return aead;
        }
        throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
    }

    public synchronized void deleteKey(String str) throws GeneralSecurityException {
        this.keyStore.deleteEntry(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        return r1;
     */
    @androidx.annotation.RequiresApi(23)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean doesSupport(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = r2.keyUri     // Catch:{ all -> 0x000e }
            r1 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r2)
            return r1
        L_0x000e:
            r3 = move-exception
            goto L_0x0026
        L_0x0010:
            java.lang.String r0 = r2.keyUri     // Catch:{ all -> 0x000e }
            if (r0 != 0) goto L_0x0023
            java.util.Locale r0 = java.util.Locale.US     // Catch:{ all -> 0x000e }
            java.lang.String r3 = r3.toLowerCase(r0)     // Catch:{ all -> 0x000e }
            java.lang.String r0 = "android-keystore://"
            boolean r3 = r3.startsWith(r0)     // Catch:{ all -> 0x000e }
            if (r3 == 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            monitor-exit(r2)
            return r1
        L_0x0026:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.doesSupport(java.lang.String):boolean");
    }

    public synchronized Aead getAead(String str) throws GeneralSecurityException {
        try {
            String str2 = this.keyUri;
            if (str2 != null) {
                if (!str2.equals(str)) {
                    String str3 = this.keyUri;
                    throw new GeneralSecurityException("this client is bound to " + str3 + ", cannot load keys bound to " + str);
                }
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return validateAead(new AndroidKeystoreAesGcm(Validators.validateKmsKeyUriAndRemovePrefix(PREFIX, str), this.keyStore));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|(6:11|12|13|14|15|16)) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        android.util.Log.w(TAG, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        sleepRandomAmount();
        r0 = java.security.KeyStore.getInstance("AndroidKeyStore");
        r2.keyStore = r0;
        r0.load((java.security.KeyStore.LoadStoreParameter) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002e, code lost:
        return r2.keyStore.containsAlias(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0035, code lost:
        throw new java.security.GeneralSecurityException(r3);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0011 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean hasKey(java.lang.String r3) throws java.security.GeneralSecurityException {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r0 = "android-keystore://"
            java.lang.String r3 = com.google.crypto.tink.subtle.Validators.validateKmsKeyUriAndRemovePrefix(r0, r3)     // Catch:{ all -> 0x000f }
            java.security.KeyStore r0 = r2.keyStore     // Catch:{ NullPointerException -> 0x0011 }
            boolean r3 = r0.containsAlias(r3)     // Catch:{ NullPointerException -> 0x0011 }
            monitor-exit(r2)
            return r3
        L_0x000f:
            r3 = move-exception
            goto L_0x0036
        L_0x0011:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x000f }
            java.lang.String r1 = "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again."
            android.util.Log.w(r0, r1)     // Catch:{ all -> 0x000f }
            sleepRandomAmount()     // Catch:{ IOException -> 0x002f }
            java.lang.String r0 = "AndroidKeyStore"
            java.security.KeyStore r0 = java.security.KeyStore.getInstance(r0)     // Catch:{ IOException -> 0x002f }
            r2.keyStore = r0     // Catch:{ IOException -> 0x002f }
            r1 = 0
            r0.load(r1)     // Catch:{ IOException -> 0x002f }
            java.security.KeyStore r0 = r2.keyStore     // Catch:{ all -> 0x000f }
            boolean r3 = r0.containsAlias(r3)     // Catch:{ all -> 0x000f }
            monitor-exit(r2)
            return r3
        L_0x002f:
            r3 = move-exception
            java.security.GeneralSecurityException r0 = new java.security.GeneralSecurityException     // Catch:{ all -> 0x000f }
            r0.<init>(r3)     // Catch:{ all -> 0x000f }
            throw r0     // Catch:{ all -> 0x000f }
        L_0x0036:
            monitor-exit(r2)     // Catch:{ all -> 0x000f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient.hasKey(java.lang.String):boolean");
    }

    @RequiresApi(23)
    public KmsClient withCredentials(String str) throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @RequiresApi(23)
    public KmsClient withDefaultCredentials() throws GeneralSecurityException {
        return new AndroidKeystoreKmsClient();
    }

    @RequiresApi(23)
    public AndroidKeystoreKmsClient() throws GeneralSecurityException {
        this(new Builder());
    }

    @RequiresApi(23)
    @Deprecated
    public AndroidKeystoreKmsClient(String str) {
        this(new Builder().setKeyUri(str));
    }

    private AndroidKeystoreKmsClient(Builder builder) {
        this.keyUri = builder.keyUri;
        this.keyStore = builder.keyStore;
    }
}
