package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u0004\u0018\u00010\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSValueArgument;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "isSpread", "", "()Z", "name", "Lcom/google/devtools/ksp/symbol/KSName;", "getName", "()Lcom/google/devtools/ksp/symbol/KSName;", "value", "", "getValue", "()Ljava/lang/Object;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSValueArgument extends KSAnnotated {
    @Nullable
    KSName getName();

    @Nullable
    Object getValue();

    boolean isSpread();
}
