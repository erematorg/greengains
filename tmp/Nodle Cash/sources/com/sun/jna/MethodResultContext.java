package com.sun.jna;

import java.lang.reflect.Method;

public class MethodResultContext extends FunctionResultContext {
    private final Method method;

    public MethodResultContext(Class<?> cls, Function function, Object[] objArr, Method method2) {
        super(cls, function, objArr);
        this.method = method2;
    }

    public Method getMethod() {
        return this.method;
    }
}
