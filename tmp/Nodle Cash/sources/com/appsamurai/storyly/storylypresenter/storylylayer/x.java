package com.appsamurai.storyly.storylypresenter.storylylayer;

import A0.C0201b;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewGroupKt;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.i;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class x {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6202a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final FrameLayout f6203b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final StorylyConfig f6204c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public final com.appsamurai.storyly.analytics.e f6205d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public v f6206e;

    /* renamed from: f  reason: collision with root package name */
    public Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> f6207f;

    /* renamed from: g  reason: collision with root package name */
    public Function1<? super b0, Unit> f6208g;

    /* renamed from: h  reason: collision with root package name */
    public Function0<Unit> f6209h;

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f6210i;

    /* renamed from: j  reason: collision with root package name */
    public Function1<? super Integer, Unit> f6211j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f6212k;

    /* renamed from: l  reason: collision with root package name */
    public Function0<Unit> f6213l;

    /* renamed from: m  reason: collision with root package name */
    public Function0<Unit> f6214m;

    /* renamed from: n  reason: collision with root package name */
    public Function1<? super Long, Unit> f6215n;

    /* renamed from: o  reason: collision with root package name */
    public Function0<Unit> f6216o;

    /* renamed from: p  reason: collision with root package name */
    public Function1<? super List<Pair<Integer, Float>>, Unit> f6217p;
    @NotNull

    /* renamed from: q  reason: collision with root package name */
    public AtomicInteger f6218q = new AtomicInteger(0);
    @NotNull

    /* renamed from: r  reason: collision with root package name */
    public AtomicInteger f6219r = new AtomicInteger(0);

    /* renamed from: s  reason: collision with root package name */
    public boolean f6220s;

    /* renamed from: t  reason: collision with root package name */
    public boolean f6221t = true;
    @Nullable

    /* renamed from: u  reason: collision with root package name */
    public a f6222u;
    @Nullable

    /* renamed from: v  reason: collision with root package name */
    public Integer f6223v;

    /* renamed from: w  reason: collision with root package name */
    public boolean f6224w;

    /* renamed from: x  reason: collision with root package name */
    public z f6225x;
    @NotNull

    /* renamed from: y  reason: collision with root package name */
    public final Lazy f6226y = LazyKt.lazy(new d(this));

    public final class a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public List<o1> f6227a = new ArrayList();
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public Map<String, o1> f6228b = new LinkedHashMap();

        public a(x xVar) {
            Intrinsics.checkNotNullParameter(xVar, "this$0");
        }

        public final void a(@NotNull Function1<? super List<o1>, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            synchronized (this.f6227a) {
                function1.invoke(this.f6227a);
                Unit unit = Unit.INSTANCE;
            }
        }

        public final void b(@NotNull Function1<? super Map<String, o1>, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            synchronized (this.f6228b) {
                function1.invoke(this.f6228b);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public static final class b extends Lambda implements Function1<Map<String, o1>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b0 f6229a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ o1 f6230b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(b0 b0Var, o1 o1Var) {
            super(1);
            this.f6229a = b0Var;
            this.f6230b = o1Var;
        }

        public Object invoke(Object obj) {
            Map map = (Map) obj;
            Intrinsics.checkNotNullParameter(map, "it");
            map.put(this.f6229a.f3614i, this.f6230b);
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function1<List<o1>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o1 f6231a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(o1 o1Var) {
            super(1);
            this.f6231a = o1Var;
        }

        public Object invoke(Object obj) {
            List list = (List) obj;
            Intrinsics.checkNotNullParameter(list, "it");
            list.add(this.f6231a);
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function0<c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ x f6232a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(x xVar) {
            super(0);
            this.f6232a = xVar;
        }

        public Object invoke() {
            c cVar = new c(this.f6232a.f6202a);
            x xVar = this.f6232a;
            a0 a0Var = new a0(xVar);
            Intrinsics.checkNotNullParameter(a0Var, "<set-?>");
            cVar.f5661e = a0Var;
            Function1<? super List<Pair<Integer, Float>>, Unit> function1 = xVar.f6217p;
            if (function1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onMetadataPartsReady");
                function1 = null;
            }
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            cVar.f5660d = function1;
            return cVar;
        }
    }

    public static final class e extends Lambda implements Function1<List<o1>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final e f6233a = new e();

        public e() {
            super(1);
        }

        public Object invoke(Object obj) {
            List<o1> list = (List) obj;
            Intrinsics.checkNotNullParameter(list, "it");
            for (o1 c3 : list) {
                c3.c();
            }
            return Unit.INSTANCE;
        }
    }

    public x(@NotNull Context context, @NotNull FrameLayout frameLayout, @NotNull StorylyConfig storylyConfig, @Nullable com.appsamurai.storyly.analytics.e eVar) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(frameLayout, TtmlNode.TAG_LAYOUT);
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6202a = context;
        this.f6203b = frameLayout;
        this.f6204c = storylyConfig;
        this.f6205d = eVar;
    }

    public final c a() {
        return (c) this.f6226y.getValue();
    }

    @NotNull
    public final Function0<Unit> b() {
        Function0<Unit> function0 = this.f6212k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onLayerLoadFail");
        return null;
    }

    @NotNull
    public final Function1<b0, Unit> c() {
        Function1<? super b0, Unit> function1 = this.f6208g;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserActionClick");
        return null;
    }

    @NotNull
    public final Function0<Unit> d() {
        Function0<Unit> function0 = this.f6210i;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionEnded");
        return null;
    }

    @NotNull
    public final Function0<Unit> e() {
        Function0<Unit> function0 = this.f6209h;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserInteractionStarted");
        return null;
    }

    @NotNull
    public final Function5<com.appsamurai.storyly.analytics.a, b0, StoryComponent, JsonObject, Function1<? super Boolean, Unit>, Unit> f() {
        Function5<? super com.appsamurai.storyly.analytics.a, ? super b0, ? super StoryComponent, ? super JsonObject, ? super Function1<? super Boolean, Unit>, Unit> function5 = this.f6207f;
        if (function5 != null) {
            return function5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onUserReaction");
        return null;
    }

    public final void g() {
        this.f6221t = true;
        this.f6220s = false;
        this.f6224w = false;
        this.f6223v = null;
        a aVar = this.f6222u;
        if (aVar != null) {
            aVar.a(e.f6233a);
        }
        this.f6222u = null;
        c a2 = a();
        a2.f5658b = null;
        a2.f5659c.clear();
        this.f6203b.removeAllViews();
    }

    public static /* synthetic */ void a(x xVar, o1 o1Var, Integer num, Boolean bool, int i3) {
        if ((i3 & 4) != 0) {
            bool = null;
        }
        xVar.a(o1Var, (Integer) null, bool);
    }

    public final void a(o1 o1Var, Integer num, Boolean bool) {
        StoryGroupType storyGroupType;
        View view;
        Unit unit;
        Function1<? super Integer, Unit> function1 = null;
        if (this.f6221t && num != null) {
            int intValue = num.intValue();
            Integer num2 = this.f6223v;
            if (num2 == null) {
                unit = null;
            } else {
                this.f6223v = Integer.valueOf(Math.max(intValue, num2.intValue()));
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                this.f6223v = Integer.valueOf(intValue);
            }
        }
        if (Intrinsics.areEqual((Object) bool, (Object) Boolean.TRUE)) {
            this.f6219r.decrementAndGet();
        } else if (Intrinsics.areEqual((Object) bool, (Object) Boolean.FALSE)) {
            this.f6218q.decrementAndGet();
        } else if (bool == null) {
            this.f6219r.decrementAndGet();
            this.f6218q.decrementAndGet();
        }
        if (!this.f6221t) {
            a(o1Var);
            return;
        }
        synchronized (this) {
            try {
                if (this.f6218q.get() == 0 && !this.f6224w) {
                    a aVar = this.f6222u;
                    if (aVar != null) {
                        aVar.a(new y(this));
                    }
                    this.f6224w = true;
                }
                if (this.f6219r.get() == 0 && this.f6224w) {
                    v vVar = this.f6206e;
                    if (vVar == null) {
                        storyGroupType = null;
                    } else {
                        storyGroupType = vVar.f4228h;
                    }
                    if (storyGroupType == StoryGroupType.Ad) {
                        Iterator<View> it = ViewGroupKt.getChildren(this.f6203b).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                view = null;
                                break;
                            }
                            view = it.next();
                            if (view instanceof h) {
                                break;
                            }
                        }
                        View view2 = view;
                        if (view2 != null) {
                            Map mapOf = MapsKt.mapOf(TuplesKt.to("cta", view2));
                            for (View next : ViewGroupKt.getChildren(this.f6203b)) {
                                if (next instanceof g) {
                                    ((g) next).setLayers(mapOf);
                                }
                            }
                        }
                    }
                    Function1<? super Integer, Unit> function12 = this.f6211j;
                    if (function12 != null) {
                        function1 = function12;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("onAllLayersAdded");
                    }
                    function1.invoke(this.f6223v);
                    this.f6203b.setVisibility(0);
                    this.f6221t = false;
                }
            } finally {
            }
        }
    }

    public final void a(o1 o1Var) {
        if (o1Var.getParent() == null) {
            new Handler(Looper.getMainLooper()).post(new C0201b(this, o1Var, 2));
            com.appsamurai.storyly.analytics.e eVar = this.f6205d;
            if (eVar != null) {
                com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.InteractiveImpression;
                v vVar = this.f6206e;
                z zVar = this.f6225x;
                if (zVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("storylyItem");
                    zVar = null;
                }
                z zVar2 = zVar;
                b0 storylyLayerItem$storyly_release = o1Var.getStorylyLayerItem$storyly_release();
                b0 storylyLayerItem$storyly_release2 = o1Var.getStorylyLayerItem$storyly_release();
                com.appsamurai.storyly.analytics.e.a(eVar, aVar, vVar, zVar2, storylyLayerItem$storyly_release, storylyLayerItem$storyly_release2.f3615j.a(storylyLayerItem$storyly_release2), (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4064);
            }
        }
    }

    public final void a(b0 b0Var, o1 o1Var) {
        a aVar = this.f6222u;
        if (aVar != null) {
            aVar.b(new b(b0Var, o1Var));
        }
        a aVar2 = this.f6222u;
        if (aVar2 != null) {
            aVar2.a(new c(o1Var));
        }
    }

    public static final void a(x xVar, o1 o1Var) {
        boolean z2;
        com.appsamurai.storyly.data.x storylyLayer$storyly_release;
        Intrinsics.checkNotNullParameter(xVar, "this$0");
        Intrinsics.checkNotNullParameter(o1Var, "$layerView");
        xVar.f6203b.addView(o1Var);
        float measuredWidth = (float) xVar.f6203b.getMeasuredWidth();
        float measuredHeight = (float) xVar.f6203b.getMeasuredHeight();
        if (((float) xVar.f6203b.getMeasuredHeight()) / ((float) xVar.f6203b.getMeasuredWidth()) >= 1.7777778f) {
            measuredHeight = ((float) xVar.f6203b.getMeasuredWidth()) * 1.7777778f;
        } else {
            measuredWidth = ((float) xVar.f6203b.getMeasuredHeight()) / 1.7777778f;
        }
        List<i> list = null;
        w wVar = o1Var instanceof w ? (w) o1Var : null;
        if (wVar == null || (storylyLayer$storyly_release = wVar.getStorylyLayer$storyly_release()) == null) {
            z2 = false;
        } else {
            z2 = storylyLayer$storyly_release.f4259h;
        }
        if (z2) {
            measuredWidth = (float) xVar.f6203b.getMeasuredWidth();
            measuredHeight = (float) xVar.f6203b.getMeasuredHeight();
        }
        o1 o1Var2 = o1Var != null ? o1Var : null;
        if (o1Var2 != null) {
            o1Var2.setSafeFrame$storyly_release(new d(new Pair(Float.valueOf(measuredWidth), Float.valueOf(measuredHeight)), new Pair(Float.valueOf(0.0f), Float.valueOf(0.0f))));
        }
        com.appsamurai.storyly.data.b bVar = o1Var.getStorylyLayerItem$storyly_release().f3618m;
        if (bVar != null) {
            list = bVar.f3599a;
        }
        if (list == null) {
            o1Var.setAlpha(0.0f);
            o1Var.animate().alpha(1.0f).setDuration(400);
        }
    }

    @Nullable
    public final Bitmap a(boolean z2) {
        View view;
        if (z2) {
            ViewParent parent = this.f6203b.getParent();
            view = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
        } else {
            view = this.f6203b;
        }
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.draw(canvas);
        a aVar = this.f6222u;
        if (aVar != null) {
            aVar.a(new h1(canvas));
        }
        return createBitmap;
    }
}
