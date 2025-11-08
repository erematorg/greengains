package com.apollographql.apollo3.api.http.internal;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\u0002\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0005H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0000\"\u0016\u0010\u0000\u001a\u00020\u00018\u0002XD¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"RESERVED_CHARS", "", "getRESERVED_CHARS$annotations", "()V", "percentEncode", "", "urlEncode", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nUrlEncode.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UrlEncode.kt\ncom/apollographql/apollo3/api/http/internal/UrlEncodeKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,27:1\n1174#2,2:28\n*S KotlinDebug\n*F\n+ 1 UrlEncode.kt\ncom/apollographql/apollo3/api/http/internal/UrlEncodeKt\n*L\n16#1:28,2\n*E\n"})
public final class UrlEncodeKt {
    @NotNull
    private static final String RESERVED_CHARS = "!#$&'\"()*+,/:;=?@[]{}% ";

    private static /* synthetic */ void getRESERVED_CHARS$annotations() {
    }

    private static final String percentEncode(char c3) {
        StringBuilder sb = new StringBuilder("%");
        String num = Integer.toString(c3, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
        sb.append(num);
        String upperCase = sb.toString().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    @NotNull
    public static final String urlEncode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (StringsKt__StringsKt.contains$default((CharSequence) RESERVED_CHARS, charAt, false, 2, (Object) null)) {
                sb.append(percentEncode(charAt));
            } else {
                sb.append(charAt);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }
}
