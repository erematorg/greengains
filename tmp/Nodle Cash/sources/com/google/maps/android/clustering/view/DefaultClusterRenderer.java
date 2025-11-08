package com.google.maps.android.clustering.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.compose.ui.autofill.a;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.R;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.ui.IconGenerator;
import com.google.maps.android.ui.SquareTextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DefaultClusterRenderer<T extends ClusterItem> implements ClusterRenderer<T> {
    /* access modifiers changed from: private */
    public static final TimeInterpolator ANIMATION_INTERP = new DecelerateInterpolator();
    private static final int[] BUCKETS = {10, 20, 50, 100, 200, 500, 1000};
    /* access modifiers changed from: private */
    public boolean mAnimate;
    /* access modifiers changed from: private */
    public ClusterManager.OnClusterClickListener<T> mClickListener;
    /* access modifiers changed from: private */
    public final ClusterManager<T> mClusterManager;
    /* access modifiers changed from: private */
    public MarkerCache<Cluster<T>> mClusterMarkerCache = new MarkerCache<>();
    /* access modifiers changed from: private */
    public Set<? extends Cluster<T>> mClusters;
    private ShapeDrawable mColoredCircleBackground;
    private final float mDensity;
    private final IconGenerator mIconGenerator;
    private SparseArray<BitmapDescriptor> mIcons = new SparseArray<>();
    /* access modifiers changed from: private */
    public ClusterManager.OnClusterInfoWindowClickListener<T> mInfoWindowClickListener;
    /* access modifiers changed from: private */
    public ClusterManager.OnClusterItemClickListener<T> mItemClickListener;
    /* access modifiers changed from: private */
    public ClusterManager.OnClusterItemInfoWindowClickListener<T> mItemInfoWindowClickListener;
    /* access modifiers changed from: private */
    public final GoogleMap mMap;
    /* access modifiers changed from: private */
    public MarkerCache<T> mMarkerCache = new MarkerCache<>();
    /* access modifiers changed from: private */
    public Set<MarkerWithPosition> mMarkers = Collections.newSetFromMap(new ConcurrentHashMap());
    private int mMinClusterSize = 4;
    private final DefaultClusterRenderer<T>.ViewModifier mViewModifier = new ViewModifier();
    /* access modifiers changed from: private */
    public float mZoom;

    @TargetApi(12)
    public class AnimationTask extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private final LatLng from;
        private MarkerManager mMarkerManager;
        private boolean mRemoveOnComplete;
        private final Marker marker;
        private final MarkerWithPosition markerWithPosition;
        private final LatLng to;

        public void onAnimationEnd(Animator animator) {
            if (this.mRemoveOnComplete) {
                DefaultClusterRenderer.this.mMarkerCache.remove(this.marker);
                DefaultClusterRenderer.this.mClusterMarkerCache.remove(this.marker);
                this.mMarkerManager.remove(this.marker);
            }
            LatLng unused = this.markerWithPosition.position = this.to;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float animatedFraction = valueAnimator.getAnimatedFraction();
            LatLng latLng = this.to;
            double d2 = latLng.latitude;
            LatLng latLng2 = this.from;
            double d3 = latLng2.latitude;
            double d4 = (double) animatedFraction;
            double d5 = ((d2 - d3) * d4) + d3;
            double d6 = latLng.longitude - latLng2.longitude;
            if (Math.abs(d6) > 180.0d) {
                d6 -= Math.signum(d6) * 360.0d;
            }
            this.marker.setPosition(new LatLng(d5, (d6 * d4) + this.from.longitude));
        }

        public void perform() {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.setInterpolator(DefaultClusterRenderer.ANIMATION_INTERP);
            ofFloat.addUpdateListener(this);
            ofFloat.addListener(this);
            ofFloat.start();
        }

        public void removeOnAnimationComplete(MarkerManager markerManager) {
            this.mMarkerManager = markerManager;
            this.mRemoveOnComplete = true;
        }

        private AnimationTask(MarkerWithPosition markerWithPosition2, LatLng latLng, LatLng latLng2) {
            this.markerWithPosition = markerWithPosition2;
            this.marker = markerWithPosition2.marker;
            this.from = latLng;
            this.to = latLng2;
        }
    }

    public class CreateMarkerTask {
        private final LatLng animateFrom;
        private final Cluster<T> cluster;
        private final Set<MarkerWithPosition> newMarkers;

        public CreateMarkerTask(Cluster<T> cluster2, Set<MarkerWithPosition> set, LatLng latLng) {
            this.cluster = cluster2;
            this.newMarkers = set;
            this.animateFrom = latLng;
        }

        /* access modifiers changed from: private */
        public void perform(DefaultClusterRenderer<T>.MarkerModifier markerModifier) {
            MarkerWithPosition markerWithPosition;
            MarkerWithPosition markerWithPosition2;
            if (!DefaultClusterRenderer.this.shouldRenderAsCluster(this.cluster)) {
                for (T t2 : this.cluster.getItems()) {
                    Marker marker = DefaultClusterRenderer.this.mMarkerCache.get(t2);
                    if (marker == null) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        LatLng latLng = this.animateFrom;
                        if (latLng != null) {
                            markerOptions.position(latLng);
                        } else {
                            markerOptions.position(t2.getPosition());
                        }
                        DefaultClusterRenderer.this.onBeforeClusterItemRendered(t2, markerOptions);
                        marker = DefaultClusterRenderer.this.mClusterManager.getMarkerCollection().addMarker(markerOptions);
                        markerWithPosition2 = new MarkerWithPosition(marker);
                        DefaultClusterRenderer.this.mMarkerCache.put(t2, marker);
                        LatLng latLng2 = this.animateFrom;
                        if (latLng2 != null) {
                            markerModifier.animate(markerWithPosition2, latLng2, t2.getPosition());
                        }
                    } else {
                        markerWithPosition2 = new MarkerWithPosition(marker);
                        DefaultClusterRenderer.this.onClusterItemUpdated(t2, marker);
                    }
                    DefaultClusterRenderer.this.onClusterItemRendered(t2, marker);
                    this.newMarkers.add(markerWithPosition2);
                }
                return;
            }
            Marker marker2 = DefaultClusterRenderer.this.mClusterMarkerCache.get(this.cluster);
            if (marker2 == null) {
                MarkerOptions markerOptions2 = new MarkerOptions();
                LatLng latLng3 = this.animateFrom;
                if (latLng3 == null) {
                    latLng3 = this.cluster.getPosition();
                }
                MarkerOptions position = markerOptions2.position(latLng3);
                DefaultClusterRenderer.this.onBeforeClusterRendered(this.cluster, position);
                marker2 = DefaultClusterRenderer.this.mClusterManager.getClusterMarkerCollection().addMarker(position);
                DefaultClusterRenderer.this.mClusterMarkerCache.put(this.cluster, marker2);
                markerWithPosition = new MarkerWithPosition(marker2);
                LatLng latLng4 = this.animateFrom;
                if (latLng4 != null) {
                    markerModifier.animate(markerWithPosition, latLng4, this.cluster.getPosition());
                }
            } else {
                markerWithPosition = new MarkerWithPosition(marker2);
                DefaultClusterRenderer.this.onClusterUpdated(this.cluster, marker2);
            }
            DefaultClusterRenderer.this.onClusterRendered(this.cluster, marker2);
            this.newMarkers.add(markerWithPosition);
        }
    }

    public static class MarkerCache<T> {
        private Map<T, Marker> mCache;
        private Map<Marker, T> mCacheReverse;

        private MarkerCache() {
            this.mCache = new HashMap();
            this.mCacheReverse = new HashMap();
        }

        public Marker get(T t2) {
            return this.mCache.get(t2);
        }

        public void put(T t2, Marker marker) {
            this.mCache.put(t2, marker);
            this.mCacheReverse.put(marker, t2);
        }

        public void remove(Marker marker) {
            T t2 = this.mCacheReverse.get(marker);
            this.mCacheReverse.remove(marker);
            this.mCache.remove(t2);
        }

        public T get(Marker marker) {
            return this.mCacheReverse.get(marker);
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class MarkerModifier extends Handler implements MessageQueue.IdleHandler {
        private static final int BLANK = 0;
        private final Condition busyCondition;
        private final Lock lock;
        private Queue<DefaultClusterRenderer<T>.AnimationTask> mAnimationTasks;
        private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mCreateMarkerTasks;
        private boolean mListenerAdded;
        private Queue<DefaultClusterRenderer<T>.CreateMarkerTask> mOnScreenCreateMarkerTasks;
        private Queue<Marker> mOnScreenRemoveMarkerTasks;
        private Queue<Marker> mRemoveMarkerTasks;

        @TargetApi(11)
        private void performNextTask() {
            if (!this.mOnScreenRemoveMarkerTasks.isEmpty()) {
                removeMarker(this.mOnScreenRemoveMarkerTasks.poll());
            } else if (!this.mAnimationTasks.isEmpty()) {
                this.mAnimationTasks.poll().perform();
            } else if (!this.mOnScreenCreateMarkerTasks.isEmpty()) {
                this.mOnScreenCreateMarkerTasks.poll().perform(this);
            } else if (!this.mCreateMarkerTasks.isEmpty()) {
                this.mCreateMarkerTasks.poll().perform(this);
            } else if (!this.mRemoveMarkerTasks.isEmpty()) {
                removeMarker(this.mRemoveMarkerTasks.poll());
            }
        }

        private void removeMarker(Marker marker) {
            DefaultClusterRenderer.this.mMarkerCache.remove(marker);
            DefaultClusterRenderer.this.mClusterMarkerCache.remove(marker);
            DefaultClusterRenderer.this.mClusterManager.getMarkerManager().remove(marker);
        }

        public void add(boolean z2, DefaultClusterRenderer<T>.CreateMarkerTask createMarkerTask) {
            this.lock.lock();
            sendEmptyMessage(0);
            if (z2) {
                this.mOnScreenCreateMarkerTasks.add(createMarkerTask);
            } else {
                this.mCreateMarkerTasks.add(createMarkerTask);
            }
            this.lock.unlock();
        }

        public void animate(MarkerWithPosition markerWithPosition, LatLng latLng, LatLng latLng2) {
            this.lock.lock();
            this.mAnimationTasks.add(new AnimationTask(markerWithPosition, latLng, latLng2));
            this.lock.unlock();
        }

        @TargetApi(11)
        public void animateThenRemove(MarkerWithPosition markerWithPosition, LatLng latLng, LatLng latLng2) {
            this.lock.lock();
            AnimationTask animationTask = new AnimationTask(markerWithPosition, latLng, latLng2);
            animationTask.removeOnAnimationComplete(DefaultClusterRenderer.this.mClusterManager.getMarkerManager());
            this.mAnimationTasks.add(animationTask);
            this.lock.unlock();
        }

        public void handleMessage(Message message) {
            if (!this.mListenerAdded) {
                Looper.myQueue().addIdleHandler(this);
                this.mListenerAdded = true;
            }
            removeMessages(0);
            this.lock.lock();
            int i3 = 0;
            while (i3 < 10) {
                try {
                    performNextTask();
                    i3++;
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
            if (!isBusy()) {
                this.mListenerAdded = false;
                Looper.myQueue().removeIdleHandler(this);
                this.busyCondition.signalAll();
            } else {
                sendEmptyMessageDelayed(0, 10);
            }
            this.lock.unlock();
        }

        public boolean isBusy() {
            try {
                this.lock.lock();
                return !this.mCreateMarkerTasks.isEmpty() || !this.mOnScreenCreateMarkerTasks.isEmpty() || !this.mOnScreenRemoveMarkerTasks.isEmpty() || !this.mRemoveMarkerTasks.isEmpty() || !this.mAnimationTasks.isEmpty();
            } finally {
                this.lock.unlock();
            }
        }

        public boolean queueIdle() {
            sendEmptyMessage(0);
            return true;
        }

        public void remove(boolean z2, Marker marker) {
            this.lock.lock();
            sendEmptyMessage(0);
            if (z2) {
                this.mOnScreenRemoveMarkerTasks.add(marker);
            } else {
                this.mRemoveMarkerTasks.add(marker);
            }
            this.lock.unlock();
        }

        public void waitUntilFree() {
            while (isBusy()) {
                sendEmptyMessage(0);
                this.lock.lock();
                try {
                    if (isBusy()) {
                        this.busyCondition.await();
                    }
                    this.lock.unlock();
                } catch (InterruptedException e3) {
                    throw new RuntimeException(e3);
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
            }
        }

        private MarkerModifier() {
            super(Looper.getMainLooper());
            ReentrantLock reentrantLock = new ReentrantLock();
            this.lock = reentrantLock;
            this.busyCondition = reentrantLock.newCondition();
            this.mCreateMarkerTasks = new LinkedList();
            this.mOnScreenCreateMarkerTasks = new LinkedList();
            this.mRemoveMarkerTasks = new LinkedList();
            this.mOnScreenRemoveMarkerTasks = new LinkedList();
            this.mAnimationTasks = new LinkedList();
        }
    }

    public static class MarkerWithPosition {
        /* access modifiers changed from: private */
        public final Marker marker;
        /* access modifiers changed from: private */
        public LatLng position;

        public boolean equals(Object obj) {
            if (obj instanceof MarkerWithPosition) {
                return this.marker.equals(((MarkerWithPosition) obj).marker);
            }
            return false;
        }

        public int hashCode() {
            return this.marker.hashCode();
        }

        private MarkerWithPosition(Marker marker2) {
            this.marker = marker2;
            this.position = marker2.getPosition();
        }
    }

    public class RenderTask implements Runnable {
        final Set<? extends Cluster<T>> clusters;
        private Runnable mCallback;
        private float mMapZoom;
        private Projection mProjection;
        private SphericalMercatorProjection mSphericalMercatorProjection;

        @SuppressLint({"NewApi"})
        public void run() {
            LatLngBounds latLngBounds;
            ArrayList arrayList;
            if (this.clusters.equals(DefaultClusterRenderer.this.mClusters)) {
                this.mCallback.run();
                return;
            }
            ArrayList arrayList2 = null;
            MarkerModifier markerModifier = new MarkerModifier();
            float f2 = this.mMapZoom;
            boolean z2 = f2 > DefaultClusterRenderer.this.mZoom;
            float access$1000 = f2 - DefaultClusterRenderer.this.mZoom;
            Set<MarkerWithPosition> access$1300 = DefaultClusterRenderer.this.mMarkers;
            try {
                latLngBounds = this.mProjection.getVisibleRegion().latLngBounds;
            } catch (Exception e3) {
                e3.printStackTrace();
                latLngBounds = LatLngBounds.builder().include(new LatLng(0.0d, 0.0d)).build();
            }
            if (DefaultClusterRenderer.this.mClusters == null || !DefaultClusterRenderer.this.mAnimate) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                for (Cluster cluster : DefaultClusterRenderer.this.mClusters) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(cluster) && latLngBounds.contains(cluster.getPosition())) {
                        arrayList.add(this.mSphericalMercatorProjection.toPoint(cluster.getPosition()));
                    }
                }
            }
            Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
            for (Cluster cluster2 : this.clusters) {
                boolean contains = latLngBounds.contains(cluster2.getPosition());
                if (!z2 || !contains || !DefaultClusterRenderer.this.mAnimate) {
                    markerModifier.add(contains, new CreateMarkerTask(cluster2, newSetFromMap, (LatLng) null));
                } else {
                    Point access$1500 = DefaultClusterRenderer.this.findClosestCluster(arrayList, this.mSphericalMercatorProjection.toPoint(cluster2.getPosition()));
                    if (access$1500 != null) {
                        markerModifier.add(true, new CreateMarkerTask(cluster2, newSetFromMap, this.mSphericalMercatorProjection.toLatLng(access$1500)));
                    } else {
                        markerModifier.add(true, new CreateMarkerTask(cluster2, newSetFromMap, (LatLng) null));
                    }
                }
            }
            markerModifier.waitUntilFree();
            access$1300.removeAll(newSetFromMap);
            if (DefaultClusterRenderer.this.mAnimate) {
                arrayList2 = new ArrayList();
                for (Cluster cluster3 : this.clusters) {
                    if (DefaultClusterRenderer.this.shouldRenderAsCluster(cluster3) && latLngBounds.contains(cluster3.getPosition())) {
                        arrayList2.add(this.mSphericalMercatorProjection.toPoint(cluster3.getPosition()));
                    }
                }
            }
            for (MarkerWithPosition markerWithPosition : access$1300) {
                boolean contains2 = latLngBounds.contains(markerWithPosition.position);
                if (z2 || access$1000 <= -3.0f || !contains2 || !DefaultClusterRenderer.this.mAnimate) {
                    markerModifier.remove(contains2, markerWithPosition.marker);
                } else {
                    Point access$15002 = DefaultClusterRenderer.this.findClosestCluster(arrayList2, this.mSphericalMercatorProjection.toPoint(markerWithPosition.position));
                    if (access$15002 != null) {
                        markerModifier.animateThenRemove(markerWithPosition, markerWithPosition.position, this.mSphericalMercatorProjection.toLatLng(access$15002));
                    } else {
                        markerModifier.remove(true, markerWithPosition.marker);
                    }
                }
            }
            markerModifier.waitUntilFree();
            Set unused = DefaultClusterRenderer.this.mMarkers = newSetFromMap;
            Set unused2 = DefaultClusterRenderer.this.mClusters = this.clusters;
            float unused3 = DefaultClusterRenderer.this.mZoom = f2;
            this.mCallback.run();
        }

        public void setCallback(Runnable runnable) {
            this.mCallback = runnable;
        }

        public void setMapZoom(float f2) {
            this.mMapZoom = f2;
            this.mSphericalMercatorProjection = new SphericalMercatorProjection(Math.pow(2.0d, (double) Math.min(f2, DefaultClusterRenderer.this.mZoom)) * 256.0d);
        }

        public void setProjection(Projection projection) {
            this.mProjection = projection;
        }

        private RenderTask(Set<? extends Cluster<T>> set) {
            this.clusters = set;
        }
    }

    @SuppressLint({"HandlerLeak"})
    public class ViewModifier extends Handler {
        private static final int RUN_TASK = 0;
        private static final int TASK_FINISHED = 1;
        private DefaultClusterRenderer<T>.RenderTask mNextClusters;
        private boolean mViewModificationInProgress;

        private ViewModifier() {
            this.mViewModificationInProgress = false;
            this.mNextClusters = null;
        }

        public void handleMessage(Message message) {
            DefaultClusterRenderer<T>.RenderTask renderTask;
            if (message.what == 1) {
                this.mViewModificationInProgress = false;
                if (this.mNextClusters != null) {
                    sendEmptyMessage(0);
                    return;
                }
                return;
            }
            removeMessages(0);
            if (!this.mViewModificationInProgress && this.mNextClusters != null) {
                Projection projection = DefaultClusterRenderer.this.mMap.getProjection();
                synchronized (this) {
                    renderTask = this.mNextClusters;
                    this.mNextClusters = null;
                    this.mViewModificationInProgress = true;
                }
                renderTask.setCallback(new Runnable() {
                    public void run() {
                        ViewModifier.this.sendEmptyMessage(1);
                    }
                });
                renderTask.setProjection(projection);
                renderTask.setMapZoom(DefaultClusterRenderer.this.mMap.getCameraPosition().zoom);
                new Thread(renderTask).start();
            }
        }

        public void queue(Set<? extends Cluster<T>> set) {
            synchronized (this) {
                this.mNextClusters = new RenderTask(set);
            }
            sendEmptyMessage(0);
        }
    }

    public DefaultClusterRenderer(Context context, GoogleMap googleMap, ClusterManager<T> clusterManager) {
        this.mMap = googleMap;
        this.mAnimate = true;
        this.mDensity = context.getResources().getDisplayMetrics().density;
        IconGenerator iconGenerator = new IconGenerator(context);
        this.mIconGenerator = iconGenerator;
        iconGenerator.setContentView(makeSquareTextView(context));
        iconGenerator.setTextAppearance(R.style.amu_ClusterIcon_TextAppearance);
        iconGenerator.setBackground(makeClusterBackground());
        this.mClusterManager = clusterManager;
    }

    private static double distanceSquared(Point point, Point point2) {
        double d2 = point.f7188x;
        double d3 = point2.f7188x;
        double d4 = d2 - d3;
        double d5 = point.f7189y;
        double d6 = point2.f7189y;
        return a.a(d5, d6, d5 - d6, (d2 - d3) * d4);
    }

    /* access modifiers changed from: private */
    public Point findClosestCluster(List<Point> list, Point point) {
        Point point2 = null;
        if (list != null && !list.isEmpty()) {
            int maxDistanceBetweenClusteredItems = this.mClusterManager.getAlgorithm().getMaxDistanceBetweenClusteredItems();
            double d2 = (double) (maxDistanceBetweenClusteredItems * maxDistanceBetweenClusteredItems);
            for (Point next : list) {
                double distanceSquared = distanceSquared(next, point);
                if (distanceSquared < d2) {
                    point2 = next;
                    d2 = distanceSquared;
                }
            }
        }
        return point2;
    }

    private LayerDrawable makeClusterBackground() {
        this.mColoredCircleBackground = new ShapeDrawable(new OvalShape());
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(-2130706433);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{shapeDrawable, this.mColoredCircleBackground});
        int i3 = (int) (this.mDensity * 3.0f);
        layerDrawable.setLayerInset(1, i3, i3, i3, i3);
        return layerDrawable;
    }

    private SquareTextView makeSquareTextView(Context context) {
        SquareTextView squareTextView = new SquareTextView(context);
        squareTextView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        squareTextView.setId(R.id.amu_text);
        int i3 = (int) (this.mDensity * 12.0f);
        squareTextView.setPadding(i3, i3, i3, i3);
        return squareTextView;
    }

    public int getBucket(Cluster<T> cluster) {
        int size = cluster.getSize();
        int i3 = 0;
        if (size <= BUCKETS[0]) {
            return size;
        }
        while (true) {
            int[] iArr = BUCKETS;
            if (i3 >= iArr.length - 1) {
                return iArr[iArr.length - 1];
            }
            int i4 = i3 + 1;
            if (size < iArr[i4]) {
                return iArr[i3];
            }
            i3 = i4;
        }
    }

    public Cluster<T> getCluster(Marker marker) {
        return this.mClusterMarkerCache.get(marker);
    }

    public T getClusterItem(Marker marker) {
        return (ClusterItem) this.mMarkerCache.get(marker);
    }

    public String getClusterText(int i3) {
        if (i3 < BUCKETS[0]) {
            return String.valueOf(i3);
        }
        return i3 + org.slf4j.Marker.ANY_NON_NULL_MARKER;
    }

    public int getColor(int i3) {
        float min = 300.0f - Math.min((float) i3, 300.0f);
        return Color.HSVToColor(new float[]{((min * min) / 90000.0f) * 220.0f, 1.0f, 0.6f});
    }

    public BitmapDescriptor getDescriptorForCluster(Cluster<T> cluster) {
        int bucket = getBucket(cluster);
        BitmapDescriptor bitmapDescriptor = this.mIcons.get(bucket);
        if (bitmapDescriptor != null) {
            return bitmapDescriptor;
        }
        this.mColoredCircleBackground.getPaint().setColor(getColor(bucket));
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(this.mIconGenerator.makeIcon(getClusterText(bucket)));
        this.mIcons.put(bucket, fromBitmap);
        return fromBitmap;
    }

    public Marker getMarker(T t2) {
        return this.mMarkerCache.get(t2);
    }

    public int getMinClusterSize() {
        return this.mMinClusterSize;
    }

    public void onAdd() {
        this.mClusterManager.getMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.mItemClickListener != null && DefaultClusterRenderer.this.mItemClickListener.onClusterItemClick((ClusterItem) DefaultClusterRenderer.this.mMarkerCache.get(marker));
            }
        });
        this.mClusterManager.getMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.mItemInfoWindowClickListener != null) {
                    DefaultClusterRenderer.this.mItemInfoWindowClickListener.onClusterItemInfoWindowClick((ClusterItem) DefaultClusterRenderer.this.mMarkerCache.get(marker));
                }
            }
        });
        this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                return DefaultClusterRenderer.this.mClickListener != null && DefaultClusterRenderer.this.mClickListener.onClusterClick((Cluster) DefaultClusterRenderer.this.mClusterMarkerCache.get(marker));
            }
        });
        this.mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            public void onInfoWindowClick(Marker marker) {
                if (DefaultClusterRenderer.this.mInfoWindowClickListener != null) {
                    DefaultClusterRenderer.this.mInfoWindowClickListener.onClusterInfoWindowClick((Cluster) DefaultClusterRenderer.this.mClusterMarkerCache.get(marker));
                }
            }
        });
    }

    public void onBeforeClusterItemRendered(T t2, MarkerOptions markerOptions) {
        if (t2.getTitle() != null && t2.getSnippet() != null) {
            markerOptions.title(t2.getTitle());
            markerOptions.snippet(t2.getSnippet());
        } else if (t2.getTitle() != null) {
            markerOptions.title(t2.getTitle());
        } else if (t2.getSnippet() != null) {
            markerOptions.title(t2.getSnippet());
        }
    }

    public void onBeforeClusterRendered(Cluster<T> cluster, MarkerOptions markerOptions) {
        markerOptions.icon(getDescriptorForCluster(cluster));
    }

    public void onClusterItemRendered(T t2, Marker marker) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClusterItemUpdated(T r4, com.google.android.gms.maps.model.Marker r5) {
        /*
            r3 = this;
            java.lang.String r3 = r4.getTitle()
            r0 = 1
            r1 = 0
            if (r3 == 0) goto L_0x003b
            java.lang.String r3 = r4.getSnippet()
            if (r3 == 0) goto L_0x003b
            java.lang.String r3 = r5.getTitle()
            java.lang.String r2 = r4.getTitle()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0024
            java.lang.String r3 = r4.getTitle()
            r5.setTitle(r3)
            r1 = r0
        L_0x0024:
            java.lang.String r3 = r5.getSnippet()
            java.lang.String r2 = r4.getSnippet()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0073
            java.lang.String r3 = r4.getSnippet()
            r5.setSnippet(r3)
        L_0x0039:
            r1 = r0
            goto L_0x0073
        L_0x003b:
            java.lang.String r3 = r4.getSnippet()
            if (r3 == 0) goto L_0x0057
            java.lang.String r3 = r4.getSnippet()
            java.lang.String r2 = r5.getTitle()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0057
            java.lang.String r3 = r4.getSnippet()
            r5.setTitle(r3)
            goto L_0x0039
        L_0x0057:
            java.lang.String r3 = r4.getTitle()
            if (r3 == 0) goto L_0x0073
            java.lang.String r3 = r4.getTitle()
            java.lang.String r2 = r5.getTitle()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0073
            java.lang.String r3 = r4.getTitle()
            r5.setTitle(r3)
            goto L_0x0039
        L_0x0073:
            com.google.android.gms.maps.model.LatLng r3 = r5.getPosition()
            com.google.android.gms.maps.model.LatLng r2 = r4.getPosition()
            boolean r3 = r3.equals(r2)
            if (r3 != 0) goto L_0x0089
            com.google.android.gms.maps.model.LatLng r3 = r4.getPosition()
            r5.setPosition(r3)
            goto L_0x008a
        L_0x0089:
            r0 = r1
        L_0x008a:
            if (r0 == 0) goto L_0x0095
            boolean r3 = r5.isInfoWindowShown()
            if (r3 == 0) goto L_0x0095
            r5.showInfoWindow()
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.clustering.view.DefaultClusterRenderer.onClusterItemUpdated(com.google.maps.android.clustering.ClusterItem, com.google.android.gms.maps.model.Marker):void");
    }

    public void onClusterRendered(Cluster<T> cluster, Marker marker) {
    }

    public void onClusterUpdated(Cluster<T> cluster, Marker marker) {
        marker.setIcon(getDescriptorForCluster(cluster));
    }

    public void onClustersChanged(Set<? extends Cluster<T>> set) {
        this.mViewModifier.queue(set);
    }

    public void onRemove() {
        this.mClusterManager.getMarkerCollection().setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) null);
        this.mClusterManager.getMarkerCollection().setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) null);
        this.mClusterManager.getClusterMarkerCollection().setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) null);
        this.mClusterManager.getClusterMarkerCollection().setOnInfoWindowClickListener((GoogleMap.OnInfoWindowClickListener) null);
    }

    public void setAnimation(boolean z2) {
        this.mAnimate = z2;
    }

    public void setMinClusterSize(int i3) {
        this.mMinClusterSize = i3;
    }

    public void setOnClusterClickListener(ClusterManager.OnClusterClickListener<T> onClusterClickListener) {
        this.mClickListener = onClusterClickListener;
    }

    public void setOnClusterInfoWindowClickListener(ClusterManager.OnClusterInfoWindowClickListener<T> onClusterInfoWindowClickListener) {
        this.mInfoWindowClickListener = onClusterInfoWindowClickListener;
    }

    public void setOnClusterItemClickListener(ClusterManager.OnClusterItemClickListener<T> onClusterItemClickListener) {
        this.mItemClickListener = onClusterItemClickListener;
    }

    public void setOnClusterItemInfoWindowClickListener(ClusterManager.OnClusterItemInfoWindowClickListener<T> onClusterItemInfoWindowClickListener) {
        this.mItemInfoWindowClickListener = onClusterItemInfoWindowClickListener;
    }

    public boolean shouldRenderAsCluster(Cluster<T> cluster) {
        return cluster.getSize() > this.mMinClusterSize;
    }

    public Marker getMarker(Cluster<T> cluster) {
        return this.mClusterMarkerCache.get(cluster);
    }
}
