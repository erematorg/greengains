package com.apollographql.apollo3.api;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\u0006"}, d2 = {"Lcom/apollographql/apollo3/api/QueryDocumentMinifier;", "", "()V", "minify", "", "queryDocument", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@Deprecated(message = "Use the version in apollo-ast instead or copy paste this implementation", replaceWith = @ReplaceWith(expression = "com.apollographql.apollo3.ast.QueryDocumentMinifier", imports = {}))
public final class QueryDocumentMinifier {
    @NotNull
    public static final QueryDocumentMinifier INSTANCE = new QueryDocumentMinifier();

    private QueryDocumentMinifier() {
    }

    @JvmStatic
    @NotNull
    public static final String minify(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "queryDocument");
        return new Regex("\\s *").replace((CharSequence) str, StringUtils.SPACE);
    }
}
