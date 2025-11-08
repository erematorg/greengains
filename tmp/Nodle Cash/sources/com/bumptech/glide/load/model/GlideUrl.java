package com.bumptech.glide.load.model;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Map;

public class GlideUrl implements Key {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%;$";
    @Nullable
    private volatile byte[] cacheKeyBytes;
    private int hashCode;
    private final Headers headers;
    @Nullable
    private String safeStringUrl;
    @Nullable
    private URL safeUrl;
    @Nullable
    private final String stringUrl;
    @Nullable
    private final URL url;

    public GlideUrl(URL url2) {
        this(url2, Headers.DEFAULT);
    }

    private byte[] getCacheKeyBytes() {
        if (this.cacheKeyBytes == null) {
            this.cacheKeyBytes = getCacheKey().getBytes(Key.CHARSET);
        }
        return this.cacheKeyBytes;
    }

    private String getSafeStringUrl() {
        if (TextUtils.isEmpty(this.safeStringUrl)) {
            String str = this.stringUrl;
            if (TextUtils.isEmpty(str)) {
                str = ((URL) Preconditions.checkNotNull(this.url)).toString();
            }
            this.safeStringUrl = Uri.encode(str, ALLOWED_URI_CHARS);
        }
        return this.safeStringUrl;
    }

    private URL getSafeUrl() throws MalformedURLException {
        if (this.safeUrl == null) {
            this.safeUrl = new URL(getSafeStringUrl());
        }
        return this.safeUrl;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GlideUrl)) {
            return false;
        }
        GlideUrl glideUrl = (GlideUrl) obj;
        return getCacheKey().equals(glideUrl.getCacheKey()) && this.headers.equals(glideUrl.headers);
    }

    public String getCacheKey() {
        String str = this.stringUrl;
        return str != null ? str : ((URL) Preconditions.checkNotNull(this.url)).toString();
    }

    public Map<String, String> getHeaders() {
        return this.headers.getHeaders();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode2 = getCacheKey().hashCode();
            this.hashCode = hashCode2;
            this.hashCode = this.headers.hashCode() + (hashCode2 * 31);
        }
        return this.hashCode;
    }

    public String toString() {
        return getCacheKey();
    }

    public String toStringUrl() {
        return getSafeStringUrl();
    }

    public URL toURL() throws MalformedURLException {
        return getSafeUrl();
    }

    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(getCacheKeyBytes());
    }

    public GlideUrl(String str) {
        this(str, Headers.DEFAULT);
    }

    public GlideUrl(URL url2, Headers headers2) {
        this.url = (URL) Preconditions.checkNotNull(url2);
        this.stringUrl = null;
        this.headers = (Headers) Preconditions.checkNotNull(headers2);
    }

    public GlideUrl(String str, Headers headers2) {
        this.url = null;
        this.stringUrl = Preconditions.checkNotEmpty(str);
        this.headers = (Headers) Preconditions.checkNotNull(headers2);
    }
}
