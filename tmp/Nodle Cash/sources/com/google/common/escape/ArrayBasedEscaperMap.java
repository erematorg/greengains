package com.google.common.escape;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;

@GwtCompatible
@ElementTypesAreNonnullByDefault
public final class ArrayBasedEscaperMap {
    private static final char[][] EMPTY_REPLACEMENT_ARRAY;
    private final char[][] replacementArray;

    static {
        int[] iArr = new int[2];
        iArr[1] = 0;
        iArr[0] = 0;
        EMPTY_REPLACEMENT_ARRAY = (char[][]) Array.newInstance(Character.TYPE, iArr);
    }

    private ArrayBasedEscaperMap(char[][] cArr) {
        this.replacementArray = cArr;
    }

    public static ArrayBasedEscaperMap create(Map<Character, String> map) {
        return new ArrayBasedEscaperMap(createReplacementArray(map));
    }

    @VisibleForTesting
    public static char[][] createReplacementArray(Map<Character, String> map) {
        Preconditions.checkNotNull(map);
        if (map.isEmpty()) {
            return EMPTY_REPLACEMENT_ARRAY;
        }
        char[][] cArr = new char[(((Character) Collections.max(map.keySet())).charValue() + 1)][];
        for (Character next : map.keySet()) {
            cArr[next.charValue()] = map.get(next).toCharArray();
        }
        return cArr;
    }

    public char[][] getReplacementArray() {
        return this.replacementArray;
    }
}
