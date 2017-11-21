package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/*
 * 何曜日と何限目を決めるためのクラス
 * @author Nakata
 */
public class Decide_dayAndPeriod extends Decide_faculty implements iDayPeriod {

	private static final String EVALUATION_FILE = "DayPeriodEvaluationData.csv";// 日程の評価値のデータのファイル
	private static final int CANDIDATE_NUM = 3;// 候補の数

	// private ArrayList<TimeTable>[]f_CandidateTimeTables3=new
	// ArrayList[CANDIDATE_NUM];
	// private TimeTable[][] f_CandidateTimeTableData3 = new
	// TimeTable[CANDIDATE_NUM][ORDER3_COLS];
	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 前期の時間割
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 後期の時間割
	private TimeTable f_TimeTableDataTmp1;// 時間割のデータの交換1
	private TimeTable f_TimeTableDataTmp2;// 時間割のデータの交換2
	private double f_OldEvaluationValue;// 前の評価値
	private double f_NewEvaluationValue;// 新しい評価値
	private String f_RandomDayOfWeek1;// ランダムの曜日
	private int f_RandomPeriod1;// ランダムの限目
	private int f_RandomGrade1;// ランダムの学年
	private int f_RandomPreviousOrLatter;// 前期か後期か
	private String f_RandomDayOfWeek2;// ランダムの曜日
	private int f_RandomPeriod2;// ランダムの限目
	private int f_CandidateRandomNumber1;// ランダムの候補の要素番号
	private int f_RandomCrossNumber1;// 交叉する要素番号1
	private int f_RandomCrossNumber2;// 交叉する要素番号2
	private int f_CandidateRandomNumber2;// 交換用の番目2
	private int f_CandidateMutationNumber;// 突然変異の候補の番号
	private String f_TmpDayOfWeek;
	private int f_TmpPeriod;
	private int f_MutationNumber;// 突然変異の番号
	private String f_TmpMutationDayOfWeek;// 突然変異の曜日
	private int f_TmpMutationPeriod;// 突然変異の限目
	private ArrayList<TimeTable> f_NewTimeTableData3 = new ArrayList<TimeTable>();// 新規の3次の時間割のデータ
	private ArrayList<Double> f_EvaluationValues = new ArrayList<Double>();// 評価値の動的配列
	private ArrayList<Evaluation> f_EvaluationData = new ArrayList<Evaluation>();// 評価値のデータ
	private ArrayList<Integer> f_MutationCandidateNumbers = new ArrayList<Integer>();// 突然変異の候補番号の動的配列
	private ArrayList<Integer> f_MutationNumbers = new ArrayList<Integer>();// 突然変異の要素番号の動的配列
	private ArrayList<String> f_MutationDayOfWeeks = new ArrayList<String>();// 突然変異の曜日の動的配列
	private ArrayList<Integer> f_MutationPeriods = new ArrayList<Integer>();// 突然変異の限目の動的配列
	// private ArrayList<ArrayList<TimeTable>> f_CandidateTimeTables3 = new
	// ArrayList<ArrayList<TimeTable>>();// 候補の時間割

	private ArrayList<TimeTable>[] f_CandidateTimeTableData3 = new ArrayList[CANDIDATE_NUM];// 候補の時間割
	private ArrayList<TimeTable>[] f_CandidateNewTimeTableData3 = new ArrayList[CANDIDATE_NUM];// 新しく生成された候補の時間割

	protected ArrayList<TimeTable> f_TimeTableData1 = new ArrayList<TimeTable>();// 1次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData2 = new ArrayList<TimeTable>();// 2次の時間割のデータ
	protected ArrayList<TimeTable> f_TimeTableData3 = new ArrayList<TimeTable>();// 3次の時間割のデータ

	/*
	 * コンストラクタ
	 */
	public Decide_dayAndPeriod() {
		super();
		f_TimeTableDataTmp1 = new TimeTable();
		f_TimeTableDataTmp2 = new TimeTable();

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {
			f_CandidateTimeTableData3[candidate] = new ArrayList<TimeTable>();
			f_CandidateNewTimeTableData3[candidate] = new ArrayList<TimeTable>();
		}
	}

	/*
	 * データの表示
	 */
	private void indicateData() {

		System.out.println("1次のデータ");

		for (int number = 0; number < f_TimeTableData1.size(); number++) {
			System.out.print(f_TimeTableData1.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData1.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getSemester()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.println(f_TimeTableData1.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		System.out.println("\n2次のデータ");

		for (int number = 0; number < f_TimeTableData2.size(); number++) {
			System.out.print(f_TimeTableData2.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData2.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getSemester()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.println(f_TimeTableData2.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {

			System.out.println("候補" + (candidate + 1) + "の3次のデータ");

			for (int number = 0; number < f_CandidateTimeTableData3[candidate]
					.size(); number++) {

				System.out.print(f_CandidateTimeTableData3[candidate].get(
						number).getDayOfWeek()
						+ "曜日、");// 曜日
				System.out.print(f_CandidateTimeTableData3[candidate].get(
						number).getPeriod()
						+ "限目、");// 限目
				System.out.print(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getNumber()
						+ "コマ、");// コマ数
				System.out.print(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getGrade()
						+ "年、");// 学年
				System.out.print(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getSemester()
						+ "、");// 前期・後期
				System.out.print(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getSubject()
						+ "、");// 科目名
				System.out.print(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getTeachers().getName()
						+ "先生、");// 担当教員
				System.out.println(f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getCourseOrClass()
						+ "クラス\n");// コース・クラス
			}
		}
	}

	/*
	 * 3次の時間割を表示する
	 */
	private void indicateTimeTableData3() {

		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			System.out.println("\n候補" + (candidate + 1) + "の3次のデータ");

			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

				System.out.print("候補" + (candidate + 1) + "," + number + "番、");

				System.out.print(f_CandidateNewTimeTableData3[candidate].get(
						number).getDayOfWeek()
						+ "曜日、");// 曜日
				System.out.print(f_CandidateNewTimeTableData3[candidate].get(
						number).getPeriod()
						+ "限目、");// 限目
				System.out.print(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getNumber()
						+ "コマ、");// コマ数
				System.out.print(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getGrade()
						+ "年、");// 学年
				System.out.print(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getSemester()
						+ "、");// 前期・後期
				System.out.print(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getSubject()
						+ "、");// 科目名
				System.out.print(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getTeachers().getName()
						+ "先生、");// 担当教員
				System.out.println(f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getCourseOrClass()
						+ "クラス\n");// コース・クラス
			}
		}
	}

	/*
	 * 担当教員のコマ数の表示
	 */
	private void indicateTeacherDayNum() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			System.out.println(f_TeacherData.get(teacherNum).getName() + "先生");

			for (int semester = 0; semester <= MAX_SEMESTER; semester++) {

				System.out.print(ClassOfGrade.changeValueToSemester(semester));

				for (int day = 0; day <= MAX_DAY; day++) {
					System.out.print(TimeTable.changeValueToDay(day)
							+ "曜日："
							+ f_TeacherData.get(teacherNum).getDayNumber(
									semester, day) + "コマ ");
				}

				System.out.println();
			}
			System.out.println();
		}
	}

	/*
	 * 担当教員の曜日のコマ数をリセット
	 */
	private void resetTeacherDayNum() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
			f_TeacherData.get(teacherNum).resetDayNumber();
		}
	}

	// ------------------------------------------------------//
	// ---------------------評価値---------------------------//
	// ------------------------------------------------------//

	/*
	 * より大きい曜日と限目の評価値かを取得
	 * 
	 * @return true より大きい
	 * 
	 * @return false 以下
	 */
	private boolean isBiggerDayPeriodEvaluationValue() {

		// resetTeacherDayNum();

		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			double value = f_FacultyEvaluationValue;

			// 候補の時間割に関して
			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

				// 時間割と重複するとき
				if (checkDuplication(candidate, number)) {

					if (DEBUG) {
						System.out.println("既存の時間割と重複");
					}
					return false;
				}

				// 限目に関しての評価
				for (int addPeriod = 0; addPeriod < f_CandidateNewTimeTableData3[candidate]
						.get(number).getClassOfGrade().getNumber(); addPeriod++) {

					// 1限目または5限目のとき
					if (f_CandidateNewTimeTableData3[candidate].get(number)
							.getPeriod() + addPeriod <= 1
							|| 5 <= f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod() + addPeriod) {

						value -= 10.0;
					}

					// 2限目～4限目のとき
					else {
						value += 100.0;
					}
				}

				value += getValueDayPeriodFromTimeTableData1(candidate, number);
			}

