package com.appsamurai.storyly.storylypresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.util.l;
import com.appsamurai.storyly.util.ui.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

@SuppressLint({"ViewConstructor"})
public final class d extends RecyclerView {

    /* renamed from: u  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4955u = {androidx.compose.ui.autofill.a.m(d.class, "storylyGroupItems", "getStorylyGroupItems()Ljava/util/List;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4956a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final com.appsamurai.storyly.data.managers.cache.c f4957b;

    /* renamed from: c  reason: collision with root package name */
    public FrameLayout f4958c;

    /* renamed from: d  reason: collision with root package name */
    public com.appsamurai.storyly.analytics.e f4959d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final ReadWriteProperty f4960e;

    /* renamed from: f  reason: collision with root package name */
    public int f4961f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public com.appsamurai.storyly.data.managers.product.a f4962g;

    /* renamed from: h  reason: collision with root package name */
    public Function0<Unit> f4963h;

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f4964i;

    /* renamed from: j  reason: collision with root package name */
    public Function0<Unit> f4965j;

    /* renamed from: k  reason: collision with root package name */
    public Function1<? super v, Unit> f4966k;

    /* renamed from: l  reason: collision with root package name */
    public Function1<? super Story, Unit> f4967l;

    /* renamed from: m  reason: collision with root package name */
    public Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> f4968m;

    /* renamed from: n  reason: collision with root package name */
    public Function2<? super StoryGroup, ? super Story, Unit> f4969n;

    /* renamed from: o  reason: collision with root package name */
    public Function1<? super z, Boolean> f4970o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public Function1<? super Function0<Unit>, Unit> f4971p;

    /* renamed from: q  reason: collision with root package name */
    public boolean f4972q;

    /* renamed from: r  reason: collision with root package name */
    public boolean f4973r;

    /* renamed from: s  reason: collision with root package name */
    public int f4974s;
    @NotNull

    /* renamed from: t  reason: collision with root package name */
    public final Lazy f4975t;

    public static final class a implements j.f {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4976a;

        /* renamed from: com.appsamurai.storyly.storylypresenter.d$a$a  reason: collision with other inner class name */
        public static final class C0022a extends com.appsamurai.storyly.util.b {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ o f4977a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ d f4978b;

            public C0022a(o oVar, d dVar) {
                this.f4977a = oVar;
                this.f4978b = dVar;
            }

            public static final void a(d dVar) {
                Intrinsics.checkNotNullParameter(dVar, "this$0");
                dVar.getOnDismissed$storyly_release().invoke();
                dVar.getBackgroundLayout().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }

            public void onAnimationEnd(@Nullable Animation animation) {
                this.f4977a.m();
                v storylyGroupItem$storyly_release = this.f4977a.getStorylyGroupItem$storyly_release();
                com.appsamurai.storyly.analytics.e.a(this.f4978b.getStorylyTracker(), com.appsamurai.storyly.analytics.a.Dismissed, this.f4977a.getStorylyGroupItem$storyly_release(), storylyGroupItem$storyly_release == null ? null : storylyGroupItem$storyly_release.f4240t, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
                this.f4978b.setLayoutManager((RecyclerView.LayoutManager) null);
                new Handler(Looper.getMainLooper()).postDelayed(new W.a(this.f4978b, 1), 200);
            }
        }

        public a(d dVar) {
            this.f4976a = dVar;
        }

        public void a(float f2, @NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            View childAt = this.f4976a.getChildAt(0);
            o oVar = childAt instanceof o ? (o) childAt : null;
            if (oVar != null) {
                if (Math.abs(motionEvent.getRawX() - f2) > ((float) this.f4976a.getMeasuredWidth()) * 0.35f) {
                    this.f4976a.getBackgroundLayout().setBackgroundColor(0);
                    Integer selectedStorylyGroupIndex = this.f4976a.getSelectedStorylyGroupIndex();
                    ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 0, (selectedStorylyGroupIndex != null && selectedStorylyGroupIndex.intValue() == this.f4976a.getStorylyGroupItems().size() + -1) ? 0.0f : (float) this.f4976a.getWidth(), 0, (float) (this.f4976a.getHeight() / 2));
                    scaleAnimation.setAnimationListener(new C0022a(oVar, this.f4976a));
                    scaleAnimation.setDuration(200);
                    Unit unit = Unit.INSTANCE;
                    oVar.startAnimation(scaleAnimation);
                    return;
                }
                oVar.j();
            }
        }
    }

