package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.commons.text.StringSubstitutor;

public final class Gson {
    static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    static final String DEFAULT_DATE_PATTERN = null;
    static final boolean DEFAULT_ESCAPE_HTML = true;
    static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
    static final FormattingStyle DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
    static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
    static final boolean DEFAULT_SERIALIZE_NULLS = false;
    static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    static final Strictness DEFAULT_STRICTNESS = null;
    static final boolean DEFAULT_USE_JDK_UNSAFE = true;
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    final List<TypeAdapterFactory> builderFactories;
    final List<TypeAdapterFactory> builderHierarchyFactories;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List<TypeAdapterFactory> factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final FormattingStyle formattingStyle;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map<Type, InstanceCreator<?>> instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final LongSerializationPolicy longSerializationPolicy;
    final ToNumberStrategy numberToNumberStrategy;
    final ToNumberStrategy objectToNumberStrategy;
    final List<ReflectionAccessFilter> reflectionFilters;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final Strictness strictness;
    private final ThreadLocal<Map<TypeToken<?>, TypeAdapter<?>>> threadLocalAdapterResults;
    final int timeStyle;
    private final ConcurrentMap<TypeToken<?>, TypeAdapter<?>> typeTokenCache;
    final boolean useJdkUnsafe;

    public static class FutureTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
        private TypeAdapter<T> delegate = null;

        private TypeAdapter<T> delegate() {
            TypeAdapter<T> typeAdapter = this.delegate;
            if (typeAdapter != null) {
                return typeAdapter;
            }
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }

        public TypeAdapter<T> getSerializationDelegate() {
            return delegate();
        }

