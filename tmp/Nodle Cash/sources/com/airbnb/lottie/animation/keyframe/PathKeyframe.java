package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.value.Keyframe;

public class PathKeyframe extends Keyframe<PointF> {
    @Nullable
    private Path path;
    private final Keyframe<PointF> pointKeyFrame;

    public PathKeyframe(LottieComposition lottieComposition, Keyframe<PointF> keyframe) {
        super(lottieComposition, (PointF) keyframe.startValue, (PointF) keyframe.endValue, keyframe.interpolator, keyframe.xInterpolator, keyframe.yInterpolator, keyframe.startFrame, keyframe.endFrame);
        this.pointKeyFrame = keyframe;
        createPath();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r4.startValue;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createPath() {
        /*
            r4 = this;
            T r0 = r4.endValue
            if (r0 == 0) goto L_0x001b
            T r1 = r4.startValue
            if (r1 == 0) goto L_0x001b
            android.graphics.PointF r1 = (android.graphics.PointF) r1
            r2 = r0
            android.graphics.PointF r2 = (android.graphics.PointF) r2
            float r2 = r2.x
            android.graphics.PointF r0 = (android.graphics.PointF) r0
            float r0 = r0.y
            boolean r0 = r1.equals(r2, r0)
            if (r0 == 0) goto L_0x001b
            r0 = 1
            goto L_0x001c
        L_0x001b:
            r0 = 0
        L_0x001c:
            T r1 = r4.startValue
            if (r1 == 0) goto L_0x0036
            T r2 = r4.endValue
            if (r2 == 0) goto L_0x0036
            if (r0 != 0) goto L_0x0036
            android.graphics.PointF r1 = (android.graphics.PointF) r1
            android.graphics.PointF r2 = (android.graphics.PointF) r2
            com.airbnb.lottie.value.Keyframe<android.graphics.PointF> r0 = r4.pointKeyFrame
            android.graphics.PointF r3 = r0.pathCp1
            android.graphics.PointF r0 = r0.pathCp2
            android.graphics.Path r0 = com.airbnb.lottie.utils.Utils.createPath(r1, r2, r3, r0)
            r4.path = r0
        L_0x0036:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.PathKeyframe.createPath():void");
    }

    @Nullable
    public Path getPath() {
        return this.path;
    }
}
