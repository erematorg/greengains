package com.google.android.gms.internal.p002firebaseauthapi;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import androidx.annotation.RequiresApi;
import com.google.android.gms.stats.CodePackage;
import com.google.crypto.tink.integration.android.AndroidKeystoreKmsClient;
import com.reown.android.internal.common.crypto.kmr.BouncyCastleKeyManagementRepository;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Arrays;
import java.util.Locale;
import javax.crypto.KeyGenerator;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmc  reason: invalid package */
public final class zzmc implements zzcd {
    private static final Object zza = new Object();
    private static final String zzb = "zzmc";
    private final String zzc;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzmc$zza */
    public static final class zza {
        private String zza = null;

        @RequiresApi(23)
        public zza() {
            zzmc.zza();
        }
    }

    @RequiresApi(23)
    public zzmc() throws GeneralSecurityException {
        this(new zza());
    }

    public static /* synthetic */ boolean zza() {
        return true;
    }

    private static KeyStore zzb() throws GeneralSecurityException {
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load((KeyStore.LoadStoreParameter) null);
            return instance;
        } catch (IOException e3) {
            throw new GeneralSecurityException(e3);
        }
    }

    @RequiresApi(23)
    public static boolean zzc(String str) throws GeneralSecurityException {
        new zzmc();
        synchronized (zza) {
            try {
                if (zzd(str)) {
                    return false;
                }
                String zza2 = zzxq.zza(AndroidKeystoreKmsClient.PREFIX, str);
                KeyGenerator instance = KeyGenerator.getInstance(BouncyCastleKeyManagementRepository.AES, "AndroidKeyStore");
                instance.init(new KeyGenParameterSpec.Builder(zza2, 3).setKeySize(256).setBlockModes(new String[]{CodePackage.GCM}).setEncryptionPaddings(new String[]{"NoPadding"}).build());
                instance.generateKey();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static boolean zzd(String str) throws GeneralSecurityException {
        boolean containsAlias;
        String zza2 = zzxq.zza(AndroidKeystoreKmsClient.PREFIX, str);
        try {
            synchronized (zza) {
                containsAlias = zzb().containsAlias(zza2);
            }
            return containsAlias;
        } catch (NullPointerException unused) {
            Log.w(zzb, "Keystore is temporarily unavailable, wait, reinitialize Keystore and try again.");
            try {
                Thread.sleep((long) ((int) (Math.random() * 40.0d)));
            } catch (InterruptedException unused2) {
            }
            synchronized (zza) {
                return zzb().containsAlias(zza2);
            }
        }
    }

    private zzmc(zza zza2) {
        this.zzc = null;
    }

    public final zzbh zza(String str) throws GeneralSecurityException {
        zzma zzma;
        try {
            synchronized (zza) {
                zzma = new zzma(zzxq.zza(AndroidKeystoreKmsClient.PREFIX, str));
                byte[] zza2 = zzow.zza(10);
                byte[] bArr = new byte[0];
                if (!Arrays.equals(zza2, zzma.zza(zzma.zzb(zza2, bArr), bArr))) {
                    throw new KeyStoreException("cannot use Android Keystore: encryption/decryption of non-empty message and empty aad returns an incorrect result");
                }
            }
            return zzma;
        } catch (IOException e3) {
            throw new GeneralSecurityException(e3);
        } catch (Throwable th) {
            throw th;
        }
    }

    @RequiresApi(23)
    public final boolean zzb(String str) {
        return str.toLowerCase(Locale.US).startsWith(AndroidKeystoreKmsClient.PREFIX);
    }
}
