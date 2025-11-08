package com.appsamurai.storyly.exoplayer2.datasource.upstream;

import A.a;
import android.net.Uri;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.browser.trusted.c;
import com.adjust.sdk.Constants;
import com.appsamurai.storyly.exoplayer2.common.PlaybackException;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.datasource.upstream.HttpDataSource;
import com.google.common.base.Predicate;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class DefaultHttpDataSource extends BaseDataSource implements HttpDataSource {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final int HTTP_STATUS_PERMANENT_REDIRECT = 308;
    private static final int HTTP_STATUS_TEMPORARY_REDIRECT = 307;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "DefaultHttpDataSource";
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesToRead;
    private final int connectTimeoutMillis;
    @Nullable
    private HttpURLConnection connection;
    @Nullable
    private Predicate<String> contentTypePredicate;
    @Nullable
    private DataSpec dataSpec;
    @Nullable
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    @Nullable
    private InputStream inputStream;
    private final boolean keepPostFor302Redirects;
    private boolean opened;
    private final int readTimeoutMillis;
    private final HttpDataSource.RequestProperties requestProperties;
    private int responseCode;
    @Nullable
    private final String userAgent;

    public static final class Factory implements HttpDataSource.Factory {
        private boolean allowCrossProtocolRedirects;
        private int connectTimeoutMs = 8000;
        @Nullable
        private Predicate<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private boolean keepPostFor302Redirects;
        private int readTimeoutMs = 8000;
        @Nullable
        private TransferListener transferListener;
        @Nullable
        private String userAgent;

        public Factory setAllowCrossProtocolRedirects(boolean z2) {
            this.allowCrossProtocolRedirects = z2;
            return this;
        }

        public Factory setConnectTimeoutMs(int i3) {
            this.connectTimeoutMs = i3;
            return this;
        }

        public Factory setContentTypePredicate(@Nullable Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        public Factory setKeepPostFor302Redirects(boolean z2) {
            this.keepPostFor302Redirects = z2;
            return this;
        }

        public Factory setReadTimeoutMs(int i3) {
            this.readTimeoutMs = i3;
            return this;
        }

        public Factory setTransferListener(@Nullable TransferListener transferListener2) {
            this.transferListener = transferListener2;
            return this;
        }

        public Factory setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        public DefaultHttpDataSource createDataSource() {
            DefaultHttpDataSource defaultHttpDataSource = new DefaultHttpDataSource(this.userAgent, this.connectTimeoutMs, this.readTimeoutMs, this.allowCrossProtocolRedirects, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects);
            TransferListener transferListener2 = this.transferListener;
            if (transferListener2 != null) {
                defaultHttpDataSource.addTransferListener(transferListener2);
            }
            return defaultHttpDataSource;
        }
    }

    public static class NullFilteringHeadersMap extends ForwardingMap<String, List<String>> {
        private final Map<String, List<String>> headers;

        public NullFilteringHeadersMap(Map<String, List<String>> map) {
            this.headers = map;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$entrySet$1(Map.Entry entry) {
            return entry.getKey() != null;
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ boolean lambda$keySet$0(String str) {
            return str != null;
        }

        public boolean containsKey(@Nullable Object obj) {
            return obj != null && super.containsKey(obj);
        }

        public boolean containsValue(@Nullable Object obj) {
            return super.standardContainsValue(obj);
        }

        public Set<Map.Entry<String, List<String>>> entrySet() {
            return Sets.filter(super.entrySet(), new a(1));
        }

        public boolean equals(@Nullable Object obj) {
            return obj != null && super.standardEquals(obj);
        }

        public int hashCode() {
            return super.standardHashCode();
        }

        public boolean isEmpty() {
            if (!super.isEmpty()) {
                return super.size() == 1 && super.containsKey((Object) null);
            }
            return true;
        }

        public Set<String> keySet() {
            return Sets.filter(super.keySet(), new a(0));
        }

        public int size() {
            return super.size() - (super.containsKey((Object) null) ? 1 : 0);
        }

        public Map<String, List<String>> delegate() {
            return this.headers;
        }

        @Nullable
        public List<String> get(@Nullable Object obj) {
            if (obj == null) {
                return null;
            }
            return (List) super.get(obj);
        }
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e3) {
                Log.e(TAG, "Unexpected error while disconnecting", e3);
            }
            this.connection = null;
        }
    }

    private URL handleRedirect(URL url, @Nullable String str, DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        if (str != null) {
            try {
                URL url2 = new URL(url, str);
                String protocol = url2.getProtocol();
                if (!Constants.SCHEME.equals(protocol) && !"http".equals(protocol)) {
                    throw new HttpDataSource.HttpDataSourceException(c.a("Unsupported protocol redirect: ", protocol), dataSpec2, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
                } else if (this.allowCrossProtocolRedirects || protocol.equals(url.getProtocol())) {
                    return url2;
                } else {
                    throw new HttpDataSource.HttpDataSourceException("Disallowed cross-protocol redirect (" + url.getProtocol() + " to " + protocol + ")", dataSpec2, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
                }
            } catch (MalformedURLException e3) {
                throw new HttpDataSource.HttpDataSourceException((IOException) e3, dataSpec2, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
            }
        } else {
            throw new HttpDataSource.HttpDataSourceException("Null location redirect", dataSpec2, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
        }
    }

    private static boolean isCompressed(HttpURLConnection httpURLConnection) {
        return "gzip".equalsIgnoreCase(httpURLConnection.getHeaderField(HttpHeaders.CONTENT_ENCODING));
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec2) throws IOException {
        HttpURLConnection makeConnection;
        DataSpec dataSpec3 = dataSpec2;
        URL url = new URL(dataSpec3.uri.toString());
        int i3 = dataSpec3.httpMethod;
        byte[] bArr = dataSpec3.httpBody;
        long j2 = dataSpec3.position;
        long j3 = dataSpec3.length;
        boolean isFlagSet = dataSpec3.isFlagSet(1);
        if (!this.allowCrossProtocolRedirects && !this.keepPostFor302Redirects) {
            return makeConnection(url, i3, bArr, j2, j3, isFlagSet, true, dataSpec3.httpRequestHeaders);
        }
        int i4 = 0;
        URL url2 = url;
        int i5 = i3;
        byte[] bArr2 = bArr;
        while (true) {
            int i6 = i4 + 1;
            if (i4 <= 20) {
                long j4 = j2;
                long j5 = j2;
                int i7 = i5;
                int i8 = i6;
                URL url3 = url2;
                long j6 = j3;
                makeConnection = makeConnection(url2, i5, bArr2, j4, j3, isFlagSet, false, dataSpec3.httpRequestHeaders);
                int responseCode2 = makeConnection.getResponseCode();
                String headerField = makeConnection.getHeaderField(HttpHeaders.LOCATION);
                if ((i7 == 1 || i7 == 3) && (responseCode2 == 300 || responseCode2 == 301 || responseCode2 == 302 || responseCode2 == 303 || responseCode2 == 307 || responseCode2 == 308)) {
                    URL url4 = url3;
                    makeConnection.disconnect();
                    url2 = handleRedirect(url4, headerField, dataSpec3);
                    i5 = i7;
                } else if (i7 != 2 || (responseCode2 != 300 && responseCode2 != 301 && responseCode2 != 302 && responseCode2 != 303)) {
                    return makeConnection;
                } else {
                    makeConnection.disconnect();
                    if (!this.keepPostFor302Redirects || responseCode2 != 302) {
                        bArr2 = null;
                        i5 = 1;
                    } else {
                        i5 = i7;
                    }
                    url2 = handleRedirect(url3, headerField, dataSpec3);
                }
                i4 = i8;
                j2 = j5;
                j3 = j6;
            } else {
                throw new HttpDataSource.HttpDataSourceException((IOException) new NoRouteToHostException(a.k("Too many redirects: ", i6)), dataSpec3, (int) PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED, 1);
            }
        }
        return makeConnection;
    }

    private static void maybeTerminateInputStream(@Nullable HttpURLConnection httpURLConnection, long j2) {
        int i3;
        if (httpURLConnection != null && (i3 = Util.SDK_INT) >= 19 && i3 <= 20) {
            try {
                InputStream inputStream2 = httpURLConnection.getInputStream();
                if (j2 == -1) {
                    if (inputStream2.read() == -1) {
                        return;
                    }
                } else if (j2 <= 2048) {
                    return;
                }
                String name = inputStream2.getClass().getName();
                if ("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream".equals(name) || "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream".equals(name)) {
                    Method declaredMethod = ((Class) Assertions.checkNotNull(inputStream2.getClass().getSuperclass())).getDeclaredMethod("unexpectedEndOfInput", (Class[]) null);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(inputStream2, (Object[]) null);
                }
            } catch (Exception unused) {
            }
        }
    }

    private int readInternal(byte[] bArr, int i3, int i4) throws IOException {
        if (i4 == 0) {
            return 0;
        }
        long j2 = this.bytesToRead;
        if (j2 != -1) {
            long j3 = j2 - this.bytesRead;
            if (j3 == 0) {
                return -1;
            }
            i4 = (int) Math.min((long) i4, j3);
        }
        int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, i3, i4);
        if (read == -1) {
            return -1;
        }
        this.bytesRead += (long) read;
        bytesTransferred(read);
        return read;
    }

    private void skipFully(long j2, DataSpec dataSpec2) throws IOException {
        if (j2 != 0) {
            byte[] bArr = new byte[4096];
            while (j2 > 0) {
                int read = ((InputStream) Util.castNonNull(this.inputStream)).read(bArr, 0, (int) Math.min(j2, (long) 4096));
                if (Thread.currentThread().isInterrupted()) {
                    throw new HttpDataSource.HttpDataSourceException((IOException) new InterruptedIOException(), dataSpec2, 2000, 1);
                } else if (read != -1) {
                    j2 -= (long) read;
                    bytesTransferred(read);
                } else {
                    throw new HttpDataSource.HttpDataSourceException(dataSpec2, 2008, 1);
                }
            }
        }
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    public void close() throws HttpDataSource.HttpDataSourceException {
        try {
            InputStream inputStream2 = this.inputStream;
            if (inputStream2 != null) {
                long j2 = this.bytesToRead;
                long j3 = -1;
                if (j2 != -1) {
                    j3 = j2 - this.bytesRead;
                }
                maybeTerminateInputStream(this.connection, j3);
                inputStream2.close();
            }
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e3) {
            throw new HttpDataSource.HttpDataSourceException(e3, (DataSpec) Util.castNonNull(this.dataSpec), 2000, 3);
        } catch (Throwable th) {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
            throw th;
        }
    }

    public int getResponseCode() {
        int i3;
        if (this.connection == null || (i3 = this.responseCode) <= 0) {
            return -1;
        }
        return i3;
    }

    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.connection;
        return httpURLConnection == null ? ImmutableMap.of() : new NullFilteringHeadersMap(httpURLConnection.getHeaderFields());
    }

    @Nullable
    public Uri getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public long open(DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        byte[] bArr;
        this.dataSpec = dataSpec2;
        long j2 = 0;
        this.bytesRead = 0;
        this.bytesToRead = 0;
        transferInitializing(dataSpec2);
        try {
            HttpURLConnection makeConnection = makeConnection(dataSpec2);
            this.connection = makeConnection;
            this.responseCode = makeConnection.getResponseCode();
            String responseMessage = makeConnection.getResponseMessage();
            int i3 = this.responseCode;
            long j3 = -1;
            if (i3 < 200 || i3 > 299) {
                Map<String, List<String>> headerFields = makeConnection.getHeaderFields();
                if (this.responseCode == 416) {
                    if (dataSpec2.position == HttpUtil.getDocumentSize(makeConnection.getHeaderField(HttpHeaders.CONTENT_RANGE))) {
                        this.opened = true;
                        transferStarted(dataSpec2);
                        long j4 = dataSpec2.length;
                        if (j4 != -1) {
                            return j4;
                        }
                        return 0;
                    }
                }
                InputStream errorStream = makeConnection.getErrorStream();
                if (errorStream != null) {
                    try {
                        bArr = Util.toByteArray(errorStream);
                    } catch (IOException unused) {
                        bArr = Util.EMPTY_BYTE_ARRAY;
                    }
                } else {
                    bArr = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr2 = bArr;
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidResponseCodeException(this.responseCode, responseMessage, this.responseCode == 416 ? new DataSourceException(2008) : null, headerFields, dataSpec2, bArr2);
            }
            String contentType = makeConnection.getContentType();
            Predicate<String> predicate = this.contentTypePredicate;
            if (predicate == null || predicate.apply(contentType)) {
                if (this.responseCode == 200) {
                    long j5 = dataSpec2.position;
                    if (j5 != 0) {
                        j2 = j5;
                    }
                }
                boolean isCompressed = isCompressed(makeConnection);
                if (!isCompressed) {
                    long j6 = dataSpec2.length;
                    if (j6 != -1) {
                        this.bytesToRead = j6;
                    } else {
                        long contentLength = HttpUtil.getContentLength(makeConnection.getHeaderField(HttpHeaders.CONTENT_LENGTH), makeConnection.getHeaderField(HttpHeaders.CONTENT_RANGE));
                        if (contentLength != -1) {
                            j3 = contentLength - j2;
                        }
                        this.bytesToRead = j3;
                    }
                } else {
                    this.bytesToRead = dataSpec2.length;
                }
                try {
                    this.inputStream = makeConnection.getInputStream();
                    if (isCompressed) {
                        this.inputStream = new GZIPInputStream(this.inputStream);
                    }
                    this.opened = true;
                    transferStarted(dataSpec2);
                    try {
                        skipFully(j2, dataSpec2);
                        return this.bytesToRead;
                    } catch (IOException e3) {
                        closeConnectionQuietly();
                        if (e3 instanceof HttpDataSource.HttpDataSourceException) {
                            throw ((HttpDataSource.HttpDataSourceException) e3);
                        }
                        throw new HttpDataSource.HttpDataSourceException(e3, dataSpec2, 2000, 1);
                    }
                } catch (IOException e4) {
                    closeConnectionQuietly();
                    throw new HttpDataSource.HttpDataSourceException(e4, dataSpec2, 2000, 1);
                }
            } else {
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidContentTypeException(contentType, dataSpec2);
            }
        } catch (IOException e5) {
            closeConnectionQuietly();
            throw HttpDataSource.HttpDataSourceException.createForIOException(e5, dataSpec2, 1);
        }
    }

    @VisibleForTesting
    public HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    public int read(byte[] bArr, int i3, int i4) throws HttpDataSource.HttpDataSourceException {
        try {
            return readInternal(bArr, i3, i4);
        } catch (IOException e3) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e3, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    @Deprecated
    public void setContentTypePredicate(@Nullable Predicate<String> predicate) {
        this.contentTypePredicate = predicate;
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    @Deprecated
    public DefaultHttpDataSource() {
        this((String) null, 8000, 8000);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str) {
        this(str, 8000, 8000);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str, int i3, int i4) {
        this(str, i3, i4, false, (HttpDataSource.RequestProperties) null);
    }

    @Deprecated
    public DefaultHttpDataSource(@Nullable String str, int i3, int i4, boolean z2, @Nullable HttpDataSource.RequestProperties requestProperties2) {
        this(str, i3, i4, z2, requestProperties2, (Predicate<String>) null, false);
    }

    private DefaultHttpDataSource(@Nullable String str, int i3, int i4, boolean z2, @Nullable HttpDataSource.RequestProperties requestProperties2, @Nullable Predicate<String> predicate, boolean z3) {
        super(true);
        this.userAgent = str;
        this.connectTimeoutMillis = i3;
        this.readTimeoutMillis = i4;
        this.allowCrossProtocolRedirects = z2;
        this.defaultRequestProperties = requestProperties2;
        this.contentTypePredicate = predicate;
        this.requestProperties = new HttpDataSource.RequestProperties();
        this.keepPostFor302Redirects = z3;
    }

    private HttpURLConnection makeConnection(URL url, int i3, @Nullable byte[] bArr, long j2, long j3, boolean z2, boolean z3, Map<String, String> map) throws IOException {
        HttpURLConnection openConnection = openConnection(url);
        openConnection.setConnectTimeout(this.connectTimeoutMillis);
        openConnection.setReadTimeout(this.readTimeoutMillis);
        HashMap hashMap = new HashMap();
        HttpDataSource.RequestProperties requestProperties2 = this.defaultRequestProperties;
        if (requestProperties2 != null) {
            hashMap.putAll(requestProperties2.getSnapshot());
        }
        hashMap.putAll(this.requestProperties.getSnapshot());
        hashMap.putAll(map);
        for (Map.Entry entry : hashMap.entrySet()) {
            openConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        String buildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(j2, j3);
        if (buildRangeRequestHeader != null) {
            openConnection.setRequestProperty(HttpHeaders.RANGE, buildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            openConnection.setRequestProperty(HttpHeaders.USER_AGENT, str);
        }
        openConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, z2 ? "gzip" : "identity");
        openConnection.setInstanceFollowRedirects(z3);
        openConnection.setDoOutput(bArr != null);
        openConnection.setRequestMethod(DataSpec.getStringForHttpMethod(i3));
        if (bArr != null) {
            openConnection.setFixedLengthStreamingMode(bArr.length);
            openConnection.connect();
            OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            openConnection.connect();
        }
        return openConnection;
    }
}
