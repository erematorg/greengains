package app.cash.sqldelight.db;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \t*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0003\b\t\nJ\u000e\u0010\u0006\u001a\u00028\u0000H¦@¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0003\u001a\u00028\u00008VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Lapp/cash/sqldelight/db/QueryResult;", "T", "", "value", "getValue", "()Ljava/lang/Object;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "AsyncValue", "Companion", "Value", "Lapp/cash/sqldelight/db/QueryResult$AsyncValue;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface QueryResult<T> {
    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @JvmInline
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B%\u0012\u001c\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00028\u0001H@¢\u0006\u0004\b\u000b\u0010\fJ\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019R&\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\t\u0001\u0003\u0001\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¨\u0006\u001a"}, d2 = {"Lapp/cash/sqldelight/db/QueryResult$AsyncValue;", "T", "Lapp/cash/sqldelight/db/QueryResult;", "getter", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "constructor-impl", "(Lkotlin/jvm/functions/Function1;)Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function1;", "await", "await-impl", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", "other", "equals-impl", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Lkotlin/jvm/functions/Function1;)I", "toString", "", "toString-impl", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/String;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AsyncValue<T> implements QueryResult<T> {
        @NotNull
        private final Function1<Continuation<? super T>, Object> getter;

        private /* synthetic */ AsyncValue(Function1 function1) {
            this.getter = function1;
        }

        @Nullable
        /* renamed from: await-impl  reason: not valid java name */
        public static Object m8078awaitimpl(Function1<? super Continuation<? super T>, ? extends Object> function1, @NotNull Continuation<? super T> continuation) {
            return function1.invoke(continuation);
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ AsyncValue m8079boximpl(Function1 function1) {
            return new AsyncValue(function1);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static <T> Function1<Continuation<? super T>, Object> m8080constructorimpl(@NotNull Function1<? super Continuation<? super T>, ? extends Object> function1) {
            Intrinsics.checkNotNullParameter(function1, "getter");
            return function1;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8081equalsimpl(Function1<? super Continuation<? super T>, ? extends Object> function1, Object obj) {
            return (obj instanceof AsyncValue) && Intrinsics.areEqual((Object) function1, (Object) ((AsyncValue) obj).m8086unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8082equalsimpl0(Function1<? super Continuation<? super T>, ? extends Object> function1, Function1<? super Continuation<? super T>, ? extends Object> function12) {
            return Intrinsics.areEqual((Object) function1, (Object) function12);
        }

        /* renamed from: getValue-impl  reason: not valid java name */
        public static T m8083getValueimpl(Function1<? super Continuation<? super T>, ? extends Object> function1) {
            return m8079boximpl(function1).getValue();
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8084hashCodeimpl(Function1<? super Continuation<? super T>, ? extends Object> function1) {
            return function1.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8085toStringimpl(Function1<? super Continuation<? super T>, ? extends Object> function1) {
            return "AsyncValue(getter=" + function1 + ')';
        }

        @Nullable
        public Object await(@NotNull Continuation<? super T> continuation) {
            return m8078awaitimpl(this.getter, continuation);
        }

        public boolean equals(Object obj) {
            return m8081equalsimpl(this.getter, obj);
        }

        public T getValue() {
            return DefaultImpls.getValue(this);
        }

        public int hashCode() {
            return m8084hashCodeimpl(this.getter);
        }

        public String toString() {
            return m8085toStringimpl(this.getter);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ Function1 m8086unboximpl() {
            return this.getter;
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\t"}, d2 = {"Lapp/cash/sqldelight/db/QueryResult$Companion;", "", "()V", "Unit", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "getUnit-mlR-ZEE", "()Ljava/lang/Object;", "Ljava/lang/Object;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Object Unit = Value.m8090constructorimpl(Unit.INSTANCE);

        private Companion() {
        }

        @NotNull
        /* renamed from: getUnit-mlR-ZEE  reason: not valid java name */
        public final Object m8087getUnitmlRZEE() {
            return Unit;
        }
    }

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <T> T getValue(@NotNull QueryResult<T> queryResult) {
            throw new IllegalStateException("The driver used with SQLDelight is asynchronous, so SQLDelight should be configured for\nasynchronous usage:\n\nsqldelight {\n  databases {\n    MyDatabase {\n      generateAsync = true\n    }\n  }\n}");
        }
    }

    @JvmInline
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0001¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\t\u001a\u00028\u0001H@¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0003\u001a\u00028\u0001X\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007\u0001\u0003¨\u0006\u001a"}, d2 = {"Lapp/cash/sqldelight/db/QueryResult$Value;", "T", "Lapp/cash/sqldelight/db/QueryResult;", "value", "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "await", "await-impl", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/Object;)I", "toString", "", "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "runtime"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Value<T> implements QueryResult<T> {
        private final T value;

        private /* synthetic */ Value(Object obj) {
            this.value = obj;
        }

        @Nullable
        /* renamed from: await-impl  reason: not valid java name */
        public static Object m8088awaitimpl(Object obj, @NotNull Continuation<? super T> continuation) {
            return obj;
        }

        /* renamed from: box-impl  reason: not valid java name */
        public static final /* synthetic */ Value m8089boximpl(Object obj) {
            return new Value(obj);
        }

        @NotNull
        /* renamed from: constructor-impl  reason: not valid java name */
        public static <T> Object m8090constructorimpl(T t2) {
            return t2;
        }

        /* renamed from: equals-impl  reason: not valid java name */
        public static boolean m8091equalsimpl(Object obj, Object obj2) {
            return (obj2 instanceof Value) && Intrinsics.areEqual(obj, ((Value) obj2).m8095unboximpl());
        }

        /* renamed from: equals-impl0  reason: not valid java name */
        public static final boolean m8092equalsimpl0(Object obj, Object obj2) {
            return Intrinsics.areEqual(obj, obj2);
        }

        /* renamed from: hashCode-impl  reason: not valid java name */
        public static int m8093hashCodeimpl(Object obj) {
            if (obj == null) {
                return 0;
            }
            return obj.hashCode();
        }

        /* renamed from: toString-impl  reason: not valid java name */
        public static String m8094toStringimpl(Object obj) {
            return "Value(value=" + obj + ')';
        }

        @Nullable
        public Object await(@NotNull Continuation<? super T> continuation) {
            return m8088awaitimpl(this.value, continuation);
        }

        public boolean equals(Object obj) {
            return m8091equalsimpl(this.value, obj);
        }

        public T getValue() {
            return this.value;
        }

        public int hashCode() {
            return m8093hashCodeimpl(this.value);
        }

        public String toString() {
            return m8094toStringimpl(this.value);
        }

        /* renamed from: unbox-impl  reason: not valid java name */
        public final /* synthetic */ Object m8095unboximpl() {
            return this.value;
        }
    }

    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    T getValue();
}
