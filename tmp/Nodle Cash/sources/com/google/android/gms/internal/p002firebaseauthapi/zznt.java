package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.ByteBuffer;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zznt  reason: invalid package */
public final class zznt {
    public static final zzxv zza = zzxv.zza(new byte[0]);

    public static final zzxv zza(int i3) {
        return zzxv.zza(ByteBuffer.allocate(5).put((byte) 0).putInt(i3).array());
    }

    public static final zzxv zzb(int i3) {
        return zzxv.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(i3).array());
    }
}
