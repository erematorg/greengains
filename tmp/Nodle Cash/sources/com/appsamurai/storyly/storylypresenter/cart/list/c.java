package com.appsamurai.storyly.storylypresenter.cart.list;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

public final class c extends RecyclerView.Adapter<a> implements h {

    /* renamed from: d  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f4794d = {androidx.compose.ui.autofill.a.m(c.class, FirebaseAnalytics.Param.ITEMS, "getItems()Ljava/util/List;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f4795a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ReadWriteProperty f4796b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public Function3<? super STRCartItem, ? super Integer, ? super Function0<Unit>, Unit> f4797c;

    public final class a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final d f4798a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ c f4799b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull c cVar, d dVar) {
            super(dVar);
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            Intrinsics.checkNotNullParameter(dVar, "view");
            this.f4799b = cVar;
            this.f4798a = dVar;
        }
    }

    public static final class b extends ObservableProperty<List<? extends STRCartItem>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f4800a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ c f4801b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Object obj, Object obj2, c cVar) {
            super(obj2);
            this.f4800a = obj;
            this.f4801b = cVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends STRCartItem> list, List<? extends STRCartItem> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List list4 = list;
            c cVar = this.f4801b;
            cVar.getClass();
            Intrinsics.checkNotNullParameter(cVar, "this");
            Intrinsics.checkNotNullParameter(cVar, "receiver");
            Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
            Intrinsics.checkNotNullParameter(list3, "new");
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new g(list4, list3, cVar), true);
            Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : RecyclerView.Vi…atchUpdatesTo(this)\n    }");
            calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) cVar);
        }
    }

    public c(@NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f4795a = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        List emptyList = CollectionsKt.emptyList();
        this.f4796b = new b(emptyList, emptyList, this);
    }

    public boolean a(@Nullable STRCartItem sTRCartItem, @Nullable STRCartItem sTRCartItem2) {
        STRProductItem item;
        Intrinsics.checkNotNullParameter(this, "this");
        if (!(sTRCartItem == null || (item = sTRCartItem.getItem()) == null)) {
            if (item.isContentSame$storyly_release(sTRCartItem2 == null ? null : sTRCartItem2.getItem())) {
                int quantity = sTRCartItem.getQuantity();
                if (sTRCartItem2 != null && quantity == sTRCartItem2.getQuantity() && Intrinsics.areEqual(sTRCartItem.getTotalPrice(), sTRCartItem2.getTotalPrice()) && Intrinsics.areEqual(sTRCartItem.getOldTotalPrice(), sTRCartItem2.getOldTotalPrice())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getItemCount() {
        return ((List) this.f4796b.getValue(this, f4794d[0])).size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
        a aVar = (a) viewHolder;
        Intrinsics.checkNotNullParameter(aVar, "holder");
        STRCartItem sTRCartItem = (STRCartItem) ((List) this.f4796b.getValue(this, f4794d[0])).get(i3);
        aVar.getClass();
        Intrinsics.checkNotNullParameter(sTRCartItem, "item");
        d dVar = aVar.f4798a;
        c cVar = aVar.f4799b;
        dVar.a();
        dVar.setupView$storyly_release(sTRCartItem);
        dVar.setOnUpdateCart$storyly_release(cVar.f4797c);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        d dVar = new d(context, this.f4795a);
        int height = (int) (((double) m.b().height()) * 0.025d);
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = RelativeLayout.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-1, Integer.valueOf((int) (((double) m.b().height()) * 0.189d))});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) newInstance;
        layoutParams.leftMargin = height;
        layoutParams.rightMargin = height;
        Unit unit = Unit.INSTANCE;
        dVar.setLayoutParams(newInstance);
        return new a(this, dVar);
    }
}
