package com.apollographql.apollo3.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SourceDebugExtension({"SMAP\nmultipart.kt\nKotlin\n*S Kotlin\n*F\n+ 1 multipart.kt\ncom/apollographql/apollo3/internal/MultipartKt$multipartBodyFlow$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n1#2:50\n*E\n"})
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lokio/BufferedSource;", "it", ""}, k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.apollographql.apollo3.internal.MultipartKt$multipartBodyFlow$2", f = "multipart.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
public final class MultipartKt$multipartBodyFlow$2 extends SuspendLambda implements Function3<FlowCollector<? super BufferedSource>, Throwable, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef<MultipartReader> $multipartReader;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MultipartKt$multipartBodyFlow$2(Ref.ObjectRef<MultipartReader> objectRef, Continuation<? super MultipartKt$multipartBodyFlow$2> continuation) {
        super(3, continuation);
        this.$multipartReader = objectRef;
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Unit unit;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Ref.ObjectRef<MultipartReader> objectRef = this.$multipartReader;
            try {
                Result.Companion companion = Result.Companion;
                MultipartReader multipartReader = (MultipartReader) objectRef.element;
                if (multipartReader != null) {
                    multipartReader.close();
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.m8979constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                Result.m8979constructorimpl(ResultKt.createFailure(th));
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Nullable
    public final Object invoke(@NotNull FlowCollector<? super BufferedSource> flowCollector, @Nullable Throwable th, @Nullable Continuation<? super Unit> continuation) {
        MultipartKt$multipartBodyFlow$2 multipartKt$multipartBodyFlow$2 = new MultipartKt$multipartBodyFlow$2(this.$multipartReader, continuation);
        multipartKt$multipartBodyFlow$2.L$0 = flowCollector;
        return multipartKt$multipartBodyFlow$2.invokeSuspend(Unit.INSTANCE);
    }
}
