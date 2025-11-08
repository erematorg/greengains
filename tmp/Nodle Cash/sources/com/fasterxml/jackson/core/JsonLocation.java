package com.fasterxml.jackson.core;

import androidx.compose.ui.autofill.a;
import com.fasterxml.jackson.core.io.ContentReference;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class JsonLocation implements Serializable {
    @Deprecated
    public static final int MAX_CONTENT_SNIPPET = 500;
    public static final JsonLocation NA = new JsonLocation(ContentReference.unknown(), -1, -1, -1, -1);
    private static final long serialVersionUID = 2;
    protected final int _columnNr;
    protected final ContentReference _contentReference;
    protected final int _lineNr;
    protected transient String _sourceDescription;
    protected final long _totalBytes;
    protected final long _totalChars;

    public JsonLocation(ContentReference contentReference, long j2, int i3, int i4) {
        this(contentReference, -1, j2, i3, i4);
    }

    public static ContentReference _wrap(Object obj) {
        return obj instanceof ContentReference ? (ContentReference) obj : ContentReference.construct(false, obj);
    }

    public StringBuilder appendOffsetDescription(StringBuilder sb) {
        if (this._contentReference.hasTextualContent()) {
            sb.append("line: ");
            int i3 = this._lineNr;
            if (i3 >= 0) {
                sb.append(i3);
            } else {
                sb.append("UNKNOWN");
            }
            sb.append(", column: ");
            int i4 = this._columnNr;
            if (i4 >= 0) {
                sb.append(i4);
            } else {
                sb.append("UNKNOWN");
            }
        } else if (this._lineNr > 0) {
            sb.append("line: ");
            sb.append(this._lineNr);
            if (this._columnNr > 0) {
                sb.append(", column: ");
                sb.append(this._columnNr);
            }
        } else {
            sb.append("byte offset: #");
            long j2 = this._totalBytes;
            if (j2 >= 0) {
                sb.append(j2);
            } else {
                sb.append("UNKNOWN");
            }
        }
        return sb;
    }

    public ContentReference contentReference() {
        return this._contentReference;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof JsonLocation)) {
            return false;
        }
        JsonLocation jsonLocation = (JsonLocation) obj;
        ContentReference contentReference = this._contentReference;
        if (contentReference == null) {
            if (jsonLocation._contentReference != null) {
                return false;
            }
        } else if (!contentReference.equals(jsonLocation._contentReference)) {
            return false;
        }
        return this._lineNr == jsonLocation._lineNr && this._columnNr == jsonLocation._columnNr && this._totalChars == jsonLocation._totalChars && this._totalBytes == jsonLocation._totalBytes;
    }

    public long getByteOffset() {
        return this._totalBytes;
    }

    public long getCharOffset() {
        return this._totalChars;
    }

    public int getColumnNr() {
        return this._columnNr;
    }

    public int getLineNr() {
        return this._lineNr;
    }

    @Deprecated
    public Object getSourceRef() {
        return this._contentReference.getRawContent();
    }

    public int hashCode() {
        return ((((this._contentReference == null ? 1 : 2) ^ this._lineNr) + this._columnNr) ^ ((int) this._totalChars)) + ((int) this._totalBytes);
    }

    public String offsetDescription() {
        return appendOffsetDescription(new StringBuilder(40)).toString();
    }

    public String sourceDescription() {
        if (this._sourceDescription == null) {
            this._sourceDescription = this._contentReference.buildSourceDescription();
        }
        return this._sourceDescription;
    }

    public String toString() {
        String sourceDescription = sourceDescription();
        StringBuilder sb = new StringBuilder(sourceDescription.length() + 40);
        a.o(sb, "[Source: ", sourceDescription, "; ");
        StringBuilder appendOffsetDescription = appendOffsetDescription(sb);
        appendOffsetDescription.append(AbstractJsonLexerKt.END_LIST);
        return appendOffsetDescription.toString();
    }

    public JsonLocation(ContentReference contentReference, long j2, long j3, int i3, int i4) {
        this._contentReference = contentReference == null ? ContentReference.unknown() : contentReference;
        this._totalBytes = j2;
        this._totalChars = j3;
        this._lineNr = i3;
        this._columnNr = i4;
    }

    @Deprecated
    public JsonLocation(Object obj, long j2, int i3, int i4) {
        this(_wrap(obj), j2, i3, i4);
    }

    @Deprecated
    public JsonLocation(Object obj, long j2, long j3, int i3, int i4) {
        this(_wrap(obj), j2, j3, i3, i4);
    }
}
