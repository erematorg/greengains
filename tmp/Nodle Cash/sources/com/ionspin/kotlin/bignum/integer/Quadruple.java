package com.ionspin.kotlin.bignum.integer;

import androidx.compose.runtime.b;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005B%\u0012\u0006\u0010\u0006\u001a\u00028\u0000\u0012\u0006\u0010\u0007\u001a\u00028\u0001\u0012\u0006\u0010\b\u001a\u00028\u0002\u0012\u0006\u0010\t\u001a\u00028\u0003¢\u0006\u0002\u0010\nJ\u000e\u0010\u0011\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0012\u001a\u00028\u0001HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0013\u001a\u00028\u0002HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0014\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\fJN\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00002\b\b\u0002\u0010\u0007\u001a\u00028\u00012\b\b\u0002\u0010\b\u001a\u00028\u00022\b\b\u0002\u0010\t\u001a\u00028\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0006\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u00028\u0001¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\b\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\t\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/ionspin/kotlin/bignum/integer/Quadruple;", "A", "B", "C", "D", "", "a", "b", "c", "d", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getA", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getB", "getC", "getD", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/ionspin/kotlin/bignum/integer/Quadruple;", "equals", "", "other", "hashCode", "", "toString", "", "bignum"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class Quadruple<A, B, C, D> {

    /* renamed from: a  reason: collision with root package name */
    private final A f7212a;

    /* renamed from: b  reason: collision with root package name */
    private final B f7213b;

    /* renamed from: c  reason: collision with root package name */
    private final C f7214c;

    /* renamed from: d  reason: collision with root package name */
    private final D f7215d;

    public Quadruple(A a2, B b3, C c3, D d2) {
        this.f7212a = a2;
        this.f7213b = b3;
        this.f7214c = c3;
        this.f7215d = d2;
    }

    public static /* synthetic */ Quadruple copy$default(Quadruple quadruple, A a2, B b3, C c3, D d2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            a2 = quadruple.f7212a;
        }
        if ((i3 & 2) != 0) {
            b3 = quadruple.f7213b;
        }
        if ((i3 & 4) != 0) {
            c3 = quadruple.f7214c;
        }
        if ((i3 & 8) != 0) {
            d2 = quadruple.f7215d;
        }
        return quadruple.copy(a2, b3, c3, d2);
    }

    public final A component1() {
        return this.f7212a;
    }

    public final B component2() {
        return this.f7213b;
    }

    public final C component3() {
        return this.f7214c;
    }

    public final D component4() {
        return this.f7215d;
    }

    @NotNull
    public final Quadruple<A, B, C, D> copy(A a2, B b3, C c3, D d2) {
        return new Quadruple<>(a2, b3, c3, d2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quadruple)) {
            return false;
        }
        Quadruple quadruple = (Quadruple) obj;
        return Intrinsics.areEqual((Object) this.f7212a, (Object) quadruple.f7212a) && Intrinsics.areEqual((Object) this.f7213b, (Object) quadruple.f7213b) && Intrinsics.areEqual((Object) this.f7214c, (Object) quadruple.f7214c) && Intrinsics.areEqual((Object) this.f7215d, (Object) quadruple.f7215d);
    }

    public final A getA() {
        return this.f7212a;
    }

    public final B getB() {
        return this.f7213b;
    }

    public final C getC() {
        return this.f7214c;
    }

    public final D getD() {
        return this.f7215d;
    }

    public int hashCode() {
        A a2 = this.f7212a;
        int i3 = 0;
        int hashCode = (a2 == null ? 0 : a2.hashCode()) * 31;
        B b3 = this.f7213b;
        int hashCode2 = (hashCode + (b3 == null ? 0 : b3.hashCode())) * 31;
        C c3 = this.f7214c;
        int hashCode3 = (hashCode2 + (c3 == null ? 0 : c3.hashCode())) * 31;
        D d2 = this.f7215d;
        if (d2 != null) {
            i3 = d2.hashCode();
        }
        return hashCode3 + i3;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("Quadruple(a=");
        sb.append(this.f7212a);
        sb.append(", b=");
        sb.append(this.f7213b);
        sb.append(", c=");
        sb.append(this.f7214c);
        sb.append(", d=");
        return b.c(sb, this.f7215d, ')');
    }
}
