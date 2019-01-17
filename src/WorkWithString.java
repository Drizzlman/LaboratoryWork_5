import java.io.*;
import java.util.StringTokenizer;

public class WorkWithString {

    String data;
    String choice;
    String choice2;
    RandomAccessFile fio;
    BufferedReader in;

    public WorkWithString() throws UnsupportedEncodingException {
        this.in = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));//создание объекта класса BufferedReader с возможностью ввода данных на русском языке(кодировка "Cp1251")
    }

    public void runConsol() throws IOException {
        while (true) {
            printMenu();//вызов метода вывода меню на экран
            choice = in.readLine();//ввод выбранного пункта меню
            if (choice.compareTo("1") == 0) {//сравнение выбранного пользователем пункта меню // прочитать текст!
                textIntoFile();//вызов метода ввода текста и записи его в файл .... прочитать файл
            } else if (choice.compareTo("2") == 0) {
                printRedactMenu();//вызов метода вывода возможных действий редактирования текста на экран // оставляем
                choice2 = in.readLine();//ввод выбранного пункта
                if (choice2.compareTo("1") == 0) {
                    addStart();//вызов метода добавления текста в начало файла
                } else if (choice2.compareTo("2") == 0) {
                    addEnd();//вызов метода добавления текста в конец файла
                } else if (choice2.compareTo("3") == 0) {
                    addRandom();//вызов метода добавления текста в указаную позицию в файле
                }
            } else if (choice.compareTo("3") == 0) {
               textFromfile();// найти максим длинное
            } else if (choice.compareTo("4") == 0) {
                return;//выход из программы
            }
        }
    }

    public void printMenu() {//метод вывода меню на экран
        System.out.println("Enter your choice:");
        System.out.println("1.Show text;"); //Ввести и записать его в файл
        System.out.println("2.Edit text in file;");
        System.out.println("3.Show word with max length in the text;");
        System.out.println("4.Exit");
    }

    public void textIntoFile() throws IOException {//метод ввода текста и записи его в файл

        fio = new RandomAccessFile(new File("E:\\LaboratoryWork_5\\src\\file.txt"), "r");
        data = fio.readLine();//чтение информации из заданного файла
        fio.close();
        System.out.println("Information in the file: " + data);//вывод информации из файла

    }

    public void printRedactMenu() {//метод вывода возможных действий редактирования текста на экран
        System.out.println("Enter your choice:");
        System.out.println("1 - Add text in the beginning of file (добавление текста в начало файла);");
        System.out.println("2 - Add text in the end of file (добавление текста в конец файла);");
        System.out.println("3 - Add text in a different position of file (добавление текста в произвольную позицию в файле);");
    }

    public void addStart() throws IOException {//метод добавления текста в начало файла
        fio = new RandomAccessFile(new File("E:\\LaboratoryWork_5\\src\\file.txt"), "rw");
        data = fio.readLine();//чтение информации из заданного файла
        System.out.println("Enter string for add in the beginning:");//Введите строку для добавления в начало:
        String s;
        s = in.readLine();//ввод строки
        fio.seek(0);//переход в начало файла
        fio.writeBytes(s);//запись введенной строки
        fio.seek(s.length());//переход в конец записанной строки
        fio.writeBytes(data);//запись исходного текста после введенной строки
        fio.close();
        System.out.println("String saved in the beginning of file.");//Cтрока записана в начало файла.
    }

    public void addEnd() throws IOException {//метод добавления текста в конец файла
        fio = new RandomAccessFile(new File("E:\\LaboratoryWork_5\\src\\file.txt"), "rw");
        data = fio.readLine();//чтение информации из заданного файла
        System.out.println("Enter string for add in the beginning:");//Введите строку для добавления в конец
        String s;
        s = in.readLine();//ввод строки
        fio.seek(fio.length());//переход в конец файла
        fio.writeBytes(s);//запись введенной строки в конец файла
        fio.close();
        System.out.println("String saved in the beginning of file.");//Cтрока записана в конец файла
    }

    public void addRandom() throws IOException {//метод добавления текста в указаную позицию в файле
        fio = new RandomAccessFile(new File("E:\\LaboratoryWork_5\\src\\file.txt"), "rw");
        System.out.println("Enter string for add in the different position of file:");//Введите строку для добавления в указанную позицию в файле:
        String s;
        s = in.readLine();//ввод строки
        System.out.println("Enter needing position in the file:");//Введите необходимую позицию в файле:
        int n;
        n = Integer.parseInt(in.readLine());//ввод позиции
        fio.seek(n);//смещение на n позицию в файле
        data = fio.readLine();//чтение файла начиная с позиции n
        fio.seek(n);
        fio.writeBytes(s);//запись введенной строки с позиции n
        fio.writeBytes(data);//запись прочитанного с позиции n текста после введенной строки
        fio.close();
        System.out.println("String saved in the file.");//Cтрока записана в файл.
    }

   public void textFromfile() throws IOException {
    fio = new RandomAccessFile(new File("E:\\LaboratoryWork_5\\src\\file.txt"), "r");
    data = fio.readLine();
    fio.close();
       StringTokenizer st = new StringTokenizer(data);
       int maxLengthOfWord = 0;
       String mytext = "";
       while (st.hasMoreTokens()) {
           String word = st.nextToken();
           int wordLength = word.length();
          // System.out.println("Token: '" + word + "' with length = " + wordLength);
           if ( maxLengthOfWord == 0) {
               mytext = word;
               maxLengthOfWord = wordLength;
           } else if (wordLength > maxLengthOfWord) {
               maxLengthOfWord = wordLength;
               mytext = word;
           }
       }
       if ( maxLengthOfWord > 0) {
           System.out.println("Max length of word = " + maxLengthOfWord);
           System.out.println("Max  word = " + mytext);
       } else {
           System.out.println("No word in string!");
       }
       }


    public static void main(String args[]) throws UnsupportedEncodingException, IOException {
        WorkWithString n = new WorkWithString();//создание объекта класса WorkWithString
        n.runConsol();//вызов метода, выполняющего действия над файлом и строкой
    }
}
