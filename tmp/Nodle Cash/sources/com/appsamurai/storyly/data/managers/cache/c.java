package com.appsamurai.storyly.data.managers.cache;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.appsamurai.storyly.data.a0;
import com.appsamurai.storyly.data.b0;
import com.appsamurai.storyly.data.q0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.x;
import com.appsamurai.storyly.data.z;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class c {

    /* renamed from: g  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f3886g = {androidx.compose.ui.autofill.a.m(c.class, "storylyGroupItems", "getStorylyGroupItems$storyly_release()Ljava/util/List;", 0)};
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f3887a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public Map<String, ? extends Map<String, ? extends List<String>>> f3888b = MapsKt.emptyMap();
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final ReadWriteProperty f3889c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    public Target<?> f3890d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public List<String> f3891e;

    /* renamed from: f  reason: collision with root package name */
    public int f3892f;

    public static final class a implements RequestListener<Drawable> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ c f3893a;

        public a(c cVar) {
            this.f3893a = cVar;
        }

        public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z2) {
            c cVar = this.f3893a;
            cVar.f3890d = null;
            cVar.b();
            return false;
        }

        public boolean onResourceReady(Object obj, Object obj2, Target target, DataSource dataSource, boolean z2) {
            Drawable drawable = (Drawable) obj;
            c cVar = this.f3893a;
            cVar.f3890d = null;
            cVar.b();
            return false;
        }
    }

    public static final class b extends ObservableProperty<List<? extends v>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f3894a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ c f3895b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Object obj, Object obj2, c cVar) {
            super(obj2);
            this.f3894a = obj;
            this.f3895b = cVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends v> list, List<? extends v> list2) {
            List list3;
            String str;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list4 = list2;
            List list5 = list;
            if (!this.f3895b.a().isEmpty()) {
                c cVar = this.f3895b;
                List<v> a2 = cVar.a();
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(a2, 10)), 16));
                for (v vVar : a2) {
                    String str2 = vVar.f4221a;
                    List<z> list6 = vVar.f4226f;
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(list6, 10)), 16));
                    for (z zVar : list6) {
                        String str3 = zVar.f4302a;
                        List<b0> list7 = zVar.f4303b.f3639a;
                        List list8 = null;
                        if (list7 != null) {
                            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list7, 10));
                            for (b0 b0Var : list7) {
                                a0 a0Var = b0Var == null ? null : b0Var.f3615j;
                                if (a0Var instanceof x) {
                                    x xVar = (x) a0Var;
                                    int ordinal = xVar.f4271t.ordinal();
                                    if (ordinal == 2) {
                                        str = xVar.f4253b;
                                    } else if (ordinal == 3) {
                                        str = Intrinsics.stringPlus(vVar.f4223c, xVar.f4254c);
                                    }
                                    arrayList.add(str);
                                } else if (a0Var instanceof q0) {
                                    q0 q0Var = (q0) a0Var;
                                    String str4 = q0Var.f4148c;
                                    if (str4 != null) {
                                        str = str4;
                                    } else {
                                        String str5 = q0Var.f4149d;
                                        if (str5 != null) {
                                            str = Intrinsics.stringPlus(vVar.f4223c, str5);
                                        }
                                    }
                                    arrayList.add(str);
                                }
                                str = null;
                                arrayList.add(str);
                            }
                            list8 = CollectionsKt.filterNotNull(arrayList);
                        }
                        Pair pair = TuplesKt.to(str3, list8);
                        linkedHashMap2.put(pair.getFirst(), pair.getSecond());
                    }
                    Pair pair2 = TuplesKt.to(str2, linkedHashMap2);
                    linkedHashMap.put(pair2.getFirst(), pair2.getSecond());
                }
                cVar.f3888b = linkedHashMap;
                c cVar2 = this.f3895b;
                List<T> take = CollectionsKt.take(cVar2.a(), 1);
                cVar2.a(true);
                if (!cVar2.a().isEmpty()) {
                    ArrayList arrayList2 = new ArrayList();
                    for (T t2 : take) {
                        Map map = (Map) cVar2.f3888b.get(t2.f4221a);
                        if (!(map == null || (list3 = (List) map.get(((z) CollectionsKt.first(t2.f4226f)).f4302a)) == null)) {
                            arrayList2.addAll(list3);
                        }
                    }
                    cVar2.f3891e.addAll(arrayList2);
                    cVar2.b();
                }
            }
        }
    }

    public c(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f3887a = context;
        Delegates delegates = Delegates.INSTANCE;
        List emptyList = CollectionsKt.emptyList();
        this.f3889c = new b(emptyList, emptyList, this);
        this.f3891e = new ArrayList();
        this.f3892f = -1;
    }

    @NotNull
    public final List<v> a() {
        return (List) this.f3889c.getValue(this, f3886g[0]);
    }

    public final void b() {
        int i3 = this.f3892f + 1;
        this.f3892f = i3;
        if (i3 < this.f3891e.size()) {
            this.f3890d = ((RequestBuilder) Glide.with(this.f3887a.getApplicationContext()).load(this.f3891e.get(this.f3892f)).diskCacheStrategy(DiskCacheStrategy.ALL)).listener(new a(this)).preload();
        }
    }

    public final void a(boolean z2) {
        if (z2) {
            Target<?> target = this.f3890d;
            if (target != null) {
                Glide.with(this.f3887a.getApplicationContext()).clear(target);
            }
            this.f3890d = null;
        }
        this.f3891e.clear();
        this.f3892f = -1;
    }
}
