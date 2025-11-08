package com.appsamurai.storyly;

import androidx.annotation.Keep;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rR\u001c\u0010\u0003\u001a\u00020\u00028\u0016@\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\u00078\u0006@\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/appsamurai/storyly/StoryComponent;", "", "", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/appsamurai/storyly/StoryComponentType;", "type", "Lcom/appsamurai/storyly/StoryComponentType;", "getType", "()Lcom/appsamurai/storyly/StoryComponentType;", "<init>", "(Ljava/lang/String;Lcom/appsamurai/storyly/StoryComponentType;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public class StoryComponent {
    @NotNull
    private final String id;
    @NotNull
    private final StoryComponentType type;

    public StoryComponent(@NotNull String str, @NotNull StoryComponentType storyComponentType) {
        Intrinsics.checkNotNullParameter(str, TtmlNode.ATTR_ID);
        Intrinsics.checkNotNullParameter(storyComponentType, "type");
        this.id = str;
        this.type = storyComponentType;
    }

    @NotNull
    public String getId() {
        return this.id;
    }

    @NotNull
    public final StoryComponentType getType() {
        return this.type;
    }
}
