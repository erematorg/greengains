package app.cash.sqldelight.coroutines;

import app.cash.sqldelight.Query;
import kotlinx.coroutines.channels.Channel;

public final /* synthetic */ class a implements Query.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Channel f2984a;

    public /* synthetic */ a(Channel channel) {
        this.f2984a = channel;
    }

    public final void queryResultsChanged() {
        FlowQuery$asFlow$1.invokeSuspend$lambda$0(this.f2984a);
    }
}
