package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.internal.zze;
import com.google.mlkit.vision.barcode.internal.zzh;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

public final class zzxk {
    /* access modifiers changed from: private */
    public static final GmsLogger zzf = new GmsLogger("AutoZoom");
    @VisibleForTesting
    final zzxm zza;
    @VisibleForTesting
    final zzbw zzb;
    @VisibleForTesting
    @Nullable
    ScheduledFuture zzc;
    @VisibleForTesting
    @Nullable
    String zzd;
    @VisibleForTesting
    int zze;
    /* access modifiers changed from: private */
    public final AtomicBoolean zzg;
    private final Object zzh = new Object();
    private final ScheduledExecutorService zzi;
    private final zzbb zzj;
    private final zzwp zzk;
    private final String zzl;
    private Executor zzm;
    private float zzn;
    private float zzo;
    private long zzp;
    private long zzq;
    private boolean zzr;
    private zze zzs;

    private zzxk(Context context, zzxm zzxm, String str) {
        zzg.zza();
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(2));
        zzbb zza2 = zzar.zza();
        zzwp zzwp = new zzwp(context, new SharedPrefManager(context), new zzwi(context, zzwh.zzd("scanner-auto-zoom").zzd()), "scanner-auto-zoom");
        this.zza = zzxm;
        this.zzg = new AtomicBoolean(false);
        this.zzb = zzbw.zzz();
        this.zzi = unconfigurableScheduledExecutorService;
        this.zzj = zza2;
        this.zzk = zzwp;
        this.zzl = str;
        this.zze = 1;
        this.zzn = 1.0f;
        this.zzo = -1.0f;
        this.zzp = zza2.zza();
    }

    public static zzxk zzd(Context context, String str) {
        return new zzxk(context, zzxm.zza, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzxk r7) {
        /*
            java.lang.Object r0 = r7.zzh
            monitor-enter(r0)
            int r1 = r7.zze     // Catch:{ all -> 0x0041 }
            r2 = 2
            if (r1 != r2) goto L_0x0045
            java.util.concurrent.atomic.AtomicBoolean r1 = r7.zzg     // Catch:{ all -> 0x0041 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0041 }
            if (r1 != 0) goto L_0x0045
            java.util.concurrent.ScheduledFuture r1 = r7.zzc     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x0045
            boolean r1 = r1.isCancelled()     // Catch:{ all -> 0x0041 }
            if (r1 == 0) goto L_0x001b
            goto L_0x0045
        L_0x001b:
            float r1 = r7.zzn     // Catch:{ all -> 0x0041 }
            r2 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x0043
            long r3 = r7.zza()     // Catch:{ all -> 0x0041 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r1 = r7.zza     // Catch:{ all -> 0x0041 }
            long r5 = r1.zzi()     // Catch:{ all -> 0x0041 }
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 < 0) goto L_0x0043
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x0041 }
            java.lang.String r3 = "AutoZoom"
            java.lang.String r4 = "Reset zoom = 1"
            r1.i(r3, r4)     // Catch:{ all -> 0x0041 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_AUTO_RESET     // Catch:{ all -> 0x0041 }
            r3 = 0
            r7.zzl(r2, r1, r3)     // Catch:{ all -> 0x0041 }
            goto L_0x0043
        L_0x0041:
            r7 = move-exception
            goto L_0x0047
        L_0x0043:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            return
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0041 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzf(com.google.android.gms.internal.mlkit_vision_barcode.zzxk):void");
    }

    public static /* bridge */ /* synthetic */ void zzg(zzxk zzxk, float f2) {
        synchronized (zzxk.zzh) {
            zzxk.zzn = f2;
            zzxk.zzr(false);
        }
    }

    private final float zzp(float f2) {
        int i3 = (f2 > 1.0f ? 1 : (f2 == 1.0f ? 0 : -1));
        float f3 = this.zzo;
        if (i3 < 0) {
            f2 = 1.0f;
        }
        return (f3 <= 0.0f || f2 <= f3) ? f2 : f3;
    }

    /* access modifiers changed from: private */
    public final void zzq(zzrc zzrc, float f2, float f3, @Nullable zzxn zzxn) {
        long convert;
        if (this.zzd != null) {
            zzuo zzuo = new zzuo();
            zzuo.zza(this.zzl);
            String str = this.zzd;
            str.getClass();
            zzuo.zze(str);
            zzuo.zzf(Float.valueOf(f2));
            zzuo.zzc(Float.valueOf(f3));
            synchronized (this.zzh) {
                convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzq, TimeUnit.NANOSECONDS);
            }
            zzuo.zzb(Long.valueOf(convert));
            if (zzxn != null) {
                zzup zzup = new zzup();
                zzup.zzc(Float.valueOf(zzxn.zzc()));
                zzup.zze(Float.valueOf(zzxn.zze()));
                zzup.zzb(Float.valueOf(zzxn.zzb()));
                zzup.zzd(Float.valueOf(zzxn.zzd()));
                zzup.zza(Float.valueOf(0.0f));
                zzuo.zzd(zzup.zzf());
            }
            zzwp zzwp = this.zzk;
            zzrd zzrd = new zzrd();
            zzrd.zzi(zzuo.zzh());
            zzwp.zzd(zzws.zzf(zzrd), zzrc);
        }
    }

    private final void zzr(boolean z2) {
        ScheduledFuture scheduledFuture;
        synchronized (this.zzh) {
            try {
                this.zzb.zzs();
                this.zzp = this.zzj.zza();
                if (z2 && (scheduledFuture = this.zzc) != null) {
                    scheduledFuture.cancel(false);
                    this.zzc = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @VisibleForTesting
    public final long zza() {
        long convert;
        synchronized (this.zzh) {
            convert = TimeUnit.MILLISECONDS.convert(this.zzj.zza() - this.zzp, TimeUnit.NANOSECONDS);
        }
        return convert;
    }

    public final /* synthetic */ zzet zzc(float f2) throws Exception {
        zze zze2 = this.zzs;
        float zzp2 = zzp(f2);
        ZoomSuggestionOptions zoomSuggestionOptions = zze2.zza;
        int i3 = zzh.zzc;
        if (true != zoomSuggestionOptions.zzb().setZoom(zzp2)) {
            zzp2 = 0.0f;
        }
        return zzej.zza(Float.valueOf(zzp2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x019b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x026c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(int r18, com.google.android.gms.internal.mlkit_vision_barcode.zzxn r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            java.lang.Object r2 = r0.zzh
            monitor-enter(r2)
            int r3 = r0.zze     // Catch:{ all -> 0x000e }
            r4 = 2
            if (r3 == r4) goto L_0x0011
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x000e:
            r0 = move-exception
            goto L_0x026d
        L_0x0011:
            boolean r3 = r19.zzh()     // Catch:{ all -> 0x000e }
            if (r3 == 0) goto L_0x026b
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r0.zza     // Catch:{ all -> 0x000e }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x000e }
            r4 = 0
            if (r3 == 0) goto L_0x002c
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r0.zza     // Catch:{ all -> 0x000e }
            float r3 = r3.zzb()     // Catch:{ all -> 0x000e }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x002c
            goto L_0x026b
        L_0x002c:
            boolean r3 = r0.zzr     // Catch:{ all -> 0x000e }
            if (r3 != 0) goto L_0x003a
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r3 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x000e }
            float r5 = r0.zzn     // Catch:{ all -> 0x000e }
            r0.zzq(r3, r5, r5, r1)     // Catch:{ all -> 0x000e }
            r3 = 1
            r0.zzr = r3     // Catch:{ all -> 0x000e }
        L_0x003a:
            com.google.android.gms.common.internal.GmsLogger r3 = zzf     // Catch:{ all -> 0x000e }
            java.lang.String r5 = "AutoZoom"
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch:{ all -> 0x000e }
            java.lang.String r7 = "Process PredictedArea: [%.2f, %.2f, %.2f, %.2f, %.2f], frameIndex = %d"
            float r8 = r19.zzc()     // Catch:{ all -> 0x000e }
            java.lang.Float r9 = java.lang.Float.valueOf(r8)     // Catch:{ all -> 0x000e }
            float r8 = r19.zze()     // Catch:{ all -> 0x000e }
            java.lang.Float r10 = java.lang.Float.valueOf(r8)     // Catch:{ all -> 0x000e }
            float r8 = r19.zzb()     // Catch:{ all -> 0x000e }
            java.lang.Float r11 = java.lang.Float.valueOf(r8)     // Catch:{ all -> 0x000e }
            float r8 = r19.zzd()     // Catch:{ all -> 0x000e }
            java.lang.Float r12 = java.lang.Float.valueOf(r8)     // Catch:{ all -> 0x000e }
            java.lang.Float r13 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x000e }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r18)     // Catch:{ all -> 0x000e }
            r14 = r8
            java.lang.Object[] r9 = new java.lang.Object[]{r9, r10, r11, r12, r13, r14}     // Catch:{ all -> 0x000e }
            java.lang.String r6 = java.lang.String.format(r6, r7, r9)     // Catch:{ all -> 0x000e }
            r3.i(r5, r6)     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r0.zzb     // Catch:{ all -> 0x000e }
            r3.zzt(r8, r1)     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r0.zzb     // Catch:{ all -> 0x000e }
            java.util.Set r3 = r3.zzw()     // Catch:{ all -> 0x000e }
            int r5 = r3.size()     // Catch:{ all -> 0x000e }
            int r5 = r5 + -1
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r6 = r0.zza     // Catch:{ all -> 0x000e }
            int r6 = r6.zzh()     // Catch:{ all -> 0x000e }
            if (r5 <= r6) goto L_0x00cc
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x000e }
            r5 = r18
        L_0x0097:
            boolean r6 = r3.hasNext()     // Catch:{ all -> 0x000e }
            if (r6 == 0) goto L_0x00ab
            java.lang.Object r6 = r3.next()     // Catch:{ all -> 0x000e }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x000e }
            int r6 = r6.intValue()     // Catch:{ all -> 0x000e }
            if (r5 <= r6) goto L_0x0097
            r5 = r6
            goto L_0x0097
        L_0x00ab:
            com.google.android.gms.common.internal.GmsLogger r3 = zzf     // Catch:{ all -> 0x000e }
            java.lang.String r6 = "AutoZoom"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x000e }
            r7.<init>()     // Catch:{ all -> 0x000e }
            java.lang.String r8 = "Removing recent frameIndex = "
            r7.append(r8)     // Catch:{ all -> 0x000e }
            r7.append(r5)     // Catch:{ all -> 0x000e }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x000e }
            r3.i(r6, r7)     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r3 = r0.zzb     // Catch:{ all -> 0x000e }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x000e }
            r3.zzf(r5)     // Catch:{ all -> 0x000e }
        L_0x00cc:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x000e }
            r3.<init>()     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbw r5 = r0.zzb     // Catch:{ all -> 0x000e }
            java.util.Collection r5 = r5.zzu()     // Catch:{ all -> 0x000e }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x000e }
        L_0x00db:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x000e }
            if (r6 == 0) goto L_0x0169
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x000e }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x000e }
            java.lang.Object r7 = r6.getKey()     // Catch:{ all -> 0x000e }
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch:{ all -> 0x000e }
            int r7 = r7.intValue()     // Catch:{ all -> 0x000e }
            r8 = r18
            if (r7 == r8) goto L_0x00db
            java.lang.Object r7 = r6.getValue()     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxn r7 = (com.google.android.gms.internal.mlkit_vision_barcode.zzxn) r7     // Catch:{ all -> 0x000e }
            boolean r9 = r7.zzh()     // Catch:{ all -> 0x000e }
            if (r9 == 0) goto L_0x0107
            boolean r9 = r19.zzh()     // Catch:{ all -> 0x000e }
            if (r9 != 0) goto L_0x0109
        L_0x0107:
            r10 = r4
            goto L_0x0154
        L_0x0109:
            float r9 = r7.zzc()     // Catch:{ all -> 0x000e }
            float r10 = r19.zzc()     // Catch:{ all -> 0x000e }
            float r12 = java.lang.Math.max(r9, r10)     // Catch:{ all -> 0x000e }
            float r9 = r7.zze()     // Catch:{ all -> 0x000e }
            float r10 = r19.zze()     // Catch:{ all -> 0x000e }
            float r13 = java.lang.Math.max(r9, r10)     // Catch:{ all -> 0x000e }
            float r9 = r7.zzb()     // Catch:{ all -> 0x000e }
            float r10 = r19.zzb()     // Catch:{ all -> 0x000e }
            float r14 = java.lang.Math.min(r9, r10)     // Catch:{ all -> 0x000e }
            float r9 = r7.zzd()     // Catch:{ all -> 0x000e }
            float r10 = r19.zzd()     // Catch:{ all -> 0x000e }
            float r15 = java.lang.Math.min(r9, r10)     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxg r9 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxg     // Catch:{ all -> 0x000e }
            r16 = 0
            r11 = r9
            r11.<init>(r12, r13, r14, r15, r16)     // Catch:{ all -> 0x000e }
            float r10 = r9.zzf()     // Catch:{ all -> 0x000e }
            float r7 = r7.zzf()     // Catch:{ all -> 0x000e }
            float r11 = r19.zzf()     // Catch:{ all -> 0x000e }
            float r7 = r7 + r11
            float r9 = r9.zzf()     // Catch:{ all -> 0x000e }
            float r7 = r7 - r9
            float r10 = r10 / r7
        L_0x0154:
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r7 = r0.zza     // Catch:{ all -> 0x000e }
            float r7 = r7.zzd()     // Catch:{ all -> 0x000e }
            int r7 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x00db
            java.lang.Object r6 = r6.getKey()     // Catch:{ all -> 0x000e }
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ all -> 0x000e }
            r3.add(r6)     // Catch:{ all -> 0x000e }
            goto L_0x00db
        L_0x0169:
            int r3 = r3.size()     // Catch:{ all -> 0x000e }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r0.zza     // Catch:{ all -> 0x000e }
            int r5 = r5.zzg()     // Catch:{ all -> 0x000e }
            if (r3 >= r5) goto L_0x0187
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r0.zza     // Catch:{ all -> 0x000e }
            boolean r3 = r3.zzl()     // Catch:{ all -> 0x000e }
            if (r3 == 0) goto L_0x0267
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r3 = r0.zza     // Catch:{ all -> 0x000e }
            float r3 = r3.zza()     // Catch:{ all -> 0x000e }
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 > 0) goto L_0x0267
        L_0x0187:
            java.lang.Object r3 = r0.zzh     // Catch:{ all -> 0x000e }
            monitor-enter(r3)     // Catch:{ all -> 0x000e }
            long r4 = r17.zza()     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r6 = r0.zza     // Catch:{ all -> 0x019b }
            long r6 = r6.zzj()     // Catch:{ all -> 0x019b }
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 >= 0) goto L_0x019e
            monitor-exit(r3)     // Catch:{ all -> 0x019b }
            goto L_0x0267
        L_0x019b:
            r0 = move-exception
            goto L_0x0269
        L_0x019e:
            float r4 = r19.zzc()     // Catch:{ all -> 0x019b }
            java.lang.Float r4 = java.lang.Float.valueOf(r4)     // Catch:{ all -> 0x019b }
            float r5 = r19.zze()     // Catch:{ all -> 0x019b }
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch:{ all -> 0x019b }
            float r6 = r19.zzb()     // Catch:{ all -> 0x019b }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ all -> 0x019b }
            float r7 = r19.zzd()     // Catch:{ all -> 0x019b }
            java.lang.Float r7 = java.lang.Float.valueOf(r7)     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzcs r4 = com.google.android.gms.internal.mlkit_vision_barcode.zzcs.zzi(r4, r5, r6, r7)     // Catch:{ all -> 0x019b }
            r5 = 0
            com.google.android.gms.internal.mlkit_vision_barcode.zzdv r4 = r4.listIterator(r5)     // Catch:{ all -> 0x019b }
            r5 = 1315859240(0x4e6e6b28, float:1.0E9)
        L_0x01ca:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x019b }
            if (r6 == 0) goto L_0x01f9
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x019b }
            java.lang.Float r6 = (java.lang.Float) r6     // Catch:{ all -> 0x019b }
            float r6 = r6.floatValue()     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r7 = r0.zza     // Catch:{ all -> 0x019b }
            float r7 = r7.zzc()     // Catch:{ all -> 0x019b }
            r8 = 1073741824(0x40000000, float:2.0)
            float r7 = r7 / r8
            r8 = -1090519040(0xffffffffbf000000, float:-0.5)
            float r6 = r6 + r8
            float r6 = java.lang.Math.abs(r6)     // Catch:{ all -> 0x019b }
            r8 = 981668463(0x3a83126f, float:0.001)
            float r6 = java.lang.Math.max(r6, r8)     // Catch:{ all -> 0x019b }
            float r6 = r7 / r6
            int r7 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r7 <= 0) goto L_0x01ca
            r5 = r6
            goto L_0x01ca
        L_0x01f9:
            float r4 = r0.zzn     // Catch:{ all -> 0x019b }
            float r4 = r4 * r5
            float r4 = r0.zzp(r4)     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r0.zza     // Catch:{ all -> 0x019b }
            boolean r5 = r5.zzk()     // Catch:{ all -> 0x019b }
            if (r5 == 0) goto L_0x0249
            float r5 = r0.zzn     // Catch:{ all -> 0x019b }
            float r6 = r4 - r5
            float r6 = r6 / r5
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r0.zza     // Catch:{ all -> 0x019b }
            float r5 = r5.zze()     // Catch:{ all -> 0x019b }
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 > 0) goto L_0x0249
            com.google.android.gms.internal.mlkit_vision_barcode.zzxm r5 = r0.zza     // Catch:{ all -> 0x019b }
            float r5 = r5.zzf()     // Catch:{ all -> 0x019b }
            float r5 = -r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x0249
            com.google.android.gms.common.internal.GmsLogger r1 = zzf     // Catch:{ all -> 0x019b }
            java.lang.String r5 = "AutoZoom"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x019b }
            r6.<init>()     // Catch:{ all -> 0x019b }
            java.lang.String r7 = "Auto zoom to "
            r6.append(r7)     // Catch:{ all -> 0x019b }
            r6.append(r4)     // Catch:{ all -> 0x019b }
            java.lang.String r4 = " is filtered by threshold"
            r6.append(r4)     // Catch:{ all -> 0x019b }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x019b }
            r1.i(r5, r4)     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbb r1 = r0.zzj     // Catch:{ all -> 0x019b }
            long r4 = r1.zza()     // Catch:{ all -> 0x019b }
            r0.zzp = r4     // Catch:{ all -> 0x019b }
            monitor-exit(r3)     // Catch:{ all -> 0x019b }
            goto L_0x0267
        L_0x0249:
            com.google.android.gms.common.internal.GmsLogger r5 = zzf     // Catch:{ all -> 0x019b }
            java.lang.String r6 = "AutoZoom"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x019b }
            r7.<init>()     // Catch:{ all -> 0x019b }
            java.lang.String r8 = "Going to set zoom = "
            r7.append(r8)     // Catch:{ all -> 0x019b }
            r7.append(r4)     // Catch:{ all -> 0x019b }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x019b }
            r5.i(r6, r7)     // Catch:{ all -> 0x019b }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_AUTO_ZOOM     // Catch:{ all -> 0x019b }
            r0.zzl(r4, r5, r1)     // Catch:{ all -> 0x019b }
            monitor-exit(r3)     // Catch:{ all -> 0x019b }
        L_0x0267:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x0269:
            monitor-exit(r3)     // Catch:{ all -> 0x019b }
            throw r0     // Catch:{ all -> 0x000e }
        L_0x026b:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x026d:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzi(int, com.google.android.gms.internal.mlkit_vision_barcode.zzxn):void");
    }

    public final void zzj() {
        synchronized (this.zzh) {
            try {
                if (this.zze != 4) {
                    zzn(false);
                    this.zzi.shutdown();
                    this.zze = 4;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void zzk(float f2) {
        synchronized (this.zzh) {
            zzaz.zzd(f2 >= 1.0f);
            this.zzo = f2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        return;
     */
    @androidx.annotation.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(float r10, com.google.android.gms.internal.mlkit_vision_barcode.zzrc r11, @javax.annotation.Nullable com.google.android.gms.internal.mlkit_vision_barcode.zzxn r12) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzh
            monitor-enter(r0)
            java.util.concurrent.Executor r1 = r9.zzm     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x003f
            com.google.mlkit.vision.barcode.internal.zze r1 = r9.zzs     // Catch:{ all -> 0x001d }
            if (r1 == 0) goto L_0x003f
            int r1 = r9.zze     // Catch:{ all -> 0x001d }
            r2 = 2
            if (r1 == r2) goto L_0x0011
            goto L_0x003f
        L_0x0011:
            java.util.concurrent.atomic.AtomicBoolean r1 = r9.zzg     // Catch:{ all -> 0x001d }
            r2 = 0
            r3 = 1
            boolean r1 = r1.compareAndSet(r2, r3)     // Catch:{ all -> 0x001d }
            if (r1 != 0) goto L_0x001f
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x001d:
            r9 = move-exception
            goto L_0x0041
        L_0x001f:
            float r4 = r9.zzn     // Catch:{ all -> 0x001d }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxh r1 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxh     // Catch:{ all -> 0x001d }
            r1.<init>(r9, r10)     // Catch:{ all -> 0x001d }
            java.util.concurrent.Executor r2 = r9.zzm     // Catch:{ all -> 0x001d }
            com.google.android.gms.internal.mlkit_vision_barcode.zzet r7 = com.google.android.gms.internal.mlkit_vision_barcode.zzej.zzc(r1, r2)     // Catch:{ all -> 0x001d }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxj r8 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxj     // Catch:{ all -> 0x001d }
            r1 = r8
            r2 = r9
            r3 = r11
            r5 = r12
            r6 = r10
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x001d }
            java.util.concurrent.Executor r9 = com.google.android.gms.internal.mlkit_vision_barcode.zzeu.zza()     // Catch:{ all -> 0x001d }
            com.google.android.gms.internal.mlkit_vision_barcode.zzej.zzb(r7, r8, r9)     // Catch:{ all -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x003f:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            return
        L_0x0041:
            monitor-exit(r0)     // Catch:{ all -> 0x001d }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzl(float, com.google.android.gms.internal.mlkit_vision_barcode.zzrc, com.google.android.gms.internal.mlkit_vision_barcode.zzxn):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzm() {
        /*
            r10 = this;
            java.lang.Object r0 = r10.zzh
            monitor-enter(r0)
            int r1 = r10.zze     // Catch:{ all -> 0x0044 }
            r2 = 2
            if (r1 == r2) goto L_0x0051
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x0051
        L_0x000c:
            r1 = 1
            r10.zzr(r1)     // Catch:{ all -> 0x0044 }
            java.util.concurrent.ScheduledExecutorService r3 = r10.zzi     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzxi r4 = new com.google.android.gms.internal.mlkit_vision_barcode.zzxi     // Catch:{ all -> 0x0044 }
            r4.<init>(r10)     // Catch:{ all -> 0x0044 }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0044 }
            r7 = 500(0x1f4, double:2.47E-321)
            r5 = r7
            java.util.concurrent.ScheduledFuture r3 = r3.scheduleWithFixedDelay(r4, r5, r7, r9)     // Catch:{ all -> 0x0044 }
            r10.zzc = r3     // Catch:{ all -> 0x0044 }
            int r3 = r10.zze     // Catch:{ all -> 0x0044 }
            r4 = 0
            if (r3 != r1) goto L_0x0046
            java.util.UUID r1 = java.util.UUID.randomUUID()     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0044 }
            r10.zzd = r1     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzbb r1 = r10.zzj     // Catch:{ all -> 0x0044 }
            long r5 = r1.zza()     // Catch:{ all -> 0x0044 }
            r10.zzq = r5     // Catch:{ all -> 0x0044 }
            r1 = 0
            r10.zzr = r1     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_START     // Catch:{ all -> 0x0044 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0044 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0044 }
            goto L_0x004d
        L_0x0044:
            r10 = move-exception
            goto L_0x0053
        L_0x0046:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r1 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_RESUME     // Catch:{ all -> 0x0044 }
            float r3 = r10.zzn     // Catch:{ all -> 0x0044 }
            r10.zzq(r1, r3, r3, r4)     // Catch:{ all -> 0x0044 }
        L_0x004d:
            r10.zze = r2     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0051:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzm():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzn(boolean r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.zzh
            monitor-enter(r0)
            int r1 = r4.zze     // Catch:{ all -> 0x001e }
            r2 = 1
            if (r1 == r2) goto L_0x0038
            r3 = 4
            if (r1 != r3) goto L_0x000c
            goto L_0x0038
        L_0x000c:
            r4.zzr(r2)     // Catch:{ all -> 0x001e }
            r1 = 0
            if (r5 == 0) goto L_0x0028
            boolean r5 = r4.zzr     // Catch:{ all -> 0x001e }
            if (r5 != 0) goto L_0x0020
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT     // Catch:{ all -> 0x001e }
            float r3 = r4.zzn     // Catch:{ all -> 0x001e }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x001e }
            goto L_0x0020
        L_0x001e:
            r4 = move-exception
            goto L_0x003a
        L_0x0020:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_SCAN_SUCCESS     // Catch:{ all -> 0x001e }
            float r3 = r4.zzn     // Catch:{ all -> 0x001e }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x001e }
            goto L_0x002f
        L_0x0028:
            com.google.android.gms.internal.mlkit_vision_barcode.zzrc r5 = com.google.android.gms.internal.mlkit_vision_barcode.zzrc.SCANNER_AUTO_ZOOM_SCAN_FAILED     // Catch:{ all -> 0x001e }
            float r3 = r4.zzn     // Catch:{ all -> 0x001e }
            r4.zzq(r5, r3, r3, r1)     // Catch:{ all -> 0x001e }
        L_0x002f:
            r5 = 0
            r4.zzr = r5     // Catch:{ all -> 0x001e }
            r4.zze = r2     // Catch:{ all -> 0x001e }
            r4.zzd = r1     // Catch:{ all -> 0x001e }
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x0038:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzxk.zzn(boolean):void");
    }

    public final void zzo(zze zze2, Executor executor) {
        this.zzs = zze2;
        this.zzm = executor;
    }
}
