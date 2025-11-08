package com.appsamurai.storyly;

import com.appsamurai.storyly.data.j;
import com.appsamurai.storyly.data.managers.processing.f;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class k extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ StorylyView f4633a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ f f4634b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(StorylyView storylyView, f fVar) {
        super(0);
        this.f4633a = storylyView;
        this.f4634b = fVar;
    }

    public Object invoke() {
        StorylyProductListener storylyProductListener = this.f4633a.getStorylyProductListener();
        if (storylyProductListener != null) {
            StorylyView storylyView = this.f4633a;
            Set<j> set = this.f4634b.e().f4043c;
            ArrayList arrayList = new ArrayList();
            for (j jVar : set) {
                String str = jVar.f3790c;
                if (str != null) {
                    arrayList.add(str);
                }
            }
            storylyProductListener.storylyHydration(storylyView, arrayList);
        }
        return Unit.INSTANCE;
    }
}
