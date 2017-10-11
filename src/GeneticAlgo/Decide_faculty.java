package GeneticAlgo;

import java.util.ArrayList;

/*
 * 科目をだれが担当するのかを決めるためのクラス
 * @author Nakata
 */
public class Decide_faculty implements iTimeTable {

	private static StringBuffer TITLE = new StringBuffer("遺伝的アルゴリズムを用いた時間割作成");
	
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData1=new ArrayList<ClassOfGrade>();// 1次の学年ごとの授業のデータ
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData2=new ArrayList<ClassOfGrade>();// 2次の学年ごとの授業のデータ
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData3=new ArrayList<ClassOfGrade>();// 3次の学年ごとの授業のデータ
//	protected static final String RESULT_FILE_NAME = "room_3.csv";// 3次の結果のファイルの名前
	protected static int PROG_COUNT;// プログラムを実行した回数

	/*
	 * コンストラクタ
	 */
	public Decide_faculty() {

//		for (int day = 0; day < MAX_DAY; day++) {
//			for (int period = 0; period < MAX_PERIOD; period++) {
//				f_PreviousTimeTables[day][period] = new TimeTable();
//				f_LatterTimeTables[day][period] = new TimeTable();
//			}
//		}
	}

//	/*
//	 * 前期と後期の時間割の結果の表示
//	 */
//	private void result() {
//
//		// 前期の時間割の結果
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			System.out.print(TimeTable.changeValueToDay(day) + "曜日");
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				System.out.println((period + 1) + "限目");
//
//				for (int number = 0; number < f_PreviousTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getGrade() + "年,");// 学年
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");// コース・クラス
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");// 学年
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");// 担当者
//					System.out.println(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom());// 教室
//
//				}
//			}
//		}
//
//		// 後期の時間割の結果
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			System.out.print(TimeTable.changeValueToDay(day) + "曜日");
//
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				System.out.println((period + 1) + "限目");
//
//				for (int number = 0; number < f_LatterTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//
//					System.out.print(f_LatterTimeTables[day][period].getGrade()
//							+ "年,");// 学年
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");// コース・クラス
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");// 学年
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");// 担当者
//					System.out.println(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom());// 教室
//				}
//			}
//		}
//
//		result3();// 3次のファイル(担当者決定のファイル)を表示
//	}

//	/*
//	 * 3次のファイル(それぞれの科目の担当者)の結果を表示
//	 */
//	private void result3(){
//
//		for(int number=0;number<f_ClassOfGradeData3.size();number++){
//			System.out.print(f_ClassOfGradeData3.get(number).getNumber()+"コマ,");// コマ数
//			System.out.print(f_ClassOfGradeData3.get(number).getGrade()+"年,");// 学年
//			System.out.print(f_ClassOfGradeData3.get(number).getPreviousOrLatter()+",");// 前期後期
//			System.out.print(f_ClassOfGradeData3.get(number).getSubject()+",");// 科目名
//			System.out.println(f_ClassOfGradeData3.get(number).getTeacher());// 先生
//		}
//	}

