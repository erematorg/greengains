package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import java.util.Collection;

class zzcl extends zzcm {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    public zzcl(int i3) {
    }

    private final void zzd(int i3) {
        Object[] objArr = this.zza;
        int length = objArr.length;
        if (length < i3) {
            int i4 = length + (length >> 1) + 1;
            if (i4 < i3) {
                int highestOneBit = Integer.highestOneBit(i3 - 1);
                i4 = highestOneBit + highestOneBit;
            }
            if (i4 < 0) {
                i4 = Integer.MAX_VALUE;
            }
            this.zza = Arrays.copyOf(objArr, i4);
            this.zzc = false;
        } else if (this.zzc) {
            this.zza = (Object[]) objArr.clone();
            this.zzc = false;
        }
    }

    public final zzcl zza(Object obj) {
        obj.getClass();
        zzd(this.zzb + 1);
        Object[] objArr = this.zza;
        int i3 = this.zzb;
        this.zzb = i3 + 1;
        objArr[i3] = obj;
        return this;
    }

    public /* bridge */ /* synthetic */ zzcm zzb(Object obj) {
        throw null;
    }

    public final zzcm zzc(Iterable iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            zzd(collection.size() + this.zzb);
            if (collection instanceof zzcn) {
                this.zzb = ((zzcn) collection).zza(this.zza, this.zzb);
                return this;
            }
        }
        for (Object zzb2 : iterable) {
            zzb(zzb2);
        }
        return this;
    }
}
