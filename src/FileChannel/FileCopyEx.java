package FileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class FileCopyEx {
    public static void main(String[] args) throws IOException {
        String filePathFrom = "src/leaves-g09fea5b4e_1920.jpg";
        String filePathTo = "C:/Users/hbcho/Desktop/pictureCopy.jpg";
        Path from = Paths.get(filePathFrom);
        Path to = Paths.get(filePathTo);

        // 입력용 FileChannel 열기
        FileChannel fileChannelFrom = FileChannel.open(from, StandardOpenOption.READ);

        // 출력용 FileChannel 열기
        FileChannel fileChannelTo = FileChannel.open(to,StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        
        // 다이렉트 ByteBuffer를 이용하여 데이터 입출력
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        while (true) {
            int byteCount = fileChannelFrom.read(buffer);
            if(byteCount == -1)
                break;
            buffer.flip();
            fileChannelTo.write(buffer);
            buffer.clear();
        }
        System.out.println("파일 복사 성공");

        /**
         * 단순 파일을 복사만 하는 것이 목적이라면 Files.copy()를 사용하는 것이 더 편하다.
         * REPLACE_EXISTING : 타겟 파일이 존재하면 대체
         * COPY_ATTRIBUTES : 파일 속성까지 복사
         * NOFOLLOW_LINKS : 링크 파일일 경우 링크 파일만 복사하고 링크된 파일은 복사하지 않는다.
         */

        String filePathFrom2 = "src/leaves-g09fea5b4e_1920.jpg";
        String filePathTo2 = "C:/Users/hbcho/Desktop/pictureCopy2.jpg";
        Path from2 = Paths.get(filePathFrom2);
        Path to2 = Paths.get(filePathTo2);

        Files.copy(from2, to2, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("파일 복사 성공");

        fileChannelFrom.close();
        fileChannelTo.close();
    }
}
