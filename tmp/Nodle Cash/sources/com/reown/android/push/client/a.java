package com.reown.android.push.client;

import com.reown.android.push.notifications.PushMessagingService;
import com.reown.sign.client.SignInterface;
import com.reown.sign.engine.use_case.calls.ApproveSessionUseCaseInterface;
import com.reown.sign.engine.use_case.requests.OnSessionProposalUseCase;
import com.reown.walletkit.client.WalletKit;
import io.nodle.cash.ui.feature.chat.ConversationDetailScreenKt;
import io.nodle.cash.ui.feature.qrcode.QrCodeScannerFragment;
import io.nodle.cash.ui.feature.transfer.legacy.view.PkInputView;
import io.nodle.cash.ui.feature.transfer.view.TokenInputView;
import io.nodle.cash.ui.feature.transfer.view.TransferAmountView;
import io.nodle.cash.ui.feature.walletconnect.WalletConnectManager;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.serialization.json.JsonElementSerializer;
import org.koin.viewmodel.BundleExtKt;
import uniffi.xmtpv3.UniffiLib;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7356a;

    public /* synthetic */ a(int i3) {
        this.f7356a = i3;
    }

    public final Object invoke() {
        switch (this.f7356a) {
            case 0:
                return PushClient.clientId_delegate$lambda$1();
            case 1:
                return PushClient.projectId_delegate$lambda$2();
            case 2:
                return PushClient.pushMessagesRepository_delegate$lambda$3();
            case 3:
                return PushMessagingService.decryptMessageUseCases_delegate$lambda$0();
            case 4:
                return PushMessagingService.pushMessagesRepository_delegate$lambda$1();
            case 5:
                return SignInterface.initialize$lambda$0();
            case 6:
                return ApproveSessionUseCaseInterface.approve$lambda$0();
            case 7:
                return Boolean.valueOf(OnSessionProposalUseCase.isAuthenticateEnabled_delegate$lambda$0());
            case 8:
                return WalletKit.initialize$lambda$0();
            case 9:
                return ConversationDetailScreenKt.ConversationDetailScreen$lambda$77$lambda$76$lambda$73$lambda$72$lambda$62$lambda$57$lambda$56();
            case 10:
                return QrCodeScannerFragment.qrScanner_delegate$lambda$0();
            case 11:
                return PkInputView.onDoneClickListener$lambda$0();
            case 12:
                return TokenInputView.onDoneClickListener$lambda$0();
            case 13:
                return TransferAmountView.onNextClicked$lambda$0();
            case 14:
                return TransferAmountView.onBackClicked$lambda$2();
            case 15:
                return TransferAmountView.onPickTokenClicked$lambda$4();
            case 16:
                return TransferAmountView.onMaxClicked$lambda$6();
            case 17:
                return WalletConnectManager.init$lambda$1();
            case 18:
                return DebugProbesImpl.startWeakRefCleanerThread$lambda$2();
            case 19:
                return JsonElementSerializer.descriptor$lambda$5$lambda$0();
            case 20:
                return JsonElementSerializer.descriptor$lambda$5$lambda$1();
            case 21:
                return JsonElementSerializer.descriptor$lambda$5$lambda$2();
            case 22:
                return JsonElementSerializer.descriptor$lambda$5$lambda$3();
            case 23:
                return JsonElementSerializer.descriptor$lambda$5$lambda$4();
            case 24:
                return BundleExtKt.emptyState$lambda$3();
            case 25:
                return UniffiLib.Companion.INSTANCE_delegate$lambda$1();
            default:
                return UniffiLib.Companion.CLEANER_delegate$lambda$2();
        }
    }
}
