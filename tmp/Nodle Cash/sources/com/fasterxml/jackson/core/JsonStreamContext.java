package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.ContentReference;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.slf4j.Logger;

public abstract class JsonStreamContext {
    public static final int TYPE_ARRAY = 1;
    public static final int TYPE_OBJECT = 2;
    public static final int TYPE_ROOT = 0;
    protected int _index;
    protected int _type;

    public JsonStreamContext() {
    }

    public final int getCurrentIndex() {
        int i3 = this._index;
        if (i3 < 0) {
            return 0;
        }
        return i3;
    }

    public abstract String getCurrentName();

    public Object getCurrentValue() {
        return null;
    }

    public final int getEntryCount() {
        return this._index + 1;
    }

    public abstract JsonStreamContext getParent();

    @Deprecated
    public JsonLocation getStartLocation(Object obj) {
        return JsonLocation.NA;
    }

    @Deprecated
    public final String getTypeDesc() {
        int i3 = this._type;
        return i3 != 0 ? i3 != 1 ? i3 != 2 ? "?" : "OBJECT" : "ARRAY" : Logger.ROOT_LOGGER_NAME;
    }

    public boolean hasCurrentIndex() {
        return this._index >= 0;
    }

    public boolean hasCurrentName() {
        return getCurrentName() != null;
    }

    public boolean hasPathSegment() {
        int i3 = this._type;
        if (i3 == 2) {
            return hasCurrentName();
        }
        if (i3 == 1) {
            return hasCurrentIndex();
        }
        return false;
    }

    public final boolean inArray() {
        return this._type == 1;
    }

    public final boolean inObject() {
        return this._type == 2;
    }

    public final boolean inRoot() {
        return this._type == 0;
    }

    public JsonPointer pathAsPointer() {
        return JsonPointer.forPath(this, false);
    }

    public void setCurrentValue(Object obj) {
    }

    public JsonLocation startLocation(ContentReference contentReference) {
        return JsonLocation.NA;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i3 = this._type;
        if (i3 == 0) {
            sb.append("/");
        } else if (i3 != 1) {
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            String currentName = getCurrentName();
            if (currentName != null) {
                sb.append('\"');
                CharTypes.appendQuoted(sb, currentName);
                sb.append('\"');
            } else {
                sb.append('?');
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
        } else {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(getCurrentIndex());
            sb.append(AbstractJsonLexerKt.END_LIST);
        }
        return sb.toString();
    }

    public String typeDesc() {
        int i3 = this._type;
        return i3 != 0 ? i3 != 1 ? i3 != 2 ? "?" : "Object" : "Array" : "root";
    }

    public JsonStreamContext(JsonStreamContext jsonStreamContext) {
        this._type = jsonStreamContext._type;
        this._index = jsonStreamContext._index;
    }

    public JsonPointer pathAsPointer(boolean z2) {
        return JsonPointer.forPath(this, z2);
    }

    public JsonStreamContext(int i3, int i4) {
        this._type = i3;
        this._index = i4;
    }
}
