package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "name", "Lcom/google/devtools/ksp/symbol/KSName;", "getName", "()Lcom/google/devtools/ksp/symbol/KSName;", "type", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSTypeAlias extends KSDeclaration {
    @NotNull
    KSName getName();

    @NotNull
    KSTypeReference getType();
}
