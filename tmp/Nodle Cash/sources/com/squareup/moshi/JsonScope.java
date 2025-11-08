package com.squareup.moshi;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.ClassUtils;

final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;
    static final int STREAMING_VALUE = 9;

    private JsonScope() {
    }

    public static String getPath(int i3, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder sb = new StringBuilder("$");
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            if (i5 == 1 || i5 == 2) {
                sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                sb.append(iArr2[i4]);
                sb.append(AbstractJsonLexerKt.END_LIST);
            } else if (i5 == 3 || i5 == 4 || i5 == 5) {
                sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
                String str = strArr[i4];
                if (str != null) {
                    sb.append(str);
                }
            }
        }
        return sb.toString();
    }
}
