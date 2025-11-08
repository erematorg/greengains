package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0013\u001a\u00020\u0014H&J\u0016\u0010\u0015\u001a\u00020\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H&J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH&J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000fH&J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00000\u000fH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001dÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "classKind", "Lcom/google/devtools/ksp/symbol/ClassKind;", "getClassKind", "()Lcom/google/devtools/ksp/symbol/ClassKind;", "isCompanionObject", "", "()Z", "primaryConstructor", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "getPrimaryConstructor", "()Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "superTypes", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getSuperTypes", "()Lkotlin/sequences/Sequence;", "asStarProjectedType", "Lcom/google/devtools/ksp/symbol/KSType;", "asType", "typeArguments", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "getAllFunctions", "getAllProperties", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getSealedSubclasses", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSClassDeclaration extends KSDeclaration, KSDeclarationContainer {
    @NotNull
    KSType asStarProjectedType();

    @NotNull
    KSType asType(@NotNull List<? extends KSTypeArgument> list);

    @NotNull
    Sequence<KSFunctionDeclaration> getAllFunctions();

    @NotNull
    Sequence<KSPropertyDeclaration> getAllProperties();

    @NotNull
    ClassKind getClassKind();

    @Nullable
    KSFunctionDeclaration getPrimaryConstructor();

    @NotNull
    Sequence<KSClassDeclaration> getSealedSubclasses();

    @NotNull
    Sequence<KSTypeReference> getSuperTypes();

    boolean isCompanionObject();
}
