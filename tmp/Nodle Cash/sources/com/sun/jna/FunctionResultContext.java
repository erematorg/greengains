package com.sun.jna;

public class FunctionResultContext extends FromNativeContext {
    private Object[] args;
    private Function function;

    public FunctionResultContext(Class<?> cls, Function function2, Object[] objArr) {
        super(cls);
        this.function = function2;
        this.args = objArr;
    }

    public Object[] getArguments() {
        return this.args;
    }

    public Function getFunction() {
        return this.function;
    }
}
