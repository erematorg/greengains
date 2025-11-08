package com.appsamurai.storyly.util.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class i extends AppCompatTextView {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Lazy f6335a;

    public static final class a extends Lambda implements Function0<f> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f6336a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Context context) {
            super(0);
            this.f6336a = context;
        }

        public Object invoke() {
            return new f(this.f6336a);
        }
    }

    public static final class b extends Lambda implements Function1<Typeface, Unit> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i f6337a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ Function0<Unit> f6338b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(i iVar, Function0<Unit> function0) {
            super(1);
            this.f6337a = iVar;
            this.f6338b = function0;
        }

        public Object invoke(Object obj) {
            Typeface typeface = (Typeface) obj;
            Intrinsics.checkNotNullParameter(typeface, "typeface");
            this.f6337a.setTypeface(typeface);
            this.f6338b.invoke();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public i(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.f6335a = LazyKt.lazy(new a(context));
    }

    private final f getFontManager() {
        return (f) this.f6335a.getValue();
    }

    public final void a(@NotNull h hVar, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(hVar, "font");
        Intrinsics.checkNotNullParameter(function0, "onComplete");
        f fontManager = getFontManager();
        b bVar = new b(this, function0);
        fontManager.getClass();
        Intrinsics.checkNotNullParameter(hVar, "font");
        Intrinsics.checkNotNullParameter(bVar, "onComplete");
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), (CoroutineContext) null, (CoroutineStart) null, new e(hVar, fontManager, bVar, (Continuation<? super e>) null), 3, (Object) null);
    }
}
