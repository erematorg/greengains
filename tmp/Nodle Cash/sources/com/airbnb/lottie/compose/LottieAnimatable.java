package com.airbnb.lottie.compose;

import androidx.compose.runtime.Stable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.compose.LottieAnimationState;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0001\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J;\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"Lcom/airbnb/lottie/compose/LottieAnimatable;", "Lcom/airbnb/lottie/compose/LottieAnimationState;", "animate", "", "composition", "Lcom/airbnb/lottie/LottieComposition;", "iteration", "", "iterations", "reverseOnRepeat", "", "speed", "", "clipSpec", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "initialProgress", "continueFromPreviousAnimate", "cancellationBehavior", "Lcom/airbnb/lottie/compose/LottieCancellationBehavior;", "ignoreSystemAnimationsDisabled", "useCompositionFrameRate", "(Lcom/airbnb/lottie/LottieComposition;IIZFLcom/airbnb/lottie/compose/LottieClipSpec;FZLcom/airbnb/lottie/compose/LottieCancellationBehavior;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapTo", "progress", "resetLastFrameNanos", "(Lcom/airbnb/lottie/LottieComposition;FIZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Stable
public interface LottieAnimatable extends LottieAnimationState {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object animate$default(LottieAnimatable lottieAnimatable, LottieComposition lottieComposition, int i3, int i4, boolean z2, float f2, LottieClipSpec lottieClipSpec, float f3, boolean z3, LottieCancellationBehavior lottieCancellationBehavior, boolean z4, boolean z5, Continuation continuation, int i5, Object obj) {
            float f4;
            int i6 = i5;
            if (obj == null) {
                int iteration = (i6 & 2) != 0 ? lottieAnimatable.getIteration() : i3;
                int iterations = (i6 & 4) != 0 ? lottieAnimatable.getIterations() : i4;
                boolean reverseOnRepeat = (i6 & 8) != 0 ? lottieAnimatable.getReverseOnRepeat() : z2;
                float speed = (i6 & 16) != 0 ? lottieAnimatable.getSpeed() : f2;
                LottieClipSpec clipSpec = (i6 & 32) != 0 ? lottieAnimatable.getClipSpec() : lottieClipSpec;
                if ((i6 & 64) != 0) {
                    f4 = LottieAnimatableKt.defaultProgress(lottieComposition, clipSpec, speed);
                } else {
                    LottieComposition lottieComposition2 = lottieComposition;
                    f4 = f3;
                }
                return lottieAnimatable.animate(lottieComposition, iteration, iterations, reverseOnRepeat, speed, clipSpec, f4, (i6 & 128) != 0 ? false : z3, (i6 & 256) != 0 ? LottieCancellationBehavior.Immediately : lottieCancellationBehavior, (i6 & 512) != 0 ? false : z4, (i6 & 1024) != 0 ? false : z5, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: animate");
        }

        public static long getLastFrameNanos(@NotNull LottieAnimatable lottieAnimatable) {
            return LottieAnimationState.DefaultImpls.getLastFrameNanos(lottieAnimatable);
        }

        public static /* synthetic */ Object snapTo$default(LottieAnimatable lottieAnimatable, LottieComposition lottieComposition, float f2, int i3, boolean z2, Continuation continuation, int i4, Object obj) {
            if (obj == null) {
                if ((i4 & 1) != 0) {
                    lottieComposition = lottieAnimatable.getComposition();
                }
                LottieComposition lottieComposition2 = lottieComposition;
                if ((i4 & 2) != 0) {
                    f2 = lottieAnimatable.getProgress();
                }
                float f3 = f2;
                if ((i4 & 4) != 0) {
                    i3 = lottieAnimatable.getIteration();
                }
                int i5 = i3;
                if ((i4 & 8) != 0) {
                    z2 = !(f3 == lottieAnimatable.getProgress());
                }
                return lottieAnimatable.snapTo(lottieComposition2, f3, i5, z2, continuation);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: snapTo");
        }
    }

    @Nullable
    Object animate(@Nullable LottieComposition lottieComposition, int i3, int i4, boolean z2, float f2, @Nullable LottieClipSpec lottieClipSpec, float f3, boolean z3, @NotNull LottieCancellationBehavior lottieCancellationBehavior, boolean z4, boolean z5, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object snapTo(@Nullable LottieComposition lottieComposition, float f2, int i3, boolean z2, @NotNull Continuation<? super Unit> continuation);
}
