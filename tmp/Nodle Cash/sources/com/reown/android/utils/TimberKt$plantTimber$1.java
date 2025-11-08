package com.reown.android.utils;

import com.reown.android.push.notifications.PushMessagingService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014¨\u0006\u000b"}, d2 = {"com/reown/android/utils/TimberKt$plantTimber$1", "Ltimber/log/Timber$DebugTree;", "log", "", "priority", "", "tag", "", "message", "t", "", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TimberKt$plantTimber$1 extends Timber.DebugTree {
    public void log(int i3, String str, String str2, Throwable th) {
        Intrinsics.checkNotNullParameter(str2, PushMessagingService.KEY_MESSAGE);
        super.log(i3, "WalletConnectV2", str2, th);
    }
}
