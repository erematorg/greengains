package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class RepeaterContent implements DrawingContent, PathContent, GreedyContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private ContentGroup contentGroup;
    private final BaseKeyframeAnimation<Float, Float> copies;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> offset;
    private final Path path = new Path();
    private final TransformKeyframeAnimation transform;

    public RepeaterContent(LottieDrawable lottieDrawable2, BaseLayer baseLayer, Repeater repeater) {
        this.lottieDrawable = lottieDrawable2;
        this.layer = baseLayer;
        this.name = repeater.getName();
        this.hidden = repeater.isHidden();
        BaseKeyframeAnimation<Float, Float> createAnimation = repeater.getCopies().createAnimation();
        this.copies = createAnimation;
        baseLayer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
        BaseKeyframeAnimation<Float, Float> createAnimation2 = repeater.getOffset().createAnimation();
        this.offset = createAnimation2;
        baseLayer.addAnimation(createAnimation2);
        createAnimation2.addUpdateListener(this);
        TransformKeyframeAnimation createAnimation3 = repeater.getTransform().createAnimation();
        this.transform = createAnimation3;
        createAnimation3.addAnimationsToLayer(baseLayer);
        createAnimation3.addListener(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0005 A[LOOP:0: B:3:0x0005->B:6:0x000f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void absorbContent(java.util.ListIterator<com.airbnb.lottie.animation.content.Content> r9) {
        /*
            r8 = this;
            com.airbnb.lottie.animation.content.ContentGroup r0 = r8.contentGroup
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r9.hasPrevious()
            if (r0 == 0) goto L_0x0012
            java.lang.Object r0 = r9.previous()
            if (r0 == r8) goto L_0x0012
            goto L_0x0005
        L_0x0012:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0017:
            boolean r0 = r9.hasPrevious()
            if (r0 == 0) goto L_0x002a
            java.lang.Object r0 = r9.previous()
            com.airbnb.lottie.animation.content.Content r0 = (com.airbnb.lottie.animation.content.Content) r0
            r6.add(r0)
            r9.remove()
            goto L_0x0017
        L_0x002a:
            java.util.Collections.reverse(r6)
            com.airbnb.lottie.animation.content.ContentGroup r9 = new com.airbnb.lottie.animation.content.ContentGroup
            com.airbnb.lottie.LottieDrawable r2 = r8.lottieDrawable
            com.airbnb.lottie.model.layer.BaseLayer r3 = r8.layer
            boolean r5 = r8.hidden
            r7 = 0
            java.lang.String r4 = "Repeater"
            r1 = r9
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r8.contentGroup = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RepeaterContent.absorbContent(java.util.ListIterator):void");
    }

    public <T> void addValueCallback(T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        if (!this.transform.applyValueCallback(t2, lottieValueCallback)) {
            if (t2 == LottieProperty.REPEATER_COPIES) {
                this.copies.setValueCallback(lottieValueCallback);
            } else if (t2 == LottieProperty.REPEATER_OFFSET) {
                this.offset.setValueCallback(lottieValueCallback);
            }
        }
    }

    public void draw(Canvas canvas, Matrix matrix2, int i3) {
        float floatValue = this.copies.getValue().floatValue();
        float floatValue2 = this.offset.getValue().floatValue();
        float floatValue3 = this.transform.getStartOpacity().getValue().floatValue() / 100.0f;
        float floatValue4 = this.transform.getEndOpacity().getValue().floatValue() / 100.0f;
        for (int i4 = ((int) floatValue) - 1; i4 >= 0; i4--) {
            this.matrix.set(matrix2);
            float f2 = (float) i4;
            this.matrix.preConcat(this.transform.getMatrixForRepeater(f2 + floatValue2));
            this.contentGroup.draw(canvas, this.matrix, (int) (MiscUtils.lerp(floatValue3, floatValue4, f2 / floatValue) * ((float) i3)));
        }
    }

    public void getBounds(RectF rectF, Matrix matrix2, boolean z2) {
        this.contentGroup.getBounds(rectF, matrix2, z2);
    }

    public String getName() {
        return this.name;
    }

    public Path getPath() {
        Path path2 = this.contentGroup.getPath();
        this.path.reset();
        float floatValue = this.copies.getValue().floatValue();
        float floatValue2 = this.offset.getValue().floatValue();
        for (int i3 = ((int) floatValue) - 1; i3 >= 0; i3--) {
            this.matrix.set(this.transform.getMatrixForRepeater(((float) i3) + floatValue2));
            this.path.addPath(path2, this.matrix);
        }
        return this.path;
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void resolveKeyPath(KeyPath keyPath, int i3, List<KeyPath> list, KeyPath keyPath2) {
        MiscUtils.resolveKeyPath(keyPath, i3, list, keyPath2, this);
        for (int i4 = 0; i4 < this.contentGroup.getContents().size(); i4++) {
            Content content = this.contentGroup.getContents().get(i4);
            if (content instanceof KeyPathElementContent) {
                MiscUtils.resolveKeyPath(keyPath, i3, list, keyPath2, (KeyPathElementContent) content);
            }
        }
    }

    public void setContents(List<Content> list, List<Content> list2) {
        this.contentGroup.setContents(list, list2);
    }
}
