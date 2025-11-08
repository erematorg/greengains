package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSType;", "it", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getAllSuperTypes$3 extends Lambda implements Function1<KSDeclaration, Sequence<? extends KSType>> {
    public static final UtilsKt$getAllSuperTypes$3 INSTANCE = new UtilsKt$getAllSuperTypes$3();

    public UtilsKt$getAllSuperTypes$3() {
        super(1);
    }

    @NotNull
    public final Sequence<KSType> invoke(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "it");
        if (kSDeclaration instanceof KSClassDeclaration) {
            return UtilsKt.getAllSuperTypes((KSClassDeclaration) kSDeclaration);
        }
        if (kSDeclaration instanceof KSTypeAlias) {
            return UtilsKt.getAllSuperTypes(UtilsKt.findActualType((KSTypeAlias) kSDeclaration));
        }
        if (kSDeclaration instanceof KSTypeParameter) {
            return SequencesKt.flatMap(UtilsKt.getAllSuperTypes$getTypesUpperBound((KSTypeParameter) kSDeclaration), AnonymousClass1.INSTANCE);
        }
        throw new IllegalStateException("unhandled super type kind, please file a bug at https://github.com/google/ksp/issues/new");
    }
}
