package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser implements Iterator<JsonElement> {
    private final Object lock;
    private final JsonReader parser;

    public JsonStreamParser(String str) {
        this((Reader) new StringReader(str));
    }

    public boolean hasNext() {
        boolean z2;
        synchronized (this.lock) {
            try {
                z2 = this.parser.peek() != JsonToken.END_DOCUMENT;
            } catch (MalformedJsonException e3) {
                throw new JsonSyntaxException((Throwable) e3);
            } catch (IOException e4) {
                throw new JsonIOException((Throwable) e4);
            } catch (Throwable th) {
                throw th;
            }
        }
        return z2;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public JsonStreamParser(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        this.parser = jsonReader;
        jsonReader.setStrictness(Strictness.LENIENT);
        this.lock = new Object();
    }

    public JsonElement next() throws JsonParseException {
        if (hasNext()) {
            try {
                return Streams.parse(this.parser);
            } catch (OutOfMemoryError | StackOverflowError e3) {
                throw new JsonParseException("Failed parsing JSON source to Json", e3);
            }
        } else {
            throw new NoSuchElementException();
        }
    }
}
