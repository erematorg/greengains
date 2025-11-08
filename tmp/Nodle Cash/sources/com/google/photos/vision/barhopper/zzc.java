package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzco;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfn;
import java.util.List;

public final class zzc extends zzeh implements zzfn {
    /* access modifiers changed from: private */
    public static final zzc zzb;
    private byte zzA = 2;
    private int zzd;
    private int zze;
    private zzdf zzf;
    private String zzg;
    private zzad zzh;
    private int zzi;
    private zzp zzj;
    private zzv zzk;
    private zzco zzl;
    private zzah zzm;
    private zzao zzn;
    private zzak zzo;
    private zzz zzp;
    private zzn zzq;
    private zzr zzr;
    private zzj zzs;
    private zzeo zzt;
    private zzen zzu;
    private String zzv;
    private zzeo zzw;
    private boolean zzx;
    private double zzy;
    private zzdf zzz;

    static {
        zzc zzc = new zzc();
        zzb = zzc;
        zzeh.zzV(zzc.class, zzc);
    }

    private zzc() {
        zzdf zzdf = zzdf.zzb;
        this.zzf = zzdf;
        this.zzg = "";
        this.zzt = zzeh.zzP();
        this.zzu = zzeh.zzO();
        this.zzv = "";
        this.zzw = zzeh.zzP();
        this.zzx = true;
        this.zzz = zzdf;
    }

    public static /* synthetic */ void zzp(zzc zzc, int i3, zzaf zzaf) {
        zzaf.getClass();
        zzeo zzeo = zzc.zzt;
        if (!zzeo.zzc()) {
            zzc.zzt = zzeh.zzQ(zzeo);
        }
        zzc.zzt.set(i3, zzaf);
    }

    public final int zzA() {
        int zza = zzg.zza(this.zzi);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }

    public final int zza() {
        return this.zzt.size();
    }

    public final zzco zzb() {
        zzco zzco = this.zzl;
        return zzco == null ? zzco.zzb() : zzco;
    }

    public final zzn zzd() {
        zzn zzn2 = this.zzq;
        return zzn2 == null ? zzn.zzd() : zzn2;
    }

    public final zzp zze() {
        zzp zzp2 = this.zzj;
        return zzp2 == null ? zzp.zzc() : zzp2;
    }

    public final zzr zzf() {
        zzr zzr2 = this.zzr;
        return zzr2 == null ? zzr.zzb() : zzr2;
    }

    public final Object zzg(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return Byte.valueOf(this.zzA);
        }
        if (i4 == 2) {
            return zzeh.zzS(zzb, "\u0004\u0016\u0000\u0001\u0001\u0017\u0016\u0000\u0003\u000b\u0001ᴌ\u0000\u0002ᔊ\u0001\u0003ᔈ\u0002\u0004ᴌ\u0004\u0005ᐉ\u0005\u0006ဉ\u0006\u0007ဉ\u0007\bᐉ\b\tᐉ\t\nᐉ\n\u000bЛ\fဈ\u000f\rЛ\u000eည\u0012\u000fᐉ\u000b\u0010ဉ\f\u0011ဉ\r\u0012\u0016\u0013ဉ\u000e\u0014ဇ\u0010\u0015က\u0011\u0017ဉ\u0003", new Object[]{"zzd", "zze", zzd.zza, "zzf", "zzg", "zzi", zzf.zza, "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzt", zzaf.class, "zzv", "zzw", zzaf.class, "zzz", "zzp", "zzq", "zzr", "zzu", "zzs", "zzx", "zzy", "zzh"});
        } else if (i4 == 3) {
            return new zzc();
        } else {
            if (i4 == 4) {
                return new zzb((zza) null);
            }
            if (i4 == 5) {
                return zzb;
            }
            this.zzA = obj == null ? (byte) 0 : 1;
            return null;
        }
    }

    public final zzv zzh() {
        zzv zzv2 = this.zzk;
        return zzv2 == null ? zzv.zzb() : zzv2;
    }

    public final zzz zzi() {
        zzz zzz2 = this.zzp;
        return zzz2 == null ? zzz.zzd() : zzz2;
    }

    public final zzah zzj() {
        zzah zzah = this.zzm;
        return zzah == null ? zzah.zzb() : zzah;
    }

    public final zzak zzk() {
        zzak zzak = this.zzo;
        return zzak == null ? zzak.zzb() : zzak;
    }

    public final zzao zzl() {
        zzao zzao = this.zzn;
        return zzao == null ? zzao.zzb() : zzao;
    }

    public final zzdf zzm() {
        return this.zzf;
    }

    public final String zzn() {
        return this.zzg;
    }

    public final List zzo() {
        return this.zzt;
    }

    public final boolean zzq() {
        return (this.zzd & 4096) != 0;
    }

    public final boolean zzr() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzs() {
        return (this.zzd & 8192) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 64) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 2048) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 128) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 256) != 0;
    }

    public final boolean zzx() {
        return (this.zzd & 1024) != 0;
    }

    public final boolean zzy() {
        return (this.zzd & 512) != 0;
    }

    public final int zzz() {
        int zza = zze.zza(this.zze);
        if (zza == 0) {
            return 1;
        }
        return zza;
    }
}
