package com.airbnb.lottie.compose;

import com.airbnb.lottie.LottieComposition;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.NonCancellable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2", f = "LottieAnimatable.kt", i = {}, l = {269}, m = "invokeSuspend", n = {}, s = {})
public final class LottieAnimatableImpl$animate$2 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ LottieCancellationBehavior $cancellationBehavior;
    final /* synthetic */ LottieClipSpec $clipSpec;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ boolean $continueFromPreviousAnimate;
    final /* synthetic */ float $initialProgress;
    final /* synthetic */ int $iteration;
    final /* synthetic */ int $iterations;
    final /* synthetic */ boolean $reverseOnRepeat;
    final /* synthetic */ float $speed;
    final /* synthetic */ boolean $useCompositionFrameRate;
    int label;
    final /* synthetic */ LottieAnimatableImpl this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2$1", f = "LottieAnimatable.kt", i = {}, l = {277}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* renamed from: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2$1$WhenMappings */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LottieCancellationBehavior.values().length];
                try {
                    iArr[LottieCancellationBehavior.OnIterationFinish.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new AnonymousClass1(lottieCancellationBehavior, job, i5, i6, lottieAnimatableImpl2, continuation);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0034  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0040 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0049  */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
        @org.jetbrains.annotations.Nullable
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r4) {
            /*
                r3 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r3.label
                r2 = 1
                if (r1 == 0) goto L_0x0017
                if (r1 != r2) goto L_0x000f
                kotlin.ResultKt.throwOnFailure(r4)
                goto L_0x0041
            L_0x000f:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L_0x0017:
                kotlin.ResultKt.throwOnFailure(r4)
            L_0x001a:
                com.airbnb.lottie.compose.LottieCancellationBehavior r4 = r5
                int[] r1 = com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2.AnonymousClass1.WhenMappings.$EnumSwitchMapping$0
                int r4 = r4.ordinal()
                r4 = r1[r4]
                if (r4 != r2) goto L_0x0034
                kotlinx.coroutines.Job r4 = r6
                boolean r4 = r4.isActive()
                if (r4 == 0) goto L_0x0031
                int r4 = r7
                goto L_0x0036
            L_0x0031:
                int r4 = r8
                goto L_0x0036
            L_0x0034:
                int r4 = r7
            L_0x0036:
                com.airbnb.lottie.compose.LottieAnimatableImpl r1 = r9
                r3.label = r2
                java.lang.Object r4 = r1.doFrame(r4, r3)
                if (r4 != r0) goto L_0x0041
                return r0
            L_0x0041:
                java.lang.Boolean r4 = (java.lang.Boolean) r4
                boolean r4 = r4.booleanValue()
                if (r4 != 0) goto L_0x001a
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            /*
                com.airbnb.lottie.compose.LottieCancellationBehavior[] r0 = com.airbnb.lottie.compose.LottieCancellationBehavior.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.airbnb.lottie.compose.LottieCancellationBehavior r1 = com.airbnb.lottie.compose.LottieCancellationBehavior.OnIterationFinish     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.airbnb.lottie.compose.LottieCancellationBehavior r1 = com.airbnb.lottie.compose.LottieCancellationBehavior.Immediately     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.compose.LottieAnimatableImpl$animate$2.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieAnimatableImpl$animate$2(LottieAnimatableImpl lottieAnimatableImpl, int i3, int i4, boolean z2, float f2, LottieClipSpec lottieClipSpec, LottieComposition lottieComposition, float f3, boolean z3, boolean z4, LottieCancellationBehavior lottieCancellationBehavior, Continuation<? super LottieAnimatableImpl$animate$2> continuation) {
        super(1, continuation);
        this.this$0 = lottieAnimatableImpl;
        this.$iteration = i3;
        this.$iterations = i4;
        this.$reverseOnRepeat = z2;
        this.$speed = f2;
        this.$clipSpec = lottieClipSpec;
        this.$composition = lottieComposition;
        this.$initialProgress = f3;
        this.$useCompositionFrameRate = z3;
        this.$continueFromPreviousAnimate = z4;
        this.$cancellationBehavior = lottieCancellationBehavior;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new LottieAnimatableImpl$animate$2(this.this$0, this.$iteration, this.$iterations, this.$reverseOnRepeat, this.$speed, this.$clipSpec, this.$composition, this.$initialProgress, this.$useCompositionFrameRate, this.$continueFromPreviousAnimate, this.$cancellationBehavior, continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        CoroutineContext coroutineContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.setIteration(this.$iteration);
            this.this$0.setIterations(this.$iterations);
            this.this$0.setReverseOnRepeat(this.$reverseOnRepeat);
            this.this$0.setSpeed(this.$speed);
            this.this$0.setClipSpec(this.$clipSpec);
            this.this$0.setComposition(this.$composition);
            this.this$0.updateProgress(this.$initialProgress);
            this.this$0.setUseCompositionFrameRate(this.$useCompositionFrameRate);
            if (!this.$continueFromPreviousAnimate) {
                this.this$0.setLastFrameNanos(Long.MIN_VALUE);
            }
            if (this.$composition == null) {
                this.this$0.setPlaying(false);
                return Unit.INSTANCE;
            } else if (Float.isInfinite(this.$speed)) {
                LottieAnimatableImpl lottieAnimatableImpl = this.this$0;
                lottieAnimatableImpl.updateProgress(lottieAnimatableImpl.getEndProgress());
                this.this$0.setPlaying(false);
                this.this$0.setIteration(this.$iterations);
                return Unit.INSTANCE;
            } else {
                this.this$0.setPlaying(true);
                int i4 = WhenMappings.$EnumSwitchMapping$0[this.$cancellationBehavior.ordinal()];
                if (i4 == 1) {
                    coroutineContext = NonCancellable.INSTANCE;
                } else if (i4 == 2) {
                    coroutineContext = EmptyCoroutineContext.INSTANCE;
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                final Job job = JobKt.getJob(getContext());
                final LottieCancellationBehavior lottieCancellationBehavior = this.$cancellationBehavior;
                final int i5 = this.$iterations;
                final int i6 = this.$iteration;
                final LottieAnimatableImpl lottieAnimatableImpl2 = this.this$0;
                AnonymousClass1 r4 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
                this.label = 1;
                if (BuildersKt.withContext(coroutineContext, r4, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i3 == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.this$0.setPlaying(false);
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        JobKt.ensureActive(getContext());
        this.this$0.setPlaying(false);
        return Unit.INSTANCE;
    }

    @Nullable
    public final Object invoke(@Nullable Continuation<? super Unit> continuation) {
        return ((LottieAnimatableImpl$animate$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
