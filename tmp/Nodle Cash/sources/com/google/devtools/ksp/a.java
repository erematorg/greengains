package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSAnnotation;
import com.tinder.scarlet.Scarlet;
import com.tinder.scarlet.internal.Service;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public final /* synthetic */ class a implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7035a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Class f7036b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7037c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Object f7038d;

    public /* synthetic */ a(Object obj, int i3, Object obj2, Class cls) {
        this.f7035a = i3;
        this.f7037c = obj;
        this.f7036b = cls;
        this.f7038d = obj2;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        switch (this.f7035a) {
            case 0:
                return UtilsKt.createInvocationHandler$lambda$8((KSAnnotation) this.f7037c, this.f7036b, (ConcurrentHashMap) this.f7038d, obj, method, objArr);
            default:
                return Scarlet.createInvocationHandler$lambda$0((Scarlet) this.f7037c, this.f7036b, (Service) this.f7038d, obj, method, objArr);
        }
    }
}
