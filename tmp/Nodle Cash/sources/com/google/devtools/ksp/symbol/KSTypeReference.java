package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSTypeReference;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "getElement", "()Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "resolve", "Lcom/google/devtools/ksp/symbol/KSType;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSTypeReference extends KSAnnotated, KSModifierListOwner {
    @Nullable
    KSReferenceElement getElement();

    @NotNull
    KSType resolve();
}
