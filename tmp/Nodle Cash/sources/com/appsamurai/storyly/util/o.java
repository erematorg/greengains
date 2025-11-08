package com.appsamurai.storyly.util;

import android.content.Context;
import android.os.Handler;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class o {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6346a;

    /* renamed from: b  reason: collision with root package name */
    public final long f6347b;

    /* renamed from: c  reason: collision with root package name */
    public final long f6348c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function1<? super Long, Unit> f6349d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f6350e;

    /* renamed from: f  reason: collision with root package name */
    public long f6351f;

    /* renamed from: g  reason: collision with root package name */
    public long f6352g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f6353h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f6354i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Lazy f6355j = LazyKt.lazy(new a(this));

    public static final class a extends Lambda implements Function0<n> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f6356a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(o oVar) {
            super(0);
            this.f6356a = oVar;
        }

        public Object invoke() {
            return new n(this.f6356a, this.f6356a.f6346a.getMainLooper());
        }
    }

    public o(@NotNull Context context, long j2, long j3) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6346a = context;
        this.f6347b = j2;
        this.f6348c = j3;
    }

    public final Handler a() {
        return (Handler) this.f6355j.getValue();
    }
}
