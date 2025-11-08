package com.google.devtools.ksp.visitor;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.KSValueParameter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001B!\u0012\u001a\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\u001f\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0002J\u001f\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0019J\u001f\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010!J\u001f\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020(2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010)J\u001f\u0010*\u001a\u00020\u00032\u0006\u0010+\u001a\u00020,2\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010-J\u001f\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u0002002\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00101J\u001f\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u0002042\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00105J\u001f\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u0002082\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u00109R\"\u0010\u0004\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSValidateVisitor;", "Lcom/google/devtools/ksp/visitor/KSDefaultVisitor;", "Lcom/google/devtools/ksp/symbol/KSNode;", "", "predicate", "Lkotlin/Function2;", "(Lkotlin/jvm/functions/Function2;)V", "defaultHandler", "node", "data", "(Lcom/google/devtools/ksp/symbol/KSNode;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "validateType", "type", "Lcom/google/devtools/ksp/symbol/KSType;", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Lcom/google/devtools/ksp/symbol/KSNode;)Ljava/lang/Boolean;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nKSValidateVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KSValidateVisitor.kt\ncom/google/devtools/ksp/visitor/KSValidateVisitor\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,115:1\n1747#2,3:116\n1747#2,3:119\n1747#2,3:128\n1726#2,3:133\n1726#2,3:136\n1224#3,2:122\n1224#3,2:124\n1224#3,2:126\n1224#3,2:131\n*S KotlinDebug\n*F\n+ 1 KSValidateVisitor.kt\ncom/google/devtools/ksp/visitor/KSValidateVisitor\n*L\n9#1:116,3\n20#1:119,3\n50#1:128,3\n82#1:133,3\n105#1:136,3\n27#1:122,2\n36#1:124,2\n40#1:126,2\n64#1:131,2\n*E\n"})
public class KSValidateVisitor extends KSDefaultVisitor<KSNode, Boolean> {
    @NotNull
    private final Function2<KSNode, KSNode, Boolean> predicate;

    public KSValidateVisitor(@NotNull Function2<? super KSNode, ? super KSNode, Boolean> function2) {
        Intrinsics.checkNotNullParameter(function2, "predicate");
        this.predicate = function2;
    }

