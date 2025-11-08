package com.appsamurai.storyly.storylylist;

import android.content.Context;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.config.styling.group.StoryGroupView;
import com.appsamurai.storyly.config.styling.group.StoryGroupViewFactory;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class c extends StoryGroupViewFactory {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4701a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f4702b;

    public c(@NotNull Context context, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4701a = context;
        this.f4702b = storylyConfig;
    }

    @NotNull
    public StoryGroupView createView() {
        return new a(this.f4701a, this.f4702b);
    }
}
