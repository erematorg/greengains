package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.http.DefaultHttpRequestComposer;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001dB[\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012.\b\u0002\u0010\b\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0012\u001a\u00020\u0013H@¢\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\u00020\u00132\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000bH\u0016J \u0010\u0017\u001a\u00020\u0013\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016J \u0010\u001c\u001a\u00020\u0013\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R6\u0010\b\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\tX\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocol;", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "listener", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "connectionAcknowledgeTimeoutMs", "", "connectionPayload", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "", "frameType", "Lcom/apollographql/apollo3/network/ws/WsFrameType;", "(Lcom/apollographql/apollo3/network/ws/WebSocketConnection;Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;JLkotlin/jvm/functions/Function1;Lcom/apollographql/apollo3/network/ws/WsFrameType;)V", "Lkotlin/jvm/functions/Function1;", "connectionInit", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleServerMessage", "messageMap", "startOperation", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "stopOperation", "Factory", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class SubscriptionWsProtocol extends WsProtocol {
    private final long connectionAcknowledgeTimeoutMs;
    @NotNull
    private final Function1<Continuation<? super Map<String, ? extends Object>>, Object> connectionPayload;
    @NotNull
    private final WsFrameType frameType;

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0001\u0010\u0000\u001a\u0004\u0018\u00010\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$1", f = "SubscriptionWsProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$1  reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation, Object> {
        int label;

        @NotNull
        public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return null;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }

        @Nullable
        public final Object invoke(@Nullable Continuation continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /*  JADX ERROR: Inner class code generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Method generation error
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:274)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0016: CONSTRUCTOR  
          (wrap: long : ?: TERNARY(r1v1 'j2' long) = ((wrap: int : 0x0000: ARITH  (r6v1 int) = (r5v0 'i3' int) & (1 int)) != (0 int)) ? (10000 long) : (r1v0 'j2' long))
          (wrap: kotlin.jvm.functions.Function1 : ?: TERNARY(r3v1 'function1' kotlin.jvm.functions.Function1) = ((wrap: int : 0x0006: ARITH  (r6v2 int) = (r5v0 'i3' int) & (2 int)) != (0 int)) ? (wrap: kotlin.jvm.functions.Function1 : 0x000d: CONSTRUCTOR  (r3v2 'function1' kotlin.jvm.functions.Function1) = (null kotlin.coroutines.Continuation) call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.<init>(kotlin.coroutines.Continuation):void type: CONSTRUCTOR) : (r3v0 'function1' kotlin.jvm.functions.Function1))
          (wrap: com.apollographql.apollo3.network.ws.WsFrameType : ?: TERNARY(r4v1 'wsFrameType' com.apollographql.apollo3.network.ws.WsFrameType) = ((wrap: int : 0x0010: ARITH  (r5v1 int) = (r5v0 'i3' int) & (4 int)) != (0 int)) ? (wrap: com.apollographql.apollo3.network.ws.WsFrameType : 0x0014: SGET  (r4v2 'wsFrameType' com.apollographql.apollo3.network.ws.WsFrameType) =  com.apollographql.apollo3.network.ws.WsFrameType.Text com.apollographql.apollo3.network.ws.WsFrameType) : (r4v0 'wsFrameType' com.apollographql.apollo3.network.ws.WsFrameType))
         call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.<init>(long, kotlin.jvm.functions.Function1, com.apollographql.apollo3.network.ws.WsFrameType):void type: THIS in method: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.<init>(long, kotlin.jvm.functions.Function1, com.apollographql.apollo3.network.ws.WsFrameType, int, kotlin.jvm.internal.DefaultConstructorMarker):void, dex: classes3.dex
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	... 35 more
        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: TERNARY(r3v1 'function1' kotlin.jvm.functions.Function1) = ((wrap: int : 0x0006: ARITH  (r6v2 int) = (r5v0 'i3' int) & (2 int)) != (0 int)) ? (wrap: kotlin.jvm.functions.Function1 : 0x000d: CONSTRUCTOR  (r3v2 'function1' kotlin.jvm.functions.Function1) = (null kotlin.coroutines.Continuation) call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.<init>(kotlin.coroutines.Continuation):void type: CONSTRUCTOR) : (r3v0 'function1' kotlin.jvm.functions.Function1) in method: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.<init>(long, kotlin.jvm.functions.Function1, com.apollographql.apollo3.network.ws.WsFrameType, int, kotlin.jvm.internal.DefaultConstructorMarker):void, dex: classes3.dex
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:640)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	... 44 more
        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: CONSTRUCTOR  (r3v2 'function1' kotlin.jvm.functions.Function1) = (null kotlin.coroutines.Continuation) call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.<init>(kotlin.coroutines.Continuation):void type: CONSTRUCTOR in method: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.<init>(long, kotlin.jvm.functions.Function1, com.apollographql.apollo3.network.ws.WsFrameType, int, kotlin.jvm.internal.DefaultConstructorMarker):void, dex: classes3.dex
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
        	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:953)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:476)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
        	... 50 more
        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Method generation error
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:274)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.util.ArrayList.forEach(ArrayList.java:1259)
        	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
        	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
        	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:676)
        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
        	... 55 more
        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0005: RETURN  
          (wrap: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1 : 0x0002: CONSTRUCTOR  (r0v1 com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1) = (r1v0 'continuation' kotlin.coroutines.Continuation<?>) call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.<init>(kotlin.coroutines.Continuation):void type: CONSTRUCTOR)
         in method: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.create(kotlin.coroutines.Continuation):kotlin.coroutines.Continuation<kotlin.Unit>, dex: classes3.dex
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	... 72 more
        Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: CONSTRUCTOR  (r0v1 com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1) = (r1v0 'continuation' kotlin.coroutines.Continuation<?>) call: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.<init>(kotlin.coroutines.Continuation):void type: CONSTRUCTOR in method: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1.create(kotlin.coroutines.Continuation):kotlin.coroutines.Continuation<kotlin.Unit>, dex: classes3.dex
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:314)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	... 81 more
        Caused by: jadx.core.utils.exceptions.CodegenException: Anonymous inner class unlimited recursion detected. Convert class to inner: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.Factory.1
        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:649)
        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:607)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
        	... 85 more
        */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BK\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012.\b\u0002\u0010\u0004\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R6\u0010\u0004\u001a(\b\u0001\u0012\u001a\u0012\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005X\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/apollographql/apollo3/network/ws/SubscriptionWsProtocol$Factory;", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "connectionAcknowledgeTimeoutMs", "", "connectionPayload", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "", "frameType", "Lcom/apollographql/apollo3/network/ws/WsFrameType;", "(JLkotlin/jvm/functions/Function1;Lcom/apollographql/apollo3/network/ws/WsFrameType;)V", "Lkotlin/jvm/functions/Function1;", "name", "getName", "()Ljava/lang/String;", "create", "Lcom/apollographql/apollo3/network/ws/WsProtocol;", "webSocketConnection", "Lcom/apollographql/apollo3/network/ws/WebSocketConnection;", "listener", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Listener;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Factory implements WsProtocol.Factory {
        private final long connectionAcknowledgeTimeoutMs;
        @NotNull
        private final Function1<Continuation<? super Map<String, ? extends Object>>, Object> connectionPayload;
        @NotNull
        private final WsFrameType frameType;

        @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0001\u0010\u0000\u001a\u0004\u0018\u00010\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
        @DebugMetadata(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1", f = "SubscriptionWsProtocol.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1  reason: invalid class name */
        public static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation, Object> {
            int label;

            @NotNull
            public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
                return new AnonymousClass1(continuation);
            }

            @Nullable
            public final Object invokeSuspend(@NotNull Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label == 0) {
                    ResultKt.throwOnFailure(obj);
                    return null;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            @Nullable
            public final Object invoke(@Nullable Continuation continuation) {
                return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @JvmOverloads
        public Factory() {
            this(0, (Function1) null, (WsFrameType) null, 7, (DefaultConstructorMarker) null);
        }

        @NotNull
        public WsProtocol create(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener, @NotNull CoroutineScope coroutineScope) {
            Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
            Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            Intrinsics.checkNotNullParameter(coroutineScope, "scope");
            return new SubscriptionWsProtocol(webSocketConnection, listener, this.connectionAcknowledgeTimeoutMs, this.connectionPayload, this.frameType);
        }

        @NotNull
        public String getName() {
            return "graphql-ws";
        }

        @JvmOverloads
        public Factory(long j2) {
            this(j2, (Function1) null, (WsFrameType) null, 6, (DefaultConstructorMarker) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Factory(long j2, @NotNull Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> function1) {
            this(j2, function1, (WsFrameType) null, 4, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(function1, "connectionPayload");
        }

        @JvmOverloads
        public Factory(long j2, @NotNull Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> function1, @NotNull WsFrameType wsFrameType) {
            Intrinsics.checkNotNullParameter(function1, "connectionPayload");
            Intrinsics.checkNotNullParameter(wsFrameType, "frameType");
            this.connectionAcknowledgeTimeoutMs = j2;
            this.connectionPayload = function1;
            this.frameType = wsFrameType;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Factory(long j2, Function1 function1, WsFrameType wsFrameType, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 10000 : j2, (i3 & 2) != 0 ? new AnonymousClass1((Continuation<? super AnonymousClass1>) null) : function1, (i3 & 4) != 0 ? WsFrameType.Text : wsFrameType);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SubscriptionWsProtocol(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener) {
        this(webSocketConnection, listener, 0, (Function1) null, (WsFrameType) null, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object connectionInit(kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1 r0 = (com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1 r0 = new com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 == r4) goto L_0x0034
            if (r2 != r3) goto L_0x002c
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x008a
        L_0x002c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0034:
            java.lang.Object r7 = r0.L$1
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r2 = r0.L$0
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol r2 = (com.apollographql.apollo3.network.ws.SubscriptionWsProtocol) r2
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0067
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = "type"
            java.lang.String r2 = "connection_init"
            kotlin.Pair r8 = kotlin.TuplesKt.to(r8, r2)
            kotlin.Pair[] r8 = new kotlin.Pair[]{r8}
            java.util.Map r8 = kotlin.collections.MapsKt.mutableMapOf(r8)
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, ? extends java.lang.Object>>, java.lang.Object> r2 = r7.connectionPayload
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r4
            java.lang.Object r2 = r2.invoke(r0)
            if (r2 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r6 = r2
            r2 = r7
            r7 = r8
            r8 = r6
        L_0x0067:
            java.util.Map r8 = (java.util.Map) r8
            if (r8 == 0) goto L_0x0070
            java.lang.String r4 = "payload"
            r7.put(r4, r8)
        L_0x0070:
            com.apollographql.apollo3.network.ws.WsFrameType r8 = r2.frameType
            r2.sendMessageMap(r7, r8)
            long r7 = r2.connectionAcknowledgeTimeoutMs
            com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2 r4 = new com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2
            r5 = 0
            r4.<init>(r2, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.TimeoutKt.withTimeout(r7, r4, r0)
            if (r7 != r1) goto L_0x008a
            return r1
        L_0x008a:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.SubscriptionWsProtocol.connectionInit(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public void handleServerMessage(@NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "messageMap");
        Object obj = map.get("type");
        if (Intrinsics.areEqual((Object) obj, (Object) "data")) {
            WsProtocol.Listener listener = getListener();
            Object obj2 = map.get(TtmlNode.ATTR_ID);
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.String");
            Object obj3 = map.get("payload");
            Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            listener.operationResponse((String) obj2, (Map) obj3);
        } else if (Intrinsics.areEqual((Object) obj, (Object) Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
            Object obj4 = map.get(TtmlNode.ATTR_ID);
            if (obj4 instanceof String) {
                getListener().operationError((String) obj4, (Map) map.get("payload"));
            } else {
                getListener().generalError((Map) map.get("payload"));
            }
        } else if (Intrinsics.areEqual((Object) obj, (Object) "complete")) {
            WsProtocol.Listener listener2 = getListener();
            Object obj5 = map.get(TtmlNode.ATTR_ID);
            Intrinsics.checkNotNull(obj5, "null cannot be cast to non-null type kotlin.String");
            listener2.operationComplete((String) obj5);
        }
    }

    public <D extends Operation.Data> void startOperation(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        sendMessageMap(MapsKt.mapOf(TuplesKt.to("type", TtmlNode.START), TuplesKt.to(TtmlNode.ATTR_ID, apolloRequest.getRequestUuid().toString()), TuplesKt.to("payload", DefaultHttpRequestComposer.Companion.composePayload(apolloRequest))), this.frameType);
    }

    public <D extends Operation.Data> void stopOperation(@NotNull ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "request");
        sendMessageMap(MapsKt.mapOf(TuplesKt.to("type", "stop"), TuplesKt.to(TtmlNode.ATTR_ID, apolloRequest.getRequestUuid().toString())), this.frameType);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SubscriptionWsProtocol(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener, long j2) {
        this(webSocketConnection, listener, j2, (Function1) null, (WsFrameType) null, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SubscriptionWsProtocol(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener, long j2, @NotNull Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> function1) {
        this(webSocketConnection, listener, j2, function1, (WsFrameType) null, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(function1, "connectionPayload");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SubscriptionWsProtocol(WebSocketConnection webSocketConnection, WsProtocol.Listener listener, long j2, Function1 function1, WsFrameType wsFrameType, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(webSocketConnection, listener, (i3 & 4) != 0 ? 10000 : j2, (i3 & 8) != 0 ? new AnonymousClass1((Continuation<? super AnonymousClass1>) null) : function1, (i3 & 16) != 0 ? WsFrameType.Text : wsFrameType);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SubscriptionWsProtocol(@NotNull WebSocketConnection webSocketConnection, @NotNull WsProtocol.Listener listener, long j2, @NotNull Function1<? super Continuation<? super Map<String, ? extends Object>>, ? extends Object> function1, @NotNull WsFrameType wsFrameType) {
        super(webSocketConnection, listener);
        Intrinsics.checkNotNullParameter(webSocketConnection, "webSocketConnection");
        Intrinsics.checkNotNullParameter(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(function1, "connectionPayload");
        Intrinsics.checkNotNullParameter(wsFrameType, "frameType");
        this.connectionAcknowledgeTimeoutMs = j2;
        this.connectionPayload = function1;
        this.frameType = wsFrameType;
    }
}
