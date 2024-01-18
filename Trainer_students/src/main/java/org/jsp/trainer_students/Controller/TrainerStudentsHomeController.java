package org.jsp.trainer_students.Controller;

import java.util.Scanner;

import org.jsp.trainer_students.DAO.TrainerStudentsDao;

public class TrainerStudentsHomeController {

	static TrainerStudentsDao dao = new TrainerStudentsDao();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println(
					"1. Save Trainer \n2. update Trainer \n3. Find Trainer by phone and password \n4. Find Trainer by email and password \n5. Find Trainer by Id \n6. Delete Trainer by Id \n7. Save Students \n8. update Students \n9. Find Students by phone and password \n10. Find Students by trainer Id \n11. Delete Student by Id \n12. exit");

			int key = sc.nextInt();
			switch (key) {

			case 1:
				dao.saveTrainer();
				break;

			case 2:
				dao.updateTrainer();
				break;

			case 3:
				dao.findTrainerByPhoneAndPassword();
				break;

			case 4:
				dao.findTrainerByEmailAndPassword();
				break;

			case 5:
				dao.findTrainerById();
				break;

			case 6:
				dao.deleteTrainerById();
				break;

			case 7:
				dao.saveStudents();
				break;

			case 8:
				dao.updateStudents();
				break;

			case 9:
				dao.verifyStudentByPhoneAndPassword();
				break;

			case 10:
				dao.findStudentsByTrainerId();
				break;

			case 11:
				dao.deleteStudentsById();
				break;

			case 12:
				System.exit(0);
				break;

			default:
				System.out.println("INVALID OPTION , PLEASE CHOOSE VALID OPTION...!!!\n\n\n");
				break;
			}
		}
	}
}
