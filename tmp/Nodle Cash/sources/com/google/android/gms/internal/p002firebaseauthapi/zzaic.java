package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaic  reason: invalid package */
public abstract class zzaic {
    private static volatile int zze = 100;
    int zza;
    int zzb;
    int zzc;
    zzail zzd;
    private boolean zzf;

    public static int zza(int i3) {
        return (-(i3 & 1)) ^ (i3 >>> 1);
    }

    public abstract double zza() throws IOException;

    public abstract float zzb() throws IOException;

    public abstract int zzb(int i3) throws zzajk;

    public abstract int zzc();

    public abstract void zzc(int i3) throws zzajk;

    public abstract int zzd() throws IOException;

    public abstract void zzd(int i3);

    public abstract int zze() throws IOException;

    public abstract boolean zze(int i3) throws IOException;

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

    public abstract zzaho zzq() throws IOException;

    public abstract String zzr() throws IOException;

    public abstract String zzs() throws IOException;

    public abstract boolean zzt() throws IOException;

    public abstract boolean zzu() throws IOException;

    private zzaic() {
        this.zzb = zze;
        this.zzc = Integer.MAX_VALUE;
        this.zzf = false;
    }

    public static long zza(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    public static zzaic zza(byte[] bArr, int i3, int i4, boolean z2) {
        zzaif zzaif = new zzaif(bArr, i3, i4, z2);
        try {
            zzaif.zzb(i4);
            return zzaif;
        } catch (zzajk e3) {
            throw new IllegalArgumentException(e3);
        }
    }
}
