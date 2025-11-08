package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzez;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzfa  reason: invalid package */
public class zzfa extends zzcu {
    private final zzez zza;
    private final zzxv zzb;
    @Nullable
    private final Integer zzc;

    private zzfa(zzez zzez, zzxv zzxv, @Nullable Integer num) {
        this.zza = zzez;
        this.zzb = zzxv;
        this.zzc = num;
    }

    public static zzfa zza(zzez zzez, @Nullable Integer num) throws GeneralSecurityException {
        zzxv zzxv;
        if (zzez.zzb() == zzez.zza.zza) {
            if (num != null) {
                zzxv = zzxv.zza(ByteBuffer.allocate(5).put((byte) 1).putInt(num.intValue()).array());
            } else {
                throw new GeneralSecurityException("For given Variant TINK the value of idRequirement must be non-null");
            }
        } else if (zzez.zzb() != zzez.zza.zzb) {
            throw new GeneralSecurityException("Unknown Variant: ".concat(String.valueOf(zzez.zzb())));
        } else if (num == null) {
            zzxv = zzxv.zza(new byte[0]);
        } else {
            throw new GeneralSecurityException("For given Variant NO_PREFIX the value of idRequirement must be null");
        }
        return new zzfa(zzez, zzxv, num);
    }

    public final zzez zzb() {
        return this.zza;
    }

    public final zzxv zzc() {
        return this.zzb;
    }

    public final Integer zza() {
        return this.zzc;
    }
}
