package com.airbnb.lottie.compose;

import android.content.Context;
import androidx.camera.camera2.internal.C0118y;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.utils.Utils;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001as\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0012¨\u0006\u0013²\u0006\n\u0010\u0014\u001a\u00020\u0005X\u0002"}, d2 = {"animateLottieCompositionAsState", "Lcom/airbnb/lottie/compose/LottieAnimationState;", "composition", "Lcom/airbnb/lottie/LottieComposition;", "isPlaying", "", "restartOnPlay", "reverseOnRepeat", "clipSpec", "Lcom/airbnb/lottie/compose/LottieClipSpec;", "speed", "", "iterations", "", "cancellationBehavior", "Lcom/airbnb/lottie/compose/LottieCancellationBehavior;", "ignoreSystemAnimatorScale", "useCompositionFrameRate", "(Lcom/airbnb/lottie/LottieComposition;ZZZLcom/airbnb/lottie/compose/LottieClipSpec;FILcom/airbnb/lottie/compose/LottieCancellationBehavior;ZZLandroidx/compose/runtime/Composer;II)Lcom/airbnb/lottie/compose/LottieAnimationState;", "lottie-compose_release", "wasPlaying"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nanimateLottieCompositionAsState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 animateLottieCompositionAsState.kt\ncom/airbnb/lottie/compose/AnimateLottieCompositionAsStateKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 4 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 5 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 6 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n*L\n1#1,93:1\n1#2:94\n25#3:95\n1097#4,6:96\n76#5:102\n81#6:103\n107#6,2:104\n*S KotlinDebug\n*F\n+ 1 animateLottieCompositionAsState.kt\ncom/airbnb/lottie/compose/AnimateLottieCompositionAsStateKt\n*L\n60#1:95\n60#1:96,6\n63#1:102\n60#1:103\n60#1:104,2\n*E\n"})
public final class AnimateLottieCompositionAsStateKt {
    @NotNull
    @Composable
    public static final LottieAnimationState animateLottieCompositionAsState(@Nullable LottieComposition lottieComposition, boolean z2, boolean z3, boolean z4, @Nullable LottieClipSpec lottieClipSpec, float f2, int i3, @Nullable LottieCancellationBehavior lottieCancellationBehavior, boolean z5, boolean z6, @Nullable Composer composer, int i4, int i5) {
        Composer composer2 = composer;
        int i6 = i5;
        composer2.startReplaceableGroup(683659508);
        boolean z7 = (i6 & 2) != 0 ? true : z2;
        boolean z8 = (i6 & 4) != 0 ? true : z3;
        boolean z9 = (i6 & 8) != 0 ? false : z4;
        LottieClipSpec lottieClipSpec2 = (i6 & 16) != 0 ? null : lottieClipSpec;
        float f3 = (i6 & 32) != 0 ? 1.0f : f2;
        int i7 = (i6 & 64) != 0 ? 1 : i3;
        LottieCancellationBehavior lottieCancellationBehavior2 = (i6 & 128) != 0 ? LottieCancellationBehavior.Immediately : lottieCancellationBehavior;
        boolean z10 = (i6 & 256) != 0 ? false : z5;
        boolean z11 = (i6 & 512) != 0 ? false : z6;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(683659508, i4, -1, "com.airbnb.lottie.compose.animateLottieCompositionAsState (animateLottieCompositionAsState.kt:54)");
        }
        if (i7 <= 0) {
            throw new IllegalArgumentException(C0118y.c(i7, "Iterations must be a positive number (", ").").toString());
        } else if (Float.isInfinite(f3) || Float.isNaN(f3)) {
            throw new IllegalArgumentException(("Speed must be a finite number. It is " + f3 + JwtUtilsKt.JWT_DELIMITER).toString());
        } else {
            LottieAnimatable rememberLottieAnimatable = LottieAnimatableKt.rememberLottieAnimatable(composer2, 0);
            composer2.startReplaceableGroup(-492369756);
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                rememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z7), (SnapshotMutationPolicy) null, 2, (Object) null);
                composer2.updateRememberedValue(rememberedValue);
            }
            composer.endReplaceableGroup();
            MutableState mutableState = (MutableState) rememberedValue;
            composer2.startReplaceableGroup(-180606834);
            if (!z10) {
                f3 /= Utils.getAnimationScale((Context) composer2.consume(AndroidCompositionLocals_androidKt.getLocalContext()));
            }
            float f4 = f3;
            composer.endReplaceableGroup();
            LottieComposition lottieComposition2 = lottieComposition;
            EffectsKt.LaunchedEffect(new Object[]{lottieComposition2, Boolean.valueOf(z7), lottieClipSpec2, Float.valueOf(f4), Integer.valueOf(i7)}, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) new AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3(z7, z8, rememberLottieAnimatable, lottieComposition2, i7, z9, f4, lottieClipSpec2, lottieCancellationBehavior2, z11, mutableState, (Continuation<? super AnimateLottieCompositionAsStateKt$animateLottieCompositionAsState$3>) null), composer2, 72);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return rememberLottieAnimatable;
        }
    }

    /* access modifiers changed from: private */
    public static final boolean animateLottieCompositionAsState$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* access modifiers changed from: private */
    public static final void animateLottieCompositionAsState$lambda$4(MutableState<Boolean> mutableState, boolean z2) {
        mutableState.setValue(Boolean.valueOf(z2));
    }
}
