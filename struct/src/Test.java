import java.io.Serializable;
import java.util.Arrays;

import struct.JavaStruct;
import struct.StructClass;
import struct.StructException;
import struct.StructField;

/**
 * @Title PACKAGE_NAME
 * @date 2021/5/7
 * @autor Zsy
 */

public class Test {

    @StructClass
    public class Foo implements Serializable {
        @StructField(order = 0)
        public short[] iis;
    }

    public void TestFoo() {
        try {
            // Pack the class as a byte buffer
            // [1,  0, 0, 0, 2,  0, 0, 0, 0,               1 ]
            // [1,  0, 0, 0, 2,  0, 0, 0, 0,  0, 0, 0, 0,  1 ]
            Foo f = new Foo();
            f.iis = new short[1];
            byte[] b = JavaStruct.pack(f);
            System.out.println(Arrays.toString(b));
           /* for (int i = 0; i < b.length; i++) {
                System.out.printf("b[%d]: %d\n", i, b[i]);
            }
            */
            b[0] = 0x05;
            b[1] = (byte) 0xC0;
            System.out.println(0x05 << 8 | 0xC0 & 255);
            // Unpack it into an object
            Foo f2 = new Foo();
            f2.iis = new short[1];

            JavaStruct.unpack(f2, b);

            System.out.println("f2.iis " + Arrays.toString(f2.iis));

        } catch (StructException e) {
            e.printStackTrace();
        }
    }

    @StructClass
    public class Arr implements Serializable {
        private static final long serialVersionUID = 8356611007456552681L;
        @StructField(order = 0)
        public byte[] b;
        @StructField(order = 1)
        public int[] i;
    }

    public void TestArr() {
        Arr arr = new Arr();
        arr.b = new byte[]{1, 1};
        arr.i = new int[]{2, 3};
        try {
            byte[] b = JavaStruct.pack(arr);
            System.out.println(Arrays.toString(b));
        } catch (StructException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Test t = new Test();
        t.TestFoo();
        System.out.println("====================================");
        t.TestArr();
    }


}
