package com.tinder.scarlet.internal.connection;

import com.tinder.StateMachine;
import com.tinder.scarlet.Event;
import com.tinder.scarlet.SideEffect;
import com.tinder.scarlet.State;
import com.tinder.scarlet.internal.connection.Connection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@SourceDebugExtension({"SMAP\nConnection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Connection.kt\ncom/tinder/scarlet/internal/connection/Connection$StateManager$stateMachine$1\n+ 2 StateMachine.kt\ncom/tinder/StateMachine$GraphBuilder\n+ 3 StateMachine.kt\ncom/tinder/StateMachine$Matcher$Companion\n*L\n1#1,245:1\n145#2:246\n146#2:248\n145#2:249\n146#2:251\n145#2:252\n146#2:254\n145#2:255\n146#2:257\n145#2:258\n146#2:260\n145#2:261\n146#2:263\n120#3:247\n120#3:250\n120#3:253\n120#3:256\n120#3:259\n120#3:262\n*S KotlinDebug\n*F\n+ 1 Connection.kt\ncom/tinder/scarlet/internal/connection/Connection$StateManager$stateMachine$1\n*L\n65#1:246\n65#1:248\n82#1:249\n82#1:251\n104#1:252\n104#1:254\n124#1:255\n124#1:257\n157#1:258\n157#1:260\n162#1:261\n162#1:263\n65#1:247\n82#1:250\n104#1:253\n124#1:256\n157#1:259\n162#1:262\n*E\n"})
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0002H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "Lcom/tinder/StateMachine$GraphBuilder;", "Lcom/tinder/scarlet/State;", "Lcom/tinder/scarlet/Event;", "Lcom/tinder/scarlet/SideEffect;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class Connection$StateManager$stateMachine$1 extends Lambda implements Function1<StateMachine.GraphBuilder<State, Event, SideEffect>, Unit> {
    final /* synthetic */ Connection.StateManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Connection$StateManager$stateMachine$1(Connection.StateManager stateManager) {
        super(1);
        this.this$0 = stateManager;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((StateMachine.GraphBuilder<State, Event, SideEffect>) (StateMachine.GraphBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull StateMachine.GraphBuilder<State, Event, SideEffect> graphBuilder) {
        Intrinsics.checkNotNullParameter(graphBuilder, "$this$create");
        final Connection.StateManager stateManager = this.this$0;
        AnonymousClass1 r02 = new Function1<StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Disconnected>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Disconnected>) (StateMachine.GraphBuilder.StateDefinitionBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull final StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Disconnected> stateDefinitionBuilder) {
                Intrinsics.checkNotNullParameter(stateDefinitionBuilder, "$this$state");
                final Connection.StateManager stateManager = stateManager;
                stateDefinitionBuilder.onEnter(new Function2<State.Disconnected, Event, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((State.Disconnected) obj, (Event) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull State.Disconnected disconnected, @NotNull Event event) {
                        Intrinsics.checkNotNullParameter(disconnected, "$this$onEnter");
                        Intrinsics.checkNotNullParameter(event, "it");
                        stateManager.requestNextLifecycleState();
                    }
                });
                StateMachine.Matcher access$lifecycleStart = stateManager.lifecycleStart();
                final Connection.StateManager stateManager2 = stateManager;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStart, (Function2<? super State.Disconnected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Disconnected, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Disconnected disconnected, @NotNull Event.OnLifecycle.StateChange<?> stateChange) {
                        Intrinsics.checkNotNullParameter(disconnected, "$this$on");
                        Intrinsics.checkNotNullParameter(stateChange, "it");
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, disconnected, new State.Connecting(stateManager2.open(), 0), (Object) null, 2, (Object) null);
                    }
                });
                StateMachine.Matcher access$lifecycleStop = stateManager.lifecycleStop();
                final Connection.StateManager stateManager3 = stateManager;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStop, (Function2<? super State.Disconnected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Disconnected, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Disconnected disconnected, @NotNull Event.OnLifecycle.StateChange<?> stateChange) {
                        Intrinsics.checkNotNullParameter(disconnected, "$this$on");
                        Intrinsics.checkNotNullParameter(stateChange, "it");
                        stateManager3.requestNextLifecycleState();
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.dontTransition$default(stateDefinitionBuilder, disconnected, (Object) null, 1, (Object) null);
                    }
                });
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) StateMachine.Matcher.Companion.any(Event.OnLifecycle.Terminate.class), (Function2<? super State.Disconnected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Disconnected, Event.OnLifecycle.Terminate, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Disconnected disconnected, @NotNull Event.OnLifecycle.Terminate terminate) {
                        Intrinsics.checkNotNullParameter(disconnected, "$this$on");
                        Intrinsics.checkNotNullParameter(terminate, "it");
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, disconnected, State.Destroyed.INSTANCE, (Object) null, 2, (Object) null);
                    }
                });
            }
        };
        StateMachine.Matcher.Companion companion = StateMachine.Matcher.Companion;
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.Disconnected.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) r02);
        final Connection.StateManager stateManager2 = this.this$0;
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.WaitingToRetry.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) new Function1<StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.WaitingToRetry>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.WaitingToRetry>) (StateMachine.GraphBuilder.StateDefinitionBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull final StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.WaitingToRetry> stateDefinitionBuilder) {
                Intrinsics.checkNotNullParameter(stateDefinitionBuilder, "$this$state");
                final Connection.StateManager stateManager = stateManager2;
                stateDefinitionBuilder.onEnter(new Function2<State.WaitingToRetry, Event, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((State.WaitingToRetry) obj, (Event) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull State.WaitingToRetry waitingToRetry, @NotNull Event event) {
                        Intrinsics.checkNotNullParameter(waitingToRetry, "$this$onEnter");
                        Intrinsics.checkNotNullParameter(event, "it");
                        stateManager.requestNextLifecycleState();
                    }
                });
                final Connection.StateManager stateManager2 = stateManager2;
                AnonymousClass2 r02 = new Function2<State.WaitingToRetry, Event.OnRetry, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.WaitingToRetry waitingToRetry, @NotNull Event.OnRetry onRetry) {
                        Intrinsics.checkNotNullParameter(waitingToRetry, "$this$on");
                        Intrinsics.checkNotNullParameter(onRetry, "it");
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, waitingToRetry, new State.Connecting(stateManager2.open(), waitingToRetry.getRetryCount() + 1), (Object) null, 2, (Object) null);
                    }
                };
                StateMachine.Matcher.Companion companion = StateMachine.Matcher.Companion;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) companion.any(Event.OnRetry.class), (Function2<? super State.WaitingToRetry, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) r02);
                StateMachine.Matcher access$lifecycleStart = stateManager2.lifecycleStart();
                final Connection.StateManager stateManager3 = stateManager2;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStart, (Function2<? super State.WaitingToRetry, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.WaitingToRetry, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.WaitingToRetry waitingToRetry, @NotNull Event.OnLifecycle.StateChange<?> stateChange) {
                        Intrinsics.checkNotNullParameter(waitingToRetry, "$this$on");
                        Intrinsics.checkNotNullParameter(stateChange, "it");
                        stateManager3.requestNextLifecycleState();
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.dontTransition$default(stateDefinitionBuilder, waitingToRetry, (Object) null, 1, (Object) null);
                    }
                });
                StateMachine.Matcher access$lifecycleStop = stateManager2.lifecycleStop();
                final Connection.StateManager stateManager4 = stateManager2;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStop, (Function2<? super State.WaitingToRetry, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.WaitingToRetry, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.WaitingToRetry waitingToRetry, @NotNull Event.OnLifecycle.StateChange<?> stateChange) {
                        Intrinsics.checkNotNullParameter(waitingToRetry, "$this$on");
                        Intrinsics.checkNotNullParameter(stateChange, "it");
                        stateManager4.cancelRetry(waitingToRetry);
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, waitingToRetry, State.Disconnected.INSTANCE, (Object) null, 2, (Object) null);
                    }
                });
                final Connection.StateManager stateManager5 = stateManager2;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) companion.any(Event.OnLifecycle.Terminate.class), (Function2<? super State.WaitingToRetry, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.WaitingToRetry, Event.OnLifecycle.Terminate, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.WaitingToRetry waitingToRetry, @NotNull Event.OnLifecycle.Terminate terminate) {
                        Intrinsics.checkNotNullParameter(waitingToRetry, "$this$on");
                        Intrinsics.checkNotNullParameter(terminate, "it");
                        stateManager5.cancelRetry(waitingToRetry);
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, waitingToRetry, State.Destroyed.INSTANCE, (Object) null, 2, (Object) null);
                    }
                });
            }
        });
        final Connection.StateManager stateManager3 = this.this$0;
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.Connecting.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) new Function1<StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connecting>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connecting>) (StateMachine.GraphBuilder.StateDefinitionBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull final StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connecting> stateDefinitionBuilder) {
                Intrinsics.checkNotNullParameter(stateDefinitionBuilder, "$this$state");
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) stateManager3.webSocketOpen(), (Function2<? super State.Connecting, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Connecting, Event.OnWebSocket.C0066Event<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Connecting connecting, @NotNull Event.OnWebSocket.C0066Event<?> event) {
                        Intrinsics.checkNotNullParameter(connecting, "$this$on");
                        Intrinsics.checkNotNullParameter(event, "it");
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connecting, new State.Connected(connecting.getSession$scarlet()), (Object) null, 2, (Object) null);
                    }
                });
                final Connection.StateManager stateManager = stateManager3;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) StateMachine.Matcher.Companion.any(Event.OnWebSocket.Terminate.class), (Function2<? super State.Connecting, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Connecting, Event.OnWebSocket.Terminate, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Connecting connecting, @NotNull Event.OnWebSocket.Terminate terminate) {
                        Intrinsics.checkNotNullParameter(connecting, "$this$on");
                        Intrinsics.checkNotNullParameter(terminate, "it");
                        if (stateManager.backoffStrategy.getShouldBackoff()) {
                            long backoffDurationMillisAt = stateManager.backoffStrategy.backoffDurationMillisAt(connecting.getRetryCount());
                            return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connecting, new State.WaitingToRetry(stateManager.scheduleRetry(backoffDurationMillisAt), connecting.getRetryCount(), backoffDurationMillisAt), (Object) null, 2, (Object) null);
                        }
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connecting, State.Disconnected.INSTANCE, (Object) null, 2, (Object) null);
                    }
                });
            }
        });
        final Connection.StateManager stateManager4 = this.this$0;
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.Connected.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) new Function1<StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connected>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connected>) (StateMachine.GraphBuilder.StateDefinitionBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull final StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Connected> stateDefinitionBuilder) {
                Intrinsics.checkNotNullParameter(stateDefinitionBuilder, "$this$state");
                final Connection.StateManager stateManager = stateManager4;
                stateDefinitionBuilder.onEnter(new Function2<State.Connected, Event, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((State.Connected) obj, (Event) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull State.Connected connected, @NotNull Event event) {
                        Intrinsics.checkNotNullParameter(connected, "$this$onEnter");
                        Intrinsics.checkNotNullParameter(event, "it");
                        stateManager.requestNextLifecycleState();
                    }
                });
                StateMachine.Matcher access$lifecycleStart = stateManager4.lifecycleStart();
                final Connection.StateManager stateManager2 = stateManager4;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStart, (Function2<? super State.Connected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Connected, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Connected connected, @NotNull Event.OnLifecycle.StateChange<?> stateChange) {
                        Intrinsics.checkNotNullParameter(connected, "$this$on");
                        Intrinsics.checkNotNullParameter(stateChange, "it");
                        stateManager2.requestNextLifecycleState();
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.dontTransition$default(stateDefinitionBuilder, connected, (Object) null, 1, (Object) null);
                    }
                });
                StateMachine.Matcher access$lifecycleStop = stateManager4.lifecycleStop();
                final Connection.StateManager stateManager3 = stateManager4;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) access$lifecycleStop, (Function2<? super State.Connected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Connected, Event.OnLifecycle.StateChange<?>, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object, com.tinder.scarlet.Event$OnLifecycle$StateChange, com.tinder.scarlet.Event$OnLifecycle$StateChange<?>] */
                    /* JADX WARNING: Unknown variable types count: 1 */
                    @org.jetbrains.annotations.NotNull
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final com.tinder.StateMachine.Graph.State.TransitionTo<com.tinder.scarlet.State, com.tinder.scarlet.SideEffect> invoke(@org.jetbrains.annotations.NotNull com.tinder.scarlet.State.Connected r8, @org.jetbrains.annotations.NotNull com.tinder.scarlet.Event.OnLifecycle.StateChange<?> r9) {
                        /*
                            r7 = this;
                            java.lang.String r0 = "$this$on"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                            java.lang.String r0 = "it"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                            com.tinder.scarlet.internal.connection.Connection$StateManager r0 = r2
                            com.tinder.scarlet.Lifecycle$State r9 = r9.getState()
                            r0.initiateShutdown(r8, r9)
                            com.tinder.StateMachine$GraphBuilder<com.tinder.scarlet.State, com.tinder.scarlet.Event, com.tinder.scarlet.SideEffect>$StateDefinitionBuilder<com.tinder.scarlet.State$Connected> r1 = r4
                            com.tinder.scarlet.State$Disconnecting r3 = com.tinder.scarlet.State.Disconnecting.INSTANCE
                            r5 = 2
                            r6 = 0
                            r4 = 0
                            r2 = r8
                            com.tinder.StateMachine$Graph$State$TransitionTo r7 = com.tinder.StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(r1, r2, r3, r4, r5, r6)
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.tinder.scarlet.internal.connection.Connection$StateManager$stateMachine$1.AnonymousClass4.AnonymousClass3.invoke(com.tinder.scarlet.State$Connected, com.tinder.scarlet.Event$OnLifecycle$StateChange):com.tinder.StateMachine$Graph$State$TransitionTo");
                    }
                });
                AnonymousClass4 r02 = new Function2<State.Connected, Event.OnLifecycle.Terminate, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Connected connected, @NotNull Event.OnLifecycle.Terminate terminate) {
                        Intrinsics.checkNotNullParameter(connected, "$this$on");
                        Intrinsics.checkNotNullParameter(terminate, "it");
                        connected.getSession$scarlet().getWebSocket().cancel();
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connected, State.Destroyed.INSTANCE, (Object) null, 2, (Object) null);
                    }
                };
                StateMachine.Matcher.Companion companion = StateMachine.Matcher.Companion;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) companion.any(Event.OnLifecycle.Terminate.class), (Function2<? super State.Connected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) r02);
                final Connection.StateManager stateManager4 = stateManager4;
                stateDefinitionBuilder.on((StateMachine.Matcher<EVENT, ? extends E>) companion.any(Event.OnWebSocket.Terminate.class), (Function2<? super State.Connected, ? super E, ? extends StateMachine.Graph.State.TransitionTo<? extends STATE, ? extends SIDE_EFFECT>>) new Function2<State.Connected, Event.OnWebSocket.Terminate, StateMachine.Graph.State.TransitionTo<? extends State, ? extends SideEffect>>() {
                    @NotNull
                    public final StateMachine.Graph.State.TransitionTo<State, SideEffect> invoke(@NotNull State.Connected connected, @NotNull Event.OnWebSocket.Terminate terminate) {
                        Intrinsics.checkNotNullParameter(connected, "$this$on");
                        Intrinsics.checkNotNullParameter(terminate, "it");
                        if (stateManager4.backoffStrategy.getShouldBackoff()) {
                            long backoffDurationMillisAt = stateManager4.backoffStrategy.backoffDurationMillisAt(0);
                            return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connected, new State.WaitingToRetry(stateManager4.scheduleRetry(backoffDurationMillisAt), 0, backoffDurationMillisAt), (Object) null, 2, (Object) null);
                        }
                        return StateMachine.GraphBuilder.StateDefinitionBuilder.transitionTo$default(stateDefinitionBuilder, connected, State.Disconnected.INSTANCE, (Object) null, 2, (Object) null);
                    }
                });
            }
        });
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.Disconnecting.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) AnonymousClass5.INSTANCE);
        final Connection.StateManager stateManager5 = this.this$0;
        graphBuilder.state((StateMachine.Matcher<State, ? extends S>) companion.any(State.Destroyed.class), (Function1<? super StateMachine.GraphBuilder.StateDefinitionBuilder<S>, Unit>) new Function1<StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Destroyed>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Destroyed>) (StateMachine.GraphBuilder.StateDefinitionBuilder) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull StateMachine.GraphBuilder<State, Event, SideEffect>.StateDefinitionBuilder<State.Destroyed> stateDefinitionBuilder) {
                Intrinsics.checkNotNullParameter(stateDefinitionBuilder, "$this$state");
                final Connection.StateManager stateManager = stateManager5;
                stateDefinitionBuilder.onEnter(new Function2<State.Destroyed, Event, Unit>() {
                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((State.Destroyed) obj, (Event) obj2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull State.Destroyed destroyed, @NotNull Event event) {
                        Intrinsics.checkNotNullParameter(destroyed, "$this$onEnter");
                        Intrinsics.checkNotNullParameter(event, "it");
                        stateManager.lifecycleStateSubscriber.dispose();
                    }
                });
            }
        });
        graphBuilder.initialState(State.Disconnected.INSTANCE);
        final Connection.StateManager stateManager6 = this.this$0;
        graphBuilder.onTransition(new Function1<StateMachine.Transition<? extends State, ? extends Event, ? extends SideEffect>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((StateMachine.Transition<? extends State, ? extends Event, SideEffect>) (StateMachine.Transition) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull StateMachine.Transition<? extends State, ? extends Event, SideEffect> transition) {
                Intrinsics.checkNotNullParameter(transition, "transition");
                Connection.StateManager stateManager = stateManager6;
                if ((transition instanceof StateMachine.Transition.Valid) && !Intrinsics.areEqual((Object) transition.getFromState(), ((StateMachine.Transition.Valid) transition).getToState())) {
                    stateManager.eventProcessor.onNext(new Event.OnStateChange(stateManager.getState()));
                }
            }
        });
    }
}
