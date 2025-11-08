package com.appsamurai.storyly.config.styling.moments;

import androidx.annotation.Keep;
import com.appsamurai.storyly.config.styling.a;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001:\u0001\u0012B\u0011\b\u0000\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0002HÆ\u0001J\t\u0010\t\u001a\u00020\bHÖ\u0001J\t\u0010\n\u001a\u00020\u0002HÖ\u0001J\u0013\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001c\u0010\u0006\u001a\u00020\u00028\u0000@\u0000X\u0004¢\u0006\f\n\u0004\b\u0006\u0010\u000e\u001a\u0004\b\u000f\u0010\u0004¨\u0006\u0013"}, d2 = {"Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsLinkCTAStyling;", "", "", "component1$storyly_release", "()I", "component1", "linkTextColor", "copy", "", "toString", "hashCode", "other", "", "equals", "I", "getLinkTextColor$storyly_release", "<init>", "(I)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyMomentsLinkCTAStyling {
    private final int linkTextColor;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0006\u001a\u00020\u0005R\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsLinkCTAStyling$Builder;", "", "", "color", "setLinkTextColor", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsLinkCTAStyling;", "build", "linkTextColor", "I", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private int linkTextColor = a.COLOR_AD27FF.a();

        @NotNull
        public final StorylyMomentsLinkCTAStyling build() {
            return new StorylyMomentsLinkCTAStyling(this.linkTextColor);
        }

        @NotNull
        public final Builder setLinkTextColor(int i3) {
            this.linkTextColor = i3;
            return this;
        }
    }

    public StorylyMomentsLinkCTAStyling(int i3) {
        this.linkTextColor = i3;
    }

    public static /* synthetic */ StorylyMomentsLinkCTAStyling copy$default(StorylyMomentsLinkCTAStyling storylyMomentsLinkCTAStyling, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i3 = storylyMomentsLinkCTAStyling.linkTextColor;
        }
        return storylyMomentsLinkCTAStyling.copy(i3);
    }

    public final int component1$storyly_release() {
        return this.linkTextColor;
    }

    @NotNull
    public final StorylyMomentsLinkCTAStyling copy(int i3) {
        return new StorylyMomentsLinkCTAStyling(i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StorylyMomentsLinkCTAStyling) && this.linkTextColor == ((StorylyMomentsLinkCTAStyling) obj).linkTextColor;
    }

    public final int getLinkTextColor$storyly_release() {
        return this.linkTextColor;
    }

    public int hashCode() {
        return Integer.hashCode(this.linkTextColor);
    }

    @NotNull
    public String toString() {
        return android.support.v4.media.session.a.p(new StringBuilder("StorylyMomentsLinkCTAStyling(linkTextColor="), this.linkTextColor, ')');
    }
}
