package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\b\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0006\u0012\u0006\u0010\u0012\u001a\u00020\b\u0012\u0006\u0010\u0013\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\f¢\u0006\u0004\b+\u0010,J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\t\u0010\t\u001a\u00020\bHÆ\u0003J\t\u0010\u000b\u001a\u00020\nHÆ\u0003J\t\u0010\r\u001a\u00020\fHÆ\u0003JQ\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u000e\u001a\u00020\u00022\b\b\u0002\u0010\u000f\u001a\u00020\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\fHÆ\u0001J\t\u0010\u0016\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u000e\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u000f\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001a\u001a\u0004\b\u001e\u0010\u001cR\u0019\u0010\u0011\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u001f\u001a\u0004\b \u0010!R\u0019\u0010\u0012\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\"\u001a\u0004\b#\u0010$R\u0019\u0010\u0013\u001a\u00020\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u0013\u0010%\u001a\u0004\b&\u0010'R\u0019\u0010\u0014\u001a\u00020\f8\u0006@\u0006¢\u0006\f\n\u0004\b\u0014\u0010(\u001a\u0004\b)\u0010*¨\u0006-"}, d2 = {"Lcom/appsamurai/storyly/Story;", "", "", "component1", "component2", "component3", "", "component4", "", "component5", "", "component6", "Lcom/appsamurai/storyly/StoryMedia;", "component7", "uniqueId", "title", "name", "index", "seen", "currentTime", "media", "copy", "toString", "hashCode", "other", "equals", "Ljava/lang/String;", "getUniqueId", "()Ljava/lang/String;", "getTitle", "getName", "I", "getIndex", "()I", "Z", "getSeen", "()Z", "J", "getCurrentTime", "()J", "Lcom/appsamurai/storyly/StoryMedia;", "getMedia", "()Lcom/appsamurai/storyly/StoryMedia;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZJLcom/appsamurai/storyly/StoryMedia;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class Story {
    private final long currentTime;
    private final int index;
    @NotNull
    private final StoryMedia media;
    @Nullable
    private final String name;
    private final boolean seen;
    @NotNull
    private final String title;
    @NotNull
    private final String uniqueId;

    public Story(@NotNull String str, @NotNull String str2, @Nullable String str3, int i3, boolean z2, long j2, @NotNull StoryMedia storyMedia) {
        Intrinsics.checkNotNullParameter(str, "uniqueId");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(storyMedia, SVGParser.XML_STYLESHEET_ATTR_MEDIA);
        this.uniqueId = str;
        this.title = str2;
        this.name = str3;
        this.index = i3;
        this.seen = z2;
        this.currentTime = j2;
        this.media = storyMedia;
    }

    public static /* synthetic */ Story copy$default(Story story, String str, String str2, String str3, int i3, boolean z2, long j2, StoryMedia storyMedia, int i4, Object obj) {
        Story story2 = story;
        return story.copy((i4 & 1) != 0 ? story2.uniqueId : str, (i4 & 2) != 0 ? story2.title : str2, (i4 & 4) != 0 ? story2.name : str3, (i4 & 8) != 0 ? story2.index : i3, (i4 & 16) != 0 ? story2.seen : z2, (i4 & 32) != 0 ? story2.currentTime : j2, (i4 & 64) != 0 ? story2.media : storyMedia);
    }

    @NotNull
    public final String component1() {
        return this.uniqueId;
    }

    @NotNull
    public final String component2() {
        return this.title;
    }

    @Nullable
    public final String component3() {
        return this.name;
    }

    public final int component4() {
        return this.index;
    }

    public final boolean component5() {
        return this.seen;
    }

    public final long component6() {
        return this.currentTime;
    }

    @NotNull
    public final StoryMedia component7() {
        return this.media;
    }

    @NotNull
    public final Story copy(@NotNull String str, @NotNull String str2, @Nullable String str3, int i3, boolean z2, long j2, @NotNull StoryMedia storyMedia) {
        Intrinsics.checkNotNullParameter(str, "uniqueId");
        Intrinsics.checkNotNullParameter(str2, "title");
        StoryMedia storyMedia2 = storyMedia;
        Intrinsics.checkNotNullParameter(storyMedia2, SVGParser.XML_STYLESHEET_ATTR_MEDIA);
        return new Story(str, str2, str3, i3, z2, j2, storyMedia2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Story)) {
            return false;
        }
        Story story = (Story) obj;
        return Intrinsics.areEqual((Object) this.uniqueId, (Object) story.uniqueId) && Intrinsics.areEqual((Object) this.title, (Object) story.title) && Intrinsics.areEqual((Object) this.name, (Object) story.name) && this.index == story.index && this.seen == story.seen && this.currentTime == story.currentTime && Intrinsics.areEqual((Object) this.media, (Object) story.media);
    }

    public final long getCurrentTime() {
        return this.currentTime;
    }

    public final int getIndex() {
        return this.index;
    }

    @NotNull
    public final StoryMedia getMedia() {
        return this.media;
    }

    @Nullable
    public final String getName() {
        return this.name;
    }

    public final boolean getSeen() {
        return this.seen;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final String getUniqueId() {
        return this.uniqueId;
    }

    public int hashCode() {
        int i3 = a.i(this.title, this.uniqueId.hashCode() * 31, 31);
        String str = this.name;
        int c3 = a.c(this.index, (i3 + (str == null ? 0 : str.hashCode())) * 31, 31);
        boolean z2 = this.seen;
        if (z2) {
            z2 = true;
        }
        return this.media.hashCode() + a.D(this.currentTime, (c3 + (z2 ? 1 : 0)) * 31, 31);
    }

    @NotNull
    public String toString() {
        return "Story(uniqueId=" + this.uniqueId + ", title=" + this.title + ", name=" + this.name + ", index=" + this.index + ", seen=" + this.seen + ", currentTime=" + this.currentTime + ", media=" + this.media + ')';
    }
}
