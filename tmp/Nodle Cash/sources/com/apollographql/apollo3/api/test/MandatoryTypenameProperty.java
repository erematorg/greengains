package com.apollographql.apollo3.api.test;

import androidx.compose.animation.core.a;
import com.apollographql.apollo3.annotations.ApolloExperimental;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApolloExperimental
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0002J%\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u000f\u001a\u00020\u0003H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/api/test/MandatoryTypenameProperty;", "", "parentTypeName", "", "possibleTypes", "", "(Ljava/lang/String;Ljava/util/List;)V", "typename", "getValue", "mapBuilder", "Lcom/apollographql/apollo3/api/test/MapBuilder;", "property", "Lkotlin/reflect/KProperty;", "setValue", "", "value", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class MandatoryTypenameProperty {
    @NotNull
    private final String parentTypeName;
    @NotNull
    private final List<String> possibleTypes;
    @Nullable
    private String typename;

    public MandatoryTypenameProperty(@NotNull String str, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(str, "parentTypeName");
        Intrinsics.checkNotNullParameter(list, "possibleTypes");
        this.parentTypeName = str;
        this.possibleTypes = list;
    }

    @NotNull
    public final String getValue(@NotNull MapBuilder mapBuilder, @NotNull KProperty<?> kProperty) {
        Intrinsics.checkNotNullParameter(mapBuilder, "mapBuilder");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        String str = this.typename;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.parentTypeName);
        sb.append(": __typename is not known at compile-time for this type. Please specify it explicitly (allowed values: ");
        throw new IllegalStateException(a.o(')', CollectionsKt___CollectionsKt.joinToString$default(this.possibleTypes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null), sb).toString());
    }

    public final void setValue(@NotNull MapBuilder mapBuilder, @NotNull KProperty<?> kProperty, @NotNull String str) {
        Intrinsics.checkNotNullParameter(mapBuilder, "mapBuilder");
        Intrinsics.checkNotNullParameter(kProperty, "property");
        Intrinsics.checkNotNullParameter(str, "value");
        this.typename = str;
    }
}
