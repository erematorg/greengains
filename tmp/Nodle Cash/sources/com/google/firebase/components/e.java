package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.inject.Provider;
import java.util.Map;

public final /* synthetic */ class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7051a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7052b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f7053c;

    public /* synthetic */ e(Object obj, Object obj2, int i3) {
        this.f7051a = i3;
        this.f7053c = obj;
        this.f7052b = obj2;
    }

    public final void run() {
        switch (this.f7051a) {
            case 0:
                ((OptionalProvider) this.f7053c).set((Provider) this.f7052b);
                return;
            case 1:
                ((LazySet) this.f7053c).add((Provider) this.f7052b);
                return;
            default:
                ((EventHandler) ((Map.Entry) this.f7053c).getKey()).handle((Event) this.f7052b);
                return;
        }
    }
}
