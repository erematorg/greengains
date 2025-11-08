package com.google.crypto.tink.jwt;

import androidx.camera.camera2.internal.C0118y;
import com.google.crypto.tink.internal.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;

final class JsonUtil {
    private JsonUtil() {
    }

    public static boolean isValidString(String str) {
        return JsonParser.isValidString(str);
    }

    public static JsonObject parseJson(String str) throws JwtInvalidException {
        try {
            return JsonParser.parse(str).getAsJsonObject();
        } catch (JsonParseException | IOException | IllegalStateException e3) {
            throw new JwtInvalidException(C0118y.e("invalid JSON: ", e3));
        }
    }

    public static JsonArray parseJsonArray(String str) throws JwtInvalidException {
        try {
            return JsonParser.parse(str).getAsJsonArray();
        } catch (JsonParseException | IOException | IllegalStateException e3) {
            throw new JwtInvalidException(C0118y.e("invalid JSON: ", e3));
        }
    }
}
