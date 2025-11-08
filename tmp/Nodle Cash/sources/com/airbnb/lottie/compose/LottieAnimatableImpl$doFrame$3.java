package com.airbnb.lottie.compose;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "frameNanos", "", "invoke", "(J)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class LottieAnimatableImpl$doFrame$3 extends Lambda implements Function1<Long, Boolean> {
    final /* synthetic */ int $iterations;
    final /* synthetic */ LottieAnimatableImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieAnimatableImpl$doFrame$3(LottieAnimatableImpl lottieAnimatableImpl, int i3) {
        super(1);
        this.this$0 = lottieAnimatableImpl;
        this.$iterations = i3;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return invoke(((Number) obj).longValue());
    }

    @NotNull
    public final Boolean invoke(long j2) {
        return Boolean.valueOf(this.this$0.onFrame(this.$iterations, j2));
    }
}
