package com.airbnb.lottie.compose;

import androidx.compose.runtime.Stable;
import androidx.compose.runtime.State;
import com.airbnb.lottie.LottieComposition;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Stable
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0012\u0010\u000e\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u001c\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\rR\u0012\u0010\u001e\u001a\u00020\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001bR\u0012\u0010 \u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\r¨\u0006\""}, d2 = {"Lcom/airbnb/lottie/compose/LottieAnimationState;", "Landroidx/compose/runtime/State;", "", "clipSpec", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "getClipSpec", "()Lcom/airbnb/lottie/compose/LottieClipSpec;", "composition", "Lcom/airbnb/lottie/LottieComposition;", "getComposition", "()Lcom/airbnb/lottie/LottieComposition;", "isAtEnd", "", "()Z", "isPlaying", "iteration", "", "getIteration", "()I", "iterations", "getIterations", "lastFrameNanos", "", "getLastFrameNanos", "()J", "progress", "getProgress", "()F", "reverseOnRepeat", "getReverseOnRepeat", "speed", "getSpeed", "useCompositionFrameRate", "getUseCompositionFrameRate", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface LottieAnimationState extends State<Float> {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static long getLastFrameNanos(@NotNull LottieAnimationState lottieAnimationState) {
            return Long.MIN_VALUE;
        }
    }

    @Nullable
    LottieClipSpec getClipSpec();

    @Nullable
    LottieComposition getComposition();

    int getIteration();

    int getIterations();

    long getLastFrameNanos();

    float getProgress();

    boolean getReverseOnRepeat();

    float getSpeed();

    boolean getUseCompositionFrameRate();

    boolean isAtEnd();

    boolean isPlaying();
}
