package FileChannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *  파일 입출력을 하는 동안 FileChannel의 read(), write()는 블로킹 됨
 *  따라서 별도의 작업 쓰레드를 생성해서 이 메소드들 호출해야 한다.
 *  NIO는 비동기 파일 채널을 제공함
 *  -> read(), write()를 호출하면 쓰레드 풀에게 입출력 처리를 요청하고 바로 리턴된다는 특징을 가진다.
 *  입출력 처리는 쓰레드 풀의 작업 메소드가 담당함
 *  -> 작업 쓰레드가 입출력을 완료하면 콜백 메서드를 자동으로 호출함
 *  -> 따라서 입출력 완료 후 실행되어야 할 코드는 콜백 메서드에 작성하면 됨
 */
public class AsynchronousFileChannelWriteEx {
    public static void main(String[] args) throws IOException {
        // 쓰레드 풀 생성
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        String filePath = "/Users/hyunbincho/Desktop/test";
        String ext = ".txt";
        String message1 = "안녕하세요 ";
        String message2 = "반갑습니다. ";

        for (int i = 0; i < 10; i++) {
            Path path = Paths.get(filePath + i + ext);

            // 비동기 파일 채널 생성
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE), executorService);

            Charset charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = charset.encode(message1 + i + "\n" + message2 + i);


            // 첨부 객체 생성
            class Attachment{
                Path path;
                AsynchronousFileChannel fileChannel;
            }

            Attachment attachment = new Attachment();
            attachment.path = path;
            attachment.fileChannel = fileChannel;

            // completionHandler 객체 생성
            CompletionHandler<Integer, Attachment> completionHandler = new CompletionHandler<Integer, Attachment>() {
                @Override
                public void completed(Integer result, Attachment attachment) {
                    System.out.println(attachment.path.getFileName() + " : " + result + "bytes written" + Thread.currentThread().getName());
                    try {
                        attachment.fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Attachment attachment) {
                    exc.printStackTrace();
                    try {
                        attachment.fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            fileChannel.write(byteBuffer, 0, attachment, completionHandler);
        }

        executorService.shutdown();
    }
}
