package com.reown.android.internal.common.modal.domain.usecase;

import androidx.exifinterface.media.ExifInterface;
import com.reown.android.internal.common.modal.data.model.Wallet;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"SampleWalletDebug", "Lcom/reown/android/internal/common/modal/data/model/Wallet;", "SampleWalletInternal", "SampleWalletRelease", "RNSampleWalletInternal", "RNSampleWallet", "FLSampleWallet", "FLSampleWalletInternal", "android_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class GetSamplesWalletsUseCaseInterfaceKt {
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet FLSampleWallet = new Wallet("FLSampleWallet", "FL Sample", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "6", "wcflutterwallet://", (String) null, (String) null, "https://appkit-lab.reown.com/flutter_walletkit", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet FLSampleWalletInternal = new Wallet("FLSampleWalletInternal", "FL Sample Internal", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "7", "wcflutterwallet-internal://", (String) null, (String) null, "https://appkit-lab.reown.com/flutter_walletkit_internal", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet RNSampleWallet = new Wallet("RNSampleWalletInternal", "RN Sample Internal", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "5", "rn-web3wallet://", (String) null, (String) null, "https://appkit-lab.reown.com/rn_walletkit", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet RNSampleWalletInternal = new Wallet("RNSampleWallet", "RN Sample", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "4", "rn-web3wallet://", (String) null, (String) null, "https://appkit-lab.reown.com/rn_walletkit_internal", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet SampleWalletDebug = new Wallet("SampleWalletDebug", "Android Sample Debug", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "1", "kotlin-web3wallet://", (String) null, (String) null, "https://appkit-lab.reown.com/wallet_debug", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet SampleWalletInternal = new Wallet("SampleWalletInternal", "Android Sample Internal", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", "2", "kotlin-web3wallet://", (String) null, (String) null, "https://appkit-lab.reown.com/wallet_internal", true);
    /* access modifiers changed from: private */
    @NotNull
    public static final Wallet SampleWalletRelease = new Wallet("SampleWalletRelease", "Android Sample Release", "https://walletconnect.com", "https://raw.githubusercontent.com/WalletConnect/WalletConnectKotlinV2/develop/sample/wallet/src/main/res/drawable-xxxhdpi/wc_icon.png", ExifInterface.GPS_MEASUREMENT_3D, "kotlin-web3wallet://", (String) null, (String) null, "https://appkit-lab.reown.com/wallet_release", true);
}
