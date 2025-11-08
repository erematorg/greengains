package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "invoke", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getConstructors$1 extends Lambda implements Function1<KSFunctionDeclaration, Boolean> {
    public static final UtilsKt$getConstructors$1 INSTANCE = new UtilsKt$getConstructors$1();

    public UtilsKt$getConstructors$1() {
        super(1);
    }

    @NotNull
    public final Boolean invoke(@NotNull KSFunctionDeclaration kSFunctionDeclaration) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "it");
        return Boolean.valueOf(UtilsKt.isConstructor(kSFunctionDeclaration));
    }
}
