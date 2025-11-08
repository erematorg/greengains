package com.appsamurai.storyly.data.managers.processing;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DebugMetadata(c = "com.appsamurai.storyly.data.managers.processing.StorylyLocalDataManager$readData$1", f = "StorylyLocalDataManager.kt", i = {}, l = {24}, m = "invokeSuspend", n = {}, s = {})
public final class l extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f4016a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ n f4017b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4018c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Function1<a, Unit> f4019d;

    @DebugMetadata(c = "com.appsamurai.storyly.data.managers.processing.StorylyLocalDataManager$readData$1$1", f = "StorylyLocalDataManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function1<a, Unit> f4020a;

        /* renamed from: b  reason: collision with root package name */
        public final /* synthetic */ a f4021b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Function1<? super a, Unit> function1, a aVar, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f4020a = function1;
            this.f4021b = aVar;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.f4020a, this.f4021b, continuation);
        }

        public Object invoke(Object obj, Object obj2) {
            CoroutineScope coroutineScope = (CoroutineScope) obj;
            return new a(this.f4020a, this.f4021b, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            Function1<a, Unit> function1 = this.f4020a;
            if (function1 != null) {
                function1.invoke(this.f4021b);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public l(n nVar, String str, Function1<? super a, Unit> function1, Continuation<? super l> continuation) {
        super(2, continuation);
        this.f4017b = nVar;
        this.f4018c = str;
        this.f4019d = function1;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new l(this.f4017b, this.f4018c, this.f4019d, continuation);
    }

    public Object invoke(Object obj, Object obj2) {
        CoroutineScope coroutineScope = (CoroutineScope) obj;
        return new l(this.f4017b, this.f4018c, this.f4019d, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        a aVar;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f4016a;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            n nVar = this.f4017b;
            String str = this.f4018c;
            String d2 = nVar.d(nVar.b(str));
            if (d2 == null) {
                aVar = null;
            } else {
                String d3 = nVar.d(nVar.c(str));
                aVar = d3 == null ? new a(d2, (String) null) : new a(d2, d3);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            a aVar2 = new a(this.f4019d, aVar, (Continuation<? super a>) null);
            this.f4016a = 1;
            if (BuildersKt.withContext(main, aVar2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
