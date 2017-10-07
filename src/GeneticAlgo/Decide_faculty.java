package GeneticAlgo;

import java.util.ArrayList;

/*
 * �Ȗڂ����ꂪ�S������̂������߂邽�߂̃N���X
 * @author Nakata
 */
public class Decide_faculty implements iTimeTable {

	private static StringBuffer TITLE = new StringBuffer("��`�I�A���S���Y����p�������Ԋ��쐬");
	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// �O���̎��Ԋ�
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// ����̎��Ԋ�
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData1=new ArrayList<ClassOfGrade>();// 1���̊w�N���Ƃ̎��Ƃ̃f�[�^
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData2=new ArrayList<ClassOfGrade>();// 2���̊w�N���Ƃ̎��Ƃ̃f�[�^
	protected ArrayList<ClassOfGrade> f_ClassOfGradeData3=new ArrayList<ClassOfGrade>();// 3���̊w�N���Ƃ̎��Ƃ̃f�[�^
//	protected static final String RESULT_FILE_NAME = "room_3.csv";// 3���̌��ʂ̃t�@�C���̖��O
	protected static int PROG_COUNT;// �v���O���������s������

	/*
	 * �R���X�g���N�^
	 */
	public Decide_faculty() {

		for (int day = 0; day < MAX_DAY; day++) {
			for (int period = 0; period < MAX_PERIOD; period++) {
				f_PreviousTimeTables[day][period] = new TimeTable();
				f_LatterTimeTables[day][period] = new TimeTable();
			}
		}
	}

//	/*
//	 * �O���ƌ���̎��Ԋ��̌��ʂ̕\��
//	 */
//	private void result() {
//
//		// �O���̎��Ԋ��̌���
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			System.out.print(TimeTable.changeValueToDay(day) + "�j��");
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				System.out.println((period + 1) + "����");
//
//				for (int number = 0; number < f_PreviousTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getGrade() + "�N,");// �w�N
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");// �R�[�X�E�N���X
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");// �w�N
//					System.out.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");// �S����
//					System.out.println(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom());// ����
//
//				}
//			}
//		}
//
//		// ����̎��Ԋ��̌���
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			System.out.print(TimeTable.changeValueToDay(day) + "�j��");
//
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				System.out.println((period + 1) + "����");
//
//				for (int number = 0; number < f_LatterTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//
//					System.out.print(f_LatterTimeTables[day][period].getGrade()
//							+ "�N,");// �w�N
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");// �R�[�X�E�N���X
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");// �w�N
//					System.out.print(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");// �S����
//					System.out.println(f_LatterTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom());// ����
//				}
//			}
//		}
//
//		result3();// 3���̃t�@�C��(�S���Ҍ���̃t�@�C��)��\��
//	}

//	/*
//	 * 3���̃t�@�C��(���ꂼ��̉Ȗڂ̒S����)�̌��ʂ�\��
//	 */
//	private void result3(){
//
//		for(int number=0;number<f_ClassOfGradeData3.size();number++){
//			System.out.print(f_ClassOfGradeData3.get(number).getNumber()+"�R�},");// �R�}��
//			System.out.print(f_ClassOfGradeData3.get(number).getGrade()+"�N,");// �w�N
//			System.out.print(f_ClassOfGradeData3.get(number).getPreviousOrLatter()+",");// �O�����
//			System.out.print(f_ClassOfGradeData3.get(number).getSubject()+",");// �Ȗږ�
//			System.out.println(f_ClassOfGradeData3.get(number).getTeacher());// �搶
//		}
//	}

