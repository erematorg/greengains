package com.google.common.graph;

import com.google.common.base.Function;

public final /* synthetic */ class e implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6908a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6909b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f6910c;

    public /* synthetic */ e(Object obj, Object obj2, int i3) {
        this.f6908a = i3;
        this.f6910c = obj;
        this.f6909b = obj2;
    }

    public final Object apply(Object obj) {
        switch (this.f6908a) {
            case 0:
                return ((Network) this.f6910c).incidentNodes(obj).adjacentNode(this.f6909b);
            default:
                return ImmutableValueGraph.lambda$connectionsOf$0((ValueGraph) this.f6910c, this.f6909b, obj);
        }
    }
}
