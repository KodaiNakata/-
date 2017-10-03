package GeneticAlgo;

// ���Ԋ��̃N���X
public class TimeTable {

	private String f_DayOfWeek;// �j��
	private int f_Grade;// �w�N
	private String f_Course;// �R�[�X
	private String f_Subject;// �Ȗږ�
	private String f_Teacher;// �S���̐搶

	/*
	 * �R���X�g���N�^
	 * 0�܂���null�ɂ���
	 */
	public TimeTable(){
		f_DayOfWeek=null;
		f_Grade=0;
		f_Course=null;
		f_Subject=null;
		f_Teacher=null;
	}

	/*
	 * �j���̃Q�b�^�[
	 *
	 * @return �j��
	 */
	public String getDayOfWeek() {
		return f_DayOfWeek;
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
	 * �R�[�X�̃Q�b�^�[
	 *
	 * @return �R�[�X
	 */
	public String getCourse() {
		return f_Course;
	}

	/*
	 * �Ȗږ��̃Q�b�^�[
	 *
	 * @return �Ȗږ�
	 */
	public String getSubject() {
		return f_Subject;
	}

	/*
	 * �搶�̃Q�b�^�[
	 *
	 * @return �搶
	 */
	public String getTeacher() {
		return f_Teacher;
	}

	/*
	 * �j���̃Z�b�^�[
	 *
	 * @param dayOfWeek �j��
	 */
	public void setDayOfWeek(String dayOfWeek) {
		f_DayOfWeek = dayOfWeek;
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
	 * �R�[�X�̃Z�b�^�[
	 *
	 * @param course �R�[�X
	 */
	public void setCourse(String course) {
		f_Course = course;
	}

	/*
	 * �Ȗږ��̃Z�b�^�[
	 *
	 * @param subject �Ȗږ�
	 */
	public void setSubject(String subject) {
		f_Subject = subject;
	}

	/*
	 * �搶�̃Z�b�^�[
	 *
	 * @param teacher �搶
	 */
	public void setTeacher(String teacher) {
		f_Teacher = teacher;
	}
}
