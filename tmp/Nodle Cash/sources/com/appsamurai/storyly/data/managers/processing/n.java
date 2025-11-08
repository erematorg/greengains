package com.appsamurai.storyly.data.managers.processing;

import android.content.Context;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.signature.SignatureVisitor;

public final class n {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4028a;

    public n(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4028a = context;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(java.lang.String r2, java.lang.String r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r1 = r1.f4028a     // Catch:{ Exception -> 0x0032 }
            java.io.FileOutputStream r1 = r1.openFileOutput(r2, r0)     // Catch:{ Exception -> 0x0032 }
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8     // Catch:{ all -> 0x0022 }
            if (r3 == 0) goto L_0x0024
            byte[] r2 = r3.getBytes(r2)     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "(this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ all -> 0x0022 }
            r1.write(r2)     // Catch:{ all -> 0x0022 }
            r1.flush()     // Catch:{ all -> 0x0022 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0022 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ Exception -> 0x0032 }
            r0 = 1
            goto L_0x0032
        L_0x0022:
            r2 = move-exception
            goto L_0x002c
        L_0x0024:
            java.lang.NullPointerException r2 = new java.lang.NullPointerException     // Catch:{ all -> 0x0022 }
            java.lang.String r3 = "null cannot be cast to non-null type java.lang.String"
            r2.<init>(r3)     // Catch:{ all -> 0x0022 }
            throw r2     // Catch:{ all -> 0x0022 }
        L_0x002c:
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r2)     // Catch:{ Exception -> 0x0032 }
            throw r3     // Catch:{ Exception -> 0x0032 }
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.n.a(java.lang.String, java.lang.String):boolean");
    }

    public final String b(String str) {
        return "stryly-local-cache-" + a(str) + SignatureVisitor.SUPER + StringsKt.takeLast(str, 8);
    }

    public final String c(String str) {
        return "stryly-etag-cache-" + a(str) + SignatureVisitor.SUPER + StringsKt.takeLast(str, 8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String d(java.lang.String r5) {
        /*
            r4 = this;
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r4.f4028a
            java.io.File r1 = r1.getFilesDir()
            r0.<init>(r1, r5)
            boolean r1 = r0.exists()
            r2 = 0
            if (r1 != 0) goto L_0x0013
            return r2
        L_0x0013:
            android.content.Context r4 = r4.f4028a     // Catch:{ Exception -> 0x003f }
            java.io.FileInputStream r4 = r4.openFileInput(r5)     // Catch:{ Exception -> 0x003f }
            java.lang.String r5 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ all -> 0x0038 }
            java.nio.charset.Charset r5 = kotlin.text.Charsets.UTF_8     // Catch:{ all -> 0x0038 }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ all -> 0x0038 }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x0038 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ all -> 0x0038 }
            r3 = 8192(0x2000, float:1.14794E-41)
            r5.<init>(r1, r3)     // Catch:{ all -> 0x0038 }
            java.lang.String r5 = kotlin.io.TextStreamsKt.readText(r5)     // Catch:{ all -> 0x0038 }
            kotlin.io.CloseableKt.closeFinally(r4, r2)     // Catch:{ Exception -> 0x003f }
            r0.delete()     // Catch:{ Exception -> 0x0037 }
            return r5
        L_0x0037:
            return r2
        L_0x0038:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x003a }
        L_0x003a:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r5)     // Catch:{ Exception -> 0x003f }
            throw r0     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.data.managers.processing.n.d(java.lang.String):java.lang.String");
    }

    public final String a(String str) {
        MessageDigest instance = MessageDigest.getInstance("MD5");
        Charset charset = Charsets.UTF_8;
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            String bigInteger = new BigInteger(1, instance.digest(bytes)).toString(16);
            Intrinsics.checkNotNullExpressionValue(bigInteger, "BigInteger(1, md.digest(…yteArray())).toString(16)");
            return StringsKt__StringsKt.padStart(bigInteger, 32, '0');
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}
