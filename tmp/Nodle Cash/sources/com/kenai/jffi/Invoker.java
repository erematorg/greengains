package com.kenai.jffi;

import A.a;
import androidx.camera.camera2.internal.C0118y;
import java.math.BigDecimal;

public abstract class Invoker {
    private final Foreign foreign;
    private final ObjectParameterInvoker objectParameterInvoker;

    public static final class ILP32 extends Invoker {
        private static final long ADDRESS_MASK = 4294967295L;
        /* access modifiers changed from: private */
        public static final Invoker INSTANCE = new ILP32();

        private ILP32() {
            super();
        }

        public final long invokeAddress(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
            return ((long) invokeInt(callContext, j2, heapInvocationBuffer)) & 4294967295L;
        }
    }

    public static final class LP64 extends Invoker {
        /* access modifiers changed from: private */
        public static final Invoker INSTANCE = new LP64();

        private LP64() {
            super();
        }

        public final long invokeAddress(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
            return invokeLong(callContext, j2, heapInvocationBuffer);
        }
    }

    public static final class SingletonHolder {
        /* access modifiers changed from: private */
        public static final Invoker INSTANCE = (Platform.getPlatform().addressSize() == 64 ? LP64.INSTANCE : ILP32.INSTANCE);

        private SingletonHolder() {
        }
    }

    public static Invoker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private int invokeArrayWithObjectsInt32(long j2, long j3, HeapInvocationBuffer heapInvocationBuffer, ObjectBuffer objectBuffer) {
        Object[] objects = objectBuffer.objects();
        int[] info = objectBuffer.info();
        int objectCount = objectBuffer.objectCount();
        if (objectCount == 1) {
            return Foreign.invokeArrayO1Int32(j2, j3, heapInvocationBuffer.array(), objects[0], info[0], info[1], info[2]);
        } else if (objectCount != 2) {
            return Foreign.invokeArrayWithObjectsInt32(j2, j3, heapInvocationBuffer.array(), objectCount, info, objects);
        } else {
            return Foreign.invokeArrayO2Int32(j2, j3, heapInvocationBuffer.array(), objects[0], info[0], info[1], info[2], objects[1], info[3], info[4], info[5]);
        }
    }

    private long invokeArrayWithObjectsInt64(long j2, long j3, HeapInvocationBuffer heapInvocationBuffer, ObjectBuffer objectBuffer) {
        Object[] objects = objectBuffer.objects();
        int[] info = objectBuffer.info();
        int objectCount = objectBuffer.objectCount();
        if (objectCount == 1) {
            return Foreign.invokeArrayO1Int64(j2, j3, heapInvocationBuffer.array(), objects[0], info[0], info[1], info[2]);
        } else if (objectCount != 2) {
            return Foreign.invokeArrayWithObjectsInt64(j2, j3, heapInvocationBuffer.array(), objectCount, info, objects);
        } else {
            return Foreign.invokeArrayO2Int64(j2, j3, heapInvocationBuffer.array(), objects[0], info[0], info[1], info[2], objects[1], info[3], info[4], info[5]);
        }
    }

    private static RuntimeException newHeapObjectCountError(int i3) {
        return new RuntimeException(C0118y.c(i3, "insufficient number of heap objects supplied (", " required)"));
    }

    private static RuntimeException newInsufficientObjectCountError(int i3) {
        return new RuntimeException(a.k("invalid object count: ", i3));
    }

    private static RuntimeException newObjectCountError(int i3) {
        return new RuntimeException(a.k("invalid object count: ", i3));
    }

    public final ObjectParameterInvoker getObjectParameterInvoker() {
        return this.objectParameterInvoker;
    }

    public final void invoke(Function function, long j2, long[] jArr) {
        Foreign.invokePointerParameterArray(function.contextAddress, function.functionAddress, j2, jArr);
    }

    public abstract long invokeAddress(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer);

