package com.fasterxml.jackson.databind.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CompactStringObjectMap implements Serializable {
    private static final CompactStringObjectMap EMPTY = new CompactStringObjectMap(1, 0, new Object[4]);
    private static final long serialVersionUID = 1;
    private final Object[] _hashArea;
    private final int _hashMask;
    private final int _spillCount;

    private CompactStringObjectMap(int i3, int i4, Object[] objArr) {
        this._hashMask = i3;
        this._spillCount = i4;
        this._hashArea = objArr;
    }

    private final Object _find2(String str, int i3, Object obj) {
        if (obj == null) {
            return null;
        }
        int i4 = this._hashMask + 1;
        int i5 = ((i3 >> 1) + i4) << 1;
        Object obj2 = this._hashArea[i5];
        if (str.equals(obj2)) {
            return this._hashArea[i5 + 1];
        }
        if (obj2 != null) {
            int i6 = (i4 + (i4 >> 1)) << 1;
            int i7 = this._spillCount + i6;
            while (i6 < i7) {
                Object obj3 = this._hashArea[i6];
                if (obj3 == str || str.equals(obj3)) {
                    return this._hashArea[i6 + 1];
                }
                i6 += 2;
            }
        }
        return null;
    }

    public static <T> CompactStringObjectMap construct(Map<String, T> map) {
        if (map.isEmpty()) {
            return EMPTY;
        }
        int findSize = findSize(map.size());
        int i3 = findSize - 1;
        int i4 = (findSize >> 1) + findSize;
        Object[] objArr = new Object[(i4 * 2)];
        int i5 = 0;
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            if (str != null) {
                int hashCode = str.hashCode() & i3;
                int i6 = hashCode + hashCode;
                if (objArr[i6] != null) {
                    i6 = ((hashCode >> 1) + findSize) << 1;
                    if (objArr[i6] != null) {
                        i6 = (i4 << 1) + i5;
                        i5 += 2;
                        if (i6 >= objArr.length) {
                            objArr = Arrays.copyOf(objArr, objArr.length + 4);
                        }
                    }
                }
                objArr[i6] = str;
                objArr[i6 + 1] = next.getValue();
            }
        }
        return new CompactStringObjectMap(i3, i5, objArr);
    }

    private static final int findSize(int i3) {
        if (i3 <= 5) {
            return 8;
        }
        if (i3 <= 12) {
            return 16;
        }
        int i4 = 32;
        while (i4 < i3 + (i3 >> 2)) {
            i4 += i4;
        }
        return i4;
    }

    public Object find(String str) {
        int hashCode = str.hashCode() & this._hashMask;
        int i3 = hashCode << 1;
        Object obj = this._hashArea[i3];
        return (obj == str || str.equals(obj)) ? this._hashArea[i3 + 1] : _find2(str, hashCode, obj);
    }

    public Object findCaseInsensitive(String str) {
        int length = this._hashArea.length;
        for (int i3 = 0; i3 < length; i3 += 2) {
            Object obj = this._hashArea[i3];
            if (obj != null && ((String) obj).equalsIgnoreCase(str)) {
                return this._hashArea[i3 + 1];
            }
        }
        return null;
    }

    public List<String> keys() {
        int length = this._hashArea.length;
        ArrayList arrayList = new ArrayList(length >> 2);
        for (int i3 = 0; i3 < length; i3 += 2) {
            Object obj = this._hashArea[i3];
            if (obj != null) {
                arrayList.add((String) obj);
            }
        }
        return arrayList;
    }
}
