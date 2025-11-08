package com.adjust.sdk;

import com.adjust.sdk.network.IActivityPackageSender;
import com.adjust.sdk.scheduler.SingleThreadCachedScheduler;
import com.adjust.sdk.scheduler.ThreadScheduler;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseVerificationHandler implements IPurchaseVerificationHandler {
    private static final double MILLISECONDS_TO_SECONDS_DIVISOR = 1000.0d;
    private static final String SCHEDULED_EXECUTOR_SOURCE = "PurchaseVerificationHandler";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private IActivityPackageSender activityPackageSender;
    private BackoffStrategy backoffStrategy = AdjustFactory.getSdkClickBackoffStrategy();
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    /* access modifiers changed from: private */
    public List<ActivityPackage> packageQueue;
    private boolean paused;
    private ThreadScheduler scheduler = new SingleThreadCachedScheduler(SCHEDULED_EXECUTOR_SOURCE);

    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ActivityPackage f3301a;

        public a(ActivityPackage activityPackage) {
            this.f3301a = activityPackage;
        }

        public final void run() {
            PurchaseVerificationHandler.this.packageQueue.add(this.f3301a);
            PurchaseVerificationHandler.this.logger.debug("Added purchase_verification %d", Integer.valueOf(PurchaseVerificationHandler.this.packageQueue.size()));
            PurchaseVerificationHandler.this.logger.verbose("%s", this.f3301a.getExtendedString());
            PurchaseVerificationHandler.this.sendNextPurchaseVerificationPackage();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        public final void run() {
            PurchaseVerificationHandler.this.sendNextPurchaseVerificationPackageI();
        }
    }

    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ActivityPackage f3304a;

        public c(ActivityPackage activityPackage) {
            this.f3304a = activityPackage;
        }

        public final void run() {
            PurchaseVerificationHandler.this.sendPurchaseVerificationPackageI(this.f3304a);
            PurchaseVerificationHandler.this.sendNextPurchaseVerificationPackage();
        }
    }

    public PurchaseVerificationHandler(IActivityHandler iActivityHandler, boolean z2, IActivityPackageSender iActivityPackageSender) {
        init(iActivityHandler, z2, iActivityPackageSender);
    }

    private Map<String, String> generateSendingParametersI() {
        HashMap hashMap = new HashMap();
        PackageBuilder.addString(hashMap, "sent_at", Util.dateFormatter.format(Long.valueOf(System.currentTimeMillis())));
        int size = this.packageQueue.size() - 1;
        if (size > 0) {
            PackageBuilder.addLong(hashMap, "queue_size", (long) size);
        }
        return hashMap;
    }

    private void retrySendingI(ActivityPackage activityPackage) {
        this.logger.error("Retrying purchase_verification package for the %d time", Integer.valueOf(activityPackage.increaseRetries()));
        sendPurchaseVerificationPackage(activityPackage);
    }

    /* access modifiers changed from: private */
    public void sendNextPurchaseVerificationPackage() {
        this.scheduler.submit(new b());
    }

    /* access modifiers changed from: private */
    public void sendNextPurchaseVerificationPackageI() {
        IActivityHandler iActivityHandler = this.activityHandlerWeakRef.get();
        if (iActivityHandler.getActivityState() != null && !iActivityHandler.getActivityState().isGdprForgotten && !this.paused && !this.packageQueue.isEmpty()) {
            ActivityPackage remove = this.packageQueue.remove(0);
            int retries = remove.getRetries();
            c cVar = new c(remove);
            if (retries <= 0) {
                cVar.run();
                return;
            }
            long waitingTime = Util.getWaitingTime(retries, this.backoffStrategy);
            this.logger.verbose("Waiting for %s seconds before retrying purchase_verification for the %d time", Util.SecondsDisplayFormat.format(((double) waitingTime) / 1000.0d), Integer.valueOf(retries));
            this.scheduler.schedule(cVar, waitingTime);
        }
    }

    /* access modifiers changed from: private */
    public void sendPurchaseVerificationPackageI(ActivityPackage activityPackage) {
        IActivityHandler iActivityHandler = this.activityHandlerWeakRef.get();
        ResponseData sendActivityPackageSync = this.activityPackageSender.sendActivityPackageSync(activityPackage, generateSendingParametersI());
        if (sendActivityPackageSync instanceof PurchaseVerificationResponseData) {
            PurchaseVerificationResponseData purchaseVerificationResponseData = (PurchaseVerificationResponseData) sendActivityPackageSync;
            if (purchaseVerificationResponseData.willRetry) {
                retrySendingI(activityPackage);
            } else if (iActivityHandler != null) {
                if (purchaseVerificationResponseData.trackingState == TrackingState.OPTED_OUT) {
                    iActivityHandler.gotOptOutResponse();
                } else {
                    iActivityHandler.finishedTrackingActivity(purchaseVerificationResponseData);
                }
            }
        }
    }

    public void init(IActivityHandler iActivityHandler, boolean z2, IActivityPackageSender iActivityPackageSender) {
        this.paused = !z2;
        this.packageQueue = new ArrayList();
        this.activityHandlerWeakRef = new WeakReference<>(iActivityHandler);
        this.activityPackageSender = iActivityPackageSender;
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
        sendNextPurchaseVerificationPackage();
    }

    public void sendPurchaseVerificationPackage(ActivityPackage activityPackage) {
        this.scheduler.submit(new a(activityPackage));
    }

    public void teardown() {
        this.logger.verbose("PurchaseVerificationHandler teardown", new Object[0]);
        ThreadScheduler threadScheduler = this.scheduler;
        if (threadScheduler != null) {
            threadScheduler.teardown();
        }
        List<ActivityPackage> list = this.packageQueue;
        if (list != null) {
            list.clear();
        }
        WeakReference<IActivityHandler> weakReference = this.activityHandlerWeakRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.logger = null;
        this.packageQueue = null;
        this.backoffStrategy = null;
        this.scheduler = null;
    }
}
