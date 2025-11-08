package com.reown.android.internal.common.explorer;

import kotlin.jvm.functions.Function0;

public final /* synthetic */ class a implements Function0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7320a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ExplorerProtocol f7321b;

    public /* synthetic */ a(ExplorerProtocol explorerProtocol, int i3) {
        this.f7320a = i3;
        this.f7321b = explorerProtocol;
    }

    public final Object invoke() {
        int i3 = this.f7320a;
        ExplorerProtocol explorerProtocol = this.f7321b;
        switch (i3) {
            case 0:
                return ExplorerProtocol.getProjectsWithPaginationUseCase_delegate$lambda$0(explorerProtocol);
            default:
                return ExplorerProtocol.logger_delegate$lambda$1(explorerProtocol);
        }
    }
}
