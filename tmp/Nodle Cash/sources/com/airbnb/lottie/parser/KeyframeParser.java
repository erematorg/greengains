package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

class KeyframeParser {
    static JsonReader.Options INTERPOLATOR_NAMES = JsonReader.Options.of("x", "y");
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float MAX_CP_VALUE = 100.0f;
    static JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", TypedValues.TransitionType.S_TO, "ti");
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;

    @Nullable
    private static WeakReference<Interpolator> getInterpolator(int i3) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(i3);
        }
        return weakReference;
    }

    private static Interpolator interpolatorFor(PointF pointF, PointF pointF2) {
        Interpolator create;
        pointF.x = MiscUtils.clamp(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.clamp(pointF.y, -100.0f, 100.0f);
        pointF2.x = MiscUtils.clamp(pointF2.x, -1.0f, 1.0f);
        float clamp = MiscUtils.clamp(pointF2.y, -100.0f, 100.0f);
        pointF2.y = clamp;
        int hashFor = Utils.hashFor(pointF.x, pointF.y, pointF2.x, clamp);
        Interpolator interpolator = null;
        WeakReference<Interpolator> interpolator2 = L.getDisablePathInterpolatorCache() ? null : getInterpolator(hashFor);
        if (interpolator2 != null) {
            interpolator = interpolator2.get();
        }
        if (interpolator2 == null || interpolator == null) {
            try {
                create = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e3) {
                create = "The Path cannot loop back on itself.".equals(e3.getMessage()) ? PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
            }
            interpolator = create;
            if (!L.getDisablePathInterpolatorCache()) {
                try {
                    putInterpolator(hashFor, new WeakReference(interpolator));
                } catch (ArrayIndexOutOfBoundsException unused) {
                }
            }
        }
        return interpolator;
    }

    public static <T> Keyframe<T> parse(JsonReader jsonReader, LottieComposition lottieComposition, float f2, ValueParser<T> valueParser, boolean z2, boolean z3) throws IOException {
        return (!z2 || !z3) ? z2 ? parseKeyframe(lottieComposition, jsonReader, f2, valueParser) : parseStaticValue(jsonReader, f2, valueParser) : parseMultiDimensionalKeyframe(lottieComposition, jsonReader, f2, valueParser);
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f2, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        T t2;
        jsonReader.beginObject();
        PointF pointF = null;
        T t3 = null;
        T t4 = null;
        PointF pointF2 = null;
        PointF pointF3 = null;
        float f3 = 0.0f;
        boolean z2 = false;
        PointF pointF4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    t4 = valueParser.parse(jsonReader, f2);
                    break;
                case 2:
                    t3 = valueParser.parse(jsonReader, f2);
                    break;
                case 3:
                    pointF = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 4:
                    pointF4 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 5:
                    if (jsonReader.nextInt() != 1) {
                        z2 = false;
                        break;
                    } else {
                        z2 = true;
                        break;
                    }
                case 6:
                    pointF2 = JsonUtils.jsonToPoint(jsonReader, f2);
                    break;
                case 7:
                    pointF3 = JsonUtils.jsonToPoint(jsonReader, f2);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        if (z2) {
            interpolator = LINEAR_INTERPOLATOR;
            t2 = t4;
        } else {
            interpolator = (pointF == null || pointF4 == null) ? LINEAR_INTERPOLATOR : interpolatorFor(pointF, pointF4);
            t2 = t3;
        }
        Keyframe keyframe = new Keyframe(lottieComposition, t4, t2, interpolator, f3, (Float) null);
        keyframe.pathCp1 = pointF2;
        keyframe.pathCp2 = pointF3;
        return keyframe;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e0, code lost:
        r14 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> com.airbnb.lottie.value.Keyframe<T> parseMultiDimensionalKeyframe(com.airbnb.lottie.LottieComposition r21, com.airbnb.lottie.parser.moshi.JsonReader r22, float r23, com.airbnb.lottie.parser.ValueParser<T> r24) throws java.io.IOException {
        /*
            r0 = r22
            r1 = r23
            r2 = r24
            r22.beginObject()
            r3 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x0016:
            boolean r17 = r22.hasNext()
            if (r17 == 0) goto L_0x01a5
            com.airbnb.lottie.parser.moshi.JsonReader$Options r4 = NAMES
            int r4 = r0.selectName(r4)
            r5 = 1
            switch(r4) {
                case 0: goto L_0x019a;
                case 1: goto L_0x018f;
                case 2: goto L_0x0184;
                case 3: goto L_0x00ef;
                case 4: goto L_0x003e;
                case 5: goto L_0x0034;
                case 6: goto L_0x002f;
                case 7: goto L_0x002a;
                default: goto L_0x0026;
            }
        L_0x0026:
            r22.skipValue()
            goto L_0x0016
        L_0x002a:
            android.graphics.PointF r3 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r22, r23)
            goto L_0x0016
        L_0x002f:
            android.graphics.PointF r15 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r22, r23)
            goto L_0x0016
        L_0x0034:
            int r4 = r22.nextInt()
            if (r4 != r5) goto L_0x003c
            r6 = r5
            goto L_0x0016
        L_0x003c:
            r6 = 0
            goto L_0x0016
        L_0x003e:
            com.airbnb.lottie.parser.moshi.JsonReader$Token r4 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r5 = com.airbnb.lottie.parser.moshi.JsonReader.Token.BEGIN_OBJECT
            if (r4 != r5) goto L_0x00e5
            r22.beginObject()
            r4 = 0
            r5 = 0
            r12 = 0
            r13 = 0
        L_0x004d:
            boolean r18 = r22.hasNext()
            if (r18 == 0) goto L_0x00cc
            r18 = r15
            com.airbnb.lottie.parser.moshi.JsonReader$Options r15 = INTERPOLATOR_NAMES
            int r15 = r0.selectName(r15)
            if (r15 == 0) goto L_0x009f
            r19 = r3
            r3 = 1
            if (r15 == r3) goto L_0x006a
            r22.skipValue()
        L_0x0065:
            r15 = r18
            r3 = r19
            goto L_0x004d
        L_0x006a:
            com.airbnb.lottie.parser.moshi.JsonReader$Token r3 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r5 = com.airbnb.lottie.parser.moshi.JsonReader.Token.NUMBER
            if (r3 != r5) goto L_0x007b
            r3 = r14
            double r13 = r22.nextDouble()
            float r13 = (float) r13
            r14 = r3
            r5 = r13
            goto L_0x0065
        L_0x007b:
            r3 = r14
            r22.beginArray()
            double r13 = r22.nextDouble()
            float r13 = (float) r13
            com.airbnb.lottie.parser.moshi.JsonReader$Token r14 = r22.peek()
            if (r14 != r5) goto L_0x0090
            double r14 = r22.nextDouble()
            float r5 = (float) r14
            goto L_0x0091
        L_0x0090:
            r5 = r13
        L_0x0091:
            r22.endArray()
            r14 = r3
            r15 = r18
            r3 = r19
            r20 = r13
            r13 = r5
            r5 = r20
            goto L_0x004d
        L_0x009f:
            r19 = r3
            r3 = r14
            com.airbnb.lottie.parser.moshi.JsonReader$Token r4 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r12 = com.airbnb.lottie.parser.moshi.JsonReader.Token.NUMBER
            if (r4 != r12) goto L_0x00b2
            double r14 = r22.nextDouble()
            float r12 = (float) r14
            r14 = r3
            r4 = r12
            goto L_0x0065
        L_0x00b2:
            r22.beginArray()
            double r14 = r22.nextDouble()
            float r4 = (float) r14
            com.airbnb.lottie.parser.moshi.JsonReader$Token r14 = r22.peek()
            if (r14 != r12) goto L_0x00c6
            double r14 = r22.nextDouble()
            float r12 = (float) r14
            goto L_0x00c7
        L_0x00c6:
            r12 = r4
        L_0x00c7:
            r22.endArray()
            r14 = r3
            goto L_0x0065
        L_0x00cc:
            r19 = r3
            r3 = r14
            r18 = r15
            android.graphics.PointF r14 = new android.graphics.PointF
            r14.<init>(r4, r5)
            android.graphics.PointF r4 = new android.graphics.PointF
            r4.<init>(r12, r13)
            r22.endObject()
            r13 = r4
            r12 = r14
        L_0x00e0:
            r14 = r3
        L_0x00e1:
            r3 = r19
            goto L_0x0016
        L_0x00e5:
            r19 = r3
            r3 = r14
            r18 = r15
            android.graphics.PointF r8 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r22, r23)
            goto L_0x00e1
        L_0x00ef:
            r19 = r3
            r3 = r14
            r18 = r15
            com.airbnb.lottie.parser.moshi.JsonReader$Token r4 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r5 = com.airbnb.lottie.parser.moshi.JsonReader.Token.BEGIN_OBJECT
            if (r4 != r5) goto L_0x017b
            r22.beginObject()
            r4 = 0
            r5 = 0
            r9 = 0
            r11 = 0
        L_0x0103:
            boolean r14 = r22.hasNext()
            if (r14 == 0) goto L_0x0168
            com.airbnb.lottie.parser.moshi.JsonReader$Options r14 = INTERPOLATOR_NAMES
            int r14 = r0.selectName(r14)
            if (r14 == 0) goto L_0x0140
            r15 = 1
            if (r14 == r15) goto L_0x0118
            r22.skipValue()
            goto L_0x0103
        L_0x0118:
            com.airbnb.lottie.parser.moshi.JsonReader$Token r5 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r11 = com.airbnb.lottie.parser.moshi.JsonReader.Token.NUMBER
            if (r5 != r11) goto L_0x0127
            double r14 = r22.nextDouble()
            float r11 = (float) r14
            r5 = r11
            goto L_0x0103
        L_0x0127:
            r22.beginArray()
            double r14 = r22.nextDouble()
            float r5 = (float) r14
            com.airbnb.lottie.parser.moshi.JsonReader$Token r14 = r22.peek()
            if (r14 != r11) goto L_0x013b
            double r14 = r22.nextDouble()
            float r11 = (float) r14
            goto L_0x013c
        L_0x013b:
            r11 = r5
        L_0x013c:
            r22.endArray()
            goto L_0x0103
        L_0x0140:
            com.airbnb.lottie.parser.moshi.JsonReader$Token r4 = r22.peek()
            com.airbnb.lottie.parser.moshi.JsonReader$Token r9 = com.airbnb.lottie.parser.moshi.JsonReader.Token.NUMBER
            if (r4 != r9) goto L_0x014f
            double r14 = r22.nextDouble()
            float r9 = (float) r14
            r4 = r9
            goto L_0x0103
        L_0x014f:
            r22.beginArray()
            double r14 = r22.nextDouble()
            float r4 = (float) r14
            com.airbnb.lottie.parser.moshi.JsonReader$Token r14 = r22.peek()
            if (r14 != r9) goto L_0x0163
            double r14 = r22.nextDouble()
            float r9 = (float) r14
            goto L_0x0164
        L_0x0163:
            r9 = r4
        L_0x0164:
            r22.endArray()
            goto L_0x0103
        L_0x0168:
            android.graphics.PointF r14 = new android.graphics.PointF
            r14.<init>(r4, r5)
            android.graphics.PointF r4 = new android.graphics.PointF
            r4.<init>(r9, r11)
            r22.endObject()
            r11 = r4
            r9 = r14
            r15 = r18
            goto L_0x00e0
        L_0x017b:
            android.graphics.PointF r7 = com.airbnb.lottie.parser.JsonUtils.jsonToPoint(r22, r23)
            r14 = r3
            r15 = r18
            goto L_0x00e1
        L_0x0184:
            r19 = r3
            r3 = r14
            r18 = r15
            java.lang.Object r16 = r2.parse(r0, r1)
            goto L_0x00e1
        L_0x018f:
            r19 = r3
            r3 = r14
            r18 = r15
            java.lang.Object r10 = r2.parse(r0, r1)
            goto L_0x00e1
        L_0x019a:
            r19 = r3
            r18 = r15
            double r3 = r22.nextDouble()
            float r14 = (float) r3
            goto L_0x00e1
        L_0x01a5:
            r19 = r3
            r3 = r14
            r18 = r15
            r22.endObject()
            if (r6 == 0) goto L_0x01b5
            android.view.animation.Interpolator r0 = LINEAR_INTERPOLATOR
            r11 = r10
        L_0x01b2:
            r12 = 0
            r13 = 0
            goto L_0x01d9
        L_0x01b5:
            if (r7 == 0) goto L_0x01c0
            if (r8 == 0) goto L_0x01c0
            android.view.animation.Interpolator r0 = interpolatorFor(r7, r8)
        L_0x01bd:
            r11 = r16
            goto L_0x01b2
        L_0x01c0:
            if (r9 == 0) goto L_0x01d6
            if (r11 == 0) goto L_0x01d6
            if (r12 == 0) goto L_0x01d6
            if (r13 == 0) goto L_0x01d6
            android.view.animation.Interpolator r0 = interpolatorFor(r9, r12)
            android.view.animation.Interpolator r1 = interpolatorFor(r11, r13)
            r12 = r0
            r13 = r1
            r11 = r16
            r0 = 0
            goto L_0x01d9
        L_0x01d6:
            android.view.animation.Interpolator r0 = LINEAR_INTERPOLATOR
            goto L_0x01bd
        L_0x01d9:
            if (r12 == 0) goto L_0x01ea
            if (r13 == 0) goto L_0x01ea
            com.airbnb.lottie.value.Keyframe r0 = new com.airbnb.lottie.value.Keyframe
            r15 = 0
            r8 = r0
            r9 = r21
            r14 = r3
            r1 = r18
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            goto L_0x01f8
        L_0x01ea:
            r1 = r18
            com.airbnb.lottie.value.Keyframe r2 = new com.airbnb.lottie.value.Keyframe
            r14 = 0
            r8 = r2
            r9 = r21
            r12 = r0
            r13 = r3
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r0 = r2
        L_0x01f8:
            r0.pathCp1 = r1
            r3 = r19
            r0.pathCp2 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.KeyframeParser.parseMultiDimensionalKeyframe(com.airbnb.lottie.LottieComposition, com.airbnb.lottie.parser.moshi.JsonReader, float, com.airbnb.lottie.parser.ValueParser):com.airbnb.lottie.value.Keyframe");
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f2, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(jsonReader, f2));
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static void putInterpolator(int i3, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i3, weakReference);
        }
    }
}