    public interface c {
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.d$d  reason: collision with other inner class name */
    public static final class C0023d extends Lambda implements Function0<StorylyGroupRecyclerView$linearLayoutManager$2$1> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f4981a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4982b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0023d(Context context, d dVar) {
            super(0);
            this.f4981a = context;
            this.f4982b = dVar;
        }

        public Object invoke() {
            return new StorylyGroupRecyclerView$linearLayoutManager$2$1(this.f4982b, this.f4981a);
        }
    }

    public static final class e extends ObservableProperty<List<v>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4983a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ d f4984b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, d dVar) {
            super(obj2);
            this.f4983a = obj;
            this.f4984b = dVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<v> list, List<v> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List<v> list4 = list;
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            for (Object next : list4) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                v vVar = (v) next;
                if (i3 < this.f4984b.f4961f && vVar.f4228h == StoryGroupType.Ad) {
                    arrayList.add(next);
                }
                i3 = i4;
            }
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list4, 10));
            for (v vVar2 : list4) {
                arrayList2.add(vVar2.f4221a);
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object next2 : list3) {
                if (!arrayList2.contains(((v) next2).f4221a)) {
                    arrayList3.add(next2);
                }
            }
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                v vVar3 = (v) it.next();
                Iterator it2 = list3.iterator();
                int i5 = 0;
                while (true) {
                    if (!it2.hasNext()) {
                        i5 = -1;
                        break;
                    } else if (Intrinsics.areEqual((Object) ((v) it2.next()).f4221a, (Object) vVar3.f4221a)) {
                        break;
                    } else {
                        i5++;
                    }
                }
                d dVar = this.f4984b;
                int i6 = dVar.f4961f;
                if (i5 <= i6 - size) {
                    dVar.f4961f = i6 + 1;
                }
            }
            RecyclerView.Adapter adapter = this.f4984b.getAdapter();
            if (adapter != null) {
                b bVar = (b) adapter;
                Intrinsics.checkNotNullParameter(list4, "oldValue");
                Intrinsics.checkNotNullParameter(list3, "newValue");
                Intrinsics.checkNotNullParameter(bVar, "this");
                Intrinsics.checkNotNullParameter(bVar, "receiver");
                Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
                Intrinsics.checkNotNullParameter(list3, "new");
                DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new n(list4, list3, bVar), true);
                Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : ViewHolder> Ada…UpdatesTo(this)\n        }");
                calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) bVar);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.appsamurai.storyly.storylypresenter.StorylyGroupRecyclerView.StorylyGroupAdapter");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @NotNull com.appsamurai.storyly.data.managers.cache.c cVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(cVar, "storylyImageCacheManager");
        this.f4956a = storylyConfig;
        this.f4957b = cVar;
        Delegates delegates = Delegates.INSTANCE;
        ArrayList arrayList = new ArrayList();
        this.f4960e = new e(arrayList, arrayList, this);
        this.f4975t = LazyKt.lazy(new C0023d(context, this));
        setId(R.id.st_storyly_group_recycler_view);
        setBackgroundColor(0);
        setHasFixedSize(true);
        setItemViewCacheSize(0);
        setImportantForAccessibility(2);
        setContentDescription("");
        setLayoutManager(getLinearLayoutManager());
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        new j(this).a((j.f) new a(this));
        setAdapter(new b(this));
        new PagerSnapHelper().attachToRecyclerView(this);
        setLayoutDirection(storylyConfig.getLayoutDirection$storyly_release().getLayoutDirection$storyly_release());
    }

    /* access modifiers changed from: private */
    public final StorylyGroupRecyclerView$linearLayoutManager$2$1 getLinearLayoutManager() {
        return (StorylyGroupRecyclerView$linearLayoutManager$2$1) this.f4975t.getValue();
    }

    public final void b() {
        o a2 = a(getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.b();
        }
    }

    public final Integer c() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return null;
        }
        int findFirstVisibleItemPosition = l.a(this) ? linearLayoutManager.findFirstVisibleItemPosition() : linearLayoutManager.findLastVisibleItemPosition();
        Integer valueOf = Integer.valueOf(findFirstVisibleItemPosition);
        if (findFirstVisibleItemPosition != -1) {
            return valueOf;
        }
        return null;
    }

    public final void d() {
        o a2 = a(getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.h();
        }
    }

    public final void e() {
        o a2 = a(getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.j();
        }
    }

    @NotNull
    public final FrameLayout getBackgroundLayout() {
        FrameLayout frameLayout = this.f4958c;
        if (frameLayout != null) {
            return frameLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("backgroundLayout");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnClosed$storyly_release() {
        Function0<Unit> function0 = this.f4963h;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onClosed");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnCompleted$storyly_release() {
        Function0<Unit> function0 = this.f4965j;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onCompleted");
        return null;
    }

    @NotNull
    public final Function0<Unit> getOnDismissed$storyly_release() {
        Function0<Unit> function0 = this.f4964i;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onDismissed");
        return null;
    }

    @Nullable
    public final Function1<Function0<Unit>, Unit> getOnPagingThresholdPassed$storyly_release() {
        return this.f4971p;
    }

    @NotNull
    public final Function1<z, Boolean> getOnStoryConditionCheck$storyly_release() {
        Function1<? super z, Boolean> function1 = this.f4970o;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStoryConditionCheck");
        return null;
    }

    @NotNull
    public final Function3<StoryGroup, Story, StoryComponent, Unit> getOnStoryLayerInteraction$storyly_release() {
        Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function3 = this.f4968m;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStoryLayerInteraction");
        return null;
    }

    @NotNull
    public final Function1<Story, Unit> getOnStorylyActionClicked$storyly_release() {
        Function1<? super Story, Unit> function1 = this.f4967l;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStorylyActionClicked");
        return null;
    }

    @NotNull
    public final Function1<v, Unit> getOnStorylyGroupShown$storyly_release() {
        Function1<? super v, Unit> function1 = this.f4966k;
        if (function1 != null) {
            return function1;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStorylyGroupShown");
        return null;
    }

    @NotNull
    public final Function2<StoryGroup, Story, Unit> getOnStorylyHeaderClicked$storyly_release() {
        Function2<? super StoryGroup, ? super Story, Unit> function2 = this.f4969n;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onStorylyHeaderClicked");
        return null;
    }

    @Nullable
    public final Integer getSelectedStorylyGroupIndex() {
        return Integer.valueOf(this.f4961f);
    }

    @Nullable
    public final com.appsamurai.storyly.data.managers.product.a getStorylyCart() {
        return this.f4962g;
    }

    @NotNull
    public final List<v> getStorylyGroupItems() {
        return (List) this.f4960e.getValue(this, f4955u[0]);
    }

    @NotNull
    public final com.appsamurai.storyly.analytics.e getStorylyTracker() {
        com.appsamurai.storyly.analytics.e eVar = this.f4959d;
        if (eVar != null) {
            return eVar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storylyTracker");
        return null;
    }

    public void onScrollStateChanged(int i3) {
        v vVar;
        z zVar;
        int i4 = i3;
        super.onScrollStateChanged(i3);
        if (i4 == 2) {
            this.f4974s = i4;
            return;
        }
        if (i4 == 0) {
            for (View a2 : ViewGroupKt.getChildren(this)) {
                com.appsamurai.storyly.util.ui.a.a(a2);
            }
            if (this.f4974s == 2) {
                Integer c3 = c();
                if (c3 != null) {
                    int intValue = c3.intValue();
                    o a3 = a(c3);
                    Integer selectedStorylyGroupIndex = getSelectedStorylyGroupIndex();
                    if (selectedStorylyGroupIndex == null || intValue != selectedStorylyGroupIndex.intValue()) {
                        Integer selectedStorylyGroupIndex2 = getSelectedStorylyGroupIndex();
                        if (selectedStorylyGroupIndex2 != null) {
                            int intValue2 = selectedStorylyGroupIndex2.intValue();
                            v vVar2 = (v) com.appsamurai.storyly.util.e.a(getStorylyGroupItems(), selectedStorylyGroupIndex2);
                            if (vVar2 != null && (vVar = (v) com.appsamurai.storyly.util.e.a(getStorylyGroupItems(), c3)) != null && (zVar = (z) com.appsamurai.storyly.util.e.a(vVar.f4226f, Integer.valueOf(vVar.b()))) != null) {
                                com.appsamurai.storyly.analytics.a aVar = intValue > intValue2 ? com.appsamurai.storyly.analytics.a.NextSwiped : com.appsamurai.storyly.analytics.a.PreviousSwiped;
                                JsonPrimitive a4 = getStorylyTracker().a(vVar.f4228h, vVar);
                                JsonPrimitive a5 = getStorylyTracker().a(vVar.f4228h, zVar);
                                StoryGroupType storyGroupType = vVar2.f4228h;
                                StoryGroupType storyGroupType2 = StoryGroupType.MomentsDefault;
                                if (storyGroupType != storyGroupType2 && vVar.f4228h == storyGroupType2) {
                                    a4 = null;
                                    a5 = null;
                                }
                                com.appsamurai.storyly.analytics.e storylyTracker = getStorylyTracker();
                                z zVar2 = vVar2.f4240t;
                                JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
                                if (a4 == null) {
                                    a4 = JsonNull.INSTANCE;
                                }
                                jsonObjectBuilder.put("target_story_group_id", a4);
                                if (a5 == null) {
                                    a5 = JsonNull.INSTANCE;
                                }
                                jsonObjectBuilder.put("target_story_id", a5);
                                Unit unit = Unit.INSTANCE;
                                com.appsamurai.storyly.analytics.e.a(storylyTracker, aVar, vVar2, zVar2, (b0) null, (StoryComponent) null, jsonObjectBuilder.build(), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
                                setSelectedStorylyGroupIndex(c3);
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    } else if (a3 != null) {
                        a3.j();
                    }
                    b();
                } else {
                    return;
                }
            } else {
                Integer selectedStorylyGroupIndex3 = getSelectedStorylyGroupIndex();
                if (selectedStorylyGroupIndex3 != null) {
                    int intValue3 = selectedStorylyGroupIndex3.intValue();
                    if (intValue3 > 0 && intValue3 < getStorylyGroupItems().size() - 1) {
                        e();
                    }
                    b();
                } else {
                    return;
                }
            }
        } else if (i4 == 1) {
            d();
            a();
        }
        this.f4974s = i4;
    }

    public void onScrolled(int i3, int i4) {
        super.onScrolled(i3, i4);
        for (View next : ViewGroupKt.getChildren(this)) {
            com.appsamurai.storyly.util.ui.a.a(next, ((float) (next.getLeft() - i3)) / (((float) ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) * 1.0f));
        }
    }

    public final void setBackgroundLayout(@NotNull FrameLayout frameLayout) {
        Intrinsics.checkNotNullParameter(frameLayout, "<set-?>");
        this.f4958c = frameLayout;
    }

    public final void setOnClosed$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f4963h = function0;
    }

    public final void setOnCompleted$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f4965j = function0;
    }

    public final void setOnDismissed$storyly_release(@NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.f4964i = function0;
    }

    public final void setOnPagingThresholdPassed$storyly_release(@Nullable Function1<? super Function0<Unit>, Unit> function1) {
        this.f4971p = function1;
    }

    public final void setOnStoryConditionCheck$storyly_release(@NotNull Function1<? super z, Boolean> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f4970o = function1;
    }

    public final void setOnStoryLayerInteraction$storyly_release(@NotNull Function3<? super StoryGroup, ? super Story, ? super StoryComponent, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.f4968m = function3;
    }

    public final void setOnStorylyActionClicked$storyly_release(@NotNull Function1<? super Story, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f4967l = function1;
    }

    public final void setOnStorylyGroupShown$storyly_release(@NotNull Function1<? super v, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.f4966k = function1;
    }

    public final void setOnStorylyHeaderClicked$storyly_release(@NotNull Function2<? super StoryGroup, ? super Story, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "<set-?>");
        this.f4969n = function2;
    }

    public final void setSelectedStorylyGroupIndex(@Nullable Integer num) {
        if (num != null && com.appsamurai.storyly.util.e.a(getStorylyGroupItems(), num) != null) {
            this.f4961f = num.intValue();
            setLayoutManager(getLinearLayoutManager());
            scrollToPosition(num.intValue());
        }
    }

    public final void setStorylyCart(@Nullable com.appsamurai.storyly.data.managers.product.a aVar) {
        this.f4962g = aVar;
    }

    public final void setStorylyGroupItems(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f4960e.setValue(this, f4955u[0], list);
    }

    public final void setStorylyTracker(@NotNull com.appsamurai.storyly.analytics.e eVar) {
        Intrinsics.checkNotNullParameter(eVar, "<set-?>");
        this.f4959d = eVar;
    }

    public final void a() {
        o a2 = a(getSelectedStorylyGroupIndex());
        if (a2 != null) {
            a2.a();
        }
    }

    public final o a(Integer num) {
        if (num == null) {
            return null;
        }
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return null;
        }
        View findViewByPosition = linearLayoutManager.findViewByPosition(num.intValue());
        if (findViewByPosition instanceof o) {
            return (o) findViewByPosition;
        }
        return null;
    }

    public final void a(@NotNull v vVar, @NotNull v vVar2) {
        Intrinsics.checkNotNullParameter(vVar, "groupItem");
        Intrinsics.checkNotNullParameter(vVar2, "adGroupItem");
        int indexOf = getStorylyGroupItems().indexOf(vVar) + 1;
        getStorylyGroupItems().add(indexOf, vVar2);
        new Handler(Looper.getMainLooper()).post(new W.c(this, indexOf, 0));
    }

    public static final void a(d dVar, int i3) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        RecyclerView.Adapter adapter = dVar.getAdapter();
        if (adapter != null) {
            adapter.notifyItemInserted(i3);
        }
    }

    public final class b extends RecyclerView.Adapter<a> implements c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ d f4979a;

        public final class a extends RecyclerView.ViewHolder {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final o f4980a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public a(@NotNull b bVar, o oVar) {
                super(oVar);
                Intrinsics.checkNotNullParameter(bVar, "this$0");
                Intrinsics.checkNotNullParameter(oVar, "storylyGroupView");
                this.f4980a = oVar;
            }
        }

        public b(d dVar) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            this.f4979a = dVar;
        }

        /* renamed from: a */
        public void onViewDetachedFromWindow(@NotNull a aVar) {
            List<z> list;
            List<z> list2;
            List<z> list3;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            super.onViewDetachedFromWindow(aVar);
            o oVar = aVar.f4980a;
            Iterator<Map.Entry<Integer, z>> it = oVar.f5052g.entrySet().iterator();
            while (true) {
                int i3 = 0;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                z zVar = (z) next.getValue();
                int intValue = ((Number) next.getKey()).intValue();
                v storylyGroupItem$storyly_release = oVar.getStorylyGroupItem$storyly_release();
                if (!(storylyGroupItem$storyly_release == null || (list3 = storylyGroupItem$storyly_release.f4226f) == null)) {
                    i3 = list3.size();
                }
                if (intValue > i3) {
                    v storylyGroupItem$storyly_release2 = oVar.getStorylyGroupItem$storyly_release();
                    if (!(storylyGroupItem$storyly_release2 == null || (list2 = storylyGroupItem$storyly_release2.f4226f) == null)) {
                        list2.add(zVar);
                    }
                } else {
                    v storylyGroupItem$storyly_release3 = oVar.getStorylyGroupItem$storyly_release();
                    if (!(storylyGroupItem$storyly_release3 == null || (list = storylyGroupItem$storyly_release3.f4226f) == null)) {
                        list.add(intValue, zVar);
                    }
                }
                it.remove();
            }
            aVar.f4980a.m();
            if (this.f4979a.getScrollState() != 1) {
                this.f4979a.f4973r = false;
                new Handler(Looper.getMainLooper()).postDelayed(new W.a(this.f4979a, 2), 200);
            }
        }

        public int getItemCount() {
            return this.f4979a.getStorylyGroupItems().size();
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
            a aVar = (a) viewHolder;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            aVar.f4980a.setStorylyGroupItems$storyly_release(this.f4979a.getStorylyGroupItems());
            aVar.f4980a.setTempStorylyGroupItem$storyly_release((v) com.appsamurai.storyly.util.e.a(this.f4979a.getStorylyGroupItems(), Integer.valueOf(i3)));
            aVar.f4980a.setStorylyCart$storyly_release(this.f4979a.getStorylyCart());
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            Context context = viewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "parent.context");
            com.appsamurai.storyly.analytics.e storylyTracker = this.f4979a.getStorylyTracker();
            d dVar = this.f4979a;
            o oVar = new o(context, storylyTracker, dVar.f4956a, dVar.f4957b);
            oVar.setShowMomentsUserAnalytics$storyly_release(this.f4979a.f4956a.getMoments$storyly_release().getShowMomentsUserAnalytics$storyly_release());
            oVar.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            oVar.setOnClosed$storyly_release(new e(this.f4979a));
            oVar.setOnCompleted$storyly_release(new f(this.f4979a));
            oVar.setOnPrevious$storyly_release(new g(this.f4979a));
            oVar.setOnSwipeHorizontal$storyly_release(new h(this.f4979a));
            oVar.setOnTouchUp$storyly_release(new i(this.f4979a));
            oVar.setOnDismissed$storyly_release(new j(this.f4979a));
            oVar.setOnSwipeDown$storyly_release(new k(this.f4979a));
            oVar.setOnPullDown$storyly_release(new l(this.f4979a));
            oVar.setOnStorylyActionClicked$storyly_release(this.f4979a.getOnStorylyActionClicked$storyly_release());
            oVar.setOnStoryLayerInteraction$storyly_release(this.f4979a.getOnStoryLayerInteraction$storyly_release());
            oVar.setOnStorylyHeaderClicked$storyly_release(this.f4979a.getOnStorylyHeaderClicked$storyly_release());
            oVar.setOnStoryConditionCheck$storyly_release(this.f4979a.getOnStoryConditionCheck$storyly_release());
            return new a(this, oVar);
        }

        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            a aVar = (a) viewHolder;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            super.onViewAttachedToWindow(aVar);
            if (CollectionsKt.indexOf(this.f4979a.getStorylyGroupItems(), aVar.f4980a.getTempStorylyGroupItem$storyly_release()) >= this.f4979a.getStorylyGroupItems().size() - 4) {
                d dVar = this.f4979a;
                if (!dVar.f4972q) {
                    dVar.f4972q = true;
                    Function1<Function0<Unit>, Unit> onPagingThresholdPassed$storyly_release = dVar.getOnPagingThresholdPassed$storyly_release();
                    if (onPagingThresholdPassed$storyly_release != null) {
                        onPagingThresholdPassed$storyly_release.invoke(new m(this.f4979a));
                    }
                }
            }
            o oVar = aVar.f4980a;
            oVar.setStorylyGroupItem$storyly_release(oVar.getTempStorylyGroupItem$storyly_release());
            aVar.f4980a.setStorylyCart$storyly_release(this.f4979a.getStorylyCart());
            v storylyGroupItem$storyly_release = aVar.f4980a.getStorylyGroupItem$storyly_release();
            if (storylyGroupItem$storyly_release != null) {
                this.f4979a.getOnStorylyGroupShown$storyly_release().invoke(storylyGroupItem$storyly_release);
            }
            aVar.f4980a.c();
        }

        public static final void a(d dVar) {
            Intrinsics.checkNotNullParameter(dVar, "this$0");
            o a2 = dVar.a(dVar.getSelectedStorylyGroupIndex());
            if (a2 != null) {
                a2.l();
            }
        }
    }
}
