package com.appsamurai.storyly.exoplayer2.core.source;

import android.os.Handler;
import android.os.Message;

public final /* synthetic */ class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ConcatenatingMediaSource f4554a;

    public /* synthetic */ b(ConcatenatingMediaSource concatenatingMediaSource) {
        this.f4554a = concatenatingMediaSource;
    }

    public final boolean handleMessage(Message message) {
        return this.f4554a.handleMessage(message);
    }
}
