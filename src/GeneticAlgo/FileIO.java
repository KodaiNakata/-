package GeneticAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 *  ファイルの入出力関係のクラス
 *  @author Nakata
 */
public class FileIO {

	/*
	 * ファイルを読み込む
	 *
	 * @param fileName:ファイルの名前
	 */
	public static BufferedReader readFile(String fileName) {

		BufferedReader readFile = null;

		try {
			readFile = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException error_report) {
			System.out.println(fileName + "not found");
			System.out.println(error_report);
			System.exit(1);
		}

		return readFile;
	}

	/*
	 * ファイルへ書き込む
	 *
	 * @param fileName:ファイルの名前
	 */
	public static PrintWriter writeFile(String fileName, boolean app) {
		PrintWriter f_write = null;

		try {
			f_write = new PrintWriter(new BufferedWriter(new FileWriter(
					fileName, app)));
		} catch (IOException error_report) {
			System.out.println(error_report);
			System.exit(1);
		}

		return f_write;
	}

	/*
	 * 探したいファイルをチェック
	 * @param fileName:探したいファイルの名前
	 */
	public static void checkTargetFile(String fileName) {

		File targetFile = new File(fileName);

		try{

		// ファイルが存在しないとき
		if (!(targetFile.exists())) {
			System.out.println(targetFile + "が存在しません。");
			System.out.println(targetFile+"を作成します。");

			// Writer writer = new FileWriter(scorefile);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					targetFile)));
			pw.println(0.0);
			pw.close();
		}

		// ファイルが存在するとき
		else{
			System.out.println(targetFile+"は存在します。");
		}

		}catch(IOException e){
			System.out.println("入出力エラーです。");
		}
	}
}
