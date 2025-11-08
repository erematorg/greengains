package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;

class ContentModelParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("ty", "d");

    private ContentModelParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d0, code lost:
        if (r3.equals("fl") == false) goto L_0x0034;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.model.content.ContentModel parse(com.airbnb.lottie.parser.moshi.JsonReader r7, com.airbnb.lottie.LottieComposition r8) throws java.io.IOException {
        /*
            r0 = 1
            r7.beginObject()
            r1 = 2
            r2 = r1
        L_0x0006:
            boolean r3 = r7.hasNext()
            r4 = 0
            if (r3 == 0) goto L_0x0028
            com.airbnb.lottie.parser.moshi.JsonReader$Options r3 = NAMES
            int r3 = r7.selectName(r3)
            if (r3 == 0) goto L_0x0023
            if (r3 == r0) goto L_0x001e
            r7.skipName()
            r7.skipValue()
            goto L_0x0006
        L_0x001e:
            int r2 = r7.nextInt()
            goto L_0x0006
        L_0x0023:
            java.lang.String r3 = r7.nextString()
            goto L_0x0029
        L_0x0028:
            r3 = r4
        L_0x0029:
            if (r3 != 0) goto L_0x002c
            return r4
        L_0x002c:
            r5 = -1
            int r6 = r3.hashCode()
            switch(r6) {
                case 3239: goto L_0x00d4;
                case 3270: goto L_0x00ca;
                case 3295: goto L_0x00be;
                case 3307: goto L_0x00b2;
                case 3308: goto L_0x00a7;
                case 3488: goto L_0x009c;
                case 3633: goto L_0x0091;
                case 3634: goto L_0x0086;
                case 3646: goto L_0x007a;
                case 3669: goto L_0x006d;
                case 3679: goto L_0x0060;
                case 3681: goto L_0x0053;
                case 3705: goto L_0x0045;
                case 3710: goto L_0x0037;
                default: goto L_0x0034;
            }
        L_0x0034:
            r0 = r5
            goto L_0x00df
        L_0x0037:
            java.lang.String r0 = "tr"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0041
            goto L_0x0034
        L_0x0041:
            r0 = 13
            goto L_0x00df
        L_0x0045:
            java.lang.String r0 = "tm"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x004f
            goto L_0x0034
        L_0x004f:
            r0 = 12
            goto L_0x00df
        L_0x0053:
            java.lang.String r0 = "st"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x005c
            goto L_0x0034
        L_0x005c:
            r0 = 11
            goto L_0x00df
        L_0x0060:
            java.lang.String r0 = "sr"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0069
            goto L_0x0034
        L_0x0069:
            r0 = 10
            goto L_0x00df
        L_0x006d:
            java.lang.String r0 = "sh"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0076
            goto L_0x0034
        L_0x0076:
            r0 = 9
            goto L_0x00df
        L_0x007a:
            java.lang.String r0 = "rp"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x0083
            goto L_0x0034
        L_0x0083:
            r0 = 8
            goto L_0x00df
        L_0x0086:
            java.lang.String r0 = "rd"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x008f
            goto L_0x0034
        L_0x008f:
            r0 = 7
            goto L_0x00df
        L_0x0091:
            java.lang.String r0 = "rc"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x009a
            goto L_0x0034
        L_0x009a:
            r0 = 6
            goto L_0x00df
        L_0x009c:
            java.lang.String r0 = "mm"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x00a5
            goto L_0x0034
        L_0x00a5:
            r0 = 5
            goto L_0x00df
        L_0x00a7:
            java.lang.String r0 = "gs"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x00b0
            goto L_0x0034
        L_0x00b0:
            r0 = 4
            goto L_0x00df
        L_0x00b2:
            java.lang.String r0 = "gr"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x00bc
            goto L_0x0034
        L_0x00bc:
            r0 = 3
            goto L_0x00df
        L_0x00be:
            java.lang.String r0 = "gf"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x00c8
            goto L_0x0034
        L_0x00c8:
            r0 = r1
            goto L_0x00df
        L_0x00ca:
            java.lang.String r1 = "fl"
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L_0x00df
            goto L_0x0034
        L_0x00d4:
            java.lang.String r0 = "el"
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x00de
            goto L_0x0034
        L_0x00de:
            r0 = 0
        L_0x00df:
            switch(r0) {
                case 0: goto L_0x0132;
                case 1: goto L_0x012d;
                case 2: goto L_0x0128;
                case 3: goto L_0x0123;
                case 4: goto L_0x011e;
                case 5: goto L_0x0114;
                case 6: goto L_0x010f;
                case 7: goto L_0x010a;
                case 8: goto L_0x0105;
                case 9: goto L_0x0100;
                case 10: goto L_0x00fb;
                case 11: goto L_0x00f6;
                case 12: goto L_0x00f1;
                case 13: goto L_0x00ec;
                default: goto L_0x00e2;
            }
        L_0x00e2:
            java.lang.String r8 = "Unknown shape type "
            java.lang.String r8 = r8.concat(r3)
            com.airbnb.lottie.utils.Logger.warning(r8)
            goto L_0x0136
        L_0x00ec:
            com.airbnb.lottie.model.animatable.AnimatableTransform r4 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r7, r8)
            goto L_0x0136
        L_0x00f1:
            com.airbnb.lottie.model.content.ShapeTrimPath r4 = com.airbnb.lottie.parser.ShapeTrimPathParser.parse(r7, r8)
            goto L_0x0136
        L_0x00f6:
            com.airbnb.lottie.model.content.ShapeStroke r4 = com.airbnb.lottie.parser.ShapeStrokeParser.parse(r7, r8)
            goto L_0x0136
        L_0x00fb:
            com.airbnb.lottie.model.content.PolystarShape r4 = com.airbnb.lottie.parser.PolystarShapeParser.parse(r7, r8, r2)
            goto L_0x0136
        L_0x0100:
            com.airbnb.lottie.model.content.ShapePath r4 = com.airbnb.lottie.parser.ShapePathParser.parse(r7, r8)
            goto L_0x0136
        L_0x0105:
            com.airbnb.lottie.model.content.Repeater r4 = com.airbnb.lottie.parser.RepeaterParser.parse(r7, r8)
            goto L_0x0136
        L_0x010a:
            com.airbnb.lottie.model.content.RoundedCorners r4 = com.airbnb.lottie.parser.RoundedCornersParser.parse(r7, r8)
            goto L_0x0136
        L_0x010f:
            com.airbnb.lottie.model.content.RectangleShape r4 = com.airbnb.lottie.parser.RectangleShapeParser.parse(r7, r8)
            goto L_0x0136
        L_0x0114:
            com.airbnb.lottie.model.content.MergePaths r4 = com.airbnb.lottie.parser.MergePathsParser.parse(r7)
            java.lang.String r0 = "Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove()."
            r8.addWarning(r0)
            goto L_0x0136
        L_0x011e:
            com.airbnb.lottie.model.content.GradientStroke r4 = com.airbnb.lottie.parser.GradientStrokeParser.parse(r7, r8)
            goto L_0x0136
        L_0x0123:
            com.airbnb.lottie.model.content.ShapeGroup r4 = com.airbnb.lottie.parser.ShapeGroupParser.parse(r7, r8)
            goto L_0x0136
        L_0x0128:
            com.airbnb.lottie.model.content.GradientFill r4 = com.airbnb.lottie.parser.GradientFillParser.parse(r7, r8)
            goto L_0x0136
        L_0x012d:
            com.airbnb.lottie.model.content.ShapeFill r4 = com.airbnb.lottie.parser.ShapeFillParser.parse(r7, r8)
            goto L_0x0136
        L_0x0132:
            com.airbnb.lottie.model.content.CircleShape r4 = com.airbnb.lottie.parser.CircleShapeParser.parse(r7, r8, r2)
        L_0x0136:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0140
            r7.skipValue()
            goto L_0x0136
        L_0x0140:
            r7.endObject()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ContentModelParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ContentModel");
    }
}
