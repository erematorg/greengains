package com.google.android.odml.image;

import A.a;

final class zzb extends zzh {
    private Integer zza;
    private Integer zzb;

    public final zzh zza(int i3) {
        this.zza = Integer.valueOf(i3);
        return this;
    }

    public final zzh zzb(int i3) {
        this.zzb = Integer.valueOf(i3);
        return this;
    }

    public final ImageProperties zzc() {
        Integer num = this.zza;
        if (num != null && this.zzb != null) {
            return new zzc(num.intValue(), this.zzb.intValue(), (zza) null);
        }
        StringBuilder sb = new StringBuilder();
        if (this.zza == null) {
            sb.append(" imageFormat");
        }
        if (this.zzb == null) {
            sb.append(" storageType");
        }
        String valueOf = String.valueOf(sb);
        throw new IllegalStateException(a.n(new StringBuilder(valueOf.length() + 28), "Missing required properties:", valueOf));
    }
}