    private final boolean validateType(KSType kSType) {
        if (!kSType.isError()) {
            Iterable<KSTypeArgument> arguments = kSType.getArguments();
            if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
                for (KSTypeArgument type : arguments) {
                    KSTypeReference type2 = type.getType();
                    if (type2 == null || ((Boolean) type2.accept(this, null)).booleanValue()) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static final boolean visitValueArgument$visitValue(KSValidateVisitor kSValidateVisitor, KSNode kSNode, Object obj) {
        if (obj instanceof KSType) {
            return kSValidateVisitor.validateType((KSType) obj);
        }
        if (obj instanceof KSAnnotation) {
            return kSValidateVisitor.visitAnnotation((KSAnnotation) obj, kSNode).booleanValue();
        }
        if (obj instanceof List) {
            Iterable<Object> iterable = (Iterable) obj;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                for (Object visitValueArgument$visitValue : iterable) {
                    if (!visitValueArgument$visitValue(kSValidateVisitor, kSNode, visitValueArgument$visitValue)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @NotNull
    public Boolean defaultHandler(@NotNull KSNode kSNode, @Nullable KSNode kSNode2) {
        Intrinsics.checkNotNullParameter(kSNode, "node");
        return Boolean.TRUE;
    }

    @NotNull
    public Boolean visitAnnotated(@NotNull KSAnnotated kSAnnotated, @Nullable KSNode kSNode) {
        boolean z2;
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        if (this.predicate.invoke(kSNode, kSAnnotated).booleanValue()) {
            Iterator<KSAnnotation> it = kSAnnotated.getAnnotations().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!((Boolean) it.next().accept(this, kSAnnotated)).booleanValue()) {
                        z2 = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z2 = true;
        return Boolean.valueOf(z2);
    }

    @NotNull
    public Boolean visitAnnotation(@NotNull KSAnnotation kSAnnotation, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        if (!this.predicate.invoke(kSNode, kSAnnotation).booleanValue()) {
            return Boolean.TRUE;
        }
        if (!((Boolean) kSAnnotation.getAnnotationType().accept(this, kSAnnotation)).booleanValue()) {
            return Boolean.FALSE;
        }
        Iterable<KSValueArgument> arguments = kSAnnotation.getArguments();
        if (!(arguments instanceof Collection) || !((Collection) arguments).isEmpty()) {
            for (KSValueArgument kSValueArgument : arguments) {
                if (!((Boolean) kSValueArgument.accept(this, kSValueArgument)).booleanValue()) {
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    @NotNull
    public Boolean visitClassDeclaration(@NotNull KSClassDeclaration kSClassDeclaration, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        if (kSClassDeclaration.asStarProjectedType().isError()) {
            return Boolean.FALSE;
        }
        for (KSTypeReference accept : kSClassDeclaration.getSuperTypes()) {
            if (!((Boolean) accept.accept(this, kSClassDeclaration)).booleanValue()) {
                return Boolean.FALSE;
            }
        }
        if (!visitDeclaration((KSDeclaration) kSClassDeclaration, kSNode).booleanValue()) {
            return Boolean.FALSE;
        }
        if (!visitDeclarationContainer((KSDeclarationContainer) kSClassDeclaration, kSNode).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @NotNull
    public Boolean visitDeclaration(@NotNull KSDeclaration kSDeclaration, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        if (!this.predicate.invoke(kSNode, kSDeclaration).booleanValue()) {
            return Boolean.TRUE;
        }
        Iterable<KSTypeParameter> typeParameters = kSDeclaration.getTypeParameters();
        if (!(typeParameters instanceof Collection) || !((Collection) typeParameters).isEmpty()) {
            for (KSTypeParameter accept : typeParameters) {
                if (!((Boolean) accept.accept(this, kSDeclaration)).booleanValue()) {
                    return Boolean.FALSE;
                }
            }
        }
        return visitAnnotated((KSAnnotated) kSDeclaration, kSNode);
    }

    @NotNull
    public Boolean visitDeclarationContainer(@NotNull KSDeclarationContainer kSDeclarationContainer, @Nullable KSNode kSNode) {
        boolean z2;
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        Iterator<KSDeclaration> it = kSDeclarationContainer.getDeclarations().iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = true;
                break;
            }
            KSDeclaration next = it.next();
            if (this.predicate.invoke(kSDeclarationContainer, next).booleanValue() && !((Boolean) next.accept(this, kSDeclarationContainer)).booleanValue()) {
                z2 = false;
                break;
            }
        }
        return Boolean.valueOf(z2);
    }

    @NotNull
    public Boolean visitFunctionDeclaration(@NotNull KSFunctionDeclaration kSFunctionDeclaration, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "function");
        if (kSFunctionDeclaration.getReturnType() != null) {
            Function2<KSNode, KSNode, Boolean> function2 = this.predicate;
            KSTypeReference returnType = kSFunctionDeclaration.getReturnType();
            Intrinsics.checkNotNull(returnType);
            if (function2.invoke(kSFunctionDeclaration, returnType).booleanValue()) {
                KSTypeReference returnType2 = kSFunctionDeclaration.getReturnType();
                Intrinsics.checkNotNull(returnType2);
                if (!((Boolean) returnType2.accept(this, kSNode)).booleanValue()) {
                    return Boolean.FALSE;
                }
            }
        }
        Iterable<KSValueParameter> parameters = kSFunctionDeclaration.getParameters();
        if (!(parameters instanceof Collection) || !((Collection) parameters).isEmpty()) {
            for (KSValueParameter accept : parameters) {
                if (!((Boolean) accept.accept(this, kSFunctionDeclaration)).booleanValue()) {
                    return Boolean.FALSE;
                }
            }
        }
        if (!visitDeclaration((KSDeclaration) kSFunctionDeclaration, kSNode).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @NotNull
    public Boolean visitPropertyDeclaration(@NotNull KSPropertyDeclaration kSPropertyDeclaration, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        if (this.predicate.invoke(kSPropertyDeclaration, kSPropertyDeclaration.getType()).booleanValue() && !((Boolean) kSPropertyDeclaration.getType().accept(this, kSNode)).booleanValue()) {
            return Boolean.FALSE;
        }
        if (!visitDeclaration((KSDeclaration) kSPropertyDeclaration, kSNode).booleanValue()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @NotNull
    public Boolean visitTypeParameter(@NotNull KSTypeParameter kSTypeParameter, @Nullable KSNode kSNode) {
        boolean z2;
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        if (this.predicate.invoke(kSNode, kSTypeParameter).booleanValue()) {
            Iterator<KSTypeReference> it = kSTypeParameter.getBounds().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!((Boolean) it.next().accept(this, kSTypeParameter)).booleanValue()) {
                        z2 = false;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z2 = true;
        return Boolean.valueOf(z2);
    }

    @NotNull
    public Boolean visitTypeReference(@NotNull KSTypeReference kSTypeReference, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        return Boolean.valueOf(validateType(kSTypeReference.resolve()));
    }

    @NotNull
    public Boolean visitValueArgument(@NotNull KSValueArgument kSValueArgument, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        return Boolean.valueOf(visitValueArgument$visitValue(this, kSNode, kSValueArgument.getValue()));
    }

    @NotNull
    public Boolean visitValueParameter(@NotNull KSValueParameter kSValueParameter, @Nullable KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        return (Boolean) kSValueParameter.getType().accept(this, kSValueParameter);
    }
}
