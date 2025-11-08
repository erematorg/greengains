package com.appsamurai.storyly;

import androidx.annotation.ColorInt;
import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u001e\u0010\u001fJ\u000b\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0007\u0010\u0006J\u0012\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0004\b\t\u0010\nJ@\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0011\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u0019\u001a\u0004\b\u001a\u0010\u0006R\u001b\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u0019\u001a\u0004\b\u001b\u0010\u0006R\u001b\u0010\u000e\u001a\u0004\u0018\u00010\b8\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001c\u001a\u0004\b\u001d\u0010\n¨\u0006 "}, d2 = {"Lcom/appsamurai/storyly/StoryGroupBadgeStyle;", "", "", "component1", "", "component2", "()Ljava/lang/Integer;", "component3", "", "component4", "()Ljava/lang/Long;", "text", "textColor", "backgroundColor", "endTime", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/appsamurai/storyly/StoryGroupBadgeStyle;", "toString", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getText", "()Ljava/lang/String;", "Ljava/lang/Integer;", "getTextColor", "getBackgroundColor", "Ljava/lang/Long;", "getEndTime", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryGroupBadgeStyle {
    @Nullable
    private final Integer backgroundColor;
    @Nullable
    private final Long endTime;
    @Nullable
    private final String text;
    @Nullable
    private final Integer textColor;

    public StoryGroupBadgeStyle(@Nullable String str, @Nullable @ColorInt Integer num, @Nullable @ColorInt Integer num2, @Nullable Long l2) {
        this.text = str;
        this.textColor = num;
        this.backgroundColor = num2;
        this.endTime = l2;
    }

    public static /* synthetic */ StoryGroupBadgeStyle copy$default(StoryGroupBadgeStyle storyGroupBadgeStyle, String str, Integer num, Integer num2, Long l2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = storyGroupBadgeStyle.text;
        }
        if ((i3 & 2) != 0) {
            num = storyGroupBadgeStyle.textColor;
        }
        if ((i3 & 4) != 0) {
            num2 = storyGroupBadgeStyle.backgroundColor;
        }
        if ((i3 & 8) != 0) {
            l2 = storyGroupBadgeStyle.endTime;
        }
        return storyGroupBadgeStyle.copy(str, num, num2, l2);
    }

    @Nullable
    public final String component1() {
        return this.text;
    }

    @Nullable
    public final Integer component2() {
        return this.textColor;
    }

    @Nullable
    public final Integer component3() {
        return this.backgroundColor;
    }

    @Nullable
    public final Long component4() {
        return this.endTime;
    }

    @NotNull
    public final StoryGroupBadgeStyle copy(@Nullable String str, @Nullable @ColorInt Integer num, @Nullable @ColorInt Integer num2, @Nullable Long l2) {
        return new StoryGroupBadgeStyle(str, num, num2, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryGroupBadgeStyle)) {
            return false;
        }
        StoryGroupBadgeStyle storyGroupBadgeStyle = (StoryGroupBadgeStyle) obj;
        return Intrinsics.areEqual((Object) this.text, (Object) storyGroupBadgeStyle.text) && Intrinsics.areEqual((Object) this.textColor, (Object) storyGroupBadgeStyle.textColor) && Intrinsics.areEqual((Object) this.backgroundColor, (Object) storyGroupBadgeStyle.backgroundColor) && Intrinsics.areEqual((Object) this.endTime, (Object) storyGroupBadgeStyle.endTime);
    }

    @Nullable
    public final Integer getBackgroundColor() {
        return this.backgroundColor;
    }

    @Nullable
    public final Long getEndTime() {
        return this.endTime;
    }

    @Nullable
    public final String getText() {
        return this.text;
    }

    @Nullable
    public final Integer getTextColor() {
        return this.textColor;
    }

    public int hashCode() {
        String str = this.text;
        int i3 = 0;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.textColor;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.backgroundColor;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l2 = this.endTime;
        if (l2 != null) {
            i3 = l2.hashCode();
        }
        return hashCode3 + i3;
    }

    @NotNull
    public String toString() {
        return "StoryGroupBadgeStyle(text=" + this.text + ", textColor=" + this.textColor + ", backgroundColor=" + this.backgroundColor + ", endTime=" + this.endTime + ')';
    }
}
