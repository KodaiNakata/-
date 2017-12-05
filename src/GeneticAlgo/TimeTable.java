package GeneticAlgo;

/*
 *  ���Ԋ��̃N���X
 *  @author Nakata
 */
public class TimeTable {

	private String f_DayOfWeek;// �j��
	private int f_Period;// ����
	// private int f_Grade;// �w�N
	private ClassOfGrade f_ClassOfGrade;// �w�N���Ƃ̎���
	private String f_ClassRoom;// ����

	/*
	 * �R���X�g���N�^
	 */
	public TimeTable() {
		f_DayOfWeek = "�s��";
		f_Period = 0;
		// f_Grade = 0;
		f_ClassOfGrade = new ClassOfGrade();
		f_ClassRoom = "�s��";
	}

	/*
	 * ���Ԋ��̃N���X�̑S�t�B�[���h�ɐݒ肷��
	 * 
	 * @param day_of_week �j��
	 * 
	 * @param period ����
	 * 
	 * @param class_of_grade �w�N���Ƃ̎���
	 * 
	 * @param class_room ����
	 */
	public void setTimeTable(String day_of_week, int period, ClassOfGrade class_of_grade) {
		f_DayOfWeek = day_of_week;
		f_Period = period;
		f_ClassOfGrade = class_of_grade;
	}

	// /*
	// * ���Ԋ��̌���
	// */
	// public static void exchangeTimeTable(TimeTable[] time_table1,
	// TimeTable[] time_table2) {
	//
	// TimeTable tmpTimeTable = new TimeTable();
	//
	// tmpTimeTable.setTimeTable(time_table1[0].getDayOfWeek(),
	// time_table1[0].getPeriod(), time_table1[0].getClassOfGrade(),
	// time_table1[0].getClassRoom());
	//
	// time_table1[0].setTimeTable(time_table2[0].getDayOfWeek(),
	// time_table2[0].getPeriod(), time_table2[0].getClassOfGrade(),
	// time_table2[0].getClassRoom());
	//
	// time_table2[0].setTimeTable(tmpTimeTable.getDayOfWeek(),
	// tmpTimeTable.getPeriod(), tmpTimeTable.getClassOfGrade(),
	// tmpTimeTable.getClassRoom());
	// }

	/*
	 * �j���𐔒l�ɕς���
	 * 
	 * @param day_of_week �j��
	 * 
	 * @return �j���𐔒l�ɕς�������
	 */
	public static int changeDayToValue(String day_of_week) {

		if (day_of_week == null) {
			day_of_week = "�s��";
		}

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

	/*
	 * �l��j���ɕς���
	 * 
	 * @param value �l
	 * 
	 * @return �l��j���ɕς�������
	 */
	public static String changeValueToDay(int value) {

		switch (value) {

		case 0:
			return "��";

		case 1:
			return "��";

		case 2:
			return "��";

		case 3:
			return "��";

		case 4:
			return "��";

		case 5:
			return "�y";
		}
		return "�s��";
	}

	/*
	 * �����𐔒l�ɕς���
	 * 
	 * @param class_room ����
	 * 
	 * @return �����𐔒l�ɕς�������
	 */
	public static int changeRoomToValue(String class_room) {

		switch (class_room) {
		case "31-202":
			return 0;
		case "31-301":
			return 1;
		case "31-302":
			return 2;
		case "31-401":
			return 3;
		case "31-402":
			return 4;
		case "31-501":
			return 5;
		case "31-502":
			return 6;
		case "31-503":
			return 7;
		case "31-505":
			return 8;
		case "31-506":
			return 9;
		case "31-601":
			return 10;
		case "31-602":
			return 11;
		case "31-603":
			return 12;
		case "31-604":
			return 13;
		case "31-801":
			return 14;
		case "31-802":
			return 15;
		case "31-803":
			return 16;
		}
		return -1;
	}

	/*
	 * ���l�������ɕς���
	 * 
	 * @param value ���l
	 * 
	 * @return ���l�������ɕς�������
	 */
	public static String changeValueToRoom(int value) {

		switch (value) {
		case 0:
			return "31-202";
		case 1:
			return "31-301";
		case 2:
			return "31-302";
		case 3:
			return "31-401";
		case 4:
			return "31-402";
		case 5:
			return "31-501";
		case 6:
			return "31-502";
		case 7:
			return "31-503";
		case 8:
			return "31-505";
		case 9:
			return "31-506";
		case 10:
			return "31-601";
		case 11:
			return "31-602";
		case 12:
			return "31-603";
		case 13:
			return "31-604";
		case 14:
			return "31-801";
		case 15:
			return "31-802";
		case 16:
			return "31-803";
		}
		return "�s��";
	}

	// -------------------------------//
	// ------------�Q�b�^�[-----------//
	// -------------------------------//
	/*
	 * �j���̃Q�b�^�[
	 * 
	 * @return �j��
	 */
	public String getDayOfWeek() {
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

	// /*
	// * �w�N�̃Q�b�^�[
	// */
	// public int getGrade() {
	// return f_Grade;
	// }

	/*
	 * �w�N���Ƃ̎��Ƃ̃Q�b�^�[
	 * 
	 * @return �w�N���Ƃ̎���
	 */
	public ClassOfGrade getClassOfGrade() {
		return f_ClassOfGrade;
	}

	/*
	 * �����̃Q�b�^�[
	 * 
	 * @return ����
	 */
	public String getClassRoom() {
		return f_ClassRoom;
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

		if ("�s��".equals(f_DayOfWeek)) {
			f_DayOfWeek = dayOfWeek;
		}

		else {
			f_DayOfWeek = f_DayOfWeek.replaceAll(f_DayOfWeek, dayOfWeek);
		}
	}

	/*
	 * ���ڂ̃Z�b�^�[
	 * 
	 * @param period ����
	 */
	public void setPeriod(int period) {
		f_Period = period;
	}

	// /*
	// * �w�N�̃Z�b�^�[
	// *
	// * @param grade �w�N
	// */
	// public void setGrade(int grade) {
	// f_Grade = grade;
	// }

	/*
	 * �w�N���Ƃ̎��Ƃ̃Z�b�^�[
	 * 
	 * @param class_of_grade �w�N���Ƃ̎���
	 */
	public void setClassOfGrade(ClassOfGrade class_of_grade) {
		f_ClassOfGrade = class_of_grade;
	}

	/*
	 * �����̃Z�b�^�[
	 * 
	 * @param class_room ����
	 */
	public void setClassRoom(String class_room) {
		f_ClassRoom = class_room;
	}
}
