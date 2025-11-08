package com.google.firebase.components;

public final /* synthetic */ class a implements ComponentFactory {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7043a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7044b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7043a = i3;
        this.f7044b = obj;
    }

    public final Object create(ComponentContainer componentContainer) {
        int i3 = this.f7043a;
        Object obj = this.f7044b;
        switch (i3) {
            case 0:
                return Component.lambda$of$2(obj, componentContainer);
            case 1:
                return Component.lambda$intoSet$4(obj, componentContainer);
            case 2:
                return Component.lambda$intoSet$3(obj, componentContainer);
            case 3:
                return Component.lambda$of$0(obj, componentContainer);
            default:
                return Component.lambda$of$1(obj, componentContainer);
        }
    }
}
