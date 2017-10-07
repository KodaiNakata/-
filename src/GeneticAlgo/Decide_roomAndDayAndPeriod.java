package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * �����Ɖ��j���Ɖ����ڂ����߂邽�߂̃N���X
 * @author Nakata
 */
public class Decide_roomAndDayAndPeriod extends Decide_faculty {

	protected ArrayList<TimeTable> f_TimeTableData1 = new ArrayList<TimeTable>();// 1���̎��Ԋ��̃f�[�^
	protected ArrayList<TimeTable> f_TimeTableData2 = new ArrayList<TimeTable>();// 2���̎��Ԋ��̃f�[�^
	protected ArrayList<TimeTable> f_TimeTableData3 = new ArrayList<TimeTable>();// 3���̎��Ԋ��̃f�[�^

	/*
	 * �R���X�g���N�^
	 */
	public Decide_roomAndDayAndPeriod() {
		super();
	}

	/*
	 * 3���̎��Ԋ��̍쐬
	 */
	private void makeTimeTable3(){

	}

	/*
	 * �v���O���������s����
	 */
	private void executeProg() {
		int count = 0;
		PROG_COUNT++;
		super.startProg();// �v���O�������s�J�n

		do {
			System.out.println("�񐔁F" + (count + 1) + "���");

			// �v���O�������I�����Ƃ�
			if (isFinishedProg()) {
				break;
			}
			count++;
		} while (true);
		super.finishProg();// �v���O�������s�I��
	}

	/*
	 * �v���O�������I��������
	 *
	 * @return true:�I��
	 */
	private boolean isFinishedProg() {
		readFacultyFile();// �S���҂����܂����t�@�C����ǂݍ���(3���̃t�@�C��)
		readRoomDayPeriodFile();// �����Ɖ��j���Ɖ����ڂ����܂����t�@�C����ǂݍ���(1����2���̃t�@�C��)
		makeTimeTable3();// 3���̎��Ԋ����쐬
		return true;
	}

	/*
	 * ���s���鏈��
	 *
	 * @return 0:�I��
	 */
	public int exe() {

		for (;;) {
			switch (super.menu()) {
			case 1:
				super.instruction();// ����
				break;
			case 2:
				executeProg();// �v���O���������s
				break;
			case 3:
				super.finish();// �I��
				return 0;
			}
		}
	}
	// --------------------------------//
	// ----------�t�@�C���֌W----------//
	// --------------------------------//
	/*
	 * �����Ɖ��j���Ɖ����ڂ����܂����t�@�C������������
	 */
	public void writeRoomAndDayAndPeriodFile() {
		// writeRoomAndDayAndPeriodFile1();// 1���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)����������
		// writeRoomAndDayAndPeriodFile2();// 2���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)����������
		writeRoomAndDayAndPeriodFile3();// 3���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)����������
	}

