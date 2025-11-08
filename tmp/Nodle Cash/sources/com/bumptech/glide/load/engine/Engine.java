package com.bumptech.glide.load.engine;

import A.a;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pools;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineResource;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.executor.GlideExecutor;
import com.bumptech.glide.request.ResourceCallback;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.pool.FactoryPools;
import java.util.Map;
import java.util.concurrent.Executor;

public class Engine implements EngineJobListener, MemoryCache.ResourceRemovedListener, EngineResource.ResourceListener {
    private static final int JOB_POOL_SIZE = 150;
    private static final String TAG = "Engine";
    private static final boolean VERBOSE_IS_LOGGABLE = Log.isLoggable(TAG, 2);
    private final ActiveResources activeResources;
    private final MemoryCache cache;
    private final DecodeJobFactory decodeJobFactory;
    private final LazyDiskCacheProvider diskCacheProvider;
    private final EngineJobFactory engineJobFactory;
    private final Jobs jobs;
    private final EngineKeyFactory keyFactory;
    private final ResourceRecycler resourceRecycler;

    @VisibleForTesting
    public static class DecodeJobFactory {
        private int creationOrder;
        final DecodeJob.DiskCacheProvider diskCacheProvider;
        final Pools.Pool<DecodeJob<?>> pool = FactoryPools.threadSafe(150, new FactoryPools.Factory<DecodeJob<?>>() {
            public DecodeJob<?> create() {
                DecodeJobFactory decodeJobFactory = DecodeJobFactory.this;
                return new DecodeJob<>(decodeJobFactory.diskCacheProvider, decodeJobFactory.pool);
            }
        });

        public DecodeJobFactory(DecodeJob.DiskCacheProvider diskCacheProvider2) {
            this.diskCacheProvider = diskCacheProvider2;
        }

        public <R> DecodeJob<R> build(GlideContext glideContext, Object obj, EngineKey engineKey, Key key, int i3, int i4, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z2, boolean z3, boolean z4, Options options, DecodeJob.Callback<R> callback) {
            DecodeJob decodeJob = (DecodeJob) Preconditions.checkNotNull(this.pool.acquire());
            int i5 = this.creationOrder;
            int i6 = i5;
            this.creationOrder = i5 + 1;
            return decodeJob.init(glideContext, obj, engineKey, key, i3, i4, cls, cls2, priority, diskCacheStrategy, map, z2, z3, z4, options, callback, i6);
        }
    }

    @VisibleForTesting
    public static class EngineJobFactory {
        final GlideExecutor animationExecutor;
        final GlideExecutor diskCacheExecutor;
        final EngineJobListener engineJobListener;
        final Pools.Pool<EngineJob<?>> pool = FactoryPools.threadSafe(150, new FactoryPools.Factory<EngineJob<?>>() {
            public EngineJob<?> create() {
                EngineJobFactory engineJobFactory = EngineJobFactory.this;
                return new EngineJob(engineJobFactory.diskCacheExecutor, engineJobFactory.sourceExecutor, engineJobFactory.sourceUnlimitedExecutor, engineJobFactory.animationExecutor, engineJobFactory.engineJobListener, engineJobFactory.resourceListener, engineJobFactory.pool);
            }
        });
        final EngineResource.ResourceListener resourceListener;
        final GlideExecutor sourceExecutor;
        final GlideExecutor sourceUnlimitedExecutor;

        public EngineJobFactory(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, EngineJobListener engineJobListener2, EngineResource.ResourceListener resourceListener2) {
            this.diskCacheExecutor = glideExecutor;
            this.sourceExecutor = glideExecutor2;
            this.sourceUnlimitedExecutor = glideExecutor3;
            this.animationExecutor = glideExecutor4;
            this.engineJobListener = engineJobListener2;
            this.resourceListener = resourceListener2;
        }

        public <R> EngineJob<R> build(Key key, boolean z2, boolean z3, boolean z4, boolean z5) {
            return ((EngineJob) Preconditions.checkNotNull(this.pool.acquire())).init(key, z2, z3, z4, z5);
        }

        @VisibleForTesting
        public void shutdown() {
            Executors.shutdownAndAwaitTermination(this.diskCacheExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceExecutor);
            Executors.shutdownAndAwaitTermination(this.sourceUnlimitedExecutor);
            Executors.shutdownAndAwaitTermination(this.animationExecutor);
        }
    }

