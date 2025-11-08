package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.appsamurai.storyly.exoplayer2.core.source.chunk.a;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

final class zzdx {
    private static final zzdx zzb = new zzdx(true);
    final zzgo zza = new zzgh();
    private boolean zzc;
    private boolean zzd;

    private zzdx() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005e, code lost:
        r1 = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0123, code lost:
        return r4 + r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw r4, java.lang.Object r5) {
        /*
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf r0 = r4.zzd()
            int r1 = r4.zza()
            r4.zzg()
            int r4 = r1 << 3
            int r4 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r4)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf.GROUP
            if (r0 != r1) goto L_0x0022
            r1 = r5
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r1 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm) r1
            byte[] r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzep.zzb
            boolean r1 = r1 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcr
            if (r1 != 0) goto L_0x0020
            int r4 = r4 + r4
            goto L_0x0022
        L_0x0020:
            r4 = 0
            throw r4
        L_0x0022:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg.INT
            int r0 = r0.ordinal()
            r1 = 4
            r2 = 8
            switch(r0) {
                case 0: goto L_0x011b;
                case 1: goto L_0x0115;
                case 2: goto L_0x010a;
                case 3: goto L_0x00ff;
                case 4: goto L_0x00f3;
                case 5: goto L_0x00ec;
                case 6: goto L_0x00e6;
                case 7: goto L_0x00df;
                case 8: goto L_0x00c9;
                case 9: goto L_0x00c2;
                case 10: goto L_0x00ac;
                case 11: goto L_0x0092;
                case 12: goto L_0x0086;
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
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x0048:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r0 = r5 + r5
            int r5 = r5 >> 31
            r5 = r5 ^ r0
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
            goto L_0x0122
        L_0x0059:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.getClass()
        L_0x005e:
            r1 = r2
            goto L_0x0122
        L_0x0061:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.getClass()
            goto L_0x0122
        L_0x0068:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej
            if (r0 == 0) goto L_0x0079
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej) r5
            int r5 = r5.zza()
            long r0 = (long) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x0079:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            long r0 = (long) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x0086:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
            goto L_0x0122
        L_0x0092:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf
            if (r0 == 0) goto L_0x00a4
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
        L_0x00a0:
            int r1 = r0 + r5
            goto L_0x0122
        L_0x00a4:
            byte[] r5 = (byte[]) r5
            int r5 = r5.length
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
            goto L_0x00a0
        L_0x00ac:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
            if (r0 == 0) goto L_0x00bb
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew) r5
            int r5 = r5.zza()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
            goto L_0x00a0
        L_0x00bb:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzx(r5)
            goto L_0x0122
        L_0x00c2:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm) r5
            int r1 = r5.zzF()
            goto L_0x0122
        L_0x00c9:
            boolean r0 = r5 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf
            if (r0 == 0) goto L_0x00d8
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf r5 = (com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf) r5
            int r5 = r5.zzd()
            int r0 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzA(r5)
            goto L_0x00a0
        L_0x00d8:
            java.lang.String r5 = (java.lang.String) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzz(r5)
            goto L_0x0122
        L_0x00df:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            r5.getClass()
            r1 = 1
            goto L_0x0122
        L_0x00e6:
            java.lang.Integer r5 = (java.lang.Integer) r5
            r5.getClass()
            goto L_0x0122
        L_0x00ec:
            java.lang.Long r5 = (java.lang.Long) r5
            r5.getClass()
            goto L_0x005e
        L_0x00f3:
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            long r0 = (long) r5
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x00ff:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x010a:
            java.lang.Long r5 = (java.lang.Long) r5
            long r0 = r5.longValue()
            int r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn.zzB(r0)
            goto L_0x0122
        L_0x0115:
            java.lang.Float r5 = (java.lang.Float) r5
            r5.getClass()
            goto L_0x0122
        L_0x011b:
            java.lang.Double r5 = (java.lang.Double) r5
            r5.getClass()
            goto L_0x005e
        L_0x0122:
            int r4 = r4 + r1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx.zza(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw, java.lang.Object):int");
    }

    public static zzdx zzd() {
        return zzb;
    }

    private static Object zzl(Object obj) {
        if (obj instanceof zzfr) {
            return ((zzfr) obj).zzc();
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
        zzdw zzdw = (zzdw) entry.getKey();
        Object value = entry.getValue();
        boolean z2 = value instanceof zzew;
        zzdw.zzg();
        if (zzdw.zze() == zzhg.MESSAGE) {
            Object zze = zze(zzdw);
            if (zze == null) {
                this.zza.put(zzdw, zzl(value));
                if (z2) {
                    this.zzd = true;
                }
            } else if (!z2) {
                this.zza.put(zzdw, zze instanceof zzfr ? zzdw.zzc((zzfr) zze, (zzfr) value) : zzdw.zzb(((zzfm) zze).zzaa(), (zzfm) value).zzj());
            } else {
                throw null;
            }
        } else if (!z2) {
            this.zza.put(zzdw, zzl(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private static boolean zzn(Map.Entry entry) {
        zzdw zzdw = (zzdw) entry.getKey();
        if (zzdw.zze() != zzhg.MESSAGE) {
            return true;
        }
        zzdw.zzg();
        Object value = entry.getValue();
        if (value instanceof zzfn) {
            return ((zzfn) value).zzad();
        }
        if (value instanceof zzew) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    private static final int zzo(Map.Entry entry) {
        int i3;
        int zzA;
        int zzx;
        zzdw zzdw = (zzdw) entry.getKey();
        Object value = entry.getValue();
        if (zzdw.zze() != zzhg.MESSAGE) {
            return zza(zzdw, value);
        }
        zzdw.zzg();
        zzdw.zzf();
        if (value instanceof zzew) {
            int zza2 = ((zzdw) entry.getKey()).zza();
            int zzA2 = zzdn.zzA(8);
            i3 = zzA2 + zzA2;
            zzA = zzdn.zzA(zza2) + zzdn.zzA(16);
            int zzA3 = zzdn.zzA(24);
            int zza3 = ((zzew) value).zza();
            zzx = a.b(zza3, zza3, zzA3);
        } else {
            int zza4 = ((zzdw) entry.getKey()).zza();
            int zzA4 = zzdn.zzA(8);
            i3 = zzA4 + zzA4;
            zzA = zzdn.zzA(zza4) + zzdn.zzA(16);
            zzx = zzdn.zzx((zzfm) value) + zzdn.zzA(24);
        }
        return i3 + zzA + zzx;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdx)) {
            return false;
        }
        return this.zza.equals(((zzdx) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final int zzb() {
        int zzc2 = this.zza.zzc();
        int i3 = 0;
        for (int i4 = 0; i4 < zzc2; i4++) {
            i3 += zzo(this.zza.zzg(i4));
        }
        for (Map.Entry zzo : this.zza.zzd()) {
            i3 += zzo(zzo);
        }
        return i3;
    }

    /* renamed from: zzc */
    public final zzdx clone() {
        zzdx zzdx = new zzdx();
        int zzc2 = this.zza.zzc();
        for (int i3 = 0; i3 < zzc2; i3++) {
            Map.Entry zzg = this.zza.zzg(i3);
            zzdx.zzi((zzdw) ((zzgi) zzg).zza(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzd()) {
            zzdx.zzi((zzdw) entry.getKey(), entry.getValue());
        }
        zzdx.zzd = this.zzd;
        return zzdx;
    }

    public final Object zze(zzdw zzdw) {
        Object obj = this.zza.get(zzdw);
        if (!(obj instanceof zzew)) {
            return obj;
        }
        throw null;
    }

    public final Iterator zzf() {
        return this.zza.isEmpty() ? Collections.emptyIterator() : this.zzd ? new zzev(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zzg() {
        if (!this.zzc) {
            int zzc2 = this.zza.zzc();
            for (int i3 = 0; i3 < zzc2; i3++) {
                Map.Entry zzg = this.zza.zzg(i3);
                if (zzg.getValue() instanceof zzeh) {
                    ((zzeh) zzg.getValue()).zzT();
                }
            }
            this.zza.zza();
            this.zzc = true;
        }
    }

    public final void zzh(zzdx zzdx) {
        int zzc2 = zzdx.zza.zzc();
        for (int i3 = 0; i3 < zzc2; i3++) {
            zzm(zzdx.zza.zzg(i3));
        }
        for (Map.Entry zzm : zzdx.zza.zzd()) {
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
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew) == false) goto L_0x0051;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        r2.zzd = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        r2.zza.zzf(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007e, code lost:
        throw new java.lang.IllegalArgumentException(java.lang.String.format("Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n", new java.lang.Object[]{java.lang.Integer.valueOf(r3.zza()), r3.zzd().zza(), r4.getClass().getName()}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0022, code lost:
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew) == false) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if ((r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej) == false) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw r3, java.lang.Object r4) {
        /*
            r2 = this;
            r3.zzg()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf r0 = r3.zzd()
            byte[] r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzep.zzb
            r4.getClass()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf.DOUBLE
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg r1 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg.INT
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg r0 = r0.zza()
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
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
            if (r0 != 0) goto L_0x004a
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
            if (r0 == 0) goto L_0x0057
            goto L_0x004a
        L_0x0025:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x004a
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej
            if (r0 == 0) goto L_0x0057
            goto L_0x004a
        L_0x002e:
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdf
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
            boolean r0 = r4 instanceof com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzew
            if (r0 == 0) goto L_0x0051
            r0 = 1
            r2.zzd = r0
        L_0x0051:
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgo r2 = r2.zza
            r2.put(r3, r4)
            return
        L_0x0057:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            int r0 = r3.zza()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhf r3 = r3.zzd()
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzhg r3 = r3.zza()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r0, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdx.zzi(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw, java.lang.Object):void");
    }

    public final boolean zzj() {
        return this.zzc;
    }

    public final boolean zzk() {
        int zzc2 = this.zza.zzc();
        for (int i3 = 0; i3 < zzc2; i3++) {
            if (!zzn(this.zza.zzg(i3))) {
                return false;
            }
        }
        for (Map.Entry zzn : this.zza.zzd()) {
            if (!zzn(zzn)) {
                return false;
            }
        }
        return true;
    }

    private zzdx(boolean z2) {
        zzg();
        zzg();
    }
}
