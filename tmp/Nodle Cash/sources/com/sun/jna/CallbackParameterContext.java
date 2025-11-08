package com.sun.jna;

import java.lang.reflect.Method;

public class CallbackParameterContext extends FromNativeContext {
    private Object[] args;
    private int index;
    private Method method;

    public CallbackParameterContext(Class<?> cls, Method method2, Object[] objArr, int i3) {
        super(cls);
        this.method = method2;
        this.args = objArr;
        this.index = i3;
    }

    public Object[] getArguments() {
        return this.args;
    }

    public int getIndex() {
        return this.index;
    }

    public Method getMethod() {
        return this.method;
    }
}
