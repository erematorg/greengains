package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.ByteBufferSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.InetSocketAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.ser.std.ToEmptyObjectSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicSerializerFactory extends SerializerFactory implements Serializable {
    protected static final HashMap<String, JsonSerializer<?>> _concrete;
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy;
    protected final SerializerFactoryConfig _factoryConfig;

    /* renamed from: com.fasterxml.jackson.databind.ser.BasicSerializerFactory$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|19|20|21|22|23|24|26) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0064 */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonInclude$Include[] r0 = com.fasterxml.jackson.annotation.JsonInclude.Include.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = r0
                r1 = 1
                com.fasterxml.jackson.annotation.JsonInclude$Include r2 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonInclude$Include r3 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r3 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.CUSTOM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r3 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r3 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                com.fasterxml.jackson.annotation.JsonFormat$Shape[] r3 = com.fasterxml.jackson.annotation.JsonFormat.Shape.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = r3
                com.fasterxml.jackson.annotation.JsonFormat$Shape r4 = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING     // Catch:{ NoSuchFieldError -> 0x005a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                int[] r1 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x0064 }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r3 = com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x006e }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.AnonymousClass1.<clinit>():void");
        }
    }

    static {
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap = new HashMap<>();
        HashMap<String, JsonSerializer<?>> hashMap2 = new HashMap<>();
        hashMap2.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        hashMap2.put(StringBuffer.class.getName(), toStringSerializer);
        hashMap2.put(StringBuilder.class.getName(), toStringSerializer);
        hashMap2.put(Character.class.getName(), toStringSerializer);
        hashMap2.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers.addAll(hashMap2);
        hashMap2.put(Boolean.TYPE.getName(), new BooleanSerializer(true));
        hashMap2.put(Boolean.class.getName(), new BooleanSerializer(false));
        Class<BigInteger> cls = BigInteger.class;
        hashMap2.put(cls.getName(), new NumberSerializer(cls));
        Class<BigDecimal> cls2 = BigDecimal.class;
        hashMap2.put(cls2.getName(), new NumberSerializer(cls2));
        hashMap2.put(Calendar.class.getName(), CalendarSerializer.instance);
        hashMap2.put(Date.class.getName(), DateSerializer.instance);
        for (Map.Entry next : StdJdkSerializers.all()) {
            Object value = next.getValue();
            if (value instanceof JsonSerializer) {
                hashMap2.put(((Class) next.getKey()).getName(), (JsonSerializer) value);
            } else {
                hashMap.put(((Class) next.getKey()).getName(), (Class) value);
            }
        }
        hashMap.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
        _concrete = hashMap2;
        _concreteLazy = hashMap;
    }

    public BasicSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        this._factoryConfig = serializerFactoryConfig == null ? new SerializerFactoryConfig() : serializerFactoryConfig;
    }

    public MapSerializer _checkMapContentInclusion(SerializerProvider serializerProvider, BeanDescription beanDescription, MapSerializer mapSerializer) throws JsonMappingException {
        JavaType contentType = mapSerializer.getContentType();
        JsonInclude.Value _findInclusionWithContent = _findInclusionWithContent(serializerProvider, beanDescription, contentType, Map.class);
        JsonInclude.Include contentInclusion = _findInclusionWithContent == null ? JsonInclude.Include.USE_DEFAULTS : _findInclusionWithContent.getContentInclusion();
        Object obj = null;
        boolean z2 = true;
        if (contentInclusion == JsonInclude.Include.USE_DEFAULTS || contentInclusion == JsonInclude.Include.ALWAYS) {
            return !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? mapSerializer.withContentInclusion((Object) null, true) : mapSerializer;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[contentInclusion.ordinal()];
        if (i3 == 1) {
            obj = BeanUtil.getDefaultValue(contentType);
            if (obj != null && obj.getClass().isArray()) {
                obj = ArrayBuilders.getArrayComparator(obj);
            }
        } else if (i3 != 2) {
            if (i3 == 3) {
                obj = MapSerializer.MARKER_FOR_EMPTY;
            } else if (i3 == 4 && (obj = serializerProvider.includeFilterInstance((BeanPropertyDefinition) null, _findInclusionWithContent.getContentFilter())) != null) {
                z2 = serializerProvider.includeFilterSuppressNulls(obj);
            }
        } else if (contentType.isReferenceType()) {
            obj = MapSerializer.MARKER_FOR_EMPTY;
        }
        return mapSerializer.withContentInclusion(obj, z2);
    }

    public JsonSerializer<Object> _findContentSerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(annotated);
        if (findContentSerializer != null) {
            return serializerProvider.serializerInstance(annotated, findContentSerializer);
        }
        return null;
    }

    public JsonInclude.Value _findInclusionWithContent(SerializerProvider serializerProvider, BeanDescription beanDescription, JavaType javaType, Class<?> cls) throws JsonMappingException {
        SerializationConfig config = serializerProvider.getConfig();
        JsonInclude.Value defaultPropertyInclusion = config.getDefaultPropertyInclusion(cls, beanDescription.findPropertyInclusion(config.getDefaultPropertyInclusion()));
        JsonInclude.Value defaultPropertyInclusion2 = config.getDefaultPropertyInclusion(javaType.getRawClass(), (JsonInclude.Value) null);
        if (defaultPropertyInclusion2 == null) {
            return defaultPropertyInclusion;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[defaultPropertyInclusion2.getValueInclusion().ordinal()];
        return i3 != 4 ? i3 != 6 ? defaultPropertyInclusion.withContentInclusion(defaultPropertyInclusion2.getValueInclusion()) : defaultPropertyInclusion : defaultPropertyInclusion.withContentFilter(defaultPropertyInclusion2.getContentFilter());
    }

    public JsonSerializer<Object> _findKeySerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findKeySerializer = serializerProvider.getAnnotationIntrospector().findKeySerializer(annotated);
        if (findKeySerializer != null) {
            return serializerProvider.serializerInstance(annotated, findKeySerializer);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x000d A[LOOP:0: B:1:0x000d->B:4:0x0022, LOOP_START, PHI: r0 
      PHI: (r0v2 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r0v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r0v15 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:0:0x0000, B:4:0x0022] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildArraySerializer(com.fasterxml.jackson.databind.SerializerProvider r8, com.fasterxml.jackson.databind.type.ArrayType r9, com.fasterxml.jackson.databind.BeanDescription r10, boolean r11, com.fasterxml.jackson.databind.jsontype.TypeSerializer r12, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r13) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r7 = this;
            com.fasterxml.jackson.databind.SerializationConfig r8 = r8.getConfig()
            java.lang.Iterable r0 = r7.customSerializers()
            java.util.Iterator r6 = r0.iterator()
            r0 = 0
        L_0x000d:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0024
            java.lang.Object r0 = r6.next()
            com.fasterxml.jackson.databind.ser.Serializers r0 = (com.fasterxml.jackson.databind.ser.Serializers) r0
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r12
            r5 = r13
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.findArraySerializer(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000d
        L_0x0024:
            if (r0 != 0) goto L_0x0048
            java.lang.Class r1 = r9.getRawClass()
            if (r13 == 0) goto L_0x0032
            boolean r2 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r13)
            if (r2 == 0) goto L_0x003d
        L_0x0032:
            java.lang.Class<java.lang.String[]> r0 = java.lang.String[].class
            if (r0 != r1) goto L_0x0039
            com.fasterxml.jackson.databind.ser.impl.StringArraySerializer r0 = com.fasterxml.jackson.databind.ser.impl.StringArraySerializer.instance
            goto L_0x003d
        L_0x0039:
            com.fasterxml.jackson.databind.JsonSerializer r0 = com.fasterxml.jackson.databind.ser.std.StdArraySerializers.findStandardImpl(r1)
        L_0x003d:
            if (r0 != 0) goto L_0x0048
            com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r0 = new com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer
            com.fasterxml.jackson.databind.JavaType r1 = r9.getContentType()
            r0.<init>(r1, r11, r12, r13)
        L_0x0048:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r11 = r7._factoryConfig
            boolean r11 = r11.hasSerializerModifiers()
            if (r11 == 0) goto L_0x006b
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r7 = r7._factoryConfig
            java.lang.Iterable r7 = r7.serializerModifiers()
            java.util.Iterator r7 = r7.iterator()
        L_0x005a:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x006b
            java.lang.Object r11 = r7.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r11 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r11
            com.fasterxml.jackson.databind.JsonSerializer r0 = r11.modifyArraySerializer(r8, r9, r10, r0)
            goto L_0x005a
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildArraySerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.ArrayType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JsonSerializer<?> buildAtomicReferenceSerializer(SerializerProvider serializerProvider, ReferenceType referenceType, BeanDescription beanDescription, boolean z2, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        boolean z3;
        JavaType referencedType = referenceType.getReferencedType();
        JsonInclude.Value _findInclusionWithContent = _findInclusionWithContent(serializerProvider, beanDescription, referencedType, AtomicReference.class);
        JsonInclude.Include contentInclusion = _findInclusionWithContent == null ? JsonInclude.Include.USE_DEFAULTS : _findInclusionWithContent.getContentInclusion();
        Object obj = null;
        if (contentInclusion == JsonInclude.Include.USE_DEFAULTS || contentInclusion == JsonInclude.Include.ALWAYS) {
            z3 = false;
        } else {
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[contentInclusion.ordinal()];
            z3 = true;
            if (i3 == 1) {
                obj = BeanUtil.getDefaultValue(referencedType);
                if (obj != null && obj.getClass().isArray()) {
                    obj = ArrayBuilders.getArrayComparator(obj);
                }
            } else if (i3 != 2) {
                if (i3 == 3) {
                    obj = MapSerializer.MARKER_FOR_EMPTY;
                } else if (i3 == 4 && (obj = serializerProvider.includeFilterInstance((BeanPropertyDefinition) null, _findInclusionWithContent.getContentFilter())) != null) {
                    z3 = serializerProvider.includeFilterSuppressNulls(obj);
                }
            } else if (referencedType.isReferenceType()) {
                obj = MapSerializer.MARKER_FOR_EMPTY;
            }
        }
        return new AtomicReferenceSerializer(referenceType, z2, typeSerializer, jsonSerializer).withContentInclusion(obj, z3);
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>, com.fasterxml.jackson.databind.JsonSerializer, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x000e A[LOOP:0: B:1:0x000e->B:4:0x0023, LOOP_START, PHI: r0 
      PHI: (r0v2 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r0v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r0v15 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:0:0x0000, B:4:0x0023] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0089  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildCollectionSerializer(com.fasterxml.jackson.databind.SerializerProvider r10, com.fasterxml.jackson.databind.type.CollectionType r11, com.fasterxml.jackson.databind.BeanDescription r12, boolean r13, com.fasterxml.jackson.databind.jsontype.TypeSerializer r14, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r15) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.SerializationConfig r6 = r10.getConfig()
            java.lang.Iterable r0 = r9.customSerializers()
            java.util.Iterator r7 = r0.iterator()
            r8 = 0
            r0 = r8
        L_0x000e:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0025
            java.lang.Object r0 = r7.next()
            com.fasterxml.jackson.databind.ser.Serializers r0 = (com.fasterxml.jackson.databind.ser.Serializers) r0
            r1 = r6
            r2 = r11
            r3 = r12
            r4 = r14
            r5 = r15
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.findCollectionSerializer(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000e
        L_0x0025:
            if (r0 != 0) goto L_0x0091
            com.fasterxml.jackson.databind.JsonSerializer r0 = r9.findSerializerByAnnotations(r10, r11, r12)
            if (r0 != 0) goto L_0x0091
            com.fasterxml.jackson.annotation.JsonFormat$Value r10 = r12.findExpectedFormat(r8)
            com.fasterxml.jackson.annotation.JsonFormat$Shape r10 = r10.getShape()
            com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT
            if (r10 != r1) goto L_0x003a
            return r8
        L_0x003a:
            java.lang.Class r10 = r11.getRawClass()
            java.lang.Class<java.util.EnumSet> r1 = java.util.EnumSet.class
            boolean r1 = r1.isAssignableFrom(r10)
            if (r1 == 0) goto L_0x0057
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            boolean r13 = r10.isEnumImplType()
            if (r13 != 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r8 = r10
        L_0x0052:
            com.fasterxml.jackson.databind.JsonSerializer r0 = r9.buildEnumSetSerializer(r8)
            goto L_0x0091
        L_0x0057:
            com.fasterxml.jackson.databind.JavaType r1 = r11.getContentType()
            java.lang.Class r1 = r1.getRawClass()
            boolean r10 = r9.isIndexedList(r10)
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            if (r10 == 0) goto L_0x007c
            if (r1 != r2) goto L_0x0073
            boolean r10 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r15)
            if (r10 == 0) goto L_0x0087
            com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer r10 = com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer.instance
        L_0x0071:
            r0 = r10
            goto L_0x0087
        L_0x0073:
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            com.fasterxml.jackson.databind.ser.ContainerSerializer r10 = r9.buildIndexedListSerializer(r10, r13, r14, r15)
            goto L_0x0071
        L_0x007c:
            if (r1 != r2) goto L_0x0087
            boolean r10 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r15)
            if (r10 == 0) goto L_0x0087
            com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer r10 = com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer.instance
            goto L_0x0071
        L_0x0087:
            if (r0 != 0) goto L_0x0091
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            com.fasterxml.jackson.databind.ser.ContainerSerializer r0 = r9.buildCollectionSerializer(r10, r13, r14, r15)
        L_0x0091:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r10 = r9._factoryConfig
            boolean r10 = r10.hasSerializerModifiers()
            if (r10 == 0) goto L_0x00b4
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r9 = r9._factoryConfig
            java.lang.Iterable r9 = r9.serializerModifiers()
            java.util.Iterator r9 = r9.iterator()
        L_0x00a3:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00b4
            java.lang.Object r10 = r9.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r10 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r10
            com.fasterxml.jackson.databind.JsonSerializer r0 = r10.modifyCollectionSerializer(r6, r11, r12, r0)
            goto L_0x00a3
        L_0x00b4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildCollectionSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.CollectionType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a A[LOOP:0: B:20:0x006a->B:23:0x0081, LOOP_START, PHI: r3 
      PHI: (r3v13 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r3v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r3v21 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:19:0x0062, B:23:0x0081] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d5 A[LOOP:2: B:41:0x00d5->B:44:0x00eb, LOOP_START, PHI: r3 
      PHI: (r3v3 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r3v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r3v11 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:40:0x00cd, B:44:0x00eb] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildContainerSerializer(com.fasterxml.jackson.databind.SerializerProvider r17, com.fasterxml.jackson.databind.JavaType r18, com.fasterxml.jackson.databind.BeanDescription r19, boolean r20) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r9 = r19
            com.fasterxml.jackson.databind.SerializationConfig r10 = r17.getConfig()
            if (r20 != 0) goto L_0x0024
            boolean r2 = r18.useStaticType()
            if (r2 == 0) goto L_0x0024
            boolean r2 = r18.isContainerType()
            if (r2 == 0) goto L_0x0022
            com.fasterxml.jackson.databind.JavaType r2 = r18.getContentType()
            boolean r2 = r2.isJavaLangObject()
            if (r2 != 0) goto L_0x0024
        L_0x0022:
            r2 = 1
            goto L_0x0026
        L_0x0024:
            r2 = r20
        L_0x0026:
            com.fasterxml.jackson.databind.JavaType r3 = r18.getContentType()
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r11 = r0.createTypeSerializer(r10, r3)
            if (r11 == 0) goto L_0x0031
            r2 = 0
        L_0x0031:
            r4 = r2
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r19.getClassInfo()
            com.fasterxml.jackson.databind.JsonSerializer r12 = r0._findContentSerializer(r1, r2)
            boolean r2 = r18.isMapLikeType()
            r3 = 0
            if (r2 == 0) goto L_0x00af
            r13 = r18
            com.fasterxml.jackson.databind.type.MapLikeType r13 = (com.fasterxml.jackson.databind.type.MapLikeType) r13
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r19.getClassInfo()
            com.fasterxml.jackson.databind.JsonSerializer r14 = r0._findKeySerializer(r1, r2)
            boolean r2 = r13 instanceof com.fasterxml.jackson.databind.type.MapType
            if (r2 == 0) goto L_0x0062
            r2 = r13
            com.fasterxml.jackson.databind.type.MapType r2 = (com.fasterxml.jackson.databind.type.MapType) r2
            r0 = r16
            r1 = r17
            r3 = r19
            r5 = r14
            r6 = r11
            r7 = r12
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildMapSerializer(r1, r2, r3, r4, r5, r6, r7)
            return r0
        L_0x0062:
            java.lang.Iterable r2 = r16.customSerializers()
            java.util.Iterator r15 = r2.iterator()
        L_0x006a:
            boolean r2 = r15.hasNext()
            if (r2 == 0) goto L_0x0083
            java.lang.Object r2 = r15.next()
            com.fasterxml.jackson.databind.ser.Serializers r2 = (com.fasterxml.jackson.databind.ser.Serializers) r2
            r3 = r10
            r4 = r13
            r5 = r19
            r6 = r14
            r7 = r11
            r8 = r12
            com.fasterxml.jackson.databind.JsonSerializer r3 = r2.findMapLikeSerializer(r3, r4, r5, r6, r7, r8)
            if (r3 == 0) goto L_0x006a
        L_0x0083:
            if (r3 != 0) goto L_0x0089
            com.fasterxml.jackson.databind.JsonSerializer r3 = r16.findSerializerByAnnotations(r17, r18, r19)
        L_0x0089:
            if (r3 == 0) goto L_0x00ae
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r0._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x00ae
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r0 = r0._factoryConfig
            java.lang.Iterable r0 = r0.serializerModifiers()
            java.util.Iterator r0 = r0.iterator()
        L_0x009d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00ae
            java.lang.Object r1 = r0.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r1 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r1
            com.fasterxml.jackson.databind.JsonSerializer r3 = r1.modifyMapLikeSerializer(r10, r13, r9, r3)
            goto L_0x009d
        L_0x00ae:
            return r3
        L_0x00af:
            boolean r2 = r18.isCollectionLikeType()
            if (r2 == 0) goto L_0x0119
            r8 = r18
            com.fasterxml.jackson.databind.type.CollectionLikeType r8 = (com.fasterxml.jackson.databind.type.CollectionLikeType) r8
            boolean r2 = r8 instanceof com.fasterxml.jackson.databind.type.CollectionType
            if (r2 == 0) goto L_0x00cd
            r2 = r8
            com.fasterxml.jackson.databind.type.CollectionType r2 = (com.fasterxml.jackson.databind.type.CollectionType) r2
            r0 = r16
            r1 = r17
            r3 = r19
            r5 = r11
            r6 = r12
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildCollectionSerializer(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x00cd:
            java.lang.Iterable r2 = r16.customSerializers()
            java.util.Iterator r13 = r2.iterator()
        L_0x00d5:
            boolean r2 = r13.hasNext()
            if (r2 == 0) goto L_0x00ed
            java.lang.Object r2 = r13.next()
            com.fasterxml.jackson.databind.ser.Serializers r2 = (com.fasterxml.jackson.databind.ser.Serializers) r2
            r3 = r10
            r4 = r8
            r5 = r19
            r6 = r11
            r7 = r12
            com.fasterxml.jackson.databind.JsonSerializer r3 = r2.findCollectionLikeSerializer(r3, r4, r5, r6, r7)
            if (r3 == 0) goto L_0x00d5
        L_0x00ed:
            if (r3 != 0) goto L_0x00f3
            com.fasterxml.jackson.databind.JsonSerializer r3 = r16.findSerializerByAnnotations(r17, r18, r19)
        L_0x00f3:
            if (r3 == 0) goto L_0x0118
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r0._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x0118
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r0 = r0._factoryConfig
            java.lang.Iterable r0 = r0.serializerModifiers()
            java.util.Iterator r0 = r0.iterator()
        L_0x0107:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0118
            java.lang.Object r1 = r0.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r1 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r1
            com.fasterxml.jackson.databind.JsonSerializer r3 = r1.modifyCollectionLikeSerializer(r10, r8, r9, r3)
            goto L_0x0107
        L_0x0118:
            return r3
        L_0x0119:
            boolean r2 = r18.isArrayType()
            if (r2 == 0) goto L_0x0130
            r2 = r18
            com.fasterxml.jackson.databind.type.ArrayType r2 = (com.fasterxml.jackson.databind.type.ArrayType) r2
            r0 = r16
            r1 = r17
            r3 = r19
            r5 = r11
            r6 = r12
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildArraySerializer(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0130:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildContainerSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.BeanDescription, boolean):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JsonSerializer<?> buildEnumSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonFormat.Value findExpectedFormat = beanDescription.findExpectedFormat((JsonFormat.Value) null);
        if (findExpectedFormat.getShape() == JsonFormat.Shape.OBJECT) {
            ((BasicBeanDescription) beanDescription).removeProperty("declaringClass");
            return null;
        }
        JsonSerializer<?> construct = EnumSerializer.construct(javaType.getRawClass(), serializationConfig, beanDescription, findExpectedFormat);
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyEnumSerializer : this._factoryConfig.serializerModifiers()) {
                construct = modifyEnumSerializer.modifyEnumSerializer(serializationConfig, javaType, beanDescription, construct);
            }
        }
        return construct;
    }

    public JsonSerializer<?> buildEnumSetSerializer(JavaType javaType) {
        return new EnumSetSerializer(javaType);
    }

    public ContainerSerializer<?> buildIndexedListSerializer(JavaType javaType, boolean z2, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z2, typeSerializer, jsonSerializer);
    }

    public JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z2, JavaType javaType2) throws JsonMappingException {
        return new IterableSerializer(javaType2, z2, createTypeSerializer(serializationConfig, javaType2));
    }

    public JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z2, JavaType javaType2) throws JsonMappingException {
        return new IteratorSerializer(javaType2, z2, createTypeSerializer(serializationConfig, javaType2));
    }

    public JsonSerializer<?> buildMapEntrySerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z2, JavaType javaType2, JavaType javaType3) throws JsonMappingException {
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanDescription beanDescription2 = beanDescription;
        Class<Map.Entry> cls = Map.Entry.class;
        Object obj = null;
        if (JsonFormat.Value.merge(beanDescription2.findExpectedFormat((JsonFormat.Value) null), serializerProvider.getDefaultPropertyFormat(cls)).getShape() == JsonFormat.Shape.OBJECT) {
            return null;
        }
        JavaType javaType4 = javaType3;
        MapEntrySerializer mapEntrySerializer = new MapEntrySerializer(javaType3, javaType2, javaType4, z2, createTypeSerializer(serializerProvider.getConfig(), javaType4), (BeanProperty) null);
        JavaType contentType = mapEntrySerializer.getContentType();
        JsonInclude.Value _findInclusionWithContent = _findInclusionWithContent(serializerProvider, beanDescription2, contentType, cls);
        JsonInclude.Include contentInclusion = _findInclusionWithContent == null ? JsonInclude.Include.USE_DEFAULTS : _findInclusionWithContent.getContentInclusion();
        if (contentInclusion == JsonInclude.Include.USE_DEFAULTS || contentInclusion == JsonInclude.Include.ALWAYS) {
            return mapEntrySerializer;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[contentInclusion.ordinal()];
        boolean z3 = true;
        if (i3 == 1) {
            obj = BeanUtil.getDefaultValue(contentType);
            if (obj != null && obj.getClass().isArray()) {
                obj = ArrayBuilders.getArrayComparator(obj);
            }
        } else if (i3 != 2) {
            if (i3 == 3) {
                obj = MapSerializer.MARKER_FOR_EMPTY;
            } else if (i3 == 4 && (obj = serializerProvider.includeFilterInstance((BeanPropertyDefinition) null, _findInclusionWithContent.getContentFilter())) != null) {
                z3 = serializerProvider.includeFilterSuppressNulls(obj);
            }
        } else if (contentType.isReferenceType()) {
            obj = MapSerializer.MARKER_FOR_EMPTY;
        }
        return mapEntrySerializer.withContentInclusion(obj, z3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x001f A[LOOP:0: B:4:0x001f->B:7:0x003a, LOOP_START, PHI: r1 
      PHI: (r1v4 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r1v3 com.fasterxml.jackson.databind.JsonSerializer<?>), (r1v20 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:3:0x0012, B:7:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildMapSerializer(com.fasterxml.jackson.databind.SerializerProvider r20, com.fasterxml.jackson.databind.type.MapType r21, com.fasterxml.jackson.databind.BeanDescription r22, boolean r23, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r24, com.fasterxml.jackson.databind.jsontype.TypeSerializer r25, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r26) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r19 = this;
            r0 = r19
            r8 = r22
            r9 = 0
            com.fasterxml.jackson.annotation.JsonFormat$Value r1 = r8.findExpectedFormat(r9)
            com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = r1.getShape()
            com.fasterxml.jackson.annotation.JsonFormat$Shape r2 = com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT
            if (r1 != r2) goto L_0x0012
            return r9
        L_0x0012:
            com.fasterxml.jackson.databind.SerializationConfig r10 = r20.getConfig()
            java.lang.Iterable r1 = r19.customSerializers()
            java.util.Iterator r11 = r1.iterator()
            r1 = r9
        L_0x001f:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L_0x003c
            java.lang.Object r1 = r11.next()
            com.fasterxml.jackson.databind.ser.Serializers r1 = (com.fasterxml.jackson.databind.ser.Serializers) r1
            r2 = r10
            r3 = r21
            r4 = r22
            r5 = r24
            r6 = r25
            r7 = r26
            com.fasterxml.jackson.databind.JsonSerializer r1 = r1.findMapSerializer(r2, r3, r4, r5, r6, r7)
            if (r1 == 0) goto L_0x001f
        L_0x003c:
            if (r1 != 0) goto L_0x0080
            com.fasterxml.jackson.databind.JsonSerializer r1 = r19.findSerializerByAnnotations(r20, r21, r22)
            if (r1 != 0) goto L_0x0080
            java.lang.Object r18 = r0.findFilterId(r10, r8)
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r22.getClassInfo()
            java.lang.Class<java.util.Map> r2 = java.util.Map.class
            com.fasterxml.jackson.annotation.JsonIgnoreProperties$Value r1 = r10.getDefaultPropertyIgnorals(r2, r1)
            if (r1 != 0) goto L_0x0056
            r11 = r9
            goto L_0x005b
        L_0x0056:
            java.util.Set r1 = r1.findIgnoredForSerialization()
            r11 = r1
        L_0x005b:
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r22.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIncludeProperties$Value r1 = r10.getDefaultPropertyInclusions(r2, r1)
            if (r1 != 0) goto L_0x0067
        L_0x0065:
            r12 = r9
            goto L_0x006c
        L_0x0067:
            java.util.Set r9 = r1.getIncluded()
            goto L_0x0065
        L_0x006c:
            r13 = r21
            r14 = r23
            r15 = r25
            r16 = r24
            r17 = r26
            com.fasterxml.jackson.databind.ser.std.MapSerializer r1 = com.fasterxml.jackson.databind.ser.std.MapSerializer.construct(r11, r12, r13, r14, r15, r16, r17, r18)
            r2 = r20
            com.fasterxml.jackson.databind.ser.std.MapSerializer r1 = r0._checkMapContentInclusion(r2, r8, r1)
        L_0x0080:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r2 = r0._factoryConfig
            boolean r2 = r2.hasSerializerModifiers()
            if (r2 == 0) goto L_0x00a5
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r0 = r0._factoryConfig
            java.lang.Iterable r0 = r0.serializerModifiers()
            java.util.Iterator r0 = r0.iterator()
        L_0x0092:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00a5
            java.lang.Object r2 = r0.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r2 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r2
            r3 = r21
            com.fasterxml.jackson.databind.JsonSerializer r1 = r2.modifyMapSerializer(r10, r3, r8, r1)
            goto L_0x0092
        L_0x00a5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildMapSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001c A[LOOP:0: B:3:0x001c->B:6:0x002c, LOOP_START, PHI: r4 
      PHI: (r4v7 com.fasterxml.jackson.databind.JsonSerializer) = (r4v6 com.fasterxml.jackson.databind.JsonSerializer), (r4v10 com.fasterxml.jackson.databind.JsonSerializer) binds: [B:2:0x0011, B:6:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> createKeySerializer(com.fasterxml.jackson.databind.SerializerProvider r7, com.fasterxml.jackson.databind.JavaType r8, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r9) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r6 = this;
            com.fasterxml.jackson.databind.SerializationConfig r0 = r7.getConfig()
            com.fasterxml.jackson.databind.BeanDescription r1 = r0.introspect(r8)
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r2 = r6._factoryConfig
            boolean r2 = r2.hasKeySerializers()
            r3 = 0
            if (r2 == 0) goto L_0x002f
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r2 = r6._factoryConfig
            java.lang.Iterable r2 = r2.keySerializers()
            java.util.Iterator r2 = r2.iterator()
            r4 = r3
        L_0x001c:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0030
            java.lang.Object r4 = r2.next()
            com.fasterxml.jackson.databind.ser.Serializers r4 = (com.fasterxml.jackson.databind.ser.Serializers) r4
            com.fasterxml.jackson.databind.JsonSerializer r4 = r4.findSerializer(r0, r8, r1)
            if (r4 == 0) goto L_0x001c
            goto L_0x0030
        L_0x002f:
            r4 = r3
        L_0x0030:
            if (r4 != 0) goto L_0x0081
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r1.getClassInfo()
            com.fasterxml.jackson.databind.JsonSerializer r2 = r6._findKeySerializer(r7, r2)
            if (r2 != 0) goto L_0x007f
            if (r9 != 0) goto L_0x0082
            java.lang.Class r2 = r8.getRawClass()
            r4 = 0
            com.fasterxml.jackson.databind.JsonSerializer r2 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getStdKeySerializer(r0, r2, r4)
            if (r2 != 0) goto L_0x007f
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r1.findJsonKeyAccessor()
            if (r2 != 0) goto L_0x0053
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r1.findJsonValueAccessor()
        L_0x0053:
            if (r2 == 0) goto L_0x0076
            com.fasterxml.jackson.databind.JavaType r4 = r2.getType()
            com.fasterxml.jackson.databind.JsonSerializer r7 = r6.createKeySerializer((com.fasterxml.jackson.databind.SerializerProvider) r7, (com.fasterxml.jackson.databind.JavaType) r4, (com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>) r9)
            boolean r9 = r0.canOverrideAccessModifiers()
            if (r9 == 0) goto L_0x0070
            java.lang.reflect.Member r9 = r2.getMember()
            com.fasterxml.jackson.databind.MapperFeature r4 = com.fasterxml.jackson.databind.MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS
            boolean r4 = r0.isEnabled(r4)
            com.fasterxml.jackson.databind.util.ClassUtil.checkAndFixAccess(r9, r4)
        L_0x0070:
            com.fasterxml.jackson.databind.ser.std.JsonValueSerializer r9 = new com.fasterxml.jackson.databind.ser.std.JsonValueSerializer
            r9.<init>(r2, r3, r7)
            goto L_0x0082
        L_0x0076:
            java.lang.Class r7 = r8.getRawClass()
            com.fasterxml.jackson.databind.JsonSerializer r9 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getFallbackKeySerializer(r0, r7)
            goto L_0x0082
        L_0x007f:
            r9 = r2
            goto L_0x0082
        L_0x0081:
            r9 = r4
        L_0x0082:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r7 = r6._factoryConfig
            boolean r7 = r7.hasSerializerModifiers()
            if (r7 == 0) goto L_0x00a5
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r6 = r6._factoryConfig
            java.lang.Iterable r6 = r6.serializerModifiers()
            java.util.Iterator r6 = r6.iterator()
        L_0x0094:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00a5
            java.lang.Object r7 = r6.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r7 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r7
            com.fasterxml.jackson.databind.JsonSerializer r9 = r7.modifyKeySerializer(r0, r8, r1, r9)
            goto L_0x0094
        L_0x00a5:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.createKeySerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) throws JsonMappingException;

    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        Collection<NamedType> collection;
        AnnotatedClass classInfo = serializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> findTypeResolver = serializationConfig.getAnnotationIntrospector().findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collection = null;
        } else {
            collection = serializationConfig.getSubtypeResolver().collectAndResolveSubtypesByClass(serializationConfig, classInfo);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collection);
    }

    public abstract Iterable<Serializers> customSerializers();

    public Converter<Object, Object> findConverter(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializationConverter = serializerProvider.getAnnotationIntrospector().findSerializationConverter(annotated);
        if (findSerializationConverter == null) {
            return null;
        }
        return serializerProvider.converterInstance(annotated, findSerializationConverter);
    }

    public JsonSerializer<?> findConvertingSerializer(SerializerProvider serializerProvider, Annotated annotated, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        Converter<Object, Object> findConverter = findConverter(serializerProvider, annotated);
        return findConverter == null ? jsonSerializer : new StdDelegatingSerializer(findConverter, findConverter.getOutputType(serializerProvider.getTypeFactory()), jsonSerializer);
    }

    public Object findFilterId(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(beanDescription.getClassInfo());
    }

    public JsonSerializer<?> findOptionalStdSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z2) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findSerializer(serializerProvider.getConfig(), javaType, beanDescription);
    }

    public JsonSerializer<?> findReferenceSerializer(SerializerProvider serializerProvider, ReferenceType referenceType, BeanDescription beanDescription, boolean z2) throws JsonMappingException {
        JavaType contentType = referenceType.getContentType();
        TypeSerializer typeSerializer = (TypeSerializer) contentType.getTypeHandler();
        SerializationConfig config = serializerProvider.getConfig();
        if (typeSerializer == null) {
            typeSerializer = createTypeSerializer(config, contentType);
        }
        TypeSerializer typeSerializer2 = typeSerializer;
        JsonSerializer jsonSerializer = (JsonSerializer) contentType.getValueHandler();
        for (Serializers findReferenceSerializer : customSerializers()) {
            JsonSerializer<?> findReferenceSerializer2 = findReferenceSerializer.findReferenceSerializer(config, referenceType, beanDescription, typeSerializer2, jsonSerializer);
            if (findReferenceSerializer2 != null) {
                return findReferenceSerializer2;
            }
        }
        if (referenceType.isTypeOrSubTypeOf(AtomicReference.class)) {
            return buildAtomicReferenceSerializer(serializerProvider, referenceType, beanDescription, z2, typeSerializer2, jsonSerializer);
        }
        return null;
    }

    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z2) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        Class<Iterator> cls = Iterator.class;
        if (cls.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) cls);
            return buildIteratorSerializer(serializationConfig, javaType, beanDescription, z2, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]);
        }
        Class<Iterable> cls2 = Iterable.class;
        if (cls2.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters2 = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) cls2);
            return buildIterableSerializer(serializationConfig, javaType, beanDescription, z2, (findTypeParameters2 == null || findTypeParameters2.length != 1) ? TypeFactory.unknownType() : findTypeParameters2[0]);
        } else if (CharSequence.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        } else {
            return null;
        }
    }

    public final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        if (JsonSerializable.class.isAssignableFrom(javaType.getRawClass())) {
            return SerializableSerializer.instance;
        }
        AnnotatedMember findJsonValueAccessor = beanDescription.findJsonValueAccessor();
        if (findJsonValueAccessor == null) {
            return null;
        }
        if (serializerProvider.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findJsonValueAccessor.getMember(), serializerProvider.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        JavaType type = findJsonValueAccessor.getType();
        JsonSerializer<Object> findSerializerFromAnnotation = findSerializerFromAnnotation(serializerProvider, findJsonValueAccessor);
        if (findSerializerFromAnnotation == null) {
            findSerializerFromAnnotation = (JsonSerializer) type.getValueHandler();
        }
        TypeSerializer typeSerializer = (TypeSerializer) type.getTypeHandler();
        if (typeSerializer == null) {
            typeSerializer = createTypeSerializer(serializerProvider.getConfig(), type);
        }
        return new JsonValueSerializer(findJsonValueAccessor, typeSerializer, findSerializerFromAnnotation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r0 = _concreteLazy.get(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonSerializer<?> findSerializerByLookup(com.fasterxml.jackson.databind.JavaType r1, com.fasterxml.jackson.databind.SerializationConfig r2, com.fasterxml.jackson.databind.BeanDescription r3, boolean r4) {
        /*
            r0 = this;
            java.lang.Class r0 = r1.getRawClass()
            java.lang.String r0 = r0.getName()
            java.util.HashMap<java.lang.String, com.fasterxml.jackson.databind.JsonSerializer<?>> r1 = _concrete
            java.lang.Object r1 = r1.get(r0)
            com.fasterxml.jackson.databind.JsonSerializer r1 = (com.fasterxml.jackson.databind.JsonSerializer) r1
            if (r1 != 0) goto L_0x0024
            java.util.HashMap<java.lang.String, java.lang.Class<? extends com.fasterxml.jackson.databind.JsonSerializer<?>>> r2 = _concreteLazy
            java.lang.Object r0 = r2.get(r0)
            java.lang.Class r0 = (java.lang.Class) r0
            if (r0 == 0) goto L_0x0024
            r1 = 0
            java.lang.Object r0 = com.fasterxml.jackson.databind.util.ClassUtil.createInstance(r0, r1)
            com.fasterxml.jackson.databind.JsonSerializer r0 = (com.fasterxml.jackson.databind.JsonSerializer) r0
            return r0
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.findSerializerByLookup(com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.BeanDescription, boolean):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z2) throws JsonMappingException {
        if (javaType.isEnumType()) {
            return buildEnumSerializer(serializerProvider.getConfig(), javaType, beanDescription);
        }
        Class<?> rawClass = javaType.getRawClass();
        JsonSerializer<?> findOptionalStdSerializer = findOptionalStdSerializer(serializerProvider, javaType, beanDescription, z2);
        if (findOptionalStdSerializer != null) {
            return findOptionalStdSerializer;
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarSerializer.instance;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateSerializer.instance;
        }
        Class<Map.Entry> cls = Map.Entry.class;
        if (cls.isAssignableFrom(rawClass)) {
            JavaType findSuperType = javaType.findSuperType(cls);
            return buildMapEntrySerializer(serializerProvider, javaType, beanDescription, z2, findSuperType.containedTypeOrUnknown(0), findSuperType.containedTypeOrUnknown(1));
        } else if (ByteBuffer.class.isAssignableFrom(rawClass)) {
            return new ByteBufferSerializer();
        } else {
            if (InetAddress.class.isAssignableFrom(rawClass)) {
                return new InetAddressSerializer();
            }
            if (InetSocketAddress.class.isAssignableFrom(rawClass)) {
                return new InetSocketAddressSerializer();
            }
            if (TimeZone.class.isAssignableFrom(rawClass)) {
                return new TimeZoneSerializer();
            }
            if (Charset.class.isAssignableFrom(rawClass)) {
                return ToStringSerializer.instance;
            }
            if (Number.class.isAssignableFrom(rawClass)) {
                int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[beanDescription.findExpectedFormat((JsonFormat.Value) null).getShape().ordinal()];
                if (i3 == 1) {
                    return ToStringSerializer.instance;
                }
                if (i3 == 2 || i3 == 3) {
                    return null;
                }
                return NumberSerializer.instance;
            } else if (ClassLoader.class.isAssignableFrom(rawClass)) {
                return new ToEmptyObjectSerializer(javaType);
            } else {
                return null;
            }
        }
    }

    public JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializer = serializerProvider.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        return findConvertingSerializer(serializerProvider, annotated, serializerProvider.serializerInstance(annotated, findSerializer));
    }

    public SerializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    public boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer) {
        if (typeSerializer != null) {
            return false;
        }
        JsonSerialize.Typing findSerializationTyping = serializationConfig.getAnnotationIntrospector().findSerializationTyping(beanDescription.getClassInfo());
        return (findSerializationTyping == null || findSerializationTyping == JsonSerialize.Typing.DEFAULT_TYPING) ? serializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING) : findSerializationTyping == JsonSerialize.Typing.STATIC;
    }

    public final SerializerFactory withAdditionalKeySerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalKeySerializers(serializers));
    }

    public final SerializerFactory withAdditionalSerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalSerializers(serializers));
    }

    public abstract SerializerFactory withConfig(SerializerFactoryConfig serializerFactoryConfig);

    public final SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        return withConfig(this._factoryConfig.withSerializerModifier(beanSerializerModifier));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r7 = r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x0017 A[LOOP:0: B:3:0x0017->B:6:0x0027, LOOP_START, PHI: r2 
      PHI: (r2v2 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r2v0 com.fasterxml.jackson.databind.JsonSerializer<?>), (r2v5 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:2:0x000d, B:6:0x0027] A[DONT_GENERATE, DONT_INLINE]] */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> createKeySerializer(com.fasterxml.jackson.databind.SerializationConfig r5, com.fasterxml.jackson.databind.JavaType r6, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r7) {
        /*
            r4 = this;
            com.fasterxml.jackson.databind.BeanDescription r0 = r5.introspect(r6)
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            boolean r1 = r1.hasKeySerializers()
            r2 = 0
            if (r1 == 0) goto L_0x0029
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            java.lang.Iterable r1 = r1.keySerializers()
            java.util.Iterator r1 = r1.iterator()
        L_0x0017:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0029
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.ser.Serializers r2 = (com.fasterxml.jackson.databind.ser.Serializers) r2
            com.fasterxml.jackson.databind.JsonSerializer r2 = r2.findSerializer(r5, r6, r0)
            if (r2 == 0) goto L_0x0017
        L_0x0029:
            if (r2 != 0) goto L_0x0041
            if (r7 != 0) goto L_0x0042
            java.lang.Class r7 = r6.getRawClass()
            r1 = 0
            com.fasterxml.jackson.databind.JsonSerializer r7 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getStdKeySerializer(r5, r7, r1)
            if (r7 != 0) goto L_0x0042
            java.lang.Class r7 = r6.getRawClass()
            com.fasterxml.jackson.databind.JsonSerializer r7 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getFallbackKeySerializer(r5, r7)
            goto L_0x0042
        L_0x0041:
            r7 = r2
        L_0x0042:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x0065
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r4 = r4._factoryConfig
            java.lang.Iterable r4 = r4.serializerModifiers()
            java.util.Iterator r4 = r4.iterator()
        L_0x0054:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0065
            java.lang.Object r1 = r4.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r1 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r1
            com.fasterxml.jackson.databind.JsonSerializer r7 = r1.modifyKeySerializer(r5, r6, r0, r7)
            goto L_0x0054
        L_0x0065:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.createKeySerializer(com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public ContainerSerializer<?> buildCollectionSerializer(JavaType javaType, boolean z2, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z2, typeSerializer, jsonSerializer);
    }
}
