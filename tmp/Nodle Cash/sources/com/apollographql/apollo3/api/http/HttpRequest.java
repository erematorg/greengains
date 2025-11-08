package com.apollographql.apollo3.api.http;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0016B/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u001c\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpRequest;", "", "method", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "url", "", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "body", "Lcom/apollographql/apollo3/api/http/HttpBody;", "(Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/lang/String;Ljava/util/List;Lcom/apollographql/apollo3/api/http/HttpBody;)V", "getBody", "()Lcom/apollographql/apollo3/api/http/HttpBody;", "getHeaders", "()Ljava/util/List;", "getMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "getUrl", "()Ljava/lang/String;", "newBuilder", "Lcom/apollographql/apollo3/api/http/HttpRequest$Builder;", "Builder", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class HttpRequest {
    @Nullable
    private final HttpBody body;
    @NotNull
    private final List<HttpHeader> headers;
    @NotNull
    private final HttpMethod method;
    @NotNull
    private final String url;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005J\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0014\u0010\t\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/apollographql/apollo3/api/http/HttpRequest$Builder;", "", "method", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "url", "", "(Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/lang/String;)V", "body", "Lcom/apollographql/apollo3/api/http/HttpBody;", "headers", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "addHeader", "name", "value", "addHeaders", "", "build", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @Nullable
        private HttpBody body;
        @NotNull
        private final List<HttpHeader> headers = new ArrayList();
        @NotNull
        private final HttpMethod method;
        @NotNull
        private final String url;

        public Builder(@NotNull HttpMethod httpMethod, @NotNull String str) {
            Intrinsics.checkNotNullParameter(httpMethod, FirebaseAnalytics.Param.METHOD);
            Intrinsics.checkNotNullParameter(str, "url");
            this.method = httpMethod;
            this.url = str;
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
        public final Builder body(@NotNull HttpBody httpBody) {
            Intrinsics.checkNotNullParameter(httpBody, "body");
            this.body = httpBody;
            return this;
        }

        @NotNull
        public final HttpRequest build() {
            return new HttpRequest(this.method, this.url, this.headers, this.body, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder headers(@NotNull List<HttpHeader> list) {
            Intrinsics.checkNotNullParameter(list, "headers");
            this.headers.clear();
            this.headers.addAll(list);
            return this;
        }
    }

    public /* synthetic */ HttpRequest(HttpMethod httpMethod, String str, List list, HttpBody httpBody, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpMethod, str, list, httpBody);
    }

    public static /* synthetic */ Builder newBuilder$default(HttpRequest httpRequest, HttpMethod httpMethod, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            httpMethod = httpRequest.method;
        }
        if ((i3 & 2) != 0) {
            str = httpRequest.url;
        }
        return httpRequest.newBuilder(httpMethod, str);
    }

    @Nullable
    public final HttpBody getBody() {
        return this.body;
    }

    @NotNull
    public final List<HttpHeader> getHeaders() {
        return this.headers;
    }

    @NotNull
    public final HttpMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }

    @NotNull
    @JvmOverloads
    public final Builder newBuilder() {
        return newBuilder$default(this, (HttpMethod) null, (String) null, 3, (Object) null);
    }

    private HttpRequest(HttpMethod httpMethod, String str, List<HttpHeader> list, HttpBody httpBody) {
        this.method = httpMethod;
        this.url = str;
        this.headers = list;
        this.body = httpBody;
    }

    @NotNull
    @JvmOverloads
    public final Builder newBuilder(@NotNull HttpMethod httpMethod) {
        Intrinsics.checkNotNullParameter(httpMethod, FirebaseAnalytics.Param.METHOD);
        return newBuilder$default(this, httpMethod, (String) null, 2, (Object) null);
    }

    @NotNull
    @JvmOverloads
    public final Builder newBuilder(@NotNull HttpMethod httpMethod, @NotNull String str) {
        Intrinsics.checkNotNullParameter(httpMethod, FirebaseAnalytics.Param.METHOD);
        Intrinsics.checkNotNullParameter(str, "url");
        Builder builder = new Builder(httpMethod, str);
        HttpBody httpBody = this.body;
        if (httpBody != null) {
            builder.body(httpBody);
        }
        builder.addHeaders(this.headers);
        return builder;
    }
}