	/*
	 * 1���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����f�[�^)����������
	 */
	private void writeRoomAndDayAndPeriodFile1() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE1_NAME, false);
		//
		// System.out.println("�S���҂����܂���3���̃t�@�C��" + FACULTY3_NAME + "�ɏ������݂܂��B");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �j��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �R�}��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �w�N
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �O�����
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �Ȗږ�
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �S������
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// ����
		// output.println();
		// }
	}

	/*
	 * 2���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����f�[�^)����������
	 */
	private void writeRoomAndDayAndPeriodFile2() {

		// PrintWriter output;
		// output = FileIO.writeFile(FILE2_NAME, false);
		//
		// System.out.println("�S���҂����܂���3���̃t�@�C��" + FACULTY3_NAME + "�ɏ������݂܂��B");
		//
		// for(int number=0;number<f_TimeTableData3.size();number++){
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �j��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �R�}��
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �w�N
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �O�����
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �Ȗږ�
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// �S������
		// output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");//
		// ����
		// output.println();
		// }
	}

	/*
	 * 3���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����f�[�^)����������
	 */
	private void writeRoomAndDayAndPeriodFile3() {

		PrintWriter output;
		output = FileIO.writeFile(FILE3_NAME, false);

		System.out.println("�S���҂����܂���3���̃t�@�C��" + FACULTY3_NAME + "�ɏ������݂܂��B");

		for (int number = 0; number < f_TimeTableData3.size(); number++) {
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �j��
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ��
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �R�}��
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �w�N
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �O�����
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �Ȗږ�
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// �S������
			output.print(f_TimeTableData3.get(number).getDayOfWeeks() + ",");// ����
			output.println();
		}
	}

	/*
	 * �����Ɖ��j���Ɖ����ڂ����܂����t�@�C����ǂݍ���
	 */
	private void readRoomDayPeriodFile() {
		readRoomDayPeriodFile1();// 1���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
		readRoomDayPeriodFile2();// 2���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
		// readRoomDayPeriodFile3();// 3���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
	}

	/*
	 * 1���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
	 */
	private void readRoomDayPeriodFile1() {

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
									.parseInt(strData[1]));// ����
							timeTableData.getClassOfGrade().setNumber(
									Integer.parseInt(strData[2]));// �R�}��
							timeTableData.getClassOfGrade().setGrade(
									Integer.parseInt(strData[3]));// �w�N
							timeTableData.getClassOfGrade()
									.setPreviousOrLatter(strData[4]);// �O�����
							timeTableData.getClassOfGrade().setSubject(
									strData[5]);// �Ȗږ�
							timeTableData.getClassOfGrade().setTeacher(
									strData[6]);// �S������
							timeTableData.setClassRoom(strData[7]);// ����

							f_TimeTableData1.add(timeTableData);// 1���̎��Ԋ��̓��I�z��ɒǉ�
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
	 * 2���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
	 */
	private void readRoomDayPeriodFile2() {

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

						timeTableData.setDayOfWeek(strData[0]);// �j��
						timeTableData.setPeriod(Integer.parseInt(strData[1]));// ����
						timeTableData.getClassOfGrade().setNumber(
								Integer.parseInt(strData[2]));// �R�}��
						timeTableData.getClassOfGrade().setGrade(
								Integer.parseInt(strData[3]));// �w�N
						timeTableData.getClassOfGrade().setPreviousOrLatter(
								strData[4]);// �O�����
						timeTableData.getClassOfGrade().setSubject(strData[5]);// �Ȗږ�
						timeTableData.getClassOfGrade().setTeacher(strData[6]);// �S������
						timeTableData.setClassRoom(strData[7]);// ����

						f_TimeTableData2.add(timeTableData);// 2���̎��Ԋ��̓��I�z��ɒǉ�

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	// /*
	// * 3���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)��ǂݍ���
	// */
	// private void readRoomDayPeriodFile3() {
	//
	// String[] strData = new String[ORDER3_DATA];
	//
	// BufferedReader input = FileIO.readFile(FACULTY3_NAME);
	//
	// ClassOfGrade classOfGradeData = new ClassOfGrade();
	//
	// try {
	// String line = new String();
	//
	// // ��ԏォ���ԉ��̍s�܂œǂݍ���
	// for (int cols = 0; cols < ORDER3_COLS; cols++) {
	//
	// // �ǂݍ���1�s���󔒂łȂ��Ƃ�
	// if ((line = input.readLine()) != null) {
	//
	// // �ォ��1�s�ȏ�̂Ƃ�
	// if (1 <= cols) {
	// strData = line.split(",");
	//
	// classOfGradeData
	// .setNumber(Integer.parseInt(strData[0]));// �R�}��
	// classOfGradeData.setGrade(Integer.parseInt(strData[1]));// �w�N
	// classOfGradeData.setPreviousOrLatter(strData[2]);// �O�����
	// classOfGradeData.setSubject(strData[3]);// �Ȗږ�
	// classOfGradeData.setTeacher(strData[4]);// �S������
	//
	// f_ClassOfGradeData3.add(classOfGradeData);// 3���̎��Ԋ��̓��I�z��ɒǉ�
	// }
	// }
	// }
	// input.close();
	// } catch (IOException error_report) {
	// System.out.println(error_report);
	// }
	// }

	/*
	 * �S���҂����܂����t�@�C����ǂݍ���
	 */
	private void readFacultyFile() {
		// readFacultyFile1();
		// readFacultyFile2();
		readFacultyFile3();// 3���̃t�@�C��(�S���Ҍ���̃t�@�C��)��ǂݍ���
	}

	/*
	 * 1���̃t�@�C��(�S���Ҍ���̃t�@�C��)��ǂݍ���
	 */
	private void readFacultyFile1() {

		String[] strData = new String[ORDER1_DATA];

		BufferedReader input = FileIO.readFile(FACULTY1_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

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
							classOfGradeData.setNumber(Integer
									.parseInt(strData[0]));// �R�}��
							classOfGradeData.setGrade(Integer
									.parseInt(strData[1]));// �w�N
							classOfGradeData.setPreviousOrLatter(strData[2]);// �O�����
							classOfGradeData.setSubject(strData[3]);// �Ȗږ�
							classOfGradeData.setTeacher(strData[4]);// �S������

							f_ClassOfGradeData1.add(classOfGradeData);// 1���̎��Ԋ��̓��I�z��ɒǉ�
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
	 * 2���̃t�@�C��(�S���Ҍ���̃t�@�C��)��ǂݍ���
	 */
	private void readFacultyFile2() {

		String[] strData = new String[ORDER2_DATA];

		BufferedReader input = FileIO.readFile(FACULTY2_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

		try {
			String line = new String();

			// ��ԏォ���ԉ��̍s�܂œǂݍ���
			for (int cols = 0; cols < ORDER2_COLS; cols++) {

				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
				if ((line = input.readLine()) != null) {

					// �ォ��1�s�ȏ�̂Ƃ�
					if (1 <= cols) {
						strData = line.split(",");

						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// �R�}��
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// �w�N
						classOfGradeData.setPreviousOrLatter(strData[2]);// �O�����
						classOfGradeData.setSubject(strData[3]);// �Ȗږ�
						classOfGradeData.setTeacher(strData[4]);// �S������

						f_ClassOfGradeData2.add(classOfGradeData);// 2���̎��Ԋ��̓��I�z��ɒǉ�

					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}

	/*
	 * 3���̃t�@�C��(�S���Ҍ���̃t�@�C��)��ǂݍ���
	 */
	private void readFacultyFile3() {

		String[] strData = new String[ORDER3_DATA];

		BufferedReader input = FileIO.readFile(FACULTY3_NAME);

		ClassOfGrade classOfGradeData = new ClassOfGrade();

		try {
			String line = new String();

			// ��ԏォ���ԉ��̍s�܂œǂݍ���
			for (int cols = 0; cols < ORDER3_COLS; cols++) {

				// �ǂݍ���1�s���󔒂łȂ��Ƃ�
				if ((line = input.readLine()) != null) {

					// �ォ��1�s�ȏ�̂Ƃ�
					if (1 <= cols) {
						strData = line.split(",");

						classOfGradeData
								.setNumber(Integer.parseInt(strData[0]));// �R�}��
						classOfGradeData.setGrade(Integer.parseInt(strData[1]));// �w�N
						classOfGradeData.setPreviousOrLatter(strData[2]);// �O�����
						classOfGradeData.setSubject(strData[3]);// �Ȗږ�
						classOfGradeData.setTeacher(strData[4]);// �S������

						f_ClassOfGradeData3.add(classOfGradeData);// 3���̊w�N���Ƃ̎��Ƃ̓��I�z��ɒǉ�
					}
				}
			}
			input.close();
		} catch (IOException error_report) {
			System.out.println(error_report);
		}
	}
}
