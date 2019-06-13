package java8demo;

import static java.util.stream.Stream.concat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyDemo {


	public static void main(String[] args) {

		List<Integer> nosList = Arrays.asList(11, 7);
		int result = findSquareOfMaxOdd(nosList);
		System.out.println("Result is:" + result);

		//

		List<String> cntryList = Arrays.asList("India", "China", "England");
		// Stream<String> words = Arrays.asList("A", "B", "C", "D").stream();

		String joinedCntryString = cntryList.stream().collect(Collectors.joining()); // ABCD

		System.out.println(joinedCntryString);

		joinedCntryString = cntryList.stream().collect(Collectors.joining(",")); // A,B,C,D

		System.out.println(joinedCntryString);

		joinedCntryString = cntryList.stream().collect(Collectors.joining(",", "\"", "\"")); // {A,B,C,D}

		System.out.println(joinedCntryString);
		//

		List<String> listOfStrings = Arrays.asList("1", "2", "3", "4", "5");

		List<Integer> listOfIntegers = listOfStrings.stream().map(Integer::valueOf).collect(Collectors.toList());

		System.out.println(listOfIntegers); // [1, 2, 3, 4, 5]

		// flatMap Example

		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(4, 5, 6);
		List<Integer> list3 = Arrays.asList(7, 8, 9);

		List<List<Integer>> listOfLists = Arrays.asList(list1, list2, list3);

		List<Integer> listOfAllIntegers = listOfLists.stream().flatMap(x -> x.stream()).collect(Collectors.toList());

		System.out.println(listOfAllIntegers);

		//

		List<Employee> listOfEmployees = Arrays.asList(new Employee("1", "Sridhar", "Salem", 42),
				new Employee("2", "Suresh", "CBE", 40));

		//Stream List , Map Each Element to its upperCase and collect them all as a New List
		List<String> listOfEmployeeNameLength = listOfEmployees.stream().map(x -> x.empName.toUpperCase())
				.collect(Collectors.toList());
		System.out.println(listOfEmployeeNameLength);

		//Stream List , Map Each Element to its upperCase and collect them all delimiting with "x",adding prefix as "START" and suffix as "END"
		String str = listOfEmployees.stream().map(x -> x.empName.toUpperCase())
				.collect(Collectors.joining("*", "START ", "END "));

		System.out.println(str);

		//Stream List , Filter and Print Each Element
		listOfEmployees.stream().filter(i -> i.empName.startsWith("Su")).forEach(System.out::println);

		//List to Stream then Map with Key and Value as object
		Map<String, Employee> employeesMap = listOfEmployees.stream()
				.collect(Collectors.toMap(Employee::getEmpNo, employee->employee));

		employeesMap.forEach((x, y) -> System.out.println(" EmpNo:" + x + "::" + "Employee :" + y));

		
		Comparator<Employee> comparator = Comparator.comparing(Employee::getAge);

		// Get Youngest Employee by streaming List and applying Min Age comparator
		Employee youngestEmployee = listOfEmployees.stream().min(comparator).get();
		
		// Get Oldest Employee by streaming List and applying Max Age comparator
		Employee oldestEmployee = listOfEmployees.stream().max(comparator).get();

		System.out.println("youngestEmployee = " + youngestEmployee);
		System.out.println("oldestEmployee = " + oldestEmployee);
		
		
		//Stream Employee List and Convert to Employee Array
		Employee[] employeesArray = listOfEmployees.stream().toArray(Employee[]::new);
		System.out.println("employeesArray :: " + Arrays.toString(employeesArray));

		// Stream List, Filter and then Collect as Array
		Employee[] salemEmployeesArray = listOfEmployees.stream().filter(e -> e.getCity().equalsIgnoreCase("Salem"))
				.toArray(Employee[]::new);
		System.out.println("salemEmployeesArray :: " + Arrays.toString(salemEmployeesArray));

		// Stream List, Filter and then Collect as List
		List<Employee> cbeEmployeesList = listOfEmployees.stream().filter(e -> e.getCity().equalsIgnoreCase("CBE"))
				.collect(Collectors.toList());
		System.out.println("cbeEmployeesList :: " + cbeEmployeesList);

		// Stream List, Filter and then Collect as Map
		Map<String, Employee> cbeEmployeesMap = listOfEmployees.stream()
				.filter(e -> e.getCity().equalsIgnoreCase("CBE"))
				.collect(Collectors.toMap(Employee::getEmpNo, Function.identity()));
		System.out.println("cbeEmployeesMap :: " + cbeEmployeesMap);
		
		
		//Apply Predicate (isCBEEmployee) to List and Filter Elements
		System.out.println(filterEmployees(listOfEmployees,isCBEEmployee()));
		
		//Apply Predicate (isSalemEmployee) to List and Filter Elements
		System.out.println(filterEmployees(listOfEmployees,isSalemEmployee()));
		
		//Apply Predicate (isAgeMoreThan(40)) to List and Filter Elements
		System.out.println(filterEmployees(listOfEmployees,isAgeMoreThan(40)));

		//

		List<Integer> listOfNos = Arrays.asList(1, 2, 3, 4, 5);
		
		//Stream List, find Max element using Comparator
		Integer maxNumber = listOfNos.stream().max(Comparator.comparing(Integer::valueOf)).get();

		//Stream List, find Min element using Comparator
		Integer minNumber = listOfNos.stream().min(Comparator.comparing(Integer::valueOf)).get();

		System.out.println("maxNumber = " + maxNumber);
		System.out.println("minNumber = " + minNumber);

		
		Consumer<Integer> action = i -> {
			if (i % 2 == 0) {
				System.out.println("Even number :: " + i);
			} else {
				System.out.println("Odd  number :: " + i);
			}
		};

		//Stream List, Apply If/Else Action to each element
		listOfNos.stream().forEach(action);

		// Total Count of Nos
		Long count = listOfNos.stream().collect(Collectors.counting());
		System.out.println("Total Count of Nos :: " + count);
		// Total Count of Even Nos
		Long evenCount = listOfNos.stream().filter(i -> i % 2 == 0).collect(Collectors.counting());
		System.out.println("Total Count of Even Nos :: " + evenCount);
		// Total Count of Odd Nos
		Long oddCount = listOfNos.stream().filter(i -> i % 2 != 0).collect(Collectors.counting());
		System.out.println("Total Count of Odd Nos :: " + oddCount);

		
		//Stream Integer List and Convert to Integer Array
		Integer[] MyArray = listOfNos.stream().toArray(Integer[]::new);
		System.out.println("List to Array :: " + Arrays.toString(MyArray));

		Stream<Integer> firstStream = Stream.of(1, 2, 3);
		Stream<Integer> secondStream = Stream.of(4, 5, 6);

		// Merge two streams
		Stream<Integer> resultingStream1 = Stream.concat(firstStream, secondStream);

		System.out.println(resultingStream1.collect(Collectors.toList()));

		Stream<Integer> first = Stream.of(1, 2);
		Stream<Integer> second = Stream.of(3, 4);
		Stream<Integer> third = Stream.of(5, 6);
		Stream<Integer> fourth = Stream.of(7, 8);

		// Merge multiple streams
		Stream<Integer> resultingStream2 = Stream.concat(first, concat(second, concat(third, fourth)));

		System.out.println(resultingStream2.collect(Collectors.toList()));

		Stream<Integer> firstStream1 = Stream.of(1, 2, 3, 4, 5, 6);
		Stream<Integer> secondStream1 = Stream.of(4, 5, 6, 7, 8, 9);

		// Java merge streams and retain unique elements
		Stream<Integer> resultingStream3 = Stream.concat(firstStream1, secondStream1).distinct();

		firstStream1 = Stream.of(1, 2, 3, 4, 5, 6);
		secondStream1 = Stream.of(4, 5, 6, 7, 8, 9);
		Stream<Integer> resultingStream4 = Stream.concat(firstStream1, secondStream1);

		System.out.println(resultingStream3.collect(Collectors.toList()));
		System.out.println(resultingStream4.collect(Collectors.toList()));
		//
		
		
		

	}

	public static Predicate<Employee> isCBEEmployee() {
		return p -> p.getCity().equalsIgnoreCase("CBE");
	}

	public static Predicate<Employee> isSalemEmployee() {
		return p -> p.getCity().equalsIgnoreCase("Salem");
	}

	public static Predicate<Employee> isAgeMoreThan(Integer age) {
		return p -> p.getAge() > age;
	}

	public static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> predicate) {
		return employees.stream().filter(predicate).collect(Collectors.<Employee>toList());
	}

	public static int findSquareOfMaxOdd(List<Integer> numbers) {
		return numbers.stream().filter(MyDemo::isOdd) // Predicate is functional interface and
				// .filter(MyDemo::isGreaterThan3) // we are using lambdas to initialize it
				.filter(MyDemo::isLessThanOrEqualTo11) // rather than anonymous inner classes
				.max(Comparator.naturalOrder()).map(i -> i * i).get();
	}

	public static boolean isOdd(int i) {
		return i % 2 != 0;
	}

	public static boolean isGreaterThan3(int i) {
		return i > 3;
	}

	public static boolean isLessThanOrEqualTo11(int i) {
		return i <= 11;
	}

}
