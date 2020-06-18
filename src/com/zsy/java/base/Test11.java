package com.zsy.java.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Title com.zsy.java.base
 * @date 2020/4/20
 * @autor Zsy
 */

class Test11 {

    static boolean aBoolean;
    static final int[] count = {0};
    static ExecutorService executorService = Executors.newSingleThreadExecutor();
    static InputStream inputStream;

    public static void main(String[] args) {
        count[0] = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                inputStream = new InputStream() {
                    @Override
                    public int read() throws IOException {
                        try {
                            Thread.sleep(1000);
                            if (count[0] >= 10) {
                                aBoolean = false;
                            } else {
                                count[0]++;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return 1;
                    }
                };
            }
        }).start();
        final int[] number = {0};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                number[0]++;
             /*   if (number[0] == 3) {
                    try {
                        boolean b = executorService.awaitTermination(1, TimeUnit.SECONDS);
                        System.out.println("线程池取消 awaitTermination:" + b);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
                if (number[0] == 5) {
                    executorService.shutdownNow();
                    System.out.println("线程池取消 shutdownNow");
                }
//                executorService.shutdown();
//                System.out.println("线程池取消 shutdown");

            }
        }, 0, 1000);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startRecord("AA", inputStream);
    }

    private static void startRecord(String name, InputStream inputStream) {
        count[0] = 0;

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:/123/record/text.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        aBoolean = true;
        FileOutputStream finalOutputStream = outputStream;
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[1];
                int len = 0;
                while (aBoolean && !executorService.isShutdown()) {
                    try {
                        if ((len = inputStream.read(bytes)) != -1) {
                            finalOutputStream.write(bytes);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + "  " + String.valueOf(count[0]) + " -> " + bytes[0]);
                }
                try {
                    inputStream.close();
                    finalOutputStream.close();
                    System.out.println(name + " 关闭流");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(name + " 关闭流" + e.toString());
                }
            }
        });
        /*executorService.submit(new TimerTask() {
            @Override
            public void run() {
                byte[] bytes = new byte[1];
                int len = 0;
                while (aBoolean) {
                    try {
                        if ((len = inputStream.read(bytes)) != -1) {
                            finalOutputStream.write(bytes);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(name + "  " + String.valueOf(count[0]) + " -> " + bytes[0]);
                }
                try {
                    inputStream.close();
                    finalOutputStream.close();
                    System.out.println(name + " 关闭流");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(name + " 关闭流" + e.toString());
                }
            }
        });*/
    }


}

