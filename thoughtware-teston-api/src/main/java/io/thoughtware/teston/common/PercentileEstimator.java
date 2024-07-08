package io.thoughtware.teston.common;

public interface PercentileEstimator {
    /**
     * 添加一个新的数据点到估算器中
     * @param value 要添加的数据点
     */
    void add(double value);

    /**
     * 获取当前的百分位数估计值
     * @return 估计的百分位数值
     */
    double estimate();

    /**
     * 获取此估算器正在计算的百分位数
     * @return 百分位数（0到1之间的值）
     */
    double getPercentile();

    /**
     * 获取已处理的数据点数量
     * @return 数据点数量
     */
    long getCount();
}