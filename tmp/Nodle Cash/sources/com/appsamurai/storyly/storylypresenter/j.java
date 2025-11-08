package com.appsamurai.storyly.storylypresenter;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.a;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.util.e;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;

public final class j extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d f5004a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j(d dVar) {
        super(0);
        this.f5004a = dVar;
    }

    public final void a() {
        View childAt = this.f5004a.getChildAt(0);
        o oVar = childAt instanceof o ? (o) childAt : null;
        if (oVar != null) {
            oVar.m();
        }
        v vVar = (v) e.a(this.f5004a.getStorylyGroupItems(), this.f5004a.getSelectedStorylyGroupIndex());
        if (vVar != null) {
            com.appsamurai.storyly.analytics.e.a(this.f5004a.getStorylyTracker(), a.Dismissed, vVar, vVar.f4240t, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
            this.f5004a.setLayoutManager((RecyclerView.LayoutManager) null);
            new Handler(Looper.getMainLooper()).postDelayed(new W.a(this.f5004a, 3), 200);
        }
    }

    public /* bridge */ /* synthetic */ Object invoke() {
        a();
        return Unit.INSTANCE;
    }

    public static final void a(d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "this$0");
        dVar.getOnDismissed$storyly_release().invoke();
        dVar.getBackgroundLayout().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }
}
