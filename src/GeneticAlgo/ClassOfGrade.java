package GeneticAlgo;


/*
 *  学年ごとの授業のクラス
 *  @author Nakata
 */
public class ClassOfGrade {

//	private String f_DayOfWeek;// 曜日
//	private int f_Period;// 限目
	private int f_Number;// コマ数
	private int f_Grade;// 学年
	private String f_PreviousOrLatter;// 前期後期
	private String f_Subject;// 科目
	private String f_Teacher;// 先生の名前
	private String f_CourseOrClass;// コース・クラス
//	private String f_ClassRoom;// 教室
	private Teacher f_Teachers;// 担当の先生(Teacherクラス)

//	// 動的配列
//	private ArrayList<Intger> f_Grades=new ArrayList<Integer>();
//	private ArrayList<Integer> f_Numbers = new ArrayList<Integer>();// コマ数
//	private ArrayList<String> f_CoursesOrClasses = new ArrayList<String>();// コースまたはクラス
//	private ArrayList<String> f_Subjects = new ArrayList<String>();// 科目名
//	private ArrayList<String> f_Teacher=new ArrayList<String>();// 担当の先生
//	private ArrayList<Teacher> f_Teachers = new ArrayList<Teacher>();// 担当の先生(Teacherクラス)
//	private ArrayList<String> f_ClassRooms = new ArrayList<String>();// 教室

