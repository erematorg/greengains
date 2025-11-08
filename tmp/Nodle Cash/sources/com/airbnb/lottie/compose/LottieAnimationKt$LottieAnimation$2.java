package com.airbnb.lottie.compose;

import android.graphics.Matrix;
import android.graphics.Typeface;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import com.airbnb.lottie.AsyncUpdates;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.RenderMode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nLottieAnimation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LottieAnimation.kt\ncom/airbnb/lottie/compose/LottieAnimationKt$LottieAnimation$2\n+ 2 DrawScope.kt\nandroidx/compose/ui/graphics/drawscope/DrawScopeKt\n*L\n1#1,233:1\n245#2:234\n*S KotlinDebug\n*F\n+ 1 LottieAnimation.kt\ncom/airbnb/lottie/compose/LottieAnimationKt$LottieAnimation$2\n*L\n103#1:234\n*E\n"})
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class LottieAnimationKt$LottieAnimation$2 extends Lambda implements Function1<DrawScope, Unit> {
    final /* synthetic */ Alignment $alignment;
    final /* synthetic */ boolean $applyOpacityToLayers;
    final /* synthetic */ AsyncUpdates $asyncUpdates;
    final /* synthetic */ boolean $clipToCompositionBounds;
    final /* synthetic */ LottieComposition $composition;
    final /* synthetic */ ContentScale $contentScale;
    final /* synthetic */ LottieDrawable $drawable;
    final /* synthetic */ LottieDynamicProperties $dynamicProperties;
    final /* synthetic */ boolean $enableMergePaths;
    final /* synthetic */ Map<String, Typeface> $fontMap;
    final /* synthetic */ boolean $maintainOriginalImageBounds;
    final /* synthetic */ Matrix $matrix;
    final /* synthetic */ boolean $outlineMasksAndMattes;
    final /* synthetic */ Function0<Float> $progress;
    final /* synthetic */ RenderMode $renderMode;
    final /* synthetic */ MutableState<LottieDynamicProperties> $setDynamicProperties$delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LottieAnimationKt$LottieAnimation$2(LottieComposition lottieComposition, ContentScale contentScale, Alignment alignment, Matrix matrix, LottieDrawable lottieDrawable, boolean z2, RenderMode renderMode, AsyncUpdates asyncUpdates, Map<String, ? extends Typeface> map, LottieDynamicProperties lottieDynamicProperties, boolean z3, boolean z4, boolean z5, boolean z6, Function0<Float> function0, MutableState<LottieDynamicProperties> mutableState) {
        super(1);
        this.$composition = lottieComposition;
        this.$contentScale = contentScale;
        this.$alignment = alignment;
        this.$matrix = matrix;
        this.$drawable = lottieDrawable;
        this.$enableMergePaths = z2;
        this.$renderMode = renderMode;
        this.$asyncUpdates = asyncUpdates;
        this.$fontMap = map;
        this.$dynamicProperties = lottieDynamicProperties;
        this.$outlineMasksAndMattes = z3;
        this.$applyOpacityToLayers = z4;
        this.$maintainOriginalImageBounds = z5;
        this.$clipToCompositionBounds = z6;
        this.$progress = function0;
        this.$setDynamicProperties$delegate = mutableState;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DrawScope) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull DrawScope drawScope) {
        Intrinsics.checkNotNullParameter(drawScope, "$this$Canvas");
        LottieComposition lottieComposition = this.$composition;
        ContentScale contentScale = this.$contentScale;
        Alignment alignment = this.$alignment;
        Matrix matrix = this.$matrix;
        LottieDrawable lottieDrawable = this.$drawable;
        boolean z2 = this.$enableMergePaths;
        RenderMode renderMode = this.$renderMode;
        AsyncUpdates asyncUpdates = this.$asyncUpdates;
        Map<String, Typeface> map = this.$fontMap;
        LottieDynamicProperties lottieDynamicProperties = this.$dynamicProperties;
        boolean z3 = this.$outlineMasksAndMattes;
        boolean z4 = this.$applyOpacityToLayers;
        boolean z5 = this.$maintainOriginalImageBounds;
        boolean z6 = this.$clipToCompositionBounds;
        Function0<Float> function0 = this.$progress;
        MutableState<LottieDynamicProperties> mutableState = this.$setDynamicProperties$delegate;
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        boolean z7 = z5;
        long Size = SizeKt.Size((float) lottieComposition.getBounds().width(), (float) lottieComposition.getBounds().height());
        boolean z8 = z3;
        long IntSize = IntSizeKt.IntSize(MathKt.roundToInt(Size.m4446getWidthimpl(drawScope.m5250getSizeNHjbRc())), MathKt.roundToInt(Size.m4443getHeightimpl(drawScope.m5250getSizeNHjbRc())));
        MutableState<LottieDynamicProperties> mutableState2 = mutableState;
        long r02 = contentScale.m6175computeScaleFactorH7hwNQA(Size, drawScope.m5250getSizeNHjbRc());
        Function0<Float> function02 = function0;
        boolean z9 = z7;
        boolean z10 = z6;
        boolean z11 = z8;
        boolean z12 = z4;
        LottieDynamicProperties lottieDynamicProperties2 = lottieDynamicProperties;
        long r3 = alignment.m4171alignKFBX0sM(LottieAnimationKt.m8157timesUQTWf7w(Size, r02), IntSize, drawScope.getLayoutDirection());
        matrix.reset();
        matrix.preTranslate((float) IntOffset.m7767getXimpl(r3), (float) IntOffset.m7768getYimpl(r3));
        matrix.preScale(ScaleFactor.m6325getScaleXimpl(r02), ScaleFactor.m6326getScaleYimpl(r02));
        lottieDrawable.enableMergePathsForKitKatAndAbove(z2);
        lottieDrawable.setRenderMode(renderMode);
        lottieDrawable.setAsyncUpdates(asyncUpdates);
        LottieComposition lottieComposition2 = lottieComposition;
        lottieDrawable.setComposition(lottieComposition2);
        lottieDrawable.setFontMap(map);
        if (lottieDynamicProperties2 != LottieAnimationKt.LottieAnimation$lambda$3(mutableState2)) {
            LottieDynamicProperties access$LottieAnimation$lambda$3 = LottieAnimationKt.LottieAnimation$lambda$3(mutableState2);
            if (access$LottieAnimation$lambda$3 != null) {
                access$LottieAnimation$lambda$3.removeFrom$lottie_compose_release(lottieDrawable);
            }
            if (lottieDynamicProperties2 != null) {
                lottieDynamicProperties2.addTo$lottie_compose_release(lottieDrawable);
            }
            LottieAnimationKt.LottieAnimation$lambda$4(mutableState2, lottieDynamicProperties2);
        }
        lottieDrawable.setOutlineMasksAndMattes(z8);
        lottieDrawable.setApplyingOpacityToLayersEnabled(z12);
        lottieDrawable.setMaintainOriginalImageBounds(z7);
        lottieDrawable.setClipToCompositionBounds(z10);
        lottieDrawable.setProgress(function02.invoke().floatValue());
        lottieDrawable.setBounds(0, 0, lottieComposition2.getBounds().width(), lottieComposition2.getBounds().height());
        lottieDrawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas), matrix);
    }
}
