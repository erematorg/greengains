package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zzkk implements zzna {
    private final zzkg zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzkk(zzkg zzkg) {
        zzkg zzkg2 = (zzkg) zzle.zza(zzkg, "input");
        this.zza = zzkg2;
        zzkg2.zzc = this;
    }

    public final double zza() throws IOException {
        zzb(1);
        return this.zza.zza();
    }

    public final float zzb() throws IOException {
        zzb(5);
        return this.zza.zzb();
    }

    public final int zzc() throws IOException {
        int i3 = this.zzd;
        if (i3 != 0) {
            this.zzb = i3;
            this.zzd = 0;
        } else {
            this.zzb = this.zza.zzi();
        }
        int i4 = this.zzb;
        if (i4 == 0 || i4 == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i4 >>> 3;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() throws IOException {
        zzb(0);
        return this.zza.zzd();
    }

    public final int zzf() throws IOException {
        zzb(5);
        return this.zza.zze();
    }

    public final int zzg() throws IOException {
        zzb(0);
        return this.zza.zzf();
    }

    public final int zzh() throws IOException {
        zzb(5);
        return this.zza.zzg();
    }

    public final int zzi() throws IOException {
        zzb(0);
        return this.zza.zzh();
    }

    public final int zzj() throws IOException {
        zzb(0);
        return this.zza.zzj();
    }

    public final long zzk() throws IOException {
        zzb(1);
        return this.zza.zzk();
    }

    public final long zzl() throws IOException {
        zzb(0);
        return this.zza.zzl();
    }

    public final long zzm() throws IOException {
        zzb(1);
        return this.zza.zzn();
    }

    public final long zzn() throws IOException {
        zzb(0);
        return this.zza.zzo();
    }

    public final long zzo() throws IOException {
        zzb(0);
        return this.zza.zzp();
    }

    public final zzjs zzp() throws IOException {
        zzb(2);
        return this.zza.zzq();
    }

    public final String zzq() throws IOException {
        zzb(2);
        return this.zza.zzr();
    }

    public final String zzr() throws IOException {
        zzb(2);
        return this.zza.zzs();
    }

    public final boolean zzs() throws IOException {
        zzb(0);
        return this.zza.zzu();
    }

    public final boolean zzt() throws IOException {
        int i3;
        if (this.zza.zzt() || (i3 = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzd(i3);
    }

    private final <T> void zzd(T t2, zznd<T> zznd, zzkp zzkp) throws IOException {
        int zzj = this.zza.zzj();
        zzkg zzkg = this.zza;
        if (zzkg.zza < zzkg.zzb) {
            int zza2 = zzkg.zza(zzj);
            this.zza.zza++;
            zznd.zza(t2, this, zzkp);
            this.zza.zzb(0);
            zzkg zzkg2 = this.zza;
            zzkg2.zza--;
            zzkg2.zzc(zza2);
            return;
        }
        throw zzlk.zzh();
    }

    public static zzkk zza(zzkg zzkg) {
        zzkk zzkk = zzkg.zzc;
        if (zzkk != null) {
            return zzkk;
        }
        return new zzkk(zzkg);
    }

    private final <T> T zzb(zznd<T> zznd, zzkp zzkp) throws IOException {
        T zza2 = zznd.zza();
        zzd(zza2, zznd, zzkp);
        zznd.zzd(zza2);
        return zza2;
    }

    public final void zze(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzc(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzlf.zzd(this.zza.zze());
                } while (this.zza.zzc() < zzc2);
            } else if (i3 == 5) {
                do {
                    zzlf.zzd(this.zza.zze());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzc(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                } while (this.zza.zzc() < zzc3);
            } else if (i4 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzf(List<Long> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    zzlw.zza(this.zza.zzk());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzd(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzlw.zza(this.zza.zzk());
                } while (this.zza.zzc() < zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzk()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzd(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzk()));
                } while (this.zza.zzc() < zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzg(List<Float> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlb) {
            zzlb zzlb = (zzlb) list;
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzc(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzlb.zza(this.zza.zzb());
                } while (this.zza.zzc() < zzc2);
            } else if (i3 == 5) {
                do {
                    zzlb.zza(this.zza.zzb());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzc(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                } while (this.zza.zzc() < zzc3);
            } else if (i4 == 5) {
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzh(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlf.zzd(this.zza.zzf());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlf.zzd(this.zza.zzf());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzi(List<Long> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlw.zza(this.zza.zzl());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlw.zza(this.zza.zzl());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzl()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzl()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzj(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzc(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzlf.zzd(this.zza.zzg());
                } while (this.zza.zzc() < zzc2);
            } else if (i3 == 5) {
                do {
                    zzlf.zzd(this.zza.zzg());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzc(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzc() < zzc3);
            } else if (i4 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzk(List<Long> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    zzlw.zza(this.zza.zzn());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzd(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzlw.zza(this.zza.zzn());
                } while (this.zza.zzc() < zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzd(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                } while (this.zza.zzc() < zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzl(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlf.zzd(this.zza.zzh());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlf.zzd(this.zza.zzh());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzm(List<Long> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlw.zza(this.zza.zzo());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlw.zza(this.zza.zzo());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzn(List<String> list) throws IOException {
        zza(list, false);
    }

    public final void zzo(List<String> list) throws IOException {
        zza(list, true);
    }

    public final void zzp(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlf.zzd(this.zza.zzj());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlf.zzd(this.zza.zzj());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final void zzq(List<Long> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlw) {
            zzlw zzlw = (zzlw) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlw.zza(this.zza.zzp());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlw.zza(this.zza.zzp());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    private final Object zza(zzof zzof, Class<?> cls, zzkp zzkp) throws IOException {
        switch (zzkj.zza[zzof.ordinal()]) {
            case 1:
                return Boolean.valueOf(zzs());
            case 2:
                return zzp();
            case 3:
                return Double.valueOf(zza());
            case 4:
                return Integer.valueOf(zze());
            case 5:
                return Integer.valueOf(zzf());
            case 6:
                return Long.valueOf(zzk());
            case 7:
                return Float.valueOf(zzb());
            case 8:
                return Integer.valueOf(zzg());
            case 9:
                return Long.valueOf(zzl());
            case 10:
                zzb(2);
                return zzb(zzmz.zza().zza(cls), zzkp);
            case 11:
                return Integer.valueOf(zzh());
            case 12:
                return Long.valueOf(zzm());
            case 13:
                return Integer.valueOf(zzi());
            case 14:
                return Long.valueOf(zzn());
            case 15:
                return zzr();
            case 16:
                return Integer.valueOf(zzj());
            case 17:
                return Long.valueOf(zzo());
            default:
                throw new IllegalArgumentException("unsupported field type.");
        }
    }

    private final <T> void zzc(T t2, zznd<T> zznd, zzkp zzkp) throws IOException {
        int i3 = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zznd.zza(t2, this, zzkp);
            if (this.zzb != this.zzc) {
                throw zzlk.zzg();
            }
        } finally {
            this.zzc = i3;
        }
    }

    public final <T> void zzb(T t2, zznd<T> zznd, zzkp zzkp) throws IOException {
        zzb(2);
        zzd(t2, zznd, zzkp);
    }

    public final void zzb(List<zzjs> list) throws IOException {
        int zzi;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                if (!this.zza.zzt()) {
                    zzi = this.zza.zzi();
                } else {
                    return;
                }
            } while (zzi == this.zzb);
            this.zzd = zzi;
            return;
        }
        throw zzlk.zza();
    }

    public final void zzd(List<Integer> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzlf) {
            zzlf zzlf = (zzlf) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzlf.zzd(this.zza.zzd());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzlf.zzd(this.zza.zzd());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzd()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Integer.valueOf(this.zza.zzd()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    public final <T> void zzb(List<T> list, zznd<T> zznd, zzkp zzkp) throws IOException {
        int zzi;
        int i3 = this.zzb;
        if ((i3 & 7) == 2) {
            do {
                list.add(zzb(zznd, zzkp));
                if (!this.zza.zzt() && this.zzd == 0) {
                    zzi = this.zza.zzi();
                } else {
                    return;
                }
            } while (zzi == i3);
            this.zzd = zzi;
            return;
        }
        throw zzlk.zza();
    }

    public final void zzc(List<Double> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzkn) {
            zzkn zzkn = (zzkn) list;
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    zzkn.zza(this.zza.zza());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzj = this.zza.zzj();
                zzd(zzj);
                int zzc2 = this.zza.zzc() + zzj;
                do {
                    zzkn.zza(this.zza.zza());
                } while (this.zza.zzc() < zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzj2 = this.zza.zzj();
                zzd(zzj2);
                int zzc3 = this.zza.zzc() + zzj2;
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                } while (this.zza.zzc() < zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    private final void zzb(int i3) throws IOException {
        if ((this.zzb & 7) != i3) {
            throw zzlk.zza();
        }
    }

    private final <T> T zza(zznd<T> zznd, zzkp zzkp) throws IOException {
        T zza2 = zznd.zza();
        zzc(zza2, zznd, zzkp);
        zznd.zzd(zza2);
        return zza2;
    }

    public final <T> void zza(T t2, zznd<T> zznd, zzkp zzkp) throws IOException {
        zzb(3);
        zzc(t2, zznd, zzkp);
    }

    public final void zza(List<Boolean> list) throws IOException {
        int zzi;
        int zzi2;
        if (list instanceof zzjq) {
            zzjq zzjq = (zzjq) list;
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    zzjq.zza(this.zza.zzu());
                    if (!this.zza.zzt()) {
                        zzi2 = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi2 == this.zzb);
                this.zzd = zzi2;
            } else if (i3 == 2) {
                int zzc2 = this.zza.zzc() + this.zza.zzj();
                do {
                    zzjq.zza(this.zza.zzu());
                } while (this.zza.zzc() < zzc2);
                zza(zzc2);
            } else {
                throw zzlk.zza();
            }
        } else {
            int i4 = this.zzb & 7;
            if (i4 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzu()));
                    if (!this.zza.zzt()) {
                        zzi = this.zza.zzi();
                    } else {
                        return;
                    }
                } while (zzi == this.zzb);
                this.zzd = zzi;
            } else if (i4 == 2) {
                int zzc3 = this.zza.zzc() + this.zza.zzj();
                do {
                    list.add(Boolean.valueOf(this.zza.zzu()));
                } while (this.zza.zzc() < zzc3);
                zza(zzc3);
            } else {
                throw zzlk.zza();
            }
        }
    }

    private static void zzd(int i3) throws IOException {
        if ((i3 & 7) != 0) {
            throw zzlk.zzg();
        }
    }

    private static void zzc(int i3) throws IOException {
        if ((i3 & 3) != 0) {
            throw zzlk.zzg();
        }
    }

    @Deprecated
    public final <T> void zza(List<T> list, zznd<T> zznd, zzkp zzkp) throws IOException {
        int zzi;
        int i3 = this.zzb;
        if ((i3 & 7) == 3) {
            do {
                list.add(zza(zznd, zzkp));
                if (!this.zza.zzt() && this.zzd == 0) {
                    zzi = this.zza.zzi();
                } else {
                    return;
                }
            } while (zzi == i3);
            this.zzd = zzi;
            return;
        }
        throw zzlk.zza();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        if (zzt() != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
        throw new com.google.android.gms.internal.measurement.zzlk(r6);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0050 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <K, V> void zza(java.util.Map<K, V> r8, com.google.android.gms.internal.measurement.zzmc<K, V> r9, com.google.android.gms.internal.measurement.zzkp r10) throws java.io.IOException {
        /*
            r7 = this;
            r0 = 2
            r7.zzb((int) r0)
            com.google.android.gms.internal.measurement.zzkg r1 = r7.zza
            int r1 = r1.zzj()
            com.google.android.gms.internal.measurement.zzkg r2 = r7.zza
            int r1 = r2.zza((int) r1)
            K r2 = r9.zzb
            V r3 = r9.zzd
        L_0x0014:
            int r4 = r7.zzc()     // Catch:{ all -> 0x0039 }
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r4 == r5) goto L_0x005d
            com.google.android.gms.internal.measurement.zzkg r5 = r7.zza     // Catch:{ all -> 0x0039 }
            boolean r5 = r5.zzt()     // Catch:{ all -> 0x0039 }
            if (r5 != 0) goto L_0x005d
            r5 = 1
            java.lang.String r6 = "Unable to parse map entry."
            if (r4 == r5) goto L_0x0048
            if (r4 == r0) goto L_0x003b
            boolean r4 = r7.zzt()     // Catch:{ zzln -> 0x0050 }
            if (r4 == 0) goto L_0x0033
            goto L_0x0014
        L_0x0033:
            com.google.android.gms.internal.measurement.zzlk r4 = new com.google.android.gms.internal.measurement.zzlk     // Catch:{ zzln -> 0x0050 }
            r4.<init>(r6)     // Catch:{ zzln -> 0x0050 }
            throw r4     // Catch:{ zzln -> 0x0050 }
        L_0x0039:
            r8 = move-exception
            goto L_0x0066
        L_0x003b:
            com.google.android.gms.internal.measurement.zzof r4 = r9.zzc     // Catch:{ zzln -> 0x0050 }
            V r5 = r9.zzd     // Catch:{ zzln -> 0x0050 }
            java.lang.Class r5 = r5.getClass()     // Catch:{ zzln -> 0x0050 }
            java.lang.Object r3 = r7.zza((com.google.android.gms.internal.measurement.zzof) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.measurement.zzkp) r10)     // Catch:{ zzln -> 0x0050 }
            goto L_0x0014
        L_0x0048:
            com.google.android.gms.internal.measurement.zzof r4 = r9.zza     // Catch:{ zzln -> 0x0050 }
            r5 = 0
            java.lang.Object r2 = r7.zza((com.google.android.gms.internal.measurement.zzof) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.measurement.zzkp) r5)     // Catch:{ zzln -> 0x0050 }
            goto L_0x0014
        L_0x0050:
            boolean r4 = r7.zzt()     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x0057
            goto L_0x0014
        L_0x0057:
            com.google.android.gms.internal.measurement.zzlk r8 = new com.google.android.gms.internal.measurement.zzlk     // Catch:{ all -> 0x0039 }
            r8.<init>(r6)     // Catch:{ all -> 0x0039 }
            throw r8     // Catch:{ all -> 0x0039 }
        L_0x005d:
            r8.put(r2, r3)     // Catch:{ all -> 0x0039 }
            com.google.android.gms.internal.measurement.zzkg r7 = r7.zza
            r7.zzc(r1)
            return
        L_0x0066:
            com.google.android.gms.internal.measurement.zzkg r7 = r7.zza
            r7.zzc(r1)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkk.zza(java.util.Map, com.google.android.gms.internal.measurement.zzmc, com.google.android.gms.internal.measurement.zzkp):void");
    }

    private final void zza(List<String> list, boolean z2) throws IOException {
        int zzi;
        int zzi2;
        if ((this.zzb & 7) != 2) {
            throw zzlk.zza();
        } else if (!(list instanceof zzls) || z2) {
            do {
                list.add(z2 ? zzr() : zzq());
                if (!this.zza.zzt()) {
                    zzi = this.zza.zzi();
                } else {
                    return;
                }
            } while (zzi == this.zzb);
            this.zzd = zzi;
        } else {
            zzls zzls = (zzls) list;
            do {
                zzls.zza(zzp());
                if (!this.zza.zzt()) {
                    zzi2 = this.zza.zzi();
                } else {
                    return;
                }
            } while (zzi2 == this.zzb);
            this.zzd = zzi2;
        }
    }

    private final void zza(int i3) throws IOException {
        if (this.zza.zzc() != i3) {
            throw zzlk.zzi();
        }
    }
}
