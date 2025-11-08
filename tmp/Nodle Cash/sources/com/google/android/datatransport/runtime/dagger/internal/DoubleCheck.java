package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider<T>, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private DoubleCheck(Provider<T> provider2) {
        this.provider = provider2;
    }

    public static <P extends Provider<T>, T> Lazy<T> lazy(P p2) {
        return p2 instanceof Lazy ? (Lazy) p2 : new DoubleCheck((Provider) Preconditions.checkNotNull(p2));
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P p2) {
        Preconditions.checkNotNull(p2);
        return p2 instanceof DoubleCheck ? p2 : new DoubleCheck(p2);
    }

    private static Object reentrantCheck(Object obj, Object obj2) {
        if (obj == UNINITIALIZED || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    public T get() {
        T t2 = this.instance;
        T t3 = UNINITIALIZED;
        if (t2 == t3) {
            synchronized (this) {
                try {
                    t2 = this.instance;
                    if (t2 == t3) {
                        t2 = this.provider.get();
                        this.instance = reentrantCheck(this.instance, t2);
                        this.provider = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return t2;
    }
}
