package com.appsamurai.storyly.util;

import android.util.Log;
import com.reown.android.push.notifications.PushMessagingService;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final C0055a f6249a = new C0055a();

    /* renamed from: com.appsamurai.storyly.util.a$a  reason: collision with other inner class name */
    public static final class C0055a {
        public static void a(C0055a aVar, String str, String str2, int i3) {
            String str3 = (i3 & 2) != 0 ? "" : null;
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_TAG);
            Log.e(Intrinsics.stringPlus("[Storyly] ", str3), str);
        }

        public static void b(C0055a aVar, String str, String str2, int i3) {
            String str3 = (i3 & 2) != 0 ? "" : null;
            Intrinsics.checkNotNullParameter(str, PushMessagingService.KEY_MESSAGE);
            Intrinsics.checkNotNullParameter(str3, PushMessagingService.KEY_TAG);
            Log.w(Intrinsics.stringPlus("[Storyly] ", str3), str);
        }
    }
}
