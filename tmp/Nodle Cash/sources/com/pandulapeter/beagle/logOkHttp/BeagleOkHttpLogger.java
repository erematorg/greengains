package com.pandulapeter.beagle.logOkHttp;

import com.pandulapeter.beagle.commonBase.BeagleNetworkLoggerContract;
import com.pandulapeter.beagle.commonBase.model.NetworkLogEntry;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0007\u001a\u00020\bH\u0001JZ\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\rH\u0001¢\u0006\u0002\u0010\u0015J+\u0010\u0016\u001a\u00020\b2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\b0\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bH\u0001R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00048VX\u0005¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, d2 = {"Lcom/pandulapeter/beagle/logOkHttp/BeagleOkHttpLogger;", "Lcom/pandulapeter/beagle/commonBase/BeagleNetworkLoggerContract;", "()V", "logger", "", "getLogger", "()Ljava/lang/Object;", "clearNetworkLogEntries", "", "logNetwork", "isOutgoing", "", "url", "", "payload", "headers", "", "duration", "", "timestamp", "id", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;JLjava/lang/String;)V", "register", "onNewLog", "Lkotlin/Function1;", "Lcom/pandulapeter/beagle/commonBase/model/NetworkLogEntry;", "clearLogs", "Lkotlin/Function0;", "log-okhttp"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class BeagleOkHttpLogger implements BeagleNetworkLoggerContract {
    @NotNull
    public static final BeagleOkHttpLogger INSTANCE = new BeagleOkHttpLogger();
    private final /* synthetic */ BeagleOkHttpLoggerImplementation $$delegate_0 = new BeagleOkHttpLoggerImplementation();

    private BeagleOkHttpLogger() {
    }

    public void clearNetworkLogEntries() {
        this.$$delegate_0.clearNetworkLogEntries();
    }

    @Nullable
    public Object getLogger() {
        return this.$$delegate_0.getLogger();
    }

    public void logNetwork(boolean z2, @NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Long l2, long j2, @NotNull String str3) {
        Intrinsics.checkNotNullParameter(str, "url");
        String str4 = str3;
        Intrinsics.checkNotNullParameter(str4, TtmlNode.ATTR_ID);
        this.$$delegate_0.logNetwork(z2, str, str2, list, l2, j2, str4);
    }

    public void register(@NotNull Function1<? super NetworkLogEntry, Unit> function1, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function1, "onNewLog");
        Intrinsics.checkNotNullParameter(function0, "clearLogs");
        this.$$delegate_0.register(function1, function0);
    }
}
