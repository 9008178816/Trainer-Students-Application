package org.jsp.trainer_students.DAO;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.trainer_students.DTO.Students;
import org.jsp.trainer_students.DTO.Trainer;

public class TrainerStudentsDao {

	static EntityManager manager = Persistence.createEntityManagerFactory("TSA").createEntityManager();
	static EntityTransaction transaction = manager.getTransaction();
	static Scanner sc = new Scanner(System.in);

	public void saveTrainer() {
		Trainer a = saveT();
		manager.persist(a);
		transaction.begin();
		transaction.commit();
		System.out.println("Trainer saved with id : " + a.getId());
	}

	public Trainer saveT() {
		Trainer t = new Trainer();
		System.out.println("enter name, email, phone number, batch_name and password");
		t.setName(sc.next());
		t.setEmail(sc.next());
		t.setPhone(sc.nextLong());
		t.setBatch_name(sc.next());
		t.setPassword(sc.next());
		return t;
	}

	public void updateTrainer() {
		System.out.println("enter the trainer id");
		int id = sc.nextInt();
		Trainer t = manager.find(Trainer.class, id);
		if (t != null) {
			System.out.println("enter name, email, phone number, batch_name and password");
			t.setName(sc.next());
			t.setEmail(sc.next());
			t.setPhone(sc.nextLong());
			t.setBatch_name(sc.next());
			t.setPassword(sc.next());
			transaction.begin();
			transaction.commit();
			System.out.println("Trainer update with the id : " + id);
			return;
		}
		System.err.println("Invalid trainer id , please enter the valid trainer id");
	}

	public void findTrainerByPhoneAndPassword() {
		System.out.println("enter the phone number and password ");
		long phone = sc.nextLong();
		String password = sc.next();
		Trainer t = verifyByPhone(phone, password);
		if (t != null) {
			System.out.println("Trainer id : " + t.getId());
			System.out.println("Trainer Name : " + t.getName());
			System.out.println("Trainer email : " + t.getEmail());
			System.out.println("Trainer batch : " + t.getBatch_name());
			System.out.println("Trainer phone number : " + t.getPhone() + "\n\n\n");
			return;
		} else {
			System.err.println("Invalid phone number or password...!!!\n\n\n");
		}
	}

	public Trainer verifyByPhone(long phone, String password) {
		Query q = manager.createQuery("select t from Trainer t where t.phone=?1 and t.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Trainer) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void findTrainerByEmailAndPassword() {
		System.out.println("enter the email and password ");
		String email = sc.next();
		String password = sc.next();
		Trainer t = verifyByEmail(email, password);
		if (t != null) {
			System.out.println("Trainer id : " + t.getId());
			System.out.println("Trainer Name : " + t.getName());
			System.out.println("Trainer email : " + t.getEmail());
			System.out.println("Trainer batch : " + t.getBatch_name());
			System.out.println("Trainer phone number : " + t.getPhone() + "\n\n\n");
			return;
		} else {
			System.err.println("Invalid phone number or password...!!!\n\n\n");
		}
	}

	public Trainer verifyByEmail(String Email, String password) {
		Query q = manager.createQuery("select t from Trainer t where t.email=?1 and t.password=?2");
		q.setParameter(1, Email);
		q.setParameter(2, password);
		try {
			return (Trainer) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void findTrainerById() {
		System.out.println("enter the id to fetch details ");
		int id = sc.nextInt();
		Trainer t = manager.find(Trainer.class, id);
		if (t != null) {
			System.out.println("Trainer id : " + t.getId());
			System.out.println("Trainer Name : " + t.getName());
			System.out.println("Trainer email : " + t.getEmail());
			System.out.println("Trainer batch : " + t.getBatch_name());
			System.out.println("Trainer phone number : " + t.getPhone() + "\n\n\n");
			return;
		} else {
			System.err.println("Invalid phone number or password...!!!\n\n\n");
		}
	}

	public void saveStudents() {
		System.out.println("enter the Trainer id");
		int id = sc.nextInt();
		Trainer t = manager.find(Trainer.class, id);
		if (t != null) {
			Students s = saveS();
			t.getStudents().add(s);
			s.setTrainer(t);
			manager.persist(s);
			transaction.begin();
			transaction.commit();
			return;
		} else {
			System.err.println("Invalid Trainer Id...!!!\n\n\n");
		}
	}

	public Students saveS() {
		Students s = new Students();
		System.out.println("enter name, email, phone number and password");
		s.setName(sc.next());
		s.setEmail(sc.next());
		s.setPhone(sc.nextLong());
		s.setPassword(sc.next());
		return s;
	}

	public void findStudentsByTrainerId() {
		System.out.println("enter the Trainer id ");
		int id = sc.nextInt();
		Query q = manager.createQuery("select s from Students s where s.trainer.id=?1");
		q.setParameter(1, id);
		List<Students> s = q.getResultList();
		if (s.size() > 0) {
			for (Students e : s) {
				System.out.println("Trainer id : " + e.getId());
				System.out.println("Trainer Name : " + e.getName());
				System.out.println("Trainer email : " + e.getEmail());
				System.out.println("Trainer phone number : " + e.getPhone());
				System.out.println("-----------------------------------\n\n");
			}
		} else {
			System.out.println("No students present in trainer id " + id);
		}
	}

	public void updateStudents() {
		System.out.println("enter the students id");
		int id = sc.nextInt();
		Students s = manager.find(Students.class, id);
		if (s != null) {
			System.out.println("enter name, email, phone number and password");
			s.setName(sc.next());
			s.setEmail(sc.next());
			s.setPhone(sc.nextLong());
			s.setPassword(sc.next());
			transaction.begin();
			transaction.commit();
			System.out.println("Students update with the id : " + id);
			return;
		}
		System.err.println("Invalid students id , please enter the valid students id");
	}

	public void verifyStudentByPhoneAndPassword() {
		System.out.println("enter the phone number and password ");
		long phone = sc.nextLong();
		String password = sc.next();
		Students s = verifyByPhoneS(phone, password);
		if (s != null) {
			System.out.println("Trainer id : " + s.getId());
			System.out.println("Trainer Name : " + s.getName());
			System.out.println("Trainer email : " + s.getEmail());
			System.out.println("Trainer phone number : " + s.getPhone() + "\n\n\n");
			return;
		} else {
			System.err.println("Invalid phone number or password...!!!\n\n\n");
		}
	}

	public Students verifyByPhoneS(long phone, String password) {
		Query q = manager.createQuery("select t from Trainer t where t.phone=?1 and t.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Students) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void deleteStudentsById() {
		System.out.println("enter the students id ");
		int id = sc.nextInt();
		Students s = manager.find(Students.class, id);
		if(s!=null) {
			manager.remove(s);
			transaction.begin();
			transaction.commit();
			System.out.println("Students deleted with id "+s.getId());
		}else {
			System.out.println("Invalid Student id...!!!");
		}
	}

	public void deleteTrainerById() {
		System.out.println("enter the Trainer id ");
		int id = sc.nextInt();
		Trainer t = manager.find(Trainer.class, id);
		if(t!=null) {
			manager.remove(t);
			transaction.begin();
			transaction.commit();
			System.out.println("Students deleted with id "+t.getId());
		}else {
			System.out.println("Invalid Student id...!!!");
		}
	}

}
