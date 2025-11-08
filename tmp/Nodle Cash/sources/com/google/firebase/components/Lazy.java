package com.google.firebase.components;

import androidx.annotation.VisibleForTesting;
import com.google.firebase.inject.Provider;

public class Lazy<T> implements Provider<T> {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    public Lazy(T t2) {
        this.instance = t2;
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
                        this.instance = t2;
                        this.provider = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return t2;
    }

    @VisibleForTesting
    public boolean isInitialized() {
        return this.instance != UNINITIALIZED;
    }

    public Lazy(Provider<T> provider2) {
        this.provider = provider2;
    }
}
