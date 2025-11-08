package com.fasterxml.jackson.databind.ser.std;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.text.lookup.StringLookupFactory;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final JavaType _beanType;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final JsonFormat.Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* renamed from: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonFormat$Shape[] r0 = com.fasterxml.jackson.annotation.JsonFormat.Shape.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = r0
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.AnonymousClass1.<clinit>():void");
        }
    }

    public BeanSerializerBase(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType);
        this._beanType = javaType;
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        if (beanSerializerBuilder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = beanSerializerBuilder.getTypeId();
        this._anyGetterWriter = beanSerializerBuilder.getAnyGetter();
        this._propertyFilterId = beanSerializerBuilder.getFilterId();
        this._objectIdWriter = beanSerializerBuilder.getObjectIdWriter();
        this._serializationShape = beanSerializerBuilder.getBeanDescription().findExpectedFormat((JsonFormat.Value) null).getShape();
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] beanPropertyWriterArr, NameTransformer nameTransformer) {
        if (beanPropertyWriterArr == null || beanPropertyWriterArr.length == 0 || nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return beanPropertyWriterArr;
        }
        int length = beanPropertyWriterArr.length;
        BeanPropertyWriter[] beanPropertyWriterArr2 = new BeanPropertyWriter[length];
        for (int i3 = 0; i3 < length; i3++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i3];
            if (beanPropertyWriter != null) {
                beanPropertyWriterArr2[i3] = beanPropertyWriter.rename(nameTransformer);
            }
        }
        return beanPropertyWriterArr2;
    }

    @Deprecated
    public final String _customTypeId(Object obj) {
        Object value = this._typeId.getValue(obj);
        return value == null ? "" : value instanceof String ? (String) value : value.toString();
    }

    public void _serializeObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer, WritableObjectId writableObjectId) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableTypeId _typeIdDef = _typeIdDef(typeSerializer, obj, JsonToken.START_OBJECT);
        typeSerializer.writeTypePrefix(jsonGenerator, _typeIdDef);
        jsonGenerator.setCurrentValue(obj);
        writableObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, _typeIdDef);
    }

    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z2) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
                return;
            }
            if (z2) {
                jsonGenerator.writeStartObject(obj);
            }
            findObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
            } else {
                serializeFields(obj, jsonGenerator, serializerProvider);
            }
            if (z2) {
                jsonGenerator.writeEndObject();
            }
        }
    }

    public final WritableTypeId _typeIdDef(TypeSerializer typeSerializer, Object obj, JsonToken jsonToken) {
        AnnotatedMember annotatedMember = this._typeId;
        if (annotatedMember == null) {
            return typeSerializer.typeId(obj, jsonToken);
        }
        Object value = annotatedMember.getValue(obj);
        if (value == null) {
            value = "";
        }
        return typeSerializer.typeId(obj, jsonToken, value);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat;
        if (jsonFormatVisitorWrapper != null && (expectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType)) != null) {
            SerializerProvider provider = jsonFormatVisitorWrapper.getProvider();
            int i3 = 0;
            Class<?> cls = null;
            if (this._propertyFilterId != null) {
                PropertyFilter findPropertyFilter = findPropertyFilter(jsonFormatVisitorWrapper.getProvider(), this._propertyFilterId, (Object) null);
                int length = this._props.length;
                while (i3 < length) {
                    findPropertyFilter.depositSchemaProperty((PropertyWriter) this._props[i3], expectObjectFormat, provider);
                    i3++;
                }
                return;
            }
            if (!(this._filteredProps == null || provider == null)) {
                cls = provider.getActiveView();
            }
            BeanPropertyWriter[] beanPropertyWriterArr = cls != null ? this._filteredProps : this._props;
            int length2 = beanPropertyWriterArr.length;
            while (i3 < length2) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i3];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.depositSchemaProperty(expectObjectFormat, provider);
                }
                i3++;
            }
        }
    }

    public abstract BeanSerializerBase asArraySerializer();

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Shape shape;
        int i3;
        Set<String> set;
        BeanPropertyWriter[] beanPropertyWriterArr;
        Set<String> set2;
        Object obj;
        BeanSerializerBase beanSerializerBase;
        ObjectIdWriter withSerializer;
        BeanPropertyWriter[] beanPropertyWriterArr2;
        Set<String> set3;
        Object obj2;
        BeanPropertyWriter beanPropertyWriter;
        ObjectIdInfo findObjectReferenceInfo;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        AnnotatedMember member = (beanProperty2 == null || annotationIntrospector == null) ? null : beanProperty.getMember();
        SerializationConfig config = serializerProvider.getConfig();
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider2, beanProperty2, this._handledType);
        if (findFormatOverrides == null || !findFormatOverrides.hasShape()) {
            shape = null;
        } else {
            shape = findFormatOverrides.getShape();
            if (!(shape == JsonFormat.Shape.ANY || shape == this._serializationShape)) {
                if (this._beanType.isEnumType()) {
                    int i4 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[shape.ordinal()];
                    if (i4 == 1 || i4 == 2 || i4 == 3) {
                        return serializerProvider2.handlePrimaryContextualization(EnumSerializer.construct(this._beanType.getRawClass(), serializerProvider.getConfig(), config.introspectClassAnnotations(this._beanType), findFormatOverrides), beanProperty2);
                    }
                } else if (shape == JsonFormat.Shape.NATURAL && (!this._beanType.isMapLikeType() || !Map.class.isAssignableFrom(this._handledType))) {
                    Class<Map.Entry> cls = Map.Entry.class;
                    if (cls.isAssignableFrom(this._handledType)) {
                        JavaType findSuperType = this._beanType.findSuperType(cls);
                        return serializerProvider2.handlePrimaryContextualization(new MapEntrySerializer(this._beanType, findSuperType.containedTypeOrUnknown(0), findSuperType.containedTypeOrUnknown(1), false, (TypeSerializer) null, beanProperty), beanProperty2);
                    }
                }
            }
        }
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        if (member != null) {
            set = annotationIntrospector.findPropertyIgnoralByName(config, member).findIgnoredForSerialization();
            Set<String> included = annotationIntrospector.findPropertyInclusionByName(config, member).getIncluded();
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(member);
            if (findObjectIdInfo == null) {
                if (!(objectIdWriter == null || (findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, (ObjectIdInfo) null)) == null)) {
                    objectIdWriter = this._objectIdWriter.withAlwaysAsId(findObjectReferenceInfo.getAlwaysAsId());
                }
                set3 = included;
                i3 = 0;
                beanPropertyWriterArr = null;
            } else {
                ObjectIdInfo findObjectReferenceInfo2 = annotationIntrospector.findObjectReferenceInfo(member, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo2.getGeneratorType();
                JavaType javaType = serializerProvider.getTypeFactory().findTypeParameters(serializerProvider2.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String simpleName = findObjectReferenceInfo2.getPropertyName().getSimpleName();
                    int length = this._props.length;
                    i3 = 0;
                    while (true) {
                        if (i3 == length) {
                            set3 = included;
                            serializerProvider2.reportBadDefinition(this._beanType, C0118y.f("Invalid Object Id definition for ", ClassUtil.nameOf((Class<?>) handledType()), ": cannot find property with name ", ClassUtil.name(simpleName)));
                        } else {
                            set3 = included;
                        }
                        beanPropertyWriter = this._props[i3];
                        if (simpleName.equals(beanPropertyWriter.getName())) {
                            break;
                        }
                        i3++;
                        included = set3;
                    }
                    beanPropertyWriterArr = null;
                    objectIdWriter = ObjectIdWriter.construct(beanPropertyWriter.getType(), (PropertyName) null, new PropertyBasedObjectIdGenerator(findObjectReferenceInfo2, beanPropertyWriter), findObjectReferenceInfo2.getAlwaysAsId());
                } else {
                    beanPropertyWriterArr = null;
                    set3 = included;
                    objectIdWriter = ObjectIdWriter.construct(javaType, findObjectReferenceInfo2.getPropertyName(), serializerProvider2.objectIdGeneratorInstance(member, findObjectReferenceInfo2), findObjectReferenceInfo2.getAlwaysAsId());
                    i3 = 0;
                }
            }
            obj = annotationIntrospector.findFilterId(member);
            if (obj == null || ((obj2 = this._propertyFilterId) != null && obj.equals(obj2))) {
                obj = beanPropertyWriterArr;
            }
            set2 = set3;
        } else {
            beanPropertyWriterArr = null;
            obj = null;
            set2 = null;
            set = null;
            i3 = 0;
        }
        if (i3 > 0) {
            BeanPropertyWriter[] beanPropertyWriterArr3 = this._props;
            BeanPropertyWriter[] beanPropertyWriterArr4 = (BeanPropertyWriter[]) Arrays.copyOf(beanPropertyWriterArr3, beanPropertyWriterArr3.length);
            BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriterArr4[i3];
            System.arraycopy(beanPropertyWriterArr4, 0, beanPropertyWriterArr4, 1, i3);
            beanPropertyWriterArr4[0] = beanPropertyWriter2;
            BeanPropertyWriter[] beanPropertyWriterArr5 = this._filteredProps;
            if (beanPropertyWriterArr5 == null) {
                beanPropertyWriterArr2 = beanPropertyWriterArr;
            } else {
                beanPropertyWriterArr2 = (BeanPropertyWriter[]) Arrays.copyOf(beanPropertyWriterArr5, beanPropertyWriterArr5.length);
                BeanPropertyWriter beanPropertyWriter3 = beanPropertyWriterArr2[i3];
                System.arraycopy(beanPropertyWriterArr2, 0, beanPropertyWriterArr2, 1, i3);
                beanPropertyWriterArr2[0] = beanPropertyWriter3;
            }
            beanSerializerBase = withProperties(beanPropertyWriterArr4, beanPropertyWriterArr2);
        } else {
            beanSerializerBase = this;
        }
        if (!(objectIdWriter == null || (withSerializer = objectIdWriter.withSerializer(serializerProvider2.findValueSerializer(objectIdWriter.idType, beanProperty2))) == this._objectIdWriter)) {
            beanSerializerBase = beanSerializerBase.withObjectIdWriter(withSerializer);
        }
        if ((set != null && !set.isEmpty()) || set2 != null) {
            beanSerializerBase = beanSerializerBase.withByNameInclusion(set, set2);
        }
        if (obj != null) {
            beanSerializerBase = beanSerializerBase.withFilterId(obj);
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        return shape == JsonFormat.Shape.ARRAY ? beanSerializerBase.asArraySerializer() : beanSerializerBase;
    }

    public JsonSerializer<Object> findConvertingSerializer(SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws JsonMappingException {
        AnnotatedMember member;
        Object findSerializationConverter;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        JsonSerializer<Object> jsonSerializer = null;
        if (annotationIntrospector == null || (member = beanPropertyWriter.getMember()) == null || (findSerializationConverter = annotationIntrospector.findSerializationConverter(member)) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = serializerProvider.converterInstance(beanPropertyWriter.getMember(), findSerializationConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
        if (!outputType.isJavaLangObject()) {
            jsonSerializer = serializerProvider.findValueSerializer(outputType, (BeanProperty) beanPropertyWriter);
        }
        return new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer);
    }

    @Deprecated
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        String id;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (!(jsonSerializableSchema == null || (id = jsonSerializableSchema.id()) == null || id.isEmpty())) {
            createSchemaNode.put(TtmlNode.ATTR_ID, id);
        }
        ObjectNode objectNode = createSchemaNode.objectNode();
        Object obj = this._propertyFilterId;
        PropertyFilter propertyFilter = null;
        if (obj != null) {
            propertyFilter = findPropertyFilter(serializerProvider, obj, (Object) null);
        }
        int i3 = 0;
        while (true) {
            BeanPropertyWriter[] beanPropertyWriterArr = this._props;
            if (i3 < beanPropertyWriterArr.length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i3];
                if (propertyFilter == null) {
                    beanPropertyWriter.depositSchemaProperty(objectNode, serializerProvider);
                } else {
                    propertyFilter.depositSchemaProperty((PropertyWriter) beanPropertyWriter, objectNode, serializerProvider);
                }
                i3++;
            } else {
                createSchemaNode.set(StringLookupFactory.KEY_PROPERTIES, objectNode);
                return createSchemaNode;
            }
        }
    }

    public Iterator<PropertyWriter> properties() {
        return Arrays.asList(this._props).iterator();
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        JsonSerializer<Object> findNullValueSerializer;
        BeanPropertyWriter beanPropertyWriter2;
        BeanPropertyWriter[] beanPropertyWriterArr = this._filteredProps;
        int length = beanPropertyWriterArr == null ? 0 : beanPropertyWriterArr.length;
        int length2 = this._props.length;
        for (int i3 = 0; i3 < length2; i3++) {
            BeanPropertyWriter beanPropertyWriter3 = this._props[i3];
            if (!beanPropertyWriter3.willSuppressNulls() && !beanPropertyWriter3.hasNullSerializer() && (findNullValueSerializer = serializerProvider.findNullValueSerializer(beanPropertyWriter3)) != null) {
                beanPropertyWriter3.assignNullSerializer(findNullValueSerializer);
                if (i3 < length && (beanPropertyWriter2 = this._filteredProps[i3]) != null) {
                    beanPropertyWriter2.assignNullSerializer(findNullValueSerializer);
                }
            }
            if (!beanPropertyWriter3.hasSerializer()) {
                JsonSerializer<Object> findConvertingSerializer = findConvertingSerializer(serializerProvider, beanPropertyWriter3);
                if (findConvertingSerializer == null) {
                    JavaType serializationType = beanPropertyWriter3.getSerializationType();
                    if (serializationType == null) {
                        serializationType = beanPropertyWriter3.getType();
                        if (!serializationType.isFinal()) {
                            if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                                beanPropertyWriter3.setNonTrivialBaseType(serializationType);
                            }
                        }
                    }
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(serializationType, (BeanProperty) beanPropertyWriter3);
                    findConvertingSerializer = (!serializationType.isContainerType() || (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) == null || !(findValueSerializer instanceof ContainerSerializer)) ? findValueSerializer : ((ContainerSerializer) findValueSerializer).withValueTypeSerializer(typeSerializer);
                }
                if (i3 >= length || (beanPropertyWriter = this._filteredProps[i3]) == null) {
                    beanPropertyWriter3.assignSerializer(findConvertingSerializer);
                } else {
                    beanPropertyWriter.assignSerializer(findConvertingSerializer);
                }
            }
        }
        AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
        if (anyGetterWriter != null) {
            anyGetterWriter.resolve(serializerProvider);
        }
    }

    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = "[anySetter]";
        BeanPropertyWriter[] beanPropertyWriterArr = (this._filteredProps == null || serializerProvider.getActiveView() == null) ? this._props : this._filteredProps;
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
            }
            AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
            if (anyGetterWriter != null) {
                anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e3) {
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            wrapAndThrow(serializerProvider, (Throwable) e3, obj, str);
        } catch (StackOverflowError e4) {
            JsonMappingException jsonMappingException = new JsonMappingException((Closeable) jsonGenerator, "Infinite recursion (StackOverflowError)", (Throwable) e4);
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            jsonMappingException.prependPath(obj, str);
            throw jsonMappingException;
        }
    }

    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String str = "[anySetter]";
        BeanPropertyWriter[] beanPropertyWriterArr = (this._filteredProps == null || serializerProvider.getActiveView() == null) ? this._props : this._filteredProps;
        PropertyFilter findPropertyFilter = findPropertyFilter(serializerProvider, this._propertyFilterId, obj);
        if (findPropertyFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    findPropertyFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
            }
            AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
            if (anyGetterWriter != null) {
                anyGetterWriter.getAndFilter(obj, jsonGenerator, serializerProvider, findPropertyFilter);
            }
        } catch (Exception e3) {
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            wrapAndThrow(serializerProvider, (Throwable) e3, obj, str);
        } catch (StackOverflowError e4) {
            JsonMappingException jsonMappingException = new JsonMappingException((Closeable) jsonGenerator, "Infinite recursion (StackOverflowError)", (Throwable) e4);
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            jsonMappingException.prependPath(obj, str);
            throw jsonMappingException;
        }
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, typeSerializer);
            return;
        }
        WritableTypeId _typeIdDef = _typeIdDef(typeSerializer, obj, JsonToken.START_OBJECT);
        typeSerializer.writeTypePrefix(jsonGenerator, _typeIdDef);
        jsonGenerator.setCurrentValue(obj);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, _typeIdDef);
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public abstract BeanSerializerBase withByNameInclusion(Set<String> set, Set<String> set2);

    public abstract BeanSerializerBase withFilterId(Object obj);

    @Deprecated
    public BeanSerializerBase withIgnorals(Set<String> set) {
        return withByNameInclusion(set, (Set<String>) null);
    }

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    public abstract BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2);

    @Deprecated
    public BeanSerializerBase withIgnorals(String[] strArr) {
        return withIgnorals((Set<String>) ArrayBuilders.arrayToSet(strArr));
    }

    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
            } else {
                _serializeObjectId(obj, jsonGenerator, serializerProvider, typeSerializer, findObjectId);
            }
        }
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(beanSerializerBase._handledType);
        this._beanType = beanSerializerBase._beanType;
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter) {
        this(beanSerializerBase, objectIdWriter, beanSerializerBase._propertyFilterId);
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(beanSerializerBase._handledType);
        this._beanType = beanSerializerBase._beanType;
        this._props = beanSerializerBase._props;
        this._filteredProps = beanSerializerBase._filteredProps;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = obj;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    @Deprecated
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, String[] strArr) {
        this(beanSerializerBase, (Set<String>) ArrayBuilders.arrayToSet(strArr), (Set<String>) null);
    }

    @Deprecated
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, Set<String> set) {
        this(beanSerializerBase, set, (Set<String>) null);
    }

    /* JADX WARNING: type inference failed for: r11v11, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanSerializerBase(com.fasterxml.jackson.databind.ser.std.BeanSerializerBase r10, java.util.Set<java.lang.String> r11, java.util.Set<java.lang.String> r12) {
        /*
            r9 = this;
            java.lang.Class<T> r0 = r10._handledType
            r9.<init>(r0)
            com.fasterxml.jackson.databind.JavaType r0 = r10._beanType
            r9._beanType = r0
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r0 = r10._props
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r1 = r10._filteredProps
            int r2 = r0.length
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            r4 = 0
            if (r1 != 0) goto L_0x0018
            r5 = r4
            goto L_0x001d
        L_0x0018:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r2)
        L_0x001d:
            r6 = 0
        L_0x001e:
            if (r6 >= r2) goto L_0x003a
            r7 = r0[r6]
            java.lang.String r8 = r7.getName()
            boolean r8 = com.fasterxml.jackson.databind.util.IgnorePropertiesUtil.shouldIgnore(r8, r11, r12)
            if (r8 == 0) goto L_0x002d
            goto L_0x0037
        L_0x002d:
            r3.add(r7)
            if (r1 == 0) goto L_0x0037
            r7 = r1[r6]
            r5.add(r7)
        L_0x0037:
            int r6 = r6 + 1
            goto L_0x001e
        L_0x003a:
            int r11 = r3.size()
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter[r11]
            java.lang.Object[] r11 = r3.toArray(r11)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r11
            r9._props = r11
            if (r5 != 0) goto L_0x004b
            goto L_0x0058
        L_0x004b:
            int r11 = r5.size()
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter[r11]
            java.lang.Object[] r11 = r5.toArray(r11)
            r4 = r11
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r4 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r4
        L_0x0058:
            r9._filteredProps = r4
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r11 = r10._typeId
            r9._typeId = r11
            com.fasterxml.jackson.databind.ser.AnyGetterWriter r11 = r10._anyGetterWriter
            r9._anyGetterWriter = r11
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r11 = r10._objectIdWriter
            r9._objectIdWriter = r11
            java.lang.Object r11 = r10._propertyFilterId
            r9._propertyFilterId = r11
            com.fasterxml.jackson.annotation.JsonFormat$Shape r10 = r10._serializationShape
            r9._serializationShape = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, java.util.Set, java.util.Set):void");
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this(beanSerializerBase, beanSerializerBase._props, beanSerializerBase._filteredProps);
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, NameTransformer nameTransformer) {
        this(beanSerializerBase, rename(beanSerializerBase._props, nameTransformer), rename(beanSerializerBase._filteredProps, nameTransformer));
    }
}
