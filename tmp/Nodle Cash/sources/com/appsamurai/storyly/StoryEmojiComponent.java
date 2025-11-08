package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u000f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004HÆ\u0003J\t\u0010\u0007\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J9\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\t\u001a\u00020\u00022\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\u000e\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003R\u001c\u0010\t\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\t\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u000b\u001a\u00020\u00068\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\u0014\u001a\u0004\b\u001d\u0010\u0016¨\u0006 "}, d2 = {"Lcom/appsamurai/storyly/StoryEmojiComponent;", "Lcom/appsamurai/storyly/StoryComponent;", "", "component1", "", "component2", "", "component3", "component4", "id", "emojiCodes", "selectedEmojiIndex", "customPayload", "copy", "toString", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Ljava/util/List;", "getEmojiCodes", "()Ljava/util/List;", "I", "getSelectedEmojiIndex", "()I", "getCustomPayload", "<init>", "(Ljava/lang/String;Ljava/util/List;ILjava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryEmojiComponent extends StoryComponent {
    @Nullable
    private final String customPayload;
    @NotNull
    private final List<String> emojiCodes;
    @NotNull
    private final String id;
    private final int selectedEmojiIndex;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryEmojiComponent(@NotNull String str, @NotNull List<String> list, int i3, @Nullable String str2) {
        super(str, StoryComponentType.Emoji);
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(list, "emojiCodes");
        this.id = str;
        this.emojiCodes = list;
        this.selectedEmojiIndex = i3;
        this.customPayload = str2;
    }

    public static /* synthetic */ StoryEmojiComponent copy$default(StoryEmojiComponent storyEmojiComponent, String str, List<String> list, int i3, String str2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = storyEmojiComponent.getId();
        }
        if ((i4 & 2) != 0) {
            list = storyEmojiComponent.emojiCodes;
        }
        if ((i4 & 4) != 0) {
            i3 = storyEmojiComponent.selectedEmojiIndex;
        }
        if ((i4 & 8) != 0) {
            str2 = storyEmojiComponent.customPayload;
        }
        return storyEmojiComponent.copy(str, list, i3, str2);
    }

    @NotNull
    public final String component1() {
        return getId();
    }

    @NotNull
    public final List<String> component2() {
        return this.emojiCodes;
    }

    public final int component3() {
        return this.selectedEmojiIndex;
    }

    @Nullable
    public final String component4() {
        return this.customPayload;
    }

    @NotNull
    public final StoryEmojiComponent copy(@NotNull String str, @NotNull List<String> list, int i3, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(list, "emojiCodes");
        return new StoryEmojiComponent(str, list, i3, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryEmojiComponent)) {
            return false;
        }
        StoryEmojiComponent storyEmojiComponent = (StoryEmojiComponent) obj;
        return Intrinsics.areEqual((Object) getId(), (Object) storyEmojiComponent.getId()) && Intrinsics.areEqual((Object) this.emojiCodes, (Object) storyEmojiComponent.emojiCodes) && this.selectedEmojiIndex == storyEmojiComponent.selectedEmojiIndex && Intrinsics.areEqual((Object) this.customPayload, (Object) storyEmojiComponent.customPayload);
    }

    @Nullable
    public final String getCustomPayload() {
        return this.customPayload;
    }

    @NotNull
    public final List<String> getEmojiCodes() {
        return this.emojiCodes;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    public final int getSelectedEmojiIndex() {
        return this.selectedEmojiIndex;
    }

    public int hashCode() {
        int c3 = a.c(this.selectedEmojiIndex, a.j(this.emojiCodes, getId().hashCode() * 31, 31), 31);
        String str = this.customPayload;
        return c3 + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return "StoryEmojiComponent(id=" + getId() + ", emojiCodes=" + this.emojiCodes + ", selectedEmojiIndex=" + this.selectedEmojiIndex + ", customPayload=" + this.customPayload + ')';
    }
}
