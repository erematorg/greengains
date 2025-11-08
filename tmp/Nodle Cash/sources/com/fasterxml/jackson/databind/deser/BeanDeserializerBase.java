package com.fasterxml.jackson.databind.deser;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.MergingSettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReferenceProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.AccessPattern;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer, ValueInstantiator.Gettable, Serializable {
    protected static final PropertyName TEMP_PROPERTY_NAME = new PropertyName("#temporary-name");
    private static final long serialVersionUID = 1;
    protected SettableAnyProperty _anySetter;
    protected JsonDeserializer<Object> _arrayDelegateDeserializer;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final Set<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final Set<String> _includableProps;
    protected final ValueInjector[] _injectables;
    protected final boolean _needViewProcesing;
    protected boolean _nonStandardCreation;
    protected final ObjectIdReader _objectIdReader;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final JsonFormat.Shape _serializationShape;
    protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;
    protected boolean _vanillaProcessing;

    public BeanDeserializerBase(BeanDeserializerBuilder beanDeserializerBuilder, BeanDescription beanDescription, BeanPropertyMap beanPropertyMap, Map<String, SettableBeanProperty> map, Set<String> set, boolean z2, Set<String> set2, boolean z3) {
        super(beanDescription.getType());
        this._beanType = beanDescription.getType();
        ValueInstantiator valueInstantiator = beanDeserializerBuilder.getValueInstantiator();
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = null;
        this._arrayDelegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._beanProperties = beanPropertyMap;
        this._backRefs = map;
        this._ignorableProps = set;
        this._ignoreAllUnknown = z2;
        this._includableProps = set2;
        this._anySetter = beanDeserializerBuilder.getAnySetter();
        List<ValueInjector> injectables = beanDeserializerBuilder.getInjectables();
        ValueInjector[] valueInjectorArr = (injectables == null || injectables.isEmpty()) ? null : (ValueInjector[]) injectables.toArray(new ValueInjector[injectables.size()]);
        this._injectables = valueInjectorArr;
        ObjectIdReader objectIdReader = beanDeserializerBuilder.getObjectIdReader();
        this._objectIdReader = objectIdReader;
        boolean z4 = true;
        this._nonStandardCreation = this._unwrappedPropertyHandler != null || valueInstantiator.canCreateUsingDelegate() || valueInstantiator.canCreateFromObjectWith() || !valueInstantiator.canCreateUsingDefault();
        this._serializationShape = beanDescription.findExpectedFormat((JsonFormat.Value) null).getShape();
        this._needViewProcesing = z3;
        this._vanillaProcessing = (this._nonStandardCreation || valueInjectorArr != null || z3 || objectIdReader != null) ? false : z4;
    }

    private JsonDeserializer<Object> _findDelegateDeserializer(DeserializationContext deserializationContext, JavaType javaType, AnnotatedWithParams annotatedWithParams) throws JsonMappingException {
        BeanProperty.Std std = new BeanProperty.Std(TEMP_PROPERTY_NAME, javaType, (PropertyName) null, annotatedWithParams, PropertyMetadata.STD_OPTIONAL);
        TypeDeserializer typeDeserializer = (TypeDeserializer) javaType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = deserializationContext.getConfig().findTypeDeserializer(javaType);
        }
        JsonDeserializer jsonDeserializer = (JsonDeserializer) javaType.getValueHandler();
        JsonDeserializer findDeserializer = jsonDeserializer == null ? findDeserializer(deserializationContext, javaType, std) : deserializationContext.handleSecondaryContextualization(jsonDeserializer, std, javaType);
        return typeDeserializer != null ? new TypeWrappedDeserializer(typeDeserializer.forProperty(std), findDeserializer) : findDeserializer;
    }

    private Throwable throwOrReturnThrowable(Throwable th, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        boolean z2 = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th instanceof IOException) {
            if (!z2 || !(th instanceof JacksonException)) {
                throw ((IOException) th);
            }
        } else if (!z2) {
            ClassUtil.throwIfRTE(th);
        }
        return th;
    }

    public Object _convertObjectId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, JsonDeserializer<Object> jsonDeserializer) throws IOException {
        TokenBuffer bufferForInputBuffering = deserializationContext.bufferForInputBuffering(jsonParser);
        if (obj instanceof String) {
            bufferForInputBuffering.writeString((String) obj);
        } else if (obj instanceof Long) {
            bufferForInputBuffering.writeNumber(((Long) obj).longValue());
        } else if (obj instanceof Integer) {
            bufferForInputBuffering.writeNumber(((Integer) obj).intValue());
        } else {
            bufferForInputBuffering.writeObject(obj);
        }
        JsonParser asParser = bufferForInputBuffering.asParser();
        asParser.nextToken();
        return jsonDeserializer.deserialize(asParser, deserializationContext);
    }

    public final JsonDeserializer<Object> _delegateDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        return jsonDeserializer == null ? this._arrayDelegateDeserializer : jsonDeserializer;
    }

    public abstract Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public NameTransformer _findPropertyUnwrapper(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        NameTransformer findUnwrappingNameTransformer;
        AnnotatedMember member = settableBeanProperty.getMember();
        if (member == null || (findUnwrappingNameTransformer = deserializationContext.getAnnotationIntrospector().findUnwrappingNameTransformer(member)) == null) {
            return null;
        }
        if (settableBeanProperty instanceof CreatorProperty) {
            JavaType valueType = getValueType();
            String name = settableBeanProperty.getName();
            deserializationContext.reportBadDefinition(valueType, "Cannot define Creator property \"" + name + "\" as `@JsonUnwrapped`: combination not yet supported");
        }
        return findUnwrappingNameTransformer;
    }

    public JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException {
        JsonDeserializer<Object> jsonDeserializer;
        synchronized (this) {
            HashMap<ClassKey, JsonDeserializer<Object>> hashMap = this._subDeserializers;
            jsonDeserializer = hashMap == null ? null : hashMap.get(new ClassKey(obj.getClass()));
        }
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(deserializationContext.constructType(obj.getClass()));
        if (findRootValueDeserializer != null) {
            synchronized (this) {
                try {
                    if (this._subDeserializers == null) {
                        this._subDeserializers = new HashMap<>();
                    }
                    this._subDeserializers.put(new ClassKey(obj.getClass()), findRootValueDeserializer);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return findRootValueDeserializer;
    }

    public BeanDeserializerBase _handleByNameInclusion(DeserializationContext deserializationContext, AnnotationIntrospector annotationIntrospector, BeanDeserializerBase beanDeserializerBase, AnnotatedMember annotatedMember) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JsonIgnoreProperties.Value findPropertyIgnoralByName = annotationIntrospector.findPropertyIgnoralByName(config, annotatedMember);
        if (findPropertyIgnoralByName.getIgnoreUnknown() && !this._ignoreAllUnknown) {
            beanDeserializerBase = beanDeserializerBase.withIgnoreAllUnknown(true);
        }
        Set<String> findIgnoredForDeserialization = findPropertyIgnoralByName.findIgnoredForDeserialization();
        Set<String> set = beanDeserializerBase._ignorableProps;
        if (findIgnoredForDeserialization.isEmpty()) {
            findIgnoredForDeserialization = set;
        } else if (set != null && !set.isEmpty()) {
            HashSet hashSet = new HashSet(set);
            hashSet.addAll(findIgnoredForDeserialization);
            findIgnoredForDeserialization = hashSet;
        }
        Set<String> set2 = beanDeserializerBase._includableProps;
        Set<String> combineNamesToInclude = IgnorePropertiesUtil.combineNamesToInclude(set2, annotationIntrospector.findPropertyInclusionByName(config, annotatedMember).getIncluded());
        return (findIgnoredForDeserialization == set && combineNamesToInclude == set2) ? beanDeserializerBase : beanDeserializerBase.withByNameInclusion(findIgnoredForDeserialization, combineNamesToInclude);
    }

    public Object _handleTypedObjectId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, Object obj2) throws IOException {
        JsonDeserializer<Object> deserializer = this._objectIdReader.getDeserializer();
        if (deserializer.handledType() != obj2.getClass()) {
            obj2 = _convertObjectId(jsonParser, deserializationContext, obj2, deserializer);
        }
        ObjectIdReader objectIdReader = this._objectIdReader;
        deserializationContext.findObjectId(obj2, objectIdReader.generator, objectIdReader.resolver).bindItem(obj);
        SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
        return settableBeanProperty != null ? settableBeanProperty.setAndReturn(obj, obj2) : obj;
    }

    public void _replaceProperty(BeanPropertyMap beanPropertyMap, SettableBeanProperty[] settableBeanPropertyArr, SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2) {
        beanPropertyMap.replace(settableBeanProperty, settableBeanProperty2);
        if (settableBeanPropertyArr != null) {
            int length = settableBeanPropertyArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (settableBeanPropertyArr[i3] == settableBeanProperty) {
                    settableBeanPropertyArr[i3] = settableBeanProperty2;
                    return;
                }
            }
        }
    }

    public SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) {
        Class<?> rawClass;
        Class<?> outerClass;
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        if ((valueDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) valueDeserializer).getValueInstantiator().canCreateUsingDefault() && (outerClass = ClassUtil.getOuterClass(rawClass)) != null && outerClass == this._beanType.getRawClass()) {
            Constructor[] constructors = (rawClass = settableBeanProperty.getType().getRawClass()).getConstructors();
            int length = constructors.length;
            int i3 = 0;
            while (i3 < length) {
                Constructor constructor = constructors[i3];
                if (constructor.getParameterCount() != 1 || !outerClass.equals(constructor.getParameterTypes()[0])) {
                    i3++;
                } else {
                    if (deserializationContext.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(constructor, deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return new InnerClassProperty(settableBeanProperty, (Constructor<?>) constructor);
                }
            }
        }
        return settableBeanProperty;
    }

    public SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        String managedReferenceName = settableBeanProperty.getManagedReferenceName();
        if (managedReferenceName == null) {
            return settableBeanProperty;
        }
        SettableBeanProperty findBackReference = settableBeanProperty.getValueDeserializer().findBackReference(managedReferenceName);
        if (findBackReference == null) {
            return (SettableBeanProperty) deserializationContext.reportBadDefinition(this._beanType, C0118y.f("Cannot handle managed/back reference ", ClassUtil.name(managedReferenceName), ": no back reference property found from type ", ClassUtil.getTypeDescription(settableBeanProperty.getType())));
        }
        JavaType javaType = this._beanType;
        JavaType type = findBackReference.getType();
        boolean isContainerType = settableBeanProperty.getType().isContainerType();
        if (!type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
            JavaType javaType2 = this._beanType;
            String name = ClassUtil.name(managedReferenceName);
            String typeDescription = ClassUtil.getTypeDescription(type);
            String name2 = javaType.getRawClass().getName();
            StringBuilder l2 = C0118y.l("Cannot handle managed/back reference ", name, ": back reference type (", typeDescription, ") not compatible with managed type (");
            l2.append(name2);
            l2.append(")");
            deserializationContext.reportBadDefinition(javaType2, l2.toString());
        }
        return new ManagedReferenceProperty(settableBeanProperty, managedReferenceName, findBackReference, isContainerType);
    }

    public SettableBeanProperty _resolveMergeAndNullSettings(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty, PropertyMetadata propertyMetadata) throws JsonMappingException {
        PropertyMetadata.MergeInfo mergeInfo = propertyMetadata.getMergeInfo();
        if (mergeInfo != null) {
            JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
            Boolean supportsUpdate = valueDeserializer.supportsUpdate(deserializationContext.getConfig());
            if (supportsUpdate == null) {
                if (mergeInfo.fromDefaults) {
                    return settableBeanProperty;
                }
            } else if (!supportsUpdate.booleanValue()) {
                if (!mergeInfo.fromDefaults) {
                    deserializationContext.handleBadMerge(valueDeserializer);
                }
                return settableBeanProperty;
            }
            AnnotatedMember annotatedMember = mergeInfo.getter;
            annotatedMember.fixAccess(deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            if (!(settableBeanProperty instanceof SetterlessProperty)) {
                settableBeanProperty = MergingSettableBeanProperty.construct(settableBeanProperty, annotatedMember);
            }
        }
        NullValueProvider findValueNullProvider = findValueNullProvider(deserializationContext, settableBeanProperty, propertyMetadata);
        return findValueNullProvider != null ? settableBeanProperty.withNullProvider(findValueNullProvider) : settableBeanProperty;
    }

    public SettableBeanProperty _resolvedObjectIdProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        ObjectIdInfo objectIdInfo = settableBeanProperty.getObjectIdInfo();
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
        return (objectIdInfo == null && (valueDeserializer == null ? null : valueDeserializer.getObjectIdReader()) == null) ? settableBeanProperty : new ObjectIdReferenceProperty(settableBeanProperty, objectIdInfo);
    }

    public abstract BeanDeserializerBase asArrayDeserializer();

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        BeanPropertyMap beanPropertyMap;
        BeanPropertyMap withCaseInsensitivity;
        ObjectIdInfo findObjectIdInfo;
        JavaType javaType;
        SettableBeanProperty settableBeanProperty;
        PropertyBasedObjectIdGenerator objectIdGeneratorInstance;
        ObjectIdReader objectIdReader = this._objectIdReader;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        JsonFormat.Shape shape = null;
        AnnotatedMember member = StdDeserializer._neitherNull(beanProperty, annotationIntrospector) ? beanProperty.getMember() : null;
        if (!(member == null || (findObjectIdInfo = annotationIntrospector.findObjectIdInfo(member)) == null)) {
            ObjectIdInfo findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, findObjectIdInfo);
            Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo.getGeneratorType();
            ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(member, findObjectReferenceInfo);
            if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                PropertyName propertyName = findObjectReferenceInfo.getPropertyName();
                SettableBeanProperty findProperty = findProperty(propertyName);
                if (findProperty == null) {
                    return (JsonDeserializer) deserializationContext.reportBadDefinition(this._beanType, C0118y.f("Invalid Object Id definition for ", ClassUtil.nameOf(handledType()), ": cannot find property with name ", ClassUtil.name(propertyName)));
                }
                javaType = findProperty.getType();
                settableBeanProperty = findProperty;
                objectIdGeneratorInstance = new PropertyBasedObjectIdGenerator(findObjectReferenceInfo.getScope());
            } else {
                javaType = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                settableBeanProperty = null;
                objectIdGeneratorInstance = deserializationContext.objectIdGeneratorInstance(member, findObjectReferenceInfo);
            }
            JavaType javaType2 = javaType;
            objectIdReader = ObjectIdReader.construct(javaType2, findObjectReferenceInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext.findRootValueDeserializer(javaType2), settableBeanProperty, objectIdResolverInstance);
        }
        BeanDeserializerBase withObjectIdReader = (objectIdReader == null || objectIdReader == this._objectIdReader) ? this : withObjectIdReader(objectIdReader);
        if (member != null) {
            withObjectIdReader = _handleByNameInclusion(deserializationContext, annotationIntrospector, withObjectIdReader, member);
        }
        JsonFormat.Value findFormatOverrides = findFormatOverrides(deserializationContext, beanProperty, handledType());
        if (findFormatOverrides != null) {
            if (findFormatOverrides.hasShape()) {
                shape = findFormatOverrides.getShape();
            }
            Boolean feature = findFormatOverrides.getFeature(JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            if (!(feature == null || (withCaseInsensitivity = beanPropertyMap.withCaseInsensitivity(feature.booleanValue())) == (beanPropertyMap = this._beanProperties))) {
                withObjectIdReader = withObjectIdReader.withBeanProperties(withCaseInsensitivity);
            }
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        return shape == JsonFormat.Shape.ARRAY ? withObjectIdReader.asArrayDeserializer() : withObjectIdReader;
    }

    public Iterator<SettableBeanProperty> creatorProperties() {
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        return propertyBasedCreator == null ? Collections.emptyList().iterator() : propertyBasedCreator.properties().iterator();
    }

    @Deprecated
    public Object deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserializeFromArray(jsonParser, deserializationContext);
    }

    public Object deserializeFromBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
        if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromBoolean()) {
            return this._valueInstantiator.createFromBoolean(deserializationContext, jsonParser.currentToken() == JsonToken.VALUE_TRUE);
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDelegate);
        }
        return createUsingDelegate;
    }

    public Object deserializeFromDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.DOUBLE || numberType == JsonParser.NumberType.FLOAT) {
            JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
            if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromDouble()) {
                return this._valueInstantiator.createFromDouble(deserializationContext, jsonParser.getDoubleValue());
            }
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        } else if (numberType == JsonParser.NumberType.BIG_DECIMAL) {
            JsonDeserializer<Object> _delegateDeserializer3 = _delegateDeserializer();
            if (_delegateDeserializer3 == null || this._valueInstantiator.canCreateFromBigDecimal()) {
                return this._valueInstantiator.createFromBigDecimal(deserializationContext, jsonParser.getDecimalValue());
            }
            Object createUsingDelegate2 = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer3.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate2);
            }
            return createUsingDelegate2;
        } else {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "no suitable creator method found to deserialize from Number value (%s)", jsonParser.getNumberValue());
        }
    }

    public Object deserializeFromEmbedded(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
        if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromString()) {
            Object embeddedObject = jsonParser.getEmbeddedObject();
            return (embeddedObject == null || this._beanType.isTypeOrSuperTypeOf(embeddedObject.getClass())) ? embeddedObject : deserializationContext.handleWeirdNativeValue(this._beanType, embeddedObject, jsonParser);
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDelegate);
        }
        return createUsingDelegate;
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
        JsonParser.NumberType numberType = jsonParser.getNumberType();
        if (numberType == JsonParser.NumberType.INT) {
            if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromInt()) {
                return this._valueInstantiator.createFromInt(deserializationContext, jsonParser.getIntValue());
            }
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        } else if (numberType == JsonParser.NumberType.LONG) {
            if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromInt()) {
                return this._valueInstantiator.createFromLong(deserializationContext, jsonParser.getLongValue());
            }
            Object createUsingDelegate2 = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate2);
            }
            return createUsingDelegate2;
        } else if (numberType != JsonParser.NumberType.BIG_INTEGER) {
            return deserializationContext.handleMissingInstantiator(handledType(), getValueInstantiator(), jsonParser, "no suitable creator method found to deserialize from Number value (%s)", jsonParser.getNumberValue());
        } else if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromBigInteger()) {
            return this._valueInstantiator.createFromBigInteger(deserializationContext, jsonParser.getBigIntegerValue());
        } else {
            Object createUsingDelegate3 = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate3);
            }
            return createUsingDelegate3;
        }
    }

    public abstract Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    public Object deserializeFromObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Object readObjectReference = this._objectIdReader.readObjectReference(jsonParser, deserializationContext);
        ObjectIdReader objectIdReader = this._objectIdReader;
        ReadableObjectId findObjectId = deserializationContext.findObjectId(readObjectReference, objectIdReader.generator, objectIdReader.resolver);
        Object resolve = findObjectId.resolve();
        if (resolve != null) {
            return resolve;
        }
        throw new UnresolvedForwardReference(jsonParser, "Could not resolve Object Id [" + readObjectReference + "] (for " + this._beanType + ").", jsonParser.getCurrentLocation(), findObjectId);
    }

    public Object deserializeFromObjectUsingNonDefault(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
        if (_delegateDeserializer2 != null) {
            Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
            if (this._injectables != null) {
                injectValues(deserializationContext, createUsingDelegate);
            }
            return createUsingDelegate;
        } else if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        } else {
            Class<?> rawClass = this._beanType.getRawClass();
            if (ClassUtil.isNonStaticInnerClass(rawClass)) {
                return deserializationContext.handleMissingInstantiator(rawClass, (ValueInstantiator) null, jsonParser, "non-static inner classes like this can only by instantiated using default, no-argument constructor", new Object[0]);
            } else if (NativeImageUtil.needsReflectionConfiguration(rawClass)) {
                return deserializationContext.handleMissingInstantiator(rawClass, (ValueInstantiator) null, jsonParser, "cannot deserialize from Object value (no delegate- or property-based Creator): this appears to be a native image, in which case you may need to configure reflection for the class that is to be deserialized", new Object[0]);
            } else {
                return deserializationContext.handleMissingInstantiator(rawClass, getValueInstantiator(), jsonParser, "cannot deserialize from Object value (no delegate- or property-based Creator)", new Object[0]);
            }
        }
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> _delegateDeserializer2 = _delegateDeserializer();
        if (_delegateDeserializer2 == null || this._valueInstantiator.canCreateFromString()) {
            return _deserializeFromString(jsonParser, deserializationContext);
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext, _delegateDeserializer2.deserialize(jsonParser, deserializationContext));
        if (this._injectables != null) {
            injectValues(deserializationContext, createUsingDelegate);
        }
        return createUsingDelegate;
    }

    public Object deserializeWithObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserializeFromObject(jsonParser, deserializationContext);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        Object objectId;
        if (this._objectIdReader != null) {
            if (jsonParser.canReadObjectId() && (objectId = jsonParser.getObjectId()) != null) {
                return _handleTypedObjectId(jsonParser, deserializationContext, typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext), objectId);
            }
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken != null) {
                if (currentToken.isScalarValue()) {
                    return deserializeFromObjectId(jsonParser, deserializationContext);
                }
                if (currentToken == JsonToken.START_OBJECT) {
                    currentToken = jsonParser.nextToken();
                }
                if (currentToken == JsonToken.FIELD_NAME && this._objectIdReader.maySerializeAsObject() && this._objectIdReader.isValidReferencePropertyName(jsonParser.currentName(), jsonParser)) {
                    return deserializeFromObjectId(jsonParser, deserializationContext);
                }
            }
        }
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public SettableBeanProperty findBackReference(String str) {
        Map<String, SettableBeanProperty> map = this._backRefs;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        Object findDeserializationConverter;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findDeserializationConverter = annotationIntrospector.findDeserializationConverter(settableBeanProperty.getMember())) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = deserializationContext.converterInstance(settableBeanProperty.getMember(), findDeserializationConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext.getTypeFactory());
        return new StdDelegatingDeserializer(converterInstance, inputType, deserializationContext.findNonContextualValueDeserializer(inputType));
    }

    public SettableBeanProperty findProperty(PropertyName propertyName) {
        return findProperty(propertyName.getSimpleName());
    }

    @Deprecated
    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    public AccessPattern getEmptyAccessPattern() {
        return AccessPattern.DYNAMIC;
    }

    public Object getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
        try {
            return this._valueInstantiator.createUsingDefault(deserializationContext);
        } catch (IOException e3) {
            return ClassUtil.throwAsMappingException(deserializationContext, e3);
        }
    }

    public Collection<Object> getKnownPropertyNames() {
        ArrayList arrayList = new ArrayList();
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        return arrayList;
    }

    public AccessPattern getNullAccessPattern() {
        return AccessPattern.ALWAYS_NULL;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public JavaType getValueType() {
        return this._beanType;
    }

    public void handleIgnoredProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)) {
            jsonParser.skipChildren();
            return;
        }
        throw IgnoredPropertyException.from(jsonParser, obj, str, getKnownPropertyNames());
    }

    public Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException {
        JsonDeserializer<Object> _findSubclassDeserializer = _findSubclassDeserializer(deserializationContext, obj, tokenBuffer);
        if (_findSubclassDeserializer != null) {
            if (tokenBuffer != null) {
                tokenBuffer.writeEndObject();
                JsonParser asParser = tokenBuffer.asParser();
                asParser.nextToken();
                obj = _findSubclassDeserializer.deserialize(asParser, deserializationContext, obj);
            }
            return jsonParser != null ? _findSubclassDeserializer.deserialize(jsonParser, deserializationContext, obj) : obj;
        }
        if (tokenBuffer != null) {
            obj = handleUnknownProperties(deserializationContext, obj, tokenBuffer);
        }
        return jsonParser != null ? deserialize(jsonParser, deserializationContext, obj) : obj;
    }

    public Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException {
        tokenBuffer.writeEndObject();
        JsonParser asParser = tokenBuffer.asParser();
        while (asParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = asParser.currentName();
            asParser.nextToken();
            handleUnknownProperty(asParser, deserializationContext, obj, currentName);
        }
        return obj;
    }

    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (this._ignoreAllUnknown) {
            jsonParser.skipChildren();
            return;
        }
        if (IgnorePropertiesUtil.shouldIgnore(str, this._ignorableProps, this._includableProps)) {
            handleIgnoredProperty(jsonParser, deserializationContext, obj, str);
        }
        super.handleUnknownProperty(jsonParser, deserializationContext, obj, str);
    }

    public void handleUnknownVanilla(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        if (IgnorePropertiesUtil.shouldIgnore(str, this._ignorableProps, this._includableProps)) {
            handleIgnoredProperty(jsonParser, deserializationContext, obj, str);
            return;
        }
        SettableAnyProperty settableAnyProperty = this._anySetter;
        if (settableAnyProperty != null) {
            try {
                settableAnyProperty.deserializeAndSet(jsonParser, deserializationContext, obj, str);
            } catch (Exception e3) {
                wrapAndThrow(e3, obj, str, deserializationContext);
            }
        } else {
            handleUnknownProperty(jsonParser, deserializationContext, obj, str);
        }
    }

    public Class<?> handledType() {
        return this._beanType.getRawClass();
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    public boolean hasViews() {
        return this._needViewProcesing;
    }

    public void injectValues(DeserializationContext deserializationContext, Object obj) throws IOException {
        for (ValueInjector inject : this._injectables) {
            inject.inject(deserializationContext, obj);
        }
    }

    public boolean isCachable() {
        return true;
    }

    public boolean isCaseInsensitive() {
        return this._beanProperties.isCaseInsensitive();
    }

    public LogicalType logicalType() {
        return LogicalType.POJO;
    }

    public Iterator<SettableBeanProperty> properties() {
        BeanPropertyMap beanPropertyMap = this._beanProperties;
        if (beanPropertyMap != null) {
            return beanPropertyMap.iterator();
        }
        throw new IllegalStateException("Can only call after BeanDeserializer has been resolved");
    }

    public void replaceProperty(SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2) {
        this._beanProperties.replace(settableBeanProperty, settableBeanProperty2);
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        SettableBeanProperty[] settableBeanPropertyArr;
        JsonDeserializer<Object> valueDeserializer;
        JsonDeserializer<Object> unwrappingDeserializer;
        boolean z2 = false;
        ExternalTypeHandler.Builder builder = null;
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            settableBeanPropertyArr = this._valueInstantiator.getFromObjectArguments(deserializationContext.getConfig());
            if (!(this._ignorableProps == null && this._includableProps == null)) {
                int length = settableBeanPropertyArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    if (IgnorePropertiesUtil.shouldIgnore(settableBeanPropertyArr[i3].getName(), this._ignorableProps, this._includableProps)) {
                        settableBeanPropertyArr[i3].markAsIgnorable();
                    }
                }
            }
        } else {
            settableBeanPropertyArr = null;
        }
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            if (!next.hasValueDeserializer()) {
                JsonDeserializer<Object> findConvertingDeserializer = findConvertingDeserializer(deserializationContext, next);
                if (findConvertingDeserializer == null) {
                    findConvertingDeserializer = deserializationContext.findNonContextualValueDeserializer(next.getType());
                }
                _replaceProperty(this._beanProperties, settableBeanPropertyArr, next, next.withValueDeserializer(findConvertingDeserializer));
            }
        }
        Iterator<SettableBeanProperty> it2 = this._beanProperties.iterator();
        UnwrappedPropertyHandler unwrappedPropertyHandler = null;
        while (it2.hasNext()) {
            SettableBeanProperty next2 = it2.next();
            SettableBeanProperty _resolveManagedReferenceProperty = _resolveManagedReferenceProperty(deserializationContext, next2.withValueDeserializer(deserializationContext.handlePrimaryContextualization(next2.getValueDeserializer(), next2, next2.getType())));
            if (!(_resolveManagedReferenceProperty instanceof ManagedReferenceProperty)) {
                _resolveManagedReferenceProperty = _resolvedObjectIdProperty(deserializationContext, _resolveManagedReferenceProperty);
            }
            NameTransformer _findPropertyUnwrapper = _findPropertyUnwrapper(deserializationContext, _resolveManagedReferenceProperty);
            if (_findPropertyUnwrapper == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(_findPropertyUnwrapper)) == (valueDeserializer = _resolveManagedReferenceProperty.getValueDeserializer()) || unwrappingDeserializer == null) {
                SettableBeanProperty _resolveInnerClassValuedProperty = _resolveInnerClassValuedProperty(deserializationContext, _resolveMergeAndNullSettings(deserializationContext, _resolveManagedReferenceProperty, _resolveManagedReferenceProperty.getMetadata()));
                if (_resolveInnerClassValuedProperty != next2) {
                    _replaceProperty(this._beanProperties, settableBeanPropertyArr, next2, _resolveInnerClassValuedProperty);
                }
                if (_resolveInnerClassValuedProperty.hasValueTypeDeserializer()) {
                    TypeDeserializer valueTypeDeserializer = _resolveInnerClassValuedProperty.getValueTypeDeserializer();
                    if (valueTypeDeserializer.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
                        if (builder == null) {
                            builder = ExternalTypeHandler.builder(this._beanType);
                        }
                        builder.addExternal(_resolveInnerClassValuedProperty, valueTypeDeserializer);
                        this._beanProperties.remove(_resolveInnerClassValuedProperty);
                    }
                }
            } else {
                SettableBeanProperty withValueDeserializer = _resolveManagedReferenceProperty.withValueDeserializer(unwrappingDeserializer);
                if (unwrappedPropertyHandler == null) {
                    unwrappedPropertyHandler = new UnwrappedPropertyHandler();
                }
                unwrappedPropertyHandler.addProperty(withValueDeserializer);
                this._beanProperties.remove(withValueDeserializer);
            }
        }
        SettableAnyProperty settableAnyProperty = this._anySetter;
        if (settableAnyProperty != null && !settableAnyProperty.hasValueDeserializer()) {
            SettableAnyProperty settableAnyProperty2 = this._anySetter;
            this._anySetter = settableAnyProperty2.withValueDeserializer(findDeserializer(deserializationContext, settableAnyProperty2.getType(), this._anySetter.getProperty()));
        }
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (delegateType == null) {
                JavaType javaType = this._beanType;
                deserializationContext.reportBadDefinition(javaType, C0118y.g("Invalid delegate-creator definition for ", ClassUtil.getTypeDescription(javaType), ": value instantiator (", ClassUtil.classNameOf(this._valueInstantiator), ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'"));
            }
            this._delegateDeserializer = _findDelegateDeserializer(deserializationContext, delegateType, this._valueInstantiator.getDelegateCreator());
        }
        if (this._valueInstantiator.canCreateUsingArrayDelegate()) {
            JavaType arrayDelegateType = this._valueInstantiator.getArrayDelegateType(deserializationContext.getConfig());
            if (arrayDelegateType == null) {
                JavaType javaType2 = this._beanType;
                deserializationContext.reportBadDefinition(javaType2, C0118y.g("Invalid delegate-creator definition for ", ClassUtil.getTypeDescription(javaType2), ": value instantiator (", ClassUtil.classNameOf(this._valueInstantiator), ") returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'"));
            }
            this._arrayDelegateDeserializer = _findDelegateDeserializer(deserializationContext, arrayDelegateType, this._valueInstantiator.getArrayDelegateCreator());
        }
        if (settableBeanPropertyArr != null) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, settableBeanPropertyArr, this._beanProperties);
        }
        if (builder != null) {
            this._externalTypeIdHandler = builder.build(this._beanProperties);
            this._nonStandardCreation = true;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler;
        if (unwrappedPropertyHandler != null) {
            this._nonStandardCreation = true;
        }
        if (this._vanillaProcessing && !this._nonStandardCreation) {
            z2 = true;
        }
        this._vanillaProcessing = z2;
    }

    public Boolean supportsUpdate(DeserializationConfig deserializationConfig) {
        return Boolean.TRUE;
    }

    public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer);

    public BeanDeserializerBase withBeanProperties(BeanPropertyMap beanPropertyMap) {
        throw new UnsupportedOperationException("Class " + getClass().getName() + " does not override `withBeanProperties()`, needs to");
    }

    public abstract BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2);

    @Deprecated
    public BeanDeserializerBase withIgnorableProperties(Set<String> set) {
        return withByNameInclusion(set, this._includableProps);
    }

    public abstract BeanDeserializerBase withIgnoreAllUnknown(boolean z2);

    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader objectIdReader);

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, str);
    }

    public Object wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        ClassUtil.throwIfError(th);
        if (th instanceof IOException) {
            throw ((IOException) th);
        } else if (deserializationContext != null) {
            if (!deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS)) {
                ClassUtil.throwIfRTE(th);
            }
            return deserializationContext.handleInstantiationProblem(this._beanType.getRawClass(), (Object) null, th);
        } else {
            throw new IllegalArgumentException(th.getMessage(), th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        r1 = r1._propertyBasedCreator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty findProperty(java.lang.String r2) {
        /*
            r1 = this;
            com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r0 = r1._beanProperties
            if (r0 != 0) goto L_0x0006
            r0 = 0
            goto L_0x000a
        L_0x0006:
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r0.find((java.lang.String) r2)
        L_0x000a:
            if (r0 != 0) goto L_0x0014
            com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r1 = r1._propertyBasedCreator
            if (r1 == 0) goto L_0x0014
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r1.findCreatorProperty((java.lang.String) r2)
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.findProperty(java.lang.String):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000c, code lost:
        r1 = r1._propertyBasedCreator;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty findProperty(int r2) {
        /*
            r1 = this;
            com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r0 = r1._beanProperties
            if (r0 != 0) goto L_0x0006
            r0 = 0
            goto L_0x000a
        L_0x0006:
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r0.find((int) r2)
        L_0x000a:
            if (r0 != 0) goto L_0x0014
            com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r1 = r1._propertyBasedCreator
            if (r1 == 0) goto L_0x0014
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r0 = r1.findCreatorProperty((int) r2)
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerBase.findProperty(int):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase) {
        this(beanDeserializerBase, beanDeserializerBase._ignoreAllUnknown);
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, boolean z2) {
        super(beanDeserializerBase._beanType);
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._beanProperties = beanDeserializerBase._beanProperties;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = z2;
        this._includableProps = beanDeserializerBase._includableProps;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, NameTransformer nameTransformer) {
        super(beanDeserializerBase._beanType);
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = nameTransformer != null || beanDeserializerBase._ignoreAllUnknown;
        this._includableProps = beanDeserializerBase._includableProps;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        UnwrappedPropertyHandler unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        if (nameTransformer != null) {
            unwrappedPropertyHandler = unwrappedPropertyHandler != null ? unwrappedPropertyHandler.renameAll(nameTransformer) : unwrappedPropertyHandler;
            this._beanProperties = beanDeserializerBase._beanProperties.renameAll(nameTransformer);
        } else {
            this._beanProperties = beanDeserializerBase._beanProperties;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = false;
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, ObjectIdReader objectIdReader) {
        super(beanDeserializerBase._beanType);
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        this._includableProps = beanDeserializerBase._includableProps;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._objectIdReader = objectIdReader;
        if (objectIdReader == null) {
            this._beanProperties = beanDeserializerBase._beanProperties;
            this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
            return;
        }
        this._beanProperties = beanDeserializerBase._beanProperties.withProperty(new ObjectIdValueProperty(objectIdReader, PropertyMetadata.STD_REQUIRED));
        this._vanillaProcessing = false;
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, Set<String> set, Set<String> set2) {
        super(beanDeserializerBase._beanType);
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = set;
        this._ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        this._includableProps = set2;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._beanProperties = beanDeserializerBase._beanProperties.withoutProperties(set, set2);
    }

    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, BeanPropertyMap beanPropertyMap) {
        super(beanDeserializerBase._beanType);
        this._beanType = beanDeserializerBase._beanType;
        this._valueInstantiator = beanDeserializerBase._valueInstantiator;
        this._delegateDeserializer = beanDeserializerBase._delegateDeserializer;
        this._arrayDelegateDeserializer = beanDeserializerBase._arrayDelegateDeserializer;
        this._propertyBasedCreator = beanDeserializerBase._propertyBasedCreator;
        this._beanProperties = beanPropertyMap;
        this._backRefs = beanDeserializerBase._backRefs;
        this._ignorableProps = beanDeserializerBase._ignorableProps;
        this._ignoreAllUnknown = beanDeserializerBase._ignoreAllUnknown;
        this._includableProps = beanDeserializerBase._includableProps;
        this._anySetter = beanDeserializerBase._anySetter;
        this._injectables = beanDeserializerBase._injectables;
        this._objectIdReader = beanDeserializerBase._objectIdReader;
        this._nonStandardCreation = beanDeserializerBase._nonStandardCreation;
        this._unwrappedPropertyHandler = beanDeserializerBase._unwrappedPropertyHandler;
        this._needViewProcesing = beanDeserializerBase._needViewProcesing;
        this._serializationShape = beanDeserializerBase._serializationShape;
        this._vanillaProcessing = beanDeserializerBase._vanillaProcessing;
    }

    @Deprecated
    public BeanDeserializerBase(BeanDeserializerBase beanDeserializerBase, Set<String> set) {
        this(beanDeserializerBase, set, beanDeserializerBase._includableProps);
    }
}
