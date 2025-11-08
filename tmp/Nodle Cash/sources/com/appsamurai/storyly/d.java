package com.appsamurai.storyly;

import com.appsamurai.storyly.data.managers.ad.a;
import com.appsamurai.storyly.data.managers.ad.b;
import com.appsamurai.storyly.data.v;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class d extends Lambda implements Function1<v, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f3590a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public d(StorylyView storylyView) {
        super(1);
        this.f3590a = storylyView;
    }

    public Object invoke(Object obj) {
        v vVar = (v) obj;
        Intrinsics.checkNotNullParameter(vVar, "storylyGroupItem");
        b access$getAdViewManager = this.f3590a.getAdViewManager();
        access$getAdViewManager.getClass();
        Intrinsics.checkNotNullParameter(vVar, "storylyGroupItem");
        Iterator<v> it = access$getAdViewManager.f3878c.iterator();
        int i3 = 0;
        while (true) {
            if (!it.hasNext()) {
                i3 = -1;
                break;
            } else if (Intrinsics.areEqual((Object) it.next().f4221a, (Object) vVar.f4221a)) {
                break;
            } else {
                i3++;
            }
        }
        access$getAdViewManager.f3880e = Math.max(access$getAdViewManager.f3880e, i3);
        access$getAdViewManager.a(access$getAdViewManager.f3879d, i3);
        if (access$getAdViewManager.f3882g.contains(vVar.f4221a)) {
            access$getAdViewManager.f3876a.invoke(new a(access$getAdViewManager, vVar, i3));
        }
        return Unit.INSTANCE;
    }
}
