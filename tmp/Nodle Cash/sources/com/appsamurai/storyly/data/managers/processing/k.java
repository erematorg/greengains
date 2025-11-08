package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.data.managers.conditional.b;
import com.appsamurai.storyly.data.s;
import com.appsamurai.storyly.data.v;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class k extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ f f4014a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ StoryComponent f4015b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(f fVar, StoryComponent storyComponent) {
        super(0);
        this.f4014a = fVar;
        this.f4015b = storyComponent;
    }

    public Object invoke() {
        b b3 = this.f4014a.b();
        s sVar = this.f4014a.f3980j;
        List<v> list = sVar == null ? null : sVar.f4197a;
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        b3.a(list, this.f4015b.getId());
        return Unit.INSTANCE;
    }
}
