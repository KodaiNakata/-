package GeneticAlgo;

/*
 *  時間割のクラス
 *  @author Nakata
 */
public class TimeTable implements iDayPeriod{

	private String f_FixedDayOfWeek;// 固定の曜日
	private int f_FixedPeriod;// 固定の限目
	private String[] f_DayOfWeek=new String[CANDIDATE_NUM];
	private int[] f_Period=new int[CANDIDATE_NUM];
	private ClassOfGrade f_ClassOfGrade;// 学年ごとの授業
	private String f_ClassRoom;// 教室

	/*
	 * コンストラクタ
	 */
	public TimeTable() {
		f_FixedDayOfWeek = "不明";
		f_FixedPeriod = 0;
		
		for(int candidate=0;candidate<f_DayOfWeek.length;candidate++) {
			f_DayOfWeek[candidate]="不明";
			f_Period[candidate]=0;
		}
		
		f_ClassOfGrade = new ClassOfGrade();
		f_ClassRoom = "不明";
	}

	/*
	 * コピーコンストラクタ
	 *
	 * @param time_table コピー元の時間割
	 */
	public TimeTable(TimeTable time_table) {
		f_FixedDayOfWeek = time_table.getFixedDayOfWeek();
		f_FixedPeriod = time_table.getFixedPeriod();
		
		for(int candidate=0;candidate<f_DayOfWeek.length;candidate++) {
			f_DayOfWeek[candidate]=time_table.getDayOfWeek(candidate);
			f_Period[candidate]=time_table.getPeriod(candidate);
		}
		
		f_ClassOfGrade = time_table.f_ClassOfGrade;
		f_ClassRoom = time_table.f_ClassRoom;
	}
	/*
	 * 時間割のクラスの全フィールドに設定する
	 *
	 * @param day_of_week 曜日
	 *
	 * @param period 限目
	 *
	 * @param class_of_grade 学年ごとの授業
	 *
	 * @param class_room 教室
	 */
	public void setTimeTable(String day_of_week, int period, ClassOfGrade class_of_grade) {
		f_FixedDayOfWeek = day_of_week;
		f_FixedPeriod = period;
		f_ClassOfGrade = class_of_grade;
	}

	public void resetDayPeriod(int candidate) {
		
		f_DayOfWeek[candidate]="不明";
		f_Period[candidate]=0;
	}
	/*
	 * 曜日を数値に変える
	 *
	 * @param day_of_week 曜日
	 *
	 * @return 曜日を数値に変えたもの
	 */
	public static int changeDayToValue(String day_of_week) {

		if (day_of_week == null) {
			day_of_week = "不明";
		}

		switch (day_of_week) {

		case "月":
			return 0;

		case "火":
			return 1;

		case "水":
			return 2;

		case "木":
			return 3;

		case "金":
			return 4;

		case "土":
			return 5;

		default:
			return -1;
		}
	}

	/*
	 * 値を曜日に変える
	 *
	 * @param value 値
	 *
	 * @return 値を曜日に変えたもの
	 */
	public static String changeValueToDay(int value) {

		switch (value) {

		case 0:
			return "月";

		case 1:
			return "火";

		case 2:
			return "水";

		case 3:
			return "木";

		case 4:
			return "金";

		case 5:
			return "土";
		}
		return "不明";
	}

//	/*
//	 * 教室を数値に変える
//	 *
//	 * @param class_room 教室
//	 *
//	 * @return 教室を数値に変えたもの
//	 */
//	public static int changeRoomToValue(String class_room) {
//
//		switch (class_room) {
//		case "31-202":
//			return 0;
//		case "31-301":
//			return 1;
//		case "31-302":
//			return 2;
//		case "31-401":
//			return 3;
//		case "31-402":
//			return 4;
//		case "31-501":
//			return 5;
//		case "31-502":
//			return 6;
//		case "31-503":
//			return 7;
//		case "31-505":
//			return 8;
//		case "31-506":
//			return 9;
//		case "31-601":
//			return 10;
//		case "31-602":
//			return 11;
//		case "31-603":
//			return 12;
//		case "31-604":
//			return 13;
//		case "31-801":
//			return 14;
//		case "31-802":
//			return 15;
//		case "31-803":
//			return 16;
//		}
//		return -1;
//	}
//
//	/*
//	 * 数値を教室に変える
//	 *
//	 * @param value 数値
//	 *
//	 * @return 数値を教室に変えたもの
//	 */
//	public static String changeValueToRoom(int value) {
//
//		switch (value) {
//		case 0:
//			return "31-202";
//		case 1:
//			return "31-301";
//		case 2:
//			return "31-302";
//		case 3:
//			return "31-401";
//		case 4:
//			return "31-402";
//		case 5:
//			return "31-501";
//		case 6:
//			return "31-502";
//		case 7:
//			return "31-503";
//		case 8:
//			return "31-505";
//		case 9:
//			return "31-506";
//		case 10:
//			return "31-601";
//		case 11:
//			return "31-602";
//		case 12:
//			return "31-603";
//		case 13:
//			return "31-604";
//		case 14:
//			return "31-801";
//		case 15:
//			return "31-802";
//		case 16:
//			return "31-803";
//		}
//		return "不明";
//	}

