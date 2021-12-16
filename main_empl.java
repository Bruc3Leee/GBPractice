package OO;

public class main_empl {
    public static void main(String[] args) {

        Employeeee[] employee = new Employeeee[5];
        employee[0] = new Employeeee("И. И. Федор", "Финансовый директор", "qwdqwdq@mail.ru", 892222222, 2000, 22);
        employee[1] = new Employeeee("П. П. Геннадий", "Управляющий", "ww@mail.ru", 99323333, 1000, 54);
        employee[2] = new Employeeee("З. З. Заур", "Клоун", "kk@mail.ru", 99929339, 500, 41);
        employee[3] = new Employeeee("Л. Л. Лаур", "Менеджер по продажам", "sale@mail.ru", 233939459, 1400, 32);
        employee[4] = new Employeeee("Щ. Щ. Щука", "Начальник охраны", "shuka@mail.ru", 234553455, 3400, 51);

        for (Employeeee employeeee : employee) {
            if (employeeee.getAge() > 40) {
                System.out.println(employeeee);
            }
        }
    }
}
