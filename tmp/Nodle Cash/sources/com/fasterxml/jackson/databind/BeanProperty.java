package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;

public interface BeanProperty extends Named {
    public static final JsonFormat.Value EMPTY_FORMAT = new JsonFormat.Value();
    public static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();

    public static class Bogus implements BeanProperty {
        public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        }

        public List<PropertyName> findAliases(MapperConfig<?> mapperConfig) {
            return Collections.emptyList();
        }

        @Deprecated
        public JsonFormat.Value findFormatOverrides(AnnotationIntrospector annotationIntrospector) {
            return JsonFormat.Value.empty();
        }

        public JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls) {
            return JsonFormat.Value.empty();
        }

        public JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls) {
            return null;
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return null;
        }

        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            return null;
        }

        public PropertyName getFullName() {
            return PropertyName.NO_NAME;
        }

        public AnnotatedMember getMember() {
            return null;
        }

        public PropertyMetadata getMetadata() {
            return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        }

        public String getName() {
            return "";
        }

        public JavaType getType() {
            return TypeFactory.unknownType();
        }

        public PropertyName getWrapperName() {
            return null;
        }

        public boolean isRequired() {
            return false;
        }

        public boolean isVirtual() {
            return false;
        }
    }

    void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException;

    List<PropertyName> findAliases(MapperConfig<?> mapperConfig);

    @Deprecated
    JsonFormat.Value findFormatOverrides(AnnotationIntrospector annotationIntrospector);

    JsonFormat.Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls);

    JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls);

    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    PropertyName getFullName();

    AnnotatedMember getMember();

    PropertyMetadata getMetadata();

    String getName();

    JavaType getType();

    PropertyName getWrapperName();

    boolean isRequired();

    boolean isVirtual();

    public static class Std implements BeanProperty, Serializable {
        private static final long serialVersionUID = 1;
        protected final AnnotatedMember _member;
        protected final PropertyMetadata _metadata;
        protected final PropertyName _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, AnnotatedMember annotatedMember, PropertyMetadata propertyMetadata) {
            this._name = propertyName;
            this._type = javaType;
            this._wrapperName = propertyName2;
            this._metadata = propertyMetadata;
            this._member = annotatedMember;
        }

        public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) {
            throw new UnsupportedOperationException("Instances of " + getClass().getName() + " should not get visited");
        }

        public List<PropertyName> findAliases(MapperConfig<?> mapperConfig) {
            return Collections.emptyList();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
            r0 = r1.findFormat(r0);
         */
        @java.lang.Deprecated
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.fasterxml.jackson.annotation.JsonFormat.Value findFormatOverrides(com.fasterxml.jackson.databind.AnnotationIntrospector r1) {
            /*
                r0 = this;
                com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r0._member
                if (r0 == 0) goto L_0x000d
                if (r1 == 0) goto L_0x000d
                com.fasterxml.jackson.annotation.JsonFormat$Value r0 = r1.findFormat(r0)
                if (r0 == 0) goto L_0x000d
                return r0
            L_0x000d:
                com.fasterxml.jackson.annotation.JsonFormat$Value r0 = com.fasterxml.jackson.databind.BeanProperty.EMPTY_FORMAT
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.BeanProperty.Std.findFormatOverrides(com.fasterxml.jackson.databind.AnnotationIntrospector):com.fasterxml.jackson.annotation.JsonFormat$Value");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
            r0 = r1.findFormat((r0 = r0._member));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.fasterxml.jackson.annotation.JsonFormat.Value findPropertyFormat(com.fasterxml.jackson.databind.cfg.MapperConfig<?> r1, java.lang.Class<?> r2) {
            /*
                r0 = this;
                com.fasterxml.jackson.annotation.JsonFormat$Value r2 = r1.getDefaultPropertyFormat(r2)
                com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r1.getAnnotationIntrospector()
                if (r1 == 0) goto L_0x001b
                com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r0._member
                if (r0 != 0) goto L_0x000f
                goto L_0x001b
            L_0x000f:
                com.fasterxml.jackson.annotation.JsonFormat$Value r0 = r1.findFormat(r0)
                if (r0 != 0) goto L_0x0016
                return r2
            L_0x0016:
                com.fasterxml.jackson.annotation.JsonFormat$Value r0 = r2.withOverrides(r0)
                return r0
            L_0x001b:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.BeanProperty.Std.findPropertyFormat(com.fasterxml.jackson.databind.cfg.MapperConfig, java.lang.Class):com.fasterxml.jackson.annotation.JsonFormat$Value");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
            r1 = r2.findPropertyInclusion((r1 = r1._member));
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.fasterxml.jackson.annotation.JsonInclude.Value findPropertyInclusion(com.fasterxml.jackson.databind.cfg.MapperConfig<?> r2, java.lang.Class<?> r3) {
            /*
                r1 = this;
                com.fasterxml.jackson.databind.JavaType r0 = r1._type
                java.lang.Class r0 = r0.getRawClass()
                com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r2.getDefaultInclusion(r3, r0)
                com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2.getAnnotationIntrospector()
                if (r2 == 0) goto L_0x0021
                com.fasterxml.jackson.databind.introspect.AnnotatedMember r1 = r1._member
                if (r1 != 0) goto L_0x0015
                goto L_0x0021
            L_0x0015:
                com.fasterxml.jackson.annotation.JsonInclude$Value r1 = r2.findPropertyInclusion(r1)
                if (r1 != 0) goto L_0x001c
                return r3
            L_0x001c:
                com.fasterxml.jackson.annotation.JsonInclude$Value r1 = r3.withOverrides(r1)
                return r1
            L_0x0021:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.BeanProperty.Std.findPropertyInclusion(com.fasterxml.jackson.databind.cfg.MapperConfig, java.lang.Class):com.fasterxml.jackson.annotation.JsonInclude$Value");
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            AnnotatedMember annotatedMember = this._member;
            if (annotatedMember == null) {
                return null;
            }
            return annotatedMember.getAnnotation(cls);
        }

        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            return null;
        }

        public PropertyName getFullName() {
            return this._name;
        }

        public AnnotatedMember getMember() {
            return this._member;
        }

        public PropertyMetadata getMetadata() {
            return this._metadata;
        }

        public String getName() {
            return this._name.getSimpleName();
        }

        public JavaType getType() {
            return this._type;
        }

        public PropertyName getWrapperName() {
            return this._wrapperName;
        }

        public boolean isRequired() {
            return this._metadata.isRequired();
        }

        public boolean isVirtual() {
            return false;
        }

        public Std withType(JavaType javaType) {
            return new Std(this, javaType);
        }

        @Deprecated
        public Std(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, Annotations annotations, AnnotatedMember annotatedMember, PropertyMetadata propertyMetadata) {
            this(propertyName, javaType, propertyName2, annotatedMember, propertyMetadata);
        }

        public Std(Std std, JavaType javaType) {
            this(std._name, javaType, std._wrapperName, std._member, std._metadata);
        }
    }
}