	/*
	 * コンストラクタ
	 */
	public ClassOfGrade() {
//		f_DayOfWeek=null;
//		f_Period=0;
		f_Grade = 0;
		f_PreviousOrLatter = null;
		f_Number = 0;
		f_CourseOrClass = null;
		f_Subject = null;
		f_Teacher=null;
		f_Teachers = null;
//		f_ClassRoom = null;
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
//	/*
//	 * 曜日のゲッター
//	 * @return 曜日
//	 */
//	public String getDayOfWeek(){
//		return f_DayOfWeek;
//	}

//	/*
//	 * 限目のゲッター
//	 * @return 限目
//	 */
//	public int getPeriod(){
//		return f_Period;
//	}

	/*
	 * コマ数のゲッター
	 *
	 * @return コマ数
	 */
	public int getNumber(){
		return f_Number;
	}

//	/*
//	 * コマ数のゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return コマ数
//	 */
//	public int getNumbers(int number) {
//		return f_Numbers.get(number);
//	}

//	/*
//	 * コマ数のサイズのゲッター
//	 *
//	 * @return コマ数のサイズ
//	 */
//	public int getNumbersSize(){
//		return f_Numbers.size();
//	}

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
	public String getCourseOrClass(){
		return f_CourseOrClass;
	}

//	/*
//	 * コースまたはクラスのゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return コースまたはクラス
//	 */
//	public String getCoursesOrClasses(int number) {
//		return f_CoursesOrClasses.get(number);
//	}

//	/*
//	 * コースまたはクラスのサイズのゲッター
//	 *
//	 * @return コースまたはクラスのサイズ
//	 */
//	public int getCoursesOrClassesSize(){
//		return f_CoursesOrClasses.size();
//	}

	/*
	 * 科目のゲッター
	 *
	 * @return 科目名
	 */
	public String getSubject(){
		return f_Subject;
	}

//	/*
//	 * 科目名のゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return 科目名
//	 */
//	public String getSubjects(int number) {
//		return f_Subjects.get(number);
//	}

//	/*
//	 * 科目名のサイズのゲッター
//	 *
//	 * @return 科目名のサイズ
//	 */
//	public int getSubjectsSize(){
//		return f_Subjects.size();
//	}

//	/*
//	 * 先生のゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return 先生
//	 */
//	public String getTeacher(int number){
//		return f_Teacher.get(number);
//	}

	/*
	 * 先生のゲッター
	 * @return 先生の名前
	 */
	public String getTeacher(){
		return f_Teacher;
	}
//	/*
//	 * 先生のサイズのゲッター
//	 *
//	 * @return 先生のサイズ
//	 */
//	public int getTeacherSize(){
//		return f_Teacher.size();
//	}

//	/*
//	 * Teacherクラスの先生のゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return 先生
//	 */
//	public Teacher getTeachers(int number) {
//		return f_Teachers.get(number);
//	}

//	/*
//	 * Teacherクラスの先生のサイズのゲッター
//	 *
//	 * @return Teacherクラスの先生のサイズ
//	 */
//	public int getTeachersSize(){
//		return f_Teachers.size();
//	}

//	/*
//	 * 教室のゲッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @return 教室
//	 */
//	public String getClassRooms(int number) {
//		return f_ClassRooms.get(number);
//	}

//	/*
//	 * 教室のサイズのゲッター
//	 *
//	 * @return 教室のサイズ
//	 */
//	public int getClassRoomsSize(){
//		return f_ClassRooms.size();
//	}

//	/*
//	 * 教室のゲッター
//	 *
//	 * @return 教室
//	 */
//	public String getClassRoom(){
//		return f_ClassRoom;
//	}
	// ----------------------------//
	// ----------セッター関数------//
	// ----------------------------//
//	/*
//	 * コマ数のセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param num コマ数
//	 */
//	public void setNumbers(int number, int num) {
//		f_Numbers.set(number, num);
//	}

//	/*
//	 * 曜日のセッター
//	 * @param day_of_week 曜日
//	 */
//	public void setDayOfWeek(String day_of_week){
//		f_DayOfWeek=day_of_week;
//	}

//	/*
//	 * 限目のセッター
//	 * @param period 限目
//	 */
//	public void setPeriod(int period){
//		f_Period=period;
//	}

	/*
	 * コマ数のセッター
	 *
	 * @param number コマ数
	 */
	public void setNumber(int number){
		f_Number=number;
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

//	/*
//	 * コースまたはクラスのセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param course_or_class コースまたはクラス
//	 */
//	public void setCoursesOrClasses(int number, String course_or_class) {
//		f_CoursesOrClasses.set(number, course_or_class);
//	}

	/*
	 * コースまたはクラスのセッター
	 *
	 * @param course_or_class コースまたはクラス
	 */
	public void setCourseOrClass(String course_or_class){
		f_CourseOrClass=course_or_class;
	}

//	/*
//	 * 科目名のセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param subject 科目名
//	 */
//	public void setSubjects(int number, String subject) {
//		f_Subjects.set(number, subject);
//	}

	/*
	 * 科目名のセッター
	 *
	 * @param subject
	 */
	public void setSubject(String subject){
		f_Subject=subject;
	}

//	/*
//	 * 先生のセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param teacher 先生
//	 *
//	 */
//	public void setTeacher(int number,String teacher){
//		f_Teacher.set(number,teacher);
//	}

	/*
	 * 先生のセッター
	 *
	 * @param teacher 先生
	 */
	public void setTeacher(String teacher){
		f_Teacher=teacher;
	}

//	/*
//	 * Teacherクラスの先生のセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param teacher 先生
//	 */
//	public void setTeachers(int number, Teacher teacher) {
//		f_Teachers.set(number, teacher);
//	}

//	/*
//	 * 教室のセッター
//	 *
//	 * @param class_room 教室
//	 */
//	public void setClassRoom(String class_room){
//		f_ClassRoom=class_room;
//	}

//	/*
//	 * 教室のセッター
//	 *
//	 * @param number 何番目
//	 *
//	 * @param class_room 教室
//	 */
//	public void setClassRooms(int number, String class_room) {
//		f_ClassRooms.set(number, class_room);
//	}

	// ----------------------------//
	// ----------add関数-----------//
	// ----------------------------//
//	/*
//	 * コマ数の追加
//	 *
//	 * @param number コマ数
//	 */
//	public void addNumbers(int number) {
//		f_Numbers.add(number);
//	}
//
//	/*
//	 * コースまたはクラスの追加
//	 *
//	 * @param course_or_class コースまたはクラス
//	 */
//	public void addCoursesOrClasses(String course_or_class) {
//		f_CoursesOrClasses.add(course_or_class);
//	}
//
//	/*
//	 * 科目の追加
//	 *
//	 * @param subject 科目
//	 */
//	public void addSubjects(String subject) {
//		f_Subjects.add(subject);
//	}
//
//	/*
//	 * 先生の追加
//	 *
//	 * @param teacher 先生
//	 */
//	public void addTeacher(String teacher){
//		f_Teacher.add(teacher);
//	}
//
//	/*
//	 * Teacherクラスの先生の追加
//	 *
//	 * @param teacher 先生
//	 */
//	public void addTeachers(Teacher teacher) {
//		f_Teachers.add(teacher);
//	}
//
//	/*
//	 * 教室の追加
//	 *
//	 * @param class_room 教室
//	 */
//	public void addClassRooms(String class_room) {
//		f_ClassRooms.add(class_room);
//	}

}
