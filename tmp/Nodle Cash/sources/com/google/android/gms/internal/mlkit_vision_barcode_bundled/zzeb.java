package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeh;

public class zzeb<MessageType extends zzeh<MessageType, BuilderType>, BuilderType extends zzeb<MessageType, BuilderType>> extends zzcp<MessageType, BuilderType> {
    protected zzeh zza;
    private final zzeh zzb;

    public zzeb(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzY()) {
            this.zza = messagetype.zzK();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzfu.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* bridge */ /* synthetic */ zzfm zzac() {
        throw null;
    }

    public final boolean zzad() {
        return zzeh.zzX(this.zza, false);
    }

    /* renamed from: zzf */
    public final zzeb zze() {
        zzeb zzeb = (zzeb) this.zzb.zzg(5, (Object) null, (Object) null);
        zzeb.zza = zzk();
        return zzeb;
    }

    public final zzeb zzg(zzeh zzeh) {
        if (!this.zzb.equals(zzeh)) {
            if (!this.zza.zzY()) {
                zzn();
            }
            zza(this.zza, zzeh);
        }
        return this;
    }

    /* renamed from: zzh */
    public final MessageType zzj() {
        MessageType zzi = zzk();
        if (zzeh.zzX(zzi, true)) {
            return zzi;
        }
        throw new zzgr(zzi);
    }

    /* renamed from: zzi */
    public MessageType zzk() {
        if (!this.zza.zzY()) {
            return this.zza;
        }
        this.zza.zzT();
        return this.zza;
    }

    public final void zzm() {
        if (!this.zza.zzY()) {
            zzn();
        }
    }

    public void zzn() {
        zzeh zzK = this.zzb.zzK();
        zza(zzK, this.zza);
        this.zza = zzK;
    }
}
