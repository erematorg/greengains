package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BeanUtil {
    public static String checkUnsupportedType(JavaType javaType) {
        String str;
        String str2;
        String name = javaType.getRawClass().getName();
        if (isJava8TimeClass(name)) {
            if (name.indexOf(46, 10) >= 0) {
                return null;
            }
            str2 = "Java 8 date/time";
            str = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310";
        } else if (!isJodaTimeClass(name)) {
            return null;
        } else {
            str2 = "Joda date/time";
            str = "com.fasterxml.jackson.datatype:jackson-datatype-joda";
        }
        String typeDescription = ClassUtil.getTypeDescription(javaType);
        return str2 + " type " + typeDescription + " not supported by default: add Module \"" + str + "\" to enable handling";
    }

    public static Object getDefaultValue(JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        Class<?> primitiveType = ClassUtil.primitiveType(rawClass);
        if (primitiveType != null) {
            return ClassUtil.defaultValue(primitiveType);
        }
        if (javaType.isContainerType() || javaType.isReferenceType()) {
            return JsonInclude.Include.NON_EMPTY;
        }
        if (rawClass == String.class) {
            return "";
        }
        if (javaType.isTypeOrSubTypeOf(Date.class)) {
            return new Date(0);
        }
        if (!javaType.isTypeOrSubTypeOf(Calendar.class)) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(0);
        return gregorianCalendar;
    }

    public static boolean isCglibGetCallbacks(AnnotatedMethod annotatedMethod) {
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

    public static boolean isGroovyMetaClassGetter(AnnotatedMethod annotatedMethod) {
        return annotatedMethod.getRawType().getName().startsWith("groovy.lang");
    }

    public static boolean isJava8TimeClass(Class<?> cls) {
        return isJava8TimeClass(cls.getName());
    }

    public static boolean isJodaTimeClass(Class<?> cls) {
        return isJodaTimeClass(cls.getName());
    }

    public static String legacyManglePropertyName(String str, int i3) {
        int length = str.length();
        if (length == i3) {
            return null;
        }
        char charAt = str.charAt(i3);
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

    @Deprecated
    public static String okNameForGetter(AnnotatedMethod annotatedMethod, boolean z2) {
        String name = annotatedMethod.getName();
        String okNameForIsGetter = okNameForIsGetter(annotatedMethod, name, z2);
        return okNameForIsGetter == null ? okNameForRegularGetter(annotatedMethod, name, z2) : okNameForIsGetter;
    }

    @Deprecated
    public static String okNameForIsGetter(AnnotatedMethod annotatedMethod, String str, boolean z2) {
        if (!str.startsWith("is")) {
            return null;
        }
        Class<?> rawType = annotatedMethod.getRawType();
        if (rawType == Boolean.class || rawType == Boolean.TYPE) {
            return z2 ? stdManglePropertyName(str, 2) : legacyManglePropertyName(str, 2);
        }
        return null;
    }

    @Deprecated
    public static String okNameForMutator(AnnotatedMethod annotatedMethod, String str, boolean z2) {
        String name = annotatedMethod.getName();
        if (name.startsWith(str)) {
            return z2 ? stdManglePropertyName(name, str.length()) : legacyManglePropertyName(name, str.length());
        }
        return null;
    }

    @Deprecated
    public static String okNameForRegularGetter(AnnotatedMethod annotatedMethod, String str, boolean z2) {
        if (!str.startsWith("get")) {
            return null;
        }
        if ("getCallbacks".equals(str)) {
            if (isCglibGetCallbacks(annotatedMethod)) {
                return null;
            }
        } else if ("getMetaClass".equals(str) && isGroovyMetaClassGetter(annotatedMethod)) {
            return null;
        }
        return z2 ? stdManglePropertyName(str, 3) : legacyManglePropertyName(str, 3);
    }

    @Deprecated
    public static String okNameForSetter(AnnotatedMethod annotatedMethod, boolean z2) {
        return okNameForMutator(annotatedMethod, "set", z2);
    }

    public static String stdManglePropertyName(String str, int i3) {
        int length = str.length();
        if (length == i3) {
            return null;
        }
        char charAt = str.charAt(i3);
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

    private static boolean isJava8TimeClass(String str) {
        return str.startsWith("java.time.");
    }

    private static boolean isJodaTimeClass(String str) {
        return str.startsWith("org.joda.time.");
    }
}
