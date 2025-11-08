package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.Collections;
import java.util.List;

public class ShapeLayer extends BaseLayer {
    private final CompositionLayer compositionLayer;
    private final ContentGroup contentGroup;

    public ShapeLayer(LottieDrawable lottieDrawable, Layer layer, CompositionLayer compositionLayer2, LottieComposition lottieComposition) {
        super(lottieDrawable, layer);
        this.compositionLayer = compositionLayer2;
        ContentGroup contentGroup2 = new ContentGroup(lottieDrawable, this, new ShapeGroup("__container", layer.getShapes(), false), lottieComposition);
        this.contentGroup = contentGroup2;
        contentGroup2.setContents(Collections.emptyList(), Collections.emptyList());
    }

    public void drawLayer(@NonNull Canvas canvas, Matrix matrix, int i3) {
        this.contentGroup.draw(canvas, matrix, i3);
    }

    @Nullable
    public BlurEffect getBlurEffect() {
        BlurEffect blurEffect = super.getBlurEffect();
        return blurEffect != null ? blurEffect : this.compositionLayer.getBlurEffect();
    }

    public void getBounds(RectF rectF, Matrix matrix, boolean z2) {
        super.getBounds(rectF, matrix, z2);
        this.contentGroup.getBounds(rectF, this.boundsMatrix, z2);
    }

    @Nullable
    public DropShadowEffect getDropShadowEffect() {
        DropShadowEffect dropShadowEffect = super.getDropShadowEffect();
        return dropShadowEffect != null ? dropShadowEffect : this.compositionLayer.getDropShadowEffect();
    }

    public void resolveChildKeyPath(KeyPath keyPath, int i3, List<KeyPath> list, KeyPath keyPath2) {
        this.contentGroup.resolveKeyPath(keyPath, i3, list, keyPath2);
    }
}
