package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.graph.AbstractNetwork;
import com.google.common.graph.Graphs;

public final /* synthetic */ class c implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6904a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6905b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f6904a = i3;
        this.f6905b = obj;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6904a;
        Object obj2 = this.f6905b;
        switch (i3) {
            case 0:
                return ((AbstractNetwork.AnonymousClass1.AnonymousClass1) obj2).lambda$iterator$0(obj);
            case 1:
                return ((Graphs.TransposedGraph.AnonymousClass1) obj2).lambda$iterator$0((EndpointPair) obj);
            default:
                return AbstractValueGraph.lambda$edgeValueMap$0((ValueGraph) obj2, (EndpointPair) obj);
        }
    }
}
