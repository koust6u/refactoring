import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreReportMain {
	private static final String ENTER_NAME_MESSAGE = "Enter student name: ";
	private static final String COMMAND_INFORMATION_MESSAGE = """

			Select a command !\s
			\t -1. Init
			\t 0. Quit
			\t 1. Add student
			\t 2. Add scores
			\t 3. Show student report
			\t 4. Clear student scores""";
	private static final String BLANK = " ";
	private static final String CLEAR_MESSAGE = "Score cleared";
	private static final String QUIT_MESSAGE = "Enter scores( -1 for quit): ";
	private static final Integer QUIT_NUMBER = -1;
	private static final String BYE = "Bye";
	
	
	private static final ScoreReportMain instance = new ScoreReportMain();

	private ScoreReportMain(){
	}
	private static ScoreReportMain getInstance(){
		return instance;
	}
	private static final Scanner scanner = new Scanner(System.in) ;
	private static final List<Student> students = new ArrayList<Student>();

	public static void main(String[] args) {
		boolean RunTimeCheckFlag = false ;
		while ( ! RunTimeCheckFlag ) {
			CommandList command = UserInterface();
			switch (command) {
				case QUIT -> RunTimeCheckFlag = true;
				case ADD_STUDENT -> additionalStudentEnrollments();
				case ADD_SCORE -> additionStudentScores();
				case STUDENT_INFO -> findScoresByStudentName();
				case DELETE -> deleteStudentScoreByName();
				case INITIALIZE -> initRepository();
				default -> {}
			}
		}
		GOODBYE();
	}

	private static void GOODBYE(){
		System.out.println(BYE);
	}
	private static void UserCommandViewer(){
		System.out.println(COMMAND_INFORMATION_MESSAGE);
	}

	private static CommandList getUserCommand() {
		int commandNumber = scanner.nextInt() ;
		return CommandList.getType(commandNumber);
	}

	private static CommandList UserInterface() {
		UserCommandViewer();
		return getUserCommand();
	}

	private static void initRepository() {
		Student DefualtStudent = new Student("James") ;
		DefualtStudent.getScores().add(20);
		DefualtStudent.getScores().add(10);
		students.add(DefualtStudent);

		DefualtStudent = new Student("Brown") ;
		DefualtStudent.getScores().add(30);
		DefualtStudent.getScores().add(10);
		DefualtStudent.getScores().add(20);
		DefualtStudent.getScores().add(60);
		DefualtStudent.getScores().add(50);
		students.add(DefualtStudent) ;
	}

	private static void deleteStudentScoreByName() {
		System.out.println(ENTER_NAME_MESSAGE);
		String targetStudent = scanner.next() ;
		for ( Student student : students) {
			if (student.isSameAs(targetStudent)) {
				student.getScores().clear();
				System.out.println(CLEAR_MESSAGE);
				break;
			}
		}
	}

	private static void printStudentScores(List<Integer> scores){
		int sumOfScores = 0;
		for (Integer score : scores) {
			System.out.println(score + BLANK);
			sumOfScores += score;
		}
		System.out.println(sumOfScores);
	}

	private static void findScoresByStudentName() {
		System.out.println(ENTER_NAME_MESSAGE);
		String targetName = scanner.next() ;
		for (Student student : students) {
			if (student.isSameAs(targetName)) {
				printStudentScores(student.getScores());
				break;
			}
		}
	}

	private static void additionalStudentEnrollments(){
		System.out.println(ENTER_NAME_MESSAGE);
		String newName = scanner.next();
		Student newStudent = new Student(newName);
		students.add(newStudent);
	}

	private static void additionStudentScores() {
		System.out.println(ENTER_NAME_MESSAGE);
		String targetName = scanner.next() ;
		for ( Student student : students) {
			if (student.isSameAs(targetName)) {
				System.out.println(QUIT_MESSAGE);
				while (true) {
					Integer score = scanner.nextInt();
					if (score.equals(QUIT_NUMBER)) break;
					student.getScores().add(score);
				}
				break;
			}
		}
	}

}