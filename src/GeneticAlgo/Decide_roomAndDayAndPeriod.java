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
	private double f_EvaluationValue1;// 評価値
	private int f_RandomDayOfWeek1;// ランダムの曜日
	private int f_RandomPeriod1;// ランダムの限目
	private int f_RandomGrade1;// ランダムの学年
	private int f_RandomDayOfWeek2;// ランダムの曜日
	private int f_RandomPeriod2;// ランダムの限目
	private int f_RandomGrade2;// ランダムの学年
	private TimeTable f_TimeTableDataTmp1;// 時間割のデータの交換1
	private TimeTable f_TimeTableDataTmp2;// 時間割のデータの交換2
	private int f_TmpNumber1;// 交換用の番目1
	private int f_TmpNumber2;// 交換用の番目2

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
	private void result() {

		System.out.println("3次の時間割の結果");

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
					.getTeacher()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}
	}

	// ------------------------------------------------------//
	// ---------------------遺伝的アルゴリズム---------------//
	// ------------------------------------------------------//
	/*
	 * 3次の時間割の作成
	 */
	private void makeTimeTable3() {
		setClassOfGrade3ToFirstTimeTable3();// 3次の学年ごとの授業を最初の時間割にセットする
//		exeGeneticAlgo();// 遺伝的アルゴリズムを実行する
	}

	/*
	 * 遺伝的アルゴリズムを実行する
	 */
	private void exeGeneticAlgo() {

		for (int num = 0; num < CHECK_NUM; num++) {

			System.out.println((num + 1) + "回目");
			choice();// 何曜日の何限目の何学年かを選択する
			cross();// 交叉する
			mutation();// 突然変異をする
		}
	}

	/*
	 * 何曜日の何限目の何学年かを選択
	 */
	private void choice() {

		// 時間割3のデータに存在しない限り繰り返す
		do {
			// 曜日をランダムに選択
			f_RandomDayOfWeek1 = Calculation.getRnd(
					TimeTable.changeDayToValue("月"),
					TimeTable.changeDayToValue("土"));

			// 限目をランダムに選択
			f_RandomPeriod1 = Calculation.getRnd(1, 5);

			// 学年をランダムに選択
			f_RandomGrade1 = Calculation.getRnd(1, 4);

		} while (!existInTimeTable3(f_RandomDayOfWeek1, f_RandomPeriod1,
				f_RandomGrade1, f_TimeTableDataTmp1, f_TmpNumber1));

		// 時間割3のデータに存在しない限り繰り返す
		do {
			// 曜日をランダムに選択
			f_RandomDayOfWeek2 = Calculation.getRnd(
					TimeTable.changeDayToValue("月"),
					TimeTable.changeDayToValue("土"));

			// 限目をランダムに選択
			f_RandomPeriod2 = Calculation.getRnd(1, 5);

			// 学年をランダムに選択
			f_RandomGrade2 = Calculation.getRnd(1, 4);

		} while (!existInTimeTable3(f_RandomDayOfWeek2, f_RandomPeriod2,
				f_RandomGrade2, f_TimeTableDataTmp2, f_TmpNumber2)
				|| ((f_RandomDayOfWeek1 == f_RandomDayOfWeek2)
						&& (f_RandomPeriod1 == f_RandomPeriod2) && (f_RandomGrade1 == f_RandomGrade2)));
	}

	/*
	 * 時間割3に存在するか
	 *
	 * @param day_of_week 曜日
	 *
	 * @param period 限目
	 *
	 * @param grade 学年
	 *
	 * @param time_table 時間割のデータ
	 *
	 * @param tmp_number 交換用の番号(時間割のデータの要素番号)
	 *
	 * @return true 存在する
	 *
	 * @return false 存在しない
	 */
	private boolean existInTimeTable3(int day_of_week, int period, int grade,
			TimeTable time_table, int tmp_number) {

		for (int number = 0; number < f_TimeTableData3.size(); number++) {

			// ランダムに選択した曜日、限目、学年が時間割3のデータに存在するとき
			if (f_TimeTableData3.get(number).getDayOfWeek()
					.equals(TimeTable.changeValueToDay(day_of_week))
					&& f_TimeTableData3.get(number).getPeriod() == period
					&& f_TimeTableData3.get(number).getClassOfGrade()
							.getGrade() == grade) {

				time_table = f_TimeTableData3.get(number);
				tmp_number = number;

				if (DEBUG) {
					System.out.println("時間割3のデータに存在します。");
				}
				return true;
			}
		}

		if (DEBUG) {
			System.out.println("時間割3のデータに存在しません。");
		}

		return false;
	}

	/*
	 * 交叉する
	 */
	private void cross() {

	}

	/*
	 * 突然変異する
	 */
	private void mutation() {

		int num = Calculation
				.getRnd(1, ORDER1_COLS + ORDER2_COLS + ORDER3_COLS);// 3次の科目数/全科目数の確率

		// 1から3次の科目数までのとき
		if (num <= ORDER3_COLS) {

			if (DEBUG) {
				System.out.println("突然変異発生");
			}

		}

		// 3次の科目数を超えた数のとき
		else {
			if (DEBUG) {
				System.out.println("突然変異なし");
			}
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

			} while (checkDuplication(number));

		}
	}

	/*
	 * 最初の3次の時間割を決める
	 *
	 * @param number 番目
	 */
	private void makeFirstTimeTable3(int number) {

		// ランダムに決定
		int randomDay = Calculation.getRnd(TimeTable.changeDayToValue("月"),
				TimeTable.changeDayToValue("土"));// 曜日をランダムに決定
		int randomPeriod = Calculation.getRnd(1, 5);// 限目をランダムに決定
		int randomClassRoom = Calculation.getRnd(
				TimeTable.changeRoomToValue("31-202"),
				TimeTable.changeRoomToValue("31-803"));// 教室をランダムに決定

		// 3次の時間割のデータとして設定する
		f_TimeTableData3.get(number).setDayOfWeek(
				TimeTable.changeValueToDay(randomDay));// 曜日を設定
		f_TimeTableData3.get(number).setPeriod(randomPeriod);// 限目を設定
		f_TimeTableData3.get(number).setClassRoom(
				TimeTable.changeValueToRoom(randomClassRoom));// 教室を設定
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
					.getTeacher()
					+ "先生、");// 担当教員
			System.out.print(f_TimeTableData3.get(number).getClassRoom()
					+ "教室、");// 教室
			System.out.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass()
					+ "クラス");// コース・クラス
		}

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
			// System.out.println("");
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

						for (int addPeriod = 0; addPeriod < f_TimeTableData1
								.get(number1).getClassOfGrade().getNumber(); addPeriod++) {

							// 同じ限目のとき
							if (f_TimeTableData1.get(number1).getPeriod()
									+ addPeriod == f_TimeTableData3.get(number)
									.getPeriod()) {

								// 同じ先生のとき
								if (f_TimeTableData1
										.get(number1)
										.getClassOfGrade()
										.getTeacher()
										.equals(f_TimeTableData3.get(number)
												.getClassOfGrade().getTeacher())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// 同じ教室のとき
								else if (f_TimeTableData1
										.get(number1)
										.getClassRoom()
										.equals(f_TimeTableData3.get(number)
												.getClassRoom())) {

									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// 3次の時間割のクラスがabクラスのとき
								else if ("ab".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のbcクラスのとき
									if ("bc".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("abと重複");
									}
									return true;
								}

								// 3次の時間割のクラスがbcクラスのとき
								else if ("bc".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がabクラスのとき
									else if ("ab".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("bcと重複");
									}
									return true;
								}

								// 3次の時間割のクラスが奇数クラスのとき
								else if ("奇数".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のクラスが偶数クラスのとき
									if ("偶数".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {

										return false;
									}

									if (DEBUG) {
										System.out.println("奇数と重複");
									}
									return true;
								}

								// 3次の時間割のクラスが偶数クラスのとき
								else if ("偶数".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のクラスが奇数クラスのとき
									if ("奇数".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {

										return false;
									}

									if (DEBUG) {
										System.out.println("偶数と重複");
									}
									return true;
								}

								// 3次の時間割がaクラスのとき
								else if ("a".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がbクラスのとき
									if ("b".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がbcクラスのとき
									else if ("bc".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("aと重複");
									}
									return true;
								}

								// 3次の時間割がbクラスのとき
								else if ("b".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("bと重複");
									}

									return true;

								}

								// 3次の時間割がcクラスのとき
								else if ("c".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がbクラスのとき
									else if ("b".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がabクラスのとき
									else if ("ab".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("cと重複");
									}
									return true;
								}

								// 3次の時間割がエレ情(奇数)クラスのとき
								else if ("エレ情(奇数)".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(偶数)クラスのとき
									else if ("エレ情(偶数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情(奇数)と重複");
									}
									return true;
								}

								// 3次の時間割がエレ情(偶数)クラスのとき
								else if ("エレ情(偶数)".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(奇数)クラスのとき
									else if ("エレ情(奇数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情(偶数)と重複");
									}
									return true;
								}

								// 3次の時間割がエレ情クラスのとき
								else if ("エレ情".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情と重複");
									}
									return true;
								}

								// 3次の時間割がエネ環クラスのとき
								else if ("エネ環".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエレ情クラスのとき
									if ("エレ情".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(奇数)クラスのとき
									else if ("エレ情(奇数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(偶数)クラスのとき
									else if ("エレ情(偶数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}
									if (DEBUG) {
										System.out.println("エネ環と重複");
									}
									return true;
								}

								// 3次の時間割が共通クラスのとき
								else if ("共通".equals(f_TimeTableData1
										.get(number1).getClassOfGrade()
										.getCourseOrClass()) == true) {

									if (DEBUG) {
										System.out.println("共通と重複");
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
								if (f_TimeTableData1
										.get(number1)
										.getClassOfGrade()
										.getTeacher()
										.equals(f_TimeTableData3.get(number)
												.getClassOfGrade().getTeacher())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								else if (f_TimeTableData1
										.get(number1)
										.getClassRoom()
										.equals(f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
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
			if (f_TimeTableData2.get(number2).getDayOfWeek()
					.equals(f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData2
						.get(number2)
						.getClassOfGrade()
						.getPreviousOrLatter()
						.equals(f_TimeTableData3.get(number).getClassOfGrade()
								.getPreviousOrLatter())) {

					for (int addPeriod = 0; addPeriod < f_TimeTableData2
							.get(number2).getClassOfGrade().getNumber(); addPeriod++) {

						// 同じ限目のとき
						if (f_TimeTableData2.get(number2).getPeriod()
								+ addPeriod == f_TimeTableData3.get(number)
								.getPeriod()) {

							// 同じ学年のとき
							if (f_TimeTableData2.get(number2).getClassOfGrade()
									.getGrade() == f_TimeTableData3.get(number)
									.getClassOfGrade().getGrade()) {

								if (DEBUG) {
									System.out.println("学年が重複");
								}

								return true;
							}

							// 同じ先生のとき
							else if (f_TimeTableData2
									.get(number2)
									.getClassOfGrade()
									.getTeacher()
									.equals(f_TimeTableData3.get(number)
											.getClassOfGrade().getTeacher())) {

								if (DEBUG) {
									System.out.println("先生が重複");
								}

								return true;
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

		for (int number3 = 0; number3 < number; number3++) {

			// 同じ曜日のとき
			if (f_TimeTableData3.get(number3).getDayOfWeek()
					.equals(f_TimeTableData3.get(number).getDayOfWeek())) {

				// 同じ前期・後期のとき
				if (f_TimeTableData3
						.get(number3)
						.getClassOfGrade()
						.getPreviousOrLatter()
						.equals(f_TimeTableData3.get(number).getClassOfGrade()
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
								if (f_TimeTableData3
										.get(number3)
										.getClassOfGrade()
										.getTeacher()
										.equals(f_TimeTableData3.get(number)
												.getClassOfGrade().getTeacher())) {

									if (DEBUG) {
										System.out.println("先生と重複");
									}
									return true;
								}

								// 同じ教室のとき
								else if (f_TimeTableData3
										.get(number3)
										.getClassRoom()
										.equals(f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複");
									}
									return true;
								}

								// 同じコース・クラスのとき
								else if (f_TimeTableData3
										.get(number3)
										.getClassOfGrade()
										.getCourseOrClass()
										.equals(f_TimeTableData3.get(number)
												.getClassOfGrade()
												.getCourseOrClass()) == true) {

									if (DEBUG) {
										System.out.println("コース・クラスと重複");
									}
									return true;
								}

								// 3次の時間割のクラスがabクラスのとき
								else if ("ab".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のbcクラスのとき
									if ("bc".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("abと重複");
									}
									return true;
								}

								// 3次の時間割のクラスがbcクラスのとき
								else if ("bc".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がabクラスのとき
									else if ("ab".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("bcと重複");
									}
									return true;
								}

								// 3次の時間割のクラスが奇数クラスのとき
								else if ("奇数".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のクラスが偶数クラスのとき
									if ("偶数".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {

										return false;
									}

									if (DEBUG) {
										System.out.println("奇数と重複");
									}
									return true;
								}

								// 3次の時間割のクラスが偶数クラスのとき
								else if ("偶数".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割のクラスが奇数クラスのとき
									if ("奇数".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {

										return false;
									}

									if (DEBUG) {
										System.out.println("偶数と重複");
									}
									return true;
								}

								// 3次の時間割がaクラスのとき
								else if ("a".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がbクラスのとき
									if ("b".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がbcクラスのとき
									else if ("bc".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("aと重複");
									}
									return true;
								}

								// 3次の時間割がbクラスのとき
								else if ("b".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がcクラスのとき
									else if ("c".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("bと重複");
									}

									return true;

								}

								// 3次の時間割がcクラスのとき
								else if ("c".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がaクラスのとき
									if ("a".equals(f_TimeTableData3.get(number)
											.getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がbクラスのとき
									else if ("b".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がabクラスのとき
									else if ("ab".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("cと重複");
									}
									return true;
								}

								// 3次の時間割がエレ情(奇数)クラスのとき
								else if ("エレ情(奇数)".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(偶数)クラスのとき
									else if ("エレ情(偶数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情(奇数)と重複");
									}
									return true;
								}

								// 3次の時間割がエレ情(偶数)クラスのとき
								else if ("エレ情(偶数)".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(奇数)クラスのとき
									else if ("エレ情(奇数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情(偶数)と重複");
									}
									return true;
								}

								// 3次の時間割がエレ情クラスのとき
								else if ("エレ情".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエネ環クラスのとき
									if ("エネ環".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									if (DEBUG) {
										System.out.println("エレ情と重複");
									}
									return true;
								}

								// 3次の時間割がエネ環クラスのとき
								else if ("エネ環".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									// 3次の時間割がエレ情クラスのとき
									if ("エレ情".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(奇数)クラスのとき
									else if ("エレ情(奇数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}

									// 3次の時間割がエレ情(偶数)クラスのとき
									else if ("エレ情(偶数)".equals(f_TimeTableData3
											.get(number).getClassOfGrade()
											.getCourseOrClass()) == true) {
										return false;
									}
									if (DEBUG) {
										System.out.println("エネ環と重複");
									}
									return true;
								}

								// 3次の時間割が共通クラスのとき
								else if ("共通".equals(f_TimeTableData3
										.get(number3).getClassOfGrade()
										.getCourseOrClass()) == true) {

									if (DEBUG) {
										System.out.println("共通と重複");
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
								if (f_TimeTableData3
										.get(number3)
										.getClassOfGrade()
										.getTeacher()
										.equals(f_TimeTableData3.get(number)
												.getClassOfGrade().getTeacher())) {

									if (DEBUG) {
										System.out.println("先生と重複(違う学年)");
									}
									return true;
								}

								// 同じ教室のとき
								else if (f_TimeTableData3
										.get(number3)
										.getClassRoom()
										.equals(f_TimeTableData3.get(number)
												.getClassRoom())) {
									if (DEBUG) {
										System.out.println("教室と重複(違う学年)");
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
					.getTeacher()
					+ ",");// 担当教員
			output.print(f_TimeTableData3.get(number).getClassRoom() + ",");// 教室
			output.println(f_TimeTableData3.get(number).getClassOfGrade()
					.getCourseOrClass());// コース・クラス
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
