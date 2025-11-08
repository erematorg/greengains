package com.pandulapeter.beagle.commonBase;

import com.pandulapeter.beagle.commonBase.model.LogEntry;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016JF\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0005H\u0016JA\u0010\u000e\u001a\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00030\u00102#\u0010\u0012\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0004\u0012\u0004\u0012\u00020\u00030\u0010H\u0016¨\u0006\u0015"}, d2 = {"Lcom/pandulapeter/beagle/commonBase/BeagleLoggerContract;", "", "clearLogEntries", "", "label", "", "log", "message", "payload", "isPersisted", "", "timestamp", "", "id", "register", "onNewLog", "Lkotlin/Function1;", "Lcom/pandulapeter/beagle/commonBase/model/LogEntry;", "clearLogs", "Lkotlin/ParameterName;", "name", "internal-common-base"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface BeagleLoggerContract {

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static void clearLogEntries(@NotNull BeagleLoggerContract beagleLoggerContract, @Nullable String str) {
        }

        public static /* synthetic */ void clearLogEntries$default(BeagleLoggerContract beagleLoggerContract, String str, int i3, Object obj) {
            if (obj == null) {
                if ((i3 & 1) != 0) {
                    str = null;
                }
                beagleLoggerContract.clearLogEntries(str);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: clearLogEntries");
        }

        public static void log(@NotNull BeagleLoggerContract beagleLoggerContract, @NotNull String str, @Nullable String str2, @Nullable String str3, boolean z2, long j2, @NotNull String str4) {
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str4, TtmlNode.ATTR_ID);
        }

        public static /* synthetic */ void log$default(BeagleLoggerContract beagleLoggerContract, String str, String str2, String str3, boolean z2, long j2, String str4, int i3, Object obj) {
            if (obj == null) {
                String str5 = null;
                String str6 = (i3 & 2) != 0 ? null : str2;
                if ((i3 & 4) == 0) {
                    str5 = str3;
                }
                beagleLoggerContract.log(str, str6, str5, (i3 & 8) != 0 ? false : z2, (i3 & 16) != 0 ? HelpersKt.getCurrentTimestamp() : j2, (i3 & 32) != 0 ? HelpersKt.getRandomId() : str4);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }

        public static void register(@NotNull BeagleLoggerContract beagleLoggerContract, @NotNull Function1<? super LogEntry, Unit> function1, @NotNull Function1<? super String, Unit> function12) {
            Intrinsics.checkNotNullParameter(function1, "onNewLog");
            Intrinsics.checkNotNullParameter(function12, "clearLogs");
        }
    }

    void clearLogEntries(@Nullable String str);

    void log(@NotNull String str, @Nullable String str2, @Nullable String str3, boolean z2, long j2, @NotNull String str4);

    void register(@NotNull Function1<? super LogEntry, Unit> function1, @NotNull Function1<? super String, Unit> function12);
}
