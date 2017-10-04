package GeneticAlgo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// �t�@�C���̓��o�͊֌W�̃N���X
public class FileIO {

	/*
	 * �t�@�C����ǂݍ���
	 *
	 * @param fileName:�t�@�C���̖��O
	 */
	public static BufferedReader readFile(String fileName) {

		BufferedReader readFile = null;

		try {
			readFile = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException error_report) {
			System.out.println(fileName + "not found");
			System.out.println(error_report);
			System.exit(1);
		}

		return readFile;
	}

	/*
	 * �t�@�C���֏�������
	 *
	 * @param fileName:�t�@�C���̖��O
	 */
	public static PrintWriter writeFile(String fileName, boolean app) {
		PrintWriter f_write = null;

		try {
			f_write = new PrintWriter(new BufferedWriter(new FileWriter(
					fileName, app)));
		} catch (IOException error_report) {
			System.out.println(error_report);
			System.exit(1);
		}

		return f_write;
	}

	/*
	 * �T�������t�@�C�����`�F�b�N
	 * @param fileName:�T�������t�@�C���̖��O
	 */
	public static void checkTargetFile(String fileName) {

		File targetFile = new File(fileName);

		try{

		// �t�@�C�������݂��Ȃ��Ƃ�
		if (!(targetFile.exists())) {
			System.out.println(targetFile + "�����݂��܂���B");
			System.out.println(targetFile+"���쐬���܂��B");

			// Writer writer = new FileWriter(scorefile);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					targetFile)));
			pw.println(0.0);
			pw.close();
		}

		// �t�@�C�������݂���Ƃ�
		else{
			System.out.println(targetFile+"�͑��݂��܂��B");
		}

		}catch(IOException e){
			System.out.println("���o�̓G���[�ł��B");
		}
	}
}
