package GeneticAlgo;

/*
 *  ���Ԋ��̃C���^�[�t�F�[�X
 *  @author Nakata
 */
public interface iTimeTable {

	public final boolean DEBUG = true;// �f�o�b�O���[�h

	public final int MAX_PERIOD = 5;// 5����
	public final int MAX_DAY = 6;// ���j����y�j��6����

	public final int ORDER1_COLS = 17;// 1���̃f�[�^�̍s
	public final int ORDER1_DATA = 8;// 1���̃f�[�^�̐�
	public final String FACULTY1_NAME="faculty_1.csv";// 1���̃t�@�C��(�S���҂����܂����t�@�C��)�̖��O
	public final String FILE1_NAME = "room_1.csv";// 1���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)�̖��O

	public final int ORDER2_COLS = 175;// 2���̃f�[�^�̍s
	public final int ORDER2_DATA = 8;// 2���̃f�[�^�̐�
	public final String FACULTY2_NAME="faculty_2.csv";// 2���̃t�@�C��(�S���҂����܂����t�@�C��)�̖��O
	public final String FILE2_NAME = "room_2.csv";// 2���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)�̖��O

	public final int ORDER3_COLS = 113;// 3���̃f�[�^�̍s
	public final int ORDER3_DATA = 5;// 3���̃f�[�^�̐�
	public final String FACULTY3_NAME = "faculty_3.csv";// 3���̃t�@�C��(�S���҂����܂����t�@�C��)�̖��O
	public final String FILE3_NAME="room_3.csv";// 3���̃t�@�C��(�����Ɖ��j���Ɖ����ڂ����܂����t�@�C��)�̖��O
}
