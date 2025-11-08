package com.pandulapeter.beagle.logOkHttp;

import com.pandulapeter.beagle.commonBase.BeagleNetworkLoggerContract;
import com.pandulapeter.beagle.commonBase.model.NetworkLogEntry;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0005H\u0016JQ\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010\u001cJ*\u0010\u001d\u001a\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00050\r2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078VX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u0005\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/pandulapeter/beagle/logOkHttp/BeagleOkHttpLoggerImplementation;", "Lcom/pandulapeter/beagle/commonBase/BeagleNetworkLoggerContract;", "()V", "clearLogs", "Lkotlin/Function0;", "", "logger", "Lokhttp3/Interceptor;", "getLogger", "()Lokhttp3/Interceptor;", "logger$delegate", "Lkotlin/Lazy;", "onNewLog", "Lkotlin/Function1;", "Lcom/pandulapeter/beagle/commonBase/model/NetworkLogEntry;", "clearNetworkLogEntries", "logNetwork", "isOutgoing", "", "url", "", "payload", "headers", "", "duration", "", "timestamp", "id", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;JLjava/lang/String;)V", "register", "log-okhttp"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class BeagleOkHttpLoggerImplementation implements BeagleNetworkLoggerContract {
    @Nullable
    private Function0<Unit> clearLogs;
    @NotNull
    private final Lazy logger$delegate = LazyKt.lazy(BeagleOkHttpLoggerImplementation$logger$2.INSTANCE);
    @Nullable
    private Function1<? super NetworkLogEntry, Unit> onNewLog;

    public void clearNetworkLogEntries() {
        Function0<Unit> function0 = this.clearLogs;
        if (function0 != null) {
            function0.invoke();
        }
    }

    public void logNetwork(boolean z2, @NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Long l2, long j2, @NotNull String str3) {
        String str4 = str;
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str3, TtmlNode.ATTR_ID);
        Function1<? super NetworkLogEntry, Unit> function1 = this.onNewLog;
        if (function1 != null) {
            function1.invoke(new NetworkLogEntry(str3, z2, str, str2, list == null ? CollectionsKt.emptyList() : list, l2, j2));
        }
    }

    public void register(@NotNull Function1<? super NetworkLogEntry, Unit> function1, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function1, "onNewLog");
        Intrinsics.checkNotNullParameter(function0, "clearLogs");
        this.onNewLog = function1;
        this.clearLogs = function0;
    }

    @NotNull
    public Interceptor getLogger() {
        return (Interceptor) this.logger$delegate.getValue();
    }
}
