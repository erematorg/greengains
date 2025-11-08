package com.appsamurai.storyly.data.managers.processing;

import androidx.core.app.NotificationCompat;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public c f3949a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public e f3950b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function0<Unit> f3951c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function0<Unit> f3952d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public b f3953e;

    public b(@NotNull c cVar, @NotNull e eVar, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function02, @Nullable b bVar) {
        Intrinsics.checkNotNullParameter(cVar, NotificationCompat.CATEGORY_STATUS);
        Intrinsics.checkNotNullParameter(eVar, "type");
        this.f3949a = cVar;
        this.f3950b = eVar;
        this.f3951c = function0;
        this.f3952d = function02;
        this.f3953e = bVar;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(c cVar, e eVar, Function0 function0, Function0 function02, b bVar, int i3) {
        this((i3 & 1) != 0 ? c.InQueue : cVar, eVar, (i3 & 4) != 0 ? null : function0, (i3 & 8) != 0 ? null : function02, (b) null);
    }
}
