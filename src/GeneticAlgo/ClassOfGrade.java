package GeneticAlgo;

import java.util.ArrayList;

// 学年ごとの授業のクラス
public class ClassOfGrade {

	private int f_Grade;// 学年
	private ArrayList<Integer> f_Numbers = new ArrayList<Integer>();// コマ数
	private String f_PreviousOrLatter;// 前期後期
	private ArrayList<String> f_CoursesOrClasses = new ArrayList<String>();// コースまたはクラス
	private ArrayList<String> f_Subjects = new ArrayList<String>();// 科目名
	private ArrayList<String> f_Teacher=new ArrayList<String>();// 担当の先生
	private ArrayList<Teacher> f_Teachers = new ArrayList<Teacher>();// 担当の先生(Teacherクラス)
	private ArrayList<String> f_ClassRooms = new ArrayList<String>();// 教室

	/*
	 * コンストラクタ
	 */
	public ClassOfGrade() {
		f_Grade = 0;
		f_Numbers = null;
		f_PreviousOrLatter = null;
		f_CoursesOrClasses = null;
		f_Subjects = null;
		f_Teacher=null;
		f_Teachers = null;
		f_ClassRooms = null;
	}

	// ----------------------------//
	// ----------ゲッター関数------//
	// ----------------------------//
	/*
	 * コマ数のゲッター
	 *
	 * @param number 何番目
	 *
	 * @return コマ数
	 */
	public int getNumbers(int number) {
		return f_Numbers.get(number);
	}

	/*
	 * コマ数のサイズのゲッター
	 *
	 * @return コマ数のサイズ
	 */
	public int getNumbersSize(){
		return f_Numbers.size();
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
	 * コースまたはクラスのゲッター
	 *
	 * @param number 何番目
	 *
	 * @return コース
	 */
	public String getCoursesOrClasses(int number) {
		return f_CoursesOrClasses.get(number);
	}

	/*
	 * コースまたはクラスのサイズのゲッター
	 *
	 * @return コースまたはクラスのサイズ
	 */
	public int getCoursesOrClassesSize(){
		return f_CoursesOrClasses.size();
	}
	/*
	 * 科目名のゲッター
	 *
	 * @param number 何番目
	 *
	 * @return 科目名
	 */
	public String getSubjects(int number) {
		return f_Subjects.get(number);
	}

	/*
	 * 科目名のサイズのゲッター
	 *
	 * @return 科目名のサイズ
	 */
	public int getSubjectsSize(){
		return f_Subjects.size();
	}

	/*
	 * 先生のゲッター
	 *
	 * @param number 何番目
	 *
	 * @return 先生
	 */
	public String getTeacher(int number){
		return f_Teacher.get(number);
	}

	/*
	 * 先生のサイズのゲッター
	 *
	 * @return 先生のサイズ
	 */
	public int getTeacherSize(){
		return f_Teacher.size();
	}
	/*
	 * Teacherクラスの先生のゲッター
	 *
	 * @param number 何番目
	 *
	 * @return 先生
	 */
	public Teacher getTeachers(int number) {
		return f_Teachers.get(number);
	}

	/*
	 * Teacherクラスの先生のサイズのゲッター
	 *
	 * @return Teacherクラスの先生のサイズ
	 */
	public int getTeachersSize(){
		return f_Teachers.size();
	}

	/*
	 * 教室のゲッター
	 *
	 * @param number 何番目
	 *
	 * @return 教室
	 */
	public String getClassRooms(int number) {
		return f_ClassRooms.get(number);
	}

	/*
	 * 教室のサイズのゲッター
	 *
	 * @return 教室のサイズ
	 */
	public int getClassRoomsSize(){
		return f_ClassRooms.size();
	}

	// ----------------------------//
	// ----------セッター関数------//
	// ----------------------------//
	/*
	 * コマ数のセッター
	 *
	 * @param number 何番目
	 *
	 * @param num コマ数
	 */
	public void setNumbers(int number, int num) {
		f_Numbers.set(number, num);
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
	 * @param number 何番目
	 *
	 * @param course_or_class コースまたはクラス
	 */
	public void setCoursesOrClasses(int number, String course_or_class) {
		f_CoursesOrClasses.set(number, course_or_class);
	}

	/*
	 * 科目名のセッター
	 *
	 * @param number 何番目
	 *
	 * @param subject 科目名
	 */
	public void setSubjects(int number, String subject) {
		f_Subjects.set(number, subject);
	}

	/*
	 * 先生のセッター
	 *
	 * @param number 何番目
	 *
	 * @param teacher 先生
	 *
	 */
	public void setTeacher(int number,String teacher){
		f_Teacher.set(number,teacher);
	}
	/*
	 * Teacherクラスの先生のセッター
	 *
	 * @param number 何番目
	 *
	 * @param teacher 先生
	 */
	public void setTeachers(int number, Teacher teacher) {
		f_Teachers.set(number, teacher);
	}

	/*
	 * 教室のセッター
	 *
	 * @param number 何番目
	 *
	 * @param class_room 教室
	 */
	public void setClassRooms(int number, String class_room) {
		f_ClassRooms.set(number, class_room);
	}

	// ----------------------------//
	// ----------add関数-----------//
	// ----------------------------//
	/*
	 * コマ数の追加
	 *
	 * @param number コマ数
	 */
	public void addNumbers(int number) {
		f_Numbers.add(number);
	}

	/*
	 * コースまたはクラスの追加
	 *
	 * @param course_or_class コースまたはクラス
	 */
	public void addCoursesOrClasses(String course_or_class) {
		f_CoursesOrClasses.add(course_or_class);
	}

	/*
	 * 科目の追加
	 *
	 * @param subject 科目
	 */
	public void addSubjects(String subject) {
		f_Subjects.add(subject);
	}

	/*
	 * 先生の追加
	 *
	 * @param teacher 先生
	 */
	public void addTeacher(String teacher){
		f_Teacher.add(teacher);
	}

	/*
	 * Teacherクラスの先生の追加
	 *
	 * @param teacher 先生
	 */
	public void addTeachers(Teacher teacher) {
		f_Teachers.add(teacher);
	}

	/*
	 * 教室の追加
	 *
	 * @param class_room 教室
	 */
	public void addClassRooms(String class_room) {
		f_ClassRooms.add(class_room);
	}

}
