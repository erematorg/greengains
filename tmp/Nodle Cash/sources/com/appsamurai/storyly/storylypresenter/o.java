package com.appsamurai.storyly.storylypresenter;

import A0.C0202c;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.OneShotPreDrawListener;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.StoryType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.d0;
import com.appsamurai.storyly.data.g0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartEventResult;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import com.appsamurai.storyly.storylypresenter.storylycenter.a;
import com.appsamurai.storyly.storylypresenter.storylyfooter.a;
import com.appsamurai.storyly.storylypresenter.storylyheader.a;
import com.appsamurai.storyly.storylypresenter.storylylayer.i1;
import com.appsamurai.storyly.storylypresenter.storylylayer.j1;
import com.appsamurai.storyly.storylypresenter.storylylayer.k1;
import com.appsamurai.storyly.storylypresenter.storylylayer.l1;
import com.appsamurai.storyly.storylypresenter.storylylayer.x;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jnr.ffi.provider.jffi.JNINativeInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor", "ClickableViewAccessibility"})
public final class o extends RelativeLayout {

    /* renamed from: L  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5037L;

    /* renamed from: A  reason: collision with root package name */
    public com.appsamurai.storyly.external.a f5038A;
    @NotNull

    /* renamed from: B  reason: collision with root package name */
    public final Lazy f5039B;
    @NotNull

    /* renamed from: C  reason: collision with root package name */
    public final Lazy f5040C;
    @NotNull

    /* renamed from: D  reason: collision with root package name */
    public final Lazy f5041D;
    @NotNull

    /* renamed from: E  reason: collision with root package name */
    public final Lazy f5042E;
    @NotNull
    public final Lazy F;
    @NotNull
    public final Lazy G;
    @NotNull

    /* renamed from: H  reason: collision with root package name */
    public final Lazy f5043H;

    /* renamed from: I  reason: collision with root package name */
    public boolean f5044I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f5045J;
    @NotNull
    public final Lazy K;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final com.appsamurai.storyly.analytics.e f5046a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f5047b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final com.appsamurai.storyly.data.managers.cache.c f5048c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final ReadWriteProperty f5049d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final com.appsamurai.storyly.databinding.j f5050e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public a f5051f = a.Initiated;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public Map<Integer, z> f5052g = new LinkedHashMap();
    @Nullable

    /* renamed from: h  reason: collision with root package name */
    public List<com.appsamurai.storyly.data.v> f5053h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final ReadWriteProperty f5054i = new C0024o((Object) null, (Object) null, this);
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public com.appsamurai.storyly.data.v f5055j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public com.appsamurai.storyly.data.managers.product.a f5056k;
    @NotNull

    /* renamed from: l  reason: collision with root package name */
    public final ReadWriteProperty f5057l = new q((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Lazy f5058m;
    @NotNull

    /* renamed from: n  reason: collision with root package name */
    public final ReadWriteProperty f5059n;

    /* renamed from: o  reason: collision with root package name */
    public Function0<Unit> f5060o;

    /* renamed from: p  reason: collision with root package name */
    public Function0<Unit> f5061p;

    /* renamed from: q  reason: collision with root package name */
    public Function0<Unit> f5062q;

    /* renamed from: r  reason: collision with root package name */
    public Function1<? super Story, Unit> f5063r;

    /* renamed from: s  reason: collision with root package name */
    public Function0<Unit> f5064s;

    /* renamed from: t  reason: collision with root package name */
    public Function0<Unit> f5065t;

    /* renamed from: u  reason: collision with root package name */
    public Function0<Unit> f5066u;

    /* renamed from: v  reason: collision with root package name */
    public Function1<? super Float, Unit> f5067v;

    /* renamed from: w  reason: collision with root package name */
    public Function1<? super Boolean, Unit> f5068w;

    /* renamed from: x  reason: collision with root package name */
    public Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> f5069x;

    /* renamed from: y  reason: collision with root package name */
    public Function2<? super StoryGroup, ? super Story, Unit> f5070y;

    /* renamed from: z  reason: collision with root package name */
    public Function1<? super z, Boolean> f5071z;

    public enum a {
        Initiated,
        Buffering,
        Loaded,
        Started,
        Paused
    }

    public static final class b extends Lambda implements Function0<a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5078a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(o oVar) {
            super(0);
            this.f5078a = oVar;
        }

        public Object invoke() {
            a aVar = new a();
            o oVar = this.f5078a;
            s sVar = new s(oVar);
            Intrinsics.checkNotNullParameter(sVar, "<set-?>");
            aVar.f4729j = sVar;
            t tVar = new t(oVar);
            Intrinsics.checkNotNullParameter(tVar, "<set-?>");
            aVar.f4730k = tVar;
            u uVar = new u(oVar);
            Intrinsics.checkNotNullParameter(uVar, "<set-?>");
            aVar.f4731l = uVar;
            v vVar = new v(oVar);
            Intrinsics.checkNotNullParameter(vVar, "<set-?>");
            aVar.f4732m = vVar;
            w wVar = new w(oVar);
            Intrinsics.checkNotNullParameter(wVar, "<set-?>");
            aVar.f4733n = wVar;
            Function0<Unit> onSwipeHorizontal$storyly_release = oVar.getOnSwipeHorizontal$storyly_release();
            Intrinsics.checkNotNullParameter(onSwipeHorizontal$storyly_release, "<set-?>");
            aVar.f4724e = onSwipeHorizontal$storyly_release;
            x xVar = new x(oVar);
            Intrinsics.checkNotNullParameter(xVar, "<set-?>");
            aVar.f4725f = xVar;
            y yVar = new y(oVar);
            Intrinsics.checkNotNullParameter(yVar, "<set-?>");
            aVar.f4726g = yVar;
            z zVar = new z(oVar);
            Intrinsics.checkNotNullParameter(zVar, "<set-?>");
            aVar.f4727h = zVar;
            a0 a0Var = new a0(oVar);
            Intrinsics.checkNotNullParameter(a0Var, "<set-?>");
            aVar.f4728i = a0Var;
            p pVar = new p(oVar);
            Intrinsics.checkNotNullParameter(pVar, "<set-?>");
            aVar.f4734o = pVar;
            q qVar = new q(oVar);
            Intrinsics.checkNotNullParameter(qVar, "<set-?>");
            aVar.f4735p = qVar;
            Function0<Unit> onTouchUp$storyly_release = oVar.getOnTouchUp$storyly_release();
            Intrinsics.checkNotNullParameter(onTouchUp$storyly_release, "<set-?>");
            aVar.f4736q = onTouchUp$storyly_release;
            r rVar = new r(oVar);
            Intrinsics.checkNotNullParameter(rVar, "<set-?>");
            aVar.f4737r = rVar;
            return aVar;
        }
    }

    public static final class c extends Lambda implements Function0<Handler> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f5079a = new c();

        public c() {
            super(0);
        }

        public Object invoke() {
            return new Handler(Looper.getMainLooper());
        }
    }

