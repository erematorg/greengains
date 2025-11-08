package com.google.android.gms.auth.api.identity;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

public final class zbb {
    private String zba;

    private zbb() {
    }

    public static final zbb zbc(zbc zbc) {
        String zbb = zbc.zbb();
        zbb zbb2 = new zbb();
        if (zbb != null) {
            zbb2.zba = Preconditions.checkNotEmpty(zbb);
        }
        return zbb2;
    }

    public final zbb zba(@NonNull String str) {
        this.zba = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zbc zbb() {
        return new zbc(this.zba);
    }

    public /* synthetic */ zbb(zba zba2) {
    }
}
