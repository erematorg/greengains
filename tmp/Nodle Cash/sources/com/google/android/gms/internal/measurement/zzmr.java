package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

final class zzmr<T> implements zznd<T> {
    private final zzml zza;
    private final zznx<?, ?> zzb;
    private final boolean zzc;
    private final zzkr<?> zzd;

    private zzmr(zznx<?, ?> zznx, zzkr<?> zzkr, zzml zzml) {
        this.zzb = zznx;
        this.zzc = zzkr.zza(zzml);
        this.zzd = zzkr;
        this.zza = zzml;
    }

    public final int zza(T t2) {
        zznx<?, ?> zznx = this.zzb;
        int zzb2 = zznx.zzb(zznx.zzd(t2));
        return this.zzc ? zzb2 + this.zzd.zza((Object) t2).zza() : zzb2;
    }

    public final int zzb(T t2) {
        int hashCode = this.zzb.zzd(t2).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza((Object) t2).hashCode() : hashCode;
    }

    public final void zzd(T t2) {
        this.zzb.zzf(t2);
        this.zzd.zzc(t2);
    }

    public final boolean zze(T t2) {
        return this.zzd.zza((Object) t2).zzg();
    }

    public final boolean zzb(T t2, T t3) {
        if (!this.zzb.zzd(t2).equals(this.zzb.zzd(t3))) {
            return false;
        }
        if (this.zzc) {
            return this.zzd.zza((Object) t2).equals(this.zzd.zza((Object) t3));
        }
        return true;
    }

    public static <T> zzmr<T> zza(zznx<?, ?> zznx, zzkr<?> zzkr, zzml zzml) {
        return new zzmr<>(zznx, zzkr, zzml);
    }

    public final T zza() {
        zzml zzml = this.zza;
        if (zzml instanceof zzlc) {
            return ((zzlc) zzml).zzce();
        }
        return zzml.zzci().zzaj();
    }

    public final void zza(T t2, T t3) {
        zznf.zza(this.zzb, t2, t3);
        if (this.zzc) {
            zznf.zza(this.zzd, t2, t3);
        }
    }

