package com.google.mlkit.vision.barcode.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzft;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqh;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzqo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzra;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwe;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzws;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.Iterator;
import java.util.List;

public final class zzl extends MLTask {
    @VisibleForTesting
    static boolean zza = true;
    private static final ImageUtils zzb = ImageUtils.getInstance();
    private final BarcodeScannerOptions zzc;
    private final zzm zzd;
    private final zzwp zze;
    private final zzwr zzf;
    private final BitmapInStreamingChecker zzg = new BitmapInStreamingChecker();
    private boolean zzh;

    public zzl(MlKitContext mlKitContext, BarcodeScannerOptions barcodeScannerOptions, zzm zzm, zzwp zzwp) {
        Preconditions.checkNotNull(mlKitContext, "MlKitContext can not be null");
        Preconditions.checkNotNull(barcodeScannerOptions, "BarcodeScannerOptions can not be null");
        this.zzc = barcodeScannerOptions;
        this.zzd = zzm;
        this.zze = zzwp;
        this.zzf = zzwr.zza(mlKitContext.getApplicationContext());
    }

    @WorkerThread
    private final void zzf(zzrb zzrb, long j2, @NonNull InputImage inputImage, @Nullable List list) {
        zzcp zzcp = new zzcp();
        zzcp zzcp2 = new zzcp();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Barcode barcode = (Barcode) it.next();
                zzcp.zzd(zzb.zza(barcode.getFormat()));
                zzcp2.zzd(zzb.zzb(barcode.getValueType()));
            }
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
        this.zze.zzf(new zzj(this, elapsedRealtime, zzrb, zzcp, zzcp2, inputImage), zzrc.ON_DEVICE_BARCODE_DETECT);
        zzfr zzfr = new zzfr();
        zzfr.zze(zzrb);
        zzfr.zzf(Boolean.valueOf(zza));
        zzfr.zzg(zzb.zzc(this.zzc));
        zzfr.zzc(zzcp.zzf());
        zzfr.zzd(zzcp2.zzf());
        MLTaskExecutor.workerThreadExecutor().execute(new zzwn(this.zze, zzrc.AGGREGATED_ON_DEVICE_BARCODE_DETECTION, zzfr.zzh(), elapsedRealtime, new zzk(this)));
        long currentTimeMillis = System.currentTimeMillis();
        this.zzf.zzc(true != this.zzh ? 24301 : 24302, zzrb.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @WorkerThread
    public final synchronized void load() throws MlKitException {
        this.zzh = this.zzd.zzc();
    }

    @WorkerThread
    public final synchronized void release() {
        try {
            this.zzd.zzb();
            zza = true;
            zzrd zzrd = new zzrd();
            zzra zzra = this.zzh ? zzra.TYPE_THICK : zzra.TYPE_THIN;
            zzwp zzwp = this.zze;
            zzrd.zze(zzra);
            zzrp zzrp = new zzrp();
            zzrp.zzi(zzb.zzc(this.zzc));
            zzrd.zzg(zzrp.zzj());
            zzwp.zzd(zzws.zzf(zzrd), zzrc.ON_DEVICE_BARCODE_CLOSE);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final /* synthetic */ zzwe zzc(long j2, zzrb zzrb, zzcp zzcp, zzcp zzcp2, InputImage inputImage) {
        zzrp zzrp = new zzrp();
        zzqo zzqo = new zzqo();
        zzqo.zzc(Long.valueOf(j2));
        zzqo.zzd(zzrb);
        zzqo.zze(Boolean.valueOf(zza));
        Boolean bool = Boolean.TRUE;
        zzqo.zza(bool);
        zzqo.zzb(bool);
        zzrp.zzh(zzqo.zzf());
        zzrp.zzi(zzb.zzc(this.zzc));
        zzrp.zze(zzcp.zzf());
        zzrp.zzf(zzcp2.zzf());
        int format = inputImage.getFormat();
        int mobileVisionImageSize = zzb.getMobileVisionImageSize(inputImage);
        zzqh zzqh = new zzqh();
        zzqh.zza(format != -1 ? format != 35 ? format != 842094169 ? format != 16 ? format != 17 ? zzqi.UNKNOWN_FORMAT : zzqi.NV21 : zzqi.NV16 : zzqi.YV12 : zzqi.YUV_420_888 : zzqi.BITMAP);
        zzqh.zzb(Integer.valueOf(mobileVisionImageSize));
        zzrp.zzg(zzqh.zzd());
        zzrd zzrd = new zzrd();
        zzrd.zze(this.zzh ? zzra.TYPE_THICK : zzra.TYPE_THIN);
        zzrd.zzg(zzrp.zzj());
        return zzws.zzf(zzrd);
    }

    public final /* synthetic */ zzwe zzd(zzft zzft, int i3, zzqd zzqd) {
        zzrd zzrd = new zzrd();
        zzrd.zze(this.zzh ? zzra.TYPE_THICK : zzra.TYPE_THIN);
        zzfq zzfq = new zzfq();
        zzfq.zza(Integer.valueOf(i3));
        zzfq.zzc(zzft);
        zzfq.zzb(zzqd);
        zzrd.zzd(zzfq.zze());
        return zzws.zzf(zzrd);
    }

    @WorkerThread
    /* renamed from: zze */
    public final synchronized List run(@NonNull InputImage inputImage) throws MlKitException {
        List zza2;
        BitmapInStreamingChecker bitmapInStreamingChecker = this.zzg;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        bitmapInStreamingChecker.check(inputImage);
        try {
            zza2 = this.zzd.zza(inputImage);
            zzf(zzrb.NO_ERROR, elapsedRealtime, inputImage, zza2);
            zza = false;
        } catch (MlKitException e3) {
            zzf(e3.getErrorCode() == 14 ? zzrb.MODEL_NOT_DOWNLOADED : zzrb.UNKNOWN_ERROR, elapsedRealtime, inputImage, (List) null);
            throw e3;
        }
        return zza2;
    }
}
