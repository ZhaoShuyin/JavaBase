package com.zsy.pattern.create.singleton;

class Instance {

    private Instance() {
    }

    private static Instance instance = null;

    public static synchronized Instance getInstance() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (instance == null) {
            instance = new Instance();
        }
        return instance;
    }
}