package com.google.android.gms.internal.auth;

import java.util.List;

public final class zzhs extends zzev implements zzfy {
    /* access modifiers changed from: private */
    public static final zzhs zzb;
    private zzez zzd = zzev.zzf();

    static {
        zzhs zzhs = new zzhs();
        zzb = zzhs;
        zzev.zzk(zzhs.class, zzhs);
    }

    private zzhs() {
    }

    public static zzhs zzp(byte[] bArr) throws zzfb {
        return (zzhs) zzev.zzd(zzb, bArr);
    }

    public final Object zzn(int i3, Object obj, Object obj2) {
        int i4 = i3 - 1;
        if (i4 == 0) {
            return (byte) 1;
        }
        if (i4 == 2) {
            return zzev.zzh(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"zzd"});
        } else if (i4 == 3) {
            return new zzhs();
        } else {
            if (i4 == 4) {
                return new zzhr((zzhq) null);
            }
            if (i4 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final List zzq() {
        return this.zzd;
    }
}
