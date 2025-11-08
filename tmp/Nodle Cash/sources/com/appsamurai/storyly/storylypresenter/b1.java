package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.a;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;

public final class b1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f4768a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b1(o oVar) {
        super(0);
        this.f4768a = oVar;
    }

    public Object invoke() {
        o oVar = this.f4768a;
        e.a(oVar.f5046a, a.LoadFailed, oVar.getStorylyGroupItem$storyly_release(), this.f4768a.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        this.f4768a.d();
        return Unit.INSTANCE;
    }
}
