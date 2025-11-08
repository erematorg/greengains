package com.getkeepsafe.relinker;

import android.content.Context;
import android.support.v4.media.session.a;
import com.getkeepsafe.relinker.ReLinker;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ReLinkerInstance {
    private static final String LIB_DIR = "lib";
    protected boolean force;
    protected final ReLinker.LibraryInstaller libraryInstaller;
    protected final ReLinker.LibraryLoader libraryLoader;
    protected final Set<String> loadedLibraries;
    protected ReLinker.Logger logger;
    protected boolean recursive;

    public ReLinkerInstance() {
        this(new SystemLibraryLoader(), new ApkLibraryInstaller());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a4 A[Catch:{ IOException -> 0x00a8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadLibraryInternal(android.content.Context r9, java.lang.String r10, java.lang.String r11) {
        /*
            r8 = this;
            java.util.Set<java.lang.String> r0 = r8.loadedLibraries
            boolean r0 = r0.contains(r10)
            if (r0 == 0) goto L_0x0016
            boolean r0 = r8.force
            if (r0 != 0) goto L_0x0016
            java.lang.String r9 = "%s already loaded previously!"
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            r8.log(r9, r10)
            return
        L_0x0016:
            com.getkeepsafe.relinker.ReLinker$LibraryLoader r0 = r8.libraryLoader     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            r0.loadLibrary(r10)     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            java.util.Set<java.lang.String> r0 = r8.loadedLibraries     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            r0.add(r10)     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            java.lang.String r0 = "%s (%s) was loaded normally!"
            java.lang.Object[] r1 = new java.lang.Object[]{r10, r11}     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            r8.log(r0, r1)     // Catch:{ UnsatisfiedLinkError -> 0x002a }
            return
        L_0x002a:
            r0 = move-exception
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "Loading the library normally failed: %s"
            r8.log(r1, r0)
            java.lang.String r0 = "%s (%s) was not loaded normally, re-linking..."
            java.lang.Object[] r1 = new java.lang.Object[]{r10, r11}
            r8.log(r0, r1)
            java.io.File r0 = r8.getWorkaroundLibFile(r9, r10, r11)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x004f
            boolean r1 = r8.force
            if (r1 == 0) goto L_0x0073
        L_0x004f:
            boolean r1 = r8.force
            if (r1 == 0) goto L_0x005c
            java.lang.String r1 = "Forcing a re-link of %s (%s)..."
            java.lang.Object[] r2 = new java.lang.Object[]{r10, r11}
            r8.log(r1, r2)
        L_0x005c:
            r8.cleanupOldLibFiles(r9, r10, r11)
            com.getkeepsafe.relinker.ReLinker$LibraryInstaller r2 = r8.libraryInstaller
            com.getkeepsafe.relinker.ReLinker$LibraryLoader r1 = r8.libraryLoader
            java.lang.String[] r4 = r1.supportedAbis()
            com.getkeepsafe.relinker.ReLinker$LibraryLoader r1 = r8.libraryLoader
            java.lang.String r5 = r1.mapLibraryName(r10)
            r3 = r9
            r6 = r0
            r7 = r8
            r2.installLibrary(r3, r4, r5, r6, r7)
        L_0x0073:
            boolean r1 = r8.recursive     // Catch:{ IOException -> 0x00a8 }
            if (r1 == 0) goto L_0x00a8
            r1 = 0
            com.getkeepsafe.relinker.elf.ElfParser r2 = new com.getkeepsafe.relinker.elf.ElfParser     // Catch:{ all -> 0x00a1 }
            r2.<init>(r0)     // Catch:{ all -> 0x00a1 }
            java.util.List r1 = r2.parseNeededDependencies()     // Catch:{ all -> 0x009e }
            r2.close()     // Catch:{ IOException -> 0x00a8 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ IOException -> 0x00a8 }
        L_0x0088:
            boolean r2 = r1.hasNext()     // Catch:{ IOException -> 0x00a8 }
            if (r2 == 0) goto L_0x00a8
            java.lang.Object r2 = r1.next()     // Catch:{ IOException -> 0x00a8 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00a8 }
            com.getkeepsafe.relinker.ReLinker$LibraryLoader r3 = r8.libraryLoader     // Catch:{ IOException -> 0x00a8 }
            java.lang.String r2 = r3.unmapLibraryName(r2)     // Catch:{ IOException -> 0x00a8 }
            r8.loadLibrary(r9, r2)     // Catch:{ IOException -> 0x00a8 }
            goto L_0x0088
        L_0x009e:
            r9 = move-exception
            r1 = r2
            goto L_0x00a2
        L_0x00a1:
            r9 = move-exception
        L_0x00a2:
            if (r1 == 0) goto L_0x00a7
            r1.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a7:
            throw r9     // Catch:{ IOException -> 0x00a8 }
        L_0x00a8:
            com.getkeepsafe.relinker.ReLinker$LibraryLoader r9 = r8.libraryLoader
            java.lang.String r0 = r0.getAbsolutePath()
            r9.loadPath(r0)
            java.util.Set<java.lang.String> r9 = r8.loadedLibraries
            r9.add(r10)
            java.lang.String r9 = "%s (%s) was re-linked!"
            java.lang.Object[] r10 = new java.lang.Object[]{r10, r11}
            r8.log(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getkeepsafe.relinker.ReLinkerInstance.loadLibraryInternal(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public void cleanupOldLibFiles(Context context, String str, String str2) {
        File workaroundLibDir = getWorkaroundLibDir(context);
        File workaroundLibFile = getWorkaroundLibFile(context, str, str2);
        final String mapLibraryName = this.libraryLoader.mapLibraryName(str);
        File[] listFiles = workaroundLibDir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(mapLibraryName);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (this.force || !file.getAbsolutePath().equals(workaroundLibFile.getAbsolutePath())) {
                    file.delete();
                }
            }
        }
    }

    public ReLinkerInstance force() {
        this.force = true;
        return this;
    }

    public File getWorkaroundLibDir(Context context) {
        return context.getDir(LIB_DIR, 0);
    }

    public File getWorkaroundLibFile(Context context, String str, String str2) {
        String mapLibraryName = this.libraryLoader.mapLibraryName(str);
        return TextUtils.isEmpty(str2) ? new File(getWorkaroundLibDir(context), mapLibraryName) : new File(getWorkaroundLibDir(context), a.n(mapLibraryName, JwtUtilsKt.JWT_DELIMITER, str2));
    }

    public void loadLibrary(Context context, String str) {
        loadLibrary(context, str, (String) null, (ReLinker.LoadListener) null);
    }

    public ReLinkerInstance log(ReLinker.Logger logger2) {
        this.logger = logger2;
        return this;
    }

    public ReLinkerInstance recursively() {
        this.recursive = true;
        return this;
    }

    public ReLinkerInstance(ReLinker.LibraryLoader libraryLoader2, ReLinker.LibraryInstaller libraryInstaller2) {
        this.loadedLibraries = new HashSet();
        if (libraryLoader2 == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        } else if (libraryInstaller2 != null) {
            this.libraryLoader = libraryLoader2;
            this.libraryInstaller = libraryInstaller2;
        } else {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
    }

    public void loadLibrary(Context context, String str, String str2) {
        loadLibrary(context, str, str2, (ReLinker.LoadListener) null);
    }

    public void log(String str, Object... objArr) {
        log(String.format(Locale.US, str, objArr));
    }

    public void loadLibrary(Context context, String str, ReLinker.LoadListener loadListener) {
        loadLibrary(context, str, (String) null, loadListener);
    }

    public void log(String str) {
        ReLinker.Logger logger2 = this.logger;
        if (logger2 != null) {
            logger2.log(str);
        }
    }

    public void loadLibrary(Context context, String str, String str2, ReLinker.LoadListener loadListener) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        } else if (!TextUtils.isEmpty(str)) {
            log("Beginning load of %s...", str);
            if (loadListener == null) {
                loadLibraryInternal(context, str, str2);
                return;
            }
            final Context context2 = context;
            final String str3 = str;
            final String str4 = str2;
            final ReLinker.LoadListener loadListener2 = loadListener;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ReLinkerInstance.this.loadLibraryInternal(context2, str3, str4);
                        loadListener2.success();
                    } catch (UnsatisfiedLinkError e3) {
                        loadListener2.failure(e3);
                    } catch (MissingLibraryException e4) {
                        loadListener2.failure(e4);
                    }
                }
            }).start();
        } else {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
    }
}
