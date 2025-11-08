package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.reflect.Type;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaiy  reason: invalid package */
public enum zzaiy {
    DOUBLE(0, r4, r42),
    FLOAT(1, r4, r43),
    INT64(2, r4, r5),
    UINT64(3, r4, r5),
    INT32(4, r4, r54),
    FIXED64(5, r4, r55),
    FIXED32(6, r4, r54),
    BOOL(7, r4, r49),
    STRING(8, r4, r33),
    MESSAGE(9, r4, r56),
    BYTES(10, r4, r35),
    UINT32(11, r4, r54),
    ENUM(12, r4, r51),
    SFIXED32(13, r4, r54),
    SFIXED64(14, r4, r55),
    SINT32(15, r4, r54),
    SINT64(16, r4, r55),
    GROUP(17, r4, r56),
    DOUBLE_LIST(18, r4, r42),
    FLOAT_LIST(19, r4, r43),
    INT64_LIST(20, r4, r5),
    UINT64_LIST(21, r4, r5),
    INT32_LIST(22, r4, r54),
    FIXED64_LIST(23, r4, r55),
    FIXED32_LIST(24, r4, r54),
    BOOL_LIST(25, r4, r49),
    STRING_LIST(26, r4, r33),
    MESSAGE_LIST(27, r4, r56),
    BYTES_LIST(28, r4, r35),
    UINT32_LIST(29, r4, r54),
    ENUM_LIST(30, r4, r51),
    SFIXED32_LIST(31, r4, r54),
    SFIXED64_LIST(32, r4, r55),
    SINT32_LIST(33, r4, r54),
    SINT64_LIST(34, r4, r55),
    DOUBLE_LIST_PACKED(35, r4, r42),
    FLOAT_LIST_PACKED(36, r4, r43),
    INT64_LIST_PACKED(37, r4, r5),
    UINT64_LIST_PACKED(38, r4, r5),
    INT32_LIST_PACKED(39, r4, r54),
    FIXED64_LIST_PACKED(40, r4, r55),
    FIXED32_LIST_PACKED(41, r4, r54),
    BOOL_LIST_PACKED(42, r4, r49),
    UINT32_LIST_PACKED(43, r4, r54),
    ENUM_LIST_PACKED(44, r4, r51),
    SFIXED32_LIST_PACKED(45, r4, r54),
    SFIXED64_LIST_PACKED(46, r4, r55),
    SINT32_LIST_PACKED(47, r4, r54),
    SINT64_LIST_PACKED(48, r4, r55),
    GROUP_LIST(49, r57, r56),
    MAP(50, zzaja.MAP, zzajm.VOID);
    
    private static final zzaiy[] zzaz = null;
    private static final Type[] zzba = null;
    private final zzajm zzbc;
    private final int zzbd;
    private final zzaja zzbe;
    private final Class<?> zzbf;
    private final boolean zzbg;

    static {
        zzba = new Type[0];
        zzaiy[] values = values();
        zzaz = new zzaiy[values.length];
        for (zzaiy zzaiy : values) {
            zzaz[zzaiy.zzbd] = zzaiy;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        r1 = com.google.android.gms.internal.p002firebaseauthapi.zzajb.zza[r5.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzaiy(int r3, com.google.android.gms.internal.p002firebaseauthapi.zzaja r4, com.google.android.gms.internal.p002firebaseauthapi.zzajm r5) {
        /*
            r0 = this;
            r0.<init>(r1, r2)
            r0.zzbd = r3
            r0.zzbe = r4
            r0.zzbc = r5
            int r1 = r4.ordinal()
            r2 = 3
            r3 = 1
            if (r1 == r3) goto L_0x001e
            if (r1 == r2) goto L_0x0017
            r1 = 0
            r0.zzbf = r1
            goto L_0x0024
        L_0x0017:
            java.lang.Class r1 = r5.zza()
            r0.zzbf = r1
            goto L_0x0024
        L_0x001e:
            java.lang.Class r1 = r5.zza()
            r0.zzbf = r1
        L_0x0024:
            com.google.android.gms.internal.firebase-auth-api.zzaja r1 = com.google.android.gms.internal.p002firebaseauthapi.zzaja.SCALAR
            if (r4 != r1) goto L_0x0038
            int[] r1 = com.google.android.gms.internal.p002firebaseauthapi.zzajb.zza
            int r4 = r5.ordinal()
            r1 = r1[r4]
            if (r1 == r3) goto L_0x0038
            r4 = 2
            if (r1 == r4) goto L_0x0038
            if (r1 == r2) goto L_0x0038
            goto L_0x0039
        L_0x0038:
            r3 = 0
        L_0x0039:
            r0.zzbg = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.p002firebaseauthapi.zzaiy.<init>(java.lang.String, int, int, com.google.android.gms.internal.firebase-auth-api.zzaja, com.google.android.gms.internal.firebase-auth-api.zzajm):void");
    }

    public final int zza() {
        return this.zzbd;
    }
}
