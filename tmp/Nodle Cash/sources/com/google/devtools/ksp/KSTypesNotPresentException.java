package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/google/devtools/ksp/KSTypesNotPresentException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "ksTypes", "", "Lcom/google/devtools/ksp/symbol/KSType;", "cause", "", "(Ljava/util/List;Ljava/lang/Throwable;)V", "getKsTypes", "()Ljava/util/List;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
@KspExperimental
public final class KSTypesNotPresentException extends RuntimeException {
    @NotNull
    private final List<KSType> ksTypes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KSTypesNotPresentException(@NotNull List<? extends KSType> list, @NotNull Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(list, "ksTypes");
        Intrinsics.checkNotNullParameter(th, "cause");
        this.ksTypes = list;
    }

    @NotNull
    public final List<KSType> getKsTypes() {
        return this.ksTypes;
    }
}
