package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

public final /* synthetic */ class a implements ObjectConstructor {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7181a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ InstanceCreator f7182b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Type f7183c;

    public /* synthetic */ a(InstanceCreator instanceCreator, Type type, int i3) {
        this.f7181a = i3;
        this.f7182b = instanceCreator;
        this.f7183c = type;
    }

    public final Object construct() {
        switch (this.f7181a) {
            case 0:
                return this.f7182b.createInstance(this.f7183c);
            default:
                return this.f7182b.createInstance(this.f7183c);
        }
    }
}
