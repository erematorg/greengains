package com.apollographql.apollo3.api;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;
import okio.FileHandle;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nDefaultUpload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultUpload.kt\ncom/apollographql/apollo3/api/DefaultUploadKt$toUpload$1\n+ 2 Okio.kt\nokio/Okio__OkioKt\n*L\n1#1,122:1\n52#2,18:123\n*S KotlinDebug\n*F\n+ 1 DefaultUpload.kt\ncom/apollographql/apollo3/api/DefaultUploadKt$toUpload$1\n*L\n114#1:123,18\n*E\n"})
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sink", "Lokio/BufferedSink;", "invoke"}, k = 3, mv = {1, 5, 1}, xi = 48)
public final class DefaultUploadKt$toUpload$1 extends Lambda implements Function1<BufferedSink, Unit> {
    final /* synthetic */ FileSystem $fileSystem;
    final /* synthetic */ Path $this_toUpload;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultUploadKt$toUpload$1(FileSystem fileSystem, Path path) {
        super(1);
        this.$fileSystem = fileSystem;
        this.$this_toUpload = path;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BufferedSink) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull BufferedSink bufferedSink) {
        Unit unit;
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        FileHandle openReadOnly = this.$fileSystem.openReadOnly(this.$this_toUpload);
        Throwable th = null;
        try {
            bufferedSink.writeAll(Okio.buffer(FileHandle.source$default(openReadOnly, 0, 1, (Object) null)));
            unit = Unit.INSTANCE;
        } catch (Throwable th2) {
            th = th2;
            unit = null;
        }
        if (openReadOnly != null) {
            try {
                openReadOnly.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                } else {
                    ExceptionsKt.addSuppressed(th, th3);
                }
            }
        }
        if (th == null) {
            Intrinsics.checkNotNull(unit);
            return;
        }
        throw th;
    }
}
