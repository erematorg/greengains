package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import A.a;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcp;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcq;
import java.io.IOException;

public abstract class zzcq<MessageType extends zzcq<MessageType, BuilderType>, BuilderType extends zzcp<MessageType, BuilderType>> implements zzfm {
    protected int zza = 0;

    public int zzB(zzge zzge) {
        throw null;
    }

    public final zzdf zzC() {
        try {
            int zzF = zzF();
            zzdf zzdf = zzdf.zzb;
            byte[] bArr = new byte[zzF];
            zzdk zzdk = new zzdk(bArr, 0, zzF);
            zzab(zzdk);
            zzdk.zzC();
            return new zzde(bArr);
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a ByteString threw an IOException (should never happen)."), e3);
        }
    }

    public final byte[] zzD() {
        try {
            int zzF = zzF();
            byte[] bArr = new byte[zzF];
            zzdk zzdk = new zzdk(bArr, 0, zzF);
            zzab(zzdk);
            zzdk.zzC();
            return bArr;
        } catch (IOException e3) {
            throw new RuntimeException(a.l("Serializing ", getClass().getName(), " to a byte array threw an IOException (should never happen)."), e3);
        }
    }
}
