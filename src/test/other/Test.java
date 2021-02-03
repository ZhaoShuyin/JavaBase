package test.other;

import java.util.Arrays;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Title test
 */

class Test {

    static int[][] datasReal = new int[18][32];             //波形数据[哪个导联][一次刷新数据个数]
    static int[][] datasRefresh = new int[18][16];         //波形数据[哪个导联][一次刷新数据个数]
    static int mDataIndex = 0;               //数据计算
    static int mRefreshIndex = 0;           //数据计算

    public static ConcurrentLinkedQueue<Short> linkedQueue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        datasRefresh = new int[18][16];//32取4
        add();
        System.out.println();
        cal125(4);
        datasRefresh = new int[18][8];//32取8
        add();
        cal125(8);
        datasRefresh = new int[18][4];//32取16
        add();
        cal125(16);
    }

    /**
     * 计算点
     * 12.5mm/s : 4取2  32个点取 16个  scale 4
     * 25mm/s :   8取2  32个点取 8个   scale 8
     * 50mm/s :   16取2 32个点取 4个   scale 16
     *
     * @param scale
     */
    private static void cal125(int scale) {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                datasReal[j][i] = (linkedQueue.isEmpty()) ? 0 : (int) ((Short) linkedQueue.poll());
            }
        }
        System.out.println(Arrays.toString(datasReal[0]));
        int max = -32767;
        int min = 32766;
        int temp = 0;
        int ecg_max_idx = 0;
        int ecg_min_idx = 0;
        mRefreshIndex = 32 / scale;
        for (int i = 0; i < mRefreshIndex; i++) {
            for (int j = 0; j < 18; j++) {
                int position = (int) (i * scale);//获取索引值
                for (int k = 0; k < scale; k++) {
                    temp = datasReal[j][position + k];
                    if (temp >= max) {
                        max = temp;
                        ecg_max_idx = k;
                    }
                    if (temp <= min) {
                        min = temp;
                        ecg_min_idx = k;
                    }
                }
                if (ecg_max_idx < ecg_min_idx) { //降序
                    datasRefresh[j][2 * i] = max;
                    datasRefresh[j][2 * i + 1] = min;
                } else {                         //升序
                    datasRefresh[j][2 * i] = min;
                    datasRefresh[j][2 * i + 1] = max;
                }
                max = -32767;
                min = 32766;
            }
        }
        System.out.println(mRefreshIndex+"  "+ Arrays.toString(datasRefresh[0]));
    }


    private static void add() {
        linkedQueue.clear();
        for (int j = 0; j < 500; j++) {
            for (int i = 0; i < 18; i++) {
                linkedQueue.add((short) (j + 1)); // 1111 1111 1111 1111
            }
        }
    }

}
