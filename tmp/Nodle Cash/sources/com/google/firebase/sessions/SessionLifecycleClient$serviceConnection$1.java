package com.google.firebase.sessions;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;
import android.util.Log;
import kotlin.Metadata;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\t"}, d2 = {"com/google/firebase/sessions/SessionLifecycleClient$serviceConnection$1", "Landroid/content/ServiceConnection;", "onServiceConnected", "", "className", "Landroid/content/ComponentName;", "serviceBinder", "Landroid/os/IBinder;", "onServiceDisconnected", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SessionLifecycleClient$serviceConnection$1 implements ServiceConnection {
    final /* synthetic */ SessionLifecycleClient this$0;

    public SessionLifecycleClient$serviceConnection$1(SessionLifecycleClient sessionLifecycleClient) {
        this.this$0 = sessionLifecycleClient;
    }

    public void onServiceConnected(@Nullable ComponentName componentName, @Nullable IBinder iBinder) {
        Log.d(SessionLifecycleClient.TAG, "Connected to SessionLifecycleService. Queue size " + this.this$0.queuedMessages.size());
        this.this$0.service = new Messenger(iBinder);
        this.this$0.serviceBound = true;
        SessionLifecycleClient sessionLifecycleClient = this.this$0;
        Job unused = sessionLifecycleClient.sendLifecycleEvents(sessionLifecycleClient.drainQueue());
    }

    public void onServiceDisconnected(@Nullable ComponentName componentName) {
        Log.d(SessionLifecycleClient.TAG, "Disconnected from SessionLifecycleService");
        this.this$0.service = null;
        this.this$0.serviceBound = false;
    }
}
