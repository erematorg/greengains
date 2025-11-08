package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import java.io.IOException;

public interface DataRewinder<T> {

    public interface Factory<T> {
        @NonNull
        DataRewinder<T> build(@NonNull T t2);

        @NonNull
        Class<T> getDataClass();
    }

    void cleanup();

    @NonNull
    T rewindAndGet() throws IOException;
}
