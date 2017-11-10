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
	private int f_MutationNumber;// 突然変異の番号
	private ArrayList<TimeTable> f_NewTimeTableData3 = new ArrayList<TimeTable>();// 新規の3次の時間割のデータ
	private ArrayList<Double> f_EvaluationValues = new ArrayList<Double>();// 評価値の動的配列
	private ArrayList<Evaluation> f_EvaluationData = new ArrayList<Evaluation>();// 評価値のデータ
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
						.get(number).getClassOfGrade().getPreviousOrLatter()
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

		for (int candidate = 0; candidate < f_CandidateTimeTableData3.length; candidate++) {

			System.out.println("\n候補" + (candidate + 1) + "の3次のデータ");

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
						.get(number).getClassOfGrade().getPreviousOrLatter()
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

		double value = f_FacultyEvaluationValue;
		f_NewEvaluationValue=0.0;

		for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

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
						value += 5.0;
					}
				}
			}

			f_EvaluationValues.set(candidate, value);

			if (DEBUG) {
				System.out.println("評価値:" + f_EvaluationValues.get(candidate));
			}

		}

		f_NewEvaluationValue=getSumEvaluationValue();// 評価値の合計を取得

		// 新しい評価値が親の評価値より大きいとき
		if(f_OldEvaluationValue<f_NewEvaluationValue){

			f_OldEvaluationValue=f_NewEvaluationValue;// 評価値を更新

			return true;
		}

		// 新しい評価値が親の評価値以下のとき
		else{
			return false;
		}
	}

	/*
	 * 曜日限目の最初の評価値の計算をする
	 */
	private void calcDayPeriodFirstEvaluationValue() {

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

						value -= 10.0;
					}

					// 2限目～4限目のとき
					else {
						value += 5.0;
					}
				}
			}

			f_EvaluationValues.add(value);
		}
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

