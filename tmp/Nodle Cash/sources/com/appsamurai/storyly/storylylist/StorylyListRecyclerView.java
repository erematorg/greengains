package com.appsamurai.storyly.storylylist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.view.ViewGroupKt;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroupAnimation;
import com.appsamurai.storyly.StoryGroupListOrientation;
import com.appsamurai.storyly.StoryGroupSize;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.config.styling.group.StoryGroupView;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.m0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.ui.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

@SuppressLint({"ViewConstructor"})
public final class StorylyListRecyclerView extends RecyclerView {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4640a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final com.appsamurai.storyly.analytics.e f4641b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final Lazy f4642c = LazyKt.lazy(new g(this));
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Function2<? super v, ? super Integer, Unit> f4643d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public Function1<? super Function0<Unit>, Unit> f4644e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public Function0<Integer> f4645f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Function0<Integer> f4646g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public e f4647h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public b f4648i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    public List<v> f4649j;
    @Nullable

    /* renamed from: k  reason: collision with root package name */
    public List<MomentsItem> f4650k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    public String f4651l;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    public Integer f4652m;

    /* renamed from: n  reason: collision with root package name */
    public boolean f4653n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f4654o;
    @NotNull

    /* renamed from: p  reason: collision with root package name */
    public List<String> f4655p = new ArrayList();

    public static final class a extends RecyclerView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyListRecyclerView f4657a;

        public a(StorylyListRecyclerView storylyListRecyclerView) {
            this.f4657a = storylyListRecyclerView;
        }

