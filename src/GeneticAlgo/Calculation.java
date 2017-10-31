package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/*
 * 計算用のクラス
 *
 * @author Nakata
 */
public class Calculation {

	static Random random = new Random(100);

	/*
	 * 累乗根の取得
	 * 
	 * @param value 値
	 * 
	 * @param pow 累乗
	 * 
	 * @return 累乗根
	 */
	public static double getPowerRoot(double value, double pow) {

		return Math.pow(value, pow);
	}

	/*
	 * 平方根の取得
	 * 
	 * @param value 値
	 * 
	 * @return 平方根
	 */
	public static double getSqrt(double value) {
		return Math.sqrt(value);
	}

	/*
	 * minからmaxの間の整数値のいずれかを出力する一様乱数
	 * 
	 * @param min:最小値
	 * 
	 * @param max:最大値
	 */
	public static int getRnd(int min, int max) {
		int k;
		do {
			k = (int) (Math.random() * (max - min + 1) + min);
		} while (k == max + 1);

		return k;
	}

	/*
	 * minからmaxの間の実数値のいずれかを出力する一様乱数
	 * 
	 * @param min:最小値
	 * 
	 * @param max:最大値
	 */
	public static double getDRnd(double min, double max) {
		return (Math.random() * (max - min + 1.0) + min);
	}

	/*
	 * 0から1の間の実数値のいずれかを出力する一様乱数
	 */
	public static double getDDRnd() {
		return random.nextDouble();
	}

	/*
	 * 整数値を入力する
	 * 
	 * @param string:表示したい文字列
	 */
	public static int inputInt(String string) {
		int num = 0;

		System.out.println(string);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String str = br.readLine();
			num = Integer.parseInt(str);
		} catch (IOException e) {
			System.out.println("入出力エラーです。");
		}

		return num;
	}

	/*
	 * 整数値を入れ替える
	 * 
	 * @param arrayList:Integer型の動的配列
	 * 
	 * @param before:前の要素
	 * 
	 * @param after:後の要素
	 */
	public static void exchangeInteger(ArrayList<Integer> arrayList,
			int before, int after) {

		int tmp = arrayList.get(before);
		arrayList.set(before, arrayList.get(after));
		arrayList.set(after, tmp);

	}

	/*
	 * 文字列を入れ替える
	 * 
	 * @param arrayList:String型の動的配列
	 * 
	 * @param before:前の要素
	 * 
	 * @param after:後の要素
	 */
	public static void exchangeString(ArrayList<String> arrayList, int before,
			int after) {

		String strTmp = arrayList.get(before);
		arrayList.set(before, arrayList.get(after));
		arrayList.set(after, strTmp);

	}

	/*
	 * 動的配列を入れ替える
	 * 
	 * @param arrayList:T型の動的配列
	 * 
	 * @param before:前の要素
	 * 
	 * @param after:後の要素
	 */
	public static <T> void exchangeArrayList(ArrayList<T> arrayList,
			int before, int after) {

		T tmp = arrayList.get(before);
		arrayList.set(before, arrayList.get(after));
		arrayList.set(after, tmp);

	}

	/*
	 * 2次元の動的配列を入れ替える
	 * 
	 * @param arrayArrayList:T型の2次元の動的配列
	 * 
	 * @param beforeCol:入れ替えられる行
	 * 
	 * @param beforeRow:入れ替えられる列
	 * 
	 * @param afterCol:入れ替える行
	 * 
	 * @param afterRow:入れ替える列
	 */
	public static <T> void exchangeArrayArrayList(
			ArrayList<ArrayList<T>> arrayArrayList, int beforeCol,
			int beforeRow, int afterCol, int afterRow) {
		T tmp = arrayArrayList.get(afterCol).get(afterRow);
		arrayArrayList.get(beforeCol).set(beforeRow,
				arrayArrayList.get(afterCol).get(afterRow));
		arrayArrayList.get(afterCol).set(afterRow, tmp);
	}

	// public static <T>void exchangeStaticArray(){
	// T tmp=array[col][row];
	// array[col][row]=arrayList.get(number);
	//
	// }
	/*
	 * 文字列型の動的配列同士の追加
	 * 
	 * @param whichList:どこの動的配列に追加するか
	 * 
	 * @param arrayList:追加したい動的配列
	 * 
	 * @param elementNumber:追加したい動的配列の要素番号
	 */
	public static void addStringArrayList(ArrayList<String> whichList,
			ArrayList<String> arrayList, int elementNumber) {
		whichList.add(arrayList.get(elementNumber));
	}

	/*
	 * 整数型の動的配列同士の追加
	 * 
	 * @param whichList:どこの動的配列に追加するか
	 * 
	 * @param arrayList:追加したい動的配列
	 * 
	 * @param elementNumber:追加したい動的配列の要素番号
	 */
	public static void addIntegerArrayList(ArrayList<Integer> whichList,
			ArrayList<Integer> arrayList, int elementNumber) {
		whichList.add(arrayList.get(elementNumber));
	}

	/*
	 * 整数型の動的配列に整数を追加
	 * 
	 * @param whichList:どこの動的配列に追加するか
	 * 
	 * @param arrayList:追加したい動的配列
	 * 
	 * @param elementNumber:追加したい動的配列の要素番号
	 */
	public static void addIntegerAndArrayList(ArrayList<Integer> whichList,
			int elementNumber) {
		whichList.add(elementNumber);
	}

	/*
	 * 整数型の動的配列の中の値を1減らす arrayList:1減らしたい動的配列 number:要素番号
	 */
	public static void decrementArrayList(ArrayList<Integer> arrayList,
			int number) {
		arrayList.set(number, arrayList.get(number) - 1);
	}

	/*
	 * クラス型の動的配列の追加
	 * 
	 * @param whichList:どこの動的配列に追加するか
	 * 
	 * @param arrayList:追加したい動的配列
	 * 
	 * @param elementNumber:追加したい動的配列の要素番号
	 */
	public static <T> void addArrayList(ArrayList<T> whichList,
			ArrayList<T> arrayList, int elementNumber) {
		whichList.add(arrayList.get(elementNumber));
	}

	/*
	 * 整数型の多次元配列の動的配列の追加
	 * 
	 * @param arraysList:多次元の動的配列
	 * 
	 * @param arrayList:1次元の動的配列
	 * 
	 * @param number:整数値
	 */
	public static void addIntegerArrayArrayList(
			ArrayList<ArrayList<Integer>> arraysList,
			ArrayList<Integer> arrayList, int beforeRow, int col, int afterRow) {
		arrayList.add(beforeRow);
		arraysList.add(arrayList);
	}

	/*
	 * 多次元の動的配列の追加 arraysList:追加される2次元の動的配列 arrayList:追加する1次元の動的配列 col:何行目か
	 * number:何番目の要素を追加するか
	 */
	public static <T> void addArrayArrayList(
			ArrayList<ArrayList<T>> arraysList, ArrayList<T> arrayList,
			int col, int row, int number) {
		arraysList.get(col).set(row, arrayList.get(number));
	}
}
