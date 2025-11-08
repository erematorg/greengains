package com.kenai.jffi;

public abstract class ObjectParameterInvoker {

    public static final class SingletonHolder {
        static final ObjectParameterInvoker INSTANCE = (Foreign.getInstance().getVersion() >= 65546 ? ObjectParameterInvoker.newNativeInvoker() : ObjectParameterInvoker.newHeapInvoker());

        private SingletonHolder() {
        }
    }

    public static ObjectParameterInvoker getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static ObjectParameterInvoker newHeapInvoker() {
        return new HeapObjectParameterInvoker(Foreign.getInstance());
    }

    public static ObjectParameterInvoker newNativeInvoker() {
        return new NativeObjectParameterInvoker(Foreign.getInstance());
    }

    public abstract long invokeN1O1rN(Function function, long j2, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN2O1rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN2O2rN(Function function, long j2, long j3, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2);

    public abstract long invokeN3O1rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN3O2rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2);

    public abstract long invokeN3O3rN(Function function, long j2, long j3, long j4, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3);

    public abstract long invokeN4O1rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN4O2rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2);

    public abstract long invokeN4O3rN(Function function, long j2, long j3, long j4, long j5, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3);

    public abstract long invokeN5O1rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN5O2rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2);

    public abstract long invokeN5O3rN(Function function, long j2, long j3, long j4, long j5, long j6, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3);

    public abstract long invokeN6O1rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo);

    public abstract long invokeN6O2rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2);

    public abstract long invokeN6O3rN(Function function, long j2, long j3, long j4, long j5, long j6, long j7, Object obj, int i3, int i4, ObjectParameterInfo objectParameterInfo, Object obj2, int i5, int i6, ObjectParameterInfo objectParameterInfo2, Object obj3, int i7, int i8, ObjectParameterInfo objectParameterInfo3);

    public abstract boolean isNative();
}
