package com.appsamurai.storyly.storylypresenter;

import android.content.Context;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.a;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.util.g;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;

public final class n0 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5035a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Context f5036b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public n0(o oVar, Context context) {
        super(0);
        this.f5035a = oVar;
        this.f5036b = context;
    }

    public Object invoke() {
        o oVar = this.f5035a;
        e.a(oVar.f5046a, a.Complete, oVar.getStorylyGroupItem$storyly_release(), this.f5035a.getStorylyItem(), (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        if (!g.b(this.f5036b)) {
            this.f5035a.d();
        }
        return Unit.INSTANCE;
    }
}
