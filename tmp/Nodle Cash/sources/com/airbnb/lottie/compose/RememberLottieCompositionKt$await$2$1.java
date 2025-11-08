package com.airbnb.lottie.compose;

import com.airbnb.lottie.LottieListener;
import kotlin.Metadata;
import kotlin.Result;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "T", "c", "kotlin.jvm.PlatformType", "onResult", "(Ljava/lang/Object;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class RememberLottieCompositionKt$await$2$1<T> implements LottieListener {
    final /* synthetic */ CancellableContinuation<T> $cont;

    public RememberLottieCompositionKt$await$2$1(CancellableContinuation<? super T> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public final void onResult(T t2) {
        if (!this.$cont.isCompleted()) {
            this.$cont.resumeWith(Result.m8979constructorimpl(t2));
        }
    }
}
