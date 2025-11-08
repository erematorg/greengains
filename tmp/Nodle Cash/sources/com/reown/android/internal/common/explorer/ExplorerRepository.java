package com.reown.android.internal.common.explorer;

import android.net.Uri;
import com.reown.android.internal.common.explorer.data.model.App;
import com.reown.android.internal.common.explorer.data.model.Colors;
import com.reown.android.internal.common.explorer.data.model.DappListings;
import com.reown.android.internal.common.explorer.data.model.Desktop;
import com.reown.android.internal.common.explorer.data.model.ImageUrl;
import com.reown.android.internal.common.explorer.data.model.Injected;
import com.reown.android.internal.common.explorer.data.model.Listing;
import com.reown.android.internal.common.explorer.data.model.Mobile;
import com.reown.android.internal.common.explorer.data.model.NotificationType;
import com.reown.android.internal.common.explorer.data.model.NotifyConfig;
import com.reown.android.internal.common.explorer.data.model.ProjectListing;
import com.reown.android.internal.common.explorer.data.model.SupportedStandard;
import com.reown.android.internal.common.explorer.data.network.ExplorerService;
import com.reown.android.internal.common.explorer.data.network.model.AppDTO;
import com.reown.android.internal.common.explorer.data.network.model.ColorsDTO;
import com.reown.android.internal.common.explorer.data.network.model.DappListingsDTO;
import com.reown.android.internal.common.explorer.data.network.model.DesktopDTO;
import com.reown.android.internal.common.explorer.data.network.model.ImageUrlDTO;
import com.reown.android.internal.common.explorer.data.network.model.InjectedDTO;
import com.reown.android.internal.common.explorer.data.network.model.ListingDTO;
import com.reown.android.internal.common.explorer.data.network.model.MetadataDTO;
import com.reown.android.internal.common.explorer.data.network.model.MobileDTO;
import com.reown.android.internal.common.explorer.data.network.model.NotificationTypeDTO;
import com.reown.android.internal.common.explorer.data.network.model.NotifyConfigDTO;
import com.reown.android.internal.common.explorer.data.network.model.NotifyConfigDataDTO;
import com.reown.android.internal.common.explorer.data.network.model.ProjectDTO;
import com.reown.android.internal.common.explorer.data.network.model.ProjectListingDTO;
import com.reown.android.internal.common.explorer.data.network.model.SupportedStandardDTO;
import com.reown.android.internal.common.model.ProjectId;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nJ.\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H@¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H@¢\u0006\u0002\u0010\u0018J\f\u0010\u0019\u001a\u00020\u0015*\u00020\u001aH\u0002J\f\u0010\u001b\u001a\u00020\u001c*\u00020\u001dH\u0002J\f\u0010\u001e\u001a\u00020\f*\u00020\u001fH\u0002J\f\u0010 \u001a\u00020!*\u00020\"H\u0002J\f\u0010#\u001a\u00020\t*\u00020$H\u0002J\f\u0010%\u001a\u00020&*\u00020'H\u0002J\f\u0010(\u001a\u00020)*\u00020*H\u0002J\f\u0010+\u001a\u00020,*\u00020-H\u0002J\f\u0010.\u001a\u00020/*\u000200H\u0002J\f\u00101\u001a\u000202*\u000203H\u0002J\f\u00104\u001a\u000205*\u000206H\u0002J\f\u00107\u001a\u000208*\u000209H\u0002J\f\u0010:\u001a\u00020;*\u00020<H\u0002J\f\u0010=\u001a\u00020>*\u00020?H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/reown/android/internal/common/explorer/ExplorerRepository;", "", "explorerService", "Lcom/reown/android/internal/common/explorer/data/network/ExplorerService;", "projectId", "Lcom/reown/android/internal/common/model/ProjectId;", "<init>", "(Lcom/reown/android/internal/common/explorer/data/network/ExplorerService;Lcom/reown/android/internal/common/model/ProjectId;)V", "getAllDapps", "Lcom/reown/android/internal/common/explorer/data/model/DappListings;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProjects", "Lcom/reown/android/internal/common/explorer/data/model/ProjectListing;", "page", "", "entries", "isVerified", "", "isFeatured", "(IIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotifyConfig", "Lcom/reown/android/internal/common/explorer/data/model/NotifyConfig;", "appDomain", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toNotifyConfig", "Lcom/reown/android/internal/common/explorer/data/network/model/NotifyConfigDTO;", "toNotificationType", "Lcom/reown/android/internal/common/explorer/data/model/NotificationType;", "Lcom/reown/android/internal/common/explorer/data/network/model/NotificationTypeDTO;", "toProjectListing", "Lcom/reown/android/internal/common/explorer/data/network/model/ProjectListingDTO;", "toProject", "Lcom/reown/android/internal/common/explorer/data/model/Project;", "Lcom/reown/android/internal/common/explorer/data/network/model/ProjectDTO;", "toDappListing", "Lcom/reown/android/internal/common/explorer/data/network/model/DappListingsDTO;", "toListing", "Lcom/reown/android/internal/common/explorer/data/model/Listing;", "Lcom/reown/android/internal/common/explorer/data/network/model/ListingDTO;", "toImageUrl", "Lcom/reown/android/internal/common/explorer/data/model/ImageUrl;", "Lcom/reown/android/internal/common/explorer/data/network/model/ImageUrlDTO;", "toApp", "Lcom/reown/android/internal/common/explorer/data/model/App;", "Lcom/reown/android/internal/common/explorer/data/network/model/AppDTO;", "toInjected", "Lcom/reown/android/internal/common/explorer/data/model/Injected;", "Lcom/reown/android/internal/common/explorer/data/network/model/InjectedDTO;", "toMobile", "Lcom/reown/android/internal/common/explorer/data/model/Mobile;", "Lcom/reown/android/internal/common/explorer/data/network/model/MobileDTO;", "toDesktop", "Lcom/reown/android/internal/common/explorer/data/model/Desktop;", "Lcom/reown/android/internal/common/explorer/data/network/model/DesktopDTO;", "toSupportedStandard", "Lcom/reown/android/internal/common/explorer/data/model/SupportedStandard;", "Lcom/reown/android/internal/common/explorer/data/network/model/SupportedStandardDTO;", "toMetadata", "Lcom/reown/android/internal/common/explorer/data/model/Metadata;", "Lcom/reown/android/internal/common/explorer/data/network/model/MetadataDTO;", "toColors", "Lcom/reown/android/internal/common/explorer/data/model/Colors;", "Lcom/reown/android/internal/common/explorer/data/network/model/ColorsDTO;", "android_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExplorerRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExplorerRepository.kt\ncom/reown/android/internal/common/explorer/ExplorerRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,175:1\n1563#2:176\n1634#2,3:177\n1563#2:180\n1634#2,3:181\n1563#2:185\n1634#2,3:186\n1563#2:190\n1634#2,3:191\n1563#2:194\n1634#2,3:195\n1#3:184\n29#4:189\n*S KotlinDebug\n*F\n+ 1 ExplorerRepository.kt\ncom/reown/android/internal/common/explorer/ExplorerRepository\n*L\n81#1:176\n81#1:177,3\n97#1:180\n97#1:181,3\n115#1:185\n115#1:186,3\n131#1:190\n131#1:191,3\n134#1:194\n134#1:195,3\n123#1:189\n*E\n"})
public final class ExplorerRepository {
    @NotNull
    private final ExplorerService explorerService;
    @NotNull
    private final ProjectId projectId;

