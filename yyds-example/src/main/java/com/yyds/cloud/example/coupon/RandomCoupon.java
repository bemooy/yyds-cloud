package com.yyds.cloud.example.coupon;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 计算随机红包金额
 */
public class RandomCoupon {

    /**
     * 计算随机红包金额
     * @param winNumberCnt 剩余的红包数量
     * @param distMoney 剩余金额
     * @return
     */
    public static BigDecimal getRandomMoney(Integer winNumberCnt, BigDecimal distMoney) {
        if (distMoney.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        if (winNumberCnt == 1) {
            return distMoney;
        }
        Random r = new Random();
        BigDecimal min = BigDecimal.ZERO;
        BigDecimal max = distMoney.divide(new BigDecimal(winNumberCnt), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(new BigDecimal(2));
        BigDecimal money = max.multiply(BigDecimal.valueOf(r.nextDouble()))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
        if (money.compareTo(min) <= 0) {
            return BigDecimal.valueOf(0.01);
        } else if (money.compareTo(distMoney) >= 0) {
            return distMoney;
        } else {
            return money;
        }
    }
}
