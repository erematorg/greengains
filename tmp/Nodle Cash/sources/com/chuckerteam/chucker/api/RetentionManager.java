package com.chuckerteam.chucker.api;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/chuckerteam/chucker/api/RetentionManager;", "", "context", "Landroid/content/Context;", "retentionPeriod", "(Landroid/content/Context;Ljava/lang/Object;)V", "doMaintenance", "", "Period", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class RetentionManager {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/chuckerteam/chucker/api/RetentionManager$Period;", "", "(Ljava/lang/String;I)V", "ONE_HOUR", "ONE_DAY", "ONE_WEEK", "FOREVER", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public enum Period {
        ONE_HOUR,
        ONE_DAY,
        ONE_WEEK,
        FOREVER
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public RetentionManager(@NotNull Context context) {
        this(context, (Object) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final synchronized void doMaintenance() {
    }

    @JvmOverloads
    public RetentionManager(@NotNull Context context, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RetentionManager(Context context, Object obj, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : obj);
    }
}
