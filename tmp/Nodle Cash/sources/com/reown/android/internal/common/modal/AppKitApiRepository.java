package com.reown.android.internal.common.modal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.media.session.a;
import com.reown.android.internal.common.modal.data.model.Wallet;
import com.reown.android.internal.common.modal.data.model.WalletAppData;
import com.reown.android.internal.common.modal.data.network.AppKitService;
import com.reown.android.internal.common.modal.data.network.model.WalletDTO;
import com.reown.android.internal.common.modal.data.network.model.WalletDataDTO;
import com.reown.android.utils.PackageManagerExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ$\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000b2\u0006\u0010\u000e\u001a\u00020\u0005H@¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u0005H@¢\u0006\u0004\b\u0013\u0010\u0010JV\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fH@¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\f*\b\u0012\u0004\u0012\u00020\u001f0\fH\u0002J\u0018\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\f*\b\u0012\u0004\u0012\u00020!0\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/reown/android/internal/common/modal/AppKitApiRepository;", "", "context", "Landroid/content/Context;", "web3ModalApiUrl", "", "appKitService", "Lcom/reown/android/internal/common/modal/data/network/AppKitService;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lcom/reown/android/internal/common/modal/data/network/AppKitService;)V", "getAndroidWalletsData", "Lkotlin/Result;", "", "Lcom/reown/android/internal/common/modal/data/model/WalletAppData;", "sdkType", "getAndroidWalletsData-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAnalyticsConfig", "", "getAnalyticsConfig-gIAlu-s", "getWallets", "Lcom/reown/android/internal/common/modal/data/model/WalletListing;", "page", "", "search", "excludeIds", "includeWallets", "getWallets-hUnOzRk", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toWallets", "Lcom/reown/android/internal/common/modal/data/model/Wallet;", "Lcom/reown/android/internal/common/modal/data/network/model/WalletDTO;", "toWalletsAppData", "Lcom/reown/android/internal/common/modal/data/network/model/WalletDataDTO;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAppKitApiRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AppKitApiRepository.kt\ncom/reown/android/internal/common/modal/AppKitApiRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,77:1\n774#2:78\n865#2,2:79\n1563#2:81\n1634#2,3:82\n1563#2:85\n1634#2,3:86\n*S KotlinDebug\n*F\n+ 1 AppKitApiRepository.kt\ncom/reown/android/internal/common/modal/AppKitApiRepository\n*L\n21#1:78\n21#1:79,2\n53#1:81\n53#1:82,3\n69#1:85\n69#1:86,3\n*E\n"})
public final class AppKitApiRepository {
    @NotNull
    private final AppKitService appKitService;
    @NotNull
    private final Context context;
    @NotNull
    private final String web3ModalApiUrl;

    public AppKitApiRepository(@NotNull Context context2, @NotNull String str, @NotNull AppKitService appKitService2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(str, "web3ModalApiUrl");
        Intrinsics.checkNotNullParameter(appKitService2, "appKitService");
        this.context = context2;
        this.web3ModalApiUrl = str;
        this.appKitService = appKitService2;
    }

