package com.appsamurai.storyly.data.managers.ad;

import com.appsamurai.storyly.ad.StorylyAdView;
import com.appsamurai.storyly.ad.StorylyAdViewListener;
import com.appsamurai.storyly.data.a;
import com.appsamurai.storyly.data.v;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Function1<StorylyAdViewListener, Unit> f3876a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Function2<v, v, Unit> f3877b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public List<v> f3878c = CollectionsKt.emptyList();

    /* renamed from: d  reason: collision with root package name */
    public int f3879d = -1;

    /* renamed from: e  reason: collision with root package name */
    public int f3880e = -1;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public a f3881f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public List<String> f3882g = new ArrayList();
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public List<StorylyAdView> f3883h = new ArrayList();

    public b(@NotNull Function1<? super StorylyAdViewListener, Unit> function1, @NotNull Function2<? super v, ? super v, Unit> function2) {
        Intrinsics.checkNotNullParameter(function1, "onAdRequest");
        Intrinsics.checkNotNullParameter(function2, "onAdLoad");
        this.f3876a = function1;
        this.f3877b = function2;
    }

    public final void a(int i3, int i4) {
        if (i3 < i4) {
            while (true) {
                int i5 = i3 + 1;
                this.f3882g.remove(this.f3878c.get(i3).f4221a);
                if (i5 < i4) {
                    i3 = i5;
                } else {
                    return;
                }
            }
        }
    }
}
