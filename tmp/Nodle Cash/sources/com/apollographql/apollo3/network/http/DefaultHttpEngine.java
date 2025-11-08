package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpBody;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.api.http.HttpRequest;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.exception.ApolloNetworkException;
import com.apollographql.apollo3.network.OkHttpExtensionsKt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0011\b\u0016\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nB\r\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H@¢\u0006\u0002\u0010\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/network/http/DefaultHttpEngine;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "timeoutMillis", "", "(J)V", "connectTimeout", "readTimeout", "(JJ)V", "httpCallFactory", "Lokhttp3/Call$Factory;", "(Lokhttp3/Call$Factory;)V", "dispose", "", "execute", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "request", "Lcom/apollographql/apollo3/api/http/HttpRequest;", "(Lcom/apollographql/apollo3/api/http/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nOkHttpEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OkHttpEngine.kt\ncom/apollographql/apollo3/network/http/DefaultHttpEngine\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,110:1\n314#2,9:111\n323#2,2:124\n1549#3:120\n1620#3,3:121\n*S KotlinDebug\n*F\n+ 1 OkHttpEngine.kt\ncom/apollographql/apollo3/network/http/DefaultHttpEngine\n*L\n38#1:111,9\n38#1:124,2\n94#1:120\n94#1:121,3\n*E\n"})
public final class DefaultHttpEngine implements HttpEngine {
    /* access modifiers changed from: private */
    @NotNull
    public final Call.Factory httpCallFactory;

    public DefaultHttpEngine(@NotNull Call.Factory factory) {
        Intrinsics.checkNotNullParameter(factory, "httpCallFactory");
        this.httpCallFactory = factory;
    }

    public void dispose() {
    }

    @Nullable
    public Object execute(@NotNull HttpRequest httpRequest, @NotNull Continuation<? super HttpResponse> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Request.Builder headers = new Request.Builder().url(httpRequest.getUrl()).headers(OkHttpExtensionsKt.toOkHttpHeaders(httpRequest.getHeaders()));
        if (httpRequest.getMethod() == HttpMethod.Get) {
            headers.get();
        } else {
            HttpBody body = httpRequest.getBody();
            if (body != null) {
                headers.post(new DefaultHttpEngine$execute$2$httpRequest$1$2(body));
            } else {
                throw new IllegalStateException("HTTP POST requires a request body");
            }
        }
        Call newCall = this.httpCallFactory.newCall(headers.build());
        cancellableContinuationImpl.invokeOnCancellation(new DefaultHttpEngine$execute$2$1(newCall));
        Response response = null;
        try {
            response = newCall.execute();
            e = null;
        } catch (IOException e3) {
            e = e3;
        }
        if (e != null) {
            Result.Companion companion = Result.Companion;
            cancellableContinuationImpl.resumeWith(Result.m8979constructorimpl(ResultKt.createFailure(new ApolloNetworkException("Failed to execute GraphQL http network request", e))));
        } else {
            Result.Companion companion2 = Result.Companion;
            Intrinsics.checkNotNull(response);
            HttpResponse.Builder builder = new HttpResponse.Builder(response.code());
            ResponseBody body2 = response.body();
            Intrinsics.checkNotNull(body2);
            HttpResponse.Builder body3 = builder.body(body2.source());
            Headers headers2 = response.headers();
            IntRange until = RangesKt.until(0, headers2.size());
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                arrayList.add(new HttpHeader(headers2.name(nextInt), headers2.value(nextInt)));
            }
            Object r7 = Result.m8979constructorimpl(body3.addHeaders(arrayList).build());
            ResultKt.throwOnFailure(r7);
            cancellableContinuationImpl.resumeWith(Result.m8979constructorimpl(r7));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultHttpEngine(@NotNull OkHttpClient okHttpClient) {
        this((Call.Factory) okHttpClient);
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
    }

    public DefaultHttpEngine(long j2) {
        this(j2, j2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DefaultHttpEngine(long j2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 60000 : j2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultHttpEngine(long r3, long r5) {
        /*
            r2 = this;
            okhttp3.OkHttpClient$Builder r0 = new okhttp3.OkHttpClient$Builder
            r0.<init>()
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            okhttp3.OkHttpClient$Builder r3 = r0.connectTimeout(r3, r1)
            okhttp3.OkHttpClient$Builder r3 = r3.readTimeout(r5, r1)
            okhttp3.OkHttpClient r3 = r3.build()
            r2.<init>((okhttp3.OkHttpClient) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.DefaultHttpEngine.<init>(long, long):void");
    }
}
