package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper;

public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    float alphaEndSwipeDistance = 0.5f;
    float alphaStartSwipeDistance = 0.0f;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        private static final int INVALID_POINTER_ID = -1;
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        private boolean shouldDismiss(@NonNull View view, float f2) {
            int i3 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i3 != 0) {
                boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
                int i4 = SwipeDismissBehavior.this.swipeDirection;
                if (i4 == 2) {
                    return true;
                }
                if (i4 == 0) {
                    if (z2) {
                        if (f2 >= 0.0f) {
                            return false;
                        }
                    } else if (i3 <= 0) {
                        return false;
                    }
                    return true;
                } else if (i4 != 1) {
                    return false;
                } else {
                    if (z2) {
                        if (i3 <= 0) {
                            return false;
                        }
                    } else if (f2 >= 0.0f) {
                        return false;
                    }
                    return true;
                }
            } else {
                return Math.abs(view.getLeft() - this.originalCapturedViewLeft) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.dragDismissThreshold);
            }
        }

        public int clampViewPositionHorizontal(@NonNull View view, int i3, int i4) {
            int i5;
            int i6;
            int width;
            boolean z2 = ViewCompat.getLayoutDirection(view) == 1;
            int i7 = SwipeDismissBehavior.this.swipeDirection;
            if (i7 != 0) {
                if (i7 != 1) {
                    i5 = this.originalCapturedViewLeft - view.getWidth();
                    i6 = this.originalCapturedViewLeft + view.getWidth();
                } else if (z2) {
                    i5 = this.originalCapturedViewLeft;
                    width = view.getWidth();
                } else {
                    i5 = this.originalCapturedViewLeft - view.getWidth();
                    i6 = this.originalCapturedViewLeft;
                }
                return SwipeDismissBehavior.clamp(i5, i3, i6);
            } else if (z2) {
                i5 = this.originalCapturedViewLeft - view.getWidth();
                i6 = this.originalCapturedViewLeft;
                return SwipeDismissBehavior.clamp(i5, i3, i6);
            } else {
                i5 = this.originalCapturedViewLeft;
                width = view.getWidth();
            }
            i6 = width + i5;
            return SwipeDismissBehavior.clamp(i5, i3, i6);
        }

        public int clampViewPositionVertical(@NonNull View view, int i3, int i4) {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return view.getWidth();
        }

        public void onViewCaptured(@NonNull View view, int i3) {
            this.activePointerId = i3;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                boolean unused = SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = true;
                parent.requestDisallowInterceptTouchEvent(true);
                boolean unused2 = SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = false;
            }
        }

        public void onViewDragStateChanged(int i3) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
            if (onDismissListener != null) {
                onDismissListener.onDragStateChanged(i3);
            }
        }

        public void onViewPositionChanged(@NonNull View view, int i3, int i4, int i5, int i6) {
            float width = ((float) view.getWidth()) * SwipeDismissBehavior.this.alphaStartSwipeDistance;
            float width2 = ((float) view.getWidth()) * SwipeDismissBehavior.this.alphaEndSwipeDistance;
            float abs = (float) Math.abs(i3 - this.originalCapturedViewLeft);
            if (abs <= width) {
                view.setAlpha(1.0f);
            } else if (abs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, abs), 1.0f));
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:0x0012, code lost:
            r4 = r3.getLeft();
            r0 = r2.originalCapturedViewLeft;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onViewReleased(@androidx.annotation.NonNull android.view.View r3, float r4, float r5) {
            /*
                r2 = this;
                r5 = -1
                r2.activePointerId = r5
                int r5 = r3.getWidth()
                boolean r0 = r2.shouldDismiss(r3, r4)
                if (r0 == 0) goto L_0x0023
                r0 = 0
                int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r4 < 0) goto L_0x001d
                int r4 = r3.getLeft()
                int r0 = r2.originalCapturedViewLeft
                if (r4 >= r0) goto L_0x001b
                goto L_0x001d
            L_0x001b:
                int r0 = r0 + r5
                goto L_0x0021
            L_0x001d:
                int r4 = r2.originalCapturedViewLeft
                int r0 = r4 - r5
            L_0x0021:
                r4 = 1
                goto L_0x0026
            L_0x0023:
                int r0 = r2.originalCapturedViewLeft
                r4 = 0
            L_0x0026:
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.ViewDragHelper r5 = r5.viewDragHelper
                int r1 = r3.getTop()
                boolean r5 = r5.settleCapturedViewAt(r0, r1)
                if (r5 == 0) goto L_0x003f
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r5 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r5.<init>(r3, r4)
                androidx.core.view.ViewCompat.postOnAnimation(r3, r5)
                goto L_0x004a
            L_0x003f:
                if (r4 == 0) goto L_0x004a
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$OnDismissListener r2 = r2.listener
                if (r2 == 0) goto L_0x004a
                r2.onDismiss(r3)
            L_0x004a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        public boolean tryCaptureView(View view, int i3) {
            int i4 = this.activePointerId;
            return (i4 == -1 || i4 == i3) && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    };
    float dragDismissThreshold = 0.5f;
    private boolean interceptingEvents;
    OnDismissListener listener;
    /* access modifiers changed from: private */
    public boolean requestingDisallowInterceptTouchEvent;
    private float sensitivity = 0.0f;
    private boolean sensitivitySet;
    int swipeDirection = 2;
    ViewDragHelper viewDragHelper;

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i3);
    }

    public class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        public SettleRunnable(View view2, boolean z2) {
            this.view = view2;
            this.dismiss = z2;
        }

        public void run() {
            OnDismissListener onDismissListener;
            ViewDragHelper viewDragHelper = SwipeDismissBehavior.this.viewDragHelper;
            if (viewDragHelper != null && viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.view, this);
            } else if (this.dismiss && (onDismissListener = SwipeDismissBehavior.this.listener) != null) {
                onDismissListener.onDismiss(this.view);
            }
        }
    }

    public static float clamp(float f2, float f3, float f4) {
        return Math.min(Math.max(f2, f3), f4);
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.viewDragHelper == null) {
            this.viewDragHelper = this.sensitivitySet ? ViewDragHelper.create(viewGroup, this.sensitivity, this.dragCallback) : ViewDragHelper.create(viewGroup, this.dragCallback);
        }
    }

    public static float fraction(float f2, float f3, float f4) {
        return (f4 - f2) / (f3 - f2);
    }

    private void updateAccessibilityActions(View view) {
        ViewCompat.removeAccessibilityAction(view, 1048576);
        if (canSwipeDismissView(view)) {
            ViewCompat.replaceAccessibilityAction(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, (CharSequence) null, new AccessibilityViewCommand() {
                public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                    boolean z2 = false;
                    if (!SwipeDismissBehavior.this.canSwipeDismissView(view)) {
                        return false;
                    }
                    if (ViewCompat.getLayoutDirection(view) == 1) {
                        z2 = true;
                    }
                    int i3 = SwipeDismissBehavior.this.swipeDirection;
                    ViewCompat.offsetLeftAndRight(view, ((i3 != 0 || !z2) && (i3 != 1 || z2)) ? view.getWidth() : -view.getWidth());
                    view.setAlpha(0.0f);
                    OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
                    if (onDismissListener != null) {
                        onDismissListener.onDismiss(view);
                    }
                    return true;
                }
            });
        }
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public int getDragState() {
        ViewDragHelper viewDragHelper2 = this.viewDragHelper;
        if (viewDragHelper2 != null) {
            return viewDragHelper2.getViewDragState();
        }
        return 0;
    }

    @VisibleForTesting
    @Nullable
    public OnDismissListener getListener() {
        return this.listener;
    }

    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, @NonNull MotionEvent motionEvent) {
        boolean z2 = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            z2 = coordinatorLayout.isPointInChildBounds(v2, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = z2;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!z2) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return !this.requestingDisallowInterceptTouchEvent && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent);
    }

    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, int i3) {
        boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, v2, i3);
        if (ViewCompat.getImportantForAccessibility(v2) == 0) {
            ViewCompat.setImportantForAccessibility(v2, 1);
            updateAccessibilityActions(v2);
        }
        return onLayoutChild;
    }

    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v2, MotionEvent motionEvent) {
        if (this.viewDragHelper == null) {
            return false;
        }
        if (this.requestingDisallowInterceptTouchEvent && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.viewDragHelper.processTouchEvent(motionEvent);
        return true;
    }

    public void setDragDismissDistance(float f2) {
        this.dragDismissThreshold = clamp(0.0f, f2, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f2) {
        this.alphaEndSwipeDistance = clamp(0.0f, f2, 1.0f);
    }

    public void setListener(@Nullable OnDismissListener onDismissListener) {
        this.listener = onDismissListener;
    }

    public void setSensitivity(float f2) {
        this.sensitivity = f2;
        this.sensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f2) {
        this.alphaStartSwipeDistance = clamp(0.0f, f2, 1.0f);
    }

    public void setSwipeDirection(int i3) {
        this.swipeDirection = i3;
    }

    public static int clamp(int i3, int i4, int i5) {
        return Math.min(Math.max(i3, i4), i5);
    }
}
