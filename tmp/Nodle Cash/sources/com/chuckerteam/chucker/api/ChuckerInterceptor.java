package com.chuckerteam.chucker.api;

import android.content.Context;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001f\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u0010\"\u00020\u0011¢\u0006\u0002\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "Lokhttp3/Interceptor;", "context", "Landroid/content/Context;", "collector", "", "maxContentLength", "headersToRedact", "alwaysReadResponseBody", "(Landroid/content/Context;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "redactHeaders", "names", "", "", "([Ljava/lang/String;)Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "Builder", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class ChuckerInterceptor implements Interceptor {

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eJ\u001f\u0010\u000f\u001a\u00020\u00002\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u0011\"\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0014\u0010\u000f\u001a\u00020\u00002\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alwaysReadResponseBody", "enable", "", "build", "Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "collector", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "maxContentLength", "length", "", "redactHeaders", "headerNames", "", "", "([Ljava/lang/String;)Lcom/chuckerteam/chucker/api/ChuckerInterceptor$Builder;", "", "com.github.ChuckerTeam.Chucker.library-no-op"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Builder {
        @NotNull
        private final Context context;

        public Builder(@NotNull Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        @NotNull
        public final Builder alwaysReadResponseBody(boolean z2) {
            return this;
        }

        @NotNull
        public final ChuckerInterceptor build() {
            return new ChuckerInterceptor(this.context, (Object) null, (Object) null, (Object) null, (Object) null, 30, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Builder collector(@NotNull ChuckerCollector chuckerCollector) {
            Intrinsics.checkNotNullParameter(chuckerCollector, "collector");
            return this;
        }

        @NotNull
        public final Builder maxContentLength(long j2) {
            return this;
        }

        @NotNull
        public final Builder redactHeaders(@NotNull Iterable<String> iterable) {
            Intrinsics.checkNotNullParameter(iterable, "headerNames");
            return this;
        }

        @NotNull
        public final Builder redactHeaders(@NotNull String... strArr) {
            Intrinsics.checkNotNullParameter(strArr, "headerNames");
            return this;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context) {
        this(context, (Object) null, (Object) null, (Object) null, (Object) null, 30, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Response proceed = chain.proceed(chain.request());
        Intrinsics.checkNotNullExpressionValue(proceed, "chain.proceed(request)");
        return proceed;
    }

    @NotNull
    public final ChuckerInterceptor redactHeaders(@NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "names");
        return this;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @Nullable Object obj) {
        this(context, obj, (Object) null, (Object) null, (Object) null, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @Nullable Object obj, @Nullable Object obj2) {
        this(context, obj, obj2, (Object) null, (Object) null, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3) {
        this(context, obj, obj2, obj3, (Object) null, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @Nullable Object obj, @Nullable Object obj2, @Nullable Object obj3, @Nullable Object obj4) {
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ChuckerInterceptor(Context context, Object obj, Object obj2, Object obj3, Object obj4, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : obj, (i3 & 4) != 0 ? null : obj2, (i3 & 8) != 0 ? null : obj3, (i3 & 16) != 0 ? null : obj4);
    }
}
