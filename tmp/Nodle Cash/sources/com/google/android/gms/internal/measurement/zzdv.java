package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzhq;
import com.google.android.gms.measurement.internal.zzjg;
import com.google.android.gms.measurement.internal.zzjj;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;

public class zzdv {
    private static volatile zzdv zzb;
    protected final Clock zza;
    /* access modifiers changed from: private */
    public final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    @GuardedBy("listenerList")
    private final List<Pair<zzjj, zzd>> zzf;
    private int zzg;
    /* access modifiers changed from: private */
    public boolean zzh;
    private String zzi;
    /* access modifiers changed from: private */
    public volatile zzdk zzj;

    public static class zza extends zzdp {
        private final zzjg zza;

        public zza(zzjg zzjg) {
            this.zza = zzjg;
        }

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        public final void zza(String str, String str2, Bundle bundle, long j2) {
            this.zza.interceptEvent(str, str2, bundle, j2);
        }
    }

    public abstract class zzb implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        public zzb(zzdv zzdv) {
            this(true);
        }

        public void run() {
            if (zzdv.this.zzh) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e3) {
                zzdv.this.zza(e3, false, this.zzc);
                zzb();
            }
        }

        public abstract void zza() throws RemoteException;

        public void zzb() {
        }

        public zzb(boolean z2) {
            this.zza = zzdv.this.zza.currentTimeMillis();
            this.zzb = zzdv.this.zza.elapsedRealtime();
            this.zzc = z2;
        }
    }

    public class zzc implements Application.ActivityLifecycleCallbacks {
        public zzc() {
        }

        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzdv.this.zza((zzb) new zzfg(this, bundle, activity));
        }

        public final void onActivityDestroyed(Activity activity) {
            zzdv.this.zza((zzb) new zzfl(this, activity));
        }

        public final void onActivityPaused(Activity activity) {
            zzdv.this.zza((zzb) new zzfh(this, activity));
        }

        public final void onActivityResumed(Activity activity) {
            zzdv.this.zza((zzb) new zzfi(this, activity));
        }

        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzdi zzdi = new zzdi();
            zzdv.this.zza((zzb) new zzfj(this, activity, zzdi));
            Bundle zza2 = zzdi.zza(50);
            if (zza2 != null) {
                bundle.putAll(zza2);
            }
        }

        public final void onActivityStarted(Activity activity) {
            zzdv.this.zza((zzb) new zzff(this, activity));
        }

        public final void onActivityStopped(Activity activity) {
            zzdv.this.zza((zzb) new zzfk(this, activity));
        }
    }

    public static class zzd extends zzdp {
        private final zzjj zza;

        public zzd(zzjj zzjj) {
            this.zza = zzjj;
        }

        public final int zza() {
            return System.identityHashCode(this.zza);
        }

        public final void zza(String str, String str2, Bundle bundle, long j2) {
            this.zza.onEvent(str, str2, bundle, j2);
        }
    }

    private zzdv(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        boolean z2 = true;
        this.zzd = zzcz.zza().zza(new zzeh(this), 1);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        if (!zzb(context) || zzk()) {
            if (!zzc(str2, str3)) {
                this.zzi = "fa";
                if (str2 == null || str3 == null) {
                    if ((str2 == null) ^ (str3 != null ? false : z2)) {
                        Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                    }
                } else {
                    Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
                }
            } else {
                this.zzi = str2;
            }
            zza((zzb) new zzdy(this, str2, str3, context, bundle));
            Application application = (Application) context.getApplicationContext();
            if (application == null) {
                Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
            } else {
                application.registerActivityLifecycleCallbacks(new zzc());
            }
        } else {
            this.zzi = null;
            this.zzh = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
        }
    }

    private final boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, getClass().getClassLoader());
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final String zzd() {
        return this.zzi;
    }

    @WorkerThread
    public final String zze() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzev(this, zzdi));
        return zzdi.zzc(120000);
    }

    public final String zzf() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzeo(this, zzdi));
        return zzdi.zzc(50);
    }

    public final String zzg() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzep(this, zzdi));
        return zzdi.zzc(500);
    }

    public final String zzh() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzeq(this, zzdi));
        return zzdi.zzc(500);
    }

    public final String zzi() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzel(this, zzdi));
        return zzdi.zzc(500);
    }

    public final void zzj() {
        zza((zzb) new zzei(this));
    }

    public final AppMeasurementSdk zzb() {
        return this.zze;
    }

    @WorkerThread
    public final Long zzc() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzey(this, zzdi));
        return zzdi.zzb(120000);
    }

    public final void zzd(Bundle bundle) {
        zza((zzb) new zzez(this, bundle));
    }

    public final void zzb(String str) {
        zza((zzb) new zzej(this, str));
    }

    public final void zzd(String str) {
        zza((zzb) new zzeb(this, str));
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, (Long) null);
    }

    public final void zzb(Bundle bundle) {
        zza((zzb) new zzeg(this, bundle));
    }

    public final void zzc(String str) {
        zza((zzb) new zzem(this, str));
    }

    public final int zza(String str) {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzew(this, str, zzdi));
        Integer num = (Integer) zzdi.zza(zzdi.zza(10000), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final void zzb(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zzc(Bundle bundle) {
        zza((zzb) new zzef(this, bundle));
    }

    /* access modifiers changed from: private */
    public final boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r3.zzj == null) goto L_0x0054;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r3.zzj.unregisterOnMeasurementEventListener(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        android.util.Log.w(r3.zzc, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0054, code lost:
        zza((com.google.android.gms.internal.measurement.zzdv.zzb) new com.google.android.gms.internal.measurement.zzfe(r3, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.measurement.internal.zzjj r4) {
        /*
            r3 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r0 = r3.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r2 = r3.zzf     // Catch:{ all -> 0x0028 }
            int r2 = r2.size()     // Catch:{ all -> 0x0028 }
            if (r1 >= r2) goto L_0x002d
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r2 = r3.zzf     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0028 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0028 }
            boolean r2 = r4.equals(r2)     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x002a
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r4 = r3.zzf     // Catch:{ all -> 0x0028 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0028 }
            android.util.Pair r4 = (android.util.Pair) r4     // Catch:{ all -> 0x0028 }
            goto L_0x002e
        L_0x0028:
            r3 = move-exception
            goto L_0x005d
        L_0x002a:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002d:
            r4 = 0
        L_0x002e:
            if (r4 != 0) goto L_0x0039
            java.lang.String r3 = r3.zzc     // Catch:{ all -> 0x0028 }
            java.lang.String r4 = "OnEventListener had not been registered."
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return
        L_0x0039:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r1 = r3.zzf     // Catch:{ all -> 0x0028 }
            r1.remove(r4)     // Catch:{ all -> 0x0028 }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x0028 }
            com.google.android.gms.internal.measurement.zzdv$zzd r4 = (com.google.android.gms.internal.measurement.zzdv.zzd) r4     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.internal.measurement.zzdk r0 = r3.zzj
            if (r0 == 0) goto L_0x0054
            com.google.android.gms.internal.measurement.zzdk r0 = r3.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004d }
            r0.unregisterOnMeasurementEventListener(r4)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x004d }
            return
        L_0x004d:
            java.lang.String r0 = r3.zzc
            java.lang.String r1 = "Failed to unregister event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r0, r1)
        L_0x0054:
            com.google.android.gms.internal.measurement.zzfe r0 = new com.google.android.gms.internal.measurement.zzfe
            r0.<init>(r3, r4)
            r3.zza((com.google.android.gms.internal.measurement.zzdv.zzb) r0)
            return
        L_0x005d:
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdv.zzb(com.google.android.gms.measurement.internal.zzjj):void");
    }

    public final long zza() {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzen(this, zzdi));
        Long zzb2 = zzdi.zzb(500);
        if (zzb2 != null) {
            return zzb2.longValue();
        }
        long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
        int i3 = this.zzg + 1;
        this.zzg = i3;
        return nextLong + ((long) i3);
    }

    public final Bundle zza(Bundle bundle, boolean z2) {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzet(this, bundle, zzdi));
        if (z2) {
            return zzdi.zza(5000);
        }
        return null;
    }

    public final zzdk zza(Context context, boolean z2) {
        try {
            return zzdj.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e3) {
            zza((Exception) e3, true, false);
            return null;
        }
    }

    private static boolean zzb(Context context) {
        try {
            if (new zzhq(context, zzhq.zza(context)).zza("google_app_id") != null) {
                return true;
            }
            return false;
        } catch (IllegalStateException unused) {
        }
    }

    public static zzdv zza(@NonNull Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    public static zzdv zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzdv.class) {
                try {
                    if (zzb == null) {
                        zzb = new zzdv(context, str, str2, str3, bundle);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zzb;
    }

    public final Object zza(int i3) {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzex(this, zzdi, i3));
        return zzdi.zza(zzdi.zza(15000), Object.class);
    }

    public final List<Bundle> zza(String str, String str2) {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzec(this, str, str2, zzdi));
        List<Bundle> list = (List) zzdi.zza(zzdi.zza(5000), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zza(String str, String str2, boolean z2) {
        zzdi zzdi = new zzdi();
        zza((zzb) new zzes(this, str, str2, z2, zzdi));
        Bundle zza2 = zzdi.zza(5000);
        if (zza2 == null || zza2.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(zza2.size());
        for (String next : zza2.keySet()) {
            Object obj = zza2.get(next);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                hashMap.put(next, obj);
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public final void zza(Exception exc, boolean z2, boolean z3) {
        this.zzh |= z2;
        if (z2) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z3) {
            zza(5, "Error with data collection. Data lost.", (Object) exc, (Object) null, (Object) null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza((zzb) new zzdz(this, str, str2, bundle));
    }

    public final void zza(@NonNull String str, Bundle bundle) {
        zza((String) null, str, bundle, false, true, (Long) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j2) {
        zza(str, str2, bundle, true, false, Long.valueOf(j2));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z2, boolean z3, Long l2) {
        zza((zzb) new zzfd(this, l2, str, str2, bundle, z2, z3));
    }

    public final void zza(int i3, String str, Object obj, Object obj2, Object obj3) {
        zza((zzb) new zzer(this, false, 5, str, obj, (Object) null, (Object) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r4.zzj == null) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r4.zzj.registerOnMeasurementEventListener(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        android.util.Log.w(r4.zzc, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        zza((com.google.android.gms.internal.measurement.zzdv.zzb) new com.google.android.gms.internal.measurement.zzfb(r4, r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0056, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzjj r5) {
        /*
            r4 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r0 = r4.zzf
            monitor-enter(r0)
            r1 = 0
        L_0x0007:
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r2 = r4.zzf     // Catch:{ all -> 0x0028 }
            int r2 = r2.size()     // Catch:{ all -> 0x0028 }
            if (r1 >= r2) goto L_0x002d
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r2 = r4.zzf     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0028 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r2.first     // Catch:{ all -> 0x0028 }
            boolean r2 = r5.equals(r2)     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x002a
            java.lang.String r4 = r4.zzc     // Catch:{ all -> 0x0028 }
            java.lang.String r5 = "OnEventListener already registered."
            android.util.Log.w(r4, r5)     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            return
        L_0x0028:
            r4 = move-exception
            goto L_0x0057
        L_0x002a:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x002d:
            com.google.android.gms.internal.measurement.zzdv$zzd r1 = new com.google.android.gms.internal.measurement.zzdv$zzd     // Catch:{ all -> 0x0028 }
            r1.<init>(r5)     // Catch:{ all -> 0x0028 }
            java.util.List<android.util.Pair<com.google.android.gms.measurement.internal.zzjj, com.google.android.gms.internal.measurement.zzdv$zzd>> r2 = r4.zzf     // Catch:{ all -> 0x0028 }
            android.util.Pair r3 = new android.util.Pair     // Catch:{ all -> 0x0028 }
            r3.<init>(r5, r1)     // Catch:{ all -> 0x0028 }
            r2.add(r3)     // Catch:{ all -> 0x0028 }
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.internal.measurement.zzdk r5 = r4.zzj
            if (r5 == 0) goto L_0x004e
            com.google.android.gms.internal.measurement.zzdk r5 = r4.zzj     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0047 }
            r5.registerOnMeasurementEventListener(r1)     // Catch:{ BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException -> 0x0047 }
            return
        L_0x0047:
            java.lang.String r5 = r4.zzc
            java.lang.String r0 = "Failed to register event listener on calling thread. Trying again on the dynamite thread."
            android.util.Log.w(r5, r0)
        L_0x004e:
            com.google.android.gms.internal.measurement.zzfb r5 = new com.google.android.gms.internal.measurement.zzfb
            r5.<init>(r4, r1)
            r4.zza((com.google.android.gms.internal.measurement.zzdv.zzb) r5)
            return
        L_0x0057:
            monitor-exit(r0)     // Catch:{ all -> 0x0028 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzdv.zza(com.google.android.gms.measurement.internal.zzjj):void");
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        this.zzd.execute(zzb2);
    }

    public final void zza(Bundle bundle) {
        zza((zzb) new zzea(this, bundle));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza((zzb) new zzee(this, activity, str, str2));
    }

    public final void zza(boolean z2) {
        zza((zzb) new zzfa(this, z2));
    }

    public final void zza(zzjg zzjg) {
        zza zza2 = new zza(zzjg);
        if (this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zza2);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.zzc, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        zza((zzb) new zzeu(this, zza2));
    }

    public final void zza(Boolean bool) {
        zza((zzb) new zzed(this, bool));
    }

    public final void zza(long j2) {
        zza((zzb) new zzek(this, j2));
    }

    public final void zza(Intent intent) {
        zza((zzb) new zzfc(this, intent));
    }

    public final void zza(String str, String str2, Object obj, boolean z2) {
        zza((zzb) new zzdx(this, str, str2, obj, z2));
    }
}