    public static final class d extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.c> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5080a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Context context) {
            super(0);
            this.f5080a = context;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.data.managers.storage.c(this.f5080a, "stryly-moments-report-status");
        }
    }

    public static final class e extends Lambda implements Function4<STRCartItem, Integer, Function1<? super STRCart, ? extends Unit>, Function1<? super STRCartEventResult, ? extends Unit>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5081a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(o oVar) {
            super(4);
            this.f5081a = oVar;
        }

        public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            STRProductItem item;
            STRCartItem sTRCartItem = (STRCartItem) obj;
            int intValue = ((Number) obj2).intValue();
            Function1 function1 = (Function1) obj3;
            Function1 function12 = (Function1) obj4;
            Intrinsics.checkNotNullParameter(function1, "onSuccess");
            Intrinsics.checkNotNullParameter(function12, "onFail");
            com.appsamurai.storyly.analytics.a aVar = (sTRCartItem != null && sTRCartItem.getQuantity() == 0) ? com.appsamurai.storyly.analytics.a.ProductRemoved : com.appsamurai.storyly.analytics.a.ProductUpdated;
            o oVar = this.f5081a;
            com.appsamurai.storyly.analytics.e eVar = oVar.f5046a;
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
            z h3 = this.f5081a.getStorylyItem();
            com.appsamurai.storyly.data.managers.product.a storylyCart$storyly_release = this.f5081a.getStorylyCart$storyly_release();
            Integer num = null;
            STRCart sTRCart = storylyCart$storyly_release == null ? null : storylyCart$storyly_release.f4039b;
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            if (!(sTRCartItem == null || (item = sTRCartItem.getItem()) == null)) {
                item.serialize$storyly_release(jsonObjectBuilder2);
            }
            if (sTRCartItem != null) {
                num = Integer.valueOf(sTRCartItem.getQuantity());
            }
            JsonElementBuildersKt.put(jsonObjectBuilder2, FirebaseAnalytics.Param.QUANTITY, (Number) num);
            JsonElementBuildersKt.put(jsonObjectBuilder2, "change", (Number) Integer.valueOf(intValue));
            Unit unit = Unit.INSTANCE;
            jsonObjectBuilder.put("product", jsonObjectBuilder2.build());
            com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, h3, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, function1, function12, sTRCart, sTRCartItem, JNINativeInterface.UnregisterNatives);
            return Unit.INSTANCE;
        }
    }

    public static final class f extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5082a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(o oVar) {
            super(0);
            this.f5082a = oVar;
        }

        public Object invoke() {
            this.f5082a.j();
            o oVar = this.f5082a;
            com.appsamurai.storyly.analytics.e.a(oVar.f5046a, com.appsamurai.storyly.analytics.a.CheckoutButtonClicked, oVar.getStorylyGroupItem$storyly_release(), this.f5082a.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            return Unit.INSTANCE;
        }
    }

    public static final class g extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5083a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(o oVar) {
            super(0);
            this.f5083a = oVar;
        }

        public Object invoke() {
            this.f5083a.j();
            return Unit.INSTANCE;
        }
    }

    public static final class h extends Lambda implements Function1<STRProductItem, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5084a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(o oVar) {
            super(1);
            this.f5084a = oVar;
        }

        public Object invoke(Object obj) {
            STRProductItem sTRProductItem = (STRProductItem) obj;
            o oVar = this.f5084a;
            com.appsamurai.storyly.analytics.e eVar = oVar.f5046a;
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.ProductSelected;
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
            z h3 = this.f5084a.getStorylyItem();
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            if (sTRProductItem != null) {
                sTRProductItem.serialize$storyly_release(jsonObjectBuilder2);
            }
            Unit unit = Unit.INSTANCE;
            jsonObjectBuilder.put("product", jsonObjectBuilder2.build());
            com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, h3, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
            return Unit.INSTANCE;
        }
    }

    public static final class i extends Lambda implements Function4<STRProductItem, Integer, Function1<? super STRCart, ? extends Unit>, Function1<? super STRCartEventResult, ? extends Unit>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5085a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public i(o oVar) {
            super(4);
            this.f5085a = oVar;
        }

        public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            STRProductItem sTRProductItem = (STRProductItem) obj;
            int intValue = ((Number) obj2).intValue();
            Function1 function1 = (Function1) obj3;
            Function1 function12 = (Function1) obj4;
            Intrinsics.checkNotNullParameter(sTRProductItem, "product");
            Intrinsics.checkNotNullParameter(function1, "onSuccess");
            Intrinsics.checkNotNullParameter(function12, "onFail");
            STRCartItem sTRCartItem = new STRCartItem(sTRProductItem, intValue, (Float) null, (Float) null);
            o oVar = this.f5085a;
            com.appsamurai.storyly.analytics.e eVar = oVar.f5046a;
            com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.ProductAdded;
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
            z h3 = this.f5085a.getStorylyItem();
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            JsonObjectBuilder jsonObjectBuilder2 = new JsonObjectBuilder();
            sTRProductItem.serialize$storyly_release(jsonObjectBuilder2);
            JsonElementBuildersKt.put(jsonObjectBuilder2, FirebaseAnalytics.Param.QUANTITY, String.valueOf(intValue));
            Unit unit = Unit.INSTANCE;
            jsonObjectBuilder.put("product", jsonObjectBuilder2.build());
            com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, h3, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, function1, function12, (STRCart) null, sTRCartItem, 1240);
            return Unit.INSTANCE;
        }
    }

    public static final class j extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5086a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ g0 f5087b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public j(o oVar, g0 g0Var) {
            super(0);
            this.f5086a = oVar;
            this.f5087b = g0Var;
        }

        public Object invoke() {
            this.f5086a.a(this.f5087b);
            return Unit.INSTANCE;
        }
    }

    public static final class k extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5088a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public k(o oVar) {
            super(0);
            this.f5088a = oVar;
        }

        public Object invoke() {
            this.f5088a.j();
            return Unit.INSTANCE;
        }
    }

    public static final class l extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5089a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public l(o oVar) {
            super(0);
            this.f5089a = oVar;
        }

        public Object invoke() {
            com.appsamurai.storyly.data.managers.product.a storylyCart$storyly_release = this.f5089a.getStorylyCart$storyly_release();
            if (storylyCart$storyly_release != null && storylyCart$storyly_release.f4038a) {
                this.f5089a.k();
            }
            o oVar = this.f5089a;
            com.appsamurai.storyly.analytics.e.a(oVar.f5046a, com.appsamurai.storyly.analytics.a.CartButtonClicked, oVar.getStorylyGroupItem$storyly_release(), this.f5089a.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            return Unit.INSTANCE;
        }
    }

    public static final class m extends Lambda implements Function0<Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5090a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public m(o oVar) {
            super(0);
            this.f5090a = oVar;
        }

        public Object invoke() {
            this.f5090a.j();
            return Unit.INSTANCE;
        }
    }

    public static final class n extends ObservableProperty<Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5091a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public n(Object obj, Object obj2, o oVar) {
            super(obj2);
            this.f5091a = oVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Boolean bool, Boolean bool2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            boolean booleanValue = bool2.booleanValue();
            bool.getClass();
            this.f5091a.getStorylyFooterView().f5347d = booleanValue;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.o$o  reason: collision with other inner class name */
    public static final class C0024o extends ObservableProperty<com.appsamurai.storyly.data.v> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5092a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0024o(Object obj, Object obj2, o oVar) {
            super(null);
            this.f5092a = oVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, com.appsamurai.storyly.data.v vVar, com.appsamurai.storyly.data.v vVar2) {
            int i3;
            List<z> list;
            List<z> list2;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            com.appsamurai.storyly.data.v vVar3 = vVar2;
            com.appsamurai.storyly.data.v vVar4 = vVar;
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = this.f5092a.getStorylyGroupItem$storyly_release();
            if (!(storylyGroupItem$storyly_release == null || (list2 = storylyGroupItem$storyly_release.f4226f) == null)) {
                int i4 = 0;
                for (T next : list2) {
                    int i5 = i4 + 1;
                    if (i4 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    z zVar = (z) next;
                    if (!zVar.f4318q) {
                        this.f5092a.f5052g.put(Integer.valueOf(i4), zVar);
                    }
                    i4 = i5;
                }
            }
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release2 = this.f5092a.getStorylyGroupItem$storyly_release();
            if (!(storylyGroupItem$storyly_release2 == null || (list = storylyGroupItem$storyly_release2.f4226f) == null)) {
                CollectionsKt__MutableCollectionsKt.removeAll(list, v.f5099a);
            }
            this.f5092a.getStorylyLayerContainerView().f6206e = this.f5092a.getStorylyGroupItem$storyly_release();
            this.f5092a.getStorylyFooterView().f5348e = this.f5092a.getStorylyGroupItem$storyly_release();
            com.appsamurai.storyly.storylypresenter.storylyheader.a g2 = this.f5092a.getStorylyHeaderView();
            g2.f5465g.setValue(g2, com.appsamurai.storyly.storylypresenter.storylyheader.a.f5458o[0], this.f5092a.getStorylyGroupItem$storyly_release());
            o oVar = this.f5092a;
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release3 = oVar.getStorylyGroupItem$storyly_release();
            Integer num = null;
            if (storylyGroupItem$storyly_release3 != null) {
                Integer num2 = storylyGroupItem$storyly_release3.f4238r;
                if (num2 == null) {
                    Iterator<z> it = storylyGroupItem$storyly_release3.f4226f.iterator();
                    int i6 = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            i6 = -1;
                            break;
                        } else if (!it.next().f4320s) {
                            break;
                        } else {
                            i6++;
                        }
                    }
                    i3 = Math.max(i6, 0);
                } else {
                    int intValue = num2.intValue();
                    storylyGroupItem$storyly_release3.f4238r = null;
                    i3 = intValue;
                }
                num = Integer.valueOf(i3);
            }
            oVar.setStorylyCurrentIndex(num);
        }
    }

    public static final class p extends ObservableProperty<z> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5093a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public p(Object obj, Object obj2, o oVar) {
            super(null);
            this.f5093a = oVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            if (zVar != zVar2) {
                a b3 = this.f5093a.getActionManager();
                b3.f4721b.setValue(b3, a.f4719s[0], this.f5093a.getStorylyItem());
                com.appsamurai.storyly.storylypresenter.storylyfooter.a f2 = this.f5093a.getStorylyFooterView();
                f2.f5349f.setValue(f2, com.appsamurai.storyly.storylypresenter.storylyfooter.a.f5343n[0], this.f5093a.getStorylyItem());
                com.appsamurai.storyly.storylypresenter.storylycenter.a d2 = this.f5093a.getStorylyCenterView();
                d2.f5336d.setValue(d2, com.appsamurai.storyly.storylypresenter.storylycenter.a.f5332e[0], this.f5093a.getStorylyItem());
            }
        }
    }

    public static final class q extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5094a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public q(Object obj, Object obj2, o oVar) {
            super(null);
            this.f5094a = oVar;
        }

        /* JADX WARNING: type inference failed for: r4v2, types: [android.view.ViewParent] */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x001b, code lost:
            r2 = r2.f4226f;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean beforeChange(@org.jetbrains.annotations.NotNull kotlin.reflect.KProperty<?> r7, java.lang.Integer r8, java.lang.Integer r9) {
            /*
                r6 = this;
                java.lang.String r0 = "property"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.Integer r9 = (java.lang.Integer) r9
                java.lang.Integer r8 = (java.lang.Integer) r8
                r7 = 0
                r8 = 0
                r0 = 1
                if (r9 == 0) goto L_0x0151
                int r1 = r9.intValue()
                com.appsamurai.storyly.storylypresenter.o r2 = r6.f5094a
                com.appsamurai.storyly.data.v r2 = r2.getStorylyGroupItem$storyly_release()
                if (r2 != 0) goto L_0x001b
                goto L_0x001f
            L_0x001b:
                java.util.List<com.appsamurai.storyly.data.z> r2 = r2.f4226f
                if (r2 != 0) goto L_0x0022
            L_0x001f:
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                goto L_0x0026
            L_0x0022:
                int r2 = r2.size()
            L_0x0026:
                if (r1 > r2) goto L_0x0151
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.data.v r1 = r1.getStorylyGroupItem$storyly_release()
                if (r1 != 0) goto L_0x0031
                goto L_0x0035
            L_0x0031:
                java.util.List<com.appsamurai.storyly.data.z> r1 = r1.f4226f
                if (r1 != 0) goto L_0x0037
            L_0x0035:
                r1 = r8
                goto L_0x003d
            L_0x0037:
                java.lang.Object r1 = com.appsamurai.storyly.util.e.a(r1, r9)
                com.appsamurai.storyly.data.z r1 = (com.appsamurai.storyly.data.z) r1
            L_0x003d:
                if (r1 != 0) goto L_0x0041
                goto L_0x0151
            L_0x0041:
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.data.v r1 = r1.getStorylyGroupItem$storyly_release()
                if (r1 != 0) goto L_0x004a
                goto L_0x0069
            L_0x004a:
                int r2 = r9.intValue()
                com.appsamurai.storyly.storylypresenter.o r3 = r6.f5094a
                com.appsamurai.storyly.data.v r3 = r3.getStorylyGroupItem$storyly_release()
                if (r3 != 0) goto L_0x0057
                goto L_0x005b
            L_0x0057:
                java.util.List<com.appsamurai.storyly.data.z> r3 = r3.f4226f
                if (r3 != 0) goto L_0x005d
            L_0x005b:
                r2 = r8
                goto L_0x0067
            L_0x005d:
                java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
                java.lang.Object r2 = com.appsamurai.storyly.util.e.a(r3, r2)
                com.appsamurai.storyly.data.z r2 = (com.appsamurai.storyly.data.z) r2
            L_0x0067:
                r1.f4240t = r2
            L_0x0069:
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.data.v r2 = r1.getStorylyGroupItem$storyly_release()
                if (r2 != 0) goto L_0x0073
                r2 = r8
                goto L_0x0075
            L_0x0073:
                com.appsamurai.storyly.data.z r2 = r2.f4240t
            L_0x0075:
                r1.setStorylyItem(r2)
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.storylypresenter.storylyheader.a r1 = r1.getStorylyHeaderView()
                kotlin.properties.ReadWriteProperty r2 = r1.f5466h
                kotlin.reflect.KProperty<java.lang.Object>[] r3 = com.appsamurai.storyly.storylypresenter.storylyheader.a.f5458o
                r3 = r3[r0]
                r2.setValue(r1, r3, r9)
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r1 = r1.getStorylyFooterView()
                kotlin.properties.ReadWriteProperty r2 = r1.f5350g
                kotlin.reflect.KProperty<java.lang.Object>[] r3 = com.appsamurai.storyly.storylypresenter.storylyfooter.a.f5343n
                r3 = r3[r0]
                r2.setValue(r1, r3, r9)
                com.appsamurai.storyly.storylypresenter.o r1 = r6.f5094a
                com.appsamurai.storyly.data.managers.product.a r2 = r1.getStorylyCart$storyly_release()
                if (r2 != 0) goto L_0x00a0
                r2 = r8
                goto L_0x00a4
            L_0x00a0:
                java.lang.Integer r2 = r2.a()
            L_0x00a4:
                r1.a((java.lang.Integer) r9, (java.lang.Integer) r2)
                com.appsamurai.storyly.storylypresenter.o r6 = r6.f5094a
                int r9 = r9.intValue()
                android.content.Context r1 = r6.getContext()
                java.lang.String r2 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                boolean r1 = com.appsamurai.storyly.util.g.b(r1)
                if (r1 != 0) goto L_0x00be
                goto L_0x014f
            L_0x00be:
                java.util.List<com.appsamurai.storyly.data.v> r1 = r6.f5053h
                if (r1 != 0) goto L_0x00c4
                goto L_0x014f
            L_0x00c4:
                com.appsamurai.storyly.data.v r2 = r6.getStorylyGroupItem$storyly_release()
                if (r2 != 0) goto L_0x00cc
                goto L_0x014f
            L_0x00cc:
                android.view.ViewParent r3 = r6.getParent()
                boolean r4 = r3 instanceof android.view.View
                if (r4 == 0) goto L_0x00d7
                android.view.View r3 = (android.view.View) r3
                goto L_0x00d8
            L_0x00d7:
                r3 = r8
            L_0x00d8:
                if (r3 != 0) goto L_0x00db
                goto L_0x00e6
            L_0x00db:
                boolean r3 = com.appsamurai.storyly.util.l.a(r3)
                if (r3 != 0) goto L_0x00e6
                com.appsamurai.storyly.databinding.j r3 = r6.f5050e
                android.widget.ImageButton r3 = r3.f4379i
                goto L_0x00ea
            L_0x00e6:
                com.appsamurai.storyly.databinding.j r3 = r6.f5050e
                android.widget.ImageButton r3 = r3.f4380j
            L_0x00ea:
                java.lang.String r4 = "if ((parent as? View)?.i…ng.stNavigationLeftButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                android.view.ViewParent r4 = r6.getParent()
                boolean r5 = r4 instanceof android.view.View
                if (r5 == 0) goto L_0x00fa
                r8 = r4
                android.view.View r8 = (android.view.View) r8
            L_0x00fa:
                if (r8 != 0) goto L_0x00fd
                goto L_0x0108
            L_0x00fd:
                boolean r8 = com.appsamurai.storyly.util.l.a(r8)
                if (r8 != 0) goto L_0x0108
                com.appsamurai.storyly.databinding.j r6 = r6.f5050e
                android.widget.ImageButton r6 = r6.f4380j
                goto L_0x010c
            L_0x0108:
                com.appsamurai.storyly.databinding.j r6 = r6.f5050e
                android.widget.ImageButton r6 = r6.f4379i
            L_0x010c:
                java.lang.String r8 = "if ((parent as? View)?.i…g.stNavigationRightButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r8)
                int r8 = r1.indexOf(r2)
                if (r8 != 0) goto L_0x0119
                r8 = r0
                goto L_0x011a
            L_0x0119:
                r8 = r7
            L_0x011a:
                int r4 = r1.indexOf(r2)
                int r1 = r1.size()
                int r1 = r1 - r0
                if (r4 != r1) goto L_0x0127
                r1 = r0
                goto L_0x0128
            L_0x0127:
                r1 = r7
            L_0x0128:
                if (r9 != 0) goto L_0x012c
                r4 = r0
                goto L_0x012d
            L_0x012c:
                r4 = r7
            L_0x012d:
                java.util.List<com.appsamurai.storyly.data.z> r2 = r2.f4226f
                int r2 = r2.size()
                int r2 = r2 - r0
                if (r9 != r2) goto L_0x0138
                r9 = r0
                goto L_0x0139
            L_0x0138:
                r9 = r7
            L_0x0139:
                r2 = 8
                if (r8 == 0) goto L_0x0142
                if (r4 != 0) goto L_0x0140
                goto L_0x0142
            L_0x0140:
                r8 = r2
                goto L_0x0143
            L_0x0142:
                r8 = r7
            L_0x0143:
                r6.setVisibility(r8)
                if (r1 == 0) goto L_0x014c
                if (r9 != 0) goto L_0x014b
                goto L_0x014c
            L_0x014b:
                r7 = r2
            L_0x014c:
                r3.setVisibility(r7)
            L_0x014f:
                r7 = r0
                goto L_0x0160
            L_0x0151:
                com.appsamurai.storyly.storylypresenter.o r6 = r6.f5094a
                com.appsamurai.storyly.storylypresenter.storylyheader.a r6 = r6.getStorylyHeaderView()
                kotlin.properties.ReadWriteProperty r9 = r6.f5466h
                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.appsamurai.storyly.storylypresenter.storylyheader.a.f5458o
                r0 = r1[r0]
                r9.setValue(r6, r0, r8)
            L_0x0160:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.q.beforeChange(kotlin.reflect.KProperty, java.lang.Object, java.lang.Object):boolean");
        }
    }

    public static final class r extends Lambda implements Function1<STRCart, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5095a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public r(o oVar) {
            super(1);
            this.f5095a = oVar;
        }

        public Object invoke(Object obj) {
            STRCart sTRCart = (STRCart) obj;
            o oVar = this.f5095a;
            Integer e3 = oVar.getStorylyCurrentIndex();
            com.appsamurai.storyly.data.managers.product.a storylyCart$storyly_release = this.f5095a.getStorylyCart$storyly_release();
            oVar.a(e3, storylyCart$storyly_release == null ? null : storylyCart$storyly_release.a());
            return Unit.INSTANCE;
        }
    }

    public static final class s extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.cart.b> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5096a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public s(o oVar) {
            super(0);
            this.f5096a = oVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = this.f5096a.f5050e.f4372b;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stCartViewHolder");
            return new com.appsamurai.storyly.storylypresenter.cart.b(frameLayout, this.f5096a.f5047b);
        }
    }

    public static final class t extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.storylycenter.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5097a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public t(o oVar) {
            super(0);
            this.f5097a = oVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = this.f5097a.f5050e.f4373c;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stCenterViewHolder");
            return new com.appsamurai.storyly.storylypresenter.storylycenter.a(frameLayout);
        }
    }

    public static final class u extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.storylyfooter.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5098a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public u(o oVar) {
            super(0);
            this.f5098a = oVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = this.f5098a.f5050e.f4374d;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stFooterViewHolder");
            com.appsamurai.storyly.storylypresenter.storylyfooter.a aVar = new com.appsamurai.storyly.storylypresenter.storylyfooter.a(frameLayout, this.f5098a.f5047b);
            o oVar = this.f5098a;
            e0 e0Var = new e0(oVar);
            Intrinsics.checkNotNullParameter(e0Var, "<set-?>");
            aVar.f5351h = e0Var;
            f0 f0Var = new f0(oVar);
            Intrinsics.checkNotNullParameter(f0Var, "<set-?>");
            aVar.f5352i = f0Var;
            g0 g0Var = new g0(oVar);
            Intrinsics.checkNotNullParameter(g0Var, "<set-?>");
            aVar.f5353j = g0Var;
            h0 h0Var = new h0(oVar);
            Intrinsics.checkNotNullParameter(h0Var, "<set-?>");
            aVar.f5354k = h0Var;
            i0 i0Var = new i0(oVar);
            Intrinsics.checkNotNullParameter(i0Var, "<set-?>");
            aVar.f5355l = i0Var;
            j0 j0Var = new j0(oVar);
            Intrinsics.checkNotNullParameter(j0Var, "<set-?>");
            aVar.f5356m = j0Var;
            return aVar;
        }
    }

    public static final class v extends Lambda implements Function1<z, Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public static final v f5099a = new v();

        public v() {
            super(1);
        }

        public Object invoke(Object obj) {
            z zVar = (z) obj;
            Intrinsics.checkNotNullParameter(zVar, "it");
            return Boolean.valueOf(!zVar.f4318q);
        }
    }

    public static final class w extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.storylyheader.a> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5100a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Context f5101b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public w(o oVar, Context context) {
            super(0);
            this.f5100a = oVar;
            this.f5101b = context;
        }

        public Object invoke() {
            FrameLayout frameLayout = this.f5100a.f5050e.f4376f;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stHeaderViewHolder");
            com.appsamurai.storyly.storylypresenter.storylyheader.a aVar = new com.appsamurai.storyly.storylypresenter.storylyheader.a(frameLayout, this.f5100a.f5047b);
            o oVar = this.f5100a;
            Context context = this.f5101b;
            k0 k0Var = new k0(oVar);
            Intrinsics.checkNotNullParameter(k0Var, "<set-?>");
            aVar.f5472n = k0Var;
            l0 l0Var = new l0(oVar);
            Intrinsics.checkNotNullParameter(l0Var, "<set-?>");
            aVar.f5467i = l0Var;
            m0 m0Var = new m0(oVar);
            Intrinsics.checkNotNullParameter(m0Var, "<set-?>");
            aVar.f5468j = m0Var;
            n0 n0Var = new n0(oVar, context);
            Intrinsics.checkNotNullParameter(n0Var, "<set-?>");
            aVar.f5469k = n0Var;
            p0 p0Var = new p0(oVar, context);
            Intrinsics.checkNotNullParameter(p0Var, "<set-?>");
            aVar.f5470l = p0Var;
            q0 q0Var = new q0(oVar);
            Intrinsics.checkNotNullParameter(q0Var, "<set-?>");
            aVar.f5471m = q0Var;
            return aVar;
        }
    }

    public static final class x extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.storylylayer.x> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f5102a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ o f5103b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public x(Context context, o oVar) {
            super(0);
            this.f5102a = context;
            this.f5103b = oVar;
        }

        public Object invoke() {
            Context context = this.f5102a;
            FrameLayout frameLayout = this.f5103b.f5050e.f4382l;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stStorylyLayerView");
            o oVar = this.f5103b;
            com.appsamurai.storyly.storylypresenter.storylylayer.x xVar = new com.appsamurai.storyly.storylypresenter.storylylayer.x(context, frameLayout, oVar.f5047b, oVar.f5046a);
            o oVar2 = this.f5103b;
            v0 v0Var = new v0(oVar2);
            Intrinsics.checkNotNullParameter(v0Var, "<set-?>");
            xVar.f6208g = v0Var;
            w0 w0Var = new w0(oVar2);
            Intrinsics.checkNotNullParameter(w0Var, "<set-?>");
            xVar.f6207f = w0Var;
            x0 x0Var = new x0(oVar2);
            Intrinsics.checkNotNullParameter(x0Var, "<set-?>");
            xVar.f6209h = x0Var;
            y0 y0Var = new y0(oVar2);
            Intrinsics.checkNotNullParameter(y0Var, "<set-?>");
            xVar.f6210i = y0Var;
            z0 z0Var = new z0(oVar2);
            Intrinsics.checkNotNullParameter(z0Var, "<set-?>");
            xVar.f6217p = z0Var;
            a1 a1Var = new a1(oVar2);
            Intrinsics.checkNotNullParameter(a1Var, "<set-?>");
            xVar.f6211j = a1Var;
            b1 b1Var = new b1(oVar2);
            Intrinsics.checkNotNullParameter(b1Var, "<set-?>");
            xVar.f6212k = b1Var;
            c1 c1Var = new c1(oVar2);
            Intrinsics.checkNotNullParameter(c1Var, "<set-?>");
            xVar.f6213l = c1Var;
            d1 d1Var = new d1(oVar2);
            Intrinsics.checkNotNullParameter(d1Var, "<set-?>");
            xVar.f6214m = d1Var;
            Intrinsics.checkNotNullParameter(new r0(oVar2), "<set-?>");
            s0 s0Var = new s0(oVar2);
            Intrinsics.checkNotNullParameter(s0Var, "<set-?>");
            xVar.f6215n = s0Var;
            Intrinsics.checkNotNullParameter(new t0(oVar2), "<set-?>");
            u0 u0Var = new u0(oVar2);
            Intrinsics.checkNotNullParameter(u0Var, "<set-?>");
            xVar.f6216o = u0Var;
            return xVar;
        }
    }

    public static final class y extends Lambda implements Function0<com.appsamurai.storyly.storylypresenter.storylyfooter.d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ o f5104a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public y(o oVar) {
            super(0);
            this.f5104a = oVar;
        }

        public Object invoke() {
            FrameLayout frameLayout = this.f5104a.f5050e.f4378h;
            Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stMomentsReportView");
            FrameLayout frameLayout2 = this.f5104a.f5050e.f4382l;
            Intrinsics.checkNotNullExpressionValue(frameLayout2, "binding.stStorylyLayerView");
            com.appsamurai.storyly.storylypresenter.storylyfooter.d dVar = new com.appsamurai.storyly.storylypresenter.storylyfooter.d(frameLayout, frameLayout2);
            o oVar = this.f5104a;
            e1 e1Var = new e1(oVar);
            Intrinsics.checkNotNullParameter(e1Var, "<set-?>");
            dVar.f5432c = e1Var;
            Intrinsics.checkNotNullParameter(new f1(oVar), "<set-?>");
            a b3 = oVar.getActionManager();
            Intrinsics.checkNotNullParameter(b3, "<set-?>");
            dVar.f5435f = b3;
            h1 h1Var = new h1(oVar, dVar);
            Intrinsics.checkNotNullParameter(h1Var, "<set-?>");
            dVar.f5433d = h1Var;
            i1 i1Var = new i1(oVar);
            Intrinsics.checkNotNullParameter(i1Var, "<set-?>");
            dVar.f5434e = i1Var;
            return dVar;
        }
    }

    static {
        Class<o> cls = o.class;
        f5037L = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "showMomentsUserAnalytics", "getShowMomentsUserAnalytics$storyly_release()Z", 0), androidx.compose.ui.autofill.a.m(cls, "storylyGroupItem", "getStorylyGroupItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyGroupItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex()Ljava/lang/Integer;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem()Lcom/appsamurai/storyly/data/StorylyItem;", 0)};
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public o(@NotNull Context context, @NotNull com.appsamurai.storyly.analytics.e eVar, @NotNull StorylyConfig storylyConfig, @NotNull com.appsamurai.storyly.data.managers.cache.c cVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(cVar, "storylyImageCacheManager");
        this.f5046a = eVar;
        this.f5047b = storylyConfig;
        this.f5048c = cVar;
        Delegates delegates = Delegates.INSTANCE;
        Boolean bool = Boolean.TRUE;
        this.f5049d = new n(bool, bool, this);
        com.appsamurai.storyly.databinding.j a2 = com.appsamurai.storyly.databinding.j.a(LayoutInflater.from(context));
        Intrinsics.checkNotNullExpressionValue(a2, "inflate(LayoutInflater.from(context))");
        this.f5050e = a2;
        this.f5058m = LazyKt.lazy(new d(context));
        this.f5059n = new p((Object) null, (Object) null, this);
        this.f5039B = LazyKt.lazy(new w(this, context));
        this.f5040C = LazyKt.lazy(new u(this));
        this.f5041D = LazyKt.lazy(new y(this));
        this.f5042E = LazyKt.lazy(new s(this));
        this.F = LazyKt.lazy(new t(this));
        this.G = LazyKt.lazy(new x(context, this));
        this.f5043H = LazyKt.lazy(new b(this));
        this.f5045J = true;
        this.K = LazyKt.lazy(c.f5079a);
        addView(a2.a());
        setImportantForAccessibility(2);
        setContentDescription("");
        a2.a().setOnTouchListener(new E0.e(this, 1));
        RelativeLayout relativeLayout = a2.f4381k;
        relativeLayout.setVisibility(com.appsamurai.storyly.util.g.b(context) ? 0 : 8);
        a2.f4379i.setOnClickListener(new W.g(relativeLayout, this, 0));
        a2.f4380j.setOnClickListener(new W.g(relativeLayout, this, 1));
        ImageButton imageButton = a2.f4379i;
        Resources resources = relativeLayout.getResources();
        Intrinsics.checkNotNullExpressionValue(relativeLayout, "");
        imageButton.setContentDescription(resources.getString(com.appsamurai.storyly.util.l.a(relativeLayout) ? R.string.st_desc_story_previous : R.string.st_desc_story_next));
        a2.f4379i.setImportantForAccessibility(1);
        a2.f4380j.setContentDescription(relativeLayout.getResources().getString(com.appsamurai.storyly.util.l.a(relativeLayout) ? R.string.st_desc_story_next : R.string.st_desc_story_previous));
        a2.f4380j.setImportantForAccessibility(1);
        FrameLayout frameLayout = a2.f4377g;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "binding.stLoadingLayout");
        this.f5038A = new com.appsamurai.storyly.util.ui.d(frameLayout, context);
        a2.f4372b.setOnClickListener(new E0.c(this, 7));
    }

    public static final void a(o oVar, Long l2) {
        z storylyItem;
        oVar.getClass();
        if (l2 != null && (storylyItem = oVar.getStorylyItem()) != null) {
            storylyItem.f4319r = l2.longValue();
        }
    }

    /* access modifiers changed from: private */
    public final a getActionManager() {
        return (a) this.f5043H.getValue();
    }

    private final Handler getImpressionHandler() {
        return (Handler) this.K.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.data.managers.storage.c getReportSharedPreferencesManager() {
        return (com.appsamurai.storyly.data.managers.storage.c) this.f5058m.getValue();
    }

    private final com.appsamurai.storyly.storylypresenter.cart.b getStorylyCartView() {
        return (com.appsamurai.storyly.storylypresenter.cart.b) this.f5042E.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.storylycenter.a getStorylyCenterView() {
        return (com.appsamurai.storyly.storylypresenter.storylycenter.a) this.F.getValue();
    }

    /* access modifiers changed from: private */
    public final Integer getStorylyCurrentIndex() {
        return (Integer) this.f5057l.getValue(this, f5037L[2]);
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.storylyfooter.a getStorylyFooterView() {
        return (com.appsamurai.storyly.storylypresenter.storylyfooter.a) this.f5040C.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.storylyheader.a getStorylyHeaderView() {
        return (com.appsamurai.storyly.storylypresenter.storylyheader.a) this.f5039B.getValue();
    }

    /* access modifiers changed from: private */
    public final z getStorylyItem() {
        return (z) this.f5059n.getValue(this, f5037L[3]);
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.storylylayer.x getStorylyLayerContainerView() {
        return (com.appsamurai.storyly.storylypresenter.storylylayer.x) this.G.getValue();
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.storylypresenter.storylyfooter.d getStorylyReportView() {
        return (com.appsamurai.storyly.storylypresenter.storylyfooter.d) this.f5041D.getValue();
    }

    public static final void k(o oVar) {
        a.C0032a aVar = oVar.getStorylyFooterView().f5346c;
        if (aVar != null) {
            if (aVar.f5358b == a.d.NotHiding) {
                aVar.b();
            } else {
                aVar.h();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void n(o oVar) {
        a.c cVar = oVar.getStorylyHeaderView().f5461c;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
            cVar = null;
        }
        cVar.getClass();
        a.C0032a aVar = oVar.getStorylyFooterView().f5346c;
        if (aVar != null) {
            aVar.g();
            Unit unit = Unit.INSTANCE;
        }
    }

    public static final void o(o oVar) {
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        oVar.m();
    }

    public static final void p(o oVar) {
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        oVar.j();
        oVar.getOnPullDown$storyly_release().invoke(Boolean.FALSE);
    }

    public static final void q(o oVar) {
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        com.appsamurai.storyly.analytics.e.a(oVar.f5046a, com.appsamurai.storyly.analytics.a.Impression, oVar.getStorylyGroupItem$storyly_release(), oVar.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
    }

    /* access modifiers changed from: private */
    public final void setStorylyCurrentIndex(Integer num) {
        this.f5057l.setValue(this, f5037L[2], num);
    }

    /* access modifiers changed from: private */
    public final void setStorylyItem(z zVar) {
        this.f5059n.setValue(this, f5037L[3], zVar);
    }

    @NotNull
    public final Function0<Unit> getOnClosed$storyly_release() {
        Function0<Unit> function0 = this.f5060o;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onClosed");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnCompleted$storyly_release() {
        Function0<Unit> function0 = this.f5061p;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onCompleted");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnDismissed$storyly_release() {
        Function0<Unit> function0 = this.f5066u;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onDismissed");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnPrevious$storyly_release() {
        Function0<Unit> function0 = this.f5062q;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onPrevious");
        return null;
    }

    @NotNull
    public final Function1<Boolean, Unit> getOnPullDown$storyly_release() {
        Function1<? super Boolean, Unit> function1 = this.f5068w;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onPullDown");
        return null;
    }

    @NotNull
    public final Function1<z, Boolean> getOnStoryConditionCheck$storyly_release() {
        Function1<? super z, Boolean> function1 = this.f5071z;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStoryConditionCheck");
        return null;
    }

    @NotNull
    public final Function3<StoryGroup, Story, StoryComponent, Unit> getOnStoryLayerInteraction$storyly_release() {
        Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function3 = this.f5069x;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStoryLayerInteraction");
        return null;
    }

    @NotNull
    public final Function1<Story, Unit> getOnStorylyActionClicked$storyly_release() {
        Function1<? super Story, Unit> function1 = this.f5063r;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStorylyActionClicked");
        return null;
    }

    @NotNull
    public final Function2<StoryGroup, Story, Unit> getOnStorylyHeaderClicked$storyly_release() {
        Function2<? super StoryGroup, ? super Story, Unit> function2 = this.f5070y;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStorylyHeaderClicked");
        return null;
    }

    @NotNull
    public final Function1<Float, Unit> getOnSwipeDown$storyly_release() {
        Function1<? super Float, Unit> function1 = this.f5067v;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onSwipeDown");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnSwipeHorizontal$storyly_release() {
        Function0<Unit> function0 = this.f5064s;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onSwipeHorizontal");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnTouchUp$storyly_release() {
        Function0<Unit> function0 = this.f5065t;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTouchUp");
        return null;
    }

    public final boolean getShowMomentsUserAnalytics$storyly_release() {
        return ((Boolean) this.f5049d.getValue(this, f5037L[0])).booleanValue();
    }

    @Nullable
    public final com.appsamurai.storyly.data.managers.product.a getStorylyCart$storyly_release() {
        return this.f5056k;
    }

    @Nullable
    public final com.appsamurai.storyly.data.v getStorylyGroupItem$storyly_release() {
        return (com.appsamurai.storyly.data.v) this.f5054i.getValue(this, f5037L[1]);
    }

    @Nullable
    public final List<com.appsamurai.storyly.data.v> getStorylyGroupItems$storyly_release() {
        return this.f5053h;
    }

    @Nullable
    public final com.appsamurai.storyly.data.v getTempStorylyGroupItem$storyly_release() {
        return this.f5055j;
    }

    public final void l() {
        StoryType storyType;
        Long l2;
        StoryGroupType storyGroupType;
        boolean z2 = true;
        if (this.f5051f != a.Loaded) {
            this.f5044I = true;
            return;
        }
        com.appsamurai.storyly.analytics.e.a(this.f5046a, com.appsamurai.storyly.analytics.a.Viewed, getStorylyGroupItem$storyly_release(), getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        this.f5044I = true;
        Handler impressionHandler = getImpressionHandler();
        W.f fVar = new W.f(this, 1);
        z storylyItem = getStorylyItem();
        a.c cVar = null;
        if (storylyItem == null) {
            storyType = null;
        } else {
            storyType = storylyItem.f4307f;
        }
        impressionHandler.postDelayed(fVar, storyType == StoryType.Video ? ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS : 1000);
        z storylyItem2 = getStorylyItem();
        if (storylyItem2 != null) {
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
            if (storylyGroupItem$storyly_release == null) {
                storyGroupType = null;
            } else {
                storyGroupType = storylyGroupItem$storyly_release.f4228h;
            }
            if (storyGroupType == StoryGroupType.Live) {
                z2 = false;
            }
            storylyItem2.f4320s = z2;
        }
        com.appsamurai.storyly.storylypresenter.storylyheader.a storylyHeaderView = getStorylyHeaderView();
        z storylyItem3 = getStorylyItem();
        if (storylyItem3 == null) {
            l2 = null;
        } else {
            l2 = Long.valueOf(storylyItem3.f4304c);
        }
        a.c cVar2 = storylyHeaderView.f5461c;
        if (cVar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
        } else {
            cVar = cVar2;
        }
        cVar.a(l2);
        x.a aVar = getStorylyLayerContainerView().f6222u;
        if (aVar != null) {
            aVar.b(j1.f5838a);
        }
        a.C0032a aVar2 = getStorylyFooterView().f5346c;
        if (aVar2 != null) {
            aVar2.h();
            Unit unit = Unit.INSTANCE;
        }
        this.f5051f = a.Started;
    }

    public final void m() {
        this.f5044I = false;
        i();
    }

    public final void setOnClosed$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5060o = function0;
    }

    public final void setOnCompleted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5061p = function0;
    }

    public final void setOnDismissed$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5066u = function0;
    }

    public final void setOnPrevious$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5062q = function0;
    }

    public final void setOnPullDown$storyly_release(@NotNull Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5068w = function1;
    }

    public final void setOnStoryConditionCheck$storyly_release(@NotNull Function1<? super z, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5071z = function1;
    }

    public final void setOnStoryLayerInteraction$storyly_release(@NotNull Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.f5069x = function3;
    }

    public final void setOnStorylyActionClicked$storyly_release(@NotNull Function1<? super Story, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5063r = function1;
    }

    public final void setOnStorylyHeaderClicked$storyly_release(@NotNull Function2<? super StoryGroup, ? super Story, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.f5070y = function2;
    }

    public final void setOnSwipeDown$storyly_release(@NotNull Function1<? super Float, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f5067v = function1;
    }

    public final void setOnSwipeHorizontal$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5064s = function0;
    }

    public final void setOnTouchUp$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f5065t = function0;
    }

    public final void setShowMomentsUserAnalytics$storyly_release(boolean z2) {
        this.f5049d.setValue(this, f5037L[0], Boolean.valueOf(z2));
    }

    public final void setStorylyCart$storyly_release(@Nullable com.appsamurai.storyly.data.managers.product.a aVar) {
        this.f5056k = aVar;
        Integer storylyCurrentIndex = getStorylyCurrentIndex();
        com.appsamurai.storyly.data.managers.product.a aVar2 = this.f5056k;
        a(storylyCurrentIndex, aVar2 == null ? null : aVar2.a());
        if (aVar != null) {
            aVar.f4040c = new r(this);
        }
    }

    public final void setStorylyGroupItem$storyly_release(@Nullable com.appsamurai.storyly.data.v vVar) {
        this.f5054i.setValue(this, f5037L[1], vVar);
    }

    public final void setStorylyGroupItems$storyly_release(@Nullable List<com.appsamurai.storyly.data.v> list) {
        this.f5053h = list;
    }

    public final void setTempStorylyGroupItem$storyly_release(@Nullable com.appsamurai.storyly.data.v vVar) {
        this.f5055j = vVar;
    }

    public static final void b(RelativeLayout relativeLayout, o oVar, View view) {
        Intrinsics.checkNotNullParameter(relativeLayout, "$this_apply");
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        if (com.appsamurai.storyly.util.l.a(relativeLayout)) {
            oVar.a(true);
        } else {
            oVar.f();
        }
    }

    public final void c() {
        StoryGroupType storyGroupType;
        RelativeLayout.LayoutParams layoutParams;
        if (this.f5051f == a.Initiated) {
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
            StoryGroupType storyGroupType2 = null;
            if (storylyGroupItem$storyly_release == null) {
                storyGroupType = null;
            } else {
                storyGroupType = storylyGroupItem$storyly_release.f4228h;
            }
            StoryGroupType storyGroupType3 = StoryGroupType.MomentsDefault;
            if (storyGroupType == storyGroupType3) {
                layoutParams = new RelativeLayout.LayoutParams(com.appsamurai.storyly.util.m.c().width(), com.appsamurai.storyly.util.m.c().height());
            } else {
                layoutParams = new RelativeLayout.LayoutParams(com.appsamurai.storyly.util.m.c().width(), -1);
            }
            layoutParams.addRule(14);
            this.f5050e.f4382l.setLayoutParams(layoutParams);
            this.f5051f = a.Buffering;
            this.f5048c.a(true);
            z storylyItem = getStorylyItem();
            if (storylyItem != null) {
                com.appsamurai.storyly.storylypresenter.storylylayer.x storylyLayerContainerView = getStorylyLayerContainerView();
                storylyLayerContainerView.getClass();
                Intrinsics.checkNotNullParameter(storylyItem, "storylyItem");
                storylyLayerContainerView.f6225x = storylyItem;
                storylyLayerContainerView.f6222u = new x.a(storylyLayerContainerView);
                String str = storylyItem.f4303b.f3640b;
                storylyLayerContainerView.f6203b.setVisibility(4);
                Function0<Unit> function0 = storylyLayerContainerView.f6216o;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onLayerLoadBegin");
                    function0 = null;
                }
                function0.invoke();
                FrameLayout frameLayout = storylyLayerContainerView.f6203b;
                Intrinsics.checkExpressionValueIsNotNull(OneShotPreDrawListener.add(frameLayout, new com.appsamurai.storyly.storylypresenter.storylylayer.b0(frameLayout, storylyLayerContainerView, storylyItem, str)), "OneShotPreDrawListener.add(this) { action(this) }");
            }
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release2 = getStorylyGroupItem$storyly_release();
            if (storylyGroupItem$storyly_release2 != null) {
                storyGroupType2 = storylyGroupItem$storyly_release2.f4228h;
            }
            if (storyGroupType2 == storyGroupType3) {
                getStorylyReportView().f5437h = getStorylyGroupItem$storyly_release();
                com.appsamurai.storyly.storylypresenter.storylyfooter.d storylyReportView = getStorylyReportView();
                storylyReportView.f5438i.setValue(storylyReportView, com.appsamurai.storyly.storylypresenter.storylyfooter.d.f5429r[0], getStorylyItem());
            }
        }
    }

    public final void d() {
        Integer num;
        List<z> list;
        Integer storylyCurrentIndex = getStorylyCurrentIndex();
        Integer num2 = null;
        Integer valueOf = storylyCurrentIndex == null ? null : Integer.valueOf(storylyCurrentIndex.intValue() + 1);
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
        if (storylyGroupItem$storyly_release == null || (list = storylyGroupItem$storyly_release.f4226f) == null) {
            num = null;
        } else {
            num = Integer.valueOf(list.size());
        }
        if (Intrinsics.areEqual((Object) valueOf, (Object) num)) {
            getOnCompleted$storyly_release().invoke();
            return;
        }
        i();
        Integer storylyCurrentIndex2 = getStorylyCurrentIndex();
        if (storylyCurrentIndex2 != null) {
            num2 = Integer.valueOf(storylyCurrentIndex2.intValue() + 1);
        }
        setStorylyCurrentIndex(num2);
        c();
    }

    public final void e() {
        com.appsamurai.storyly.analytics.e eVar = this.f5046a;
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.Dismissed;
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
        z storylyItem = getStorylyItem();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "back_button_pressed", Boolean.TRUE);
        Unit unit = Unit.INSTANCE;
        com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, storylyItem, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
        new Handler(Looper.getMainLooper()).postDelayed(new W.f(this, 2), 100);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f() {
        /*
            r20 = this;
            r0 = r20
            java.util.List<com.appsamurai.storyly.data.v> r1 = r0.f5053h
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L_0x000b
            r1 = r4
            goto L_0x0028
        L_0x000b:
            com.appsamurai.storyly.data.v r5 = r20.getStorylyGroupItem$storyly_release()
            int r1 = kotlin.collections.CollectionsKt.indexOf(r1, r5)
            java.lang.Integer r5 = r20.getStorylyCurrentIndex()
            if (r5 != 0) goto L_0x001a
            goto L_0x0022
        L_0x001a:
            int r5 = r5.intValue()
            if (r5 != 0) goto L_0x0022
            r5 = r3
            goto L_0x0023
        L_0x0022:
            r5 = r2
        L_0x0023:
            int r1 = r1 - r5
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
        L_0x0028:
            if (r1 != 0) goto L_0x002b
            goto L_0x0035
        L_0x002b:
            int r1 = r1.intValue()
            java.util.List r5 = r20.getStorylyGroupItems$storyly_release()
            if (r5 != 0) goto L_0x0037
        L_0x0035:
            r1 = r4
            goto L_0x0041
        L_0x0037:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = com.appsamurai.storyly.util.e.a(r5, r1)
            com.appsamurai.storyly.data.v r1 = (com.appsamurai.storyly.data.v) r1
        L_0x0041:
            java.lang.Integer r5 = r20.getStorylyCurrentIndex()
            if (r5 != 0) goto L_0x0048
            goto L_0x005a
        L_0x0048:
            int r5 = r5.intValue()
            if (r5 != 0) goto L_0x005a
            if (r1 != 0) goto L_0x0051
            goto L_0x0060
        L_0x0051:
            int r5 = r1.b()
        L_0x0055:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L_0x0068
        L_0x005a:
            java.lang.Integer r5 = r20.getStorylyCurrentIndex()
            if (r5 != 0) goto L_0x0062
        L_0x0060:
            r5 = r4
            goto L_0x0068
        L_0x0062:
            int r5 = r5.intValue()
            int r5 = r5 - r3
            goto L_0x0055
        L_0x0068:
            if (r5 != 0) goto L_0x006b
            goto L_0x0076
        L_0x006b:
            int r5 = r5.intValue()
            if (r1 != 0) goto L_0x0072
            goto L_0x0076
        L_0x0072:
            java.util.List<com.appsamurai.storyly.data.z> r6 = r1.f4226f
            if (r6 != 0) goto L_0x0078
        L_0x0076:
            r5 = r4
            goto L_0x0082
        L_0x0078:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r5 = com.appsamurai.storyly.util.e.a(r6, r5)
            com.appsamurai.storyly.data.z r5 = (com.appsamurai.storyly.data.z) r5
        L_0x0082:
            com.appsamurai.storyly.analytics.e r6 = r0.f5046a
            if (r1 != 0) goto L_0x0088
            r7 = r4
            goto L_0x008a
        L_0x0088:
            com.appsamurai.storyly.StoryGroupType r7 = r1.f4228h
        L_0x008a:
            kotlinx.serialization.json.JsonPrimitive r6 = r6.a((com.appsamurai.storyly.StoryGroupType) r7, (com.appsamurai.storyly.data.v) r1)
            com.appsamurai.storyly.analytics.e r7 = r0.f5046a
            if (r1 != 0) goto L_0x0094
            r8 = r4
            goto L_0x0096
        L_0x0094:
            com.appsamurai.storyly.StoryGroupType r8 = r1.f4228h
        L_0x0096:
            kotlinx.serialization.json.JsonPrimitive r5 = r7.a((com.appsamurai.storyly.StoryGroupType) r8, (com.appsamurai.storyly.data.z) r5)
            com.appsamurai.storyly.data.v r7 = r20.getStorylyGroupItem$storyly_release()
            if (r7 != 0) goto L_0x00a2
            r7 = r4
            goto L_0x00a4
        L_0x00a2:
            com.appsamurai.storyly.StoryGroupType r7 = r7.f4228h
        L_0x00a4:
            com.appsamurai.storyly.StoryGroupType r8 = com.appsamurai.storyly.StoryGroupType.MomentsDefault
            if (r7 == r8) goto L_0x00b2
            if (r1 != 0) goto L_0x00ac
            r1 = r4
            goto L_0x00ae
        L_0x00ac:
            com.appsamurai.storyly.StoryGroupType r1 = r1.f4228h
        L_0x00ae:
            if (r1 != r8) goto L_0x00b2
            r5 = r4
            goto L_0x00b3
        L_0x00b2:
            r4 = r6
        L_0x00b3:
            com.appsamurai.storyly.analytics.e r6 = r0.f5046a
            com.appsamurai.storyly.analytics.a r7 = com.appsamurai.storyly.analytics.a.PreviousClicked
            com.appsamurai.storyly.data.v r8 = r20.getStorylyGroupItem$storyly_release()
            com.appsamurai.storyly.data.z r9 = r20.getStorylyItem()
            kotlinx.serialization.json.JsonObjectBuilder r1 = new kotlinx.serialization.json.JsonObjectBuilder
            r1.<init>()
            if (r4 != 0) goto L_0x00c8
            kotlinx.serialization.json.JsonNull r4 = kotlinx.serialization.json.JsonNull.INSTANCE
        L_0x00c8:
            java.lang.String r10 = "target_story_group_id"
            r1.put(r10, r4)
            if (r5 != 0) goto L_0x00d2
            kotlinx.serialization.json.JsonNull r5 = kotlinx.serialization.json.JsonNull.INSTANCE
        L_0x00d2:
            java.lang.String r4 = "target_story_id"
            r1.put(r4, r5)
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r12 = r1.build()
            r18 = 0
            r19 = 4056(0xfd8, float:5.684E-42)
            r10 = 0
            r11 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            com.appsamurai.storyly.analytics.e.a(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            java.lang.Integer r1 = r20.getStorylyCurrentIndex()
            if (r1 != 0) goto L_0x00f5
            goto L_0x0103
        L_0x00f5:
            int r1 = r1.intValue()
            if (r1 != 0) goto L_0x0103
            kotlin.jvm.functions.Function0 r0 = r20.getOnPrevious$storyly_release()
            r0.invoke()
            goto L_0x0120
        L_0x0103:
            r20.i()
            java.lang.Integer r1 = r20.getStorylyCurrentIndex()
            if (r1 != 0) goto L_0x010d
            goto L_0x011d
        L_0x010d:
            int r1 = r1.intValue()
            int r1 = r1 - r3
            int r1 = java.lang.Math.max(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.setStorylyCurrentIndex(r1)
        L_0x011d:
            r20.c()
        L_0x0120:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.f():void");
    }

    public final void g() {
        animate().translationY(0.0f).withEndAction(new W.f(this, 0));
    }

    public final void h() {
        if (this.f5051f == a.Started) {
            x.a aVar = getStorylyLayerContainerView().f6222u;
            if (aVar != null) {
                aVar.b(i1.f5835a);
            }
            a.c cVar = getStorylyHeaderView().f5461c;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.k();
            a.C0032a aVar2 = getStorylyFooterView().f5346c;
            if (aVar2 != null) {
                aVar2.c();
                Unit unit = Unit.INSTANCE;
            }
            com.appsamurai.storyly.analytics.e.a(this.f5046a, com.appsamurai.storyly.analytics.a.Paused, getStorylyGroupItem$storyly_release(), getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            this.f5051f = a.Paused;
        }
    }

    public final void i() {
        getImpressionHandler().removeCallbacksAndMessages((Object) null);
        this.f5050e.f4371a.setBackgroundResource(0);
        a.c cVar = getStorylyHeaderView().f5461c;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
            cVar = null;
        }
        cVar.l();
        getStorylyLayerContainerView().g();
        a.C0032a aVar = getStorylyFooterView().f5346c;
        if (aVar != null) {
            aVar.d();
            Unit unit = Unit.INSTANCE;
        }
        getStorylyReportView().h();
        com.appsamurai.storyly.storylypresenter.cart.b storylyCartView = getStorylyCartView();
        storylyCartView.f4778c.setValue(storylyCartView, com.appsamurai.storyly.storylypresenter.cart.b.f4775i[0], null);
        storylyCartView.f4776a.setVisibility(4);
        AppCompatTextView c3 = storylyCartView.c();
        c3.clearAnimation();
        c3.setText(String.valueOf(storylyCartView.d()));
        AppCompatTextView b3 = storylyCartView.b();
        b3.clearAnimation();
        b3.setText(String.valueOf(storylyCartView.d()));
        this.f5051f = a.Initiated;
    }

    public final void j() {
        if (this.f5051f == a.Paused) {
            com.appsamurai.storyly.analytics.e.a(this.f5046a, com.appsamurai.storyly.analytics.a.Resumed, getStorylyGroupItem$storyly_release(), getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            x.a aVar = getStorylyLayerContainerView().f6222u;
            if (aVar != null) {
                aVar.b(j1.f5838a);
            }
            a.c cVar = getStorylyHeaderView().f5461c;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.m();
            a.C0032a aVar2 = getStorylyFooterView().f5346c;
            if (aVar2 != null) {
                aVar2.e();
                Unit unit = Unit.INSTANCE;
            }
            this.f5051f = a.Started;
        }
    }

    public static final void m(o oVar) {
        a.c cVar = oVar.getStorylyHeaderView().f5461c;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
            cVar = null;
        }
        cVar.getClass();
        a.C0032a aVar = oVar.getStorylyFooterView().f5346c;
        if (aVar != null) {
            aVar.f();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void b() {
        getActionManager().e();
        this.f5045J = true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.storylypresenter.o r6, kotlin.Pair r7) {
        /*
            r6.getClass()
            java.lang.Object r7 = r7.getSecond()
            java.lang.Number r7 = (java.lang.Number) r7
            float r7 = r7.floatValue()
            double r0 = (double) r7
            com.appsamurai.storyly.databinding.j r7 = r6.f5050e
            android.widget.RelativeLayout r7 = r7.f4375e
            int r7 = r7.getMeasuredHeight()
            double r2 = (double) r7
            r4 = 4600877379321698714(0x3fd999999999999a, double:0.4)
            double r2 = r2 * r4
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x00d0
            com.appsamurai.storyly.storylypresenter.storylylayer.x r7 = r6.getStorylyLayerContainerView()
            com.appsamurai.storyly.storylypresenter.storylylayer.c r7 = r7.a()
            java.util.List<com.appsamurai.storyly.data.b0> r7 = r7.f5659c
            java.util.Iterator r7 = r7.iterator()
        L_0x002f:
            boolean r0 = r7.hasNext()
            r1 = 0
            if (r0 == 0) goto L_0x0044
            java.lang.Object r0 = r7.next()
            r2 = r0
            com.appsamurai.storyly.data.b0 r2 = (com.appsamurai.storyly.data.b0) r2
            com.appsamurai.storyly.data.a0 r2 = r2.f3615j
            boolean r2 = r2 instanceof com.appsamurai.storyly.data.n0
            if (r2 == 0) goto L_0x002f
            goto L_0x0045
        L_0x0044:
            r0 = r1
        L_0x0045:
            com.appsamurai.storyly.data.b0 r0 = (com.appsamurai.storyly.data.b0) r0
            if (r0 != 0) goto L_0x004b
            goto L_0x00d0
        L_0x004b:
            com.appsamurai.storyly.data.a0 r7 = r0.f3615j
            boolean r2 = r7 instanceof com.appsamurai.storyly.data.g0
            if (r2 == 0) goto L_0x00be
            com.appsamurai.storyly.data.g0 r7 = (com.appsamurai.storyly.data.g0) r7
            com.appsamurai.storyly.data.m r7 = r7.c()
            if (r7 != 0) goto L_0x005b
            goto L_0x00be
        L_0x005b:
            java.util.Map<com.appsamurai.storyly.data.j, java.util.List<com.appsamurai.storyly.data.managers.product.STRProductItem>> r7 = r7.f3859a
            if (r7 != 0) goto L_0x0060
            goto L_0x00be
        L_0x0060:
            java.util.Collection r7 = r7.values()
            if (r7 != 0) goto L_0x0067
            goto L_0x00be
        L_0x0067:
            boolean r2 = r7.isEmpty()
            if (r2 == 0) goto L_0x006e
            goto L_0x0085
        L_0x006e:
            java.util.Iterator r7 = r7.iterator()
        L_0x0072:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x0085
            java.lang.Object r2 = r7.next()
            java.util.List r2 = (java.util.List) r2
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0072
            goto L_0x00be
        L_0x0085:
            com.appsamurai.storyly.data.a0 r7 = r0.f3615j
            com.appsamurai.storyly.data.g0 r7 = (com.appsamurai.storyly.data.g0) r7
            com.appsamurai.storyly.data.m r7 = r7.c()
            if (r7 != 0) goto L_0x0090
            goto L_0x00d0
        L_0x0090:
            com.appsamurai.storyly.data.a0 r0 = r0.f3615j
            com.appsamurai.storyly.data.g0 r0 = (com.appsamurai.storyly.data.g0) r0
            java.util.Map<com.appsamurai.storyly.data.j, java.util.List<com.appsamurai.storyly.data.managers.product.STRProductItem>> r7 = r7.f3859a
            if (r7 != 0) goto L_0x0099
            goto L_0x00ba
        L_0x0099:
            java.util.Collection r7 = r7.values()
            if (r7 != 0) goto L_0x00a0
            goto L_0x00ba
        L_0x00a0:
            java.util.Iterator r7 = r7.iterator()
        L_0x00a4:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x00b8
            java.lang.Object r2 = r7.next()
            r3 = r2
            java.util.List r3 = (java.util.List) r3
            boolean r3 = r3.isEmpty()
            if (r3 != 0) goto L_0x00a4
            r1 = r2
        L_0x00b8:
            java.util.List r1 = (java.util.List) r1
        L_0x00ba:
            r6.a((com.appsamurai.storyly.data.g0) r0, (java.util.List<com.appsamurai.storyly.data.managers.product.STRProductItem>) r1)
            goto L_0x00d0
        L_0x00be:
            com.appsamurai.storyly.data.a0 r7 = r0.f3615j
            boolean r2 = r7 instanceof com.appsamurai.storyly.data.n0
            if (r2 == 0) goto L_0x00c7
            com.appsamurai.storyly.data.n0 r7 = (com.appsamurai.storyly.data.n0) r7
            goto L_0x00c8
        L_0x00c7:
            r7 = r1
        L_0x00c8:
            if (r7 != 0) goto L_0x00cb
            goto L_0x00cd
        L_0x00cb:
            java.lang.String r1 = r7.f4063e
        L_0x00cd:
            r6.a((java.lang.String) r1, (com.appsamurai.storyly.data.b0) r0)
        L_0x00d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.a(com.appsamurai.storyly.storylypresenter.o, kotlin.Pair):void");
    }

    public static final void b(o oVar, long j2) {
        long j3;
        o oVar2 = oVar;
        long j4 = j2;
        z storylyItem = oVar.getStorylyItem();
        if (storylyItem == null) {
            j3 = 0;
        } else {
            j3 = storylyItem.f4317p;
        }
        com.appsamurai.storyly.analytics.e eVar = oVar2.f5046a;
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.Seeked;
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
        z storylyItem2 = oVar.getStorylyItem();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "current_time", (Number) Long.valueOf(j3));
        JsonElementBuildersKt.put(jsonObjectBuilder, "target_time", (Number) Long.valueOf(j2));
        Unit unit = Unit.INSTANCE;
        com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, storylyItem2, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
        a aVar2 = oVar2.f5051f;
        if (aVar2 == a.Paused || aVar2 == a.Started) {
            x.a aVar3 = oVar.getStorylyLayerContainerView().f6222u;
            if (aVar3 != null) {
                aVar3.b(new l1(j4));
            }
            a.c cVar = oVar.getStorylyHeaderView().f5461c;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.b(j4);
        }
    }

    public final void k() {
        z zVar;
        ArrayList arrayList;
        a0 a0Var;
        STRCart sTRCart;
        List<STRCartItem> items;
        STRCart sTRCart2;
        Object obj;
        d0 d0Var;
        List<b0> list;
        a0 a0Var2;
        List<z> list2;
        h();
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
        g0 g0Var = null;
        if (storylyGroupItem$storyly_release == null || (list2 = storylyGroupItem$storyly_release.f4226f) == null) {
            zVar = null;
        } else {
            zVar = (z) com.appsamurai.storyly.util.e.a(list2, getStorylyCurrentIndex());
        }
        if (zVar == null || (d0Var = zVar.f4303b) == null || (list = d0Var.f3639a) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (b0 next : list) {
                if (next == null) {
                    a0Var2 = null;
                } else {
                    a0Var2 = next.f3615j;
                }
                arrayList.add(a0Var2);
            }
        }
        if (arrayList == null) {
            a0Var = null;
        } else {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                a0 a0Var3 = (a0) obj;
                if ((a0Var3 instanceof g0) && ((g0) a0Var3).c() != null) {
                    break;
                }
            }
            a0Var = (a0) obj;
        }
        if (a0Var instanceof g0) {
            g0Var = (g0) a0Var;
        }
        g0 g0Var2 = g0Var;
        com.appsamurai.storyly.data.managers.product.a aVar = this.f5056k;
        if (aVar != null && (sTRCart = aVar.f4039b) != null && (items = sTRCart.getItems()) != null && (!items.isEmpty())) {
            com.appsamurai.storyly.data.managers.product.a aVar2 = this.f5056k;
            if (aVar2 != null && (sTRCart2 = aVar2.f4039b) != null) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                com.appsamurai.storyly.storylypresenter.cart.sheet.d dVar = new com.appsamurai.storyly.storylypresenter.cart.sheet.d(context, this.f5047b, sTRCart2, g0Var2, new g(this));
                dVar.setOnUpdateCart$storyly_release(new e(this));
                dVar.setOnGoToCheckout$storyly_release(new f(this));
                Class cls = Integer.TYPE;
                ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
                Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
                Unit unit = Unit.INSTANCE;
                addView(dVar, newInstance);
                dVar.post(new C0.d(dVar, 6));
            }
        } else if (g0Var2 != null) {
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            new com.appsamurai.storyly.storylypresenter.cart.sheet.j(context2, this.f5047b, g0Var2.e(), new d0(this)).show();
        }
    }

    public static final void l(o oVar) {
        o oVar2 = oVar;
        if (oVar2.f5051f == a.Started) {
            com.appsamurai.storyly.analytics.e.a(oVar2.f5046a, com.appsamurai.storyly.analytics.a.Complete, oVar.getStorylyGroupItem$storyly_release(), oVar.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            oVar.getStorylyLayerContainerView().g();
        }
    }

    public static final void a(com.appsamurai.storyly.storylypresenter.product.b bVar) {
        Intrinsics.checkNotNullParameter(bVar, "$successSheet");
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = bVar.f5116g;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(4);
        }
    }

    public static final boolean a(o oVar, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        if (!oVar.f5045J) {
            return true;
        }
        a actionManager = oVar.getActionManager();
        Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(oVar.f5050e.f4382l.getWidth()), Integer.valueOf(oVar.f5050e.f4382l.getHeight()));
        actionManager.getClass();
        Intrinsics.checkNotNullParameter(pair, "parentArea");
        actionManager.f4720a = pair;
        j1 j1Var = actionManager.f4723d;
        if (j1Var != null) {
            j1Var.a(motionEvent);
        }
        return true;
    }

    public static final void a(RelativeLayout relativeLayout, o oVar, View view) {
        Intrinsics.checkNotNullParameter(relativeLayout, "$this_apply");
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        if (com.appsamurai.storyly.util.l.a(relativeLayout)) {
            oVar.f();
        } else {
            oVar.a(true);
        }
    }

    public static final void a(com.appsamurai.storyly.storylypresenter.cart.sheet.d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "$bottomSheet");
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = dVar.f4865f;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(4);
        }
    }

    public static final void a(o oVar, View view) {
        Intrinsics.checkNotNullParameter(oVar, "this$0");
        com.appsamurai.storyly.analytics.e.a(oVar.f5046a, com.appsamurai.storyly.analytics.a.CartViewClicked, oVar.getStorylyGroupItem$storyly_release(), oVar.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        oVar.k();
    }

    public final void a(String str, b0 b0Var) {
        d0 d0Var;
        z storylyItem;
        String str2 = str;
        z storylyItem2 = getStorylyItem();
        StoryGroupType storyGroupType = null;
        if (storylyItem2 == null) {
            d0Var = null;
        } else {
            d0Var = storylyItem2.f4303b;
        }
        if (d0Var != null) {
            d0Var.f3641c = str2;
        }
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
        if (storylyGroupItem$storyly_release != null) {
            storyGroupType = storylyGroupItem$storyly_release.f4228h;
        }
        if (!(storyGroupType == StoryGroupType.Ad || (storylyItem = getStorylyItem()) == null)) {
            getOnStorylyActionClicked$storyly_release().invoke(storylyItem.a());
        }
        com.appsamurai.storyly.analytics.e eVar = this.f5046a;
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.ActionClicked;
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release2 = getStorylyGroupItem$storyly_release();
        z storylyItem3 = getStorylyItem();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "click_url", str2);
        Unit unit = Unit.INSTANCE;
        com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release2, storylyItem3, b0Var, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4048);
    }

    public final void a() {
        getActionManager().e();
        this.f5045J = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00f2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r19) {
        /*
            r18 = this;
            r0 = r18
            java.util.List<com.appsamurai.storyly.data.v> r1 = r0.f5053h
            r2 = 0
            if (r1 != 0) goto L_0x0009
            r1 = r2
            goto L_0x0035
        L_0x0009:
            com.appsamurai.storyly.data.v r3 = r18.getStorylyGroupItem$storyly_release()
            int r1 = kotlin.collections.CollectionsKt.indexOf(r1, r3)
            java.lang.Integer r3 = r18.getStorylyCurrentIndex()
            com.appsamurai.storyly.data.v r4 = r18.getStorylyGroupItem$storyly_release()
            if (r4 != 0) goto L_0x001c
            goto L_0x0020
        L_0x001c:
            java.util.List<com.appsamurai.storyly.data.z> r4 = r4.f4226f
            if (r4 != 0) goto L_0x0022
        L_0x0020:
            r4 = r2
            goto L_0x002c
        L_0x0022:
            int r4 = r4.size()
            int r4 = r4 + -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x002c:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            int r3 = r3 + r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
        L_0x0035:
            if (r1 != 0) goto L_0x0038
            goto L_0x0042
        L_0x0038:
            int r1 = r1.intValue()
            java.util.List r3 = r18.getStorylyGroupItems$storyly_release()
            if (r3 != 0) goto L_0x0044
        L_0x0042:
            r1 = r2
            goto L_0x004e
        L_0x0044:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = com.appsamurai.storyly.util.e.a(r3, r1)
            com.appsamurai.storyly.data.v r1 = (com.appsamurai.storyly.data.v) r1
        L_0x004e:
            java.lang.Integer r3 = r18.getStorylyCurrentIndex()
            com.appsamurai.storyly.data.v r4 = r18.getStorylyGroupItem$storyly_release()
            if (r4 != 0) goto L_0x0059
            goto L_0x005d
        L_0x0059:
            java.util.List<com.appsamurai.storyly.data.z> r4 = r4.f4226f
            if (r4 != 0) goto L_0x005f
        L_0x005d:
            r4 = r2
            goto L_0x0069
        L_0x005f:
            int r4 = r4.size()
            int r4 = r4 + -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L_0x0069:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x007b
            if (r1 != 0) goto L_0x0072
            goto L_0x0081
        L_0x0072:
            int r3 = r1.b()
        L_0x0076:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            goto L_0x008a
        L_0x007b:
            java.lang.Integer r3 = r18.getStorylyCurrentIndex()
            if (r3 != 0) goto L_0x0083
        L_0x0081:
            r3 = r2
            goto L_0x008a
        L_0x0083:
            int r3 = r3.intValue()
            int r3 = r3 + 1
            goto L_0x0076
        L_0x008a:
            if (r3 != 0) goto L_0x008d
            goto L_0x0098
        L_0x008d:
            int r3 = r3.intValue()
            if (r1 != 0) goto L_0x0094
            goto L_0x0098
        L_0x0094:
            java.util.List<com.appsamurai.storyly.data.z> r4 = r1.f4226f
            if (r4 != 0) goto L_0x009a
        L_0x0098:
            r3 = r2
            goto L_0x00a4
        L_0x009a:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r3 = com.appsamurai.storyly.util.e.a(r4, r3)
            com.appsamurai.storyly.data.z r3 = (com.appsamurai.storyly.data.z) r3
        L_0x00a4:
            com.appsamurai.storyly.analytics.e r4 = r0.f5046a
            if (r1 != 0) goto L_0x00aa
            r5 = r2
            goto L_0x00ac
        L_0x00aa:
            com.appsamurai.storyly.StoryGroupType r5 = r1.f4228h
        L_0x00ac:
            kotlinx.serialization.json.JsonPrimitive r4 = r4.a((com.appsamurai.storyly.StoryGroupType) r5, (com.appsamurai.storyly.data.v) r1)
            com.appsamurai.storyly.analytics.e r5 = r0.f5046a
            if (r1 != 0) goto L_0x00b6
            r6 = r2
            goto L_0x00b8
        L_0x00b6:
            com.appsamurai.storyly.StoryGroupType r6 = r1.f4228h
        L_0x00b8:
            kotlinx.serialization.json.JsonPrimitive r3 = r5.a((com.appsamurai.storyly.StoryGroupType) r6, (com.appsamurai.storyly.data.z) r3)
            com.appsamurai.storyly.data.v r5 = r18.getStorylyGroupItem$storyly_release()
            if (r5 != 0) goto L_0x00c4
            r5 = r2
            goto L_0x00c6
        L_0x00c4:
            com.appsamurai.storyly.StoryGroupType r5 = r5.f4228h
        L_0x00c6:
            com.appsamurai.storyly.StoryGroupType r6 = com.appsamurai.storyly.StoryGroupType.MomentsDefault
            if (r5 == r6) goto L_0x00d4
            if (r1 != 0) goto L_0x00ce
            r1 = r2
            goto L_0x00d0
        L_0x00ce:
            com.appsamurai.storyly.StoryGroupType r1 = r1.f4228h
        L_0x00d0:
            if (r1 != r6) goto L_0x00d4
            r3 = r2
            goto L_0x00d5
        L_0x00d4:
            r2 = r4
        L_0x00d5:
            com.appsamurai.storyly.analytics.e r4 = r0.f5046a
            com.appsamurai.storyly.analytics.a r5 = com.appsamurai.storyly.analytics.a.NextClicked
            com.appsamurai.storyly.data.v r6 = r18.getStorylyGroupItem$storyly_release()
            com.appsamurai.storyly.data.z r7 = r18.getStorylyItem()
            kotlinx.serialization.json.JsonObjectBuilder r1 = new kotlinx.serialization.json.JsonObjectBuilder
            r1.<init>()
            if (r2 != 0) goto L_0x00ea
            kotlinx.serialization.json.JsonNull r2 = kotlinx.serialization.json.JsonNull.INSTANCE
        L_0x00ea:
            java.lang.String r8 = "target_story_group_id"
            r1.put(r8, r2)
            if (r3 != 0) goto L_0x00f4
            kotlinx.serialization.json.JsonNull r3 = kotlinx.serialization.json.JsonNull.INSTANCE
        L_0x00f4:
            java.lang.String r2 = "target_story_id"
            r1.put(r2, r3)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r19)
            java.lang.String r3 = "from_user"
            kotlinx.serialization.json.JsonElementBuildersKt.put((kotlinx.serialization.json.JsonObjectBuilder) r1, (java.lang.String) r3, (java.lang.Boolean) r2)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            kotlinx.serialization.json.JsonObject r10 = r1.build()
            r16 = 0
            r17 = 4056(0xfd8, float:5.684E-42)
            r8 = 0
            r9 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            com.appsamurai.storyly.analytics.e.a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r18.d()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.a(boolean):void");
    }

    public static final void a(com.appsamurai.storyly.storylypresenter.product.productdetail.h hVar) {
        Intrinsics.checkNotNullParameter(hVar, "$bottomSheet");
        BottomSheetBehavior<FrameLayout> bottomSheetBehavior = hVar.f5215e;
        if (bottomSheetBehavior != null) {
            bottomSheetBehavior.setState(4);
        }
    }

    public static final void a(o oVar, long j2) {
        long j3;
        long j4 = j2;
        z storylyItem = oVar.getStorylyItem();
        if (storylyItem == null) {
            j3 = 0;
        } else {
            j3 = storylyItem.f4317p;
        }
        com.appsamurai.storyly.analytics.e eVar = oVar.f5046a;
        com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.Seeked;
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
        z storylyItem2 = oVar.getStorylyItem();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        JsonElementBuildersKt.put(jsonObjectBuilder, "current_time", (Number) Long.valueOf(j3));
        JsonElementBuildersKt.put(jsonObjectBuilder, "target_time", (Number) Long.valueOf(j3 + j4));
        Unit unit = Unit.INSTANCE;
        com.appsamurai.storyly.analytics.e.a(eVar, aVar, storylyGroupItem$storyly_release, storylyItem2, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
        com.appsamurai.storyly.storylypresenter.storylycenter.a storylyCenterView = oVar.getStorylyCenterView();
        if (storylyCenterView.f5334b != null) {
            storylyCenterView.b();
            a.C0031a aVar2 = storylyCenterView.f5334b;
            if (aVar2 != null) {
                aVar2.a(j4);
            }
        }
        a.c cVar = oVar.getStorylyHeaderView().f5461c;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
            cVar = null;
        }
        cVar.a(j4);
        x.a aVar3 = oVar.getStorylyLayerContainerView().f6222u;
        if (aVar3 != null) {
            aVar3.b(new k1(j4));
        }
    }

    public static final void a(o oVar, boolean z2) {
        a.C0032a aVar = oVar.getStorylyFooterView().f5346c;
        if (aVar != null) {
            if (z2) {
                aVar.h();
            } else {
                aVar.b();
            }
            Unit unit = Unit.INSTANCE;
        }
        a.c cVar = oVar.getStorylyHeaderView().f5461c;
        if (cVar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("headerView");
            cVar = null;
        }
        if (z2) {
            cVar.n();
        } else {
            cVar.j();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.storylypresenter.o r24, com.appsamurai.storyly.storylypresenter.share.c r25) {
        /*
            r0 = r24
            java.lang.Integer r1 = r24.getStorylyCurrentIndex()
            if (r1 != 0) goto L_0x000a
            goto L_0x02dd
        L_0x000a:
            int r1 = r1.intValue()
            com.appsamurai.storyly.data.v r2 = r24.getStorylyGroupItem$storyly_release()
            if (r2 != 0) goto L_0x0015
            goto L_0x0019
        L_0x0015:
            java.util.List<com.appsamurai.storyly.data.z> r2 = r2.f4226f
            if (r2 != 0) goto L_0x001b
        L_0x0019:
            r7 = 0
            goto L_0x0022
        L_0x001b:
            java.lang.Object r1 = r2.get(r1)
            com.appsamurai.storyly.data.z r1 = (com.appsamurai.storyly.data.z) r1
            r7 = r1
        L_0x0022:
            if (r7 != 0) goto L_0x0026
            goto L_0x02dd
        L_0x0026:
            com.appsamurai.storyly.data.v r1 = r24.getStorylyGroupItem$storyly_release()
            if (r1 != 0) goto L_0x002e
            goto L_0x02dd
        L_0x002e:
            java.lang.String r2 = r7.f4302a
            com.appsamurai.storyly.config.StorylyConfig r4 = r0.f5047b
            com.appsamurai.storyly.config.StorylyShareConfig r4 = r4.getShare$storyly_release()
            java.lang.String r4 = r4.getUrl$storyly_release()
            java.lang.String r5 = "{story_id}"
            java.lang.String r4 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r2, false, 4, (java.lang.Object) null)
            java.lang.String r1 = r1.f4221a
            java.lang.String r5 = "{story_group_id}"
            java.lang.String r1 = kotlin.text.StringsKt__StringsJVMKt.replace$default((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r1, false, 4, (java.lang.Object) null)
            com.appsamurai.storyly.util.share.a r4 = new com.appsamurai.storyly.util.share.a
            android.content.Context r5 = r24.getContext()
            java.lang.String r6 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r4.<init>(r5)
            java.lang.String r5 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
            java.lang.String r8 = "link"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r8)
            int r8 = r25.ordinal()
            java.lang.String r10 = "storyId"
            java.lang.String r11 = "insertImage(context.cont…urrentTimeMillis(), null)"
            java.lang.String r12 = "IMG_"
            java.lang.String r13 = "com.instagram.android"
            java.lang.String r14 = "android.intent.action.SEND"
            java.lang.String r15 = "applicationPackage"
            java.lang.String r9 = "android.intent.extra.TEXT"
            r3 = 0
            switch(r8) {
                case 0: goto L_0x027f;
                case 1: goto L_0x0203;
                case 2: goto L_0x01f3;
                case 3: goto L_0x00d3;
                case 4: goto L_0x00bf;
                case 5: goto L_0x00a9;
                case 6: goto L_0x0093;
                case 7: goto L_0x007d;
                default: goto L_0x0079;
            }
        L_0x0079:
            r18 = r7
            goto L_0x02c4
        L_0x007d:
            java.lang.String r2 = "com.facebook.katana"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r15)
            android.content.Context r3 = r4.f6357a
            r5 = 0
            r6 = 2
            android.content.Intent r2 = com.appsamurai.storyly.util.share.b.a(r2, r5, r6)
            r2.putExtra(r9, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            androidx.core.content.ContextCompat.startActivity(r3, r2, r5)
            goto L_0x0079
        L_0x0093:
            r5 = 0
            r6 = 2
            java.lang.String r2 = "com.twitter.android"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r15)
            android.content.Context r3 = r4.f6357a
            android.content.Intent r2 = com.appsamurai.storyly.util.share.b.a(r2, r5, r6)
            r2.putExtra(r9, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            androidx.core.content.ContextCompat.startActivity(r3, r2, r5)
            goto L_0x0079
        L_0x00a9:
            r5 = 0
            r6 = 2
            java.lang.String r2 = "com.whatsapp"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r15)
            android.content.Context r3 = r4.f6357a
            android.content.Intent r2 = com.appsamurai.storyly.util.share.b.a(r2, r5, r6)
            r2.putExtra(r9, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            androidx.core.content.ContextCompat.startActivity(r3, r2, r5)
            goto L_0x0079
        L_0x00bf:
            r5 = 0
            r6 = 2
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r15)
            android.content.Context r2 = r4.f6357a
            android.content.Intent r3 = com.appsamurai.storyly.util.share.b.a(r13, r5, r6)
            r3.putExtra(r9, r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            androidx.core.content.ContextCompat.startActivity(r2, r3, r5)
            goto L_0x0079
        L_0x00d3:
            com.appsamurai.storyly.config.StorylyConfig r1 = r0.f5047b
            com.appsamurai.storyly.config.StorylyShareConfig r1 = r1.getShare$storyly_release()
            java.lang.String r1 = r1.getFacebookAppID$storyly_release()
            if (r1 != 0) goto L_0x00e0
            goto L_0x0079
        L_0x00e0:
            com.appsamurai.storyly.storylypresenter.storylylayer.x r2 = r24.getStorylyLayerContainerView()
            android.graphics.Bitmap r2 = r2.a((boolean) r3)
            com.appsamurai.storyly.util.share.a r4 = new com.appsamurai.storyly.util.share.a
            android.content.Context r8 = r24.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)
            r4.<init>(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
            java.lang.String r5 = "appID"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r5)
            android.content.Context r5 = r4.f6357a
            android.content.pm.PackageManager r5 = r5.getPackageManager()
            android.content.Context r6 = r4.f6357a
            java.lang.String r6 = r6.getPackageName()
            android.graphics.drawable.Drawable r5 = r5.getApplicationIcon(r6)
            java.lang.String r6 = "context.packageManager.g…Icon(context.packageName)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r20 = 125(0x7d, float:1.75E-43)
            r21 = 0
            r19 = 125(0x7d, float:1.75E-43)
            r22 = 4
            r23 = 0
            r18 = r5
            android.graphics.Bitmap r5 = androidx.core.graphics.drawable.DrawableKt.toBitmap$default(r18, r19, r20, r21, r22, r23)
            int r6 = r5.getWidth()
            int r8 = r5.getHeight()
            android.graphics.Bitmap$Config r9 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r8, r9)
            android.graphics.Rect r8 = new android.graphics.Rect
            int r9 = r5.getWidth()
            int r10 = r5.getHeight()
            r8.<init>(r3, r3, r9, r10)
            android.graphics.Canvas r9 = new android.graphics.Canvas
            r9.<init>(r6)
            android.graphics.Paint r10 = new android.graphics.Paint
            r10.<init>()
            android.graphics.RectF r14 = new android.graphics.RectF
            r14.<init>(r8)
            r15 = 25
            float r15 = (float) r15
            r18 = r7
            r7 = 1
            r10.setAntiAlias(r7)
            r9.drawARGB(r3, r3, r3, r3)
            r3 = -12434878(0xffffffffff424242, float:-2.5821426E38)
            r10.setColor(r3)
            r9.drawRoundRect(r14, r15, r15, r10)
            android.graphics.PorterDuffXfermode r3 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r14 = android.graphics.PorterDuff.Mode.SRC_IN
            r3.<init>(r14)
            r10.setXfermode(r3)
            r9.drawBitmap(r5, r8, r8, r10)
            if (r6 != 0) goto L_0x0170
            goto L_0x0171
        L_0x0170:
            r5 = r6
        L_0x0171:
            if (r2 != 0) goto L_0x0176
            r3 = 0
            r14 = 0
            goto L_0x01ad
        L_0x0176:
            int r3 = r2.getWidth()
            int r6 = r2.getHeight()
            int r8 = r5.getWidth()
            int r9 = r5.getHeight()
            float r10 = (float) r3
            r14 = 1063675494(0x3f666666, float:0.9)
            float r10 = r10 * r14
            r15 = 2
            int r8 = r8 / r15
            float r8 = (float) r8
            float r10 = r10 - r8
            float r8 = (float) r6
            float r8 = r8 * r14
            int r9 = r9 / r15
            float r9 = (float) r9
            float r8 = r8 - r9
            android.graphics.Bitmap$Config r9 = r2.getConfig()
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3, r6, r9)
            android.graphics.Canvas r6 = new android.graphics.Canvas
            r6.<init>(r3)
            android.graphics.Matrix r9 = new android.graphics.Matrix
            r9.<init>()
            r14 = 0
            r6.drawBitmap(r2, r9, r14)
            r6.drawBitmap(r5, r10, r8, r14)
        L_0x01ad:
            android.content.Context r2 = r4.f6357a
            android.content.ContentResolver r2 = r2.getContentResolver()
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r12, r5)
            java.lang.String r2 = android.provider.MediaStore.Images.Media.insertImage(r2, r3, r5, r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            android.net.Uri r2 = android.net.Uri.parse(r2)
            android.content.Intent r3 = new android.content.Intent
            java.lang.String r5 = "com.instagram.share.ADD_TO_STORY"
            r3.<init>(r5)
            r3.setFlags(r7)
            java.lang.String r5 = "image/jpeg"
            r3.setDataAndType(r2, r5)
            java.lang.String r5 = "source_application"
            r3.putExtra(r5, r1)
            android.content.Context r1 = r4.f6357a
            android.app.Activity r1 = com.appsamurai.storyly.util.g.a(r1)
            if (r1 != 0) goto L_0x01e7
            goto L_0x01ea
        L_0x01e7:
            r1.grantUriPermission(r13, r2, r7)
        L_0x01ea:
            if (r1 != 0) goto L_0x01ee
            goto L_0x02c4
        L_0x01ee:
            r1.startActivity(r3)
            goto L_0x02c4
        L_0x01f3:
            r18 = r7
            android.content.Context r2 = r24.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.String r3 = "shareUrl"
            com.appsamurai.storyly.util.g.a(r2, r3, r1)
            goto L_0x02c4
        L_0x0203:
            r18 = r7
            com.appsamurai.storyly.storylypresenter.storylylayer.x r1 = r24.getStorylyLayerContainerView()
            android.graphics.Bitmap r1 = r1.a((boolean) r3)
            com.appsamurai.storyly.util.share.a r3 = new com.appsamurai.storyly.util.share.a
            android.content.Context r4 = r24.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            r3.<init>(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r10)
            android.content.Context r4 = r3.f6357a
            android.content.ContentResolver r4 = r4.getContentResolver()
            long r5 = java.lang.System.currentTimeMillis()
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
            java.lang.String r5 = kotlin.jvm.internal.Intrinsics.stringPlus(r12, r5)
            r6 = 0
            java.lang.String r1 = android.provider.MediaStore.Images.Media.insertImage(r4, r1, r5, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            android.content.Intent r4 = new android.content.Intent
            r4.<init>()
            android.content.Context r5 = r3.f6357a
            java.lang.String r5 = r5.getPackageName()
            r4.setPackage(r5)
            r4.setAction(r14)
            android.content.Context r5 = r3.f6357a
            int r2 = java.lang.Integer.parseInt(r2)
            r6 = 134217728(0x8000000, float:3.85186E-34)
            int r6 = com.appsamurai.storyly.util.notification.b.a(r6)
            android.app.PendingIntent r2 = android.app.PendingIntent.getBroadcast(r5, r2, r4, r6)
            android.content.Intent r4 = new android.content.Intent
            r4.<init>()
            r4.setAction(r14)
            java.lang.String r5 = "android.intent.extra.STREAM"
            r4.putExtra(r5, r1)
            java.lang.String r1 = "image/*"
            r4.setType(r1)
            android.content.IntentSender r1 = r2.getIntentSender()
            r2 = 0
            android.content.Intent r1 = android.content.Intent.createChooser(r4, r2, r1)
            android.content.Context r3 = r3.f6357a
            androidx.core.content.ContextCompat.startActivity(r3, r1, r2)
            goto L_0x02c4
        L_0x027f:
            r18 = r7
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r10)
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.content.Context r5 = r4.f6357a
            java.lang.String r5 = r5.getPackageName()
            r3.setPackage(r5)
            r3.setAction(r14)
            android.content.Context r5 = r4.f6357a
            int r2 = java.lang.Integer.parseInt(r2)
            r6 = 134217728(0x8000000, float:3.85186E-34)
            int r6 = com.appsamurai.storyly.util.notification.b.a(r6)
            android.app.PendingIntent r2 = android.app.PendingIntent.getBroadcast(r5, r2, r3, r6)
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            r3.setAction(r14)
            r3.putExtra(r9, r1)
            java.lang.String r1 = "text/plain"
            r3.setType(r1)
            android.content.IntentSender r1 = r2.getIntentSender()
            r2 = 0
            android.content.Intent r1 = android.content.Intent.createChooser(r3, r2, r1)
            android.content.Context r3 = r4.f6357a
            androidx.core.content.ContextCompat.startActivity(r3, r1, r2)
        L_0x02c4:
            com.appsamurai.storyly.analytics.e r4 = r0.f5046a
            com.appsamurai.storyly.analytics.a r5 = com.appsamurai.storyly.analytics.a.LinkShared
            com.appsamurai.storyly.data.v r6 = r24.getStorylyGroupItem$storyly_release()
            r14 = 0
            r15 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r16 = 0
            r17 = 4088(0xff8, float:5.729E-42)
            r7 = r18
            com.appsamurai.storyly.analytics.e.a(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
        L_0x02dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.a(com.appsamurai.storyly.storylypresenter.o, com.appsamurai.storyly.storylypresenter.share.c):void");
    }

    public final void a(g0 g0Var) {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        com.appsamurai.storyly.storylypresenter.product.b bVar = new com.appsamurai.storyly.storylypresenter.product.b(context, this.f5047b, g0Var.f(), g0Var.e(), g0Var.g(), new l(this), new m(this));
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(bVar, newInstance);
        bVar.post(new C0.d(bVar, 7));
    }

    public static final void a(o oVar) {
        List<z> list;
        List<z> list2;
        List<z> list3;
        int size = oVar.f5052g.size();
        for (Map.Entry next : oVar.f5052g.entrySet()) {
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
            if (!(storylyGroupItem$storyly_release == null || (list3 = storylyGroupItem$storyly_release.f4226f) == null)) {
                list3.add(((Number) next.getKey()).intValue(), next.getValue());
            }
        }
        Iterator<Map.Entry<Integer, z>> it = oVar.f5052g.entrySet().iterator();
        while (it.hasNext()) {
            z zVar = (z) it.next().getValue();
            if (!zVar.f4318q && oVar.getOnStoryConditionCheck$storyly_release().invoke(zVar).booleanValue()) {
                it.remove();
            }
        }
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release2 = oVar.getStorylyGroupItem$storyly_release();
        if (!(storylyGroupItem$storyly_release2 == null || (list2 = storylyGroupItem$storyly_release2.f4226f) == null)) {
            CollectionsKt__MutableCollectionsKt.removeAll(list2, b0.f4767a);
        }
        int size2 = size - oVar.f5052g.size();
        if (size2 > 0) {
            com.appsamurai.storyly.storylypresenter.storylyheader.a storylyHeaderView = oVar.getStorylyHeaderView();
            Integer valueOf = Integer.valueOf(size2);
            a.c cVar = storylyHeaderView.f5461c;
            Integer num = null;
            if (cVar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerView");
                cVar = null;
            }
            cVar.a(valueOf);
            com.appsamurai.storyly.data.v storylyGroupItem$storyly_release3 = oVar.getStorylyGroupItem$storyly_release();
            if (!(storylyGroupItem$storyly_release3 == null || (list = storylyGroupItem$storyly_release3.f4226f) == null)) {
                num = Integer.valueOf(CollectionsKt.indexOf(list, oVar.getStorylyItem()));
            }
            oVar.setStorylyCurrentIndex(num);
        }
    }

    public final void a(Integer num, Integer num2) {
        z zVar;
        boolean z2;
        d0 d0Var;
        List<b0> list;
        a0 a0Var;
        Map<com.appsamurai.storyly.data.j, List<STRProductItem>> map;
        com.appsamurai.storyly.data.m c3;
        List<z> list2;
        com.appsamurai.storyly.data.v storylyGroupItem$storyly_release = getStorylyGroupItem$storyly_release();
        if (storylyGroupItem$storyly_release == null || (list2 = storylyGroupItem$storyly_release.f4226f) == null) {
            zVar = null;
        } else {
            zVar = (z) com.appsamurai.storyly.util.e.a(list2, num);
        }
        if (zVar != null && (d0Var = zVar.f4303b) != null && (list = d0Var.f3639a) != null && !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                b0 b0Var = (b0) it.next();
                if (b0Var == null) {
                    a0Var = null;
                } else {
                    a0Var = b0Var.f3615j;
                }
                g0 g0Var = a0Var instanceof g0 ? (g0) a0Var : null;
                if (g0Var == null || (c3 = g0Var.c()) == null) {
                    map = null;
                } else {
                    map = c3.f3859a;
                }
                if (map != null && !map.isEmpty()) {
                    z2 = true;
                    break;
                }
            }
            post(new W.h(this, z2, num2));
        }
        z2 = false;
        post(new W.h(this, z2, num2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r4 = r3.getStorylyCart$storyly_release();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void a(com.appsamurai.storyly.storylypresenter.o r3, boolean r4, java.lang.Integer r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            com.appsamurai.storyly.storylypresenter.cart.b r0 = r3.getStorylyCartView()
            r1 = 0
            if (r4 == 0) goto L_0x001b
            com.appsamurai.storyly.data.managers.product.a r4 = r3.getStorylyCart$storyly_release()
            if (r4 != 0) goto L_0x0014
            goto L_0x001b
        L_0x0014:
            boolean r4 = r4.f4038a
            r2 = 1
            if (r4 != r2) goto L_0x001b
            r4 = r1
            goto L_0x001c
        L_0x001b:
            r4 = 4
        L_0x001c:
            android.view.ViewGroup r0 = r0.f4776a
            r0.setVisibility(r4)
            com.appsamurai.storyly.storylypresenter.cart.b r3 = r3.getStorylyCartView()
            kotlin.properties.ReadWriteProperty r4 = r3.f4778c
            kotlin.reflect.KProperty<java.lang.Object>[] r0 = com.appsamurai.storyly.storylypresenter.cart.b.f4775i
            r0 = r0[r1]
            r4.setValue(r3, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.o.a(com.appsamurai.storyly.storylypresenter.o, boolean, java.lang.Integer):void");
    }

    public final void a(g0 g0Var, List<STRProductItem> list) {
        com.appsamurai.storyly.analytics.e.a(this.f5046a, com.appsamurai.storyly.analytics.a.ProductCatalogClicked, getStorylyGroupItem$storyly_release(), getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        h();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        com.appsamurai.storyly.storylypresenter.product.productdetail.h hVar = new com.appsamurai.storyly.storylypresenter.product.productdetail.h(context, list == null ? CollectionsKt.emptyList() : list, this.f5047b, new k(this), g0Var);
        hVar.setOnProductSelected$storyly_release(new h(this));
        hVar.setOnBuyNowClick$storyly_release(new i(this));
        hVar.setOnBuyNowSuccess$storyly_release(new j(this, g0Var));
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, -1});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        addView(hVar, newInstance);
        hVar.post(new C0202c(hVar, 1));
    }
}
