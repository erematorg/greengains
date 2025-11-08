package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.analytics.a;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;

public final class j0 extends Lambda implements Function3<Boolean, v, z, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f5005a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public j0(o oVar) {
        super(3);
        this.f5005a = oVar;
    }

    public Object invoke(Object obj, Object obj2, Object obj3) {
        e.a(this.f5005a.f5046a, ((Boolean) obj).booleanValue() ? a.Liked : a.Unliked, (v) obj2, (z) obj3, (b0) null, (StoryComponent) null, (JsonObject) null, (Function1) null, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, 4088);
        return Unit.INSTANCE;
    }
}
