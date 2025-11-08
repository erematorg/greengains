package com.google.android.gms.internal.fido;

import java.util.Arrays;
import org.msgpack.core.MessagePack;

public final class zzdj extends zzdr {
    private final boolean zza;

    public zzdj(boolean z2) {
        this.zza = z2;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzdr.zzd(MessagePack.Code.NEGFIXINT_PREFIX) != zzdr.zza()) {
            return zzdr.zzd(MessagePack.Code.NEGFIXINT_PREFIX) - zzdr.zza();
        }
        zzdj zzdj = (zzdj) zzdr;
        int i3 = 21;
        int i4 = true != this.zza ? 20 : 21;
        if (true != zzdj.zza) {
            i3 = 20;
        }
        return i4 - i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && zzdj.class == obj.getClass() && this.zza == ((zzdj) obj).zza;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzdr.zzd(MessagePack.Code.NEGFIXINT_PREFIX)), Boolean.valueOf(this.zza)});
    }

    public final String toString() {
        return Boolean.toString(this.zza);
    }

    public final int zza() {
        return zzdr.zzd(MessagePack.Code.NEGFIXINT_PREFIX);
    }
}
