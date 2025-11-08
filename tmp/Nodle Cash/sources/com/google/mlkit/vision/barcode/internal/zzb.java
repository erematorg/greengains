package com.google.mlkit.vision.barcode.internal;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcp;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrb;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrc;
import com.google.android.gms.internal.mlkit_vision_barcode.zzrn;
import com.google.android.gms.internal.mlkit_vision_barcode.zzro;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvw;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvx;
import com.google.android.gms.internal.mlkit_vision_barcode.zzvz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzwp;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public final class zzb {
    @VisibleForTesting
    static final AtomicReference zza = new AtomicReference();
    private static final SparseArray zzb;
    private static final SparseArray zzc;
    @SuppressLint({"UseSparseArrays"})
    private static final Map zzd;

    static {
        SparseArray sparseArray = new SparseArray();
        zzb = sparseArray;
        SparseArray sparseArray2 = new SparseArray();
        zzc = sparseArray2;
        sparseArray.put(-1, zzrn.FORMAT_UNKNOWN);
        sparseArray.put(1, zzrn.FORMAT_CODE_128);
        sparseArray.put(2, zzrn.FORMAT_CODE_39);
        sparseArray.put(4, zzrn.FORMAT_CODE_93);
        sparseArray.put(8, zzrn.FORMAT_CODABAR);
        sparseArray.put(16, zzrn.FORMAT_DATA_MATRIX);
        sparseArray.put(32, zzrn.FORMAT_EAN_13);
        sparseArray.put(64, zzrn.FORMAT_EAN_8);
        sparseArray.put(128, zzrn.FORMAT_ITF);
        sparseArray.put(256, zzrn.FORMAT_QR_CODE);
        sparseArray.put(512, zzrn.FORMAT_UPC_A);
        sparseArray.put(1024, zzrn.FORMAT_UPC_E);
        sparseArray.put(2048, zzrn.FORMAT_PDF417);
        sparseArray.put(4096, zzrn.FORMAT_AZTEC);
        sparseArray2.put(0, zzro.TYPE_UNKNOWN);
        sparseArray2.put(1, zzro.TYPE_CONTACT_INFO);
        sparseArray2.put(2, zzro.TYPE_EMAIL);
        sparseArray2.put(3, zzro.TYPE_ISBN);
        sparseArray2.put(4, zzro.TYPE_PHONE);
        sparseArray2.put(5, zzro.TYPE_PRODUCT);
        sparseArray2.put(6, zzro.TYPE_SMS);
        sparseArray2.put(7, zzro.TYPE_TEXT);
        sparseArray2.put(8, zzro.TYPE_URL);
        sparseArray2.put(9, zzro.TYPE_WIFI);
        sparseArray2.put(10, zzro.TYPE_GEO);
        sparseArray2.put(11, zzro.TYPE_CALENDAR_EVENT);
        sparseArray2.put(12, zzro.TYPE_DRIVER_LICENSE);
        HashMap hashMap = new HashMap();
        zzd = hashMap;
        hashMap.put(1, zzvw.CODE_128);
        hashMap.put(2, zzvw.CODE_39);
        hashMap.put(4, zzvw.CODE_93);
        hashMap.put(8, zzvw.CODABAR);
        hashMap.put(16, zzvw.DATA_MATRIX);
        hashMap.put(32, zzvw.EAN_13);
        hashMap.put(64, zzvw.EAN_8);
        hashMap.put(128, zzvw.ITF);
        hashMap.put(256, zzvw.QR_CODE);
        hashMap.put(512, zzvw.UPC_A);
        hashMap.put(1024, zzvw.UPC_E);
        hashMap.put(2048, zzvw.PDF417);
        hashMap.put(4096, zzvw.AZTEC);
    }

    public static zzrn zza(@Barcode.BarcodeFormat int i3) {
        zzrn zzrn = (zzrn) zzb.get(i3);
        return zzrn == null ? zzrn.FORMAT_UNKNOWN : zzrn;
    }

    public static zzro zzb(@Barcode.BarcodeValueType int i3) {
        zzro zzro = (zzro) zzc.get(i3);
        return zzro == null ? zzro.TYPE_UNKNOWN : zzro;
    }

    public static zzvz zzc(BarcodeScannerOptions barcodeScannerOptions) {
        int zza2 = barcodeScannerOptions.zza();
        zzcp zzcp = new zzcp();
        if (zza2 == 0) {
            zzcp.zze(zzd.values());
        } else {
            for (Map.Entry entry : zzd.entrySet()) {
                if ((((Integer) entry.getKey()).intValue() & zza2) != 0) {
                    zzcp.zzd((zzvw) entry.getValue());
                }
            }
        }
        zzvx zzvx = new zzvx();
        zzvx.zzb(zzcp.zzf());
        return zzvx.zzc();
    }

    public static String zzd() {
        return true != zzf() ? "play-services-mlkit-barcode-scanning" : "barcode-scanning";
    }

    public static void zze(zzwp zzwp, zzrb zzrb) {
        zzwp.zzf(new zza(zzrb), zzrc.ON_DEVICE_BARCODE_LOAD);
    }

    public static boolean zzf() {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((Boolean) atomicReference.get()).booleanValue();
        }
        boolean zzd2 = zzo.zzd(MlKitContext.getInstance().getApplicationContext());
        atomicReference.set(Boolean.valueOf(zzd2));
        return zzd2;
    }
}
