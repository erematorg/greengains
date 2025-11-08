package com.apollographql.apollo3.api;

import androidx.compose.runtime.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002:\u0006\u0005\u0006\u0007\b\t\nB\u0007\b\u0004¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H&\u0001\u0006\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression;", "T", "", "()V", "simplify", "And", "Element", "False", "Not", "Or", "True", "Lcom/apollographql/apollo3/api/BooleanExpression$And;", "Lcom/apollographql/apollo3/api/BooleanExpression$Element;", "Lcom/apollographql/apollo3/api/BooleanExpression$False;", "Lcom/apollographql/apollo3/api/BooleanExpression$Not;", "Lcom/apollographql/apollo3/api/BooleanExpression$Or;", "Lcom/apollographql/apollo3/api/BooleanExpression$True;", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class BooleanExpression<T> {

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0001\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0001¢\u0006\u0002\u0010\u0005J\u000e\u0010\t\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u0001HÆ\u0001¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\u0000H\u0016J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Element;", "T", "", "Lcom/apollographql/apollo3/api/BooleanExpression;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "copy", "(Ljava/lang/Object;)Lcom/apollographql/apollo3/api/BooleanExpression$Element;", "equals", "", "other", "hashCode", "", "simplify", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Element<T> extends BooleanExpression<T> {
        @NotNull
        private final T value;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Element(@NotNull T t2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(t2, "value");
            this.value = t2;
        }

        public static /* synthetic */ Element copy$default(Element element, T t2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                t2 = element.value;
            }
            return element.copy(t2);
        }

        @NotNull
        public final T component1() {
            return this.value;
        }

        @NotNull
        public final Element<T> copy(@NotNull T t2) {
            Intrinsics.checkNotNullParameter(t2, "value");
            return new Element<>(t2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Element) && Intrinsics.areEqual((Object) this.value, (Object) ((Element) obj).value);
        }

        @NotNull
        public final T getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        @NotNull
        public Element<T> simplify() {
            return this;
        }

        @NotNull
        public String toString() {
            return b.c(new StringBuilder("Element(value="), this.value, ')');
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0000H\u0016¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$False;", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "()V", "simplify", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class False extends BooleanExpression {
        @NotNull
        public static final False INSTANCE = new False();

        private False() {
            super((DefaultConstructorMarker) null);
        }

        @NotNull
        public False simplify() {
            return this;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0001\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003HÆ\u0003J\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Not;", "T", "", "Lcom/apollographql/apollo3/api/BooleanExpression;", "operand", "(Lcom/apollographql/apollo3/api/BooleanExpression;)V", "getOperand", "()Lcom/apollographql/apollo3/api/BooleanExpression;", "component1", "copy", "equals", "", "other", "hashCode", "", "simplify", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Not<T> extends BooleanExpression<T> {
        @NotNull
        private final BooleanExpression<T> operand;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Not(@NotNull BooleanExpression<? extends T> booleanExpression) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(booleanExpression, "operand");
            this.operand = booleanExpression;
        }

        public static /* synthetic */ Not copy$default(Not not, BooleanExpression<T> booleanExpression, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                booleanExpression = not.operand;
            }
            return not.copy(booleanExpression);
        }

        @NotNull
        public final BooleanExpression<T> component1() {
            return this.operand;
        }

        @NotNull
        public final Not<T> copy(@NotNull BooleanExpression<? extends T> booleanExpression) {
            Intrinsics.checkNotNullParameter(booleanExpression, "operand");
            return new Not<>(booleanExpression);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Not) && Intrinsics.areEqual((Object) this.operand, (Object) ((Not) obj).operand);
        }

        @NotNull
        public final BooleanExpression<T> getOperand() {
            return this.operand;
        }

        public int hashCode() {
            return this.operand.hashCode();
        }

        @NotNull
        public BooleanExpression<T> simplify() {
            BooleanExpression<T> booleanExpression = this.operand;
            return booleanExpression instanceof True ? False.INSTANCE : booleanExpression instanceof False ? True.INSTANCE : this;
        }

        @NotNull
        public String toString() {
            return "Not(operand=" + this.operand + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0000H\u0016¨\u0006\u0005"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$True;", "Lcom/apollographql/apollo3/api/BooleanExpression;", "", "()V", "simplify", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class True extends BooleanExpression {
        @NotNull
        public static final True INSTANCE = new True();

        private True() {
            super((DefaultConstructorMarker) null);
        }

        @NotNull
        public True simplify() {
            return this;
        }
    }

    public /* synthetic */ BooleanExpression(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public abstract BooleanExpression<T> simplify();

    @SourceDebugExtension({"SMAP\nBooleanExpression.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpression$And\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,194:1\n766#2:195\n857#2,2:196\n1549#2:198\n1620#2,3:199\n*S KotlinDebug\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpression$And\n*L\n73#1:195\n73#1:196,2\n75#1:198\n75#1:199,3\n*E\n"})
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\b\u0016\u0012\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0005\"\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0002\u0010\u0006B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007HÆ\u0003J%\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$And;", "T", "", "Lcom/apollographql/apollo3/api/BooleanExpression;", "operands", "", "([Lcom/apollographql/apollo3/api/BooleanExpression;)V", "", "(Ljava/util/Set;)V", "getOperands", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "hashCode", "", "simplify", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class And<T> extends BooleanExpression<T> {
        @NotNull
        private final Set<BooleanExpression<T>> operands;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public And(@NotNull Set<? extends BooleanExpression<? extends T>> set) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(set, "operands");
            this.operands = set;
            if (set.isEmpty()) {
                throw new IllegalStateException("Apollo: cannot create a 'And' condition from an empty list");
            }
        }

        public static /* synthetic */ And copy$default(And and, Set<BooleanExpression<T>> set, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                set = and.operands;
            }
            return and.copy(set);
        }

        @NotNull
        public final Set<BooleanExpression<T>> component1() {
            return this.operands;
        }

        @NotNull
        public final And<T> copy(@NotNull Set<? extends BooleanExpression<? extends T>> set) {
            Intrinsics.checkNotNullParameter(set, "operands");
            return new And<>(set);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof And) && Intrinsics.areEqual((Object) this.operands, (Object) ((And) obj).operands);
        }

        @NotNull
        public final Set<BooleanExpression<T>> getOperands() {
            return this.operands;
        }

        public int hashCode() {
            return this.operands.hashCode();
        }

        @NotNull
        public BooleanExpression<T> simplify() {
            ArrayList arrayList = new ArrayList();
            for (Object next : this.operands) {
                if (!Intrinsics.areEqual((Object) (BooleanExpression) next, (Object) True.INSTANCE)) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((BooleanExpression) it.next()).simplify());
            }
            False falseR = False.INSTANCE;
            return arrayList2.contains(falseR) ? falseR : arrayList2.isEmpty() ? True.INSTANCE : arrayList2.size() == 1 ? (BooleanExpression) CollectionsKt.first(arrayList2) : new And(CollectionsKt.toSet(arrayList2));
        }

        @NotNull
        public String toString() {
            return "And(operands=" + this.operands + ')';
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public And(@NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
            this(ArraysKt.toSet((T[]) booleanExpressionArr));
            Intrinsics.checkNotNullParameter(booleanExpressionArr, "operands");
        }
    }

    @SourceDebugExtension({"SMAP\nBooleanExpression.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpression$Or\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,194:1\n766#2:195\n857#2,2:196\n1549#2:198\n1620#2,3:199\n*S KotlinDebug\n*F\n+ 1 BooleanExpression.kt\ncom/apollographql/apollo3/api/BooleanExpression$Or\n*L\n47#1:195\n47#1:196,2\n49#1:198\n49#1:199,3\n*E\n"})
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\"\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B'\b\u0016\u0012\u001e\u0010\u0004\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0005\"\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\u0002\u0010\u0006B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007HÆ\u0003J%\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u00002\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/apollographql/apollo3/api/BooleanExpression$Or;", "T", "", "Lcom/apollographql/apollo3/api/BooleanExpression;", "operands", "", "([Lcom/apollographql/apollo3/api/BooleanExpression;)V", "", "(Ljava/util/Set;)V", "getOperands", "()Ljava/util/Set;", "component1", "copy", "equals", "", "other", "hashCode", "", "simplify", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Or<T> extends BooleanExpression<T> {
        @NotNull
        private final Set<BooleanExpression<T>> operands;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Or(@NotNull Set<? extends BooleanExpression<? extends T>> set) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(set, "operands");
            this.operands = set;
            if (set.isEmpty()) {
                throw new IllegalStateException("Apollo: cannot create a 'Or' condition from an empty list");
            }
        }

        public static /* synthetic */ Or copy$default(Or or, Set<BooleanExpression<T>> set, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                set = or.operands;
            }
            return or.copy(set);
        }

        @NotNull
        public final Set<BooleanExpression<T>> component1() {
            return this.operands;
        }

        @NotNull
        public final Or<T> copy(@NotNull Set<? extends BooleanExpression<? extends T>> set) {
            Intrinsics.checkNotNullParameter(set, "operands");
            return new Or<>(set);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Or) && Intrinsics.areEqual((Object) this.operands, (Object) ((Or) obj).operands);
        }

        @NotNull
        public final Set<BooleanExpression<T>> getOperands() {
            return this.operands;
        }

        public int hashCode() {
            return this.operands.hashCode();
        }

        @NotNull
        public BooleanExpression<T> simplify() {
            ArrayList arrayList = new ArrayList();
            for (Object next : this.operands) {
                if (!Intrinsics.areEqual((Object) (BooleanExpression) next, (Object) False.INSTANCE)) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((BooleanExpression) it.next()).simplify());
            }
            True trueR = True.INSTANCE;
            return arrayList2.contains(trueR) ? trueR : arrayList2.isEmpty() ? False.INSTANCE : arrayList2.size() == 1 ? (BooleanExpression) CollectionsKt.first(arrayList2) : new Or(CollectionsKt.toSet(arrayList2));
        }

        @NotNull
        public String toString() {
            return CollectionsKt___CollectionsKt.joinToString$default(this.operands, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Or(@NotNull BooleanExpression<? extends T>... booleanExpressionArr) {
            this(ArraysKt.toSet((T[]) booleanExpressionArr));
            Intrinsics.checkNotNullParameter(booleanExpressionArr, "operands");
        }
    }

    private BooleanExpression() {
    }
}
