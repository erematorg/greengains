package com.fasterxml.jackson.dataformat.cbor;

import A.a;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.json.DupDetector;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class CBORReadContext extends JsonStreamContext {
    protected CBORReadContext _child = null;
    protected String _currentName;
    protected Object _currentValue;
    protected final DupDetector _dups;
    protected int _expEntryCount;
    protected final CBORReadContext _parent;

    public CBORReadContext(CBORReadContext cBORReadContext, DupDetector dupDetector, int i3, int i4) {
        this._parent = cBORReadContext;
        this._dups = dupDetector;
        this._type = i3;
        this._expEntryCount = i4;
        this._index = -1;
    }

    private void _checkDup(DupDetector dupDetector, String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            throw new JsonParseException((JsonParser) null, a.l("Duplicate field '", str, "'"), dupDetector.findLocation());
        }
    }

    public static CBORReadContext createRootContext(DupDetector dupDetector) {
        return new CBORReadContext((CBORReadContext) null, dupDetector, 0, -1);
    }

    public boolean acceptsBreakMarker() {
        return this._expEntryCount < 0 && this._type != 0;
    }

    public CBORReadContext createChildArrayContext(int i3) {
        CBORReadContext cBORReadContext = this._child;
        if (cBORReadContext == null) {
            DupDetector dupDetector = this._dups;
            cBORReadContext = new CBORReadContext(this, dupDetector == null ? null : dupDetector.child(), 1, i3);
            this._child = cBORReadContext;
        } else {
            cBORReadContext.reset(1, i3);
        }
        return cBORReadContext;
    }

    public CBORReadContext createChildObjectContext(int i3) {
        CBORReadContext cBORReadContext = this._child;
        if (cBORReadContext == null) {
            DupDetector dupDetector = this._dups;
            CBORReadContext cBORReadContext2 = new CBORReadContext(this, dupDetector == null ? null : dupDetector.child(), 2, i3);
            this._child = cBORReadContext2;
            return cBORReadContext2;
        }
        cBORReadContext.reset(2, i3);
        return cBORReadContext;
    }

    public boolean expectMoreValues() {
        int i3 = this._index + 1;
        this._index = i3;
        return i3 != this._expEntryCount;
    }

    public String getCurrentName() {
        return this._currentName;
    }

    public Object getCurrentValue() {
        return this._currentValue;
    }

    public int getExpectedLength() {
        return this._expEntryCount;
    }

    public int getRemainingExpectedLength() {
        return Math.max(0, this._expEntryCount - this._index);
    }

    @Deprecated
    public JsonLocation getStartLocation(Object obj) {
        return startLocation(ContentReference.rawReference(obj));
    }

    public boolean hasExpectedLength() {
        return this._expEntryCount >= 0;
    }

    public void reset(int i3, int i4) {
        this._type = i3;
        this._expEntryCount = i4;
        this._index = -1;
        this._currentName = null;
        this._currentValue = null;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            dupDetector.reset();
        }
    }

    public void setCurrentName(String str) throws JsonProcessingException {
        this._currentName = str;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
    }

    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public JsonLocation startLocation(ContentReference contentReference) {
        return new JsonLocation(contentReference, 1, -1, -1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        int i3 = this._type;
        if (i3 == 0) {
            sb.append("/");
        } else if (i3 == 1) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(getCurrentIndex());
            sb.append(AbstractJsonLexerKt.END_LIST);
        } else if (i3 == 2) {
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            if (this._currentName != null) {
                sb.append('\"');
                CharTypes.appendQuoted(sb, this._currentName);
                sb.append('\"');
            } else {
                sb.append('?');
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
        }
        return sb.toString();
    }

    public CBORReadContext getParent() {
        return this._parent;
    }
}
