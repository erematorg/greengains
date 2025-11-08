package com.bumptech.glide.load.resource;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    public SimpleResource(@NonNull T t2) {
        this.data = Preconditions.checkNotNull(t2);
    }

    @NonNull
    public final T get() {
        return this.data;
    }

    @NonNull
    public Class<T> getResourceClass() {
        return this.data.getClass();
    }

    public final int getSize() {
        return 1;
    }

    public void recycle() {
    }
}
