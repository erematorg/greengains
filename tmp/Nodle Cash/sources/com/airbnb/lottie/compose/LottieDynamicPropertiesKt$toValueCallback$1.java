package com.airbnb.lottie.compose;

import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001b\u0010\u0002\u001a\u00028\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"com/airbnb/lottie/compose/LottieDynamicPropertiesKt$toValueCallback$1", "Lcom/airbnb/lottie/value/LottieValueCallback;", "getValue", "frameInfo", "Lcom/airbnb/lottie/value/LottieFrameInfo;", "(Lcom/airbnb/lottie/value/LottieFrameInfo;)Ljava/lang/Object;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class LottieDynamicPropertiesKt$toValueCallback$1 extends LottieValueCallback<Object> {
    final /* synthetic */ Function1<LottieFrameInfo<Object>, Object> $this_toValueCallback;

    public LottieDynamicPropertiesKt$toValueCallback$1(Function1<? super LottieFrameInfo<Object>, Object> function1) {
        this.$this_toValueCallback = function1;
    }

    public Object getValue(@NotNull LottieFrameInfo<Object> lottieFrameInfo) {
        Intrinsics.checkNotNullParameter(lottieFrameInfo, "frameInfo");
        return this.$this_toValueCallback.invoke(lottieFrameInfo);
    }
}
