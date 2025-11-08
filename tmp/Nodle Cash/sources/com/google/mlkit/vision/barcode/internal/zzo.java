package com.google.mlkit.vision.barcode.internal;

import android.content.Context;
import android.media.Image;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.mlkit.dynamite.barcode.ModuleDescriptor;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyd;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyo;
import com.google.android.gms.internal.mlkit_vision_barcode.zzyu;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.CommonConvertUtils;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.ArrayList;
import java.util.List;

final class zzo implements zzm {
    private static final zzcs zza = zzcs.zzh(OptionalModuleUtils.BARCODE_MODULE_ID, OptionalModuleUtils.TFLITE_DYNAMITE_MODULE_ID);
    private boolean zzb;
    private boolean zzc;
    private boolean zzd;
    private final Context zze;
    private final BarcodeScannerOptions zzf;
    private final zzwp zzg;
    @Nullable
    private zzyl zzh;

    public zzo(Context context, BarcodeScannerOptions barcodeScannerOptions, zzwp zzwp) {
        this.zze = context;
        this.zzf = barcodeScannerOptions;
        this.zzg = zzwp;
    }

    public static boolean zzd(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > 0;
    }

    @WorkerThread
    public final List zza(InputImage inputImage) throws MlKitException {
        if (this.zzh == null) {
            zzc();
        }
        zzyl zzyl = (zzyl) Preconditions.checkNotNull(this.zzh);
        if (!this.zzb) {
            try {
                zzyl.zze();
                this.zzb = true;
            } catch (RemoteException e3) {
                throw new MlKitException("Failed to init barcode scanner.", 13, e3);
            }
        }
        int width = inputImage.getWidth();
        if (inputImage.getFormat() == 35) {
            width = ((Image.Plane[]) Preconditions.checkNotNull(inputImage.getPlanes()))[0].getRowStride();
        }
        try {
            List<zzyb> zzd2 = zzyl.zzd(ImageUtils.getInstance().getImageDataWrapper(inputImage), new zzyu(inputImage.getFormat(), width, inputImage.getHeight(), CommonConvertUtils.convertToMVRotation(inputImage.getRotationDegrees()), SystemClock.elapsedRealtime()));
            ArrayList arrayList = new ArrayList();
            for (zzyb zzn : zzd2) {
                arrayList.add(new Barcode(new zzn(zzn), inputImage.getCoordinatesMatrix()));
            }
            return arrayList;
        } catch (RemoteException e4) {
            throw new MlKitException("Failed to run barcode scanner.", 13, e4);
        }
    }

    @WorkerThread
    public final void zzb() {
        zzyl zzyl = this.zzh;
        if (zzyl != null) {
            try {
                zzyl.zzf();
            } catch (RemoteException e3) {
                Log.e("DecoupledBarcodeScanner", "Failed to release barcode scanner.", e3);
            }
            this.zzh = null;
            this.zzb = false;
        }
    }

    @WorkerThread
    public final boolean zzc() throws MlKitException {
        if (this.zzh != null) {
            return this.zzc;
        }
        if (zzd(this.zze)) {
            this.zzc = true;
            try {
                this.zzh = zze(DynamiteModule.PREFER_LOCAL, ModuleDescriptor.MODULE_ID, "com.google.mlkit.vision.barcode.bundled.internal.ThickBarcodeScannerCreator");
            } catch (DynamiteModule.LoadingException e3) {
                throw new MlKitException("Failed to load the bundled barcode module.", 13, e3);
            } catch (RemoteException e4) {
                throw new MlKitException("Failed to create thick barcode scanner.", 13, e4);
            }
        } else {
            this.zzc = false;
            if (!OptionalModuleUtils.areAllRequiredModulesAvailable(this.zze, (List<String>) zza)) {
                if (!this.zzd) {
                    OptionalModuleUtils.requestDownload(this.zze, (List<String>) zzcs.zzh(OptionalModuleUtils.BARCODE, OptionalModuleUtils.TFLITE_DYNAMITE));
                    this.zzd = true;
                }
                zzb.zze(this.zzg, zzrb.OPTIONAL_MODULE_NOT_AVAILABLE);
                throw new MlKitException("Waiting for the barcode module to be downloaded. Please wait.", 14);
            }
            try {
                this.zzh = zze(DynamiteModule.PREFER_REMOTE, OptionalModuleUtils.BARCODE_MODULE_ID, "com.google.android.gms.vision.barcode.mlkit.BarcodeScannerCreator");
            } catch (RemoteException | DynamiteModule.LoadingException e5) {
                zzb.zze(this.zzg, zzrb.OPTIONAL_MODULE_INIT_ERROR);
                throw new MlKitException("Failed to create thin barcode scanner.", 13, e5);
            }
        }
        zzb.zze(this.zzg, zzrb.NO_ERROR);
        return this.zzc;
    }

    @VisibleForTesting
    public final zzyl zze(DynamiteModule.VersionPolicy versionPolicy, String str, String str2) throws DynamiteModule.LoadingException, RemoteException {
        zzyo zza2 = zzyn.zza(DynamiteModule.load(this.zze, versionPolicy, str).instantiate(str2));
        BarcodeScannerOptions barcodeScannerOptions = this.zzf;
        IObjectWrapper wrap = ObjectWrapper.wrap(this.zze);
        int zza3 = barcodeScannerOptions.zza();
        boolean z2 = true;
        if (!barcodeScannerOptions.zzd() && this.zzf.zzb() == null) {
            z2 = false;
        }
        return zza2.zzd(wrap, new zzyd(zza3, z2));
    }
}
