package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.content.CircleShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

class CircleShapeParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", TtmlNode.TAG_P, "s", "hd", "d");

    private CircleShapeParser() {
    }

    public static CircleShape parse(JsonReader jsonReader, LottieComposition lottieComposition, int i3) throws IOException {
        boolean z2 = i3 == 3;
        boolean z3 = false;
        String str = null;
        AnimatableValue<PointF, PointF> animatableValue = null;
        AnimatablePointValue animatablePointValue = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName == 1) {
                animatableValue = AnimatablePathValueParser.parseSplitPath(jsonReader, lottieComposition);
            } else if (selectName == 2) {
                animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
            } else if (selectName == 3) {
                z3 = jsonReader.nextBoolean();
            } else if (selectName != 4) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                z2 = jsonReader.nextInt() == 3;
            }
        }
        return new CircleShape(str, animatableValue, animatablePointValue, z2, z3);
    }
}
