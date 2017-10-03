package GeneticAlgo;

public class Main {

	public static void main(String[] args) {

		Decide_faculty Dec_fac = new Decide_faculty  ();
		Decide_room Dec_room = new Decide_room  ();

		Dec_fac.exec();
		Dec_room.exec();
	}

}
