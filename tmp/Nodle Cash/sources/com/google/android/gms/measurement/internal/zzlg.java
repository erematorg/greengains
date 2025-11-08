package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.dataflow.qual.Pure;

public final class zzlg extends zzf {
    @VisibleForTesting
    protected zzlh zza;
    private volatile zzlh zzb;
    private volatile zzlh zzc;
    private final Map<Activity, zzlh> zzd = new ConcurrentHashMap();
    @GuardedBy("activityLock")
    private Activity zze;
    @GuardedBy("activityLock")
    private volatile boolean zzf;
    private volatile zzlh zzg;
    /* access modifiers changed from: private */
    public zzlh zzh;
    @GuardedBy("activityLock")
    private boolean zzi;
    private final Object zzj = new Object();

    public zzlg(zzhw zzhw) {
        super(zzhw);
    }

    public final zzlh zzaa() {
        return this.zzb;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzbb zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgc zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgi zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgu zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhp zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjk zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlg zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzlp zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzop zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final boolean zzz() {
        return false;
    }

    @MainThread
    private final zzlh zzd(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzlh zzlh = this.zzd.get(activity);
        if (zzlh == null) {
            zzlh zzlh2 = new zzlh((String) null, zza(activity.getClass(), "Activity"), zzq().zzm());
            this.zzd.put(activity, zzlh2);
            zzlh = zzlh2;
        }
        return this.zzg != null ? this.zzg : zzlh;
    }

    @MainThread
    public final void zzb(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long elapsedRealtime = zzb().elapsedRealtime();
        if (!zze().zzy()) {
            this.zzb = null;
            zzl().zzb((Runnable) new zzlk(this, elapsedRealtime));
            return;
        }
        zzlh zzd2 = zzd(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        zzl().zzb((Runnable) new zzln(this, zzd2, elapsedRealtime));
    }

    @MainThread
    public final void zzc(Activity activity) {
        synchronized (this.zzj) {
            this.zzi = true;
            if (activity != this.zze) {
                synchronized (this.zzj) {
                    this.zze = activity;
                    this.zzf = false;
                }
                if (zze().zzy()) {
                    this.zzg = null;
                    zzl().zzb((Runnable) new zzlm(this));
                }
            }
        }
        if (!zze().zzy()) {
            this.zzb = this.zzg;
            zzl().zzb((Runnable) new zzll(this));
            return;
        }
        zza(activity, zzd(activity), false);
        zza zzc2 = zzc();
        zzc2.zzl().zzb((Runnable) new zze(zzc2, zzc2.zzb().elapsedRealtime()));
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @WorkerThread
    public final zzlh zza(boolean z2) {
        zzu();
        zzt();
        if (!z2) {
            return this.zza;
        }
        zzlh zzlh = this.zza;
        return zzlh != null ? zzlh : this.zzh;
    }

    @VisibleForTesting
    private final String zza(Class<?> cls, String str) {
        String str2;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return str;
        }
        String[] split = canonicalName.split("\\.");
        if (split.length > 0) {
            str2 = split[split.length - 1];
        } else {
            str2 = "";
        }
        return str2.length() > zze().zza((String) null, false) ? str2.substring(0, zze().zza((String) null, false)) : str2;
    }

    public static /* synthetic */ void zza(zzlg zzlg, Bundle bundle, zzlh zzlh, zzlh zzlh2, long j2) {
        if (bundle != null) {
            bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
            bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        }
        zzlg.zza(zzlh, zzlh2, j2, true, zzlg.zzq().zza((String) null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, (List<String>) null, false));
    }

    @MainThread
    public final void zzb(Activity activity, Bundle bundle) {
        zzlh zzlh;
        if (zze().zzy() && bundle != null && (zzlh = this.zzd.get(activity)) != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putLong(TtmlNode.ATTR_ID, zzlh.zzc);
            bundle2.putString("name", zzlh.zza);
            bundle2.putString("referrer_name", zzlh.zzb);
            bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
        }
    }

    @MainThread
    private final void zza(Activity activity, zzlh zzlh, boolean z2) {
        zzlh zzlh2;
        zzlh zzlh3 = this.zzb == null ? this.zzc : this.zzb;
        if (zzlh.zzb == null) {
            zzlh2 = new zzlh(zzlh.zza, activity != null ? zza(activity.getClass(), "Activity") : null, zzlh.zzc, zzlh.zze, zzlh.zzf);
        } else {
            zzlh2 = zzlh;
        }
        this.zzc = this.zzb;
        this.zzb = zzlh2;
        zzl().zzb((Runnable) new zzli(this, zzlh2, zzlh3, zzb().elapsedRealtime(), z2));
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzlh zzlh, zzlh zzlh2, long j2, boolean z2, Bundle bundle) {
        Bundle bundle2;
        String str;
        long j3;
        zzlh zzlh3 = zzlh;
        zzlh zzlh4 = zzlh2;
        long j4 = j2;
        Bundle bundle3 = bundle;
        zzt();
        boolean z3 = false;
        boolean z4 = zzlh4 == null || zzlh4.zzc != zzlh3.zzc || !Objects.equals(zzlh4.zzb, zzlh3.zzb) || !Objects.equals(zzlh4.zza, zzlh3.zza);
        if (z2 && this.zza != null) {
            z3 = true;
        }
        if (z4) {
            if (bundle3 == null) {
                bundle2 = new Bundle();
            }
            Bundle bundle4 = bundle2;
            zzop.zza(zzlh3, bundle4, true);
            if (zzlh4 != null) {
                String str2 = zzlh4.zza;
                if (str2 != null) {
                    bundle4.putString("_pn", str2);
                }
                String str3 = zzlh4.zzb;
                if (str3 != null) {
                    bundle4.putString("_pc", str3);
                }
                bundle4.putLong("_pi", zzlh4.zzc);
            }
            if (z3) {
                long zza2 = zzp().zzb.zza(j4);
                if (zza2 > 0) {
                    zzq().zza(bundle4, zza2);
                }
            }
            if (!zze().zzy()) {
                bundle4.putLong("_mst", 1);
            }
            if (zzlh3.zze) {
                str = "app";
            } else {
                str = "auto";
            }
            String str4 = str;
            long currentTimeMillis = zzb().currentTimeMillis();
            if (zzlh3.zze) {
                long j5 = zzlh3.zzf;
                if (j5 != 0) {
                    j3 = j5;
                    zzm().zza(str4, "_vs", j3, bundle4);
                }
            }
            j3 = currentTimeMillis;
            zzm().zza(str4, "_vs", j3, bundle4);
        }
        if (z3) {
            zza(this.zza, true, j4);
        }
        this.zza = zzlh3;
        if (zzlh3.zze) {
            this.zzh = zzlh3;
        }
        zzo().zza(zzlh3);
    }

    @MainThread
    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (zze().zzy() && bundle != null && (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) != null) {
            this.zzd.put(activity, new zzlh(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong(TtmlNode.ATTR_ID)));
        }
    }

    @MainThread
    public final void zza(Activity activity) {
        synchronized (this.zzj) {
            try {
                if (activity == this.zze) {
                    this.zze = null;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (zze().zzy()) {
            this.zzd.remove(activity);
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(zzlh zzlh, boolean z2, long j2) {
        zzc().zza(zzb().elapsedRealtime());
        if (zzp().zza(zzlh != null && zzlh.zzd, z2, j2) && zzlh != null) {
            zzlh.zzd = false;
        }
    }

    @Deprecated
    public final void zza(@NonNull Activity activity, @Size(max = 36, min = 1) String str, @Size(max = 36, min = 1) String str2) {
        if (!zze().zzy()) {
            zzj().zzv().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zzlh zzlh = this.zzb;
        if (zzlh == null) {
            zzj().zzv().zza("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzd.get(activity) == null) {
            zzj().zzv().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zza(activity.getClass(), "Activity");
            }
            boolean equals = Objects.equals(zzlh.zzb, str2);
            boolean equals2 = Objects.equals(zzlh.zza, str);
            if (equals && equals2) {
                zzj().zzv().zza("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > zze().zza((String) null, false))) {
                zzj().zzv().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= zze().zza((String) null, false))) {
                zzj().zzp().zza("Setting current screen to name, class", str == null ? AbstractJsonLexerKt.NULL : str, str2);
                zzlh zzlh2 = new zzlh(str, str2, zzq().zzm());
                this.zzd.put(activity, zzlh2);
                zza(activity, zzlh2, true);
            } else {
                zzj().zzv().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c7, code lost:
        r1 = zzj().zzp();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        if (r6 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00d3, code lost:
        r4 = kotlinx.serialization.json.internal.AbstractJsonLexerKt.NULL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d6, code lost:
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d7, code lost:
        if (r7 != null) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d9, code lost:
        r5 = kotlinx.serialization.json.internal.AbstractJsonLexerKt.NULL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00dc, code lost:
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00dd, code lost:
        r1.zza("Logging screen view with name, class", r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e2, code lost:
        if (r0.zzb != null) goto L_0x00e8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e4, code lost:
        r1 = r0.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e6, code lost:
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e8, code lost:
        r1 = r0.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00eb, code lost:
        r5 = new com.google.android.gms.measurement.internal.zzlh(r6, r7, zzq().zzm(), true, r16);
        r0.zzb = r5;
        r0.zzc = r13;
        r0.zzg = r5;
        zzl().zzb((java.lang.Runnable) new com.google.android.gms.measurement.internal.zzlj(r14, r15, r5, r13, zzb().elapsedRealtime()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x011a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(android.os.Bundle r15, long r16) {
        /*
            r14 = this;
            r0 = r14
            r3 = r15
            java.lang.Object r1 = r0.zzj
            monitor-enter(r1)
            boolean r2 = r0.zzi     // Catch:{ all -> 0x0018 }
            if (r2 != 0) goto L_0x001b
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzv()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Cannot log screen view event when the app is in the background."
            r0.zza(r2)     // Catch:{ all -> 0x0018 }
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            return
        L_0x0018:
            r0 = move-exception
            goto L_0x011b
        L_0x001b:
            r2 = 0
            r4 = 0
            if (r3 == 0) goto L_0x0088
            java.lang.String r5 = "screen_name"
            java.lang.String r5 = r15.getString(r5)     // Catch:{ all -> 0x0018 }
            if (r5 == 0) goto L_0x0052
            int r6 = r5.length()     // Catch:{ all -> 0x0018 }
            if (r6 <= 0) goto L_0x003b
            int r6 = r5.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzah r7 = r14.zze()     // Catch:{ all -> 0x0018 }
            int r7 = r7.zza((java.lang.String) r4, (boolean) r2)     // Catch:{ all -> 0x0018 }
            if (r6 <= r7) goto L_0x0052
        L_0x003b:
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzv()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Invalid screen name length for screen view. Length"
            int r3 = r5.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0018 }
            r0.zza(r2, r3)     // Catch:{ all -> 0x0018 }
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            return
        L_0x0052:
            java.lang.String r6 = "screen_class"
            java.lang.String r6 = r15.getString(r6)     // Catch:{ all -> 0x0018 }
            if (r6 == 0) goto L_0x0085
            int r7 = r6.length()     // Catch:{ all -> 0x0018 }
            if (r7 <= 0) goto L_0x006e
            int r7 = r6.length()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzah r8 = r14.zze()     // Catch:{ all -> 0x0018 }
            int r4 = r8.zza((java.lang.String) r4, (boolean) r2)     // Catch:{ all -> 0x0018 }
            if (r7 <= r4) goto L_0x0085
        L_0x006e:
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzv()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Invalid screen class length for screen view. Length"
            int r3 = r6.length()     // Catch:{ all -> 0x0018 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0018 }
            r0.zza(r2, r3)     // Catch:{ all -> 0x0018 }
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            return
        L_0x0085:
            r4 = r6
            r6 = r5
            goto L_0x0089
        L_0x0088:
            r6 = r4
        L_0x0089:
            if (r4 != 0) goto L_0x009c
            android.app.Activity r4 = r0.zze     // Catch:{ all -> 0x0018 }
            if (r4 == 0) goto L_0x009a
            java.lang.Class r4 = r4.getClass()     // Catch:{ all -> 0x0018 }
            java.lang.String r5 = "Activity"
            java.lang.String r4 = r14.zza((java.lang.Class<?>) r4, (java.lang.String) r5)     // Catch:{ all -> 0x0018 }
            goto L_0x009c
        L_0x009a:
            java.lang.String r4 = "Activity"
        L_0x009c:
            r7 = r4
            com.google.android.gms.measurement.internal.zzlh r4 = r0.zzb     // Catch:{ all -> 0x0018 }
            boolean r5 = r0.zzf     // Catch:{ all -> 0x0018 }
            if (r5 == 0) goto L_0x00c6
            if (r4 == 0) goto L_0x00c6
            r0.zzf = r2     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = r4.zzb     // Catch:{ all -> 0x0018 }
            boolean r2 = java.util.Objects.equals(r2, r7)     // Catch:{ all -> 0x0018 }
            java.lang.String r4 = r4.zza     // Catch:{ all -> 0x0018 }
            boolean r4 = java.util.Objects.equals(r4, r6)     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x00c6
            if (r4 == 0) goto L_0x00c6
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzv()     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "Ignoring call to log screen view event with duplicate parameters."
            r0.zza(r2)     // Catch:{ all -> 0x0018 }
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            return
        L_0x00c6:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            com.google.android.gms.measurement.internal.zzgi r1 = r14.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.String r2 = "Logging screen view with name, class"
            if (r6 != 0) goto L_0x00d6
            java.lang.String r4 = "null"
            goto L_0x00d7
        L_0x00d6:
            r4 = r6
        L_0x00d7:
            if (r7 != 0) goto L_0x00dc
            java.lang.String r5 = "null"
            goto L_0x00dd
        L_0x00dc:
            r5 = r7
        L_0x00dd:
            r1.zza(r2, r4, r5)
            com.google.android.gms.measurement.internal.zzlh r1 = r0.zzb
            if (r1 != 0) goto L_0x00e8
            com.google.android.gms.measurement.internal.zzlh r1 = r0.zzc
        L_0x00e6:
            r13 = r1
            goto L_0x00eb
        L_0x00e8:
            com.google.android.gms.measurement.internal.zzlh r1 = r0.zzb
            goto L_0x00e6
        L_0x00eb:
            com.google.android.gms.measurement.internal.zzlh r4 = new com.google.android.gms.measurement.internal.zzlh
            com.google.android.gms.measurement.internal.zzop r1 = r14.zzq()
            long r8 = r1.zzm()
            r10 = 1
            r5 = r4
            r11 = r16
            r5.<init>(r6, r7, r8, r10, r11)
            r0.zzb = r4
            r0.zzc = r13
            r0.zzg = r4
            com.google.android.gms.common.util.Clock r1 = r14.zzb()
            long r6 = r1.elapsedRealtime()
            com.google.android.gms.measurement.internal.zzhp r8 = r14.zzl()
            com.google.android.gms.measurement.internal.zzlj r9 = new com.google.android.gms.measurement.internal.zzlj
            r1 = r9
            r2 = r14
            r3 = r15
            r5 = r13
            r1.<init>(r2, r3, r4, r5, r6)
            r8.zzb((java.lang.Runnable) r9)
            return
        L_0x011b:
            monitor-exit(r1)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlg.zza(android.os.Bundle, long):void");
    }
}
