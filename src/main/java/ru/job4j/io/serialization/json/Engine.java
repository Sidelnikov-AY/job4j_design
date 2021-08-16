package ru.job4j.io.serialization.json;

public class Engine {
    private final boolean turbo;
    private final double engineVolume;

    public Engine(boolean turbo, double engineVolume) {
        this.turbo = turbo;
        this.engineVolume = engineVolume;
    }

    @Override
    public String toString() {
        return "Engine{"
                +
                "turbo=" + turbo
                +
                ", engineVolume=" + engineVolume
                +
                '}';
    }
}
