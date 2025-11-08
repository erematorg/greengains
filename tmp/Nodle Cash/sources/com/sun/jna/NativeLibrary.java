package com.sun.jna;

import android.support.v4.media.session.a;
import androidx.camera.camera2.internal.C0118y;
import androidx.emoji2.emojipicker.StickyVariantProvider;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import com.sun.jna.internal.Cleaner;
import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeLibrary implements Closeable {
    private static final Level DEBUG_LOAD_LEVEL = (Native.DEBUG_LOAD ? Level.INFO : Level.FINE);
    private static final int DEFAULT_OPEN_OPTIONS = -1;
    private static final Logger LOG;
    private static final SymbolProvider NATIVE_SYMBOL_PROVIDER = new SymbolProvider() {
        public long getSymbolAddress(long j2, String str, SymbolProvider symbolProvider) {
            return Native.findSymbol(j2, str);
        }
    };
    private static Method addSuppressedMethod;
    private static final Map<String, Reference<NativeLibrary>> libraries = new HashMap();
    private static final LinkedHashSet<String> librarySearchPath = new LinkedHashSet<>();
    private static final Map<String, List<String>> searchPaths = new ConcurrentHashMap();
    private final int callFlags;
    private final Cleaner.Cleanable cleanable;
    private final String encoding;
    private final Map<String, Function> functions;
    private volatile long handle;
    private final String libraryName;
    private final String libraryPath;
    private final Map<String, ?> options;
    private final SymbolProvider symbolProvider;

    public static final class NativeLibraryDisposer implements Runnable {
        private long handle;

        public NativeLibraryDisposer(long j2) {
            this.handle = j2;
        }

        public synchronized void run() {
            try {
                long j2 = this.handle;
                if (j2 != 0) {
                    Native.close(j2);
                    this.handle = 0;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            r0 = -1
            r1 = 1
            java.lang.Class<java.lang.Throwable> r2 = java.lang.Throwable.class
            java.lang.Class<com.sun.jna.NativeLibrary> r3 = com.sun.jna.NativeLibrary.class
            java.lang.String r4 = r3.getName()
            java.util.logging.Logger r4 = java.util.logging.Logger.getLogger(r4)
            LOG = r4
            boolean r4 = com.sun.jna.Native.DEBUG_LOAD
            if (r4 == 0) goto L_0x0017
            java.util.logging.Level r4 = java.util.logging.Level.INFO
            goto L_0x0019
        L_0x0017:
            java.util.logging.Level r4 = java.util.logging.Level.FINE
        L_0x0019:
            DEBUG_LOAD_LEVEL = r4
            com.sun.jna.NativeLibrary$1 r4 = new com.sun.jna.NativeLibrary$1
            r4.<init>()
            NATIVE_SYMBOL_PROVIDER = r4
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            libraries = r4
            java.util.concurrent.ConcurrentHashMap r4 = new java.util.concurrent.ConcurrentHashMap
            r4.<init>()
            searchPaths = r4
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            librarySearchPath = r4
            int r4 = com.sun.jna.Native.POINTER_SIZE
            if (r4 == 0) goto L_0x016a
            r4 = 0
            addSuppressedMethod = r4
            java.lang.String r4 = "addSuppressed"
            java.lang.Class[] r5 = new java.lang.Class[]{r2}     // Catch:{ NoSuchMethodException -> 0x005b, SecurityException -> 0x004b }
            java.lang.reflect.Method r2 = r2.getMethod(r4, r5)     // Catch:{ NoSuchMethodException -> 0x005b, SecurityException -> 0x004b }
            addSuppressedMethod = r2     // Catch:{ NoSuchMethodException -> 0x005b, SecurityException -> 0x004b }
            goto L_0x005b
        L_0x004b:
            r2 = move-exception
            java.lang.String r3 = r3.getName()
            java.util.logging.Logger r3 = java.util.logging.Logger.getLogger(r3)
            java.util.logging.Level r4 = java.util.logging.Level.SEVERE
            java.lang.String r5 = "Failed to initialize 'addSuppressed' method"
            r3.log(r4, r5, r2)
        L_0x005b:
            java.lang.String r2 = "jnidispatch"
            java.lang.String r2 = com.sun.jna.Native.getWebStartLibraryPath(r2)
            if (r2 == 0) goto L_0x0068
            java.util.LinkedHashSet<java.lang.String> r3 = librarySearchPath
            r3.add(r2)
        L_0x0068:
            java.lang.String r2 = "jna.platform.library.path"
            java.lang.String r3 = java.lang.System.getProperty(r2)
            if (r3 != 0) goto L_0x0160
            boolean r3 = com.sun.jna.Platform.isWindows()
            if (r3 != 0) goto L_0x0160
            boolean r3 = com.sun.jna.Platform.isLinux()
            java.lang.String r4 = ""
            if (r3 != 0) goto L_0x0093
            boolean r3 = com.sun.jna.Platform.isSolaris()
            if (r3 != 0) goto L_0x0093
            boolean r3 = com.sun.jna.Platform.isFreeBSD()
            if (r3 != 0) goto L_0x0093
            boolean r3 = com.sun.jna.Platform.iskFreeBSD()
            if (r3 == 0) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r3 = r4
            goto L_0x00b0
        L_0x0093:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            boolean r5 = com.sun.jna.Platform.isSolaris()
            if (r5 == 0) goto L_0x00a1
            java.lang.String r5 = "/"
            goto L_0x00a2
        L_0x00a1:
            r5 = r4
        L_0x00a2:
            r3.append(r5)
            int r5 = com.sun.jna.Native.POINTER_SIZE
            int r5 = r5 * 8
            r3.append(r5)
            java.lang.String r3 = r3.toString()
        L_0x00b0:
            r5 = 4
            java.lang.String[] r6 = new java.lang.String[r5]
            java.lang.String r7 = "/usr/lib"
            java.lang.String r8 = androidx.browser.trusted.c.a(r7, r3)
            r9 = 0
            r6[r9] = r8
            java.lang.String r8 = "/lib"
            java.lang.String r10 = androidx.browser.trusted.c.a(r8, r3)
            r6[r1] = r10
            r10 = 2
            r6[r10] = r7
            r11 = 3
            r6[r11] = r8
            boolean r12 = com.sun.jna.Platform.isLinux()
            if (r12 != 0) goto L_0x00dc
            boolean r12 = com.sun.jna.Platform.iskFreeBSD()
            if (r12 != 0) goto L_0x00dc
            boolean r12 = com.sun.jna.Platform.isGNU()
            if (r12 == 0) goto L_0x0105
        L_0x00dc:
            java.lang.String r6 = getMultiArchPath()
            r12 = 6
            java.lang.String[] r12 = new java.lang.String[r12]
            java.lang.String r13 = "/usr/lib/"
            java.lang.String r13 = androidx.browser.trusted.c.a(r13, r6)
            r12[r9] = r13
            java.lang.String r13 = "/lib/"
            java.lang.String r6 = androidx.browser.trusted.c.a(r13, r6)
            r12[r1] = r6
            java.lang.String r6 = androidx.browser.trusted.c.a(r7, r3)
            r12[r10] = r6
            java.lang.String r3 = androidx.browser.trusted.c.a(r8, r3)
            r12[r11] = r3
            r12[r5] = r7
            r3 = 5
            r12[r3] = r8
            r6 = r12
        L_0x0105:
            boolean r3 = com.sun.jna.Platform.isLinux()
            if (r3 == 0) goto L_0x012e
            java.util.ArrayList r3 = getLinuxLdPaths()
            int r5 = r6.length
            int r5 = r5 - r1
        L_0x0111:
            if (r5 < 0) goto L_0x0125
            r7 = r6[r5]
            int r7 = r3.indexOf(r7)
            if (r7 == r0) goto L_0x011e
            r3.remove(r7)
        L_0x011e:
            r7 = r6[r5]
            r3.add(r9, r7)
            int r5 = r5 + r0
            goto L_0x0111
        L_0x0125:
            java.lang.String[] r0 = new java.lang.String[r9]
            java.lang.Object[] r0 = r3.toArray(r0)
            r6 = r0
            java.lang.String[] r6 = (java.lang.String[]) r6
        L_0x012e:
            r0 = r4
            r3 = r0
        L_0x0130:
            int r5 = r6.length
            if (r9 >= r5) goto L_0x0157
            java.io.File r5 = new java.io.File
            r7 = r6[r9]
            r5.<init>(r7)
            boolean r7 = r5.exists()
            if (r7 == 0) goto L_0x0155
            boolean r5 = r5.isDirectory()
            if (r5 == 0) goto L_0x0155
            java.lang.StringBuilder r0 = A.a.q(r0, r3)
            r3 = r6[r9]
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r3 = java.io.File.pathSeparator
        L_0x0155:
            int r9 = r9 + r1
            goto L_0x0130
        L_0x0157:
            boolean r1 = r4.equals(r0)
            if (r1 != 0) goto L_0x0160
            java.lang.System.setProperty(r2, r0)
        L_0x0160:
            java.util.LinkedHashSet<java.lang.String> r0 = librarySearchPath
            java.util.List r1 = initPaths(r2)
            r0.addAll(r1)
            return
        L_0x016a:
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "Native library not initialized"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.NativeLibrary.<clinit>():void");
    }

    private NativeLibrary(String str, String str2, long j2, Map<String, ?> map) {
        HashMap hashMap = new HashMap();
        this.functions = hashMap;
        String libraryName2 = getLibraryName(str);
        this.libraryName = libraryName2;
        this.libraryPath = str2;
        this.handle = j2;
        this.cleanable = Cleaner.getCleaner().register(this, new NativeLibraryDisposer(j2));
        Object obj = map.get(Library.OPTION_CALLING_CONVENTION);
        int intValue = obj instanceof Number ? ((Number) obj).intValue() : 0;
        this.callFlags = intValue;
        this.options = map;
        SymbolProvider symbolProvider2 = (SymbolProvider) map.get(Library.OPTION_SYMBOL_PROVIDER);
        if (symbolProvider2 == null) {
            this.symbolProvider = NATIVE_SYMBOL_PROVIDER;
        } else {
            this.symbolProvider = symbolProvider2;
        }
        String str3 = (String) map.get(Library.OPTION_STRING_ENCODING);
        str3 = str3 == null ? Native.getDefaultStringEncoding() : str3;
        this.encoding = str3;
        if (Platform.isWindows() && "kernel32".equals(libraryName2.toLowerCase())) {
            synchronized (hashMap) {
                hashMap.put(functionKey("GetLastError", intValue, str3), new Function(this, "GetLastError", 63, str3) {
                    public Object invoke(Object[] objArr, Class<?> cls, boolean z2, int i3) {
                        return Integer.valueOf(Native.getLastError());
                    }

                    public Object invoke(Method method, Class<?>[] clsArr, Class<?> cls, Object[] objArr, Map<String, ?> map) {
                        return Integer.valueOf(Native.getLastError());
                    }
                });
            }
        }
    }

    public static final void addSearchPath(String str, String str2) {
        Map<String, List<String>> map = searchPaths;
        List list = map.get(str);
        if (list == null) {
            list = Collections.synchronizedList(new ArrayList());
            map.put(str, list);
        }
        list.add(str2);
    }

    private static void addSuppressedReflected(Throwable th, Throwable th2) {
        Method method = addSuppressedMethod;
        if (method != null) {
            try {
                method.invoke(th, new Object[]{th2});
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e3) {
                throw new RuntimeException("Failed to call addSuppressedMethod", e3);
            }
        }
    }

    public static void disposeAll() {
        LinkedHashSet<Reference> linkedHashSet;
        Map<String, Reference<NativeLibrary>> map = libraries;
        synchronized (map) {
            linkedHashSet = new LinkedHashSet<>(map.values());
        }
        for (Reference reference : linkedHashSet) {
            NativeLibrary nativeLibrary = (NativeLibrary) reference.get();
            if (nativeLibrary != null) {
                nativeLibrary.close();
            }
        }
    }

    private static String findLibraryPath(String str, Collection<String> collection) {
        if (new File(str).isAbsolute()) {
            return str;
        }
        String mapSharedLibraryName = mapSharedLibraryName(str);
        for (String next : collection) {
            File file = new File(next, mapSharedLibraryName);
            if (file.exists()) {
                return file.getAbsolutePath();
            }
            if (Platform.isMac() && mapSharedLibraryName.endsWith(".dylib")) {
                File file2 = new File(next, mapSharedLibraryName.substring(0, mapSharedLibraryName.lastIndexOf(".dylib")) + ".jnilib");
                if (file2.exists()) {
                    return file2.getAbsolutePath();
                }
            }
        }
        return mapSharedLibraryName;
    }

    private static String functionKey(String str, int i3, String str2) {
        return str + StickyVariantProvider.ENTRY_DELIMITER + i3 + StickyVariantProvider.ENTRY_DELIMITER + str2;
    }

    public static final NativeLibrary getInstance(String str) {
        return getInstance(str, (Map<String, ?>) Collections.emptyMap());
    }

    private String getLibraryName(String str) {
        String mapSharedLibraryName = mapSharedLibraryName("---");
        int indexOf = mapSharedLibraryName.indexOf("---");
        if (indexOf > 0 && str.startsWith(mapSharedLibraryName.substring(0, indexOf))) {
            str = str.substring(indexOf);
        }
        int indexOf2 = str.indexOf(mapSharedLibraryName.substring(indexOf + 3));
        return indexOf2 != -1 ? str.substring(0, indexOf2) : str;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:19|20|21|22|43) */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x006a, code lost:
        if (r2 == null) goto L_0x006d;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c A[SYNTHETIC, Splitter:B:29:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0061 A[SYNTHETIC, Splitter:B:33:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0067 A[SYNTHETIC, Splitter:B:39:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<java.lang.String> getLinuxLdPaths() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.lang.String r3 = "/sbin/ldconfig -p"
            java.lang.Process r2 = r2.exec(r3)     // Catch:{ Exception -> 0x0058, all -> 0x0055 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0065, all -> 0x0053 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0065, all -> 0x0053 }
            java.io.InputStream r5 = r2.getInputStream()     // Catch:{ Exception -> 0x0065, all -> 0x0053 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0065, all -> 0x0053 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0065, all -> 0x0053 }
        L_0x001e:
            java.lang.String r1 = r3.readLine()     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            if (r1 == 0) goto L_0x004c
            java.lang.String r4 = " => "
            int r4 = r1.indexOf(r4)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            r5 = 47
            int r5 = r1.lastIndexOf(r5)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            r6 = -1
            if (r4 == r6) goto L_0x001e
            if (r5 == r6) goto L_0x001e
            if (r4 >= r5) goto L_0x001e
            int r4 = r4 + 4
            java.lang.String r1 = r1.substring(r4, r5)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            boolean r4 = r0.contains(r1)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            if (r4 != 0) goto L_0x001e
            r0.add(r1)     // Catch:{ Exception -> 0x004a, all -> 0x0047 }
            goto L_0x001e
        L_0x0047:
            r0 = move-exception
            r1 = r3
            goto L_0x005a
        L_0x004a:
            r1 = r3
            goto L_0x0065
        L_0x004c:
            r3.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            r2.waitFor()     // Catch:{ InterruptedException -> 0x006d }
            goto L_0x006d
        L_0x0053:
            r0 = move-exception
            goto L_0x005a
        L_0x0055:
            r0 = move-exception
            r2 = r1
            goto L_0x005a
        L_0x0058:
            r2 = r1
            goto L_0x0065
        L_0x005a:
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ IOException -> 0x005f }
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.waitFor()     // Catch:{ InterruptedException -> 0x0064 }
        L_0x0064:
            throw r0
        L_0x0065:
            if (r1 == 0) goto L_0x006a
            r1.close()     // Catch:{ IOException -> 0x006a }
        L_0x006a:
            if (r2 == 0) goto L_0x006d
            goto L_0x004f
        L_0x006d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.NativeLibrary.getLinuxLdPaths():java.util.ArrayList");
    }

    private static String getMultiArchPath() {
        String str = Platform.ARCH;
        String str2 = Platform.iskFreeBSD() ? "-kfreebsd" : Platform.isGNU() ? "" : "-linux";
        String str3 = "-gnu";
        if (Platform.isIntel()) {
            str = Platform.is64Bit() ? "x86_64" : "i386";
        } else if (Platform.isPPC()) {
            str = Platform.is64Bit() ? "powerpc64" : "powerpc";
        } else if (Platform.isARM()) {
            str = "arm";
            str3 = "-gnueabi";
        } else if (str.equals("mips64el")) {
            str3 = "-gnuabi64";
        }
        return a.n(str, str2, str3);
    }

    public static final synchronized NativeLibrary getProcess() {
        NativeLibrary instance;
        synchronized (NativeLibrary.class) {
            instance = getInstance((String) null);
        }
        return instance;
    }

    private static List<String> initPaths(String str) {
        String property = System.getProperty(str, "");
        if ("".equals(property)) {
            return Collections.emptyList();
        }
        StringTokenizer stringTokenizer = new StringTokenizer(property, File.pathSeparator);
        ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (!"".equals(nextToken)) {
                arrayList.add(nextToken);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public static boolean isVersionedName(String str) {
        int lastIndexOf;
        int i3;
        if (!str.startsWith("lib") || (lastIndexOf = str.lastIndexOf(".so.")) == -1 || (i3 = lastIndexOf + 4) >= str.length()) {
            return false;
        }
        for (i3 = lastIndexOf + 4; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (!Character.isDigit(charAt) && charAt != '.') {
                return false;
            }
        }
        return true;
    }

    private static NativeLibrary loadLibrary(String str, Map<String, ?> map) {
        long j2;
        File extractFromResourcePath;
        String str2 = str;
        Logger logger = LOG;
        Level level = DEBUG_LOAD_LEVEL;
        logger.log(level, "Looking for library '" + str2 + "'");
        ArrayList arrayList = new ArrayList();
        boolean isAbsolute = new File(str2).isAbsolute();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int openFlags = openFlags(map);
        List list = searchPaths.get(str2);
        if (list != null) {
            synchronized (list) {
                linkedHashSet.addAll(list);
            }
        }
        String webStartLibraryPath = Native.getWebStartLibraryPath(str);
        if (webStartLibraryPath != null) {
            logger.log(level, "Adding web start path ".concat(webStartLibraryPath));
            linkedHashSet.add(webStartLibraryPath);
        }
        logger.log(level, "Adding paths from jna.library.path: " + System.getProperty("jna.library.path"));
        linkedHashSet.addAll(initPaths("jna.library.path"));
        String findLibraryPath = findLibraryPath(str2, linkedHashSet);
        try {
            logger.log(level, "Trying " + findLibraryPath);
            j2 = Native.open(findLibraryPath, openFlags);
        } catch (UnsatisfiedLinkError e3) {
            Logger logger2 = LOG;
            Level level2 = DEBUG_LOAD_LEVEL;
            logger2.log(level2, "Loading failed with message: " + e3.getMessage());
            StringBuilder sb = new StringBuilder("Adding system paths: ");
            LinkedHashSet<String> linkedHashSet2 = librarySearchPath;
            sb.append(linkedHashSet2);
            logger2.log(level2, sb.toString());
            arrayList.add(e3);
            linkedHashSet.addAll(linkedHashSet2);
            j2 = 0;
        }
        if (j2 == 0) {
            try {
                findLibraryPath = findLibraryPath(str2, linkedHashSet);
                LOG.log(DEBUG_LOAD_LEVEL, "Trying " + findLibraryPath);
                j2 = Native.open(findLibraryPath, openFlags);
                if (j2 == 0) {
                    throw new UnsatisfiedLinkError("Failed to load library '" + str2 + "'");
                }
            } catch (UnsatisfiedLinkError e4) {
                Logger logger3 = LOG;
                Level level3 = DEBUG_LOAD_LEVEL;
                logger3.log(level3, "Loading failed with message: " + e4.getMessage());
                arrayList.add(e4);
                if (!Platform.isAndroid()) {
                    if (!Platform.isLinux() && !Platform.isFreeBSD()) {
                        if (Platform.isMac() && !str2.endsWith(".dylib")) {
                            String[] matchFramework = matchFramework(str);
                            int length = matchFramework.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length) {
                                    break;
                                }
                                String str3 = matchFramework[i3];
                                try {
                                    LOG.log(DEBUG_LOAD_LEVEL, "Trying " + str3);
                                    j2 = Native.open(str3, openFlags);
                                    break;
                                } catch (UnsatisfiedLinkError e5) {
                                    LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e5.getMessage());
                                    arrayList.add(e5);
                                    i3++;
                                }
                            }
                        } else if (Platform.isWindows() && !isAbsolute) {
                            logger3.log(level3, "Looking for lib- prefix");
                            findLibraryPath = findLibraryPath("lib" + str2, linkedHashSet);
                            if (findLibraryPath != null) {
                                logger3.log(level3, "Trying ".concat(findLibraryPath));
                                try {
                                    j2 = Native.open(findLibraryPath, openFlags);
                                } catch (UnsatisfiedLinkError e6) {
                                    UnsatisfiedLinkError unsatisfiedLinkError = e6;
                                    LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + unsatisfiedLinkError.getMessage());
                                    arrayList.add(unsatisfiedLinkError);
                                }
                            }
                        }
                    } else {
                        logger3.log(level3, "Looking for version variants");
                        findLibraryPath = matchLibrary(str2, linkedHashSet);
                        if (findLibraryPath != null) {
                            logger3.log(level3, "Trying ".concat(findLibraryPath));
                            try {
                                j2 = Native.open(findLibraryPath, openFlags);
                            } catch (UnsatisfiedLinkError e7) {
                                UnsatisfiedLinkError unsatisfiedLinkError2 = e7;
                                LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + unsatisfiedLinkError2.getMessage());
                                arrayList.add(unsatisfiedLinkError2);
                            }
                        }
                    }
                } else {
                    try {
                        logger3.log(level3, "Preload (via System.loadLibrary) " + str2);
                        System.loadLibrary(str);
                        j2 = Native.open(findLibraryPath, openFlags);
                    } catch (UnsatisfiedLinkError e8) {
                        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e8.getMessage());
                        arrayList.add(e8);
                    }
                }
                if (j2 == 0) {
                    try {
                        extractFromResourcePath = Native.extractFromResourcePath(str2, (ClassLoader) map.get(Library.OPTION_CLASSLOADER));
                        if (extractFromResourcePath != null) {
                            j2 = Native.open(extractFromResourcePath.getAbsolutePath(), openFlags);
                            findLibraryPath = extractFromResourcePath.getAbsolutePath();
                            if (Native.isUnpacked(extractFromResourcePath)) {
                                Native.deleteLibrary(extractFromResourcePath);
                            }
                        }
                    } catch (IOException e9) {
                        LOG.log(DEBUG_LOAD_LEVEL, "Loading failed with message: " + e9.getMessage());
                        arrayList.add(e9);
                    }
                } else {
                    Map<String, ?> map2 = map;
                }
                if (j2 == 0) {
                    StringBuilder w2 = a.w("Unable to load library '", str2, "':");
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        w2.append("\n");
                        w2.append(((Throwable) it.next()).getMessage());
                    }
                    UnsatisfiedLinkError unsatisfiedLinkError3 = new UnsatisfiedLinkError(w2.toString());
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        addSuppressedReflected(unsatisfiedLinkError3, (Throwable) it2.next());
                    }
                    throw unsatisfiedLinkError3;
                }
            } catch (Throwable th) {
                if (Native.isUnpacked(extractFromResourcePath)) {
                    Native.deleteLibrary(extractFromResourcePath);
                }
                throw th;
            }
        }
        Map<String, ?> map3 = map;
        String str4 = findLibraryPath;
        LOG.log(DEBUG_LOAD_LEVEL, C0118y.f("Found library '", str2, "' at ", str4));
        return new NativeLibrary(str, str4, j2, map);
    }

    public static String mapSharedLibraryName(String str) {
        if (!Platform.isMac()) {
            if (Platform.isLinux() || Platform.isFreeBSD()) {
                if (isVersionedName(str) || str.endsWith(".so")) {
                    return str;
                }
            } else if (Platform.isAIX()) {
                if (isVersionedName(str) || str.endsWith(".so") || str.startsWith("lib") || str.endsWith(".a")) {
                    return str;
                }
            } else if (Platform.isWindows() && (str.endsWith(".drv") || str.endsWith(".dll") || str.endsWith(".ocx"))) {
                return str;
            }
            String mapLibraryName = System.mapLibraryName(str);
            return (!Platform.isAIX() || !mapLibraryName.endsWith(".so")) ? mapLibraryName : mapLibraryName.replaceAll(".so$", ".a");
        } else if (str.startsWith("lib") && (str.endsWith(".dylib") || str.endsWith(".jnilib"))) {
            return str;
        } else {
            String mapLibraryName2 = System.mapLibraryName(str);
            if (!mapLibraryName2.endsWith(".jnilib")) {
                return mapLibraryName2;
            }
            return mapLibraryName2.substring(0, mapLibraryName2.lastIndexOf(".jnilib")) + ".dylib";
        }
    }

    public static String[] matchFramework(String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        File file = new File(str);
        if (!file.isAbsolute()) {
            String[] strArr = {System.getProperty("user.home"), "", "/System"};
            if (!str.contains(".framework")) {
                str = a.n(str, ".framework/", str);
            }
            for (int i3 = 0; i3 < 3; i3++) {
                File file2 = new File(a.n(strArr[i3], "/Library/Frameworks/", str));
                if (file2.exists()) {
                    return new String[]{file2.getAbsolutePath()};
                }
                linkedHashSet.add(file2.getAbsolutePath());
            }
        } else if (!str.contains(".framework")) {
            File file3 = new File(new File(file.getParentFile(), file.getName() + ".framework"), file.getName());
            if (file3.exists()) {
                return new String[]{file3.getAbsolutePath()};
            }
            linkedHashSet.add(file3.getAbsolutePath());
        } else if (file.exists()) {
            return new String[]{file.getAbsolutePath()};
        } else {
            linkedHashSet.add(file.getAbsolutePath());
        }
        return (String[]) linkedHashSet.toArray(new String[0]);
    }

    public static String matchLibrary(final String str, Collection<String> collection) {
        File file = new File(str);
        if (file.isAbsolute()) {
            collection = Arrays.asList(new String[]{file.getParent()});
        }
        AnonymousClass3 r02 = new FilenameFilter() {
            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0038, code lost:
                if (r6.startsWith("lib") != false) goto L_0x003a;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public boolean accept(java.io.File r4, java.lang.String r5) {
                /*
                    r3 = this;
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    java.lang.String r0 = "lib"
                    r4.<init>(r0)
                    java.lang.String r1 = r6
                    r4.append(r1)
                    java.lang.String r1 = ".so"
                    r4.append(r1)
                    java.lang.String r4 = r4.toString()
                    boolean r4 = r5.startsWith(r4)
                    if (r4 != 0) goto L_0x003a
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r2 = r6
                    r4.append(r2)
                    r4.append(r1)
                    java.lang.String r4 = r4.toString()
                    boolean r4 = r5.startsWith(r4)
                    if (r4 == 0) goto L_0x0042
                    java.lang.String r3 = r6
                    boolean r3 = r3.startsWith(r0)
                    if (r3 == 0) goto L_0x0042
                L_0x003a:
                    boolean r3 = com.sun.jna.NativeLibrary.isVersionedName(r5)
                    if (r3 == 0) goto L_0x0042
                    r3 = 1
                    goto L_0x0043
                L_0x0042:
                    r3 = 0
                L_0x0043:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sun.jna.NativeLibrary.AnonymousClass3.accept(java.io.File, java.lang.String):boolean");
            }
        };
        LinkedList<File> linkedList = new LinkedList<>();
        for (String file2 : collection) {
            File[] listFiles = new File(file2).listFiles(r02);
            if (listFiles != null && listFiles.length > 0) {
                linkedList.addAll(Arrays.asList(listFiles));
            }
        }
        double d2 = -1.0d;
        String str2 = null;
        for (File absolutePath : linkedList) {
            String absolutePath2 = absolutePath.getAbsolutePath();
            double parseVersion = parseVersion(absolutePath2.substring(absolutePath2.lastIndexOf(".so.") + 4));
            if (parseVersion > d2) {
                str2 = absolutePath2;
                d2 = parseVersion;
            }
        }
        return str2;
    }

    private static int openFlags(Map<String, ?> map) {
        Object obj = map.get(Library.OPTION_OPEN_FLAGS);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        return -1;
    }

    public static double parseVersion(String str) {
        String str2;
        int indexOf = str.indexOf(JwtUtilsKt.JWT_DELIMITER);
        double d2 = 1.0d;
        double d3 = 0.0d;
        while (str != null) {
            if (indexOf != -1) {
                String substring = str.substring(0, indexOf);
                String substring2 = str.substring(indexOf + 1);
                indexOf = substring2.indexOf(JwtUtilsKt.JWT_DELIMITER);
                String str3 = substring;
                str2 = substring2;
                str = str3;
            } else {
                str2 = null;
            }
            try {
                d3 += ((double) Integer.parseInt(str)) / d2;
                d2 *= 100.0d;
                str = str2;
            } catch (NumberFormatException unused) {
                return 0.0d;
            }
        }
        return d3;
    }

    public void close() {
        HashSet hashSet = new HashSet();
        Map<String, Reference<NativeLibrary>> map = libraries;
        synchronized (map) {
            try {
                for (Map.Entry next : map.entrySet()) {
                    if (((Reference) next.getValue()).get() == this) {
                        hashSet.add((String) next.getKey());
                    }
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    libraries.remove((String) it.next());
                }
            } finally {
                while (true) {
                }
            }
        }
        synchronized (this) {
            try {
                if (this.handle != 0) {
                    this.handle = 0;
                    this.cleanable.clean();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Deprecated
    public void dispose() {
        close();
    }

    public File getFile() {
        if (this.libraryPath == null) {
            return null;
        }
        return new File(this.libraryPath);
    }

    public Function getFunction(String str) {
        return getFunction(str, this.callFlags);
    }

    public Pointer getGlobalVariableAddress(String str) {
        try {
            return new Pointer(getSymbolAddress(str));
        } catch (UnsatisfiedLinkError e3) {
            StringBuilder w2 = a.w("Error looking up '", str, "': ");
            w2.append(e3.getMessage());
            throw new UnsatisfiedLinkError(w2.toString());
        }
    }

    public String getName() {
        return this.libraryName;
    }

    public Map<String, ?> getOptions() {
        return this.options;
    }

    public long getSymbolAddress(String str) {
        if (this.handle != 0) {
            return this.symbolProvider.getSymbolAddress(this.handle, str, NATIVE_SYMBOL_PROVIDER);
        }
        throw new UnsatisfiedLinkError("Library has been unloaded");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Native Library <");
        sb.append(this.libraryPath);
        sb.append("@");
        return a.k(this.handle, ">", sb);
    }

    public static final NativeLibrary getInstance(String str, ClassLoader classLoader) {
        return getInstance(str, (Map<String, ?>) Collections.singletonMap(Library.OPTION_CLASSLOADER, classLoader));
    }

    public static final synchronized NativeLibrary getProcess(Map<String, ?> map) {
        NativeLibrary instance;
        synchronized (NativeLibrary.class) {
            instance = getInstance((String) null, map);
        }
        return instance;
    }

    public Function getFunction(String str, Method method) {
        FunctionMapper functionMapper = (FunctionMapper) this.options.get(Library.OPTION_FUNCTION_MAPPER);
        if (functionMapper != null) {
            str = functionMapper.getFunctionName(this, method);
        }
        String property = System.getProperty("jna.profiler.prefix", "$$YJP$$");
        if (str.startsWith(property)) {
            str = str.substring(property.length());
        }
        int i3 = this.callFlags;
        Class[] exceptionTypes = method.getExceptionTypes();
        for (Class isAssignableFrom : exceptionTypes) {
            if (LastErrorException.class.isAssignableFrom(isAssignableFrom)) {
                i3 |= 64;
            }
        }
        return getFunction(str, i3);
    }

    public static final NativeLibrary getInstance(String str, Map<String, ?> map) {
        NativeLibrary nativeLibrary;
        NativeLibrary loadLibrary;
        HashMap hashMap = new HashMap(map);
        if (hashMap.get(Library.OPTION_CALLING_CONVENTION) == null) {
            hashMap.put(Library.OPTION_CALLING_CONVENTION, 0);
        }
        if ((Platform.isLinux() || Platform.isFreeBSD() || Platform.isAIX()) && Platform.C_LIBRARY_NAME.equals(str)) {
            str = null;
        }
        Map<String, Reference<NativeLibrary>> map2 = libraries;
        synchronized (map2) {
            try {
                Reference reference = map2.get(str + hashMap);
                nativeLibrary = reference != null ? (NativeLibrary) reference.get() : null;
                if (nativeLibrary == null) {
                    if (str == null) {
                        loadLibrary = new NativeLibrary("<process>", (String) null, Native.open((String) null, openFlags(hashMap)), hashMap);
                    } else {
                        loadLibrary = loadLibrary(str, hashMap);
                    }
                    nativeLibrary = loadLibrary;
                    WeakReference weakReference = new WeakReference(nativeLibrary);
                    map2.put(nativeLibrary.getName() + hashMap, weakReference);
                    File file = nativeLibrary.getFile();
                    if (file != null) {
                        map2.put(file.getAbsolutePath() + hashMap, weakReference);
                        map2.put(file.getName() + hashMap, weakReference);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return nativeLibrary;
    }

    public Function getFunction(String str, int i3) {
        return getFunction(str, i3, this.encoding);
    }

    public Function getFunction(String str, int i3, String str2) {
        Function function;
        if (str != null) {
            synchronized (this.functions) {
                try {
                    String functionKey = functionKey(str, i3, str2);
                    function = this.functions.get(functionKey);
                    if (function == null) {
                        function = new Function(this, str, i3, str2);
                        this.functions.put(functionKey, function);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return function;
        }
        throw new NullPointerException("Function name may not be null");
    }
}