//			calcDayPeriodEvaluationValue();// 評価値の計算をする

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

			System.out.println("遺伝的アルゴリズム" + (num + 1) + "回目");

			evaluation.setGeneration(num + 1);// num世代目

			rouletteChoice(CANDIDATE_NUM);// どの時間割かをルーレット選択する

			doCirculationCross();// 循環交叉

			mutation();// 突然変異

			calcEvaluationValue();// 評価値の計算をする

			// 新しい評価値が上のとき
			if (isBiggerDayPeriodEvaluationValue()) {

//				f_OldEvaluationValue = f_NewEvaluationValue;// 評価値を更新

				// 候補の時間割を更新
				for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

					for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
							.size(); number++) {

						// 曜日を更新
						f_CandidateTimeTableData3[candidate].get(number)
								.setDayOfWeek(
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getDayOfWeek());

						// 限目を更新
						f_CandidateTimeTableData3[candidate].get(number)
								.setPeriod(
										f_CandidateNewTimeTableData3[candidate]
												.get(number).getPeriod());
					}
				}

				if (DEBUG) {
					System.out.println("評価値が更新された。");
					System.out.println("評価値:" + f_OldEvaluationValue);
					indicateTimeTableData3();
					InOutPut.anyKey();
				}

				evaluation.setEvaluationValue(f_OldEvaluationValue);

				f_EvaluationData.add(evaluation);
			}

			// 新しい評価値以下のとき
			else {

				// 元の候補に戻す
				for (int candidate = 0; candidate < f_CandidateNewTimeTableData3.length; candidate++) {

					for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
							.size(); number++) {

						// 曜日を戻す
						f_CandidateNewTimeTableData3[candidate].get(number)
								.setDayOfWeek(
										f_CandidateTimeTableData3[candidate]
												.get(number).getDayOfWeek());

						// 限目を戻す
						f_CandidateNewTimeTableData3[candidate].get(number)
								.setPeriod(
										f_CandidateTimeTableData3[candidate]
												.get(number).getPeriod());
					}
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

		double sum = getSumEvaluationValue();// 合計の評価値を取得

		for (int candidate = 0; candidate < num; candidate++) {

			double randomNum = Calculation.getDRnd(0.0, sum);// 0.0から候補の数だけの評価値の合計までランダムで選ぶ

			// 候補1のとき
			if (candidate == 0) {

				if (0.0 <= randomNum
						&& randomNum <= f_EvaluationValues.get(candidate)) {
					f_CandidateRandomNumber1 = 0;
				}
			}

			// 最後の候補のとき
			else if (candidate == num - 1) {

				if (f_EvaluationValues.get(candidate) < randomNum
						&& randomNum <= sum) {
					f_CandidateRandomNumber1 = num - 1;
				}
			}

			else {

				if (f_EvaluationValues.get(candidate - 1) < randomNum
						&& randomNum <= f_EvaluationValues.get(candidate)) {
					f_CandidateRandomNumber1 = candidate;
				}
			}

			for (int number = 0; number < f_CandidateNewTimeTableData3[candidate]
					.size(); number++) {

				f_CandidateNewTimeTableData3[candidate].set(number,
						f_CandidateTimeTableData3[f_CandidateRandomNumber1]
								.get(number));// 新しい時間割の候補として入れる
			}
		}
	}

	/*
	 * 循環交叉する
	 */
	private void doCirculationCross() {

		int elementNumber3, elementNumber4;// 要素番号

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

		// 選ばれた2つの時間割のデータそれぞれの曜日と限目を取得
		f_RandomDayOfWeek1 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
				.get(f_RandomCrossNumber1).getDayOfWeek();// 候補1の曜日を取得

		f_RandomPeriod1 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
				.get(f_RandomCrossNumber1).getPeriod();// 候補1の限目を取得

		f_RandomDayOfWeek2 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
				.get(f_RandomCrossNumber1).getDayOfWeek();// 候補2の曜日を取得

		f_RandomPeriod2 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
				.get(f_RandomCrossNumber1).getPeriod();// 候補2の限目を取得

		// 曜日を交換
		f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
				f_RandomCrossNumber1).setDayOfWeek(f_RandomDayOfWeek1);
		f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
				f_RandomCrossNumber1).setDayOfWeek(f_RandomDayOfWeek2);

		// 限目を交換
		f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
				f_RandomCrossNumber1).setPeriod(f_RandomPeriod1);
		f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
				f_RandomCrossNumber1).setPeriod(f_RandomPeriod2);

		// 候補2の中に候補1の曜日と限目が同じものがないかを探す
		elementNumber3 = getInTimeTable3Number(
				f_CandidateRandomNumber2,
				f_RandomDayOfWeek1,
				f_RandomPeriod1,
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getClassOfGrade()
						.getPreviousOrLatter());

		// 候補1の中に候補2の曜日と限目が同じものがないかを探す
		elementNumber4 = getInTimeTable3Number(
				f_CandidateRandomNumber1,
				f_RandomDayOfWeek2,
				f_RandomPeriod2,
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getClassOfGrade().getGrade(),
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getClassOfGrade()
						.getPreviousOrLatter());

		// 両方見つかった場合(計4つの要素番号)
		if (0 <= elementNumber3 && 0 <= elementNumber4) {

			// 候補2の中の曜日を取得
			String dayOfWeek3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
					.get(elementNumber3).getDayOfWeek();

			// 候補2の中の限目を取得
			int period3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
					.get(elementNumber3).getPeriod();

			// 候補1の中の曜日を取得
			String dayOfWeek4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
					.get(elementNumber4).getDayOfWeek();

			// 候補1の中の限目を取得
			int period4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
					.get(elementNumber4).getPeriod();

			// 曜日を交換
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
					elementNumber3).setDayOfWeek(dayOfWeek4);
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
					elementNumber4).setDayOfWeek(dayOfWeek3);

			// 限目を交換
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
					elementNumber3).setPeriod(period4);
			f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
					elementNumber4).setPeriod(period3);

		}

		// 片方見つかる場合
		else {

			// 候補2だけ見つかったとき
			if (0 <= elementNumber3) {

				// 候補2の中の曜日を取得
				String dayOfWeek3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(elementNumber3).getDayOfWeek();

				// 候補2の中の限目を取得
				int period3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(elementNumber3).getPeriod();

				// 見つからなかった候補1の交叉する要素番号と同じ曜日を取得
				String dayOfWeek4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getDayOfWeek();

				// 見つからなかった候補1の交叉する要素番号と同じ限目を取得
				int period4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(f_RandomCrossNumber1).getPeriod();

				// 見つかった候補2と同じ要素番号の候補1にコピー
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber3).setDayOfWeek(dayOfWeek4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber3).setPeriod(period4);

				// 曜日を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber3).setDayOfWeek(dayOfWeek4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber3).setDayOfWeek(dayOfWeek3);

				// 限目を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber3).setPeriod(period4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber3).setPeriod(period3);
			}

			// 候補1だけ見つかったとき
			else if (0 <= elementNumber4) {

				// 候補1の中の曜日を取得
				String dayOfWeek3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(elementNumber4).getDayOfWeek();

				// 候補1の中の限目を取得
				int period3 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber1]
						.get(elementNumber4).getPeriod();

				// 見つからなかった候補2の交叉する要素番号と同じ曜日を取得
				String dayOfWeek4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getDayOfWeek();

				// 見つからなかった候補2の交叉する要素番号と同じ限目を取得
				int period4 = f_CandidateNewTimeTableData3[f_CandidateRandomNumber2]
						.get(f_RandomCrossNumber1).getPeriod();

				// 見つかった候補1と同じ要素番号の候補2にコピー
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber4).setDayOfWeek(dayOfWeek4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber4).setPeriod(period4);

				// 曜日を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber4).setDayOfWeek(dayOfWeek4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber4).setDayOfWeek(dayOfWeek3);

				// 限目を交換
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber1].get(
						elementNumber4).setPeriod(period4);
				f_CandidateNewTimeTableData3[f_CandidateRandomNumber2].get(
						elementNumber4).setPeriod(period3);
			}
		}

	}

	/*
	 * ランダムの限目を取得
	 */
	private int getRandomPeriod() {

		int randomPeriod = Calculation.getRnd(1, MAX_PERIOD * 4);

		if (1 <= randomPeriod && randomPeriod <= 8) {

			randomPeriod = 1;
		}

		else if (9 <= randomPeriod && randomPeriod <= 16) {
			randomPeriod = 5;
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
	 * @param previous_latter 前期後期
	 *
	 * @return 既存の時間割のデータの要素番号
	 */
	private int getInTimeTable3Number(int candidate_number, String day_of_week,
			int period, int grade, String previous_latter) {

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
									.getPreviousOrLatter(), previous_latter)) {

				if (DEBUG) {
					System.out.println("すべての要素が同じものを発見");
				}
				return number;
			}
		}

		for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
				.size(); number++) {

			// ランダムに選択した曜日、限目、前期後期が時間割3のデータに存在するとき
			if (Objects.equals(f_CandidateNewTimeTableData3[candidate_number]
					.get(number).getDayOfWeek(), day_of_week)
					&& f_CandidateNewTimeTableData3[candidate_number].get(
							number).getPeriod() == period
					&& Objects.equals(
							f_CandidateNewTimeTableData3[candidate_number]
									.get(number).getClassOfGrade()
									.getPreviousOrLatter(), previous_latter)) {

				if (DEBUG) {
					System.out.println("学年だけ違うものを発見");
				}
				return number;
			}
		}

		for (int number = 0; number < f_CandidateNewTimeTableData3[candidate_number]
				.size(); number++) {

			// ランダムに選択した曜日、限目が時間割3のデータに存在するとき
			if (Objects.equals(f_CandidateNewTimeTableData3[candidate_number]
					.get(number).getDayOfWeek(), day_of_week)
					&& f_CandidateNewTimeTableData3[candidate_number].get(
							number).getPeriod() == period) {
				if (DEBUG) {
					System.out.println("学年と前期後期が違うものを発見");
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

	private void mutation() {

		int num = Calculation
				.getRnd(1, ORDER1_COLS + ORDER2_COLS + ORDER3_COLS);// 3次の科目数/全科目数の確率

		// 1から3次の科目数までのとき
		if (num <= ORDER3_COLS) {

			// 突然変異させる候補の番号をランダムで選ぶ
			int mutationCandidateNumber = Calculation.getRnd(0,
					f_CandidateNewTimeTableData3.length - 1);

			// 突然変異させる要素番号をランダムで選ぶ
			int mutationNumber = Calculation.getRnd(0,
					f_CandidateNewTimeTableData3[mutationCandidateNumber]
							.size() - 1);

			int randomDayOfWeek = Calculation.getRnd(
					TimeTable.changeDayToValue("月"), MAX_DAY);
			int randomPeriod = Calculation.getRnd(1, MAX_PERIOD);

			// 曜日を交換
			f_CandidateNewTimeTableData3[mutationCandidateNumber].get(
					mutationNumber).setDayOfWeek(
					TimeTable.changeValueToDay(randomDayOfWeek));

			// 限目を交換
			f_CandidateNewTimeTableData3[mutationCandidateNumber].get(
					mutationNumber).setPeriod(randomPeriod);
		}

		// 3次の科目数を超えた数のとき
		else {

			if (DEBUG) {
				System.out.println("突然変異なし");
			}

			// return false;
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
						.getClassOfGrade().getPreviousOrLatter(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getPreviousOrLatter())) {

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
	private boolean checkDuplication2(int candidate, int number) {

		for (int number2 = 0; number2 < f_TimeTableData2.size(); number2++) {

			// 同じ曜日のとき
			if (Objects.equals(f_TimeTableData2.get(number2).getDayOfWeek(),
					f_CandidateNewTimeTableData3[candidate].get(number)
							.getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (Objects.equals(f_TimeTableData2.get(number2)
						.getClassOfGrade().getPreviousOrLatter(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getPreviousOrLatter())) {

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
								.getClassOfGrade().getPreviousOrLatter(),
						f_CandidateNewTimeTableData3[candidate].get(number)
								.getClassOfGrade().getPreviousOrLatter())) {

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

			// if (DEBUG) {
			// indicateData();
			// InOutPut.anyKey();
			// }

		}

		calcDayPeriodFirstEvaluationValue();// 評価値の計算をする

		f_OldEvaluationValue = getSumEvaluationValue();// 最初の0代目の評価値の合計を取得

		// // 新規の3次の時間割のデータとしてセット
		// for (int number = 0; number < f_TimeTableData3.size(); number++) {
		// f_NewTimeTableData3.add(f_TimeTableData3.get(number));
		// }

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
						.getClassOfGrade().getPreviousOrLatter()
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
					.getClassOfGrade().getPreviousOrLatter()
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
						.getClassOfGrade().getPreviousOrLatter()
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
