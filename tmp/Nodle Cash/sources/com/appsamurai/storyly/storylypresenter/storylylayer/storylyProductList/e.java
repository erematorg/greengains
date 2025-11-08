package com.appsamurai.storyly.storylypresenter.storylylayer.storylyProductList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductItem;
import com.appsamurai.storyly.util.m;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

public final class e extends RecyclerView.Adapter<a> implements c {

    /* renamed from: f  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f6060f = {androidx.compose.ui.autofill.a.m(e.class, FirebaseAnalytics.Param.ITEMS, "getItems()Ljava/util/List;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f6061a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public Function1<? super List<STRProductItem>, Unit> f6062b = b.f6068a;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f6063c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public a f6064d;

    /* renamed from: e  reason: collision with root package name */
    public int f6065e;

    public final class a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final View f6066a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ e f6067b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull e eVar, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(eVar, "this$0");
            Intrinsics.checkNotNullParameter(view, "view");
            this.f6067b = eVar;
            this.f6066a = view;
            ((f) view).setOnClickListener(new B0.a(eVar, this, 6));
        }

        public static final void a(e eVar, a aVar, View view) {
            Intrinsics.checkNotNullParameter(eVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            eVar.f6062b.invoke(eVar.a().get(aVar.getBindingAdapterPosition()));
        }
    }

    public static final class b extends Lambda implements Function1<List<? extends STRProductItem>, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f6068a = new b();

        public b() {
            super(1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            List list = (List) obj;
            return Unit.INSTANCE;
        }
    }

    public static final class c extends ObservableProperty<List<? extends List<? extends STRProductItem>>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f6069a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ e f6070b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Object obj, Object obj2, e eVar) {
            super(obj2);
            this.f6069a = obj;
            this.f6070b = eVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends List<? extends STRProductItem>> list, List<? extends List<? extends STRProductItem>> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List list4 = list;
            e eVar = this.f6070b;
            eVar.getClass();
            Intrinsics.checkNotNullParameter(eVar, "this");
            Intrinsics.checkNotNullParameter(eVar, "receiver");
            Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
            Intrinsics.checkNotNullParameter(list3, "new");
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new b(list4, list3, eVar), true);
            Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : RecyclerView.Vi…atchUpdatesTo(this)\n    }");
            calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) eVar);
        }
    }

    public e(@NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6061a = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        List emptyList = CollectionsKt.emptyList();
        this.f6063c = new c(emptyList, emptyList, this);
        this.f6064d = new a((Integer) null, (Integer) null, (Integer) null, (Integer) null, (Integer) null, (String) null, (Boolean) null, (Boolean) null, (Boolean) null, 511);
    }

    public boolean a(@Nullable List<STRProductItem> list, @Nullable List<STRProductItem> list2) {
        STRProductItem sTRProductItem;
        STRProductItem sTRProductItem2;
        String str;
        List<String> list3;
        Float f2;
        Float f3;
        Intrinsics.checkNotNullParameter(this, "this");
        Float f4 = null;
        if (list == null) {
            sTRProductItem = null;
        } else {
            sTRProductItem = (STRProductItem) CollectionsKt.firstOrNull(list);
        }
        if (list2 == null) {
            sTRProductItem2 = null;
        } else {
            sTRProductItem2 = (STRProductItem) CollectionsKt.firstOrNull(list2);
        }
        if (sTRProductItem == null) {
            str = null;
        } else {
            str = sTRProductItem.getTitle();
        }
        if (Intrinsics.areEqual((Object) str, (Object) sTRProductItem2 == null ? null : sTRProductItem2.getTitle())) {
            if (sTRProductItem == null) {
                list3 = null;
            } else {
                list3 = sTRProductItem.getImageUrls();
            }
            if (Intrinsics.areEqual((Object) list3, (Object) sTRProductItem2 == null ? null : sTRProductItem2.getImageUrls())) {
                if (sTRProductItem == null) {
                    f2 = null;
                } else {
                    f2 = Float.valueOf(sTRProductItem.getPrice());
                }
                if (Intrinsics.areEqual(f2, sTRProductItem2 == null ? null : Float.valueOf(sTRProductItem2.getPrice()))) {
                    if (sTRProductItem == null) {
                        f3 = null;
                    } else {
                        f3 = sTRProductItem.getSalesPrice();
                    }
                    if (sTRProductItem2 != null) {
                        f4 = sTRProductItem2.getSalesPrice();
                    }
                    if (Intrinsics.areEqual(f3, f4)) {
                        return true;
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
        int i4;
        STRProductItem sTRProductItem;
        String title;
        STRProductItem sTRProductItem2;
        List<String> imageUrls;
        a aVar = (a) viewHolder;
        Intrinsics.checkNotNullParameter(aVar, "holder");
        List list = a().get(i3);
        aVar.getClass();
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        View view = aVar.f6066a;
        f fVar = view instanceof f ? (f) view : null;
        if (fVar != null) {
            fVar.a();
        }
        if (fVar != null) {
            e eVar = aVar.f6067b;
            if (eVar.a().size() == 1) {
                i4 = (int) (((double) m.b().width()) * 0.9d);
            } else {
                float f2 = (float) eVar.f6065e;
                float f3 = 0.09f * f2 * ((float) 2);
                float f4 = (0.82f * f2) + f3;
                float f5 = (f2 * 2.05f) + f3;
                int width = (int) (((double) m.b().width()) * 0.2d);
                int width2 = (int) (((double) m.b().width()) * 0.4d);
                List list2 = (List) CollectionsKt.firstOrNull(eVar.a());
                int i5 = (list2 == null || (sTRProductItem2 = (STRProductItem) CollectionsKt.firstOrNull(list2)) == null || (imageUrls = sTRProductItem2.getImageUrls()) == null || imageUrls.isEmpty()) ? 0 : (int) f4;
                List list3 = (List) CollectionsKt.firstOrNull(eVar.a());
                if (!(list3 == null || (sTRProductItem = (STRProductItem) CollectionsKt.firstOrNull(list3)) == null || (title = sTRProductItem.getTitle()) == null || title.length() != 0) || Intrinsics.areEqual((Object) eVar.f6064d.f6055i, (Object) Boolean.FALSE)) {
                    String str = eVar.f6064d.f6052f;
                    if (str != null && str.length() > 0) {
                        i5 += width2;
                    }
                    i4 = Intrinsics.areEqual((Object) eVar.f6064d.f6053g, (Object) Boolean.TRUE) ? i5 + width : i5;
                } else {
                    i4 = ((int) f5) + i5;
                }
            }
            int i6 = aVar.f6067b.f6065e;
            Class cls = Integer.TYPE;
            ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{Integer.valueOf(i4), Integer.valueOf(i6)});
            Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
            Unit unit = Unit.INSTANCE;
            fVar.setLayoutParams(newInstance);
        }
        if (fVar != null) {
            fVar.a(aVar.f6067b.f6065e, (STRProductItem) CollectionsKt.firstOrNull(list), aVar.f6067b.f6064d);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return new a(this, new f(context, (AttributeSet) null, 0, this.f6061a));
    }

    public final List<List<STRProductItem>> a() {
        return (List) this.f6063c.getValue(this, f6060f[0]);
    }
}
