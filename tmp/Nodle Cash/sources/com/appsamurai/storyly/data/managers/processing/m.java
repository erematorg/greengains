package com.appsamurai.storyly.data.managers.processing;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@DebugMetadata(c = "com.appsamurai.storyly.data.managers.processing.StorylyLocalDataManager$writeData$1", f = "StorylyLocalDataManager.kt", i = {}, l = {33}, m = "invokeSuspend", n = {}, s = {})
public final class m extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    public int f4022a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ n f4023b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ String f4024c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ a f4025d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Function0<Unit> f4026e;

    @DebugMetadata(c = "com.appsamurai.storyly.data.managers.processing.StorylyLocalDataManager$writeData$1$1", f = "StorylyLocalDataManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Function0<Unit> f4027a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public a(Function0<Unit> function0, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f4027a = function0;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.f4027a, continuation);
        }

        public Object invoke(Object obj, Object obj2) {
            CoroutineScope coroutineScope = (CoroutineScope) obj;
            return new a(this.f4027a, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            Function0<Unit> function0 = this.f4027a;
            if (function0 != null) {
                function0.invoke();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m(n nVar, String str, a aVar, Function0<Unit> function0, Continuation<? super m> continuation) {
        super(2, continuation);
        this.f4023b = nVar;
        this.f4024c = str;
        this.f4025d = aVar;
        this.f4026e = function0;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new m(this.f4023b, this.f4024c, this.f4025d, this.f4026e, continuation);
    }

    public Object invoke(Object obj, Object obj2) {
        CoroutineScope coroutineScope = (CoroutineScope) obj;
        return new m(this.f4023b, this.f4024c, this.f4025d, this.f4026e, (Continuation) obj2).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f4022a;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            n nVar = this.f4023b;
            String str2 = this.f4024c;
            a aVar = this.f4025d;
            if (nVar.a(nVar.b(str2), aVar.f3947a) && (str = aVar.f3948b) != null) {
                nVar.a(nVar.c(str2), str);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            a aVar2 = new a(this.f4026e, (Continuation<? super a>) null);
            this.f4022a = 1;
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
