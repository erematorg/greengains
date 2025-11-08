package com.reown.sign.di;

import com.reown.sign.common.model.vo.clientsync.session.SignRpc;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionEventVO;
import com.reown.sign.common.model.vo.clientsync.session.payload.SessionRequestVO;
import com.reown.sign.json_rpc.model.JsonRpcMethod;
import com.reown.utils.UtilFunctionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.module.Module;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0000¨\u0006\u0002"}, d2 = {"signJsonRpcModule", "Lorg/koin/core/module/Module;", "sign_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class SignJsonRpcModuleKt {
    /* access modifiers changed from: private */
    public static final Unit signJsonRpcModule$lambda$0(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Class<SignRpc.SessionPropose> cls = SignRpc.SessionPropose.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls));
        Class<SignRpc.SessionPing> cls2 = SignRpc.SessionPing.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls2));
        Class<SignRpc.SessionEvent> cls3 = SignRpc.SessionEvent.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls3));
        Class<SignRpc.SessionUpdate> cls4 = SignRpc.SessionUpdate.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls4));
        Class<SignRpc.SessionRequest> cls5 = SignRpc.SessionRequest.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls5));
        Class<SignRpc.SessionDelete> cls6 = SignRpc.SessionDelete.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls6));
        Class<SignRpc.SessionSettle> cls7 = SignRpc.SessionSettle.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls7));
        Class<SignRpc.SessionExtend> cls8 = SignRpc.SessionExtend.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls8));
        Class<SignRpc.SessionAuthenticate> cls9 = SignRpc.SessionAuthenticate.class;
        UtilFunctionsKt.addSerializerEntry(module, Reflection.getOrCreateKotlinClass(cls9));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_PROPOSE, Reflection.getOrCreateKotlinClass(cls));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_SETTLE, Reflection.getOrCreateKotlinClass(cls7));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_REQUEST, Reflection.getOrCreateKotlinClass(cls5));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_DELETE, Reflection.getOrCreateKotlinClass(cls6));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_PING, Reflection.getOrCreateKotlinClass(cls2));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_EVENT, Reflection.getOrCreateKotlinClass(cls3));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_UPDATE, Reflection.getOrCreateKotlinClass(cls4));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_EXTEND, Reflection.getOrCreateKotlinClass(cls8));
        UtilFunctionsKt.addDeserializerEntry(module, JsonRpcMethod.WC_SESSION_AUTHENTICATE, Reflection.getOrCreateKotlinClass(cls9));
        UtilFunctionsKt.addJsonAdapter(module, SessionEventVO.class, SignJsonRpcModuleKt$signJsonRpcModule$1$1.INSTANCE);
        UtilFunctionsKt.addJsonAdapter(module, SessionRequestVO.class, SignJsonRpcModuleKt$signJsonRpcModule$1$2.INSTANCE);
        return Unit.INSTANCE;
    }
}
