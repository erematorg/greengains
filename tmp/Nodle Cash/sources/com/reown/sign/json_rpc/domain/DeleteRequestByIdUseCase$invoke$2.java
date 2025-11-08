package com.reown.sign.json_rpc.domain;

import com.reown.android.internal.common.storage.verify.VerifyContextStorageRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.json_rpc.domain.DeleteRequestByIdUseCase$invoke$2", f = "DeleteRequestByIdUseCase.kt", i = {}, l = {15}, m = "invokeSuspend", n = {}, s = {})
public final class DeleteRequestByIdUseCase$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $id;
    int label;
    final /* synthetic */ DeleteRequestByIdUseCase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeleteRequestByIdUseCase$invoke$2(DeleteRequestByIdUseCase deleteRequestByIdUseCase, long j2, Continuation<? super DeleteRequestByIdUseCase$invoke$2> continuation) {
        super(2, continuation);
        this.this$0 = deleteRequestByIdUseCase;
        this.$id = j2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DeleteRequestByIdUseCase$invoke$2(this.this$0, this.$id, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.jsonRpcHistory.deleteRecordById(this.$id);
            VerifyContextStorageRepository access$getVerifyContextStorageRepository$p = this.this$0.verifyContextStorageRepository;
            long j2 = this.$id;
            this.label = 1;
            if (access$getVerifyContextStorageRepository$p.delete(j2, this) == coroutine_suspended) {
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
        return ((DeleteRequestByIdUseCase$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
