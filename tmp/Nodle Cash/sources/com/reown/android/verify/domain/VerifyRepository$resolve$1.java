package com.reown.android.verify.domain;

import com.reown.android.verify.data.VerifyService;
import com.reown.android.verify.model.Origin;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.ResponseBody;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository$resolve$1", f = "VerifyRepository.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
public final class VerifyRepository$resolve$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $attestationId;
    final /* synthetic */ String $metadataUrl;
    final /* synthetic */ Function1<Throwable, Unit> $onError;
    final /* synthetic */ Function1<VerifyResult, Unit> $onSuccess;
    int label;
    final /* synthetic */ VerifyRepository this$0;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.reown.android.verify.domain.VerifyRepository$resolve$1$1", f = "VerifyRepository.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.reown.android.verify.domain.VerifyRepository$resolve$1$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(verifyRepository, str, function1, str2, function12, continuation);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                VerifyService access$getVerifyService$p = verifyRepository.verifyService;
                String str = str;
                this.label = 1;
                obj = access$getVerifyService$p.resolveAttestation(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i3 == 1) {
                try {
                    ResultKt.throwOnFailure(obj);
                } catch (Exception e3) {
                    function12.invoke(e3);
                }
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Response response = (Response) obj;
            if (!response.isSuccessful() || response.body() == null) {
                Function1<Throwable, Unit> function1 = function12;
                ResponseBody errorBody = response.errorBody();
                function1.invoke(new IllegalArgumentException(errorBody != null ? errorBody.string() : null));
                return Unit.INSTANCE;
            }
            Object body = response.body();
            Intrinsics.checkNotNull(body);
            String origin = ((Origin) body).getOrigin();
            Object body2 = response.body();
            Intrinsics.checkNotNull(body2);
            function1.invoke(new VerifyResult(VerifyUtilsKt.getValidation(str2, origin), ((Origin) body2).isScam(), origin));
            return Unit.INSTANCE;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerifyRepository$resolve$1(VerifyRepository verifyRepository, String str, Function1<? super VerifyResult, Unit> function1, String str2, Function1<? super Throwable, Unit> function12, Continuation<? super VerifyRepository$resolve$1> continuation) {
        super(2, continuation);
        this.this$0 = verifyRepository;
        this.$attestationId = str;
        this.$onSuccess = function1;
        this.$metadataUrl = str2;
        this.$onError = function12;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VerifyRepository$resolve$1(this.this$0, this.$attestationId, this.$onSuccess, this.$metadataUrl, this.$onError, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            final VerifyRepository verifyRepository = this.this$0;
            final String str = this.$attestationId;
            final Function1<VerifyResult, Unit> function1 = this.$onSuccess;
            final String str2 = this.$metadataUrl;
            final Function1<Throwable, Unit> function12 = this.$onError;
            AnonymousClass1 r3 = new AnonymousClass1((Continuation<? super AnonymousClass1>) null);
            this.label = 1;
            if (SupervisorKt.supervisorScope(r3, this) == coroutine_suspended) {
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
        return ((VerifyRepository$resolve$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
