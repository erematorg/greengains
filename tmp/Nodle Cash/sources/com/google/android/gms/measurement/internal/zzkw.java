package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import com.adjust.sdk.Constants;

@VisibleForTesting
@MainThread
final class zzkw implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzjk zza;

    public zzkw(zzjk zzjk) {
        this.zza = zzjk;
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a9 A[SYNTHETIC, Splitter:B:40:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x010a A[Catch:{ RuntimeException -> 0x002b }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x010b A[Catch:{ RuntimeException -> 0x002b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void zza(com.google.android.gms.measurement.internal.zzkw r16, boolean r17, android.net.Uri r18, java.lang.String r19, java.lang.String r20) {
        /*
            r1 = r16
            r0 = r19
            r2 = r20
            java.lang.String r3 = "gclid="
            java.lang.String r4 = "https://google.com/search?"
            com.google.android.gms.measurement.internal.zzjk r5 = r1.zza
            r5.zzt()
            com.google.android.gms.measurement.internal.zzjk r5 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzop r5 = r5.zzq()     // Catch:{ RuntimeException -> 0x002b }
            boolean r6 = com.google.android.gms.internal.measurement.zzqn.zza()     // Catch:{ RuntimeException -> 0x002b }
            if (r6 == 0) goto L_0x002e
            com.google.android.gms.measurement.internal.zzjk r6 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzah r6 = r6.zze()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbj.zzcs     // Catch:{ RuntimeException -> 0x002b }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r6 == 0) goto L_0x002e
            r6 = 1
            goto L_0x002f
        L_0x002b:
            r0 = move-exception
            goto L_0x0194
        L_0x002e:
            r6 = 0
        L_0x002f:
            boolean r9 = android.text.TextUtils.isEmpty(r20)     // Catch:{ RuntimeException -> 0x002b }
            java.lang.String r10 = "_cis"
            java.lang.String r11 = "Activity created with data 'referrer' without required params"
            java.lang.String r12 = "utm_medium"
            java.lang.String r13 = "utm_source"
            java.lang.String r14 = "utm_campaign"
            r15 = 0
            java.lang.String r7 = "gclid"
            if (r9 == 0) goto L_0x0044
        L_0x0042:
            r4 = r15
            goto L_0x00a5
        L_0x0044:
            boolean r9 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            if (r6 == 0) goto L_0x0054
            java.lang.String r9 = "gbraid"
            boolean r9 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
        L_0x0054:
            boolean r9 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            boolean r9 = r2.contains(r13)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            boolean r9 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            java.lang.String r9 = "utm_id"
            boolean r9 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            java.lang.String r9 = "dclid"
            boolean r9 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            java.lang.String r9 = "srsltid"
            boolean r9 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            java.lang.String r9 = "sfmc_id"
            boolean r9 = r2.contains(r9)     // Catch:{ RuntimeException -> 0x002b }
            if (r9 != 0) goto L_0x0092
            com.google.android.gms.measurement.internal.zzgi r4 = r5.zzj()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzc()     // Catch:{ RuntimeException -> 0x002b }
            r4.zza(r11)     // Catch:{ RuntimeException -> 0x002b }
            goto L_0x0042
        L_0x0092:
            java.lang.String r4 = r4.concat(r2)     // Catch:{ RuntimeException -> 0x002b }
            android.net.Uri r4 = android.net.Uri.parse(r4)     // Catch:{ RuntimeException -> 0x002b }
            android.os.Bundle r4 = r5.zza((android.net.Uri) r4, (boolean) r6)     // Catch:{ RuntimeException -> 0x002b }
            if (r4 == 0) goto L_0x00a5
            java.lang.String r5 = "referrer"
            r4.putString(r10, r5)     // Catch:{ RuntimeException -> 0x002b }
        L_0x00a5:
            java.lang.String r5 = "_cmp"
            if (r17 == 0) goto L_0x0104
            com.google.android.gms.measurement.internal.zzjk r6 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzop r6 = r6.zzq()     // Catch:{ RuntimeException -> 0x002b }
            boolean r9 = com.google.android.gms.internal.measurement.zzqn.zza()     // Catch:{ RuntimeException -> 0x002b }
            if (r9 == 0) goto L_0x00c7
            com.google.android.gms.measurement.internal.zzjk r9 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzah r9 = r9.zze()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzcs     // Catch:{ RuntimeException -> 0x002b }
            boolean r8 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ RuntimeException -> 0x002b }
            if (r8 == 0) goto L_0x00c7
            r9 = r18
            r8 = 1
            goto L_0x00ca
        L_0x00c7:
            r9 = r18
            r8 = 0
        L_0x00ca:
            android.os.Bundle r6 = r6.zza((android.net.Uri) r9, (boolean) r8)     // Catch:{ RuntimeException -> 0x002b }
            if (r6 == 0) goto L_0x0104
            java.lang.String r8 = "intent"
            r6.putString(r10, r8)     // Catch:{ RuntimeException -> 0x002b }
            boolean r8 = r6.containsKey(r7)     // Catch:{ RuntimeException -> 0x002b }
            if (r8 != 0) goto L_0x00f8
            if (r4 == 0) goto L_0x00f8
            boolean r8 = r4.containsKey(r7)     // Catch:{ RuntimeException -> 0x002b }
            if (r8 == 0) goto L_0x00f8
            java.lang.String r8 = "_cer"
            java.lang.String r9 = r4.getString(r7)     // Catch:{ RuntimeException -> 0x002b }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x002b }
            r10.<init>(r3)     // Catch:{ RuntimeException -> 0x002b }
            r10.append(r9)     // Catch:{ RuntimeException -> 0x002b }
            java.lang.String r3 = r10.toString()     // Catch:{ RuntimeException -> 0x002b }
            r6.putString(r8, r3)     // Catch:{ RuntimeException -> 0x002b }
        L_0x00f8:
            com.google.android.gms.measurement.internal.zzjk r3 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            r3.zzc(r0, r5, r6)     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzjk r3 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzs r3 = r3.zza     // Catch:{ RuntimeException -> 0x002b }
            r3.zza(r0, r6)     // Catch:{ RuntimeException -> 0x002b }
        L_0x0104:
            boolean r3 = android.text.TextUtils.isEmpty(r20)     // Catch:{ RuntimeException -> 0x002b }
            if (r3 == 0) goto L_0x010b
            return
        L_0x010b:
            com.google.android.gms.measurement.internal.zzjk r3 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzc()     // Catch:{ RuntimeException -> 0x002b }
            java.lang.String r6 = "Activity created with referrer"
            r3.zza(r6, r2)     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzjk r3 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzah r3 = r3.zze()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzbp     // Catch:{ RuntimeException -> 0x002b }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ RuntimeException -> 0x002b }
            java.lang.String r6 = "_ldl"
            java.lang.String r8 = "auto"
            if (r3 == 0) goto L_0x0151
            if (r4 == 0) goto L_0x013b
            com.google.android.gms.measurement.internal.zzjk r2 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            r2.zzc(r0, r5, r4)     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzjk r2 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzs r2 = r2.zza     // Catch:{ RuntimeException -> 0x002b }
            r2.zza(r0, r4)     // Catch:{ RuntimeException -> 0x002b }
            goto L_0x014a
        L_0x013b:
            com.google.android.gms.measurement.internal.zzjk r0 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x002b }
            java.lang.String r3 = "Referrer does not contain valid parameters"
            r0.zza(r3, r2)     // Catch:{ RuntimeException -> 0x002b }
        L_0x014a:
            com.google.android.gms.measurement.internal.zzjk r0 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            r2 = 1
            r0.zza((java.lang.String) r8, (java.lang.String) r6, (java.lang.Object) r15, (boolean) r2)     // Catch:{ RuntimeException -> 0x002b }
            return
        L_0x0151:
            boolean r0 = r2.contains(r7)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 == 0) goto L_0x0186
            boolean r0 = r2.contains(r14)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 != 0) goto L_0x0179
            boolean r0 = r2.contains(r13)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 != 0) goto L_0x0179
            boolean r0 = r2.contains(r12)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 != 0) goto L_0x0179
            java.lang.String r0 = "utm_term"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 != 0) goto L_0x0179
            java.lang.String r0 = "utm_content"
            boolean r0 = r2.contains(r0)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 == 0) goto L_0x0186
        L_0x0179:
            boolean r0 = android.text.TextUtils.isEmpty(r20)     // Catch:{ RuntimeException -> 0x002b }
            if (r0 != 0) goto L_0x0185
            com.google.android.gms.measurement.internal.zzjk r0 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            r3 = 1
            r0.zza((java.lang.String) r8, (java.lang.String) r6, (java.lang.Object) r2, (boolean) r3)     // Catch:{ RuntimeException -> 0x002b }
        L_0x0185:
            return
        L_0x0186:
            com.google.android.gms.measurement.internal.zzjk r0 = r1.zza     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ RuntimeException -> 0x002b }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzc()     // Catch:{ RuntimeException -> 0x002b }
            r0.zza(r11)     // Catch:{ RuntimeException -> 0x002b }
            return
        L_0x0194:
            com.google.android.gms.measurement.internal.zzjk r1 = r1.zza
            com.google.android.gms.measurement.internal.zzgi r1 = r1.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()
            java.lang.String r2 = "Throwable caught in handleReferrerForOnActivityCreated"
            r1.zza(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkw.zza(com.google.android.gms.measurement.internal.zzkw, boolean, android.net.Uri, java.lang.String, java.lang.String):void");
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zza.zzj().zzp().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent == null) {
                this.zza.zzn().zza(activity, bundle);
                return;
            }
            Uri data = intent.getData();
            if (data == null || !data.isHierarchical()) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String string = extras.getString("com.android.vending.referral_url");
                    if (!TextUtils.isEmpty(string)) {
                        data = Uri.parse(string);
                    }
                }
                data = null;
            }
            Uri uri = data;
            if (uri != null) {
                if (uri.isHierarchical()) {
                    this.zza.zzq();
                    this.zza.zzl().zzb((Runnable) new zzkz(this, bundle == null, uri, zzop.zza(intent) ? "gs" : "auto", uri.getQueryParameter(Constants.REFERRER)));
                    this.zza.zzn().zza(activity, bundle);
                    return;
                }
            }
            this.zza.zzn().zza(activity, bundle);
        } catch (RuntimeException e3) {
            this.zza.zzj().zzg().zza("Throwable caught in onActivityCreated", e3);
            this.zza.zzn().zza(activity, bundle);
        } catch (Throwable th) {
            this.zza.zzn().zza(activity, bundle);
            throw th;
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzn().zza(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.zza.zzn().zzb(activity);
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zznd(zzp, zzp.zzb().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zznb zzp = this.zza.zzp();
        zzp.zzl().zzb((Runnable) new zzna(zzp, zzp.zzb().elapsedRealtime()));
        this.zza.zzn().zzc(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzn().zzb(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
