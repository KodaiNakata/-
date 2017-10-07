package GeneticAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  ���o�͊֌W�̃N���X
 *  @author Nakata
 */
public class InOutPut {

	/*
	 * �R���X�g���N�^
	 */
	public InOutPut() {

	}

	/*
	 * �����L�[������
	 */
	public static void anyKey() {
		System.out.println("Hit any key...");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));

			br.readLine();
		} catch (IOException e) {
			System.out.println("���o�̓G���[�ł��B");
		}
	}

	/*
	 * �ŏ��l�ƍő�l�̗̈���Ő����l����͂���
	 * @param min:�ŏ��l
	 * @param max:�ő�l
	 */
	public static int getnCheck(int min, int max) {
		int num = 0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String str = br.readLine();
			num = Integer.parseInt(str);

			while (num < min || num > max) {
				str = br.readLine();
				num = Integer.parseInt(str);
			}
		} catch (IOException e) {
			System.out.println("���o�̓G���[�ł��B");
		}
		return num;
	}

	/*
	 * ���j���[���擾
	 * @param str:�o�͂��镶����
	 * @param min:�ŏ��l
	 * @param max:�ő�l
	 */
	public static int getMenu(String str, int min, int max) {
		System.out.println(str);

		int d = getnCheck(min, max);
		System.out.println(d);
		return d;
	}

	/*
	 * �f�R���[�V����
	 * @param str:�o�͂��镶����
	 * @param chara:�o�͂��镶��
	 */
	public static void decoration(String str, char chara) {
		StringBuffer ds = new StringBuffer("");
		ds.append(str);
		for (int i = 0; i < ds.length(); i++)
			ds.setCharAt(i, chara);

		System.out.println(ds);
		System.out.println(str);
		System.out.println(ds);

	}
}
