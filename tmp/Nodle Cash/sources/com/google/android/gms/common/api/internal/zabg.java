package com.google.android.gms.common.api.internal;

abstract class zabg {
    private final zabf zaa;

    public zabg(zabf zabf) {
        this.zaa = zabf;
    }

    public abstract void zaa();

    public final void zab(zabi zabi) {
        zabi.zai.lock();
        try {
            if (zabi.zan == this.zaa) {
                zaa();
            }
        } finally {
            zabi.zai.unlock();
        }
    }
}
