package GeneticAlgo;

import java.util.ArrayList;

// �w�N���Ƃ̎��Ƃ̃N���X
public class ClassOfGrade {

	private int f_Grade;// �w�N
	private ArrayList<Integer> f_Numbers = new ArrayList<Integer>();// �R�}��
	private String f_PreviousOrLatter;// �O�����
	private ArrayList<String> f_CoursesOrClasses = new ArrayList<String>();// �R�[�X�܂��̓N���X
	private ArrayList<String> f_Subjects = new ArrayList<String>();// �Ȗږ�
	private ArrayList<String> f_Teacher=new ArrayList<String>();// �S���̐搶
	private ArrayList<Teacher> f_Teachers = new ArrayList<Teacher>();// �S���̐搶(Teacher�N���X)
	private ArrayList<String> f_ClassRooms = new ArrayList<String>();// ����

	/*
	 * �R���X�g���N�^
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
	// ----------�Q�b�^�[�֐�------//
	// ----------------------------//
	/*
	 * �R�}���̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return �R�}��
	 */
	public int getNumbers(int number) {
		return f_Numbers.get(number);
	}

	/*
	 * �R�}���̃T�C�Y�̃Q�b�^�[
	 *
	 * @return �R�}���̃T�C�Y
	 */
	public int getNumbersSize(){
		return f_Numbers.size();
	}

	/*
	 * �w�N�̃Q�b�^�[
	 *
	 * @return �w�N
	 */
	public int getGrade() {
		return f_Grade;
	}

	/*
	 * �O������̃Q�b�^�[
	 *
	 * @return �O���������
	 */
	public String getPreviousOrLatter() {
		return f_PreviousOrLatter;
	}

	/*
	 * �R�[�X�܂��̓N���X�̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return �R�[�X
	 */
	public String getCoursesOrClasses(int number) {
		return f_CoursesOrClasses.get(number);
	}

	/*
	 * �R�[�X�܂��̓N���X�̃T�C�Y�̃Q�b�^�[
	 *
	 * @return �R�[�X�܂��̓N���X�̃T�C�Y
	 */
	public int getCoursesOrClassesSize(){
		return f_CoursesOrClasses.size();
	}
	/*
	 * �Ȗږ��̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return �Ȗږ�
	 */
	public String getSubjects(int number) {
		return f_Subjects.get(number);
	}

	/*
	 * �Ȗږ��̃T�C�Y�̃Q�b�^�[
	 *
	 * @return �Ȗږ��̃T�C�Y
	 */
	public int getSubjectsSize(){
		return f_Subjects.size();
	}

	/*
	 * �搶�̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return �搶
	 */
	public String getTeacher(int number){
		return f_Teacher.get(number);
	}

	/*
	 * �搶�̃T�C�Y�̃Q�b�^�[
	 *
	 * @return �搶�̃T�C�Y
	 */
	public int getTeacherSize(){
		return f_Teacher.size();
	}
	/*
	 * Teacher�N���X�̐搶�̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return �搶
	 */
	public Teacher getTeachers(int number) {
		return f_Teachers.get(number);
	}

	/*
	 * Teacher�N���X�̐搶�̃T�C�Y�̃Q�b�^�[
	 *
	 * @return Teacher�N���X�̐搶�̃T�C�Y
	 */
	public int getTeachersSize(){
		return f_Teachers.size();
	}

	/*
	 * �����̃Q�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @return ����
	 */
	public String getClassRooms(int number) {
		return f_ClassRooms.get(number);
	}

	/*
	 * �����̃T�C�Y�̃Q�b�^�[
	 *
	 * @return �����̃T�C�Y
	 */
	public int getClassRoomsSize(){
		return f_ClassRooms.size();
	}

	// ----------------------------//
	// ----------�Z�b�^�[�֐�------//
	// ----------------------------//
	/*
	 * �R�}���̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param num �R�}��
	 */
	public void setNumbers(int number, int num) {
		f_Numbers.set(number, num);
	}

	/*
	 * �w�N�̃Z�b�^�[
	 *
	 * @param grade �w�N
	 */
	public void setGrade(int grade) {
		f_Grade = grade;
	}

	/*
	 * �O����������̃Z�b�^�[
	 *
	 * @param previous_or_latter �O���������
	 */
	public void setPreviousOrLatter(String previous_or_latter) {
		f_PreviousOrLatter = previous_or_latter;
	}

	/*
	 * �R�[�X�܂��̓N���X�̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param course_or_class �R�[�X�܂��̓N���X
	 */
	public void setCoursesOrClasses(int number, String course_or_class) {
		f_CoursesOrClasses.set(number, course_or_class);
	}

	/*
	 * �Ȗږ��̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param subject �Ȗږ�
	 */
	public void setSubjects(int number, String subject) {
		f_Subjects.set(number, subject);
	}

	/*
	 * �搶�̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param teacher �搶
	 *
	 */
	public void setTeacher(int number,String teacher){
		f_Teacher.set(number,teacher);
	}
	/*
	 * Teacher�N���X�̐搶�̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param teacher �搶
	 */
	public void setTeachers(int number, Teacher teacher) {
		f_Teachers.set(number, teacher);
	}

	/*
	 * �����̃Z�b�^�[
	 *
	 * @param number ���Ԗ�
	 *
	 * @param class_room ����
	 */
	public void setClassRooms(int number, String class_room) {
		f_ClassRooms.set(number, class_room);
	}

	// ----------------------------//
	// ----------add�֐�-----------//
	// ----------------------------//
	/*
	 * �R�}���̒ǉ�
	 *
	 * @param number �R�}��
	 */
	public void addNumbers(int number) {
		f_Numbers.add(number);
	}

	/*
	 * �R�[�X�܂��̓N���X�̒ǉ�
	 *
	 * @param course_or_class �R�[�X�܂��̓N���X
	 */
	public void addCoursesOrClasses(String course_or_class) {
		f_CoursesOrClasses.add(course_or_class);
	}

	/*
	 * �Ȗڂ̒ǉ�
	 *
	 * @param subject �Ȗ�
	 */
	public void addSubjects(String subject) {
		f_Subjects.add(subject);
	}

	/*
	 * �搶�̒ǉ�
	 *
	 * @param teacher �搶
	 */
	public void addTeacher(String teacher){
		f_Teacher.add(teacher);
	}

	/*
	 * Teacher�N���X�̐搶�̒ǉ�
	 *
	 * @param teacher �搶
	 */
	public void addTeachers(Teacher teacher) {
		f_Teachers.add(teacher);
	}

	/*
	 * �����̒ǉ�
	 *
	 * @param class_room ����
	 */
	public void addClassRooms(String class_room) {
		f_ClassRooms.add(class_room);
	}

}