	// -------------------------------//
	// ------------ゲッター-----------//
	// -------------------------------//
	/*
	 * 固定の曜日のゲッター
	 *
	 * @return 固定の曜日
	 */
	public String getFixedDayOfWeek() {
		return f_FixedDayOfWeek;
	}

	/*
	 * 曜日の配列のゲッター
	 *
	 * @return 曜日の配列
	 */
	public String[] getArrayOfDayOfWeek() {
		return f_DayOfWeek;
	}

	/*
	 * 指定した候補の曜日のゲッター
	 *
	 * @param candidate 候補番号
	 *
	 * @return 指定した候補の曜日
	 */
	public String getDayOfWeek(int candidate) {
		return f_DayOfWeek[candidate];
	}

	/*
	 * 固定の限目のゲッター
	 *
	 * @return 固定の限目
	 */
	public int getFixedPeriod() {
		return f_FixedPeriod;
	}

	/*
	 * 限目の配列のゲッター
	 *
	 * @return 限目の配列
	 */
	public int[] getArrayOfPeriod() {
		return f_Period;
	}

	/*
	 * 指定した候補の限目のゲッター
	 *
	 * @param candidate 候補番号
	 *
	 * @return 指定した候補の限目
	 */
	public int getPeriod(int candidate) {
		return f_Period[candidate];
	}

	/*
	 * 学年ごとの授業のゲッター
	 *
	 * @return 学年ごとの授業
	 */
	public ClassOfGrade getClassOfGrade() {
		return f_ClassOfGrade;
	}

	/*
	 * 教室のゲッター
	 *
	 * @return 教室
	 */
	public String getClassRoom() {
		return f_ClassRoom;
	}

	// -------------------------------//
	// ------------セッター-----------//
	// -------------------------------//
	/*
	 * 固定の曜日のセッター
	 *
	 * @param dayOfWeek 曜日
	 */
	public void setFixedDayOfWeek(String dayOfWeek) {

		if ("不明".equals(f_FixedDayOfWeek)) {
			f_FixedDayOfWeek = dayOfWeek;
		}

		else {
			f_FixedDayOfWeek = f_FixedDayOfWeek.replaceAll(f_FixedDayOfWeek, dayOfWeek);
		}
	}

	/*
	 * 曜日のセッター
	 *
	 * @param candidate 候補番号
	 *
	 * @param array_day_of_week[] 曜日の配列
	 *
	 * @param day_of_week 曜日
	 */
	public void setDayOfWeek(int candidate,String[] array_day_of_week,String day_of_week) {
		array_day_of_week[candidate]=day_of_week;
	}

	/*
	 * 固定の限目のセッター
	 *
	 * @param period 限目
	 */
	public void setFixedPeriod(int period) {
		f_FixedPeriod = period;
	}

	/*
	 * 限目のセッター
	 *
	 * @param candidate 候補番号
	 *
	 * @param array_period 限目の配列
	 *
	 * @param period 限目
	 */
	public void setPeriod(int candidate,int[] array_period,int period) {
		array_period[candidate]=period;
	}

	/*
	 * 学年ごとの授業のセッター
	 *
	 * @param class_of_grade 学年ごとの授業
	 */
	public void setClassOfGrade(ClassOfGrade class_of_grade) {
		f_ClassOfGrade = class_of_grade;
	}

	/*
	 * 教室のセッター
	 *
	 * @param class_room 教室
	 */
	public void setClassRoom(String class_room) {
		f_ClassRoom = class_room;
	}
}
