package com.google.firebase.remoteconfig;

import com.google.firebase.remoteconfig.ktx.RemoteConfigKt$configUpdates$1$registration$1;
import kotlinx.coroutines.channels.ProducerScope;

public final /* synthetic */ class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7164a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ProducerScope f7165b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ConfigUpdate f7166c;

    public /* synthetic */ d(ProducerScope producerScope, ConfigUpdate configUpdate, int i3) {
        this.f7164a = i3;
        this.f7165b = producerScope;
        this.f7166c = configUpdate;
    }

    public final void run() {
        switch (this.f7164a) {
            case 0:
                RemoteConfigKt$configUpdates$1$registration$1.onUpdate$lambda$0(this.f7165b, this.f7166c);
                return;
            default:
                RemoteConfigKt$configUpdates$1$registration$1.onUpdate$lambda$0(this.f7165b, this.f7166c);
                return;
        }
    }
}
