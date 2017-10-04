package GeneticAlgo;

import java.util.ArrayList;

// �搶�̃N���X
public class Teacher {

	private String f_Name;// ����
	private int f_NumOfBasicSeminar1;// ��b�[�~1�������Ă��邩
	private int f_NumOfBasicSeminar2;// ��b�[�~2�������Ă��邩
	private int f_HasEngineeringDesignExperience;// �G���W�j�A�����O�f�U�C�������������Ă��邩
	private String f_ThemeOfElectricElectronicTraining;// �d�C�d�q�H�w���K�̉��̃e�[�}��
	private int f_NumOfElectricElectronicTraining;// �d�C�d�q�H�w���K�̃R�}��
	private String f_ThemeOfElectricElectronicExperiment;// �d�C�d�q�H�w�����̉��̃e�[�}��
	private int f_NumOfElectricElectronicExperiment;// �d�C�d�q�H�w�����̃R�}��
	private String f_ThemeOfEleInfoCommunicationExperiment;// �G��������̉��̃e�[�}��
	private String f_ThemeOfEnergyEnvironmentExperiment;// �G�l�����̉��̃e�[�}��
	private int f_NumOfGraduateStudySeminar;// �����[�~�̃R�}��
	private ArrayList<String> f_ClassSubjects=new ArrayList<String>();// ���ƉȖڂ̓��I�z��

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

	/*
	 * ��b�[�~1�̃R�}���̃Q�b�^�[
	 * @return ��b�[�~1�̃R�}��
	 */
	public int getNumOfBasicSeminar1(){
		return f_NumOfBasicSeminar1;
	}

	/*
	 * ��b�[�~1�̃R�}���̃Z�b�^�[
	 * @param num �R�}��
	 */
	public void setNumOfBasicSeminar1(int num){
		f_NumOfBasicSeminar1=num;
	}
}
