package com.google.common.graph;

import com.google.common.base.Function;

public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6902a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Network f6903b;

    public /* synthetic */ b(Network network, int i3) {
        this.f6902a = i3;
        this.f6903b = network;
    }

    public final Object apply(Object obj) {
        int i3 = this.f6902a;
        Network network = this.f6903b;
        switch (i3) {
            case 0:
                return network.incidentNodes(obj);
            case 1:
                return network.incidentNodes(obj).source();
            default:
                return network.incidentNodes(obj).target();
        }
    }
}
