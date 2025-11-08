package com.reown.sign.storage.link_mode;

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
@DebugMetadata(c = "com.reown.sign.storage.link_mode.LinkModeStorageRepository$insert$2", f = "LinkModeStorageRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class LinkModeStorageRepository$insert$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $appLink;
    int label;
    final /* synthetic */ LinkModeStorageRepository this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LinkModeStorageRepository$insert$2(LinkModeStorageRepository linkModeStorageRepository, String str, Continuation<? super LinkModeStorageRepository$insert$2> continuation) {
        super(2, continuation);
        this.this$0 = linkModeStorageRepository;
        this.$appLink = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LinkModeStorageRepository$insert$2(this.this$0, this.$appLink, continuation);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.linkModeDaoQueries.insertOrIgnore(this.$appLink);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LinkModeStorageRepository$insert$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
