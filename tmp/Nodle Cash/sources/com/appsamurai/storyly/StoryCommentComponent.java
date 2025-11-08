package com.appsamurai.storyly;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\t\u0010\u0004\u001a\u00020\u0002HÆ\u0003J\u001d\u0010\u0007\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0002HÆ\u0001J\t\u0010\b\u001a\u00020\u0002HÖ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bHÖ\u0003R\u001c\u0010\u0005\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0006\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000f\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcom/appsamurai/storyly/StoryCommentComponent;", "Lcom/appsamurai/storyly/StoryComponent;", "", "component1", "component2", "id", "text", "copy", "toString", "", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getText", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StoryCommentComponent extends StoryComponent {
    @NotNull
    private final String id;
    @NotNull
    private final String text;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StoryCommentComponent(@NotNull String str, @NotNull String str2) {
        super(str, StoryComponentType.Comment);
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "text");
        this.id = str;
        this.text = str2;
    }

    public static /* synthetic */ StoryCommentComponent copy$default(StoryCommentComponent storyCommentComponent, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = storyCommentComponent.getId();
        }
        if ((i3 & 2) != 0) {
            str2 = storyCommentComponent.text;
        }
        return storyCommentComponent.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return getId();
    }

    @NotNull
    public final String component2() {
        return this.text;
    }

    @NotNull
    public final StoryCommentComponent copy(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(str2, "text");
        return new StoryCommentComponent(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoryCommentComponent)) {
            return false;
        }
        StoryCommentComponent storyCommentComponent = (StoryCommentComponent) obj;
        return Intrinsics.areEqual((Object) getId(), (Object) storyCommentComponent.getId()) && Intrinsics.areEqual((Object) this.text, (Object) storyCommentComponent.text);
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        return this.text.hashCode() + (getId().hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StoryCommentComponent(id=");
        sb.append(getId());
        sb.append(", text=");
        return a.o(')', this.text, sb);
    }
}
