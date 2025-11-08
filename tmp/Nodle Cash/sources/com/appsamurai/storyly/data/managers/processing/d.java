package com.appsamurai.storyly.data.managers.processing;

import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

public final class d {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<b> f3957a = new ArrayList();

    public static final class a extends Lambda implements Function1<b, Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ e f3958a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(e eVar) {
            super(1);
            this.f3958a = eVar;
        }

        public Object invoke(Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(bVar, "it");
            return Boolean.valueOf(bVar.f3950b == this.f3958a && bVar.f3949a == c.InQueue);
        }
    }

    public static final class b extends Lambda implements Function1<b, Boolean> {

        /* renamed from: a  reason: collision with root package name */
        public static final b f3959a = new b();

        public b() {
            super(1);
        }

        public Object invoke(Object obj) {
            b bVar = (b) obj;
            Intrinsics.checkNotNullParameter(bVar, "it");
            return Boolean.valueOf(bVar.f3949a == c.InQueue);
        }
    }

    public final b a() {
        b bVar = (b) CollectionsKt.firstOrNull(this.f3957a);
        if (bVar == null) {
            return null;
        }
        c cVar = c.Processing;
        Intrinsics.checkNotNullParameter(cVar, "<set-?>");
        bVar.f3949a = cVar;
        Function0<Unit> function0 = bVar.f3951c;
        if (function0 == null) {
            return bVar;
        }
        function0.invoke();
        return bVar;
    }

    public final void a(e eVar) {
        if (eVar == null) {
            eVar = null;
        } else {
            CollectionsKt__MutableCollectionsKt.removeAll(this.f3957a, new a(eVar));
        }
        if (eVar == null) {
            CollectionsKt__MutableCollectionsKt.removeAll(this.f3957a, b.f3959a);
        }
    }

    public final void a(b bVar) {
        b bVar2 = bVar == null ? null : bVar.f3953e;
        if (bVar2 != null) {
            this.f3957a.remove(bVar2);
            a(bVar2.f3953e);
        }
    }
}
