package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSName;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "", "it", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "invoke", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getAnnotationsByType$1 extends Lambda implements Function1<KSAnnotation, Boolean> {
    final /* synthetic */ KClass<T> $annotationKClass;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UtilsKt$getAnnotationsByType$1(KClass<T> kClass) {
        super(1);
        this.$annotationKClass = kClass;
    }

    @NotNull
    public final Boolean invoke(@NotNull KSAnnotation kSAnnotation) {
        boolean z2;
        Intrinsics.checkNotNullParameter(kSAnnotation, "it");
        if (Intrinsics.areEqual((Object) kSAnnotation.getShortName().getShortName(), (Object) this.$annotationKClass.getSimpleName())) {
            KSName qualifiedName = kSAnnotation.getAnnotationType().resolve().getDeclaration().getQualifiedName();
            if (Intrinsics.areEqual((Object) qualifiedName != null ? qualifiedName.asString() : null, (Object) this.$annotationKClass.getQualifiedName())) {
                z2 = true;
                return Boolean.valueOf(z2);
            }
        }
        z2 = false;
        return Boolean.valueOf(z2);
    }
}
