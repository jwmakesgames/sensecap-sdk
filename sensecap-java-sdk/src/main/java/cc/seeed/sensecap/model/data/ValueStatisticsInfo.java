package cc.seeed.sensecap.model.data;


/**
 * @Author ascgoogle
 * @Description
 * @Date 2020/9/22 17:44
 * @Version V1.0
 */
public class ValueStatisticsInfo {

    private double total;
    private int count;
    private long time;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
