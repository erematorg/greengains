package com.appsamurai.storyly.util.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewGroupKt;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.zksync.protocol.JsonRpc2_0ZkSync;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class j implements View.OnTouchListener {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final com.appsamurai.storyly.storylypresenter.d f6394a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public g f6395b = new g(this, 3.0f, 1.0f);
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public c f6396c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public b f6397d = new b(this, -2.0f);
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public h f6398e;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public f f6399f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Float f6400g;

    /* renamed from: h  reason: collision with root package name */
    public float f6401h;

    public static final class a {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public static final a f6402a = new a();
        @NotNull

        /* renamed from: b  reason: collision with root package name */
        public static Property<View, Float> f6403b;

        /* renamed from: c  reason: collision with root package name */
        public static float f6404c;

        /* renamed from: d  reason: collision with root package name */
        public static int f6405d;

        static {
            Property<View, Float> property = View.TRANSLATION_X;
            Intrinsics.checkNotNullExpressionValue(property, "TRANSLATION_X");
            f6403b = property;
        }
    }

    public final class b implements h, Animator.AnimatorListener {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public Interpolator f6406a = new DecelerateInterpolator();

        /* renamed from: b  reason: collision with root package name */
        public float f6407b;

        /* renamed from: c  reason: collision with root package name */
        public float f6408c;

        /* renamed from: d  reason: collision with root package name */
        public final /* synthetic */ j f6409d;

        public b(j jVar, float f2) {
            Intrinsics.checkNotNullParameter(jVar, "this$0");
            this.f6409d = jVar;
            this.f6407b = f2;
            this.f6408c = f2 * 2.0f;
        }

        public boolean a(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            return true;
        }

        public boolean b(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            return true;
        }

        public void onAnimationCancel(@Nullable Animator animator) {
        }

        public void onAnimationEnd(@Nullable Animator animator) {
            j jVar = this.f6409d;
            jVar.a((h) jVar.f6396c);
        }

        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        public void onAnimationStart(@Nullable Animator animator) {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: android.animation.ObjectAnimator} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: android.animation.ObjectAnimator} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: android.animation.AnimatorSet} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: android.animation.ObjectAnimator} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(@org.jetbrains.annotations.NotNull com.appsamurai.storyly.util.ui.j.h r8) {
            /*
                r7 = this;
                r0 = 0
                r1 = 1
                java.lang.String r2 = "fromState"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
                com.appsamurai.storyly.util.ui.j r8 = r7.f6409d
                com.appsamurai.storyly.storylypresenter.d r8 = r8.f6394a
                com.appsamurai.storyly.util.ui.j$a r2 = com.appsamurai.storyly.util.ui.j.a.f6402a
                java.lang.String r2 = "view"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r2)
                float r2 = r8.getTranslationX()
                com.appsamurai.storyly.util.ui.j.a.f6404c = r2
                int r2 = r8.getWidth()
                com.appsamurai.storyly.util.ui.j.a.f6405d = r2
                com.appsamurai.storyly.util.ui.j r2 = r7.f6409d
                float r2 = r2.f6401h
                r3 = 0
                int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r4 != 0) goto L_0x0029
                goto L_0x0037
            L_0x0029:
                if (r4 >= 0) goto L_0x002f
                boolean r4 = com.appsamurai.storyly.util.ui.j.e.f6416c
                if (r4 != 0) goto L_0x0037
            L_0x002f:
                int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r4 <= 0) goto L_0x003e
                boolean r4 = com.appsamurai.storyly.util.ui.j.e.f6416c
                if (r4 != 0) goto L_0x003e
            L_0x0037:
                float r8 = com.appsamurai.storyly.util.ui.j.a.f6404c
                android.animation.ObjectAnimator r8 = r7.a((float) r8)
                goto L_0x007d
            L_0x003e:
                float r4 = -r2
                float r5 = r7.f6407b
                float r5 = r4 / r5
                int r6 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r6 >= 0) goto L_0x0048
                goto L_0x0049
            L_0x0048:
                r3 = r5
            L_0x0049:
                float r4 = r4 * r2
                float r2 = r7.f6408c
                float r4 = r4 / r2
                float r2 = com.appsamurai.storyly.util.ui.j.a.f6404c
                float r2 = r2 + r4
                int r3 = (int) r3
                android.util.Property<android.view.View, java.lang.Float> r4 = com.appsamurai.storyly.util.ui.j.a.f6403b
                float[] r5 = new float[r1]
                r5[r0] = r2
                android.animation.ObjectAnimator r8 = android.animation.ObjectAnimator.ofFloat(r8, r4, r5)
                long r3 = (long) r3
                r8.setDuration(r3)
                android.view.animation.Interpolator r3 = r7.f6406a
                r8.setInterpolator(r3)
                java.lang.String r3 = "slowdownAnim"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r3)
                android.animation.ObjectAnimator r2 = r7.a((float) r2)
                android.animation.AnimatorSet r3 = new android.animation.AnimatorSet
                r3.<init>()
                r4 = 2
                android.animation.Animator[] r4 = new android.animation.Animator[r4]
                r4[r0] = r8
                r4[r1] = r2
                r3.playSequentially(r4)
                r8 = r3
            L_0x007d:
                r8.addListener(r7)
                r8.start()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsamurai.storyly.util.ui.j.b.a(com.appsamurai.storyly.util.ui.j$h):void");
        }

        public final ObjectAnimator a(float f2) {
            com.appsamurai.storyly.storylypresenter.d dVar = this.f6409d.f6394a;
            float abs = (Math.abs(f2) / ((float) a.f6405d)) * ((float) JsonRpc2_0ZkSync.DEFAULT_BLOCK_COMMIT_TIME);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(dVar, a.f6403b, new float[]{e.f6415b});
            ofFloat.setDuration((long) Math.max((int) abs, 200));
            ofFloat.setInterpolator(this.f6406a);
            Intrinsics.checkNotNullExpressionValue(ofFloat, "bounceBackAnim");
            return ofFloat;
        }
    }

    public final class c implements h {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ j f6410a;

        public c(j jVar) {
            Intrinsics.checkNotNullParameter(jVar, "this$0");
            this.f6410a = jVar;
        }

        public void a(@NotNull h hVar) {
            Intrinsics.checkNotNullParameter(hVar, "fromState");
        }

        public boolean b(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            com.appsamurai.storyly.storylypresenter.d dVar = this.f6410a.f6394a;
            Intrinsics.checkNotNullParameter(dVar, "view");
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            if (motionEvent.getHistorySize() != 0) {
                float y2 = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
                float x2 = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
                if (Math.abs(x2) >= Math.abs(y2)) {
                    d.f6411a = dVar.getTranslationX();
                    d.f6412b = x2;
                    d.f6413c = x2 > 0.0f;
                    if ((this.f6410a.f6394a.canScrollHorizontally(-1) || !d.f6413c) && (this.f6410a.f6394a.canScrollHorizontally(1) || d.f6413c)) {
                        return false;
                    }
                    e.f6414a = motionEvent.getPointerId(0);
                    e.f6415b = d.f6411a;
                    e.f6416c = d.f6413c;
                    j jVar = this.f6410a;
                    jVar.a((h) jVar.f6395b);
                    return this.f6410a.f6395b.b(motionEvent);
                }
            }
            return false;
        }

        public boolean a(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            return false;
        }
    }

    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public static float f6411a;

        /* renamed from: b  reason: collision with root package name */
        public static float f6412b;

        /* renamed from: c  reason: collision with root package name */
        public static boolean f6413c;
    }

    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        public static int f6414a;

        /* renamed from: b  reason: collision with root package name */
        public static float f6415b;

        /* renamed from: c  reason: collision with root package name */
        public static boolean f6416c;
    }

    public interface f {
        void a(float f2, @NotNull MotionEvent motionEvent);
    }

    public final class g implements h {

        /* renamed from: a  reason: collision with root package name */
        public final float f6417a;

        /* renamed from: b  reason: collision with root package name */
        public final float f6418b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ j f6419c;

        public g(j jVar, float f2, float f3) {
            Intrinsics.checkNotNullParameter(jVar, "this$0");
            this.f6419c = jVar;
            this.f6417a = f2;
            this.f6418b = f3;
        }

        public void a(@NotNull h hVar) {
            Intrinsics.checkNotNullParameter(hVar, "fromState");
        }

        public boolean b(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            if (e.f6414a != motionEvent.getPointerId(0)) {
                j jVar = this.f6419c;
                jVar.a((h) jVar.f6397d);
                return true;
            }
            com.appsamurai.storyly.storylypresenter.d dVar = this.f6419c.f6394a;
            Intrinsics.checkNotNullParameter(dVar, "view");
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            if (motionEvent.getHistorySize() != 0) {
                float y2 = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
                float x2 = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
                if (Math.abs(x2) >= Math.abs(y2)) {
                    float translationX = dVar.getTranslationX();
                    d.f6411a = translationX;
                    d.f6412b = x2;
                    boolean z2 = x2 > 0.0f;
                    d.f6413c = z2;
                    float f2 = x2 / (z2 == e.f6416c ? this.f6417a : this.f6418b);
                    float f3 = translationX + f2;
                    j jVar2 = this.f6419c;
                    if (jVar2.f6400g == null) {
                        jVar2.f6400g = Float.valueOf(motionEvent.getRawX());
                    }
                    boolean z3 = e.f6416c;
                    if ((!z3 || d.f6413c || f3 > e.f6415b) && (z3 || !d.f6413c || f3 < e.f6415b)) {
                        if (dVar.getParent() != null) {
                            dVar.getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        long eventTime = motionEvent.getEventTime() - motionEvent.getHistoricalEventTime(0);
                        if (eventTime > 0) {
                            this.f6419c.f6401h = f2 / ((float) eventTime);
                        }
                        j jVar3 = this.f6419c;
                        jVar3.getClass();
                        dVar.setTranslationX(f3);
                        for (View next : ViewGroupKt.getChildren(jVar3.f6394a)) {
                            a.a(next, (((float) next.getLeft()) + f3) / (((float) ((jVar3.f6394a.getMeasuredWidth() - jVar3.f6394a.getPaddingLeft()) - jVar3.f6394a.getPaddingRight())) * 1.0f));
                        }
                        return true;
                    }
                    j jVar4 = this.f6419c;
                    float f4 = e.f6415b;
                    jVar4.getClass();
                    dVar.setTranslationX(f4);
                    for (View next2 : ViewGroupKt.getChildren(jVar4.f6394a)) {
                        a.a(next2, (((float) next2.getLeft()) + f4) / (((float) ((jVar4.f6394a.getMeasuredWidth() - jVar4.f6394a.getPaddingLeft()) - jVar4.f6394a.getPaddingRight())) * 1.0f));
                    }
                    motionEvent.offsetLocation(f4 - motionEvent.getX(0), 0.0f);
                    j jVar5 = this.f6419c;
                    jVar5.a((h) jVar5.f6396c);
                    return true;
                }
            }
            return true;
        }

        public boolean a(@NotNull MotionEvent motionEvent) {
            Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
            j jVar = this.f6419c;
            Float f2 = jVar.f6400g;
            if (f2 != null) {
                float floatValue = f2.floatValue();
                f fVar = jVar.f6399f;
                if (fVar != null) {
                    fVar.a(floatValue, motionEvent);
                }
            }
            j jVar2 = this.f6419c;
            jVar2.a((h) jVar2.f6397d);
            this.f6419c.f6400g = null;
            return false;
        }
    }

    public interface h {
        void a(@NotNull h hVar);

        boolean a(@NotNull MotionEvent motionEvent);

        boolean b(@NotNull MotionEvent motionEvent);
    }

    public j(@NotNull com.appsamurai.storyly.storylypresenter.d dVar) {
        Intrinsics.checkNotNullParameter(dVar, "recyclerView");
        this.f6394a = dVar;
        c cVar = new c(this);
        this.f6396c = cVar;
        this.f6398e = cVar;
        a();
    }

    public final void a(@Nullable f fVar) {
        this.f6399f = fVar;
    }

    public boolean onTouch(@Nullable View view, @Nullable MotionEvent motionEvent) {
        Integer valueOf = motionEvent == null ? null : Integer.valueOf(motionEvent.getAction());
        if (valueOf != null && valueOf.intValue() == 2) {
            return this.f6398e.b(motionEvent);
        }
        if ((valueOf != null && valueOf.intValue() == 3) || (valueOf != null && valueOf.intValue() == 1)) {
            return this.f6398e.a(motionEvent);
        }
        return false;
    }

    public final void a() {
        this.f6394a.setOnTouchListener(this);
        this.f6394a.setOverScrollMode(2);
    }

    public final void a(@NotNull h hVar) {
        Intrinsics.checkNotNullParameter(hVar, RemoteConfigConstants.ResponseFieldKey.STATE);
        h hVar2 = this.f6398e;
        this.f6398e = hVar;
        hVar.a(hVar2);
    }
}
