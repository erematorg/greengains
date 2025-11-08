package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0000H&J\b\u0010\u001c\u001a\u00020\u0011H&J\b\u0010\u001d\u001a\u00020\u0011H&J\b\u0010\u001e\u001a\u00020\u0000H&J\b\u0010\u001f\u001a\u00020\u0000H&J\u0016\u0010 \u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\b\u0010!\u001a\u00020\u0000H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012R\u0012\u0010\u0014\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0012\u0010\u0015\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0012R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\"À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSType;", "", "annotations", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getAnnotations", "()Lkotlin/sequences/Sequence;", "arguments", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "getArguments", "()Ljava/util/List;", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "getDeclaration", "()Lcom/google/devtools/ksp/symbol/KSDeclaration;", "isError", "", "()Z", "isFunctionType", "isMarkedNullable", "isSuspendFunctionType", "nullability", "Lcom/google/devtools/ksp/symbol/Nullability;", "getNullability", "()Lcom/google/devtools/ksp/symbol/Nullability;", "isAssignableFrom", "that", "isCovarianceFlexible", "isMutabilityFlexible", "makeNotNullable", "makeNullable", "replace", "starProjection", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSType {
    @NotNull
    Sequence<KSAnnotation> getAnnotations();

    @NotNull
    List<KSTypeArgument> getArguments();

    @NotNull
    KSDeclaration getDeclaration();

    @NotNull
    Nullability getNullability();

    boolean isAssignableFrom(@NotNull KSType kSType);

    boolean isCovarianceFlexible();

    boolean isError();

    boolean isFunctionType();

    boolean isMarkedNullable();

    boolean isMutabilityFlexible();

    boolean isSuspendFunctionType();

    @NotNull
    KSType makeNotNullable();

    @NotNull
    KSType makeNullable();

    @NotNull
    KSType replace(@NotNull List<? extends KSTypeArgument> list);

    @NotNull
    KSType starProjection();
}
