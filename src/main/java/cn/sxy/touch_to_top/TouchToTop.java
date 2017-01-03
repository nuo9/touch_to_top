package cn.sxy.touch_to_top;

public class TouchToTop {
    // 从 start+1 开始跑
    private int start = 0;
    // 超过 headCount 条false则跳出
    private int headCount = 100;
    // 被测超过 touchLimit 条则跳出
    private int touchLimit = Integer.MAX_VALUE;
    // 测试方法
    private IAvailable available;
    // 状态输出
    private IOutput output = (c, m, f) -> {
    };

    private TouchToTop() {
    }

    public static TouchToTop create(IAvailable available) {
        TouchToTop t = new TouchToTop();
        t.available = available;
        return t;
    }

    public TouchToTop setOutput(IOutput output) {
        this.output = output;
        return this;
    }

    public TouchToTop setTouchLimit(int touchLimit) {
        this.touchLimit = touchLimit;
        return this;
    }

    public TouchToTop setStart(int start) {
        this.start = start;
        return this;
    }

    public TouchToTop setHeadCount(int headCount) {
        if (headCount < 1) {
            throw new IllegalArgumentException(" headCount error ");
        }

        this.headCount = headCount;
        return this;
    }

    public int touch() {
        int cursor = start;
        int max = start;
        int falseCount = 0;
        while (falseCount < headCount) {
            boolean availableResult = available.isAvailable(++cursor);
            if (availableResult) {
                max = cursor;
                falseCount = 0;
            } else {
                falseCount++;
            }

            if (cursor - start >= touchLimit) {
                return max;
            }

            output.output(cursor, max, falseCount);
        }

        return max;
    }

}
