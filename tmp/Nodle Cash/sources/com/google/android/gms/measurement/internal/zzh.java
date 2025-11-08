package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.SieveCacheKt;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class zzh {
    private final zzhw zza;
    @Nullable
    private Long zzaa;
    @Nullable
    private Long zzab;
    private long zzac;
    @Nullable
    private String zzad;
    private int zzae;
    private int zzaf;
    private long zzag;
    private String zzah;
    @Nullable
    private byte[] zzai;
    private long zzaj;
    private long zzak;
    private long zzal;
    private long zzam;
    private long zzan;
    private long zzao;
    @Nullable
    private String zzap;
    private boolean zzaq;
    private long zzar;
    private long zzas;
    private final String zzb;
    @Nullable
    private String zzc;
    @Nullable
    private String zzd;
    @Nullable
    private String zze;
    @Nullable
    private String zzf;
    private long zzg;
    private long zzh;
    private long zzi;
    @Nullable
    private String zzj;
    private long zzk;
    @Nullable
    private String zzl;
    private long zzm;
    private long zzn;
    private boolean zzo;
    private boolean zzp;
    @Nullable
    private String zzq;
    @Nullable
    private Boolean zzr;
    private long zzs;
    @Nullable
    private List<String> zzt;
    @Nullable
    private String zzu;
    private boolean zzv;
    private long zzw;
    private long zzx;
    private int zzy;
    private boolean zzz;

    @WorkerThread
    public zzh(zzhw zzhw, String str) {
        Preconditions.checkNotNull(zzhw);
        Preconditions.checkNotEmpty(str);
        this.zza = zzhw;
        this.zzb = str;
        zzhw.zzl().zzt();
    }

    @WorkerThread
    public final int zza() {
        this.zza.zzl().zzt();
        return this.zzy;
    }

    @WorkerThread
    @Nullable
    public final String zzaa() {
        this.zza.zzl().zzt();
        return this.zzq;
    }

    @WorkerThread
    @Nullable
    public final String zzab() {
        this.zza.zzl().zzt();
        String str = this.zzap;
        zzg((String) null);
        return str;
    }

    @WorkerThread
    public final String zzac() {
        this.zza.zzl().zzt();
        return this.zzb;
    }

    @WorkerThread
    @Nullable
    public final String zzad() {
        this.zza.zzl().zzt();
        return this.zzc;
    }

    @WorkerThread
    @Nullable
    public final String zzae() {
        this.zza.zzl().zzt();
        return this.zzl;
    }

    @WorkerThread
    @Nullable
    public final String zzaf() {
        this.zza.zzl().zzt();
        return this.zzj;
    }

    @WorkerThread
    @Nullable
    public final String zzag() {
        this.zza.zzl().zzt();
        return this.zzf;
    }

    @WorkerThread
    @Nullable
    public final String zzah() {
        this.zza.zzl().zzt();
        return this.zzd;
    }

    @WorkerThread
    @Nullable
    public final String zzai() {
        this.zza.zzl().zzt();
        return this.zzap;
    }

    @WorkerThread
    @Nullable
    public final String zzaj() {
        this.zza.zzl().zzt();
        return this.zze;
    }

    @WorkerThread
    public final String zzak() {
        this.zza.zzl().zzt();
        return this.zzah;
    }

    @WorkerThread
    @Nullable
    public final String zzal() {
        this.zza.zzl().zzt();
        return this.zzu;
    }

    @WorkerThread
    @Nullable
    public final String zzam() {
        this.zza.zzl().zzt();
        return this.zzad;
    }

    @WorkerThread
    @Nullable
    public final List<String> zzan() {
        this.zza.zzl().zzt();
        return this.zzt;
    }

    @WorkerThread
    public final void zzao() {
        this.zza.zzl().zzt();
        this.zzaq = false;
    }

    @WorkerThread
    public final void zzap() {
        this.zza.zzl().zzt();
        long j2 = this.zzg + 1;
        if (j2 > SieveCacheKt.NodeLinkMask) {
            this.zza.zzj().zzu().zza("Bundle index overflow. appId", zzgi.zza(this.zzb));
            j2 = 0;
        }
        this.zzaq = true;
        this.zzg = j2;
    }

    @WorkerThread
    public final boolean zzaq() {
        this.zza.zzl().zzt();
        return this.zzp;
    }

    @WorkerThread
    public final boolean zzar() {
        this.zza.zzl().zzt();
        return this.zzo;
    }

    @WorkerThread
    public final boolean zzas() {
        this.zza.zzl().zzt();
        return this.zzaq;
    }

    @WorkerThread
    public final boolean zzat() {
        this.zza.zzl().zzt();
        return this.zzv;
    }

    @WorkerThread
    public final boolean zzau() {
        this.zza.zzl().zzt();
        return this.zzz;
    }

    @WorkerThread
    @Nullable
    public final byte[] zzav() {
        this.zza.zzl().zzt();
        return this.zzai;
    }

    @WorkerThread
    public final int zzb() {
        this.zza.zzl().zzt();
        return this.zzaf;
    }

    @WorkerThread
    public final int zzc() {
        this.zza.zzl().zzt();
        return this.zzae;
    }

    @WorkerThread
    public final long zzd() {
        this.zza.zzl().zzt();
        return 0;
    }

    @WorkerThread
    public final long zze() {
        this.zza.zzl().zzt();
        return this.zzk;
    }

    @WorkerThread
    public final long zzf() {
        this.zza.zzl().zzt();
        return this.zzac;
    }

    @WorkerThread
    public final long zzg() {
        this.zza.zzl().zzt();
        return this.zzar;
    }

    @WorkerThread
    public final long zzh() {
        this.zza.zzl().zzt();
        return this.zzam;
    }

    @WorkerThread
    public final long zzi() {
        this.zza.zzl().zzt();
        return this.zzan;
    }

    @WorkerThread
    public final long zzj() {
        this.zza.zzl().zzt();
        return this.zzal;
    }

    @WorkerThread
    public final long zzk() {
        this.zza.zzl().zzt();
        return this.zzak;
    }

    @WorkerThread
    public final long zzl() {
        this.zza.zzl().zzt();
        return this.zzao;
    }

    @WorkerThread
    public final long zzm() {
        this.zza.zzl().zzt();
        return this.zzaj;
    }

    @WorkerThread
    public final long zzn() {
        this.zza.zzl().zzt();
        return this.zzn;
    }

    @WorkerThread
    public final long zzo() {
        this.zza.zzl().zzt();
        return this.zzs;
    }

    @WorkerThread
    public final long zzp() {
        this.zza.zzl().zzt();
        return this.zzas;
    }

    @WorkerThread
    public final long zzq() {
        this.zza.zzl().zzt();
        return this.zzm;
    }

    @WorkerThread
    public final long zzr() {
        this.zza.zzl().zzt();
        return this.zzag;
    }

    @WorkerThread
    public final long zzs() {
        this.zza.zzl().zzt();
        return this.zzi;
    }

    @WorkerThread
    public final long zzt() {
        this.zza.zzl().zzt();
        return this.zzg;
    }

    @WorkerThread
    public final long zzu() {
        this.zza.zzl().zzt();
        return this.zzh;
    }

    @WorkerThread
    public final long zzv() {
        this.zza.zzl().zzt();
        return this.zzx;
    }

    @WorkerThread
    public final long zzw() {
        this.zza.zzl().zzt();
        return this.zzw;
    }

    @WorkerThread
    @Nullable
    public final Boolean zzx() {
        this.zza.zzl().zzt();
        return this.zzr;
    }

    @WorkerThread
    @Nullable
    public final Long zzy() {
        this.zza.zzl().zzt();
        return this.zzaa;
    }

    @WorkerThread
    @Nullable
    public final Long zzz() {
        this.zza.zzl().zzt();
        return this.zzab;
    }

    @WorkerThread
    public final void zzd(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzj, str);
        this.zzj = str;
    }

    @WorkerThread
    public final void zza(long j2) {
        this.zza.zzl().zzt();
        long j3 = this.zzg + j2;
        if (j3 > SieveCacheKt.NodeLinkMask) {
            this.zza.zzj().zzu().zza("Bundle index overflow. appId", zzgi.zza(this.zzb));
            j3 = j2 - 1;
        }
        long j4 = this.zzag + 1;
        if (j4 > SieveCacheKt.NodeLinkMask) {
            this.zza.zzj().zzu().zza("Delivery index overflow. appId", zzgi.zza(this.zzb));
            j4 = 0;
        }
        this.zzaq = true;
        this.zzg = j3;
        this.zzag = j4;
    }

    @WorkerThread
    public final void zzb(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzc, str);
        this.zzc = str;
    }

    @WorkerThread
    public final void zzc(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzl, str);
        this.zzl = str;
    }

    @WorkerThread
    public final void zze(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzam != j2;
        this.zzam = j2;
    }

    @WorkerThread
    public final void zzf(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzan != j2;
        this.zzan = j2;
    }

    @WorkerThread
    public final void zzg(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzal != j2;
        this.zzal = j2;
    }

    @WorkerThread
    public final void zzh(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzak != j2;
        this.zzak = j2;
    }

    @WorkerThread
    public final void zzi(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzao != j2;
        this.zzao = j2;
    }

    @WorkerThread
    public final void zzj(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzaj != j2;
        this.zzaj = j2;
    }

    @WorkerThread
    public final void zzk(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzn != j2;
        this.zzn = j2;
    }

    @WorkerThread
    public final void zzl(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzs != j2;
        this.zzs = j2;
    }

    @WorkerThread
    public final void zzm(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzas != j2;
        this.zzas = j2;
    }

    @WorkerThread
    public final void zzn(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzm != j2;
        this.zzm = j2;
    }

    @WorkerThread
    public final void zzo(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzag != j2;
        this.zzag = j2;
    }

    @WorkerThread
    public final void zzp(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzi != j2;
        this.zzi = j2;
    }

    @WorkerThread
    public final void zzq(long j2) {
        boolean z2 = false;
        Preconditions.checkArgument(j2 >= 0);
        this.zza.zzl().zzt();
        boolean z3 = this.zzaq;
        if (this.zzg != j2) {
            z2 = true;
        }
        this.zzaq = z3 | z2;
        this.zzg = j2;
    }

    @WorkerThread
    public final void zzr(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzh != j2;
        this.zzh = j2;
    }

    @WorkerThread
    public final void zzs(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzx != j2;
        this.zzx = j2;
    }

    @WorkerThread
    public final void zzt(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzw != j2;
        this.zzw = j2;
    }

    @WorkerThread
    public final void zzd(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzar != j2;
        this.zzar = j2;
    }

    @WorkerThread
    public final void zzb(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzk != j2;
        this.zzk = j2;
    }

    @WorkerThread
    public final void zzc(long j2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzac != j2;
        this.zzac = j2;
    }

    @WorkerThread
    public final void zze(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzf, str);
        this.zzf = str;
    }

    @WorkerThread
    public final void zzf(@Nullable String str) {
        this.zza.zzl().zzt();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzaq |= !Objects.equals(this.zzd, str);
        this.zzd = str;
    }

    @WorkerThread
    public final void zzg(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzap, str);
        this.zzap = str;
    }

    @WorkerThread
    public final void zzh(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zze, str);
        this.zze = str;
    }

    @WorkerThread
    public final void zzi(String str) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzah != str;
        this.zzah = str;
    }

    @WorkerThread
    public final void zzj(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzu, str);
        this.zzu = str;
    }

    @WorkerThread
    public final void zzk(@Nullable String str) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzad != str;
        this.zzad = str;
    }

    @WorkerThread
    public final void zzd(boolean z2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzz != z2;
        this.zzz = z2;
    }

    @WorkerThread
    public final void zzb(int i3) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzaf != i3;
        this.zzaf = i3;
    }

    @WorkerThread
    public final void zzc(int i3) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzae != i3;
        this.zzae = i3;
    }

    @WorkerThread
    public final void zza(@Nullable byte[] bArr) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzai != bArr;
        this.zzai = bArr;
    }

    @WorkerThread
    public final void zzb(boolean z2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzo != z2;
        this.zzo = z2;
    }

    @WorkerThread
    public final void zzc(boolean z2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzv != z2;
        this.zzv = z2;
    }

    @WorkerThread
    public final void zza(@Nullable String str) {
        this.zza.zzl().zzt();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzaq |= !Objects.equals(this.zzq, str);
        this.zzq = str;
    }

    @WorkerThread
    public final void zzb(@Nullable Long l2) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzab, l2);
        this.zzab = l2;
    }

    @WorkerThread
    public final void zza(int i3) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzy != i3;
        this.zzy = i3;
    }

    @WorkerThread
    public final void zza(boolean z2) {
        this.zza.zzl().zzt();
        this.zzaq |= this.zzp != z2;
        this.zzp = z2;
    }

    @WorkerThread
    public final void zza(@Nullable Boolean bool) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzr, bool);
        this.zzr = bool;
    }

    @WorkerThread
    public final void zza(@Nullable List<String> list) {
        this.zza.zzl().zzt();
        if (!Objects.equals(this.zzt, list)) {
            this.zzaq = true;
            this.zzt = list != null ? new ArrayList(list) : null;
        }
    }

    @WorkerThread
    public final void zza(@Nullable Long l2) {
        this.zza.zzl().zzt();
        this.zzaq |= !Objects.equals(this.zzaa, l2);
        this.zzaa = l2;
    }
}
