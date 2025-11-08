package com.appsamurai.storyly.util.ui.blur;

import android.content.Context;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import androidx.annotation.RequiresApi;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@RequiresApi(api = 17)
public final class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f6375a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f6376b = LazyKt.lazy(new a(this));
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Allocation f6377c;

    /* renamed from: d  reason: collision with root package name */
    public int f6378d = -1;

    /* renamed from: e  reason: collision with root package name */
    public int f6379e = -1;

    public static final class a extends Lambda implements Function0<ScriptIntrinsicBlur> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f6380a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(e eVar) {
            super(0);
            this.f6380a = eVar;
        }

        public Object invoke() {
            return ScriptIntrinsicBlur.create(this.f6380a.b(), Element.U8_4(this.f6380a.b()));
        }
    }

    public static final class b extends Lambda implements Function0<RenderScript> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6381a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context) {
            super(0);
            this.f6381a = context;
        }

        public Object invoke() {
            return RenderScript.create(this.f6381a);
        }
    }

    public e(@Nullable Context context) {
        this.f6375a = LazyKt.lazy(new b(context));
    }

    public final ScriptIntrinsicBlur a() {
        Object value = this.f6376b.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-blurScript>(...)");
        return (ScriptIntrinsicBlur) value;
    }

    public final RenderScript b() {
        Object value = this.f6375a.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-renderScript>(...)");
        return (RenderScript) value;
    }
}
