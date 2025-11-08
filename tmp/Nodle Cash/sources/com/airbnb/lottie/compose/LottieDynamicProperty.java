package com.airbnb.lottie.compose;

import androidx.compose.runtime.internal.StabilityInferred;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.sun.jna.Callback;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0002\u0010\u0007B@\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012'\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0002\u0010\u000eR5\u0010\b\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00000\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00028\u00000\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0003\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/airbnb/lottie/compose/LottieDynamicProperty;", "T", "", "property", "keyPath", "Lcom/airbnb/lottie/model/KeyPath;", "value", "(Ljava/lang/Object;Lcom/airbnb/lottie/model/KeyPath;Ljava/lang/Object;)V", "callback", "Lkotlin/Function1;", "Lcom/airbnb/lottie/value/LottieFrameInfo;", "Lkotlin/ParameterName;", "name", "frameInfo", "(Ljava/lang/Object;Lcom/airbnb/lottie/model/KeyPath;Lkotlin/jvm/functions/Function1;)V", "getCallback$lottie_compose_release", "()Lkotlin/jvm/functions/Function1;", "getKeyPath$lottie_compose_release", "()Lcom/airbnb/lottie/model/KeyPath;", "getProperty$lottie_compose_release", "()Ljava/lang/Object;", "Ljava/lang/Object;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LottieDynamicProperty<T> {
    public static final int $stable = 8;
    @NotNull
    private final Function1<LottieFrameInfo<T>, T> callback;
    @NotNull
    private final KeyPath keyPath;
    private final T property;

    public LottieDynamicProperty(T t2, @NotNull KeyPath keyPath2, @NotNull Function1<? super LottieFrameInfo<T>, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(keyPath2, "keyPath");
        Intrinsics.checkNotNullParameter(function1, Callback.METHOD_NAME);
        this.property = t2;
        this.keyPath = keyPath2;
        this.callback = function1;
    }

    @NotNull
    public final Function1<LottieFrameInfo<T>, T> getCallback$lottie_compose_release() {
        return this.callback;
    }

    @NotNull
    public final KeyPath getKeyPath$lottie_compose_release() {
        return this.keyPath;
    }

    public final T getProperty$lottie_compose_release() {
        return this.property;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LottieDynamicProperty(T t2, @NotNull KeyPath keyPath2, final T t3) {
        this(t2, keyPath2, new Function1<LottieFrameInfo<T>, T>() {
            public final T invoke(@NotNull LottieFrameInfo<T> lottieFrameInfo) {
                Intrinsics.checkNotNullParameter(lottieFrameInfo, "it");
                return t3;
            }
        });
        Intrinsics.checkNotNullParameter(keyPath2, "keyPath");
    }
}
