package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

final class zzu extends zzs {
    public zzu(int i3, int i4, Bundle bundle) {
        super(i3, i4, bundle);
    }

    public final void zza(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle("data");
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        zzd(bundle2);
    }

    public final boolean zzb() {
        return false;
    }
}
