package com.appsamurai.storyly.exoplayer2.core.audio;

import com.appsamurai.storyly.exoplayer2.core.audio.AudioRendererEventListener;
import org.web3j.protocol.websocket.WebSocketService;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4476a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ long f4477b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f4478c;

    public /* synthetic */ e(Object obj, long j2, int i3) {
        this.f4476a = i3;
        this.f4478c = obj;
        this.f4477b = j2;
    }

    public final void run() {
        switch (this.f4476a) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f4478c).lambda$positionAdvancing$3(this.f4477b);
                return;
            default:
                ((WebSocketService) this.f4478c).lambda$setRequestTimeout$3(this.f4477b);
                return;
        }
    }
}
