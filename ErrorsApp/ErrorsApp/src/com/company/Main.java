package com.company;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
public class Main
{
    public static boolean Function(DBHandler dbHandler) throws SQLException
    {
        System.out.println("Список пунктов:");
        System.out.println("0 - Вывести весь список ошибок.");
        System.out.println("1 - Вывести весь список типов ошибок.");
        System.out.println("2 - Вывести весь список ошибок за определённую дату.");
        System.out.println("3 - Вывести весь список ошибок с определённым описанием.");
        System.out.println("4 - Добавить ошибку.");
        System.out.println("5 - Добавить тип ошибки.");
        System.out.println("6 - Удалить ошибку.");
        System.out.println("7 - Удалить тип ошибки.");
        System.out.println("8 - Обновить информацию об ошибке.");
        System.out.println("9 - Обновить информацию о типе ошибки.");
        System.out.println("10 - Завершить работу.");
        Scanner inNumber = new Scanner(System.in);
        System.out.print("Введите номер пункта: ");
        int number = 9999;
        number = inNumber.nextInt();
        switch(number) {
            case 0: {
                List<ErrorList> errorLists = dbHandler.getAllErrors();
                for (ErrorList errorList : errorLists) System.out.println(errorList.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 1: {
                List<ErrorType> errorTypes = dbHandler.getAllErrorTypes();
                for (ErrorType errorType : errorTypes) System.out.println(errorType.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 2: {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите дату: ");
                int dateIn = in.nextInt();
                List<ErrorList> dateFind = dbHandler.findErrorByDate(Integer.toString(dateIn));
                for (ErrorList errorList : dateFind) System.out.println(errorList.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 3: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите описание: ");
                String descIN = in.nextLine();
                List<ErrorList> descr = dbHandler.findErrorByDesc(descIN);
                for (ErrorList errorList : descr) System.out.println(errorList.toString());
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 4: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите название: ");
                String name = in.nextLine();
                System.out.print("Введите дату: ");
                String date = in.nextLine();
                System.out.print("Введите год описание: ");
                String desc = in.nextLine();
                System.out.print("Введите тип ошибки: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 9999) {
                    dbHandler.addError(new ErrorList(0, name, date, desc, id));
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                } else
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 5: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип ошибки: ");
                String type = in.nextLine();
                System.out.print("Введите описание типа ошибки: ");
                String description = in.nextLine();
                dbHandler.addType(new ErrorType(0, type, description));
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 6: {
                Scanner in = new Scanner(System.in);
                System.out.print("Введите ID ошибки: ");
                int id = in.nextInt();
                dbHandler.deleteError(id);
                System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                break;
            }
            case 7: {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 9999) {
                    dbHandler.deleteType(id);
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                } else
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 8: {
                Scanner inInput = new Scanner(System.in);
                System.out.print("Введите ID ошибки: ");
                int idInput = inInput.nextInt();
                Scanner in = new Scanner(System.in, "windows-1251");
                if (!dbHandler.isErrorEx(idInput)) {
                    System.out.println("\u001B[31m" + "Запрос не отработан (не существует события под таким ID)" + "\u001B[0m");
                    break;
                }
                System.out.print("Введите название ошибки или пропуск (-): ");
                String name = in.nextLine();
                System.out.print("Введите дату или пропуск (-): ");
                String date = in.nextLine();
                System.out.print("Введите описание или пропуск (-): ");
                String desc = in.nextLine();
                System.out.print("Введите тип ошибки или пропуск (-): ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if (id != 9999 || type.equals("-")) {
                    dbHandler.updError(new ErrorList(idInput, name, date, desc, id), type);
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                }
                else System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 9:
            {
                Scanner in = new Scanner(System.in, "windows-1251");
                System.out.print("Введите тип ошибки: ");
                String type = in.nextLine();
                int id = dbHandler.findidbyType(type);
                if(id != 9999)
                {
                    System.out.print("Введите новое название типа ошибки или пропуск (-): ");
                    String typeNew = in.nextLine();
                    System.out.print("Введите описание типа ошибки или пропуск (-): ");
                    String description = in.nextLine();
                    dbHandler.updType(new ErrorType(id, typeNew, description));
                    System.out.println("\u001B[31m" + "Запрос отработан!" + "\u001B[0m");
                }
                else System.out.println("\u001B[31m" + "Запрос не отработан (не существует такого типа)" + "\u001B[0m");
                break;
            }
            case 10: return true;
            default:
            {
                System.out.println("\u001B[31m" + "Пожалуйста выберите пункт..." + "\u001B[0m");
                Function(dbHandler);
            }
        }
        return false;
    }
    static DBHandler dbHandler;
    public static DBHandler GetDBHandler()
    {
        return dbHandler;
    }
    public static void main(String[] args) throws SQLException
    {
        dbHandler = DBHandler.getInstance();
        do
        {
            boolean exit = Function(dbHandler);
            if(exit) break;
        }
        while(true == true);
    }
}
