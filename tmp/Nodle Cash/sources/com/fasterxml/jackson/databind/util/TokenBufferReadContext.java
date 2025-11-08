package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.ContentReference;
import com.fasterxml.jackson.core.json.JsonReadContext;

public class TokenBufferReadContext extends JsonStreamContext {
    protected String _currentName;
    protected Object _currentValue;
    protected final JsonStreamContext _parent;
    protected final JsonLocation _startLocation;

    public TokenBufferReadContext(JsonStreamContext jsonStreamContext, ContentReference contentReference) {
        super(jsonStreamContext);
        this._parent = jsonStreamContext.getParent();
        this._currentName = jsonStreamContext.getCurrentName();
        this._currentValue = jsonStreamContext.getCurrentValue();
        if (jsonStreamContext instanceof JsonReadContext) {
            this._startLocation = ((JsonReadContext) jsonStreamContext).startLocation(contentReference);
        } else {
            this._startLocation = JsonLocation.NA;
        }
    }

    public static TokenBufferReadContext createRootContext(JsonStreamContext jsonStreamContext) {
        return jsonStreamContext == null ? new TokenBufferReadContext() : new TokenBufferReadContext(jsonStreamContext, ContentReference.unknown());
    }

    public TokenBufferReadContext createChildArrayContext() {
        this._index++;
        return new TokenBufferReadContext(this, 1, -1);
    }

    public TokenBufferReadContext createChildObjectContext() {
        this._index++;
        return new TokenBufferReadContext(this, 2, -1);
    }

    public String getCurrentName() {
        return this._currentName;
    }

    public Object getCurrentValue() {
        return this._currentValue;
    }

    public JsonStreamContext getParent() {
        return this._parent;
    }

    public boolean hasCurrentName() {
        return this._currentName != null;
    }

    public TokenBufferReadContext parentOrCopy() {
        JsonStreamContext jsonStreamContext = this._parent;
        return jsonStreamContext instanceof TokenBufferReadContext ? (TokenBufferReadContext) jsonStreamContext : jsonStreamContext == null ? new TokenBufferReadContext() : new TokenBufferReadContext(jsonStreamContext, this._startLocation);
    }

    public void setCurrentName(String str) throws JsonProcessingException {
        this._currentName = str;
    }

    public void setCurrentValue(Object obj) {
        this._currentValue = obj;
    }

    public void updateForValue() {
        this._index++;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TokenBufferReadContext(com.fasterxml.jackson.core.JsonStreamContext r2, java.lang.Object r3) {
        /*
            r1 = this;
            boolean r0 = r3 instanceof com.fasterxml.jackson.core.io.ContentReference
            if (r0 == 0) goto L_0x0007
            com.fasterxml.jackson.core.io.ContentReference r3 = (com.fasterxml.jackson.core.io.ContentReference) r3
            goto L_0x000b
        L_0x0007:
            com.fasterxml.jackson.core.io.ContentReference r3 = com.fasterxml.jackson.core.io.ContentReference.rawReference(r3)
        L_0x000b:
            r1.<init>((com.fasterxml.jackson.core.JsonStreamContext) r2, (com.fasterxml.jackson.core.io.ContentReference) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.TokenBufferReadContext.<init>(com.fasterxml.jackson.core.JsonStreamContext, java.lang.Object):void");
    }

    public TokenBufferReadContext(JsonStreamContext jsonStreamContext, JsonLocation jsonLocation) {
        super(jsonStreamContext);
        this._parent = jsonStreamContext.getParent();
        this._currentName = jsonStreamContext.getCurrentName();
        this._currentValue = jsonStreamContext.getCurrentValue();
        this._startLocation = jsonLocation;
    }

    public TokenBufferReadContext() {
        super(0, -1);
        this._parent = null;
        this._startLocation = JsonLocation.NA;
    }

    public TokenBufferReadContext(TokenBufferReadContext tokenBufferReadContext, int i3, int i4) {
        super(i3, i4);
        this._parent = tokenBufferReadContext;
        this._startLocation = tokenBufferReadContext._startLocation;
    }
}
