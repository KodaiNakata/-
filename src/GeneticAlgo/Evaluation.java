package GeneticAlgo;

/*
 * 評価値のクラス
 * 
 * @author Nakata
 */
public class Evaluation {

	private int f_Generation;// 世代
	private double f_EvaluationValue;// 評価値

	/*
	 * コンストラクタ
	 */
	public Evaluation() {
		f_Generation = -1;
		f_EvaluationValue = 0.0;
	}

	// ----------------------------//
	// ---------ゲッター-----------//
	// ----------------------------//
	/*
	 * 世代のゲッター
	 */
	public int getGeneration() {
		return f_Generation;
	}

	/*
	 * 評価値のゲッター
	 */
	public double getEvaluationValue() {
		return f_EvaluationValue;
	}

	// ----------------------------//
	// ---------セッター-----------//
	// ----------------------------//
	/*
	 * 世代のセッター
	 * 
	 * @param generation 世代
	 */
	public void setGeneration(int generation) {
		f_Generation = generation;
	}

	/*
	 * 評価値のセッター
	 * 
	 * @param value 値
	 */
	public void setEvaluationValue(double value) {
		f_EvaluationValue = value;
	}
}
