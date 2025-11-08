package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.BooleanExpression;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a;\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\u0010\u0006\u001a\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\t\u001a$\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a;\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\u0010\u0006\u001a%\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u0005\"\u00020\t¢\u0006\u0002\u0010\u000f\u001a\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00012\u0006\u0010\u0012\u001a\u00020\t\u001aE\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\u0010\u0013\u001a\u001a\u0010\u0014\u001a\u00020\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a.\u0010\u0016\u001a\u00020\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0018\u001a*\u0010\u0016\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00190\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\tH\u0007\u001a@\u0010\u0016\u001a\u00020\u0015*\b\u0012\u0004\u0012\u00020\u00190\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010 \u001a9\u0010!\u001a\u0004\u0018\u0001H\"\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\"*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\"0$¢\u0006\u0002\u0010%\u001aE\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0005\"\b\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\u0010\u0013¨\u0006&"}, d2 = {"and", "Lcom/apollographql/apollo3/api/BooleanExpression;", "T", "", "other", "", "([Lcom/apollographql/apollo3/api/BooleanExpression;)Lcom/apollographql/apollo3/api/BooleanExpression;", "label", "Lcom/apollographql/apollo3/api/BLabel;", "", "not", "or", "possibleTypes", "Lcom/apollographql/apollo3/api/BPossibleTypes;", "typenames", "([Ljava/lang/String;)Lcom/apollographql/apollo3/api/BooleanExpression;", "variable", "Lcom/apollographql/apollo3/api/BVariable;", "name", "(Lcom/apollographql/apollo3/api/BooleanExpression;[Lcom/apollographql/apollo3/api/BooleanExpression;)Lcom/apollographql/apollo3/api/BooleanExpression;", "containsPossibleTypes", "", "evaluate", "block", "Lkotlin/Function1;", "Lcom/apollographql/apollo3/api/BTerm;", "variables", "", "typename", "adapterContext", "Lcom/apollographql/apollo3/api/AdapterContext;", "path", "", "firstElementOfType", "U", "type", "Lkotlin/reflect/KClass;", "(Lcom/apollographql/apollo3/api/BooleanExpression;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nBooleanExpression.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpressions\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,194:1\n1747#2,3:195\n1726#2,3:198\n1747#2,3:201\n1747#2,3:204\n288#2,2:207\n288#2,2:209\n*S KotlinDebug\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpressions\n*L\n110#1:195,3\n111#1:198,3\n178#1:201,3\n179#1:204,3\n190#1:207,2\n191#1:209,2\n*E\n"})
@JvmName(name = "BooleanExpressions")
public final class BooleanExpressions {
    @NotNull
    public static final <T> BooleanExpression<T> and(@NotNull BooleanExpression<? extends T> booleanExpression, @NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(booleanExpressionArr, "other");
        return new BooleanExpression.And(CollectionsKt.toSet(CollectionsKt.plus(ArraysKt.toList((T[]) booleanExpressionArr), booleanExpression)));
    }

