package test.util.filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Title test.util.filter
 * @date 2021/4/1
 * @autor Zsy
 */

public class Filter {

    private static float[][] offset = new float[18][1601];
    private static float[] offsetReult = new float[18];
    private static int offsetIndex = -1;

    private static float[][] average = new float[18][10];
    private static int averageIndex = -1;
    private static boolean filterMode = true;
    private static boolean averMode = true;



    public static void main(String[] args) {
        File file = new File("D:/Test/apk/2768821033100007/origin.data");
        File target = new File("D:/Test/apk/origin.data");
        boolean filter = filter(file, target);
        System.out.println("转换 结果 :" + filter);
        System.out.println(" 原来文件 :" + file.length() + "  " + file.getAbsolutePath());
        System.out.println(" 转换后文件: " + target.length() + "  " + target.getAbsolutePath());
    }

    public static boolean filter(File file, File target) {
        if (!file.exists()) {
            return false;
        }
        if (!target.exists()) {
            try {
                target.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        try {
            FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(target);
            file.length();
            byte[] bytes = new byte[24];
            while (inputStream.read(bytes) > 0) {
                toInts(bytes);
                calculate();
                getBytes(temp);
                outputStream.write(dataByte);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return false;
        }
    }

    protected static int[] temp = new int[12];

    public static void toInts(byte[] bytes) {
        for (int i = 0; i < temp.length; i++) {
            temp[i] = bytes[2 * i] & 255 | bytes[2 * i + 1] << 8;
        }
    }

    private static byte[] dataByte = new byte[24];

    public static byte[] getBytes(int[] ints) {
        dataByte[0] = (byte) (ints[0] & 255);
        dataByte[1] = (byte) (ints[0] >> 8);
        dataByte[2] = (byte) (ints[1] & 255);
        dataByte[3] = (byte) (ints[1] >> 8);
        dataByte[4] = (byte) (ints[2] & 255);
        dataByte[5] = (byte) (ints[2] >> 8);
        dataByte[6] = (byte) (ints[3] & 255);
        dataByte[7] = (byte) (ints[3] >> 8);
        dataByte[8] = (byte) (ints[4] & 255);
        dataByte[9] = (byte) (ints[4] >> 8);
        dataByte[10] = (byte) (ints[5] & 255);
        dataByte[11] = (byte) (ints[5] >> 8);
        dataByte[12] = (byte) (ints[6] & 255);
        dataByte[13] = (byte) (ints[6] >> 8);
        dataByte[14] = (byte) (ints[7] & 255);
        dataByte[15] = (byte) (ints[7] >> 8);
        dataByte[16] = (byte) (ints[8] & 255);
        dataByte[17] = (byte) (ints[8] >> 8);
        dataByte[18] = (byte) (ints[9] & 255);
        dataByte[19] = (byte) (ints[9] >> 8);
        dataByte[20] = (byte) (ints[10] & 255);
        dataByte[21] = (byte) (ints[10] >> 8);
        dataByte[22] = (byte) (ints[11] & 255);
        dataByte[23] = (byte) (ints[11] >> 8);

//        dataByte[24] = (byte) (ints[12] & 255);
//        dataByte[25] = (byte) (ints[12] >> 8);
//        dataByte[26] = (byte) (ints[13] & 255);
//        dataByte[27] = (byte) (ints[13] >> 8);
//        dataByte[28] = (byte) (ints[14] & 255);
//        dataByte[29] = (byte) (ints[14] >> 8);
//        dataByte[30] = (byte) (ints[15] & 255);
//        dataByte[31] = (byte) (ints[15] >> 8);
//        dataByte[32] = (byte) (ints[16] & 255);
//        dataByte[33] = (byte) (ints[16] >> 8);
//        dataByte[34] = (byte) (ints[17] & 255);
//        dataByte[35] = (byte) (ints[17] >> 8);

        return dataByte;
    }

    public static void calculate() {
        int index = 0;

        offsetIndex++;
        if (offsetIndex >= 1601) {
            offsetIndex = 0;
        }
        averageIndex++;
        if (averageIndex >= 10) {
            averageIndex = 0;
        }
        for (int i = 0; i < 12; i++) {
            if (filterMode) {                                          //开启基线滤波
                offset[i][offsetIndex] = temp[i];
                offsetReult[i] = 0;
                for (int j = 0; j < 1601; j++) {
                    index = (offsetIndex + j) % 1601;
                    offsetReult[i] += offset[i][index] * floats[j];
                }
                //平均数数组赋值
                average[i][averageIndex] = offsetReult[i];
            } else {                                              //并不开启基线滤波
                average[i][averageIndex] = temp[i];
            }
            //使用平均数平滑滤波
            if (averMode) {
                temp[i] = (int) ((average[i][0] + average[i][1] + average[i][2] + average[i][3] + average[i][4]
                        + average[i][5] + average[i][6] + average[i][7] + average[i][8] + average[i][9])
                        / 10);
            } else {
                temp[i] = (int) average[i][averageIndex];
            }
        }
    }

    public static float[] floats = {
            0.000030272154f, 0.000030372668f, 0.000030474941f, 0.000030578992f, 0.000030684841f,
            0.000030792505f, 0.000030902004f, 0.000031013354f, 0.000031126573f, 0.000031241677f,
            0.000031358682f, 0.000031477603f, 0.000031598455f, 0.000031721252f, 0.000031846007f,
            0.000031972733f, 0.000032101443f, 0.000032232148f, 0.000032364859f, 0.000032499588f,
            0.000032636342f, 0.000032775133f, 0.000032915967f, 0.000033058854f, 0.000033203800f,
            0.000033350813f, 0.000033499897f, 0.000033651059f, 0.000033804303f, 0.000033959633f,
            0.000034117053f, 0.000034276564f, 0.000034438169f, 0.000034601870f, 0.000034767666f,
            0.000034935559f, 0.000035105546f, 0.000035277626f, 0.000035451798f, 0.000035628059f,
            0.000035806404f, 0.000035986829f, 0.000036169330f, 0.000036353900f, 0.000036540534f,
            0.000036729224f, 0.000036919961f, 0.000037112738f, 0.000037307545f, 0.000037504371f,
            0.000037703206f, 0.000037904038f, 0.000038106855f, 0.000038311644f, 0.000038518390f,
            0.000038727079f, 0.000038937695f, 0.000039150223f, 0.000039364646f, 0.000039580945f,
            0.000039799102f, 0.000040019098f, 0.000040240912f, 0.000040464525f, 0.000040689914f,
            0.000040917057f, 0.000041145930f, 0.000041376511f, 0.000041608773f, 0.000041842692f,
            0.000042078242f, 0.000042315394f, 0.000042554121f, 0.000042794395f, 0.000043036186f,
            0.000043279463f, 0.000043524196f, 0.000043770352f, 0.000044017900f, 0.000044266804f,
            0.000044517032f, 0.000044768548f, 0.000045021316f, 0.000045275299f, 0.000045530460f,
            0.000045786761f, 0.000046044162f, 0.000046302624f, 0.000046562105f, 0.000046822564f,
            0.000047083960f, 0.000047346247f, 0.000047609384f, 0.000047873325f, 0.000048138024f,
            0.000048403435f, 0.000048669511f, 0.000048936204f, 0.000049203464f, 0.000049471244f,
            0.000049739491f, 0.000050008156f, 0.000050277185f, 0.000050546527f, 0.000050816127f,
            0.000051085931f, 0.000051355885f, 0.000051625932f, 0.000051896015f, 0.000052166077f,
            0.000052436059f, 0.000052705903f, 0.000052975549f, 0.000053244935f, 0.000053514001f,
            0.000053782684f, 0.000054050922f, 0.000054318650f, 0.000054585804f, 0.000054852318f,
            0.000055118127f, 0.000055383164f, 0.000055647361f, 0.000055910650f, 0.000056172961f,
            0.000056434225f, 0.000056694372f, 0.000056953329f, 0.000057211026f, 0.000057467388f,
            0.000057722343f, 0.000057975815f, 0.000058227731f, 0.000058478015f, 0.000058726589f,
            0.000058973377f, 0.000059218301f, 0.000059461283f, 0.000059702242f, 0.000059941099f,
            0.000060177774f, 0.000060412184f, 0.000060644249f, 0.000060873884f, 0.000061101007f,
            0.000061325534f, 0.000061547380f, 0.000061766459f, 0.000061982685f, 0.000062195972f,
            0.000062406232f, 0.000062613377f, 0.000062817319f, 0.000063017968f, 0.000063215234f,
            0.000063409027f, 0.000063599255f, 0.000063785827f, 0.000063968651f, 0.000064147633f,
            0.000064322680f, 0.000064493697f, 0.000064660590f, 0.000064823265f, 0.000064981623f,
            0.000065135571f, 0.000065285009f, 0.000065429841f, 0.000065569969f, 0.000065705295f,
            0.000065835718f, 0.000065961139f, 0.000066081458f, 0.000066196575f, 0.000066306388f,
            0.000066410795f, 0.000066509695f, 0.000066602984f, 0.000066690560f, 0.000066772318f,
            0.000066848156f, 0.000066917967f, 0.000066981648f, 0.000067039093f, 0.000067090197f,
            0.000067134851f, 0.000067172952f, 0.000067204390f, 0.000067229059f, 0.000067246851f,
            0.000067257657f, 0.000067261369f, 0.000067257879f, 0.000067247076f, 0.000067228851f,
            0.000067203094f, 0.000067169695f, 0.000067128542f, 0.000067079526f, 0.000067022534f,
            0.000066957456f, 0.000066884178f, 0.000066802590f, 0.000066712578f, 0.000066614030f,
            0.000066506833f, 0.000066390873f, 0.000066266038f, 0.000066132214f, 0.000065989286f,
            0.000065837141f, 0.000065675664f, 0.000065504741f, 0.000065324258f, 0.000065134099f,
            0.000064934149f, 0.000064724293f, 0.000064504416f, 0.000064274403f, 0.000064034138f,
            0.000063783505f, 0.000063522389f, 0.000063250673f, 0.000062968242f, 0.000062674980f,
            0.000062370770f, 0.000062055497f, 0.000061729044f, 0.000061391295f, 0.000061042134f,
            0.000060681445f, 0.000060309110f, 0.000059925015f, 0.000059529042f, 0.000059121076f,
            0.000058701000f, 0.000058268698f, 0.000057824054f, 0.000057366952f, 0.000056897276f,
            0.000056414911f, 0.000055919739f, 0.000055411647f, 0.000054890517f, 0.000054356235f,
            0.000053808686f, 0.000053247754f, 0.000052673324f, 0.000052085282f, 0.000051483513f,
            0.000050867902f, 0.000050238335f, 0.000049594699f, 0.000048936878f, 0.000048264761f,
            0.000047578233f, 0.000046877182f, 0.000046161494f, 0.000045431058f, 0.000044685760f,
            0.000043925489f, 0.000043150134f, 0.000042359582f, 0.000041553723f, 0.000040732447f,
            0.000039895643f, 0.000039043201f, 0.000038175011f, 0.000037290965f, 0.000036390953f,
            0.000035474867f, 0.000034542600f, 0.000033594044f, 0.000032629091f, 0.000031647635f,
            0.000030649570f, 0.000029634789f, 0.000028603189f, 0.000027554664f, 0.000026489109f,
            0.000025406422f, 0.000024306498f, 0.000023189236f, 0.000022054533f, 0.000020902287f,
            0.000019732398f, 0.000018544764f, 0.000017339287f, 0.000016115867f, 0.000014874405f,
            0.000013614803f, 0.000012336964f, 0.000011040792f, 0.000009726189f, 0.000008393061f,
            0.000007041313f, 0.000005670850f, 0.000004281580f, 0.000002873409f, 0.000001446246f,
            -0.000000000000f, -0.000001465421f, -0.000002950105f, -0.000004454142f, -0.000005977621f,
            -0.000007520628f, -0.000009083250f, -0.000010665574f, -0.000012267683f, -0.000013889664f,
            -0.000015531598f, -0.000017193569f, -0.000018875658f, -0.000020577947f, -0.000022300515f,
            -0.000024043442f, -0.000025806805f, -0.000027590682f, -0.000029395150f, -0.000031220284f,
            -0.000033066159f, -0.000034932849f, -0.000036820426f, -0.000038728962f, -0.000040658528f,
            -0.000042609194f, -0.000044581028f, -0.000046574099f, -0.000048588474f, -0.000050624218f,
            -0.000052681397f, -0.000054760073f, -0.000056860311f, -0.000058982171f, -0.000061125715f,
            -0.000063291002f, -0.000065478090f, -0.000067687038f, -0.000069917901f, -0.000072170735f,
            -0.000074445594f, -0.000076742532f, -0.000079061599f, -0.000081402848f, -0.000083766327f,
            -0.000086152086f, -0.000088560171f, -0.000090990628f, -0.000093443504f, -0.000095918842f,
            -0.000098416683f, -0.000100937071f, -0.000103480045f, -0.000106045645f, -0.000108633907f,
            -0.000111244869f, -0.000113878566f, -0.000116535033f, -0.000119214301f, -0.000121916404f,
            -0.000124641371f, -0.000127389231f, -0.000130160012f, -0.000132953741f, -0.000135770442f,
            -0.000138610141f, -0.000141472859f, -0.000144358618f, -0.000147267438f, -0.000150199338f,
            -0.000153154335f, -0.000156132444f, -0.000159133682f, -0.000162158060f, -0.000165205592f,
            -0.000168276287f, -0.000171370156f, -0.000174487205f, -0.000177627441f, -0.000180790871f,
            -0.000183977496f, -0.000187187320f, -0.000190420343f, -0.000193676566f, -0.000196955986f,
            -0.000200258599f, -0.000203584402f, -0.000206933389f, -0.000210305550f, -0.000213700879f,
            -0.000217119363f, -0.000220560992f, -0.000224025752f, -0.000227513629f, -0.000231024605f,
            -0.000234558664f, -0.000238115786f, -0.000241695951f, -0.000245299136f, -0.000248925319f,
            -0.000252574473f, -0.000256246573f, -0.000259941591f, -0.000263659496f, -0.000267400259f,
            -0.000271163847f, -0.000274950225f, -0.000278759359f, -0.000282591211f, -0.000286445743f,
            -0.000290322916f, -0.000294222686f, -0.000298145012f, -0.000302089850f, -0.000306057152f,
            -0.000310046872f, -0.000314058960f, -0.000318093367f, -0.000322150039f, -0.000326228924f,
            -0.000330329966f, -0.000334453109f, -0.000338598294f, -0.000342765462f, -0.000346954551f,
            -0.000351165499f, -0.000355398242f, -0.000359652713f, -0.000363928846f, -0.000368226571f,
            -0.000372545818f, -0.000376886515f, -0.000381248589f, -0.000385631964f, -0.000390036565f,
            -0.000394462313f, -0.000398909128f, -0.000403376930f, -0.000407865635f, -0.000412375160f,
            -0.000416905419f, -0.000421456325f, -0.000426027788f, -0.000430619720f, -0.000435232027f,
            -0.000439864616f, -0.000444517393f, -0.000449190262f, -0.000453883124f, -0.000458595880f,
            -0.000463328430f, -0.000468080670f, -0.000472852498f, -0.000477643806f, -0.000482454490f,
            -0.000487284439f, -0.000492133545f, -0.000497001696f, -0.000501888779f, -0.000506794679f,
            -0.000511719282f, -0.000516662469f, -0.000521624121f, -0.000526604119f, -0.000531602340f,
            -0.000536618662f, -0.000541652959f, -0.000546705106f, -0.000551774975f, -0.000556862437f,
            -0.000561967361f, -0.000567089615f, -0.000572229067f, -0.000577385581f, -0.000582559021f,
            -0.000587749249f, -0.000592956127f, -0.000598179513f, -0.000603419267f, -0.000608675244f,
            -0.000613947300f, -0.000619235289f, -0.000624539064f, -0.000629858475f, -0.000635193373f,
            -0.000640543605f, -0.000645909020f, -0.000651289462f, -0.000656684776f, -0.000662094805f,
            -0.000667519391f, -0.000672958374f, -0.000678411594f, -0.000683878887f, -0.000689360090f,
            -0.000694855039f, -0.000700363567f, -0.000705885507f, -0.000711420691f, -0.000716968947f,
            -0.000722530105f, -0.000728103992f, -0.000733690435f, -0.000739289259f, -0.000744900287f,
            -0.000750523342f, -0.000756158245f, -0.000761804817f, -0.000767462876f, -0.000773132240f,
            -0.000778812725f, -0.000784504148f, -0.000790206322f, -0.000795919060f, -0.000801642174f,
            -0.000807375476f, -0.000813118774f, -0.000818871878f, -0.000824634595f, -0.000830406730f,
            -0.000836188091f, -0.000841978479f, -0.000847777700f, -0.000853585554f, -0.000859401843f,
            -0.000865226367f, -0.000871058925f, -0.000876899315f, -0.000882747333f, -0.000888602776f,
            -0.000894465439f, -0.000900335115f, -0.000906211597f, -0.000912094679f, -0.000917984150f,
            -0.000923879801f, -0.000929781421f, -0.000935688799f, -0.000941601722f, -0.000947519977f,
            -0.000953443349f, -0.000959371623f, -0.000965304583f, -0.000971242013f, -0.000977183694f,
            -0.000983129409f, -0.000989078937f, -0.000995032060f, -0.001000988555f, -0.001006948201f,
            -0.001012910776f, -0.001018876057f, -0.001024843820f, -0.001030813841f, -0.001036785893f,
            -0.001042759751f, -0.001048735188f, -0.001054711978f, -0.001060689891f, -0.001066668700f,
            -0.001072648175f, -0.001078628086f, -0.001084608203f, -0.001090588295f, -0.001096568129f,
            -0.001102547474f, -0.001108526098f, -0.001114503765f, -0.001120480244f, -0.001126455299f,
            -0.001132428696f, -0.001138400199f, -0.001144369572f, -0.001150336580f, -0.001156300985f,
            -0.001162262550f, -0.001168221038f, -0.001174176211f, -0.001180127831f, -0.001186075658f,
            -0.001192019454f, -0.001197958979f, -0.001203893994f, -0.001209824258f, -0.001215749531f,
            -0.001221669572f, -0.001227584141f, -0.001233492996f, -0.001239395895f, -0.001245292598f,
            -0.001251182862f, -0.001257066445f, -0.001262943104f, -0.001268812598f, -0.001274674683f,
            -0.001280529117f, -0.001286375657f, -0.001292214059f, -0.001298044081f, -0.001303865480f,
            -0.001309678011f, -0.001315481433f, -0.001321275500f, -0.001327059971f, -0.001332834601f,
            -0.001338599146f, -0.001344353365f, -0.001350097012f, -0.001355829845f, -0.001361551621f,
            -0.001367262096f, -0.001372961027f, -0.001378648170f, -0.001384323284f, -0.001389986125f,
            -0.001395636451f, -0.001401274019f, -0.001406898587f, -0.001412509912f, -0.001418107754f,
            -0.001423691869f, -0.001429262017f, -0.001434817957f, -0.001440359447f, -0.001445886247f,
            -0.001451398117f, -0.001456894816f, -0.001462376106f, -0.001467841745f, -0.001473291497f,
            -0.001478725121f, -0.001484142380f, -0.001489543035f, -0.001494926850f, -0.001500293587f,
            -0.001505643010f, -0.001510974883f, -0.001516288970f, -0.001521585036f, -0.001526862846f,
            -0.001532122166f, -0.001537362763f, -0.001542584404f, -0.001547786856f, -0.001552969887f,
            -0.001558133266f, -0.001563276763f, -0.001568400147f, -0.001573503189f, -0.001578585660f,
            -0.001583647332f, -0.001588687977f, -0.001593707370f, -0.001598705284f, -0.001603681493f,
            -0.001608635773f, -0.001613567900f, -0.001618477651f, -0.001623364805f, -0.001628229139f,
            -0.001633070433f, -0.001637888466f, -0.001642683021f, -0.001647453879f, -0.001652200822f,
            -0.001656923635f, -0.001661622101f, -0.001666296006f, -0.001670945137f, -0.001675569280f,
            -0.001680168224f, -0.001684741759f, -0.001689289673f, -0.001693811759f, -0.001698307808f,
            -0.001702777614f, -0.001707220970f, -0.001711637672f, -0.001716027517f, -0.001720390301f,
            -0.001724725823f, -0.001729033883f, -0.001733314281f, -0.001737566818f, -0.001741791298f,
            -0.001745987525f, -0.001750155304f, -0.001754294441f, -0.001758404744f, -0.001762486021f,
            -0.001766538082f, -0.001770560739f, -0.001774553804f, -0.001778517091f, -0.001782450414f,
            -0.001786353589f, -0.001790226435f, -0.001794068769f, -0.001797880412f, -0.001801661185f,
            -0.001805410911f, -0.001809129414f, -0.001812816519f, -0.001816472052f, -0.001820095843f,
            -0.001823687720f, -0.001827247514f, -0.001830775059f, -0.001834270186f, -0.001837732732f,
            -0.001841162533f, -0.001844559427f, -0.001847923253f, -0.001851253853f, -0.001854551069f,
            -0.001857814745f, -0.001861044726f, -0.001864240859f, -0.001867402994f, -0.001870530978f,
            -0.001873624665f, -0.001876683908f, -0.001879708560f, -0.001882698479f, -0.001885653522f,
            -0.001888573548f, -0.001891458418f, -0.001894307996f, -0.001897122145f, -0.001899900731f,
            -0.001902643622f, -0.001905350686f, -0.001908021795f, -0.001910656821f, -0.001913255638f,
            -0.001915818121f, -0.001918344149f, -0.001920833601f, -0.001923286357f, -0.001925702300f,
            -0.001928081314f, -0.001930423286f, -0.001932728103f, -0.001934995654f, -0.001937225831f,
            -0.001939418527f, -0.001941573636f, -0.001943691056f, -0.001945770684f, -0.001947812420f,
            -0.001949816167f, -0.001951781828f, -0.001953709308f, -0.001955598515f, -0.001957449358f,
            -0.001959261748f, -0.001961035598f, -0.001962770821f, -0.001964467336f, -0.001966125059f,
            -0.001967743911f, -0.001969323814f, -0.001970864691f, -0.001972366469f, -0.001973829075f,
            -0.001975252439f, -0.001976636491f, -0.001977981165f, -0.001979286396f, -0.001980552120f,
            -0.001981778278f, -0.001982964809f, -0.001984111657f, -0.001985218765f, -0.001986286080f,
            -0.001987313551f, -0.001988301128f, -0.001989248764f, -0.001990156412f, -0.001991024028f,
            -0.001991851571f, -0.001992639000f, -0.001993386278f, -0.001994093367f, -0.001994760235f,
            -0.001995386849f, -0.001995973178f, -0.001996519194f, -0.001997024870f, -0.001997490183f,
            -0.001997915109f, -0.001998299628f, -0.001998643721f, -0.001998947372f, -0.001999210566f,
            -0.001999433290f, -0.001999615534f, -0.001999757288f, -0.001999858546f, -0.001999919303f,
            0.997969838110f, -0.001999919303f, -0.001999858546f, -0.001999757288f, -0.001999615534f,
            -0.001999433290f, -0.001999210566f, -0.001998947372f, -0.001998643721f, -0.001998299628f,
            -0.001997915109f, -0.001997490183f, -0.001997024870f, -0.001996519194f, -0.001995973178f,
            -0.001995386849f, -0.001994760235f, -0.001994093367f, -0.001993386278f, -0.001992639000f,
            -0.001991851571f, -0.001991024028f, -0.001990156412f, -0.001989248764f, -0.001988301128f,
            -0.001987313551f, -0.001986286080f, -0.001985218765f, -0.001984111657f, -0.001982964809f,
            -0.001981778278f, -0.001980552120f, -0.001979286396f, -0.001977981165f, -0.001976636491f,
            -0.001975252439f, -0.001973829075f, -0.001972366469f, -0.001970864691f, -0.001969323814f,
            -0.001967743911f, -0.001966125059f, -0.001964467336f, -0.001962770821f, -0.001961035598f,
            -0.001959261748f, -0.001957449358f, -0.001955598515f, -0.001953709308f, -0.001951781828f,
            -0.001949816167f, -0.001947812420f, -0.001945770684f, -0.001943691056f, -0.001941573636f,
            -0.001939418527f, -0.001937225831f, -0.001934995654f, -0.001932728103f, -0.001930423286f,
            -0.001928081314f, -0.001925702300f, -0.001923286357f, -0.001920833601f, -0.001918344149f,
            -0.001915818121f, -0.001913255638f, -0.001910656821f, -0.001908021795f, -0.001905350686f,
            -0.001902643622f, -0.001899900731f, -0.001897122145f, -0.001894307996f, -0.001891458418f,
            -0.001888573548f, -0.001885653522f, -0.001882698479f, -0.001879708560f, -0.001876683908f,
            -0.001873624665f, -0.001870530978f, -0.001867402994f, -0.001864240859f, -0.001861044726f,
            -0.001857814745f, -0.001854551069f, -0.001851253853f, -0.001847923253f, -0.001844559427f,
            -0.001841162533f, -0.001837732732f, -0.001834270186f, -0.001830775059f, -0.001827247514f,
            -0.001823687720f, -0.001820095843f, -0.001816472052f, -0.001812816519f, -0.001809129414f,
            -0.001805410911f, -0.001801661185f, -0.001797880412f, -0.001794068769f, -0.001790226435f,
            -0.001786353589f, -0.001782450414f, -0.001778517091f, -0.001774553804f, -0.001770560739f,
            -0.001766538082f, -0.001762486021f, -0.001758404744f, -0.001754294441f, -0.001750155304f,
            -0.001745987525f, -0.001741791298f, -0.001737566818f, -0.001733314281f, -0.001729033883f,
            -0.001724725823f, -0.001720390301f, -0.001716027517f, -0.001711637672f, -0.001707220970f,
            -0.001702777614f, -0.001698307808f, -0.001693811759f, -0.001689289673f, -0.001684741759f,
            -0.001680168224f, -0.001675569280f, -0.001670945137f, -0.001666296006f, -0.001661622101f,
            -0.001656923635f, -0.001652200822f, -0.001647453879f, -0.001642683021f, -0.001637888466f,
            -0.001633070433f, -0.001628229139f, -0.001623364805f, -0.001618477651f, -0.001613567900f,
            -0.001608635773f, -0.001603681493f, -0.001598705284f, -0.001593707370f, -0.001588687977f,
            -0.001583647332f, -0.001578585660f, -0.001573503189f, -0.001568400147f, -0.001563276763f,
            -0.001558133266f, -0.001552969887f, -0.001547786856f, -0.001542584404f, -0.001537362763f,
            -0.001532122166f, -0.001526862846f, -0.001521585036f, -0.001516288970f, -0.001510974883f,
            -0.001505643010f, -0.001500293587f, -0.001494926850f, -0.001489543035f, -0.001484142380f,
            -0.001478725121f, -0.001473291497f, -0.001467841745f, -0.001462376106f, -0.001456894816f,
            -0.001451398117f, -0.001445886247f, -0.001440359447f, -0.001434817957f, -0.001429262017f,
            -0.001423691869f, -0.001418107754f, -0.001412509912f, -0.001406898587f, -0.001401274019f,
            -0.001395636451f, -0.001389986125f, -0.001384323284f, -0.001378648170f, -0.001372961027f,
            -0.001367262096f, -0.001361551621f, -0.001355829845f, -0.001350097012f, -0.001344353365f,
            -0.001338599146f, -0.001332834601f, -0.001327059971f, -0.001321275500f, -0.001315481433f,
            -0.001309678011f, -0.001303865480f, -0.001298044081f, -0.001292214059f, -0.001286375657f,
            -0.001280529117f, -0.001274674683f, -0.001268812598f, -0.001262943104f, -0.001257066445f,
            -0.001251182862f, -0.001245292598f, -0.001239395895f, -0.001233492996f, -0.001227584141f,
            -0.001221669572f, -0.001215749531f, -0.001209824258f, -0.001203893994f, -0.001197958979f,
            -0.001192019454f, -0.001186075658f, -0.001180127831f, -0.001174176211f, -0.001168221038f,
            -0.001162262550f, -0.001156300985f, -0.001150336580f, -0.001144369572f, -0.001138400199f,
            -0.001132428696f, -0.001126455299f, -0.001120480244f, -0.001114503765f, -0.001108526098f,
            -0.001102547474f, -0.001096568129f, -0.001090588295f, -0.001084608203f, -0.001078628086f,
            -0.001072648175f, -0.001066668700f, -0.001060689891f, -0.001054711978f, -0.001048735188f,
            -0.001042759751f, -0.001036785893f, -0.001030813841f, -0.001024843820f, -0.001018876057f,
            -0.001012910776f, -0.001006948201f, -0.001000988555f, -0.000995032060f, -0.000989078937f,
            -0.000983129409f, -0.000977183694f, -0.000971242013f, -0.000965304583f, -0.000959371623f,
            -0.000953443349f, -0.000947519977f, -0.000941601722f, -0.000935688799f, -0.000929781421f,
            -0.000923879801f, -0.000917984150f, -0.000912094679f, -0.000906211597f, -0.000900335115f,
            -0.000894465439f, -0.000888602776f, -0.000882747333f, -0.000876899315f, -0.000871058925f,
            -0.000865226367f, -0.000859401843f, -0.000853585554f, -0.000847777700f, -0.000841978479f,
            -0.000836188091f, -0.000830406730f, -0.000824634595f, -0.000818871878f, -0.000813118774f,
            -0.000807375476f, -0.000801642174f, -0.000795919060f, -0.000790206322f, -0.000784504148f,
            -0.000778812725f, -0.000773132240f, -0.000767462876f, -0.000761804817f, -0.000756158245f,
            -0.000750523342f, -0.000744900287f, -0.000739289259f, -0.000733690435f, -0.000728103992f,
            -0.000722530105f, -0.000716968947f, -0.000711420691f, -0.000705885507f, -0.000700363567f,
            -0.000694855039f, -0.000689360090f, -0.000683878887f, -0.000678411594f, -0.000672958374f,
            -0.000667519391f, -0.000662094805f, -0.000656684776f, -0.000651289462f, -0.000645909020f,
            -0.000640543605f, -0.000635193373f, -0.000629858475f, -0.000624539064f, -0.000619235289f,
            -0.000613947300f, -0.000608675244f, -0.000603419267f, -0.000598179513f, -0.000592956127f,
            -0.000587749249f, -0.000582559021f, -0.000577385581f, -0.000572229067f, -0.000567089615f,
            -0.000561967361f, -0.000556862437f, -0.000551774975f, -0.000546705106f, -0.000541652959f,
            -0.000536618662f, -0.000531602340f, -0.000526604119f, -0.000521624121f, -0.000516662469f,
            -0.000511719282f, -0.000506794679f, -0.000501888779f, -0.000497001696f, -0.000492133545f,
            -0.000487284439f, -0.000482454490f, -0.000477643806f, -0.000472852498f, -0.000468080670f,
            -0.000463328430f, -0.000458595880f, -0.000453883124f, -0.000449190262f, -0.000444517393f,
            -0.000439864616f, -0.000435232027f, -0.000430619720f, -0.000426027788f, -0.000421456325f,
            -0.000416905419f, -0.000412375160f, -0.000407865635f, -0.000403376930f, -0.000398909128f,
            -0.000394462313f, -0.000390036565f, -0.000385631964f, -0.000381248589f, -0.000376886515f,
            -0.000372545818f, -0.000368226571f, -0.000363928846f, -0.000359652713f, -0.000355398242f,
            -0.000351165499f, -0.000346954551f, -0.000342765462f, -0.000338598294f, -0.000334453109f,
            -0.000330329966f, -0.000326228924f, -0.000322150039f, -0.000318093367f, -0.000314058960f,
            -0.000310046872f, -0.000306057152f, -0.000302089850f, -0.000298145012f, -0.000294222686f,
            -0.000290322916f, -0.000286445743f, -0.000282591211f, -0.000278759359f, -0.000274950225f,
            -0.000271163847f, -0.000267400259f, -0.000263659496f, -0.000259941591f, -0.000256246573f,
            -0.000252574473f, -0.000248925319f, -0.000245299136f, -0.000241695951f, -0.000238115786f,
            -0.000234558664f, -0.000231024605f, -0.000227513629f, -0.000224025752f, -0.000220560992f,
            -0.000217119363f, -0.000213700879f, -0.000210305550f, -0.000206933389f, -0.000203584402f,
            -0.000200258599f, -0.000196955986f, -0.000193676566f, -0.000190420343f, -0.000187187320f,
            -0.000183977496f, -0.000180790871f, -0.000177627441f, -0.000174487205f, -0.000171370156f,
            -0.000168276287f, -0.000165205592f, -0.000162158060f, -0.000159133682f, -0.000156132444f,
            -0.000153154335f, -0.000150199338f, -0.000147267438f, -0.000144358618f, -0.000141472859f,
            -0.000138610141f, -0.000135770442f, -0.000132953741f, -0.000130160012f, -0.000127389231f,
            -0.000124641371f, -0.000121916404f, -0.000119214301f, -0.000116535033f, -0.000113878566f,
            -0.000111244869f, -0.000108633907f, -0.000106045645f, -0.000103480045f, -0.000100937071f,
            -0.000098416683f, -0.000095918842f, -0.000093443504f, -0.000090990628f, -0.000088560171f,
            -0.000086152086f, -0.000083766327f, -0.000081402848f, -0.000079061599f, -0.000076742532f,
            -0.000074445594f, -0.000072170735f, -0.000069917901f, -0.000067687038f, -0.000065478090f,
            -0.000063291002f, -0.000061125715f, -0.000058982171f, -0.000056860311f, -0.000054760073f,
            -0.000052681397f, -0.000050624218f, -0.000048588474f, -0.000046574099f, -0.000044581028f,
            -0.000042609194f, -0.000040658528f, -0.000038728962f, -0.000036820426f, -0.000034932849f,
            -0.000033066159f, -0.000031220284f, -0.000029395150f, -0.000027590682f, -0.000025806805f,
            -0.000024043442f, -0.000022300515f, -0.000020577947f, -0.000018875658f, -0.000017193569f,
            -0.000015531598f, -0.000013889664f, -0.000012267683f, -0.000010665574f, -0.000009083250f,
            -0.000007520628f, -0.000005977621f, -0.000004454142f, -0.000002950105f, -0.000001465421f,
            -0.000000000000f, 0.000001446246f, 0.000002873409f, 0.000004281580f, 0.000005670850f,
            0.000007041313f, 0.000008393061f, 0.000009726189f, 0.000011040792f, 0.000012336964f,
            0.000013614803f, 0.000014874405f, 0.000016115867f, 0.000017339287f, 0.000018544764f,
            0.000019732398f, 0.000020902287f, 0.000022054533f, 0.000023189236f, 0.000024306498f,
            0.000025406422f, 0.000026489109f, 0.000027554664f, 0.000028603189f, 0.000029634789f,
            0.000030649570f, 0.000031647635f, 0.000032629091f, 0.000033594044f, 0.000034542600f,
            0.000035474867f, 0.000036390953f, 0.000037290965f, 0.000038175011f, 0.000039043201f,
            0.000039895643f, 0.000040732447f, 0.000041553723f, 0.000042359582f, 0.000043150134f,
            0.000043925489f, 0.000044685760f, 0.000045431058f, 0.000046161494f, 0.000046877182f,
            0.000047578233f, 0.000048264761f, 0.000048936878f, 0.000049594699f, 0.000050238335f,
            0.000050867902f, 0.000051483513f, 0.000052085282f, 0.000052673324f, 0.000053247754f,
            0.000053808686f, 0.000054356235f, 0.000054890517f, 0.000055411647f, 0.000055919739f,
            0.000056414911f, 0.000056897276f, 0.000057366952f, 0.000057824054f, 0.000058268698f,
            0.000058701000f, 0.000059121076f, 0.000059529042f, 0.000059925015f, 0.000060309110f,
            0.000060681445f, 0.000061042134f, 0.000061391295f, 0.000061729044f, 0.000062055497f,
            0.000062370770f, 0.000062674980f, 0.000062968242f, 0.000063250673f, 0.000063522389f,
            0.000063783505f, 0.000064034138f, 0.000064274403f, 0.000064504416f, 0.000064724293f,
            0.000064934149f, 0.000065134099f, 0.000065324258f, 0.000065504741f, 0.000065675664f,
            0.000065837141f, 0.000065989286f, 0.000066132214f, 0.000066266038f, 0.000066390873f,
            0.000066506833f, 0.000066614030f, 0.000066712578f, 0.000066802590f, 0.000066884178f,
            0.000066957456f, 0.000067022534f, 0.000067079526f, 0.000067128542f, 0.000067169695f,
            0.000067203094f, 0.000067228851f, 0.000067247076f, 0.000067257879f, 0.000067261369f,
            0.000067257657f, 0.000067246851f, 0.000067229059f, 0.000067204390f, 0.000067172952f,
            0.000067134851f, 0.000067090197f, 0.000067039093f, 0.000066981648f, 0.000066917967f,
            0.000066848156f, 0.000066772318f, 0.000066690560f, 0.000066602984f, 0.000066509695f,
            0.000066410795f, 0.000066306388f, 0.000066196575f, 0.000066081458f, 0.000065961139f,
            0.000065835718f, 0.000065705295f, 0.000065569969f, 0.000065429841f, 0.000065285009f,
            0.000065135571f, 0.000064981623f, 0.000064823265f, 0.000064660590f, 0.000064493697f,
            0.000064322680f, 0.000064147633f, 0.000063968651f, 0.000063785827f, 0.000063599255f,
            0.000063409027f, 0.000063215234f, 0.000063017968f, 0.000062817319f, 0.000062613377f,
            0.000062406232f, 0.000062195972f, 0.000061982685f, 0.000061766459f, 0.000061547380f,
            0.000061325534f, 0.000061101007f, 0.000060873884f, 0.000060644249f, 0.000060412184f,
            0.000060177774f, 0.000059941099f, 0.000059702242f, 0.000059461283f, 0.000059218301f,
            0.000058973377f, 0.000058726589f, 0.000058478015f, 0.000058227731f, 0.000057975815f,
            0.000057722343f, 0.000057467388f, 0.000057211026f, 0.000056953329f, 0.000056694372f,
            0.000056434225f, 0.000056172961f, 0.000055910650f, 0.000055647361f, 0.000055383164f,
            0.000055118127f, 0.000054852318f, 0.000054585804f, 0.000054318650f, 0.000054050922f,
            0.000053782684f, 0.000053514001f, 0.000053244935f, 0.000052975549f, 0.000052705903f,
            0.000052436059f, 0.000052166077f, 0.000051896015f, 0.000051625932f, 0.000051355885f,
            0.000051085931f, 0.000050816127f, 0.000050546527f, 0.000050277185f, 0.000050008156f,
            0.000049739491f, 0.000049471244f, 0.000049203464f, 0.000048936204f, 0.000048669511f,
            0.000048403435f, 0.000048138024f, 0.000047873325f, 0.000047609384f, 0.000047346247f,
            0.000047083960f, 0.000046822564f, 0.000046562105f, 0.000046302624f, 0.000046044162f,
            0.000045786761f, 0.000045530460f, 0.000045275299f, 0.000045021316f, 0.000044768548f,
            0.000044517032f, 0.000044266804f, 0.000044017900f, 0.000043770352f, 0.000043524196f,
            0.000043279463f, 0.000043036186f, 0.000042794395f, 0.000042554121f, 0.000042315394f,
            0.000042078242f, 0.000041842692f, 0.000041608773f, 0.000041376511f, 0.000041145930f,
            0.000040917057f, 0.000040689914f, 0.000040464525f, 0.000040240912f, 0.000040019098f,
            0.000039799102f, 0.000039580945f, 0.000039364646f, 0.000039150223f, 0.000038937695f,
            0.000038727079f, 0.000038518390f, 0.000038311644f, 0.000038106855f, 0.000037904038f,
            0.000037703206f, 0.000037504371f, 0.000037307545f, 0.000037112738f, 0.000036919961f,
            0.000036729224f, 0.000036540534f, 0.000036353900f, 0.000036169330f, 0.000035986829f,
            0.000035806404f, 0.000035628059f, 0.000035451798f, 0.000035277626f, 0.000035105546f,
            0.000034935559f, 0.000034767666f, 0.000034601870f, 0.000034438169f, 0.000034276564f,
            0.000034117053f, 0.000033959633f, 0.000033804303f, 0.000033651059f, 0.000033499897f,
            0.000033350813f, 0.000033203800f, 0.000033058854f, 0.000032915967f, 0.000032775133f,
            0.000032636342f, 0.000032499588f, 0.000032364859f, 0.000032232148f, 0.000032101443f,
            0.000031972733f, 0.000031846007f, 0.000031721252f, 0.000031598455f, 0.000031477603f,
            0.000031358682f, 0.000031241677f, 0.000031126573f, 0.000031013354f, 0.000030902004f,
            0.000030792505f, 0.000030684841f, 0.000030578992f, 0.000030474941f, 0.000030372668f,
            0.000030272154f
    };

}
