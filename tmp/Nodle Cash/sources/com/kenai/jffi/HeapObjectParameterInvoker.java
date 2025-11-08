package com.kenai.jffi;

import com.kenai.jffi.HeapInvocationBuffer;

final class HeapObjectParameterInvoker extends ObjectParameterInvoker {
    private final Foreign foreign;

    public HeapObjectParameterInvoker(Foreign foreign2) {
        this.foreign = foreign2;
    }

    private static int encode(HeapInvocationBuffer.Encoder encoder, byte[] bArr, int i3, Type type, long j2) {
        return type.size() <= 4 ? encoder.putInt(bArr, i3, (int) j2) : encoder.putLong(bArr, i3, j2);
    }

    private static byte[] encodeN3(Function function, long j2, long j3, long j4) {
        HeapInvocationBuffer.Encoder instance = HeapInvocationBuffer.Encoder.getInstance();
        byte[] bArr = new byte[instance.getBufferSize(function.getCallContext())];
        HeapInvocationBuffer.Encoder encoder = instance;
        byte[] bArr2 = bArr;
        encode(encoder, bArr2, encode(encoder, bArr2, encode(encoder, bArr2, 0, function.getParameterType(0), j2), function.getParameterType(1), j3), function.getParameterType(2), j4);
        return bArr;
    }

    private static byte[] encodeN4(Function function, long j2, long j3, long j4, long j5) {
        Function function2 = function;
        HeapInvocationBuffer.Encoder instance = HeapInvocationBuffer.Encoder.getInstance();
        byte[] bArr = new byte[instance.getBufferSize(function.getCallContext())];
        byte[] bArr2 = bArr;
        HeapInvocationBuffer.Encoder encoder = instance;
        HeapInvocationBuffer.Encoder encoder2 = instance;
        encode(instance, bArr, encode(encoder2, bArr2, encode(encoder, bArr2, encode(instance, bArr2, 0, function.getParameterType(0), j2), function.getParameterType(1), j3), function.getParameterType(2), j4), function.getParameterType(3), j5);
        return bArr;
    }

    private static byte[] encodeN5(Function function, long j2, long j3, long j4, long j5, long j6) {
        Function function2 = function;
        HeapInvocationBuffer.Encoder instance = HeapInvocationBuffer.Encoder.getInstance();
        byte[] bArr = new byte[instance.getBufferSize(function.getCallContext())];
        byte[] bArr2 = bArr;
        HeapInvocationBuffer.Encoder encoder = instance;
        HeapInvocationBuffer.Encoder encoder2 = instance;
        encode(instance, bArr, encode(instance, bArr, encode(encoder2, bArr2, encode(encoder, bArr2, encode(instance, bArr2, 0, function.getParameterType(0), j2), function.getParameterType(1), j3), function.getParameterType(2), j4), function.getParameterType(3), j5), function.getParameterType(4), j6);
        return bArr;
    }

    private static byte[] encodeN6(Function function, long j2, long j3, long j4, long j5, long j6, long j7) {
        Function function2 = function;
        HeapInvocationBuffer.Encoder instance = HeapInvocationBuffer.Encoder.getInstance();
        byte[] bArr = new byte[instance.getBufferSize(function.getCallContext())];
        byte[] bArr2 = bArr;
        HeapInvocationBuffer.Encoder encoder = instance;
        HeapInvocationBuffer.Encoder encoder2 = instance;
        int encode = encode(encoder2, bArr2, encode(encoder, bArr2, encode(instance, bArr2, 0, function.getParameterType(0), j2), function.getParameterType(1), j3), function.getParameterType(2), j4);
        HeapInvocationBuffer.Encoder encoder3 = instance;
        byte[] bArr3 = bArr;
        encode(instance, bArr, encode(encoder3, bArr3, encode(encoder3, bArr3, encode, function.getParameterType(3), j5), function.getParameterType(4), j6), function.getParameterType(5), j7);
        return bArr;
    }

    private long invokeO1(Function function, byte[] bArr, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        if (function.getReturnType().size() == 8) {
            return Foreign.invokeArrayO1Int64(function.getContextAddress(), function.getFunctionAddress(), bArr, obj, objectParameterInfo.asObjectInfo(), i3, i4);
        }
        return (long) Foreign.invokeArrayO1Int32(function.getContextAddress(), function.getFunctionAddress(), bArr, obj, objectParameterInfo.asObjectInfo(), i3, i4);
    }

