package com.airbnb.lottie;

import A.a;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieThreadFactory;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    public static final int INFINITE = -1;
    private static final float MAX_DELTA_MS_ASYNC_SET_PROGRESS = 50.0f;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private static final boolean invalidateSelfOnMainThread = false;
    private static final Executor setProgressExecutor = new ThreadPoolExecutor(0, 2, 35, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new LottieThreadFactory());
    private int alpha = 255;
    private final LottieValueAnimator animator;
    @Nullable
    private AsyncUpdates asyncUpdates;
    private Rect canvasClipBounds;
    private RectF canvasClipBoundsRectF;
    private boolean clipToCompositionBounds = true;
    private LottieComposition composition;
    @Nullable
    private CompositionLayer compositionLayer;
    @Nullable
    String defaultFontFileExtension;
    private boolean enableMergePaths;
    @Nullable
    FontAssetDelegate fontAssetDelegate;
    @Nullable
    private FontAssetManager fontAssetManager;
    @Nullable
    private Map<String, Typeface> fontMap;
    private boolean ignoreSystemAnimationsDisabled = false;
    @Nullable
    private ImageAssetDelegate imageAssetDelegate;
    @Nullable
    private ImageAssetManager imageAssetManager;
    @Nullable
    private String imageAssetsFolder;
    private Runnable invalidateSelfRunnable;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isDirty = false;
    private float lastDrawnProgress;
    private final ArrayList<LazyCompositionTask> lazyCompositionTasks = new ArrayList<>();
    private Handler mainThreadHandler;
    private boolean maintainOriginalImageBounds = false;
    private OnVisibleAction onVisibleAction = OnVisibleAction.NONE;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    private RenderMode renderMode = RenderMode.AUTOMATIC;
    private final Matrix renderingMatrix = new Matrix();
    private boolean safeMode = false;
    private final Semaphore setProgressDrawLock;
    private Bitmap softwareRenderingBitmap;
    private Canvas softwareRenderingCanvas;
    private Rect softwareRenderingDstBoundsRect;
    private RectF softwareRenderingDstBoundsRectF;
    private Matrix softwareRenderingOriginalCanvasMatrix;
    private Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private Paint softwareRenderingPaint;
    private Rect softwareRenderingSrcBoundsRect;
    private RectF softwareRenderingTransformedBounds;
    private boolean systemAnimationsEnabled = true;
    @Nullable
    TextDelegate textDelegate;
    private final Runnable updateProgressRunnable;
    private boolean useSoftwareRendering = false;

    public interface LazyCompositionTask {
        void run(LottieComposition lottieComposition);
    }

    public enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.animator = lottieValueAnimator;
        v vVar = new v(this);
        this.progressUpdateListener = vVar;
        this.setProgressDrawLock = new Semaphore(1);
        this.updateProgressRunnable = new s(this, 1);
        this.lastDrawnProgress = -3.4028235E38f;
        lottieValueAnimator.addUpdateListener(vVar);
    }

    private boolean animationsEnabled() {
        return this.systemAnimationsEnabled || this.ignoreSystemAnimationsDisabled;
    }

    private void buildCompositionLayer() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            CompositionLayer compositionLayer2 = new CompositionLayer(this, LayerParser.parse(lottieComposition), lottieComposition.getLayers(), lottieComposition);
            this.compositionLayer = compositionLayer2;
            if (this.outlineMasksAndMattes) {
                compositionLayer2.setOutlineMasksAndMattes(true);
            }
            this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
        }
    }

    private void computeRenderMode() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            this.useSoftwareRendering = this.renderMode.useSoftwareRendering(Build.VERSION.SDK_INT, lottieComposition.hasDashPattern(), lottieComposition.getMaskAndMatteCount());
        }
    }

    private void convertRect(RectF rectF, Rect rect) {
        rect.set((int) Math.floor((double) rectF.left), (int) Math.floor((double) rectF.top), (int) Math.ceil((double) rectF.right), (int) Math.ceil((double) rectF.bottom));
    }

    private void drawDirectlyToCanvas(Canvas canvas) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        LottieComposition lottieComposition = this.composition;
        if (compositionLayer2 != null && lottieComposition != null) {
            this.renderingMatrix.reset();
            Rect bounds = getBounds();
            if (!bounds.isEmpty()) {
                this.renderingMatrix.preScale(((float) bounds.width()) / ((float) lottieComposition.getBounds().width()), ((float) bounds.height()) / ((float) lottieComposition.getBounds().height()));
                this.renderingMatrix.preTranslate((float) bounds.left, (float) bounds.top);
            }
            compositionLayer2.draw(canvas, this.renderingMatrix, this.alpha);
        }
    }

    private void ensureSoftwareRenderingBitmap(int i3, int i4) {
        Bitmap bitmap = this.softwareRenderingBitmap;
        if (bitmap == null || bitmap.getWidth() < i3 || this.softwareRenderingBitmap.getHeight() < i4) {
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Bitmap.Config.ARGB_8888);
            this.softwareRenderingBitmap = createBitmap;
            this.softwareRenderingCanvas.setBitmap(createBitmap);
            this.isDirty = true;
        } else if (this.softwareRenderingBitmap.getWidth() > i3 || this.softwareRenderingBitmap.getHeight() > i4) {
            Bitmap createBitmap2 = Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, i3, i4);
            this.softwareRenderingBitmap = createBitmap2;
            this.softwareRenderingCanvas.setBitmap(createBitmap2);
            this.isDirty = true;
        }
    }

    private void ensureSoftwareRenderingObjectsInitialized() {
        if (this.softwareRenderingCanvas == null) {
            this.softwareRenderingCanvas = new Canvas();
            this.softwareRenderingTransformedBounds = new RectF();
            this.softwareRenderingOriginalCanvasMatrix = new Matrix();
            this.softwareRenderingOriginalCanvasMatrixInverse = new Matrix();
            this.canvasClipBounds = new Rect();
            this.canvasClipBoundsRectF = new RectF();
            this.softwareRenderingPaint = new LPaint();
            this.softwareRenderingSrcBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRectF = new RectF();
        }
    }

    @Nullable
    private Context getContext() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    private FontAssetManager getFontAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        if (this.fontAssetManager == null) {
            FontAssetManager fontAssetManager2 = new FontAssetManager(getCallback(), this.fontAssetDelegate);
            this.fontAssetManager = fontAssetManager2;
            String str = this.defaultFontFileExtension;
            if (str != null) {
                fontAssetManager2.setDefaultFontFileExtension(str);
            }
        }
        return this.fontAssetManager;
    }

    private ImageAssetManager getImageAssetManager() {
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null && !imageAssetManager2.hasSameContext(getContext())) {
            this.imageAssetManager = null;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    private boolean ignoreCanvasClipBounds() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        ViewParent parent = ((View) callback).getParent();
        if (parent instanceof ViewGroup) {
            return !((ViewGroup) parent).getClipChildren();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addValueCallback$17(KeyPath keyPath, Object obj, LottieValueCallback lottieValueCallback, LottieComposition lottieComposition) {
        addValueCallback(keyPath, obj, lottieValueCallback);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(ValueAnimator valueAnimator) {
        if (getAsyncUpdatesEnabled()) {
            invalidateSelf();
            return;
        }
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            compositionLayer2.setProgress(this.animator.getAnimatedValueAbsolute());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1() {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$2() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            try {
                this.setProgressDrawLock.acquire();
                compositionLayer2.setProgress(this.animator.getAnimatedValueAbsolute());
                if (invalidateSelfOnMainThread && this.isDirty) {
                    if (this.mainThreadHandler == null) {
                        this.mainThreadHandler = new Handler(Looper.getMainLooper());
                        this.invalidateSelfRunnable = new s(this, 0);
                    }
                    this.mainThreadHandler.post(this.invalidateSelfRunnable);
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                this.setProgressDrawLock.release();
                throw th;
            }
            this.setProgressDrawLock.release();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playAnimation$3(LottieComposition lottieComposition) {
        playAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumeAnimation$4(LottieComposition lottieComposition) {
        resumeAnimation();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFrame$15(int i3, LottieComposition lottieComposition) {
        setFrame(i3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxFrame$10(String str, LottieComposition lottieComposition) {
        setMaxFrame(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxFrame$7(int i3, LottieComposition lottieComposition) {
        setMaxFrame(i3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMaxProgress$8(float f2, LottieComposition lottieComposition) {
        setMaxProgress(f2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxFrame$11(String str, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxFrame$12(String str, String str2, boolean z2, LottieComposition lottieComposition) {
        setMinAndMaxFrame(str, str2, z2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxFrame$13(int i3, int i4, LottieComposition lottieComposition) {
        setMinAndMaxFrame(i3, i4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinAndMaxProgress$14(float f2, float f3, LottieComposition lottieComposition) {
        setMinAndMaxProgress(f2, f3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinFrame$5(int i3, LottieComposition lottieComposition) {
        setMinFrame(i3);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinFrame$9(String str, LottieComposition lottieComposition) {
        setMinFrame(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMinProgress$6(float f2, LottieComposition lottieComposition) {
        setMinProgress(f2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setProgress$16(float f2, LottieComposition lottieComposition) {
        setProgress(f2);
    }

    private void renderAndDrawAsBitmap(Canvas canvas, CompositionLayer compositionLayer2) {
        if (this.composition != null && compositionLayer2 != null) {
            ensureSoftwareRenderingObjectsInitialized();
            canvas.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
            canvas.getClipBounds(this.canvasClipBounds);
            convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
            convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
            if (this.clipToCompositionBounds) {
                this.softwareRenderingTransformedBounds.set(0.0f, 0.0f, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
            } else {
                compositionLayer2.getBounds(this.softwareRenderingTransformedBounds, (Matrix) null, false);
            }
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
            Rect bounds = getBounds();
            float width = ((float) bounds.width()) / ((float) getIntrinsicWidth());
            float height = ((float) bounds.height()) / ((float) getIntrinsicHeight());
            scaleRect(this.softwareRenderingTransformedBounds, width, height);
            if (!ignoreCanvasClipBounds()) {
                RectF rectF = this.softwareRenderingTransformedBounds;
                Rect rect = this.canvasClipBounds;
                rectF.intersect((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
            }
            int ceil = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.width());
            int ceil2 = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.height());
            if (ceil > 0 && ceil2 > 0) {
                ensureSoftwareRenderingBitmap(ceil, ceil2);
                if (this.isDirty) {
                    this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
                    this.renderingMatrix.preScale(width, height);
                    Matrix matrix = this.renderingMatrix;
                    RectF rectF2 = this.softwareRenderingTransformedBounds;
                    matrix.postTranslate(-rectF2.left, -rectF2.top);
                    this.softwareRenderingBitmap.eraseColor(0);
                    compositionLayer2.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha);
                    this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
                    this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
                    convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
                }
                this.softwareRenderingSrcBoundsRect.set(0, 0, ceil, ceil2);
                canvas.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
            }
        }
    }

    private void scaleRect(RectF rectF, float f2, float f3) {
        rectF.set(rectF.left * f2, rectF.top * f3, rectF.right * f2, rectF.bottom * f3);
    }

    private boolean shouldSetProgressBeforeDrawing() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return false;
        }
        float f2 = this.lastDrawnProgress;
        float animatedValueAbsolute = this.animator.getAnimatedValueAbsolute();
        this.lastDrawnProgress = animatedValueAbsolute;
        return Math.abs(animatedValueAbsolute - f2) * lottieComposition.getDuration() >= 50.0f;
    }

    public void addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animator.addListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void addAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.addPauseListener(animatorPauseListener);
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.addUpdateListener(animatorUpdateListener);
    }

    public <T> void addValueCallback(KeyPath keyPath, T t2, @Nullable LottieValueCallback<T> lottieValueCallback) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 == null) {
            this.lazyCompositionTasks.add(new q(this, keyPath, t2, lottieValueCallback));
            return;
        }
        boolean z2 = true;
        if (keyPath == KeyPath.COMPOSITION) {
            compositionLayer2.addValueCallback(t2, lottieValueCallback);
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(t2, lottieValueCallback);
        } else {
            List<KeyPath> resolveKeyPath = resolveKeyPath(keyPath);
            for (int i3 = 0; i3 < resolveKeyPath.size(); i3++) {
                resolveKeyPath.get(i3).getResolvedElement().addValueCallback(t2, lottieValueCallback);
            }
            z2 = true ^ resolveKeyPath.isEmpty();
        }
        if (z2) {
            invalidateSelf();
            if (t2 == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void clearComposition() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.lastDrawnProgress = -3.4028235E38f;
        this.animator.clearComposition();
        invalidateSelf();
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
    }

    public void draw(@NonNull Canvas canvas) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        if (compositionLayer2 != null) {
            boolean asyncUpdatesEnabled = getAsyncUpdatesEnabled();
            if (asyncUpdatesEnabled) {
                try {
                    this.setProgressDrawLock.acquire();
                } catch (InterruptedException unused) {
                    L.endSection("Drawable#draw");
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    L.endSection("Drawable#draw");
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() != this.animator.getAnimatedValueAbsolute()) {
                            setProgressExecutor.execute(this.updateProgressRunnable);
                        }
                    }
                    throw th;
                }
            }
            L.beginSection("Drawable#draw");
            if (asyncUpdatesEnabled && shouldSetProgressBeforeDrawing()) {
                setProgress(this.animator.getAnimatedValueAbsolute());
            }
            if (this.safeMode) {
                try {
                    if (this.useSoftwareRendering) {
                        renderAndDrawAsBitmap(canvas, compositionLayer2);
                    } else {
                        drawDirectlyToCanvas(canvas);
                    }
                } catch (Throwable th2) {
                    Logger.error("Lottie crashed in draw!", th2);
                }
            } else if (this.useSoftwareRendering) {
                renderAndDrawAsBitmap(canvas, compositionLayer2);
            } else {
                drawDirectlyToCanvas(canvas);
            }
            this.isDirty = false;
            L.endSection("Drawable#draw");
            if (asyncUpdatesEnabled) {
                this.setProgressDrawLock.release();
                if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                    return;
                }
                setProgressExecutor.execute(this.updateProgressRunnable);
            }
        }
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    @MainThread
    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public int getAlpha() {
        return this.alpha;
    }

    public AsyncUpdates getAsyncUpdates() {
        AsyncUpdates asyncUpdates2 = this.asyncUpdates;
        return asyncUpdates2 != null ? asyncUpdates2 : L.getDefaultAsyncUpdates();
    }

    public boolean getAsyncUpdatesEnabled() {
        return getAsyncUpdates() == AsyncUpdates.ENABLED;
    }

    @Nullable
    public Bitmap getBitmapForId(String str) {
        ImageAssetManager imageAssetManager2 = getImageAssetManager();
        if (imageAssetManager2 != null) {
            return imageAssetManager2.bitmapForId(str);
        }
        return null;
    }

    public boolean getClipToCompositionBounds() {
        return this.clipToCompositionBounds;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public int getFrame() {
        return (int) this.animator.getFrame();
    }

    @Deprecated
    @Nullable
    public Bitmap getImageAsset(String str) {
        ImageAssetManager imageAssetManager2 = getImageAssetManager();
        if (imageAssetManager2 != null) {
            return imageAssetManager2.bitmapForId(str);
        }
        LottieComposition lottieComposition = this.composition;
        LottieImageAsset lottieImageAsset = lottieComposition == null ? null : lottieComposition.getImages().get(str);
        if (lottieImageAsset != null) {
            return lottieImageAsset.getBitmap();
        }
        return null;
    }

    @Nullable
    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().height();
    }

    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().width();
    }

    @Nullable
    public LottieImageAsset getLottieImageAssetForId(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return null;
        }
        return lottieComposition.getImages().get(str);
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.maintainOriginalImageBounds;
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    public int getOpacity() {
        return -3;
    }

    @Nullable
    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            return lottieComposition.getPerformanceTracker();
        }
        return null;
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    public RenderMode getRenderMode() {
        return this.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    @SuppressLint({"WrongConstant"})
    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    @Nullable
    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Typeface getTypeface(Font font) {
        Map<String, Typeface> map = this.fontMap;
        if (map != null) {
            String family = font.getFamily();
            if (map.containsKey(family)) {
                return map.get(family);
            }
            String name = font.getName();
            if (map.containsKey(name)) {
                return map.get(name);
            }
            String str = font.getFamily() + "-" + font.getStyle();
            if (map.containsKey(str)) {
                return map.get(str);
            }
        }
        FontAssetManager fontAssetManager2 = getFontAssetManager();
        if (fontAssetManager2 != null) {
            return fontAssetManager2.getTypeface(font);
        }
        return null;
    }

    public boolean hasMasks() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        return compositionLayer2 != null && compositionLayer2.hasMasks();
    }

    public boolean hasMatte() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        return compositionLayer2 != null && compositionLayer2.hasMatte();
    }

    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void invalidateSelf() {
        Drawable.Callback callback;
        if (!this.isDirty) {
            this.isDirty = true;
            if ((!invalidateSelfOnMainThread || Looper.getMainLooper() == Looper.myLooper()) && (callback = getCallback()) != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.animator;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    public boolean isAnimatingOrWillAnimateOnVisible() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        OnVisibleAction onVisibleAction2 = this.onVisibleAction;
        return onVisibleAction2 == OnVisibleAction.PLAY || onVisibleAction2 == OnVisibleAction.RESUME;
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public boolean isRunning() {
        return isAnimating();
    }

    @Deprecated
    public void loop(boolean z2) {
        this.animator.setRepeatCount(z2 ? -1 : 0);
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    @MainThread
    public void playAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new t(this, 1));
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.playAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.PLAY;
            }
        }
        if (!animationsEnabled()) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    public void removeAllAnimatorListeners() {
        this.animator.removeAllListeners();
    }

    public void removeAllUpdateListeners() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animator.removeListener(animatorListener);
    }

    @RequiresApi(api = 19)
    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener animatorPauseListener) {
        this.animator.removePauseListener(animatorPauseListener);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        this.animator.removeUpdateListener(animatorUpdateListener);
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        if (this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        this.compositionLayer.resolveKeyPath(keyPath, 0, arrayList, new KeyPath(new String[0]));
        return arrayList;
    }

    @MainThread
    public void resumeAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new t(this, 0));
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.resumeAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.RESUME;
            }
        }
        if (!animationsEnabled()) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long j2) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j2);
        }
    }

    public void setAlpha(@IntRange(from = 0, to = 255) int i3) {
        this.alpha = i3;
        invalidateSelf();
    }

    public void setApplyingOpacityToLayersEnabled(boolean z2) {
        this.isApplyingOpacityToLayersEnabled = z2;
    }

    public void setAsyncUpdates(@Nullable AsyncUpdates asyncUpdates2) {
        this.asyncUpdates = asyncUpdates2;
    }

    public void setClipToCompositionBounds(boolean z2) {
        if (z2 != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = z2;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setClipToCompositionBounds(z2);
            }
            invalidateSelf();
        }
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    public boolean setComposition(LottieComposition lottieComposition) {
        if (this.composition == lottieComposition) {
            return false;
        }
        this.isDirty = true;
        clearComposition();
        this.composition = lottieComposition;
        buildCompositionLayer();
        this.animator.setComposition(lottieComposition);
        setProgress(this.animator.getAnimatedFraction());
        Iterator it = new ArrayList(this.lazyCompositionTasks).iterator();
        while (it.hasNext()) {
            LazyCompositionTask lazyCompositionTask = (LazyCompositionTask) it.next();
            if (lazyCompositionTask != null) {
                lazyCompositionTask.run(lottieComposition);
            }
            it.remove();
        }
        this.lazyCompositionTasks.clear();
        lottieComposition.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        computeRenderMode();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ImageView imageView = (ImageView) callback;
            imageView.setImageDrawable((Drawable) null);
            imageView.setImageDrawable(this);
        }
        return true;
    }

    public void setDefaultFontFileExtension(String str) {
        this.defaultFontFileExtension = str;
        FontAssetManager fontAssetManager2 = getFontAssetManager();
        if (fontAssetManager2 != null) {
            fontAssetManager2.setDefaultFontFileExtension(str);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate fontAssetDelegate2) {
        this.fontAssetDelegate = fontAssetDelegate2;
        FontAssetManager fontAssetManager2 = this.fontAssetManager;
        if (fontAssetManager2 != null) {
            fontAssetManager2.setDelegate(fontAssetDelegate2);
        }
    }

    public void setFontMap(@Nullable Map<String, Typeface> map) {
        if (map != this.fontMap) {
            this.fontMap = map;
            invalidateSelf();
        }
    }

    public void setFrame(int i3) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new n(this, i3, 0));
        } else {
            this.animator.setFrame((float) i3);
        }
    }

    public void setIgnoreDisabledSystemAnimations(boolean z2) {
        this.ignoreSystemAnimationsDisabled = z2;
    }

    public void setImageAssetDelegate(ImageAssetDelegate imageAssetDelegate2) {
        this.imageAssetDelegate = imageAssetDelegate2;
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null) {
            imageAssetManager2.setDelegate(imageAssetDelegate2);
        }
    }

    public void setImagesAssetsFolder(@Nullable String str) {
        this.imageAssetsFolder = str;
    }

    public void setMaintainOriginalImageBounds(boolean z2) {
        this.maintainOriginalImageBounds = z2;
    }

    public void setMaxFrame(int i3) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new n(this, i3, 1));
        } else {
            this.animator.setMaxFrame(((float) i3) + 0.99f);
        }
    }

    public void setMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new C0215r(this, f2, 0));
        } else {
            this.animator.setMaxFrame(MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f2));
        }
    }

    public void setMinAndMaxFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new m(this, str, 0));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            int i3 = (int) marker.startFrame;
            setMinAndMaxFrame(i3, ((int) marker.durationFrames) + i3);
            return;
        }
        throw new IllegalArgumentException(a.l("Cannot find marker with name ", str, JwtUtilsKt.JWT_DELIMITER));
    }

    public void setMinAndMaxProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new o(this, f2, f3));
        } else {
            setMinAndMaxFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f2), (int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), f3));
        }
    }

    public void setMinFrame(int i3) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new n(this, i3, 2));
        } else {
            this.animator.setMinFrame(i3);
        }
    }

    public void setMinProgress(float f2) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new C0215r(this, f2, 1));
        } else {
            setMinFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), f2));
        }
    }

    public void setOutlineMasksAndMattes(boolean z2) {
        if (this.outlineMasksAndMattes != z2) {
            this.outlineMasksAndMattes = z2;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setOutlineMasksAndMattes(z2);
            }
        }
    }

    public void setPerformanceTrackingEnabled(boolean z2) {
        this.performanceTrackingEnabled = z2;
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            lottieComposition.setPerformanceTrackingEnabled(z2);
        }
    }

    public void setProgress(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new C0215r(this, f2, 2));
            return;
        }
        L.beginSection("Drawable#setProgress");
        this.animator.setFrame(this.composition.getFrameForProgress(f2));
        L.endSection("Drawable#setProgress");
    }

    public void setRenderMode(RenderMode renderMode2) {
        this.renderMode = renderMode2;
        computeRenderMode();
    }

    public void setRepeatCount(int i3) {
        this.animator.setRepeatCount(i3);
    }

    public void setRepeatMode(int i3) {
        this.animator.setRepeatMode(i3);
    }

    public void setSafeMode(boolean z2) {
        this.safeMode = z2;
    }

    public void setSpeed(float f2) {
        this.animator.setSpeed(f2);
    }

    public void setSystemAnimationsAreEnabled(Boolean bool) {
        this.systemAnimationsEnabled = bool.booleanValue();
    }

    public void setTextDelegate(TextDelegate textDelegate2) {
        this.textDelegate = textDelegate2;
    }

    public void setUseCompositionFrameRate(boolean z2) {
        this.animator.setUseCompositionFrameRate(z2);
    }

    public boolean setVisible(boolean z2, boolean z3) {
        boolean isVisible = isVisible();
        boolean visible = super.setVisible(z2, z3);
        if (z2) {
            OnVisibleAction onVisibleAction2 = this.onVisibleAction;
            if (onVisibleAction2 == OnVisibleAction.PLAY) {
                playAnimation();
            } else if (onVisibleAction2 == OnVisibleAction.RESUME) {
                resumeAnimation();
            }
        } else if (this.animator.isRunning()) {
            pauseAnimation();
            this.onVisibleAction = OnVisibleAction.RESUME;
        } else if (isVisible) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
        return visible;
    }

    @MainThread
    public void start() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View) || !((View) callback).isInEditMode()) {
            playAnimation();
        }
    }

    @MainThread
    public void stop() {
        endAnimation();
    }

    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    @Nullable
    public Bitmap updateBitmap(String str, @Nullable Bitmap bitmap) {
        ImageAssetManager imageAssetManager2 = getImageAssetManager();
        if (imageAssetManager2 == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap updateBitmap = imageAssetManager2.updateBitmap(str, bitmap);
        invalidateSelf();
        return updateBitmap;
    }

    public boolean useTextGlyphs() {
        return this.fontMap == null && this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }

    public void enableMergePathsForKitKatAndAbove(boolean z2) {
        if (this.enableMergePaths != z2) {
            this.enableMergePaths = z2;
            if (this.composition != null) {
                buildCompositionLayer();
            }
        }
    }

    public void setMaxFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new m(this, str, 1));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMaxFrame((int) (marker.startFrame + marker.durationFrames));
            return;
        }
        throw new IllegalArgumentException(a.l("Cannot find marker with name ", str, JwtUtilsKt.JWT_DELIMITER));
    }

    public void setMinFrame(String str) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new m(this, str, 2));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            setMinFrame((int) marker.startFrame);
            return;
        }
        throw new IllegalArgumentException(a.l("Cannot find marker with name ", str, JwtUtilsKt.JWT_DELIMITER));
    }

    private void convertRect(Rect rect, RectF rectF) {
        rectF.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
    }

    public <T> void addValueCallback(KeyPath keyPath, T t2, final SimpleLottieValueCallback<T> simpleLottieValueCallback) {
        addValueCallback(keyPath, t2, new LottieValueCallback<T>() {
            public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
                return simpleLottieValueCallback.getValue(lottieFrameInfo);
            }
        });
    }

    public void setMinAndMaxFrame(String str, String str2, boolean z2) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new u(this, str, str2, z2));
            return;
        }
        Marker marker = lottieComposition.getMarker(str);
        if (marker != null) {
            int i3 = (int) marker.startFrame;
            Marker marker2 = this.composition.getMarker(str2);
            if (marker2 != null) {
                setMinAndMaxFrame(i3, (int) (marker2.startFrame + (z2 ? 1.0f : 0.0f)));
                return;
            }
            throw new IllegalArgumentException(a.l("Cannot find marker with name ", str2, JwtUtilsKt.JWT_DELIMITER));
        }
        throw new IllegalArgumentException(a.l("Cannot find marker with name ", str, JwtUtilsKt.JWT_DELIMITER));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void draw(Canvas canvas, Matrix matrix) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        LottieComposition lottieComposition = this.composition;
        if (compositionLayer2 != null && lottieComposition != null) {
            boolean asyncUpdatesEnabled = getAsyncUpdatesEnabled();
            if (asyncUpdatesEnabled) {
                try {
                    this.setProgressDrawLock.acquire();
                    if (shouldSetProgressBeforeDrawing()) {
                        setProgress(this.animator.getAnimatedValueAbsolute());
                    }
                } catch (InterruptedException unused) {
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    if (asyncUpdatesEnabled) {
                        this.setProgressDrawLock.release();
                        if (compositionLayer2.getProgress() != this.animator.getAnimatedValueAbsolute()) {
                            setProgressExecutor.execute(this.updateProgressRunnable);
                        }
                    }
                    throw th;
                }
            }
            if (this.useSoftwareRendering) {
                canvas.save();
                canvas.concat(matrix);
                renderAndDrawAsBitmap(canvas, compositionLayer2);
                canvas.restore();
            } else {
                compositionLayer2.draw(canvas, matrix, this.alpha);
            }
            this.isDirty = false;
            if (asyncUpdatesEnabled) {
                this.setProgressDrawLock.release();
                if (compositionLayer2.getProgress() == this.animator.getAnimatedValueAbsolute()) {
                    return;
                }
                setProgressExecutor.execute(this.updateProgressRunnable);
            }
        }
    }

    public void setMinAndMaxFrame(int i3, int i4) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new p(this, i3, i4));
        } else {
            this.animator.setMinAndMaxFrames((float) i3, ((float) i4) + 0.99f);
        }
    }
}
