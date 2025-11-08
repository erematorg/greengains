package com.appsamurai.storyly.storylypresenter;

import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.analytics.a;
import com.appsamurai.storyly.analytics.e;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.managers.product.STRCart;
import com.appsamurai.storyly.data.managers.product.STRCartItem;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.JsonObject;
import okio.Utf8;

public final class w0 extends Lambda implements Function5<a, b0, StoryComponent, JsonObject, Function1<? super Boolean, ? extends Unit>, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f6247a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public w0(o oVar) {
        super(5);
        this.f6247a = oVar;
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        a aVar = (a) obj;
        StoryComponent storyComponent = (StoryComponent) obj3;
        Intrinsics.checkNotNullParameter(aVar, "analyticsEvent");
        o oVar = this.f6247a;
        e.a(oVar.f5046a, aVar, oVar.getStorylyGroupItem$storyly_release(), this.f6247a.getStorylyItem(), (b0) obj2, storyComponent, (JsonObject) obj4, (Function1) obj5, (String) null, (Function1) null, (Function1) null, (STRCart) null, (STRCartItem) null, Utf8.MASK_2BYTES);
        v storylyGroupItem$storyly_release = this.f6247a.getStorylyGroupItem$storyly_release();
        Story story = null;
        StoryGroup d2 = storylyGroupItem$storyly_release == null ? null : storylyGroupItem$storyly_release.d();
        z h3 = this.f6247a.getStorylyItem();
        if (h3 != null) {
            story = h3.a();
        }
        if (!(d2 == null || story == null || storyComponent == null)) {
            this.f6247a.getOnStoryLayerInteraction$storyly_release().invoke(d2, story, storyComponent);
            o.a(this.f6247a);
        }
        return Unit.INSTANCE;
    }
}
