package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.graph.DirectedGraphConnections;

public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6906a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6907b;

    public /* synthetic */ d(Object obj, int i3) {
        this.f6906a = i3;
        this.f6907b = obj;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6906a;
        Object obj2 = this.f6907b;
        switch (i3) {
            case 0:
                return EndpointPair.ordered(obj, obj2);
            case 1:
                return EndpointPair.ordered(obj2, obj);
            case 2:
                return DirectedGraphConnections.lambda$incidentEdgeIterator$2(obj2, (DirectedGraphConnections.NodeConnection) obj);
            default:
                return EndpointPair.unordered(obj2, obj);
        }
    }
}
