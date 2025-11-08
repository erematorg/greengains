package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.graph.AbstractBaseGraph;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6900a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ AbstractBaseGraph.AnonymousClass2 f6901b;

    public /* synthetic */ a(AbstractBaseGraph.AnonymousClass2 r12, int i3) {
        this.f6900a = i3;
        this.f6901b = r12;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6900a;
        AbstractBaseGraph.AnonymousClass2 r12 = this.f6901b;
        switch (i3) {
            case 0:
                return r12.lambda$iterator$0(obj);
            case 1:
                return r12.lambda$iterator$1(obj);
            default:
                return r12.lambda$iterator$2(obj);
        }
    }
}
