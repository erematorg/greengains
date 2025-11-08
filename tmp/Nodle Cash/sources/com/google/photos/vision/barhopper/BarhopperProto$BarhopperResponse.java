package com.google.photos.vision.barhopper;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzds;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzer;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;
import java.util.List;

public final class BarhopperProto$BarhopperResponse extends zzeh<BarhopperProto$BarhopperResponse, zzh> implements zzfn {
    /* access modifiers changed from: private */
    public static final BarhopperProto$BarhopperResponse zzb;
    private int zzd;
    private zzeo zze = zzeh.zzP();
    private int zzf;
    private String zzg = "";
    private zzdf zzh = zzdf.zzb;
    private byte zzi = 2;

    static {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse = new BarhopperProto$BarhopperResponse();
        zzb = barhopperProto$BarhopperResponse;
        zzeh.zzV(BarhopperProto$BarhopperResponse.class, barhopperProto$BarhopperResponse);
    }

    private BarhopperProto$BarhopperResponse() {
    }

    public static BarhopperProto$BarhopperResponse zzb(byte[] bArr, zzds zzds) throws zzer {
        return (BarhopperProto$BarhopperResponse) zzeh.zzL(zzb, bArr, zzds);
    }

    @NonNull
    public final List zzc() {
        return this.zze;
    }

    @NonNull
    public final Object zzg(int i3, @NonNull Object obj, @NonNull Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzi);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001Л\u0002ᴌ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zzd", "zze", zzc.class, "zzf", zzai.zza, "zzg", "zzh"});
        } else if (i4 == 3) {
            return new BarhopperProto$BarhopperResponse();
        } else {
            if (i4 == 4) {
                return new zzh((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzi = obj == null ? (byte) 0 : 1;
            return null;
        }
    }
}