        public void onScrollStateChanged(@NotNull RecyclerView recyclerView, int i3) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            super.onScrollStateChanged(recyclerView, i3);
            if (i3 == 0) {
                this.f4657a.getStoryGroupImpressionManager().a(this.f4657a.getVisibleStorylyGroupItems());
            }
        }
    }

    public interface c {
    }

    public final class d extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyListRecyclerView f4663a;

        public d(StorylyListRecyclerView storylyListRecyclerView) {
            Intrinsics.checkNotNullParameter(storylyListRecyclerView, "this$0");
            this.f4663a = storylyListRecyclerView;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0084, code lost:
            r4 = r4.invoke();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void getItemOffsets(@org.jetbrains.annotations.NotNull android.graphics.Rect r8, @org.jetbrains.annotations.NotNull android.view.View r9, @org.jetbrains.annotations.NotNull androidx.recyclerview.widget.RecyclerView r10, @org.jetbrains.annotations.NotNull androidx.recyclerview.widget.RecyclerView.State r11) {
            /*
                r7 = this;
                java.lang.String r0 = "outRect"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "view"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = "parent"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r0 = "state"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                int r9 = r10.getChildAdapterPosition(r9)
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r10 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r10 = r10.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r10 = r10.getBar$storyly_release()
                int r10 = r10.getSection$storyly_release()
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r11 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r11 = r11.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r11 = r11.getBar$storyly_release()
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r0 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r0 = r0.f4640a
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r0 = r0.getGroup$storyly_release()
                com.appsamurai.storyly.StoryGroupSize r0 = r0.getSize$storyly_release()
                int r11 = r11.getHorizontalPaddingBetweenItems$storyly_release(r0)
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r0 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r0 = r0.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r0 = r0.getBar$storyly_release()
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r1 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r1 = r1.f4640a
                com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling r1 = r1.getGroup$storyly_release()
                com.appsamurai.storyly.StoryGroupSize r1 = r1.getSize$storyly_release()
                int r0 = r0.getVerticalPaddingBetweenItems$storyly_release(r1)
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r1 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r1 = r1.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r1 = r1.getBar$storyly_release()
                int r1 = r1.getHorizontalEdgePadding$storyly_release()
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r2 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r2 = r2.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r2 = r2.getBar$storyly_release()
                int r2 = r2.getVerticalEdgePadding$storyly_release()
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r3 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r3 = r3.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r3 = r3.getBar$storyly_release()
                int r3 = r3.getSection$storyly_release()
                int r3 = r9 % r3
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r4 = r7.f4663a
                kotlin.jvm.functions.Function0 r4 = r4.getRetrieveCombinedGroupSize$storyly_release()
                if (r4 != 0) goto L_0x0084
                goto L_0x008c
            L_0x0084:
                java.lang.Object r4 = r4.invoke()
                java.lang.Integer r4 = (java.lang.Integer) r4
                if (r4 != 0) goto L_0x008e
            L_0x008c:
                r4 = 0
                goto L_0x0092
            L_0x008e:
                int r4 = r4.intValue()
            L_0x0092:
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r5 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r5 = r5.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r5 = r5.getBar$storyly_release()
                com.appsamurai.storyly.StoryGroupListOrientation r5 = r5.getOrientation$storyly_release()
                com.appsamurai.storyly.StoryGroupListOrientation r6 = com.appsamurai.storyly.StoryGroupListOrientation.Horizontal
                if (r5 != r6) goto L_0x00b1
                int r5 = r3 * r0
                int r5 = r5 / r10
                r8.top = r5
                int r3 = r3 + 1
                int r3 = r3 * r0
                int r3 = r3 / r10
                int r0 = r0 - r3
                r8.bottom = r0
                r8.left = r11
                goto L_0x00bf
            L_0x00b1:
                int r5 = r3 * r11
                int r5 = r5 / r10
                r8.left = r5
                int r3 = r3 + 1
                int r3 = r3 * r11
                int r3 = r3 / r10
                int r11 = r11 - r3
                r8.right = r11
                r8.top = r0
            L_0x00bf:
                if (r9 < 0) goto L_0x00e2
                if (r9 >= r10) goto L_0x00e2
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r9 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r9 = r9.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r9 = r9.getBar$storyly_release()
                com.appsamurai.storyly.StoryGroupListOrientation r9 = r9.getOrientation$storyly_release()
                if (r9 != r6) goto L_0x00df
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r7 = r7.f4663a
                boolean r7 = com.appsamurai.storyly.util.l.a(r7)
                if (r7 == 0) goto L_0x00dc
                r8.left = r1
                goto L_0x0111
            L_0x00dc:
                r8.right = r1
                goto L_0x0111
            L_0x00df:
                r8.top = r2
                goto L_0x0111
            L_0x00e2:
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r10 = r7.f4663a
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView$b r10 = r10.f4648i
                java.util.List r10 = r10.a()
                int r10 = r10.size()
                int r10 = r10 + r4
                int r10 = r10 + -1
                if (r9 != r10) goto L_0x0111
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r9 = r7.f4663a
                com.appsamurai.storyly.config.StorylyConfig r9 = r9.f4640a
                com.appsamurai.storyly.config.styling.bar.StorylyBarStyling r9 = r9.getBar$storyly_release()
                com.appsamurai.storyly.StoryGroupListOrientation r9 = r9.getOrientation$storyly_release()
                if (r9 != r6) goto L_0x010f
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r7 = r7.f4663a
                boolean r7 = com.appsamurai.storyly.util.l.a(r7)
                if (r7 == 0) goto L_0x010c
                r8.right = r1
                goto L_0x0111
            L_0x010c:
                r8.left = r1
                goto L_0x0111
            L_0x010f:
                r8.bottom = r2
            L_0x0111:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylylist.StorylyListRecyclerView.d.getItemOffsets(android.graphics.Rect, android.view.View, androidx.recyclerview.widget.RecyclerView, androidx.recyclerview.widget.RecyclerView$State):void");
        }
    }

    public interface f {
    }

    public static final class g extends Lambda implements Function0<com.appsamurai.storyly.analytics.d> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ StorylyListRecyclerView f4671a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(StorylyListRecyclerView storylyListRecyclerView) {
            super(0);
            this.f4671a = storylyListRecyclerView;
        }

        public Object invoke() {
            return new com.appsamurai.storyly.analytics.d(this.f4671a.f4641b);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public StorylyListRecyclerView(@NotNull Context context, @NotNull StorylyConfig storylyConfig, @NotNull com.appsamurai.storyly.analytics.e eVar) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        Intrinsics.checkNotNullParameter(eVar, "storylyTracker");
        this.f4640a = storylyConfig;
        this.f4641b = eVar;
        StoryGroupListOrientation orientation$storyly_release = storylyConfig.getBar$storyly_release().getOrientation$storyly_release();
        StoryGroupListOrientation storyGroupListOrientation = StoryGroupListOrientation.Horizontal;
        setLayoutParams(orientation$storyly_release == storyGroupListOrientation ? new FrameLayout.LayoutParams(-1, -2) : new FrameLayout.LayoutParams(-2, -1));
        m0 storylyStyle$storyly_release = storylyConfig.getStorylyStyle$storyly_release();
        if ((storylyStyle$storyly_release == null ? null : storylyStyle$storyly_release.a()) == null) {
            storylyConfig.getGroup$storyly_release().getIconBorderAnimation$storyly_release();
        }
        setId(R.id.st_storyly_list_recycler_view);
        setBackgroundColor(0);
        setHasFixedSize(true);
        this.f4647h = new e(this);
        this.f4648i = new b(this);
        AnonymousClass1 r4 = new GridLayoutManager(this, context, storylyConfig.getBar$storyly_release().getSection$storyly_release()) {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ StorylyListRecyclerView f4656a;

            {
                this.f4656a = r1;
            }

            public boolean getReverseLayout() {
                return true;
            }

            public void onLayoutCompleted(@Nullable RecyclerView.State state) {
                super.onLayoutCompleted(state);
                if (this.f4656a.getScrollState() == 0) {
                    this.f4656a.getStoryGroupImpressionManager().a(this.f4656a.getVisibleStorylyGroupItems());
                }
                StorylyListRecyclerView.a(this.f4656a);
            }

            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        };
        r4.setOrientation(storylyConfig.getBar$storyly_release().getOrientation$storyly_release() == storyGroupListOrientation ? 0 : 1);
        Unit unit = Unit.INSTANCE;
        setLayoutManager(r4);
        addItemDecoration(new d(this));
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setOverScrollMode(2);
        setAdapter(new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{this.f4648i, this.f4647h}));
        setLayoutDirection(storylyConfig.getLayoutDirection$storyly_release().getLayoutDirection$storyly_release());
        addOnScrollListener(new a(this));
    }

    public static final void a(StorylyListRecyclerView storylyListRecyclerView) {
        List<MomentsItem> list = storylyListRecyclerView.f4650k;
        if (list != null) {
            storylyListRecyclerView.f4650k = null;
            storylyListRecyclerView.setMomentsAdapterData$storyly_release(list);
        }
        List<v> list2 = storylyListRecyclerView.f4649j;
        if (list2 != null) {
            storylyListRecyclerView.f4649j = null;
            storylyListRecyclerView.setStorylyAdapterData$storyly_release(list2);
        }
    }

    /* access modifiers changed from: private */
    public final com.appsamurai.storyly.analytics.d getStoryGroupImpressionManager() {
        return (com.appsamurai.storyly.analytics.d) this.f4642c.getValue();
    }

    /* access modifiers changed from: private */
    public final List<v> getVisibleStorylyGroupItems() {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        Integer num = null;
        GridLayoutManager gridLayoutManager = layoutManager instanceof GridLayoutManager ? (GridLayoutManager) layoutManager : null;
        Integer valueOf = gridLayoutManager == null ? null : Integer.valueOf(gridLayoutManager.findFirstVisibleItemPosition());
        if (valueOf == null) {
            return CollectionsKt.emptyList();
        }
        int intValue = valueOf.intValue();
        RecyclerView.LayoutManager layoutManager2 = getLayoutManager();
        GridLayoutManager gridLayoutManager2 = layoutManager2 instanceof GridLayoutManager ? (GridLayoutManager) layoutManager2 : null;
        if (gridLayoutManager2 != null) {
            num = Integer.valueOf(gridLayoutManager2.findLastVisibleItemPosition());
        }
        if (num == null) {
            return CollectionsKt.emptyList();
        }
        int intValue2 = num.intValue();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4648i.a());
        arrayList.addAll(getStorylyGroupItems$storyly_release());
        if (!RangesKt.until(0, arrayList.size()).contains(intValue2)) {
            return CollectionsKt.emptyList();
        }
        if (!RangesKt.until(0, arrayList.size()).contains(intValue)) {
            return CollectionsKt.emptyList();
        }
        List slice = CollectionsKt.slice(arrayList, new IntRange(intValue, intValue2));
        ArrayList arrayList2 = new ArrayList();
        for (Object next : slice) {
            if (next instanceof v) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    @Nullable
    public final Function1<Function0<Unit>, Unit> getOnScrollStarted$storyly_release() {
        return this.f4644e;
    }

    @Nullable
    public final Function2<v, Integer, Unit> getOnStorylyGroupSelected$storyly_release() {
        return this.f4643d;
    }

    @Nullable
    public final String getPaginationDataStateId$storyly_release() {
        return this.f4651l;
    }

    @Nullable
    public final Function0<Integer> getRetrieveCombinedGroupSize$storyly_release() {
        return this.f4646g;
    }

    @Nullable
    public final Function0<Integer> getRetrieveSessionNotSeenCount$storyly_release() {
        return this.f4645f;
    }

    @NotNull
    public final List<v> getStorylyGroupItems$storyly_release() {
        return this.f4647h.a();
    }

    public final void setMomentsAdapterData$storyly_release(@NotNull List<MomentsItem> list) {
        Intrinsics.checkNotNullParameter(list, "momentsItems");
        post(new V.a(this, (List) list));
    }

    public final void setOnScrollStarted$storyly_release(@Nullable Function1<? super Function0<Unit>, Unit> function1) {
        this.f4644e = function1;
    }

    public final void setOnStorylyGroupSelected$storyly_release(@Nullable Function2<? super v, ? super Integer, Unit> function2) {
        this.f4643d = function2;
    }

    public final void setPaginationDataStateId$storyly_release(@Nullable String str) {
        this.f4651l = str;
    }

    public final void setRetrieveCombinedGroupSize$storyly_release(@Nullable Function0<Integer> function0) {
        this.f4646g = function0;
    }

    public final void setRetrieveSessionNotSeenCount$storyly_release(@Nullable Function0<Integer> function0) {
        this.f4645f = function0;
    }

    public final void setStorylyAdapterData$storyly_release(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "storylyGroupItems");
        if (isComputingLayout()) {
            this.f4649j = list;
            return;
        }
        this.f4649j = null;
        if (this.f4648i.a().isEmpty() || !CollectionsKt.filterNotNull(list).isEmpty()) {
            e eVar = this.f4647h;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (v vVar : list) {
                arrayList.add(vVar == null ? null : vVar.a());
            }
            eVar.getClass();
            Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
            eVar.f4665a.setValue(eVar, e.f4664c[0], arrayList);
        } else {
            e eVar2 = this.f4647h;
            List emptyList = CollectionsKt.emptyList();
            eVar2.getClass();
            Intrinsics.checkNotNullParameter(emptyList, "<set-?>");
            eVar2.f4665a.setValue(eVar2, e.f4664c[0], emptyList);
        }
        post(new V.a((List) list, this));
    }

    public final class b extends RecyclerView.Adapter<a> implements c {

        /* renamed from: b  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f4658b = {androidx.compose.ui.autofill.a.m(b.class, "momentsItems", "getMomentsItems()Ljava/util/List;", 0)};
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ReadWriteProperty f4659a;

        public final class a extends RecyclerView.ViewHolder {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public a(@NotNull b bVar, d dVar) {
                super(dVar);
                Intrinsics.checkNotNullParameter(bVar, "this$0");
                Intrinsics.checkNotNullParameter(dVar, "momentsItemView");
            }
        }

        /* renamed from: com.appsamurai.storyly.storylylist.StorylyListRecyclerView$b$b  reason: collision with other inner class name */
        public static final class C0013b extends ObservableProperty<List<? extends MomentsItem>> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Object f4660a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ StorylyListRecyclerView f4661b;

            /* renamed from: c  reason: collision with root package name */
            public final /* synthetic */ b f4662c;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0013b(Object obj, Object obj2, StorylyListRecyclerView storylyListRecyclerView, b bVar) {
                super(obj2);
                this.f4660a = obj;
                this.f4661b = storylyListRecyclerView;
                this.f4662c = bVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, List<? extends MomentsItem> list, List<? extends MomentsItem> list2) {
                Intrinsics.checkNotNullParameter(kProperty, "property");
                List list3 = list2;
                List list4 = list;
                StorylyListRecyclerView storylyListRecyclerView = this.f4661b;
                if (storylyListRecyclerView.f4649j == null) {
                    storylyListRecyclerView.setStorylyAdapterData$storyly_release(storylyListRecyclerView.f4647h.a());
                }
                if (b.a(this.f4662c, list4, list3)) {
                    StorylyListRecyclerView.a(this.f4661b);
                } else {
                    b bVar = this.f4662c;
                    b bVar2 = this.f4661b.f4648i;
                    bVar.getClass();
                    Intrinsics.checkNotNullParameter(bVar, "this");
                    Intrinsics.checkNotNullParameter(bVar2, "receiver");
                    Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
                    Intrinsics.checkNotNullParameter(list3, "new");
                    DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new e(list4, list3, bVar), true);
                    Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : ViewHolder> Ada…UpdatesTo(this)\n        }");
                    calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) bVar2);
                }
                this.f4661b.smoothScrollToPosition(0);
            }
        }

        public b(StorylyListRecyclerView storylyListRecyclerView) {
            Intrinsics.checkNotNullParameter(storylyListRecyclerView, "this$0");
            Delegates delegates = Delegates.INSTANCE;
            List emptyList = CollectionsKt.emptyList();
            this.f4659a = new C0013b(emptyList, emptyList, storylyListRecyclerView, this);
        }

        public boolean a(@Nullable MomentsItem momentsItem, @Nullable MomentsItem momentsItem2) {
            String str;
            Intrinsics.checkNotNullParameter(this, "this");
            String str2 = null;
            if (momentsItem == null) {
                str = null;
            } else {
                str = momentsItem.getTag$storyly_release();
            }
            if (momentsItem2 != null) {
                str2 = momentsItem2.getTag$storyly_release();
            }
            return Intrinsics.areEqual((Object) str, (Object) str2);
        }

        public int getItemCount() {
            return a().size();
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
            MomentsItem momentsItem;
            View momentsView;
            a aVar = (a) viewHolder;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            if ((aVar.itemView instanceof d) && (momentsItem = a().get(i3)) != null && (momentsView = momentsItem.getMomentsView()) != null) {
                ((d) aVar.itemView).setMomentsView$storyly_release(momentsView);
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            Context context = viewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "parent.context");
            return new a(this, new d(context, (AttributeSet) null, 0));
        }

        public static final boolean a(b bVar, List list, List list2) {
            bVar.getClass();
            if (list.size() != list2.size()) {
                return false;
            }
            int size = list.size() - 1;
            if (size >= 0) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + 1;
                    if (!bVar.a((MomentsItem) list.get(i3), (MomentsItem) list2.get(i3))) {
                        return false;
                    }
                    if (i4 > size) {
                        break;
                    }
                    i3 = i4;
                }
            }
            return true;
        }

        @NotNull
        public final List<MomentsItem> a() {
            return (List) this.f4659a.getValue(this, f4658b[0]);
        }
    }

    public static final void a(List list, StorylyListRecyclerView storylyListRecyclerView) {
        Intrinsics.checkNotNullParameter(list, "$storylyGroupItems");
        Intrinsics.checkNotNullParameter(storylyListRecyclerView, "this$0");
        int indexOf = list.indexOf(CollectionsKt.lastOrNull(storylyListRecyclerView.getVisibleStorylyGroupItems()));
        Integer valueOf = Integer.valueOf(indexOf);
        if (indexOf == -1) {
            valueOf = null;
        }
        if (valueOf != null) {
            storylyListRecyclerView.f4647h.a(valueOf.intValue());
        }
    }

    public static final void a(StorylyListRecyclerView storylyListRecyclerView, List list) {
        Intrinsics.checkNotNullParameter(storylyListRecyclerView, "this$0");
        Intrinsics.checkNotNullParameter(list, "$momentsItems");
        if (storylyListRecyclerView.isComputingLayout()) {
            storylyListRecyclerView.f4650k = list;
            return;
        }
        storylyListRecyclerView.f4650k = null;
        b bVar = storylyListRecyclerView.f4648i;
        bVar.getClass();
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        bVar.f4659a.setValue(bVar, b.f4658b[0], list);
    }

    public final void a() {
        for (View next : ViewGroupKt.getChildren(this)) {
            a aVar = null;
            h hVar = next instanceof h ? (h) next : null;
            ViewParent storyGroupView$storyly_release = hVar == null ? null : hVar.getStoryGroupView$storyly_release();
            if (storyGroupView$storyly_release instanceof a) {
                aVar = (a) storyGroupView$storyly_release;
            }
            if (aVar != null) {
                aVar.c();
            }
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final class e extends RecyclerView.Adapter<a> implements f {

        /* renamed from: c  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f4664c = {androidx.compose.ui.autofill.a.m(e.class, "storylyGroupItems", "getStorylyGroupItems()Ljava/util/List;", 0)};
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ReadWriteProperty f4665a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ StorylyListRecyclerView f4666b;

        public final class a extends RecyclerView.ViewHolder {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public a(@NotNull e eVar, h hVar) {
                super(hVar);
                Intrinsics.checkNotNullParameter(eVar, "this$0");
                Intrinsics.checkNotNullParameter(hVar, "storylyListView");
            }
        }

        public static final class b extends Lambda implements Function0<Unit> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ StorylyListRecyclerView f4667a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public b(StorylyListRecyclerView storylyListRecyclerView) {
                super(0);
                this.f4667a = storylyListRecyclerView;
            }

            public Object invoke() {
                this.f4667a.f4653n = false;
                return Unit.INSTANCE;
            }
        }

        public static final class c extends ObservableProperty<List<? extends v>> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Object f4668a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ StorylyListRecyclerView f4669b;

            /* renamed from: c  reason: collision with root package name */
            public final /* synthetic */ e f4670c;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public c(Object obj, Object obj2, StorylyListRecyclerView storylyListRecyclerView, e eVar) {
                super(obj2);
                this.f4668a = obj;
                this.f4669b = storylyListRecyclerView;
                this.f4670c = eVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, List<? extends v> list, List<? extends v> list2) {
                int i3;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                List list3 = list2;
                List list4 = list;
                StorylyListRecyclerView storylyListRecyclerView = this.f4669b;
                List<v> a2 = this.f4670c.a();
                ArrayList arrayList = new ArrayList();
                for (v vVar : a2) {
                    if (vVar != null) {
                        arrayList.add(vVar);
                    }
                }
                ListIterator listIterator = arrayList.listIterator(arrayList.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (!((v) listIterator.previous()).f4237q) {
                            i3 = listIterator.nextIndex();
                            break;
                        }
                    } else {
                        i3 = -1;
                        break;
                    }
                }
                storylyListRecyclerView.f4652m = Integer.valueOf(i3);
                if (e.a(this.f4670c, list4, list3)) {
                    StorylyListRecyclerView.a(this.f4669b);
                    return;
                }
                e eVar = this.f4670c;
                e eVar2 = this.f4669b.f4647h;
                eVar.getClass();
                Intrinsics.checkNotNullParameter(eVar, "this");
                Intrinsics.checkNotNullParameter(eVar2, "receiver");
                Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
                Intrinsics.checkNotNullParameter(list3, "new");
                DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new g(list4, list3, eVar), true);
                Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : ViewHolder> Ada…UpdatesTo(this)\n        }");
                calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) eVar2);
            }
        }

        public e(StorylyListRecyclerView storylyListRecyclerView) {
            Intrinsics.checkNotNullParameter(storylyListRecyclerView, "this$0");
            this.f4666b = storylyListRecyclerView;
            Delegates delegates = Delegates.INSTANCE;
            ArrayList arrayList = new ArrayList(4);
            for (int i3 = 0; i3 < 4; i3++) {
                arrayList.add((Object) null);
            }
            this.f4665a = new c(arrayList, arrayList, this.f4666b, this);
        }

        public boolean a(@Nullable v vVar, @Nullable v vVar2) {
            String str;
            Boolean bool;
            Boolean bool2;
            String str2;
            String str3;
            String str4;
            Boolean bool3;
            Intrinsics.checkNotNullParameter(this, "this");
            Boolean bool4 = null;
            if (vVar == null) {
                str = null;
            } else {
                str = vVar.f4221a;
            }
            if (Intrinsics.areEqual((Object) str, (Object) vVar2 == null ? null : vVar2.f4221a)) {
                if (vVar == null) {
                    bool = null;
                } else {
                    bool = Boolean.valueOf(vVar.f4237q);
                }
                if (vVar2 == null) {
                    bool2 = null;
                } else {
                    bool2 = Boolean.valueOf(vVar2.f4237q);
                }
                if (Intrinsics.areEqual((Object) bool, (Object) bool2)) {
                    if (vVar == null) {
                        str2 = null;
                    } else {
                        str2 = vVar.f4222b;
                    }
                    if (Intrinsics.areEqual((Object) str2, (Object) vVar2 == null ? null : vVar2.f4222b)) {
                        if (vVar == null) {
                            str3 = null;
                        } else {
                            str3 = vVar.f4223c;
                        }
                        if (Intrinsics.areEqual((Object) str3, (Object) vVar2 == null ? null : vVar2.f4223c)) {
                            if (vVar == null) {
                                str4 = null;
                            } else {
                                str4 = vVar.f4224d;
                            }
                            if (Intrinsics.areEqual((Object) str4, (Object) vVar2 == null ? null : vVar2.f4224d)) {
                                if (vVar == null) {
                                    bool3 = null;
                                } else {
                                    bool3 = Boolean.valueOf(vVar.f4230j);
                                }
                                if (vVar2 != null) {
                                    bool4 = Boolean.valueOf(vVar2.f4230j);
                                }
                                if (Intrinsics.areEqual((Object) bool3, (Object) bool4)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

        public int getItemCount() {
            return a().size();
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
            a aVar = (a) viewHolder;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            if (aVar.itemView instanceof h) {
                v vVar = a().get(i3);
                StoryGroupView storyGroupView$storyly_release = ((h) aVar.itemView).getStoryGroupView$storyly_release();
                String str = null;
                a aVar2 = storyGroupView$storyly_release instanceof a ? (a) storyGroupView$storyly_release : null;
                if (aVar2 != null) {
                    aVar2.setStorylyGroupItem$storyly_release(vVar);
                }
                ((h) aVar.itemView).setStorylyGroupItem(vVar);
                StorylyListRecyclerView storylyListRecyclerView = this.f4666b;
                View view = aVar.itemView;
                Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
                storylyListRecyclerView.getClass();
                Intrinsics.checkNotNullParameter(view, "itemView");
                if (view instanceof h) {
                    StoryGroupView storyGroupView$storyly_release2 = ((h) view).getStoryGroupView$storyly_release();
                    a aVar3 = storyGroupView$storyly_release2 instanceof a ? (a) storyGroupView$storyly_release2 : null;
                    m0 storylyStyle$storyly_release = storylyListRecyclerView.f4640a.getStorylyStyle$storyly_release();
                    StoryGroupAnimation storyGroupAnimation = storylyStyle$storyly_release == null ? null : storylyStyle$storyly_release.f3867f;
                    if (storyGroupAnimation == null) {
                        storyGroupAnimation = storylyListRecyclerView.f4640a.getGroup$storyly_release().getIconBorderAnimation$storyly_release();
                    }
                    boolean z2 = false;
                    boolean z3 = storyGroupAnimation == StoryGroupAnimation.Disabled;
                    if (storylyListRecyclerView.f4640a.getGroup$storyly_release().getSize$storyly_release() == StoryGroupSize.Custom) {
                        z2 = true;
                    }
                    boolean contains = storylyListRecyclerView.f4655p.contains(vVar == null ? null : vVar.f4221a);
                    if (!z2 && !z3) {
                        if (!storylyListRecyclerView.f4654o && !contains) {
                            if (aVar3 != null) {
                                aVar3.f();
                            }
                            List<String> list = storylyListRecyclerView.f4655p;
                            if (vVar != null) {
                                str = vVar.f4221a;
                            }
                            list.add(str);
                        } else if (aVar3 != null) {
                            aVar3.c();
                        }
                    }
                }
            }
        }

        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
            return a(viewGroup);
        }

        public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
            a aVar = (a) viewHolder;
            Intrinsics.checkNotNullParameter(aVar, "holder");
            super.onViewAttachedToWindow(aVar);
            if (aVar.itemView instanceof h) {
                a(a().indexOf(((h) aVar.itemView).getStorylyGroupItem()));
            }
        }

        public static final boolean a(e eVar, List list, List list2) {
            eVar.getClass();
            if (list.size() != list2.size()) {
                return false;
            }
            int size = list.size() - 1;
            if (size >= 0) {
                int i3 = 0;
                while (true) {
                    int i4 = i3 + 1;
                    if (!eVar.a((v) list.get(i3), (v) list2.get(i3))) {
                        return false;
                    }
                    if (i4 > size) {
                        break;
                    }
                    i3 = i4;
                }
            }
            return true;
        }

        @NotNull
        public final List<v> a() {
            return (List) this.f4665a.getValue(this, f4664c[0]);
        }

        @NotNull
        public a a(@NotNull ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            Context context = viewGroup.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "parent.context");
            h hVar = new h(context, (AttributeSet) null, 0, this.f4666b.f4640a);
            hVar.setOnClickListener(new V.b(hVar, this, this.f4666b));
            l.a(hVar, new com.appsamurai.storyly.util.ui.f(new f(this)));
            return new a(this, hVar);
        }

        public static final void a(h hVar, e eVar, StorylyListRecyclerView storylyListRecyclerView, View view) {
            String str;
            StorylyListRecyclerView storylyListRecyclerView2 = storylyListRecyclerView;
            Intrinsics.checkNotNullParameter(hVar, "$storylyGroupView");
            Intrinsics.checkNotNullParameter(eVar, "this$0");
            Intrinsics.checkNotNullParameter(storylyListRecyclerView2, "this$1");
            v storylyGroupItem = hVar.getStorylyGroupItem();
            if (storylyGroupItem != null) {
                Iterator<v> it = eVar.a().iterator();
                int i3 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i3 = -1;
                        break;
                    }
                    v next = it.next();
                    if (next == null) {
                        str = null;
                    } else {
                        str = next.f4221a;
                    }
                    if (Intrinsics.areEqual((Object) str, (Object) storylyGroupItem.f4221a)) {
                        break;
                    }
                    i3++;
                }
                int i4 = i3;
                com.appsamurai.storyly.analytics.e eVar2 = storylyListRecyclerView2.f4641b;
                com.appsamurai.storyly.analytics.a aVar = com.appsamurai.storyly.analytics.a.Selected;
                com.appsamurai.storyly.analytics.a aVar2 = aVar;
                v vVar = storylyGroupItem;
                com.appsamurai.storyly.analytics.e.a(eVar2, aVar2, vVar, storylyGroupItem.f4226f.get(storylyGroupItem.b()), (b0) null, (StoryComponent) null, com.appsamurai.storyly.analytics.f.a(eVar.a(), storylyGroupItem, storylyListRecyclerView2.f4640a), (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4056);
                Function2<v, Integer, Unit> onStorylyGroupSelected$storyly_release = storylyListRecyclerView.getOnStorylyGroupSelected$storyly_release();
                if (onStorylyGroupSelected$storyly_release != null) {
                    onStorylyGroupSelected$storyly_release.invoke(storylyGroupItem, Integer.valueOf(i4));
                }
                if (!storylyListRecyclerView2.f4654o) {
                    storylyListRecyclerView2.f4654o = true;
                    storylyListRecyclerView.a();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
            r0 = r0.invoke();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void a(int r4) {
            /*
                r3 = this;
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r0 = r3.f4666b
                kotlin.jvm.functions.Function0 r0 = r0.getRetrieveSessionNotSeenCount$storyly_release()
                r1 = 0
                if (r0 != 0) goto L_0x000a
                goto L_0x0012
            L_0x000a:
                java.lang.Object r0 = r0.invoke()
                java.lang.Integer r0 = (java.lang.Integer) r0
                if (r0 != 0) goto L_0x0014
            L_0x0012:
                r0 = r1
                goto L_0x0018
            L_0x0014:
                int r0 = r0.intValue()
            L_0x0018:
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r2 = r3.f4666b
                java.lang.Integer r2 = r2.f4652m
                if (r2 != 0) goto L_0x001f
                goto L_0x0023
            L_0x001f:
                int r1 = r2.intValue()
            L_0x0023:
                java.util.List r2 = r3.a()
                int r2 = r2.size()
                if (r2 < r0) goto L_0x002e
                r1 = r2
            L_0x002e:
                int r1 = r1 + -4
                if (r4 < r1) goto L_0x004c
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r4 = r3.f4666b
                boolean r0 = r4.f4653n
                if (r0 != 0) goto L_0x004c
                r0 = 1
                r4.f4653n = r0
                kotlin.jvm.functions.Function1 r4 = r4.getOnScrollStarted$storyly_release()
                if (r4 != 0) goto L_0x0042
                goto L_0x004c
            L_0x0042:
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView$e$b r0 = new com.appsamurai.storyly.storylylist.StorylyListRecyclerView$e$b
                com.appsamurai.storyly.storylylist.StorylyListRecyclerView r3 = r3.f4666b
                r0.<init>(r3)
                r4.invoke(r0)
            L_0x004c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylylist.StorylyListRecyclerView.e.a(int):void");
        }
    }
}
