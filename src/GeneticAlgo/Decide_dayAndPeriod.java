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
public class Decide_dayAndPeriod extends Decide_faculty implements iDayPeriod, iTimeTable {

	private static final String EVALUATION_FILE = "DayPeriodEvaluationData.csv";// 日程の評価値のデータのファイル
	private static final int STUDENT_COLS = 51;// 生徒のデータの行
	private static final int STUDENT_DATA_NUM = 3;// 生徒のデータの項目数
	private static final String STUDENT_DATA_FILE = "studentData.csv";// 生徒のデータのファイル

	private TimeTable f_TimeTableDataTmp1;// 時間割のデータの交換1
	private TimeTable f_TimeTableDataTmp2;// 時間割のデータの交換2
	private int f_OldEvaluationValue;// 前の評価値
	private int f_NewEvaluationValue;// 新しい評価値
	private String f_RandomDayOfWeek1;// ランダムの曜日
	private int f_RandomPeriod1;// ランダムの限目
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
	private int f_NumOfDuplication;// 重複している回数

	private ArrayList<Integer> f_MutationCandidateNumbers = new ArrayList<Integer>();// 突然変異の候補番号の動的配列
	private ArrayList<Integer> f_MutationNumbers = new ArrayList<Integer>();// 突然変異の要素番号の動的配列
	private ArrayList<Integer> f_MutationPeriods = new ArrayList<Integer>();// 突然変異の限目の動的配列
	private ArrayList<Integer> f_EvaluationValues = new ArrayList<Integer>();// 評価値の動的配列
	private ArrayList<String> f_MutationDayOfWeeks = new ArrayList<String>();// 突然変異の曜日の動的配列

	private ArrayList<Evaluation> f_EvaluationData = new ArrayList<Evaluation>();// 評価値のデータ

	private ArrayList<Student> f_NewStudentData = new ArrayList<Student>();// 更新された生徒のデータ
	private ArrayList<Student> f_StudentData = new ArrayList<Student>();// 生徒のデータ

	private ArrayList<TimeTable> f_NewTimeTableData3 = new ArrayList<TimeTable>();// 新規の3次の時間割のデータ
	// private ArrayList<TimeTable>[] f_CandidateTimeTableData3 = new
	// ArrayList[CANDIDATE_NUM];// 候補の時間割
	// private ArrayList<TimeTable>[] f_CandidateNewTimeTableData3 = new
	// ArrayList[CANDIDATE_NUM];// 新しく生成された候補の時間割

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
		f_NumOfDuplication = 0;

