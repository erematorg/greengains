package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzin;
import com.google.android.recaptcha.internal.zzit;

public class zzin<MessageType extends zzit<MessageType, BuilderType>, BuilderType extends zzin<MessageType, BuilderType>> extends zzge<MessageType, BuilderType> {
    protected zzit zza;
    private final zzit zzb;

    public zzin(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzG()) {
            this.zza = messagetype.zzs();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zzd(Object obj, Object obj2) {
        zzkn.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* synthetic */ zzke zzY() {
        return this.zzb;
    }

    public final /* synthetic */ zzge zzb(zzgf zzgf) {
        zzg((zzit) zzgf);
        return this;
    }

    /* renamed from: zzf */
    public final zzin zza() {
        zzin zzin = (zzin) this.zzb.zzh(5, (Object) null, (Object) null);
        zzin.zza = zzk();
        return zzin;
    }

    public final zzin zzg(zzit zzit) {
        if (!this.zzb.equals(zzit)) {
            if (!this.zza.zzG()) {
                zzn();
            }
            zzd(this.zza, zzit);
        }
        return this;
    }

    /* renamed from: zzh */
    public final MessageType zzj() {
        MessageType zzi = zzk();
        if (zzi.zzo()) {
            return zzi;
        }
        throw new zzlk(zzi);
    }

    /* renamed from: zzi */
    public MessageType zzk() {
        if (!this.zza.zzG()) {
            return this.zza;
        }
        this.zza.zzB();
        return this.zza;
    }

    public final void zzm() {
        if (!this.zza.zzG()) {
            zzn();
        }
    }

    public void zzn() {
        zzit zzs = this.zzb.zzs();
        zzd(zzs, this.zza);
        this.zza = zzs;
    }

    public final boolean zzo() {
        return zzit.zzF(this.zza, false);
    }
}
