package com.fasterxml.jackson.core;

import A.a;
import androidx.collection.SieveCacheKt;
import androidx.constraintlayout.core.state.b;
import com.fasterxml.jackson.core.io.NumberInput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class JsonPointer implements Serializable {
    protected static final JsonPointer EMPTY = new JsonPointer();
    public static final char SEPARATOR = '/';
    private static final long serialVersionUID = 1;
    protected final String _asString;
    protected final int _asStringOffset;
    protected int _hashCode;
    protected volatile JsonPointer _head;
    protected final int _matchingElementIndex;
    protected final String _matchingPropertyName;
    protected final JsonPointer _nextSegment;

    public static class PointerParent {
        public final int fullPathOffset;
        public final PointerParent parent;
        public final String segment;

        public PointerParent(PointerParent pointerParent, int i3, String str) {
            this.parent = pointerParent;
            this.fullPathOffset = i3;
            this.segment = str;
        }
    }

    public static class PointerSegment {
        public final int index;
        public final PointerSegment next;
        public int pathOffset;
        public PointerSegment prev;
        public final String property;

        public PointerSegment(PointerSegment pointerSegment, String str, int i3) {
            this.next = pointerSegment;
            this.property = str;
            this.index = i3;
            if (pointerSegment != null) {
                pointerSegment.prev = this;
            }
        }
    }

    public static class Serialization implements Externalizable {
        private String _fullPath;

        public Serialization() {
        }

        private Object readResolve() throws ObjectStreamException {
            return JsonPointer.compile(this._fullPath);
        }

        public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
            this._fullPath = objectInput.readUTF();
        }

        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeUTF(this._fullPath);
        }

        public Serialization(String str) {
            this._fullPath = str;
        }
    }

    public JsonPointer() {
        this._nextSegment = null;
        this._matchingPropertyName = null;
        this._matchingElementIndex = -1;
        this._asString = "";
        this._asStringOffset = 0;
    }

    private static void _appendEscape(StringBuilder sb, char c3) {
        if (c3 == '0') {
            c3 = '~';
        } else if (c3 == '1') {
            c3 = SEPARATOR;
        } else {
            sb.append('~');
        }
        sb.append(c3);
    }

    private static void _appendEscaped(StringBuilder sb, String str) {
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt == '/') {
                sb.append("~1");
            } else if (charAt == '~') {
                sb.append("~0");
            } else {
                sb.append(charAt);
            }
        }
    }

    private static JsonPointer _buildPath(String str, int i3, String str2, PointerParent pointerParent) {
        JsonPointer jsonPointer = new JsonPointer(str, i3, str2, EMPTY);
        while (pointerParent != null) {
            JsonPointer jsonPointer2 = new JsonPointer(str, pointerParent.fullPathOffset, pointerParent.segment, jsonPointer);
            pointerParent = pointerParent.parent;
            jsonPointer = jsonPointer2;
        }
        return jsonPointer;
    }

    private final boolean _compare(String str, int i3, String str2, int i4) {
        int length = str.length();
        if (length - i3 != str2.length() - i4) {
            return false;
        }
        while (i3 < length) {
            int i5 = i3 + 1;
            int i6 = i4 + 1;
            if (str.charAt(i3) != str2.charAt(i4)) {
                return false;
            }
            i3 = i5;
            i4 = i6;
        }
        return true;
    }

    public static int _extractEscapedSegment(String str, int i3, int i4, StringBuilder sb) {
        int length = str.length();
        int i5 = i4 - 1;
        if (i5 - i3 > 0) {
            sb.append(str, i3, i5);
        }
        int i6 = i4 + 1;
        _appendEscape(sb, str.charAt(i4));
        while (i6 < length) {
            char charAt = str.charAt(i6);
            if (charAt == '/') {
                return i6;
            }
            int i7 = i6 + 1;
            if (charAt != '~' || i7 >= length) {
                sb.append(charAt);
                i6 = i7;
            } else {
                i6 += 2;
                _appendEscape(sb, str.charAt(i7));
            }
        }
        return -1;
    }

    private static final int _parseIndex(String str) {
        int length = str.length();
        if (length == 0 || length > 10) {
            return -1;
        }
        char charAt = str.charAt(0);
        if (charAt <= '0') {
            return (length == 1 && charAt == '0') ? 0 : -1;
        }
        if (charAt > '9') {
            return -1;
        }
        for (int i3 = 1; i3 < length; i3++) {
            char charAt2 = str.charAt(i3);
            if (charAt2 > '9' || charAt2 < '0') {
                return -1;
            }
        }
        if (length != 10 || NumberInput.parseLong(str) <= SieveCacheKt.NodeLinkMask) {
            return NumberInput.parseInt(str);
        }
        return -1;
    }

    public static JsonPointer _parseTail(String str) {
        int length = str.length();
        PointerParent pointerParent = null;
        int i3 = 1;
        int i4 = 0;
        while (i3 < length) {
            char charAt = str.charAt(i3);
            if (charAt == '/') {
                PointerParent pointerParent2 = new PointerParent(pointerParent, i4, str.substring(i4 + 1, i3));
                i4 = i3;
                i3++;
                pointerParent = pointerParent2;
            } else {
                i3++;
                if (charAt == '~' && i3 < length) {
                    StringBuilder sb = new StringBuilder(32);
                    int _extractEscapedSegment = _extractEscapedSegment(str, i4 + 1, i3, sb);
                    String sb2 = sb.toString();
                    if (_extractEscapedSegment < 0) {
                        return _buildPath(str, i4, sb2, pointerParent);
                    }
                    PointerParent pointerParent3 = new PointerParent(pointerParent, i4, sb2);
                    i4 = _extractEscapedSegment;
                    i3 = _extractEscapedSegment + 1;
                    pointerParent = pointerParent3;
                }
            }
        }
        return _buildPath(str, i4, str.substring(i4 + 1), pointerParent);
    }

    public static JsonPointer compile(String str) throws IllegalArgumentException {
        if (str == null || str.length() == 0) {
            return EMPTY;
        }
        if (str.charAt(0) == '/') {
            return _parseTail(str);
        }
        throw new IllegalArgumentException(a.l("Invalid input: JSON Pointer expression must start with '/': \"", str, "\""));
    }

    public static JsonPointer empty() {
        return EMPTY;
    }

    public static JsonPointer forPath(JsonStreamContext jsonStreamContext, boolean z2) {
        if (jsonStreamContext == null) {
            return EMPTY;
        }
        if (!jsonStreamContext.hasPathSegment() && (!z2 || !jsonStreamContext.inRoot() || !jsonStreamContext.hasCurrentIndex())) {
            jsonStreamContext = jsonStreamContext.getParent();
        }
        int i3 = 0;
        PointerSegment pointerSegment = null;
        while (jsonStreamContext != null) {
            if (jsonStreamContext.inObject()) {
                String currentName = jsonStreamContext.getCurrentName();
                if (currentName == null) {
                    currentName = "";
                }
                int length = currentName.length() + 2 + i3;
                pointerSegment = new PointerSegment(pointerSegment, currentName, -1);
                i3 = length;
            } else if (jsonStreamContext.inArray() || z2) {
                i3 += 6;
                pointerSegment = new PointerSegment(pointerSegment, (String) null, jsonStreamContext.getCurrentIndex());
            }
            jsonStreamContext = jsonStreamContext.getParent();
        }
        if (pointerSegment == null) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder(i3);
        PointerSegment pointerSegment2 = null;
        PointerSegment pointerSegment3 = pointerSegment;
        while (pointerSegment3 != null) {
            pointerSegment3.pathOffset = sb.length();
            sb.append(SEPARATOR);
            String str = pointerSegment3.property;
            if (str != null) {
                _appendEscaped(sb, str);
            } else {
                sb.append(pointerSegment3.index);
            }
            PointerSegment pointerSegment4 = pointerSegment3;
            pointerSegment3 = pointerSegment3.next;
            pointerSegment2 = pointerSegment4;
        }
        String sb2 = sb.toString();
        JsonPointer jsonPointer = EMPTY;
        while (pointerSegment2 != null) {
            String str2 = pointerSegment2.property;
            if (str2 != null) {
                jsonPointer = new JsonPointer(sb2, pointerSegment2.pathOffset, str2, jsonPointer);
            } else {
                int i4 = pointerSegment2.index;
                jsonPointer = new JsonPointer(sb2, pointerSegment2.pathOffset, String.valueOf(i4), i4, jsonPointer);
            }
            pointerSegment2 = pointerSegment2.prev;
        }
        return jsonPointer;
    }

    public static JsonPointer valueOf(String str) {
        return compile(str);
    }

    private Object writeReplace() {
        return new Serialization(toString());
    }

    public JsonPointer _constructHead() {
        JsonPointer last = last();
        if (last == this) {
            return EMPTY;
        }
        int length = last.length();
        return new JsonPointer(b.y(toString(), length, 0), 0, this._matchingPropertyName, this._matchingElementIndex, this._nextSegment._constructHead(length, last));
    }

    public JsonPointer append(JsonPointer jsonPointer) {
        JsonPointer jsonPointer2 = EMPTY;
        if (this == jsonPointer2) {
            return jsonPointer;
        }
        if (jsonPointer == jsonPointer2) {
            return this;
        }
        String str = this._asString;
        if (str.endsWith("/")) {
            str = b.y(str, 1, 0);
        }
        StringBuilder p2 = a.p(str);
        p2.append(jsonPointer._asString);
        return compile(p2.toString());
    }

    public JsonPointer appendIndex(int i3) {
        if (i3 >= 0) {
            String str = this._asString;
            if (str.endsWith("/")) {
                str = b.y(str, 1, 0);
            }
            return compile(str + SEPARATOR + i3);
        }
        throw new IllegalArgumentException("Negative index cannot be appended");
    }

    public JsonPointer appendProperty(String str) {
        if (str == null || str.isEmpty()) {
            return this;
        }
        if (str.charAt(0) != '/') {
            str = "/".concat(str);
        }
        String str2 = this._asString;
        if (str2.endsWith("/")) {
            str2 = b.y(str2, 1, 0);
        }
        return compile(str2 + str);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonPointer)) {
            return false;
        }
        JsonPointer jsonPointer = (JsonPointer) obj;
        return _compare(this._asString, this._asStringOffset, jsonPointer._asString, jsonPointer._asStringOffset);
    }

    public int getMatchingIndex() {
        return this._matchingElementIndex;
    }

    public String getMatchingProperty() {
        return this._matchingPropertyName;
    }

    public int hashCode() {
        int i3 = this._hashCode;
        if (i3 == 0) {
            i3 = toString().hashCode();
            if (i3 == 0) {
                i3 = -1;
            }
            this._hashCode = i3;
        }
        return i3;
    }

    public JsonPointer head() {
        JsonPointer jsonPointer = this._head;
        if (jsonPointer == null) {
            if (this != EMPTY) {
                jsonPointer = _constructHead();
            }
            this._head = jsonPointer;
        }
        return jsonPointer;
    }

    public JsonPointer last() {
        if (this == EMPTY) {
            return null;
        }
        while (true) {
            JsonPointer jsonPointer = this._nextSegment;
            if (jsonPointer == EMPTY) {
                return this;
            }
            this = jsonPointer;
        }
    }

    public int length() {
        return this._asString.length() - this._asStringOffset;
    }

    public JsonPointer matchElement(int i3) {
        if (i3 != this._matchingElementIndex || i3 < 0) {
            return null;
        }
        return this._nextSegment;
    }

    public JsonPointer matchProperty(String str) {
        if (this._nextSegment == null || !this._matchingPropertyName.equals(str)) {
            return null;
        }
        return this._nextSegment;
    }

    public boolean matches() {
        return this._nextSegment == null;
    }

    public boolean matchesElement(int i3) {
        return i3 == this._matchingElementIndex && i3 >= 0;
    }

    public boolean matchesProperty(String str) {
        return this._nextSegment != null && this._matchingPropertyName.equals(str);
    }

    public boolean mayMatchElement() {
        return this._matchingElementIndex >= 0;
    }

    public boolean mayMatchProperty() {
        return this._matchingPropertyName != null;
    }

    public JsonPointer tail() {
        return this._nextSegment;
    }

    public String toString() {
        int i3 = this._asStringOffset;
        return i3 <= 0 ? this._asString : this._asString.substring(i3);
    }

    public JsonPointer(String str, int i3, String str2, JsonPointer jsonPointer) {
        this._asString = str;
        this._asStringOffset = i3;
        this._nextSegment = jsonPointer;
        this._matchingPropertyName = str2;
        this._matchingElementIndex = _parseIndex(str2);
    }

    public JsonPointer _constructHead(int i3, JsonPointer jsonPointer) {
        if (this == jsonPointer) {
            return EMPTY;
        }
        return new JsonPointer(b.y(toString(), i3, 0), 0, this._matchingPropertyName, this._matchingElementIndex, this._nextSegment._constructHead(i3, jsonPointer));
    }

    public JsonPointer(String str, int i3, String str2, int i4, JsonPointer jsonPointer) {
        this._asString = str;
        this._asStringOffset = i3;
        this._nextSegment = jsonPointer;
        this._matchingPropertyName = str2;
        this._matchingElementIndex = i4;
    }
}
