package GeneticAlgo;


/*
 * 評価値のクラス
 *
 * @author Nakata
 */
public class Evaluation implements iDayPeriod{

	private int f_Generation;// 世代
//	private ArrayList<Integer> f_EvaluationValues=new ArrayList<Integer>();
	private long[] f_EvaluationValues=new long[CANDIDATE_NUM];
	private long f_SumEvaluationValue;// 評価値

	/*
	 * コンストラクタ
	 */
	public Evaluation() {
		f_Generation = -1;
		f_SumEvaluationValue = 0;

		for(int candidate=0;candidate<CANDIDATE_NUM;candidate++){
			f_EvaluationValues[candidate]=0;
		}

//		f_EvaluationValues.clear();
	}

//	/*
//	 * 各評価値の動的配列の追加
//	 *
//	 * @param value 評価値
//	 */
//	public void addEvaluationValues(int value){
//		f_EvaluationValues.add(value);
//	}

	// ----------------------------//
	// ---------ゲッター-----------//
	// ----------------------------//
	/*
	 * 世代のゲッター
	 *
	 * @return 世代
	 */
	public int getGeneration() {
		return f_Generation;
	}

//	/*
//	 * 各評価値のゲッター
//	 *
//	 * @param number 要素番号
//	 *
//	 * @return 指定された要素の評価値
//	 */
//	public int getEvaluationValues(int number){
//		return f_EvaluationValues.get(number);
//	}

	public long[] getArrayOfEvaluationValues(){
		return f_EvaluationValues;
	}

	public long getEvaluationValues(int candidate){
		return f_EvaluationValues[candidate];
	}

	/*
	 * 評価値のゲッター
	 *
	 * @return 評価値
	 */
	public long getSumEvaluationValue() {
		return f_SumEvaluationValue;
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

	public void setEvaluationValues(int candidate,long[] evaluation,long value){
		evaluation[candidate]=value;
	}

	/*
	 * 評価値のセッター
	 *
	 * @param value 値
	 */
	public void setSumEvaluationValue(long value) {
		f_SumEvaluationValue = value;
	}

}
