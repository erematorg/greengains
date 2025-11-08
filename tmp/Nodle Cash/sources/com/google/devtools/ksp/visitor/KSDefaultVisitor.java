package com.google.devtools.ksp.visitor;

import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSCallableReference;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSClassifierReference;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSDefNonNullReference;
import com.google.devtools.ksp.symbol.KSDynamicReference;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSModifierListOwner;
import com.google.devtools.ksp.symbol.KSParenthesizedReference;
import com.google.devtools.ksp.symbol.KSPropertyAccessor;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSPropertyGetter;
import com.google.devtools.ksp.symbol.KSPropertySetter;
import com.google.devtools.ksp.symbol.KSReferenceElement;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.KSValueParameter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00028\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u001d\u0010\u0012\u001a\u00028\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u00172\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00028\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#J\u001d\u0010$\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00020%2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00028\u00012\u0006\u0010(\u001a\u00020)2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00028\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u0002012\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u0002042\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00105J\u001d\u00106\u001a\u00028\u00012\u0006\u00107\u001a\u0002082\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00109J\u001d\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020<2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010=J\u001d\u0010>\u001a\u00028\u00012\u0006\u0010?\u001a\u00020@2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010AJ\u001d\u0010B\u001a\u00028\u00012\u0006\u0010C\u001a\u00020D2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010EJ\u001d\u0010F\u001a\u00028\u00012\u0006\u0010G\u001a\u00020H2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010IJ\u001d\u0010J\u001a\u00028\u00012\u0006\u0010K\u001a\u00020L2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010MJ\u001d\u0010N\u001a\u00028\u00012\u0006\u0010O\u001a\u00020P2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010QJ\u001d\u0010R\u001a\u00028\u00012\u0006\u0010S\u001a\u00020T2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010UJ\u001d\u0010V\u001a\u00028\u00012\u0006\u0010W\u001a\u00020X2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010YJ\u001d\u0010Z\u001a\u00028\u00012\u0006\u0010[\u001a\u00020\\2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010]J\u001d\u0010^\u001a\u00028\u00012\u0006\u0010_\u001a\u00020`2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010a¨\u0006b"}, d2 = {"Lcom/google/devtools/ksp/visitor/KSDefaultVisitor;", "D", "R", "Lcom/google/devtools/ksp/visitor/KSEmptyVisitor;", "()V", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "data", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReference", "reference", "Lcom/google/devtools/ksp/symbol/KSCallableReference;", "(Lcom/google/devtools/ksp/symbol/KSCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassifierReference", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "(Lcom/google/devtools/ksp/symbol/KSClassifierReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Ljava/lang/Object;)Ljava/lang/Object;", "visitDefNonNullReference", "Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "(Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDynamicReference", "Lcom/google/devtools/ksp/symbol/KSDynamicReference;", "(Lcom/google/devtools/ksp/symbol/KSDynamicReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitFile", "file", "Lcom/google/devtools/ksp/symbol/KSFile;", "(Lcom/google/devtools/ksp/symbol/KSFile;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitModifierListOwner", "modifierListOwner", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "(Lcom/google/devtools/ksp/symbol/KSModifierListOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitParenthesizedReference", "Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;", "(Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessor", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "(Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyGetter", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertyGetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertySetter", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertySetter;Ljava/lang/Object;)Ljava/lang/Object;", "visitReferenceElement", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "(Lcom/google/devtools/ksp/symbol/KSReferenceElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "(Lcom/google/devtools/ksp/symbol/KSTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeArgument", "typeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Ljava/lang/Object;)Ljava/lang/Object;", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class KSDefaultVisitor<D, R> extends KSEmptyVisitor<D, R> {
    public R visitAnnotated(@NotNull KSAnnotated kSAnnotated, D d2) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        visitNode(kSAnnotated, d2);
        return super.visitAnnotated(kSAnnotated, d2);
    }

    public R visitAnnotation(@NotNull KSAnnotation kSAnnotation, D d2) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        visitNode(kSAnnotation, d2);
        return super.visitAnnotation(kSAnnotation, d2);
    }

    public R visitCallableReference(@NotNull KSCallableReference kSCallableReference, D d2) {
        Intrinsics.checkNotNullParameter(kSCallableReference, "reference");
        visitReferenceElement(kSCallableReference, d2);
        return super.visitCallableReference(kSCallableReference, d2);
    }

    public R visitClassDeclaration(@NotNull KSClassDeclaration kSClassDeclaration, D d2) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        visitDeclaration(kSClassDeclaration, d2);
        visitDeclarationContainer(kSClassDeclaration, d2);
        return super.visitClassDeclaration(kSClassDeclaration, d2);
    }

    public R visitClassifierReference(@NotNull KSClassifierReference kSClassifierReference, D d2) {
        Intrinsics.checkNotNullParameter(kSClassifierReference, "reference");
        visitReferenceElement(kSClassifierReference, d2);
        return super.visitClassifierReference(kSClassifierReference, d2);
    }

    public R visitDeclaration(@NotNull KSDeclaration kSDeclaration, D d2) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        visitAnnotated(kSDeclaration, d2);
        visitModifierListOwner(kSDeclaration, d2);
        return super.visitDeclaration(kSDeclaration, d2);
    }

    public R visitDeclarationContainer(@NotNull KSDeclarationContainer kSDeclarationContainer, D d2) {
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        visitNode(kSDeclarationContainer, d2);
        return super.visitDeclarationContainer(kSDeclarationContainer, d2);
    }

    public R visitDefNonNullReference(@NotNull KSDefNonNullReference kSDefNonNullReference, D d2) {
        Intrinsics.checkNotNullParameter(kSDefNonNullReference, "reference");
        visitReferenceElement(kSDefNonNullReference, d2);
        return super.visitDefNonNullReference(kSDefNonNullReference, d2);
    }

    public R visitDynamicReference(@NotNull KSDynamicReference kSDynamicReference, D d2) {
        Intrinsics.checkNotNullParameter(kSDynamicReference, "reference");
        visitReferenceElement(kSDynamicReference, d2);
        return super.visitDynamicReference(kSDynamicReference, d2);
    }

    public R visitFile(@NotNull KSFile kSFile, D d2) {
        Intrinsics.checkNotNullParameter(kSFile, StringLookupFactory.KEY_FILE);
        visitAnnotated(kSFile, d2);
        visitDeclarationContainer(kSFile, d2);
        return super.visitFile(kSFile, d2);
    }

    public R visitFunctionDeclaration(@NotNull KSFunctionDeclaration kSFunctionDeclaration, D d2) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "function");
        visitDeclaration(kSFunctionDeclaration, d2);
        visitDeclarationContainer(kSFunctionDeclaration, d2);
        return super.visitFunctionDeclaration(kSFunctionDeclaration, d2);
    }

    public R visitModifierListOwner(@NotNull KSModifierListOwner kSModifierListOwner, D d2) {
        Intrinsics.checkNotNullParameter(kSModifierListOwner, "modifierListOwner");
        visitNode(kSModifierListOwner, d2);
        return super.visitModifierListOwner(kSModifierListOwner, d2);
    }

    public R visitParenthesizedReference(@NotNull KSParenthesizedReference kSParenthesizedReference, D d2) {
        Intrinsics.checkNotNullParameter(kSParenthesizedReference, "reference");
        visitReferenceElement(kSParenthesizedReference, d2);
        return super.visitParenthesizedReference(kSParenthesizedReference, d2);
    }

    public R visitPropertyAccessor(@NotNull KSPropertyAccessor kSPropertyAccessor, D d2) {
        Intrinsics.checkNotNullParameter(kSPropertyAccessor, "accessor");
        visitModifierListOwner(kSPropertyAccessor, d2);
        visitAnnotated(kSPropertyAccessor, d2);
        return super.visitPropertyAccessor(kSPropertyAccessor, d2);
    }

    public R visitPropertyDeclaration(@NotNull KSPropertyDeclaration kSPropertyDeclaration, D d2) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        visitDeclaration(kSPropertyDeclaration, d2);
        return super.visitPropertyDeclaration(kSPropertyDeclaration, d2);
    }

    public R visitPropertyGetter(@NotNull KSPropertyGetter kSPropertyGetter, D d2) {
        Intrinsics.checkNotNullParameter(kSPropertyGetter, "getter");
        visitPropertyAccessor(kSPropertyGetter, d2);
        return super.visitPropertyGetter(kSPropertyGetter, d2);
    }

    public R visitPropertySetter(@NotNull KSPropertySetter kSPropertySetter, D d2) {
        Intrinsics.checkNotNullParameter(kSPropertySetter, "setter");
        visitPropertyAccessor(kSPropertySetter, d2);
        return super.visitPropertySetter(kSPropertySetter, d2);
    }

    public R visitReferenceElement(@NotNull KSReferenceElement kSReferenceElement, D d2) {
        Intrinsics.checkNotNullParameter(kSReferenceElement, "element");
        visitNode(kSReferenceElement, d2);
        return super.visitReferenceElement(kSReferenceElement, d2);
    }

    public R visitTypeAlias(@NotNull KSTypeAlias kSTypeAlias, D d2) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "typeAlias");
        visitDeclaration(kSTypeAlias, d2);
        return super.visitTypeAlias(kSTypeAlias, d2);
    }

    public R visitTypeArgument(@NotNull KSTypeArgument kSTypeArgument, D d2) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "typeArgument");
        visitAnnotated(kSTypeArgument, d2);
        return super.visitTypeArgument(kSTypeArgument, d2);
    }

    public R visitTypeParameter(@NotNull KSTypeParameter kSTypeParameter, D d2) {
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        visitDeclaration(kSTypeParameter, d2);
        return super.visitTypeParameter(kSTypeParameter, d2);
    }

    public R visitTypeReference(@NotNull KSTypeReference kSTypeReference, D d2) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        visitAnnotated(kSTypeReference, d2);
        visitModifierListOwner(kSTypeReference, d2);
        return super.visitTypeReference(kSTypeReference, d2);
    }

    public R visitValueArgument(@NotNull KSValueArgument kSValueArgument, D d2) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        visitAnnotated(kSValueArgument, d2);
        return super.visitValueArgument(kSValueArgument, d2);
    }

    public R visitValueParameter(@NotNull KSValueParameter kSValueParameter, D d2) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        visitAnnotated(kSValueParameter, d2);
        return super.visitValueParameter(kSValueParameter, d2);
    }
}
