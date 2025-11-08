package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayDeque;
import java.util.Arrays;

final class zzfz {
    private final ArrayDeque zza = new ArrayDeque();

    private zzfz() {
    }

    public static /* bridge */ /* synthetic */ zzdf zza(zzfz zzfz, zzdf zzdf, zzdf zzdf2) {
        zzfz.zzb(zzdf);
        zzfz.zzb(zzdf2);
        zzdf zzdf3 = (zzdf) zzfz.zza.pop();
        while (!zzfz.zza.isEmpty()) {
            zzdf3 = new zzgd((zzdf) zzfz.zza.pop(), zzdf3);
        }
        return zzdf3;
    }

    private final void zzb(zzdf zzdf) {
        if (zzdf.zzh()) {
            int zzc = zzc(zzdf.zzd());
            ArrayDeque arrayDeque = this.zza;
            int zzc2 = zzgd.zzc(zzc + 1);
            if (arrayDeque.isEmpty() || ((zzdf) this.zza.peek()).zzd() >= zzc2) {
                this.zza.push(zzdf);
                return;
            }
            int zzc3 = zzgd.zzc(zzc);
            zzdf zzdf2 = (zzdf) this.zza.pop();
            while (!this.zza.isEmpty() && ((zzdf) this.zza.peek()).zzd() < zzc3) {
                zzdf2 = new zzgd((zzdf) this.zza.pop(), zzdf2);
            }
            zzgd zzgd = new zzgd(zzdf2, zzdf);
            while (!this.zza.isEmpty()) {
                ArrayDeque arrayDeque2 = this.zza;
                if (((zzdf) arrayDeque2.peek()).zzd() >= zzgd.zzc(zzc(zzgd.zzd()) + 1)) {
                    break;
                }
                zzgd = new zzgd((zzdf) this.zza.pop(), zzgd);
            }
            this.zza.push(zzgd);
        } else if (zzdf instanceof zzgd) {
            zzgd zzgd2 = (zzgd) zzdf;
            zzb(zzgd2.zzd);
            zzb(zzgd2.zze);
        } else {
            throw new IllegalArgumentException("Has a new type of ByteString been created? Found ".concat(String.valueOf(zzdf.getClass())));
        }
    }

    private static final int zzc(int i3) {
        int binarySearch = Arrays.binarySearch(zzgd.zza, i3);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }

    public /* synthetic */ zzfz(zzfy zzfy) {
    }
}
