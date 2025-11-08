package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.internal.mlkit_vision_barcode.zzah;
import com.google.android.gms.internal.mlkit_vision_barcode.zzaj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzal;
import com.google.android.gms.internal.mlkit_vision_barcode.zzan;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzu;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import java.util.ArrayList;
import java.util.List;

final class zzq implements zzm {
    private boolean zza;
    private final Context zzb;
    private final zzah zzc;
    private final zzwp zzd;
    @Nullable
    private zzaj zze;

    public zzq(Context context, BarcodeScannerOptions barcodeScannerOptions, zzwp zzwp) {
        zzah zzah = new zzah();
        this.zzc = zzah;
        this.zzb = context;
        zzah.zza = barcodeScannerOptions.zza();
        this.zzd = zzwp;
    }

    @WorkerThread
    public final List zza(InputImage inputImage) throws MlKitException {
        zzu[] zzuArr;
        if (this.zze == null) {
            zzc();
        }
        zzaj zzaj = this.zze;
        if (zzaj != null) {
            zzaj zzaj2 = (zzaj) Preconditions.checkNotNull(zzaj);
            zzan zzan = new zzan(inputImage.getWidth(), inputImage.getHeight(), 0, 0, CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()));
            try {
                int format = inputImage.getFormat();
                if (format == -1) {
                    zzuArr = zzaj2.zzf(ObjectWrapper.wrap(inputImage.getBitmapInternal()), zzan);
                } else if (format == 17) {
                    zzuArr = zzaj2.zze(ObjectWrapper.wrap(inputImage.getByteBuffer()), zzan);
                } else if (format == 35) {
                    Image.Plane[] planeArr = (Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes());
                    zzan.zza = planeArr[0].getRowStride();
                    zzuArr = zzaj2.zze(ObjectWrapper.wrap(planeArr[0].getBuffer()), zzan);
                } else if (format == 842094169) {
                    zzuArr = zzaj2.zze(ObjectWrapper.wrap(ImageConvertUtils.getInstance().convertToNv21Buffer(inputImage, false)), zzan);
                } else {
                    throw new MlKitException("Unsupported image format: " + inputImage.getFormat(), 3);
                }
                ArrayList arrayList = new ArrayList();
                for (zzu zzp : zzuArr) {
                    arrayList.add(new Barcode(new zzp(zzp), inputImage.getCoordinatesMatrix()));
                }
                return arrayList;
            } catch (RemoteException e3) {
                throw new MlKitException("Failed to detect with legacy barcode detector", 13, e3);
            }
        } else {
            throw new MlKitException("Error initializing the legacy barcode scanner.", 14);
        }
    }

    @WorkerThread
    public final void zzb() {
        zzaj zzaj = this.zze;
        if (zzaj != null) {
            try {
                zzaj.zzd();
            } catch (RemoteException e3) {
                Log.e("LegacyBarcodeScanner", "Failed to release legacy barcode detector.", e3);
            }
            this.zze = null;
        }
    }

    @WorkerThread
    public final boolean zzc() throws MlKitException {
        if (this.zze != null) {
            return false;
        }
        try {
            zzaj zzd2 = zzal.zza(DynamiteModule.load(this.zzb, DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.DEPRECATED_DYNAMITE_MODULE_ID).instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator")).zzd(ObjectWrapper.wrap(this.zzb), this.zzc);
            this.zze = zzd2;
            if (zzd2 == null) {
                if (!this.zza) {
                    Log.d("LegacyBarcodeScanner", "Request optional module download.");
                    OptionalModuleUtils.requestDownload(this.zzb, OptionalModuleUtils.BARCODE);
                    this.zza = true;
                    zzb.zze(this.zzd, zzrb.OPTIONAL_MODULE_NOT_AVAILABLE);
                    throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
                }
            }
            zzb.zze(this.zzd, zzrb.NO_ERROR);
            return false;
        } catch (RemoteException e3) {
            throw new MlKitException("Failed to create legacy barcode detector.", 13, e3);
        } catch (DynamiteModule.LoadingException e4) {
            throw new MlKitException("Failed to load deprecated vision dynamite module.", 13, e4);
        }
    }
}
