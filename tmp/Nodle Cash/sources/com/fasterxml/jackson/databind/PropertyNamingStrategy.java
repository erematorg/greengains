package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public class PropertyNamingStrategy implements Serializable {
    @Deprecated
    public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
    @Deprecated
    public static final PropertyNamingStrategy KEBAB_CASE = new KebabCaseStrategy();
    @Deprecated
    public static final PropertyNamingStrategy LOWER_CAMEL_CASE = new PropertyNamingStrategy();
    @Deprecated
    public static final PropertyNamingStrategy LOWER_CASE = new LowerCaseStrategy();
    @Deprecated
    public static final PropertyNamingStrategy LOWER_DOT_CASE = new LowerDotCaseStrategy();
    @Deprecated
    public static final PropertyNamingStrategy PASCAL_CASE_TO_CAMEL_CASE;
    @Deprecated
    public static final PropertyNamingStrategy SNAKE_CASE;
    @Deprecated
    public static final PropertyNamingStrategy UPPER_CAMEL_CASE;
    private static final long serialVersionUID = 2;

    @Deprecated
    public static class KebabCaseStrategy extends PropertyNamingStrategyBase {
        public String translate(String str) {
            return PropertyNamingStrategyBase.translateLowerCaseWithSeparator(str, SignatureVisitor.SUPER);
        }
    }

    @Deprecated
    public static class LowerCaseStrategy extends PropertyNamingStrategyBase {
        public String translate(String str) {
            return str.toLowerCase();
        }
    }

    @Deprecated
    public static class LowerCaseWithUnderscoresStrategy extends SnakeCaseStrategy {
    }

    @Deprecated
    public static class LowerDotCaseStrategy extends PropertyNamingStrategyBase {
        public String translate(String str) {
            return PropertyNamingStrategyBase.translateLowerCaseWithSeparator(str, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        }
    }

    @Deprecated
    public static class PascalCaseStrategy extends UpperCamelCaseStrategy {
    }

    @Deprecated
    public static abstract class PropertyNamingStrategyBase extends PropertyNamingStrategy {
        public static String translateLowerCaseWithSeparator(String str, char c3) {
            int length;
            if (str == null || (length = str.length()) == 0) {
                return str;
            }
            StringBuilder sb = new StringBuilder((length >> 1) + length);
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                char charAt = str.charAt(i4);
                char lowerCase = Character.toLowerCase(charAt);
                if (lowerCase == charAt) {
                    if (i3 > 1) {
                        sb.insert(sb.length() - 1, c3);
                    }
                    i3 = 0;
                } else {
                    if (i3 == 0 && i4 > 0) {
                        sb.append(c3);
                    }
                    i3++;
                }
                sb.append(lowerCase);
            }
            return sb.toString();
        }

        public String nameForConstructorParameter(MapperConfig<?> mapperConfig, AnnotatedParameter annotatedParameter, String str) {
            return translate(str);
        }

        public String nameForField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, String str) {
            return translate(str);
        }

        public String nameForGetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        public String nameForSetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            return translate(str);
        }

        public abstract String translate(String str);
    }

    @Deprecated
    public static class SnakeCaseStrategy extends PropertyNamingStrategyBase {
        public String translate(String str) {
            if (str == null) {
                return str;
            }
            int length = str.length();
            StringBuilder sb = new StringBuilder(length * 2);
            int i3 = 0;
            boolean z2 = false;
            for (int i4 = 0; i4 < length; i4++) {
                char charAt = str.charAt(i4);
                if (i4 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z2 && i3 > 0 && sb.charAt(i3 - 1) != '_') {
                            sb.append('_');
                            i3++;
                        }
                        charAt = Character.toLowerCase(charAt);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    sb.append(charAt);
                    i3++;
                }
            }
            return i3 > 0 ? sb.toString() : str;
        }
    }

    @Deprecated
    public static class UpperCamelCaseStrategy extends PropertyNamingStrategyBase {
        public String translate(String str) {
            char charAt;
            char upperCase;
            if (str == null || str.isEmpty() || (charAt = str.charAt(0)) == (upperCase = Character.toUpperCase(charAt))) {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
            sb.setCharAt(0, upperCase);
            return sb.toString();
        }
    }

    static {
        UpperCamelCaseStrategy upperCamelCaseStrategy = new UpperCamelCaseStrategy();
        UPPER_CAMEL_CASE = upperCamelCaseStrategy;
        SnakeCaseStrategy snakeCaseStrategy = new SnakeCaseStrategy();
        SNAKE_CASE = snakeCaseStrategy;
        CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = snakeCaseStrategy;
        PASCAL_CASE_TO_CAMEL_CASE = upperCamelCaseStrategy;
    }

    public String nameForConstructorParameter(MapperConfig<?> mapperConfig, AnnotatedParameter annotatedParameter, String str) {
        return str;
    }

    public String nameForField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, String str) {
        return str;
    }

    public String nameForGetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        return str;
    }

    public String nameForSetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        return str;
    }
}
