package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0006\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\b\u001a\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\t\u0010\r\u001a\u00020\u0002HÖ\u0001J\t\u0010\u000e\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fHÖ\u0003R\u001c\u0010\b\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\b\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\t\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\u0013\u001a\u0004\b\u0016\u0010\u0015R\u0019\u0010\n\u001a\u00020\u00058\u0006@\u0006¢\u0006\f\n\u0004\b\n\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0013\u001a\u0004\b\u001a\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/appsamurai/storyly/StoryRatingComponent;", "Lcom/appsamurai/storyly/StoryComponent;", "", "component1", "component2", "", "component3", "component4", "id", "emojiCode", "rating", "customPayload", "copy", "toString", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getEmojiCode", "I", "getRating", "()I", "getCustomPayload", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryRatingComponent extends StoryComponent {
    @Nullable
    private final String customPayload;
    @NotNull
    private final String emojiCode;
    @NotNull
    private final String id;
    private final int rating;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryRatingComponent(@NotNull String str, @NotNull String str2, int i3, @Nullable String str3) {
        super(str, StoryComponentType.Rating);
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "emojiCode");
        this.id = str;
        this.emojiCode = str2;
        this.rating = i3;
        this.customPayload = str3;
    }

    public static /* synthetic */ StoryRatingComponent copy$default(StoryRatingComponent storyRatingComponent, String str, String str2, int i3, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = storyRatingComponent.getId();
        }
        if ((i4 & 2) != 0) {
            str2 = storyRatingComponent.emojiCode;
        }
        if ((i4 & 4) != 0) {
            i3 = storyRatingComponent.rating;
        }
        if ((i4 & 8) != 0) {
            str3 = storyRatingComponent.customPayload;
        }
        return storyRatingComponent.copy(str, str2, i3, str3);
    }

    @NotNull
    public final String component1() {
        return getId();
    }

    @NotNull
    public final String component2() {
        return this.emojiCode;
    }

    public final int component3() {
        return this.rating;
    }

    @Nullable
    public final String component4() {
        return this.customPayload;
    }

    @NotNull
    public final StoryRatingComponent copy(@NotNull String str, @NotNull String str2, int i3, @Nullable String str3) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "emojiCode");
        return new StoryRatingComponent(str, str2, i3, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryRatingComponent)) {
            return false;
        }
        StoryRatingComponent storyRatingComponent = (StoryRatingComponent) obj;
        return Intrinsics.areEqual((Object) getId(), (Object) storyRatingComponent.getId()) && Intrinsics.areEqual((Object) this.emojiCode, (Object) storyRatingComponent.emojiCode) && this.rating == storyRatingComponent.rating && Intrinsics.areEqual((Object) this.customPayload, (Object) storyRatingComponent.customPayload);
    }

    @Nullable
    public final String getCustomPayload() {
        return this.customPayload;
    }

    @NotNull
    public final String getEmojiCode() {
        return this.emojiCode;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    public final int getRating() {
        return this.rating;
    }

    public int hashCode() {
        int c3 = a.c(this.rating, a.i(this.emojiCode, getId().hashCode() * 31, 31), 31);
        String str = this.customPayload;
        return c3 + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return "StoryRatingComponent(id=" + getId() + ", emojiCode=" + this.emojiCode + ", rating=" + this.rating + ", customPayload=" + this.customPayload + ')';
    }
}
