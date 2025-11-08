package com.google.android.gms.internal.measurement;

import androidx.camera.camera2.internal.C0118y;
import com.google.android.gms.internal.measurement.zzjh;
import com.google.android.gms.internal.measurement.zzji;
import java.io.IOException;

public abstract class zzjh<MessageType extends zzji<MessageType, BuilderType>, BuilderType extends zzjh<MessageType, BuilderType>> implements zzmk {
    /* renamed from: zza */
    public abstract BuilderType zzb(zzkg zzkg, zzkp zzkp) throws IOException;

    public BuilderType zza(byte[] bArr, int i3, int i4) throws zzlk {
        try {
            zzkg zza = zzkg.zza(bArr, 0, i4, false);
            zzb(zza, zzkp.zza);
            zza.zzb(0);
            return this;
        } catch (zzlk e3) {
            throw e3;
        } catch (IOException e4) {
            throw new RuntimeException(zza("byte array"), e4);
        }
    }

    /* renamed from: zzaf */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i3, int i4, zzkp zzkp) throws zzlk {
        try {
            zzkg zza = zzkg.zza(bArr, 0, i4, false);
            zzb(zza, zzkp);
            zza.zzb(0);
            return this;
        } catch (zzlk e3) {
            throw e3;
        } catch (IOException e4) {
            throw new RuntimeException(zza("byte array"), e4);
        }
    }

    public final /* synthetic */ zzmk zza(byte[] bArr) throws zzlk {
        return zza(bArr, 0, bArr.length);
    }

    public final /* synthetic */ zzmk zza(byte[] bArr, zzkp zzkp) throws zzlk {
        return zza(bArr, 0, bArr.length, zzkp);
    }

    private final String zza(String str) {
        return C0118y.g("Reading ", getClass().getName(), " from a ", str, " threw an IOException (should never happen).");
    }
}
