package com.google.common.reflect;

import android.support.v4.media.session.a;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.ByteSource;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.lookup.StringLookupFactory;

@ElementTypesAreNonnullByDefault
public final class ClassPath {
    private static final String CLASS_FILE_NAME_EXTENSION = ".class";
    private static final Splitter CLASS_PATH_ATTRIBUTE_SEPARATOR = Splitter.on(StringUtils.SPACE).omitEmptyStrings();
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ClassPath.class.getName());
    private final ImmutableSet<ResourceInfo> resources;

    public static final class ClassInfo extends ResourceInfo {
        private final String className;

        public ClassInfo(File file, String str, ClassLoader classLoader) {
            super(file, str, classLoader);
            this.className = ClassPath.getClassName(str);
        }

        public String getName() {
            return this.className;
        }

        public String getPackageName() {
            return Reflection.getPackageName(this.className);
        }

        public String getSimpleName() {
            int lastIndexOf = this.className.lastIndexOf(36);
            if (lastIndexOf != -1) {
                return CharMatcher.inRange('0', '9').trimLeadingFrom(this.className.substring(lastIndexOf + 1));
            }
            String packageName = getPackageName();
            return packageName.isEmpty() ? this.className : this.className.substring(packageName.length() + 1);
        }

        public boolean isTopLevel() {
            return this.className.indexOf(36) == -1;
        }

        public Class<?> load() {
            try {
                return this.loader.loadClass(this.className);
            } catch (ClassNotFoundException e3) {
                throw new IllegalStateException(e3);
            }
        }

        public String toString() {
            return this.className;
        }
    }

    public static final class LocationInfo {
        private final ClassLoader classloader;
        final File home;

        public LocationInfo(File file, ClassLoader classLoader) {
            this.home = (File) Preconditions.checkNotNull(file);
            this.classloader = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        private void scan(File file, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        scanDirectory(file, builder);
                    } else {
                        scanJar(file, set, builder);
                    }
                }
            } catch (SecurityException e3) {
                Logger access$000 = ClassPath.logger;
                access$000.warning("Cannot access " + file + ": " + e3);
            }
        }

        private void scanDirectory(File file, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            HashSet hashSet = new HashSet();
            hashSet.add(file.getCanonicalFile());
            scanDirectory(file, "", hashSet, builder);
        }

        private void scanJar(File file, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            try {
                JarFile jarFile = new JarFile(file);
                try {
                    UnmodifiableIterator<File> it = ClassPath.getClassPathFromManifest(file, jarFile.getManifest()).iterator();
                    while (it.hasNext()) {
                        File next = it.next();
                        if (set.add(next.getCanonicalFile())) {
                            scan(next, set, builder);
                        }
                    }
                    scanJarFile(jarFile, builder);
                    try {
                        jarFile.close();
                    } catch (IOException unused) {
                    }
                } finally {
                    try {
                        jarFile.close();
                    } catch (IOException unused2) {
                    }
                }
            } catch (IOException unused3) {
            }
        }

        private void scanJarFile(JarFile jarFile, ImmutableSet.Builder<ResourceInfo> builder) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                if (!nextElement.isDirectory() && !nextElement.getName().equals("META-INF/MANIFEST.MF")) {
                    builder.add((Object) ResourceInfo.of(new File(jarFile.getName()), nextElement.getName(), this.classloader));
                }
            }
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof LocationInfo)) {
                return false;
            }
            LocationInfo locationInfo = (LocationInfo) obj;
            return this.home.equals(locationInfo.home) && this.classloader.equals(locationInfo.classloader);
        }

        public final File file() {
            return this.home;
        }

        public int hashCode() {
            return this.home.hashCode();
        }

        public ImmutableSet<ResourceInfo> scanResources() throws IOException {
            return scanResources(new HashSet());
        }

        public String toString() {
            return this.home.toString();
        }

        public ImmutableSet<ResourceInfo> scanResources(Set<File> set) throws IOException {
            ImmutableSet.Builder builder = ImmutableSet.builder();
            set.add(this.home);
            scan(this.home, set, builder);
            return builder.build();
        }

        private void scanDirectory(File file, String str, Set<File> set, ImmutableSet.Builder<ResourceInfo> builder) throws IOException {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                ClassPath.logger.warning("Cannot read directory " + file);
                return;
            }
            for (File file2 : listFiles) {
                String name = file2.getName();
                if (file2.isDirectory()) {
                    File canonicalFile = file2.getCanonicalFile();
                    if (set.add(canonicalFile)) {
                        scanDirectory(canonicalFile, a.n(str, name, "/"), set, builder);
                        set.remove(canonicalFile);
                    }
                } else {
                    String m3 = a.m(str, name);
                    if (!m3.equals("META-INF/MANIFEST.MF")) {
                        builder.add((Object) ResourceInfo.of(file2, m3, this.classloader));
                    }
                }
            }
        }
    }

    public static class ResourceInfo {
        private final File file;
        final ClassLoader loader;
        private final String resourceName;

        public ResourceInfo(File file2, String str, ClassLoader classLoader) {
            this.file = (File) Preconditions.checkNotNull(file2);
            this.resourceName = (String) Preconditions.checkNotNull(str);
            this.loader = (ClassLoader) Preconditions.checkNotNull(classLoader);
        }

        public static ResourceInfo of(File file2, String str, ClassLoader classLoader) {
            return str.endsWith(ClassPath.CLASS_FILE_NAME_EXTENSION) ? new ClassInfo(file2, str, classLoader) : new ResourceInfo(file2, str, classLoader);
        }

        public final ByteSource asByteSource() {
            return Resources.asByteSource(url());
        }

        public final CharSource asCharSource(Charset charset) {
            return Resources.asCharSource(url(), charset);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof ResourceInfo)) {
                return false;
            }
            ResourceInfo resourceInfo = (ResourceInfo) obj;
            return this.resourceName.equals(resourceInfo.resourceName) && this.loader == resourceInfo.loader;
        }

        public final File getFile() {
            return this.file;
        }

        public final String getResourceName() {
            return this.resourceName;
        }

        public int hashCode() {
            return this.resourceName.hashCode();
        }

        public String toString() {
            return this.resourceName;
        }

        public final URL url() {
            URL resource = this.loader.getResource(this.resourceName);
            if (resource != null) {
                return resource;
            }
            throw new NoSuchElementException(this.resourceName);
        }
    }

    private ClassPath(ImmutableSet<ResourceInfo> immutableSet) {
        this.resources = immutableSet;
    }

    public static ClassPath from(ClassLoader classLoader) throws IOException {
        ImmutableSet<LocationInfo> locationsFrom = locationsFrom(classLoader);
        HashSet hashSet = new HashSet();
        UnmodifiableIterator<LocationInfo> it = locationsFrom.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().file());
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<LocationInfo> it2 = locationsFrom.iterator();
        while (it2.hasNext()) {
            builder.addAll((Iterable) it2.next().scanResources(hashSet));
        }
        return new ClassPath(builder.build());
    }

    private static ImmutableList<URL> getClassLoaderUrls(ClassLoader classLoader) {
        return classLoader instanceof URLClassLoader ? ImmutableList.copyOf((E[]) ((URLClassLoader) classLoader).getURLs()) : classLoader.equals(ClassLoader.getSystemClassLoader()) ? parseJavaClassPath() : ImmutableList.of();
    }

    @VisibleForTesting
    public static String getClassName(String str) {
        return str.substring(0, str.length() - 6).replace(JsonPointer.SEPARATOR, ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }

    @VisibleForTesting
    public static ImmutableMap<File, ClassLoader> getClassPathEntries(ClassLoader classLoader) {
        LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
        ClassLoader parent = classLoader.getParent();
        if (parent != null) {
            newLinkedHashMap.putAll(getClassPathEntries(parent));
        }
        UnmodifiableIterator<URL> it = getClassLoaderUrls(classLoader).iterator();
        while (it.hasNext()) {
            URL next = it.next();
            if (next.getProtocol().equals(StringLookupFactory.KEY_FILE)) {
                File file = toFile(next);
                if (!newLinkedHashMap.containsKey(file)) {
                    newLinkedHashMap.put(file, classLoader);
                }
            }
        }
        return ImmutableMap.copyOf(newLinkedHashMap);
    }

    @VisibleForTesting
    public static URL getClassPathEntry(File file, String str) throws MalformedURLException {
        return new URL(file.toURI().toURL(), str);
    }

    @VisibleForTesting
    public static ImmutableSet<File> getClassPathFromManifest(File file, @CheckForNull Manifest manifest) {
        if (manifest == null) {
            return ImmutableSet.of();
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        String value = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH.toString());
        if (value != null) {
            for (String next : CLASS_PATH_ATTRIBUTE_SEPARATOR.split(value)) {
                try {
                    URL classPathEntry = getClassPathEntry(file, next);
                    if (classPathEntry.getProtocol().equals(StringLookupFactory.KEY_FILE)) {
                        builder.add((Object) toFile(classPathEntry));
                    }
                } catch (MalformedURLException unused) {
                    Logger logger2 = logger;
                    logger2.warning("Invalid Class-Path entry: " + next);
                }
            }
        }
        return builder.build();
    }

    public static ImmutableSet<LocationInfo> locationsFrom(ClassLoader classLoader) {
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<Map.Entry<File, ClassLoader>> it = getClassPathEntries(classLoader).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            builder.add((Object) new LocationInfo((File) next.getKey(), (ClassLoader) next.getValue()));
        }
        return builder.build();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:3|4|5|7|8|15|12|1) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0039, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0050, code lost:
        r4 = logger;
        r5 = java.util.logging.Level.WARNING;
        r4.log(r5, "malformed classpath entry: " + r2, r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x003b */
    @com.google.common.annotations.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.collect.ImmutableList<java.net.URL> parseJavaClassPath() {
        /*
            com.google.common.collect.ImmutableList$Builder r0 = com.google.common.collect.ImmutableList.builder()
            com.google.common.base.StandardSystemProperty r1 = com.google.common.base.StandardSystemProperty.PATH_SEPARATOR
            java.lang.String r1 = r1.value()
            com.google.common.base.Splitter r1 = com.google.common.base.Splitter.on((java.lang.String) r1)
            com.google.common.base.StandardSystemProperty r2 = com.google.common.base.StandardSystemProperty.JAVA_CLASS_PATH
            java.lang.String r2 = r2.value()
            java.lang.Iterable r1 = r1.split(r2)
            java.util.Iterator r1 = r1.iterator()
        L_0x001c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0066
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.io.File r3 = new java.io.File     // Catch:{ SecurityException -> 0x003b }
            r3.<init>(r2)     // Catch:{ SecurityException -> 0x003b }
            java.net.URI r3 = r3.toURI()     // Catch:{ SecurityException -> 0x003b }
            java.net.URL r3 = r3.toURL()     // Catch:{ SecurityException -> 0x003b }
            r0.add((java.lang.Object) r3)     // Catch:{ SecurityException -> 0x003b }
            goto L_0x001c
        L_0x0039:
            r3 = move-exception
            goto L_0x0050
        L_0x003b:
            java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0039 }
            java.lang.String r4 = "file"
            java.io.File r5 = new java.io.File     // Catch:{ MalformedURLException -> 0x0039 }
            r5.<init>(r2)     // Catch:{ MalformedURLException -> 0x0039 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ MalformedURLException -> 0x0039 }
            r6 = 0
            r3.<init>(r4, r6, r5)     // Catch:{ MalformedURLException -> 0x0039 }
            r0.add((java.lang.Object) r3)     // Catch:{ MalformedURLException -> 0x0039 }
            goto L_0x001c
        L_0x0050:
            java.util.logging.Logger r4 = logger
            java.util.logging.Level r5 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "malformed classpath entry: "
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            r4.log(r5, r2, r3)
            goto L_0x001c
        L_0x0066:
            com.google.common.collect.ImmutableList r0 = r0.build()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.reflect.ClassPath.parseJavaClassPath():com.google.common.collect.ImmutableList");
    }

    @VisibleForTesting
    public static File toFile(URL url) {
        Preconditions.checkArgument(url.getProtocol().equals(StringLookupFactory.KEY_FILE));
        try {
            return new File(url.toURI());
        } catch (URISyntaxException unused) {
            return new File(url.getPath());
        }
    }

    public ImmutableSet<ClassInfo> getAllClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).toSet();
    }

    public ImmutableSet<ResourceInfo> getResources() {
        return this.resources;
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses() {
        return FluentIterable.from(this.resources).filter(ClassInfo.class).filter(new a(0)).toSet();
    }

    public ImmutableSet<ClassInfo> getTopLevelClassesRecursive(String str) {
        Preconditions.checkNotNull(str);
        String str2 = str + ClassUtils.PACKAGE_SEPARATOR_CHAR;
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getName().startsWith(str2)) {
                builder.add((Object) next);
            }
        }
        return builder.build();
    }

    public ImmutableSet<ClassInfo> getTopLevelClasses(String str) {
        Preconditions.checkNotNull(str);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        UnmodifiableIterator<ClassInfo> it = getTopLevelClasses().iterator();
        while (it.hasNext()) {
            ClassInfo next = it.next();
            if (next.getPackageName().equals(str)) {
                builder.add((Object) next);
            }
        }
        return builder.build();
    }
}
