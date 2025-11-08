package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "receiver", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getReceiver", "()Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSPropertyAccessor extends KSDeclarationContainer, KSAnnotated, KSModifierListOwner {
    @NotNull
    KSPropertyDeclaration getReceiver();
}
