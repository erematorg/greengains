package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSTypeReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "it", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getAllSuperTypes$2 extends Lambda implements Function1<KSTypeReference, KSDeclaration> {
    public static final UtilsKt$getAllSuperTypes$2 INSTANCE = new UtilsKt$getAllSuperTypes$2();

    public UtilsKt$getAllSuperTypes$2() {
        super(1);
    }

    @Nullable
    public final KSDeclaration invoke(@NotNull KSTypeReference kSTypeReference) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "it");
        return kSTypeReference.resolve().getDeclaration();
    }
}
