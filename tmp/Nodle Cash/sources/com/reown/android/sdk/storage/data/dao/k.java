package com.reown.android.sdk.storage.data.dao;

import app.cash.sqldelight.db.SqlCursor;
import com.reown.foundation.network.BaseRelayClient;
import com.reown.foundation.network.RelayInterface;
import com.reown.sign.client.Sign;
import com.reown.sign.client.SignInterface;
import com.reown.sign.di.CallsModuleKt;
import com.reown.sign.di.EngineModuleKt;
import com.reown.sign.di.RequestsModuleKt;
import com.reown.sign.di.ResponsesModuleKt;
import com.reown.sign.di.SignJsonRpcModuleKt;
import kotlin.Result;
import kotlin.jvm.functions.Function1;
import org.koin.core.module.Module;

public final /* synthetic */ class k implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7403a;

    public /* synthetic */ k(int i3) {
        this.f7403a = i3;
    }

    public final Object invoke(Object obj) {
        switch (this.f7403a) {
            case 0:
                return MetaDataQueries.insertOrAbortMetaData$lambda$4((Function1) obj);
            case 1:
                return Long.valueOf(MetaDataQueries.getIdByTopicAndType$lambda$2((SqlCursor) obj));
            case 2:
                return MetaDataQueries.deleteMetaDataFromTopic$lambda$10((Function1) obj);
            case 3:
                return MetaDataQueries.updateMetaData$lambda$6((Function1) obj);
            case 4:
                return PairingQueries.updateOrAbortExpiry$lambda$17((Function1) obj);
            case 5:
                return PairingQueries.insertOrAbortPairing$lambda$11((Function1) obj);
            case 6:
                return PairingQueries.hasTopic$lambda$9((SqlCursor) obj);
            case 7:
                return PairingQueries.setRequestReceived$lambda$15((Function1) obj);
            case 8:
                return PairingQueries.deletePairing$lambda$13((Function1) obj);
            case 9:
                return Boolean.valueOf(PushMessageQueries.doesMessagesExistsByRequestId$lambda$2((SqlCursor) obj));
            case 10:
                return PushMessageQueries.upsertMessage$lambda$4((Function1) obj);
            case 11:
                return PushMessageQueries.deleteMessageByTopic$lambda$6((Function1) obj);
            case 12:
                return VerifyContextQueries.deleteVerifyContext$lambda$7((Function1) obj);
            case 13:
                return VerifyContextQueries.insertOrAbortVerifyContext$lambda$5((Function1) obj);
            case 14:
                return VerifyPublicKeyQueries.upsertKey$lambda$3((Function1) obj);
            case 15:
                return BaseRelayClient.connectWithRetry$lambda$20((Throwable) obj);
            case 16:
                return RelayInterface.publish$lambda$0((Result) obj);
            case 17:
                return SignInterface.emit$lambda$7((Sign.Params.Emit) obj);
            case 18:
                return SignInterface.update$lambda$5((Sign.Params.Update) obj);
            case 19:
                return SignInterface.approveSession$lambda$1((Sign.Params.Approve) obj);
            case 20:
                return SignInterface.respond$lambda$4((Sign.Params.Response) obj);
            case 21:
                return SignInterface.request$lambda$3((Sign.Model.SentRequest) obj);
            case 22:
                return SignInterface.rejectSession$lambda$2((Sign.Params.Reject) obj);
            case 23:
                return SignInterface.disconnect$lambda$8((Sign.Params.Disconnect) obj);
            case 24:
                return SignInterface.extend$lambda$6((Sign.Params.Extend) obj);
            case 25:
                return CallsModuleKt.callsModule$lambda$25((Module) obj);
            case 26:
                return EngineModuleKt.engineModule$lambda$12((Module) obj);
            case 27:
                return RequestsModuleKt.requestsModule$lambda$9((Module) obj);
            case 28:
                return ResponsesModuleKt.responsesModule$lambda$5((Module) obj);
            default:
                return SignJsonRpcModuleKt.signJsonRpcModule$lambda$0((Module) obj);
        }
    }
}
