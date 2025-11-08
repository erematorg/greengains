package com.fasterxml.jackson.databind.ser;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class PropertyBuilder {
    private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonInclude.Value _defaultInclusion;
    protected final boolean _useRealPropertyDefaults;

    /* renamed from: com.fasterxml.jackson.databind.ser.PropertyBuilder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonInclude$Include[] r0 = com.fasterxml.jackson.annotation.JsonInclude.Include.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = r0
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.CUSTOM     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        JsonInclude.Value merge = JsonInclude.Value.merge(beanDescription.findPropertyInclusion(JsonInclude.Value.empty()), serializationConfig.getDefaultPropertyInclusion(beanDescription.getBeanClass(), JsonInclude.Value.empty()));
        this._defaultInclusion = JsonInclude.Value.merge(serializationConfig.getDefaultPropertyInclusion(), merge);
        this._useRealPropertyDefaults = merge.getValueInclusion() == JsonInclude.Include.NON_DEFAULT;
        this._annotationIntrospector = serializationConfig.getAnnotationIntrospector();
    }

    public BeanPropertyWriter _constructPropertyWriter(BeanPropertyDefinition beanPropertyDefinition, AnnotatedMember annotatedMember, Annotations annotations, JavaType javaType, JsonSerializer<?> jsonSerializer, TypeSerializer typeSerializer, JavaType javaType2, boolean z2, Object obj, Class<?>[] clsArr) throws JsonMappingException {
        return new BeanPropertyWriter(beanPropertyDefinition, annotatedMember, annotations, javaType, jsonSerializer, typeSerializer, javaType2, z2, obj, clsArr);
    }

    public Object _throwWrapped(Exception exc, String str, Object obj) {
        while (true) {
            Throwable cause = r2.getCause();
            Throwable th = exc;
            if (cause != null) {
                th = th.getCause();
            } else {
                ClassUtil.throwIfError(th);
                ClassUtil.throwIfRTE(th);
                StringBuilder w2 = a.w("Failed to get property '", str, "' of default ");
                w2.append(obj.getClass().getName());
                w2.append(" instance");
                throw new IllegalArgumentException(w2.toString());
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0134  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.ser.BeanPropertyWriter buildWriter(com.fasterxml.jackson.databind.SerializerProvider r16, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r17, com.fasterxml.jackson.databind.JavaType r18, com.fasterxml.jackson.databind.JsonSerializer<?> r19, com.fasterxml.jackson.databind.jsontype.TypeSerializer r20, com.fasterxml.jackson.databind.jsontype.TypeSerializer r21, com.fasterxml.jackson.databind.introspect.AnnotatedMember r22, boolean r23) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r15 = this;
            r12 = r15
            r13 = r16
            r2 = r17
            r5 = r18
            r0 = r21
            r14 = r22
            r1 = 0
            r3 = r23
            com.fasterxml.jackson.databind.JavaType r3 = r15.findSerializationType(r14, r3, r5)     // Catch:{ JsonMappingException -> 0x0148 }
            if (r0 == 0) goto L_0x0040
            if (r3 != 0) goto L_0x0017
            r3 = r5
        L_0x0017:
            com.fasterxml.jackson.databind.JavaType r4 = r3.getContentType()
            if (r4 != 0) goto L_0x0037
            com.fasterxml.jackson.databind.BeanDescription r4 = r12._beanDesc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "serialization type "
            r6.<init>(r7)
            r6.append(r3)
            java.lang.String r7 = " has no content"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r7 = new java.lang.Object[r1]
            r13.reportBadPropertyDefinition(r4, r2, r6, r7)
        L_0x0037:
            com.fasterxml.jackson.databind.JavaType r0 = r3.withContentTypeHandler(r0)
            r0.getContentType()
            r8 = r0
            goto L_0x0041
        L_0x0040:
            r8 = r3
        L_0x0041:
            if (r8 != 0) goto L_0x0045
            r0 = r5
            goto L_0x0046
        L_0x0045:
            r0 = r8
        L_0x0046:
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r3 = r17.getAccessor()
            if (r3 != 0) goto L_0x0059
            com.fasterxml.jackson.databind.BeanDescription r0 = r12._beanDesc
            java.lang.String r3 = "could not determine property type"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Object r0 = r13.reportBadPropertyDefinition(r0, r2, r3, r1)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r0 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter) r0
            return r0
        L_0x0059:
            java.lang.Class r3 = r3.getRawType()
            com.fasterxml.jackson.databind.SerializationConfig r4 = r12._config
            java.lang.Class r6 = r0.getRawClass()
            com.fasterxml.jackson.annotation.JsonInclude$Value r7 = r12._defaultInclusion
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r4.getDefaultInclusion(r6, r3, r7)
            com.fasterxml.jackson.annotation.JsonInclude$Value r4 = r17.findInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r3.withOverrides(r4)
            com.fasterxml.jackson.annotation.JsonInclude$Include r4 = r3.getValueInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Include r6 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS
            if (r4 != r6) goto L_0x007b
            com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS
        L_0x007b:
            int[] r6 = com.fasterxml.jackson.databind.ser.PropertyBuilder.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include
            int r4 = r4.ordinal()
            r4 = r6[r4]
            r6 = 1
            r7 = 0
            if (r4 == r6) goto L_0x00c7
            r9 = 2
            if (r4 == r9) goto L_0x00bc
            r9 = 3
            if (r4 == r9) goto L_0x00b7
            r9 = 4
            if (r4 == r9) goto L_0x00ae
            r3 = 5
            if (r4 == r3) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r1 = r6
        L_0x0095:
            com.fasterxml.jackson.databind.SerializationFeature r3 = com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS
            boolean r0 = r0.isContainerType()
            if (r0 == 0) goto L_0x00ab
            com.fasterxml.jackson.databind.SerializationConfig r0 = r12._config
            boolean r0 = r0.isEnabled((com.fasterxml.jackson.databind.SerializationFeature) r3)
            if (r0 != 0) goto L_0x00ab
            java.lang.Object r0 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
        L_0x00a7:
            r10 = r0
            r9 = r1
            goto L_0x010a
        L_0x00ab:
            r9 = r1
        L_0x00ac:
            r10 = r7
            goto L_0x010a
        L_0x00ae:
            java.lang.Class r0 = r3.getValueFilter()
            java.lang.Object r0 = r13.includeFilterInstance(r2, r0)
            goto L_0x00a7
        L_0x00b7:
            java.lang.Object r0 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
        L_0x00b9:
            r10 = r0
            r9 = r6
            goto L_0x010a
        L_0x00bc:
            boolean r0 = r0.isReferenceType()
            if (r0 == 0) goto L_0x00c5
            java.lang.Object r0 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            goto L_0x00b9
        L_0x00c5:
            r9 = r6
            goto L_0x00ac
        L_0x00c7:
            boolean r3 = r12._useRealPropertyDefaults
            if (r3 == 0) goto L_0x00f3
            java.lang.Object r3 = r15.getDefaultBean()
            if (r3 == 0) goto L_0x00f3
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS
            boolean r0 = r13.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            if (r0 == 0) goto L_0x00e4
            com.fasterxml.jackson.databind.SerializationConfig r0 = r12._config
            com.fasterxml.jackson.databind.MapperFeature r4 = com.fasterxml.jackson.databind.MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS
            boolean r0 = r0.isEnabled(r4)
            r14.fixAccess(r0)
        L_0x00e4:
            java.lang.Object r7 = r14.getValue(r3)     // Catch:{ Exception -> 0x00e9 }
            goto L_0x00f8
        L_0x00e9:
            r0 = move-exception
            r4 = r0
            java.lang.String r0 = r17.getName()
            r15._throwWrapped(r4, r0, r3)
            goto L_0x00f8
        L_0x00f3:
            java.lang.Object r7 = com.fasterxml.jackson.databind.util.BeanUtil.getDefaultValue(r0)
            r1 = r6
        L_0x00f8:
            if (r7 != 0) goto L_0x00fb
            goto L_0x00c5
        L_0x00fb:
            java.lang.Class r0 = r7.getClass()
            boolean r0 = r0.isArray()
            if (r0 == 0) goto L_0x00ab
            java.lang.Object r0 = com.fasterxml.jackson.databind.util.ArrayBuilders.getArrayComparator(r7)
            goto L_0x00a7
        L_0x010a:
            java.lang.Class[] r0 = r17.findViews()
            if (r0 != 0) goto L_0x0116
            com.fasterxml.jackson.databind.BeanDescription r0 = r12._beanDesc
            java.lang.Class[] r0 = r0.findDefaultViews()
        L_0x0116:
            r11 = r0
            com.fasterxml.jackson.databind.BeanDescription r0 = r12._beanDesc
            com.fasterxml.jackson.databind.util.Annotations r4 = r0.getClassAnnotations()
            r1 = r15
            r2 = r17
            r3 = r22
            r5 = r18
            r6 = r19
            r7 = r20
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r0 = r1._constructPropertyWriter(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r12._annotationIntrospector
            java.lang.Object r1 = r1.findNullSerializer(r14)
            if (r1 == 0) goto L_0x013b
            com.fasterxml.jackson.databind.JsonSerializer r1 = r13.serializerInstance(r14, r1)
            r0.assignNullSerializer(r1)
        L_0x013b:
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r12._annotationIntrospector
            com.fasterxml.jackson.databind.util.NameTransformer r1 = r1.findUnwrappingNameTransformer(r14)
            if (r1 == 0) goto L_0x0147
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r0 = r0.unwrappingWriter(r1)
        L_0x0147:
            return r0
        L_0x0148:
            r0 = move-exception
            r3 = r0
            if (r2 != 0) goto L_0x0157
            java.lang.String r0 = com.fasterxml.jackson.databind.util.ClassUtil.exceptionMessage(r3)
            java.lang.Object r0 = r13.reportBadDefinition(r5, r0)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r0 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter) r0
            return r0
        L_0x0157:
            com.fasterxml.jackson.databind.BeanDescription r0 = r12._beanDesc
            java.lang.String r3 = com.fasterxml.jackson.databind.util.ClassUtil.exceptionMessage(r3)
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.Object r0 = r13.reportBadPropertyDefinition(r0, r2, r3, r1)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r0 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.introspect.AnnotatedMember, boolean):com.fasterxml.jackson.databind.ser.BeanPropertyWriter");
    }

    public JavaType findSerializationType(Annotated annotated, boolean z2, JavaType javaType) throws JsonMappingException {
        JavaType refineSerializationType = this._annotationIntrospector.refineSerializationType(this._config, annotated, javaType);
        boolean z3 = true;
        if (refineSerializationType != javaType) {
            Class<?> rawClass = refineSerializationType.getRawClass();
            Class<?> rawClass2 = javaType.getRawClass();
            if (!rawClass.isAssignableFrom(rawClass2) && !rawClass2.isAssignableFrom(rawClass)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + rawClass.getName() + " not a super-type of (declared) class " + rawClass2.getName());
            }
            javaType = refineSerializationType;
            z2 = true;
        }
        JsonSerialize.Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
        if (!(findSerializationTyping == null || findSerializationTyping == JsonSerialize.Typing.DEFAULT_TYPING)) {
            if (findSerializationTyping != JsonSerialize.Typing.STATIC) {
                z3 = false;
            }
            z2 = z3;
        }
        if (z2) {
            return javaType.withStaticTyping();
        }
        return null;
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    public Object getDefaultBean() {
        Object obj = this._defaultBean;
        if (obj == null) {
            obj = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (obj == null) {
                obj = NO_DEFAULT_MARKER;
            }
            this._defaultBean = obj;
        }
        if (obj == NO_DEFAULT_MARKER) {
            return null;
        }
        return this._defaultBean;
    }

    @Deprecated
    public Object getDefaultValue(JavaType javaType) {
        return BeanUtil.getDefaultValue(javaType);
    }

    @Deprecated
    public Object getPropertyDefaultValue(String str, AnnotatedMember annotatedMember, JavaType javaType) {
        Object defaultBean = getDefaultBean();
        if (defaultBean == null) {
            return getDefaultValue(javaType);
        }
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e3) {
            return _throwWrapped(e3, str, defaultBean);
        }
    }
}
