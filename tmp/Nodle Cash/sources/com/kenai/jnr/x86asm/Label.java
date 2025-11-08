package com.kenai.jnr.x86asm;

import java.util.LinkedList;
import java.util.List;

@Deprecated
public final class Label extends Operand {
    final int id;
    final List<LinkData> links;
    int position;
    LABEL_STATE state;

    public Label() {
        this(0);
    }

    public final boolean isBound() {
        return this.state == LABEL_STATE.LABEL_STATE_BOUND;
    }

    public final boolean isLinked() {
        return this.state == LABEL_STATE.LABEL_STATE_LINKED;
    }

    public final boolean isUnused() {
        return this.state == LABEL_STATE.LABEL_STATE_UNUSED;
    }

    public final void link(LinkData linkData) {
        this.links.add(linkData);
        this.state = LABEL_STATE.LABEL_STATE_LINKED;
    }

    public final int position() {
        return this.position;
    }

    public Label(int i3) {
        super(4, 4);
        this.links = new LinkedList();
        this.id = i3;
        this.state = LABEL_STATE.LABEL_STATE_UNUSED;
        this.position = -1;
    }
}
