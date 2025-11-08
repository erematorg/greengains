package com.google.protobuf;

import com.google.protobuf.Empty;
import com.google.protobuf.EmptyKt;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000¢\u0006\u0002\b\u0007\u001a)\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\t"}, d2 = {"empty", "Lcom/google/protobuf/Empty;", "block", "Lkotlin/Function1;", "Lcom/google/protobuf/EmptyKt$Dsl;", "", "Lkotlin/ExtensionFunctionType;", "-initializeempty", "copy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class EmptyKtKt {
    @NotNull
    @JvmName(name = "-initializeempty")
    /* renamed from: -initializeempty  reason: not valid java name */
    public static final Empty m8246initializeempty(@NotNull Function1<? super EmptyKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        EmptyKt.Dsl.Companion companion = EmptyKt.Dsl.Companion;
        Empty.Builder newBuilder = Empty.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        EmptyKt.Dsl _create = companion._create(newBuilder);
        function1.invoke(_create);
        return _create._build();
    }

    @NotNull
    public static final Empty copy(@NotNull Empty empty, @NotNull Function1<? super EmptyKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(empty, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        EmptyKt.Dsl.Companion companion = EmptyKt.Dsl.Companion;
        GeneratedMessageLite.Builder builder = empty.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "this.toBuilder()");
        EmptyKt.Dsl _create = companion._create((Empty.Builder) builder);
        function1.invoke(_create);
        return _create._build();
    }
}