    public static class LazyDiskCacheProvider implements DecodeJob.DiskCacheProvider {
        private volatile DiskCache diskCache;
        private final DiskCache.Factory factory;

        public LazyDiskCacheProvider(DiskCache.Factory factory2) {
            this.factory = factory2;
        }

        @VisibleForTesting
        public synchronized void clearDiskCacheIfCreated() {
            if (this.diskCache != null) {
                this.diskCache.clear();
            }
        }

        public DiskCache getDiskCache() {
            if (this.diskCache == null) {
                synchronized (this) {
                    try {
                        if (this.diskCache == null) {
                            this.diskCache = this.factory.build();
                        }
                        if (this.diskCache == null) {
                            this.diskCache = new DiskCacheAdapter();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
            return this.diskCache;
        }
    }

    public class LoadStatus {
        private final ResourceCallback cb;
        private final EngineJob<?> engineJob;

        public LoadStatus(ResourceCallback resourceCallback, EngineJob<?> engineJob2) {
            this.cb = resourceCallback;
            this.engineJob = engineJob2;
        }

        public void cancel() {
            synchronized (Engine.this) {
                this.engineJob.removeCallback(this.cb);
            }
        }
    }

    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, boolean z2) {
        this(memoryCache, factory, glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, (Jobs) null, (EngineKeyFactory) null, (ActiveResources) null, (EngineJobFactory) null, (DecodeJobFactory) null, (ResourceRecycler) null, z2);
    }

    private EngineResource<?> getEngineResourceFromCache(Key key) {
        Resource<?> remove = this.cache.remove(key);
        if (remove == null) {
            return null;
        }
        return remove instanceof EngineResource ? (EngineResource) remove : new EngineResource<>(remove, true, true, key, this);
    }

    @Nullable
    private EngineResource<?> loadFromActiveResources(Key key) {
        EngineResource<?> engineResource = this.activeResources.get(key);
        if (engineResource != null) {
            engineResource.acquire();
        }
        return engineResource;
    }

    private EngineResource<?> loadFromCache(Key key) {
        EngineResource<?> engineResourceFromCache = getEngineResourceFromCache(key);
        if (engineResourceFromCache != null) {
            engineResourceFromCache.acquire();
            this.activeResources.activate(key, engineResourceFromCache);
        }
        return engineResourceFromCache;
    }

    @Nullable
    private EngineResource<?> loadFromMemory(EngineKey engineKey, boolean z2, long j2) {
        if (!z2) {
            return null;
        }
        EngineResource<?> loadFromActiveResources = loadFromActiveResources(engineKey);
        if (loadFromActiveResources != null) {
            if (VERBOSE_IS_LOGGABLE) {
                logWithTimeAndKey("Loaded resource from active resources", j2, engineKey);
            }
            return loadFromActiveResources;
        }
        EngineResource<?> loadFromCache = loadFromCache(engineKey);
        if (loadFromCache == null) {
            return null;
        }
        if (VERBOSE_IS_LOGGABLE) {
            logWithTimeAndKey("Loaded resource from cache", j2, engineKey);
        }
        return loadFromCache;
    }

    private static void logWithTimeAndKey(String str, long j2, Key key) {
        StringBuilder q2 = a.q(str, " in ");
        q2.append(LogTime.getElapsedMillis(j2));
        q2.append("ms, key: ");
        q2.append(key);
        Log.v(TAG, q2.toString());
    }

    private <R> LoadStatus waitForExistingOrStartNewJob(GlideContext glideContext, Object obj, Key key, int i3, int i4, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z2, boolean z3, Options options, boolean z4, boolean z5, boolean z6, boolean z7, ResourceCallback resourceCallback, Executor executor, EngineKey engineKey, long j2) {
        ResourceCallback resourceCallback2 = resourceCallback;
        Executor executor2 = executor;
        EngineKey engineKey2 = engineKey;
        long j3 = j2;
        EngineJob<?> engineJob = this.jobs.get(engineKey2, z7);
        if (engineJob != null) {
            engineJob.addCallback(resourceCallback2, executor2);
            if (VERBOSE_IS_LOGGABLE) {
                logWithTimeAndKey("Added to existing load", j3, engineKey2);
            }
            return new LoadStatus(resourceCallback2, engineJob);
        }
        EngineJob build = this.engineJobFactory.build(engineKey, z4, z5, z6, z7);
        EngineJob engineJob2 = build;
        EngineKey engineKey3 = engineKey2;
        DecodeJob<R> build2 = this.decodeJobFactory.build(glideContext, obj, engineKey, key, i3, i4, cls, cls2, priority, diskCacheStrategy, map, z2, z3, z7, options, build);
        this.jobs.put(engineKey3, engineJob2);
        EngineJob engineJob3 = engineJob2;
        EngineKey engineKey4 = engineKey3;
        ResourceCallback resourceCallback3 = resourceCallback;
        engineJob3.addCallback(resourceCallback3, executor);
        engineJob3.start(build2);
        if (VERBOSE_IS_LOGGABLE) {
            logWithTimeAndKey("Started new load", j2, engineKey4);
        }
        return new LoadStatus(resourceCallback3, engineJob3);
    }

    public void clearDiskCache() {
        this.diskCacheProvider.getDiskCache().clear();
    }

    public <R> LoadStatus load(GlideContext glideContext, Object obj, Key key, int i3, int i4, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z2, boolean z3, Options options, boolean z4, boolean z5, boolean z6, boolean z7, ResourceCallback resourceCallback, Executor executor) {
        long logTime = VERBOSE_IS_LOGGABLE ? LogTime.getLogTime() : 0;
        EngineKey buildKey = this.keyFactory.buildKey(obj, key, i3, i4, map, cls, cls2, options);
        synchronized (this) {
            try {
                EngineResource<?> loadFromMemory = loadFromMemory(buildKey, z4, logTime);
                if (loadFromMemory == null) {
                    LoadStatus waitForExistingOrStartNewJob = waitForExistingOrStartNewJob(glideContext, obj, key, i3, i4, cls, cls2, priority, diskCacheStrategy, map, z2, z3, options, z4, z5, z6, z7, resourceCallback, executor, buildKey, logTime);
                    return waitForExistingOrStartNewJob;
                }
                resourceCallback.onResourceReady(loadFromMemory, DataSource.MEMORY_CACHE, false);
                return null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public synchronized void onEngineJobCancelled(EngineJob<?> engineJob, Key key) {
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public synchronized void onEngineJobComplete(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource) {
        if (engineResource != null) {
            try {
                if (engineResource.isMemoryCacheable()) {
                    this.activeResources.activate(key, engineResource);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.jobs.removeIfCurrent(key, engineJob);
    }

    public void onResourceReleased(Key key, EngineResource<?> engineResource) {
        this.activeResources.deactivate(key);
        if (engineResource.isMemoryCacheable()) {
            this.cache.put(key, engineResource);
        } else {
            this.resourceRecycler.recycle(engineResource, false);
        }
    }

    public void onResourceRemoved(@NonNull Resource<?> resource) {
        this.resourceRecycler.recycle(resource, true);
    }

    public void release(Resource<?> resource) {
        if (resource instanceof EngineResource) {
            ((EngineResource) resource).release();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    @VisibleForTesting
    public void shutdown() {
        this.engineJobFactory.shutdown();
        this.diskCacheProvider.clearDiskCacheIfCreated();
        this.activeResources.shutdown();
    }

    @VisibleForTesting
    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, Jobs jobs2, EngineKeyFactory engineKeyFactory, ActiveResources activeResources2, EngineJobFactory engineJobFactory2, DecodeJobFactory decodeJobFactory2, ResourceRecycler resourceRecycler2, boolean z2) {
        this.cache = memoryCache;
        DiskCache.Factory factory2 = factory;
        LazyDiskCacheProvider lazyDiskCacheProvider = new LazyDiskCacheProvider(factory);
        this.diskCacheProvider = lazyDiskCacheProvider;
        ActiveResources activeResources3 = activeResources2 == null ? new ActiveResources(z2) : activeResources2;
        this.activeResources = activeResources3;
        activeResources3.setListener(this);
        this.keyFactory = engineKeyFactory == null ? new EngineKeyFactory() : engineKeyFactory;
        this.jobs = jobs2 == null ? new Jobs() : jobs2;
        this.engineJobFactory = engineJobFactory2 == null ? new EngineJobFactory(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, this, this) : engineJobFactory2;
        this.decodeJobFactory = decodeJobFactory2 == null ? new DecodeJobFactory(lazyDiskCacheProvider) : decodeJobFactory2;
        this.resourceRecycler = resourceRecycler2 == null ? new ResourceRecycler() : resourceRecycler2;
        memoryCache.setResourceRemovedListener(this);
    }
}
