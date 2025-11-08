package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u0012\u0010\u0007\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0005R\u0012\u0010\t\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0005R\u0014\u0010\u000b\u001a\u0004\u0018\u00010\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSValueParameter;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "hasDefault", "", "getHasDefault", "()Z", "isCrossInline", "isNoInline", "isVal", "isVar", "isVararg", "name", "Lcom/google/devtools/ksp/symbol/KSName;", "getName", "()Lcom/google/devtools/ksp/symbol/KSName;", "type", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getType", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSValueParameter extends KSAnnotated {
    boolean getHasDefault();

    @Nullable
    KSName getName();

    @NotNull
    KSTypeReference getType();

    boolean isCrossInline();

    boolean isNoInline();

    boolean isVal();

    boolean isVar();

    boolean isVararg();
}
