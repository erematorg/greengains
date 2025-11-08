package com.ionspin.kotlin.bignum.integer;

import androidx.compose.runtime.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004*\u0004\b\u0004\u0010\u00052\u00020\u0006B-\u0012\u0006\u0010\u0007\u001a\u00028\u0000\u0012\u0006\u0010\b\u001a\u00028\u0001\u0012\u0006\u0010\t\u001a\u00028\u0002\u0012\u0006\u0010\n\u001a\u00028\u0003\u0012\u0006\u0010\u000b\u001a\u00028\u0004¢\u0006\u0002\u0010\fJ\u000e\u0010\u0014\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0015\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0016\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0017\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0018\u001a\u00028\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ^\u0010\u0019\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u00002\b\b\u0002\u0010\u0007\u001a\u00028\u00002\b\b\u0002\u0010\b\u001a\u00028\u00012\b\b\u0002\u0010\t\u001a\u00028\u00022\b\b\u0002\u0010\n\u001a\u00028\u00032\b\b\u0002\u0010\u000b\u001a\u00028\u0004HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0013\u0010\u0007\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\b\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\t\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\n\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0012\u0010\u000eR\u0013\u0010\u000b\u001a\u00028\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0013\u0010\u000e¨\u0006\""}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/Quintuple;", "A", "B", "C", "D", "E", "", "a", "b", "c", "d", "e", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getB", "getC", "getD", "getE", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/ionspin/kotlin/bignum/integer/Quintuple;", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Quintuple<A, B, C, D, E> {

    /* renamed from: a  reason: collision with root package name */
    private final A f7216a;

    /* renamed from: b  reason: collision with root package name */
    private final B f7217b;

    /* renamed from: c  reason: collision with root package name */
    private final C f7218c;

    /* renamed from: d  reason: collision with root package name */
    private final D f7219d;

    /* renamed from: e  reason: collision with root package name */
    private final E f7220e;

    public Quintuple(A a2, B b3, C c3, D d2, E e3) {
        this.f7216a = a2;
        this.f7217b = b3;
        this.f7218c = c3;
        this.f7219d = d2;
        this.f7220e = e3;
    }

    public static /* synthetic */ Quintuple copy$default(Quintuple quintuple, A a2, B b3, C c3, D d2, E e3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            a2 = quintuple.f7216a;
        }
        if ((i3 & 2) != 0) {
            b3 = quintuple.f7217b;
        }
        B b4 = b3;
        if ((i3 & 4) != 0) {
            c3 = quintuple.f7218c;
        }
        C c4 = c3;
        if ((i3 & 8) != 0) {
            d2 = quintuple.f7219d;
        }
        D d3 = d2;
        if ((i3 & 16) != 0) {
            e3 = quintuple.f7220e;
        }
        return quintuple.copy(a2, b4, c4, d3, e3);
    }

    public final A component1() {
        return this.f7216a;
    }

    public final B component2() {
        return this.f7217b;
    }

    public final C component3() {
        return this.f7218c;
    }

    public final D component4() {
        return this.f7219d;
    }

    public final E component5() {
        return this.f7220e;
    }

    @NotNull
    public final Quintuple<A, B, C, D, E> copy(A a2, B b3, C c3, D d2, E e3) {
        return new Quintuple(a2, b3, c3, d2, e3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quintuple)) {
            return false;
        }
        Quintuple quintuple = (Quintuple) obj;
        return Intrinsics.areEqual((Object) this.f7216a, (Object) quintuple.f7216a) && Intrinsics.areEqual((Object) this.f7217b, (Object) quintuple.f7217b) && Intrinsics.areEqual((Object) this.f7218c, (Object) quintuple.f7218c) && Intrinsics.areEqual((Object) this.f7219d, (Object) quintuple.f7219d) && Intrinsics.areEqual((Object) this.f7220e, (Object) quintuple.f7220e);
    }

    public final A getA() {
        return this.f7216a;
    }

    public final B getB() {
        return this.f7217b;
    }

    public final C getC() {
        return this.f7218c;
    }

    public final D getD() {
        return this.f7219d;
    }

    public final E getE() {
        return this.f7220e;
    }

    public int hashCode() {
        A a2 = this.f7216a;
        int i3 = 0;
        int hashCode = (a2 == null ? 0 : a2.hashCode()) * 31;
        B b3 = this.f7217b;
        int hashCode2 = (hashCode + (b3 == null ? 0 : b3.hashCode())) * 31;
        C c3 = this.f7218c;
        int hashCode3 = (hashCode2 + (c3 == null ? 0 : c3.hashCode())) * 31;
        D d2 = this.f7219d;
        int hashCode4 = (hashCode3 + (d2 == null ? 0 : d2.hashCode())) * 31;
        E e3 = this.f7220e;
        if (e3 != null) {
            i3 = e3.hashCode();
        }
        return hashCode4 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Quintuple(a=");
        sb.append(this.f7216a);
        sb.append(", b=");
        sb.append(this.f7217b);
        sb.append(", c=");
        sb.append(this.f7218c);
        sb.append(", d=");
        sb.append(this.f7219d);
        sb.append(", e=");
        return b.c(sb, this.f7220e, ')');
    }
}