	// ------------------------------------------//
	// -------------�t�@�C���֌W----------------//
	// ------------------------------------------//
	/*
	 * ���ʂ̃t�@�C������������
	 */
	private void writeResultFile() {
//		PrintWriter output;
//		output = FileIO.writeFile(RESULT_FILE_NAME, false);
//
//		System.out.println("�v���O�������I�����܂����B" + RESULT_FILE_NAME + "�ɏ������݂܂��B");
//
//		// �O���̎��Ԋ��쐬�̌��ʂ�\��
//		for (int day = 0; day < MAX_DAY; day++) {
//
//			for (int period = 0; period < MAX_PERIOD; period++) {
//
//				for (int number = 0; number < f_PreviousTimeTables[day][period]
//						.getClassesOfGradesSize(); number++) {
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getCourseOrClass()
//							+ ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getSubject() + ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getTeacher() + ",");
//					output.print(f_PreviousTimeTables[day][period]
//							.getClassesOfGrades(number).getClassRoom() + ",");
//				}
//			}
//			output.println();
//		}
//		output.close();
//		System.out.println("�������݂��I�����܂����B");

//		writeFile3();// 3���̃t�@�C��(�S���Ҍ���̃t�@�C��)����������
	}

//	/*
//	 * �S���҂����܂���3���̃t�@�C������������
//	 */
//	private void writeFile3(){
//		PrintWriter output;
//		output = FileIO.writeFile(FACULTY3_NAME, false);
//
//		System.out.println("�S���҂����܂���3���̃t�@�C��" + FACULTY3_NAME + "�ɏ������݂܂��B");
//
//		for(int number=0;number<f_TimeTableData3.size();number++){
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �j��
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ��
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �R�}��
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �w�N
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �O�����
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �Ȗږ�
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �S������
//			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ����
//			output.println();
//		}
//
//		output.close();
//		System.out.println("�S���҂����܂���3���̃t�@�C��" + FACULTY3_NAME + "�̏������݂��I�����܂����B");
//	}

//	/*
//	 * �t�@�C����ǂݍ���
//	 */
//	private void readFile() {
//
//		readFile1();// 1���̃t�@�C����ǂݍ���
//
//		readFile2();// 2���̃t�@�C����ǂݍ���
//
//		readFile3();// 3���̃t�@�C����ǂݍ���
//	}

//	/*
//	 * 1���̃t�@�C����ǂݍ���
//	 */
//	private void readFile1() {
//
//		String[] strData = new String[ORDER1_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE1_NAME);
//
//		ClassOfGrade classOfGradeData = new ClassOfGrade();
//
//		try {
//			String line = new String();
//
//			// ��ԏォ���ԉ��̍s�܂œǂݍ���
//			for (int cols = 0; cols < ORDER1_COLS; cols++) {
//
//				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
//				if ((line = input.readLine()) != null) {
//
//					// �ォ��1�s�ȏ�̂Ƃ�
//					if (1 <= cols) {
//						strData = line.split(",");
//
//						// �j�����󔒂łȂ��Ƃ�
//						if (strData[0] != null) {
//							classOfGradeData.setDayOfWeek(strData[0]);// �j��
//							classOfGradeData.setPeriod(Integer
//									.parseInt(strData[1]));// ��
//							classOfGradeData.getClassOfGrade().setNumber(
//									Integer.parseInt(strData[2]));// �R�}��
//							classOfGradeData
//									.setGrade(Integer.parseInt(strData[3]));// �w�N
//							classOfGradeData.getClassOfGrade()
//									.setPreviousOrLatter(strData[4]);// �O�����
//							classOfGradeData.getClassOfGrade().setSubject(
//									strData[5]);// �Ȗږ�
//							classOfGradeData.getClassOfGrade().setTeacher(
//									strData[6]);// �S������
//							classOfGradeData.getClassOfGrade().setClassRoom(
//									strData[7]);// ����
//
//							f_ClassOfGradeData1.add(classOfGradeData);// 1���̎��Ԋ��̓��I�z��ɒǉ�
//						}
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}
//
//	/*
//	 * 2���̃t�@�C����ǂݍ���
//	 */
//	private void readFile2() {
//
//		String[] strData = new String[ORDER2_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE2_NAME);
//
//		TimeTable timeTableData = new TimeTable();
//
//		try {
//			String line = new String();
//
//			// ��ԏォ���ԉ��̍s�܂œǂݍ���
//			for (int cols = 0; cols < ORDER2_COLS; cols++) {
//
//				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
//				if ((line = input.readLine()) != null) {
//
//					// �ォ��1�s�ȏ�̂Ƃ�
//					if (1 <= cols) {
//						strData = line.split(",");
//
////						System.out.print(cols+"�s��\t");
////						System.out.print(strData[0]);
////						System.out.println(strData[1] + "����");
//						timeTableData.setDayOfWeek(strData[0]);// �j��
//						timeTableData.setPeriod(Integer.parseInt(strData[1]));// ��
//						timeTableData.getClassOfGrade().setNumber(
//								Integer.parseInt(strData[2]));// �R�}��
//						timeTableData.setGrade(Integer.parseInt(strData[3]));// �w�N
//						timeTableData.getClassOfGrade().setPreviousOrLatter(
//								strData[4]);// �O�����
//						timeTableData.getClassOfGrade().setSubject(strData[5]);// �Ȗږ�
//						timeTableData.getClassOfGrade().setTeacher(strData[6]);// �S������
//						timeTableData.getClassOfGrade()
//								.setClassRoom(strData[7]);// ����
//
//						f_TimeTableData2.add(timeTableData);// 2���̎��Ԋ��̓��I�z��ɒǉ�
//
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}
//
//	/*
//	 * 3���̃t�@�C����ǂݍ���
//	 */
//	private void readFile3() {
//
//		String[] strData = new String[ORDER3_DATA];
//
//		BufferedReader input = FileIO.readFile(FILE3_NAME);
//
//		TimeTable timeTableData = new TimeTable();
//
//		try {
//			String line = new String();
//
//			// ��ԏォ���ԉ��̍s�܂œǂݍ���
//			for (int cols = 0; cols < ORDER3_COLS; cols++) {
//
//				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
//				if ((line = input.readLine()) != null) {
//
//					// �ォ��1�s�ȏ�̂Ƃ�
//					if (1 <= cols) {
//						strData = line.split(",");
//
//						timeTableData.getClassOfGrade().setNumber(
//								Integer.parseInt(strData[0]));// �R�}��
//						timeTableData.setGrade(Integer.parseInt(strData[1]));// �w�N
//						timeTableData.getClassOfGrade().setPreviousOrLatter(
//								strData[2]);// �O�����
//						timeTableData.getClassOfGrade().setSubject(strData[3]);// �Ȗږ�
//						timeTableData.getClassOfGrade().setTeacher(strData[4]);// �S������
//
//						f_TimeTableData3.add(timeTableData);// 3���̎��Ԋ��̓��I�z��ɒǉ�
//					}
//				}
//			}
//			input.close();
//		} catch (IOException error_report) {
//			System.out.println(error_report);
//		}
//	}

	/*
	 * ���j���[
	 */
	public int menu() {
		int d = InOutPut.getMenu("which 1)���� 2)�v���O�������s 3)�I��", 1, 3);
		return d;
	}

	/*
	 * ������\��
	 */
	public void instruction() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("�F����");
		InOutPut.decoration(s.toString(), '-');

		System.out.println("��`�I�A���S���Y����p���Ď��Ԋ����쐬����");
	}

