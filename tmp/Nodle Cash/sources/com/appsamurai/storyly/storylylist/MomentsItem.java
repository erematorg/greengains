package com.appsamurai.storyly.storylylist;

import android.view.View;
import androidx.annotation.Keep;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u000fR\"\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/appsamurai/storyly/storylylist/MomentsItem;", "", "", "tag", "Ljava/lang/String;", "getTag$storyly_release", "()Ljava/lang/String;", "setTag$storyly_release", "(Ljava/lang/String;)V", "Landroid/view/View;", "momentsView", "Landroid/view/View;", "getMomentsView", "()Landroid/view/View;", "setMomentsView", "(Landroid/view/View;)V", "customView", "<init>", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class MomentsItem {
    @NotNull
    private View momentsView;
    @NotNull
    private String tag;

    public MomentsItem(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "customView");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.tag = uuid;
        this.momentsView = view;
    }

    @NotNull
    public final View getMomentsView() {
        return this.momentsView;
    }

    @NotNull
    public final String getTag$storyly_release() {
        return this.tag;
    }

    public final void setMomentsView(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.momentsView = view;
    }

    public final void setTag$storyly_release(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tag = str;
    }
}
