package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xml.serialize.LineSeparator;

public class TextLayer extends BaseLayer {
    private final LongSparseArray<String> codePointCache = new LongSparseArray<>();
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> colorCallbackAnimation;
    private final LottieComposition composition;
    private final Map<FontCharacter, List<ContentGroup>> contentsForCharacter = new HashMap();
    private final Paint fillPaint = new Paint(1) {
        {
            setStyle(Paint.Style.FILL);
        }
    };
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private final RectF rectF = new RectF();
    private final StringBuilder stringBuilder = new StringBuilder(2);
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> strokeColorAnimation;
    @Nullable
    private BaseKeyframeAnimation<Integer, Integer> strokeColorCallbackAnimation;
    private final Paint strokePaint = new Paint(1) {
        {
            setStyle(Paint.Style.STROKE);
        }
    };
    @Nullable
    private BaseKeyframeAnimation<Float, Float> strokeWidthAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> strokeWidthCallbackAnimation;
    private final TextKeyframeAnimation textAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> textSizeCallbackAnimation;
    private final List<TextSubLine> textSubLines = new ArrayList();
    @Nullable
    private BaseKeyframeAnimation<Float, Float> trackingAnimation;
    @Nullable
    private BaseKeyframeAnimation<Float, Float> trackingCallbackAnimation;
    @Nullable
    private BaseKeyframeAnimation<Typeface, Typeface> typefaceCallbackAnimation;

    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.airbnb.lottie.model.DocumentData$Justification[] r0 = com.airbnb.lottie.model.DocumentData.Justification.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification = r0
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.LEFT_ALIGN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.RIGHT_ALIGN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.airbnb.lottie.model.DocumentData$Justification r1 = com.airbnb.lottie.model.DocumentData.Justification.CENTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.AnonymousClass3.<clinit>():void");
        }
    }

    public static class TextSubLine {
        /* access modifiers changed from: private */
        public String text;
        /* access modifiers changed from: private */
        public float width;

        private TextSubLine() {
            this.text = "";
            this.width = 0.0f;
        }

        public void set(String str, float f2) {
            this.text = str;
            this.width = f2;
        }
    }

    public TextLayer(LottieDrawable lottieDrawable2, Layer layer) {
        super(lottieDrawable2, layer);
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableColorValue animatableColorValue;
        AnimatableColorValue animatableColorValue2;
        this.lottieDrawable = lottieDrawable2;
        this.composition = layer.getComposition();
        TextKeyframeAnimation createAnimation = layer.getText().createAnimation();
        this.textAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        addAnimation(createAnimation);
        AnimatableTextProperties textProperties = layer.getTextProperties();
        if (!(textProperties == null || (animatableColorValue2 = textProperties.color) == null)) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation2 = animatableColorValue2.createAnimation();
            this.colorAnimation = createAnimation2;
            createAnimation2.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (!(textProperties == null || (animatableColorValue = textProperties.stroke) == null)) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation3 = animatableColorValue.createAnimation();
            this.strokeColorAnimation = createAnimation3;
            createAnimation3.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (!(textProperties == null || (animatableFloatValue2 = textProperties.strokeWidth) == null)) {
            BaseKeyframeAnimation<Float, Float> createAnimation4 = animatableFloatValue2.createAnimation();
            this.strokeWidthAnimation = createAnimation4;
            createAnimation4.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (textProperties != null && (animatableFloatValue = textProperties.tracking) != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation5 = animatableFloatValue.createAnimation();
            this.trackingAnimation = createAnimation5;
            createAnimation5.addUpdateListener(this);
            addAnimation(this.trackingAnimation);
        }
    }

    private String codePointToString(String str, int i3) {
        int codePointAt = str.codePointAt(i3);
        int charCount = Character.charCount(codePointAt) + i3;
        while (charCount < str.length()) {
            int codePointAt2 = str.codePointAt(charCount);
            if (!isModifier(codePointAt2)) {
                break;
            }
            charCount += Character.charCount(codePointAt2);
            codePointAt = (codePointAt * 31) + codePointAt2;
        }
        long j2 = (long) codePointAt;
        if (this.codePointCache.containsKey(j2)) {
            return this.codePointCache.get(j2);
        }
        this.stringBuilder.setLength(0);
        while (i3 < charCount) {
            int codePointAt3 = str.codePointAt(i3);
            this.stringBuilder.appendCodePoint(codePointAt3);
            i3 += Character.charCount(codePointAt3);
        }
        String sb = this.stringBuilder.toString();
        this.codePointCache.put(j2, sb);
        return sb;
    }

    private void configurePaint(DocumentData documentData, int i3) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            this.fillPaint.setColor(baseKeyframeAnimation.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.colorAnimation;
            if (baseKeyframeAnimation2 != null) {
                this.fillPaint.setColor(baseKeyframeAnimation2.getValue().intValue());
            } else {
                this.fillPaint.setColor(documentData.color);
            }
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation3 = this.strokeColorCallbackAnimation;
        if (baseKeyframeAnimation3 != null) {
            this.strokePaint.setColor(baseKeyframeAnimation3.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation4 = this.strokeColorAnimation;
            if (baseKeyframeAnimation4 != null) {
                this.strokePaint.setColor(baseKeyframeAnimation4.getValue().intValue());
            } else {
                this.strokePaint.setColor(documentData.strokeColor);
            }
        }
        int intValue = ((((this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue()) * 255) / 100) * i3) / 255;
        this.fillPaint.setAlpha(intValue);
        this.strokePaint.setAlpha(intValue);
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.strokeWidthCallbackAnimation;
        if (baseKeyframeAnimation5 != null) {
            this.strokePaint.setStrokeWidth(baseKeyframeAnimation5.getValue().floatValue());
            return;
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation6 = this.strokeWidthAnimation;
        if (baseKeyframeAnimation6 != null) {
            this.strokePaint.setStrokeWidth(baseKeyframeAnimation6.getValue().floatValue());
        } else {
            this.strokePaint.setStrokeWidth(Utils.dpScale() * documentData.strokeWidth);
        }
    }

    private void drawCharacter(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
            }
        }
    }

    private void drawCharacterAsGlyph(FontCharacter fontCharacter, float f2, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> contentsForCharacter2 = getContentsForCharacter(fontCharacter);
        for (int i3 = 0; i3 < contentsForCharacter2.size(); i3++) {
            Path path = contentsForCharacter2.get(i3).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.reset();
            this.matrix.preTranslate(0.0f, Utils.dpScale() * (-documentData.baselineShift));
            this.matrix.preScale(f2, f2);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawCharacterFromFont(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            drawCharacter(str, this.fillPaint, canvas);
            drawCharacter(str, this.strokePaint, canvas);
            return;
        }
        drawCharacter(str, this.strokePaint, canvas);
        drawCharacter(str, this.fillPaint, canvas);
    }

    private void drawFontTextLine(String str, DocumentData documentData, Canvas canvas, float f2) {
        int i3 = 0;
        while (i3 < str.length()) {
            String codePointToString = codePointToString(str, i3);
            i3 += codePointToString.length();
            drawCharacterFromFont(codePointToString, documentData, canvas);
            canvas.translate(this.fillPaint.measureText(codePointToString) + f2, 0.0f);
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawPath(path, paint);
            }
        }
    }

    private void drawGlyphTextLine(String str, DocumentData documentData, Font font, Canvas canvas, float f2, float f3, float f4) {
        for (int i3 = 0; i3 < str.length(); i3++) {
            FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(str.charAt(i3), font.getFamily(), font.getStyle()));
            if (fontCharacter != null) {
                drawCharacterAsGlyph(fontCharacter, f3, documentData, canvas);
                canvas.translate((Utils.dpScale() * ((float) fontCharacter.getWidth()) * f3) + f4, 0.0f);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawTextWithFont(com.airbnb.lottie.model.DocumentData r19, com.airbnb.lottie.model.Font r20, android.graphics.Canvas r21) {
        /*
            r18 = this;
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            android.graphics.Typeface r0 = r7.getTypeface(r9)
            if (r0 != 0) goto L_0x000f
            return
        L_0x000f:
            java.lang.String r1 = r8.text
            com.airbnb.lottie.LottieDrawable r2 = r7.lottieDrawable
            com.airbnb.lottie.TextDelegate r2 = r2.getTextDelegate()
            if (r2 == 0) goto L_0x0021
            java.lang.String r3 = r18.getName()
            java.lang.String r1 = r2.getTextInternal(r3, r1)
        L_0x0021:
            android.graphics.Paint r2 = r7.fillPaint
            r2.setTypeface(r0)
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r0 = r7.textSizeCallbackAnimation
            if (r0 == 0) goto L_0x0035
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L_0x0037
        L_0x0035:
            float r0 = r8.size
        L_0x0037:
            android.graphics.Paint r2 = r7.fillPaint
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r0
            r2.setTextSize(r3)
            android.graphics.Paint r2 = r7.strokePaint
            android.graphics.Paint r3 = r7.fillPaint
            android.graphics.Typeface r3 = r3.getTypeface()
            r2.setTypeface(r3)
            android.graphics.Paint r2 = r7.strokePaint
            android.graphics.Paint r3 = r7.fillPaint
            float r3 = r3.getTextSize()
            r2.setTextSize(r3)
            int r2 = r8.tracking
            float r2 = (float) r2
            r3 = 1092616192(0x41200000, float:10.0)
            float r2 = r2 / r3
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.trackingCallbackAnimation
            if (r3 == 0) goto L_0x006d
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
        L_0x006b:
            float r2 = r2 + r3
            goto L_0x007c
        L_0x006d:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r3 = r7.trackingAnimation
            if (r3 == 0) goto L_0x007c
            java.lang.Object r3 = r3.getValue()
            java.lang.Float r3 = (java.lang.Float) r3
            float r3 = r3.floatValue()
            goto L_0x006b
        L_0x007c:
            float r3 = com.airbnb.lottie.utils.Utils.dpScale()
            float r3 = r3 * r2
            float r3 = r3 * r0
            r0 = 1120403456(0x42c80000, float:100.0)
            float r11 = r3 / r0
            java.util.List r12 = r7.getTextLines(r1)
            int r13 = r12.size()
            r14 = 0
            r0 = -1
            r15 = r0
            r6 = r14
        L_0x0092:
            if (r6 >= r13) goto L_0x00de
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r8.boxSize
            if (r0 != 0) goto L_0x00a2
            r0 = 0
        L_0x00a0:
            r2 = r0
            goto L_0x00a5
        L_0x00a2:
            float r0 = r0.x
            goto L_0x00a0
        L_0x00a5:
            r4 = 0
            r16 = 0
            r0 = r18
            r3 = r20
            r5 = r11
            r17 = r6
            r6 = r16
            java.util.List r0 = r0.splitGlyphTextIntoLines(r1, r2, r3, r4, r5, r6)
            r1 = r14
        L_0x00b6:
            int r2 = r0.size()
            if (r1 >= r2) goto L_0x00db
            java.lang.Object r2 = r0.get(r1)
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r2 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r2
            int r15 = r15 + 1
            r21.save()
            float r3 = r2.width
            r7.offsetCanvas(r10, r8, r15, r3)
            java.lang.String r2 = r2.text
            r7.drawFontTextLine(r2, r8, r10, r11)
            r21.restore()
            int r1 = r1 + 1
            goto L_0x00b6
        L_0x00db:
            int r6 = r17 + 1
            goto L_0x0092
        L_0x00de:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawTextWithFont(com.airbnb.lottie.model.DocumentData, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawTextWithGlyphs(com.airbnb.lottie.model.DocumentData r21, android.graphics.Matrix r22, com.airbnb.lottie.model.Font r23, android.graphics.Canvas r24) {
        /*
            r20 = this;
            r8 = r20
            r9 = r21
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r0 = r8.textSizeCallbackAnimation
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.getValue()
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            goto L_0x0015
        L_0x0013:
            float r0 = r9.size
        L_0x0015:
            r1 = 1120403456(0x42c80000, float:100.0)
            float r10 = r0 / r1
            float r11 = com.airbnb.lottie.utils.Utils.getScale(r22)
            java.lang.String r0 = r9.text
            java.util.List r12 = r8.getTextLines(r0)
            int r13 = r12.size()
            int r0 = r9.tracking
            float r0 = (float) r0
            r1 = 1092616192(0x41200000, float:10.0)
            float r0 = r0 / r1
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r1 = r8.trackingCallbackAnimation
            if (r1 == 0) goto L_0x003e
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
        L_0x003b:
            float r0 = r0 + r1
        L_0x003c:
            r14 = r0
            goto L_0x004d
        L_0x003e:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<java.lang.Float, java.lang.Float> r1 = r8.trackingAnimation
            if (r1 == 0) goto L_0x003c
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            goto L_0x003b
        L_0x004d:
            r15 = 0
            r0 = -1
            r7 = r0
            r6 = r15
        L_0x0051:
            if (r6 >= r13) goto L_0x00b2
            java.lang.Object r0 = r12.get(r6)
            r1 = r0
            java.lang.String r1 = (java.lang.String) r1
            android.graphics.PointF r0 = r9.boxSize
            if (r0 != 0) goto L_0x0061
            r0 = 0
        L_0x005f:
            r2 = r0
            goto L_0x0064
        L_0x0061:
            float r0 = r0.x
            goto L_0x005f
        L_0x0064:
            r16 = 1
            r0 = r20
            r3 = r23
            r4 = r10
            r5 = r14
            r17 = r6
            r6 = r16
            java.util.List r6 = r0.splitGlyphTextIntoLines(r1, r2, r3, r4, r5, r6)
            r5 = r15
        L_0x0075:
            int r0 = r6.size()
            if (r5 >= r0) goto L_0x00af
            java.lang.Object r0 = r6.get(r5)
            com.airbnb.lottie.model.layer.TextLayer$TextSubLine r0 = (com.airbnb.lottie.model.layer.TextLayer.TextSubLine) r0
            int r7 = r7 + 1
            r24.save()
            float r1 = r0.width
            r4 = r24
            r8.offsetCanvas(r4, r9, r7, r1)
            java.lang.String r1 = r0.text
            r0 = r20
            r2 = r21
            r3 = r23
            r16 = r5
            r5 = r11
            r18 = r6
            r6 = r10
            r19 = r7
            r7 = r14
            r0.drawGlyphTextLine(r1, r2, r3, r4, r5, r6, r7)
            r24.restore()
            int r5 = r16 + 1
            r6 = r18
            r7 = r19
            goto L_0x0075
        L_0x00af:
            int r6 = r17 + 1
            goto L_0x0051
        L_0x00b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.layer.TextLayer.drawTextWithGlyphs(com.airbnb.lottie.model.DocumentData, android.graphics.Matrix, com.airbnb.lottie.model.Font, android.graphics.Canvas):void");
    }

    private TextSubLine ensureEnoughSubLines(int i3) {
        for (int size = this.textSubLines.size(); size < i3; size++) {
            this.textSubLines.add(new TextSubLine());
        }
        return this.textSubLines.get(i3 - 1);
    }

    private List<ContentGroup> getContentsForCharacter(FontCharacter fontCharacter) {
        if (this.contentsForCharacter.containsKey(fontCharacter)) {
            return this.contentsForCharacter.get(fontCharacter);
        }
        List<ShapeGroup> shapes = fontCharacter.getShapes();
        int size = shapes.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.add(new ContentGroup(this.lottieDrawable, this, shapes.get(i3), this.composition));
        }
        this.contentsForCharacter.put(fontCharacter, arrayList);
        return arrayList;
    }

    private List<String> getTextLines(String str) {
        return Arrays.asList(str.replaceAll(LineSeparator.Windows, "\r").replaceAll("\u0003", "\r").replaceAll("\n", "\r").split("\r"));
    }

    @Nullable
    private Typeface getTypeface(Font font) {
        Typeface value;
        BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation = this.typefaceCallbackAnimation;
        if (baseKeyframeAnimation != null && (value = baseKeyframeAnimation.getValue()) != null) {
            return value;
        }
        Typeface typeface = this.lottieDrawable.getTypeface(font);
        return typeface != null ? typeface : font.getTypeface();
    }

    private boolean isModifier(int i3) {
        return Character.getType(i3) == 16 || Character.getType(i3) == 27 || Character.getType(i3) == 6 || Character.getType(i3) == 28 || Character.getType(i3) == 8 || Character.getType(i3) == 19;
    }

    private void offsetCanvas(Canvas canvas, DocumentData documentData, int i3, float f2) {
        PointF pointF = documentData.boxPosition;
        PointF pointF2 = documentData.boxSize;
        float dpScale = Utils.dpScale();
        float f3 = 0.0f;
        float f4 = (((float) i3) * documentData.lineHeight * dpScale) + (pointF == null ? 0.0f : (documentData.lineHeight * dpScale) + pointF.y);
        float f5 = pointF == null ? 0.0f : pointF.x;
        if (pointF2 != null) {
            f3 = pointF2.x;
        }
        int i4 = AnonymousClass3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[documentData.justification.ordinal()];
        if (i4 == 1) {
            canvas.translate(f5, f4);
        } else if (i4 == 2) {
            canvas.translate((f5 + f3) - f2, f4);
        } else if (i4 == 3) {
            canvas.translate(((f3 / 2.0f) + f5) - (f2 / 2.0f), f4);
        }
    }

    private List<TextSubLine> splitGlyphTextIntoLines(String str, float f2, Font font, float f3, float f4, boolean z2) {
        float f5;
        String str2 = str;
        int i3 = 0;
        int i4 = 0;
        boolean z3 = false;
        int i5 = 0;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        for (int i6 = 0; i6 < str.length(); i6++) {
            char charAt = str2.charAt(i6);
            if (z2) {
                FontCharacter fontCharacter = this.composition.getCharacters().get(FontCharacter.hashFor(charAt, font.getFamily(), font.getStyle()));
                if (fontCharacter == null) {
                } else {
                    f5 = (Utils.dpScale() * ((float) fontCharacter.getWidth()) * f3) + f4;
                }
            } else {
                f5 = this.fillPaint.measureText(str2.substring(i6, i6 + 1)) + f4;
            }
            if (charAt == ' ') {
                z3 = true;
                f8 = f5;
            } else if (z3) {
                z3 = false;
                i5 = i6;
                f7 = f5;
            } else {
                f7 += f5;
            }
            f6 += f5;
            if (f2 > 0.0f && f6 >= f2 && charAt != ' ') {
                i3++;
                TextSubLine ensureEnoughSubLines = ensureEnoughSubLines(i3);
                if (i5 == i4) {
                    String substring = str2.substring(i4, i6);
                    String trim = substring.trim();
                    ensureEnoughSubLines.set(trim, (f6 - f5) - (((float) (trim.length() - substring.length())) * f8));
                    i4 = i6;
                    i5 = i4;
                    f6 = f5;
                    f7 = f6;
                } else {
                    String substring2 = str2.substring(i4, i5 - 1);
                    String trim2 = substring2.trim();
                    ensureEnoughSubLines.set(trim2, ((f6 - f7) - (((float) (substring2.length() - trim2.length())) * f8)) - f8);
                    f6 = f7;
                    i4 = i5;
                }
            }
        }
        if (f6 > 0.0f) {
            i3++;
            ensureEnoughSubLines(i3).set(str2.substring(i4), f6);
        }
        return this.textSubLines.subList(0, i3);
    }

    public <T> void addValueCallback(T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        super.addValueCallback(t2, lottieValueCallback);
        if (t2 == LottieProperty.COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
            if (baseKeyframeAnimation != null) {
                removeAnimation(baseKeyframeAnimation);
            }
            if (lottieValueCallback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
        } else if (t2 == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.strokeColorCallbackAnimation;
            if (baseKeyframeAnimation2 != null) {
                removeAnimation(baseKeyframeAnimation2);
            }
            if (lottieValueCallback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeColorCallbackAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
        } else if (t2 == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.strokeWidthCallbackAnimation;
            if (baseKeyframeAnimation3 != null) {
                removeAnimation(baseKeyframeAnimation3);
            }
            if (lottieValueCallback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.strokeWidthCallbackAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
        } else if (t2 == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation4 = this.trackingCallbackAnimation;
            if (baseKeyframeAnimation4 != null) {
                removeAnimation(baseKeyframeAnimation4);
            }
            if (lottieValueCallback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.trackingCallbackAnimation = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
        } else if (t2 == LottieProperty.TEXT_SIZE) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.textSizeCallbackAnimation;
            if (baseKeyframeAnimation5 != null) {
                removeAnimation(baseKeyframeAnimation5);
            }
            if (lottieValueCallback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.textSizeCallbackAnimation = valueCallbackKeyframeAnimation5;
            valueCallbackKeyframeAnimation5.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
        } else if (t2 == LottieProperty.TYPEFACE) {
            BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation6 = this.typefaceCallbackAnimation;
            if (baseKeyframeAnimation6 != null) {
                removeAnimation(baseKeyframeAnimation6);
            }
            if (lottieValueCallback == null) {
                this.typefaceCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(lottieValueCallback);
            this.typefaceCallbackAnimation = valueCallbackKeyframeAnimation6;
            valueCallbackKeyframeAnimation6.addUpdateListener(this);
            addAnimation(this.typefaceCallbackAnimation);
        } else if (t2 == LottieProperty.TEXT) {
            this.textAnimation.setStringValueCallback(lottieValueCallback);
        }
    }

    public void drawLayer(Canvas canvas, Matrix matrix2, int i3) {
        DocumentData documentData = (DocumentData) this.textAnimation.getValue();
        Font font = this.composition.getFonts().get(documentData.fontName);
        if (font != null) {
            canvas.save();
            canvas.concat(matrix2);
            configurePaint(documentData, i3);
            if (this.lottieDrawable.useTextGlyphs()) {
                drawTextWithGlyphs(documentData, matrix2, font, canvas);
            } else {
                drawTextWithFont(documentData, font, canvas);
            }
            canvas.restore();
        }
    }

    public void getBounds(RectF rectF2, Matrix matrix2, boolean z2) {
        super.getBounds(rectF2, matrix2, z2);
        rectF2.set(0.0f, 0.0f, (float) this.composition.getBounds().width(), (float) this.composition.getBounds().height());
    }
}
