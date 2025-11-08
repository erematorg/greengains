package com.fasterxml.jackson.dataformat.cbor;

import A.a;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.json.DupDetector;

public final class CBORWriteContext extends JsonStreamContext {
    protected CBORWriteContext _childToRecycle;
    protected long _currentFieldId;
    protected String _currentName;
    protected Object _currentValue;
    protected DupDetector _dups;
    protected boolean _gotFieldId;
    protected final CBORWriteContext _parent;

    public CBORWriteContext(int i3, CBORWriteContext cBORWriteContext, DupDetector dupDetector, Object obj) {
        this._type = i3;
        this._parent = cBORWriteContext;
        this._dups = dupDetector;
        this._index = -1;
        this._currentValue = obj;
    }

    private final void _checkDup(DupDetector dupDetector, String str) throws JsonProcessingException {
        if (dupDetector.isDup(str)) {
            Object source = dupDetector.getSource();
            throw new JsonGenerationException(a.l("Duplicate field '", str, "'"), source instanceof JsonGenerator ? (JsonGenerator) source : null);
        }
    }

    public static CBORWriteContext createRootContext(DupDetector dupDetector) {
        return new CBORWriteContext(0, (CBORWriteContext) null, dupDetector, (Object) null);
    }

    private CBORWriteContext reset(int i3, Object obj) {
        this._type = i3;
        this._index = -1;
        this._gotFieldId = false;
        this._currentValue = obj;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            dupDetector.reset();
        }
        return this;
    }

    public CBORWriteContext clearAndGetParent() {
        this._currentValue = null;
        return this._parent;
    }

    public CBORWriteContext createChildArrayContext(Object obj) {
        CBORWriteContext cBORWriteContext = this._childToRecycle;
        if (cBORWriteContext != null) {
            return cBORWriteContext.reset(1, obj);
        }
        DupDetector dupDetector = this._dups;
        CBORWriteContext cBORWriteContext2 = new CBORWriteContext(1, this, dupDetector == null ? null : dupDetector.child(), obj);
        this._childToRecycle = cBORWriteContext2;
        return cBORWriteContext2;
    }

    public CBORWriteContext createChildObjectContext(Object obj) {
        CBORWriteContext cBORWriteContext = this._childToRecycle;
        if (cBORWriteContext != null) {
            return cBORWriteContext.reset(2, obj);
        }
        DupDetector dupDetector = this._dups;
        CBORWriteContext cBORWriteContext2 = new CBORWriteContext(2, this, dupDetector == null ? null : dupDetector.child(), obj);
        this._childToRecycle = cBORWriteContext2;
        return cBORWriteContext2;
    }

    public final String getCurrentName() {
        if (!this._gotFieldId) {
            return null;
        }
        String str = this._currentName;
        return str != null ? str : String.valueOf(this._currentFieldId);
    }

    public Object getCurrentValue() {
        return this._currentValue;
    }

    public DupDetector getDupDetector() {
        return this._dups;
    }

    public boolean hasCurrentName() {
        return this._gotFieldId;
    }

    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public CBORWriteContext withDupDetector(DupDetector dupDetector) {
        this._dups = dupDetector;
        return this;
    }

    public boolean writeFieldId(long j2) throws JsonProcessingException {
        if (this._type != 2 || this._gotFieldId) {
            return false;
        }
        this._gotFieldId = true;
        this._currentFieldId = j2;
        return true;
    }

    public boolean writeFieldName(String str) throws JsonProcessingException {
        if (this._type != 2 || this._gotFieldId) {
            return false;
        }
        this._gotFieldId = true;
        this._currentName = str;
        DupDetector dupDetector = this._dups;
        if (dupDetector != null) {
            _checkDup(dupDetector, str);
        }
        return true;
    }

    public boolean writeValue() {
        if (this._type == 2) {
            if (!this._gotFieldId) {
                return false;
            }
            this._gotFieldId = false;
        }
        this._index++;
        return true;
    }

    public final CBORWriteContext getParent() {
        return this._parent;
    }
}
