package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzaje;
import kotlin.text.Typography;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuw  reason: invalid package */
public final class zzuw extends zzaje<zzuw, zza> implements zzakr {
    /* access modifiers changed from: private */
    public static final zzuw zzc;
    private static volatile zzaky<zzuw> zzd;
    private String zze = "";
    private zzaho zzf = zzaho.zza;
    private int zzg;

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuw$zza */
    public static final class zza extends zzaje.zza<zzuw, zza> implements zzakr {
        private zza() {
            super(zzuw.zzc);
        }

        public final zza zza(zzb zzb) {
            zzi();
            ((zzuw) this.zza).zza(zzb);
            return this;
        }

        public /* synthetic */ zza(zzux zzux) {
            this();
        }

        public final zza zza(String str) {
            zzi();
            ((zzuw) this.zza).zza(str);
            return this;
        }

        public final zza zza(zzaho zzaho) {
            zzi();
            ((zzuw) this.zza).zza(zzaho);
            return this;
        }
    }

    static {
        zzuw zzuw = new zzuw();
        zzc = zzuw;
        zzaje.zza(zzuw.class, zzuw);
    }

    private zzuw() {
    }

    public static zza zza() {
        return (zza) zzc.zzm();
    }

    public static zzuw zzd() {
        return zzc;
    }

    public final zzb zzb() {
        zzb zza2 = zzb.zza(this.zzg);
        return zza2 == null ? zzb.UNRECOGNIZED : zza2;
    }

    public final zzaho zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zze;
    }

    public final Object zza(int i3, Object obj, Object obj2) {
        switch (zzux.zza[i3 - 1]) {
            case 1:
                return new zzuw();
            case 2:
                return new zza((zzux) null);
            case 3:
                return zzaje.zza((zzakp) zzc, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
            case 4:
                return zzc;
            case 5:
                zzaky<zzuw> zzaky = zzd;
                if (zzaky == null) {
                    synchronized (zzuw.class) {
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

    /* renamed from: com.google.android.gms.internal.firebase-auth-api.zzuw$zzb */
    public enum zzb implements zzajg {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private static final zzajj<zzb> zzg = null;
        private final int zzi;

        static {
            zzg = new zzuy();
        }

        private zzb(int i3) {
            this.zzi = i3;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("<");
            sb.append(zzb.class.getName());
            sb.append('@');
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            if (this != UNRECOGNIZED) {
                sb.append(" number=");
                sb.append(zza());
            }
            sb.append(" name=");
            sb.append(name());
            sb.append(Typography.greater);
            return sb.toString();
        }

        public final int zza() {
            if (this != UNRECOGNIZED) {
                return this.zzi;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static zzb zza(int i3) {
            if (i3 == 0) {
                return UNKNOWN_KEYMATERIAL;
            }
            if (i3 == 1) {
                return SYMMETRIC;
            }
            if (i3 == 2) {
                return ASYMMETRIC_PRIVATE;
            }
            if (i3 == 3) {
                return ASYMMETRIC_PUBLIC;
            }
            if (i3 != 4) {
                return null;
            }
            return REMOTE;
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzb zzb2) {
        this.zzg = zzb2.zza();
    }

    /* access modifiers changed from: private */
    public final void zza(String str) {
        str.getClass();
        this.zze = str;
    }

    /* access modifiers changed from: private */
    public final void zza(zzaho zzaho) {
        zzaho.getClass();
        this.zzf = zzaho;
    }
}
