package com.tinder.scarlet.utils;

import com.tinder.scarlet.Stream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0015\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/tinder/scarlet/utils/EmptyStreamObserver;", "T", "Lcom/tinder/scarlet/Stream$Observer;", "()V", "onComplete", "", "onError", "throwable", "", "onNext", "data", "(Ljava/lang/Object;)V", "scarlet-core-internal"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class EmptyStreamObserver<T> implements Stream.Observer<T> {
    public void onComplete() {
    }

    public void onError(@NotNull Throwable th) {
        Intrinsics.checkNotNullParameter(th, "throwable");
    }

    public void onNext(T t2) {
    }
}
