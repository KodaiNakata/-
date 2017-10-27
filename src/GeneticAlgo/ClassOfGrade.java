package GeneticAlgo;

/*
 *  学年ごとの授業のクラス
 *  @author Nakata
 */
public class ClassOfGrade {

	private int f_Number;// コマ数
	private int f_Grade;// 学年
	private String f_PreviousOrLatter;// 前期後期
	private String f_Subject;// 科目
	private String f_CourseOrClass;// コース・クラス
	private Teacher f_Teachers;// 担当の先生(Teacherクラス)
	private int f_NeedPC;// PCが必要か
	
	/*
	 * コンストラクタ
	 */
	public ClassOfGrade() {
		f_Grade = 0;
		f_PreviousOrLatter = null;
		f_Number = 0;
		f_CourseOrClass = null;
		f_Subject = null;
		f_Teachers = new Teacher();
		f_NeedPC=0;
	}

	/*
	 * 前期後期を数値に変える
	 *
	 * @param previous_latter 前期後期
	 *
	 * @return 前期後期を数値に変えたもの
	 */
	public static int changePreviousOrLatterToValue(String previous_latter) {

		switch (previous_latter) {
		case "前期":
			return 0;

		case "後期":
			return 1;

		default:
			return -1;
		}
	}

	/*
	 * 値を前期後期に変える
	 *
	 * @param value 値
	 *
	 * @return 値を前期後期に変えたもの
	 */
	public static String changeValueToPreviousOrLatter(int value) {

		switch (value) {

		case 0:
			return "前期";

		case 1:
			return "後期";

		}
		return "不明";
	}

	// ----------------------------//
	// ----------ゲッター関数------//
	// ----------------------------//

	/*
	 * コマ数のゲッター
	 *
	 * @return コマ数
	 */
	public int getNumber() {
		return f_Number;
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
	 * 前期後期のゲッター
	 *
	 * @return 前期か後期か
	 */
	public String getPreviousOrLatter() {
		return f_PreviousOrLatter;
	}

	/*
	 * コース・クラスのゲッター
	 *
	 * @return コースまたはクラス
	 */
	public String getCourseOrClass() {
		return f_CourseOrClass;
	}

	/*
	 * 科目のゲッター
	 *
	 * @return 科目名
	 */
	public String getSubject() {
		return f_Subject;
	}

	/*
	 * 先生クラスのゲッター
	 *
	 * @return 先生クラス
	 */
	public Teacher getTeachers() {
		return f_Teachers;
	}

	/*
	 * PCが必要かのゲッター
	 * 
	 * @return PC(1:必要,0:不要)
	 */
	public int getNeedPC(){
		return f_NeedPC;
	}
	
	// ----------------------------//
	// ----------セッター関数------//
	// ----------------------------//

	/*
	 * コマ数のセッター
	 *
	 * @param number コマ数
	 */
	public void setNumber(int number) {
		f_Number = number;
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
	 * 前期か後期かのセッター
	 *
	 * @param previous_or_latter 前期か後期か
	 */
	public void setPreviousOrLatter(String previous_or_latter) {
		f_PreviousOrLatter = previous_or_latter;
	}

	/*
	 * コースまたはクラスのセッター
	 *
	 * @param course_or_class コースまたはクラス
	 */
	public void setCourseOrClass(String course_or_class) {
		f_CourseOrClass = course_or_class;
	}

	/*
	 * 科目名のセッター
	 *
	 * @param subject
	 */
	public void setSubject(String subject) {
		f_Subject = subject;
	}


	/*
	 * 先生のセッター(Teacherクラス)
	 *
	 * @param teachers 先生クラス
	 */
	public void setTeachers(Teacher teachers) {
		f_Teachers = teachers;
	}
	
	/*
	 * PCが必要かのセッター
	 * 
	 * @param need PCが必要か(1:必要,0:不要)
	 */
	public void setNeedPC(int need){
		f_NeedPC=need;
	}
	
	// ----------------------------//
	// ----------add関数-----------//
	// ----------------------------//
	// /*
	// * コマ数の追加
	// *
	// * @param number コマ数
	// */
	// public void addNumbers(int number) {
	// f_Numbers.add(number);
	// }
	//
	// /*
	// * コースまたはクラスの追加
	// *
	// * @param course_or_class コースまたはクラス
	// */
	// public void addCoursesOrClasses(String course_or_class) {
	// f_CoursesOrClasses.add(course_or_class);
	// }
	//
	// /*
	// * 科目の追加
	// *
	// * @param subject 科目
	// */
	// public void addSubjects(String subject) {
	// f_Subjects.add(subject);
	// }
	//
	// /*
	// * 先生の追加
	// *
	// * @param teacher 先生
	// */
	// public void addTeacher(String teacher){
	// f_Teacher.add(teacher);
	// }
	//
	// /*
	// * Teacherクラスの先生の追加
	// *
	// * @param teacher 先生
	// */
	// public void addTeachers(Teacher teacher) {
	// f_Teachers.add(teacher);
	// }
	//
	// /*
	// * 教室の追加
	// *
	// * @param class_room 教室
	// */
	// public void addClassRooms(String class_room) {
	// f_ClassRooms.add(class_room);
	// }

}
