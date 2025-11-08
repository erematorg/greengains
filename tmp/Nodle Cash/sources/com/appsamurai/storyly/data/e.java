package com.appsamurai.storyly.data;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static d f3644a = new d("https://api.storyly.io/sdk/v2.3/stories/{token}", "https://trk.storyly.io/traffic/{token}", "https://api.storyly.io/products/sdk/v2.3/{token}", "https://open.storyly.io/share/v2/{story_id}", "https://api.storyly.io/ugc/v1.31/stories/report", "https://ugc-trk.storyly.io/traffic", "https://api.storyly.io/ugc/v1.31/story-groups/ids", "https://api.storyly.io/ugc/v1.31/story-groups");

    static {
        Intrinsics.checkNotNullParameter("http://192.168.88.37:8000/base_demo_data_with_long_video.json", "storylyListEndpoint");
        Intrinsics.checkNotNullParameter("http://192.168.1.3:9099/traffic/{token}", "storylyAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter("https://192.168.1.7:8000/product.json", "storylyProductEndpoint");
        Intrinsics.checkNotNullParameter("http://open.qa.storyly.io/share/v2/{story_id}", "shareUrl");
        Intrinsics.checkNotNullParameter("http://192.168.1.20:8000/ugc.json", "momentsReportEndpoint");
        Intrinsics.checkNotNullParameter("https://192.168.1.20:8000/traffic", "momentsAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter("http://192.168.1.7:8000/id.json", "momentsStoryGroupIdsEndpoint");
        Intrinsics.checkNotNullParameter("http://192.168.1.7:8000/moments.json", "momentsStoryGroupPagedListEndpoint");
        Intrinsics.checkNotNullParameter("https://api.qa.storyly.io/sdk/v2.3/stories/{token}", "storylyListEndpoint");
        Intrinsics.checkNotNullParameter("https://trk.qa.storyly.io/traffic/{token}", "storylyAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter("https://api.qa.storyly.io/products/sdk/v2.3/{token}", "storylyProductEndpoint");
        Intrinsics.checkNotNullParameter("https://open.qa.storyly.io/share/v2/{story_id}", "shareUrl");
        Intrinsics.checkNotNullParameter("https://api.storyly.io/ugc/v1.31/stories/report", "momentsReportEndpoint");
        Intrinsics.checkNotNullParameter("https://ugc-trk.qa.storyly.io/traffic", "momentsAnalyticsEndpoint");
        Intrinsics.checkNotNullParameter("https://api.qa.storyly.io/ugc/v1.31/story-groups/ids", "momentsStoryGroupIdsEndpoint");
        Intrinsics.checkNotNullParameter("https://api.qa.storyly.io/ugc/v1.31/story-groups", "momentsStoryGroupPagedListEndpoint");
    }

    @NotNull
    public static final d a() {
        return f3644a;
    }
}
