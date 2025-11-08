package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzkx;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzkv<T extends zzkx<T>> {
    private static final zzkv<?> zzb = new zzkv<>(true);
    final zzne<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    private zzkv() {
        this.zza = new zznh();
    }

    public static int zza(zzof zzof, int i3, Object obj) {
        int zzi = zzkl.zzi(i3);
        if (zzof == zzof.GROUP) {
            zzle.zza((zzml) obj);
            zzi <<= 1;
        }
        return zzi + zza(zzof, obj);
    }

    public static <T extends zzkx<T>> zzkv<T> zzb() {
        return zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzkv zzkv = new zzkv();
        int zza2 = this.zza.zza();
        for (int i3 = 0; i3 < zza2; i3++) {
            Map.Entry<T, Object> zza3 = this.zza.zza(i3);
            zzkv.zzb((zzkx) zza3.getKey(), zza3.getValue());
        }
        for (Map.Entry next : this.zza.zzb()) {
            zzkv.zzb((zzkx) next.getKey(), next.getValue());
        }
        zzkv.zzd = this.zzd;
        return zzkv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkv)) {
            return false;
        }
        return this.zza.equals(((zzkv) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zza.isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.zzd) {
            return new zzlq(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        return this.zza.isEmpty() ? Collections.emptyIterator() : this.zzd ? new zzlq(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zze() {
        if (!this.zzc) {
            int zza2 = this.zza.zza();
            for (int i3 = 0; i3 < zza2; i3++) {
                Map.Entry<T, Object> zza3 = this.zza.zza(i3);
                if (zza3.getValue() instanceof zzlc) {
                    ((zzlc) zza3.getValue()).zzcl();
                }
            }
            this.zza.zzd();
            this.zzc = true;
        }
    }

    public final boolean zzf() {
        return this.zzc;
    }

    public final boolean zzg() {
        int zza2 = this.zza.zza();
        for (int i3 = 0; i3 < zza2; i3++) {
            if (!zzc(this.zza.zza(i3))) {
                return false;
            }
        }
        for (Map.Entry<T, Object> zzc2 : this.zza.zzb()) {
            if (!zzc(zzc2)) {
                return false;
            }
        }
        return true;
    }

    private final void zzb(Map.Entry<T, Object> entry) {
        Object obj;
        zzkx zzkx = (zzkx) entry.getKey();
        Object value = entry.getValue();
        boolean z2 = value instanceof zzlp;
        if (zzkx.zze()) {
            if (!z2) {
                Object zza2 = zza(zzkx);
                List list = (List) value;
                int size = list.size();
                if (zza2 == null) {
                    zza2 = new ArrayList(size);
                }
                List list2 = (List) zza2;
                for (int i3 = 0; i3 < size; i3++) {
                    list2.add(zza(list.get(i3)));
                }
                this.zza.put(zzkx, zza2);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzkx.zzc() == zzop.MESSAGE) {
            Object zza3 = zza(zzkx);
            if (zza3 == null) {
                this.zza.put(zzkx, zza(value));
                if (z2) {
                    this.zzd = true;
                }
            } else if (!z2) {
                if (zza3 instanceof zzmq) {
                    obj = zzkx.zza((zzmq) zza3, (zzmq) value);
                } else {
                    obj = zzkx.zza(((zzml) zza3).zzcj(), (zzml) value).zzai();
                }
                this.zza.put(zzkx, obj);
            } else {
                throw new NoSuchMethodError();
            }
        } else if (!z2) {
            this.zza.put(zzkx, zza(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private zzkv(zzne<T, Object> zzne) {
        this.zza = zzne;
        zze();
    }

    private static int zza(zzof zzof, Object obj) {
        switch (zzku.zzb[zzof.ordinal()]) {
            case 1:
                return zzkl.zza(((Double) obj).doubleValue());
            case 2:
                return zzkl.zza(((Float) obj).floatValue());
            case 3:
                return zzkl.zzd(((Long) obj).longValue());
            case 4:
                return zzkl.zzg(((Long) obj).longValue());
            case 5:
                return zzkl.zzf(((Integer) obj).intValue());
            case 6:
                return zzkl.zzc(((Long) obj).longValue());
            case 7:
                return zzkl.zze(((Integer) obj).intValue());
            case 8:
                return zzkl.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzkl.zzb((zzml) obj);
            case 10:
                if (obj instanceof zzlp) {
                    return zzkl.zza((zzlt) (zzlp) obj);
                }
                return zzkl.zzc((zzml) obj);
            case 11:
                if (obj instanceof zzjs) {
                    return zzkl.zzb((zzjs) obj);
                }
                return zzkl.zzb((String) obj);
            case 12:
                if (obj instanceof zzjs) {
                    return zzkl.zzb((zzjs) obj);
                }
                return zzkl.zza((byte[]) obj);
            case 13:
                return zzkl.zzj(((Integer) obj).intValue());
            case 14:
                return zzkl.zzg(((Integer) obj).intValue());
            case 15:
                return zzkl.zze(((Long) obj).longValue());
            case 16:
                return zzkl.zzh(((Integer) obj).intValue());
            case 17:
                return zzkl.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzlh) {
                    return zzkl.zzd(((zzlh) obj).zza());
                }
                return zzkl.zzd(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzlh) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if ((r4 instanceof com.google.android.gms.internal.measurement.zzlp) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(T r3, java.lang.Object r4) {
        /*
            com.google.android.gms.internal.measurement.zzof r0 = r3.zzb()
            com.google.android.gms.internal.measurement.zzle.zza(r4)
            int[] r1 = com.google.android.gms.internal.measurement.zzku.zza
            com.google.android.gms.internal.measurement.zzop r0 = r0.zzb()
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            r2 = 0
            switch(r0) {
                case 1: goto L_0x0044;
                case 2: goto L_0x0041;
                case 3: goto L_0x003e;
                case 4: goto L_0x003b;
                case 5: goto L_0x0038;
                case 6: goto L_0x0035;
                case 7: goto L_0x002c;
                case 8: goto L_0x0023;
                case 9: goto L_0x001a;
                default: goto L_0x0018;
            }
        L_0x0018:
            r1 = r2
            goto L_0x0046
        L_0x001a:
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzml
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzlp
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0023:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzlh
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x002c:
            boolean r0 = r4 instanceof com.google.android.gms.internal.measurement.zzjs
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof byte[]
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0035:
            boolean r1 = r4 instanceof java.lang.String
            goto L_0x0046
        L_0x0038:
            boolean r1 = r4 instanceof java.lang.Boolean
            goto L_0x0046
        L_0x003b:
            boolean r1 = r4 instanceof java.lang.Double
            goto L_0x0046
        L_0x003e:
            boolean r1 = r4 instanceof java.lang.Float
            goto L_0x0046
        L_0x0041:
            boolean r1 = r4 instanceof java.lang.Long
            goto L_0x0046
        L_0x0044:
            boolean r1 = r4 instanceof java.lang.Integer
        L_0x0046:
            if (r1 == 0) goto L_0x0049
            return
        L_0x0049:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r3.zza()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.android.gms.internal.measurement.zzof r3 = r3.zzb()
            com.google.android.gms.internal.measurement.zzop r3 = r3.zzb()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkv.zzc(com.google.android.gms.internal.measurement.zzkx, java.lang.Object):void");
    }

    private zzkv(boolean z2) {
        this(new zznh());
        zze();
    }

    private static <T extends zzkx<T>> boolean zzc(Map.Entry<T, Object> entry) {
        zzkx zzkx = (zzkx) entry.getKey();
        if (zzkx.zzc() != zzop.MESSAGE) {
            return true;
        }
        if (!zzkx.zze()) {
            return zzb(entry.getValue());
        }
        List list = (List) entry.getValue();
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (!zzb(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    private final void zzb(T t2, Object obj) {
        if (!t2.zze()) {
            zzc(t2, obj);
        } else if (obj instanceof List) {
            List list = (List) obj;
            int size = list.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                Object obj2 = list.get(i3);
                zzc(t2, obj2);
                arrayList.add(obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzlp) {
            this.zzd = true;
        }
        this.zza.put(t2, obj);
    }

    public static int zza(zzkx<?> zzkx, Object obj) {
        zzof zzb2 = zzkx.zzb();
        int zza2 = zzkx.zza();
        if (!zzkx.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List list = (List) obj;
        int size = list.size();
        int i3 = 0;
        if (!zzkx.zzd()) {
            int i4 = 0;
            while (i3 < size) {
                i4 += zza(zzb2, zza2, list.get(i3));
                i3++;
            }
            return i4;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            int i5 = 0;
            while (i3 < size) {
                i5 += zza(zzb2, list.get(i3));
                i3++;
            }
            return zzkl.zzj(i5) + zzkl.zzi(zza2) + i5;
        }
    }

    private static boolean zzb(Object obj) {
        if (obj instanceof zzmn) {
            return ((zzmn) obj).zzcn();
        }
        if (obj instanceof zzlp) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    public final int zza() {
        int zza2 = this.zza.zza();
        int i3 = 0;
        for (int i4 = 0; i4 < zza2; i4++) {
            i3 += zza(this.zza.zza(i4));
        }
        for (Map.Entry<T, Object> zza3 : this.zza.zzb()) {
            i3 += zza(zza3);
        }
        return i3;
    }

    private static int zza(Map.Entry<T, Object> entry) {
        zzkx zzkx = (zzkx) entry.getKey();
        Object value = entry.getValue();
        if (zzkx.zzc() != zzop.MESSAGE || zzkx.zze() || zzkx.zzd()) {
            return zza((zzkx<?>) zzkx, value);
        }
        if (value instanceof zzlp) {
            return zzkl.zza(((zzkx) entry.getKey()).zza(), (zzlt) (zzlp) value);
        }
        return zzkl.zzb(((zzkx) entry.getKey()).zza(), (zzml) value);
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzmq) {
            return ((zzmq) obj).zza();
        }
        if (!(obj instanceof byte[])) {
            return obj;
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private final Object zza(T t2) {
        Object obj = this.zza.get(t2);
        if (!(obj instanceof zzlp)) {
            return obj;
        }
        throw new NoSuchMethodError();
    }

    public final void zza(zzkv<T> zzkv) {
        int zza2 = zzkv.zza.zza();
        for (int i3 = 0; i3 < zza2; i3++) {
            zzb(zzkv.zza.zza(i3));
        }
        for (Map.Entry<T, Object> zzb2 : zzkv.zza.zzb()) {
            zzb(zzb2);
        }
    }

    public static void zza(zzkl zzkl, zzof zzof, int i3, Object obj) throws IOException {
        if (zzof == zzof.GROUP) {
            zzml zzml = (zzml) obj;
            zzle.zza(zzml);
            zzkl.zzc(i3, 3);
            zzml.zza(zzkl);
            zzkl.zzc(i3, 4);
            return;
        }
        zzkl.zzc(i3, zzof.zza());
        switch (zzku.zzb[zzof.ordinal()]) {
            case 1:
                zzkl.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzkl.zzb(((Float) obj).floatValue());
                return;
            case 3:
                zzkl.zzb(((Long) obj).longValue());
                return;
            case 4:
                zzkl.zzb(((Long) obj).longValue());
                return;
            case 5:
                zzkl.zzb(((Integer) obj).intValue());
                return;
            case 6:
                zzkl.zza(((Long) obj).longValue());
                return;
            case 7:
                zzkl.zza(((Integer) obj).intValue());
                return;
            case 8:
                zzkl.zzb(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzml) obj).zza(zzkl);
                return;
            case 10:
                zzkl.zza((zzml) obj);
                return;
            case 11:
                if (obj instanceof zzjs) {
                    zzkl.zza((zzjs) obj);
                    return;
                } else {
                    zzkl.zza((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzjs) {
                    zzkl.zza((zzjs) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzkl.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzkl.zzc(((Integer) obj).intValue());
                return;
            case 14:
                zzkl.zza(((Integer) obj).intValue());
                return;
            case 15:
                zzkl.zza(((Long) obj).longValue());
                return;
            case 16:
                zzkl.zzk(((Integer) obj).intValue());
                return;
            case 17:
                zzkl.zzh(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzlh) {
                    zzkl.zzb(((zzlh) obj).zza());
                    return;
                } else {
                    zzkl.zzb(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }
}
