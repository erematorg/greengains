package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class CharEscaperBuilder {
    private final Map<Character, String> map = new HashMap();
    private int max = -1;

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscape(char c3, String str) {
        this.map.put(Character.valueOf(c3), (String) Preconditions.checkNotNull(str));
        if (c3 > this.max) {
            this.max = c3;
        }
        return this;
    }

    @CanIgnoreReturnValue
    public CharEscaperBuilder addEscapes(char[] cArr, String str) {
        Preconditions.checkNotNull(str);
        for (char addEscape : cArr) {
            addEscape(addEscape, str);
        }
        return this;
    }

    public char[][] toArray() {
        char[][] cArr = new char[(this.max + 1)][];
        for (Map.Entry next : this.map.entrySet()) {
            cArr[((Character) next.getKey()).charValue()] = ((String) next.getValue()).toCharArray();
        }
        return cArr;
    }

    public Escaper toEscaper() {
        return new CharArrayDecorator(toArray());
    }

    public static class CharArrayDecorator extends CharEscaper {
        private final int replaceLength;
        private final char[][] replacements;

        public CharArrayDecorator(char[][] cArr) {
            this.replacements = cArr;
            this.replaceLength = cArr.length;
        }

        public String escape(String str) {
            int length = str.length();
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                char[][] cArr = this.replacements;
                if (charAt < cArr.length && cArr[charAt] != null) {
                    return escapeSlow(str, i3);
                }
            }
            return str;
        }

        @CheckForNull
        public char[] escape(char c3) {
            if (c3 < this.replaceLength) {
                return this.replacements[c3];
            }
            return null;
        }
    }
}
