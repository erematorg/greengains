package com.google.mlkit.vision.barcode.bundled.internal;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.Image;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzam;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzan;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzao;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzap;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaq;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzar;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzas;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzat;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzau;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzav;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzaw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzax;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzay;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzba;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbe;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzbm;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcc;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.libraries.barhopper.BarhopperV3;
import com.google.android.libraries.barhopper.MultiScaleDecodingOptions;
import com.google.android.libraries.barhopper.MultiScaleDetectionOptions;
import com.google.android.libraries.barhopper.RecognitionOptions;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.barhopper.deeplearning.zzab;
import com.google.barhopper.deeplearning.zzac;
import com.google.barhopper.deeplearning.zze;
import com.google.barhopper.deeplearning.zzf;
import com.google.barhopper.deeplearning.zzh;
import com.google.barhopper.deeplearning.zzi;
import com.google.barhopper.deeplearning.zzk;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import com.google.photos.vision.barhopper.zzae;
import com.google.photos.vision.barhopper.zzaf;
import com.google.photos.vision.barhopper.zzah;
import com.google.photos.vision.barhopper.zzak;
import com.google.photos.vision.barhopper.zzb;
import com.google.photos.vision.barhopper.zzc;
import com.google.photos.vision.barhopper.zzl;
import com.google.photos.vision.barhopper.zzn;
import com.google.photos.vision.barhopper.zzp;
import com.google.photos.vision.barhopper.zzr;
import com.google.photos.vision.barhopper.zzv;
import com.google.photos.vision.barhopper.zzz;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class zza extends zzbm {
    private static final int[] zza = {5, 7, 7, 7, 5, 5};
    private static final double[][] zzb;
    private final Context zzc;
    private final zzba zzd;
    @Nullable
    private BarhopperV3 zze;

    static {
        double[] dArr = new double[2];
        double[] dArr2 = dArr;
        // fill-array-data instruction
        dArr[0] = 4590068740216009523;
        dArr[1] = 4607182418800017408;
        double[] dArr3 = new double[2];
        double[] dArr4 = dArr3;
        // fill-array-data instruction
        dArr3[0] = 4591870180066957722;
        dArr3[1] = 4607182418800017408;
        double[] dArr5 = new double[2];
        double[] dArr6 = dArr5;
        // fill-array-data instruction
        dArr5[0] = 4593671619917905920;
        dArr5[1] = 4607182418800017408;
        double[] dArr7 = new double[2];
        double[] dArr8 = dArr7;
        // fill-array-data instruction
        dArr7[0] = 4596373779694328218;
        dArr7[1] = 4611686018427387904;
        double[] dArr9 = new double[2];
        double[] dArr10 = dArr9;
        // fill-array-data instruction
        dArr9[0] = 4596373779694328218;
        dArr9[1] = 4602678819172646912;
        double[] dArr11 = new double[2];
        double[] dArr12 = dArr11;
        // fill-array-data instruction
        dArr11[0] = 4594572339843380019;
        dArr11[1] = 4607182418800017408;
        double[] dArr13 = new double[2];
        double[] dArr14 = dArr13;
        // fill-array-data instruction
        dArr13[0] = 4596373779694328218;
        dArr13[1] = 4607182418800017408;
        double[] dArr15 = new double[2];
        double[] dArr16 = dArr15;
        // fill-array-data instruction
        dArr15[0] = 4598175219545276416;
        dArr15[1] = 4607182418800017408;
        double[] dArr17 = new double[2];
        double[] dArr18 = dArr17;
        // fill-array-data instruction
        dArr17[0] = 4599976659396224614;
        dArr17[1] = 4611686018427387904;
        double[] dArr19 = new double[2];
        double[] dArr20 = dArr19;
        // fill-array-data instruction
        dArr19[0] = 4599976659396224614;
        dArr19[1] = 4602678819172646912;
        double[] dArr21 = new double[2];
        double[] dArr22 = dArr21;
        // fill-array-data instruction
        dArr21[0] = 4599976659396224614;
        dArr21[1] = 4613937818241073152;
        double[] dArr23 = new double[2];
        double[] dArr24 = dArr23;
        // fill-array-data instruction
        dArr23[0] = 4599976659396224614;
        dArr23[1] = 4599675818941116265;
        double[] dArr25 = new double[2];
        double[] dArr26 = dArr25;
        // fill-array-data instruction
        dArr25[0] = 4599075939470750515;
        dArr25[1] = 4607182418800017408;
        double[] dArr27 = new double[2];
        double[] dArr28 = dArr27;
        // fill-array-data instruction
        dArr27[0] = 4600877379321698714;
        dArr27[1] = 4607182418800017408;
        double[] dArr29 = new double[2];
        // fill-array-data instruction
        dArr29[0] = 4602678819172646912;
        dArr29[1] = 4607182418800017408;
        double[] dArr30 = dArr2;
        double[] dArr31 = new double[2];
        double[] dArr32 = dArr31;
        // fill-array-data instruction
        dArr31[0] = 4602678819172646912;
        dArr31[1] = 4611686018427387904;
        double[] dArr33 = new double[2];
        double[] dArr34 = dArr33;
        // fill-array-data instruction
        dArr33[0] = 4602678819172646912;
        dArr33[1] = 4602678819172646912;
        double[] dArr35 = new double[2];
        double[] dArr36 = dArr35;
        // fill-array-data instruction
        dArr35[0] = 4602678819172646912;
        dArr35[1] = 4613937818241073152;
        double[] dArr37 = new double[2];
        double[] dArr38 = dArr37;
        // fill-array-data instruction
        dArr37[0] = 4602678819172646912;
        dArr37[1] = 4599675818941116265;
        double[] dArr39 = new double[2];
        double[] dArr40 = dArr39;
        // fill-array-data instruction
        dArr39[0] = 4603579539098121011;
        dArr39[1] = 4607182418800017408;
        double[] dArr41 = new double[2];
        double[] dArr42 = dArr41;
        // fill-array-data instruction
        dArr41[0] = 4605380978949069210;
        dArr41[1] = 4607182418800017408;
        double[] dArr43 = new double[2];
        double[] dArr44 = dArr43;
        // fill-array-data instruction
        dArr43[0] = 4607182418800017408;
        dArr43[1] = 4607182418800017408;
        double[] dArr45 = new double[2];
        double[] dArr46 = dArr45;
        // fill-array-data instruction
        dArr45[0] = 4604029899060858061;
        dArr45[1] = 4611686018427387904;
        double[] dArr47 = new double[2];
        double[] dArr48 = dArr47;
        // fill-array-data instruction
        dArr47[0] = 4604029899060858061;
        dArr47[1] = 4602678819172646912;
        double[] dArr49 = new double[2];
        double[] dArr50 = dArr49;
        // fill-array-data instruction
        dArr49[0] = 4604029899060858061;
        dArr49[1] = 4613937818241073152;
        double[] dArr51 = new double[2];
        double[] dArr52 = dArr51;
        // fill-array-data instruction
        dArr51[0] = 4604029899060858061;
        dArr51[1] = 4599675818941116265;
        double[] dArr53 = new double[2];
        double[] dArr54 = dArr53;
        // fill-array-data instruction
        dArr53[0] = 4607182418800017408;
        dArr53[1] = 4607182418800017408;
        double[] dArr55 = new double[2];
        double[] dArr56 = dArr55;
        // fill-array-data instruction
        dArr55[0] = 4605380978949069210;
        dArr55[1] = 4611686018427387904;
        double[] dArr57 = new double[2];
        double[] dArr58 = dArr57;
        // fill-array-data instruction
        dArr57[0] = 4605380978949069210;
        dArr57[1] = 4602678819172646912;
        double[] dArr59 = new double[2];
        double[] dArr60 = dArr59;
        // fill-array-data instruction
        dArr59[0] = 4605380978949069210;
        dArr59[1] = 4613937818241073152;
        double[] dArr61 = new double[2];
        double[] dArr62 = dArr61;
        // fill-array-data instruction
        dArr61[0] = 4605380978949069210;
        dArr61[1] = 4599675818941116265;
        double[] dArr63 = new double[2];
        double[] dArr64 = dArr63;
        // fill-array-data instruction
        dArr63[0] = 4607182418800017408;
        dArr63[1] = 4607182418800017408;
        double[] dArr65 = new double[2];
        double[] dArr66 = dArr65;
        // fill-array-data instruction
        dArr65[0] = 4606732058837280358;
        dArr65[1] = 4611686018427387904;
        double[] dArr67 = new double[2];
        double[] dArr68 = dArr67;
        // fill-array-data instruction
        dArr67[0] = 4606732058837280358;
        dArr67[1] = 4602678819172646912;
        double[] dArr69 = new double[2];
        // fill-array-data instruction
        dArr69[0] = 4606732058837280358;
        dArr69[1] = 4613937818241073152;
        double[] dArr70 = new double[2];
        // fill-array-data instruction
        dArr70[0] = 4606732058837280358;
        dArr70[1] = 4599675818941116265;
        zzb = new double[][]{dArr30, dArr4, dArr6, dArr8, dArr10, dArr12, dArr14, dArr16, dArr18, dArr20, dArr22, dArr24, dArr26, dArr28, dArr29, dArr32, dArr34, dArr36, dArr38, dArr40, dArr42, dArr44, dArr46, dArr48, dArr50, dArr52, dArr54, dArr56, dArr58, dArr60, dArr62, dArr64, dArr66, dArr68, dArr69, dArr70};
    }

    public zza(Context context, zzba zzba) {
        this.zzc = context;
        this.zzd = zzba;
    }

    private final RecognitionOptions zzg() {
        RecognitionOptions recognitionOptions = new RecognitionOptions();
        recognitionOptions.setBarcodeFormats(this.zzd.zza());
        recognitionOptions.setOutputUnrecognizedBarcodes(this.zzd.zzb());
        recognitionOptions.setEnableQrAlignmentGrid(true);
        recognitionOptions.setEnableUseKeypointAsFinderPattern(true);
        return recognitionOptions;
    }

    @Nullable
    private static zzan zzh(@Nullable zzl zzl, @Nullable String str, String str2) {
        String str3 = null;
        if (zzl == null || str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile(str2).matcher(str);
        int zzf = zzl.zzf();
        int zzd2 = zzl.zzd();
        int zza2 = zzl.zza();
        int zzb2 = zzl.zzb();
        int zzc2 = zzl.zzc();
        int zze2 = zzl.zze();
        boolean zzj = zzl.zzj();
        if (matcher.find()) {
            str3 = matcher.group(1);
        }
        return new zzan(zzf, zzd2, zza2, zzb2, zzc2, zze2, zzj, str3);
    }

    private final BarhopperProto$BarhopperResponse zzi(ByteBuffer byteBuffer, zzcc zzcc, RecognitionOptions recognitionOptions) {
        BarhopperV3 barhopperV3 = (BarhopperV3) Preconditions.checkNotNull(this.zze);
        if (((ByteBuffer) Preconditions.checkNotNull(byteBuffer)).isDirect()) {
            return barhopperV3.recognize(zzcc.zzd(), zzcc.zza(), byteBuffer, recognitionOptions);
        }
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return barhopperV3.recognize(zzcc.zzd(), zzcc.zza(), byteBuffer.array(), recognitionOptions);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return barhopperV3.recognize(zzcc.zzd(), zzcc.zza(), bArr, recognitionOptions);
    }

    private final List zzj(IObjectWrapper iObjectWrapper, zzcc zzcc, RecognitionOptions recognitionOptions) {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse;
        zzar zzar;
        zzau zzau;
        zzav zzav;
        zzax zzax;
        zzaw zzaw;
        zzas zzas;
        zzao zzao;
        Iterator it;
        zzap zzap;
        int i3;
        zzaq zzaq;
        int i4;
        Point[] pointArr;
        int i5;
        int i6;
        int i7;
        zzau[] zzauArr;
        zzar[] zzarArr;
        zzam[] zzamArr;
        zzcc zzcc2 = zzcc;
        RecognitionOptions recognitionOptions2 = recognitionOptions;
        int zzb2 = zzcc.zzb();
        int i8 = -1;
        int i9 = 0;
        if (zzb2 != -1) {
            if (zzb2 != 17) {
                if (zzb2 == 35) {
                    barhopperProto$BarhopperResponse = zzi(((Image) Preconditions.checkNotNull((Image) ObjectWrapper.unwrap(iObjectWrapper))).getPlanes()[0].getBuffer(), zzcc2, recognitionOptions2);
                } else if (zzb2 != 842094169) {
                    throw new IllegalArgumentException(a.k("Unsupported image format: ", zzcc.zzb()));
                }
            }
            barhopperProto$BarhopperResponse = zzi((ByteBuffer) ObjectWrapper.unwrap(iObjectWrapper), zzcc2, recognitionOptions2);
        } else {
            barhopperProto$BarhopperResponse = ((BarhopperV3) Preconditions.checkNotNull(this.zze)).recognize((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), recognitionOptions2);
        }
        ArrayList arrayList = new ArrayList();
        Matrix uprightRotationMatrix = ImageUtils.getInstance().getUprightRotationMatrix(zzcc.zzd(), zzcc.zza(), zzcc.zzc());
        Iterator it2 = barhopperProto$BarhopperResponse.zzc().iterator();
        while (it2.hasNext()) {
            zzc zzc2 = (zzc) it2.next();
            if (zzc2.zza() > 0 && uprightRotationMatrix != null) {
                float[] fArr = new float[8];
                List zzo = zzc2.zzo();
                int zza2 = zzc2.zza();
                for (int i10 = i9; i10 < zza2; i10++) {
                    int i11 = i10 + i10;
                    fArr[i11] = (float) ((zzaf) zzo.get(i10)).zza();
                    fArr[i11 + 1] = (float) ((zzaf) zzo.get(i10)).zzb();
                }
                uprightRotationMatrix.mapPoints(fArr);
                int zzc3 = zzcc.zzc();
                for (int i12 = i9; i12 < zza2; i12++) {
                    zzb zzb3 = (zzb) zzc2.zzH();
                    int i13 = i12 + i12;
                    zzae zzc4 = zzaf.zzc();
                    zzc4.zza((int) fArr[i13]);
                    zzc4.zzb((int) fArr[i13 + 1]);
                    zzb3.zza((i12 + zzc3) % zza2, (zzaf) zzc4.zzj());
                    zzc2 = (zzc) zzb3.zzj();
                }
            }
            if (zzc2.zzt()) {
                zzv zzh = zzc2.zzh();
                zzar = new zzar(zzh.zzf() + i8, zzh.zzc(), zzh.zze(), zzh.zzd());
            } else {
                zzar = null;
            }
            if (zzc2.zzv()) {
                zzco zzb4 = zzc2.zzb();
                zzau = new zzau(zzb4.zzd() + i8, zzb4.zzc());
            } else {
                zzau = null;
            }
            if (zzc2.zzw()) {
                zzah zzj = zzc2.zzj();
                zzav = new zzav(zzj.zzc(), zzj.zzd());
            } else {
                zzav = null;
            }
            if (zzc2.zzy()) {
                com.google.photos.vision.barhopper.zzao zzl = zzc2.zzl();
                zzax = new zzax(zzl.zzd(), zzl.zzc(), zzl.zze() + i8);
            } else {
                zzax = null;
            }
            if (zzc2.zzx()) {
                zzak zzk = zzc2.zzk();
                zzaw = new zzaw(zzk.zzc(), zzk.zzd());
            } else {
                zzaw = null;
            }
            if (zzc2.zzu()) {
                zzz zzi = zzc2.zzi();
                zzas = new zzas(zzi.zza(), zzi.zzb());
            } else {
                zzas = null;
            }
            if (zzc2.zzq()) {
                zzn zzd2 = zzc2.zzd();
                zzao = new zzao(zzd2.zzj(), zzd2.zze(), zzd2.zzf(), zzd2.zzh(), zzd2.zzi(), zzh(zzd2.zzb(), zzc2.zzm().zzn() ? zzc2.zzm().zzt() : null, "DTSTART:([0-9TZ]*)"), zzh(zzd2.zza(), zzc2.zzm().zzn() ? zzc2.zzm().zzt() : null, "DTEND:([0-9TZ]*)"));
            } else {
                zzao = null;
            }
            if (zzc2.zzr()) {
                zzp zze2 = zzc2.zze();
                zzck zza3 = zze2.zza();
                zzat zzat = zza3 != null ? new zzat(zza3.zzd(), zza3.zzi(), zza3.zzh(), zza3.zzc(), zza3.zzf(), zza3.zze(), zza3.zzj()) : null;
                String zzd3 = zze2.zzd();
                String zze3 = zze2.zze();
                List zzi2 = zze2.zzi();
                if (zzi2.isEmpty()) {
                    zzauArr = null;
                } else {
                    zzauArr = new zzau[zzi2.size()];
                    for (int i14 = i9; i14 < zzi2.size(); i14++) {
                        zzauArr[i14] = new zzau(((zzco) zzi2.get(i14)).zzd() + i8, ((zzco) zzi2.get(i14)).zzc());
                    }
                }
                List zzh2 = zze2.zzh();
                if (zzh2.isEmpty()) {
                    it = it2;
                    zzarArr = null;
                } else {
                    zzar[] zzarArr2 = new zzar[zzh2.size()];
                    int i15 = 0;
                    while (i15 < zzh2.size()) {
                        zzarArr2[i15] = new zzar(((zzv) zzh2.get(i15)).zzf() + i8, ((zzv) zzh2.get(i15)).zzc(), ((zzv) zzh2.get(i15)).zze(), ((zzv) zzh2.get(i15)).zzd());
                        i15++;
                        zzcc zzcc3 = zzcc;
                        it2 = it2;
                        i8 = -1;
                    }
                    it = it2;
                    zzarArr = zzarArr2;
                }
                String[] strArr = (String[]) zze2.zzj().toArray(new String[0]);
                List zzf = zze2.zzf();
                if (zzf.isEmpty()) {
                    zzamArr = null;
                } else {
                    zzamArr = new zzam[zzf.size()];
                    int i16 = 0;
                    while (i16 < zzf.size()) {
                        zzamArr[i16] = new zzam(((zzci) zzf.get(i16)).zzc() - 1, (String[]) ((zzci) zzf.get(i16)).zzb().toArray(new String[0]));
                        i16++;
                        zzf = zzf;
                    }
                }
                i3 = 0;
                zzap = new zzap(zzat, zzd3, zze3, zzauArr, zzarArr, strArr, zzamArr);
            } else {
                it = it2;
                i3 = i9;
                zzap = null;
            }
            if (zzc2.zzs()) {
                zzr zzf2 = zzc2.zzf();
                zzaq = new zzaq(zzf2.zzi(), zzf2.zzk(), zzf2.zzq(), zzf2.zzo(), zzf2.zzl(), zzf2.zze(), zzf2.zzc(), zzf2.zzd(), zzf2.zzf(), zzf2.zzp(), zzf2.zzm(), zzf2.zzj(), zzf2.zzh(), zzf2.zzn());
            } else {
                zzaq = null;
            }
            switch (zzc2.zzz() - 1) {
                case 0:
                    i4 = i3;
                    break;
                case 1:
                    i4 = 1;
                    break;
                case 2:
                    i4 = 2;
                    break;
                case 3:
                    i4 = 4;
                    break;
                case 4:
                    i4 = 8;
                    break;
                case 5:
                    i7 = 16;
                    break;
                case 6:
                    i7 = 32;
                    break;
                case 7:
                    i7 = 64;
                    break;
                case 8:
                    i7 = 128;
                    break;
                case 9:
                    i7 = 256;
                    break;
                case 10:
                    i7 = 512;
                    break;
                case 11:
                    i7 = 1024;
                    break;
                case 12:
                    i7 = 2048;
                    break;
                case 13:
                    i7 = 4096;
                    break;
                default:
                    i4 = -1;
                    break;
            }
            i4 = i7;
            String zzn = zzc2.zzn();
            String zzt = zzc2.zzm().zzn() ? zzc2.zzm().zzt() : null;
            byte[] zzw = zzc2.zzm().zzw();
            List zzo2 = zzc2.zzo();
            if (zzo2.isEmpty()) {
                pointArr = null;
            } else {
                Point[] pointArr2 = new Point[zzo2.size()];
                for (int i17 = i3; i17 < zzo2.size(); i17++) {
                    pointArr2[i17] = new Point(((zzaf) zzo2.get(i17)).zza(), ((zzaf) zzo2.get(i17)).zzb());
                }
                pointArr = pointArr2;
            }
            switch (zzc2.zzA() - 1) {
                case 1:
                    i5 = 1;
                    continue;
                case 2:
                    i5 = 2;
                    continue;
                case 3:
                    i6 = 3;
                    break;
                case 4:
                    i5 = 4;
                    continue;
                case 5:
                    i6 = 5;
                    break;
                case 6:
                    i6 = 6;
                    break;
                case 7:
                    i6 = 7;
                    break;
                case 8:
                    i5 = 8;
                    continue;
                case 9:
                    i6 = 9;
                    break;
                case 10:
                    i6 = 10;
                    break;
                case 11:
                    i6 = 11;
                    break;
                case 12:
                    i6 = 12;
                    break;
                default:
                    i5 = i3;
                    continue;
            }
            i5 = i6;
            arrayList.add(new zzay(i4, zzn, zzt, zzw, pointArr, i5, zzar, zzau, zzav, zzax, zzaw, zzas, zzao, zzap, zzaq));
            zzcc zzcc4 = zzcc;
            i9 = i3;
            i8 = -1;
            it2 = it;
        }
        return arrayList;
    }

    public final List zzb(IObjectWrapper iObjectWrapper, zzcc zzcc) {
        return zzj(iObjectWrapper, zzcc, zzg());
    }

    public final List zzc(IObjectWrapper iObjectWrapper, zzcc zzcc, zzbc zzbc) {
        RecognitionOptions zzg = zzg();
        MultiScaleDecodingOptions multiScaleDecodingOptions = new MultiScaleDecodingOptions();
        multiScaleDecodingOptions.setExtraScales(zzbc.zza().zzc());
        multiScaleDecodingOptions.setMinimumDetectedDimension(zzbc.zza().zza());
        multiScaleDecodingOptions.setSkipProcessingIfBarcodeFound(zzbc.zza().zzb());
        zzg.setMultiScaleDecodingOptions(multiScaleDecodingOptions);
        MultiScaleDetectionOptions multiScaleDetectionOptions = new MultiScaleDetectionOptions();
        multiScaleDetectionOptions.setExtraScales(zzbc.zza().zzc());
        zzg.setMultiScaleDetectionOptions(multiScaleDetectionOptions);
        zzg.setQrEnableFourthCornerApproximation(zzbc.zzb());
        return zzj(iObjectWrapper, zzcc, zzg);
    }

    public final void zzd() {
        InputStream open;
        InputStream open2;
        if (this.zze == null) {
            this.zze = new BarhopperV3();
            zzh zza2 = zzi.zza();
            zze zza3 = zzf.zza();
            int i3 = 16;
            int i4 = 0;
            for (int i5 = 0; i5 < 6; i5++) {
                com.google.barhopper.deeplearning.zzb zza4 = com.google.barhopper.deeplearning.zzc.zza();
                zza4.zzc(i3);
                zza4.zzd(i3);
                for (int i6 = 0; i6 < zza[i5]; i6++) {
                    double[] dArr = zzb[i4];
                    float sqrt = (float) Math.sqrt(dArr[1]);
                    float f2 = (float) (dArr[0] * 320.0d);
                    zza4.zza(f2 / sqrt);
                    zza4.zzb(f2 * sqrt);
                    i4++;
                }
                i3 += i3;
                zza3.zza(zza4);
            }
            zza2.zza(zza3);
            try {
                InputStream open3 = this.zzc.getAssets().open("mlkit_barcode_models/barcode_ssd_mobilenet_v1_dmp25_quant.tflite");
                try {
                    open = this.zzc.getAssets().open("mlkit_barcode_models/oned_auto_regressor_mobile.tflite");
                    open2 = this.zzc.getAssets().open("mlkit_barcode_models/oned_feature_extractor_mobile.tflite");
                    zzk zza5 = BarhopperV3Options.zza();
                    zza2.zzb(zzdf.zzs(open3));
                    zza5.zza(zza2);
                    zzab zza6 = zzac.zza();
                    zza6.zza(zzdf.zzs(open));
                    zza6.zzb(zzdf.zzs(open2));
                    zza5.zzb(zza6);
                    ((BarhopperV3) Preconditions.checkNotNull(this.zze)).create((BarhopperV3Options) zza5.zzj());
                    if (open2 != null) {
                        open2.close();
                    }
                    if (open != null) {
                        open.close();
                    }
                    if (open3 != null) {
                        open3.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    if (open3 != null) {
                        open3.close();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                throw new IllegalStateException("Failed to open Barcode models", e3);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            return;
        }
        throw th;
        throw th;
    }

    public final void zze(zzbe zzbe) {
        zzd();
    }

    public final void zzf() {
        BarhopperV3 barhopperV3 = this.zze;
        if (barhopperV3 != null) {
            barhopperV3.close();
            this.zze = null;
        }
    }
}
