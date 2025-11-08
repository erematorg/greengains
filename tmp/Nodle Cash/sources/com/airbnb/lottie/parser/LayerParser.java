package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.LBlendMode;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayerParser {
    private static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("ty", "nm");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", TtmlNode.TAG_TT, "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd", "ao", "bm");
    private static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");

    /* renamed from: com.airbnb.lottie.parser.LayerParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.airbnb.lottie.model.layer.Layer$MatteType[] r0 = com.airbnb.lottie.model.layer.Layer.MatteType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType = r0
                com.airbnb.lottie.model.layer.Layer$MatteType r1 = com.airbnb.lottie.model.layer.Layer.MatteType.LUMA     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.layer.Layer$MatteType r1 = com.airbnb.lottie.model.layer.Layer.MatteType.LUMA_INVERTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LayerParser.AnonymousClass1.<clinit>():void");
        }
    }

    private LayerParser() {
    }

    public static Layer parse(LottieComposition lottieComposition) {
        Rect bounds = lottieComposition.getBounds();
        List emptyList = Collections.emptyList();
        Layer.LayerType layerType = Layer.LayerType.PRE_COMP;
        List emptyList2 = Collections.emptyList();
        AnimatableTransform animatableTransform = r4;
        AnimatableTransform animatableTransform2 = new AnimatableTransform();
        return new Layer(emptyList, lottieComposition, "__container", -1, layerType, -1, (String) null, emptyList2, animatableTransform, 0, 0, 0, 0.0f, 0.0f, (float) bounds.width(), (float) bounds.height(), (AnimatableTextFrame) null, (AnimatableTextProperties) null, Collections.emptyList(), Layer.MatteType.NONE, (AnimatableFloatValue) null, false, (BlurEffect) null, (DropShadowEffect) null, LBlendMode.NORMAL);
    }

    public static Layer parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        String str;
        ArrayList arrayList;
        long j2;
        boolean z2;
        AnimatableTransform animatableTransform;
        ArrayList arrayList2;
        JsonReader jsonReader2 = jsonReader;
        LottieComposition lottieComposition2 = lottieComposition;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        LBlendMode lBlendMode = LBlendMode.NORMAL;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        jsonReader.beginObject();
        Float valueOf = Float.valueOf(0.0f);
        Float valueOf2 = Float.valueOf(1.0f);
        String str2 = null;
        Layer.MatteType matteType2 = matteType;
        LBlendMode lBlendMode2 = lBlendMode;
        float f2 = 1.0f;
        AnimatableTransform animatableTransform2 = null;
        Layer.LayerType layerType = null;
        String str3 = null;
        AnimatableTextFrame animatableTextFrame = null;
        AnimatableTextProperties animatableTextProperties = null;
        AnimatableFloatValue animatableFloatValue = null;
        BlurEffect blurEffect = null;
        DropShadowEffect dropShadowEffect = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        long j3 = -1;
        float f7 = 0.0f;
        long j4 = 0;
        String str4 = "UNSET";
        boolean z4 = false;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(NAMES)) {
                case 0:
                    str4 = jsonReader.nextString();
                    break;
                case 1:
                    j4 = (long) jsonReader.nextInt();
                    break;
                case 2:
                    str3 = jsonReader.nextString();
                    break;
                case 3:
                    int nextInt = jsonReader.nextInt();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (nextInt < layerType.ordinal()) {
                        layerType = Layer.LayerType.values()[nextInt];
                        break;
                    }
                    break;
                case 4:
                    j3 = (long) jsonReader.nextInt();
                    break;
                case 5:
                    i3 = (int) (Utils.dpScale() * ((float) jsonReader.nextInt()));
                    break;
                case 6:
                    i4 = (int) (Utils.dpScale() * ((float) jsonReader.nextInt()));
                    break;
                case 7:
                    i5 = Color.parseColor(jsonReader.nextString());
                    break;
                case 8:
                    animatableTransform2 = AnimatableTransformParser.parse(jsonReader, lottieComposition);
                    break;
                case 9:
                    int nextInt2 = jsonReader.nextInt();
                    if (nextInt2 < Layer.MatteType.values().length) {
                        matteType2 = Layer.MatteType.values()[nextInt2];
                        int i6 = AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[matteType2.ordinal()];
                        if (i6 == 1) {
                            lottieComposition2.addWarning("Unsupported matte type: Luma");
                        } else if (i6 == 2) {
                            lottieComposition2.addWarning("Unsupported matte type: Luma Inverted");
                        }
                        lottieComposition2.incrementMatteOrMaskCount(1);
                        break;
                    } else {
                        lottieComposition2.addWarning("Unsupported matte type: " + nextInt2);
                        break;
                    }
                case 10:
                    ArrayList arrayList5 = arrayList3;
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        arrayList5.add(MaskParser.parse(jsonReader, lottieComposition));
                    }
                    arrayList3 = arrayList5;
                    lottieComposition2.incrementMatteOrMaskCount(arrayList3.size());
                    jsonReader.endArray();
                    break;
                case 11:
                    arrayList2 = arrayList3;
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ContentModel parse = ContentModelParser.parse(jsonReader, lottieComposition);
                        if (parse != null) {
                            arrayList4.add(parse);
                        }
                    }
                    jsonReader.endArray();
                    break;
                case 12:
                    arrayList2 = arrayList3;
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        int selectName = jsonReader2.selectName(TEXT_NAMES);
                        if (selectName == 0) {
                            animatableTextFrame = AnimatableValueParser.parseDocumentData(jsonReader, lottieComposition);
                        } else if (selectName != 1) {
                            jsonReader.skipName();
                            jsonReader.skipValue();
                        } else {
                            jsonReader.beginArray();
                            if (jsonReader.hasNext()) {
                                animatableTextProperties = AnimatableTextPropertiesParser.parse(jsonReader, lottieComposition);
                            }
                            while (jsonReader.hasNext()) {
                                jsonReader.skipValue();
                            }
                            jsonReader.endArray();
                        }
                    }
                    jsonReader.endObject();
                    break;
                case 13:
                    arrayList2 = arrayList3;
                    jsonReader.beginArray();
                    ArrayList arrayList6 = new ArrayList();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            int selectName2 = jsonReader2.selectName(EFFECTS_NAMES);
                            if (selectName2 == 0) {
                                int nextInt3 = jsonReader.nextInt();
                                if (nextInt3 == 29) {
                                    blurEffect = BlurEffectParser.parse(jsonReader, lottieComposition);
                                } else if (nextInt3 == 25) {
                                    dropShadowEffect = new DropShadowEffectParser().parse(jsonReader2, lottieComposition2);
                                }
                            } else if (selectName2 != 1) {
                                jsonReader.skipName();
                                jsonReader.skipValue();
                            } else {
                                arrayList6.add(jsonReader.nextString());
                            }
                        }
                        jsonReader.endObject();
                    }
                    jsonReader.endArray();
                    lottieComposition2.addWarning("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList6);
                    break;
                case 14:
                    ArrayList arrayList7 = arrayList3;
                    f2 = (float) jsonReader.nextDouble();
                    break;
                case 15:
                    ArrayList arrayList8 = arrayList3;
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 16:
                    arrayList2 = arrayList3;
                    f4 = (float) (jsonReader.nextDouble() * ((double) Utils.dpScale()));
                    break;
                case 17:
                    arrayList2 = arrayList3;
                    f5 = (float) (jsonReader.nextDouble() * ((double) Utils.dpScale()));
                    break;
                case 18:
                    f7 = (float) jsonReader.nextDouble();
                    continue;
                case 19:
                    f6 = (float) jsonReader.nextDouble();
                    continue;
                case 20:
                    animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader2, lottieComposition2, false);
                    continue;
                case 21:
                    str2 = jsonReader.nextString();
                    continue;
                case 22:
                    z3 = jsonReader.nextBoolean();
                    continue;
                case 23:
                    if (jsonReader.nextInt() != 1) {
                        z4 = false;
                        break;
                    } else {
                        z4 = true;
                        continue;
                    }
                case 24:
                    int nextInt4 = jsonReader.nextInt();
                    if (nextInt4 < LBlendMode.values().length) {
                        lBlendMode2 = LBlendMode.values()[nextInt4];
                        break;
                    } else {
                        lottieComposition2.addWarning("Unsupported Blend Mode: " + nextInt4);
                        lBlendMode2 = LBlendMode.NORMAL;
                        continue;
                    }
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
            }
            arrayList3 = arrayList2;
        }
        jsonReader.endObject();
        ArrayList arrayList9 = new ArrayList();
        if (f7 > 0.0f) {
            arrayList = arrayList3;
            z2 = z4;
            str = str2;
            j2 = j4;
            Keyframe keyframe = r0;
            Keyframe keyframe2 = new Keyframe(lottieComposition, valueOf, valueOf, (Interpolator) null, 0.0f, Float.valueOf(f7));
            arrayList9.add(keyframe);
        } else {
            str = str2;
            arrayList = arrayList3;
            j2 = j4;
            z2 = z4;
        }
        if (f6 <= 0.0f) {
            f6 = lottieComposition.getEndFrame();
        }
        LottieComposition lottieComposition3 = lottieComposition;
        arrayList9.add(new Keyframe(lottieComposition3, valueOf2, valueOf2, (Interpolator) null, f7, Float.valueOf(f6)));
        arrayList9.add(new Keyframe(lottieComposition3, valueOf, valueOf, (Interpolator) null, f6, Float.valueOf(Float.MAX_VALUE)));
        if (str4.endsWith(".ai") || "ai".equals(str)) {
            lottieComposition2.addWarning("Convert your Illustrator layers to shape layers.");
        }
        if (z2) {
            if (animatableTransform2 == null) {
                animatableTransform2 = new AnimatableTransform();
            }
            AnimatableTransform animatableTransform3 = animatableTransform2;
            animatableTransform3.setAutoOrient(z2);
            animatableTransform = animatableTransform3;
        } else {
            animatableTransform = animatableTransform2;
        }
        return new Layer(arrayList4, lottieComposition, str4, j2, layerType, j3, str3, arrayList, animatableTransform, i3, i4, i5, f2, f3, f4, f5, animatableTextFrame, animatableTextProperties, arrayList9, matteType2, animatableFloatValue, z3, blurEffect, dropShadowEffect, lBlendMode2);
    }
}
