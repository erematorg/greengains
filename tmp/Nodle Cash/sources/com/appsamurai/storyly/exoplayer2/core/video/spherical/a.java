package com.appsamurai.storyly.exoplayer2.core.video.spherical;

import android.graphics.SurfaceTexture;

public final /* synthetic */ class a implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SceneRenderer f4604a;

    public /* synthetic */ a(SceneRenderer sceneRenderer) {
        this.f4604a = sceneRenderer;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.f4604a.lambda$init$0(surfaceTexture);
    }
}
