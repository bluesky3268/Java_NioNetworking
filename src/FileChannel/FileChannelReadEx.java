package FileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelReadEx {
    public static void main(String[] args) throws IOException {
        String filePath = "C:/Users/hbcho/Desktop/fileNio.txt";
        Path path = Paths.get(filePath);

        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ);

        // 읽은 바이트가 저장될 ByteBuffer 생성
        ByteBuffer byteBuffer = ByteBuffer.allocate(3);

        // FileChannel로부터 데이터 입력 받기
        Charset charset = Charset.forName("UTF-8");
        String data = "";
        while (true) {
            int byteNum = fileChannel.read(byteBuffer);
            if (byteNum == -1)
                break;
            byteBuffer.flip();
            String str = charset.decode(byteBuffer).toString();
            System.out.println("str : " + str);
            data += str;
            byteBuffer.clear();
        }

        fileChannel.close();

        System.out.println("input data : " + data);

    }
}
