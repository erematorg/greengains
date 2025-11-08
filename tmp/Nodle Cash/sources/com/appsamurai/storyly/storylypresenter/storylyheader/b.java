package com.appsamurai.storyly.storylypresenter.storylyheader;

import android.animation.ObjectAnimator;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.e;
import com.appsamurai.storyly.util.o;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b {

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5538j;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f5539a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f5540b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f5541c = new a((Object) null, (Object) null, this);

    /* renamed from: d  reason: collision with root package name */
    public Function2<? super Long, ? super Long, Unit> f5542d;

    /* renamed from: e  reason: collision with root package name */
    public Function0<Unit> f5543e;

    /* renamed from: f  reason: collision with root package name */
    public long f5544f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Long f5545g = 0L;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final ReadWriteProperty f5546h = new C0047b((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final List<c> f5547i = new ArrayList();

    public static final class a extends ObservableProperty<v> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f5548a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Object obj, Object obj2, b bVar) {
            super(null);
            this.f5548a = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, v vVar, v vVar2) {
            int size;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            v vVar3 = vVar2;
            v vVar4 = vVar;
            this.f5548a.f5539a.removeAllViews();
            this.f5548a.f5547i.clear();
            if (vVar3 != null && vVar3.f4226f.size() - 1 >= 0) {
                int i3 = 0;
                do {
                    i3++;
                    Function2<? super Long, ? super Long, Unit> function2 = null;
                    c cVar = new c(new ContextThemeWrapper(this.f5548a.f5539a.getContext(), R.style.StorylyConfig), (AttributeSet) null, 16842872, this.f5548a.f5540b);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f / ((float) vVar3.f4226f.size()));
                    layoutParams.setMargins(this.f5548a.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_left_padding), this.f5548a.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_top_padding), this.f5548a.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_right_padding), this.f5548a.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_bottom_padding));
                    layoutParams.height = this.f5548a.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_height);
                    cVar.setLayoutParams(layoutParams);
                    Function0<Unit> function0 = this.f5548a.f5543e;
                    if (function0 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onTimeCompleted");
                        function0 = null;
                    }
                    cVar.setOnTimeCompleted(function0);
                    Function2<? super Long, ? super Long, Unit> function22 = this.f5548a.f5542d;
                    if (function22 != null) {
                        function2 = function22;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("onTimeUpdated");
                    }
                    cVar.setOnTimeUpdated(function2);
                    this.f5548a.f5547i.add(cVar);
                    this.f5548a.f5539a.addView(cVar);
                } while (i3 <= size);
            }
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.b$b  reason: collision with other inner class name */
    public static final class C0047b extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f5549a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0047b(Object obj, Object obj2, b bVar) {
            super(null);
            this.f5549a = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            c cVar;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            Integer num3 = num2;
            Integer num4 = num;
            this.f5549a.c();
            b bVar = this.f5549a;
            Integer a2 = bVar.a();
            StoryGroupType storyGroupType = null;
            if (a2 != null) {
                int intValue = a2.intValue();
                int i3 = 0;
                for (T next : bVar.f5547i) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    c cVar2 = (c) next;
                    if (i3 < intValue) {
                        cVar2.a();
                    } else if (i3 == intValue) {
                        v vVar = (v) bVar.f5541c.getValue(bVar, b.f5538j[0]);
                        if ((vVar == null ? null : vVar.f4228h) == StoryGroupType.Live) {
                            cVar2.a();
                        }
                    }
                    i3 = i4;
                }
            }
            b bVar2 = this.f5549a;
            v vVar2 = (v) bVar2.f5541c.getValue(bVar2, b.f5538j[0]);
            if (vVar2 != null) {
                storyGroupType = vVar2.f4228h;
            }
            if (storyGroupType != StoryGroupType.Live) {
                b bVar3 = this.f5549a;
                if (bVar3.f5544f > 0 && (cVar = (c) e.a(bVar3.f5547i, bVar3.a())) != null) {
                    b bVar4 = this.f5549a;
                    cVar.a(bVar4.f5545g, bVar4.f5544f);
                }
                this.f5549a.f5544f = 0;
            }
        }
    }

    static {
        Class<b> cls = b.class;
        f5538j = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyGroupItem", "getStorylyGroupItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyGroupItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
    }

    public b(@NotNull ViewGroup viewGroup, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.TAG_LAYOUT);
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5539a = viewGroup;
        this.f5540b = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
    }

    public final void a(@Nullable v vVar) {
        this.f5541c.setValue(this, f5538j[0], vVar);
    }

    public final void b(@Nullable Integer num) {
        this.f5546h.setValue(this, f5538j[1], num);
    }

    public final void c() {
        Integer a2 = a();
        if (a2 != null) {
            int intValue = a2.intValue();
            int i3 = 0;
            for (T next : this.f5547i) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                c cVar = (c) next;
                if (i3 >= intValue) {
                    cVar.c();
                }
                i3 = i4;
            }
        }
    }

    public final void d() {
        ObjectAnimator objectAnimator;
        Integer a2 = a();
        if (a2 != null) {
            c cVar = this.f5547i.get(a2.intValue());
            if (cVar.f5559i && (objectAnimator = cVar.f5555e) != null) {
                objectAnimator.start();
                objectAnimator.setCurrentPlayTime(cVar.f5557g);
                cVar.f5557g = 0;
                cVar.f5559i = false;
            }
            o oVar = cVar.f5554d;
            if (oVar != null) {
                synchronized (oVar) {
                    if (oVar.f6354i) {
                        oVar.f6354i = false;
                        oVar.f6351f = oVar.f6352g + SystemClock.elapsedRealtime();
                        oVar.a().sendMessage(oVar.a().obtainMessage(1));
                    }
                }
            }
        }
    }

    public final void a(@NotNull Function2<? super Long, ? super Long, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.f5542d = function2;
    }

    public final void b() {
        Integer a2 = a();
        if (a2 != null) {
            c cVar = this.f5547i.get(a2.intValue());
            ObjectAnimator objectAnimator = cVar.f5555e;
            if (objectAnimator != null) {
                cVar.f5557g = objectAnimator.getCurrentPlayTime();
                objectAnimator.cancel();
            }
            o oVar = cVar.f5554d;
            if (oVar != null) {
                synchronized (oVar) {
                    if (!oVar.f6354i) {
                        oVar.f6354i = true;
                        oVar.f6352g = oVar.f6351f - SystemClock.elapsedRealtime();
                    }
                }
            }
            cVar.f5559i = true;
        }
    }

    public final void a(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5543e = function0;
    }

    @Nullable
    public final Integer a() {
        return (Integer) this.f5546h.getValue(this, f5538j[1]);
    }

    public final void a(@Nullable Long l2) {
        Integer a2 = a();
        if (a2 != null) {
            int intValue = a2.intValue();
            this.f5545g = l2;
            int i3 = c.f5550j;
            this.f5547i.get(intValue).a(l2, 0);
        }
    }

    public final void a(@Nullable Integer num) {
        if (num != null) {
            c cVar = (c) e.a(this.f5547i, a());
            Long valueOf = cVar == null ? null : Long.valueOf(cVar.getCurrentPlayTime$storyly_release());
            if (valueOf != null) {
                this.f5544f = valueOf.longValue();
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 1.0f / ((float) (num.intValue() + this.f5547i.size())));
                layoutParams.setMargins(this.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_left_padding), this.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_top_padding), this.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_right_padding), this.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_gap_bottom_padding));
                layoutParams.height = this.f5539a.getResources().getDimensionPixelSize(R.dimen.st_progress_bar_height);
                int intValue = num.intValue();
                for (int i3 = 0; i3 < intValue; i3++) {
                    c cVar2 = new c(new ContextThemeWrapper(this.f5539a.getContext(), R.style.StorylyConfig), (AttributeSet) null, 16842872, this.f5540b);
                    Function0<Unit> function0 = this.f5543e;
                    if (function0 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onTimeCompleted");
                        function0 = null;
                    }
                    cVar2.setOnTimeCompleted(function0);
                    Function2<? super Long, ? super Long, Unit> function2 = this.f5542d;
                    if (function2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onTimeUpdated");
                        function2 = null;
                    }
                    cVar2.setOnTimeUpdated(function2);
                    this.f5547i.add(cVar2);
                    this.f5539a.addView(cVar2);
                }
                for (c layoutParams2 : this.f5547i) {
                    layoutParams2.setLayoutParams(layoutParams);
                }
            }
        }
    }
}
