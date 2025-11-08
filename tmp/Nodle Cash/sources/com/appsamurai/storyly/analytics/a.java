package com.appsamurai.storyly.analytics;

import java.util.List;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.Nullable;

public enum a {
    Selected(CollectionsKt.listOf(r3, StorylyEvent.StoryGroupUserOpened)),
    DeepLinkOpened(CollectionsKt.listOf(r3, StorylyEvent.StoryGroupDeepLinkOpened)),
    ProgrammaticallySelected(CollectionsKt.listOf(r3, StorylyEvent.StoryGroupProgrammaticallyOpened)),
    GroupComplete(CollectionsKt.listOf(StorylyEvent.StoryGroupCompleted)),
    LastGroupCompleted((String) null),
    PreviousSwiped(CollectionsKt.listOf(StorylyEvent.StoryGroupPreviousSwiped)),
    NextSwiped(CollectionsKt.listOf(StorylyEvent.StoryGroupNextSwiped)),
    Dismissed(CollectionsKt.listOf(r9)),
    Closed(CollectionsKt.listOf(r9)),
    Impression(CollectionsKt.listOf(StorylyEvent.StoryImpression)),
    Viewed(CollectionsKt.listOf(StorylyEvent.StoryViewed)),
    Complete(CollectionsKt.listOf(StorylyEvent.StoryCompleted)),
    PreviousClicked(CollectionsKt.listOf(StorylyEvent.StoryPreviousClicked)),
    NextClicked(CollectionsKt.listOf(StorylyEvent.StoryNextClicked)),
    Paused(CollectionsKt.listOf(StorylyEvent.StoryPaused)),
    Resumed(CollectionsKt.listOf(StorylyEvent.StoryResumed)),
    LoadFailed((String) null),
    LinkShared(CollectionsKt.listOf(StorylyEvent.StoryShared)),
    ActionClicked(CollectionsKt.listOf(StorylyEvent.StoryCTAClicked)),
    ReactionClicked(CollectionsKt.listOf(StorylyEvent.StoryEmojiClicked)),
    PollSelected(CollectionsKt.listOf(StorylyEvent.StoryPollAnswered)),
    QuizAnswered(CollectionsKt.listOf(StorylyEvent.StoryQuizAnswered)),
    ImageQuizAnswered(CollectionsKt.listOf(StorylyEvent.StoryImageQuizAnswered)),
    CountdownReminderAdded(CollectionsKt.listOf(StorylyEvent.StoryCountdownReminderAdded)),
    CountdownReminderRemoved(CollectionsKt.listOf(StorylyEvent.StoryCountdownReminderRemoved)),
    Rated(CollectionsKt.listOf(StorylyEvent.StoryRated)),
    InteractiveImpression(CollectionsKt.listOf(StorylyEvent.StoryInteractiveImpression)),
    ProductTagExpanded(CollectionsKt.listOf(StorylyEvent.StoryProductTagExpanded)),
    ProductTagClicked(CollectionsKt.listOf(StorylyEvent.StoryProductTagClicked)),
    ProductCardClicked(CollectionsKt.listOf(StorylyEvent.StoryProductCardClicked)),
    PromoCodeCopied(CollectionsKt.listOf(StorylyEvent.StoryPromoCodeCopied)),
    CommentSent(CollectionsKt.listOf(StorylyEvent.StoryCommentSent)),
    CommentInputFocus(CollectionsKt.listOf(StorylyEvent.StoryCommentInputOpened)),
    CommentInputFocusClear(CollectionsKt.listOf(StorylyEvent.StoryCommentInputClosed)),
    Seeked(CollectionsKt.listOf(StorylyEvent.StorySeeked)),
    OnScreen((String) null),
    ApiLoadFailed((String) null),
    ParseFailed((String) null),
    BarViewed((String) null),
    Liked(CollectionsKt.listOf(StorylyEvent.StoryLiked)),
    Unliked(CollectionsKt.listOf(StorylyEvent.StoryUnliked)),
    ProductAdded(CollectionsKt.listOf(StorylyEvent.StoryProductAdded)),
    ProductUpdated(CollectionsKt.listOf(StorylyEvent.StoryProductUpdated)),
    ProductRemoved(CollectionsKt.listOf(StorylyEvent.StoryProductRemoved)),
    CheckoutButtonClicked(CollectionsKt.listOf(StorylyEvent.StoryCheckoutButtonClicked)),
    CartButtonClicked(CollectionsKt.listOf(StorylyEvent.StoryCartButtonClicked)),
    CartViewClicked(CollectionsKt.listOf(StorylyEvent.StoryCartViewClicked)),
    ProductCatalogClicked(CollectionsKt.listOf(StorylyEvent.StoryProductCatalogClicked)),
    ProductSelected(CollectionsKt.listOf(StorylyEvent.StoryProductSelected));
    
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final List<StorylyEvent> f3501a;

    /* access modifiers changed from: public */
    a(List<? extends StorylyEvent> list) {
        this.f3501a = list;
    }
}
