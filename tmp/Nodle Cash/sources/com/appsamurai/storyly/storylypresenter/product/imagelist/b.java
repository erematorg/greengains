package com.appsamurai.storyly.storylypresenter.product.imagelist;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.web3j.ens.contracts.generated.ENSRegistryWithFallbackContract;

public final class b extends RecyclerView.Adapter<a> implements e {

    /* renamed from: b  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5140b = {androidx.compose.ui.autofill.a.m(b.class, FirebaseAnalytics.Param.ITEMS, "getItems()Ljava/util/List;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ReadWriteProperty f5141a;

    public final class a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final f f5142a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(@NotNull b bVar, f fVar) {
            super(fVar);
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            Intrinsics.checkNotNullParameter(fVar, "view");
            this.f5142a = fVar;
        }
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.product.imagelist.b$b  reason: collision with other inner class name */
    public static final class C0026b extends ObservableProperty<List<? extends String>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f5143a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ b f5144b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0026b(Object obj, Object obj2, b bVar) {
            super(obj2);
            this.f5143a = obj;
            this.f5144b = bVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends String> list, List<? extends String> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List list4 = list;
            b bVar = this.f5144b;
            bVar.getClass();
            Intrinsics.checkNotNullParameter(bVar, "this");
            Intrinsics.checkNotNullParameter(bVar, "receiver");
            Intrinsics.checkNotNullParameter(list4, ENSRegistryWithFallbackContract.FUNC_OLD);
            Intrinsics.checkNotNullParameter(list3, "new");
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new d(list4, list3, bVar), true);
            Intrinsics.checkNotNullExpressionValue(calculateDiff, "fun <T : RecyclerView.Vi…atchUpdatesTo(this)\n    }");
            calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) bVar);
        }
    }

    public b() {
        Delegates delegates = Delegates.INSTANCE;
        List emptyList = CollectionsKt.emptyList();
        this.f5141a = new C0026b(emptyList, emptyList, this);
    }

    public int getItemCount() {
        return ((List) this.f5141a.getValue(this, f5140b[0])).size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
        a aVar = (a) viewHolder;
        Intrinsics.checkNotNullParameter(aVar, "holder");
        String str = (String) ((List) this.f5141a.getValue(this, f5140b[0])).get(i3);
        aVar.getClass();
        Intrinsics.checkNotNullParameter(str, "item");
        aVar.f5142a.a();
        aVar.f5142a.setupView(str);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        Context context = viewGroup.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return new a(this, new f(context));
    }
}
