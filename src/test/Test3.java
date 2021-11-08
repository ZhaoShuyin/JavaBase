package test;

/**
 * @Title test
 * @date 2020/9/6
 * @autor Zsy
 */
public class Test3 {

    public static void bubbleSort(int a[]) {
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void selectSort(int a[]) {
        int temp = 0;
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            int min = a[i];
            int index = i;
            for (int j = i + 1; j < len; j++) {
                if (min > a[j]) {
                    min = a[j];
                    index = j;
                }
            }
            temp = a[i];
            a[i] = a[index];
            a[index] = temp;
        }
    }

    public static void insertSort(int a[]) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            int temp = a[i];// 待插入的值
            int index = i;// 待插入的位置
            while (index > 0 && a[index - 1] > temp) {
                a[index] = a[index - 1];// 待插入的位置重新赋更大的值
                index--;// 位置往前移
            }
            a[index] = temp;
        }
    }

    public static int partition(int a[], int low, int height) {
        int key = a[low];
        while (low < height) {
            while (low < height && a[height] >= key)
                height--;
            a[low] = a[height];
            while (low < height && a[low] <= key)
                low++;
            a[height] = a[low];
        }
        a[low] = key;
        return low;
    }

    public static void quickSort(int a[], int low, int height) {
        if (low < height) {
            int result = partition(a, low, height);
            quickSort(a, low, result - 1);
            quickSort(a, result + 1, height);
        }
    }

}
