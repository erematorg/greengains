package com.apollographql.apollo3.api.test;

import com.apollographql.apollo3.annotations.ApolloExperimental;
import com.apollographql.apollo3.api.CompiledListType;
import com.apollographql.apollo3.api.CompiledNamedType;
import com.apollographql.apollo3.api.CompiledNotNullType;
import com.apollographql.apollo3.api.CompiledType;
import com.apollographql.apollo3.api.CustomScalarType;
import com.apollographql.apollo3.api.EnumType;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@ApolloExperimental
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\r\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000eH\u0002JW\u0010\u0015\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c2$\u0010\u001d\u001a \u0012\u001a\b\u0001\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u001f0\u001e\u0018\u00010\rH\u0016¢\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u00062\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016JM\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c2\"\u0010\u001d\u001a\u001e\u0012\u001a\b\u0001\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u001f0\u001e0\rH\u0016¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u00182\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016J$\u0010&\u001a\u00020\u00182\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001cH\u0016J\u0016\u0010'\u001a\u00020\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016J\u0016\u0010(\u001a\u00020\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016JW\u0010)\u001a\u0002H\u0016\"\u0004\b\u0000\u0010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001c2$\u0010\u001d\u001a \u0012\u001a\b\u0001\u0012\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u001f0\u001e\u0018\u00010\rH\u0002¢\u0006\u0002\u0010 J\u0016\u0010*\u001a\u00020\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016J\u0016\u0010+\u001a\u00020\u00182\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001cH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/apollographql/apollo3/api/test/DefaultTestResolver;", "Lcom/apollographql/apollo3/api/test/TestResolver;", "()V", "MAX_STACK_SIZE", "", "booleanCounter", "", "compositeCounter", "enumCounter", "floatCounter", "", "intCounter", "stack", "", "", "[Ljava/lang/Object;", "stackSize", "pop", "", "push", "v", "resolve", "T", "responseName", "", "compiledType", "Lcom/apollographql/apollo3/api/CompiledType;", "enumValues", "", "ctors", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lcom/apollographql/apollo3/api/CompiledType;Ljava/util/List;[Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "resolveBoolean", "path", "resolveComposite", "(Ljava/util/List;[Lkotlin/jvm/functions/Function0;)Ljava/util/Map;", "resolveCustomScalar", "resolveEnum", "resolveFloat", "resolveInt", "resolveInternal", "resolveListSize", "resolveString", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
@SourceDebugExtension({"SMAP\nTestResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TestResolver.kt\ncom/apollographql/apollo3/api/test/DefaultTestResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,151:1\n378#2,7:152\n1549#2:159\n1620#2,3:160\n*S KotlinDebug\n*F\n+ 1 TestResolver.kt\ncom/apollographql/apollo3/api/test/DefaultTestResolver\n*L\n53#1:152,7\n99#1:159\n99#1:160,3\n*E\n"})
public class DefaultTestResolver implements TestResolver {
    private final int MAX_STACK_SIZE = 256;
    private boolean booleanCounter;
    private int compositeCounter;
    private int enumCounter;
    private double floatCounter;
    private int intCounter;
    @NotNull
    private final Object[] stack;
    private int stackSize;

    public DefaultTestResolver() {
        Object[] objArr = new Object[256];
        for (int i3 = 0; i3 < 256; i3++) {
            objArr[i3] = 0;
        }
        this.stack = objArr;
        this.floatCounter = 0.5d;
    }

    private final void pop() {
        int i3 = this.stackSize - 1;
        this.stackSize = i3;
        this.stack[i3] = 0;
    }

    private final void push(Object obj) {
        int i3 = this.stackSize;
        if (i3 < this.MAX_STACK_SIZE) {
            Object[] objArr = this.stack;
            this.stackSize = i3 + 1;
            objArr[i3] = obj;
            return;
        }
        throw new IllegalStateException(("Nesting too deep at " + ArraysKt___ArraysKt.joinToString$default(this.stack, (CharSequence) JwtUtilsKt.JWT_DELIMITER, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)).toString());
    }

    private final <T> T resolveInternal(String str, CompiledType compiledType, List<String> list, Function0<? extends Map<String, ? extends Object>>[] function0Arr) {
        List list2 = CollectionsKt.toList(ArraysKt.take((T[]) this.stack, this.stackSize));
        if (compiledType instanceof CompiledNotNullType) {
            return resolve(str, ((CompiledNotNullType) compiledType).getOfType(), list, function0Arr);
        }
        if (compiledType instanceof CompiledListType) {
            IntRange until = RangesKt.until(0, resolveListSize(list2));
            T arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10));
            Iterator it = until.iterator();
            while (it.hasNext()) {
                push(Integer.valueOf(((IntIterator) it).nextInt()));
                Object resolveInternal = resolveInternal(str, ((CompiledListType) compiledType).getOfType(), list, function0Arr);
                pop();
                arrayList.add(resolveInternal);
            }
            return arrayList;
        } else if (compiledType instanceof CustomScalarType) {
            String name = ((CustomScalarType) compiledType).getName();
            switch (name.hashCode()) {
                case -1808118735:
                    if (name.equals("String")) {
                        return resolveString(list2);
                    }
                    break;
                case 2331:
                    if (name.equals(SchemaSymbols.ATTVAL_ID)) {
                        return resolveString(list2);
                    }
                    break;
                case 73679:
                    if (name.equals("Int")) {
                        return Integer.valueOf(resolveInt(list2));
                    }
                    break;
                case 67973692:
                    if (name.equals("Float")) {
                        return Double.valueOf(resolveFloat(list2));
                    }
                    break;
                case 1729365000:
                    if (name.equals("Boolean")) {
                        return Boolean.valueOf(resolveBoolean(list2));
                    }
                    break;
            }
            return resolveCustomScalar(list2);
        } else if (compiledType instanceof EnumType) {
            return resolveEnum(list2, list);
        } else {
            if (!(compiledType instanceof CompiledNamedType)) {
                throw new NoWhenBranchMatchedException();
            } else if (function0Arr != null) {
                return resolveComposite(list2, function0Arr);
            } else {
                throw new IllegalStateException(("no ctors for " + str).toString());
            }
        }
    }

    public <T> T resolve(@NotNull String str, @NotNull CompiledType compiledType, @NotNull List<String> list, @Nullable Function0<? extends Map<String, ? extends Object>>[] function0Arr) {
        Intrinsics.checkNotNullParameter(str, "responseName");
        Intrinsics.checkNotNullParameter(compiledType, "compiledType");
        Intrinsics.checkNotNullParameter(list, "enumValues");
        push(str);
        T resolveInternal = resolveInternal(str, compiledType, list, function0Arr);
        pop();
        return resolveInternal;
    }

    public boolean resolveBoolean(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "path");
        boolean z2 = this.booleanCounter;
        this.booleanCounter = !z2;
        return z2;
    }

    @NotNull
    public Map<String, Object> resolveComposite(@NotNull List<? extends Object> list, @NotNull Function0<? extends Map<String, ? extends Object>>[] function0Arr) {
        Intrinsics.checkNotNullParameter(list, "path");
        Intrinsics.checkNotNullParameter(function0Arr, "ctors");
        int i3 = this.compositeCounter;
        this.compositeCounter = i3 + 1;
        return (Map) function0Arr[i3 % function0Arr.length].invoke();
    }

    @NotNull
    public String resolveCustomScalar(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "path");
        throw new IllegalStateException(("Cannot resolve custom scalar at " + list).toString());
    }

    @NotNull
    public String resolveEnum(@NotNull List<? extends Object> list, @NotNull List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "path");
        Intrinsics.checkNotNullParameter(list2, "enumValues");
        int i3 = this.enumCounter;
        this.enumCounter = i3 + 1;
        return list2.get(i3 % list2.size());
    }

    public double resolveFloat(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "path");
        double d2 = this.floatCounter;
        this.floatCounter = 1.0d + d2;
        return d2;
    }

    public int resolveInt(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "path");
        int i3 = this.intCounter;
        this.intCounter = i3 + 1;
        return i3;
    }

    public int resolveListSize(@NotNull List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(list, "path");
        return 3;
    }

    @NotNull
    public String resolveString(@NotNull List<? extends Object> list) {
        int i3;
        Intrinsics.checkNotNullParameter(list, "path");
        ListIterator<? extends Object> listIterator = list.listIterator(list.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                if (listIterator.previous() instanceof String) {
                    i3 = listIterator.nextIndex();
                    break;
                }
            } else {
                i3 = -1;
                break;
            }
        }
        return CollectionsKt___CollectionsKt.joinToString$default(list.subList(i3, list.size()), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
