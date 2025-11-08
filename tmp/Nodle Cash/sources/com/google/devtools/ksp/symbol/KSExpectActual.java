package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0004¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSExpectActual;", "", "isActual", "", "()Z", "isExpect", "findActuals", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "findExpects", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSExpectActual {
    @NotNull
    Sequence<KSDeclaration> findActuals();

    @NotNull
    Sequence<KSDeclaration> findExpects();

    boolean isActual();

    boolean isExpect();
}
