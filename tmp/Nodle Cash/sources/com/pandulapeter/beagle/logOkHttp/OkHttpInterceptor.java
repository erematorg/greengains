package com.pandulapeter.beagle.logOkHttp;

import com.google.common.net.HttpHeaders;
import com.pandulapeter.beagle.commonBase.BeagleNetworkLoggerContract;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\f\u0010\u0011\u001a\u00020\u0007*\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/pandulapeter/beagle/logOkHttp/OkHttpInterceptor;", "Lokhttp3/Interceptor;", "()V", "utf8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "bodyHasUnknownEncoding", "", "headers", "Lokhttp3/Headers;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "isPlaintext", "buffer", "Lokio/Buffer;", "isProbablyUtf8", "Companion", "log-okhttp"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OkHttpInterceptor implements Interceptor {
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final long MAX_STRING_LENGTH = 4096;
    private final Charset utf8 = Charset.forName("UTF-8");

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/pandulapeter/beagle/logOkHttp/OkHttpInterceptor$Companion;", "", "()V", "MAX_STRING_LENGTH", "", "log-okhttp"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get(HttpHeaders.CONTENT_ENCODING);
        return str != null && !StringsKt__StringsJVMKt.equals(str, "identity", true) && !StringsKt__StringsJVMKt.equals(str, "gzip", true);
    }

    private final boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0, buffer.size() < 64 ? buffer.size() : 64);
            int i3 = 0;
            while (true) {
                if (i3 >= 16) {
                    break;
                } else if (buffer2.exhausted()) {
                    break;
                } else {
                    int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                    if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                        return false;
                    }
                    i3++;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private final boolean isProbablyUtf8(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0, RangesKt.coerceAtMost(buffer.size(), 64));
            int i3 = 0;
            while (true) {
                if (i3 >= 16) {
                    break;
                } else if (buffer2.exhausted()) {
                    break;
                } else {
                    int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                    if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                        return false;
                    }
                    i3++;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        String str;
        BufferedSource source;
        Buffer buffer;
        ResponseBody body;
        Interceptor.Chain chain2 = chain;
        Intrinsics.checkNotNullParameter(chain2, "chain");
        Request request = chain.request();
        RequestBody body2 = request.body();
        if (body2 == null) {
            str = "No payload";
        } else if (bodyHasUnknownEncoding(request.headers())) {
            str = "Encoded payload";
        } else if (body2.contentLength() > 4096) {
            str = "Payload too large";
        } else {
            Buffer buffer2 = new Buffer();
            body2.writeTo(buffer2);
            Charset charset = this.utf8;
            MediaType contentType = body2.contentType();
            if (contentType != null) {
                charset = contentType.charset(this.utf8);
            }
            if (!isPlaintext(buffer2)) {
                str = "Binary payload, " + body2.contentLength() + "-byte body";
            } else if (charset == null || (str = buffer2.readString(charset)) == null) {
                str = "";
            }
        }
        String str2 = str;
        BeagleOkHttpLogger beagleOkHttpLogger = BeagleOkHttpLogger.INSTANCE;
        String str3 = "[" + request.method() + "] " + request.url();
        Headers<Pair> headers = request.headers();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(headers, 10));
        for (Pair pair : headers) {
            arrayList.add("[" + ((String) pair.getFirst()) + "] " + ((String) pair.getSecond()));
        }
        String str4 = "] ";
        BeagleNetworkLoggerContract.DefaultImpls.logNetwork$default(beagleOkHttpLogger, true, str3, str2, arrayList, (Long) null, 0, (String) null, 112, (Object) null);
        long nanoTime = System.nanoTime();
        try {
            Response proceed = chain2.proceed(request);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime);
            ResponseBody body3 = proceed.body();
            MediaType mediaType = null;
            MediaType contentType2 = body3 != null ? body3.contentType() : null;
            String string = ((contentType2 != null && !Intrinsics.areEqual((Object) contentType2.subtype(), (Object) "json")) || body3 == null || (source = body3.source()) == null || (buffer = source.getBuffer()) == null || !isProbablyUtf8(buffer) || (body = proceed.body()) == null) ? null : body.string();
            BeagleOkHttpLogger beagleOkHttpLogger2 = BeagleOkHttpLogger.INSTANCE;
            StringBuilder sb = new StringBuilder("[");
            sb.append(request.method());
            String str5 = str4;
            sb.append(str5);
            sb.append(proceed.code());
            sb.append(' ');
            sb.append(request.url());
            String sb2 = sb.toString();
            String message = string == null ? proceed.message() : string;
            Headers<Pair> headers2 = proceed.headers();
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(headers2, 10));
            for (Pair pair2 : headers2) {
                arrayList2.add("[" + ((String) pair2.getFirst()) + str5 + ((String) pair2.getSecond()));
            }
            BeagleNetworkLoggerContract.DefaultImpls.logNetwork$default(beagleOkHttpLogger2, false, sb2, message, arrayList2, Long.valueOf(millis), 0, (String) null, 96, (Object) null);
            Response.Builder newBuilder = proceed.newBuilder();
            if (string != null) {
                ResponseBody.Companion companion = ResponseBody.Companion;
                if (body3 != null) {
                    mediaType = body3.contentType();
                }
                ResponseBody create = companion.create(string, mediaType);
                if (create != null) {
                    body3 = create;
                }
            }
            return newBuilder.body(body3).build();
        } catch (Exception e3) {
            Exception exc = e3;
            BeagleOkHttpLogger beagleOkHttpLogger3 = BeagleOkHttpLogger.INSTANCE;
            String str6 = "[" + request.method() + "] FAIL " + request.url();
            String message2 = exc.getMessage();
            if (message2 == null) {
                message2 = "HTTP Failed";
            }
            BeagleNetworkLoggerContract.DefaultImpls.logNetwork$default(beagleOkHttpLogger3, false, str6, message2, (List) null, (Long) null, 0, (String) null, 120, (Object) null);
            throw exc;
        }
    }
}
