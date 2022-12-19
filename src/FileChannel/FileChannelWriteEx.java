package FileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelWriteEx {

    /**
     * NIO에서 제공하는 FileChannel을 이용하면 파일을 읽고 쓸 수 있다.
     * FileChannel은 동기화 처리되어 있음(-> 멀티 스레드 환경에서 사용해도 안전)
     *
     * 정적 메소드인 open()을 통해 얻을수도 있고
     * IO의 FileInputStream, FileOutputStream의 getChannel()을 통해서도 얻을 수 있다.
     *
     * FileChannel을 사용하지 않을 때는 close()해줘야 한다.
     *
     * StandardOpenOption
     * - READ : 읽기용
     * - WRITE : 쓰기용
     * - CREATE : 파일이 없으면 새 파일 생성
     * - CREATE_NEW : 새 파일 생성(파일이 있는 경우 예외 발생)
     * - APPEND : 파일 끝에 데이터를 추가
     * - DELETE_ON_CLOSE : 스트림을 닫을 때 파일을 삭제
     * - TRUNCATE_EXISTING : 파일을 0바이트로 잘라냄
     */
    public static void main(String[] args) throws IOException {

        // 경로와 파일 디렉토리 생성
        String filePath = "C:/Users/hbcho/Desktop/fileNio.txt";
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());

        // FileChannel 열기
        FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        // 문자열을 ByteBuffer로 변환
        String data = "Hello World!!\n Nice to meet you!!";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(data);

        // FileChannel을 통해서 ByteBuffer 출력하기
        int byteCount = fileChannel.write(byteBuffer);
        System.out.println("fileNio.txt : " + byteCount + "bytes written");

        // FileChannel 닫기
        fileChannel.close();

    }

}
