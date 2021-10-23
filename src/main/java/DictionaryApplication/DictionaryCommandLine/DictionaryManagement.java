package DictionaryApplication.DictionaryCommandLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class DictionaryManagement {
    //doc du lieu tu file - final
    public void insertFromFile(Dictionary dictionary, String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String tam, word_explain = "", word_target = "";
            while ((tam = bufferedReader.readLine()) != null) {
                if (tam.startsWith("|")) {
                    if (!word_explain.isEmpty()) {
                        Word word = new Word();
                        word.setWord_target(word_target.trim());
                        word.setWord_explain(word_explain.trim());
                        dictionary.add(word);
                        word_explain = "";
                    }
                    word_target = tam.replace("|", "").trim();

                } else {
                    word_explain = word_explain + tam + "\n";
                }
            }
            Word word = new Word();
            word.setWord_target(word_target.trim());
            word.setWord_explain(word_explain.trim());
            dictionary.add(word);
        } catch (IOException e) {
            System.out.println("insertFromCommandline");
            System.out.println(e);
        }
    }

    //xuat ra file (xoa roi xuat) - da xong - final
    public void exportToFile(Dictionary dictionary, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : dictionary) {
                bufferedWriter.write("|" + word.getWord_target() + "\n" + word.getWord_explain());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // them - da xong - final
    public void addWord(Word word, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("|" + word.getWord_target() + "\n" + word.getWord_explain() + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    //xoa tu - da xong
    public void removeWord(Dictionary dictionary, int index, String path) {
        try {
            dictionary.remove(index);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    //update nghia cua tu - thay the
    public void updateWord(Dictionary dictionary, int index, String path, String mean) {
        try {
            dictionary.get(index).setWord_explain(mean);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }


    //them nghia cua tu
    public void addupdateWord(Dictionary dictionary, int index, String path, String mean) {
        try {
            String tam = dictionary.get(index).getWord_explain();
            dictionary.get(index).setWord_explain(tam + "\n" + mean);
            exportToFile(dictionary, path);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    //tim kiem -final - dung de tim kiem chinh xac nhung tu duoc them de check xem nen bo sung hay sua
    public int searchWord(Dictionary dictionary, String key) {
        try {
            for (int i = 0; i < dictionary.size(); ++i) {
                if (dictionary.get(i).getWord_target().equals(key)) {
                    return i;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    //
    public ObservableList<String> lookupWord(Dictionary dictionary, String key) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (int i = 0; i < dictionary.size(); ++i) {
            if (dictionary.get(i).getWord_target().contains(key)) {
                list.add(String.valueOf(dictionary.get(i).getWord_target()));
            }
        }
        return list;
    }
}
