package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.appsamurai.storyly.exoplayer2.common.a;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.ProcessDetailsProvider;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CrashlyticsReportDataCapture {
    private static final Map<String, Integer> ARCHITECTURES_BY_NAME;
    static final String GENERATOR = "Crashlytics Android SDK/19.1.0";
    static final int GENERATOR_TYPE = 3;
    static final int REPORT_ANDROID_PLATFORM = 4;
    static final int SESSION_ANDROID_PLATFORM = 3;
    static final String SIGNAL_DEFAULT = "0";
    private final AppData appData;
    private final Context context;
    private final IdManager idManager;
    private final ProcessDetailsProvider processDetailsProvider = ProcessDetailsProvider.INSTANCE;
    private final SettingsProvider settingsProvider;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;

    static {
        HashMap hashMap = new HashMap();
        ARCHITECTURES_BY_NAME = hashMap;
        a.c(5, hashMap, "armeabi", 6, "armeabi-v7a");
        a.c(9, hashMap, "arm64-v8a", 0, "x86");
        hashMap.put("x86_64", 1);
        Locale locale = Locale.US;
    }

    public CrashlyticsReportDataCapture(Context context2, IdManager idManager2, AppData appData2, StackTraceTrimmingStrategy stackTraceTrimmingStrategy2, SettingsProvider settingsProvider2) {
        this.context = context2;
        this.idManager = idManager2;
        this.appData = appData2;
        this.stackTraceTrimmingStrategy = stackTraceTrimmingStrategy2;
        this.settingsProvider = settingsProvider2;
    }

    private CrashlyticsReport.ApplicationExitInfo addBuildIdInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        List list;
        if (!this.settingsProvider.getSettingsSync().featureFlagData.collectBuildIds || this.appData.buildIdInfoList.size() <= 0) {
            list = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (BuildIdInfo next : this.appData.buildIdInfoList) {
                arrayList.add(CrashlyticsReport.ApplicationExitInfo.BuildIdMappingForArch.builder().setLibraryName(next.getLibraryName()).setArch(next.getArch()).setBuildId(next.getBuildId()).build());
            }
            list = Collections.unmodifiableList(arrayList);
        }
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(applicationExitInfo.getImportance()).setProcessName(applicationExitInfo.getProcessName()).setReasonCode(applicationExitInfo.getReasonCode()).setTimestamp(applicationExitInfo.getTimestamp()).setPid(applicationExitInfo.getPid()).setPss(applicationExitInfo.getPss()).setRss(applicationExitInfo.getRss()).setTraceFile(applicationExitInfo.getTraceFile()).setBuildIdMappingForArch(list).build();
    }

    private CrashlyticsReport.Builder buildReportData() {
        return CrashlyticsReport.builder().setSdkVersion(BuildConfig.VERSION_NAME).setGmpAppId(this.appData.googleAppId).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setFirebaseInstallationId(this.idManager.getInstallIds().getFirebaseInstallationId()).setFirebaseAuthenticationToken(this.idManager.getInstallIds().getFirebaseAuthenticationToken()).setBuildVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setPlatform(4);
    }

    private static long ensureNonNegative(long j2) {
        if (j2 > 0) {
            return j2;
        }
        return 0;
    }

    private static int getDeviceArchitecture() {
        Integer num;
        String str = Build.CPU_ABI;
        if (!TextUtils.isEmpty(str) && (num = ARCHITECTURES_BY_NAME.get(str.toLowerCase(Locale.US))) != null) {
            return num.intValue();
        }
        return 7;
    }

    private CrashlyticsReport.Session.Event.Application.Execution.BinaryImage populateBinaryImageData() {
        return CrashlyticsReport.Session.Event.Application.Execution.BinaryImage.builder().setBaseAddress(0).setSize(0).setName(this.appData.packageName).setUuid(this.appData.buildId).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.BinaryImage> populateBinaryImagesList() {
        return Collections.singletonList(populateBinaryImageData());
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i3, TrimmedThrowableData trimmedThrowableData, Thread thread, int i4, int i5, boolean z2) {
        Boolean bool;
        CrashlyticsReport.Session.Event.Application.ProcessDetails currentProcessDetails = this.processDetailsProvider.getCurrentProcessDetails(this.context);
        if (currentProcessDetails.getImportance() > 0) {
            bool = Boolean.valueOf(currentProcessDetails.getImportance() != 100);
        } else {
            bool = null;
        }
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(bool).setCurrentProcessDetails(currentProcessDetails).setAppProcessDetails(this.processDetailsProvider.getAppProcessDetails(this.context)).setUiOrientation(i3).setExecution(populateExecutionData(trimmedThrowableData, thread, i4, i5, z2)).build();
    }

    private CrashlyticsReport.Session.Event.Device populateEventDeviceData(int i3) {
        BatteryState batteryState = BatteryState.get(this.context);
        Float batteryLevel = batteryState.getBatteryLevel();
        Double valueOf = batteryLevel != null ? Double.valueOf(batteryLevel.doubleValue()) : null;
        int batteryVelocity = batteryState.getBatteryVelocity();
        boolean proximitySensorEnabled = CommonUtils.getProximitySensorEnabled(this.context);
        return CrashlyticsReport.Session.Event.Device.builder().setBatteryLevel(valueOf).setBatteryVelocity(batteryVelocity).setProximityOn(proximitySensorEnabled).setOrientation(i3).setRamUsed(ensureNonNegative(CommonUtils.calculateTotalRamInBytes(this.context) - CommonUtils.calculateFreeRamInBytes(this.context))).setDiskUsed(CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath())).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i3, int i4) {
        return populateExceptionData(trimmedThrowableData, i3, i4, 0);
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(TrimmedThrowableData trimmedThrowableData, Thread thread, int i3, int i4, boolean z2) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setThreads(populateThreadsList(trimmedThrowableData, thread, i3, z2)).setException(populateExceptionData(trimmedThrowableData, i3, i4)).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame populateFrameData(StackTraceElement stackTraceElement, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.Builder builder) {
        long j2 = 0;
        long max = stackTraceElement.isNativeMethod() ? Math.max((long) stackTraceElement.getLineNumber(), 0) : 0;
        String str = stackTraceElement.getClassName() + JwtUtilsKt.JWT_DELIMITER + stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        if (!stackTraceElement.isNativeMethod() && stackTraceElement.getLineNumber() > 0) {
            j2 = (long) stackTraceElement.getLineNumber();
        }
        return builder.setPc(max).setSymbol(str).setFile(fileName).setOffset(j2).build();
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame> populateFramesList(StackTraceElement[] stackTraceElementArr, int i3) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement populateFrameData : stackTraceElementArr) {
            arrayList.add(populateFrameData(populateFrameData, CrashlyticsReport.Session.Event.Application.Execution.Thread.Frame.builder().setImportance(i3)));
        }
        return Collections.unmodifiableList(arrayList);
    }

    private CrashlyticsReport.Session.Application populateSessionApplicationData() {
        return CrashlyticsReport.Session.Application.builder().setIdentifier(this.idManager.getAppIdentifier()).setVersion(this.appData.versionCode).setDisplayVersion(this.appData.versionName).setInstallationUuid(this.idManager.getInstallIds().getCrashlyticsInstallId()).setDevelopmentPlatform(this.appData.developmentPlatformProvider.getDevelopmentPlatform()).setDevelopmentPlatformVersion(this.appData.developmentPlatformProvider.getDevelopmentPlatformVersion()).build();
    }

    private CrashlyticsReport.Session populateSessionData(String str, long j2) {
        return CrashlyticsReport.Session.builder().setStartedAt(j2).setIdentifier(str).setGenerator(GENERATOR).setApp(populateSessionApplicationData()).setOs(populateSessionOperatingSystemData()).setDevice(populateSessionDeviceData()).setGeneratorType(3).build();
    }

    private CrashlyticsReport.Session.Device populateSessionDeviceData() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        int deviceArchitecture = getDeviceArchitecture();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        long calculateTotalRamInBytes = CommonUtils.calculateTotalRamInBytes(this.context);
        long blockCount = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        boolean isEmulator = CommonUtils.isEmulator();
        int deviceState = CommonUtils.getDeviceState();
        String str = Build.MANUFACTURER;
        return CrashlyticsReport.Session.Device.builder().setArch(deviceArchitecture).setModel(Build.MODEL).setCores(availableProcessors).setRam(calculateTotalRamInBytes).setDiskSpace(blockCount).setSimulator(isEmulator).setState(deviceState).setManufacturer(str).setModelClass(Build.PRODUCT).build();
    }

    private CrashlyticsReport.Session.OperatingSystem populateSessionOperatingSystemData() {
        return CrashlyticsReport.Session.OperatingSystem.builder().setPlatform(3).setVersion(Build.VERSION.RELEASE).setBuildVersion(Build.VERSION.CODENAME).setJailbroken(CommonUtils.isRooted()).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Signal populateSignalData() {
        return CrashlyticsReport.Session.Event.Application.Execution.Signal.builder().setName("0").setCode("0").setAddress(0).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr) {
        return populateThreadData(thread, stackTraceElementArr, 0);
    }

    private List<CrashlyticsReport.Session.Event.Application.Execution.Thread> populateThreadsList(TrimmedThrowableData trimmedThrowableData, Thread thread, int i3, boolean z2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(populateThreadData(thread, trimmedThrowableData.stacktrace, i3));
        if (z2) {
            for (Map.Entry next : Thread.getAllStackTraces().entrySet()) {
                Thread thread2 = (Thread) next.getKey();
                if (!thread2.equals(thread)) {
                    arrayList.add(populateThreadData(thread2, this.stackTraceTrimmingStrategy.getTrimmedStackTrace((StackTraceElement[]) next.getValue())));
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    private CrashlyticsReport.Session.Event.Application.ProcessDetails processDetailsFromApplicationExitInfo(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return this.processDetailsProvider.buildProcessDetails(applicationExitInfo.getProcessName(), applicationExitInfo.getPid(), applicationExitInfo.getImportance());
    }

    public CrashlyticsReport.Session.Event captureAnrEventData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        int i3 = this.context.getResources().getConfiguration().orientation;
        return CrashlyticsReport.Session.Event.builder().setType("anr").setTimestamp(applicationExitInfo.getTimestamp()).setApp(populateEventApplicationData(i3, addBuildIdInfo(applicationExitInfo))).setDevice(populateEventDeviceData(i3)).build();
    }

    public CrashlyticsReport.Session.Event captureEventData(Throwable th, Thread thread, String str, long j2, int i3, int i4, boolean z2) {
        int i5 = this.context.getResources().getConfiguration().orientation;
        Throwable th2 = th;
        String str2 = str;
        long j3 = j2;
        return CrashlyticsReport.Session.Event.builder().setType(str).setTimestamp(j2).setApp(populateEventApplicationData(i5, TrimmedThrowableData.makeTrimmedThrowableData(th, this.stackTraceTrimmingStrategy), thread, i3, i4, z2)).setDevice(populateEventDeviceData(i5)).build();
    }

    public CrashlyticsReport captureReportData(String str, long j2) {
        return buildReportData().setSession(populateSessionData(str, j2)).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Exception populateExceptionData(TrimmedThrowableData trimmedThrowableData, int i3, int i4, int i5) {
        String str = trimmedThrowableData.className;
        String str2 = trimmedThrowableData.localizedMessage;
        StackTraceElement[] stackTraceElementArr = trimmedThrowableData.stacktrace;
        int i6 = 0;
        if (stackTraceElementArr == null) {
            stackTraceElementArr = new StackTraceElement[0];
        }
        TrimmedThrowableData trimmedThrowableData2 = trimmedThrowableData.cause;
        if (i5 >= i4) {
            TrimmedThrowableData trimmedThrowableData3 = trimmedThrowableData2;
            while (trimmedThrowableData3 != null) {
                trimmedThrowableData3 = trimmedThrowableData3.cause;
                i6++;
            }
        }
        CrashlyticsReport.Session.Event.Application.Execution.Exception.Builder overflowCount = CrashlyticsReport.Session.Event.Application.Execution.Exception.builder().setType(str).setReason(str2).setFrames(populateFramesList(stackTraceElementArr, i3)).setOverflowCount(i6);
        if (trimmedThrowableData2 != null && i6 == 0) {
            overflowCount.setCausedBy(populateExceptionData(trimmedThrowableData2, i3, i4, i5 + 1));
        }
        return overflowCount.build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution.Thread populateThreadData(Thread thread, StackTraceElement[] stackTraceElementArr, int i3) {
        return CrashlyticsReport.Session.Event.Application.Execution.Thread.builder().setName(thread.getName()).setImportance(i3).setFrames(populateFramesList(stackTraceElementArr, i3)).build();
    }

    private CrashlyticsReport.Session.Event.Application.Execution populateExecutionData(CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.Execution.builder().setAppExitInfo(applicationExitInfo).setSignal(populateSignalData()).setBinaries(populateBinaryImagesList()).build();
    }

    private CrashlyticsReport.Session.Event.Application populateEventApplicationData(int i3, CrashlyticsReport.ApplicationExitInfo applicationExitInfo) {
        return CrashlyticsReport.Session.Event.Application.builder().setBackground(Boolean.valueOf(applicationExitInfo.getImportance() != 100)).setCurrentProcessDetails(processDetailsFromApplicationExitInfo(applicationExitInfo)).setUiOrientation(i3).setExecution(populateExecutionData(applicationExitInfo)).build();
    }
}
