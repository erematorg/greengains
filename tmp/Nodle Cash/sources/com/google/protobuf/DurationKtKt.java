package com.google.protobuf;

import com.google.protobuf.Duration;
import com.google.protobuf.DurationKt;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000¢\u0006\u0002\b\u0007\u001a)\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\t"}, d2 = {"duration", "Lcom/google/protobuf/Duration;", "block", "Lkotlin/Function1;", "Lcom/google/protobuf/DurationKt$Dsl;", "", "Lkotlin/ExtensionFunctionType;", "-initializeduration", "copy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class DurationKtKt {
    @NotNull
    @JvmName(name = "-initializeduration")
    /* renamed from: -initializeduration  reason: not valid java name */
    public static final Duration m8245initializeduration(@NotNull Function1<? super DurationKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        DurationKt.Dsl.Companion companion = DurationKt.Dsl.Companion;
        Duration.Builder newBuilder = Duration.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        DurationKt.Dsl _create = companion._create(newBuilder);
        function1.invoke(_create);
        return _create._build();
    }

    @NotNull
    public static final Duration copy(@NotNull Duration duration, @NotNull Function1<? super DurationKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(duration, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        DurationKt.Dsl.Companion companion = DurationKt.Dsl.Companion;
        GeneratedMessageLite.Builder builder = duration.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "this.toBuilder()");
        DurationKt.Dsl _create = companion._create((Duration.Builder) builder);
        function1.invoke(_create);
        return _create._build();
    }
}
