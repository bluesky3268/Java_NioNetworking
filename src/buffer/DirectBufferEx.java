package buffer;

import java.nio.ByteBuffer;

public class DirectBufferEx {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        System.out.println(buffer);
    }
}
