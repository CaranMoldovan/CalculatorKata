import java.util.Scanner;

public class Main {
    private static String[] operationSing= {"+", "-", "*", "/"};
    private static String operation;
    private static boolean itsRome=false;// показатель наличия римских цифр в вводе
    private static String firsOperand;
    public static String secondOperand;
    private static int firstOperandInt;
    private static int secondOperandInt;
    private static boolean isItsArabic =false;
    public static void main(String[] args) {
        System.out.println("Введите ваше число");
        Scanner scanner = new Scanner(System.in);
        String input= scanner.next();
        scanner.close();
        whatOperation(input);
        comtainsRomeNumerals(input);
        containsArabicNumeral(input);
        hasArabicAndRomes();
        separationOperands(input);
        if(isItsArabic==true) {
          firstOperandInt =  arabicOperandToInt(firsOperand);
          secondOperandInt =  arabicOperandToInt(secondOperand);
            System.out.println(operating());
        } else if (itsRome==true) {
           firstOperandInt = romeToArabic(firsOperand);
           secondOperandInt= romeToArabic(secondOperand);
           int result = operating();
            System.out.println(resultRome(result));

        }

    }
    private static String resultRome(int resultArabic){
        String resultRome=null;
        for (RomeNumbers number : RomeNumbers.values()){
            if(number.getNumberArabic()==resultArabic){
                resultRome = number.getNumberRome();
            }
        }
        return resultRome;
    }

    private static int romeToArabic(String operand) {
        int operandInt = 0;
        
        for (RomeNumbers number : RomeNumbers.values()){
            if(number.getNumberRome().equals(operand)){
                operandInt = number.getNumberArabic();
            }
        }
        controlTen(operandInt);
        return operandInt;
    }
    private static void controlTen(int operand){
        if(operand>10){
            throw new RuntimeException("Римская цифра больше 10");
        }
    }

    private static int operating() {
        int result;
        switch (operation) {
            case "+":
                result= firstOperandInt+secondOperandInt;
                break;
            case "-":
                result=firstOperandInt-secondOperandInt;
break;  
            case "*":
                result=firstOperandInt*secondOperandInt;
                break;
            case "/":
                result=firstOperandInt/secondOperandInt;
            default:
                throw new RuntimeException("Не найдена опецария");
        }
        return result;
        
    }

    private static int arabicOperandToInt(String operand) {
        return Integer.parseInt(operand);

    }


    private static void separationOperands(String input) {
        String[] operands;
        switch (operation) {
            case "+":
                operands = input.split("\\+");
                break;
            case "-":
                operands= input.split("-");
                break;
            case "*":
                operands=input.split("\\*");
                break;
            case "/":
                operands=input.split("/");
                break;
            default:
                throw new RuntimeException("Не получилось разделить операнды");
        }
        controlLenght(operands);
        firsOperand=operands[0];
        secondOperand=operands[1];
    }

    private static void controlLenght(String[] operands) {
        if(operands.length>2){
            throw new RuntimeException("Операция более чем с 2 числами");
        }
    }

    private static void hasArabicAndRomes() {
        if (itsRome==true&&isItsArabic==true){
            throw new RuntimeException("Присутствуют римские и арабские числа");
        }
    }


    private static void whatOperation(String input) {
         int symbolCount =0;

    for (String symbol : operationSing){
       if( input.contains(symbol)){
            symbolCount++;
            operation =symbol;
        }
    }
    controlCount(symbolCount);


    }

    private static void controlCount(int symbolCount){
        if (symbolCount>1||symbolCount<1){
            throw new  RuntimeException("Операция больше чем с 2 числами");
        }
    }
    private static void comtainsRomeNumerals(String input){
        for (RomeNumbers romeNumber: RomeNumbers.values()){
            if (input.contains(romeNumber.getNumberRome())){
                itsRome=true;
            }
        }
    }
    private static void containsArabicNumeral(String input) {
        isItsArabic = input.matches(".*\\d+.*");
    }

}