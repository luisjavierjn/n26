package com.n26.domain.dto;

import java.math.BigDecimal;

public class StatisticsDTO {

    private BigDecimal sum = new BigDecimal("0.00");
    private BigDecimal avg = new BigDecimal("0.00");
    private BigDecimal max = new BigDecimal("0.00");
    private BigDecimal min = new BigDecimal("0.00");
    private long count;

    public String getSum() {
        return sum.toString();
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getAvg() {
        return avg.toString();
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getMax() {
        return max.toString();
    }

    public void setMax(BigDecimal max) {
        this.max = max.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public String getMin() {
        return min.toString();
    }

    public void setMin(BigDecimal min) {
        this.min = min.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