        public T read(JsonReader jsonReader) throws IOException {
            return delegate().read(jsonReader);
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate == null) {
                this.delegate = typeAdapter;
                return;
            }
            throw new AssertionError("Delegate is already set");
        }

        public void write(JsonWriter jsonWriter, T t2) throws IOException {
            delegate().write(jsonWriter, t2);
        }
    }

    public Gson() {
        this(Excluder.DEFAULT, DEFAULT_FIELD_NAMING_STRATEGY, Collections.emptyMap(), false, false, false, true, DEFAULT_FORMATTING_STYLE, DEFAULT_STRICTNESS, false, true, LongSerializationPolicy.DEFAULT, DEFAULT_DATE_PATTERN, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), DEFAULT_OBJECT_TO_NUMBER_STRATEGY, DEFAULT_NUMBER_TO_NUMBER_STRATEGY, Collections.emptyList());
    }

    private static void assertFullConsumption(Object obj, JsonReader jsonReader) {
        if (obj != null) {
            try {
                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonSyntaxException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e3) {
                throw new JsonSyntaxException((Throwable) e3);
            } catch (IOException e4) {
                throw new JsonIOException((Throwable) e4);
            }
        }
    }

    private static TypeAdapter<AtomicLong> atomicLongAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLong>() {
            public AtomicLong read(JsonReader jsonReader) throws IOException {
                return new AtomicLong(((Number) TypeAdapter.this.read(jsonReader)).longValue());
            }

            public void write(JsonWriter jsonWriter, AtomicLong atomicLong) throws IOException {
                TypeAdapter.this.write(jsonWriter, Long.valueOf(atomicLong.get()));
            }
        }.nullSafe();
    }

    private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(final TypeAdapter<Number> typeAdapter) {
        return new TypeAdapter<AtomicLongArray>() {
            public AtomicLongArray read(JsonReader jsonReader) throws IOException {
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(Long.valueOf(((Number) TypeAdapter.this.read(jsonReader)).longValue()));
                }
                jsonReader.endArray();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i3 = 0; i3 < size; i3++) {
                    atomicLongArray.set(i3, ((Long) arrayList.get(i3)).longValue());
                }
                return atomicLongArray;
            }

            public void write(JsonWriter jsonWriter, AtomicLongArray atomicLongArray) throws IOException {
                jsonWriter.beginArray();
                int length = atomicLongArray.length();
                for (int i3 = 0; i3 < length; i3++) {
                    TypeAdapter.this.write(jsonWriter, Long.valueOf(atomicLongArray.get(i3)));
                }
                jsonWriter.endArray();
            }
        }.nullSafe();
    }

    public static void checkValidFloatingPoint(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private TypeAdapter<Number> doubleAdapter(boolean z2) {
        return z2 ? TypeAdapters.DOUBLE : new TypeAdapter<Number>() {
            public Double read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Double.valueOf(jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                double doubleValue = number.doubleValue();
                Gson.checkValidFloatingPoint(doubleValue);
                jsonWriter.value(doubleValue);
            }
        };
    }

    private TypeAdapter<Number> floatAdapter(boolean z2) {
        return z2 ? TypeAdapters.FLOAT : new TypeAdapter<Number>() {
            public Float read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Float.valueOf((float) jsonReader.nextDouble());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                    return;
                }
                float floatValue = number.floatValue();
                Gson.checkValidFloatingPoint((double) floatValue);
                if (!(number instanceof Float)) {
                    number = Float.valueOf(floatValue);
                }
                jsonWriter.value(number);
            }
        };
    }

    private static TypeAdapter<Number> longAdapter(LongSerializationPolicy longSerializationPolicy2) {
        return longSerializationPolicy2 == LongSerializationPolicy.DEFAULT ? TypeAdapters.LONG : new TypeAdapter<Number>() {
            public Number read(JsonReader jsonReader) throws IOException {
                if (jsonReader.peek() != JsonToken.NULL) {
                    return Long.valueOf(jsonReader.nextLong());
                }
                jsonReader.nextNull();
                return null;
            }

            public void write(JsonWriter jsonWriter, Number number) throws IOException {
                if (number == null) {
                    jsonWriter.nullValue();
                } else {
                    jsonWriter.value(number.toString());
                }
            }
        };
    }

    @Deprecated
    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public <T> T fromJson(String str, Class<T> cls) throws JsonSyntaxException {
        return fromJson(str, TypeToken.get(cls));
    }

    public <T> TypeAdapter<T> getAdapter(TypeToken<T> typeToken) {
        boolean z2;
        Objects.requireNonNull(typeToken, "type must not be null");
        TypeAdapter<T> typeAdapter = this.typeTokenCache.get(typeToken);
        if (typeAdapter != null) {
            return typeAdapter;
        }
        Map map = this.threadLocalAdapterResults.get();
        if (map == null) {
            map = new HashMap();
            this.threadLocalAdapterResults.set(map);
            z2 = true;
        } else {
            TypeAdapter<T> typeAdapter2 = (TypeAdapter) map.get(typeToken);
            if (typeAdapter2 != null) {
                return typeAdapter2;
            }
            z2 = false;
        }
        try {
            FutureTypeAdapter futureTypeAdapter = new FutureTypeAdapter();
            map.put(typeToken, futureTypeAdapter);
            Iterator<TypeAdapterFactory> it = this.factories.iterator();
            TypeAdapter<T> typeAdapter3 = null;
            while (true) {
                if (it.hasNext()) {
                    typeAdapter3 = it.next().create(this, typeToken);
                    if (typeAdapter3 != null) {
                        futureTypeAdapter.setDelegate(typeAdapter3);
                        map.put(typeToken, typeAdapter3);
                        break;
                    }
                } else {
                    break;
                }
            }
            if (typeAdapter3 != null) {
                if (z2) {
                    this.typeTokenCache.putAll(map);
                }
                return typeAdapter3;
            }
            throw new IllegalArgumentException("GSON (2.13.2) cannot handle " + typeToken);
        } finally {
            if (z2) {
                this.threadLocalAdapterResults.remove();
            }
        }
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory typeAdapterFactory, TypeToken<T> typeToken) {
        Objects.requireNonNull(typeAdapterFactory, "skipPast must not be null");
        Objects.requireNonNull(typeToken, "type must not be null");
        if (this.jsonAdapterFactory.isClassJsonAdapterFactory(typeToken, typeAdapterFactory)) {
            typeAdapterFactory = this.jsonAdapterFactory;
        }
        boolean z2 = false;
        for (TypeAdapterFactory next : this.factories) {
            if (z2) {
                TypeAdapter<T> create = next.create(this, typeToken);
                if (create != null) {
                    return create;
                }
            } else if (next == typeAdapterFactory) {
                z2 = true;
            }
        }
        if (!z2) {
            return getAdapter(typeToken);
        }
        throw new IllegalArgumentException("GSON cannot serialize or deserialize " + typeToken);
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    public GsonBuilder newBuilder() {
        return new GsonBuilder(this);
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        Strictness strictness2 = this.strictness;
        if (strictness2 == null) {
            strictness2 = Strictness.LEGACY_STRICT;
        }
        jsonReader.setStrictness(strictness2);
        return jsonReader;
    }

    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setFormattingStyle(this.formattingStyle);
        jsonWriter.setHtmlSafe(this.htmlSafe);
        Strictness strictness2 = this.strictness;
        if (strictness2 == null) {
            strictness2 = Strictness.LEGACY_STRICT;
        }
        jsonWriter.setStrictness(strictness2);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public String toJson(Object obj) {
        if (obj == null) {
            return toJson((JsonElement) JsonNull.INSTANCE);
        }
        return toJson(obj, (Type) obj.getClass());
    }

    public JsonElement toJsonTree(Object obj) {
        if (obj == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(obj, obj.getClass());
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + StringSubstitutor.DEFAULT_VAR_END;
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        return fromJson(str, TypeToken.get(type));
    }

    public <T> T fromJson(String str, TypeToken<T> typeToken) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return fromJson((Reader) new StringReader(str), typeToken);
    }

    public String toJson(Object obj, Type type) {
        StringBuilder sb = new StringBuilder();
        toJson(obj, type, (Appendable) sb);
        return sb.toString();
    }

    public JsonElement toJsonTree(Object obj, Type type) {
        JsonTreeWriter jsonTreeWriter = new JsonTreeWriter();
        toJson(obj, type, (JsonWriter) jsonTreeWriter);
        return jsonTreeWriter.get();
    }

    public <T> T fromJson(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        return fromJson(reader, TypeToken.get(cls));
    }

    public <T> T fromJson(Reader reader, Type type) throws JsonIOException, JsonSyntaxException {
        return fromJson(reader, TypeToken.get(type));
    }

    public void toJson(Object obj, Appendable appendable) throws JsonIOException {
        if (obj != null) {
            toJson(obj, (Type) obj.getClass(), appendable);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, appendable);
        }
    }

    public <T> T fromJson(Reader reader, TypeToken<T> typeToken) throws JsonIOException, JsonSyntaxException {
        JsonReader newJsonReader = newJsonReader(reader);
        T fromJson = fromJson(newJsonReader, typeToken);
        assertFullConsumption(fromJson, newJsonReader);
        return fromJson;
    }

    public Gson(Excluder excluder2, FieldNamingStrategy fieldNamingStrategy2, Map<Type, InstanceCreator<?>> map, boolean z2, boolean z3, boolean z4, boolean z5, FormattingStyle formattingStyle2, Strictness strictness2, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy2, String str, int i3, int i4, List<TypeAdapterFactory> list, List<TypeAdapterFactory> list2, List<TypeAdapterFactory> list3, ToNumberStrategy toNumberStrategy, ToNumberStrategy toNumberStrategy2, List<ReflectionAccessFilter> list4) {
        Map<Type, InstanceCreator<?>> map2 = map;
        boolean z8 = z3;
        boolean z9 = z6;
        boolean z10 = z7;
        List<ReflectionAccessFilter> list5 = list4;
        this.threadLocalAdapterResults = new ThreadLocal<>();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = excluder2;
        this.fieldNamingStrategy = fieldNamingStrategy2;
        this.instanceCreators = map2;
        ConstructorConstructor constructorConstructor2 = new ConstructorConstructor(map2, z10, list5);
        this.constructorConstructor = constructorConstructor2;
        this.serializeNulls = z2;
        this.complexMapKeySerialization = z8;
        this.generateNonExecutableJson = z4;
        this.htmlSafe = z5;
        this.formattingStyle = formattingStyle2;
        this.strictness = strictness2;
        this.serializeSpecialFloatingPointValues = z9;
        this.useJdkUnsafe = z10;
        this.longSerializationPolicy = longSerializationPolicy2;
        this.datePattern = str;
        this.dateStyle = i3;
        this.timeStyle = i4;
        this.builderFactories = list;
        this.builderHierarchyFactories = list2;
        this.objectToNumberStrategy = toNumberStrategy;
        this.numberToNumberStrategy = toNumberStrategy2;
        this.reflectionFilters = list5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        arrayList.add(ObjectTypeAdapter.getFactory(toNumberStrategy));
        arrayList.add(excluder2);
        arrayList.addAll(list3);
        arrayList.add(TypeAdapters.STRING_FACTORY);
        arrayList.add(TypeAdapters.INTEGER_FACTORY);
        arrayList.add(TypeAdapters.BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.BYTE_FACTORY);
        arrayList.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter<Number> longAdapter = longAdapter(longSerializationPolicy2);
        arrayList.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter));
        arrayList.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(z9)));
        arrayList.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(z9)));
        arrayList.add(NumberTypeAdapter.getFactory(toNumberStrategy2));
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        arrayList.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        arrayList.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(longAdapter)));
        arrayList.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(longAdapter)));
        arrayList.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        arrayList.add(TypeAdapters.CHARACTER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUILDER_FACTORY);
        arrayList.add(TypeAdapters.STRING_BUFFER_FACTORY);
        arrayList.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
        arrayList.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
        arrayList.add(TypeAdapters.newFactory(LazilyParsedNumber.class, TypeAdapters.LAZILY_PARSED_NUMBER));
        arrayList.add(TypeAdapters.URL_FACTORY);
        arrayList.add(TypeAdapters.URI_FACTORY);
        arrayList.add(TypeAdapters.UUID_FACTORY);
        arrayList.add(TypeAdapters.CURRENCY_FACTORY);
        arrayList.add(TypeAdapters.LOCALE_FACTORY);
        arrayList.add(TypeAdapters.INET_ADDRESS_FACTORY);
        arrayList.add(TypeAdapters.BIT_SET_FACTORY);
        arrayList.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
        arrayList.add(TypeAdapters.CALENDAR_FACTORY);
        if (SqlTypesSupport.SUPPORTS_SQL_TYPES) {
            arrayList.add(SqlTypesSupport.TIME_FACTORY);
            arrayList.add(SqlTypesSupport.DATE_FACTORY);
            arrayList.add(SqlTypesSupport.TIMESTAMP_FACTORY);
        }
        arrayList.add(ArrayTypeAdapter.FACTORY);
        arrayList.add(TypeAdapters.CLASS_FACTORY);
        arrayList.add(new CollectionTypeAdapterFactory(constructorConstructor2));
        arrayList.add(new MapTypeAdapterFactory(constructorConstructor2, z8));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(constructorConstructor2);
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(TypeAdapters.ENUM_FACTORY);
        arrayList.add(new ReflectiveTypeAdapterFactory(constructorConstructor2, fieldNamingStrategy2, excluder2, jsonAdapterAnnotationTypeAdapterFactory, list4));
        this.factories = Collections.unmodifiableList(arrayList);
    }

    public void toJson(Object obj, Type type, Appendable appendable) throws JsonIOException {
        try {
            toJson(obj, type, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e3) {
            throw new JsonIOException((Throwable) e3);
        }
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonIOException, JsonSyntaxException {
        return fromJson(jsonReader, TypeToken.get(type));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006d, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006f, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x008c, code lost:
        throw new java.lang.AssertionError("AssertionError (GSON 2.13.2): " + r7.getMessage(), r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0099, code lost:
        r7 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009a, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009d, code lost:
        r8.setStrictness(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a1, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a7, code lost:
        throw new com.google.gson.JsonSyntaxException((java.lang.Throwable) r7);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b A[ExcHandler: AssertionError (r7v6 'e' java.lang.AssertionError A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d A[ExcHandler: IOException (r7v5 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f A[ExcHandler: IllegalStateException (r7v4 'e' java.lang.IllegalStateException A[CUSTOM_DECLARE]), Splitter:B:6:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2 A[SYNTHETIC, Splitter:B:36:0x00a2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T fromJson(com.google.gson.stream.JsonReader r8, com.google.gson.reflect.TypeToken<T> r9) throws com.google.gson.JsonIOException, com.google.gson.JsonSyntaxException {
        /*
            r7 = this;
            java.lang.String r0 = "AssertionError (GSON 2.13.2): "
            java.lang.String r1 = "Type adapter '"
            com.google.gson.Strictness r2 = r8.getStrictness()
            com.google.gson.Strictness r3 = r7.strictness
            if (r3 == 0) goto L_0x0010
            r8.setStrictness(r3)
            goto L_0x001d
        L_0x0010:
            com.google.gson.Strictness r3 = r8.getStrictness()
            com.google.gson.Strictness r4 = com.google.gson.Strictness.LEGACY_STRICT
            if (r3 != r4) goto L_0x001d
            com.google.gson.Strictness r3 = com.google.gson.Strictness.LENIENT
            r8.setStrictness(r3)
        L_0x001d:
            r8.peek()     // Catch:{ EOFException -> 0x0099, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r3 = 0
            com.google.gson.TypeAdapter r7 = r7.getAdapter(r9)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Object r4 = r7.read(r8)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r5 = r9.getRawType()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r5 = com.google.gson.internal.Primitives.wrap(r5)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            if (r4 == 0) goto L_0x0073
            boolean r5 = r5.isInstance(r4)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            if (r5 == 0) goto L_0x003a
            goto L_0x0073
        L_0x003a:
            java.lang.ClassCastException r5 = new java.lang.ClassCastException     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.<init>(r1)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = "' returned wrong type; requested "
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r7 = r9.getRawType()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = " but got instance of "
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.Class r7 = r4.getClass()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = "\nVerify that the adapter was registered for the correct type."
            r6.append(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            java.lang.String r7 = r6.toString()     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            r5.<init>(r7)     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
            throw r5     // Catch:{ EOFException -> 0x0071, IllegalStateException -> 0x006f, IOException -> 0x006d, AssertionError -> 0x006b }
        L_0x0069:
            r7 = move-exception
            goto L_0x00a8
        L_0x006b:
            r7 = move-exception
            goto L_0x0077
        L_0x006d:
            r7 = move-exception
            goto L_0x008d
        L_0x006f:
            r7 = move-exception
            goto L_0x0093
        L_0x0071:
            r7 = move-exception
            goto L_0x009b
        L_0x0073:
            r8.setStrictness(r2)
            return r4
        L_0x0077:
            java.lang.AssertionError r9 = new java.lang.AssertionError     // Catch:{ all -> 0x0069 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0069 }
            r1.<init>(r0)     // Catch:{ all -> 0x0069 }
            java.lang.String r0 = r7.getMessage()     // Catch:{ all -> 0x0069 }
            r1.append(r0)     // Catch:{ all -> 0x0069 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x0069 }
            r9.<init>(r0, r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x008d:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x0093:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x0099:
            r7 = move-exception
            r3 = 1
        L_0x009b:
            if (r3 == 0) goto L_0x00a2
            r8.setStrictness(r2)
            r7 = 0
            return r7
        L_0x00a2:
            com.google.gson.JsonSyntaxException r9 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0069 }
            r9.<init>((java.lang.Throwable) r7)     // Catch:{ all -> 0x0069 }
            throw r9     // Catch:{ all -> 0x0069 }
        L_0x00a8:
            r8.setStrictness(r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.fromJson(com.google.gson.stream.JsonReader, com.google.gson.reflect.TypeToken):java.lang.Object");
    }

    public void toJson(Object obj, Type type, JsonWriter jsonWriter) throws JsonIOException {
        TypeAdapter<?> adapter = getAdapter(TypeToken.get(type));
        Strictness strictness2 = jsonWriter.getStrictness();
        Strictness strictness3 = this.strictness;
        if (strictness3 != null) {
            jsonWriter.setStrictness(strictness3);
        } else if (jsonWriter.getStrictness() == Strictness.LEGACY_STRICT) {
            jsonWriter.setStrictness(Strictness.LENIENT);
        }
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        boolean serializeNulls2 = jsonWriter.getSerializeNulls();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        try {
            adapter.write(jsonWriter, obj);
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
        } catch (IOException e3) {
            throw new JsonIOException((Throwable) e3);
        } catch (AssertionError e4) {
            throw new AssertionError("AssertionError (GSON 2.13.2): " + e4.getMessage(), e4);
        } catch (Throwable th) {
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
            throw th;
        }
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> cls) {
        return getAdapter(TypeToken.get(cls));
    }

    public String toJson(JsonElement jsonElement) {
        StringBuilder sb = new StringBuilder();
        toJson(jsonElement, (Appendable) sb);
        return sb.toString();
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        return fromJson(jsonElement, TypeToken.get(cls));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        return fromJson(jsonElement, TypeToken.get(type));
    }

    public void toJson(JsonElement jsonElement, Appendable appendable) throws JsonIOException {
        try {
            toJson(jsonElement, newJsonWriter(Streams.writerForAppendable(appendable)));
        } catch (IOException e3) {
            throw new JsonIOException((Throwable) e3);
        }
    }

    public <T> T fromJson(JsonElement jsonElement, TypeToken<T> typeToken) throws JsonSyntaxException {
        if (jsonElement == null) {
            return null;
        }
        return fromJson((JsonReader) new JsonTreeReader(jsonElement), typeToken);
    }

    public void toJson(JsonElement jsonElement, JsonWriter jsonWriter) throws JsonIOException {
        Strictness strictness2 = jsonWriter.getStrictness();
        boolean isHtmlSafe = jsonWriter.isHtmlSafe();
        boolean serializeNulls2 = jsonWriter.getSerializeNulls();
        jsonWriter.setHtmlSafe(this.htmlSafe);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        Strictness strictness3 = this.strictness;
        if (strictness3 != null) {
            jsonWriter.setStrictness(strictness3);
        } else if (jsonWriter.getStrictness() == Strictness.LEGACY_STRICT) {
            jsonWriter.setStrictness(Strictness.LENIENT);
        }
        try {
            Streams.write(jsonElement, jsonWriter);
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
        } catch (IOException e3) {
            throw new JsonIOException((Throwable) e3);
        } catch (AssertionError e4) {
            throw new AssertionError("AssertionError (GSON 2.13.2): " + e4.getMessage(), e4);
        } catch (Throwable th) {
            jsonWriter.setStrictness(strictness2);
            jsonWriter.setHtmlSafe(isHtmlSafe);
            jsonWriter.setSerializeNulls(serializeNulls2);
            throw th;
        }
    }
}