    public ExplorerRepository(@NotNull ExplorerService explorerService2, @NotNull ProjectId projectId2) {
        Intrinsics.checkNotNullParameter(explorerService2, "explorerService");
        Intrinsics.checkNotNullParameter(projectId2, "projectId");
        this.explorerService = explorerService2;
        this.projectId = projectId2;
    }

    private final App toApp(AppDTO appDTO) {
        return new App(appDTO.getBrowser(), appDTO.getIos(), appDTO.getAndroid(), appDTO.getMac(), appDTO.getWindows(), appDTO.getLinux(), appDTO.getChrome(), appDTO.getFirefox(), appDTO.getSafari(), appDTO.getEdge(), appDTO.getOpera());
    }

    private final Colors toColors(ColorsDTO colorsDTO) {
        return new Colors(colorsDTO.getPrimary(), colorsDTO.getSecondary());
    }

    private final DappListings toDappListing(DappListingsDTO dappListingsDTO) {
        Iterable<ListingDTO> values = dappListingsDTO.getListings().values();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        for (ListingDTO listing : values) {
            arrayList.add(toListing(listing));
        }
        return new DappListings(arrayList, dappListingsDTO.getCount(), dappListingsDTO.getTotal());
    }

    private final Desktop toDesktop(DesktopDTO desktopDTO) {
        return new Desktop(desktopDTO.getNative(), desktopDTO.getUniversal());
    }

