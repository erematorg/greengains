package com.google.android.recaptcha.internal;

import java.util.List;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

public final class zzcg {
    @NotNull
    private final zzfl zza;

    public zzcg() {
        this(1);
    }

    @NotNull
    public final List zwk() {
        return zza();
    }

    @NotNull
    public final List zza() {
        return CollectionsKt.toList(this.zza);
    }

    public final boolean zzb(@NotNull List list) {
        this.zza.add(list);
        return true;
    }

    public zzcg(int i3) {
        this.zza = zzfl.zza(i3);
    }
}
