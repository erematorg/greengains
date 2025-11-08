package com.kenai.jnr.x86asm;

@Deprecated
public enum CPU {
    X86_32,
    X86_64;
    
    public static final CPU I386 = null;

    static {
        CPU cpu;
        I386 = cpu;
    }
}
