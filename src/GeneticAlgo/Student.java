package GeneticAlgo;

/*
 *  生徒のクラス
 *  @author Nakata
 */
public class Student implements iDayPeriod {

	private int f_Grade;// 学年
	private String f_Semester;// 学期
	private String f_CourseOrClass;// コース・クラス
	private int[][][] f_DayPeriodNumber = new int[CANDIDATE_NUM][MAX_DAY + 1][MAX_PERIOD];// ある曜日の限目のコマ数
	private int[] f_DayPeriodEvaluationValue = new int[CANDIDATE_NUM];// 日程の評価値

	/*
	 * コンストラクタ
	 */
	public Student() {

		f_Grade = 0;
		f_Semester = "不明";
		f_CourseOrClass = "不明";

		for (int candidate = 0; candidate < f_DayPeriodNumber.length; candidate++) {

			for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

				for (int period = 0; period < f_DayPeriodNumber[candidate][day].length; period++) {
					f_DayPeriodNumber[candidate][day][period] = 0;
				}
			}
			f_DayPeriodEvaluationValue[candidate] = 1;
		}
	}

	/*
	 * 曜日限目のコマ数をリセットする
	 */
	public void resetDayPeriodNumber() {

		for (int candidate = 0; candidate < f_DayPeriodNumber.length; candidate++) {

			for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

				for (int period = 0; period < f_DayPeriodNumber[candidate][day].length; period++) {

					f_DayPeriodNumber[candidate][day][period] = 0;
				}
			}
		}
	}

	// ----------------------------//
	// ---------ゲッター-----------//
	// ----------------------------//
	/*
	 * 学年のゲッター
	 * 
	 * @return 学年
	 */
	public int getGrade() {
		return f_Grade;
	}

	/*
	 * 学期のゲッター
	 * 
	 * @return 前期or後期
	 */
	public String getSemester() {
		return f_Semester;
	}

	/*
	 * コース・クラスのゲッター
	 * 
	 * @return コースorクラス
	 */
	public String getCourseOrClass() {
		return f_CourseOrClass;
	}

	/*
	 * ある曜日の限目のところのコマ数のゲッター
	 * 
	 * @param day 曜日
	 * 
	 * @param period 限目
	 * 
	 * @return 指定した曜日と限目のコマ数
	 */
	public int getDayPeriodNumber(int candidate, int day, int period) {

		if (MAX_DAY < day || MAX_PERIOD <= period) {
			System.out.println(day + "曜日" + period + "限目");
		}

		else if (day < 0 || period < 0) {
			System.out.println(day + "曜日" + period + "限目");
		}

		return f_DayPeriodNumber[candidate][day][period];
	}

	/*
	 * 日程の評価値のゲッター
	 * 
	 * @return 日程の評価値
	 */
	public int getDayPeriodEvaluationValue(int candidate) {
		return f_DayPeriodEvaluationValue[candidate];
	}

	/*
	 * ある候補のある曜日のコマ数の合計のゲッター
	 * 
	 * @param candidate 候補番号
	 * 
	 * @day 曜日
	 * 
	 * @return ある候補のある曜日のコマ数の合計
	 */
	public int getSumNumberOfDayPeriod(int candidate, int day) {

		int sum = 0;

		for (int period = 1; period <= f_DayPeriodNumber[candidate][day].length; period++) {
			sum += f_DayPeriodNumber[candidate][day][period - 1];
		}

		return sum;
	}

	/*
	 * ある候補の最大コマ数のゲッター
	 * 
	 * @param candidate 候補番号
	 * 
	 * @return ある候補のある曜日の最大コマ数
	 */
	public int getMaxNumberOfDayPeriod(int candidate) {

		int max = f_DayPeriodNumber[candidate][0][0];

		for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {
			for (int period = 1; period <= f_DayPeriodNumber[candidate][day].length; period++) {

				if (max < f_DayPeriodNumber[candidate][day][period - 1]) {
					max = f_DayPeriodNumber[candidate][day][period - 1];
				}
			}
		}

		return max;
	}

	/*
	 * ある候補の最小コマ数のゲッター
	 * 
	 * @param candidate 候補番号
	 * 
	 * @return ある候補のある曜日の最小コマ数
	 */
	public int getMinNumberOfDayPeriod(int candidate) {

		int min = f_DayPeriodNumber[candidate][0][0];

		for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

			for (int period = 1; period <= f_DayPeriodNumber[candidate][day].length; period++) {

				if (min > f_DayPeriodNumber[candidate][day][period - 1]) {
					min = f_DayPeriodNumber[candidate][day][period - 1];
				}
			}
		}

		return min;
	}

	// ----------------------------//
	// ---------セッター-----------//
	// ----------------------------//
	/*
	 * 学年のセッター
	 * 
	 * @param grade 学年
	 */
	public void setGrade(int grade) {
		f_Grade = grade;
	}

	/*
	 * 学期のセッター
	 * 
	 * @param semester 学期
	 */
	public void setSemester(String semster) {
		f_Semester = semster;
	}

	/*
	 * コース・クラスのセッター
	 * 
	 * @param course_class コースorクラス
	 */
	public void setCourseOrClass(String course_class) {
		f_CourseOrClass = course_class;
	}

	/*
	 * ある曜日の限目のところのコマ数のセッター
	 * 
	 * @param candidate 候補番号
	 * 
	 * @param day 曜日
	 * 
	 * @param period 限目
	 * 
	 * @param number コマ数
	 */
	public void setDayPeriodNumber(int candidate, int day, int period,
			int number) {

		for (int addPeriod = 0; addPeriod < number; addPeriod++) {

			if (f_DayPeriodNumber[candidate][day][period + addPeriod] <= 0) {
				f_DayPeriodNumber[candidate][day][period + addPeriod] = 1;
			}
		}
	}

	public void updateDayPeriodNumber(int candidate, int day, int period,
			int number) {
		f_DayPeriodNumber[candidate][day][period] = number;
	}

	/*
	 * 日程の評価値のセッター
	 * 
	 * @param value 値
	 */
	public void setDayPeriodEvaluationValue(int candidate, int value) {
		f_DayPeriodEvaluationValue[candidate] = value;
	}
}