    /* renamed from: getAnalyticsConfig-gIAlu-s$default  reason: not valid java name */
    public static /* synthetic */ Object m8743getAnalyticsConfiggIAlus$default(AppKitApiRepository appKitApiRepository, String str, Continuation continuation, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = "w3m";
        }
        return appKitApiRepository.m8745getAnalyticsConfiggIAlus(str, continuation);
    }

    /* renamed from: getWallets-hUnOzRk$default  reason: not valid java name */
    public static /* synthetic */ Object m8744getWalletshUnOzRk$default(AppKitApiRepository appKitApiRepository, String str, int i3, String str2, List list, List list2, Continuation continuation, int i4, Object obj) {
        return appKitApiRepository.m8747getWalletshUnOzRk(str, i3, (i4 & 4) != 0 ? null : str2, (i4 & 8) != 0 ? null : list, (i4 & 16) != 0 ? null : list2, continuation);
    }

    private final List<Wallet> toWallets(List<WalletDTO> list) {
        Iterable<WalletDTO> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (WalletDTO walletDTO : iterable) {
            Wallet wallet = r4;
            Wallet wallet2 = new Wallet(walletDTO.getId(), walletDTO.getName(), walletDTO.getHomePage(), a.n(this.web3ModalApiUrl, "getWalletImage/", walletDTO.getImageId()), walletDTO.getOrder(), walletDTO.getMobileLink(), walletDTO.getPlayStore(), walletDTO.getWebappLink(), walletDTO.getLinkMode(), false, 512, (DefaultConstructorMarker) null);
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            Wallet wallet3 = wallet;
            wallet3.setWalletInstalled(PackageManagerExtensionsKt.isWalletInstalled(packageManager, wallet.getAppPackage()));
            arrayList.add(wallet3);
        }
        return arrayList;
    }

    private final List<WalletAppData> toWalletsAppData(List<WalletDataDTO> list) {
        Iterable<WalletDataDTO> iterable = list;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
        for (WalletDataDTO walletDataDTO : iterable) {
            String id = walletDataDTO.getId();
            String appId = walletDataDTO.getAppId();
            PackageManager packageManager = this.context.getPackageManager();
            Intrinsics.checkNotNullExpressionValue(packageManager, "getPackageManager(...)");
            arrayList.add(new WalletAppData(id, appId, PackageManagerExtensionsKt.isWalletInstalled(packageManager, walletDataDTO.getAppId())));
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0071 A[SYNTHETIC, Splitter:B:24:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getAnalyticsConfig-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8745getAnalyticsConfiggIAlus(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.Boolean>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.reown.android.internal.common.modal.AppKitApiRepository$getAnalyticsConfig$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.reown.android.internal.common.modal.AppKitApiRepository$getAnalyticsConfig$1 r0 = (com.reown.android.internal.common.modal.AppKitApiRepository$getAnalyticsConfig$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.modal.AppKitApiRepository$getAnalyticsConfig$1 r0 = new com.reown.android.internal.common.modal.AppKitApiRepository$getAnalyticsConfig$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$1
            com.reown.android.internal.common.modal.AppKitApiRepository r4 = (com.reown.android.internal.common.modal.AppKitApiRepository) r4
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x005a
        L_0x0031:
            r4 = move-exception
            goto L_0x0061
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0031 }
            com.reown.android.internal.common.modal.data.network.AppKitService r6 = r4.appKitService     // Catch:{ all -> 0x0031 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0031 }
            r0.L$0 = r2     // Catch:{ all -> 0x0031 }
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0031 }
            r0.L$1 = r4     // Catch:{ all -> 0x0031 }
            r4 = 0
            r0.I$0 = r4     // Catch:{ all -> 0x0031 }
            r0.label = r3     // Catch:{ all -> 0x0031 }
            java.lang.Object r6 = r6.getAnalyticsConfig(r5, r0)     // Catch:{ all -> 0x0031 }
            if (r6 != r1) goto L_0x005a
            return r1
        L_0x005a:
            retrofit2.Response r6 = (retrofit2.Response) r6     // Catch:{ all -> 0x0031 }
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x006b
        L_0x0061:
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x006b:
            boolean r5 = kotlin.Result.m8986isSuccessimpl(r4)
            if (r5 == 0) goto L_0x0090
            retrofit2.Response r4 = (retrofit2.Response) r4     // Catch:{ all -> 0x0089 }
            java.lang.Object r4 = r4.body()     // Catch:{ all -> 0x0089 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0089 }
            com.reown.android.internal.common.modal.data.network.model.EnableAnalyticsDTO r4 = (com.reown.android.internal.common.modal.data.network.model.EnableAnalyticsDTO) r4     // Catch:{ all -> 0x0089 }
            boolean r4 = r4.isAnalyticsEnabled()     // Catch:{ all -> 0x0089 }
            java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)     // Catch:{ all -> 0x0089 }
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)     // Catch:{ all -> 0x0089 }
            goto L_0x0094
        L_0x0089:
            r4 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
        L_0x0090:
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
        L_0x0094:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.AppKitApiRepository.m8745getAnalyticsConfiggIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0071 A[SYNTHETIC, Splitter:B:24:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getAndroidWalletsData-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8746getAndroidWalletsDatagIAlus(@org.jetbrains.annotations.NotNull java.lang.String r5, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<? extends java.util.List<com.reown.android.internal.common.modal.data.model.WalletAppData>>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.reown.android.internal.common.modal.AppKitApiRepository$getAndroidWalletsData$1
            if (r0 == 0) goto L_0x0013
            r0 = r6
            com.reown.android.internal.common.modal.AppKitApiRepository$getAndroidWalletsData$1 r0 = (com.reown.android.internal.common.modal.AppKitApiRepository$getAndroidWalletsData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.modal.AppKitApiRepository$getAndroidWalletsData$1 r0 = new com.reown.android.internal.common.modal.AppKitApiRepository$getAndroidWalletsData$1
            r0.<init>(r4, r6)
        L_0x0018:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r5 = r0.L$1
            com.reown.android.internal.common.modal.AppKitApiRepository r5 = (com.reown.android.internal.common.modal.AppKitApiRepository) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x005a
        L_0x0031:
            r5 = move-exception
            goto L_0x0061
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x0031 }
            com.reown.android.internal.common.modal.data.network.AppKitService r6 = r4.appKitService     // Catch:{ all -> 0x0031 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r5)     // Catch:{ all -> 0x0031 }
            r0.L$0 = r2     // Catch:{ all -> 0x0031 }
            java.lang.Object r2 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r4)     // Catch:{ all -> 0x0031 }
            r0.L$1 = r2     // Catch:{ all -> 0x0031 }
            r2 = 0
            r0.I$0 = r2     // Catch:{ all -> 0x0031 }
            r0.label = r3     // Catch:{ all -> 0x0031 }
            java.lang.Object r6 = r6.getAndroidData(r5, r0)     // Catch:{ all -> 0x0031 }
            if (r6 != r1) goto L_0x005a
            return r1
        L_0x005a:
            retrofit2.Response r6 = (retrofit2.Response) r6     // Catch:{ all -> 0x0031 }
            java.lang.Object r5 = kotlin.Result.m8979constructorimpl(r6)     // Catch:{ all -> 0x0031 }
            goto L_0x006b
        L_0x0061:
            kotlin.Result$Companion r6 = kotlin.Result.Companion
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            java.lang.Object r5 = kotlin.Result.m8979constructorimpl(r5)
        L_0x006b:
            boolean r6 = kotlin.Result.m8986isSuccessimpl(r5)
            if (r6 == 0) goto L_0x00b8
            retrofit2.Response r5 = (retrofit2.Response) r5     // Catch:{ all -> 0x00a6 }
            java.lang.Object r5 = r5.body()     // Catch:{ all -> 0x00a6 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)     // Catch:{ all -> 0x00a6 }
            com.reown.android.internal.common.modal.data.network.model.GetAndroidDataDTO r5 = (com.reown.android.internal.common.modal.data.network.model.GetAndroidDataDTO) r5     // Catch:{ all -> 0x00a6 }
            java.util.List r5 = r5.getData()     // Catch:{ all -> 0x00a6 }
            java.util.List r4 = r4.toWalletsAppData(r5)     // Catch:{ all -> 0x00a6 }
            java.lang.Iterable r4 = (java.lang.Iterable) r4     // Catch:{ all -> 0x00a6 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x00a6 }
            r5.<init>()     // Catch:{ all -> 0x00a6 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00a6 }
        L_0x008f:
            boolean r6 = r4.hasNext()     // Catch:{ all -> 0x00a6 }
            if (r6 == 0) goto L_0x00a8
            java.lang.Object r6 = r4.next()     // Catch:{ all -> 0x00a6 }
            r0 = r6
            com.reown.android.internal.common.modal.data.model.WalletAppData r0 = (com.reown.android.internal.common.modal.data.model.WalletAppData) r0     // Catch:{ all -> 0x00a6 }
            boolean r0 = r0.isInstalled()     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x008f
            r5.add(r6)     // Catch:{ all -> 0x00a6 }
            goto L_0x008f
        L_0x00a6:
            r4 = move-exception
            goto L_0x00ad
        L_0x00a8:
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r5)     // Catch:{ all -> 0x00a6 }
            goto L_0x00bc
        L_0x00ad:
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r4)
            goto L_0x00bc
        L_0x00b8:
            java.lang.Object r4 = kotlin.Result.m8979constructorimpl(r5)
        L_0x00bc:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.AppKitApiRepository.m8746getAndroidWalletsDatagIAlus(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d8 A[SYNTHETIC, Splitter:B:40:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0029  */
    @org.jetbrains.annotations.Nullable
    /* renamed from: getWallets-hUnOzRk  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object m8747getWalletshUnOzRk(@org.jetbrains.annotations.NotNull java.lang.String r19, int r20, @org.jetbrains.annotations.Nullable java.lang.String r21, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r22, @org.jetbrains.annotations.Nullable java.util.List<java.lang.String> r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<com.reown.android.internal.common.modal.data.model.WalletListing>> r24) {
        /*
            r18 = this;
            r1 = r18
            r0 = r24
            boolean r2 = r0 instanceof com.reown.android.internal.common.modal.AppKitApiRepository$getWallets$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            com.reown.android.internal.common.modal.AppKitApiRepository$getWallets$1 r2 = (com.reown.android.internal.common.modal.AppKitApiRepository$getWallets$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0018
            int r3 = r3 - r4
            r2.label = r3
        L_0x0016:
            r11 = r2
            goto L_0x001e
        L_0x0018:
            com.reown.android.internal.common.modal.AppKitApiRepository$getWallets$1 r2 = new com.reown.android.internal.common.modal.AppKitApiRepository$getWallets$1
            r2.<init>(r1, r0)
            goto L_0x0016
        L_0x001e:
            java.lang.Object r0 = r11.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r11.label
            r4 = 1
            if (r3 == 0) goto L_0x0053
            if (r3 != r4) goto L_0x004b
            int r2 = r11.I$0
            java.lang.Object r3 = r11.L$4
            com.reown.android.internal.common.modal.AppKitApiRepository r3 = (com.reown.android.internal.common.modal.AppKitApiRepository) r3
            java.lang.Object r3 = r11.L$3
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r11.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r11.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r11.L$0
            java.lang.String r3 = (java.lang.String) r3
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0047 }
            r14 = r2
            goto L_0x00c0
        L_0x0047:
            r0 = move-exception
            r14 = r2
            goto L_0x00c8
        L_0x004b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x0071 }
            com.reown.android.internal.common.modal.data.network.AppKitService r3 = r1.appKitService     // Catch:{ all -> 0x0071 }
            r0 = 0
            if (r22 == 0) goto L_0x0073
            r5 = r22
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ all -> 0x0071 }
            java.lang.String r6 = ","
            r10 = 62
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r5 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r5, r6, r7, r8, 0, (java.lang.CharSequence) null, r9, r10, (java.lang.Object) null)     // Catch:{ all -> 0x0071 }
            r7 = r5
            goto L_0x0074
        L_0x006e:
            r14 = r20
            goto L_0x00c8
        L_0x0071:
            r0 = move-exception
            goto L_0x006e
        L_0x0073:
            r7 = r0
        L_0x0074:
            if (r23 == 0) goto L_0x0086
            r12 = r23
            java.lang.Iterable r12 = (java.lang.Iterable) r12     // Catch:{ all -> 0x0071 }
            java.lang.String r13 = ","
            r17 = 62
            r14 = 0
            r15 = 0
            r16 = 0
            java.lang.String r0 = kotlin.collections.CollectionsKt___CollectionsKt.joinToString$default(r12, r13, r14, r15, 0, (java.lang.CharSequence) null, r16, r17, (java.lang.Object) null)     // Catch:{ all -> 0x0071 }
        L_0x0086:
            r8 = r0
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r19)     // Catch:{ all -> 0x0071 }
            r11.L$0 = r0     // Catch:{ all -> 0x0071 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r21)     // Catch:{ all -> 0x0071 }
            r11.L$1 = r0     // Catch:{ all -> 0x0071 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r22)     // Catch:{ all -> 0x0071 }
            r11.L$2 = r0     // Catch:{ all -> 0x0071 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r23)     // Catch:{ all -> 0x0071 }
            r11.L$3 = r0     // Catch:{ all -> 0x0071 }
            java.lang.Object r0 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r18)     // Catch:{ all -> 0x0071 }
            r11.L$4 = r0     // Catch:{ all -> 0x0071 }
            r14 = r20
            r11.I$0 = r14     // Catch:{ all -> 0x00c7 }
            r0 = 0
            r11.I$1 = r0     // Catch:{ all -> 0x00c7 }
            r11.label = r4     // Catch:{ all -> 0x00c7 }
            r12 = 96
            r13 = 0
            r9 = 0
            r10 = 0
            r4 = r19
            r5 = r20
            r6 = r21
            java.lang.Object r0 = com.reown.android.internal.common.modal.data.network.AppKitService.getWallets$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x00c7 }
            if (r0 != r2) goto L_0x00c0
            return r2
        L_0x00c0:
            retrofit2.Response r0 = (retrofit2.Response) r0     // Catch:{ all -> 0x00c7 }
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)     // Catch:{ all -> 0x00c7 }
            goto L_0x00d2
        L_0x00c7:
            r0 = move-exception
        L_0x00c8:
            kotlin.Result$Companion r2 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)
        L_0x00d2:
            boolean r2 = kotlin.Result.m8986isSuccessimpl(r0)
            if (r2 == 0) goto L_0x0100
            retrofit2.Response r0 = (retrofit2.Response) r0     // Catch:{ all -> 0x00f9 }
            java.lang.Object r0 = r0.body()     // Catch:{ all -> 0x00f9 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x00f9 }
            com.reown.android.internal.common.modal.data.network.model.GetWalletsDTO r0 = (com.reown.android.internal.common.modal.data.network.model.GetWalletsDTO) r0     // Catch:{ all -> 0x00f9 }
            com.reown.android.internal.common.modal.data.model.WalletListing r2 = new com.reown.android.internal.common.modal.data.model.WalletListing     // Catch:{ all -> 0x00f9 }
            int r3 = r0.getCount()     // Catch:{ all -> 0x00f9 }
            java.util.List r0 = r0.getData()     // Catch:{ all -> 0x00f9 }
            java.util.List r0 = r1.toWallets(r0)     // Catch:{ all -> 0x00f9 }
            r2.<init>(r14, r3, r0)     // Catch:{ all -> 0x00f9 }
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r2)     // Catch:{ all -> 0x00f9 }
            goto L_0x0104
        L_0x00f9:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
        L_0x0100:
            java.lang.Object r0 = kotlin.Result.m8979constructorimpl(r0)
        L_0x0104:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.modal.AppKitApiRepository.m8747getWalletshUnOzRk(java.lang.String, int, java.lang.String, java.util.List, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
