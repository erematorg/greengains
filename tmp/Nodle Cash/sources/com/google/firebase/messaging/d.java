package com.google.firebase.messaging;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class d implements SuccessContinuation {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7130a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ String f7131b;

    public /* synthetic */ d(String str, int i3) {
        this.f7130a = i3;
        this.f7131b = str;
    }

    public final Task then(Object obj) {
        int i3 = this.f7130a;
        String str = this.f7131b;
        TopicsSubscriber topicsSubscriber = (TopicsSubscriber) obj;
        switch (i3) {
            case 0:
                return topicsSubscriber.subscribeToTopic(str);
            default:
                return topicsSubscriber.unsubscribeFromTopic(str);
        }
    }
}
