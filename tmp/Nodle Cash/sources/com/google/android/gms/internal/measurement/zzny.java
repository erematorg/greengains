package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.web3j.abi.datatypes.Address;
import sun.misc.Unsafe;

final class zzny {
    static final boolean zza;
    private static final Unsafe zzb;
    private static final Class<?> zzc = zzjl.zza();
    private static final boolean zzd;
    private static final boolean zze;
    private static final zzb zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi = ((long) zzb(byte[].class));

    public static final class zza extends zzb {
        public zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final double zza(Object obj, long j2) {
            return Double.longBitsToDouble(zze(obj, j2));
        }

        public final float zzb(Object obj, long j2) {
            return Float.intBitsToFloat(zzd(obj, j2));
        }

        public final boolean zzc(Object obj, long j2) {
            return zzny.zza ? zzny.zzf(obj, j2) : zzny.zzg(obj, j2);
        }

        public final void zza(Object obj, long j2, boolean z2) {
            if (zzny.zza) {
                zzny.zzc(obj, j2, r3 ? (byte) 1 : 0);
            } else {
                zzny.zzd(obj, j2, r3 ? (byte) 1 : 0);
            }
        }

        public final void zza(Object obj, long j2, byte b3) {
            if (zzny.zza) {
                zzny.zzc(obj, j2, b3);
            } else {
                zzny.zzd(obj, j2, b3);
            }
        }

        public final void zza(Object obj, long j2, double d2) {
            zza(obj, j2, Double.doubleToLongBits(d2));
        }

        public final void zza(Object obj, long j2, float f2) {
            zza(obj, j2, Float.floatToIntBits(f2));
        }
    }

    public static abstract class zzb {
        Unsafe zza;

        public zzb(Unsafe unsafe) {
            this.zza = unsafe;
        }

        public abstract double zza(Object obj, long j2);

        public abstract void zza(Object obj, long j2, byte b3);

        public abstract void zza(Object obj, long j2, double d2);

        public abstract void zza(Object obj, long j2, float f2);

        public final void zza(Object obj, long j2, int i3) {
            this.zza.putInt(obj, j2, i3);
        }

        public abstract void zza(Object obj, long j2, boolean z2);

        public abstract float zzb(Object obj, long j2);

        public final boolean zzb() {
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
                if (zzny.zze() == null) {
                    return false;
                }
                return true;
            } catch (Throwable th) {
                Logger.getLogger(zzny.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(String.valueOf(th)));
                return false;
            }
        }

        public abstract boolean zzc(Object obj, long j2);

        public final int zzd(Object obj, long j2) {
            return this.zza.getInt(obj, j2);
        }

        public final long zze(Object obj, long j2) {
            return this.zza.getLong(obj, j2);
        }

        public final void zza(Object obj, long j2, long j3) {
            this.zza.putLong(obj, j2, j3);
        }

