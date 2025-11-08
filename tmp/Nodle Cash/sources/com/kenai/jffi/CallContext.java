package com.kenai.jffi;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class CallContext {
    final AtomicIntegerFieldUpdater<CallContext> UPDATER;
    final long contextAddress;
    volatile int disposed;
    final int fixedParamCount;
    final int flags;
    private final Foreign foreign;
    private final int parameterCount;
    final long[] parameterTypeHandles;
    final Type[] parameterTypes;
    private final int rawParameterSize;
    final Type returnType;

    public CallContext(Type type, Type... typeArr) {
        this(type, typeArr, CallingConvention.DEFAULT, true);
    }

    public static CallContext getCallContext(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        return CallContextCache.getInstance().getCallContext(type, typeArr, callingConvention, z2);
    }

    @Deprecated
    public final void dispose() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CallContext.class != obj.getClass()) {
            return false;
        }
        CallContext callContext = (CallContext) obj;
        return this.flags == callContext.flags && this.parameterCount == callContext.parameterCount && this.rawParameterSize == callContext.rawParameterSize && Arrays.equals(this.parameterTypes, callContext.parameterTypes) && this.returnType.equals(callContext.returnType);
    }

    public void finalize() throws Throwable {
        Class<CallContext> cls = CallContext.class;
        try {
            if (this.UPDATER.getAndSet(this, 1) == 0) {
                long j2 = this.contextAddress;
                if (j2 != 0) {
                    this.foreign.freeCallContext(j2);
                }
            }
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
        super.finalize();
    }

    public final long getAddress() {
        return this.contextAddress;
    }

    public final int getParameterCount() {
        return this.parameterCount;
    }

    public final Type getParameterType(int i3) {
        return this.parameterTypes[i3];
    }

    public final int getRawParameterSize() {
        return this.rawParameterSize;
    }

    public final Type getReturnType() {
        return this.returnType;
    }

    public int hashCode() {
        int hashCode = this.returnType.hashCode();
        return ((Arrays.hashCode(this.parameterTypes) + ((hashCode + (this.parameterCount * 31)) * 31)) * 31) + this.flags;
    }

    public CallContext(Type type, Type[] typeArr, CallingConvention callingConvention) {
        this(type, typeArr, callingConvention, true);
    }

    public static CallContext getCallContext(Type type, int i3, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        return CallContextCache.getInstance().getCallContext(type, i3, typeArr, callingConvention, z2);
    }

    public CallContext(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2) {
        this(type, typeArr.length, typeArr, callingConvention, z2, false);
    }

    public static CallContext getCallContext(Type type, Type[] typeArr, CallingConvention callingConvention, boolean z2, boolean z3) {
        return CallContextCache.getInstance().getCallContext(type, typeArr, callingConvention, z2, z3);
    }

    public CallContext(Type type, int i3, Type[] typeArr, CallingConvention callingConvention, boolean z2, boolean z3) {
        this.UPDATER = AtomicIntegerFieldUpdater.newUpdater(CallContext.class, "disposed");
        Foreign instance = Foreign.getInstance();
        this.foreign = instance;
        int i4 = (callingConvention == CallingConvention.STDCALL ? 1 : 0) | (!z2 ? 2 : 0) | (z3 ? 4 : 0);
        long newCallContext = instance.newCallContext(type.handle(), Type.nativeHandles(typeArr), (i3 << 16) | i4);
        if (newCallContext != 0) {
            this.contextAddress = newCallContext;
            this.returnType = type;
            this.parameterTypes = (Type[]) typeArr.clone();
            this.parameterCount = typeArr.length;
            this.fixedParamCount = i3;
            this.rawParameterSize = instance.getCallContextRawParameterSize(newCallContext);
            this.parameterTypeHandles = Type.nativeHandles(typeArr);
            this.flags = i4;
            return;
        }
        throw new RuntimeException("Failed to create native function");
    }
}
