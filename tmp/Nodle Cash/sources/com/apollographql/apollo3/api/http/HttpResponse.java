package com.apollographql.apollo3.api.http;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B1\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0013\u001a\u00020\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpResponse;", "", "statusCode", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "bodySource", "Lokio/BufferedSource;", "bodyString", "Lokio/ByteString;", "(ILjava/util/List;Lokio/BufferedSource;Lokio/ByteString;)V", "body", "getBody", "()Lokio/BufferedSource;", "getHeaders", "()Ljava/util/List;", "getStatusCode", "()I", "newBuilder", "Lcom/apollographql/apollo3/api/http/HttpResponse$Builder;", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nHttp.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http.kt\ncom/apollographql/apollo3/api/http/HttpResponse\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,190:1\n1#2:191\n*E\n"})
public final class HttpResponse {
    @Nullable
    private final BufferedSource bodySource;
    @Nullable
    private final ByteString bodyString;
    @NotNull
    private final List<HttpHeader> headers;
    private final int statusCode;

    public /* synthetic */ HttpResponse(int i3, List list, BufferedSource bufferedSource, ByteString byteString, DefaultConstructorMarker defaultConstructorMarker) {
        this(i3, list, bufferedSource, byteString);
    }

    @Nullable
    public final BufferedSource getBody() {
        BufferedSource bufferedSource = this.bodySource;
        if (bufferedSource != null) {
            return bufferedSource;
        }
        ByteString byteString = this.bodyString;
        return byteString != null ? new Buffer().write(byteString) : null;
    }

    @NotNull
    public final List<HttpHeader> getHeaders() {
        return this.headers;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final Builder newBuilder() {
        Builder builder = new Builder(this.statusCode);
        BufferedSource bufferedSource = this.bodySource;
        if (bufferedSource != null) {
            builder.body(bufferedSource);
        }
        ByteString byteString = this.bodyString;
        if (byteString != null) {
            builder.body(byteString);
        }
        builder.addHeaders(this.headers);
        return builder;
    }

    private HttpResponse(int i3, List<HttpHeader> list, BufferedSource bufferedSource, ByteString byteString) {
        this.statusCode = i3;
        this.headers = list;
        this.bodySource = bufferedSource;
        this.bodyString = byteString;
    }

    @SourceDebugExtension({"SMAP\nHttp.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Http.kt\ncom/apollographql/apollo3/api/http/HttpResponse$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,190:1\n1#2:191\n*E\n"})
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014J\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\r\u001a\u00020\u00002\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0017R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpResponse$Builder;", "", "statusCode", "", "(I)V", "bodySource", "Lokio/BufferedSource;", "bodyString", "Lokio/ByteString;", "hasBody", "", "getHasBody", "()Z", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "getStatusCode", "()I", "addHeader", "name", "", "value", "addHeaders", "", "body", "build", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @Nullable
        private BufferedSource bodySource;
        @Nullable
        private ByteString bodyString;
        @NotNull
        private final List<HttpHeader> headers = new ArrayList();
        private final int statusCode;

        public Builder(int i3) {
            this.statusCode = i3;
        }

        private final boolean getHasBody() {
            return (this.bodySource == null && this.bodyString == null) ? false : true;
        }

        @NotNull
        public final Builder addHeader(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkNotNullParameter(str, "name");
            Intrinsics.checkNotNullParameter(str2, "value");
            this.headers.add(new HttpHeader(str, str2));
            return this;
        }

        @NotNull
        public final Builder addHeaders(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers.addAll(list);
            return this;
        }

        @NotNull
        public final Builder body(@NotNull BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "bodySource");
            if (!getHasBody()) {
                this.bodySource = bufferedSource;
                return this;
            }
            throw new IllegalStateException("body() can only be called once");
        }

        @NotNull
        public final HttpResponse build() {
            return new HttpResponse(this.statusCode, this.headers, this.bodySource, this.bodyString, (DefaultConstructorMarker) null);
        }

        public final int getStatusCode() {
            return this.statusCode;
        }

        @NotNull
        public final Builder headers(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers.clear();
            this.headers.addAll(list);
            return this;
        }

        @NotNull
        @Deprecated(message = "Use body(BufferedSource) instead", replaceWith = @ReplaceWith(expression = "Buffer().write(bodyString)", imports = {"okio.Buffer"}))
        public final Builder body(@NotNull ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, "bodyString");
            if (!getHasBody()) {
                this.bodyString = byteString;
                return this;
            }
            throw new IllegalStateException("body() can only be called once");
        }
    }
}
