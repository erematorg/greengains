package com.sun.jna;

import java.lang.reflect.Method;

public class CallbackResultContext extends ToNativeContext {
    private Method method;

    public CallbackResultContext(Method method2) {
        this.method = method2;
    }

    public Method getMethod() {
        return this.method;
    }
}
