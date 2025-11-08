package com.reown.android.internal.common.modal.domain.usecase;

import android.content.Context;
import android.content.pm.PackageManager;
import com.reown.android.internal.common.modal.data.model.Wallet;
import com.reown.android.utils.PackageManagerExtensionsKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HB¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/reown/android/internal/common/modal/domain/usecase/GetSampleWalletsUseCase;", "Lcom/reown/android/internal/common/modal/domain/usecase/GetSampleWalletsUseCaseInterface;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "invoke", "", "Lcom/reown/android/internal/common/modal/data/model/Wallet;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetSamplesWalletsUseCaseInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetSamplesWalletsUseCaseInterface.kt\ncom/reown/android/internal/common/modal/domain/usecase/GetSampleWalletsUseCase\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,123:1\n216#2,2:124\n126#2:126\n153#2,3:127\n774#3:130\n865#3,2:131\n*S KotlinDebug\n*F\n+ 1 GetSamplesWalletsUseCaseInterface.kt\ncom/reown/android/internal/common/modal/domain/usecase/GetSampleWalletsUseCase\n*L\n24#1:124,2\n29#1:126\n29#1:127,3\n29#1:130\n29#1:131,2\n*E\n"})
public final class GetSampleWalletsUseCase implements GetSampleWalletsUseCaseInterface {
    @NotNull
    private final Context context;

    public GetSampleWalletsUseCase(@NotNull Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    @Nullable
    public Object invoke(@NotNull Continuation<? super List<Wallet>> continuation) {
        Map mapOf = MapsKt.mapOf(TuplesKt.to("com.reown.sample.wallet.debug", GetSamplesWalletsUseCaseInterfaceKt.SampleWalletDebug), TuplesKt.to("com.reown.sample.wallet.internal", GetSamplesWalletsUseCaseInterfaceKt.SampleWalletInternal), TuplesKt.to("com.reown.sample.wallet", GetSamplesWalletsUseCaseInterfaceKt.SampleWalletRelease), TuplesKt.to("com.walletconnect.web3wallet.rnsample.internal", GetSamplesWalletsUseCaseInterfaceKt.RNSampleWalletInternal), TuplesKt.to("com.walletconnect.web3wallet.rnsample", GetSamplesWalletsUseCaseInterfaceKt.RNSampleWallet), TuplesKt.to("com.walletconnect.flutterwallet", GetSamplesWalletsUseCaseInterfaceKt.FLSampleWallet), TuplesKt.to("com.walletconnect.flutterwallet.internal", GetSamplesWalletsUseCaseInterfaceKt.FLSampleWalletInternal));
        for (Map.Entry entry : mapOf.entrySet()) {
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            ((Wallet) entry.getValue()).setWalletInstalled(PackageManagerExtensionsKt.isWalletInstalled(packageManager, (String) entry.getKey()));
        }
        ArrayList arrayList = new ArrayList(mapOf.size());
        for (Map.Entry value : mapOf.entrySet()) {
            arrayList.add((Wallet) value.getValue());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (((Wallet) next).isWalletInstalled()) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }
}
