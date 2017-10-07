package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  入出力関係のクラス
 *  @author Nakata
 */
public class InOutPut {

	/*
	 * コンストラクタ
	 */
	public InOutPut() {

	}

	/*
	 * 何かキーを押す
	 */
	public static void anyKey() {
		System.out.println("Hit any key...");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			br.readLine();
		} catch (IOException e) {
			System.out.println("入出力エラーです。");
		}
	}

	/*
	 * 最小値と最大値の領域内で整数値を入力する
	 * @param min:最小値
	 * @param max:最大値
	 */
	public static int getnCheck(int min, int max) {
		int num = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String str = br.readLine();
			num = Integer.parseInt(str);

			while (num < min || num > max) {
				str = br.readLine();
				num = Integer.parseInt(str);
			}
		} catch (IOException e) {
			System.out.println("入出力エラーです。");
		}
		return num;
	}

	/*
	 * メニューを取得
	 * @param str:出力する文字列
	 * @param min:最小値
	 * @param max:最大値
	 */
	public static int getMenu(String str, int min, int max) {
		System.out.println(str);

		int d = getnCheck(min, max);
		System.out.println(d);
		return d;
	}

	/*
	 * デコレーション
	 * @param str:出力する文字列
	 * @param chara:出力する文字
	 */
	public static void decoration(String str, char chara) {
		StringBuffer ds = new StringBuffer("");
		ds.append(str);
		for (int i = 0; i < ds.length(); i++)
			ds.setCharAt(i, chara);

		System.out.println(ds);
		System.out.println(str);
		System.out.println(ds);

	}
}
