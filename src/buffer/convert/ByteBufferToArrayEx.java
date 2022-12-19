package buffer.convert;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class ByteBufferToArrayEx {
    public static void main(String[] args) {
        // ByteBuffer -> byte[]
        // ByteBuffer에 데이터 저장
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(3);
        byte b1 = 10;
        byteBuffer1.put(b1);
        byte b2 = 20;
        byteBuffer1.put(b2);
        
        // position = 0, limit = 마지막 데이터 다음 위치에 있을 때 ByteBuffer를 배열로 변환하는 방법
        byteBuffer1.flip();
        System.out.print(byteBuffer1 + " -> ");

        // ByteBuffer의 limit까지 byte[]를 얻기 위해서 limit 길이만큼 byte[] 생성 후 get()을 통해 읽은 byte값을 배열에 저장
        byte[] byteArr = new byte[byteBuffer1.limit()];
        byteBuffer1.get(byteArr);
        System.out.println(Arrays.toString(byteArr));

        // ByteBuffer -> int[]
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(16);
        byteBuffer2.putInt(10);
        byteBuffer2.putInt(20);
        byteBuffer2.flip();
        System.out.print(byteBuffer2 + " -> ");

        IntBuffer intBuffer = byteBuffer2.asIntBuffer();
        int[] intArr = new int[intBuffer.capacity()];
        intBuffer.get(intArr);
        System.out.println(Arrays.toString(intArr));

        //ByteBuffer -> double[]
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(24);
        byteBuffer3.putDouble(10.0);
        byteBuffer3.putDouble(20.0);
        byteBuffer3.flip();
        System.out.print(byteBuffer3 + " -> ");

        DoubleBuffer doubleBuffer = byteBuffer3.asDoubleBuffer();
        double[] doubleArray = new double[doubleBuffer.capacity()];
        doubleBuffer.get(doubleArray);
        System.out.println(Arrays.toString(doubleArray));

    }
}
