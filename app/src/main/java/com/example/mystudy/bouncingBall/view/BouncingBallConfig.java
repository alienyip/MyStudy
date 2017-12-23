package com.example.mystudy.bouncingBall.view;

import android.graphics.Color;

/**
 * Created by Nature on 2017/9/27.
 */

public class BouncingBallConfig {

    public int ballCount;
    public int ballColor;
    public int ballRadius;
    public int cycleTime;

    public static class Builder {
        private int ballCount = 6;//默认6个
        private int ballColor = Color.BLUE;//默认蓝色
        private int ballRadius = 30;//默认半径10
        private int cycleTime = 1000;//默认一个周期3秒

        public Builder setBallCount(int ballCount) {
            this.ballCount = ballCount;
            return this;
        }

        public Builder setBallColor(int color) {
            this.ballColor = color;
            return this;
        }

        public Builder setBallRadius(int ballRadius) {
            this.ballRadius = ballRadius;
            return this;
        }

        public Builder setCycleTime(int cycleTime) {
            this.cycleTime = cycleTime;
            return this;
        }

        public void applyConfig(BouncingBallConfig config) {
            config.ballColor = this.ballColor;
            config.ballColor = this.ballCount;
            config.ballRadius = this.ballRadius;
            config.cycleTime = this.cycleTime;
        }

        public BouncingBallConfig create() {
            BouncingBallConfig config = new BouncingBallConfig();
            applyConfig(config);
            return config;
        }
    }

}
