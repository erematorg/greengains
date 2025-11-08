package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AccessorNamingStrategy;
import com.fasterxml.jackson.databind.jdk14.JDK14Util;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DefaultAccessorNamingStrategy extends AccessorNamingStrategy {
    protected final BaseNameValidator _baseNameValidator;
    protected final MapperConfig<?> _config;
    protected final AnnotatedClass _forClass;
    protected final String _getterPrefix;
    protected final String _isGetterPrefix;
    protected final boolean _isGettersNonBoolean;
    protected final String _mutatorPrefix;
    protected final boolean _stdBeanNaming;

    public interface BaseNameValidator {
        boolean accept(char c3, String str, int i3);
    }

    public static class FirstCharBasedValidator implements BaseNameValidator {
        private final boolean _allowLowerCaseFirstChar;
        private final boolean _allowNonLetterFirstChar;

        public FirstCharBasedValidator(boolean z2, boolean z3) {
            this._allowLowerCaseFirstChar = z2;
            this._allowNonLetterFirstChar = z3;
        }

        public static BaseNameValidator forFirstNameRule(boolean z2, boolean z3) {
            if (z2 || z3) {
                return new FirstCharBasedValidator(z2, z3);
            }
            return null;
        }

        public boolean accept(char c3, String str, int i3) {
            return Character.isLetter(c3) ? this._allowLowerCaseFirstChar || !Character.isLowerCase(c3) : this._allowNonLetterFirstChar;
        }
    }

    public static class Provider extends AccessorNamingStrategy.Provider implements Serializable {
        private static final long serialVersionUID = 1;
        protected final BaseNameValidator _baseNameValidator;
        protected final String _getterPrefix;
        protected final String _isGetterPrefix;
        protected final String _setterPrefix;
        protected final String _withPrefix;

        public Provider() {
            this("set", JsonPOJOBuilder.DEFAULT_WITH_PREFIX, "get", "is", (BaseNameValidator) null);
        }

        public AccessorNamingStrategy forBuilder(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, BeanDescription beanDescription) {
            JsonPOJOBuilder.Value value = null;
            AnnotationIntrospector annotationIntrospector = mapperConfig.isAnnotationProcessingEnabled() ? mapperConfig.getAnnotationIntrospector() : null;
            if (annotationIntrospector != null) {
                value = annotationIntrospector.findPOJOBuilderConfig(annotatedClass);
            }
            return new DefaultAccessorNamingStrategy(mapperConfig, annotatedClass, value == null ? this._withPrefix : value.withPrefix, this._getterPrefix, this._isGetterPrefix, this._baseNameValidator);
        }

        public AccessorNamingStrategy forPOJO(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass) {
            return new DefaultAccessorNamingStrategy(mapperConfig, annotatedClass, this._setterPrefix, this._getterPrefix, this._isGetterPrefix, this._baseNameValidator);
        }

        public AccessorNamingStrategy forRecord(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass) {
            return new RecordNaming(mapperConfig, annotatedClass);
        }

        public Provider withBaseNameValidator(BaseNameValidator baseNameValidator) {
            return new Provider(this, baseNameValidator);
        }

        public Provider withBuilderPrefix(String str) {
            return new Provider(this, this._setterPrefix, str, this._getterPrefix, this._isGetterPrefix);
        }

        public Provider withFirstCharAcceptance(boolean z2, boolean z3) {
            return withBaseNameValidator(FirstCharBasedValidator.forFirstNameRule(z2, z3));
        }

        public Provider withGetterPrefix(String str) {
            return new Provider(this, this._setterPrefix, this._withPrefix, str, this._isGetterPrefix);
        }

        public Provider withIsGetterPrefix(String str) {
            return new Provider(this, this._setterPrefix, this._withPrefix, this._getterPrefix, str);
        }

        public Provider withSetterPrefix(String str) {
            return new Provider(this, str, this._withPrefix, this._getterPrefix, this._isGetterPrefix);
        }

        public Provider(Provider provider, String str, String str2, String str3, String str4) {
            this(str, str2, str3, str4, provider._baseNameValidator);
        }

        public Provider(Provider provider, BaseNameValidator baseNameValidator) {
            this(provider._setterPrefix, provider._withPrefix, provider._getterPrefix, provider._isGetterPrefix, baseNameValidator);
        }

        public Provider(String str, String str2, String str3, String str4, BaseNameValidator baseNameValidator) {
            this._setterPrefix = str;
            this._withPrefix = str2;
            this._getterPrefix = str3;
            this._isGetterPrefix = str4;
            this._baseNameValidator = baseNameValidator;
        }
    }

    public static class RecordNaming extends DefaultAccessorNamingStrategy {
        protected final Set<String> _fieldNames;

        public RecordNaming(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass) {
            super(mapperConfig, annotatedClass, (String) null, "get", "is", (BaseNameValidator) null);
            String[] recordFieldNames = JDK14Util.getRecordFieldNames(annotatedClass.getRawType());
            this._fieldNames = recordFieldNames == null ? Collections.emptySet() : new HashSet<>(Arrays.asList(recordFieldNames));
        }

        public String findNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
            return this._fieldNames.contains(str) ? str : DefaultAccessorNamingStrategy.super.findNameForRegularGetter(annotatedMethod, str);
        }
    }

    public DefaultAccessorNamingStrategy(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, String str, String str2, String str3, BaseNameValidator baseNameValidator) {
        this._config = mapperConfig;
        this._forClass = annotatedClass;
        this._stdBeanNaming = mapperConfig.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
        this._isGettersNonBoolean = mapperConfig.isEnabled(MapperFeature.ALLOW_IS_GETTERS_FOR_NON_BOOLEAN);
        this._mutatorPrefix = str;
        this._getterPrefix = str2;
        this._isGetterPrefix = str3;
        this._baseNameValidator = baseNameValidator;
    }

    public boolean _isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
        Class<?> rawType = annotatedMethod.getRawType();
        if (!rawType.isArray()) {
            return false;
        }
        String name = rawType.getComponentType().getName();
        if (name.contains(".cglib")) {
            return name.startsWith("net.sf.cglib") || name.startsWith("org.hibernate.repackage.cglib") || name.startsWith("org.springframework.cglib");
        }
        return false;
    }

    public boolean _isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.getRawType().getName().startsWith("groovy.lang");
    }

    public String findNameForIsGetter(AnnotatedMethod annotatedMethod, String str) {
        if (this._isGetterPrefix == null) {
            return null;
        }
        Class<?> rawType = annotatedMethod.getRawType();
        if ((this._isGettersNonBoolean || rawType == Boolean.class || rawType == Boolean.TYPE) && str.startsWith(this._isGetterPrefix)) {
            return this._stdBeanNaming ? stdManglePropertyName(str, 2) : legacyManglePropertyName(str, 2);
        }
        return null;
    }

    public String findNameForMutator(AnnotatedMethod annotatedMethod, String str) {
        String str2 = this._mutatorPrefix;
        if (str2 == null || !str.startsWith(str2)) {
            return null;
        }
        return this._stdBeanNaming ? stdManglePropertyName(str, this._mutatorPrefix.length()) : legacyManglePropertyName(str, this._mutatorPrefix.length());
    }

    public String findNameForRegularGetter(AnnotatedMethod annotatedMethod, String str) {
        String str2 = this._getterPrefix;
        if (str2 == null || !str.startsWith(str2)) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (_isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && _isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return this._stdBeanNaming ? stdManglePropertyName(str, this._getterPrefix.length()) : legacyManglePropertyName(str, this._getterPrefix.length());
    }

    public String legacyManglePropertyName(String str, int i3) {
        int length = str.length();
        if (length == i3) {
            return null;
        }
        char charAt = str.charAt(i3);
        BaseNameValidator baseNameValidator = this._baseNameValidator;
        if (baseNameValidator != null && !baseNameValidator.accept(charAt, str, i3)) {
            return null;
        }
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(i3);
        }
        StringBuilder sb = new StringBuilder(length - i3);
        sb.append(lowerCase);
        while (true) {
            i3++;
            if (i3 >= length) {
                break;
            }
            char charAt2 = str.charAt(i3);
            char lowerCase2 = Character.toLowerCase(charAt2);
            if (charAt2 == lowerCase2) {
                sb.append(str, i3, length);
                break;
            }
            sb.append(lowerCase2);
        }
        return sb.toString();
    }

    public String modifyFieldName(AnnotatedField annotatedField, String str) {
        return str;
    }

    public String stdManglePropertyName(String str, int i3) {
        int length = str.length();
        if (length == i3) {
            return null;
        }
        char charAt = str.charAt(i3);
        BaseNameValidator baseNameValidator = this._baseNameValidator;
        if (baseNameValidator != null && !baseNameValidator.accept(charAt, str, i3)) {
            return null;
        }
        char lowerCase = Character.toLowerCase(charAt);
        if (charAt == lowerCase) {
            return str.substring(i3);
        }
        int i4 = i3 + 1;
        if (i4 < length && Character.isUpperCase(str.charAt(i4))) {
            return str.substring(i3);
        }
        StringBuilder sb = new StringBuilder(length - i3);
        sb.append(lowerCase);
        sb.append(str, i4, length);
        return sb.toString();
    }
}
