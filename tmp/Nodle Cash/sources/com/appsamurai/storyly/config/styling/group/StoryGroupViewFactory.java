package com.appsamurai.storyly.config.styling.group;

import androidx.annotation.Keep;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0006"}, d2 = {"Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "", "Lcom/appsamurai/storyly/config/styling/group/StoryGroupView;", "createView", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public abstract class StoryGroupViewFactory {
    @NotNull
    public abstract StoryGroupView createView();
}
