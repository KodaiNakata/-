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
public class Decide_dayAndPeriod extends Decide_faculty {

	private static final String EVALUATION_FILE = "DayPeriodEvaluationData.csv";// 日程の評価値のデータのファイル

	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 前期の時間割
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// 後期の時間割
	private TimeTable f_TimeTableDataTmp1;// 時間割のデータの交換1
	private TimeTable f_TimeTableDataTmp2;// 時間割のデータの交換2
	private double f_OldEvaluationValue;// 前の評価値
	private double f_NewEvaluationValue;// 新しい評価値
	private int f_RandomDayOfWeek1;// ランダムの曜日
	private int f_RandomPeriod1;// ランダムの限目
	private int f_RandomGrade1;// ランダムの学年
	private int f_RandomPreviousOrLatter;// 前期か後期か
	private int f_RandomDayOfWeek2;// ランダムの曜日
	private int f_RandomPeriod2;// ランダムの限目
	private int f_TmpNumber1;// 交換用の番目1
	private int f_TmpNumber2;// 交換用の番目2
	private int f_MutationNumber;// 突然変異の番号
	private ArrayList<TimeTable> f_NewTimeTableData3 = new ArrayList<TimeTable>();// 新規の3次の時間割のデータ
	private ArrayList<Evaluation> f_EvaluationData = new ArrayList<Evaluation>();// 評価値のデータ

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
					.getPreviousOrLatter()
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
					.getPreviousOrLatter()
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

		System.out.println("\n3次のデータ");

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
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
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_TimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

