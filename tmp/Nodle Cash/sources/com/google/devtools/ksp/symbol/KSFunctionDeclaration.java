package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H&J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0001H&R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "extensionReceiver", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "getExtensionReceiver", "()Lcom/google/devtools/ksp/symbol/KSTypeReference;", "functionKind", "Lcom/google/devtools/ksp/symbol/FunctionKind;", "getFunctionKind", "()Lcom/google/devtools/ksp/symbol/FunctionKind;", "isAbstract", "", "()Z", "parameters", "", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "getParameters", "()Ljava/util/List;", "returnType", "getReturnType", "asMemberOf", "Lcom/google/devtools/ksp/symbol/KSFunction;", "containing", "Lcom/google/devtools/ksp/symbol/KSType;", "findOverridee", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSFunctionDeclaration extends KSDeclaration, KSDeclarationContainer {
    @NotNull
    KSFunction asMemberOf(@NotNull KSType kSType);

    @Nullable
    KSDeclaration findOverridee();

    @Nullable
    KSTypeReference getExtensionReceiver();

    @NotNull
    FunctionKind getFunctionKind();

    @NotNull
    List<KSValueParameter> getParameters();

    @Nullable
    KSTypeReference getReturnType();

    boolean isAbstract();
}
