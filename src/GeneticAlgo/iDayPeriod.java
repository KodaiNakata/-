package GeneticAlgo;

/*
 * 曜日と限目のインタフェース
 */
public interface iDayPeriod {
	public final int MAX_DAY = TimeTable.changeDayToValue("金");// 月曜から何曜日までか
	public final int MAX_PERIOD = 5;// 5限目
	
}
