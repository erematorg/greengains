package com.reown.sign.engine.domain;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

@SourceDebugExtension({"SMAP\nSignEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$propagatePendingSessionRequestsQueue$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,522:1\n1563#2:523\n1634#2,3:524\n774#2:527\n865#2,2:528\n774#2:530\n865#2:531\n866#2:533\n2756#2:534\n1#3:532\n1#3:535\n*S KotlinDebug\n*F\n+ 1 SignEngine.kt\ncom/reown/sign/engine/domain/SignEngine$propagatePendingSessionRequestsQueue$1\n*L\n376#1:523\n376#1:524,3\n384#1:527\n384#1:528,2\n385#1:530\n385#1:531\n385#1:533\n393#1:534\n393#1:535\n*E\n"})
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1", f = "SignEngine.kt", i = {1, 1, 1, 1, 1, 1, 1, 1}, l = {375, 387}, m = "invokeSuspend", n = {"$this$filter$iv", "$this$filterTo$iv$iv", "destination$iv$iv", "element$iv$iv", "sessionRequest", "$i$f$filter", "$i$f$filterTo", "$i$a$-filter-SignEngine$propagatePendingSessionRequestsQueue$1$3"}, s = {"L$0", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"})
public final class SignEngine$propagatePendingSessionRequestsQueue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ SignEngine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SignEngine$propagatePendingSessionRequestsQueue$1(SignEngine signEngine, Continuation<? super SignEngine$propagatePendingSessionRequestsQueue$1> continuation) {
        super(2, continuation);
        this.this$0 = signEngine;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignEngine$propagatePendingSessionRequestsQueue$1(this.this$0, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d0 A[Catch:{ Exception -> 0x0048 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010c A[Catch:{ Exception -> 0x0037 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0139 A[Catch:{ Exception -> 0x0048 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0150 A[Catch:{ Exception -> 0x0048 }, LOOP:3: B:56:0x014a->B:58:0x0150, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x012a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 0
            r4 = 2
            r5 = 1
            r6 = 0
            if (r0 == 0) goto L_0x004b
            if (r0 == r5) goto L_0x0042
            if (r0 != r4) goto L_0x003a
            int r7 = r1.I$1
            int r8 = r1.I$0
            java.lang.Object r0 = r1.L$6
            com.reown.sign.engine.model.EngineDO$SessionRequest r0 = (com.reown.sign.engine.model.EngineDO.SessionRequest) r0
            java.lang.Object r9 = r1.L$5
            java.lang.Object r10 = r1.L$4
            java.util.Iterator r10 = (java.util.Iterator) r10
            java.lang.Object r11 = r1.L$3
            java.util.Collection r11 = (java.util.Collection) r11
            java.lang.Object r12 = r1.L$2
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.lang.Object r13 = r1.L$1
            com.reown.sign.engine.domain.SignEngine r13 = (com.reown.sign.engine.domain.SignEngine) r13
            java.lang.Object r14 = r1.L$0
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ Exception -> 0x0037 }
            r5 = r18
            goto L_0x0100
        L_0x0037:
            r0 = move-exception
            goto L_0x012f
        L_0x003a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r18)     // Catch:{ Exception -> 0x0048 }
            r0 = r18
            goto L_0x005d
        L_0x0048:
            r0 = move-exception
            goto L_0x0167
        L_0x004b:
            kotlin.ResultKt.throwOnFailure(r18)
            com.reown.sign.engine.domain.SignEngine r0 = r1.this$0     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.json_rpc.domain.GetPendingSessionRequests r0 = r0.getPendingSessionRequests     // Catch:{ Exception -> 0x0048 }
            r1.label = r5     // Catch:{ Exception -> 0x0048 }
            java.lang.Object r0 = r0.invoke(r1)     // Catch:{ Exception -> 0x0048 }
            if (r0 != r2) goto L_0x005d
            return r2
        L_0x005d:
            java.lang.Iterable r0 = (java.lang.Iterable) r0     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.domain.SignEngine r7 = r1.this$0     // Catch:{ Exception -> 0x0048 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0048 }
            int r9 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, 10)     // Catch:{ Exception -> 0x0048 }
            r8.<init>(r9)     // Catch:{ Exception -> 0x0048 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x0048 }
        L_0x006e:
            boolean r9 = r0.hasNext()     // Catch:{ Exception -> 0x0048 }
            if (r9 == 0) goto L_0x0090
            java.lang.Object r9 = r0.next()     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.common.model.Request r9 = (com.reown.sign.common.model.Request) r9     // Catch:{ Exception -> 0x0048 }
            com.reown.android.internal.common.storage.metadata.MetadataStorageRepositoryInterface r10 = r7.metadataStorageRepository     // Catch:{ Exception -> 0x0048 }
            com.reown.foundation.common.model.Topic r11 = r9.getTopic()     // Catch:{ Exception -> 0x0048 }
            com.reown.android.internal.common.model.AppMetaDataType r12 = com.reown.android.internal.common.model.AppMetaDataType.PEER     // Catch:{ Exception -> 0x0048 }
            com.reown.android.internal.common.model.AppMetaData r10 = r10.getByTopicAndType(r11, r12)     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.model.EngineDO$SessionRequest r9 = kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, "<this>")     // Catch:{ Exception -> 0x0048 }
            r8.add(r9)     // Catch:{ Exception -> 0x0048 }
            goto L_0x006e
        L_0x0090:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x0048 }
            r0.<init>()     // Catch:{ Exception -> 0x0048 }
            java.util.Iterator r7 = r8.iterator()     // Catch:{ Exception -> 0x0048 }
        L_0x0099:
            boolean r8 = r7.hasNext()     // Catch:{ Exception -> 0x0048 }
            if (r8 == 0) goto L_0x00b8
            java.lang.Object r8 = r7.next()     // Catch:{ Exception -> 0x0048 }
            r9 = r8
            com.reown.sign.engine.model.EngineDO$SessionRequest r9 = (com.reown.sign.engine.model.EngineDO.SessionRequest) r9     // Catch:{ Exception -> 0x0048 }
            com.reown.android.internal.common.model.Expiry r9 = r9.getExpiry()     // Catch:{ Exception -> 0x0048 }
            if (r9 == 0) goto L_0x0099
            com.reown.android.internal.utils.CoreValidator r10 = com.reown.android.internal.utils.CoreValidator.INSTANCE     // Catch:{ Exception -> 0x0048 }
            boolean r9 = r10.isExpired(r9)     // Catch:{ Exception -> 0x0048 }
            if (r9 != 0) goto L_0x0099
            r0.add(r8)     // Catch:{ Exception -> 0x0048 }
            goto L_0x0099
        L_0x00b8:
            com.reown.sign.engine.domain.SignEngine r7 = r1.this$0     // Catch:{ Exception -> 0x0048 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ Exception -> 0x0048 }
            r8.<init>()     // Catch:{ Exception -> 0x0048 }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ Exception -> 0x0048 }
            r12 = r0
            r14 = r12
            r13 = r7
            r11 = r8
            r10 = r9
            r7 = r6
            r8 = r7
        L_0x00ca:
            boolean r0 = r10.hasNext()     // Catch:{ Exception -> 0x0048 }
            if (r0 == 0) goto L_0x0140
            java.lang.Object r9 = r10.next()     // Catch:{ Exception -> 0x0048 }
            r0 = r9
            com.reown.sign.engine.model.EngineDO$SessionRequest r0 = (com.reown.sign.engine.model.EngineDO.SessionRequest) r0     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.use_case.calls.GetSessionsUseCaseInterface r15 = r13.getSessionsUseCase     // Catch:{ Exception -> 0x0037 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r14)     // Catch:{ Exception -> 0x0037 }
            r1.L$0 = r5     // Catch:{ Exception -> 0x0037 }
            r1.L$1 = r13     // Catch:{ Exception -> 0x0037 }
            java.lang.Object r5 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)     // Catch:{ Exception -> 0x0037 }
            r1.L$2 = r5     // Catch:{ Exception -> 0x0037 }
            r1.L$3 = r11     // Catch:{ Exception -> 0x0037 }
            r1.L$4 = r10     // Catch:{ Exception -> 0x0037 }
            r1.L$5 = r9     // Catch:{ Exception -> 0x0037 }
            r1.L$6 = r0     // Catch:{ Exception -> 0x0037 }
            r1.I$0 = r8     // Catch:{ Exception -> 0x0037 }
            r1.I$1 = r7     // Catch:{ Exception -> 0x0037 }
            r1.I$2 = r6     // Catch:{ Exception -> 0x0037 }
            r1.label = r4     // Catch:{ Exception -> 0x0037 }
            java.lang.Object r5 = r15.getListOfSettledSessions(r1)     // Catch:{ Exception -> 0x0037 }
            if (r5 != r2) goto L_0x0100
            return r2
        L_0x0100:
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ Exception -> 0x0037 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ Exception -> 0x0037 }
        L_0x0106:
            boolean r15 = r5.hasNext()     // Catch:{ Exception -> 0x0037 }
            if (r15 == 0) goto L_0x012a
            java.lang.Object r15 = r5.next()     // Catch:{ Exception -> 0x0037 }
            r16 = r15
            com.reown.sign.engine.model.EngineDO$Session r16 = (com.reown.sign.engine.model.EngineDO.Session) r16     // Catch:{ Exception -> 0x0037 }
            com.reown.foundation.common.model.Topic r16 = r16.getTopic()     // Catch:{ Exception -> 0x0037 }
            java.lang.String r4 = r16.getValue()     // Catch:{ Exception -> 0x0037 }
            java.lang.String r6 = r0.getTopic()     // Catch:{ Exception -> 0x0037 }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r6)     // Catch:{ Exception -> 0x0037 }
            if (r4 == 0) goto L_0x0127
            goto L_0x012b
        L_0x0127:
            r4 = 2
            r6 = 0
            goto L_0x0106
        L_0x012a:
            r15 = r3
        L_0x012b:
            if (r15 == 0) goto L_0x0136
            r0 = 1
            goto L_0x0137
        L_0x012f:
            com.reown.foundation.util.Logger r4 = r13.logger     // Catch:{ Exception -> 0x0048 }
            r4.error((java.lang.Throwable) r0)     // Catch:{ Exception -> 0x0048 }
        L_0x0136:
            r0 = 0
        L_0x0137:
            if (r0 == 0) goto L_0x013c
            r11.add(r9)     // Catch:{ Exception -> 0x0048 }
        L_0x013c:
            r4 = 2
            r5 = 1
            r6 = 0
            goto L_0x00ca
        L_0x0140:
            java.util.List r11 = (java.util.List) r11     // Catch:{ Exception -> 0x0048 }
            java.lang.Iterable r11 = (java.lang.Iterable) r11     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.domain.SignEngine r0 = r1.this$0     // Catch:{ Exception -> 0x0048 }
            java.util.Iterator r2 = r11.iterator()     // Catch:{ Exception -> 0x0048 }
        L_0x014a:
            boolean r4 = r2.hasNext()     // Catch:{ Exception -> 0x0048 }
            if (r4 == 0) goto L_0x0170
            java.lang.Object r4 = r2.next()     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.model.EngineDO$SessionRequest r4 = (com.reown.sign.engine.model.EngineDO.SessionRequest) r4     // Catch:{ Exception -> 0x0048 }
            kotlinx.coroutines.CoroutineScope r5 = com.reown.android.internal.common.WalletConnectScopeKt.getScope()     // Catch:{ Exception -> 0x0048 }
            com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1$4$1 r8 = new com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1$4$1     // Catch:{ Exception -> 0x0048 }
            r8.<init>(r0, r4, r3)     // Catch:{ Exception -> 0x0048 }
            r6 = 0
            r7 = 0
            r9 = 3
            r10 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0048 }
            goto L_0x014a
        L_0x0167:
            com.reown.sign.engine.domain.SignEngine r1 = r1.this$0
            com.reown.foundation.util.Logger r1 = r1.logger
            r1.error((java.lang.Throwable) r0)
        L_0x0170:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.sign.engine.domain.SignEngine$propagatePendingSessionRequestsQueue$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SignEngine$propagatePendingSessionRequestsQueue$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
