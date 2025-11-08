package com.reown.android.push.client;

import com.reown.android.push.network.PushService;
import com.reown.android.push.network.model.PushResponse;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.ResponseBody;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.push.client.PushClient$unregister$1", f = "PushClient.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
public final class PushClient$unregister$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Throwable, Unit> $onError;
    final /* synthetic */ Function0<Unit> $onSuccess;
    int label;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.push.client.PushClient$unregister$1$1", f = "PushClient.kt", i = {}, l = {54}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.push.client.PushClient$unregister$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(function0, function1, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            PushResponse.Error error;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                PushClient pushClient = PushClient.INSTANCE;
                PushService access$getPushService = pushClient.getPushService();
                String value = pushClient.getProjectId().getValue();
                String clientId = pushClient.getClientId();
                this.label = 1;
                obj = access$getPushService.unregister(value, clientId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e3) {
                    function1.invoke(e3);
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Response response = (Response) obj;
            String str = null;
            if (!response.isSuccessful() || response.body() == null) {
                Function1<Throwable, Unit> function1 = function1;
                ResponseBody errorBody = response.errorBody();
                if (errorBody != null) {
                    str = errorBody.string();
                }
                function1.invoke(new IllegalArgumentException(str));
                return Unit.INSTANCE;
            }
            Object body = response.body();
            Intrinsics.checkNotNull(body);
            if (Intrinsics.areEqual((Object) ((PushResponse) body).getStatus(), (Object) "SUCCESS")) {
                function0.invoke();
            } else {
                Function1<Throwable, Unit> function12 = function1;
                Object body2 = response.body();
                Intrinsics.checkNotNull(body2);
                List<PushResponse.Error> errors = ((PushResponse) body2).getErrors();
                if (!(errors == null || (error = (PushResponse.Error) CollectionsKt.first(errors)) == null)) {
                    str = error.getMessage();
                }
                function12.invoke(new IllegalArgumentException(str));
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PushClient$unregister$1(Function0<Unit> function0, Function1<? super Throwable, Unit> function1, Continuation<? super PushClient$unregister$1> continuation) {
        super(2, continuation);
        this.$onSuccess = function0;
        this.$onError = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PushClient$unregister$1(this.$onSuccess, this.$onError, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final Function0<Unit> function0 = this.$onSuccess;
            final Function1<Throwable, Unit> function1 = this.$onError;
            AnonymousClass1 r6 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r6, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PushClient$unregister$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