        public final boolean zza() {
            Class<Class> cls = Class.class;
            Class<Object> cls2 = Object.class;
            Unsafe unsafe = this.zza;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls3 = unsafe.getClass();
                cls3.getMethod("objectFieldOffset", new Class[]{Field.class});
                cls3.getMethod("arrayBaseOffset", new Class[]{cls});
                cls3.getMethod("arrayIndexScale", new Class[]{cls});
                Class cls4 = Long.TYPE;
                cls3.getMethod("getInt", new Class[]{cls2, cls4});
                cls3.getMethod("putInt", new Class[]{cls2, cls4, Integer.TYPE});
                cls3.getMethod("getLong", new Class[]{cls2, cls4});
                cls3.getMethod("putLong", new Class[]{cls2, cls4, cls4});
                cls3.getMethod("getObject", new Class[]{cls2, cls4});
                cls3.getMethod("putObject", new Class[]{cls2, cls4, cls2});
                return true;
            } catch (Throwable th) {
                Logger.getLogger(zzny.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(String.valueOf(th)));
                return false;
            }
        }
    }

    public static final class zzc extends zzb {
        public zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final double zza(Object obj, long j2) {
            return Double.longBitsToDouble(zze(obj, j2));
        }

        public final float zzb(Object obj, long j2) {
            return Float.intBitsToFloat(zzd(obj, j2));
        }

        public final boolean zzc(Object obj, long j2) {
            return zzny.zza ? zzny.zzf(obj, j2) : zzny.zzg(obj, j2);
        }

        public final void zza(Object obj, long j2, boolean z2) {
            if (zzny.zza) {
                zzny.zzc(obj, j2, r3 ? (byte) 1 : 0);
            } else {
                zzny.zzd(obj, j2, r3 ? (byte) 1 : 0);
            }
        }

        public final void zza(Object obj, long j2, byte b3) {
            if (zzny.zza) {
                zzny.zzc(obj, j2, b3);
            } else {
                zzny.zzd(obj, j2, b3);
            }
        }

        public final void zza(Object obj, long j2, double d2) {
            zza(obj, j2, Double.doubleToLongBits(d2));
        }

        public final void zza(Object obj, long j2, float f2) {
            zza(obj, j2, Float.floatToIntBits(f2));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0034  */
    static {
        /*
            sun.misc.Unsafe r0 = zzb()
            zzb = r0
            java.lang.Class r1 = com.google.android.gms.internal.measurement.zzjl.zza()
            zzc = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzd(r1)
            zzd = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzd(r2)
            zze = r2
            if (r0 == 0) goto L_0x002e
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.measurement.zzny$zzc r1 = new com.google.android.gms.internal.measurement.zzny$zzc
            r1.<init>(r0)
            goto L_0x002f
        L_0x0026:
            if (r2 == 0) goto L_0x002e
            com.google.android.gms.internal.measurement.zzny$zza r1 = new com.google.android.gms.internal.measurement.zzny$zza
            r1.<init>(r0)
            goto L_0x002f
        L_0x002e:
            r1 = 0
        L_0x002f:
            zzf = r1
            r0 = 0
            if (r1 != 0) goto L_0x0036
            r2 = r0
            goto L_0x003a
        L_0x0036:
            boolean r2 = r1.zzb()
        L_0x003a:
            zzg = r2
            if (r1 != 0) goto L_0x0040
            r2 = r0
            goto L_0x0044
        L_0x0040:
            boolean r2 = r1.zza()
        L_0x0044:
            zzh = r2
            java.lang.Class<byte[]> r2 = byte[].class
            int r2 = zzb(r2)
            long r2 = (long) r2
            zzi = r2
            java.lang.Class<boolean[]> r2 = boolean[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<int[]> r2 = int[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<long[]> r2 = long[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<float[]> r2 = float[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<double[]> r2 = double[].class
            zzb(r2)
            zzc(r2)
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            zzb(r2)
            zzc(r2)
            java.lang.reflect.Field r2 = zze()
            if (r2 == 0) goto L_0x008d
            if (r1 != 0) goto L_0x0088
            goto L_0x008d
        L_0x0088:
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r2)
        L_0x008d:
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            if (r1 != r2) goto L_0x0096
            r0 = 1
        L_0x0096:
            zza = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzny.<clinit>():void");
    }

    private zzny() {
    }

    private static int zzc(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    public static long zzd(Object obj, long j2) {
        return zzf.zze(obj, j2);
    }

    public static Object zze(Object obj, long j2) {
        return zzf.zza.getObject(obj, j2);
    }

    public static /* synthetic */ boolean zzf(Object obj, long j2) {
        return ((byte) (zzc(obj, -4 & j2) >>> ((int) (((~j2) & 3) << 3)))) != 0;
    }

    public static /* synthetic */ boolean zzg(Object obj, long j2) {
        return ((byte) (zzc(obj, -4 & j2) >>> ((int) ((j2 & 3) << 3)))) != 0;
    }

    public static boolean zzh(Object obj, long j2) {
        return zzf.zzc(obj, j2);
    }

    public static float zzb(Object obj, long j2) {
        return zzf.zzb(obj, j2);
    }

    /* access modifiers changed from: private */
    public static void zzd(Object obj, long j2, byte b3) {
        long j3 = -4 & j2;
        int i3 = (((int) j2) & 3) << 3;
        zza(obj, j3, ((255 & b3) << i3) | (zzc(obj, j3) & (~(255 << i3))));
    }

    public static double zza(Object obj, long j2) {
        return zzf.zza(obj, j2);
    }

    private static int zzb(Class<?> cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static int zzc(Object obj, long j2) {
        return zzf.zzd(obj, j2);
    }

    /* access modifiers changed from: private */
    public static Field zze() {
        Class<Buffer> cls = Buffer.class;
        Field zza2 = zza((Class<?>) cls, "effectiveDirectAddress");
        if (zza2 != null) {
            return zza2;
        }
        Field zza3 = zza((Class<?>) cls, Address.TYPE_NAME);
        if (zza3 == null || zza3.getType() != Long.TYPE) {
            return null;
        }
        return zza3;
    }

    public static <T> T zza(Class<T> cls) {
        try {
            return zzb.allocateInstance(cls);
        } catch (InstantiationException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public static void zzc(Object obj, long j2, boolean z2) {
        zzf.zza(obj, j2, z2);
    }

    private static boolean zzd(Class<?> cls) {
        Class<byte[]> cls2 = byte[].class;
        try {
            Class<?> cls3 = zzc;
            Class cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", new Class[]{cls, cls4});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", new Class[]{cls, cls5, cls4});
            cls3.getMethod("peekInt", new Class[]{cls, cls4});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls5, cls5});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static Unsafe zzb() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzoa());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j2, byte b3) {
        long j3 = -4 & j2;
        int zzc2 = zzc(obj, j3);
        int i3 = ((~((int) j2)) & 3) << 3;
        zza(obj, j3, ((255 & b3) << i3) | (zzc2 & (~(255 << i3))));
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean zzc() {
        return zzh;
    }

    public static void zza(byte[] bArr, long j2, byte b3) {
        zzf.zza((Object) bArr, zzi + j2, b3);
    }

    public static void zza(Object obj, long j2, double d2) {
        zzf.zza(obj, j2, d2);
    }

    public static void zza(Object obj, long j2, float f2) {
        zzf.zza(obj, j2, f2);
    }

    public static void zza(Object obj, long j2, int i3) {
        zzf.zza(obj, j2, i3);
    }

    public static boolean zzd() {
        return zzg;
    }

    public static void zza(Object obj, long j2, long j3) {
        zzf.zza(obj, j2, j3);
    }

    public static void zza(Object obj, long j2, Object obj2) {
        zzf.zza.putObject(obj, j2, obj2);
    }
}
