package GeneticAlgo;

import java.util.ArrayList;

/*
 *  先生のクラス
 *  @author Nakata
 */
public class Teacher implements iDayPeriod {

	private String f_Name;// 氏名
	private int f_NumOfBasicSeminar1;// 基礎ゼミ1を持っているか
	private int f_NumOfBasicSeminar2;// 基礎ゼミ2を持っているか
	private int f_NumOfOddElectricElectronicTraining;// 電気電子工学実習(奇数)のコマ数
	private int f_NumOfEvenElectricElectronicTraining;// 電気電子工学実習(偶数)のコマ数
	private int f_NumOfOddElectricElectronicExperiment;// 電気電子工学実験(奇数)のコマ数
	private int f_NumOfEvenElectricElectronicExperiment;// 電気電子工学実験(偶数)のコマ数
	private int f_NumOfOddEngineeringDesignExperiment;// エンジニアリングデザイン実験(奇数)のコマ数
	private int f_NumOfEvenEngineeringDesignExperiment;// エンジニアリングデザイン実験(偶数)のコマ数
	private int f_NumOfEleInfoCommunicationExperiment;// エレ情実験の何のテーマか
	private int f_NumOfEnergyEnvironmentExperiment;// エネ環実験の何のテーマか
	private int f_NumOfGraduateStudySeminar;// 卒研ゼミのコマ数
	private int f_HasGraduateStudy;// 卒研あるか
	private String f_Lab;// 研究室
	private ArrayList<String> f_ChargeOfSubjects = new ArrayList<String>();// 担当科目の動的配列

	private int f_NumOfNewSubject;// 新規の科目のコマ数
	private int f_NumOfAllSubject;// 全科目のコマ数
	private int[][] f_DayNumber = new int[2][MAX_DAY + 1];// 曜日のコマ数

	/*
	 * コンストラクタ nullまたは0にする
	 */
	public Teacher() {
		f_Name = null;
		f_NumOfBasicSeminar1 = 0;
		f_NumOfBasicSeminar2 = 0;
		f_NumOfOddElectricElectronicTraining = 0;
		f_NumOfEvenElectricElectronicTraining = 0;
		f_NumOfOddElectricElectronicExperiment = 0;
		f_NumOfEvenElectricElectronicExperiment = 0;
		f_NumOfOddEngineeringDesignExperiment = 0;
		f_NumOfEvenEngineeringDesignExperiment = 0;
		f_NumOfEleInfoCommunicationExperiment = 0;
		f_NumOfEnergyEnvironmentExperiment = 0;
		f_NumOfGraduateStudySeminar = 0;
		f_HasGraduateStudy = 0;

		f_Lab = null;
		f_NumOfNewSubject = 0;
		f_NumOfAllSubject = 0;

		resetDayNumber();
	}

