package com.appsamurai.storyly.storylypresenter.cart.list;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class b extends RecyclerView.ItemDecoration {

    /* renamed from: a  reason: collision with root package name */
    public final int f4793a;

    public b(int i3) {
        this.f4793a = i3;
    }

    public void getItemOffsets(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, RemoteConfigConstants.ResponseFieldKey.STATE);
        rect.bottom = this.f4793a;
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.top = this.f4793a;
        }
    }
}
