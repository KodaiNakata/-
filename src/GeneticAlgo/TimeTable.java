package GeneticAlgo;

// 時間割のクラス
public class TimeTable {

	private String f_DayOfWeek;// 曜日
	private int f_Period;// 限目
	private int f_Grade;// 学年
	private ClassOfGrade f_ClassOfGrade;// 学年ごとの授業

	/*
	 * コンストラクタ 0またはnull
	 */
	public TimeTable() {
		f_DayOfWeek = null;
		f_Period = 0;
		f_Grade = 0;
		f_ClassOfGrade = new ClassOfGrade();
	}

	/*
	 * 曜日を数値に変える
	 *
	 * @param day_of_week 曜日
	 *
	 * @return 曜日を数値に変えたもの
	 */
	public int changeValueDay(String day_of_week) {
		switch (day_of_week) {
		case "月":
			return 0;

		case "火":
			return 1;

		case "水":
			return 2;

		case "木":
			return 3;

		case "金":
			return 4;

		case "土":
			return 5;

		default:
			return -1;
		}
	}

	// -------------------------------//
	// ------------ゲッター-----------//
	// -------------------------------//
	/*
	 * 曜日のゲッター
	 *
	 * @return 曜日
	 */
	public String getDayOfWeeks() {
		return f_DayOfWeek;
	}

	/*
	 * 限目のゲッター
	 *
	 * @return 限目
	 */
	public int getPeriod() {
		return f_Period;
	}

	/*
	 * 学年のゲッター
	 */
	public int getGrade() {
		return f_Grade;
	}

	/*
	 * 学年ごとの授業のゲッター
	 *
	 * @return 学年ごとの授業
	 */
	public ClassOfGrade getClassOfGrade() {
		return f_ClassOfGrade;
	}

	// -------------------------------//
	// ------------セッター-----------//
	// -------------------------------//
	/*
	 * 曜日のセッター
	 *
	 * @param dayOfWeek 曜日
	 */
	public void setDayOfWeek(String dayOfWeek) {
		f_DayOfWeek = dayOfWeek;
	}

	/*
	 * 限目のセッター
	 *
	 * @param period 限目
	 */
	public void setPeriod(int period) {
		f_Period = period;
	}

	/*
	 * 学年のセッター
	 *
	 * @param grade 学年
	 */
	public void setGrade(int grade) {
		f_Grade = grade;
	}

	/*
	 * 学年ごとの授業のセッター
	 *
	 * @param class_of_grade 学年ごとの授業
	 */
	public void setClassOfGrade(ClassOfGrade class_of_grade) {
		f_ClassOfGrade = class_of_grade;
	}
}
