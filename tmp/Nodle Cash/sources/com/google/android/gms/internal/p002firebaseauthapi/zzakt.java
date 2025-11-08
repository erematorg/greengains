package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.camera.camera2.internal.C0118y;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakt  reason: invalid package */
final class zzakt<T> implements zzalh<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzamm.zzb();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzakp zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final zzala zzj;
    private final boolean zzk;
    private final int[] zzl;
    private final int zzm;
    private final int zzn;
    private final zzakx zzo;
    private final zzaju zzp;
    private final zzamg<?, ?> zzq;
    private final zzais<?> zzr;
    private final zzaki zzs;

    private zzakt(int[] iArr, Object[] objArr, int i3, int i4, zzakp zzakp, zzala zzala, boolean z2, int[] iArr2, int i5, int i6, zzakx zzakx, zzaju zzaju, zzamg<?, ?> zzamg, zzais<?> zzais, zzaki zzaki) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zze = i3;
        this.zzf = i4;
        this.zzi = zzakp instanceof zzaje;
        this.zzj = zzala;
        this.zzh = zzais != null && zzais.zza(zzakp);
        this.zzk = false;
        this.zzl = iArr2;
        this.zzm = i5;
        this.zzn = i6;
        this.zzo = zzakx;
        this.zzp = zzaju;
        this.zzq = zzamg;
        this.zzr = zzais;
        this.zzg = zzakp;
        this.zzs = zzaki;
    }

    private static <T> double zza(T t2, long j2) {
        return ((Double) zzamm.zze(t2, j2)).doubleValue();
    }

    private static <T> float zzb(T t2, long j2) {
        return ((Float) zzamm.zze(t2, j2)).floatValue();
    }

    private static <T> int zzc(T t2, long j2) {
        return ((Integer) zzamm.zze(t2, j2)).intValue();
    }

    private static <T> long zzd(T t2, long j2) {
        return ((Long) zzamm.zze(t2, j2)).longValue();
    }

    private final zzalh zze(int i3) {
        int i4 = (i3 / 3) << 1;
        zzalh zzalh = (zzalh) this.zzd[i4];
        if (zzalh != null) {
            return zzalh;
        }
        zzalh zza2 = zzald.zza().zza((Class) this.zzd[i4 + 1]);
        this.zzd[i4] = zza2;
        return zza2;
    }

    private final Object zzf(int i3) {
        return this.zzd[(i3 / 3) << 1];
    }

    private static boolean zzg(int i3) {
        return (i3 & 536870912) != 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.p002firebaseauthapi.zzamt r4, java.lang.Class<?> r5, com.google.android.gms.internal.p002firebaseauthapi.zzahn r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaks.zza
            int r4 = r4.ordinal()
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0099;
                case 2: goto L_0x0094;
                case 3: goto L_0x0089;
                case 4: goto L_0x007e;
                case 5: goto L_0x007e;
                case 6: goto L_0x0071;
                case 7: goto L_0x0071;
                case 8: goto L_0x0064;
                case 9: goto L_0x0057;
                case 10: goto L_0x0057;
                case 11: goto L_0x0057;
                case 12: goto L_0x004a;
                case 13: goto L_0x004a;
                case 14: goto L_0x003d;
                case 15: goto L_0x002b;
                case 16: goto L_0x0019;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "unsupported field type."
            r1.<init>(r2)
            throw r1
        L_0x0013:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r1, r2, r6)
            goto L_0x00ae
        L_0x0019:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r1, r2, r6)
            long r2 = r6.zzb
            long r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzc = r2
            goto L_0x00ae
        L_0x002b:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r1, r2, r6)
            int r2 = r6.zza
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzc = r2
            goto L_0x00ae
        L_0x003d:
            com.google.android.gms.internal.firebase-auth-api.zzald r4 = com.google.android.gms.internal.p002firebaseauthapi.zzald.zza()
            com.google.android.gms.internal.firebase-auth-api.zzalh r4 = r4.zza(r5)
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((com.google.android.gms.internal.p002firebaseauthapi.zzalh) r4, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r6)
            goto L_0x00ae
        L_0x004a:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r1, r2, r6)
            long r2 = r6.zzb
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zzc = r2
            goto L_0x00ae
        L_0x0057:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r1, r2, r6)
            int r2 = r6.zza
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zzc = r2
            goto L_0x00ae
        L_0x0064:
            float r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r6.zzc = r1
        L_0x006e:
            int r1 = r2 + 4
            goto L_0x00ae
        L_0x0071:
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r6.zzc = r1
        L_0x007b:
            int r1 = r2 + 8
            goto L_0x00ae
        L_0x007e:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.zzc = r1
            goto L_0x006e
        L_0x0089:
            double r3 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
            r6.zzc = r1
            goto L_0x007b
        L_0x0094:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r1, r2, r6)
            goto L_0x00ae
        L_0x0099:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r1, r2, r6)
            long r2 = r6.zzb
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 == 0) goto L_0x00a7
            r2 = 1
            goto L_0x00a8
        L_0x00a7:
            r2 = 0
        L_0x00a8:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r6.zzc = r2
        L_0x00ae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(byte[], int, int, com.google.android.gms.internal.firebase-auth-api.zzamt, java.lang.Class, com.google.android.gms.internal.firebase-auth-api.zzahn):int");
    }

    private final int zzc(int i3) {
        return this.zzc[i3 + 1];
    }

    private final zzaji zzd(int i3) {
        return (zzaji) this.zzd[((i3 / 3) << 1) + 1];
    }

    private static void zzf(Object obj) {
        if (!zzg(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(obj)));
        }
    }

    private static boolean zzg(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzaje) {
            return ((zzaje) obj).zzu();
        }
        return true;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0051, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0170, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0030, code lost:
        r2 = r3 + r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x022e, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzb(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x0232
            int r3 = r8.zzc((int) r1)
            int[] r4 = r8.zzc
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x021e;
                case 1: goto L_0x0212;
                case 2: goto L_0x0206;
                case 3: goto L_0x01fa;
                case 4: goto L_0x01f2;
                case 5: goto L_0x01e6;
                case 6: goto L_0x01de;
                case 7: goto L_0x01d2;
                case 8: goto L_0x01c4;
                case 9: goto L_0x01b9;
                case 10: goto L_0x01ad;
                case 11: goto L_0x01a5;
                case 12: goto L_0x019d;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0181;
                case 16: goto L_0x0175;
                case 17: goto L_0x0166;
                case 18: goto L_0x015a;
                case 19: goto L_0x015a;
                case 20: goto L_0x015a;
                case 21: goto L_0x015a;
                case 22: goto L_0x015a;
                case 23: goto L_0x015a;
                case 24: goto L_0x015a;
                case 25: goto L_0x015a;
                case 26: goto L_0x015a;
                case 27: goto L_0x015a;
                case 28: goto L_0x015a;
                case 29: goto L_0x015a;
                case 30: goto L_0x015a;
                case 31: goto L_0x015a;
                case 32: goto L_0x015a;
                case 33: goto L_0x015a;
                case 34: goto L_0x015a;
                case 35: goto L_0x015a;
                case 36: goto L_0x015a;
                case 37: goto L_0x015a;
                case 38: goto L_0x015a;
                case 39: goto L_0x015a;
                case 40: goto L_0x015a;
                case 41: goto L_0x015a;
                case 42: goto L_0x015a;
                case 43: goto L_0x015a;
                case 44: goto L_0x015a;
                case 45: goto L_0x015a;
                case 46: goto L_0x015a;
                case 47: goto L_0x015a;
                case 48: goto L_0x015a;
                case 49: goto L_0x015a;
                case 50: goto L_0x014e;
                case 51: goto L_0x0138;
                case 52: goto L_0x0126;
                case 53: goto L_0x0114;
                case 54: goto L_0x0102;
                case 55: goto L_0x00f4;
                case 56: goto L_0x00e2;
                case 57: goto L_0x00d4;
                case 58: goto L_0x00c2;
                case 59: goto L_0x00ae;
                case 60: goto L_0x009d;
                case 61: goto L_0x008c;
                case 62: goto L_0x007f;
                case 63: goto L_0x0072;
                case 64: goto L_0x0065;
                case 65: goto L_0x0054;
                case 66: goto L_0x0045;
                case 67: goto L_0x0034;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x022e
        L_0x0020:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
        L_0x0030:
            int r3 = r3 + r2
            r2 = r3
            goto L_0x022e
        L_0x0034:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            long r3 = zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0045:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
        L_0x0051:
            int r2 = r2 + r3
            goto L_0x022e
        L_0x0054:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            long r3 = zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0065:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
            goto L_0x0051
        L_0x0072:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
            goto L_0x0051
        L_0x007f:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
            goto L_0x0051
        L_0x008c:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x009d:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x00ae:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x00c2:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            boolean r3 = zze(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((boolean) r3)
            goto L_0x0030
        L_0x00d4:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
            goto L_0x0051
        L_0x00e2:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            long r3 = zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x00f4:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            int r3 = zzc(r9, (long) r5)
            goto L_0x0051
        L_0x0102:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            long r3 = zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0114:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            long r3 = zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0126:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            float r3 = zzb(r9, (long) r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0030
        L_0x0138:
            boolean r3 = r8.zzc(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x022e
            int r2 = r2 * 53
            double r3 = zza(r9, (long) r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x014e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x015a:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x0166:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            if (r3 == 0) goto L_0x0170
            int r7 = r3.hashCode()
        L_0x0170:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x022e
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            if (r3 == 0) goto L_0x0170
            int r7 = r3.hashCode()
            goto L_0x0170
        L_0x01c4:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0030
        L_0x01d2:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((boolean) r3)
            goto L_0x0030
        L_0x01de:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x01f2:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r9, r5)
            goto L_0x0051
        L_0x01fa:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0206:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r9, r5)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x0212:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0030
        L_0x021e:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza((long) r3)
            goto L_0x0030
        L_0x022e:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x0232:
            int r2 = r2 * 53
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r8.zzq
            java.lang.Object r0 = r0.zzd(r9)
            int r0 = r0.hashCode()
            int r0 = r0 + r2
            boolean r1 = r8.zzh
            if (r1 == 0) goto L_0x0250
            int r0 = r0 * 53
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r8 = r8.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r8 = r8.zza((java.lang.Object) r9)
            int r8 = r8.hashCode()
            int r0 = r0 + r8
        L_0x0250:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zzb(java.lang.Object):int");
    }

    public final void zzc(T t2) {
        if (zzg((Object) t2)) {
            if (t2 instanceof zzaje) {
                zzaje zzaje = (zzaje) t2;
                zzaje.zzb(Integer.MAX_VALUE);
                zzaje.zza = 0;
                zzaje.zzt();
            }
            int length = this.zzc.length;
            for (int i3 = 0; i3 < length; i3 += 3) {
                int zzc2 = zzc(i3);
                long j2 = (long) (1048575 & zzc2);
                int i4 = (zzc2 & 267386880) >>> 20;
                if (i4 != 9) {
                    if (i4 == 60 || i4 == 68) {
                        if (zzc(t2, this.zzc[i3], i3)) {
                            zze(i3).zzc(zzb.getObject(t2, j2));
                        }
                    } else {
                        switch (i4) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzp.zzb(t2, j2);
                                continue;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(t2, j2);
                                if (object != null) {
                                    unsafe.putObject(t2, j2, this.zzs.zzc(object));
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (zzc(t2, i3)) {
                    zze(i3).zzc(zzb.getObject(t2, j2));
                }
            }
            this.zzq.zzf(t2);
            if (this.zzh) {
                this.zzr.zzc(t2);
            }
        }
    }

    public final boolean zzd(T t2) {
        int i3;
        int i4;
        T t3 = t2;
        int i5 = 1048575;
        int i6 = 0;
        int i7 = 0;
        while (i7 < this.zzm) {
            int i8 = this.zzl[i7];
            int i9 = this.zzc[i8];
            int zzc2 = zzc(i8);
            int i10 = this.zzc[i8 + 2];
            int i11 = i10 & 1048575;
            int i12 = 1 << (i10 >>> 20);
            if (i11 != i5) {
                if (i11 != 1048575) {
                    i6 = zzb.getInt(t3, (long) i11);
                }
                i3 = i6;
                i4 = i11;
            } else {
                i4 = i5;
                i3 = i6;
            }
            if ((268435456 & zzc2) != 0 && !zza(t2, i8, i4, i3, i12)) {
                return false;
            }
            int i13 = (267386880 & zzc2) >>> 20;
            if (i13 != 9 && i13 != 17) {
                if (i13 != 27) {
                    if (i13 == 60 || i13 == 68) {
                        if (zzc(t3, i9, i8) && !zza((Object) t3, zzc2, zze(i8))) {
                            return false;
                        }
                    } else if (i13 != 49) {
                        if (i13 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzd2 = this.zzs.zzd(zzamm.zze(t3, (long) (zzc2 & 1048575)));
                            if (!zzd2.isEmpty()) {
                                if (this.zzs.zza(zzf(i8)).zzc.zzb() == zzand.MESSAGE) {
                                    zzalh<?> zzalh = null;
                                    for (Object next : zzd2.values()) {
                                        if (zzalh == null) {
                                            zzalh = zzald.zza().zza(next.getClass());
                                        }
                                        if (!zzalh.zzd(next)) {
                                            return false;
                                        }
                                    }
                                    continue;
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                }
                List list = (List) zzamm.zze(t3, (long) (zzc2 & 1048575));
                if (!list.isEmpty()) {
                    zzalh zze2 = zze(i8);
                    for (int i14 = 0; i14 < list.size(); i14++) {
                        if (!zze2.zzd(list.get(i14))) {
                            return false;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            } else if (zza(t2, i8, i4, i3, i12) && !zza((Object) t3, zzc2, zze(i8))) {
                return false;
            }
            i7++;
            i5 = i4;
            i6 = i3;
        }
        return !this.zzh || this.zzr.zza((Object) t3).zzg();
    }

    private static zzamf zze(Object obj) {
        zzaje zzaje = (zzaje) obj;
        zzamf zzamf = zzaje.zzb;
        if (zzamf != zzamf.zzc()) {
            return zzamf;
        }
        zzamf zzd2 = zzamf.zzd();
        zzaje.zzb = zzd2;
        return zzd2;
    }

    private static <T> boolean zze(T t2, long j2) {
        return ((Boolean) zzamm.zze(t2, j2)).booleanValue();
    }

    private final boolean zzc(T t2, T t3, int i3) {
        return zzc(t2, i3) == zzc(t3, i3);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02d6, code lost:
        r12 = r12 + r0;
        r15 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0566, code lost:
        r11 = r11 + 3;
        r0 = r14;
        r1 = r16;
        r10 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        r12 = r12 + r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a8, code lost:
        r12 = r12 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01ad, code lost:
        r12 = r12 + ((r2 + r1) + r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r18) {
        /*
            r17 = this;
            r6 = r17
            r7 = r18
            sun.misc.Unsafe r8 = zzb
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r10
            r1 = 0
            r11 = 0
            r12 = 0
        L_0x000d:
            int[] r2 = r6.zzc
            int r2 = r2.length
            if (r11 >= r2) goto L_0x0570
            int r2 = r6.zzc((int) r11)
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r2
            int r3 = r3 >>> 20
            int[] r4 = r6.zzc
            r13 = r4[r11]
            int r5 = r11 + 2
            r4 = r4[r5]
            r5 = r4 & r10
            r14 = 17
            r15 = 1
            if (r3 > r14) goto L_0x0040
            if (r5 == r0) goto L_0x0037
            if (r5 != r10) goto L_0x0030
            r1 = 0
            goto L_0x0036
        L_0x0030:
            long r0 = (long) r5
            int r0 = r8.getInt(r7, r0)
            r1 = r0
        L_0x0036:
            r0 = r5
        L_0x0037:
            int r4 = r4 >>> 20
            int r4 = r15 << r4
            r14 = r0
            r16 = r1
            r5 = r4
            goto L_0x0044
        L_0x0040:
            r14 = r0
            r16 = r1
            r5 = 0
        L_0x0044:
            r0 = r2 & r10
            long r1 = (long) r0
            com.google.android.gms.internal.firebase-auth-api.zzaiy r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaiy.DOUBLE_LIST_PACKED
            int r0 = r0.zza()
            if (r3 < r0) goto L_0x0055
            com.google.android.gms.internal.firebase-auth-api.zzaiy r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaiy.SINT64_LIST_PACKED
            int r0 = r0.zza()
        L_0x0055:
            r4 = 0
            r9 = 0
            switch(r3) {
                case 0: goto L_0x054f;
                case 1: goto L_0x053a;
                case 2: goto L_0x0521;
                case 3: goto L_0x0508;
                case 4: goto L_0x04ef;
                case 5: goto L_0x04db;
                case 6: goto L_0x04c5;
                case 7: goto L_0x04b1;
                case 8: goto L_0x048a;
                case 9: goto L_0x046d;
                case 10: goto L_0x0452;
                case 11: goto L_0x0439;
                case 12: goto L_0x0420;
                case 13: goto L_0x040b;
                case 14: goto L_0x03f7;
                case 15: goto L_0x03de;
                case 16: goto L_0x03c5;
                case 17: goto L_0x03a6;
                case 18: goto L_0x0399;
                case 19: goto L_0x038c;
                case 20: goto L_0x037f;
                case 21: goto L_0x0372;
                case 22: goto L_0x0365;
                case 23: goto L_0x0358;
                case 24: goto L_0x034b;
                case 25: goto L_0x033f;
                case 26: goto L_0x0333;
                case 27: goto L_0x0323;
                case 28: goto L_0x0317;
                case 29: goto L_0x030a;
                case 30: goto L_0x02fe;
                case 31: goto L_0x02f2;
                case 32: goto L_0x02e6;
                case 33: goto L_0x02da;
                case 34: goto L_0x02cb;
                case 35: goto L_0x02b5;
                case 36: goto L_0x029f;
                case 37: goto L_0x0289;
                case 38: goto L_0x0273;
                case 39: goto L_0x025d;
                case 40: goto L_0x0247;
                case 41: goto L_0x0231;
                case 42: goto L_0x021b;
                case 43: goto L_0x0206;
                case 44: goto L_0x01f1;
                case 45: goto L_0x01dc;
                case 46: goto L_0x01c7;
                case 47: goto L_0x01b2;
                case 48: goto L_0x0199;
                case 49: goto L_0x0189;
                case 50: goto L_0x0179;
                case 51: goto L_0x016b;
                case 52: goto L_0x015f;
                case 53: goto L_0x014f;
                case 54: goto L_0x013f;
                case 55: goto L_0x012f;
                case 56: goto L_0x0123;
                case 57: goto L_0x0117;
                case 58: goto L_0x010b;
                case 59: goto L_0x00ed;
                case 60: goto L_0x00d9;
                case 61: goto L_0x00c8;
                case 62: goto L_0x00b9;
                case 63: goto L_0x00aa;
                case 64: goto L_0x009d;
                case 65: goto L_0x0092;
                case 66: goto L_0x0083;
                case 67: goto L_0x0074;
                case 68: goto L_0x005c;
                default: goto L_0x005b;
            }
        L_0x005b:
            goto L_0x0071
        L_0x005c:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.android.gms.internal.firebase-auth-api.zzakp r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r0
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza(r13, r0, r1)
        L_0x0070:
            int r12 = r12 + r0
        L_0x0071:
            r15 = 0
            goto L_0x0566
        L_0x0074:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            long r0 = zzd(r7, r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzd((int) r13, (long) r0)
            goto L_0x0070
        L_0x0083:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = zzc(r7, (long) r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzf((int) r13, (int) r0)
            goto L_0x0070
        L_0x0092:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzc((int) r13, (long) r9)
            goto L_0x0070
        L_0x009d:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            r0 = 0
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zze((int) r13, (int) r0)
        L_0x00a8:
            int r12 = r12 + r1
            goto L_0x0071
        L_0x00aa:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = zzc(r7, (long) r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzb((int) r13, (int) r0)
            goto L_0x0070
        L_0x00b9:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = zzc(r7, (long) r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzg((int) r13, (int) r0)
            goto L_0x0070
        L_0x00c8:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x0070
        L_0x00d9:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r1)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r13, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
        L_0x00eb:
            int r12 = r12 + r0
            goto L_0x0071
        L_0x00ed:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r1)
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaho
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x0070
        L_0x0103:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (java.lang.String) r0)
            goto L_0x0070
        L_0x010b:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (boolean) r15)
            goto L_0x0070
        L_0x0117:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            r0 = 0
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzc((int) r13, (int) r0)
            goto L_0x00a8
        L_0x0123:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (long) r9)
            goto L_0x0070
        L_0x012f:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = zzc(r7, (long) r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzd((int) r13, (int) r0)
            goto L_0x0070
        L_0x013f:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            long r0 = zzd(r7, r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zze((int) r13, (long) r0)
            goto L_0x0070
        L_0x014f:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            long r0 = zzd(r7, r1)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzb((int) r13, (long) r0)
            goto L_0x0070
        L_0x015f:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (float) r4)
            goto L_0x0070
        L_0x016b:
            boolean r0 = r6.zzc(r7, (int) r13, (int) r11)
            if (r0 == 0) goto L_0x0071
            r0 = 0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (double) r0)
            goto L_0x0070
        L_0x0179:
            com.google.android.gms.internal.firebase-auth-api.zzaki r0 = r6.zzs
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.lang.Object r2 = r6.zzf((int) r11)
            int r0 = r0.zza(r13, r1, r2)
            goto L_0x00eb
        L_0x0189:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r13, (java.util.List<com.google.android.gms.internal.p002firebaseauthapi.zzakp>) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x00eb
        L_0x0199:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
        L_0x01ad:
            int r2 = r2 + r1
            int r2 = r2 + r0
            int r12 = r12 + r2
            goto L_0x0071
        L_0x01b2:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x01c7:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x01dc:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x01f1:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x0206:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x021b:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.util.List<?>) r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x0231:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x0247:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x025d:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x0273:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x0289:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x029f:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x02b5:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r0)
            if (r0 <= 0) goto L_0x0071
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzi((int) r13)
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzj((int) r0)
            goto L_0x01ad
        L_0x02cb:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            r3 = 0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r13, r0, r3)
        L_0x02d6:
            int r12 = r12 + r0
            r15 = r3
            goto L_0x0566
        L_0x02da:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r13, r0, r3)
            goto L_0x02d6
        L_0x02e6:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r13, r0, r3)
            goto L_0x02d6
        L_0x02f2:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r13, r0, r3)
            goto L_0x02d6
        L_0x02fe:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r13, (java.util.List<java.lang.Integer>) r0, (boolean) r3)
            goto L_0x02d6
        L_0x030a:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r13, r0, r3)
            goto L_0x00eb
        L_0x0317:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r13, (java.util.List<com.google.android.gms.internal.p002firebaseauthapi.zzaho>) r0)
            goto L_0x00eb
        L_0x0323:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r13, (java.util.List<?>) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x00eb
        L_0x0333:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb(r13, r0)
            goto L_0x00eb
        L_0x033f:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            r3 = 0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r13, (java.util.List<?>) r0, (boolean) r3)
            goto L_0x02d6
        L_0x034b:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r13, r0, r3)
            goto L_0x02d6
        L_0x0358:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r13, r0, r3)
            goto L_0x02d6
        L_0x0365:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r13, r0, r3)
            goto L_0x02d6
        L_0x0372:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r13, r0, r3)
            goto L_0x02d6
        L_0x037f:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r13, r0, r3)
            goto L_0x02d6
        L_0x038c:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r13, r0, r3)
            goto L_0x02d6
        L_0x0399:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r13, r0, r3)
            goto L_0x00eb
        L_0x03a6:
            r0 = r17
            r9 = r1
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.android.gms.internal.firebase-auth-api.zzakp r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r0
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza(r13, r0, r1)
            goto L_0x0070
        L_0x03c5:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzd((int) r13, (long) r0)
            goto L_0x0070
        L_0x03de:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzf((int) r13, (int) r0)
            goto L_0x0070
        L_0x03f7:
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzc((int) r13, (long) r9)
            goto L_0x0070
        L_0x040b:
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            r0 = 0
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zze((int) r13, (int) r0)
            goto L_0x00a8
        L_0x0420:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzb((int) r13, (int) r0)
            goto L_0x0070
        L_0x0439:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzg((int) r13, (int) r0)
            goto L_0x0070
        L_0x0452:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x0070
        L_0x046d:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r9)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r13, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x00eb
        L_0x048a:
            r9 = r1
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r8.getObject(r7, r9)
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzaho
            if (r1 == 0) goto L_0x04a9
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x0070
        L_0x04a9:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (java.lang.String) r0)
            goto L_0x0070
        L_0x04b1:
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (boolean) r15)
            goto L_0x0070
        L_0x04c5:
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0071
            r15 = 0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzc((int) r13, (int) r15)
        L_0x04d8:
            int r12 = r12 + r0
            goto L_0x0566
        L_0x04db:
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (long) r9)
            goto L_0x04d8
        L_0x04ef:
            r9 = r1
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            int r0 = r8.getInt(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzd((int) r13, (int) r0)
            goto L_0x04d8
        L_0x0508:
            r9 = r1
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zze((int) r13, (long) r0)
            goto L_0x04d8
        L_0x0521:
            r9 = r1
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            long r0 = r8.getLong(r7, r9)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zzb((int) r13, (long) r0)
            goto L_0x04d8
        L_0x053a:
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (float) r9)
            goto L_0x04d8
        L_0x054f:
            r15 = 0
            r0 = r17
            r1 = r18
            r2 = r11
            r3 = r14
            r4 = r16
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0566
            r0 = 0
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzaim.zza((int) r13, (double) r0)
            goto L_0x04d8
        L_0x0566:
            int r11 = r11 + 3
            r0 = r14
            r1 = r16
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000d
        L_0x0570:
            r15 = 0
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r6.zzq
            java.lang.Object r1 = r0.zzd(r7)
            int r0 = r0.zza(r1)
            int r12 = r12 + r0
            boolean r0 = r6.zzh
            if (r0 == 0) goto L_0x05ce
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r0 = r0.zza((java.lang.Object) r7)
            r9 = r15
        L_0x0587:
            com.google.android.gms.internal.firebase-auth-api.zzali<T, java.lang.Object> r1 = r0.zza
            int r1 = r1.zza()
            if (r9 >= r1) goto L_0x05a7
            com.google.android.gms.internal.firebase-auth-api.zzali<T, java.lang.Object> r1 = r0.zza
            java.util.Map$Entry r1 = r1.zzb((int) r9)
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.firebase-auth-api.zzaiz r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaiz) r2
            java.lang.Object r1 = r1.getValue()
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaix.zza((com.google.android.gms.internal.p002firebaseauthapi.zzaiz<?>) r2, (java.lang.Object) r1)
            int r15 = r15 + r1
            int r9 = r9 + 1
            goto L_0x0587
        L_0x05a7:
            com.google.android.gms.internal.firebase-auth-api.zzali<T, java.lang.Object> r0 = r0.zza
            java.lang.Iterable r0 = r0.zzb()
            java.util.Iterator r0 = r0.iterator()
        L_0x05b1:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x05cd
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            com.google.android.gms.internal.firebase-auth-api.zzaiz r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzaiz) r2
            java.lang.Object r1 = r1.getValue()
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaix.zza((com.google.android.gms.internal.p002firebaseauthapi.zzaiz<?>) r2, (java.lang.Object) r1)
            int r15 = r15 + r1
            goto L_0x05b1
        L_0x05cd:
            int r12 = r12 + r15
        L_0x05ce:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(java.lang.Object):int");
    }

    private final boolean zzc(T t2, int i3) {
        int zzb2 = zzb(i3);
        long j2 = (long) (zzb2 & 1048575);
        if (j2 != 1048575) {
            return ((1 << (zzb2 >>> 20)) & zzamm.zzc(t2, j2)) != 0;
        }
        int zzc2 = zzc(i3);
        long j3 = (long) (zzc2 & 1048575);
        switch ((zzc2 & 267386880) >>> 20) {
            case 0:
                return Double.doubleToRawLongBits(zzamm.zza((Object) t2, j3)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzamm.zzb(t2, j3)) != 0;
            case 2:
                return zzamm.zzd(t2, j3) != 0;
            case 3:
                return zzamm.zzd(t2, j3) != 0;
            case 4:
                return zzamm.zzc(t2, j3) != 0;
            case 5:
                return zzamm.zzd(t2, j3) != 0;
            case 6:
                return zzamm.zzc(t2, j3) != 0;
            case 7:
                return zzamm.zzh(t2, j3);
            case 8:
                Object zze2 = zzamm.zze(t2, j3);
                if (zze2 instanceof String) {
                    return !((String) zze2).isEmpty();
                }
                if (zze2 instanceof zzaho) {
                    return !zzaho.zza.equals(zze2);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzamm.zze(t2, j3) != null;
            case 10:
                return !zzaho.zza.equals(zzamm.zze(t2, j3));
            case 11:
                return zzamm.zzc(t2, j3) != 0;
            case 12:
                return zzamm.zzc(t2, j3) != 0;
            case 13:
                return zzamm.zzc(t2, j3) != 0;
            case 14:
                return zzamm.zzd(t2, j3) != 0;
            case 15:
                return zzamm.zzc(t2, j3) != 0;
            case 16:
                return zzamm.zzd(t2, j3) != 0;
            case 17:
                return zzamm.zze(t2, j3) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzc(T t2, int i3, int i4) {
        return zzamm.zzc(t2, (long) (zzb(i4) & 1048575)) == i3;
    }

    private final int zzb(int i3) {
        return this.zzc[i3 + 2];
    }

    private final void zzb(T t2, T t3, int i3) {
        int i4 = this.zzc[i3];
        if (zzc(t3, i4, i3)) {
            long zzc2 = (long) (zzc(i3) & 1048575);
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(t3, zzc2);
            if (object != null) {
                zzalh zze2 = zze(i3);
                if (!zzc(t2, i4, i3)) {
                    if (!zzg(object)) {
                        unsafe.putObject(t2, zzc2, object);
                    } else {
                        Object zza2 = zze2.zza();
                        zze2.zza(zza2, object);
                        unsafe.putObject(t2, zzc2, zza2);
                    }
                    zzb(t2, i4, i3);
                    return;
                }
                Object object2 = unsafe.getObject(t2, zzc2);
                if (!zzg(object2)) {
                    Object zza3 = zze2.zza();
                    zze2.zza(zza3, object2);
                    unsafe.putObject(t2, zzc2, zza3);
                    object2 = zza3;
                }
                zze2.zza(object2, object);
                return;
            }
            int i5 = this.zzc[i3];
            String valueOf = String.valueOf(t3);
            throw new IllegalStateException("Source subfield " + i5 + " is present but null: " + valueOf);
        }
    }

    private final void zzb(T t2, int i3) {
        int zzb2 = zzb(i3);
        long j2 = (long) (1048575 & zzb2);
        if (j2 != 1048575) {
            zzamm.zza((Object) t2, j2, (1 << (zzb2 >>> 20)) | zzamm.zzc(t2, j2));
        }
    }

    private final void zzb(T t2, int i3, int i4) {
        zzamm.zza((Object) t2, (long) (zzb(i4) & 1048575), i3);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006b, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6), com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)) != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007e, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008f, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a2, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b3, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c4, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00d6, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ec, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6), com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)) != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0102, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6), com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)) != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0118, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6), com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)) != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012a, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x013c, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0150, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0162, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0176, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x018a, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6) == com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r11, r6))) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c0, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r11, r6))) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6), com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)) != false) goto L_0x01c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003a, code lost:
        r3 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzb(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzc
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01cb
            int r4 = r9.zzc((int) r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x01a8;
                case 1: goto L_0x018e;
                case 2: goto L_0x017a;
                case 3: goto L_0x0166;
                case 4: goto L_0x0154;
                case 5: goto L_0x0140;
                case 6: goto L_0x012e;
                case 7: goto L_0x011c;
                case 8: goto L_0x0106;
                case 9: goto L_0x00f0;
                case 10: goto L_0x00da;
                case 11: goto L_0x00c8;
                case 12: goto L_0x00b6;
                case 13: goto L_0x00a5;
                case 14: goto L_0x0092;
                case 15: goto L_0x0081;
                case 16: goto L_0x006e;
                case 17: goto L_0x0059;
                case 18: goto L_0x004b;
                case 19: goto L_0x004b;
                case 20: goto L_0x004b;
                case 21: goto L_0x004b;
                case 22: goto L_0x004b;
                case 23: goto L_0x004b;
                case 24: goto L_0x004b;
                case 25: goto L_0x004b;
                case 26: goto L_0x004b;
                case 27: goto L_0x004b;
                case 28: goto L_0x004b;
                case 29: goto L_0x004b;
                case 30: goto L_0x004b;
                case 31: goto L_0x004b;
                case 32: goto L_0x004b;
                case 33: goto L_0x004b;
                case 34: goto L_0x004b;
                case 35: goto L_0x004b;
                case 36: goto L_0x004b;
                case 37: goto L_0x004b;
                case 38: goto L_0x004b;
                case 39: goto L_0x004b;
                case 40: goto L_0x004b;
                case 41: goto L_0x004b;
                case 42: goto L_0x004b;
                case 43: goto L_0x004b;
                case 44: goto L_0x004b;
                case 45: goto L_0x004b;
                case 46: goto L_0x004b;
                case 47: goto L_0x004b;
                case 48: goto L_0x004b;
                case 49: goto L_0x004b;
                case 50: goto L_0x003d;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01c4
        L_0x001c:
            int r4 = r9.zzb((int) r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r4)
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r4)
            if (r8 != r4) goto L_0x003a
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c4
        L_0x003a:
            r3 = r1
            goto L_0x01c4
        L_0x003d:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r3 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01c4
        L_0x004b:
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r3 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r3, (java.lang.Object) r4)
            goto L_0x01c4
        L_0x0059:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c4
            goto L_0x003a
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6)
            long r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x0081:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x0092:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6)
            long r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x00a5:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x00b6:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x00c8:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x00da:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c4
            goto L_0x003a
        L_0x00f0:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c4
            goto L_0x003a
        L_0x0106:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r11, r6)
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 != 0) goto L_0x01c4
            goto L_0x003a
        L_0x011c:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            boolean r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r10, r6)
            boolean r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x012e:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x0140:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6)
            long r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x0154:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            int r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r10, r6)
            int r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r11, r6)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x0166:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6)
            long r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x017a:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            long r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r10, r6)
            long r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r11, r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            float r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c4
            goto L_0x003a
        L_0x01a8:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x003a
            double r4 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r10, (long) r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r11, (long) r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x01c4
            goto L_0x003a
        L_0x01c4:
            if (r3 != 0) goto L_0x01c7
            return r1
        L_0x01c7:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01cb:
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r9.zzq
            java.lang.Object r0 = r0.zzd(r10)
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r2 = r9.zzq
            java.lang.Object r2 = r2.zzd(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01de
            return r1
        L_0x01de:
            boolean r0 = r9.zzh
            if (r0 == 0) goto L_0x01f3
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r9.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r10 = r0.zza((java.lang.Object) r10)
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r9 = r9.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r9 = r9.zza((java.lang.Object) r11)
            boolean r9 = r10.equals(r9)
            return r9
        L_0x01f3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zzb(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.google.android.gms.internal.firebase-auth-api.zzamf} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v36, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v39, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v40, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v70, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v47, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v77, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v78, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v83, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v47, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v55, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v57, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v57, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v100, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v104, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v105, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v106, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v108, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v110, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v60, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v113, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v50, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v42, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v63, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v22, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v116, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v25, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v65, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v68, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v70, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v73, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v82, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v77, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v28, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v79, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v131, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v41, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v30, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v138, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v139, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v143, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v144, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v31, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v148, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v150, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v32, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v153, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v154, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v155, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v157, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v159, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v33, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v43, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v161, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v162, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v163, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v165, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v167, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v47, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v35, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v168, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v36, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v45, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v88, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v37, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v172, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v173, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v175, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v38, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v46, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v90, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v110, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v49, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v67, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v50, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v78, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v94, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v55, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v79, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v183, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v80, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v69, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v51, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v71, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v98, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v81, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v52, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v72, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v56, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v189, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v73, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v57, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v53, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v75, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v82, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v76, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v58, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v77, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v59, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v196, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v78, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v86, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v13, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v79, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v197, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v87, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v80, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v61, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v14, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v199, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v200, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v202, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v203, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v88, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v81, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v62, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v15, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v36, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v206, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v83, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v90, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v63, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v91, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v84, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v92, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v85, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v208, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v55, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v86, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v93, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v66, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v88, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v110, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v89, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v57, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v90, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v91, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v58, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v92, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v94, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v93, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v213, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v111, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v95, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v94, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v214, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v60, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v19, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v96, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v215, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v95, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v75, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v97, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v216, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v76, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v98, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v72, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v21, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v217, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v61, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v115, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v99, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v96, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v219, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v74, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v101, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v82, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v37, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v99, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v102, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v63, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v119, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v103, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v77, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v224, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v121, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v65, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v103, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v105, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v80, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v106, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v107, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v81, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v123, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v108, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v118, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v124, resolved type: byte} */
    /* JADX WARNING: type inference failed for: r11v66, types: [int] */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0382, code lost:
        r27 = r8;
        r2 = r10;
        r10 = r13;
        r13 = r14;
        r14 = r15;
        r4 = r19;
        r8 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0476, code lost:
        r9 = r10;
        r8 = r11;
        r10 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x04d8, code lost:
        r9 = r10;
        r8 = r11;
        r10 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x05df, code lost:
        r14 = r0;
        r0 = r1;
        r10 = r3;
        r8 = r4;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:258:0x06a1, code lost:
        r14 = r0;
        r10 = r3;
        r8 = r4;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x075c, code lost:
        r14 = r0;
        r10 = r3;
        r8 = r4;
        r9 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x0760, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00c1, code lost:
        r20 = r19;
        r19 = r8;
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x08c3, code lost:
        if (r0 != r10) goto L_0x08d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x08c5, code lost:
        r7 = r31;
        r2 = r0;
        r5 = r6;
        r10 = r9;
        r13 = r14;
        r14 = r15;
        r4 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x08d2, code lost:
        r5 = r35;
        r4 = r7;
        r11 = r8;
        r10 = r9;
        r2 = r14;
        r9 = r19;
        r13 = r21;
        r14 = r26;
        r3 = r27;
        r7 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0149, code lost:
        r13 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x0a5b, code lost:
        r8 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x0a5d, code lost:
        r19 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x0a95, code lost:
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x0a97, code lost:
        r8 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:430:0x0a99, code lost:
        r19 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x017a, code lost:
        r1 = r21 | r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x017c, code lost:
        r3 = r8;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:472:0x0bcf, code lost:
        r0 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x017e, code lost:
        r10 = r13;
        r2 = r14;
        r9 = r19;
        r11 = r20;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:489:0x0c74, code lost:
        if (r0 != r6) goto L_0x0ce0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0184, code lost:
        r14 = r26;
        r8 = r0;
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:490:0x0c76, code lost:
        r6 = r35;
        r2 = r0;
        r10 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:506:0x0ce0, code lost:
        r11 = r4;
        r4 = r34;
        r6 = r5;
        r9 = r11;
        r2 = r13;
        r15 = r14;
        r10 = r19;
        r13 = r21;
        r14 = r26;
        r3 = r27;
        r5 = r35;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:508:0x0cfd, code lost:
        if (r14 == r0) goto L_0x0d05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:509:0x0cff, code lost:
        r27.putInt(r7, (long) r14, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:510:0x0d05, code lost:
        r10 = r9.zzm;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:512:0x0d0c, code lost:
        if (r10 >= r9.zzn) goto L_0x0d24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:513:0x0d0e, code lost:
        r3 = zza((java.lang.Object) r31, r9.zzl[r10], r3, r9.zzq, (java.lang.Object) r31);
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:514:0x0d24, code lost:
        if (r3 == null) goto L_0x0d2b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:515:0x0d26, code lost:
        r9.zzq.zzb((java.lang.Object) r7, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:516:0x0d2b, code lost:
        if (r6 != 0) goto L_0x0d37;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:518:0x0d2f, code lost:
        if (r8 != r34) goto L_0x0d32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:520:0x0d36, code lost:
        throw com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:522:0x0d39, code lost:
        if (r8 > r34) goto L_0x0d3e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:523:0x0d3b, code lost:
        if (r11 != r6) goto L_0x0d3e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:524:0x0d3d, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:526:0x0d42, code lost:
        throw com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0209, code lost:
        r20 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x027d, code lost:
        r1 = r21 | r25;
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0287, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v72, types: [int, byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0589  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0616  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0664  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0c7d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:498:0x0c96  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x0cce  */
    /* JADX WARNING: Removed duplicated region for block: B:569:0x0476 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:573:0x05df A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:577:0x05df A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r31, byte[] r32, int r33, int r34, int r35, com.google.android.gms.internal.p002firebaseauthapi.zzahn r36) throws java.io.IOException {
        /*
            r30 = this;
            r6 = r30
            r7 = r31
            r15 = r32
            r4 = r34
            r5 = r35
            r2 = r36
            zzf((java.lang.Object) r31)
            sun.misc.Unsafe r3 = zzb
            r16 = 0
            r8 = r33
            r10 = r16
            r11 = r10
            r13 = r11
            r9 = -1
            r14 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001d:
            if (r8 >= r4) goto L_0x0cf2
            int r11 = r8 + 1
            byte r8 = r15[r8]
            if (r8 >= 0) goto L_0x002e
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r8, (byte[]) r15, (int) r11, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r2)
            int r11 = r2.zza
            r12 = r11
            r11 = r8
            goto L_0x002f
        L_0x002e:
            r12 = r8
        L_0x002f:
            int r8 = r12 >>> 3
            r1 = r12 & 7
            r0 = 3
            if (r8 <= r9) goto L_0x0048
            int r10 = r10 / r0
            int r9 = r6.zze
            if (r8 < r9) goto L_0x0044
            int r9 = r6.zzf
            if (r8 > r9) goto L_0x0044
            int r9 = r6.zza((int) r8, (int) r10)
            goto L_0x0045
        L_0x0044:
            r9 = -1
        L_0x0045:
            r10 = r9
            r9 = -1
            goto L_0x004d
        L_0x0048:
            int r9 = r6.zza((int) r8)
            goto L_0x0045
        L_0x004d:
            if (r10 != r9) goto L_0x0065
            r27 = r3
            r4 = r8
            r18 = r9
            r8 = r12
            r21 = r13
            r26 = r14
            r14 = r15
            r10 = r16
            r13 = r2
            r2 = r11
        L_0x005e:
            r29 = r6
            r6 = r5
            r5 = r29
            goto L_0x0c7b
        L_0x0065:
            int[] r9 = r6.zzc
            int r19 = r10 + 1
            r0 = r9[r19]
            r19 = 267386880(0xff00000, float:2.3665827E-29)
            r19 = r0 & r19
            int r4 = r19 >>> 20
            r17 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r0 & r17
            r20 = r11
            r19 = r12
            long r11 = (long) r5
            r22 = 0
            java.lang.String r5 = ""
            r24 = r5
            r5 = 17
            if (r4 > r5) goto L_0x038e
            int r5 = r10 + 2
            r5 = r9[r5]
            int r9 = r5 >>> 20
            r21 = 1
            int r25 = r21 << r9
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r9
            r17 = r10
            if (r5 == r14) goto L_0x00af
            if (r14 == r9) goto L_0x00a0
            long r9 = (long) r14
            r3.putInt(r7, r9, r13)
            r9 = 1048575(0xfffff, float:1.469367E-39)
        L_0x00a0:
            if (r5 != r9) goto L_0x00a5
            r10 = r16
            goto L_0x00aa
        L_0x00a5:
            long r13 = (long) r5
            int r10 = r3.getInt(r7, r13)
        L_0x00aa:
            r26 = r5
            r21 = r10
            goto L_0x00b3
        L_0x00af:
            r21 = r13
            r26 = r14
        L_0x00b3:
            switch(r4) {
                case 0: goto L_0x0364;
                case 1: goto L_0x0346;
                case 2: goto L_0x0312;
                case 3: goto L_0x0312;
                case 4: goto L_0x02f5;
                case 5: goto L_0x02cb;
                case 6: goto L_0x02ae;
                case 7: goto L_0x028a;
                case 8: goto L_0x0241;
                case 9: goto L_0x020d;
                case 10: goto L_0x01e2;
                case 11: goto L_0x02f5;
                case 12: goto L_0x018a;
                case 13: goto L_0x02ae;
                case 14: goto L_0x02cb;
                case 15: goto L_0x015b;
                case 16: goto L_0x010b;
                case 17: goto L_0x00c8;
                default: goto L_0x00b6;
            }
        L_0x00b6:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
        L_0x00c1:
            r20 = r19
            r19 = r8
            r8 = r3
            goto L_0x0382
        L_0x00c8:
            r0 = 3
            if (r1 != r0) goto L_0x00ff
            r0 = r17
            java.lang.Object r1 = r6.zza(r7, (int) r0)
            int r4 = r8 << 3
            r13 = r4 | 4
            com.google.android.gms.internal.firebase-auth-api.zzalh r4 = r6.zze((int) r0)
            r5 = r8
            r8 = r1
            r17 = r9
            r18 = -1
            r9 = r4
            r4 = r0
            r10 = r32
            r11 = r20
            r0 = r19
            r12 = r34
            r14 = r36
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((java.lang.Object) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r9, (byte[]) r10, (int) r11, (int) r12, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            r6.zza(r7, (int) r4, (java.lang.Object) r1)
            r13 = r21 | r25
            r11 = r0
            r10 = r4
            r9 = r5
            r14 = r26
            r4 = r34
            r5 = r35
            goto L_0x001d
        L_0x00ff:
            r18 = -1
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            goto L_0x00c1
        L_0x010b:
            r5 = r8
            r4 = r17
            r0 = r19
            r18 = -1
            r17 = r9
            if (r1 != 0) goto L_0x014c
            r10 = r20
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r10, r2)
            long r9 = r2.zzb
            long r9 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r9)
            r13 = r0
            r0 = r3
            r14 = r17
            r1 = r31
            r14 = r2
            r33 = r8
            r8 = r3
            r2 = r11
            r11 = r34
            r12 = r35
            r19 = r5
            r20 = r13
            r13 = r4
            r4 = r9
            r0.putLong(r1, r2, r4)
            r0 = r21 | r25
            r3 = r8
            r4 = r11
            r5 = r12
            r10 = r13
            r2 = r14
            r9 = r19
            r11 = r20
            r14 = r26
            r8 = r33
        L_0x0149:
            r13 = r0
            goto L_0x001d
        L_0x014c:
            r14 = r2
            r8 = r3
            r13 = r4
            r19 = r5
            r10 = r20
            r9 = r34
            r5 = r35
            r20 = r0
            goto L_0x0382
        L_0x015b:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != 0) goto L_0x0382
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r1)
            r8.putInt(r7, r11, r1)
        L_0x017a:
            r1 = r21 | r25
        L_0x017c:
            r3 = r8
            r4 = r9
        L_0x017e:
            r10 = r13
            r2 = r14
            r9 = r19
            r11 = r20
        L_0x0184:
            r14 = r26
            r8 = r0
            r13 = r1
            goto L_0x001d
        L_0x018a:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != 0) goto L_0x0382
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r2 = r14.zza
            com.google.android.gms.internal.firebase-auth-api.zzaji r3 = r6.zzd((int) r13)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r4
            if (r0 == 0) goto L_0x01b3
            if (r3 == 0) goto L_0x01b3
            boolean r0 = r3.zza(r2)
            if (r0 == 0) goto L_0x01b6
        L_0x01b3:
            r4 = r20
            goto L_0x01d2
        L_0x01b6:
            com.google.android.gms.internal.firebase-auth-api.zzamf r0 = zze((java.lang.Object) r31)
            long r2 = (long) r2
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4 = r20
            r0.zza((int) r4, (java.lang.Object) r2)
            r11 = r4
            r3 = r8
            r4 = r9
            r10 = r13
            r2 = r14
            r9 = r19
            r13 = r21
            r14 = r26
        L_0x01cf:
            r8 = r1
            goto L_0x001d
        L_0x01d2:
            r8.putInt(r7, r11, r2)
            r0 = r21 | r25
            r11 = r4
            r3 = r8
            r4 = r9
            r10 = r13
            r2 = r14
            r9 = r19
            r14 = r26
            r13 = r0
            goto L_0x01cf
        L_0x01e2:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r4 = r19
            r10 = r20
            r0 = 2
            r18 = -1
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0209
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r15, r10, r14)
            java.lang.Object r1 = r14.zzc
            r8.putObject(r7, r11, r1)
            r1 = r21 | r25
            r11 = r4
            r3 = r8
            r4 = r9
            r10 = r13
            r2 = r14
            r9 = r19
            goto L_0x0184
        L_0x0209:
            r20 = r4
            goto L_0x0382
        L_0x020d:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r4 = r19
            r10 = r20
            r0 = 2
            r18 = -1
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0209
            java.lang.Object r11 = r6.zza(r7, (int) r13)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r13)
            r0 = r11
            r2 = r32
            r3 = r10
            r20 = r4
            r4 = r34
            r12 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1, (byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            r6.zza(r7, (int) r13, (java.lang.Object) r11)
            r1 = r21 | r25
            r3 = r8
            r4 = r9
            r5 = r12
            goto L_0x017e
        L_0x0241:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r2 = 2
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != r2) goto L_0x0287
            boolean r0 = zzg((int) r0)
            if (r0 == 0) goto L_0x025f
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r15, r10, r14)
            goto L_0x0278
        L_0x025f:
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            if (r1 < 0) goto L_0x0282
            if (r1 != 0) goto L_0x026e
            r2 = r24
            r14.zzc = r2
            goto L_0x0278
        L_0x026e:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r3 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r2.<init>(r15, r0, r1, r3)
            r14.zzc = r2
            int r0 = r0 + r1
        L_0x0278:
            java.lang.Object r1 = r14.zzc
            r8.putObject(r7, r11, r1)
        L_0x027d:
            r1 = r21 | r25
            r5 = r4
            goto L_0x017c
        L_0x0282:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x0287:
            r5 = r4
            goto L_0x0382
        L_0x028a:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != 0) goto L_0x0287
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r10, r14)
            long r1 = r14.zzb
            int r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x02a8
            r5 = 1
            goto L_0x02aa
        L_0x02a8:
            r5 = r16
        L_0x02aa:
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc((java.lang.Object) r7, (long) r11, (boolean) r5)
            goto L_0x027d
        L_0x02ae:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r0 = 5
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0287
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10)
            r8.putInt(r7, r11, r0)
            int r0 = r10 + 4
            goto L_0x027d
        L_0x02cb:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r0 = 1
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0287
            long r22 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r10)
            r0 = r8
            r1 = r31
            r2 = r11
            r11 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            int r0 = r10 + 8
            r1 = r21 | r25
            r3 = r8
            r4 = r9
            r5 = r11
            goto L_0x017e
        L_0x02f5:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != 0) goto L_0x0287
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            r8.putInt(r7, r11, r1)
            goto L_0x027d
        L_0x0312:
            r9 = r34
            r4 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != 0) goto L_0x0287
            int r10 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r10, r14)
            long r2 = r14.zzb
            r0 = r8
            r1 = r31
            r22 = r2
            r2 = r11
            r11 = r4
            r4 = r22
            r0.putLong(r1, r2, r4)
            r0 = r21 | r25
            r3 = r8
            r4 = r9
            r8 = r10
            r5 = r11
            r10 = r13
            r2 = r14
            r9 = r19
            r11 = r20
            r14 = r26
            goto L_0x0149
        L_0x0346:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r0 = 5
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0382
            float r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r15, r10)
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r7, (long) r11, (float) r0)
            int r0 = r10 + 4
            goto L_0x017a
        L_0x0364:
            r9 = r34
            r5 = r35
            r14 = r2
            r13 = r17
            r10 = r20
            r0 = 1
            r18 = -1
            r20 = r19
            r19 = r8
            r8 = r3
            if (r1 != r0) goto L_0x0382
            double r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r15, r10)
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r7, (long) r11, (double) r0)
            int r0 = r10 + 8
            goto L_0x017a
        L_0x0382:
            r27 = r8
            r2 = r10
            r10 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r8 = r20
            goto L_0x005e
        L_0x038e:
            r5 = r35
            r21 = r13
            r26 = r14
            r18 = -1
            r14 = r2
            r13 = r10
            r10 = r20
            r2 = r24
            r20 = r19
            r19 = r8
            r8 = r3
            r3 = 27
            r17 = 10
            if (r4 != r3) goto L_0x03f6
            r3 = 2
            if (r1 != r3) goto L_0x03ee
            java.lang.Object r0 = r8.getObject(r7, r11)
            com.google.android.gms.internal.firebase-auth-api.zzajl r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzajl) r0
            boolean r1 = r0.zzc()
            if (r1 != 0) goto L_0x03c9
            int r1 = r0.size()
            if (r1 != 0) goto L_0x03bf
        L_0x03bc:
            r1 = r17
            goto L_0x03c2
        L_0x03bf:
            int r17 = r1 << 1
            goto L_0x03bc
        L_0x03c2:
            com.google.android.gms.internal.firebase-auth-api.zzajl r0 = r0.zza(r1)
            r8.putObject(r7, r11, r0)
        L_0x03c9:
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r13)
            r3 = r8
            r8 = r1
            r9 = r20
            r1 = r10
            r10 = r32
            r11 = r1
            r12 = r34
            r2 = r13
            r13 = r0
            r0 = r14
            r14 = r36
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((com.google.android.gms.internal.p002firebaseauthapi.zzalh<?>) r8, (int) r9, (byte[]) r10, (int) r11, (int) r12, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            r4 = r34
            r10 = r2
            r9 = r19
            r11 = r20
            r13 = r21
            r14 = r26
            r2 = r0
            goto L_0x001d
        L_0x03ee:
            r9 = r34
            r27 = r8
            r24 = r20
            goto L_0x09f8
        L_0x03f6:
            r3 = r8
            r29 = r13
            r13 = r10
            r10 = r29
            r8 = 49
            if (r4 > r8) goto L_0x08e5
            long r8 = (long) r0
            sun.misc.Unsafe r0 = zzb
            java.lang.Object r24 = r0.getObject(r7, r11)
            r25 = r3
            r3 = r24
            com.google.android.gms.internal.firebase-auth-api.zzajl r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzajl) r3
            boolean r24 = r3.zzc()
            if (r24 != 0) goto L_0x0426
            int r24 = r3.size()
            if (r24 != 0) goto L_0x041c
        L_0x0419:
            r5 = r17
            goto L_0x041f
        L_0x041c:
            int r17 = r24 << 1
            goto L_0x0419
        L_0x041f:
            com.google.android.gms.internal.firebase-auth-api.zzajl r3 = r3.zza(r5)
            r0.putObject(r7, r11, r3)
        L_0x0426:
            r12 = r3
            switch(r4) {
                case 18: goto L_0x0875;
                case 19: goto L_0x0827;
                case 20: goto L_0x07da;
                case 21: goto L_0x07da;
                case 22: goto L_0x07b2;
                case 23: goto L_0x0763;
                case 24: goto L_0x070d;
                case 25: goto L_0x06a7;
                case 26: goto L_0x05e6;
                case 27: goto L_0x05c1;
                case 28: goto L_0x0562;
                case 29: goto L_0x07b2;
                case 30: goto L_0x0531;
                case 31: goto L_0x070d;
                case 32: goto L_0x0763;
                case 33: goto L_0x04dd;
                case 34: goto L_0x0485;
                case 35: goto L_0x0875;
                case 36: goto L_0x0827;
                case 37: goto L_0x07da;
                case 38: goto L_0x07da;
                case 39: goto L_0x07b2;
                case 40: goto L_0x0763;
                case 41: goto L_0x070d;
                case 42: goto L_0x06a7;
                case 43: goto L_0x07b2;
                case 44: goto L_0x0531;
                case 45: goto L_0x070d;
                case 46: goto L_0x0763;
                case 47: goto L_0x04dd;
                case 48: goto L_0x0485;
                case 49: goto L_0x0434;
                default: goto L_0x042a;
            }
        L_0x042a:
            r7 = r34
            r9 = r10
            r10 = r13
            r8 = r20
            r27 = r25
            goto L_0x08c2
        L_0x0434:
            r0 = 3
            if (r1 != r0) goto L_0x047b
            com.google.android.gms.internal.firebase-auth-api.zzalh r8 = r6.zze((int) r10)
            r11 = r20
            r0 = r11 & -8
            r9 = r0 | 4
            r0 = r8
            r1 = r32
            r2 = r13
            r5 = r34
            r4 = r25
            r3 = r34
            r27 = r4
            r4 = r9
            r7 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((com.google.android.gms.internal.p002firebaseauthapi.zzalh) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            java.lang.Object r1 = r14.zzc
            r12.add(r1)
        L_0x045a:
            if (r0 >= r7) goto L_0x0476
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r1 = r14.zza
            if (r11 != r1) goto L_0x0476
            r0 = r8
            r1 = r32
            r3 = r34
            r4 = r9
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((com.google.android.gms.internal.p002firebaseauthapi.zzalh) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            java.lang.Object r1 = r14.zzc
            r12.add(r1)
            goto L_0x045a
        L_0x0476:
            r9 = r10
            r8 = r11
            r10 = r13
            goto L_0x08c3
        L_0x047b:
            r7 = r34
            r27 = r25
            r9 = r10
            r10 = r13
            r8 = r20
            goto L_0x08c2
        L_0x0485:
            r7 = r34
            r11 = r20
            r27 = r25
            r0 = 2
            if (r1 != r0) goto L_0x04af
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r13, r14)
            int r1 = r14.zza
            int r1 = r1 + r0
        L_0x0497:
            if (r0 >= r1) goto L_0x04a7
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r0, r14)
            long r2 = r14.zzb
            long r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r2)
            r12.zza((long) r2)
            goto L_0x0497
        L_0x04a7:
            if (r0 != r1) goto L_0x04aa
            goto L_0x0476
        L_0x04aa:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x04af:
            if (r1 != 0) goto L_0x04d8
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r13, r14)
            long r1 = r14.zzb
            long r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r1)
            r12.zza((long) r1)
        L_0x04c0:
            if (r0 >= r7) goto L_0x0476
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r2 = r14.zza
            if (r11 != r2) goto L_0x0476
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r1, r14)
            long r1 = r14.zzb
            long r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r1)
            r12.zza((long) r1)
            goto L_0x04c0
        L_0x04d8:
            r9 = r10
            r8 = r11
            r10 = r13
            goto L_0x08c2
        L_0x04dd:
            r7 = r34
            r11 = r20
            r27 = r25
            r0 = 2
            if (r1 != r0) goto L_0x0508
            com.google.android.gms.internal.firebase-auth-api.zzajf r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajf) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r13, r14)
            int r1 = r14.zza
            int r1 = r1 + r0
        L_0x04ef:
            if (r0 >= r1) goto L_0x04ff
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r2 = r14.zza
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r2)
            r12.zzc(r2)
            goto L_0x04ef
        L_0x04ff:
            if (r0 != r1) goto L_0x0503
            goto L_0x0476
        L_0x0503:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x0508:
            if (r1 != 0) goto L_0x04d8
            com.google.android.gms.internal.firebase-auth-api.zzajf r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajf) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r13, r14)
            int r1 = r14.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r1)
            r12.zzc(r1)
        L_0x0519:
            if (r0 >= r7) goto L_0x0476
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r2 = r14.zza
            if (r11 != r2) goto L_0x0476
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1, r14)
            int r1 = r14.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r1)
            r12.zzc(r1)
            goto L_0x0519
        L_0x0531:
            r7 = r34
            r11 = r20
            r27 = r25
            r0 = 2
            if (r1 != r0) goto L_0x0540
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((byte[]) r15, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r12, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
        L_0x053e:
            r8 = r0
            goto L_0x0550
        L_0x0540:
            if (r1 != 0) goto L_0x04d8
            r0 = r11
            r1 = r32
            r2 = r13
            r3 = r34
            r4 = r12
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            goto L_0x053e
        L_0x0550:
            com.google.android.gms.internal.firebase-auth-api.zzaji r3 = r6.zzd((int) r10)
            r4 = 0
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r5 = r6.zzq
            r0 = r31
            r1 = r19
            r2 = r12
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(r0, r1, r2, r3, r4, r5)
            r0 = r8
            goto L_0x0476
        L_0x0562:
            r7 = r34
            r11 = r20
            r27 = r25
            r0 = 2
            if (r1 != r0) goto L_0x04d8
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r13, r14)
            int r1 = r14.zza
            if (r1 < 0) goto L_0x05bc
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x05b7
            if (r1 != 0) goto L_0x057f
            com.google.android.gms.internal.firebase-auth-api.zzaho r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza
            r12.add(r1)
            goto L_0x0587
        L_0x057f:
            com.google.android.gms.internal.firebase-auth-api.zzaho r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza((byte[]) r15, (int) r0, (int) r1)
            r12.add(r2)
        L_0x0586:
            int r0 = r0 + r1
        L_0x0587:
            if (r0 >= r7) goto L_0x0476
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r2 = r14.zza
            if (r11 != r2) goto L_0x0476
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1, r14)
            int r1 = r14.zza
            if (r1 < 0) goto L_0x05b2
            int r2 = r15.length
            int r2 = r2 - r0
            if (r1 > r2) goto L_0x05ad
            if (r1 != 0) goto L_0x05a5
            com.google.android.gms.internal.firebase-auth-api.zzaho r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza
            r12.add(r1)
            goto L_0x0587
        L_0x05a5:
            com.google.android.gms.internal.firebase-auth-api.zzaho r2 = com.google.android.gms.internal.p002firebaseauthapi.zzaho.zza((byte[]) r15, (int) r0, (int) r1)
            r12.add(r2)
            goto L_0x0586
        L_0x05ad:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x05b2:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x05b7:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x05bc:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x05c1:
            r7 = r34
            r11 = r20
            r27 = r25
            r0 = 2
            if (r1 != r0) goto L_0x04d8
            com.google.android.gms.internal.firebase-auth-api.zzalh r8 = r6.zze((int) r10)
            r9 = r11
            r5 = r10
            r10 = r32
            r4 = r11
            r3 = r13
            r11 = r3
            r13 = r12
            r12 = r34
            r0 = r14
            r14 = r36
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((com.google.android.gms.internal.p002firebaseauthapi.zzalh<?>) r8, (int) r9, (byte[]) r10, (int) r11, (int) r12, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
        L_0x05df:
            r14 = r0
            r0 = r1
            r10 = r3
            r8 = r4
            r9 = r5
            goto L_0x08c3
        L_0x05e6:
            r7 = r34
            r5 = r10
            r3 = r13
            r0 = r14
            r4 = r20
            r27 = r25
            r10 = 2
            r13 = r12
            if (r1 != r10) goto L_0x06a1
            r10 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r8 & r10
            int r1 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r1 != 0) goto L_0x0641
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3, r0)
            int r8 = r0.zza
            if (r8 < 0) goto L_0x063c
            if (r8 != 0) goto L_0x0609
            r13.add(r2)
            goto L_0x0614
        L_0x0609:
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r9.<init>(r15, r1, r8, r10)
            r13.add(r9)
        L_0x0613:
            int r1 = r1 + r8
        L_0x0614:
            if (r1 >= r7) goto L_0x05df
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1, r0)
            int r9 = r0.zza
            if (r4 != r9) goto L_0x05df
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r8, r0)
            int r8 = r0.zza
            if (r8 < 0) goto L_0x0637
            if (r8 != 0) goto L_0x062c
            r13.add(r2)
            goto L_0x0614
        L_0x062c:
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r9.<init>(r15, r1, r8, r10)
            r13.add(r9)
            goto L_0x0613
        L_0x0637:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x063c:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x0641:
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3, r0)
            int r8 = r0.zza
            if (r8 < 0) goto L_0x069c
            if (r8 != 0) goto L_0x064f
            r13.add(r2)
            goto L_0x0662
        L_0x064f:
            int r9 = r1 + r8
            boolean r10 = com.google.android.gms.internal.p002firebaseauthapi.zzamn.zzc(r15, r1, r9)
            if (r10 == 0) goto L_0x0697
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r10.<init>(r15, r1, r8, r11)
            r13.add(r10)
        L_0x0661:
            r1 = r9
        L_0x0662:
            if (r1 >= r7) goto L_0x05df
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1, r0)
            int r9 = r0.zza
            if (r4 != r9) goto L_0x05df
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r8, r0)
            int r8 = r0.zza
            if (r8 < 0) goto L_0x0692
            if (r8 != 0) goto L_0x067a
            r13.add(r2)
            goto L_0x0662
        L_0x067a:
            int r9 = r1 + r8
            boolean r10 = com.google.android.gms.internal.p002firebaseauthapi.zzamn.zzc(r15, r1, r9)
            if (r10 == 0) goto L_0x068d
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r10.<init>(r15, r1, r8, r11)
            r13.add(r10)
            goto L_0x0661
        L_0x068d:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzd()
            throw r0
        L_0x0692:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x0697:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzd()
            throw r0
        L_0x069c:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzf()
            throw r0
        L_0x06a1:
            r14 = r0
            r10 = r3
            r8 = r4
            r9 = r5
            goto L_0x08c2
        L_0x06a7:
            r7 = r34
            r5 = r10
            r3 = r13
            r0 = r14
            r4 = r20
            r27 = r25
            r2 = 2
            r13 = r12
            if (r1 != r2) goto L_0x06db
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzahm r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzahm) r12
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3, r0)
            int r2 = r0.zza
            int r2 = r2 + r1
        L_0x06be:
            if (r1 >= r2) goto L_0x06d2
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r1, r0)
            long r8 = r0.zzb
            int r8 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r8 == 0) goto L_0x06cc
            r8 = 1
            goto L_0x06ce
        L_0x06cc:
            r8 = r16
        L_0x06ce:
            r12.zza((boolean) r8)
            goto L_0x06be
        L_0x06d2:
            if (r1 != r2) goto L_0x06d6
            goto L_0x05df
        L_0x06d6:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x06db:
            if (r1 != 0) goto L_0x06a1
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzahm r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzahm) r12
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r3, r0)
            long r8 = r0.zzb
            int r2 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r2 == 0) goto L_0x06ec
            r2 = 1
            goto L_0x06ee
        L_0x06ec:
            r2 = r16
        L_0x06ee:
            r12.zza((boolean) r2)
        L_0x06f1:
            if (r1 >= r7) goto L_0x05df
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1, r0)
            int r8 = r0.zza
            if (r4 != r8) goto L_0x05df
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r2, r0)
            long r8 = r0.zzb
            int r2 = (r8 > r22 ? 1 : (r8 == r22 ? 0 : -1))
            if (r2 == 0) goto L_0x0707
            r2 = 1
            goto L_0x0709
        L_0x0707:
            r2 = r16
        L_0x0709:
            r12.zza((boolean) r2)
            goto L_0x06f1
        L_0x070d:
            r7 = r34
            r5 = r10
            r3 = r13
            r0 = r14
            r4 = r20
            r27 = r25
            r2 = 2
            r13 = r12
            if (r1 != r2) goto L_0x0739
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzajf r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajf) r12
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3, r0)
            int r2 = r0.zza
            int r2 = r2 + r1
        L_0x0724:
            if (r1 >= r2) goto L_0x0730
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1)
            r12.zzc(r8)
            int r1 = r1 + 4
            goto L_0x0724
        L_0x0730:
            if (r1 != r2) goto L_0x0734
            goto L_0x05df
        L_0x0734:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x0739:
            r2 = 5
            if (r1 != r2) goto L_0x06a1
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzajf r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajf) r12
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3)
            r12.zzc(r1)
            int r11 = r3 + 4
        L_0x0748:
            if (r11 >= r7) goto L_0x075c
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r11, r0)
            int r2 = r0.zza
            if (r4 != r2) goto L_0x075c
            int r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r1)
            r12.zzc(r2)
            int r11 = r1 + 4
            goto L_0x0748
        L_0x075c:
            r14 = r0
            r10 = r3
            r8 = r4
            r9 = r5
        L_0x0760:
            r0 = r11
            goto L_0x08c3
        L_0x0763:
            r7 = r34
            r5 = r10
            r3 = r13
            r0 = r14
            r4 = r20
            r27 = r25
            r2 = 2
            r13 = r12
            if (r1 != r2) goto L_0x078f
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r3, r0)
            int r2 = r0.zza
            int r2 = r2 + r1
        L_0x077a:
            if (r1 >= r2) goto L_0x0786
            long r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r1)
            r12.zza((long) r8)
            int r1 = r1 + 8
            goto L_0x077a
        L_0x0786:
            if (r1 != r2) goto L_0x078a
            goto L_0x05df
        L_0x078a:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x078f:
            r2 = 1
            if (r1 != r2) goto L_0x06a1
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            long r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r3)
            r12.zza((long) r1)
            int r11 = r3 + 8
        L_0x079e:
            if (r11 >= r7) goto L_0x075c
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r11, r0)
            int r2 = r0.zza
            if (r4 != r2) goto L_0x075c
            long r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r1)
            r12.zza((long) r8)
            int r11 = r1 + 8
            goto L_0x079e
        L_0x07b2:
            r7 = r34
            r5 = r10
            r3 = r13
            r0 = r14
            r4 = r20
            r27 = r25
            r2 = 2
            r13 = r12
            if (r1 != r2) goto L_0x07c5
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((byte[]) r15, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r0)
            goto L_0x05df
        L_0x07c5:
            if (r1 != 0) goto L_0x06a1
            r14 = r0
            r0 = r4
            r1 = r32
            r2 = r3
            r10 = r3
            r3 = r34
            r8 = r4
            r4 = r13
            r9 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzajl<?>) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            goto L_0x08c3
        L_0x07da:
            r7 = r34
            r9 = r10
            r10 = r13
            r8 = r20
            r27 = r25
            r0 = 2
            r13 = r12
            if (r1 != r0) goto L_0x0805
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            int r1 = r1 + r0
        L_0x07f0:
            if (r0 >= r1) goto L_0x07fc
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r0, r14)
            long r2 = r14.zzb
            r12.zza((long) r2)
            goto L_0x07f0
        L_0x07fc:
            if (r0 != r1) goto L_0x0800
            goto L_0x08c3
        L_0x0800:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x0805:
            if (r1 != 0) goto L_0x08c2
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaka r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaka) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r10, r14)
            long r1 = r14.zzb
            r12.zza((long) r1)
        L_0x0813:
            if (r0 >= r7) goto L_0x08c3
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r0, r14)
            int r2 = r14.zza
            if (r8 != r2) goto L_0x08c3
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r1, r14)
            long r1 = r14.zzb
            r12.zza((long) r1)
            goto L_0x0813
        L_0x0827:
            r7 = r34
            r9 = r10
            r10 = r13
            r8 = r20
            r27 = r25
            r0 = 2
            r13 = r12
            if (r1 != r0) goto L_0x0852
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzajd r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajd) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            int r1 = r1 + r0
        L_0x083d:
            if (r0 >= r1) goto L_0x0849
            float r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r15, r0)
            r12.zza((float) r2)
            int r0 = r0 + 4
            goto L_0x083d
        L_0x0849:
            if (r0 != r1) goto L_0x084d
            goto L_0x08c3
        L_0x084d:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x0852:
            r0 = 5
            if (r1 != r0) goto L_0x08c2
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzajd r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzajd) r12
            float r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r15, r10)
            r12.zza((float) r0)
            int r11 = r10 + 4
        L_0x0861:
            if (r11 >= r7) goto L_0x0760
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r11, r14)
            int r1 = r14.zza
            if (r8 != r1) goto L_0x0760
            float r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r15, r0)
            r12.zza((float) r1)
            int r11 = r0 + 4
            goto L_0x0861
        L_0x0875:
            r7 = r34
            r9 = r10
            r10 = r13
            r8 = r20
            r27 = r25
            r0 = 2
            r13 = r12
            if (r1 != r0) goto L_0x089f
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaio r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaio) r12
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            int r1 = r1 + r0
        L_0x088b:
            if (r0 >= r1) goto L_0x0897
            double r2 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r15, r0)
            r12.zza((double) r2)
            int r0 = r0 + 8
            goto L_0x088b
        L_0x0897:
            if (r0 != r1) goto L_0x089a
            goto L_0x08c3
        L_0x089a:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x089f:
            r0 = 1
            if (r1 != r0) goto L_0x08c2
            r12 = r13
            com.google.android.gms.internal.firebase-auth-api.zzaio r12 = (com.google.android.gms.internal.p002firebaseauthapi.zzaio) r12
            double r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r15, r10)
            r12.zza((double) r0)
            int r11 = r10 + 8
        L_0x08ae:
            if (r11 >= r7) goto L_0x0760
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r11, r14)
            int r1 = r14.zza
            if (r8 != r1) goto L_0x0760
            double r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r15, r0)
            r12.zza((double) r1)
            int r11 = r0 + 8
            goto L_0x08ae
        L_0x08c2:
            r0 = r10
        L_0x08c3:
            if (r0 != r10) goto L_0x08d2
            r7 = r31
            r2 = r0
            r5 = r6
            r10 = r9
            r13 = r14
            r14 = r15
            r4 = r19
        L_0x08ce:
            r6 = r35
            goto L_0x0c7b
        L_0x08d2:
            r5 = r35
            r4 = r7
            r11 = r8
            r10 = r9
            r2 = r14
            r9 = r19
            r13 = r21
            r14 = r26
            r3 = r27
            r7 = r31
        L_0x08e2:
            r8 = r0
            goto L_0x001d
        L_0x08e5:
            r7 = r34
            r27 = r3
            r8 = r20
            r29 = r13
            r13 = r10
            r10 = r29
            r3 = 50
            if (r4 != r3) goto L_0x0a06
            r3 = 2
            if (r1 != r3) goto L_0x09f3
            sun.misc.Unsafe r0 = zzb
            java.lang.Object r1 = r6.zzf((int) r13)
            r9 = r7
            r7 = r31
            java.lang.Object r2 = r0.getObject(r7, r11)
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r6.zzs
            boolean r3 = r3.zzf(r2)
            if (r3 == 0) goto L_0x091b
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r6.zzs
            java.lang.Object r3 = r3.zzb(r1)
            com.google.android.gms.internal.firebase-auth-api.zzaki r4 = r6.zzs
            r4.zza(r3, r2)
            r0.putObject(r7, r11, r3)
            r2 = r3
        L_0x091b:
            com.google.android.gms.internal.firebase-auth-api.zzaki r0 = r6.zzs
            com.google.android.gms.internal.firebase-auth-api.zzakg r11 = r0.zza(r1)
            com.google.android.gms.internal.firebase-auth-api.zzaki r0 = r6.zzs
            java.util.Map r12 = r0.zze(r2)
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r15, r10, r14)
            int r1 = r14.zza
            if (r1 < 0) goto L_0x09ee
            int r2 = r9 - r0
            if (r1 > r2) goto L_0x09ee
            int r5 = r0 + r1
            K r1 = r11.zzb
            V r2 = r11.zzd
            r4 = r1
            r3 = r2
        L_0x093b:
            if (r0 >= r5) goto L_0x09bd
            int r1 = r0 + 1
            byte r0 = r15[r0]
            if (r0 >= 0) goto L_0x094e
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r15, (int) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            int r1 = r14.zza
            r29 = r1
            r1 = r0
            r0 = r29
        L_0x094e:
            int r2 = r0 >>> 3
            r33 = r3
            r3 = r0 & 7
            r17 = r5
            r5 = 1
            if (r2 == r5) goto L_0x0996
            r5 = 2
            if (r2 == r5) goto L_0x0964
            r2 = r33
            r24 = r8
            r6 = r17
            r8 = r4
            goto L_0x09b7
        L_0x0964:
            com.google.android.gms.internal.firebase-auth-api.zzamt r2 = r11.zzc
            int r2 = r2.zza()
            if (r3 != r2) goto L_0x098e
            com.google.android.gms.internal.firebase-auth-api.zzamt r3 = r11.zzc
            V r0 = r11.zzd
            java.lang.Class r20 = r0.getClass()
            r0 = r32
            r2 = r34
            r24 = r8
            r8 = r4
            r4 = r20
            r6 = r17
            r5 = r36
            int r0 = zza((byte[]) r0, (int) r1, (int) r2, (com.google.android.gms.internal.p002firebaseauthapi.zzamt) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            java.lang.Object r3 = r14.zzc
        L_0x0987:
            r5 = r6
            r4 = r8
        L_0x0989:
            r8 = r24
            r6 = r30
            goto L_0x093b
        L_0x098e:
            r24 = r8
            r6 = r17
            r8 = r4
        L_0x0993:
            r2 = r33
            goto L_0x09b7
        L_0x0996:
            r24 = r8
            r6 = r17
            r8 = r4
            com.google.android.gms.internal.firebase-auth-api.zzamt r2 = r11.zza
            int r2 = r2.zza()
            if (r3 != r2) goto L_0x0993
            com.google.android.gms.internal.firebase-auth-api.zzamt r3 = r11.zza
            r4 = 0
            r0 = r32
            r2 = r34
            r8 = r33
            r5 = r36
            int r0 = zza((byte[]) r0, (int) r1, (int) r2, (com.google.android.gms.internal.p002firebaseauthapi.zzamt) r3, (java.lang.Class<?>) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            java.lang.Object r4 = r14.zzc
            r5 = r6
            r3 = r8
            goto L_0x0989
        L_0x09b7:
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r15, (int) r1, (int) r9, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            r3 = r2
            goto L_0x0987
        L_0x09bd:
            r2 = r3
            r6 = r5
            r24 = r8
            r8 = r4
            if (r0 != r6) goto L_0x09e9
            r12.put(r8, r2)
            if (r6 != r10) goto L_0x09d5
            r5 = r30
            r2 = r6
            r10 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r8 = r24
            goto L_0x08ce
        L_0x09d5:
            r5 = r35
            r8 = r6
            r4 = r9
            r10 = r13
            r2 = r14
            r9 = r19
            r13 = r21
            r11 = r24
            r14 = r26
            r3 = r27
            r6 = r30
            goto L_0x001d
        L_0x09e9:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg()
            throw r0
        L_0x09ee:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzi()
            throw r0
        L_0x09f3:
            r9 = r7
            r24 = r8
            r7 = r31
        L_0x09f8:
            r5 = r30
            r6 = r35
            r2 = r10
            r10 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r8 = r24
            goto L_0x0c7b
        L_0x0a06:
            r6 = r7
            r24 = r8
            r7 = r31
            sun.misc.Unsafe r3 = zzb
            int r5 = r13 + 2
            r5 = r9[r5]
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r9
            r20 = r10
            long r9 = (long) r5
            switch(r4) {
                case 51: goto L_0x0c52;
                case 52: goto L_0x0c31;
                case 53: goto L_0x0c12;
                case 54: goto L_0x0c12;
                case 55: goto L_0x0bf2;
                case 56: goto L_0x0bd2;
                case 57: goto L_0x0bb0;
                case 58: goto L_0x0b87;
                case 59: goto L_0x0b48;
                case 60: goto L_0x0b1a;
                case 61: goto L_0x0afe;
                case 62: goto L_0x0bf2;
                case 63: goto L_0x0ac0;
                case 64: goto L_0x0bb0;
                case 65: goto L_0x0bd2;
                case 66: goto L_0x0a9d;
                case 67: goto L_0x0a6f;
                case 68: goto L_0x0a29;
                default: goto L_0x0a1b;
            }
        L_0x0a1b:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r19 = r13
            r13 = r14
            r14 = r15
            goto L_0x0c73
        L_0x0a29:
            r0 = 3
            if (r1 != r0) goto L_0x0a61
            r5 = r30
            r4 = r19
            java.lang.Object r0 = r5.zza(r7, (int) r4, (int) r13)
            r1 = r24 & -8
            r1 = r1 | 4
            com.google.android.gms.internal.firebase-auth-api.zzalh r9 = r5.zze((int) r13)
            r2 = r24
            r8 = r0
            r3 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r20
            r10 = r32
            r11 = r12
            r6 = r12
            r12 = r34
            r19 = r2
            r2 = r13
            r13 = r1
            r1 = r14
            r14 = r36
            int r8 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((java.lang.Object) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r9, (byte[]) r10, (int) r11, (int) r12, (int) r13, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r14)
            r5.zza(r7, (int) r4, (int) r2, (java.lang.Object) r0)
            r13 = r1
            r0 = r8
            r14 = r15
        L_0x0a5b:
            r8 = r19
        L_0x0a5d:
            r19 = r2
            goto L_0x0c74
        L_0x0a61:
            r4 = r19
            r6 = r20
            r5 = r30
            r19 = r13
            r13 = r14
            r14 = r15
            r8 = r24
            goto L_0x0c73
        L_0x0a6f:
            r5 = r30
            r2 = r13
            r13 = r14
            r4 = r19
            r6 = r20
            r19 = r24
            r14 = 1048575(0xfffff, float:1.469367E-39)
            if (r1 != 0) goto L_0x0a95
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r15, r6, r13)
            long r14 = r13.zzb
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((long) r14)
            java.lang.Long r1 = java.lang.Long.valueOf(r14)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            r14 = r32
            goto L_0x0a5b
        L_0x0a95:
            r14 = r32
        L_0x0a97:
            r8 = r19
        L_0x0a99:
            r19 = r2
            goto L_0x0c73
        L_0x0a9d:
            r5 = r30
            r2 = r13
            r13 = r14
            r4 = r19
            r6 = r20
            r19 = r24
            if (r1 != 0) goto L_0x0a95
            r14 = r32
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r14, r6, r13)
            int r1 = r13.zza
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaic.zza((int) r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0a5b
        L_0x0ac0:
            r5 = r30
            r2 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r6 = r20
            r19 = r24
            if (r1 != 0) goto L_0x0a97
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r14, r6, r13)
            int r1 = r13.zza
            com.google.android.gms.internal.firebase-auth-api.zzaji r8 = r5.zzd((int) r2)
            if (r8 == 0) goto L_0x0adf
            boolean r8 = r8.zza(r1)
            if (r8 == 0) goto L_0x0ae2
        L_0x0adf:
            r8 = r19
            goto L_0x0af2
        L_0x0ae2:
            com.google.android.gms.internal.firebase-auth-api.zzamf r3 = zze((java.lang.Object) r31)
            long r8 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r8)
            r8 = r19
            r3.zza((int) r8, (java.lang.Object) r1)
            goto L_0x0a5d
        L_0x0af2:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0a5d
        L_0x0afe:
            r5 = r30
            r2 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r6 = r20
            r8 = r24
            r15 = 2
            if (r1 != r15) goto L_0x0a99
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r14, r6, r13)
            java.lang.Object r1 = r13.zzc
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0a5d
        L_0x0b1a:
            r5 = r30
            r2 = r13
            r13 = r14
            r14 = r15
            r4 = r19
            r6 = r20
            r8 = r24
            r15 = 2
            if (r1 != r15) goto L_0x0a99
            java.lang.Object r9 = r5.zza(r7, (int) r4, (int) r2)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r5.zze((int) r2)
            r0 = r9
            r10 = r2
            r2 = r32
            r3 = r6
            r11 = r4
            r4 = r34
            r12 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1, (byte[]) r2, (int) r3, (int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            r12.zza(r7, (int) r11, (int) r10, (java.lang.Object) r9)
            r19 = r10
            r4 = r11
            r5 = r12
            goto L_0x0c74
        L_0x0b48:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r19 = r13
            r13 = r14
            r14 = r15
            r15 = 2
            if (r1 != r15) goto L_0x0c73
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r14, r6, r13)
            int r15 = r13.zza
            if (r15 != 0) goto L_0x0b63
            r3.putObject(r7, r11, r2)
            goto L_0x0b81
        L_0x0b63:
            r2 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r2
            if (r0 == 0) goto L_0x0b76
            int r0 = r1 + r15
            boolean r0 = com.google.android.gms.internal.p002firebaseauthapi.zzamn.zzc(r14, r1, r0)
            if (r0 == 0) goto L_0x0b71
            goto L_0x0b76
        L_0x0b71:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzd()
            throw r0
        L_0x0b76:
            java.lang.String r0 = new java.lang.String
            java.nio.charset.Charset r2 = com.google.android.gms.internal.p002firebaseauthapi.zzajh.zza
            r0.<init>(r14, r1, r15, r2)
            r3.putObject(r7, r11, r0)
            int r1 = r1 + r15
        L_0x0b81:
            r3.putInt(r7, r9, r4)
            r0 = r1
            goto L_0x0c74
        L_0x0b87:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != 0) goto L_0x0c73
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r14, r6, r13)
            long r1 = r13.zzb
            int r1 = (r1 > r22 ? 1 : (r1 == r22 ? 0 : -1))
            if (r1 == 0) goto L_0x0ba2
            r28 = 1
            goto L_0x0ba4
        L_0x0ba2:
            r28 = r16
        L_0x0ba4:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r28)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0c74
        L_0x0bb0:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r0 = 5
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != r0) goto L_0x0c73
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r14, r6)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.putObject(r7, r11, r0)
            int r11 = r6 + 4
            r3.putInt(r7, r9, r4)
        L_0x0bcf:
            r0 = r11
            goto L_0x0c74
        L_0x0bd2:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r0 = 1
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != r0) goto L_0x0c73
            long r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r14, r6)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r3.putObject(r7, r11, r0)
            int r11 = r6 + 8
            r3.putInt(r7, r9, r4)
            goto L_0x0bcf
        L_0x0bf2:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != 0) goto L_0x0c73
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzc(r14, r6, r13)
            int r1 = r13.zza
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0c74
        L_0x0c12:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != 0) goto L_0x0c73
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzd(r14, r6, r13)
            long r1 = r13.zzb
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            r3.putObject(r7, r11, r1)
            r3.putInt(r7, r9, r4)
            goto L_0x0c74
        L_0x0c31:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r0 = 5
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != r0) goto L_0x0c73
            float r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zzb(r14, r6)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r3.putObject(r7, r11, r0)
            int r11 = r6 + 4
            r3.putInt(r7, r9, r4)
            goto L_0x0bcf
        L_0x0c52:
            r5 = r30
            r4 = r19
            r6 = r20
            r8 = r24
            r0 = 1
            r19 = r13
            r13 = r14
            r14 = r15
            if (r1 != r0) goto L_0x0c73
            double r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza(r14, r6)
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r3.putObject(r7, r11, r0)
            int r11 = r6 + 8
            r3.putInt(r7, r9, r4)
            goto L_0x0bcf
        L_0x0c73:
            r0 = r6
        L_0x0c74:
            if (r0 != r6) goto L_0x0ce0
            r6 = r35
            r2 = r0
            r10 = r19
        L_0x0c7b:
            if (r8 != r6) goto L_0x0c8c
            if (r6 != 0) goto L_0x0c80
            goto L_0x0c8c
        L_0x0c80:
            r9 = r5
            r11 = r8
            r13 = r21
            r14 = r26
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r2
            goto L_0x0cfd
        L_0x0c8c:
            boolean r0 = r5.zzh
            if (r0 == 0) goto L_0x0cce
            com.google.android.gms.internal.firebase-auth-api.zzaiq r0 = r13.zzd
            com.google.android.gms.internal.firebase-auth-api.zzaiq r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaiq.zza
            if (r0 == r1) goto L_0x0cce
            com.google.android.gms.internal.firebase-auth-api.zzakp r1 = r5.zzg
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzd r0 = r0.zza(r1, r4)
            if (r0 != 0) goto L_0x0cc0
            com.google.android.gms.internal.firebase-auth-api.zzamf r9 = zze((java.lang.Object) r31)
            r0 = r8
            r1 = r32
            r3 = r34
            r11 = r4
            r4 = r9
            r9 = r5
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzamf) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
        L_0x0cb0:
            r4 = r34
            r5 = r6
            r6 = r9
            r9 = r11
            r2 = r13
            r15 = r14
            r13 = r21
            r14 = r26
            r3 = r27
        L_0x0cbd:
            r11 = r8
            goto L_0x08e2
        L_0x0cc0:
            r0 = r7
            com.google.android.gms.internal.firebase-auth-api.zzaje$zzb r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaje.zzb) r0
            r0.zza()
            com.google.android.gms.internal.firebase-auth-api.zzaix<com.google.android.gms.internal.firebase-auth-api.zzaje$zze> r0 = r0.zzc
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0cce:
            r11 = r4
            r9 = r5
            com.google.android.gms.internal.firebase-auth-api.zzamf r4 = zze((java.lang.Object) r31)
            r0 = r8
            r1 = r32
            r3 = r34
            r5 = r36
            int r0 = com.google.android.gms.internal.p002firebaseauthapi.zzahk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzamf) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzahn) r5)
            goto L_0x0cb0
        L_0x0ce0:
            r11 = r4
            r4 = r34
            r6 = r5
            r9 = r11
            r2 = r13
            r15 = r14
            r10 = r19
            r13 = r21
            r14 = r26
            r3 = r27
            r5 = r35
            goto L_0x0cbd
        L_0x0cf2:
            r27 = r3
            r9 = r6
            r21 = r13
            r26 = r14
            r6 = r5
            r0 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0cfd:
            if (r14 == r0) goto L_0x0d05
            long r0 = (long) r14
            r2 = r27
            r2.putInt(r7, r0, r13)
        L_0x0d05:
            int r0 = r9.zzm
            r1 = 0
            r10 = r0
            r3 = r1
        L_0x0d0a:
            int r0 = r9.zzn
            if (r10 >= r0) goto L_0x0d24
            int[] r0 = r9.zzl
            r2 = r0[r10]
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r4 = r9.zzq
            r0 = r30
            r1 = r31
            r5 = r31
            java.lang.Object r0 = r0.zza((java.lang.Object) r1, (int) r2, r3, r4, (java.lang.Object) r5)
            r3 = r0
            com.google.android.gms.internal.firebase-auth-api.zzamf r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzamf) r3
            int r10 = r10 + 1
            goto L_0x0d0a
        L_0x0d24:
            if (r3 == 0) goto L_0x0d2b
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r9.zzq
            r0.zzb((java.lang.Object) r7, r3)
        L_0x0d2b:
            if (r6 != 0) goto L_0x0d37
            r0 = r34
            if (r8 != r0) goto L_0x0d32
            goto L_0x0d3d
        L_0x0d32:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg()
            throw r0
        L_0x0d37:
            r0 = r34
            if (r8 > r0) goto L_0x0d3e
            if (r11 != r6) goto L_0x0d3e
        L_0x0d3d:
            return r8
        L_0x0d3e:
            com.google.android.gms.internal.firebase-auth-api.zzajk r0 = com.google.android.gms.internal.p002firebaseauthapi.zzajk.zzg()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.firebase-auth-api.zzahn):int");
    }

    private final int zza(int i3) {
        if (i3 < this.zze || i3 > this.zzf) {
            return -1;
        }
        return zza(i3, 0);
    }

    private final int zza(int i3, int i4) {
        int length = (this.zzc.length / 3) - 1;
        while (i4 <= length) {
            int i5 = (length + i4) >>> 1;
            int i6 = i5 * 3;
            int i7 = this.zzc[i6];
            if (i3 == i7) {
                return i6;
            }
            if (i3 < i7) {
                length = i5 - 1;
            } else {
                i4 = i5 + 1;
            }
        }
        return -1;
    }

    /* JADX WARNING: Removed duplicated region for block: B:122:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0279  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> com.google.android.gms.internal.p002firebaseauthapi.zzakt<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.p002firebaseauthapi.zzakn r34, com.google.android.gms.internal.p002firebaseauthapi.zzakx r35, com.google.android.gms.internal.p002firebaseauthapi.zzaju r36, com.google.android.gms.internal.p002firebaseauthapi.zzamg<?, ?> r37, com.google.android.gms.internal.p002firebaseauthapi.zzais<?> r38, com.google.android.gms.internal.p002firebaseauthapi.zzaki r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.p002firebaseauthapi.zzalf
            if (r1 == 0) goto L_0x0406
            com.google.android.gms.internal.firebase-auth-api.zzalf r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzalf) r0
            java.lang.String r1 = r0.zzd()
            int r2 = r1.length()
            r3 = 0
            char r4 = r1.charAt(r3)
            r5 = 55296(0xd800, float:7.7486E-41)
            r6 = 1
            if (r4 < r5) goto L_0x0026
            r4 = r6
        L_0x001c:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0027
            r4 = r7
            goto L_0x001c
        L_0x0026:
            r7 = r6
        L_0x0027:
            int r4 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0046
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0033:
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0043
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r9
            r7 = r7 | r4
            int r9 = r9 + 13
            r4 = r10
            goto L_0x0033
        L_0x0043:
            int r4 = r4 << r9
            r7 = r7 | r4
            r4 = r10
        L_0x0046:
            if (r7 != 0) goto L_0x0058
            int[] r7 = zza
            r11 = r3
            r12 = r11
            r13 = r12
            r14 = r13
            r16 = r14
            r18 = r16
            r17 = r7
            r7 = r18
            goto L_0x0168
        L_0x0058:
            int r7 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x0077
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0064:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0074
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r4 = r4 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x0064
        L_0x0074:
            int r7 = r7 << r9
            r4 = r4 | r7
            r7 = r10
        L_0x0077:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r5) goto L_0x0096
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0083:
            int r11 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x0093
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0083
        L_0x0093:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r11
        L_0x0096:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r5) goto L_0x00b5
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00a2:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00b2
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00a2
        L_0x00b2:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00b5:
            int r11 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r5) goto L_0x00d4
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00c1:
            int r13 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00d1
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00c1
        L_0x00d1:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00d4:
            int r12 = r11 + 1
            char r11 = r1.charAt(r11)
            if (r11 < r5) goto L_0x00f3
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00e0:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x00f0
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00e0
        L_0x00f0:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x00f3:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r5) goto L_0x0112
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00ff:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x010f
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00ff
        L_0x010f:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0112:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r5) goto L_0x0133
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x011e:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x012f
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x011e
        L_0x012f:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0133:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r5) goto L_0x0156
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x013f:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r5) goto L_0x0151
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x013f
        L_0x0151:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x0156:
            int r16 = r14 + r12
            int r13 = r16 + r13
            int[] r13 = new int[r13]
            int r16 = r4 << 1
            int r16 = r16 + r7
            r7 = r4
            r17 = r13
            r18 = r14
            r4 = r15
            r13 = r9
            r14 = r10
        L_0x0168:
            sun.misc.Unsafe r9 = zzb
            java.lang.Object[] r10 = r0.zze()
            com.google.android.gms.internal.firebase-auth-api.zzakp r15 = r0.zza()
            java.lang.Class r15 = r15.getClass()
            int r3 = r11 * 3
            int[] r3 = new int[r3]
            int r11 = r11 << r6
            java.lang.Object[] r11 = new java.lang.Object[r11]
            int r19 = r18 + r12
            r21 = r18
            r22 = r19
            r12 = 0
            r20 = 0
        L_0x0186:
            if (r4 >= r2) goto L_0x03e0
            int r23 = r4 + 1
            char r4 = r1.charAt(r4)
            if (r4 < r5) goto L_0x01ae
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r8 = r23
            r23 = 13
        L_0x0196:
            int r24 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r5) goto L_0x01a8
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r23
            r4 = r4 | r8
            int r23 = r23 + 13
            r8 = r24
            goto L_0x0196
        L_0x01a8:
            int r8 = r8 << r23
            r4 = r4 | r8
            r8 = r24
            goto L_0x01b0
        L_0x01ae:
            r8 = r23
        L_0x01b0:
            int r23 = r8 + 1
            char r8 = r1.charAt(r8)
            if (r8 < r5) goto L_0x01d6
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r6 = r23
            r23 = 13
        L_0x01be:
            int r25 = r6 + 1
            char r6 = r1.charAt(r6)
            if (r6 < r5) goto L_0x01d0
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r23
            r8 = r8 | r6
            int r23 = r23 + 13
            r6 = r25
            goto L_0x01be
        L_0x01d0:
            int r6 = r6 << r23
            r8 = r8 | r6
            r6 = r25
            goto L_0x01d8
        L_0x01d6:
            r6 = r23
        L_0x01d8:
            r5 = r8 & 255(0xff, float:3.57E-43)
            r25 = r2
            r2 = r8 & 1024(0x400, float:1.435E-42)
            if (r2 == 0) goto L_0x01e6
            int r2 = r20 + 1
            r17[r20] = r12
            r20 = r2
        L_0x01e6:
            r2 = 51
            r28 = r14
            if (r5 < r2) goto L_0x0293
            int r2 = r6 + 1
            char r6 = r1.charAt(r6)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r14) goto L_0x0215
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r30 = 13
        L_0x01fb:
            int r31 = r2 + 1
            char r2 = r1.charAt(r2)
            if (r2 < r14) goto L_0x0210
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r30
            r6 = r6 | r2
            int r30 = r30 + 13
            r2 = r31
            r14 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01fb
        L_0x0210:
            int r2 = r2 << r30
            r6 = r6 | r2
            r2 = r31
        L_0x0215:
            int r14 = r5 + -51
            r30 = r2
            r2 = 9
            if (r14 == r2) goto L_0x0221
            r2 = 17
            if (r14 != r2) goto L_0x0223
        L_0x0221:
            r14 = 1
            goto L_0x0248
        L_0x0223:
            r2 = 12
            if (r14 != r2) goto L_0x0238
            com.google.android.gms.internal.firebase-auth-api.zzala r2 = r0.zzb()
            com.google.android.gms.internal.firebase-auth-api.zzala r14 = com.google.android.gms.internal.p002firebaseauthapi.zzala.PROTO2
            boolean r2 = r2.equals(r14)
            if (r2 != 0) goto L_0x023a
            r2 = r8 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x0238
            goto L_0x023a
        L_0x0238:
            r14 = 1
            goto L_0x0253
        L_0x023a:
            int r2 = r12 / 3
            r14 = 1
            int r2 = r2 << r14
            int r2 = r2 + r14
            int r24 = r16 + 1
            r16 = r10[r16]
            r11[r2] = r16
        L_0x0245:
            r16 = r24
            goto L_0x0253
        L_0x0248:
            int r2 = r12 / 3
            int r2 = r2 << r14
            int r2 = r2 + r14
            int r24 = r16 + 1
            r16 = r10[r16]
            r11[r2] = r16
            goto L_0x0245
        L_0x0253:
            int r2 = r6 << 1
            r6 = r10[r2]
            boolean r14 = r6 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0260
            java.lang.reflect.Field r6 = (java.lang.reflect.Field) r6
        L_0x025d:
            r31 = r13
            goto L_0x0269
        L_0x0260:
            java.lang.String r6 = (java.lang.String) r6
            java.lang.reflect.Field r6 = zza((java.lang.Class<?>) r15, (java.lang.String) r6)
            r10[r2] = r6
            goto L_0x025d
        L_0x0269:
            long r13 = r9.objectFieldOffset(r6)
            int r6 = (int) r13
            int r2 = r2 + 1
            r13 = r10[r2]
            boolean r14 = r13 instanceof java.lang.reflect.Field
            if (r14 == 0) goto L_0x0279
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x0281
        L_0x0279:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zza((java.lang.Class<?>) r15, (java.lang.String) r13)
            r10[r2] = r13
        L_0x0281:
            long r13 = r9.objectFieldOffset(r13)
            int r2 = (int) r13
            r26 = r0
            r27 = r1
            r24 = r16
            r13 = r30
            r0 = 0
            r16 = 1
            goto L_0x039b
        L_0x0293:
            r31 = r13
            int r2 = r16 + 1
            r13 = r10[r16]
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zza((java.lang.Class<?>) r15, (java.lang.String) r13)
            r14 = 9
            if (r5 == r14) goto L_0x02a7
            r14 = 17
            if (r5 != r14) goto L_0x02ac
        L_0x02a7:
            r26 = r0
            r14 = 1
            goto L_0x0318
        L_0x02ac:
            r14 = 27
            if (r5 == r14) goto L_0x02b4
            r14 = 49
            if (r5 != r14) goto L_0x02b8
        L_0x02b4:
            r26 = r0
            r14 = 1
            goto L_0x030d
        L_0x02b8:
            r14 = 12
            if (r5 == r14) goto L_0x02f1
            r14 = 30
            if (r5 == r14) goto L_0x02f1
            r14 = 44
            if (r5 != r14) goto L_0x02c5
            goto L_0x02f1
        L_0x02c5:
            r14 = 50
            if (r5 != r14) goto L_0x02ee
            int r14 = r21 + 1
            r17[r21] = r12
            int r21 = r12 / 3
            r24 = 1
            int r21 = r21 << 1
            int r26 = r16 + 2
            r2 = r10[r2]
            r11[r21] = r2
            r2 = r8 & 2048(0x800, float:2.87E-42)
            if (r2 == 0) goto L_0x02ea
            int r21 = r21 + 1
            int r2 = r16 + 3
            r16 = r10[r26]
            r11[r21] = r16
            r26 = r0
            r21 = r14
            goto L_0x0322
        L_0x02ea:
            r21 = r14
            r2 = r26
        L_0x02ee:
            r26 = r0
            goto L_0x0322
        L_0x02f1:
            com.google.android.gms.internal.firebase-auth-api.zzala r14 = r0.zzb()
            r26 = r0
            com.google.android.gms.internal.firebase-auth-api.zzala r0 = com.google.android.gms.internal.p002firebaseauthapi.zzala.PROTO2
            if (r14 == r0) goto L_0x02ff
            r0 = r8 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0322
        L_0x02ff:
            int r0 = r12 / 3
            r14 = 1
            int r0 = r0 << r14
            int r0 = r0 + r14
            int r16 = r16 + 2
            r2 = r10[r2]
            r11[r0] = r2
        L_0x030a:
            r2 = r16
            goto L_0x0322
        L_0x030d:
            int r0 = r12 / 3
            int r0 = r0 << r14
            int r0 = r0 + r14
            int r16 = r16 + 2
            r2 = r10[r2]
            r11[r0] = r2
            goto L_0x030a
        L_0x0318:
            int r0 = r12 / 3
            int r0 = r0 << r14
            int r0 = r0 + r14
            java.lang.Class r14 = r13.getType()
            r11[r0] = r14
        L_0x0322:
            long r13 = r9.objectFieldOffset(r13)
            int r0 = (int) r13
            r13 = r8 & 4096(0x1000, float:5.74E-42)
            if (r13 == 0) goto L_0x037c
            r13 = 17
            if (r5 > r13) goto L_0x037c
            int r13 = r6 + 1
            char r6 = r1.charAt(r6)
            r14 = 55296(0xd800, float:7.7486E-41)
            if (r6 < r14) goto L_0x0355
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x033e:
            int r23 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r14) goto L_0x0350
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r16
            r6 = r6 | r13
            int r16 = r16 + 13
            r13 = r23
            goto L_0x033e
        L_0x0350:
            int r13 = r13 << r16
            r6 = r6 | r13
            r13 = r23
        L_0x0355:
            r16 = 1
            int r23 = r7 << 1
            int r24 = r6 / 32
            int r24 = r24 + r23
            r14 = r10[r24]
            r27 = r1
            boolean r1 = r14 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x036a
            java.lang.reflect.Field r14 = (java.lang.reflect.Field) r14
        L_0x0367:
            r24 = r2
            goto L_0x0373
        L_0x036a:
            java.lang.String r14 = (java.lang.String) r14
            java.lang.reflect.Field r14 = zza((java.lang.Class<?>) r15, (java.lang.String) r14)
            r10[r24] = r14
            goto L_0x0367
        L_0x0373:
            long r1 = r9.objectFieldOffset(r14)
            int r1 = (int) r1
            int r6 = r6 % 32
            r2 = r1
            goto L_0x0388
        L_0x037c:
            r27 = r1
            r24 = r2
            r16 = 1
            r1 = 1048575(0xfffff, float:1.469367E-39)
            r2 = r1
            r13 = r6
            r6 = 0
        L_0x0388:
            r1 = 18
            if (r5 < r1) goto L_0x0396
            r1 = 49
            if (r5 > r1) goto L_0x0396
            int r1 = r22 + 1
            r17[r22] = r0
            r22 = r1
        L_0x0396:
            r32 = r6
            r6 = r0
            r0 = r32
        L_0x039b:
            int r1 = r12 + 1
            r3[r12] = r4
            int r4 = r12 + 2
            r14 = r8 & 512(0x200, float:7.175E-43)
            if (r14 == 0) goto L_0x03a8
            r14 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03a9
        L_0x03a8:
            r14 = 0
        L_0x03a9:
            r29 = r7
            r7 = r8 & 256(0x100, float:3.59E-43)
            if (r7 == 0) goto L_0x03b2
            r7 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03b3
        L_0x03b2:
            r7 = 0
        L_0x03b3:
            r7 = r7 | r14
            r8 = r8 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L_0x03bb
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x03bc
        L_0x03bb:
            r8 = 0
        L_0x03bc:
            r7 = r7 | r8
            int r5 = r5 << 20
            r5 = r5 | r7
            r5 = r5 | r6
            r3[r1] = r5
            int r12 = r12 + 3
            int r0 = r0 << 20
            r0 = r0 | r2
            r3[r4] = r0
            r4 = r13
            r6 = r16
            r16 = r24
            r2 = r25
            r0 = r26
            r1 = r27
            r14 = r28
            r7 = r29
            r13 = r31
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0186
        L_0x03e0:
            r26 = r0
            r31 = r13
            r28 = r14
            com.google.android.gms.internal.firebase-auth-api.zzakt r0 = new com.google.android.gms.internal.firebase-auth-api.zzakt
            com.google.android.gms.internal.firebase-auth-api.zzakp r14 = r26.zza()
            com.google.android.gms.internal.firebase-auth-api.zzala r15 = r26.zzb()
            r16 = 0
            r9 = r0
            r10 = r3
            r12 = r31
            r13 = r28
            r20 = r35
            r21 = r36
            r22 = r37
            r23 = r38
            r24 = r39
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return r0
        L_0x0406:
            com.google.android.gms.internal.firebase-auth-api.zzalz r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzalz) r0
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(java.lang.Class, com.google.android.gms.internal.firebase-auth-api.zzakn, com.google.android.gms.internal.firebase-auth-api.zzakx, com.google.android.gms.internal.firebase-auth-api.zzaju, com.google.android.gms.internal.firebase-auth-api.zzamg, com.google.android.gms.internal.firebase-auth-api.zzais, com.google.android.gms.internal.firebase-auth-api.zzaki):com.google.android.gms.internal.firebase-auth-api.zzakt");
    }

    private final <UT, UB> UB zza(Object obj, int i3, UB ub, zzamg<UT, UB> zzamg, Object obj2) {
        zzaji zzd2;
        int i4 = this.zzc[i3];
        Object zze2 = zzamm.zze(obj, (long) (zzc(i3) & 1048575));
        if (zze2 == null || (zzd2 = zzd(i3)) == null) {
            return ub;
        }
        return zza(i3, i4, this.zzs.zze(zze2), zzd2, ub, zzamg, obj2);
    }

    private final <K, V, UT, UB> UB zza(int i3, int i4, Map<K, V> map, zzaji zzaji, UB ub, zzamg<UT, UB> zzamg, Object obj) {
        zzakg<?, ?> zza2 = this.zzs.zza(zzf(i3));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzaji.zza(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzamg.zzc(obj);
                }
                zzahx zzc2 = zzaho.zzc(zzakh.zza(zza2, next.getKey(), next.getValue()));
                try {
                    zzakh.zza(zzc2.zzb(), zza2, next.getKey(), next.getValue());
                    zzamg.zza(ub, i4, zzc2.zza());
                    it.remove();
                } catch (IOException e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
        return ub;
    }

    private final Object zza(T t2, int i3) {
        zzalh zze2 = zze(i3);
        long zzc2 = (long) (zzc(i3) & 1048575);
        if (!zzc(t2, i3)) {
            return zze2.zza();
        }
        Object object = zzb.getObject(t2, zzc2);
        if (zzg(object)) {
            return object;
        }
        Object zza2 = zze2.zza();
        if (object != null) {
            zze2.zza(zza2, object);
        }
        return zza2;
    }

    private final Object zza(T t2, int i3, int i4) {
        zzalh zze2 = zze(i4);
        if (!zzc(t2, i3, i4)) {
            return zze2.zza();
        }
        Object object = zzb.getObject(t2, (long) (zzc(i4) & 1048575));
        if (zzg(object)) {
            return object;
        }
        Object zza2 = zze2.zza();
        if (object != null) {
            zze2.zza(zza2, object);
        }
        return zza2;
    }

    public final T zza() {
        return this.zzo.zza(this.zzg);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder l2 = C0118y.l("Field ", str, " for ", name, " not found. Known fields are ");
            l2.append(arrays);
            throw new RuntimeException(l2.toString());
        }
    }

    public final void zza(T t2, T t3) {
        zzf((Object) t2);
        t3.getClass();
        for (int i3 = 0; i3 < this.zzc.length; i3 += 3) {
            int zzc2 = zzc(i3);
            long j2 = (long) (1048575 & zzc2);
            int i4 = this.zzc[i3];
            switch ((zzc2 & 267386880) >>> 20) {
                case 0:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zza((Object) t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 1:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzb(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 2:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzd(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 3:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzd(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 4:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 5:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzd(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 6:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 7:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zzc((Object) t2, j2, zzamm.zzh(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 8:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zze(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 9:
                    zza(t2, t3, i3);
                    break;
                case 10:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zze(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 11:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 12:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 13:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 14:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzd(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 15:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzc(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 16:
                    if (!zzc(t3, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zzd(t3, j2));
                        zzb(t2, i3);
                        break;
                    }
                case 17:
                    zza(t2, t3, i3);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzp.zza(t2, t3, j2);
                    break;
                case 50:
                    zzalj.zza(this.zzs, t2, t3, j2);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zzc(t3, i4, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zze(t3, j2));
                        zzb(t2, i4, i3);
                        break;
                    }
                case 60:
                    zzb(t2, t3, i3);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zzc(t3, i4, i3)) {
                        break;
                    } else {
                        zzamm.zza((Object) t2, j2, zzamm.zze(t3, j2));
                        zzb(t2, i4, i3);
                        break;
                    }
                case 68:
                    zzb(t2, t3, i3);
                    break;
            }
        }
        zzalj.zza(this.zzq, t2, t3);
        if (this.zzh) {
            zzalj.zza(this.zzr, t2, t3);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: com.google.android.gms.internal.firebase-auth-api.zzamg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: com.google.android.gms.internal.firebase-auth-api.zzamg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: com.google.android.gms.internal.firebase-auth-api.zzamg} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v7, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v9, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v13, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v15, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v16, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v17, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v18, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v18, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v19, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v20, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v20, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v21, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v22, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v22, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v23, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v23, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v24, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v24, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v25, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v25, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v26, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v26, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v27, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v27, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v28, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v28, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v29, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v29, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v30, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v31, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v31, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v32, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v32, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v33, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v33, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v34, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v34, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v35, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v35, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v36, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v36, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v37, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v37, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v38, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v38, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v39, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v39, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v40, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v40, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v41, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v41, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v42, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v42, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v43, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v43, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v44, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v44, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v45, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v45, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v46, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v46, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v47, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v47, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v48, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v48, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v49, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v49, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v50, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v50, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v51, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v51, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v52, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v52, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v53, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v53, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v54, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v54, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v55, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v55, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v56, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v58, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v57, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v59, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v67, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v78, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v68, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v82, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v69, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v100, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v83, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v84, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v71, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v72, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v73, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v74, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v75, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v76, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v77, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v78, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v79, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v80, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v81, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v82, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v83, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v84, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v85, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v85, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v86, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v86, resolved type: com.google.android.gms.internal.firebase-auth-api.zzais<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v87, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v88, resolved type: com.google.android.gms.internal.firebase-auth-api.zzaix<?>} */
    /* JADX WARNING: type inference failed for: r14v4 */
    /* JADX WARNING: type inference failed for: r13v64, types: [boolean] */
    /* JADX WARNING: type inference failed for: r11v80 */
    /* JADX WARNING: type inference failed for: r14v65 */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0623, code lost:
        r15 = r9;
        r5 = r11;
        r4 = r13;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f6, code lost:
        r15 = r9;
        r13 = r13;
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010e, code lost:
        r13 = r4;
        r11 = r5;
        r14 = r6;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x062e A[Catch:{ all -> 0x00ca }] */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x0662 A[LOOP:5: B:186:0x065e->B:188:0x0662, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0676  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0639 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r18, com.google.android.gms.internal.p002firebaseauthapi.zzale r19, com.google.android.gms.internal.p002firebaseauthapi.zzaiq r20) throws java.io.IOException {
        /*
            r17 = this;
            r7 = r17
            r15 = r18
            r0 = r19
            r6 = r20
            r20.getClass()
            zzf((java.lang.Object) r18)
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r14 = r7.zzq
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r5 = r7.zzr
            r16 = 0
            r4 = r16
            r8 = r4
        L_0x0017:
            int r2 = r19.zzc()     // Catch:{ all -> 0x0657 }
            int r1 = r7.zza((int) r2)     // Catch:{ all -> 0x0657 }
            if (r1 >= 0) goto L_0x00b1
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r1) goto L_0x0044
            int r0 = r7.zzm
        L_0x0028:
            int r1 = r7.zzn
            if (r0 >= r1) goto L_0x003e
            int[] r1 = r7.zzl
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r5 = r14
            r6 = r18
            java.lang.Object r4 = r1.zza((java.lang.Object) r2, (int) r3, r4, r5, (java.lang.Object) r6)
            int r0 = r0 + 1
            goto L_0x0028
        L_0x003e:
            if (r4 == 0) goto L_0x0043
            r14.zzb((java.lang.Object) r15, r4)
        L_0x0043:
            return
        L_0x0044:
            boolean r1 = r7.zzh     // Catch:{ all -> 0x005b }
            if (r1 != 0) goto L_0x004b
            r11 = r16
            goto L_0x0052
        L_0x004b:
            com.google.android.gms.internal.firebase-auth-api.zzakp r1 = r7.zzg     // Catch:{ all -> 0x005b }
            java.lang.Object r1 = r5.zza(r6, r1, r2)     // Catch:{ all -> 0x005b }
            r11 = r1
        L_0x0052:
            if (r11 == 0) goto L_0x007a
            if (r8 != 0) goto L_0x0060
            com.google.android.gms.internal.firebase-auth-api.zzaix r1 = r5.zzb(r15)     // Catch:{ all -> 0x005b }
            goto L_0x0061
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            r10 = r14
            r9 = r15
            goto L_0x065b
        L_0x0060:
            r1 = r8
        L_0x0061:
            r8 = r5
            r9 = r18
            r10 = r19
            r12 = r20
            r13 = r1
            r3 = r14
            r14 = r4
            r2 = r15
            r15 = r3
            java.lang.Object r4 = r8.zza(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x0075 }
            r8 = r1
        L_0x0072:
            r15 = r2
            r14 = r3
            goto L_0x0017
        L_0x0075:
            r0 = move-exception
            r9 = r2
            r10 = r3
            goto L_0x065b
        L_0x007a:
            r3 = r14
            r2 = r15
            r3.zza((com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ all -> 0x0075 }
            if (r4 != 0) goto L_0x0086
            java.lang.Object r1 = r3.zzc(r2)     // Catch:{ all -> 0x0075 }
            r4 = r1
        L_0x0086:
            boolean r1 = r3.zza(r4, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ all -> 0x0075 }
            if (r1 != 0) goto L_0x0072
            int r0 = r7.zzm
        L_0x008e:
            int r1 = r7.zzn
            if (r0 >= r1) goto L_0x00a9
            int[] r1 = r7.zzl
            r5 = r1[r0]
            r1 = r17
            r9 = r2
            r2 = r18
            r10 = r3
            r3 = r5
            r5 = r10
            r6 = r18
            java.lang.Object r4 = r1.zza((java.lang.Object) r2, (int) r3, r4, r5, (java.lang.Object) r6)
            int r0 = r0 + 1
            r2 = r9
            r3 = r10
            goto L_0x008e
        L_0x00a9:
            r9 = r2
            r10 = r3
            if (r4 == 0) goto L_0x00b0
            r10.zzb((java.lang.Object) r9, r4)
        L_0x00b0:
            return
        L_0x00b1:
            r10 = r14
            r9 = r15
            int r3 = r7.zzc((int) r1)     // Catch:{ all -> 0x02f3 }
            r11 = 267386880(0xff00000, float:2.3665827E-29)
            r11 = r11 & r3
            int r11 = r11 >>> 20
            r12 = 1048575(0xfffff, float:1.469367E-39)
            switch(r11) {
                case 0: goto L_0x0613;
                case 1: goto L_0x0602;
                case 2: goto L_0x05f1;
                case 3: goto L_0x05e0;
                case 4: goto L_0x05cf;
                case 5: goto L_0x05be;
                case 6: goto L_0x05ac;
                case 7: goto L_0x059a;
                case 8: goto L_0x058f;
                case 9: goto L_0x057a;
                case 10: goto L_0x0568;
                case 11: goto L_0x0556;
                case 12: goto L_0x0531;
                case 13: goto L_0x051f;
                case 14: goto L_0x050d;
                case 15: goto L_0x04fb;
                case 16: goto L_0x04e9;
                case 17: goto L_0x04d4;
                case 18: goto L_0x04c3;
                case 19: goto L_0x04b2;
                case 20: goto L_0x04a1;
                case 21: goto L_0x0490;
                case 22: goto L_0x047f;
                case 23: goto L_0x046e;
                case 24: goto L_0x045d;
                case 25: goto L_0x044c;
                case 26: goto L_0x0427;
                case 27: goto L_0x0412;
                case 28: goto L_0x0401;
                case 29: goto L_0x03f0;
                case 30: goto L_0x03d4;
                case 31: goto L_0x03c3;
                case 32: goto L_0x03b2;
                case 33: goto L_0x03a1;
                case 34: goto L_0x0390;
                case 35: goto L_0x037f;
                case 36: goto L_0x036e;
                case 37: goto L_0x035d;
                case 38: goto L_0x034c;
                case 39: goto L_0x033b;
                case 40: goto L_0x032a;
                case 41: goto L_0x0319;
                case 42: goto L_0x0308;
                case 43: goto L_0x02f7;
                case 44: goto L_0x02cf;
                case 45: goto L_0x02c1;
                case 46: goto L_0x02b3;
                case 47: goto L_0x02a5;
                case 48: goto L_0x0297;
                case 49: goto L_0x0285;
                case 50: goto L_0x0243;
                case 51: goto L_0x0231;
                case 52: goto L_0x021f;
                case 53: goto L_0x020d;
                case 54: goto L_0x01fb;
                case 55: goto L_0x01e9;
                case 56: goto L_0x01d7;
                case 57: goto L_0x01c5;
                case 58: goto L_0x01b3;
                case 59: goto L_0x01ab;
                case 60: goto L_0x0199;
                case 61: goto L_0x018b;
                case 62: goto L_0x017a;
                case 63: goto L_0x0157;
                case 64: goto L_0x0146;
                case 65: goto L_0x0135;
                case 66: goto L_0x0124;
                case 67: goto L_0x0113;
                case 68: goto L_0x00fe;
                default: goto L_0x00c2;
            }
        L_0x00c2:
            if (r4 != 0) goto L_0x00d2
            java.lang.Object r1 = r10.zzc(r9)     // Catch:{  }
            r4 = r1
            goto L_0x00d2
        L_0x00ca:
            r0 = move-exception
            goto L_0x065b
        L_0x00cd:
            r13 = r4
            r11 = r5
            r14 = r6
            goto L_0x0628
        L_0x00d2:
            boolean r1 = r10.zza(r4, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ zzajn -> 0x00fa }
            if (r1 != 0) goto L_0x00f6
            int r0 = r7.zzm
        L_0x00da:
            int r1 = r7.zzn
            if (r0 >= r1) goto L_0x00f0
            int[] r1 = r7.zzl
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r5 = r10
            r6 = r18
            java.lang.Object r4 = r1.zza((java.lang.Object) r2, (int) r3, r4, r5, (java.lang.Object) r6)
            int r0 = r0 + 1
            goto L_0x00da
        L_0x00f0:
            if (r4 == 0) goto L_0x00f5
            r10.zzb((java.lang.Object) r9, r4)
        L_0x00f5:
            return
        L_0x00f6:
            r15 = r9
        L_0x00f7:
            r14 = r10
            goto L_0x0017
        L_0x00fa:
            r11 = r5
            r14 = r6
            goto L_0x0629
        L_0x00fe:
            java.lang.Object r3 = r7.zza(r9, (int) r2, (int) r1)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzakp r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r3     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzalh r11 = r7.zze((int) r1)     // Catch:{  }
            r0.zza(r3, r11, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r6)     // Catch:{  }
            r7.zza(r9, (int) r2, (int) r1, (java.lang.Object) r3)     // Catch:{  }
        L_0x010e:
            r13 = r4
            r11 = r5
            r14 = r6
            goto L_0x0623
        L_0x0113:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            long r13 = r19.zzn()     // Catch:{  }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0124:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            int r3 = r19.zzi()     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0135:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            long r13 = r19.zzm()     // Catch:{  }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0146:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            int r3 = r19.zzh()     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0157:
            int r11 = r19.zze()     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzaji r13 = r7.zzd((int) r1)     // Catch:{  }
            if (r13 == 0) goto L_0x016d
            boolean r13 = r13.zza(r11)     // Catch:{  }
            if (r13 == 0) goto L_0x0168
            goto L_0x016d
        L_0x0168:
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(r9, r2, r11, r4, r10)     // Catch:{  }
            goto L_0x00f6
        L_0x016d:
            r3 = r3 & r12
            long r12 = (long) r3     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r12, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x017a:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            int r3 = r19.zzj()     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x018b:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzaho r3 = r19.zzp()     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0199:
            java.lang.Object r3 = r7.zza(r9, (int) r2, (int) r1)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzakp r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r3     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzalh r11 = r7.zze((int) r1)     // Catch:{  }
            r0.zzb(r3, r11, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r6)     // Catch:{  }
            r7.zza(r9, (int) r2, (int) r1, (java.lang.Object) r3)     // Catch:{  }
            goto L_0x010e
        L_0x01ab:
            r7.zza((java.lang.Object) r9, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x01b3:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            boolean r3 = r19.zzs()     // Catch:{  }
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x01c5:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            int r3 = r19.zzf()     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x01d7:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            long r13 = r19.zzk()     // Catch:{  }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x01e9:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            int r3 = r19.zzg()     // Catch:{  }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x01fb:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            long r13 = r19.zzo()     // Catch:{  }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x020d:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            long r13 = r19.zzl()     // Catch:{  }
            java.lang.Long r3 = java.lang.Long.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x021f:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            float r3 = r19.zzb()     // Catch:{  }
            java.lang.Float r3 = java.lang.Float.valueOf(r3)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0231:
            r3 = r3 & r12
            long r11 = (long) r3     // Catch:{  }
            double r13 = r19.zza()     // Catch:{  }
            java.lang.Double r3 = java.lang.Double.valueOf(r13)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r7.zzb(r9, (int) r2, (int) r1)     // Catch:{  }
            goto L_0x010e
        L_0x0243:
            java.lang.Object r2 = r7.zzf((int) r1)     // Catch:{  }
            int r1 = r7.zzc((int) r1)     // Catch:{  }
            r1 = r1 & r12
            long r11 = (long) r1     // Catch:{  }
            java.lang.Object r1 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r9, r11)     // Catch:{  }
            if (r1 != 0) goto L_0x025d
            com.google.android.gms.internal.firebase-auth-api.zzaki r1 = r7.zzs     // Catch:{  }
            java.lang.Object r1 = r1.zzb(r2)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r1)     // Catch:{  }
            goto L_0x0274
        L_0x025d:
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r7.zzs     // Catch:{  }
            boolean r3 = r3.zzf(r1)     // Catch:{  }
            if (r3 == 0) goto L_0x0274
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r7.zzs     // Catch:{  }
            java.lang.Object r3 = r3.zzb(r2)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzaki r13 = r7.zzs     // Catch:{  }
            r13.zza(r3, r1)     // Catch:{  }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r11, (java.lang.Object) r3)     // Catch:{  }
            r1 = r3
        L_0x0274:
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r7.zzs     // Catch:{  }
            java.util.Map r1 = r3.zze(r1)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzaki r3 = r7.zzs     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzakg r2 = r3.zza(r2)     // Catch:{  }
            r0.zza(r1, r2, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r6)     // Catch:{  }
            goto L_0x010e
        L_0x0285:
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r7.zze((int) r1)     // Catch:{  }
            com.google.android.gms.internal.firebase-auth-api.zzaju r11 = r7.zzp     // Catch:{  }
            java.util.List r2 = r11.zza(r9, r2)     // Catch:{  }
            r0.zza(r2, r1, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r6)     // Catch:{  }
            goto L_0x010e
        L_0x0297:
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{  }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{  }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{  }
            r0.zzm(r1)     // Catch:{  }
            goto L_0x010e
        L_0x02a5:
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{  }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{  }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{  }
            r0.zzl(r1)     // Catch:{  }
            goto L_0x010e
        L_0x02b3:
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{  }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{  }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{  }
            r0.zzk(r1)     // Catch:{  }
            goto L_0x010e
        L_0x02c1:
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{  }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{  }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{  }
            r0.zzj(r1)     // Catch:{  }
            goto L_0x010e
        L_0x02cf:
            com.google.android.gms.internal.firebase-auth-api.zzaju r11 = r7.zzp     // Catch:{ zzajn -> 0x00cd }
            r3 = r3 & r12
            long r12 = (long) r3     // Catch:{ zzajn -> 0x00cd }
            java.util.List r3 = r11.zza(r9, r12)     // Catch:{ zzajn -> 0x00cd }
            r0.zzd(r3)     // Catch:{ zzajn -> 0x00cd }
            com.google.android.gms.internal.firebase-auth-api.zzaji r11 = r7.zzd((int) r1)     // Catch:{ zzajn -> 0x00cd }
            r1 = r18
            r13 = r4
            r4 = r11
            r11 = r5
            r5 = r13
            r14 = r6
            r6 = r10
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(r1, r2, r3, r4, r5, r6)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
        L_0x02ea:
            r15 = r9
            r5 = r11
        L_0x02ec:
            r6 = r14
            goto L_0x00f7
        L_0x02ef:
            r0 = move-exception
            r4 = r13
            goto L_0x065b
        L_0x02f3:
            r0 = move-exception
            r13 = r4
            goto L_0x065b
        L_0x02f7:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzp(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0308:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zza(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0319:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zze(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x032a:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzf(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x033b:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzh(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x034c:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzq(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x035d:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzi(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x036e:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzg(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x037f:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzc(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0390:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzm(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x03a1:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzl(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x03b2:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzk(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x03c3:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzj(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x03d4:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r4 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r3 = r3 & r12
            long r5 = (long) r3     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r3 = r4.zza(r9, r5)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzd(r3)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzaji r4 = r7.zzd((int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r1 = r18
            r5 = r13
            r6 = r10
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(r1, r2, r3, r4, r5, r6)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x02ea
        L_0x03f0:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzp(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0401:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzb(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0412:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r7.zze((int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzaju r4 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r2 = r4.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzb(r2, r1, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r14)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0427:
            r13 = r4
            r11 = r5
            r14 = r6
            boolean r1 = zzg((int) r3)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            if (r1 == 0) goto L_0x043e
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzo(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x043e:
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzn(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x044c:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zza(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x045d:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zze(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x046e:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzf(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x047f:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzh(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0490:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzq(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04a1:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzi(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04b2:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzg(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04c3:
            r13 = r4
            r11 = r5
            r14 = r6
            com.google.android.gms.internal.firebase-auth-api.zzaju r1 = r7.zzp     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            java.util.List r1 = r1.zza(r9, r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzc(r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04d4:
            r13 = r4
            r11 = r5
            r14 = r6
            java.lang.Object r2 = r7.zza(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzakp r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzalh r3 = r7.zze((int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zza(r2, r3, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r14)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zza(r9, (int) r1, (java.lang.Object) r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04e9:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            long r4 = r19.zzn()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (long) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x04fb:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            int r4 = r19.zzi()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x050d:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            long r4 = r19.zzm()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (long) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x051f:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            int r4 = r19.zzh()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0531:
            r13 = r4
            r11 = r5
            r14 = r6
            int r4 = r19.zze()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzaji r5 = r7.zzd((int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            if (r5 == 0) goto L_0x054b
            boolean r5 = r5.zza(r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            if (r5 == 0) goto L_0x0545
            goto L_0x054b
        L_0x0545:
            java.lang.Object r4 = com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza(r9, r2, r4, r13, r10)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x02ea
        L_0x054b:
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0556:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            int r4 = r19.zzj()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0568:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzaho r4 = r19.zzp()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (java.lang.Object) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x057a:
            r13 = r4
            r11 = r5
            r14 = r6
            java.lang.Object r2 = r7.zza(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzakp r2 = (com.google.android.gms.internal.p002firebaseauthapi.zzakp) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.firebase-auth-api.zzalh r3 = r7.zze((int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r0.zzb(r2, r3, (com.google.android.gms.internal.p002firebaseauthapi.zzaiq) r14)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zza(r9, (int) r1, (java.lang.Object) r2)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x058f:
            r13 = r4
            r11 = r5
            r14 = r6
            r7.zza((java.lang.Object) r9, (int) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x059a:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            boolean r4 = r19.zzs()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc((java.lang.Object) r9, (long) r2, (boolean) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x05ac:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            int r4 = r19.zzf()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x05be:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            long r4 = r19.zzk()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (long) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x05cf:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            int r4 = r19.zzg()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (int) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x05e0:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            long r4 = r19.zzo()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (long) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x05f1:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            long r4 = r19.zzl()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (long) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0602:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            float r4 = r19.zzb()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (float) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            goto L_0x0623
        L_0x0613:
            r13 = r4
            r11 = r5
            r14 = r6
            r2 = r3 & r12
            long r2 = (long) r2     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            double r4 = r19.zza()     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r9, (long) r2, (double) r4)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
            r7.zzb(r9, (int) r1)     // Catch:{ zzajn -> 0x0628, all -> 0x02ef }
        L_0x0623:
            r15 = r9
            r5 = r11
            r4 = r13
            goto L_0x02ec
        L_0x0628:
            r4 = r13
        L_0x0629:
            r10.zza((com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ all -> 0x00ca }
            if (r4 != 0) goto L_0x0633
            java.lang.Object r1 = r10.zzc(r9)     // Catch:{ all -> 0x00ca }
            r4 = r1
        L_0x0633:
            boolean r1 = r10.zza(r4, (com.google.android.gms.internal.p002firebaseauthapi.zzale) r0)     // Catch:{ all -> 0x00ca }
            if (r1 != 0) goto L_0x02ea
            int r0 = r7.zzm
        L_0x063b:
            int r1 = r7.zzn
            if (r0 >= r1) goto L_0x0651
            int[] r1 = r7.zzl
            r3 = r1[r0]
            r1 = r17
            r2 = r18
            r5 = r10
            r6 = r18
            java.lang.Object r4 = r1.zza((java.lang.Object) r2, (int) r3, r4, r5, (java.lang.Object) r6)
            int r0 = r0 + 1
            goto L_0x063b
        L_0x0651:
            if (r4 == 0) goto L_0x0656
            r10.zzb((java.lang.Object) r9, r4)
        L_0x0656:
            return
        L_0x0657:
            r0 = move-exception
            r13 = r4
            goto L_0x005c
        L_0x065b:
            int r1 = r7.zzm
            r8 = r1
        L_0x065e:
            int r1 = r7.zzn
            if (r8 >= r1) goto L_0x0674
            int[] r1 = r7.zzl
            r3 = r1[r8]
            r1 = r17
            r2 = r18
            r5 = r10
            r6 = r18
            java.lang.Object r4 = r1.zza((java.lang.Object) r2, (int) r3, r4, r5, (java.lang.Object) r6)
            int r8 = r8 + 1
            goto L_0x065e
        L_0x0674:
            if (r4 == 0) goto L_0x0679
            r10.zzb((java.lang.Object) r9, r4)
        L_0x0679:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzale, com.google.android.gms.internal.firebase-auth-api.zzaiq):void");
    }

    public final void zza(T t2, byte[] bArr, int i3, int i4, zzahn zzahn) throws IOException {
        zza(t2, bArr, i3, i4, 0, zzahn);
    }

    private final void zza(T t2, T t3, int i3) {
        if (zzc(t3, i3)) {
            long zzc2 = (long) (zzc(i3) & 1048575);
            Unsafe unsafe = zzb;
            Object object = unsafe.getObject(t3, zzc2);
            if (object != null) {
                zzalh zze2 = zze(i3);
                if (!zzc(t2, i3)) {
                    if (!zzg(object)) {
                        unsafe.putObject(t2, zzc2, object);
                    } else {
                        Object zza2 = zze2.zza();
                        zze2.zza(zza2, object);
                        unsafe.putObject(t2, zzc2, zza2);
                    }
                    zzb(t2, i3);
                    return;
                }
                Object object2 = unsafe.getObject(t2, zzc2);
                if (!zzg(object2)) {
                    Object zza3 = zze2.zza();
                    zze2.zza(zza3, object2);
                    unsafe.putObject(t2, zzc2, zza3);
                    object2 = zza3;
                }
                zze2.zza(object2, object);
                return;
            }
            int i4 = this.zzc[i3];
            String valueOf = String.valueOf(t3);
            throw new IllegalStateException("Source subfield " + i4 + " is present but null: " + valueOf);
        }
    }

    private final void zza(Object obj, int i3, zzale zzale) throws IOException {
        if (zzg(i3)) {
            zzamm.zza(obj, (long) (i3 & 1048575), (Object) zzale.zzr());
        } else if (this.zzi) {
            zzamm.zza(obj, (long) (i3 & 1048575), (Object) zzale.zzq());
        } else {
            zzamm.zza(obj, (long) (i3 & 1048575), (Object) zzale.zzp());
        }
    }

    private final void zza(T t2, int i3, Object obj) {
        zzb.putObject(t2, (long) (zzc(i3) & 1048575), obj);
        zzb(t2, i3);
    }

    private final void zza(T t2, int i3, int i4, Object obj) {
        zzb.putObject(t2, (long) (zzc(i4) & 1048575), obj);
        zzb(t2, i3, i4);
    }

    private final <K, V> void zza(zzanc zzanc, int i3, Object obj, int i4) throws IOException {
        if (obj != null) {
            zzanc.zza(i3, this.zzs.zza(zzf(i4)), this.zzs.zzd(obj));
        }
    }

    private static void zza(int i3, Object obj, zzanc zzanc) throws IOException {
        if (obj instanceof String) {
            zzanc.zza(i3, (String) obj);
        } else {
            zzanc.zza(i3, (zzaho) obj);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:196:0x05b9, code lost:
        r22 = r10;
        r20 = r11;
        r21 = r15;
        r16 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x05c1, code lost:
        r11 = r4;
        r15 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:269:0x07e1, code lost:
        r22 = r10;
        r20 = r11;
        r16 = r12;
        r21 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0b78, code lost:
        r4 = r11 + 3;
        r2 = r13;
        r5 = r15;
        r12 = r16;
        r13 = 1048575;
        r14 = r19;
        r1 = r20;
        r15 = r21;
        r0 = r22;
        r9 = 267386880;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0518  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x0557  */
    /* JADX WARNING: Removed duplicated region for block: B:345:0x0b91  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r24, com.google.android.gms.internal.p002firebaseauthapi.zzanc r25) throws java.io.IOException {
        /*
            r23 = this;
            r6 = r23
            r7 = r24
            r8 = r25
            int r0 = r25.zza()
            int r1 = com.google.android.gms.internal.p002firebaseauthapi.zzanf.zzb
            r9 = 267386880(0xff00000, float:2.3665827E-29)
            r11 = 1
            r12 = 0
            r13 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x052d
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r6.zzq
            zza(r0, r7, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            boolean r0 = r6.zzh
            if (r0 == 0) goto L_0x0037
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r0 = r0.zza((java.lang.Object) r7)
            com.google.android.gms.internal.firebase-auth-api.zzali<T, java.lang.Object> r1 = r0.zza
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0037
            java.util.Iterator r0 = r0.zzc()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0039
        L_0x0037:
            r0 = 0
            r1 = 0
        L_0x0039:
            int[] r2 = r6.zzc
            int r2 = r2.length
            int r2 = r2 + -3
        L_0x003e:
            if (r2 < 0) goto L_0x0516
            int r3 = r6.zzc((int) r2)
            int[] r4 = r6.zzc
            r4 = r4[r2]
        L_0x0048:
            if (r1 == 0) goto L_0x0066
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r5 = r6.zzr
            int r5 = r5.zza((java.util.Map.Entry<?, ?>) r1)
            if (r5 <= r4) goto L_0x0066
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r5 = r6.zzr
            r5.zza(r8, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0064
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0048
        L_0x0064:
            r1 = 0
            goto L_0x0048
        L_0x0066:
            r5 = r3 & r9
            int r5 = r5 >>> 20
            switch(r5) {
                case 0: goto L_0x0503;
                case 1: goto L_0x04f3;
                case 2: goto L_0x04e3;
                case 3: goto L_0x04d3;
                case 4: goto L_0x04c3;
                case 5: goto L_0x04b3;
                case 6: goto L_0x04a3;
                case 7: goto L_0x0492;
                case 8: goto L_0x0481;
                case 9: goto L_0x046c;
                case 10: goto L_0x0459;
                case 11: goto L_0x0448;
                case 12: goto L_0x0437;
                case 13: goto L_0x0426;
                case 14: goto L_0x0415;
                case 15: goto L_0x0404;
                case 16: goto L_0x03f3;
                case 17: goto L_0x03de;
                case 18: goto L_0x03cd;
                case 19: goto L_0x03bc;
                case 20: goto L_0x03ab;
                case 21: goto L_0x039a;
                case 22: goto L_0x0389;
                case 23: goto L_0x0378;
                case 24: goto L_0x0367;
                case 25: goto L_0x0356;
                case 26: goto L_0x0345;
                case 27: goto L_0x0330;
                case 28: goto L_0x031f;
                case 29: goto L_0x030e;
                case 30: goto L_0x02fd;
                case 31: goto L_0x02ec;
                case 32: goto L_0x02db;
                case 33: goto L_0x02ca;
                case 34: goto L_0x02b9;
                case 35: goto L_0x02a8;
                case 36: goto L_0x0297;
                case 37: goto L_0x0286;
                case 38: goto L_0x0275;
                case 39: goto L_0x0264;
                case 40: goto L_0x0253;
                case 41: goto L_0x0242;
                case 42: goto L_0x0231;
                case 43: goto L_0x0220;
                case 44: goto L_0x020f;
                case 45: goto L_0x01fe;
                case 46: goto L_0x01ed;
                case 47: goto L_0x01dc;
                case 48: goto L_0x01cb;
                case 49: goto L_0x01b6;
                case 50: goto L_0x01ab;
                case 51: goto L_0x019a;
                case 52: goto L_0x0189;
                case 53: goto L_0x0178;
                case 54: goto L_0x0167;
                case 55: goto L_0x0156;
                case 56: goto L_0x0145;
                case 57: goto L_0x0134;
                case 58: goto L_0x0123;
                case 59: goto L_0x0112;
                case 60: goto L_0x00fd;
                case 61: goto L_0x00ea;
                case 62: goto L_0x00d9;
                case 63: goto L_0x00c8;
                case 64: goto L_0x00b7;
                case 65: goto L_0x00a6;
                case 66: goto L_0x0095;
                case 67: goto L_0x0084;
                case 68: goto L_0x006f;
                default: goto L_0x006d;
            }
        L_0x006d:
            goto L_0x0512
        L_0x006f:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            r8.zza((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x0084:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = zzd(r7, r14)
            r8.zzd((int) r4, (long) r14)
            goto L_0x0512
        L_0x0095:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zze((int) r4, (int) r3)
            goto L_0x0512
        L_0x00a6:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = zzd(r7, r14)
            r8.zzc((int) r4, (long) r14)
            goto L_0x0512
        L_0x00b7:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zzd((int) r4, (int) r3)
            goto L_0x0512
        L_0x00c8:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zza((int) r4, (int) r3)
            goto L_0x0512
        L_0x00d9:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zzf(r4, r3)
            goto L_0x0512
        L_0x00ea:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzaho r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r3
            r8.zza((int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r3)
            goto L_0x0512
        L_0x00fd:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            r8.zzb((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x0112:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            zza((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x0512
        L_0x0123:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            boolean r3 = zze(r7, r14)
            r8.zza((int) r4, (boolean) r3)
            goto L_0x0512
        L_0x0134:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zzb((int) r4, (int) r3)
            goto L_0x0512
        L_0x0145:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = zzd(r7, r14)
            r8.zza((int) r4, (long) r14)
            goto L_0x0512
        L_0x0156:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = zzc(r7, (long) r14)
            r8.zzc((int) r4, (int) r3)
            goto L_0x0512
        L_0x0167:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = zzd(r7, r14)
            r8.zze((int) r4, (long) r14)
            goto L_0x0512
        L_0x0178:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = zzd(r7, r14)
            r8.zzb((int) r4, (long) r14)
            goto L_0x0512
        L_0x0189:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            float r3 = zzb(r7, (long) r14)
            r8.zza((int) r4, (float) r3)
            goto L_0x0512
        L_0x019a:
            boolean r5 = r6.zzc(r7, (int) r4, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            double r14 = zza(r7, (long) r14)
            r8.zza((int) r4, (double) r14)
            goto L_0x0512
        L_0x01ab:
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            r6.zza((com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (int) r4, (java.lang.Object) r3, (int) r2)
            goto L_0x0512
        L_0x01b6:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r4, (java.util.List<?>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x01cb:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzl(r4, r3, r8, r11)
            goto L_0x0512
        L_0x01dc:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzk(r4, r3, r8, r11)
            goto L_0x0512
        L_0x01ed:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r4, r3, r8, r11)
            goto L_0x0512
        L_0x01fe:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r4, r3, r8, r11)
            goto L_0x0512
        L_0x020f:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0220:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzm(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0231:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r4, (java.util.List<java.lang.Boolean>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r11)
            goto L_0x0512
        L_0x0242:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0253:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0264:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0275:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzn(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0286:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r4, r3, r8, r11)
            goto L_0x0512
        L_0x0297:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r4, r3, r8, r11)
            goto L_0x0512
        L_0x02a8:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r4, (java.util.List<java.lang.Double>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r11)
            goto L_0x0512
        L_0x02b9:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzl(r4, r3, r8, r12)
            goto L_0x0512
        L_0x02ca:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzk(r4, r3, r8, r12)
            goto L_0x0512
        L_0x02db:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r4, r3, r8, r12)
            goto L_0x0512
        L_0x02ec:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r4, r3, r8, r12)
            goto L_0x0512
        L_0x02fd:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r4, r3, r8, r12)
            goto L_0x0512
        L_0x030e:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzm(r4, r3, r8, r12)
            goto L_0x0512
        L_0x031f:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r4, (java.util.List<com.google.android.gms.internal.p002firebaseauthapi.zzaho>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x0512
        L_0x0330:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r4, (java.util.List<?>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x0345:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r4, (java.util.List<java.lang.String>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x0512
        L_0x0356:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r4, (java.util.List<java.lang.Boolean>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r12)
            goto L_0x0512
        L_0x0367:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r4, r3, r8, r12)
            goto L_0x0512
        L_0x0378:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r4, r3, r8, r12)
            goto L_0x0512
        L_0x0389:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r4, r3, r8, r12)
            goto L_0x0512
        L_0x039a:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzn(r4, r3, r8, r12)
            goto L_0x0512
        L_0x03ab:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r4, r3, r8, r12)
            goto L_0x0512
        L_0x03bc:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r4, r3, r8, r12)
            goto L_0x0512
        L_0x03cd:
            int[] r4 = r6.zzc
            r4 = r4[r2]
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            java.util.List r3 = (java.util.List) r3
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r4, (java.util.List<java.lang.Double>) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r12)
            goto L_0x0512
        L_0x03de:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            r8.zza((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x03f3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r7, r14)
            r8.zzd((int) r4, (long) r14)
            goto L_0x0512
        L_0x0404:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zze((int) r4, (int) r3)
            goto L_0x0512
        L_0x0415:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r7, r14)
            r8.zzc((int) r4, (long) r14)
            goto L_0x0512
        L_0x0426:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zzd((int) r4, (int) r3)
            goto L_0x0512
        L_0x0437:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zza((int) r4, (int) r3)
            goto L_0x0512
        L_0x0448:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zzf(r4, r3)
            goto L_0x0512
        L_0x0459:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzaho r3 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r3
            r8.zza((int) r4, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r3)
            goto L_0x0512
        L_0x046c:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            com.google.android.gms.internal.firebase-auth-api.zzalh r5 = r6.zze((int) r2)
            r8.zzb((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r5)
            goto L_0x0512
        L_0x0481:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            java.lang.Object r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zze(r7, r14)
            zza((int) r4, (java.lang.Object) r3, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x0512
        L_0x0492:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            boolean r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r7, r14)
            r8.zza((int) r4, (boolean) r3)
            goto L_0x0512
        L_0x04a3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zzb((int) r4, (int) r3)
            goto L_0x0512
        L_0x04b3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r7, r14)
            r8.zza((int) r4, (long) r14)
            goto L_0x0512
        L_0x04c3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            int r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzc(r7, r14)
            r8.zzc((int) r4, (int) r3)
            goto L_0x0512
        L_0x04d3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r7, r14)
            r8.zze((int) r4, (long) r14)
            goto L_0x0512
        L_0x04e3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            long r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzd(r7, r14)
            r8.zzb((int) r4, (long) r14)
            goto L_0x0512
        L_0x04f3:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            float r3 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r7, r14)
            r8.zza((int) r4, (float) r3)
            goto L_0x0512
        L_0x0503:
            boolean r5 = r6.zzc(r7, (int) r2)
            if (r5 == 0) goto L_0x0512
            r3 = r3 & r13
            long r14 = (long) r3
            double r14 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r7, (long) r14)
            r8.zza((int) r4, (double) r14)
        L_0x0512:
            int r2 = r2 + -3
            goto L_0x003e
        L_0x0516:
            if (r1 == 0) goto L_0x052c
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r2 = r6.zzr
            r2.zza(r8, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x052a
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0516
        L_0x052a:
            r1 = 0
            goto L_0x0516
        L_0x052c:
            return
        L_0x052d:
            boolean r0 = r6.zzh
            if (r0 == 0) goto L_0x054b
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            com.google.android.gms.internal.firebase-auth-api.zzaix r0 = r0.zza((java.lang.Object) r7)
            com.google.android.gms.internal.firebase-auth-api.zzali<T, java.lang.Object> r1 = r0.zza
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x054b
            java.util.Iterator r0 = r0.zzd()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r14 = r0
            goto L_0x054d
        L_0x054b:
            r1 = 0
            r14 = 0
        L_0x054d:
            int[] r0 = r6.zzc
            int r15 = r0.length
            sun.misc.Unsafe r5 = zzb
            r2 = r12
            r4 = r2
            r0 = r13
        L_0x0555:
            if (r4 >= r15) goto L_0x0b8d
            int r3 = r6.zzc((int) r4)
            int[] r10 = r6.zzc
            r12 = r10[r4]
            r17 = r3 & r9
            int r9 = r17 >>> 20
            r11 = 17
            if (r9 > r11) goto L_0x058b
            int r11 = r4 + 2
            r10 = r10[r11]
            r11 = r10 & r13
            if (r11 == r0) goto L_0x057f
            if (r11 != r13) goto L_0x0575
            r19 = r14
            r2 = 0
            goto L_0x057d
        L_0x0575:
            r19 = r14
            long r13 = (long) r11
            int r0 = r5.getInt(r7, r13)
            r2 = r0
        L_0x057d:
            r0 = r11
            goto L_0x0581
        L_0x057f:
            r19 = r14
        L_0x0581:
            int r10 = r10 >>> 20
            r11 = 1
            int r10 = r11 << r10
            r11 = r1
            r13 = r2
            r14 = r10
            r10 = r0
            goto L_0x0591
        L_0x058b:
            r19 = r14
            r10 = r0
            r11 = r1
            r13 = r2
            r14 = 0
        L_0x0591:
            if (r11 == 0) goto L_0x05b0
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            int r0 = r0.zza((java.util.Map.Entry<?, ?>) r11)
            if (r0 > r12) goto L_0x05b0
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            r0.zza(r8, r11)
            boolean r0 = r19.hasNext()
            if (r0 == 0) goto L_0x05ae
            java.lang.Object r0 = r19.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r11 = r0
            goto L_0x0591
        L_0x05ae:
            r11 = 0
            goto L_0x0591
        L_0x05b0:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r3 & r18
            long r2 = (long) r0
            switch(r9) {
                case 0: goto L_0x0b57;
                case 1: goto L_0x0b35;
                case 2: goto L_0x0b13;
                case 3: goto L_0x0af0;
                case 4: goto L_0x0acd;
                case 5: goto L_0x0aaa;
                case 6: goto L_0x0a87;
                case 7: goto L_0x0a64;
                case 8: goto L_0x0a41;
                case 9: goto L_0x0a1a;
                case 10: goto L_0x09f5;
                case 11: goto L_0x09d2;
                case 12: goto L_0x09af;
                case 13: goto L_0x098c;
                case 14: goto L_0x0969;
                case 15: goto L_0x0946;
                case 16: goto L_0x0923;
                case 17: goto L_0x08f7;
                case 18: goto L_0x08e6;
                case 19: goto L_0x08d5;
                case 20: goto L_0x08c4;
                case 21: goto L_0x08b3;
                case 22: goto L_0x08a2;
                case 23: goto L_0x0891;
                case 24: goto L_0x0880;
                case 25: goto L_0x086f;
                case 26: goto L_0x085f;
                case 27: goto L_0x084b;
                case 28: goto L_0x083b;
                case 29: goto L_0x082b;
                case 30: goto L_0x081b;
                case 31: goto L_0x080b;
                case 32: goto L_0x07fb;
                case 33: goto L_0x07eb;
                case 34: goto L_0x07d2;
                case 35: goto L_0x07c2;
                case 36: goto L_0x07b2;
                case 37: goto L_0x07a2;
                case 38: goto L_0x0792;
                case 39: goto L_0x0782;
                case 40: goto L_0x0772;
                case 41: goto L_0x0762;
                case 42: goto L_0x0752;
                case 43: goto L_0x0742;
                case 44: goto L_0x0732;
                case 45: goto L_0x0722;
                case 46: goto L_0x0712;
                case 47: goto L_0x0702;
                case 48: goto L_0x06f2;
                case 49: goto L_0x06df;
                case 50: goto L_0x06d6;
                case 51: goto L_0x06c7;
                case 52: goto L_0x06b8;
                case 53: goto L_0x06a9;
                case 54: goto L_0x069a;
                case 55: goto L_0x068b;
                case 56: goto L_0x067c;
                case 57: goto L_0x066d;
                case 58: goto L_0x065e;
                case 59: goto L_0x064f;
                case 60: goto L_0x063c;
                case 61: goto L_0x062b;
                case 62: goto L_0x061d;
                case 63: goto L_0x060f;
                case 64: goto L_0x0601;
                case 65: goto L_0x05f3;
                case 66: goto L_0x05e5;
                case 67: goto L_0x05d7;
                case 68: goto L_0x05c5;
                default: goto L_0x05b9;
            }
        L_0x05b9:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
        L_0x05c1:
            r11 = r4
            r15 = r5
            goto L_0x0b78
        L_0x05c5:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            java.lang.Object r0 = r5.getObject(r7, r2)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r4)
            r8.zza((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x05b9
        L_0x05d7:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            long r0 = zzd(r7, r2)
            r8.zzd((int) r12, (long) r0)
            goto L_0x05b9
        L_0x05e5:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zze((int) r12, (int) r0)
            goto L_0x05b9
        L_0x05f3:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            long r0 = zzd(r7, r2)
            r8.zzc((int) r12, (long) r0)
            goto L_0x05b9
        L_0x0601:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zzd((int) r12, (int) r0)
            goto L_0x05b9
        L_0x060f:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zza((int) r12, (int) r0)
            goto L_0x05b9
        L_0x061d:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zzf(r12, r0)
            goto L_0x05b9
        L_0x062b:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            java.lang.Object r0 = r5.getObject(r7, r2)
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            r8.zza((int) r12, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x05b9
        L_0x063c:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            java.lang.Object r0 = r5.getObject(r7, r2)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r4)
            r8.zzb((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x05b9
        L_0x064f:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            java.lang.Object r0 = r5.getObject(r7, r2)
            zza((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x05b9
        L_0x065e:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            boolean r0 = zze(r7, r2)
            r8.zza((int) r12, (boolean) r0)
            goto L_0x05b9
        L_0x066d:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zzb((int) r12, (int) r0)
            goto L_0x05b9
        L_0x067c:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            long r0 = zzd(r7, r2)
            r8.zza((int) r12, (long) r0)
            goto L_0x05b9
        L_0x068b:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            int r0 = zzc(r7, (long) r2)
            r8.zzc((int) r12, (int) r0)
            goto L_0x05b9
        L_0x069a:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            long r0 = zzd(r7, r2)
            r8.zze((int) r12, (long) r0)
            goto L_0x05b9
        L_0x06a9:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            long r0 = zzd(r7, r2)
            r8.zzb((int) r12, (long) r0)
            goto L_0x05b9
        L_0x06b8:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            float r0 = zzb(r7, (long) r2)
            r8.zza((int) r12, (float) r0)
            goto L_0x05b9
        L_0x06c7:
            boolean r0 = r6.zzc(r7, (int) r12, (int) r4)
            if (r0 == 0) goto L_0x05b9
            double r0 = zza(r7, (long) r2)
            r8.zza((int) r12, (double) r0)
            goto L_0x05b9
        L_0x06d6:
            java.lang.Object r0 = r5.getObject(r7, r2)
            r6.zza((com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (int) r12, (java.lang.Object) r0, (int) r4)
            goto L_0x05b9
        L_0x06df:
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.firebase-auth-api.zzalh r2 = r6.zze((int) r4)
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r0, (java.util.List<?>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r2)
            goto L_0x05b9
        L_0x06f2:
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r9 = 1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzl(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0702:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzk(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0712:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0722:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0732:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0742:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzm(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0752:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r0, (java.util.List<java.lang.Boolean>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r9)
            goto L_0x05b9
        L_0x0762:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0772:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0782:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x0792:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzn(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x07a2:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x07b2:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r0, r1, r8, r9)
            goto L_0x05b9
        L_0x07c2:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r0, (java.util.List<java.lang.Double>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r9)
            goto L_0x05b9
        L_0x07d2:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r12 = 0
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzl(r0, r1, r8, r12)
        L_0x07e1:
            r22 = r10
            r20 = r11
            r16 = r12
            r21 = r15
            goto L_0x05c1
        L_0x07eb:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzk(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x07fb:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzj(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x080b:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzi(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x081b:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzc(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x082b:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzm(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x083b:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r0, (java.util.List<com.google.android.gms.internal.p002firebaseauthapi.zzaho>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x05b9
        L_0x084b:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.firebase-auth-api.zzalh r2 = r6.zze((int) r4)
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r0, (java.util.List<?>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r2)
            goto L_0x05b9
        L_0x085f:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r0, (java.util.List<java.lang.String>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x05b9
        L_0x086f:
            r9 = 1
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r12 = 0
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zza((int) r0, (java.util.List<java.lang.Boolean>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r12)
            goto L_0x07e1
        L_0x0880:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzd(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x0891:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zze(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x08a2:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzg(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x08b3:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzn(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x08c4:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzh(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x08d5:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzf(r0, r1, r8, r12)
            goto L_0x07e1
        L_0x08e6:
            r9 = 1
            r12 = 0
            int[] r0 = r6.zzc
            r0 = r0[r4]
            java.lang.Object r1 = r5.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            com.google.android.gms.internal.p002firebaseauthapi.zzalj.zzb((int) r0, (java.util.List<java.lang.Double>) r1, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8, (boolean) r12)
            goto L_0x07e1
        L_0x08f7:
            r9 = 1
            r16 = 0
            r0 = r23
            r1 = r24
            r8 = r2
            r2 = r4
            r3 = r10
            r20 = r11
            r11 = r4
            r4 = r13
            r21 = r15
            r15 = r5
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0920
            java.lang.Object r0 = r15.getObject(r7, r8)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            r8 = r25
            r8.zza((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
        L_0x091c:
            r22 = r10
            goto L_0x0b78
        L_0x0920:
            r8 = r25
            goto L_0x091c
        L_0x0923:
            r20 = r11
            r21 = r15
            r16 = 0
            r11 = r4
            r15 = r5
            r4 = r2
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r10
            r22 = r10
            r9 = r4
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            long r0 = r15.getLong(r7, r9)
            r8.zzd((int) r12, (long) r0)
            goto L_0x0b78
        L_0x0946:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zze((int) r12, (int) r0)
            goto L_0x0b78
        L_0x0969:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            long r0 = r15.getLong(r7, r9)
            r8.zzc((int) r12, (long) r0)
            goto L_0x0b78
        L_0x098c:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zzd((int) r12, (int) r0)
            goto L_0x0b78
        L_0x09af:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zza((int) r12, (int) r0)
            goto L_0x0b78
        L_0x09d2:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zzf(r12, r0)
            goto L_0x0b78
        L_0x09f5:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            java.lang.Object r0 = r15.getObject(r7, r9)
            com.google.android.gms.internal.firebase-auth-api.zzaho r0 = (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0
            r8.zza((int) r12, (com.google.android.gms.internal.p002firebaseauthapi.zzaho) r0)
            goto L_0x0b78
        L_0x0a1a:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            java.lang.Object r0 = r15.getObject(r7, r9)
            com.google.android.gms.internal.firebase-auth-api.zzalh r1 = r6.zze((int) r11)
            r8.zzb((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzalh) r1)
            goto L_0x0b78
        L_0x0a41:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            java.lang.Object r0 = r15.getObject(r7, r9)
            zza((int) r12, (java.lang.Object) r0, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            goto L_0x0b78
        L_0x0a64:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            boolean r0 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzh(r7, r9)
            r8.zza((int) r12, (boolean) r0)
            goto L_0x0b78
        L_0x0a87:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zzb((int) r12, (int) r0)
            goto L_0x0b78
        L_0x0aaa:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            long r0 = r15.getLong(r7, r9)
            r8.zza((int) r12, (long) r0)
            goto L_0x0b78
        L_0x0acd:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            int r0 = r15.getInt(r7, r9)
            r8.zzc((int) r12, (int) r0)
            goto L_0x0b78
        L_0x0af0:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            long r0 = r15.getLong(r7, r9)
            r8.zze((int) r12, (long) r0)
            goto L_0x0b78
        L_0x0b13:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            long r0 = r15.getLong(r7, r9)
            r8.zzb((int) r12, (long) r0)
            goto L_0x0b78
        L_0x0b35:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            float r0 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zzb(r7, r9)
            r8.zza((int) r12, (float) r0)
            goto L_0x0b78
        L_0x0b57:
            r22 = r10
            r20 = r11
            r21 = r15
            r16 = 0
            r9 = r2
            r11 = r4
            r15 = r5
            r0 = r23
            r1 = r24
            r2 = r11
            r3 = r22
            r4 = r13
            r5 = r14
            boolean r0 = r0.zza(r1, (int) r2, (int) r3, (int) r4, (int) r5)
            if (r0 == 0) goto L_0x0b78
            double r0 = com.google.android.gms.internal.p002firebaseauthapi.zzamm.zza((java.lang.Object) r7, (long) r9)
            r8.zza((int) r12, (double) r0)
        L_0x0b78:
            int r4 = r11 + 3
            r2 = r13
            r5 = r15
            r12 = r16
            r13 = r18
            r14 = r19
            r1 = r20
            r15 = r21
            r0 = r22
            r9 = 267386880(0xff00000, float:2.3665827E-29)
            r11 = 1
            goto L_0x0555
        L_0x0b8d:
            r19 = r14
        L_0x0b8f:
            if (r1 == 0) goto L_0x0ba6
            com.google.android.gms.internal.firebase-auth-api.zzais<?> r0 = r6.zzr
            r0.zza(r8, r1)
            boolean r0 = r19.hasNext()
            if (r0 == 0) goto L_0x0ba4
            java.lang.Object r0 = r19.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r1 = r0
            goto L_0x0b8f
        L_0x0ba4:
            r1 = 0
            goto L_0x0b8f
        L_0x0ba6:
            com.google.android.gms.internal.firebase-auth-api.zzamg<?, ?> r0 = r6.zzq
            zza(r0, r7, (com.google.android.gms.internal.p002firebaseauthapi.zzanc) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzakt.zza(java.lang.Object, com.google.android.gms.internal.firebase-auth-api.zzanc):void");
    }

    private static <UT, UB> void zza(zzamg<UT, UB> zzamg, T t2, zzanc zzanc) throws IOException {
        zzamg.zzb(zzamg.zzd(t2), zzanc);
    }

    private final boolean zza(T t2, int i3, int i4, int i5, int i6) {
        if (i4 == 1048575) {
            return zzc(t2, i3);
        }
        return (i5 & i6) != 0;
    }

    private static boolean zza(Object obj, int i3, zzalh zzalh) {
        return zzalh.zzd(zzamm.zze(obj, (long) (i3 & 1048575)));
    }
}
