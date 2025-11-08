package com.squareup.moshi;

import com.squareup.moshi.JsonReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Buffer;
import okio.BufferedSource;

final class JsonValueReader extends JsonReader {
    private static final Object JSON_READER_CLOSED = new Object();
    private Object[] stack;

    public static final class JsonIterator implements Iterator<Object>, Cloneable {
        final Object[] array;
        final JsonReader.Token endToken;
        int next;

        public JsonIterator(JsonReader.Token token, Object[] objArr, int i3) {
            this.endToken = token;
            this.array = objArr;
            this.next = i3;
        }

        public boolean hasNext() {
            return this.next < this.array.length;
        }

        public Object next() {
            Object[] objArr = this.array;
            int i3 = this.next;
            this.next = i3 + 1;
            return objArr[i3];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public JsonIterator clone() {
            return new JsonIterator(this.endToken, this.array, this.next);
        }
    }

    public JsonValueReader(Object obj) {
        int[] iArr = this.scopes;
        int i3 = this.stackSize;
        iArr[i3] = 7;
        Object[] objArr = new Object[32];
        this.stack = objArr;
        this.stackSize = i3 + 1;
        objArr[i3] = obj;
    }

    private void push(Object obj) {
        int i3 = this.stackSize;
        if (i3 == this.stack.length) {
            if (i3 != 256) {
                int[] iArr = this.scopes;
                this.scopes = Arrays.copyOf(iArr, iArr.length * 2);
                String[] strArr = this.pathNames;
                this.pathNames = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
                int[] iArr2 = this.pathIndices;
                this.pathIndices = Arrays.copyOf(iArr2, iArr2.length * 2);
                Object[] objArr = this.stack;
                this.stack = Arrays.copyOf(objArr, objArr.length * 2);
            } else {
                throw new JsonDataException("Nesting too deep at " + getPath());
            }
        }
        Object[] objArr2 = this.stack;
        int i4 = this.stackSize;
        this.stackSize = i4 + 1;
        objArr2[i4] = obj;
    }

    private void remove() {
        int i3 = this.stackSize;
        int i4 = i3 - 1;
        this.stackSize = i4;
        Object[] objArr = this.stack;
        objArr[i4] = null;
        this.scopes[i4] = 0;
        if (i4 > 0) {
            int[] iArr = this.pathIndices;
            int i5 = i3 - 2;
            iArr[i5] = iArr[i5] + 1;
            Object obj = objArr[i3 - 2];
            if (obj instanceof Iterator) {
                Iterator it = (Iterator) obj;
                if (it.hasNext()) {
                    push(it.next());
                }
            }
        }
    }

