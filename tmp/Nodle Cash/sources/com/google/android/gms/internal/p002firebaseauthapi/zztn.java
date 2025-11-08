package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zztn  reason: invalid package */
public final class zztn extends zzaje<zztn, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zztn zzc;
    private static volatile zzaky<zztn> zzd;
    private int zze;
    private zzto zzf;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zztn$zza */
    public static final class zza extends zzaje.zza<zztn, zza> implements zzakr {
        private zza() {
            super(zztn.zzc);
        }

        public final zza zza(zzto zzto) {
            zzi();
            ((zztn) this.zza).zza(zzto);
            return this;
        }

        public /* synthetic */ zza(zztm zztm) {
            this();
        }
    }

    static {
        zztn zztn = new zztn();
        zzc = zztn;
        zzaje.zza(zztn.class, zztn);
    }

    private zztn() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public final zzto zzc() {
        zzto zzto = this.zzf;
        return zzto == null ? zzto.zze() : zzto;
    }

    public static zztn zza(zzaho zzaho, zzaiq zzaiq) throws zzajk {
        return (zztn) zzaje.zza(zzc, zzaho, zzaiq);
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zztm.zza[i3 - 1]) {
            case 1:
                return new zztn();
            case 2:
                return new zza((zztm) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
            case 4:
                return zzc;
            case 5:
                zzaky<zztn> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zztn.class) {
                        try {
                            zzaky = zzd;
                            if (zzaky == null) {
                                zzaky = new zzaje.zzc<>(zzc);
                                zzd = zzaky;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
                return zzaky;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzto zzto) {
        zzto.getClass();
        this.zzf = zzto;
        this.zze |= 1;
    }
}
