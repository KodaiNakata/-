package GeneticAlgo;

// �搶�̃N���X
public class Teacher {

	String f_Name;// ����
	int f_NumOfBasicSeminar1;// ��b�[�~1�������Ă��邩
	int f_NumOfBasicSeminar2;// ��b�[�~2�������Ă��邩
	int f_HasEngineeringDesignExperience;// �G���W�j�A�����O�f�U�C�������������Ă��邩
	String f_ThemeOfElectricElectronicTraining;// �d�C�d�q�H�w���K�̉��̃e�[�}��
	int f_NumOfElectricElectronicTraining;// �d�C�d�q�H�w���K�̃R�}��
	String f_ThemeOfElectricElectronicExperiment;// �d�C�d�q�H�w�����̉��̃e�[�}��
	int f_NumOfElectricElectronicExperiment;// �d�C�d�q�H�w�����̃R�}��
	String f_ThemeOfEleInfoCommunicationExperiment;// �G��������̉��̃e�[�}��
	String f_ThemeOfEnergyEnvironmentExperiment;// �G�l�����̉��̃e�[�}��
	int f_NumOfGraduateStudySeminar;// �����[�~�̃R�}��

	/*
	 * �R���X�g���N�^
	 * null�܂���0�ɂ���
	 */
	public Teacher(){
		f_Name=null;
		f_NumOfBasicSeminar1=0;
		f_NumOfBasicSeminar2=0;
		f_HasEngineeringDesignExperience=0;
		f_ThemeOfElectricElectronicTraining=null;
		f_NumOfElectricElectronicTraining=0;
		f_ThemeOfElectricElectronicExperiment=null;
		f_NumOfElectricElectronicExperiment=0;
		f_ThemeOfEleInfoCommunicationExperiment=null;
		f_ThemeOfEnergyEnvironmentExperiment=null;
		f_NumOfGraduateStudySeminar=0;
	}

	/*
	 * �����̃Q�b�^�[
	 * @return ����
	 */
	public String getName(){
		return f_Name;
	}

	/*
	 * �����̃Z�b�^�[
	 * @param name ����
	 */
	public void setName(String name){
		f_Name=name;
	}

}
