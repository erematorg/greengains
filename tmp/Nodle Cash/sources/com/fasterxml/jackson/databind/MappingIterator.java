package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
    protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator((JavaType) null, (JsonParser) null, (DeserializationContext) null, (JsonDeserializer<?>) null, false, (Object) null);
    protected static final int STATE_CLOSED = 0;
    protected static final int STATE_HAS_VALUE = 3;
    protected static final int STATE_MAY_HAVE_VALUE = 2;
    protected static final int STATE_NEED_RESYNC = 1;
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected final JsonParser _parser;
    protected final JsonStreamContext _seqContext;
    protected int _state;
    protected final JavaType _type;
    protected final T _updatedValue;

    public MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z2, Object obj) {
        this._type = javaType;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        this._closeParser = z2;
        if (obj == null) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj;
        }
        if (jsonParser == null) {
            this._seqContext = null;
            this._state = 0;
            return;
        }
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        if (!z2 || !jsonParser.isExpectedStartArrayToken()) {
            JsonToken currentToken = jsonParser.currentToken();
            if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.START_ARRAY) {
                parsingContext = parsingContext.getParent();
            }
        } else {
            jsonParser.clearCurrentToken();
        }
        this._seqContext = parsingContext;
        this._state = 2;
    }

    public static <T> MappingIterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public <R> R _handleIOException(IOException iOException) {
        throw new RuntimeException(iOException.getMessage(), iOException);
    }

    public <R> R _handleMappingException(JsonMappingException jsonMappingException) {
        throw new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
    }

    public void _resync() throws IOException {
        JsonParser jsonParser = this._parser;
        if (jsonParser.getParsingContext() != this._seqContext) {
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY || nextToken == JsonToken.END_OBJECT) {
                    if (jsonParser.getParsingContext() == this._seqContext) {
                        jsonParser.clearCurrentToken();
                        return;
                    }
                } else if (nextToken == JsonToken.START_ARRAY || nextToken == JsonToken.START_OBJECT) {
                    jsonParser.skipChildren();
                } else if (nextToken == null) {
                    return;
                }
            }
        }
    }

    public <R> R _throwNoSuchElement() {
        throw new NoSuchElementException();
    }

    public void close() throws IOException {
        if (this._state != 0) {
            this._state = 0;
            JsonParser jsonParser = this._parser;
            if (jsonParser != null) {
                jsonParser.close();
            }
        }
    }

    public JsonLocation getCurrentLocation() {
        return this._parser.getCurrentLocation();
    }

    public JsonParser getParser() {
        return this._parser;
    }

    public FormatSchema getParserSchema() {
        return this._parser.getSchema();
    }

    public boolean hasNext() {
        try {
            return hasNextValue();
        } catch (JsonMappingException e3) {
            return ((Boolean) _handleMappingException(e3)).booleanValue();
        } catch (IOException e4) {
            return ((Boolean) _handleIOException(e4)).booleanValue();
        }
    }

    public boolean hasNextValue() throws IOException {
        JsonToken nextToken;
        int i3 = this._state;
        if (i3 == 0) {
            return false;
        }
        if (i3 == 1) {
            _resync();
        } else if (i3 != 2) {
            return true;
        }
        JsonParser jsonParser = this._parser;
        if (jsonParser == null) {
            return false;
        }
        if (jsonParser.currentToken() == null && ((nextToken = this._parser.nextToken()) == null || nextToken == JsonToken.END_ARRAY)) {
            this._state = 0;
            if (this._closeParser) {
                this._parser.close();
            }
            return false;
        }
        this._state = 3;
        return true;
    }

    public T next() {
        try {
            return nextValue();
        } catch (JsonMappingException e3) {
            return _handleMappingException(e3);
        } catch (IOException e4) {
            return _handleIOException(e4);
        }
    }

    public T nextValue() throws IOException {
        T t2;
        int i3 = this._state;
        if (i3 == 0) {
            return _throwNoSuchElement();
        }
        int i4 = 2;
        i4 = 1;
        if ((i3 == i4 || i3 == i4) && !hasNextValue()) {
            return _throwNoSuchElement();
        }
        try {
            T t3 = this._updatedValue;
            if (t3 == null) {
                t2 = this._deserializer.deserialize(this._parser, this._context);
            } else {
                this._deserializer.deserialize(this._parser, this._context, t3);
                t2 = this._updatedValue;
            }
            return t2;
        } finally {
            this._state = i4;
            this._parser.clearCurrentToken();
        }
    }

    public List<T> readAll() throws IOException {
        return readAll(new ArrayList());
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public <L extends List<? super T>> L readAll(L l2) throws IOException {
        while (hasNextValue()) {
            l2.add(nextValue());
        }
        return l2;
    }

    public <C extends Collection<? super T>> C readAll(C c3) throws IOException {
        while (hasNextValue()) {
            c3.add(nextValue());
        }
        return c3;
    }
}
