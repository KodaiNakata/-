package GeneticAlgo;

// ���Ԋ��̃N���X
public class TimeTable {

	private String f_DayOfWeek;// �j��
	private int f_Period;// ����
	private int f_Grade;// �w�N
	private ClassOfGrade f_ClassOfGrade;// �w�N���Ƃ̎���

	/*
	 * �R���X�g���N�^ 0�܂���null
	 */
	public TimeTable() {
		f_DayOfWeek = null;
		f_Period = 0;
		f_Grade = 0;
		f_ClassOfGrade = new ClassOfGrade();
	}

	/*
	 * �j���𐔒l�ɕς���
	 *
	 * @param day_of_week �j��
	 *
	 * @return �j���𐔒l�ɕς�������
	 */
	public int changeValueDay(String day_of_week) {
		switch (day_of_week) {
		case "��":
			return 0;

		case "��":
			return 1;

		case "��":
			return 2;

		case "��":
			return 3;

		case "��":
			return 4;

		case "�y":
			return 5;

		default:
			return -1;
		}
	}

	// -------------------------------//
	// ------------�Q�b�^�[-----------//
	// -------------------------------//
	/*
	 * �j���̃Q�b�^�[
	 *
	 * @return �j��
	 */
	public String getDayOfWeeks() {
		return f_DayOfWeek;
	}

	/*
	 * ���ڂ̃Q�b�^�[
	 *
	 * @return ����
	 */
	public int getPeriod() {
		return f_Period;
	}

	/*
	 * �w�N�̃Q�b�^�[
	 */
	public int getGrade() {
		return f_Grade;
	}

	/*
	 * �w�N���Ƃ̎��Ƃ̃Q�b�^�[
	 *
	 * @return �w�N���Ƃ̎���
	 */
	public ClassOfGrade getClassOfGrade() {
		return f_ClassOfGrade;
	}

	// -------------------------------//
	// ------------�Z�b�^�[-----------//
	// -------------------------------//
	/*
	 * �j���̃Z�b�^�[
	 *
	 * @param dayOfWeek �j��
	 */
	public void setDayOfWeek(String dayOfWeek) {
		f_DayOfWeek = dayOfWeek;
	}

	/*
	 * ���ڂ̃Z�b�^�[
	 *
	 * @param period ����
	 */
	public void setPeriod(int period) {
		f_Period = period;
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
	 * �w�N���Ƃ̎��Ƃ̃Z�b�^�[
	 *
	 * @param class_of_grade �w�N���Ƃ̎���
	 */
	public void setClassOfGrade(ClassOfGrade class_of_grade) {
		f_ClassOfGrade = class_of_grade;
	}
}
