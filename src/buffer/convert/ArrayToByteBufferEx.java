package buffer.convert;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class ArrayToByteBufferEx {

    /**
     * byte[] 를 ByteBuffer로 변환할 때는 wrap()을 사용하여 간단하게 변환할 수 있다.
     * int[] 를 ByteBuffer로 변환할 때는 int[] 보다 4배 큰 capacity를 가진 ByteBuffer가 필요하다 (int는 4byte니까)
     *  -> asIntBuffer()는 IntBuffer뷰를 리턴함
     *
     */

    public static void main(String[] args) {
        // byte[] -> ByteBuffer
        byte[] byteArr = {10, 20};
        ByteBuffer byteBuffer = ByteBuffer.wrap(byteArr);
        System.out.println(byteBuffer);

        // int[] -> ByteBuffer
        int[] intArr = {10, 20};
        ByteBuffer intTypeByteBuffer = ByteBuffer.allocate(intArr.length * 4);
        intTypeByteBuffer.asIntBuffer().put(intArr);
        System.out.println(intTypeByteBuffer);

        // double[] -> ByteBuffer
        double[] doubleArr = {10.9, 20.5};
        ByteBuffer doubleTypeByteBuffer = ByteBuffer.allocate(doubleArr.length * 8);
        doubleTypeByteBuffer.asDoubleBuffer().put(doubleArr);
        System.out.println(doubleTypeByteBuffer);

    }

}
