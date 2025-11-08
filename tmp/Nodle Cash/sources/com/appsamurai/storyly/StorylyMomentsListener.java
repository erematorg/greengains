package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J$\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/appsamurai/storyly/StorylyMomentsListener;", "", "Lcom/appsamurai/storyly/StorylyView;", "storylyView", "Lcom/appsamurai/storyly/StoryGroup;", "storyGroup", "Lcom/appsamurai/storyly/Story;", "story", "", "storyHeaderClicked", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public interface StorylyMomentsListener {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    public static final class DefaultImpls {
        public static void storyHeaderClicked(@NotNull StorylyMomentsListener storylyMomentsListener, @NotNull StorylyView storylyView, @Nullable StoryGroup storyGroup, @Nullable Story story) {
            Intrinsics.checkNotNullParameter(storylyMomentsListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
        }
    }

    void storyHeaderClicked(@NotNull StorylyView storylyView, @Nullable StoryGroup storyGroup, @Nullable Story story);
}
