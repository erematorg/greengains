package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;
import org.apache.commons.lang3.ClassUtils;
import org.objectweb.asm.signature.SignatureVisitor;

public abstract class PropertyNamingStrategies implements Serializable {
    public static final PropertyNamingStrategy KEBAB_CASE = KebabCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy LOWER_CAMEL_CASE = LowerCamelCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy LOWER_CASE = LowerCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy LOWER_DOT_CASE = LowerDotCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy SNAKE_CASE = SnakeCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy UPPER_CAMEL_CASE = UpperCamelCaseStrategy.INSTANCE;
    public static final PropertyNamingStrategy UPPER_SNAKE_CASE = UpperSnakeCaseStrategy.INSTANCE;
    private static final long serialVersionUID = 2;

    public static class KebabCaseStrategy extends NamingBase {
        public static final KebabCaseStrategy INSTANCE = new KebabCaseStrategy();
        private static final long serialVersionUID = 2;

        public String translate(String str) {
            return translateLowerCaseWithSeparator(str, SignatureVisitor.SUPER);
        }
    }

    public static class LowerCamelCaseStrategy extends NamingBase {
        public static final LowerCamelCaseStrategy INSTANCE = new LowerCamelCaseStrategy();
        private static final long serialVersionUID = 2;

        public String translate(String str) {
            return str;
        }
    }

    public static class LowerCaseStrategy extends NamingBase {
        public static final LowerCaseStrategy INSTANCE = new LowerCaseStrategy();
        private static final long serialVersionUID = 2;

        public String translate(String str) {
            return str.toLowerCase();
        }
    }

    public static class LowerDotCaseStrategy extends NamingBase {
        public static final LowerDotCaseStrategy INSTANCE = new LowerDotCaseStrategy();
        private static final long serialVersionUID = 2;

        public String translate(String str) {
            return translateLowerCaseWithSeparator(str, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        }
    }

    public static abstract class NamingBase extends PropertyNamingStrategy {
        private static final long serialVersionUID = 2;

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

        public String translateLowerCaseWithSeparator(String str, char c3) {
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
    }

    public static class SnakeCaseStrategy extends NamingBase {
        public static final SnakeCaseStrategy INSTANCE = new SnakeCaseStrategy();
        private static final long serialVersionUID = 2;

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

    public static class UpperCamelCaseStrategy extends NamingBase {
        public static final UpperCamelCaseStrategy INSTANCE = new UpperCamelCaseStrategy();
        private static final long serialVersionUID = 2;

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

    public static class UpperSnakeCaseStrategy extends SnakeCaseStrategy {
        public static final UpperSnakeCaseStrategy INSTANCE = new UpperSnakeCaseStrategy();
        private static final long serialVersionUID = 2;

        public String translate(String str) {
            if (super.translate(str) == null) {
                return null;
            }
            return super.translate(str).toUpperCase();
        }
    }
}
