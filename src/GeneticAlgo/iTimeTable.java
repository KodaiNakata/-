package GeneticAlgo;

/*
 *  時間割のインターフェース
 *  @author Nakata
 */
public interface iTimeTable {

	public final boolean DEBUG = true;// デバッグモード

	public final int MAX_PERIOD = 5;// 5限目
	public final int MAX_DAY = 6;// 月曜から土曜の6日間

	public final int ORDER1_COLS = 17;// 1次のデータの行
	public final int ORDER1_DATA = 8;// 1次のデータの数
	public final String FACULTY1_NAME="faculty_1.csv";// 1次のファイル(担当者が決まったファイル)の名前
	public final String FILE1_NAME = "room_1.csv";// 1次のファイル(教室と何曜日と何限目が決まったファイル)の名前

	public final int ORDER2_COLS = 175;// 2次のデータの行
	public final int ORDER2_DATA = 8;// 2次のデータの数
	public final String FACULTY2_NAME="faculty_2.csv";// 2次のファイル(担当者が決まったファイル)の名前
	public final String FILE2_NAME = "room_2.csv";// 2次のファイル(教室と何曜日と何限目が決まったファイル)の名前

	public final int ORDER3_COLS = 113;// 3次のデータの行
	public final int ORDER3_DATA = 6;// 3次のデータの数
	public final String FACULTY3_NAME = "faculty_3.csv";// 3次のファイル(担当者が決まったファイル)の名前
	public final String FILE3_NAME="room_3.csv";// 3次のファイル(教室と何曜日と何限目が決まったファイル)の名前
}
