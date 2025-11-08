package com.appsamurai.storyly;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B/\u0012\u0010\b\u0001\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0011\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002HÆ\u0003J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J:\u0010\f\u001a\u00020\u00002\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003R!\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0006R\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/appsamurai/storyly/StoryGroupStyle;", "", "", "", "component1", "component2", "()Ljava/lang/Integer;", "Lcom/appsamurai/storyly/StoryGroupBadgeStyle;", "component3", "borderUnseenColors", "textUnseenColor", "badge", "copy", "(Ljava/util/List;Ljava/lang/Integer;Lcom/appsamurai/storyly/StoryGroupBadgeStyle;)Lcom/appsamurai/storyly/StoryGroupStyle;", "", "toString", "hashCode", "other", "", "equals", "Ljava/util/List;", "getBorderUnseenColors", "()Ljava/util/List;", "Ljava/lang/Integer;", "getTextUnseenColor", "Lcom/appsamurai/storyly/StoryGroupBadgeStyle;", "getBadge", "()Lcom/appsamurai/storyly/StoryGroupBadgeStyle;", "<init>", "(Ljava/util/List;Ljava/lang/Integer;Lcom/appsamurai/storyly/StoryGroupBadgeStyle;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryGroupStyle {
    @Nullable
    private final StoryGroupBadgeStyle badge;
    @Nullable
    private final List<Integer> borderUnseenColors;
    @Nullable
    private final Integer textUnseenColor;

    public StoryGroupStyle(@Nullable @ColorInt List<Integer> list, @Nullable @ColorInt Integer num, @Nullable StoryGroupBadgeStyle storyGroupBadgeStyle) {
        this.borderUnseenColors = list;
        this.textUnseenColor = num;
        this.badge = storyGroupBadgeStyle;
    }

    public static /* synthetic */ StoryGroupStyle copy$default(StoryGroupStyle storyGroupStyle, List<Integer> list, Integer num, StoryGroupBadgeStyle storyGroupBadgeStyle, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            list = storyGroupStyle.borderUnseenColors;
        }
        if ((i3 & 2) != 0) {
            num = storyGroupStyle.textUnseenColor;
        }
        if ((i3 & 4) != 0) {
            storyGroupBadgeStyle = storyGroupStyle.badge;
        }
        return storyGroupStyle.copy(list, num, storyGroupBadgeStyle);
    }

    @Nullable
    public final List<Integer> component1() {
        return this.borderUnseenColors;
    }

    @Nullable
    public final Integer component2() {
        return this.textUnseenColor;
    }

    @Nullable
    public final StoryGroupBadgeStyle component3() {
        return this.badge;
    }

    @NotNull
    public final StoryGroupStyle copy(@Nullable @ColorInt List<Integer> list, @Nullable @ColorInt Integer num, @Nullable StoryGroupBadgeStyle storyGroupBadgeStyle) {
        return new StoryGroupStyle(list, num, storyGroupBadgeStyle);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryGroupStyle)) {
            return false;
        }
        StoryGroupStyle storyGroupStyle = (StoryGroupStyle) obj;
        return Intrinsics.areEqual((Object) this.borderUnseenColors, (Object) storyGroupStyle.borderUnseenColors) && Intrinsics.areEqual((Object) this.textUnseenColor, (Object) storyGroupStyle.textUnseenColor) && Intrinsics.areEqual((Object) this.badge, (Object) storyGroupStyle.badge);
    }

    @Nullable
    public final StoryGroupBadgeStyle getBadge() {
        return this.badge;
    }

    @Nullable
    public final List<Integer> getBorderUnseenColors() {
        return this.borderUnseenColors;
    }

    @Nullable
    public final Integer getTextUnseenColor() {
        return this.textUnseenColor;
    }

    public int hashCode() {
        List<Integer> list = this.borderUnseenColors;
        int i3 = 0;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        Integer num = this.textUnseenColor;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        StoryGroupBadgeStyle storyGroupBadgeStyle = this.badge;
        if (storyGroupBadgeStyle != null) {
            i3 = storyGroupBadgeStyle.hashCode();
        }
        return hashCode2 + i3;
    }

    @NotNull
    public String toString() {
        return "StoryGroupStyle(borderUnseenColors=" + this.borderUnseenColors + ", textUnseenColor=" + this.textUnseenColor + ", badge=" + this.badge + ')';
    }
}
