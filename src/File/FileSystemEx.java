package File;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemEx {
    /**
     * FileSystem은 운영체제의 파일시스템에 접근할 수 있는 인터페이스이다.
     * FileSystem의 구현체는 FileSystems이다. -> FileSystems.getDefault()
     */

    public static void main(String[] args) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();
        for (FileStore fileStore : fileSystem.getFileStores()) {
            System.out.println("[드라이버명] " + fileStore.name());
            System.out.println("[파일시스템] " + fileStore.type());
            System.out.println("[전체 공간] " + fileStore.getTotalSpace() + "바이트");
            System.out.println("[사용 중인 공간] \t" + (fileStore.getTotalSpace() - fileStore.getUnallocatedSpace()) + "바이트");
            System.out.println("[사용가능한 공간] " + fileStore.getUsableSpace() + "바이트");
        }

        System.out.println("[파일구분자] " + fileSystem.getSeparator());
        for (Path path : fileSystem.getRootDirectories()) {
            System.out.println(path.toString());
        }
    }
}
