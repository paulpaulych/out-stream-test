
//Выполните ревью кода. Найдите логические ошибки. Прокомментируйте с различных точек зрения: архитектура, организация кода, нейминг, и т.д.
//Можете ли вы предложить более оптимальное решение с точки зрения быстродействия / ресурсов?
//
//Можно оставлять комментарии прямо в тексте, можно написать заключение отдельно, можно также править код или писать новый
//

package test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Преобразовать файл так, чтобы выходной файл содержал только цифровые символы из исходного
 */
public class Main {

    public static void main(String[] args) throws IOException {
        StringBuffer result = new StringBuffer();
        var path = Path.of("test.txt");

        BufferedReader r = null;
        try {
            try {
                r = new BufferedReader(new FileReader(path.toFile()));
            } catch (FileNotFoundException e) {
            }

            String l;
            while ((l = r.readLine()) != null) {
                Main.readLine(result, l);
            }
        } catch (IOException e) {
            throw new WrongFileFormatException();
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                }
            }
        }

        Files.writeString(Path.of("result.txt"), result.toString());
    }

    private static boolean readLine(StringBuffer accum, String s) {
        Optional<Character> chr = null;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) > new Character('0')) || (s.charAt(i) < new Character('9'))) {
                chr = Optional.ofNullable(s.charAt(i));
            }
        }
        if (chr != null) {
            accum.append(chr.get());
        }
        return false;
    }

    public static class WrongFileFormatException extends RuntimeException {
        public String code;

        public WrongFileFormatException() {
            super("File of a wrong format");
        }
    }
}
