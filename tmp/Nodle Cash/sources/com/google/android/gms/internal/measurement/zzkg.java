package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzkg {
    private static volatile int zzd = 100;
    int zza;
    int zzb;
    zzkk zzc;

    public static long zza(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    public static int zze(int i3) {
        return (-(i3 & 1)) ^ (i3 >>> 1);
    }

    public abstract double zza() throws IOException;

    public abstract int zza(int i3) throws zzlk;

    public abstract float zzb() throws IOException;

    public abstract void zzb(int i3) throws zzlk;

    public abstract int zzc();

    public abstract void zzc(int i3);

    public abstract int zzd() throws IOException;

    public abstract boolean zzd(int i3) throws IOException;

    public abstract int zze() throws IOException;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract int zzi() throws IOException;

    public abstract int zzj() throws IOException;

    public abstract long zzk() throws IOException;

    public abstract long zzl() throws IOException;

    public abstract long zzm() throws IOException;

    public abstract long zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract zzjs zzq() throws IOException;

    public abstract String zzr() throws IOException;

    public abstract String zzs() throws IOException;

    public abstract boolean zzt() throws IOException;

    public abstract boolean zzu() throws IOException;

    private zzkg() {
        this.zzb = zzd;
    }

    public static zzkg zza(byte[] bArr, int i3, int i4, boolean z2) {
        zzkf zzkf = new zzkf(bArr, i4);
        try {
            zzkf.zza(i4);
            return zzkf;
        } catch (zzlk e3) {
            throw new IllegalArgumentException(e3);
        }
    }
}
