package com.appsamurai.storyly;

import com.appsamurai.storyly.data.v;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class b extends Lambda implements Function2<v, v, Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f3545a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(StorylyView storylyView) {
        super(2);
        this.f3545a = storylyView;
    }

    public Object invoke(Object obj, Object obj2) {
        v vVar = (v) obj;
        v vVar2 = (v) obj2;
        Intrinsics.checkNotNullParameter(vVar, "groupItem");
        Intrinsics.checkNotNullParameter(vVar2, "adGroupItem");
        com.appsamurai.storyly.storylypresenter.b access$getStorylyDialog$p = this.f3545a.f3426o;
        if (access$getStorylyDialog$p != null) {
            Intrinsics.checkNotNullParameter(vVar, "groupItem");
            Intrinsics.checkNotNullParameter(vVar2, "adGroupItem");
            access$getStorylyDialog$p.b().a(vVar, vVar2);
        }
        return Unit.INSTANCE;
    }
}
