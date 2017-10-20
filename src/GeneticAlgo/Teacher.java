package GeneticAlgo;

/*
 *  先生のクラス
 *  @author Nakata
 */
public class Teacher {

	private String f_Name;// 氏名
	private int f_NumOfBasicSeminar1;// 基礎ゼミ1を持っているか
	private int f_NumOfBasicSeminar2;// 基礎ゼミ2を持っているか
	private int f_HasEngineeringDesignExperience;// エンジニアリングデザイン実験を持っているか
	private String f_ThemeOfElectricElectronicTraining;// 電気電子工学実習の何のテーマか
	private int f_NumOfElectricElectronicTraining;// 電気電子工学実習のコマ数
	private String f_ThemeOfElectricElectronicExperiment;// 電気電子工学実験の何のテーマか
	private int f_NumOfElectricElectronicExperiment;// 電気電子工学実験のコマ数
	private String f_ThemeOfEleInfoCommunicationExperiment;// エレ情実験の何のテーマか
	private String f_ThemeOfEnergyEnvironmentExperiment;// エネ環実験の何のテーマか
	private int f_NumOfGraduateStudySeminar;// 卒研ゼミのコマ数
	private double f_FacultyEvaluationValue;// 担当者決めに対する評価値
	private double f_TimeTableEvaluationValue;// 時間割に対する評価値

	/*
	 * コンストラクタ nullまたは0にする
	 */
	public Teacher() {
		f_Name = null;
		f_NumOfBasicSeminar1 = 0;
		f_NumOfBasicSeminar2 = 0;
		f_HasEngineeringDesignExperience = 0;
		f_ThemeOfElectricElectronicTraining = null;
		f_NumOfElectricElectronicTraining = 0;
		f_ThemeOfElectricElectronicExperiment = null;
		f_NumOfElectricElectronicExperiment = 0;
		f_ThemeOfEleInfoCommunicationExperiment = null;
		f_ThemeOfEnergyEnvironmentExperiment = null;
		f_NumOfGraduateStudySeminar = 0;
		f_TimeTableEvaluationValue=0.0;
	}

	// ---------------------------------//
	// -------------ゲッター------------//
	// ---------------------------------//
	/*
	 * 氏名のゲッター
	 *
	 * @return 氏名
	 */
	public String getName() {
		return f_Name;
	}

	/*
	 * 基礎ゼミ1のコマ数のゲッター
	 *
	 * @return 基礎ゼミ1のコマ数
	 */
	public int getNumOfBasicSeminar1() {
		return f_NumOfBasicSeminar1;
	}

	/*
	 * 時間割に対する評価値のゲッター
	 *
	 * @return 時間割に対する評価値
	 */
	public double getTimeTableEvaluationValue(){
		return f_TimeTableEvaluationValue;
	}
	// ---------------------------------//
	// -------------セッター------------//
	// ---------------------------------//
	/*
	 * 氏名のセッター
	 *
	 * @param name 氏名
	 */
	public void setName(String name) {
		f_Name = name;
	}

	/*
	 * 基礎ゼミ1のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfBasicSeminar1(int num) {
		f_NumOfBasicSeminar1 = num;
	}

	/*
	 * 時間割に対する評価値のセッター
	 *
	 * @param value 時間割に対する評価値
	 */
	public void setTimeTableEvaluationValue(double value){
		f_TimeTableEvaluationValue=value;
	}
}
