package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.analytics.StorylyEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J&\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J(\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0015H\u0016J6\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016¨\u0006\u001b"}, d2 = {"Lcom/appsamurai/storyly/StorylyListener;", "", "Lcom/appsamurai/storyly/StorylyView;", "storylyView", "Lcom/appsamurai/storyly/Story;", "story", "", "storylyActionClicked", "", "Lcom/appsamurai/storyly/StoryGroup;", "storyGroupList", "Lcom/appsamurai/storyly/StorylyDataSource;", "dataSource", "storylyLoaded", "", "errorMessage", "storylyLoadFailed", "storylyStoryShown", "storylyStoryShowFailed", "storylyStoryDismissed", "storyGroup", "Lcom/appsamurai/storyly/StoryComponent;", "storyComponent", "storylyUserInteracted", "Lcom/appsamurai/storyly/analytics/StorylyEvent;", "event", "storylyEvent", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public interface StorylyListener {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
    public static final class DefaultImpls {
        public static void storylyActionClicked(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull Story story) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(story, "story");
        }

        public static void storylyEvent(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent, @Nullable StoryGroup storyGroup, @Nullable Story story, @Nullable StoryComponent storyComponent) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(storylyEvent, NotificationCompat.CATEGORY_EVENT);
        }

        public static void storylyLoadFailed(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull String str) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(str, "errorMessage");
        }

        public static void storylyLoaded(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull List<StoryGroup> list, @NotNull StorylyDataSource storylyDataSource) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(list, "storyGroupList");
            Intrinsics.checkNotNullParameter(storylyDataSource, "dataSource");
        }

        public static void storylyStoryDismissed(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
        }

        public static void storylyStoryShowFailed(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull String str) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(str, "errorMessage");
        }

        public static void storylyStoryShown(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
        }

        public static void storylyUserInteracted(@NotNull StorylyListener storylyListener, @NotNull StorylyView storylyView, @NotNull StoryGroup storyGroup, @NotNull Story story, @NotNull StoryComponent storyComponent) {
            Intrinsics.checkNotNullParameter(storylyListener, "this");
            Intrinsics.checkNotNullParameter(storylyView, "storylyView");
            Intrinsics.checkNotNullParameter(storyGroup, "storyGroup");
            Intrinsics.checkNotNullParameter(story, "story");
            Intrinsics.checkNotNullParameter(storyComponent, "storyComponent");
        }
    }

    void storylyActionClicked(@NotNull StorylyView storylyView, @NotNull Story story);

    void storylyEvent(@NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent, @Nullable StoryGroup storyGroup, @Nullable Story story, @Nullable StoryComponent storyComponent);

    void storylyLoadFailed(@NotNull StorylyView storylyView, @NotNull String str);

    void storylyLoaded(@NotNull StorylyView storylyView, @NotNull List<StoryGroup> list, @NotNull StorylyDataSource storylyDataSource);

    void storylyStoryDismissed(@NotNull StorylyView storylyView);

    void storylyStoryShowFailed(@NotNull StorylyView storylyView, @NotNull String str);

    void storylyStoryShown(@NotNull StorylyView storylyView);

    void storylyUserInteracted(@NotNull StorylyView storylyView, @NotNull StoryGroup storyGroup, @NotNull Story story, @NotNull StoryComponent storyComponent);
}
