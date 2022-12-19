package buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

public class NonDirectBufferEx {

    /**
     * 버퍼의 위치 속성
     * capacity : 버퍼가 최대 저장할 수 있는 데이터 수 
     * limit : 읽거나 쓸 수 있는 한계(생성 시 capacity와 같은 값을 가짐)
     * position : 현재 읽거나 쓰는 위치(position == limit 면 더이상 데이터를 읽거나 쓸 수 없음)
     * mark : reset()이 호출되었을 때 다시 되돌아올 위치(position이나 limit가 mark보다 작은 경우 mark 자동제거)
     * 0 <= mark <= position <= limit <= capacity
     *
     * 저장이 완료된 후 버퍼에 저장된 바이트를 읽기 위해서는 flip()을 먼저 호출해야 한다.
     * flip() : limit를 현재 position으로, position을 0으로 이동
     */

    public static void main(String[] args) {
        // 논다이렉트 byte 버퍼 생성
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        System.out.println(buffer1);
        System.out.println();

        // 논다이렉트 int 버퍼 생성
        IntBuffer buffer2 = IntBuffer.allocate(100);
        System.out.println(buffer2);
        System.out.println();

        // 배열을 래핑하여 논다이렉트 byte버퍼 생성
        byte[] byteArr = new byte[10];
        byteArr[0] = 10;
        byteArr[1] = 20;
//        byte[] byteArr = {10, 20};

        ByteBuffer buffer3 = ByteBuffer.wrap(byteArr);
        System.out.println(buffer3 + ", ");

        // 래핑된 배열 가져와서 출력
        System.out.println(Arrays.toString(buffer3.array()));
        System.out.println();

        int length = 2;
        ByteBuffer buffer4 = ByteBuffer.wrap(byteArr, 0, length);
        System.out.println("buffer4");
//        buffer4.flip();
        System.out.println(buffer4);
        for (int i = 0; i < 2; i++) { // i < 3 하면 IndexOutOfBoundsException 발생함
            System.out.println(buffer4.get(i));
        }


        // 배열을 래핑하여 논다이렉트 char[] 생성
        String java = "This is java";
        char[] charArr = java.toCharArray();
        CharBuffer buffer5 = CharBuffer.wrap(charArr);
        System.out.println(buffer5);

        // 래핑된 배열 가져와서 출력
        System.out.println(Arrays.toString(buffer5.array()));
    }
}
