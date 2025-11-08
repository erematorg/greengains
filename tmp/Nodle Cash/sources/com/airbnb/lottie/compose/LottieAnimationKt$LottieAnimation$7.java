package com.airbnb.lottie.compose;

import android.graphics.Typeface;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import com.airbnb.lottie.AsyncUpdates;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.RenderMode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public final class LottieAnimationKt$LottieAnimation$7 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ boolean $applyOpacityToLayers;
    final /* synthetic */ AsyncUpdates $asyncUpdates;
    final /* synthetic */ LottieClipSpec $clipSpec;
    final /* synthetic */ boolean $clipToCompositionBounds;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ LottieDynamicProperties $dynamicProperties;
    final /* synthetic */ boolean $enableMergePaths;
    final /* synthetic */ Map<String, Typeface> $fontMap;
    final /* synthetic */ boolean $isPlaying;
    final /* synthetic */ int $iterations;
    final /* synthetic */ boolean $maintainOriginalImageBounds;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ boolean $outlineMasksAndMattes;
    final /* synthetic */ RenderMode $renderMode;
    final /* synthetic */ boolean $restartOnPlay;
    final /* synthetic */ boolean $reverseOnRepeat;
    final /* synthetic */ float $speed;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieAnimationKt$LottieAnimation$7(LottieComposition lottieComposition, Modifier modifier, boolean z2, boolean z3, LottieClipSpec lottieClipSpec, float f2, int i3, boolean z4, boolean z5, boolean z6, RenderMode renderMode, boolean z7, boolean z8, LottieDynamicProperties lottieDynamicProperties, Alignment alignment, ContentScale contentScale, boolean z9, Map<String, ? extends Typeface> map, AsyncUpdates asyncUpdates, int i4, int i5, int i6) {
        super(2);
        this.$composition = lottieComposition;
        this.$modifier = modifier;
        this.$isPlaying = z2;
        this.$restartOnPlay = z3;
        this.$clipSpec = lottieClipSpec;
        this.$speed = f2;
        this.$iterations = i3;
        this.$outlineMasksAndMattes = z4;
        this.$applyOpacityToLayers = z5;
        this.$enableMergePaths = z6;
        this.$renderMode = renderMode;
        this.$reverseOnRepeat = z7;
        this.$maintainOriginalImageBounds = z8;
        this.$dynamicProperties = lottieDynamicProperties;
        this.$alignment = alignment;
        this.$contentScale = contentScale;
        this.$clipToCompositionBounds = z9;
        this.$fontMap = map;
        this.$asyncUpdates = asyncUpdates;
        this.$$changed = i4;
        this.$$changed1 = i5;
        this.$$default = i6;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Composer composer, int i3) {
        Composer composer2 = composer;
        LottieAnimationKt.LottieAnimation(this.$composition, this.$modifier, this.$isPlaying, this.$restartOnPlay, this.$clipSpec, this.$speed, this.$iterations, this.$outlineMasksAndMattes, this.$applyOpacityToLayers, this.$enableMergePaths, this.$renderMode, this.$reverseOnRepeat, this.$maintainOriginalImageBounds, this.$dynamicProperties, this.$alignment, this.$contentScale, this.$clipToCompositionBounds, this.$fontMap, this.$asyncUpdates, composer2, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
