package com.squareup.moshi.adapters;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import java.io.IOException;
import java.util.Date;

public final class Rfc3339DateJsonAdapter extends JsonAdapter<Date> {
    public synchronized Date fromJson(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonReader.Token.NULL) {
            return (Date) jsonReader.nextNull();
        }
        return Iso8601Utils.parse(jsonReader.nextString());
    }

    public synchronized void toJson(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            try {
                jsonWriter.nullValue();
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            jsonWriter.value(Iso8601Utils.format(date));
        }
    }
}
