package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.media.session.a;
import android.util.Base64;
import androidx.annotation.VisibleForTesting;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadx  reason: invalid package */
final class zzadx {
    /* access modifiers changed from: private */
    public static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    /* access modifiers changed from: private */
    public final HashMap<String, zzaea> zzd = new HashMap<>();

    public zzadx(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzb = context;
        this.zzc = scheduledExecutorService;
    }

    /* access modifiers changed from: private */
    public final void zze(String str) {
        zzaea zzaea = this.zzd.get(str);
        if (zzaea != null && !zzaea.zzh && !zzah.zzc(zzaea.zzd)) {
            zza.w("Timed out waiting for SMS.", new Object[0]);
            for (zzacf zza2 : zzaea.zzb) {
                zza2.zza(zzaea.zzd);
            }
            zzaea.zzi = true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final void zzb(String str) {
        zzaea zzaea = this.zzd.get(str);
        if (zzaea != null) {
            if (!zzaea.zzi) {
                zze(str);
            }
            zzc(str);
        }
    }

    public final boolean zzd(String str) {
        return this.zzd.get(str) != null;
    }

    public final String zzb() {
        try {
            String packageName = this.zzb.getPackageName();
            String zza2 = zza(packageName, Wrappers.packageManager(this.zzb).getPackageInfo(packageName, C.BUFFER_FLAG_FIRST_SAMPLE).signingInfo.getApkContentsSigners()[0].toCharsString());
            if (zza2 != null) {
                return zza2;
            }
            zza.e("Hash generation failed.", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            zza.e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    public final void zzc(String str) {
        zzaea zzaea = this.zzd.get(str);
        if (zzaea != null) {
            ScheduledFuture<?> scheduledFuture = zzaea.zzf;
            if (scheduledFuture != null && !scheduledFuture.isDone()) {
                zzaea.zzf.cancel(false);
            }
            zzaea.zzb.clear();
            this.zzd.remove(str);
        }
    }

    public final zzacf zza(zzacf zzacf, String str) {
        return new zzady(this, zzacf, str);
    }

    @VisibleForTesting
    public static String zza(String str) {
        Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private static String zza(String str, String str2) {
        String n2 = a.n(str, StringUtils.SPACE, str2);
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(n2.getBytes(zzq.zza));
            String substring = Base64.encodeToString(Arrays.copyOf(instance.digest(), 9), 3).substring(0, 11);
            Logger logger = zza;
            logger.d("Package: " + str + " -- Hash: " + substring, new Object[0]);
            return substring;
        } catch (NoSuchAlgorithmException e3) {
            zza.e(c.a("NoSuchAlgorithm: ", e3.getMessage()), new Object[0]);
            return null;
        }
    }

    public final void zzb(zzacf zzacf, String str) {
        zzaea zzaea = this.zzd.get(str);
        if (zzaea != null) {
            zzaea.zzb.add(zzacf);
            if (zzaea.zzg) {
                zzacf.zzb(zzaea.zzd);
            }
            if (zzaea.zzh) {
                zzacf.zza(PhoneAuthCredential.zza(zzaea.zzd, zzaea.zze));
            }
            if (zzaea.zzi) {
                zzacf.zza(zzaea.zzd);
            }
        }
    }

    public static /* synthetic */ void zza(zzadx zzadx, String str) {
        zzaea zzaea = zzadx.zzd.get(str);
        if (zzaea != null && !zzah.zzc(zzaea.zzd) && !zzah.zzc(zzaea.zze) && !zzaea.zzb.isEmpty()) {
            for (zzacf zza2 : zzaea.zzb) {
                zza2.zza(PhoneAuthCredential.zza(zzaea.zzd, zzaea.zze));
            }
            zzaea.zzh = true;
        }
    }

    public final void zza(String str, zzacf zzacf, long j2, boolean z2) {
        this.zzd.put(str, new zzaea(j2, z2));
        zzb(zzacf, str);
        zzaea zzaea = this.zzd.get(str);
        if (zzaea.zza <= 0) {
            zza.w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzaea.zzf = this.zzc.schedule(new zzadw(this, str), zzaea.zza, TimeUnit.SECONDS);
        if (!zzaea.zzc) {
            zza.w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzaeb zzaeb = new zzaeb(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        zzc.zza(this.zzb.getApplicationContext(), zzaeb, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzadz(this));
    }
}
