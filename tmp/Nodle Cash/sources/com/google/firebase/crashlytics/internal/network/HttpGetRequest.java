package com.google.firebase.crashlytics.internal.network;

import android.support.v4.media.session.a;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpGetRequest {
    private static final int DEFAULT_TIMEOUT_MS = 10000;
    private static final String METHOD_GET = "GET";
    private static final int READ_BUFFER_SIZE = 8192;
    private final Map<String, String> headers = new HashMap();
    private final Map<String, String> queryParams;
    private final String url;

    public HttpGetRequest(String str, Map<String, String> map) {
        this.url = str;
        this.queryParams = map;
    }

    private String createParamsString(Map<String, String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        Map.Entry next = it.next();
        sb.append((String) next.getKey());
        sb.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
        sb.append(next.getValue() != null ? URLEncoder.encode((String) next.getValue(), "UTF-8") : "");
        while (it.hasNext()) {
            Map.Entry next2 = it.next();
            sb.append("&");
            sb.append((String) next2.getKey());
            sb.append(StickyVariantProvider.KEY_VALUE_DELIMITER);
            sb.append(next2.getValue() != null ? URLEncoder.encode((String) next2.getValue(), "UTF-8") : "");
        }
        return sb.toString();
    }

    private String createUrlWithParams(String str, Map<String, String> map) throws UnsupportedEncodingException {
        String createParamsString = createParamsString(map);
        if (createParamsString.isEmpty()) {
            return str;
        }
        if (!str.contains("?")) {
            return a.n(str, "?", createParamsString);
        }
        if (!str.endsWith("&")) {
            createParamsString = "&".concat(createParamsString);
        }
        return a.m(str, createParamsString);
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.crashlytics.internal.network.HttpResponse execute() throws java.io.IOException {
        /*
            r5 = this;
            java.lang.String r0 = "GET Request URL: "
            r1 = 0
            java.lang.String r2 = r5.url     // Catch:{ all -> 0x0081 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.queryParams     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = r5.createUrlWithParams(r2, r3)     // Catch:{ all -> 0x0081 }
            com.google.firebase.crashlytics.internal.Logger r3 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0081 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r4.<init>(r0)     // Catch:{ all -> 0x0081 }
            r4.append(r2)     // Catch:{ all -> 0x0081 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0081 }
            r3.v(r0)     // Catch:{ all -> 0x0081 }
            java.net.URL r0 = new java.net.URL     // Catch:{ all -> 0x0081 }
            r0.<init>(r2)     // Catch:{ all -> 0x0081 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ all -> 0x0081 }
            javax.net.ssl.HttpsURLConnection r0 = (javax.net.ssl.HttpsURLConnection) r0     // Catch:{ all -> 0x0081 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r0.setReadTimeout(r2)     // Catch:{ all -> 0x005c }
            r0.setConnectTimeout(r2)     // Catch:{ all -> 0x005c }
            java.lang.String r2 = "GET"
            r0.setRequestMethod(r2)     // Catch:{ all -> 0x005c }
            java.util.Map<java.lang.String, java.lang.String> r2 = r5.headers     // Catch:{ all -> 0x005c }
            java.util.Set r2 = r2.entrySet()     // Catch:{ all -> 0x005c }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x005c }
        L_0x0040:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x005e
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x005c }
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x005c }
            java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x005c }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x005c }
            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x005c }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x005c }
            r0.addRequestProperty(r4, r3)     // Catch:{ all -> 0x005c }
            goto L_0x0040
        L_0x005c:
            r5 = move-exception
            goto L_0x0083
        L_0x005e:
            r0.connect()     // Catch:{ all -> 0x005c }
            int r2 = r0.getResponseCode()     // Catch:{ all -> 0x005c }
            java.io.InputStream r3 = r0.getInputStream()     // Catch:{ all -> 0x005c }
            if (r3 == 0) goto L_0x0073
            java.lang.String r1 = r5.readStream(r3)     // Catch:{ all -> 0x0070 }
            goto L_0x0073
        L_0x0070:
            r5 = move-exception
            r1 = r3
            goto L_0x0083
        L_0x0073:
            if (r3 == 0) goto L_0x0078
            r3.close()
        L_0x0078:
            r0.disconnect()
            com.google.firebase.crashlytics.internal.network.HttpResponse r5 = new com.google.firebase.crashlytics.internal.network.HttpResponse
            r5.<init>(r2, r1)
            return r5
        L_0x0081:
            r5 = move-exception
            r0 = r1
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            r1.close()
        L_0x0088:
            if (r0 == 0) goto L_0x008d
            r0.disconnect()
        L_0x008d:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.network.HttpGetRequest.execute():com.google.firebase.crashlytics.internal.network.HttpResponse");
    }

    public HttpGetRequest header(String str, String str2) {
        this.headers.put(str, str2);
        return this;
    }

    public HttpGetRequest header(Map.Entry<String, String> entry) {
        return header(entry.getKey(), entry.getValue());
    }
}
