package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.collection.SieveCacheKt;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzdt;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzgn;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzlk;
import com.google.android.gms.internal.measurement.zzox;
import com.google.android.gms.internal.measurement.zzpd;
import com.google.android.gms.internal.measurement.zzqt;
import com.google.android.gms.internal.measurement.zzrl;
import com.google.android.gms.internal.measurement.zzrw;
import com.google.android.gms.measurement.internal.zzjc;
import com.google.common.net.HttpHeaders;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zznv implements zzja {
    private static volatile zznv zza;
    private List<Long> zzaa;
    private long zzab;
    private final Map<String, zzjc> zzac;
    private final Map<String, zzaz> zzad;
    private final Map<String, zzb> zzae;
    private zzlh zzaf;
    private String zzag;
    private final zzoo zzah;
    private zzhg zzb;
    private zzgp zzc;
    private zzam zzd;
    private zzgs zze;
    private zznm zzf;
    private zzv zzg;
    private final zzol zzh;
    private zzlf zzi;
    private zzms zzj;
    private final zznq zzk;
    private zzhd zzl;
    /* access modifiers changed from: private */
    public final zzhw zzm;
    private boolean zzn;
    private boolean zzo;
    @VisibleForTesting
    private long zzp;
    private List<Runnable> zzq;
    private final Set<String> zzr;
    private int zzs;
    private int zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private FileLock zzx;
    private FileChannel zzy;
    private List<Long> zzz;

    public class zza implements zzat {
        zzgn.zzk zza;
        List<Long> zzb;
        List<zzgn.zzf> zzc;
        private long zzd;

        private static long zza(zzgn.zzf zzf) {
            return ((zzf.zzd() / 1000) / 60) / 60;
        }

        private zza() {
        }

        public final void zza(zzgn.zzk zzk) {
            Preconditions.checkNotNull(zzk);
            this.zza = zzk;
        }

        public final boolean zza(long j2, zzgn.zzf zzf) {
            Preconditions.checkNotNull(zzf);
            if (this.zzc == null) {
                this.zzc = new ArrayList();
            }
            if (this.zzb == null) {
                this.zzb = new ArrayList();
            }
            if (!this.zzc.isEmpty() && zza(this.zzc.get(0)) != zza(zzf)) {
                return false;
            }
            long zzcb = this.zzd + ((long) zzf.zzcb());
            zznv.this.zze();
            if (zzcb >= ((long) Math.max(0, zzbj.zzi.zza(null).intValue()))) {
                return false;
            }
            this.zzd = zzcb;
            this.zzc.add(zzf);
            this.zzb.add(Long.valueOf(j2));
            int size = this.zzc.size();
            zznv.this.zze();
            if (size >= Math.max(1, zzbj.zzj.zza(null).intValue())) {
                return false;
            }
            return true;
        }
    }

    private zznv(zzoh zzoh) {
        this(zzoh, (zzhw) null);
    }

    @WorkerThread
    private final void zzaa() {
        zzl().zzt();
        if (this.zzu || this.zzv || this.zzw) {
            zzj().zzp().zza("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv), Boolean.valueOf(this.zzw));
            return;
        }
        zzj().zzp().zza("Stopping uploading service(s)");
        List<Runnable> list = this.zzq;
        if (list != null) {
            for (Runnable run : list) {
                run.run();
            }
            ((List) Preconditions.checkNotNull(this.zzq)).clear();
        }
    }

    @WorkerThread
    private final void zzab() {
        zzl().zzt();
        for (String next : this.zzr) {
            if (zzrl.zza() && zze().zze(next, zzbj.zzcg)) {
                zzj().zzc().zza("Notifying app that trigger URIs are available. App ID", next);
                Intent intent = new Intent();
                intent.setAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
                intent.setPackage(next);
                this.zzm.zza().sendBroadcast(intent);
            }
        }
        this.zzr.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ad  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzac() {
        /*
            r21 = this;
            r0 = r21
            com.google.android.gms.measurement.internal.zzhp r1 = r21.zzl()
            r1.zzt()
            r21.zzs()
            long r1 = r0.zzp
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004d
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.elapsedRealtime()
            long r5 = r0.zzp
            long r1 = r1 - r5
            long r1 = java.lang.Math.abs(r1)
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004b
            com.google.android.gms.measurement.internal.zzgi r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.Long r2 = java.lang.Long.valueOf(r5)
            java.lang.String r3 = "Upload has been suspended. Will update scheduling later in approximately ms"
            r1.zza(r3, r2)
            com.google.android.gms.measurement.internal.zzgs r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznm r0 = r21.zzz()
            r0.zzu()
            return
        L_0x004b:
            r0.zzp = r3
        L_0x004d:
            com.google.android.gms.measurement.internal.zzhw r1 = r0.zzm
            boolean r1 = r1.zzaf()
            if (r1 == 0) goto L_0x024e
            boolean r1 = r21.zzad()
            if (r1 != 0) goto L_0x005d
            goto L_0x024e
        L_0x005d:
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbj.zzab
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r7 = r5.longValue()
            long r7 = java.lang.Math.max(r3, r7)
            com.google.android.gms.measurement.internal.zzam r5 = r21.zzf()
            boolean r5 = r5.zzz()
            if (r5 != 0) goto L_0x0090
            com.google.android.gms.measurement.internal.zzam r5 = r21.zzf()
            boolean r5 = r5.zzy()
            if (r5 == 0) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            r5 = 0
            goto L_0x0091
        L_0x0090:
            r5 = 1
        L_0x0091:
            if (r5 == 0) goto L_0x00d1
            com.google.android.gms.measurement.internal.zzah r10 = r21.zze()
            java.lang.String r10 = r10.zzu()
            boolean r11 = android.text.TextUtils.isEmpty(r10)
            if (r11 != 0) goto L_0x00bd
            java.lang.String r11 = ".none."
            boolean r10 = r11.equals(r10)
            if (r10 != 0) goto L_0x00bd
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbj.zzw
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00bd:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbj.zzv
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
            goto L_0x00e4
        L_0x00d1:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r10 = com.google.android.gms.measurement.internal.zzbj.zzu
            java.lang.Object r10 = r10.zza(r6)
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            long r10 = java.lang.Math.max(r3, r10)
        L_0x00e4:
            com.google.android.gms.measurement.internal.zzms r12 = r0.zzj
            com.google.android.gms.measurement.internal.zzgz r12 = r12.zzd
            long r12 = r12.zza()
            com.google.android.gms.measurement.internal.zzms r14 = r0.zzj
            com.google.android.gms.measurement.internal.zzgz r14 = r14.zze
            long r14 = r14.zza()
            com.google.android.gms.measurement.internal.zzam r16 = r21.zzf()
            r17 = r10
            long r9 = r16.c_()
            com.google.android.gms.measurement.internal.zzam r11 = r21.zzf()
            r19 = r7
            long r6 = r11.d_()
            long r6 = java.lang.Math.max(r9, r6)
            int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0113
        L_0x0110:
            r10 = r3
            goto L_0x018d
        L_0x0113:
            long r6 = r6 - r1
            long r6 = java.lang.Math.abs(r6)
            long r6 = r1 - r6
            long r12 = r12 - r1
            long r8 = java.lang.Math.abs(r12)
            long r8 = r1 - r8
            long r14 = r14 - r1
            long r10 = java.lang.Math.abs(r14)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r6 + r19
            if (r5 == 0) goto L_0x0139
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0139
            long r10 = java.lang.Math.min(r6, r8)
            long r10 = r10 + r17
        L_0x0139:
            com.google.android.gms.measurement.internal.zzol r5 = r21.zzp()
            r12 = r17
            boolean r5 = r5.zza((long) r8, (long) r12)
            if (r5 != 0) goto L_0x0147
            long r10 = r8 + r12
        L_0x0147:
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x018d
            int r5 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x018d
            r5 = 0
        L_0x0150:
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbj.zzad
            r7 = 0
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r8 = 0
            int r6 = java.lang.Math.max(r8, r6)
            r9 = 20
            int r6 = java.lang.Math.min(r9, r6)
            if (r5 >= r6) goto L_0x0110
            r12 = 1
            long r12 = r12 << r5
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r6 = com.google.android.gms.measurement.internal.zzbj.zzac
            java.lang.Object r6 = r6.zza(r7)
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            long r6 = java.lang.Math.max(r3, r6)
            long r6 = r6 * r12
            long r10 = r10 + r6
            int r6 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x018a
            goto L_0x018d
        L_0x018a:
            int r5 = r5 + 1
            goto L_0x0150
        L_0x018d:
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x01ad
            com.google.android.gms.measurement.internal.zzgi r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.String r2 = "Next upload time is 0"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgs r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznm r0 = r21.zzz()
            r0.zzu()
            return
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzgp r1 = r21.zzh()
            boolean r1 = r1.zzu()
            if (r1 != 0) goto L_0x01d3
            com.google.android.gms.measurement.internal.zzgi r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.String r2 = "No network"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgs r1 = r21.zzy()
            r1.zza()
            com.google.android.gms.measurement.internal.zznm r0 = r21.zzz()
            r0.zzu()
            return
        L_0x01d3:
            com.google.android.gms.measurement.internal.zzms r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzgz r1 = r1.zzc
            long r1 = r1.zza()
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r5 = com.google.android.gms.measurement.internal.zzbj.zzs
            r6 = 0
            java.lang.Object r5 = r5.zza(r6)
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.measurement.internal.zzol r7 = r21.zzp()
            boolean r7 = r7.zza((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01fe
            long r1 = r1 + r5
            long r10 = java.lang.Math.max(r10, r1)
        L_0x01fe:
            com.google.android.gms.measurement.internal.zzgs r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.common.util.Clock r1 = r21.zzb()
            long r1 = r1.currentTimeMillis()
            long r10 = r10 - r1
            int r1 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x0235
            r21.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r1 = com.google.android.gms.measurement.internal.zzbj.zzx
            r2 = 0
            java.lang.Object r1 = r1.zza(r2)
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r10 = java.lang.Math.max(r3, r1)
            com.google.android.gms.measurement.internal.zzms r1 = r0.zzj
            com.google.android.gms.measurement.internal.zzgz r1 = r1.zzd
            com.google.android.gms.common.util.Clock r2 = r21.zzb()
            long r2 = r2.currentTimeMillis()
            r1.zza(r2)
        L_0x0235:
            com.google.android.gms.measurement.internal.zzgi r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.Long r2 = java.lang.Long.valueOf(r10)
            java.lang.String r3 = "Upload scheduled in approximately ms"
            r1.zza(r3, r2)
            com.google.android.gms.measurement.internal.zznm r0 = r21.zzz()
            r0.zza(r10)
            return
        L_0x024e:
            com.google.android.gms.measurement.internal.zzgi r1 = r21.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.zza(r2)
            com.google.android.gms.measurement.internal.zzgs r1 = r21.zzy()
            r1.zzb()
            com.google.android.gms.measurement.internal.zznm r0 = r21.zzz()
            r0.zzu()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzac():void");
    }

    private final boolean zzad() {
        zzl().zzt();
        zzs();
        return zzf().zzx() || !TextUtils.isEmpty(zzf().f_());
    }

    @VisibleForTesting
    @WorkerThread
    private final boolean zzae() {
        zzl().zzt();
        return this.zzz != null;
    }

    @VisibleForTesting
    @WorkerThread
    private final boolean zzaf() {
        zzl().zzt();
        FileLock fileLock = this.zzx;
        if (fileLock == null || !fileLock.isValid()) {
            try {
                FileChannel channel = new RandomAccessFile(new File(zzcf.zza().zza(this.zzm.zza().getFilesDir(), "google_app_measurement.db")), "rw").getChannel();
                this.zzy = channel;
                FileLock tryLock = channel.tryLock();
                this.zzx = tryLock;
                if (tryLock != null) {
                    zzj().zzp().zza("Storage concurrent access okay");
                    return true;
                }
                zzj().zzg().zza("Storage concurrent data access panic");
                return false;
            } catch (FileNotFoundException e3) {
                zzj().zzg().zza("Failed to acquire storage lock", e3);
                return false;
            } catch (IOException e4) {
                zzj().zzg().zza("Failed to access storage lock file", e4);
                return false;
            } catch (OverlappingFileLockException e5) {
                zzj().zzu().zza("Storage lock already acquired", e5);
                return false;
            }
        } else {
            zzj().zzp().zza("Storage concurrent access okay");
            return true;
        }
    }

    @WorkerThread
    private final zzp zzc(String str) {
        String str2 = str;
        zzh zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping", str2);
            return null;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null || zza2.booleanValue()) {
            return new zzp(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zze2.zzag(), zze2.zzd(), 0, 0, zze2.zzaq(), false, zze2.zzaa(), zze2.zzx(), zze2.zzo(), zze2.zzan(), (String) null, zzb(str).zzh(), "", (String) null, zze2.zzat(), zze2.zzw(), zzb(str).zza(), zzd(str).zzf(), zze2.zza(), zze2.zzf(), zze2.zzam(), zze2.zzak());
        }
        zzj().zzg().zza("App version does not match; dropping. appId", zzgi.zza(str));
        return null;
    }

    private final long zzx() {
        long currentTimeMillis = zzb().currentTimeMillis();
        zzms zzms = this.zzj;
        zzms.zzal();
        zzms.zzt();
        long zza2 = zzms.zzf.zza();
        if (zza2 == 0) {
            zza2 = ((long) zzms.zzq().zzv().nextInt(86400000)) + 1;
            zzms.zzf.zza(zza2);
        }
        return ((((currentTimeMillis + zza2) / 1000) / 60) / 60) / 24;
    }

    private final zzgs zzy() {
        zzgs zzgs = this.zze;
        if (zzgs != null) {
            return zzgs;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zznm zzz() {
        return (zznm) zza((zznr) this.zzf);
    }

    public final Clock zzb() {
        return ((zzhw) Preconditions.checkNotNull(this.zzm)).zzb();
    }

    public final zzac zzd() {
        return this.zzm.zzd();
    }

    public final zzah zze() {
        return ((zzhw) Preconditions.checkNotNull(this.zzm)).zzf();
    }

    public final zzam zzf() {
        return (zzam) zza((zznr) this.zzd);
    }

    public final zzgh zzg() {
        return this.zzm.zzk();
    }

    public final zzgp zzh() {
        return (zzgp) zza((zznr) this.zzc);
    }

    public final zzhg zzi() {
        return (zzhg) zza((zznr) this.zzb);
    }

    public final zzgi zzj() {
        return ((zzhw) Preconditions.checkNotNull(this.zzm)).zzj();
    }

    public final zzhw zzk() {
        return this.zzm;
    }

    public final zzhp zzl() {
        return ((zzhw) Preconditions.checkNotNull(this.zzm)).zzl();
    }

    public final zzlf zzm() {
        return (zzlf) zza((zznr) this.zzi);
    }

    public final zzms zzn() {
        return this.zzj;
    }

    public final zznq zzo() {
        return this.zzk;
    }

    public final zzol zzp() {
        return (zzol) zza((zznr) this.zzh);
    }

    public final zzop zzq() {
        return ((zzhw) Preconditions.checkNotNull(this.zzm)).zzt();
    }

    @WorkerThread
    public final void zzr() {
        zzl().zzt();
        zzs();
        if (!this.zzo) {
            this.zzo = true;
            if (zzaf()) {
                int zza2 = zza(this.zzy);
                int zzab2 = this.zzm.zzh().zzab();
                zzl().zzt();
                if (zza2 > zzab2) {
                    zzj().zzg().zza("Panic: can't downgrade version. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                } else if (zza2 >= zzab2) {
                } else {
                    if (zza(zzab2, this.zzy)) {
                        zzj().zzp().zza("Storage version upgraded. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    } else {
                        zzj().zzg().zza("Storage version upgrade failed. Previous, current version", Integer.valueOf(zza2), Integer.valueOf(zzab2));
                    }
                }
            }
        }
    }

    public final void zzs() {
        if (!this.zzn) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    public final void zzt() {
        this.zzt++;
    }

    public final void zzu() {
        this.zzs++;
    }

    @WorkerThread
    public final void zzv() {
        int delete;
        zzl().zzt();
        zzf().zzv();
        zzam zzf2 = zzf();
        zzf2.zzt();
        zzf2.zzal();
        if (zzf2.zzaa()) {
            zzfz<Long> zzfz = zzbj.zzbh;
            if (zzfz.zza(null).longValue() != 0 && (delete = zzf2.e_().delete("trigger_uris", "abs(timestamp_millis - ?) > cast(? as integer)", new String[]{String.valueOf(zzf2.zzb().currentTimeMillis()), String.valueOf(zzfz.zza(null))})) > 0) {
                zzf2.zzj().zzp().zza("Deleted stale trigger uris. rowsDeleted", Integer.valueOf(delete));
            }
        }
        if (this.zzj.zzd.zza() == 0) {
            this.zzj.zzd.zza(zzb().currentTimeMillis());
        }
        zzac();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:238:?, code lost:
        zzj().zzg().zza("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.measurement.internal.zzgi.zza(r6), r16.zzb());
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:237:0x05d0 */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01cb A[SYNTHETIC, Splitter:B:100:0x01cb] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01e3 A[Catch:{ all -> 0x021d }] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0328 A[Catch:{ all -> 0x021d }] */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x03aa A[Catch:{ all -> 0x021d }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x03c2 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x0577 A[Catch:{ all -> 0x021d }] */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0580 A[Catch:{ all -> 0x021d }] */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0596 A[Catch:{ MalformedURLException -> 0x05d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x037f A[SYNTHETIC] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzw() {
        /*
            r25 = this;
            r8 = r25
            com.google.android.gms.measurement.internal.zzhp r0 = r25.zzl()
            r0.zzt()
            r25.zzs()
            r0 = 1
            r8.zzw = r0
            r9 = 0
            com.google.android.gms.measurement.internal.zzhw r1 = r8.zzm     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzlp r1 = r1.zzr()     // Catch:{ all -> 0x021d }
            java.lang.Boolean r1 = r1.zzab()     // Catch:{ all -> 0x021d }
            if (r1 != 0) goto L_0x0033
            com.google.android.gms.measurement.internal.zzgi r0 = r25.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload data called on the client side before use of service was decided"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r25.zzaa()
            return
        L_0x002f:
            r0 = move-exception
            r1 = r9
            goto L_0x0612
        L_0x0033:
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x021d }
            if (r1 == 0) goto L_0x004c
            com.google.android.gms.measurement.internal.zzgi r0 = r25.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Upload called in the client side when service should be used"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r25.zzaa()
            return
        L_0x004c:
            long r1 = r8.zzp     // Catch:{ all -> 0x021d }
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x005d
            r25.zzac()     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r25.zzaa()
            return
        L_0x005d:
            boolean r1 = r25.zzae()     // Catch:{ all -> 0x021d }
            if (r1 == 0) goto L_0x0076
            com.google.android.gms.measurement.internal.zzgi r0 = r25.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Uploading requested multiple times"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r25.zzaa()
            return
        L_0x0076:
            com.google.android.gms.measurement.internal.zzgp r1 = r25.zzh()     // Catch:{ all -> 0x021d }
            boolean r1 = r1.zzu()     // Catch:{ all -> 0x021d }
            if (r1 != 0) goto L_0x0096
            com.google.android.gms.measurement.internal.zzgi r0 = r25.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ all -> 0x002f }
            java.lang.String r1 = "Network not connected, ignoring upload request"
            r0.zza(r1)     // Catch:{ all -> 0x002f }
            r25.zzac()     // Catch:{ all -> 0x002f }
            r8.zzw = r9
            r25.zzaa()
            return
        L_0x0096:
            com.google.android.gms.common.util.Clock r1 = r25.zzb()     // Catch:{ all -> 0x021d }
            long r1 = r1.currentTimeMillis()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzah r5 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbj.zzau     // Catch:{ all -> 0x021d }
            r7 = 0
            int r5 = r5.zzb((java.lang.String) r7, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r6)     // Catch:{ all -> 0x021d }
            r25.zze()     // Catch:{ all -> 0x021d }
            long r10 = com.google.android.gms.measurement.internal.zzah.zzh()     // Catch:{ all -> 0x021d }
            long r10 = r1 - r10
            r6 = r9
        L_0x00b3:
            if (r6 >= r5) goto L_0x00be
            boolean r12 = r8.zza((java.lang.String) r7, (long) r10)     // Catch:{ all -> 0x002f }
            if (r12 == 0) goto L_0x00be
            int r6 = r6 + 1
            goto L_0x00b3
        L_0x00be:
            boolean r5 = com.google.android.gms.internal.measurement.zzrl.zza()     // Catch:{ all -> 0x021d }
            if (r5 == 0) goto L_0x00c7
            r25.zzab()     // Catch:{ all -> 0x002f }
        L_0x00c7:
            com.google.android.gms.measurement.internal.zzms r5 = r8.zzj     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgz r5 = r5.zzd     // Catch:{ all -> 0x021d }
            long r5 = r5.zza()     // Catch:{ all -> 0x021d }
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00ea
            com.google.android.gms.measurement.internal.zzgi r3 = r25.zzj()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzc()     // Catch:{ all -> 0x002f }
            java.lang.String r4 = "Uploading events. Elapsed time since last upload attempt (ms)"
            long r5 = r1 - r5
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x002f }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x002f }
            r3.zza(r4, r5)     // Catch:{ all -> 0x002f }
        L_0x00ea:
            com.google.android.gms.measurement.internal.zzam r3 = r25.zzf()     // Catch:{ all -> 0x021d }
            java.lang.String r6 = r3.f_()     // Catch:{ all -> 0x021d }
            boolean r3 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x021d }
            r4 = -1
            if (r3 != 0) goto L_0x05e6
            long r10 = r8.zzab     // Catch:{ all -> 0x021d }
            int r3 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r3 != 0) goto L_0x010a
            com.google.android.gms.measurement.internal.zzam r3 = r25.zzf()     // Catch:{ all -> 0x002f }
            long r3 = r3.b_()     // Catch:{ all -> 0x002f }
            r8.zzab = r3     // Catch:{ all -> 0x002f }
        L_0x010a:
            com.google.android.gms.measurement.internal.zzah r3 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r4 = com.google.android.gms.measurement.internal.zzbj.zzg     // Catch:{ all -> 0x021d }
            int r3 = r3.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r4)     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzah r4 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r5 = com.google.android.gms.measurement.internal.zzbj.zzh     // Catch:{ all -> 0x021d }
            int r4 = r4.zzb((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r5)     // Catch:{ all -> 0x021d }
            int r4 = java.lang.Math.max(r9, r4)     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzam r5 = r25.zzf()     // Catch:{ all -> 0x021d }
            java.util.List r3 = r5.zza((java.lang.String) r6, (int) r3, (int) r4)     // Catch:{ all -> 0x021d }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x021d }
            if (r4 != 0) goto L_0x05ce
            com.google.android.gms.measurement.internal.zzjc r4 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x021d }
            boolean r4 = r4.zzi()     // Catch:{ all -> 0x021d }
            if (r4 == 0) goto L_0x018d
            java.util.Iterator r4 = r3.iterator()     // Catch:{ all -> 0x002f }
        L_0x013e:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x002f }
            if (r5 == 0) goto L_0x015d
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x002f }
            android.util.Pair r5 = (android.util.Pair) r5     // Catch:{ all -> 0x002f }
            java.lang.Object r5 = r5.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzgn$zzk r5 = (com.google.android.gms.internal.measurement.zzgn.zzk) r5     // Catch:{ all -> 0x002f }
            java.lang.String r10 = r5.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.isEmpty()     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x013e
            java.lang.String r4 = r5.zzan()     // Catch:{ all -> 0x002f }
            goto L_0x015e
        L_0x015d:
            r4 = r7
        L_0x015e:
            if (r4 == 0) goto L_0x018d
            r5 = r9
        L_0x0161:
            int r10 = r3.size()     // Catch:{ all -> 0x002f }
            if (r5 >= r10) goto L_0x018d
            java.lang.Object r10 = r3.get(r5)     // Catch:{ all -> 0x002f }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x002f }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x002f }
            com.google.android.gms.internal.measurement.zzgn$zzk r10 = (com.google.android.gms.internal.measurement.zzgn.zzk) r10     // Catch:{ all -> 0x002f }
            java.lang.String r11 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r11 = r11.isEmpty()     // Catch:{ all -> 0x002f }
            if (r11 != 0) goto L_0x018a
            java.lang.String r10 = r10.zzan()     // Catch:{ all -> 0x002f }
            boolean r10 = r10.equals(r4)     // Catch:{ all -> 0x002f }
            if (r10 != 0) goto L_0x018a
            java.util.List r3 = r3.subList(r9, r5)     // Catch:{ all -> 0x002f }
            goto L_0x018d
        L_0x018a:
            int r5 = r5 + 1
            goto L_0x0161
        L_0x018d:
            com.google.android.gms.internal.measurement.zzgn$zzj$zza r4 = com.google.android.gms.internal.measurement.zzgn.zzj.zzb()     // Catch:{ all -> 0x021d }
            int r5 = r3.size()     // Catch:{ all -> 0x021d }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x021d }
            int r11 = r3.size()     // Catch:{ all -> 0x021d }
            r10.<init>(r11)     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzah r11 = r25.zze()     // Catch:{ all -> 0x021d }
            boolean r11 = r11.zzj(r6)     // Catch:{ all -> 0x021d }
            if (r11 == 0) goto L_0x01b4
            com.google.android.gms.measurement.internal.zzjc r11 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x002f }
            boolean r11 = r11.zzi()     // Catch:{ all -> 0x002f }
            if (r11 == 0) goto L_0x01b4
            r11 = r0
            goto L_0x01b5
        L_0x01b4:
            r11 = r9
        L_0x01b5:
            com.google.android.gms.measurement.internal.zzjc r12 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x021d }
            boolean r12 = r12.zzi()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzjc r13 = r8.zzb((java.lang.String) r6)     // Catch:{ all -> 0x021d }
            boolean r13 = r13.zzj()     // Catch:{ all -> 0x021d }
            boolean r14 = com.google.android.gms.internal.measurement.zzrq.zza()     // Catch:{ all -> 0x021d }
            if (r14 == 0) goto L_0x01d9
            com.google.android.gms.measurement.internal.zzah r14 = r25.zze()     // Catch:{ all -> 0x002f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r15 = com.google.android.gms.measurement.internal.zzbj.zzbv     // Catch:{ all -> 0x002f }
            boolean r14 = r14.zze(r6, r15)     // Catch:{ all -> 0x002f }
            if (r14 == 0) goto L_0x01d9
            r14 = r0
            goto L_0x01da
        L_0x01d9:
            r14 = r9
        L_0x01da:
            com.google.android.gms.measurement.internal.zznq r15 = r8.zzk     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzns r16 = r15.zza((java.lang.String) r6)     // Catch:{ all -> 0x021d }
            r15 = r9
        L_0x01e1:
            if (r15 >= r5) goto L_0x0391
            java.lang.Object r17 = r3.get(r15)     // Catch:{ all -> 0x021d }
            r7 = r17
            android.util.Pair r7 = (android.util.Pair) r7     // Catch:{ all -> 0x021d }
            java.lang.Object r7 = r7.first     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = (com.google.android.gms.internal.measurement.zzgn.zzk) r7     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc$zzb r7 = r7.zzcd()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r7 = (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r7     // Catch:{ all -> 0x021d }
            java.lang.Object r17 = r3.get(r15)     // Catch:{ all -> 0x021d }
            r0 = r17
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x021d }
            java.lang.Object r0 = r0.second     // Catch:{ all -> 0x021d }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x021d }
            r10.add(r0)     // Catch:{ all -> 0x021d }
            r25.zze()     // Catch:{ all -> 0x021d }
            r0 = r10
            r9 = 102001(0x18e71, double:5.0395E-319)
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r9 = r7.zzl((long) r9)     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r9 = r9.zzk((long) r1)     // Catch:{ all -> 0x021d }
            r10 = 0
            r9.zzd((boolean) r10)     // Catch:{ all -> 0x021d }
            if (r11 != 0) goto L_0x0221
            r7.zzk()     // Catch:{ all -> 0x021d }
            goto L_0x0221
        L_0x021d:
            r0 = move-exception
            r1 = 0
            goto L_0x0612
        L_0x0221:
            if (r12 != 0) goto L_0x0229
            r7.zzq()     // Catch:{ all -> 0x021d }
            r7.zzn()     // Catch:{ all -> 0x021d }
        L_0x0229:
            if (r13 != 0) goto L_0x022e
            r7.zzh()     // Catch:{ all -> 0x021d }
        L_0x022e:
            r8.zza((java.lang.String) r6, (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r7)     // Catch:{ all -> 0x021d }
            if (r14 != 0) goto L_0x0236
            r7.zzr()     // Catch:{ all -> 0x021d }
        L_0x0236:
            boolean r9 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x024d
            com.google.android.gms.measurement.internal.zzah r9 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzdd     // Catch:{ all -> 0x021d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x024d
            if (r13 != 0) goto L_0x024d
            r7.zzi()     // Catch:{ all -> 0x021d }
        L_0x024d:
            boolean r9 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x0329
            com.google.android.gms.measurement.internal.zzah r9 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ all -> 0x021d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x0329
            java.lang.String r9 = r7.zzz()     // Catch:{ all -> 0x021d }
            boolean r10 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x021d }
            if (r10 != 0) goto L_0x027e
            java.lang.String r10 = "00000000-0000-0000-0000-000000000000"
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x0272
            goto L_0x027e
        L_0x0272:
            r18 = r3
            r19 = r11
            r23 = r12
            r22 = r13
            r24 = r14
            goto L_0x0322
        L_0x027e:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x021d }
            java.util.List r10 = r7.zzaa()     // Catch:{ all -> 0x021d }
            r9.<init>(r10)     // Catch:{ all -> 0x021d }
            java.util.Iterator r10 = r9.iterator()     // Catch:{ all -> 0x021d }
            r18 = r3
            r19 = r11
            r3 = 0
            r11 = 0
            r20 = 0
            r21 = 0
        L_0x0295:
            boolean r22 = r10.hasNext()     // Catch:{ all -> 0x021d }
            if (r22 == 0) goto L_0x030a
            java.lang.Object r22 = r10.next()     // Catch:{ all -> 0x021d }
            r23 = r12
            r12 = r22
            com.google.android.gms.internal.measurement.zzgn$zzf r12 = (com.google.android.gms.internal.measurement.zzgn.zzf) r12     // Catch:{ all -> 0x021d }
            r22 = r13
            java.lang.String r13 = "_fx"
            r24 = r14
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x021d }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x021d }
            if (r13 == 0) goto L_0x02c3
            r10.remove()     // Catch:{ all -> 0x021d }
            r13 = r22
            r12 = r23
            r14 = r24
            r20 = 1
            r21 = 1
            goto L_0x0295
        L_0x02c3:
            java.lang.String r13 = "_f"
            java.lang.String r14 = r12.zzg()     // Catch:{ all -> 0x021d }
            boolean r13 = r13.equals(r14)     // Catch:{ all -> 0x021d }
            if (r13 == 0) goto L_0x0303
            com.google.android.gms.measurement.internal.zzah r13 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbj.zzcz     // Catch:{ all -> 0x021d }
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)     // Catch:{ all -> 0x021d }
            if (r13 == 0) goto L_0x0301
            r25.zzp()     // Catch:{ all -> 0x021d }
            java.lang.String r13 = "_pfo"
            com.google.android.gms.internal.measurement.zzgn$zzh r13 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x021d }
            if (r13 == 0) goto L_0x02ee
            long r13 = r13.zzd()     // Catch:{ all -> 0x021d }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x021d }
        L_0x02ee:
            r25.zzp()     // Catch:{ all -> 0x021d }
            java.lang.String r13 = "_uwa"
            com.google.android.gms.internal.measurement.zzgn$zzh r12 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r12, (java.lang.String) r13)     // Catch:{ all -> 0x021d }
            if (r12 == 0) goto L_0x0301
            long r11 = r12.zzd()     // Catch:{ all -> 0x021d }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x021d }
        L_0x0301:
            r21 = 1
        L_0x0303:
            r13 = r22
            r12 = r23
            r14 = r24
            goto L_0x0295
        L_0x030a:
            r23 = r12
            r22 = r13
            r24 = r14
            if (r20 == 0) goto L_0x0318
            r7.zzl()     // Catch:{ all -> 0x021d }
            r7.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzf>) r9)     // Catch:{ all -> 0x021d }
        L_0x0318:
            if (r21 == 0) goto L_0x0322
            java.lang.String r9 = r7.zzt()     // Catch:{ all -> 0x021d }
            r10 = 1
            r8.zza((java.lang.String) r9, (boolean) r10, (java.lang.Long) r3, (java.lang.Long) r11)     // Catch:{ all -> 0x021d }
        L_0x0322:
            int r3 = r7.zzc()     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x037f
            goto L_0x0333
        L_0x0329:
            r18 = r3
            r19 = r11
            r23 = r12
            r22 = r13
            r24 = r14
        L_0x0333:
            com.google.android.gms.measurement.internal.zzah r3 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbj.zzbl     // Catch:{ all -> 0x021d }
            boolean r3 = r3.zze(r6, r9)     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x0356
            com.google.android.gms.internal.measurement.zzml r3 = r7.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r3 = (com.google.android.gms.internal.measurement.zzgn.zzk) r3     // Catch:{ all -> 0x021d }
            byte[] r3 = r3.zzca()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzol r9 = r25.zzp()     // Catch:{ all -> 0x021d }
            long r9 = r9.zza((byte[]) r3)     // Catch:{ all -> 0x021d }
            r7.zza((long) r9)     // Catch:{ all -> 0x021d }
        L_0x0356:
            boolean r3 = com.google.android.gms.internal.measurement.zzrw.zza()     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x037c
            com.google.android.gms.measurement.internal.zzah r3 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbj.zzbw     // Catch:{ all -> 0x021d }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r9)     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x037c
            r25.zzq()     // Catch:{ all -> 0x021d }
            boolean r3 = com.google.android.gms.measurement.internal.zzop.zzf(r6)     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x037c
            com.google.android.gms.measurement.internal.zznt r3 = r16.zza()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zznt r9 = com.google.android.gms.measurement.internal.zznt.SGTM     // Catch:{ all -> 0x021d }
            if (r3 != r9) goto L_0x037c
            r7.zzk()     // Catch:{ all -> 0x021d }
        L_0x037c:
            r4.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r7)     // Catch:{ all -> 0x021d }
        L_0x037f:
            int r15 = r15 + 1
            r10 = r0
            r3 = r18
            r11 = r19
            r13 = r22
            r12 = r23
            r14 = r24
            r0 = 1
            r7 = 0
            r9 = 0
            goto L_0x01e1
        L_0x0391:
            r0 = r10
            boolean r3 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x03c2
            com.google.android.gms.measurement.internal.zzah r3 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ all -> 0x021d }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x021d }
            if (r3 == 0) goto L_0x03c2
            int r3 = r4.zza()     // Catch:{ all -> 0x021d }
            if (r3 != 0) goto L_0x03c2
            r8.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ all -> 0x021d }
            java.util.List r7 = java.util.Collections.emptyList()     // Catch:{ all -> 0x021d }
            r2 = 0
            r3 = 204(0xcc, float:2.86E-43)
            r4 = 0
            r5 = 0
            r1 = r25
            r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x021d }
            r1 = 0
            r8.zzw = r1
            r25.zzaa()
            return
        L_0x03c2:
            com.google.android.gms.internal.measurement.zzml r3 = r4.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r3 = (com.google.android.gms.internal.measurement.zzgn.zzj) r3     // Catch:{ all -> 0x021d }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x021d }
            r7.<init>()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzah r9 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzbw     // Catch:{ all -> 0x021d }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x056b
            r25.zzq()     // Catch:{ all -> 0x021d }
            boolean r9 = com.google.android.gms.measurement.internal.zzop.zzf(r6)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x056b
            com.google.android.gms.measurement.internal.zznt r9 = r16.zza()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zznt r10 = com.google.android.gms.measurement.internal.zznt.SGTM     // Catch:{ all -> 0x021d }
            if (r9 != r10) goto L_0x056b
            com.google.android.gms.internal.measurement.zzml r3 = r4.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r3 = (com.google.android.gms.internal.measurement.zzgn.zzj) r3     // Catch:{ all -> 0x021d }
            java.util.List r3 = r3.zzf()     // Catch:{ all -> 0x021d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x021d }
        L_0x03fc:
            boolean r9 = r3.hasNext()     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x0417
            java.lang.Object r9 = r3.next()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = (com.google.android.gms.internal.measurement.zzgn.zzk) r9     // Catch:{ all -> 0x021d }
            boolean r9 = r9.zzbh()     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x03fc
            java.util.UUID r3 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x021d }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x021d }
            goto L_0x0418
        L_0x0417:
            r3 = 0
        L_0x0418:
            com.google.android.gms.internal.measurement.zzml r9 = r4.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r9 = (com.google.android.gms.internal.measurement.zzlc) r9     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r9 = (com.google.android.gms.internal.measurement.zzgn.zzj) r9     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzhp r10 = r25.zzl()     // Catch:{ all -> 0x021d }
            r10.zzt()     // Catch:{ all -> 0x021d }
            r25.zzs()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj$zza r10 = com.google.android.gms.internal.measurement.zzgn.zzj.zza((com.google.android.gms.internal.measurement.zzgn.zzj) r9)     // Catch:{ all -> 0x021d }
            boolean r11 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x021d }
            if (r11 != 0) goto L_0x0437
            r10.zza((java.lang.String) r3)     // Catch:{ all -> 0x021d }
        L_0x0437:
            com.google.android.gms.measurement.internal.zzhg r11 = r25.zzi()     // Catch:{ all -> 0x021d }
            java.lang.String r11 = r11.zzf(r6)     // Catch:{ all -> 0x021d }
            boolean r12 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x021d }
            if (r12 != 0) goto L_0x0448
            r10.zzb(r11)     // Catch:{ all -> 0x021d }
        L_0x0448:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x021d }
            r11.<init>()     // Catch:{ all -> 0x021d }
            java.util.List r9 = r9.zzf()     // Catch:{ all -> 0x021d }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x021d }
        L_0x0455:
            boolean r12 = r9.hasNext()     // Catch:{ all -> 0x021d }
            if (r12 == 0) goto L_0x0474
            java.lang.Object r12 = r9.next()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r12 = (com.google.android.gms.internal.measurement.zzgn.zzk) r12     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r12 = com.google.android.gms.internal.measurement.zzgn.zzk.zza((com.google.android.gms.internal.measurement.zzgn.zzk) r12)     // Catch:{ all -> 0x021d }
            r12.zzk()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzml r12 = r12.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r12 = (com.google.android.gms.internal.measurement.zzlc) r12     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r12 = (com.google.android.gms.internal.measurement.zzgn.zzk) r12     // Catch:{ all -> 0x021d }
            r11.add(r12)     // Catch:{ all -> 0x021d }
            goto L_0x0455
        L_0x0474:
            r10.zzb()     // Catch:{ all -> 0x021d }
            r10.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzk>) r11)     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgi r9 = r25.zzj()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzp()     // Catch:{ all -> 0x021d }
            java.lang.String r11 = "Processed MeasurementBatch for sGTM with sgtmJoinId"
            java.lang.String r12 = r10.zzc()     // Catch:{ all -> 0x021d }
            r9.zza(r11, r12)     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzml r9 = r10.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r9 = (com.google.android.gms.internal.measurement.zzlc) r9     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r9 = (com.google.android.gms.internal.measurement.zzgn.zzj) r9     // Catch:{ all -> 0x021d }
            boolean r10 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x021d }
            if (r10 != 0) goto L_0x0568
            com.google.android.gms.measurement.internal.zzah r10 = r25.zze()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbj.zzca     // Catch:{ all -> 0x021d }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r11)     // Catch:{ all -> 0x021d }
            if (r10 == 0) goto L_0x0568
            com.google.android.gms.internal.measurement.zzml r10 = r4.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r10 = (com.google.android.gms.internal.measurement.zzlc) r10     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r10 = (com.google.android.gms.internal.measurement.zzgn.zzj) r10     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzhp r11 = r25.zzl()     // Catch:{ all -> 0x021d }
            r11.zzt()     // Catch:{ all -> 0x021d }
            r25.zzs()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj$zza r11 = com.google.android.gms.internal.measurement.zzgn.zzj.zzb()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgi r12 = r25.zzj()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgk r12 = r12.zzp()     // Catch:{ all -> 0x021d }
            java.lang.String r13 = "Processing Google Signal, sgtmJoinId"
            java.lang.String r14 = r10.zzd()     // Catch:{ all -> 0x021d }
            r12.zza(r13, r14)     // Catch:{ all -> 0x021d }
            r11.zza((java.lang.String) r3)     // Catch:{ all -> 0x021d }
            java.util.List r3 = r10.zzf()     // Catch:{ all -> 0x021d }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x021d }
        L_0x04d7:
            boolean r10 = r3.hasNext()     // Catch:{ all -> 0x021d }
            if (r10 == 0) goto L_0x04fb
            java.lang.Object r10 = r3.next()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk r10 = (com.google.android.gms.internal.measurement.zzgn.zzk) r10     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r12 = com.google.android.gms.internal.measurement.zzgn.zzk.zzw()     // Catch:{ all -> 0x021d }
            java.lang.String r13 = r10.zzah()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r12 = r12.zzj((java.lang.String) r13)     // Catch:{ all -> 0x021d }
            int r10 = r10.zzd()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r10 = r12.zzg((int) r10)     // Catch:{ all -> 0x021d }
            r11.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r10)     // Catch:{ all -> 0x021d }
            goto L_0x04d7
        L_0x04fb:
            com.google.android.gms.internal.measurement.zzml r3 = r11.zzai()     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ all -> 0x021d }
            com.google.android.gms.internal.measurement.zzgn$zzj r3 = (com.google.android.gms.internal.measurement.zzgn.zzj) r3     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zznq r10 = r8.zzk     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzhg r10 = r10.zzm()     // Catch:{ all -> 0x021d }
            java.lang.String r10 = r10.zzf(r6)     // Catch:{ all -> 0x021d }
            boolean r11 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x021d }
            if (r11 != 0) goto L_0x0550
            com.google.android.gms.measurement.internal.zzfz<java.lang.String> r11 = com.google.android.gms.measurement.internal.zzbj.zzr     // Catch:{ all -> 0x021d }
            r12 = 0
            java.lang.Object r11 = r11.zza(r12)     // Catch:{ all -> 0x021d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x021d }
            android.net.Uri r11 = android.net.Uri.parse(r11)     // Catch:{ all -> 0x021d }
            android.net.Uri$Builder r12 = r11.buildUpon()     // Catch:{ all -> 0x021d }
            java.lang.String r11 = r11.getAuthority()     // Catch:{ all -> 0x021d }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x021d }
            r13.<init>()     // Catch:{ all -> 0x021d }
            r13.append(r10)     // Catch:{ all -> 0x021d }
            java.lang.String r10 = "."
            r13.append(r10)     // Catch:{ all -> 0x021d }
            r13.append(r11)     // Catch:{ all -> 0x021d }
            java.lang.String r10 = r13.toString()     // Catch:{ all -> 0x021d }
            r12.authority(r10)     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzns r10 = new com.google.android.gms.measurement.internal.zzns     // Catch:{ all -> 0x021d }
            android.net.Uri r11 = r12.build()     // Catch:{ all -> 0x021d }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zznt r12 = com.google.android.gms.measurement.internal.zznt.GOOGLE_SIGNAL     // Catch:{ all -> 0x021d }
            r10.<init>(r11, r12)     // Catch:{ all -> 0x021d }
            r12 = 0
            goto L_0x0560
        L_0x0550:
            com.google.android.gms.measurement.internal.zzns r10 = new com.google.android.gms.measurement.internal.zzns     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.String> r11 = com.google.android.gms.measurement.internal.zzbj.zzr     // Catch:{ all -> 0x021d }
            r12 = 0
            java.lang.Object r11 = r11.zza(r12)     // Catch:{ all -> 0x021d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zznt r13 = com.google.android.gms.measurement.internal.zznt.GOOGLE_SIGNAL     // Catch:{ all -> 0x021d }
            r10.<init>(r11, r13)     // Catch:{ all -> 0x021d }
        L_0x0560:
            android.util.Pair r3 = android.util.Pair.create(r3, r10)     // Catch:{ all -> 0x021d }
            r7.add(r3)     // Catch:{ all -> 0x021d }
            goto L_0x0569
        L_0x0568:
            r12 = 0
        L_0x0569:
            r3 = r9
            goto L_0x056c
        L_0x056b:
            r12 = 0
        L_0x056c:
            com.google.android.gms.measurement.internal.zzgi r9 = r25.zzj()     // Catch:{ all -> 0x021d }
            r10 = 2
            boolean r9 = r9.zza((int) r10)     // Catch:{ all -> 0x021d }
            if (r9 == 0) goto L_0x0580
            com.google.android.gms.measurement.internal.zzol r9 = r25.zzp()     // Catch:{ all -> 0x021d }
            java.lang.String r9 = r9.zza((com.google.android.gms.internal.measurement.zzgn.zzj) r3)     // Catch:{ all -> 0x021d }
            goto L_0x0581
        L_0x0580:
            r9 = r12
        L_0x0581:
            r25.zzp()     // Catch:{ all -> 0x021d }
            byte[] r13 = r3.zzca()     // Catch:{ all -> 0x021d }
            r8.zza((java.util.List<java.lang.Long>) r0)     // Catch:{ MalformedURLException -> 0x05d0 }
            com.google.android.gms.measurement.internal.zzms r0 = r8.zzj     // Catch:{ MalformedURLException -> 0x05d0 }
            com.google.android.gms.measurement.internal.zzgz r0 = r0.zze     // Catch:{ MalformedURLException -> 0x05d0 }
            r0.zza(r1)     // Catch:{ MalformedURLException -> 0x05d0 }
            java.lang.String r0 = "?"
            if (r5 <= 0) goto L_0x059f
            r1 = 0
            com.google.android.gms.internal.measurement.zzgn$zzk r0 = r4.zza((int) r1)     // Catch:{ MalformedURLException -> 0x05d0 }
            java.lang.String r0 = r0.zzz()     // Catch:{ MalformedURLException -> 0x05d0 }
        L_0x059f:
            com.google.android.gms.measurement.internal.zzgi r1 = r25.zzj()     // Catch:{ MalformedURLException -> 0x05d0 }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()     // Catch:{ MalformedURLException -> 0x05d0 }
            java.lang.String r2 = "Uploading data. app, uncompressed size, data"
            int r3 = r13.length     // Catch:{ MalformedURLException -> 0x05d0 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ MalformedURLException -> 0x05d0 }
            r1.zza(r2, r0, r3, r9)     // Catch:{ MalformedURLException -> 0x05d0 }
            r0 = 1
            r8.zzv = r0     // Catch:{ MalformedURLException -> 0x05d0 }
            com.google.android.gms.measurement.internal.zzgp r10 = r25.zzh()     // Catch:{ MalformedURLException -> 0x05d0 }
            java.net.URL r12 = new java.net.URL     // Catch:{ MalformedURLException -> 0x05d0 }
            java.lang.String r0 = r16.zzb()     // Catch:{ MalformedURLException -> 0x05d0 }
            r12.<init>(r0)     // Catch:{ MalformedURLException -> 0x05d0 }
            java.util.Map r14 = r16.zzc()     // Catch:{ MalformedURLException -> 0x05d0 }
            com.google.android.gms.measurement.internal.zznw r15 = new com.google.android.gms.measurement.internal.zznw     // Catch:{ MalformedURLException -> 0x05d0 }
            r15.<init>(r8, r6, r7)     // Catch:{ MalformedURLException -> 0x05d0 }
            r11 = r6
            r10.zza(r11, r12, r13, r14, r15)     // Catch:{ MalformedURLException -> 0x05d0 }
        L_0x05ce:
            r1 = 0
            goto L_0x060c
        L_0x05d0:
            com.google.android.gms.measurement.internal.zzgi r0 = r25.zzj()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x021d }
            java.lang.String r1 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ all -> 0x021d }
            java.lang.String r3 = r16.zzb()     // Catch:{ all -> 0x021d }
            r0.zza(r1, r2, r3)     // Catch:{ all -> 0x021d }
            goto L_0x05ce
        L_0x05e6:
            r8.zzab = r4     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzam r0 = r25.zzf()     // Catch:{ all -> 0x021d }
            r25.zze()     // Catch:{ all -> 0x021d }
            long r3 = com.google.android.gms.measurement.internal.zzah.zzh()     // Catch:{ all -> 0x021d }
            long r1 = r1 - r3
            java.lang.String r0 = r0.zza((long) r1)     // Catch:{ all -> 0x021d }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x021d }
            if (r1 != 0) goto L_0x05ce
            com.google.android.gms.measurement.internal.zzam r1 = r25.zzf()     // Catch:{ all -> 0x021d }
            com.google.android.gms.measurement.internal.zzh r0 = r1.zze(r0)     // Catch:{ all -> 0x021d }
            if (r0 == 0) goto L_0x05ce
            r8.zzb((com.google.android.gms.measurement.internal.zzh) r0)     // Catch:{ all -> 0x021d }
            goto L_0x05ce
        L_0x060c:
            r8.zzw = r1
            r25.zzaa()
            return
        L_0x0612:
            r8.zzw = r1
            r25.zzaa()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzw():void");
    }

    public class zzb {
        final String zza;
        long zzb;

        private zzb(zznv zznv) {
            this(zznv, zznv.zzq().zzp());
        }

        private zzb(zznv zznv, String str) {
            this.zza = str;
            this.zzb = zznv.zzb().elapsedRealtime();
        }
    }

    private zznv(zzoh zzoh, zzhw zzhw) {
        this.zzn = false;
        this.zzr = new HashSet();
        this.zzah = new zzoa(this);
        Preconditions.checkNotNull(zzoh);
        this.zzm = zzhw.zza(zzoh.zza, (zzdt) null, (Long) null);
        this.zzab = -1;
        this.zzk = new zznq(this);
        zzol zzol = new zzol(this);
        zzol.zzam();
        this.zzh = zzol;
        zzgp zzgp = new zzgp(this);
        zzgp.zzam();
        this.zzc = zzgp;
        zzhg zzhg = new zzhg(this);
        zzhg.zzam();
        this.zzb = zzhg;
        this.zzac = new HashMap();
        this.zzad = new HashMap();
        this.zzae = new HashMap();
        zzl().zzb((Runnable) new zznx(this, zzoh));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        r0 = r5.zzb;
        r3 = com.google.android.gms.measurement.internal.zzjc.zza.AD_PERSONALIZATION;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(java.lang.String r6, com.google.android.gms.measurement.internal.zzai r7) {
        /*
            r5 = this;
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzb
            com.google.android.gms.internal.measurement.zzfx$zza r0 = r0.zzb(r6)
            r1 = 1
            if (r0 != 0) goto L_0x0011
            com.google.android.gms.measurement.internal.zzjc$zza r5 = com.google.android.gms.measurement.internal.zzjc.zza.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzal r6 = com.google.android.gms.measurement.internal.zzal.FAILSAFE
            r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r5, (com.google.android.gms.measurement.internal.zzal) r6)
            return r1
        L_0x0011:
            boolean r0 = com.google.android.gms.internal.measurement.zzox.zza()
            r2 = 0
            if (r0 == 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzah r0 = r5.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzcw
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r0 == 0) goto L_0x0055
            com.google.android.gms.measurement.internal.zzam r0 = r5.zzf()
            com.google.android.gms.measurement.internal.zzh r0 = r0.zze(r6)
            if (r0 == 0) goto L_0x0055
            java.lang.String r0 = r0.zzak()
            com.google.android.gms.measurement.internal.zzd r0 = com.google.android.gms.measurement.internal.zzd.zza(r0)
            com.google.android.gms.measurement.internal.zzjb r0 = r0.zza()
            com.google.android.gms.measurement.internal.zzjb r3 = com.google.android.gms.measurement.internal.zzjb.POLICY
            if (r0 != r3) goto L_0x0055
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzb
            com.google.android.gms.measurement.internal.zzjc$zza r3 = com.google.android.gms.measurement.internal.zzjc.zza.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzjb r0 = r0.zza((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzjc.zza) r3)
            com.google.android.gms.measurement.internal.zzjb r4 = com.google.android.gms.measurement.internal.zzjb.UNINITIALIZED
            if (r0 == r4) goto L_0x0055
            com.google.android.gms.measurement.internal.zzal r5 = com.google.android.gms.measurement.internal.zzal.REMOTE_ENFORCED_DEFAULT
            r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r3, (com.google.android.gms.measurement.internal.zzal) r5)
            com.google.android.gms.measurement.internal.zzjb r5 = com.google.android.gms.measurement.internal.zzjb.GRANTED
            if (r0 != r5) goto L_0x0054
            return r2
        L_0x0054:
            return r1
        L_0x0055:
            com.google.android.gms.measurement.internal.zzjc$zza r0 = com.google.android.gms.measurement.internal.zzjc.zza.AD_PERSONALIZATION
            com.google.android.gms.measurement.internal.zzal r3 = com.google.android.gms.measurement.internal.zzal.REMOTE_DEFAULT
            r7.zza((com.google.android.gms.measurement.internal.zzjc.zza) r0, (com.google.android.gms.measurement.internal.zzal) r3)
            com.google.android.gms.measurement.internal.zzhg r5 = r5.zzb
            boolean r5 = r5.zzc((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzjc.zza) r0)
            if (r5 == 0) goto L_0x0065
            return r2
        L_0x0065:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, com.google.android.gms.measurement.internal.zzai):int");
    }

    @WorkerThread
    private final zzaz zzd(String str) {
        zzl().zzt();
        zzs();
        zzaz zzaz = this.zzad.get(str);
        if (zzaz != null) {
            return zzaz;
        }
        zzaz zzg2 = zzf().zzg(str);
        this.zzad.put(str, zzg2);
        return zzg2;
    }

    private final Boolean zzh(zzp zzp2) {
        Boolean bool = zzp2.zzq;
        if (!zzox.zza() || !zze().zza(zzbj.zzcw) || TextUtils.isEmpty(zzp2.zzad)) {
            return bool;
        }
        int i3 = zzoc.zza[zzd.zza(zzp2.zzad).zza().ordinal()];
        if (i3 != 1) {
            if (i3 == 2) {
                return Boolean.FALSE;
            }
            if (i3 == 3) {
                return Boolean.TRUE;
            }
            if (i3 != 4) {
                return bool;
            }
        }
        return null;
    }

    private static boolean zzi(zzp zzp2) {
        return !TextUtils.isEmpty(zzp2.zzb) || !TextUtils.isEmpty(zzp2.zzp);
    }

    @WorkerThread
    public final zzjc zzb(String str) {
        zzl().zzt();
        zzs();
        zzjc zzjc = this.zzac.get(str);
        if (zzjc == null) {
            zzjc = zzf().zzi(str);
            if (zzjc == null) {
                zzjc = zzjc.zza;
            }
            zza(str, zzjc);
        }
        return zzjc;
    }

    @VisibleForTesting(otherwise = 4)
    @WorkerThread
    public final void zze(zzp zzp2) {
        if (this.zzz != null) {
            ArrayList arrayList = new ArrayList();
            this.zzaa = arrayList;
            arrayList.addAll(this.zzz);
        }
        zzam zzf2 = zzf();
        String str = (String) Preconditions.checkNotNull(zzp2.zza);
        Preconditions.checkNotEmpty(str);
        zzf2.zzt();
        zzf2.zzal();
        try {
            SQLiteDatabase e_ = zzf2.e_();
            String[] strArr = {str};
            int delete = e_.delete("apps", "app_id=?", strArr) + e_.delete("events", "app_id=?", strArr) + e_.delete("events_snapshot", "app_id=?", strArr) + e_.delete("user_attributes", "app_id=?", strArr) + e_.delete("conditional_properties", "app_id=?", strArr) + e_.delete("raw_events", "app_id=?", strArr) + e_.delete("raw_events_metadata", "app_id=?", strArr) + e_.delete("queue", "app_id=?", strArr) + e_.delete("audience_filter_values", "app_id=?", strArr) + e_.delete("main_event_params", "app_id=?", strArr) + e_.delete("default_event_params", "app_id=?", strArr) + e_.delete("trigger_uris", "app_id=?", strArr) + e_.delete("upload_queue", "app_id=?", strArr);
            if (delete > 0) {
                zzf2.zzj().zzp().zza("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e3) {
            zzf2.zzj().zzg().zza("Error resetting analytics data. appId, error", zzgi.zza(str), e3);
        }
        if (zzp2.zzh) {
            zzd(zzp2);
        }
    }

    @WorkerThread
    public final void zzf(zzp zzp2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzp2.zza);
        zzaz zza2 = zzaz.zza(zzp2.zzz);
        zzj().zzp().zza("Setting DMA consent for package", zzp2.zza, zza2);
        String str = zzp2.zza;
        zzl().zzt();
        zzs();
        zzjb zzc2 = zzaz.zza(zza(str), 100).zzc();
        this.zzad.put(str, zza2);
        zzf().zza(str, zza2);
        zzjb zzc3 = zzaz.zza(zza(str), 100).zzc();
        zzl().zzt();
        zzs();
        zzjb zzjb = zzjb.DENIED;
        boolean z2 = false;
        boolean z3 = zzc2 == zzjb && zzc3 == zzjb.GRANTED;
        boolean z4 = zzc2 == zzjb.GRANTED && zzc3 == zzjb;
        if (zze().zza(zzbj.zzcp)) {
            if (z3 || z4) {
                z2 = true;
            }
            z3 = z2;
        }
        if (z3) {
            zzj().zzp().zza("Generated _dcu event for", str);
            Bundle bundle = new Bundle();
            if (zzf().zza(zzx(), str, false, false, false, false, false, false).zzf < ((long) zze().zzb(str, zzbj.zzay))) {
                bundle.putLong("_r", 1);
                zzj().zzp().zza("_dcu realtime event count", str, Long.valueOf(zzf().zza(zzx(), str, false, false, false, false, false, true).zzf));
            }
            this.zzah.zza(str, "_dcu", bundle);
        }
    }

    @WorkerThread
    public final void zzg(zzp zzp2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotEmpty(zzp2.zza);
        zzjc zza2 = zzjc.zza(zzp2.zzt, zzp2.zzy);
        zzjc zzb2 = zzb(zzp2.zza);
        zzj().zzp().zza("Setting storage consent for package", zzp2.zza, zza2);
        zza(zzp2.zza, zza2);
        if ((!zzpd.zza() || !zze().zza(zzbj.zzdc)) && zza2.zzc(zzb2)) {
            zze(zzp2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:125:0x03b2 A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x03dd A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x03f4 A[SYNTHETIC, Splitter:B:131:0x03f4] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x048d A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x04aa A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0516 A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010e A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01cd A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x022a A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0237 A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x024a A[Catch:{ SQLiteException -> 0x01b8, all -> 0x00c5 }] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.measurement.internal.zzp r24) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            java.lang.String r3 = "_sysu"
            java.lang.String r4 = "_sys"
            java.lang.String r5 = "_pfo"
            java.lang.String r6 = "com.android.vending"
            java.lang.String r0 = "_npa"
            java.lang.String r7 = "_uwa"
            java.lang.String r8 = "app_id=?"
            com.google.android.gms.measurement.internal.zzhp r9 = r23.zzl()
            r9.zzt()
            r23.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r24)
            java.lang.String r9 = r2.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            boolean r9 = zzi(r24)
            if (r9 != 0) goto L_0x002b
            return
        L_0x002b:
            com.google.android.gms.measurement.internal.zzam r9 = r23.zzf()
            java.lang.String r10 = r2.zza
            com.google.android.gms.measurement.internal.zzh r9 = r9.zze(r10)
            r10 = 0
            r11 = 0
            if (r9 == 0) goto L_0x005f
            java.lang.String r13 = r9.zzah()
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 == 0) goto L_0x005f
            java.lang.String r13 = r2.zzb
            boolean r13 = android.text.TextUtils.isEmpty(r13)
            if (r13 != 0) goto L_0x005f
            r9.zzd((long) r11)
            com.google.android.gms.measurement.internal.zzam r13 = r23.zzf()
            r13.zza((com.google.android.gms.measurement.internal.zzh) r9, (boolean) r10, (boolean) r10)
            com.google.android.gms.measurement.internal.zzhg r9 = r23.zzi()
            java.lang.String r13 = r2.zza
            r9.zzj(r13)
        L_0x005f:
            boolean r9 = r2.zzh
            if (r9 != 0) goto L_0x0067
            r23.zza((com.google.android.gms.measurement.internal.zzp) r24)
            return
        L_0x0067:
            long r13 = r2.zzl
            int r9 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r9 != 0) goto L_0x0075
            com.google.android.gms.common.util.Clock r9 = r23.zzb()
            long r13 = r9.currentTimeMillis()
        L_0x0075:
            com.google.android.gms.measurement.internal.zzhw r9 = r1.zzm
            com.google.android.gms.measurement.internal.zzbb r9 = r9.zzg()
            r9.zzm()
            int r9 = r2.zzm
            r15 = 1
            if (r9 == 0) goto L_0x009d
            if (r9 == r15) goto L_0x009d
            com.google.android.gms.measurement.internal.zzgi r16 = r23.zzj()
            com.google.android.gms.measurement.internal.zzgk r15 = r16.zzu()
            java.lang.String r11 = r2.zza
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r11)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r12 = "Incorrect app type, assuming installed app. appId, appType"
            r15.zza(r12, r11, r9)
            r9 = r10
        L_0x009d:
            com.google.android.gms.measurement.internal.zzam r11 = r23.zzf()
            r11.zzp()
            com.google.android.gms.measurement.internal.zzam r11 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzom r11 = r11.zze(r12, r0)     // Catch:{ all -> 0x00c5 }
            java.lang.Boolean r12 = r23.zzh(r24)     // Catch:{ all -> 0x00c5 }
            r21 = r3
            r22 = r4
            if (r11 == 0) goto L_0x00c8
            java.lang.String r15 = "auto"
            java.lang.String r10 = r11.zzb     // Catch:{ all -> 0x00c5 }
            boolean r10 = r15.equals(r10)     // Catch:{ all -> 0x00c5 }
            if (r10 == 0) goto L_0x00c3
            goto L_0x00c8
        L_0x00c3:
            r10 = 1
            goto L_0x00fc
        L_0x00c5:
            r0 = move-exception
            goto L_0x0544
        L_0x00c8:
            if (r12 == 0) goto L_0x00f6
            com.google.android.gms.measurement.internal.zzok r0 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_npa"
            boolean r10 = r12.booleanValue()     // Catch:{ all -> 0x00c5 }
            if (r10 == 0) goto L_0x00d7
            r18 = 1
            goto L_0x00d9
        L_0x00d7:
            r18 = 0
        L_0x00d9:
            java.lang.Long r19 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x00c5 }
            java.lang.String r20 = "auto"
            r10 = 1
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00c5 }
            if (r11 == 0) goto L_0x00f2
            java.lang.Object r11 = r11.zze     // Catch:{ all -> 0x00c5 }
            java.lang.Long r12 = r0.zzc     // Catch:{ all -> 0x00c5 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x00c5 }
            if (r11 != 0) goto L_0x00fc
        L_0x00f2:
            r1.zza((com.google.android.gms.measurement.internal.zzok) r0, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            goto L_0x00fc
        L_0x00f6:
            r10 = 1
            if (r11 == 0) goto L_0x00fc
            r1.zza((java.lang.String) r0, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
        L_0x00fc:
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x00c5 }
            java.lang.Object r11 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x00c5 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzh r0 = r0.zze(r11)     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x01cb
            r23.zzq()     // Catch:{ all -> 0x00c5 }
            java.lang.String r12 = r2.zzb     // Catch:{ all -> 0x00c5 }
            java.lang.String r15 = r0.zzah()     // Catch:{ all -> 0x00c5 }
            java.lang.String r11 = r2.zzp     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r0.zzaa()     // Catch:{ all -> 0x00c5 }
            boolean r3 = com.google.android.gms.measurement.internal.zzop.zza((java.lang.String) r12, (java.lang.String) r15, (java.lang.String) r11, (java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            if (r3 == 0) goto L_0x01cb
            com.google.android.gms.measurement.internal.zzgi r3 = r23.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzu()     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r11 = r0.zzac()     // Catch:{ all -> 0x00c5 }
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r11)     // Catch:{ all -> 0x00c5 }
            r3.zza(r4, r11)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzam r3 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = r0.zzac()     // Catch:{ all -> 0x00c5 }
            r3.zzal()     // Catch:{ all -> 0x00c5 }
            r3.zzt()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)     // Catch:{ all -> 0x00c5 }
            android.database.sqlite.SQLiteDatabase r0 = r3.e_()     // Catch:{ SQLiteException -> 0x01b8 }
            java.lang.String[] r11 = new java.lang.String[]{r4}     // Catch:{ SQLiteException -> 0x01b8 }
            java.lang.String r12 = "events"
            int r12 = r0.delete(r12, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            java.lang.String r15 = "user_attributes"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "conditional_properties"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "apps"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "raw_events"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "raw_events_metadata"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "event_filters"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "property_filters"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "audience_filter_values"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "consent_settings"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "default_event_params"
            int r15 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r15
            java.lang.String r15 = "trigger_uris"
            int r0 = r0.delete(r15, r8, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            int r12 = r12 + r0
            if (r12 <= 0) goto L_0x01ca
            com.google.android.gms.measurement.internal.zzgi r0 = r3.zzj()     // Catch:{ SQLiteException -> 0x01b8 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ SQLiteException -> 0x01b8 }
            java.lang.String r8 = "Deleted application data. app, records"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r12)     // Catch:{ SQLiteException -> 0x01b8 }
            r0.zza(r8, r4, r11)     // Catch:{ SQLiteException -> 0x01b8 }
            goto L_0x01ca
        L_0x01b8:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x00c5 }
            java.lang.String r8 = "Error deleting application data. appId, error"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r4)     // Catch:{ all -> 0x00c5 }
            r3.zza(r8, r4, r0)     // Catch:{ all -> 0x00c5 }
        L_0x01ca:
            r0 = 0
        L_0x01cb:
            if (r0 == 0) goto L_0x0225
            long r3 = r0.zze()     // Catch:{ all -> 0x00c5 }
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x01e4
            long r3 = r0.zze()     // Catch:{ all -> 0x00c5 }
            long r10 = r2.zzj     // Catch:{ all -> 0x00c5 }
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 == 0) goto L_0x01e4
            r3 = 1
            goto L_0x01e5
        L_0x01e4:
            r3 = 0
        L_0x01e5:
            java.lang.String r4 = r0.zzaf()     // Catch:{ all -> 0x00c5 }
            long r10 = r0.zze()     // Catch:{ all -> 0x00c5 }
            r15 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r0 != 0) goto L_0x0200
            if (r4 == 0) goto L_0x0200
            java.lang.String r0 = r2.zzc     // Catch:{ all -> 0x00c5 }
            boolean r0 = r4.equals(r0)     // Catch:{ all -> 0x00c5 }
            if (r0 != 0) goto L_0x0200
            r15 = 1
            goto L_0x0201
        L_0x0200:
            r15 = 0
        L_0x0201:
            r0 = r3 | r15
            if (r0 == 0) goto L_0x0225
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x00c5 }
            r0.<init>()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = "_pv"
            r0.putString(r3, r4)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzbh r3 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_au"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x00c5 }
            r4.<init>(r0)     // Catch:{ all -> 0x00c5 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00c5 }
            r1.zza((com.google.android.gms.measurement.internal.zzbh) r3, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
        L_0x0225:
            r23.zza((com.google.android.gms.measurement.internal.zzp) r24)     // Catch:{ all -> 0x00c5 }
            if (r9 != 0) goto L_0x0237
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = "_f"
            com.google.android.gms.measurement.internal.zzbd r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x00c5 }
            goto L_0x0248
        L_0x0237:
            r3 = 1
            if (r9 != r3) goto L_0x0247
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r2.zza     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = "_v"
            com.google.android.gms.measurement.internal.zzbd r0 = r0.zzd(r3, r4)     // Catch:{ all -> 0x00c5 }
            goto L_0x0248
        L_0x0247:
            r0 = 0
        L_0x0248:
            if (r0 != 0) goto L_0x0516
            r3 = 3600000(0x36ee80, double:1.7786363E-317)
            long r10 = r13 / r3
            r15 = 1
            long r10 = r10 + r15
            long r10 = r10 * r3
            java.lang.String r3 = "_dac"
            java.lang.String r4 = "_et"
            java.lang.String r12 = "_r"
            java.lang.String r15 = "_c"
            if (r9 != 0) goto L_0x04c7
            com.google.android.gms.measurement.internal.zzok r0 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_fot"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00c5 }
            java.lang.String r20 = "auto"
            r9 = r15
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00c5 }
            r1.zza((com.google.android.gms.measurement.internal.zzok) r0, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhp r0 = r23.zzl()     // Catch:{ all -> 0x00c5 }
            r0.zzt()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhd r0 = r1.zzl     // Catch:{ all -> 0x00c5 }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x00c5 }
            r10 = r0
            com.google.android.gms.measurement.internal.zzhd r10 = (com.google.android.gms.measurement.internal.zzhd) r10     // Catch:{ all -> 0x00c5 }
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x0373
            boolean r11 = r0.isEmpty()     // Catch:{ all -> 0x00c5 }
            if (r11 == 0) goto L_0x028d
            goto L_0x0373
        L_0x028d:
            com.google.android.gms.measurement.internal.zzhw r11 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhp r11 = r11.zzl()     // Catch:{ all -> 0x00c5 }
            r11.zzt()     // Catch:{ all -> 0x00c5 }
            boolean r11 = r10.zza()     // Catch:{ all -> 0x00c5 }
            if (r11 != 0) goto L_0x02ad
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzn()     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = "Install Referrer Reporter is not available"
            r0.zza(r6)     // Catch:{ all -> 0x00c5 }
            goto L_0x0382
        L_0x02ad:
            com.google.android.gms.measurement.internal.zzhc r11 = new com.google.android.gms.measurement.internal.zzhc     // Catch:{ all -> 0x00c5 }
            r11.<init>(r10, r0)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhp r0 = r0.zzl()     // Catch:{ all -> 0x00c5 }
            r0.zzt()     // Catch:{ all -> 0x00c5 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x00c5 }
            java.lang.String r15 = "com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE"
            r0.<init>(r15)     // Catch:{ all -> 0x00c5 }
            android.content.ComponentName r15 = new android.content.ComponentName     // Catch:{ all -> 0x00c5 }
            java.lang.String r8 = "com.google.android.finsky.externalreferrer.GetInstallReferrerService"
            r15.<init>(r6, r8)     // Catch:{ all -> 0x00c5 }
            r0.setComponent(r15)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhw r8 = r10.zza     // Catch:{ all -> 0x00c5 }
            android.content.Context r8 = r8.zza()     // Catch:{ all -> 0x00c5 }
            android.content.pm.PackageManager r8 = r8.getPackageManager()     // Catch:{ all -> 0x00c5 }
            if (r8 != 0) goto L_0x02e9
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzw()     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = "Failed to obtain Package Manager to verify binding conditions for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x00c5 }
            goto L_0x0382
        L_0x02e9:
            r15 = 0
            java.util.List r8 = r8.queryIntentServices(r0, r15)     // Catch:{ all -> 0x00c5 }
            if (r8 == 0) goto L_0x0363
            boolean r16 = r8.isEmpty()     // Catch:{ all -> 0x00c5 }
            if (r16 != 0) goto L_0x0363
            java.lang.Object r8 = r8.get(r15)     // Catch:{ all -> 0x00c5 }
            android.content.pm.ResolveInfo r8 = (android.content.pm.ResolveInfo) r8     // Catch:{ all -> 0x00c5 }
            android.content.pm.ServiceInfo r8 = r8.serviceInfo     // Catch:{ all -> 0x00c5 }
            if (r8 == 0) goto L_0x0382
            java.lang.String r15 = r8.packageName     // Catch:{ all -> 0x00c5 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x00c5 }
            if (r8 == 0) goto L_0x0353
            boolean r6 = r6.equals(r15)     // Catch:{ all -> 0x00c5 }
            if (r6 == 0) goto L_0x0353
            boolean r6 = r10.zza()     // Catch:{ all -> 0x00c5 }
            if (r6 == 0) goto L_0x0353
            android.content.Intent r6 = new android.content.Intent     // Catch:{ all -> 0x00c5 }
            r6.<init>(r0)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.common.stats.ConnectionTracker r0 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ RuntimeException -> 0x0337 }
            com.google.android.gms.measurement.internal.zzhw r8 = r10.zza     // Catch:{ RuntimeException -> 0x0337 }
            android.content.Context r8 = r8.zza()     // Catch:{ RuntimeException -> 0x0337 }
            r15 = 1
            boolean r0 = r0.bindService(r8, r6, r11, r15)     // Catch:{ RuntimeException -> 0x0337 }
            com.google.android.gms.measurement.internal.zzhw r6 = r10.zza     // Catch:{ RuntimeException -> 0x0337 }
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()     // Catch:{ RuntimeException -> 0x0337 }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzp()     // Catch:{ RuntimeException -> 0x0337 }
            java.lang.String r11 = "Install Referrer Service is"
            if (r0 == 0) goto L_0x0339
            java.lang.String r0 = "available"
            goto L_0x033b
        L_0x0337:
            r0 = move-exception
            goto L_0x033f
        L_0x0339:
            java.lang.String r0 = "not available"
        L_0x033b:
            r6.zza(r11, r0)     // Catch:{ RuntimeException -> 0x0337 }
            goto L_0x0382
        L_0x033f:
            com.google.android.gms.measurement.internal.zzhw r6 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzg()     // Catch:{ all -> 0x00c5 }
            java.lang.String r10 = "Exception occurred while binding to Install Referrer Service"
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00c5 }
            r6.zza(r10, r0)     // Catch:{ all -> 0x00c5 }
            goto L_0x0382
        L_0x0353:
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzu()     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = "Play Store version 8.3.73 or higher required for Install Referrer"
            r0.zza(r6)     // Catch:{ all -> 0x00c5 }
            goto L_0x0382
        L_0x0363:
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzn()     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = "Play Service for fetching Install Referrer is unavailable on device"
            r0.zza(r6)     // Catch:{ all -> 0x00c5 }
            goto L_0x0382
        L_0x0373:
            com.google.android.gms.measurement.internal.zzhw r0 = r10.zza     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgi r0 = r0.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzw()     // Catch:{ all -> 0x00c5 }
            java.lang.String r6 = "Install Referrer Reporter was called with invalid app package name"
            r0.zza(r6)     // Catch:{ all -> 0x00c5 }
        L_0x0382:
            com.google.android.gms.measurement.internal.zzhp r0 = r23.zzl()     // Catch:{ all -> 0x00c5 }
            r0.zzt()     // Catch:{ all -> 0x00c5 }
            r23.zzs()     // Catch:{ all -> 0x00c5 }
            android.os.Bundle r6 = new android.os.Bundle     // Catch:{ all -> 0x00c5 }
            r6.<init>()     // Catch:{ all -> 0x00c5 }
            r10 = 1
            r6.putLong(r9, r10)     // Catch:{ all -> 0x00c5 }
            r6.putLong(r12, r10)     // Catch:{ all -> 0x00c5 }
            r8 = 0
            r6.putLong(r7, r8)     // Catch:{ all -> 0x00c5 }
            r6.putLong(r5, r8)     // Catch:{ all -> 0x00c5 }
            r12 = r22
            r6.putLong(r12, r8)     // Catch:{ all -> 0x00c5 }
            r15 = r21
            r6.putLong(r15, r8)     // Catch:{ all -> 0x00c5 }
            r6.putLong(r4, r10)     // Catch:{ all -> 0x00c5 }
            boolean r0 = r2.zzo     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x03b5
            r6.putLong(r3, r10)     // Catch:{ all -> 0x00c5 }
        L_0x03b5:
            java.lang.String r0 = r2.zza     // Catch:{ all -> 0x00c5 }
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ all -> 0x00c5 }
            r3 = r0
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x00c5 }
            r0.zzt()     // Catch:{ all -> 0x00c5 }
            r0.zzal()     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = "first_open_count"
            long r8 = r0.zzb((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhw r0 = r1.zzm     // Catch:{ all -> 0x00c5 }
            android.content.Context r0 = r0.zza()     // Catch:{ all -> 0x00c5 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x00c5 }
            if (r0 != 0) goto L_0x03f4
            com.google.android.gms.measurement.internal.zzgi r0 = r23.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x00c5 }
            java.lang.String r4 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            r0.zza(r4, r3)     // Catch:{ all -> 0x00c5 }
            r21 = r5
        L_0x03f0:
            r3 = 0
            goto L_0x04a6
        L_0x03f4:
            com.google.android.gms.measurement.internal.zzhw r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x0404 }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x0404 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0404 }
            r4 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0404 }
            goto L_0x0417
        L_0x0404:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgi r4 = r23.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x00c5 }
            java.lang.String r10 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            r4.zza(r10, r11, r0)     // Catch:{ all -> 0x00c5 }
            r0 = 0
        L_0x0417:
            if (r0 == 0) goto L_0x0465
            long r10 = r0.firstInstallTime     // Catch:{ all -> 0x00c5 }
            r16 = 0
            int r4 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x0465
            r21 = r5
            long r4 = r0.lastUpdateTime     // Catch:{ all -> 0x00c5 }
            int r0 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0448
            com.google.android.gms.measurement.internal.zzah r0 = r23.zze()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbj.zzbr     // Catch:{ all -> 0x00c5 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r4)     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x0441
            r4 = 0
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0446
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x00c5 }
            goto L_0x0446
        L_0x0441:
            r4 = 1
            r6.putLong(r7, r4)     // Catch:{ all -> 0x00c5 }
        L_0x0446:
            r0 = 0
            goto L_0x0449
        L_0x0448:
            r0 = 1
        L_0x0449:
            com.google.android.gms.measurement.internal.zzok r4 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_fi"
            if (r0 == 0) goto L_0x0452
            r10 = 1
            goto L_0x0454
        L_0x0452:
            r10 = 0
        L_0x0454:
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00c5 }
            java.lang.String r20 = "auto"
            r5 = r15
            r15 = r4
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00c5 }
            r1.zza((com.google.android.gms.measurement.internal.zzok) r4, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            goto L_0x0468
        L_0x0465:
            r21 = r5
            r5 = r15
        L_0x0468:
            com.google.android.gms.measurement.internal.zzhw r0 = r1.zzm     // Catch:{ NameNotFoundException -> 0x0478 }
            android.content.Context r0 = r0.zza()     // Catch:{ NameNotFoundException -> 0x0478 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x0478 }
            r4 = 0
            android.content.pm.ApplicationInfo r11 = r0.getApplicationInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x0478 }
            goto L_0x048b
        L_0x0478:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgi r4 = r23.zzj()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x00c5 }
            java.lang.String r7 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            r4.zza(r7, r3, r0)     // Catch:{ all -> 0x00c5 }
            r11 = 0
        L_0x048b:
            if (r11 == 0) goto L_0x03f0
            int r0 = r11.flags     // Catch:{ all -> 0x00c5 }
            r3 = 1
            r0 = r0 & r3
            if (r0 == 0) goto L_0x0499
            r3 = 1
            r6.putLong(r12, r3)     // Catch:{ all -> 0x00c5 }
            goto L_0x049b
        L_0x0499:
            r3 = 1
        L_0x049b:
            int r0 = r11.flags     // Catch:{ all -> 0x00c5 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x03f0
            r6.putLong(r5, r3)     // Catch:{ all -> 0x00c5 }
            goto L_0x03f0
        L_0x04a6:
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x04af
            r3 = r21
            r6.putLong(r3, r8)     // Catch:{ all -> 0x00c5 }
        L_0x04af:
            com.google.android.gms.measurement.internal.zzbh r0 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_f"
            com.google.android.gms.measurement.internal.zzbc r3 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x00c5 }
            r3.<init>(r6)     // Catch:{ all -> 0x00c5 }
            java.lang.String r18 = "auto"
            r15 = r0
            r17 = r3
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00c5 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbh) r0, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            goto L_0x0535
        L_0x04c7:
            r6 = r15
            r5 = 1
            if (r9 != r5) goto L_0x0535
            com.google.android.gms.measurement.internal.zzok r0 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_fvt"
            java.lang.Long r19 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00c5 }
            java.lang.String r20 = "auto"
            r15 = r0
            r17 = r13
            r15.<init>(r16, r17, r19, r20)     // Catch:{ all -> 0x00c5 }
            r1.zza((com.google.android.gms.measurement.internal.zzok) r0, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzhp r0 = r23.zzl()     // Catch:{ all -> 0x00c5 }
            r0.zzt()     // Catch:{ all -> 0x00c5 }
            r23.zzs()     // Catch:{ all -> 0x00c5 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x00c5 }
            r0.<init>()     // Catch:{ all -> 0x00c5 }
            r7 = 1
            r0.putLong(r6, r7)     // Catch:{ all -> 0x00c5 }
            r0.putLong(r12, r7)     // Catch:{ all -> 0x00c5 }
            r0.putLong(r4, r7)     // Catch:{ all -> 0x00c5 }
            boolean r4 = r2.zzo     // Catch:{ all -> 0x00c5 }
            if (r4 == 0) goto L_0x04ff
            r0.putLong(r3, r7)     // Catch:{ all -> 0x00c5 }
        L_0x04ff:
            com.google.android.gms.measurement.internal.zzbh r3 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_v"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x00c5 }
            r4.<init>(r0)     // Catch:{ all -> 0x00c5 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00c5 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbh) r3, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
            goto L_0x0535
        L_0x0516:
            boolean r0 = r2.zzi     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x0535
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x00c5 }
            r0.<init>()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzbh r3 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00c5 }
            java.lang.String r16 = "_cd"
            com.google.android.gms.measurement.internal.zzbc r4 = new com.google.android.gms.measurement.internal.zzbc     // Catch:{ all -> 0x00c5 }
            r4.<init>(r0)     // Catch:{ all -> 0x00c5 }
            java.lang.String r18 = "auto"
            r15 = r3
            r17 = r4
            r19 = r13
            r15.<init>(r16, r17, r18, r19)     // Catch:{ all -> 0x00c5 }
            r1.zzb((com.google.android.gms.measurement.internal.zzbh) r3, (com.google.android.gms.measurement.internal.zzp) r2)     // Catch:{ all -> 0x00c5 }
        L_0x0535:
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()     // Catch:{ all -> 0x00c5 }
            r0.zzw()     // Catch:{ all -> 0x00c5 }
            com.google.android.gms.measurement.internal.zzam r0 = r23.zzf()
            r0.zzu()
            return
        L_0x0544:
            com.google.android.gms.measurement.internal.zzam r1 = r23.zzf()
            r1.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzd(com.google.android.gms.measurement.internal.zzp):void");
    }

    public final String zzb(zzp zzp2) {
        try {
            return (String) zzl().zza(new zzob(this, zzp2)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e3) {
            zzj().zzg().zza("Failed to get app instance id. appId", zzgi.zza(zzp2.zza), e3);
            return null;
        }
    }

    @WorkerThread
    private final void zzb(zzh zzh2) {
        zzh zzh3 = zzh2;
        zzl().zzt();
        if (!TextUtils.isEmpty(zzh2.zzah()) || !TextUtils.isEmpty(zzh2.zzaa())) {
            ArrayMap arrayMap = null;
            if (!zzqt.zza() || !zze().zza(zzbj.zzcc)) {
                String zza2 = this.zzk.zza(zzh3);
                try {
                    String str = (String) Preconditions.checkNotNull(zzh2.zzac());
                    URL url = new URL(zza2);
                    zzj().zzp().zza("Fetching remote configuration", str);
                    zzfx.zzd zzc2 = zzi().zzc(str);
                    String zze2 = zzi().zze(str);
                    if (zzc2 != null) {
                        if (!TextUtils.isEmpty(zze2)) {
                            arrayMap = new ArrayMap();
                            arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zze2);
                        }
                        String zzd2 = zzi().zzd(str);
                        if (!TextUtils.isEmpty(zzd2)) {
                            if (arrayMap == null) {
                                arrayMap = new ArrayMap();
                            }
                            arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzd2);
                        }
                    }
                    this.zzu = true;
                    zzgp zzh4 = zzh();
                    zzny zzny = new zzny(this);
                    zzh4.zzt();
                    zzh4.zzal();
                    Preconditions.checkNotNull(url);
                    Preconditions.checkNotNull(zzny);
                    zzh4.zzl().zza((Runnable) new zzgt(zzh4, str, url, (byte[]) null, arrayMap, zzny));
                } catch (MalformedURLException unused) {
                    zzj().zzg().zza("Failed to parse config URL. Not fetching. appId", zzgi.zza(zzh2.zzac()), zza2);
                }
            } else {
                String str2 = (String) Preconditions.checkNotNull(zzh2.zzac());
                zzj().zzp().zza("Fetching remote configuration", str2);
                zzfx.zzd zzc3 = zzi().zzc(str2);
                String zze3 = zzi().zze(str2);
                if (zzc3 != null) {
                    if (!TextUtils.isEmpty(zze3)) {
                        arrayMap = new ArrayMap();
                        arrayMap.put(HttpHeaders.IF_MODIFIED_SINCE, zze3);
                    }
                    String zzd3 = zzi().zzd(str2);
                    if (!TextUtils.isEmpty(zzd3)) {
                        if (arrayMap == null) {
                            arrayMap = new ArrayMap();
                        }
                        arrayMap.put(HttpHeaders.IF_NONE_MATCH, zzd3);
                    }
                }
                ArrayMap arrayMap2 = arrayMap;
                this.zzu = true;
                zzgp zzh5 = zzh();
                zznu zznu = new zznu(this);
                zzh5.zzt();
                zzh5.zzal();
                Preconditions.checkNotNull(zzh2);
                Preconditions.checkNotNull(zznu);
                String zza3 = zzh5.zzo().zza(zzh3);
                try {
                    zzgp zzgp = zzh5;
                    zzh5.zzl().zza((Runnable) new zzgt(zzgp, zzh2.zzac(), new URI(zza3).toURL(), (byte[]) null, arrayMap2, zznu));
                } catch (IllegalArgumentException | MalformedURLException | URISyntaxException unused2) {
                    zzh5.zzj().zzg().zza("Failed to parse config URL. Not fetching. appId", zzgi.zza(zzh2.zzac()), zza3);
                }
            }
        } else {
            zza((String) Preconditions.checkNotNull(zzh2.zzac()), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final int zza(FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzj().zzu().zza("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e3) {
            zzj().zzg().zza("Failed to read from channel", e3);
            return 0;
        }
    }

    public final Context zza() {
        return this.zzm.zza();
    }

    @WorkerThread
    public final Bundle zza(String str) {
        int i3;
        zzl().zzt();
        zzs();
        if (zzi().zzb(str) == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        zzjc zzb2 = zzb(str);
        bundle.putAll(zzb2.zzb());
        bundle.putAll(zza(str, zzd(str), zzb2, new zzai()).zzb());
        if (zzp().zzc(str)) {
            i3 = 1;
        } else {
            zzom zze2 = zzf().zze(str, "_npa");
            if (zze2 != null) {
                i3 = zze2.zze.equals(1L);
            } else {
                i3 = zza(str, new zzai());
            }
        }
        bundle.putString("ad_personalization", i3 == 1 ? "denied" : "granted");
        return bundle;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:48|49|50|51|52|53|54|55) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x0107 */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zze(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.measurement.internal.zzhp r0 = r11.zzl()
            r0.zzt()
            r11.zzs()
            r0 = 1
            r11.zzw = r0
            r1 = 0
            com.google.android.gms.measurement.internal.zzhw r2 = r11.zzm     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzlp r2 = r2.zzr()     // Catch:{ all -> 0x002d }
            java.lang.Boolean r2 = r2.zzab()     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x0030
            com.google.android.gms.measurement.internal.zzgi r12 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r12 = r12.zzu()     // Catch:{ all -> 0x002d }
            java.lang.String r0 = "Upload data called on the client side before use of service was decided"
            r12.zza(r0)     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x002d:
            r12 = move-exception
            goto L_0x0122
        L_0x0030:
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x0049
            com.google.android.gms.measurement.internal.zzgi r12 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r12 = r12.zzg()     // Catch:{ all -> 0x002d }
            java.lang.String r0 = "Upload called in the client side when service should be used"
            r12.zza(r0)     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0049:
            long r2 = r11.zzp     // Catch:{ all -> 0x002d }
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x005a
            r11.zzac()     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x005a:
            boolean r2 = r11.zzae()     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x0073
            com.google.android.gms.measurement.internal.zzgi r12 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r12 = r12.zzp()     // Catch:{ all -> 0x002d }
            java.lang.String r0 = "Uploading requested multiple times"
            r12.zza(r0)     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0073:
            com.google.android.gms.measurement.internal.zzgp r2 = r11.zzh()     // Catch:{ all -> 0x002d }
            boolean r2 = r2.zzu()     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x0093
            com.google.android.gms.measurement.internal.zzgi r12 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r12 = r12.zzp()     // Catch:{ all -> 0x002d }
            java.lang.String r0 = "Network not connected, ignoring upload request"
            r12.zza(r0)     // Catch:{ all -> 0x002d }
            r11.zzac()     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0093:
            com.google.android.gms.measurement.internal.zzam r2 = r11.zzf()     // Catch:{ all -> 0x002d }
            boolean r2 = r2.zzs(r12)     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x00b0
            com.google.android.gms.measurement.internal.zzgi r0 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ all -> 0x002d }
            java.lang.String r2 = "Upload queue has no batches for appId"
            r0.zza(r2, r12)     // Catch:{ all -> 0x002d }
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x00b0:
            com.google.android.gms.measurement.internal.zzam r2 = r11.zzf()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzog r2 = r2.zzj(r12)     // Catch:{ all -> 0x002d }
            if (r2 != 0) goto L_0x00c0
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x00c0:
            com.google.android.gms.internal.measurement.zzgn$zzj r3 = r2.zzb()     // Catch:{ all -> 0x002d }
            if (r3 != 0) goto L_0x00cc
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x00cc:
            com.google.android.gms.measurement.internal.zzol r4 = r11.zzp()     // Catch:{ all -> 0x002d }
            java.lang.String r4 = r4.zza((com.google.android.gms.internal.measurement.zzgn.zzj) r3)     // Catch:{ all -> 0x002d }
            byte[] r8 = r3.zzca()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgi r3 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Uploading data from upload queue. appId, uncompressed size, data"
            int r6 = r8.length     // Catch:{ all -> 0x002d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x002d }
            r3.zza(r5, r12, r6, r4)     // Catch:{ all -> 0x002d }
            r11.zzv = r0     // Catch:{ MalformedURLException -> 0x0107 }
            com.google.android.gms.measurement.internal.zzgp r5 = r11.zzh()     // Catch:{ MalformedURLException -> 0x0107 }
            java.net.URL r7 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0107 }
            java.lang.String r0 = r2.zzc()     // Catch:{ MalformedURLException -> 0x0107 }
            r7.<init>(r0)     // Catch:{ MalformedURLException -> 0x0107 }
            java.util.Map r9 = r2.zzd()     // Catch:{ MalformedURLException -> 0x0107 }
            com.google.android.gms.measurement.internal.zznz r10 = new com.google.android.gms.measurement.internal.zznz     // Catch:{ MalformedURLException -> 0x0107 }
            r10.<init>(r11, r12, r2)     // Catch:{ MalformedURLException -> 0x0107 }
            r6 = r12
            r5.zza(r6, r7, r8, r9, r10)     // Catch:{ MalformedURLException -> 0x0107 }
            goto L_0x011c
        L_0x0107:
            com.google.android.gms.measurement.internal.zzgi r0 = r11.zzj()     // Catch:{ all -> 0x002d }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzg()     // Catch:{ all -> 0x002d }
            java.lang.String r3 = "Failed to parse URL. Not uploading MeasurementBatch. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r12)     // Catch:{ all -> 0x002d }
            java.lang.String r2 = r2.zzc()     // Catch:{ all -> 0x002d }
            r0.zza(r3, r12, r2)     // Catch:{ all -> 0x002d }
        L_0x011c:
            r11.zzw = r1
            r11.zzaa()
            return
        L_0x0122:
            r11.zzw = r1
            r11.zzaa()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zze(java.lang.String):void");
    }

    public final zzv zzc() {
        return (zzv) zza((zznr) this.zzg);
    }

    @WorkerThread
    public final void zzc(zzp zzp2) {
        zzl().zzt();
        zzs();
        Preconditions.checkNotNull(zzp2);
        Preconditions.checkNotEmpty(zzp2.zza);
        if (zze().zza(zzbj.zzdg)) {
            int i3 = 0;
            if (zze().zza(zzbj.zzbi)) {
                long currentTimeMillis = zzb().currentTimeMillis();
                int zzb2 = zze().zzb((String) null, zzbj.zzau);
                zze();
                long zzh2 = currentTimeMillis - zzah.zzh();
                while (i3 < zzb2 && zza((String) null, zzh2)) {
                    i3++;
                }
            } else {
                zze();
                long zzn2 = zzah.zzn();
                while (((long) i3) < zzn2 && zza(zzp2.zza, 0)) {
                    i3++;
                }
            }
            if (zze().zza(zzbj.zzbj)) {
                zzab();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x025f  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x024f  */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzh zza(com.google.android.gms.measurement.internal.zzp r13) {
        /*
            r12 = this;
            com.google.android.gms.measurement.internal.zzhp r0 = r12.zzl()
            r0.zzt()
            r12.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            java.lang.String r0 = r13.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            java.lang.String r0 = r13.zzu
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 != 0) goto L_0x0029
            java.util.Map<java.lang.String, com.google.android.gms.measurement.internal.zznv$zzb> r0 = r12.zzae
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zznv$zzb r3 = new com.google.android.gms.measurement.internal.zznv$zzb
            java.lang.String r4 = r13.zzu
            r3.<init>(r4)
            r0.put(r2, r3)
        L_0x0029:
            com.google.android.gms.measurement.internal.zzam r0 = r12.zzf()
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zzh r0 = r0.zze(r2)
            java.lang.String r2 = r13.zza
            com.google.android.gms.measurement.internal.zzjc r2 = r12.zzb((java.lang.String) r2)
            java.lang.String r3 = r13.zzt
            com.google.android.gms.measurement.internal.zzjc r3 = com.google.android.gms.measurement.internal.zzjc.zzb((java.lang.String) r3)
            com.google.android.gms.measurement.internal.zzjc r2 = r2.zza((com.google.android.gms.measurement.internal.zzjc) r3)
            boolean r3 = r2.zzi()
            if (r3 == 0) goto L_0x0054
            com.google.android.gms.measurement.internal.zzms r3 = r12.zzj
            java.lang.String r4 = r13.zza
            boolean r5 = r13.zzn
            java.lang.String r3 = r3.zza((java.lang.String) r4, (boolean) r5)
            goto L_0x0056
        L_0x0054:
            java.lang.String r3 = ""
        L_0x0056:
            r4 = 0
            if (r0 != 0) goto L_0x007b
            com.google.android.gms.measurement.internal.zzh r0 = new com.google.android.gms.measurement.internal.zzh
            com.google.android.gms.measurement.internal.zzhw r5 = r12.zzm
            java.lang.String r6 = r13.zza
            r0.<init>(r5, r6)
            boolean r5 = r2.zzj()
            if (r5 == 0) goto L_0x006f
            java.lang.String r5 = r12.zza((com.google.android.gms.measurement.internal.zzjc) r2)
            r0.zzb((java.lang.String) r5)
        L_0x006f:
            boolean r2 = r2.zzi()
            if (r2 == 0) goto L_0x0078
            r0.zzh((java.lang.String) r3)
        L_0x0078:
            r2 = r4
            goto L_0x0142
        L_0x007b:
            boolean r5 = r2.zzi()
            if (r5 == 0) goto L_0x0129
            if (r3 == 0) goto L_0x0129
            java.lang.String r5 = r0.zzaj()
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L_0x0129
            java.lang.String r5 = r0.zzaj()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            r0.zzh((java.lang.String) r3)
            boolean r3 = r13.zzn
            if (r3 == 0) goto L_0x0110
            com.google.android.gms.measurement.internal.zzms r3 = r12.zzj
            java.lang.String r6 = r13.zza
            android.util.Pair r3 = r3.zza((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzjc) r2)
            java.lang.Object r3 = r3.first
            java.lang.String r6 = "00000000-0000-0000-0000-000000000000"
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x0110
            if (r5 != 0) goto L_0x0110
            boolean r3 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r3 == 0) goto L_0x00ca
            com.google.android.gms.measurement.internal.zzah r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x00ca
            boolean r3 = r2.zzj()
            if (r3 != 0) goto L_0x00ca
            r2 = 1
            goto L_0x00d2
        L_0x00ca:
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzjc) r2)
            r0.zzb((java.lang.String) r2)
            r2 = r4
        L_0x00d2:
            com.google.android.gms.measurement.internal.zzam r3 = r12.zzf()
            java.lang.String r5 = r13.zza
            java.lang.String r6 = "_id"
            com.google.android.gms.measurement.internal.zzom r3 = r3.zze(r5, r6)
            if (r3 == 0) goto L_0x0142
            com.google.android.gms.measurement.internal.zzam r3 = r12.zzf()
            java.lang.String r5 = r13.zza
            java.lang.String r6 = "_lair"
            com.google.android.gms.measurement.internal.zzom r3 = r3.zze(r5, r6)
            if (r3 != 0) goto L_0x0142
            com.google.android.gms.common.util.Clock r3 = r12.zzb()
            long r9 = r3.currentTimeMillis()
            com.google.android.gms.measurement.internal.zzom r3 = new com.google.android.gms.measurement.internal.zzom
            java.lang.String r6 = r13.zza
            r7 = 1
            java.lang.Long r11 = java.lang.Long.valueOf(r7)
            java.lang.String r7 = "auto"
            java.lang.String r8 = "_lair"
            r5 = r3
            r5.<init>(r6, r7, r8, r9, r11)
            com.google.android.gms.measurement.internal.zzam r5 = r12.zzf()
            r5.zza((com.google.android.gms.measurement.internal.zzom) r3)
            goto L_0x0142
        L_0x0110:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0078
            boolean r3 = r2.zzj()
            if (r3 == 0) goto L_0x0078
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzjc) r2)
            r0.zzb((java.lang.String) r2)
            goto L_0x0078
        L_0x0129:
            java.lang.String r3 = r0.zzad()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0078
            boolean r3 = r2.zzj()
            if (r3 == 0) goto L_0x0078
            java.lang.String r2 = r12.zza((com.google.android.gms.measurement.internal.zzjc) r2)
            r0.zzb((java.lang.String) r2)
            goto L_0x0078
        L_0x0142:
            java.lang.String r3 = r13.zzb
            r0.zzf((java.lang.String) r3)
            java.lang.String r3 = r13.zzp
            r0.zza((java.lang.String) r3)
            java.lang.String r3 = r13.zzk
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0159
            java.lang.String r3 = r13.zzk
            r0.zze((java.lang.String) r3)
        L_0x0159:
            long r5 = r13.zze
            r7 = 0
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0164
            r0.zzn(r5)
        L_0x0164:
            java.lang.String r3 = r13.zzc
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0171
            java.lang.String r3 = r13.zzc
            r0.zzd((java.lang.String) r3)
        L_0x0171:
            long r5 = r13.zzj
            r0.zzb((long) r5)
            java.lang.String r3 = r13.zzd
            if (r3 == 0) goto L_0x017d
            r0.zzc((java.lang.String) r3)
        L_0x017d:
            long r5 = r13.zzf
            r0.zzk((long) r5)
            boolean r3 = r13.zzh
            r0.zzb((boolean) r3)
            java.lang.String r3 = r13.zzg
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0194
            java.lang.String r3 = r13.zzg
            r0.zzg((java.lang.String) r3)
        L_0x0194:
            boolean r3 = r13.zzn
            r0.zza((boolean) r3)
            java.lang.Boolean r3 = r13.zzq
            r0.zza((java.lang.Boolean) r3)
            long r5 = r13.zzr
            r0.zzl(r5)
            java.lang.String r3 = r13.zzv
            r0.zzj((java.lang.String) r3)
            boolean r3 = com.google.android.gms.internal.measurement.zzpv.zza()
            if (r3 == 0) goto L_0x01c0
            com.google.android.gms.measurement.internal.zzah r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbj.zzbu
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01c0
            java.util.List<java.lang.String> r1 = r13.zzs
            r0.zza((java.util.List<java.lang.String>) r1)
            goto L_0x01d5
        L_0x01c0:
            boolean r3 = com.google.android.gms.internal.measurement.zzpv.zza()
            if (r3 == 0) goto L_0x01d5
            com.google.android.gms.measurement.internal.zzah r3 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbj.zzbt
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)
            if (r3 == 0) goto L_0x01d5
            r0.zza((java.util.List<java.lang.String>) r1)
        L_0x01d5:
            boolean r1 = com.google.android.gms.internal.measurement.zzrw.zza()
            if (r1 == 0) goto L_0x020a
            com.google.android.gms.measurement.internal.zzah r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzbw
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020a
            r12.zzq()
            java.lang.String r1 = r0.zzac()
            boolean r1 = com.google.android.gms.measurement.internal.zzop.zzf(r1)
            if (r1 == 0) goto L_0x020a
            boolean r1 = r13.zzw
            r0.zzc((boolean) r1)
            com.google.android.gms.measurement.internal.zzah r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzbx
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x020a
            java.lang.String r1 = r13.zzac
            r0.zzk((java.lang.String) r1)
        L_0x020a:
            boolean r1 = com.google.android.gms.internal.measurement.zzrl.zza()
            if (r1 == 0) goto L_0x0221
            com.google.android.gms.measurement.internal.zzah r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzcg
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x0221
            int r1 = r13.zzaa
            r0.zza((int) r1)
        L_0x0221:
            long r5 = r13.zzx
            r0.zzt(r5)
            boolean r1 = com.google.android.gms.internal.measurement.zzox.zza()
            if (r1 == 0) goto L_0x023d
            com.google.android.gms.measurement.internal.zzah r1 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzcw
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)
            if (r1 == 0) goto L_0x023d
            java.lang.String r13 = r13.zzad
            r0.zzi((java.lang.String) r13)
        L_0x023d:
            boolean r13 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r13 == 0) goto L_0x025f
            com.google.android.gms.measurement.internal.zzah r13 = r12.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)
            if (r13 == 0) goto L_0x025f
            boolean r13 = r0.zzas()
            if (r13 != 0) goto L_0x0257
            if (r2 == 0) goto L_0x026c
        L_0x0257:
            com.google.android.gms.measurement.internal.zzam r12 = r12.zzf()
            r12.zza((com.google.android.gms.measurement.internal.zzh) r0, (boolean) r2, (boolean) r4)
            goto L_0x026c
        L_0x025f:
            boolean r13 = r0.zzas()
            if (r13 == 0) goto L_0x026c
            com.google.android.gms.measurement.internal.zzam r12 = r12.zzf()
            r12.zza((com.google.android.gms.measurement.internal.zzh) r0, (boolean) r4, (boolean) r4)
        L_0x026c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(com.google.android.gms.measurement.internal.zzp):com.google.android.gms.measurement.internal.zzh");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x04f0: MOVE  (r5v18 java.lang.String) = (r27v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:172:0x0508 */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0319 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0398 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x03c6  */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0514 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x054f A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x05c4 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0610 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x061d A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x062a A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0637 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0645 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x0656 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x06ae A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x074a A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x075c A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:275:0x07a2 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x07fc A[SYNTHETIC, Splitter:B:291:0x07fc] */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0824 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x089e A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x08b7 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x0919 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:324:0x0941 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:328:0x095f A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x09d6 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x0a31 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01e1 A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0248  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x025a A[Catch:{ SQLiteException -> 0x02e3, all -> 0x01bb }] */
    @androidx.annotation.WorkerThread
    private final void zzc(com.google.android.gms.measurement.internal.zzbh r37, com.google.android.gms.measurement.internal.zzp r38) {
        /*
            r36 = this;
            r1 = r36
            r2 = r37
            r3 = r38
            java.lang.String r4 = "_fx"
            java.lang.String r5 = "_sno"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r38)
            java.lang.String r6 = r3.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)
            long r6 = java.lang.System.nanoTime()
            com.google.android.gms.measurement.internal.zzhp r8 = r36.zzl()
            r8.zzt()
            r36.zzs()
            java.lang.String r8 = r3.zza
            r36.zzp()
            boolean r9 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.measurement.internal.zzbh) r37, (com.google.android.gms.measurement.internal.zzp) r38)
            if (r9 != 0) goto L_0x002c
            return
        L_0x002c:
            boolean r9 = r3.zzh
            if (r9 != 0) goto L_0x0034
            r1.zza((com.google.android.gms.measurement.internal.zzp) r3)
            return
        L_0x0034:
            com.google.android.gms.measurement.internal.zzhg r9 = r36.zzi()
            java.lang.String r10 = r2.zza
            boolean r9 = r9.zzd(r8, r10)
            java.lang.String r15 = "_err"
            r14 = 0
            if (r9 == 0) goto L_0x00df
            com.google.android.gms.measurement.internal.zzgi r3 = r36.zzj()
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzu()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)
            com.google.android.gms.measurement.internal.zzhw r5 = r1.zzm
            com.google.android.gms.measurement.internal.zzgh r5 = r5.zzk()
            java.lang.String r6 = r2.zza
            java.lang.String r5 = r5.zza((java.lang.String) r6)
            java.lang.String r6 = "Dropping blocked event. appId"
            r3.zza(r6, r4, r5)
            com.google.android.gms.measurement.internal.zzhg r3 = r36.zzi()
            boolean r3 = r3.zzm(r8)
            if (r3 != 0) goto L_0x0077
            com.google.android.gms.measurement.internal.zzhg r3 = r36.zzi()
            boolean r3 = r3.zzo(r8)
            if (r3 == 0) goto L_0x0075
            goto L_0x0077
        L_0x0075:
            r3 = 0
            goto L_0x0078
        L_0x0077:
            r3 = 1
        L_0x0078:
            if (r3 != 0) goto L_0x0095
            java.lang.String r4 = r2.zza
            boolean r4 = r15.equals(r4)
            if (r4 != 0) goto L_0x0095
            r36.zzq()
            com.google.android.gms.measurement.internal.zzoo r9 = r1.zzah
            java.lang.String r13 = r2.zza
            r2 = 0
            r11 = 11
            java.lang.String r12 = "_ev"
            r10 = r8
            r4 = r14
            r14 = r2
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzoo) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)
            goto L_0x0096
        L_0x0095:
            r4 = r14
        L_0x0096:
            if (r3 == 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()
            com.google.android.gms.measurement.internal.zzh r2 = r2.zze(r8)
            if (r2 == 0) goto L_0x00de
            long r5 = r2.zzp()
            long r7 = r2.zzg()
            long r5 = java.lang.Math.max(r5, r7)
            com.google.android.gms.common.util.Clock r3 = r36.zzb()
            long r7 = r3.currentTimeMillis()
            long r7 = r7 - r5
            long r5 = java.lang.Math.abs(r7)
            r36.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r3 = com.google.android.gms.measurement.internal.zzbj.zzaa
            java.lang.Object r3 = r3.zza(r4)
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            int r3 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x00de
            com.google.android.gms.measurement.internal.zzgi r3 = r36.zzj()
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzc()
            java.lang.String r4 = "Fetching config for blocked app"
            r3.zza(r4)
            r1.zzb((com.google.android.gms.measurement.internal.zzh) r2)
        L_0x00de:
            return
        L_0x00df:
            com.google.android.gms.measurement.internal.zzgm r2 = com.google.android.gms.measurement.internal.zzgm.zza(r37)
            com.google.android.gms.measurement.internal.zzop r9 = r36.zzq()
            com.google.android.gms.measurement.internal.zzah r10 = r36.zze()
            int r10 = r10.zzb(r8)
            r9.zza((com.google.android.gms.measurement.internal.zzgm) r2, (int) r10)
            boolean r9 = com.google.android.gms.internal.measurement.zzqz.zza()
            if (r9 == 0) goto L_0x0113
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzcf
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)
            if (r9 == 0) goto L_0x0113
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r10 = com.google.android.gms.measurement.internal.zzbj.zzas
            r11 = 10
            r12 = 35
            int r9 = r9.zza(r8, r10, r11, r12)
            goto L_0x0114
        L_0x0113:
            r9 = 0
        L_0x0114:
            java.util.TreeSet r10 = new java.util.TreeSet
            android.os.Bundle r11 = r2.zzd
            java.util.Set r11 = r11.keySet()
            r10.<init>(r11)
            java.util.Iterator r10 = r10.iterator()
        L_0x0123:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x015b
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.String r12 = "items"
            boolean r12 = r12.equals(r11)
            if (r12 == 0) goto L_0x0159
            com.google.android.gms.measurement.internal.zzop r12 = r36.zzq()
            android.os.Bundle r13 = r2.zzd
            android.os.Parcelable[] r11 = r13.getParcelableArray(r11)
            boolean r13 = com.google.android.gms.internal.measurement.zzqz.zza()
            if (r13 == 0) goto L_0x0155
            com.google.android.gms.measurement.internal.zzah r13 = r36.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r14 = com.google.android.gms.measurement.internal.zzbj.zzcf
            boolean r13 = r13.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r14)
            if (r13 == 0) goto L_0x0155
            r13 = 1
            goto L_0x0156
        L_0x0155:
            r13 = 0
        L_0x0156:
            r12.zza((android.os.Parcelable[]) r11, (int) r9, (boolean) r13)
        L_0x0159:
            r14 = 0
            goto L_0x0123
        L_0x015b:
            com.google.android.gms.measurement.internal.zzbh r2 = r2.zza()
            com.google.android.gms.measurement.internal.zzgi r9 = r36.zzj()
            r10 = 2
            boolean r9 = r9.zza((int) r10)
            if (r9 == 0) goto L_0x0181
            com.google.android.gms.measurement.internal.zzgi r9 = r36.zzj()
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzp()
            com.google.android.gms.measurement.internal.zzhw r10 = r1.zzm
            com.google.android.gms.measurement.internal.zzgh r10 = r10.zzk()
            java.lang.String r10 = r10.zza((com.google.android.gms.measurement.internal.zzbh) r2)
            java.lang.String r11 = "Logging event"
            r9.zza(r11, r10)
        L_0x0181:
            boolean r9 = com.google.android.gms.internal.measurement.zzqs.zza()
            if (r9 == 0) goto L_0x0190
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzcb
            r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)
        L_0x0190:
            com.google.android.gms.measurement.internal.zzam r9 = r36.zzf()
            r9.zzp()
            r1.zza((com.google.android.gms.measurement.internal.zzp) r3)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = "ecommerce_purchase"
            java.lang.String r10 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x01bb }
            java.lang.String r10 = "refund"
            if (r9 != 0) goto L_0x01bf
            java.lang.String r9 = "purchase"
            java.lang.String r11 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.equals(r11)     // Catch:{ all -> 0x01bb }
            if (r9 != 0) goto L_0x01bf
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = r10.equals(r9)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x01b9
            goto L_0x01bf
        L_0x01b9:
            r9 = 0
            goto L_0x01c0
        L_0x01bb:
            r0 = move-exception
            r2 = r0
            goto L_0x0a7c
        L_0x01bf:
            r9 = 1
        L_0x01c0:
            java.lang.String r11 = "_iap"
            java.lang.String r12 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x01bb }
            java.lang.String r13 = "value"
            if (r11 != 0) goto L_0x01d7
            if (r9 == 0) goto L_0x01cf
            goto L_0x01d7
        L_0x01cf:
            r24 = r6
            r27 = r13
            r7 = r15
        L_0x01d4:
            r6 = 1
            goto L_0x034f
        L_0x01d7:
            com.google.android.gms.measurement.internal.zzbc r11 = r2.zzb     // Catch:{ all -> 0x01bb }
            java.lang.String r12 = "currency"
            java.lang.String r11 = r11.zzd(r12)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0248
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x01bb }
            java.lang.Double r9 = r9.zza((java.lang.String) r13)     // Catch:{ all -> 0x01bb }
            double r19 = r9.doubleValue()     // Catch:{ all -> 0x01bb }
            r21 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            double r19 = r19 * r21
            r23 = 0
            int r9 = (r19 > r23 ? 1 : (r19 == r23 ? 0 : -1))
            if (r9 != 0) goto L_0x0208
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x01bb }
            java.lang.Long r9 = r9.zzb(r13)     // Catch:{ all -> 0x01bb }
            r23 = r15
            long r14 = r9.longValue()     // Catch:{ all -> 0x01bb }
            double r14 = (double) r14     // Catch:{ all -> 0x01bb }
            double r19 = r14 * r21
            goto L_0x020a
        L_0x0208:
            r23 = r15
        L_0x020a:
            r14 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r9 = (r19 > r14 ? 1 : (r19 == r14 ? 0 : -1))
            if (r9 > 0) goto L_0x0224
            r14 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r9 = (r19 > r14 ? 1 : (r19 == r14 ? 0 : -1))
            if (r9 < 0) goto L_0x0224
            long r14 = java.lang.Math.round(r19)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = r10.equals(r9)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0254
            long r14 = -r14
            goto L_0x0254
        L_0x0224:
            com.google.android.gms.measurement.internal.zzgi r2 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzu()     // Catch:{ all -> 0x01bb }
            java.lang.String r3 = "Data lost. Currency value is too big. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            java.lang.Double r5 = java.lang.Double.valueOf(r19)     // Catch:{ all -> 0x01bb }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r2.zzw()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            return
        L_0x0248:
            r23 = r15
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x01bb }
            java.lang.Long r9 = r9.zzb(r13)     // Catch:{ all -> 0x01bb }
            long r14 = r9.longValue()     // Catch:{ all -> 0x01bb }
        L_0x0254:
            boolean r9 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x01bb }
            if (r9 != 0) goto L_0x0347
            java.util.Locale r9 = java.util.Locale.US     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r11.toUpperCase(r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r10 = "[A-Z]{3}"
            boolean r10 = r9.matches(r10)     // Catch:{ all -> 0x01bb }
            if (r10 == 0) goto L_0x0347
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = "_ltv_"
            r10.<init>(r11)     // Catch:{ all -> 0x01bb }
            r10.append(r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r12 = r10.toString()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r9 = r36.zzf()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r9 = r9.zze(r8, r12)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0286
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x01bb }
            boolean r10 = r9 instanceof java.lang.Long     // Catch:{ all -> 0x01bb }
            if (r10 != 0) goto L_0x028e
        L_0x0286:
            r24 = r6
            r27 = r13
            r7 = r23
            r6 = 1
            goto L_0x02b9
        L_0x028e:
            java.lang.Long r9 = (java.lang.Long) r9     // Catch:{ all -> 0x01bb }
            long r9 = r9.longValue()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r19 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.util.Clock r20 = r36.zzb()     // Catch:{ all -> 0x01bb }
            long r20 = r20.currentTimeMillis()     // Catch:{ all -> 0x01bb }
            long r9 = r9 + r14
            java.lang.Long r15 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01bb }
            r9 = r19
            r10 = r8
            r14 = 0
            r24 = r6
            r27 = r13
            r6 = 1
            r7 = 0
            r13 = r20
            r7 = r23
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x01bb }
        L_0x02b6:
            r9 = r19
            goto L_0x030f
        L_0x02b9:
            com.google.android.gms.measurement.internal.zzam r9 = r36.zzf()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r10 = r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r11 = com.google.android.gms.measurement.internal.zzbj.zzag     // Catch:{ all -> 0x01bb }
            int r10 = r10.zzb((java.lang.String) r8, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r11)     // Catch:{ all -> 0x01bb }
            int r10 = r10 - r6
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x01bb }
            r9.zzt()     // Catch:{ all -> 0x01bb }
            r9.zzal()     // Catch:{ all -> 0x01bb }
            android.database.sqlite.SQLiteDatabase r11 = r9.e_()     // Catch:{ SQLiteException -> 0x02e3 }
            java.lang.String r13 = "delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);"
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x02e3 }
            java.lang.String[] r10 = new java.lang.String[]{r8, r8, r10}     // Catch:{ SQLiteException -> 0x02e3 }
            r11.execSQL(r13, r10)     // Catch:{ SQLiteException -> 0x02e3 }
            goto L_0x02f6
        L_0x02e3:
            r0 = move-exception
            r10 = r0
            com.google.android.gms.measurement.internal.zzgi r9 = r9.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = "Error pruning currencies. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            r9.zza(r11, r13, r10)     // Catch:{ all -> 0x01bb }
        L_0x02f6:
            com.google.android.gms.measurement.internal.zzom r19 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.util.Clock r9 = r36.zzb()     // Catch:{ all -> 0x01bb }
            long r16 = r9.currentTimeMillis()     // Catch:{ all -> 0x01bb }
            java.lang.Long r15 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x01bb }
            r9 = r19
            r10 = r8
            r13 = r16
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x01bb }
            goto L_0x02b6
        L_0x030f:
            com.google.android.gms.measurement.internal.zzam r10 = r36.zzf()     // Catch:{ all -> 0x01bb }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzom) r9)     // Catch:{ all -> 0x01bb }
            if (r10 != 0) goto L_0x034f
            com.google.android.gms.measurement.internal.zzgi r10 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r10 = r10.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = "Too many unique user properties are set. Ignoring user property. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r13 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgh r13 = r13.zzk()     // Catch:{ all -> 0x01bb }
            java.lang.String r14 = r9.zzc     // Catch:{ all -> 0x01bb }
            java.lang.String r13 = r13.zzc(r14)     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x01bb }
            r10.zza(r11, r12, r13, r9)     // Catch:{ all -> 0x01bb }
            r36.zzq()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzoo r9 = r1.zzah     // Catch:{ all -> 0x01bb }
            r13 = 0
            r14 = 0
            r11 = 9
            r12 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzoo) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x01bb }
            goto L_0x034f
        L_0x0347:
            r24 = r6
            r27 = r13
            r7 = r23
            goto L_0x01d4
        L_0x034f:
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r21 = com.google.android.gms.measurement.internal.zzop.zzh(r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x01bb }
            r36.zzq()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbc r9 = r2.zzb     // Catch:{ all -> 0x01bb }
            long r9 = com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzbc) r9)     // Catch:{ all -> 0x01bb }
            r13 = 1
            long r15 = r9 + r13
            com.google.android.gms.measurement.internal.zzam r9 = r36.zzf()     // Catch:{ all -> 0x01bb }
            long r10 = r36.zzx()     // Catch:{ all -> 0x01bb }
            r19 = 0
            r20 = 0
            r17 = 1
            r18 = 0
            r12 = r8
            r13 = r15
            r15 = r17
            r16 = r21
            r17 = r18
            r18 = r7
            com.google.android.gms.measurement.internal.zzar r9 = r9.zza(r10, r12, r13, r15, r16, r17, r18, r19, r20)     // Catch:{ all -> 0x01bb }
            long r10 = r9.zzb     // Catch:{ all -> 0x01bb }
            r36.zze()     // Catch:{ all -> 0x01bb }
            long r12 = com.google.android.gms.measurement.internal.zzah.zzn()     // Catch:{ all -> 0x01bb }
            long r10 = r10 - r12
            r14 = 0
            int r12 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            r16 = 1000(0x3e8, double:4.94E-321)
            if (r12 <= 0) goto L_0x03c6
            long r10 = r10 % r16
            r12 = 1
            int r2 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x03b7
            com.google.android.gms.measurement.internal.zzgi r2 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r3 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            long r5 = r9.zzb     // Catch:{ all -> 0x01bb }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x01bb }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x01bb }
        L_0x03b7:
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r2.zzw()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            return
        L_0x03c6:
            r12 = 1
            if (r21 == 0) goto L_0x0420
            long r10 = r9.zza     // Catch:{ all -> 0x01bb }
            r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r6 = com.google.android.gms.measurement.internal.zzbj.zzm     // Catch:{ all -> 0x01bb }
            r12 = 0
            java.lang.Object r6 = r6.zza(r12)     // Catch:{ all -> 0x01bb }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x01bb }
            int r6 = r6.intValue()     // Catch:{ all -> 0x01bb }
            long r12 = (long) r6     // Catch:{ all -> 0x01bb }
            long r10 = r10 - r12
            int r6 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r6 <= 0) goto L_0x0420
            long r10 = r10 % r16
            r3 = 1
            int r3 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0401
            com.google.android.gms.measurement.internal.zzgi r3 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r4 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            long r6 = r9.zza     // Catch:{ all -> 0x01bb }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x01bb }
            r3.zza(r4, r5, r6)     // Catch:{ all -> 0x01bb }
        L_0x0401:
            r36.zzq()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzoo r9 = r1.zzah     // Catch:{ all -> 0x01bb }
            java.lang.String r12 = "_ev"
            java.lang.String r13 = r2.zza     // Catch:{ all -> 0x01bb }
            r14 = 0
            r11 = 16
            r10 = r8
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzoo) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r2.zzw()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            return
        L_0x0420:
            if (r7 == 0) goto L_0x046e
            long r6 = r9.zzd     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r10 = r36.zze()     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = r3.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r12 = com.google.android.gms.measurement.internal.zzbj.zzl     // Catch:{ all -> 0x01bb }
            int r10 = r10.zzb((java.lang.String) r11, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r12)     // Catch:{ all -> 0x01bb }
            r11 = 1000000(0xf4240, float:1.401298E-39)
            int r10 = java.lang.Math.min(r11, r10)     // Catch:{ all -> 0x01bb }
            r13 = 0
            int r10 = java.lang.Math.max(r13, r10)     // Catch:{ all -> 0x01bb }
            long r10 = (long) r10     // Catch:{ all -> 0x01bb }
            long r6 = r6 - r10
            int r10 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x046f
            r10 = 1
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 != 0) goto L_0x045f
            com.google.android.gms.measurement.internal.zzgi r2 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r3 = "Too many error events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            long r5 = r9.zzd     // Catch:{ all -> 0x01bb }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x01bb }
            r2.zza(r3, r4, r5)     // Catch:{ all -> 0x01bb }
        L_0x045f:
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r2.zzw()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            return
        L_0x046e:
            r13 = 0
        L_0x046f:
            com.google.android.gms.measurement.internal.zzbc r6 = r2.zzb     // Catch:{ all -> 0x01bb }
            android.os.Bundle r6 = r6.zzb()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzop r7 = r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = "_o"
            java.lang.String r10 = r2.zzc     // Catch:{ all -> 0x01bb }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzop r7 = r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zzac     // Catch:{ all -> 0x01bb }
            boolean r7 = r7.zzd(r8, r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r12 = "_r"
            if (r7 == 0) goto L_0x04a9
            com.google.android.gms.measurement.internal.zzop r7 = r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = "_dbg"
            r16 = 1
            java.lang.Long r10 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x01bb }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzop r7 = r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.Long r9 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x01bb }
            r7.zza((android.os.Bundle) r6, (java.lang.String) r12, (java.lang.Object) r9)     // Catch:{ all -> 0x01bb }
            goto L_0x04ab
        L_0x04a9:
            r16 = 1
        L_0x04ab:
            java.lang.String r7 = "_s"
            java.lang.String r9 = r2.zza     // Catch:{ all -> 0x01bb }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x04d0
            com.google.android.gms.measurement.internal.zzam r7 = r36.zzf()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r7 = r7.zze(r9, r5)     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x04d0
            java.lang.Object r9 = r7.zze     // Catch:{ all -> 0x01bb }
            boolean r9 = r9 instanceof java.lang.Long     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x04d0
            com.google.android.gms.measurement.internal.zzop r9 = r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.Object r7 = r7.zze     // Catch:{ all -> 0x01bb }
            r9.zza((android.os.Bundle) r6, (java.lang.String) r5, (java.lang.Object) r7)     // Catch:{ all -> 0x01bb }
        L_0x04d0:
            com.google.android.gms.measurement.internal.zzah r5 = r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbj.zzdn     // Catch:{ all -> 0x01bb }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x01bb }
            if (r5 == 0) goto L_0x0508
            java.lang.String r5 = r2.zzc     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = "am"
            boolean r5 = java.util.Objects.equals(r5, r7)     // Catch:{ all -> 0x01bb }
            if (r5 == 0) goto L_0x0508
            java.lang.String r5 = r2.zza     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = "_ai"
            boolean r5 = java.util.Objects.equals(r5, r7)     // Catch:{ all -> 0x01bb }
            if (r5 == 0) goto L_0x0508
            r5 = r27
            java.lang.Object r7 = r6.get(r5)     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x0508
            boolean r9 = r7 instanceof java.lang.String     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0508
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NumberFormatException -> 0x0508 }
            double r9 = java.lang.Double.parseDouble(r7)     // Catch:{ NumberFormatException -> 0x0508 }
            r6.remove(r5)     // Catch:{ NumberFormatException -> 0x0508 }
            r6.putDouble(r5, r9)     // Catch:{ NumberFormatException -> 0x0508 }
        L_0x0508:
            com.google.android.gms.measurement.internal.zzam r5 = r36.zzf()     // Catch:{ all -> 0x01bb }
            long r9 = r5.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            int r5 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r5 <= 0) goto L_0x0529
            com.google.android.gms.measurement.internal.zzgi r5 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r5 = r5.zzu()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x01bb }
            r5.zza(r7, r11, r9)     // Catch:{ all -> 0x01bb }
        L_0x0529:
            com.google.android.gms.measurement.internal.zzba r5 = new com.google.android.gms.measurement.internal.zzba     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r10 = r1.zzm     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = r2.zzc     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r2.zza     // Catch:{ all -> 0x01bb }
            long r14 = r2.zzd     // Catch:{ all -> 0x01bb }
            r22 = 0
            r9 = r5
            r2 = r12
            r12 = r8
            r26 = r2
            r2 = r13
            r13 = r7
            r16 = r22
            r18 = r6
            r9.<init>((com.google.android.gms.measurement.internal.zzhw) r10, (java.lang.String) r11, (java.lang.String) r12, (java.lang.String) r13, (long) r14, (long) r16, (android.os.Bundle) r18)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r6 = r36.zzf()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r5.zzb     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbd r6 = r6.zzd(r8, r7)     // Catch:{ all -> 0x01bb }
            if (r6 != 0) goto L_0x05c4
            com.google.android.gms.measurement.internal.zzam r6 = r36.zzf()     // Catch:{ all -> 0x01bb }
            long r6 = r6.zzc(r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()     // Catch:{ all -> 0x01bb }
            int r9 = r9.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            long r9 = (long) r9     // Catch:{ all -> 0x01bb }
            int r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1))
            if (r6 < 0) goto L_0x05a5
            if (r21 == 0) goto L_0x05a5
            com.google.android.gms.measurement.internal.zzgi r2 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r3 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r6 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x01bb }
            java.lang.String r5 = r5.zzb     // Catch:{ all -> 0x01bb }
            java.lang.String r5 = r6.zza((java.lang.String) r5)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r6 = r36.zze()     // Catch:{ all -> 0x01bb }
            int r6 = r6.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x01bb }
            r2.zza(r3, r4, r5, r6)     // Catch:{ all -> 0x01bb }
            r36.zzq()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzoo r9 = r1.zzah     // Catch:{ all -> 0x01bb }
            r13 = 0
            r14 = 0
            r11 = 8
            r12 = 0
            r10 = r8
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzoo) r9, (java.lang.String) r10, (int) r11, (java.lang.String) r12, (java.lang.String) r13, (int) r14)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            return
        L_0x05a5:
            com.google.android.gms.measurement.internal.zzbd r6 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = r5.zzb     // Catch:{ all -> 0x01bb }
            long r14 = r5.zzd     // Catch:{ all -> 0x01bb }
            r22 = 0
            r23 = 0
            r12 = 0
            r16 = 0
            r18 = 0
            r20 = 0
            r21 = 0
            r9 = r6
            r10 = r8
            r7 = r14
            r14 = r16
            r16 = r7
            r9.<init>(r10, r11, r12, r14, r16, r18, r20, r21, r22, r23)     // Catch:{ all -> 0x01bb }
            goto L_0x05d2
        L_0x05c4:
            com.google.android.gms.measurement.internal.zzhw r7 = r1.zzm     // Catch:{ all -> 0x01bb }
            long r8 = r6.zzf     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzba r5 = r5.zza(r7, r8)     // Catch:{ all -> 0x01bb }
            long r7 = r5.zzd     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbd r6 = r6.zza(r7)     // Catch:{ all -> 0x01bb }
        L_0x05d2:
            com.google.android.gms.measurement.internal.zzam r7 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r7.zza((com.google.android.gms.measurement.internal.zzbd) r6)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhp r6 = r36.zzl()     // Catch:{ all -> 0x01bb }
            r6.zzt()     // Catch:{ all -> 0x01bb }
            r36.zzs()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r38)     // Catch:{ all -> 0x01bb }
            java.lang.String r6 = r5.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r6)     // Catch:{ all -> 0x01bb }
            java.lang.String r6 = r5.zza     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x01bb }
            boolean r6 = r6.equals(r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r6)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r6 = com.google.android.gms.internal.measurement.zzgn.zzk.zzw()     // Catch:{ all -> 0x01bb }
            r7 = 1
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r6 = r6.zzh((int) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = "android"
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r6 = r6.zzp(r8)     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x0615
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            r6.zzb((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
        L_0x0615:
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x01bb }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x0622
            java.lang.String r8 = r3.zzd     // Catch:{ all -> 0x01bb }
            r6.zzd((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
        L_0x0622:
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x01bb }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x062f
            java.lang.String r8 = r3.zzc     // Catch:{ all -> 0x01bb }
            r6.zze((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
        L_0x062f:
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x01bb }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x063c
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x01bb }
            r6.zzr(r8)     // Catch:{ all -> 0x01bb }
        L_0x063c:
            long r8 = r3.zzj     // Catch:{ all -> 0x01bb }
            r10 = -2147483648(0xffffffff80000000, double:NaN)
            int r10 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r10 == 0) goto L_0x0649
            int r8 = (int) r8     // Catch:{ all -> 0x01bb }
            r6.zze((int) r8)     // Catch:{ all -> 0x01bb }
        L_0x0649:
            long r8 = r3.zze     // Catch:{ all -> 0x01bb }
            r6.zzf((long) r8)     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x01bb }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x065b
            java.lang.String r8 = r3.zzb     // Catch:{ all -> 0x01bb }
            r6.zzm(r8)     // Catch:{ all -> 0x01bb }
        L_0x065b:
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            java.lang.Object r8 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r8 = r1.zzb((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zzt     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r9 = com.google.android.gms.measurement.internal.zzjc.zzb((java.lang.String) r9)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r8 = r8.zza((com.google.android.gms.measurement.internal.zzjc) r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r8.zzg()     // Catch:{ all -> 0x01bb }
            r6.zzg((java.lang.String) r9)     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r6.zzx()     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.isEmpty()     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x068f
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x01bb }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x01bb }
            if (r9 != 0) goto L_0x068f
            java.lang.String r9 = r3.zzp     // Catch:{ all -> 0x01bb }
            r6.zza((java.lang.String) r9)     // Catch:{ all -> 0x01bb }
        L_0x068f:
            boolean r9 = com.google.android.gms.internal.measurement.zzrl.zza()     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0742
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()     // Catch:{ all -> 0x01bb }
            java.lang.String r10 = r3.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbj.zzcg     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.zze(r10, r11)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0742
            r36.zzq()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = com.google.android.gms.measurement.internal.zzop.zzd(r9)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0742
            int r9 = r3.zzaa     // Catch:{ all -> 0x01bb }
            r6.zzd((int) r9)     // Catch:{ all -> 0x01bb }
            long r9 = r3.zzab     // Catch:{ all -> 0x01bb }
            boolean r8 = r8.zzi()     // Catch:{ all -> 0x01bb }
            r11 = 32
            r13 = 0
            if (r8 != 0) goto L_0x06c9
            int r8 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x06c9
            r15 = -2
            long r8 = r9 & r15
            long r9 = r8 | r11
        L_0x06c9:
            r7 = 1
            int r15 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r15 != 0) goto L_0x06d1
            r15 = 1
            goto L_0x06d2
        L_0x06d1:
            r15 = r2
        L_0x06d2:
            r6.zza((boolean) r15)     // Catch:{ all -> 0x01bb }
            int r15 = (r9 > r13 ? 1 : (r9 == r13 ? 0 : -1))
            if (r15 == 0) goto L_0x0744
            com.google.android.gms.internal.measurement.zzgn$zzc$zza r15 = com.google.android.gms.internal.measurement.zzgn.zzc.zza()     // Catch:{ all -> 0x01bb }
            long r18 = r9 & r7
            int r16 = (r18 > r13 ? 1 : (r18 == r13 ? 0 : -1))
            if (r16 == 0) goto L_0x06e5
            r7 = 1
            goto L_0x06e6
        L_0x06e5:
            r7 = r2
        L_0x06e6:
            r15.zzc(r7)     // Catch:{ all -> 0x01bb }
            r7 = 2
            long r7 = r7 & r9
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x06f2
            r7 = 1
            goto L_0x06f3
        L_0x06f2:
            r7 = r2
        L_0x06f3:
            r15.zze(r7)     // Catch:{ all -> 0x01bb }
            r7 = 4
            long r7 = r7 & r9
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x06ff
            r7 = 1
            goto L_0x0700
        L_0x06ff:
            r7 = r2
        L_0x0700:
            r15.zzf(r7)     // Catch:{ all -> 0x01bb }
            r7 = 8
            long r7 = r7 & r9
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x070c
            r7 = 1
            goto L_0x070d
        L_0x070c:
            r7 = r2
        L_0x070d:
            r15.zzg(r7)     // Catch:{ all -> 0x01bb }
            r7 = 16
            long r7 = r7 & r9
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x0719
            r7 = 1
            goto L_0x071a
        L_0x0719:
            r7 = r2
        L_0x071a:
            r15.zzb(r7)     // Catch:{ all -> 0x01bb }
            long r7 = r9 & r11
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x0725
            r7 = 1
            goto L_0x0726
        L_0x0725:
            r7 = r2
        L_0x0726:
            r15.zza(r7)     // Catch:{ all -> 0x01bb }
            r7 = 64
            long r7 = r7 & r9
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x0732
            r7 = 1
            goto L_0x0733
        L_0x0732:
            r7 = r2
        L_0x0733:
            r15.zzd(r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzml r7 = r15.zzai()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzlc r7 = (com.google.android.gms.internal.measurement.zzlc) r7     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzc r7 = (com.google.android.gms.internal.measurement.zzgn.zzc) r7     // Catch:{ all -> 0x01bb }
            r6.zza((com.google.android.gms.internal.measurement.zzgn.zzc) r7)     // Catch:{ all -> 0x01bb }
            goto L_0x0744
        L_0x0742:
            r13 = 0
        L_0x0744:
            long r7 = r3.zzf     // Catch:{ all -> 0x01bb }
            int r9 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r9 == 0) goto L_0x074d
            r6.zzc((long) r7)     // Catch:{ all -> 0x01bb }
        L_0x074d:
            long r7 = r3.zzr     // Catch:{ all -> 0x01bb }
            r6.zzd((long) r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzol r7 = r36.zzp()     // Catch:{ all -> 0x01bb }
            java.util.List r7 = r7.zzu()     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x075f
            r6.zzc((java.lang.Iterable<? extends java.lang.Integer>) r7)     // Catch:{ all -> 0x01bb }
        L_0x075f:
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x01bb }
            java.lang.Object r7 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r7 = r1.zzb((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zzt     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r8 = com.google.android.gms.measurement.internal.zzjc.zzb((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zza((com.google.android.gms.measurement.internal.zzjc) r8)     // Catch:{ all -> 0x01bb }
            boolean r8 = r7.zzi()     // Catch:{ all -> 0x01bb }
            if (r8 == 0) goto L_0x0854
            boolean r8 = r3.zzn     // Catch:{ all -> 0x01bb }
            if (r8 == 0) goto L_0x0854
            com.google.android.gms.measurement.internal.zzms r8 = r1.zzj     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x01bb }
            android.util.Pair r8 = r8.zza((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzjc) r7)     // Catch:{ all -> 0x01bb }
            if (r8 == 0) goto L_0x0854
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x01bb }
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ all -> 0x01bb }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x01bb }
            if (r9 != 0) goto L_0x0854
            boolean r9 = r3.zzn     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0854
            java.lang.Object r9 = r8.first     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01bb }
            r6.zzq(r9)     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r8.second     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x07ab
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.booleanValue()     // Catch:{ all -> 0x01bb }
            r6.zzc((boolean) r9)     // Catch:{ all -> 0x01bb }
        L_0x07ab:
            boolean r9 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0854
            com.google.android.gms.measurement.internal.zzah r9 = r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r10 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r10)     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0854
            java.lang.String r9 = r5.zzb     // Catch:{ all -> 0x01bb }
            boolean r9 = r9.equals(r4)     // Catch:{ all -> 0x01bb }
            if (r9 != 0) goto L_0x0854
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = "00000000-0000-0000-0000-000000000000"
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x01bb }
            if (r8 != 0) goto L_0x0854
            com.google.android.gms.measurement.internal.zzam r8 = r36.zzf()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzh r8 = r8.zze(r9)     // Catch:{ all -> 0x01bb }
            if (r8 == 0) goto L_0x0854
            boolean r9 = r8.zzau()     // Catch:{ all -> 0x01bb }
            if (r9 == 0) goto L_0x0854
            java.lang.String r9 = r3.zza     // Catch:{ all -> 0x01bb }
            r10 = 0
            r1.zza((java.lang.String) r9, (boolean) r2, (java.lang.Long) r10, (java.lang.Long) r10)     // Catch:{ all -> 0x01bb }
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ all -> 0x01bb }
            r9.<init>()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r10 = r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r11 = com.google.android.gms.measurement.internal.zzbj.zzcz     // Catch:{ all -> 0x01bb }
            boolean r10 = r10.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r11)     // Catch:{ all -> 0x01bb }
            java.lang.String r11 = "_pfo"
            if (r10 == 0) goto L_0x0824
            java.lang.Long r10 = r8.zzy()     // Catch:{ all -> 0x01bb }
            if (r10 == 0) goto L_0x080d
            long r2 = r10.longValue()     // Catch:{ all -> 0x01bb }
            long r2 = java.lang.Math.max(r13, r2)     // Catch:{ all -> 0x01bb }
            r9.putLong(r11, r2)     // Catch:{ all -> 0x01bb }
        L_0x080d:
            java.lang.Long r2 = r8.zzz()     // Catch:{ all -> 0x01bb }
            if (r2 == 0) goto L_0x081c
            java.lang.String r3 = "_uwa"
            long r10 = r2.longValue()     // Catch:{ all -> 0x01bb }
            r9.putLong(r3, r10)     // Catch:{ all -> 0x01bb }
        L_0x081c:
            r3 = r38
            r2 = r7
        L_0x081f:
            r10 = r26
            r7 = 1
            goto L_0x0849
        L_0x0824:
            com.google.android.gms.measurement.internal.zzah r2 = r36.zze()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbj.zzcy     // Catch:{ all -> 0x01bb }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r3)     // Catch:{ all -> 0x01bb }
            if (r2 == 0) goto L_0x081c
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r3 = r38
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            long r18 = r2.zzb(r8)     // Catch:{ all -> 0x01bb }
            r2 = r7
            r20 = 1
            long r7 = r18 - r20
            long r7 = java.lang.Math.max(r13, r7)     // Catch:{ all -> 0x01bb }
            r9.putLong(r11, r7)     // Catch:{ all -> 0x01bb }
            goto L_0x081f
        L_0x0849:
            r9.putLong(r10, r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzoo r7 = r1.zzah     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            r7.zza(r8, r4, r9)     // Catch:{ all -> 0x01bb }
            goto L_0x0857
        L_0x0854:
            r2 = r7
            r10 = r26
        L_0x0857:
            com.google.android.gms.measurement.internal.zzhw r4 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbb r4 = r4.zzg()     // Catch:{ all -> 0x01bb }
            r4.zzac()     // Catch:{ all -> 0x01bb }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r4 = r6.zzi((java.lang.String) r4)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r7 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbb r7 = r7.zzg()     // Catch:{ all -> 0x01bb }
            r7.zzac()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r4 = r4.zzo(r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r7 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbb r7 = r7.zzg()     // Catch:{ all -> 0x01bb }
            long r7 = r7.zzg()     // Catch:{ all -> 0x01bb }
            int r7 = (int) r7     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r4 = r4.zzj((int) r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r7 = r1.zzm     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbb r7 = r7.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r7.zzh()     // Catch:{ all -> 0x01bb }
            r4.zzs(r7)     // Catch:{ all -> 0x01bb }
            long r7 = r3.zzx     // Catch:{ all -> 0x01bb }
            r6.zzj((long) r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r4 = r1.zzm     // Catch:{ all -> 0x01bb }
            boolean r4 = r4.zzac()     // Catch:{ all -> 0x01bb }
            if (r4 == 0) goto L_0x08ab
            r6.zzt()     // Catch:{ all -> 0x01bb }
            r4 = 0
            boolean r7 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x01bb }
            if (r7 != 0) goto L_0x08ab
            r6.zzj((java.lang.String) r4)     // Catch:{ all -> 0x01bb }
        L_0x08ab:
            com.google.android.gms.measurement.internal.zzam r4 = r36.zzf()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzh r4 = r4.zze(r7)     // Catch:{ all -> 0x01bb }
            if (r4 != 0) goto L_0x0919
            com.google.android.gms.measurement.internal.zzh r4 = new com.google.android.gms.measurement.internal.zzh     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhw r7 = r1.zzm     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            r4.<init>(r7, r8)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r1.zza((com.google.android.gms.measurement.internal.zzjc) r2)     // Catch:{ all -> 0x01bb }
            r4.zzb((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zzk     // Catch:{ all -> 0x01bb }
            r4.zze((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zzb     // Catch:{ all -> 0x01bb }
            r4.zzf((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            boolean r7 = r2.zzi()     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x08e4
            com.google.android.gms.measurement.internal.zzms r7 = r1.zzj     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zza     // Catch:{ all -> 0x01bb }
            boolean r9 = r3.zzn     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r7.zza((java.lang.String) r8, (boolean) r9)     // Catch:{ all -> 0x01bb }
            r4.zzh((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
        L_0x08e4:
            r4.zzq(r13)     // Catch:{ all -> 0x01bb }
            r4.zzr(r13)     // Catch:{ all -> 0x01bb }
            r4.zzp(r13)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zzc     // Catch:{ all -> 0x01bb }
            r4.zzd((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            long r7 = r3.zzj     // Catch:{ all -> 0x01bb }
            r4.zzb((long) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zzd     // Catch:{ all -> 0x01bb }
            r4.zzc((java.lang.String) r7)     // Catch:{ all -> 0x01bb }
            long r7 = r3.zze     // Catch:{ all -> 0x01bb }
            r4.zzn(r7)     // Catch:{ all -> 0x01bb }
            long r7 = r3.zzf     // Catch:{ all -> 0x01bb }
            r4.zzk((long) r7)     // Catch:{ all -> 0x01bb }
            boolean r7 = r3.zzh     // Catch:{ all -> 0x01bb }
            r4.zzb((boolean) r7)     // Catch:{ all -> 0x01bb }
            long r7 = r3.zzr     // Catch:{ all -> 0x01bb }
            r4.zzl(r7)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r7 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r8 = 0
            r7.zza((com.google.android.gms.measurement.internal.zzh) r4, (boolean) r8, (boolean) r8)     // Catch:{ all -> 0x01bb }
            goto L_0x091a
        L_0x0919:
            r8 = 0
        L_0x091a:
            boolean r2 = r2.zzj()     // Catch:{ all -> 0x01bb }
            if (r2 == 0) goto L_0x0937
            java.lang.String r2 = r4.zzad()     // Catch:{ all -> 0x01bb }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x01bb }
            if (r2 != 0) goto L_0x0937
            java.lang.String r2 = r4.zzad()     // Catch:{ all -> 0x01bb }
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x01bb }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x01bb }
            r6.zzc((java.lang.String) r2)     // Catch:{ all -> 0x01bb }
        L_0x0937:
            java.lang.String r2 = r4.zzag()     // Catch:{ all -> 0x01bb }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x01bb }
            if (r2 != 0) goto L_0x094e
            java.lang.String r2 = r4.zzag()     // Catch:{ all -> 0x01bb }
            java.lang.Object r2 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x01bb }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x01bb }
            r6.zzl((java.lang.String) r2)     // Catch:{ all -> 0x01bb }
        L_0x094e:
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r3.zza     // Catch:{ all -> 0x01bb }
            java.util.List r2 = r2.zzl(r7)     // Catch:{ all -> 0x01bb }
            r12 = r8
        L_0x0959:
            int r7 = r2.size()     // Catch:{ all -> 0x01bb }
            if (r12 >= r7) goto L_0x09be
            com.google.android.gms.internal.measurement.zzgn$zzo$zza r7 = com.google.android.gms.internal.measurement.zzgn.zzo.zze()     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r2.get(r12)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r9 = (com.google.android.gms.measurement.internal.zzom) r9     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r9.zzc     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzo$zza r7 = r7.zza((java.lang.String) r9)     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r2.get(r12)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r9 = (com.google.android.gms.measurement.internal.zzom) r9     // Catch:{ all -> 0x01bb }
            long r8 = r9.zzd     // Catch:{ all -> 0x01bb }
            com.google.android.gms.internal.measurement.zzgn$zzo$zza r7 = r7.zzb((long) r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzol r8 = r36.zzp()     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r2.get(r12)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r9 = (com.google.android.gms.measurement.internal.zzom) r9     // Catch:{ all -> 0x01bb }
            java.lang.Object r9 = r9.zze     // Catch:{ all -> 0x01bb }
            r8.zza((com.google.android.gms.internal.measurement.zzgn.zzo.zza) r7, (java.lang.Object) r9)     // Catch:{ all -> 0x01bb }
            r6.zza((com.google.android.gms.internal.measurement.zzgn.zzo.zza) r7)     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = "_sid"
            java.lang.Object r8 = r2.get(r12)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzom r8 = (com.google.android.gms.measurement.internal.zzom) r8     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r8.zzc     // Catch:{ all -> 0x01bb }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x09ba
            long r7 = r4.zzv()     // Catch:{ all -> 0x01bb }
            int r7 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r7 == 0) goto L_0x09ba
            com.google.android.gms.measurement.internal.zzol r7 = r36.zzp()     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r3.zzv     // Catch:{ all -> 0x01bb }
            long r7 = r7.zza((java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            long r18 = r4.zzv()     // Catch:{ all -> 0x01bb }
            int r7 = (r7 > r18 ? 1 : (r7 == r18 ? 0 : -1))
            if (r7 == 0) goto L_0x09ba
            r6.zzr()     // Catch:{ all -> 0x01bb }
        L_0x09ba:
            int r12 = r12 + 1
            r8 = 0
            goto L_0x0959
        L_0x09be:
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ IOException -> 0x0a34 }
            com.google.android.gms.internal.measurement.zzml r3 = r6.zzai()     // Catch:{ IOException -> 0x0a34 }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ IOException -> 0x0a34 }
            com.google.android.gms.internal.measurement.zzgn$zzk r3 = (com.google.android.gms.internal.measurement.zzgn.zzk) r3     // Catch:{ IOException -> 0x0a34 }
            long r2 = r2.zza((com.google.android.gms.internal.measurement.zzgn.zzk) r3)     // Catch:{ IOException -> 0x0a34 }
            com.google.android.gms.measurement.internal.zzam r4 = r36.zzf()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzbc r6 = r5.zzf     // Catch:{ all -> 0x01bb }
            if (r6 == 0) goto L_0x0a2a
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x01bb }
        L_0x09da:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x09ee
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01bb }
            boolean r7 = r10.equals(r7)     // Catch:{ all -> 0x01bb }
            if (r7 == 0) goto L_0x09da
        L_0x09ec:
            r6 = 1
            goto L_0x0a2b
        L_0x09ee:
            com.google.android.gms.measurement.internal.zzhg r6 = r36.zzi()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x01bb }
            java.lang.String r8 = r5.zzb     // Catch:{ all -> 0x01bb }
            boolean r6 = r6.zzc((java.lang.String) r7, (java.lang.String) r8)     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r26 = r36.zzf()     // Catch:{ all -> 0x01bb }
            long r27 = r36.zzx()     // Catch:{ all -> 0x01bb }
            java.lang.String r7 = r5.zza     // Catch:{ all -> 0x01bb }
            r34 = 0
            r35 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r29 = r7
            com.google.android.gms.measurement.internal.zzar r7 = r26.zza(r27, r29, r30, r31, r32, r33, r34, r35)     // Catch:{ all -> 0x01bb }
            if (r6 == 0) goto L_0x0a2a
            long r6 = r7.zze     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzah r8 = r36.zze()     // Catch:{ all -> 0x01bb }
            java.lang.String r9 = r5.zza     // Catch:{ all -> 0x01bb }
            int r8 = r8.zzc(r9)     // Catch:{ all -> 0x01bb }
            long r8 = (long) r8     // Catch:{ all -> 0x01bb }
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x0a2a
            goto L_0x09ec
        L_0x0a2a:
            r6 = 0
        L_0x0a2b:
            boolean r2 = r4.zza((com.google.android.gms.measurement.internal.zzba) r5, (long) r2, (boolean) r6)     // Catch:{ all -> 0x01bb }
            if (r2 == 0) goto L_0x0a4b
            r1.zzp = r13     // Catch:{ all -> 0x01bb }
            goto L_0x0a4b
        L_0x0a34:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzgi r3 = r36.zzj()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x01bb }
            java.lang.String r4 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r5 = r6.zzt()     // Catch:{ all -> 0x01bb }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r5)     // Catch:{ all -> 0x01bb }
            r3.zza(r4, r5, r2)     // Catch:{ all -> 0x01bb }
        L_0x0a4b:
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()     // Catch:{ all -> 0x01bb }
            r2.zzw()     // Catch:{ all -> 0x01bb }
            com.google.android.gms.measurement.internal.zzam r2 = r36.zzf()
            r2.zzu()
            r36.zzac()
            com.google.android.gms.measurement.internal.zzgi r1 = r36.zzj()
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()
            long r2 = java.lang.System.nanoTime()
            long r2 = r2 - r24
            r4 = 500000(0x7a120, double:2.47033E-318)
            long r2 = r2 + r4
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 / r4
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.String r3 = "Background event processing time, ms"
            r1.zza(r3, r2)
            return
        L_0x0a7c:
            com.google.android.gms.measurement.internal.zzam r1 = r36.zzf()
            r1.zzu()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zzc(com.google.android.gms.measurement.internal.zzbh, com.google.android.gms.measurement.internal.zzp):void");
    }

    @WorkerThread
    private final void zzb(zzbh zzbh, zzp zzp2) {
        Preconditions.checkNotEmpty(zzp2.zza);
        zzgm zza2 = zzgm.zza(zzbh);
        zzq().zza(zza2.zzd, zzf().zzd(zzp2.zza));
        zzq().zza(zza2, zze().zzb(zzp2.zza));
        zzbh zza3 = zza2.zza();
        if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zza3.zza) && "referrer API v2".equals(zza3.zzb.zzd("_cis"))) {
            String zzd2 = zza3.zzb.zzd("gclid");
            if (!TextUtils.isEmpty(zzd2)) {
                zza(new zzok("_lgclid", zza3.zzd, zzd2, "auto"), zzp2);
            }
        }
        zza(zza3, zzp2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x0146 A[Catch:{ all -> 0x005d, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0154 A[Catch:{ all -> 0x005d, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x017a A[Catch:{ all -> 0x005d, all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x017e A[Catch:{ all -> 0x005d, all -> 0x0013 }] */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.WorkerThread
    /* renamed from: zzb */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            com.google.android.gms.measurement.internal.zzhp r0 = r6.zzl()
            r0.zzt()
            r6.zzs()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0016
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x0013 }
            goto L_0x0016
        L_0x0013:
            r7 = move-exception
            goto L_0x01c9
        L_0x0016:
            com.google.android.gms.measurement.internal.zzgi r1 = r6.zzj()     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzp()     // Catch:{ all -> 0x0013 }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x0013 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0013 }
            r1.zza(r2, r3)     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzam r1 = r6.zzf()     // Catch:{ all -> 0x0013 }
            r1.zzp()     // Catch:{ all -> 0x0013 }
            com.google.android.gms.measurement.internal.zzam r1 = r6.zzf()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzh r1 = r1.zze(r7)     // Catch:{ all -> 0x005d }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x0043
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0043
            if (r8 != r3) goto L_0x0047
        L_0x0043:
            if (r9 != 0) goto L_0x0047
            r2 = 1
            goto L_0x0048
        L_0x0047:
            r2 = r0
        L_0x0048:
            if (r1 != 0) goto L_0x0060
            com.google.android.gms.measurement.internal.zzgi r8 = r6.zzj()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzu()     // Catch:{ all -> 0x005d }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r7)     // Catch:{ all -> 0x005d }
            r8.zza(r9, r7)     // Catch:{ all -> 0x005d }
            goto L_0x01ad
        L_0x005d:
            r7 = move-exception
            goto L_0x01c1
        L_0x0060:
            r4 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00bc
            if (r8 != r4) goto L_0x0067
            goto L_0x00bc
        L_0x0067:
            com.google.android.gms.common.util.Clock r10 = r6.zzb()     // Catch:{ all -> 0x005d }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x005d }
            r1.zzm(r10)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzam r10 = r6.zzf()     // Catch:{ all -> 0x005d }
            r10.zza((com.google.android.gms.measurement.internal.zzh) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgi r10 = r6.zzj()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgk r10 = r10.zzp()     // Catch:{ all -> 0x005d }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x005d }
            r10.zza(r11, r1, r9)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzhg r9 = r6.zzi()     // Catch:{ all -> 0x005d }
            r9.zzi(r7)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzms r7 = r6.zzj     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgz r7 = r7.zze     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.util.Clock r9 = r6.zzb()     // Catch:{ all -> 0x005d }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x005d }
            r7.zza(r9)     // Catch:{ all -> 0x005d }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00a8
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00b7
        L_0x00a8:
            com.google.android.gms.measurement.internal.zzms r7 = r6.zzj     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgz r7 = r7.zzc     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.util.Clock r8 = r6.zzb()     // Catch:{ all -> 0x005d }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x005d }
            r7.zza(r8)     // Catch:{ all -> 0x005d }
        L_0x00b7:
            r6.zzac()     // Catch:{ all -> 0x005d }
            goto L_0x01ad
        L_0x00bc:
            r9 = 0
            if (r11 == 0) goto L_0x00c8
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r2 = r11.get(r2)     // Catch:{ all -> 0x005d }
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x005d }
            goto L_0x00c9
        L_0x00c8:
            r2 = r9
        L_0x00c9:
            if (r2 == 0) goto L_0x00d8
            boolean r5 = r2.isEmpty()     // Catch:{ all -> 0x005d }
            if (r5 != 0) goto L_0x00d8
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x005d }
            goto L_0x00d9
        L_0x00d8:
            r2 = r9
        L_0x00d9:
            if (r11 == 0) goto L_0x00e4
            java.lang.String r5 = "ETag"
            java.lang.Object r11 = r11.get(r5)     // Catch:{ all -> 0x005d }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x005d }
            goto L_0x00e5
        L_0x00e4:
            r11 = r9
        L_0x00e5:
            if (r11 == 0) goto L_0x00f4
            boolean r5 = r11.isEmpty()     // Catch:{ all -> 0x005d }
            if (r5 != 0) goto L_0x00f4
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x005d }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x005d }
            goto L_0x00f5
        L_0x00f4:
            r11 = r9
        L_0x00f5:
            if (r8 == r4) goto L_0x0111
            if (r8 != r3) goto L_0x00fa
            goto L_0x0111
        L_0x00fa:
            com.google.android.gms.measurement.internal.zzhg r9 = r6.zzi()     // Catch:{ all -> 0x005d }
            boolean r9 = r9.zza(r7, r10, r2, r11)     // Catch:{ all -> 0x005d }
            if (r9 != 0) goto L_0x0132
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzf()     // Catch:{ all -> 0x0013 }
            r7.zzu()     // Catch:{ all -> 0x0013 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x0111:
            com.google.android.gms.measurement.internal.zzhg r11 = r6.zzi()     // Catch:{ all -> 0x005d }
            com.google.android.gms.internal.measurement.zzfx$zzd r11 = r11.zzc(r7)     // Catch:{ all -> 0x005d }
            if (r11 != 0) goto L_0x0132
            com.google.android.gms.measurement.internal.zzhg r11 = r6.zzi()     // Catch:{ all -> 0x005d }
            boolean r9 = r11.zza(r7, r9, r9, r9)     // Catch:{ all -> 0x005d }
            if (r9 != 0) goto L_0x0132
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzf()     // Catch:{ all -> 0x0013 }
            r7.zzu()     // Catch:{ all -> 0x0013 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x0132:
            com.google.android.gms.common.util.Clock r9 = r6.zzb()     // Catch:{ all -> 0x005d }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x005d }
            r1.zzd((long) r2)     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzam r9 = r6.zzf()     // Catch:{ all -> 0x005d }
            r9.zza((com.google.android.gms.measurement.internal.zzh) r1, (boolean) r0, (boolean) r0)     // Catch:{ all -> 0x005d }
            if (r8 != r4) goto L_0x0154
            com.google.android.gms.measurement.internal.zzgi r8 = r6.zzj()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzv()     // Catch:{ all -> 0x005d }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zza(r9, r7)     // Catch:{ all -> 0x005d }
            goto L_0x016a
        L_0x0154:
            com.google.android.gms.measurement.internal.zzgi r7 = r6.zzj()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzp()     // Catch:{ all -> 0x005d }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x005d }
            int r10 = r10.length     // Catch:{ all -> 0x005d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x005d }
            r7.zza(r9, r8, r10)     // Catch:{ all -> 0x005d }
        L_0x016a:
            com.google.android.gms.measurement.internal.zzgp r7 = r6.zzh()     // Catch:{ all -> 0x005d }
            boolean r7 = r7.zzu()     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x017e
            boolean r7 = r6.zzad()     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x017e
            r6.zzw()     // Catch:{ all -> 0x005d }
            goto L_0x01ad
        L_0x017e:
            com.google.android.gms.measurement.internal.zzah r7 = r6.zze()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzbz     // Catch:{ all -> 0x005d }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x01aa
            com.google.android.gms.measurement.internal.zzgp r7 = r6.zzh()     // Catch:{ all -> 0x005d }
            boolean r7 = r7.zzu()     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x01aa
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzf()     // Catch:{ all -> 0x005d }
            java.lang.String r8 = r1.zzac()     // Catch:{ all -> 0x005d }
            boolean r7 = r7.zzs(r8)     // Catch:{ all -> 0x005d }
            if (r7 == 0) goto L_0x01aa
            java.lang.String r7 = r1.zzac()     // Catch:{ all -> 0x005d }
            r6.zze((java.lang.String) r7)     // Catch:{ all -> 0x005d }
            goto L_0x01ad
        L_0x01aa:
            r6.zzac()     // Catch:{ all -> 0x005d }
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzf()     // Catch:{ all -> 0x005d }
            r7.zzw()     // Catch:{ all -> 0x005d }
            com.google.android.gms.measurement.internal.zzam r7 = r6.zzf()     // Catch:{ all -> 0x0013 }
            r7.zzu()     // Catch:{ all -> 0x0013 }
            r6.zzu = r0
            r6.zzaa()
            return
        L_0x01c1:
            com.google.android.gms.measurement.internal.zzam r8 = r6.zzf()     // Catch:{ all -> 0x0013 }
            r8.zzu()     // Catch:{ all -> 0x0013 }
            throw r7     // Catch:{ all -> 0x0013 }
        L_0x01c9:
            r6.zzu = r0
            r6.zzaa()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    @VisibleForTesting
    @WorkerThread
    private final zzaz zza(String str, zzaz zzaz, zzjc zzjc, zzai zzai) {
        zzjb zzjb;
        zzjc.zza zza2;
        zzjb zza3;
        int i3 = 90;
        if (zzi().zzb(str) == null) {
            if (zzaz.zzc() == zzjb.DENIED) {
                i3 = zzaz.zza();
                zzai.zza(zzjc.zza.AD_USER_DATA, i3);
            } else {
                zzai.zza(zzjc.zza.AD_USER_DATA, zzal.FAILSAFE);
            }
            return new zzaz(Boolean.FALSE, i3, Boolean.TRUE, "-");
        }
        zzjb zzc2 = zzaz.zzc();
        zzjb zzjb2 = zzjb.GRANTED;
        if (zzc2 == zzjb2 || zzc2 == (zzjb = zzjb.DENIED)) {
            i3 = zzaz.zza();
            zzai.zza(zzjc.zza.AD_USER_DATA, i3);
        } else {
            boolean z2 = true;
            if (!zzox.zza() || !zze().zza(zzbj.zzcw)) {
                zzjb zzjb3 = zzjb.UNINITIALIZED;
                if (!(zzc2 == zzjb3 || zzc2 == zzjb.POLICY)) {
                    z2 = false;
                }
                Preconditions.checkArgument(z2);
                zzhg zzhg = this.zzb;
                zzjc.zza zza4 = zzjc.zza.AD_USER_DATA;
                zzjc.zza zzb2 = zzhg.zzb(str, zza4);
                Boolean zze2 = zzjc.zze();
                if (zzb2 == zzjc.zza.AD_STORAGE && zze2 != null) {
                    zzc2 = zze2.booleanValue() ? zzjb2 : zzjb;
                    zzai.zza(zza4, zzal.REMOTE_DELEGATION);
                }
                if (zzc2 == zzjb3) {
                    if (!this.zzb.zzc(str, zza4)) {
                        zzjb2 = zzjb;
                    }
                    zzai.zza(zza4, zzal.REMOTE_DEFAULT);
                }
            } else if (zzc2 != zzjb.POLICY || (zza3 = this.zzb.zza(str, zza2)) == zzjb.UNINITIALIZED) {
                zzhg zzhg2 = this.zzb;
                zzjc.zza zza5 = zzjc.zza.AD_USER_DATA;
                zzjc.zza zzb3 = zzhg2.zzb(str, zza5);
                zzjb zzc3 = zzjc.zzc();
                if (!(zzc3 == zzjb2 || zzc3 == zzjb)) {
                    z2 = false;
                }
                if (zzb3 != zzjc.zza.AD_STORAGE || !z2) {
                    zzai.zza(zza5, zzal.REMOTE_DEFAULT);
                    if (!this.zzb.zzc(str, zza5)) {
                        zzc2 = zzjb;
                    }
                } else {
                    zzai.zza(zza5, zzal.REMOTE_DELEGATION);
                    zzc2 = zzc3;
                }
            } else {
                zzai.zza((zza2 = zzjc.zza.AD_USER_DATA), zzal.REMOTE_ENFORCED_DEFAULT);
                zzc2 = zza3;
            }
            zzc2 = zzjb2;
        }
        boolean zzn2 = this.zzb.zzn(str);
        SortedSet<String> zzh2 = zzi().zzh(str);
        if (zzc2 == zzjb.DENIED || zzh2.isEmpty()) {
            return new zzaz(Boolean.FALSE, i3, Boolean.valueOf(zzn2), "-");
        }
        Boolean bool = Boolean.TRUE;
        Boolean valueOf = Boolean.valueOf(zzn2);
        String str2 = "";
        if (zzn2) {
            str2 = TextUtils.join(str2, zzh2);
        }
        return new zzaz(bool, i3, valueOf, str2);
    }

    @WorkerThread
    public final void zzb(zzh zzh2, zzgn.zzk.zza zza2) {
        zzl().zzt();
        zzs();
        zzgn.zza.C0064zza zzc2 = zzgn.zza.zzc();
        byte[] zzav = zzh2.zzav();
        if (zzav != null) {
            try {
                zzc2 = (zzgn.zza.C0064zza) zzol.zza(zzc2, zzav);
            } catch (zzlk unused) {
                zzj().zzu().zza("Failed to parse locally stored ad campaign info. appId", zzgi.zza(zzh2.zzac()));
            }
        }
        for (zzgn.zzf next : zza2.zzaa()) {
            if (next.zzg().equals(Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN)) {
                String str = (String) zzol.zza(next, "gclid", (Object) "");
                String str2 = (String) zzol.zza(next, "gbraid", (Object) "");
                String str3 = (String) zzol.zza(next, "gad_source", (Object) "");
                if (!str.isEmpty() || !str2.isEmpty()) {
                    long longValue = ((Long) zzol.zza(next, "click_timestamp", (Object) 0L)).longValue();
                    if (longValue <= 0) {
                        longValue = next.zzd();
                    }
                    if ("referrer API v2".equals(zzol.zzb(next, "_cis"))) {
                        if (longValue > zzc2.zzb()) {
                            if (str.isEmpty()) {
                                zzc2.zzh();
                            } else {
                                zzc2.zzf(str);
                            }
                            if (str2.isEmpty()) {
                                zzc2.zzg();
                            } else {
                                zzc2.zze(str2);
                            }
                            if (str3.isEmpty()) {
                                zzc2.zzf();
                            } else {
                                zzc2.zzd(str3);
                            }
                            zzc2.zzb(longValue);
                        }
                    } else if (longValue > zzc2.zza()) {
                        if (str.isEmpty()) {
                            zzc2.zze();
                        } else {
                            zzc2.zzc(str);
                        }
                        if (str2.isEmpty()) {
                            zzc2.zzd();
                        } else {
                            zzc2.zzb(str2);
                        }
                        if (str3.isEmpty()) {
                            zzc2.zzc();
                        } else {
                            zzc2.zza(str3);
                        }
                        zzc2.zza(longValue);
                    }
                }
            }
        }
        if (!((zzgn.zza) ((zzlc) zzc2.zzai())).equals(zzgn.zza.zze())) {
            zza2.zza((zzgn.zza) ((zzlc) zzc2.zzai()));
        }
        zzh2.zza(((zzgn.zza) ((zzlc) zzc2.zzai())).zzca());
        if (zzh2.zzas()) {
            zzf().zza(zzh2, false, false);
        }
    }

    private static zznr zza(zznr zznr) {
        if (zznr == null) {
            throw new IllegalStateException("Upload Component not created");
        } else if (zznr.zzan()) {
            return zznr;
        } else {
            throw new IllegalStateException("Component not initialized: ".concat(String.valueOf(zznr.getClass())));
        }
    }

    public static zznv zza(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zznv.class) {
                try {
                    if (zza == null) {
                        zza = new zznv((zzoh) Preconditions.checkNotNull(new zzoh(context)));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return zza;
    }

    @WorkerThread
    private final Boolean zza(zzh zzh2) {
        try {
            if (zzh2.zze() != SieveCacheKt.NodeMetaAndPreviousMask) {
                if (zzh2.zze() == ((long) Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzh2.zzac(), 0).versionCode)) {
                    return Boolean.TRUE;
                }
            } else {
                String str = Wrappers.packageManager(this.zzm.zza()).getPackageInfo(zzh2.zzac(), 0).versionName;
                String zzaf2 = zzh2.zzaf();
                if (zzaf2 != null && zzaf2.equals(str)) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @WorkerThread
    public final void zzb(zzaf zzaf2) {
        zzp zzc2 = zzc((String) Preconditions.checkNotNull(zzaf2.zza));
        if (zzc2 != null) {
            zzb(zzaf2, zzc2);
        }
    }

    @WorkerThread
    public final void zzb(zzaf zzaf2, zzp zzp2) {
        boolean z2;
        Preconditions.checkNotNull(zzaf2);
        Preconditions.checkNotEmpty(zzaf2.zza);
        Preconditions.checkNotNull(zzaf2.zzb);
        Preconditions.checkNotNull(zzaf2.zzc);
        Preconditions.checkNotEmpty(zzaf2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzi(zzp2)) {
            if (!zzp2.zzh) {
                zza(zzp2);
                return;
            }
            zzaf zzaf3 = new zzaf(zzaf2);
            boolean z3 = false;
            zzaf3.zze = false;
            zzf().zzp();
            try {
                zzaf zzc2 = zzf().zzc((String) Preconditions.checkNotNull(zzaf3.zza), zzaf3.zzc.zza);
                if (zzc2 != null && !zzc2.zzb.equals(zzaf3.zzb)) {
                    zzj().zzu().zza("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzm.zzk().zzc(zzaf3.zzc.zza), zzaf3.zzb, zzc2.zzb);
                }
                if (zzc2 != null && (z2 = zzc2.zze)) {
                    zzaf3.zzb = zzc2.zzb;
                    zzaf3.zzd = zzc2.zzd;
                    zzaf3.zzh = zzc2.zzh;
                    zzaf3.zzf = zzc2.zzf;
                    zzaf3.zzi = zzc2.zzi;
                    zzaf3.zze = z2;
                    zzok zzok = zzaf3.zzc;
                    zzaf3.zzc = new zzok(zzok.zza, zzc2.zzc.zzb, zzok.zza(), zzc2.zzc.zze);
                } else if (TextUtils.isEmpty(zzaf3.zzf)) {
                    zzok zzok2 = zzaf3.zzc;
                    zzaf3.zzc = new zzok(zzok2.zza, zzaf3.zzd, zzok2.zza(), zzaf3.zzc.zze);
                    z3 = true;
                    zzaf3.zze = true;
                }
                if (zzaf3.zze) {
                    zzok zzok3 = zzaf3.zzc;
                    zzom zzom = new zzom((String) Preconditions.checkNotNull(zzaf3.zza), zzaf3.zzb, zzok3.zza, zzok3.zzb, Preconditions.checkNotNull(zzok3.zza()));
                    if (zzf().zza(zzom)) {
                        zzj().zzc().zza("User property updated immediately", zzaf3.zza, this.zzm.zzk().zzc(zzom.zzc), zzom.zze);
                    } else {
                        zzj().zzg().zza("(2)Too many active user properties, ignoring", zzgi.zza(zzaf3.zza), this.zzm.zzk().zzc(zzom.zzc), zzom.zze);
                    }
                    if (z3 && zzaf3.zzi != null) {
                        zzc(new zzbh(zzaf3.zzi, zzaf3.zzd), zzp2);
                    }
                }
                if (zzf().zza(zzaf3)) {
                    zzj().zzc().zza("Conditional property added", zzaf3.zza, this.zzm.zzk().zzc(zzaf3.zzc.zza), zzaf3.zzc.zza());
                } else {
                    zzj().zzg().zza("Too many conditional properties, ignoring", zzgi.zza(zzaf3.zza), this.zzm.zzk().zzc(zzaf3.zzc.zza), zzaf3.zzc.zza());
                }
                zzf().zzw();
                zzf().zzu();
            } catch (Throwable th) {
                zzf().zzu();
                throw th;
            }
        }
    }

    @WorkerThread
    private final String zza(zzjc zzjc) {
        if (!zzjc.zzj()) {
            return null;
        }
        byte[] bArr = new byte[16];
        zzq().zzv().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    public static /* synthetic */ void zza(zznv zznv, zzoh zzoh) {
        zznv.zzl().zzt();
        zznv.zzl = new zzhd(zznv);
        zzam zzam = new zzam(zznv);
        zzam.zzam();
        zznv.zzd = zzam;
        zznv.zze().zza((zzaj) Preconditions.checkNotNull(zznv.zzb));
        zzms zzms = new zzms(zznv);
        zzms.zzam();
        zznv.zzj = zzms;
        zzv zzv2 = new zzv(zznv);
        zzv2.zzam();
        zznv.zzg = zzv2;
        zzlf zzlf = new zzlf(zznv);
        zzlf.zzam();
        zznv.zzi = zzlf;
        zznm zznm = new zznm(zznv);
        zznm.zzam();
        zznv.zzf = zznm;
        zznv.zze = new zzgs(zznv);
        if (zznv.zzs != zznv.zzt) {
            zznv.zzj().zzg().zza("Not all upload components initialized", Integer.valueOf(zznv.zzs), Integer.valueOf(zznv.zzt));
        }
        zznv.zzn = true;
    }

    @WorkerThread
    public final void zza(Runnable runnable) {
        zzl().zzt();
        if (this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ab, code lost:
        if ((zze().zzc(r6, com.google.android.gms.measurement.internal.zzbj.zzaw) + r0.zzb) < zzb().elapsedRealtime()) goto L_0x00ad;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(java.lang.String r6, com.google.android.gms.internal.measurement.zzgn.zzk.zza r7) {
        /*
            r5 = this;
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            java.util.Set r0 = r0.zzg(r6)
            if (r0 == 0) goto L_0x000d
            r7.zzd((java.lang.Iterable<java.lang.String>) r0)
        L_0x000d:
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            boolean r0 = r0.zzq(r6)
            if (r0 == 0) goto L_0x001a
            r7.zzj()
        L_0x001a:
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            boolean r0 = r0.zzt(r6)
            r1 = -1
            if (r0 == 0) goto L_0x003f
            java.lang.String r0 = r7.zzy()
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L_0x003f
            java.lang.String r2 = "."
            int r2 = r0.indexOf(r2)
            if (r2 == r1) goto L_0x003f
            r3 = 0
            java.lang.String r0 = r0.substring(r3, r2)
            r7.zzo(r0)
        L_0x003f:
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            boolean r0 = r0.zzu(r6)
            if (r0 == 0) goto L_0x0054
            java.lang.String r0 = "_id"
            int r0 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r7, (java.lang.String) r0)
            if (r0 == r1) goto L_0x0054
            r7.zzc((int) r0)
        L_0x0054:
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            boolean r0 = r0.zzs(r6)
            if (r0 == 0) goto L_0x0061
            r7.zzk()
        L_0x0061:
            com.google.android.gms.measurement.internal.zzhg r0 = r5.zzi()
            boolean r0 = r0.zzp(r6)
            if (r0 == 0) goto L_0x00bd
            r7.zzh()
            boolean r0 = com.google.android.gms.internal.measurement.zzpd.zza()
            if (r0 == 0) goto L_0x008a
            com.google.android.gms.measurement.internal.zzah r0 = r5.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbj.zzdc
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)
            if (r0 == 0) goto L_0x008a
            com.google.android.gms.measurement.internal.zzjc r0 = r5.zzb((java.lang.String) r6)
            boolean r0 = r0.zzj()
            if (r0 == 0) goto L_0x00bd
        L_0x008a:
            java.util.Map<java.lang.String, com.google.android.gms.measurement.internal.zznv$zzb> r0 = r5.zzae
            java.lang.Object r0 = r0.get(r6)
            com.google.android.gms.measurement.internal.zznv$zzb r0 = (com.google.android.gms.measurement.internal.zznv.zzb) r0
            if (r0 == 0) goto L_0x00ad
            long r1 = r0.zzb
            com.google.android.gms.measurement.internal.zzah r3 = r5.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Long> r4 = com.google.android.gms.measurement.internal.zzbj.zzaw
            long r3 = r3.zzc((java.lang.String) r6, (com.google.android.gms.measurement.internal.zzfz<java.lang.Long>) r4)
            long r3 = r3 + r1
            com.google.android.gms.common.util.Clock r1 = r5.zzb()
            long r1 = r1.elapsedRealtime()
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b8
        L_0x00ad:
            com.google.android.gms.measurement.internal.zznv$zzb r0 = new com.google.android.gms.measurement.internal.zznv$zzb
            r1 = 0
            r0.<init>()
            java.util.Map<java.lang.String, com.google.android.gms.measurement.internal.zznv$zzb> r1 = r5.zzae
            r1.put(r6, r0)
        L_0x00b8:
            java.lang.String r0 = r0.zza
            r7.zzk((java.lang.String) r0)
        L_0x00bd:
            com.google.android.gms.measurement.internal.zzhg r5 = r5.zzi()
            boolean r5 = r5.zzr(r6)
            if (r5 == 0) goto L_0x00ca
            r7.zzr()
        L_0x00ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, com.google.android.gms.internal.measurement.zzgn$zzk$zza):void");
    }

    @WorkerThread
    public final void zza(zzh zzh2, zzgn.zzk.zza zza2) {
        zzgn.zzo zzo2;
        zzl().zzt();
        zzs();
        zzai zza3 = zzai.zza(zza2.zzv());
        if (!zzox.zza() || !zze().zza(zzbj.zzcw)) {
            String zzac2 = zzh2.zzac();
            zzl().zzt();
            zzs();
            zzjc zzb2 = zzb(zzac2);
            if (zzb2.zze() != null) {
                zza3.zza(zzjc.zza.AD_STORAGE, zzb2.zza());
            } else {
                zza3.zza(zzjc.zza.AD_STORAGE, zzal.FAILSAFE);
            }
            if (zzb2.zzf() != null) {
                zza3.zza(zzjc.zza.ANALYTICS_STORAGE, zzb2.zza());
            } else {
                zza3.zza(zzjc.zza.ANALYTICS_STORAGE, zzal.FAILSAFE);
            }
        } else {
            String zzac3 = zzh2.zzac();
            zzl().zzt();
            zzs();
            zzjc zzb3 = zzb(zzac3);
            int[] iArr = zzoc.zza;
            int i3 = iArr[zzb3.zzc().ordinal()];
            if (i3 == 1) {
                zza3.zza(zzjc.zza.AD_STORAGE, zzal.REMOTE_ENFORCED_DEFAULT);
            } else if (i3 == 2 || i3 == 3) {
                zza3.zza(zzjc.zza.AD_STORAGE, zzb3.zza());
            } else {
                zza3.zza(zzjc.zza.AD_STORAGE, zzal.FAILSAFE);
            }
            int i4 = iArr[zzb3.zzd().ordinal()];
            if (i4 == 1) {
                zza3.zza(zzjc.zza.ANALYTICS_STORAGE, zzal.REMOTE_ENFORCED_DEFAULT);
            } else if (i4 == 2 || i4 == 3) {
                zza3.zza(zzjc.zza.ANALYTICS_STORAGE, zzb3.zza());
            } else {
                zza3.zza(zzjc.zza.ANALYTICS_STORAGE, zzal.FAILSAFE);
            }
        }
        String zzac4 = zzh2.zzac();
        zzl().zzt();
        zzs();
        zzaz zza4 = zza(zzac4, zzd(zzac4), zzb(zzac4), zza3);
        zza2.zzb(((Boolean) Preconditions.checkNotNull(zza4.zzd())).booleanValue());
        if (!TextUtils.isEmpty(zza4.zze())) {
            zza2.zzh(zza4.zze());
        }
        zzl().zzt();
        zzs();
        Iterator<zzgn.zzo> it = zza2.zzab().iterator();
        while (true) {
            if (!it.hasNext()) {
                zzo2 = null;
                break;
            }
            zzo2 = it.next();
            if ("_npa".equals(zzo2.zzg())) {
                break;
            }
        }
        if (zzo2 != null) {
            zzjc.zza zza5 = zzjc.zza.AD_PERSONALIZATION;
            if (zza3.zza(zza5) == zzal.UNSET) {
                zzom zze2 = zzf().zze(zzh2.zzac(), "_npa");
                if (zze2 == null) {
                    Boolean zzx2 = zzh2.zzx();
                    if (zzx2 == null || ((zzx2 == Boolean.TRUE && zzo2.zzc() != 1) || (zzx2 == Boolean.FALSE && zzo2.zzc() != 0))) {
                        zza3.zza(zza5, zzal.API);
                    } else {
                        zza3.zza(zza5, zzal.MANIFEST);
                    }
                } else if ("tcf".equals(zze2.zzb)) {
                    zza3.zza(zza5, zzal.TCF);
                } else if ("app".equals(zze2.zzb)) {
                    zza3.zza(zza5, zzal.API);
                } else {
                    zza3.zza(zza5, zzal.MANIFEST);
                }
            }
        } else {
            int zza6 = zza(zzh2.zzac(), zza3);
            zza2.zza((zzgn.zzo) ((zzlc) zzgn.zzo.zze().zza("_npa").zzb(zzb().currentTimeMillis()).zza((long) zza6).zzai()));
            zzj().zzp().zza("Setting user property", "non_personalized_ads(_npa)", Integer.valueOf(zza6));
        }
        zza2.zzf(zza3.toString());
        boolean zzn2 = this.zzb.zzn(zzh2.zzac());
        List<zzgn.zzf> zzaa2 = zza2.zzaa();
        int i5 = 0;
        for (int i6 = 0; i6 < zzaa2.size(); i6++) {
            if ("_tcf".equals(zzaa2.get(i6).zzg())) {
                zzgn.zzf.zza zza7 = (zzgn.zzf.zza) zzaa2.get(i6).zzcd();
                List<zzgn.zzh> zzf2 = zza7.zzf();
                while (true) {
                    if (i5 >= zzf2.size()) {
                        break;
                    } else if ("_tcfd".equals(zzf2.get(i5).zzg())) {
                        zza7.zza(i5, zzgn.zzh.zze().zza("_tcfd").zzb(zzni.zza(zzf2.get(i5).zzh(), zzn2)));
                        break;
                    } else {
                        i5++;
                    }
                }
                zza2.zza(i6, zza7);
                return;
            }
        }
    }

    @VisibleForTesting
    private static void zza(zzgn.zzf.zza zza2, int i3, String str) {
        List<zzgn.zzh> zzf2 = zza2.zzf();
        int i4 = 0;
        while (i4 < zzf2.size()) {
            if (!"_err".equals(zzf2.get(i4).zzg())) {
                i4++;
            } else {
                return;
            }
        }
        zza2.zza((zzgn.zzh) ((zzlc) zzgn.zzh.zze().zza("_err").zza((long) i3).zzai())).zza((zzgn.zzh) ((zzlc) zzgn.zzh.zze().zza("_ev").zzb(str).zzai()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x002b, code lost:
        r4 = r1.zzag;
     */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.measurement.internal.zzbh r20, com.google.android.gms.measurement.internal.zzp r21) {
        /*
            r19 = this;
            r1 = r19
            r0 = r21
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r21)
            java.lang.String r2 = r0.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)
            com.google.android.gms.measurement.internal.zzhp r2 = r19.zzl()
            r2.zzt()
            r19.zzs()
            java.lang.String r2 = r0.zza
            r3 = r20
            long r10 = r3.zzd
            com.google.android.gms.measurement.internal.zzgm r3 = com.google.android.gms.measurement.internal.zzgm.zza(r20)
            com.google.android.gms.measurement.internal.zzhp r4 = r19.zzl()
            r4.zzt()
            com.google.android.gms.measurement.internal.zzlh r4 = r1.zzaf
            if (r4 == 0) goto L_0x0039
            java.lang.String r4 = r1.zzag
            if (r4 == 0) goto L_0x0039
            boolean r4 = r4.equals(r2)
            if (r4 != 0) goto L_0x0036
            goto L_0x0039
        L_0x0036:
            com.google.android.gms.measurement.internal.zzlh r4 = r1.zzaf
            goto L_0x003a
        L_0x0039:
            r4 = 0
        L_0x003a:
            android.os.Bundle r5 = r3.zzd
            r12 = 0
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzlh) r4, (android.os.Bundle) r5, (boolean) r12)
            com.google.android.gms.measurement.internal.zzbh r3 = r3.zza()
            r19.zzp()
            boolean r4 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.measurement.internal.zzbh) r3, (com.google.android.gms.measurement.internal.zzp) r0)
            if (r4 != 0) goto L_0x004e
            return
        L_0x004e:
            boolean r4 = r0.zzh
            if (r4 != 0) goto L_0x0056
            r1.zza((com.google.android.gms.measurement.internal.zzp) r0)
            return
        L_0x0056:
            java.util.List<java.lang.String> r4 = r0.zzs
            if (r4 == 0) goto L_0x0097
            java.lang.String r5 = r3.zza
            boolean r4 = r4.contains(r5)
            if (r4 == 0) goto L_0x0085
            com.google.android.gms.measurement.internal.zzbc r4 = r3.zzb
            android.os.Bundle r4 = r4.zzb()
            java.lang.String r5 = "ga_safelisted"
            r6 = 1
            r4.putLong(r5, r6)
            com.google.android.gms.measurement.internal.zzbh r5 = new com.google.android.gms.measurement.internal.zzbh
            java.lang.String r14 = r3.zza
            com.google.android.gms.measurement.internal.zzbc r15 = new com.google.android.gms.measurement.internal.zzbc
            r15.<init>(r4)
            java.lang.String r4 = r3.zzc
            long r6 = r3.zzd
            r13 = r5
            r16 = r4
            r17 = r6
            r13.<init>(r14, r15, r16, r17)
            goto L_0x0098
        L_0x0085:
            com.google.android.gms.measurement.internal.zzgi r0 = r19.zzj()
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzc()
            java.lang.String r1 = r3.zza
            java.lang.String r3 = r3.zzc
            java.lang.String r4 = "Dropping non-safelisted event. appId, event name, origin"
            r0.zza(r4, r2, r1, r3)
            return
        L_0x0097:
            r13 = r3
        L_0x0098:
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()
            r3.zzp()
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x00cc }
            r3.zzt()     // Catch:{ all -> 0x00cc }
            r3.zzal()     // Catch:{ all -> 0x00cc }
            r4 = 0
            int r4 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x00cf
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzu()     // Catch:{ all -> 0x00cc }
            java.lang.String r5 = "Invalid time querying timed out conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r2)     // Catch:{ all -> 0x00cc }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00cc }
            r3.zza(r5, r6, r7)     // Catch:{ all -> 0x00cc }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00cc }
            goto L_0x00dd
        L_0x00cc:
            r0 = move-exception
            goto L_0x02e5
        L_0x00cf:
            java.lang.String r5 = "active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00cc }
            java.lang.String[] r6 = new java.lang.String[]{r2, r6}     // Catch:{ all -> 0x00cc }
            java.util.List r3 = r3.zza((java.lang.String) r5, (java.lang.String[]) r6)     // Catch:{ all -> 0x00cc }
        L_0x00dd:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00cc }
        L_0x00e1:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x00cc }
            if (r5 == 0) goto L_0x012c
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzaf r5 = (com.google.android.gms.measurement.internal.zzaf) r5     // Catch:{ all -> 0x00cc }
            if (r5 == 0) goto L_0x00e1
            com.google.android.gms.measurement.internal.zzgi r6 = r19.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzp()     // Catch:{ all -> 0x00cc }
            java.lang.String r7 = "User property timed out"
            java.lang.String r8 = r5.zza     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzhw r9 = r1.zzm     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgh r9 = r9.zzk()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r14 = r5.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r14 = r14.zza     // Catch:{ all -> 0x00cc }
            java.lang.String r9 = r9.zzc(r14)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r14 = r5.zzc     // Catch:{ all -> 0x00cc }
            java.lang.Object r14 = r14.zza()     // Catch:{ all -> 0x00cc }
            r6.zza(r7, r8, r9, r14)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzbh r6 = r5.zzg     // Catch:{ all -> 0x00cc }
            if (r6 == 0) goto L_0x0120
            com.google.android.gms.measurement.internal.zzbh r6 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzbh r7 = r5.zzg     // Catch:{ all -> 0x00cc }
            r6.<init>(r7, r10)     // Catch:{ all -> 0x00cc }
            r1.zzc(r6, r0)     // Catch:{ all -> 0x00cc }
        L_0x0120:
            com.google.android.gms.measurement.internal.zzam r6 = r19.zzf()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r5 = r5.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r5 = r5.zza     // Catch:{ all -> 0x00cc }
            r6.zza((java.lang.String) r2, (java.lang.String) r5)     // Catch:{ all -> 0x00cc }
            goto L_0x00e1
        L_0x012c:
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x00cc }
            r3.zzt()     // Catch:{ all -> 0x00cc }
            r3.zzal()     // Catch:{ all -> 0x00cc }
            if (r4 >= 0) goto L_0x0155
            com.google.android.gms.measurement.internal.zzgi r3 = r3.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzu()     // Catch:{ all -> 0x00cc }
            java.lang.String r5 = "Invalid time querying expired conditional properties"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r2)     // Catch:{ all -> 0x00cc }
            java.lang.Long r7 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00cc }
            r3.zza(r5, r6, r7)     // Catch:{ all -> 0x00cc }
            java.util.List r3 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00cc }
            goto L_0x0163
        L_0x0155:
            java.lang.String r5 = "active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00cc }
            java.lang.String[] r6 = new java.lang.String[]{r2, r6}     // Catch:{ all -> 0x00cc }
            java.util.List r3 = r3.zza((java.lang.String) r5, (java.lang.String[]) r6)     // Catch:{ all -> 0x00cc }
        L_0x0163:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x00cc }
            int r6 = r3.size()     // Catch:{ all -> 0x00cc }
            r5.<init>(r6)     // Catch:{ all -> 0x00cc }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00cc }
        L_0x0170:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x00cc }
            if (r6 == 0) goto L_0x01bf
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzaf r6 = (com.google.android.gms.measurement.internal.zzaf) r6     // Catch:{ all -> 0x00cc }
            if (r6 == 0) goto L_0x0170
            com.google.android.gms.measurement.internal.zzgi r7 = r19.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzp()     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = "User property expired"
            java.lang.String r9 = r6.zza     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzhw r14 = r1.zzm     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgh r14 = r14.zzk()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r15 = r6.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r15 = r15.zza     // Catch:{ all -> 0x00cc }
            java.lang.String r14 = r14.zzc(r15)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r15 = r6.zzc     // Catch:{ all -> 0x00cc }
            java.lang.Object r15 = r15.zza()     // Catch:{ all -> 0x00cc }
            r7.zza(r8, r9, r14, r15)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzam r7 = r19.zzf()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r8 = r6.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r8 = r8.zza     // Catch:{ all -> 0x00cc }
            r7.zzh(r2, r8)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzbh r7 = r6.zzk     // Catch:{ all -> 0x00cc }
            if (r7 == 0) goto L_0x01b3
            r5.add(r7)     // Catch:{ all -> 0x00cc }
        L_0x01b3:
            com.google.android.gms.measurement.internal.zzam r7 = r19.zzf()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzok r6 = r6.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r6.zza     // Catch:{ all -> 0x00cc }
            r7.zza((java.lang.String) r2, (java.lang.String) r6)     // Catch:{ all -> 0x00cc }
            goto L_0x0170
        L_0x01bf:
            int r3 = r5.size()     // Catch:{ all -> 0x00cc }
            r6 = r12
        L_0x01c4:
            if (r6 >= r3) goto L_0x01d7
            java.lang.Object r7 = r5.get(r6)     // Catch:{ all -> 0x00cc }
            int r6 = r6 + 1
            com.google.android.gms.measurement.internal.zzbh r7 = (com.google.android.gms.measurement.internal.zzbh) r7     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzbh r8 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00cc }
            r8.<init>(r7, r10)     // Catch:{ all -> 0x00cc }
            r1.zzc(r8, r0)     // Catch:{ all -> 0x00cc }
            goto L_0x01c4
        L_0x01d7:
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()     // Catch:{ all -> 0x00cc }
            java.lang.String r5 = r13.zza     // Catch:{ all -> 0x00cc }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r2)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)     // Catch:{ all -> 0x00cc }
            r3.zzt()     // Catch:{ all -> 0x00cc }
            r3.zzal()     // Catch:{ all -> 0x00cc }
            if (r4 >= 0) goto L_0x020d
            com.google.android.gms.measurement.internal.zzgi r4 = r3.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzu()     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = "Invalid time querying triggered conditional properties"
            java.lang.Object r2 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r2)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgh r3 = r3.zzi()     // Catch:{ all -> 0x00cc }
            java.lang.String r3 = r3.zza((java.lang.String) r5)     // Catch:{ all -> 0x00cc }
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x00cc }
            r4.zza(r6, r2, r3, r5)     // Catch:{ all -> 0x00cc }
            java.util.List r2 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00cc }
            goto L_0x021b
        L_0x020d:
            java.lang.String r4 = "active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout"
            java.lang.String r6 = java.lang.String.valueOf(r10)     // Catch:{ all -> 0x00cc }
            java.lang.String[] r2 = new java.lang.String[]{r2, r5, r6}     // Catch:{ all -> 0x00cc }
            java.util.List r2 = r3.zza((java.lang.String) r4, (java.lang.String[]) r2)     // Catch:{ all -> 0x00cc }
        L_0x021b:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x00cc }
            int r3 = r2.size()     // Catch:{ all -> 0x00cc }
            r14.<init>(r3)     // Catch:{ all -> 0x00cc }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00cc }
        L_0x0228:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00cc }
            if (r3 == 0) goto L_0x02bb
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00cc }
            r15 = r3
            com.google.android.gms.measurement.internal.zzaf r15 = (com.google.android.gms.measurement.internal.zzaf) r15     // Catch:{ all -> 0x00cc }
            if (r15 == 0) goto L_0x0228
            com.google.android.gms.measurement.internal.zzok r3 = r15.zzc     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzom r9 = new com.google.android.gms.measurement.internal.zzom     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = r15.zza     // Catch:{ all -> 0x00cc }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x00cc }
            java.lang.String r5 = r15.zzb     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r3.zza     // Catch:{ all -> 0x00cc }
            java.lang.Object r3 = r3.zza()     // Catch:{ all -> 0x00cc }
            java.lang.Object r16 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x00cc }
            r3 = r9
            r7 = r10
            r12 = r9
            r9 = r16
            r3.<init>(r4, r5, r6, r7, r9)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()     // Catch:{ all -> 0x00cc }
            boolean r3 = r3.zza((com.google.android.gms.measurement.internal.zzom) r12)     // Catch:{ all -> 0x00cc }
            if (r3 == 0) goto L_0x027f
            com.google.android.gms.measurement.internal.zzgi r3 = r19.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = "User property triggered"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzhw r6 = r1.zzm     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x00cc }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x00cc }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x00cc }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x00cc }
            goto L_0x02a0
        L_0x027f:
            com.google.android.gms.measurement.internal.zzgi r3 = r19.zzj()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x00cc }
            java.lang.String r4 = "Too many active user properties, ignoring"
            java.lang.String r5 = r15.zza     // Catch:{ all -> 0x00cc }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r5)     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzhw r6 = r1.zzm     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzgh r6 = r6.zzk()     // Catch:{ all -> 0x00cc }
            java.lang.String r7 = r12.zzc     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = r6.zzc(r7)     // Catch:{ all -> 0x00cc }
            java.lang.Object r7 = r12.zze     // Catch:{ all -> 0x00cc }
            r3.zza(r4, r5, r6, r7)     // Catch:{ all -> 0x00cc }
        L_0x02a0:
            com.google.android.gms.measurement.internal.zzbh r3 = r15.zzi     // Catch:{ all -> 0x00cc }
            if (r3 == 0) goto L_0x02a7
            r14.add(r3)     // Catch:{ all -> 0x00cc }
        L_0x02a7:
            com.google.android.gms.measurement.internal.zzok r3 = new com.google.android.gms.measurement.internal.zzok     // Catch:{ all -> 0x00cc }
            r3.<init>(r12)     // Catch:{ all -> 0x00cc }
            r15.zzc = r3     // Catch:{ all -> 0x00cc }
            r3 = 1
            r15.zze = r3     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzam r3 = r19.zzf()     // Catch:{ all -> 0x00cc }
            r3.zza((com.google.android.gms.measurement.internal.zzaf) r15)     // Catch:{ all -> 0x00cc }
            r12 = 0
            goto L_0x0228
        L_0x02bb:
            r1.zzc(r13, r0)     // Catch:{ all -> 0x00cc }
            int r2 = r14.size()     // Catch:{ all -> 0x00cc }
            r12 = 0
        L_0x02c3:
            if (r12 >= r2) goto L_0x02d6
            java.lang.Object r3 = r14.get(r12)     // Catch:{ all -> 0x00cc }
            int r12 = r12 + 1
            com.google.android.gms.measurement.internal.zzbh r3 = (com.google.android.gms.measurement.internal.zzbh) r3     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzbh r4 = new com.google.android.gms.measurement.internal.zzbh     // Catch:{ all -> 0x00cc }
            r4.<init>(r3, r10)     // Catch:{ all -> 0x00cc }
            r1.zzc(r4, r0)     // Catch:{ all -> 0x00cc }
            goto L_0x02c3
        L_0x02d6:
            com.google.android.gms.measurement.internal.zzam r0 = r19.zzf()     // Catch:{ all -> 0x00cc }
            r0.zzw()     // Catch:{ all -> 0x00cc }
            com.google.android.gms.measurement.internal.zzam r0 = r19.zzf()
            r0.zzu()
            return
        L_0x02e5:
            com.google.android.gms.measurement.internal.zzam r1 = r19.zzf()
            r1.zzu()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(com.google.android.gms.measurement.internal.zzbh, com.google.android.gms.measurement.internal.zzp):void");
    }

    @WorkerThread
    public final void zza(zzbh zzbh, String str) {
        zzbh zzbh2 = zzbh;
        String str2 = str;
        zzh zze2 = zzf().zze(str2);
        if (zze2 == null || TextUtils.isEmpty(zze2.zzaf())) {
            zzj().zzc().zza("No app data available; dropping event", str2);
            return;
        }
        Boolean zza2 = zza(zze2);
        if (zza2 == null) {
            if (!"_ui".equals(zzbh2.zza)) {
                zzj().zzu().zza("Could not find package. appId", zzgi.zza(str));
            }
        } else if (!zza2.booleanValue()) {
            zzj().zzg().zza("App version does not match; dropping event. appId", zzgi.zza(str));
            return;
        }
        zzp zzp2 = r2;
        zzp zzp3 = new zzp(str, zze2.zzah(), zze2.zzaf(), zze2.zze(), zze2.zzae(), zze2.zzq(), zze2.zzn(), (String) null, zze2.zzar(), false, zze2.zzag(), zze2.zzd(), 0, 0, zze2.zzaq(), false, zze2.zzaa(), zze2.zzx(), zze2.zzo(), zze2.zzan(), (String) null, zzb(str2).zzh(), "", (String) null, zze2.zzat(), zze2.zzw(), zzb(str2).zza(), zzd(str2).zzf(), zze2.zza(), zze2.zzf(), zze2.zzam(), zze2.zzak());
        zzb(zzbh2, zzp2);
    }

    @VisibleForTesting
    private final void zza(zzgn.zzk.zza zza2, long j2, boolean z2) {
        String str;
        zzom zzom;
        String str2;
        if (z2) {
            str = "_se";
        } else {
            str = "_lte";
        }
        zzom zze2 = zzf().zze(zza2.zzt(), str);
        if (zze2 == null || zze2.zze == null) {
            zzom = new zzom(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(j2));
        } else {
            zzom = new zzom(zza2.zzt(), "auto", str, zzb().currentTimeMillis(), Long.valueOf(((Long) zze2.zze).longValue() + j2));
        }
        zzgn.zzo zzo2 = (zzgn.zzo) ((zzlc) zzgn.zzo.zze().zza(str).zzb(zzb().currentTimeMillis()).zza(((Long) zzom.zze).longValue()).zzai());
        int zza3 = zzol.zza(zza2, str);
        if (zza3 >= 0) {
            zza2.zza(zza3, zzo2);
        } else {
            zza2.zza(zzo2);
        }
        if (j2 > 0) {
            zzf().zza(zzom);
            if (z2) {
                str2 = "session-scoped";
            } else {
                str2 = "lifetime";
            }
            zzj().zzp().zza("Updated engagement user property. scope, value", str2, zzom.zze);
        }
    }

    public final void zza(boolean z2) {
        zzac();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x007f A[Catch:{ all -> 0x0105, SQLiteException -> 0x005b, all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d5 A[Catch:{ all -> 0x0105, SQLiteException -> 0x005b, all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0112 A[Catch:{ all -> 0x0105, SQLiteException -> 0x005b, all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0183 A[Catch:{ all -> 0x0105, SQLiteException -> 0x005b, all -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0187 A[Catch:{ all -> 0x0105, SQLiteException -> 0x005b, all -> 0x0017 }] */
    @androidx.annotation.VisibleForTesting
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(boolean r15, int r16, java.lang.Throwable r17, byte[] r18, java.lang.String r19, java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzgn.zzj, com.google.android.gms.measurement.internal.zzns>> r20) {
        /*
            r14 = this;
            r1 = r14
            r0 = r16
            r2 = r17
            r8 = r19
            com.google.android.gms.measurement.internal.zzhp r3 = r14.zzl()
            r3.zzt()
            r14.zzs()
            r9 = 0
            if (r18 != 0) goto L_0x001a
            byte[] r3 = new byte[r9]     // Catch:{ all -> 0x0017 }
            goto L_0x001c
        L_0x0017:
            r0 = move-exception
            goto L_0x022f
        L_0x001a:
            r3 = r18
        L_0x001c:
            java.util.List<java.lang.Long> r4 = r1.zzz     // Catch:{ all -> 0x0017 }
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x0017 }
            r10 = r4
            java.util.List r10 = (java.util.List) r10     // Catch:{ all -> 0x0017 }
            r11 = 0
            r1.zzz = r11     // Catch:{ all -> 0x0017 }
            boolean r4 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ all -> 0x0017 }
            if (r4 == 0) goto L_0x003c
            com.google.android.gms.measurement.internal.zzah r4 = r14.zze()     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r5 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ all -> 0x0017 }
            boolean r4 = r4.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r5)     // Catch:{ all -> 0x0017 }
            if (r4 == 0) goto L_0x003c
            if (r15 == 0) goto L_0x0046
        L_0x003c:
            r4 = 200(0xc8, float:2.8E-43)
            if (r0 == r4) goto L_0x0044
            r4 = 204(0xcc, float:2.86E-43)
            if (r0 != r4) goto L_0x01e8
        L_0x0044:
            if (r2 != 0) goto L_0x01e8
        L_0x0046:
            boolean r2 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ SQLiteException -> 0x005b }
            if (r2 == 0) goto L_0x005e
            com.google.android.gms.measurement.internal.zzah r2 = r14.zze()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ SQLiteException -> 0x005b }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r4)     // Catch:{ SQLiteException -> 0x005b }
            if (r2 == 0) goto L_0x005e
            if (r15 == 0) goto L_0x006d
            goto L_0x005e
        L_0x005b:
            r0 = move-exception
            goto L_0x01bd
        L_0x005e:
            com.google.android.gms.measurement.internal.zzms r2 = r1.zzj     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgz r2 = r2.zzd     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.common.util.Clock r4 = r14.zzb()     // Catch:{ SQLiteException -> 0x005b }
            long r4 = r4.currentTimeMillis()     // Catch:{ SQLiteException -> 0x005b }
            r2.zza(r4)     // Catch:{ SQLiteException -> 0x005b }
        L_0x006d:
            com.google.android.gms.measurement.internal.zzms r2 = r1.zzj     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgz r2 = r2.zze     // Catch:{ SQLiteException -> 0x005b }
            r12 = 0
            r2.zza(r12)     // Catch:{ SQLiteException -> 0x005b }
            r14.zzac()     // Catch:{ SQLiteException -> 0x005b }
            boolean r2 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ SQLiteException -> 0x005b }
            if (r2 == 0) goto L_0x00ac
            com.google.android.gms.measurement.internal.zzah r2 = r14.zze()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r4 = com.google.android.gms.measurement.internal.zzbj.zzcr     // Catch:{ SQLiteException -> 0x005b }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r4)     // Catch:{ SQLiteException -> 0x005b }
            if (r2 == 0) goto L_0x00ac
            if (r15 == 0) goto L_0x008e
            goto L_0x00ac
        L_0x008e:
            boolean r0 = com.google.android.gms.internal.measurement.zzpo.zza()     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x00c2
            com.google.android.gms.measurement.internal.zzah r0 = r14.zze()     // Catch:{ SQLiteException -> 0x005b }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r4)     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x00c2
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ SQLiteException -> 0x005b }
            java.lang.String r2 = "Purged empty bundles"
            r0.zza(r2)     // Catch:{ SQLiteException -> 0x005b }
            goto L_0x00c2
        L_0x00ac:
            com.google.android.gms.measurement.internal.zzgi r2 = r14.zzj()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzp()     // Catch:{ SQLiteException -> 0x005b }
            java.lang.String r4 = "Successful upload. Got network response. code, size"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x005b }
            int r3 = r3.length     // Catch:{ SQLiteException -> 0x005b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ SQLiteException -> 0x005b }
            r2.zza(r4, r0, r3)     // Catch:{ SQLiteException -> 0x005b }
        L_0x00c2:
            com.google.android.gms.measurement.internal.zzam r0 = r14.zzf()     // Catch:{ SQLiteException -> 0x005b }
            r0.zzp()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzah r0 = r14.zze()     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzbz     // Catch:{ all -> 0x0105 }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)     // Catch:{ all -> 0x0105 }
            if (r0 == 0) goto L_0x0108
            java.util.Iterator r0 = r20.iterator()     // Catch:{ all -> 0x0105 }
        L_0x00d9:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0105 }
            if (r2 == 0) goto L_0x0108
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0105 }
            android.util.Pair r2 = (android.util.Pair) r2     // Catch:{ all -> 0x0105 }
            java.lang.Object r3 = r2.first     // Catch:{ all -> 0x0105 }
            r4 = r3
            com.google.android.gms.internal.measurement.zzgn$zzj r4 = (com.google.android.gms.internal.measurement.zzgn.zzj) r4     // Catch:{ all -> 0x0105 }
            java.lang.Object r2 = r2.second     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzns r2 = (com.google.android.gms.measurement.internal.zzns) r2     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzam r3 = r14.zzf()     // Catch:{ all -> 0x0105 }
            java.lang.String r5 = r2.zzb()     // Catch:{ all -> 0x0105 }
            java.util.Map r6 = r2.zzc()     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zznt r7 = r2.zza()     // Catch:{ all -> 0x0105 }
            r2 = r3
            r3 = r19
            r2.zza(r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0105 }
            goto L_0x00d9
        L_0x0105:
            r0 = move-exception
            goto L_0x01b5
        L_0x0108:
            java.util.Iterator r2 = r10.iterator()     // Catch:{ all -> 0x0105 }
        L_0x010c:
            boolean r0 = r2.hasNext()     // Catch:{ all -> 0x0105 }
            if (r0 == 0) goto L_0x0163
            java.lang.Object r0 = r2.next()     // Catch:{ all -> 0x0105 }
            r3 = r0
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzam r4 = r14.zzf()     // Catch:{ SQLiteException -> 0x0156 }
            long r5 = r3.longValue()     // Catch:{ SQLiteException -> 0x0156 }
            r4.zzt()     // Catch:{ SQLiteException -> 0x0156 }
            r4.zzal()     // Catch:{ SQLiteException -> 0x0156 }
            android.database.sqlite.SQLiteDatabase r0 = r4.e_()     // Catch:{ SQLiteException -> 0x0156 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0156 }
            java.lang.String[] r5 = new java.lang.String[]{r5}     // Catch:{ SQLiteException -> 0x0156 }
            java.lang.String r6 = "queue"
            java.lang.String r7 = "rowid=?"
            int r0 = r0.delete(r6, r7, r5)     // Catch:{ SQLiteException -> 0x0147 }
            r5 = 1
            if (r0 != r5) goto L_0x013f
            goto L_0x010c
        L_0x013f:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0147 }
            java.lang.String r5 = "Deleted fewer rows from queue than expected"
            r0.<init>(r5)     // Catch:{ SQLiteException -> 0x0147 }
            throw r0     // Catch:{ SQLiteException -> 0x0147 }
        L_0x0147:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgi r4 = r4.zzj()     // Catch:{ SQLiteException -> 0x0156 }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ SQLiteException -> 0x0156 }
            java.lang.String r5 = "Failed to delete a bundle in a queue table"
            r4.zza(r5, r0)     // Catch:{ SQLiteException -> 0x0156 }
            throw r0     // Catch:{ SQLiteException -> 0x0156 }
        L_0x0156:
            r0 = move-exception
            java.util.List<java.lang.Long> r4 = r1.zzaa     // Catch:{ all -> 0x0105 }
            if (r4 == 0) goto L_0x0162
            boolean r3 = r4.contains(r3)     // Catch:{ all -> 0x0105 }
            if (r3 == 0) goto L_0x0162
            goto L_0x010c
        L_0x0162:
            throw r0     // Catch:{ all -> 0x0105 }
        L_0x0163:
            com.google.android.gms.measurement.internal.zzam r0 = r14.zzf()     // Catch:{ all -> 0x0105 }
            r0.zzw()     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzam r0 = r14.zzf()     // Catch:{ SQLiteException -> 0x005b }
            r0.zzu()     // Catch:{ SQLiteException -> 0x005b }
            r1.zzaa = r11     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzgp r0 = r14.zzh()     // Catch:{ SQLiteException -> 0x005b }
            boolean r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x0187
            boolean r0 = r14.zzad()     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x0187
            r14.zzw()     // Catch:{ SQLiteException -> 0x005b }
            goto L_0x01b2
        L_0x0187:
            com.google.android.gms.measurement.internal.zzah r0 = r14.zze()     // Catch:{ SQLiteException -> 0x005b }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r2 = com.google.android.gms.measurement.internal.zzbj.zzbz     // Catch:{ SQLiteException -> 0x005b }
            boolean r0 = r0.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r2)     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x01ab
            com.google.android.gms.measurement.internal.zzgp r0 = r14.zzh()     // Catch:{ SQLiteException -> 0x005b }
            boolean r0 = r0.zzu()     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x01ab
            com.google.android.gms.measurement.internal.zzam r0 = r14.zzf()     // Catch:{ SQLiteException -> 0x005b }
            boolean r0 = r0.zzs(r8)     // Catch:{ SQLiteException -> 0x005b }
            if (r0 == 0) goto L_0x01ab
            r14.zze((java.lang.String) r8)     // Catch:{ SQLiteException -> 0x005b }
            goto L_0x01b2
        L_0x01ab:
            r2 = -1
            r1.zzab = r2     // Catch:{ SQLiteException -> 0x005b }
            r14.zzac()     // Catch:{ SQLiteException -> 0x005b }
        L_0x01b2:
            r1.zzp = r12     // Catch:{ SQLiteException -> 0x005b }
            goto L_0x0229
        L_0x01b5:
            com.google.android.gms.measurement.internal.zzam r2 = r14.zzf()     // Catch:{ SQLiteException -> 0x005b }
            r2.zzu()     // Catch:{ SQLiteException -> 0x005b }
            throw r0     // Catch:{ SQLiteException -> 0x005b }
        L_0x01bd:
            com.google.android.gms.measurement.internal.zzgi r2 = r14.zzj()     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x0017 }
            java.lang.String r3 = "Database error while trying to delete uploaded bundles"
            r2.zza(r3, r0)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.common.util.Clock r0 = r14.zzb()     // Catch:{ all -> 0x0017 }
            long r2 = r0.elapsedRealtime()     // Catch:{ all -> 0x0017 }
            r1.zzp = r2     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgi r0 = r14.zzj()     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgk r0 = r0.zzp()     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = "Disable upload, time"
            long r3 = r1.zzp     // Catch:{ all -> 0x0017 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0017 }
            r0.zza(r2, r3)     // Catch:{ all -> 0x0017 }
            goto L_0x0229
        L_0x01e8:
            com.google.android.gms.measurement.internal.zzgi r3 = r14.zzj()     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ all -> 0x0017 }
            java.lang.String r4 = "Network upload failed. Will retry later. code, error"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r16)     // Catch:{ all -> 0x0017 }
            r3.zza(r4, r5, r2)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzms r2 = r1.zzj     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgz r2 = r2.zze     // Catch:{ all -> 0x0017 }
            com.google.android.gms.common.util.Clock r3 = r14.zzb()     // Catch:{ all -> 0x0017 }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x0017 }
            r2.zza(r3)     // Catch:{ all -> 0x0017 }
            r2 = 503(0x1f7, float:7.05E-43)
            if (r0 == r2) goto L_0x0210
            r2 = 429(0x1ad, float:6.01E-43)
            if (r0 != r2) goto L_0x021f
        L_0x0210:
            com.google.android.gms.measurement.internal.zzms r0 = r1.zzj     // Catch:{ all -> 0x0017 }
            com.google.android.gms.measurement.internal.zzgz r0 = r0.zzc     // Catch:{ all -> 0x0017 }
            com.google.android.gms.common.util.Clock r2 = r14.zzb()     // Catch:{ all -> 0x0017 }
            long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0017 }
            r0.zza(r2)     // Catch:{ all -> 0x0017 }
        L_0x021f:
            com.google.android.gms.measurement.internal.zzam r0 = r14.zzf()     // Catch:{ all -> 0x0017 }
            r0.zza((java.util.List<java.lang.Long>) r10)     // Catch:{ all -> 0x0017 }
            r14.zzac()     // Catch:{ all -> 0x0017 }
        L_0x0229:
            r1.zzv = r9
            r14.zzaa()
            return
        L_0x022f:
            r1.zzv = r9
            r14.zzaa()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(boolean, int, java.lang.Throwable, byte[], java.lang.String, java.util.List):void");
    }

    @VisibleForTesting
    @WorkerThread
    public final void zza(@NonNull String str, int i3, Throwable th, byte[] bArr, zzog zzog) {
        zzam zzf2;
        zzl().zzt();
        zzs();
        if ((i3 == 200 || i3 == 204) && th == null) {
            if (zzog != null) {
                try {
                    zzf2 = zzf();
                    Long valueOf = Long.valueOf(zzog.zza());
                    zzf2.zzt();
                    zzf2.zzal();
                    Preconditions.checkNotNull(valueOf);
                    if (zzrw.zza()) {
                        if (zzf2.zze().zza(zzbj.zzbz)) {
                        }
                    }
                    if (zzf2.e_().delete("upload_queue", "rowid=?", new String[]{String.valueOf(valueOf)}) != 1) {
                        zzf2.zzj().zzu().zza("Deleted fewer rows from upload_queue than expected");
                    }
                } catch (SQLiteException e3) {
                    zzf2.zzj().zzg().zza("Failed to delete a MeasurementBatch in a upload_queue table", e3);
                    throw e3;
                } catch (Throwable th2) {
                    this.zzv = false;
                    zzaa();
                    throw th2;
                }
            }
            zzj().zzp().zza("Successfully uploaded batch from upload queue. appId, status", str, Integer.valueOf(i3));
            if (!zze().zza(zzbj.zzbz) || !zzh().zzu() || !zzf().zzs(str)) {
                zzac();
            } else {
                zze(str);
            }
        } else {
            zzj().zzp().zza("Network upload failed. Will retry later. appId, status", str, Integer.valueOf(i3));
            if (zzog != null) {
                zzf().zza(Long.valueOf(zzog.zza()));
            }
            zzac();
        }
        this.zzv = false;
        zzaa();
    }

    @WorkerThread
    public final void zza(zzaf zzaf2) {
        zzp zzc2 = zzc((String) Preconditions.checkNotNull(zzaf2.zza));
        if (zzc2 != null) {
            zza(zzaf2, zzc2);
        }
    }

    @WorkerThread
    public final void zza(zzaf zzaf2, zzp zzp2) {
        Preconditions.checkNotNull(zzaf2);
        Preconditions.checkNotEmpty(zzaf2.zza);
        Preconditions.checkNotNull(zzaf2.zzc);
        Preconditions.checkNotEmpty(zzaf2.zzc.zza);
        zzl().zzt();
        zzs();
        if (zzi(zzp2)) {
            if (!zzp2.zzh) {
                zza(zzp2);
                return;
            }
            zzf().zzp();
            try {
                zza(zzp2);
                String str = (String) Preconditions.checkNotNull(zzaf2.zza);
                zzaf zzc2 = zzf().zzc(str, zzaf2.zzc.zza);
                if (zzc2 != null) {
                    zzj().zzc().zza("Removing conditional user property", zzaf2.zza, this.zzm.zzk().zzc(zzaf2.zzc.zza));
                    zzf().zza(str, zzaf2.zzc.zza);
                    if (zzc2.zze) {
                        zzf().zzh(str, zzaf2.zzc.zza);
                    }
                    zzbh zzbh = zzaf2.zzk;
                    if (zzbh != null) {
                        zzbc zzbc = zzbh.zzb;
                        zzc((zzbh) Preconditions.checkNotNull(zzq().zza(str, ((zzbh) Preconditions.checkNotNull(zzaf2.zzk)).zza, zzbc != null ? zzbc.zzb() : null, zzc2.zzb, zzaf2.zzk.zzd, true, true)), zzp2);
                    }
                } else {
                    zzj().zzu().zza("Conditional user property doesn't exist", zzgi.zza(zzaf2.zza), this.zzm.zzk().zzc(zzaf2.zzc.zza));
                }
                zzf().zzw();
                zzf().zzu();
            } catch (Throwable th) {
                zzf().zzu();
                throw th;
            }
        }
    }

    @VisibleForTesting
    private static void zza(zzgn.zzf.zza zza2, @NonNull String str) {
        List<zzgn.zzh> zzf2 = zza2.zzf();
        for (int i3 = 0; i3 < zzf2.size(); i3++) {
            if (str.equals(zzf2.get(i3).zzg())) {
                zza2.zza(i3);
                return;
            }
        }
    }

    @WorkerThread
    public final void zza(String str, zzp zzp2) {
        zzl().zzt();
        zzs();
        if (zzi(zzp2)) {
            if (!zzp2.zzh) {
                zza(zzp2);
                return;
            }
            Boolean zzh2 = zzh(zzp2);
            if (!"_npa".equals(str) || zzh2 == null) {
                zzj().zzc().zza("Removing user property", this.zzm.zzk().zzc(str));
                zzf().zzp();
                try {
                    zza(zzp2);
                    if ("_id".equals(str)) {
                        zzf().zzh((String) Preconditions.checkNotNull(zzp2.zza), "_lair");
                    }
                    zzf().zzh((String) Preconditions.checkNotNull(zzp2.zza), str);
                    zzf().zzw();
                    zzj().zzc().zza("User property removed", this.zzm.zzk().zzc(str));
                    zzf().zzu();
                } catch (Throwable th) {
                    zzf().zzu();
                    throw th;
                }
            } else {
                zzj().zzc().zza("Falling back to manifest metadata value for ad personalization");
                zza(new zzok("_npa", zzb().currentTimeMillis(), Long.valueOf(zzh2.booleanValue() ? 1 : 0), "auto"), zzp2);
            }
        }
    }

    @WorkerThread
    public final void zza(String str, zzlh zzlh) {
        zzl().zzt();
        String str2 = this.zzag;
        if (str2 == null || str2.equals(str) || zzlh != null) {
            this.zzag = str;
            this.zzaf = zzlh;
        }
    }

    @VisibleForTesting
    private final void zza(List<Long> list) {
        Preconditions.checkArgument(!list.isEmpty());
        if (this.zzz != null) {
            zzj().zzg().zza("Set uploading progress before finishing the previous upload");
        } else {
            this.zzz = new ArrayList(list);
        }
    }

    @WorkerThread
    private final void zza(String str, zzjc zzjc) {
        zzl().zzt();
        zzs();
        this.zzac.put(str, zzjc);
        zzf().zzb(str, zzjc);
    }

    @WorkerThread
    private final void zza(String str, boolean z2, Long l2, Long l3) {
        zzh zze2 = zzf().zze(str);
        if (zze2 != null) {
            zze2.zzd(z2);
            zze2.zza(l2);
            zze2.zzb(l3);
            if (zze2.zzas()) {
                zzf().zza(zze2, false, false);
            }
        }
    }

    @WorkerThread
    public final void zza(zzok zzok, zzp zzp2) {
        zzom zze2;
        long j2;
        zzl().zzt();
        zzs();
        if (zzi(zzp2)) {
            if (!zzp2.zzh) {
                zza(zzp2);
                return;
            }
            int zzb2 = zzq().zzb(zzok.zza);
            int i3 = 0;
            if (zzb2 != 0) {
                zzq();
                String str = zzok.zza;
                zze();
                String zza2 = zzop.zza(str, 24, true);
                String str2 = zzok.zza;
                int length = str2 != null ? str2.length() : 0;
                zzq();
                zzop.zza(this.zzah, zzp2.zza, zzb2, "_ev", zza2, length);
                return;
            }
            int zza3 = zzq().zza(zzok.zza, zzok.zza());
            if (zza3 != 0) {
                zzq();
                String str3 = zzok.zza;
                zze();
                String zza4 = zzop.zza(str3, 24, true);
                Object zza5 = zzok.zza();
                if (zza5 != null && ((zza5 instanceof String) || (zza5 instanceof CharSequence))) {
                    i3 = String.valueOf(zza5).length();
                }
                zzq();
                zzop.zza(this.zzah, zzp2.zza, zza3, "_ev", zza4, i3);
                return;
            }
            Object zzc2 = zzq().zzc(zzok.zza, zzok.zza());
            if (zzc2 != null) {
                if ("_sid".equals(zzok.zza)) {
                    long j3 = zzok.zzb;
                    String str4 = zzok.zze;
                    String str5 = (String) Preconditions.checkNotNull(zzp2.zza);
                    zzom zze3 = zzf().zze(str5, "_sno");
                    if (zze3 != null) {
                        Object obj = zze3.zze;
                        if (obj instanceof Long) {
                            j2 = ((Long) obj).longValue();
                            zza(new zzok("_sno", j3, Long.valueOf(j2 + 1), str4), zzp2);
                        }
                    }
                    if (zze3 != null) {
                        zzj().zzu().zza("Retrieved last session number from database does not contain a valid (long) value", zze3.zze);
                    }
                    zzbd zzd2 = zzf().zzd(str5, "_s");
                    if (zzd2 != null) {
                        j2 = zzd2.zzc;
                        zzj().zzp().zza("Backfill the session number. Last used session number", Long.valueOf(j2));
                    } else {
                        j2 = 0;
                    }
                    zza(new zzok("_sno", j3, Long.valueOf(j2 + 1), str4), zzp2);
                }
                zzom zzom = new zzom((String) Preconditions.checkNotNull(zzp2.zza), (String) Preconditions.checkNotNull(zzok.zze), zzok.zza, zzok.zzb, zzc2);
                zzj().zzp().zza("Setting user property", this.zzm.zzk().zzc(zzom.zzc), zzc2);
                zzf().zzp();
                try {
                    if ("_id".equals(zzom.zzc) && (zze2 = zzf().zze(zzp2.zza, "_id")) != null && !zzom.zze.equals(zze2.zze)) {
                        zzf().zzh(zzp2.zza, "_lair");
                    }
                    zza(zzp2);
                    boolean zza6 = zzf().zza(zzom);
                    if ("_sid".equals(zzok.zza)) {
                        long zza7 = zzp().zza(zzp2.zzv);
                        zzh zze4 = zzf().zze(zzp2.zza);
                        if (zze4 != null) {
                            zze4.zzs(zza7);
                            if (zze4.zzas()) {
                                zzf().zza(zze4, false, false);
                            }
                        }
                    }
                    zzf().zzw();
                    if (!zza6) {
                        zzj().zzg().zza("Too many unique user properties are set. Ignoring user property", this.zzm.zzk().zzc(zzom.zzc), zzom.zze);
                        zzq();
                        zzop.zza(this.zzah, zzp2.zza, 9, (String) null, (String) null, 0);
                    }
                    zzf().zzu();
                } catch (Throwable th) {
                    zzf().zzu();
                    throw th;
                }
            }
        }
    }

    @VisibleForTesting
    private final void zza(String str, zzgn.zzh.zza zza2, Bundle bundle, String str2) {
        int zzb2;
        List listOf = CollectionUtils.listOf((T[]) new String[]{"_o", "_sn", "_sc", "_si"});
        if (zzop.zzg(zza2.zzf()) || zzop.zzg(str)) {
            zzb2 = zze().zzb(str2, true);
        } else {
            zzb2 = zze().zza(str2, true);
        }
        long j2 = (long) zzb2;
        long codePointCount = (long) zza2.zzg().codePointCount(0, zza2.zzg().length());
        zzq();
        String zzf2 = zza2.zzf();
        zze();
        String zza3 = zzop.zza(zzf2, 40, true);
        if (codePointCount > j2 && !listOf.contains(zza2.zzf())) {
            if ("_ev".equals(zza2.zzf())) {
                zzq();
                bundle.putString("_ev", zzop.zza(zza2.zzg(), zze().zzb(str2, true), true));
                return;
            }
            zzj().zzv().zza("Param value is too long; discarded. Name, value length", zza3, Long.valueOf(codePointCount));
            if (bundle.getLong("_err") == 0) {
                bundle.putLong("_err", 4);
                if (bundle.getString("_ev") == null) {
                    bundle.putString("_ev", zza3);
                    bundle.putLong("_el", codePointCount);
                }
            }
            bundle.remove(zza2.zzf());
        }
    }

    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v50 */
    /* JADX WARNING: type inference failed for: r10v71 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        r1 = r0;
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        r9 = r43;
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ac, code lost:
        r0 = e;
        r10 = r10;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0232 A[SYNTHETIC, Splitter:B:114:0x0232] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0239 A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0043 A[Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }, ExcHandler: all (r0v7 'th' java.lang.Throwable A[CUSTOM_DECLARE, Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }]), Splitter:B:4:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x058a A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x064f A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x069c A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x0708 A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x080d A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:460:0x0cff A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:461:0x0d24 A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:541:0x0f77 A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:542:0x0f7b A[Catch:{ IOException -> 0x0205, all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:582:0x1093 A[SYNTHETIC, Splitter:B:582:0x1093] */
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(java.lang.String r43, long r44) {
        /*
            r42 = this;
            r1 = r42
            java.lang.String r2 = "_ai"
            java.lang.String r3 = "items"
            com.google.android.gms.measurement.internal.zzam r4 = r42.zzf()
            r4.zzp()
            com.google.android.gms.measurement.internal.zznv$zza r4 = new com.google.android.gms.measurement.internal.zznv$zza     // Catch:{ all -> 0x007f }
            r5 = 0
            r4.<init>()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r6 = r42.zzf()     // Catch:{ all -> 0x007f }
            long r7 = r1.zzab     // Catch:{ all -> 0x007f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x007f }
            r6.zzt()     // Catch:{ all -> 0x007f }
            r6.zzal()     // Catch:{ all -> 0x007f }
            r10 = -1
            r12 = 1
            r13 = 0
            android.database.sqlite.SQLiteDatabase r15 = r6.e_()     // Catch:{ SQLiteException -> 0x00b0, all -> 0x0043 }
            boolean r14 = android.text.TextUtils.isEmpty(r43)     // Catch:{ SQLiteException -> 0x00b0, all -> 0x0043 }
            java.lang.String r16 = ""
            if (r14 == 0) goto L_0x009c
            int r14 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r14 == 0) goto L_0x004f
            java.lang.String r5 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String r9 = java.lang.String.valueOf(r44)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String[] r5 = new java.lang.String[]{r5, r9}     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            goto L_0x0057
        L_0x0043:
            r0 = move-exception
            r1 = r0
            r5 = 0
            goto L_0x1091
        L_0x0048:
            r0 = move-exception
            r9 = r43
            r7 = r0
        L_0x004c:
            r5 = 0
            goto L_0x021f
        L_0x004f:
            java.lang.String r5 = java.lang.String.valueOf(r44)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String[] r5 = new java.lang.String[]{r5}     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
        L_0x0057:
            if (r14 == 0) goto L_0x005b
            java.lang.String r16 = "rowid <= ? and "
        L_0x005b:
            r9 = r16
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String r10 = "select app_id, metadata_fingerprint from raw_events where "
            r14.<init>(r10)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            r14.append(r9)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String r9 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r14.append(r9)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            java.lang.String r9 = r14.toString()     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            android.database.Cursor r5 = r15.rawQuery(r9, r5)     // Catch:{ SQLiteException -> 0x0048, all -> 0x0043 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0098 }
            if (r9 != 0) goto L_0x0083
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x007f:
            r0 = move-exception
            r1 = r0
            goto L_0x1097
        L_0x0083:
            java.lang.String r9 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x0098 }
            java.lang.String r10 = r5.getString(r12)     // Catch:{ SQLiteException -> 0x0094 }
            r5.close()     // Catch:{ SQLiteException -> 0x0094 }
            goto L_0x00ee
        L_0x0090:
            r0 = move-exception
            r1 = r0
            goto L_0x1091
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            r7 = r0
            goto L_0x021f
        L_0x0098:
            r0 = move-exception
            r9 = r43
            goto L_0x0095
        L_0x009c:
            r9 = r10
            int r5 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r5 == 0) goto L_0x00b4
            java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x00b0, all -> 0x0043 }
            r10 = r43
            java.lang.String[] r9 = new java.lang.String[]{r10, r9}     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            goto L_0x00ba
        L_0x00ac:
            r0 = move-exception
        L_0x00ad:
            r7 = r0
            r9 = r10
            goto L_0x004c
        L_0x00b0:
            r0 = move-exception
            r10 = r43
            goto L_0x00ad
        L_0x00b4:
            r10 = r43
            java.lang.String[] r9 = new java.lang.String[]{r43}     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
        L_0x00ba:
            if (r5 == 0) goto L_0x00be
            java.lang.String r16 = " and rowid <= ?"
        L_0x00be:
            r5 = r16
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            java.lang.String r14 = "select metadata_fingerprint from raw_events where app_id = ?"
            r11.<init>(r14)     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            r11.append(r5)     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            java.lang.String r5 = " order by rowid limit 1;"
            r11.append(r5)     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            java.lang.String r5 = r11.toString()     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            android.database.Cursor r5 = r15.rawQuery(r5, r9)     // Catch:{ SQLiteException -> 0x00ac, all -> 0x0043 }
            boolean r9 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x021c }
            if (r9 != 0) goto L_0x00e2
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x00e2:
            java.lang.String r9 = r5.getString(r13)     // Catch:{ SQLiteException -> 0x021c }
            r5.close()     // Catch:{ SQLiteException -> 0x021c }
            r41 = r10
            r10 = r9
            r9 = r41
        L_0x00ee:
            java.lang.String r11 = "raw_events_metadata"
            java.lang.String r14 = "metadata"
            java.lang.String[] r16 = new java.lang.String[]{r14}     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r17 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r18 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "2"
            r19 = 0
            r20 = 0
            r14 = r15
            r23 = r15
            r15 = r11
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x0094 }
            boolean r11 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0094 }
            if (r11 != 0) goto L_0x0128
            com.google.android.gms.measurement.internal.zzgi r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzg()     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r8 = "Raw event metadata record is missing. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0094 }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x0094 }
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x0128:
            byte[] r11 = r5.getBlob(r13)     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r14 = com.google.android.gms.internal.measurement.zzgn.zzk.zzw()     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzmk r11 = com.google.android.gms.measurement.internal.zzol.zza(r14, (byte[]) r11)     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r11 = (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r11     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzml r11 = r11.zzai()     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ IOException -> 0x0205 }
            com.google.android.gms.internal.measurement.zzgn$zzk r11 = (com.google.android.gms.internal.measurement.zzgn.zzk) r11     // Catch:{ IOException -> 0x0205 }
            boolean r14 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0094 }
            if (r14 == 0) goto L_0x0155
            com.google.android.gms.measurement.internal.zzgi r14 = r6.zzj()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.measurement.internal.zzgk r14 = r14.zzu()     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r15 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0094 }
            r14.zza(r15, r12)     // Catch:{ SQLiteException -> 0x0094 }
        L_0x0155:
            r5.close()     // Catch:{ SQLiteException -> 0x0094 }
            r4.zza(r11)     // Catch:{ SQLiteException -> 0x0094 }
            r11 = -1
            int r14 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r14 == 0) goto L_0x0170
            java.lang.String r11 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String[] r7 = new java.lang.String[]{r9, r10, r7}     // Catch:{ SQLiteException -> 0x0094 }
            r18 = r7
            r17 = r11
            goto L_0x017a
        L_0x0170:
            java.lang.String r7 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r8 = new java.lang.String[]{r9, r10}     // Catch:{ SQLiteException -> 0x0094 }
            r17 = r7
            r18 = r8
        L_0x017a:
            java.lang.String r15 = "raw_events"
            java.lang.String r7 = "rowid"
            java.lang.String r8 = "name"
            java.lang.String r10 = "timestamp"
            java.lang.String r11 = "data"
            java.lang.String[] r16 = new java.lang.String[]{r7, r8, r10, r11}     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r21 = "rowid"
            r22 = 0
            r19 = 0
            r20 = 0
            r14 = r23
            android.database.Cursor r5 = r14.query(r15, r16, r17, r18, r19, r20, r21, r22)     // Catch:{ SQLiteException -> 0x0094 }
            boolean r7 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0094 }
            if (r7 != 0) goto L_0x01b2
            com.google.android.gms.measurement.internal.zzgi r7 = r6.zzj()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzu()     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r8 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0094 }
            r7.zza(r8, r10)     // Catch:{ SQLiteException -> 0x0094 }
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x01b2:
            long r7 = r5.getLong(r13)     // Catch:{ SQLiteException -> 0x0094 }
            r10 = 3
            byte[] r11 = r5.getBlob(r10)     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r10 = com.google.android.gms.internal.measurement.zzgn.zzf.zze()     // Catch:{ IOException -> 0x01e8 }
            com.google.android.gms.internal.measurement.zzmk r10 = com.google.android.gms.measurement.internal.zzol.zza(r10, (byte[]) r11)     // Catch:{ IOException -> 0x01e8 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10     // Catch:{ IOException -> 0x01e8 }
            r11 = 1
            java.lang.String r12 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r11 = r10.zza((java.lang.String) r12)     // Catch:{ SQLiteException -> 0x0094 }
            r12 = 2
            long r14 = r5.getLong(r12)     // Catch:{ SQLiteException -> 0x0094 }
            r11.zzb((long) r14)     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzml r10 = r10.zzai()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzlc r10 = (com.google.android.gms.internal.measurement.zzlc) r10     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.internal.measurement.zzgn$zzf r10 = (com.google.android.gms.internal.measurement.zzgn.zzf) r10     // Catch:{ SQLiteException -> 0x0094 }
            boolean r7 = r4.zza(r7, r10)     // Catch:{ SQLiteException -> 0x0094 }
            if (r7 != 0) goto L_0x01fb
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x01e8:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzgi r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r10 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0094 }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x0094 }
        L_0x01fb:
            boolean r7 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0094 }
            if (r7 != 0) goto L_0x01b2
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x0205:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.measurement.internal.zzgi r8 = r6.zzj()     // Catch:{ SQLiteException -> 0x0094 }
            com.google.android.gms.measurement.internal.zzgk r8 = r8.zzg()     // Catch:{ SQLiteException -> 0x0094 }
            java.lang.String r10 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ SQLiteException -> 0x0094 }
            r8.zza(r10, r11, r7)     // Catch:{ SQLiteException -> 0x0094 }
            r5.close()     // Catch:{ all -> 0x007f }
            goto L_0x0235
        L_0x021c:
            r0 = move-exception
            r7 = r0
            r9 = r10
        L_0x021f:
            com.google.android.gms.measurement.internal.zzgi r6 = r6.zzj()     // Catch:{ all -> 0x0090 }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzg()     // Catch:{ all -> 0x0090 }
            java.lang.String r8 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r9)     // Catch:{ all -> 0x0090 }
            r6.zza(r8, r9, r7)     // Catch:{ all -> 0x0090 }
            if (r5 == 0) goto L_0x0235
            r5.close()     // Catch:{ all -> 0x007f }
        L_0x0235:
            java.util.List<com.google.android.gms.internal.measurement.zzgn$zzf> r5 = r4.zzc     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x1081
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0241
            goto L_0x1081
        L_0x0241:
            com.google.android.gms.internal.measurement.zzgn$zzk r5 = r4.zza     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r5 = r5.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r5 = (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r5 = r5.zzl()     // Catch:{ all -> 0x007f }
            r9 = r13
            r10 = r9
            r11 = r10
            r7 = 0
            r8 = 0
            r12 = -1
            r14 = -1
        L_0x0254:
            java.util.List<com.google.android.gms.internal.measurement.zzgn$zzf> r15 = r4.zzc     // Catch:{ all -> 0x007f }
            int r15 = r15.size()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = "_et"
            java.lang.String r6 = "_fr"
            r17 = r10
            java.lang.String r10 = "_e"
            r44 = r11
            java.lang.String r11 = "_c"
            r18 = r12
            r45 = r13
            if (r9 >= r15) goto L_0x0831
            java.util.List<com.google.android.gms.internal.measurement.zzgn$zzf> r15 = r4.zzc     // Catch:{ all -> 0x007f }
            java.lang.Object r15 = r15.get(r9)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r15 = (com.google.android.gms.internal.measurement.zzgn.zzf) r15     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r15 = r15.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r15 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzhg r12 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r13 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x007f }
            r21 = r9
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r9 = r12.zzd(r13, r9)     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "_err"
            if (r9 == 0) goto L_0x0307
            com.google.android.gms.measurement.internal.zzgi r6 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = "Dropping blocked raw event. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r10 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r10)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzhw r11 = r1.zzm     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgh r11 = r11.zzk()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r15.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r11 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x007f }
            r6.zza(r9, r10, r11)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzhg r6 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            boolean r6 = r6.zzm(r9)     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x02fc
            com.google.android.gms.measurement.internal.zzhg r6 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            boolean r6 = r6.zzo(r9)     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x02d8
            goto L_0x02fc
        L_0x02d8:
            java.lang.String r6 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r6 = r12.equals(r6)     // Catch:{ all -> 0x007f }
            if (r6 != 0) goto L_0x02fc
            r42.zzq()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzoo r6 = r1.zzah     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r25 = r9.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r27 = "_ev"
            java.lang.String r28 = r15.zze()     // Catch:{ all -> 0x007f }
            r29 = 0
            r26 = 11
            r24 = r6
            com.google.android.gms.measurement.internal.zzop.zza((com.google.android.gms.measurement.internal.zzoo) r24, (java.lang.String) r25, (int) r26, (java.lang.String) r27, (java.lang.String) r28, (int) r29)     // Catch:{ all -> 0x007f }
        L_0x02fc:
            r11 = r44
            r23 = r2
            r10 = r3
            r12 = r18
            r13 = r21
            goto L_0x0827
        L_0x0307:
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = com.google.android.gms.measurement.internal.zzjf.zza(r2)     // Catch:{ all -> 0x007f }
            boolean r9 = r9.equals(r13)     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x0379
            r15.zza((java.lang.String) r2)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgi r9 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r9 = r9.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = "Renaming ad_impression to _ai"
            r9.zza(r13)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgi r9 = r42.zzj()     // Catch:{ all -> 0x007f }
            r13 = 5
            boolean r9 = r9.zza((int) r13)     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x0379
            r9 = 0
        L_0x0331:
            int r13 = r15.zza()     // Catch:{ all -> 0x007f }
            if (r9 >= r13) goto L_0x0379
            java.lang.String r13 = "ad_platform"
            com.google.android.gms.internal.measurement.zzgn$zzh r22 = r15.zzb((int) r9)     // Catch:{ all -> 0x007f }
            r23 = r2
            java.lang.String r2 = r22.zzg()     // Catch:{ all -> 0x007f }
            boolean r2 = r13.equals(r2)     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0374
            com.google.android.gms.internal.measurement.zzgn$zzh r2 = r15.zzb((int) r9)     // Catch:{ all -> 0x007f }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x007f }
            boolean r2 = r2.isEmpty()     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0374
            java.lang.String r2 = "admob"
            com.google.android.gms.internal.measurement.zzgn$zzh r13 = r15.zzb((int) r9)     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r13.zzh()     // Catch:{ all -> 0x007f }
            boolean r2 = r2.equalsIgnoreCase(r13)     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0374
            com.google.android.gms.measurement.internal.zzgi r2 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzv()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = "AdMob ad impression logged from app. Potentially duplicative."
            r2.zza(r13)     // Catch:{ all -> 0x007f }
        L_0x0374:
            int r9 = r9 + 1
            r2 = r23
            goto L_0x0331
        L_0x0379:
            r23 = r2
            com.google.android.gms.measurement.internal.zzhg r2 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r2 = r2.zzc((java.lang.String) r9, (java.lang.String) r13)     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x03b7
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)     // Catch:{ all -> 0x007f }
            int r13 = r9.hashCode()     // Catch:{ all -> 0x007f }
            r22 = r3
            r3 = 95027(0x17333, float:1.33161E-40)
            if (r13 == r3) goto L_0x03a5
            goto L_0x03ae
        L_0x03a5:
            java.lang.String r3 = "_ui"
            boolean r3 = r9.equals(r3)     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x03ae
            goto L_0x03b9
        L_0x03ae:
            r25 = r5
            r24 = r7
            r26 = r8
            r9 = r14
            goto L_0x0588
        L_0x03b7:
            r22 = r3
        L_0x03b9:
            r24 = r7
            r3 = 0
            r9 = 0
            r13 = 0
        L_0x03be:
            int r7 = r15.zza()     // Catch:{ all -> 0x007f }
            r25 = r5
            java.lang.String r5 = "_r"
            if (r3 >= r7) goto L_0x0428
            com.google.android.gms.internal.measurement.zzgn$zzh r7 = r15.zzb((int) r3)     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x007f }
            boolean r7 = r11.equals(r7)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x03f5
            com.google.android.gms.internal.measurement.zzgn$zzh r5 = r15.zzb((int) r3)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r5 = r5.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r5     // Catch:{ all -> 0x007f }
            r26 = r8
            r7 = 1
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = r5.zza((long) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r5 = r5.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r5 = (com.google.android.gms.internal.measurement.zzlc) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r5 = (com.google.android.gms.internal.measurement.zzgn.zzh) r5     // Catch:{ all -> 0x007f }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzgn.zzh) r5)     // Catch:{ all -> 0x007f }
            r9 = 1
            goto L_0x0421
        L_0x03f5:
            r26 = r8
            com.google.android.gms.internal.measurement.zzgn$zzh r7 = r15.zzb((int) r3)     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x007f }
            boolean r5 = r5.equals(r7)     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0421
            com.google.android.gms.internal.measurement.zzgn$zzh r5 = r15.zzb((int) r3)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r5 = r5.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r5     // Catch:{ all -> 0x007f }
            r7 = 1
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = r5.zza((long) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r5 = r5.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r5 = (com.google.android.gms.internal.measurement.zzlc) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r5 = (com.google.android.gms.internal.measurement.zzgn.zzh) r5     // Catch:{ all -> 0x007f }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzgn.zzh) r5)     // Catch:{ all -> 0x007f }
            r13 = 1
        L_0x0421:
            int r3 = r3 + 1
            r5 = r25
            r8 = r26
            goto L_0x03be
        L_0x0428:
            r26 = r8
            if (r9 != 0) goto L_0x045a
            if (r2 == 0) goto L_0x045a
            com.google.android.gms.measurement.internal.zzgi r3 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r7 = "Marking event as conversion"
            com.google.android.gms.measurement.internal.zzhw r8 = r1.zzm     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgh r8 = r8.zzk()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zza((java.lang.String) r9)     // Catch:{ all -> 0x007f }
            r3.zza(r7, r8)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = com.google.android.gms.internal.measurement.zzgn.zzh.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = r3.zza((java.lang.String) r11)     // Catch:{ all -> 0x007f }
            r7 = 1
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = r3.zza((long) r7)     // Catch:{ all -> 0x007f }
            r15.zza((com.google.android.gms.internal.measurement.zzgn.zzh.zza) r3)     // Catch:{ all -> 0x007f }
        L_0x045a:
            if (r13 != 0) goto L_0x0488
            com.google.android.gms.measurement.internal.zzgi r3 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r7 = "Marking event as real-time"
            com.google.android.gms.measurement.internal.zzhw r8 = r1.zzm     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgh r8 = r8.zzk()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r15.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zza((java.lang.String) r9)     // Catch:{ all -> 0x007f }
            r3.zza(r7, r8)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = com.google.android.gms.internal.measurement.zzgn.zzh.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = r3.zza((java.lang.String) r5)     // Catch:{ all -> 0x007f }
            r7 = 1
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = r3.zza((long) r7)     // Catch:{ all -> 0x007f }
            r15.zza((com.google.android.gms.internal.measurement.zzgn.zzh.zza) r3)     // Catch:{ all -> 0x007f }
        L_0x0488:
            com.google.android.gms.measurement.internal.zzam r27 = r42.zzf()     // Catch:{ all -> 0x007f }
            long r28 = r42.zzx()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r3 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r30 = r3.zzz()     // Catch:{ all -> 0x007f }
            r35 = 1
            r36 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            com.google.android.gms.measurement.internal.zzar r3 = r27.zza(r28, r30, r31, r32, r33, r34, r35, r36)     // Catch:{ all -> 0x007f }
            long r7 = r3.zze     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzah r3 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            int r3 = r3.zzc(r9)     // Catch:{ all -> 0x007f }
            r9 = r14
            long r13 = (long) r3     // Catch:{ all -> 0x007f }
            int r3 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r3 <= 0) goto L_0x04c0
            zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (java.lang.String) r5)     // Catch:{ all -> 0x007f }
            goto L_0x04c2
        L_0x04c0:
            r17 = 1
        L_0x04c2:
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r3 = com.google.android.gms.measurement.internal.zzop.zzh(r3)     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0588
            if (r2 == 0) goto L_0x0588
            com.google.android.gms.measurement.internal.zzam r27 = r42.zzf()     // Catch:{ all -> 0x007f }
            long r28 = r42.zzx()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r3 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r30 = r3.zzz()     // Catch:{ all -> 0x007f }
            r35 = 0
            r36 = 0
            r31 = 0
            r32 = 0
            r33 = 1
            r34 = 0
            com.google.android.gms.measurement.internal.zzar r3 = r27.zza(r28, r30, r31, r32, r33, r34, r35, r36)     // Catch:{ all -> 0x007f }
            long r7 = r3.zzc     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzah r3 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r5 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r13 = com.google.android.gms.measurement.internal.zzbj.zzn     // Catch:{ all -> 0x007f }
            int r3 = r3.zzb((java.lang.String) r5, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r13)     // Catch:{ all -> 0x007f }
            long r13 = (long) r3     // Catch:{ all -> 0x007f }
            int r3 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r3 <= 0) goto L_0x0588
            com.google.android.gms.measurement.internal.zzgi r3 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r7)     // Catch:{ all -> 0x007f }
            r3.zza(r5, r7)     // Catch:{ all -> 0x007f }
            r3 = -1
            r5 = 0
            r7 = 0
            r8 = 0
        L_0x051e:
            int r13 = r15.zza()     // Catch:{ all -> 0x007f }
            if (r5 >= r13) goto L_0x0549
            com.google.android.gms.internal.measurement.zzgn$zzh r13 = r15.zzb((int) r5)     // Catch:{ all -> 0x007f }
            java.lang.String r14 = r13.zzg()     // Catch:{ all -> 0x007f }
            boolean r14 = r11.equals(r14)     // Catch:{ all -> 0x007f }
            if (r14 == 0) goto L_0x053b
            com.google.android.gms.internal.measurement.zzlc$zzb r3 = r13.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r3 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r3     // Catch:{ all -> 0x007f }
            r7 = r3
            r3 = r5
            goto L_0x0546
        L_0x053b:
            java.lang.String r13 = r13.zzg()     // Catch:{ all -> 0x007f }
            boolean r13 = r12.equals(r13)     // Catch:{ all -> 0x007f }
            if (r13 == 0) goto L_0x0546
            r8 = 1
        L_0x0546:
            int r5 = r5 + 1
            goto L_0x051e
        L_0x0549:
            if (r8 == 0) goto L_0x0551
            if (r7 == 0) goto L_0x0551
            r15.zza((int) r3)     // Catch:{ all -> 0x007f }
            goto L_0x0588
        L_0x0551:
            if (r7 == 0) goto L_0x0571
            java.lang.Object r5 = r7.clone()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r5 = (com.google.android.gms.internal.measurement.zzlc.zzb) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = r5.zza((java.lang.String) r12)     // Catch:{ all -> 0x007f }
            r7 = 10
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r5 = r5.zza((long) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r5 = r5.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r5 = (com.google.android.gms.internal.measurement.zzlc) r5     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r5 = (com.google.android.gms.internal.measurement.zzgn.zzh) r5     // Catch:{ all -> 0x007f }
            r15.zza((int) r3, (com.google.android.gms.internal.measurement.zzgn.zzh) r5)     // Catch:{ all -> 0x007f }
            goto L_0x0588
        L_0x0571:
            com.google.android.gms.measurement.internal.zzgi r3 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r3 = r3.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r7)     // Catch:{ all -> 0x007f }
            r3.zza(r5, r7)     // Catch:{ all -> 0x007f }
        L_0x0588:
            if (r2 == 0) goto L_0x05f9
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x007f }
            java.util.List r3 = r15.zzf()     // Catch:{ all -> 0x007f }
            r2.<init>(r3)     // Catch:{ all -> 0x007f }
            r3 = 0
            r5 = -1
            r7 = -1
        L_0x0596:
            int r8 = r2.size()     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "currency"
            java.lang.String r13 = "value"
            if (r3 >= r8) goto L_0x05c6
            java.lang.Object r8 = r2.get(r3)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r8 = (com.google.android.gms.internal.measurement.zzgn.zzh) r8     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzg()     // Catch:{ all -> 0x007f }
            boolean r8 = r13.equals(r8)     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x05b2
            r5 = r3
            goto L_0x05c3
        L_0x05b2:
            java.lang.Object r8 = r2.get(r3)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r8 = (com.google.android.gms.internal.measurement.zzgn.zzh) r8     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzg()     // Catch:{ all -> 0x007f }
            boolean r8 = r12.equals(r8)     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x05c3
            r7 = r3
        L_0x05c3:
            int r3 = r3 + 1
            goto L_0x0596
        L_0x05c6:
            r3 = -1
            if (r5 == r3) goto L_0x05fa
            java.lang.Object r3 = r2.get(r5)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r3 = (com.google.android.gms.internal.measurement.zzgn.zzh) r3     // Catch:{ all -> 0x007f }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x007f }
            if (r3 != 0) goto L_0x05fc
            java.lang.Object r3 = r2.get(r5)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r3 = (com.google.android.gms.internal.measurement.zzgn.zzh) r3     // Catch:{ all -> 0x007f }
            boolean r3 = r3.zzj()     // Catch:{ all -> 0x007f }
            if (r3 != 0) goto L_0x05fc
            com.google.android.gms.measurement.internal.zzgi r2 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzv()     // Catch:{ all -> 0x007f }
            java.lang.String r3 = "Value must be specified with a numeric type."
            r2.zza(r3)     // Catch:{ all -> 0x007f }
            r15.zza((int) r5)     // Catch:{ all -> 0x007f }
            zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x007f }
            r2 = 18
            zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (int) r2, (java.lang.String) r13)     // Catch:{ all -> 0x007f }
        L_0x05f9:
            r3 = -1
        L_0x05fa:
            r8 = 3
            goto L_0x0643
        L_0x05fc:
            r3 = -1
            if (r7 != r3) goto L_0x0601
            r8 = 3
            goto L_0x0624
        L_0x0601:
            java.lang.Object r2 = r2.get(r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r2 = (com.google.android.gms.internal.measurement.zzgn.zzh) r2     // Catch:{ all -> 0x007f }
            java.lang.String r2 = r2.zzh()     // Catch:{ all -> 0x007f }
            int r7 = r2.length()     // Catch:{ all -> 0x007f }
            r8 = 3
            if (r7 == r8) goto L_0x0613
            goto L_0x0624
        L_0x0613:
            r7 = 0
        L_0x0614:
            int r13 = r2.length()     // Catch:{ all -> 0x007f }
            if (r7 >= r13) goto L_0x0643
            int r13 = r2.codePointAt(r7)     // Catch:{ all -> 0x007f }
            boolean r14 = java.lang.Character.isLetter(r13)     // Catch:{ all -> 0x007f }
            if (r14 != 0) goto L_0x063d
        L_0x0624:
            com.google.android.gms.measurement.internal.zzgi r2 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzv()     // Catch:{ all -> 0x007f }
            java.lang.String r7 = "Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter."
            r2.zza(r7)     // Catch:{ all -> 0x007f }
            r15.zza((int) r5)     // Catch:{ all -> 0x007f }
            zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (java.lang.String) r11)     // Catch:{ all -> 0x007f }
            r2 = 19
            zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (int) r2, (java.lang.String) r12)     // Catch:{ all -> 0x007f }
            goto L_0x0643
        L_0x063d:
            int r13 = java.lang.Character.charCount(r13)     // Catch:{ all -> 0x007f }
            int r7 = r7 + r13
            goto L_0x0614
        L_0x0643:
            java.lang.String r2 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r2 = r10.equals(r2)     // Catch:{ all -> 0x007f }
            r10 = 1000(0x3e8, double:4.94E-321)
            if (r2 == 0) goto L_0x069c
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r2 = r15.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r2 = (com.google.android.gms.internal.measurement.zzlc) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r2 = (com.google.android.gms.internal.measurement.zzgn.zzf) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r2 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r2, (java.lang.String) r6)     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0697
            if (r26 == 0) goto L_0x068f
            long r5 = r26.zzc()     // Catch:{ all -> 0x007f }
            long r12 = r15.zzc()     // Catch:{ all -> 0x007f }
            long r5 = r5 - r12
            long r5 = java.lang.Math.abs(r5)     // Catch:{ all -> 0x007f }
            int r2 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r2 > 0) goto L_0x068f
            java.lang.Object r2 = r26.clone()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r2 = (com.google.android.gms.internal.measurement.zzlc.zzb) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2     // Catch:{ all -> 0x007f }
            boolean r5 = r1.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2)     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x068f
            r5 = r25
            r5.zza((int) r9, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2)     // Catch:{ all -> 0x007f }
            r14 = r9
            r12 = r18
        L_0x0689:
            r24 = 0
            r26 = 0
            goto L_0x06f0
        L_0x068f:
            r5 = r25
            r12 = r44
            r14 = r9
            r24 = r15
            goto L_0x06f0
        L_0x0697:
            r5 = r25
        L_0x0699:
            r6 = r18
            goto L_0x06ee
        L_0x069c:
            r5 = r25
            java.lang.String r2 = "_vs"
            java.lang.String r6 = r15.zze()     // Catch:{ all -> 0x007f }
            boolean r2 = r2.equals(r6)     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0699
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r2 = r15.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r2 = (com.google.android.gms.internal.measurement.zzlc) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r2 = (com.google.android.gms.internal.measurement.zzgn.zzf) r2     // Catch:{ all -> 0x007f }
            r7 = r45
            com.google.android.gms.internal.measurement.zzgn$zzh r2 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r2, (java.lang.String) r7)     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0699
            if (r24 == 0) goto L_0x06e6
            long r6 = r24.zzc()     // Catch:{ all -> 0x007f }
            long r12 = r15.zzc()     // Catch:{ all -> 0x007f }
            long r6 = r6 - r12
            long r6 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x007f }
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 > 0) goto L_0x06e6
            java.lang.Object r2 = r24.clone()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r2 = (com.google.android.gms.internal.measurement.zzlc.zzb) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r2 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2     // Catch:{ all -> 0x007f }
            boolean r6 = r1.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15)     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x06e6
            r6 = r18
            r5.zza((int) r6, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r2)     // Catch:{ all -> 0x007f }
            r12 = r6
            r14 = r9
            goto L_0x0689
        L_0x06e6:
            r6 = r18
            r14 = r44
            r12 = r6
            r26 = r15
            goto L_0x06f0
        L_0x06ee:
            r12 = r6
            r14 = r9
        L_0x06f0:
            boolean r2 = com.google.android.gms.internal.measurement.zzqy.zza()     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x080d
            com.google.android.gms.measurement.internal.zzah r2 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzcu     // Catch:{ all -> 0x007f }
            boolean r2 = r2.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x080d
            int r2 = r15.zza()     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x080d
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.util.List r2 = r15.zzf()     // Catch:{ all -> 0x007f }
            android.os.Bundle r2 = com.google.android.gms.measurement.internal.zzol.zza((java.util.List<com.google.android.gms.internal.measurement.zzgn.zzh>) r2)     // Catch:{ all -> 0x007f }
            r6 = 0
        L_0x0714:
            int r7 = r15.zza()     // Catch:{ all -> 0x007f }
            if (r6 >= r7) goto L_0x07bb
            com.google.android.gms.internal.measurement.zzgn$zzh r7 = r15.zzb((int) r6)     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r7.zzg()     // Catch:{ all -> 0x007f }
            r10 = r22
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x0796
            java.util.List r9 = r7.zzi()     // Catch:{ all -> 0x007f }
            boolean r9 = r9.isEmpty()     // Catch:{ all -> 0x007f }
            if (r9 != 0) goto L_0x0796
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            java.util.List r7 = r7.zzi()     // Catch:{ all -> 0x007f }
            int r11 = r7.size()     // Catch:{ all -> 0x007f }
            android.os.Bundle[] r11 = new android.os.Bundle[r11]     // Catch:{ all -> 0x007f }
            r13 = 0
        L_0x0745:
            int r3 = r7.size()     // Catch:{ all -> 0x007f }
            if (r13 >= r3) goto L_0x0792
            java.lang.Object r3 = r7.get(r13)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r3 = (com.google.android.gms.internal.measurement.zzgn.zzh) r3     // Catch:{ all -> 0x007f }
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.util.List r18 = r3.zzi()     // Catch:{ all -> 0x007f }
            android.os.Bundle r8 = com.google.android.gms.measurement.internal.zzol.zza((java.util.List<com.google.android.gms.internal.measurement.zzgn.zzh>) r18)     // Catch:{ all -> 0x007f }
            java.util.List r3 = r3.zzi()     // Catch:{ all -> 0x007f }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x007f }
        L_0x0764:
            boolean r18 = r3.hasNext()     // Catch:{ all -> 0x007f }
            if (r18 == 0) goto L_0x0788
            java.lang.Object r18 = r3.next()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r18 = (com.google.android.gms.internal.measurement.zzgn.zzh) r18     // Catch:{ all -> 0x007f }
            r45 = r3
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r18 = r18.zzcd()     // Catch:{ all -> 0x007f }
            r19 = r7
            r7 = r18
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r7 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r7     // Catch:{ all -> 0x007f }
            r1.zza((java.lang.String) r3, (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r7, (android.os.Bundle) r8, (java.lang.String) r9)     // Catch:{ all -> 0x007f }
            r3 = r45
            r7 = r19
            goto L_0x0764
        L_0x0788:
            r19 = r7
            r11[r13] = r8     // Catch:{ all -> 0x007f }
            int r13 = r13 + 1
            r7 = r19
            r8 = 3
            goto L_0x0745
        L_0x0792:
            r2.putParcelableArray(r10, r11)     // Catch:{ all -> 0x007f }
            goto L_0x07b3
        L_0x0796:
            java.lang.String r3 = r7.zzg()     // Catch:{ all -> 0x007f }
            boolean r3 = r3.equals(r10)     // Catch:{ all -> 0x007f }
            if (r3 != 0) goto L_0x07b3
            java.lang.String r3 = r15.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r7 = r7.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r7 = (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r7     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            r1.zza((java.lang.String) r3, (com.google.android.gms.internal.measurement.zzgn.zzh.zza) r7, (android.os.Bundle) r2, (java.lang.String) r8)     // Catch:{ all -> 0x007f }
        L_0x07b3:
            int r6 = r6 + 1
            r22 = r10
            r3 = -1
            r8 = 3
            goto L_0x0714
        L_0x07bb:
            r10 = r22
            r15.zzd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol r3 = r42.zzp()     // Catch:{ all -> 0x007f }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x007f }
            r6.<init>()     // Catch:{ all -> 0x007f }
            java.util.Set r7 = r2.keySet()     // Catch:{ all -> 0x007f }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ all -> 0x007f }
        L_0x07d1:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x07fa
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r9 = com.google.android.gms.internal.measurement.zzgn.zzh.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r9 = r9.zza((java.lang.String) r8)     // Catch:{ all -> 0x007f }
            java.lang.Object r8 = r2.get(r8)     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x07d1
            r3.zza((com.google.android.gms.internal.measurement.zzgn.zzh.zza) r9, (java.lang.Object) r8)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r8 = r9.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r8 = (com.google.android.gms.internal.measurement.zzlc) r8     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r8 = (com.google.android.gms.internal.measurement.zzgn.zzh) r8     // Catch:{ all -> 0x007f }
            r6.add(r8)     // Catch:{ all -> 0x007f }
            goto L_0x07d1
        L_0x07fa:
            int r2 = r6.size()     // Catch:{ all -> 0x007f }
            r3 = 0
        L_0x07ff:
            if (r3 >= r2) goto L_0x080f
            java.lang.Object r7 = r6.get(r3)     // Catch:{ all -> 0x007f }
            int r3 = r3 + 1
            com.google.android.gms.internal.measurement.zzgn$zzh r7 = (com.google.android.gms.internal.measurement.zzgn.zzh) r7     // Catch:{ all -> 0x007f }
            r15.zza((com.google.android.gms.internal.measurement.zzgn.zzh) r7)     // Catch:{ all -> 0x007f }
            goto L_0x07ff
        L_0x080d:
            r10 = r22
        L_0x080f:
            java.util.List<com.google.android.gms.internal.measurement.zzgn$zzf> r2 = r4.zzc     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r3 = r15.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r3 = (com.google.android.gms.internal.measurement.zzlc) r3     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r3 = (com.google.android.gms.internal.measurement.zzgn.zzf) r3     // Catch:{ all -> 0x007f }
            r13 = r21
            r2.set(r13, r3)     // Catch:{ all -> 0x007f }
            int r11 = r44 + 1
            r5.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r15)     // Catch:{ all -> 0x007f }
            r7 = r24
            r8 = r26
        L_0x0827:
            int r9 = r13 + 1
            r3 = r10
            r10 = r17
            r2 = r23
            r13 = 0
            goto L_0x0254
        L_0x0831:
            r7 = r45
            r2 = 0
            r8 = r44
            r12 = r2
            r9 = 0
        L_0x0839:
            if (r9 >= r8) goto L_0x0886
            com.google.android.gms.internal.measurement.zzgn$zzf r14 = r5.zza((int) r9)     // Catch:{ all -> 0x007f }
            java.lang.String r15 = r14.zzg()     // Catch:{ all -> 0x007f }
            boolean r15 = r10.equals(r15)     // Catch:{ all -> 0x007f }
            if (r15 == 0) goto L_0x085b
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r15 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r14, (java.lang.String) r6)     // Catch:{ all -> 0x007f }
            if (r15 == 0) goto L_0x085b
            r5.zzb((int) r9)     // Catch:{ all -> 0x007f }
            int r8 = r8 + -1
            int r9 = r9 + -1
        L_0x0859:
            r14 = 1
            goto L_0x0884
        L_0x085b:
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r14 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf) r14, (java.lang.String) r7)     // Catch:{ all -> 0x007f }
            if (r14 == 0) goto L_0x0859
            boolean r15 = r14.zzl()     // Catch:{ all -> 0x007f }
            if (r15 == 0) goto L_0x0873
            long r14 = r14.zzd()     // Catch:{ all -> 0x007f }
            java.lang.Long r14 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x007f }
            goto L_0x0874
        L_0x0873:
            r14 = 0
        L_0x0874:
            if (r14 == 0) goto L_0x0859
            long r21 = r14.longValue()     // Catch:{ all -> 0x007f }
            int r15 = (r21 > r2 ? 1 : (r21 == r2 ? 0 : -1))
            if (r15 <= 0) goto L_0x0859
            long r14 = r14.longValue()     // Catch:{ all -> 0x007f }
            long r12 = r12 + r14
            goto L_0x0859
        L_0x0884:
            int r9 = r9 + r14
            goto L_0x0839
        L_0x0886:
            r6 = 0
            r1.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5, (long) r12, (boolean) r6)     // Catch:{ all -> 0x007f }
            java.util.List r6 = r5.zzaa()     // Catch:{ all -> 0x007f }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ all -> 0x007f }
        L_0x0892:
            boolean r7 = r6.hasNext()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "_se"
            if (r7 == 0) goto L_0x08b7
            java.lang.Object r7 = r6.next()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r7 = (com.google.android.gms.internal.measurement.zzgn.zzf) r7     // Catch:{ all -> 0x007f }
            java.lang.String r9 = "_s"
            java.lang.String r7 = r7.zzg()     // Catch:{ all -> 0x007f }
            boolean r7 = r9.equals(r7)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0892
            com.google.android.gms.measurement.internal.zzam r6 = r42.zzf()     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r5.zzt()     // Catch:{ all -> 0x007f }
            r6.zzh(r7, r8)     // Catch:{ all -> 0x007f }
        L_0x08b7:
            java.lang.String r6 = "_sid"
            int r6 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5, (java.lang.String) r6)     // Catch:{ all -> 0x007f }
            if (r6 < 0) goto L_0x08c4
            r6 = 1
            r1.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5, (long) r12, (boolean) r6)     // Catch:{ all -> 0x007f }
            goto L_0x08e4
        L_0x08c4:
            int r6 = com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5, (java.lang.String) r8)     // Catch:{ all -> 0x007f }
            if (r6 < 0) goto L_0x08e4
            r5.zzc((int) r6)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgi r6 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r6 = r6.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r7 = "Session engagement user property is in the bundle without session ID. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r8)     // Catch:{ all -> 0x007f }
            r6.zza(r7, r8)     // Catch:{ all -> 0x007f }
        L_0x08e4:
            com.google.android.gms.measurement.internal.zzol r6 = r42.zzp()     // Catch:{ all -> 0x007f }
            r6.zza((com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r6 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzhp r7 = r42.zzl()     // Catch:{ all -> 0x007f }
            r7.zzt()     // Catch:{ all -> 0x007f }
            r42.zzs()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r7 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzh r7 = r7.zze(r6)     // Catch:{ all -> 0x007f }
            if (r7 != 0) goto L_0x0917
            com.google.android.gms.measurement.internal.zzgi r7 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "Cannot fix consent fields without appInfo. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ all -> 0x007f }
            r7.zza(r8, r6)     // Catch:{ all -> 0x007f }
            goto L_0x091a
        L_0x0917:
            r1.zza((com.google.android.gms.measurement.internal.zzh) r7, (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5)     // Catch:{ all -> 0x007f }
        L_0x091a:
            boolean r6 = com.google.android.gms.internal.measurement.zzqn.zza()     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x095b
            com.google.android.gms.measurement.internal.zzah r6 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r7 = com.google.android.gms.measurement.internal.zzbj.zzct     // Catch:{ all -> 0x007f }
            boolean r6 = r6.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r7)     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x095b
            com.google.android.gms.internal.measurement.zzgn$zzk r6 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzhp r7 = r42.zzl()     // Catch:{ all -> 0x007f }
            r7.zzt()     // Catch:{ all -> 0x007f }
            r42.zzs()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r7 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzh r7 = r7.zze(r6)     // Catch:{ all -> 0x007f }
            if (r7 != 0) goto L_0x0958
            com.google.android.gms.measurement.internal.zzgi r7 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r7 = r7.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = "Cannot populate ad_campaign_info without appInfo. appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ all -> 0x007f }
            r7.zza(r8, r6)     // Catch:{ all -> 0x007f }
            goto L_0x095b
        L_0x0958:
            r1.zzb((com.google.android.gms.measurement.internal.zzh) r7, (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5)     // Catch:{ all -> 0x007f }
        L_0x095b:
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r6 = r5.zzi((long) r6)     // Catch:{ all -> 0x007f }
            r7 = -9223372036854775808
            r6.zze((long) r7)     // Catch:{ all -> 0x007f }
            r6 = 0
        L_0x096a:
            int r7 = r5.zzc()     // Catch:{ all -> 0x007f }
            if (r6 >= r7) goto L_0x099d
            com.google.android.gms.internal.measurement.zzgn$zzf r7 = r5.zza((int) r6)     // Catch:{ all -> 0x007f }
            long r8 = r7.zzd()     // Catch:{ all -> 0x007f }
            long r12 = r5.zzf()     // Catch:{ all -> 0x007f }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 >= 0) goto L_0x0987
            long r8 = r7.zzd()     // Catch:{ all -> 0x007f }
            r5.zzi((long) r8)     // Catch:{ all -> 0x007f }
        L_0x0987:
            long r8 = r7.zzd()     // Catch:{ all -> 0x007f }
            long r12 = r5.zze()     // Catch:{ all -> 0x007f }
            int r8 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x099a
            long r7 = r7.zzd()     // Catch:{ all -> 0x007f }
            r5.zze((long) r7)     // Catch:{ all -> 0x007f }
        L_0x099a:
            int r6 = r6 + 1
            goto L_0x096a
        L_0x099d:
            r5.zzs()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r6 = com.google.android.gms.measurement.internal.zzjc.zza     // Catch:{ all -> 0x007f }
            boolean r7 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0a35
            com.google.android.gms.measurement.internal.zzah r7 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzdc     // Catch:{ all -> 0x007f }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0a35
            com.google.android.gms.internal.measurement.zzgn$zzk r6 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r6 = r1.zzb((java.lang.String) r6)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzae()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r7 = com.google.android.gms.measurement.internal.zzjc.zzb((java.lang.String) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r6 = r6.zza((com.google.android.gms.measurement.internal.zzjc) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r7 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r7 = r7.zzh(r8)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r8 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzz()     // Catch:{ all -> 0x007f }
            r8.zza((java.lang.String) r9, (com.google.android.gms.measurement.internal.zzjc) r6)     // Catch:{ all -> 0x007f }
            boolean r8 = r6.zzj()     // Catch:{ all -> 0x007f }
            if (r8 != 0) goto L_0x0a01
            boolean r8 = r7.zzj()     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x0a01
            com.google.android.gms.measurement.internal.zzam r7 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            r7.zzq(r8)     // Catch:{ all -> 0x007f }
            goto L_0x0a1a
        L_0x0a01:
            boolean r8 = r6.zzj()     // Catch:{ all -> 0x007f }
            if (r8 == 0) goto L_0x0a1a
            boolean r7 = r7.zzj()     // Catch:{ all -> 0x007f }
            if (r7 != 0) goto L_0x0a1a
            com.google.android.gms.measurement.internal.zzam r7 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            r7.zzr(r8)     // Catch:{ all -> 0x007f }
        L_0x0a1a:
            boolean r7 = r6.zzi()     // Catch:{ all -> 0x007f }
            if (r7 != 0) goto L_0x0a29
            r5.zzq()     // Catch:{ all -> 0x007f }
            r5.zzn()     // Catch:{ all -> 0x007f }
            r5.zzk()     // Catch:{ all -> 0x007f }
        L_0x0a29:
            boolean r7 = r6.zzj()     // Catch:{ all -> 0x007f }
            if (r7 != 0) goto L_0x0a35
            r5.zzh()     // Catch:{ all -> 0x007f }
            r5.zzr()     // Catch:{ all -> 0x007f }
        L_0x0a35:
            boolean r7 = com.google.android.gms.internal.measurement.zzrl.zza()     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b57
            com.google.android.gms.measurement.internal.zzah r7 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r8 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r8.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r9 = com.google.android.gms.measurement.internal.zzbj.zzcg     // Catch:{ all -> 0x007f }
            boolean r7 = r7.zze(r8, r9)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b57
            r42.zzq()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x007f }
            boolean r7 = com.google.android.gms.measurement.internal.zzop.zzd(r7)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b57
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzjc r7 = r1.zzb((java.lang.String) r7)     // Catch:{ all -> 0x007f }
            boolean r7 = r7.zzi()     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b57
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            boolean r7 = r7.zzat()     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b57
            r7 = 0
        L_0x0a75:
            int r8 = r5.zzc()     // Catch:{ all -> 0x007f }
            if (r7 >= r8) goto L_0x0b57
            com.google.android.gms.internal.measurement.zzgn$zzf r8 = r5.zza((int) r7)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r8 = r8.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r8 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r8     // Catch:{ all -> 0x007f }
            java.util.List r9 = r8.zzf()     // Catch:{ all -> 0x007f }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x007f }
        L_0x0a8d:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x007f }
            if (r10 == 0) goto L_0x0b53
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r10 = (com.google.android.gms.internal.measurement.zzgn.zzh) r10     // Catch:{ all -> 0x007f }
            java.lang.String r10 = r10.zzg()     // Catch:{ all -> 0x007f }
            boolean r10 = r11.equals(r10)     // Catch:{ all -> 0x007f }
            if (r10 == 0) goto L_0x0a8d
            com.google.android.gms.internal.measurement.zzgn$zzk r9 = r4.zza     // Catch:{ all -> 0x007f }
            int r9 = r9.zza()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzah r10 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r12 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Integer> r13 = com.google.android.gms.measurement.internal.zzbj.zzax     // Catch:{ all -> 0x007f }
            int r10 = r10.zzb((java.lang.String) r12, (com.google.android.gms.measurement.internal.zzfz<java.lang.Integer>) r13)     // Catch:{ all -> 0x007f }
            if (r9 < r10) goto L_0x0b48
            com.google.android.gms.measurement.internal.zzah r9 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r10 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbj.zzci     // Catch:{ all -> 0x007f }
            boolean r9 = r9.zze(r10, r12)     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x0aef
            com.google.android.gms.measurement.internal.zzop r9 = r42.zzq()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r9.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = com.google.android.gms.internal.measurement.zzgn.zzh.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "_tu"
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = r10.zzb(r9)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r10 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r10 = (com.google.android.gms.internal.measurement.zzlc) r10     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r10 = (com.google.android.gms.internal.measurement.zzgn.zzh) r10     // Catch:{ all -> 0x007f }
            r8.zza((com.google.android.gms.internal.measurement.zzgn.zzh) r10)     // Catch:{ all -> 0x007f }
            goto L_0x0af0
        L_0x0aef:
            r9 = 0
        L_0x0af0:
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = com.google.android.gms.internal.measurement.zzgn.zzh.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "_tr"
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = r10.zza((java.lang.String) r12)     // Catch:{ all -> 0x007f }
            r12 = 1
            com.google.android.gms.internal.measurement.zzgn$zzh$zza r10 = r10.zza((long) r12)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r10 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r10 = (com.google.android.gms.internal.measurement.zzlc) r10     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r10 = (com.google.android.gms.internal.measurement.zzgn.zzh) r10     // Catch:{ all -> 0x007f }
            r8.zza((com.google.android.gms.internal.measurement.zzgn.zzh) r10)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol r10 = r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r12 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zznk r9 = r10.zza((java.lang.String) r12, (com.google.android.gms.internal.measurement.zzgn.zzk.zza) r5, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r8, (java.lang.String) r9)     // Catch:{ all -> 0x007f }
            if (r9 == 0) goto L_0x0b48
            com.google.android.gms.measurement.internal.zzgi r10 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r10 = r10.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "Generated trigger URI. appId, uri"
            com.google.android.gms.internal.measurement.zzgn$zzk r13 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r14 = r9.zza     // Catch:{ all -> 0x007f }
            r10.zza(r12, r13, r14)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r10 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r12 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r12 = r12.zzz()     // Catch:{ all -> 0x007f }
            r10.zza((java.lang.String) r12, (com.google.android.gms.measurement.internal.zznk) r9)     // Catch:{ all -> 0x007f }
            java.util.Set<java.lang.String> r9 = r1.zzr     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r10 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r10 = r10.zzz()     // Catch:{ all -> 0x007f }
            r9.add(r10)     // Catch:{ all -> 0x007f }
        L_0x0b48:
            com.google.android.gms.internal.measurement.zzml r8 = r8.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r8 = (com.google.android.gms.internal.measurement.zzlc) r8     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r8 = (com.google.android.gms.internal.measurement.zzgn.zzf) r8     // Catch:{ all -> 0x007f }
            r5.zza((int) r7, (com.google.android.gms.internal.measurement.zzgn.zzf) r8)     // Catch:{ all -> 0x007f }
        L_0x0b53:
            int r7 = r7 + 1
            goto L_0x0a75
        L_0x0b57:
            boolean r7 = com.google.android.gms.internal.measurement.zzpd.zza()     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b9c
            com.google.android.gms.measurement.internal.zzah r7 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r8 = com.google.android.gms.measurement.internal.zzbj.zzdc     // Catch:{ all -> 0x007f }
            boolean r7 = r7.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r8)     // Catch:{ all -> 0x007f }
            if (r7 == 0) goto L_0x0b9c
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r7 = r5.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzv r8 = r42.zzc()     // Catch:{ all -> 0x007f }
            java.lang.String r9 = r5.zzt()     // Catch:{ all -> 0x007f }
            java.util.List r10 = r5.zzaa()     // Catch:{ all -> 0x007f }
            java.util.List r11 = r5.zzab()     // Catch:{ all -> 0x007f }
            long r12 = r5.zzf()     // Catch:{ all -> 0x007f }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x007f }
            long r13 = r5.zze()     // Catch:{ all -> 0x007f }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x007f }
            boolean r6 = r6.zzj()     // Catch:{ all -> 0x007f }
            r14 = 1
            r6 = r6 ^ r14
            r14 = r6
            java.util.List r6 = r8.zza(r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x007f }
            r7.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzd>) r6)     // Catch:{ all -> 0x007f }
            goto L_0x0bc7
        L_0x0b9c:
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r6 = r5.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzv r7 = r42.zzc()     // Catch:{ all -> 0x007f }
            java.lang.String r8 = r5.zzt()     // Catch:{ all -> 0x007f }
            java.util.List r9 = r5.zzaa()     // Catch:{ all -> 0x007f }
            java.util.List r10 = r5.zzab()     // Catch:{ all -> 0x007f }
            long r11 = r5.zzf()     // Catch:{ all -> 0x007f }
            java.lang.Long r11 = java.lang.Long.valueOf(r11)     // Catch:{ all -> 0x007f }
            long r12 = r5.zze()     // Catch:{ all -> 0x007f }
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x007f }
            java.util.List r7 = r7.zza(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x007f }
            r6.zza((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzd>) r7)     // Catch:{ all -> 0x007f }
        L_0x0bc7:
            com.google.android.gms.measurement.internal.zzah r6 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r7 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r7 = r7.zzz()     // Catch:{ all -> 0x007f }
            boolean r6 = r6.zzk(r7)     // Catch:{ all -> 0x007f }
            if (r6 == 0) goto L_0x0ecf
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x007f }
            r6.<init>()     // Catch:{ all -> 0x007f }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x007f }
            r7.<init>()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzop r8 = r42.zzq()     // Catch:{ all -> 0x007f }
            java.security.SecureRandom r8 = r8.zzv()     // Catch:{ all -> 0x007f }
            r9 = 0
        L_0x0bea:
            int r10 = r5.zzc()     // Catch:{ all -> 0x007f }
            if (r9 >= r10) goto L_0x0e98
            com.google.android.gms.internal.measurement.zzgn$zzf r10 = r5.zza((int) r9)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc$zzb r10 = r10.zzcd()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r10 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10     // Catch:{ all -> 0x007f }
            java.lang.String r11 = r10.zze()     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "_ep"
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x007f }
            java.lang.String r12 = "_sr"
            if (r11 == 0) goto L_0x0c87
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r11 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r11 = (com.google.android.gms.internal.measurement.zzgn.zzf) r11     // Catch:{ all -> 0x007f }
            java.lang.String r13 = "_en"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzol.zzb(r11, r13)     // Catch:{ all -> 0x007f }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x007f }
            java.lang.Object r13 = r6.get(r11)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r13 = (com.google.android.gms.measurement.internal.zzbd) r13     // Catch:{ all -> 0x007f }
            if (r13 != 0) goto L_0x0c3c
            com.google.android.gms.measurement.internal.zzam r13 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r14 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r11)     // Catch:{ all -> 0x007f }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r13 = r13.zzd(r14, r15)     // Catch:{ all -> 0x007f }
            if (r13 == 0) goto L_0x0c3c
            r6.put(r11, r13)     // Catch:{ all -> 0x007f }
        L_0x0c3c:
            if (r13 == 0) goto L_0x0c7b
            java.lang.Long r11 = r13.zzi     // Catch:{ all -> 0x007f }
            if (r11 != 0) goto L_0x0c7b
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x007f }
            if (r11 == 0) goto L_0x0c58
            long r14 = r11.longValue()     // Catch:{ all -> 0x007f }
            r18 = 1
            int r11 = (r14 > r18 ? 1 : (r14 == r18 ? 0 : -1))
            if (r11 <= 0) goto L_0x0c58
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.lang.Long r11 = r13.zzj     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r11)     // Catch:{ all -> 0x007f }
        L_0x0c58:
            java.lang.Boolean r11 = r13.zzk     // Catch:{ all -> 0x007f }
            if (r11 == 0) goto L_0x0c70
            boolean r11 = r11.booleanValue()     // Catch:{ all -> 0x007f }
            if (r11 == 0) goto L_0x0c70
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r11 = "_efs"
            r12 = 1
            java.lang.Long r14 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10, (java.lang.String) r11, (java.lang.Object) r14)     // Catch:{ all -> 0x007f }
        L_0x0c70:
            com.google.android.gms.internal.measurement.zzml r11 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r11 = (com.google.android.gms.internal.measurement.zzgn.zzf) r11     // Catch:{ all -> 0x007f }
            r7.add(r11)     // Catch:{ all -> 0x007f }
        L_0x0c7b:
            r5.zza((int) r9, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10)     // Catch:{ all -> 0x007f }
        L_0x0c7e:
            r15 = r4
            r1 = r5
            r45 = r8
            r2 = r9
            r8 = 1
            goto L_0x0e8b
        L_0x0c87:
            com.google.android.gms.measurement.internal.zzhg r11 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r13 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r13 = r13.zzz()     // Catch:{ all -> 0x007f }
            long r13 = r11.zza((java.lang.String) r13)     // Catch:{ all -> 0x007f }
            r42.zzq()     // Catch:{ all -> 0x007f }
            long r2 = r10.zzc()     // Catch:{ all -> 0x007f }
            long r2 = com.google.android.gms.measurement.internal.zzop.zza((long) r2, (long) r13)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r11 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r11 = (com.google.android.gms.internal.measurement.zzgn.zzf) r11     // Catch:{ all -> 0x007f }
            java.lang.String r15 = "_dbg"
            r18 = 1
            java.lang.Long r1 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x007f }
            boolean r18 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x007f }
            if (r18 != 0) goto L_0x0cea
            java.util.List r11 = r11.zzh()     // Catch:{ all -> 0x007f }
            java.util.Iterator r11 = r11.iterator()     // Catch:{ all -> 0x007f }
        L_0x0cbe:
            boolean r18 = r11.hasNext()     // Catch:{ all -> 0x007f }
            if (r18 == 0) goto L_0x0cea
            java.lang.Object r18 = r11.next()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzh r18 = (com.google.android.gms.internal.measurement.zzgn.zzh) r18     // Catch:{ all -> 0x007f }
            r45 = r11
            java.lang.String r11 = r18.zzg()     // Catch:{ all -> 0x007f }
            boolean r11 = r15.equals(r11)     // Catch:{ all -> 0x007f }
            if (r11 == 0) goto L_0x0ce7
            long r21 = r18.zzd()     // Catch:{ all -> 0x007f }
            java.lang.Long r11 = java.lang.Long.valueOf(r21)     // Catch:{ all -> 0x007f }
            boolean r1 = r1.equals(r11)     // Catch:{ all -> 0x007f }
            if (r1 != 0) goto L_0x0ce5
            goto L_0x0cea
        L_0x0ce5:
            r11 = 1
            goto L_0x0cfd
        L_0x0ce7:
            r11 = r45
            goto L_0x0cbe
        L_0x0cea:
            com.google.android.gms.measurement.internal.zzhg r1 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r11 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r11 = r11.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x007f }
            int r1 = r1.zzb((java.lang.String) r11, (java.lang.String) r15)     // Catch:{ all -> 0x007f }
            r11 = r1
        L_0x0cfd:
            if (r11 > 0) goto L_0x0d24
            com.google.android.gms.measurement.internal.zzgi r1 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r2 = "Sample rate must be positive. event, rate"
            java.lang.String r3 = r10.zze()     // Catch:{ all -> 0x007f }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x007f }
            r1.zza(r2, r3, r11)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r1 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r1 = (com.google.android.gms.internal.measurement.zzlc) r1     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r1 = (com.google.android.gms.internal.measurement.zzgn.zzf) r1     // Catch:{ all -> 0x007f }
            r7.add(r1)     // Catch:{ all -> 0x007f }
            r5.zza((int) r9, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10)     // Catch:{ all -> 0x007f }
            goto L_0x0c7e
        L_0x0d24:
            java.lang.String r1 = r10.zze()     // Catch:{ all -> 0x007f }
            java.lang.Object r1 = r6.get(r1)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r1 = (com.google.android.gms.measurement.internal.zzbd) r1     // Catch:{ all -> 0x007f }
            if (r1 != 0) goto L_0x0d83
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r15 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r15 = r15.zzz()     // Catch:{ all -> 0x007f }
            r21 = r13
            java.lang.String r13 = r10.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zzd(r15, r13)     // Catch:{ all -> 0x007f }
            if (r1 != 0) goto L_0x0d85
            com.google.android.gms.measurement.internal.zzgi r1 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r13 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzgn$zzk r14 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r14 = r14.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r15 = r10.zze()     // Catch:{ all -> 0x007f }
            r1.zza(r13, r14, r15)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r1 = new com.google.android.gms.measurement.internal.zzbd     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r13 = r4.zza     // Catch:{ all -> 0x007f }
            java.lang.String r25 = r13.zzz()     // Catch:{ all -> 0x007f }
            java.lang.String r26 = r10.zze()     // Catch:{ all -> 0x007f }
            long r33 = r10.zzc()     // Catch:{ all -> 0x007f }
            r39 = 0
            r40 = 0
            r27 = 1
            r29 = 1
            r31 = 1
            r35 = 0
            r37 = 0
            r38 = 0
            r24 = r1
            r24.<init>(r25, r26, r27, r29, r31, r33, r35, r37, r38, r39, r40)     // Catch:{ all -> 0x007f }
            goto L_0x0d85
        L_0x0d83:
            r21 = r13
        L_0x0d85:
            r42.zzp()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r13 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r13 = (com.google.android.gms.internal.measurement.zzlc) r13     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r13 = (com.google.android.gms.internal.measurement.zzgn.zzf) r13     // Catch:{ all -> 0x007f }
            java.lang.String r14 = "_eid"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzol.zzb(r13, r14)     // Catch:{ all -> 0x007f }
            java.lang.Long r13 = (java.lang.Long) r13     // Catch:{ all -> 0x007f }
            if (r13 == 0) goto L_0x0d9d
            r14 = 1
        L_0x0d9b:
            r15 = 1
            goto L_0x0d9f
        L_0x0d9d:
            r14 = 0
            goto L_0x0d9b
        L_0x0d9f:
            if (r11 != r15) goto L_0x0dcb
            com.google.android.gms.internal.measurement.zzml r2 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r2 = (com.google.android.gms.internal.measurement.zzlc) r2     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r2 = (com.google.android.gms.internal.measurement.zzgn.zzf) r2     // Catch:{ all -> 0x007f }
            r7.add(r2)     // Catch:{ all -> 0x007f }
            if (r14 == 0) goto L_0x0dc6
            java.lang.Long r2 = r1.zzi     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0dba
            java.lang.Long r2 = r1.zzj     // Catch:{ all -> 0x007f }
            if (r2 != 0) goto L_0x0dba
            java.lang.Boolean r2 = r1.zzk     // Catch:{ all -> 0x007f }
            if (r2 == 0) goto L_0x0dc6
        L_0x0dba:
            r2 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r2, r2, r2)     // Catch:{ all -> 0x007f }
            java.lang.String r2 = r10.zze()     // Catch:{ all -> 0x007f }
            r6.put(r2, r1)     // Catch:{ all -> 0x007f }
        L_0x0dc6:
            r5.zza((int) r9, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10)     // Catch:{ all -> 0x007f }
            goto L_0x0c7e
        L_0x0dcb:
            int r15 = r8.nextInt(r11)     // Catch:{ all -> 0x007f }
            if (r15 != 0) goto L_0x0e0d
            r42.zzp()     // Catch:{ all -> 0x007f }
            r15 = r4
            r25 = r5
            long r4 = (long) r11     // Catch:{ all -> 0x007f }
            java.lang.Long r11 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r11)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r11 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r11 = (com.google.android.gms.internal.measurement.zzgn.zzf) r11     // Catch:{ all -> 0x007f }
            r7.add(r11)     // Catch:{ all -> 0x007f }
            if (r14 == 0) goto L_0x0df5
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x007f }
            r5 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r5, r4, r5)     // Catch:{ all -> 0x007f }
        L_0x0df5:
            java.lang.String r4 = r10.zze()     // Catch:{ all -> 0x007f }
            long r11 = r10.zzc()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r11, r2)     // Catch:{ all -> 0x007f }
            r6.put(r4, r1)     // Catch:{ all -> 0x007f }
            r45 = r8
            r2 = r9
            r1 = r25
            r8 = 1
            goto L_0x0e88
        L_0x0e0d:
            r15 = r4
            r25 = r5
            java.lang.Long r4 = r1.zzh     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0e1d
            long r4 = r4.longValue()     // Catch:{ all -> 0x007f }
            r45 = r8
            r18 = r9
            goto L_0x0e2e
        L_0x0e1d:
            r42.zzq()     // Catch:{ all -> 0x007f }
            long r4 = r10.zzb()     // Catch:{ all -> 0x007f }
            r45 = r8
            r18 = r9
            r8 = r21
            long r4 = com.google.android.gms.measurement.internal.zzop.zza((long) r4, (long) r8)     // Catch:{ all -> 0x007f }
        L_0x0e2e:
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0e77
            r42.zzp()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "_efs"
            r8 = 1
            java.lang.Long r5 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10, (java.lang.String) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x007f }
            r42.zzp()     // Catch:{ all -> 0x007f }
            long r4 = (long) r11     // Catch:{ all -> 0x007f }
            java.lang.Long r11 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzol.zza((com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10, (java.lang.String) r12, (java.lang.Object) r11)     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r11 = r10.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r11 = (com.google.android.gms.internal.measurement.zzlc) r11     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzf r11 = (com.google.android.gms.internal.measurement.zzgn.zzf) r11     // Catch:{ all -> 0x007f }
            r7.add(r11)     // Catch:{ all -> 0x007f }
            if (r14 == 0) goto L_0x0e63
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x007f }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x007f }
            r11 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r11, r4, r5)     // Catch:{ all -> 0x007f }
        L_0x0e63:
            java.lang.String r4 = r10.zze()     // Catch:{ all -> 0x007f }
            long r11 = r10.zzc()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r11, r2)     // Catch:{ all -> 0x007f }
            r6.put(r4, r1)     // Catch:{ all -> 0x007f }
        L_0x0e72:
            r2 = r18
            r1 = r25
            goto L_0x0e88
        L_0x0e77:
            r8 = 1
            if (r14 == 0) goto L_0x0e72
            java.lang.String r2 = r10.zze()     // Catch:{ all -> 0x007f }
            r3 = 0
            com.google.android.gms.measurement.internal.zzbd r1 = r1.zza(r13, r3, r3)     // Catch:{ all -> 0x007f }
            r6.put(r2, r1)     // Catch:{ all -> 0x007f }
            goto L_0x0e72
        L_0x0e88:
            r1.zza((int) r2, (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r10)     // Catch:{ all -> 0x007f }
        L_0x0e8b:
            int r2 = r2 + 1
            r8 = r45
            r5 = r1
            r9 = r2
            r4 = r15
            r2 = 0
            r1 = r42
            goto L_0x0bea
        L_0x0e98:
            r15 = r4
            r1 = r5
            int r2 = r7.size()     // Catch:{ all -> 0x007f }
            int r3 = r1.zzc()     // Catch:{ all -> 0x007f }
            if (r2 >= r3) goto L_0x0eab
            com.google.android.gms.internal.measurement.zzgn$zzk$zza r2 = r1.zzl()     // Catch:{ all -> 0x007f }
            r2.zzb((java.lang.Iterable<? extends com.google.android.gms.internal.measurement.zzgn.zzf>) r7)     // Catch:{ all -> 0x007f }
        L_0x0eab:
            java.util.Set r2 = r6.entrySet()     // Catch:{ all -> 0x007f }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x007f }
        L_0x0eb3:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x007f }
            if (r3 == 0) goto L_0x0ecd
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x007f }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r4 = r42.zzf()     // Catch:{ all -> 0x007f }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzbd r3 = (com.google.android.gms.measurement.internal.zzbd) r3     // Catch:{ all -> 0x007f }
            r4.zza((com.google.android.gms.measurement.internal.zzbd) r3)     // Catch:{ all -> 0x007f }
            goto L_0x0eb3
        L_0x0ecd:
            r2 = r15
            goto L_0x0ed1
        L_0x0ecf:
            r1 = r5
            r2 = r4
        L_0x0ed1:
            com.google.android.gms.internal.measurement.zzgn$zzk r3 = r2.zza     // Catch:{ all -> 0x007f }
            java.lang.String r3 = r3.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r4 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzh r4 = r4.zze(r3)     // Catch:{ all -> 0x007f }
            if (r4 != 0) goto L_0x0efa
            com.google.android.gms.measurement.internal.zzgi r4 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r6 = r2.zza     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ all -> 0x007f }
            r4.zza(r5, r6)     // Catch:{ all -> 0x007f }
            goto L_0x0f86
        L_0x0efa:
            int r5 = r1.zzc()     // Catch:{ all -> 0x007f }
            if (r5 <= 0) goto L_0x0f86
            long r5 = r4.zzs()     // Catch:{ all -> 0x007f }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0f0e
            r1.zzg((long) r5)     // Catch:{ all -> 0x007f }
            goto L_0x0f11
        L_0x0f0e:
            r1.zzo()     // Catch:{ all -> 0x007f }
        L_0x0f11:
            long r7 = r4.zzu()     // Catch:{ all -> 0x007f }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0f1c
            goto L_0x0f1d
        L_0x0f1c:
            r5 = r7
        L_0x0f1d:
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x0f25
            r1.zzh((long) r5)     // Catch:{ all -> 0x007f }
            goto L_0x0f28
        L_0x0f25:
            r1.zzp()     // Catch:{ all -> 0x007f }
        L_0x0f28:
            boolean r5 = com.google.android.gms.internal.measurement.zzrw.zza()     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0f58
            com.google.android.gms.measurement.internal.zzah r5 = r42.zze()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r6 = com.google.android.gms.measurement.internal.zzbj.zzbw     // Catch:{ all -> 0x007f }
            boolean r5 = r5.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r6)     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0f58
            r42.zzq()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = r4.zzac()     // Catch:{ all -> 0x007f }
            boolean r5 = com.google.android.gms.measurement.internal.zzop.zzf(r5)     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0f58
            int r5 = r1.zzc()     // Catch:{ all -> 0x007f }
            long r5 = (long) r5     // Catch:{ all -> 0x007f }
            r4.zza((long) r5)     // Catch:{ all -> 0x007f }
            long r5 = r4.zzr()     // Catch:{ all -> 0x007f }
            int r5 = (int) r5     // Catch:{ all -> 0x007f }
            r1.zzg((int) r5)     // Catch:{ all -> 0x007f }
            goto L_0x0f5b
        L_0x0f58:
            r4.zzap()     // Catch:{ all -> 0x007f }
        L_0x0f5b:
            long r5 = r4.zzt()     // Catch:{ all -> 0x007f }
            int r5 = (int) r5     // Catch:{ all -> 0x007f }
            r1.zzf((int) r5)     // Catch:{ all -> 0x007f }
            long r5 = r1.zzf()     // Catch:{ all -> 0x007f }
            r4.zzr(r5)     // Catch:{ all -> 0x007f }
            long r5 = r1.zze()     // Catch:{ all -> 0x007f }
            r4.zzp(r5)     // Catch:{ all -> 0x007f }
            java.lang.String r5 = r4.zzab()     // Catch:{ all -> 0x007f }
            if (r5 == 0) goto L_0x0f7b
            r1.zzn(r5)     // Catch:{ all -> 0x007f }
            goto L_0x0f7e
        L_0x0f7b:
            r1.zzm()     // Catch:{ all -> 0x007f }
        L_0x0f7e:
            com.google.android.gms.measurement.internal.zzam r5 = r42.zzf()     // Catch:{ all -> 0x007f }
            r6 = 0
            r5.zza((com.google.android.gms.measurement.internal.zzh) r4, (boolean) r6, (boolean) r6)     // Catch:{ all -> 0x007f }
        L_0x0f86:
            int r4 = r1.zzc()     // Catch:{ all -> 0x007f }
            if (r4 <= 0) goto L_0x0fe5
            com.google.android.gms.measurement.internal.zzhg r4 = r42.zzi()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r5 = r2.zza     // Catch:{ all -> 0x007f }
            java.lang.String r5 = r5.zzz()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzfx$zzd r4 = r4.zzc(r5)     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0fab
            boolean r5 = r4.zzs()     // Catch:{ all -> 0x007f }
            if (r5 != 0) goto L_0x0fa3
            goto L_0x0fab
        L_0x0fa3:
            long r4 = r4.zzc()     // Catch:{ all -> 0x007f }
            r1.zzb((long) r4)     // Catch:{ all -> 0x007f }
            goto L_0x0fd4
        L_0x0fab:
            com.google.android.gms.internal.measurement.zzgn$zzk r4 = r2.zza     // Catch:{ all -> 0x007f }
            java.lang.String r4 = r4.zzaj()     // Catch:{ all -> 0x007f }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x007f }
            if (r4 == 0) goto L_0x0fbd
            r4 = -1
            r1.zzb((long) r4)     // Catch:{ all -> 0x007f }
            goto L_0x0fd4
        L_0x0fbd:
            com.google.android.gms.measurement.internal.zzgi r4 = r42.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r4 = r4.zzu()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzgn$zzk r6 = r2.zza     // Catch:{ all -> 0x007f }
            java.lang.String r6 = r6.zzz()     // Catch:{ all -> 0x007f }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r6)     // Catch:{ all -> 0x007f }
            r4.zza(r5, r6)     // Catch:{ all -> 0x007f }
        L_0x0fd4:
            com.google.android.gms.measurement.internal.zzam r4 = r42.zzf()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzml r1 = r1.zzai()     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzlc r1 = (com.google.android.gms.internal.measurement.zzlc) r1     // Catch:{ all -> 0x007f }
            com.google.android.gms.internal.measurement.zzgn$zzk r1 = (com.google.android.gms.internal.measurement.zzgn.zzk) r1     // Catch:{ all -> 0x007f }
            r13 = r17
            r4.zza((com.google.android.gms.internal.measurement.zzgn.zzk) r1, (boolean) r13)     // Catch:{ all -> 0x007f }
        L_0x0fe5:
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()     // Catch:{ all -> 0x007f }
            java.util.List<java.lang.Long> r2 = r2.zzb     // Catch:{ all -> 0x007f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x007f }
            r1.zzt()     // Catch:{ all -> 0x007f }
            r1.zzal()     // Catch:{ all -> 0x007f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "rowid in ("
            r4.<init>(r5)     // Catch:{ all -> 0x007f }
            r13 = 0
        L_0x0ffc:
            int r5 = r2.size()     // Catch:{ all -> 0x007f }
            if (r13 >= r5) goto L_0x1019
            if (r13 == 0) goto L_0x1009
            java.lang.String r5 = ","
            r4.append(r5)     // Catch:{ all -> 0x007f }
        L_0x1009:
            java.lang.Object r5 = r2.get(r13)     // Catch:{ all -> 0x007f }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x007f }
            long r5 = r5.longValue()     // Catch:{ all -> 0x007f }
            r4.append(r5)     // Catch:{ all -> 0x007f }
            int r13 = r13 + 1
            goto L_0x0ffc
        L_0x1019:
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch:{ all -> 0x007f }
            android.database.sqlite.SQLiteDatabase r5 = r1.e_()     // Catch:{ all -> 0x007f }
            java.lang.String r6 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x007f }
            r7 = 0
            int r4 = r5.delete(r6, r4, r7)     // Catch:{ all -> 0x007f }
            int r5 = r2.size()     // Catch:{ all -> 0x007f }
            if (r4 == r5) goto L_0x104c
            com.google.android.gms.measurement.internal.zzgi r1 = r1.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r5 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x007f }
            int r2 = r2.size()     // Catch:{ all -> 0x007f }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x007f }
            r1.zza(r5, r4, r2)     // Catch:{ all -> 0x007f }
        L_0x104c:
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()     // Catch:{ all -> 0x007f }
            android.database.sqlite.SQLiteDatabase r2 = r1.e_()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            java.lang.String[] r5 = new java.lang.String[]{r3, r3}     // Catch:{ SQLiteException -> 0x105e }
            r2.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x105e }
            goto L_0x1071
        L_0x105e:
            r0 = move-exception
            r2 = r0
            com.google.android.gms.measurement.internal.zzgi r1 = r1.zzj()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzgk r1 = r1.zzg()     // Catch:{ all -> 0x007f }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r3)     // Catch:{ all -> 0x007f }
            r1.zza(r4, r3, r2)     // Catch:{ all -> 0x007f }
        L_0x1071:
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()     // Catch:{ all -> 0x007f }
            r1.zzw()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()
            r1.zzu()
            r1 = 1
            return r1
        L_0x1081:
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()     // Catch:{ all -> 0x007f }
            r1.zzw()     // Catch:{ all -> 0x007f }
            com.google.android.gms.measurement.internal.zzam r1 = r42.zzf()
            r1.zzu()
            r1 = 0
            return r1
        L_0x1091:
            if (r5 == 0) goto L_0x1096
            r5.close()     // Catch:{ all -> 0x007f }
        L_0x1096:
            throw r1     // Catch:{ all -> 0x007f }
        L_0x1097:
            com.google.android.gms.measurement.internal.zzam r2 = r42.zzf()
            r2.zzu()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zznv.zza(java.lang.String, long):boolean");
    }

    private final boolean zza(zzgn.zzf.zza zza2, zzgn.zzf.zza zza3) {
        String str;
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzgn.zzh zza4 = zzol.zza((zzgn.zzf) ((zzlc) zza2.zzai()), "_sc");
        String str2 = null;
        if (zza4 == null) {
            str = null;
        } else {
            str = zza4.zzh();
        }
        zzp();
        zzgn.zzh zza5 = zzol.zza((zzgn.zzf) ((zzlc) zza3.zzai()), "_pc");
        if (zza5 != null) {
            str2 = zza5.zzh();
        }
        if (str2 == null || !str2.equals(str)) {
            return false;
        }
        Preconditions.checkArgument("_e".equals(zza2.zze()));
        zzp();
        zzgn.zzh zza6 = zzol.zza((zzgn.zzf) ((zzlc) zza2.zzai()), "_et");
        if (zza6 == null || !zza6.zzl() || zza6.zzd() <= 0) {
            return true;
        }
        long zzd2 = zza6.zzd();
        zzp();
        zzgn.zzh zza7 = zzol.zza((zzgn.zzf) ((zzlc) zza3.zzai()), "_et");
        if (zza7 != null && zza7.zzd() > 0) {
            zzd2 += zza7.zzd();
        }
        zzp();
        zzol.zza(zza3, "_et", (Object) Long.valueOf(zzd2));
        zzp();
        zzol.zza(zza2, "_fr", (Object) 1L);
        return true;
    }

    @VisibleForTesting
    @WorkerThread
    private final boolean zza(int i3, FileChannel fileChannel) {
        zzl().zzt();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzj().zzg().zza("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i3);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzj().zzg().zza("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e3) {
            zzj().zzg().zza("Failed to write to channel", e3);
            return false;
        }
    }
}
