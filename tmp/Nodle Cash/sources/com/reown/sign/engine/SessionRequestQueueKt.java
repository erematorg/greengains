package com.reown.sign.engine;

import com.reown.sign.engine.model.EngineDO;
import java.util.concurrent.ConcurrentLinkedQueue;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"sessionRequestEventsQueue", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/reown/sign/engine/model/EngineDO$SessionRequestEvent;", "getSessionRequestEventsQueue", "()Ljava/util/concurrent/ConcurrentLinkedQueue;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class SessionRequestQueueKt {
    @NotNull
    private static final ConcurrentLinkedQueue<EngineDO.SessionRequestEvent> sessionRequestEventsQueue = new ConcurrentLinkedQueue<>();

    @NotNull
    public static final ConcurrentLinkedQueue<EngineDO.SessionRequestEvent> getSessionRequestEventsQueue() {
        return sessionRequestEventsQueue;
    }
}
