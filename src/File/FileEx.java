package File;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEx {
    /**
     * File은 파일과 디렉토리 생성, 삭제와 속성을 읽는 메서드를 제공한다.
     */

    public static void main(String[] args) throws IOException {
        Path path1 = Paths.get("src/File/FileSystemEx.java");

        System.out.println("[디렉토리 여부] " + Files.isDirectory(path1));
        System.out.println("[파일 여부] " + Files.isRegularFile(path1));
        System.out.println("[마지막 수정 시간] " + Files.getLastModifiedTime(path1));
        System.out.println("[파일 크기] " + Files.size(path1));
        System.out.println("[소유자] " + Files.getOwner(path1));
        System.out.println("[숨김 파일 여부] " + Files.isHidden(path1));
        System.out.println("[읽기 가능 여부] " + Files.isReadable(path1));
        System.out.println("[쓰기 가능 여부] " + Files.isWritable(path1));
        
        // 디렉토리 내용 출력을 위한 디렉토리 및 파일 생성
        // Desktop
        Path path2 = Paths.get("C:/Users/hbcho/Desktop/test1.txt");
        if (Files.notExists(path2)) {
            Files.createFile(path2);
        }

        // testDir
        path2 = Paths.get("C:/Users/hbcho/Desktop/testDir");
        if (Files.notExists(path2)) {
            Files.createDirectories(path2);
        }
        path2 = Paths.get("C:/Users/hbcho/Desktop/testDir/test2.txt");
        if (Files.notExists(path2)) {
            Files.createFile(path2);
        }

        // testDir/testDir2
        path2 = Paths.get("C:/Users/hbcho/Desktop/testDir/testDir2");
        if (Files.notExists(path2)) {
            Files.createDirectories(path2);
        }
        path2 = Paths.get("C:/Users/hbcho/Desktop/testDir/testDir2/test3.txt");
        if (Files.notExists(path2)) {
            Files.createFile(path2);
        }

        System.out.println("=======================================================");
        path2 = Paths.get("C:/Users/hbcho/Desktop/java_ch16");
        printDirectoryContent(path2, 0);
    }

    // 디렉토리 내용 출력
    public static void printDirectoryContent(Path path, int indent) {
        try{
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            directoryStream.forEach(p -> {
                if (!Files.isDirectory(p)) {
                    // 들여쓰기
                    for (int i = 0; i < indent; i++) {
                        System.out.print("\t");
                    }
                    // 파일 이름과 크기 출력
                    try{
                        System.out.println(p.getFileName() + "(크기 : " + Files.size(p) + ")");
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
            });

            // 디렉토리만 출력
            directoryStream = Files.newDirectoryStream(path);
            directoryStream.forEach(p -> {
                if (Files.isDirectory(p)) {
                    for (int i = 0; i < indent; i++) {
                        System.out.print("\t");
                    }
                    System.out.println("[" + p.getFileName() + "]");
                    printDirectoryContent(p, indent+1);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
