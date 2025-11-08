package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import A.a;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.browser.trusted.c;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.MimeTypes;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.DataSource;
import com.google.common.base.Ascii;
import com.google.common.base.Predicate;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xml.serialize.Method;

public interface HttpDataSource extends DataSource {
    public static final Predicate<String> REJECT_PAYWALL_TYPES = new a(2);

    public static abstract class BaseFactory implements Factory {
        private final RequestProperties defaultRequestProperties = new RequestProperties();

        public abstract HttpDataSource createDataSourceInternal(RequestProperties requestProperties);

        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        public final HttpDataSource createDataSource() {
            return createDataSourceInternal(this.defaultRequestProperties);
        }
    }

    public static final class CleartextNotPermittedException extends HttpDataSourceException {
        public CleartextNotPermittedException(IOException iOException, DataSpec dataSpec) {
            super("Cleartext HTTP traffic not permitted. See https://exoplayer.dev/issues/cleartext-not-permitted", iOException, dataSpec, PlaybackException.ERROR_CODE_IO_CLEARTEXT_NOT_PERMITTED, 1);
        }
    }

    public interface Factory extends DataSource.Factory {
        HttpDataSource createDataSource();

        Factory setDefaultRequestProperties(Map<String, String> map);
    }

    public static class HttpDataSourceException extends DataSourceException {
        public static final int TYPE_CLOSE = 3;
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_READ = 2;
        public final DataSpec dataSpec;
        public final int type;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        @Deprecated
        public HttpDataSourceException(DataSpec dataSpec2, int i3) {
            this(dataSpec2, 2000, i3);
        }

        private static int assignErrorCode(int i3, int i4) {
            return (i3 == 2000 && i4 == 1) ? PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED : i3;
        }

        public static HttpDataSourceException createForIOException(IOException iOException, DataSpec dataSpec2, int i3) {
            String message = iOException.getMessage();
            int i4 = iOException instanceof SocketTimeoutException ? PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT : iOException instanceof InterruptedIOException ? 1004 : (message == null || !Ascii.toLowerCase(message).matches("cleartext.*not permitted.*")) ? PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED : 2007;
            return i4 == 2007 ? new CleartextNotPermittedException(iOException, dataSpec2) : new HttpDataSourceException(iOException, dataSpec2, i4, i3);
        }

        public HttpDataSourceException(DataSpec dataSpec2, int i3, int i4) {
            super(assignErrorCode(i3, i4));
            this.dataSpec = dataSpec2;
            this.type = i4;
        }

        @Deprecated
        public HttpDataSourceException(String str, DataSpec dataSpec2, int i3) {
            this(str, dataSpec2, 2000, i3);
        }

        public HttpDataSourceException(String str, DataSpec dataSpec2, int i3, int i4) {
            super(str, assignErrorCode(i3, i4));
            this.dataSpec = dataSpec2;
            this.type = i4;
        }

        @Deprecated
        public HttpDataSourceException(IOException iOException, DataSpec dataSpec2, int i3) {
            this(iOException, dataSpec2, 2000, i3);
        }

        public HttpDataSourceException(IOException iOException, DataSpec dataSpec2, int i3, int i4) {
            super((Throwable) iOException, assignErrorCode(i3, i4));
            this.dataSpec = dataSpec2;
            this.type = i4;
        }

        @Deprecated
        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec2, int i3) {
            this(str, iOException, dataSpec2, 2000, i3);
        }

        public HttpDataSourceException(String str, @Nullable IOException iOException, DataSpec dataSpec2, int i3, int i4) {
            super(str, iOException, assignErrorCode(i3, i4));
            this.dataSpec = dataSpec2;
            this.type = i4;
        }
    }

    public static final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        public InvalidContentTypeException(String str, DataSpec dataSpec) {
            super(c.a("Invalid content type: ", str), dataSpec, (int) PlaybackException.ERROR_CODE_IO_INVALID_HTTP_CONTENT_TYPE, 1);
            this.contentType = str;
        }
    }

    public static final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map<String, List<String>> headerFields;
        public final byte[] responseBody;
        public final int responseCode;
        @Nullable
        public final String responseMessage;

        @Deprecated
        public InvalidResponseCodeException(int i3, Map<String, List<String>> map, DataSpec dataSpec) {
            this(i3, (String) null, (IOException) null, map, dataSpec, Util.EMPTY_BYTE_ARRAY);
        }

        @Deprecated
        public InvalidResponseCodeException(int i3, @Nullable String str, Map<String, List<String>> map, DataSpec dataSpec) {
            this(i3, str, (IOException) null, map, dataSpec, Util.EMPTY_BYTE_ARRAY);
        }

        public InvalidResponseCodeException(int i3, @Nullable String str, @Nullable IOException iOException, Map<String, List<String>> map, DataSpec dataSpec, byte[] bArr) {
            super(a.k("Response code: ", i3), iOException, dataSpec, PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS, 1);
            this.responseCode = i3;
            this.responseMessage = str;
            this.headerFields = map;
            this.responseBody = bArr;
        }
    }

    /* access modifiers changed from: private */
    static /* synthetic */ boolean lambda$static$0(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = Ascii.toLowerCase(str);
        if (!TextUtils.isEmpty(lowerCase)) {
            return (!lowerCase.contains("text") || lowerCase.contains(MimeTypes.TEXT_VTT)) && !lowerCase.contains(Method.HTML) && !lowerCase.contains("xml");
        }
        return false;
    }

    void clearAllRequestProperties();

    void clearRequestProperty(String str);

    void close() throws HttpDataSourceException;

    int getResponseCode();

    Map<String, List<String>> getResponseHeaders();

    long open(DataSpec dataSpec) throws HttpDataSourceException;

    int read(byte[] bArr, int i3, int i4) throws HttpDataSourceException;

    void setRequestProperty(String str, String str2);

    public static final class RequestProperties {
        private final Map<String, String> requestProperties = new HashMap();
        @Nullable
        private Map<String, String> requestPropertiesSnapshot;

        public synchronized void clear() {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
        }

        public synchronized void clearAndSet(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
            this.requestProperties.putAll(map);
        }

        public synchronized Map<String, String> getSnapshot() {
            try {
                if (this.requestPropertiesSnapshot == null) {
                    this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return this.requestPropertiesSnapshot;
        }

        public synchronized void remove(String str) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.remove(str);
        }

        public synchronized void set(String str, String str2) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.put(str, str2);
        }

        public synchronized void set(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.putAll(map);
        }
    }
}
