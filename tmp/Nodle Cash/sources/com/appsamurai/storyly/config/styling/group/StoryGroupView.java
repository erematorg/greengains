package com.appsamurai.storyly.config.styling.group;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import com.appsamurai.storyly.StoryGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¨\u0006\n"}, d2 = {"Lcom/appsamurai/storyly/config/styling/group/StoryGroupView;", "Landroid/widget/FrameLayout;", "Lcom/appsamurai/storyly/StoryGroup;", "storyGroup", "", "populateView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public abstract class StoryGroupView extends FrameLayout {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryGroupView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public abstract void populateView(@Nullable StoryGroup storyGroup);
}
