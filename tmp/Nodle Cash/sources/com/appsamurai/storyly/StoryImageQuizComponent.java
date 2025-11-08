package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b'\u0010(J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u000b\u0010\u0004\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u0011\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0005HÆ\u0003J\u0012\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\b\u0010\tJ\t\u0010\n\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0003JZ\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\f\u001a\u00020\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0014\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003R\u001c\u0010\f\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\f\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR!\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b\"\u0010\tR\u0019\u0010\u0010\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010#\u001a\u0004\b$\u0010%R\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u001a\u001a\u0004\b&\u0010\u001c¨\u0006)"}, d2 = {"Lcom/appsamurai/storyly/StoryImageQuizComponent;", "Lcom/appsamurai/storyly/StoryComponent;", "", "component1", "component2", "", "component3", "", "component4", "()Ljava/lang/Integer;", "component5", "component6", "id", "title", "options", "rightAnswerIndex", "selectedOptionIndex", "customPayload", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;ILjava/lang/String;)Lcom/appsamurai/storyly/StoryImageQuizComponent;", "toString", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getTitle", "Ljava/util/List;", "getOptions", "()Ljava/util/List;", "Ljava/lang/Integer;", "getRightAnswerIndex", "I", "getSelectedOptionIndex", "()I", "getCustomPayload", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;ILjava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryImageQuizComponent extends StoryComponent {
    @Nullable
    private final String customPayload;
    @NotNull
    private final String id;
    @Nullable
    private final List<String> options;
    @Nullable
    private final Integer rightAnswerIndex;
    private final int selectedOptionIndex;
    @Nullable
    private final String title;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryImageQuizComponent(@NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Integer num, int i3, @Nullable String str3) {
        super(str, StoryComponentType.ImageQuiz);
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        this.id = str;
        this.title = str2;
        this.options = list;
        this.rightAnswerIndex = num;
        this.selectedOptionIndex = i3;
        this.customPayload = str3;
    }

    public static /* synthetic */ StoryImageQuizComponent copy$default(StoryImageQuizComponent storyImageQuizComponent, String str, String str2, List<String> list, Integer num, int i3, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = storyImageQuizComponent.getId();
        }
        if ((i4 & 2) != 0) {
            str2 = storyImageQuizComponent.title;
        }
        String str4 = str2;
        if ((i4 & 4) != 0) {
            list = storyImageQuizComponent.options;
        }
        List<String> list2 = list;
        if ((i4 & 8) != 0) {
            num = storyImageQuizComponent.rightAnswerIndex;
        }
        Integer num2 = num;
        if ((i4 & 16) != 0) {
            i3 = storyImageQuizComponent.selectedOptionIndex;
        }
        int i5 = i3;
        if ((i4 & 32) != 0) {
            str3 = storyImageQuizComponent.customPayload;
        }
        return storyImageQuizComponent.copy(str, str4, list2, num2, i5, str3);
    }

    @NotNull
    public final String component1() {
        return getId();
    }

    @Nullable
    public final String component2() {
        return this.title;
    }

    @Nullable
    public final List<String> component3() {
        return this.options;
    }

    @Nullable
    public final Integer component4() {
        return this.rightAnswerIndex;
    }

    public final int component5() {
        return this.selectedOptionIndex;
    }

    @Nullable
    public final String component6() {
        return this.customPayload;
    }

    @NotNull
    public final StoryImageQuizComponent copy(@NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Integer num, int i3, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        return new StoryImageQuizComponent(str, str2, list, num, i3, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryImageQuizComponent)) {
            return false;
        }
        StoryImageQuizComponent storyImageQuizComponent = (StoryImageQuizComponent) obj;
        return Intrinsics.areEqual((Object) getId(), (Object) storyImageQuizComponent.getId()) && Intrinsics.areEqual((Object) this.title, (Object) storyImageQuizComponent.title) && Intrinsics.areEqual((Object) this.options, (Object) storyImageQuizComponent.options) && Intrinsics.areEqual((Object) this.rightAnswerIndex, (Object) storyImageQuizComponent.rightAnswerIndex) && this.selectedOptionIndex == storyImageQuizComponent.selectedOptionIndex && Intrinsics.areEqual((Object) this.customPayload, (Object) storyImageQuizComponent.customPayload);
    }

    @Nullable
    public final String getCustomPayload() {
        return this.customPayload;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @Nullable
    public final List<String> getOptions() {
        return this.options;
    }

    @Nullable
    public final Integer getRightAnswerIndex() {
        return this.rightAnswerIndex;
    }

    public final int getSelectedOptionIndex() {
        return this.selectedOptionIndex;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int hashCode = getId().hashCode() * 31;
        String str = this.title;
        int i3 = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.options;
        int hashCode3 = (hashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        Integer num = this.rightAnswerIndex;
        int c3 = a.c(this.selectedOptionIndex, (hashCode3 + (num == null ? 0 : num.hashCode())) * 31, 31);
        String str2 = this.customPayload;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        return c3 + i3;
    }

    @NotNull
    public String toString() {
        return "StoryImageQuizComponent(id=" + getId() + ", title=" + this.title + ", options=" + this.options + ", rightAnswerIndex=" + this.rightAnswerIndex + ", selectedOptionIndex=" + this.selectedOptionIndex + ", customPayload=" + this.customPayload + ')';
    }
}
