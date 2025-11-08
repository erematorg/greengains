package com.google.firebase.messaging;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.messaging.FirebaseMessaging;

public final /* synthetic */ class i implements EventHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FirebaseMessaging.AutoInit f7142a;

    public /* synthetic */ i(FirebaseMessaging.AutoInit autoInit) {
        this.f7142a = autoInit;
    }

    public final void handle(Event event) {
        this.f7142a.lambda$initialize$0(event);
    }
}
