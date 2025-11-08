package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringGeneratorDelegate extends JsonGeneratorDelegate {
    protected boolean _allowMultipleMatches;
    protected TokenFilterContext _filterContext;
    protected TokenFilter.Inclusion _inclusion;
    protected TokenFilter _itemFilter;
    protected int _matchCount;
    protected TokenFilter rootFilter;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public FilteringGeneratorDelegate(JsonGenerator jsonGenerator, TokenFilter tokenFilter, boolean z2, boolean z3) {
        this(jsonGenerator, tokenFilter, z2 ? TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH : TokenFilter.Inclusion.ONLY_INCLUDE_ALL, z3);
    }

    public boolean _checkBinaryWrite() throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            return false;
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (!tokenFilter.includeBinary()) {
            return false;
        }
        _checkParentPath();
        return true;
    }

    public void _checkParentPath() throws IOException {
        _checkParentPath(true);
    }

    public void _checkPropertyParentPath() throws IOException {
        this._matchCount++;
        TokenFilter.Inclusion inclusion = this._inclusion;
        if (inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
            this._filterContext.writePath(this.delegate);
        } else if (inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext.ensureFieldNameWritten(this.delegate);
        }
        if (!this._allowMultipleMatches) {
            this._filterContext.skipParentChecks();
        }
    }

    public boolean _checkRawValueWrite() throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            return false;
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (!tokenFilter.includeRawValue()) {
            return false;
        }
        _checkParentPath();
        return true;
    }

    public TokenFilter getFilter() {
        return this.rootFilter;
    }

    public JsonStreamContext getFilterContext() {
        return this._filterContext;
    }

    public int getMatchCount() {
        return this._matchCount;
    }

    public JsonStreamContext getOutputContext() {
        return this._filterContext;
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i3, int i4) throws IOException {
        if (_checkBinaryWrite()) {
            this.delegate.writeBinary(base64Variant, bArr, i3, i4);
        }
    }

    public void writeBoolean(boolean z2) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeBoolean(z2)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeBoolean(z2);
        }
    }

    public void writeEndArray() throws IOException {
        TokenFilterContext closeArray = this._filterContext.closeArray(this.delegate);
        this._filterContext = closeArray;
        if (closeArray != null) {
            this._itemFilter = closeArray.getFilter();
        }
    }

    public void writeEndObject() throws IOException {
        TokenFilterContext closeObject = this._filterContext.closeObject(this.delegate);
        this._filterContext = closeObject;
        if (closeObject != null) {
            this._itemFilter = closeObject.getFilter();
        }
    }

    public void writeFieldId(long j2) throws IOException {
        writeFieldName(Long.toString(j2));
    }

    public void writeFieldName(String str) throws IOException {
        TokenFilter fieldName = this._filterContext.setFieldName(str);
        if (fieldName == null) {
            this._itemFilter = null;
            return;
        }
        TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
        if (fieldName == tokenFilter) {
            this._itemFilter = fieldName;
            this.delegate.writeFieldName(str);
            return;
        }
        TokenFilter includeProperty = fieldName.includeProperty(str);
        this._itemFilter = includeProperty;
        if (includeProperty == tokenFilter) {
            _checkPropertyParentPath();
        }
    }

    public void writeNull() throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNull()) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNull();
        }
    }

    public void writeNumber(short s3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber((int) s3)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(s3);
        }
    }

    public void writeObjectId(Object obj) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeObjectId(obj);
        }
    }

    public void writeObjectRef(Object obj) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeObjectRef(obj);
        }
    }

    public void writeOmittedField(String str) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeOmittedField(str);
        }
    }

    public void writeRaw(String str) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRaw(str);
        }
    }

    public void writeRawUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRawUTF8String(bArr, i3, i4);
        }
    }

    public void writeRawValue(String str) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRawValue(str);
        }
    }

    public void writeStartArray() throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter, true);
            this.delegate.writeStartArray();
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        this._itemFilter = checkValue;
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this._itemFilter = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this._itemFilter;
        if (tokenFilter3 == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray();
        } else if (tokenFilter3 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter3, false);
        } else {
            _checkParentPath(false);
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray();
        }
    }

    public void writeStartObject() throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, true);
            this.delegate.writeStartObject();
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildObjectContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, true);
            this.delegate.writeStartObject();
        } else if (checkValue == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, false);
        } else {
            _checkParentPath(false);
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, true);
            this.delegate.writeStartObject();
        }
    }

    public void writeString(String str) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeString(str)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeString(str);
        }
    }

    public void writeTypeId(Object obj) throws IOException {
        if (this._itemFilter != null) {
            this.delegate.writeTypeId(obj);
        }
    }

    public void writeUTF8String(byte[] bArr, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeUTF8String(bArr, i3, i4);
        }
    }

    public FilteringGeneratorDelegate(JsonGenerator jsonGenerator, TokenFilter tokenFilter, TokenFilter.Inclusion inclusion, boolean z2) {
        super(jsonGenerator, false);
        this.rootFilter = tokenFilter;
        this._itemFilter = tokenFilter;
        this._filterContext = TokenFilterContext.createRootContext(tokenFilter);
        this._inclusion = inclusion;
        this._allowMultipleMatches = z2;
    }

    public void _checkParentPath(boolean z2) throws IOException {
        if (z2) {
            this._matchCount++;
        }
        TokenFilter.Inclusion inclusion = this._inclusion;
        if (inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
            this._filterContext.writePath(this.delegate);
        } else if (inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext.ensureFieldNameWritten(this.delegate);
        }
        if (z2 && !this._allowMultipleMatches) {
            this._filterContext.skipParentChecks();
        }
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i3) throws IOException {
        if (_checkBinaryWrite()) {
            return this.delegate.writeBinary(base64Variant, inputStream, i3);
        }
        return -1;
    }

    public void writeRaw(String str, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRaw(str, i3, i4);
        }
    }

    public void writeRawValue(String str, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRawValue(str, i3, i4);
        }
    }

    public void writeRaw(SerializableString serializableString) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRaw(serializableString);
        }
    }

    public void writeRawValue(char[] cArr, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRawValue(cArr, i3, i4);
        }
    }

    public void writeNumber(int i3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(i3)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(i3);
        }
    }

    public void writeRaw(char[] cArr, int i3, int i4) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRaw(cArr, i3, i4);
        }
    }

    public void writeString(char[] cArr, int i3, int i4) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                String str = new String(cArr, i3, i4);
                TokenFilter checkValue = this._filterContext.checkValue(this._itemFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeString(str)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeString(cArr, i3, i4);
        }
    }

    public void writeFieldName(SerializableString serializableString) throws IOException {
        TokenFilter fieldName = this._filterContext.setFieldName(serializableString.getValue());
        if (fieldName == null) {
            this._itemFilter = null;
            return;
        }
        TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
        if (fieldName == tokenFilter) {
            this._itemFilter = fieldName;
            this.delegate.writeFieldName(serializableString);
            return;
        }
        TokenFilter includeProperty = fieldName.includeProperty(serializableString.getValue());
        this._itemFilter = includeProperty;
        if (includeProperty == tokenFilter) {
            _checkPropertyParentPath();
        }
    }

    public void writeRaw(char c3) throws IOException {
        if (_checkRawValueWrite()) {
            this.delegate.writeRaw(c3);
        }
    }

    public void writeNumber(long j2) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(j2)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(j2);
        }
    }

    public void writeString(SerializableString serializableString) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeString(serializableString.getValue())) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeString(serializableString);
        }
    }

    public void writeStartObject(Object obj) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, true);
            this.delegate.writeStartObject(obj);
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildObjectContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, true);
            this.delegate.writeStartObject(obj);
        } else if (checkValue == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, false);
        } else {
            _checkParentPath(false);
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, true);
            this.delegate.writeStartObject(obj);
        }
    }

    public void writeStartArray(int i3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter, true);
            this.delegate.writeStartArray(i3);
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        this._itemFilter = checkValue;
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this._itemFilter = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this._itemFilter;
        if (tokenFilter3 == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(i3);
        } else if (tokenFilter3 == null || this._inclusion != TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter3, false);
        } else {
            _checkParentPath(false);
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(i3);
        }
    }

    public void writeNumber(BigInteger bigInteger) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(bigInteger)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(bigInteger);
        }
    }

    public void writeString(Reader reader, int i3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeString(reader, i3)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeString(reader, i3);
        }
    }

    public void writeNumber(double d2) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(d2)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(d2);
        }
    }

    public void writeNumber(float f2) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(f2)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(f2);
        }
    }

    public void writeStartObject(Object obj, int i3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildObjectContext(tokenFilter, true);
            this.delegate.writeStartObject(obj, i3);
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildObjectContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildObjectContext(checkValue, true);
            this.delegate.writeStartObject(obj, i3);
            return;
        }
        this._filterContext = this._filterContext.createChildObjectContext(checkValue, false);
    }

    public void writeStartArray(Object obj) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter, true);
            this.delegate.writeStartArray(obj);
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        this._itemFilter = checkValue;
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this._itemFilter = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this._itemFilter;
        if (tokenFilter3 == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(obj);
            return;
        }
        this._filterContext = this._filterContext.createChildArrayContext(tokenFilter3, false);
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeNumber(bigDecimal)) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(bigDecimal);
        }
    }

    public void writeNumber(String str) throws IOException, UnsupportedOperationException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeRawValue()) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(str);
        }
    }

    public void writeStartArray(Object obj, int i3) throws IOException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this._filterContext = this._filterContext.createChildArrayContext(tokenFilter, true);
            this.delegate.writeStartArray(obj, i3);
            return;
        }
        TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
        this._itemFilter = checkValue;
        if (checkValue == null) {
            this._filterContext = this._filterContext.createChildArrayContext((TokenFilter) null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this._itemFilter = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this._itemFilter;
        if (tokenFilter3 == tokenFilter2) {
            _checkParentPath();
            this._filterContext = this._filterContext.createChildArrayContext(this._itemFilter, true);
            this.delegate.writeStartArray(obj, i3);
            return;
        }
        this._filterContext = this._filterContext.createChildArrayContext(tokenFilter3, false);
    }

    public void writeNumber(char[] cArr, int i3, int i4) throws IOException, UnsupportedOperationException {
        TokenFilter tokenFilter = this._itemFilter;
        if (tokenFilter != null) {
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                TokenFilter checkValue = this._filterContext.checkValue(tokenFilter);
                if (checkValue != null) {
                    if (checkValue == tokenFilter2 || checkValue.includeRawValue()) {
                        _checkParentPath();
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
            this.delegate.writeNumber(cArr, i3, i4);
        }
    }
}
