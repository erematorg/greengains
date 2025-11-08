package com.apollographql.apollo3.internal;

import com.apollographql.apollo3.api.http.HttpHeaders;
import com.apollographql.apollo3.api.http.HttpResponse;
import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.internal.MultipartReader;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.FlowCollector;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lokio/BufferedSource;"}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.internal.MultipartKt$multipartBodyFlow$1", f = "multipart.kt", i = {0}, l = {28}, m = "invokeSuspend", n = {"$this$flow"}, s = {"L$0"})
public final class MultipartKt$multipartBodyFlow$1 extends SuspendLambda implements Function2<FlowCollector<? super BufferedSource>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<MultipartReader> $multipartReader;
    final /* synthetic */ HttpResponse $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MultipartKt$multipartBodyFlow$1(Ref.ObjectRef<MultipartReader> objectRef, HttpResponse httpResponse, Continuation<? super MultipartKt$multipartBodyFlow$1> continuation) {
        super(2, continuation);
        this.$multipartReader = objectRef;
        this.$response = httpResponse;
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        MultipartKt$multipartBodyFlow$1 multipartKt$multipartBodyFlow$1 = new MultipartKt$multipartBodyFlow$1(this.$multipartReader, this.$response, continuation);
        multipartKt$multipartBodyFlow$1.L$0 = obj;
        return multipartKt$multipartBodyFlow$1;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        FlowCollector flowCollector;
        BufferedSource body;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            Ref.ObjectRef<MultipartReader> objectRef = this.$multipartReader;
            BufferedSource body2 = this.$response.getBody();
            Intrinsics.checkNotNull(body2);
            String access$getBoundaryParameter = MultipartKt.getBoundaryParameter(HttpHeaders.valueOf(this.$response.getHeaders(), "Content-Type"));
            if (access$getBoundaryParameter != null) {
                objectRef.element = new MultipartReader(body2, access$getBoundaryParameter);
                flowCollector = flowCollector2;
            } else {
                throw new ApolloException("Expected the Content-Type to have a boundary parameter", (Throwable) null, 2, (DefaultConstructorMarker) null);
            }
        } else if (i3 == 1) {
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            T t2 = this.$multipartReader.element;
            Intrinsics.checkNotNull(t2);
            MultipartReader.Part nextPart = ((MultipartReader) t2).nextPart();
            if (nextPart == null) {
                return Unit.INSTANCE;
            }
            body = nextPart.getBody();
            this.L$0 = flowCollector;
            this.label = 1;
        } while (flowCollector.emit(body, this) != coroutine_suspended);
        return coroutine_suspended;
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super BufferedSource> flowCollector, @Nullable Continuation<? super Unit> continuation) {
        return ((MultipartKt$multipartBodyFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
