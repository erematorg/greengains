package com.appsamurai.storyly.storylypresenter.storylyfooter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.appcompat.content.res.AppCompatResources;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.data.v;
import com.appsamurai.storyly.data.z;
import com.appsamurai.storyly.databinding.g;
import com.appsamurai.storyly.exoplayer2.common.C;
import com.appsamurai.storyly.util.m;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a {

    /* renamed from: n  reason: collision with root package name */
    public static final /* synthetic */ KProperty<Object>[] f5343n;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ViewGroup f5344a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final StorylyConfig f5345b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    public C0032a f5346c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f5347d = true;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    public v f5348e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final ReadWriteProperty f5349f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final ReadWriteProperty f5350g;

    /* renamed from: h  reason: collision with root package name */
    public Function0<Unit> f5351h;

    /* renamed from: i  reason: collision with root package name */
    public Function0<Unit> f5352i;

    /* renamed from: j  reason: collision with root package name */
    public Function1<? super Long, Unit> f5353j;

    /* renamed from: k  reason: collision with root package name */
    public Function0<Unit> f5354k;

    /* renamed from: l  reason: collision with root package name */
    public Function0<Unit> f5355l;

    /* renamed from: m  reason: collision with root package name */
    public Function3<? super Boolean, ? super v, ? super z, Unit> f5356m;

    /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.a$a  reason: collision with other inner class name */
    public static class C0032a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ViewGroup f5357a;
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public d f5358b = d.NotHiding;

        public C0032a(@NotNull ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.TAG_LAYOUT);
            this.f5357a = viewGroup;
        }

        public static final void b(C0032a aVar) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            aVar.f5357a.setVisibility(8);
        }

        public void a(@Nullable Integer num) {
        }

        public void c() {
        }

        public void d() {
        }

        public void e() {
        }

        public void f() {
        }

        public void g() {
        }

        public void h() {
            this.f5357a.animate().cancel();
            this.f5357a.animate().alpha(1.0f).setDuration(400).withStartAction(new E0.a(this, 2));
        }

        public static final void c(C0032a aVar) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            aVar.f5358b = d.NotHiding;
            aVar.f5357a.setVisibility(0);
        }

        public void a(@Nullable Long l2, @Nullable Long l3) {
        }

        public void a(@NotNull List<Pair<Integer, Float>> list) {
            Intrinsics.checkNotNullParameter(list, "parts");
        }

        public void b() {
            this.f5357a.animate().cancel();
            this.f5357a.animate().alpha(0.0f).setDuration(400).withStartAction(new E0.a(this, 0)).withEndAction(new E0.a(this, 1));
        }

        @NotNull
        public final ViewGroup a() {
            return this.f5357a;
        }

        public static final void a(C0032a aVar) {
            Intrinsics.checkNotNullParameter(aVar, "this$0");
            aVar.f5358b = d.NotShowing;
        }
    }

    public final class b extends C0032a {

        /* renamed from: l  reason: collision with root package name */
        public static final /* synthetic */ KProperty<Object>[] f5359l = {androidx.compose.ui.autofill.a.m(b.class, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final com.appsamurai.storyly.databinding.e f5360c;

        /* renamed from: d  reason: collision with root package name */
        public final boolean f5361d;
        @NotNull

        /* renamed from: e  reason: collision with root package name */
        public final AnimatorSet f5362e = new AnimatorSet();
        @NotNull

        /* renamed from: f  reason: collision with root package name */
        public final ImageView f5363f;
        @NotNull

        /* renamed from: g  reason: collision with root package name */
        public final Lazy f5364g;
        @NotNull

        /* renamed from: h  reason: collision with root package name */
        public final Lazy f5365h;
        @NotNull

        /* renamed from: i  reason: collision with root package name */
        public final Lazy f5366i;
        @NotNull

        /* renamed from: j  reason: collision with root package name */
        public final ReadWriteProperty f5367j;

        /* renamed from: k  reason: collision with root package name */
        public final /* synthetic */ a f5368k;

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$a  reason: collision with other inner class name */
        public static final class C0033a extends Lambda implements Function0<b> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5369a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0033a(a aVar) {
                super(0);
                this.f5369a = aVar;
            }

            public Object invoke() {
                Context context = this.f5369a.f5344a.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "holder.context");
                return new b(context, this.f5369a.f5345b);
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$b  reason: collision with other inner class name */
        public static final class C0034b extends Lambda implements Function0<c> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5370a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public C0034b(a aVar) {
                super(0);
                this.f5370a = aVar;
            }

            public Object invoke() {
                Context context = this.f5370a.f5344a.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "holder.context");
                return new c(context, this.f5370a.f5345b);
            }
        }

        public static final class c extends Lambda implements Function0<com.appsamurai.storyly.data.managers.storage.b> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5371a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public c(a aVar) {
                super(0);
                this.f5371a = aVar;
            }

            public Object invoke() {
                Context context = this.f5371a.f5344a.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "holder.context");
                return new com.appsamurai.storyly.data.managers.storage.b(context, "stryly-moments-like-status");
            }
        }

        public static final class d implements Animator.AnimatorListener {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ b f5372a;

            public d(b bVar) {
                this.f5372a = bVar;
            }

            public void onAnimationCancel(@NotNull Animator animator) {
            }

            public void onAnimationEnd(@NotNull Animator animator) {
                this.f5372a.f5363f.setScaleX(0.3f);
                this.f5372a.f5363f.setScaleY(0.3f);
                this.f5372a.f5363f.setAlpha(1.0f);
                this.f5372a.f5363f.setRotation(30.0f);
                this.f5372a.f5363f.setTranslationY(0.0f);
                this.f5372a.f5363f.setTranslationX(0.0f);
                this.f5372a.f5363f.setVisibility(4);
            }

            public void onAnimationRepeat(@NotNull Animator animator) {
            }

            public void onAnimationStart(@NotNull Animator animator) {
            }
        }

        public static final class e implements Animator.AnimatorListener {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ b f5373a;

            public e(b bVar) {
                this.f5373a = bVar;
            }

            public void onAnimationCancel(@NotNull Animator animator) {
            }

            public void onAnimationEnd(@NotNull Animator animator) {
            }

            public void onAnimationRepeat(@NotNull Animator animator) {
            }

            public void onAnimationStart(@NotNull Animator animator) {
                this.f5373a.f5363f.setScaleX(0.3f);
                this.f5373a.f5363f.setScaleY(0.3f);
                this.f5373a.f5363f.setVisibility(0);
            }
        }

        public static final class f extends ObservableProperty<Integer> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ b f5374a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ a f5375b;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public f(Object obj, Object obj2, b bVar, a aVar) {
                super(null);
                this.f5374a = bVar;
                this.f5375b = aVar;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:19:0x0062, code lost:
                r1 = (r1 = (com.appsamurai.storyly.data.z) kotlin.collections.CollectionsKt.getOrNull((r1 = r1.f4226f), r2)).f4314m;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void afterChange(@org.jetbrains.annotations.NotNull kotlin.reflect.KProperty<?> r2, java.lang.Integer r3, java.lang.Integer r4) {
                /*
                    r1 = this;
                    java.lang.String r0 = "property"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                    java.lang.Integer r4 = (java.lang.Integer) r4
                    java.lang.Integer r3 = (java.lang.Integer) r3
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a$b r2 = r1.f5374a
                    r2.n()
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a$b r2 = r1.f5374a
                    java.lang.Integer r2 = r2.l()
                    if (r2 != 0) goto L_0x0017
                    goto L_0x0077
                L_0x0017:
                    int r2 = r2.intValue()
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a$b r3 = r1.f5374a
                    com.appsamurai.storyly.storylypresenter.storylyfooter.c r3 = r3.k()
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a r4 = r1.f5375b
                    com.appsamurai.storyly.data.v r4 = r4.f5348e
                    if (r4 != 0) goto L_0x0028
                    goto L_0x003f
                L_0x0028:
                    java.util.List<com.appsamurai.storyly.data.z> r4 = r4.f4226f
                    if (r4 != 0) goto L_0x002d
                    goto L_0x003f
                L_0x002d:
                    java.lang.Object r4 = kotlin.collections.CollectionsKt.getOrNull(r4, r2)
                    com.appsamurai.storyly.data.z r4 = (com.appsamurai.storyly.data.z) r4
                    if (r4 != 0) goto L_0x0036
                    goto L_0x003f
                L_0x0036:
                    com.appsamurai.storyly.analytics.b r4 = r4.f4314m
                    if (r4 != 0) goto L_0x003b
                    goto L_0x003f
                L_0x003b:
                    java.lang.Integer r4 = r4.f3502a
                    if (r4 != 0) goto L_0x0044
                L_0x003f:
                    r4 = 0
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                L_0x0044:
                    r3.setViewStats$storyly_release(r4)
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a$b r3 = r1.f5374a
                    com.appsamurai.storyly.storylypresenter.storylyfooter.b r3 = r3.j()
                    com.appsamurai.storyly.storylypresenter.storylyfooter.a r1 = r1.f5375b
                    com.appsamurai.storyly.data.v r1 = r1.f5348e
                    if (r1 != 0) goto L_0x0054
                    goto L_0x0066
                L_0x0054:
                    java.util.List<com.appsamurai.storyly.data.z> r1 = r1.f4226f
                    if (r1 != 0) goto L_0x0059
                    goto L_0x0066
                L_0x0059:
                    java.lang.Object r1 = kotlin.collections.CollectionsKt.getOrNull(r1, r2)
                    com.appsamurai.storyly.data.z r1 = (com.appsamurai.storyly.data.z) r1
                    if (r1 != 0) goto L_0x0062
                    goto L_0x0066
                L_0x0062:
                    com.appsamurai.storyly.analytics.b r1 = r1.f4314m
                    if (r1 != 0) goto L_0x0068
                L_0x0066:
                    r1 = 0
                    goto L_0x0074
                L_0x0068:
                    java.lang.Integer r2 = r1.f3502a
                    java.lang.Integer r4 = r1.f3503b
                    java.util.List<com.appsamurai.storyly.analytics.c> r1 = r1.f3504c
                    com.appsamurai.storyly.analytics.b r0 = new com.appsamurai.storyly.analytics.b
                    r0.<init>(r2, r4, r1)
                    r1 = r0
                L_0x0074:
                    r3.setLikeStats$storyly_release(r1)
                L_0x0077:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.a.b.f.afterChange(kotlin.reflect.KProperty, java.lang.Object, java.lang.Object):void");
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public b(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylyfooter.a r10, com.appsamurai.storyly.databinding.e r11, boolean r12) {
            /*
                r9 = this;
                java.lang.String r0 = "this$0"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r0 = "binding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                r9.f5368k = r10
                android.widget.FrameLayout r0 = r11.a()
                java.lang.String r1 = "binding.root"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r9.<init>(r0)
                r9.f5360c = r11
                r9.f5361d = r12
                android.animation.AnimatorSet r0 = new android.animation.AnimatorSet
                r0.<init>()
                r9.f5362e = r0
                android.widget.ImageView r0 = new android.widget.ImageView
                android.view.ViewGroup r1 = r10.f5344a
                android.content.Context r1 = r1.getContext()
                r0.<init>(r1)
                r9.f5363f = r0
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$b r0 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$b
                r0.<init>(r10)
                kotlin.Lazy r0 = kotlin.LazyKt.lazy(r0)
                r9.f5364g = r0
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$a r0 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$a
                r0.<init>(r10)
                kotlin.Lazy r0 = kotlin.LazyKt.lazy(r0)
                r9.f5365h = r0
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$c r0 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$c
                r0.<init>(r10)
                kotlin.Lazy r0 = kotlin.LazyKt.lazy(r0)
                r9.f5366i = r0
                kotlin.properties.Delegates r0 = kotlin.properties.Delegates.INSTANCE
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$f r0 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$b$f
                r1 = 0
                r0.<init>(r1, r1, r9, r10)
                r9.f5367j = r0
                r0 = 0
                if (r12 == 0) goto L_0x00cf
                android.widget.FrameLayout r12 = r11.a()
                com.appsamurai.storyly.storylypresenter.storylyfooter.c r1 = r9.k()
                android.widget.FrameLayout$LayoutParams r2 = new android.widget.FrameLayout$LayoutParams
                r3 = -2
                r4 = 8388627(0x800013, float:1.175497E-38)
                r2.<init>(r3, r3, r4)
                r4 = 16
                java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
                int r5 = com.appsamurai.storyly.util.m.a((java.lang.Number) r5)
                java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
                int r6 = com.appsamurai.storyly.util.m.a((java.lang.Number) r6)
                java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
                int r7 = com.appsamurai.storyly.util.m.a((java.lang.Number) r7)
                r2.setMargins(r5, r6, r0, r7)
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                r12.addView(r1, r2)
                android.widget.FrameLayout r11 = r11.a()
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r12 = r9.j()
                android.widget.FrameLayout$LayoutParams r1 = new android.widget.FrameLayout$LayoutParams
                r2 = 8388629(0x800015, float:1.1754973E-38)
                r1.<init>(r3, r3, r2)
                java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
                int r2 = com.appsamurai.storyly.util.m.a((java.lang.Number) r2)
                java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
                int r3 = com.appsamurai.storyly.util.m.a((java.lang.Number) r3)
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                int r4 = com.appsamurai.storyly.util.m.a((java.lang.Number) r4)
                r1.setMargins(r0, r2, r3, r4)
                r11.addView(r12, r1)
                com.appsamurai.storyly.storylypresenter.storylyfooter.b r11 = r9.j()
                E0.b r12 = new E0.b
                r12.<init>(r9, r10, r0)
                r11.setOnClickListener(r12)
                goto L_0x012e
            L_0x00cf:
                android.widget.ImageView r12 = r11.f4344b
                r12.setVisibility(r0)
                android.widget.ImageView r12 = r11.f4345c
                r12.setVisibility(r0)
                android.widget.ImageView r12 = r11.f4345c
                android.widget.FrameLayout$LayoutParams r1 = new android.widget.FrameLayout$LayoutParams
                r2 = 1118568448(0x42ac0000, float:86.0)
                r3 = 0
                r4 = 2
                int r5 = com.appsamurai.storyly.util.m.b(r2, r3, r4)
                r6 = 1110441984(0x42300000, float:44.0)
                int r7 = com.appsamurai.storyly.util.m.a(r6, r3, r4)
                r8 = 17
                r1.<init>(r5, r7, r8)
                r12.setLayoutParams(r1)
                android.widget.ImageView r12 = r11.f4345c
                r1 = 1098907648(0x41800000, float:16.0)
                int r5 = com.appsamurai.storyly.util.m.a(r1, r3, r4)
                r12.setPadding(r0, r0, r0, r5)
                android.widget.ImageView r12 = r11.f4345c
                E0.c r5 = new E0.c
                r5.<init>(r9, r0)
                r12.setOnClickListener(r5)
                android.widget.ImageView r12 = r11.f4344b
                android.widget.FrameLayout$LayoutParams r5 = new android.widget.FrameLayout$LayoutParams
                int r2 = com.appsamurai.storyly.util.m.b(r2, r3, r4)
                int r6 = com.appsamurai.storyly.util.m.a(r6, r3, r4)
                r5.<init>(r2, r6, r8)
                r12.setLayoutParams(r5)
                android.widget.ImageView r12 = r11.f4344b
                int r1 = com.appsamurai.storyly.util.m.a(r1, r3, r4)
                r12.setPadding(r0, r0, r0, r1)
                android.widget.ImageView r11 = r11.f4344b
                E0.b r12 = new E0.b
                r0 = 1
                r12.<init>(r9, r10, r0)
                r11.setOnClickListener(r12)
            L_0x012e:
                r9.n()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.a.b.<init>(com.appsamurai.storyly.storylypresenter.storylyfooter.a, com.appsamurai.storyly.databinding.e, boolean):void");
        }

        public static final void b(b bVar, a aVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            bVar.a(false);
            ViewParent parent = aVar.f5344a.getParent();
            RelativeLayout relativeLayout = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
            if (relativeLayout != null) {
                relativeLayout.removeView(bVar.f5363f);
            }
            bVar.f5362e.end();
            bVar.b(false);
        }

        public void a(@Nullable Integer num) {
            this.f5367j.setValue(this, f5359l[0], num);
        }

        public void d() {
            ViewParent parent = this.f5368k.f5344a.getParent();
            RelativeLayout relativeLayout = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
            if (relativeLayout != null) {
                relativeLayout.removeView(this.f5363f);
            }
            k().setViewStats$storyly_release(0);
            j().a();
        }

        public final void i() {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m.b(86.0f, 0.0f, 2), m.a(44.0f, 0.0f, 2));
            layoutParams.addRule(12);
            layoutParams.addRule(14);
            ImageView imageView = this.f5363f;
            Drawable storyLikeAnimationIcon$storyly_release = this.f5368k.f5345b.getMoments$storyly_release().getIconStyling$storyly_release().getStoryLikeAnimationIcon$storyly_release();
            if (storyLikeAnimationIcon$storyly_release == null) {
                storyLikeAnimationIcon$storyly_release = AppCompatResources.getDrawable(imageView.getContext(), R.drawable.st_like_heart);
            }
            imageView.setImageDrawable(storyLikeAnimationIcon$storyly_release);
            imageView.setLayoutParams(layoutParams);
            imageView.setRotation(30.0f);
            imageView.setVisibility(4);
            ViewParent parent = this.f5368k.f5344a.getParent();
            RelativeLayout relativeLayout = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
            if (relativeLayout != null) {
                relativeLayout.addView(this.f5363f);
            }
        }

        public final b j() {
            return (b) this.f5365h.getValue();
        }

        public final c k() {
            return (c) this.f5364g.getValue();
        }

        @Nullable
        public Integer l() {
            return (Integer) this.f5367j.getValue(this, f5359l[0]);
        }

        public final void m() {
            ViewParent parent = this.f5368k.f5344a.getParent();
            Float f2 = null;
            RelativeLayout relativeLayout = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
            Float valueOf = relativeLayout == null ? null : Float.valueOf((float) relativeLayout.getMeasuredHeight());
            if (valueOf != null) {
                float floatValue = valueOf.floatValue();
                ViewParent parent2 = this.f5368k.f5344a.getParent();
                RelativeLayout relativeLayout2 = parent2 instanceof RelativeLayout ? (RelativeLayout) parent2 : null;
                if (relativeLayout2 != null) {
                    f2 = Float.valueOf((float) relativeLayout2.getMeasuredWidth());
                }
                if (f2 != null) {
                    float floatValue2 = f2.floatValue();
                    PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{(-floatValue) / ((float) 2)});
                    Property property = View.TRANSLATION_X;
                    ArrayList arrayList = new ArrayList();
                    float f3 = floatValue2 / 4.0f;
                    boolean z2 = false;
                    float f4 = 0.0f;
                    float f5 = 0.0f;
                    while (0.0f <= f4 && f4 <= f3) {
                        Keyframe ofFloat2 = Keyframe.ofFloat(f5, f4);
                        Intrinsics.checkNotNullExpressionValue(ofFloat2, "ofFloat(fractionCount, controlPoint)");
                        arrayList.add(ofFloat2);
                        if (f4 >= f3) {
                            z2 = true;
                        }
                        f4 = z2 ? f4 - 3.0f : f4 + 3.0f;
                        f5 += 1.0f / (floatValue2 / 6.0f);
                    }
                    Object[] array = arrayList.toArray(new Keyframe[0]);
                    if (array != null) {
                        Keyframe[] keyframeArr = (Keyframe[]) array;
                        PropertyValuesHolder ofKeyframe = PropertyValuesHolder.ofKeyframe(property, (Keyframe[]) Arrays.copyOf(keyframeArr, keyframeArr.length));
                        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat(View.ALPHA, new float[]{0.0f});
                        Property property2 = View.SCALE_X;
                        PropertyValuesHolder ofFloat4 = PropertyValuesHolder.ofFloat(property2, new float[]{6.0f});
                        Property property3 = View.SCALE_Y;
                        PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat(property3, new float[]{6.0f});
                        PropertyValuesHolder ofFloat6 = PropertyValuesHolder.ofFloat(property2, new float[]{12.0f});
                        PropertyValuesHolder ofFloat7 = PropertyValuesHolder.ofFloat(property3, new float[]{12.0f});
                        ObjectAnimator duration = ObjectAnimator.ofPropertyValuesHolder(this.f5363f, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.ROTATION, new float[]{0.0f}), ofKeyframe, ofFloat, ofFloat4, ofFloat5}).setDuration(500);
                        Intrinsics.checkNotNullExpressionValue(duration, "ofPropertyValuesHolder(a…ON.toLong()\n            )");
                        ObjectAnimator duration2 = ObjectAnimator.ofPropertyValuesHolder(this.f5363f, new PropertyValuesHolder[]{ofFloat3, ofFloat6, ofFloat7}).setDuration(500);
                        Intrinsics.checkNotNullExpressionValue(duration2, "ofPropertyValuesHolder(a…ON.toLong()\n            )");
                        duration2.addListener(new d(this));
                        duration.addListener(new e(this));
                        this.f5362e.setInterpolator(new LinearInterpolator());
                        this.f5362e.play(duration2).after(duration);
                        this.f5362e.start();
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
                }
            }
        }

        public final void n() {
            Integer l2;
            Unit unit;
            v vVar = this.f5368k.f5348e;
            if (vVar != null && (l2 = l()) != null) {
                int intValue = l2.intValue();
                Object a2 = ((com.appsamurai.storyly.data.managers.storage.b) this.f5366i.getValue()).a("ttl_" + vVar.f4221a + '_' + vVar.f4226f.get(intValue).f4302a);
                if (a2 == null) {
                    unit = null;
                } else {
                    if ((!(a2 instanceof Long) || ((Number) a2).longValue() <= System.currentTimeMillis()) && (!(a2 instanceof Boolean) || !Intrinsics.areEqual(a2, (Object) Boolean.TRUE))) {
                        a(false);
                    } else {
                        a(true);
                    }
                    unit = Unit.INSTANCE;
                }
                if (unit == null) {
                    a(false);
                }
            }
        }

        public static final void a(b bVar, a aVar, View view) {
            List<z> list;
            z zVar;
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            com.appsamurai.storyly.analytics.b bVar2 = null;
            if (!bVar.j().getLikeStatus$storyly_release()) {
                bVar.i();
                bVar.m();
                bVar.b(true);
            } else {
                ViewParent parent = aVar.f5344a.getParent();
                RelativeLayout relativeLayout = parent instanceof RelativeLayout ? (RelativeLayout) parent : null;
                if (relativeLayout != null) {
                    relativeLayout.removeView(bVar.f5363f);
                }
                bVar.f5362e.end();
                bVar.b(false);
            }
            bVar.j().setLikeStatus$storyly_release(true ^ bVar.j().getLikeStatus$storyly_release());
            b j2 = bVar.j();
            Integer l2 = bVar.l();
            if (l2 != null) {
                int intValue = l2.intValue();
                v vVar = aVar.f5348e;
                if (!(vVar == null || (list = vVar.f4226f) == null || (zVar = (z) CollectionsKt.getOrNull(list, intValue)) == null)) {
                    bVar2 = zVar.f4314m;
                }
            }
            j2.setLikeStats$storyly_release(bVar2);
        }

        public final void b(boolean z2) {
            List<z> list;
            Integer l2 = l();
            if (l2 != null) {
                a aVar = this.f5368k;
                int intValue = l2.intValue();
                v vVar = aVar.f5348e;
                if (vVar != null && (list = vVar.f4226f) != null && intValue < list.size()) {
                    Function3<? super Boolean, ? super v, ? super z, Unit> function3 = aVar.f5356m;
                    String str = null;
                    if (function3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onUserLikeOrUnlike");
                        function3 = null;
                    }
                    function3.invoke(Boolean.valueOf(z2), aVar.f5348e, list.get(intValue));
                    StringBuilder sb = new StringBuilder("ttl_");
                    v vVar2 = aVar.f5348e;
                    if (vVar2 != null) {
                        str = vVar2.f4221a;
                    }
                    sb.append(str);
                    sb.append('_');
                    sb.append(list.get(intValue).f4302a);
                    String sb2 = sb.toString();
                    com.appsamurai.storyly.data.managers.storage.b bVar = (com.appsamurai.storyly.data.managers.storage.b) this.f5366i.getValue();
                    bVar.getClass();
                    Intrinsics.checkNotNullParameter(sb2, JwtUtilsKt.DID_METHOD_KEY);
                    if (z2) {
                        bVar.a(sb2, System.currentTimeMillis() + ((long) 90000000));
                    } else {
                        bVar.a(sb2, false);
                    }
                }
            }
        }

        public static final void a(b bVar, View view) {
            Intrinsics.checkNotNullParameter(bVar, "this$0");
            bVar.i();
            bVar.m();
            bVar.a(true);
            bVar.b(true);
        }

        public final void a(boolean z2) {
            if (this.f5361d) {
                j().setLikeStatus$storyly_release(z2);
            } else if (z2) {
                this.f5360c.f4345c.setVisibility(4);
                this.f5360c.f4344b.setVisibility(0);
            } else {
                this.f5360c.f4345c.setVisibility(0);
                this.f5360c.f4344b.setVisibility(4);
            }
        }
    }

    public enum d {
        NotShowing,
        NotHiding
    }

    public static final class e extends ObservableProperty<z> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5391a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, a aVar) {
            super(null);
            this.f5391a = aVar;
        }

        /* JADX WARNING: Removed duplicated region for block: B:25:0x00d6  */
        /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void afterChange(@org.jetbrains.annotations.NotNull kotlin.reflect.KProperty<?> r8, com.appsamurai.storyly.data.z r9, com.appsamurai.storyly.data.z r10) {
            /*
                r7 = this;
                java.lang.String r0 = "property"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                com.appsamurai.storyly.data.z r10 = (com.appsamurai.storyly.data.z) r10
                com.appsamurai.storyly.data.z r9 = (com.appsamurai.storyly.data.z) r9
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r8 = r7.f5391a
                com.appsamurai.storyly.data.v r9 = r8.f5348e
                if (r9 != 0) goto L_0x0011
                goto L_0x00ed
            L_0x0011:
                if (r10 != 0) goto L_0x0015
                goto L_0x00ed
            L_0x0015:
                android.view.ViewGroup r8 = r8.f5344a
                r0 = 8
                r8.setVisibility(r0)
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r8 = r7.f5391a
                android.view.ViewGroup r8 = r8.f5344a
                r8.removeAllViews()
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r8 = r7.f5391a
                r8.getClass()
                com.appsamurai.storyly.StoryGroupType r9 = r9.f4228h
                com.appsamurai.storyly.StoryGroupType r0 = com.appsamurai.storyly.StoryGroupType.MomentsDefault
                java.lang.String r1 = "Missing required view with ID: "
                java.lang.String r2 = "inflate(LayoutInflater.from(holder.context))"
                r3 = 0
                r4 = 0
                if (r9 != r0) goto L_0x007d
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$b r9 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$b
                android.view.ViewGroup r10 = r8.f5344a
                android.content.Context r10 = r10.getContext()
                android.view.LayoutInflater r10 = android.view.LayoutInflater.from(r10)
                int r0 = com.appsamurai.storyly.R.layout.st_moments_footer_view
                android.view.View r10 = r10.inflate(r0, r4, r3)
                int r0 = com.appsamurai.storyly.R.id.st_moments_liked
                android.view.View r4 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
                android.widget.ImageView r4 = (android.widget.ImageView) r4
                if (r4 == 0) goto L_0x006b
                int r0 = com.appsamurai.storyly.R.id.st_moments_unliked
                android.view.View r5 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
                android.widget.ImageView r5 = (android.widget.ImageView) r5
                if (r5 == 0) goto L_0x006b
                com.appsamurai.storyly.databinding.e r0 = new com.appsamurai.storyly.databinding.e
                android.widget.FrameLayout r10 = (android.widget.FrameLayout) r10
                r0.<init>(r10, r4, r5)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
                boolean r10 = r8.f5347d
                r9.<init>(r8, r0, r10)
            L_0x0069:
                r4 = r9
                goto L_0x00d3
            L_0x006b:
                android.content.res.Resources r7 = r10.getResources()
                java.lang.String r7 = r7.getResourceName(r0)
                java.lang.NullPointerException r8 = new java.lang.NullPointerException
                java.lang.String r7 = r1.concat(r7)
                r8.<init>(r7)
                throw r8
            L_0x007d:
                com.appsamurai.storyly.StoryType r9 = r10.f4307f
                com.appsamurai.storyly.StoryType r10 = com.appsamurai.storyly.StoryType.LongVideo
                if (r9 != r10) goto L_0x00d3
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$c r9 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$c
                android.view.ViewGroup r10 = r8.f5344a
                android.content.Context r10 = r10.getContext()
                android.view.LayoutInflater r10 = android.view.LayoutInflater.from(r10)
                int r0 = com.appsamurai.storyly.R.layout.st_seek_bar
                android.view.View r10 = r10.inflate(r0, r4, r3)
                int r0 = com.appsamurai.storyly.R.id.st_play_pause
                android.view.View r4 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
                android.widget.ImageView r4 = (android.widget.ImageView) r4
                if (r4 == 0) goto L_0x00c1
                int r0 = com.appsamurai.storyly.R.id.st_seek_bar
                android.view.View r5 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
                android.widget.SeekBar r5 = (android.widget.SeekBar) r5
                if (r5 == 0) goto L_0x00c1
                int r0 = com.appsamurai.storyly.R.id.st_seek_bar_time
                android.view.View r6 = androidx.viewbinding.ViewBindings.findChildViewById(r10, r0)
                android.widget.TextView r6 = (android.widget.TextView) r6
                if (r6 == 0) goto L_0x00c1
                com.appsamurai.storyly.databinding.g r0 = new com.appsamurai.storyly.databinding.g
                android.widget.RelativeLayout r10 = (android.widget.RelativeLayout) r10
                r0.<init>(r10, r4, r5, r6)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
                r9.<init>(r8, r0)
                goto L_0x0069
            L_0x00c1:
                android.content.res.Resources r7 = r10.getResources()
                java.lang.String r7 = r7.getResourceName(r0)
                java.lang.NullPointerException r8 = new java.lang.NullPointerException
                java.lang.String r7 = r1.concat(r7)
                r8.<init>(r7)
                throw r8
            L_0x00d3:
                if (r4 != 0) goto L_0x00d6
                goto L_0x00ed
            L_0x00d6:
                r8.f5346c = r4
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r8 = r7.f5391a
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$a r9 = r8.f5346c
                if (r9 != 0) goto L_0x00df
                goto L_0x00ed
            L_0x00df:
                android.view.ViewGroup r9 = r9.f5357a
                android.view.ViewGroup r8 = r8.f5344a
                r8.addView(r9)
                com.appsamurai.storyly.storylypresenter.storylyfooter.a r7 = r7.f5391a
                android.view.ViewGroup r7 = r7.f5344a
                r7.setVisibility(r3)
            L_0x00ed:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.a.e.afterChange(kotlin.reflect.KProperty, java.lang.Object, java.lang.Object):void");
        }
    }

    public static final class f extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f5392a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public f(Object obj, Object obj2, a aVar) {
            super(null);
            this.f5392a = aVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            Integer num3 = num2;
            Integer num4 = num;
            a aVar = this.f5392a;
            C0032a aVar2 = aVar.f5346c;
            if (aVar2 != null) {
                aVar2.a((Integer) aVar.f5350g.getValue(aVar, a.f5343n[1]));
            }
        }
    }

    static {
        Class<a> cls = a.class;
        f5343n = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "storylyItem", "getStorylyItem$storyly_release()Lcom/appsamurai/storyly/data/StorylyItem;", 0), androidx.compose.ui.autofill.a.m(cls, "storylyCurrentIndex", "getStorylyCurrentIndex$storyly_release()Ljava/lang/Integer;", 0)};
    }

    public a(@NotNull ViewGroup viewGroup, @NotNull StorylyConfig storylyConfig) {
        Intrinsics.checkNotNullParameter(viewGroup, "holder");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f5344a = viewGroup;
        this.f5345b = storylyConfig;
        Delegates delegates = Delegates.INSTANCE;
        this.f5349f = new e((Object) null, (Object) null, this);
        this.f5350g = new f((Object) null, (Object) null, this);
    }

    public final class c extends C0032a {
        @NotNull

        /* renamed from: c  reason: collision with root package name */
        public final g f5376c;

        /* renamed from: d  reason: collision with root package name */
        public boolean f5377d;
        @NotNull

        /* renamed from: e  reason: collision with root package name */
        public Handler f5378e = new Handler(Looper.getMainLooper());

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.a$c$a  reason: collision with other inner class name */
        public static final class C0035a implements SeekBar.OnSeekBarChangeListener {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a f5379a;

            /* renamed from: b  reason: collision with root package name */
            public final /* synthetic */ c f5380b;

            public C0035a(a aVar, c cVar) {
                this.f5379a = aVar;
                this.f5380b = cVar;
            }

            public void onProgressChanged(@Nullable SeekBar seekBar, int i3, boolean z2) {
            }

            public void onStartTrackingTouch(@Nullable SeekBar seekBar) {
                Function0<Unit> function0 = this.f5379a.f5354k;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onUserSeekStarted");
                    function0 = null;
                }
                function0.invoke();
                this.f5380b.f5377d = true;
            }

            public void onStopTrackingTouch(@Nullable SeekBar seekBar) {
                if (seekBar != null) {
                    a aVar = this.f5379a;
                    z zVar = (z) aVar.f5349f.getValue(aVar, a.f5343n[0]);
                    if (zVar != null) {
                        Function1<? super Long, Unit> function1 = this.f5379a.f5353j;
                        Function0<Unit> function0 = null;
                        if (function1 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("onUserSeek");
                            function1 = null;
                        }
                        function1.invoke(Long.valueOf((long) (((double) seekBar.getProgress()) * 0.01d * ((double) zVar.f4304c))));
                        Function0<Unit> function02 = this.f5379a.f5355l;
                        if (function02 != null) {
                            function0 = function02;
                        } else {
                            Intrinsics.throwUninitializedPropertyAccessException("onUserSeekEnded");
                        }
                        function0.invoke();
                        this.f5380b.f5377d = false;
                    }
                }
            }
        }

        public final class b extends Drawable {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final List<Pair<Integer, Float>> f5381a;

            /* renamed from: b  reason: collision with root package name */
            public final float f5382b;
            @NotNull

            /* renamed from: c  reason: collision with root package name */
            public final Paint f5383c;
            @NotNull

            /* renamed from: d  reason: collision with root package name */
            public final Paint f5384d;

            public b(@NotNull c cVar, List<Pair<Integer, Float>> list) {
                Intrinsics.checkNotNullParameter(cVar, "this$0");
                Intrinsics.checkNotNullParameter(list, "parts");
                this.f5381a = list;
                float dimension = cVar.a().getContext().getResources().getDimension(R.dimen.st_seek_bar_background_thickness);
                this.f5382b = cVar.a().getContext().getResources().getDimension(R.dimen.st_seek_bar_background_padding);
                Paint paint = new Paint(1);
                paint.setColor(-1);
                paint.setStrokeWidth(dimension);
                Paint.Cap cap = Paint.Cap.ROUND;
                paint.setStrokeCap(cap);
                Paint.Style style = Paint.Style.STROKE;
                paint.setStyle(style);
                Unit unit = Unit.INSTANCE;
                this.f5383c = paint;
                Paint paint2 = new Paint(1);
                paint2.setColor(Color.parseColor("#FF5F4A"));
                paint2.setStrokeWidth(dimension);
                paint2.setStrokeCap(cap);
                paint2.setStyle(style);
                this.f5384d = paint2;
            }

            public void draw(@NotNull Canvas canvas) {
                Intrinsics.checkNotNullParameter(canvas, "canvas");
                Rect bounds = getBounds();
                float f2 = (float) bounds.left;
                for (Pair pair : this.f5381a) {
                    canvas.drawLine(f2, (float) bounds.centerY(), (((Number) pair.getSecond()).floatValue() * ((float) bounds.width())) - this.f5382b, (float) getBounds().centerY(), ((Number) pair.getFirst()).intValue() == 0 ? this.f5383c : this.f5384d);
                    f2 = (((Number) pair.getSecond()).floatValue() * ((float) bounds.width())) + this.f5382b;
                }
            }

            public int getOpacity() {
                return -3;
            }

            public void setAlpha(int i3) {
            }

            public void setColorFilter(@Nullable ColorFilter colorFilter) {
            }
        }

        /* renamed from: com.appsamurai.storyly.storylypresenter.storylyfooter.a$c$c  reason: collision with other inner class name */
        public final class C0036c extends Drawable {
            @NotNull

            /* renamed from: a  reason: collision with root package name */
            public final List<Pair<Integer, Float>> f5385a;

            /* renamed from: b  reason: collision with root package name */
            public final float f5386b;
            @NotNull

            /* renamed from: c  reason: collision with root package name */
            public final Paint f5387c;

            public C0036c(@NotNull c cVar, List<Pair<Integer, Float>> list) {
                Intrinsics.checkNotNullParameter(cVar, "this$0");
                Intrinsics.checkNotNullParameter(list, "parts");
                this.f5385a = list;
                float dimension = cVar.a().getContext().getResources().getDimension(R.dimen.st_seek_bar_background_thickness);
                this.f5386b = cVar.a().getContext().getResources().getDimension(R.dimen.st_seek_bar_background_padding);
                Paint paint = new Paint(1);
                paint.setColor(Color.parseColor("#00E0E4"));
                paint.setStrokeWidth(dimension);
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStyle(Paint.Style.STROKE);
                Unit unit = Unit.INSTANCE;
                this.f5387c = paint;
            }

            public void draw(@NotNull Canvas canvas) {
                Intrinsics.checkNotNullParameter(canvas, "canvas");
                float level = (((float) getLevel()) / 100.0f) / 100.0f;
                Rect bounds = getBounds();
                float f2 = (float) bounds.left;
                for (Pair pair : this.f5385a) {
                    if (((Number) pair.getSecond()).floatValue() < level) {
                        Canvas canvas2 = canvas;
                        canvas2.drawLine(f2, (float) bounds.centerY(), (((Number) pair.getSecond()).floatValue() * ((float) bounds.width())) - this.f5386b, (float) bounds.centerY(), this.f5387c);
                        f2 = (((Number) pair.getSecond()).floatValue() * ((float) bounds.width())) + this.f5386b;
                    }
                }
                canvas.drawLine(f2, (float) bounds.centerY(), ((float) bounds.width()) * level, (float) bounds.centerY(), this.f5387c);
            }

            public int getOpacity() {
                return -3;
            }

            public void setAlpha(int i3) {
            }

            public void setColorFilter(@Nullable ColorFilter colorFilter) {
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public c(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.storylypresenter.storylyfooter.a r5, com.appsamurai.storyly.databinding.g r6) {
            /*
                r4 = this;
                java.lang.String r0 = "this$0"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.String r0 = "binding"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                android.widget.RelativeLayout r0 = r6.a()
                java.lang.String r1 = "binding.root"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r4.<init>(r0)
                r4.f5376c = r6
                android.os.Handler r0 = new android.os.Handler
                android.os.Looper r1 = android.os.Looper.getMainLooper()
                r0.<init>(r1)
                r4.f5378e = r0
                android.widget.SeekBar r0 = r6.f4358c
                r1 = 0
                r0.setProgress(r1)
                android.widget.TextView r0 = r6.f4359d
                android.view.ViewGroup r1 = r4.a()
                android.content.Context r1 = r1.getContext()
                android.content.res.Resources r1 = r1.getResources()
                int r2 = com.appsamurai.storyly.R.string.st_long_video_time_text
                r3 = 0
                java.lang.String r3 = r4.a((java.lang.Long) r3)
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                java.lang.String r1 = r1.getString(r2, r3)
                r0.setText(r1)
                android.widget.ImageView r0 = r6.f4357b
                B0.a r1 = new B0.a
                r2 = 2
                r1.<init>(r4, r5, r2)
                r0.setOnClickListener(r1)
                android.widget.SeekBar r6 = r6.f4358c
                com.appsamurai.storyly.storylypresenter.storylyfooter.a$c$a r0 = new com.appsamurai.storyly.storylypresenter.storylyfooter.a$c$a
                r0.<init>(r5, r4)
                r6.setOnSeekBarChangeListener(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.storylypresenter.storylyfooter.a.c.<init>(com.appsamurai.storyly.storylypresenter.storylyfooter.a, com.appsamurai.storyly.databinding.g):void");
        }

        public static final void b(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            cVar.b();
        }

        public final String a(Long l2) {
            if (l2 == null) {
                String string = this.f5357a.getContext().getString(R.string.st_default_long_video_time_text);
                Intrinsics.checkNotNullExpressionValue(string, "layout.context.getString…ult_long_video_time_text)");
                return string;
            }
            float f2 = (float) 60;
            int longValue = (int) ((((float) l2.longValue()) / 1000.0f) % f2);
            StringBuilder sb = new StringBuilder();
            sb.append((int) ((((float) l2.longValue()) / 1000.0f) / f2));
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(longValue < 10 ? Intrinsics.stringPlus(SchemaSymbols.ATTVAL_FALSE_0, Integer.valueOf(longValue)) : String.valueOf(longValue));
            return sb.toString();
        }

        public void c() {
            this.f5376c.f4357b.setSelected(true);
        }

        public void e() {
            this.f5376c.f4357b.setSelected(false);
        }

        public void f() {
            this.f5378e.removeCallbacksAndMessages((Object) null);
            this.f5378e.postDelayed(new E0.d(this, 0), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        }

        public void g() {
            this.f5378e.removeCallbacksAndMessages((Object) null);
        }

        public void h() {
            super.h();
            this.f5378e.removeCallbacksAndMessages((Object) null);
            this.f5378e.postDelayed(new E0.d(this, 1), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
        }

        public static final void a(c cVar, a aVar, View view) {
            Function0<Unit> function0;
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            Function0<Unit> function02 = null;
            if (cVar.f5376c.f4357b.isSelected()) {
                function0 = aVar.f5352i;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onUserResume");
                    function02.invoke();
                }
            } else {
                function0 = aVar.f5351h;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onUserPause");
                    function02.invoke();
                }
            }
            function02 = function0;
            function02.invoke();
        }

        public static final void a(c cVar) {
            Intrinsics.checkNotNullParameter(cVar, "this$0");
            cVar.b();
        }

        public void a(@Nullable Long l2, @Nullable Long l3) {
            if (l2 != null && l3 != null) {
                if (!this.f5377d) {
                    this.f5376c.f4358c.setProgress((int) ((l2.longValue() * ((long) 100)) / l3.longValue()));
                }
                this.f5376c.f4359d.setText(this.f5357a.getContext().getResources().getString(R.string.st_long_video_time_text, new Object[]{a(l2)}));
            }
        }

        public void a(@NotNull List<Pair<Integer, Float>> list) {
            Intrinsics.checkNotNullParameter(list, "parts");
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new b(this, list), new C0036c(this, list)});
            layerDrawable.setId(0, 16908288);
            layerDrawable.setId(1, 16908301);
            this.f5376c.f4358c.setProgressDrawable(layerDrawable);
        }
    }
}
