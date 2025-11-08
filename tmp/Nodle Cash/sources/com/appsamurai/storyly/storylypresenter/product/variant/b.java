package com.appsamurai.storyly.storylypresenter.product.variant;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.managers.product.STRProductVariant;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

public final class b extends RecyclerView.Adapter<a> implements g {

    /* renamed from: g  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5262g;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f5263a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ReadWriteProperty f5264b;

    /* renamed from: c  reason: collision with root package name */
    public int f5265c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f5266d = true;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public Function1<? super STRProductVariant, Unit> f5267e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final ReadWriteProperty f5268f = new c(0, 0, this);

    /* renamed from: com.appsamurai.storyly.storylypresenter.product.variant.b$b  reason: collision with other inner class name */
    public static final class C0029b extends ObservableProperty<List<? extends STRProductVariant>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f5271a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5272b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0029b(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f5271a = obj;
            this.f5272b = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends STRProductVariant> list, List<? extends STRProductVariant> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List list4 = list;
            b bVar = this.f5272b;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(bVar, "this");
            Intrinsics.checkNotNullParameter(bVar, "receiver");
            Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
            Intrinsics.checkNotNullParameter(list3, "new");
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new f(list4, list3, bVar), true);
            Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : RecyclerView.Vi…atchUpdatesTo(this)\n    }");
            calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) bVar);
        }
    }

    public static final class c extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f5273a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5274b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public c(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f5273a = obj;
            this.f5274b = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            int intValue = num2.intValue();
            this.f5274b.notifyItemChanged(num.intValue());
            this.f5274b.notifyItemChanged(intValue);
        }
    }

    static {
        Class<b> cls = b.class;
        f5262g = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, FirebaseAnalytics.Param.ITEMS, "getItems()Ljava/util/List;", 0), androidx.compose.ui.autofill.a.m(cls, "selectedIndex", "getSelectedIndex()I", 0)};
    }

    public b(@NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5263a = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        ArrayList arrayList = new ArrayList();
        this.f5264b = new C0029b(arrayList, arrayList, this);
    }

    public boolean a(@Nullable STRProductVariant sTRProductVariant, @Nullable STRProductVariant sTRProductVariant2) {
        String str;
        Intrinsics.checkNotNullParameter(this, "this");
        Boolean bool = null;
        if (sTRProductVariant == null) {
            str = null;
        } else {
            str = sTRProductVariant.getValue();
        }
        if (Intrinsics.areEqual((Object) str, (Object) sTRProductVariant2 == null ? null : sTRProductVariant2.getValue())) {
            Boolean valueOf = sTRProductVariant == null ? null : Boolean.valueOf(sTRProductVariant.isEnabled$storyly_release());
            if (sTRProductVariant2 != null) {
                bool = Boolean.valueOf(sTRProductVariant2.isEnabled$storyly_release());
            }
            if (Intrinsics.areEqual((Object) valueOf, (Object) bool)) {
                return true;
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
        aVar.a(a().get(i3));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        h hVar = new h(context, this.f5263a);
        Class cls = Integer.TYPE;
        ViewGroup.LayoutParams newInstance = ViewGroup.LayoutParams.class.getConstructor(new Class[]{cls, cls}).newInstance(new Object[]{-2, -2});
        Intrinsics.checkNotNullExpressionValue(newInstance, "layoutParams");
        Unit unit = Unit.INSTANCE;
        hVar.setLayoutParams(newInstance);
        return new a(this, hVar);
    }

    public final List<STRProductVariant> a() {
        return (List) this.f5264b.getValue(this, f5262g[0]);
    }

    public final void a(int i3) {
        this.f5268f.setValue(this, f5262g[1], Integer.valueOf(i3));
    }

    public final class a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final h f5269a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5270b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull b bVar, h hVar) {
            super(hVar);
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            Intrinsics.checkNotNullParameter(hVar, "view");
            this.f5270b = bVar;
            this.f5269a = hVar;
        }

        public static final void a(b bVar, a aVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            if (bVar.f5266d) {
                bVar.f5268f.setValue(bVar, b.f5262g[1], Integer.valueOf(aVar.getBindingAdapterPosition()));
                Function1<? super STRProductVariant, Unit> function1 = bVar.f5267e;
                if (function1 != null) {
                    function1.invoke(aVar.f5269a.getVariantItem());
                }
            }
        }

        public final void a(@NotNull STRProductVariant sTRProductVariant) {
            Intrinsics.checkNotNullParameter(sTRProductVariant, "item");
            int bindingAdapterPosition = getBindingAdapterPosition();
            b bVar = this.f5270b;
            boolean z2 = true;
            if (bindingAdapterPosition != ((Number) bVar.f5268f.getValue(bVar, b.f5262g[1])).intValue()) {
                z2 = false;
            }
            h hVar = this.f5269a;
            b bVar2 = this.f5270b;
            hVar.a();
            if (sTRProductVariant.getSourceType$storyly_release() == com.appsamurai.storyly.data.managers.product.c.Raw) {
                hVar.b(sTRProductVariant, bVar2.f5265c, z2);
            } else {
                hVar.a(sTRProductVariant, bVar2.f5265c, z2);
            }
            hVar.setOnClickListener(new B0.a(bVar2, this, 0));
        }
    }
}
