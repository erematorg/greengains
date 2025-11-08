package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.introspect.AnnotatedAndMetadata;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class BeanDescription {
    protected final JavaType _type;

    public BeanDescription(JavaType javaType) {
        this._type = javaType;
    }

    @Deprecated
    public abstract TypeBindings bindingsForBeanType();

    public abstract AnnotatedMember findAnyGetter();

    @Deprecated
    public AnnotatedMethod findAnySetter() {
        AnnotatedMember findAnySetterAccessor = findAnySetterAccessor();
        if (findAnySetterAccessor instanceof AnnotatedMethod) {
            return (AnnotatedMethod) findAnySetterAccessor;
        }
        return null;
    }

    public abstract AnnotatedMember findAnySetterAccessor();

    @Deprecated
    public AnnotatedMember findAnySetterField() {
        AnnotatedMember findAnySetterAccessor = findAnySetterAccessor();
        if (findAnySetterAccessor instanceof AnnotatedField) {
            return findAnySetterAccessor;
        }
        return null;
    }

    @Deprecated
    public abstract Map<String, AnnotatedMember> findBackReferenceProperties();

    public abstract List<BeanPropertyDefinition> findBackReferences();

    public String findClassDescription() {
        return null;
    }

    public abstract AnnotatedConstructor findDefaultConstructor();

    public abstract Class<?>[] findDefaultViews();

    public abstract Converter<Object, Object> findDeserializationConverter();

    public abstract JsonFormat.Value findExpectedFormat(JsonFormat.Value value);

    @Deprecated
    public abstract Method findFactoryMethod(Class<?>... clsArr);

    public abstract Map<Object, AnnotatedMember> findInjectables();

    public AnnotatedMember findJsonKeyAccessor() {
        return null;
    }

    public abstract AnnotatedMember findJsonValueAccessor();

    @Deprecated
    public abstract AnnotatedMethod findJsonValueMethod();

    public abstract AnnotatedMethod findMethod(String str, Class<?>[] clsArr);

    public abstract Class<?> findPOJOBuilder();

    public abstract JsonPOJOBuilder.Value findPOJOBuilderConfig();

    public abstract List<BeanPropertyDefinition> findProperties();

    public abstract JsonInclude.Value findPropertyInclusion(JsonInclude.Value value);

    public abstract Converter<Object, Object> findSerializationConverter();

    @Deprecated
    public abstract Constructor<?> findSingleArgConstructor(Class<?>... clsArr);

    public Class<?> getBeanClass() {
        return this._type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public abstract AnnotatedClass getClassInfo();

    public abstract List<AnnotatedConstructor> getConstructors();

    public abstract List<AnnotatedAndMetadata<AnnotatedConstructor, JsonCreator.Mode>> getConstructorsWithMode();

    public abstract List<AnnotatedMethod> getFactoryMethods();

    public abstract List<AnnotatedAndMetadata<AnnotatedMethod, JsonCreator.Mode>> getFactoryMethodsWithMode();

    public abstract Set<String> getIgnoredPropertyNames();

    public abstract ObjectIdInfo getObjectIdInfo();

    public JavaType getType() {
        return this._type;
    }

    public abstract boolean hasKnownClassAnnotations();

    public abstract Object instantiateBean(boolean z2);

    public boolean isNonStaticInnerClass() {
        return getClassInfo().isNonStaticInnerClass();
    }

    @Deprecated
    public abstract JavaType resolveType(Type type);
}
