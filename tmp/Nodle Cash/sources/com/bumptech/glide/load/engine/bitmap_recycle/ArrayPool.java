package com.bumptech.glide.load.engine.bitmap_recycle;

public interface ArrayPool {
    public static final int STANDARD_BUFFER_SIZE_BYTES = 65536;

    void clearMemory();

    <T> T get(int i3, Class<T> cls);

    <T> T getExact(int i3, Class<T> cls);

    <T> void put(T t2);

    @Deprecated
    <T> void put(T t2, Class<T> cls);

    void trimMemory(int i3);
}
