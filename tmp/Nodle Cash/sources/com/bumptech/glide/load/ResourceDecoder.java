package com.bumptech.glide.load;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;

public interface ResourceDecoder<T, Z> {
    @Nullable
    Resource<Z> decode(@NonNull T t2, int i3, int i4, @NonNull Options options) throws IOException;

    boolean handles(@NonNull T t2, @NonNull Options options) throws IOException;
}
