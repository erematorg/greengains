package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class TextKeyframeAnimation extends KeyframeAnimation<DocumentData> {
    public TextKeyframeAnimation(List<Keyframe<DocumentData>> list) {
        super(list);
    }

    public void setStringValueCallback(final LottieValueCallback<String> lottieValueCallback) {
        final LottieFrameInfo lottieFrameInfo = new LottieFrameInfo();
        final DocumentData documentData = new DocumentData();
        super.setValueCallback(new LottieValueCallback<DocumentData>() {
            /* JADX WARNING: type inference failed for: r17v0, types: [com.airbnb.lottie.value.LottieFrameInfo, com.airbnb.lottie.value.LottieFrameInfo<com.airbnb.lottie.model.DocumentData>] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.airbnb.lottie.model.DocumentData getValue(com.airbnb.lottie.value.LottieFrameInfo<com.airbnb.lottie.model.DocumentData> r17) {
                /*
                    r16 = this;
                    r0 = r16
                    com.airbnb.lottie.value.LottieFrameInfo r1 = r0
                    float r2 = r17.getStartFrame()
                    float r3 = r17.getEndFrame()
                    java.lang.Object r4 = r17.getStartValue()
                    com.airbnb.lottie.model.DocumentData r4 = (com.airbnb.lottie.model.DocumentData) r4
                    java.lang.String r4 = r4.text
                    java.lang.Object r5 = r17.getEndValue()
                    com.airbnb.lottie.model.DocumentData r5 = (com.airbnb.lottie.model.DocumentData) r5
                    java.lang.String r5 = r5.text
                    float r6 = r17.getLinearKeyframeProgress()
                    float r7 = r17.getInterpolatedKeyframeProgress()
                    float r8 = r17.getOverallProgress()
                    r1.set(r2, r3, r4, r5, r6, r7, r8)
                    com.airbnb.lottie.value.LottieValueCallback r1 = r4
                    com.airbnb.lottie.value.LottieFrameInfo r2 = r0
                    java.lang.Object r1 = r1.getValue(r2)
                    r3 = r1
                    java.lang.String r3 = (java.lang.String) r3
                    float r1 = r17.getInterpolatedKeyframeProgress()
                    r2 = 1065353216(0x3f800000, float:1.0)
                    int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                    if (r1 != 0) goto L_0x0047
                    java.lang.Object r1 = r17.getEndValue()
                L_0x0044:
                    com.airbnb.lottie.model.DocumentData r1 = (com.airbnb.lottie.model.DocumentData) r1
                    goto L_0x004c
                L_0x0047:
                    java.lang.Object r1 = r17.getStartValue()
                    goto L_0x0044
                L_0x004c:
                    com.airbnb.lottie.model.DocumentData r2 = r1
                    java.lang.String r4 = r1.fontName
                    float r5 = r1.size
                    com.airbnb.lottie.model.DocumentData$Justification r6 = r1.justification
                    int r7 = r1.tracking
                    float r8 = r1.lineHeight
                    float r9 = r1.baselineShift
                    int r10 = r1.color
                    int r11 = r1.strokeColor
                    float r12 = r1.strokeWidth
                    boolean r13 = r1.strokeOverFill
                    android.graphics.PointF r14 = r1.boxPosition
                    android.graphics.PointF r15 = r1.boxSize
                    r2.set(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
                    com.airbnb.lottie.model.DocumentData r0 = r1
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation.AnonymousClass1.getValue(com.airbnb.lottie.value.LottieFrameInfo):com.airbnb.lottie.model.DocumentData");
            }
        });
    }

    public DocumentData getValue(Keyframe<DocumentData> keyframe, float f2) {
        T t2;
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            float f3 = keyframe.startFrame;
            Float f4 = keyframe.endFrame;
            float floatValue = f4 == null ? Float.MAX_VALUE : f4.floatValue();
            T t3 = keyframe.startValue;
            DocumentData documentData = (DocumentData) t3;
            T t4 = keyframe.endValue;
            return (DocumentData) lottieValueCallback.getValueInternal(f3, floatValue, documentData, t4 == null ? (DocumentData) t3 : (DocumentData) t4, f2, getInterpolatedCurrentKeyframeProgress(), getProgress());
        } else if (f2 != 1.0f || (t2 = keyframe.endValue) == null) {
            return (DocumentData) keyframe.startValue;
        } else {
            return (DocumentData) t2;
        }
    }
}
