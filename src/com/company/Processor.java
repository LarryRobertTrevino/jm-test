package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Processor {

    public void start() throws Exception {

        String[] expression = getExpression();

        InputData inputData = NumberHandler.processExpression(expression);

        int res = NumberHandler.calculate(inputData);

        if (inputData.isRoman()) {

            System.out.println(NumberHandler.convertToRoman(res));

        } else {

            System.out.println(res);

        }

    }

    public static String[] getExpression() throws Exception {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String[] expression = br.readLine().split("\\s+");

            if (expression.length != 3) {
                throw new Exception("Invalid expression. The expression must match the following format : \"[number1] [operator(+, -, *, /)] [number2]]\".");
            }

            return expression;

        } catch (IOException e) {

            e.printStackTrace();

        }

        throw new IllegalArgumentException();
    }

}
