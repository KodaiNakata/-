package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/*
 * 科目をだれが担当するのかを決めるためのクラス
 * @author Nakata
 */
public class Decide_faculty implements iTimeTable {

	private static StringBuffer TITLE = new StringBuffer("遺伝的アルゴリズムを用いた時間割提案作成");
	private static final double DEFINE_NUM = 0.6;// 評価式のある定数
	private static final int SUBJECT_NUM = 10;// 科目数
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData1 = new ArrayList<ClassOfGrade>();// 1次の学年ごとの授業のデータ
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData2 = new ArrayList<ClassOfGrade>();// 2次の学年ごとの授業のデータ
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData3 = new ArrayList<ClassOfGrade>();// 3次の学年ごとの授業のデータ
	protected ArrayList<Teacher> f_TeacherData = new ArrayList<Teacher>();// 先生のデータ
	protected double f_FacultyEvaluationValue;// 担当者決定の評価値
	protected static int PROG_COUNT;// プログラムを実行した回数

	/*
	 * コンストラクタ
	 */
	public Decide_faculty() {

		// for (int day = 0; day < MAX_DAY; day++) {
		// for (int period = 0; period < MAX_PERIOD; period++) {
		// f_PreviousTimeTables[day][period] = new TimeTable();
		// f_LatterTimeTables[day][period] = new TimeTable();
		// }
		// }
	}

	/*
	 * 担当者の授業のコマ数の評価値を計算
	 */
	public void calctTeacherEvaluationValue() {

		double value = 0.0;

		// (担当教員数)-((持っているコマ数)/DEFINE_NUM^(新規コマ数))
		// ---------------------------------------------------
		// √(持っているコマ数)
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			if (f_TeacherData.get(teacherNum).getNumOfAllSubject() == 0) {
				continue;
			}

			value += (((double) f_TeacherData.size() - ((double) f_TeacherData
					.get(teacherNum).getNumOfAllSubject() / Calculation
					.getPowerRoot(DEFINE_NUM, f_TeacherData.get(teacherNum)
							.getNumOfNewSubject()))) / Calculation
					.getSqrt((double) f_TeacherData.get(teacherNum)
							.getNumOfAllSubject()));
		}

