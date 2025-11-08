package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.KspExperimental;
import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSDeclarationContainer;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSPropertyAccessor;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.Modifier;
import com.google.devtools.ksp.symbol.Variance;
import java.util.Set;
import kotlin.Metadata;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH'J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u0017\u001a\u00020\u0018H'J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u001a\u001a\u00020\u001bH'J \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007H'J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010#\u001a\u00020\u001dH'J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010$\u001a\u00020%H'J\u0012\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u001dH'J\u0012\u0010&\u001a\u0004\u0018\u00010\u00182\u0006\u0010$\u001a\u00020%H'J\u0010\u0010'\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0018H&J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u001dH'J\u0012\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020*H'J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00102\u0006\u0010\u0017\u001a\u00020\u0018H'J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00180\u00102\u0006\u0010.\u001a\u00020\u0018H'J\u001c\u0010/\u001a\u0004\u0018\u00010*2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u001e\u001a\u00020\u001fH&J \u00100\u001a\b\u0012\u0004\u0012\u0002010\u00102\u0006\u0010.\u001a\u00020\u00182\b\b\u0002\u00102\u001a\u00020\u001fH&J\u0018\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u000207H&J\u0010\u00108\u001a\u00020\u001f2\u0006\u0010\b\u001a\u00020\tH'J\u0012\u00109\u001a\u0004\u0018\u00010\u00152\u0006\u0010:\u001a\u00020\u0015H'J\u0012\u0010;\u001a\u0004\u0018\u00010\u00152\u0006\u0010<\u001a\u00020\u0015H'J\u0012\u0010=\u001a\u0004\u0018\u00010\u00182\u0006\u0010\r\u001a\u00020\u000eH'J\u0018\u0010>\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u000eH&J \u0010>\u001a\u00020\u001f2\u0006\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006BÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/Resolver;", "", "builtIns", "Lcom/google/devtools/ksp/processing/KSBuiltIns;", "getBuiltIns", "()Lcom/google/devtools/ksp/processing/KSBuiltIns;", "createKSTypeReferenceFromKSType", "Lcom/google/devtools/ksp/symbol/KSTypeReference;", "type", "Lcom/google/devtools/ksp/symbol/KSType;", "effectiveJavaModifiers", "", "Lcom/google/devtools/ksp/symbol/Modifier;", "declaration", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "getAllFiles", "Lkotlin/sequences/Sequence;", "Lcom/google/devtools/ksp/symbol/KSFile;", "getClassDeclarationByName", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "name", "Lcom/google/devtools/ksp/symbol/KSName;", "getDeclarationsFromPackage", "packageName", "", "getDeclarationsInSourceOrder", "container", "Lcom/google/devtools/ksp/symbol/KSDeclarationContainer;", "getFunctionDeclarationsByName", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "includeTopLevel", "", "getJavaWildcard", "reference", "getJvmCheckedException", "function", "accessor", "Lcom/google/devtools/ksp/symbol/KSPropertyAccessor;", "getJvmName", "getKSNameFromString", "getNewFiles", "getOwnerJvmClassName", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getPackageAnnotations", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "getPackagesWithAnnotation", "annotationName", "getPropertyDeclarationByName", "getSymbolsWithAnnotation", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "inDepth", "getTypeArgument", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "typeRef", "variance", "Lcom/google/devtools/ksp/symbol/Variance;", "isJavaRawType", "mapJavaNameToKotlin", "javaName", "mapKotlinNameToJava", "kotlinName", "mapToJvmSignature", "overrides", "overrider", "overridee", "containingClass", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Resolver {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Sequence getFunctionDeclarationsByName$default(Resolver resolver, KSName kSName, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                z2 = false;
            }
            return resolver.getFunctionDeclarationsByName(kSName, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getFunctionDeclarationsByName");
    }

    static /* synthetic */ KSPropertyDeclaration getPropertyDeclarationByName$default(Resolver resolver, KSName kSName, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                z2 = false;
            }
            return resolver.getPropertyDeclarationByName(kSName, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPropertyDeclarationByName");
    }

    static /* synthetic */ Sequence getSymbolsWithAnnotation$default(Resolver resolver, String str, boolean z2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                z2 = false;
            }
            return resolver.getSymbolsWithAnnotation(str, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSymbolsWithAnnotation");
    }

    @NotNull
    KSTypeReference createKSTypeReferenceFromKSType(@NotNull KSType kSType);

    @NotNull
    @KspExperimental
    Set<Modifier> effectiveJavaModifiers(@NotNull KSDeclaration kSDeclaration);

    @NotNull
    Sequence<KSFile> getAllFiles();

    @NotNull
    KSBuiltIns getBuiltIns();

    @Nullable
    KSClassDeclaration getClassDeclarationByName(@NotNull KSName kSName);

    @NotNull
    @KspExperimental
    Sequence<KSDeclaration> getDeclarationsFromPackage(@NotNull String str);

    @NotNull
    @KspExperimental
    Sequence<KSDeclaration> getDeclarationsInSourceOrder(@NotNull KSDeclarationContainer kSDeclarationContainer);

    @NotNull
    Sequence<KSFunctionDeclaration> getFunctionDeclarationsByName(@NotNull KSName kSName, boolean z2);

    @NotNull
    @KspExperimental
    KSTypeReference getJavaWildcard(@NotNull KSTypeReference kSTypeReference);

    @NotNull
    @KspExperimental
    Sequence<KSType> getJvmCheckedException(@NotNull KSFunctionDeclaration kSFunctionDeclaration);

    @NotNull
    @KspExperimental
    Sequence<KSType> getJvmCheckedException(@NotNull KSPropertyAccessor kSPropertyAccessor);

    @Nullable
    @KspExperimental
    String getJvmName(@NotNull KSFunctionDeclaration kSFunctionDeclaration);

    @Nullable
    @KspExperimental
    String getJvmName(@NotNull KSPropertyAccessor kSPropertyAccessor);

    @NotNull
    KSName getKSNameFromString(@NotNull String str);

    @NotNull
    Sequence<KSFile> getNewFiles();

    @Nullable
    @KspExperimental
    String getOwnerJvmClassName(@NotNull KSFunctionDeclaration kSFunctionDeclaration);

    @Nullable
    @KspExperimental
    String getOwnerJvmClassName(@NotNull KSPropertyDeclaration kSPropertyDeclaration);

    @NotNull
    @KspExperimental
    Sequence<KSAnnotation> getPackageAnnotations(@NotNull String str);

    @NotNull
    @KspExperimental
    Sequence<String> getPackagesWithAnnotation(@NotNull String str);

    @Nullable
    KSPropertyDeclaration getPropertyDeclarationByName(@NotNull KSName kSName, boolean z2);

    @NotNull
    Sequence<KSAnnotated> getSymbolsWithAnnotation(@NotNull String str, boolean z2);

    @NotNull
    KSTypeArgument getTypeArgument(@NotNull KSTypeReference kSTypeReference, @NotNull Variance variance);

    @KspExperimental
    boolean isJavaRawType(@NotNull KSType kSType);

    @Nullable
    @KspExperimental
    KSName mapJavaNameToKotlin(@NotNull KSName kSName);

    @Nullable
    @KspExperimental
    KSName mapKotlinNameToJava(@NotNull KSName kSName);

    @Nullable
    @KspExperimental
    String mapToJvmSignature(@NotNull KSDeclaration kSDeclaration);

    boolean overrides(@NotNull KSDeclaration kSDeclaration, @NotNull KSDeclaration kSDeclaration2);

    boolean overrides(@NotNull KSDeclaration kSDeclaration, @NotNull KSDeclaration kSDeclaration2, @NotNull KSClassDeclaration kSClassDeclaration);
}