	// ------------------------------------------//
	// -------------ファイル関係----------------//
	// ------------------------------------------//
	/*
	 * 結果のファイルを書き込む
	 */
	private void writeResultFile() {
//		PrintWriter output;
//		output = FileIO.writeFile(RESULT_FILE_NAME, false);
//
//		System.out.println("プログラムを終了しました。" + RESULT_FILE_NAME + "に書き込みます。");
//
//		// 前期の時間割作成の結果を表示
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				for (int number = 0; number < f_PreviousTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom() + ",");
//				}
//			}
//			output.println();
//		}
//		output.close();
//		System.out.println("書き込みを終了しました。");

//		writeFile3();// 3次のファイル(担当者決定のファイル)を書き込む
	}

//	/*
//	 * 担当者が決まった3次のファイルを書き込む
//	 */
//	private void writeFile3(){
//		PrintWriter output;
//		output = FileIO.writeFile(FACULTY3_NAME, false);
//
//		System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");
//
//		for(int number=0;number<f_TimeTableData3.size();number++){
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 曜日
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 限
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// コマ数
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 学年
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 前期後期
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 科目名
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 担当教員
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// 教室
//			output.println();
//		}
//
//		output.close();
//		System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "の書き込みを終了しました。");
//	}

//	/*
//	 * ファイルを読み込む
//	 */
//	private void readFile() {
//
//		readFile1();// 1次のファイルを読み込む
//
//		readFile2();// 2次のファイルを読み込む
//
//		readFile3();// 3次のファイルを読み込む
//	}

//	/*
//	 * 1次のファイルを読み込む
//	 */
//	private void readFile1() {
//
//		String[] strData = new String[ORDER1_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE1_NAME);
//
//		ClassOfGrade classOfGradeData = new ClassOfGrade();
//
//		try {
//			String line = new String();
//
//			// 一番上から一番下の行まで読み込む
//			for (int cols = 0; cols < ORDER1_COLS; cols++) {
//
//				// 読み込んだ1行が空白でないとき
//				if ((line = input.readLine()) != null) {
//
//					// 上から1行以上のとき
//					if (1 <= cols) {
//						strData = line.split(",");
//
//						// 曜日が空白でないとき
//						if (strData[0] != null) {
//							classOfGradeData.setDayOfWeek(strData[0]);// 曜日
//							classOfGradeData.setPeriod(Integer
//									.parseInt(strData[1]));// 限
//							classOfGradeData.getClassOfGrade().setNumber(
//									Integer.parseInt(strData[2]));// コマ数
//							classOfGradeData
//									.setGrade(Integer.parseInt(strData[3]));// 学年
//							classOfGradeData.getClassOfGrade()
//									.setPreviousOrLatter(strData[4]);// 前期後期
//							classOfGradeData.getClassOfGrade().setSubject(
//									strData[5]);// 科目名
//							classOfGradeData.getClassOfGrade().setTeacher(
//									strData[6]);// 担当教員
//							classOfGradeData.getClassOfGrade().setClassRoom(
//									strData[7]);// 教室
//
//							f_ClassOfGradeData1.add(classOfGradeData);// 1次の時間割の動的配列に追加
//						}
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}
//
//	/*
//	 * 2次のファイルを読み込む
//	 */
//	private void readFile2() {
//
//		String[] strData = new String[ORDER2_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE2_NAME);
//
//		TimeTable timeTableData = new TimeTable();
//
//		try {
//			String line = new String();
//
//			// 一番上から一番下の行まで読み込む
//			for (int cols = 0; cols < ORDER2_COLS; cols++) {
//
//				// 読み込んだ1行が空白でないとき
//				if ((line = input.readLine()) != null) {
//
//					// 上から1行以上のとき
//					if (1 <= cols) {
//						strData = line.split(",");
//
////						System.out.print(cols+"行目\t");
////						System.out.print(strData[0]);
////						System.out.println(strData[1] + "限目");
//						timeTableData.setDayOfWeek(strData[0]);// 曜日
//						timeTableData.setPeriod(Integer.parseInt(strData[1]));// 限
//						timeTableData.getClassOfGrade().setNumber(
//								Integer.parseInt(strData[2]));// コマ数
//						timeTableData.setGrade(Integer.parseInt(strData[3]));// 学年
//						timeTableData.getClassOfGrade().setPreviousOrLatter(
//								strData[4]);// 前期後期
//						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名
//						timeTableData.getClassOfGrade().setTeacher(strData[6]);// 担当教員
//						timeTableData.getClassOfGrade()
//								.setClassRoom(strData[7]);// 教室
//
//						f_TimeTableData2.add(timeTableData);// 2次の時間割の動的配列に追加
//
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}
//
//	/*
//	 * 3次のファイルを読み込む
//	 */
//	private void readFile3() {
//
//		String[] strData = new String[ORDER3_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE3_NAME);
//
//		TimeTable timeTableData = new TimeTable();
//
//		try {
//			String line = new String();
//
//			// 一番上から一番下の行まで読み込む
//			for (int cols = 0; cols < ORDER3_COLS; cols++) {
//
//				// 読み込んだ1行が空白でないとき
//				if ((line = input.readLine()) != null) {
//
//					// 上から1行以上のとき
//					if (1 <= cols) {
//						strData = line.split(",");
//
//						timeTableData.getClassOfGrade().setNumber(
//								Integer.parseInt(strData[0]));// コマ数
//						timeTableData.setGrade(Integer.parseInt(strData[1]));// 学年
//						timeTableData.getClassOfGrade().setPreviousOrLatter(
//								strData[2]);// 前期後期
//						timeTableData.getClassOfGrade().setSubject(strData[3]);// 科目名
//						timeTableData.getClassOfGrade().setTeacher(strData[4]);// 担当教員
//
//						f_TimeTableData3.add(timeTableData);// 3次の時間割の動的配列に追加
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}

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

//	/*
//	 * プログラムを実行する
//	 */
//	private void executeProg() {
//		int count = 0;
//		PROG_COUNT++;
//		startProg();// プログラム実行開始
//
//		do {
//			System.out.println("回数：" + (count + 1) + "回目");
//
//			// プログラムを終えたとき
//			if (isFinishedProg()) {
//				break;
//			}
//			count++;
//		} while (true);
//		finishProg();// プログラム実行終了
//	}

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

//	/*
//	 * プログラムを終了したか
//	 */
//	private boolean isFinishedProg() {
//		readFile();// ファイルを読み込む
//		result();// 結果の表示
//		return true;
//	}

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
//		writeResultFile();
		System.out.println("See you later !");
	}

//	/*
//	 * 実行する処理
//	 */
//	public int exe() {
//
//		for (;;) {
//			switch (menu()) {
//			case 1:
//				instruction();// 説明を表示
//				break;
//			case 2:
//				executeProg();// プログラムを実行
//				break;
//			case 3:
//				finish();// プログラムを終了
//				return 0;
//			}
//		}
//	}
}
