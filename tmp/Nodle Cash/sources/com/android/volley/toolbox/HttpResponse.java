package com.android.volley.toolbox;

import androidx.annotation.Nullable;
import com.android.volley.Header;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class HttpResponse {
    @Nullable
    private final InputStream mContent;
    @Nullable
    private final byte[] mContentBytes;
    private final int mContentLength;
    private final List<Header> mHeaders;
    private final int mStatusCode;

    public HttpResponse(int i3, List<Header> list) {
        this(i3, list, -1, (InputStream) null);
    }

    @Nullable
    public final InputStream getContent() {
        InputStream inputStream = this.mContent;
        if (inputStream != null) {
            return inputStream;
        }
        if (this.mContentBytes != null) {
            return new ByteArrayInputStream(this.mContentBytes);
        }
        return null;
    }

    @Nullable
    public final byte[] getContentBytes() {
        return this.mContentBytes;
    }

    public final int getContentLength() {
        return this.mContentLength;
    }

    public final List<Header> getHeaders() {
        return Collections.unmodifiableList(this.mHeaders);
    }

    public final int getStatusCode() {
        return this.mStatusCode;
    }

    public HttpResponse(int i3, List<Header> list, int i4, InputStream inputStream) {
        this.mStatusCode = i3;
        this.mHeaders = list;
        this.mContentLength = i4;
        this.mContent = inputStream;
        this.mContentBytes = null;
    }

    public HttpResponse(int i3, List<Header> list, byte[] bArr) {
        this.mStatusCode = i3;
        this.mHeaders = list;
        this.mContentLength = bArr.length;
        this.mContentBytes = bArr;
        this.mContent = null;
    }
}
