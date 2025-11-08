package com.appsamurai.storyly.storylypresenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.databinding.k;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class b extends Dialog {

    /* renamed from: k  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4745k;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final com.appsamurai.storyly.analytics.e f4746a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f4747b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function1<? super Function0<Unit>, Unit> f4748c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f4749d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final ReadWriteProperty f4750e = new f((Object) null, (Object) null, this);
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public String f4751f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public k f4752g;
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public View f4753h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Lazy f4754i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f4755j;

    public static final class a extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4756a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(b bVar) {
            super(0);
            this.f4756a = bVar;
        }

        public Object invoke() {
            this.f4756a.a(false, (Integer) null);
            return Unit.INSTANCE;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.b$b  reason: collision with other inner class name */
    public static final class C0017b extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4757a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0017b(b bVar) {
            super(0);
            this.f4757a = bVar;
        }

        public Object invoke() {
            this.f4757a.a(false, (Integer) null);
            return Unit.INSTANCE;
        }
    }

    public static final class c extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4758a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(b bVar) {
            super(0);
            this.f4758a = bVar;
        }

        public Object invoke() {
            this.f4758a.a(false, (Integer) null);
            return Unit.INSTANCE;
        }
    }

    public static final class d extends Lambda implements Function0<Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4759a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f4759a = context;
        }

        public Object invoke() {
            Window window;
            View decorView;
            WindowInsets rootWindowInsets;
            Context context = this.f4759a;
            DisplayCutout displayCutout = null;
            Activity activity = context instanceof Activity ? (Activity) context : null;
            if (!(activity == null || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null || (rootWindowInsets = decorView.getRootWindowInsets()) == null)) {
                displayCutout = rootWindowInsets.getDisplayCutout();
            }
            return Boolean.valueOf(displayCutout != null);
        }
    }

    public static final class e extends ObservableProperty<List<? extends v>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4760a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f4761b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f4760a = obj;
            this.f4761b = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends v> list, List<? extends v> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list;
            this.f4761b.b().setStorylyGroupItems(CollectionsKt.toMutableList(list2));
        }
    }

    public static final class f extends ObservableProperty<com.appsamurai.storyly.data.managers.product.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ b f4762a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Object obj, Object obj2, b bVar) {
            super(null);
            this.f4762a = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, com.appsamurai.storyly.data.managers.product.a aVar, com.appsamurai.storyly.data.managers.product.a aVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            com.appsamurai.storyly.data.managers.product.a aVar3 = aVar;
            this.f4762a.b().setStorylyCart(aVar2);
        }
    }

    public static final class g extends Lambda implements Function0<d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4763a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyConfig f4764b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ com.appsamurai.storyly.data.managers.cache.c f4765c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ b f4766d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Context context, StorylyConfig storylyConfig, com.appsamurai.storyly.data.managers.cache.c cVar, b bVar) {
            super(0);
            this.f4763a = context;
            this.f4764b = storylyConfig;
            this.f4765c = cVar;
            this.f4766d = bVar;
        }

        public Object invoke() {
            d dVar = new d(this.f4763a, this.f4764b, this.f4765c);
            dVar.setOnPagingThresholdPassed$storyly_release(new c(this.f4766d));
            return dVar;
        }
    }

    static {
        Class<b> cls = b.class;
        f4745k = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyGroupItems", "getStorylyGroupItems()Ljava/util/List;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCart", "getStorylyCart()Lcom/appsamurai/storyly/data/managers/product/StorylyCart;", 0)};
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(@NotNull Context context, int i3, @NotNull com.appsamurai.storyly.analytics.e eVar, @NotNull StorylyConfig storylyConfig, @NotNull com.appsamurai.storyly.data.managers.cache.c cVar, @NotNull Function1<? super v, Unit> function1, @NotNull Function1<? super Story, Unit> function12, @NotNull Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function3, @NotNull Function2<? super StoryGroup, ? super Story, Unit> function2, @NotNull Function1<? super z, Boolean> function13) {
        super(context, i3);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(cVar, "storylyImageCacheManager");
        Intrinsics.checkNotNullParameter(function1, "onStorylyGroupShown");
        Intrinsics.checkNotNullParameter(function12, "onStorylyActionClicked");
        Intrinsics.checkNotNullParameter(function3, "onStoryLayerInteraction");
        Intrinsics.checkNotNullParameter(function2, "onStorylyHeaderClicked");
        Intrinsics.checkNotNullParameter(function13, "onStoryConditionCheck");
        this.f4746a = eVar;
        this.f4747b = LazyKt.lazy(new g(context, storylyConfig, cVar, this));
        Delegates delegates = Delegates.INSTANCE;
        ArrayList arrayList = new ArrayList();
        this.f4749d = new e(arrayList, arrayList, this);
        this.f4754i = LazyKt.lazy(new d(context));
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        k a2 = k.a(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(layoutInflater)");
        this.f4752g = a2;
        setContentView(a2.a());
        this.f4752g.f4385c.addView(b(), -1, -1);
        b().setOnClosed$storyly_release(new a(this));
        b().setOnCompleted$storyly_release(new C0017b(this));
        b().setOnDismissed$storyly_release(new c(this));
        b().setOnStorylyActionClicked$storyly_release(function12);
        b().setOnStorylyGroupShown$storyly_release(function1);
        b().setStorylyTracker(eVar);
        d b3 = b();
        FrameLayout frameLayout = this.f4752g.f4385c;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stStorylyDialogLayout");
        b3.setBackgroundLayout(frameLayout);
        b().setOnStoryLayerInteraction$storyly_release(function3);
        b().setOnStorylyHeaderClicked$storyly_release(function2);
        b().setOnStoryConditionCheck$storyly_release(function13);
    }

    public static final void c(b bVar) {
        Intrinsics.checkNotNullParameter(bVar, "this$0");
        bVar.a((List<v>) CollectionsKt.emptyList());
    }

    @NotNull
    public final List<v> a() {
        return (List) this.f4749d.getValue(this, f4745k[0]);
    }

    public final d b() {
        return (d) this.f4747b.getValue();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (((Boolean) this.f4754i.getValue()).booleanValue()) {
            Window window = getWindow();
            if (window != null) {
                window.addFlags(2);
            }
            Window window2 = getWindow();
            if (window2 != null) {
                window2.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
    }

    public void onBackPressed() {
        d b3 = b();
        o a2 = b3.a(b3.getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.e();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new W.b(this, 2), 200);
        super.onBackPressed();
    }

    public void onWindowFocusChanged(boolean z2) {
        super.onWindowFocusChanged(z2);
        if (this.f4755j && z2) {
            com.appsamurai.storyly.analytics.e.a(this.f4746a, com.appsamurai.storyly.analytics.a.OnScreen, (v) null, (z) null, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            if (this.f4753h == null && this.f4752g.f4384b.getChildCount() == 0) {
                b().e();
                this.f4755j = false;
            }
        } else if (!z2) {
            b().d();
            this.f4755j = true;
        }
    }

    public void setOnCancelListener(@Nullable DialogInterface.OnCancelListener onCancelListener) {
        if (onCancelListener == null || !StringsKt__StringsKt.contains((CharSequence) onCancelListener.getClass().getName(), (CharSequence) "fragment", true)) {
            super.setOnCancelListener(onCancelListener);
        }
    }

    public void setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        if (onDismissListener == null || !StringsKt__StringsKt.contains((CharSequence) onDismissListener.getClass().getName(), (CharSequence) "fragment", true)) {
            super.setOnDismissListener(onDismissListener);
        }
    }

    public void setOnShowListener(@Nullable DialogInterface.OnShowListener onShowListener) {
        if (onShowListener == null || !StringsKt__StringsKt.contains((CharSequence) onShowListener.getClass().getName(), (CharSequence) "fragment", true)) {
            super.setOnShowListener(onShowListener);
        }
    }

    public static final void b(b bVar) {
        Intrinsics.checkNotNullParameter(bVar, "this$0");
        bVar.a((List<v>) CollectionsKt.emptyList());
    }

    public final void a(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f4749d.setValue(this, f4745k[0], list);
    }

    public final void c() {
        b().b();
        b().e();
    }

    public final void a(@Nullable Integer num) {
        d b3 = b();
        b3.setSelectedStorylyGroupIndex(num);
        new Handler(Looper.getMainLooper()).postDelayed(new W.a(b3, 0), 150);
    }

    public static final void a(d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "$this_apply");
        dVar.b();
        o a2 = dVar.a(dVar.getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.l();
        }
    }

    public final void a(boolean z2, @Nullable Integer num) {
        if (z2) {
            this.f4755j = true;
            b().d();
        }
        View view = this.f4753h;
        if (view != null) {
            this.f4752g.f4385c.removeView(view);
            c();
            this.f4753h = null;
        }
        if (num != null) {
            int intValue = num.intValue();
            Window window = getWindow();
            if (window != null) {
                window.setWindowAnimations(intValue);
            }
        }
        new Handler(Looper.getMainLooper()).post(new W.b(this, 0));
        if (!z2) {
            new Handler(Looper.getMainLooper()).postDelayed(new W.b(this, 1), 200);
        }
    }

    public static final void a(b bVar) {
        Intrinsics.checkNotNullParameter(bVar, "this$0");
        bVar.dismiss();
    }
}
