package com.apollographql.apollo3.api;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Utf8;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B5\b\u0000\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0004H\u0016R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/api/DefaultUpload;", "Lcom/apollographql/apollo3/api/Upload;", "writeTo", "Lkotlin/Function1;", "Lokio/BufferedSink;", "", "contentType", "", "contentLength", "", "fileName", "(Lkotlin/jvm/functions/Function1;Ljava/lang/String;JLjava/lang/String;)V", "getContentLength", "()J", "getContentType", "()Ljava/lang/String;", "getFileName", "newBuilder", "Lcom/apollographql/apollo3/api/DefaultUpload$Builder;", "sink", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class DefaultUpload implements Upload {
    private final long contentLength;
    @NotNull
    private final String contentType;
    @Nullable
    private final String fileName;
    @NotNull
    private final Function1<BufferedSink, Unit> writeTo;

    public DefaultUpload(@NotNull Function1<? super BufferedSink, Unit> function1, @NotNull String str, long j2, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(function1, "writeTo");
        Intrinsics.checkNotNullParameter(str, "contentType");
        this.writeTo = function1;
        this.contentType = str;
        this.contentLength = j2;
        this.fileName = str2;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    @NotNull
    public String getContentType() {
        return this.contentType;
    }

    @Nullable
    public String getFileName() {
        return this.fileName;
    }

    @NotNull
    public final Builder newBuilder() {
        Builder contentLength2 = new Builder().content((Function1<? super BufferedSink, Unit>) this.writeTo).contentType(getContentType()).contentLength(getContentLength());
        if (getFileName() != null) {
            contentLength2.fileName(getFileName());
        }
        return contentLength2;
    }

    public void writeTo(@NotNull BufferedSink bufferedSink) {
        Intrinsics.checkNotNullParameter(bufferedSink, "sink");
        this.writeTo.invoke(bufferedSink);
    }

    @SourceDebugExtension({"SMAP\nDefaultUpload.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultUpload.kt\ncom/apollographql/apollo3/api/DefaultUpload$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,122:1\n1#2:123\n*E\n"})
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u001a\u0010\u000e\u001a\u00020\u00002\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0011H\u0007J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/DefaultUpload$Builder;", "", "()V", "contentLength", "", "contentType", "", "fileName", "writeTo", "Lkotlin/Function1;", "Lokio/BufferedSink;", "", "build", "Lcom/apollographql/apollo3/api/DefaultUpload;", "content", "byteArray", "", "Lokio/BufferedSource;", "byteString", "Lokio/ByteString;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        private long contentLength = -1;
        @Nullable
        private String contentType;
        @Nullable
        private String fileName;
        @Nullable
        private Function1<? super BufferedSink, Unit> writeTo;

        @NotNull
        public final DefaultUpload build() {
            Function1<? super BufferedSink, Unit> function1 = this.writeTo;
            if (function1 != null) {
                String str = this.contentType;
                if (str == null) {
                    str = "application/octet-stream";
                }
                return new DefaultUpload(function1, str, this.contentLength, this.fileName);
            }
            throw new IllegalStateException("DefaultUpload content is missing");
        }

        @NotNull
        @Deprecated(message = "This API is dangerous because the resulting upload can only be used once and can also lead to resource leaks.", replaceWith = @ReplaceWith(expression = "content {sink ->\nval source = openSource()\nsource.use {sink.writeAll(it)}\n}", imports = {}))
        public final Builder content(@NotNull BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, FirebaseAnalytics.Param.CONTENT);
            if (this.writeTo == null) {
                this.writeTo = new DefaultUpload$Builder$content$1$2(new Ref.BooleanRef(), bufferedSource);
                return this;
            }
            throw new IllegalStateException("content() can only be called once");
        }

        @NotNull
        public final Builder contentLength(long j2) {
            this.contentLength = j2;
            return this;
        }

        @NotNull
        public final Builder contentType(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "contentType");
            this.contentType = str;
            return this;
        }

        @NotNull
        public final Builder fileName(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "fileName");
            this.fileName = str;
            return this;
        }

        @NotNull
        public final Builder content(@NotNull Function1<? super BufferedSink, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "writeTo");
            if (this.writeTo == null) {
                this.writeTo = function1;
                return this;
            }
            throw new IllegalStateException("content() can only be called once");
        }

        @NotNull
        public final Builder content(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, FirebaseAnalytics.Param.CONTENT);
            if (this.writeTo == null) {
                this.writeTo = new DefaultUpload$Builder$content$3$2(str);
                this.contentLength = Utf8.size$default(str, 0, 0, 3, (Object) null);
                return this;
            }
            throw new IllegalStateException("content() can only be called once");
        }

        @NotNull
        public final Builder content(@NotNull ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, "byteString");
            if (this.writeTo == null) {
                this.writeTo = new DefaultUpload$Builder$content$4$2(byteString);
                this.contentLength = (long) byteString.size();
                return this;
            }
            throw new IllegalStateException("content() can only be called once");
        }

        @NotNull
        public final Builder content(@NotNull byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "byteArray");
            if (this.writeTo == null) {
                this.writeTo = new DefaultUpload$Builder$content$5$2(bArr);
                this.contentLength = (long) bArr.length;
                return this;
            }
            throw new IllegalStateException("content() can only be called once");
        }
    }
}
