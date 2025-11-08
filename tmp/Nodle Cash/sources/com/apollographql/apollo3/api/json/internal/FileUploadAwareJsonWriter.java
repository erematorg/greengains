package com.apollographql.apollo3.api.json.internal;

import com.apollographql.apollo3.api.Upload;
import com.apollographql.apollo3.api.json.JsonNumber;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\u0000H\u0016J\b\u0010\f\u001a\u00020\u0000H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0010J\b\u0010\u0011\u001a\u00020\u0000H\u0016J\b\u0010\u0012\u001a\u00020\u0000H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\u0010\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0005H\u0016J\b\u0010\u0015\u001a\u00020\u0000H\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\nH\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0018H\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0019H\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u001aH\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u001bH\u0016J\u0010\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0005H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/apollographql/apollo3/api/json/internal/FileUploadAwareJsonWriter;", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "wrappedWriter", "(Lcom/apollographql/apollo3/api/json/JsonWriter;)V", "path", "", "getPath", "()Ljava/lang/String;", "uploads", "", "Lcom/apollographql/apollo3/api/Upload;", "beginArray", "beginObject", "close", "", "collectedUploads", "", "endArray", "endObject", "flush", "name", "nullValue", "value", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "", "", "", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class FileUploadAwareJsonWriter implements JsonWriter {
    @NotNull
    private final Map<String, Upload> uploads = new LinkedHashMap();
    @NotNull
    private final JsonWriter wrappedWriter;

    public FileUploadAwareJsonWriter(@NotNull JsonWriter jsonWriter) {
        Intrinsics.checkNotNullParameter(jsonWriter, "wrappedWriter");
        this.wrappedWriter = jsonWriter;
    }

    public void close() {
        this.wrappedWriter.close();
    }

    @NotNull
    public final Map<String, Upload> collectedUploads() {
        return this.uploads;
    }

    public void flush() {
        this.wrappedWriter.flush();
    }

    @NotNull
    public String getPath() {
        return this.wrappedWriter.getPath();
    }

    @NotNull
    public FileUploadAwareJsonWriter beginArray() {
        this.wrappedWriter.beginArray();
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter beginObject() {
        this.wrappedWriter.beginObject();
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter endArray() {
        this.wrappedWriter.endArray();
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter endObject() {
        this.wrappedWriter.endObject();
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter name(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.wrappedWriter.name(str);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter nullValue() {
        this.wrappedWriter.nullValue();
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.wrappedWriter.value(str);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(boolean z2) {
        this.wrappedWriter.value(z2);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(double d2) {
        this.wrappedWriter.value(d2);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(int i3) {
        this.wrappedWriter.value(i3);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(long j2) {
        this.wrappedWriter.value(j2);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(@NotNull JsonNumber jsonNumber) {
        Intrinsics.checkNotNullParameter(jsonNumber, "value");
        this.wrappedWriter.value(jsonNumber);
        return this;
    }

    @NotNull
    public FileUploadAwareJsonWriter value(@NotNull Upload upload) {
        Intrinsics.checkNotNullParameter(upload, "value");
        this.uploads.put(this.wrappedWriter.getPath(), upload);
        this.wrappedWriter.nullValue();
        return this;
    }
}
