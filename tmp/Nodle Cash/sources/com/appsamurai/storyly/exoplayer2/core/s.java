package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayerImplInternal;

public final /* synthetic */ class s implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4538a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f4539b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4540c;

    public /* synthetic */ s(Object obj, Object obj2, int i3) {
        this.f4538a = i3;
        this.f4539b = obj;
        this.f4540c = obj2;
    }

    public final void run() {
        switch (this.f4538a) {
            case 0:
                ((ExoPlayerImpl) this.f4539b).lambda$new$1((ExoPlayerImplInternal.PlaybackInfoUpdate) this.f4540c);
                return;
            default:
                ((ExoPlayerImplInternal) this.f4539b).lambda$sendMessageToTargetThread$1((PlayerMessage) this.f4540c);
                return;
        }
    }
}
