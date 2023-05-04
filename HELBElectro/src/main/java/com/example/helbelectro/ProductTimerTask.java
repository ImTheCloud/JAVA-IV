package com.example.helbelectro;

import java.util.TimerTask;

class ProductTimerTask extends TimerTask {
    private final Product product;

    public ProductTimerTask(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        System.out.println(product.getClass().getSimpleName() + " a été fabriqué");
    }
}
