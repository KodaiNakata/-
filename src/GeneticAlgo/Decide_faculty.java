package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

// 時間割を決めるためのクラス
public class Decide_faculty implements iTimeTable {

	private static StringBuffer TITLE = new StringBuffer("遺伝的アルゴリズムを用いた時間割作成");
	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 前期の時間割
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 後期の時間割
	private static final String RESULT_FILE_NAME = "result.csv";// 時間割作成の結果のファイルの名前
	// private ArrayList
	protected static int PROG_COUNT;// プログラムを実行した回数

	/*
	 * コンストラクタ
	 */
	public Decide_faculty() {

		for (int day = 0; day < MAX_DAY; day++) {
			for (int period = 0; period < MAX_PERIOD; period++) {
				f_PreviousTimeTables[day][period] = new TimeTable();
				f_LatterTimeTables[day][period] = new TimeTable();
			}
		}
	}

	// ------------------------------------------//
	// -------------ファイル関係----------------//
	// ------------------------------------------//
	/*
	 * 結果のファイルを書き込む
	 */
	private void writeResultFile() {
		PrintWriter output;
		output = FileIO.writeFile(RESULT_FILE_NAME, false);

		System.out.println("プログラムを終了しました。" + RESULT_FILE_NAME + "に書き込みます。");

		// 前期の時間割作成の結果を表示
		for (int day = 0; day < MAX_DAY; day++) {
			for (int period = 0; period < MAX_PERIOD; period++) {

				for (int number = 0; number < f_PreviousTimeTables[day][period]
						.getClassOfGrade().getCoursesOrClassesSize(); number++) {
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getCoursesOrClasses(number)
							+ ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getSubjects(number) + ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getTeacher(number) + ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getClassRooms(number) + ",");
				}
			}
			output.println();
		}
		output.close();
		System.out.println("書き込みを終了しました。");
	}

	/*
	 * ファイルを読み込む
	 */
	public void readFile() {

		readFile1();// 1次のファイルを読み込む

		readFile2();// 2次のファイルを読み込む

		readFile3();// 3次のファイルを読み込む
	}

	/*
	 * 1次のファイルを読み込む
	 */
	private void readFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FILE1_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER1_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							timeTableData.setDayOfWeek(strData[0]);// 曜日
							timeTableData.setPeriod(Integer
									.parseInt(strData[1]));// 限
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[2]));// コマ数
							timeTableData
									.setGrade(Integer.parseInt(strData[3]));// 学年
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().addSubjects(
									strData[5]);// 科目名
							timeTableData.getClassOfGrade().addTeacher(
									strData[6]);// 担当教員
							timeTableData.getClassOfGrade().addClassRooms(
									strData[7]);// 教室
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 2次のファイルを読み込む
	 */
	private void readFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FILE2_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							timeTableData.setDayOfWeek(strData[0]);// 曜日
							timeTableData.setPeriod(Integer
									.parseInt(strData[1]));// 限
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[2]));// コマ数
							timeTableData
									.setGrade(Integer.parseInt(strData[3]));// 学年
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().addSubjects(
									strData[5]);// 科目名
							timeTableData.getClassOfGrade().addTeacher(
									strData[6]);// 担当教員
							timeTableData.getClassOfGrade().addClassRooms(
									strData[7]);// 教室
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 3次のファイルを読み込む
	 */
	private void readFile3() {

		String[] strData = new String[ORDER3_DATA];

		BufferedReader input = FileIO.readFile(FILE3_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// コマ数が空白でないとき
						if (strData[0] != null) {
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[0]));// コマ数
							timeTableData
									.setGrade(Integer.parseInt(strData[1]));// 学年
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[2]);// 前期後期
							timeTableData.getClassOfGrade().addSubjects(
									strData[3]);// 科目名
							timeTableData.getClassOfGrade().addTeacher(
									strData[4]);// 担当教員
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * メニュー
	 */
	public int menu() {
		int d = InOutPut.getMenu("which 1)説明 2)プログラム実行 3)終了", 1, 3);
		return d;
	}

	/*
	 * 説明を表示
	 */
	public void instruction() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("：説明");
		InOutPut.decoration(s.toString(), '-');

		System.out.println("遺伝的アルゴリズムを用いて時間割を作成する");
	}

	/*
	 * プログラムを実行する
	 */
	private void executeProg() {
		int count = 0;
		PROG_COUNT++;
		startProg();// プログラム実行開始

		do {
			System.out.println("回数：" + (count + 1) + "回目");

			// プログラムを終えたとき
			if (isFinishedProg()) {
				break;
			}
			count++;
		} while (true);
		finishProg();// プログラム実行終了
	}

	/*
	 * プログラム実行開始
	 */
	protected void startProg() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("：第");
		s.append(PROG_COUNT);
		s.append("回プログラム実行開始!");

		InOutPut.decoration(s.toString(), '-');
	}

	/*
	 * プログラムを終了したか
	 */
	private boolean isFinishedProg() {
		readFile();// ファイルを読み込む
		// result();// 結果の表示
		return true;
	}

	/*
	 * プログラム実行終了
	 */
	public void finishProg() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("：第");
		s.append(PROG_COUNT);
		s.append("回実行終了!");

		InOutPut.decoration(s.toString(), '-');
		System.out.println("");
	}

	/*
	 * 終了
	 */
	public void finish() {
		writeResultFile();
		System.out.println("See you later !");
	}

	/*
	 * 実行する処理
	 */
	public int exe() {

		for (;;) {
			switch (menu()) {
			case 1:
				instruction();// 説明を表示
				break;
			case 2:
				executeProg();// プログラムを実行
				break;
			case 3:
				finish();// プログラムを終了
				return 0;
			}
		}
	}
}
