package com.kenai.jffi;

final class NativeObjectParameterInvoker extends ObjectParameterInvoker {
    private final Foreign foreign;

    public NativeObjectParameterInvoker(Foreign foreign2) {
        this.foreign = foreign2;
    }

    public final long invokeN1O1(CallContext callContext, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN1O1(callContext.getAddress(), j2, j3, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN1O1rN(Function function, long j2, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN1O1(function.getContextAddress(), function.getFunctionAddress(), j2, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN2O1(CallContext callContext, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN2O1(callContext.getAddress(), j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN2O1rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN2O1(function.getContextAddress(), function.getFunctionAddress(), j2, j3, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN2O2rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return Foreign.invokeN2O2(function.getContextAddress(), function.getFunctionAddress(), j2, j3, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeN3O1rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN3O1(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN3O2rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return Foreign.invokeN3O2(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeN3O3rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return Foreign.invokeN3O3(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6, obj3, objectParameterInfo3.asObjectInfo(), i7, i8);
    }

    public final long invokeN4O1rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN4O1(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public final long invokeN4O2rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return Foreign.invokeN4O2(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeN4O3rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return Foreign.invokeN4O3(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6, obj3, objectParameterInfo3.asObjectInfo(), i7, i8);
    }

    public long invokeN5O1rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN5O1(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public long invokeN5O2rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return Foreign.invokeN5O2(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeN5O3rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return Foreign.invokeN5O3(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6, obj3, objectParameterInfo3.asObjectInfo(), i7, i8);
    }

    public long invokeN6O1rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return Foreign.invokeN6O1(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, j7, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    public long invokeN6O2rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return Foreign.invokeN6O2(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, j7, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeN6O3rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return Foreign.invokeN6O3(function.getContextAddress(), function.getFunctionAddress(), j2, j3, j4, j5, j6, j7, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6, obj3, objectParameterInfo3.asObjectInfo(), i7, i8);
    }

    public final boolean isNative() {
        return true;
    }
}
