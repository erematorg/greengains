package com.appsamurai.storyly.storylypresenter.storylylayer;

import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;

public final class t implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ r f6104a;

    public t(r rVar) {
        this.f6104a = rVar;
    }

    public void run() {
        r rVar = this.f6104a;
        ExoPlayer exoPlayer = rVar.f5967p;
        if (exoPlayer != null && exoPlayer.isPlaying()) {
            rVar.f5970s += 200;
            rVar.getOnSessionTimeUpdated$storyly_release().invoke(Long.valueOf(rVar.f5970s));
        }
        this.f6104a.getTimerHandler().postDelayed(this, 200);
    }
}
