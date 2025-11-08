package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.core.AudioFocusManager;

/* renamed from: com.appsamurai.storyly.exoplayer2.core.a  reason: case insensitive filesystem */
public final /* synthetic */ class C0218a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AudioFocusManager.AudioFocusListener f4390a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f4391b;

    public /* synthetic */ C0218a(AudioFocusManager.AudioFocusListener audioFocusListener, int i3) {
        this.f4390a = audioFocusListener;
        this.f4391b = i3;
    }

    public final void run() {
        this.f4390a.lambda$onAudioFocusChange$0(this.f4391b);
    }
}
