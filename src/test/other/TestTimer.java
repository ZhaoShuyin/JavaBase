package test.other;

import java.io.FileOutputStream;

/**
 * @Title test
 */

class TestTimer {

    public interface RecordListener {
        void record(int remain, int progress);//正在记录

        void error(String s, int total);    //记录错误
    }

    static RecordListener listener;
    static  int duration = 10;
    public static void main(String[] args) {
        listener = new RecordListener() {
            @Override
            public void record(int remain, int progress) {
                System.out.println(remain + "  " + progress);
            }

            @Override
            public void error(String s, int total) {
                System.out.println(s);
            }
        };

        recordCount = 0;
        recordTotal = duration * 500;
        recording = true;
        while (recording) {
            record(null);
        }

    }

    private static FileOutputStream outputStream;         //记录
    private static boolean recording;                        //记录标记
    private static double recordCount;                             //记录计时记数
    private static double recordTotal;                             //记录计时记数
    private static int recordDuration;                           //记录计时秒数
    private static boolean receiving;

    private static void record(byte[] bytes) {
        if (recordCount > recordTotal) {
            recording = false;
            try {
                System.out.println("outputStream.close();");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
//                System.out.println("outputStream.write(bytes, 0, 36)  recordCount: "+recordCount);
                recordCount++;
            } catch (Exception e) {
                e.printStackTrace();
                recording = false;
            }
            if ((recordCount % 100 == 0) && listener != null) {
                System.out.println("outputStream.write(bytes, 0, 36)  recordCount: "+recordCount);
                double percent = recordCount / recordTotal;
                listener.record((int) (duration-recordCount/500), (int) (percent * 100));
            }
        }
    }

}
