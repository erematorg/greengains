package com.android.volley.toolbox;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.ClientError;
import com.android.volley.Header;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.appsamurai.storyly.exoplayer2.common.C;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

final class NetworkUtility {
    private static final int SLOW_REQUEST_THRESHOLD_MS = 3000;

    public static class RetryInfo {
        /* access modifiers changed from: private */
        public final VolleyError errorToRetry;
        /* access modifiers changed from: private */
        public final String logPrefix;

        private RetryInfo(String str, VolleyError volleyError) {
            this.logPrefix = str;
            this.errorToRetry = volleyError;
        }
    }

    private NetworkUtility() {
    }

    public static void attemptRetryOnException(Request<?> request, RetryInfo retryInfo) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(retryInfo.errorToRetry);
            String access$100 = retryInfo.logPrefix;
            request.addMarker(access$100 + "-retry [timeout=" + timeoutMs + "]");
        } catch (VolleyError e3) {
            String access$1002 = retryInfo.logPrefix;
            request.addMarker(access$1002 + "-timeout-giveup [timeout=" + timeoutMs + "]");
            throw e3;
        }
    }

    public static NetworkResponse getNotModifiedNetworkResponse(Request<?> request, long j2, List<Header> list) {
        Cache.Entry cacheEntry = request.getCacheEntry();
        if (cacheEntry == null) {
            return new NetworkResponse(304, (byte[]) null, true, j2, list);
        }
        return new NetworkResponse(304, cacheEntry.data, true, j2, HttpHeaderParser.combineHeaders(list, cacheEntry));
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033 A[SYNTHETIC, Splitter:B:19:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] inputStreamToBytes(java.io.InputStream r5, int r6, com.android.volley.toolbox.ByteArrayPool r7) throws java.io.IOException {
        /*
            java.lang.String r0 = "Error occurred when closing InputStream"
            com.android.volley.toolbox.PoolingByteArrayOutputStream r1 = new com.android.volley.toolbox.PoolingByteArrayOutputStream
            r1.<init>(r7, r6)
            r6 = 1024(0x400, float:1.435E-42)
            r2 = 0
            byte[] r6 = r7.getBuf(r6)     // Catch:{ all -> 0x002f }
        L_0x000e:
            int r3 = r5.read(r6)     // Catch:{ all -> 0x0019 }
            r4 = -1
            if (r3 == r4) goto L_0x001b
            r1.write(r6, r2, r3)     // Catch:{ all -> 0x0019 }
            goto L_0x000e
        L_0x0019:
            r3 = move-exception
            goto L_0x0031
        L_0x001b:
            byte[] r3 = r1.toByteArray()     // Catch:{ all -> 0x0019 }
            r5.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0028
        L_0x0023:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            com.android.volley.VolleyLog.v(r0, r5)
        L_0x0028:
            r7.returnBuf(r6)
            r1.close()
            return r3
        L_0x002f:
            r3 = move-exception
            r6 = 0
        L_0x0031:
            if (r5 == 0) goto L_0x003c
            r5.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003c
        L_0x0037:
            java.lang.Object[] r5 = new java.lang.Object[r2]
            com.android.volley.VolleyLog.v(r0, r5)
        L_0x003c:
            r7.returnBuf(r6)
            r1.close()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.toolbox.NetworkUtility.inputStreamToBytes(java.io.InputStream, int, com.android.volley.toolbox.ByteArrayPool):byte[]");
    }

    public static void logSlowRequests(long j2, Request<?> request, byte[] bArr, int i3) {
        if (VolleyLog.DEBUG || j2 > C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS) {
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", request, Long.valueOf(j2), bArr != null ? Integer.valueOf(bArr.length) : AbstractJsonLexerKt.NULL, Integer.valueOf(i3), Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount()));
        }
    }

    public static RetryInfo shouldRetryException(Request<?> request, IOException iOException, long j2, @Nullable HttpResponse httpResponse, @Nullable byte[] bArr) throws VolleyError {
        if (iOException instanceof SocketTimeoutException) {
            return new RetryInfo("socket", new TimeoutError());
        }
        if (iOException instanceof MalformedURLException) {
            throw new RuntimeException("Bad URL " + request.getUrl(), iOException);
        } else if (httpResponse != null) {
            int statusCode = httpResponse.getStatusCode();
            VolleyLog.e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
            if (bArr == null) {
                return new RetryInfo("network", new NetworkError());
            }
            int i3 = statusCode;
            byte[] bArr2 = bArr;
            NetworkResponse networkResponse = new NetworkResponse(i3, bArr2, false, SystemClock.elapsedRealtime() - j2, httpResponse.getHeaders());
            if (statusCode == 401 || statusCode == 403) {
                return new RetryInfo("auth", new AuthFailureError(networkResponse));
            }
            if (statusCode >= 400 && statusCode <= 499) {
                throw new ClientError(networkResponse);
            } else if (statusCode >= 500 && statusCode <= 599 && request.shouldRetryServerErrors()) {
                return new RetryInfo("server", new ServerError(networkResponse));
            } else {
                throw new ServerError(networkResponse);
            }
        } else if (request.shouldRetryConnectionErrors()) {
            return new RetryInfo("connection", new NoConnectionError());
        } else {
            throw new NoConnectionError(iOException);
        }
    }
}
