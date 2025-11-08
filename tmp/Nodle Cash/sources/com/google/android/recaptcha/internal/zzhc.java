package com.google.android.recaptcha.internal;

import java.io.IOException;

public abstract class zzhc {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzhd zzc;

    public /* synthetic */ zzhc(zzhb zzhb) {
    }

    public static int zzF(int i3) {
        return (i3 >>> 1) ^ (-(i3 & 1));
    }

    public static long zzG(long j2) {
        return (j2 >>> 1) ^ (-(1 & j2));
    }

    public static zzhc zzH(byte[] bArr, int i3, int i4, boolean z2) {
        zzgy zzgy = new zzgy(bArr, 0, 0, false, (zzgx) null);
        try {
            zzgy.zze(0);
            return zzgy;
        } catch (zzje e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    public abstract void zzA(int i3);

    public abstract boolean zzC() throws IOException;

    public abstract boolean zzD() throws IOException;

    public abstract boolean zzE(int i3) throws IOException;

    public abstract double zzb() throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzd();

    public abstract int zze(int i3) throws zzje;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract int zzk() throws IOException;

    public abstract int zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract long zzt() throws IOException;

    public abstract long zzu() throws IOException;

    public abstract long zzv() throws IOException;

    public abstract zzgw zzw() throws IOException;

    public abstract String zzx() throws IOException;

    public abstract String zzy() throws IOException;

    public abstract void zzz(int i3) throws zzje;
}
