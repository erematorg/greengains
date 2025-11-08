package com.bumptech.glide.request.target;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;

public class NotificationTarget extends CustomTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final String notificationTag;
    private final RemoteViews remoteViews;
    private final int viewId;

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    public NotificationTarget(Context context2, int i3, RemoteViews remoteViews2, Notification notification2, int i4) {
        this(context2, i3, remoteViews2, notification2, i4, (String) null);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    private void setBitmap(@Nullable Bitmap bitmap) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    private void update() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.context.getSystemService("notification"))).notify(this.notificationTag, this.notificationId, this.notification);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    public void onLoadCleared(@Nullable Drawable drawable) {
        setBitmap((Bitmap) null);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    public NotificationTarget(Context context2, int i3, RemoteViews remoteViews2, Notification notification2, int i4, String str) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, i3, remoteViews2, notification2, i4, str);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
        setBitmap(bitmap);
    }

    @RequiresPermission("android.permission.POST_NOTIFICATIONS")
    @SuppressLint({"InlinedApi"})
    public NotificationTarget(Context context2, int i3, int i4, int i5, RemoteViews remoteViews2, Notification notification2, int i6, String str) {
        super(i3, i4);
        this.context = (Context) Preconditions.checkNotNull(context2, "Context must not be null!");
        this.notification = (Notification) Preconditions.checkNotNull(notification2, "Notification object can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
        this.viewId = i5;
        this.notificationId = i6;
        this.notificationTag = str;
    }
}