    private final ImageUrl toImageUrl(ImageUrlDTO imageUrlDTO) {
        return new ImageUrl(imageUrlDTO.getSm(), imageUrlDTO.getMd(), imageUrlDTO.getLg());
    }

    private final Injected toInjected(InjectedDTO injectedDTO) {
        return new Injected(injectedDTO.getNamespace(), injectedDTO.getInjectedId());
    }

    private final Listing toListing(ListingDTO listingDTO) {
        ArrayList arrayList;
        String id = listingDTO.getId();
        String name = listingDTO.getName();
        String description = listingDTO.getDescription();
        Uri parse = Uri.parse(listingDTO.getHomepage());
        List<String> chains = listingDTO.getChains();
        List<String> versions = listingDTO.getVersions();
        List<String> sdks = listingDTO.getSdks();
        String appType = listingDTO.getAppType();
        String imageId = listingDTO.getImageId();
        ImageUrl imageUrl = toImageUrl(listingDTO.getImageUrl());
        App app2 = toApp(listingDTO.getApp());
        List<InjectedDTO> injected = listingDTO.getInjected();
        if (injected != null) {
            Iterable<InjectedDTO> iterable = injected;
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(iterable, 10));
            for (InjectedDTO injected2 : iterable) {
                arrayList2.add(toInjected(injected2));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        Mobile mobile = toMobile(listingDTO.getMobile());
        Desktop desktop = toDesktop(listingDTO.getDesktop());
        Iterable<SupportedStandardDTO> supportedStandards = listingDTO.getSupportedStandards();
        Desktop desktop2 = desktop;
        Mobile mobile2 = mobile;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(supportedStandards, 10));
        for (SupportedStandardDTO supportedStandard : supportedStandards) {
            arrayList3.add(toSupportedStandard(supportedStandard));
        }
        ArrayList arrayList4 = arrayList3;
        return new Listing(id, name, description, parse, chains, versions, sdks, appType, imageId, imageUrl, app2, arrayList, mobile2, desktop2, arrayList4, toMetadata(listingDTO.getMetadata()), listingDTO.getUpdatedAt());
    }

    private final com.reown.android.internal.common.explorer.data.model.Metadata toMetadata(MetadataDTO metadataDTO) {
        return new com.reown.android.internal.common.explorer.data.model.Metadata(metadataDTO.getShortName(), toColors(metadataDTO.getColors()));
    }

    private final Mobile toMobile(MobileDTO mobileDTO) {
        return new Mobile(mobileDTO.getNative(), mobileDTO.getUniversal());
    }

    private final NotificationType toNotificationType(NotificationTypeDTO notificationTypeDTO) {
        String name = notificationTypeDTO.getName();
        String id = notificationTypeDTO.getId();
        String description = notificationTypeDTO.getDescription();
        ImageUrlDTO imageUrl = notificationTypeDTO.getImageUrl();
        return new NotificationType(name, id, description, imageUrl != null ? toImageUrl(imageUrl) : null);
    }

