package com.google.android.gms.internal.fido;

import A.a;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import java.util.Arrays;

public final class zzdp extends zzdr {
    private final String zza;

    public zzdp(String str) {
        this.zza = str;
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzdr zzdr = (zzdr) obj;
        if (zzdr.zzd(CBORConstants.BYTE_EMPTY_STRING) != zzdr.zza()) {
            return zzdr.zzd(CBORConstants.BYTE_EMPTY_STRING) - zzdr.zza();
        }
        String str = this.zza;
        int length = str.length();
        String str2 = ((zzdp) zzdr).zza;
        return length != str2.length() ? str.length() - str2.length() : str.compareTo(str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && zzdp.class == obj.getClass()) {
            return this.zza.equals(((zzdp) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(zzdr.zzd(CBORConstants.BYTE_EMPTY_STRING)), this.zza});
    }

    public final String toString() {
        return a.l("\"", this.zza, "\"");
    }

    public final int zza() {
        return zzdr.zzd(CBORConstants.BYTE_EMPTY_STRING);
    }
}
