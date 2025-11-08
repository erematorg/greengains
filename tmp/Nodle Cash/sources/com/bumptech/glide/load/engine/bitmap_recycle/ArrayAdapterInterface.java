package com.bumptech.glide.load.engine.bitmap_recycle;

interface ArrayAdapterInterface<T> {
    int getArrayLength(T t2);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i3);
}
