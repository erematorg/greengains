package com.google.android.gms.common.data;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import java.io.Closeable;
import java.util.Iterator;

public interface DataBuffer<T> extends Iterable<T>, Releasable, Closeable {
    void close();

    @NonNull
    T get(int i3);

    int getCount();

    @KeepForSdk
    @Nullable
    Bundle getMetadata();

    @Deprecated
    boolean isClosed();

    @NonNull
    Iterator<T> iterator();

    void release();

    @NonNull
    Iterator<T> singleRefIterator();
}
