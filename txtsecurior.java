package post;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public class txtsecurior {
    public static void main(String[] args) {
        String folderPath = "";  //자신의 경로를 쑤시오
        String passwordFilePath = ""; //파일의 위치를 쓰시오
        // 폴더 비밀번호 설정
        setFolderPassword(folderPath, passwordFilePath);

        // 폴더 열기
        if (checkPassword(passwordFilePath)) {
            System.out.println("폴더 열기 성공!");
            // 여기에서 보안 폴더 내의 파일 및 디렉토리를 조작할 수 있습니다.
        } else {
            System.out.println("비밀번호가 올바르지 않습니다. 폴더를 열 수 없습니다.");
        }
    }

    private static void setFolderPassword(String folderPath, String passwordFilePath) {
        File folder = new File(folderPath);
        File passwordFile = new File(passwordFilePath);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        if (!passwordFile.exists()) {
            try {
                FileWriter writer = new FileWriter(passwordFile);
                System.out.print("새로운 폴더 비밀번호를 설정하세요: ");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.nextLine();
                writer.write(password);
                writer.close();
                System.out.println("폴더 비밀번호가 설정되었습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("이미 비밀번호가 설정되어 있습니다.");
        }
    }

    private static boolean checkPassword(String passwordFilePath) {
        try {
            String storedPassword = Files.readString(new File(passwordFilePath).toPath(), StandardCharsets.UTF_8);
            System.out.print("비밀번호를 입력하세요: ");
            Scanner scanner = new Scanner(System.in);
            String enteredPassword = scanner.nextLine();
            return storedPassword.equals(enteredPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
