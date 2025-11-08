package com.airbnb.lottie.compose;

import androidx.compose.runtime.State;
import com.airbnb.lottie.value.LottieFrameInfo;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "T", "it", "Lcom/airbnb/lottie/value/LottieFrameInfo;", "invoke", "(Lcom/airbnb/lottie/value/LottieFrameInfo;)Ljava/lang/Object;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class LottieDynamicPropertiesKt$rememberLottieDynamicProperty$2$1 extends Lambda implements Function1<LottieFrameInfo<T>, T> {
    final /* synthetic */ State<Function1<LottieFrameInfo<T>, T>> $callbackState$delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieDynamicPropertiesKt$rememberLottieDynamicProperty$2$1(State<? extends Function1<? super LottieFrameInfo<T>, ? extends T>> state) {
        super(1);
        this.$callbackState$delegate = state;
    }

    public final T invoke(@NotNull LottieFrameInfo<T> lottieFrameInfo) {
        Intrinsics.checkNotNullParameter(lottieFrameInfo, "it");
        return LottieDynamicPropertiesKt.rememberLottieDynamicProperty$lambda$4(this.$callbackState$delegate).invoke(lottieFrameInfo);
    }
}
