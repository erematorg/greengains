package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "it", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getAllSuperTypes$getTypesUpperBound$1 extends Lambda implements Function1<KSTypeReference, Sequence<? extends KSClassDeclaration>> {
    public static final UtilsKt$getAllSuperTypes$getTypesUpperBound$1 INSTANCE = new UtilsKt$getAllSuperTypes$getTypesUpperBound$1();

    public UtilsKt$getAllSuperTypes$getTypesUpperBound$1() {
        super(1);
    }

    @NotNull
    public final Sequence<KSClassDeclaration> invoke(@NotNull KSTypeReference kSTypeReference) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "it");
        KSDeclaration declaration = kSTypeReference.resolve().getDeclaration();
        if (declaration instanceof KSClassDeclaration) {
            return SequencesKt.sequenceOf((T[]) new KSClassDeclaration[]{declaration});
        } else if (declaration instanceof KSTypeAlias) {
            return SequencesKt.sequenceOf((T[]) new KSClassDeclaration[]{UtilsKt.findActualType((KSTypeAlias) declaration)});
        } else if (declaration instanceof KSTypeParameter) {
            return UtilsKt.getAllSuperTypes$getTypesUpperBound((KSTypeParameter) declaration);
        } else {
            throw new IllegalStateException("unhandled type parameter bound, please file a bug at https://github.com/google/ksp/issues/new");
        }
    }
}
