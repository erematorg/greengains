package com.reown.sign.engine.use_case.calls;

import kotlin.Metadata;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"THIRTY_SECONDS_TIMEOUT", "Lkotlin/time/Duration;", "J", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PingUseCaseKt {
    /* access modifiers changed from: private */
    public static final long THIRTY_SECONDS_TIMEOUT = DurationKt.toDuration(30, DurationUnit.SECONDS);

    static {
        Duration.Companion companion = Duration.Companion;
    }
}
