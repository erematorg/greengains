package com.appsamurai.storyly.util.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import com.appsamurai.storyly.R;
import com.appsamurai.storyly.StoryGroupAnimation;
import com.appsamurai.storyly.config.StorylyConfig;
import com.appsamurai.storyly.exoplayer2.core.ExoPlayer;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressLint({"ViewConstructor"})
public final class k extends AppCompatImageView {
    public static final /* synthetic */ KProperty<Object>[] G;
    @NotNull

    /* renamed from: A  reason: collision with root package name */
    public final ReadWriteProperty f6420A;

    /* renamed from: B  reason: collision with root package name */
    public float f6421B;

    /* renamed from: C  reason: collision with root package name */
    public final ValueAnimator f6422C;

    /* renamed from: D  reason: collision with root package name */
    public boolean f6423D;
    @Nullable

    /* renamed from: E  reason: collision with root package name */
    public StoryGroupAnimation f6424E;
    public boolean F;
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final StorylyConfig f6425a;

    /* renamed from: b  reason: collision with root package name */
    public final boolean f6426b;
    @NotNull

    /* renamed from: c  reason: collision with root package name */
    public final RectF f6427c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final RectF f6428d;
    @NotNull

    /* renamed from: e  reason: collision with root package name */
    public final RectF f6429e;
    @NotNull

    /* renamed from: f  reason: collision with root package name */
    public final RectF f6430f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    public final Matrix f6431g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final Paint f6432h;
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final Paint f6433i;
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final Paint f6434j;
    @NotNull

    /* renamed from: k  reason: collision with root package name */
    public final Paint f6435k;

    /* renamed from: l  reason: collision with root package name */
    public a f6436l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Paint f6437m;

    /* renamed from: n  reason: collision with root package name */
    public float f6438n;
    @Nullable

    /* renamed from: o  reason: collision with root package name */
    public Bitmap f6439o;
    @Nullable

    /* renamed from: p  reason: collision with root package name */
    public BitmapShader f6440p;

    /* renamed from: q  reason: collision with root package name */
    public int f6441q;

    /* renamed from: r  reason: collision with root package name */
    public int f6442r;

    /* renamed from: s  reason: collision with root package name */
    public float f6443s;

    /* renamed from: t  reason: collision with root package name */
    public float f6444t;

    /* renamed from: u  reason: collision with root package name */
    public float f6445u;

    /* renamed from: v  reason: collision with root package name */
    public float f6446v;

    /* renamed from: w  reason: collision with root package name */
    public float f6447w;
    @NotNull

    /* renamed from: x  reason: collision with root package name */
    public final ReadWriteProperty f6448x;

    /* renamed from: y  reason: collision with root package name */
    public int f6449y;

    /* renamed from: z  reason: collision with root package name */
    public int f6450z;

    @RequiresApi(api = 21)
    public final class b extends ViewOutlineProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ k f6453a;

        public b(k kVar) {
            Intrinsics.checkNotNullParameter(kVar, "this$0");
            this.f6453a = kVar;
        }