	/*
	 * 曜日のコマ数をリセット
	 */
	public void resetDayNumber() {

		for (int semester = 0; semester <= MAX_SEMESTER; semester++) {

			for (int day = 0; day <= MAX_DAY; day++) {
				f_DayNumber[semester][day] = 0;
			}
		}
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
	 * 基礎ゼミ2のコマ数のゲッター
	 *
	 * @return 基礎ゼミ2のコマ数
	 */
	public int getNumOfBasicSeminar2() {
		return f_NumOfBasicSeminar2;
	}

	/*
	 * 奇数の電気電子工学実習のコマ数のゲッター
	 *
	 * @return 電気電子工学実習のコマ数
	 */
	public int getNumOfOddEETraining() {
		return f_NumOfOddElectricElectronicTraining;
	}

	/*
	 * 偶数の電気電子工学実習のコマ数のゲッター
	 *
	 * @return 電気電子工学実習のコマ数
	 */
	public int getNumOfEvenEETraining() {
		return f_NumOfEvenElectricElectronicTraining;
	}

	/*
	 * 奇数の電気電子工学実験のコマ数のゲッター
	 *
	 * @return 電気電子工学実験のコマ数
	 */
	public int getNumOfOddEEExperiment() {
		return f_NumOfOddElectricElectronicExperiment;
	}

	/*
	 * 偶数の電気電子工学実験のコマ数のゲッター
	 *
	 * @return 電気電子工学実験のコマ数
	 */
	public int getNumOfEvenEEExperiment() {
		return f_NumOfEvenElectricElectronicExperiment;
	}

	/*
	 * 奇数のエンジニアリングデザイン実験のコマ数のゲッター
	 *
	 * @return エンジニアリングデザイン実験のコマ数
	 */
	public int getNumOfOddEngineeringDesignExperiment() {
		return f_NumOfOddEngineeringDesignExperiment;
	}

	/*
	 * 偶数のエンジニアリングデザイン実験のコマ数のゲッター
	 *
	 * @return エンジニアリングデザイン実験のコマ数
	 */
	public int getNumOfEvenEngineeringDesignExperiment() {
		return f_NumOfEvenEngineeringDesignExperiment;
	}

	/*
	 * エレ情実験のコマ数のゲッター
	 *
	 * @return エレ情実験のコマ数
	 */
	public int getNumOfEleInfoCommunicationExperiment() {
		return f_NumOfEleInfoCommunicationExperiment;
	}

	/*
	 * エネ環実験のコマ数のゲッター
	 *
	 * @return エネ環実験のコマ数
	 */
	public int getNumOfEnergyEnvironmentExperiment() {
		return f_NumOfEnergyEnvironmentExperiment;
	}

	/*
	 * 卒研ゼミのコマ数のゲッター
	 *
	 * @return 卒研ゼミのコマ数
	 */
	public int getNumOfGraduateStudySeminar() {
		return f_NumOfGraduateStudySeminar;
	}

	/*
	 * 卒研のコマ数のゲッター
	 *
	 * @return 卒研のコマ数
	 */
	public int getNumOfGraduateStudy() {
		return f_HasGraduateStudy;
	}

	/*
	 * 新規の科目のコマ数のゲッター
	 *
	 * @return 新規の科目のコマ数
	 */
	public int getNumOfNewSubject() {
		return f_NumOfNewSubject;
	}

	/*
	 * 全科目のコマ数のゲッター
	 *
	 * @return 全科目のコマ数
	 */
	public int getNumOfAllSubject() {
		return f_NumOfAllSubject;
	}

	/*
	 * 曜日のコマ数のゲッター
	 *
	 * @param semester 学期
	 *
	 * @param day 曜日
	 *
	 * @return 指定した曜日のコマ数
	 */
	public int getDayNumber(int semester, int day) {
		return f_DayNumber[semester][day];
	}

	/*
	 * 研究室の階のゲッター
	 *
	 * @return 研究室の階
	 */
	public String getLab() {
		return f_Lab;
	}

	/*
	 * 担当科目の動的配列のゲッター
	 *
	 * @param number 要素番号
	 *
	 * @return 担当科目
	 */
	public String getChargeOfSubject(int number) {
		return f_ChargeOfSubjects.get(number);
	}

	/*
	 * 担当科目の動的配列のサイズのゲッター
	 */
	public int getSizeChargeOfSubject() {
		return f_ChargeOfSubjects.size();
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
	 * 基礎ゼミ2のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfBasicSeminar2(int num) {
		f_NumOfBasicSeminar2 = num;
	}

	/*
	 * 奇数の電気電子工学実習のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfOddEETraining(int num) {
		f_NumOfOddElectricElectronicTraining = num;
	}

	/*
	 * 偶数の電気電子工学実習のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfEvenEETraining(int num) {
		f_NumOfEvenElectricElectronicTraining = num;
	}

	/*
	 * 奇数の電気電子工学実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfOddEEExperiment(int num) {
		f_NumOfOddElectricElectronicExperiment = num;
	}

	/*
	 * 偶数の電気電子工学実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfEvenEEExperiment(int num) {
		f_NumOfEvenElectricElectronicExperiment = num;
	}

	/*
	 * 奇数のエンジニアリングデザイン実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfOddEngineeringDesignExperiment(int num) {
		f_NumOfOddEngineeringDesignExperiment = num;
	}

	/*
	 * 偶数のエンジニアリングデザイン実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfEvenEngineeringDesignExperiment(int num) {
		f_NumOfEvenEngineeringDesignExperiment = num;
	}

	/*
	 * エレ情実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfEleInfoCommunicationExperiment(int num) {
		f_NumOfEleInfoCommunicationExperiment = num;
	}

	/*
	 * エネ環実験のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfEnergyEnvironmentExperiment(int num) {
		f_NumOfEnergyEnvironmentExperiment = num;
	}

	/*
	 * 卒研ゼミのコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfGraduateStudySeminar(int num) {
		f_NumOfGraduateStudySeminar = num;
	}

	/*
	 * 卒研のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setHasGraduateStudy(int num) {
		f_HasGraduateStudy = num;
	}

	/*
	 * 新規の科目のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfNewSubject(int num) {
		f_NumOfNewSubject = num;
	}

	/*
	 * 全科目のコマ数のセッター
	 *
	 * @param num コマ数
	 */
	public void setNumOfAllSubject(int num) {
		f_NumOfAllSubject = num;
	}

	/*
	 * それぞれの曜日のコマ数のセッター
	 *
	 * @param semester 学期
	 *
	 * @param day 曜日
	 *
	 * @param num コマ数
	 */
	public void setDayNumber(int semester, int day, int num) {
		f_DayNumber[semester][day] = num;
	}

	/*
	 * 研究室の階のセッター
	 *
	 * @param floor 部屋
	 */
	public void setLab(String floor) {
		f_Lab = floor;
	}

	/*
	 * 担当科目の動的配列のセッター
	 *
	 * @param number 要素番号
	 *
	 * @param subject 科目
	 */
	public void setChargeOfSubject(int number, String subject) {
		f_ChargeOfSubjects.set(number, subject);
	}

	// ---------------------------------//
	// -------------add関数-------------//
	// ---------------------------------//
	/*
	 * 担当科目の動的配列の追加
	 *
	 * @param subject 科目
	 */
	public void addChargeOfSubject(String subject) {
		f_ChargeOfSubjects.add(subject);
	}

}
