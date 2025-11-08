package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class TokenFilterContext extends JsonStreamContext {
    protected TokenFilterContext _child;
    protected String _currentName;
    protected TokenFilter _filter;
    protected boolean _needToHandleName = false;
    protected final TokenFilterContext _parent;
    protected boolean _startHandled;

    public TokenFilterContext(int i3, TokenFilterContext tokenFilterContext, TokenFilter tokenFilter, boolean z2) {
        this._type = i3;
        this._parent = tokenFilterContext;
        this._filter = tokenFilter;
        this._index = -1;
        this._startHandled = z2;
    }

    private void _writePath(JsonGenerator jsonGenerator) throws IOException {
        TokenFilter tokenFilter = this._filter;
        if (tokenFilter != null && tokenFilter != TokenFilter.INCLUDE_ALL) {
            TokenFilterContext tokenFilterContext = this._parent;
            if (tokenFilterContext != null) {
                tokenFilterContext._writePath(jsonGenerator);
            }
            if (!this._startHandled) {
                this._startHandled = true;
                int i3 = this._type;
                if (i3 == 2) {
                    jsonGenerator.writeStartObject();
                    if (this._needToHandleName) {
                        this._needToHandleName = false;
                        jsonGenerator.writeFieldName(this._currentName);
                    }
                } else if (i3 == 1) {
                    jsonGenerator.writeStartArray();
                }
            } else if (this._needToHandleName) {
                this._needToHandleName = false;
                jsonGenerator.writeFieldName(this._currentName);
            }
        }
    }

    public static TokenFilterContext createRootContext(TokenFilter tokenFilter) {
        return new TokenFilterContext(0, (TokenFilterContext) null, tokenFilter, true);
    }

    public void appendDesc(StringBuilder sb) {
        TokenFilterContext tokenFilterContext = this._parent;
        if (tokenFilterContext != null) {
            tokenFilterContext.appendDesc(sb);
        }
        int i3 = this._type;
        if (i3 == 2) {
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            if (this._currentName != null) {
                sb.append('\"');
                sb.append(this._currentName);
                sb.append('\"');
            } else {
                sb.append('?');
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
        } else if (i3 == 1) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(getCurrentIndex());
            sb.append(AbstractJsonLexerKt.END_LIST);
        } else {
            sb.append("/");
        }
    }

    public TokenFilter checkValue(TokenFilter tokenFilter) {
        int i3 = this._type;
        if (i3 == 2) {
            return tokenFilter;
        }
        int i4 = this._index + 1;
        this._index = i4;
        return i3 == 1 ? tokenFilter.includeElement(i4) : tokenFilter.includeRootValue(i4);
    }

    public TokenFilterContext closeArray(JsonGenerator jsonGenerator) throws IOException {
        if (this._startHandled) {
            jsonGenerator.writeEndArray();
        } else {
            TokenFilter tokenFilter = this._filter;
            if (!(tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL || !tokenFilter.includeEmptyArray(hasCurrentIndex()))) {
                TokenFilterContext tokenFilterContext = this._parent;
                if (tokenFilterContext != null) {
                    tokenFilterContext._writePath(jsonGenerator);
                }
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }
        TokenFilter tokenFilter2 = this._filter;
        if (!(tokenFilter2 == null || tokenFilter2 == TokenFilter.INCLUDE_ALL)) {
            tokenFilter2.filterFinishArray();
        }
        return this._parent;
    }

    public TokenFilterContext closeObject(JsonGenerator jsonGenerator) throws IOException {
        if (this._startHandled) {
            jsonGenerator.writeEndObject();
        } else {
            TokenFilter tokenFilter = this._filter;
            if (!(tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL || !tokenFilter.includeEmptyObject(hasCurrentName()))) {
                TokenFilterContext tokenFilterContext = this._parent;
                if (tokenFilterContext != null) {
                    tokenFilterContext._writePath(jsonGenerator);
                }
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
        TokenFilter tokenFilter2 = this._filter;
        if (!(tokenFilter2 == null || tokenFilter2 == TokenFilter.INCLUDE_ALL)) {
            tokenFilter2.filterFinishObject();
        }
        return this._parent;
    }

    public TokenFilterContext createChildArrayContext(TokenFilter tokenFilter, boolean z2) {
        TokenFilterContext tokenFilterContext = this._child;
        if (tokenFilterContext != null) {
            return tokenFilterContext.reset(1, tokenFilter, z2);
        }
        TokenFilterContext tokenFilterContext2 = new TokenFilterContext(1, this, tokenFilter, z2);
        this._child = tokenFilterContext2;
        return tokenFilterContext2;
    }

    public TokenFilterContext createChildObjectContext(TokenFilter tokenFilter, boolean z2) {
        TokenFilterContext tokenFilterContext = this._child;
        if (tokenFilterContext != null) {
            return tokenFilterContext.reset(2, tokenFilter, z2);
        }
        TokenFilterContext tokenFilterContext2 = new TokenFilterContext(2, this, tokenFilter, z2);
        this._child = tokenFilterContext2;
        return tokenFilterContext2;
    }

    public void ensureFieldNameWritten(JsonGenerator jsonGenerator) throws IOException {
        if (this._needToHandleName) {
            this._needToHandleName = false;
            jsonGenerator.writeFieldName(this._currentName);
        }
    }

    public TokenFilterContext findChildOf(TokenFilterContext tokenFilterContext) {
        TokenFilterContext tokenFilterContext2 = this._parent;
        if (tokenFilterContext2 == tokenFilterContext) {
            return this;
        }
        while (tokenFilterContext2 != null) {
            TokenFilterContext tokenFilterContext3 = tokenFilterContext2._parent;
            if (tokenFilterContext3 == tokenFilterContext) {
                return tokenFilterContext2;
            }
            tokenFilterContext2 = tokenFilterContext3;
        }
        return null;
    }

    public final String getCurrentName() {
        return this._currentName;
    }

    public Object getCurrentValue() {
        return null;
    }

    public TokenFilter getFilter() {
        return this._filter;
    }

    public boolean hasCurrentName() {
        return this._currentName != null;
    }

    public boolean isStartHandled() {
        return this._startHandled;
    }

    public JsonToken nextTokenToRead() {
        if (!this._startHandled) {
            this._startHandled = true;
            return this._type == 2 ? JsonToken.START_OBJECT : JsonToken.START_ARRAY;
        } else if (!this._needToHandleName || this._type != 2) {
            return null;
        } else {
            this._needToHandleName = false;
            return JsonToken.FIELD_NAME;
        }
    }

    public TokenFilterContext reset(int i3, TokenFilter tokenFilter, boolean z2) {
        this._type = i3;
        this._filter = tokenFilter;
        this._index = -1;
        this._currentName = null;
        this._startHandled = z2;
        this._needToHandleName = false;
        return this;
    }

    public void setCurrentValue(Object obj) {
    }

    public TokenFilter setFieldName(String str) throws JsonProcessingException {
        this._currentName = str;
        this._needToHandleName = true;
        return this._filter;
    }

    public void skipParentChecks() {
        this._filter = null;
        for (TokenFilterContext tokenFilterContext = this._parent; tokenFilterContext != null; tokenFilterContext = tokenFilterContext._parent) {
            this._parent._filter = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        appendDesc(sb);
        return sb.toString();
    }

    public void writePath(JsonGenerator jsonGenerator) throws IOException {
        TokenFilter tokenFilter = this._filter;
        if (tokenFilter != null && tokenFilter != TokenFilter.INCLUDE_ALL) {
            TokenFilterContext tokenFilterContext = this._parent;
            if (tokenFilterContext != null) {
                tokenFilterContext._writePath(jsonGenerator);
            }
            if (!this._startHandled) {
                this._startHandled = true;
                int i3 = this._type;
                if (i3 == 2) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeFieldName(this._currentName);
                } else if (i3 == 1) {
                    jsonGenerator.writeStartArray();
                }
            } else if (this._needToHandleName) {
                jsonGenerator.writeFieldName(this._currentName);
            }
        }
    }

    public final TokenFilterContext getParent() {
        return this._parent;
    }
}
