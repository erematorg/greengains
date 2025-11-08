package com.google.firebase.auth.internal;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.state.b;
import com.google.android.gms.internal.p002firebaseauthapi.zzbj;
import com.google.android.gms.internal.p002firebaseauthapi.zzbp;
import com.google.android.gms.internal.p002firebaseauthapi.zzce;
import com.google.android.gms.internal.p002firebaseauthapi.zzkh;
import com.google.android.gms.internal.p002firebaseauthapi.zzkq;
import com.google.android.gms.internal.p002firebaseauthapi.zzlz;
import com.google.android.gms.internal.p002firebaseauthapi.zzw;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public final class zzn {
    private static zzn zza;
    private final String zzb;
    @Nullable
    private final zzlz zzc;

    private zzn(Context context, String str, boolean z2) {
        zzlz zzlz;
        this.zzb = str;
        try {
            zzkh.zza();
            zzlz.zza zza2 = new zzlz.zza();
            zzlz.zza zza3 = zza2.zza(context, "GenericIdpKeyset", "com.google.firebase.auth.api.crypto." + str).zza(zzkq.zza);
            zza3.zza("android-keystore://firebear_master_key_id." + str);
            zzlz = zza3.zza();
        } catch (IOException | GeneralSecurityException e3) {
            b.u("Exception encountered during crypto setup:\n", e3.getMessage(), "FirebearCryptoHelper");
            zzlz = null;
        }
        this.zzc = zzlz;
    }

    public static zzn zza(Context context, String str) {
        zzn zzn = zza;
        if (zzn == null || !zzw.zza(zzn.zzb, str)) {
            zza = new zzn(context, str, true);
        }
        return zza;
    }

    @Nullable
    public final String zza(String str) {
        String str2;
        zzlz zzlz = this.zzc;
        if (zzlz == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to decrypt payload");
            return null;
        }
        try {
            synchronized (zzlz) {
                str2 = new String(((zzbp) this.zzc.zza().zza(zzbp.class)).zza(Base64.decode(str, 8), (byte[]) null), "UTF-8");
            }
            return str2;
        } catch (UnsupportedEncodingException | GeneralSecurityException e3) {
            b.u("Exception encountered while decrypting bytes:\n", e3.getMessage(), "FirebearCryptoHelper");
            return null;
        }
    }

    @Nullable
    public final String zza() {
        if (this.zzc == null) {
            Log.e("FirebearCryptoHelper", "KeysetManager failed to initialize - unable to get Public key");
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        zzce zza2 = zzbj.zza((OutputStream) byteArrayOutputStream);
        try {
            synchronized (this.zzc) {
                this.zzc.zza().zza().zza(zza2);
            }
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 8);
        } catch (IOException | GeneralSecurityException e3) {
            b.u("Exception encountered when attempting to get Public Key:\n", e3.getMessage(), "FirebearCryptoHelper");
            return null;
        }
    }
}
