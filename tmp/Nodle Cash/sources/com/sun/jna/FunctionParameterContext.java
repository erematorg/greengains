package com.sun.jna;

public class FunctionParameterContext extends ToNativeContext {
    private Object[] args;
    private Function function;
    private int index;

    public FunctionParameterContext(Function function2, Object[] objArr, int i3) {
        this.function = function2;
        this.args = objArr;
        this.index = i3;
    }

    public Function getFunction() {
        return this.function;
    }

    public int getParameterIndex() {
        return this.index;
    }

    public Object[] getParameters() {
        return this.args;
    }
}
