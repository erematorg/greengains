package com.appsamurai.storyly.util.ui.blur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.renderscript.Allocation;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.ColorInt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class a implements b {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final c f6358a;
    @NotNull

    /* renamed from: b  reason: collision with root package name */
    public final ViewGroup f6359b;

    /* renamed from: c  reason: collision with root package name */
    public int f6360c;
    @NotNull

    /* renamed from: d  reason: collision with root package name */
    public final Lazy f6361d = LazyKt.lazy(new C0057a(this));

    /* renamed from: e  reason: collision with root package name */
    public float f6362e = 16.0f;
    @Nullable

    /* renamed from: f  reason: collision with root package name */
    public d f6363f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    public Bitmap f6364g;
    @NotNull

    /* renamed from: h  reason: collision with root package name */
    public final int[] f6365h = new int[2];
    @NotNull

    /* renamed from: i  reason: collision with root package name */
    public final int[] f6366i = new int[2];
    @NotNull

    /* renamed from: j  reason: collision with root package name */
    public final ViewTreeObserver.OnPreDrawListener f6367j = new J0.a(this);

    /* renamed from: k  reason: collision with root package name */
    public boolean f6368k = true;

    /* renamed from: l  reason: collision with root package name */
    public boolean f6369l;
    @NotNull

    /* renamed from: m  reason: collision with root package name */
    public final Paint f6370m = new Paint(2);

    /* renamed from: com.appsamurai.storyly.util.ui.blur.a$a  reason: collision with other inner class name */
    public static final class C0057a extends Lambda implements Function0<e> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a f6371a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C0057a(a aVar) {
            super(0);
            this.f6371a = aVar;
        }

        public Object invoke() {
            return new e(this.f6371a.f6358a.getContext());
        }
    }

    public a(@NotNull c cVar, @NotNull ViewGroup viewGroup, @ColorInt int i3) {
        Intrinsics.checkNotNullParameter(cVar, "blurView");
        Intrinsics.checkNotNullParameter(viewGroup, "rootView");
        this.f6358a = cVar;
        this.f6359b = viewGroup;
        this.f6360c = i3;
        a(cVar.getMeasuredWidth(), cVar.getMeasuredHeight());
    }

    public static final boolean a(a aVar) {
        Intrinsics.checkNotNullParameter(aVar, "this$0");
        aVar.d();
        return true;
    }

    public void b() {
        a(this.f6358a.getMeasuredWidth(), this.f6358a.getMeasuredHeight());
    }

    public final e c() {
        return (e) this.f6361d.getValue();
    }

    public final void d() {
        d dVar;
        if (this.f6368k && this.f6369l && (dVar = this.f6363f) != null) {
            Bitmap bitmap = this.f6364g;
            if (bitmap != null) {
                bitmap.eraseColor(0);
            }
            dVar.save();
            this.f6359b.getLocationOnScreen(this.f6365h);
            this.f6358a.getLocationOnScreen(this.f6366i);
            int[] iArr = this.f6366i;
            int i3 = iArr[0];
            int[] iArr2 = this.f6365h;
            int i4 = iArr[1] - iArr2[1];
            Bitmap bitmap2 = this.f6364g;
            Intrinsics.checkNotNull(bitmap2);
            float height = ((float) this.f6358a.getHeight()) / ((float) bitmap2.getHeight());
            Bitmap bitmap3 = this.f6364g;
            Intrinsics.checkNotNull(bitmap3);
            float width = ((float) this.f6358a.getWidth()) / ((float) bitmap3.getWidth());
            float f2 = ((float) (-(i3 - iArr2[0]))) / width;
            float f3 = ((float) (-i4)) / height;
            d dVar2 = this.f6363f;
            if (dVar2 != null) {
                dVar2.translate(f2, f3);
            }
            d dVar3 = this.f6363f;
            if (dVar3 != null) {
                float f4 = (float) 1;
                dVar3.scale(f4 / width, f4 / height);
            }
            this.f6359b.draw(dVar);
            dVar.restore();
            Bitmap bitmap4 = this.f6364g;
            if (bitmap4 == null) {
                bitmap4 = null;
            } else {
                e c3 = c();
                float f5 = this.f6362e;
                c3.getClass();
                Intrinsics.checkNotNullParameter(bitmap4, "bitmap");
                Allocation createFromBitmap = Allocation.createFromBitmap(c3.b(), bitmap4);
                if (!(bitmap4.getHeight() == c3.f6379e && bitmap4.getWidth() == c3.f6378d)) {
                    Allocation allocation = c3.f6377c;
                    if (allocation != null) {
                        allocation.destroy();
                    }
                    c3.f6377c = Allocation.createTyped(c3.b(), createFromBitmap.getType());
                    c3.f6378d = bitmap4.getWidth();
                    c3.f6379e = bitmap4.getHeight();
                }
                c3.a().setRadius(f5);
                c3.a().setInput(createFromBitmap);
                c3.a().forEach(c3.f6377c);
                Allocation allocation2 = c3.f6377c;
                if (allocation2 != null) {
                    allocation2.copyTo(bitmap4);
                }
                createFromBitmap.destroy();
                Unit unit = Unit.INSTANCE;
            }
            this.f6364g = bitmap4;
            c().getClass();
        }
    }

    public final void a(int i3, int i4) {
        d dVar;
        c().getClass();
        float f2 = (float) i4;
        if (((int) Math.ceil((double) (f2 / 8.0f))) == 0 || ((int) Math.ceil((double) (((float) i3) / 8.0f))) == 0) {
            this.f6358a.setWillNotDraw(true);
            return;
        }
        this.f6358a.setWillNotDraw(false);
        float f3 = (float) i3;
        int ceil = (int) Math.ceil((double) (f3 / 8.0f));
        int i5 = ceil % 64;
        if (i5 != 0) {
            ceil = (ceil - i5) + 64;
        }
        c().getClass();
        Bitmap createBitmap = Bitmap.createBitmap(ceil, (int) Math.ceil((double) (f2 / (f3 / ((float) ceil)))), Bitmap.Config.ARGB_8888);
        this.f6364g = createBitmap;
        if (createBitmap == null) {
            dVar = null;
        } else {
            dVar = new d(createBitmap);
        }
        this.f6363f = dVar;
        this.f6369l = true;
    }

    public void b(boolean z2) {
        this.f6359b.getViewTreeObserver().removeOnPreDrawListener(this.f6367j);
        if (z2) {
            this.f6359b.getViewTreeObserver().addOnPreDrawListener(this.f6367j);
        }
    }

    public boolean a(@Nullable Canvas canvas) {
        if (!this.f6368k || !this.f6369l) {
            return true;
        }
        if (canvas == null || (canvas instanceof d)) {
            return false;
        }
        d();
        Bitmap bitmap = this.f6364g;
        if (bitmap == null) {
            return false;
        }
        float height = ((float) this.f6358a.getHeight()) / ((float) bitmap.getHeight());
        canvas.save();
        canvas.scale(((float) this.f6358a.getWidth()) / ((float) bitmap.getWidth()), height);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.f6370m);
        canvas.restore();
        int i3 = this.f6360c;
        if (i3 != 0) {
            canvas.drawColor(i3);
        }
        return true;
    }

    public void a() {
        b(false);
        e c3 = c();
        c3.a().destroy();
        c3.b().destroy();
        Allocation allocation = c3.f6377c;
        if (allocation != null) {
            allocation.destroy();
        }
        this.f6369l = false;
    }

    public void a(float f2) {
        this.f6362e = f2;
    }

    public void a(boolean z2) {
        this.f6368k = z2;
        b(z2);
        this.f6358a.invalidate();
    }

    public void a(int i3) {
        if (this.f6360c != i3) {
            this.f6360c = i3;
            this.f6358a.invalidate();
        }
    }
}
