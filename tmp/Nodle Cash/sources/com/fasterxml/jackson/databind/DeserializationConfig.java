package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.CoercionAction;
import com.fasterxml.jackson.databind.cfg.CoercionConfigs;
import com.fasterxml.jackson.databind.cfg.CoercionInputShape;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.util.Collection;

public final class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> implements Serializable {
    private static final int DESER_FEATURE_DEFAULTS = MapperConfig.collectFeatureDefaults(DeserializationFeature.class);
    private static final long serialVersionUID = 2;
    protected final CoercionConfigs _coercionConfigs;
    protected final ConstructorDetector _ctorDetector;
    protected final int _deserFeatures;
    protected final int _formatReadFeatures;
    protected final int _formatReadFeaturesToChange;
    protected final JsonNodeFactory _nodeFactory;
    protected final int _parserFeatures;
    protected final int _parserFeaturesToChange;
    protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;

    public DeserializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides, CoercionConfigs coercionConfigs, DatatypeFeatures datatypeFeatures) {
        super(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides, datatypeFeatures);
        this._deserFeatures = DESER_FEATURE_DEFAULTS;
        this._problemHandlers = null;
        this._nodeFactory = JsonNodeFactory.instance;
        this._ctorDetector = null;
        this._coercionConfigs = coercionConfigs;
        this._parserFeatures = 0;
        this._parserFeaturesToChange = 0;
        this._formatReadFeatures = 0;
        this._formatReadFeaturesToChange = 0;
    }

    private DeserializationConfig _withJsonReadFeatures(FormatFeature... formatFeatureArr) {
        JsonParser.Feature mappedFeature;
        int i3 = this._parserFeatures;
        int i4 = this._parserFeaturesToChange;
        int i5 = this._formatReadFeatures;
        int i6 = this._formatReadFeaturesToChange;
        int i7 = i5;
        int i8 = i6;
        int i9 = i3;
        int i10 = i4;
        for (JsonReadFeature jsonReadFeature : formatFeatureArr) {
            int mask = jsonReadFeature.getMask();
            i7 |= mask;
            i8 |= mask;
            if ((jsonReadFeature instanceof JsonReadFeature) && (mappedFeature = jsonReadFeature.mappedFeature()) != null) {
                int mask2 = mappedFeature.getMask();
                i9 |= mask2;
                i10 |= mask2;
            }
        }
        if (this._formatReadFeatures == i7 && this._formatReadFeaturesToChange == i8 && this._parserFeatures == i9 && this._parserFeaturesToChange == i10) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i9, i10, i7, i8);
    }

    private DeserializationConfig _withoutJsonReadFeatures(FormatFeature... formatFeatureArr) {
        JsonParser.Feature mappedFeature;
        int i3 = this._parserFeatures;
        int i4 = this._parserFeaturesToChange;
        int i5 = this._formatReadFeatures;
        int i6 = this._formatReadFeaturesToChange;
        int i7 = i5;
        int i8 = i6;
        int i9 = i3;
        int i10 = i4;
        for (JsonReadFeature jsonReadFeature : formatFeatureArr) {
            int mask = jsonReadFeature.getMask();
            i7 &= ~mask;
            i8 |= mask;
            if ((jsonReadFeature instanceof JsonReadFeature) && (mappedFeature = jsonReadFeature.mappedFeature()) != null) {
                int mask2 = mappedFeature.getMask();
                i9 &= ~mask2;
                i10 |= mask2;
            }
        }
        if (this._formatReadFeatures == i7 && this._formatReadFeaturesToChange == i8 && this._parserFeatures == i9 && this._parserFeaturesToChange == i10) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i9, i10, i7, i8);
    }

    public CoercionAction findCoercionAction(LogicalType logicalType, Class<?> cls, CoercionInputShape coercionInputShape) {
        return this._coercionConfigs.findCoercion(this, logicalType, cls, coercionInputShape);
    }

    public CoercionAction findCoercionFromBlankString(LogicalType logicalType, Class<?> cls, CoercionAction coercionAction) {
        return this._coercionConfigs.findCoercionFromBlankString(this, logicalType, cls, coercionAction);
    }

    public TypeDeserializer findTypeDeserializer(JavaType javaType) throws JsonMappingException {
        Collection<NamedType> collection;
        AnnotatedClass classInfo = introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> findTypeResolver = getAnnotationIntrospector().findTypeResolver(this, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = getDefaultTyper(javaType);
            collection = null;
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, classInfo);
        }
        return findTypeResolver.buildTypeDeserializer(this, javaType, collection);
    }

    public BaseSettings getBaseSettings() {
        return this._base;
    }

    public ConstructorDetector getConstructorDetector() {
        ConstructorDetector constructorDetector = this._ctorDetector;
        return constructorDetector == null ? ConstructorDetector.DEFAULT : constructorDetector;
    }

    public final int getDeserializationFeatures() {
        return this._deserFeatures;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public final boolean hasDeserializationFeatures(int i3) {
        return (this._deserFeatures & i3) == i3;
    }

    public final boolean hasSomeOfFeatures(int i3) {
        return (this._deserFeatures & i3) != 0;
    }

    public JsonParser initialize(JsonParser jsonParser) {
        int i3 = this._parserFeaturesToChange;
        if (i3 != 0) {
            jsonParser.overrideStdFeatures(this._parserFeatures, i3);
        }
        int i4 = this._formatReadFeaturesToChange;
        if (i4 != 0) {
            jsonParser.overrideFormatFeatures(this._formatReadFeatures, i4);
        }
        return jsonParser;
    }

    public BeanDescription introspect(JavaType javaType) {
        return getClassIntrospector().forDeserialization(this, javaType, this);
    }

    public BeanDescription introspectForBuilder(JavaType javaType, BeanDescription beanDescription) {
        return getClassIntrospector().forDeserializationWithBuilder(this, javaType, this, beanDescription);
    }

    public BeanDescription introspectForCreation(JavaType javaType) {
        return getClassIntrospector().forCreation(this, javaType, this);
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._deserFeatures & deserializationFeature.getMask()) != 0;
    }

    public final boolean requiresFullValue() {
        return DeserializationFeature.FAIL_ON_TRAILING_TOKENS.enabledIn(this._deserFeatures);
    }

    public boolean useRootWrapping() {
        PropertyName propertyName = this._rootName;
        return propertyName != null ? !propertyName.isEmpty() : isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }

    public DeserializationConfig withFeatures(DeserializationFeature... deserializationFeatureArr) {
        int i3 = this._deserFeatures;
        int i4 = i3;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i4 |= mask.getMask();
        }
        if (i4 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i4, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        return LinkedNode.contains(this._problemHandlers, deserializationProblemHandler) ? this : new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) new LinkedNode(deserializationProblemHandler, this._problemHandlers));
    }

    public DeserializationConfig withNoProblemHandlers() {
        return this._problemHandlers == null ? this : new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) null);
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature) {
        int i3 = this._deserFeatures & (~deserializationFeature.getMask());
        if (i3 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i3, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        int i3 = this._deserFeatures;
        int i4 = i3;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i4 &= ~mask.getMask();
        }
        if (i4 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i4, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public final DeserializationConfig _with(DatatypeFeatures datatypeFeatures) {
        return new DeserializationConfig(this, datatypeFeatures);
    }

    public final DeserializationConfig _withBase(BaseSettings baseSettings) {
        return this._base == baseSettings ? this : new DeserializationConfig(this, baseSettings);
    }

    public final DeserializationConfig _withMapperFeatures(long j2) {
        return new DeserializationConfig(this, j2, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    @Deprecated
    public BeanDescription introspectForBuilder(JavaType javaType) {
        return getClassIntrospector().forDeserializationWithBuilder(this, javaType, this);
    }

    public final boolean isEnabled(JsonParser.Feature feature, JsonFactory jsonFactory) {
        if ((feature.getMask() & this._parserFeaturesToChange) != 0) {
            return (this._parserFeatures & feature.getMask()) != 0;
        }
        return jsonFactory.isEnabled(feature);
    }

    public DeserializationConfig withRootName(PropertyName propertyName) {
        if (propertyName == null) {
            if (this._rootName == null) {
                return this;
            }
        } else if (propertyName.equals(this._rootName)) {
            return this;
        }
        return new DeserializationConfig(this, propertyName);
    }

    public DeserializationConfig withView(Class<?> cls) {
        return this._view == cls ? this : new DeserializationConfig(this, cls);
    }

    public DeserializationConfig with(SubtypeResolver subtypeResolver) {
        return this._subtypeResolver == subtypeResolver ? this : new DeserializationConfig(this, subtypeResolver);
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        int i3 = (~deserializationFeature.getMask()) & this._deserFeatures;
        int i4 = i3;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i4 &= ~mask.getMask();
        }
        if (i4 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i4, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(ContextAttributes contextAttributes) {
        return contextAttributes == this._attributes ? this : new DeserializationConfig(this, contextAttributes);
    }

    public JsonParser initialize(JsonParser jsonParser, FormatSchema formatSchema) {
        int i3 = this._parserFeaturesToChange;
        if (i3 != 0) {
            jsonParser.overrideStdFeatures(this._parserFeatures, i3);
        }
        int i4 = this._formatReadFeaturesToChange;
        if (i4 != 0) {
            jsonParser.overrideFormatFeatures(this._formatReadFeatures, i4);
        }
        if (formatSchema != null) {
            jsonParser.setSchema(formatSchema);
        }
        return jsonParser;
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature) {
        int mask = this._deserFeatures | deserializationFeature.getMask();
        if (mask == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, mask, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(JsonParser.Feature... featureArr) {
        int i3 = this._parserFeatures;
        int i4 = this._parserFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (JsonParser.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i5 |= mask2;
            i6 |= mask2;
        }
        if (this._parserFeatures == i5 && this._parserFeaturesToChange == i6) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i5, i6, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(JsonParser.Feature... featureArr) {
        int i3 = this._parserFeatures;
        int i4 = this._parserFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (JsonParser.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i5 &= ~mask2;
            i6 |= mask2;
        }
        if (this._parserFeatures == i5 && this._parserFeaturesToChange == i6) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i5, i6, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public final boolean isEnabled(DatatypeFeature datatypeFeature) {
        return this._datatypeFeatures.isEnabled(datatypeFeature);
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        int mask = deserializationFeature.getMask() | this._deserFeatures;
        int i3 = mask;
        for (DeserializationFeature mask2 : deserializationFeatureArr) {
            i3 |= mask2.getMask();
        }
        if (i3 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i3, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(JsonParser.Feature feature) {
        int i3 = this._parserFeatures & (~feature.getMask());
        int mask = this._parserFeaturesToChange | feature.getMask();
        if (this._parserFeatures == i3 && this._parserFeaturesToChange == mask) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i3, mask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(FormatFeature... formatFeatureArr) {
        if (formatFeatureArr.length > 0 && (formatFeatureArr[0] instanceof JsonReadFeature)) {
            return _withJsonReadFeatures(formatFeatureArr);
        }
        int i3 = this._formatReadFeatures;
        int i4 = this._formatReadFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i5 |= mask2;
            i6 |= mask2;
        }
        if (this._formatReadFeatures == i5 && this._formatReadFeaturesToChange == i6) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i5, i6);
    }

    public DeserializationConfig without(FormatFeature formatFeature) {
        if (formatFeature instanceof JsonReadFeature) {
            return _withoutJsonReadFeatures(formatFeature);
        }
        int i3 = this._formatReadFeatures & (~formatFeature.getMask());
        int mask = this._formatReadFeaturesToChange | formatFeature.getMask();
        if (this._formatReadFeatures == i3 && this._formatReadFeaturesToChange == mask) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i3, mask);
    }

    public DeserializationConfig withoutFeatures(FormatFeature... formatFeatureArr) {
        if (formatFeatureArr.length > 0 && (formatFeatureArr[0] instanceof JsonReadFeature)) {
            return _withoutJsonReadFeatures(formatFeatureArr);
        }
        int i3 = this._formatReadFeatures;
        int i4 = this._formatReadFeaturesToChange;
        int i5 = i3;
        int i6 = i4;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i5 &= ~mask2;
            i6 |= mask2;
        }
        if (this._formatReadFeatures == i5 && this._formatReadFeaturesToChange == i6) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i5, i6);
    }

    public DeserializationConfig(DeserializationConfig deserializationConfig, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides, CoercionConfigs coercionConfigs) {
        super(deserializationConfig, subtypeResolver, simpleMixInResolver, rootNameLookup, configOverrides);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._coercionConfigs = coercionConfigs;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    public DeserializationConfig with(JsonParser.Feature feature) {
        int mask = this._parserFeatures | feature.getMask();
        int mask2 = this._parserFeaturesToChange | feature.getMask();
        if (this._parserFeatures == mask && this._parserFeaturesToChange == mask2) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, mask, mask2, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(FormatFeature formatFeature) {
        if (formatFeature instanceof JsonReadFeature) {
            return _withJsonReadFeatures(formatFeature);
        }
        int mask = this._formatReadFeatures | formatFeature.getMask();
        int mask2 = this._formatReadFeaturesToChange | formatFeature.getMask();
        if (this._formatReadFeatures == mask && this._formatReadFeaturesToChange == mask2) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, mask, mask2);
    }

    public DeserializationConfig with(JsonNodeFactory jsonNodeFactory) {
        if (this._nodeFactory == jsonNodeFactory) {
            return this;
        }
        return new DeserializationConfig(this, jsonNodeFactory);
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, long j2, int i3, int i4, int i5, int i6, int i7) {
        super(deserializationConfig, j2);
        this._deserFeatures = i3;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = i4;
        this._parserFeaturesToChange = i5;
        this._formatReadFeatures = i6;
        this._formatReadFeaturesToChange = i7;
    }

    public DeserializationConfig with(ConstructorDetector constructorDetector) {
        if (this._ctorDetector == constructorDetector) {
            return this;
        }
        return new DeserializationConfig(this, constructorDetector);
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, SubtypeResolver subtypeResolver) {
        super(deserializationConfig, subtypeResolver);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, BaseSettings baseSettings) {
        super(deserializationConfig, baseSettings);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, JsonNodeFactory jsonNodeFactory) {
        super(deserializationConfig);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = jsonNodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, ConstructorDetector constructorDetector) {
        super(deserializationConfig);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = constructorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, LinkedNode<DeserializationProblemHandler> linkedNode) {
        super(deserializationConfig);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = linkedNode;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, PropertyName propertyName) {
        super(deserializationConfig, propertyName);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, Class<?> cls) {
        super(deserializationConfig, cls);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    public DeserializationConfig(DeserializationConfig deserializationConfig, ContextAttributes contextAttributes) {
        super(deserializationConfig, contextAttributes);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    public DeserializationConfig(DeserializationConfig deserializationConfig, SimpleMixInResolver simpleMixInResolver) {
        super(deserializationConfig, simpleMixInResolver);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    public DeserializationConfig(DeserializationConfig deserializationConfig, DatatypeFeatures datatypeFeatures) {
        super(deserializationConfig, datatypeFeatures);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._coercionConfigs = deserializationConfig._coercionConfigs;
        this._ctorDetector = deserializationConfig._ctorDetector;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }
}
