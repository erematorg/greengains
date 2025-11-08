package com.appsamurai.storyly.storylypresenter.share;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.databinding.i;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a extends RecyclerView.Adapter<C0030a> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<b> f5301a = CollectionsKt.emptyList();
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    public Function1<? super b, Unit> f5302b;

    /* renamed from: com.appsamurai.storyly.storylypresenter.share.a$a  reason: collision with other inner class name */
    public final class C0030a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final i f5303a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0030a(@NotNull a aVar, i iVar) {
            super(iVar.a());
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(iVar, "binding");
            this.f5303a = iVar;
            iVar.a().setOnClickListener(new B0.a(aVar, this, 1));
        }

        public static final void a(a aVar, C0030a aVar2, View view) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar2, "this$1");
            Function1<? super b, Unit> function1 = aVar.f5302b;
            if (function1 != null) {
                function1.invoke(aVar.f5301a.get(aVar2.getBindingAdapterPosition()));
            }
        }
    }

    public int getItemCount() {
        return this.f5301a.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i3) {
        C0030a aVar = (C0030a) viewHolder;
        Intrinsics.checkNotNullParameter(aVar, "holder");
        b bVar = this.f5301a.get(i3);
        aVar.getClass();
        Intrinsics.checkNotNullParameter(bVar, "item");
        aVar.f5303a.f4370c.setText(bVar.f5305b);
        aVar.f5303a.f4369b.setImageDrawable(AppCompatResources.getDrawable(aVar.itemView.getContext(), bVar.f5304a));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i3) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.st_share_list_item, viewGroup, false);
        int i4 = R.id.st_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(inflate, i4);
        if (imageView != null) {
            i4 = R.id.st_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(inflate, i4);
            if (textView != null) {
                i iVar = new i((LinearLayout) inflate, imageView, textView);
                Intrinsics.checkNotNullExpressionValue(iVar, "inflate(LayoutInflater.f….context), parent, false)");
                return new C0030a(this, iVar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i4)));
    }
}
