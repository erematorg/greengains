package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Arrays;

class zzj extends zzk {
    Object[] zza = new Object[4];
    int zzb = 0;
    boolean zzc;

    public zzj(int i3) {
    }

    private final void zzb(int i3) {
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

    public final zzj zza(Object obj) {
        obj.getClass();
        zzb(this.zzb + 1);
        Object[] objArr = this.zza;
        int i3 = this.zzb;
        this.zzb = i3 + 1;
        objArr[i3] = obj;
        return this;
    }
}
