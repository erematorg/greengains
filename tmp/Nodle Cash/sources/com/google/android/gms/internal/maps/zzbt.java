package com.google.android.gms.internal.maps;

import javax.annotation.CheckForNull;

final class zzbt extends zzbl {
    static final zzbl zza = new zzbt((Object) null, new Object[0], 0);
    final transient Object[] zzb;
    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzbt(@CheckForNull Object obj, Object[] objArr, int i3) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i3;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [short[], byte[]], vars: [r5v5 ?, r5v12 ?, r5v8 ?, r5v13 ?]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:102)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:78)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:69)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:51)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:32)
        */
    public static com.google.android.gms.internal.maps.zzbt zzg(int r17, java.lang.Object[] r18, com.google.android.gms.internal.maps.zzbk r19) {
        /*
            r0 = r17
            r1 = r18
            if (r0 != 0) goto L_0x000b
            com.google.android.gms.internal.maps.zzbl r0 = zza
            com.google.android.gms.internal.maps.zzbt r0 = (com.google.android.gms.internal.maps.zzbt) r0
            return r0
        L_0x000b:
            r2 = 0
            r3 = 0
            r4 = 1
            if (r0 != r4) goto L_0x0023
            r0 = r1[r3]
            java.util.Objects.requireNonNull(r0)
            r3 = r1[r4]
            java.util.Objects.requireNonNull(r3)
            com.google.android.gms.internal.maps.zzbd.zza(r0, r3)
            com.google.android.gms.internal.maps.zzbt r0 = new com.google.android.gms.internal.maps.zzbt
            r0.<init>(r2, r1, r4)
            return r0
        L_0x0023:
            int r5 = r1.length
            int r5 = r5 >> r4
            java.lang.String r6 = "index"
            com.google.android.gms.internal.maps.zzba.zzb(r0, r5, r6)
            int r5 = com.google.android.gms.internal.maps.zzbm.zzf(r17)
            if (r0 != r4) goto L_0x0041
            r0 = r1[r3]
            java.util.Objects.requireNonNull(r0)
            r5 = r1[r4]
            java.util.Objects.requireNonNull(r5)
            com.google.android.gms.internal.maps.zzbd.zza(r0, r5)
            r0 = r4
        L_0x003e:
            r5 = 2
            goto L_0x0187
        L_0x0041:
            int r7 = r5 + -1
            r8 = 128(0x80, float:1.794E-43)
            r9 = 3
            r10 = -1
            if (r5 > r8) goto L_0x00b3
            byte[] r5 = new byte[r5]
            java.util.Arrays.fill(r5, r10)
            r8 = r3
            r10 = r8
        L_0x0050:
            if (r8 >= r0) goto L_0x009f
            int r11 = r10 + r10
            int r12 = r8 + r8
            r13 = r1[r12]
            java.util.Objects.requireNonNull(r13)
            r12 = r12 ^ r4
            r12 = r1[r12]
            java.util.Objects.requireNonNull(r12)
            com.google.android.gms.internal.maps.zzbd.zza(r13, r12)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.maps.zzbe.zza(r14)
        L_0x006c:
            r14 = r14 & r7
            byte r15 = r5[r14]
            r6 = 255(0xff, float:3.57E-43)
            r15 = r15 & r6
            if (r15 != r6) goto L_0x0082
            byte r6 = (byte) r11
            r5[r14] = r6
            if (r10 >= r8) goto L_0x007f
            r1[r11] = r13
            r6 = r11 ^ 1
            r1[r6] = r12
        L_0x007f:
            int r10 = r10 + 1
            goto L_0x0099
        L_0x0082:
            r6 = r1[r15]
            boolean r6 = r13.equals(r6)
            if (r6 == 0) goto L_0x009c
            r2 = r15 ^ 1
            com.google.android.gms.internal.maps.zzbj r6 = new com.google.android.gms.internal.maps.zzbj
            r11 = r1[r2]
            java.util.Objects.requireNonNull(r11)
            r6.<init>(r13, r12, r11)
            r1[r2] = r12
            r2 = r6
        L_0x0099:
            int r8 = r8 + 1
            goto L_0x0050
        L_0x009c:
            int r14 = r14 + 1
            goto L_0x006c
        L_0x009f:
            if (r10 != r0) goto L_0x00a3
        L_0x00a1:
            r2 = r5
            goto L_0x003e
        L_0x00a3:
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r3] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            r6[r4] = r5
            r5 = 2
            r6[r5] = r2
        L_0x00b0:
            r2 = r6
            goto L_0x0187
        L_0x00b3:
            r6 = 32768(0x8000, float:4.5918E-41)
            if (r5 > r6) goto L_0x0120
            short[] r5 = new short[r5]
            java.util.Arrays.fill(r5, r10)
            r6 = r3
            r8 = r6
        L_0x00bf:
            if (r6 >= r0) goto L_0x010f
            int r10 = r8 + r8
            int r11 = r6 + r6
            r12 = r1[r11]
            java.util.Objects.requireNonNull(r12)
            r11 = r11 ^ r4
            r11 = r1[r11]
            java.util.Objects.requireNonNull(r11)
            com.google.android.gms.internal.maps.zzbd.zza(r12, r11)
            int r13 = r12.hashCode()
            int r13 = com.google.android.gms.internal.maps.zzbe.zza(r13)
        L_0x00db:
            r13 = r13 & r7
            short r14 = r5[r13]
            char r14 = (char) r14
            r15 = 65535(0xffff, float:9.1834E-41)
            if (r14 != r15) goto L_0x00f2
            short r14 = (short) r10
            r5[r13] = r14
            if (r8 >= r6) goto L_0x00ef
            r1[r10] = r12
            r10 = r10 ^ 1
            r1[r10] = r11
        L_0x00ef:
            int r8 = r8 + 1
            goto L_0x0109
        L_0x00f2:
            r15 = r1[r14]
            boolean r15 = r12.equals(r15)
            if (r15 == 0) goto L_0x010c
            r2 = r14 ^ 1
            com.google.android.gms.internal.maps.zzbj r10 = new com.google.android.gms.internal.maps.zzbj
            r13 = r1[r2]
            java.util.Objects.requireNonNull(r13)
            r10.<init>(r12, r11, r13)
            r1[r2] = r11
            r2 = r10
        L_0x0109:
            int r6 = r6 + 1
            goto L_0x00bf
        L_0x010c:
            int r13 = r13 + 1
            goto L_0x00db
        L_0x010f:
            if (r8 != r0) goto L_0x0112
            goto L_0x00a1
        L_0x0112:
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r3] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            r6[r4] = r5
            r5 = 2
            r6[r5] = r2
            goto L_0x00b0
        L_0x0120:
            int[] r5 = new int[r5]
            java.util.Arrays.fill(r5, r10)
            r6 = r3
            r8 = r6
        L_0x0127:
            if (r6 >= r0) goto L_0x0174
            int r11 = r8 + r8
            int r12 = r6 + r6
            r13 = r1[r12]
            java.util.Objects.requireNonNull(r13)
            r12 = r12 ^ r4
            r12 = r1[r12]
            java.util.Objects.requireNonNull(r12)
            com.google.android.gms.internal.maps.zzbd.zza(r13, r12)
            int r14 = r13.hashCode()
            int r14 = com.google.android.gms.internal.maps.zzbe.zza(r14)
        L_0x0143:
            r14 = r14 & r7
            r15 = r5[r14]
            if (r15 != r10) goto L_0x0155
            r5[r14] = r11
            if (r8 >= r6) goto L_0x0152
            r1[r11] = r13
            r11 = r11 ^ 1
            r1[r11] = r12
        L_0x0152:
            int r8 = r8 + 1
            goto L_0x016c
        L_0x0155:
            r10 = r1[r15]
            boolean r10 = r13.equals(r10)
            if (r10 == 0) goto L_0x0170
            r2 = r15 ^ 1
            com.google.android.gms.internal.maps.zzbj r10 = new com.google.android.gms.internal.maps.zzbj
            r11 = r1[r2]
            java.util.Objects.requireNonNull(r11)
            r10.<init>(r13, r12, r11)
            r1[r2] = r12
            r2 = r10
        L_0x016c:
            int r6 = r6 + 1
            r10 = -1
            goto L_0x0127
        L_0x0170:
            int r14 = r14 + 1
            r10 = -1
            goto L_0x0143
        L_0x0174:
            if (r8 != r0) goto L_0x0178
            goto L_0x00a1
        L_0x0178:
            java.lang.Object[] r6 = new java.lang.Object[r9]
            r6[r3] = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            r6[r4] = r5
            r5 = 2
            r6[r5] = r2
            goto L_0x00b0
        L_0x0187:
            boolean r6 = r2 instanceof java.lang.Object[]
            if (r6 == 0) goto L_0x01aa
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            r0 = r2[r5]
            com.google.android.gms.internal.maps.zzbj r0 = (com.google.android.gms.internal.maps.zzbj) r0
            r5 = r19
            r5.zzc = r0
            r0 = r2[r3]
            r2 = r2[r4]
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            int r3 = r2 + r2
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r3)
            r16 = r2
            r2 = r0
            r0 = r16
        L_0x01aa:
            com.google.android.gms.internal.maps.zzbt r3 = new com.google.android.gms.internal.maps.zzbt
            r3.<init>(r2, r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.maps.zzbt.zzg(int, java.lang.Object[], com.google.android.gms.internal.maps.zzbk):com.google.android.gms.internal.maps.zzbt");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x009e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x009f A[RETURN] */
    @javax.annotation.CheckForNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0006
        L_0x0003:
            r8 = r0
            goto L_0x009c
        L_0x0006:
            int r1 = r8.zzd
            java.lang.Object[] r2 = r8.zzb
            r3 = 1
            if (r1 != r3) goto L_0x0020
            r8 = 0
            r8 = r2[r8]
            java.util.Objects.requireNonNull(r8)
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0003
            r8 = r2[r3]
            java.util.Objects.requireNonNull(r8)
            goto L_0x009c
        L_0x0020:
            java.lang.Object r8 = r8.zzc
            if (r8 != 0) goto L_0x0025
            goto L_0x0003
        L_0x0025:
            boolean r1 = r8 instanceof byte[]
            r4 = -1
            if (r1 == 0) goto L_0x0051
            r1 = r8
            byte[] r1 = (byte[]) r1
            int r8 = r1.length
            int r5 = r8 + -1
            int r8 = r9.hashCode()
            int r8 = com.google.android.gms.internal.maps.zzbe.zza(r8)
        L_0x0038:
            r8 = r8 & r5
            byte r4 = r1[r8]
            r6 = 255(0xff, float:3.57E-43)
            r4 = r4 & r6
            if (r4 != r6) goto L_0x0041
            goto L_0x0003
        L_0x0041:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x004e
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L_0x009c
        L_0x004e:
            int r8 = r8 + 1
            goto L_0x0038
        L_0x0051:
            boolean r1 = r8 instanceof short[]
            if (r1 == 0) goto L_0x007d
            r1 = r8
            short[] r1 = (short[]) r1
            int r8 = r1.length
            int r5 = r8 + -1
            int r8 = r9.hashCode()
            int r8 = com.google.android.gms.internal.maps.zzbe.zza(r8)
        L_0x0063:
            r8 = r8 & r5
            short r4 = r1[r8]
            char r4 = (char) r4
            r6 = 65535(0xffff, float:9.1834E-41)
            if (r4 != r6) goto L_0x006d
            goto L_0x0003
        L_0x006d:
            r6 = r2[r4]
            boolean r6 = r9.equals(r6)
            if (r6 == 0) goto L_0x007a
            r8 = r4 ^ 1
            r8 = r2[r8]
            goto L_0x009c
        L_0x007a:
            int r8 = r8 + 1
            goto L_0x0063
        L_0x007d:
            int[] r8 = (int[]) r8
            int r1 = r8.length
            int r1 = r1 + r4
            int r5 = r9.hashCode()
            int r5 = com.google.android.gms.internal.maps.zzbe.zza(r5)
        L_0x0089:
            r5 = r5 & r1
            r6 = r8[r5]
            if (r6 != r4) goto L_0x0090
            goto L_0x0003
        L_0x0090:
            r7 = r2[r6]
            boolean r7 = r9.equals(r7)
            if (r7 == 0) goto L_0x00a0
            r8 = r6 ^ 1
            r8 = r2[r8]
        L_0x009c:
            if (r8 != 0) goto L_0x009f
            return r0
        L_0x009f:
            return r8
        L_0x00a0:
            int r5 = r5 + 1
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.maps.zzbt.get(java.lang.Object):java.lang.Object");
    }

    public final int size() {
        return this.zzd;
    }

    public final zzbf zza() {
        return new zzbs(this.zzb, 1, this.zzd);
    }

    public final zzbm zzd() {
        return new zzbq(this, this.zzb, 0, this.zzd);
    }

    public final zzbm zze() {
        return new zzbr(this, new zzbs(this.zzb, 0, this.zzd));
    }
}
