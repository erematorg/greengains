package com.appsamurai.storyly.data.managers.network;

import android.content.Context;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.data.managers.processing.a;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class e {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3915a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyInit f3916b;

    /* renamed from: c  reason: collision with root package name */
    public final int f3917c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final String f3918d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final com.appsamurai.storyly.data.managers.processing.e f3919e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public final a f3920f;

    public e(@NotNull Context context, @NotNull StorylyInit storylyInit, int i3, @NotNull String str, @NotNull com.appsamurai.storyly.data.managers.processing.e eVar, @Nullable a aVar) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyInit, "storylyInit");
        Intrinsics.checkNotNullParameter(str, "endpoint");
        Intrinsics.checkNotNullParameter(eVar, "requestType");
        this.f3915a = context;
        this.f3916b = storylyInit;
        this.f3917c = i3;
        this.f3918d = str;
        this.f3919e = eVar;
        this.f3920f = aVar;
    }

    @NotNull
    public Map<String, Object> a() {
        throw null;
    }

    @NotNull
    public Map<String, String> b() {
        throw null;
    }

    @Nullable
    public String c() {
        return null;
    }

    public /* synthetic */ e(Context context, StorylyInit storylyInit, int i3, String str, com.appsamurai.storyly.data.managers.processing.e eVar, a aVar, int i4) {
        this(context, storylyInit, i3, str, eVar, (a) null);
    }
}
