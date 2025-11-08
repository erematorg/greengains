package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzdn extends zzcx {
    private static final Logger zzb = Logger.getLogger(zzdn.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzc = zzgz.zzx();
    zzdo zza;

    private zzdn() {
        throw null;
    }

    public static int zzA(int i3) {
        return (352 - (Integer.numberOfLeadingZeros(i3) * 9)) >>> 6;
    }

    public static int zzB(long j2) {
        return (640 - (Long.numberOfLeadingZeros(j2) * 9)) >>> 6;
    }

    @Deprecated
    public static int zzw(int i3, zzfm zzfm, zzge zzge) {
        int zzA = zzA(i3 << 3);
        return ((zzcq) zzfm).zzB(zzge) + zzA + zzA;
    }

    public static int zzx(zzfm zzfm) {
        int zzF = zzfm.zzF();
        return zzA(zzF) + zzF;
    }

    public static int zzy(zzfm zzfm, zzge zzge) {
        int zzB = ((zzcq) zzfm).zzB(zzge);
        return zzA(zzB) + zzB;
    }

    public static int zzz(String str) {
        int i3;
        try {
            i3 = zzhe.zze(str);
        } catch (zzhd unused) {
            i3 = str.getBytes(zzep.zza).length;
        }
        return zzA(i3) + i3;
    }

    public final void zzC() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzD(String str, zzhd zzhd) throws IOException {
        zzb.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzhd);
        byte[] bytes = str.getBytes(zzep.zza);
        try {
            int length = bytes.length;
            zzt(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzdl(e3);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b3) throws IOException;

    public abstract void zzd(int i3, boolean z2) throws IOException;

    public abstract void zze(int i3, zzdf zzdf) throws IOException;

    public abstract void zzf(int i3, int i4) throws IOException;

    public abstract void zzg(int i3) throws IOException;

    public abstract void zzh(int i3, long j2) throws IOException;

    public abstract void zzi(long j2) throws IOException;

    public abstract void zzj(int i3, int i4) throws IOException;

    public abstract void zzk(int i3) throws IOException;

    public abstract void zzl(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void zzm(int i3, zzfm zzfm, zzge zzge) throws IOException;

    public abstract void zzn(int i3, zzfm zzfm) throws IOException;

    public abstract void zzo(int i3, zzdf zzdf) throws IOException;

    public abstract void zzp(int i3, String str) throws IOException;

    public abstract void zzr(int i3, int i4) throws IOException;

    public abstract void zzs(int i3, int i4) throws IOException;

    public abstract void zzt(int i3) throws IOException;

    public abstract void zzu(int i3, long j2) throws IOException;

    public abstract void zzv(long j2) throws IOException;

    public /* synthetic */ zzdn(zzdm zzdm) {
    }
}
