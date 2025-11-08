package com.tinder;

import androidx.camera.camera2.internal.C0118y;
import androidx.camera.core.impl.i;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 !*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u00020\u0002:\u0005!\"#$%B!\b\u0002\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006¢\u0006\u0002\u0010\u0007J%\u0010\r\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0001¢\u0006\u0002\u0010\u0010JC\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002)\u0010\u0012\u001a%\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013¢\u0006\u0002\b\u0016J#\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0018*\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0019J+\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000e*\u00028\u00002\u0006\u0010\u000f\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\u0015*\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\u0015*\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010\u001eJ\u001e\u0010 \u001a\u00020\u0015*\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000eH\u0002R \u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00028\u00008F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/tinder/StateMachine;", "STATE", "", "EVENT", "SIDE_EFFECT", "graph", "Lcom/tinder/StateMachine$Graph;", "(Lcom/tinder/StateMachine$Graph;)V", "state", "getState", "()Ljava/lang/Object;", "stateRef", "Ljava/util/concurrent/atomic/AtomicReference;", "transition", "Lcom/tinder/StateMachine$Transition;", "event", "(Ljava/lang/Object;)Lcom/tinder/StateMachine$Transition;", "with", "init", "Lkotlin/Function1;", "Lcom/tinder/StateMachine$GraphBuilder;", "", "Lkotlin/ExtensionFunctionType;", "getDefinition", "Lcom/tinder/StateMachine$Graph$State;", "(Ljava/lang/Object;)Lcom/tinder/StateMachine$Graph$State;", "getTransition", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Transition;", "notifyOnEnter", "cause", "(Ljava/lang/Object;Ljava/lang/Object;)V", "notifyOnExit", "notifyOnTransition", "Companion", "Graph", "GraphBuilder", "Matcher", "Transition", "state-machine"}, k = 1, mv = {1, 1, 13})
public final class StateMachine<STATE, EVENT, SIDE_EFFECT> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Graph<STATE, EVENT, SIDE_EFFECT> graph;
    private final AtomicReference<STATE> stateRef;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0004\"\b\b\u0003\u0010\u0005*\u00020\u0001\"\b\b\u0004\u0010\u0006*\u00020\u0001\"\b\b\u0005\u0010\u0007*\u00020\u00012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\t2)\u0010\n\u001a%\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0002Ja\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\u0004\"\b\b\u0003\u0010\u0005*\u00020\u0001\"\b\b\u0004\u0010\u0006*\u00020\u0001\"\b\b\u0005\u0010\u0007*\u00020\u00012)\u0010\n\u001a%\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00070\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/tinder/StateMachine$Companion;", "", "()V", "create", "Lcom/tinder/StateMachine;", "STATE", "EVENT", "SIDE_EFFECT", "graph", "Lcom/tinder/StateMachine$Graph;", "init", "Lkotlin/Function1;", "Lcom/tinder/StateMachine$GraphBuilder;", "", "Lkotlin/ExtensionFunctionType;", "state-machine"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final <STATE, EVENT, SIDE_EFFECT> StateMachine<STATE, EVENT, SIDE_EFFECT> create(@NotNull Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>, Unit> function1) {
            Intrinsics.checkParameterIsNotNull(function1, "init");
            return create((Graph) null, function1);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final <STATE, EVENT, SIDE_EFFECT> StateMachine<STATE, EVENT, SIDE_EFFECT> create(Graph<STATE, EVENT, SIDE_EFFECT> graph, Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>, Unit> function1) {
            GraphBuilder graphBuilder = new GraphBuilder(graph);
            function1.invoke(graphBuilder);
            return new StateMachine<>(graphBuilder.build(), (DefaultConstructorMarker) null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u0002*\b\b\u0004\u0010\u0003*\u00020\u0002*\b\b\u0005\u0010\u0004*\u00020\u00022\u00020\u0002:\u0001#Bk\u0012\u0006\u0010\u0005\u001a\u00028\u0003\u00120\u0010\u0006\u001a,\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00030\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\t0\u0007\u0012*\u0010\n\u001a&\u0012\"\u0012 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e0\f0\u000b¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0017\u001a\u00028\u0003HÆ\u0003¢\u0006\u0002\u0010\u0011J3\u0010\u0018\u001a,\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00030\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\t0\u0007HÆ\u0003J-\u0010\u0019\u001a&\u0012\"\u0012 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e0\f0\u000bHÆ\u0003J\u0001\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\b\b\u0002\u0010\u0005\u001a\u00028\u000322\b\u0002\u0010\u0006\u001a,\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00030\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\t0\u00072,\b\u0002\u0010\n\u001a&\u0012\"\u0012 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e0\f0\u000bHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\"HÖ\u0001R\u0013\u0010\u0005\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R5\u0010\n\u001a&\u0012\"\u0012 \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e0\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R;\u0010\u0006\u001a,\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00030\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcom/tinder/StateMachine$Graph;", "STATE", "", "EVENT", "SIDE_EFFECT", "initialState", "stateDefinitions", "", "Lcom/tinder/StateMachine$Matcher;", "Lcom/tinder/StateMachine$Graph$State;", "onTransitionListeners", "", "Lkotlin/Function1;", "Lcom/tinder/StateMachine$Transition;", "", "(Ljava/lang/Object;Ljava/util/Map;Ljava/util/List;)V", "getInitialState", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getOnTransitionListeners", "()Ljava/util/List;", "getStateDefinitions", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "(Ljava/lang/Object;Ljava/util/Map;Ljava/util/List;)Lcom/tinder/StateMachine$Graph;", "equals", "", "other", "hashCode", "", "toString", "", "State", "state-machine"}, k = 1, mv = {1, 1, 13})
    public static final class Graph<STATE, EVENT, SIDE_EFFECT> {
        @NotNull
        private final STATE initialState;
        @NotNull
        private final List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> onTransitionListeners;
        @NotNull
        private final Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> stateDefinitions;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0006\u0010\u0001*\u00020\u0002*\b\b\u0007\u0010\u0003*\u00020\u0002*\b\b\b\u0010\u0004*\u00020\u00022\u00020\u0002:\u0001\u0015B\u0007\b\u0000¢\u0006\u0002\u0010\u0005R)\u0010\u0006\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR)\u0010\f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00020\t0\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0001\u0010\u000e\u001ar\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00028\u00070\u0010\u0012\"\u0012 \u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\b0\u00110\b0\u000fj8\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00028\u00070\u0010\u0012\"\u0012 \u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\b0\u00110\b`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/tinder/StateMachine$Graph$State;", "STATE", "", "EVENT", "SIDE_EFFECT", "()V", "onEnterListeners", "", "Lkotlin/Function2;", "", "getOnEnterListeners", "()Ljava/util/List;", "onExitListeners", "getOnExitListeners", "transitions", "Ljava/util/LinkedHashMap;", "Lcom/tinder/StateMachine$Matcher;", "Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "Lkotlin/collections/LinkedHashMap;", "getTransitions", "()Ljava/util/LinkedHashMap;", "TransitionTo", "state-machine"}, k = 1, mv = {1, 1, 13})
        public static final class State<STATE, EVENT, SIDE_EFFECT> {
            @NotNull
            private final List<Function2<STATE, EVENT, Unit>> onEnterListeners = new ArrayList();
            @NotNull
            private final List<Function2<STATE, EVENT, Unit>> onExitListeners = new ArrayList();
            @NotNull
            private final LinkedHashMap<Matcher<EVENT, EVENT>, Function2<STATE, EVENT, TransitionTo<STATE, SIDE_EFFECT>>> transitions = new LinkedHashMap<>();

            @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\t\u0010\u0001 \u0001*\u00020\u0002*\n\b\n\u0010\u0003 \u0001*\u00020\u00022\u00020\u0002B\u0019\b\u0000\u0012\u0006\u0010\u0004\u001a\u00028\t\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\n¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\tHÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00018\nHÆ\u0003¢\u0006\u0002\u0010\bJ0\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\t\u0012\u0004\u0012\u00028\n0\u00002\b\b\u0002\u0010\u0004\u001a\u00028\t2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\nHÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00018\n¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u00028\t¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "STATE", "", "SIDE_EFFECT", "toState", "sideEffect", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getSideEffect", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getToState", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "equals", "", "other", "hashCode", "", "toString", "", "state-machine"}, k = 1, mv = {1, 1, 13})
            public static final class TransitionTo<STATE, SIDE_EFFECT> {
                @Nullable
                private final SIDE_EFFECT sideEffect;
                @NotNull
                private final STATE toState;

                public TransitionTo(@NotNull STATE state, @Nullable SIDE_EFFECT side_effect) {
                    Intrinsics.checkParameterIsNotNull(state, "toState");
                    this.toState = state;
                    this.sideEffect = side_effect;
                }

                @NotNull
                public static /* synthetic */ TransitionTo copy$default(TransitionTo transitionTo, STATE state, SIDE_EFFECT side_effect, int i3, Object obj) {
                    if ((i3 & 1) != 0) {
                        state = transitionTo.toState;
                    }
                    if ((i3 & 2) != 0) {
                        side_effect = transitionTo.sideEffect;
                    }
                    return transitionTo.copy(state, side_effect);
                }

                @NotNull
                public final STATE component1() {
                    return this.toState;
                }

                @Nullable
                public final SIDE_EFFECT component2() {
                    return this.sideEffect;
                }

                @NotNull
                public final TransitionTo<STATE, SIDE_EFFECT> copy(@NotNull STATE state, @Nullable SIDE_EFFECT side_effect) {
                    Intrinsics.checkParameterIsNotNull(state, "toState");
                    return new TransitionTo<>(state, side_effect);
                }

                public boolean equals(@Nullable Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof TransitionTo)) {
                        return false;
                    }
                    TransitionTo transitionTo = (TransitionTo) obj;
                    return Intrinsics.areEqual((Object) this.toState, (Object) transitionTo.toState) && Intrinsics.areEqual((Object) this.sideEffect, (Object) transitionTo.sideEffect);
                }

                @Nullable
                public final SIDE_EFFECT getSideEffect() {
                    return this.sideEffect;
                }

                @NotNull
                public final STATE getToState() {
                    return this.toState;
                }

                public int hashCode() {
                    STATE state = this.toState;
                    int i3 = 0;
                    int hashCode = (state != null ? state.hashCode() : 0) * 31;
                    SIDE_EFFECT side_effect = this.sideEffect;
                    if (side_effect != null) {
                        i3 = side_effect.hashCode();
                    }
                    return hashCode + i3;
                }

                @NotNull
                public String toString() {
                    StringBuilder sb = new StringBuilder("TransitionTo(toState=");
                    sb.append(this.toState);
                    sb.append(", sideEffect=");
                    return i.b(sb, this.sideEffect, ")");
                }
            }

            @NotNull
            public final List<Function2<STATE, EVENT, Unit>> getOnEnterListeners() {
                return this.onEnterListeners;
            }

            @NotNull
            public final List<Function2<STATE, EVENT, Unit>> getOnExitListeners() {
                return this.onExitListeners;
            }

            @NotNull
            public final LinkedHashMap<Matcher<EVENT, EVENT>, Function2<STATE, EVENT, TransitionTo<STATE, SIDE_EFFECT>>> getTransitions() {
                return this.transitions;
            }
        }

        public Graph(@NotNull STATE state, @NotNull Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> map, @NotNull List<? extends Function1<? super Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> list) {
            Intrinsics.checkParameterIsNotNull(state, "initialState");
            Intrinsics.checkParameterIsNotNull(map, "stateDefinitions");
            Intrinsics.checkParameterIsNotNull(list, "onTransitionListeners");
            this.initialState = state;
            this.stateDefinitions = map;
            this.onTransitionListeners = list;
        }

        @NotNull
        public static /* synthetic */ Graph copy$default(Graph graph, STATE state, Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> map, List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> list, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                state = graph.initialState;
            }
            if ((i3 & 2) != 0) {
                map = graph.stateDefinitions;
            }
            if ((i3 & 4) != 0) {
                list = graph.onTransitionListeners;
            }
            return graph.copy(state, map, list);
        }

        @NotNull
        public final STATE component1() {
            return this.initialState;
        }

        @NotNull
        public final Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> component2() {
            return this.stateDefinitions;
        }

        @NotNull
        public final List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> component3() {
            return this.onTransitionListeners;
        }

        @NotNull
        public final Graph<STATE, EVENT, SIDE_EFFECT> copy(@NotNull STATE state, @NotNull Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> map, @NotNull List<? extends Function1<? super Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> list) {
            Intrinsics.checkParameterIsNotNull(state, "initialState");
            Intrinsics.checkParameterIsNotNull(map, "stateDefinitions");
            Intrinsics.checkParameterIsNotNull(list, "onTransitionListeners");
            return new Graph<>(state, map, list);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Graph)) {
                return false;
            }
            Graph graph = (Graph) obj;
            return Intrinsics.areEqual((Object) this.initialState, (Object) graph.initialState) && Intrinsics.areEqual((Object) this.stateDefinitions, (Object) graph.stateDefinitions) && Intrinsics.areEqual((Object) this.onTransitionListeners, (Object) graph.onTransitionListeners);
        }

        @NotNull
        public final STATE getInitialState() {
            return this.initialState;
        }

        @NotNull
        public final List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> getOnTransitionListeners() {
            return this.onTransitionListeners;
        }

        @NotNull
        public final Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> getStateDefinitions() {
            return this.stateDefinitions;
        }

        public int hashCode() {
            STATE state = this.initialState;
            int i3 = 0;
            int hashCode = (state != null ? state.hashCode() : 0) * 31;
            Map<Matcher<STATE, STATE>, State<STATE, EVENT, SIDE_EFFECT>> map = this.stateDefinitions;
            int hashCode2 = (hashCode + (map != null ? map.hashCode() : 0)) * 31;
            List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> list = this.onTransitionListeners;
            if (list != null) {
                i3 = list.hashCode();
            }
            return hashCode2 + i3;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder("Graph(initialState=");
            sb.append(this.initialState);
            sb.append(", stateDefinitions=");
            sb.append(this.stateDefinitions);
            sb.append(", onTransitionListeners=");
            return C0118y.h(")", this.onTransitionListeners, sb);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u0002*\b\b\u0004\u0010\u0003*\u00020\u0002*\b\b\u0005\u0010\u0004*\u00020\u00022\u00020\u0002:\u0001!B#\u0012\u001c\b\u0002\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u0005\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0006J\u0013\u0010\b\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00028\u0003¢\u0006\u0002\u0010\u0017J,\u0010\u0018\u001a\u00020\u000e2$\u0010\u0019\u001a \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e0\fJY\u0010\u001a\u001a\u00020\u000e\"\n\b\u0006\u0010\u001b\u0018\u0001*\u00028\u00032\u0006\u0010\u001a\u001a\u0002H\u001b25\b\b\u0010\u001c\u001a/\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u001b0\u001dR\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0000\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u001eH\b¢\u0006\u0002\u0010\u001fJL\u0010\u001a\u001a\u00020\u000e\"\n\b\u0006\u0010\u001b\u0018\u0001*\u00028\u000325\b\b\u0010\u001c\u001a/\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u001b0\u001dR\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0000\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u001eH\bJY\u0010\u001a\u001a\u00020\u000e\"\b\b\u0006\u0010\u001b*\u00028\u00032\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u0002H\u001b0\u001323\u0010\u001c\u001a/\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u001b0\u001dR\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0000\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0002\b\u001eR\u0012\u0010\b\u001a\u0004\u0018\u00018\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\tR§\u0001\u0010\n\u001a\u0001\u0012H\u0012F\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e \u000f*\"\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f0\f0\u000bjL\u0012H\u0012F\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e \u000f*\"\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\f0\f`\u0010X\u0004¢\u0006\u0002\n\u0000RÃ\u0001\u0010\u0011\u001a¶\u0001\u0012$\u0012\"\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0003 \u000f*\u0010\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00130\u0013\u00120\u0012.\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u0005 \u000f*\u0016\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u0005\u0018\u00010\u00140\u00140\u0012jZ\u0012$\u0012\"\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0003 \u000f*\u0010\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u00130\u0013\u00120\u0012.\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u0005 \u000f*\u0016\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u0005\u0018\u00010\u00140\u0014`\u0015X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/tinder/StateMachine$GraphBuilder;", "STATE", "", "EVENT", "SIDE_EFFECT", "graph", "Lcom/tinder/StateMachine$Graph;", "(Lcom/tinder/StateMachine$Graph;)V", "initialState", "Ljava/lang/Object;", "onTransitionListeners", "Ljava/util/ArrayList;", "Lkotlin/Function1;", "Lcom/tinder/StateMachine$Transition;", "", "kotlin.jvm.PlatformType", "Lkotlin/collections/ArrayList;", "stateDefinitions", "Ljava/util/LinkedHashMap;", "Lcom/tinder/StateMachine$Matcher;", "Lcom/tinder/StateMachine$Graph$State;", "Lkotlin/collections/LinkedHashMap;", "build", "(Ljava/lang/Object;)V", "onTransition", "listener", "state", "S", "init", "Lcom/tinder/StateMachine$GraphBuilder$StateDefinitionBuilder;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "stateMatcher", "StateDefinitionBuilder", "state-machine"}, k = 1, mv = {1, 1, 13})
    public static final class GraphBuilder<STATE, EVENT, SIDE_EFFECT> {
        private STATE initialState;
        private final ArrayList<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> onTransitionListeners;
        private final LinkedHashMap<Matcher<STATE, STATE>, Graph.State<STATE, EVENT, SIDE_EFFECT>> stateDefinitions;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0004\u0018\u0000*\b\b\u0006\u0010\u0001*\u00028\u00032\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J!\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u0002H\b0\u0007\"\n\b\u0007\u0010\b\u0018\u0001*\u00028\u0004H\bJ\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0005J.\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u0002H\u000b0\u0007\"\n\b\u0007\u0010\u000b\u0018\u0001*\u00028\u00042\u0006\u0010\f\u001a\u0002H\u000bH\b¢\u0006\u0002\u0010\rJO\u0010\u000e\u001a\u00020\u000f\"\n\b\u0007\u0010\b\u0018\u0001*\u00028\u00042\u0006\u0010\u0010\u001a\u0002H\b2+\b\b\u0010\u0011\u001a%\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00050\u00130\u0012¢\u0006\u0002\b\u0014H\b¢\u0006\u0002\u0010\u0015JB\u0010\u000e\u001a\u00020\u000f\"\n\b\u0007\u0010\b\u0018\u0001*\u00028\u00042+\b\b\u0010\u0011\u001a%\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00050\u00130\u0012¢\u0006\u0002\b\u0014H\bJO\u0010\u000e\u001a\u00020\u000f\"\b\b\u0007\u0010\b*\u00028\u00042\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u0002H\b0\u00072)\u0010\u0011\u001a%\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u0002H\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00050\u00130\u0012¢\u0006\u0002\b\u0014J%\u0010\u0017\u001a\u00020\u00182\u001d\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00020\u000f0\u0012¢\u0006\u0002\b\u0014J%\u0010\u001a\u001a\u00020\u00182\u001d\u0010\u0019\u001a\u0019\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00020\u000f0\u0012¢\u0006\u0002\b\u0014J'\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00050\u0013*\u00028\u00062\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00018\u0005¢\u0006\u0002\u0010\u001dJ/\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00050\u0013*\u00028\u00062\u0006\u0010\u001f\u001a\u00028\u00032\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00018\u0005¢\u0006\u0002\u0010 R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/tinder/StateMachine$GraphBuilder$StateDefinitionBuilder;", "S", "", "(Lcom/tinder/StateMachine$GraphBuilder;)V", "stateDefinition", "Lcom/tinder/StateMachine$Graph$State;", "any", "Lcom/tinder/StateMachine$Matcher;", "E", "build", "eq", "R", "value", "(Ljava/lang/Object;)Lcom/tinder/StateMachine$Matcher;", "on", "", "event", "createTransitionTo", "Lkotlin/Function2;", "Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "eventMatcher", "onEnter", "", "listener", "onExit", "dontTransition", "sideEffect", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "transitionTo", "state", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Graph$State$TransitionTo;", "state-machine"}, k = 1, mv = {1, 1, 13})
        public final class StateDefinitionBuilder<S extends STATE> {
            private final Graph.State<STATE, EVENT, SIDE_EFFECT> stateDefinition = new Graph.State<>();

            public StateDefinitionBuilder() {
            }

            private final <E extends EVENT> Matcher<EVENT, E> any() {
                Matcher.Companion companion = Matcher.Companion;
                Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_EAST);
                return companion.any(Object.class);
            }

            @NotNull
            public static /* synthetic */ Graph.State.TransitionTo dontTransition$default(StateDefinitionBuilder stateDefinitionBuilder, Object obj, Object obj2, int i3, Object obj3) {
                if ((i3 & 1) != 0) {
                    obj2 = null;
                }
                return stateDefinitionBuilder.dontTransition(obj, obj2);
            }

            private final <R extends EVENT> Matcher<EVENT, R> eq(R r2) {
                Matcher.Companion companion = Matcher.Companion;
                Intrinsics.reifiedOperationMarker(4, "R");
                return companion.any(Object.class).where(new StateMachine$Matcher$Companion$eq$1(r2));
            }

            @NotNull
            public static /* synthetic */ Graph.State.TransitionTo transitionTo$default(StateDefinitionBuilder stateDefinitionBuilder, Object obj, Object obj2, Object obj3, int i3, Object obj4) {
                if ((i3 & 2) != 0) {
                    obj3 = null;
                }
                return stateDefinitionBuilder.transitionTo(obj, obj2, obj3);
            }

            @NotNull
            public final Graph.State<STATE, EVENT, SIDE_EFFECT> build() {
                return this.stateDefinition;
            }

            @NotNull
            public final Graph.State.TransitionTo<STATE, SIDE_EFFECT> dontTransition(@NotNull S s3, @Nullable SIDE_EFFECT side_effect) {
                Intrinsics.checkParameterIsNotNull(s3, "receiver$0");
                return transitionTo(s3, s3, side_effect);
            }

            public final <E extends EVENT> void on(@NotNull Matcher<EVENT, ? extends E> matcher, @NotNull Function2<? super S, ? super E, ? extends Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>> function2) {
                Intrinsics.checkParameterIsNotNull(matcher, "eventMatcher");
                Intrinsics.checkParameterIsNotNull(function2, "createTransitionTo");
                this.stateDefinition.getTransitions().put(matcher, new StateMachine$GraphBuilder$StateDefinitionBuilder$on$1(function2));
            }

            public final boolean onEnter(@NotNull Function2<? super S, ? super EVENT, Unit> function2) {
                Intrinsics.checkParameterIsNotNull(function2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                return this.stateDefinition.getOnEnterListeners().add(new StateMachine$GraphBuilder$StateDefinitionBuilder$onEnter$$inlined$with$lambda$1(function2));
            }

            public final boolean onExit(@NotNull Function2<? super S, ? super EVENT, Unit> function2) {
                Intrinsics.checkParameterIsNotNull(function2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                return this.stateDefinition.getOnExitListeners().add(new StateMachine$GraphBuilder$StateDefinitionBuilder$onExit$$inlined$with$lambda$1(function2));
            }

            @NotNull
            public final Graph.State.TransitionTo<STATE, SIDE_EFFECT> transitionTo(@NotNull S s3, @NotNull STATE state, @Nullable SIDE_EFFECT side_effect) {
                Intrinsics.checkParameterIsNotNull(s3, "receiver$0");
                Intrinsics.checkParameterIsNotNull(state, RemoteConfigConstants.ResponseFieldKey.STATE);
                return new Graph.State.TransitionTo<>(state, side_effect);
            }

            private final <E extends EVENT> void on(Function2<? super S, ? super E, ? extends Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>> function2) {
                Matcher.Companion companion = Matcher.Companion;
                Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_EAST);
                on(companion.any(Object.class), function2);
            }

            private final <E extends EVENT> void on(E e3, Function2<? super S, ? super E, ? extends Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>> function2) {
                Matcher.Companion companion = Matcher.Companion;
                Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_EAST);
                on(companion.any(Object.class).where(new StateMachine$Matcher$Companion$eq$1(e3)), function2);
            }
        }

        public GraphBuilder() {
            this((Graph) null, 1, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final Graph<STATE, EVENT, SIDE_EFFECT> build() {
            STATE state = this.initialState;
            if (state != null) {
                return new Graph<>(state, MapsKt.toMap(this.stateDefinitions), CollectionsKt.toList(this.onTransitionListeners));
            }
            throw new IllegalArgumentException("Required value was null.");
        }

        public final void initialState(@NotNull STATE state) {
            Intrinsics.checkParameterIsNotNull(state, "initialState");
            this.initialState = state;
        }

        public final void onTransition(@NotNull Function1<? super Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit> function1) {
            Intrinsics.checkParameterIsNotNull(function1, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.onTransitionListeners.add(function1);
        }

        public final <S extends STATE> void state(@NotNull Matcher<STATE, ? extends S> matcher, @NotNull Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>.StateDefinitionBuilder<S>, Unit> function1) {
            Intrinsics.checkParameterIsNotNull(matcher, "stateMatcher");
            Intrinsics.checkParameterIsNotNull(function1, "init");
            LinkedHashMap<Matcher<STATE, STATE>, Graph.State<STATE, EVENT, SIDE_EFFECT>> linkedHashMap = this.stateDefinitions;
            StateDefinitionBuilder stateDefinitionBuilder = new StateDefinitionBuilder();
            function1.invoke(stateDefinitionBuilder);
            linkedHashMap.put(matcher, stateDefinitionBuilder.build());
        }

        public GraphBuilder(@Nullable Graph<STATE, EVENT, SIDE_EFFECT> graph) {
            List<Function1<Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT>, Unit>> emptyList;
            Map<Matcher<STATE, STATE>, Graph.State<STATE, EVENT, SIDE_EFFECT>> emptyMap;
            this.initialState = graph != null ? graph.getInitialState() : null;
            this.stateDefinitions = new LinkedHashMap<>((graph == null || (emptyMap = graph.getStateDefinitions()) == null) ? MapsKt.emptyMap() : emptyMap);
            this.onTransitionListeners = new ArrayList<>((graph == null || (emptyList = graph.getOnTransitionListeners()) == null) ? CollectionsKt.emptyList() : emptyList);
        }

        private final <S extends STATE> void state(Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>.StateDefinitionBuilder<S>, Unit> function1) {
            Matcher.Companion companion = Matcher.Companion;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
            state(companion.any(Object.class), function1);
        }

        private final <S extends STATE> void state(S s3, Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>.StateDefinitionBuilder<S>, Unit> function1) {
            Matcher.Companion companion = Matcher.Companion;
            Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
            state(companion.any(Object.class).where(new StateMachine$Matcher$Companion$eq$1(s3)), function1);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ GraphBuilder(Graph graph, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? null : graph);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\n\b\u0003\u0010\u0001 \u0001*\u00020\u0002*\n\b\u0004\u0010\u0003 \u0001*\u00020\u0002*\n\b\u0005\u0010\u0004 \u0001*\u00020\u00022\u00020\u0002:\u0002\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00028\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/tinder/StateMachine$Transition;", "STATE", "", "EVENT", "SIDE_EFFECT", "()V", "event", "getEvent", "()Ljava/lang/Object;", "fromState", "getFromState", "Invalid", "Valid", "Lcom/tinder/StateMachine$Transition$Valid;", "Lcom/tinder/StateMachine$Transition$Invalid;", "state-machine"}, k = 1, mv = {1, 1, 13})
    public static abstract class Transition<STATE, EVENT, SIDE_EFFECT> {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0006\u0010\u0001 \u0001*\u00020\u0002*\n\b\u0007\u0010\u0003 \u0001*\u00020\u0002*\n\b\b\u0010\u0004 \u0001*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0005B\u0017\b\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\r\u001a\u00028\u0006HÆ\u0003¢\u0006\u0002\u0010\nJ\u000e\u0010\u000e\u001a\u00028\u0007HÆ\u0003¢\u0006\u0002\u0010\nJ4\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00028\b0\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u0007HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0016\u0010\u0007\u001a\u00028\u0007X\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00028\u0006X\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/tinder/StateMachine$Transition$Invalid;", "STATE", "", "EVENT", "SIDE_EFFECT", "Lcom/tinder/StateMachine$Transition;", "fromState", "event", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getFromState", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Transition$Invalid;", "equals", "", "other", "hashCode", "", "toString", "", "state-machine"}, k = 1, mv = {1, 1, 13})
        public static final class Invalid<STATE, EVENT, SIDE_EFFECT> extends Transition<STATE, EVENT, SIDE_EFFECT> {
            @NotNull
            private final EVENT event;
            @NotNull
            private final STATE fromState;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Invalid(@NotNull STATE state, @NotNull EVENT event2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkParameterIsNotNull(state, "fromState");
                Intrinsics.checkParameterIsNotNull(event2, NotificationCompat.CATEGORY_EVENT);
                this.fromState = state;
                this.event = event2;
            }

            @NotNull
            public static /* synthetic */ Invalid copy$default(Invalid invalid, Object obj, Object obj2, int i3, Object obj3) {
                if ((i3 & 1) != 0) {
                    obj = invalid.getFromState();
                }
                if ((i3 & 2) != 0) {
                    obj2 = invalid.getEvent();
                }
                return invalid.copy(obj, obj2);
            }

            @NotNull
            public final STATE component1() {
                return getFromState();
            }

            @NotNull
            public final EVENT component2() {
                return getEvent();
            }

            @NotNull
            public final Invalid<STATE, EVENT, SIDE_EFFECT> copy(@NotNull STATE state, @NotNull EVENT event2) {
                Intrinsics.checkParameterIsNotNull(state, "fromState");
                Intrinsics.checkParameterIsNotNull(event2, NotificationCompat.CATEGORY_EVENT);
                return new Invalid<>(state, event2);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Invalid)) {
                    return false;
                }
                Invalid invalid = (Invalid) obj;
                return Intrinsics.areEqual(getFromState(), invalid.getFromState()) && Intrinsics.areEqual(getEvent(), invalid.getEvent());
            }

            @NotNull
            public EVENT getEvent() {
                return this.event;
            }

            @NotNull
            public STATE getFromState() {
                return this.fromState;
            }

            public int hashCode() {
                Object fromState2 = getFromState();
                int i3 = 0;
                int hashCode = (fromState2 != null ? fromState2.hashCode() : 0) * 31;
                Object event2 = getEvent();
                if (event2 != null) {
                    i3 = event2.hashCode();
                }
                return hashCode + i3;
            }

            @NotNull
            public String toString() {
                return "Invalid(fromState=" + getFromState() + ", event=" + getEvent() + ")";
            }
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\n\b\u0006\u0010\u0001 \u0001*\u00020\u0002*\n\b\u0007\u0010\u0003 \u0001*\u00020\u0002*\n\b\b\u0010\u0004 \u0001*\u00020\u00022\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0005B)\b\u0000\u0012\u0006\u0010\u0006\u001a\u00028\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0007\u0012\u0006\u0010\b\u001a\u00028\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00018\b¢\u0006\u0002\u0010\nJ\u000e\u0010\u0011\u001a\u00028\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0012\u001a\u00028\u0007HÆ\u0003¢\u0006\u0002\u0010\fJ\u000e\u0010\u0013\u001a\u00028\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00018\bHÆ\u0003¢\u0006\u0002\u0010\fJJ\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00028\u0006\u0012\u0004\u0012\u00028\u0007\u0012\u0004\u0012\u00028\b0\u00002\b\b\u0002\u0010\u0006\u001a\u00028\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u00072\b\b\u0002\u0010\b\u001a\u00028\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00018\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0016\u0010\u0007\u001a\u00028\u0007X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u00028\u0006X\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\t\u001a\u0004\u0018\u00018\b¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\b\u001a\u00028\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0010\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/tinder/StateMachine$Transition$Valid;", "STATE", "", "EVENT", "SIDE_EFFECT", "Lcom/tinder/StateMachine$Transition;", "fromState", "event", "toState", "sideEffect", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "getEvent", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getFromState", "getSideEffect", "getToState", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/tinder/StateMachine$Transition$Valid;", "equals", "", "other", "hashCode", "", "toString", "", "state-machine"}, k = 1, mv = {1, 1, 13})
        public static final class Valid<STATE, EVENT, SIDE_EFFECT> extends Transition<STATE, EVENT, SIDE_EFFECT> {
            @NotNull
            private final EVENT event;
            @NotNull
            private final STATE fromState;
            @Nullable
            private final SIDE_EFFECT sideEffect;
            @NotNull
            private final STATE toState;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Valid(@NotNull STATE state, @NotNull EVENT event2, @NotNull STATE state2, @Nullable SIDE_EFFECT side_effect) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkParameterIsNotNull(state, "fromState");
                Intrinsics.checkParameterIsNotNull(event2, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkParameterIsNotNull(state2, "toState");
                this.fromState = state;
                this.event = event2;
                this.toState = state2;
                this.sideEffect = side_effect;
            }

            @NotNull
            public static /* synthetic */ Valid copy$default(Valid valid, Object obj, Object obj2, STATE state, SIDE_EFFECT side_effect, int i3, Object obj3) {
                if ((i3 & 1) != 0) {
                    obj = valid.getFromState();
                }
                if ((i3 & 2) != 0) {
                    obj2 = valid.getEvent();
                }
                if ((i3 & 4) != 0) {
                    state = valid.toState;
                }
                if ((i3 & 8) != 0) {
                    side_effect = valid.sideEffect;
                }
                return valid.copy(obj, obj2, state, side_effect);
            }

            @NotNull
            public final STATE component1() {
                return getFromState();
            }

            @NotNull
            public final EVENT component2() {
                return getEvent();
            }

            @NotNull
            public final STATE component3() {
                return this.toState;
            }

            @Nullable
            public final SIDE_EFFECT component4() {
                return this.sideEffect;
            }

            @NotNull
            public final Valid<STATE, EVENT, SIDE_EFFECT> copy(@NotNull STATE state, @NotNull EVENT event2, @NotNull STATE state2, @Nullable SIDE_EFFECT side_effect) {
                Intrinsics.checkParameterIsNotNull(state, "fromState");
                Intrinsics.checkParameterIsNotNull(event2, NotificationCompat.CATEGORY_EVENT);
                Intrinsics.checkParameterIsNotNull(state2, "toState");
                return new Valid<>(state, event2, state2, side_effect);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Valid)) {
                    return false;
                }
                Valid valid = (Valid) obj;
                return Intrinsics.areEqual(getFromState(), valid.getFromState()) && Intrinsics.areEqual(getEvent(), valid.getEvent()) && Intrinsics.areEqual((Object) this.toState, (Object) valid.toState) && Intrinsics.areEqual((Object) this.sideEffect, (Object) valid.sideEffect);
            }

            @NotNull
            public EVENT getEvent() {
                return this.event;
            }

            @NotNull
            public STATE getFromState() {
                return this.fromState;
            }

            @Nullable
            public final SIDE_EFFECT getSideEffect() {
                return this.sideEffect;
            }

            @NotNull
            public final STATE getToState() {
                return this.toState;
            }

            public int hashCode() {
                Object fromState2 = getFromState();
                int i3 = 0;
                int hashCode = (fromState2 != null ? fromState2.hashCode() : 0) * 31;
                Object event2 = getEvent();
                int hashCode2 = (hashCode + (event2 != null ? event2.hashCode() : 0)) * 31;
                STATE state = this.toState;
                int hashCode3 = (hashCode2 + (state != null ? state.hashCode() : 0)) * 31;
                SIDE_EFFECT side_effect = this.sideEffect;
                if (side_effect != null) {
                    i3 = side_effect.hashCode();
                }
                return hashCode3 + i3;
            }

            @NotNull
            public String toString() {
                StringBuilder sb = new StringBuilder("Valid(fromState=");
                sb.append(getFromState());
                sb.append(", event=");
                sb.append(getEvent());
                sb.append(", toState=");
                sb.append(this.toState);
                sb.append(", sideEffect=");
                return i.b(sb, this.sideEffect, ")");
            }
        }

        private Transition() {
        }

        @NotNull
        public abstract EVENT getEvent();

        @NotNull
        public abstract STATE getFromState();

        public /* synthetic */ Transition(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private StateMachine(Graph<STATE, EVENT, SIDE_EFFECT> graph2) {
        this.graph = graph2;
        this.stateRef = new AtomicReference<>(graph2.getInitialState());
    }

    private final Graph.State<STATE, EVENT, SIDE_EFFECT> getDefinition(@NotNull STATE state) {
        Map<Matcher<STATE, STATE>, Graph.State<STATE, EVENT, SIDE_EFFECT>> stateDefinitions = this.graph.getStateDefinitions();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry next : stateDefinitions.entrySet()) {
            if (((Matcher) next.getKey()).matches(state)) {
                linkedHashMap.put(next.getKey(), next.getValue());
            }
        }
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry value : linkedHashMap.entrySet()) {
            arrayList.add((Graph.State) value.getValue());
        }
        Graph.State<STATE, EVENT, SIDE_EFFECT> state2 = (Graph.State) CollectionsKt.firstOrNull(arrayList);
        if (state2 != null) {
            return state2;
        }
        throw new IllegalStateException(("Missing definition for state " + state.getClass().getSimpleName() + '!').toString());
    }

    private final Transition<STATE, EVENT, SIDE_EFFECT> getTransition(@NotNull STATE state, EVENT event) {
        for (Map.Entry entry : getDefinition(state).getTransitions().entrySet()) {
            Function2 function2 = (Function2) entry.getValue();
            if (((Matcher) entry.getKey()).matches(event)) {
                Graph.State.TransitionTo transitionTo = (Graph.State.TransitionTo) function2.invoke(state, event);
                return new Transition.Valid(state, event, transitionTo.component1(), transitionTo.component2());
            }
        }
        return new Transition.Invalid(state, event);
    }

    private final void notifyOnEnter(@NotNull STATE state, EVENT event) {
        for (Function2 invoke : getDefinition(state).getOnEnterListeners()) {
            invoke.invoke(state, event);
        }
    }

    private final void notifyOnExit(@NotNull STATE state, EVENT event) {
        for (Function2 invoke : getDefinition(state).getOnExitListeners()) {
            invoke.invoke(state, event);
        }
    }

    private final void notifyOnTransition(@NotNull Transition<? extends STATE, ? extends EVENT, ? extends SIDE_EFFECT> transition) {
        for (Function1 invoke : this.graph.getOnTransitionListeners()) {
            invoke.invoke(transition);
        }
    }

    @NotNull
    public final STATE getState() {
        STATE state = this.stateRef.get();
        Intrinsics.checkExpressionValueIsNotNull(state, "stateRef.get()");
        return state;
    }

    @NotNull
    public final Transition<STATE, EVENT, SIDE_EFFECT> transition(@NotNull EVENT event) {
        Transition<STATE, EVENT, SIDE_EFFECT> transition;
        Intrinsics.checkParameterIsNotNull(event, NotificationCompat.CATEGORY_EVENT);
        synchronized (this) {
            STATE state = this.stateRef.get();
            Intrinsics.checkExpressionValueIsNotNull(state, "fromState");
            transition = getTransition(state, event);
            if (transition instanceof Transition.Valid) {
                this.stateRef.set(((Transition.Valid) transition).getToState());
            }
        }
        notifyOnTransition(transition);
        if (transition instanceof Transition.Valid) {
            Transition.Valid valid = (Transition.Valid) transition;
            notifyOnExit(valid.getFromState(), event);
            notifyOnEnter(valid.getToState(), event);
        }
        return transition;
    }

    @NotNull
    public final StateMachine<STATE, EVENT, SIDE_EFFECT> with(@NotNull Function1<? super GraphBuilder<STATE, EVENT, SIDE_EFFECT>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "init");
        return Companion.create(Graph.copy$default(this.graph, getState(), (Map) null, (List) null, 6, (Object) null), function1);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u0011*\b\b\u0003\u0010\u0001*\u00020\u0002*\n\b\u0004\u0010\u0003 \u0001*\u0002H\u00012\u00020\u0002:\u0001\u0011B\u0015\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00040\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00028\u0003¢\u0006\u0002\u0010\rJ+\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u00002\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00040\u0005X\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00020\n0\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/tinder/StateMachine$Matcher;", "T", "", "R", "clazz", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "predicates", "", "Lkotlin/Function1;", "", "matches", "value", "(Ljava/lang/Object;)Z", "where", "predicate", "Lkotlin/ExtensionFunctionType;", "Companion", "state-machine"}, k = 1, mv = {1, 1, 13})
    public static final class Matcher<T, R extends T> {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public final Class<R> clazz;
        private final List<Function1<T, Boolean>> predicates;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0005\u0010\u0005*\u00020\u0001\"\n\b\u0006\u0010\u0006\u0018\u0001*\u0002H\u0005H\bJ4\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0005\u0010\u0005*\u00020\u0001\"\b\b\u0006\u0010\u0006*\u0002H\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\bJ8\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0005\u0010\u0005*\u00020\u0001\"\n\b\u0006\u0010\u0006\u0018\u0001*\u0002H\u00052\u0006\u0010\n\u001a\u0002H\u0006H\b¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/tinder/StateMachine$Matcher$Companion;", "", "()V", "any", "Lcom/tinder/StateMachine$Matcher;", "T", "R", "clazz", "Ljava/lang/Class;", "eq", "value", "(Ljava/lang/Object;)Lcom/tinder/StateMachine$Matcher;", "state-machine"}, k = 1, mv = {1, 1, 13})
        public static final class Companion {
            private Companion() {
            }

            private final <T, R extends T> Matcher<T, R> eq(R r2) {
                Intrinsics.reifiedOperationMarker(4, "R");
                return any(Object.class).where(new StateMachine$Matcher$Companion$eq$1(r2));
            }

            @NotNull
            public final <T, R extends T> Matcher<T, R> any(@NotNull Class<R> cls) {
                Intrinsics.checkParameterIsNotNull(cls, "clazz");
                return new Matcher<>(cls, (DefaultConstructorMarker) null);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private final <T, R extends T> Matcher<T, R> any() {
                Intrinsics.reifiedOperationMarker(4, "R");
                return any(Object.class);
            }
        }

        private Matcher(Class<R> cls) {
            this.clazz = cls;
            this.predicates = CollectionsKt.mutableListOf(new StateMachine$Matcher$predicates$1(this));
        }

        public final boolean matches(@NotNull T t2) {
            Intrinsics.checkParameterIsNotNull(t2, "value");
            Iterable<Function1> iterable = this.predicates;
            if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                return true;
            }
            for (Function1 invoke : iterable) {
                if (!((Boolean) invoke.invoke(t2)).booleanValue()) {
                    return false;
                }
            }
            return true;
        }

        @NotNull
        public final Matcher<T, R> where(@NotNull Function1<? super R, Boolean> function1) {
            Intrinsics.checkParameterIsNotNull(function1, "predicate");
            this.predicates.add(new StateMachine$Matcher$where$$inlined$apply$lambda$1(function1));
            return this;
        }

        public /* synthetic */ Matcher(Class cls, DefaultConstructorMarker defaultConstructorMarker) {
            this(cls);
        }
    }

    public /* synthetic */ StateMachine(Graph graph2, DefaultConstructorMarker defaultConstructorMarker) {
        this(graph2);
    }
}
