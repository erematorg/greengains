package com.fasterxml.jackson.core.json;

import A.a;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.ContentReference;

public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child;
    protected int _columnNr;
    protected String _currentName;
    protected Object _currentValue;
    protected DupDetector _dups;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, DupDetector dupDetector, int i3, int i4, int i5) {
        this._parent = jsonReadContext;
        this._dups = dupDetector;
        this._type = i3;
        this._lineNr = i4;
        this._columnNr = i5;
        this._index = -1;
    }

    private void _checkDup(DupDetector dupDetector, String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            throw new JsonParseException(source instanceof JsonParser ? (JsonParser) source : null, a.l("Duplicate field '", str, "'"));
        }
    }

    public static JsonReadContext createRootContext(int i3, int i4, DupDetector dupDetector) {
        return new JsonReadContext((JsonReadContext) null, dupDetector, 0, i3, i4);
    }

    public JsonReadContext clearAndGetParent() {
        this._currentValue = null;
        return this._parent;
    }

    public JsonReadContext createChildArrayContext(int i3, int i4) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            jsonReadContext = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 1, i3, i4);
            this._child = jsonReadContext;
        } else {
            jsonReadContext.reset(1, i3, i4);
        }
        return jsonReadContext;
    }

    public JsonReadContext createChildObjectContext(int i3, int i4) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            DupDetector dupDetector = this._dups;
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, dupDetector == null ? null : dupDetector.child(), 2, i3, i4);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, i3, i4);
        return jsonReadContext;
    }

    public boolean expectComma() {
        int i3 = this._index + 1;
        this._index = i3;
        return this._type != 0 && i3 > 0;
    }

    public String getCurrentName() {
        return this._currentName;
    }

    public Object getCurrentValue() {
        return this._currentValue;
    }

    public DupDetector getDupDetector() {
        return this._dups;
    }

    @Deprecated
    public JsonLocation getStartLocation(Object obj) {
        return startLocation(ContentReference.rawReference(obj));
    }

    public boolean hasCurrentName() {
        return this._currentName != null;
    }

    public void reset(int i3, int i4, int i5) {
        this._type = i3;
        this._index = -1;
        this._lineNr = i4;
        this._columnNr = i5;
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
        return new JsonLocation(contentReference, -1, this._lineNr, this._columnNr);
    }

    public JsonReadContext withDupDetector(DupDetector dupDetector) {
        this._dups = dupDetector;
        return this;
    }

    public static JsonReadContext createRootContext(DupDetector dupDetector) {
        return new JsonReadContext((JsonReadContext) null, dupDetector, 0, 1, 0);
    }

    public JsonReadContext getParent() {
        return this._parent;
    }
}
