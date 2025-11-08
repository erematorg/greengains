package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import androidx.annotation.CallSuper;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    private final List<BaseKeyframeAnimation<?, ?>> animations;
    @Nullable
    BlurMaskFilter blurMaskFilter;
    float blurMaskFilterRadius;
    final Matrix boundsMatrix;
    private final RectF canvasBounds;
    private final Matrix canvasMatrix = new Matrix();
    private final Paint clearPaint;
    private final Paint contentPaint = new LPaint(1);
    private final String drawTraceName;
    private final Paint dstInPaint;
    private final Paint dstOutPaint;
    @Nullable
    private FloatKeyframeAnimation inOutAnimation;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    @Nullable
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect;
    private final Matrix matrix = new Matrix();
    private final RectF matteBoundsRect;
    @Nullable
    private BaseLayer matteLayer;
    private final Paint mattePaint;
    private boolean outlineMasksAndMattes;
    @Nullable
    private Paint outlineMasksAndMattesPaint;
    @Nullable
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    private final Path path = new Path();
    private final RectF rect;
    private final RectF tempMaskBoundsRect;
    final TransformKeyframeAnimation transform;
    private boolean visible;

    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode;
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|(3:29|30|32)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|32) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        static {
            /*
                com.airbnb.lottie.model.content.Mask$MaskMode[] r0 = com.airbnb.lottie.model.content.Mask.MaskMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = r0
                r1 = 1
                com.airbnb.lottie.model.content.Mask$MaskMode r2 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.content.Mask$MaskMode r3 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_SUBTRACT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.airbnb.lottie.model.content.Mask$MaskMode r4 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_INTERSECT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.airbnb.lottie.model.content.Mask$MaskMode r5 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_ADD     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                com.airbnb.lottie.model.layer.Layer$LayerType[] r4 = com.airbnb.lottie.model.layer.Layer.LayerType.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = r4
                com.airbnb.lottie.model.layer.Layer$LayerType r5 = com.airbnb.lottie.model.layer.Layer.LayerType.SHAPE     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x004e }
                com.airbnb.lottie.model.layer.Layer$LayerType r4 = com.airbnb.lottie.model.layer.Layer.LayerType.PRE_COMP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.SOLID     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.IMAGE     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x006d }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.NULL     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.TEXT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.airbnb.lottie.model.layer.Layer$LayerType r1 = com.airbnb.lottie.model.layer.Layer.LayerType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.BaseLayer.AnonymousClass1.<clinit>():void");
        }
    }

    public BaseLayer(LottieDrawable lottieDrawable2, Layer layer) {
        PorterDuff.Mode mode = PorterDuff.Mode.DST_IN;
        this.dstInPaint = new LPaint(1, mode);
        PorterDuff.Mode mode2 = PorterDuff.Mode.DST_OUT;
        this.dstOutPaint = new LPaint(1, mode2);
        LPaint lPaint = new LPaint(1);
        this.mattePaint = lPaint;
        this.clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
        this.rect = new RectF();
        this.canvasBounds = new RectF();
        this.maskBoundsRect = new RectF();
        this.matteBoundsRect = new RectF();
        this.tempMaskBoundsRect = new RectF();
        this.boundsMatrix = new Matrix();
        this.animations = new ArrayList();
        this.visible = true;
        this.blurMaskFilterRadius = 0.0f;
        this.lottieDrawable = lottieDrawable2;
        this.layerModel = layer;
        this.drawTraceName = layer.getName() + "#draw";
        if (layer.getMatteType() == Layer.MatteType.INVERT) {
            lPaint.setXfermode(new PorterDuffXfermode(mode2));
        } else {
            lPaint.setXfermode(new PorterDuffXfermode(mode));
        }
        TransformKeyframeAnimation createAnimation = layer.getTransform().createAnimation();
        this.transform = createAnimation;
        createAnimation.addListener(this);
        if (layer.getMasks() != null && !layer.getMasks().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation = new MaskKeyframeAnimation(layer.getMasks());
            this.mask = maskKeyframeAnimation;
            for (BaseKeyframeAnimation<ShapeData, Path> addUpdateListener : maskKeyframeAnimation.getMaskAnimations()) {
                addUpdateListener.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation next : this.mask.getOpacityAnimations()) {
                addAnimation(next);
                next.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    private void applyAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) baseKeyframeAnimation2.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) baseKeyframeAnimation2.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) baseKeyframeAnimation2.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) baseKeyframeAnimation2.getValue().intValue()) * 2.55f));
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation, BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstOutPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) baseKeyframeAnimation2.getValue().intValue()) * 2.55f));
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyMasks(Canvas canvas, Matrix matrix2) {
        L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint, 19);
        L.endSection("Layer#saveLayer");
        for (int i3 = 0; i3 < this.mask.getMasks().size(); i3++) {
            Mask mask2 = this.mask.getMasks().get(i3);
            BaseKeyframeAnimation baseKeyframeAnimation = this.mask.getMaskAnimations().get(i3);
            BaseKeyframeAnimation baseKeyframeAnimation2 = this.mask.getOpacityAnimations().get(i3);
            int i4 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()];
            if (i4 != 1) {
                if (i4 == 2) {
                    if (i3 == 0) {
                        this.contentPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                    }
                    if (mask2.isInverted()) {
                        applyInvertedSubtractMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                    } else {
                        applySubtractMask(canvas, matrix2, baseKeyframeAnimation);
                    }
                } else if (i4 != 3) {
                    if (i4 == 4) {
                        if (mask2.isInverted()) {
                            applyInvertedAddMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                        } else {
                            applyAddMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                        }
                    }
                } else if (mask2.isInverted()) {
                    applyInvertedIntersectMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                } else {
                    applyIntersectMask(canvas, matrix2, baseKeyframeAnimation, baseKeyframeAnimation2);
                }
            } else if (areAllMasksNone()) {
                this.contentPaint.setAlpha(255);
                canvas.drawRect(this.rect, this.contentPaint);
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas.restore();
        L.endSection("Layer#restoreLayer");
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> baseKeyframeAnimation) {
        this.path.set(baseKeyframeAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private boolean areAllMasksNone() {
        if (this.mask.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i3 = 0; i3 < this.mask.getMasks().size(); i3++) {
            if (this.mask.getMasks().get(i3).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers == null) {
            if (this.parentLayer == null) {
                this.parentLayers = Collections.emptyList();
                return;
            }
            this.parentLayers = new ArrayList();
            for (BaseLayer baseLayer = this.parentLayer; baseLayer != null; baseLayer = baseLayer.parentLayer) {
                this.parentLayers.add(baseLayer);
            }
        }
    }

    private void clearCanvas(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        RectF rectF = this.rect;
        canvas.drawRect(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    @Nullable
    public static BaseLayer forModel(CompositionLayer compositionLayer, Layer layer, LottieDrawable lottieDrawable2, LottieComposition lottieComposition) {
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[layer.getLayerType().ordinal()]) {
            case 1:
                return new ShapeLayer(lottieDrawable2, layer, compositionLayer, lottieComposition);
            case 2:
                return new CompositionLayer(lottieDrawable2, layer, lottieComposition.getPrecomps(layer.getRefId()), lottieComposition);
            case 3:
                return new SolidLayer(lottieDrawable2, layer);
            case 4:
                return new ImageLayer(lottieDrawable2, layer);
            case 5:
                return new NullLayer(lottieDrawable2, layer);
            case 6:
                return new TextLayer(lottieDrawable2, layer);
            default:
                Logger.warning("Unknown layer type " + layer.getLayerType());
                return null;
        }
    }

    private void intersectBoundsWithMask(RectF rectF, Matrix matrix2) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            for (int i3 = 0; i3 < size; i3++) {
                Mask mask2 = this.mask.getMasks().get(i3);
                Path path2 = (Path) this.mask.getMaskAnimations().get(i3).getValue();
                if (path2 != null) {
                    this.path.set(path2);
                    this.path.transform(matrix2);
                    int i4 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()];
                    if (i4 != 1 && i4 != 2) {
                        if ((i4 != 3 && i4 != 4) || !mask2.isInverted()) {
                            this.path.computeBounds(this.tempMaskBoundsRect, false);
                            if (i3 == 0) {
                                this.maskBoundsRect.set(this.tempMaskBoundsRect);
                            } else {
                                RectF rectF2 = this.maskBoundsRect;
                                rectF2.set(Math.min(rectF2.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            if (!rectF.intersect(this.maskBoundsRect)) {
                rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private void intersectBoundsWithMatte(RectF rectF, Matrix matrix2) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != Layer.MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix2, true);
            if (!rectF.intersect(this.matteBoundsRect)) {
                rectF.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setupInOutAnimations$0() {
        setVisible(this.inOutAnimation.getFloatValue() == 1.0f);
    }

    private void recordRenderTime(float f2) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), f2);
    }

    private void setVisible(boolean z2) {
        if (z2 != this.visible) {
            this.visible = z2;
            invalidateSelf();
        }
    }

    private void setupInOutAnimations() {
        boolean z2 = true;
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            this.inOutAnimation = floatKeyframeAnimation;
            floatKeyframeAnimation.setIsDiscrete();
            this.inOutAnimation.addUpdateListener(new a(this));
            if (((Float) this.inOutAnimation.getValue()).floatValue() != 1.0f) {
                z2 = false;
            }
            setVisible(z2);
            addAnimation(this.inOutAnimation);
            return;
        }
        setVisible(true);
    }

    public void addAnimation(@Nullable BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        if (baseKeyframeAnimation != null) {
            this.animations.add(baseKeyframeAnimation);
        }
    }

    @CallSuper
    public <T> void addValueCallback(T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        this.transform.applyValueCallback(t2, lottieValueCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0050, code lost:
        r0 = r0.getValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r7, android.graphics.Matrix r8, int r9) {
        /*
            r6 = this;
            java.lang.String r0 = r6.drawTraceName
            com.airbnb.lottie.L.beginSection(r0)
            boolean r0 = r6.visible
            if (r0 == 0) goto L_0x01b8
            com.airbnb.lottie.model.layer.Layer r0 = r6.layerModel
            boolean r0 = r0.isHidden()
            if (r0 == 0) goto L_0x0013
            goto L_0x01b8
        L_0x0013:
            r6.buildParentLayerListIfNeeded()
            java.lang.String r0 = "Layer#parentMatrix"
            com.airbnb.lottie.L.beginSection(r0)
            android.graphics.Matrix r1 = r6.matrix
            r1.reset()
            android.graphics.Matrix r1 = r6.matrix
            r1.set(r8)
            java.util.List<com.airbnb.lottie.model.layer.BaseLayer> r1 = r6.parentLayers
            int r1 = r1.size()
            int r1 = r1 + -1
        L_0x002d:
            if (r1 < 0) goto L_0x0045
            android.graphics.Matrix r2 = r6.matrix
            java.util.List<com.airbnb.lottie.model.layer.BaseLayer> r3 = r6.parentLayers
            java.lang.Object r3 = r3.get(r1)
            com.airbnb.lottie.model.layer.BaseLayer r3 = (com.airbnb.lottie.model.layer.BaseLayer) r3
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r3 = r3.transform
            android.graphics.Matrix r3 = r3.getMatrix()
            r2.preConcat(r3)
            int r1 = r1 + -1
            goto L_0x002d
        L_0x0045:
            com.airbnb.lottie.L.endSection(r0)
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r0 = r6.transform
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation r0 = r0.getOpacity()
            if (r0 == 0) goto L_0x005d
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L_0x005d
            int r0 = r0.intValue()
            goto L_0x005f
        L_0x005d:
            r0 = 100
        L_0x005f:
            float r9 = (float) r9
            r1 = 1132396544(0x437f0000, float:255.0)
            float r9 = r9 / r1
            float r0 = (float) r0
            float r9 = r9 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r9 = r9 / r0
            float r9 = r9 * r1
            int r9 = (int) r9
            boolean r0 = r6.hasMatteOnThisLayer()
            java.lang.String r1 = "Layer#drawLayer"
            if (r0 != 0) goto L_0x0098
            boolean r0 = r6.hasMasksOnThisLayer()
            if (r0 != 0) goto L_0x0098
            android.graphics.Matrix r8 = r6.matrix
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r0 = r6.transform
            android.graphics.Matrix r0 = r0.getMatrix()
            r8.preConcat(r0)
            com.airbnb.lottie.L.beginSection(r1)
            android.graphics.Matrix r8 = r6.matrix
            r6.drawLayer(r7, r8, r9)
            com.airbnb.lottie.L.endSection(r1)
            java.lang.String r7 = r6.drawTraceName
            float r7 = com.airbnb.lottie.L.endSection(r7)
            r6.recordRenderTime(r7)
            return
        L_0x0098:
            java.lang.String r0 = "Layer#computeBounds"
            com.airbnb.lottie.L.beginSection(r0)
            android.graphics.RectF r2 = r6.rect
            android.graphics.Matrix r3 = r6.matrix
            r4 = 0
            r6.getBounds(r2, r3, r4)
            android.graphics.RectF r2 = r6.rect
            r6.intersectBoundsWithMatte(r2, r8)
            android.graphics.Matrix r2 = r6.matrix
            com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation r3 = r6.transform
            android.graphics.Matrix r3 = r3.getMatrix()
            r2.preConcat(r3)
            android.graphics.RectF r2 = r6.rect
            android.graphics.Matrix r3 = r6.matrix
            r6.intersectBoundsWithMask(r2, r3)
            android.graphics.RectF r2 = r6.canvasBounds
            int r3 = r7.getWidth()
            float r3 = (float) r3
            int r4 = r7.getHeight()
            float r4 = (float) r4
            r5 = 0
            r2.set(r5, r5, r3, r4)
            android.graphics.Matrix r2 = r6.canvasMatrix
            r7.getMatrix(r2)
            android.graphics.Matrix r2 = r6.canvasMatrix
            boolean r2 = r2.isIdentity()
            if (r2 != 0) goto L_0x00e5
            android.graphics.Matrix r2 = r6.canvasMatrix
            r2.invert(r2)
            android.graphics.Matrix r2 = r6.canvasMatrix
            android.graphics.RectF r3 = r6.canvasBounds
            r2.mapRect(r3)
        L_0x00e5:
            android.graphics.RectF r2 = r6.rect
            android.graphics.RectF r3 = r6.canvasBounds
            boolean r2 = r2.intersect(r3)
            if (r2 != 0) goto L_0x00f4
            android.graphics.RectF r2 = r6.rect
            r2.set(r5, r5, r5, r5)
        L_0x00f4:
            com.airbnb.lottie.L.endSection(r0)
            android.graphics.RectF r0 = r6.rect
            float r0 = r0.width()
            r2 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x0175
            android.graphics.RectF r0 = r6.rect
            float r0 = r0.height()
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x0175
            java.lang.String r0 = "Layer#saveLayer"
            com.airbnb.lottie.L.beginSection(r0)
            android.graphics.Paint r2 = r6.contentPaint
            r3 = 255(0xff, float:3.57E-43)
            r2.setAlpha(r3)
            android.graphics.RectF r2 = r6.rect
            android.graphics.Paint r3 = r6.contentPaint
            com.airbnb.lottie.utils.Utils.saveLayerCompat(r7, r2, r3)
            com.airbnb.lottie.L.endSection(r0)
            r6.clearCanvas(r7)
            com.airbnb.lottie.L.beginSection(r1)
            android.graphics.Matrix r2 = r6.matrix
            r6.drawLayer(r7, r2, r9)
            com.airbnb.lottie.L.endSection(r1)
            boolean r1 = r6.hasMasksOnThisLayer()
            if (r1 == 0) goto L_0x013c
            android.graphics.Matrix r1 = r6.matrix
            r6.applyMasks(r7, r1)
        L_0x013c:
            boolean r1 = r6.hasMatteOnThisLayer()
            java.lang.String r2 = "Layer#restoreLayer"
            if (r1 == 0) goto L_0x016c
            java.lang.String r1 = "Layer#drawMatte"
            com.airbnb.lottie.L.beginSection(r1)
            com.airbnb.lottie.L.beginSection(r0)
            android.graphics.RectF r3 = r6.rect
            android.graphics.Paint r4 = r6.mattePaint
            r5 = 19
            com.airbnb.lottie.utils.Utils.saveLayerCompat(r7, r3, r4, r5)
            com.airbnb.lottie.L.endSection(r0)
            r6.clearCanvas(r7)
            com.airbnb.lottie.model.layer.BaseLayer r0 = r6.matteLayer
            r0.draw(r7, r8, r9)
            com.airbnb.lottie.L.beginSection(r2)
            r7.restore()
            com.airbnb.lottie.L.endSection(r2)
            com.airbnb.lottie.L.endSection(r1)
        L_0x016c:
            com.airbnb.lottie.L.beginSection(r2)
            r7.restore()
            com.airbnb.lottie.L.endSection(r2)
        L_0x0175:
            boolean r8 = r6.outlineMasksAndMattes
            if (r8 == 0) goto L_0x01ae
            android.graphics.Paint r8 = r6.outlineMasksAndMattesPaint
            if (r8 == 0) goto L_0x01ae
            android.graphics.Paint$Style r9 = android.graphics.Paint.Style.STROKE
            r8.setStyle(r9)
            android.graphics.Paint r8 = r6.outlineMasksAndMattesPaint
            r9 = -251901(0xfffffffffffc2803, float:NaN)
            r8.setColor(r9)
            android.graphics.Paint r8 = r6.outlineMasksAndMattesPaint
            r9 = 1082130432(0x40800000, float:4.0)
            r8.setStrokeWidth(r9)
            android.graphics.RectF r8 = r6.rect
            android.graphics.Paint r9 = r6.outlineMasksAndMattesPaint
            r7.drawRect(r8, r9)
            android.graphics.Paint r8 = r6.outlineMasksAndMattesPaint
            android.graphics.Paint$Style r9 = android.graphics.Paint.Style.FILL
            r8.setStyle(r9)
            android.graphics.Paint r8 = r6.outlineMasksAndMattesPaint
            r9 = 1357638635(0x50ebebeb, float:3.1664855E10)
            r8.setColor(r9)
            android.graphics.RectF r8 = r6.rect
            android.graphics.Paint r9 = r6.outlineMasksAndMattesPaint
            r7.drawRect(r8, r9)
        L_0x01ae:
            java.lang.String r7 = r6.drawTraceName
            float r7 = com.airbnb.lottie.L.endSection(r7)
            r6.recordRenderTime(r7)
            return
        L_0x01b8:
            java.lang.String r6 = r6.drawTraceName
            com.airbnb.lottie.L.endSection(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.BaseLayer.draw(android.graphics.Canvas, android.graphics.Matrix, int):void");
    }

    public abstract void drawLayer(Canvas canvas, Matrix matrix2, int i3);

    public LBlendMode getBlendMode() {
        return this.layerModel.getBlendMode();
    }

    @Nullable
    public BlurEffect getBlurEffect() {
        return this.layerModel.getBlurEffect();
    }

    public BlurMaskFilter getBlurMaskFilter(float f2) {
        if (this.blurMaskFilterRadius == f2) {
            return this.blurMaskFilter;
        }
        BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(f2 / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.blurMaskFilter = blurMaskFilter2;
        this.blurMaskFilterRadius = f2;
        return blurMaskFilter2;
    }

    @CallSuper
    public void getBounds(RectF rectF, Matrix matrix2, boolean z2) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(matrix2);
        if (z2) {
            List<BaseLayer> list = this.parentLayers;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    this.boundsMatrix.preConcat(this.parentLayers.get(size).transform.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.parentLayer;
                if (baseLayer != null) {
                    this.boundsMatrix.preConcat(baseLayer.transform.getMatrix());
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    @Nullable
    public DropShadowEffect getDropShadowEffect() {
        return this.layerModel.getDropShadowEffect();
    }

    public Layer getLayerModel() {
        return this.layerModel;
    }

    public String getName() {
        return this.layerModel.getName();
    }

    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return maskKeyframeAnimation != null && !maskKeyframeAnimation.getMaskAnimations().isEmpty();
    }

    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    public void onValueChanged() {
        invalidateSelf();
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animations.remove(baseKeyframeAnimation);
    }

    public void resolveChildKeyPath(KeyPath keyPath, int i3, List<KeyPath> list, KeyPath keyPath2) {
    }

    public void resolveKeyPath(KeyPath keyPath, int i3, List<KeyPath> list, KeyPath keyPath2) {
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            KeyPath addKey = keyPath2.addKey(baseLayer.getName());
            if (keyPath.fullyResolvesTo(this.matteLayer.getName(), i3)) {
                list.add(addKey.resolve(this.matteLayer));
            }
            if (keyPath.propagateToChildren(getName(), i3)) {
                this.matteLayer.resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(this.matteLayer.getName(), i3) + i3, list, addKey);
            }
        }
        if (keyPath.matches(getName(), i3)) {
            if (!"__container".equals(getName())) {
                keyPath2 = keyPath2.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), i3)) {
                    list.add(keyPath2.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), i3)) {
                resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(getName(), i3) + i3, list, keyPath2);
            }
        }
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public void setMatteLayer(@Nullable BaseLayer baseLayer) {
        this.matteLayer = baseLayer;
    }

    public void setOutlineMasksAndMattes(boolean z2) {
        if (z2 && this.outlineMasksAndMattesPaint == null) {
            this.outlineMasksAndMattesPaint = new LPaint();
        }
        this.outlineMasksAndMattes = z2;
    }

    public void setParentLayer(@Nullable BaseLayer baseLayer) {
        this.parentLayer = baseLayer;
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        L.beginSection("BaseLayer#setProgress");
        L.beginSection("BaseLayer#setProgress.transform");
        this.transform.setProgress(f2);
        L.endSection("BaseLayer#setProgress.transform");
        if (this.mask != null) {
            L.beginSection("BaseLayer#setProgress.mask");
            for (int i3 = 0; i3 < this.mask.getMaskAnimations().size(); i3++) {
                this.mask.getMaskAnimations().get(i3).setProgress(f2);
            }
            L.endSection("BaseLayer#setProgress.mask");
        }
        if (this.inOutAnimation != null) {
            L.beginSection("BaseLayer#setProgress.inout");
            this.inOutAnimation.setProgress(f2);
            L.endSection("BaseLayer#setProgress.inout");
        }
        if (this.matteLayer != null) {
            L.beginSection("BaseLayer#setProgress.matte");
            this.matteLayer.setProgress(f2);
            L.endSection("BaseLayer#setProgress.matte");
        }
        L.beginSection("BaseLayer#setProgress.animations." + this.animations.size());
        for (int i4 = 0; i4 < this.animations.size(); i4++) {
            this.animations.get(i4).setProgress(f2);
        }
        L.endSection("BaseLayer#setProgress.animations." + this.animations.size());
        L.endSection("BaseLayer#setProgress");
    }
}