        public void getOutline(@NotNull View view, @NotNull Outline outline) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(outline, "outline");
            Rect rect = new Rect();
            this.f6453a.f6429e.roundOut(rect);
            outline.setRoundRect(rect, ((float) rect.width()) / 2.0f);
        }
    }

    public static final class c implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ k f6454a;

        public c(k kVar) {
            this.f6454a = kVar;
        }

        public void onAnimationCancel(@NotNull Animator animator) {
        }

        public void onAnimationEnd(@NotNull Animator animator) {
            k kVar = this.f6454a;
            if (kVar.f6423D) {
                kVar.f6421B = 0.0f;
                kVar.f6423D = false;
            }
        }

        public void onAnimationRepeat(@NotNull Animator animator) {
        }

        public void onAnimationStart(@NotNull Animator animator) {
        }
    }

    public static final class d extends ObservableProperty<List<? extends Integer>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f6455a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ k f6456b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public d(Object obj, Object obj2, k kVar) {
            super(obj2);
            this.f6455a = obj;
            this.f6456b = kVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, List<? extends Integer> list, List<? extends Integer> list2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            List list3 = list2;
            List list4 = list;
            k kVar = this.f6456b;
            kVar.f6449y = kVar.getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_distance_to_border);
            k kVar2 = this.f6456b;
            kVar2.f6450z = kVar2.getResources().getDimensionPixelSize(R.dimen.st_story_group_icon_border_thickness);
            this.f6456b.b();
        }
    }

    public static final class e extends ObservableProperty<Integer> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Object f6457a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ k f6458b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public e(Object obj, Object obj2, k kVar) {
            super(obj2);
            this.f6457a = obj;
            this.f6458b = kVar;
        }

        public void afterChange(@NotNull KProperty<?> kProperty, Integer num, Integer num2) {
            Intrinsics.checkNotNullParameter(kProperty, "property");
            num2.intValue();
            num.intValue();
            this.f6458b.b();
        }
    }

    static {
        Class<k> cls = k.class;
        G = new KProperty[]{androidx.compose.ui.autofill.a.m(cls, "borderColor", "getBorderColor$storyly_release()Ljava/util/List;", 0), androidx.compose.ui.autofill.a.m(cls, "avatarBackgroundColor", "getAvatarBackgroundColor$storyly_release()I", 0)};
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(Context context, StorylyConfig storylyConfig, boolean z2, int i3) {
        this(context, storylyConfig, (i3 & 4) != 0 ? false : z2);
    }

    private final float getAvatarInset() {
        return ((float) this.f6449y) + ((float) this.f6450z);
    }

    /* access modifiers changed from: private */
    public final float getCurrentAnimationArchesArea() {
        return this.f6447w * this.f6446v;
    }

    private final float getSpaceBetweenArches() {
        return (this.f6446v / ((float) 20)) - 3.0f;
    }

    public final void b() {
        RectF rectF;
        float f2;
        float f3;
        if (getWidth() != 0 || getHeight() != 0) {
            Bitmap bitmap = this.f6439o;
            if (bitmap == null) {
                setImageResource(17170445);
                return;
            }
            this.f6442r = bitmap.getHeight();
            this.f6441q = bitmap.getWidth();
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.f6440p = new BitmapShader(bitmap, tileMode, tileMode);
            this.f6432h.setAntiAlias(true);
            this.f6432h.setShader(this.f6440p);
            float f4 = (float) this.f6450z;
            RectF rectF2 = this.f6429e;
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            if (this.f6426b) {
                int i3 = this.f6450z;
                int i4 = width - i3;
                int i5 = height - i3;
                float paddingLeft = ((float) getPaddingLeft()) + ((float) (this.f6450z / 2));
                float paddingTop = ((float) getPaddingTop()) + ((float) (this.f6450z / 2));
                rectF = new RectF(paddingLeft, paddingTop, ((float) i4) + paddingLeft, ((float) i5) + paddingTop);
            } else {
                int min = Math.min(width, height);
                float paddingLeft2 = (((float) (width - min)) / 2.0f) + ((float) getPaddingLeft());
                float paddingTop2 = (((float) (height - min)) / 2.0f) + ((float) getPaddingTop());
                float f5 = (float) min;
                rectF = new RectF(paddingLeft2, paddingTop2, paddingLeft2 + f5, f5 + paddingTop2);
            }
            rectF2.set(rectF);
            this.f6445u = Math.min((this.f6429e.height() - f4) / 2.0f, (this.f6429e.width() - f4) / 2.0f);
            SweepGradient sweepGradient = new SweepGradient(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f, CollectionsKt.toIntArray(getBorderColor$storyly_release()), (float[]) null);
            Paint paint = this.f6434j;
            paint.setShader(sweepGradient);
            paint.setStrokeWidth(f4);
            paint.setAntiAlias(true);
            paint.setStrokeCap(this.f6426b ? Paint.Cap.SQUARE : Paint.Cap.ROUND);
            Paint.Style style = Paint.Style.STROKE;
            paint.setStyle(style);
            Paint paint2 = this.f6435k;
            paint2.setShader(sweepGradient);
            paint2.setStrokeWidth(f4);
            paint2.setAntiAlias(true);
            paint2.setStrokeCap(this.f6426b ? Paint.Cap.SQUARE : Paint.Cap.ROUND);
            paint2.setStyle(style);
            this.f6427c.set(this.f6429e);
            if (this.f6426b) {
                this.f6427c.inset(getAvatarInset() * 0.75f, getAvatarInset() * 0.75f);
            } else {
                this.f6427c.inset(getAvatarInset(), getAvatarInset());
            }
            float f6 = (float) 2;
            this.f6438n = ((this.f6429e.width() - (f4 * f6)) - this.f6427c.width()) / f6;
            this.f6428d.set(this.f6429e);
            RectF rectF3 = this.f6428d;
            float f7 = (this.f6438n / f6) + f4;
            rectF3.inset(f7, f7);
            this.f6444t = Math.min((float) Math.floor((double) (this.f6428d.height() / 2.0f)), (float) Math.floor((double) (this.f6428d.width() / 2.0f)));
            this.f6443s = Math.min(this.f6427c.height() / 2.0f, this.f6427c.width() / 2.0f);
            Paint paint3 = this.f6433i;
            paint3.setStyle(style);
            paint3.setAntiAlias(true);
            paint3.setColor(0);
            paint3.setStrokeWidth(this.f6438n);
            Paint paint4 = this.f6437m;
            paint4.setStyle(Paint.Style.FILL);
            paint4.setAntiAlias(true);
            paint4.setColor(getAvatarBackgroundColor$storyly_release());
            RectF rectF4 = this.f6430f;
            rectF4.set(this.f6429e);
            float f8 = f4 / 2.0f;
            rectF4.inset(f8, f8);
            this.f6431g.set((Matrix) null);
            float f9 = 0.0f;
            if (this.f6427c.height() * ((float) this.f6441q) > this.f6427c.width() * ((float) this.f6442r)) {
                f3 = this.f6427c.height() / ((float) this.f6442r);
                f2 = androidx.compose.animation.core.a.a((float) this.f6441q, f3, this.f6427c.width(), 2.0f);
            } else {
                f3 = this.f6427c.width() / ((float) this.f6441q);
                f2 = 0.0f;
                f9 = androidx.compose.animation.core.a.a((float) this.f6442r, f3, this.f6427c.height(), 2.0f);
            }
            this.f6431g.setScale(f3, f3);
            Matrix matrix = this.f6431g;
            RectF rectF5 = this.f6427c;
            matrix.postTranslate(((float) ((int) (f2 + 0.5f))) + rectF5.left, ((float) ((int) (f9 + 0.5f))) + rectF5.top);
            BitmapShader bitmapShader = this.f6440p;
            Intrinsics.checkNotNull(bitmapShader);
            bitmapShader.setLocalMatrix(this.f6431g);
            invalidate();
        }
    }

    public final int getAvatarBackgroundColor$storyly_release() {
        return ((Number) this.f6420A.getValue(this, G[1])).intValue();
    }

    @NotNull
    public final List<Integer> getBorderColor$storyly_release() {
        return (List) this.f6448x.getValue(this, G[0]);
    }

    @NotNull
    public final StorylyConfig getConfig() {
        return this.f6425a;
    }

    @Nullable
    public final StoryGroupAnimation getTheme() {
        return this.f6424E;
    }

    public void onDraw(@NotNull Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.f6439o != null) {
            if (this.f6426b) {
                float max = Math.max(((float) this.f6425a.getGroup$storyly_release().getIconCornerRadius$storyly_release()) - getAvatarInset(), 0.0f);
                float max2 = Math.max(((float) this.f6425a.getGroup$storyly_release().getIconCornerRadius$storyly_release()) - ((this.f6438n / ((float) 2)) + ((float) this.f6450z)), 0.0f);
                if (getAvatarBackgroundColor$storyly_release() != 0) {
                    canvas.drawRoundRect(this.f6427c, max, max, this.f6437m);
                }
                canvas.drawRoundRect(this.f6427c, max, max, this.f6432h);
                if (this.f6438n > 0.0f) {
                    canvas.drawRoundRect(this.f6428d, max2, max2, this.f6433i);
                }
            } else {
                if (getAvatarBackgroundColor$storyly_release() != 0) {
                    canvas.drawCircle(this.f6427c.centerX(), this.f6427c.centerY(), this.f6443s, this.f6437m);
                }
                canvas.drawCircle(this.f6427c.centerX(), this.f6427c.centerY(), this.f6443s, this.f6432h);
                if (this.f6438n > 0.0f) {
                    canvas.drawCircle(this.f6428d.centerX(), this.f6428d.centerY(), this.f6444t, this.f6433i);
                }
            }
            if (this.F && !this.f6426b) {
                a aVar = this.f6436l;
                if (aVar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageAnimation");
                    aVar = null;
                }
                aVar.getClass();
                Intrinsics.checkNotNullParameter(canvas, "canvas");
                k kVar = aVar.f6452b;
                float f2 = (float) 360;
                float f3 = (kVar.f6421B + 270.0f) % f2;
                if (kVar.getCurrentAnimationArchesArea() != 0.0f) {
                    a(aVar.f6452b, f3, canvas);
                }
                float a2 = aVar.f6452b.getCurrentAnimationArchesArea() + f3;
                k kVar2 = aVar.f6452b;
                canvas.drawArc(kVar2.f6430f, a2, f2 - kVar2.getCurrentAnimationArchesArea(), false, aVar.f6452b.f6434j);
            } else if (this.f6426b) {
                float max3 = (float) Math.max(this.f6425a.getGroup$storyly_release().getIconCornerRadius$storyly_release() - (this.f6450z / 2), 0);
                canvas.drawRoundRect(this.f6429e, max3, max3, this.f6434j);
            } else {
                canvas.drawCircle(this.f6429e.centerX(), this.f6429e.centerY(), this.f6445u, this.f6434j);
            }
        }
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        b();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(@NotNull MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, NotificationCompat.CATEGORY_EVENT);
        return Math.pow(((double) motionEvent.getY()) - ((double) this.f6429e.centerY()), 2.0d) + Math.pow(((double) motionEvent.getX()) - ((double) this.f6429e.centerX()), 2.0d) <= Math.pow((double) this.f6445u, 2.0d) && super.onTouchEvent(motionEvent);
    }

    public final void setAnalyticsAvatarBorder$storyly_release(int i3) {
        setBorderColor$storyly_release(CollectionsKt.listOf(Integer.valueOf(i3), Integer.valueOf(i3)));
        this.f6449y = 0;
        this.f6450z = getResources().getDimensionPixelSize(R.dimen.st_moments_liked_avatar_icon_border);
    }

    public final void setAnimating(boolean z2) {
        this.F = z2;
    }

    public final void setAvatarBackgroundColor$storyly_release(int i3) {
        this.f6420A.setValue(this, G[1], Integer.valueOf(i3));
    }

    public final void setBorderColor$storyly_release(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.f6448x.setValue(this, G[0], list);
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        a();
    }

    public void setImageResource(@DrawableRes int i3) {
        super.setImageResource(i3);
        a();
    }

    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        a();
    }

    public void setPadding(int i3, int i4, int i5, int i6) {
        super.setPadding(i3, i4, i5, i6);
        b();
    }

    public void setPaddingRelative(int i3, int i4, int i5, int i6) {
        super.setPaddingRelative(i3, i4, i5, i6);
        b();
    }

    public final void setTheme(@Nullable StoryGroupAnimation storyGroupAnimation) {
        if (storyGroupAnimation == StoryGroupAnimation.BorderRotation) {
            this.f6436l = new a(this);
        }
        this.f6424E = storyGroupAnimation;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public k(@NotNull Context context, @NotNull StorylyConfig storylyConfig, boolean z2) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(storylyConfig, "config");
        this.f6425a = storylyConfig;
        this.f6426b = z2;
        this.f6427c = new RectF();
        this.f6428d = new RectF();
        this.f6429e = new RectF();
        this.f6430f = new RectF();
        this.f6431g = new Matrix();
        this.f6432h = new Paint();
        this.f6433i = new Paint();
        this.f6434j = new Paint();
        this.f6435k = new Paint();
        this.f6437m = new Paint();
        this.f6446v = 360.0f;
        this.f6447w = 1.0f;
        Delegates delegates = Delegates.INSTANCE;
        List listOf = CollectionsKt.listOf(0, 0);
        this.f6448x = new d(listOf, listOf, this);
        Integer valueOf = Integer.valueOf(storylyConfig.getGroup$storyly_release().getIconBackgroundColor$storyly_release());
        this.f6420A = new e(valueOf, valueOf, this);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setInterpolator(new LinearInterpolator());
        Intrinsics.checkNotNullExpressionValue(ofFloat, "");
        ofFloat.addListener(new c(this));
        Unit unit = Unit.INSTANCE;
        this.f6422C = ofFloat;
        this.f6424E = StoryGroupAnimation.BorderRotation;
        setScaleType(ImageView.ScaleType.CENTER_CROP);
        setOutlineProvider(new b(this));
        b();
    }

    public final void a() {
        Bitmap bitmap;
        Bitmap bitmap2;
        Drawable drawable = getDrawable();
        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            } else {
                try {
                    if (drawable instanceof ColorDrawable) {
                        bitmap2 = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
                    } else {
                        bitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                    }
                    Canvas canvas = new Canvas(bitmap2);
                    drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawable.draw(canvas);
                    bitmap = bitmap2;
                } catch (IllegalArgumentException unused) {
                }
            }
            this.f6439o = bitmap;
            b();
        }
        bitmap = null;
        this.f6439o = bitmap;
        b();
    }

    public final class a {

        /* renamed from: a  reason: collision with root package name */
        public final ValueAnimator f6451a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ k f6452b;

        public a(k kVar) {
            Intrinsics.checkNotNullParameter(kVar, "this$0");
            this.f6452b = kVar;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 360.0f});
            ofFloat.setRepeatCount(-1);
            ofFloat.setDuration(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.addUpdateListener(new I0.b(kVar, this, 0));
            Unit unit = Unit.INSTANCE;
            this.f6451a = ofFloat;
        }

        public static final void a(k kVar, a aVar, ValueAnimator valueAnimator) {
            Intrinsics.checkNotNullParameter(kVar, "this$0");
            Intrinsics.checkNotNullParameter(aVar, "this$1");
            if (kVar.F) {
                Object animatedValue = valueAnimator.getAnimatedValue();
                if (animatedValue != null) {
                    kVar.f6421B = ((Float) animatedValue).floatValue();
                    float f2 = kVar.f6446v;
                    if (f2 >= 0.0f) {
                        kVar.f6446v = f2 - 1.0f;
                    } else {
                        kVar.setAnimating(false);
                    }
                    kVar.invalidate();
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
            aVar.a();
        }

        public final AnimatorSet b() {
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(new Animator[]{this.f6452b.f6422C, this.f6451a});
            return animatorSet;
        }

        public final void a() {
            this.f6451a.removeAllUpdateListeners();
            this.f6451a.removeAllListeners();
            this.f6452b.f6422C.removeAllListeners();
            b().cancel();
            this.f6452b.invalidate();
        }
    }

    public static final void a(k kVar, float f2, Canvas canvas) {
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            Canvas canvas2 = canvas;
            canvas2.drawArc(kVar.f6430f, ((kVar.getSpaceBetweenArches() + 3.0f) * ((float) i3) * kVar.f6447w) + f2, 3.0f, false, kVar.f6434j);
            if (i4 <= 20) {
                i3 = i4;
            } else {
                return;
            }
        }
    }
}
