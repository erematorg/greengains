package com.apollographql.apollo3.api;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;

@SourceDebugExtension({"SMAP\nFileUpload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileUpload.kt\ncom/apollographql/apollo3/api/FileUpload$content$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,42:1\n1#2:43\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sink", "Lokio/BufferedSink;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class FileUpload$content$1 extends Lambda implements Function1<BufferedSink, Unit> {
    final /* synthetic */ File $file;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileUpload$content$1(File file) {
        super(1);
        this.$file = file;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BufferedSink) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(@org.jetbrains.annotations.NotNull okio.BufferedSink r2) {
        /*
            r1 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.io.File r1 = r1.$file
            okio.Source r1 = okio.Okio.source((java.io.File) r1)
            okio.BufferedSource r1 = okio.Okio.buffer((okio.Source) r1)
            r2.writeAll(r1)     // Catch:{ all -> 0x0019 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0019 }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            return
        L_0x0019:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001b }
        L_0x001b:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.api.FileUpload$content$1.invoke(okio.BufferedSink):void");
    }
}
