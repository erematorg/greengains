package com.google.crypto.tink.util;

import com.adjust.sdk.Constants;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import org.joda.time.Instant;

public class KeysDownloader {
    /* access modifiers changed from: private */
    public static final Executor DEFAULT_BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
    /* access modifiers changed from: private */
    public static final NetHttpTransport DEFAULT_HTTP_TRANSPORT = new NetHttpTransport.Builder().build();
    private static final Pattern MAX_AGE_PATTERN = Pattern.compile("\\s*max-age\\s*=\\s*(\\d+)\\s*");
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Executor backgroundExecutor;
    @GuardedBy("instanceStateLock")
    private long cacheExpirationDurationInMillis;
    @GuardedBy("instanceStateLock")
    private String cachedData;
    @GuardedBy("instanceStateLock")
    private long cachedTimeInMillis;
    /* access modifiers changed from: private */
    public final Object fetchDataLock = new Object();
    private final HttpTransport httpTransport;
    /* access modifiers changed from: private */
    public final Object instanceStateLock = new Object();
    /* access modifiers changed from: private */
    @GuardedBy("instanceStateLock")
    public Runnable pendingRefreshRunnable;
    private final String url;

    public static class Builder {
        private Executor executor = KeysDownloader.DEFAULT_BACKGROUND_EXECUTOR;
        private HttpTransport httpTransport = KeysDownloader.DEFAULT_HTTP_TRANSPORT;
        private String url;

        public KeysDownloader build() {
            if (this.url != null) {
                return new KeysDownloader(this.executor, this.httpTransport, this.url);
            }
            throw new IllegalArgumentException("must provide a url with {#setUrl}");
        }

        @CanIgnoreReturnValue
        public Builder setExecutor(Executor executor2) {
            this.executor = executor2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setHttpTransport(HttpTransport httpTransport2) {
            this.httpTransport = httpTransport2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }
    }

