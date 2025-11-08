package com.pandulapeter.beagle.commonBase;

import com.pandulapeter.beagle.commonBase.model.NetworkLogEntry;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0005\u001a\u00020\u0006H\u0016JY\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\u0013J*\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00060\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0019H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u001a"}, d2 = {"Lcom/pandulapeter/beagle/commonBase/BeagleNetworkLoggerContract;", "", "logger", "getLogger", "()Ljava/lang/Object;", "clearNetworkLogEntries", "", "logNetwork", "isOutgoing", "", "url", "", "payload", "headers", "", "duration", "", "timestamp", "id", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;JLjava/lang/String;)V", "register", "onNewLog", "Lkotlin/Function1;", "Lcom/pandulapeter/beagle/commonBase/model/NetworkLogEntry;", "clearLogs", "Lkotlin/Function0;", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface BeagleNetworkLoggerContract {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void clearNetworkLogEntries(@NotNull BeagleNetworkLoggerContract beagleNetworkLoggerContract) {
        }

        @Nullable
        public static Object getLogger(@NotNull BeagleNetworkLoggerContract beagleNetworkLoggerContract) {
            return null;
        }

        public static void logNetwork(@NotNull BeagleNetworkLoggerContract beagleNetworkLoggerContract, boolean z2, @NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Long l2, long j2, @NotNull String str3) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str3, TtmlNode.ATTR_ID);
        }

        public static /* synthetic */ void logNetwork$default(BeagleNetworkLoggerContract beagleNetworkLoggerContract, boolean z2, String str, String str2, List list, Long l2, long j2, String str3, int i3, Object obj) {
            if (obj == null) {
                beagleNetworkLoggerContract.logNetwork(z2, str, str2, (i3 & 8) != 0 ? null : list, (i3 & 16) != 0 ? null : l2, (i3 & 32) != 0 ? HelpersKt.getCurrentTimestamp() : j2, (i3 & 64) != 0 ? HelpersKt.getRandomId() : str3);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logNetwork");
        }

        public static void register(@NotNull BeagleNetworkLoggerContract beagleNetworkLoggerContract, @NotNull Function1<? super NetworkLogEntry, Unit> function1, @NotNull Function0<Unit> function0) {
            Intrinsics.checkNotNullParameter(function1, "onNewLog");
            Intrinsics.checkNotNullParameter(function0, "clearLogs");
        }
    }

    void clearNetworkLogEntries();

    @Nullable
    Object getLogger();

    void logNetwork(boolean z2, @NotNull String str, @Nullable String str2, @Nullable List<String> list, @Nullable Long l2, long j2, @NotNull String str3);

    void register(@NotNull Function1<? super NetworkLogEntry, Unit> function1, @NotNull Function0<Unit> function0);
}
