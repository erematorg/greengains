package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    protected TokenFilter.Inclusion _inclusion;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z2, boolean z3) {
        this(jsonParser, tokenFilter, z2 ? TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH : TokenFilter.Inclusion.ONLY_INCLUDE_ALL, z3);
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) throws IOException {
        this._exposedContext = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this._headContext) {
            tokenFilterContext = this._exposedContext.findChildOf(tokenFilterContext);
            this._exposedContext = tokenFilterContext;
            if (tokenFilterContext != null) {
                JsonToken nextTokenToRead2 = tokenFilterContext.nextTokenToRead();
                if (nextTokenToRead2 != null) {
                    return nextTokenToRead2;
                }
            } else {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    private final boolean _verifyAllowedMatches() throws IOException {
        int i3 = this._matchCount;
        if (i3 != 0 && !this._allowMultipleMatches) {
            return false;
        }
        this._matchCount = i3 + 1;
        return true;
    }

    public JsonStreamContext _filterContext() {
        TokenFilterContext tokenFilterContext = this._exposedContext;
        return tokenFilterContext != null ? tokenFilterContext : this._headContext;
    }

    public final JsonToken _nextToken2() throws IOException {
        JsonToken _nextTokenWithBuffering;
        JsonToken _nextTokenWithBuffering2;
        JsonToken _nextTokenWithBuffering3;
        TokenFilter checkValue;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                this._currToken = nextToken;
                return nextToken;
            }
            int id = nextToken.id();
            if (id == 1) {
                TokenFilter tokenFilter = this._itemFilter;
                TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter == tokenFilter2) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter, true);
                    this._currToken = nextToken;
                    return nextToken;
                } else if (tokenFilter == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue2 != tokenFilter2) {
                            checkValue2 = checkValue2.filterStartObject();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == tokenFilter2) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (checkValue2 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            TokenFilterContext createChildObjectContext = this._headContext.createChildObjectContext(checkValue2, false);
                            this._headContext = createChildObjectContext;
                            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering = _nextTokenWithBuffering(createChildObjectContext)) != null) {
                                this._currToken = _nextTokenWithBuffering;
                                return _nextTokenWithBuffering;
                            }
                        } else {
                            this._headContext = this._headContext.createChildObjectContext(checkValue2, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                    }
                }
            } else if (id == 2) {
                boolean isStartHandled = this._headContext.isStartHandled();
                TokenFilter filter = this._headContext.getFilter();
                if (!(filter == null || filter == TokenFilter.INCLUDE_ALL)) {
                    boolean includeEmptyArray = filter.includeEmptyArray(this._headContext.hasCurrentName());
                    filter.filterFinishObject();
                    if (includeEmptyArray) {
                        return _nextBuffered(this._headContext);
                    }
                }
                TokenFilterContext parent = this._headContext.getParent();
                this._headContext = parent;
                this._itemFilter = parent.getFilter();
                if (isStartHandled) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            } else if (id == 3) {
                TokenFilter tokenFilter3 = this._itemFilter;
                TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter3 == tokenFilter4) {
                    this._headContext = this._headContext.createChildArrayContext(tokenFilter3, true);
                    this._currToken = nextToken;
                    return nextToken;
                } else if (tokenFilter3 == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter3);
                    if (checkValue3 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue3 != tokenFilter4) {
                            checkValue3 = checkValue3.filterStartArray();
                        }
                        this._itemFilter = checkValue3;
                        if (checkValue3 == tokenFilter4) {
                            this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (checkValue3 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            TokenFilterContext createChildArrayContext = this._headContext.createChildArrayContext(checkValue3, false);
                            this._headContext = createChildArrayContext;
                            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering2 = _nextTokenWithBuffering(createChildArrayContext)) != null) {
                                this._currToken = _nextTokenWithBuffering2;
                                return _nextTokenWithBuffering2;
                            }
                        } else {
                            this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                    }
                }
            } else if (id == 4) {
                boolean isStartHandled2 = this._headContext.isStartHandled();
                TokenFilter filter2 = this._headContext.getFilter();
                if (!(filter2 == null || filter2 == TokenFilter.INCLUDE_ALL)) {
                    boolean includeEmptyArray2 = filter2.includeEmptyArray(this._headContext.hasCurrentIndex());
                    filter2.filterFinishArray();
                    if (includeEmptyArray2) {
                        return _nextBuffered(this._headContext);
                    }
                }
                TokenFilterContext parent2 = this._headContext.getParent();
                this._headContext = parent2;
                this._itemFilter = parent2.getFilter();
                if (isStartHandled2) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            } else if (id != 5) {
                TokenFilter tokenFilter5 = this._itemFilter;
                TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter5 == tokenFilter6) {
                    this._currToken = nextToken;
                    return nextToken;
                } else if (tokenFilter5 != null && (((checkValue = this._headContext.checkValue(tokenFilter5)) == tokenFilter6 || (checkValue != null && checkValue.includeValue(this.delegate))) && _verifyAllowedMatches())) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            } else {
                String currentName = this.delegate.getCurrentName();
                TokenFilter fieldName = this._headContext.setFieldName(currentName);
                TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                if (fieldName == tokenFilter7) {
                    this._itemFilter = fieldName;
                    this._currToken = nextToken;
                    return nextToken;
                } else if (fieldName == null) {
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                } else {
                    TokenFilter includeProperty = fieldName.includeProperty(currentName);
                    if (includeProperty == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                    } else {
                        this._itemFilter = includeProperty;
                        if (includeProperty == tokenFilter7) {
                            if (!_verifyAllowedMatches()) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
                                this._currToken = nextToken;
                                return nextToken;
                            }
                        } else if (!(this._inclusion == TokenFilter.Inclusion.ONLY_INCLUDE_ALL || (_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) == null)) {
                            this._currToken = _nextTokenWithBuffering3;
                            return _nextTokenWithBuffering3;
                        }
                    }
                }
            }
        }
    }

    public final JsonToken _nextTokenWithBuffering(TokenFilterContext tokenFilterContext) throws IOException {
        TokenFilter checkValue;
        while (true) {
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                return nextToken;
            }
            int id = nextToken.id();
            boolean z2 = false;
            if (id != 1) {
                String str = null;
                if (id == 2) {
                    TokenFilter filter = this._headContext.getFilter();
                    if (!(filter == null || filter == TokenFilter.INCLUDE_ALL)) {
                        boolean includeEmptyObject = filter.includeEmptyObject(this._headContext.hasCurrentName());
                        filter.filterFinishObject();
                        if (includeEmptyObject) {
                            TokenFilterContext tokenFilterContext2 = this._headContext;
                            TokenFilterContext tokenFilterContext3 = tokenFilterContext2._parent;
                            if (tokenFilterContext3 != null) {
                                str = tokenFilterContext3._currentName;
                            }
                            tokenFilterContext2._currentName = str;
                            tokenFilterContext2._needToHandleName = false;
                            return _nextBuffered(tokenFilterContext);
                        }
                    }
                    TokenFilterContext tokenFilterContext4 = this._headContext;
                    boolean z3 = tokenFilterContext4 == tokenFilterContext;
                    if (z3 && tokenFilterContext4.isStartHandled()) {
                        z2 = true;
                    }
                    TokenFilterContext parent = this._headContext.getParent();
                    this._headContext = parent;
                    this._itemFilter = parent.getFilter();
                    if (z2) {
                        return nextToken;
                    }
                    if (z3) {
                        return null;
                    }
                } else if (id == 3) {
                    TokenFilter checkValue2 = this._headContext.checkValue(this._itemFilter);
                    if (checkValue2 == null) {
                        this.delegate.skipChildren();
                    } else {
                        TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
                        if (checkValue2 != tokenFilter) {
                            checkValue2 = checkValue2.filterStartArray();
                        }
                        this._itemFilter = checkValue2;
                        if (checkValue2 == tokenFilter) {
                            this._headContext = this._headContext.createChildArrayContext(checkValue2, true);
                            return _nextBuffered(tokenFilterContext);
                        } else if (checkValue2 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            this._headContext = this._headContext.createChildArrayContext(checkValue2, false);
                        } else {
                            this._headContext = this._headContext.createChildArrayContext(checkValue2, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                    }
                } else if (id == 4) {
                    TokenFilter filter2 = this._headContext.getFilter();
                    if (!(filter2 == null || filter2 == TokenFilter.INCLUDE_ALL)) {
                        boolean includeEmptyArray = filter2.includeEmptyArray(this._headContext.hasCurrentIndex());
                        filter2.filterFinishArray();
                        if (includeEmptyArray) {
                            return _nextBuffered(tokenFilterContext);
                        }
                    }
                    TokenFilterContext tokenFilterContext5 = this._headContext;
                    boolean z4 = tokenFilterContext5 == tokenFilterContext;
                    if (z4 && tokenFilterContext5.isStartHandled()) {
                        z2 = true;
                    }
                    TokenFilterContext parent2 = this._headContext.getParent();
                    this._headContext = parent2;
                    this._itemFilter = parent2.getFilter();
                    if (z2) {
                        return nextToken;
                    }
                    if (z4) {
                        return null;
                    }
                } else if (id != 5) {
                    TokenFilter tokenFilter2 = this._itemFilter;
                    TokenFilter tokenFilter3 = TokenFilter.INCLUDE_ALL;
                    if (tokenFilter2 == tokenFilter3) {
                        return _nextBuffered(tokenFilterContext);
                    }
                    if (tokenFilter2 != null && (((checkValue = this._headContext.checkValue(tokenFilter2)) == tokenFilter3 || (checkValue != null && checkValue.includeValue(this.delegate))) && _verifyAllowedMatches())) {
                        return _nextBuffered(tokenFilterContext);
                    }
                } else {
                    String currentName = this.delegate.getCurrentName();
                    TokenFilter fieldName = this._headContext.setFieldName(currentName);
                    TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                    if (fieldName == tokenFilter4) {
                        this._itemFilter = fieldName;
                        return _nextBuffered(tokenFilterContext);
                    } else if (fieldName == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                    } else {
                        TokenFilter includeProperty = fieldName.includeProperty(currentName);
                        if (includeProperty == null) {
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                        } else {
                            this._itemFilter = includeProperty;
                            if (includeProperty != tokenFilter4) {
                                continue;
                            } else if (_verifyAllowedMatches()) {
                                return _nextBuffered(tokenFilterContext);
                            } else {
                                this._itemFilter = this._headContext.setFieldName(currentName);
                            }
                        }
                    }
                }
            } else {
                TokenFilter tokenFilter5 = this._itemFilter;
                TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter5 == tokenFilter6) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter5, true);
                    return nextToken;
                } else if (tokenFilter5 == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter5);
                    if (checkValue3 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue3 != tokenFilter6) {
                            checkValue3 = checkValue3.filterStartObject();
                        }
                        this._itemFilter = checkValue3;
                        if (checkValue3 == tokenFilter6) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue3, true);
                            return _nextBuffered(tokenFilterContext);
                        } else if (checkValue3 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue3, false);
                        } else {
                            this._headContext = this._headContext.createChildArrayContext(checkValue3, true);
                            return _nextBuffered(tokenFilterContext);
                        }
                    }
                }
            }
        }
    }

    public void clearCurrentToken() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != null) {
            this._lastClearedToken = jsonToken;
            this._currToken = null;
        }
    }

    public String currentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return _filterContext.getCurrentName();
        }
        JsonStreamContext parent = _filterContext.getParent();
        if (parent == null) {
            return null;
        }
        return parent.getCurrentName();
    }

    public JsonToken currentToken() {
        return this._currToken;
    }

    public final int currentTokenId() {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        return this.delegate.getBinaryValue(base64Variant);
    }

    public boolean getBooleanValue() throws IOException {
        return this.delegate.getBooleanValue();
    }

    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public String getCurrentName() throws IOException {
        JsonStreamContext _filterContext = _filterContext();
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return _filterContext.getCurrentName();
        }
        JsonStreamContext parent = _filterContext.getParent();
        if (parent == null) {
            return null;
        }
        return parent.getCurrentName();
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    @Deprecated
    public final int getCurrentTokenId() {
        return currentTokenId();
    }

    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    public JsonParser.NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    public String getText() throws IOException {
        return this._currToken == JsonToken.FIELD_NAME ? currentName() : this.delegate.getText();
    }

    public char[] getTextCharacters() throws IOException {
        return this._currToken == JsonToken.FIELD_NAME ? currentName().toCharArray() : this.delegate.getTextCharacters();
    }

    public int getTextLength() throws IOException {
        return this._currToken == JsonToken.FIELD_NAME ? currentName().length() : this.delegate.getTextLength();
    }

    public int getTextOffset() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return 0;
        }
        return this.delegate.getTextOffset();
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    public boolean getValueAsBoolean() throws IOException {
        return this.delegate.getValueAsBoolean();
    }

    public double getValueAsDouble() throws IOException {
        return this.delegate.getValueAsDouble();
    }

    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName();
        }
        return this.delegate.getValueAsString();
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return false;
        }
        return this.delegate.hasTextCharacters();
    }

    public final boolean hasToken(JsonToken jsonToken) {
        return this._currToken == jsonToken;
    }

    public boolean hasTokenId(int i3) {
        JsonToken jsonToken = this._currToken;
        return jsonToken == null ? i3 == 0 : jsonToken.id() == i3;
    }

    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    public JsonToken nextToken() throws IOException {
        JsonToken _nextTokenWithBuffering;
        JsonToken _nextTokenWithBuffering2;
        JsonToken _nextTokenWithBuffering3;
        TokenFilter checkValue;
        JsonToken jsonToken;
        if (this._allowMultipleMatches || (jsonToken = this._currToken) == null || this._exposedContext != null || !jsonToken.isScalarValue() || this._headContext.isStartHandled() || this._inclusion != TokenFilter.Inclusion.ONLY_INCLUDE_ALL || this._itemFilter != TokenFilter.INCLUDE_ALL) {
            TokenFilterContext tokenFilterContext = this._exposedContext;
            if (tokenFilterContext != null) {
                do {
                    JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
                    if (nextTokenToRead != null) {
                        this._currToken = nextTokenToRead;
                        return nextTokenToRead;
                    }
                    TokenFilterContext tokenFilterContext2 = this._headContext;
                    if (tokenFilterContext == tokenFilterContext2) {
                        this._exposedContext = null;
                        if (tokenFilterContext.inArray()) {
                            JsonToken currentToken = this.delegate.getCurrentToken();
                            this._currToken = currentToken;
                            if (currentToken == JsonToken.END_ARRAY) {
                                TokenFilterContext parent = this._headContext.getParent();
                                this._headContext = parent;
                                this._itemFilter = parent.getFilter();
                            }
                            return currentToken;
                        }
                        JsonToken currentToken2 = this.delegate.currentToken();
                        if (currentToken2 == JsonToken.END_OBJECT) {
                            TokenFilterContext parent2 = this._headContext.getParent();
                            this._headContext = parent2;
                            this._itemFilter = parent2.getFilter();
                        }
                        if (currentToken2 != JsonToken.FIELD_NAME) {
                            this._currToken = currentToken2;
                            return currentToken2;
                        }
                    } else {
                        tokenFilterContext = tokenFilterContext2.findChildOf(tokenFilterContext);
                        this._exposedContext = tokenFilterContext;
                    }
                } while (tokenFilterContext != null);
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextToken = this.delegate.nextToken();
            if (nextToken == null) {
                this._currToken = nextToken;
                return nextToken;
            }
            int id = nextToken.id();
            if (id != 1) {
                if (id != 2) {
                    if (id == 3) {
                        TokenFilter tokenFilter = this._itemFilter;
                        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
                        if (tokenFilter == tokenFilter2) {
                            this._headContext = this._headContext.createChildArrayContext(tokenFilter, true);
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (tokenFilter == null) {
                            this.delegate.skipChildren();
                        } else {
                            TokenFilter checkValue2 = this._headContext.checkValue(tokenFilter);
                            if (checkValue2 == null) {
                                this.delegate.skipChildren();
                            } else {
                                if (checkValue2 != tokenFilter2) {
                                    checkValue2 = checkValue2.filterStartArray();
                                }
                                this._itemFilter = checkValue2;
                                if (checkValue2 == tokenFilter2) {
                                    this._headContext = this._headContext.createChildArrayContext(checkValue2, true);
                                    this._currToken = nextToken;
                                    return nextToken;
                                } else if (checkValue2 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                                    TokenFilterContext createChildArrayContext = this._headContext.createChildArrayContext(checkValue2, false);
                                    this._headContext = createChildArrayContext;
                                    if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering2 = _nextTokenWithBuffering(createChildArrayContext)) != null) {
                                        this._currToken = _nextTokenWithBuffering2;
                                        return _nextTokenWithBuffering2;
                                    }
                                } else {
                                    this._headContext = this._headContext.createChildArrayContext(checkValue2, true);
                                    this._currToken = nextToken;
                                    return nextToken;
                                }
                            }
                        }
                    } else if (id != 4) {
                        if (id != 5) {
                            TokenFilter tokenFilter3 = this._itemFilter;
                            TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilter3 == tokenFilter4) {
                                this._currToken = nextToken;
                                return nextToken;
                            } else if (tokenFilter3 != null && (((checkValue = this._headContext.checkValue(tokenFilter3)) == tokenFilter4 || (checkValue != null && checkValue.includeValue(this.delegate))) && _verifyAllowedMatches())) {
                                this._currToken = nextToken;
                                return nextToken;
                            }
                        } else {
                            String currentName = this.delegate.getCurrentName();
                            TokenFilter fieldName = this._headContext.setFieldName(currentName);
                            TokenFilter tokenFilter5 = TokenFilter.INCLUDE_ALL;
                            if (fieldName == tokenFilter5) {
                                this._itemFilter = fieldName;
                                this._currToken = nextToken;
                                return nextToken;
                            } else if (fieldName == null) {
                                this.delegate.nextToken();
                                this.delegate.skipChildren();
                            } else {
                                TokenFilter includeProperty = fieldName.includeProperty(currentName);
                                if (includeProperty == null) {
                                    this.delegate.nextToken();
                                    this.delegate.skipChildren();
                                } else {
                                    this._itemFilter = includeProperty;
                                    if (includeProperty == tokenFilter5) {
                                        if (!_verifyAllowedMatches()) {
                                            this.delegate.nextToken();
                                            this.delegate.skipChildren();
                                        } else if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
                                            this._currToken = nextToken;
                                            return nextToken;
                                        }
                                    }
                                    if (!(this._inclusion == TokenFilter.Inclusion.ONLY_INCLUDE_ALL || (_nextTokenWithBuffering3 = _nextTokenWithBuffering(this._headContext)) == null)) {
                                        this._currToken = _nextTokenWithBuffering3;
                                        return _nextTokenWithBuffering3;
                                    }
                                }
                            }
                        }
                    }
                }
                boolean isStartHandled = this._headContext.isStartHandled();
                TokenFilter filter = this._headContext.getFilter();
                if (!(filter == null || filter == TokenFilter.INCLUDE_ALL)) {
                    filter.filterFinishArray();
                }
                TokenFilterContext parent3 = this._headContext.getParent();
                this._headContext = parent3;
                this._itemFilter = parent3.getFilter();
                if (isStartHandled) {
                    this._currToken = nextToken;
                    return nextToken;
                }
            } else {
                TokenFilter tokenFilter6 = this._itemFilter;
                TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter6 == tokenFilter7) {
                    this._headContext = this._headContext.createChildObjectContext(tokenFilter6, true);
                    this._currToken = nextToken;
                    return nextToken;
                } else if (tokenFilter6 == null) {
                    this.delegate.skipChildren();
                } else {
                    TokenFilter checkValue3 = this._headContext.checkValue(tokenFilter6);
                    if (checkValue3 == null) {
                        this.delegate.skipChildren();
                    } else {
                        if (checkValue3 != tokenFilter7) {
                            checkValue3 = checkValue3.filterStartObject();
                        }
                        this._itemFilter = checkValue3;
                        if (checkValue3 == tokenFilter7) {
                            this._headContext = this._headContext.createChildObjectContext(checkValue3, true);
                            this._currToken = nextToken;
                            return nextToken;
                        } else if (checkValue3 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
                            TokenFilterContext createChildObjectContext = this._headContext.createChildObjectContext(checkValue3, false);
                            this._headContext = createChildObjectContext;
                            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH && (_nextTokenWithBuffering = _nextTokenWithBuffering(createChildObjectContext)) != null) {
                                this._currToken = _nextTokenWithBuffering;
                                return _nextTokenWithBuffering;
                            }
                        } else {
                            this._headContext = this._headContext.createChildObjectContext(checkValue3, true);
                            this._currToken = nextToken;
                            return nextToken;
                        }
                    }
                }
            }
            return _nextToken2();
        }
        this._currToken = null;
        return null;
    }

    public JsonToken nextValue() throws IOException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        return this.delegate.readBinaryValue(base64Variant, outputStream);
    }

    public JsonParser skipChildren() throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i3 = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i3++;
            } else if (nextToken.isStructEnd() && i3 - 1 == 0) {
                return this;
            }
        }
    }

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, TokenFilter.Inclusion inclusion, boolean z2) {
        super(jsonParser);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._headContext = TokenFilterContext.createRootContext(tokenFilter);
        this._inclusion = inclusion;
        this._allowMultipleMatches = z2;
    }

    public boolean getValueAsBoolean(boolean z2) throws IOException {
        return this.delegate.getValueAsBoolean(z2);
    }

    public double getValueAsDouble(double d2) throws IOException {
        return this.delegate.getValueAsDouble(d2);
    }

    public int getValueAsInt(int i3) throws IOException {
        return this.delegate.getValueAsInt(i3);
    }

    public long getValueAsLong(long j2) throws IOException {
        return this.delegate.getValueAsLong(j2);
    }

    public String getValueAsString(String str) throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            return currentName();
        }
        return this.delegate.getValueAsString(str);
    }
}
