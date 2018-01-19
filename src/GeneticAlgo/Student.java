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
	private long[] f_DayPeriodEvaluationValue = new long[CANDIDATE_NUM];// 日程の評価値

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
	 * コピーコンストラクタ
	 *
	 * @param student コピー元の生徒
	 */
	public Student(Student student) {

		f_Grade = student.getGrade();
		f_Semester = student.getSemester();
		f_CourseOrClass = student.getCourseOrClass();

		for (int candidate = 0; candidate < f_DayPeriodNumber.length; candidate++) {

			for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

				for (int period = 0; period < f_DayPeriodNumber[candidate][day].length; period++) {

					f_DayPeriodNumber[candidate][day][period] = student
							.getDayPeriodNumber(candidate, day, period);
				}
			}
			f_DayPeriodEvaluationValue[candidate] = student
					.getDayPeriodEvaluationValue(candidate);
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

	/*
	 * 曜日限目のコマ数を更新する
	 *
	 * @param candidate 候補番号
	 *
	 * @param day 曜日
	 *
	 * @param period 限目
	 *
	 * @param number[] コマ数の配列
	 */
	public void updateDayPeriodNumber(int candidate1, int day1, int period1,
			int[][][] number1, int candidate2, int day2, int period2,
			int[][][] number2) {

		number1=number2.clone();

		for(int candidate=0;candidate<number2.length;candidate++){
			number1[candidate]=number2[candidate].clone();
		}

		for(int candidate=0;candidate<number2.length;candidate++){

			for(int day=0;day<number2[candidate].length;day++){

				number1[candidate][day]=number2[candidate][day].clone();
			}
		}
//		number1[candidate1][day1][period1] = number2[candidate2][day2][period2];
	}

	/*
	 * 日程の評価値のセッター
	 *
	 * @param candidate1 候補番号1
	 *
	 * @param value1[] 値の配列
	 *
	 * @param candidate2 候補番号2
	 *
	 * @param value2[] 値の配列
	 */
	public void updateDayPeriodEvaluationValue(int candidate1, long[] value1,
			int candidate2, long[] value2) {

		value1[candidate1] = value2[candidate2];
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
	 * @param candidate 候補番号
	 *
	 * @param day 曜日
	 *
	 * @param period 限目
	 *
	 * @return 指定した曜日と限目のコマ数
	 */
	public int getDayPeriodNumber(int candidate, int day, int period) {

		return f_DayPeriodNumber[candidate][day][period];
	}

	/*
	 * 曜日限目のコマ数のゲッター
	 *
	 * @return 曜日限目のコマ数の配列
	 */
	public int[][][] getDayPeriodNumbers() {
		return f_DayPeriodNumber;
	}

	/*
	 * 日程の評価値のゲッター
	 *
	 * @param candidate 候補番号
	 *
	 * @return 日程の評価値
	 */
	public long getDayPeriodEvaluationValue(int candidate) {
		return f_DayPeriodEvaluationValue[candidate];
	}

	/*
	 * 日程の評価値のゲッター
	 *
	 * @return 日程の評価値の配列
	 */
	public long[] getDayPeriodEvaluationValue() {
		return f_DayPeriodEvaluationValue;
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
	 * @return ある候補の最大コマ数
	 */
	public int getMaxNumberOfDayPeriod(int candidate) {

		int max = 0;

		for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

			int sum = 0;

			for (int period = 1; period <= f_DayPeriodNumber[candidate][day].length; period++) {

				sum += f_DayPeriodNumber[candidate][day][period - 1];

			}

			if (max < sum) {
				max = sum;
			}
		}

		return max;
	}

	/*
	 * ある候補の最小コマ数のゲッター
	 *
	 * @param candidate 候補番号
	 *
	 * @return ある候補の最小コマ数
	 */
	public int getMinNumberOfDayPeriod(int candidate) {

		int min = 5;

		for (int day = 0; day < f_DayPeriodNumber[candidate].length; day++) {

			int sum = 0;

			for (int period = 1; period <= f_DayPeriodNumber[candidate][day].length; period++) {

				sum += f_DayPeriodNumber[candidate][day][period - 1];
			}

			if (min > sum) {
				min = sum;
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
	 */
	public void setDayPeriodNumber(int candidate, int day, int period,
			int[][][] day_period_numbers) {

		if (day_period_numbers[candidate][day][period] <= 0) {
			day_period_numbers[candidate][day][period] = 1;
		}

	}

	/*
	 * 日程の評価値のセッター
	 *
	 * @param candidate 候補番号
	 *
	 * @param evaluation[] 評価値の配列
	 *
	 * @param value 値
	 */
	public void setDayPeriodEvaluationValue(int candidate, long[] evaluation,
			long value) {
		evaluation[candidate] = value;
	}

}
