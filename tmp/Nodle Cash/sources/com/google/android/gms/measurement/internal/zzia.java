package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.BinderThread;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzad;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzpi;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zzia extends zzga {
    /* access modifiers changed from: private */
    public final zznv zza;
    private Boolean zzb;
    private String zzc;

    public zzia(zznv zznv) {
        this(zznv, (String) null);
    }

    @VisibleForTesting
    public final zzbh zzb(zzbh zzbh, zzp zzp) {
        zzbc zzbc;
        if (!(!Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzbh.zza) || (zzbc = zzbh.zzb) == null || zzbc.zza() == 0)) {
            String zzd = zzbh.zzb.zzd("_cis");
            if ("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) {
                this.zza.zzj().zzn().zza("Event has been filtered ", zzbh.toString());
                return new zzbh("_cmpx", zzbh.zzb, zzbh.zzc, zzbh.zzd);
            }
        }
        return zzbh;
    }

    @BinderThread
    public final void zzc(zzp zzp) {
        zzb(zzp, false);
        zzb((Runnable) new zzif(this, zzp));
    }

    @BinderThread
    public final void zzd(zzp zzp) {
        zzb(zzp, false);
        zzb((Runnable) new zzig(this, zzp));
    }

    @BinderThread
    public final void zze(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        zza(zzp.zza, false);
        zzb((Runnable) new zzin(this, zzp));
    }

    @BinderThread
    public final void zzf(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        Preconditions.checkNotNull(zzp.zzt);
        zza((Runnable) new zziq(this, zzp));
    }

    @BinderThread
    public final void zzg(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        Preconditions.checkNotNull(zzp.zzt);
        zza((Runnable) new zzib(this, zzp));
    }

    @BinderThread
    public final void zzh(zzp zzp) {
        zzb(zzp, false);
        zzb((Runnable) new zzid(this, zzp));
    }

    @BinderThread
    public final void zzi(zzp zzp) {
        Preconditions.checkNotEmpty(zzp.zza);
        Preconditions.checkNotNull(zzp.zzt);
        zza((Runnable) new zzhz(this, zzp));
    }

    public final /* synthetic */ void zzj(zzp zzp) {
        this.zza.zzr();
        this.zza.zzf(zzp);
    }

    public final /* synthetic */ void zzk(zzp zzp) {
        this.zza.zzr();
        this.zza.zzg(zzp);
    }

    private zzia(zznv zznv, String str) {
        Preconditions.checkNotNull(zznv);
        this.zza = zznv;
        this.zzc = null;
    }

    @BinderThread
    public final zzak zza(zzp zzp) {
        zzb(zzp, false);
        Preconditions.checkNotEmpty(zzp.zza);
        try {
            return (zzak) this.zza.zzl().zzb(new zzip(this, zzp)).get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e3) {
            this.zza.zzj().zzg().zza("Failed to get consent. appId", zzgi.zza(zzp.zza), e3);
            return new zzak((Bundle) null);
        }
    }

    private final void zzd(zzbh zzbh, zzp zzp) {
        this.zza.zzr();
        this.zza.zza(zzbh, zzp);
    }

    public final void zzc(zzbh zzbh, zzp zzp) {
        zzb zzb2;
        boolean z2;
        if (!this.zza.zzi().zzl(zzp.zza)) {
            zzd(zzbh, zzp);
            return;
        }
        this.zza.zzj().zzp().zza("EES config found for", zzp.zza);
        zzhg zzi = this.zza.zzi();
        String str = zzp.zza;
        if (TextUtils.isEmpty(str)) {
            zzb2 = null;
        } else {
            zzb2 = zzi.zza.get(str);
        }
        if (zzb2 == null) {
            this.zza.zzj().zzp().zza("EES not loaded for", zzp.zza);
            zzd(zzbh, zzp);
            return;
        }
        try {
            Map<String, Object> zza2 = this.zza.zzp().zza(zzbh.zzb.zzb(), true);
            String zza3 = zzjf.zza(zzbh.zza);
            if (zza3 == null) {
                zza3 = zzbh.zza;
            }
            z2 = zzb2.zza(new zzad(zza3, zzbh.zzd, zza2));
        } catch (zzc unused) {
            this.zza.zzj().zzg().zza("EES error. appId, eventName", zzp.zzb, zzbh.zza);
            z2 = false;
        }
        if (!z2) {
            this.zza.zzj().zzp().zza("EES was not applied to event", zzbh.zza);
            zzd(zzbh, zzp);
            return;
        }
        if (zzb2.zzd()) {
            this.zza.zzj().zzp().zza("EES edited event", zzbh.zza);
            zzd(this.zza.zzp().zza(zzb2.zza().zzb()), zzp);
        } else {
            zzd(zzbh, zzp);
        }
        if (zzb2.zzc()) {
            for (zzad next : zzb2.zza().zzc()) {
                this.zza.zzj().zzp().zza("EES logging created event", next.zzb());
                zzd(this.zza.zzp().zza(next), zzp);
            }
        }
    }

    @BinderThread
    public final String zzb(zzp zzp) {
        zzb(zzp, false);
        return this.zza.zzb(zzp);
    }

    @BinderThread
    private final void zzb(zzp zzp, boolean z2) {
        Preconditions.checkNotNull(zzp);
        Preconditions.checkNotEmpty(zzp.zza);
        zza(zzp.zza, false);
        this.zza.zzq().zza(zzp.zzb, zzp.zzp);
    }

    @BinderThread
    public final List<zznk> zza(zzp zzp, Bundle bundle) {
        zzb(zzp, false);
        Preconditions.checkNotNull(zzp.zza);
        try {
            return (List) this.zza.zzl().zza(new zziw(this, zzp, bundle)).get();
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to get trigger URIs. appId", zzgi.zza(zzp.zza), e3);
            return Collections.emptyList();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:68:0x01d5  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01df  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zzb(android.os.Bundle r31, java.lang.String r32) {
        /*
            r30 = this;
            r0 = r30
            r1 = r31
            r12 = r32
            java.lang.String r13 = "_o"
            boolean r2 = r31.isEmpty()
            if (r2 == 0) goto L_0x0018
            com.google.android.gms.measurement.internal.zznv r0 = r0.zza
            com.google.android.gms.measurement.internal.zzam r0 = r0.zzf()
            r0.zzp(r12)
            return
        L_0x0018:
            com.google.android.gms.measurement.internal.zznv r2 = r0.zza
            com.google.android.gms.measurement.internal.zzam r2 = r2.zzf()
            r2.zza((java.lang.String) r12, (android.os.Bundle) r1)
            com.google.android.gms.measurement.internal.zznv r0 = r0.zza
            com.google.android.gms.measurement.internal.zzam r14 = r0.zzf()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r31)
            r14.zzt()
            r14.zzal()
            r2 = -1
        L_0x0032:
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.lang.String r19 = "app_id = ? and rowid > ?"
            java.lang.String r0 = java.lang.String.valueOf(r2)
            java.lang.String[] r20 = new java.lang.String[]{r12, r0}
            r4 = 0
            r5 = 0
            android.database.sqlite.SQLiteDatabase r16 = r14.e_()     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r17 = "raw_events"
            java.lang.String r6 = "rowid"
            java.lang.String r7 = "name"
            java.lang.String r8 = "timestamp"
            java.lang.String r9 = "metadata_fingerprint"
            java.lang.String r10 = "data"
            java.lang.String r11 = "realtime"
            java.lang.String[] r18 = new java.lang.String[]{r6, r7, r8, r9, r10, r11}     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r23 = "rowid"
            java.lang.String r24 = "1000"
            r21 = 0
            r22 = 0
            android.database.Cursor r5 = r16.query(r17, r18, r19, r20, r21, r22, r23, r24)     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r0 != 0) goto L_0x006f
            r5.close()
            return
        L_0x006f:
            long r17 = r5.getLong(r4)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 3
            long r19 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r0 = 5
            long r6 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            r8 = 1
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r6 = 1
            if (r0 != 0) goto L_0x0087
            r21 = r6
            goto L_0x0089
        L_0x0087:
            r21 = r4
        L_0x0089:
            r0 = 4
            byte[] r0 = r5.getBlob(r0)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r7 = com.google.android.gms.internal.measurement.zzgn.zzf.zze()     // Catch:{ IOException -> 0x019f }
            com.google.android.gms.internal.measurement.zzmk r0 = com.google.android.gms.measurement.internal.zzol.zza(r7, (byte[]) r0)     // Catch:{ IOException -> 0x019f }
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r0 = (com.google.android.gms.internal.measurement.zzgn.zzf.zza) r0     // Catch:{ IOException -> 0x019f }
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x00f0 }
            java.lang.String r7 = ""
            if (r6 == 0) goto L_0x00a1
            goto L_0x00a2
        L_0x00a1:
            r6 = r7
        L_0x00a2:
            com.google.android.gms.internal.measurement.zzgn$zzf$zza r6 = r0.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x00f0 }
            r8 = 2
            long r8 = r5.getLong(r8)     // Catch:{ SQLiteException -> 0x00f0 }
            r6.zzb((long) r8)     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.measurement.internal.zzol r6 = r14.g_()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.measurement.zzml r8 = r0.zzai()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.measurement.zzlc r8 = (com.google.android.gms.internal.measurement.zzlc) r8     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.measurement.zzgn$zzf r8 = (com.google.android.gms.internal.measurement.zzgn.zzf) r8     // Catch:{ SQLiteException -> 0x00f0 }
            android.os.Bundle r9 = new android.os.Bundle     // Catch:{ SQLiteException -> 0x00f0 }
            r9.<init>()     // Catch:{ SQLiteException -> 0x00f0 }
            java.util.List r10 = r8.zzh()     // Catch:{ SQLiteException -> 0x00f0 }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ SQLiteException -> 0x00f0 }
        L_0x00c7:
            boolean r11 = r10.hasNext()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r11 == 0) goto L_0x0155
            java.lang.Object r11 = r10.next()     // Catch:{ SQLiteException -> 0x00f0 }
            com.google.android.gms.internal.measurement.zzgn$zzh r11 = (com.google.android.gms.internal.measurement.zzgn.zzh) r11     // Catch:{ SQLiteException -> 0x00f0 }
            boolean r16 = r11.zzj()     // Catch:{ SQLiteException -> 0x00f0 }
            if (r16 == 0) goto L_0x00f5
            java.lang.String r4 = r11.zzg()     // Catch:{ SQLiteException -> 0x00f0 }
            r28 = r2
            double r2 = r11.zza()     // Catch:{ SQLiteException -> 0x00ed }
            r9.putDouble(r4, r2)     // Catch:{ SQLiteException -> 0x00ed }
        L_0x00e6:
            r2 = r28
            r4 = 0
            goto L_0x00c7
        L_0x00ea:
            r0 = move-exception
            goto L_0x0223
        L_0x00ed:
            r0 = move-exception
            goto L_0x01c2
        L_0x00f0:
            r0 = move-exception
            r28 = r2
            goto L_0x01c2
        L_0x00f5:
            r28 = r2
            boolean r2 = r11.zzk()     // Catch:{ SQLiteException -> 0x00ed }
            if (r2 == 0) goto L_0x0109
            java.lang.String r2 = r11.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            float r3 = r11.zzb()     // Catch:{ SQLiteException -> 0x00ed }
            r9.putFloat(r2, r3)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x00e6
        L_0x0109:
            boolean r2 = r11.zzl()     // Catch:{ SQLiteException -> 0x00ed }
            if (r2 == 0) goto L_0x011b
            java.lang.String r2 = r11.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            long r3 = r11.zzd()     // Catch:{ SQLiteException -> 0x00ed }
            r9.putLong(r2, r3)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x00e6
        L_0x011b:
            boolean r2 = r11.zzn()     // Catch:{ SQLiteException -> 0x00ed }
            if (r2 == 0) goto L_0x012d
            java.lang.String r2 = r11.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            java.lang.String r3 = r11.zzh()     // Catch:{ SQLiteException -> 0x00ed }
            r9.putString(r2, r3)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x00e6
        L_0x012d:
            java.util.List r2 = r11.zzi()     // Catch:{ SQLiteException -> 0x00ed }
            boolean r2 = r2.isEmpty()     // Catch:{ SQLiteException -> 0x00ed }
            if (r2 != 0) goto L_0x0147
            java.lang.String r2 = r11.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            java.util.List r3 = r11.zzi()     // Catch:{ SQLiteException -> 0x00ed }
            android.os.Bundle[] r3 = com.google.android.gms.measurement.internal.zzol.zzb((java.util.List<com.google.android.gms.internal.measurement.zzgn.zzh>) r3)     // Catch:{ SQLiteException -> 0x00ed }
            r9.putParcelableArray(r2, r3)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x00e6
        L_0x0147:
            com.google.android.gms.measurement.internal.zzgi r2 = r6.zzj()     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            java.lang.String r3 = "Unexpected parameter type for parameter"
            r2.zza(r3, r11)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x00e6
        L_0x0155:
            r28 = r2
            java.lang.String r2 = r9.getString(r13)     // Catch:{ SQLiteException -> 0x00ed }
            r9.remove(r13)     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzgm r3 = new com.google.android.gms.measurement.internal.zzgm     // Catch:{ SQLiteException -> 0x00ed }
            java.lang.String r23 = r8.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            if (r2 != 0) goto L_0x0169
            r24 = r7
            goto L_0x016b
        L_0x0169:
            r24 = r2
        L_0x016b:
            long r26 = r8.zzd()     // Catch:{ SQLiteException -> 0x00ed }
            r22 = r3
            r25 = r9
            r22.<init>(r23, r24, r25, r26)     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzop r2 = r14.zzq()     // Catch:{ SQLiteException -> 0x00ed }
            android.os.Bundle r4 = r3.zzd     // Catch:{ SQLiteException -> 0x00ed }
            r2.zza((android.os.Bundle) r4, (android.os.Bundle) r1)     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzop r2 = r14.zzq()     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzah r4 = r14.zze()     // Catch:{ SQLiteException -> 0x00ed }
            int r4 = r4.zzb(r12)     // Catch:{ SQLiteException -> 0x00ed }
            r2.zza((com.google.android.gms.measurement.internal.zzgm) r3, (int) r4)     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzaq r2 = new com.google.android.gms.measurement.internal.zzaq     // Catch:{ SQLiteException -> 0x00ed }
            long r22 = r0.zzb()     // Catch:{ SQLiteException -> 0x00ed }
            r16 = r2
            r24 = r3
            r16.<init>(r17, r19, r21, r22, r24)     // Catch:{ SQLiteException -> 0x00ed }
            r15.add(r2)     // Catch:{ SQLiteException -> 0x00ed }
            goto L_0x01b3
        L_0x019f:
            r0 = move-exception
            r28 = r2
            com.google.android.gms.measurement.internal.zzgi r2 = r14.zzj()     // Catch:{ SQLiteException -> 0x00ed }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ SQLiteException -> 0x00ed }
            java.lang.String r3 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r32)     // Catch:{ SQLiteException -> 0x00ed }
            r2.zza(r3, r4, r0)     // Catch:{ SQLiteException -> 0x00ed }
        L_0x01b3:
            boolean r0 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x00ed }
            if (r0 != 0) goto L_0x01bd
            r5.close()
            goto L_0x01d8
        L_0x01bd:
            r2 = r28
            r4 = 0
            goto L_0x006f
        L_0x01c2:
            com.google.android.gms.measurement.internal.zzgi r2 = r14.zzj()     // Catch:{ all -> 0x00ea }
            com.google.android.gms.measurement.internal.zzgk r2 = r2.zzg()     // Catch:{ all -> 0x00ea }
            java.lang.String r3 = "Data loss. Error querying raw events. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzgi.zza((java.lang.String) r32)     // Catch:{ all -> 0x00ea }
            r2.zza(r3, r4, r0)     // Catch:{ all -> 0x00ea }
            if (r5 == 0) goto L_0x01d8
            r5.close()
        L_0x01d8:
            int r0 = r15.size()
            r4 = 0
        L_0x01dd:
            if (r4 >= r0) goto L_0x021f
            java.lang.Object r2 = r15.get(r4)
            int r16 = r4 + 1
            r11 = r2
            com.google.android.gms.measurement.internal.zzaq r11 = (com.google.android.gms.measurement.internal.zzaq) r11
            com.google.android.gms.measurement.internal.zzba r17 = new com.google.android.gms.measurement.internal.zzba
            com.google.android.gms.measurement.internal.zzhw r3 = r14.zzu
            com.google.android.gms.measurement.internal.zzgm r2 = r11.zze
            java.lang.String r4 = r2.zzb
            java.lang.String r6 = r2.zza
            long r7 = r2.zzc
            long r9 = r11.zzd
            android.os.Bundle r5 = r2.zzd
            r2 = r17
            r18 = r5
            r5 = r32
            r30 = r0
            r0 = r11
            r11 = r18
            r2.<init>((com.google.android.gms.measurement.internal.zzhw) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (long) r7, (long) r9, (android.os.Bundle) r11)
            long r3 = r0.zza
            long r6 = r0.zzb
            boolean r8 = r0.zzc
            r2 = r14
            r5 = r17
            r2.zza((long) r3, (com.google.android.gms.measurement.internal.zzba) r5, (long) r6, (boolean) r8)
            long r2 = r0.zza
            int r0 = (r2 > r28 ? 1 : (r2 == r28 ? 0 : -1))
            if (r0 <= 0) goto L_0x021a
            r28 = r2
        L_0x021a:
            r0 = r30
            r4 = r16
            goto L_0x01dd
        L_0x021f:
            r2 = r28
            goto L_0x0032
        L_0x0223:
            if (r5 == 0) goto L_0x0228
            r5.close()
        L_0x0228:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzia.zzb(android.os.Bundle, java.lang.String):void");
    }

    @BinderThread
    public final List<zzok> zza(zzp zzp, boolean z2) {
        zzb(zzp, false);
        String str = zzp.zza;
        Preconditions.checkNotNull(str);
        try {
            List<zzom> list = (List) this.zza.zzl().zza(new zziv(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzom zzom : list) {
                if (!z2) {
                    if (!zzop.zzg(zzom.zzc)) {
                    }
                }
                arrayList.add(new zzok(zzom));
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to get user properties. appId", zzgi.zza(zzp.zza), e3);
            return null;
        }
    }

    @BinderThread
    public final List<zzaf> zza(String str, String str2, zzp zzp) {
        zzb(zzp, false);
        String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            return (List) this.zza.zzl().zza(new zzil(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties", e3);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzaf> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzl().zza(new zzio(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to get conditional user properties as", e3);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final List<zzok> zza(String str, String str2, boolean z2, zzp zzp) {
        zzb(zzp, false);
        String str3 = zzp.zza;
        Preconditions.checkNotNull(str3);
        try {
            List<zzom> list = (List) this.zza.zzl().zza(new zzij(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzom zzom : list) {
                if (!z2) {
                    if (!zzop.zzg(zzom.zzc)) {
                    }
                }
                arrayList.add(new zzok(zzom));
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to query user properties. appId", zzgi.zza(zzp.zza), e3);
            return Collections.emptyList();
        }
    }

    @VisibleForTesting
    private final void zzb(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzb(runnable);
        }
    }

    @BinderThread
    public final List<zzok> zza(String str, String str2, String str3, boolean z2) {
        zza(str, true);
        try {
            List<zzom> list = (List) this.zza.zzl().zza(new zzim(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzom zzom : list) {
                if (!z2) {
                    if (!zzop.zzg(zzom.zzc)) {
                    }
                }
                arrayList.add(new zzok(zzom));
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to get user properties as. appId", zzgi.zza(str), e3);
            return Collections.emptyList();
        }
    }

    @BinderThread
    public final void zzb(Bundle bundle, zzp zzp) {
        if (zzpi.zza() && this.zza.zze().zza(zzbj.zzdk)) {
            zzb(zzp, false);
            String str = zzp.zza;
            Preconditions.checkNotNull(str);
            zzb((Runnable) new zzic(this, bundle, str));
        }
    }

    @BinderThread
    private final void zza(String str, boolean z2) {
        boolean z3;
        if (!TextUtils.isEmpty(str)) {
            if (z2) {
                try {
                    if (this.zzb == null) {
                        if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zza(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zza.zza()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z3 = false;
                                this.zzb = Boolean.valueOf(z3);
                            }
                        }
                        z3 = true;
                        this.zzb = Boolean.valueOf(z3);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e3) {
                    this.zza.zzj().zzg().zza("Measurement Service called with invalid calling package. appId", zzgi.zza(str));
                    throw e3;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zza(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException("Unknown calling package name '" + str + "'.");
            }
            return;
        }
        this.zza.zzj().zzg().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    public final /* synthetic */ void zza(Bundle bundle, String str) {
        boolean zza2 = this.zza.zze().zza(zzbj.zzdi);
        boolean zza3 = this.zza.zze().zza(zzbj.zzdk);
        if (!bundle.isEmpty() || !zza2 || !zza3) {
            this.zza.zzf().zza(str, bundle);
        } else {
            this.zza.zzf().zzp(str);
        }
    }

    @BinderThread
    public final void zza(zzbh zzbh, zzp zzp) {
        Preconditions.checkNotNull(zzbh);
        zzb(zzp, false);
        zzb((Runnable) new zzis(this, zzbh, zzp));
    }

    @BinderThread
    public final void zza(zzbh zzbh, String str, String str2) {
        Preconditions.checkNotNull(zzbh);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zzb((Runnable) new zzir(this, zzbh, str));
    }

    @VisibleForTesting
    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzl().zzg()) {
            runnable.run();
        } else {
            this.zza.zzl().zzc(runnable);
        }
    }

    @BinderThread
    public final void zza(zzaf zzaf, zzp zzp) {
        Preconditions.checkNotNull(zzaf);
        Preconditions.checkNotNull(zzaf.zzc);
        zzb(zzp, false);
        zzaf zzaf2 = new zzaf(zzaf);
        zzaf2.zza = zzp.zza;
        zzb((Runnable) new zzih(this, zzaf2, zzp));
    }

    @BinderThread
    public final void zza(zzaf zzaf) {
        Preconditions.checkNotNull(zzaf);
        Preconditions.checkNotNull(zzaf.zzc);
        Preconditions.checkNotEmpty(zzaf.zza);
        zza(zzaf.zza, true);
        zzb((Runnable) new zzik(this, new zzaf(zzaf)));
    }

    @BinderThread
    public final void zza(long j2, String str, String str2, String str3) {
        zzb((Runnable) new zzii(this, str2, str3, str, j2));
    }

    @BinderThread
    public final void zza(Bundle bundle, zzp zzp) {
        zzb(zzp, false);
        String str = zzp.zza;
        Preconditions.checkNotNull(str);
        zzb((Runnable) new zzie(this, bundle, str));
    }

    @BinderThread
    public final void zza(zzok zzok, zzp zzp) {
        Preconditions.checkNotNull(zzok);
        zzb(zzp, false);
        zzb((Runnable) new zzit(this, zzok, zzp));
    }

    @BinderThread
    public final byte[] zza(zzbh zzbh, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbh);
        zza(str, true);
        this.zza.zzj().zzc().zza("Log and bundle. event", this.zza.zzg().zza(zzbh.zza));
        long nanoTime = this.zza.zzb().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzl().zzb(new zziu(this, zzbh, str)).get();
            if (bArr == null) {
                this.zza.zzj().zzg().zza("Log and bundle returned null. appId", zzgi.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzj().zzc().zza("Log and bundle processed. event, size, time_ms", this.zza.zzg().zza(zzbh.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzb().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e3) {
            this.zza.zzj().zzg().zza("Failed to log and bundle. appId, event, error", zzgi.zza(str), this.zza.zzg().zza(zzbh.zza), e3);
            return null;
        }
    }
}
