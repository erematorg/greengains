package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b1\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\u0006\u0010\u0017\u001a\u00020\u0002\u0012\u0006\u0010\u0018\u001a\u00020\u0002\u0012\u0006\u0010\u0019\u001a\u00020\u0002\u0012\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\u001c\u001a\u00020\t\u0012\u0006\u0010\u001d\u001a\u00020\u000b\u0012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u001f\u001a\u00020\u000b\u0012\u0006\u0010 \u001a\u00020\u0011\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\bD\u0010EJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0005\u001a\u00020\u0002HÆ\u0003J\u0017\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\n\u001a\u00020\tHÆ\u0003J\t\u0010\f\u001a\u00020\u000bHÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÆ\u0003J\t\u0010\u0012\u001a\u00020\u0011HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÆ\u0003J\u0001\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u00022\b\b\u0002\u0010\u0018\u001a\u00020\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00022\u0016\b\u0002\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00062\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u001c\u001a\u00020\t2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\u000e\b\u0002\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u001f\u001a\u00020\u000b2\b\b\u0002\u0010 \u001a\u00020\u00112\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0015HÆ\u0001J\t\u0010$\u001a\u00020\u0002HÖ\u0001J\t\u0010%\u001a\u00020\tHÖ\u0001J\u0013\u0010'\u001a\u00020\u000b2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0017\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0017\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010\u0018\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0018\u0010(\u001a\u0004\b+\u0010*R\u0019\u0010\u0019\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0019\u0010(\u001a\u0004\b,\u0010*R'\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u001a\u0010-\u001a\u0004\b.\u0010/R\u001b\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u001b\u0010(\u001a\u0004\b0\u0010*R\u0019\u0010\u001c\u001a\u00020\t8\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u00101\u001a\u0004\b2\u00103R\u0019\u0010\u001d\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\u001d\u00104\u001a\u0004\b5\u00106R\u001f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006@\u0006¢\u0006\f\n\u0004\b\u001e\u00107\u001a\u0004\b8\u00109R\u0019\u0010\u001f\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\u001f\u00104\u001a\u0004\b:\u00106R\u0019\u0010 \u001a\u00020\u00118\u0006@\u0006¢\u0006\f\n\u0004\b \u0010;\u001a\u0004\b<\u0010=R\u001b\u0010!\u001a\u0004\u0018\u00010\u00138\u0006@\u0006¢\u0006\f\n\u0004\b!\u0010>\u001a\u0004\b?\u0010@R\u001b\u0010\"\u001a\u0004\u0018\u00010\u00158\u0006@\u0006¢\u0006\f\n\u0004\b\"\u0010A\u001a\u0004\bB\u0010C¨\u0006F"}, d2 = {"Lcom/appsamurai/storyly/StoryGroup;", "", "", "component1", "component2", "component3", "", "component4", "component5", "", "component6", "", "component7", "", "Lcom/appsamurai/storyly/Story;", "component8", "component9", "Lcom/appsamurai/storyly/StoryGroupType;", "component10", "Lcom/appsamurai/storyly/MomentsUser;", "component11", "Lcom/appsamurai/storyly/StoryGroupStyle;", "component12", "uniqueId", "title", "iconUrl", "thematicIconUrls", "coverUrl", "index", "seen", "stories", "pinned", "type", "momentsUser", "style", "copy", "toString", "hashCode", "other", "equals", "Ljava/lang/String;", "getUniqueId", "()Ljava/lang/String;", "getTitle", "getIconUrl", "Ljava/util/Map;", "getThematicIconUrls", "()Ljava/util/Map;", "getCoverUrl", "I", "getIndex", "()I", "Z", "getSeen", "()Z", "Ljava/util/List;", "getStories", "()Ljava/util/List;", "getPinned", "Lcom/appsamurai/storyly/StoryGroupType;", "getType", "()Lcom/appsamurai/storyly/StoryGroupType;", "Lcom/appsamurai/storyly/MomentsUser;", "getMomentsUser", "()Lcom/appsamurai/storyly/MomentsUser;", "Lcom/appsamurai/storyly/StoryGroupStyle;", "getStyle", "()Lcom/appsamurai/storyly/StoryGroupStyle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;IZLjava/util/List;ZLcom/appsamurai/storyly/StoryGroupType;Lcom/appsamurai/storyly/MomentsUser;Lcom/appsamurai/storyly/StoryGroupStyle;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryGroup {
    @Nullable
    private final String coverUrl;
    @NotNull
    private final String iconUrl;
    private final int index;
    @Nullable
    private final MomentsUser momentsUser;
    private final boolean pinned;
    private final boolean seen;
    @NotNull
    private final List<Story> stories;
    @Nullable
    private final StoryGroupStyle style;
    @Nullable
    private final Map<String, String> thematicIconUrls;
    @NotNull
    private final String title;
    @NotNull
    private final StoryGroupType type;
    @NotNull
    private final String uniqueId;

    public StoryGroup(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable Map<String, String> map, @Nullable String str4, int i3, boolean z2, @NotNull List<Story> list, boolean z3, @NotNull StoryGroupType storyGroupType, @Nullable MomentsUser momentsUser2, @Nullable StoryGroupStyle storyGroupStyle) {
        Intrinsics.checkNotNullParameter(str, "uniqueId");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(str3, "iconUrl");
        Intrinsics.checkNotNullParameter(list, "stories");
        Intrinsics.checkNotNullParameter(storyGroupType, "type");
        this.uniqueId = str;
        this.title = str2;
        this.iconUrl = str3;
        this.thematicIconUrls = map;
        this.coverUrl = str4;
        this.index = i3;
        this.seen = z2;
        this.stories = list;
        this.pinned = z3;
        this.type = storyGroupType;
        this.momentsUser = momentsUser2;
        this.style = storyGroupStyle;
    }

    public static /* synthetic */ StoryGroup copy$default(StoryGroup storyGroup, String str, String str2, String str3, Map map, String str4, int i3, boolean z2, List list, boolean z3, StoryGroupType storyGroupType, MomentsUser momentsUser2, StoryGroupStyle storyGroupStyle, int i4, Object obj) {
        StoryGroup storyGroup2 = storyGroup;
        int i5 = i4;
        return storyGroup.copy((i5 & 1) != 0 ? storyGroup2.uniqueId : str, (i5 & 2) != 0 ? storyGroup2.title : str2, (i5 & 4) != 0 ? storyGroup2.iconUrl : str3, (i5 & 8) != 0 ? storyGroup2.thematicIconUrls : map, (i5 & 16) != 0 ? storyGroup2.coverUrl : str4, (i5 & 32) != 0 ? storyGroup2.index : i3, (i5 & 64) != 0 ? storyGroup2.seen : z2, (i5 & 128) != 0 ? storyGroup2.stories : list, (i5 & 256) != 0 ? storyGroup2.pinned : z3, (i5 & 512) != 0 ? storyGroup2.type : storyGroupType, (i5 & 1024) != 0 ? storyGroup2.momentsUser : momentsUser2, (i5 & 2048) != 0 ? storyGroup2.style : storyGroupStyle);
    }

    @NotNull
    public final String component1() {
        return this.uniqueId;
    }

    @NotNull
    public final StoryGroupType component10() {
        return this.type;
    }

    @Nullable
    public final MomentsUser component11() {
        return this.momentsUser;
    }

    @Nullable
    public final StoryGroupStyle component12() {
        return this.style;
    }

    @NotNull
    public final String component2() {
        return this.title;
    }

    @NotNull
    public final String component3() {
        return this.iconUrl;
    }

    @Nullable
    public final Map<String, String> component4() {
        return this.thematicIconUrls;
    }

    @Nullable
    public final String component5() {
        return this.coverUrl;
    }

    public final int component6() {
        return this.index;
    }

    public final boolean component7() {
        return this.seen;
    }

    @NotNull
    public final List<Story> component8() {
        return this.stories;
    }

    public final boolean component9() {
        return this.pinned;
    }

    @NotNull
    public final StoryGroup copy(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable Map<String, String> map, @Nullable String str4, int i3, boolean z2, @NotNull List<Story> list, boolean z3, @NotNull StoryGroupType storyGroupType, @Nullable MomentsUser momentsUser2, @Nullable StoryGroupStyle storyGroupStyle) {
        Intrinsics.checkNotNullParameter(str, "uniqueId");
        String str5 = str2;
        Intrinsics.checkNotNullParameter(str5, "title");
        String str6 = str3;
        Intrinsics.checkNotNullParameter(str6, "iconUrl");
        List<Story> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "stories");
        StoryGroupType storyGroupType2 = storyGroupType;
        Intrinsics.checkNotNullParameter(storyGroupType2, "type");
        return new StoryGroup(str, str5, str6, map, str4, i3, z2, list2, z3, storyGroupType2, momentsUser2, storyGroupStyle);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryGroup)) {
            return false;
        }
        StoryGroup storyGroup = (StoryGroup) obj;
        return Intrinsics.areEqual((Object) this.uniqueId, (Object) storyGroup.uniqueId) && Intrinsics.areEqual((Object) this.title, (Object) storyGroup.title) && Intrinsics.areEqual((Object) this.iconUrl, (Object) storyGroup.iconUrl) && Intrinsics.areEqual((Object) this.thematicIconUrls, (Object) storyGroup.thematicIconUrls) && Intrinsics.areEqual((Object) this.coverUrl, (Object) storyGroup.coverUrl) && this.index == storyGroup.index && this.seen == storyGroup.seen && Intrinsics.areEqual((Object) this.stories, (Object) storyGroup.stories) && this.pinned == storyGroup.pinned && this.type == storyGroup.type && Intrinsics.areEqual((Object) this.momentsUser, (Object) storyGroup.momentsUser) && Intrinsics.areEqual((Object) this.style, (Object) storyGroup.style);
    }

    @Nullable
    public final String getCoverUrl() {
        return this.coverUrl;
    }

    @NotNull
    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final int getIndex() {
        return this.index;
    }

    @Nullable
    public final MomentsUser getMomentsUser() {
        return this.momentsUser;
    }

    public final boolean getPinned() {
        return this.pinned;
    }

    public final boolean getSeen() {
        return this.seen;
    }

    @NotNull
    public final List<Story> getStories() {
        return this.stories;
    }

    @Nullable
    public final StoryGroupStyle getStyle() {
        return this.style;
    }

    @Nullable
    public final Map<String, String> getThematicIconUrls() {
        return this.thematicIconUrls;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @NotNull
    public final StoryGroupType getType() {
        return this.type;
    }

    @NotNull
    public final String getUniqueId() {
        return this.uniqueId;
    }

    public int hashCode() {
        int i3 = a.i(this.iconUrl, a.i(this.title, this.uniqueId.hashCode() * 31, 31), 31);
        Map<String, String> map = this.thematicIconUrls;
        int i4 = 0;
        int hashCode = (i3 + (map == null ? 0 : map.hashCode())) * 31;
        String str = this.coverUrl;
        int c3 = a.c(this.index, (hashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
        boolean z2 = this.seen;
        boolean z3 = true;
        if (z2) {
            z2 = true;
        }
        int j2 = a.j(this.stories, (c3 + (z2 ? 1 : 0)) * 31, 31);
        boolean z4 = this.pinned;
        if (!z4) {
            z3 = z4;
        }
        int hashCode2 = (this.type.hashCode() + ((j2 + (z3 ? 1 : 0)) * 31)) * 31;
        MomentsUser momentsUser2 = this.momentsUser;
        int hashCode3 = (hashCode2 + (momentsUser2 == null ? 0 : momentsUser2.hashCode())) * 31;
        StoryGroupStyle storyGroupStyle = this.style;
        if (storyGroupStyle != null) {
            i4 = storyGroupStyle.hashCode();
        }
        return hashCode3 + i4;
    }

    @NotNull
    public String toString() {
        return "StoryGroup(uniqueId=" + this.uniqueId + ", title=" + this.title + ", iconUrl=" + this.iconUrl + ", thematicIconUrls=" + this.thematicIconUrls + ", coverUrl=" + this.coverUrl + ", index=" + this.index + ", seen=" + this.seen + ", stories=" + this.stories + ", pinned=" + this.pinned + ", type=" + this.type + ", momentsUser=" + this.momentsUser + ", style=" + this.style + ')';
    }
}
