package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.CheckForNull;
import org.objectweb.asm.signature.SignatureVisitor;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public enum CaseFormat {
    LOWER_HYPHEN(CharMatcher.is(SignatureVisitor.SUPER), "-") {
        public String convert(CaseFormat caseFormat, String str) {
            return caseFormat == CaseFormat.LOWER_UNDERSCORE ? str.replace(SignatureVisitor.SUPER, '_') : caseFormat == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(str.replace(SignatureVisitor.SUPER, '_')) : CaseFormat.super.convert(caseFormat, str);
        }

        public String normalizeWord(String str) {
            return Ascii.toLowerCase(str);
        }
    },
    LOWER_UNDERSCORE(CharMatcher.is('_'), "_") {
        public String convert(CaseFormat caseFormat, String str) {
            return caseFormat == CaseFormat.LOWER_HYPHEN ? str.replace('_', SignatureVisitor.SUPER) : caseFormat == CaseFormat.UPPER_UNDERSCORE ? Ascii.toUpperCase(str) : CaseFormat.super.convert(caseFormat, str);
        }

        public String normalizeWord(String str) {
            return Ascii.toLowerCase(str);
        }
    },
    LOWER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        public String normalizeFirstWord(String str) {
            return Ascii.toLowerCase(str);
        }

        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_CAMEL(CharMatcher.inRange('A', 'Z'), "") {
        public String normalizeWord(String str) {
            return CaseFormat.firstCharOnlyToUpper(str);
        }
    },
    UPPER_UNDERSCORE(CharMatcher.is('_'), "_") {
        public String convert(CaseFormat caseFormat, String str) {
            return caseFormat == CaseFormat.LOWER_HYPHEN ? Ascii.toLowerCase(str.replace('_', SignatureVisitor.SUPER)) : caseFormat == CaseFormat.LOWER_UNDERSCORE ? Ascii.toLowerCase(str) : CaseFormat.super.convert(caseFormat, str);
        }

        public String normalizeWord(String str) {
            return Ascii.toUpperCase(str);
        }
    };
    
    private final CharMatcher wordBoundary;
    private final String wordSeparator;

    public static final class StringConverter extends Converter<String, String> implements Serializable {
        private static final long serialVersionUID = 0;
        private final CaseFormat sourceFormat;
        private final CaseFormat targetFormat;

        public StringConverter(CaseFormat caseFormat, CaseFormat caseFormat2) {
            this.sourceFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat);
            this.targetFormat = (CaseFormat) Preconditions.checkNotNull(caseFormat2);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof StringConverter)) {
                return false;
            }
            StringConverter stringConverter = (StringConverter) obj;
            return this.sourceFormat.equals(stringConverter.sourceFormat) && this.targetFormat.equals(stringConverter.targetFormat);
        }

        public int hashCode() {
            return this.targetFormat.hashCode() ^ this.sourceFormat.hashCode();
        }

        public String toString() {
            return this.sourceFormat + ".converterTo(" + this.targetFormat + ")";
        }

        public String doBackward(String str) {
            return this.targetFormat.to(this.sourceFormat, str);
        }

        public String doForward(String str) {
            return this.sourceFormat.to(this.targetFormat, str);
        }
    }

    /* access modifiers changed from: private */
    public static String firstCharOnlyToUpper(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return Ascii.toUpperCase(str.charAt(0)) + Ascii.toLowerCase(str.substring(1));
    }

    public String convert(CaseFormat caseFormat, String str) {
        StringBuilder sb = null;
        int i3 = 0;
        int i4 = -1;
        while (true) {
            i4 = this.wordBoundary.indexIn(str, i4 + 1);
            if (i4 == -1) {
                break;
            }
            if (i3 == 0) {
                sb = new StringBuilder((caseFormat.wordSeparator.length() * 4) + str.length());
                sb.append(caseFormat.normalizeFirstWord(str.substring(i3, i4)));
            } else {
                Objects.requireNonNull(sb);
                sb.append(caseFormat.normalizeWord(str.substring(i3, i4)));
            }
            sb.append(caseFormat.wordSeparator);
            i3 = this.wordSeparator.length() + i4;
        }
        if (i3 == 0) {
            return caseFormat.normalizeFirstWord(str);
        }
        Objects.requireNonNull(sb);
        sb.append(caseFormat.normalizeWord(str.substring(i3)));
        return sb.toString();
    }

    public Converter<String, String> converterTo(CaseFormat caseFormat) {
        return new StringConverter(this, caseFormat);
    }

    public String normalizeFirstWord(String str) {
        return normalizeWord(str);
    }

    public abstract String normalizeWord(String str);

    public final String to(CaseFormat caseFormat, String str) {
        Preconditions.checkNotNull(caseFormat);
        Preconditions.checkNotNull(str);
        return caseFormat == this ? str : convert(caseFormat, str);
    }

    private CaseFormat(CharMatcher charMatcher, String str) {
        this.wordBoundary = charMatcher;
        this.wordSeparator = str;
    }
}
