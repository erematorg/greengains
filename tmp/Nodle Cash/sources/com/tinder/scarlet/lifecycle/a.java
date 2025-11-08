package com.tinder.scarlet.lifecycle;

import io.reactivex.functions.BiPredicate;
import kotlin.jvm.functions.Function2;

public final /* synthetic */ class a implements BiPredicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7645a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Function2 f7646b;

    public /* synthetic */ a(Function2 function2, int i3) {
        this.f7645a = i3;
        this.f7646b = function2;
    }

    public final boolean test(Object obj, Object obj2) {
        int i3 = this.f7645a;
        Function2 function2 = this.f7646b;
        switch (i3) {
            case 0:
                return LifecycleRegistry._init_$lambda$0(function2, obj, obj2);
            default:
                return LifecycleRegistry._init_$lambda$2(function2, obj, obj2);
        }
    }
}
