package com.kenai.jffi;

public final class Function {
    private final CallContext callContext;
    final long contextAddress;
    final long functionAddress;

    public Function(long j2, Type type, Type... typeArr) {
        this(j2, type, typeArr, CallingConvention.DEFAULT, true);
    }

    @Deprecated
    public final void dispose() {
    }

    public final CallContext getCallContext() {
        return this.callContext;
    }

    public final long getContextAddress() {
        return this.contextAddress;
    }

    public final long getFunctionAddress() {
        return this.functionAddress;
    }

    public final int getParameterCount() {
        return this.callContext.getParameterCount();
    }

    public final Type getParameterType(int i3) {
        return this.callContext.getParameterType(i3);
    }

    public final int getRawParameterSize() {
        return this.callContext.getRawParameterSize();
    }

    public final Type getReturnType() {
        return this.callContext.getReturnType();
    }

    public Function(long j2, CallContext callContext2) {
        this.functionAddress = j2;
        this.callContext = callContext2;
        this.contextAddress = callContext2.getAddress();
    }

    public Function(long j2, Type type, Type[] typeArr, CallingConvention callingConvention) {
        this(j2, type, typeArr, callingConvention, true);
    }

    public Function(long j2, Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        this.functionAddress = j2;
        CallContext callContext2 = CallContext.getCallContext(type, typeArr, callingConvention, z2);
        this.callContext = callContext2;
        this.contextAddress = callContext2.getAddress();
    }

    public Function(long j2, Type type, int i3, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        this.functionAddress = j2;
        CallContext callContext2 = CallContext.getCallContext(type, i3, typeArr, callingConvention, z2);
        this.callContext = callContext2;
        this.contextAddress = callContext2.getAddress();
    }
}
