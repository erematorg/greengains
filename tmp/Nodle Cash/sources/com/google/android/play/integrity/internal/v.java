package com.google.android.play.integrity.internal;

final class v extends r {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ac f6845a;

    public v(ac acVar) {
        this.f6845a = acVar;
    }

    public final void b() {
        synchronized (this.f6845a.f6819g) {
            try {
                if (this.f6845a.f6825m.get() > 0) {
                    if (this.f6845a.f6825m.decrementAndGet() > 0) {
                        this.f6845a.f6815c.c("Leaving the connection open for other ongoing calls.", new Object[0]);
                        return;
                    }
                }
                ac acVar = this.f6845a;
                if (acVar.f6827o != null) {
                    acVar.f6815c.c("Unbind from service.", new Object[0]);
                    ac acVar2 = this.f6845a;
                    acVar2.f6814b.unbindService(acVar2.f6826n);
                    this.f6845a.f6820h = false;
                    this.f6845a.f6827o = null;
                    this.f6845a.f6826n = null;
                }
                this.f6845a.x();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
