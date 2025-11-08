package com.google.android.material.datepicker;

import android.widget.EditText;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.firebase.installations.FirebaseInstallations;
import io.nodle.cash.ui.feature.home.shortcut.HomeShortcutsSheetFragment;
import io.nodle.cash.ui.feature.settings.app.AppSettingsFragment;
import java.util.concurrent.ScheduledExecutorService;
import org.apache.commons.lang3.Functions;
import org.apache.commons.lang3.concurrent.TimedSemaphore;
import org.web3j.tx.response.QueuingTransactionReceiptProcessor;
import org.web3j.utils.Async;

public final /* synthetic */ class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6655a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6656b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f6655a = i3;
        this.f6656b = obj;
    }

    public final void run() {
        int i3 = this.f6655a;
        Object obj = this.f6656b;
        switch (i3) {
            case 0:
                ViewUtils.requestFocusAndShowKeyboard((EditText) obj, false);
                return;
            case 1:
                ((MaterialBackOrchestrator) obj).startListeningForBackCallbacksWithPriorityOverlay();
                return;
            case 2:
                ((FirebaseInstallations) obj).lambda$getId$1();
                return;
            case 3:
                HomeShortcutsSheetFragment.closeWithDelay$lambda$9((HomeShortcutsSheetFragment) obj);
                return;
            case 4:
                AppSettingsFragment.startWalletBackup$lambda$1$lambda$0((AppSettingsFragment) obj);
                return;
            case 5:
                Functions.run((Functions.FailableRunnable) obj);
                return;
            case 6:
                ((TimedSemaphore) obj).endOfPeriod();
                return;
            case 7:
                ((QueuingTransactionReceiptProcessor) obj).sendTransactionReceiptRequests();
                return;
            default:
                Async.shutdown((ScheduledExecutorService) obj);
                return;
        }
    }
}