    public static final <T> boolean containsPossibleTypes(@NotNull BooleanExpression<? extends T> booleanExpression) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        if (Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.True.INSTANCE) || Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.False.INSTANCE)) {
            return false;
        }
        if (booleanExpression instanceof BooleanExpression.Not) {
            return containsPossibleTypes(((BooleanExpression.Not) booleanExpression).getOperand());
        }
        if (booleanExpression instanceof BooleanExpression.Or) {
            Iterable<BooleanExpression> operands = ((BooleanExpression.Or) booleanExpression).getOperands();
            if ((operands instanceof Collection) && ((Collection) operands).isEmpty()) {
                return false;
            }
            for (BooleanExpression containsPossibleTypes : operands) {
                if (containsPossibleTypes(containsPossibleTypes)) {
                }
            }
            return false;
        } else if (booleanExpression instanceof BooleanExpression.And) {
            Iterable<BooleanExpression> operands2 = ((BooleanExpression.And) booleanExpression).getOperands();
            if ((operands2 instanceof Collection) && ((Collection) operands2).isEmpty()) {
                return false;
            }
            for (BooleanExpression containsPossibleTypes2 : operands2) {
                if (containsPossibleTypes(containsPossibleTypes2)) {
                }
            }
            return false;
        } else if (booleanExpression instanceof BooleanExpression.Element) {
            return ((BooleanExpression.Element) booleanExpression).getValue() instanceof BPossibleTypes;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        return true;
    }

    public static final <T> boolean evaluate(@NotNull BooleanExpression<? extends T> booleanExpression, @NotNull Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        if (Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.True.INSTANCE)) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.False.INSTANCE)) {
            if (booleanExpression instanceof BooleanExpression.Not) {
                if (!evaluate(((BooleanExpression.Not) booleanExpression).getOperand(), function1)) {
                    return true;
                }
            } else if (booleanExpression instanceof BooleanExpression.Or) {
                Iterable<BooleanExpression> operands = ((BooleanExpression.Or) booleanExpression).getOperands();
                if (!(operands instanceof Collection) || !((Collection) operands).isEmpty()) {
                    for (BooleanExpression evaluate : operands) {
                        if (evaluate(evaluate, function1)) {
                            return true;
                        }
                    }
                }
            } else if (booleanExpression instanceof BooleanExpression.And) {
                Iterable<BooleanExpression> operands2 = ((BooleanExpression.And) booleanExpression).getOperands();
                if ((operands2 instanceof Collection) && ((Collection) operands2).isEmpty()) {
                    return true;
                }
                for (BooleanExpression evaluate2 : operands2) {
                    if (!evaluate(evaluate2, function1)) {
                    }
                }
                return true;
            } else if (booleanExpression instanceof BooleanExpression.Element) {
                return function1.invoke(((BooleanExpression.Element) booleanExpression).getValue()).booleanValue();
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        return false;
    }

    @Nullable
    public static final <T, U> U firstElementOfType(@NotNull BooleanExpression<? extends T> booleanExpression, @NotNull KClass<U> kClass) {
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "type");
        if (Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.True.INSTANCE) || Intrinsics.areEqual((Object) booleanExpression, (Object) BooleanExpression.False.INSTANCE)) {
            return null;
        }
        if (booleanExpression instanceof BooleanExpression.Element) {
            BooleanExpression.Element element = (BooleanExpression.Element) booleanExpression;
            if (!kClass.isInstance(element.getValue())) {
                return null;
            }
            U value = element.getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type U of com.apollographql.apollo3.api.BooleanExpressions.firstElementOfType");
            return value;
        } else if (booleanExpression instanceof BooleanExpression.Not) {
            return firstElementOfType(((BooleanExpression.Not) booleanExpression).getOperand(), kClass);
        } else {
            if (booleanExpression instanceof BooleanExpression.And) {
                Iterator it = ((BooleanExpression.And) booleanExpression).getOperands().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj2 = null;
                        break;
                    }
                    obj2 = it.next();
                    if (firstElementOfType((BooleanExpression) obj2, kClass) != null) {
                        break;
                    }
                }
                BooleanExpression booleanExpression2 = (BooleanExpression) obj2;
                if (booleanExpression2 != null) {
                    return firstElementOfType(booleanExpression2, kClass);
                }
                return null;
            } else if (booleanExpression instanceof BooleanExpression.Or) {
                Iterator it2 = ((BooleanExpression.Or) booleanExpression).getOperands().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it2.next();
                    if (firstElementOfType((BooleanExpression) obj, kClass) != null) {
                        break;
                    }
                }
                BooleanExpression booleanExpression3 = (BooleanExpression) obj;
                if (booleanExpression3 != null) {
                    return firstElementOfType(booleanExpression3, kClass);
                }
                return null;
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    @NotNull
    public static final BooleanExpression<BLabel> label(@Nullable String str) {
        return new BooleanExpression.Element(new BLabel(str));
    }

    public static /* synthetic */ BooleanExpression label$default(String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = null;
        }
        return label(str);
    }

    @NotNull
    public static final <T> BooleanExpression<T> not(@NotNull BooleanExpression<? extends T> booleanExpression) {
        Intrinsics.checkNotNullParameter(booleanExpression, "other");
        return new BooleanExpression.Not(booleanExpression);
    }

    @NotNull
    public static final <T> BooleanExpression<T> or(@NotNull BooleanExpression<? extends T> booleanExpression, @NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(booleanExpressionArr, "other");
        return new BooleanExpression.Or(CollectionsKt.toSet(CollectionsKt.plus(ArraysKt.toList((T[]) booleanExpressionArr), booleanExpression)));
    }

    @NotNull
    public static final BooleanExpression<BPossibleTypes> possibleTypes(@NotNull String... strArr) {
        Intrinsics.checkNotNullParameter(strArr, "typenames");
        return new BooleanExpression.Element(new BPossibleTypes((Set<String>) ArraysKt.toSet((T[]) strArr)));
    }

    @NotNull
    public static final BooleanExpression<BVariable> variable(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return new BooleanExpression.Element(new BVariable(str));
    }

    @NotNull
    public static final <T> BooleanExpression<T> and(@NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
        Intrinsics.checkNotNullParameter(booleanExpressionArr, "other");
        return new BooleanExpression.And(CollectionsKt.toSet(ArraysKt.toList((T[]) booleanExpressionArr)));
    }

    @NotNull
    public static final <T> BooleanExpression<T> or(@NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
        Intrinsics.checkNotNullParameter(booleanExpressionArr, "other");
        return new BooleanExpression.Or(CollectionsKt.toSet(ArraysKt.toList((T[]) booleanExpressionArr)));
    }

    @Deprecated(message = "Kept for binary compatibility with generated code from older versions")
    public static final boolean evaluate(@NotNull BooleanExpression<? extends BTerm> booleanExpression, @NotNull Set<String> set, @Nullable String str) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(set, "variables");
        return evaluate(booleanExpression, new BooleanExpressions$evaluate$3(set, str));
    }

    public static final boolean evaluate(@NotNull BooleanExpression<? extends BTerm> booleanExpression, @NotNull Set<String> set, @Nullable String str, @NotNull C0216AdapterContext adapterContext, @Nullable List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(booleanExpression, "<this>");
        Intrinsics.checkNotNullParameter(set, "variables");
        Intrinsics.checkNotNullParameter(adapterContext, "adapterContext");
        return evaluate(booleanExpression, new BooleanExpressions$evaluate$4(set, adapterContext, list != null ? CollectionsKt.drop(list, 1) : null, str));
    }
}
