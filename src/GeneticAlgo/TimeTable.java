package GeneticAlgo;

// 時間割のクラス
public class TimeTable {

	private String f_DayOfWeek;// 曜日
	private int f_Grade;// 学年
	private String f_Course;// コース
	private String f_Subject;// 科目名
	private String f_Teacher;// 担当の先生

	/*
	 * コンストラクタ
	 * 0またはnullにする
	 */
	public TimeTable(){
		f_DayOfWeek=null;
		f_Grade=0;
		f_Course=null;
		f_Subject=null;
		f_Teacher=null;
	}

	/*
	 * 曜日のゲッター
	 *
	 * @return 曜日
	 */
	public String getDayOfWeek() {
		return f_DayOfWeek;
	}

	/*
	 * 学年のゲッター
	 *
	 * @return 学年
	 */
	public int getGrade() {
		return f_Grade;
	}

	/*
	 * コースのゲッター
	 *
	 * @return コース
	 */
	public String getCourse() {
		return f_Course;
	}

	/*
	 * 科目名のゲッター
	 *
	 * @return 科目名
	 */
	public String getSubject() {
		return f_Subject;
	}

	/*
	 * 先生のゲッター
	 *
	 * @return 先生
	 */
	public String getTeacher() {
		return f_Teacher;
	}

	/*
	 * 曜日のセッター
	 *
	 * @param dayOfWeek 曜日
	 */
	public void setDayOfWeek(String dayOfWeek) {
		f_DayOfWeek = dayOfWeek;
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
	 * コースのセッター
	 *
	 * @param course コース
	 */
	public void setCourse(String course) {
		f_Course = course;
	}

	/*
	 * 科目名のセッター
	 *
	 * @param subject 科目名
	 */
	public void setSubject(String subject) {
		f_Subject = subject;
	}

	/*
	 * 先生のセッター
	 *
	 * @param teacher 先生
	 */
	public void setTeacher(String teacher) {
		f_Teacher = teacher;
	}
}
