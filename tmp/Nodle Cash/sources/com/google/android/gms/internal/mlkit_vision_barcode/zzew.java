package com.google.android.gms.internal.mlkit_vision_barcode;

import A.a;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

final class zzew extends zzef implements RunnableFuture {
    @CheckForNull
    private volatile zzer zzc;

    public zzew(zzxh zzxh) {
        this.zzc = new zzev(this, zzxh);
    }

    public final void run() {
        zzer zzer = this.zzc;
        if (zzer != null) {
            zzer.run();
        }
        this.zzc = null;
    }

    @CheckForNull
    public final String zzf() {
        zzer zzer = this.zzc;
        return zzer != null ? a.l("task=[", zzer.toString(), "]") : super.zzf();
    }

    public final void zzm() {
        zzer zzer;
        if (zzp() && (zzer = this.zzc) != null) {
            zzer.zze();
        }
        this.zzc = null;
    }
}
