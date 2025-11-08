package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    private static final long AUTO_DETECT_MASK = ((((MapperFeature.AUTO_DETECT_FIELDS.getLongMask() | MapperFeature.AUTO_DETECT_GETTERS.getLongMask()) | MapperFeature.AUTO_DETECT_IS_GETTERS.getLongMask()) | MapperFeature.AUTO_DETECT_SETTERS.getLongMask()) | MapperFeature.AUTO_DETECT_CREATORS.getLongMask());
    private static final long DEFAULT_MAPPER_FEATURES = MapperFeature.collectLongDefaults();
    protected static final ConfigOverride EMPTY_OVERRIDE = ConfigOverride.empty();
    protected final ContextAttributes _attributes;
    protected final ConfigOverrides _configOverrides;
    protected final DatatypeFeatures _datatypeFeatures;
    protected final SimpleMixInResolver _mixIns;
    protected final PropertyName _rootName;
    protected final RootNameLookup _rootNames;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;

    public MapperConfigBase(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides, DatatypeFeatures datatypeFeatures) {
        super(baseSettings, DEFAULT_MAPPER_FEATURES);
        this._mixIns = simpleMixInResolver;
        this._subtypeResolver = subtypeResolver;
        this._rootNames = rootNameLookup;
        this._rootName = null;
        this._view = null;
        this._attributes = ContextAttributes.getEmpty();
        this._configOverrides = configOverrides;
        this._datatypeFeatures = datatypeFeatures;
    }

    public DatatypeFeatures _datatypeFeatures() {
        return this._datatypeFeatures;
    }

    public abstract T _with(DatatypeFeatures datatypeFeatures);

    public abstract T _withBase(BaseSettings baseSettings);

    public abstract T _withMapperFeatures(long j2);

    public ClassIntrospector.MixInResolver copy() {
        throw new UnsupportedOperationException();
    }

    public final ConfigOverride findConfigOverride(Class<?> cls) {
        return this._configOverrides.findOverride(cls);
    }

    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixIns.findMixInClassFor(cls);
    }

    public PropertyName findRootName(JavaType javaType) {
        PropertyName propertyName = this._rootName;
        if (propertyName != null) {
            return propertyName;
        }
        return this._rootNames.findRootName(javaType, (MapperConfig<?>) this);
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final ContextAttributes getAttributes() {
        return this._attributes;
    }

    public final ConfigOverride getConfigOverride(Class<?> cls) {
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        return findOverride == null ? EMPTY_OVERRIDE : findOverride;
    }

    public final JsonInclude.Value getDefaultInclusion(Class<?> cls, Class<?> cls2) {
        JsonInclude.Value includeAsProperty = getConfigOverride(cls2).getIncludeAsProperty();
        JsonInclude.Value defaultPropertyInclusion = getDefaultPropertyInclusion(cls);
        return defaultPropertyInclusion == null ? includeAsProperty : defaultPropertyInclusion.withOverrides(includeAsProperty);
    }

    public Boolean getDefaultMergeable() {
        return this._configOverrides.getDefaultMergeable();
    }

    public final JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return this._configOverrides.findFormatDefaults(cls);
    }

    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls) {
        JsonIgnoreProperties.Value ignorals;
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        if (findOverride == null || (ignorals = findOverride.getIgnorals()) == null) {
            return null;
        }
        return ignorals;
    }

    public final JsonInclude.Value getDefaultPropertyInclusion() {
        return this._configOverrides.getDefaultInclusion();
    }

    public final JsonIncludeProperties.Value getDefaultPropertyInclusions(Class<?> cls, AnnotatedClass annotatedClass) {
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findPropertyInclusionByName(this, annotatedClass);
    }

    public final JsonSetter.Value getDefaultSetterInfo() {
        return this._configOverrides.getDefaultSetterInfo();
    }

    public final VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker defaultVisibility = this._configOverrides.getDefaultVisibility();
        long j2 = this._mapperFeatures;
        long j3 = AUTO_DETECT_MASK;
        if ((j2 & j3) == j3) {
            return defaultVisibility;
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            defaultVisibility = defaultVisibility.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            defaultVisibility = defaultVisibility.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibility = defaultVisibility.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            defaultVisibility = defaultVisibility.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(MapperFeature.AUTO_DETECT_CREATORS) ? defaultVisibility.withCreatorVisibility(JsonAutoDetect.Visibility.NONE) : defaultVisibility;
    }

    public final PropertyName getFullRootName() {
        return this._rootName;
    }

    @Deprecated
    public final String getRootName() {
        PropertyName propertyName = this._rootName;
        if (propertyName == null) {
            return null;
        }
        return propertyName.getSimpleName();
    }

    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final int mixInCount() {
        return this._mixIns.localSize();
    }

    public abstract T with(ContextAttributes contextAttributes);

    public abstract T with(SubtypeResolver subtypeResolver);

    public final T withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    public T withAttribute(Object obj, Object obj2) {
        return with(getAttributes().withSharedAttribute(obj, obj2));
    }

    public T withAttributes(Map<?, ?> map) {
        return with(getAttributes().withSharedAttributes(map));
    }

    public final T withFeatures(DatatypeFeature... datatypeFeatureArr) {
        return _with(_datatypeFeatures().withFeatures(datatypeFeatureArr));
    }

    public final T withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    public abstract T withRootName(PropertyName propertyName);

    public T withRootName(String str) {
        if (str == null) {
            return withRootName((PropertyName) null);
        }
        return withRootName(PropertyName.construct(str));
    }

    public abstract T withView(Class<?> cls);

    public T withoutAttribute(Object obj) {
        return with(getAttributes().withoutSharedAttribute(obj));
    }

    public final T withoutFeatures(DatatypeFeature... datatypeFeatureArr) {
        return _with(_datatypeFeatures().withoutFeatures(datatypeFeatureArr));
    }

    public Boolean getDefaultMergeable(Class<?> cls) {
        Boolean mergeable;
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        if (findOverride == null || (mergeable = findOverride.getMergeable()) == null) {
            return this._configOverrides.getDefaultMergeable();
        }
        return mergeable;
    }

    public final JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls) {
        JsonInclude.Value include = getConfigOverride(cls).getInclude();
        JsonInclude.Value defaultPropertyInclusion = getDefaultPropertyInclusion();
        if (defaultPropertyInclusion == null) {
            return include;
        }
        return defaultPropertyInclusion.withOverrides(include);
    }

    public final T without(MapperFeature... mapperFeatureArr) {
        long j2 = this._mapperFeatures;
        for (MapperFeature longMask : mapperFeatureArr) {
            j2 &= ~longMask.getLongMask();
        }
        if (j2 == this._mapperFeatures) {
            return this;
        }
        return _withMapperFeatures(j2);
    }

    public PropertyName findRootName(Class<?> cls) {
        PropertyName propertyName = this._rootName;
        if (propertyName != null) {
            return propertyName;
        }
        return this._rootNames.findRootName(cls, (MapperConfig<?>) this);
    }

    public final JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls, AnnotatedClass annotatedClass) {
        JsonIgnoreProperties.Value value;
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            value = null;
        } else {
            value = annotationIntrospector.findPropertyIgnoralByName(this, annotatedClass);
        }
        return JsonIgnoreProperties.Value.merge(value, getDefaultPropertyIgnorals(cls));
    }

    public final T with(MapperFeature... mapperFeatureArr) {
        long j2 = this._mapperFeatures;
        for (MapperFeature longMask : mapperFeatureArr) {
            j2 |= longMask.getLongMask();
        }
        if (j2 == this._mapperFeatures) {
            return this;
        }
        return _withMapperFeatures(j2);
    }

    public final T without(DatatypeFeature datatypeFeature) {
        return _with(_datatypeFeatures().without(datatypeFeature));
    }

    public final T with(MapperFeature mapperFeature, boolean z2) {
        long j2;
        if (z2) {
            j2 = mapperFeature.getLongMask() | this._mapperFeatures;
        } else {
            j2 = (~mapperFeature.getLongMask()) & this._mapperFeatures;
        }
        if (j2 == this._mapperFeatures) {
            return this;
        }
        return _withMapperFeatures(j2);
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup, ConfigOverrides configOverrides) {
        super(mapperConfigBase, mapperConfigBase._base.copy());
        this._mixIns = simpleMixInResolver;
        this._subtypeResolver = subtypeResolver;
        this._rootNames = rootNameLookup;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public final T with(DatatypeFeature datatypeFeature) {
        return _with(_datatypeFeatures().with(datatypeFeature));
    }

    public final VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> cls, AnnotatedClass annotatedClass) {
        VisibilityChecker visibilityChecker;
        if (ClassUtil.isJDKClass(cls)) {
            visibilityChecker = VisibilityChecker.Std.allPublicInstance();
        } else {
            visibilityChecker = getDefaultVisibilityChecker();
        }
        AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
        if (annotationIntrospector != null) {
            visibilityChecker = annotationIntrospector.findAutoDetectVisibility(annotatedClass, visibilityChecker);
        }
        ConfigOverride findOverride = this._configOverrides.findOverride(cls);
        return findOverride != null ? visibilityChecker.withOverrides(findOverride.getVisibility()) : visibilityChecker;
    }

    public final T with(DatatypeFeature datatypeFeature, boolean z2) {
        DatatypeFeatures _datatypeFeatures2 = _datatypeFeatures();
        return _with(z2 ? _datatypeFeatures2.with(datatypeFeature) : _datatypeFeatures2.without(datatypeFeature));
    }

    public final T with(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    public final T with(ClassIntrospector classIntrospector) {
        return _withBase(this._base.withClassIntrospector(classIntrospector));
    }

    public final T with(TypeFactory typeFactory) {
        return _withBase(this._base.withTypeFactory(typeFactory));
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public final T with(TypeResolverBuilder<?> typeResolverBuilder) {
        return _withBase(this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    public final T with(PropertyNamingStrategy propertyNamingStrategy) {
        return _withBase(this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    public final T with(AccessorNamingStrategy.Provider provider) {
        return _withBase(this._base.withAccessorNaming(provider));
    }

    public final T with(HandlerInstantiator handlerInstantiator) {
        return _withBase(this._base.withHandlerInstantiator(handlerInstantiator));
    }

    public final T with(Base64Variant base64Variant) {
        return _withBase(this._base.with(base64Variant));
    }

    public T with(DateFormat dateFormat) {
        return _withBase(this._base.withDateFormat(dateFormat));
    }

    public final T with(Locale locale) {
        return _withBase(this._base.with(locale));
    }

    public final T with(TimeZone timeZone) {
        return _withBase(this._base.with(timeZone));
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, BaseSettings baseSettings) {
        super(mapperConfigBase, baseSettings);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, long j2) {
        super(mapperConfigBase, j2);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, SubtypeResolver subtypeResolver) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, PropertyName propertyName) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = propertyName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, Class<?> cls) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = cls;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, SimpleMixInResolver simpleMixInResolver) {
        super(mapperConfigBase);
        this._mixIns = simpleMixInResolver;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, ContextAttributes contextAttributes) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = contextAttributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = mapperConfigBase._datatypeFeatures;
    }

    public MapperConfigBase(MapperConfigBase<CFG, T> mapperConfigBase, DatatypeFeatures datatypeFeatures) {
        super(mapperConfigBase);
        this._mixIns = mapperConfigBase._mixIns;
        this._subtypeResolver = mapperConfigBase._subtypeResolver;
        this._rootNames = mapperConfigBase._rootNames;
        this._rootName = mapperConfigBase._rootName;
        this._view = mapperConfigBase._view;
        this._attributes = mapperConfigBase._attributes;
        this._configOverrides = mapperConfigBase._configOverrides;
        this._datatypeFeatures = datatypeFeatures;
    }
}
