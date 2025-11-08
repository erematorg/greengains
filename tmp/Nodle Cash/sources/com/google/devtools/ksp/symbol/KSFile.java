package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSFile;", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "fileName", "", "getFileName", "()Ljava/lang/String;", "filePath", "getFilePath", "packageName", "Lcom/google/devtools/ksp/symbol/KSName;", "getPackageName", "()Lcom/google/devtools/ksp/symbol/KSName;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSFile extends KSDeclarationContainer, KSAnnotated {
    @NotNull
    String getFileName();

    @NotNull
    String getFilePath();

    @NotNull
    KSName getPackageName();
}
