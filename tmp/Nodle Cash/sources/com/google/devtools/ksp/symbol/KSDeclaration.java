package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000fR\u0012\u0010\u0015\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000fR\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSDeclaration;", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "Lcom/google/devtools/ksp/symbol/KSExpectActual;", "containingFile", "Lcom/google/devtools/ksp/symbol/KSFile;", "getContainingFile", "()Lcom/google/devtools/ksp/symbol/KSFile;", "docString", "", "getDocString", "()Ljava/lang/String;", "packageName", "Lcom/google/devtools/ksp/symbol/KSName;", "getPackageName", "()Lcom/google/devtools/ksp/symbol/KSName;", "parentDeclaration", "getParentDeclaration", "()Lcom/google/devtools/ksp/symbol/KSDeclaration;", "qualifiedName", "getQualifiedName", "simpleName", "getSimpleName", "typeParameters", "", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "getTypeParameters", "()Ljava/util/List;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSDeclaration extends KSModifierListOwner, KSAnnotated, KSExpectActual {
    @Nullable
    KSFile getContainingFile();

    @Nullable
    String getDocString();

    @NotNull
    KSName getPackageName();

    @Nullable
    KSDeclaration getParentDeclaration();

    @Nullable
    KSName getQualifiedName();

    @NotNull
    KSName getSimpleName();

    @NotNull
    List<KSTypeParameter> getTypeParameters();
}
