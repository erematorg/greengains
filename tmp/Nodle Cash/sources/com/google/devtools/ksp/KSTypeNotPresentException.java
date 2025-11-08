package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/google/devtools/ksp/KSTypeNotPresentException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "ksType", "Lcom/google/devtools/ksp/symbol/KSType;", "cause", "", "(Lcom/google/devtools/ksp/symbol/KSType;Ljava/lang/Throwable;)V", "getKsType", "()Lcom/google/devtools/ksp/symbol/KSType;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
@KspExperimental
public final class KSTypeNotPresentException extends RuntimeException {
    @NotNull
    private final KSType ksType;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KSTypeNotPresentException(@NotNull KSType kSType, @NotNull Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(kSType, "ksType");
        Intrinsics.checkNotNullParameter(th, "cause");
        this.ksType = kSType;
    }

    @NotNull
    public final KSType getKsType() {
        return this.ksType;
    }
}