//	/*
//	 * �v���O���������s����
//	 */
//	private void executeProg() {
//		int count = 0;
//		PROG_COUNT++;
//		startProg();// �v���O�������s�J�n
//
//		do {
//			System.out.println("�񐔁F" + (count + 1) + "���");
//
//			// �v���O�������I�����Ƃ�
//			if (isFinishedProg()) {
//				break;
//			}
//			count++;
//		} while (true);
//		finishProg();// �v���O�������s�I��
//	}

	/*
	 * �v���O�������s�J�n
	 */
	protected void startProg() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("�F��");
		s.append(PROG_COUNT);
		s.append("��v���O�������s�J�n!");

		InOutPut.decoration(s.toString(), '-');
	}

//	/*
//	 * �v���O�������I��������
//	 */
//	private boolean isFinishedProg() {
//		readFile();// �t�@�C����ǂݍ���
//		result();// ���ʂ̕\��
//		return true;
//	}

	/*
	 * �v���O�������s�I��
	 */
	public void finishProg() {
		StringBuffer s = new StringBuffer(TITLE);
		s.append("�F��");
		s.append(PROG_COUNT);
		s.append("����s�I��!");

		InOutPut.decoration(s.toString(), '-');
		System.out.println("");
	}

	/*
	 * �I��
	 */
	public void finish() {
		writeResultFile();
		System.out.println("See you later !");
	}

//	/*
//	 * ���s���鏈��
//	 */
//	public int exe() {
//
//		for (;;) {
//			switch (menu()) {
//			case 1:
//				instruction();// ������\��
//				break;
//			case 2:
//				executeProg();// �v���O���������s
//				break;
//			case 3:
//				finish();// �v���O�������I��
//				return 0;
//			}
//		}
//	}
}
