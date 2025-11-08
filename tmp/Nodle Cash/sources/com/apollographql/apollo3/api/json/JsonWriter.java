package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.Upload;
import java.io.Closeable;
import java.io.IOException;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0007\u001a\u00020\u0000H&J\b\u0010\b\u001a\u00020\u0000H&J\b\u0010\t\u001a\u00020\u0000H&J\b\u0010\n\u001a\u00020\u0000H&J\b\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0004H&J\b\u0010\u000e\u001a\u00020\u0000H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0011H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0012H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0013H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0014H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0015H&J\u0010\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0004H&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo3/api/json/JsonWriter;", "Ljava/io/Closeable;", "Lokio/Closeable;", "path", "", "getPath", "()Ljava/lang/String;", "beginArray", "beginObject", "endArray", "endObject", "flush", "", "name", "nullValue", "value", "Lcom/apollographql/apollo3/api/Upload;", "Lcom/apollographql/apollo3/api/json/JsonNumber;", "", "", "", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public interface JsonWriter extends Closeable {
    @NotNull
    JsonWriter beginArray() throws IOException;

    @NotNull
    JsonWriter beginObject() throws IOException;

    @NotNull
    JsonWriter endArray() throws IOException;

    @NotNull
    JsonWriter endObject() throws IOException;

    void flush() throws IOException;

    @NotNull
    String getPath();

    @NotNull
    JsonWriter name(@NotNull String str) throws IOException;

    @NotNull
    JsonWriter nullValue() throws IOException;

    @NotNull
    JsonWriter value(double d2) throws IOException;

    @NotNull
    JsonWriter value(int i3) throws IOException;

    @NotNull
    JsonWriter value(long j2) throws IOException;

    @NotNull
    JsonWriter value(@NotNull Upload upload) throws IOException;

    @NotNull
    JsonWriter value(@NotNull JsonNumber jsonNumber) throws IOException;

    @NotNull
    JsonWriter value(@NotNull String str) throws IOException;

    @NotNull
    JsonWriter value(boolean z2) throws IOException;
}
