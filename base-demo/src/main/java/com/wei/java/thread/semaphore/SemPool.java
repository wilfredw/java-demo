package com.wei.java.thread.semaphore;

import java.util.concurrent.Semaphore;

public class SemPool {
    private Semaphore semaphore;

    public SemPool(int size) {
        semaphore = new Semaphore(size);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}
