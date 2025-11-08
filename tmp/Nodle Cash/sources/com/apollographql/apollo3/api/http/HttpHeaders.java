package com.apollographql.apollo3.api.http;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0005"}, d2 = {"valueOf", "", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "name", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nHttpHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpHeaders.kt\ncom/apollographql/apollo3/api/http/HttpHeaders\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,12:1\n288#2,2:13\n*S KotlinDebug\n*F\n+ 1 HttpHeaders.kt\ncom/apollographql/apollo3/api/http/HttpHeaders\n*L\n11#1:13,2\n*E\n"})
@JvmName(name = "HttpHeaders")
public final class HttpHeaders {
    @Nullable
    public static final String valueOf(@NotNull List<HttpHeader> list, @NotNull String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (StringsKt__StringsJVMKt.equals(((HttpHeader) obj).getName(), str, true)) {
                break;
            }
        }
        HttpHeader httpHeader = (HttpHeader) obj;
        if (httpHeader != null) {
            return httpHeader.getValue();
        }
        return null;
    }
}
