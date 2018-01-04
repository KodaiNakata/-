package GeneticAlgo;

/*
 *  時間割のインターフェース
 *  @author Nakata
 */
public interface iTimeTable {

	public final boolean DEBUG = true;// デバッグモード

	public final int MAX_GRADE=4;// 最高学年
	public final String CONFERENCE_DAY = "木";// 会議の曜日
	public final int CONFERENCE_PERIOD = 3;// 会議の限目

	public final int CHECK_NUM = 100000;// チェックする回数
	public final int MUTATION_PROBABLITY = 1;// 突然変異率

	public final int TEACHER_COLS = 43;// 先生のファイルの行
	public final int TEACHER_DATA = 14;// 先生のファイルのデータの数
	public final String TEACHER_NAME = "EleTeacher.csv";// 先生のファイルの名前

	public final int ORDER1_COLS = 17;// 1次のデータの行
	public final int ORDER1_DATA = 9;// 1次のデータの数
	public final String FACULTY1_NAME = "faculty_1.csv";// 1次のファイル(担当者が決まったファイル)の名前
	public final String FILE1_NAME = "room_1.csv";// 1次のファイル(教室と何曜日と何限目が決まったファイル)の名前

	public final int ORDER2_COLS = 175;// 2次のデータの行
	public final int ORDER2_DATA = 9;// 2次のデータの数
	public final String FACULTY2_NAME = "faculty_2.csv";// 2次のファイル(担当者が決まったファイル)の名前
	public final String FILE2_NAME = "room_2.csv";// 2次のファイル(教室と何曜日と何限目が決まったファイル)の名前

	public final int ORDER3_COLS = 112;// 3次のデータの行
	public final int ORDER3_DATA = 8;// 3次のデータの数
	public final String SUBJECT3_NAME = "subject_3.csv";// 3次の教科のファイルの名前
	public final String FACULTY3_NAME = "faculty_3.csv";// 3次のファイル(担当者が決まったファイル)の名前
	public final String FILE3_NAME = "room_3.csv";// 3次のファイル(教室と何曜日と何限目が決まったファイル)の名前

	public final String FIRST_FILE3_NAME = "roomFirst_3.csv";// 最初の3次のファイル(教室と何曜日と何限目が決まったファイル)の名前
	
	public final String TIME_TABLE_PATH="C:\\Users\\nakata\\Documents\\TimeTableFile\\";// 時間割のパス
}
