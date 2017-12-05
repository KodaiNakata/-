package GeneticAlgo;

/*
 *  生徒のクラス
 *  @author Nakata
 */
public class Student implements iDayPeriod {

	private int f_Grade;// 学年
	private String f_Semester;// 学期
	private String f_CourseOrClass;// コース・クラス
	private int[][] f_DayPeriodNumber;// ある曜日の限目のコマ数

	/*
	 * コンストラクタ
	 */
	public Student() {

		f_Grade = 0;
		f_Semester = "不明";
		f_CourseOrClass = "不明";

		for (int day = 0; day <= MAX_DAY; day++) {

			for (int period = 1; period <= MAX_PERIOD; period++) {
				f_DayPeriodNumber[day][period] = 0;
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
	public int getDayPeriodNumber(int day, int period) {
		return f_DayPeriodNumber[day][period];
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
		f_Grade=grade;
	}

	/*
	 * 学期のセッター
	 *
	 * @param semester 学期
	 */
	public void setSemester(String semster) {
		f_Semester=semster;
	}

	/*
	 * コース・クラスのセッター
	 *
	 * @param course_class コースorクラス
	 */
	public void setCourseOrClass(String course_class) {
		f_CourseOrClass=course_class;
	}

	/*
	 * ある曜日の限目のところのコマ数のセッター
	 *
	 * @param day 曜日
	 *
	 * @param period 限目
	 *
	 * @param number コマ数
	 */
	public void setDayPeriodNumber(int day, int period,int number) {
		f_DayPeriodNumber[day][period]=number;
	}
}