    public KeysDownloader(Executor executor, HttpTransport httpTransport2, String str) {
        validate(str);
        this.backgroundExecutor = executor;
        this.httpTransport = httpTransport2;
        this.url = str;
        this.cachedTimeInMillis = Long.MIN_VALUE;
        this.cacheExpirationDurationInMillis = 0;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    @GuardedBy("fetchDataLock")
    @CanIgnoreReturnValue
    public String fetchAndCacheData() throws IOException {
        long currentTimeInMillis = getCurrentTimeInMillis();
        HttpResponse execute = this.httpTransport.createRequestFactory().buildGetRequest(new GenericUrl(this.url)).execute();
        if (execute.getStatusCode() == 200) {
            InputStream content = execute.getContent();
            try {
                String readerToString = readerToString(new InputStreamReader(content, UTF_8));
                content.close();
                synchronized (this.instanceStateLock) {
                    this.cachedTimeInMillis = currentTimeInMillis;
                    this.cacheExpirationDurationInMillis = getExpirationDurationInSeconds(execute.getHeaders()) * 1000;
                    this.cachedData = readerToString;
                }
                return readerToString;
            } catch (Throwable th) {
                content.close();
                throw th;
            }
        } else {
            throw new IOException("Unexpected status code = " + execute.getStatusCode());
        }
    }

    @GuardedBy("instanceStateLock")
    private boolean hasNonExpiredDataCached() {
        long currentTimeInMillis = getCurrentTimeInMillis();
        long j2 = this.cachedTimeInMillis;
        return j2 + this.cacheExpirationDurationInMillis > currentTimeInMillis && !((j2 > currentTimeInMillis ? 1 : (j2 == currentTimeInMillis ? 0 : -1)) > 0);
    }

    private Runnable newRefreshRunnable() {
        return new Runnable() {
            /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
                java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
                	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
                	at java.util.ArrayList.get(ArrayList.java:435)
                	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
                	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
                	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
                	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
                	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
                	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
                	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
                */
            public void run() {
                /*
                    r5 = this;
                    com.google.crypto.tink.util.KeysDownloader r0 = com.google.crypto.tink.util.KeysDownloader.this
                    java.lang.Object r0 = r0.fetchDataLock
                    monitor-enter(r0)
                    r1 = 0
                    com.google.crypto.tink.util.KeysDownloader r2 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ IOException -> 0x0046, all -> 0x002a }
                    java.lang.String unused = r2.fetchAndCacheData()     // Catch:{ IOException -> 0x0046, all -> 0x002a }
                    com.google.crypto.tink.util.KeysDownloader r2 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0028 }
                    java.lang.Object r2 = r2.instanceStateLock     // Catch:{ all -> 0x0028 }
                    monitor-enter(r2)     // Catch:{ all -> 0x0028 }
                    com.google.crypto.tink.util.KeysDownloader r3 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0022 }
                    java.lang.Runnable r3 = r3.pendingRefreshRunnable     // Catch:{ all -> 0x0022 }
                    if (r3 != r5) goto L_0x0024
                    com.google.crypto.tink.util.KeysDownloader r5 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0022 }
                    java.lang.Runnable unused = r5.pendingRefreshRunnable = r1     // Catch:{ all -> 0x0022 }
                    goto L_0x0024
                L_0x0022:
                    r5 = move-exception
                    goto L_0x0026
                L_0x0024:
                    monitor-exit(r2)     // Catch:{ all -> 0x0022 }
                    goto L_0x005e
                L_0x0026:
                    monitor-exit(r2)     // Catch:{ all -> 0x0022 }
                    throw r5     // Catch:{ all -> 0x0028 }
                L_0x0028:
                    r5 = move-exception
                    goto L_0x0062
                L_0x002a:
                    r2 = move-exception
                    com.google.crypto.tink.util.KeysDownloader r3 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0028 }
                    java.lang.Object r3 = r3.instanceStateLock     // Catch:{ all -> 0x0028 }
                    monitor-enter(r3)     // Catch:{ all -> 0x0028 }
                    com.google.crypto.tink.util.KeysDownloader r4 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0040 }
                    java.lang.Runnable r4 = r4.pendingRefreshRunnable     // Catch:{ all -> 0x0040 }
                    if (r4 != r5) goto L_0x0042
                    com.google.crypto.tink.util.KeysDownloader r5 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0040 }
                    java.lang.Runnable unused = r5.pendingRefreshRunnable = r1     // Catch:{ all -> 0x0040 }
                    goto L_0x0042
                L_0x0040:
                    r5 = move-exception
                    goto L_0x0044
                L_0x0042:
                    monitor-exit(r3)     // Catch:{ all -> 0x0040 }
                    throw r2     // Catch:{ all -> 0x0028 }
                L_0x0044:
                    monitor-exit(r3)     // Catch:{ all -> 0x0040 }
                    throw r5     // Catch:{ all -> 0x0028 }
                L_0x0046:
                    com.google.crypto.tink.util.KeysDownloader r2 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x0028 }
                    java.lang.Object r2 = r2.instanceStateLock     // Catch:{ all -> 0x0028 }
                    monitor-enter(r2)     // Catch:{ all -> 0x0028 }
                    com.google.crypto.tink.util.KeysDownloader r3 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x005b }
                    java.lang.Runnable r3 = r3.pendingRefreshRunnable     // Catch:{ all -> 0x005b }
                    if (r3 != r5) goto L_0x005d
                    com.google.crypto.tink.util.KeysDownloader r5 = com.google.crypto.tink.util.KeysDownloader.this     // Catch:{ all -> 0x005b }
                    java.lang.Runnable unused = r5.pendingRefreshRunnable = r1     // Catch:{ all -> 0x005b }
                    goto L_0x005d
                L_0x005b:
                    r5 = move-exception
                    goto L_0x0060
                L_0x005d:
                    monitor-exit(r2)     // Catch:{ all -> 0x005b }
                L_0x005e:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return
                L_0x0060:
                    monitor-exit(r2)     // Catch:{ all -> 0x005b }
                    throw r5     // Catch:{ all -> 0x0028 }
                L_0x0062:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    throw r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.util.KeysDownloader.AnonymousClass1.run():void");
            }
        };
    }

    private static String readerToString(Reader reader) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedReader.read();
            if (read == -1) {
                return sb.toString();
            }
            sb.append((char) read);
        }
    }

    @GuardedBy("instanceStateLock")
    private boolean shouldProactivelyRefreshDataInBackground() {
        return (this.cacheExpirationDurationInMillis / 2) + this.cachedTimeInMillis <= getCurrentTimeInMillis();
    }

    private static void validate(String str) {
        try {
            if (!new URL(str).getProtocol().toLowerCase(Locale.US).equals(Constants.SCHEME)) {
                throw new IllegalArgumentException("url must point to a HTTPS server");
            }
        } catch (MalformedURLException e3) {
            throw new IllegalArgumentException(e3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001a, code lost:
        r1 = r3.fetchDataLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = r3.instanceStateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x001f, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0024, code lost:
        if (hasNonExpiredDataCached() == false) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0026, code lost:
        r3 = r3.cachedData;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0028, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002a, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r3 = fetchAndCacheData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0034, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0035, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String download() throws java.io.IOException {
        /*
            r3 = this;
            java.lang.Object r0 = r3.instanceStateLock
            monitor-enter(r0)
            boolean r1 = r3.hasNonExpiredDataCached()     // Catch:{ all -> 0x0013 }
            if (r1 == 0) goto L_0x0019
            boolean r1 = r3.shouldProactivelyRefreshDataInBackground()     // Catch:{ all -> 0x0013 }
            if (r1 == 0) goto L_0x0015
            r3.refreshInBackground()     // Catch:{ all -> 0x0013 }
            goto L_0x0015
        L_0x0013:
            r3 = move-exception
            goto L_0x003a
        L_0x0015:
            java.lang.String r3 = r3.cachedData     // Catch:{ all -> 0x0013 }
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            return r3
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            java.lang.Object r1 = r3.fetchDataLock
            monitor-enter(r1)
            java.lang.Object r0 = r3.instanceStateLock     // Catch:{ all -> 0x002b }
            monitor-enter(r0)     // Catch:{ all -> 0x002b }
            boolean r2 = r3.hasNonExpiredDataCached()     // Catch:{ all -> 0x002d }
            if (r2 == 0) goto L_0x002f
            java.lang.String r3 = r3.cachedData     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            return r3
        L_0x002b:
            r3 = move-exception
            goto L_0x0038
        L_0x002d:
            r3 = move-exception
            goto L_0x0036
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.String r3 = r3.fetchAndCacheData()     // Catch:{ all -> 0x002b }
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            return r3
        L_0x0036:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r3     // Catch:{ all -> 0x002b }
        L_0x0038:
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            throw r3
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x0013 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.crypto.tink.util.KeysDownloader.download():java.lang.String");
    }

    public long getCurrentTimeInMillis() {
        return Instant.now().getMillis();
    }

    public long getExpirationDurationInSeconds(HttpHeaders httpHeaders) {
        long j2;
        if (httpHeaders.getCacheControl() != null) {
            String[] split = httpHeaders.getCacheControl().split(",");
            int length = split.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                Matcher matcher = MAX_AGE_PATTERN.matcher(split[i3]);
                if (matcher.matches()) {
                    j2 = Long.valueOf(matcher.group(1)).longValue();
                    break;
                }
                i3++;
            }
        }
        j2 = 0;
        if (httpHeaders.getAge() != null) {
            j2 -= httpHeaders.getAge().longValue();
        }
        return Math.max(0, j2);
    }

    public HttpTransport getHttpTransport() {
        return this.httpTransport;
    }

    public String getUrl() {
        return this.url;
    }

    public void refreshInBackground() {
        Runnable newRefreshRunnable = newRefreshRunnable();
        synchronized (this.instanceStateLock) {
            try {
                if (this.pendingRefreshRunnable == null) {
                    this.pendingRefreshRunnable = newRefreshRunnable;
                    try {
                        this.backgroundExecutor.execute(newRefreshRunnable);
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
            } finally {
                while (true) {
                }
            }
        }
    }
}
