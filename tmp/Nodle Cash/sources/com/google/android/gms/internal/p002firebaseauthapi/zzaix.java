package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaiz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaix  reason: invalid package */
final class zzaix<T extends zzaiz<T>> {
    private static final zzaix zzb = new zzaix(true);
    final zzali<T, Object> zza;
    private boolean zzc;
    private boolean zzd;

    private zzaix() {
        this.zza = zzali.zza(16);
    }

    public static int zza(zzamt zzamt, int i3, Object obj) {
        int zzi = zzaim.zzi(i3);
        if (zzamt == zzamt.GROUP) {
            zzajh.zza((zzakp) obj);
            zzi <<= 1;
        }
        return zzi + zza(zzamt, obj);
    }

    public static <T extends zzaiz<T>> zzaix<T> zzb() {
        return zzb;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzaix zzaix = new zzaix();
        for (int i3 = 0; i3 < this.zza.zza(); i3++) {
            Map.Entry<T, Object> zzb2 = this.zza.zzb(i3);
            zzaix.zzb((zzaiz) zzb2.getKey(), zzb2.getValue());
        }
        for (Map.Entry next : this.zza.zzb()) {
            zzaix.zzb((zzaiz) next.getKey(), next.getValue());
        }
        zzaix.zzd = this.zzd;
        return zzaix;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaix)) {
            return false;
        }
        return this.zza.equals(((zzaix) obj).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final Iterator<Map.Entry<T, Object>> zzc() {
        if (this.zzd) {
            return new zzajq(this.zza.zzc().iterator());
        }
        return this.zza.zzc().iterator();
    }

    public final Iterator<Map.Entry<T, Object>> zzd() {
        return this.zzd ? new zzajq(this.zza.entrySet().iterator()) : this.zza.entrySet().iterator();
    }

    public final void zze() {
        if (!this.zzc) {
            for (int i3 = 0; i3 < this.zza.zza(); i3++) {
                Map.Entry<T, Object> zzb2 = this.zza.zzb(i3);
                if (zzb2.getValue() instanceof zzaje) {
                    ((zzaje) zzb2.getValue()).zzs();
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
        for (int i3 = 0; i3 < this.zza.zza(); i3++) {
            if (!zzc(this.zza.zzb(i3))) {
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
        zzaiz zzaiz = (zzaiz) entry.getKey();
        Object value = entry.getValue();
        boolean z2 = value instanceof zzajp;
        if (zzaiz.zze()) {
            if (!z2) {
                Object zza2 = zza(zzaiz);
                if (zza2 == null) {
                    zza2 = new ArrayList();
                }
                for (Object zza3 : (List) value) {
                    ((List) zza2).add(zza(zza3));
                }
                this.zza.put(zzaiz, zza2);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        } else if (zzaiz.zzc() == zzand.MESSAGE) {
            Object zza4 = zza(zzaiz);
            if (zza4 == null) {
                this.zza.put(zzaiz, zza(value));
                if (z2) {
                    this.zzd = true;
                    return;
                }
                return;
            }
            if (z2) {
                value = zzajp.zza();
            }
            if (zza4 instanceof zzaku) {
                obj = zzaiz.zza((zzaku) zza4, (zzaku) value);
            } else {
                obj = zzaiz.zza(((zzakp) zza4).zzr(), (zzakp) value).zzf();
            }
            this.zza.put(zzaiz, obj);
        } else if (!z2) {
            this.zza.put(zzaiz, zza(value));
        } else {
            throw new IllegalStateException("Lazy fields must be message-valued");
        }
    }

    private zzaix(zzali<T, Object> zzali) {
        this.zza = zzali;
        zze();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if ((r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajg) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r4 instanceof byte[]) == false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0020, code lost:
        if ((r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajp) == false) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzc(T r3, java.lang.Object r4) {
        /*
            com.google.android.gms.internal.firebase-auth-api.zzamt r0 = r3.zzb()
            com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza(r4)
            int[] r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaiw.zza
            com.google.android.gms.internal.firebase-auth-api.zzand r0 = r0.zzb()
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
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzakp
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajp
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x0023:
            boolean r0 = r4 instanceof java.lang.Integer
            if (r0 != 0) goto L_0x0046
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzajg
            if (r0 == 0) goto L_0x0018
            goto L_0x0046
        L_0x002c:
            boolean r0 = r4 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaho
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
            com.google.android.gms.internal.firebase-auth-api.zzamt r3 = r3.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzand r3 = r3.zzb()
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            java.lang.Object[] r3 = new java.lang.Object[]{r1, r3, r4}
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r3 = java.lang.String.format(r4, r3)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaix.zzc(com.google.android.gms.internal.firebase-auth-api.zzaiz, java.lang.Object):void");
    }

    private static int zza(zzamt zzamt, Object obj) {
        switch (zzaiw.zzb[zzamt.ordinal()]) {
            case 1:
                return zzaim.zza(((Double) obj).doubleValue());
            case 2:
                return zzaim.zza(((Float) obj).floatValue());
            case 3:
                return zzaim.zzd(((Long) obj).longValue());
            case 4:
                return zzaim.zzg(((Long) obj).longValue());
            case 5:
                return zzaim.zze(((Integer) obj).intValue());
            case 6:
                return zzaim.zzc(((Long) obj).longValue());
            case 7:
                return zzaim.zzd(((Integer) obj).intValue());
            case 8:
                return zzaim.zza(((Boolean) obj).booleanValue());
            case 9:
                return zzaim.zza((zzakp) obj);
            case 10:
                if (obj instanceof zzajp) {
                    return zzaim.zza((zzajt) (zzajp) obj);
                }
                return zzaim.zzb((zzakp) obj);
            case 11:
                if (obj instanceof zzaho) {
                    return zzaim.zza((zzaho) obj);
                }
                return zzaim.zza((String) obj);
            case 12:
                if (obj instanceof zzaho) {
                    return zzaim.zza((zzaho) obj);
                }
                return zzaim.zza((byte[]) obj);
            case 13:
                return zzaim.zzj(((Integer) obj).intValue());
            case 14:
                return zzaim.zzg(((Integer) obj).intValue());
            case 15:
                return zzaim.zze(((Long) obj).longValue());
            case 16:
                return zzaim.zzh(((Integer) obj).intValue());
            case 17:
                return zzaim.zzf(((Long) obj).longValue());
            case 18:
                if (obj instanceof zzajg) {
                    return zzaim.zzc(((zzajg) obj).zza());
                }
                return zzaim.zzc(((Integer) obj).intValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    private zzaix(boolean z2) {
        this(zzali.zza(0));
        zze();
    }

    private static <T extends zzaiz<T>> boolean zzc(Map.Entry<T, Object> entry) {
        zzaiz zzaiz = (zzaiz) entry.getKey();
        if (zzaiz.zzc() != zzand.MESSAGE) {
            return true;
        }
        if (!zzaiz.zze()) {
            return zzb(entry.getValue());
        }
        for (Object zzb2 : (List) entry.getValue()) {
            if (!zzb(zzb2)) {
                return false;
            }
        }
        return true;
    }

    private final void zzb(T t2, Object obj) {
        if (!t2.zze()) {
            zzc(t2, obj);
        } else if (obj instanceof List) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj2 = arrayList.get(i3);
                i3++;
                zzc(t2, obj2);
            }
            obj = arrayList;
        } else {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        if (obj instanceof zzajp) {
            this.zzd = true;
        }
        this.zza.put(t2, obj);
    }

    public static int zza(zzaiz<?> zzaiz, Object obj) {
        zzamt zzb2 = zzaiz.zzb();
        int zza2 = zzaiz.zza();
        if (!zzaiz.zze()) {
            return zza(zzb2, zza2, obj);
        }
        List<Object> list = (List) obj;
        int i3 = 0;
        if (!zzaiz.zzd()) {
            for (Object zza3 : list) {
                i3 += zza(zzb2, zza2, zza3);
            }
            return i3;
        } else if (list.isEmpty()) {
            return 0;
        } else {
            for (Object zza4 : list) {
                i3 += zza(zzb2, zza4);
            }
            return zzaim.zzj(i3) + zzaim.zzi(zza2) + i3;
        }
    }

    private static boolean zzb(Object obj) {
        if (obj instanceof zzakr) {
            return ((zzakr) obj).zzk();
        }
        if (obj instanceof zzajp) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    public final int zza() {
        int i3 = 0;
        for (int i4 = 0; i4 < this.zza.zza(); i4++) {
            i3 += zza(this.zza.zzb(i4));
        }
        for (Map.Entry<T, Object> zza2 : this.zza.zzb()) {
            i3 += zza(zza2);
        }
        return i3;
    }

    private static int zza(Map.Entry<T, Object> entry) {
        zzaiz zzaiz = (zzaiz) entry.getKey();
        Object value = entry.getValue();
        if (zzaiz.zzc() != zzand.MESSAGE || zzaiz.zze() || zzaiz.zzd()) {
            return zza((zzaiz<?>) zzaiz, value);
        }
        if (value instanceof zzajp) {
            return zzaim.zza(((zzaiz) entry.getKey()).zza(), (zzajt) (zzajp) value);
        }
        return zzaim.zza(((zzaiz) entry.getKey()).zza(), (zzakp) value);
    }

    private static Object zza(Object obj) {
        if (obj instanceof zzaku) {
            return ((zzaku) obj).zza();
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
        return obj instanceof zzajp ? zzajp.zza() : obj;
    }

    public final void zza(zzaix<T> zzaix) {
        for (int i3 = 0; i3 < zzaix.zza.zza(); i3++) {
            zzb(zzaix.zza.zzb(i3));
        }
        for (Map.Entry<T, Object> zzb2 : zzaix.zza.zzb()) {
            zzb(zzb2);
        }
    }

    public static void zza(zzaim zzaim, zzamt zzamt, int i3, Object obj) throws IOException {
        if (zzamt == zzamt.GROUP) {
            zzakp zzakp = (zzakp) obj;
            zzajh.zza(zzakp);
            zzaim.zzk(i3, 3);
            zzakp.zza(zzaim);
            zzaim.zzk(i3, 4);
            return;
        }
        zzaim.zzk(i3, zzamt.zza());
        switch (zzaiw.zzb[zzamt.ordinal()]) {
            case 1:
                zzaim.zzb(((Double) obj).doubleValue());
                return;
            case 2:
                zzaim.zzb(((Float) obj).floatValue());
                return;
            case 3:
                zzaim.zzj(((Long) obj).longValue());
                return;
            case 4:
                zzaim.zzj(((Long) obj).longValue());
                return;
            case 5:
                zzaim.zzl(((Integer) obj).intValue());
                return;
            case 6:
                zzaim.zzh(((Long) obj).longValue());
                return;
            case 7:
                zzaim.zzk(((Integer) obj).intValue());
                return;
            case 8:
                zzaim.zzb(((Boolean) obj).booleanValue());
                return;
            case 9:
                ((zzakp) obj).zza(zzaim);
                return;
            case 10:
                zzaim.zzc((zzakp) obj);
                return;
            case 11:
                if (obj instanceof zzaho) {
                    zzaim.zzb((zzaho) obj);
                    return;
                } else {
                    zzaim.zzb((String) obj);
                    return;
                }
            case 12:
                if (obj instanceof zzaho) {
                    zzaim.zzb((zzaho) obj);
                    return;
                }
                byte[] bArr = (byte[]) obj;
                zzaim.zzb(bArr, 0, bArr.length);
                return;
            case 13:
                zzaim.zzn(((Integer) obj).intValue());
                return;
            case 14:
                zzaim.zzk(((Integer) obj).intValue());
                return;
            case 15:
                zzaim.zzh(((Long) obj).longValue());
                return;
            case 16:
                zzaim.zzm(((Integer) obj).intValue());
                return;
            case 17:
                zzaim.zzi(((Long) obj).longValue());
                return;
            case 18:
                if (obj instanceof zzajg) {
                    zzaim.zzl(((zzajg) obj).zza());
                    return;
                } else {
                    zzaim.zzl(((Integer) obj).intValue());
                    return;
                }
            default:
                return;
        }
    }
}
