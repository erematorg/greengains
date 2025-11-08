package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J5\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u00062\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\t2\u0006\u0010\n\u001a\u0002H\u0007H\u0016¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "qualifier", "getQualifier", "()Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "accept", "R", "D", "visitor", "Lcom/google/devtools/ksp/symbol/KSVisitor;", "data", "(Lcom/google/devtools/ksp/symbol/KSVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "referencedName", "", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface KSClassifierReference extends KSReferenceElement {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <D, R> R accept(@NotNull KSClassifierReference kSClassifierReference, @NotNull KSVisitor<D, R> kSVisitor, D d2) {
            Intrinsics.checkNotNullParameter(kSVisitor, "visitor");
            return KSClassifierReference.super.accept(kSVisitor, d2);
        }
    }

    <D, R> R accept(@NotNull KSVisitor<D, R> kSVisitor, D d2) {
        Intrinsics.checkNotNullParameter(kSVisitor, "visitor");
        return kSVisitor.visitClassifierReference(this, d2);
    }

    @Nullable
    KSClassifierReference getQualifier();

    @NotNull
    String referencedName();
}
