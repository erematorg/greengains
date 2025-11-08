package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.internal.servicemethod.EventMapper;
import com.tinder.scarlet.internal.servicemethod.ServiceMethod;
import com.tinder.scarlet.lifecycle.FlowableLifecycle;
import io.reactivex.functions.Function;
import kotlin.jvm.functions.Function1;
import org.web3j.protocol.rx.JsonRpc2_0Rx;

public final /* synthetic */ class a implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7643a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7644b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7643a = i3;
        this.f7644b = obj;
    }

    public final Object apply(Object obj) {
        int i3 = this.f7643a;
        Object obj2 = this.f7644b;
        switch (i3) {
            case 0:
                return EventMapper.ToDeserialization.mapToData$lambda$1((Function1) obj2, obj);
            case 1:
                return EventMapper.ToDeserializedValue.mapToData$lambda$1((Function1) obj2, obj);
            case 2:
                return EventMapper.ToLifecycleState.mapToData$lambda$0((Function1) obj2, obj);
            case 3:
                return EventMapper.ToState.mapToData$lambda$0((Function1) obj2, obj);
            case 4:
                return EventMapper.ToWebSocketEvent.mapToData$lambda$0((Function1) obj2, obj);
            case 5:
                return ServiceMethod.Receive.execute$lambda$1((Function1) obj2, obj);
            case 6:
                return FlowableLifecycle.combineWith$lambda$1((Function1) obj2, obj);
            case 7:
                return FlowableLifecycle.combineWith$lambda$2((Function1) obj2, obj);
            default:
                return ((JsonRpc2_0Rx) obj2).lambda$pendingTransactionFlowable$3((String) obj);
        }
    }
}
