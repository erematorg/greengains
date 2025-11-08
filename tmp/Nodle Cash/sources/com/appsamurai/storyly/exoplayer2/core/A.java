package com.appsamurai.storyly.exoplayer2.core;

public final /* synthetic */ class A implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StreamVolumeManager f4389a;

    public /* synthetic */ A(StreamVolumeManager streamVolumeManager) {
        this.f4389a = streamVolumeManager;
    }

    public final void run() {
        this.f4389a.updateVolumeAndNotifyIfChanged();
    }
}