		f_FacultyEvaluationValue = value;
	}

	// /*
	// * 前期と後期の時間割の結果の表示
	// */
	// private void result() {
	//
	// // 前期の時間割の結果
	// for (int day = 0; day < MAX_DAY; day++) {
	//
	// System.out.print(TimeTable.changeValueToDay(day) + "曜日");
	// for (int period = 0; period < MAX_PERIOD; period++) {
	//
	// System.out.println((period + 1) + "限目");
	//
	// for (int number = 0; number < f_PreviousTimeTables[day][period]
	// .getClassesOfGradesSize(); number++) {
	//
	// System.out.print(f_PreviousTimeTables[day][period]
	// .getGrade() + "年,");// 学年
	// System.out.print(f_PreviousTimeTables[day][period]
	// .getClassesOfGrades(number).getCourseOrClass()
	// + ",");// コース・クラス
	// System.out.print(f_PreviousTimeTables[day][period]
	// .getClassesOfGrades(number).getSubject() + ",");// 学年
	// System.out.print(f_PreviousTimeTables[day][period]
	// .getClassesOfGrades(number).getTeacher() + ",");// 担当者
	// System.out.println(f_PreviousTimeTables[day][period]
	// .getClassesOfGrades(number).getClassRoom());// 教室
	//
	// }
	// }
	// }
	//
	// // 後期の時間割の結果
	// for (int day = 0; day < MAX_DAY; day++) {
	//
	// System.out.print(TimeTable.changeValueToDay(day) + "曜日");
	//
	// for (int period = 0; period < MAX_PERIOD; period++) {
	//
	// System.out.println((period + 1) + "限目");
	//
	// for (int number = 0; number < f_LatterTimeTables[day][period]
	// .getClassesOfGradesSize(); number++) {
	//
	// System.out.print(f_LatterTimeTables[day][period].getGrade()
	// + "年,");// 学年
	// System.out.print(f_LatterTimeTables[day][period]
	// .getClassesOfGrades(number).getCourseOrClass()
	// + ",");// コース・クラス
	// System.out.print(f_LatterTimeTables[day][period]
	// .getClassesOfGrades(number).getSubject() + ",");// 学年
	// System.out.print(f_LatterTimeTables[day][period]
	// .getClassesOfGrades(number).getTeacher() + ",");// 担当者
	// System.out.println(f_LatterTimeTables[day][period]
	// .getClassesOfGrades(number).getClassRoom());// 教室
	// }
	// }
	// }
	//
	// result3();// 3次のファイル(担当者決定のファイル)を表示
	// }

	// /*
	// * 3次のファイル(それぞれの科目の担当者)の結果を表示
	// */
	// private void result3(){
	//
	// for(int number=0;number<f_ClassOfGradeData3.size();number++){
	// System.out.print(f_ClassOfGradeData3.get(number).getNumber()+"コマ,");//
	// コマ数
	// System.out.print(f_ClassOfGradeData3.get(number).getGrade()+"年,");// 学年
	// System.out.print(f_ClassOfGradeData3.get(number).getPreviousOrLatter()+",");//
	// 前期後期
	// System.out.print(f_ClassOfGradeData3.get(number).getSubject()+",");// 科目名
	// System.out.println(f_ClassOfGradeData3.get(number).getTeacher());// 先生
	// }
	// }

	/*
	 * 最初の担当者の決定
	 */
	private void decideFirstFaculty() {

		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {

			f_ClassOfGradeData3.get(number).setTeachers(
					f_TeacherData.get(Calculation.getRnd(0,
							f_TeacherData.size() - 1)));
		}
	}

	/*
	 * 3次の科目のコマ数のカウントをする
	 */
	private void countNumOfSubject3() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			int numOfAllSubject = 0;

			for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {

				if (Objects
						.equals(f_TeacherData.get(teacherNum).getName(),
								f_ClassOfGradeData3.get(number).getTeachers()
										.getName())) {

					int numOfNewSubject = 0;

					for (int subjectNum = 0; subjectNum < f_TeacherData.get(
							number).getSizeChargeOfSubject(); subjectNum++) {

						// 担当科目でないとき
						if (Objects.equals(f_TeacherData.get(teacherNum)
								.getChargeOfSubject(subjectNum),
								f_ClassOfGradeData3.get(number).getSubject()) == false) {

							// 最後の要素番号のとき
							if (subjectNum == f_TeacherData.get(teacherNum)
									.getSizeChargeOfSubject() - 1) {
								numOfNewSubject += f_ClassOfGradeData3.get(
										number).getNumber();

								f_TeacherData.get(teacherNum).setNumOfNewSubject(numOfNewSubject);
							}
						}
					}
				}
			}
		}
	}

	// ------------------------------------------//
	// -------------ファイル関係----------------//
	// ------------------------------------------//
	/*
	 * 先生のファイルを読み込む
	 */
	public void readTeacherFile() {

		String[] strData = new String[TEACHER_DATA + SUBJECT_NUM];

		BufferedReader input = FileIO.readFile(TEACHER_NAME);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < TEACHER_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							Teacher teacherData = new Teacher();
							teacherData.setName(strData[0]);// 先生の名前
							teacherData.setNumOfBasicSeminar1(Integer
									.parseInt(strData[1]));// 基礎ゼミ1のコマ数
							teacherData.setNumOfBasicSeminar2(Integer
									.parseInt(strData[2]));// 基礎ゼミ2のコマ数
							teacherData.setNumOfOddEETraining(Integer
									.parseInt(strData[3]));// 電電実習(奇数)のコマ数
							teacherData.setNumOfEvenEETraining(Integer
									.parseInt(strData[4]));// 電電実習(偶数)のコマ数
							teacherData.setNumOfOddEEExperiment(Integer
									.parseInt(strData[5]));// 電電実験(奇数)のコマ数
							teacherData.setNumOfEvenEEExperiment(Integer
									.parseInt(strData[6]));// 電電実験(偶数)のコマ数
							teacherData
									.setNumOfOddEngineeringDesignExperiment(Integer
											.parseInt(strData[7]));// エンデザ実験(奇数)のコマ数
							teacherData
									.setNumOfEvenEngineeringDesignExperiment(Integer
											.parseInt(strData[8]));// エンデザ実験(偶数)のコマ数
							teacherData
									.setNumOfEleInfoCommunicationExperiment(Integer
											.parseInt(strData[9]));// エレ情実験のコマ数
							teacherData
									.setNumOfEnergyEnvironmentExperiment(Integer
											.parseInt(strData[10]));// エネ環実験のコマ数
							teacherData.setNumOfGraduateStudySeminar(Integer
									.parseInt(strData[11]));// 卒研ゼミのコマ数
							teacherData.setHasGraduateStudy(Integer
									.parseInt(strData[12]));// 卒研ありか
							teacherData.setLab(strData[13]);// 研究室

							for (int subjectNum = TEACHER_DATA; subjectNum < strData.length; subjectNum++) {

								// 担当科目があるとき
								if (strData[subjectNum] != null) {
									teacherData
											.addChargeOfSubject(strData[subjectNum]);// 担当科目を追加
								}

								// 担当科目がないとき
								else {
									// teacherData.addChargeOfSubject("科目なし");
									break;
								}

							}
							f_TeacherData.add(teacherData);// 先生のデータの動的配列に追加
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
	 * 結果のファイルを書き込む
	 */
	private void writeResultFile() {
		// PrintWriter output;
		// output = FileIO.writeFile(RESULT_FILE_NAME, false);
		//
		// System.out.println("プログラムを終了しました。" + RESULT_FILE_NAME + "に書き込みます。");
		//
		// // 前期の時間割作成の結果を表示
		// for (int day = 0; day < MAX_DAY; day++) {
		//
		// for (int period = 0; period < MAX_PERIOD; period++) {
		//
		// for (int number = 0; number < f_PreviousTimeTables[day][period]
		// .getClassesOfGradesSize(); number++) {
		// output.print(f_PreviousTimeTables[day][period]
		// .getClassesOfGrades(number).getCourseOrClass()
		// + ",");
		// output.print(f_PreviousTimeTables[day][period]
		// .getClassesOfGrades(number).getSubject() + ",");
		// output.print(f_PreviousTimeTables[day][period]
		// .getClassesOfGrades(number).getTeacher() + ",");
		// output.print(f_PreviousTimeTables[day][period]
		// .getClassesOfGrades(number).getClassRoom() + ",");
		// }
		// }
		// output.println();
		// }
		// output.close();
		// System.out.println("書き込みを終了しました。");

		// writeFile3();// 3次のファイル(担当者決定のファイル)を書き込む
	}

	/*
	 * 担当者が決まった3次のファイルを書き込む
	 */
	private void writeFacultyFile3() {
		PrintWriter output;
		output = FileIO.writeFile(FACULTY3_NAME, false);

		System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "に書き込みます。");

		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {
			output.print(f_ClassOfGradeData3.get(number).getNumber() + ",");// コマ数
			output.print(f_ClassOfGradeData3.get(number).getGrade() + ",");// 学年
			output.print(f_ClassOfGradeData3.get(number).getSemester()
					+ ",");// 前期後期
			output.print(f_ClassOfGradeData3.get(number).getSubject() + ",");// 科目名
			output.print(f_ClassOfGradeData3.get(number).getTeachers()
					.getName()
					+ ",");// 担当教員
			output.println();
		}

		output.close();
		System.out.println("担当者が決まった3次のファイル" + FACULTY3_NAME + "の書き込みを終了しました。");
	}

	// /*
	// * ファイルを読み込む
	// */
	// private void readFile() {
	//
	// readFile1();// 1次のファイルを読み込む
	//
	// readFile2();// 2次のファイルを読み込む
	//
	// readFile3();// 3次のファイルを読み込む
	// }

	// /*
	// * 1次のファイルを読み込む
	// */
	// private void readFile1() {
	//
	// String[] strData = new String[ORDER1_DATA];
	//
	// BufferedReader input = FileIO.readFile(FILE1_NAME);
	//
	// ClassOfGrade classOfGradeData = new ClassOfGrade();
	//
	// try {
	// String line = new String();
	//
	// // 一番上から一番下の行まで読み込む
	// for (int cols = 0; cols < ORDER1_COLS; cols++) {
	//
	// // 読み込んだ1行が空白でないとき
	// if ((line = input.readLine()) != null) {
	//
	// // 上から1行以上のとき
	// if (1 <= cols) {
	// strData = line.split(",");
	//
	// // 曜日が空白でないとき
	// if (strData[0] != null) {
	// classOfGradeData.setDayOfWeek(strData[0]);// 曜日
	// classOfGradeData.setPeriod(Integer
	// .parseInt(strData[1]));// 限
	// classOfGradeData.getClassOfGrade().setNumber(
	// Integer.parseInt(strData[2]));// コマ数
	// classOfGradeData
	// .setGrade(Integer.parseInt(strData[3]));// 学年
	// classOfGradeData.getClassOfGrade()
	// .setPreviousOrLatter(strData[4]);// 前期後期
	// classOfGradeData.getClassOfGrade().setSubject(
	// strData[5]);// 科目名
	// classOfGradeData.getClassOfGrade().setTeacher(
	// strData[6]);// 担当教員
	// classOfGradeData.getClassOfGrade().setClassRoom(
	// strData[7]);// 教室
	//
	// f_ClassOfGradeData1.add(classOfGradeData);// 1次の時間割の動的配列に追加
	// }
	// }
	// }
	// }
	// input.close();
	// } catch (IOException error_report) {
	// System.out.println(error_report);
	// }
	// }
	//
	// /*
	// * 2次のファイルを読み込む
	// */
	// private void readFile2() {
	//
	// String[] strData = new String[ORDER2_DATA];
	//
	// BufferedReader input = FileIO.readFile(FILE2_NAME);
	//
	// TimeTable timeTableData = new TimeTable();
	//
	// try {
	// String line = new String();
	//
	// // 一番上から一番下の行まで読み込む
	// for (int cols = 0; cols < ORDER2_COLS; cols++) {
	//
	// // 読み込んだ1行が空白でないとき
	// if ((line = input.readLine()) != null) {
	//
	// // 上から1行以上のとき
	// if (1 <= cols) {
	// strData = line.split(",");
	//
	// // System.out.print(cols+"行目\t");
	// // System.out.print(strData[0]);
	// // System.out.println(strData[1] + "限目");
	// timeTableData.setDayOfWeek(strData[0]);// 曜日
	// timeTableData.setPeriod(Integer.parseInt(strData[1]));// 限
	// timeTableData.getClassOfGrade().setNumber(
	// Integer.parseInt(strData[2]));// コマ数
	// timeTableData.setGrade(Integer.parseInt(strData[3]));// 学年
	// timeTableData.getClassOfGrade().setPreviousOrLatter(
	// strData[4]);// 前期後期
	// timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名
	// timeTableData.getClassOfGrade().setTeacher(strData[6]);// 担当教員
	// timeTableData.getClassOfGrade()
	// .setClassRoom(strData[7]);// 教室
	//
	// f_TimeTableData2.add(timeTableData);// 2次の時間割の動的配列に追加
	//
	// }
	// }
	// }
	// input.close();
	// } catch (IOException error_report) {
	// System.out.println(error_report);
	// }
	// }
	//
	/*
	 * 3次の教科のファイルを読み込む
	 */
	private void readSubjectFile3() {

		String[] strData = new String[ORDER3_DATA - 2];

		BufferedReader input = FileIO.readFile(SUBJECT3_NAME);

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
						classOfGradeData.setSemester(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.setCourseOrClass(strData[4]);// コース・クラス
						classOfGradeData
								.setNeedPC(Integer.parseInt(strData[5]));// PCが必要か

						f_ClassOfGradeData3.add(classOfGradeData);// 3次の時間割の動的配列に追加
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
		readTeacherFile();// 先生のファイルを読み込む
		readSubjectFile3();// 3次の科目のファイルを読み込む
		decideFirstFaculty();// 最初の担当者をランダムで決定
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
//		writeFacultyFile3();// 担当者決定のファイルを書き込む
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
