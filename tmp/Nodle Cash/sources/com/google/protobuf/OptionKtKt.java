package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Option;
import com.google.protobuf.OptionKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\bø\u0001\u0000¢\u0006\u0002\b\f\u001a)\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\bø\u0001\u0000\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000e"}, d2 = {"valueOrNull", "Lcom/google/protobuf/Any;", "Lcom/google/protobuf/OptionOrBuilder;", "getValueOrNull", "(Lcom/google/protobuf/OptionOrBuilder;)Lcom/google/protobuf/Any;", "option", "Lcom/google/protobuf/Option;", "block", "Lkotlin/Function1;", "Lcom/google/protobuf/OptionKt$Dsl;", "", "Lkotlin/ExtensionFunctionType;", "-initializeoption", "copy", "java_kotlin-lite-well_known_protos_kotlin_lite"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class OptionKtKt {
    @NotNull
    @JvmName(name = "-initializeoption")
    /* renamed from: -initializeoption  reason: not valid java name */
    public static final Option m8257initializeoption(@NotNull Function1<? super OptionKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        OptionKt.Dsl.Companion companion = OptionKt.Dsl.Companion;
        Option.Builder newBuilder = Option.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder()");
        OptionKt.Dsl _create = companion._create(newBuilder);
        function1.invoke(_create);
        return _create._build();
    }

    @NotNull
    public static final Option copy(@NotNull Option option, @NotNull Function1<? super OptionKt.Dsl, Unit> function1) {
        Intrinsics.checkNotNullParameter(option, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        OptionKt.Dsl.Companion companion = OptionKt.Dsl.Companion;
        GeneratedMessageLite.Builder builder = option.toBuilder();
        Intrinsics.checkNotNullExpressionValue(builder, "this.toBuilder()");
        OptionKt.Dsl _create = companion._create((Option.Builder) builder);
        function1.invoke(_create);
        return _create._build();
    }

    @Nullable
    public static final Any getValueOrNull(@NotNull OptionOrBuilder optionOrBuilder) {
        Intrinsics.checkNotNullParameter(optionOrBuilder, "<this>");
        if (optionOrBuilder.hasValue()) {
            return optionOrBuilder.getValue();
        }
        return null;
    }
}
