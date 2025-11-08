package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H&J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0000H&J\b\u0010\u0019\u001a\u00020\u000bH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0005¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "extensionReceiver", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getExtensionReceiver", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "getGetter", "()Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "hasBackingField", "", "getHasBackingField", "()Z", "isMutable", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "getSetter", "()Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "type", "getType", "asMemberOf", "Lcom/google/devtools/ksp/symbol/KSType;", "containing", "findOverridee", "isDelegated", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSPropertyDeclaration extends KSDeclaration {
    @NotNull
    KSType asMemberOf(@NotNull KSType kSType);

    @Nullable
    KSPropertyDeclaration findOverridee();

    @Nullable
    KSTypeReference getExtensionReceiver();

    @Nullable
    KSPropertyGetter getGetter();

    boolean getHasBackingField();

    @Nullable
    KSPropertySetter getSetter();

    @NotNull
    KSTypeReference getType();

    boolean isDelegated();

    boolean isMutable();
}
