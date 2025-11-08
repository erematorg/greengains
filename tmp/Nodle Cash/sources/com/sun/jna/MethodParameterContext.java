package com.sun.jna;

import java.lang.reflect.Method;

public class MethodParameterContext extends FunctionParameterContext {
    private Method method;

    public MethodParameterContext(Function function, Object[] objArr, int i3, Method method2) {
        super(function, objArr, i3);
        this.method = method2;
    }

    public Method getMethod() {
        return this.method;
    }
}
