package com.reown.android.internal.common.storage.push_messages;

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
@DebugMetadata(c = "com.reown.android.internal.common.storage.push_messages.PushMessagesRepository$insertPushMessage$2", f = "PushMessagesRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class PushMessagesRepository$insertPushMessage$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $blob;
    final /* synthetic */ String $id;
    final /* synthetic */ int $tag;
    final /* synthetic */ String $topic;
    int label;
    final /* synthetic */ PushMessagesRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PushMessagesRepository$insertPushMessage$2(PushMessagesRepository pushMessagesRepository, String str, String str2, String str3, int i3, Continuation<? super PushMessagesRepository$insertPushMessage$2> continuation) {
        super(2, continuation);
        this.this$0 = pushMessagesRepository;
        this.$id = str;
        this.$topic = str2;
        this.$blob = str3;
        this.$tag = i3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PushMessagesRepository$insertPushMessage$2(this.this$0, this.$id, this.$topic, this.$blob, this.$tag, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.pushMessageQueries.upsertMessage(this.$id, this.$topic, this.$blob, (long) this.$tag);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PushMessagesRepository$insertPushMessage$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
