package com.apollographql.apollo3.internal;

import com.apollographql.apollo3.api.http.HttpHeaders;
import com.apollographql.apollo3.api.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0002H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u000b"}, d2 = {"isMultipart", "", "Lcom/apollographql/apollo3/api/http/HttpResponse;", "(Lcom/apollographql/apollo3/api/http/HttpResponse;)Z", "getBoundaryParameter", "", "contentType", "multipartBodyFlow", "Lkotlinx/coroutines/flow/Flow;", "Lokio/BufferedSource;", "response", "apollo-runtime"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nmultipart.kt\nKotlin\n*S Kotlin\n*F\n+ 1 multipart.kt\ncom/apollographql/apollo3/internal/MultipartKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,49:1\n1549#2:50\n1620#2,3:51\n288#2,2:54\n*S KotlinDebug\n*F\n+ 1 multipart.kt\ncom/apollographql/apollo3/internal/MultipartKt\n*L\n42#1:50\n42#1:51,3\n43#1:54,2\n*E\n"})
public final class MultipartKt {
    /* access modifiers changed from: private */
    public static final String getBoundaryParameter(String str) {
        Object obj;
        List Y2;
        String str2;
        if (str == null) {
            return null;
        }
        Iterable<String> Y3 = StringsKt__StringsKt.split$default((CharSequence) str, new char[]{';'}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(Y3, 10));
        for (String s02 : Y3) {
            arrayList.add(StringsKt__StringsKt.trim((CharSequence) s02).toString());
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (StringsKt__StringsJVMKt.startsWith$default((String) obj, "boundary=", false, 2, (Object) null)) {
                break;
            }
        }
        String str3 = (String) obj;
        if (str3 == null || (Y2 = StringsKt__StringsKt.split$default((CharSequence) str3, new char[]{SignatureVisitor.INSTANCEOF}, false, 0, 6, (Object) null)) == null || (str2 = (String) CollectionsKt.getOrNull(Y2, 1)) == null) {
            return null;
        }
        return StringsKt.trim(str2, '\"', '\'');
    }

    public static final boolean isMultipart(@NotNull HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        String valueOf = HttpHeaders.valueOf(httpResponse.getHeaders(), "Content-Type");
        return valueOf != null && StringsKt__StringsJVMKt.startsWith(valueOf, "multipart/", true);
    }

    @NotNull
    public static final Flow<BufferedSource> multipartBodyFlow(@NotNull HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "response");
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        return FlowKt.onCompletion(FlowKt.flow(new MultipartKt$multipartBodyFlow$1(objectRef, httpResponse, (Continuation<? super MultipartKt$multipartBodyFlow$1>) null)), new MultipartKt$multipartBodyFlow$2(objectRef, (Continuation<? super MultipartKt$multipartBodyFlow$2>) null));
    }
}
