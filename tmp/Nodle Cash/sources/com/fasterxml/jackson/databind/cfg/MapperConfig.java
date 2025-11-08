package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.DefaultBaseTypeLimitingValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class MapperConfig<T extends MapperConfig<T>> implements ClassIntrospector.MixInResolver, Serializable {
    protected static final JsonFormat.Value EMPTY_FORMAT = JsonFormat.Value.empty();
    protected static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();
    private static final long serialVersionUID = 2;
    protected final BaseSettings _base;
    protected final long _mapperFeatures;

    public MapperConfig(BaseSettings baseSettings, long j2) {
        this._base = baseSettings;
        this._mapperFeatures = j2;
    }

    public static <F extends Enum<F> & ConfigFeature> int collectFeatureDefaults(Class<F> cls) {
        int i3 = 0;
        for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
            ConfigFeature configFeature = (ConfigFeature) enumR;
            if (configFeature.enabledByDefault()) {
                i3 |= configFeature.getMask();
            }
        }
        return i3;
    }

    public final boolean canOverrideAccessModifiers() {
        return isEnabled(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS);
    }

    public SerializableString compileString(String str) {
        return new SerializedString(str);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getTypeFactory().constructSpecializedType(javaType, cls, true);
    }

    public final JavaType constructType(Class<?> cls) {
        return getTypeFactory().constructType((Type) cls);
    }

    public abstract ConfigOverride findConfigOverride(Class<?> cls);

    public abstract PropertyName findRootName(JavaType javaType);

    public abstract PropertyName findRootName(Class<?> cls);

    public final AccessorNamingStrategy.Provider getAccessorNaming() {
        return this._base.getAccessorNaming();
    }

    public abstract Class<?> getActiveView();

    public AnnotationIntrospector getAnnotationIntrospector() {
        return isEnabled(MapperFeature.USE_ANNOTATIONS) ? this._base.getAnnotationIntrospector() : NopAnnotationIntrospector.instance;
    }

    public abstract ContextAttributes getAttributes();

    public Base64Variant getBase64Variant() {
        return this._base.getBase64Variant();
    }

    public ClassIntrospector getClassIntrospector() {
        return this._base.getClassIntrospector();
    }

    public abstract ConfigOverride getConfigOverride(Class<?> cls);

    public final DateFormat getDateFormat() {
        return this._base.getDateFormat();
    }

    public abstract JsonInclude.Value getDefaultInclusion(Class<?> cls, Class<?> cls2);

    public JsonInclude.Value getDefaultInclusion(Class<?> cls, Class<?> cls2, JsonInclude.Value value) {
        return JsonInclude.Value.mergeAll(value, getConfigOverride(cls).getInclude(), getConfigOverride(cls2).getIncludeAsProperty());
    }

    public abstract Boolean getDefaultMergeable();

    public abstract Boolean getDefaultMergeable(Class<?> cls);

    public abstract JsonFormat.Value getDefaultPropertyFormat(Class<?> cls);

    public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls);

    public abstract JsonIgnoreProperties.Value getDefaultPropertyIgnorals(Class<?> cls, AnnotatedClass annotatedClass);

    public abstract JsonInclude.Value getDefaultPropertyInclusion();

    public abstract JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls);

    public JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls, JsonInclude.Value value) {
        JsonInclude.Value include = getConfigOverride(cls).getInclude();
        return include != null ? include : value;
    }

    public abstract JsonIncludeProperties.Value getDefaultPropertyInclusions(Class<?> cls, AnnotatedClass annotatedClass);

    public abstract JsonSetter.Value getDefaultSetterInfo();

    public final TypeResolverBuilder<?> getDefaultTyper(JavaType javaType) {
        return this._base.getTypeResolverBuilder();
    }

    public abstract VisibilityChecker<?> getDefaultVisibilityChecker();

    public abstract VisibilityChecker<?> getDefaultVisibilityChecker(Class<?> cls, AnnotatedClass annotatedClass);

    public final HandlerInstantiator getHandlerInstantiator() {
        return this._base.getHandlerInstantiator();
    }

    public final Locale getLocale() {
        return this._base.getLocale();
    }

    public PolymorphicTypeValidator getPolymorphicTypeValidator() {
        PolymorphicTypeValidator polymorphicTypeValidator = this._base.getPolymorphicTypeValidator();
        return (polymorphicTypeValidator != LaissezFaireSubTypeValidator.instance || !isEnabled(MapperFeature.BLOCK_UNSAFE_POLYMORPHIC_BASE_TYPES)) ? polymorphicTypeValidator : new DefaultBaseTypeLimitingValidator();
    }

    public final PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._base.getPropertyNamingStrategy();
    }

    public abstract SubtypeResolver getSubtypeResolver();

    public final TimeZone getTimeZone() {
        return this._base.getTimeZone();
    }

    public final TypeFactory getTypeFactory() {
        return this._base.getTypeFactory();
    }

    public boolean hasExplicitTimeZone() {
        return this._base.hasExplicitTimeZone();
    }

    @Deprecated
    public final boolean hasMapperFeatures(int i3) {
        long j2 = this._mapperFeatures;
        long j3 = (long) i3;
        return (j2 & j3) == j3;
    }

    public BeanDescription introspectClassAnnotations(Class<?> cls) {
        return introspectClassAnnotations(constructType(cls));
    }

    public BeanDescription introspectDirectClassAnnotations(Class<?> cls) {
        return introspectDirectClassAnnotations(constructType(cls));
    }

    public final boolean isAnnotationProcessingEnabled() {
        return isEnabled(MapperFeature.USE_ANNOTATIONS);
    }

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return mapperFeature.enabledIn(this._mapperFeatures);
    }

    public final boolean shouldSortPropertiesAlphabetically() {
        return isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = r0.typeIdResolverInstance(r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.jsontype.TypeIdResolver typeIdResolverInstance(com.fasterxml.jackson.databind.introspect.Annotated r2, java.lang.Class<? extends com.fasterxml.jackson.databind.jsontype.TypeIdResolver> r3) {
        /*
            r1 = this;
            com.fasterxml.jackson.databind.cfg.HandlerInstantiator r0 = r1.getHandlerInstantiator()
            if (r0 == 0) goto L_0x000d
            com.fasterxml.jackson.databind.jsontype.TypeIdResolver r2 = r0.typeIdResolverInstance(r1, r2, r3)
            if (r2 == 0) goto L_0x000d
            return r2
        L_0x000d:
            boolean r1 = r1.canOverrideAccessModifiers()
            java.lang.Object r1 = com.fasterxml.jackson.databind.util.ClassUtil.createInstance(r3, r1)
            com.fasterxml.jackson.databind.jsontype.TypeIdResolver r1 = (com.fasterxml.jackson.databind.jsontype.TypeIdResolver) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.cfg.MapperConfig.typeIdResolverInstance(com.fasterxml.jackson.databind.introspect.Annotated, java.lang.Class):com.fasterxml.jackson.databind.jsontype.TypeIdResolver");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r2 = r0.typeResolverBuilderInstance(r1, r2, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder<?> typeResolverBuilderInstance(com.fasterxml.jackson.databind.introspect.Annotated r2, java.lang.Class<? extends com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder<?>> r3) {
        /*
            r1 = this;
            com.fasterxml.jackson.databind.cfg.HandlerInstantiator r0 = r1.getHandlerInstantiator()
            if (r0 == 0) goto L_0x000d
            com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r2 = r0.typeResolverBuilderInstance(r1, r2, r3)
            if (r2 == 0) goto L_0x000d
            return r2
        L_0x000d:
            boolean r1 = r1.canOverrideAccessModifiers()
            java.lang.Object r1 = com.fasterxml.jackson.databind.util.ClassUtil.createInstance(r3, r1)
            com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder r1 = (com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.cfg.MapperConfig.typeResolverBuilderInstance(com.fasterxml.jackson.databind.introspect.Annotated, java.lang.Class):com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder");
    }

    public abstract boolean useRootWrapping();

    public abstract T with(MapperFeature mapperFeature, boolean z2);

    public abstract T with(MapperFeature... mapperFeatureArr);

    public abstract T without(MapperFeature... mapperFeatureArr);

    public final JavaType constructType(TypeReference<?> typeReference) {
        return getTypeFactory().constructType(typeReference.getType());
    }

    public BeanDescription introspectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    public final BeanDescription introspectDirectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    public MapperConfig(MapperConfig<T> mapperConfig, long j2) {
        this._base = mapperConfig._base;
        this._mapperFeatures = j2;
    }

    public MapperConfig(MapperConfig<T> mapperConfig, BaseSettings baseSettings) {
        this._base = baseSettings;
        this._mapperFeatures = mapperConfig._mapperFeatures;
    }

    public MapperConfig(MapperConfig<T> mapperConfig) {
        this._base = mapperConfig._base;
        this._mapperFeatures = mapperConfig._mapperFeatures;
    }
}
