package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * 教室と何曜日と何限目を決めるためのクラス
 * @author Nakata
 */
public class Decide_roomAndDayAndPeriod extends Decide_faculty {

	protected ArrayList<TimeTable> f_TimeTableData1 = new ArrayList<TimeTable>();// 1次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData2 = new ArrayList<TimeTable>();// 2次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData3 = new ArrayList<TimeTable>();// 3次の時間割のデータ

	/*
	 * コンストラクタ
	 */
	public Decide_roomAndDayAndPeriod() {
		super();
	}

	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3(){

	}

	/*
	 * プログラムを実行する
	 */
	private void executeProg() {
		int count = 0;
		PROG_COUNT++;
		super.startProg();// プログラム実行開始

		do {
			System.out.println("回数：" + (count + 1) + "回目");

			// プログラムを終えたとき
			if (isFinishedProg()) {
				break;
			}
			count++;
		} while (true);
		super.finishProg();// プログラム実行終了
	}

	/*
	 * プログラムを終了したか
	 *
	 * @return true:終了
	 */
	private boolean isFinishedProg() {
		readFacultyFile();// 担当者が決まったファイルを読み込む(3次のファイル)
		readRoomDayPeriodFile();// 教室と何曜日と何限目が決まったファイルを読み込む(1次と2次のファイル)
		makeTimeTable3();// 3次の時間割を作成
		return true;
	}

	/*
	 * 実行する処理
	 *
	 * @return 0:終了
	 */
	public int exe() {

		for (;;) {
			switch (super.menu()) {
			case 1:
				super.instruction();// 説明
				break;
			case 2:
				executeProg();// プログラムを実行
				break;
			case 3:
				super.finish();// 終了
				return 0;
			}
		}
	}
	// --------------------------------//
	// ----------ファイル関係----------//
	// --------------------------------//
	/*
	 * 教室と何曜日と何限目が決まったファイルを書き込む
	 */
	public void writeRoomAndDayAndPeriodFile() {
		// writeRoomAndDayAndPeriodFile1();// 1次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		// writeRoomAndDayAndPeriodFile2();// 2次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		writeRoomAndDayAndPeriodFile3();// 3次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
	}

	/*
	 * 1次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile1() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE1_NAME, false);
		//
		// System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 曜日
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// コマ数
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 学年
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 前期後期
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 科目名
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 担当教員
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 教室
		// output.println();
		// }
	}

	/*
	 * 2次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile2() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE2_NAME, false);
		//
		// System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 曜日
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// コマ数
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 学年
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 前期後期
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 科目名
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 担当教員
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// 教室
		// output.println();
		// }
	}

	/*
	 * 3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFile3() {

		PrintWriter output;
		output = FileIO.writeFile(FILE3_NAME, false);

		System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 曜日
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// コマ数
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 学年
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 前期後期
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 科目名
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 教室
			output.println();
		}
	}

	/*
	 * 教室と何曜日と何限目が決まったファイルを読み込む
	 */
	private void readRoomDayPeriodFile() {
		readRoomDayPeriodFile1();// 1次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
		readRoomDayPeriodFile2();// 2次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
		// readRoomDayPeriodFile3();// 3次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	}

	/*
	 * 1次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	 */
	private void readRoomDayPeriodFile1() {

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
									.parseInt(strData[1]));// 限目
							timeTableData.getClassOfGrade().setNumber(
									Integer.parseInt(strData[2]));// コマ数
							timeTableData.getClassOfGrade().setGrade(
									Integer.parseInt(strData[3]));// 学年
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().setSubject(
									strData[5]);// 科目名
							timeTableData.getClassOfGrade().setTeacher(
									strData[6]);// 担当教員
							timeTableData.setClassRoom(strData[7]);// 教室

							f_TimeTableData1.add(timeTableData);// 1次の時間割の動的配列に追加
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
	 * 2次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	 */
	private void readRoomDayPeriodFile2() {

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

						timeTableData.setDayOfWeek(strData[0]);// 曜日
						timeTableData.setPeriod(Integer.parseInt(strData[1]));// 限目
						timeTableData.getClassOfGrade().setNumber(
								Integer.parseInt(strData[2]));// コマ数
						timeTableData.getClassOfGrade().setGrade(
								Integer.parseInt(strData[3]));// 学年
						timeTableData.getClassOfGrade().setPreviousOrLatter(
								strData[4]);// 前期後期
						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名
						timeTableData.getClassOfGrade().setTeacher(strData[6]);// 担当教員
						timeTableData.setClassRoom(strData[7]);// 教室

						f_TimeTableData2.add(timeTableData);// 2次の時間割の動的配列に追加

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	// /*
	// * 3次のファイル(教室と何曜日と何限目が決まったファイル)を読み込む
	// */
	// private void readRoomDayPeriodFile3() {
	//
	// String[] strData = new String[ORDER3_DATA];
	//
	// BufferedReader input = FileIO.readFile(FACULTY3_NAME);
	//
	// ClassOfGrade classOfGradeData = new ClassOfGrade();
	//
	// try {
	// String line = new String();
	//
	// // 一番上から一番下の行まで読み込む
	// for (int cols = 0; cols < ORDER3_COLS; cols++) {
	//
	// // 読み込んだ1行が空白でないとき
	// if ((line = input.readLine()) != null) {
	//
	// // 上から1行以上のとき
	// if (1 <= cols) {
	// strData = line.split(",");
	//
	// classOfGradeData
	// .setNumber(Integer.parseInt(strData[0]));// コマ数
	// classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
	// classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
	// classOfGradeData.setSubject(strData[3]);// 科目名
	// classOfGradeData.setTeacher(strData[4]);// 担当教員
	//
	// f_ClassOfGradeData3.add(classOfGradeData);// 3次の時間割の動的配列に追加
	// }
	// }
	// }
	// input.close();
	// } catch (IOException error_report) {
	// System.out.println(error_report);
	// }
	// }

	/*
	 * 担当者が決まったファイルを読み込む
	 */
	private void readFacultyFile() {
		// readFacultyFile1();
		// readFacultyFile2();
		readFacultyFile3();// 3次のファイル(担当者決定のファイル)を読み込む
	}

	/*
	 * 1次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FACULTY1_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

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
							classOfGradeData.setNumber(Integer
									.parseInt(strData[0]));// コマ数
							classOfGradeData.setGrade(Integer
									.parseInt(strData[1]));// 学年
							classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
							classOfGradeData.setSubject(strData[3]);// 科目名
							classOfGradeData.setTeacher(strData[4]);// 担当教員

							f_ClassOfGradeData1.add(classOfGradeData);// 1次の時間割の動的配列に追加
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
	 * 2次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FACULTY2_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.setTeacher(strData[4]);// 担当教員

						f_ClassOfGradeData2.add(classOfGradeData);// 2次の時間割の動的配列に追加

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 3次のファイル(担当者決定のファイル)を読み込む
	 */
	private void readFacultyFile3() {

		String[] strData = new String[ORDER3_DATA];

		BufferedReader input = FileIO.readFile(FACULTY3_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.setTeacher(strData[4]);// 担当教員

						f_ClassOfGradeData3.add(classOfGradeData);// 3次の学年ごとの授業の動的配列に追加
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}
}
