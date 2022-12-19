package buffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BufferMethodEx {

    public static void main(String[] args) {
        // 버퍼 생성
        ByteBuffer buffer = ByteBuffer.allocateDirect(7);
        printState(buffer);

        buffer.put((byte) 10);
        buffer.put((byte) 11);

        System.out.println("=== 2바이트 저장 ===");
        printState(buffer);

        buffer.put((byte) 12);
        buffer.put((byte) 13);
        buffer.put((byte) 14);

        String a = "A";
        byte[] bytes = a.getBytes(Charset.forName("UTF-8"));
        for (int i = 0; i < bytes.length; i++) {
            buffer.put(bytes[i]);
        }

        System.out.println("=== 5바이트 저장 ===");
        printState(buffer);

        System.out.println("=== flip() 실행 ===");
        buffer.flip();
        printState(buffer);

        System.out.println("=== 3바이트 읽기 ===");
        buffer.get(new byte[3]);
//        System.out.println("3바이트 읽은 후 4번째 값은 : " + buffer.get());
//        for (int i = 0; i < 2; i++) {
//            System.out.println(buffer.get());
//        }
        printState(buffer);

        System.out.println("=== mark() ===");
        buffer.mark();

        System.out.println("=== 2바이트 읽기 ===");
        buffer.get(new byte[2]);
        printState(buffer);

        System.out.println("=== reset() ===");
        buffer.reset();
        printState(buffer);

        System.out.println("=== rewind() ===");
        buffer.rewind();
        printState(buffer);

        System.out.println("=== clear() ===");
        buffer.clear();
        printState(buffer);

    }

    public static void printState(ByteBuffer buffer) {
        System.out.println("\tposition : " + buffer.position());
        System.out.println("\tlimit : " + buffer.limit());
        System.out.println("\tcapacity : " + buffer.capacity());
    }

}
