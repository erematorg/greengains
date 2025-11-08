package com.sun.jna;

import java.util.function.Function;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7641a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Structure f7642b;

    public /* synthetic */ a(Structure structure, int i3) {
        this.f7641a = i3;
        this.f7642b = structure;
    }

    public final Object apply(Object obj) {
        int i3 = this.f7641a;
        Structure structure = this.f7642b;
        Class cls = (Class) obj;
        switch (i3) {
            case 0:
                return structure.lambda$fieldOrder$1(cls);
            default:
                return structure.lambda$validateFields$2(cls);
        }
    }
}