			f_EvaluationValues.set(candidate, value);
		}

		f_NewEvaluationValue = getSumEvaluationValue();// 評価値の合計を取得

		// f_NewEvaluationValue += f_OldEvaluationValue;// 評価値が動くかどうかお試し用

		if (DEBUG) {
			System.out.println("子の評価値の合計:" + f_NewEvaluationValue);// 子の評価値の合計を表示
		}

		// 子の評価値が親の評価値より大きいとき
		if (f_OldEvaluationValue < f_NewEvaluationValue) {

			f_OldEvaluationValue = f_NewEvaluationValue;// 評価値を更新

			return true;
		}

		// 新しい評価値が親の評価値以下のとき
		else {
			return false;
		}
	}

	/*
	 * 1次の科目から曜日・限目の評価をプラスにするか
	 * 
	 * @return true プラスする
	 * 
	 * @return false マイナスする
	 */
	private double getValueDayPeriodFromTimeTableData1(int candidate, int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					f_CandidateNewTimeTableData3[candidate].get(number)
							.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getSemester(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_CandidateNewTimeTableData3[candidate]
							.get(number).getClassOfGrade().getGrade()) {

						// 実験科目のとき
						if (f_TimeTableData1.get(number1).getClassOfGrade()
								.getSubject().contains("実験")) {

							// コース・クラスと重複するとき
							if (checkDuplicationCouseOrClass(f_TimeTableData1
									.get(number1).getClassOfGrade()
									.getCourseOrClass(),
									f_CandidateNewTimeTableData3[candidate]
											.get(number).getClassOfGrade()
											.getCourseOrClass())) {

								return -10.0;
							}

							else {
								return 100.0;
							}
						}
					}
				}
			}
		}

		return 0.0;
	}

	/*
	 * 曜日限目の最初の評価値の計算をする
	 */
	private void calcDayPeriodFirstEvaluationValue() {

		// resetTeacherDayNum();

		double value = f_FacultyEvaluationValue;

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {

			for (int number = 0; number < f_CandidateTimeTableData3[candidate]
					.size(); number++) {

				for (int addPeriod = 0; addPeriod < f_CandidateTimeTableData3[candidate]
						.get(number).getClassOfGrade().getNumber(); addPeriod++) {

					// 1限目または5限目のとき
					if (f_CandidateTimeTableData3[candidate].get(number)
							.getPeriod() + addPeriod <= 1
							|| 5 <= f_CandidateTimeTableData3[candidate].get(
									number).getPeriod()
									+ addPeriod) {

						value -= 20.0;
					}

					// 2限目～4限目のとき
					else {
						value += 40.0;
					}
				}
			}

			f_EvaluationValues.add(value);
		}
	}

	/*
	 * 評価値を取得
	 * 
	 * @param candidate 候補の番号
	 * 
	 * @return 指定した候補の時間割の評価値
	 */
	private double getEvaluationValue(int candidate) {

		resetTeacherDayNum();

		double value = 0.0;

		// value += f_EvaluationValues.get(candidate);

		// それぞれの担当教員がそれぞれの曜日に何コマ入っているか調べる
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			// 1次の時間割での曜日のコマ数をカウント
			for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData1.get(number1).getClassOfGrade()
								.getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable.changeDayToValue(f_TimeTableData1.get(
							number1).getDayOfWeek());
					int semester = ClassOfGrade
							.changeSemesterToValue(f_TimeTableData1
									.get(number1).getClassOfGrade()
									.getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(
							semester,
							day,
							f_TeacherData.get(teacherNum).getDayNumber(
									semester, day)
									+ f_TimeTableData1.get(number1)
											.getClassOfGrade().getNumber());

				}
			}

			// 2次の時間割での曜日のコマ数をカウント
			for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData2.get(number2).getClassOfGrade()
								.getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable.changeDayToValue(f_TimeTableData2.get(
							number2).getDayOfWeek());

					int semester = ClassOfGrade
							.changeSemesterToValue(f_TimeTableData2
									.get(number2).getClassOfGrade()
									.getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(
							semester,
							day,
							f_TeacherData.get(teacherNum).getDayNumber(
									semester, day)
									+ f_TimeTableData2.get(number2)
											.getClassOfGrade().getNumber());

				}
			}

			// 3次の時間割での曜日のコマ数をカウント
			for (int number3 = 0; number3 < f_CandidateNewTimeTableData3[candidate]
					.size(); number3++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_CandidateNewTimeTableData3[candidate].get(number3)
								.getClassOfGrade().getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable
							.changeDayToValue(f_CandidateNewTimeTableData3[candidate]
									.get(number3).getDayOfWeek());

					if (day < 0) {
						System.out.println("候補" + candidate + "の" + number3
								+ "番目の曜日が" + "不明");
						indicateTimeTableData3();
						InOutPut.anyKey();
					}

					int semester = ClassOfGrade
							.changeSemesterToValue(f_CandidateNewTimeTableData3[candidate]
									.get(number3).getClassOfGrade()
									.getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(
							semester,
							day,
							f_TeacherData.get(teacherNum).getDayNumber(
									semester, day)
									+ f_CandidateNewTimeTableData3[candidate]
											.get(number3).getClassOfGrade()
											.getNumber());

				}
			}

			for (int semester = 0; semester <= MAX_SEMESTER; semester++) {

				// カウントしたコマ数をもとに評価値を計算
				for (int day = 0; day <= MAX_DAY; day++) {

					if (4 <= f_TeacherData.get(teacherNum).getDayNumber(
							semester, day)) {
						value -= (double) f_TeacherData.get(teacherNum)
								.getDayNumber(semester, day);
					}
				}
			}
		}

		return value;
	}

	/*
	 * 評価値の合計を取得
	 * 
	 * @return 評価値の合計
	 */
	private double getSumEvaluationValue() {

		double value = 0.0;

		for (int number = 0; number < f_EvaluationValues.size(); number++) {

			value += f_EvaluationValues.get(number);
		}

		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			value += getEvaluationValue(candidate);
		}

		return value;

	}

	/*
	 * 評価値の計算をする
	 */
	private void calcEvaluationValue() {

		boolean canEvaluate = true;// 評価できるか

		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

				// 時間割と重複するとき
				if (checkDuplication(candidate, number)) {
					f_NewEvaluationValue = 0.0;
					canEvaluate = false;
					break;
				}
			}
		}

		// 評価できるとき
		if (canEvaluate) {

			if (DEBUG) {
				System.out.println("評価する");
			}

			// calcDayPeriodEvaluationValue();// 評価値の計算をする

			f_NewEvaluationValue = getSumEvaluationValue();// 評価値の合計を取得

		}
	}

	/*
	 * もっとも評価値が高い時間割の番号を取得
	 */
	private int getBestTimeTable() {

		double biggestNum = 0.0;
		int bestNum = 0;

		for (int number = 0; number < f_EvaluationValues.size(); number++) {

			// より高い評価値があるとき
			if (biggestNum < f_EvaluationValues.get(number)) {
				biggestNum = f_EvaluationValues.get(number);// 評価値を更新
				bestNum = number;
			}
		}

		return bestNum;
	}

	/*
	 * 指定した要素番号までの評価値の合計
	 */
	private double getSumEvaluationValue(int num) {

		if (num < 0) {
			return 0.0;
		}

		double sum = f_EvaluationValues.get(num);

		return sum + getSumEvaluationValue(num - 1);

	}

	// ------------------------------------------------------//
	// ---------------------遺伝的アルゴリズム---------------//
	// ------------------------------------------------------//
	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3() {

		super.calctTeacherEvaluationValue();// 担当者決定の評価値を計算する

		setClassOfGrade3ToFirstTimeTable3();// 3次の学年ごとの授業を最初の時間割にセットする

		if (DEBUG) {
			writeRoomAndDayAndPeriodFirstFile3();
		}

		indicateTeacherDayNum();
		indicateTimeTableData3();
		InOutPut.anyKey();

		 exeGeneticAlgo();// 遺伝的アルゴリズムを実行する

		indicateTeacherDayNum();// 担当教員のコマ数を表示

//		System.out
//				.println("評価値が更新された回数：" + (f_EvaluationData.size() - 1) + "回");
	}

	/*
	 * 遺伝的アルゴリズムを実行する
	 */
	private void exeGeneticAlgo() {

		int updateNum=0;
		
		for (int num = 0; num < CHECK_NUM; num++) {
			Evaluation evaluation = new Evaluation();

			// TimeTable tmpTimeTable = new TimeTable();

			boolean wasMutated = false;

			System.out.println("遺伝的アルゴリズム" + (num + 1) + "回目");

			evaluation.setGeneration(num + 1);// num世代目

			rouletteChoice(CANDIDATE_NUM);// どの時間割かをCANDIDATE_NUM個ルーレット選択する

			doCirculationCross();// 循環交叉

			// mutation();// 突然変異

			// 突然変異するとき
			if (mutation()) {
				wasMutated = true;
			}

			// 新しい評価値が上のとき
			if (isBiggerDayPeriodEvaluationValue()) {

				// // 候補の時間割を更新
				// for (int candidate = 0; candidate <
				// f_CandidateNewTimeTableData3.length; candidate++) {
				//
				// for (int number = 0; number <
				// f_CandidateNewTimeTableData3[candidate]
				// .size(); number++) {
				//
				// String tmpDayOfWeek = f_CandidateNewTimeTableData3[candidate]
				// .get(number).getDayOfWeek();
				// int tmpPeriod = f_CandidateNewTimeTableData3[candidate]
				// .get(number).getPeriod();
				//
				// // 曜日を更新
				// f_CandidateTimeTableData3[candidate].get(number)
				// .setDayOfWeek(tmpDayOfWeek);
				//
				// // 限目を更新
				// f_CandidateTimeTableData3[candidate].get(number)
				// .setPeriod(tmpPeriod);
				//
				// }
				// }

				resetCandidateTimeTableData3(false);

				if (DEBUG) {
					System.out.println("評価値が更新された。");
					System.out.println("評価値:" + f_OldEvaluationValue);
				}

				updateNum++;
//				evaluation.setEvaluationValue(f_OldEvaluationValue);
//
//				f_EvaluationData.add(evaluation);
			}

			// 新しい評価値以下のとき
			else {

				if (wasMutated == true) {

					for (int mutationNum = f_MutationDayOfWeeks.size() - 1; mutationNum >= 0; mutationNum--) {
						// int mutationCandidateNumber =
						// f_MutationCandidateNumbers.get(mutationNum);
						// int mutationNumber =
						// f_MutationNumbers.get(mutationNum);
						// String dayOfWeek =
						// f_MutationDayOfWeeks.get(mutationNum);
						// int period = f_MutationPeriods.get(mutationNum);

						f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
								.get(mutationNum)].get(
								f_MutationNumbers.get(mutationNum))
								.setDayOfWeek(
										f_MutationDayOfWeeks.get(mutationNum));
						f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
								.get(mutationNum)].get(
								f_MutationNumbers.get(mutationNum)).setPeriod(
								f_MutationPeriods.get(mutationNum));
					}
				}

				// // 元の候補に戻す
				// for (int candidate = 0; candidate <
				// f_CandidateTimeTableData3.length; candidate++) {
				//
				// for (int number = 0; number <
				// f_CandidateTimeTableData3[candidate]
				// .size(); number++) {
				//
				// String tmpDayOfWeek = f_CandidateTimeTableData3[candidate]
				// .get(number).getDayOfWeek();
				//
				// int tmpPeriod = f_CandidateTimeTableData3[candidate]
				// .get(number).getPeriod();
				//
				// if (tmpDayOfWeek == null) {
				// System.out.println(tmpDayOfWeek + "曜日");
				// InOutPut.anyKey();
				// }
				//
				// // 曜日を戻す
				// f_CandidateNewTimeTableData3[candidate].get(number)
				// .setDayOfWeek(tmpDayOfWeek);
				//
				// // 限目を戻す
				// f_CandidateNewTimeTableData3[candidate].get(number)
				// .setPeriod(tmpPeriod);
				// }
				// }
				resetCandidateTimeTableData3(true);
			}
			
			evaluation.setEvaluationValue(f_OldEvaluationValue);

			f_EvaluationData.add(evaluation);
		}
		
		System.out
		.println("評価値が更新された回数：" + updateNum + "回");
		InOutPut.anyKey();
	}

	/*
	 * 元の候補の時間割にリセットする
	 * 
	 * @param doReset リセットするか
	 */
	private void resetCandidateTimeTableData3(boolean doReset) {

		TimeTable tmpTimeTable = new TimeTable();

		// リセットするとき
		if (doReset == true) {

			// 元の候補に戻す
			for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {

				for (int number = 0; number < f_CandidateTimeTableData3[candidate]
						.size(); number++) {

					// oldDayOfWeek = f_CandidateTimeTableData3[candidate].get(
					// number).getDayOfWeek();

					tmpTimeTable
							.setDayOfWeek(f_CandidateTimeTableData3[candidate]
									.get(number).getDayOfWeek());

					tmpTimeTable.setPeriod(f_CandidateTimeTableData3[candidate]
							.get(number).getPeriod());
					// oldPeriod = f_CandidateTimeTableData3[candidate]
					// .get(number).getPeriod();

					// newDayOfWeek =
					// f_CandidateNewTimeTableData3[candidate].get(
					// number).getDayOfWeek();
					//
					// newPeriod = f_CandidateNewTimeTableData3[candidate].get(
					// number).getPeriod();

					// 曜日を戻す
					f_CandidateNewTimeTableData3[candidate].get(number)
							.setDayOfWeek(tmpTimeTable.getDayOfWeek());

					// // 曜日を戻す
					// f_CandidateNewTimeTableData3[candidate].get(number)
					// .setDayOfWeek(oldDayOfWeek);
					// if (Objects.equals(oldDayOfWeek, newDayOfWeek) == false)
					// {
					//
					// System.out.print("ちがう曜日");
					// InOutPut.anyKey();
					//
					// }

					// 限目を戻す
					f_CandidateNewTimeTableData3[candidate].get(number)
							.setPeriod(tmpTimeTable.getPeriod());
					// // 限目を戻す
					// f_CandidateNewTimeTableData3[candidate].get(number)
					// .setPeriod(oldPeriod);
					// if (oldPeriod != newPeriod) {
					//
					// System.out.print("ちがう限目");
					// InOutPut.anyKey();
					//
					// }

					// // 時間割と重複するとき
					// if (checkDuplication(candidate, number)) {
					//
					// indicateTimeTableData3();
					//
					// System.out.println("候補" + (candidate + 1) + "の"
					// + number + "番の時間割と重複");
					// System.out.println("既存の時間割"
					// + tmpTimeTable.getDayOfWeek() + "曜日"
					// + tmpTimeTable.getPeriod() + "限目");
					// // System.out.println("新規の時間割" + newDayOfWeek + "曜日"
					// // + newPeriod + "限目");
					//
					// InOutPut.anyKey();
					// }
				}
			}
		}

		// リセットしないとき(更新するとき)
		else {

			// 時間割を更新する
			for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {

				for (int number = 0; number < f_CandidateTimeTableData3[candidate]
						.size(); number++) {

					// oldDayOfWeek = oldDayOfWeek.replaceAll(oldDayOfWeek,
					// f_CandidateTimeTableData3[candidate].get(number)
					// .getDayOfWeek());
					//
					// oldPeriod = f_CandidateTimeTableData3[candidate]
					// .get(number).getPeriod();

					// newDayOfWeek = newDayOfWeek.replaceAll(newDayOfWeek,
					// f_CandidateNewTimeTableData3[candidate].get(number)
					// .getDayOfWeek());
					//
					// newPeriod = f_CandidateNewTimeTableData3[candidate].get(
					// number).getPeriod();

					// if (newDayOfWeek == null) {
					// System.out.println(newDayOfWeek + "曜日");
					// InOutPut.anyKey();
					// }

					tmpTimeTable
							.setDayOfWeek(f_CandidateNewTimeTableData3[candidate]
									.get(number).getDayOfWeek());

					tmpTimeTable
							.setPeriod(f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod());

					// 曜日を更新
					f_CandidateTimeTableData3[candidate].get(number)
							.setDayOfWeek(tmpTimeTable.getDayOfWeek());

					// 限目を更新
					f_CandidateTimeTableData3[candidate].get(number).setPeriod(
							tmpTimeTable.getPeriod());

					// if (Objects.equals(oldDayOfWeek, newDayOfWeek) == false)
					// {
					// System.out.print("ちがう曜日");
					// InOutPut.anyKey();
					// // 曜日を更新
					// f_CandidateTimeTableData3[candidate].get(number)
					// .setDayOfWeek(newDayOfWeek);
					// }
					//
					// if (oldPeriod != newPeriod) {
					//
					// System.out.print("ちがう限目");
					// InOutPut.anyKey();
					//
					// // 限目を更新
					// f_CandidateTimeTableData3[candidate].get(number)
					// .setPeriod(newPeriod);
					// }
				}
			}
		}
	}

	/*
	 * どの時間割を選ぶかをルーレット選択
	 * 
	 * @param num 選択する数
	 */
	private void rouletteChoice(int num) {

		System.out.println("ルーレット選択");

		double sum = f_OldEvaluationValue;// 合計の評価値を取得

		TimeTable tmpTimeTable = new TimeTable();

		for (int candidate = 0; candidate < num; candidate++) {

			double randomNum = Calculation.getDRnd(0.0, sum);// 0.0から候補の数だけの評価値の合計までランダムで選ぶ

			for (int number = 0; number < f_EvaluationValues.size(); number++) {

				// 候補の時間割のとき
				if (getSumEvaluationValue(number - 1) <= randomNum
						&& randomNum <= getSumEvaluationValue(number)) {
					f_CandidateRandomNumber1 = number;
					break;
				}
			}

			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

				tmpTimeTable
						.setDayOfWeek(f_CandidateTimeTableData3[f_CandidateRandomNumber1]
								.get(number).getDayOfWeek());
				tmpTimeTable
						.setPeriod(f_CandidateTimeTableData3[f_CandidateRandomNumber1]
								.get(number).getPeriod());

				f_CandidateNewTimeTableData3[candidate].get(number)
						.setDayOfWeek(tmpTimeTable.getDayOfWeek());
				f_CandidateNewTimeTableData3[candidate].get(number).setPeriod(
						tmpTimeTable.getPeriod());
			}
		}
	}

	/*
	 * 循環交叉する
	 */
	private void doCirculationCross() {

		System.out.println("循環交叉");

		int elementNumber2, elementNumber1;// 要素番号

		// 交叉させる候補の番号をランダムで選ぶ
		f_CandidateRandomNumber1 = Calculation.getRnd(0,
				f_CandidateNewTimeTableData3.length - 1);

		// 交叉させる要素番号をランダムで選ぶ
		f_RandomCrossNumber1 = Calculation
				.getRnd(0,
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
								.size() - 1);

		// 違う候補番号が選ばれない限り繰り返す
		do {

			// 交叉させる候補の番号をランダムで選ぶ
			f_CandidateRandomNumber2 = Calculation.getRnd(0,
					f_CandidateNewTimeTableData3.length - 1);

		} while (f_CandidateRandomNumber2 == f_CandidateRandomNumber1);

		String tmpDayOfWeek = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
				.get(f_RandomCrossNumber1).getDayOfWeek();

		int tmpPeriod = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
				.get(f_RandomCrossNumber1).getPeriod();

		if (DEBUG) {
			System.out.println("交換前");
			System.out
					.println(f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
							.get(f_RandomCrossNumber1).getDayOfWeek()
							+ "曜日と"
							+ f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
									.get(f_RandomCrossNumber1).getDayOfWeek()
							+ "曜日");
		}

		f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
				f_RandomCrossNumber1).setDayOfWeek(
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						f_RandomCrossNumber1).getDayOfWeek());

		f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
				f_RandomCrossNumber1).setDayOfWeek(tmpDayOfWeek);

		f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
				f_RandomCrossNumber1).setPeriod(
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						f_RandomCrossNumber1).getPeriod());

		f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
				f_RandomCrossNumber1).setPeriod(tmpPeriod);

		if (DEBUG) {
			System.out.println("交換後");
			System.out
					.println(f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
							.get(f_RandomCrossNumber1).getPeriod()
							+ "限目と"
							+ f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
									.get(f_RandomCrossNumber1).getPeriod()
							+ "限目");

		}

		// 候補1の中に候補2の曜日と限目が同じものがないかを探す
		elementNumber1 = getInTimeTable3Number(
				f_CandidateRandomNumber1,
				f_RandomDayOfWeek2,
				f_RandomPeriod2,
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getClassOfGrade()
						.getSemester(), false);

		// 候補2の中に候補1の曜日と限目が同じものがないかを探す
		elementNumber2 = getInTimeTable3Number(
				f_CandidateRandomNumber2,
				f_RandomDayOfWeek1,
				f_RandomPeriod1,
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getClassOfGrade()
						.getSemester(), false);

		// 両方見つかった場合(計4つの要素番号)
		if (0 <= elementNumber2 && 0 <= elementNumber1) {

			tmpDayOfWeek = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
					.get(elementNumber2).getDayOfWeek();

			tmpPeriod = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
					.get(elementNumber2).getPeriod();

			f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
					elementNumber2).setDayOfWeek(
					f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
							elementNumber1).getDayOfWeek());
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
					elementNumber1).setDayOfWeek(tmpDayOfWeek);

			f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
					elementNumber2).setPeriod(
					f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
							elementNumber1).getPeriod());
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
					elementNumber1).setPeriod(tmpPeriod);

		}

		// 片方見つかる場合
		else {

			// 候補2だけ見つかったとき
			if (0 <= elementNumber2) {
				// tmpTimeTable = new TimeTable();

				tmpDayOfWeek = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(elementNumber2).getDayOfWeek();

				tmpPeriod = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(elementNumber2).getPeriod();

				// 見つかった候補2と同じ要素番号の候補1にコピー
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber2).setDayOfWeek(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
								.get(f_RandomCrossNumber1).getDayOfWeek());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber2).setPeriod(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
								.get(f_RandomCrossNumber1).getPeriod());

				// 曜日を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber2).setDayOfWeek(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
								.get(elementNumber2).getDayOfWeek());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber2).setDayOfWeek(tmpDayOfWeek);

				// 限目を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber2).setPeriod(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
								.get(elementNumber2).getPeriod());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber2).setPeriod(tmpPeriod);
			}

			// 候補1だけ見つかったとき
			else if (0 <= elementNumber1) {

				tmpDayOfWeek = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(elementNumber1).getDayOfWeek();

				tmpPeriod = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(elementNumber1).getPeriod();

				// 見つかった候補1と同じ要素番号の候補2にコピー
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber1).setDayOfWeek(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
								.get(f_RandomCrossNumber1).getDayOfWeek());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber1).setPeriod(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
								.get(f_RandomCrossNumber1).getPeriod());

				// 曜日を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber1).setDayOfWeek(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
								.get(f_RandomCrossNumber1).getDayOfWeek());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber1).setDayOfWeek(tmpDayOfWeek);

				// 限目を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber1).setPeriod(
						f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
								.get(f_RandomCrossNumber1).getPeriod());
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber1).setPeriod(tmpPeriod);
			}
		}
	}

	/*
	 * ランダムの限目を取得
	 */
	private int getRandomPeriod() {

		int randomPeriod = Calculation.getRnd(1, MAX_PERIOD * 4);

		if (1 <= randomPeriod && randomPeriod <= 8) {

			return 1;
		}

		else if (9 <= randomPeriod && randomPeriod <= 16) {
			return 5;
		}

		else if (17 <= randomPeriod) {
			randomPeriod = Calculation.getRnd(2, 4);
		}

		return randomPeriod;
	}

	/*
	 * 既存の時間割のデータの何番目に入っているか
	 * 
	 * @param candidate_number 候補の番号
	 * 
	 * @param day_of_week 曜日
	 * 
	 * @param period 限目
	 * 
	 * @param grade 学年
	 * 
	 * @param semester 学期
	 * 
	 * @param isAllSame すべての要素が同じものだけ探すか
	 * 
	 * @return 既存の時間割のデータの要素番号
	 */
	private int getInTimeTable3Number(int candidate_number, String day_of_week,
			int period, int grade, String semester, boolean isAllSame) {

		// すべての要素が同じものを探す
		for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
				.size(); number++) {

			// ランダムに選択した曜日、限目、学年、前期後期が時間割3のデータに存在するとき
			if (Objects.equals(f_CandidateNewTimeTableData3[candidate_number]
					.get(number).getDayOfWeek(), day_of_week)
					&& f_CandidateNewTimeTableData3[candidate_number].get(
							number).getPeriod() == period
					&& f_CandidateNewTimeTableData3[candidate_number]
							.get(number).getClassOfGrade().getGrade() == grade
					&& Objects.equals(
							f_CandidateNewTimeTableData3[candidate_number]
									.get(number).getClassOfGrade()
									.getSemester(), semester)) {

				if (DEBUG) {
					System.out.println("すべての要素が同じものを発見");
				}
				return number;
			}
		}

		// すべての要素が同じものを探さないとき
		if (isAllSame == false) {

			// 学年だけ違うものを探す
			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
					.size(); number++) {

				// ランダムに選択した曜日、限目、前期後期が時間割3のデータに存在するとき
				if (Objects.equals(
						f_CandidateNewTimeTableData3[candidate_number].get(
								number).getDayOfWeek(), day_of_week)
						&& f_CandidateNewTimeTableData3[candidate_number].get(
								number).getPeriod() == period
						&& Objects.equals(
								f_CandidateNewTimeTableData3[candidate_number]
										.get(number).getClassOfGrade()
										.getSemester(), semester)) {

					if (DEBUG) {
						System.out.println("学年だけ違うものを発見");
					}
					return number;
				}
			}

			// 学年と学期が違うものを探す
			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
					.size(); number++) {

				// ランダムに選択した曜日、限目が時間割3のデータに存在するとき
				if (Objects.equals(
						f_CandidateNewTimeTableData3[candidate_number].get(
								number).getDayOfWeek(), day_of_week)
						&& f_CandidateNewTimeTableData3[candidate_number].get(
								number).getPeriod() == period) {
					if (DEBUG) {
						System.out.println("学年と前期後期が違うものを発見");
					}
					return number;
				}
			}
		}

		if (DEBUG) {
			System.out.println("既存の時間割のデータに存在しません。");
		}

		return -1;
	}

	/*
	 * 交叉する
	 * 
	 * @return true 両方交叉
	 * 
	 * @return false 片方だけ交叉
	 */
	private boolean cross() {

		// 時間割のデータが両方存在のとき
		if (0 <= f_CandidateRandomNumber1) {

			if (DEBUG) {
				System.out.println("両方交叉");
			}

			TimeTable tmpTimeTable = new TimeTable();

			tmpTimeTable.setDayOfWeek(f_TimeTableDataTmp1.getDayOfWeek());
			tmpTimeTable.setPeriod(f_TimeTableDataTmp1.getPeriod());

			f_TimeTableDataTmp1
					.setDayOfWeek(f_TimeTableDataTmp2.getDayOfWeek());
			f_TimeTableDataTmp1.setPeriod(f_TimeTableDataTmp2.getPeriod());

			f_TimeTableDataTmp2.setDayOfWeek(tmpTimeTable.getDayOfWeek());
			f_TimeTableDataTmp2.setPeriod(tmpTimeTable.getPeriod());

			return true;
		}

		// 時間割のデータが片方だけ存在のとき
		else {

			if (DEBUG) {
				System.out.println("片方交叉");
			}

			f_NewTimeTableData3.get(f_CandidateRandomNumber2).setDayOfWeek(
					f_TimeTableDataTmp2.getDayOfWeek());
			f_NewTimeTableData3.get(f_CandidateRandomNumber2).setPeriod(
					f_TimeTableDataTmp2.getPeriod());

			return false;
		}
	}

	/*
	 * 突然変異
	 * 
	 * @return true 突然変異あり
	 * 
	 * @return false 突然変異なし
	 */
	private boolean mutation() {

		boolean wasMutated = false;
		int mutationNum = 0;
		TimeTable tmpTimeTable = new TimeTable();
		f_MutationCandidateNumbers.clear();
		f_MutationNumbers.clear();
		f_MutationDayOfWeeks.clear();
		f_MutationPeriods.clear();

		System.out.println("突然変異");

		// 候補の数において
		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			// 前期と後期それぞれにおいて
			for (int semester = ClassOfGrade.changeSemesterToValue("前期"); semester <= MAX_SEMESTER; semester++) {

				tmpTimeTable.getClassOfGrade().setSemester(
						ClassOfGrade.changeValueToSemester(semester));
				// String strSemester = ClassOfGrade
				// .changeValueToSemester(semester);

				// 月曜～金曜において
				for (int day = TimeTable.changeDayToValue("月"); day <= MAX_DAY; day++) {

					tmpTimeTable.setDayOfWeek(TimeTable.changeValueToDay(day));
					// String strDay = TimeTable.changeValueToDay(day);

					// 1限目～MAX_PERIOD限目において
					for (int period = 1; period <= MAX_PERIOD; period++) {

						tmpTimeTable.setPeriod(period);

						// 1年～MAX_GRADE年において
						for (int grade = 1; grade <= MAX_GRADE; grade++) {

							tmpTimeTable.getClassOfGrade().setGrade(grade);

							// 突然変異させる要素番号を既存の時間割から取得(全ての要素が同じもの)
							int mutationNumber = getInTimeTable3Number(
									candidate, tmpTimeTable.getDayOfWeek(),
									tmpTimeTable.getPeriod(), tmpTimeTable
											.getClassOfGrade().getGrade(),
									tmpTimeTable.getClassOfGrade()
											.getSemester(), true);

							// 既存の時間割に存在するとき
							if (0 <= mutationNumber) {

								int num = Calculation.getRnd(1, 100);

								// (MUTATION_PROBABILITY)/(100)のとき
								if (1 <= num && num <= MUTATION_PROBABLITY) {

									String courseOrClass = f_CandidateNewTimeTableData3[candidate]
											.get(mutationNumber)
											.getClassOfGrade()
											.getCourseOrClass();

									// // コース・クラスをもとにより良い要素番号を取得
									// int bestNumber = getBestNumberTimeTable3(
									// candidate, grade, strSemester,
									// courseOrClass);

									// コース・クラスをもとにより良い要素番号を取得
									int bestNumber = getBestNumberTimeTable3(
											candidate, grade, tmpTimeTable
													.getClassOfGrade()
													.getSemester(),
											courseOrClass);

									// // 突然変異させる候補の番号をランダムで選ぶ
									// f_MutationCandidateNumbers
									// .add(Calculation
									// .getRnd(0,
									// f_CandidateNewTimeTableData3.length -
									// 1));

									f_MutationCandidateNumbers.add(candidate);

									// // 突然変異させる要素番号をランダムで選ぶ
									// f_MutationNumbers
									// .add(Calculation
									// .getRnd(0,
									// f_CandidateNewTimeTableData3[f_CandidateMutationNumber]
									// .size() - 1));

									f_MutationNumbers.add(mutationNumber);

									f_MutationDayOfWeeks
											.add(f_CandidateNewTimeTableData3[candidate]
													.get(mutationNumber)
													.getDayOfWeek());

									f_MutationPeriods
											.add(f_CandidateNewTimeTableData3[candidate]
													.get(mutationNumber)
													.getPeriod());

									if (0 <= bestNumber) {
										tmpTimeTable
												.setDayOfWeek(f_CandidateNewTimeTableData3[candidate]
														.get(bestNumber)
														.getDayOfWeek());
										tmpTimeTable
												.setPeriod(f_CandidateNewTimeTableData3[candidate]
														.get(bestNumber)
														.getPeriod());
									}

									else {

										int randomDayOfWeek = Calculation
												.getRnd(TimeTable
														.changeDayToValue("月"),
														MAX_DAY);
										tmpTimeTable
												.setPeriod(getRandomPeriod());
										tmpTimeTable
												.setDayOfWeek(TimeTable
														.changeValueToDay(randomDayOfWeek));
									}

									// f_MutationDayOfWeeks
									// .add(f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
									// .get(f_MutationCandidateNumbers
									// .size() - 1)]
									// .get(f_MutationNumbers
									// .get(f_MutationNumbers
									// .size() - 1))
									// .getDayOfWeek());
									// f_MutationPeriods
									// .add(f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
									// .get(f_MutationCandidateNumbers
									// .size() - 1)]
									// .get(f_MutationNumbers
									// .get(f_MutationNumbers
									// .size() - 1))
									// .getPeriod());

									// // 曜日を交換
									// f_CandidateNewTimeTableData3[candidate]
									// .get(mutationNumber)
									// .setDayOfWeek(
									// tmpTimeTable.getDayOfWeek());
									//
									// // 限目を交換
									// f_CandidateNewTimeTableData3[candidate]
									// .get(mutationNumber)
									// .setPeriod(tmpTimeTable.getPeriod());

									// 曜日を交換
									f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
											.get(f_MutationCandidateNumbers
													.size() - 1)]
											.get(f_MutationNumbers
													.get(f_MutationNumbers
															.size() - 1))
											.setDayOfWeek(
													tmpTimeTable.getDayOfWeek());

									// 限目を交換
									f_CandidateNewTimeTableData3[f_MutationCandidateNumbers
											.get(f_MutationCandidateNumbers
													.size() - 1)]
											.get(f_MutationNumbers
													.get(f_MutationNumbers
															.size() - 1))
											.setPeriod(tmpTimeTable.getPeriod());

									mutationNum++;
									if (wasMutated == false) {
										wasMutated = true;
									}
								}
							}
						}
					}
				}
			}
		}

		if (wasMutated == true) {
			System.out.println(mutationNum + "回発生");
			return true;
		}

		System.out.println("発生なし");
		return false;
	}

	// TimeTable timeTable = new TimeTable();
	//
	// timeTable.setDayOfWeek(TimeTable.changeValueToDay(randomDayOfWeek));
	// timeTable.setPeriod(randomPeriod);

	// // 曜日を保管
	// f_TmpMutationDayOfWeek =
	// f_CandidateNewTimeTableData3[randomCandidateNumber]
	// .get(mutationNumber).getDayOfWeek();
	//
	// // 限目を保管
	// f_TmpMutationPeriod =
	// f_CandidateNewTimeTableData3[randomCandidateNumber]
	// .get(mutationNumber).getPeriod();
	// int num = Calculation
	// .getRnd(1, ORDER1_COLS + ORDER2_COLS + ORDER3_COLS);// 3次の科目数/全科目数の確率
	//
	// // 1から3次の科目数までのとき
	// if (ORDER3_COLS <= num
	// && num <= ORDER1_COLS + ORDER2_COLS + ORDER3_COLS) {

	//
	// int randomDayOfWeek = Calculation.getRnd(
	// TimeTable.changeDayToValue("月"),
	// MAX_DAY);
	// int randomPeriod = Calculation.getRnd(1,
	// MAX_PERIOD);
	// String tmpDayOfWeek = TimeTable
	// .changeValueToDay(randomDayOfWeek);
	//
	// // TimeTable timeTable = new TimeTable();
	// // timeTable.setDayOfWeek(tmpDayOfWeek);
	// // timeTable.setPeriod(randomPeriod);
	//
	// // 曜日を交換
	// f_CandidateNewTimeTableData3[candidate]
	// .get(mutationNumber).setDayOfWeek(
	// tmpDayOfWeek);
	//
	// // 限目を交換
	// f_CandidateNewTimeTableData3[candidate]
	// .get(mutationNumber).setPeriod(
	// randomPeriod);
	//
	// break;
	// // String courseOrClass =
	// // f_CandidateNewTimeTableData3[candidate]
	// // .get(mutationNumber)
	// // .getClassOfGrade()
	// // .getCourseOrClass();
	// //

	// //
	// // // よい要素番号があるとき
	// // if (0 <= bestNumber) {
	// // // String bestDayOfWeek =
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(bestNumber).getDayOfWeek();
	// // // int bestPeriod =
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(bestNumber).getPeriod();
	// // //
	// // // // 曜日を交換
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(mutationNumber)
	// // // .setDayOfWeek(bestDayOfWeek);
	// // //
	// // // // 限目を交換
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(mutationNumber).setPeriod(
	// // // bestPeriod);
	// // //
	// // // int randomDayOfWeek = Calculation
	// // // .getRnd(TimeTable
	// // // .changeDayToValue("月"),
	// // // MAX_DAY);
	// // // int randomPeriod =
	// // // Calculation.getRnd(
	// // // 1, MAX_PERIOD);
	// // // String tmpDayOfWeek = TimeTable
	// // // .changeValueToDay(randomDayOfWeek);
	// // //
	// // // // 曜日を交換
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(mutationNumber)
	// // // .setDayOfWeek(tmpDayOfWeek);
	// // //
	// // // // 限目を交換
	// // //
	// // f_CandidateNewTimeTableData3[candidate]
	// // // .get(mutationNumber).setPeriod(
	// // // randomPeriod);
	// // }
	// //
	// // // よい要素番号がないとき
	// // else {
	// //
	// // int randomDayOfWeek = Calculation
	// // .getRnd(TimeTable
	// // .changeDayToValue("月"),
	// // MAX_DAY);
	// // int randomPeriod = Calculation.getRnd(
	// // 1, MAX_PERIOD);
	// // String tmpDayOfWeek = TimeTable
	// // .changeValueToDay(randomDayOfWeek);
	// //
	// // // 曜日を交換
	// // f_CandidateNewTimeTableData3[candidate]
	// // .get(mutationNumber)
	// // .setDayOfWeek(tmpDayOfWeek);
	// //
	// // // 限目を交換
	// // f_CandidateNewTimeTableData3[candidate]
	// // .get(mutationNumber).setPeriod(
	// // randomPeriod);
	// // }
	// }
	// }
	// }
	// }
	// }
	// }
	// }
	// }

	/*
	 * ある候補のコースをもとにどこの部分がよりよいかの要素番号を取得
	 */
	private int getBestNumberTimeTable3(int candidate, int grade,
			String semester, String course) {

		int elementNumber = -1;
		String bestCourse = null;

		if (course == null) {
			System.out.println("コースが" + course);
			InOutPut.anyKey();
		}

		double randomNum = Calculation.getDRnd(1.0, 9.0);

		switch (course) {

		case "奇数":

			bestCourse = "偶数";
			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "偶数":

			bestCourse = "奇数";
			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "ab":

			if (1.0 <= randomNum && randomNum < 4.5) {
				bestCourse = "bc";
			}

			else {
				bestCourse = "c";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);

			return elementNumber;

		case "bc":

			if (1.0 <= randomNum && randomNum < 4.5) {
				bestCourse = "ab";
			}

			else {
				bestCourse = "a";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "a":

			if (1.0 <= randomNum && randomNum < 3.0) {
				bestCourse = "b";
			}

			else if (3.0 <= randomNum && randomNum < 6.0) {
				bestCourse = "c";
			}

			else if (6.0 <= randomNum && randomNum <= 9.0) {
				bestCourse = "bc";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "b":

			if (1.0 <= randomNum && randomNum < 4.5) {
				bestCourse = "a";
			}

			else {
				bestCourse = "c";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "c":

			if (1.0 <= randomNum && randomNum < 3.0) {
				bestCourse = "ab";
			}

			else if (3.0 <= randomNum && randomNum < 6.0) {
				bestCourse = "a";
			}

			else if (6.0 <= randomNum && randomNum <= 9.0) {
				bestCourse = "b";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "エレ情(奇数)":

			if (1.0 <= randomNum && randomNum < 4.5) {
				bestCourse = "エレ情(偶数)";
			}

			else {
				bestCourse = "エネ環";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "エレ情(偶数)":

			if (1.0 <= randomNum && randomNum < 4.5) {
				bestCourse = "エレ情(奇数)";
			}

			else {
				bestCourse = "エネ環";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "エレ情":

			bestCourse = "エネ環";
			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		case "エネ環":

			if (1.0 <= randomNum && randomNum < 3.0) {
				bestCourse = "エレ情(奇数)";
			}

			else if (3.0 <= randomNum && randomNum < 6.0) {
				bestCourse = "エレ情(偶数)";
			}

			else if (6.0 <= randomNum && randomNum <= 9.0) {
				bestCourse = "エレ情";
			}

			elementNumber = getInTimeTable3Number(candidate, grade, semester,
					bestCourse);
			return elementNumber;

		default:
			return elementNumber;

		}
	}

	/*
	 * 推奨コースの要素番号を取得
	 * 
	 * @param candidate_number 候補の番号
	 * 
	 * @param grade 学年
	 * 
	 * @param semester 学期
	 * 
	 * @param course_class コース・クラス
	 * 
	 * @return 既存の時間割のデータの要素番号
	 */
	private int getInTimeTable3Number(int candidate_number, int grade,
			String semester, String course_class) {

		// すべての要素が同じものを探す
		for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
				.size(); number++) {

			// 同じ学年、同じ学期、選んでほしいコース・クラスが既存の時間割にあるとき
			if (f_CandidateNewTimeTableData3[candidate_number].get(number)
					.getClassOfGrade().getGrade() == grade
					&& Objects.equals(
							f_CandidateNewTimeTableData3[candidate_number]
									.get(number).getClassOfGrade()
									.getSemester(), semester)
					&& Objects.equals(
							f_CandidateNewTimeTableData3[candidate_number]
									.get(number).getClassOfGrade()
									.getCourseOrClass(), course_class)) {

				return number;
			}
		}

		return -1;
	}

	/*
	 * 重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication(int candidate, int number) {

		// 1次の時間割と重複するとき
		if (checkDuplication1(candidate, number)) {

			if (DEBUG) {
				System.out.println("1次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("1次と重複なし\n");
		}

		// 2次の時間割と重複するとき
		if (checkDuplication2(candidate, number)) {

			if (DEBUG) {
				System.out.println("2次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("2次と重複なし\n");
		}

		// 3次の時間割と重複するとき
		if (checkDuplication3(candidate, number)) {

			if (DEBUG) {
				System.out.println("3次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("3次と重複なし\n");
		}

		return false;
	}

	/*
	 * 1次の時間割と重複がないかのチェック
	 * 
	 * @param candidate 候補の番号
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication1(int candidate, int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					f_CandidateNewTimeTableData3[candidate].get(number)
							.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getSemester(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_CandidateNewTimeTableData3[candidate]
							.get(number).getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(f_TimeTableData1
										.get(number1).getClassOfGrade(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade(),
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

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
	 * @param candidate 候補の番号
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication2(int candidate, int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					f_CandidateNewTimeTableData3[candidate].get(number)
							.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getSemester(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == f_CandidateNewTimeTableData3[candidate]
							.get(number).getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								if (DEBUG) {
									System.out.println("学年が重複");
								}

								return true;
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassOfGrade()
										.getTeachers().getName(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
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
	 * 3次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkDuplication3(int candidate, int number) {

		for (int number3 = 0; number3 < f_CandidateNewTimeTableData3[candidate]
				.size(); number3++) {

			// まったく同じデータがあるとき
			if (number3 == number) {

				continue;
			}

			// 同じ曜日のとき
			if (Objects.equals(
					f_CandidateNewTimeTableData3[candidate].get(number3)
							.getDayOfWeek(),
					f_CandidateNewTimeTableData3[candidate].get(number)
							.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(
						f_CandidateNewTimeTableData3[candidate].get(number3)
								.getClassOfGrade().getSemester(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_CandidateNewTimeTableData3[candidate].get(number3)
							.getClassOfGrade().getGrade() == f_CandidateNewTimeTableData3[candidate]
							.get(number).getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_CandidateNewTimeTableData3[candidate]
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_CandidateNewTimeTableData3[candidate].get(
									number3).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(
										f_CandidateNewTimeTableData3[candidate]
												.get(number3).getClassOfGrade()
												.getTeachers().getName(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_CandidateNewTimeTableData3[candidate]
												.get(number3).getClassOfGrade()
												.getCourseOrClass(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}

							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_CandidateNewTimeTableData3[candidate]
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_CandidateNewTimeTableData3[candidate].get(
									number3).getPeriod()
									+ addPeriod == f_CandidateNewTimeTableData3[candidate]
									.get(number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(
										f_CandidateNewTimeTableData3[candidate]
												.get(number3).getClassOfGrade()
												.getTeachers().getName(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(
										f_CandidateNewTimeTableData3[candidate]
												.get(number3).getClassOfGrade(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getClassOfGrade(),
										f_CandidateNewTimeTableData3[candidate]
												.get(number3).getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}
								// if (Objects
								// .equals(f_CandidateNewTimeTableData3[candidate]
								// .get(number3).getClassOfGrade()
								// .getTeachers().getName(), "各先生")) {
								//
								// return true;
								// }
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * 先生の持ちコマ数を数える
	 */
	private void countNumOfSubjectOfTeacher() {

		countNumOfSubject1OfTeacher();// 1次の科目の担当教員のコマ数を数える

		if (DEBUG) {

			for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
				System.out.print(f_TeacherData.get(teacherNum).getName()
						+ "先生,");
				System.out.print("全"
						+ f_TeacherData.get(teacherNum).getNumOfAllSubject()
						+ "コマ,");
				System.out.println("新規"
						+ f_TeacherData.get(teacherNum).getNumOfNewSubject()
						+ "コマ");

			}
			System.out.println();
		}

		countNumOfSubject2OfTeacher();// 2次の科目の担当教員のコマ数を数える

		if (DEBUG) {

			for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
				System.out.print(f_TeacherData.get(teacherNum).getName()
						+ "先生,");
				System.out.print("全"
						+ f_TeacherData.get(teacherNum).getNumOfAllSubject()
						+ "コマ,");
				System.out.println("新規"
						+ f_TeacherData.get(teacherNum).getNumOfNewSubject()
						+ "コマ");
			}
			System.out.println();
		}

		countNumOfSubject3OfTeacher();// 3次の科目の担当教員のコマ数を数える

		if (DEBUG) {

			for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
				System.out.print(f_TeacherData.get(teacherNum).getName()
						+ "先生,");
				System.out.print("全"
						+ f_TeacherData.get(teacherNum).getNumOfAllSubject()
						+ "コマ,");
				System.out.println("新規"
						+ f_TeacherData.get(teacherNum).getNumOfNewSubject()
						+ "コマ");
			}
			System.out.println();
		}

	}

	/*
	 * 1次の科目の担当教員のコマ数を数える
	 */
	private void countNumOfSubject1OfTeacher() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			int num = 0;

			for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

				// 担当教員の名前が一致したとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData1.get(number1).getClassOfGrade()
								.getTeachers().getName())) {

					num += f_TimeTableData1.get(number1).getClassOfGrade()
							.getNumber();// 担当教員の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(
					f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
		}

	}

	/*
	 * 2次の科目の担当教員のコマ数を数える
	 */
	private void countNumOfSubject2OfTeacher() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			int num = 0;

			for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

				// 担当教員の名前が一致したとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData2.get(number2).getClassOfGrade()
								.getTeachers().getName())) {

					num += f_TimeTableData2.get(number2).getClassOfGrade()
							.getNumber();// 担当教員の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(
					f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
		}

	}

	/*
	 * 3次の科目の担当教員のコマ数を数える
	 */
	private void countNumOfSubject3OfTeacher() {

		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			int num = 0;
			int newNum = 0;

			for (int number3 = 0; number3 < f_ClassOfGradeData3.size(); number3++) {

				// 担当教員の名前が一致したとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_ClassOfGradeData3.get(number3).getTeachers()
								.getName())) {

					num += f_ClassOfGradeData3.get(number3).getNumber();// 担当教員の持ちコマ数を増やす

					newNum += f_ClassOfGradeData3.get(number3).getTeachers()
							.getNumOfNewSubject();// 新規の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(
					f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
			f_TeacherData.get(teacherNum)
					.setNumOfNewSubject(
							f_TeacherData.get(teacherNum).getNumOfNewSubject()
									+ newNum);
		}

	}

	/*
	 * 学年ごとの授業を最初の時間割にセットする
	 */
	private void setClassOfGrade3ToFirstTimeTable3() {

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			if (DEBUG) {
				System.out.println("候補" + (candidate + 1) + "の時間割の初期集団を作成");
			}

			for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {

				TimeTable timeTableData = new TimeTable();

				timeTableData.setClassOfGrade(f_ClassOfGradeData3.get(number));

				f_TimeTableData3.add(timeTableData);

				// 重複がある限り繰り返す
				do {

					makeFirstTimeTable3(number);// 最初の3次の時間割を決める
				} while (checkFirstDuplication(number));

			}

			f_CandidateTimeTableData3[candidate].addAll(f_TimeTableData3);// 作成した時間割すべてを候補として追加
			f_CandidateNewTimeTableData3[candidate].addAll(f_TimeTableData3);// GAでの選択のときに必要なため追加

			f_TimeTableData3.clear();// 時間割をいったん削除
		}

		calcDayPeriodFirstEvaluationValue();// 評価値の計算をする

		f_OldEvaluationValue = getSumEvaluationValue();// 最初の0代目の評価値の合計を取得

		if (DEBUG) {
			System.out.println("0代目の評価値:" + f_OldEvaluationValue);
			// InOutPut.anyKey();
		}

		Evaluation evaluation = new Evaluation();

		evaluation.setGeneration(0);
		evaluation.setEvaluationValue(f_OldEvaluationValue);

		f_EvaluationData.add(evaluation);

	}

	/*
	 * 最初の3次の時間割を決める
	 * 
	 * @param number 番目
	 */
	private void makeFirstTimeTable3(int number) {

		// ランダムに決定
		int randomDay = Calculation.getRnd(TimeTable.changeDayToValue("月"),
				MAX_DAY);// 曜日をランダムに決定
		int randomPeriod = Calculation.getRnd(1, MAX_PERIOD);// 限目をランダムに決定

		// 3次の時間割のデータとして設定する
		f_TimeTableData3.get(number).setDayOfWeek(
				TimeTable.changeValueToDay(randomDay));// 曜日を設定
		f_TimeTableData3.get(number).setPeriod(randomPeriod);// 限目を設定

	}

	/*
	 * 重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkFirstDuplication(int number) {

		if (DEBUG) {

			System.out.print(f_TimeTableData3.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_TimeTableData3.get(number).getPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSemester()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		// 1次の時間割と重複するとき
		if (checkFirstDuplication1(number)) {
			if (DEBUG) {
				System.out.println("1次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("1次と重複なし\n");
		}

		// 2次の時間割と重複するとき
		if (checkFirstDuplication2(number)) {
			if (DEBUG) {
				System.out.println("2次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("2次と重複なし\n");
		}

		// 3次の時間割と重複するとき
		if (checkFirstDuplication3(number)) {
			if (DEBUG) {
				System.out.println("3次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("3次と重複なし\n");
		}

		return false;
	}

	/*
	 * 1次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkFirstDuplication1(int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getSemester(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass())) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(f_TimeTableData1
										.get(number1).getClassOfGrade(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}
								
								// 特定の科目と重複するとき
								if (checkDuplicationSubject(f_TimeTableData3
										.get(number).getClassOfGrade(),
										f_TimeTableData1.get(number1)
												.getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

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
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkFirstDuplication2(int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getSemester(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								if (DEBUG) {
									System.out.println("限目と重複(同じ学年)");
								}

								return true;
							}

						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
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
	 * 3次の時間割と重複がないかのチェック
	 * 
	 * @param number 番目(3次の時間割)
	 * 
	 * @return true 重複あり
	 * 
	 * @return false 重複なし
	 */
	private boolean checkFirstDuplication3(int number) {

		for (int number3 = 0; number3 < f_TimeTableData3.size(); number3++) {

			if (number <= number3) {
				break;
			}

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData3.get(number3).getDayOfWeek(),
					f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData3.get(number3)
						.getClassOfGrade().getSemester(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData3.get(number3).getClassOfGrade()
							.getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("同じ先生");
									}
									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData3.get(number3)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass()) == true) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getTeachers().getName(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(f_TimeTableData3
										.get(number3).getClassOfGrade(),
										f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}

								// 特定の科目と重複するとき
								if (checkDuplicationSubject(f_TimeTableData3
										.get(number).getClassOfGrade(),
										f_TimeTableData3.get(number3)
												.getClassOfGrade()
												.getTeachers())) {

									if (DEBUG) {
										System.out.println("特定の科目と重複");
									}

									return true;
								}
								// if (Objects.equals(f_TimeTableData3
								// .get(number3).getClassOfGrade()
								// .getTeachers().getName(), "各先生")) {
								//
								// return true;
								// }
							}
						}
					}
				}
			}
		}

		return false;
	}

	/*
	 * コース・クラスと重複しているかのチェック
	 * 
	 * @param course_or_class1 コースクラス１
	 * 
	 * @param course_or_class2 コースクラス２
	 * 
	 * @return true 重複している
	 * 
	 * @return false 重複していない
	 */
	private boolean checkDuplicationCouseOrClass(String course_or_class1,
			String course_or_class2) {

		// 同じクラスのとき
		if (course_or_class1.equals(course_or_class2)) {

			if (DEBUG) {
				System.out.println("同じクラス");
			}

			return true;
		}

		// abクラスと被るとき
		if (course_or_class1.equals("ab")) {

			// bcクラスのとき
			if (course_or_class2.equals("bc")) {
				return false;
			}

			// cクラスのとき
			if (course_or_class2.equals("c")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("abと重複");
			}
			return true;
		}

		// bcクラスと被るとき
		if (course_or_class1.equals("bc")) {

			// aクラスのとき
			if (course_or_class2.equals("a")) {
				return false;
			}

			// abクラスのとき
			if (course_or_class2.equals("ab")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bcと重複");
			}
			return true;
		}

		// 奇数クラスと被るとき
		if (course_or_class1.equals("奇数")) {

			// 偶数クラスのとき
			if (course_or_class2.equals("偶数")) {

				return false;
			}

			if (DEBUG) {
				System.out.println("奇数と重複");
			}
			return true;
		}

		// 偶数クラスと被るとき
		if (course_or_class1.equals("偶数")) {

			// 奇数クラスのとき
			if (Objects.equals("奇数", course_or_class2)) {

				return false;
			}

			if (DEBUG) {
				System.out.println("偶数と重複");
			}
			return true;
		}

		// aクラスと被るとき
		if (course_or_class1.equals("a")) {

			// bクラスのとき
			if (course_or_class2.equals("b")) {
				return false;
			}

			// cクラスのとき
			if (course_or_class2.equals("c")) {
				return false;
			}

			// bcクラスのとき
			if (course_or_class2.equals("bc")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("aと重複");
			}
			return true;
		}

		// bクラスと被るとき
		if (course_or_class1.equals("b")) {

			// aクラスのとき
			if (course_or_class2.equals("a")) {
				return false;
			}

			// cクラスのとき
			if (course_or_class2.equals("c")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bと重複");
			}

			return true;

		}

		// cクラスと被るとき
		if (course_or_class1.equals("c")) {

			// aクラスのとき
			if (course_or_class2.equals("a")) {
				return false;
			}

			// bクラスのとき
			if (course_or_class2.equals("b")) {
				return false;
			}

			// abクラスのとき
			if (course_or_class2.equals("ab")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("cと重複");
			}
			return true;
		}

		// エレ情(奇数)クラスと被るとき
		if (course_or_class1.equals("エレ情(奇数)")) {

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (course_or_class2.equals("エレ情(偶数)")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(奇数)と重複");
			}
			return true;
		}

		// エレ情(偶数)クラスと被るとき
		if (course_or_class1.equals("エレ情(偶数)")) {

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (course_or_class2.equals("エレ情(奇数)")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(偶数)と重複");
			}
			return true;
		}

		// エレ情クラスと被るとき
		if (course_or_class1.equals("エレ情")) {

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情と重複");
			}
			return true;
		}

		// エネ環クラスと被るとき
		if (course_or_class1.equals("エネ環")) {

			// エレ情クラスのとき
			if (course_or_class2.equals("エレ情")) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (course_or_class2.equals("エレ情(奇数)")) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (course_or_class2.equals("エレ情(偶数)")) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エネ環と重複");
			}
			return true;
		}

		// 共通クラスと被るとき
		if (course_or_class1.equals("共通")) {

			if (DEBUG) {
				System.out.println("共通と重複");
			}
			return true;

		}

		// 再履修クラスと被るとき
		if (course_or_class1.equals("再履修")) {

			// 再履修クラスのとき
			if (course_or_class2.equals("再履修")) {

				return false;

			}

			if (DEBUG) {
				System.out.println("再履修と重複");
			}

			return true;
		}

		return true;
	}

	/*
	 * 科目が被っているかチェック
	 * 
	 * @param class_of_grade 学年ごとの授業
	 * 
	 * @param teacher 教員の名前
	 * 
	 * @return true 重複している
	 * 
	 * @return false 重複していない
	 */
	private boolean checkDuplicationSubject(ClassOfGrade class_of_grade,
			Teacher teacher) {

		// 基礎ゼミ1のとき
		if (class_of_grade.getSubject().equals("基礎ゼミ1")) {

			// 基礎ゼミ1を担当するとき
			if (1 <= teacher.getNumOfBasicSeminar1()) {
				return true;
			}

			return false;
		}

		// 基礎ゼミ2のとき
		if (class_of_grade.getSubject().equals("基礎ゼミ2")) {

			// 基礎ゼミ2を担当するとき
			if (1 <= teacher.getNumOfBasicSeminar2()) {
				return true;
			}

			return false;
		}

		// 電気電子工学実習のとき
		if (class_of_grade.getSubject().equals("電気電子工学実習")) {

			// 奇数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("奇数")) {

				// 電気電子工学実習の奇数クラスを担当するとき
				if (1 <= teacher.getNumOfOddEETraining()) {
					return true;
				}

				return false;
			}

			// 偶数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("偶数")) {

				// 電気電子工学実習の偶数クラスを担当するとき
				if (1 <= teacher.getNumOfEvenEETraining()) {
					return true;
				}
				return false;
			}
		}

		// 電気電子工学実験のとき
		if (class_of_grade.getSubject().equals("電気電子工学実験")) {

			// 奇数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("奇数")) {

				// 電気電子工学実験の奇数クラスを担当するとき
				if (1 <= teacher.getNumOfOddEEExperiment()) {
					return true;
				}

				return false;
			}

			// 偶数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("偶数")) {

				// 電気電子工学実験の偶数クラスを担当するとき
				if (1 <= teacher.getNumOfEvenEEExperiment()) {
					return true;
				}

				return false;
			}
		}

		// エンジニアリングデザイン実験のとき
		if (class_of_grade.getSubject().equals("エンジニアリングデザイン実験")) {

			// 奇数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("奇数")) {

				// エンジニアリングデザイン実験の奇数クラスを担当するとき
				if (1 <= teacher.getNumOfOddEngineeringDesignExperiment()) {
					return true;
				}

				return false;
			}

			// 偶数クラスのとき
			if (class_of_grade.getCourseOrClass().equals("偶数")) {

				// エンジニアリングデザイン実験の偶数クラスを担当するとき
				if (1 <= teacher.getNumOfEvenEngineeringDesignExperiment()) {
					return true;
				}

				return false;
			}
		}

		// エネルギー・環境実験のとき
		if (class_of_grade.getSubject().equals("エネルギー・環境実験")) {

			// エネルギー環境実験を担当するとき
			if (1 <= teacher.getNumOfEnergyEnvironmentExperiment()) {
				return true;
			}

			return false;
		}

		// エレクトロニクス・情報通信実験のとき
		if (class_of_grade.getSubject().equals("エレクトロニクス・情報通信実験")) {

			// エレクトロニクス・情報通信実験を担当するとき
			if (1 <= teacher.getNumOfEleInfoCommunicationExperiment()) {
				return true;
			}

			return false;
		}

		// 卒業研究ゼミナールのとき
		if (class_of_grade.getSubject().equals("卒業研究ゼミナール")) {

			// 卒業研究ゼミナールを担当するとき
			if (1 <= teacher.getNumOfGraduateStudySeminar()) {
				return true;
			}

			return false;
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
		super.readTeacherFile();// 先生のファイルを読み込む
		readFacultyFile();// 担当者が決まったファイルを読み込む(3次のファイル)
		readRoomDayPeriodFile();// 教室と何曜日と何限目が決まったファイルを読み込む(1次と2次のファイル)
		countNumOfSubjectOfTeacher();// 先生のコマ数をカウントする
		super.calctTeacherEvaluationValue();// 担当者決めの評価値を計算する
		makeTimeTable3();// 3次の時間割を作成

		if (DEBUG) {
			indicateData();// データの表示
		}
		return true;
	}

	/*
	 * 終了
	 */
	public void finish() {
		writeDayAndPeriodFile();// 何曜日と何限目が決まったファイルを書き込む
		writeEvaluationFile();// 評価値のファイルを書き込む
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
	public void writeDayAndPeriodFile() {
		// writeRoomAndDayAndPeriodFile1();// 1次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		// writeRoomAndDayAndPeriodFile2();// 2次のファイル(教室と何曜日と何限目が決まったファイル)を書き込む
		writeDayAndPeriodFile3();// 3次のファイル(何曜日と何限目が決まったファイル)を書き込む
		writeDayAndPeriodBestFile3();// 3次の最も評価値が高い時間割のファイルを書き込む
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
	 * 3次のファイル(何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeDayAndPeriodFile3() {

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {
			PrintWriter output;
			output = FileIO.writeFile("room_3_" + (candidate + 1) + ".csv",
					false);

			System.out.println("担当者が決まった3次のファイル" + "room_3_" + (candidate + 1)
					+ ".csv" + "に書き込みます。");

			output.print("曜日,");// 曜日
			output.print("限目,");// 限
			output.print("コマ数,");// コマ数
			output.print("学年,");// 学年
			output.print("前期後期,");// 前期後期
			output.print("科目名,");// 科目名
			output.print("担当教員,");// 担当教員
			output.print("教室,");// 教室
			output.println("コース・クラス");// コース・クラス

			for (int number = 0; number < f_CandidateTimeTableData3[candidate]
					.size(); number++) {
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getDayOfWeek() + ",");// 曜日
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getPeriod() + ",");// 限
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getNumber()
						+ ",");// コマ数
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getGrade()
						+ ",");// 学年
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getSemester()
						+ ",");// 前期後期
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getSubject()
						+ ",");// 科目名
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getTeachers().getName()
						+ ",");// 担当教員
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassRoom() + ",");// 教室
				output.println(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getCourseOrClass());// コース・クラス
			}

			output.close();
			System.out.println("room_3_" + (candidate + 1) + ".csv"
					+ "へ書き込みました。");

		}
	}

	/*
	 * 3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeDayAndPeriodBestFile3() {

		PrintWriter output;
		output = FileIO.writeFile(FILE3_NAME, false);

		System.out.println("担当者が決まった3次のファイル" + FILE3_NAME + "に書き込みます。");

		int bestCandidate = getBestTimeTable();

		output.print("曜日,");// 曜日
		output.print("限目,");// 限
		output.print("コマ数,");// コマ数
		output.print("学年,");// 学年
		output.print("前期後期,");// 前期後期
		output.print("科目名,");// 科目名
		output.print("担当教員,");// 担当教員
		output.print("教室,");// 教室
		output.println("コース・クラス");// コース・クラス

		for (int number = 0; number < f_CandidateTimeTableData3[bestCandidate]
				.size(); number++) {
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getDayOfWeek() + ",");// 曜日
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getPeriod() + ",");// 限
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getNumber()
					+ ",");// コマ数
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getGrade()
					+ ",");// 学年
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getSemester()
					+ ",");// 前期後期
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getSubject()
					+ ",");// 科目名
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getTeachers().getName()
					+ ",");// 担当教員
			output.print(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassRoom() + ",");// 教室
			output.println(f_CandidateTimeTableData3[bestCandidate].get(number)
					.getClassOfGrade().getCourseOrClass());// コース・クラス

		}

		output.close();
		System.out.println(FILE3_NAME + "へ書き込みました。");
	}

	/*
	 * 最初の3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFirstFile3() {

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {
			PrintWriter output;
			output = FileIO.writeFile(
					"roomFirst_3_" + (candidate + 1) + ".csv", false);

			System.out.println("担当者が決まった3次のファイル" + "room_3_" + (candidate + 1)
					+ ".csv" + "に書き込みます。");

			output.print("曜日,");// 曜日
			output.print("限目,");// 限
			output.print("コマ数,");// コマ数
			output.print("学年,");// 学年
			output.print("前期後期,");// 前期後期
			output.print("科目名,");// 科目名
			output.print("担当教員,");// 担当教員
			output.print("教室,");// 教室
			output.println("コース・クラス");// コース・クラス

			for (int number = 0; number < f_CandidateTimeTableData3[candidate]
					.size(); number++) {
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getDayOfWeek() + ",");// 曜日
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getPeriod() + ",");// 限
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getNumber()
						+ ",");// コマ数
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getGrade()
						+ ",");// 学年
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getSemester()
						+ ",");// 前期後期
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getSubject()
						+ ",");// 科目名
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getTeachers().getName()
						+ ",");// 担当教員
				output.print(f_CandidateTimeTableData3[candidate].get(number)
						.getClassRoom() + ",");// 教室
				output.println(f_CandidateTimeTableData3[candidate].get(number)
						.getClassOfGrade().getCourseOrClass());// コース・クラス
			}

			output.close();
			System.out.println("room_3_" + (candidate + 1) + ".csv"
					+ "へ書き込みました。");

		}
	}

	/*
	 * 評価値のファイルを書き込む
	 */
	private void writeEvaluationFile() {

		PrintWriter output;
		output = FileIO.writeFile(EVALUATION_FILE, false);

		System.out.println("担当者が決まった3次のファイル" + EVALUATION_FILE + "に書き込みます。");

		output.print("世代,");// 世代
		output.println("評価値");// 評価値

		for (int number = 0; number < f_EvaluationData.size(); number++) {
			output.print(f_EvaluationData.get(number).getGeneration() + ",");// 世代
			output.println(f_EvaluationData.get(number).getEvaluationValue());// 評価値
		}

		output.close();
		System.out.println(EVALUATION_FILE + "へ書き込みました。");
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
							timeTableData.getClassOfGrade().setSemester(
									strData[4]);// 前期後期
							timeTableData.getClassOfGrade().setSubject(
									strData[5]);// 科目名

							for (int teacherNum = 0; teacherNum < f_TeacherData
									.size(); teacherNum++) {

								if (strData[6].equals(f_TeacherData
										.get(teacherNum))) {
									timeTableData.getClassOfGrade()
											.setTeachers(
													f_TeacherData
															.get(teacherNum));
									break;
								}

								if (teacherNum == f_TeacherData.size() - 1) {

									timeTableData.getClassOfGrade()
											.getTeachers().setName(strData[6]);// 担当教員
								}
							}
							timeTableData.setClassRoom(strData[7]);// 教室
							timeTableData.getClassOfGrade().setCourseOrClass(
									strData[8]);// コース・クラス

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
						timeTableData.getClassOfGrade().setSemester(strData[4]);// 前期後期
						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名

						for (int teacherNum = 0; teacherNum < f_TeacherData
								.size(); teacherNum++) {

							if (strData[6].equals(f_TeacherData.get(teacherNum)
									.getName())) {
								timeTableData.getClassOfGrade().setTeachers(
										f_TeacherData.get(teacherNum));// 担当教員
								break;
							}

							if (teacherNum == f_TeacherData.size() - 1) {
								timeTableData.getClassOfGrade().getTeachers()
										.setName(strData[6]);// 担当教員
							}
						}
						// timeTableData.getClassOfGrade().getTeachers()
						// .setName(strData[6]);// 担当教員
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
	// classOfGradeData.getTeachers.setName(strData[4]);// 担当教員
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
							classOfGradeData.setSemester(strData[2]);// 前期後期
							classOfGradeData.setSubject(strData[3]);// 科目名
							classOfGradeData.getTeachers().setName(strData[4]);// 担当教員

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
						classOfGradeData.setSemester(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.getTeachers().setName(strData[4]);// 担当教員

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
						classOfGradeData.setSemester(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名

						for (int teacherNum = 0; teacherNum < f_TeacherData
								.size(); teacherNum++) {

							// 担当教員のデータに存在するとき
							if (strData[4].equals(f_TeacherData.get(teacherNum)
									.getName())) {
								classOfGradeData.setTeachers(f_TeacherData
										.get(teacherNum));// 担当教員
								break;
							}

							// 担当教員のデータに存在しないとき
							if (teacherNum == f_TeacherData.size() - 1) {

								classOfGradeData.getTeachers().setName(
										strData[4]);// 担当教員
							}
						}

						// classOfGradeData.getTeachers().setName(strData[4]);//
						// 担当教員
						classOfGradeData.setCourseOrClass(strData[5]);// コース・クラス
						classOfGradeData.getTeachers().setNumOfNewSubject(
								Integer.parseInt(strData[6]));// 新規の科目
						classOfGradeData
								.setNeedPC(Integer.parseInt(strData[7]));// PCの必要性
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
