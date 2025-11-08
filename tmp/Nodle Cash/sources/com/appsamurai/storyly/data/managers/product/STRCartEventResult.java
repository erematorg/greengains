package com.appsamurai.storyly.data.managers.product;

import androidx.annotation.Keep;
import androidx.compose.animation.core.a;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÆ\u0003J\u0013\u0010\u0005\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0002HÆ\u0001J\t\u0010\u0006\u001a\u00020\u0002HÖ\u0001J\t\u0010\b\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\u0004\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0004\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/appsamurai/storyly/data/managers/product/STRCartEventResult;", "", "", "component1", "message", "copy", "toString", "", "hashCode", "other", "", "equals", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class STRCartEventResult {
    @NotNull
    private final String message;

    public STRCartEventResult(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        this.message = str;
    }

    public static /* synthetic */ STRCartEventResult copy$default(STRCartEventResult sTRCartEventResult, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = sTRCartEventResult.message;
        }
        return sTRCartEventResult.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.message;
    }

    @NotNull
    public final STRCartEventResult copy(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
        return new STRCartEventResult(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof STRCartEventResult) && Intrinsics.areEqual((Object) this.message, (Object) ((STRCartEventResult) obj).message);
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return this.message.hashCode();
    }

    @NotNull
    public String toString() {
        return a.o(')', this.message, new StringBuilder("STRCartEventResult(message="));
    }
}
