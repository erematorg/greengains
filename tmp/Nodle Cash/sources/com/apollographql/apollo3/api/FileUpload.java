package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.DefaultUpload;
import java.io.File;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSink;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0003¨\u0006\f"}, d2 = {"create", "Lcom/apollographql/apollo3/api/Upload;", "mimetype", "", "filePath", "content", "Lcom/apollographql/apollo3/api/DefaultUpload$Builder;", "file", "Ljava/io/File;", "toUpload", "Lcom/apollographql/apollo3/api/DefaultUpload;", "contentType", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "FileUpload")
public final class FileUpload {
    @NotNull
    @Deprecated(message = "Use File.toUpload() instead")
    public static final DefaultUpload.Builder content(@NotNull DefaultUpload.Builder builder, @NotNull File file) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        Intrinsics.checkNotNullParameter(file, StringLookupFactory.KEY_FILE);
        return builder.content((Function1<? super BufferedSink, Unit>) new FileUpload$content$1(file)).contentLength(file.length());
    }

    @NotNull
    @Deprecated(message = "This is a helper function to help migrating to 3.x and will be removed in a future version", replaceWith = @ReplaceWith(expression = "File(filePath).toUpload(mimetype)", imports = {"java.io.File"}))
    public static final Upload create(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkNotNullParameter(str, "mimetype");
        Intrinsics.checkNotNullParameter(str2, "filePath");
        return toUpload(new File(str2), str);
    }

    @NotNull
    public static final DefaultUpload toUpload(@NotNull File file, @NotNull String str) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(str, "contentType");
        DefaultUpload.Builder contentType = new DefaultUpload.Builder().content((Function1<? super BufferedSink, Unit>) new FileUpload$toUpload$1(file)).contentLength(file.length()).contentType(str);
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        return contentType.fileName(name).build();
    }
}