    public long invokeAddress(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeAddress(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    public final BigDecimal invokeBigDecimal(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeBigDecimal(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    public final double invokeDouble(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeDouble(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    public final float invokeFloat(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeFloat(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    public final int invokeI0(CallContext callContext, long j2) {
        return Foreign.invokeI0(callContext.contextAddress, j2);
    }

    public final int invokeI0NoErrno(CallContext callContext, long j2) {
        return Foreign.invokeI0NoErrno(callContext.contextAddress, j2);
    }

    public final int invokeI1(CallContext callContext, long j2, int i3) {
        return Foreign.invokeI1(callContext.contextAddress, j2, i3);
    }

    public final int invokeI1NoErrno(CallContext callContext, long j2, int i3) {
        return Foreign.invokeI1NoErrno(callContext.contextAddress, j2, i3);
    }

    public final int invokeI2(CallContext callContext, long j2, int i3, int i4) {
        return Foreign.invokeI2(callContext.contextAddress, j2, i3, i4);
    }

    public final int invokeI2NoErrno(CallContext callContext, long j2, int i3, int i4) {
        return Foreign.invokeI2NoErrno(callContext.contextAddress, j2, i3, i4);
    }

    public final int invokeI3(CallContext callContext, long j2, int i3, int i4, int i5) {
        return Foreign.invokeI3(callContext.contextAddress, j2, i3, i4, i5);
    }

    public final int invokeI3NoErrno(CallContext callContext, long j2, int i3, int i4, int i5) {
        return Foreign.invokeI3NoErrno(callContext.contextAddress, j2, i3, i4, i5);
    }

    public final int invokeI4(CallContext callContext, long j2, int i3, int i4, int i5, int i6) {
        return Foreign.invokeI4(callContext.contextAddress, j2, i3, i4, i5, i6);
    }

    public final int invokeI4NoErrno(CallContext callContext, long j2, int i3, int i4, int i5, int i6) {
        return Foreign.invokeI4NoErrno(callContext.contextAddress, j2, i3, i4, i5, i6);
    }

    public final int invokeI5(CallContext callContext, long j2, int i3, int i4, int i5, int i6, int i7) {
        return Foreign.invokeI5(callContext.contextAddress, j2, i3, i4, i5, i6, i7);
    }

    public final int invokeI5NoErrno(CallContext callContext, long j2, int i3, int i4, int i5, int i6, int i7) {
        return Foreign.invokeI5NoErrno(callContext.contextAddress, j2, i3, i4, i5, i6, i7);
    }

    public final int invokeI6(CallContext callContext, long j2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Foreign.invokeI6(callContext.contextAddress, j2, i3, i4, i5, i6, i7, i8);
    }

    public final int invokeI6NoErrno(CallContext callContext, long j2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Foreign.invokeI6NoErrno(callContext.contextAddress, j2, i3, i4, i5, i6, i7, i8);
    }

    @Deprecated
    public final int invokeIIIIIIrI(Function function, int i3, int i4, int i5, int i6, int i7, int i8) {
        Function function2 = function;
        return Foreign.invokeI6(function2.contextAddress, function2.functionAddress, i3, i4, i5, i6, i7, i8);
    }

    @Deprecated
    public final int invokeIIIIIrI(Function function, int i3, int i4, int i5, int i6, int i7) {
        return Foreign.invokeI5(function.contextAddress, function.functionAddress, i3, i4, i5, i6, i7);
    }

    @Deprecated
    public final int invokeIIIIrI(Function function, int i3, int i4, int i5, int i6) {
        return Foreign.invokeI4(function.contextAddress, function.functionAddress, i3, i4, i5, i6);
    }

    @Deprecated
    public final int invokeIIIrI(Function function, int i3, int i4, int i5) {
        return Foreign.invokeI3(function.contextAddress, function.functionAddress, i3, i4, i5);
    }

    @Deprecated
    public final int invokeIIrI(Function function, int i3, int i4) {
        return Foreign.invokeI2(function.contextAddress, function.functionAddress, i3, i4);
    }

    public final int invokeInt(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeInt(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    @Deprecated
    public final int invokeIrI(Function function, int i3) {
        return Foreign.invokeI1(function.contextAddress, function.functionAddress, i3);
    }

    public final long invokeL0(CallContext callContext, long j2) {
        return Foreign.invokeL0(callContext.contextAddress, j2);
    }

    public final long invokeL0NoErrno(CallContext callContext, long j2) {
        return Foreign.invokeL0NoErrno(callContext.contextAddress, j2);
    }

    public final long invokeL1(CallContext callContext, long j2, long j3) {
        return Foreign.invokeL1(callContext.contextAddress, j2, j3);
    }

    public final long invokeL1NoErrno(CallContext callContext, long j2, long j3) {
        return Foreign.invokeL1NoErrno(callContext.contextAddress, j2, j3);
    }

    public final long invokeL2(CallContext callContext, long j2, long j3, long j4) {
        return Foreign.invokeL2(callContext.contextAddress, j2, j3, j4);
    }

    public final long invokeL2NoErrno(CallContext callContext, long j2, long j3, long j4) {
        return Foreign.invokeL2NoErrno(callContext.contextAddress, j2, j3, j4);
    }

    public final long invokeL3(CallContext callContext, long j2, long j3, long j4, long j5) {
        return Foreign.invokeL3(callContext.contextAddress, j2, j3, j4, j5);
    }

    public final long invokeL3NoErrno(CallContext callContext, long j2, long j3, long j4, long j5) {
        return Foreign.invokeL3NoErrno(callContext.contextAddress, j2, j3, j4, j5);
    }

    public final long invokeL4(CallContext callContext, long j2, long j3, long j4, long j5, long j6) {
        return Foreign.invokeL4(callContext.contextAddress, j2, j3, j4, j5, j6);
    }

    public final long invokeL4NoErrno(CallContext callContext, long j2, long j3, long j4, long j5, long j6) {
        return Foreign.invokeL4NoErrno(callContext.contextAddress, j2, j3, j4, j5, j6);
    }

    public final long invokeL5(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Foreign.invokeL5(callContext.contextAddress, j2, j3, j4, j5, j6, j7);
    }

    public final long invokeL5NoErrno(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Foreign.invokeL5NoErrno(callContext.contextAddress, j2, j3, j4, j5, j6, j7);
    }

    public final long invokeL6(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        return Foreign.invokeL6(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8);
    }

    public final long invokeL6NoErrno(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        return Foreign.invokeL6NoErrno(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8);
    }

    public final long invokeLLLLLLrL(Function function, long j2, long j3, long j4, long j5, long j6, long j7) {
        Function function2 = function;
        return Foreign.invokeL6(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5, j6, j7);
    }

    public final long invokeLLLLLrL(Function function, long j2, long j3, long j4, long j5, long j6) {
        Function function2 = function;
        return Foreign.invokeL5(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5, j6);
    }

    public final long invokeLLLLrL(Function function, long j2, long j3, long j4, long j5) {
        Function function2 = function;
        return Foreign.invokeL4(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5);
    }

    public final long invokeLLLrL(Function function, long j2, long j3, long j4) {
        Function function2 = function;
        return Foreign.invokeL3(function2.contextAddress, function2.functionAddress, j2, j3, j4);
    }

    public final long invokeLLrL(Function function, long j2, long j3) {
        return Foreign.invokeL2(function.contextAddress, function.functionAddress, j2, j3);
    }

    public final long invokeLong(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeLong(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    public final long invokeLrL(Function function, long j2) {
        return Foreign.invokeL1(function.contextAddress, function.functionAddress, j2);
    }

    public final long invokeN0(CallContext callContext, long j2) {
        return Foreign.invokeN0(callContext.contextAddress, j2);
    }

    public final long invokeN1(CallContext callContext, long j2, long j3) {
        return Foreign.invokeN1(callContext.contextAddress, j2, j3);
    }

    public final long invokeN1O1(CallContext callContext, long j2, long j3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN1O1(callContext.contextAddress, j2, j3, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN2(CallContext callContext, long j2, long j3, long j4) {
        return Foreign.invokeN2(callContext.contextAddress, j2, j3, j4);
    }

    public final long invokeN2O1(CallContext callContext, long j2, long j3, long j4, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN2O1(callContext.contextAddress, j2, j3, j4, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN2O2(CallContext callContext, long j2, long j3, long j4, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        return Foreign.invokeN2O2(callContext.contextAddress, j2, j3, j4, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
    }

    public final long invokeN3(CallContext callContext, long j2, long j3, long j4, long j5) {
        return Foreign.invokeN3(callContext.contextAddress, j2, j3, j4, j5);
    }

    public final long invokeN3O1(CallContext callContext, long j2, long j3, long j4, long j5, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN3O1(callContext.contextAddress, j2, j3, j4, j5, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN3O2(CallContext callContext, long j2, long j3, long j4, long j5, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        return Foreign.invokeN3O2(callContext.contextAddress, j2, j3, j4, j5, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
    }

    public final long invokeN3O3(CallContext callContext, long j2, long j3, long j4, long j5, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2, Object obj3, ObjectParameterStrategy objectParameterStrategy3, ObjectParameterInfo objectParameterInfo3) {
        Object obj4 = obj;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy;
        Object obj5 = obj2;
        ObjectParameterStrategy objectParameterStrategy5 = objectParameterStrategy2;
        Object obj6 = obj3;
        ObjectParameterStrategy objectParameterStrategy6 = objectParameterStrategy3;
        return Foreign.invokeN3O3(callContext.contextAddress, j2, j3, j4, j5, objectParameterStrategy4.object(obj4), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5), objectParameterStrategy6.object(obj6), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy6.offset(obj6), objectParameterStrategy6.length(obj6));
    }

    public final long invokeN4(CallContext callContext, long j2, long j3, long j4, long j5, long j6) {
        return Foreign.invokeN4(callContext.contextAddress, j2, j3, j4, j5, j6);
    }

    public final long invokeN4O1(CallContext callContext, long j2, long j3, long j4, long j5, long j6, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN4O1(callContext.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN4O2(CallContext callContext, long j2, long j3, long j4, long j5, long j6, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        return Foreign.invokeN4O2(callContext.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
    }

    public final long invokeN4O3(CallContext callContext, long j2, long j3, long j4, long j5, long j6, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2, Object obj3, ObjectParameterStrategy objectParameterStrategy3, ObjectParameterInfo objectParameterInfo3) {
        Object obj4 = obj;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy;
        Object obj5 = obj2;
        ObjectParameterStrategy objectParameterStrategy5 = objectParameterStrategy2;
        Object obj6 = obj3;
        ObjectParameterStrategy objectParameterStrategy6 = objectParameterStrategy3;
        return Foreign.invokeN4O3(callContext.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy4.object(obj4), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5), objectParameterStrategy6.object(obj6), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy6.offset(obj6), objectParameterStrategy6.length(obj6));
    }

    public final long invokeN5(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7) {
        return Foreign.invokeN5(callContext.contextAddress, j2, j3, j4, j5, j6, j7);
    }

    public final long invokeN5O1(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN5O1(callContext.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN5O2(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        return Foreign.invokeN5O2(callContext.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
    }

    public final long invokeN5O3(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2, Object obj3, ObjectParameterStrategy objectParameterStrategy3, ObjectParameterInfo objectParameterInfo3) {
        Object obj4 = obj;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy;
        Object obj5 = obj2;
        ObjectParameterStrategy objectParameterStrategy5 = objectParameterStrategy2;
        Object obj6 = obj3;
        ObjectParameterStrategy objectParameterStrategy6 = objectParameterStrategy3;
        return Foreign.invokeN5O3(callContext.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy4.object(obj4), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5), objectParameterStrategy6.object(obj6), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy6.offset(obj6), objectParameterStrategy6.length(obj6));
    }

    public final long invokeN6(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        return Foreign.invokeN6(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8);
    }

    public final long invokeN6O1(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        return Foreign.invokeN6O1(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
    }

    public final long invokeN6O2(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        return Foreign.invokeN6O2(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
    }

    public final long invokeN6O3(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2, Object obj3, ObjectParameterStrategy objectParameterStrategy3, ObjectParameterInfo objectParameterInfo3) {
        Object obj4 = obj;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy;
        Object obj5 = obj2;
        ObjectParameterStrategy objectParameterStrategy5 = objectParameterStrategy2;
        Object obj6 = obj3;
        ObjectParameterStrategy objectParameterStrategy6 = objectParameterStrategy3;
        return Foreign.invokeN6O3(callContext.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy4.object(obj4), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5), objectParameterStrategy6.object(obj6), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy6.offset(obj6), objectParameterStrategy6.length(obj6));
    }

    public final long invokeNNNNNNrN(Function function, long j2, long j3, long j4, long j5, long j6, long j7) {
        Function function2 = function;
        return Foreign.invokeN6(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5, j6, j7);
    }

    public final long invokeNNNNNrN(Function function, long j2, long j3, long j4, long j5, long j6) {
        Function function2 = function;
        return Foreign.invokeN5(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5, j6);
    }

    public final long invokeNNNNrN(Function function, long j2, long j3, long j4, long j5) {
        Function function2 = function;
        return Foreign.invokeN4(function2.contextAddress, function2.functionAddress, j2, j3, j4, j5);
    }

    @Deprecated
    public final long invokeNNNO1rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        Function function2 = function;
        long j5 = function2.contextAddress;
        long j6 = function2.functionAddress;
        return Foreign.invokeN3O1(j5, j6, j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    @Deprecated
    public final long invokeNNNO2rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        Function function2 = function;
        return Foreign.invokeN3O2(function2.contextAddress, function2.functionAddress, j2, j3, j4, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeNNNrN(Function function, long j2, long j3, long j4) {
        Function function2 = function;
        return Foreign.invokeN3(function2.contextAddress, function2.functionAddress, j2, j3, j4);
    }

    @Deprecated
    public final long invokeNNO1rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        Function function2 = function;
        long j4 = function2.contextAddress;
        long j5 = function2.functionAddress;
        return Foreign.invokeN2O1(j4, j5, j2, j3, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    @Deprecated
    public final long invokeNNO2rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        Function function2 = function;
        long j4 = function2.contextAddress;
        long j5 = function2.functionAddress;
        return Foreign.invokeN2O2(j4, j5, j2, j3, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    public final long invokeNNrN(Function function, long j2, long j3) {
        return Foreign.invokeN2(function.contextAddress, function.functionAddress, j2, j3);
    }

    @Deprecated
    public final int invokeNoErrnoIIIrI(Function function, int i3, int i4, int i5) {
        return Foreign.invokeI3NoErrno(function.contextAddress, function.functionAddress, i3, i4, i5);
    }

    @Deprecated
    public final int invokeNoErrnoIIrI(Function function, int i3, int i4) {
        return Foreign.invokeI2NoErrno(function.contextAddress, function.functionAddress, i3, i4);
    }

    @Deprecated
    public final int invokeNoErrnoIrI(Function function, int i3) {
        return Foreign.invokeI1NoErrno(function.contextAddress, function.functionAddress, i3);
    }

    @Deprecated
    public final int invokeNoErrnoVrI(Function function) {
        return Foreign.invokeI0NoErrno(function.contextAddress, function.functionAddress);
    }

    public final long invokeNrN(Function function, long j2) {
        return Foreign.invokeN1(function.contextAddress, function.functionAddress, j2);
    }

    public final Object invokeObject(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        return Foreign.invokeArrayWithObjectsReturnObject(function.contextAddress, function.functionAddress, heapInvocationBuffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects());
    }

    public final byte[] invokeStruct(Function function, HeapInvocationBuffer heapInvocationBuffer) {
        return invokeStruct(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer);
    }

    @Deprecated
    public final int invokeVrI(Function function) {
        return Foreign.invokeI0(function.contextAddress, function.functionAddress);
    }

    public final long invokeVrL(Function function) {
        return Foreign.invokeL0(function.contextAddress, function.functionAddress);
    }

    public final long invokeVrN(Function function) {
        return Foreign.invokeN0(function.contextAddress, function.functionAddress);
    }

    private Invoker() {
        this(Foreign.getInstance(), ObjectParameterInvoker.getInstance());
    }

    public final void invoke(CallContext callContext, long j2, long j3, long[] jArr) {
        Foreign.invokePointerParameterArray(callContext.contextAddress, j2, j3, jArr);
    }

    public final BigDecimal invokeBigDecimal(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        byte[] invokeStruct = invokeStruct(callContext, j2, heapInvocationBuffer);
        return new BigDecimal(this.foreign.longDoubleToString(invokeStruct, 0, invokeStruct.length));
    }

    public final double invokeDouble(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        if (objectBuffer == null) {
            return Foreign.invokeArrayReturnDouble(callContext.contextAddress, j2, heapInvocationBuffer.array());
        }
        return Foreign.invokeArrayWithObjectsDouble(callContext.contextAddress, j2, heapInvocationBuffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects());
    }

    public final float invokeFloat(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        if (objectBuffer == null) {
            return Foreign.invokeArrayReturnFloat(callContext.contextAddress, j2, heapInvocationBuffer.array());
        }
        return Foreign.invokeArrayWithObjectsFloat(callContext.contextAddress, j2, heapInvocationBuffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects());
    }

    public final int invokeInt(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        if (objectBuffer == null) {
            return Foreign.invokeArrayReturnInt(callContext.contextAddress, j2, heapInvocationBuffer.array());
        }
        return invokeArrayWithObjectsInt32(callContext.contextAddress, j2, heapInvocationBuffer, objectBuffer);
    }

    public final long invokeLong(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        if (objectBuffer == null) {
            return Foreign.invokeArrayReturnLong(callContext.contextAddress, j2, heapInvocationBuffer.array());
        }
        return invokeArrayWithObjectsInt64(callContext.contextAddress, j2, heapInvocationBuffer, objectBuffer);
    }

    public final long invokeN1(CallContext callContext, long j2, long j3, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN1(callContext2.contextAddress, j2, j3);
        }
        if (i4 == 1) {
            return Foreign.invokeN1O1(callContext2.contextAddress, j2, j3, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        }
        throw newObjectCountError(i3);
    }

    public final long invokeN2(CallContext callContext, long j2, long j3, long j4, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN2(callContext2.contextAddress, j2, j3, j4);
        }
        if (i4 == 1) {
            return Foreign.invokeN2O1(callContext2.contextAddress, j2, j3, j4, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        }
        throw newObjectCountError(i3);
    }

    public final long invokeN3(CallContext callContext, long j2, long j3, long j4, long j5, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN3(callContext2.contextAddress, j2, j3, j4, j5);
        }
        if (i4 != 1) {
            throw newObjectCountError(i3);
        } else if (!objectParameterStrategy.isDirect()) {
            return Foreign.invokeN3O1(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        } else {
            throw newInsufficientObjectCountError(i3);
        }
    }

    public final long invokeN4(CallContext callContext, long j2, long j3, long j4, long j5, long j6, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN4(callContext2.contextAddress, j2, j3, j4, j5, j6);
        }
        if (i4 == 1) {
            return Foreign.invokeN4O1(callContext2.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        }
        throw newObjectCountError(i3);
    }

    public final long invokeN5(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN5(callContext2.contextAddress, j2, j3, j4, j5, j6, j7);
        }
        if (i4 == 1) {
            return Foreign.invokeN5O1(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        }
        throw newObjectCountError(i3);
    }

    public final long invokeN6(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo) {
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj2 = obj;
        ObjectParameterStrategy objectParameterStrategy2 = objectParameterStrategy;
        if (i4 == 0) {
            return Foreign.invokeN6(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, j8);
        }
        if (i4 == 1) {
            return Foreign.invokeN6O1(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy2.object(obj2), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy2.offset(obj2), objectParameterStrategy2.length(obj2));
        }
        throw newObjectCountError(i3);
    }

    public final byte[] invokeStruct(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer) {
        byte[] bArr = new byte[callContext.getReturnType().size()];
        invokeStruct(callContext, j2, heapInvocationBuffer, bArr, 0);
        return bArr;
    }

    public Invoker(Foreign foreign2, ObjectParameterInvoker objectParameterInvoker2) {
        this.foreign = foreign2;
        this.objectParameterInvoker = objectParameterInvoker2;
    }

    public final void invokeStruct(Function function, HeapInvocationBuffer heapInvocationBuffer, byte[] bArr, int i3) {
        invokeStruct(function.getCallContext(), function.getFunctionAddress(), heapInvocationBuffer, bArr, i3);
    }

    public final void invokeStruct(CallContext callContext, long j2, HeapInvocationBuffer heapInvocationBuffer, byte[] bArr, int i3) {
        CallContext callContext2 = callContext;
        ObjectBuffer objectBuffer = heapInvocationBuffer.objectBuffer();
        if (objectBuffer != null) {
            Foreign.invokeArrayWithObjectsReturnStruct(callContext2.contextAddress, j2, heapInvocationBuffer.array(), objectBuffer.objectCount(), objectBuffer.info(), objectBuffer.objects(), bArr, i3);
            return;
        }
        Foreign.invokeArrayReturnStruct(callContext2.contextAddress, j2, heapInvocationBuffer.array(), bArr, i3);
    }

    public final long invokeN2(CallContext callContext, long j2, long j3, long j4, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        ObjectParameterInfo objectParameterInfo3;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        if (i4 == 0) {
            return Foreign.invokeN2(callContext2.contextAddress, j2, j3, j4);
        }
        if (i4 == 1) {
            if (objectParameterStrategy.isDirect() && !objectParameterStrategy2.isDirect()) {
                objectParameterInfo3 = objectParameterInfo2;
                obj3 = obj4;
                objectParameterStrategy3 = objectParameterStrategy4;
            } else {
                objectParameterInfo3 = objectParameterInfo;
            }
            return Foreign.invokeN2O1(callContext2.contextAddress, j2, j3, j4, objectParameterStrategy3.object(obj3), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3));
        } else if (i4 == 2) {
            return Foreign.invokeN2O2(callContext2.contextAddress, j2, j3, j4, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
        } else {
            throw newObjectCountError(i3);
        }
    }

    public final long invokeN4(CallContext callContext, long j2, long j3, long j4, long j5, long j6, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        ObjectParameterInfo objectParameterInfo3;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        if (i4 == 0) {
            return Foreign.invokeN4(callContext2.contextAddress, j2, j3, j4, j5, j6);
        }
        if (i4 == 1) {
            if (objectParameterStrategy.isDirect() && !objectParameterStrategy2.isDirect()) {
                objectParameterInfo3 = objectParameterInfo2;
                obj3 = obj4;
                objectParameterStrategy3 = objectParameterStrategy4;
            } else {
                objectParameterInfo3 = objectParameterInfo;
            }
            return Foreign.invokeN4O1(callContext2.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy3.object(obj3), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3));
        } else if (i4 == 2) {
            return Foreign.invokeN4O2(callContext2.contextAddress, j2, j3, j4, j5, j6, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
        } else {
            throw newObjectCountError(i3);
        }
    }

    public final long invokeN5(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        ObjectParameterInfo objectParameterInfo3;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        if (i4 == 0) {
            return Foreign.invokeN5(callContext2.contextAddress, j2, j3, j4, j5, j6, j7);
        }
        if (i4 == 1) {
            if (!objectParameterStrategy.isDirect()) {
                objectParameterInfo3 = objectParameterInfo;
            } else {
                objectParameterInfo3 = objectParameterInfo2;
                obj3 = obj4;
                objectParameterStrategy3 = objectParameterStrategy4;
            }
            return Foreign.invokeN5O1(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy3.object(obj3), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3));
        } else if (i4 == 2) {
            return Foreign.invokeN5O2(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
        } else {
            throw newObjectCountError(i3);
        }
    }

    public final long invokeN6(CallContext callContext, long j2, long j3, long j4, long j5, long j6, long j7, long j8, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        ObjectParameterInfo objectParameterInfo3;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        if (i4 == 0) {
            return Foreign.invokeN6(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, j8);
        }
        if (i4 == 1) {
            if (!objectParameterStrategy.isDirect()) {
                objectParameterInfo3 = objectParameterInfo;
            } else {
                objectParameterInfo3 = objectParameterInfo2;
                obj3 = obj4;
                objectParameterStrategy3 = objectParameterStrategy4;
            }
            return Foreign.invokeN6O1(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy3.object(obj3), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3));
        } else if (i4 == 2) {
            return Foreign.invokeN6O2(callContext2.contextAddress, j2, j3, j4, j5, j6, j7, j8, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
        } else {
            throw newObjectCountError(i3);
        }
    }

    public final long invokeN3(CallContext callContext, long j2, long j3, long j4, long j5, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2) {
        ObjectParameterInfo objectParameterInfo3;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj3 = obj;
        ObjectParameterStrategy objectParameterStrategy3 = objectParameterStrategy;
        Object obj4 = obj2;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy2;
        if (i4 == 0) {
            return Foreign.invokeN3(callContext2.contextAddress, j2, j3, j4, j5);
        }
        if (i4 == 1) {
            if (objectParameterStrategy.isDirect() && !objectParameterStrategy2.isDirect()) {
                objectParameterInfo3 = objectParameterInfo2;
                obj3 = obj4;
                objectParameterStrategy3 = objectParameterStrategy4;
            } else {
                objectParameterInfo3 = objectParameterInfo;
            }
            return Foreign.invokeN3O1(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy3.object(obj3), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3));
        } else if (i4 == 2) {
            return Foreign.invokeN3O2(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy3.object(obj3), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy3.offset(obj3), objectParameterStrategy3.length(obj3), objectParameterStrategy4.object(obj4), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
        } else {
            throw newObjectCountError(i3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0077, code lost:
        if (r9 != 3) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0087, code lost:
        if (r57.isDirect() == false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        if (r60.isDirect() != false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0091, code lost:
        r11 = r61;
        r10 = r3;
        r12 = r9;
        r9 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN5(com.kenai.jffi.CallContext r39, long r40, long r42, long r44, long r46, long r48, long r50, int r52, java.lang.Object r53, com.kenai.jffi.ObjectParameterStrategy r54, com.kenai.jffi.ObjectParameterInfo r55, java.lang.Object r56, com.kenai.jffi.ObjectParameterStrategy r57, com.kenai.jffi.ObjectParameterInfo r58, java.lang.Object r59, com.kenai.jffi.ObjectParameterStrategy r60, com.kenai.jffi.ObjectParameterInfo r61) {
        /*
            r38 = this;
            r0 = r39
            r1 = r52
            r2 = r59
            r3 = r60
            if (r1 != 0) goto L_0x001d
            long r0 = r0.contextAddress
            r2 = r40
            r4 = r42
            r6 = r44
            r8 = r46
            r10 = r48
            r12 = r50
            long r0 = com.kenai.jffi.Foreign.invokeN5(r0, r2, r4, r6, r8, r10, r12)
            return r0
        L_0x001d:
            boolean r4 = r54.isDirect()
            r5 = 2
            r6 = 3
            if (r4 != 0) goto L_0x002d
            r4 = r53
            r7 = r54
            r8 = r55
            r9 = r5
            goto L_0x004f
        L_0x002d:
            boolean r4 = r57.isDirect()
            if (r4 != 0) goto L_0x003b
            r4 = r56
            r7 = r57
            r8 = r58
            r9 = r6
            goto L_0x004f
        L_0x003b:
            boolean r4 = r60.isDirect()
            r7 = 4
            if (r4 != 0) goto L_0x0048
            r8 = r61
            r4 = r2
            r9 = r7
            r7 = r3
            goto L_0x004f
        L_0x0048:
            r4 = r53
            r8 = r55
            r9 = r7
            r7 = r54
        L_0x004f:
            r10 = 1
            if (r1 != r10) goto L_0x0075
            long r11 = r0.contextAddress
            java.lang.Object r25 = r7.object(r4)
            int r26 = r7.objectInfo(r8)
            int r27 = r7.offset(r4)
            int r28 = r7.length(r4)
            r13 = r40
            r15 = r42
            r17 = r44
            r19 = r46
            r21 = r48
            r23 = r50
            long r0 = com.kenai.jffi.Foreign.invokeN5O1(r11, r13, r15, r17, r19, r21, r23, r25, r26, r27, r28)
            return r0
        L_0x0075:
            if (r9 == r5) goto L_0x0081
            if (r9 == r6) goto L_0x008a
        L_0x0079:
            r10 = r57
            r11 = r58
            r12 = r9
            r9 = r56
            goto L_0x0096
        L_0x0081:
            int r9 = r9 + 1
            boolean r11 = r57.isDirect()
            if (r11 != 0) goto L_0x008a
            goto L_0x0079
        L_0x008a:
            int r9 = r9 + r10
            boolean r10 = r60.isDirect()
            if (r10 != 0) goto L_0x0079
            r11 = r61
            r10 = r3
            r12 = r9
            r9 = r2
        L_0x0096:
            if (r1 != r5) goto L_0x00cb
            long r13 = r0.contextAddress
            java.lang.Object r27 = r7.object(r4)
            int r28 = r7.objectInfo(r8)
            int r29 = r7.offset(r4)
            int r30 = r7.length(r4)
            java.lang.Object r31 = r10.object(r9)
            int r32 = r10.objectInfo(r11)
            int r33 = r10.offset(r9)
            int r34 = r10.length(r9)
            r15 = r40
            r17 = r42
            r19 = r44
            r21 = r46
            r23 = r48
            r25 = r50
            long r0 = com.kenai.jffi.Foreign.invokeN5O2(r13, r15, r17, r19, r21, r23, r25, r27, r28, r29, r30, r31, r32, r33, r34)
            return r0
        L_0x00cb:
            if (r12 == r6) goto L_0x00ce
            goto L_0x00d1
        L_0x00ce:
            r60.isDirect()
        L_0x00d1:
            if (r1 != r6) goto L_0x0116
            long r12 = r0.contextAddress
            java.lang.Object r26 = r7.object(r4)
            int r27 = r7.objectInfo(r8)
            int r28 = r7.offset(r4)
            int r29 = r7.length(r4)
            java.lang.Object r30 = r10.object(r9)
            int r31 = r10.objectInfo(r11)
            int r32 = r10.offset(r9)
            int r33 = r10.length(r9)
            java.lang.Object r34 = r3.object(r2)
            int r35 = r60.objectInfo(r61)
            int r36 = r3.offset(r2)
            int r37 = r3.length(r2)
            r14 = r40
            r16 = r42
            r18 = r44
            r20 = r46
            r22 = r48
            r24 = r50
            long r0 = com.kenai.jffi.Foreign.invokeN5O3(r12, r14, r16, r18, r20, r22, r24, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37)
            return r0
        L_0x0116:
            java.lang.RuntimeException r0 = newObjectCountError(r52)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN5(com.kenai.jffi.CallContext, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007b, code lost:
        if (r9 != 3) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008b, code lost:
        if (r61.isDirect() == false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008e, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0093, code lost:
        if (r64.isDirect() != false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0095, code lost:
        r11 = r65;
        r10 = r3;
        r12 = r9;
        r9 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN6(com.kenai.jffi.CallContext r41, long r42, long r44, long r46, long r48, long r50, long r52, long r54, int r56, java.lang.Object r57, com.kenai.jffi.ObjectParameterStrategy r58, com.kenai.jffi.ObjectParameterInfo r59, java.lang.Object r60, com.kenai.jffi.ObjectParameterStrategy r61, com.kenai.jffi.ObjectParameterInfo r62, java.lang.Object r63, com.kenai.jffi.ObjectParameterStrategy r64, com.kenai.jffi.ObjectParameterInfo r65) {
        /*
            r40 = this;
            r0 = r41
            r1 = r56
            r2 = r63
            r3 = r64
            if (r1 != 0) goto L_0x001f
            long r0 = r0.contextAddress
            r2 = r42
            r4 = r44
            r6 = r46
            r8 = r48
            r10 = r50
            r12 = r52
            r14 = r54
            long r0 = com.kenai.jffi.Foreign.invokeN6(r0, r2, r4, r6, r8, r10, r12, r14)
            return r0
        L_0x001f:
            boolean r4 = r58.isDirect()
            r5 = 2
            r6 = 3
            if (r4 != 0) goto L_0x002f
            r4 = r57
            r7 = r58
            r8 = r59
            r9 = r5
            goto L_0x0051
        L_0x002f:
            boolean r4 = r61.isDirect()
            if (r4 != 0) goto L_0x003d
            r4 = r60
            r7 = r61
            r8 = r62
            r9 = r6
            goto L_0x0051
        L_0x003d:
            boolean r4 = r64.isDirect()
            r7 = 4
            if (r4 != 0) goto L_0x004a
            r8 = r65
            r4 = r2
            r9 = r7
            r7 = r3
            goto L_0x0051
        L_0x004a:
            r4 = r57
            r8 = r59
            r9 = r7
            r7 = r58
        L_0x0051:
            r10 = 1
            if (r1 != r10) goto L_0x0079
            long r11 = r0.contextAddress
            java.lang.Object r27 = r7.object(r4)
            int r28 = r7.objectInfo(r8)
            int r29 = r7.offset(r4)
            int r30 = r7.length(r4)
            r13 = r42
            r15 = r44
            r17 = r46
            r19 = r48
            r21 = r50
            r23 = r52
            r25 = r54
            long r0 = com.kenai.jffi.Foreign.invokeN6O1(r11, r13, r15, r17, r19, r21, r23, r25, r27, r28, r29, r30)
            return r0
        L_0x0079:
            if (r9 == r5) goto L_0x0085
            if (r9 == r6) goto L_0x008e
        L_0x007d:
            r10 = r61
            r11 = r62
            r12 = r9
            r9 = r60
            goto L_0x009a
        L_0x0085:
            int r9 = r9 + 1
            boolean r11 = r61.isDirect()
            if (r11 != 0) goto L_0x008e
            goto L_0x007d
        L_0x008e:
            int r9 = r9 + r10
            boolean r10 = r64.isDirect()
            if (r10 != 0) goto L_0x007d
            r11 = r65
            r10 = r3
            r12 = r9
            r9 = r2
        L_0x009a:
            if (r1 != r5) goto L_0x00d1
            long r13 = r0.contextAddress
            java.lang.Object r29 = r7.object(r4)
            int r30 = r7.objectInfo(r8)
            int r31 = r7.offset(r4)
            int r32 = r7.length(r4)
            java.lang.Object r33 = r10.object(r9)
            int r34 = r10.objectInfo(r11)
            int r35 = r10.offset(r9)
            int r36 = r10.length(r9)
            r15 = r42
            r17 = r44
            r19 = r46
            r21 = r48
            r23 = r50
            r25 = r52
            r27 = r54
            long r0 = com.kenai.jffi.Foreign.invokeN6O2(r13, r15, r17, r19, r21, r23, r25, r27, r29, r30, r31, r32, r33, r34, r35, r36)
            return r0
        L_0x00d1:
            if (r12 == r6) goto L_0x00d4
            goto L_0x00d7
        L_0x00d4:
            r64.isDirect()
        L_0x00d7:
            if (r1 != r6) goto L_0x011e
            long r12 = r0.contextAddress
            java.lang.Object r28 = r7.object(r4)
            int r29 = r7.objectInfo(r8)
            int r30 = r7.offset(r4)
            int r31 = r7.length(r4)
            java.lang.Object r32 = r10.object(r9)
            int r33 = r10.objectInfo(r11)
            int r34 = r10.offset(r9)
            int r35 = r10.length(r9)
            java.lang.Object r36 = r3.object(r2)
            int r37 = r64.objectInfo(r65)
            int r38 = r3.offset(r2)
            int r39 = r3.length(r2)
            r14 = r42
            r16 = r44
            r18 = r46
            r20 = r48
            r22 = r50
            r24 = r52
            r26 = r54
            long r0 = com.kenai.jffi.Foreign.invokeN6O3(r12, r14, r16, r18, r20, r22, r24, r26, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39)
            return r0
        L_0x011e:
            java.lang.RuntimeException r0 = newObjectCountError(r56)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN6(com.kenai.jffi.CallContext, long, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0073, code lost:
        if (r9 != 3) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
        if (r53.isDirect() == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0086, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x008b, code lost:
        if (r56.isDirect() != false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008d, code lost:
        r11 = r57;
        r10 = r3;
        r12 = r9;
        r9 = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN4(com.kenai.jffi.CallContext r37, long r38, long r40, long r42, long r44, long r46, int r48, java.lang.Object r49, com.kenai.jffi.ObjectParameterStrategy r50, com.kenai.jffi.ObjectParameterInfo r51, java.lang.Object r52, com.kenai.jffi.ObjectParameterStrategy r53, com.kenai.jffi.ObjectParameterInfo r54, java.lang.Object r55, com.kenai.jffi.ObjectParameterStrategy r56, com.kenai.jffi.ObjectParameterInfo r57) {
        /*
            r36 = this;
            r0 = r37
            r1 = r48
            r2 = r55
            r3 = r56
            if (r1 != 0) goto L_0x001b
            long r0 = r0.contextAddress
            r2 = r38
            r4 = r40
            r6 = r42
            r8 = r44
            r10 = r46
            long r0 = com.kenai.jffi.Foreign.invokeN4(r0, r2, r4, r6, r8, r10)
            return r0
        L_0x001b:
            boolean r4 = r50.isDirect()
            r5 = 2
            r6 = 3
            if (r4 != 0) goto L_0x002b
            r4 = r49
            r7 = r50
            r8 = r51
            r9 = r5
            goto L_0x004d
        L_0x002b:
            boolean r4 = r53.isDirect()
            if (r4 != 0) goto L_0x0039
            r4 = r52
            r7 = r53
            r8 = r54
            r9 = r6
            goto L_0x004d
        L_0x0039:
            boolean r4 = r56.isDirect()
            r7 = 4
            if (r4 != 0) goto L_0x0046
            r8 = r57
            r4 = r2
            r9 = r7
            r7 = r3
            goto L_0x004d
        L_0x0046:
            r4 = r49
            r8 = r51
            r9 = r7
            r7 = r50
        L_0x004d:
            r10 = 1
            if (r1 != r10) goto L_0x0071
            long r11 = r0.contextAddress
            java.lang.Object r23 = r7.object(r4)
            int r24 = r7.objectInfo(r8)
            int r25 = r7.offset(r4)
            int r26 = r7.length(r4)
            r13 = r38
            r15 = r40
            r17 = r42
            r19 = r44
            r21 = r46
            long r0 = com.kenai.jffi.Foreign.invokeN4O1(r11, r13, r15, r17, r19, r21, r23, r24, r25, r26)
            return r0
        L_0x0071:
            if (r9 == r5) goto L_0x007d
            if (r9 == r6) goto L_0x0086
        L_0x0075:
            r10 = r53
            r11 = r54
            r12 = r9
            r9 = r52
            goto L_0x0092
        L_0x007d:
            int r9 = r9 + 1
            boolean r11 = r53.isDirect()
            if (r11 != 0) goto L_0x0086
            goto L_0x0075
        L_0x0086:
            int r9 = r9 + r10
            boolean r10 = r56.isDirect()
            if (r10 != 0) goto L_0x0075
            r11 = r57
            r10 = r3
            r12 = r9
            r9 = r2
        L_0x0092:
            if (r1 != r5) goto L_0x00c5
            long r13 = r0.contextAddress
            java.lang.Object r25 = r7.object(r4)
            int r26 = r7.objectInfo(r8)
            int r27 = r7.offset(r4)
            int r28 = r7.length(r4)
            java.lang.Object r29 = r10.object(r9)
            int r30 = r10.objectInfo(r11)
            int r31 = r10.offset(r9)
            int r32 = r10.length(r9)
            r15 = r38
            r17 = r40
            r19 = r42
            r21 = r44
            r23 = r46
            long r0 = com.kenai.jffi.Foreign.invokeN4O2(r13, r15, r17, r19, r21, r23, r25, r26, r27, r28, r29, r30, r31, r32)
            return r0
        L_0x00c5:
            if (r12 != r6) goto L_0x0115
            boolean r5 = r56.isDirect()
            if (r5 != 0) goto L_0x0115
            if (r1 != r6) goto L_0x0110
            long r12 = r0.contextAddress
            java.lang.Object r24 = r7.object(r4)
            int r25 = r7.objectInfo(r8)
            int r26 = r7.offset(r4)
            int r27 = r7.length(r4)
            java.lang.Object r28 = r10.object(r9)
            int r29 = r10.objectInfo(r11)
            int r30 = r10.offset(r9)
            int r31 = r10.length(r9)
            java.lang.Object r32 = r3.object(r2)
            int r33 = r56.objectInfo(r57)
            int r34 = r3.offset(r2)
            int r35 = r3.length(r2)
            r14 = r38
            r16 = r40
            r18 = r42
            r20 = r44
            r22 = r46
            long r0 = com.kenai.jffi.Foreign.invokeN4O3(r12, r14, r16, r18, r20, r22, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35)
            return r0
        L_0x0110:
            java.lang.RuntimeException r0 = newObjectCountError(r48)
            throw r0
        L_0x0115:
            java.lang.RuntimeException r0 = newInsufficientObjectCountError(r48)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN4(com.kenai.jffi.CallContext, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    public final long invokeN3(CallContext callContext, long j2, long j3, long j4, long j5, int i3, Object obj, ObjectParameterStrategy objectParameterStrategy, ObjectParameterInfo objectParameterInfo, Object obj2, ObjectParameterStrategy objectParameterStrategy2, ObjectParameterInfo objectParameterInfo2, Object obj3, ObjectParameterStrategy objectParameterStrategy3, ObjectParameterInfo objectParameterInfo3) {
        char c3;
        ObjectParameterInfo objectParameterInfo4;
        ObjectParameterInfo objectParameterInfo5;
        CallContext callContext2 = callContext;
        int i4 = i3;
        Object obj4 = obj;
        ObjectParameterStrategy objectParameterStrategy4 = objectParameterStrategy;
        Object obj5 = obj2;
        ObjectParameterStrategy objectParameterStrategy5 = objectParameterStrategy2;
        Object obj6 = obj3;
        ObjectParameterStrategy objectParameterStrategy6 = objectParameterStrategy3;
        if (i4 == 0) {
            return Foreign.invokeN3(callContext2.contextAddress, j2, j3, j4, j5);
        }
        if (i4 < 3) {
            if (!objectParameterStrategy.isDirect()) {
                objectParameterInfo4 = objectParameterInfo;
                c3 = 2;
            } else if (!objectParameterStrategy2.isDirect()) {
                objectParameterInfo4 = objectParameterInfo2;
                obj4 = obj5;
                objectParameterStrategy4 = objectParameterStrategy5;
                c3 = 3;
            } else {
                objectParameterInfo4 = objectParameterInfo3;
                c3 = 4;
                obj4 = obj6;
                objectParameterStrategy4 = objectParameterStrategy6;
            }
            if (i4 == 1) {
                return Foreign.invokeN3O1(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy4.object(obj4), objectParameterStrategy4.objectInfo(objectParameterInfo4), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4));
            } else if (i4 == 2) {
                if ((c3 > 2 || objectParameterStrategy2.isDirect()) && c3 <= 3) {
                    objectParameterInfo5 = objectParameterInfo3;
                    obj5 = obj6;
                    objectParameterStrategy5 = objectParameterStrategy6;
                } else {
                    objectParameterInfo5 = objectParameterInfo2;
                }
                return Foreign.invokeN3O2(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy4.object(obj4), objectParameterStrategy4.objectInfo(objectParameterInfo4), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy5.objectInfo(objectParameterInfo5), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5));
            } else {
                throw newObjectCountError(i3);
            }
        } else {
            return Foreign.invokeN3O3(callContext2.contextAddress, j2, j3, j4, j5, objectParameterStrategy4.object(obj4), objectParameterStrategy.objectInfo(objectParameterInfo), objectParameterStrategy4.offset(obj4), objectParameterStrategy4.length(obj4), objectParameterStrategy5.object(obj5), objectParameterStrategy2.objectInfo(objectParameterInfo2), objectParameterStrategy5.offset(obj5), objectParameterStrategy5.length(obj5), objectParameterStrategy6.object(obj6), objectParameterStrategy3.objectInfo(objectParameterInfo3), objectParameterStrategy6.offset(obj6), objectParameterStrategy6.length(obj6));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0088, code lost:
        if (r10 != 4) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0098, code lost:
        if (r64.isDirect() == false) goto L_0x008a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00eb A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x014e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN5(com.kenai.jffi.CallContext r46, long r47, long r49, long r51, long r53, long r55, long r57, int r59, java.lang.Object r60, com.kenai.jffi.ObjectParameterStrategy r61, com.kenai.jffi.ObjectParameterInfo r62, java.lang.Object r63, com.kenai.jffi.ObjectParameterStrategy r64, com.kenai.jffi.ObjectParameterInfo r65, java.lang.Object r66, com.kenai.jffi.ObjectParameterStrategy r67, com.kenai.jffi.ObjectParameterInfo r68, java.lang.Object r69, com.kenai.jffi.ObjectParameterStrategy r70, com.kenai.jffi.ObjectParameterInfo r71) {
        /*
            r45 = this;
            r0 = r46
            r1 = r59
            r2 = r69
            r3 = r70
            if (r1 != 0) goto L_0x001d
            long r0 = r0.contextAddress
            r2 = r47
            r4 = r49
            r6 = r51
            r8 = r53
            r10 = r55
            r12 = r57
            long r0 = com.kenai.jffi.Foreign.invokeN5(r0, r2, r4, r6, r8, r10, r12)
            return r0
        L_0x001d:
            boolean r4 = r61.isDirect()
            r5 = 2
            r6 = 4
            r7 = 3
            if (r4 != 0) goto L_0x002e
            r4 = r60
            r8 = r61
            r9 = r62
            r10 = r5
            goto L_0x005e
        L_0x002e:
            boolean r4 = r64.isDirect()
            if (r4 != 0) goto L_0x003c
            r4 = r63
            r8 = r64
            r9 = r65
            r10 = r7
            goto L_0x005e
        L_0x003c:
            boolean r4 = r67.isDirect()
            if (r4 != 0) goto L_0x004a
            r4 = r66
            r8 = r67
            r9 = r68
            r10 = r6
            goto L_0x005e
        L_0x004a:
            boolean r4 = r70.isDirect()
            r8 = 5
            if (r4 != 0) goto L_0x0057
            r9 = r71
            r4 = r2
            r10 = r8
            r8 = r3
            goto L_0x005e
        L_0x0057:
            r4 = r60
            r9 = r62
            r10 = r8
            r8 = r61
        L_0x005e:
            r11 = 1
            if (r1 != r11) goto L_0x0084
            long r12 = r0.contextAddress
            java.lang.Object r26 = r8.object(r4)
            int r27 = r8.objectInfo(r9)
            int r28 = r8.offset(r4)
            int r29 = r8.length(r4)
            r14 = r47
            r16 = r49
            r18 = r51
            r20 = r53
            r22 = r55
            r24 = r57
            long r0 = com.kenai.jffi.Foreign.invokeN5O1(r12, r14, r16, r18, r20, r22, r24, r26, r27, r28, r29)
            return r0
        L_0x0084:
            if (r10 == r5) goto L_0x0092
            if (r10 == r7) goto L_0x009b
            if (r10 == r6) goto L_0x00aa
        L_0x008a:
            r11 = r64
            r12 = r65
            r13 = r10
            r10 = r63
            goto L_0x00b6
        L_0x0092:
            int r10 = r10 + 1
            boolean r12 = r64.isDirect()
            if (r12 != 0) goto L_0x009b
            goto L_0x008a
        L_0x009b:
            int r10 = r10 + r11
            boolean r12 = r67.isDirect()
            if (r12 != 0) goto L_0x00aa
            r11 = r67
            r12 = r68
            r13 = r10
            r10 = r66
            goto L_0x00b6
        L_0x00aa:
            int r10 = r10 + r11
            boolean r11 = r70.isDirect()
            if (r11 != 0) goto L_0x008a
            r12 = r71
            r11 = r3
            r13 = r10
            r10 = r2
        L_0x00b6:
            if (r1 != r5) goto L_0x00eb
            long r14 = r0.contextAddress
            java.lang.Object r28 = r8.object(r4)
            int r29 = r8.objectInfo(r9)
            int r30 = r8.offset(r4)
            int r31 = r8.length(r4)
            java.lang.Object r32 = r11.object(r10)
            int r33 = r11.objectInfo(r12)
            int r34 = r11.offset(r10)
            int r35 = r11.length(r10)
            r16 = r47
            r18 = r49
            r20 = r51
            r22 = r53
            r24 = r55
            r26 = r57
            long r0 = com.kenai.jffi.Foreign.invokeN5O2(r14, r16, r18, r20, r22, r24, r26, r28, r29, r30, r31, r32, r33, r34, r35)
            return r0
        L_0x00eb:
            if (r13 == r7) goto L_0x00f0
            if (r13 == r6) goto L_0x00f7
            goto L_0x0102
        L_0x00f0:
            boolean r5 = r67.isDirect()
            if (r5 != 0) goto L_0x00f7
            goto L_0x0102
        L_0x00f7:
            boolean r5 = r70.isDirect()
            if (r5 != 0) goto L_0x0102
            r14 = r71
            r5 = r2
            r13 = r3
            goto L_0x0108
        L_0x0102:
            r5 = r66
            r13 = r67
            r14 = r68
        L_0x0108:
            if (r1 != r7) goto L_0x014e
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r29 = r8.object(r4)
            int r30 = r8.objectInfo(r9)
            int r31 = r8.offset(r4)
            int r32 = r8.length(r4)
            java.lang.Object r33 = r11.object(r10)
            int r34 = r11.objectInfo(r12)
            int r35 = r11.offset(r10)
            int r36 = r11.length(r10)
            java.lang.Object r37 = r13.object(r5)
            int r38 = r13.objectInfo(r14)
            int r39 = r13.offset(r5)
            int r40 = r13.length(r5)
            r17 = r47
            r19 = r49
            r21 = r51
            r23 = r53
            r25 = r55
            r27 = r57
            long r0 = com.kenai.jffi.Foreign.invokeN5O3(r15, r17, r19, r21, r23, r25, r27, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40)
            return r0
        L_0x014e:
            if (r1 != r6) goto L_0x01a4
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r29 = r8.object(r4)
            int r30 = r8.objectInfo(r9)
            int r31 = r8.offset(r4)
            int r32 = r8.length(r4)
            java.lang.Object r33 = r11.object(r10)
            int r34 = r11.objectInfo(r12)
            int r35 = r11.offset(r10)
            int r36 = r11.length(r10)
            java.lang.Object r37 = r13.object(r5)
            int r38 = r13.objectInfo(r14)
            int r39 = r13.offset(r5)
            int r40 = r13.length(r5)
            java.lang.Object r41 = r3.object(r2)
            int r42 = r70.objectInfo(r71)
            int r43 = r3.offset(r2)
            int r44 = r3.length(r2)
            r17 = r47
            r19 = r49
            r21 = r51
            r23 = r53
            r25 = r55
            r27 = r57
            long r0 = com.kenai.jffi.Foreign.invokeN5O4(r15, r17, r19, r21, r23, r25, r27, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)
            return r0
        L_0x01a4:
            java.lang.RuntimeException r0 = newObjectCountError(r59)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN5(com.kenai.jffi.CallContext, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008c, code lost:
        if (r10 != 4) goto L_0x008e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009c, code lost:
        if (r68.isDirect() == false) goto L_0x008e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00f1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0156  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN6(com.kenai.jffi.CallContext r48, long r49, long r51, long r53, long r55, long r57, long r59, long r61, int r63, java.lang.Object r64, com.kenai.jffi.ObjectParameterStrategy r65, com.kenai.jffi.ObjectParameterInfo r66, java.lang.Object r67, com.kenai.jffi.ObjectParameterStrategy r68, com.kenai.jffi.ObjectParameterInfo r69, java.lang.Object r70, com.kenai.jffi.ObjectParameterStrategy r71, com.kenai.jffi.ObjectParameterInfo r72, java.lang.Object r73, com.kenai.jffi.ObjectParameterStrategy r74, com.kenai.jffi.ObjectParameterInfo r75) {
        /*
            r47 = this;
            r0 = r48
            r1 = r63
            r2 = r73
            r3 = r74
            if (r1 != 0) goto L_0x001f
            long r0 = r0.contextAddress
            r2 = r49
            r4 = r51
            r6 = r53
            r8 = r55
            r10 = r57
            r12 = r59
            r14 = r61
            long r0 = com.kenai.jffi.Foreign.invokeN6(r0, r2, r4, r6, r8, r10, r12, r14)
            return r0
        L_0x001f:
            boolean r4 = r65.isDirect()
            r5 = 2
            r6 = 4
            r7 = 3
            if (r4 != 0) goto L_0x0030
            r4 = r64
            r8 = r65
            r9 = r66
            r10 = r5
            goto L_0x0060
        L_0x0030:
            boolean r4 = r68.isDirect()
            if (r4 != 0) goto L_0x003e
            r4 = r67
            r8 = r68
            r9 = r69
            r10 = r7
            goto L_0x0060
        L_0x003e:
            boolean r4 = r71.isDirect()
            if (r4 != 0) goto L_0x004c
            r4 = r70
            r8 = r71
            r9 = r72
            r10 = r6
            goto L_0x0060
        L_0x004c:
            boolean r4 = r74.isDirect()
            r8 = 5
            if (r4 != 0) goto L_0x0059
            r9 = r75
            r4 = r2
            r10 = r8
            r8 = r3
            goto L_0x0060
        L_0x0059:
            r4 = r64
            r9 = r66
            r10 = r8
            r8 = r65
        L_0x0060:
            r11 = 1
            if (r1 != r11) goto L_0x0088
            long r12 = r0.contextAddress
            java.lang.Object r28 = r8.object(r4)
            int r29 = r8.objectInfo(r9)
            int r30 = r8.offset(r4)
            int r31 = r8.length(r4)
            r14 = r49
            r16 = r51
            r18 = r53
            r20 = r55
            r22 = r57
            r24 = r59
            r26 = r61
            long r0 = com.kenai.jffi.Foreign.invokeN6O1(r12, r14, r16, r18, r20, r22, r24, r26, r28, r29, r30, r31)
            return r0
        L_0x0088:
            if (r10 == r5) goto L_0x0096
            if (r10 == r7) goto L_0x009f
            if (r10 == r6) goto L_0x00ae
        L_0x008e:
            r11 = r68
            r12 = r69
            r13 = r10
            r10 = r67
            goto L_0x00ba
        L_0x0096:
            int r10 = r10 + 1
            boolean r12 = r68.isDirect()
            if (r12 != 0) goto L_0x009f
            goto L_0x008e
        L_0x009f:
            int r10 = r10 + r11
            boolean r12 = r71.isDirect()
            if (r12 != 0) goto L_0x00ae
            r11 = r71
            r12 = r72
            r13 = r10
            r10 = r70
            goto L_0x00ba
        L_0x00ae:
            int r10 = r10 + r11
            boolean r11 = r74.isDirect()
            if (r11 != 0) goto L_0x008e
            r12 = r75
            r11 = r3
            r13 = r10
            r10 = r2
        L_0x00ba:
            if (r1 != r5) goto L_0x00f1
            long r14 = r0.contextAddress
            java.lang.Object r30 = r8.object(r4)
            int r31 = r8.objectInfo(r9)
            int r32 = r8.offset(r4)
            int r33 = r8.length(r4)
            java.lang.Object r34 = r11.object(r10)
            int r35 = r11.objectInfo(r12)
            int r36 = r11.offset(r10)
            int r37 = r11.length(r10)
            r16 = r49
            r18 = r51
            r20 = r53
            r22 = r55
            r24 = r57
            r26 = r59
            r28 = r61
            long r0 = com.kenai.jffi.Foreign.invokeN6O2(r14, r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37)
            return r0
        L_0x00f1:
            if (r13 == r7) goto L_0x00f6
            if (r13 == r6) goto L_0x00fd
            goto L_0x0108
        L_0x00f6:
            boolean r5 = r71.isDirect()
            if (r5 != 0) goto L_0x00fd
            goto L_0x0108
        L_0x00fd:
            boolean r5 = r74.isDirect()
            if (r5 != 0) goto L_0x0108
            r14 = r75
            r5 = r2
            r13 = r3
            goto L_0x010e
        L_0x0108:
            r5 = r70
            r13 = r71
            r14 = r72
        L_0x010e:
            if (r1 != r7) goto L_0x0156
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r31 = r8.object(r4)
            int r32 = r8.objectInfo(r9)
            int r33 = r8.offset(r4)
            int r34 = r8.length(r4)
            java.lang.Object r35 = r11.object(r10)
            int r36 = r11.objectInfo(r12)
            int r37 = r11.offset(r10)
            int r38 = r11.length(r10)
            java.lang.Object r39 = r13.object(r5)
            int r40 = r13.objectInfo(r14)
            int r41 = r13.offset(r5)
            int r42 = r13.length(r5)
            r17 = r49
            r19 = r51
            r21 = r53
            r23 = r55
            r25 = r57
            r27 = r59
            r29 = r61
            long r0 = com.kenai.jffi.Foreign.invokeN6O3(r15, r17, r19, r21, r23, r25, r27, r29, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42)
            return r0
        L_0x0156:
            if (r1 != r6) goto L_0x01ae
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r31 = r8.object(r4)
            int r32 = r8.objectInfo(r9)
            int r33 = r8.offset(r4)
            int r34 = r8.length(r4)
            java.lang.Object r35 = r11.object(r10)
            int r36 = r11.objectInfo(r12)
            int r37 = r11.offset(r10)
            int r38 = r11.length(r10)
            java.lang.Object r39 = r13.object(r5)
            int r40 = r13.objectInfo(r14)
            int r41 = r13.offset(r5)
            int r42 = r13.length(r5)
            java.lang.Object r43 = r3.object(r2)
            int r44 = r74.objectInfo(r75)
            int r45 = r3.offset(r2)
            int r46 = r3.length(r2)
            r17 = r49
            r19 = r51
            r21 = r53
            r23 = r55
            r25 = r57
            r27 = r59
            r29 = r61
            long r0 = com.kenai.jffi.Foreign.invokeN6O4(r15, r17, r19, r21, r23, r25, r27, r29, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46)
            return r0
        L_0x01ae:
            java.lang.RuntimeException r0 = newObjectCountError(r63)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN6(com.kenai.jffi.CallContext, long, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0086, code lost:
        if (r10 != 4) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0096, code lost:
        if (r60.isDirect() == false) goto L_0x0088;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ea, code lost:
        if (r14 != 4) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00fa, code lost:
        if (r63.isDirect() == false) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fd, code lost:
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0102, code lost:
        if (r66.isDirect() != false) goto L_0x00ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0104, code lost:
        r5 = r2;
        r11 = r3;
        r15 = r14;
        r14 = r67;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN4(com.kenai.jffi.CallContext r44, long r45, long r47, long r49, long r51, long r53, int r55, java.lang.Object r56, com.kenai.jffi.ObjectParameterStrategy r57, com.kenai.jffi.ObjectParameterInfo r58, java.lang.Object r59, com.kenai.jffi.ObjectParameterStrategy r60, com.kenai.jffi.ObjectParameterInfo r61, java.lang.Object r62, com.kenai.jffi.ObjectParameterStrategy r63, com.kenai.jffi.ObjectParameterInfo r64, java.lang.Object r65, com.kenai.jffi.ObjectParameterStrategy r66, com.kenai.jffi.ObjectParameterInfo r67) {
        /*
            r43 = this;
            r0 = r44
            r1 = r55
            r2 = r65
            r3 = r66
            if (r1 != 0) goto L_0x001d
            long r0 = r0.contextAddress
            r55 = r0
            r57 = r45
            r59 = r47
            r61 = r49
            r63 = r51
            r65 = r53
            long r0 = com.kenai.jffi.Foreign.invokeN4(r55, r57, r59, r61, r63, r65)
            return r0
        L_0x001d:
            boolean r4 = r57.isDirect()
            r5 = 2
            r6 = 3
            r7 = 4
            if (r4 != 0) goto L_0x002e
            r4 = r56
            r8 = r57
            r9 = r58
            r10 = r5
            goto L_0x005e
        L_0x002e:
            boolean r4 = r60.isDirect()
            if (r4 != 0) goto L_0x003c
            r4 = r59
            r8 = r60
            r9 = r61
            r10 = r6
            goto L_0x005e
        L_0x003c:
            boolean r4 = r63.isDirect()
            if (r4 != 0) goto L_0x004a
            r4 = r62
            r8 = r63
            r9 = r64
            r10 = r7
            goto L_0x005e
        L_0x004a:
            boolean r4 = r66.isDirect()
            r8 = 5
            if (r4 != 0) goto L_0x0057
            r9 = r67
            r4 = r2
            r10 = r8
            r8 = r3
            goto L_0x005e
        L_0x0057:
            r4 = r56
            r9 = r58
            r10 = r8
            r8 = r57
        L_0x005e:
            r11 = 1
            if (r1 != r11) goto L_0x0082
            long r12 = r0.contextAddress
            java.lang.Object r24 = r8.object(r4)
            int r25 = r8.objectInfo(r9)
            int r26 = r8.offset(r4)
            int r27 = r8.length(r4)
            r14 = r45
            r16 = r47
            r18 = r49
            r20 = r51
            r22 = r53
            long r0 = com.kenai.jffi.Foreign.invokeN4O1(r12, r14, r16, r18, r20, r22, r24, r25, r26, r27)
            return r0
        L_0x0082:
            if (r10 == r5) goto L_0x0090
            if (r10 == r6) goto L_0x0099
            if (r10 == r7) goto L_0x00a8
        L_0x0088:
            r12 = r60
            r13 = r61
            r14 = r10
            r10 = r59
            goto L_0x00b4
        L_0x0090:
            int r10 = r10 + 1
            boolean r12 = r60.isDirect()
            if (r12 != 0) goto L_0x0099
            goto L_0x0088
        L_0x0099:
            int r10 = r10 + r11
            boolean r12 = r63.isDirect()
            if (r12 != 0) goto L_0x00a8
            r12 = r63
            r13 = r64
            r14 = r10
            r10 = r62
            goto L_0x00b4
        L_0x00a8:
            int r10 = r10 + r11
            boolean r12 = r66.isDirect()
            if (r12 != 0) goto L_0x0088
            r13 = r67
            r12 = r3
            r14 = r10
            r10 = r2
        L_0x00b4:
            if (r1 != r5) goto L_0x00e8
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r27 = r8.object(r4)
            int r28 = r8.objectInfo(r9)
            int r29 = r8.offset(r4)
            int r30 = r8.length(r4)
            java.lang.Object r31 = r12.object(r10)
            int r32 = r12.objectInfo(r13)
            int r33 = r12.offset(r10)
            int r34 = r12.length(r10)
            r17 = r45
            r19 = r47
            r21 = r49
            r23 = r51
            r25 = r53
            long r0 = com.kenai.jffi.Foreign.invokeN4O2(r15, r17, r19, r21, r23, r25, r27, r28, r29, r30, r31, r32, r33, r34)
            return r0
        L_0x00e8:
            if (r14 == r6) goto L_0x00f4
            if (r14 == r7) goto L_0x00fd
        L_0x00ec:
            r5 = r62
            r11 = r63
            r15 = r14
            r14 = r64
            goto L_0x0109
        L_0x00f4:
            int r14 = r14 + 1
            boolean r5 = r63.isDirect()
            if (r5 != 0) goto L_0x00fd
            goto L_0x00ec
        L_0x00fd:
            int r14 = r14 + r11
            boolean r5 = r66.isDirect()
            if (r5 != 0) goto L_0x00ec
            r5 = r2
            r11 = r3
            r15 = r14
            r14 = r67
        L_0x0109:
            if (r1 != r6) goto L_0x014e
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r28 = r8.object(r4)
            int r29 = r8.objectInfo(r9)
            int r30 = r8.offset(r4)
            int r31 = r8.length(r4)
            java.lang.Object r32 = r12.object(r10)
            int r33 = r12.objectInfo(r13)
            int r34 = r12.offset(r10)
            int r35 = r12.length(r10)
            java.lang.Object r36 = r11.object(r5)
            int r37 = r11.objectInfo(r14)
            int r38 = r11.offset(r5)
            int r39 = r11.length(r5)
            r18 = r45
            r20 = r47
            r22 = r49
            r24 = r51
            r26 = r53
            long r0 = com.kenai.jffi.Foreign.invokeN4O3(r16, r18, r20, r22, r24, r26, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39)
            return r0
        L_0x014e:
            if (r15 != r7) goto L_0x01af
            boolean r6 = r66.isDirect()
            if (r6 != 0) goto L_0x01af
            if (r1 != r7) goto L_0x01aa
            long r0 = r0.contextAddress
            r15 = r0
            java.lang.Object r27 = r8.object(r4)
            int r28 = r8.objectInfo(r9)
            int r29 = r8.offset(r4)
            int r30 = r8.length(r4)
            java.lang.Object r31 = r12.object(r10)
            int r32 = r12.objectInfo(r13)
            int r33 = r12.offset(r10)
            int r34 = r12.length(r10)
            java.lang.Object r35 = r11.object(r5)
            int r36 = r11.objectInfo(r14)
            int r37 = r11.offset(r5)
            int r38 = r11.length(r5)
            java.lang.Object r39 = r3.object(r2)
            int r40 = r66.objectInfo(r67)
            int r41 = r3.offset(r2)
            int r42 = r3.length(r2)
            r17 = r45
            r19 = r47
            r21 = r49
            r23 = r51
            r25 = r53
            long r0 = com.kenai.jffi.Foreign.invokeN4O4(r15, r17, r19, r21, r23, r25, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42)
            return r0
        L_0x01aa:
            java.lang.RuntimeException r0 = newObjectCountError(r55)
            throw r0
        L_0x01af:
            java.lang.RuntimeException r0 = newInsufficientObjectCountError(r55)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN4(com.kenai.jffi.CallContext, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009b, code lost:
        if (r11 != 5) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00ab, code lost:
        if (r69.isDirect() == false) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0113, code lost:
        if (r15 != 5) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0123, code lost:
        if (r72.isDirect() == false) goto L_0x0115;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d3  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0188 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN5(com.kenai.jffi.CallContext r51, long r52, long r54, long r56, long r58, long r60, long r62, int r64, java.lang.Object r65, com.kenai.jffi.ObjectParameterStrategy r66, com.kenai.jffi.ObjectParameterInfo r67, java.lang.Object r68, com.kenai.jffi.ObjectParameterStrategy r69, com.kenai.jffi.ObjectParameterInfo r70, java.lang.Object r71, com.kenai.jffi.ObjectParameterStrategy r72, com.kenai.jffi.ObjectParameterInfo r73, java.lang.Object r74, com.kenai.jffi.ObjectParameterStrategy r75, com.kenai.jffi.ObjectParameterInfo r76, java.lang.Object r77, com.kenai.jffi.ObjectParameterStrategy r78, com.kenai.jffi.ObjectParameterInfo r79) {
        /*
            r50 = this;
            r0 = r51
            r1 = r64
            r2 = r77
            r3 = r78
            if (r1 != 0) goto L_0x001f
            long r0 = r0.contextAddress
            r64 = r0
            r66 = r52
            r68 = r54
            r70 = r56
            r72 = r58
            r74 = r60
            r76 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5(r64, r66, r68, r70, r72, r74, r76)
            return r0
        L_0x001f:
            boolean r4 = r66.isDirect()
            r5 = 2
            r6 = 3
            r7 = 5
            r8 = 4
            if (r4 != 0) goto L_0x0031
            r4 = r65
            r9 = r66
            r10 = r67
            r11 = r5
            goto L_0x006f
        L_0x0031:
            boolean r4 = r69.isDirect()
            if (r4 != 0) goto L_0x003f
            r4 = r68
            r9 = r69
            r10 = r70
            r11 = r6
            goto L_0x006f
        L_0x003f:
            boolean r4 = r72.isDirect()
            if (r4 != 0) goto L_0x004d
            r4 = r71
            r9 = r72
            r10 = r73
            r11 = r8
            goto L_0x006f
        L_0x004d:
            boolean r4 = r75.isDirect()
            if (r4 != 0) goto L_0x005b
            r4 = r74
            r9 = r75
            r10 = r76
            r11 = r7
            goto L_0x006f
        L_0x005b:
            boolean r4 = r78.isDirect()
            r9 = 6
            if (r4 != 0) goto L_0x0068
            r10 = r79
            r4 = r2
            r11 = r9
            r9 = r3
            goto L_0x006f
        L_0x0068:
            r4 = r65
            r10 = r67
            r11 = r9
            r9 = r66
        L_0x006f:
            r12 = 1
            if (r1 != r12) goto L_0x0095
            long r13 = r0.contextAddress
            java.lang.Object r27 = r9.object(r4)
            int r28 = r9.objectInfo(r10)
            int r29 = r9.offset(r4)
            int r30 = r9.length(r4)
            r15 = r52
            r17 = r54
            r19 = r56
            r21 = r58
            r23 = r60
            r25 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5O1(r13, r15, r17, r19, r21, r23, r25, r27, r28, r29, r30)
            return r0
        L_0x0095:
            if (r11 == r5) goto L_0x00a5
            if (r11 == r6) goto L_0x00ae
            if (r11 == r8) goto L_0x00bd
            if (r11 == r7) goto L_0x00cc
        L_0x009d:
            r13 = r69
            r14 = r70
            r15 = r11
            r11 = r68
            goto L_0x00d8
        L_0x00a5:
            int r11 = r11 + 1
            boolean r13 = r69.isDirect()
            if (r13 != 0) goto L_0x00ae
            goto L_0x009d
        L_0x00ae:
            int r11 = r11 + r12
            boolean r13 = r72.isDirect()
            if (r13 != 0) goto L_0x00bd
            r13 = r72
            r14 = r73
            r15 = r11
            r11 = r71
            goto L_0x00d8
        L_0x00bd:
            int r11 = r11 + r12
            boolean r13 = r75.isDirect()
            if (r13 != 0) goto L_0x00cc
            r13 = r75
            r14 = r76
            r15 = r11
            r11 = r74
            goto L_0x00d8
        L_0x00cc:
            int r11 = r11 + r12
            boolean r13 = r78.isDirect()
            if (r13 != 0) goto L_0x009d
            r14 = r79
            r13 = r3
            r15 = r11
            r11 = r2
        L_0x00d8:
            if (r1 != r5) goto L_0x010f
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r30 = r9.object(r4)
            int r31 = r9.objectInfo(r10)
            int r32 = r9.offset(r4)
            int r33 = r9.length(r4)
            java.lang.Object r34 = r13.object(r11)
            int r35 = r13.objectInfo(r14)
            int r36 = r13.offset(r11)
            int r37 = r13.length(r11)
            r18 = r52
            r20 = r54
            r22 = r56
            r24 = r58
            r26 = r60
            r28 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5O2(r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37)
            return r0
        L_0x010f:
            if (r15 == r6) goto L_0x011d
            if (r15 == r8) goto L_0x0126
            if (r15 == r7) goto L_0x0135
        L_0x0115:
            r5 = r71
            r12 = r72
            r7 = r15
            r15 = r73
            goto L_0x0141
        L_0x011d:
            int r15 = r15 + 1
            boolean r5 = r72.isDirect()
            if (r5 != 0) goto L_0x0126
            goto L_0x0115
        L_0x0126:
            int r15 = r15 + r12
            boolean r5 = r75.isDirect()
            if (r5 != 0) goto L_0x0135
            r5 = r74
            r12 = r75
            r7 = r15
            r15 = r76
            goto L_0x0141
        L_0x0135:
            int r15 = r15 + r12
            boolean r5 = r78.isDirect()
            if (r5 != 0) goto L_0x0115
            r5 = r2
            r12 = r3
            r7 = r15
            r15 = r79
        L_0x0141:
            if (r1 != r6) goto L_0x0188
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r30 = r9.object(r4)
            int r31 = r9.objectInfo(r10)
            int r32 = r9.offset(r4)
            int r33 = r9.length(r4)
            java.lang.Object r34 = r13.object(r11)
            int r35 = r13.objectInfo(r14)
            int r36 = r13.offset(r11)
            int r37 = r13.length(r11)
            java.lang.Object r38 = r12.object(r5)
            int r39 = r12.objectInfo(r15)
            int r40 = r12.offset(r5)
            int r41 = r12.length(r5)
            r18 = r52
            r20 = r54
            r22 = r56
            r24 = r58
            r26 = r60
            r28 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5O3(r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41)
            return r0
        L_0x0188:
            if (r7 == r8) goto L_0x018e
            r6 = 5
            if (r7 == r6) goto L_0x0195
            goto L_0x01a0
        L_0x018e:
            boolean r6 = r75.isDirect()
            if (r6 != 0) goto L_0x0195
            goto L_0x01a0
        L_0x0195:
            boolean r6 = r78.isDirect()
            if (r6 != 0) goto L_0x01a0
            r6 = r2
            r7 = r3
            r2 = r79
            goto L_0x01a6
        L_0x01a0:
            r6 = r74
            r7 = r75
            r2 = r76
        L_0x01a6:
            if (r1 != r8) goto L_0x01fd
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r30 = r9.object(r4)
            int r31 = r9.objectInfo(r10)
            int r32 = r9.offset(r4)
            int r33 = r9.length(r4)
            java.lang.Object r34 = r13.object(r11)
            int r35 = r13.objectInfo(r14)
            int r36 = r13.offset(r11)
            int r37 = r13.length(r11)
            java.lang.Object r38 = r12.object(r5)
            int r39 = r12.objectInfo(r15)
            int r40 = r12.offset(r5)
            int r41 = r12.length(r5)
            java.lang.Object r42 = r7.object(r6)
            int r43 = r7.objectInfo(r2)
            int r44 = r7.offset(r6)
            int r45 = r7.length(r6)
            r18 = r52
            r20 = r54
            r22 = r56
            r24 = r58
            r26 = r60
            r28 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5O4(r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45)
            return r0
        L_0x01fd:
            r8 = 5
            if (r1 != r8) goto L_0x0267
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r30 = r9.object(r4)
            int r31 = r9.objectInfo(r10)
            int r32 = r9.offset(r4)
            int r33 = r9.length(r4)
            java.lang.Object r34 = r13.object(r11)
            int r35 = r13.objectInfo(r14)
            int r36 = r13.offset(r11)
            int r37 = r13.length(r11)
            java.lang.Object r38 = r12.object(r5)
            int r39 = r12.objectInfo(r15)
            int r40 = r12.offset(r5)
            int r41 = r12.length(r5)
            java.lang.Object r42 = r7.object(r6)
            int r43 = r7.objectInfo(r2)
            int r44 = r7.offset(r6)
            int r45 = r7.length(r6)
            r0 = r77
            java.lang.Object r46 = r3.object(r0)
            int r47 = r78.objectInfo(r79)
            int r48 = r3.offset(r0)
            int r49 = r3.length(r0)
            r18 = r52
            r20 = r54
            r22 = r56
            r24 = r58
            r26 = r60
            r28 = r62
            long r0 = com.kenai.jffi.Foreign.invokeN5O5(r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49)
            return r0
        L_0x0267:
            java.lang.RuntimeException r0 = newObjectCountError(r64)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN5(com.kenai.jffi.CallContext, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009f, code lost:
        if (r11 != 5) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00af, code lost:
        if (r73.isDirect() == false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0119, code lost:
        if (r15 != 5) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0129, code lost:
        if (r76.isDirect() == false) goto L_0x011b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0190 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0207  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN6(com.kenai.jffi.CallContext r53, long r54, long r56, long r58, long r60, long r62, long r64, long r66, int r68, java.lang.Object r69, com.kenai.jffi.ObjectParameterStrategy r70, com.kenai.jffi.ObjectParameterInfo r71, java.lang.Object r72, com.kenai.jffi.ObjectParameterStrategy r73, com.kenai.jffi.ObjectParameterInfo r74, java.lang.Object r75, com.kenai.jffi.ObjectParameterStrategy r76, com.kenai.jffi.ObjectParameterInfo r77, java.lang.Object r78, com.kenai.jffi.ObjectParameterStrategy r79, com.kenai.jffi.ObjectParameterInfo r80, java.lang.Object r81, com.kenai.jffi.ObjectParameterStrategy r82, com.kenai.jffi.ObjectParameterInfo r83) {
        /*
            r52 = this;
            r0 = r53
            r1 = r68
            r2 = r81
            r3 = r82
            if (r1 != 0) goto L_0x0021
            long r0 = r0.contextAddress
            r68 = r0
            r70 = r54
            r72 = r56
            r74 = r58
            r76 = r60
            r78 = r62
            r80 = r64
            r82 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6(r68, r70, r72, r74, r76, r78, r80, r82)
            return r0
        L_0x0021:
            boolean r4 = r70.isDirect()
            r5 = 2
            r6 = 3
            r7 = 5
            r8 = 4
            if (r4 != 0) goto L_0x0033
            r4 = r69
            r9 = r70
            r10 = r71
            r11 = r5
            goto L_0x0071
        L_0x0033:
            boolean r4 = r73.isDirect()
            if (r4 != 0) goto L_0x0041
            r4 = r72
            r9 = r73
            r10 = r74
            r11 = r6
            goto L_0x0071
        L_0x0041:
            boolean r4 = r76.isDirect()
            if (r4 != 0) goto L_0x004f
            r4 = r75
            r9 = r76
            r10 = r77
            r11 = r8
            goto L_0x0071
        L_0x004f:
            boolean r4 = r79.isDirect()
            if (r4 != 0) goto L_0x005d
            r4 = r78
            r9 = r79
            r10 = r80
            r11 = r7
            goto L_0x0071
        L_0x005d:
            boolean r4 = r82.isDirect()
            r9 = 6
            if (r4 != 0) goto L_0x006a
            r10 = r83
            r4 = r2
            r11 = r9
            r9 = r3
            goto L_0x0071
        L_0x006a:
            r4 = r69
            r10 = r71
            r11 = r9
            r9 = r70
        L_0x0071:
            r12 = 1
            if (r1 != r12) goto L_0x0099
            long r13 = r0.contextAddress
            java.lang.Object r29 = r9.object(r4)
            int r30 = r9.objectInfo(r10)
            int r31 = r9.offset(r4)
            int r32 = r9.length(r4)
            r15 = r54
            r17 = r56
            r19 = r58
            r21 = r60
            r23 = r62
            r25 = r64
            r27 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6O1(r13, r15, r17, r19, r21, r23, r25, r27, r29, r30, r31, r32)
            return r0
        L_0x0099:
            if (r11 == r5) goto L_0x00a9
            if (r11 == r6) goto L_0x00b2
            if (r11 == r8) goto L_0x00c1
            if (r11 == r7) goto L_0x00d0
        L_0x00a1:
            r13 = r73
            r14 = r74
            r15 = r11
            r11 = r72
            goto L_0x00dc
        L_0x00a9:
            int r11 = r11 + 1
            boolean r13 = r73.isDirect()
            if (r13 != 0) goto L_0x00b2
            goto L_0x00a1
        L_0x00b2:
            int r11 = r11 + r12
            boolean r13 = r76.isDirect()
            if (r13 != 0) goto L_0x00c1
            r13 = r76
            r14 = r77
            r15 = r11
            r11 = r75
            goto L_0x00dc
        L_0x00c1:
            int r11 = r11 + r12
            boolean r13 = r79.isDirect()
            if (r13 != 0) goto L_0x00d0
            r13 = r79
            r14 = r80
            r15 = r11
            r11 = r78
            goto L_0x00dc
        L_0x00d0:
            int r11 = r11 + r12
            boolean r13 = r82.isDirect()
            if (r13 != 0) goto L_0x00a1
            r14 = r83
            r13 = r3
            r15 = r11
            r11 = r2
        L_0x00dc:
            if (r1 != r5) goto L_0x0115
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r32 = r9.object(r4)
            int r33 = r9.objectInfo(r10)
            int r34 = r9.offset(r4)
            int r35 = r9.length(r4)
            java.lang.Object r36 = r13.object(r11)
            int r37 = r13.objectInfo(r14)
            int r38 = r13.offset(r11)
            int r39 = r13.length(r11)
            r18 = r54
            r20 = r56
            r22 = r58
            r24 = r60
            r26 = r62
            r28 = r64
            r30 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6O2(r16, r18, r20, r22, r24, r26, r28, r30, r32, r33, r34, r35, r36, r37, r38, r39)
            return r0
        L_0x0115:
            if (r15 == r6) goto L_0x0123
            if (r15 == r8) goto L_0x012c
            if (r15 == r7) goto L_0x013b
        L_0x011b:
            r5 = r75
            r12 = r76
            r7 = r15
            r15 = r77
            goto L_0x0147
        L_0x0123:
            int r15 = r15 + 1
            boolean r5 = r76.isDirect()
            if (r5 != 0) goto L_0x012c
            goto L_0x011b
        L_0x012c:
            int r15 = r15 + r12
            boolean r5 = r79.isDirect()
            if (r5 != 0) goto L_0x013b
            r5 = r78
            r12 = r79
            r7 = r15
            r15 = r80
            goto L_0x0147
        L_0x013b:
            int r15 = r15 + r12
            boolean r5 = r82.isDirect()
            if (r5 != 0) goto L_0x011b
            r5 = r2
            r12 = r3
            r7 = r15
            r15 = r83
        L_0x0147:
            if (r1 != r6) goto L_0x0190
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r32 = r9.object(r4)
            int r33 = r9.objectInfo(r10)
            int r34 = r9.offset(r4)
            int r35 = r9.length(r4)
            java.lang.Object r36 = r13.object(r11)
            int r37 = r13.objectInfo(r14)
            int r38 = r13.offset(r11)
            int r39 = r13.length(r11)
            java.lang.Object r40 = r12.object(r5)
            int r41 = r12.objectInfo(r15)
            int r42 = r12.offset(r5)
            int r43 = r12.length(r5)
            r18 = r54
            r20 = r56
            r22 = r58
            r24 = r60
            r26 = r62
            r28 = r64
            r30 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6O3(r16, r18, r20, r22, r24, r26, r28, r30, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43)
            return r0
        L_0x0190:
            if (r7 == r8) goto L_0x0196
            r6 = 5
            if (r7 == r6) goto L_0x019d
            goto L_0x01a8
        L_0x0196:
            boolean r6 = r79.isDirect()
            if (r6 != 0) goto L_0x019d
            goto L_0x01a8
        L_0x019d:
            boolean r6 = r82.isDirect()
            if (r6 != 0) goto L_0x01a8
            r6 = r2
            r7 = r3
            r2 = r83
            goto L_0x01ae
        L_0x01a8:
            r6 = r78
            r7 = r79
            r2 = r80
        L_0x01ae:
            if (r1 != r8) goto L_0x0207
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r32 = r9.object(r4)
            int r33 = r9.objectInfo(r10)
            int r34 = r9.offset(r4)
            int r35 = r9.length(r4)
            java.lang.Object r36 = r13.object(r11)
            int r37 = r13.objectInfo(r14)
            int r38 = r13.offset(r11)
            int r39 = r13.length(r11)
            java.lang.Object r40 = r12.object(r5)
            int r41 = r12.objectInfo(r15)
            int r42 = r12.offset(r5)
            int r43 = r12.length(r5)
            java.lang.Object r44 = r7.object(r6)
            int r45 = r7.objectInfo(r2)
            int r46 = r7.offset(r6)
            int r47 = r7.length(r6)
            r18 = r54
            r20 = r56
            r22 = r58
            r24 = r60
            r26 = r62
            r28 = r64
            r30 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6O4(r16, r18, r20, r22, r24, r26, r28, r30, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
            return r0
        L_0x0207:
            r8 = 5
            if (r1 != r8) goto L_0x0273
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r32 = r9.object(r4)
            int r33 = r9.objectInfo(r10)
            int r34 = r9.offset(r4)
            int r35 = r9.length(r4)
            java.lang.Object r36 = r13.object(r11)
            int r37 = r13.objectInfo(r14)
            int r38 = r13.offset(r11)
            int r39 = r13.length(r11)
            java.lang.Object r40 = r12.object(r5)
            int r41 = r12.objectInfo(r15)
            int r42 = r12.offset(r5)
            int r43 = r12.length(r5)
            java.lang.Object r44 = r7.object(r6)
            int r45 = r7.objectInfo(r2)
            int r46 = r7.offset(r6)
            int r47 = r7.length(r6)
            r0 = r81
            java.lang.Object r48 = r3.object(r0)
            int r49 = r82.objectInfo(r83)
            int r50 = r3.offset(r0)
            int r51 = r3.length(r0)
            r18 = r54
            r20 = r56
            r22 = r58
            r24 = r60
            r26 = r62
            r28 = r64
            r30 = r66
            long r0 = com.kenai.jffi.Foreign.invokeN6O5(r16, r18, r20, r22, r24, r26, r28, r30, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51)
            return r0
        L_0x0273:
            java.lang.RuntimeException r0 = newObjectCountError(r68)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN6(com.kenai.jffi.CallContext, long, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b0, code lost:
        if (r12 != 6) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c0, code lost:
        if (r110.isDirect() == false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0151, code lost:
        if (r113.isDirect() == false) goto L_0x013d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e1, code lost:
        if (r116.isDirect() == false) goto L_0x01cf;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x02eb  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x025b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long invokeN6(com.kenai.jffi.CallContext r90, long r91, long r93, long r95, long r97, long r99, long r101, long r103, int r105, java.lang.Object r106, com.kenai.jffi.ObjectParameterStrategy r107, com.kenai.jffi.ObjectParameterInfo r108, java.lang.Object r109, com.kenai.jffi.ObjectParameterStrategy r110, com.kenai.jffi.ObjectParameterInfo r111, java.lang.Object r112, com.kenai.jffi.ObjectParameterStrategy r113, com.kenai.jffi.ObjectParameterInfo r114, java.lang.Object r115, com.kenai.jffi.ObjectParameterStrategy r116, com.kenai.jffi.ObjectParameterInfo r117, java.lang.Object r118, com.kenai.jffi.ObjectParameterStrategy r119, com.kenai.jffi.ObjectParameterInfo r120, java.lang.Object r121, com.kenai.jffi.ObjectParameterStrategy r122, com.kenai.jffi.ObjectParameterInfo r123) {
        /*
            r89 = this;
            r0 = r90
            r1 = r105
            r2 = r121
            r3 = r122
            if (r1 != 0) goto L_0x0021
            long r0 = r0.contextAddress
            r105 = r0
            r107 = r91
            r109 = r93
            r111 = r95
            r113 = r97
            r115 = r99
            r117 = r101
            r119 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6(r105, r107, r109, r111, r113, r115, r117, r119)
            return r0
        L_0x0021:
            boolean r4 = r107.isDirect()
            r5 = 2
            r6 = 3
            r7 = 4
            r8 = 6
            r9 = 5
            if (r4 != 0) goto L_0x0034
            r4 = r106
            r10 = r107
            r11 = r108
            r12 = r5
            goto L_0x0080
        L_0x0034:
            boolean r4 = r110.isDirect()
            if (r4 != 0) goto L_0x0042
            r4 = r109
            r10 = r110
            r11 = r111
            r12 = r6
            goto L_0x0080
        L_0x0042:
            boolean r4 = r113.isDirect()
            if (r4 != 0) goto L_0x0050
            r4 = r112
            r10 = r113
            r11 = r114
            r12 = r7
            goto L_0x0080
        L_0x0050:
            boolean r4 = r116.isDirect()
            if (r4 != 0) goto L_0x005e
            r4 = r115
            r10 = r116
            r11 = r117
            r12 = r9
            goto L_0x0080
        L_0x005e:
            boolean r4 = r119.isDirect()
            if (r4 != 0) goto L_0x006c
            r4 = r118
            r10 = r119
            r11 = r120
            r12 = r8
            goto L_0x0080
        L_0x006c:
            boolean r4 = r122.isDirect()
            r10 = 7
            if (r4 != 0) goto L_0x0079
            r11 = r123
            r4 = r2
            r12 = r10
            r10 = r3
            goto L_0x0080
        L_0x0079:
            r4 = r106
            r11 = r108
            r12 = r10
            r10 = r107
        L_0x0080:
            r13 = 1
            if (r1 != r13) goto L_0x00a8
            long r14 = r0.contextAddress
            java.lang.Object r30 = r10.object(r4)
            int r31 = r10.objectInfo(r11)
            int r32 = r10.offset(r4)
            int r33 = r10.length(r4)
            r16 = r91
            r18 = r93
            r20 = r95
            r22 = r97
            r24 = r99
            r26 = r101
            r28 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O1(r14, r16, r18, r20, r22, r24, r26, r28, r30, r31, r32, r33)
            return r0
        L_0x00a8:
            if (r12 == r5) goto L_0x00ba
            if (r12 == r6) goto L_0x00c3
            if (r12 == r7) goto L_0x00d2
            if (r12 == r9) goto L_0x00e1
            if (r12 == r8) goto L_0x00f0
        L_0x00b2:
            r14 = r110
            r15 = r111
            r13 = r12
            r12 = r109
            goto L_0x00fc
        L_0x00ba:
            int r12 = r12 + 1
            boolean r14 = r110.isDirect()
            if (r14 != 0) goto L_0x00c3
            goto L_0x00b2
        L_0x00c3:
            int r12 = r12 + r13
            boolean r14 = r113.isDirect()
            if (r14 != 0) goto L_0x00d2
            r14 = r113
            r15 = r114
            r13 = r12
            r12 = r112
            goto L_0x00fc
        L_0x00d2:
            int r12 = r12 + r13
            boolean r14 = r116.isDirect()
            if (r14 != 0) goto L_0x00e1
            r14 = r116
            r15 = r117
            r13 = r12
            r12 = r115
            goto L_0x00fc
        L_0x00e1:
            int r12 = r12 + r13
            boolean r14 = r119.isDirect()
            if (r14 != 0) goto L_0x00f0
            r14 = r119
            r15 = r120
            r13 = r12
            r12 = r118
            goto L_0x00fc
        L_0x00f0:
            int r12 = r12 + r13
            boolean r14 = r122.isDirect()
            if (r14 != 0) goto L_0x00b2
            r15 = r123
            r14 = r3
            r13 = r12
            r12 = r2
        L_0x00fc:
            if (r1 != r5) goto L_0x0135
            long r0 = r0.contextAddress
            r16 = r0
            java.lang.Object r32 = r10.object(r4)
            int r33 = r10.objectInfo(r11)
            int r34 = r10.offset(r4)
            int r35 = r10.length(r4)
            java.lang.Object r36 = r14.object(r12)
            int r37 = r14.objectInfo(r15)
            int r38 = r14.offset(r12)
            int r39 = r14.length(r12)
            r18 = r91
            r20 = r93
            r22 = r95
            r24 = r97
            r26 = r99
            r28 = r101
            r30 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O2(r16, r18, r20, r22, r24, r26, r28, r30, r32, r33, r34, r35, r36, r37, r38, r39)
            return r0
        L_0x0135:
            if (r13 == r6) goto L_0x014b
            if (r13 == r7) goto L_0x0149
            if (r13 == r9) goto L_0x0147
            if (r13 == r8) goto L_0x0145
        L_0x013d:
            r5 = r112
            r8 = r114
            r9 = r13
            r13 = r113
            goto L_0x017e
        L_0x0145:
            r5 = 1
            goto L_0x0172
        L_0x0147:
            r5 = 1
            goto L_0x0163
        L_0x0149:
            r5 = 1
            goto L_0x0154
        L_0x014b:
            int r13 = r13 + 1
            boolean r5 = r113.isDirect()
            if (r5 != 0) goto L_0x0149
            goto L_0x013d
        L_0x0154:
            int r13 = r13 + r5
            boolean r16 = r116.isDirect()
            if (r16 != 0) goto L_0x0163
            r5 = r115
            r8 = r117
            r9 = r13
            r13 = r116
            goto L_0x017e
        L_0x0163:
            int r13 = r13 + r5
            boolean r16 = r119.isDirect()
            if (r16 != 0) goto L_0x0172
            r5 = r118
            r8 = r120
            r9 = r13
            r13 = r119
            goto L_0x017e
        L_0x0172:
            int r13 = r13 + r5
            boolean r5 = r122.isDirect()
            if (r5 != 0) goto L_0x013d
            r8 = r123
            r5 = r2
            r9 = r13
            r13 = r3
        L_0x017e:
            if (r1 != r6) goto L_0x01c7
            long r0 = r0.contextAddress
            r18 = r0
            java.lang.Object r34 = r10.object(r4)
            int r35 = r10.objectInfo(r11)
            int r36 = r10.offset(r4)
            int r37 = r10.length(r4)
            java.lang.Object r38 = r14.object(r12)
            int r39 = r14.objectInfo(r15)
            int r40 = r14.offset(r12)
            int r41 = r14.length(r12)
            java.lang.Object r42 = r13.object(r5)
            int r43 = r13.objectInfo(r8)
            int r44 = r13.offset(r5)
            int r45 = r13.length(r5)
            r20 = r91
            r22 = r93
            r24 = r95
            r26 = r97
            r28 = r99
            r30 = r101
            r32 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O3(r18, r20, r22, r24, r26, r28, r30, r32, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45)
            return r0
        L_0x01c7:
            if (r9 == r7) goto L_0x01db
            r6 = 5
            if (r9 == r6) goto L_0x01d9
            r6 = 6
            if (r9 == r6) goto L_0x01d7
        L_0x01cf:
            r6 = r115
            r2 = r117
            r3 = r9
            r9 = r116
            goto L_0x0202
        L_0x01d7:
            r6 = 1
            goto L_0x01f3
        L_0x01d9:
            r6 = 1
            goto L_0x01e4
        L_0x01db:
            int r9 = r9 + 1
            boolean r6 = r116.isDirect()
            if (r6 != 0) goto L_0x01d9
            goto L_0x01cf
        L_0x01e4:
            int r9 = r9 + r6
            boolean r18 = r119.isDirect()
            if (r18 != 0) goto L_0x01f3
            r6 = r118
            r2 = r120
            r3 = r9
            r9 = r119
            goto L_0x0202
        L_0x01f3:
            int r9 = r9 + r6
            boolean r6 = r122.isDirect()
            if (r6 != 0) goto L_0x01cf
            r6 = r2
            r2 = r123
            r88 = r9
            r9 = r3
            r3 = r88
        L_0x0202:
            if (r1 != r7) goto L_0x025b
            long r0 = r0.contextAddress
            r18 = r0
            java.lang.Object r34 = r10.object(r4)
            int r35 = r10.objectInfo(r11)
            int r36 = r10.offset(r4)
            int r37 = r10.length(r4)
            java.lang.Object r38 = r14.object(r12)
            int r39 = r14.objectInfo(r15)
            int r40 = r14.offset(r12)
            int r41 = r14.length(r12)
            java.lang.Object r42 = r13.object(r5)
            int r43 = r13.objectInfo(r8)
            int r44 = r13.offset(r5)
            int r45 = r13.length(r5)
            java.lang.Object r46 = r9.object(r6)
            int r47 = r9.objectInfo(r2)
            int r48 = r9.offset(r6)
            int r49 = r9.length(r6)
            r20 = r91
            r22 = r93
            r24 = r95
            r26 = r97
            r28 = r99
            r30 = r101
            r32 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O4(r18, r20, r22, r24, r26, r28, r30, r32, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49)
            return r0
        L_0x025b:
            r7 = 5
            if (r3 == r7) goto L_0x0262
            r7 = 6
            if (r3 == r7) goto L_0x0269
            goto L_0x0277
        L_0x0262:
            boolean r3 = r119.isDirect()
            if (r3 != 0) goto L_0x0269
            goto L_0x0277
        L_0x0269:
            boolean r3 = r122.isDirect()
            if (r3 != 0) goto L_0x0277
            r17 = r121
            r7 = r122
            r50 = r123
        L_0x0275:
            r3 = 5
            goto L_0x027e
        L_0x0277:
            r17 = r118
            r7 = r119
            r50 = r120
            goto L_0x0275
        L_0x027e:
            if (r1 != r3) goto L_0x02eb
            long r0 = r0.contextAddress
            r51 = r0
            java.lang.Object r67 = r10.object(r4)
            int r68 = r10.objectInfo(r11)
            int r69 = r10.offset(r4)
            int r70 = r10.length(r4)
            java.lang.Object r71 = r14.object(r12)
            int r72 = r14.objectInfo(r15)
            int r73 = r14.offset(r12)
            int r74 = r14.length(r12)
            java.lang.Object r75 = r13.object(r5)
            int r76 = r13.objectInfo(r8)
            int r77 = r13.offset(r5)
            int r78 = r13.length(r5)
            java.lang.Object r79 = r9.object(r6)
            int r80 = r9.objectInfo(r2)
            int r81 = r9.offset(r6)
            int r82 = r9.length(r6)
            r3 = r17
            java.lang.Object r83 = r7.object(r3)
            r0 = r50
            int r84 = r7.objectInfo(r0)
            int r85 = r7.offset(r3)
            int r86 = r7.length(r3)
            r53 = r91
            r55 = r93
            r57 = r95
            r59 = r97
            r61 = r99
            r63 = r101
            r65 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O5(r51, r53, r55, r57, r59, r61, r63, r65, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86)
            return r0
        L_0x02eb:
            r16 = r17
            r87 = r50
            r3 = 6
            if (r1 != r3) goto L_0x0371
            long r0 = r0.contextAddress
            r17 = r0
            java.lang.Object r33 = r10.object(r4)
            int r34 = r10.objectInfo(r11)
            int r35 = r10.offset(r4)
            int r36 = r10.length(r4)
            java.lang.Object r37 = r14.object(r12)
            int r38 = r14.objectInfo(r15)
            int r39 = r14.offset(r12)
            int r40 = r14.length(r12)
            java.lang.Object r41 = r13.object(r5)
            int r42 = r13.objectInfo(r8)
            int r43 = r13.offset(r5)
            int r44 = r13.length(r5)
            java.lang.Object r45 = r9.object(r6)
            int r46 = r9.objectInfo(r2)
            int r47 = r9.offset(r6)
            int r48 = r9.length(r6)
            r0 = r16
            java.lang.Object r49 = r7.object(r0)
            r1 = r87
            int r50 = r7.objectInfo(r1)
            int r51 = r7.offset(r0)
            int r52 = r7.length(r0)
            r0 = r121
            r1 = r122
            java.lang.Object r53 = r1.object(r0)
            int r54 = r122.objectInfo(r123)
            int r55 = r1.offset(r0)
            int r56 = r1.length(r0)
            r19 = r91
            r21 = r93
            r23 = r95
            r25 = r97
            r27 = r99
            r29 = r101
            r31 = r103
            long r0 = com.kenai.jffi.Foreign.invokeN6O6(r17, r19, r21, r23, r25, r27, r29, r31, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56)
            return r0
        L_0x0371:
            java.lang.RuntimeException r0 = newObjectCountError(r105)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jffi.Invoker.invokeN6(com.kenai.jffi.CallContext, long, long, long, long, long, long, long, int, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo, java.lang.Object, com.kenai.jffi.ObjectParameterStrategy, com.kenai.jffi.ObjectParameterInfo):long");
    }
}
