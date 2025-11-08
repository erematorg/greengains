package com.google.crypto.tink.internal;

import androidx.browser.trusted.c;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import javax.annotation.Nullable;

public final class JsonParser {
    private static final JsonElementTypeAdapter JSON_ELEMENT = new JsonElementTypeAdapter((AnonymousClass1) null);

    /* renamed from: com.google.crypto.tink.internal.JsonParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$gson$stream$JsonToken;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.gson.stream.JsonToken[] r0 = com.google.gson.stream.JsonToken.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$gson$stream$JsonToken = r0
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NUMBER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$gson$stream$JsonToken     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.gson.stream.JsonToken r1 = com.google.gson.stream.JsonToken.NULL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.JsonParser.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class JsonElementTypeAdapter extends TypeAdapter<JsonElement> {
        private static final int RECURSION_LIMIT = 100;

        private JsonElementTypeAdapter() {
        }

        private JsonElement readTerminal(JsonReader jsonReader, JsonToken jsonToken) throws IOException {
            int i3 = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()];
            if (i3 == 3) {
                String nextString = jsonReader.nextString();
                if (JsonParser.isValidString(nextString)) {
                    return new JsonPrimitive(nextString);
                }
                throw new IOException("illegal characters in string");
            } else if (i3 == 4) {
                return new JsonPrimitive((Number) new LazilyParsedNumber(jsonReader.nextString()));
            } else {
                if (i3 == 5) {
                    return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                }
                if (i3 == 6) {
                    jsonReader.nextNull();
                    return JsonNull.INSTANCE;
                }
                throw new IllegalStateException("Unexpected token: " + jsonToken);
            }
        }

        @Nullable
        private JsonElement tryBeginNesting(JsonReader jsonReader, JsonToken jsonToken) throws IOException {
            int i3 = AnonymousClass1.$SwitchMap$com$google$gson$stream$JsonToken[jsonToken.ordinal()];
            if (i3 == 1) {
                jsonReader.beginArray();
                return new JsonArray();
            } else if (i3 != 2) {
                return null;
            } else {
                jsonReader.beginObject();
                return new JsonObject();
            }
        }

        public /* synthetic */ JsonElementTypeAdapter(AnonymousClass1 r12) {
            this();
        }

        public JsonElement read(JsonReader jsonReader) throws IOException {
            String str;
            JsonToken peek = jsonReader.peek();
            JsonElement tryBeginNesting = tryBeginNesting(jsonReader, peek);
            if (tryBeginNesting == null) {
                return readTerminal(jsonReader, peek);
            }
            ArrayDeque arrayDeque = new ArrayDeque();
            while (true) {
                if (jsonReader.hasNext()) {
                    if (tryBeginNesting instanceof JsonObject) {
                        str = jsonReader.nextName();
                        if (!JsonParser.isValidString(str)) {
                            throw new IOException("illegal characters in string");
                        }
                    } else {
                        str = null;
                    }
                    JsonToken peek2 = jsonReader.peek();
                    JsonElement tryBeginNesting2 = tryBeginNesting(jsonReader, peek2);
                    boolean z2 = tryBeginNesting2 != null;
                    if (tryBeginNesting2 == null) {
                        tryBeginNesting2 = readTerminal(jsonReader, peek2);
                    }
                    if (tryBeginNesting instanceof JsonArray) {
                        ((JsonArray) tryBeginNesting).add(tryBeginNesting2);
                    } else {
                        JsonObject jsonObject = (JsonObject) tryBeginNesting;
                        if (!jsonObject.has(str)) {
                            jsonObject.add(str, tryBeginNesting2);
                        } else {
                            throw new IOException(c.a("duplicate key: ", str));
                        }
                    }
                    if (z2) {
                        arrayDeque.addLast(tryBeginNesting);
                        if (arrayDeque.size() <= 100) {
                            tryBeginNesting = tryBeginNesting2;
                        } else {
                            throw new IOException("too many recursions");
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (tryBeginNesting instanceof JsonArray) {
                        jsonReader.endArray();
                    } else {
                        jsonReader.endObject();
                    }
                    if (arrayDeque.isEmpty()) {
                        return tryBeginNesting;
                    }
                    tryBeginNesting = (JsonElement) arrayDeque.removeLast();
                }
            }
        }

        public void write(JsonWriter jsonWriter, JsonElement jsonElement) {
            throw new UnsupportedOperationException("write is not supported");
        }
    }

    public static final class LazilyParsedNumber extends Number {
        private final String value;

        public LazilyParsedNumber(String str) {
            this.value = str;
        }

        private void readObject(ObjectInputStream objectInputStream) throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        private Object writeReplace() throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        public double doubleValue() {
            return Double.parseDouble(this.value);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LazilyParsedNumber) {
                return this.value.equals(((LazilyParsedNumber) obj).value);
            }
            return false;
        }

        public float floatValue() {
            return Float.parseFloat(this.value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
            return (int) java.lang.Long.parseLong(r2.value);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
            return new java.math.BigDecimal(r2.value).intValue();
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int intValue() {
            /*
                r2 = this;
                java.lang.String r0 = r2.value     // Catch:{ NumberFormatException -> 0x0007 }
                int r2 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0007 }
                return r2
            L_0x0007:
                java.lang.String r0 = r2.value     // Catch:{ NumberFormatException -> 0x000f }
                long r0 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x000f }
                int r2 = (int) r0
                return r2
            L_0x000f:
                java.math.BigDecimal r0 = new java.math.BigDecimal
                java.lang.String r2 = r2.value
                r0.<init>(r2)
                int r2 = r0.intValue()
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.internal.JsonParser.LazilyParsedNumber.intValue():int");
        }

        public long longValue() {
            try {
                return Long.parseLong(this.value);
            } catch (NumberFormatException unused) {
                return new BigDecimal(this.value).longValue();
            }
        }

        public String toString() {
            return this.value;
        }
    }

    private JsonParser() {
    }

    public static long getParsedNumberAsLongOrThrow(JsonElement jsonElement) {
        if (jsonElement.getAsNumber() instanceof LazilyParsedNumber) {
            return Long.parseLong(jsonElement.getAsNumber().toString());
        }
        throw new IllegalArgumentException("does not contain a parsed number.");
    }

    public static boolean isValidString(String str) {
        int length = str.length();
        int i3 = 0;
        while (i3 != length) {
            char charAt = str.charAt(i3);
            int i4 = i3 + 1;
            if (!Character.isSurrogate(charAt)) {
                i3 = i4;
            } else if (Character.isLowSurrogate(charAt) || i4 == length || !Character.isLowSurrogate(str.charAt(i4))) {
                return false;
            } else {
                i3 += 2;
            }
        }
        return true;
    }

    public static JsonElement parse(String str) throws IOException {
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.setLenient(false);
            return JSON_ELEMENT.read(jsonReader);
        } catch (NumberFormatException e3) {
            throw new IOException(e3);
        }
    }
}
