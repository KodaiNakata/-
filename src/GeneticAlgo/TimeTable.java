package GeneticAlgo;


/*
 *  時間割のクラス
 *  @author Nakata
 */
public class TimeTable {

	private String f_DayOfWeek;// 曜日
	private int f_Period;// 限目
	// private int f_Grade;// 学年
	private ClassOfGrade f_ClassOfGrade;// 学年ごとの授業
	private String f_ClassRoom;// 教室

	/*
	 * コンストラクタ
	 */
	public TimeTable() {
		this.f_DayOfWeek = "不明";
		this.f_Period = 0;
		// f_Grade = 0;
		this.f_ClassOfGrade = new ClassOfGrade();
		this.f_ClassRoom = "不明";
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
	public void setTimeTable(String day_of_week, int period,
			ClassOfGrade class_of_grade) {
		this.f_DayOfWeek = day_of_week;
		this.f_Period = period;
		this.f_ClassOfGrade = class_of_grade;
	}

//	/*
//	 * 時間割の交換
//	 */
//	public static void exchangeTimeTable(TimeTable[] time_table1,
//			TimeTable[] time_table2) {
//		
//		TimeTable tmpTimeTable = new TimeTable();
//
//		tmpTimeTable.setTimeTable(time_table1[0].getDayOfWeek(),
//				time_table1[0].getPeriod(), time_table1[0].getClassOfGrade(),
//				time_table1[0].getClassRoom());
//
//		time_table1[0].setTimeTable(time_table2[0].getDayOfWeek(),
//				time_table2[0].getPeriod(), time_table2[0].getClassOfGrade(),
//				time_table2[0].getClassRoom());
//
//		time_table2[0].setTimeTable(tmpTimeTable.getDayOfWeek(),
//				tmpTimeTable.getPeriod(), tmpTimeTable.getClassOfGrade(),
//				tmpTimeTable.getClassRoom());
//	}

	/*
	 * 曜日を数値に変える
	 * 
	 * @param day_of_week 曜日
	 * 
	 * @return 曜日を数値に変えたもの
	 */
	public static int changeDayToValue(String day_of_week) {

		if(day_of_week==null){
			day_of_week="不明";
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

	/*
	 * 教室を数値に変える
	 * 
	 * @param class_room 教室
	 * 
	 * @return 教室を数値に変えたもの
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
	 * 数値を教室に変える
	 * 
	 * @param value 数値
	 * 
	 * @return 数値を教室に変えたもの
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
		return "不明";
	}

	// -------------------------------//
	// ------------ゲッター-----------//
	// -------------------------------//
	/*
	 * 曜日のゲッター
	 * 
	 * @return 曜日
	 */
	public String getDayOfWeek() {
		return this.f_DayOfWeek;
	}

	/*
	 * 限目のゲッター
	 * 
	 * @return 限目
	 */
	public int getPeriod() {
		return this.f_Period;
	}

	// /*
	// * 学年のゲッター
	// */
	// public int getGrade() {
	// return f_Grade;
	// }

	/*
	 * 学年ごとの授業のゲッター
	 * 
	 * @return 学年ごとの授業
	 */
	public ClassOfGrade getClassOfGrade() {
		return this.f_ClassOfGrade;
	}

	/*
	 * 教室のゲッター
	 * 
	 * @return 教室
	 */
	public String getClassRoom() {
		return this.f_ClassRoom;
	}

	// -------------------------------//
	// ------------セッター-----------//
	// -------------------------------//
	/*
	 * 曜日のセッター
	 * 
	 * @param dayOfWeek 曜日
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.f_DayOfWeek = dayOfWeek;
	}

	/*
	 * 限目のセッター
	 * 
	 * @param period 限目
	 */
	public void setPeriod(int period) {
		this.f_Period = period;
	}

	// /*
	// * 学年のセッター
	// *
	// * @param grade 学年
	// */
	// public void setGrade(int grade) {
	// f_Grade = grade;
	// }

	/*
	 * 学年ごとの授業のセッター
	 * 
	 * @param class_of_grade 学年ごとの授業
	 */
	public void setClassOfGrade(ClassOfGrade class_of_grade) {
		this.f_ClassOfGrade = class_of_grade;
	}

	/*
	 * 教室のセッター
	 * 
	 * @param class_room 教室
	 */
	public void setClassRoom(String class_room) {
		this.f_ClassRoom = class_room;
	}
}
