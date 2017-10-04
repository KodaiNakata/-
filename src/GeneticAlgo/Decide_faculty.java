package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

// ���Ԋ������߂邽�߂̃N���X
public class Decide_faculty implements iTimeTable {

	private static StringBuffer TITLE = new StringBuffer("��`�I�A���S���Y����p�������Ԋ��쐬");
	private TimeTable[][] f_PreviousTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// �O���̎��Ԋ�
	private TimeTable[][] f_LatterTimeTables = new TimeTable[MAX_DAY][MAX_PERIOD];// ����̎��Ԋ�
	private static final String RESULT_FILE_NAME = "result.csv";// ���Ԋ��쐬�̌��ʂ̃t�@�C���̖��O
	// private ArrayList
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

	// ------------------------------------------//
	// -------------�t�@�C���֌W----------------//
	// ------------------------------------------//
	/*
	 * ���ʂ̃t�@�C������������
	 */
	private void writeResultFile() {
		PrintWriter output;
		output = FileIO.writeFile(RESULT_FILE_NAME, false);

		System.out.println("�v���O�������I�����܂����B" + RESULT_FILE_NAME + "�ɏ������݂܂��B");

		// �O���̎��Ԋ��쐬�̌��ʂ�\��
		for (int day = 0; day < MAX_DAY; day++) {
			for (int period = 0; period < MAX_PERIOD; period++) {

				for (int number = 0; number < f_PreviousTimeTables[day][period]
						.getClassOfGrade().getCoursesOrClassesSize(); number++) {
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getCoursesOrClasses(number)
							+ ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getSubjects(number) + ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getTeacher(number) + ",");
					output.print(f_PreviousTimeTables[day][period]
							.getClassOfGrade().getClassRooms(number) + ",");
				}
			}
			output.println();
		}
		output.close();
		System.out.println("�������݂��I�����܂����B");
	}

	/*
	 * �t�@�C����ǂݍ���
	 */
	public void readFile() {

		readFile1();// 1���̃t�@�C����ǂݍ���

		readFile2();// 2���̃t�@�C����ǂݍ���

		readFile3();// 3���̃t�@�C����ǂݍ���
	}

	/*
	 * 1���̃t�@�C����ǂݍ���
	 */
	private void readFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FILE1_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// ��ԏォ���ԉ��̍s�܂œǂݍ���
			for (int cols = 0; cols < ORDER1_COLS; cols++) {

				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
				if ((line = input.readLine()) != null) {

					// �ォ��1�s�ȏ�̂Ƃ�
					if (1 <= cols) {
						strData = line.split(",");

						// �j�����󔒂łȂ��Ƃ�
						if (strData[0] != null) {
							timeTableData.setDayOfWeek(strData[0]);// �j��
							timeTableData.setPeriod(Integer
									.parseInt(strData[1]));// ��
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[2]));// �R�}��
							timeTableData
									.setGrade(Integer.parseInt(strData[3]));// �w�N
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// �O�����
							timeTableData.getClassOfGrade().addSubjects(
									strData[5]);// �Ȗږ�
							timeTableData.getClassOfGrade().addTeacher(
									strData[6]);// �S������
							timeTableData.getClassOfGrade().addClassRooms(
									strData[7]);// ����
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 2���̃t�@�C����ǂݍ���
	 */
	private void readFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FILE2_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// ��ԏォ���ԉ��̍s�܂œǂݍ���
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
				if ((line = input.readLine()) != null) {

					// �ォ��1�s�ȏ�̂Ƃ�
					if (1 <= cols) {
						strData = line.split(",");

						// �j�����󔒂łȂ��Ƃ�
						if (strData[0] != null) {
							timeTableData.setDayOfWeek(strData[0]);// �j��
							timeTableData.setPeriod(Integer
									.parseInt(strData[1]));// ��
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[2]));// �R�}��
							timeTableData
									.setGrade(Integer.parseInt(strData[3]));// �w�N
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// �O�����
							timeTableData.getClassOfGrade().addSubjects(
									strData[5]);// �Ȗږ�
							timeTableData.getClassOfGrade().addTeacher(
									strData[6]);// �S������
							timeTableData.getClassOfGrade().addClassRooms(
									strData[7]);// ����
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 3���̃t�@�C����ǂݍ���
	 */
	private void readFile3() {

		String[] strData = new String[ORDER3_DATA];

		BufferedReader input = FileIO.readFile(FILE3_NAME);

		TimeTable timeTableData = new TimeTable();

		try {
			String line = new String();

			// ��ԏォ���ԉ��̍s�܂œǂݍ���
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
				if ((line = input.readLine()) != null) {

					// �ォ��1�s�ȏ�̂Ƃ�
					if (1 <= cols) {
						strData = line.split(",");

						// �R�}�����󔒂łȂ��Ƃ�
						if (strData[0] != null) {
							timeTableData.getClassOfGrade().addNumbers(
									Integer.parseInt(strData[0]));// �R�}��
							timeTableData
									.setGrade(Integer.parseInt(strData[1]));// �w�N
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[2]);// �O�����
							timeTableData.getClassOfGrade().addSubjects(
									strData[3]);// �Ȗږ�
							timeTableData.getClassOfGrade().addTeacher(
									strData[4]);// �S������
						}
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

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

	/*
	 * �v���O���������s����
	 */
	private void executeProg() {
		int count = 0;
		PROG_COUNT++;
		startProg();// �v���O�������s�J�n

		do {
			System.out.println("�񐔁F" + (count + 1) + "���");

			// �v���O�������I�����Ƃ�
			if (isFinishedProg()) {
				break;
			}
			count++;
		} while (true);
		finishProg();// �v���O�������s�I��
	}

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

	/*
	 * �v���O�������I��������
	 */
	private boolean isFinishedProg() {
		readFile();// �t�@�C����ǂݍ���
		// result();// ���ʂ̕\��
		return true;
	}

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

	/*
	 * ���s���鏈��
	 */
	public int exe() {

		for (;;) {
			switch (menu()) {
			case 1:
				instruction();// ������\��
				break;
			case 2:
				executeProg();// �v���O���������s
				break;
			case 3:
				finish();// �v���O�������I��
				return 0;
			}
		}
	}
}