    private final NotifyConfig toNotifyConfig(NotifyConfigDTO notifyConfigDTO) {
        NotifyConfigDataDTO data = notifyConfigDTO.getData();
        Iterable<NotificationTypeDTO> notificationTypes = data.getNotificationTypes();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(notificationTypes, 10));
        for (NotificationTypeDTO notificationType : notificationTypes) {
            arrayList.add(toNotificationType(notificationType));
        }
        String name = data.getName();
        String description = data.getDescription();
        ImageUrlDTO imageUrl = data.getImageUrl();
        ImageUrl imageUrl2 = imageUrl != null ? toImageUrl(imageUrl) : null;
        String homepage = data.getHomepage();
        if (homepage == null) {
            homepage = "";
        }
        return new NotifyConfig(data.getDappUrl(), name, homepage, description, arrayList, imageUrl2, data.isVerified());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r0 == null) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003f, code lost:
        if (r0 == null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0055, code lost:
        if (r0 == null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        if (r0 == null) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.reown.android.internal.common.explorer.data.model.Project toProject(com.reown.android.internal.common.explorer.data.network.model.ProjectDTO r10) {
        /*
            r9 = this;
            java.lang.String r1 = r10.getId()
            java.lang.String r0 = r10.getName()
            r2 = 0
            if (r0 == 0) goto L_0x0018
            boolean r3 = kotlin.text.StringsKt.isBlank(r0)
            if (r3 != 0) goto L_0x0012
            goto L_0x0013
        L_0x0012:
            r0 = r2
        L_0x0013:
            if (r0 != 0) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            r3 = r0
            goto L_0x001b
        L_0x0018:
            java.lang.String r0 = "Name not provided"
            goto L_0x0016
        L_0x001b:
            java.lang.String r0 = r10.getDescription()
            if (r0 == 0) goto L_0x002e
            boolean r4 = kotlin.text.StringsKt.isBlank(r0)
            if (r4 != 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r0 = r2
        L_0x0029:
            if (r0 != 0) goto L_0x002c
            goto L_0x002e
        L_0x002c:
            r5 = r0
            goto L_0x0031
        L_0x002e:
            java.lang.String r0 = "Description not provided"
            goto L_0x002c
        L_0x0031:
            java.lang.String r0 = r10.getHomepage()
            if (r0 == 0) goto L_0x0044
            boolean r4 = kotlin.text.StringsKt.isBlank(r0)
            if (r4 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r0 = r2
        L_0x003f:
            if (r0 != 0) goto L_0x0042
            goto L_0x0044
        L_0x0042:
            r4 = r0
            goto L_0x0047
        L_0x0044:
            java.lang.String r0 = "Homepage not provided"
            goto L_0x0042
        L_0x0047:
            java.lang.String r0 = r10.getImageId()
            if (r0 == 0) goto L_0x005a
            boolean r6 = kotlin.text.StringsKt.isBlank(r0)
            if (r6 != 0) goto L_0x0054
            goto L_0x0055
        L_0x0054:
            r0 = r2
        L_0x0055:
            if (r0 != 0) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r6 = r0
            goto L_0x005d
        L_0x005a:
            java.lang.String r0 = "ImageID not provided"
            goto L_0x0058
        L_0x005d:
            com.reown.android.internal.common.explorer.data.network.model.ImageUrlDTO r0 = r10.getImageUrl()
            if (r0 == 0) goto L_0x0069
            com.reown.android.internal.common.explorer.data.model.ImageUrl r9 = r9.toImageUrl(r0)
            if (r9 != 0) goto L_0x0070
        L_0x0069:
            com.reown.android.internal.common.explorer.data.model.ImageUrl r9 = new com.reown.android.internal.common.explorer.data.model.ImageUrl
            java.lang.String r0 = ""
            r9.<init>(r0, r0, r0)
        L_0x0070:
            java.lang.String r0 = r10.getDappUrl()
            if (r0 == 0) goto L_0x0082
            boolean r7 = kotlin.text.StringsKt.isBlank(r0)
            if (r7 != 0) goto L_0x007d
            r2 = r0
        L_0x007d:
            if (r2 != 0) goto L_0x0080
            goto L_0x0082
        L_0x0080:
            r7 = r2
            goto L_0x0085
        L_0x0082:
            java.lang.String r0 = "Dapp url not provided"
            r7 = r0
        L_0x0085:
            java.lang.Long r8 = r10.getOrder()
            com.reown.android.internal.common.explorer.data.model.Project r10 = new com.reown.android.internal.common.explorer.data.model.Project
            r0 = r10
            r2 = r3
            r3 = r4
            r4 = r6
            r6 = r9
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.ExplorerRepository.toProject(com.reown.android.internal.common.explorer.data.network.model.ProjectDTO):com.reown.android.internal.common.explorer.data.model.Project");
    }

    private final ProjectListing toProjectListing(ProjectListingDTO projectListingDTO) {
        Iterable<ProjectDTO> values = projectListingDTO.getProjects().values();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(values, 10));
        for (ProjectDTO project : values) {
            arrayList.add(toProject(project));
        }
        return new ProjectListing(arrayList, projectListingDTO.getCount());
    }

    private final SupportedStandard toSupportedStandard(SupportedStandardDTO supportedStandardDTO) {
        return new SupportedStandard(supportedStandardDTO.getId(), supportedStandardDTO.getUrl(), supportedStandardDTO.getTitle(), supportedStandardDTO.getStandardId(), supportedStandardDTO.getStandardPrefix());
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllDapps(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.reown.android.internal.common.explorer.data.model.DappListings> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.reown.android.internal.common.explorer.ExplorerRepository$getAllDapps$1
            if (r0 == 0) goto L_0x0013
            r0 = r5
            com.reown.android.internal.common.explorer.ExplorerRepository$getAllDapps$1 r0 = (com.reown.android.internal.common.explorer.ExplorerRepository$getAllDapps$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.explorer.ExplorerRepository$getAllDapps$1 r0 = new com.reown.android.internal.common.explorer.ExplorerRepository$getAllDapps$1
            r0.<init>(r4, r5)
        L_0x0018:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0031
            if (r2 != r3) goto L_0x0029
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0045
        L_0x0029:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r5)
            com.reown.android.internal.common.explorer.data.network.ExplorerService r5 = r4.explorerService
            com.reown.android.internal.common.model.ProjectId r2 = r4.projectId
            java.lang.String r2 = r2.getValue()
            r0.label = r3
            java.lang.Object r5 = r5.getAllDapps(r2, r0)
            if (r5 != r1) goto L_0x0045
            return r1
        L_0x0045:
            retrofit2.Response r5 = (retrofit2.Response) r5
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto L_0x0061
            java.lang.Object r0 = r5.body()
            if (r0 == 0) goto L_0x0061
            java.lang.Object r5 = r5.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            com.reown.android.internal.common.explorer.data.network.model.DappListingsDTO r5 = (com.reown.android.internal.common.explorer.data.network.model.DappListingsDTO) r5
            com.reown.android.internal.common.explorer.data.model.DappListings r4 = r4.toDappListing(r5)
            return r4
        L_0x0061:
            java.lang.Throwable r4 = new java.lang.Throwable
            okhttp3.ResponseBody r5 = r5.errorBody()
            if (r5 == 0) goto L_0x006e
            java.lang.String r5 = r5.string()
            goto L_0x006f
        L_0x006e:
            r5 = 0
        L_0x006f:
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.ExplorerRepository.getAllDapps(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0023  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getNotifyConfig(@org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.reown.android.internal.common.explorer.data.model.NotifyConfig> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.reown.android.internal.common.explorer.ExplorerRepository$getNotifyConfig$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.reown.android.internal.common.explorer.ExplorerRepository$getNotifyConfig$1 r0 = (com.reown.android.internal.common.explorer.ExplorerRepository$getNotifyConfig$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.reown.android.internal.common.explorer.ExplorerRepository$getNotifyConfig$1 r0 = new com.reown.android.internal.common.explorer.ExplorerRepository$getNotifyConfig$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x004f
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r7)
            com.reown.android.internal.common.explorer.data.network.ExplorerService r7 = r5.explorerService
            com.reown.android.internal.common.model.ProjectId r2 = r5.projectId
            java.lang.String r2 = r2.getValue()
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r7 = r7.getNotifyConfig(r2, r6, r0)
            if (r7 != r1) goto L_0x004f
            return r1
        L_0x004f:
            retrofit2.Response r7 = (retrofit2.Response) r7
            boolean r6 = r7.isSuccessful()
            if (r6 == 0) goto L_0x006b
            java.lang.Object r6 = r7.body()
            if (r6 == 0) goto L_0x006b
            java.lang.Object r6 = r7.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            com.reown.android.internal.common.explorer.data.network.model.NotifyConfigDTO r6 = (com.reown.android.internal.common.explorer.data.network.model.NotifyConfigDTO) r6
            com.reown.android.internal.common.explorer.data.model.NotifyConfig r5 = r5.toNotifyConfig(r6)
            return r5
        L_0x006b:
            java.lang.Throwable r5 = new java.lang.Throwable
            okhttp3.ResponseBody r6 = r7.errorBody()
            if (r6 == 0) goto L_0x0078
            java.lang.String r6 = r6.string()
            goto L_0x0079
        L_0x0078:
            r6 = 0
        L_0x0079:
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.ExplorerRepository.getNotifyConfig(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getProjects(int r9, int r10, boolean r11, boolean r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.reown.android.internal.common.explorer.data.model.ProjectListing> r13) {
        /*
            r8 = this;
            boolean r0 = r13 instanceof com.reown.android.internal.common.explorer.ExplorerRepository$getProjects$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            com.reown.android.internal.common.explorer.ExplorerRepository$getProjects$1 r0 = (com.reown.android.internal.common.explorer.ExplorerRepository$getProjects$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0014
            int r1 = r1 - r2
            r0.label = r1
        L_0x0012:
            r7 = r0
            goto L_0x001a
        L_0x0014:
            com.reown.android.internal.common.explorer.ExplorerRepository$getProjects$1 r0 = new com.reown.android.internal.common.explorer.ExplorerRepository$getProjects$1
            r0.<init>(r8, r13)
            goto L_0x0012
        L_0x001a:
            java.lang.Object r13 = r7.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 != r2) goto L_0x002b
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0054
        L_0x002b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r13)
            com.reown.android.internal.common.explorer.data.network.ExplorerService r1 = r8.explorerService
            com.reown.android.internal.common.model.ProjectId r13 = r8.projectId
            java.lang.String r13 = r13.getValue()
            r7.I$0 = r9
            r7.I$1 = r10
            r7.Z$0 = r11
            r7.Z$1 = r12
            r7.label = r2
            r2 = r13
            r3 = r10
            r4 = r9
            r5 = r11
            r6 = r12
            java.lang.Object r13 = r1.getProjects(r2, r3, r4, r5, r6, r7)
            if (r13 != r0) goto L_0x0054
            return r0
        L_0x0054:
            retrofit2.Response r13 = (retrofit2.Response) r13
            boolean r9 = r13.isSuccessful()
            if (r9 == 0) goto L_0x0070
            java.lang.Object r9 = r13.body()
            if (r9 == 0) goto L_0x0070
            java.lang.Object r9 = r13.body()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            com.reown.android.internal.common.explorer.data.network.model.ProjectListingDTO r9 = (com.reown.android.internal.common.explorer.data.network.model.ProjectListingDTO) r9
            com.reown.android.internal.common.explorer.data.model.ProjectListing r8 = r8.toProjectListing(r9)
            return r8
        L_0x0070:
            java.lang.Throwable r8 = new java.lang.Throwable
            okhttp3.ResponseBody r9 = r13.errorBody()
            if (r9 == 0) goto L_0x007d
            java.lang.String r9 = r9.string()
            goto L_0x007e
        L_0x007d:
            r9 = 0
        L_0x007e:
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reown.android.internal.common.explorer.ExplorerRepository.getProjects(int, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