    public final void zza(T t2, zzna zzna, zzkp zzkp) throws IOException {
        boolean z2;
        zznx<?, ?> zznx = this.zzb;
        zzkr<?> zzkr = this.zzd;
        Object zzc2 = zznx.zzc(t2);
        zzkv<?> zzb2 = zzkr.zzb(t2);
        do {
            try {
                if (zzna.zzc() == Integer.MAX_VALUE) {
                    zznx.zzb((Object) t2, zzc2);
                    return;
                }
                int zzd2 = zzna.zzd();
                int i3 = 0;
                if (zzd2 == 11) {
                    Object obj = null;
                    zzjs zzjs = null;
                    while (zzna.zzc() != Integer.MAX_VALUE) {
                        int zzd3 = zzna.zzd();
                        if (zzd3 == 16) {
                            i3 = zzna.zzj();
                            obj = zzkr.zza(zzkp, this.zza, i3);
                        } else if (zzd3 == 26) {
                            if (obj != null) {
                                zzkr.zza(zzna, obj, zzkp, zzb2);
                            } else {
                                zzjs = zzna.zzp();
                            }
                        } else if (!zzna.zzt()) {
                            break;
                        }
                    }
                    if (zzna.zzd() != 12) {
                        throw zzlk.zzb();
                    } else if (zzjs != null) {
                        if (obj != null) {
                            zzkr.zza(zzjs, obj, zzkp, zzb2);
                        } else {
                            zznx.zza(zzc2, i3, zzjs);
                        }
                    }
                } else if ((zzd2 & 7) == 2) {
                    Object zza2 = zzkr.zza(zzkp, this.zza, zzd2 >>> 3);
                    if (zza2 != null) {
                        zzkr.zza(zzna, zza2, zzkp, zzb2);
                    } else {
                        z2 = zznx.zza(zzc2, zzna, 0);
                        continue;
                    }
                } else {
                    z2 = zzna.zzt();
                    continue;
                }
                z2 = true;
                continue;
            } catch (Throwable th) {
                zznx.zzb((Object) t2, zzc2);
                throw th;
            }
        } while (z2);
        zznx.zzb((Object) t2, zzc2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.measurement.zzlc$zzf} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.measurement.zzjn r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.measurement.zzlc r0 = (com.google.android.gms.internal.measurement.zzlc) r0
            com.google.android.gms.internal.measurement.zznw r1 = r0.zzb
            com.google.android.gms.internal.measurement.zznw r2 = com.google.android.gms.internal.measurement.zznw.zzc()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.measurement.zznw r1 = com.google.android.gms.internal.measurement.zznw.zzd()
            r0.zzb = r1
        L_0x0011:
            com.google.android.gms.internal.measurement.zzlc$zzd r10 = (com.google.android.gms.internal.measurement.zzlc.zzd) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00a4
            int r4 = com.google.android.gms.internal.measurement.zzjo.zzc(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0051
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004c
            com.google.android.gms.internal.measurement.zzkr<?> r12 = r9.zzd
            com.google.android.gms.internal.measurement.zzkp r0 = r14.zzd
            com.google.android.gms.internal.measurement.zzml r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.measurement.zzlc$zzf r0 = (com.google.android.gms.internal.measurement.zzlc.zzf) r0
            if (r0 != 0) goto L_0x0043
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.measurement.zzjo.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.measurement.zznw) r6, (com.google.android.gms.internal.measurement.zzjn) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.measurement.zzmz.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x004c:
            int r12 = com.google.android.gms.internal.measurement.zzjo.zza((int) r2, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.measurement.zzjn) r14)
            goto L_0x0018
        L_0x0051:
            r12 = 0
            r2 = r10
        L_0x0053:
            if (r4 >= r13) goto L_0x0099
            int r4 = com.google.android.gms.internal.measurement.zzjo.zzc(r11, r4, r14)
            int r5 = r14.zza
            int r6 = r5 >>> 3
            r7 = r5 & 7
            if (r6 == r3) goto L_0x007b
            r8 = 3
            if (r6 == r8) goto L_0x0065
            goto L_0x0090
        L_0x0065:
            if (r0 != 0) goto L_0x0072
            if (r7 != r3) goto L_0x0090
            int r4 = com.google.android.gms.internal.measurement.zzjo.zza(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.measurement.zzjs r2 = (com.google.android.gms.internal.measurement.zzjs) r2
            goto L_0x0053
        L_0x0072:
            com.google.android.gms.internal.measurement.zzmz.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x007b:
            if (r7 != 0) goto L_0x0090
            int r4 = com.google.android.gms.internal.measurement.zzjo.zzc(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.measurement.zzkr<?> r0 = r9.zzd
            com.google.android.gms.internal.measurement.zzkp r5 = r14.zzd
            com.google.android.gms.internal.measurement.zzml r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.measurement.zzlc$zzf r0 = (com.google.android.gms.internal.measurement.zzlc.zzf) r0
            goto L_0x0053
        L_0x0090:
            r6 = 12
            if (r5 == r6) goto L_0x0099
            int r4 = com.google.android.gms.internal.measurement.zzjo.zza((int) r5, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.measurement.zzjn) r14)
            goto L_0x0053
        L_0x0099:
            if (r2 == 0) goto L_0x00a1
            int r12 = r12 << 3
            r12 = r12 | r3
            r1.zza((int) r12, (java.lang.Object) r2)
        L_0x00a1:
            r12 = r4
            goto L_0x0018
        L_0x00a4:
            if (r12 != r13) goto L_0x00a7
            return
        L_0x00a7:
            com.google.android.gms.internal.measurement.zzlk r9 = com.google.android.gms.internal.measurement.zzlk.zzg()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzmr.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzjn):void");
    }

    public final void zza(T t2, zzos zzos) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd2 = this.zzd.zza((Object) t2).zzd();
        while (zzd2.hasNext()) {
            Map.Entry next = zzd2.next();
            zzkx zzkx = (zzkx) next.getKey();
            if (zzkx.zzc() != zzop.MESSAGE || zzkx.zze() || zzkx.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzlo) {
                zzos.zza(zzkx.zza(), (Object) ((zzlo) next).zza().zzb());
            } else {
                zzos.zza(zzkx.zza(), next.getValue());
            }
        }
        zznx<?, ?> zznx = this.zzb;
        zznx.zza(zznx.zzd(t2), zzos);
    }
}
