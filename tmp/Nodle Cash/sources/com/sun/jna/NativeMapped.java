package com.sun.jna;

public interface NativeMapped {
    Object fromNative(Object obj, FromNativeContext fromNativeContext);

    Class<?> nativeType();

    Object toNative();
}
