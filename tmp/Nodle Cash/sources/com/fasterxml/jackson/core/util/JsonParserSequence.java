package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserSequence extends JsonParserDelegate {
    protected final boolean _checkForExistingToken;
    protected boolean _hasToken;
    protected int _nextParserIndex;
    protected final JsonParser[] _parsers;

    @Deprecated
    public JsonParserSequence(JsonParser[] jsonParserArr) {
        this(false, jsonParserArr);
    }

    public static JsonParserSequence createFlattened(boolean z2, JsonParser jsonParser, JsonParser jsonParser2) {
        boolean z3 = jsonParser instanceof JsonParserSequence;
        if (!z3 && !(jsonParser2 instanceof JsonParserSequence)) {
            return new JsonParserSequence(z2, new JsonParser[]{jsonParser, jsonParser2});
        }
        ArrayList arrayList = new ArrayList();
        if (z3) {
            ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser);
        }
        if (jsonParser2 instanceof JsonParserSequence) {
            ((JsonParserSequence) jsonParser2).addFlattenedActiveParsers(arrayList);
        } else {
            arrayList.add(jsonParser2);
        }
        return new JsonParserSequence(z2, (JsonParser[]) arrayList.toArray(new JsonParser[arrayList.size()]));
    }

    public void addFlattenedActiveParsers(List<JsonParser> list) {
        int length = this._parsers.length;
        for (int i3 = this._nextParserIndex - 1; i3 < length; i3++) {
            JsonParser jsonParser = this._parsers[i3];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(list);
            } else {
                list.add(jsonParser);
            }
        }
    }

    public void close() throws IOException {
        do {
            this.delegate.close();
        } while (switchToNext());
    }

    public int containedParsersCount() {
        return this._parsers.length;
    }

    public JsonToken nextToken() throws IOException {
        JsonParser jsonParser = this.delegate;
        if (jsonParser == null) {
            return null;
        }
        if (this._hasToken) {
            this._hasToken = false;
            return jsonParser.currentToken();
        }
        JsonToken nextToken = jsonParser.nextToken();
        return nextToken == null ? switchAndReturnNext() : nextToken;
    }

    public JsonParser skipChildren() throws IOException {
        if (this.delegate.currentToken() != JsonToken.START_OBJECT && this.delegate.currentToken() != JsonToken.START_ARRAY) {
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

    public JsonToken switchAndReturnNext() throws IOException {
        JsonToken nextToken;
        do {
            int i3 = this._nextParserIndex;
            JsonParser[] jsonParserArr = this._parsers;
            if (i3 >= jsonParserArr.length) {
                return null;
            }
            this._nextParserIndex = i3 + 1;
            JsonParser jsonParser = jsonParserArr[i3];
            this.delegate = jsonParser;
            if (this._checkForExistingToken && jsonParser.hasCurrentToken()) {
                return this.delegate.getCurrentToken();
            }
            nextToken = this.delegate.nextToken();
        } while (nextToken == null);
        return nextToken;
    }

    public boolean switchToNext() {
        int i3 = this._nextParserIndex;
        JsonParser[] jsonParserArr = this._parsers;
        if (i3 >= jsonParserArr.length) {
            return false;
        }
        this._nextParserIndex = i3 + 1;
        this.delegate = jsonParserArr[i3];
        return true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonParserSequence(boolean z2, JsonParser[] jsonParserArr) {
        super(jsonParserArr[0]);
        boolean z3 = false;
        this._checkForExistingToken = z2;
        if (z2 && this.delegate.hasCurrentToken()) {
            z3 = true;
        }
        this._hasToken = z3;
        this._parsers = jsonParserArr;
        this._nextParserIndex = 1;
    }

    @Deprecated
    public static JsonParserSequence createFlattened(JsonParser jsonParser, JsonParser jsonParser2) {
        return createFlattened(false, jsonParser, jsonParser2);
    }
}
