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

public class AsynchronousFileChannelReadEx {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        String filePath = "/Users/hyunbincho/Desktop/test";
        String ext = ".txt";

        for (int i = 0; i < 10; i++) {
            Path path = Paths.get(filePath + i + ext);

            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, EnumSet.of(StandardOpenOption.READ), executorService);
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());

            class Attachment{
                Path path;
                AsynchronousFileChannel fileChannel;
                ByteBuffer byteBuffer;
            }

            Attachment attachment = new Attachment();
            attachment.path = path;
            attachment.fileChannel = fileChannel;
            attachment.byteBuffer = byteBuffer;

            CompletionHandler<Integer, Attachment> completionHandler = new CompletionHandler<Integer, Attachment>() {
                @Override
                public void completed(Integer result, Attachment attachment) {
                    attachment.byteBuffer.flip();

                    Charset charset = Charset.forName("UTF-8");
                    String data = charset.decode(attachment.byteBuffer).toString();

                    System.out.println(attachment.path.getFileName() + "\n -> " + data +  "\n\t\t -> " + Thread.currentThread().getName());

                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failed(Throwable exc, Attachment attachment) {
                    exc.printStackTrace();
                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            fileChannel.read(byteBuffer, 0, attachment, completionHandler);
        }

        executorService.shutdown();
    }
}
