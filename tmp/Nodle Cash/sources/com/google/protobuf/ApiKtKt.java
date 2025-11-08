package com.google.protobuf;

import com.google.protobuf.Api;
import com.google.protobuf.ApiKt;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\bø\u0001\u0000¢\u0006\u0002\b\f\u001a)\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\bø\u0001\u0000\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000e"}, d2 = {"sourceContextOrNull", "Lcom/google/protobuf/SourceContext;", "Lcom/google/protobuf/ApiOrBuilder;", "getSourceContextOrNull", "(Lcom/google/protobuf/ApiOrBuilder;)Lcom/google/protobuf/SourceContext;", "api", "Lcom/google/protobuf/Api;", "block", "Lkotlin/Function1;", "Lcom/google/protobuf/ApiKt$Dsl;", "", "Lkotlin/ExtensionFunctionType;", "-initializeapi", "copy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class ApiKtKt {
    @NotNull
    @JvmName(name = "-initializeapi")
    /* renamed from: -initializeapi  reason: not valid java name */
    public static final Api m8241initializeapi(@NotNull Function1<? super ApiKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        ApiKt.Dsl.Companion companion = ApiKt.Dsl.Companion;
        Api.Builder newBuilder = Api.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        ApiKt.Dsl _create = companion._create(newBuilder);
        function1.invoke(_create);
        return _create._build();
    }

    @NotNull
    public static final Api copy(@NotNull Api api, @NotNull Function1<? super ApiKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(api, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        ApiKt.Dsl.Companion companion = ApiKt.Dsl.Companion;
        GeneratedMessageLite.Builder builder = api.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "this.toBuilder()");
        ApiKt.Dsl _create = companion._create((Api.Builder) builder);
        function1.invoke(_create);
        return _create._build();
    }

    @Nullable
    public static final SourceContext getSourceContextOrNull(@NotNull ApiOrBuilder apiOrBuilder) {
        Intrinsics.checkNotNullParameter(apiOrBuilder, "<this>");
        if (apiOrBuilder.hasSourceContext()) {
            return apiOrBuilder.getSourceContext();
        }
        return null;
    }
}
