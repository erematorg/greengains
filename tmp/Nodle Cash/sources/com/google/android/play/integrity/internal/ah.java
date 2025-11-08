package com.google.android.play.integrity.internal;

public final class ah implements al {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f6829a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private volatile al f6830b;

    /* renamed from: c  reason: collision with root package name */
    private volatile Object f6831c = f6829a;

    private ah(al alVar) {
        this.f6830b = alVar;
    }

    public static al b(al alVar) {
        return alVar instanceof ah ? alVar : new ah(alVar);
    }

    public final Object a() {
        Object obj = this.f6831c;
        Object obj2 = f6829a;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.f6831c;
                    if (obj == obj2) {
                        obj = this.f6830b.a();
                        Object obj3 = this.f6831c;
                        if (obj3 != obj2) {
                            if (obj3 != obj) {
                                throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                            }
                        }
                        this.f6831c = obj;
                        this.f6830b = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return obj;
    }
}
