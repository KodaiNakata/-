package GeneticAlgo;

/*
 * 曜日と限目のインタフェース
 */
public interface iDayPeriod {
	public final int MAX_DAY = TimeTable.changeDayToValue("金");// 月曜から何曜日までか
	public final int MAX_PERIOD = 5;// 5限目
	public final int MAX_SEMESTER=ClassOfGrade.changeSemesterToValue("後期");
	public final int CANDIDATE_NUM = 3;// 候補の数
}
