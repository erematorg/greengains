package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.ScaleXY;
import java.io.IOException;

public class ScaleXYParser implements ValueParser<ScaleXY> {
    public static final ScaleXYParser INSTANCE = new ScaleXYParser();

    private ScaleXYParser() {
    }

    public ScaleXY parse(JsonReader jsonReader, float f2) throws IOException {
        boolean z2 = jsonReader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (z2) {
            jsonReader.beginArray();
        }
        float nextDouble = (float) jsonReader.nextDouble();
        float nextDouble2 = (float) jsonReader.nextDouble();
        while (jsonReader.hasNext()) {
            jsonReader.skipValue();
        }
        if (z2) {
            jsonReader.endArray();
        }
        return new ScaleXY((nextDouble / 100.0f) * f2, (nextDouble2 / 100.0f) * f2);
    }
}
