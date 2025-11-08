package com.google.android.recaptcha.internal;

import android.support.v4.media.session.a;
import java.util.Iterator;
import java.util.Map;

final class zzij {
    private static final zzij zzb = new zzij(true);
    final zzle zza = new zzku(16);
    private boolean zzc;
    private boolean zzd;

    private zzij() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005e, code lost:
        r1 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0120, code lost:
        return r4 + r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(com.google.android.recaptcha.internal.zzii r4, java.lang.Object r5) {
        /*
            com.google.android.recaptcha.internal.zzmb r0 = r4.zzd()
            int r1 = r4.zza()
            r4.zzg()
            int r4 = r1 << 3
            int r4 = com.google.android.recaptcha.internal.zzhh.zzy(r4)
            com.google.android.recaptcha.internal.zzmb r1 = com.google.android.recaptcha.internal.zzmb.GROUP
            if (r0 != r1) goto L_0x0022
            r1 = r5
            com.google.android.recaptcha.internal.zzke r1 = (com.google.android.recaptcha.internal.zzke) r1
            byte[] r2 = com.google.android.recaptcha.internal.zzjc.zzd
            boolean r1 = r1 instanceof com.google.android.recaptcha.internal.zzgg
            if (r1 != 0) goto L_0x0020
            int r4 = r4 + r4
            goto L_0x0022
        L_0x0020:
            r4 = 0
            throw r4
        L_0x0022:
            com.google.android.recaptcha.internal.zzmc r1 = com.google.android.recaptcha.internal.zzmc.INT
            int r0 = r0.ordinal()
            r1 = 4
            r2 = 8
            switch(r0) {
                case 0: goto L_0x0118;
                case 1: goto L_0x0112;
                case 2: goto L_0x0107;
                case 3: goto L_0x00fc;
                case 4: goto L_0x00f1;
                case 5: goto L_0x00ea;
                case 6: goto L_0x00e4;
                case 7: goto L_0x00dd;
                case 8: goto L_0x00c7;
                case 9: goto L_0x00c0;
                case 10: goto L_0x00aa;
                case 11: goto L_0x0090;
                case 12: goto L_0x0084;
                case 13: goto L_0x0068;
                case 14: goto L_0x0061;
                case 15: goto L_0x0059;
                case 16: goto L_0x0048;
                case 17: goto L_0x0036;
                default: goto L_0x002e;
            }
        L_0x002e:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.String r5 = "There is no way to get here, but the compiler thinks otherwise."
            r4.<init>(r5)
            throw r4
        L_0x0036:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            long r2 = r0 + r0
            r5 = 63
            long r0 = r0 >> r5
            long r0 = r0 ^ r2
            int r1 = com.google.android.recaptcha.internal.zzhh.zzz(r0)
            goto L_0x011f
        L_0x0048:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r0 = r5 + r5
            int r5 = r5 >> 31
            r5 = r5 ^ r0
            int r1 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
            goto L_0x011f
        L_0x0059:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.getClass()
        L_0x005e:
            r1 = r2
            goto L_0x011f
        L_0x0061:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.getClass()
            goto L_0x011f
        L_0x0068:
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zziv
            if (r0 == 0) goto L_0x0078
            com.google.android.recaptcha.internal.zziv r5 = (com.google.android.recaptcha.internal.zziv) r5
            int r5 = r5.zza()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzu(r5)
            goto L_0x011f
        L_0x0078:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzu(r5)
            goto L_0x011f
        L_0x0084:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
            goto L_0x011f
        L_0x0090:
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zzgw
            if (r0 == 0) goto L_0x00a2
            com.google.android.recaptcha.internal.zzgw r5 = (com.google.android.recaptcha.internal.zzgw) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
        L_0x009e:
            int r1 = r0 + r5
            goto L_0x011f
        L_0x00a2:
            byte[] r5 = (byte[]) r5
            int r5 = r5.length
            int r0 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
            goto L_0x009e
        L_0x00aa:
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zzjj
            if (r0 == 0) goto L_0x00b9
            com.google.android.recaptcha.internal.zzjj r5 = (com.google.android.recaptcha.internal.zzjj) r5
            int r5 = r5.zza()
            int r0 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
            goto L_0x009e
        L_0x00b9:
            com.google.android.recaptcha.internal.zzke r5 = (com.google.android.recaptcha.internal.zzke) r5
            int r1 = com.google.android.recaptcha.internal.zzhh.zzv(r5)
            goto L_0x011f
        L_0x00c0:
            com.google.android.recaptcha.internal.zzke r5 = (com.google.android.recaptcha.internal.zzke) r5
            int r1 = r5.zzn()
            goto L_0x011f
        L_0x00c7:
            boolean r0 = r5 instanceof com.google.android.recaptcha.internal.zzgw
            if (r0 == 0) goto L_0x00d6
            com.google.android.recaptcha.internal.zzgw r5 = (com.google.android.recaptcha.internal.zzgw) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.recaptcha.internal.zzhh.zzy(r5)
            goto L_0x009e
        L_0x00d6:
            java.lang.String r5 = (java.lang.String) r5
            int r1 = com.google.android.recaptcha.internal.zzhh.zzx(r5)
            goto L_0x011f
        L_0x00dd:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            r5.getClass()
            r1 = 1
            goto L_0x011f
        L_0x00e4:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.getClass()
            goto L_0x011f
        L_0x00ea:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.getClass()
            goto L_0x005e
        L_0x00f1:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzu(r5)
            goto L_0x011f
        L_0x00fc:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzz(r0)
            goto L_0x011f
        L_0x0107:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.recaptcha.internal.zzhh.zzz(r0)
            goto L_0x011f
        L_0x0112:
            java.lang.Float r5 = (java.lang.Float) r5
            r5.getClass()
            goto L_0x011f
        L_0x0118:
            java.lang.Double r5 = (java.lang.Double) r5
            r5.getClass()
            goto L_0x005e
        L_0x011f:
            int r4 = r4 + r1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzij.zza(com.google.android.recaptcha.internal.zzii, java.lang.Object):int");
    }

    public static zzij zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzkj) {
            return ((zzkj) obj).zzd();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private final void zzm(Map.Entry entry) {
        Object obj;
        zzii zzii = (zzii) entry.getKey();
        Object value = entry.getValue();
        boolean z2 = value instanceof zzjj;
        zzii.zzg();
        if (zzii.zze() == zzmc.MESSAGE) {
            Object zze = zze(zzii);
            if (zze == null) {
                this.zza.put(zzii, zzl(value));
                if (z2) {
                    this.zzd = true;
                }
            } else if (!z2) {
                if (zze instanceof zzkj) {
                    obj = zzii.zzc((zzkj) zze, (zzkj) value);
                } else {
                    zzkd zzX = ((zzke) zze).zzX();
                    zzii.zzb(zzX, (zzke) value);
                    obj = zzX.zzj();
                }
                this.zza.put(zzii, obj);
            } else {
                throw null;
            }
        } else if (!z2) {
            this.zza.put(zzii, zzl(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private static boolean zzn(Map.Entry entry) {
        zzii zzii = (zzii) entry.getKey();
        if (zzii.zze() != zzmc.MESSAGE) {
            return true;
        }
        zzii.zzg();
        Object value = entry.getValue();
        if (value instanceof zzkf) {
            return ((zzkf) value).zzo();
        }
        if (value instanceof zzjj) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        zzii zzii = (zzii) entry.getKey();
        Object value = entry.getValue();
        if (zzii.zze() != zzmc.MESSAGE) {
            return zza(zzii, value);
        }
        zzii.zzg();
        zzii.zzf();
        if (value instanceof zzjj) {
            int zzy = zzhh.zzy(((zzii) entry.getKey()).zza());
            int zza2 = ((zzjj) value).zza();
            int zzy2 = zzhh.zzy(zza2) + zza2;
            int zzy3 = zzhh.zzy(24);
            int zzy4 = zzhh.zzy(16);
            int zzy5 = zzhh.zzy(8);
            return zzy3 + zzy2 + zzy4 + zzy + zzy5 + zzy5;
        }
        int zzy6 = zzhh.zzy(((zzii) entry.getKey()).zza());
        int zzy7 = zzhh.zzy(24) + zzhh.zzv((zzke) value);
        int zzy8 = zzhh.zzy(16);
        int zzy9 = zzhh.zzy(8);
        return a.D(zzy8, zzy6, zzy9 + zzy9, zzy7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzij)) {
            return false;
        }
        return this.zza.equals(((zzij) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int i3 = 0;
        for (int i4 = 0; i4 < this.zza.zzb(); i4++) {
            i3 += zzo(this.zza.zzg(i4));
        }
        for (Map.Entry zzo : this.zza.zzc()) {
            i3 += zzo(zzo);
        }
        return i3;
    }

    /* renamed from: zzc */
    public final zzij clone() {
        zzij zzij = new zzij();
        for (int i3 = 0; i3 < this.zza.zzb(); i3++) {
            Map.Entry zzg = this.zza.zzg(i3);
            zzij.zzi((zzii) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzij.zzi((zzii) entry.getKey(), entry.getValue());
        }
        zzij.zzd = this.zzd;
        return zzij;
    }

    public final Object zze(zzii zzii) {
        Object obj = this.zza.get(zzii);
        if (!(obj instanceof zzjj)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        boolean z2 = this.zzd;
        zzle zzle = this.zza;
        return z2 ? new zzji(zzle.entrySet().iterator()) : zzle.entrySet().iterator();
    }

    public final void zzg() {
        if (!this.zzc) {
            for (int i3 = 0; i3 < this.zza.zzb(); i3++) {
                Map.Entry zzg = this.zza.zzg(i3);
                if (zzg.getValue() instanceof zzit) {
                    ((zzit) zzg.getValue()).zzB();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzh(zzij zzij) {
        for (int i3 = 0; i3 < zzij.zza.zzb(); i3++) {
            zzm(zzij.zza.zzg(i3));
        }
        for (Map.Entry zzm : zzij.zza.zzc()) {
            zzm(zzm);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r0 != false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        if ((r4 instanceof com.google.android.recaptcha.internal.zzjj) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r2.zzd = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        r2.zza.zze(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007e, code lost:
        throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new java.lang.Object[]{java.lang.Integer.valueOf(r3.zza()), r3.zzd().zza(), r4.getClass().getName()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0022, code lost:
        if ((r4 instanceof com.google.android.recaptcha.internal.zzjj) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if ((r4 instanceof com.google.android.recaptcha.internal.zziv) == false) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(com.google.android.recaptcha.internal.zzii r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.zzg()
            com.google.android.recaptcha.internal.zzmb r0 = r3.zzd()
            byte[] r1 = com.google.android.recaptcha.internal.zzjc.zzd
            r4.getClass()
            com.google.android.recaptcha.internal.zzmb r1 = com.google.android.recaptcha.internal.zzmb.DOUBLE
            com.google.android.recaptcha.internal.zzmc r1 = com.google.android.recaptcha.internal.zzmc.INT
            com.google.android.recaptcha.internal.zzmc r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0043;
                case 2: goto L_0x0040;
                case 3: goto L_0x003d;
                case 4: goto L_0x003a;
                case 5: goto L_0x0037;
                case 6: goto L_0x002e;
                case 7: goto L_0x0025;
                case 8: goto L_0x001c;
                default: goto L_0x001b;
            }
        L_0x001b:
            goto L_0x0057
        L_0x001c:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzke
            if (r0 != 0) goto L_0x004a
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzjj
            if (r0 == 0) goto L_0x0057
            goto L_0x004a
        L_0x0025:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x004a
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zziv
            if (r0 == 0) goto L_0x0057
            goto L_0x004a
        L_0x002e:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzgw
            if (r0 != 0) goto L_0x004a
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L_0x0057
            goto L_0x004a
        L_0x0037:
            boolean r0 = r4 instanceof java.lang.String
            goto L_0x0048
        L_0x003a:
            boolean r0 = r4 instanceof java.lang.Boolean
            goto L_0x0048
        L_0x003d:
            boolean r0 = r4 instanceof java.lang.Double
            goto L_0x0048
        L_0x0040:
            boolean r0 = r4 instanceof java.lang.Float
            goto L_0x0048
        L_0x0043:
            boolean r0 = r4 instanceof java.lang.Long
            goto L_0x0048
        L_0x0046:
            boolean r0 = r4 instanceof java.lang.Integer
        L_0x0048:
            if (r0 == 0) goto L_0x0057
        L_0x004a:
            boolean r0 = r4 instanceof com.google.android.recaptcha.internal.zzjj
            if (r0 == 0) goto L_0x0051
            r0 = 1
            r2.zzd = r0
        L_0x0051:
            com.google.android.recaptcha.internal.zzle r2 = r2.zza
            r2.put(r3, r4)
            return
        L_0x0057:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            int r0 = r3.zza()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.google.android.recaptcha.internal.zzmb r3 = r3.zzd()
            com.google.android.recaptcha.internal.zzmc r3 = r3.zza()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.recaptcha.internal.zzij.zzi(com.google.android.recaptcha.internal.zzii, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        for (int i3 = 0; i3 < this.zza.zzb(); i3++) {
            if (!zzn(this.zza.zzg(i3))) {
                return false;
            }
        }
        for (Map.Entry zzn : this.zza.zzc()) {
            if (!zzn(zzn)) {
                return false;
            }
        }
        return true;
    }

    private zzij(boolean z2) {
        zzg();
        zzg();
    }
}
