package com.phrase.android.sdk.repo;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015JW\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0001¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseApi;", "", "", "locale", "localeHash", "uuid", "sdkVersion", "lastUpdate", "currentVersion", "appVersion", "", "timeout", "Lcom/phrase/android/sdk/repo/PhraseApiResult;", "fetchLocale$sdk_release", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/phrase/android/sdk/repo/PhraseApiResult;", "fetchLocale", "distribution", "environment", "Lcom/phrase/android/sdk/repo/PhraseDiskCache;", "diskCache", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/phrase/android/sdk/repo/PhraseDiskCache;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseApi {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final String f7272a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final String f7273b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final PhraseDiskCache f7274c;

    public PhraseApi(@NotNull String str, @NotNull String str2, @NotNull PhraseDiskCache phraseDiskCache) {
        Intrinsics.checkNotNullParameter(str, "distribution");
        Intrinsics.checkNotNullParameter(str2, "environment");
        Intrinsics.checkNotNullParameter(phraseDiskCache, "diskCache");
        this.f7272a = str;
        this.f7273b = str2;
        this.f7274c = phraseDiskCache;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0116, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x011a, code lost:
        throw r7;
     */
    @org.jetbrains.annotations.NotNull
    @androidx.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.phrase.android.sdk.repo.PhraseApiResult fetchLocale$sdk_release(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull java.lang.String r7, @org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.Nullable java.lang.String r9, @org.jetbrains.annotations.Nullable java.lang.String r10, @org.jetbrains.annotations.Nullable java.lang.String r11, @org.jetbrains.annotations.Nullable java.lang.Integer r12) {
        /*
            r4 = this;
            java.lang.String r0 = "Could not update translations. Got status: "
            java.lang.String r1 = "locale"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r1)
            java.lang.String r1 = "localeHash"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            java.lang.String r1 = "uuid"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "sdkVersion"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            java.lang.String r2 = "client"
            java.lang.String r3 = "android"
            r1.appendQueryParameter(r2, r3)
            java.lang.String r2 = "unique_identifier"
            r1.appendQueryParameter(r2, r7)
            java.lang.String r7 = "sdk_version"
            r1.appendQueryParameter(r7, r8)
            if (r9 == 0) goto L_0x0033
            java.lang.String r7 = "last_update"
            r1.appendQueryParameter(r7, r9)
        L_0x0033:
            if (r10 == 0) goto L_0x003a
            java.lang.String r7 = "current_version"
            r1.appendQueryParameter(r7, r10)
        L_0x003a:
            if (r11 == 0) goto L_0x0041
            java.lang.String r7 = "app_version"
            r1.appendQueryParameter(r7, r11)
        L_0x0041:
            android.net.Uri r7 = r1.build()
            java.lang.String r7 = r7.getEncodedQuery()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = com.phrase.android.sdk.Phrase.getHost()
            r8.append(r9)
            r9 = 47
            r8.append(r9)
            java.lang.String r10 = r4.f7272a
            r8.append(r10)
            r8.append(r9)
            java.lang.String r10 = r4.f7273b
            r8.append(r10)
            r8.append(r9)
            r8.append(r5)
            java.lang.String r5 = "/xml?"
            r8.append(r5)
            r8.append(r7)
            java.lang.String r5 = r8.toString()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Request URL: "
            r7.<init>(r8)
            r7.append(r5)
            java.lang.String r7 = r7.toString()
            com.phrase.android.sdk.UtilsKt.phraseLog(r7)
            java.net.URL r7 = new java.net.URL
            r7.<init>(r5)
            java.net.URLConnection r5 = r7.openConnection()
            java.lang.String r7 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r7)
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5
            java.lang.String r7 = "GET"
            r5.setRequestMethod(r7)
            r7 = 1
            r5.setInstanceFollowRedirects(r7)
            r7 = 10000(0x2710, float:1.4013E-41)
            if (r12 == 0) goto L_0x00ac
            int r8 = r12.intValue()
            goto L_0x00ad
        L_0x00ac:
            r8 = r7
        L_0x00ad:
            r5.setConnectTimeout(r8)
            if (r12 == 0) goto L_0x00b6
            int r7 = r12.intValue()
        L_0x00b6:
            r5.setReadTimeout(r7)
            int r7 = r5.getResponseCode()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 == r8) goto L_0x011b
            r4 = 304(0x130, float:4.26E-43)
            if (r7 != r4) goto L_0x00d0
            java.lang.String r4 = "Translations already up to date"
            com.phrase.android.sdk.UtilsKt.phraseLog(r4)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            com.phrase.android.sdk.repo.PhraseApiResult$NotModified r4 = com.phrase.android.sdk.repo.PhraseApiResult.NotModified.INSTANCE     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r5.disconnect()
            return r4
        L_0x00d0:
            java.io.InputStream r4 = r5.getErrorStream()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r6 = "connection.errorStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.nio.charset.Charset r6 = kotlin.text.Charsets.UTF_8     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r7.<init>(r4, r6)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r6 = 8192(0x2000, float:1.14794E-41)
            r4.<init>(r7, r6)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r6 = kotlin.io.TextStreamsKt.readText(r4)     // Catch:{ all -> 0x0114 }
            r7 = 0
            kotlin.io.CloseableKt.closeFinally(r4, r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.<init>(r0)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            int r7 = r5.getResponseCode()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.append(r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r7 = ": "
            r4.append(r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.append(r6)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r4 = r4.toString()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            com.phrase.android.sdk.UtilsKt.phraseLog(r4)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            com.phrase.android.sdk.FailedUpdateException r4 = new com.phrase.android.sdk.FailedUpdateException     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.<init>()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            throw r4     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
        L_0x0110:
            r4 = move-exception
            goto L_0x0152
        L_0x0112:
            r4 = move-exception
            goto L_0x0160
        L_0x0114:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0116 }
        L_0x0116:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r6)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            throw r7     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
        L_0x011b:
            java.net.URL r7 = r5.getURL()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r7 = r7.toString()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r8 = "connection.url.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            android.net.UrlQuerySanitizer r8 = new android.net.UrlQuerySanitizer     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r8.<init>(r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r7 = "version"
            java.lang.String r7 = r8.getValue(r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            if (r7 == 0) goto L_0x014c
            com.phrase.android.sdk.repo.PhraseDiskCache r4 = r4.f7274c     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.io.InputStream r8 = r5.getInputStream()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            java.lang.String r9 = "connection.inputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.put(r6, r8)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            com.phrase.android.sdk.repo.PhraseApiResult$Data r4 = new com.phrase.android.sdk.repo.PhraseApiResult$Data     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.<init>(r7)     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r5.disconnect()
            return r4
        L_0x014c:
            com.phrase.android.sdk.MissingVersionException r4 = new com.phrase.android.sdk.MissingVersionException     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            r4.<init>()     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
            throw r4     // Catch:{ UnknownHostException -> 0x0112, all -> 0x0110 }
        L_0x0152:
            com.phrase.android.sdk.UtilsKt.phraseLog(r4)     // Catch:{ all -> 0x015e }
            com.phrase.android.sdk.repo.PhraseApiResult$Error r6 = new com.phrase.android.sdk.repo.PhraseApiResult$Error     // Catch:{ all -> 0x015e }
            r6.<init>(r4)     // Catch:{ all -> 0x015e }
            r5.disconnect()
            return r6
        L_0x015e:
            r4 = move-exception
            goto L_0x016e
        L_0x0160:
            java.lang.String r6 = "No Internet connection"
            com.phrase.android.sdk.UtilsKt.phraseLog(r6)     // Catch:{ all -> 0x015e }
            com.phrase.android.sdk.repo.PhraseApiResult$Error r6 = new com.phrase.android.sdk.repo.PhraseApiResult$Error     // Catch:{ all -> 0x015e }
            r6.<init>(r4)     // Catch:{ all -> 0x015e }
            r5.disconnect()
            return r6
        L_0x016e:
            r5.disconnect()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.phrase.android.sdk.repo.PhraseApi.fetchLocale$sdk_release(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer):com.phrase.android.sdk.repo.PhraseApiResult");
    }
}
