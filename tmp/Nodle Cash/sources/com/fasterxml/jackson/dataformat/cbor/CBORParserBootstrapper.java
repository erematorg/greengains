package com.fasterxml.jackson.dataformat.cbor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import java.io.IOException;
import java.io.InputStream;

public class CBORParserBootstrapper {
    protected final boolean _bufferRecyclable;
    protected final IOContext _context;
    protected final InputStream _in;
    protected final byte[] _inputBuffer;
    protected int _inputEnd;
    protected int _inputProcessed;
    protected int _inputPtr;

    public CBORParserBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._inputProcessed = 0;
        this._bufferRecyclable = true;
    }

    public static MatchStrength hasCBORFormat(InputAccessor inputAccessor) throws IOException {
        if (!inputAccessor.hasMoreBytes()) {
            return MatchStrength.INCONCLUSIVE;
        }
        byte nextByte = inputAccessor.nextByte();
        if (nextByte == -65) {
            if (inputAccessor.hasMoreBytes()) {
                byte nextByte2 = inputAccessor.nextByte();
                return nextByte2 == -1 ? MatchStrength.SOLID_MATCH : CBORConstants.hasMajorType(3, nextByte2) ? MatchStrength.SOLID_MATCH : MatchStrength.INCONCLUSIVE;
            }
        } else if (nextByte == -97) {
            if (inputAccessor.hasMoreBytes()) {
                return inputAccessor.nextByte() == -1 ? MatchStrength.SOLID_MATCH : MatchStrength.WEAK_MATCH;
            }
        } else if (CBORConstants.hasMajorType(6, nextByte)) {
            return (nextByte == -39 && inputAccessor.hasMoreBytes() && inputAccessor.nextByte() == -39 && inputAccessor.hasMoreBytes() && inputAccessor.nextByte() == -9) ? MatchStrength.FULL_MATCH : MatchStrength.WEAK_MATCH;
        } else {
            if (CBORConstants.hasMajorType(7, nextByte)) {
                return (nextByte == -12 || nextByte == -11 || nextByte == -10) ? MatchStrength.SOLID_MATCH : MatchStrength.NO_MATCH;
            }
        }
        return MatchStrength.INCONCLUSIVE;
    }

    public CBORParser constructParser(int i3, int i4, int i5, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer) throws IOException, JsonParseException {
        int i6 = i3;
        ByteQuadsCanonicalizer makeChildOrPlaceholder = byteQuadsCanonicalizer.makeChildOrPlaceholder(i3);
        ensureLoaded(1);
        return new CBORParser(this._context, i4, i5, objectCodec, makeChildOrPlaceholder, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, this._bufferRecyclable);
    }

    public boolean ensureLoaded(int i3) throws IOException {
        if (this._in == null) {
            return false;
        }
        int i4 = this._inputEnd - this._inputPtr;
        while (i4 < i3) {
            InputStream inputStream = this._in;
            byte[] bArr = this._inputBuffer;
            int i5 = this._inputEnd;
            int read = inputStream.read(bArr, i5, bArr.length - i5);
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i4 += read;
        }
        return true;
    }

    public CBORParserBootstrapper(IOContext iOContext, byte[] bArr, int i3, int i4) {
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i3;
        this._inputEnd = i4 + i3;
        this._inputProcessed = -i3;
        this._bufferRecyclable = false;
    }
}
