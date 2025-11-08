package com.airbnb.lottie.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.ContentScale;
import com.airbnb.lottie.AsyncUpdates;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.RenderMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public final class LottieAnimationKt$LottieAnimation$5 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ int $$changed;
    final /* synthetic */ int $$changed1;
    final /* synthetic */ int $$default;
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ boolean $applyOpacityToLayers;
    final /* synthetic */ AsyncUpdates $asyncUpdates;
    final /* synthetic */ boolean $clipToCompositionBounds;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ LottieDynamicProperties $dynamicProperties;
    final /* synthetic */ boolean $enableMergePaths;
    final /* synthetic */ boolean $maintainOriginalImageBounds;
    final /* synthetic */ Modifier $modifier;
    final /* synthetic */ boolean $outlineMasksAndMattes;
    final /* synthetic */ float $progress;
    final /* synthetic */ RenderMode $renderMode;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieAnimationKt$LottieAnimation$5(LottieComposition lottieComposition, float f2, Modifier modifier, boolean z2, boolean z3, boolean z4, RenderMode renderMode, boolean z5, LottieDynamicProperties lottieDynamicProperties, Alignment alignment, ContentScale contentScale, boolean z6, AsyncUpdates asyncUpdates, int i3, int i4, int i5) {
        super(2);
        this.$composition = lottieComposition;
        this.$progress = f2;
        this.$modifier = modifier;
        this.$outlineMasksAndMattes = z2;
        this.$applyOpacityToLayers = z3;
        this.$enableMergePaths = z4;
        this.$renderMode = renderMode;
        this.$maintainOriginalImageBounds = z5;
        this.$dynamicProperties = lottieDynamicProperties;
        this.$alignment = alignment;
        this.$contentScale = contentScale;
        this.$clipToCompositionBounds = z6;
        this.$asyncUpdates = asyncUpdates;
        this.$$changed = i3;
        this.$$changed1 = i4;
        this.$$default = i5;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@Nullable Composer composer, int i3) {
        LottieComposition lottieComposition = this.$composition;
        LottieComposition lottieComposition2 = lottieComposition;
        LottieAnimationKt.LottieAnimation(lottieComposition2, this.$progress, this.$modifier, this.$outlineMasksAndMattes, this.$applyOpacityToLayers, this.$enableMergePaths, this.$renderMode, this.$maintainOriginalImageBounds, this.$dynamicProperties, this.$alignment, this.$contentScale, this.$clipToCompositionBounds, this.$asyncUpdates, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
    }
}
