package com.neiizun.cordcoin.objects;

public class Conf {
    private final double minerPrice;
    private final double startingPrice;
    private final double minMinerGain;
    private final int minerHarvestInterval;

    public Conf(double minerPrice, double startingPrice, double minMinerGain, int minerHarvestInterval) {
        this.minerPrice = minerPrice;
        this.startingPrice = startingPrice;
        this.minMinerGain = minMinerGain;
        this.minerHarvestInterval = minerHarvestInterval;
    }


    public double getMinerPrice() {
        return this.minerPrice;
    }

    public double getStartingPrice() {
        return this.startingPrice;
    }

    public double getMinMinerGain() {
        return this.minMinerGain;
    }

    public int getMinerHarvestInterval() {
        return minerHarvestInterval;
    }
}
