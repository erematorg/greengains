package com.google.devtools.ksp.symbol;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\f¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSFunction;", "", "extensionReceiverType", "Lcom/google/devtools/ksp/symbol/KSType;", "getExtensionReceiverType", "()Lcom/google/devtools/ksp/symbol/KSType;", "isError", "", "()Z", "parameterTypes", "", "getParameterTypes", "()Ljava/util/List;", "returnType", "getReturnType", "typeParameters", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "getTypeParameters", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSFunction {
    @Nullable
    KSType getExtensionReceiverType();

    @NotNull
    List<KSType> getParameterTypes();

    @Nullable
    KSType getReturnType();

    @NotNull
    List<KSTypeParameter> getTypeParameters();

    boolean isError();
}
