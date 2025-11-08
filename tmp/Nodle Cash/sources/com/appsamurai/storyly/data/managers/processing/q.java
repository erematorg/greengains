package com.appsamurai.storyly.data.managers.processing;

import com.appsamurai.storyly.data.v;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

public final class q {

    public static final class a extends Lambda implements Function1<v, Comparable<?>> {

        /* renamed from: a  reason: collision with root package name */
        public static final a f4031a = new a();

        public a() {
            super(1);
        }

        public Object invoke(Object obj) {
            v vVar = (v) obj;
            Intrinsics.checkNotNullParameter(vVar, "it");
            return Boolean.valueOf(!vVar.f4230j);
        }
    }

    public static final class b extends Lambda implements Function1<v, Comparable<?>> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f4032a = new b();

        public b() {
            super(1);
        }

        public Object invoke(Object obj) {
            v vVar = (v) obj;
            Intrinsics.checkNotNullParameter(vVar, "it");
            return Boolean.valueOf(!vVar.f4230j && vVar.f4237q);
        }
    }

    public static final class c extends Lambda implements Function1<v, Comparable<?>> {

        /* renamed from: a  reason: collision with root package name */
        public static final c f4033a = new c();

        public c() {
            super(1);
        }

        public Object invoke(Object obj) {
            v vVar = (v) obj;
            Intrinsics.checkNotNullParameter(vVar, "it");
            return Integer.valueOf(vVar.f4225e);
        }
    }

    @NotNull
    public final List<v> a(@NotNull List<v> list) {
        Intrinsics.checkNotNullParameter(list, "storylyGroupItems");
        int i3 = 0;
        List<v> sortedWith = CollectionsKt.sortedWith(list, ComparisonsKt.compareBy((Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{a.f4031a, b.f4032a, c.f4033a}));
        for (T next : sortedWith) {
            int i4 = i3 + 1;
            if (i3 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ((v) next).f4241u = Integer.valueOf(i3);
            i3 = i4;
        }
        return sortedWith;
    }
}
