package com.google.mlkit.vision.barcode.internal;

import android.graphics.Point;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.Feature;
import com.google.android.gms.internal.mlkit_vision_barcode.zzeu;
import com.google.android.gms.internal.mlkit_vision_barcode.zzra;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrr;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzws;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzxn;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public final class zzh extends MobileVisionBase implements BarcodeScanner {
    public static final /* synthetic */ int zzc = 0;
    /* access modifiers changed from: private */
    public static final BarcodeScannerOptions zzd = new BarcodeScannerOptions.Builder().build();
    @VisibleForTesting
    @Nullable
    final zzxk zzb;
    private final boolean zze;
    private final BarcodeScannerOptions zzf;
    private int zzg;
    private boolean zzh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public zzh(@NonNull BarcodeScannerOptions barcodeScannerOptions, @NonNull zzl zzl, @NonNull Executor executor, @NonNull zzwp zzwp, @NonNull MlKitContext mlKitContext) {
        super(zzl, executor);
        zzxk zzxk;
        ZoomSuggestionOptions zzb2 = barcodeScannerOptions.zzb();
        if (zzb2 == null) {
            zzxk = null;
        } else {
            zzxk = zzxk.zzd(mlKitContext.getApplicationContext(), mlKitContext.getApplicationContext().getPackageName());
            zzxk.zzo(new zze(zzb2), zzeu.zza());
            if (zzb2.zza() >= 1.0f) {
                zzxk.zzk(zzb2.zza());
            }
            zzxk.zzm();
        }
        this.zzf = barcodeScannerOptions;
        boolean zzf2 = zzb.zzf();
        this.zze = zzf2;
        zzrp zzrp = new zzrp();
        zzrp.zzi(zzb.zzc(barcodeScannerOptions));
        zzrr zzj = zzrp.zzj();
        zzrd zzrd = new zzrd();
        zzrd.zze(zzf2 ? zzra.TYPE_THICK : zzra.TYPE_THIN);
        zzrd.zzg(zzj);
        zzwp.zzd(zzws.zzg(zzrd, 1), zzrc.ON_DEVICE_BARCODE_CREATE);
        this.zzb = zzxk;
    }

    private final Task zzf(@NonNull Task task, int i3, int i4) {
        return task.onSuccessTask(new zzf(this, i3, i4));
    }

    public final synchronized void close() {
        try {
            zzxk zzxk = this.zzb;
            if (zzxk != null) {
                zzxk.zzn(this.zzh);
                this.zzb.zzj();
            }
            super.close();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final int getDetectorType() {
        return 1;
    }

    public final Feature[] getOptionalFeatures() {
        if (this.zze) {
            return OptionalModuleUtils.EMPTY_FEATURES;
        }
        return new Feature[]{OptionalModuleUtils.FEATURE_BARCODE};
    }

    @NonNull
    public final Task<List<Barcode>> process(@NonNull MlImage mlImage) {
        return zzf(super.processBase(mlImage), mlImage.getWidth(), mlImage.getHeight());
    }

    public final /* synthetic */ Task zzd(int i3, int i4, List list) throws Exception {
        if (this.zzb == null) {
            return Tasks.forResult(list);
        }
        this.zzg++;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Barcode barcode = (Barcode) it.next();
            if (barcode.getFormat() == -1) {
                arrayList2.add(barcode);
            } else {
                arrayList.add(barcode);
            }
        }
        if (arrayList.isEmpty()) {
            int size = arrayList2.size();
            for (int i5 = 0; i5 < size; i5++) {
                Point[] cornerPoints = ((Barcode) arrayList2.get(i5)).getCornerPoints();
                if (cornerPoints != null) {
                    this.zzb.zzi(this.zzg, zzxn.zzg(Arrays.asList(cornerPoints), i3, i4, 0.0f));
                }
            }
        } else {
            this.zzh = true;
        }
        if (true != this.zzf.zzd()) {
            list = arrayList;
        }
        return Tasks.forResult(list);
    }

    @NonNull
    public final Task<List<Barcode>> process(@NonNull InputImage inputImage) {
        return zzf(super.processBase(inputImage), inputImage.getWidth(), inputImage.getHeight());
    }
}
