package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.browser.trusted.c;
import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.zzal;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzack  reason: invalid package */
public final class zzack extends AsyncTask<Void, Void, zzacj> {
    private static final Logger zza = new Logger("FirebaseAuth", "GetAuthDomainTask");
    private final String zzb;
    private final String zzc;
    private final WeakReference<zzacm> zzd;
    private final Uri.Builder zze;
    private final String zzf;
    private final FirebaseApp zzg;

    public zzack(String str, String str2, Intent intent, FirebaseApp firebaseApp, zzacm zzacm) {
        this.zzb = Preconditions.checkNotEmpty(str);
        this.zzg = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotNull(intent);
        String checkNotEmpty = Preconditions.checkNotEmpty(intent.getStringExtra("com.google.firebase.auth.KEY_API_KEY"));
        Uri.Builder buildUpon = Uri.parse(zzacm.zza(checkNotEmpty)).buildUpon();
        buildUpon.appendPath("getProjectConfig").appendQueryParameter(JwtUtilsKt.DID_METHOD_KEY, checkNotEmpty).appendQueryParameter("androidPackageName", str).appendQueryParameter("sha1Cert", (String) Preconditions.checkNotNull(str2));
        this.zzc = buildUpon.build().toString();
        this.zzd = new WeakReference<>(zzacm);
        this.zze = zzacm.zza(intent, str, str2);
        this.zzf = intent.getStringExtra("com.google.firebase.auth.KEY_CUSTOM_AUTH_DOMAIN");
    }

    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zza */
    public final zzacj doInBackground(Void... voidArr) {
        try {
            URL url = new URL(this.zzc);
            zzacm zzacm = this.zzd.get();
            HttpURLConnection zza2 = zzacm.zza(url);
            zza2.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
            zza2.setConnectTimeout(60000);
            new zzacv(zzacm.zza(), this.zzg, zzact.zza().zzb()).zza((URLConnection) zza2);
            int responseCode = zza2.getResponseCode();
            if (responseCode != 200) {
                String zza3 = zza(zza2);
                Logger logger = zza;
                logger.e("Error getting project config. Failed with " + zza3 + StringUtils.SPACE + responseCode, new Object[0]);
                return zzacj.zzb(zza3);
            }
            zzafl zzafl = new zzafl();
            zzafl.zza(new String(zza(zza2.getInputStream(), 128)));
            if (TextUtils.isEmpty(this.zzf)) {
                for (String next : zzafl.zza()) {
                    if (zza(next)) {
                        return zzacj.zza(next);
                    }
                }
                return null;
            } else if (!zzafl.zza().contains(this.zzf)) {
                return zzacj.zzb("UNAUTHORIZED_DOMAIN");
            } else {
                return zzacj.zza(this.zzf);
            }
        } catch (IOException e3) {
            zza.e(c.a("IOException occurred: ", e3.getMessage()), new Object[0]);
            return null;
        } catch (NullPointerException e4) {
            zza.e(c.a("Null pointer encountered: ", e4.getMessage()), new Object[0]);
            return null;
        } catch (zzaah e5) {
            zza.e(c.a("ConversionException encountered: ", e5.getMessage()), new Object[0]);
            return null;
        }
    }

    public final /* synthetic */ void onCancelled(Object obj) {
        zzacj zzacj = (zzacj) obj;
        onPostExecute((zzacj) null);
    }

    @Nullable
    private static String zza(HttpURLConnection httpURLConnection) throws zzaah {
        try {
            if (httpURLConnection.getResponseCode() < 400) {
                return null;
            }
            InputStream errorStream = httpURLConnection.getErrorStream();
            if (errorStream == null) {
                return "WEB_INTERNAL_ERROR:Could not retrieve the authDomain for this project but did not receive an error response from the network request. Please try again.";
            }
            return (String) zzacs.zza(new String(zza(errorStream, 128)), String.class);
        } catch (IOException e3) {
            zza.w("Error parsing error message from response body in getErrorMessageFromBody. ".concat(String.valueOf(e3)), new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final void onPostExecute(zzacj zzacj) {
        String str;
        String str2;
        Uri.Builder builder;
        zzacm zzacm = this.zzd.get();
        if (zzacj != null) {
            str2 = zzacj.zza();
            str = zzacj.zzb();
        } else {
            str2 = null;
            str = null;
        }
        if (zzacm == null) {
            zza.e("An error has occurred: the handler reference has returned null.", new Object[0]);
        } else if (TextUtils.isEmpty(str2) || (builder = this.zze) == null) {
            zzacm.zza(this.zzb, zzal.zza(str));
        } else {
            builder.authority(str2);
            zzacm.zza(this.zze.build(), this.zzb, FirebaseAuth.getInstance(this.zzg).zzc());
        }
    }

    @VisibleForTesting
    private static boolean zza(String str) {
        try {
            String host = new URI("https://" + str).getHost();
            if (host == null || (!host.endsWith("firebaseapp.com") && !host.endsWith("web.app"))) {
                return false;
            }
            return true;
        } catch (URISyntaxException e3) {
            zza.e(C0118y.f("Error parsing URL for auth domain check: ", str, ". ", e3.getMessage()), new Object[0]);
        }
    }

    private static byte[] zza(InputStream inputStream, int i3) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return byteArray;
                }
            }
        } catch (Throwable th) {
            byteArrayOutputStream.close();
            throw th;
        }
    }
}
