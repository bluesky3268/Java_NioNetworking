package buffer.convert;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ByteBufferToStringEx {

    /**
     * 채널을 통해 데이터를 입출력할 때는 반드시 ByteBuffer를 사용한다.
     * 따라서 문자열이나 배열을 채널을 통해 출력하려면 ByteBuffer로 변환해야 한다.
     * 그리고 채널을 통해 읽은 ByteBuffer를 원래 타입으로 변환하여 사용해야 한다.
     */

    public static void main(String[] args) {

        Charset charset = Charset.forName("UTF-8");
//        Charset charset = Charset.defaultCharset(); // 운영체제의 기본 문자셋으로 인코딩, 디코딩

        String data = "Hello World!!!";

        // 문자열 -> ByteBuffer
        ByteBuffer byteBuffer = charset.encode(data);
        System.out.println(byteBuffer);

        // ByteBuffer -> 문자열
        String result = charset.decode(byteBuffer).toString();
        System.out.println(result);

    }

}
