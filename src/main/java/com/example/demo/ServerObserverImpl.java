package com.example.demo;

class ServerObserverImpl implements ServerObserver {
    private final String observerName;

    public ServerObserverImpl(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(int port) {
        System.out.println(observerName + ": Server on port " + port + " has started.");
    }
}