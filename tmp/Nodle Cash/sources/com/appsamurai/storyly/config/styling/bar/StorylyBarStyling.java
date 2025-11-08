package com.appsamurai.storyly.config.styling.bar;

import androidx.annotation.Keep;
import com.appsamurai.storyly.StoryGroupListOrientation;
import com.appsamurai.storyly.StoryGroupSize;
import com.appsamurai.storyly.util.m;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001:\u00015B=\b\u0000\u0012\u0006\u0010\u0018\u001a\u00020\r\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0006\u0010\u001a\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b3\u00104J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J\u0017\u0010\n\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u000b\u0010\tJ\u0010\u0010\u0010\u001a\u00020\rHÀ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0013\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0014\u0010\u0012J\u0010\u0010\u0017\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0016\u0010\u0012JP\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00022\b\b\u0002\u0010\u001b\u001a\u00020\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010!\u001a\u00020 HÖ\u0001J\t\u0010\"\u001a\u00020\u0002HÖ\u0001J\u0013\u0010%\u001a\u00020$2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\u0018\u001a\u00020\r8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010&\u001a\u0004\b'\u0010\u000f\"\u0004\b(\u0010)R\"\u0010\u0019\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010*\u001a\u0004\b+\u0010\u0012\"\u0004\b,\u0010-R\"\u0010\u001a\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010*\u001a\u0004\b.\u0010\u0012\"\u0004\b/\u0010-R\"\u0010\u001b\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010*\u001a\u0004\b0\u0010\u0012\"\u0004\b1\u0010-R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001c\u00102R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001d\u00102¨\u00066"}, d2 = {"Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "", "", "component5", "()Ljava/lang/Integer;", "component6", "Lcom/appsamurai/storyly/StoryGroupSize;", "size", "getHorizontalPaddingBetweenItems$storyly_release", "(Lcom/appsamurai/storyly/StoryGroupSize;)I", "getHorizontalPaddingBetweenItems", "getVerticalPaddingBetweenItems$storyly_release", "getVerticalPaddingBetweenItems", "Lcom/appsamurai/storyly/StoryGroupListOrientation;", "component1$storyly_release", "()Lcom/appsamurai/storyly/StoryGroupListOrientation;", "component1", "component2$storyly_release", "()I", "component2", "component3$storyly_release", "component3", "component4$storyly_release", "component4", "orientation", "section", "horizontalEdgePadding", "verticalEdgePadding", "horizontalPaddingBetweenItems", "verticalPaddingBetweenItems", "copy", "(Lcom/appsamurai/storyly/StoryGroupListOrientation;IIILjava/lang/Integer;Ljava/lang/Integer;)Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "", "toString", "hashCode", "other", "", "equals", "Lcom/appsamurai/storyly/StoryGroupListOrientation;", "getOrientation$storyly_release", "setOrientation$storyly_release", "(Lcom/appsamurai/storyly/StoryGroupListOrientation;)V", "I", "getSection$storyly_release", "setSection$storyly_release", "(I)V", "getHorizontalEdgePadding$storyly_release", "setHorizontalEdgePadding$storyly_release", "getVerticalEdgePadding$storyly_release", "setVerticalEdgePadding$storyly_release", "Ljava/lang/Integer;", "<init>", "(Lcom/appsamurai/storyly/StoryGroupListOrientation;IIILjava/lang/Integer;Ljava/lang/Integer;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyBarStyling {
    private int horizontalEdgePadding;
    @Nullable
    private Integer horizontalPaddingBetweenItems;
    @NotNull
    private StoryGroupListOrientation orientation;
    private int section;
    private int verticalEdgePadding;
    @Nullable
    private Integer verticalPaddingBetweenItems;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\u000e\u001a\u00020\rR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling$Builder;", "", "Lcom/appsamurai/storyly/StoryGroupListOrientation;", "orientation", "setOrientation", "", "count", "setSection", "padding", "setHorizontalEdgePadding", "setVerticalEdgePadding", "setHorizontalPaddingBetweenItems", "setVerticalPaddingBetweenItems", "Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "build", "Lcom/appsamurai/storyly/StoryGroupListOrientation;", "section", "I", "horizontalEdgePadding", "verticalEdgePadding", "horizontalPaddingBetweenItems", "Ljava/lang/Integer;", "verticalPaddingBetweenItems", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private int horizontalEdgePadding = m.a((Number) 4);
        @Nullable
        private Integer horizontalPaddingBetweenItems;
        @NotNull
        private StoryGroupListOrientation orientation = StoryGroupListOrientation.Horizontal;
        private int section = 1;
        private int verticalEdgePadding = m.a((Number) 4);
        @Nullable
        private Integer verticalPaddingBetweenItems;

        @NotNull
        public final StorylyBarStyling build() {
            return new StorylyBarStyling(this.orientation, this.section, this.horizontalEdgePadding, this.verticalEdgePadding, this.horizontalPaddingBetweenItems, this.verticalPaddingBetweenItems);
        }

        @NotNull
        public final Builder setHorizontalEdgePadding(int i3) {
            this.horizontalEdgePadding = i3;
            return this;
        }

        @NotNull
        public final Builder setHorizontalPaddingBetweenItems(int i3) {
            this.horizontalPaddingBetweenItems = Integer.valueOf(i3);
            return this;
        }

        @NotNull
        public final Builder setOrientation(@NotNull StoryGroupListOrientation storyGroupListOrientation) {
            Intrinsics.checkNotNullParameter(storyGroupListOrientation, "orientation");
            this.orientation = storyGroupListOrientation;
            return this;
        }

        @NotNull
        public final Builder setSection(int i3) {
            this.section = i3;
            return this;
        }

        @NotNull
        public final Builder setVerticalEdgePadding(int i3) {
            this.verticalEdgePadding = i3;
            return this;
        }

        @NotNull
        public final Builder setVerticalPaddingBetweenItems(int i3) {
            this.verticalPaddingBetweenItems = Integer.valueOf(i3);
            return this;
        }
    }

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryGroupSize.values().length];
            iArr[StoryGroupSize.Small.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StorylyBarStyling(@NotNull StoryGroupListOrientation storyGroupListOrientation, int i3, int i4, int i5, @Nullable Integer num, @Nullable Integer num2) {
        Intrinsics.checkNotNullParameter(storyGroupListOrientation, "orientation");
        this.orientation = storyGroupListOrientation;
        this.section = i3;
        this.horizontalEdgePadding = i4;
        this.verticalEdgePadding = i5;
        this.horizontalPaddingBetweenItems = num;
        this.verticalPaddingBetweenItems = num2;
    }

    private final Integer component5() {
        return this.horizontalPaddingBetweenItems;
    }

    private final Integer component6() {
        return this.verticalPaddingBetweenItems;
    }

    public static /* synthetic */ StorylyBarStyling copy$default(StorylyBarStyling storylyBarStyling, StoryGroupListOrientation storyGroupListOrientation, int i3, int i4, int i5, Integer num, Integer num2, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            storyGroupListOrientation = storylyBarStyling.orientation;
        }
        if ((i6 & 2) != 0) {
            i3 = storylyBarStyling.section;
        }
        int i7 = i3;
        if ((i6 & 4) != 0) {
            i4 = storylyBarStyling.horizontalEdgePadding;
        }
        int i8 = i4;
        if ((i6 & 8) != 0) {
            i5 = storylyBarStyling.verticalEdgePadding;
        }
        int i9 = i5;
        if ((i6 & 16) != 0) {
            num = storylyBarStyling.horizontalPaddingBetweenItems;
        }
        Integer num3 = num;
        if ((i6 & 32) != 0) {
            num2 = storylyBarStyling.verticalPaddingBetweenItems;
        }
        return storylyBarStyling.copy(storyGroupListOrientation, i7, i8, i9, num3, num2);
    }

    @NotNull
    public final StoryGroupListOrientation component1$storyly_release() {
        return this.orientation;
    }

    public final int component2$storyly_release() {
        return this.section;
    }

    public final int component3$storyly_release() {
        return this.horizontalEdgePadding;
    }

    public final int component4$storyly_release() {
        return this.verticalEdgePadding;
    }

    @NotNull
    public final StorylyBarStyling copy(@NotNull StoryGroupListOrientation storyGroupListOrientation, int i3, int i4, int i5, @Nullable Integer num, @Nullable Integer num2) {
        Intrinsics.checkNotNullParameter(storyGroupListOrientation, "orientation");
        return new StorylyBarStyling(storyGroupListOrientation, i3, i4, i5, num, num2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyBarStyling)) {
            return false;
        }
        StorylyBarStyling storylyBarStyling = (StorylyBarStyling) obj;
        return this.orientation == storylyBarStyling.orientation && this.section == storylyBarStyling.section && this.horizontalEdgePadding == storylyBarStyling.horizontalEdgePadding && this.verticalEdgePadding == storylyBarStyling.verticalEdgePadding && Intrinsics.areEqual((Object) this.horizontalPaddingBetweenItems, (Object) storylyBarStyling.horizontalPaddingBetweenItems) && Intrinsics.areEqual((Object) this.verticalPaddingBetweenItems, (Object) storylyBarStyling.verticalPaddingBetweenItems);
    }

    public final int getHorizontalEdgePadding$storyly_release() {
        return this.horizontalEdgePadding;
    }

    public final int getHorizontalPaddingBetweenItems$storyly_release(@NotNull StoryGroupSize storyGroupSize) {
        Intrinsics.checkNotNullParameter(storyGroupSize, "size");
        if (a.$EnumSwitchMapping$0[storyGroupSize.ordinal()] == 1) {
            Integer num = this.horizontalPaddingBetweenItems;
            return num == null ? m.a((Number) 16) : num.intValue();
        }
        Integer num2 = this.horizontalPaddingBetweenItems;
        return num2 == null ? m.a((Number) 8) : num2.intValue();
    }

    @NotNull
    public final StoryGroupListOrientation getOrientation$storyly_release() {
        return this.orientation;
    }

    public final int getSection$storyly_release() {
        return this.section;
    }

    public final int getVerticalEdgePadding$storyly_release() {
        return this.verticalEdgePadding;
    }

    public final int getVerticalPaddingBetweenItems$storyly_release(@NotNull StoryGroupSize storyGroupSize) {
        Intrinsics.checkNotNullParameter(storyGroupSize, "size");
        if (a.$EnumSwitchMapping$0[storyGroupSize.ordinal()] == 1) {
            Integer num = this.verticalPaddingBetweenItems;
            return num == null ? m.a((Number) 16) : num.intValue();
        }
        Integer num2 = this.verticalPaddingBetweenItems;
        return num2 == null ? m.a((Number) 8) : num2.intValue();
    }

    public int hashCode() {
        int c3 = androidx.compose.animation.core.a.c(this.verticalEdgePadding, androidx.compose.animation.core.a.c(this.horizontalEdgePadding, androidx.compose.animation.core.a.c(this.section, this.orientation.hashCode() * 31, 31), 31), 31);
        Integer num = this.horizontalPaddingBetweenItems;
        int i3 = 0;
        int hashCode = (c3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.verticalPaddingBetweenItems;
        if (num2 != null) {
            i3 = num2.hashCode();
        }
        return hashCode + i3;
    }

    public final void setHorizontalEdgePadding$storyly_release(int i3) {
        this.horizontalEdgePadding = i3;
    }

    public final void setOrientation$storyly_release(@NotNull StoryGroupListOrientation storyGroupListOrientation) {
        Intrinsics.checkNotNullParameter(storyGroupListOrientation, "<set-?>");
        this.orientation = storyGroupListOrientation;
    }

    public final void setSection$storyly_release(int i3) {
        this.section = i3;
    }

    public final void setVerticalEdgePadding$storyly_release(int i3) {
        this.verticalEdgePadding = i3;
    }

    @NotNull
    public String toString() {
        return "StorylyBarStyling(orientation=" + this.orientation + ", section=" + this.section + ", horizontalEdgePadding=" + this.horizontalEdgePadding + ", verticalEdgePadding=" + this.verticalEdgePadding + ", horizontalPaddingBetweenItems=" + this.horizontalPaddingBetweenItems + ", verticalPaddingBetweenItems=" + this.verticalPaddingBetweenItems + ')';
    }
}
