package File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class PathEx {

    /**
     * Path는 java에서 File에 대응하는 NIO인터페이스이다.
     * 파일 경로를 지정할 때 사용
     */
    public static void main(String[] args) {
        Path path = Paths.get("src/NIO.md");
//        Path path = Paths.get("src/File/PathEx.java");

        System.out.println("[파일명] " + path.getFileName());
        System.out.println("[부모 디렉토리] " + path.getParent().getFileName());
        System.out.println("[중첩 경로 수] " +  path.getNameCount());

        System.out.println();
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("path.getName(" + i + ") : " + path.getName(i));
        }

        System.out.println();
        Iterator<Path> iter = path.iterator();
        while (iter.hasNext()) {
            Path temp = iter.next();
            System.out.println("temp : " + temp.getFileName());
        }

    }
    

}
