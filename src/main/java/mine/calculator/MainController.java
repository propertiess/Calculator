package mine.calculator;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController {


    // Operation button
    @FXML
    private Button cbtn, cebtn, combtn, delbtn, divbtn, equalbtn, procentbtn, sumbtn, subbtn, multbtn;


    // Number button
    @FXML
    private Button zerobtn, onebtn, twobtn, threebtn, fourbtn, fivebtn, sixbtn, sevenbtn, eightbtn, ninebtn;


    // Previous operation
    @FXML
    private Text number;

    // Output
    @FXML
    private TextField input;

    @FXML
    private AnchorPane scene;


    private int count_number;
    private double first_number, second_number;
    private char Sign;



    @FXML
    void initialize() {
        Button [] numbers = new Button[] { zerobtn, onebtn, twobtn, threebtn, fourbtn,
                fivebtn, sixbtn, sevenbtn, eightbtn, ninebtn, combtn};
        Button [] math_operation = new Button[] { divbtn, equalbtn,
                 sumbtn, subbtn, multbtn};
        Button [] clear_operation = new Button[] { cbtn, cebtn,delbtn};
        for(var i : numbers){
            ButtonNumber(i);
        }
        for(var i : math_operation){
            ButtonMathOperation(i);
        }
        for(var i : clear_operation){
            ButtonClearOperation(i);
        }

        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::KeyboardClicked);




        combtn.setOnAction(event -> {

            String temp_input = input.getText();
            try {
                if(Integer.parseInt(temp_input) >= 0d) {
                    addSign(".");
            }
            } catch (NumberFormatException e){
                System.out.println("NumberFormatExcept");
            }
        });
        procentbtn.setOnAction(event -> {

            try {
                if(Double.parseDouble(input.getText()) > 0d) {
                    double temp = Double.parseDouble(input.getText());
                    temp /= 100d;
                    input.setText(String.valueOf(temp));
                }
            } catch (Exception e){
                System.out.println("Button %");
            }

        });


    }




    // ButtonMathOperation get event click on math operations button
    private void ButtonMathOperation(Button math_operation){
        math_operation.setOnAction(event -> {
            count_number++;
            if(count_number == 1) {
                try {
                    String temp_input = input.getText();
                    String sign = math_operation.getText();
                    Sign = sign.charAt(0);
                    System.out.println(Sign);
                    first_number = Double.parseDouble(temp_input);
                    System.out.println(first_number);
                    if(Sign == '%') {
                        first_number /= 100d;
                        input.setText(String.valueOf(first_number));
                        count_number--;
                        return;
                    }




                    if (Sign != '=') {
                        number.setText(first_number + "\n" + sign);
                        input.setText("");
                    } else {

                        count_number--;
                    }



                } catch (Exception e){
                    System.out.println("ButtonMathOperation");
                    count_number--;
                }
            }

            else if (count_number == 2) {
                try {

                    String temp_input = input.getText();
                    String temp_sign = math_operation.getText();

                    if(temp_sign.charAt(0) != '=') {
                        Sign = temp_sign.charAt(0);
                        number.setText(first_number + "\n" + Sign);
                        count_number--;
                        return;
                    }
                    second_number = Double.parseDouble(temp_input);
                    if(second_number == 0d && Sign == '÷'){
                        input.setText("");
                        count_number--;
                        return;

                    }

                    number.setText("");
                    input.setText(String.valueOf(Answer(Sign)));
                    first_number = Answer(Sign);
                    count_number = 0;

                } catch (Exception e){
                    System.out.println("except");
                    count_number = 1;
                    input.setText("");

                }
            }


        });
    }
    // ButtonClearOperation get event click on clear operations button

    private void ButtonClearOperation(Button clear_operation){
        clear_operation.setOnAction(event -> {
            System.out.println(clear_operation.getText());

            if (clear_operation.getText().equalsIgnoreCase("DEL")) {
                try {
                    char[] ready = new char[input.getText().length() - 1];
                    for (int i = 0; i < input.getText().length() - 1; i++) {
                        ready[i] = input.getText().charAt(i);

                    }
                    String readies = String.valueOf(ready);
                    input.setText(readies);
                } catch (NegativeArraySizeException e){
                    System.out.println("NegativeArraySizeException");
                }

            }

            else if (clear_operation.getText().equals("C") || clear_operation.getText().equals("CE")) {
                input.setText("");
                first_number = 0;
                second_number = 0;
                count_number = 0;
                number.setText("");

            }
        });

    }
    // ButtonNumber get event click on numbers button

    private void ButtonNumber(Button button){


        button.setOnAction(event -> {
            String temp = button.getText();

            System.out.println(temp);
            addSign(temp);
        });

    }

    // addSign add symbol on input
    private void addSign(String s) {
        input.appendText(s);
    }

    // Answer get answer on math operation
    private double Answer(char s) {
        System.out.println(first_number + " " + second_number + " " + Sign);
        if(s == '+') {
            return Math.round((first_number + second_number) * 100000d) / 100000d;
        }
        else if (s == '-') {
            return Math.round((first_number - second_number) * 100000d) / 100000d;
        }
        else if (s == '×'){
            return Math.round((first_number * second_number) * 100000d) / 100000d;

        }
        else if (s == '÷') {
            return Math.round((first_number / second_number) * 100000d) / 100000d;
        }

        return 0;
    }

    private void KeyboardClicked(KeyEvent keyEvent) {
        switch (keyEvent.getText()) {
            case "0" -> zerobtn.fire();
            case "1" -> onebtn.fire();
            case "2" -> twobtn.fire();
            case "3" -> threebtn.fire();
            case "4" -> fourbtn.fire();
            case "5" -> fivebtn.fire();
            case "6" -> sixbtn.fire();
            case "7" -> sevenbtn.fire();
            case "8" -> eightbtn.fire();
            case "9" -> ninebtn.fire();

            case "+" -> sumbtn.fire();
            case "-" -> subbtn.fire();
            case "*" -> multbtn.fire();
            case "/" -> divbtn.fire();

            case "." -> combtn.fire();
            case "SHIFT %" -> procentbtn.fire();
            default -> {

            }
        }
        switch (keyEvent.getCode()){
            case BACK_SPACE -> delbtn.fire();
            case DELETE -> cebtn.fire();
            case ENTER -> equalbtn.fire();
            default -> {

            }

        }

    }








}
