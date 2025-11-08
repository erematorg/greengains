package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.DefaultUpload;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import okio.FileSystem;
import okio.Path;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"toUpload", "Lcom/apollographql/apollo3/api/Upload;", "Lokio/Path;", "contentType", "", "fileSystem", "Lokio/FileSystem;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class DefaultUploadKt {
    @NotNull
    public static final Upload toUpload(@NotNull Path path, @NotNull String str, @NotNull FileSystem fileSystem) {
        Intrinsics.checkNotNullParameter(path, "<this>");
        Intrinsics.checkNotNullParameter(str, "contentType");
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        DefaultUpload.Builder contentType = new DefaultUpload.Builder().content((Function1<? super BufferedSink, Unit>) new DefaultUploadKt$toUpload$1(fileSystem, path)).contentType(str);
        Long size = fileSystem.metadata(path).getSize();
        return contentType.contentLength(size != null ? size.longValue() : -1).build();
    }

    public static /* synthetic */ Upload toUpload$default(Path path, String str, FileSystem fileSystem, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            fileSystem = _systemFileSystemKt.getSystemFileSystem();
        }
        return toUpload(path, str, fileSystem);
    }
}
