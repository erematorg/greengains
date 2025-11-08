package com.appsamurai.storyly.exoplayer2.core;

import com.appsamurai.storyly.exoplayer2.common.util.Clock;
import com.appsamurai.storyly.exoplayer2.core.analytics.DefaultAnalyticsCollector;
import com.google.common.base.Function;

public final /* synthetic */ class e implements Function {
    public final Object apply(Object obj) {
        return new DefaultAnalyticsCollector((Clock) obj);
    }
}
