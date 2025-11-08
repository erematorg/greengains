package com.appsamurai.storyly.exoplayer2.core.offline;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.appsamurai.storyly.exoplayer2.common.util.Assertions;
import com.appsamurai.storyly.exoplayer2.common.util.Log;
import com.appsamurai.storyly.exoplayer2.common.util.NotificationUtil;
import com.appsamurai.storyly.exoplayer2.common.util.Util;
import com.appsamurai.storyly.exoplayer2.core.offline.DownloadManager;
import com.appsamurai.storyly.exoplayer2.core.scheduler.Requirements;
import com.appsamurai.storyly.exoplayer2.core.scheduler.Scheduler;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public abstract class DownloadService extends Service {
    public static final String ACTION_ADD_DOWNLOAD = "com.google.android.exoplayer.downloadService.action.ADD_DOWNLOAD";
    public static final String ACTION_INIT = "com.google.android.exoplayer.downloadService.action.INIT";
    public static final String ACTION_PAUSE_DOWNLOADS = "com.google.android.exoplayer.downloadService.action.PAUSE_DOWNLOADS";
    public static final String ACTION_REMOVE_ALL_DOWNLOADS = "com.google.android.exoplayer.downloadService.action.REMOVE_ALL_DOWNLOADS";
    public static final String ACTION_REMOVE_DOWNLOAD = "com.google.android.exoplayer.downloadService.action.REMOVE_DOWNLOAD";
    private static final String ACTION_RESTART = "com.google.android.exoplayer.downloadService.action.RESTART";
    public static final String ACTION_RESUME_DOWNLOADS = "com.google.android.exoplayer.downloadService.action.RESUME_DOWNLOADS";
    public static final String ACTION_SET_REQUIREMENTS = "com.google.android.exoplayer.downloadService.action.SET_REQUIREMENTS";
    public static final String ACTION_SET_STOP_REASON = "com.google.android.exoplayer.downloadService.action.SET_STOP_REASON";
    public static final long DEFAULT_FOREGROUND_NOTIFICATION_UPDATE_INTERVAL = 1000;
    public static final int FOREGROUND_NOTIFICATION_ID_NONE = 0;
    public static final String KEY_CONTENT_ID = "content_id";
    public static final String KEY_DOWNLOAD_REQUEST = "download_request";
    public static final String KEY_FOREGROUND = "foreground";
    public static final String KEY_REQUIREMENTS = "requirements";
    public static final String KEY_STOP_REASON = "stop_reason";
    private static final String TAG = "DownloadService";
    private static final HashMap<Class<? extends DownloadService>, DownloadManagerHelper> downloadManagerHelpers = new HashMap<>();
    @StringRes
    private final int channelDescriptionResourceId;
    @Nullable
    private final String channelId;
    @StringRes
    private final int channelNameResourceId;
    /* access modifiers changed from: private */
    public DownloadManagerHelper downloadManagerHelper;
    @Nullable
    private final ForegroundNotificationUpdater foregroundNotificationUpdater;
    private boolean isDestroyed;
    private boolean isStopped;
    private int lastStartId;
    private boolean startedInForeground;
    private boolean taskRemoved;

    public static final class DownloadManagerHelper implements DownloadManager.Listener {
        private final Context context;
        /* access modifiers changed from: private */
        public final DownloadManager downloadManager;
        @Nullable
        private DownloadService downloadService;
        private final boolean foregroundAllowed;
        private Requirements scheduledRequirements;
        @Nullable
        private final Scheduler scheduler;
        private final Class<? extends DownloadService> serviceClass;

        @RequiresNonNull({"scheduler"})
        private void cancelScheduler() {
            Requirements requirements = new Requirements(0);
            if (schedulerNeedsUpdate(requirements)) {
                this.scheduler.cancel();
                this.scheduledRequirements = requirements;
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$attachService$0(DownloadService downloadService2) {
            downloadService2.notifyDownloads(this.downloadManager.getCurrentDownloads());
        }

        private void restartService() {
            if (this.foregroundAllowed) {
                try {
                    Util.startForegroundService(this.context, DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_RESTART));
                } catch (IllegalStateException unused) {
                    Log.w(DownloadService.TAG, "Failed to restart (foreground launch restriction)");
                }
            } else {
                try {
                    this.context.startService(DownloadService.getIntent(this.context, this.serviceClass, DownloadService.ACTION_INIT));
                } catch (IllegalStateException unused2) {
                    Log.w(DownloadService.TAG, "Failed to restart (process is idle)");
                }
            }
        }

        private boolean schedulerNeedsUpdate(Requirements requirements) {
            return !Util.areEqual(this.scheduledRequirements, requirements);
        }

        private boolean serviceMayNeedRestart() {
            DownloadService downloadService2 = this.downloadService;
            return downloadService2 == null || downloadService2.isStopped();
        }

        public void attachService(DownloadService downloadService2) {
            Assertions.checkState(this.downloadService == null);
            this.downloadService = downloadService2;
            if (this.downloadManager.isInitialized()) {
                Util.createHandlerForCurrentOrMainLooper().postAtFrontOfQueue(new h(this, downloadService2, 0));
            }
        }

        public void detachService(DownloadService downloadService2) {
            Assertions.checkState(this.downloadService == downloadService2);
            this.downloadService = null;
        }

        public void onDownloadChanged(DownloadManager downloadManager2, Download download, @Nullable Exception exc) {
            DownloadService downloadService2 = this.downloadService;
            if (downloadService2 != null) {
                downloadService2.notifyDownloadChanged(download);
            }
            if (serviceMayNeedRestart() && DownloadService.needsStartedService(download.state)) {
                Log.w(DownloadService.TAG, "DownloadService wasn't running. Restarting.");
                restartService();
            }
        }

        public void onDownloadRemoved(DownloadManager downloadManager2, Download download) {
            DownloadService downloadService2 = this.downloadService;
            if (downloadService2 != null) {
                downloadService2.notifyDownloadRemoved();
            }
        }

        public final void onIdle(DownloadManager downloadManager2) {
            DownloadService downloadService2 = this.downloadService;
            if (downloadService2 != null) {
                downloadService2.onIdle();
            }
        }

        public void onInitialized(DownloadManager downloadManager2) {
            DownloadService downloadService2 = this.downloadService;
            if (downloadService2 != null) {
                downloadService2.notifyDownloads(downloadManager2.getCurrentDownloads());
            }
        }

        public void onRequirementsStateChanged(DownloadManager downloadManager2, Requirements requirements, int i3) {
            updateScheduler();
        }

        public void onWaitingForRequirementsChanged(DownloadManager downloadManager2, boolean z2) {
            if (!z2 && !downloadManager2.getDownloadsPaused() && serviceMayNeedRestart()) {
                List<Download> currentDownloads = downloadManager2.getCurrentDownloads();
                for (int i3 = 0; i3 < currentDownloads.size(); i3++) {
                    if (currentDownloads.get(i3).state == 0) {
                        restartService();
                        return;
                    }
                }
            }
        }

        public boolean updateScheduler() {
            boolean isWaitingForRequirements = this.downloadManager.isWaitingForRequirements();
            if (this.scheduler == null) {
                return !isWaitingForRequirements;
            }
            if (!isWaitingForRequirements) {
                cancelScheduler();
                return true;
            }
            Requirements requirements = this.downloadManager.getRequirements();
            if (!this.scheduler.getSupportedRequirements(requirements).equals(requirements)) {
                cancelScheduler();
                return false;
            } else if (!schedulerNeedsUpdate(requirements)) {
                return true;
            } else {
                if (this.scheduler.schedule(requirements, this.context.getPackageName(), DownloadService.ACTION_RESTART)) {
                    this.scheduledRequirements = requirements;
                    return true;
                }
                Log.w(DownloadService.TAG, "Failed to schedule restart");
                cancelScheduler();
                return false;
            }
        }

        private DownloadManagerHelper(Context context2, DownloadManager downloadManager2, boolean z2, @Nullable Scheduler scheduler2, Class<? extends DownloadService> cls) {
            this.context = context2;
            this.downloadManager = downloadManager2;
            this.foregroundAllowed = z2;
            this.scheduler = scheduler2;
            this.serviceClass = cls;
            downloadManager2.addListener(this);
            updateScheduler();
        }
    }

    public final class ForegroundNotificationUpdater {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private boolean notificationDisplayed;
        private final int notificationId;
        private boolean periodicUpdatesStarted;
        private final long updateInterval;

        public ForegroundNotificationUpdater(int i3, long j2) {
            this.notificationId = i3;
            this.updateInterval = j2;
        }

        /* access modifiers changed from: private */
        public void update() {
            DownloadManager access$100 = ((DownloadManagerHelper) Assertions.checkNotNull(DownloadService.this.downloadManagerHelper)).downloadManager;
            Notification foregroundNotification = DownloadService.this.getForegroundNotification(access$100.getCurrentDownloads(), access$100.getNotMetRequirements());
            if (!this.notificationDisplayed) {
                DownloadService.this.startForeground(this.notificationId, foregroundNotification);
                this.notificationDisplayed = true;
            } else {
                ((NotificationManager) DownloadService.this.getSystemService("notification")).notify(this.notificationId, foregroundNotification);
            }
            if (this.periodicUpdatesStarted) {
                this.handler.removeCallbacksAndMessages((Object) null);
                this.handler.postDelayed(new i(this, 0), this.updateInterval);
            }
        }

        public void invalidate() {
            if (this.notificationDisplayed) {
                update();
            }
        }

        public void showNotificationIfNotAlready() {
            if (!this.notificationDisplayed) {
                update();
            }
        }

        public void startPeriodicUpdates() {
            this.periodicUpdatesStarted = true;
            update();
        }

        public void stopPeriodicUpdates() {
            this.periodicUpdatesStarted = false;
            this.handler.removeCallbacksAndMessages((Object) null);
        }
    }

    public DownloadService(int i3) {
        this(i3, 1000);
    }

    public static Intent buildAddDownloadIntent(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, boolean z2) {
        return buildAddDownloadIntent(context, cls, downloadRequest, 0, z2);
    }

    public static Intent buildPauseDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z2) {
        return getIntent(context, cls, ACTION_PAUSE_DOWNLOADS, z2);
    }

    public static Intent buildRemoveAllDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z2) {
        return getIntent(context, cls, ACTION_REMOVE_ALL_DOWNLOADS, z2);
    }

    public static Intent buildRemoveDownloadIntent(Context context, Class<? extends DownloadService> cls, String str, boolean z2) {
        return getIntent(context, cls, ACTION_REMOVE_DOWNLOAD, z2).putExtra(KEY_CONTENT_ID, str);
    }

    public static Intent buildResumeDownloadsIntent(Context context, Class<? extends DownloadService> cls, boolean z2) {
        return getIntent(context, cls, ACTION_RESUME_DOWNLOADS, z2);
    }

    public static Intent buildSetRequirementsIntent(Context context, Class<? extends DownloadService> cls, Requirements requirements, boolean z2) {
        return getIntent(context, cls, ACTION_SET_REQUIREMENTS, z2).putExtra(KEY_REQUIREMENTS, requirements);
    }

    public static Intent buildSetStopReasonIntent(Context context, Class<? extends DownloadService> cls, @Nullable String str, int i3, boolean z2) {
        return getIntent(context, cls, ACTION_SET_STOP_REASON, z2).putExtra(KEY_CONTENT_ID, str).putExtra(KEY_STOP_REASON, i3);
    }

    private static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str, boolean z2) {
        return getIntent(context, cls, str).putExtra(KEY_FOREGROUND, z2);
    }

    /* access modifiers changed from: private */
    public boolean isStopped() {
        return this.isStopped;
    }

    /* access modifiers changed from: private */
    public static boolean needsStartedService(int i3) {
        return i3 == 2 || i3 == 5 || i3 == 7;
    }

    /* access modifiers changed from: private */
    public void notifyDownloadChanged(Download download) {
        if (this.foregroundNotificationUpdater == null) {
            return;
        }
        if (needsStartedService(download.state)) {
            this.foregroundNotificationUpdater.startPeriodicUpdates();
        } else {
            this.foregroundNotificationUpdater.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void notifyDownloadRemoved() {
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null) {
            foregroundNotificationUpdater2.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void notifyDownloads(List<Download> list) {
        if (this.foregroundNotificationUpdater != null) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (needsStartedService(list.get(i3).state)) {
                    this.foregroundNotificationUpdater.startPeriodicUpdates();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onIdle() {
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null) {
            foregroundNotificationUpdater2.stopPeriodicUpdates();
        }
        if (((DownloadManagerHelper) Assertions.checkNotNull(this.downloadManagerHelper)).updateScheduler()) {
            if (Util.SDK_INT >= 28 || !this.taskRemoved) {
                this.isStopped |= stopSelfResult(this.lastStartId);
                return;
            }
            stopSelf();
            this.isStopped = true;
        }
    }

    public static void sendAddDownload(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, boolean z2) {
        startService(context, buildAddDownloadIntent(context, cls, downloadRequest, z2), z2);
    }

    public static void sendPauseDownloads(Context context, Class<? extends DownloadService> cls, boolean z2) {
        startService(context, buildPauseDownloadsIntent(context, cls, z2), z2);
    }

    public static void sendRemoveAllDownloads(Context context, Class<? extends DownloadService> cls, boolean z2) {
        startService(context, buildRemoveAllDownloadsIntent(context, cls, z2), z2);
    }

    public static void sendRemoveDownload(Context context, Class<? extends DownloadService> cls, String str, boolean z2) {
        startService(context, buildRemoveDownloadIntent(context, cls, str, z2), z2);
    }

    public static void sendResumeDownloads(Context context, Class<? extends DownloadService> cls, boolean z2) {
        startService(context, buildResumeDownloadsIntent(context, cls, z2), z2);
    }

    public static void sendSetRequirements(Context context, Class<? extends DownloadService> cls, Requirements requirements, boolean z2) {
        startService(context, buildSetRequirementsIntent(context, cls, requirements, z2), z2);
    }

    public static void sendSetStopReason(Context context, Class<? extends DownloadService> cls, @Nullable String str, int i3, boolean z2) {
        startService(context, buildSetStopReasonIntent(context, cls, str, i3, z2), z2);
    }

    public static void start(Context context, Class<? extends DownloadService> cls) {
        context.startService(getIntent(context, cls, ACTION_INIT));
    }

    public static void startForeground(Context context, Class<? extends DownloadService> cls) {
        Util.startForegroundService(context, getIntent(context, cls, ACTION_INIT, true));
    }

    private static void startService(Context context, Intent intent, boolean z2) {
        if (z2) {
            Util.startForegroundService(context, intent);
        } else {
            context.startService(intent);
        }
    }

    public abstract DownloadManager getDownloadManager();

    public abstract Notification getForegroundNotification(List<Download> list, int i3);

    @Nullable
    public abstract Scheduler getScheduler();

    public final void invalidateForegroundNotification() {
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null && !this.isDestroyed) {
            foregroundNotificationUpdater2.invalidate();
        }
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException();
    }

    public void onCreate() {
        String str = this.channelId;
        if (str != null) {
            NotificationUtil.createNotificationChannel(this, str, this.channelNameResourceId, this.channelDescriptionResourceId, 2);
        }
        Class<?> cls = getClass();
        HashMap<Class<? extends DownloadService>, DownloadManagerHelper> hashMap = downloadManagerHelpers;
        DownloadManagerHelper downloadManagerHelper2 = hashMap.get(cls);
        if (downloadManagerHelper2 == null) {
            boolean z2 = false;
            boolean z3 = this.foregroundNotificationUpdater != null;
            if (Util.SDK_INT < 31) {
                z2 = true;
            }
            Scheduler scheduler = (!z3 || !z2) ? null : getScheduler();
            DownloadManager downloadManager = getDownloadManager();
            downloadManager.resumeDownloads();
            downloadManagerHelper2 = new DownloadManagerHelper(getApplicationContext(), downloadManager, z3, scheduler, cls);
            hashMap.put(cls, downloadManagerHelper2);
        }
        this.downloadManagerHelper = downloadManagerHelper2;
        downloadManagerHelper2.attachService(this);
    }

    public void onDestroy() {
        this.isDestroyed = true;
        ((DownloadManagerHelper) Assertions.checkNotNull(this.downloadManagerHelper)).detachService(this);
        ForegroundNotificationUpdater foregroundNotificationUpdater2 = this.foregroundNotificationUpdater;
        if (foregroundNotificationUpdater2 != null) {
            foregroundNotificationUpdater2.stopPeriodicUpdates();
        }
    }

    public int onStartCommand(@Nullable Intent intent, int i3, int i4) {
        String str;
        String str2;
        ForegroundNotificationUpdater foregroundNotificationUpdater2;
        this.lastStartId = i4;
        this.taskRemoved = false;
        if (intent != null) {
            str2 = intent.getAction();
            str = intent.getStringExtra(KEY_CONTENT_ID);
            this.startedInForeground |= intent.getBooleanExtra(KEY_FOREGROUND, false) || ACTION_RESTART.equals(str2);
        } else {
            str2 = null;
            str = null;
        }
        if (str2 == null) {
            str2 = ACTION_INIT;
        }
        DownloadManager access$100 = ((DownloadManagerHelper) Assertions.checkNotNull(this.downloadManagerHelper)).downloadManager;
        char c3 = 65535;
        switch (str2.hashCode()) {
            case -1931239035:
                if (str2.equals(ACTION_ADD_DOWNLOAD)) {
                    c3 = 0;
                    break;
                }
                break;
            case -932047176:
                if (str2.equals(ACTION_RESUME_DOWNLOADS)) {
                    c3 = 1;
                    break;
                }
                break;
            case -871181424:
                if (str2.equals(ACTION_RESTART)) {
                    c3 = 2;
                    break;
                }
                break;
            case -650547439:
                if (str2.equals(ACTION_REMOVE_ALL_DOWNLOADS)) {
                    c3 = 3;
                    break;
                }
                break;
            case -119057172:
                if (str2.equals(ACTION_SET_REQUIREMENTS)) {
                    c3 = 4;
                    break;
                }
                break;
            case 191112771:
                if (str2.equals(ACTION_PAUSE_DOWNLOADS)) {
                    c3 = 5;
                    break;
                }
                break;
            case 671523141:
                if (str2.equals(ACTION_SET_STOP_REASON)) {
                    c3 = 6;
                    break;
                }
                break;
            case 1015676687:
                if (str2.equals(ACTION_INIT)) {
                    c3 = 7;
                    break;
                }
                break;
            case 1547520644:
                if (str2.equals(ACTION_REMOVE_DOWNLOAD)) {
                    c3 = 8;
                    break;
                }
                break;
        }
        switch (c3) {
            case 0:
                DownloadRequest downloadRequest = (DownloadRequest) ((Intent) Assertions.checkNotNull(intent)).getParcelableExtra(KEY_DOWNLOAD_REQUEST);
                if (downloadRequest != null) {
                    access$100.addDownload(downloadRequest, intent.getIntExtra(KEY_STOP_REASON, 0));
                    break;
                } else {
                    Log.e(TAG, "Ignored ADD_DOWNLOAD: Missing download_request extra");
                    break;
                }
            case 1:
                access$100.resumeDownloads();
                break;
            case 2:
            case 7:
                break;
            case 3:
                access$100.removeAllDownloads();
                break;
            case 4:
                Requirements requirements = (Requirements) ((Intent) Assertions.checkNotNull(intent)).getParcelableExtra(KEY_REQUIREMENTS);
                if (requirements != null) {
                    access$100.setRequirements(requirements);
                    break;
                } else {
                    Log.e(TAG, "Ignored SET_REQUIREMENTS: Missing requirements extra");
                    break;
                }
            case 5:
                access$100.pauseDownloads();
                break;
            case 6:
                if (((Intent) Assertions.checkNotNull(intent)).hasExtra(KEY_STOP_REASON)) {
                    access$100.setStopReason(str, intent.getIntExtra(KEY_STOP_REASON, 0));
                    break;
                } else {
                    Log.e(TAG, "Ignored SET_STOP_REASON: Missing stop_reason extra");
                    break;
                }
            case 8:
                if (str != null) {
                    access$100.removeDownload(str);
                    break;
                } else {
                    Log.e(TAG, "Ignored REMOVE_DOWNLOAD: Missing content_id extra");
                    break;
                }
            default:
                Log.e(TAG, "Ignored unrecognized action: ".concat(str2));
                break;
        }
        if (Util.SDK_INT >= 26 && this.startedInForeground && (foregroundNotificationUpdater2 = this.foregroundNotificationUpdater) != null) {
            foregroundNotificationUpdater2.showNotificationIfNotAlready();
        }
        this.isStopped = false;
        if (access$100.isIdle()) {
            onIdle();
        }
        return 1;
    }

    public void onTaskRemoved(Intent intent) {
        this.taskRemoved = true;
    }

    public DownloadService(int i3, long j2) {
        this(i3, j2, (String) null, 0, 0);
    }

    public static Intent buildAddDownloadIntent(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, int i3, boolean z2) {
        return getIntent(context, cls, ACTION_ADD_DOWNLOAD, z2).putExtra(KEY_DOWNLOAD_REQUEST, downloadRequest).putExtra(KEY_STOP_REASON, i3);
    }

    /* access modifiers changed from: private */
    public static Intent getIntent(Context context, Class<? extends DownloadService> cls, String str) {
        return new Intent(context, cls).setAction(str);
    }

    @Deprecated
    public DownloadService(int i3, long j2, @Nullable String str, @StringRes int i4) {
        this(i3, j2, str, i4, 0);
    }

    public static void sendAddDownload(Context context, Class<? extends DownloadService> cls, DownloadRequest downloadRequest, int i3, boolean z2) {
        startService(context, buildAddDownloadIntent(context, cls, downloadRequest, i3, z2), z2);
    }

    public DownloadService(int i3, long j2, @Nullable String str, @StringRes int i4, @StringRes int i5) {
        if (i3 == 0) {
            this.foregroundNotificationUpdater = null;
            this.channelId = null;
            this.channelNameResourceId = 0;
            this.channelDescriptionResourceId = 0;
            return;
        }
        this.foregroundNotificationUpdater = new ForegroundNotificationUpdater(i3, j2);
        this.channelId = str;
        this.channelNameResourceId = i4;
        this.channelDescriptionResourceId = i5;
    }
}
