package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "type", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "variance", "Lcom/google/devtools/ksp/symbol/Variance;", "getVariance", "()Lcom/google/devtools/ksp/symbol/Variance;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSTypeArgument extends KSAnnotated {
    @Nullable
    KSTypeReference getType();

    @NotNull
    Variance getVariance();
}
