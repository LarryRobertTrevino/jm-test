package com.company;

public class expressionHandler {

    public static InputData processExpression(String[] expression) throws Exception {

        InputData inputData = new InputData();

        boolean firstIsRoman = false;
        boolean secondIsRoman = false;

        inputData.setOperation(expression[1]);

        try {
            inputData.setFirstNum(RomanNumerals.valueOf(expression[0]).getValue());
            firstIsRoman = true;
        } catch (IllegalArgumentException ignored) {
        }

        try {
            inputData.setSecondNum(RomanNumerals.valueOf(expression[2]).getValue());
            secondIsRoman = true;
        } catch (IllegalArgumentException ignored) {
        }

        if (firstIsRoman && secondIsRoman)
            inputData.setRoman(true);
        else if (firstIsRoman || secondIsRoman)
            throw new Exception("Both numbers must be in one number system");

        try {
            if (!inputData.isRoman()) {
                inputData.setFirstNum(Integer.parseInt(expression[0]));
                inputData.setSecondNum(Integer.parseInt(expression[2]));
            }
        } catch (NumberFormatException ignored) {
        }

        if (inputData.getFirstNum() == 0 || inputData.getSecondNum() == 0) {
            throw new Exception("Invalid numbers.");
        }

        if (inputData.getFirstNum() < 1 || inputData.getFirstNum() > 10 ||
                inputData.getSecondNum() < 1 || inputData.getSecondNum() > 10) {
            throw new Exception("Numbers must be in [1;10].");
        }

        return inputData;
    }


    public static int calculate(InputData inputData) throws Exception {

        int result;

        switch (inputData.getOperation()) {
            case "+":
                result = inputData.getFirstNum() + inputData.getSecondNum();
                break;
            case "-":
                result = inputData.getFirstNum() - inputData.getSecondNum();
                break;
            case "/":
                result = inputData.getFirstNum() / inputData.getSecondNum();
                break;
            case "*":
                result = inputData.getFirstNum() * inputData.getSecondNum();
                break;
            default:
                throw new Exception("Invalid operator. Available [+, -, *, /]");
        }

        return result;

    }

    //from 1 to 100
    public static String convertToRoman(int number) throws Exception {

        if (number <= 0) {
            throw new Exception("Roman numerals are natural (result <= 0).");
        }

        String[] romanCharacters = { "C", "XC", "L", "X", "IX", "V", "I" };
        int[] romanValues = { 100, 90, 50, 10, 9, 5, 1 };
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < romanValues.length; i++) {
            int numberInPlace = number / romanValues[i];
            if (numberInPlace == 0) continue;
            result.append(numberInPlace == 4 && i > 0 ? romanCharacters[i] + romanCharacters[i - 1] :
                    new String(new char[numberInPlace]).replace("\0", romanCharacters[i]));
            number = number % romanValues[i];
        }

        return result.toString();
    }

}
