package com.chuckerteam.chucker.api;

import android.content.Context;
import com.chuckerteam.chucker.api.RetentionManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0007R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerCollector;", "", "context", "Landroid/content/Context;", "showNotification", "", "retentionPeriod", "Lcom/chuckerteam/chucker/api/RetentionManager$Period;", "(Landroid/content/Context;ZLcom/chuckerteam/chucker/api/RetentionManager$Period;)V", "getShowNotification", "()Z", "setShowNotification", "(Z)V", "onError", "", "obj", "obj2", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ChuckerCollector {
    private boolean showNotification;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerCollector(@NotNull Context context) {
        this(context, false, (RetentionManager.Period) null, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final boolean getShowNotification() {
        return this.showNotification;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This fun will be removed in 4.x release as part of Throwable functionality removal.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void onError(@Nullable Object obj, @Nullable Object obj2) {
    }

    public final void setShowNotification(boolean z2) {
        this.showNotification = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerCollector(@NotNull Context context, boolean z2) {
        this(context, z2, (RetentionManager.Period) null, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmOverloads
    public ChuckerCollector(@NotNull Context context, boolean z2, @NotNull RetentionManager.Period period) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(period, "retentionPeriod");
        this.showNotification = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ChuckerCollector(Context context, boolean z2, RetentionManager.Period period, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? true : z2, (i3 & 4) != 0 ? RetentionManager.Period.ONE_WEEK : period);
    }
}