		// for (int candidate = 0; candidate < f_CandidateTimeTableData3.length;
		// candidate++) {
		// f_CandidateTimeTableData3[candidate] = new ArrayList<TimeTable>();
		// f_CandidateNewTimeTableData3[candidate] = new ArrayList<TimeTable>();
		// }
	}

	/*
	 * データの表示
	 */
	private void indicateData() {

		System.out.println("1次のデータ");

		for (int number = 0; number < f_TimeTableData1.size(); number++) {
			System.out.print(f_TimeTableData1.get(number).getFixedDayOfWeek() + "曜日、");// 曜日
			System.out.print(f_TimeTableData1.get(number).getFixedPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade().getNumber() + "コマ、");// コマ数
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade().getGrade() + "年、");// 学年
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade().getSemester() + "、");// 前期・後期
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade().getSubject() + "、");// 科目名
			System.out.print(f_TimeTableData1.get(number).getClassOfGrade().getTeachers().getName() + "先生、");// 担当教員
			System.out.println(f_TimeTableData1.get(number).getClassOfGrade().getCourseOrClass() + "クラス");// コース・クラス
		}

		System.out.println("\n2次のデータ");

		for (int number = 0; number < f_TimeTableData2.size(); number++) {
			System.out.print(f_TimeTableData2.get(number).getFixedDayOfWeek() + "曜日、");// 曜日
			System.out.print(f_TimeTableData2.get(number).getFixedPeriod() + "限目、");// 限目
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade().getNumber() + "コマ、");// コマ数
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade().getGrade() + "年、");// 学年
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade().getSemester() + "、");// 前期・後期
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade().getSubject() + "、");// 科目名
			System.out.print(f_TimeTableData2.get(number).getClassOfGrade().getTeachers().getName() + "先生、");// 担当教員
			System.out.println(f_TimeTableData2.get(number).getClassOfGrade().getCourseOrClass() + "クラス");// コース・クラス
		}

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			System.out.println("候補" + (candidate + 1) + "の3次のデータ");

			for (int number = 0; number < f_TimeTableData3.size(); number++) {

				System.out.print(f_TimeTableData3.get(number).getDayOfWeek(candidate) + "曜日、");// 曜日
				System.out.print(f_TimeTableData3.get(number).getPeriod(candidate) + "限目、");// 限目
				System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getNumber() + "コマ、");// コマ数
				System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade() + "年、");// 学年
				System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getSemester() + "、");// 前期・後期
				System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getSubject() + "、");// 科目名
				System.out.print(f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName() + "先生、");// 担当教員
				System.out.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass() + "クラス\n");// コース・クラス
			}
		}
	}

	/*
	 * 3次の時間割を表示する
	 */
	private void indicateTimeTableData3() {

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			System.out.println("\n候補" + (candidate + 1) + "の3次のデータ");

			for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

				System.out.print("候補" + (candidate + 1) + "," + number + "番、");

				System.out.print(f_NewTimeTableData3.get(number).getDayOfWeek(candidate) + "曜日、");// 曜日
				System.out.print(f_NewTimeTableData3.get(number).getPeriod(candidate) + "限目、");// 限目
				System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade().getNumber() + "コマ、");// コマ数
				System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade().getGrade() + "年、");// 学年
				System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade().getSemester() + "、");// 前期・後期
				System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade().getSubject() + "、");// 科目名
				System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName() + "先生、");// 担当教員
				System.out.println(f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass() + "クラス\n");// コース・クラス
			}
		}
	}

	/*
	 * 生徒のデータの表示
	 */
	private void indicateStudentData() {

		for (int studentNum = 0; studentNum < f_StudentData.size(); studentNum++) {

			System.out.print(f_StudentData.get(studentNum).getGrade() + "年、");
			System.out.print(f_StudentData.get(studentNum).getSemester() + "、");
			System.out.println(f_StudentData.get(studentNum).getCourseOrClass() + "クラス");

			for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

				for (int day = 0; day <= MAX_DAY; day++) {

					System.out.print(TimeTable.changeValueToDay(day) + "曜日：");

					for (int period = 1; period <= MAX_PERIOD; period++) {

						System.out.print(
								f_StudentData.get(studentNum).getDayPeriodNumber(candidate, day, period - 1) + "コマ");
					}
					System.out.println();
				}
			}
		}

		InOutPut.anyKey();
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
					System.out.print(TimeTable.changeValueToDay(day) + "曜日："
							+ f_TeacherData.get(teacherNum).getDayNumber(semester, day) + "コマ ");
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

	/*
	 * 生徒の曜日限目のコマ数をリセット
	 */
	private void resetNumOfStudent() {

		for (int studentNum = 0; studentNum < f_NewStudentData.size(); studentNum++) {

			f_NewStudentData.get(studentNum).resetDayPeriodNumber();
		}

		countFirstNumOfStudent();
	}

	/*
	 * 生徒のデータの更新
	 */
	private void updateStudentData() {

		for (int studentNum = 0; studentNum < f_StudentData.size(); studentNum++) {

			for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

				for (int day = 0; day <= MAX_DAY; day++) {

					for (int period = 1; period <= MAX_PERIOD; period++) {

						// 曜日と限目のコマ数を更新する
						f_StudentData.get(studentNum).updateDayPeriodNumber(candidate, day, period - 1,
								f_StudentData.get(studentNum).getDayPeriodNumbers(), candidate, day, period - 1,
								f_NewStudentData.get(studentNum).getDayPeriodNumbers());

					}
				}

				// 日程の評価値を更新する
				f_StudentData.get(studentNum).updateDayPeriodEvaluationValue(candidate,
						f_StudentData.get(studentNum).getDayPeriodEvaluationValue(),candidate,
						f_NewStudentData.get(studentNum).getDayPeriodEvaluationValue());
			}

		}

	}

	/*
	 * 時間割3の曜日と限目をリセットする
	 */
	private void resetTimeTableData3(int candidate) {

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			f_TimeTableData3.get(number).resetDayPeriod(candidate);
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

		countNumOfStudent();// 生徒のコマ数をカウントする

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			int sum = 0;

			// 候補の時間割に関して
			for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

				// 時間割と重複するとき
				if (checkDuplication(candidate, number)) {
					return false;
				}
			}

			for (int number = 0; number < f_NewStudentData.size(); number++) {

				// 生徒の評価値を計算する
				calcEvaluationValueOfStudent(candidate, number);

				sum += f_NewStudentData.get(number).getDayPeriodEvaluationValue(candidate);
			}
			f_EvaluationValues.set(candidate, sum);
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
			if (Objects.equals(f_TimeTableData1.get(number1).getFixedDayOfWeek(),
					f_NewTimeTableData3.get(number).getDayOfWeek(candidate))) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1).getClassOfGrade().getSemester(),
						f_NewTimeTableData3.get(number).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade().getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						// 実験科目のとき
						if (f_TimeTableData1.get(number1).getClassOfGrade().getSubject().contains("実験")) {

							// コース・クラスと重複するとき
							if (checkDuplicationCouseOrClass(
									f_TimeTableData1.get(number1).getClassOfGrade().getCourseOrClass(),
									f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass())) {

								return -50.0;
							}

							// コース・クラスと重複しないとき
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

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			int sum = 0;

			for (int number = 0; number < f_NewStudentData.size(); number++) {

				// 生徒の評価値を計算する
				calcEvaluationValueOfStudent(candidate, number);

				sum += f_NewStudentData.get(number).getDayPeriodEvaluationValue(candidate);
			}
			f_EvaluationValues.add(sum);

		}
	}

	/*
	 * 生徒のデータから評価値を計算
	 *
	 * @param candidate 候補番号
	 *
	 * @param number 要素番号
	 */
	private void calcEvaluationValueOfStudent(int candidate, int number) {

		int evaluationValue = 1;
		int max = 0;
		int min = 0;

		// それぞれの曜日のコマ数をカウント
		for (int day = 0; day < MAX_DAY; day++) {

			evaluationValue *= getDayOfWeekNumOfStudent(candidate, number, day)
					* getDayOfWeekNumOfStudent(candidate, number, day + 1);
		}

		max = f_NewStudentData.get(number).getMaxNumberOfDayPeriod(candidate);
		min = f_NewStudentData.get(number).getMinNumberOfDayPeriod(candidate);

		// (最大コマ数-最小コマ数)^4をかける
		for (int num = 0; num < 4; num++) {

			// 最大値と最小値が同じとき
			if (max == min) {

				break;// 計算式に入れない(1をかけるのと同じ)
			}

			evaluationValue *= (max - min);
		}

		// 新規の生徒のデータの日程評価値をセット
		f_NewStudentData.get(number).setDayPeriodEvaluationValue(candidate,
				f_NewStudentData.get(number).getDayPeriodEvaluationValue(), evaluationValue);
	}

	/*
	 * 指定した曜日のコマ数をカウント
	 *
	 * @param candidate 候補番号
	 *
	 * @param number 要素番号
	 *
	 * @param day 曜日
	 *
	 * @return ある生徒の曜日のコマ数
	 */
	private int getDayOfWeekNumOfStudent(int candidate, int number, int day) {

		int num = 0;

		for (int period = 1; period <= MAX_PERIOD; period++) {
			num += f_NewStudentData.get(number).getDayPeriodNumber(candidate, day, period - 1);
		}

		// 指定した曜日のコマ数が0のとき
		if (num <= 0) {
			num = 1;// 1としてカウント(評価値が0にならないようにする)
		}

		return num;
	}

	/*
	 * 評価値を取得
	 *
	 * @param candidate 候補の番号
	 *
	 * @return 指定した候補の時間割の評価値
	 */
	private int getEvaluationValue(int candidate) {

		resetTeacherDayNum();

		int value = 0;

		// value += f_EvaluationValues.get(candidate);

		// それぞれの担当教員がそれぞれの曜日に何コマ入っているか調べる
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			// 1次の時間割での曜日のコマ数をカウント
			for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable.changeDayToValue(f_TimeTableData1.get(number1).getFixedDayOfWeek());
					int semester = ClassOfGrade
							.changeSemesterToValue(f_TimeTableData1.get(number1).getClassOfGrade().getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(semester, day,
							f_TeacherData.get(teacherNum).getDayNumber(semester, day)
									+ f_TimeTableData1.get(number1).getClassOfGrade().getNumber());

				}
			}

			// 2次の時間割での曜日のコマ数をカウント
			for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_TimeTableData2.get(number2).getClassOfGrade().getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable.changeDayToValue(f_TimeTableData2.get(number2).getFixedDayOfWeek());

					int semester = ClassOfGrade
							.changeSemesterToValue(f_TimeTableData2.get(number2).getClassOfGrade().getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(semester, day,
							f_TeacherData.get(teacherNum).getDayNumber(semester, day)
									+ f_TimeTableData2.get(number2).getClassOfGrade().getNumber());

				}
			}

			// 3次の時間割での曜日のコマ数をカウント
			for (int number3 = 0; number3 < f_NewTimeTableData3.size(); number3++) {

				// 担当教員が見つかったとき
				if (Objects.equals(f_TeacherData.get(teacherNum).getName(),
						f_NewTimeTableData3.get(number3).getClassOfGrade().getTeachers().getName())) {

					// その時間割の曜日を値に変換
					int day = TimeTable.changeDayToValue(f_NewTimeTableData3.get(number3).getFixedDayOfWeek());

					if (day < 0) {
						System.out.println("候補" + candidate + "の" + number3 + "番目の曜日が" + "不明");
						indicateTimeTableData3();
						InOutPut.anyKey();
					}

					int semester = ClassOfGrade
							.changeSemesterToValue(f_NewTimeTableData3.get(number3).getClassOfGrade().getSemester());

					f_TeacherData.get(teacherNum).setDayNumber(semester, day,
							f_TeacherData.get(teacherNum).getDayNumber(semester, day)
									+ f_NewTimeTableData3.get(number3).getClassOfGrade().getNumber());

				}
			}

			for (int semester = 0; semester <= MAX_SEMESTER; semester++) {

				// カウントしたコマ数をもとに評価値を計算
				for (int day = 0; day <= MAX_DAY; day++) {

					if (4 <= f_TeacherData.get(teacherNum).getDayNumber(semester, day)) {
						value -= f_TeacherData.get(teacherNum).getDayNumber(semester, day);
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
	private int getSumEvaluationValue() {

		int value = 0;

		for (int number = 0; number < f_EvaluationValues.size(); number++) {

			value += f_EvaluationValues.get(number);
		}

		return value;

	}

	/*
	 * 評価値の計算をする
	 */
	private void calcEvaluationValue() {

		boolean canEvaluate = true;// 評価できるか

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

				// 時間割と重複するとき
				if (checkDuplication(candidate, number)) {
					f_NewEvaluationValue = 0;
					canEvaluate = false;
					break;
				}
			}
		}

		// 評価できるとき
		if (canEvaluate) {

			// calcDayPeriodEvaluationValue();// 評価値の計算をする

			f_NewEvaluationValue = getSumEvaluationValue();// 評価値の合計を取得

		}
	}

	/*
	 * もっとも評価値が高い時間割の番号を取得
	 *
	 * @return 最も評価値が高い候補番号
	 */
	private int getBestTimeTable() {

		int biggestNum = 0;
		int bestNum = 0;

		for(int candidate=0;candidate<CANDIDATE_NUM;candidate++) {

			if(biggestNum<f_EvaluationData.get(f_EvaluationData.size()-1).getEvaluationValues(candidate)) {
				biggestNum=f_EvaluationData.get(f_EvaluationData.size()-1).getEvaluationValues(candidate);
				bestNum=candidate;
			}
		}

		return bestNum;
	}

	/*
	 * 指定した要素番号までの評価値の合計
	 */
	private int getSumEvaluationValue(int num) {

		if (num < 0) {
			return 0;
		}

		int sum = f_EvaluationValues.get(num);

		return sum + getSumEvaluationValue(num - 1);

	}

	/*
	 * 評価値の表示
	 */
	private void indicateEvaluationValue() {

		for (int number = 0; number < f_EvaluationValues.size(); number++) {

			System.out.println("候補" + (number + 1) + "の評価値:" + f_EvaluationValues.get(number));
		}

		System.out.println("評価値の合計:" + getSumEvaluationValue(f_EvaluationValues.size() - 1));
	}

	// ------------------------------------------------------//
	// ---------------------遺伝的アルゴリズム---------------//
	// ------------------------------------------------------//
	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3() {

		// super.calctTeacherEvaluationValue();// 担当者決定の評価値を計算する

		setClassOfGrade3ToFirstTimeTable3();// 3次の学年ごとの授業を最初の時間割にセットする

		if (DEBUG) {
			writeRoomAndDayAndPeriodFirstFile3();// 初期集団においての3次の科目のデータを書き込む
			writeFirstStudentDataFile();// 初期集団においての生徒のデータを書き込む
			indicateTeacherDayNum();// 担当教員のコマ数を表示
			indicateTimeTableData3();// 3次の科目のデータの表示
			indicateEvaluationValue();// 評価値の表示
			InOutPut.anyKey();
		}

		exeGeneticAlgo();// 遺伝的アルゴリズムを実行する

		System.out.println("遺伝的アルゴリズム終了");

		if (DEBUG) {
			indicateTeacherDayNum();// 担当教員のコマ数を表示
			indicateTimeTableData3();// 3次の科目のデータの表示
			indicateEvaluationValue();// 評価値の表示

			InOutPut.anyKey();
		}
	}

	/*
	 * 遺伝的アルゴリズムを実行する
	 */
	private void exeGeneticAlgo() {

		int updateNum = 0;

		for (int num = 0; num < CHECK_NUM; num++) {

			Evaluation evaluation = new Evaluation();

			boolean wasMutated = false;

			System.out.println("遺伝的アルゴリズム" + (num + 1) + "回目" + "(現在の更新回数:" + updateNum + "回)");

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

				updateStudentData();// 生徒のデータを更新する
				resetNewTimeTableData3(false);// 時間割を更新する

				if (DEBUG) {
					System.out.println("評価値が更新された。");
					System.out.println("評価値:" + f_OldEvaluationValue);
				}

				updateNum++;

				for (int number = 0; number < f_EvaluationValues.size(); number++) {
					evaluation.addEvaluationValues(f_EvaluationValues.get(number));
				}

				evaluation.setSumEvaluationValue(f_OldEvaluationValue);

				f_EvaluationData.add(evaluation);
			}

			// 新しい評価値以下のとき
			else {

				// 突然変異したとき
				if (wasMutated == true) {

					for (int mutationNum = f_MutationDayOfWeeks.size() - 1; mutationNum >= 0; mutationNum--) {

						f_NewTimeTableData3.get(f_MutationNumbers.get(mutationNum)).setDayOfWeek(
								f_MutationCandidateNumbers.get(mutationNum),
								f_NewTimeTableData3.get(f_MutationNumbers.get(mutationNum)).getArrayOfDayOfWeek(),
								f_MutationDayOfWeeks.get(mutationNum));
						f_NewTimeTableData3.get(f_MutationNumbers.get(mutationNum)).setPeriod(
								f_MutationCandidateNumbers.get(mutationNum),
								f_NewTimeTableData3.get(f_MutationNumbers.get(mutationNum)).getArrayOfPeriod(),
								f_MutationPeriods.get(mutationNum));
					}
				}

				resetNewTimeTableData3(true);// 時間割をリセットする
			}

		}

		System.out.println("評価値が更新された回数：" + updateNum + "回");
		InOutPut.anyKey();
	}

	/*
	 * 元の時間割にリセットする
	 *
	 * @param doReset リセットするか
	 */
	private void resetNewTimeTableData3(boolean doReset) {

		// TimeTable tmpTimeTable = new TimeTable();

		// リセットするとき
		if (doReset == true) {

			// 元の候補に戻す
			for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

				for (int number = 0; number < f_TimeTableData3.size(); number++) {

					f_NewTimeTableData3.get(number).setDayOfWeek(candidate,
							f_NewTimeTableData3.get(number).getArrayOfDayOfWeek(),
							f_TimeTableData3.get(number).getDayOfWeek(candidate));
					f_NewTimeTableData3.get(number).setPeriod(candidate,
							f_NewTimeTableData3.get(number).getArrayOfPeriod(),
							f_TimeTableData3.get(number).getPeriod(candidate));
					// tmpTimeTable
					// .setFixedDayOfWeek(f_CandidateTimeTableData3[candidate].get(number).getFixedDayOfWeek());
					//
					// tmpTimeTable.setFixedPeriod(f_CandidateTimeTableData3[candidate].get(number).getFixedPeriod());
					//
					// // 曜日を戻す
					// f_CandidateNewTimeTableData3[candidate].get(number)
					// .setFixedDayOfWeek(tmpTimeTable.getFixedDayOfWeek());
					//
					// // 限目を戻す
					// f_CandidateNewTimeTableData3[candidate].get(number).setFixedPeriod(tmpTimeTable.getFixedPeriod());

				}
			}
		}

		// リセットしないとき(更新するとき)
		else {

			// 時間割を更新する
			for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

				for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

					// 曜日を更新
					f_TimeTableData3.get(number).setDayOfWeek(candidate,
							f_TimeTableData3.get(number).getArrayOfDayOfWeek(),
							f_NewTimeTableData3.get(number).getDayOfWeek(candidate));

					// 限目を更新
					f_TimeTableData3.get(number).setPeriod(candidate, f_TimeTableData3.get(number).getArrayOfPeriod(),
							f_NewTimeTableData3.get(number).getPeriod(candidate));

					// tmpTimeTable
					// .setFixedDayOfWeek(f_CandidateNewTimeTableData3[candidate].get(number).getFixedDayOfWeek());
					//
					// tmpTimeTable.setFixedPeriod(f_CandidateNewTimeTableData3[candidate].get(number).getFixedPeriod());
					//
					// // 曜日を更新
					// f_CandidateTimeTableData3[candidate].get(number)
					// .setFixedDayOfWeek(tmpTimeTable.getFixedDayOfWeek());
					//
					// // 限目を更新
					// f_CandidateTimeTableData3[candidate].get(number).setFixedPeriod(tmpTimeTable.getFixedPeriod());
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

		int sum = f_OldEvaluationValue;// 合計の評価値を取得

		// TimeTable tmpTimeTable = new TimeTable();

		for (int candidate = 0; candidate < num; candidate++) {

			double randomNum = Calculation.getDRnd(0.0, sum);// 0.0から候補の数だけの評価値の合計までランダムで選ぶ

			for (int number = 0; number < f_EvaluationValues.size(); number++) {

				// 候補の時間割のとき
				if (getSumEvaluationValue(number - 1) <= randomNum && randomNum <= getSumEvaluationValue(number)) {
					f_CandidateRandomNumber1 = number;
					break;
				}
			}

			for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

				// tmpTimeTable.setFixedDayOfWeek(
				// f_CandidateTimeTableData3[f_CandidateRandomNumber1].get(number).getFixedDayOfWeek());
				// tmpTimeTable.setFixedPeriod(
				// f_CandidateTimeTableData3[f_CandidateRandomNumber1].get(number).getFixedPeriod());

				// 曜日を別の候補に入れ替え
				f_NewTimeTableData3.get(number).setDayOfWeek(candidate,
						f_NewTimeTableData3.get(number).getArrayOfDayOfWeek(),
						f_TimeTableData3.get(number).getDayOfWeek(f_CandidateRandomNumber1));

				// 限目を別の候補に入れ替え
				f_NewTimeTableData3.get(number).setPeriod(candidate, f_NewTimeTableData3.get(number).getArrayOfPeriod(),
						f_TimeTableData3.get(number).getPeriod(f_CandidateRandomNumber1));
				// f_NewTimeTableData3.get(number).setPeriod(candidate,f_NewTimeTableData3.get(number).getArrayOfPeriod(),tmpTimeTable.getFixedPeriod());
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
		f_CandidateRandomNumber1 = Calculation.getRnd(0, CANDIDATE_NUM - 1);

		// 交叉させる要素番号をランダムで選ぶ
		f_RandomCrossNumber1 = Calculation.getRnd(0, f_NewTimeTableData3.size() - 1);

		// 違う候補番号が選ばれない限り繰り返す
		do {

			// 交叉させる候補の番号をランダムで選ぶ
			f_CandidateRandomNumber2 = Calculation.getRnd(0, CANDIDATE_NUM - 1);

		} while (f_CandidateRandomNumber2 == f_CandidateRandomNumber1);

		// 候補1の曜日を保存
		String tmpDayOfWeek = f_NewTimeTableData3.get(f_RandomCrossNumber1).getDayOfWeek(f_CandidateRandomNumber1);

		// 候補1の限目を保存
		int tmpPeriod = f_NewTimeTableData3.get(f_RandomCrossNumber1).getPeriod(f_CandidateRandomNumber1);

		// 候補1の曜日を入れ替え
		f_NewTimeTableData3.get(f_RandomCrossNumber1).setDayOfWeek(f_CandidateRandomNumber1,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getArrayOfDayOfWeek(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getDayOfWeek(f_CandidateRandomNumber2));

		// 候補2の曜日を入れ替え
		f_NewTimeTableData3.get(f_RandomCrossNumber1).setDayOfWeek(f_CandidateRandomNumber2,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getArrayOfDayOfWeek(), tmpDayOfWeek);

		// 候補1の限目を入れ替え
		f_NewTimeTableData3.get(f_RandomCrossNumber1).setPeriod(f_CandidateRandomNumber1,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getArrayOfPeriod(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getPeriod(f_CandidateRandomNumber2));

		// 候補2の限目を入れ替え
		f_NewTimeTableData3.get(f_RandomCrossNumber1).setPeriod(f_CandidateRandomNumber2,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getArrayOfPeriod(), tmpPeriod);

		// 候補1の中に候補2の曜日と限目が同じものがないかを探す
		elementNumber1 = getInTimeTable3Number(f_CandidateRandomNumber1, f_RandomDayOfWeek2, f_RandomPeriod2,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getSemester(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getCourseOrClass(), false, false, false,
				true);

		// 候補2の中に候補1の曜日と限目が同じものがないかを探す
		elementNumber2 = getInTimeTable3Number(f_CandidateRandomNumber2, f_RandomDayOfWeek1, f_RandomPeriod1,
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getSemester(),
				f_NewTimeTableData3.get(f_RandomCrossNumber1).getClassOfGrade().getCourseOrClass(), false, false, false,
				true);

		// 両方見つかった場合(計4つの要素番号)
		if (0 <= elementNumber2 && 0 <= elementNumber1) {

			tmpDayOfWeek = f_NewTimeTableData3.get(elementNumber2).getDayOfWeek(f_CandidateRandomNumber2);

			tmpPeriod = f_NewTimeTableData3.get(elementNumber2).getPeriod(f_CandidateRandomNumber2);

			// 曜日を入れ替え
			f_NewTimeTableData3.get(elementNumber2).setDayOfWeek(f_CandidateRandomNumber2,
					f_NewTimeTableData3.get(elementNumber2).getArrayOfDayOfWeek(),
					f_NewTimeTableData3.get(elementNumber1).getDayOfWeek(f_CandidateRandomNumber1));
			f_NewTimeTableData3.get(elementNumber1).setDayOfWeek(f_CandidateRandomNumber1,
					f_NewTimeTableData3.get(elementNumber1).getArrayOfDayOfWeek(), tmpDayOfWeek);

			// 限目を入れ替え
			f_NewTimeTableData3.get(elementNumber2).setPeriod(f_CandidateRandomNumber2,
					f_NewTimeTableData3.get(elementNumber2).getArrayOfPeriod(),
					f_NewTimeTableData3.get(elementNumber1).getPeriod(f_CandidateRandomNumber1));
			f_NewTimeTableData3.get(elementNumber1).setPeriod(f_CandidateRandomNumber1,
					f_NewTimeTableData3.get(elementNumber1).getArrayOfPeriod(), tmpPeriod);

		}

		// 片方見つかる場合
		else {

			// 候補2だけ見つかったとき
			if (0 <= elementNumber2) {
				// tmpTimeTable = new TimeTable();

				tmpDayOfWeek = f_NewTimeTableData3.get(elementNumber2).getDayOfWeek(f_CandidateRandomNumber2);

				tmpPeriod = f_NewTimeTableData3.get(elementNumber2).getPeriod(f_CandidateRandomNumber2);

				// 見つかった候補2と同じ要素番号の候補1にコピー
				f_NewTimeTableData3.get(elementNumber2).setDayOfWeek(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfDayOfWeek(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getDayOfWeek(f_CandidateRandomNumber1));
				f_NewTimeTableData3.get(elementNumber2).setPeriod(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfPeriod(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getPeriod(f_CandidateRandomNumber1));

				// 曜日を交換
				f_NewTimeTableData3.get(elementNumber2).setDayOfWeek(f_CandidateRandomNumber2,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfDayOfWeek(),
						f_NewTimeTableData3.get(elementNumber2).getDayOfWeek(f_CandidateRandomNumber1));
				f_NewTimeTableData3.get(elementNumber2).setDayOfWeek(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfDayOfWeek(), tmpDayOfWeek);

				// 限目を交換
				f_NewTimeTableData3.get(elementNumber2).setPeriod(f_CandidateRandomNumber2,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfPeriod(),
						f_NewTimeTableData3.get(elementNumber2).getPeriod(f_CandidateRandomNumber1));
				f_NewTimeTableData3.get(elementNumber2).setPeriod(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber2).getArrayOfPeriod(), tmpPeriod);
			}

			// 候補1だけ見つかったとき
			else if (0 <= elementNumber1) {

				tmpDayOfWeek = f_NewTimeTableData3.get(elementNumber1).getDayOfWeek(f_CandidateRandomNumber1);

				tmpPeriod = f_NewTimeTableData3.get(elementNumber1).getPeriod(f_CandidateRandomNumber1);

				// 見つかった候補1と同じ要素番号の候補2にコピー
				f_NewTimeTableData3.get(elementNumber1).setDayOfWeek(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfDayOfWeek(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getDayOfWeek(f_CandidateRandomNumber2));
				f_NewTimeTableData3.get(elementNumber1).setPeriod(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfPeriod(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getPeriod(f_CandidateRandomNumber2));

				// 曜日を交換
				f_NewTimeTableData3.get(elementNumber1).setDayOfWeek(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfDayOfWeek(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getDayOfWeek(f_CandidateRandomNumber2));
				f_NewTimeTableData3.get(elementNumber1).setDayOfWeek(f_CandidateRandomNumber2,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfDayOfWeek(), tmpDayOfWeek);

				// 限目を交換
				f_NewTimeTableData3.get(elementNumber1).setPeriod(f_CandidateRandomNumber1,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfPeriod(),
						f_NewTimeTableData3.get(f_RandomCrossNumber1).getPeriod(f_CandidateRandomNumber2));
				f_NewTimeTableData3.get(elementNumber1).setPeriod(f_CandidateRandomNumber2,
						f_NewTimeTableData3.get(elementNumber1).getArrayOfPeriod(), tmpPeriod);
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
	 * 時間割に1に存在するか
	 */
	private boolean existInTimeTable1Number(String day_of_week, int period, int grade, String semester,
			String course_class) {

		for (int number = 0; number < f_TimeTableData1.size(); number++) {

			if (day_of_week.equals(f_TimeTableData1.get(number).getFixedDayOfWeek())
					&& f_TimeTableData1.get(number).getFixedPeriod() == period
					&& f_TimeTableData1.get(number).getClassOfGrade().getGrade() == grade
					&& semester.equals(f_TimeTableData1.get(number).getClassOfGrade().getSemester())
					&& course_class.equals(f_TimeTableData1.get(number).getClassOfGrade().getCourseOrClass())) {

				return true;
			}
		}

		return false;
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
	 * @param lookForAll すべての要素が同じものを探すか
	 *
	 * @param lookForSameDayPeriodSemester 曜日、限目、学期が同じものを探すか
	 *
	 * @param lookForSameDayPeriod 曜日、限目が同じものを探すか
	 *
	 * @return 既存の時間割のデータの要素番号
	 */
	private int getInTimeTable3Number(int candidate_number, String day_of_week, int period, int grade, String semester,
			String course_class, boolean lookForAll, boolean lookForSameDayPeriodGradeSemester,
			boolean lookForSameDayPeriodSemester, boolean lookForSameDayPeriod) {

		// 全ての要素が同じもの探すとき
		if (lookForAll == true) {

			// すべての要素が同じものを探す
			for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

				// 全ての要素が同じもの探すとき
				if (lookForAll == true) {

					// 指定した曜日、限目、学年、前期後期、コース・クラスが時間割3のデータに存在するとき
					if (day_of_week.equals(f_NewTimeTableData3.get(number).getDayOfWeek(candidate_number))
							&& f_NewTimeTableData3.get(number).getPeriod(candidate_number) == period
							&& f_NewTimeTableData3.get(number).getClassOfGrade().getGrade() == grade
							&& semester.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getSemester())
							&& course_class
									.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass())) {

						return number;
					}
				}

				// 曜日、限目、学期が同じものを探すとき
				if (lookForSameDayPeriodGradeSemester == true) {

					// 指定した曜日、限目、学年、前期後期が時間割3のデータに存在するとき
					if (day_of_week.equals(f_NewTimeTableData3.get(number).getDayOfWeek(candidate_number))
							&& f_NewTimeTableData3.get(number).getPeriod(candidate_number) == period
							&& f_NewTimeTableData3.get(number).getClassOfGrade().getGrade() == grade
							&& semester.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getSemester())) {

						return number;
					}
				}

				// 曜日、限目、学期が同じものを探すとき
				if (lookForSameDayPeriodSemester == true) {

					// 指定した曜日、限目、前期後期が時間割3のデータに存在するとき
					if (day_of_week.equals(f_NewTimeTableData3.get(number).getDayOfWeek(candidate_number))
							&& f_NewTimeTableData3.get(number).getPeriod(candidate_number) == period
							&& semester.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getSemester())) {

						return number;
					}
				}

				// 曜日、限目が同じものを探すとき
				if (lookForSameDayPeriod == true) {

					// 指定した曜日、限目が時間割3のデータに存在するとき
					if (day_of_week.equals(f_NewTimeTableData3.get(number).getDayOfWeek(candidate_number))
							&& f_NewTimeTableData3.get(number).getPeriod(candidate_number) == period) {
						return number;
					}
				}
			}
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

			TimeTable tmpTimeTable = new TimeTable();

			tmpTimeTable.setFixedDayOfWeek(f_TimeTableDataTmp1.getFixedDayOfWeek());
			tmpTimeTable.setFixedPeriod(f_TimeTableDataTmp1.getFixedPeriod());

			f_TimeTableDataTmp1.setFixedDayOfWeek(f_TimeTableDataTmp2.getFixedDayOfWeek());
			f_TimeTableDataTmp1.setFixedPeriod(f_TimeTableDataTmp2.getFixedPeriod());

			f_TimeTableDataTmp2.setFixedDayOfWeek(tmpTimeTable.getFixedDayOfWeek());
			f_TimeTableDataTmp2.setFixedPeriod(tmpTimeTable.getFixedPeriod());

			return true;
		}

		// 時間割のデータが片方だけ存在のとき
		else {

			f_NewTimeTableData3.get(f_CandidateRandomNumber2)
					.setFixedDayOfWeek(f_TimeTableDataTmp2.getFixedDayOfWeek());
			f_NewTimeTableData3.get(f_CandidateRandomNumber2).setFixedPeriod(f_TimeTableDataTmp2.getFixedPeriod());

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

		boolean wasMutatedOnce = false;
		int mutationNum = 0;
		TimeTable tmpTimeTable = new TimeTable();
		f_MutationCandidateNumbers.clear();
		f_MutationNumbers.clear();
		f_MutationDayOfWeeks.clear();
		f_MutationPeriods.clear();

		System.out.println("突然変異");

		// 候補の数において
		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			// 前期と後期それぞれにおいて
			for (int semester = 0; semester <= MAX_SEMESTER; semester++) {

				tmpTimeTable.getClassOfGrade().setSemester(ClassOfGrade.changeValueToSemester(semester));

				// 月曜～金曜において
				for (int day = 0; day <= MAX_DAY; day++) {

					tmpTimeTable.setDayOfWeek(candidate, tmpTimeTable.getArrayOfDayOfWeek(),
							TimeTable.changeValueToDay(day));
					// String strDay = TimeTable.changeValueToDay(day);

					// 1限目～MAX_PERIOD限目において
					for (int period = 1; period <= MAX_PERIOD; period++) {

						tmpTimeTable.setPeriod(candidate, tmpTimeTable.getArrayOfPeriod(), period);

						// 1年～MAX_GRADE年において
						for (int grade = 1; grade <= MAX_GRADE; grade++) {

							tmpTimeTable.getClassOfGrade().setGrade(grade);

							// // 突然変異させる要素番号を既存の時間割から取得(全ての要素が同じもの)
							// int mutationNumber = getInTimeTable3Number(candidate,
							// tmpTimeTable.getDayOfWeek(candidate),
							// tmpTimeTable.getPeriod(candidate), tmpTimeTable.getClassOfGrade().getGrade(),
							// tmpTimeTable.getClassOfGrade().getSemester(),
							// tmpTimeTable.getClassOfGrade().getCourseOrClass(), true, true, true, true);

							int mutationNumber = Calculation.getRnd(0, f_NewTimeTableData3.size() - 1);

							// 既存の時間割に存在するとき
							if (0 <= mutationNumber) {

								int addProbablity = 0;

								// // 1限目または5限目のとき
								// if (period == 1 || period == 5) {
								// // addProbablity = 1;// 突然変異率を上げる
								// }
								//
								// // 2～4限目のとき
								// else {
								// continue;
								// }

								int num = Calculation.getRnd(1, 100);

								// (MUTATION_PROBABILITY)/(100)のとき
								if (1 <= num && num <= MUTATION_PROBABLITY + addProbablity) {

									f_MutationCandidateNumbers.add(candidate);

									f_MutationNumbers.add(mutationNumber);

									f_MutationDayOfWeeks
											.add(f_NewTimeTableData3.get(mutationNumber).getDayOfWeek(candidate));

									f_MutationPeriods.add(f_NewTimeTableData3.get(mutationNumber).getPeriod(candidate));

									boolean couldDiscover = false;
									// String courseOrClass =
									// f_CandidateNewTimeTableData3[candidate]
									// .get(mutationNumber)
									// .getClassOfGrade()
									// .getCourseOrClass();
									//
									// TimeTable bestTimeTable = new
									// TimeTable();
									//
									// int randomNum = Calculation.getRnd(0, 3);
									// String bestCourse = getBestCourse(
									// courseOrClass, randomNum);
									//
									// // 1次の時間割の中にベストな曜日と限目があるかを探索
									// bestTimeTable = getInTimeTable1Number(
									// candidate, tmpTimeTable
									// .getClassOfGrade()
									// .getGrade(), tmpTimeTable
									// .getClassOfGrade()
									// .getSemester(),
									// courseOrClass, bestCourse);
									//
									// // 1次の時間割にベストな曜日と限目があるとき
									// if (bestTimeTable.getPeriod() != 0) {
									//
									// tmpTimeTable.setDayOfWeek(bestTimeTable
									// .getDayOfWeek());
									// tmpTimeTable.setPeriod(bestTimeTable
									// .getPeriod());
									//
									// couldDiscover = true;
									// }
									//
									// // 発見できなかったとき
									// if (couldDiscover == false) {
									//
									// // コース・クラスをもとにより良い要素番号を取得
									// int bestNumber =
									// getInTimeTable3BestNumber(
									// candidate, grade, tmpTimeTable
									// .getClassOfGrade()
									// .getSemester(),
									// courseOrClass, bestCourse);
									//
									// // 良い要素番号を見つけたとき
									// if (0 <= bestNumber) {
									// tmpTimeTable
									// .setDayOfWeek(f_CandidateNewTimeTableData3[candidate]
									// .get(bestNumber)
									// .getDayOfWeek());
									// tmpTimeTable
									// .setPeriod(f_CandidateNewTimeTableData3[candidate]
									// .get(bestNumber)
									// .getPeriod());
									//
									// couldDiscover = true;
									// }
									// }
									//
									// 発見できなかったとき
									if (couldDiscover == false) {

										// 曜日をランダムで取得
										int randomDayOfWeek = Calculation.getRnd(TimeTable.changeDayToValue("月"),
												MAX_DAY);

										// 限目をランダムで取得
										tmpTimeTable.setPeriod(candidate, tmpTimeTable.getArrayOfPeriod(),
												getRandomPeriod());
										tmpTimeTable.setDayOfWeek(candidate, tmpTimeTable.getArrayOfDayOfWeek(),
												TimeTable.changeValueToDay(randomDayOfWeek));

									}

									// 曜日を交換
									f_NewTimeTableData3.get(f_MutationNumbers.get(f_MutationNumbers.size() - 1))
											.setDayOfWeek(
													f_MutationCandidateNumbers
															.get(f_MutationCandidateNumbers.size() - 1),
													f_NewTimeTableData3
															.get(f_MutationNumbers.get(f_MutationNumbers.size() - 1))
															.getArrayOfDayOfWeek(),
													tmpTimeTable.getDayOfWeek(candidate));

									// 限目を交換
									f_NewTimeTableData3.get(f_MutationNumbers.get(f_MutationNumbers.size() - 1))
											.setPeriod(
													f_MutationCandidateNumbers
															.get(f_MutationCandidateNumbers.size() - 1),
													f_NewTimeTableData3
															.get(f_MutationNumbers.get(f_MutationNumbers.size() - 1))
															.getArrayOfPeriod(),
													tmpTimeTable.getPeriod(candidate));

									mutationNum++;// 突然変異の回数を増やす

									// 1回も突然変異が発生していないとき
									if (wasMutatedOnce == false) {
										wasMutatedOnce = true;
									}
								}
							}
						}
					}
				}
			}
		}

		// 1回以上突然変異が発生したとき
		if (wasMutatedOnce == true) {
			System.out.println(mutationNum + "回発生");
			return true;
		}

		System.out.println("発生なし");
		return false;
	}

	/*
	 * 推奨コースの要素番号を1次の時間割から取得
	 *
	 * @param grade 学年
	 *
	 * @param semester 学期
	 *
	 * @param course_class コース・クラス
	 *
	 * @param best_course 推奨のコース・クラス
	 *
	 * @return 既存の時間割のデータの要素番号
	 */
	private TimeTable getInTimeTable1Number(int candidate_number, int grade, String semester, String course_class,
			String best_course) {

		TimeTable bestTimeTable = new TimeTable();

		for (int number = 0; number < f_TimeTableData1.size(); number++) {

			for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number).getClassOfGrade()
					.getNumber(); addPeriod++) {

				// 2限目～4限目のとき
				if (2 <= f_TimeTableData1.get(number).getFixedPeriod() + addPeriod
						&& f_TimeTableData1.get(number).getFixedPeriod() + addPeriod <= 4) {

					// 同じ学年、同じ学期、同じコース・クラスがあるとき
					if (f_TimeTableData1.get(number).getClassOfGrade().getGrade() == grade
							&& semester.equals(f_TimeTableData1.get(number).getClassOfGrade().getSemester())
							&& best_course.equals(f_TimeTableData1.get(number).getClassOfGrade().getCourseOrClass())) {

						// 曜日と限目をセット
						bestTimeTable.setFixedDayOfWeek(f_TimeTableData1.get(number).getFixedDayOfWeek());
						bestTimeTable.setFixedPeriod(f_TimeTableData1.get(number).getFixedPeriod() + addPeriod);

						// 同じ曜日と限目のところに3次の時間割のデータに同じコース・クラスがあるか確認する
						int elementNumber = getInTimeTable3Number(candidate_number, bestTimeTable.getFixedDayOfWeek(),
								bestTimeTable.getFixedPeriod(), grade, semester, course_class, true, false, false,
								false);

						if (existInTimeTable1Number(bestTimeTable.getFixedDayOfWeek(), bestTimeTable.getFixedPeriod(),
								grade, semester, course_class) == false && elementNumber < 0) {
							return bestTimeTable;
						}

						// // 同じコース・クラスがないとき
						// else {
						// return bestTimeTable;
						// }
					}
				}
			}
		}

		bestTimeTable.setFixedDayOfWeek("不明");
		bestTimeTable.setFixedPeriod(0);

		return bestTimeTable;
	}

	/*
	 * 推奨コースの要素番号を3次の時間割から取得
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
	private int getInTimeTable3BestNumber(int candidate_number, int grade, String semester, String course_class,
			String best_course) {

		// すべての要素が同じものを探す
		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			for (int addPeriod = 0; addPeriod < f_NewTimeTableData3.get(number).getClassOfGrade()
					.getNumber(); addPeriod++) {

				// 2限目～4限目のとき
				if (2 <= f_NewTimeTableData3.get(number).getPeriod(candidate_number) + addPeriod
						&& f_NewTimeTableData3.get(number).getPeriod(candidate_number) + addPeriod <= 4) {

					// 同じ学年、同じ学期、選んでほしいコース・クラスが既存の時間割にあるとき
					if (f_NewTimeTableData3.get(number).getClassOfGrade().getGrade() == grade
							&& Objects.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getSemester(), semester)
							&& Objects.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass(),
									best_course)) {

						// 3次の時間割に存在するかを確認
						int elementNumber = getInTimeTable3Number(candidate_number,
								f_NewTimeTableData3.get(number).getDayOfWeek(candidate_number),
								f_NewTimeTableData3.get(number).getPeriod(candidate_number) + addPeriod, grade,
								semester, course_class, true, false, false, false);

						if (elementNumber < 0) {
							return number;
						}
					}
				}
			}

		}
		return -1;
	}

	/*
	 * 指定したコース・クラスから重複しないコース・クラスを取得する
	 *
	 * @param course_class コース・クラス
	 *
	 * @param num 複数コースがある場合に使用(1クラスだけは何でもよい)
	 *
	 * @return 重複しないコース・クラス
	 */
	private String getBestCourse(String course_class, int num) {

		String bestCourse = null;

		if (course_class == null) {
			System.out.println("コースが" + course_class);
			InOutPut.anyKey();
		}

		switch (course_class) {

		case "奇数":

			if (num == 0) {
				bestCourse = "エレ情(偶数)";
			} else {
				bestCourse = "偶数";
			}

			return bestCourse;

		case "偶数":

			if (num == 0) {
				bestCourse = "エレ情(奇数)";
			}

			else {
				bestCourse = "奇数";
			}
			return bestCourse;

		case "ab":

			if (num == 0) {
				bestCourse = "bc";
			}

			else {
				bestCourse = "c";
			}

			return bestCourse;

		case "bc":

			if (num == 0) {
				bestCourse = "ab";
			}

			else {
				bestCourse = "a";
			}

			return bestCourse;

		case "a":

			if (num == 0) {
				bestCourse = "b";
			}

			else if (num == 1) {
				bestCourse = "c";
			}

			else {
				bestCourse = "bc";
			}

			return bestCourse;

		case "b":

			if (num == 0) {
				bestCourse = "a";
			}

			else {
				bestCourse = "c";
			}

			return bestCourse;

		case "c":

			if (num == 0) {
				bestCourse = "ab";
			}

			else if (num == 1) {
				bestCourse = "a";
			}

			else {
				bestCourse = "b";
			}

			return bestCourse;

		case "エレ情(奇数)":

			if (num == 0) {
				bestCourse = "エレ情(偶数)";
			}

			else {
				bestCourse = "エネ環";
			}

			return bestCourse;

		case "エレ情(偶数)":

			if (num == 0) {
				bestCourse = "エレ情(奇数)";
			}

			else {
				bestCourse = "エネ環";
			}

			return bestCourse;

		case "エレ情":

			bestCourse = "エネ環";
			return bestCourse;

		case "エネ環":

			if (num == 0) {
				bestCourse = "エレ情(奇数)";
			}

			else if (num == 1) {
				bestCourse = "エレ情(偶数)";
			}

			else {
				bestCourse = "エレ情";
			}

			return bestCourse;

		default:
			bestCourse = "再履修";
			return bestCourse;
		}
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

			return true;
		}

		// 2次の時間割と重複するとき
		if (checkDuplication2(candidate, number)) {

			return true;
		}

		// 3次の時間割と重複するとき
		if (checkDuplication3(candidate, number)) {

			return true;
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
			if (f_NewTimeTableData3.get(number).getDayOfWeek(candidate)
					.equals(f_TimeTableData1.get(number1).getFixedDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_NewTimeTableData3.get(number).getClassOfGrade().getSemester()
						.equals(f_TimeTableData1.get(number1).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade().getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number1).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getFixedPeriod() + addPeriod == f_NewTimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1).getClassOfGrade().getCourseOrClass(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass())) {

									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number1).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getFixedPeriod() + addPeriod == f_NewTimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
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
			if (f_NewTimeTableData3.get(number).getDayOfWeek(candidate)
					.equals(f_TimeTableData2.get(number2).getFixedDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_NewTimeTableData3.get(number).getClassOfGrade().getSemester()
						.equals(f_TimeTableData2.get(number2).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade().getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2.get(number2).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getFixedPeriod() + addPeriod == f_NewTimeTableData3
									.get(number).getPeriod(candidate)) {

								return true;
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2.get(number2).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getFixedPeriod() + addPeriod == f_NewTimeTableData3
									.get(number).getFixedPeriod()) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData2.get(number2).getClassOfGrade().getTeachers().getName(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
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

		// 会議の曜日のとき
		if (f_NewTimeTableData3.get(number).getDayOfWeek(candidate).equals(CONFERENCE_DAY)) {

			// 会議の限目のとき
			if (f_NewTimeTableData3.get(number).getPeriod(candidate) == CONFERENCE_PERIOD) {
				return true;
			}
		}

		for (int number3 = 0; number3 < f_NewTimeTableData3.size(); number3++) {

			// まったく同じデータがあるとき
			if (number3 == number) {

				continue;
			}

			// 同じ曜日のとき
			if (f_NewTimeTableData3.get(number).getDayOfWeek(candidate)
					.equals(f_NewTimeTableData3.get(number3).getDayOfWeek(candidate))) {

				// 同じ前期・後期のとき
				if (f_NewTimeTableData3.get(number).getClassOfGrade().getSemester()
						.equals(f_NewTimeTableData3.get(number3).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_NewTimeTableData3.get(number3).getClassOfGrade().getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_NewTimeTableData3.get(number3).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_NewTimeTableData3.get(number3).getPeriod(candidate) + addPeriod == f_NewTimeTableData3
									.get(number).getPeriod(candidate)) {

								// 電気電子工学概論のとき
								if ("電気電子工学概論"
										.equals(f_NewTimeTableData3.get(number3).getClassOfGrade().getSubject())) {

									// 電気電子工学概論のとき
									if ("電気電子工学概論"
											.equals(f_NewTimeTableData3.get(number).getClassOfGrade().getSubject())) {
										continue;
									}
								}

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_NewTimeTableData3.get(number3).getClassOfGrade().getTeachers().getName(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_NewTimeTableData3.get(number3).getClassOfGrade().getCourseOrClass(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getCourseOrClass())) {

									return true;
								}

							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_NewTimeTableData3.get(number3).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_NewTimeTableData3.get(number3).getPeriod(candidate) + addPeriod == f_NewTimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_NewTimeTableData3.get(number3).getClassOfGrade().getTeachers().getName(),
										f_NewTimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
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
	 * 3次の科目を除く生徒のコマ数をカウントする
	 */
	private void countFirstNumOfStudent() {

		int tmpDayOfWeek = -1;
		int tmpPeriod = 0;

		// 1次の科目をカウント
		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			for (int studentNum = 0; studentNum < f_NewStudentData.size(); studentNum++) {

				// 同じ学年のとき
				if (f_NewStudentData.get(studentNum).getGrade() == f_TimeTableData1.get(number1).getClassOfGrade()
						.getGrade()) {

					// 同じ学期のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade().getSemester()
							.equals(f_NewStudentData.get(studentNum).getSemester())) {

						// 生徒のデータのコース・クラスに含まれるとき
						if (checkIncludeCourseOrClass(
								f_TimeTableData1.get(number1).getClassOfGrade().getCourseOrClass(),
								f_NewStudentData.get(studentNum).getCourseOrClass())) {

							tmpDayOfWeek = TimeTable
									.changeDayToValue(f_TimeTableData1.get(number1).getFixedDayOfWeek());

							for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number1).getClassOfGrade()
									.getNumber(); addPeriod++) {

								tmpPeriod = f_TimeTableData1.get(number1).getFixedPeriod() + addPeriod - 1;

								// 生徒の曜日限目のコマ数に入れる
								f_NewStudentData.get(studentNum).setDayPeriodNumber(0, tmpDayOfWeek, tmpPeriod);
							}
						}
					}
				}
			}

		}

		// 2次の科目をカウント
		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			for (int studentNum = 0; studentNum < f_NewStudentData.size(); studentNum++) {

				// 土曜日のとき
				if (f_TimeTableData2.get(number2).getFixedDayOfWeek().equals("土")) {
					continue;// カウントしない
				}

				// 0限目のとき
				if (f_TimeTableData2.get(number2).getFixedPeriod() == 0) {
					continue;// カウントしない
				}

				// 生徒のデータと同じ学年のとき
				if (f_NewStudentData.get(studentNum).getGrade() == f_TimeTableData2.get(number2).getClassOfGrade()
						.getGrade()) {

					// 同じ学期のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade().getSemester()
							.equals(f_NewStudentData.get(studentNum).getSemester())) {

						tmpDayOfWeek = TimeTable.changeDayToValue(f_TimeTableData2.get(number2).getFixedDayOfWeek());

						for (int addPeriod = 0; addPeriod < f_TimeTableData2.get(number2).getClassOfGrade()
								.getNumber(); addPeriod++) {

							tmpPeriod = f_TimeTableData2.get(number2).getFixedPeriod() + addPeriod - 1;

							f_NewStudentData.get(studentNum).setDayPeriodNumber(0, tmpDayOfWeek, tmpPeriod);
						}
					}
				}
			}
		}

		// ほかの候補もカウント
		for (int candidate = 1; candidate < CANDIDATE_NUM; candidate++) {

			for (int studentNum = 0; studentNum < f_NewStudentData.size(); studentNum++) {

				for (int day = 0; day <= MAX_DAY; day++) {

					for (int period = 1; period <= MAX_PERIOD; period++) {

						// Student tmpStudentData = new Student();

						// tmpStudentData.updateDayPeriodNumber(tmpStudentData.getDayPeriodNumbers(),
						// f_NewStudentData.get(studentNum).getDayPeriodNumbers());

						f_NewStudentData.get(studentNum).updateDayPeriodNumber(candidate,day,period-1,
								f_NewStudentData.get(studentNum).getDayPeriodNumbers(),0,day,period-1,
								f_NewStudentData.get(studentNum).getDayPeriodNumbers());
					}
				}
			}
		}
	}

	/*
	 * 指定した候補の3次の科目の生徒のコマ数をカウントする
	 *
	 * @param candidate 候補番号
	 */
	private void countNewTimeTable3NumOfStudent(int candidate) {

		for (int number3 = 0; number3 < f_NewTimeTableData3.size(); number3++) {

			for (int studentNum = 0; studentNum < f_NewStudentData.size(); studentNum++) {

				// 生徒のデータと同じ学年のとき
				if (f_NewStudentData.get(studentNum).getGrade() == f_NewTimeTableData3.get(number3).getClassOfGrade()
						.getGrade()) {

					// 同じ学期のとき
					if (f_NewTimeTableData3.get(number3).getClassOfGrade().getSemester()
							.equals(f_NewStudentData.get(studentNum).getSemester())) {

						// コース・クラスが生徒のデータに含まれているとき
						if (checkIncludeCourseOrClass(
								f_NewTimeTableData3.get(number3).getClassOfGrade().getCourseOrClass(),
								f_NewStudentData.get(studentNum).getCourseOrClass())) {

							int tmpDayOfWeek = TimeTable
									.changeDayToValue(f_NewTimeTableData3.get(number3).getDayOfWeek(candidate));

							for (int addPeriod = 0; addPeriod < f_NewTimeTableData3.get(number3).getClassOfGrade()
									.getNumber(); addPeriod++) {

								int tmpPeriod = f_NewTimeTableData3.get(number3).getPeriod(candidate) + addPeriod - 1;

								// 生徒の曜日限目のコマ数に入れる
								f_NewStudentData.get(studentNum).setDayPeriodNumber(candidate, tmpDayOfWeek, tmpPeriod);
							}
						}
					}
				}
			}
		}
	}

	/*
	 * 生徒のコマ数をカウントする
	 */
	private void countNumOfStudent() {

		resetNumOfStudent();// 生徒のコマ数をリセットする

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {
			countNewTimeTable3NumOfStudent(candidate);// 初期集団生成後の3次の科目の生徒のコマ数をカウント
		}
	}

	/*
	 * コース・クラスが含まれているかチェック
	 *
	 * @param course_or_class 科目のデータに記載されるコース・クラス
	 *
	 * @param courses_or_classes 生徒のデータに記載されるコース・クラス
	 *
	 * @return true 含む
	 *
	 * @return false 含まない
	 */
	private boolean checkIncludeCourseOrClass(String course_or_class, String courses_or_classes) {

		// 共通クラスのとき
		if (course_or_class.equals("共通")) {
			return true;
		}

		// aクラスのとき
		if (course_or_class.equals("a")) {

			if (courses_or_classes.contains("a(")) {

				return true;
			}

			return false;
		}

		// bクラスのとき
		if (course_or_class.equals("b")) {

			if (courses_or_classes.contains("b(")) {

				return true;
			}

			return false;
		}

		// cクラスのとき
		if (course_or_class.equals("c")) {

			if (courses_or_classes.contains("c(")) {

				return true;
			}

			return false;
		}

		// abクラスのとき
		if (course_or_class.equals("ab")) {

			if (courses_or_classes.contains("a")) {

				return true;
			}

			if (courses_or_classes.contains("b")) {

				if (courses_or_classes.contains("a")) {
					return true;
				}
			}

			return false;
		}

		// bcクラスのとき
		if (course_or_class.equals("bc")) {

			if (courses_or_classes.contains("c")) {

				return true;
			}

			if (courses_or_classes.contains("b")) {

				if (courses_or_classes.contains("c")) {
					return true;
				}
			}

			return false;
		}

		// 奇数クラスのとき
		if (course_or_class.equals("奇数")) {

			if (courses_or_classes.contains("奇数")) {

				return true;
			}

			return false;
		}

		// 偶数クラスのとき
		if (course_or_class.equals("偶数")) {

			if (courses_or_classes.contains("偶数")) {

				return true;
			}

			return false;
		}

		// エレ情クラスのとき
		if (course_or_class.equals("エレ情")) {

			if (courses_or_classes.contains("エレ情")) {
				return true;
			}

			return false;
		}

		// エレ情(奇数)クラスのとき
		if (course_or_class.equals("エレ情(奇数)")) {

			if (courses_or_classes.contains("エレ情(奇数)")) {

				return true;
			}

			return false;
		}

		// エレ情(偶数)クラスのとき
		if (course_or_class.equals("エレ情(偶数)")) {

			if (courses_or_classes.contains("エレ情(偶数)")) {

				return true;
			}

			return false;
		}

		// エネ環クラスのとき
		if (course_or_class.equals("エネ環")) {

			if (courses_or_classes.contains("エネ環")) {

				return true;
			}

			return false;
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
				System.out.print(f_TeacherData.get(teacherNum).getName() + "先生,");
				System.out.print("全" + f_TeacherData.get(teacherNum).getNumOfAllSubject() + "コマ,");
				System.out.println("新規" + f_TeacherData.get(teacherNum).getNumOfNewSubject() + "コマ");

			}
			System.out.println();
		}

		countNumOfSubject2OfTeacher();// 2次の科目の担当教員のコマ数を数える

		if (DEBUG) {

			for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
				System.out.print(f_TeacherData.get(teacherNum).getName() + "先生,");
				System.out.print("全" + f_TeacherData.get(teacherNum).getNumOfAllSubject() + "コマ,");
				System.out.println("新規" + f_TeacherData.get(teacherNum).getNumOfNewSubject() + "コマ");
			}
			System.out.println();
		}

		countNumOfSubject3OfTeacher();// 3次の科目の担当教員のコマ数を数える

		if (DEBUG) {

			for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {
				System.out.print(f_TeacherData.get(teacherNum).getName() + "先生,");
				System.out.print("全" + f_TeacherData.get(teacherNum).getNumOfAllSubject() + "コマ,");
				System.out.println("新規" + f_TeacherData.get(teacherNum).getNumOfNewSubject() + "コマ");
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
						f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName())) {

					num += f_TimeTableData1.get(number1).getClassOfGrade().getNumber();// 担当教員の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
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
						f_TimeTableData2.get(number2).getClassOfGrade().getTeachers().getName())) {

					num += f_TimeTableData2.get(number2).getClassOfGrade().getNumber();// 担当教員の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
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
						f_ClassOfGradeData3.get(number3).getTeachers().getName())) {

					num += f_ClassOfGradeData3.get(number3).getNumber();// 担当教員の持ちコマ数を増やす

					newNum += f_ClassOfGradeData3.get(number3).getTeachers().getNumOfNewSubject();// 新規の持ちコマ数を増やす
				}
			}

			f_TeacherData.get(teacherNum).setNumOfAllSubject(f_TeacherData.get(teacherNum).getNumOfAllSubject() + num);
			f_TeacherData.get(teacherNum)
					.setNumOfNewSubject(f_TeacherData.get(teacherNum).getNumOfNewSubject() + newNum);
		}

	}

	/*
	 * 学年ごとの授業を最初の時間割にセットする
	 */
	private void setClassOfGrade3ToFirstTimeTable3() {

		// 学年ごとの授業のみセットする
		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {
			TimeTable timeTableData = new TimeTable();
			// timeTableData = new TimeTable();
			timeTableData.setClassOfGrade(f_ClassOfGradeData3.get(number));
			f_TimeTableData3.add(timeTableData);
		}

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			if (DEBUG) {
				System.out.println("候補" + (candidate + 1) + "の時間割の初期集団を作成");
			}

			for (int number = 0; number < f_TimeTableData3.size(); number++) {

				// 重複がある限り繰り返す
				do {

					makeFirstTimeTable3(candidate, number);// 最初の3次の時間割を決める

					// 重複している回数があまりにも多いとき
					if (10000 < f_NumOfDuplication) {
						resetTimeTableData3(candidate);
						break;
					}

				} while (checkFirstDuplication(candidate, number));

				if (10000 < f_NumOfDuplication) {
					candidate -= 1;
					f_NumOfDuplication = 0;
					break;
				}

				f_NumOfDuplication = 0;
				// if(number==f_ClassOfGradeData3.size()-1) {
				// break;
				// }
			}
		}

		for (TimeTable timeTable : f_TimeTableData3) {

			f_NewTimeTableData3.add(new TimeTable(timeTable));
		}

		countNumOfStudent();// 生徒のコマ数をカウント

		calcDayPeriodFirstEvaluationValue();// 評価値の計算をする

		updateStudentData();// 生徒のデータを更新をする

		f_OldEvaluationValue = getSumEvaluationValue();// 最初の0代目の評価値の合計を取得

		if (DEBUG) {
			System.out.println("0代目の評価値:" + f_OldEvaluationValue);
		}

		Evaluation evaluation = new Evaluation();

		evaluation.setGeneration(0);

		for (int number = 0; number < f_EvaluationValues.size(); number++) {
			evaluation.addEvaluationValues(f_EvaluationValues.get(number));
		}

		evaluation.setSumEvaluationValue(f_OldEvaluationValue);

		f_EvaluationData.add(evaluation);

	}

	/*
	 * 最初の3次の時間割を決める
	 *
	 * @param number 番目
	 */
	private void makeFirstTimeTable3(int candidate, int number) {

		int randomDay;
		int randomPeriod;

		// ランダムに決定
		randomDay = Calculation.getRnd(TimeTable.changeDayToValue("月"), MAX_DAY);// 曜日をランダムに決定
		randomPeriod = Calculation.getRnd(1, MAX_PERIOD);// 限目をランダムに決定

		// 3次の時間割のデータとして設定する
		f_TimeTableData3.get(number).setDayOfWeek(candidate, f_TimeTableData3.get(number).getArrayOfDayOfWeek(),
				TimeTable.changeValueToDay(randomDay));// 曜日を設定
		f_TimeTableData3.get(number).setPeriod(candidate, f_TimeTableData3.get(number).getArrayOfPeriod(),
				randomPeriod);// 限目を設定

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
	private boolean checkFirstDuplication(int candidate, int number) {

		// 1次の時間割と重複するとき
		if (checkFirstDuplication1(candidate, number)) {
			f_NumOfDuplication += 1;
			return true;
		}

		// 2次の時間割と重複するとき
		if (checkFirstDuplication2(candidate, number)) {
			f_NumOfDuplication += 1;
			return true;
		}

		// 3次の時間割と重複するとき
		if (checkFirstDuplication3(candidate, number)) {
			f_NumOfDuplication += 1;
			return true;
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
	private boolean checkFirstDuplication1(int candidate, int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (f_TimeTableData3.get(number).getDayOfWeek(candidate)
					.equals(f_TimeTableData1.get(number1).getFixedDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData3.get(number).getClassOfGrade().getSemester()
						.equals(f_TimeTableData1.get(number1).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade().getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number1).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getFixedPeriod() + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName(),
										f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData1.get(number1).getClassOfGrade().getCourseOrClass(),
										f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass())) {
									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1.get(number1).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getFixedPeriod() + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData1.get(number1).getClassOfGrade().getTeachers().getName(),
										f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
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
	private boolean checkFirstDuplication2(int candidate, int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (f_TimeTableData3.get(number).getDayOfWeek(candidate)
					.equals(f_TimeTableData2.get(number2).getFixedDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData3.get(number).getClassOfGrade().getSemester()
						.equals(f_TimeTableData2.get(number2).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade().getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2.get(number2).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getFixedPeriod() + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								return true;
							}

						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2.get(number2).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getFixedPeriod() + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData2.get(number2).getClassOfGrade().getTeachers().getName(),
										f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {
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
	private boolean checkFirstDuplication3(int candidate, int number) {

		// 会議の曜日のとき
		if (f_TimeTableData3.get(number).getDayOfWeek(candidate).equals(CONFERENCE_DAY)) {

			// 会議の限目のとき
			if (f_TimeTableData3.get(number).getPeriod(candidate) == CONFERENCE_PERIOD) {
				return true;
			}
		}

		for (int number3 = 0; number3 < f_TimeTableData3.size(); number3++) {

			if (number <= number3) {
				break;
			}

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData3.get(number3).getDayOfWeek(candidate),
					f_TimeTableData3.get(number).getDayOfWeek(candidate))) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData3.get(number3).getClassOfGrade().getSemester(),
						f_TimeTableData3.get(number).getClassOfGrade().getSemester())) {

					// 同じ学年のとき
					if (f_TimeTableData3.get(number3).getClassOfGrade().getGrade() == f_TimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3.get(number3).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod(candidate) + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								// 電気電子工学概論どうしのとき
								if ("電気電子工学概論".equals(f_TimeTableData3.get(number3).getClassOfGrade().getSubject())) {

									// 電気電子工学概論のとき
									if ("電気電子工学概論"
											.equals(f_TimeTableData3.get(number).getClassOfGrade().getSubject())) {
										return false;
									}
								}

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData3.get(number3).getClassOfGrade().getTeachers().getName(),
										f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {

									return true;
								}

								// コース・クラスが重複するとき
								if (checkDuplicationCouseOrClass(
										f_TimeTableData3.get(number3).getClassOfGrade().getCourseOrClass(),
										f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass()) == true) {

									return true;
								}
							}
						}
					}

					// 違う学年のとき
					else {

						for (int addPeriod = 0; addPeriod < f_TimeTableData3.get(number3).getClassOfGrade()
								.getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData3.get(number3).getPeriod(candidate) + addPeriod == f_TimeTableData3
									.get(number).getPeriod(candidate)) {

								// 担当教員と重複するとき
								if (checkDuplicationTeacher(
										f_TimeTableData3.get(number3).getClassOfGrade().getTeachers().getName(),
										f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName())) {

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
	 * 担当教員が重複しているかのチェック
	 *
	 * @param teacher1 教員1
	 *
	 * @param teacher2 教員2
	 *
	 * @return true 重複している
	 *
	 * @return false 重複していない
	 */
	private boolean checkDuplicationTeacher(String teacher1, String teacher2) {

		// 同じ担当教員のとき
		if (teacher1.equals(teacher2)) {
			return true;
		}

		// 担当教員に含まれているとき
		if (teacher1.contains(teacher2)) {
			return true;
		}

		// 担当教員に含まれているとき
		if (teacher2.contains(teacher1)) {
			return true;
		}

		// 複数の担当教員どうしの重複チェック
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			// 両方とも同じ担当教員が含まれているとき
			if (teacher1.contains(f_TeacherData.get(teacherNum).getName())) {

				if (teacher2.contains(f_TeacherData.get(teacherNum).getName())) {
					return true;
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
	private boolean checkDuplicationCouseOrClass(String course_or_class1, String course_or_class2) {

		// 同じクラスのとき
		if (course_or_class1.equals(course_or_class2)) {

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

			return true;
		}

		// 奇数クラスと被るとき
		if (course_or_class1.equals("奇数")) {

			// 偶数クラスのとき
			if (course_or_class2.equals("偶数")) {

				return false;
			}

			// エレ情(偶数)クラスのとき
			if (course_or_class2.equals("エレ情(偶数)")) {
				return false;
			}

			return true;
		}

		// 偶数クラスと被るとき
		if (course_or_class1.equals("偶数")) {

			// 奇数クラスのとき
			if (course_or_class2.equals("奇数")) {

				return false;
			}

			// エレ情(偶数)クラスのとき
			if (course_or_class2.equals("エレ情(奇数)")) {
				return false;
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

			return true;
		}

		// エレ情(奇数)クラスと被るとき
		if (course_or_class1.equals("エレ情(奇数)")) {

			// 偶数クラスのとき
			if (course_or_class2.equals("偶数")) {
				return false;
			}

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (course_or_class2.equals("エレ情(偶数)")) {
				return false;
			}

			return true;
		}

		// エレ情(偶数)クラスと被るとき
		if (course_or_class1.equals("エレ情(偶数)")) {

			// 奇数クラスのとき
			if (course_or_class2.equals("奇数")) {
				return false;
			}

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (course_or_class2.equals("エレ情(奇数)")) {
				return false;
			}

			return true;
		}

		// エレ情クラスと被るとき
		if (course_or_class1.equals("エレ情")) {

			// エネ環クラスのとき
			if (course_or_class2.equals("エネ環")) {
				return false;
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

			return true;
		}

		// 共通クラスと被るとき
		if (course_or_class1.equals("共通")) {

			return true;

		}

		// 再履修クラスと被るとき
		if (course_or_class1.equals("再履修")) {

			// 再履修クラスのとき
			if (course_or_class2.equals("再履修")) {

				return false;

			}

			return true;
		}

		return true;
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
		readStudentDataFile();// 生徒のデータのファイルを読み込む
		countNumOfSubjectOfTeacher();// 先生のコマ数をカウントする
		// super.calctTeacherEvaluationValue();// 担当者決めの評価値を計算する
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
		writeStudentDataFile();// 生徒のデータを書き込む
		writeStudentDataBestFile();// 最も評価値が高い生徒のデータを書き込む
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

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			PrintWriter output;
			output = FileIO.writeFile("room_3_" + (candidate + 1) + ".csv", false);

			System.out.println("担当者が決まった3次のファイル" + "room_3_" + (candidate + 1) + ".csv" + "に書き込みます。");

			output.print("曜日,");// 曜日
			output.print("限目,");// 限
			output.print("コマ数,");// コマ数
			output.print("学年,");// 学年
			output.print("前期後期,");// 前期後期
			output.print("科目名,");// 科目名
			output.print("担当教員,");// 担当教員
			output.print("教室,");// 教室
			output.println("コース・クラス");// コース・クラス

			for (int number = 0; number < f_TimeTableData3.size(); number++) {
				output.print(f_TimeTableData3.get(number).getDayOfWeek(candidate) + ",");// 曜日
				output.print(f_TimeTableData3.get(number).getPeriod(candidate) + ",");// 限
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getNumber() + ",");// コマ数
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade() + ",");// 学年
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getSemester() + ",");// 前期後期
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getSubject() + ",");// 科目名
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName() + ",");// 担当教員
				output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
				output.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass());// コース・クラス
			}

			output.close();
			System.out.println("room_3_" + (candidate + 1) + ".csv" + "へ書き込みました。");

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

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			output.print(f_TimeTableData3.get(number).getDayOfWeek(bestCandidate) + ",");// 曜日
			output.print(f_TimeTableData3.get(number).getPeriod(bestCandidate) + ",");// 限
			output.print(f_TimeTableData3.get(number).getClassOfGrade().getNumber() + ",");// コマ数
			output.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade() + ",");// 学年
			output.print(f_TimeTableData3.get(number).getClassOfGrade().getSemester() + ",");// 前期後期
			output.print(f_TimeTableData3.get(number).getClassOfGrade().getSubject() + ",");// 科目名
			output.print(f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName() + ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass());// コース・クラス

		}

		output.close();
		System.out.println(FILE3_NAME + "へ書き込みました。");
	}

	/*
	 * 最初の3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFirstFile3() {

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {
			PrintWriter output;
			output = FileIO.writeFile("roomFirst_3_" + (candidate + 1) + ".csv", false);

			System.out.println("担当者が決まった3次のファイル" + "room_3_" + (candidate + 1) + ".csv" + "に書き込みます。");

			output.print("曜日,");// 曜日
			output.print("限目,");// 限
			output.print("コマ数,");// コマ数
			output.print("学年,");// 学年
			output.print("前期後期,");// 前期後期
			output.print("科目名,");// 科目名
			output.print("担当教員,");// 担当教員
			output.print("教室,");// 教室
			output.println("コース・クラス");// コース・クラス

			for (int number = 0; number < f_TimeTableData3.size(); number++) {
				output.print(f_TimeTableData3.get(number).getDayOfWeek(candidate) + ",");// 曜日
				output.print(f_TimeTableData3.get(number).getPeriod(candidate) + ",");// 限
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getNumber() + ",");// コマ数
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getGrade() + ",");// 学年
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getSemester() + ",");// 前期後期
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getSubject() + ",");// 科目名
				output.print(f_TimeTableData3.get(number).getClassOfGrade().getTeachers().getName() + ",");// 担当教員
				output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
				output.println(f_TimeTableData3.get(number).getClassOfGrade().getCourseOrClass());// コース・クラス
			}

			output.close();
			System.out.println("room_3_" + (candidate + 1) + ".csv" + "へ書き込みました。");

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

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {
			output.print("候補" + (candidate + 1) + ",");
		}

		output.println("評価値");// 評価値

		for (int number = 0; number < f_EvaluationData.size(); number++) {
			output.print(f_EvaluationData.get(number).getGeneration() + ",");// 世代

			for (int candidateNum = 0; candidateNum < f_EvaluationValues.size(); candidateNum++) {

				output.print(f_EvaluationData.get(number).getEvaluationValues(candidateNum) + ",");
			}
			output.println(f_EvaluationData.get(number).getSumEvaluationValue());// 評価値
		}

		output.close();
		System.out.println(EVALUATION_FILE + "へ書き込みました。");
	}

	/*
	 * 最もよかった候補の時間割の生徒のデータのファイルを書き込む
	 */
	private void writeStudentDataBestFile() {

		PrintWriter output;
		output = FileIO.writeFile("studentBestData.csv", false);

		System.out.println("もっとも評価値が高いときの生徒のデータのファイル" + "studentBestData.csv" + "に書き込みます。");

		output.print("学年,");// 学年

		output.print("前期・後期,");// 前期・後期

		output.print("コース・クラス,");// コース・クラス

		for (int day = 0; day <= MAX_DAY; day++) {

			for (int period = 1; period <= MAX_PERIOD; period++) {

				output.print(TimeTable.changeValueToDay(day) + period + ",");// 何曜日の何限目
			}
		}

		output.println("評価値");// 評価値

		int bestCandidate = getBestTimeTable();

		System.out.println("候補" + (bestCandidate + 1));

		for (int number = 0; number < f_StudentData.size(); number++) {

			output.print(f_StudentData.get(number).getGrade() + ",");// 学年

			output.print(f_StudentData.get(number).getSemester() + ",");// 学期

			output.print(f_StudentData.get(number).getCourseOrClass() + ",");// コース・クラス

			for (int day = 0; day <= MAX_DAY; day++) {

				for (int period = 1; period <= MAX_PERIOD; period++) {

					output.print(f_StudentData.get(number).getDayPeriodNumber(bestCandidate, day, period - 1) + ",");// 曜日限目のコマ数
				}

			}

			output.println(f_StudentData.get(number).getDayPeriodEvaluationValue(bestCandidate));// 評価値
		}

		output.close();
		System.out.println("studentBestData.csv" + "へ書き込みました。");
	}

	/*
	 * 生徒のデータのファイルを書き込む
	 */
	private void writeStudentDataFile() {

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			PrintWriter output;
			output = FileIO.writeFile("studentData" + (candidate + 1) + ".csv", false);

			System.out.println("生徒のデータのファイル" + "studentData" + (candidate + 1) + ".csv" + "に書き込みます。");

			output.print("学年,");// 学年

			output.print("前期・後期,");// 前期・後期

			output.print("コース・クラス,");// コース・クラス

			for (int day = 0; day <= MAX_DAY; day++) {

				for (int period = 1; period <= MAX_PERIOD; period++) {

					output.print(TimeTable.changeValueToDay(day) + period + ",");// 何曜日の何限目
				}
			}

			output.println("評価値");// 評価値

			for (int number = 0; number < f_StudentData.size(); number++) {
				output.print(f_StudentData.get(number).getGrade() + ",");// 学年

				output.print(f_StudentData.get(number).getSemester() + ",");// 学期

				output.print(f_StudentData.get(number).getCourseOrClass() + ",");// コース・クラス

				for (int day = 0; day <= MAX_DAY; day++) {

					for (int period = 1; period <= MAX_PERIOD; period++) {

						output.print(f_StudentData.get(number).getDayPeriodNumber(candidate, day, period - 1) + ",");// 曜日限目のコマ数
					}

				}

				output.println(f_StudentData.get(number).getDayPeriodEvaluationValue(candidate));// 評価値
			}

			output.close();
			System.out.println("studentData" + (candidate + 1) + ".csv" + "へ書き込みました。");
		}
	}

	/*
	 * 初期集団においての生徒のデータのファイルを書き込む
	 */
	private void writeFirstStudentDataFile() {

		for (int candidate = 0; candidate < CANDIDATE_NUM; candidate++) {

			PrintWriter output;
			output = FileIO.writeFile("studentFirstData" + (candidate + 1) + ".csv", false);

			System.out.println("担当者が決まった3次のファイル" + "studentFirstData" + (candidate + 1) + ".csv" + "に書き込みます。");

			output.print("学年,");// 学年

			output.print("前期・後期,");// 前期・後期

			output.print("コース・クラス,");// コース・クラス

			for (int day = 0; day <= MAX_DAY; day++) {

				for (int period = 1; period <= MAX_PERIOD; period++) {

					output.print(TimeTable.changeValueToDay(day) + period + ",");// 何曜日の何限目
				}
			}

			output.println("評価値");// 評価値

			for (int number = 0; number < f_StudentData.size(); number++) {
				output.print(f_StudentData.get(number).getGrade() + ",");// 学年

				output.print(f_StudentData.get(number).getSemester() + ",");// 学期

				output.print(f_StudentData.get(number).getCourseOrClass() + ",");// コース・クラス

				for (int day = 0; day <= MAX_DAY; day++) {

					for (int period = 1; period <= MAX_PERIOD; period++) {

						output.print(f_StudentData.get(number).getDayPeriodNumber(candidate, day, period - 1) + ",");// 曜日限目のコマ数
					}

				}

				output.println(f_StudentData.get(number).getDayPeriodEvaluationValue(candidate));// 評価値
			}

			output.close();
			System.out.println("studentFirstData" + (candidate + 1) + ".csv" + "へ書き込みました。");
		}
	}

	/*
	 * 生徒のデータのファイルを読み込む
	 */
	private void readStudentDataFile() {

		String[] strData = new String[STUDENT_DATA_NUM];

		BufferedReader input = FileIO.readFile(STUDENT_DATA_FILE);

		try {
			String line = new String();

			// 一番上から一番下の行まで読み込む
			for (int cols = 0; cols < STUDENT_COLS; cols++) {

				// 読み込んだ1行が空白でないとき
				if ((line = input.readLine()) != null) {

					// 上から1行以上のとき
					if (1 <= cols) {
						strData = line.split(",");

						// 曜日が空白でないとき
						if (strData[0] != null) {
							Student studentData = new Student();

							studentData.setGrade(Integer.parseInt(strData[0]));// 学年

							studentData.setSemester(strData[1]);// 学期

							studentData.setCourseOrClass(strData[2]);// コース・クラス

							f_NewStudentData.add(new Student(studentData));// 新規の生徒のデータの動的配列に追加
							// f_StudentData.add(studentData);// 生徒のデータの動的配列に追加
						}
					}
				}
			}

			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}

		for (Student student : f_NewStudentData) {

			f_StudentData.add(new Student(student));
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
							timeTableData.setFixedDayOfWeek(strData[0]);// 曜日
							timeTableData.setFixedPeriod(Integer.parseInt(strData[1]));// 限目
							timeTableData.getClassOfGrade().setNumber(Integer.parseInt(strData[2]));// コマ数
							timeTableData.getClassOfGrade().setGrade(Integer.parseInt(strData[3]));// 学年
							timeTableData.getClassOfGrade().setSemester(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名

							for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

								// 担当教員のデータに存在するとき
								if (strData[6].equals(f_TeacherData.get(teacherNum))) {
									timeTableData.getClassOfGrade().setTeachers(f_TeacherData.get(teacherNum));
									break;
								}

								if (teacherNum == f_TeacherData.size() - 1) {

									timeTableData.getClassOfGrade().getTeachers().setName(strData[6]);// 担当教員
								}
							}
							timeTableData.setClassRoom(strData[7]);// 教室
							timeTableData.getClassOfGrade().setCourseOrClass(strData[8]);// コース・クラス

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
						timeTableData.setFixedDayOfWeek(strData[0]);// 曜日
						timeTableData.setFixedPeriod(Integer.parseInt(strData[1]));// 限目
						timeTableData.getClassOfGrade().setNumber(Integer.parseInt(strData[2]));// コマ数
						timeTableData.getClassOfGrade().setGrade(Integer.parseInt(strData[3]));// 学年
						timeTableData.getClassOfGrade().setSemester(strData[4]);// 前期後期
						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名

						for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

							if (strData[6].equals(f_TeacherData.get(teacherNum).getName())) {
								timeTableData.getClassOfGrade().setTeachers(f_TeacherData.get(teacherNum));// 担当教員
								break;
							}

							if (teacherNum == f_TeacherData.size() - 1) {
								timeTableData.getClassOfGrade().getTeachers().setName(strData[6]);// 担当教員
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
							classOfGradeData.setNumber(Integer.parseInt(strData[0]));// コマ数
							classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
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
						classOfGradeData.setNumber(Integer.parseInt(strData[0]));// コマ数
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
						classOfGradeData.setNumber(Integer.parseInt(strData[0]));// コマ数
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// 学年
						classOfGradeData.setSemester(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名

						for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

							// 担当教員のデータに存在するとき
							if (strData[4].equals(f_TeacherData.get(teacherNum).getName())) {
								classOfGradeData.setTeachers(f_TeacherData.get(teacherNum));// 担当教員
								break;
							}

							// 担当教員のデータに存在しないとき
							if (teacherNum == f_TeacherData.size() - 1) {

								classOfGradeData.getTeachers().setName(strData[4]);// 担当教員
							}
						}

						// classOfGradeData.getTeachers().setName(strData[4]);//
						// 担当教員
						classOfGradeData.setCourseOrClass(strData[5]);// コース・クラス
						classOfGradeData.getTeachers().setNumOfNewSubject(Integer.parseInt(strData[6]));// 新規の科目
						classOfGradeData.setNeedPC(Integer.parseInt(strData[7]));// PCの必要性
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
