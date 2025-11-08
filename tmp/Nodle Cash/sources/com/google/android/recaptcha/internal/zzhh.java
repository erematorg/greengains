package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzhh extends zzgm {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzhh.class.getName());
    /* access modifiers changed from: private */
    public static final boolean zzd = zzlv.zzx();
    zzhi zza;

    private zzhh() {
    }

    public static zzhh zzA(byte[] bArr, int i3, int i4) {
        return new zzhe(bArr, 0, i4);
    }

    @Deprecated
    public static int zzt(int i3, zzke zzke, zzkr zzkr) {
        int zza2 = ((zzgf) zzke).zza(zzkr);
        int zzy = zzy(i3 << 3);
        return zzy + zzy + zza2;
    }

    public static int zzu(int i3) {
        if (i3 >= 0) {
            return zzy(i3);
        }
        return 10;
    }

    public static int zzv(zzke zzke) {
        int zzn = zzke.zzn();
        return zzy(zzn) + zzn;
    }

    public static int zzw(zzke zzke, zzkr zzkr) {
        int zza2 = ((zzgf) zzke).zza(zzkr);
        return zzy(zza2) + zza2;
    }

    public static int zzx(String str) {
        int i3;
        try {
            i3 = zzma.zzc(str);
        } catch (zzlz unused) {
            i3 = str.getBytes(zzjc.zzb).length;
        }
        return zzy(i3) + i3;
    }

    public static int zzy(int i3) {
        if ((i3 & -128) == 0) {
            return 1;
        }
        if ((i3 & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i3) == 0) {
            return 3;
        }
        return (i3 & -268435456) == 0 ? 4 : 5;
    }

    public static int zzz(long j2) {
        int i3;
        if ((-128 & j2) == 0) {
            return 1;
        }
        if (j2 < 0) {
            return 10;
        }
        if ((-34359738368L & j2) != 0) {
            j2 >>>= 28;
            i3 = 6;
        } else {
            i3 = 2;
        }
        if ((-2097152 & j2) != 0) {
            j2 >>>= 14;
            i3 += 2;
        }
        return (j2 & -16384) != 0 ? i3 + 1 : i3;
    }

    public final void zzB() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public final void zzC(String str, zzlz zzlz) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", zzlz);
        byte[] bytes = str.getBytes(zzjc.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzhf(e3);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b3) throws IOException;

    public abstract void zzd(int i3, boolean z2) throws IOException;

    public abstract void zze(int i3, zzgw zzgw) throws IOException;

    public abstract void zzf(int i3, int i4) throws IOException;

    public abstract void zzg(int i3) throws IOException;

    public abstract void zzh(int i3, long j2) throws IOException;

    public abstract void zzi(long j2) throws IOException;

    public abstract void zzj(int i3, int i4) throws IOException;

    public abstract void zzk(int i3) throws IOException;

    public abstract void zzl(byte[] bArr, int i3, int i4) throws IOException;

    public abstract void zzm(int i3, String str) throws IOException;

    public abstract void zzo(int i3, int i4) throws IOException;

    public abstract void zzp(int i3, int i4) throws IOException;

    public abstract void zzq(int i3) throws IOException;

    public abstract void zzr(int i3, long j2) throws IOException;

    public abstract void zzs(long j2) throws IOException;

    public /* synthetic */ zzhh(zzhg zzhg) {
    }
}
