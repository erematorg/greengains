package com.reown.android.internal.common.di;

import app.cash.sqldelight.db.SqlCursor;
import com.reown.android.Core;
import com.reown.android.internal.common.signing.cacao.CacaoKt;
import com.reown.android.pairing.client.PairingInterface;
import com.reown.android.relay.RelayConnectionInterface;
import com.reown.android.sdk.storage.data.dao.EventQueries;
import com.reown.android.sdk.storage.data.dao.IdentitiesQueries;
import com.reown.android.sdk.storage.data.dao.JsonRpcHistoryQueries;
import com.reown.android.sdk.storage.data.dao.MetaDataQueries;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import org.koin.core.module.Module;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7303a;

    public /* synthetic */ b(int i3) {
        this.f7303a = i3;
    }

    public final Object invoke(Object obj) {
        switch (this.f7303a) {
            case 0:
                return AppKitModuleKt.appKitModule$lambda$11((Module) obj);
            case 1:
                return BaseStorageModuleKt$baseStorageModule$1$2$1.encode$lambda$0((Map.Entry) obj);
            case 2:
                return CoreCommonModuleKt.coreCommonModule$lambda$5((Module) obj);
            case 3:
                return CoreJsonRpcModuleKt.coreJsonRpcModule$lambda$3((Module) obj);
            case 4:
                return ExplorerModuleKt.explorerModule$lambda$6((Module) obj);
            case 5:
                return PushModuleKt.pushModule$lambda$4((Module) obj);
            case 6:
                return VerifyModuleKt.verifyModule$lambda$7((Module) obj);
            case 7:
                return CacaoKt.getActionsString$lambda$4$lambda$3((String) obj);
            case 8:
                return PairingInterface.pair$lambda$2((Core.Params.Pair) obj);
            case 9:
                return PairingInterface.pair$lambda$3((Core.Model.Error) obj);
            case 10:
                return PairingInterface.create$lambda$0((Core.Model.Error) obj);
            case 11:
                return PairingInterface.create$lambda$1((Core.Model.Error) obj);
            case 12:
                return PairingInterface.disconnect$lambda$4((Core.Model.Error) obj);
            case 13:
                return PairingInterface.disconnect$lambda$5((Core.Model.Error) obj);
            case 14:
                return RelayConnectionInterface.disconnect$lambda$1((Core.Model.Error) obj);
            case 15:
                return RelayConnectionInterface.connect$lambda$0((Core.Model.Error) obj);
            case 16:
                return EventQueries.insertOrAbort$lambda$5((Function1) obj);
            case 17:
                return EventQueries.deleteAllTelemetry$lambda$9((Function1) obj);
            case 18:
                return EventQueries.deleteByIds$lambda$8((Function1) obj);
            case 19:
                return IdentitiesQueries.getAccountIdByIdentity$lambda$0((SqlCursor) obj);
            case 20:
                return IdentitiesQueries.getCacaoPayloadByIdentity$lambda$2((String) obj);
            case 21:
                return IdentitiesQueries.removeIdentity$lambda$6((Function1) obj);
            case 22:
                return IdentitiesQueries.insertOrAbortIdentity$lambda$4((Function1) obj);
            case 23:
                return JsonRpcHistoryQueries.updateJsonRpcHistory$lambda$18((Function1) obj);
            case 24:
                return Long.valueOf(JsonRpcHistoryQueries.selectLastInsertedRowId$lambda$13((SqlCursor) obj));
            case 25:
                return JsonRpcHistoryQueries.insertOrAbortJsonRpcHistory$lambda$16((Function1) obj);
            case 26:
                return Boolean.valueOf(JsonRpcHistoryQueries.doesJsonRpcNotExist$lambda$12((SqlCursor) obj));
            case 27:
                return JsonRpcHistoryQueries.deleteJsonRpcHistory$lambda$20((Function1) obj);
            case 28:
                return JsonRpcHistoryQueries.deleteJsonRpcHistoryByRequestId$lambda$22((Function1) obj);
            default:
                return MetaDataQueries.updateOrAbortMetaDataTopic$lambda$8((Function1) obj);
        }
    }
}
