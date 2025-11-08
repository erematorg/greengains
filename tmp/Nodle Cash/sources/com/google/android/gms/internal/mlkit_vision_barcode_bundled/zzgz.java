package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import libcore.io.Memory;
import org.web3j.abi.datatypes.Address;
import sun.misc.Unsafe;

final class zzgz {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = Memory.class;
    private static final boolean zze;
    private static final zzgy zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011a  */
    static {
        /*
            java.lang.Class<java.lang.Class> r0 = java.lang.Class.class
            sun.misc.Unsafe r1 = zzg()
            zzc = r1
            int r2 = com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzct.zza
            java.lang.Class<libcore.io.Memory> r2 = libcore.io.Memory.class
            zzd = r2
            java.lang.Class r2 = java.lang.Long.TYPE
            boolean r3 = zzv(r2)
            zze = r3
            java.lang.Class r4 = java.lang.Integer.TYPE
            boolean r4 = zzv(r4)
            r5 = 0
            if (r1 != 0) goto L_0x0020
            goto L_0x002f
        L_0x0020:
            if (r3 == 0) goto L_0x0028
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgx r5 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgx
            r5.<init>(r1)
            goto L_0x002f
        L_0x0028:
            if (r4 == 0) goto L_0x002f
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgw r5 = new com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgw
            r5.<init>(r1)
        L_0x002f:
            zzf = r5
            java.lang.String r1 = "getLong"
            java.lang.Class<java.lang.reflect.Field> r3 = java.lang.reflect.Field.class
            java.lang.String r4 = "objectFieldOffset"
            r6 = 1
            r7 = 0
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            if (r5 != 0) goto L_0x003f
        L_0x003d:
            r2 = r7
            goto L_0x0061
        L_0x003f:
            sun.misc.Unsafe r5 = r5.zza
            java.lang.Class r5 = r5.getClass()     // Catch:{ all -> 0x005c }
            java.lang.Class[] r9 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x005c }
            r5.getMethod(r4, r9)     // Catch:{ all -> 0x005c }
            java.lang.Class[] r2 = new java.lang.Class[]{r8, r2}     // Catch:{ all -> 0x005c }
            r5.getMethod(r1, r2)     // Catch:{ all -> 0x005c }
            java.lang.reflect.Field r2 = zzB()     // Catch:{ all -> 0x005c }
            if (r2 != 0) goto L_0x005a
            goto L_0x003d
        L_0x005a:
            r2 = r6
            goto L_0x0061
        L_0x005c:
            r2 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r2.toString()))
            goto L_0x003d
        L_0x0061:
            zzg = r2
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r2 = zzf
            if (r2 != 0) goto L_0x0069
        L_0x0067:
            r0 = r7
            goto L_0x00c7
        L_0x0069:
            sun.misc.Unsafe r2 = r2.zza
            java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r3 = new java.lang.Class[]{r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r4, r3)     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "arrayBaseOffset"
            java.lang.Class[] r4 = new java.lang.Class[]{r0}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r3, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.String r3 = "arrayIndexScale"
            java.lang.Class[] r0 = new java.lang.Class[]{r0}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r3, r0)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "getInt"
            java.lang.Class r3 = java.lang.Long.TYPE     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r4 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putInt"
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r4 = new java.lang.Class[]{r8, r3, r4}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r4)     // Catch:{ all -> 0x00c2 }
            java.lang.Class[] r0 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r1, r0)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putLong"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "getObject"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            java.lang.String r0 = "putObject"
            java.lang.Class[] r1 = new java.lang.Class[]{r8, r3, r8}     // Catch:{ all -> 0x00c2 }
            r2.getMethod(r0, r1)     // Catch:{ all -> 0x00c2 }
            r0 = r6
            goto L_0x00c7
        L_0x00c2:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x0067
        L_0x00c7:
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzz(r0)
            long r0 = (long) r0
            zza = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzz(r0)
            zzA(r0)
            java.lang.reflect.Field r0 = zzB()
            if (r0 == 0) goto L_0x0111
            com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgy r1 = zzf
            if (r1 == 0) goto L_0x0111
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r0)
        L_0x0111:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x011a
            goto L_0x011b
        L_0x011a:
            r6 = r7
        L_0x011b:
            zzb = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzgz.<clinit>():void");
    }

    private zzgz() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i3 = zzct.zza;
        Class<Buffer> cls = Buffer.class;
        Field zzC = zzC(cls, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(cls, Address.TYPE_NAME);
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j2, byte b3) {
        zzgy zzgy = zzf;
        long j3 = -4 & j2;
        int i3 = zzgy.zza.getInt(obj, j3);
        int i4 = ((~((int) j2)) & 3) << 3;
        zzgy.zza.putInt(obj, j3, ((255 & b3) << i4) | (i3 & (~(255 << i4))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j2, byte b3) {
        zzgy zzgy = zzf;
        long j3 = -4 & j2;
        int i3 = (((int) j2) & 3) << 3;
        zzgy.zza.putInt(obj, j3, ((255 & b3) << i3) | (zzgy.zza.getInt(obj, j3) & (~(255 << i3))));
    }

    public static double zza(Object obj, long j2) {
        return zzf.zza(obj, j2);
    }

    public static float zzb(Object obj, long j2) {
        return zzf.zzb(obj, j2);
    }

    public static int zzc(Object obj, long j2) {
        return zzf.zza.getInt(obj, j2);
    }

    public static long zzd(Object obj, long j2) {
        return zzf.zza.getLong(obj, j2);
    }

    public static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e3) {
            throw new IllegalStateException(e3);
        }
    }

    public static Object zzf(Object obj, long j2) {
        return zzf.zza.getObject(obj, j2);
    }

    public static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzgv());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzm(Object obj, long j2, boolean z2) {
        zzf.zzc(obj, j2, z2);
    }

    public static void zzn(byte[] bArr, long j2, byte b3) {
        zzf.zzd(bArr, zza + j2, b3);
    }

    public static void zzo(Object obj, long j2, double d2) {
        zzf.zze(obj, j2, d2);
    }

    public static void zzp(Object obj, long j2, float f2) {
        zzf.zzf(obj, j2, f2);
    }

    public static void zzq(Object obj, long j2, int i3) {
        zzf.zza.putInt(obj, j2, i3);
    }

    public static void zzr(Object obj, long j2, long j3) {
        zzf.zza.putLong(obj, j2, j3);
    }

    public static void zzs(Object obj, long j2, Object obj2) {
        zzf.zza.putObject(obj, j2, obj2);
    }

    public static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j2) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j2) >>> ((int) (((~j2) & 3) << 3))) & 255)) != 0;
    }

    public static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j2) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j2) >>> ((int) ((j2 & 3) << 3))) & 255)) != 0;
    }

    public static boolean zzv(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i3 = zzct.zza;
        try {
            Class cls3 = zzd;
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

    public static boolean zzw(Object obj, long j2) {
        return zzf.zzg(obj, j2);
    }

    public static boolean zzx() {
        return zzh;
    }

    public static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
