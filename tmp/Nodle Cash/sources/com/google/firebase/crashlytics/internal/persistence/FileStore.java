package com.google.firebase.crashlytics.internal.persistence;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.ProcessDetailsProvider;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileStore {
    private static final String CRASHLYTICS_PATH_V1 = ".com.google.firebase.crashlytics.files.v1";
    private static final String CRASHLYTICS_PATH_V2 = ".com.google.firebase.crashlytics.files.v2";
    private static final String CRASHLYTICS_PATH_V3 = ".crashlytics.v3";
    private static final String NATIVE_REPORTS_PATH = "native-reports";
    private static final String NATIVE_SESSION_SUBDIR = "native";
    private static final String PRIORITY_REPORTS_PATH = "priority-reports";
    private static final String REPORTS_PATH = "reports";
    private static final String SESSIONS_PATH = "open-sessions";
    private final File crashlyticsDir;
    private final File filesDir;
    private final File nativeReportsDir;
    private final File priorityReportsDir;
    final String processName;
    private final File reportsDir;
    private final File sessionsDir;

    public FileStore(Context context) {
        String str;
        String processName2 = ProcessDetailsProvider.INSTANCE.getCurrentProcessDetails(context).getProcessName();
        this.processName = processName2;
        File filesDir2 = context.getFilesDir();
        this.filesDir = filesDir2;
        if (useV3FileSystem()) {
            str = CRASHLYTICS_PATH_V3 + File.separator + sanitizeName(processName2);
        } else {
            str = CRASHLYTICS_PATH_V1;
        }
        File prepareBaseDir = prepareBaseDir(new File(filesDir2, str));
        this.crashlyticsDir = prepareBaseDir;
        this.sessionsDir = prepareBaseDir(new File(prepareBaseDir, SESSIONS_PATH));
        this.reportsDir = prepareBaseDir(new File(prepareBaseDir, REPORTS_PATH));
        this.priorityReportsDir = prepareBaseDir(new File(prepareBaseDir, PRIORITY_REPORTS_PATH));
        this.nativeReportsDir = prepareBaseDir(new File(prepareBaseDir, NATIVE_REPORTS_PATH));
    }

    private void cleanupFileSystemDir(String str) {
        File file = new File(this.filesDir, str);
        if (file.exists() && recursiveDelete(file)) {
            Logger logger = Logger.getLogger();
            logger.d("Deleted previous Crashlytics file system: " + file.getPath());
        }
    }

    private void cleanupFileSystemDirs(String str) {
        String[] list;
        if (this.filesDir.exists() && (list = this.filesDir.list(new b(str))) != null) {
            for (String cleanupFileSystemDir : list) {
                cleanupFileSystemDir(cleanupFileSystemDir);
            }
        }
    }

    private File getSessionDir(String str) {
        return prepareDir(new File(this.sessionsDir, str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004d, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.io.File prepareBaseDir(java.io.File r5) {
        /*
            java.lang.String r0 = "Could not create Crashlytics-specific directory: "
            java.lang.String r1 = "Unexpected non-directory file: "
            java.lang.Class<com.google.firebase.crashlytics.internal.persistence.FileStore> r2 = com.google.firebase.crashlytics.internal.persistence.FileStore.class
            monitor-enter(r2)
            boolean r3 = r5.exists()     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0033
            boolean r3 = r5.isDirectory()     // Catch:{ all -> 0x0031 }
            if (r3 == 0) goto L_0x0015
            monitor-exit(r2)
            return r5
        L_0x0015:
            com.google.firebase.crashlytics.internal.Logger r3 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r4.<init>(r1)     // Catch:{ all -> 0x0031 }
            r4.append(r5)     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = "; deleting file and creating new directory."
            r4.append(r1)     // Catch:{ all -> 0x0031 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x0031 }
            r3.d(r1)     // Catch:{ all -> 0x0031 }
            r5.delete()     // Catch:{ all -> 0x0031 }
            goto L_0x0033
        L_0x0031:
            r5 = move-exception
            goto L_0x004e
        L_0x0033:
            boolean r1 = r5.mkdirs()     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x004c
            com.google.firebase.crashlytics.internal.Logger r1 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0031 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0031 }
            r3.<init>(r0)     // Catch:{ all -> 0x0031 }
            r3.append(r5)     // Catch:{ all -> 0x0031 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0031 }
            r1.e(r0)     // Catch:{ all -> 0x0031 }
        L_0x004c:
            monitor-exit(r2)
            return r5
        L_0x004e:
            monitor-exit(r2)     // Catch:{ all -> 0x0031 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.persistence.FileStore.prepareBaseDir(java.io.File):java.io.File");
    }

    private static File prepareDir(File file) {
        file.mkdirs();
        return file;
    }

    public static boolean recursiveDelete(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File recursiveDelete : listFiles) {
                recursiveDelete(recursiveDelete);
            }
        }
        return file.delete();
    }

    private static <T> List<T> safeArrayToList(@Nullable T[] tArr) {
        return tArr == null ? Collections.emptyList() : Arrays.asList(tArr);
    }

    @VisibleForTesting
    public static String sanitizeName(String str) {
        return str.length() > 40 ? CommonUtils.sha1(str) : str.replaceAll("[^a-zA-Z0-9.]", "_");
    }

    private boolean useV3FileSystem() {
        return !this.processName.isEmpty();
    }

    public void cleanupPreviousFileSystems() {
        cleanupFileSystemDir(".com.google.firebase.crashlytics");
        cleanupFileSystemDir(".com.google.firebase.crashlytics-ndk");
        if (useV3FileSystem()) {
            cleanupFileSystemDir(CRASHLYTICS_PATH_V1);
            cleanupFileSystemDirs(CRASHLYTICS_PATH_V2 + File.pathSeparator);
        }
    }

    @VisibleForTesting
    public void deleteAllCrashlyticsFiles() {
        recursiveDelete(this.crashlyticsDir);
    }

    public boolean deleteSessionFiles(String str) {
        return recursiveDelete(new File(this.sessionsDir, str));
    }

    public List<String> getAllOpenSessionIds() {
        return safeArrayToList(this.sessionsDir.list());
    }

    public File getCommonFile(String str) {
        return new File(this.crashlyticsDir, str);
    }

    public List<File> getCommonFiles(FilenameFilter filenameFilter) {
        return safeArrayToList(this.crashlyticsDir.listFiles(filenameFilter));
    }

    public File getNativeReport(String str) {
        return new File(this.nativeReportsDir, str);
    }

    public List<File> getNativeReports() {
        return safeArrayToList(this.nativeReportsDir.listFiles());
    }

    public File getNativeSessionDir(String str) {
        return prepareDir(new File(getSessionDir(str), NATIVE_SESSION_SUBDIR));
    }

    public File getPriorityReport(String str) {
        return new File(this.priorityReportsDir, str);
    }

    public List<File> getPriorityReports() {
        return safeArrayToList(this.priorityReportsDir.listFiles());
    }

    public File getReport(String str) {
        return new File(this.reportsDir, str);
    }

    public List<File> getReports() {
        return safeArrayToList(this.reportsDir.listFiles());
    }

    public File getSessionFile(String str, String str2) {
        return new File(getSessionDir(str), str2);
    }

    public List<File> getSessionFiles(String str, FilenameFilter filenameFilter) {
        return safeArrayToList(getSessionDir(str).listFiles(filenameFilter));
    }
}