    @Nullable
    private <T> T require(Class<T> cls, JsonReader.Token token) throws IOException {
        int i3 = this.stackSize;
        Object obj = i3 != 0 ? this.stack[i3 - 1] : null;
        if (cls.isInstance(obj)) {
            return cls.cast(obj);
        }
        if (obj == null && token == JsonReader.Token.NULL) {
            return null;
        }
        if (obj == JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw typeMismatch(obj, token);
    }

    private String stringKey(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        if (key instanceof String) {
            return (String) key;
        }
        throw typeMismatch(key, JsonReader.Token.NAME);
    }

    public void beginArray() throws IOException {
        List list = (List) require(List.class, JsonReader.Token.BEGIN_ARRAY);
        JsonIterator jsonIterator = new JsonIterator(JsonReader.Token.END_ARRAY, list.toArray(new Object[list.size()]), 0);
        Object[] objArr = this.stack;
        int i3 = this.stackSize;
        objArr[i3 - 1] = jsonIterator;
        this.scopes[i3 - 1] = 1;
        this.pathIndices[i3 - 1] = 0;
        if (jsonIterator.hasNext()) {
            push(jsonIterator.next());
        }
    }

    public void beginObject() throws IOException {
        Map map = (Map) require(Map.class, JsonReader.Token.BEGIN_OBJECT);
        JsonIterator jsonIterator = new JsonIterator(JsonReader.Token.END_OBJECT, map.entrySet().toArray(new Object[map.size()]), 0);
        Object[] objArr = this.stack;
        int i3 = this.stackSize;
        objArr[i3 - 1] = jsonIterator;
        this.scopes[i3 - 1] = 3;
        if (jsonIterator.hasNext()) {
            push(jsonIterator.next());
        }
    }

    public void close() throws IOException {
        Arrays.fill(this.stack, 0, this.stackSize, (Object) null);
        this.stack[0] = JSON_READER_CLOSED;
        this.scopes[0] = 8;
        this.stackSize = 1;
    }

    public void endArray() throws IOException {
        JsonReader.Token token = JsonReader.Token.END_ARRAY;
        JsonIterator jsonIterator = (JsonIterator) require(JsonIterator.class, token);
        if (jsonIterator.endToken != token || jsonIterator.hasNext()) {
            throw typeMismatch(jsonIterator, token);
        }
        remove();
    }

    public void endObject() throws IOException {
        JsonReader.Token token = JsonReader.Token.END_OBJECT;
        JsonIterator jsonIterator = (JsonIterator) require(JsonIterator.class, token);
        if (jsonIterator.endToken != token || jsonIterator.hasNext()) {
            throw typeMismatch(jsonIterator, token);
        }
        this.pathNames[this.stackSize - 1] = null;
        remove();
    }

    public boolean hasNext() throws IOException {
        int i3 = this.stackSize;
        if (i3 == 0) {
            return false;
        }
        Object obj = this.stack[i3 - 1];
        return !(obj instanceof Iterator) || ((Iterator) obj).hasNext();
    }

    public boolean nextBoolean() throws IOException {
        remove();
        return ((Boolean) require(Boolean.class, JsonReader.Token.BOOLEAN)).booleanValue();
    }

    public double nextDouble() throws IOException {
        double d2;
        JsonReader.Token token = JsonReader.Token.NUMBER;
        Object require = require(Object.class, token);
        if (require instanceof Number) {
            d2 = ((Number) require).doubleValue();
        } else if (require instanceof String) {
            try {
                d2 = Double.parseDouble((String) require);
            } catch (NumberFormatException unused) {
                throw typeMismatch(require, JsonReader.Token.NUMBER);
            }
        } else {
            throw typeMismatch(require, token);
        }
        if (this.lenient || (!Double.isNaN(d2) && !Double.isInfinite(d2))) {
            remove();
            return d2;
        }
        throw new JsonEncodingException("JSON forbids NaN and infinities: " + d2 + " at path " + getPath());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        throw typeMismatch(r1, com.squareup.moshi.JsonReader.Token.NUMBER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = new java.math.BigDecimal((java.lang.String) r1).intValueExact();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextInt() throws java.io.IOException {
        /*
            r3 = this;
            com.squareup.moshi.JsonReader$Token r0 = com.squareup.moshi.JsonReader.Token.NUMBER
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            java.lang.Object r1 = r3.require(r1, r0)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0013
            java.lang.Number r1 = (java.lang.Number) r1
            int r0 = r1.intValue()
            goto L_0x002b
        L_0x0013:
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0036
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x001f }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x001f }
            goto L_0x002b
        L_0x001f:
            java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x002f }
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x002f }
            r0.<init>(r2)     // Catch:{ NumberFormatException -> 0x002f }
            int r0 = r0.intValueExact()     // Catch:{ NumberFormatException -> 0x002f }
        L_0x002b:
            r3.remove()
            return r0
        L_0x002f:
            com.squareup.moshi.JsonReader$Token r0 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r3 = r3.typeMismatch(r1, r0)
            throw r3
        L_0x0036:
            com.squareup.moshi.JsonDataException r3 = r3.typeMismatch(r1, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonValueReader.nextInt():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        throw typeMismatch(r1, com.squareup.moshi.JsonReader.Token.NUMBER);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r0 = new java.math.BigDecimal((java.lang.String) r1).longValueExact();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long nextLong() throws java.io.IOException {
        /*
            r3 = this;
            com.squareup.moshi.JsonReader$Token r0 = com.squareup.moshi.JsonReader.Token.NUMBER
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            java.lang.Object r1 = r3.require(r1, r0)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0013
            java.lang.Number r1 = (java.lang.Number) r1
            long r0 = r1.longValue()
            goto L_0x002b
        L_0x0013:
            boolean r2 = r1 instanceof java.lang.String
            if (r2 == 0) goto L_0x0036
            r0 = r1
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x001f }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x001f }
            goto L_0x002b
        L_0x001f:
            java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x002f }
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ NumberFormatException -> 0x002f }
            r0.<init>(r2)     // Catch:{ NumberFormatException -> 0x002f }
            long r0 = r0.longValueExact()     // Catch:{ NumberFormatException -> 0x002f }
        L_0x002b:
            r3.remove()
            return r0
        L_0x002f:
            com.squareup.moshi.JsonReader$Token r0 = com.squareup.moshi.JsonReader.Token.NUMBER
            com.squareup.moshi.JsonDataException r3 = r3.typeMismatch(r1, r0)
            throw r3
        L_0x0036:
            com.squareup.moshi.JsonDataException r3 = r3.typeMismatch(r1, r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonValueReader.nextLong():long");
    }

    public String nextName() throws IOException {
        Map.Entry entry = (Map.Entry) require(Map.Entry.class, JsonReader.Token.NAME);
        String stringKey = stringKey(entry);
        this.stack[this.stackSize - 1] = entry.getValue();
        this.pathNames[this.stackSize - 2] = stringKey;
        return stringKey;
    }

    @Nullable
    public <T> T nextNull() throws IOException {
        require(Void.class, JsonReader.Token.NULL);
        remove();
        return null;
    }

    public BufferedSource nextSource() throws IOException {
        Object readJsonValue = readJsonValue();
        Buffer buffer = new Buffer();
        JsonWriter of = JsonWriter.of(buffer);
        try {
            of.jsonValue(readJsonValue);
            of.close();
            return buffer;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String nextString() throws IOException {
        int i3 = this.stackSize;
        String str = i3 != 0 ? this.stack[i3 - 1] : null;
        if (str instanceof String) {
            remove();
            return str;
        } else if (str instanceof Number) {
            remove();
            return str.toString();
        } else if (str == JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        } else {
            throw typeMismatch(str, JsonReader.Token.STRING);
        }
    }

    public JsonReader.Token peek() throws IOException {
        int i3 = this.stackSize;
        if (i3 == 0) {
            return JsonReader.Token.END_DOCUMENT;
        }
        Object obj = this.stack[i3 - 1];
        if (obj instanceof JsonIterator) {
            return ((JsonIterator) obj).endToken;
        }
        if (obj instanceof List) {
            return JsonReader.Token.BEGIN_ARRAY;
        }
        if (obj instanceof Map) {
            return JsonReader.Token.BEGIN_OBJECT;
        }
        if (obj instanceof Map.Entry) {
            return JsonReader.Token.NAME;
        }
        if (obj instanceof String) {
            return JsonReader.Token.STRING;
        }
        if (obj instanceof Boolean) {
            return JsonReader.Token.BOOLEAN;
        }
        if (obj instanceof Number) {
            return JsonReader.Token.NUMBER;
        }
        if (obj == null) {
            return JsonReader.Token.NULL;
        }
        if (obj == JSON_READER_CLOSED) {
            throw new IllegalStateException("JsonReader is closed");
        }
        throw typeMismatch(obj, "a JSON value");
    }

    public JsonReader peekJson() {
        return new JsonValueReader(this);
    }

    public void promoteNameToValue() throws IOException {
        if (hasNext()) {
            push(nextName());
        }
    }

    public int selectName(JsonReader.Options options) throws IOException {
        Map.Entry entry = (Map.Entry) require(Map.Entry.class, JsonReader.Token.NAME);
        String stringKey = stringKey(entry);
        int length = options.strings.length;
        for (int i3 = 0; i3 < length; i3++) {
            if (options.strings[i3].equals(stringKey)) {
                this.stack[this.stackSize - 1] = entry.getValue();
                this.pathNames[this.stackSize - 2] = stringKey;
                return i3;
            }
        }
        return -1;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int selectString(com.squareup.moshi.JsonReader.Options r6) throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.stackSize
            if (r0 == 0) goto L_0x000b
            java.lang.Object[] r1 = r5.stack
            int r0 = r0 + -1
            r0 = r1[r0]
            goto L_0x000c
        L_0x000b:
            r0 = 0
        L_0x000c:
            boolean r1 = r0 instanceof java.lang.String
            r2 = -1
            if (r1 != 0) goto L_0x001e
            java.lang.Object r5 = JSON_READER_CLOSED
            if (r0 == r5) goto L_0x0016
            return r2
        L_0x0016:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "JsonReader is closed"
            r5.<init>(r6)
            throw r5
        L_0x001e:
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String[] r1 = r6.strings
            int r1 = r1.length
            r3 = 0
        L_0x0024:
            if (r3 >= r1) goto L_0x0037
            java.lang.String[] r4 = r6.strings
            r4 = r4[r3]
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0034
            r5.remove()
            return r3
        L_0x0034:
            int r3 = r3 + 1
            goto L_0x0024
        L_0x0037:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.JsonValueReader.selectString(com.squareup.moshi.JsonReader$Options):int");
    }

    public void skipName() throws IOException {
        if (!this.failOnUnknown) {
            this.stack[this.stackSize - 1] = ((Map.Entry) require(Map.Entry.class, JsonReader.Token.NAME)).getValue();
            this.pathNames[this.stackSize - 2] = AbstractJsonLexerKt.NULL;
            return;
        }
        JsonReader.Token peek = peek();
        nextName();
        throw new JsonDataException("Cannot skip unexpected " + peek + " at " + getPath());
    }

    public void skipValue() throws IOException {
        if (!this.failOnUnknown) {
            int i3 = this.stackSize;
            if (i3 > 1) {
                this.pathNames[i3 - 2] = AbstractJsonLexerKt.NULL;
            }
            Object obj = i3 != 0 ? this.stack[i3 - 1] : null;
            if (obj instanceof JsonIterator) {
                throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
            } else if (obj instanceof Map.Entry) {
                Object[] objArr = this.stack;
                objArr[i3 - 1] = ((Map.Entry) objArr[i3 - 1]).getValue();
            } else if (i3 > 0) {
                remove();
            } else {
                throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
            }
        } else {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
    }

    public JsonValueReader(JsonValueReader jsonValueReader) {
        super(jsonValueReader);
        this.stack = (Object[]) jsonValueReader.stack.clone();
        for (int i3 = 0; i3 < this.stackSize; i3++) {
            Object[] objArr = this.stack;
            Object obj = objArr[i3];
            if (obj instanceof JsonIterator) {
                objArr[i3] = ((JsonIterator) obj).clone();
            }
        }
    }
}
