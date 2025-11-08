package com.phrase.android.sdk.repo;

import android.content.Context;
import java.io.File;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007¨\u0006\u000f"}, d2 = {"Lcom/phrase/android/sdk/repo/PhraseDiskCache;", "", "", "localeHash", "Lcom/phrase/android/sdk/repo/PhraseData;", "get", "getOrNull", "Ljava/io/InputStream;", "inputStream", "", "put", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class PhraseDiskCache {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f7283a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final Lazy f7284b = LazyKt.lazy(b.f7289a);
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReentrantReadWriteLock f7285c = new ReentrantReadWriteLock();
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public Pair<String, PhraseData> f7286d = new Pair<>(null, null);
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final Lazy f7287e = LazyKt.lazy(new a(this));

    public static final class a extends Lambda implements Function0<File> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ PhraseDiskCache f7288a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(PhraseDiskCache phraseDiskCache) {
            super(0);
            this.f7288a = phraseDiskCache;
        }

        public final Object invoke() {
            File file = new File(this.f7288a.f7283a.getCacheDir(), ".phrase_cache");
            file.mkdirs();
            return file;
        }
    }

    public static final class b extends Lambda implements Function0<PhraseXmlParser> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f7289a = new b();

        public b() {
            super(0);
        }

        public final Object invoke() {
            return new PhraseXmlParser();
        }
    }

    public PhraseDiskCache(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f7283a = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0061, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        throw r7;
     */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.phrase.android.sdk.repo.PhraseData get(@org.jetbrains.annotations.NotNull java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "localeHash"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.f7285c
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r0 = r0.readLock()
            r0.lock()
            kotlin.Pair<java.lang.String, com.phrase.android.sdk.repo.PhraseData> r1 = r6.f7286d     // Catch:{ all -> 0x005d }
            java.lang.Object r1 = r1.getFirst()     // Catch:{ all -> 0x005d }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7)     // Catch:{ all -> 0x005d }
            if (r1 != 0) goto L_0x0066
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x005d }
            java.io.File r2 = new java.io.File     // Catch:{ all -> 0x005d }
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x005d }
            android.content.Context r4 = r6.f7283a     // Catch:{ all -> 0x005d }
            java.io.File r4 = r4.getFilesDir()     // Catch:{ all -> 0x005d }
            java.lang.String r5 = ".phrase_cache"
            r3.<init>(r4, r5)     // Catch:{ all -> 0x005d }
            r3.mkdirs()     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r4.<init>()     // Catch:{ all -> 0x005d }
            r4.append(r7)     // Catch:{ all -> 0x005d }
            java.lang.String r5 = ".xml"
            r4.append(r5)     // Catch:{ all -> 0x005d }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x005d }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x005d }
            r1.<init>(r2)     // Catch:{ all -> 0x005d }
            kotlin.Lazy r2 = r6.f7284b     // Catch:{ all -> 0x005f }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x005f }
            com.phrase.android.sdk.repo.PhraseXmlParser r2 = (com.phrase.android.sdk.repo.PhraseXmlParser) r2     // Catch:{ all -> 0x005f }
            com.phrase.android.sdk.repo.PhraseData r2 = r2.parse((java.io.InputStream) r1)     // Catch:{ all -> 0x005f }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r3)     // Catch:{ all -> 0x005d }
            kotlin.Pair r1 = new kotlin.Pair     // Catch:{ all -> 0x005d }
            r1.<init>(r7, r2)     // Catch:{ all -> 0x005d }
            r6.f7286d = r1     // Catch:{ all -> 0x005d }
            goto L_0x0066
        L_0x005d:
            r6 = move-exception
            goto L_0x0077
        L_0x005f:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r6)     // Catch:{ all -> 0x005d }
            throw r7     // Catch:{ all -> 0x005d }
        L_0x0066:
            kotlin.Pair<java.lang.String, com.phrase.android.sdk.repo.PhraseData> r6 = r6.f7286d     // Catch:{ all -> 0x005d }
            java.lang.Object r6 = r6.getSecond()     // Catch:{ all -> 0x005d }
            java.lang.String r7 = "null cannot be cast to non-null type com.phrase.android.sdk.repo.PhraseData"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r7)     // Catch:{ all -> 0x005d }
            com.phrase.android.sdk.repo.PhraseData r6 = (com.phrase.android.sdk.repo.PhraseData) r6     // Catch:{ all -> 0x005d }
            r0.unlock()
            return r6
        L_0x0077:
            r0.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.phrase.android.sdk.repo.PhraseDiskCache.get(java.lang.String):com.phrase.android.sdk.repo.PhraseData");
    }

    @Nullable
    public final PhraseData getOrNull(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "localeHash");
        try {
            return get(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void put(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.io.InputStream r12) {
        /*
            r10 = this;
            java.lang.String r0 = ".phrase_cache"
            java.lang.String r1 = "localeHash"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r1)
            java.lang.String r1 = "inputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r1)
            kotlin.Lazy r1 = r10.f7287e
            java.lang.Object r1 = r1.getValue()
            java.io.File r1 = (java.io.File) r1
            java.lang.String r2 = "tmp-locale-"
            java.lang.String r3 = ".xml"
            java.io.File r1 = java.io.File.createTempFile(r2, r3, r1)
            java.io.FileOutputStream r2 = new java.io.FileOutputStream
            java.lang.String r4 = "tmpFile"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r2.<init>(r1)
            r4 = 0
            r5 = 2
            r6 = 0
            kotlin.io.ByteStreamsKt.copyTo$default(r12, r2, r4, r5, r6)
            java.util.concurrent.locks.ReentrantReadWriteLock r12 = r10.f7285c
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r2 = r12.readLock()
            int r5 = r12.getWriteHoldCount()
            if (r5 != 0) goto L_0x003d
            int r5 = r12.getReadHoldCount()
            goto L_0x003e
        L_0x003d:
            r5 = r4
        L_0x003e:
            r7 = r4
        L_0x003f:
            if (r7 >= r5) goto L_0x0047
            r2.unlock()
            int r7 = r7 + 1
            goto L_0x003f
        L_0x0047:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r12 = r12.writeLock()
            r12.lock()
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x00be }
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x00be }
            android.content.Context r9 = r10.f7283a     // Catch:{ all -> 0x00be }
            java.io.File r9 = r9.getFilesDir()     // Catch:{ all -> 0x00be }
            r8.<init>(r9, r0)     // Catch:{ all -> 0x00be }
            r8.mkdirs()     // Catch:{ all -> 0x00be }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
            r9.<init>()     // Catch:{ all -> 0x00be }
            r9.append(r11)     // Catch:{ all -> 0x00be }
            r9.append(r3)     // Catch:{ all -> 0x00be }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00be }
            r7.<init>(r8, r9)     // Catch:{ all -> 0x00be }
            r1.renameTo(r7)     // Catch:{ all -> 0x00be }
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x00be }
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x00be }
            java.io.File r8 = new java.io.File     // Catch:{ all -> 0x00be }
            android.content.Context r9 = r10.f7283a     // Catch:{ all -> 0x00be }
            java.io.File r9 = r9.getFilesDir()     // Catch:{ all -> 0x00be }
            r8.<init>(r9, r0)     // Catch:{ all -> 0x00be }
            r8.mkdirs()     // Catch:{ all -> 0x00be }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00be }
            r0.<init>()     // Catch:{ all -> 0x00be }
            r0.append(r11)     // Catch:{ all -> 0x00be }
            r0.append(r3)     // Catch:{ all -> 0x00be }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00be }
            r7.<init>(r8, r0)     // Catch:{ all -> 0x00be }
            r1.<init>(r7)     // Catch:{ all -> 0x00be }
            kotlin.Lazy r0 = r10.f7284b     // Catch:{ all -> 0x00c0 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ all -> 0x00c0 }
            com.phrase.android.sdk.repo.PhraseXmlParser r0 = (com.phrase.android.sdk.repo.PhraseXmlParser) r0     // Catch:{ all -> 0x00c0 }
            com.phrase.android.sdk.repo.PhraseData r0 = r0.parse((java.io.InputStream) r1)     // Catch:{ all -> 0x00c0 }
            kotlin.io.CloseableKt.closeFinally(r1, r6)     // Catch:{ all -> 0x00be }
            kotlin.Pair r1 = new kotlin.Pair     // Catch:{ all -> 0x00be }
            r1.<init>(r11, r0)     // Catch:{ all -> 0x00be }
            r10.f7286d = r1     // Catch:{ all -> 0x00be }
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00be }
        L_0x00b2:
            if (r4 >= r5) goto L_0x00ba
            r2.lock()
            int r4 = r4 + 1
            goto L_0x00b2
        L_0x00ba:
            r12.unlock()
            return
        L_0x00be:
            r10 = move-exception
            goto L_0x00c7
        L_0x00c0:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r11 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r10)     // Catch:{ all -> 0x00be }
            throw r11     // Catch:{ all -> 0x00be }
        L_0x00c7:
            if (r4 >= r5) goto L_0x00cf
            r2.lock()
            int r4 = r4 + 1
            goto L_0x00c7
        L_0x00cf:
            r12.unlock()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.phrase.android.sdk.repo.PhraseDiskCache.put(java.lang.String, java.io.InputStream):void");
    }
}
