import junit.framework.TestCase;

import java.util.Arrays;

import struct.JavaStruct;
import struct.PublicPrimitiveArrays;
import struct.StructException;

/**
 * @Title PACKAGE_NAME
 * @date 2021/5/13
 * @autor Zsy
 */

public class Test extends TestCase {

    public void testArrays() {
        PublicPrimitiveArrays ppa = new PublicPrimitiveArrays();
        ppa.init(2);
        ppa.setAsc(2);
        try {
            byte[] b = JavaStruct.pack(ppa);
            System.out.println(Arrays.toString(b));
        } catch (StructException e) {
            e.printStackTrace();
        }
    }

}
