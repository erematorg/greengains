package com.appsamurai.storyly.storylypresenter.storylyheader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.OneShotPreDrawListener;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.ShareType;
import com.appsamurai.storyly.StoryGroupType;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.m0;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.util.ui.k;
import com.appsamurai.storyly.util.ui.l;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: o  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5458o;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f5459a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f5460b;

    /* renamed from: c  reason: collision with root package name */
    public c f5461c;

    /* renamed from: d  reason: collision with root package name */
    public final int f5462d;

    /* renamed from: e  reason: collision with root package name */
    public final int f5463e;

    /* renamed from: f  reason: collision with root package name */
    public final float f5464f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final ReadWriteProperty f5465g = new g((Object) null, (Object) null, this);
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final ReadWriteProperty f5466h = new h((Object) null, (Object) null, this);

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f5467i;

    /* renamed from: j  reason: collision with root package name */
    public Function2<? super Long, ? super Long, Unit> f5468j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f5469k;

    /* renamed from: l  reason: collision with root package name */
    public Function0<Unit> f5470l;

    /* renamed from: m  reason: collision with root package name */
    public Function0<Unit> f5471m;

    /* renamed from: n  reason: collision with root package name */
    public Function0<Unit> f5472n;

    public class c {

        /* renamed from: l  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f5488l;
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final b f5489a;
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public final Lazy f5490b;
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final Lazy f5491c = LazyKt.lazy(new h(this));
        @NotNull

        /* renamed from: d  reason: collision with root package name */
        public final Lazy f5492d = LazyKt.lazy(new d(this));
        @NotNull

        /* renamed from: e  reason: collision with root package name */
        public final Lazy f5493e;
        @NotNull

        /* renamed from: f  reason: collision with root package name */
        public final Lazy f5494f;
        @NotNull

        /* renamed from: g  reason: collision with root package name */
        public final Lazy f5495g;
        @NotNull

        /* renamed from: h  reason: collision with root package name */
        public final ReadWriteProperty f5496h;
        @NotNull

        /* renamed from: i  reason: collision with root package name */
        public final ReadWriteProperty f5497i;
        @Nullable

        /* renamed from: j  reason: collision with root package name */
        public Integer f5498j;

        /* renamed from: k  reason: collision with root package name */
        public final /* synthetic */ a f5499k;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$c$a  reason: collision with other inner class name */
        public static final class C0042a extends Lambda implements Function0<CardView> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5500a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5501b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0042a(c cVar, a aVar) {
                super(0);
                this.f5500a = cVar;
                this.f5501b = aVar;
            }

            public Object invoke() {
                CardView cardView = new CardView(this.f5500a.f5489a.f5483a.getRoot().getContext());
                a aVar = this.f5501b;
                cardView.setCardElevation(0.0f);
                cardView.setRadius(aVar.f5464f);
                cardView.setCardBackgroundColor(aVar.f5460b.getGroup$storyly_release().getIconBackgroundColor$storyly_release());
                return cardView;
            }
        }

        public static final class b extends Lambda implements Function0<Drawable> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5502a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public b(c cVar) {
                super(0);
                this.f5502a = cVar;
            }

            public Object invoke() {
                return AppCompatResources.getDrawable(this.f5502a.f5489a.f5483a.getRoot().getContext(), R.drawable.st_close);
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$c$c  reason: collision with other inner class name */
        public static final class C0043c extends Lambda implements Function0<Drawable> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5503a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0043c(c cVar) {
                super(0);
                this.f5503a = cVar;
            }

            public Object invoke() {
                return AppCompatResources.getDrawable(this.f5503a.f5489a.f5483a.getRoot().getContext(), R.drawable.st_share_icon);
            }
        }

        public static final class d extends Lambda implements Function0<FrameLayout> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5504a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public d(c cVar) {
                super(0);
                this.f5504a = cVar;
            }

            public Object invoke() {
                return new FrameLayout(this.f5504a.f5489a.f5483a.getRoot().getContext());
            }
        }

        public static final class e extends Lambda implements Function0<k> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5505a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5506b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public e(c cVar, a aVar) {
                super(0);
                this.f5505a = cVar;
                this.f5506b = aVar;
            }

            public Object invoke() {
                Context context = this.f5505a.f5489a.f5483a.getRoot().getContext();
                Intrinsics.checkNotNullExpressionValue(context, "parentBinding.root.context");
                return new k(context, this.f5506b.f5460b, false, 4);
            }
        }

        public static final class f extends ObservableProperty<String> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5507a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public f(Object obj, Object obj2, c cVar) {
                super(null);
                this.f5507a = cVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, String str, String str2) {
                String str3;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                String str4 = str2;
                String str5 = str;
                RequestManager with = Glide.with(this.f5507a.f5489a.f5483a.getRoot().getContext().getApplicationContext());
                c cVar = this.f5507a;
                v c3 = cVar.f5499k.c();
                if (c3 == null) {
                    str3 = null;
                } else {
                    String stringPlus = Intrinsics.stringPlus(c3.f4223c, c3.f4224d);
                    if (StringsKt__StringsJVMKt.startsWith$default(c3.f4224d, "http", false, 2, (Object) null)) {
                        stringPlus = c3.f4224d;
                    }
                    str3 = (c3.f4232l == null || cVar.i() == null || c3.f4232l.get(cVar.i()) == null) ? stringPlus : Intrinsics.stringPlus(c3.f4223c, c3.f4232l.get(cVar.i()));
                }
                ((RequestBuilder) with.load(str3).centerCrop()).into((ImageView) this.f5507a.f5491c.getValue());
            }
        }

        public static final class g extends ObservableProperty<z> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5508a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5509b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public g(Object obj, Object obj2, c cVar, a aVar) {
                super(null);
                this.f5508a = cVar;
                this.f5509b = aVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
                List<z> list;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                z zVar3 = zVar2;
                z zVar4 = zVar;
                c cVar = this.f5508a;
                TextView textView = cVar.f5489a.f5485c;
                z h3 = cVar.h();
                String str = null;
                textView.setText(h3 == null ? null : h3.f4305d);
                Context context = this.f5508a.f5489a.f5483a.getRoot().getContext();
                int i3 = R.string.st_desc_story_index;
                Integer g2 = this.f5508a.g();
                Integer valueOf = g2 == null ? null : Integer.valueOf(g2.intValue() + 1);
                v c3 = this.f5509b.c();
                String string = context.getString(i3, new Object[]{valueOf, (c3 == null || (list = c3.f4226f) == null) ? null : Integer.valueOf(list.size())});
                Intrinsics.checkNotNullExpressionValue(string, "parentBinding.root.conte…ories?.size\n            )");
                b bVar = this.f5508a.f5489a;
                RelativeLayout relativeLayout = bVar.f5484b;
                Context context2 = bVar.f5483a.getRoot().getContext();
                int i4 = R.string.st_desc_title_text;
                z h4 = this.f5508a.h();
                if (h4 != null) {
                    str = h4.f4305d;
                }
                relativeLayout.setContentDescription(context2.getString(i4, new Object[]{string, str}));
                l.a(this.f5508a.f5489a.f5484b);
            }
        }

        public static final class h extends Lambda implements Function0<ImageView> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ c f5510a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public h(c cVar) {
                super(0);
                this.f5510a = cVar;
            }

            public Object invoke() {
                return new ImageView(this.f5510a.f5489a.f5483a.getRoot().getContext());
            }
        }

        static {
            Class<c> cls = c.class;
            f5488l = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "thematicIconLabel", "getThematicIconLabel$storyly_release()Ljava/lang/String;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0)};
        }

        public c(@NotNull a aVar, b bVar) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(bVar, "parentBinding");
            this.f5499k = aVar;
            this.f5489a = bVar;
            this.f5490b = LazyKt.lazy(new C0042a(this, aVar));
            this.f5493e = LazyKt.lazy(new e(this, aVar));
            this.f5494f = LazyKt.lazy(new b(this));
            this.f5495g = LazyKt.lazy(new C0043c(this));
            Delegates delegates = Delegates.INSTANCE;
            Boolean bool = null;
            this.f5496h = new f((Object) null, (Object) null, this);
            this.f5497i = new g((Object) null, (Object) null, this, aVar);
            RelativeLayout c3 = bVar.c();
            c3.setImportantForAccessibility(1);
            l.a(c3, new com.appsamurai.storyly.util.ui.e());
            c3.setContentDescription("");
            bVar.d().setImportantForAccessibility(2);
            FrameLayout b3 = bVar.b();
            m0 storylyStyle$storyly_release = aVar.f5460b.getStorylyStyle$storyly_release();
            Boolean c4 = storylyStyle$storyly_release == null ? null : storylyStyle$storyly_release.c();
            int i3 = 8;
            b3.setVisibility(c4 == null ? aVar.f5460b.getStory$storyly_release().isHeaderIconVisible$storyly_release() : c4.booleanValue() ? 0 : 8);
            TextView d2 = bVar.d();
            m0 storylyStyle$storyly_release2 = aVar.f5460b.getStorylyStyle$storyly_release();
            Boolean d3 = storylyStyle$storyly_release2 == null ? null : storylyStyle$storyly_release2.d();
            d2.setVisibility(d3 == null ? aVar.f5460b.getStory$storyly_release().isTitleVisible$storyly_release() : d3.booleanValue() ? 0 : 4);
            ImageView a2 = bVar.a();
            m0 storylyStyle$storyly_release3 = aVar.f5460b.getStorylyStyle$storyly_release();
            bool = storylyStyle$storyly_release3 != null ? storylyStyle$storyly_release3.b() : bool;
            a2.setVisibility(bool == null ? aVar.f5460b.getStory$storyly_release().isCloseButtonVisible$storyly_release() : bool.booleanValue() ? 0 : i3);
            ImageView a3 = bVar.a();
            Drawable closeButtonIcon$storyly_release = aVar.f5460b.getStory$storyly_release().getCloseButtonIcon$storyly_release();
            a3.setImageDrawable(closeButtonIcon$storyly_release == null ? b() : closeButtonIcon$storyly_release);
            bVar.d().setTextColor(aVar.f5460b.getStory$storyly_release().getTitleColor$storyly_release());
            bVar.d().setTypeface(aVar.f5460b.getStory$storyly_release().getTitleTypeface$storyly_release());
            a().addView(f());
            FrameLayout d4 = d();
            CardView a4 = a();
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            int a5 = aVar.f5463e + aVar.f5462d;
            layoutParams.setMargins(a5, a5, a5, a5);
            Unit unit = Unit.INSTANCE;
            d4.addView(a4, layoutParams);
            bVar.b().addView(d(), -1, -1);
            e().setBorderColor$storyly_release(aVar.f5460b.getStory$storyly_release().getHeaderIconBorderColor$storyly_release());
            e().setAvatarBackgroundColor$storyly_release(0);
            bVar.a().setOnClickListener(new F0.a(aVar, 1));
            a(aVar.f5460b.getGroup$storyly_release().getIconThematicImageLabel$storyly_release());
            d().addView(e());
        }

        public void a(long j2) {
        }

        public void b(long j2) {
        }

        @Nullable
        public final Drawable c() {
            return (Drawable) this.f5495g.getValue();
        }

        public final FrameLayout d() {
            return (FrameLayout) this.f5492d.getValue();
        }

        public final k e() {
            return (k) this.f5493e.getValue();
        }

        public final ImageView f() {
            return (ImageView) this.f5491c.getValue();
        }

        @Nullable
        public Integer g() {
            return this.f5498j;
        }

        @Nullable
        public z h() {
            return (z) this.f5497i.getValue(this, f5488l[1]);
        }

        @Nullable
        public final String i() {
            return (String) this.f5496h.getValue(this, f5488l[0]);
        }

        public void j() {
            this.f5489a.f5483a.getRoot().animate().cancel();
            this.f5489a.f5483a.getRoot().animate().alpha(0.0f).setDuration(400).withStartAction(new F0.b(this, 0)).withEndAction(new F0.b(this, 1));
        }

        public void k() {
        }

        public void l() {
        }

        public void m() {
        }

        public void n() {
            this.f5489a.f5483a.getRoot().animate().cancel();
            this.f5489a.f5483a.getRoot().animate().alpha(1.0f).setDuration(400).withStartAction(new F0.b(this, 2));
        }

        public static final void b(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            cVar.f5489a.f5483a.getRoot().setVisibility(8);
        }

        public static final void c(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            cVar.getClass();
            cVar.f5489a.f5483a.getRoot().setVisibility(0);
        }

        public void a(@Nullable Integer num) {
        }

        public static final void a(a aVar, View view) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Function0<Unit> function0 = aVar.f5467i;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onClosed");
                function0 = null;
            }
            function0.invoke();
        }

        public final CardView a() {
            return (CardView) this.f5490b.getValue();
        }

        public final Drawable b() {
            return (Drawable) this.f5494f.getValue();
        }

        public final void a(@Nullable String str) {
            this.f5496h.setValue(this, f5488l[0], str);
        }

        public void b(@Nullable Integer num) {
            this.f5498j = num;
        }

        public void a(@Nullable z zVar) {
            this.f5497i.setValue(this, f5488l[1], zVar);
        }

        public void a(@Nullable Long l2) {
            n();
        }

        public static final void a(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            cVar.getClass();
        }
    }

    public final class d extends c {

        /* renamed from: q  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f5511q;
        @NotNull

        /* renamed from: m  reason: collision with root package name */
        public final com.appsamurai.storyly.databinding.b f5512m;
        @NotNull

        /* renamed from: n  reason: collision with root package name */
        public final Lazy f5513n;
        @NotNull

        /* renamed from: o  reason: collision with root package name */
        public final ReadWriteProperty f5514o = new b((Object) null, (Object) null, this);
        @NotNull

        /* renamed from: p  reason: collision with root package name */
        public final ReadWriteProperty f5515p;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$d$a  reason: collision with other inner class name */
        public static final class C0044a extends Lambda implements Function0<b> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ d f5516a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5517b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0044a(d dVar, a aVar) {
                super(0);
                this.f5516a = dVar;
                this.f5517b = aVar;
            }

            public Object invoke() {
                LinearLayout linearLayout = this.f5516a.f5512m.f4328c;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.stHeaderPagerView");
                return new b(linearLayout, this.f5517b.f5460b);
            }
        }

        public static final class b extends ObservableProperty<z> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ d f5518a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public b(Object obj, Object obj2, d dVar) {
                super(null);
                this.f5518a = dVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
                int i3;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                z zVar3 = zVar2;
                z zVar4 = zVar;
                d dVar = this.f5518a;
                dVar.f5497i.setValue(dVar, c.f5488l[1], dVar.h());
                d dVar2 = this.f5518a;
                ImageView imageView = dVar2.f5512m.f4331f;
                z h3 = dVar2.h();
                ShareType shareType = null;
                if ((h3 == null ? null : h3.f4312k) != ShareType.Disabled) {
                    z h4 = this.f5518a.h();
                    if (h4 != null) {
                        shareType = h4.f4312k;
                    }
                    if (shareType != null) {
                        i3 = 0;
                        imageView.setVisibility(i3);
                    }
                }
                i3 = 8;
                imageView.setVisibility(i3);
            }
        }

        public static final class c extends ObservableProperty<Integer> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ d f5519a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5520b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public c(Object obj, Object obj2, d dVar, a aVar) {
                super(null);
                this.f5519a = dVar;
                this.f5520b = aVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
                List<z> list;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                Integer num3 = num2;
                Integer num4 = num;
                d dVar = this.f5519a;
                dVar.f5498j = dVar.g();
                this.f5519a.o().b(this.f5519a.g());
                Context context = this.f5519a.f5489a.f5483a.getRoot().getContext();
                int i3 = R.string.st_desc_story_index;
                Integer g2 = this.f5519a.g();
                Integer num5 = null;
                Integer valueOf = g2 == null ? null : Integer.valueOf(g2.intValue() + 1);
                v c3 = this.f5520b.c();
                if (!(c3 == null || (list = c3.f4226f) == null)) {
                    num5 = Integer.valueOf(list.size());
                }
                String string = context.getString(i3, new Object[]{valueOf, num5});
                Intrinsics.checkNotNullExpressionValue(string, "parentBinding.root.conte…ories?.size\n            )");
                this.f5519a.f5512m.f4328c.setContentDescription(string);
            }
        }

        static {
            Class<d> cls = d.class;
            f5511q = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(@NotNull a aVar, com.appsamurai.storyly.databinding.b bVar) {
            super(aVar, new b(bVar));
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(bVar, "binding");
            this.f5512m = bVar;
            this.f5513n = LazyKt.lazy(new C0044a(this, aVar));
            Delegates delegates = Delegates.INSTANCE;
            StoryGroupType storyGroupType = null;
            this.f5515p = new c((Object) null, (Object) null, this, aVar);
            o().a(aVar.a());
            o().a((Function2<? super Long, ? super Long, Unit>) aVar.b());
            o().a(aVar.c());
            ImageView imageView = bVar.f4331f;
            Drawable shareButtonIcon$storyly_release = aVar.f5460b.getStory$storyly_release().getShareButtonIcon$storyly_release();
            imageView.setImageDrawable(shareButtonIcon$storyly_release == null ? c() : shareButtonIcon$storyly_release);
            bVar.f4331f.setOnClickListener(new F0.a(aVar, 2));
            TextView textView = bVar.f4332g;
            v c3 = aVar.c();
            textView.setVisibility((c3 != null ? c3.e() : storyGroupType) == StoryGroupType.Ad ? 0 : 8);
        }

        public static final void b(a aVar, View view) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Function0<Unit> function0 = aVar.f5470l;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onShareRequest");
                function0 = null;
            }
            function0.invoke();
        }

        public void a(@Nullable z zVar) {
            this.f5514o.setValue(this, f5511q[0], zVar);
        }

        @Nullable
        public Integer g() {
            return (Integer) this.f5515p.getValue(this, f5511q[1]);
        }

        @Nullable
        public z h() {
            return (z) this.f5514o.getValue(this, f5511q[0]);
        }

        public final b o() {
            return (b) this.f5513n.getValue();
        }

        public void a(@Nullable Integer num) {
            o().a(num);
        }

        public void b(@Nullable Integer num) {
            this.f5515p.setValue(this, f5511q[1], num);
        }
    }

    public final class e extends c {

        /* renamed from: s  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f5521s;
        @NotNull

        /* renamed from: m  reason: collision with root package name */
        public final com.appsamurai.storyly.databinding.f f5522m;
        @NotNull

        /* renamed from: n  reason: collision with root package name */
        public final Lazy f5523n;
        @NotNull

        /* renamed from: o  reason: collision with root package name */
        public final ReadWriteProperty f5524o = new d((Object) null, (Object) null, this);
        @NotNull

        /* renamed from: p  reason: collision with root package name */
        public final Lazy f5525p;
        @NotNull

        /* renamed from: q  reason: collision with root package name */
        public final ReadWriteProperty f5526q;

        /* renamed from: r  reason: collision with root package name */
        public final /* synthetic */ a f5527r;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$e$a  reason: collision with other inner class name */
        public static final class C0045a extends Lambda implements Function0<Drawable> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5528a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ e f5529b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0045a(a aVar, e eVar) {
                super(0);
                this.f5528a = aVar;
                this.f5529b = eVar;
            }

            public Object invoke() {
                Drawable storyOptionsIcon$storyly_release = this.f5528a.f5460b.getMoments$storyly_release().getIconStyling$storyly_release().getStoryOptionsIcon$storyly_release();
                return storyOptionsIcon$storyly_release == null ? AppCompatResources.getDrawable(this.f5529b.f5489a.f5483a.getRoot().getContext(), R.drawable.st_header_options) : storyOptionsIcon$storyly_release;
            }
        }

        public static final class b extends Lambda implements Function0<b> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ e f5530a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5531b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public b(e eVar, a aVar) {
                super(0);
                this.f5530a = eVar;
                this.f5531b = aVar;
            }

            public Object invoke() {
                LinearLayout linearLayout = this.f5530a.f5522m.f4349d;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.stmHeaderPagerView");
                return new b(linearLayout, this.f5531b.f5460b);
            }
        }

        public static final class c implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ e f5532a;

            public c(View view, e eVar) {
                this.f5532a = eVar;
            }

            public final void run() {
                int width = this.f5532a.f5522m.f4348c.getWidth();
                int width2 = this.f5532a.f5522m.f4350e.getWidth();
                LinearLayout linearLayout = this.f5532a.f5522m.f4349d;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.stmHeaderPagerView");
                ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
                int i3 = 0;
                int marginStart = width2 + (layoutParams instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams) : 0);
                int width3 = this.f5532a.f5522m.f4352g.getWidth();
                ImageView imageView = this.f5532a.f5522m.f4352g;
                Intrinsics.checkNotNullExpressionValue(imageView, "binding.stmOptionsButton");
                ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                int marginEnd = width3 + (layoutParams2 instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) layoutParams2) : 0);
                int width4 = this.f5532a.f5522m.f4347b.getWidth();
                ImageView imageView2 = this.f5532a.f5522m.f4347b;
                Intrinsics.checkNotNullExpressionValue(imageView2, "binding.stmCloseButton");
                ViewGroup.LayoutParams layoutParams3 = imageView2.getLayoutParams();
                int marginEnd2 = width4 + (layoutParams3 instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) layoutParams3) : 0);
                int width5 = this.f5532a.f5522m.f4353h.getWidth();
                TextView textView = this.f5532a.f5522m.f4353h;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.stmPassedTimeView");
                ViewGroup.LayoutParams layoutParams4 = textView.getLayoutParams();
                int marginStart2 = width5 + (layoutParams4 instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams4) : 0);
                LinearLayout linearLayout2 = this.f5532a.f5522m.f4354i;
                Intrinsics.checkNotNullExpressionValue(linearLayout2, "binding.stmTitleLinearLayout");
                ViewGroup.LayoutParams layoutParams5 = linearLayout2.getLayoutParams();
                int marginEnd3 = marginStart2 + (layoutParams5 instanceof ViewGroup.MarginLayoutParams ? MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) layoutParams5) : 0);
                LinearLayout linearLayout3 = this.f5532a.f5522m.f4354i;
                Intrinsics.checkNotNullExpressionValue(linearLayout3, "binding.stmTitleLinearLayout");
                ViewGroup.LayoutParams layoutParams6 = linearLayout3.getLayoutParams();
                if (layoutParams6 instanceof ViewGroup.MarginLayoutParams) {
                    i3 = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) layoutParams6);
                }
                this.f5532a.f5522m.f4355j.setMaxWidth((((width - marginEnd2) - marginStart) - marginEnd) - (marginEnd3 + i3));
            }
        }

        public static final class d extends ObservableProperty<z> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ e f5533a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public d(Object obj, Object obj2, e eVar) {
                super(null);
                this.f5533a = eVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
                Long l2;
                String str;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                z zVar3 = zVar2;
                z zVar4 = zVar;
                e eVar = this.f5533a;
                eVar.f5497i.setValue(eVar, c.f5488l[1], eVar.h());
                this.f5533a.f5522m.f4353h.setText((CharSequence) null);
                z h3 = this.f5533a.h();
                if (h3 != null && (l2 = h3.f4313l) != null) {
                    long longValue = l2.longValue();
                    e eVar2 = this.f5533a;
                    TextView textView = eVar2.f5522m.f4353h;
                    long currentTimeMillis = System.currentTimeMillis() - longValue;
                    TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                    long days = timeUnit.toDays(currentTimeMillis);
                    long j2 = days / ((long) 7);
                    long hours = timeUnit.toHours(currentTimeMillis);
                    long minutes = timeUnit.toMinutes(currentTimeMillis);
                    long seconds = timeUnit.toSeconds(currentTimeMillis);
                    Resources resources = eVar2.f5489a.f5483a.getRoot().getContext().getResources();
                    if (j2 > 0) {
                        str = resources.getString(R.string.stm_header_week, new Object[]{Long.valueOf(j2)});
                        Intrinsics.checkNotNullExpressionValue(str, "getString(com.appsamurai…g.stm_header_week, weeks)");
                    } else if (days > 0) {
                        str = resources.getString(R.string.stm_header_day, new Object[]{Long.valueOf(days)});
                        Intrinsics.checkNotNullExpressionValue(str, "getString(com.appsamurai…ing.stm_header_day, days)");
                    } else if (hours > 0) {
                        str = resources.getString(R.string.stm_header_hour, new Object[]{Long.valueOf(hours)});
                        Intrinsics.checkNotNullExpressionValue(str, "getString(com.appsamurai…g.stm_header_hour, hours)");
                    } else if (minutes > 0) {
                        str = resources.getString(R.string.stm_header_minute, new Object[]{Long.valueOf(minutes)});
                        Intrinsics.checkNotNullExpressionValue(str, "getString(com.appsamurai…m_header_minute, minutes)");
                    } else {
                        str = resources.getString(R.string.stm_header_second, new Object[]{Long.valueOf(seconds)});
                        Intrinsics.checkNotNullExpressionValue(str, "getString(com.appsamurai…m_header_second, seconds)");
                    }
                    textView.setText(str);
                }
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$e$e  reason: collision with other inner class name */
        public static final class C0046e extends ObservableProperty<Integer> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ e f5534a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5535b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0046e(Object obj, Object obj2, e eVar, a aVar) {
                super(null);
                this.f5534a = eVar;
                this.f5535b = aVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
                List<z> list;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                Integer num3 = num2;
                Integer num4 = num;
                e eVar = this.f5534a;
                eVar.f5498j = eVar.g();
                this.f5534a.p().b(this.f5534a.g());
                Integer g2 = this.f5534a.g();
                if (g2 != null) {
                    int intValue = g2.intValue();
                    v c3 = this.f5535b.c();
                    if (c3 != null) {
                        List<z> list2 = c3.f4226f;
                        if (list2.size() <= intValue || !list2.get(intValue).f4316o) {
                            this.f5534a.f5522m.f4352g.setVisibility(0);
                        } else {
                            this.f5534a.f5522m.f4352g.setVisibility(4);
                        }
                    }
                }
                Context context = this.f5534a.f5489a.f5483a.getRoot().getContext();
                int i3 = R.string.st_desc_story_index;
                Integer g3 = this.f5534a.g();
                Integer num5 = null;
                Integer valueOf = g3 == null ? null : Integer.valueOf(g3.intValue() + 1);
                v c4 = this.f5535b.c();
                if (!(c4 == null || (list = c4.f4226f) == null)) {
                    num5 = Integer.valueOf(list.size());
                }
                String string = context.getString(i3, new Object[]{valueOf, num5});
                Intrinsics.checkNotNullExpressionValue(string, "parentBinding.root.conte…ories?.size\n            )");
                this.f5534a.f5522m.f4349d.setContentDescription(string);
            }
        }

        static {
            Class<e> cls = e.class;
            f5521s = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(@NotNull a aVar, com.appsamurai.storyly.databinding.f fVar) {
            super(aVar, new b(fVar));
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(fVar, "binding");
            this.f5527r = aVar;
            this.f5522m = fVar;
            this.f5523n = LazyKt.lazy(new C0045a(aVar, this));
            Delegates delegates = Delegates.INSTANCE;
            this.f5525p = LazyKt.lazy(new b(this, aVar));
            this.f5526q = new C0046e((Object) null, (Object) null, this, aVar);
            p().a(aVar.a());
            p().a((Function2<? super Long, ? super Long, Unit>) aVar.b());
            p().a(aVar.c());
            fVar.f4352g.setImageDrawable(o());
            fVar.f4352g.setOnClickListener(new F0.c(this, 0));
            fVar.f4350e.setOnClickListener(new F0.c(this, 1));
            fVar.f4355j.setMaxWidth(0);
            TextView textView = fVar.f4353h;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.stmPassedTimeView");
            Intrinsics.checkExpressionValueIsNotNull(OneShotPreDrawListener.add(textView, new c(textView, this)), "OneShotPreDrawListener.add(this) { action(this) }");
        }

        public void a(@Nullable Long l2) {
            n();
            p().a(l2);
        }

        public void b(@Nullable Integer num) {
            this.f5526q.setValue(this, f5521s[1], num);
        }

        @Nullable
        public Integer g() {
            return (Integer) this.f5526q.getValue(this, f5521s[1]);
        }

        @Nullable
        public z h() {
            return (z) this.f5524o.getValue(this, f5521s[0]);
        }

        public void k() {
            p().b();
        }

        public void l() {
            p().c();
        }

        public void m() {
            p().d();
        }

        public final Drawable o() {
            return (Drawable) this.f5523n.getValue();
        }

        public final b p() {
            return (b) this.f5525p.getValue();
        }

        public static final void b(e eVar, View view) {
            Intrinsics.checkNotNullParameter(eVar, "this$0");
            Function0<Unit> function0 = eVar.f5527r.f5472n;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("headerIconClicked");
                function0 = null;
            }
            function0.invoke();
        }

        public void a(@Nullable z zVar) {
            this.f5524o.setValue(this, f5521s[0], zVar);
        }

        public static final void a(e eVar, View view) {
            Intrinsics.checkNotNullParameter(eVar, "this$0");
            Function0<Unit> function0 = eVar.f5527r.f5471m;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onOptionsClicked");
                function0 = null;
            }
            function0.invoke();
        }
    }

    public /* synthetic */ class f {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryGroupType.values().length];
            iArr[StoryGroupType.MomentsDefault.ordinal()] = 1;
            iArr[StoryGroupType.Live.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final class g extends ObservableProperty<v> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5536a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public g(Object obj, Object obj2, a aVar) {
            super(null);
            this.f5536a = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, v vVar, v vVar2) {
            c cVar;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            v vVar3 = vVar2;
            v vVar4 = vVar;
            if (vVar3 != null) {
                this.f5536a.f5459a.setVisibility(8);
                this.f5536a.f5459a.removeAllViews();
                a aVar = this.f5536a;
                aVar.getClass();
                int i3 = f.$EnumSwitchMapping$0[vVar3.f4228h.ordinal()];
                c cVar2 = null;
                if (i3 == 1) {
                    View inflate = LayoutInflater.from(aVar.f5459a.getContext()).inflate(R.layout.st_moments_header_view, (ViewGroup) null, false);
                    int i4 = R.id.stm_close_button;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(inflate, i4);
                    if (imageView != null) {
                        i4 = R.id.stm_header_linear_layout;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(inflate, i4);
                        if (linearLayout != null) {
                            i4 = R.id.stm_header_pager_view;
                            LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(inflate, i4);
                            if (linearLayout2 != null) {
                                i4 = R.id.stm_icon_image_view;
                                FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(inflate, i4);
                                if (frameLayout != null) {
                                    i4 = R.id.stm_icon_title_container;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(inflate, i4);
                                    if (relativeLayout != null) {
                                        i4 = R.id.stm_options_button;
                                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(inflate, i4);
                                        if (imageView2 != null) {
                                            i4 = R.id.stm_passed_time_view;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(inflate, i4);
                                            if (textView != null) {
                                                i4 = R.id.stm_title_linear_layout;
                                                LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(inflate, i4);
                                                if (linearLayout3 != null) {
                                                    i4 = R.id.stm_title_view;
                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(inflate, i4);
                                                    if (textView2 != null) {
                                                        com.appsamurai.storyly.databinding.f fVar = new com.appsamurai.storyly.databinding.f((RelativeLayout) inflate, imageView, linearLayout, linearLayout2, frameLayout, relativeLayout, imageView2, textView, linearLayout3, textView2);
                                                        Intrinsics.checkNotNullExpressionValue(fVar, "inflate(LayoutInflater.from(holder.context))");
                                                        cVar = new e(aVar, fVar);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    throw new NullPointerException("Missing required view with ID: ".concat(inflate.getResources().getResourceName(i4)));
                } else if (i3 != 2) {
                    com.appsamurai.storyly.databinding.b a2 = com.appsamurai.storyly.databinding.b.a(LayoutInflater.from(aVar.f5459a.getContext()));
                    Intrinsics.checkNotNullExpressionValue(a2, "inflate(LayoutInflater.from(holder.context))");
                    cVar = new C0040a(aVar, a2);
                } else {
                    com.appsamurai.storyly.databinding.b a3 = com.appsamurai.storyly.databinding.b.a(LayoutInflater.from(aVar.f5459a.getContext()));
                    Intrinsics.checkNotNullExpressionValue(a3, "inflate(LayoutInflater.from(holder.context))");
                    cVar = new d(aVar, a3);
                }
                aVar.f5461c = cVar;
                a aVar2 = this.f5536a;
                ViewGroup viewGroup = aVar2.f5459a;
                c cVar3 = aVar2.f5461c;
                if (cVar3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                } else {
                    cVar2 = cVar3;
                }
                viewGroup.addView(cVar2.f5489a.f5483a.getRoot());
                this.f5536a.f5459a.setVisibility(0);
            }
        }
    }

    public static final class h extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5537a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public h(Object obj, Object obj2, a aVar) {
            super(null);
            this.f5537a = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            List<z> list;
            Intrinsics.checkNotNullParameter(kProperty, "property");
            Integer num3 = num2;
            Integer num4 = num;
            if (num3 != null) {
                c cVar = this.f5537a.f5461c;
                z zVar = null;
                if (cVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    cVar = null;
                }
                a aVar = this.f5537a;
                cVar.b((Integer) aVar.f5466h.getValue(aVar, a.f5458o[1]));
                c cVar2 = this.f5537a.f5461c;
                if (cVar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    cVar2 = null;
                }
                v c3 = this.f5537a.c();
                if (!(c3 == null || (list = c3.f4226f) == null)) {
                    zVar = list.get(num3.intValue());
                }
                cVar2.a(zVar);
            }
        }
    }

    static {
        Class<a> cls = a.class;
        f5458o = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyGroupItem", "getStorylyGroupItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyGroupItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
    }

    public a(@NotNull ViewGroup viewGroup, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(viewGroup, "holder");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5459a = viewGroup;
        this.f5460b = storylyConfig;
        int dimensionPixelSize = viewGroup.getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_distance_to_border);
        this.f5462d = dimensionPixelSize;
        int dimensionPixelSize2 = viewGroup.getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_border_thickness);
        this.f5463e = dimensionPixelSize2;
        this.f5464f = Math.max(((float) storylyConfig.getGroup$storyly_release().getIconCornerRadius$storyly_release()) - ((float) (dimensionPixelSize + dimensionPixelSize2)), 0.0f);
        Delegates delegates = Delegates.INSTANCE;
    }

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$a  reason: collision with other inner class name */
    public final class C0040a extends c {

        /* renamed from: q  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f5473q;
        @NotNull

        /* renamed from: m  reason: collision with root package name */
        public final com.appsamurai.storyly.databinding.b f5474m;
        @NotNull

        /* renamed from: n  reason: collision with root package name */
        public final Lazy f5475n;
        @NotNull

        /* renamed from: o  reason: collision with root package name */
        public final ReadWriteProperty f5476o = new b((Object) null, (Object) null, this);
        @NotNull

        /* renamed from: p  reason: collision with root package name */
        public final ReadWriteProperty f5477p;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$a$a  reason: collision with other inner class name */
        public static final class C0041a extends Lambda implements Function0<b> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ C0040a f5478a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5479b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0041a(C0040a aVar, a aVar2) {
                super(0);
                this.f5478a = aVar;
                this.f5479b = aVar2;
            }

            public Object invoke() {
                LinearLayout linearLayout = this.f5478a.f5474m.f4328c;
                Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.stHeaderPagerView");
                return new b(linearLayout, this.f5479b.f5460b);
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$a$b */
        public static final class b extends ObservableProperty<z> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ C0040a f5480a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public b(Object obj, Object obj2, C0040a aVar) {
                super(null);
                this.f5480a = aVar;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, z zVar, z zVar2) {
                int i3;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                z zVar3 = zVar2;
                z zVar4 = zVar;
                C0040a aVar = this.f5480a;
                aVar.f5497i.setValue(aVar, c.f5488l[1], aVar.h());
                C0040a aVar2 = this.f5480a;
                ImageView imageView = aVar2.f5474m.f4331f;
                z h3 = aVar2.h();
                ShareType shareType = null;
                if ((h3 == null ? null : h3.f4312k) != ShareType.Disabled) {
                    z h4 = this.f5480a.h();
                    if (h4 != null) {
                        shareType = h4.f4312k;
                    }
                    if (shareType != null) {
                        i3 = 0;
                        imageView.setVisibility(i3);
                    }
                }
                i3 = 8;
                imageView.setVisibility(i3);
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyheader.a$a$c */
        public static final class c extends ObservableProperty<Integer> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ C0040a f5481a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5482b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public c(Object obj, Object obj2, C0040a aVar, a aVar2) {
                super(null);
                this.f5481a = aVar;
                this.f5482b = aVar2;
            }

            public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
                List<z> list;
                Intrinsics.checkNotNullParameter(kProperty, "property");
                Integer num3 = num2;
                Integer num4 = num;
                C0040a aVar = this.f5481a;
                aVar.f5498j = aVar.g();
                this.f5481a.o().b(this.f5481a.g());
                Context context = this.f5481a.f5489a.f5483a.getRoot().getContext();
                int i3 = R.string.st_desc_story_index;
                Integer g2 = this.f5481a.g();
                Integer num5 = null;
                Integer valueOf = g2 == null ? null : Integer.valueOf(g2.intValue() + 1);
                v c3 = this.f5482b.c();
                if (!(c3 == null || (list = c3.f4226f) == null)) {
                    num5 = Integer.valueOf(list.size());
                }
                String string = context.getString(i3, new Object[]{valueOf, num5});
                Intrinsics.checkNotNullExpressionValue(string, "parentBinding.root.conte…ories?.size\n            )");
                this.f5481a.f5474m.f4328c.setContentDescription(string);
            }
        }

        static {
            Class<C0040a> cls = C0040a.class;
            f5473q = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0040a(@NotNull a aVar, com.appsamurai.storyly.databinding.b bVar) {
            super(aVar, new b(bVar));
            int i3 = 0;
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Intrinsics.checkNotNullParameter(bVar, "binding");
            this.f5474m = bVar;
            this.f5475n = LazyKt.lazy(new C0041a(this, aVar));
            Delegates delegates = Delegates.INSTANCE;
            StoryGroupType storyGroupType = null;
            this.f5477p = new c((Object) null, (Object) null, this, aVar);
            o().a(aVar.a());
            o().a((Function2<? super Long, ? super Long, Unit>) aVar.b());
            o().a(aVar.c());
            ImageView imageView = bVar.f4331f;
            Drawable shareButtonIcon$storyly_release = aVar.f5460b.getStory$storyly_release().getShareButtonIcon$storyly_release();
            imageView.setImageDrawable(shareButtonIcon$storyly_release == null ? c() : shareButtonIcon$storyly_release);
            bVar.f4331f.setOnClickListener(new F0.a(aVar, 0));
            TextView textView = bVar.f4332g;
            v c3 = aVar.c();
            textView.setVisibility((c3 != null ? c3.e() : storyGroupType) != StoryGroupType.Ad ? 8 : i3);
        }

        public static final void b(a aVar, View view) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            Function0<Unit> function0 = aVar.f5470l;
            if (function0 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onShareRequest");
                function0 = null;
            }
            function0.invoke();
        }

        public void a(@Nullable Long l2) {
            n();
            o().a(l2);
        }

        @Nullable
        public Integer g() {
            return (Integer) this.f5477p.getValue(this, f5473q[1]);
        }

        @Nullable
        public z h() {
            return (z) this.f5476o.getValue(this, f5473q[0]);
        }

        public void k() {
            o().b();
        }

        public void l() {
            o().c();
        }

        public void m() {
            o().d();
        }

        public final b o() {
            return (b) this.f5475n.getValue();
        }

        public void a(@Nullable z zVar) {
            this.f5476o.setValue(this, f5473q[0], zVar);
        }

        public void b(@Nullable Integer num) {
            this.f5477p.setValue(this, f5473q[1], num);
        }

        public void a(@Nullable Integer num) {
            o().a(num);
        }

        public void b(long j2) {
            b o3 = o();
            Integer a2 = o3.a();
            if (a2 != null) {
                c cVar = o3.f5547i.get(a2.intValue());
                cVar.c();
                cVar.a(cVar.f5556f, j2);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
            r5 = r5.f5547i.get(r0.intValue());
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(long r6) {
            /*
                r5 = this;
                com.appsamurai.storyly.storylypresenter.storylyheader.b r5 = r5.o()
                java.lang.Integer r0 = r5.a()
                if (r0 != 0) goto L_0x000b
                goto L_0x0040
            L_0x000b:
                int r0 = r0.intValue()
                java.util.List<com.appsamurai.storyly.storylypresenter.storylyheader.c> r5 = r5.f5547i
                java.lang.Object r5 = r5.get(r0)
                com.appsamurai.storyly.storylypresenter.storylyheader.c r5 = (com.appsamurai.storyly.storylypresenter.storylyheader.c) r5
                java.lang.Long r0 = r5.f5556f
                if (r0 != 0) goto L_0x001c
                goto L_0x0040
            L_0x001c:
                long r1 = r0.longValue()
                r5.c()
                long r3 = r5.getCurrentPlayTime$storyly_release()
                long r3 = r3 + r6
                int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                if (r6 <= 0) goto L_0x0030
                r5.a(r0, r1)
                goto L_0x0040
            L_0x0030:
                double r6 = (double) r3
                r1 = 0
                int r6 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
                if (r6 >= 0) goto L_0x003d
                r6 = 0
                r5.a(r0, r6)
                goto L_0x0040
            L_0x003d:
                r5.a(r0, r3)
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyheader.a.C0040a.a(long):void");
        }
    }

    @NotNull
    public final Function0<Unit> a() {
        Function0<Unit> function0 = this.f5469k;
        if (function0 != null) {
            return function0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTimeCompleted");
        return null;
    }

    @NotNull
    public final Function2<Long, Long, Unit> b() {
        Function2<? super Long, ? super Long, Unit> function2 = this.f5468j;
        if (function2 != null) {
            return function2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("onTimeUpdated");
        return null;
    }

    @Nullable
    public final v c() {
        return (v) this.f5465g.getValue(this, f5458o[0]);
    }

    public static final class b implements ViewBinding {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ViewBinding f5483a;
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public final RelativeLayout f5484b;
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final TextView f5485c;
        @NotNull

        /* renamed from: d  reason: collision with root package name */
        public final FrameLayout f5486d;
        @NotNull

        /* renamed from: e  reason: collision with root package name */
        public final ImageView f5487e;

        public b(@NotNull ViewBinding viewBinding, @NotNull RelativeLayout relativeLayout, @NotNull TextView textView, @NotNull FrameLayout frameLayout, @NotNull ImageView imageView, @NotNull ImageView imageView2) {
            Intrinsics.checkNotNullParameter(viewBinding, "innerBinding");
            Intrinsics.checkNotNullParameter(relativeLayout, "iconTitleContainer");
            Intrinsics.checkNotNullParameter(textView, "titleView");
            Intrinsics.checkNotNullParameter(frameLayout, "iconImageView");
            Intrinsics.checkNotNullParameter(imageView, "optionalButton");
            Intrinsics.checkNotNullParameter(imageView2, "closeButton");
            this.f5483a = viewBinding;
            this.f5484b = relativeLayout;
            this.f5485c = textView;
            this.f5486d = frameLayout;
            this.f5487e = imageView2;
        }

        @NotNull
        public final ImageView a() {
            return this.f5487e;
        }

        @NotNull
        public final FrameLayout b() {
            return this.f5486d;
        }

        @NotNull
        public final RelativeLayout c() {
            return this.f5484b;
        }

        @NotNull
        public final TextView d() {
            return this.f5485c;
        }

        @NotNull
        @NonNull
        public View getRoot() {
            return this.f5483a.getRoot();
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.databinding.b r9) {
            /*
                r8 = this;
                java.lang.String r0 = "_binding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                android.widget.RelativeLayout r3 = r9.f4330e
                java.lang.String r0 = "_binding.stIconTitleContainer"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
                android.widget.TextView r4 = r9.f4333h
                java.lang.String r0 = "_binding.stTitleView"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                android.widget.FrameLayout r5 = r9.f4329d
                java.lang.String r0 = "_binding.stIconImageView"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                android.widget.ImageView r6 = r9.f4331f
                java.lang.String r0 = "_binding.stShareButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
                android.widget.ImageView r7 = r9.f4327b
                java.lang.String r0 = "_binding.stCloseButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
                r1 = r8
                r2 = r9
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyheader.a.b.<init>(com.appsamurai.storyly.databinding.b):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.databinding.f r9) {
            /*
                r8 = this;
                java.lang.String r0 = "_binding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                android.widget.RelativeLayout r3 = r9.f4351f
                java.lang.String r0 = "_binding.stmIconTitleContainer"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r0)
                android.widget.TextView r4 = r9.f4355j
                java.lang.String r0 = "_binding.stmTitleView"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                android.widget.FrameLayout r5 = r9.f4350e
                java.lang.String r0 = "_binding.stmIconImageView"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
                android.widget.ImageView r6 = r9.f4352g
                java.lang.String r0 = "_binding.stmOptionsButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r0)
                android.widget.ImageView r7 = r9.f4347b
                java.lang.String r0 = "_binding.stmCloseButton"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r0)
                r1 = r8
                r2 = r9
                r1.<init>(r2, r3, r4, r5, r6, r7)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyheader.a.b.<init>(com.appsamurai.storyly.databinding.f):void");
        }
    }
}
