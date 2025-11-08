package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzdl;
import com.google.android.gms.internal.measurement.zzpd;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;

public final class zzlp extends zzf {
    /* access modifiers changed from: private */
    public final zzmm zza;
    /* access modifiers changed from: private */
    public zzgb zzb;
    private volatile Boolean zzc;
    private final zzax zzd;
    private final zznl zze;
    private final List<Runnable> zzf = new ArrayList();
    private final zzax zzg;

    public zzlp(zzhw zzhw) {
        super(zzhw);
        this.zze = new zznl(zzhw.zzb());
        this.zza = new zzmm(this);
        this.zzd = new zzlq(this, zzhw);
        this.zzg = new zzmd(this, zzhw);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzaq() {
        zzt();
        zzj().zzp().zza("Processing queued up service tasks", Integer.valueOf(this.zzf.size()));
        for (Runnable run : this.zzf) {
            try {
                run.run();
            } catch (RuntimeException e3) {
                zzj().zzg().zza("Task exception while flushing queue", e3);
            }
        }
        this.zzf.clear();
        this.zzg.zza();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzar() {
        zzt();
        this.zze.zzb();
        this.zzd.zza(zzbj.zzal.zza(null).longValue());
    }

    @WorkerThread
    public final zzak zzaa() {
        zzt();
        zzu();
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzae();
            zzj().zzc().zza("Failed to get consents; not connected to service yet.");
            return null;
        }
        zzp zzc2 = zzc(false);
        Preconditions.checkNotNull(zzc2);
        try {
            zzak zza2 = zzgb.zza(zzc2);
            zzar();
            return zza2;
        } catch (RemoteException e3) {
            zzj().zzg().zza("Failed to get consents; remote exception", e3);
            return null;
        }
    }

    public final Boolean zzab() {
        return this.zzc;
    }

    @WorkerThread
    public final void zzac() {
        zzt();
        zzu();
        zza((Runnable) new zzmb(this, zzc(true)));
    }

    @WorkerThread
    public final void zzad() {
        zzt();
        zzu();
        zzp zzc2 = zzc(true);
        zzh().zzab();
        zza((Runnable) new zzly(this, zzc2));
    }

    @WorkerThread
    public final void zzae() {
        zzt();
        zzu();
        if (!zzal()) {
            if (zzap()) {
                this.zza.zza();
            } else if (!zze().zzaa()) {
                List<ResolveInfo> queryIntentServices = zza().getPackageManager().queryIntentServices(new Intent().setClassName(zza(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
                if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                    zzj().zzg().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
                    return;
                }
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                intent.setComponent(new ComponentName(zza(), "com.google.android.gms.measurement.AppMeasurementService"));
                this.zza.zza(intent);
            }
        }
    }

    @WorkerThread
    public final void zzaf() {
        zzt();
        zzu();
        this.zza.zzb();
        try {
            ConnectionTracker.getInstance().unbindService(zza(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzb = null;
    }

    public final /* synthetic */ void zzag() {
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzj().zzg().zza("Failed to send Dma consent settings to service");
            return;
        }
        try {
            zzp zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzgb.zzg(zzc2);
            zzar();
        } catch (RemoteException e3) {
            zzj().zzg().zza("Failed to send Dma consent settings to the service", e3);
        }
    }

    public final /* synthetic */ void zzah() {
        zzgb zzgb = this.zzb;
        if (zzgb == null) {
            zzj().zzg().zza("Failed to send storage consent settings to service");
            return;
        }
        try {
            zzp zzc2 = zzc(false);
            Preconditions.checkNotNull(zzc2);
            zzgb.zzi(zzc2);
            zzar();
        } catch (RemoteException e3) {
            zzj().zzg().zza("Failed to send storage consent settings to the service", e3);
        }
    }

    @WorkerThread
    public final void zzai() {
        zzt();
        zzu();
        zzp zzc2 = zzc(false);
        zzh().zzaa();
        zza((Runnable) new zzlx(this, zzc2));
    }

    @WorkerThread
    public final void zzaj() {
        zzt();
        zzu();
        zza((Runnable) new zzlr(this));
    }

    @WorkerThread
    public final void zzak() {
        zzt();
        zzu();
        zza((Runnable) new zzmh(this, zzc(true)));
    }

    @WorkerThread
    public final boolean zzal() {
        zzt();
        zzu();
        return this.zzb != null;
    }

    @WorkerThread
    public final boolean zzam() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= 200900;
    }

    @WorkerThread
    public final boolean zzan() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= zzbj.zzbs.zza(null).intValue();
    }

    @WorkerThread
    public final boolean zzao() {
        zzt();
        zzu();
        return !zzap() || zzq().zzg() >= 241200;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f6  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzap() {
        /*
            r5 = this;
            r5.zzt()
            r5.zzu()
            java.lang.Boolean r0 = r5.zzc
            if (r0 != 0) goto L_0x0103
            r5.zzt()
            r5.zzu()
            com.google.android.gms.measurement.internal.zzgu r0 = r5.zzk()
            java.lang.Boolean r0 = r0.zzp()
            r1 = 1
            if (r0 == 0) goto L_0x0023
            boolean r2 = r0.booleanValue()
            if (r2 == 0) goto L_0x0023
            goto L_0x00fd
        L_0x0023:
            com.google.android.gms.measurement.internal.zzgc r2 = r5.zzg()
            int r2 = r2.zzaa()
            r3 = 0
            if (r2 != r1) goto L_0x0031
        L_0x002e:
            r0 = r1
            goto L_0x00d9
        L_0x0031:
            com.google.android.gms.measurement.internal.zzgi r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzp()
            java.lang.String r4 = "Checking service availability"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzop r2 = r5.zzq()
            r4 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r2 = r2.zza((int) r4)
            if (r2 == 0) goto L_0x00ca
            if (r2 == r1) goto L_0x00bc
            r4 = 2
            if (r2 == r4) goto L_0x009a
            r0 = 3
            if (r2 == r0) goto L_0x008c
            r0 = 9
            if (r2 == r0) goto L_0x007e
            r0 = 18
            if (r2 == r0) goto L_0x0070
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.String r2 = "Unexpected service status"
            r0.zza(r2, r1)
        L_0x006c:
            r0 = r3
            r1 = r0
            goto L_0x00d9
        L_0x0070:
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()
            java.lang.String r2 = "Service updating"
            r0.zza(r2)
            goto L_0x002e
        L_0x007e:
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()
            java.lang.String r1 = "Service invalid"
            r0.zza(r1)
            goto L_0x006c
        L_0x008c:
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()
            java.lang.String r1 = "Service disabled"
            r0.zza(r1)
            goto L_0x006c
        L_0x009a:
            com.google.android.gms.measurement.internal.zzgi r2 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzc()
            java.lang.String r4 = "Service container out of date"
            r2.zza(r4)
            com.google.android.gms.measurement.internal.zzop r2 = r5.zzq()
            int r2 = r2.zzg()
            r4 = 17443(0x4423, float:2.4443E-41)
            if (r2 >= r4) goto L_0x00b6
        L_0x00b3:
            r0 = r1
            r1 = r3
            goto L_0x00d9
        L_0x00b6:
            if (r0 != 0) goto L_0x00b9
            goto L_0x00ba
        L_0x00b9:
            r1 = r3
        L_0x00ba:
            r0 = r3
            goto L_0x00d9
        L_0x00bc:
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()
            java.lang.String r2 = "Service missing"
            r0.zza(r2)
            goto L_0x00b3
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()
            java.lang.String r2 = "Service available"
            r0.zza(r2)
            goto L_0x002e
        L_0x00d9:
            if (r1 != 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzah r2 = r5.zze()
            boolean r2 = r2.zzaa()
            if (r2 == 0) goto L_0x00f3
            com.google.android.gms.measurement.internal.zzgi r0 = r5.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()
            java.lang.String r2 = "No way to upload. Consider using the full version of Analytics"
            r0.zza(r2)
            goto L_0x00f4
        L_0x00f3:
            r3 = r0
        L_0x00f4:
            if (r3 == 0) goto L_0x00fd
            com.google.android.gms.measurement.internal.zzgu r0 = r5.zzk()
            r0.zza((boolean) r1)
        L_0x00fd:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            r5.zzc = r0
        L_0x0103:
            java.lang.Boolean r5 = r5.zzc
            boolean r5 = r5.booleanValue()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlp.zzap():boolean");
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzac zzd() {
        return super.zzd();
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

    public static /* synthetic */ void zzd(zzlp zzlp) {
        zzlp.zzt();
        if (zzlp.zzal()) {
            zzlp.zzj().zzp().zza("Inactivity, disconnecting from the service");
            zzlp.zzaf();
        }
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zza zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzah zze() {
        return super.zze();
    }

    @WorkerThread
    private final zzp zzc(boolean z2) {
        return zzg().zza(z2 ? zzj().zzx() : null);
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @WorkerThread
    public final void zzb(Bundle bundle) {
        zzt();
        zzu();
        if (zze().zza(zzbj.zzdl)) {
            zza((Runnable) new zzmf(this, zzc(false), bundle));
        }
    }

    public static /* synthetic */ void zza(zzlp zzlp, ComponentName componentName) {
        zzlp.zzt();
        if (zzlp.zzb != null) {
            zzlp.zzb = null;
            zzlp.zzj().zzp().zza("Disconnected from device MeasurementService", componentName);
            zzlp.zzt();
            zzlp.zzae();
        }
    }

    @WorkerThread
    public final void zzb(boolean z2) {
        zzt();
        zzu();
        if ((!zzpd.zza() || !zze().zza(zzbj.zzdb)) && z2) {
            zzh().zzaa();
        }
        zza((Runnable) new zzlo(this));
    }

    @WorkerThread
    public final void zza(zzdl zzdl) {
        zzt();
        zzu();
        zza((Runnable) new zzlz(this, zzc(false), zzdl));
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzt();
        zzu();
        zza((Runnable) new zzlw(this, atomicReference, zzc(false)));
    }

    @WorkerThread
    public final void zza(zzdl zzdl, String str, String str2) {
        zzt();
        zzu();
        zza((Runnable) new zzmk(this, str, str2, zzc(false), zzdl));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzaf>> atomicReference, String str, String str2, String str3) {
        zzt();
        zzu();
        zza((Runnable) new zzml(this, atomicReference, str, str2, str3, zzc(false)));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zznk>> atomicReference, Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzlv(this, atomicReference, zzc(false), bundle));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzok>> atomicReference, boolean z2) {
        zzt();
        zzu();
        zza((Runnable) new zzls(this, atomicReference, zzc(false), z2));
    }

    @WorkerThread
    public final void zza(zzdl zzdl, String str, String str2, boolean z2) {
        zzt();
        zzu();
        zza((Runnable) new zzlt(this, str, str2, zzc(false), z2, zzdl));
    }

    @WorkerThread
    public final void zza(AtomicReference<List<zzok>> atomicReference, String str, String str2, String str3, boolean z2) {
        zzt();
        zzu();
        zza((Runnable) new zzmn(this, atomicReference, str, str2, str3, zzc(false), z2));
    }

    @WorkerThread
    public final void zza(zzbh zzbh, String str) {
        Preconditions.checkNotNull(zzbh);
        zzt();
        zzu();
        zza((Runnable) new zzmj(this, true, zzc(true), zzh().zza(zzbh), zzbh, str));
    }

    @WorkerThread
    public final void zza(zzdl zzdl, zzbh zzbh, String str) {
        zzt();
        zzu();
        if (zzq().zza((int) GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            zzj().zzu().zza("Not bundling data. Service unavailable or out of date");
            zzq().zza(zzdl, new byte[0]);
            return;
        }
        zza((Runnable) new zzme(this, zzbh, str, zzdl));
    }

    @WorkerThread
    private final void zza(Runnable runnable) throws IllegalStateException {
        zzt();
        if (zzal()) {
            runnable.run();
        } else if (((long) this.zzf.size()) >= 1000) {
            zzj().zzg().zza("Discarding data. Max runnable queue size reached");
        } else {
            this.zzf.add(runnable);
            this.zzg.zza(60000);
            zzae();
        }
    }

    @WorkerThread
    public final void zza(zzgb zzgb, AbstractSafeParcelable abstractSafeParcelable, zzp zzp) {
        int i3;
        zzt();
        zzu();
        int i4 = 100;
        int i5 = 0;
        while (i5 < 1001 && i4 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zza2 = zzh().zza(100);
            if (zza2 != null) {
                arrayList.addAll(zza2);
                i3 = zza2.size();
            } else {
                i3 = 0;
            }
            if (abstractSafeParcelable != null && i3 < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size = arrayList.size();
            int i6 = 0;
            while (i6 < size) {
                Object obj = arrayList.get(i6);
                i6++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzbh) {
                    try {
                        zzgb.zza((zzbh) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e3) {
                        zzj().zzg().zza("Failed to send event to the service", e3);
                    }
                } else if (abstractSafeParcelable2 instanceof zzok) {
                    try {
                        zzgb.zza((zzok) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e4) {
                        zzj().zzg().zza("Failed to send user property to the service", e4);
                    }
                } else if (abstractSafeParcelable2 instanceof zzaf) {
                    try {
                        zzgb.zza((zzaf) abstractSafeParcelable2, zzp);
                    } catch (RemoteException e5) {
                        zzj().zzg().zza("Failed to send conditional user property to the service", e5);
                    }
                } else {
                    zzj().zzg().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i5++;
            i4 = i3;
        }
    }

    @WorkerThread
    public final void zza(zzaf zzaf) {
        Preconditions.checkNotNull(zzaf);
        zzt();
        zzu();
        zza((Runnable) new zzmi(this, true, zzc(true), zzh().zza(zzaf), new zzaf(zzaf), zzaf));
    }

    @WorkerThread
    public final void zza(boolean z2) {
        zzt();
        zzu();
        if ((!zzpd.zza() || !zze().zza(zzbj.zzdb)) && z2) {
            zzh().zzaa();
        }
        if (zzan()) {
            zza((Runnable) new zzmg(this, zzc(false)));
        }
    }

    @WorkerThread
    public final void zza(zzlh zzlh) {
        zzt();
        zzu();
        zza((Runnable) new zzma(this, zzlh));
    }

    @WorkerThread
    public final void zza(Bundle bundle) {
        zzt();
        zzu();
        zza((Runnable) new zzmc(this, zzc(false), bundle));
    }

    @WorkerThread
    public final void zza(zzgb zzgb) {
        zzt();
        Preconditions.checkNotNull(zzgb);
        this.zzb = zzgb;
        zzar();
        zzaq();
    }

    @WorkerThread
    public final void zza(zzok zzok) {
        zzt();
        zzu();
        zza((Runnable) new zzlu(this, zzc(true), zzh().zza(zzok), zzok));
    }
}
