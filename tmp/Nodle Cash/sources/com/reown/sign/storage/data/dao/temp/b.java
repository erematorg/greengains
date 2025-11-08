package com.reown.sign.storage.data.dao.temp;

import androidx.sqlite.SQLiteConnection;
import com.google.android.gms.location.LocationSettingsResponse;
import com.reown.foundation.common.model.Topic;
import com.reown.sign.storage.sequence.SessionStorageRepository;
import com.reown.walletkit.client.Wallet;
import com.reown.walletkit.client.WalletKit;
import io.nodle.cash.core.android.permission.PermissionManager;
import io.nodle.cash.data.local.dao.AccountsDao_Impl;
import io.nodle.cash.data.local.dao.ConversationDao_Impl;
import io.nodle.cash.data.local.dao.DappDao_Impl;
import io.nodle.cash.data.local.dao.DappMessageDao_Impl;
import io.nodle.cash.data.local.dao.EthereumNftDao_Impl;
import io.nodle.cash.data.local.dao.MessageDao_Impl;
import io.nodle.cash.data.local.dao.MissionsDao_Impl;
import io.nodle.cash.data.local.dao.NotificationDao_Impl;
import io.nodle.cash.data.local.dao.PolkadotNftDao_Impl;
import io.nodle.cash.data.local.dao.TokensDao_Impl;
import io.nodle.cash.data.local.dao.TokensPricesDao_Impl;
import kotlin.jvm.functions.Function1;

public final /* synthetic */ class b implements Function1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7631a;

    public /* synthetic */ b(int i3) {
        this.f7631a = i3;
    }

    public final Object invoke(Object obj) {
        switch (this.f7631a) {
            case 0:
                return TempNamespaceDaoQueries.deleteTempNamespacesByTopic$lambda$12((Function1) obj);
            case 1:
                return TempNamespaceDaoQueries.deleteTempNamespacesByRequestId$lambda$10((Function1) obj);
            case 2:
                return SessionStorageRepository.onSessionExpired$lambda$0((Topic) obj);
            case 3:
                return WalletKit.approveSession$lambda$7((Wallet.Params.SessionApprove) obj);
            case 4:
                return WalletKit.extendSession$lambda$22((Wallet.Params.SessionExtend) obj);
            case 5:
                return WalletKit.respondSessionRequest$lambda$25((Wallet.Params.SessionRequestResponse) obj);
            case 6:
                return WalletKit.pair$lambda$3((Wallet.Params.Pair) obj);
            case 7:
                return WalletKit.rejectSessionAuthenticate$lambda$16((Wallet.Params.RejectSessionAuthenticate) obj);
            case 8:
                return WalletKit.approveSessionAuthenticate$lambda$13((Wallet.Params.ApproveSessionAuthenticate) obj);
            case 9:
                return WalletKit.disconnectSession$lambda$31((Wallet.Params.SessionDisconnect) obj);
            case 10:
                return WalletKit.pair$lambda$4((Wallet.Model.Error) obj);
            case 11:
                return WalletKit.rejectSession$lambda$10((Wallet.Params.SessionReject) obj);
            case 12:
                return WalletKit.emitSessionEvent$lambda$28((Wallet.Params.SessionEmit) obj);
            case 13:
                return WalletKit.updateSession$lambda$19((Wallet.Params.SessionUpdate) obj);
            case 14:
                return PermissionManager.enableLocation$lambda$9$lambda$6((LocationSettingsResponse) obj);
            case 15:
                return AccountsDao_Impl.lambda$getAccounts$1((SQLiteConnection) obj);
            case 16:
                return ConversationDao_Impl.lambda$deleteAll$4((SQLiteConnection) obj);
            case 17:
                return DappDao_Impl.lambda$getAll$1((SQLiteConnection) obj);
            case 18:
                return DappDao_Impl.lambda$deleteAll$2((SQLiteConnection) obj);
            case 19:
                return DappMessageDao_Impl.lambda$deleteAll$4((SQLiteConnection) obj);
            case 20:
                return EthereumNftDao_Impl.lambda$getPendingNfts$3((SQLiteConnection) obj);
            case 21:
                return EthereumNftDao_Impl.lambda$getNfts$2((SQLiteConnection) obj);
            case 22:
                return MessageDao_Impl.lambda$deleteAll$5((SQLiteConnection) obj);
            case 23:
                return MissionsDao_Impl.lambda$getAll$3((SQLiteConnection) obj);
            case 24:
                return NotificationDao_Impl.lambda$readUnseenCount$2((SQLiteConnection) obj);
            case 25:
                return NotificationDao_Impl.lambda$readAll$1((SQLiteConnection) obj);
            case 26:
                return PolkadotNftDao_Impl.lambda$getPendingNfts$3((SQLiteConnection) obj);
            case 27:
                return PolkadotNftDao_Impl.lambda$getNfts$2((SQLiteConnection) obj);
            case 28:
                return TokensDao_Impl.lambda$getAllTokens$2((SQLiteConnection) obj);
            default:
                return TokensPricesDao_Impl.lambda$getTokenPrices$2((SQLiteConnection) obj);
        }
    }
}
