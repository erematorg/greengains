package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import A.a;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.browser.trusted.sharing.ShareTarget;
import com.appsamurai.storyly.exoplayer2.common.ExoPlayerLibraryInfo;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public final class DataSpec {
    public static final int FLAG_ALLOW_CACHE_FRAGMENTATION = 4;
    public static final int FLAG_ALLOW_GZIP = 1;
    public static final int FLAG_DONT_CACHE_IF_LENGTH_UNKNOWN = 2;
    public static final int FLAG_MIGHT_NOT_USE_FULL_NETWORK_SPEED = 8;
    public static final int HTTP_METHOD_GET = 1;
    public static final int HTTP_METHOD_HEAD = 3;
    public static final int HTTP_METHOD_POST = 2;
    @Deprecated
    public final long absoluteStreamPosition;
    @Nullable
    public final Object customData;
    public final int flags;
    @Nullable
    public final byte[] httpBody;
    public final int httpMethod;
    public final Map<String, String> httpRequestHeaders;
    @Nullable
    public final String key;
    public final long length;
    public final long position;
    public final Uri uri;
    public final long uriPositionOffset;

    public static final class Builder {
        @Nullable
        private Object customData;
        private int flags;
        @Nullable
        private byte[] httpBody;
        private int httpMethod;
        private Map<String, String> httpRequestHeaders;
        @Nullable
        private String key;
        private long length;
        private long position;
        @Nullable
        private Uri uri;
        private long uriPositionOffset;

        public DataSpec build() {
            Assertions.checkStateNotNull(this.uri, "The uri must be set.");
            return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position, this.length, this.key, this.flags, this.customData);
        }

        public Builder setCustomData(@Nullable Object obj) {
            this.customData = obj;
            return this;
        }

        public Builder setFlags(int i3) {
            this.flags = i3;
            return this;
        }

        public Builder setHttpBody(@Nullable byte[] bArr) {
            this.httpBody = bArr;
            return this;
        }

        public Builder setHttpMethod(int i3) {
            this.httpMethod = i3;
            return this;
        }

        public Builder setHttpRequestHeaders(Map<String, String> map) {
            this.httpRequestHeaders = map;
            return this;
        }

        public Builder setKey(@Nullable String str) {
            this.key = str;
            return this;
        }

        public Builder setLength(long j2) {
            this.length = j2;
            return this;
        }

        public Builder setPosition(long j2) {
            this.position = j2;
            return this;
        }

        public Builder setUri(String str) {
            this.uri = Uri.parse(str);
            return this;
        }

        public Builder setUriPositionOffset(long j2) {
            this.uriPositionOffset = j2;
            return this;
        }

        public Builder() {
            this.httpMethod = 1;
            this.httpRequestHeaders = Collections.emptyMap();
            this.length = -1;
        }

        public Builder setUri(Uri uri2) {
            this.uri = uri2;
            return this;
        }

        private Builder(DataSpec dataSpec) {
            this.uri = dataSpec.uri;
            this.uriPositionOffset = dataSpec.uriPositionOffset;
            this.httpMethod = dataSpec.httpMethod;
            this.httpBody = dataSpec.httpBody;
            this.httpRequestHeaders = dataSpec.httpRequestHeaders;
            this.position = dataSpec.position;
            this.length = dataSpec.length;
            this.key = dataSpec.key;
            this.flags = dataSpec.flags;
            this.customData = dataSpec.customData;
        }
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HttpMethod {
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.datasource");
    }

    public static String getStringForHttpMethod(int i3) {
        if (i3 == 1) {
            return ShareTarget.METHOD_GET;
        }
        if (i3 == 2) {
            return "POST";
        }
        if (i3 == 3) {
            return "HEAD";
        }
        throw new IllegalStateException();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public final String getHttpMethodString() {
        return getStringForHttpMethod(this.httpMethod);
    }

    public boolean isFlagSet(int i3) {
        return (this.flags & i3) == i3;
    }

    public DataSpec subrange(long j2) {
        long j3 = this.length;
        long j4 = -1;
        if (j3 != -1) {
            j4 = j3 - j2;
        }
        return subrange(j2, j4);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSpec[");
        sb.append(getHttpMethodString());
        sb.append(StringUtils.SPACE);
        sb.append(this.uri);
        sb.append(", ");
        sb.append(this.position);
        sb.append(", ");
        sb.append(this.length);
        sb.append(", ");
        sb.append(this.key);
        sb.append(", ");
        return a.m(sb, "]", this.flags);
    }

    public DataSpec withAdditionalHeaders(Map<String, String> map) {
        HashMap hashMap = new HashMap(this.httpRequestHeaders);
        hashMap.putAll(map);
        return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, hashMap, this.position, this.length, this.key, this.flags, this.customData);
    }

    public DataSpec withRequestHeaders(Map<String, String> map) {
        return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, map, this.position, this.length, this.key, this.flags, this.customData);
    }

    public DataSpec withUri(Uri uri2) {
        return new DataSpec(uri2, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position, this.length, this.key, this.flags, this.customData);
    }

    public DataSpec(Uri uri2) {
        this(uri2, 0, -1);
    }

    public DataSpec subrange(long j2, long j3) {
        if (j2 == 0 && this.length == j3) {
            return this;
        }
        return new DataSpec(this.uri, this.uriPositionOffset, this.httpMethod, this.httpBody, this.httpRequestHeaders, this.position + j2, j3, this.key, this.flags, this.customData);
    }

    public DataSpec(Uri uri2, long j2, long j3) {
        this(uri2, 0, 1, (byte[]) null, Collections.emptyMap(), j2, j3, (String) null, 0, (Object) null);
    }

    @Deprecated
    public DataSpec(Uri uri2, int i3) {
        this(uri2, 0, -1, (String) null, i3);
    }

    @Deprecated
    public DataSpec(Uri uri2, long j2, long j3, @Nullable String str) {
        this(uri2, j2, j2, j3, str, 0);
    }

    @Deprecated
    public DataSpec(Uri uri2, long j2, long j3, @Nullable String str, int i3) {
        this(uri2, j2, j2, j3, str, i3);
    }

    @Deprecated
    public DataSpec(Uri uri2, long j2, long j3, @Nullable String str, int i3, Map<String, String> map) {
        this(uri2, 1, (byte[]) null, j2, j2, j3, str, i3, map);
    }

    @Deprecated
    public DataSpec(Uri uri2, long j2, long j3, long j4, @Nullable String str, int i3) {
        this(uri2, (byte[]) null, j2, j3, j4, str, i3);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DataSpec(Uri uri2, @Nullable byte[] bArr, long j2, long j3, long j4, @Nullable String str, int i3) {
        this(uri2, bArr != null ? 2 : 1, bArr, j2, j3, j4, str, i3);
    }

    @Deprecated
    public DataSpec(Uri uri2, int i3, @Nullable byte[] bArr, long j2, long j3, long j4, @Nullable String str, int i4) {
        this(uri2, i3, bArr, j2, j3, j4, str, i4, Collections.emptyMap());
    }

    @Deprecated
    public DataSpec(Uri uri2, int i3, @Nullable byte[] bArr, long j2, long j3, long j4, @Nullable String str, int i4, Map<String, String> map) {
        this(uri2, j2 - j3, i3, bArr, map, j3, j4, str, i4, (Object) null);
    }

    private DataSpec(Uri uri2, long j2, int i3, @Nullable byte[] bArr, Map<String, String> map, long j3, long j4, @Nullable String str, int i4, @Nullable Object obj) {
        long j5 = j2;
        byte[] bArr2 = bArr;
        long j6 = j3;
        long j7 = j4;
        long j8 = j5 + j6;
        boolean z2 = false;
        Assertions.checkArgument(j8 >= 0);
        Assertions.checkArgument(j6 >= 0);
        Assertions.checkArgument((j7 > 0 || j7 == -1) ? true : z2);
        this.uri = uri2;
        this.uriPositionOffset = j5;
        this.httpMethod = i3;
        this.httpBody = (bArr2 == null || bArr2.length == 0) ? null : bArr2;
        this.httpRequestHeaders = Collections.unmodifiableMap(new HashMap(map));
        this.position = j6;
        this.absoluteStreamPosition = j8;
        this.length = j7;
        this.key = str;
        this.flags = i4;
        this.customData = obj;
    }
}
