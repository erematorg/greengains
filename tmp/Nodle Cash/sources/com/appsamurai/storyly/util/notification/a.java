package com.appsamurai.storyly.util.notification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class a {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6345a;

    public a(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6345a = context;
    }

    public final PendingIntent a(String str, int i3) {
        Intent intent = new Intent(this.f6345a, StorylyNotificationReceiver.class);
        intent.setPackage(this.f6345a.getPackageName());
        intent.setAction("com.appsamurai.storyly.ACTION_COUNTDOWN_NOTIFICATION");
        return PendingIntent.getBroadcast(this.f6345a, Integer.parseInt(str), intent, b.a(i3));
    }
}
