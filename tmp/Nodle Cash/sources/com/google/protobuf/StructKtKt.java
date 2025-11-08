package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Struct;
import com.google.protobuf.StructKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000¢\u0006\u0002\b\u0007\u001a)\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\t"}, d2 = {"struct", "Lcom/google/protobuf/Struct;", "block", "Lkotlin/Function1;", "Lcom/google/protobuf/StructKt$Dsl;", "", "Lkotlin/ExtensionFunctionType;", "-initializestruct", "copy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class StructKtKt {
    @NotNull
    @JvmName(name = "-initializestruct")
    /* renamed from: -initializestruct  reason: not valid java name */
    public static final Struct m8260initializestruct(@NotNull Function1<? super StructKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        StructKt.Dsl.Companion companion = StructKt.Dsl.Companion;
        Struct.Builder newBuilder = Struct.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        StructKt.Dsl _create = companion._create(newBuilder);
        function1.invoke(_create);
        return _create._build();
    }

    @NotNull
    public static final Struct copy(@NotNull Struct struct, @NotNull Function1<? super StructKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(struct, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        StructKt.Dsl.Companion companion = StructKt.Dsl.Companion;
        GeneratedMessageLite.Builder builder = struct.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "this.toBuilder()");
        StructKt.Dsl _create = companion._create((Struct.Builder) builder);
        function1.invoke(_create);
        return _create._build();
    }
}
