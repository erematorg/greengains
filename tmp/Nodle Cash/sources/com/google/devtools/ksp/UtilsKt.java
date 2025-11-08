package com.google.devtools.ksp;

import androidx.exifinterface.media.ExifInterface;
import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.symbol.ClassKind;
import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSDeclaration;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSName;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSPropertyDeclaration;
import com.google.devtools.ksp.symbol.KSPropertyGetter;
import com.google.devtools.ksp.symbol.KSPropertySetter;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeAlias;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeParameter;
import com.google.devtools.ksp.symbol.KSValueArgument;
import com.google.devtools.ksp.symbol.Modifier;
import com.google.devtools.ksp.symbol.Origin;
import com.google.devtools.ksp.symbol.Visibility;
import com.google.devtools.ksp.visitor.KSValidateVisitor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000Ø\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a \u0010\u0015\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a$\u0010\u0015\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u0011H\u0002\u001a(\u0010\u001b\u001a\u0012\u0012\u0002\b\u0003 \u001c*\b\u0012\u0002\b\u0003\u0018\u00010\u00140\u0014*\u00020\n2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a4\u0010\u001d\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u001c*\b\u0012\u0002\b\u0003\u0018\u00010\u00140\u00140\b*\b\u0012\u0004\u0012\u00020\n0\b2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\f\u0010\u001e\u001a\u00020\u001f*\u00020\u0011H\u0002\u001a%\u0010 \u001a\u0002H!\"\u0004\b\u0000\u0010!*\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0\u0014H\u0002¢\u0006\u0002\u0010#\u001a\f\u0010$\u001a\u00020%*\u00020\u0011H\u0002\u001a\f\u0010&\u001a\u00020'*\u00020\u0011H\u0002\u001a\f\u0010(\u001a\u00020)*\u00020\u0011H\u0002\u001a\f\u0010*\u001a\u0004\u0018\u00010+*\u00020,\u001a\u0018\u0010-\u001a\u00020.*\u00020\u00122\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0003\u001a\n\u00100\u001a\u00020+*\u000201\u001a\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020+\u001a*\u00104\u001a\b\u0012\u0004\u0012\u0002H!03\"\b\b\u0000\u0010!*\u000205*\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H!08H\u0007\u001a\u0017\u00109\u001a\u0004\u0018\u00010+\"\u0006\b\u0000\u0010!\u0018\u0001*\u00020:H\b\u001a\u0014\u00109\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001\u001a\u0010\u0010<\u001a\b\u0012\u0004\u0012\u00020=03*\u00020+\u001a\u0010\u0010>\u001a\b\u0012\u0004\u0012\u00020=03*\u00020+\u001a\u0010\u0010?\u001a\b\u0012\u0004\u0012\u00020@03*\u00020+\u001a\"\u0010A\u001a\b\u0012\u0004\u0012\u00020=03*\u00020:2\u0006\u0010;\u001a\u00020\u00012\b\b\u0002\u0010B\u001a\u00020C\u001a\u0016\u0010D\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020EH\u0007\u001a\u0016\u0010D\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001H\u0007\u001a\u0016\u0010F\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020EH\u0007\u001a\u0016\u0010F\u001a\u0004\u0018\u00010+*\u00020:2\u0006\u0010;\u001a\u00020\u0001H\u0007\u001a\u001e\u0010G\u001a\u0004\u0018\u00010@*\u00020:2\u0006\u0010;\u001a\u00020\u00012\b\b\u0002\u0010B\u001a\u00020C\u001a\n\u0010H\u001a\u00020I*\u00020,\u001a\n\u0010J\u001a\u00020C*\u00020+\u001a\n\u0010J\u001a\u00020C*\u00020@\u001a$\u0010K\u001a\u00020C\"\b\b\u0000\u0010!*\u000205*\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H!08H\u0007\u001a\n\u0010L\u001a\u00020C*\u00020=\u001a\n\u0010M\u001a\u00020C*\u00020N\u001a\n\u0010O\u001a\u00020C*\u00020,\u001a\n\u0010P\u001a\u00020C*\u00020,\u001a\n\u0010Q\u001a\u00020C*\u00020,\u001a\n\u0010R\u001a\u00020C*\u00020,\u001a\n\u0010S\u001a\u00020C*\u00020,\u001a\n\u0010T\u001a\u00020C*\u00020,\u001a\n\u0010U\u001a\u00020C*\u00020,\u001a\u0012\u0010V\u001a\u00020C*\u00020,2\u0006\u0010W\u001a\u00020,\u001a)\u0010X\u001a\u0002H!\"\b\b\u0000\u0010!*\u000205*\u00020\u00122\f\u0010Y\u001a\b\u0012\u0004\u0012\u0002H!0\u0014H\u0003¢\u0006\u0002\u0010Z\u001a9\u0010[\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\\*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110^H\u0002¢\u0006\u0002\u0010_\u001a(\u0010`\u001a\u00020C*\u00020\u00042\u001c\b\u0002\u0010a\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020C0b\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\u0004\u0018\u00010\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\r\u001a\u0004\u0018\u00010\n*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006c"}, d2 = {"ExceptionMessage", "", "containingFile", "Lcom/google/devtools/ksp/symbol/KSFile;", "Lcom/google/devtools/ksp/symbol/KSNode;", "getContainingFile", "(Lcom/google/devtools/ksp/symbol/KSNode;)Lcom/google/devtools/ksp/symbol/KSFile;", "innerArguments", "", "Lcom/google/devtools/ksp/symbol/KSTypeArgument;", "Lcom/google/devtools/ksp/symbol/KSType;", "getInnerArguments", "(Lcom/google/devtools/ksp/symbol/KSType;)Ljava/util/List;", "outerType", "getOuterType", "(Lcom/google/devtools/ksp/symbol/KSType;)Lcom/google/devtools/ksp/symbol/KSType;", "asAnnotation", "", "Lcom/google/devtools/ksp/symbol/KSAnnotation;", "annotationInterface", "Ljava/lang/Class;", "asArray", "method", "Ljava/lang/reflect/Method;", "proxyClass", "asByte", "", "asClass", "kotlin.jvm.PlatformType", "asClasses", "asDouble", "", "asEnum", "T", "returnType", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;", "asFloat", "", "asLong", "", "asShort", "", "closestClassDeclaration", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "Lcom/google/devtools/ksp/symbol/KSDeclaration;", "createInvocationHandler", "Ljava/lang/reflect/InvocationHandler;", "clazz", "findActualType", "Lcom/google/devtools/ksp/symbol/KSTypeAlias;", "getAllSuperTypes", "Lkotlin/sequences/Sequence;", "getAnnotationsByType", "", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "annotationKClass", "Lkotlin/reflect/KClass;", "getClassDeclarationByName", "Lcom/google/devtools/ksp/processing/Resolver;", "name", "getConstructors", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "getDeclaredFunctions", "getDeclaredProperties", "Lcom/google/devtools/ksp/symbol/KSPropertyDeclaration;", "getFunctionDeclarationsByName", "includeTopLevel", "", "getJavaClassByName", "Lcom/google/devtools/ksp/symbol/KSName;", "getKotlinClassByName", "getPropertyDeclarationByName", "getVisibility", "Lcom/google/devtools/ksp/symbol/Visibility;", "isAbstract", "isAnnotationPresent", "isConstructor", "isDefault", "Lcom/google/devtools/ksp/symbol/KSValueArgument;", "isInternal", "isJavaPackagePrivate", "isLocal", "isOpen", "isPrivate", "isProtected", "isPublic", "isVisibleFrom", "other", "toAnnotation", "annotationClass", "(Lcom/google/devtools/ksp/symbol/KSAnnotation;Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "toArray", "", "valueProvider", "Lkotlin/Function1;", "(Ljava/util/List;Ljava/lang/reflect/Method;Lkotlin/jvm/functions/Function1;)[Ljava/lang/Object;", "validate", "predicate", "Lkotlin/Function2;", "api"}, k = 2, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nutils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 utils.kt\ncom/google/devtools/ksp/UtilsKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 7 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,537:1\n473#2:538\n473#2:539\n37#3,2:540\n37#3,2:542\n1#4:544\n1#4:560\n1#4:563\n1#4:566\n1#4:569\n1#4:574\n1#4:577\n1#4:580\n1#4:583\n1#4:586\n1#4:589\n1549#5:545\n1620#5,3:546\n2624#5,3:549\n1549#5:552\n1620#5,3:553\n223#5,2:556\n72#6,2:558\n72#6,2:561\n72#6,2:564\n72#6,2:567\n72#6,2:570\n72#6,2:575\n72#6,2:578\n72#6,2:581\n72#6,2:584\n72#6,2:587\n1109#7,2:572\n*S KotlinDebug\n*F\n+ 1 utils.kt\ncom/google/devtools/ksp/UtilsKt\n*L\n94#1:538\n104#1:539\n461#1:540,2\n462#1:542,2\n374#1:560\n383#1:563\n390#1:566\n394#1:569\n397#1:574\n412#1:577\n416#1:580\n420#1:583\n424#1:586\n428#1:589\n528#1:545\n528#1:546,3\n360#1:549,3\n362#1:552\n362#1:553,3\n369#1:556,2\n374#1:558,2\n383#1:561,2\n390#1:564,2\n394#1:567,2\n397#1:570,2\n412#1:575,2\n416#1:578,2\n420#1:581,2\n424#1:584,2\n428#1:587,2\n404#1:572,2\n*E\n"})
public final class UtilsKt {
    @NotNull
    public static final String ExceptionMessage = "please file a bug at https://github.com/google/ksp/issues/new";

    /* access modifiers changed from: private */
    @KspExperimental
    public static final Object asAnnotation(KSAnnotation kSAnnotation, Class<?> cls) {
        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, createInvocationHandler(kSAnnotation, cls));
        Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type java.lang.reflect.Proxy");
        return (Proxy) newProxyInstance;
    }

    /* access modifiers changed from: private */
    @KspExperimental
    public static final Object asArray(List<?> list, Method method, Class<?> cls) {
        String name = method.getReturnType().getComponentType().getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals(SchemaSymbols.ATTVAL_DOUBLE)) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Double>");
                    return CollectionsKt___CollectionsKt.toDoubleArray(list);
                }
                break;
            case -530663260:
                if (name.equals("java.lang.Class")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<com.google.devtools.ksp.symbol.KSType>");
                    return asClasses(list, cls).toArray(new Class[0]);
                }
                break;
            case 104431:
                if (name.equals("int")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
                    return CollectionsKt.toIntArray(list);
                }
                break;
            case 3039496:
                if (name.equals(SchemaSymbols.ATTVAL_BYTE)) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Byte>");
                    return CollectionsKt.toByteArray(list);
                }
                break;
            case 3052374:
                if (name.equals("char")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Char>");
                    return CollectionsKt___CollectionsKt.toCharArray(list);
                }
                break;
            case 3327612:
                if (name.equals("long")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Long>");
                    return CollectionsKt___CollectionsKt.toLongArray(list);
                }
                break;
            case 64711720:
                if (name.equals("boolean")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Boolean>");
                    return CollectionsKt.toBooleanArray(list);
                }
                break;
            case 97526364:
                if (name.equals("float")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Float>");
                    return CollectionsKt___CollectionsKt.toFloatArray(list);
                }
                break;
            case 109413500:
                if (name.equals(SchemaSymbols.ATTVAL_SHORT)) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Short>");
                    return CollectionsKt___CollectionsKt.toShortArray(list);
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
                    return list.toArray(new String[0]);
                }
                break;
        }
        if (method.getReturnType().getComponentType().isEnum()) {
            return toArray(list, method, new UtilsKt$asArray$1(method));
        }
        if (method.getReturnType().getComponentType().isAnnotation()) {
            return toArray(list, method, new UtilsKt$asArray$2(method));
        }
        throw new IllegalStateException("Unable to process type ".concat(method.getReturnType().getComponentType().getName()));
    }

    /* access modifiers changed from: private */
    public static final byte asByte(Object obj) {
        if (obj instanceof Integer) {
            return (byte) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Byte");
        return ((Byte) obj).byteValue();
    }

    @KspExperimental
    private static final Class<?> asClass(KSType kSType, Class<?> cls) {
        try {
            KSName qualifiedName = kSType.getDeclaration().getQualifiedName();
            Intrinsics.checkNotNull(qualifiedName);
            return Class.forName(qualifiedName.asString(), true, cls.getClassLoader());
        } catch (Exception e3) {
            throw new KSTypeNotPresentException(kSType, e3);
        }
    }

    @KspExperimental
    private static final List<Class<?>> asClasses(List<? extends KSType> list, Class<?> cls) {
        try {
            Iterable<KSType> iterable = list;
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (KSType asClass : iterable) {
                arrayList.add(asClass(asClass, cls));
            }
            return arrayList;
        } catch (Exception e3) {
            throw new KSTypesNotPresentException(list, e3);
        }
    }

    /* access modifiers changed from: private */
    public static final double asDouble(Object obj) {
        if (obj instanceof Integer) {
            return (double) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Double");
        return ((Double) obj).doubleValue();
    }

    /* access modifiers changed from: private */
    public static final <T> T asEnum(Object obj, Class<T> cls) {
        return cls.getDeclaredMethod("valueOf", new Class[]{String.class}).invoke((Object) null, new Object[]{obj instanceof KSType ? ((KSType) obj).getDeclaration().getSimpleName().getShortName() : obj.toString()});
    }

    /* access modifiers changed from: private */
    public static final float asFloat(Object obj) {
        if (obj instanceof Integer) {
            return (float) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Float");
        return ((Float) obj).floatValue();
    }

    /* access modifiers changed from: private */
    public static final long asLong(Object obj) {
        if (obj instanceof Integer) {
            return (long) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Long");
        return ((Long) obj).longValue();
    }

    /* access modifiers changed from: private */
    public static final short asShort(Object obj) {
        if (obj instanceof Integer) {
            return (short) ((Number) obj).intValue();
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Short");
        return ((Short) obj).shortValue();
    }

    @Nullable
    public static final KSClassDeclaration closestClassDeclaration(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        if (kSDeclaration instanceof KSClassDeclaration) {
            return (KSClassDeclaration) kSDeclaration;
        }
        KSDeclaration parentDeclaration = kSDeclaration.getParentDeclaration();
        if (parentDeclaration != null) {
            return closestClassDeclaration(parentDeclaration);
        }
        return null;
    }

    @KspExperimental
    private static final InvocationHandler createInvocationHandler(KSAnnotation kSAnnotation, Class<?> cls) {
        return new a(kSAnnotation, 0, new ConcurrentHashMap(kSAnnotation.getArguments().size()), cls);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0160, code lost:
        r8 = r10.invoke();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object createInvocationHandler$lambda$8(com.google.devtools.ksp.symbol.KSAnnotation r7, java.lang.Class r8, java.util.concurrent.ConcurrentHashMap r9, java.lang.Object r10, java.lang.reflect.Method r11, java.lang.Object[] r12) {
        /*
            java.lang.String r12 = "$this_createInvocationHandler"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r12)
            java.lang.String r12 = "$clazz"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r12)
            java.lang.String r12 = "$cache"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r12)
            java.lang.String r12 = r11.getName()
            java.lang.String r0 = "toString"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
            r1 = 0
            java.lang.String r2 = "getMethods(...)"
            r3 = 0
            if (r12 == 0) goto L_0x00db
            java.util.List r12 = r7.getArguments()
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            boolean r4 = r12 instanceof java.util.Collection
            if (r4 == 0) goto L_0x0033
            r4 = r12
            java.util.Collection r4 = (java.util.Collection) r4
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L_0x0033
            goto L_0x0057
        L_0x0033:
            java.util.Iterator r12 = r12.iterator()
        L_0x0037:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r12.next()
            com.google.devtools.ksp.symbol.KSValueArgument r4 = (com.google.devtools.ksp.symbol.KSValueArgument) r4
            com.google.devtools.ksp.symbol.KSName r4 = r4.getName()
            if (r4 == 0) goto L_0x004e
            java.lang.String r4 = r4.asString()
            goto L_0x004f
        L_0x004e:
            r4 = r3
        L_0x004f:
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)
            if (r4 == 0) goto L_0x0037
            goto L_0x00db
        L_0x0057:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r8 = r8.getCanonicalName()
            r9.append(r8)
            java.util.List r7 = r7.getArguments()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r8 = new java.util.ArrayList
            int r11 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r7, 10)
            r8.<init>(r11)
            java.util.Iterator r7 = r7.iterator()
        L_0x0076:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x00ce
            java.lang.Object r11 = r7.next()
            com.google.devtools.ksp.symbol.KSValueArgument r11 = (com.google.devtools.ksp.symbol.KSValueArgument) r11
            com.google.devtools.ksp.symbol.KSName r11 = r11.getName()
            if (r11 == 0) goto L_0x008d
            java.lang.String r11 = r11.asString()
            goto L_0x008e
        L_0x008d:
            r11 = r3
        L_0x008e:
            java.lang.Class r12 = r10.getClass()
            java.lang.reflect.Method[] r12 = r12.getMethods()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r2)
            int r0 = r12.length
            r4 = r1
        L_0x009b:
            if (r4 >= r0) goto L_0x00ad
            r5 = r12[r4]
            java.lang.String r6 = r5.getName()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r11)
            if (r6 == 0) goto L_0x00aa
            goto L_0x00ae
        L_0x00aa:
            int r4 = r4 + 1
            goto L_0x009b
        L_0x00ad:
            r5 = r3
        L_0x00ae:
            if (r5 == 0) goto L_0x00b5
            java.lang.Object r12 = r5.invoke(r10, r3)
            goto L_0x00b6
        L_0x00b5:
            r12 = r3
        L_0x00b6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r11)
            r11 = 61
            r0.append(r11)
            r0.append(r12)
            java.lang.String r11 = r0.toString()
            r8.add(r11)
            goto L_0x0076
        L_0x00ce:
            java.util.List r7 = kotlin.collections.CollectionsKt.toList(r8)
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            goto L_0x0329
        L_0x00db:
            java.util.List r7 = r7.getArguments()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r7 = r7.iterator()
        L_0x00e5:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x032a
            java.lang.Object r10 = r7.next()
            com.google.devtools.ksp.symbol.KSValueArgument r10 = (com.google.devtools.ksp.symbol.KSValueArgument) r10
            com.google.devtools.ksp.symbol.KSName r12 = r10.getName()
            if (r12 == 0) goto L_0x00fc
            java.lang.String r12 = r12.asString()
            goto L_0x00fd
        L_0x00fc:
            r12 = r3
        L_0x00fd:
            java.lang.String r0 = r11.getName()
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
            if (r12 == 0) goto L_0x00e5
            java.lang.Object r7 = r10.getValue()
            if (r7 != 0) goto L_0x0111
            java.lang.Object r7 = r11.getDefaultValue()
        L_0x0111:
            boolean r10 = r7 instanceof java.lang.reflect.Proxy
            if (r10 == 0) goto L_0x0117
            goto L_0x0329
        L_0x0117:
            boolean r10 = r7 instanceof java.util.List
            if (r10 == 0) goto L_0x013e
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$1 r10 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$1
            r10.<init>(r7, r11, r8)
            kotlin.Pair r8 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r8.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r8)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r10.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r8, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x013b:
            r7 = r8
            goto L_0x0329
        L_0x013e:
            java.lang.Class r10 = r11.getReturnType()
            boolean r10 = r10.isArray()
            if (r10 == 0) goto L_0x0175
            boolean r10 = r7 instanceof java.lang.Object[]
            if (r10 != 0) goto L_0x016d
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$2 r10 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$2
            r10.<init>(r7, r11, r8)
            kotlin.Pair r7 = new kotlin.Pair
            java.lang.Class r8 = r11.getReturnType()
            r7.<init>(r8, r10)
            java.lang.Object r8 = r9.get(r7)
            if (r8 != 0) goto L_0x013b
            java.lang.Object r8 = r10.invoke()
            java.lang.Object r7 = r9.putIfAbsent(r7, r8)
            if (r7 != 0) goto L_0x016b
            goto L_0x013b
        L_0x016b:
            r8 = r7
            goto L_0x013b
        L_0x016d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "unhandled value type, please file a bug at https://github.com/google/ksp/issues/new"
            r7.<init>(r8)
            throw r7
        L_0x0175:
            java.lang.Class r10 = r11.getReturnType()
            boolean r10 = r10.isEnum()
            if (r10 == 0) goto L_0x019f
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$3 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$3
            r8.<init>(r7, r11)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x019f:
            java.lang.Class r10 = r11.getReturnType()
            boolean r10 = r10.isAnnotation()
            if (r10 == 0) goto L_0x01c9
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$4 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$4
            r8.<init>(r7, r11)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x01c9:
            java.lang.Class r10 = r11.getReturnType()
            java.lang.String r10 = r10.getName()
            java.lang.String r12 = "java.lang.Class"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r12)
            if (r10 == 0) goto L_0x023d
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r11 = r9.get(r10)
            if (r11 != 0) goto L_0x023a
            boolean r11 = r7 instanceof com.google.devtools.ksp.symbol.KSType
            if (r11 == 0) goto L_0x01f6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            com.google.devtools.ksp.symbol.KSType r7 = (com.google.devtools.ksp.symbol.KSType) r7
            java.lang.Class r7 = asClass(r7, r8)
            goto L_0x0227
        L_0x01f6:
            java.lang.Class r8 = r7.getClass()
            java.lang.reflect.Method[] r8 = r8.getMethods()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            int r11 = r8.length
        L_0x0202:
            if (r1 >= r11) goto L_0x0232
            r12 = r8[r1]
            java.lang.String r0 = r12.getName()
            java.lang.String r2 = "getCanonicalText"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            if (r0 == 0) goto L_0x022f
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.Object r7 = r12.invoke(r7, r8)
            java.lang.String r8 = "null cannot be cast to non-null type kotlin.String"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7, r8)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Class r7 = java.lang.Class.forName(r7)
        L_0x0227:
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x022f:
            int r1 = r1 + 1
            goto L_0x0202
        L_0x0232:
            java.util.NoSuchElementException r7 = new java.util.NoSuchElementException
            java.lang.String r8 = "Array contains no element matching the predicate."
            r7.<init>(r8)
            throw r7
        L_0x023a:
            r7 = r11
            goto L_0x0329
        L_0x023d:
            java.lang.Class r8 = r11.getReturnType()
            java.lang.String r8 = r8.getName()
            java.lang.String r10 = "byte"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x026d
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$5 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$5
            r8.<init>(r7)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x026d:
            java.lang.Class r8 = r11.getReturnType()
            java.lang.String r8 = r8.getName()
            java.lang.String r10 = "short"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x029d
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$6 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$6
            r8.<init>(r7)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x029d:
            java.lang.Class r8 = r11.getReturnType()
            java.lang.String r8 = r8.getName()
            java.lang.String r10 = "long"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x02cc
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$7 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$7
            r8.<init>(r7)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x02cc:
            java.lang.Class r8 = r11.getReturnType()
            java.lang.String r8 = r8.getName()
            java.lang.String r10 = "float"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x02fb
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$8 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$8
            r8.<init>(r7)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
            goto L_0x0329
        L_0x02fb:
            java.lang.Class r8 = r11.getReturnType()
            java.lang.String r8 = r8.getName()
            java.lang.String r10 = "double"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            if (r8 == 0) goto L_0x0329
            com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$9 r8 = new com.google.devtools.ksp.UtilsKt$createInvocationHandler$1$value$9
            r8.<init>(r7)
            kotlin.Pair r10 = new kotlin.Pair
            java.lang.Class r11 = r11.getReturnType()
            r10.<init>(r11, r7)
            java.lang.Object r7 = r9.get(r10)
            if (r7 != 0) goto L_0x0329
            java.lang.Object r7 = r8.invoke()
            java.lang.Object r8 = r9.putIfAbsent(r10, r7)
            if (r8 != 0) goto L_0x013b
        L_0x0329:
            return r7
        L_0x032a:
            java.util.NoSuchElementException r7 = new java.util.NoSuchElementException
            java.lang.String r8 = "Collection contains no element matching the predicate."
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.createInvocationHandler$lambda$8(com.google.devtools.ksp.symbol.KSAnnotation, java.lang.Class, java.util.concurrent.ConcurrentHashMap, java.lang.Object, java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }

    @NotNull
    public static final KSClassDeclaration findActualType(@NotNull KSTypeAlias kSTypeAlias) {
        Intrinsics.checkNotNullParameter(kSTypeAlias, "<this>");
        KSDeclaration declaration = kSTypeAlias.getType().resolve().getDeclaration();
        if (declaration instanceof KSTypeAlias) {
            return findActualType((KSTypeAlias) declaration);
        }
        Intrinsics.checkNotNull(declaration, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSClassDeclaration");
        return (KSClassDeclaration) declaration;
    }

    @NotNull
    public static final Sequence<KSType> getAllSuperTypes(@NotNull KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return SequencesKt.distinct(SequencesKt.plus(SequencesKt.map(kSClassDeclaration.getSuperTypes(), UtilsKt$getAllSuperTypes$1.INSTANCE), SequencesKt.flatMap(SequencesKt.mapNotNull(kSClassDeclaration.getSuperTypes(), UtilsKt$getAllSuperTypes$2.INSTANCE), UtilsKt$getAllSuperTypes$3.INSTANCE)));
    }

    /* access modifiers changed from: private */
    public static final Sequence<KSClassDeclaration> getAllSuperTypes$getTypesUpperBound(KSTypeParameter kSTypeParameter) {
        return SequencesKt.flatMap(kSTypeParameter.getBounds(), UtilsKt$getAllSuperTypes$getTypesUpperBound$1.INSTANCE);
    }

    @NotNull
    @KspExperimental
    public static final <T extends Annotation> Sequence<T> getAnnotationsByType(@NotNull KSAnnotated kSAnnotated, @NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "annotationKClass");
        return SequencesKt.map(SequencesKt.filter(kSAnnotated.getAnnotations(), new UtilsKt$getAnnotationsByType$1(kClass)), new UtilsKt$getAnnotationsByType$2(kClass));
    }

    public static final /* synthetic */ <T> KSClassDeclaration getClassDeclarationByName(Resolver resolver) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        String qualifiedName = Reflection.getOrCreateKotlinClass(Object.class).getQualifiedName();
        if (qualifiedName != null) {
            return resolver.getClassDeclarationByName(resolver.getKSNameFromString(qualifiedName));
        }
        return null;
    }

    @NotNull
    public static final Sequence<KSFunctionDeclaration> getConstructors(@NotNull KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return SequencesKt.filter(getDeclaredFunctions(kSClassDeclaration), UtilsKt$getConstructors$1.INSTANCE);
    }

    @Nullable
    public static final KSFile getContainingFile(@NotNull KSNode kSNode) {
        Intrinsics.checkNotNullParameter(kSNode, "<this>");
        KSNode parent = kSNode.getParent();
        while (parent != null && !(parent instanceof KSFile)) {
            parent = parent.getParent();
        }
        if (parent instanceof KSFile) {
            return (KSFile) parent;
        }
        return null;
    }

    @NotNull
    public static final Sequence<KSFunctionDeclaration> getDeclaredFunctions(@NotNull KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        Sequence<KSFunctionDeclaration> filter = SequencesKt.filter(kSClassDeclaration.getDeclarations(), UtilsKt$getDeclaredFunctions$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(filter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        return filter;
    }

    @NotNull
    public static final Sequence<KSPropertyDeclaration> getDeclaredProperties(@NotNull KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        Sequence<KSPropertyDeclaration> filter = SequencesKt.filter(kSClassDeclaration.getDeclarations(), UtilsKt$getDeclaredProperties$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(filter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        return filter;
    }

    @NotNull
    public static final Sequence<KSFunctionDeclaration> getFunctionDeclarationsByName(@NotNull Resolver resolver, @NotNull String str, boolean z2) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getFunctionDeclarationsByName(resolver.getKSNameFromString(str), z2);
    }

    public static /* synthetic */ Sequence getFunctionDeclarationsByName$default(Resolver resolver, String str, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        return getFunctionDeclarationsByName(resolver, str, z2);
    }

    @NotNull
    public static final List<KSTypeArgument> getInnerArguments(@NotNull KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        return kSType.getArguments().subList(0, kSType.getDeclaration().getTypeParameters().size());
    }

    @Nullable
    @KspExperimental
    public static final KSClassDeclaration getJavaClassByName(@NotNull Resolver resolver, @NotNull KSName kSName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(kSName, "name");
        KSName mapKotlinNameToJava = resolver.mapKotlinNameToJava(kSName);
        if (mapKotlinNameToJava != null) {
            kSName = mapKotlinNameToJava;
        }
        return resolver.getClassDeclarationByName(kSName);
    }

    @Nullable
    @KspExperimental
    public static final KSClassDeclaration getKotlinClassByName(@NotNull Resolver resolver, @NotNull KSName kSName) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(kSName, "name");
        KSName mapJavaNameToKotlin = resolver.mapJavaNameToKotlin(kSName);
        if (mapJavaNameToKotlin != null) {
            kSName = mapJavaNameToKotlin;
        }
        return resolver.getClassDeclarationByName(kSName);
    }

    @Nullable
    public static final KSType getOuterType(@NotNull KSType kSType) {
        Intrinsics.checkNotNullParameter(kSType, "<this>");
        if (!kSType.getDeclaration().getModifiers().contains(Modifier.INNER)) {
            return null;
        }
        KSDeclaration parentDeclaration = kSType.getDeclaration().getParentDeclaration();
        KSClassDeclaration kSClassDeclaration = parentDeclaration instanceof KSClassDeclaration ? (KSClassDeclaration) parentDeclaration : null;
        if (kSClassDeclaration == null) {
            return null;
        }
        return kSClassDeclaration.asType(kSType.getArguments().subList(kSType.getDeclaration().getTypeParameters().size(), kSType.getArguments().size()));
    }

    @Nullable
    public static final KSPropertyDeclaration getPropertyDeclarationByName(@NotNull Resolver resolver, @NotNull String str, boolean z2) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getPropertyDeclarationByName(resolver.getKSNameFromString(str), z2);
    }

    public static /* synthetic */ KSPropertyDeclaration getPropertyDeclarationByName$default(Resolver resolver, String str, boolean z2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            z2 = false;
        }
        return getPropertyDeclarationByName(resolver, str, z2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.google.devtools.ksp.symbol.Visibility getVisibility(@org.jetbrains.annotations.NotNull com.google.devtools.ksp.symbol.KSDeclaration r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.PUBLIC
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x0015
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00c4
        L_0x0015:
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.OVERRIDE
            boolean r0 = r0.contains(r1)
            r2 = 0
            if (r0 == 0) goto L_0x004b
            boolean r0 = r4 instanceof com.google.devtools.ksp.symbol.KSFunctionDeclaration
            if (r0 == 0) goto L_0x0035
            com.google.devtools.ksp.symbol.KSFunctionDeclaration r4 = (com.google.devtools.ksp.symbol.KSFunctionDeclaration) r4
            com.google.devtools.ksp.symbol.KSDeclaration r4 = r4.findOverridee()
            if (r4 == 0) goto L_0x0033
            com.google.devtools.ksp.symbol.Visibility r4 = getVisibility(r4)
            goto L_0x0045
        L_0x0033:
            r4 = r2
            goto L_0x0045
        L_0x0035:
            boolean r0 = r4 instanceof com.google.devtools.ksp.symbol.KSPropertyDeclaration
            if (r0 == 0) goto L_0x0033
            com.google.devtools.ksp.symbol.KSPropertyDeclaration r4 = (com.google.devtools.ksp.symbol.KSPropertyDeclaration) r4
            com.google.devtools.ksp.symbol.KSPropertyDeclaration r4 = r4.findOverridee()
            if (r4 == 0) goto L_0x0033
            com.google.devtools.ksp.symbol.Visibility r4 = getVisibility(r4)
        L_0x0045:
            if (r4 != 0) goto L_0x00c4
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00c4
        L_0x004b:
            boolean r0 = isLocal(r4)
            if (r0 == 0) goto L_0x0055
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.LOCAL
            goto L_0x00c4
        L_0x0055:
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.PRIVATE
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x0064
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.PRIVATE
            goto L_0x00c4
        L_0x0064:
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r3 = com.google.devtools.ksp.symbol.Modifier.PROTECTED
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x00c2
            java.util.Set r0 = r4.getModifiers()
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x007b
            goto L_0x00c2
        L_0x007b:
            java.util.Set r0 = r4.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.INTERNAL
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x008a
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.INTERNAL
            goto L_0x00c4
        L_0x008a:
            com.google.devtools.ksp.symbol.Origin r0 = r4.getOrigin()
            com.google.devtools.ksp.symbol.Origin r1 = com.google.devtools.ksp.symbol.Origin.SYNTHETIC
            if (r0 != r1) goto L_0x00ac
            com.google.devtools.ksp.symbol.KSDeclaration r0 = r4.getParentDeclaration()
            if (r0 == 0) goto L_0x009c
            com.google.devtools.ksp.symbol.Origin r2 = r0.getOrigin()
        L_0x009c:
            com.google.devtools.ksp.symbol.Origin r0 = com.google.devtools.ksp.symbol.Origin.JAVA
            if (r2 != r0) goto L_0x00ac
            com.google.devtools.ksp.symbol.KSDeclaration r4 = r4.getParentDeclaration()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.google.devtools.ksp.symbol.Visibility r4 = getVisibility(r4)
            goto L_0x00c4
        L_0x00ac:
            com.google.devtools.ksp.symbol.Origin r0 = r4.getOrigin()
            com.google.devtools.ksp.symbol.Origin r1 = com.google.devtools.ksp.symbol.Origin.JAVA
            if (r0 == r1) goto L_0x00bf
            com.google.devtools.ksp.symbol.Origin r4 = r4.getOrigin()
            com.google.devtools.ksp.symbol.Origin r0 = com.google.devtools.ksp.symbol.Origin.JAVA_LIB
            if (r4 == r0) goto L_0x00bf
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.PUBLIC
            goto L_0x00c4
        L_0x00bf:
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.JAVA_PACKAGE
            goto L_0x00c4
        L_0x00c2:
            com.google.devtools.ksp.symbol.Visibility r4 = com.google.devtools.ksp.symbol.Visibility.PROTECTED
        L_0x00c4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.getVisibility(com.google.devtools.ksp.symbol.KSDeclaration):com.google.devtools.ksp.symbol.Visibility");
    }

    public static final boolean isAbstract(@NotNull KSClassDeclaration kSClassDeclaration) {
        Intrinsics.checkNotNullParameter(kSClassDeclaration, "<this>");
        return kSClassDeclaration.getClassKind() == ClassKind.INTERFACE || kSClassDeclaration.getModifiers().contains(Modifier.ABSTRACT);
    }

    @KspExperimental
    public static final <T extends Annotation> boolean isAnnotationPresent(@NotNull KSAnnotated kSAnnotated, @NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kSAnnotated, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "annotationKClass");
        return SequencesKt.firstOrNull(getAnnotationsByType(kSAnnotated, kClass)) != null;
    }

    public static final boolean isConstructor(@NotNull KSFunctionDeclaration kSFunctionDeclaration) {
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration, "<this>");
        return Intrinsics.areEqual((Object) kSFunctionDeclaration.getSimpleName().asString(), (Object) "<init>");
    }

    public static final boolean isDefault(@NotNull KSValueArgument kSValueArgument) {
        Intrinsics.checkNotNullParameter(kSValueArgument, "<this>");
        return kSValueArgument.getOrigin() == Origin.SYNTHETIC;
    }

    public static final boolean isInternal(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getModifiers().contains(Modifier.INTERNAL);
    }

    public static final boolean isJavaPackagePrivate(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.JAVA_PACKAGE;
    }

    public static final boolean isLocal(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getParentDeclaration() != null && !(kSDeclaration.getParentDeclaration() instanceof KSClassDeclaration);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0071, code lost:
        if (r2 != r4) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isOpen(@org.jetbrains.annotations.NotNull com.google.devtools.ksp.symbol.KSDeclaration r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            boolean r0 = isLocal(r6)
            if (r0 != 0) goto L_0x0087
            java.util.Set r0 = r6.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r1 = com.google.devtools.ksp.symbol.Modifier.FINAL
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x0087
            boolean r0 = r6 instanceof com.google.devtools.ksp.symbol.KSClassDeclaration
            r2 = 0
            if (r0 == 0) goto L_0x0020
            r3 = r6
            com.google.devtools.ksp.symbol.KSClassDeclaration r3 = (com.google.devtools.ksp.symbol.KSClassDeclaration) r3
            goto L_0x0021
        L_0x0020:
            r3 = r2
        L_0x0021:
            if (r3 == 0) goto L_0x0028
            com.google.devtools.ksp.symbol.ClassKind r3 = r3.getClassKind()
            goto L_0x0029
        L_0x0028:
            r3 = r2
        L_0x0029:
            com.google.devtools.ksp.symbol.ClassKind r4 = com.google.devtools.ksp.symbol.ClassKind.INTERFACE
            if (r3 == r4) goto L_0x0085
            java.util.Set r3 = r6.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r5 = com.google.devtools.ksp.symbol.Modifier.OVERRIDE
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0085
            java.util.Set r3 = r6.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r5 = com.google.devtools.ksp.symbol.Modifier.ABSTRACT
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0085
            java.util.Set r3 = r6.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r5 = com.google.devtools.ksp.symbol.Modifier.OPEN
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0085
            java.util.Set r3 = r6.getModifiers()
            com.google.devtools.ksp.symbol.Modifier r5 = com.google.devtools.ksp.symbol.Modifier.SEALED
            boolean r3 = r3.contains(r5)
            if (r3 != 0) goto L_0x0085
            if (r0 != 0) goto L_0x0073
            com.google.devtools.ksp.symbol.KSDeclaration r0 = r6.getParentDeclaration()
            boolean r3 = r0 instanceof com.google.devtools.ksp.symbol.KSClassDeclaration
            if (r3 == 0) goto L_0x006a
            com.google.devtools.ksp.symbol.KSClassDeclaration r0 = (com.google.devtools.ksp.symbol.KSClassDeclaration) r0
            goto L_0x006b
        L_0x006a:
            r0 = r2
        L_0x006b:
            if (r0 == 0) goto L_0x0071
            com.google.devtools.ksp.symbol.ClassKind r2 = r0.getClassKind()
        L_0x0071:
            if (r2 == r4) goto L_0x0085
        L_0x0073:
            java.util.Set r0 = r6.getModifiers()
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x0087
            com.google.devtools.ksp.symbol.Origin r6 = r6.getOrigin()
            com.google.devtools.ksp.symbol.Origin r0 = com.google.devtools.ksp.symbol.Origin.JAVA
            if (r6 != r0) goto L_0x0087
        L_0x0085:
            r6 = 1
            goto L_0x0088
        L_0x0087:
            r6 = 0
        L_0x0088:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.isOpen(com.google.devtools.ksp.symbol.KSDeclaration):boolean");
    }

    public static final boolean isPrivate(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return kSDeclaration.getModifiers().contains(Modifier.PRIVATE);
    }

    public static final boolean isProtected(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.PROTECTED;
    }

    public static final boolean isPublic(@NotNull KSDeclaration kSDeclaration) {
        Intrinsics.checkNotNullParameter(kSDeclaration, "<this>");
        return getVisibility(kSDeclaration) == Visibility.PUBLIC;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        if (r3 != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isVisibleFrom(@org.jetbrains.annotations.NotNull com.google.devtools.ksp.symbol.KSDeclaration r3, @org.jetbrains.annotations.NotNull com.google.devtools.ksp.symbol.KSDeclaration r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "other"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            boolean r0 = isLocal(r3)
            if (r0 == 0) goto L_0x001a
            java.util.List r3 = isVisibleFrom$parentDeclarationsForLocal(r3)
            boolean r3 = r3.contains(r4)
            goto L_0x007e
        L_0x001a:
            boolean r0 = isPrivate(r3)
            if (r0 == 0) goto L_0x0025
            boolean r3 = isVisibleFrom$isVisibleInPrivate(r3, r4)
            goto L_0x007e
        L_0x0025:
            boolean r0 = isPublic(r3)
            r1 = 1
            if (r0 == 0) goto L_0x002e
        L_0x002c:
            r3 = r1
            goto L_0x007e
        L_0x002e:
            boolean r0 = isInternal(r3)
            if (r0 == 0) goto L_0x0041
            com.google.devtools.ksp.symbol.KSFile r0 = r4.getContainingFile()
            if (r0 == 0) goto L_0x0041
            com.google.devtools.ksp.symbol.KSFile r0 = r3.getContainingFile()
            if (r0 == 0) goto L_0x0041
            goto L_0x002c
        L_0x0041:
            boolean r0 = isJavaPackagePrivate(r3)
            if (r0 == 0) goto L_0x004c
            boolean r3 = isVisibleFrom$isSamePackage(r3, r4)
            goto L_0x007e
        L_0x004c:
            boolean r0 = isProtected(r3)
            r2 = 0
            if (r0 == 0) goto L_0x007d
            boolean r0 = isVisibleFrom$isVisibleInPrivate(r3, r4)
            if (r0 != 0) goto L_0x002c
            boolean r0 = isVisibleFrom$isSamePackage(r3, r4)
            if (r0 != 0) goto L_0x002c
            com.google.devtools.ksp.symbol.KSClassDeclaration r4 = closestClassDeclaration(r4)
            if (r4 == 0) goto L_0x0079
            com.google.devtools.ksp.symbol.KSClassDeclaration r3 = closestClassDeclaration(r3)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            com.google.devtools.ksp.symbol.KSType r3 = r3.asStarProjectedType()
            com.google.devtools.ksp.symbol.KSType r4 = r4.asStarProjectedType()
            boolean r3 = r3.isAssignableFrom(r4)
            goto L_0x007a
        L_0x0079:
            r3 = r2
        L_0x007a:
            if (r3 == 0) goto L_0x007d
            goto L_0x002c
        L_0x007d:
            r3 = r2
        L_0x007e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.devtools.ksp.UtilsKt.isVisibleFrom(com.google.devtools.ksp.symbol.KSDeclaration, com.google.devtools.ksp.symbol.KSDeclaration):boolean");
    }

    private static final boolean isVisibleFrom$isSamePackage(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2) {
        return Intrinsics.areEqual((Object) kSDeclaration.getPackageName(), (Object) kSDeclaration2.getPackageName());
    }

    private static final boolean isVisibleFrom$isVisibleInPrivate(KSDeclaration kSDeclaration, KSDeclaration kSDeclaration2) {
        return (isLocal(kSDeclaration2) && CollectionsKt.contains(isVisibleFrom$parentDeclarationsForLocal(kSDeclaration2), kSDeclaration.getParentDeclaration())) || Intrinsics.areEqual((Object) kSDeclaration.getParentDeclaration(), (Object) kSDeclaration2.getParentDeclaration()) || Intrinsics.areEqual((Object) kSDeclaration.getParentDeclaration(), (Object) kSDeclaration2) || (kSDeclaration.getParentDeclaration() == null && kSDeclaration2.getParentDeclaration() == null && Intrinsics.areEqual((Object) kSDeclaration.getContainingFile(), (Object) kSDeclaration2.getContainingFile()));
    }

    private static final List<KSDeclaration> isVisibleFrom$parentDeclarationsForLocal(KSDeclaration kSDeclaration) {
        ArrayList arrayList = new ArrayList();
        KSDeclaration parentDeclaration = kSDeclaration.getParentDeclaration();
        Intrinsics.checkNotNull(parentDeclaration);
        while (isLocal(parentDeclaration)) {
            arrayList.add(parentDeclaration);
            parentDeclaration = parentDeclaration.getParentDeclaration();
            Intrinsics.checkNotNull(parentDeclaration);
        }
        arrayList.add(parentDeclaration);
        return arrayList;
    }

    /* access modifiers changed from: private */
    @KspExperimental
    public static final <T extends Annotation> T toAnnotation(KSAnnotation kSAnnotation, Class<T> cls) {
        T newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, createInvocationHandler(kSAnnotation, cls));
        Intrinsics.checkNotNull(newProxyInstance, "null cannot be cast to non-null type T of com.google.devtools.ksp.UtilsKt.toAnnotation");
        return (Annotation) newProxyInstance;
    }

    private static final Object[] toArray(List<?> list, Method method, Function1<Object, ? extends Object> function1) {
        Object newInstance = Array.newInstance(method.getReturnType().getComponentType(), list.size());
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
        Object[] objArr = (Object[]) newInstance;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            objArr[i3] = obj != null ? function1.invoke(obj) : null;
        }
        return objArr;
    }

    public static final boolean validate(@NotNull KSNode kSNode, @NotNull Function2<? super KSNode, ? super KSNode, Boolean> function2) {
        Intrinsics.checkNotNullParameter(kSNode, "<this>");
        Intrinsics.checkNotNullParameter(function2, "predicate");
        return ((Boolean) kSNode.accept(new KSValidateVisitor(function2), null)).booleanValue();
    }

    public static /* synthetic */ boolean validate$default(KSNode kSNode, Function2 function2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            function2 = UtilsKt$validate$1.INSTANCE;
        }
        return validate(kSNode, function2);
    }

    public static final boolean isAbstract(@NotNull KSPropertyDeclaration kSPropertyDeclaration) {
        Set<Modifier> modifiers;
        Set<Modifier> modifiers2;
        Intrinsics.checkNotNullParameter(kSPropertyDeclaration, "<this>");
        Set<Modifier> modifiers3 = kSPropertyDeclaration.getModifiers();
        Modifier modifier = Modifier.ABSTRACT;
        if (modifiers3.contains(modifier)) {
            return true;
        }
        KSDeclaration parentDeclaration = kSPropertyDeclaration.getParentDeclaration();
        KSClassDeclaration kSClassDeclaration = parentDeclaration instanceof KSClassDeclaration ? (KSClassDeclaration) parentDeclaration : null;
        if (kSClassDeclaration == null || kSClassDeclaration.getClassKind() != ClassKind.INTERFACE) {
            return false;
        }
        KSPropertyGetter getter = kSPropertyDeclaration.getGetter();
        if ((getter == null || (modifiers2 = getter.getModifiers()) == null) ? true : modifiers2.contains(modifier)) {
            KSPropertySetter setter = kSPropertyDeclaration.getSetter();
            if ((setter == null || (modifiers = setter.getModifiers()) == null) ? true : modifiers.contains(modifier)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @KspExperimental
    public static final KSClassDeclaration getJavaClassByName(@NotNull Resolver resolver, @NotNull String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return getJavaClassByName(resolver, resolver.getKSNameFromString(str));
    }

    @Nullable
    @KspExperimental
    public static final KSClassDeclaration getKotlinClassByName(@NotNull Resolver resolver, @NotNull String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return getKotlinClassByName(resolver, resolver.getKSNameFromString(str));
    }

    @Nullable
    public static final KSClassDeclaration getClassDeclarationByName(@NotNull Resolver resolver, @NotNull String str) {
        Intrinsics.checkNotNullParameter(resolver, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        return resolver.getClassDeclarationByName(resolver.getKSNameFromString(str));
    }

    /* access modifiers changed from: private */
    @KspExperimental
    public static final Object asArray(Object obj, Method method, Class<?> cls) {
        return asArray((List<?>) CollectionsKt.listOf(obj), method, cls);
    }
}
