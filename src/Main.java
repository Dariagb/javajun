import java.util.*;
import java.util.stream.Collectors;

public class Homework {

    private static class Department {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Department(String name) {
            this.name = name;
        }
        // TODO: геттеры, сеттеры
    }

    private static class Person {
        private String name;
        private int age;
        private double salary;
        private Department depart;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public Department getDepart() {
            return depart;
        }

        public void setDepart(Department depart) {
            this.depart = depart;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    ", depart=" + depart +
                    '}';
        }

        // TODO: геттеры, сеттеры
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Олег", 25, 56.789, new Department("IT")));
        people.add(new Person("Анна", 30, 6000.0, new Department("HR")));
        people.add(new Person("Сергей", 28, 4500.765, new Department("IT")));

        Optional<Person> youngestPerson = findMostYoungestPerson(people);
        youngestPerson.ifPresent(System.out::println);

        System.out.println(findMostYoungestPerson(List < Person > people));
        System.out.println(findMostExpensiveDepartment(List < Person > people));
    }

    /**
     * Найти самого молодого сотрудника
     */
    static Optional<Person> findMostYoungestPerson(List<Person> people) {
        return people.stream()
                .min(Comparator.comparingInt(Person::getAge));
    }

    /**
     * Найти департамент, в котором работает сотрудник с самой большой зарплатой
     */

    static Optional<Department> findMostExpensiveDepartment(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingDouble(Person::getSalary))
                .map(Person::getDepart)
                .distinct()
                .findFirst();
    }
// FIXME: ваша реализация здесь


    /**
     * Сгруппировать сотрудников по департаментам
     */
    public static Map<Department, List<Person>> groupByDepartment(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(Person::getDepartment));
    }

    /**
     * Сгруппировать сотрудников по названиям департаментов
     */
    public static Map<String, List<Person>> groupByDepartmentName(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(p -> p.getDepartment().getName()));
    }

    /**
     * В каждом департаменте найти самого старшего сотрудника
     */
    static void getDepartmentOldestPerson(List<Person> people) {
// FIXME: ваша реализация здесь
        people.stream()
                .max(Comparator.comparingDouble(Person::getSalary));
    }

    /**
     * *Найти сотрудников с минимальными зарплатами в своем отделе
     * (прим. можно реализовать в два запроса)
     */
    static List<Person> cheapPersonsInDepartment(List<Person> people) {
        Map<String, List<Person>> groupedByDepartment = groupByDepartmentName(people);
        List<Person> result = new ArrayList<>();
        for (Map.Entry<String, List<Person>> entry : groupedByDepartment.entrySet()) {
            List<Person> personInDepartment = entry.getValue();
            double minSalary = personInDepartment.stream()
                    .mapToDouble(Person::getSalary)
                    .min()
                    .orElse(0.0);
            personInDepartment.stream()
                    .filter(p -> p.getSalary() == minSalary)
                    .forEach(result::add);
        }
        return result;

}
}