		System.out.println("\n3次のデータ(新しい時間割)");

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {
			System.out.print(f_NewTimeTableData3.get(number).getDayOfWeek()
					+ "曜日、");// 曜日
			System.out.print(f_NewTimeTableData3.get(number).getPeriod()
					+ "限目、");// 限目
			System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade()
					.getNumber()
					+ "コマ、");// コマ数
			System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade()
					.getGrade()
					+ "年、");// 学年
			System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade()
					.getPreviousOrLatter()
					+ "、");// 前期・後期
			System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade()
					.getSubject()
					+ "、");// 科目名
			System.out.print(f_NewTimeTableData3.get(number).getClassOfGrade()
					.getTeachers().getName()
					+ "先生、");// 担当教員
			System.out.println(f_NewTimeTableData3.get(number)
					.getClassOfGrade().getCourseOrClass()
					+ "クラス");// コース・クラス
		}
	}

	// ------------------------------------------------------//
	// ---------------------評価値---------------------------//
	// ------------------------------------------------------//
	/*
	 * 担当者の授業のコマ数の評価値
	 *
	 * @return 担当者の評価値
	 */
	private double getTeacherEvaluationValue() {

		double value = 0.0;

		// (担当教員数)-((持っているコマ数)/0.6^(新規コマ数))
		// ---------------------------------------------------
		// √(持っているコマ数)
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			if (f_TeacherData.get(teacherNum).getNumOfAllSubject() == 0) {
				continue;
			}

			value += (((double) f_TeacherData.size() - ((double) f_TeacherData
					.get(teacherNum).getNumOfAllSubject() / Calculation
					.getPowerRoot(0.6, f_TeacherData.get(teacherNum)
							.getNumOfNewSubject()))) / Calculation
					.getSqrt((double) f_TeacherData.get(teacherNum)
							.getNumOfAllSubject()));
		}

		return value;
	}

	/*
	 * 担当者の授業のコマ数の評価値
	 *
	 * @param teacher 担当者
	 *
	 * @return 担当者の評価値
	 */
	private double getTeacherEvaluationValue(String teacher) {

		double value = 1.0;

		// (担当教員数)-((持っているコマ数)/0.6^(新規コマ数))
		// ---------------------------------------------------
		// √(持っているコマ数)
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			if (f_TeacherData.get(teacherNum).getNumOfAllSubject() == 0) {
				continue;
			}

			value += (((double) f_TeacherData.size() - ((double) f_TeacherData
					.get(teacherNum).getNumOfAllSubject() / Calculation
					.getPowerRoot(0.6, f_TeacherData.get(teacherNum)
							.getNumOfNewSubject()))) / Calculation
					.getSqrt((double) f_TeacherData.get(teacherNum)
							.getNumOfAllSubject()));
		}

		return value;
	}

	/*
	 * 評価値の取得
	 *
	 * @return 評価値
	 */
	private double getEvaluationValue() {

		double value = 0.0;
		double dayPeriodValue = 0.0;

		if (DEBUG) {
			System.out.println("新しい時間割のデータが重複していないか調べる");

		}

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {
			// 時間割と重複するとき
			if (checkDuplication(number)) {

				if (DEBUG) {
					System.out.println("評価しない。");
				}

				return value;
			}
		}

		if (DEBUG) {
			System.out.println("重複していないので評価する。");
			// InOutPut.anyKey();
		}

		// 3次の曜日限目に対する先生の評価値
		for (int teacherNum = 0; teacherNum < f_TeacherData.size(); teacherNum++) {

			for (int day = TimeTable.changeDayToValue("月"); day <= MAX_DAY; day++) {

				for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

					// 同じ曜日のとき
					if (Objects.equals(TimeTable.changeValueToDay(day),
							f_NewTimeTableData3.get(number).getDayOfWeek())) {

						// 同じ担当教員のとき
						if (Objects.equals(f_TeacherData.get(teacherNum)
								.getName(), f_NewTimeTableData3.get(number)
								.getClassOfGrade().getTeachers().getName())) {

							dayPeriodValue += (double) f_NewTimeTableData3
									.get(number).getClassOfGrade().getNumber();
						}
					}
				}
			}

			value = value - dayPeriodValue;

			dayPeriodValue = 0.0;

		}

		return getTeacherEvaluationValue() - dayPeriodValue;
	}

	// /*
	// * 全体の評価値
	// *
	// * @param teacher 先生の名前
	// *
	// * @return 全体の評価値
	// */
	// private double getEvaluationValue(String teacher) {
	//
	// return getDayEvaluationValue() * getTeacherEvaluationValue(teacher);
	// }

	/*
	 * 評価値の計算をする
	 */
	private void calcEvaluationValue() {

		// f_NewEvaluationValue = getEvaluationValue();

		boolean canEvaluate = true;// 評価できるか

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			// 時間割と重複するとき
			if (checkDuplication(number)) {
				f_NewEvaluationValue = 0.0;
				canEvaluate = false;
			}
		}

		// 評価できるとき
		if (canEvaluate) {

			if (DEBUG) {
				System.out.println("評価する");
			}


			f_NewEvaluationValue = f_OldEvaluationValue + 1.0;
		}
		// for(int teacherNum=0;teacherNum<f_TeacherData.size();teacherNum++){
		// f_NewEvaluationValue=
		// }
		//
		// f_NewEvaluationValue = getDayEvaluationValue();
		//
		// for (int num = 0; num < f_TeacherData.size(); num++) {
		// f_NewEvaluationValue *= getTeacherEvaluationValue(f_TeacherData
		// .get(num).getName());
		// }
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
			indicateData();
			writeRoomAndDayAndPeriodFirstFile3();
			System.out.println("遺伝的アルゴリズムを開始する");
			InOutPut.anyKey();
		}

		exeGeneticAlgo();// 遺伝的アルゴリズムを実行する

		if (DEBUG) {
			System.out.println("評価値が更新された回数：" + f_EvaluationData.size() + "回");
		}
	}

	/*
	 * 遺伝的アルゴリズムを実行する
	 */
	private void exeGeneticAlgo() {

		for (int num = 0; num < CHECK_NUM; num++) {

			Evaluation evaluation = new Evaluation();
			boolean wasCrossedBoth = false;
			boolean wasMutated = false;

			System.out.println("遺伝的アルゴリズム" + (num + 1) + "回目");

			evaluation.setGeneration(num + 1);// num世代目

			choice();// 前期後期どちらかの何曜日の何限目の何学年かを選択する

			// 両方交叉するとき
			if (cross()) {
				wasCrossedBoth = true;
			}

			// 突然変異するとき
			if (mutation()) {
				wasMutated = true;
			}

			calcEvaluationValue();// 評価値の計算をする

			// 新しい評価値が上のとき
			if (f_OldEvaluationValue < f_NewEvaluationValue) {

				f_OldEvaluationValue = f_NewEvaluationValue;// 評価値を更新

				// 両方交叉したとき
				if (wasCrossedBoth) {

					TimeTable timeTable = new TimeTable();

					timeTable.setDayOfWeek(f_NewTimeTableData3
							.get(f_TmpNumber1).getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					timeTable.setPeriod(f_NewTimeTableData3.get(f_TmpNumber1)
							.getPeriod());// 両方交叉した要素番号の限目を更新

					f_TimeTableData3.get(f_TmpNumber1).setDayOfWeek(
							f_NewTimeTableData3.get(f_TmpNumber2)
									.getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					f_TimeTableData3.get(f_TmpNumber1).setPeriod(
							f_NewTimeTableData3.get(f_TmpNumber2).getPeriod());// 両方交叉した要素番号の限目を更新

					f_TimeTableData3.get(f_TmpNumber2).setDayOfWeek(
							timeTable.getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					f_TimeTableData3.get(f_TmpNumber2).setPeriod(
							timeTable.getPeriod());// 両方交叉した要素番号の限目を更新

				}

				// 片方だけ交叉したとき
				else {

					TimeTable timeTable = new TimeTable();

					timeTable.setDayOfWeek(f_NewTimeTableData3
							.get(f_TmpNumber2).getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					timeTable.setPeriod(f_NewTimeTableData3.get(f_TmpNumber2)
							.getPeriod());// 両方交叉した要素番号の限目を更新

					f_TimeTableData3.get(f_TmpNumber2).setDayOfWeek(
							timeTable.getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					f_TimeTableData3.get(f_TmpNumber2).setPeriod(
							timeTable.getPeriod());// 両方交叉した要素番号の限目を更新

				}

				// 突然変異したとき
				if (wasMutated) {

					TimeTable timeTable = new TimeTable();

					timeTable.setDayOfWeek(f_NewTimeTableData3.get(
							f_MutationNumber).getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					timeTable.setPeriod(f_NewTimeTableData3.get(
							f_MutationNumber).getPeriod());// 両方交叉した要素番号の限目を更新

					f_TimeTableData3.get(f_MutationNumber).setDayOfWeek(
							timeTable.getDayOfWeek());// 両方交叉した要素番号の曜日を更新
					f_TimeTableData3.get(f_MutationNumber).setPeriod(
							timeTable.getPeriod());// 両方交叉した要素番号の限目を更新
				}

				f_OldEvaluationValue = f_NewEvaluationValue;// 評価値を更新する

				if (DEBUG) {
					System.out.println("評価値が更新された。");
					System.out.println("評価値:" + f_NewEvaluationValue);
				}

				evaluation.setEvaluationValue(f_NewEvaluationValue);

				f_EvaluationData.add(evaluation);
			}

			// 新しい評価値以下のとき
			else {

				// 両方交叉したとき
				if (wasCrossedBoth) {

					f_NewTimeTableData3.get(f_TmpNumber1).setDayOfWeek(
							f_TimeTableData3.get(f_TmpNumber1).getDayOfWeek());// 両方交叉した要素番号の曜日を戻す

					f_NewTimeTableData3.get(f_TmpNumber1).setPeriod(
							f_TimeTableData3.get(f_TmpNumber1).getPeriod());// 両方交叉した要素番号の限目を戻す

					f_NewTimeTableData3.get(f_TmpNumber2).setDayOfWeek(
							f_TimeTableData3.get(f_TmpNumber2).getDayOfWeek());// 両方交叉した要素番号の曜日を戻す

					f_NewTimeTableData3.get(f_TmpNumber2).setPeriod(
							f_TimeTableData3.get(f_TmpNumber2).getPeriod());// 両方交叉した要素番号の限目を戻す

				}

				// 片方だけ交叉したとき
				else {
					f_NewTimeTableData3.get(f_TmpNumber2).setDayOfWeek(
							f_TimeTableData3.get(f_TmpNumber2).getDayOfWeek());// 片方だけ交叉した要素番号の曜日を戻す

					f_NewTimeTableData3.get(f_TmpNumber2).setPeriod(
							f_TimeTableData3.get(f_TmpNumber2).getPeriod());// 片方だけ交叉した要素番号の限目を戻す
				}

				// 突然変異したとき
				if (wasMutated) {

					f_NewTimeTableData3.get(f_MutationNumber).setDayOfWeek(
							f_TimeTableData3.get(f_MutationNumber)
									.getDayOfWeek());// 突然変異した要素番号の曜日を戻す

					f_NewTimeTableData3.get(f_MutationNumber).setPeriod(
							f_TimeTableData3.get(f_MutationNumber).getPeriod());// 突然変異した要素番号の限目を戻す
				}
			}
		}
	}

	/*
	 * 前期後期どちらかの何曜日の何限目の何学年かを選択
	 */
	private void choice() {

		f_TimeTableDataTmp1 = new TimeTable();
		f_TimeTableDataTmp2 = new TimeTable();

		// 曜日をランダムに選択
		f_RandomDayOfWeek1 = Calculation.getRnd(
				TimeTable.changeDayToValue("月"), MAX_DAY);

		// 限目をランダムに選択
		f_RandomPeriod1 = Calculation.getRnd(1, MAX_PERIOD);

		// 学年をランダムに選択
		f_RandomGrade1 = Calculation.getRnd(1, 4);

		// 前期か後期かをランダムに選択
		f_RandomPreviousOrLatter = Calculation.getRnd(
				ClassOfGrade.changePreviousOrLatterToValue("前期"),
				ClassOfGrade.changePreviousOrLatterToValue("後期"));

		f_TimeTableDataTmp1.setDayOfWeek(TimeTable
				.changeValueToDay(f_RandomDayOfWeek1));
		f_TimeTableDataTmp1.setPeriod(f_RandomPeriod1);

		// 時間割3のデータに存在する要素番号を取得
		f_TmpNumber1 = getInTimeTable3Number(f_RandomDayOfWeek1,
				f_RandomPeriod1, f_RandomGrade1, f_RandomPreviousOrLatter);

		if (DEBUG) {
			System.out.print(TimeTable.changeValueToDay(f_RandomDayOfWeek1)
					+ "曜日、");
			System.out.print(f_RandomPeriod1 + "限目、");
			System.out.print(f_RandomGrade1 + "年、");
			System.out.println(ClassOfGrade
					.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter)
					+ "が時間割3のデータに存在するか調べます\n");
		}

		// 時間割3のデータに存在するとき
		if (existInTimeTable3(
				TimeTable.changeValueToDay(f_RandomDayOfWeek1),
				f_RandomPeriod1,
				f_RandomGrade1,
				ClassOfGrade
						.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter))) {

			if (DEBUG) {
				System.out.println("時間割3のデータに存在する");

				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getDayOfWeek() + "曜日、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getPeriod() + "限目、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getNumber()
						+ "コマ、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getGrade()
						+ "年、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getPreviousOrLatter()
						+ "、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getSubject()
						+ "、");
				System.out.print(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getTeachers().getName()
						+ "先生、");
				System.out.println(f_NewTimeTableData3.get(f_TmpNumber1)
						.getClassOfGrade().getCourseOrClass()
						+ "クラス\n");
			}
		}

		// 時間割3のデータに存在しないとき
		else {

			if (DEBUG) {
				System.out.print(TimeTable.changeValueToDay(f_RandomDayOfWeek1)
						+ "曜日、");
				System.out.print(f_RandomPeriod1 + "限目、");
				System.out.print(f_RandomGrade1 + "年、");
				System.out
						.print(ClassOfGrade
								.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter)
								+ "は、");
				System.out.println("時間割3のデータに存在しない\n");
			}
		}

		// 時間割3のデータに存在しない
		// または、1つ目で選択したデータと被る
		// 上記の条件のとき繰り返す
		do {

			// 曜日をランダムに選択
			f_RandomDayOfWeek2 = Calculation.getRnd(
					TimeTable.changeDayToValue("月"), MAX_DAY);

			// 限目をランダムに選択
			f_RandomPeriod2 = Calculation.getRnd(1, MAX_PERIOD);

			// 要素番号を取得
			f_TmpNumber2 = getInTimeTable3Number(f_RandomDayOfWeek2,
					f_RandomPeriod2, f_RandomGrade1, f_RandomPreviousOrLatter);

			if (DEBUG) {
				System.out.print(TimeTable.changeValueToDay(f_RandomDayOfWeek2)
						+ "曜日、");
				System.out.print(f_RandomPeriod2 + "限目、");
				System.out.print(f_RandomGrade1 + "年、");
				System.out
						.println(ClassOfGrade
								.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter)
								+ "が時間割3のデータに存在するか調べます");
			}

		} while (!existInTimeTable3(
				TimeTable.changeValueToDay(f_RandomDayOfWeek2),
				f_RandomPeriod2,
				f_RandomGrade1,
				ClassOfGrade
						.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter)));

		f_TimeTableDataTmp2.setDayOfWeek(TimeTable
				.changeValueToDay(f_RandomDayOfWeek2));
		f_TimeTableDataTmp2.setPeriod(f_RandomPeriod2);

	}

	/*
	 * 既存の時間割のデータに存在するか
	 *
	 * @param time_table_data 既存の時間割のデータ
	 *
	 * @param day_of_week 曜日
	 *
	 * @param period 限目
	 *
	 * @param grade 学年
	 *
	 * @param previous_latter 前期後期
	 *
	 * @param time_table 新規の時間割のデータ
	 *
	 * @param tmp_number 交換用の番号(時間割のデータの要素番号)
	 *
	 * @return true 存在する
	 *
	 * @return false 存在しない
	 */
	private boolean existInTimeTable3(String day_of_week, int period,
			int grade, String previous_latter) {

		if (DEBUG) {
			System.out.print(day_of_week + "曜日、");
			System.out.print(period + "限目、");
			System.out.print(grade + "年、");
			System.out.print(previous_latter + "は");
		}

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			// ランダムに選択した曜日、限目、学年、前期後期が時間割3のデータに存在するとき
			if (Objects.equals(f_NewTimeTableData3.get(number).getDayOfWeek(),
					day_of_week)
					&& f_NewTimeTableData3.get(number).getPeriod() == period
					&& f_NewTimeTableData3.get(number).getClassOfGrade()
							.getGrade() == grade
					&& Objects.equals(f_NewTimeTableData3.get(number)
							.getClassOfGrade().getPreviousOrLatter(),
							previous_latter)) {

				if (DEBUG) {
					System.out.println("既存の時間割のデータに存在します。");
				}
				return true;
			}
		}

		if (DEBUG) {
			System.out.println("既存の時間割のデータに存在しません。\n");
		}

		return false;
	}

	/*
	 * 既存の時間割のデータの何番目に入っているか
	 *
	 * @param time_table_data 既存の時間割のデータ
	 *
	 * @param day_of_week 曜日
	 *
	 * @param period 限目
	 *
	 * @param grade 学年
	 *
	 * @param previous_latter 前期後期
	 *
	 * @param time_table 新規の時間割のデータ
	 *
	 * @return 既存の時間割のデータの要素番号
	 */
	private int getInTimeTable3Number(int day_of_week, int period, int grade,
			int previous_latter) {

		for (int number = 0; number < f_NewTimeTableData3.size(); number++) {

			// ランダムに選択した曜日、限目、学年、前期後期が時間割3のデータに存在するとき
			if (Objects.equals(f_NewTimeTableData3.get(number).getDayOfWeek(),
					TimeTable.changeValueToDay(day_of_week))
					&& f_NewTimeTableData3.get(number).getPeriod() == period
					&& f_NewTimeTableData3.get(number).getClassOfGrade()
							.getGrade() == grade
					&& Objects
							.equals(f_NewTimeTableData3.get(number)
									.getClassOfGrade().getPreviousOrLatter(),
									ClassOfGrade
											.changeValueToPreviousOrLatter(previous_latter))) {

				if (DEBUG) {
					System.out.println(number + "番目に存在します。");
				}
				return number;
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
		if (0 <= f_TmpNumber1) {

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

			f_NewTimeTableData3.get(f_TmpNumber2).setDayOfWeek(
					f_TimeTableDataTmp2.getDayOfWeek());
			f_NewTimeTableData3.get(f_TmpNumber2).setPeriod(
					f_TimeTableDataTmp2.getPeriod());

			return false;
		}
	}

	/*
	 * 突然変異する
	 *
	 * @return true 突然変異する
	 *
	 * @return false 突然変異しない
	 */
	private boolean mutation() {

		f_TimeTableDataTmp1 = new TimeTable();

		int num = Calculation
				.getRnd(1, ORDER1_COLS + ORDER2_COLS + ORDER3_COLS);// 3次の科目数/全科目数の確率

		// 1から3次の科目数までのとき
		if (num <= ORDER3_COLS) {

			// 時間割3のデータに存在しない
			// または、1つ目で選択したデータと被る
			// 上記の条件のとき繰り返す
			do {

				// 曜日をランダムに選択
				f_RandomDayOfWeek1 = Calculation.getRnd(
						TimeTable.changeDayToValue("月"), MAX_DAY);

				// 限目をランダムに選択
				f_RandomPeriod1 = Calculation.getRnd(1, MAX_PERIOD);

				// 学年をランダムに選択
				f_RandomGrade1 = Calculation.getRnd(1, 4);

				// 前期か後期かをランダムに選択
				f_RandomPreviousOrLatter = Calculation.getRnd(
						ClassOfGrade.changePreviousOrLatterToValue("前期"),
						ClassOfGrade.changePreviousOrLatterToValue("後期"));

				f_TimeTableDataTmp1.setDayOfWeek(TimeTable
						.changeValueToDay(f_RandomDayOfWeek1));
				f_TimeTableDataTmp1.setPeriod(f_RandomPeriod1);

				// 時間割3のデータに存在する要素番号を取得
				f_MutationNumber = getInTimeTable3Number(f_RandomDayOfWeek1,
						f_RandomPeriod1, f_RandomGrade1,
						f_RandomPreviousOrLatter);

			} while (!existInTimeTable3(
					TimeTable.changeValueToDay(f_RandomDayOfWeek1),
					f_RandomPeriod1,
					f_RandomGrade1,
					ClassOfGrade
							.changeValueToPreviousOrLatter(f_RandomPreviousOrLatter)));
			if (DEBUG) {
				System.out.println("突然変異発生");
				System.out.println(f_MutationNumber + "番");
			}

			f_NewTimeTableData3.get(f_MutationNumber).setDayOfWeek(
					f_TimeTableDataTmp1.getDayOfWeek());
			f_NewTimeTableData3.get(f_MutationNumber).setPeriod(
					f_TimeTableDataTmp1.getPeriod());

			return true;
		}

		// 3次の科目数を超えた数のとき
		else {

			if (DEBUG) {
				System.out.println("突然変異なし");
			}

			return false;
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
	private boolean checkDuplication(int number) {

		// 1次の時間割と重複するとき
		if (checkDuplication1(number)) {

			if (DEBUG) {
				System.out.println("1次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("1次と重複なし\n");
		}

		// 2次の時間割と重複するとき
		if (checkDuplication2(number)) {

			if (DEBUG) {
				System.out.println("2次と重複あり\n");
			}
			return true;
		}

		if (DEBUG) {
			System.out.println("2次と重複なし\n");
		}

		// 3次の時間割と重複するとき
		if (checkDuplication3(number)) {

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
	private boolean checkDuplication1(int number) {

		for (int number1 = 0; number1 < f_TimeTableData1.size(); number1++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData1.get(number1).getDayOfWeek(),
					f_NewTimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData1.get(number1)
						.getClassOfGrade().getPreviousOrLatter(),
						f_NewTimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData1.get(number1).getClassOfGrade()
							.getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_NewTimeTableData3.get(number)
												.getClassOfGrade()
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
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getTeachers().getName(),
										f_NewTimeTableData3.get(number)
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
	 * 2次の時間割と重複がないかのチェック
	 *
	 * @param number 番目(3次の時間割)
	 *
	 * @return true 重複あり
	 *
	 * @return false 重複なし
	 */
	private boolean checkDuplication2(int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					f_NewTimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getPreviousOrLatter(),
						f_NewTimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_TimeTableData2.get(number2).getClassOfGrade()
							.getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_TimeTableData2
								.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData2.get(number2).getPeriod()
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

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
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(f_TimeTableData2
										.get(number2).getClassOfGrade()
										.getTeachers().getName(),
										f_NewTimeTableData3.get(number)
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
	private boolean checkDuplication3(int number) {

		for (int number3 = 0; number3 < f_NewTimeTableData3.size(); number3++) {

			// まったく同じデータがあるとき
			if (number3 == number) {

				// if (DEBUG) {
				//
				// System.out.println(number + "同士なので、読み飛ばす");
				// }
				continue;
			}

			// 同じ曜日のとき
			if (Objects.equals(f_NewTimeTableData3.get(number3).getDayOfWeek(),
					f_NewTimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_NewTimeTableData3.get(number3)
						.getClassOfGrade().getPreviousOrLatter(),
						f_NewTimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					// 同じ学年のとき
					if (f_NewTimeTableData3.get(number3).getClassOfGrade()
							.getGrade() == f_NewTimeTableData3.get(number)
							.getClassOfGrade().getGrade()) {

						for (int addPeriod = 0; addPeriod < f_NewTimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_NewTimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(
										f_NewTimeTableData3.get(number3)
												.getClassOfGrade()
												.getTeachers().getName(),
										f_NewTimeTableData3.get(number)
												.getClassOfGrade()
												.getTeachers().getName())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// コース・クラスと重複するとき
								if (checkDuplicationCouseOrClass(
										f_NewTimeTableData3.get(number3)
												.getClassOfGrade()
												.getCourseOrClass(),
										f_NewTimeTableData3.get(number)
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

						for (int addPeriod = 0; addPeriod < f_NewTimeTableData3
								.get(number3).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_NewTimeTableData3.get(number3).getPeriod()
									+ addPeriod == f_NewTimeTableData3.get(
									number).getPeriod()) {

								// 同じ先生のとき
								if (Objects.equals(
										f_NewTimeTableData3.get(number3)
												.getClassOfGrade()
												.getTeachers().getName(),
										f_NewTimeTableData3.get(number)
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
		TimeTable timeTableData = new TimeTable();

		for (int number = 0; number < f_ClassOfGradeData3.size(); number++) {
			timeTableData = new TimeTable();
			timeTableData.setClassOfGrade(f_ClassOfGradeData3.get(number));
			f_TimeTableData3.add(timeTableData);

			// 重複がある限り繰り返す
			do {
				makeFirstTimeTable3(number);// 最初の3次の時間割を決める

			} while (checkFirstDuplication(number));

		}

		// 新規の3次の時間割のデータとしてセット
		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			f_NewTimeTableData3.add(f_TimeTableData3.get(number));
		}

		f_OldEvaluationValue = 5.0;// 親集団の評価値として取得

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
					.getPreviousOrLatter()
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
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

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
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

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
						.getClassOfGrade().getPreviousOrLatter(),
						f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

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
		if (Objects.equals(course_or_class1, course_or_class2)) {

			if (DEBUG) {
				System.out.println("同じクラス");
			}

			return true;
		}

		// abクラスと被るとき
		if (Objects.equals("ab", course_or_class1)) {

			// bcクラスのとき
			if (Objects.equals("bc", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("abと重複");
			}
			return true;
		}

		// bcクラスと被るとき
		if (Objects.equals("bc", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// abクラスのとき
			if (Objects.equals("ab", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bcと重複");
			}
			return true;
		}

		// 奇数クラスと被るとき
		if (Objects.equals("奇数", course_or_class1)) {

			// 偶数クラスのとき
			if (Objects.equals("偶数", course_or_class2)) {

				return false;
			}

			if (DEBUG) {
				System.out.println("奇数と重複");
			}
			return true;
		}

		// 偶数クラスと被るとき
		if (Objects.equals("偶数", course_or_class1)) {

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
		if (Objects.equals("a", course_or_class1)) {

			// bクラスのとき
			if (Objects.equals("b", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			// bcクラスのとき
			if (Objects.equals("bc", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("aと重複");
			}
			return true;
		}

		// bクラスと被るとき
		if (Objects.equals("b", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// cクラスのとき
			if (Objects.equals("c", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("bと重複");
			}

			return true;

		}

		// cクラスと被るとき
		if (Objects.equals("c", course_or_class1)) {

			// aクラスのとき
			if (Objects.equals("a", course_or_class2)) {
				return false;
			}

			// bクラスのとき
			if (Objects.equals("b", course_or_class2)) {
				return false;
			}

			// abクラスのとき
			if (Objects.equals("ab", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("cと重複");
			}
			return true;
		}

		// エレ情(奇数)クラスと被るとき
		if (Objects.equals("エレ情(奇数)", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (Objects.equals("エレ情(偶数)", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(奇数)と重複");
			}
			return true;
		}

		// エレ情(偶数)クラスと被るとき
		if (Objects.equals("エレ情(偶数)", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (Objects.equals("エレ情(奇数)", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情(偶数)と重複");
			}
			return true;
		}

		// エレ情クラスと被るとき
		if (Objects.equals("エレ情", course_or_class1)) {

			// エネ環クラスのとき
			if (Objects.equals("エネ環", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エレ情と重複");
			}
			return true;
		}

		// エネ環クラスと被るとき
		if (Objects.equals("エネ環", course_or_class1)) {

			// エレ情クラスのとき
			if (Objects.equals("エレ情", course_or_class2)) {
				return false;
			}

			// エレ情(奇数)クラスのとき
			if (Objects.equals("エレ情(奇数)", course_or_class2)) {
				return false;
			}

			// エレ情(偶数)クラスのとき
			if (Objects.equals("エレ情(偶数)", course_or_class2)) {
				return false;
			}

			if (DEBUG) {
				System.out.println("エネ環と重複");
			}
			return true;
		}

		// 共通クラスと被るとき
		if (Objects.equals("共通", course_or_class1)) {

			if (DEBUG) {
				System.out.println("共通と重複");
			}
			return true;

		}

		// 再履修クラスと被るとき
		if (Objects.equals("再履修", course_or_class1)) {

			// 再履修クラスでないとき
			if (Objects.equals("再履修", course_or_class2) == false) {

				if (DEBUG) {
					System.out.println("再履修と重複");
				}
				return true;

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
		writeRoomAndDayAndPeriodFile();// 教室と何曜日と何限目が決まったファイルを書き込む
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
					.getTeachers().getName()
					+ ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass());// コース・クラス
		}

		output.close();
		System.out.println(FILE3_NAME + "へ書き込みました。");
	}

	/*
	 * 最初の3次のファイル(教室と何曜日と何限目が決まったデータ)を書き込む
	 */
	private void writeRoomAndDayAndPeriodFirstFile3() {
		PrintWriter output;
		output = FileIO.writeFile(FIRST_FILE3_NAME, false);

		System.out.println("担当者が決まった3次のファイル" + FIRST_FILE3_NAME + "に書き込みます。");

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
					.getTeachers().getName()
					+ ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass());// コース・クラス
		}

		output.close();
		System.out.println(FIRST_FILE3_NAME + "へ書き込みました。");
	}

	/*
	 * 評価値のファイルを書き込む
	 */
	private void writeEvaluationFile(){

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
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// 前期後期
							timeTableData.getClassOfGrade().setSubject(
									strData[5]);// 科目名
							timeTableData.getClassOfGrade().getTeachers()
									.setName(strData[6]);// 担当教員
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
						timeTableData.getClassOfGrade().setPreviousOrLatter(
								strData[4]);// 前期後期
						timeTableData.getClassOfGrade().setSubject(strData[5]);// 科目名
						timeTableData.getClassOfGrade().getTeachers()
								.setName(strData[6]);// 担当教員
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
							classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
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
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
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
						classOfGradeData.setPreviousOrLatter(strData[2]);// 前期後期
						classOfGradeData.setSubject(strData[3]);// 科目名
						classOfGradeData.getTeachers().setName(strData[4]);// 担当教員
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
