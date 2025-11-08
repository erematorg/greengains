package com.google.gson;

import A.a;
import androidx.constraintlayout.core.state.b;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.InlineMe;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder;
    private final List<TypeAdapterFactory> factories;
    private FieldNamingStrategy fieldNamingPolicy;
    private FormattingStyle formattingStyle;
    private boolean generateNonExecutableJson;
    private final List<TypeAdapterFactory> hierarchyFactories;
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private LongSerializationPolicy longSerializationPolicy;
    private ToNumberStrategy numberToNumberStrategy;
    private ToNumberStrategy objectToNumberStrategy;
    private final ArrayDeque<ReflectionAccessFilter> reflectionFilters;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private Strictness strictness;
    private int timeStyle;
    private boolean useJdkUnsafe;

    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.datePattern = Gson.DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = Gson.DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        this.reflectionFilters = new ArrayDeque<>();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addTypeAdaptersForDate(java.lang.String r4, int r5, int r6, java.util.List<com.google.gson.TypeAdapterFactory> r7) {
        /*
            boolean r0 = com.google.gson.internal.sql.SqlTypesSupport.SUPPORTS_SQL_TYPES
            r1 = 0
            if (r4 == 0) goto L_0x0026
            java.lang.String r2 = r4.trim()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L_0x0026
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<java.util.Date> r5 = com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType.DATE
            com.google.gson.TypeAdapterFactory r5 = r5.createAdapterFactory(r4)
            if (r0 == 0) goto L_0x0024
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r6 = com.google.gson.internal.sql.SqlTypesSupport.TIMESTAMP_DATE_TYPE
            com.google.gson.TypeAdapterFactory r1 = r6.createAdapterFactory(r4)
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r6 = com.google.gson.internal.sql.SqlTypesSupport.DATE_DATE_TYPE
            com.google.gson.TypeAdapterFactory r4 = r6.createAdapterFactory(r4)
            goto L_0x0047
        L_0x0024:
            r4 = r1
            goto L_0x0047
        L_0x0026:
            r4 = 2
            if (r5 != r4) goto L_0x002d
            if (r6 == r4) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            return
        L_0x002d:
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<java.util.Date> r4 = com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType.DATE
            com.google.gson.TypeAdapterFactory r4 = r4.createAdapterFactory(r5, r6)
            if (r0 == 0) goto L_0x0045
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r1 = com.google.gson.internal.sql.SqlTypesSupport.TIMESTAMP_DATE_TYPE
            com.google.gson.TypeAdapterFactory r1 = r1.createAdapterFactory(r5, r6)
            com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType<? extends java.util.Date> r2 = com.google.gson.internal.sql.SqlTypesSupport.DATE_DATE_TYPE
            com.google.gson.TypeAdapterFactory r5 = r2.createAdapterFactory(r5, r6)
            r3 = r5
            r5 = r4
            r4 = r3
            goto L_0x0047
        L_0x0045:
            r5 = r4
            goto L_0x0024
        L_0x0047:
            r7.add(r5)
            if (r0 == 0) goto L_0x0052
            r7.add(r1)
            r7.add(r4)
        L_0x0052:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.GsonBuilder.addTypeAdaptersForDate(java.lang.String, int, int, java.util.List):void");
    }

    private static int checkDateFormatStyle(int i3) {
        if (i3 >= 0 && i3 <= 3) {
            return i3;
        }
        throw new IllegalArgumentException(a.k("Invalid style: ", i3));
    }

    private static boolean hasNonOverridableAdapter(Type type) {
        return type == Object.class;
    }

    @CanIgnoreReturnValue
    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        Objects.requireNonNull(exclusionStrategy);
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, false, true);
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder addReflectionAccessFilter(ReflectionAccessFilter reflectionAccessFilter) {
        Objects.requireNonNull(reflectionAccessFilter);
        this.reflectionFilters.addFirst(reflectionAccessFilter);
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy exclusionStrategy) {
        Objects.requireNonNull(exclusionStrategy);
        this.excluder = this.excluder.withExclusionStrategy(exclusionStrategy, true, false);
        return this;
    }

    public Gson create() {
        ArrayList arrayList = r1;
        ArrayList arrayList2 = new ArrayList(this.hierarchyFactories.size() + this.factories.size() + 3);
        arrayList2.addAll(this.factories);
        Collections.reverse(arrayList2);
        ArrayList arrayList3 = new ArrayList(this.hierarchyFactories);
        Collections.reverse(arrayList3);
        arrayList2.addAll(arrayList3);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, arrayList2);
        Excluder excluder2 = this.excluder;
        FieldNamingStrategy fieldNamingStrategy = this.fieldNamingPolicy;
        HashMap hashMap = r5;
        HashMap hashMap2 = new HashMap(this.instanceCreators);
        boolean z2 = this.serializeNulls;
        boolean z3 = this.complexMapKeySerialization;
        boolean z4 = this.generateNonExecutableJson;
        boolean z5 = this.escapeHtmlChars;
        FormattingStyle formattingStyle2 = this.formattingStyle;
        Strictness strictness2 = this.strictness;
        boolean z6 = this.serializeSpecialFloatingPointValues;
        boolean z7 = this.useJdkUnsafe;
        LongSerializationPolicy longSerializationPolicy2 = this.longSerializationPolicy;
        String str = this.datePattern;
        int i3 = this.dateStyle;
        int i4 = this.timeStyle;
        ArrayList arrayList4 = r1;
        Excluder excluder3 = excluder2;
        ArrayList arrayList5 = new ArrayList(this.factories);
        ArrayList arrayList6 = r1;
        ArrayList arrayList7 = new ArrayList(this.hierarchyFactories);
        ToNumberStrategy toNumberStrategy = this.objectToNumberStrategy;
        ToNumberStrategy toNumberStrategy2 = this.numberToNumberStrategy;
        ArrayList arrayList8 = r1;
        ArrayList arrayList9 = new ArrayList(this.reflectionFilters);
        return new Gson(excluder3, fieldNamingStrategy, hashMap, z2, z3, z4, z5, formattingStyle2, strictness2, z6, z7, longSerializationPolicy2, str, i3, i4, arrayList4, arrayList6, arrayList, toNumberStrategy, toNumberStrategy2, arrayList8);
    }

    @CanIgnoreReturnValue
    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder disableJdkUnsafe() {
        this.useJdkUnsafe = false;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder excludeFieldsWithModifiers(int... iArr) {
        Objects.requireNonNull(iArr);
        this.excluder = this.excluder.withModifiers(iArr);
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder registerTypeAdapter(Type type, Object obj) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(obj);
        boolean z2 = obj instanceof JsonSerializer;
        if (!z2 && !(obj instanceof JsonDeserializer) && !(obj instanceof InstanceCreator) && !(obj instanceof TypeAdapter)) {
            throw new IllegalArgumentException("Class " + obj.getClass().getName() + " does not implement any supported type adapter class or interface");
        } else if (!hasNonOverridableAdapter(type)) {
            if (obj instanceof InstanceCreator) {
                this.instanceCreators.put(type, (InstanceCreator) obj);
            }
            if (z2 || (obj instanceof JsonDeserializer)) {
                this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type), obj));
            }
            if (obj instanceof TypeAdapter) {
                this.factories.add(TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) obj));
            }
            return this;
        } else {
            throw new IllegalArgumentException(b.n("Cannot override built-in adapter for ", type));
        }
    }

    @CanIgnoreReturnValue
    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory typeAdapterFactory) {
        Objects.requireNonNull(typeAdapterFactory);
        this.factories.add(typeAdapterFactory);
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder registerTypeHierarchyAdapter(Class<?> cls, Object obj) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(obj);
        boolean z2 = obj instanceof JsonSerializer;
        if (z2 || (obj instanceof JsonDeserializer) || (obj instanceof TypeAdapter)) {
            if ((obj instanceof JsonDeserializer) || z2) {
                this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(cls, obj));
            }
            if (obj instanceof TypeAdapter) {
                this.factories.add(TypeAdapters.newTypeHierarchyFactory(cls, (TypeAdapter) obj));
            }
            return this;
        }
        throw new IllegalArgumentException("Class " + obj.getClass().getName() + " does not implement any supported type adapter class or interface");
    }

    @CanIgnoreReturnValue
    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setDateFormat(String str) {
        if (str != null) {
            try {
                new SimpleDateFormat(str);
            } catch (IllegalArgumentException e3) {
                throw new IllegalArgumentException(a.l("The date pattern '", str, "' is not valid"), e3);
            }
        }
        this.datePattern = str;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setExclusionStrategies(ExclusionStrategy... exclusionStrategyArr) {
        Objects.requireNonNull(exclusionStrategyArr);
        for (ExclusionStrategy withExclusionStrategy : exclusionStrategyArr) {
            this.excluder = this.excluder.withExclusionStrategy(withExclusionStrategy, true, true);
        }
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy2) {
        return setFieldNamingStrategy(fieldNamingPolicy2);
    }

    @CanIgnoreReturnValue
    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        Objects.requireNonNull(fieldNamingStrategy);
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setFormattingStyle(FormattingStyle formattingStyle2) {
        Objects.requireNonNull(formattingStyle2);
        this.formattingStyle = formattingStyle2;
        return this;
    }

    @InlineMe(imports = {"com.google.gson.Strictness"}, replacement = "this.setStrictness(Strictness.LENIENT)")
    @CanIgnoreReturnValue
    @Deprecated
    public GsonBuilder setLenient() {
        return setStrictness(Strictness.LENIENT);
    }

    @CanIgnoreReturnValue
    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy longSerializationPolicy2) {
        Objects.requireNonNull(longSerializationPolicy2);
        this.longSerializationPolicy = longSerializationPolicy2;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy toNumberStrategy) {
        Objects.requireNonNull(toNumberStrategy);
        this.numberToNumberStrategy = toNumberStrategy;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy toNumberStrategy) {
        Objects.requireNonNull(toNumberStrategy);
        this.objectToNumberStrategy = toNumberStrategy;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setPrettyPrinting() {
        return setFormattingStyle(FormattingStyle.PRETTY);
    }

    @CanIgnoreReturnValue
    public GsonBuilder setStrictness(Strictness strictness2) {
        Objects.requireNonNull(strictness2);
        this.strictness = strictness2;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setVersion(double d2) {
        if (Double.isNaN(d2) || d2 < 0.0d) {
            throw new IllegalArgumentException("Invalid version: " + d2);
        }
        this.excluder = this.excluder.withVersion(d2);
        return this;
    }

    @CanIgnoreReturnValue
    @Deprecated
    public GsonBuilder setDateFormat(int i3) {
        this.dateStyle = checkDateFormatStyle(i3);
        this.datePattern = null;
        return this;
    }

    @CanIgnoreReturnValue
    public GsonBuilder setDateFormat(int i3, int i4) {
        this.dateStyle = checkDateFormatStyle(i3);
        this.timeStyle = checkDateFormatStyle(i4);
        this.datePattern = null;
        return this;
    }

    public GsonBuilder(Gson gson) {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
        HashMap hashMap = new HashMap();
        this.instanceCreators = hashMap;
        ArrayList arrayList = new ArrayList();
        this.factories = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.hierarchyFactories = arrayList2;
        this.serializeNulls = false;
        this.datePattern = Gson.DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = Gson.DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = Gson.DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = Gson.DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = Gson.DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        ArrayDeque<ReflectionAccessFilter> arrayDeque = new ArrayDeque<>();
        this.reflectionFilters = arrayDeque;
        this.excluder = gson.excluder;
        this.fieldNamingPolicy = gson.fieldNamingStrategy;
        hashMap.putAll(gson.instanceCreators);
        this.serializeNulls = gson.serializeNulls;
        this.complexMapKeySerialization = gson.complexMapKeySerialization;
        this.generateNonExecutableJson = gson.generateNonExecutableJson;
        this.escapeHtmlChars = gson.htmlSafe;
        this.formattingStyle = gson.formattingStyle;
        this.strictness = gson.strictness;
        this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson.longSerializationPolicy;
        this.datePattern = gson.datePattern;
        this.dateStyle = gson.dateStyle;
        this.timeStyle = gson.timeStyle;
        arrayList.addAll(gson.builderFactories);
        arrayList2.addAll(gson.builderHierarchyFactories);
        this.useJdkUnsafe = gson.useJdkUnsafe;
        this.objectToNumberStrategy = gson.objectToNumberStrategy;
        this.numberToNumberStrategy = gson.numberToNumberStrategy;
        arrayDeque.addAll(gson.reflectionFilters);
    }
}
