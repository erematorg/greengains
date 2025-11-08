package com.google.android.gms.internal.auth;

import com.google.android.gms.internal.auth.zzet;
import com.google.android.gms.internal.auth.zzev;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzev<MessageType extends zzev<MessageType, BuilderType>, BuilderType extends zzet<MessageType, BuilderType>> extends zzdq<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    protected zzha zzc = zzha.zza();
    private int zzd = -1;

    public static zzev zzb(Class cls) {
        Map map = zzb;
        zzev zzev = (zzev) map.get(cls);
        if (zzev == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzev = (zzev) map.get(cls);
            } catch (ClassNotFoundException e3) {
                throw new IllegalStateException("Class initialization cannot fail.", e3);
            }
        }
        if (zzev == null) {
            zzev = (zzev) ((zzev) zzhj.zze(cls)).zzn(6, (Object) null, (Object) null);
            if (zzev != null) {
                map.put(cls, zzev);
            } else {
                throw new IllegalStateException();
            }
        }
        return zzev;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        if (r1 != false) goto L_0x0044;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.auth.zzev zzd(com.google.android.gms.internal.auth.zzev r3, byte[] r4) throws com.google.android.gms.internal.auth.zzfb {
        /*
            int r0 = r4.length
            com.google.android.gms.internal.auth.zzel r1 = com.google.android.gms.internal.auth.zzel.zza
            r2 = 0
            com.google.android.gms.internal.auth.zzev r3 = zzo(r3, r4, r2, r0, r1)
            if (r3 == 0) goto L_0x0044
            r4 = 1
            r0 = 0
            java.lang.Object r1 = r3.zzn(r4, r0, r0)
            java.lang.Byte r1 = (java.lang.Byte) r1
            byte r1 = r1.byteValue()
            if (r1 != r4) goto L_0x0019
            goto L_0x0044
        L_0x0019:
            if (r1 == 0) goto L_0x0037
            java.lang.Class r1 = r3.getClass()
            com.google.android.gms.internal.auth.zzgf r2 = com.google.android.gms.internal.auth.zzgf.zza()
            com.google.android.gms.internal.auth.zzgi r1 = r2.zzb(r1)
            boolean r1 = r1.zzi(r3)
            if (r4 == r1) goto L_0x002f
            r4 = r0
            goto L_0x0030
        L_0x002f:
            r4 = r3
        L_0x0030:
            r2 = 2
            r3.zzn(r2, r4, r0)
            if (r1 == 0) goto L_0x0037
            goto L_0x0044
        L_0x0037:
            com.google.android.gms.internal.auth.zzgy r4 = new com.google.android.gms.internal.auth.zzgy
            r4.<init>(r3)
            com.google.android.gms.internal.auth.zzfb r4 = r4.zza()
            r4.zze(r3)
            throw r4
        L_0x0044:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzev.zzd(com.google.android.gms.internal.auth.zzev, byte[]):com.google.android.gms.internal.auth.zzev");
    }

    public static zzez zzf() {
        return zzgg.zze();
    }

    public static Object zzg(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    public static Object zzh(zzfx zzfx, String str, Object[] objArr) {
        return new zzgh(zzfx, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", objArr);
    }

    public static void zzk(Class cls, zzev zzev) {
        zzev.zzj();
        zzb.put(cls, zzev);
    }

    private static zzev zzo(zzev zzev, byte[] bArr, int i3, int i4, zzel zzel) throws zzfb {
        zzev zzc2 = zzev.zzc();
        try {
            zzgi zzb2 = zzgf.zza().zzb(zzc2.getClass());
            zzb2.zzg(zzc2, bArr, 0, i4, new zzdt(zzel));
            zzb2.zze(zzc2);
            return zzc2;
        } catch (zzfb e3) {
            e3.zze(zzc2);
            throw e3;
        } catch (zzgy e4) {
            zzfb zza = e4.zza();
            zza.zze(zzc2);
            throw zza;
        } catch (IOException e5) {
            if (e5.getCause() instanceof zzfb) {
                throw ((zzfb) e5.getCause());
            }
            zzfb zzfb = new zzfb(e5);
            zzfb.zze(zzc2);
            throw zzfb;
        } catch (IndexOutOfBoundsException unused) {
            zzfb zzf = zzfb.zzf();
            zzf.zze(zzc2);
            throw zzf;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzgf.zza().zzb(getClass()).zzh(this, (zzev) obj);
    }

    public final int hashCode() {
        if (zzm()) {
            return zza();
        }
        int i3 = this.zza;
        if (i3 != 0) {
            return i3;
        }
        int zza = zza();
        this.zza = zza;
        return zza;
    }

    public final String toString() {
        return zzfz.zza(this, super.toString());
    }

    public final int zza() {
        return zzgf.zza().zzb(getClass()).zza(this);
    }

    public final zzev zzc() {
        return (zzev) zzn(4, (Object) null, (Object) null);
    }

    public final /* synthetic */ zzfx zze() {
        return (zzev) zzn(6, (Object) null, (Object) null);
    }

    public final void zzi() {
        zzgf.zza().zzb(getClass()).zze(this);
        zzj();
    }

    public final void zzj() {
        this.zzd &= Integer.MAX_VALUE;
    }

    public final void zzl(int i3) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    public final boolean zzm() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    public abstract Object zzn(int i3, Object obj, Object obj2);
}