    private long invokeO2(Function function, byte[] bArr, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        if (function.getReturnType().size() == 8) {
            return Foreign.invokeArrayO2Int64(function.getContextAddress(), function.getFunctionAddress(), bArr, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
        }
        return (long) Foreign.invokeArrayO2Int32(function.getContextAddress(), function.getFunctionAddress(), bArr, obj, objectParameterInfo.asObjectInfo(), i3, i4, obj2, objectParameterInfo2.asObjectInfo(), i5, i6);
    }

    private long invokeO3(Function function, byte[] bArr, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        int[] iArr = {objectParameterInfo.asObjectInfo(), i3, i4, objectParameterInfo2.asObjectInfo(), i5, i6, objectParameterInfo3.asObjectInfo(), i7, i8};
        Object obj4 = obj;
        Object[] objArr = {obj, obj2, obj3};
        if (function.getReturnType().size() == 8) {
            return Foreign.invokeArrayWithObjectsInt64(function.getContextAddress(), function.getFunctionAddress(), bArr, 3, iArr, objArr);
        }
        return (long) Foreign.invokeArrayWithObjectsInt32(function.getContextAddress(), function.getFunctionAddress(), bArr, 3, iArr, objArr);
    }

    public long invokeN1O1rN(Function function, long j2, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return invokeO1(function, new byte[HeapInvocationBuffer.Encoder.getInstance().getBufferSize(function.getCallContext())], obj, i3, i4, objectParameterInfo);
    }

    public long invokeN2O1rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        HeapInvocationBuffer.Encoder instance = HeapInvocationBuffer.Encoder.getInstance();
        byte[] bArr = new byte[instance.getBufferSize(function.getCallContext())];
        byte[] bArr2 = bArr;
        HeapInvocationBuffer.Encoder encoder = instance;
        encode(encoder, bArr2, encode(instance, bArr2, 0, function.getParameterType(0), j2), function.getParameterType(1), j3);
        return invokeO1(function, bArr, obj, i3, i4, objectParameterInfo);
    }

    public long invokeN2O2rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return invokeO2(function, new byte[HeapInvocationBuffer.Encoder.getInstance().getBufferSize(function.getCallContext())], obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2);
    }

    public long invokeN3O1rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return invokeO1(function, encodeN3(function, j2, j3, j4), obj, i3, i4, objectParameterInfo);
    }

    public long invokeN3O2rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return invokeO2(function, encodeN3(function, j2, j3, j4), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2);
    }

    public long invokeN3O3rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return invokeO3(function, encodeN3(function, j2, j3, j4), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2, obj3, i7, i8, objectParameterInfo3);
    }

    public long invokeN4O1rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return invokeO1(function, encodeN4(function, j2, j3, j4, j5), obj, i3, i4, objectParameterInfo);
    }

    public long invokeN4O2rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return invokeO2(function, encodeN4(function, j2, j3, j4, j5), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2);
    }

    public long invokeN4O3rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return invokeO3(function, encodeN4(function, j2, j3, j4, j5), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2, obj3, i7, i8, objectParameterInfo3);
    }

    public long invokeN5O1rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return invokeO1(function, encodeN5(function, j2, j3, j4, j5, j6), obj, i3, i4, objectParameterInfo);
    }

    public long invokeN5O2rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return invokeO2(function, encodeN5(function, j2, j3, j4, j5, j6), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2);
    }

    public long invokeN5O3rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return invokeO3(function, encodeN5(function, j2, j3, j4, j5, j6), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2, obj3, i7, i8, objectParameterInfo3);
    }

    public long invokeN6O1rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo) {
        return invokeO1(function, encodeN6(function, j2, j3, j4, j5, j6, j7), obj, i3, i4, objectParameterInfo);
    }

    public long invokeN6O2rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2) {
        return invokeO2(function, encodeN6(function, j2, j3, j4, j5, j6, j7), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2);
    }

    public long invokeN6O3rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3) {
        return invokeO3(function, encodeN6(function, j2, j3, j4, j5, j6, j7), obj, i3, i4, objectParameterInfo, obj2, i5, i6, objectParameterInfo2, obj3, i7, i8, objectParameterInfo3);
    }

    public final boolean isNative() {
        return false;
    }
}
