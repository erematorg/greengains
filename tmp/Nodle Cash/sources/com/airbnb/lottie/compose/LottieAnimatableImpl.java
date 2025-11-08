package com.airbnb.lottie.compose;

import androidx.compose.animation.core.InfiniteAnimationPolicyKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Stable;
import androidx.compose.runtime.State;
import com.airbnb.lottie.LottieComposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002Jm\u0010P\u001a\u00020Q2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010%\u001a\u00020$2\u0006\u0010+\u001a\u00020$2\u0006\u0010A\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010R\u001a\u00020\u00142\u0006\u0010S\u001a\u00020\u001d2\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010WJ\u0019\u0010X\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020$H@ø\u0001\u0000¢\u0006\u0002\u0010YJ\u0018\u0010Z\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020$2\u0006\u0010[\u001a\u00020/H\u0002J3\u0010\\\u001a\u00020Q2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u00108\u001a\u00020\u00142\u0006\u0010%\u001a\u00020$2\u0006\u0010]\u001a\u00020\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010^J\u0010\u0010_\u001a\u00020Q2\u0006\u00108\u001a\u00020\u0014H\u0002J\u0016\u0010`\u001a\u00020\u0014*\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0002R/\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048V@RX\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR/\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0003\u001a\u0004\u0018\u00010\f8V@RX\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\u00148BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0018\u001a\u0004\b\u001a\u0010\u0016R\u001b\u0010\u001c\u001a\u00020\u001d8VX\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0018\u001a\u0004\b\u001c\u0010\u001eR+\u0010 \u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d8V@RX\u0002¢\u0006\u0012\n\u0004\b#\u0010\u000b\u001a\u0004\b \u0010\u001e\"\u0004\b!\u0010\"R+\u0010%\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$8V@RX\u0002¢\u0006\u0012\n\u0004\b*\u0010\u000b\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R+\u0010+\u001a\u00020$2\u0006\u0010\u0003\u001a\u00020$8V@RX\u0002¢\u0006\u0012\n\u0004\b.\u0010\u000b\u001a\u0004\b,\u0010'\"\u0004\b-\u0010)R+\u00100\u001a\u00020/2\u0006\u0010\u0003\u001a\u00020/8V@RX\u0002¢\u0006\u0012\n\u0004\b5\u0010\u000b\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u000e\u00106\u001a\u000207X\u0004¢\u0006\u0002\n\u0000R+\u00108\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00148V@RX\u0002¢\u0006\u0012\n\u0004\b<\u0010\u000b\u001a\u0004\b9\u0010\u0016\"\u0004\b:\u0010;R+\u0010=\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00148B@BX\u0002¢\u0006\u0012\n\u0004\b@\u0010\u000b\u001a\u0004\b>\u0010\u0016\"\u0004\b?\u0010;R+\u0010A\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d8V@RX\u0002¢\u0006\u0012\n\u0004\bD\u0010\u000b\u001a\u0004\bB\u0010\u001e\"\u0004\bC\u0010\"R+\u0010E\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u00148V@RX\u0002¢\u0006\u0012\n\u0004\bH\u0010\u000b\u001a\u0004\bF\u0010\u0016\"\u0004\bG\u0010;R+\u0010I\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u001d8V@RX\u0002¢\u0006\u0012\n\u0004\bL\u0010\u000b\u001a\u0004\bJ\u0010\u001e\"\u0004\bK\u0010\"R\u0014\u0010M\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\bN\u0010O\u0002\u0004\n\u0002\b\u0019¨\u0006a"}, d2 = {"Lcom/airbnb/lottie/compose/LottieAnimatableImpl;", "Lcom/airbnb/lottie/compose/LottieAnimatable;", "()V", "<set-?>", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "clipSpec", "getClipSpec", "()Lcom/airbnb/lottie/compose/LottieClipSpec;", "setClipSpec", "(Lcom/airbnb/lottie/compose/LottieClipSpec;)V", "clipSpec$delegate", "Landroidx/compose/runtime/MutableState;", "Lcom/airbnb/lottie/LottieComposition;", "composition", "getComposition", "()Lcom/airbnb/lottie/LottieComposition;", "setComposition", "(Lcom/airbnb/lottie/LottieComposition;)V", "composition$delegate", "endProgress", "", "getEndProgress", "()F", "endProgress$delegate", "Landroidx/compose/runtime/State;", "frameSpeed", "getFrameSpeed", "frameSpeed$delegate", "isAtEnd", "", "()Z", "isAtEnd$delegate", "isPlaying", "setPlaying", "(Z)V", "isPlaying$delegate", "", "iteration", "getIteration", "()I", "setIteration", "(I)V", "iteration$delegate", "iterations", "getIterations", "setIterations", "iterations$delegate", "", "lastFrameNanos", "getLastFrameNanos", "()J", "setLastFrameNanos", "(J)V", "lastFrameNanos$delegate", "mutex", "Landroidx/compose/foundation/MutatorMutex;", "progress", "getProgress", "setProgress", "(F)V", "progress$delegate", "progressRaw", "getProgressRaw", "setProgressRaw", "progressRaw$delegate", "reverseOnRepeat", "getReverseOnRepeat", "setReverseOnRepeat", "reverseOnRepeat$delegate", "speed", "getSpeed", "setSpeed", "speed$delegate", "useCompositionFrameRate", "getUseCompositionFrameRate", "setUseCompositionFrameRate", "useCompositionFrameRate$delegate", "value", "getValue", "()Ljava/lang/Float;", "animate", "", "initialProgress", "continueFromPreviousAnimate", "cancellationBehavior", "Lcom/airbnb/lottie/compose/LottieCancellationBehavior;", "ignoreSystemAnimationsDisabled", "(Lcom/airbnb/lottie/LottieComposition;IIZFLcom/airbnb/lottie/compose/LottieClipSpec;FZLcom/airbnb/lottie/compose/LottieCancellationBehavior;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doFrame", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onFrame", "frameNanos", "snapTo", "resetLastFrameNanos", "(Lcom/airbnb/lottie/LottieComposition;FIZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProgress", "roundToCompositionFrameRate", "lottie-compose_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLottieAnimatable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LottieAnimatable.kt\ncom/airbnb/lottie/compose/LottieAnimatableImpl\n+ 2 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n*L\n1#1,359:1\n81#2:360\n107#2,2:361\n81#2:363\n107#2,2:364\n81#2:366\n107#2,2:367\n81#2:369\n107#2,2:370\n81#2:372\n107#2,2:373\n81#2:375\n107#2,2:376\n81#2:378\n107#2,2:379\n81#2:381\n81#2:382\n107#2,2:383\n81#2:385\n107#2,2:386\n81#2:388\n107#2,2:389\n81#2:391\n107#2,2:392\n81#2:394\n81#2:395\n*S KotlinDebug\n*F\n+ 1 LottieAnimatable.kt\ncom/airbnb/lottie/compose/LottieAnimatableImpl\n*L\n157#1:360\n157#1:361,2\n163#1:363\n163#1:364,2\n166#1:366\n166#1:367,2\n169#1:369\n169#1:370,2\n172#1:372\n172#1:373,2\n175#1:375\n175#1:376,2\n178#1:378\n178#1:379,2\n184#1:381\n188#1:382\n188#1:383,2\n191#1:385\n191#1:386,2\n193#1:388\n193#1:389,2\n196#1:391\n196#1:392,2\n199#1:394\n208#1:395\n*E\n"})
@Stable
final class LottieAnimatableImpl implements LottieAnimatable {
    @NotNull
    private final MutableState clipSpec$delegate;
    @NotNull
    private final MutableState composition$delegate;
    @NotNull
    private final State endProgress$delegate;
    @NotNull
    private final State frameSpeed$delegate;
    @NotNull
    private final State isAtEnd$delegate;
    @NotNull
    private final MutableState isPlaying$delegate;
    @NotNull
    private final MutableState iteration$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(1, (SnapshotMutationPolicy) null, 2, (Object) null);
    @NotNull
    private final MutableState iterations$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(1, (SnapshotMutationPolicy) null, 2, (Object) null);
    @NotNull
    private final MutableState lastFrameNanos$delegate;
    @NotNull
    private final MutatorMutex mutex;
    @NotNull
    private final MutableState progress$delegate;
    @NotNull
    private final MutableState progressRaw$delegate;
    @NotNull
    private final MutableState reverseOnRepeat$delegate;
    @NotNull
    private final MutableState speed$delegate;
    @NotNull
    private final MutableState useCompositionFrameRate$delegate;

    public LottieAnimatableImpl() {
        Boolean bool = Boolean.FALSE;
        this.isPlaying$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.reverseOnRepeat$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.clipSpec$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.speed$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(1.0f), (SnapshotMutationPolicy) null, 2, (Object) null);
        this.useCompositionFrameRate$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(bool, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.frameSpeed$delegate = SnapshotStateKt.derivedStateOf(new LottieAnimatableImpl$frameSpeed$2(this));
        this.composition$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
        Float valueOf = Float.valueOf(0.0f);
        this.progressRaw$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(valueOf, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.progress$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(valueOf, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.lastFrameNanos$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Long.MIN_VALUE, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.endProgress$delegate = SnapshotStateKt.derivedStateOf(new LottieAnimatableImpl$endProgress$2(this));
        this.isAtEnd$delegate = SnapshotStateKt.derivedStateOf(new LottieAnimatableImpl$isAtEnd$2(this));
        this.mutex = new MutatorMutex();
    }

    /* access modifiers changed from: private */
    public final Object doFrame(int i3, Continuation<? super Boolean> continuation) {
        return i3 == Integer.MAX_VALUE ? InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(new LottieAnimatableImpl$doFrame$2(this, i3), continuation) : MonotonicFrameClockKt.withFrameNanos(new LottieAnimatableImpl$doFrame$3(this, i3), continuation);
    }

    /* access modifiers changed from: private */
    public final float getEndProgress() {
        return ((Number) this.endProgress$delegate.getValue()).floatValue();
    }

    private final float getFrameSpeed() {
        return ((Number) this.frameSpeed$delegate.getValue()).floatValue();
    }

    private final float getProgressRaw() {
        return ((Number) this.progressRaw$delegate.getValue()).floatValue();
    }

    /* access modifiers changed from: private */
    public final boolean onFrame(int i3, long j2) {
        LottieComposition composition = getComposition();
        if (composition == null) {
            return true;
        }
        long lastFrameNanos = getLastFrameNanos() == Long.MIN_VALUE ? 0 : j2 - getLastFrameNanos();
        setLastFrameNanos(j2);
        LottieClipSpec clipSpec = getClipSpec();
        float minProgress$lottie_compose_release = clipSpec != null ? clipSpec.getMinProgress$lottie_compose_release(composition) : 0.0f;
        LottieClipSpec clipSpec2 = getClipSpec();
        float maxProgress$lottie_compose_release = clipSpec2 != null ? clipSpec2.getMaxProgress$lottie_compose_release(composition) : 1.0f;
        float duration = (((float) (lastFrameNanos / ((long) 1000000))) / composition.getDuration()) * getFrameSpeed();
        float progressRaw = getFrameSpeed() < 0.0f ? minProgress$lottie_compose_release - (getProgressRaw() + duration) : (getProgressRaw() + duration) - maxProgress$lottie_compose_release;
        if (progressRaw < 0.0f) {
            updateProgress(RangesKt.coerceIn(getProgressRaw(), minProgress$lottie_compose_release, maxProgress$lottie_compose_release) + duration);
        } else {
            float f2 = maxProgress$lottie_compose_release - minProgress$lottie_compose_release;
            int i4 = (int) (progressRaw / f2);
            int i5 = i4 + 1;
            if (getIteration() + i5 > i3) {
                updateProgress(getEndProgress());
                setIteration(i3);
                return false;
            }
            setIteration(getIteration() + i5);
            float f3 = progressRaw - (((float) i4) * f2);
            updateProgress(getFrameSpeed() < 0.0f ? maxProgress$lottie_compose_release - f3 : minProgress$lottie_compose_release + f3);
        }
        return true;
    }

    private final float roundToCompositionFrameRate(float f2, LottieComposition lottieComposition) {
        if (lottieComposition == null) {
            return f2;
        }
        return f2 - (f2 % (((float) 1) / lottieComposition.getFrameRate()));
    }

    /* access modifiers changed from: private */
    public void setClipSpec(LottieClipSpec lottieClipSpec) {
        this.clipSpec$delegate.setValue(lottieClipSpec);
    }

    /* access modifiers changed from: private */
    public void setComposition(LottieComposition lottieComposition) {
        this.composition$delegate.setValue(lottieComposition);
    }

    /* access modifiers changed from: private */
    public void setIteration(int i3) {
        this.iteration$delegate.setValue(Integer.valueOf(i3));
    }

    /* access modifiers changed from: private */
    public void setIterations(int i3) {
        this.iterations$delegate.setValue(Integer.valueOf(i3));
    }

    /* access modifiers changed from: private */
    public void setLastFrameNanos(long j2) {
        this.lastFrameNanos$delegate.setValue(Long.valueOf(j2));
    }

    /* access modifiers changed from: private */
    public void setPlaying(boolean z2) {
        this.isPlaying$delegate.setValue(Boolean.valueOf(z2));
    }

    private void setProgress(float f2) {
        this.progress$delegate.setValue(Float.valueOf(f2));
    }

    private final void setProgressRaw(float f2) {
        this.progressRaw$delegate.setValue(Float.valueOf(f2));
    }

    /* access modifiers changed from: private */
    public void setReverseOnRepeat(boolean z2) {
        this.reverseOnRepeat$delegate.setValue(Boolean.valueOf(z2));
    }

    /* access modifiers changed from: private */
    public void setSpeed(float f2) {
        this.speed$delegate.setValue(Float.valueOf(f2));
    }

    /* access modifiers changed from: private */
    public void setUseCompositionFrameRate(boolean z2) {
        this.useCompositionFrameRate$delegate.setValue(Boolean.valueOf(z2));
    }

    /* access modifiers changed from: private */
    public final void updateProgress(float f2) {
        setProgressRaw(f2);
        if (getUseCompositionFrameRate()) {
            f2 = roundToCompositionFrameRate(f2, getComposition());
        }
        setProgress(f2);
    }

    @Nullable
    public Object animate(@Nullable LottieComposition lottieComposition, int i3, int i4, boolean z2, float f2, @Nullable LottieClipSpec lottieClipSpec, float f3, boolean z3, @NotNull LottieCancellationBehavior lottieCancellationBehavior, boolean z4, boolean z5, @NotNull Continuation<? super Unit> continuation) {
        Object mutate$default = MutatorMutex.mutate$default(this.mutex, (MutatePriority) null, new LottieAnimatableImpl$animate$2(this, i3, i4, z2, f2, lottieClipSpec, lottieComposition, f3, z5, z3, lottieCancellationBehavior, (Continuation<? super LottieAnimatableImpl$animate$2>) null), continuation, 1, (Object) null);
        return mutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate$default : Unit.INSTANCE;
    }

    @Nullable
    public LottieClipSpec getClipSpec() {
        return (LottieClipSpec) this.clipSpec$delegate.getValue();
    }

    @Nullable
    public LottieComposition getComposition() {
        return (LottieComposition) this.composition$delegate.getValue();
    }

    public int getIteration() {
        return ((Number) this.iteration$delegate.getValue()).intValue();
    }

    public int getIterations() {
        return ((Number) this.iterations$delegate.getValue()).intValue();
    }

    public long getLastFrameNanos() {
        return ((Number) this.lastFrameNanos$delegate.getValue()).longValue();
    }

    public float getProgress() {
        return ((Number) this.progress$delegate.getValue()).floatValue();
    }

    public boolean getReverseOnRepeat() {
        return ((Boolean) this.reverseOnRepeat$delegate.getValue()).booleanValue();
    }

    public float getSpeed() {
        return ((Number) this.speed$delegate.getValue()).floatValue();
    }

    public boolean getUseCompositionFrameRate() {
        return ((Boolean) this.useCompositionFrameRate$delegate.getValue()).booleanValue();
    }

    public boolean isAtEnd() {
        return ((Boolean) this.isAtEnd$delegate.getValue()).booleanValue();
    }

    public boolean isPlaying() {
        return ((Boolean) this.isPlaying$delegate.getValue()).booleanValue();
    }

    @Nullable
    public Object snapTo(@Nullable LottieComposition lottieComposition, float f2, int i3, boolean z2, @NotNull Continuation<? super Unit> continuation) {
        Object mutate$default = MutatorMutex.mutate$default(this.mutex, (MutatePriority) null, new LottieAnimatableImpl$snapTo$2(this, lottieComposition, f2, i3, z2, (Continuation<? super LottieAnimatableImpl$snapTo$2>) null), continuation, 1, (Object) null);
        return mutate$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mutate$default : Unit.INSTANCE;
    }

    @NotNull
    public Float getValue() {
        return Float.valueOf(getProgress());
    }
}
