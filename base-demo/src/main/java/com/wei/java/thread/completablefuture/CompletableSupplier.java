package com.wei.java.thread.completablefuture;

import java.util.UUID;
import java.util.function.Supplier;

public class CompletableSupplier implements Supplier<String> {
    @Override
    public String get() {
        return UUID.randomUUID().toString();
    }
}
