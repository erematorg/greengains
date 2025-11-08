package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSAnnotation;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "T", "", "it", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "invoke", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;)Ljava/lang/annotation/Annotation;"}, k = 3, mv = {1, 9, 0}, xi = 48)
public final class UtilsKt$getAnnotationsByType$2 extends Lambda implements Function1<KSAnnotation, T> {
    final /* synthetic */ KClass<T> $annotationKClass;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UtilsKt$getAnnotationsByType$2(KClass<T> kClass) {
        super(1);
        this.$annotationKClass = kClass;
    }

    @NotNull
    public final T invoke(@NotNull KSAnnotation kSAnnotation) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "it");
        return UtilsKt.toAnnotation(kSAnnotation, JvmClassMappingKt.getJavaClass(this.$annotationKClass));
    }
}
