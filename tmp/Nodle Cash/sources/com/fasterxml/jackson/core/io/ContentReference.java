package com.fasterxml.jackson.core.io;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class ContentReference implements Serializable {
    public static final int DEFAULT_MAX_CONTENT_SNIPPET = 500;
    protected static final ContentReference UNKNOWN_CONTENT = new ContentReference(false, (Object) null);
    private static final long serialVersionUID = 1;
    protected final boolean _isContentTextual;
    protected final int _length;
    protected final int _offset;
    protected final transient Object _rawContent;

    public ContentReference(boolean z2, Object obj) {
        this(z2, obj, -1, -1);
    }

    public static ContentReference construct(boolean z2, Object obj) {
        return new ContentReference(z2, obj);
    }

    public static ContentReference rawReference(boolean z2, Object obj) {
        if (obj instanceof ContentReference) {
            return (ContentReference) obj;
        }
        return new ContentReference(z2, obj);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
    }

    public static ContentReference unknown() {
        return UNKNOWN_CONTENT;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
    }

    public int _append(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isISOControl(charAt) || !_appendEscaped(sb, charAt)) {
                sb.append(charAt);
            }
        }
        sb.append('\"');
        return str.length();
    }

    public boolean _appendEscaped(StringBuilder sb, int i3) {
        if (i3 == 13 || i3 == 10) {
            return false;
        }
        sb.append(AbstractJsonLexerKt.STRING_ESC);
        sb.append(AbstractJsonLexerKt.UNICODE_ESC);
        sb.append(CharTypes.hexToChar((i3 >> 12) & 15));
        sb.append(CharTypes.hexToChar((i3 >> 8) & 15));
        sb.append(CharTypes.hexToChar((i3 >> 4) & 15));
        sb.append(CharTypes.hexToChar(i3 & 15));
        return true;
    }

    public String _truncate(CharSequence charSequence, int[] iArr, int i3) {
        _truncateOffsets(iArr, charSequence.length());
        int i4 = iArr[0];
        return charSequence.subSequence(i4, Math.min(iArr[1], i3) + i4).toString();
    }

    public void _truncateOffsets(int[] iArr, int i3) {
        int i4 = iArr[0];
        if (i4 < 0) {
            i4 = 0;
        } else if (i4 >= i3) {
            i4 = i3;
        }
        iArr[0] = i4;
        int i5 = iArr[1];
        int i6 = i3 - i4;
        if (i5 < 0 || i5 > i6) {
            iArr[1] = i6;
        }
    }

    public StringBuilder appendSourceDescription(StringBuilder sb) {
        String str;
        Object rawContent = getRawContent();
        if (rawContent == null) {
            sb.append("UNKNOWN");
            return sb;
        }
        Class<?> cls = rawContent instanceof Class ? (Class) rawContent : rawContent.getClass();
        String name = cls.getName();
        if (name.startsWith("java.")) {
            name = cls.getSimpleName();
        } else if (rawContent instanceof byte[]) {
            name = "byte[]";
        } else if (rawContent instanceof char[]) {
            name = "char[]";
        }
        sb.append('(');
        sb.append(name);
        sb.append(')');
        if (hasTextualContent()) {
            int maxContentSnippetLength = maxContentSnippetLength();
            int[] iArr = {contentOffset(), contentLength()};
            String str2 = " chars";
            if (rawContent instanceof CharSequence) {
                str = _truncate((CharSequence) rawContent, iArr, maxContentSnippetLength);
            } else if (rawContent instanceof char[]) {
                str = _truncate((char[]) rawContent, iArr, maxContentSnippetLength);
            } else if (rawContent instanceof byte[]) {
                str = _truncate((byte[]) rawContent, iArr, maxContentSnippetLength);
                str2 = " bytes";
            } else {
                str = null;
            }
            if (str != null) {
                _append(sb, str);
                if (iArr[1] > maxContentSnippetLength) {
                    sb.append("[truncated ");
                    sb.append(iArr[1] - maxContentSnippetLength);
                    sb.append(str2);
                    sb.append(AbstractJsonLexerKt.END_LIST);
                }
            }
        } else if (rawContent instanceof byte[]) {
            int contentLength = contentLength();
            if (contentLength < 0) {
                contentLength = ((byte[]) rawContent).length;
            }
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(contentLength);
            sb.append(" bytes]");
        }
        return sb;
    }

    public String buildSourceDescription() {
        return appendSourceDescription(new StringBuilder(200)).toString();
    }

    public int contentLength() {
        return this._length;
    }

    public int contentOffset() {
        return this._offset;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ContentReference)) {
            return false;
        }
        ContentReference contentReference = (ContentReference) obj;
        if (this._offset != contentReference._offset || this._length != contentReference._length) {
            return false;
        }
        Object obj2 = contentReference._rawContent;
        Object obj3 = this._rawContent;
        if (obj3 == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return ((obj3 instanceof File) || (obj3 instanceof URL) || (obj3 instanceof URI)) ? obj3.equals(obj2) : obj3 == obj2;
    }

    public Object getRawContent() {
        return this._rawContent;
    }

    public boolean hasTextualContent() {
        return this._isContentTextual;
    }

    public int hashCode() {
        return Objects.hashCode(this._rawContent);
    }

    public int maxContentSnippetLength() {
        return 500;
    }

    public Object readResolve() {
        return UNKNOWN_CONTENT;
    }

    public ContentReference(boolean z2, Object obj, int i3, int i4) {
        this._isContentTextual = z2;
        this._rawContent = obj;
        this._offset = i3;
        this._length = i4;
    }

    public static ContentReference construct(boolean z2, Object obj, int i3, int i4) {
        return new ContentReference(z2, obj, i3, i4);
    }

    public static ContentReference rawReference(Object obj) {
        return rawReference(false, obj);
    }

    public String _truncate(char[] cArr, int[] iArr, int i3) {
        _truncateOffsets(iArr, cArr.length);
        return new String(cArr, iArr[0], Math.min(iArr[1], i3));
    }

    public String _truncate(byte[] bArr, int[] iArr, int i3) {
        _truncateOffsets(iArr, bArr.length);
        return new String(bArr, iArr[0], Math.min(iArr[1], i3), Charset.forName("UTF-8"));
    }
}
