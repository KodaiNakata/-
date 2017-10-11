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

	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 前期の時間割
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 後期の時間割

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
	 * 結果の表示
	 */
	private void result(){
		
		System.out.println("3次の時間割の結果");
		
		for(int number=0;number<f_TimeTableData3.size();number++){
			System.out.print(f_TimeTableData3.get(number).getDayOfWeek()+"曜日、");// 曜日
			System.out.print(f_TimeTableData3.get(number).getPeriod()+"限目、");// 限目
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getNumber()+"コマ、");// コマ数
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade()+"年、");// 学年
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getPreviousOrLatter()+"、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getSubject()+"、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getTeacher()+"先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()+"教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass()+"クラス");// コース・クラス
		}
	}
	
	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3() {
		setClassOfGrade3ToTimeTable3();// 3次の学年ごとの授業を時間割にセットする
	}

	/*
	 * 学年ごとの授業を時間割にセットする
	 */
	private void setClassOfGrade3ToTimeTable3() {

		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {
			TimeTable timeTableData = new TimeTable();
			timeTableData.setClassOfGrade(f_ClassOfGradeData3.get(number));
			f_TimeTableData3.add(timeTableData);
			// f_TimeTableData3.get(number).setClassOfGrade(f_ClassOfGradeData3.get(number));

			// System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade()+"年");
			// System.out.println(f_TimeTableData3.get(number).getClassOfGrade().getTeacher()+"先生");
			// System.out.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass()+"クラス");

			// 重複がある限り繰り返す
			do {

				makeFirstTimeTable3(number);// 最初の3次の時間割を決める
			} while (checkDuplication());

		}
	}

	/*
	 * 最初の3次の時間割を決める
	 * 
	 * @param number 番目
	 */
	private void makeFirstTimeTable3(int number) {
		int randomDay = Calculation.getRnd(TimeTable.changeDayToValue("月"),
				TimeTable.changeDayToValue("土"));// 曜日をランダムに決定
		int randomPeriod = Calculation.getRnd(1, 5);// 限目をランダムに決定
		int randomClassRoom = Calculation.getRnd(
				TimeTable.changeRoomToValue("31-202"),
				TimeTable.changeRoomToValue("31-803"));// 教室をランダムに決定
		f_TimeTableData3.get(number).setDayOfWeek(
				TimeTable.changeValueToDay(randomDay));
		f_TimeTableData3.get(number).setPeriod(randomPeriod);
		f_TimeTableData3.get(number).setClassRoom(
				TimeTable.changeValueToRoom(randomClassRoom));
	}

	/*
	 * 重複がないかのチェック
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication() {

		for (int number = 0; number < f_TimeTableData3.size(); number++) {

			// 1次の時間割と重複するとき
			if (checkDuplication1(number)) {
				return true;
			}

			// 2次の時間割と重複するとき
			if (checkDuplication2(number)) {
				return true;
			}
		}

		return false;
	}

	/*
	 * 1次の時間割と重複がないかのチェック
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication1(int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (f_TimeTableData1.get(number1).getDayOfWeek()
					.equals(f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData1
						.get(number1)
						.getClassOfGrade()
						.getPreviousOrLatter()
						.equals(f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						// 同じ限目のとき
						if (f_TimeTableData1.get(number1).getPeriod() == f_TimeTableData3
								.get(number).getPeriod()) {

							// 同じ先生のとき
							if (f_TimeTableData1
									.get(number1)
									.getClassOfGrade()
									.getTeacher()
									.equals(f_TimeTableData3.get(number)
											.getClassOfGrade().getTeacher())) {
								return true;
							}

							// 同じ教室のとき
							else if (f_TimeTableData1
									.get(number1)
									.getClassRoom()
									.equals(f_TimeTableData3.get(number)
											.getClassRoom())) {
								return true;
							}

							// 同じコース・クラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == (f_TimeTableData3
									.get(number).getClassOfGrade()
									.getCourseOrClass())) {
								return true;
							}

							// 1次の時間割のクラスがabクラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "ab") {

								// 3次の時間割のクラスがaクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == ("a")) {
									return true;
								}

								// 3次の時間割のクラスがbクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "b") {
									return true;
								}

								// 3次の時間割のクラスが奇数クラスまたは偶数クラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "奇数"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "偶数") {
									return true;
								}
							}

							// 1次の時間割のクラスがbcクラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "bc") {

								// 3次の時間割のクラスがaクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "b") {
									return true;
								}

								// 3次の時間割のクラスがbクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "c") {
									return true;
								}

								// 3次の時間割のクラスが奇数クラスまたは偶数クラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "奇数"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "偶数") {
									return true;
								}
							}

							// 1次の時間割が奇数クラスまたは偶数クラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "奇数"
									|| f_TimeTableData1.get(number1)
											.getClassOfGrade()
											.getCourseOrClass() == "偶数") {

								// 3次の時間割のクラスがaクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "a") {
									return true;
								}

								// 3次の時間割のクラスがbクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "b") {
									return true;
								}

								// 3次の時間割のクラスがcクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "c") {
									return true;
								}

								// 3次の時間割のクラスがabクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "ab") {
									return true;
								}

								// 3次の時間割のクラスがbcクラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "bc") {
									return true;
								}
							}

							// 1次の時間割がaクラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "a") {

								// 3次の時間割のクラスがabクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "ab") {
									return true;
								}

								// 3次の時間割のクラスが奇数クラスまたは偶数クラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "奇数"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "偶数") {
									return true;
								}
							}

							// 1次の時間割がbクラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "b") {

								// 3次の時間割のクラスがabクラスまたはbcクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "ab"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "bc") {
									return true;
								}

								// 3次の時間割のクラスが奇数クラスまたは偶数クラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "奇数"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "偶数") {
									return true;
								}
							}

							// 1次の時間割がcクラスのとき
							else if (f_TimeTableData1.get(number1)
									.getClassOfGrade().getCourseOrClass() == "c") {

								// 3次の時間割のクラスがbcクラスのとき
								if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "bc") {
									return true;
								}

								// 3次の時間割のクラスが奇数クラスまたは偶数クラスのとき
								else if (f_TimeTableData3.get(number)
										.getClassOfGrade().getCourseOrClass() == "奇数"
										|| f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass() == "偶数") {
									return true;
								}
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * 2次の時間割と重複がないかのチェック
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication2(int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (f_TimeTableData2.get(number2).getDayOfWeek()
					.equals(f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData2
						.get(number2)
						.getClassOfGrade()
						.getPreviousOrLatter()
						.equals(f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						// 同じ限目のとき
						if (f_TimeTableData2.get(number2).getPeriod() == f_TimeTableData3
								.get(number).getPeriod()) {

							return true;
						}
					}
				}
			}
		}

		return false;
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

		
		if (DEBUG) {
			result();// 結果の表示
		}
		return true;
	}

	/*
	 * 終了
	 */
	public void finish() {
		writeRoomAndDayAndPeriodFile();// 教室と何曜日と何限目が決まったファイルを書き込む
		super.finish();
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
				finish();// 終了
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

		System.out.println("担当者が決まった3次のファイル" + FILE3_NAME + "に書き込みます。");

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			output.print(f_TimeTableData3.get(number).getDayOfWeek() + ",");// 曜日
			output.print(f_TimeTableData3.get(number).getPeriod() + ",");// 限
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ ",");// コマ数
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ ",");// 学年
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ ",");// 前期後期
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ ",");// 科目名
			output.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeacher()
					+ ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass());// コース・クラス
			// output.println();
		}

		output.close();
		System.out.println(FILE3_NAME + "へ書き込みました。");
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
							TimeTable timeTableData = new TimeTable();
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

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						TimeTable timeTableData = new TimeTable();
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
							ClassOfGrade classOfGradeData = new ClassOfGrade();
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

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						ClassOfGrade classOfGradeData = new ClassOfGrade();
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

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						ClassOfGrade classOfGradeData = new ClassOfGrade();
						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.setTeacher(strData[4]);// 担当教員
						classOfGradeData.setCourseOrClass(strData[5]);// コース・クラス
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
