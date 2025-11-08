package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.text.lookup.StringLookupFactory;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\bJ\u001d\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\fJ\u001d\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0010J\u001d\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0014J\u001d\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001bJ\u001d\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020!2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010)J\u001d\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010-J\u001d\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u0002002\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00101J\u001d\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00105J\u001d\u00106\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u0002072\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00020\u00022\u0006\u0010:\u001a\u00020;2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010<J\u001d\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00020\u00022\u0006\u0010B\u001a\u00020C2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010DJ\u001d\u0010E\u001a\u00020\u00022\u0006\u0010F\u001a\u00020G2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010HJ\u001d\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020K2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010LJ\u001d\u0010M\u001a\u00020\u00022\u0006\u0010N\u001a\u00020O2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010PJ\u001d\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020S2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010TJ\u001d\u0010U\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010XJ\u001d\u0010Y\u001a\u00020\u00022\u0006\u0010Z\u001a\u00020[2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\\J\u001d\u0010]\u001a\u00020\u00022\u0006\u0010^\u001a\u00020_2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010`J\u001d\u0010a\u001a\u00020\u00022\u0006\u0010b\u001a\u00020c2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010d¨\u0006e"}, d2 = {"Lcom/google/devtools/ksp/symbol/KSVisitorVoid;", "Lcom/google/devtools/ksp/symbol/KSVisitor;", "", "()V", "visitAnnotated", "annotated", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "data", "(Lcom/google/devtools/ksp/symbol/KSAnnotated;Lkotlin/Unit;)V", "visitAnnotation", "annotation", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Lkotlin/Unit;)V", "visitCallableReference", "reference", "Lcom/google/devtools/ksp/symbol/KSCallableReference;", "(Lcom/google/devtools/ksp/symbol/KSCallableReference;Lkotlin/Unit;)V", "visitClassDeclaration", "classDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSClassDeclaration;Lkotlin/Unit;)V", "visitClassifierReference", "Lcom/google/devtools/ksp/symbol/KSClassifierReference;", "(Lcom/google/devtools/ksp/symbol/KSClassifierReference;Lkotlin/Unit;)V", "visitDeclaration", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSDeclaration;Lkotlin/Unit;)V", "visitDeclarationContainer", "declarationContainer", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "(Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;Lkotlin/Unit;)V", "visitDefNonNullReference", "Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;", "(Lcom/google/devtools/ksp/symbol/KSDefNonNullReference;Lkotlin/Unit;)V", "visitDynamicReference", "Lcom/google/devtools/ksp/symbol/KSDynamicReference;", "(Lcom/google/devtools/ksp/symbol/KSDynamicReference;Lkotlin/Unit;)V", "visitFile", "file", "Lcom/google/devtools/ksp/symbol/KSFile;", "(Lcom/google/devtools/ksp/symbol/KSFile;Lkotlin/Unit;)V", "visitFunctionDeclaration", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Lkotlin/Unit;)V", "visitModifierListOwner", "modifierListOwner", "Lcom/google/devtools/ksp/symbol/KSModifierListOwner;", "(Lcom/google/devtools/ksp/symbol/KSModifierListOwner;Lkotlin/Unit;)V", "visitNode", "node", "Lcom/google/devtools/ksp/symbol/KSNode;", "(Lcom/google/devtools/ksp/symbol/KSNode;Lkotlin/Unit;)V", "visitParenthesizedReference", "Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;", "(Lcom/google/devtools/ksp/symbol/KSParenthesizedReference;Lkotlin/Unit;)V", "visitPropertyAccessor", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "(Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;Lkotlin/Unit;)V", "visitPropertyDeclaration", "property", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "(Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;Lkotlin/Unit;)V", "visitPropertyGetter", "getter", "Lcom/google/devtools/ksp/symbol/KSPropertyGetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertyGetter;Lkotlin/Unit;)V", "visitPropertySetter", "setter", "Lcom/google/devtools/ksp/symbol/KSPropertySetter;", "(Lcom/google/devtools/ksp/symbol/KSPropertySetter;Lkotlin/Unit;)V", "visitReferenceElement", "element", "Lcom/google/devtools/ksp/symbol/KSReferenceElement;", "(Lcom/google/devtools/ksp/symbol/KSReferenceElement;Lkotlin/Unit;)V", "visitTypeAlias", "typeAlias", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "(Lcom/google/devtools/ksp/symbol/KSTypeAlias;Lkotlin/Unit;)V", "visitTypeArgument", "typeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "(Lcom/google/devtools/ksp/symbol/KSTypeArgument;Lkotlin/Unit;)V", "visitTypeParameter", "typeParameter", "Lcom/google/devtools/ksp/symbol/KSTypeParameter;", "(Lcom/google/devtools/ksp/symbol/KSTypeParameter;Lkotlin/Unit;)V", "visitTypeReference", "typeReference", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "(Lcom/google/devtools/ksp/symbol/KSTypeReference;Lkotlin/Unit;)V", "visitValueArgument", "valueArgument", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "(Lcom/google/devtools/ksp/symbol/KSValueArgument;Lkotlin/Unit;)V", "visitValueParameter", "valueParameter", "Lcom/google/devtools/ksp/symbol/KSValueParameter;", "(Lcom/google/devtools/ksp/symbol/KSValueParameter;Lkotlin/Unit;)V", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class KSVisitorVoid implements KSVisitor<Unit, Unit> {
    public void visitAnnotated(@NotNull KSAnnotated kSAnnotated, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "annotated");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitAnnotation(@NotNull KSAnnotation kSAnnotation, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSAnnotation, "annotation");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitCallableReference(@NotNull KSCallableReference kSCallableReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSCallableReference, "reference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitClassDeclaration(@NotNull KSClassDeclaration kSClassDeclaration, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "classDeclaration");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitClassifierReference(@NotNull KSClassifierReference kSClassifierReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSClassifierReference, "reference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitDeclaration(@NotNull KSDeclaration kSDeclaration, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "declaration");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitDeclarationContainer(@NotNull KSDeclarationContainer kSDeclarationContainer, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSDeclarationContainer, "declarationContainer");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitDefNonNullReference(@NotNull KSDefNonNullReference kSDefNonNullReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSDefNonNullReference, "reference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitDynamicReference(@NotNull KSDynamicReference kSDynamicReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSDynamicReference, "reference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitFile(@NotNull KSFile kSFile, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSFile, StringLookupFactory.KEY_FILE);
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitFunctionDeclaration(@NotNull KSFunctionDeclaration kSFunctionDeclaration, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "function");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitModifierListOwner(@NotNull KSModifierListOwner kSModifierListOwner, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSModifierListOwner, "modifierListOwner");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitNode(@NotNull KSNode kSNode, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSNode, "node");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitParenthesizedReference(@NotNull KSParenthesizedReference kSParenthesizedReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSParenthesizedReference, "reference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitPropertyAccessor(@NotNull KSPropertyAccessor kSPropertyAccessor, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSPropertyAccessor, "accessor");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitPropertyDeclaration(@NotNull KSPropertyDeclaration kSPropertyDeclaration, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "property");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitPropertyGetter(@NotNull KSPropertyGetter kSPropertyGetter, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSPropertyGetter, "getter");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitPropertySetter(@NotNull KSPropertySetter kSPropertySetter, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSPropertySetter, "setter");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitReferenceElement(@NotNull KSReferenceElement kSReferenceElement, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSReferenceElement, "element");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitTypeAlias(@NotNull KSTypeAlias kSTypeAlias, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "typeAlias");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitTypeArgument(@NotNull KSTypeArgument kSTypeArgument, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSTypeArgument, "typeArgument");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitTypeParameter(@NotNull KSTypeParameter kSTypeParameter, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSTypeParameter, "typeParameter");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitTypeReference(@NotNull KSTypeReference kSTypeReference, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSTypeReference, "typeReference");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitValueArgument(@NotNull KSValueArgument kSValueArgument, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "valueArgument");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public void visitValueParameter(@NotNull KSValueParameter kSValueParameter, @NotNull Unit unit) {
        Intrinsics.checkNotNullParameter(kSValueParameter, "valueParameter");
        Intrinsics.checkNotNullParameter(unit, "data");
    }

    public /* bridge */ /* synthetic */ Object visitAnnotated(KSAnnotated kSAnnotated, Object obj) {
        visitAnnotated(kSAnnotated, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitAnnotation(KSAnnotation kSAnnotation, Object obj) {
        visitAnnotation(kSAnnotation, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitCallableReference(KSCallableReference kSCallableReference, Object obj) {
        visitCallableReference(kSCallableReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitClassDeclaration(KSClassDeclaration kSClassDeclaration, Object obj) {
        visitClassDeclaration(kSClassDeclaration, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitClassifierReference(KSClassifierReference kSClassifierReference, Object obj) {
        visitClassifierReference(kSClassifierReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitDeclaration(KSDeclaration kSDeclaration, Object obj) {
        visitDeclaration(kSDeclaration, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitDeclarationContainer(KSDeclarationContainer kSDeclarationContainer, Object obj) {
        visitDeclarationContainer(kSDeclarationContainer, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitDefNonNullReference(KSDefNonNullReference kSDefNonNullReference, Object obj) {
        visitDefNonNullReference(kSDefNonNullReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitDynamicReference(KSDynamicReference kSDynamicReference, Object obj) {
        visitDynamicReference(kSDynamicReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitFile(KSFile kSFile, Object obj) {
        visitFile(kSFile, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, Object obj) {
        visitFunctionDeclaration(kSFunctionDeclaration, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitModifierListOwner(KSModifierListOwner kSModifierListOwner, Object obj) {
        visitModifierListOwner(kSModifierListOwner, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitNode(KSNode kSNode, Object obj) {
        visitNode(kSNode, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitParenthesizedReference(KSParenthesizedReference kSParenthesizedReference, Object obj) {
        visitParenthesizedReference(kSParenthesizedReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitPropertyAccessor(KSPropertyAccessor kSPropertyAccessor, Object obj) {
        visitPropertyAccessor(kSPropertyAccessor, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitPropertyDeclaration(KSPropertyDeclaration kSPropertyDeclaration, Object obj) {
        visitPropertyDeclaration(kSPropertyDeclaration, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitPropertyGetter(KSPropertyGetter kSPropertyGetter, Object obj) {
        visitPropertyGetter(kSPropertyGetter, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitPropertySetter(KSPropertySetter kSPropertySetter, Object obj) {
        visitPropertySetter(kSPropertySetter, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitReferenceElement(KSReferenceElement kSReferenceElement, Object obj) {
        visitReferenceElement(kSReferenceElement, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitTypeAlias(KSTypeAlias kSTypeAlias, Object obj) {
        visitTypeAlias(kSTypeAlias, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitTypeArgument(KSTypeArgument kSTypeArgument, Object obj) {
        visitTypeArgument(kSTypeArgument, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitTypeParameter(KSTypeParameter kSTypeParameter, Object obj) {
        visitTypeParameter(kSTypeParameter, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitTypeReference(KSTypeReference kSTypeReference, Object obj) {
        visitTypeReference(kSTypeReference, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitValueArgument(KSValueArgument kSValueArgument, Object obj) {
        visitValueArgument(kSValueArgument, (Unit) obj);
        return Unit.INSTANCE;
    }

    public /* bridge */ /* synthetic */ Object visitValueParameter(KSValueParameter kSValueParameter, Object obj) {
        visitValueParameter(kSValueParameter, (Unit) obj);
        return Unit.INSTANCE;
    }
}
