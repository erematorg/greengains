package com.ionspin.kotlin.bignum.integer;

import androidx.compose.runtime.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004*\u0004\b\u0004\u0010\u0005*\u0004\b\u0005\u0010\u00062\u00020\u0007B5\u0012\u0006\u0010\b\u001a\u00028\u0000\u0012\u0006\u0010\t\u001a\u00028\u0001\u0012\u0006\u0010\n\u001a\u00028\u0002\u0012\u0006\u0010\u000b\u001a\u00028\u0003\u0012\u0006\u0010\f\u001a\u00028\u0004\u0012\u0006\u0010\r\u001a\u00028\u0005¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0017\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0018\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0019\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010\u001a\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010\u001b\u001a\u00028\u0004HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000e\u0010\u001c\u001a\u00028\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010Jn\u0010\u001d\u001a&\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\b\b\u0002\u0010\b\u001a\u00028\u00002\b\b\u0002\u0010\t\u001a\u00028\u00012\b\b\u0002\u0010\n\u001a\u00028\u00022\b\b\u0002\u0010\u000b\u001a\u00028\u00032\b\b\u0002\u0010\f\u001a\u00028\u00042\b\b\u0002\u0010\r\u001a\u00028\u0005HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\t\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\n\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0013\u0010\u000b\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0014\u0010\u0010R\u0013\u0010\f\u001a\u00028\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\r\u001a\u00028\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0016\u0010\u0010¨\u0006&"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/Sextuple;", "A", "B", "C", "D", "E", "F", "", "a", "b", "c", "d", "e", "f", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getB", "getC", "getD", "getE", "getF", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/ionspin/kotlin/bignum/integer/Sextuple;", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Sextuple<A, B, C, D, E, F> {

    /* renamed from: a  reason: collision with root package name */
    private final A f7221a;

    /* renamed from: b  reason: collision with root package name */
    private final B f7222b;

    /* renamed from: c  reason: collision with root package name */
    private final C f7223c;

    /* renamed from: d  reason: collision with root package name */
    private final D f7224d;

    /* renamed from: e  reason: collision with root package name */
    private final E f7225e;

    /* renamed from: f  reason: collision with root package name */
    private final F f7226f;

    public Sextuple(A a2, B b3, C c3, D d2, E e3, F f2) {
        this.f7221a = a2;
        this.f7222b = b3;
        this.f7223c = c3;
        this.f7224d = d2;
        this.f7225e = e3;
        this.f7226f = f2;
    }

    public static /* synthetic */ Sextuple copy$default(Sextuple sextuple, A a2, B b3, C c3, D d2, E e3, F f2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            a2 = sextuple.f7221a;
        }
        if ((i3 & 2) != 0) {
            b3 = sextuple.f7222b;
        }
        B b4 = b3;
        if ((i3 & 4) != 0) {
            c3 = sextuple.f7223c;
        }
        C c4 = c3;
        if ((i3 & 8) != 0) {
            d2 = sextuple.f7224d;
        }
        D d3 = d2;
        if ((i3 & 16) != 0) {
            e3 = sextuple.f7225e;
        }
        E e4 = e3;
        if ((i3 & 32) != 0) {
            f2 = sextuple.f7226f;
        }
        return sextuple.copy(a2, b4, c4, d3, e4, f2);
    }

    public final A component1() {
        return this.f7221a;
    }

    public final B component2() {
        return this.f7222b;
    }

    public final C component3() {
        return this.f7223c;
    }

    public final D component4() {
        return this.f7224d;
    }

    public final E component5() {
        return this.f7225e;
    }

    public final F component6() {
        return this.f7226f;
    }

    @NotNull
    public final Sextuple<A, B, C, D, E, F> copy(A a2, B b3, C c3, D d2, E e3, F f2) {
        return new Sextuple(a2, b3, c3, d2, e3, f2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sextuple)) {
            return false;
        }
        Sextuple sextuple = (Sextuple) obj;
        return Intrinsics.areEqual((Object) this.f7221a, (Object) sextuple.f7221a) && Intrinsics.areEqual((Object) this.f7222b, (Object) sextuple.f7222b) && Intrinsics.areEqual((Object) this.f7223c, (Object) sextuple.f7223c) && Intrinsics.areEqual((Object) this.f7224d, (Object) sextuple.f7224d) && Intrinsics.areEqual((Object) this.f7225e, (Object) sextuple.f7225e) && Intrinsics.areEqual((Object) this.f7226f, (Object) sextuple.f7226f);
    }

    public final A getA() {
        return this.f7221a;
    }

    public final B getB() {
        return this.f7222b;
    }

    public final C getC() {
        return this.f7223c;
    }

    public final D getD() {
        return this.f7224d;
    }

    public final E getE() {
        return this.f7225e;
    }

    public final F getF() {
        return this.f7226f;
    }

    public int hashCode() {
        A a2 = this.f7221a;
        int i3 = 0;
        int hashCode = (a2 == null ? 0 : a2.hashCode()) * 31;
        B b3 = this.f7222b;
        int hashCode2 = (hashCode + (b3 == null ? 0 : b3.hashCode())) * 31;
        C c3 = this.f7223c;
        int hashCode3 = (hashCode2 + (c3 == null ? 0 : c3.hashCode())) * 31;
        D d2 = this.f7224d;
        int hashCode4 = (hashCode3 + (d2 == null ? 0 : d2.hashCode())) * 31;
        E e3 = this.f7225e;
        int hashCode5 = (hashCode4 + (e3 == null ? 0 : e3.hashCode())) * 31;
        F f2 = this.f7226f;
        if (f2 != null) {
            i3 = f2.hashCode();
        }
        return hashCode5 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Sextuple(a=");
        sb.append(this.f7221a);
        sb.append(", b=");
        sb.append(this.f7222b);
        sb.append(", c=");
        sb.append(this.f7223c);
        sb.append(", d=");
        sb.append(this.f7224d);
        sb.append(", e=");
        sb.append(this.f7225e);
        sb.append(", f=");
        return b.c(sb, this.f7226f, ')');
    }
}
