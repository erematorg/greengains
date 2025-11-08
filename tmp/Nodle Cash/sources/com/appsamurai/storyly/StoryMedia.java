package com.appsamurai.storyly;

import androidx.annotation.Keep;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b$\u0010%J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u0011\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0011\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0007HÆ\u0003JO\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\u00022\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\t\u0010\u0011\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u000b\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R!\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR!\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001e\u001a\u0004\b\u001f\u0010 R$\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u001e\u001a\u0004\b!\u0010 \"\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/appsamurai/storyly/StoryMedia;", "", "Lcom/appsamurai/storyly/StoryType;", "component1", "", "Lcom/appsamurai/storyly/StoryComponent;", "component2", "", "component3", "component4", "component5", "type", "storyComponentList", "actionUrlList", "actionUrl", "previewUrl", "copy", "toString", "", "hashCode", "other", "", "equals", "Lcom/appsamurai/storyly/StoryType;", "getType", "()Lcom/appsamurai/storyly/StoryType;", "Ljava/util/List;", "getStoryComponentList", "()Ljava/util/List;", "getActionUrlList", "Ljava/lang/String;", "getActionUrl", "()Ljava/lang/String;", "getPreviewUrl", "setPreviewUrl", "(Ljava/lang/String;)V", "<init>", "(Lcom/appsamurai/storyly/StoryType;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryMedia {
    @Nullable
    private final String actionUrl;
    @Nullable
    private final List<String> actionUrlList;
    @Nullable
    private String previewUrl;
    @Nullable
    private final List<StoryComponent> storyComponentList;
    @NotNull
    private final StoryType type;

    public StoryMedia(@NotNull StoryType storyType, @Nullable List<? extends StoryComponent> list, @Nullable List<String> list2, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(storyType, "type");
        this.type = storyType;
        this.storyComponentList = list;
        this.actionUrlList = list2;
        this.actionUrl = str;
        this.previewUrl = str2;
    }

    public static /* synthetic */ StoryMedia copy$default(StoryMedia storyMedia, StoryType storyType, List<StoryComponent> list, List<String> list2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            storyType = storyMedia.type;
        }
        if ((i3 & 2) != 0) {
            list = storyMedia.storyComponentList;
        }
        List<StoryComponent> list3 = list;
        if ((i3 & 4) != 0) {
            list2 = storyMedia.actionUrlList;
        }
        List<String> list4 = list2;
        if ((i3 & 8) != 0) {
            str = storyMedia.actionUrl;
        }
        String str3 = str;
        if ((i3 & 16) != 0) {
            str2 = storyMedia.previewUrl;
        }
        return storyMedia.copy(storyType, list3, list4, str3, str2);
    }

    @NotNull
    public final StoryType component1() {
        return this.type;
    }

    @Nullable
    public final List<StoryComponent> component2() {
        return this.storyComponentList;
    }

    @Nullable
    public final List<String> component3() {
        return this.actionUrlList;
    }

    @Nullable
    public final String component4() {
        return this.actionUrl;
    }

    @Nullable
    public final String component5() {
        return this.previewUrl;
    }

    @NotNull
    public final StoryMedia copy(@NotNull StoryType storyType, @Nullable List<? extends StoryComponent> list, @Nullable List<String> list2, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(storyType, "type");
        return new StoryMedia(storyType, list, list2, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryMedia)) {
            return false;
        }
        StoryMedia storyMedia = (StoryMedia) obj;
        return this.type == storyMedia.type && Intrinsics.areEqual((Object) this.storyComponentList, (Object) storyMedia.storyComponentList) && Intrinsics.areEqual((Object) this.actionUrlList, (Object) storyMedia.actionUrlList) && Intrinsics.areEqual((Object) this.actionUrl, (Object) storyMedia.actionUrl) && Intrinsics.areEqual((Object) this.previewUrl, (Object) storyMedia.previewUrl);
    }

    @Nullable
    public final String getActionUrl() {
        return this.actionUrl;
    }

    @Nullable
    public final List<String> getActionUrlList() {
        return this.actionUrlList;
    }

    @Nullable
    public final String getPreviewUrl() {
        return this.previewUrl;
    }

    @Nullable
    public final List<StoryComponent> getStoryComponentList() {
        return this.storyComponentList;
    }

    @NotNull
    public final StoryType getType() {
        return this.type;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        List<StoryComponent> list = this.storyComponentList;
        int i3 = 0;
        int hashCode2 = (hashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.actionUrlList;
        int hashCode3 = (hashCode2 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str = this.actionUrl;
        int hashCode4 = (hashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.previewUrl;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return hashCode4 + i3;
    }

    public final void setPreviewUrl(@Nullable String str) {
        this.previewUrl = str;
    }

    @NotNull
    public String toString() {
        return "StoryMedia(type=" + this.type + ", storyComponentList=" + this.storyComponentList + ", actionUrlList=" + this.actionUrlList + ", actionUrl=" + this.actionUrl + ", previewUrl=" + this.previewUrl + ')';
    }
}
