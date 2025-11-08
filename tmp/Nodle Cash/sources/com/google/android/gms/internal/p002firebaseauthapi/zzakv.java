package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakv  reason: invalid package */
final class zzakv<T> implements zzalh<T> {
    private final zzakp zza;
    private final zzamg<?, ?> zzb;
    private final boolean zzc;
    private final zzais<?> zzd;

    private zzakv(zzamg<?, ?> zzamg, zzais<?> zzais, zzakp zzakp) {
        this.zzb = zzamg;
        this.zzc = zzais.zza(zzakp);
        this.zzd = zzais;
        this.zza = zzakp;
    }

    public final int zza(T t2) {
        zzamg<?, ?> zzamg = this.zzb;
        int zzb2 = zzamg.zzb(zzamg.zzd(t2));
        return this.zzc ? zzb2 + this.zzd.zza((Object) t2).zza() : zzb2;
    }

    public final int zzb(T t2) {
        int hashCode = this.zzb.zzd(t2).hashCode();
        return this.zzc ? (hashCode * 53) + this.zzd.zza((Object) t2).hashCode() : hashCode;
    }

    public final void zzc(T t2) {
        this.zzb.zzf(t2);
        this.zzd.zzc(t2);
    }

    public final boolean zzd(T t2) {
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

    public static <T> zzakv<T> zza(zzamg<?, ?> zzamg, zzais<?> zzais, zzakp zzakp) {
        return new zzakv<>(zzamg, zzais, zzakp);
    }

    public final T zza() {
        zzakp zzakp = this.zza;
        if (zzakp instanceof zzaje) {
            return ((zzaje) zzakp).zzo();
        }
        return zzakp.zzq().zzg();
    }

    public final void zza(T t2, T t3) {
        zzalj.zza(this.zzb, t2, t3);
        if (this.zzc) {
            zzalj.zza(this.zzd, t2, t3);
        }
    }

    public final void zza(T t2, zzale zzale, zzaiq zzaiq) throws IOException {
        boolean z2;
        zzamg<?, ?> zzamg = this.zzb;
        zzais<?> zzais = this.zzd;
        Object zzc2 = zzamg.zzc(t2);
        zzaix<?> zzb2 = zzais.zzb(t2);
        do {
            try {
                if (zzale.zzc() == Integer.MAX_VALUE) {
                    zzamg.zzb((Object) t2, zzc2);
                    return;
                }
                int zzd2 = zzale.zzd();
                if (zzd2 == 11) {
                    Object obj = null;
                    int i3 = 0;
                    zzaho zzaho = null;
                    while (zzale.zzc() != Integer.MAX_VALUE) {
                        int zzd3 = zzale.zzd();
                        if (zzd3 == 16) {
                            i3 = zzale.zzj();
                            obj = zzais.zza(zzaiq, this.zza, i3);
                        } else if (zzd3 == 26) {
                            if (obj != null) {
                                zzais.zza(zzale, obj, zzaiq, zzb2);
                            } else {
                                zzaho = zzale.zzp();
                            }
                        } else if (!zzale.zzt()) {
                            break;
                        }
                    }
                    if (zzale.zzd() != 12) {
                        throw zzajk.zzb();
                    } else if (zzaho != null) {
                        if (obj != null) {
                            zzais.zza(zzaho, obj, zzaiq, zzb2);
                        } else {
                            zzamg.zza(zzc2, i3, zzaho);
                        }
                    }
                } else if ((zzd2 & 7) == 2) {
                    Object zza2 = zzais.zza(zzaiq, this.zza, zzd2 >>> 3);
                    if (zza2 != null) {
                        zzais.zza(zzale, zza2, zzaiq, zzb2);
                    } else {
                        z2 = zzamg.zza(zzc2, zzale);
                        continue;
                    }
                } else {
                    z2 = zzale.zzt();
                    continue;
                }
                z2 = true;
                continue;
            } catch (Throwable th) {
                zzamg.zzb((Object) t2, zzc2);
                throw th;
            }
        } while (z2);
        zzamg.zzb((Object) t2, zzc2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaje$zzd} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r10, byte[] r11, int r12, int r13, com.google.android.gms.internal.p002firebaseauthapi.zzahn r14) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r10
            com.google.android.gms.internal.firebase-auth-api.zzaje r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje) r0
            com.google.android.gms.internal.firebase-auth-api.zzamf r1 = r0.zzb
            com.google.android.gms.internal.firebase-auth-api.zzamf r2 = com.google.android.gms.internal.p002firebaseauthapi.zzamf.zzc()
            if (r1 != r2) goto L_0x0011
            com.google.android.gms.internal.firebase-auth-api.zzamf r1 = com.google.android.gms.internal.p002firebaseauthapi.zzamf.zzd()
            r0.zzb = r1
        L_0x0011:
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzb r10 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzb) r10
            r10.zza()
            r10 = 0
            r0 = r10
        L_0x0018:
            if (r12 >= r13) goto L_0x00a4
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r11, r12, r14)
            int r2 = r14.zza
            r12 = 11
            r3 = 2
            if (r2 == r12) goto L_0x0051
            r12 = r2 & 7
            if (r12 != r3) goto L_0x004c
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r12 = r9.zzd
            com.google.android.gms.internal.firebase-auth-api.zzaiq r0 = r14.zzd
            com.google.android.gms.internal.firebase-auth-api.zzakp r3 = r9.zza
            int r5 = r2 >>> 3
            java.lang.Object r12 = r12.zza(r0, r3, r5)
            r0 = r12
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzd r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzd) r0
            if (r0 != 0) goto L_0x0043
            r3 = r11
            r5 = r13
            r6 = r1
            r7 = r14
            int r12 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.p002firebaseauthapi.zzamf) r6, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r7)
            goto L_0x0018
        L_0x0043:
            com.google.android.gms.internal.p002firebaseauthapi.zzald.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x004c:
            int r12 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r2, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            goto L_0x0018
        L_0x0051:
            r12 = 0
            r2 = r10
        L_0x0053:
            if (r4 >= r13) goto L_0x0099
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r11, r4, r14)
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
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r11, r4, r14)
            java.lang.Object r2 = r14.zzc
            com.google.android.gms.internal.firebase-auth-api.zzaho r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r2
            goto L_0x0053
        L_0x0072:
            com.google.android.gms.internal.p002firebaseauthapi.zzald.zza()
            java.lang.NoSuchMethodError r9 = new java.lang.NoSuchMethodError
            r9.<init>()
            throw r9
        L_0x007b:
            if (r7 != 0) goto L_0x0090
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r11, r4, r14)
            int r12 = r14.zza
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r9.zzd
            com.google.android.gms.internal.firebase-auth-api.zzaiq r5 = r14.zzd
            com.google.android.gms.internal.firebase-auth-api.zzakp r6 = r9.zza
            java.lang.Object r0 = r0.zza(r5, r6, r12)
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzd r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzd) r0
            goto L_0x0053
        L_0x0090:
            r6 = 12
            if (r5 == r6) goto L_0x0099
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r5, (byte[]) r11, (int) r4, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
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
            com.google.android.gms.internal.firebase-auth-api.zzajk r9 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakv.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzahn):void");
    }

    public final void zza(T t2, zzanc zzanc) throws IOException {
        Iterator<Map.Entry<?, Object>> zzd2 = this.zzd.zza((Object) t2).zzd();
        while (zzd2.hasNext()) {
            Map.Entry next = zzd2.next();
            zzaiz zzaiz = (zzaiz) next.getKey();
            if (zzaiz.zzc() != zzand.MESSAGE || zzaiz.zze() || zzaiz.zzd()) {
                throw new IllegalStateException("Found invalid MessageSet item.");
            } else if (next instanceof zzajo) {
                zzanc.zza(zzaiz.zza(), (Object) ((zzajo) next).zza().zzc());
            } else {
                zzanc.zza(zzaiz.zza(), next.getValue());
            }
        }
        zzamg<?, ?> zzamg = this.zzb;
        zzamg.zza(zzamg.zzd(t2), zzanc);
    }
}
