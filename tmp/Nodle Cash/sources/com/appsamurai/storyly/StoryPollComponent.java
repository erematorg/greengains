package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b!\u0010\"J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\u000f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005HÆ\u0003J\t\u0010\b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0003JC\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\n\u001a\u00020\u00022\b\b\u0002\u0010\u000b\u001a\u00020\u00022\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\u0010\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u001c\u0010\n\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\n\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\r\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0016\u001a\u0004\b \u0010\u0018¨\u0006#"}, d2 = {"Lcom/appsamurai/storyly/StoryPollComponent;", "Lcom/appsamurai/storyly/StoryComponent;", "", "component1", "component2", "", "component3", "", "component4", "component5", "id", "title", "options", "selectedOptionIndex", "customPayload", "copy", "toString", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Ljava/util/List;", "getOptions", "()Ljava/util/List;", "I", "getSelectedOptionIndex", "()I", "getCustomPayload", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ILjava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryPollComponent extends StoryComponent {
    @Nullable
    private final String customPayload;
    @NotNull
    private final String id;
    @NotNull
    private final List<String> options;
    private final int selectedOptionIndex;
    @NotNull
    private final String title;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryPollComponent(@NotNull String str, @NotNull String str2, @NotNull List<String> list, int i3, @Nullable String str3) {
        super(str, StoryComponentType.Poll);
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(list, "options");
        this.id = str;
        this.title = str2;
        this.options = list;
        this.selectedOptionIndex = i3;
        this.customPayload = str3;
    }

    public static /* synthetic */ StoryPollComponent copy$default(StoryPollComponent storyPollComponent, String str, String str2, List<String> list, int i3, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = storyPollComponent.getId();
        }
        if ((i4 & 2) != 0) {
            str2 = storyPollComponent.title;
        }
        String str4 = str2;
        if ((i4 & 4) != 0) {
            list = storyPollComponent.options;
        }
        List<String> list2 = list;
        if ((i4 & 8) != 0) {
            i3 = storyPollComponent.selectedOptionIndex;
        }
        int i5 = i3;
        if ((i4 & 16) != 0) {
            str3 = storyPollComponent.customPayload;
        }
        return storyPollComponent.copy(str, str4, list2, i5, str3);
    }

    @NotNull
    public final String component1() {
        return getId();
    }

    @NotNull
    public final String component2() {
        return this.title;
    }

    @NotNull
    public final List<String> component3() {
        return this.options;
    }

    public final int component4() {
        return this.selectedOptionIndex;
    }

    @Nullable
    public final String component5() {
        return this.customPayload;
    }

    @NotNull
    public final StoryPollComponent copy(@NotNull String str, @NotNull String str2, @NotNull List<String> list, int i3, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "title");
        Intrinsics.checkNotNullParameter(list, "options");
        return new StoryPollComponent(str, str2, list, i3, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryPollComponent)) {
            return false;
        }
        StoryPollComponent storyPollComponent = (StoryPollComponent) obj;
        return Intrinsics.areEqual((Object) getId(), (Object) storyPollComponent.getId()) && Intrinsics.areEqual((Object) this.title, (Object) storyPollComponent.title) && Intrinsics.areEqual((Object) this.options, (Object) storyPollComponent.options) && this.selectedOptionIndex == storyPollComponent.selectedOptionIndex && Intrinsics.areEqual((Object) this.customPayload, (Object) storyPollComponent.customPayload);
    }

    @Nullable
    public final String getCustomPayload() {
        return this.customPayload;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @NotNull
    public final List<String> getOptions() {
        return this.options;
    }

    public final int getSelectedOptionIndex() {
        return this.selectedOptionIndex;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int c3 = a.c(this.selectedOptionIndex, a.j(this.options, a.i(this.title, getId().hashCode() * 31, 31), 31), 31);
        String str = this.customPayload;
        return c3 + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return "StoryPollComponent(id=" + getId() + ", title=" + this.title + ", options=" + this.options + ", selectedOptionIndex=" + this.selectedOptionIndex + ", customPayload=" + this.customPayload + ')';
    }
}
