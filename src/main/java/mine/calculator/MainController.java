package mine.calculator;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;

public class MainController {



    @FXML
    private Button cbtn, cebtn, combtn, delbtn, divbtn, equalbtn, procentbtn, sumbtn, subbtn, multbtn;

    @FXML
    private Button zerobtn, onebtn, twobtn, threebtn, fourbtn, fivebtn, sixbtn, sevenbtn, eightbtn, ninebtn;


    @FXML
    private Text number;

    @FXML
    private TextField input;


    private int count_number;
    private int first_number, second_number;
    private char Sign;



    @FXML
    void initialize() {
        Button [] numbers = new Button[] {zerobtn, onebtn, twobtn, threebtn, fourbtn,
                fivebtn, sixbtn, sevenbtn, eightbtn, ninebtn, combtn};
        Button [] math_operation = new Button[] {combtn, divbtn, equalbtn,
                procentbtn, sumbtn, subbtn, multbtn};
        Button [] clear_operation = new Button[] {cbtn, cebtn,delbtn};
        for(var i : numbers){
            ButtonNumber(i);
        }
        for(var i : math_operation){
            ButtonMathOperation(i);
        }
        for(var i : clear_operation){
            ButtonClearOperation(i);
        }




    }

    private void ButtonMathOperation(Button math_operation){
        math_operation.setOnAction(event -> {
            count_number++;
            if(count_number == 1) {
                try {
                String temp_input = input.getText();
                String sign = math_operation.getText();

                    first_number = Integer.parseInt(temp_input);
                    System.out.println(first_number);

                    Sign = sign.charAt(0);
                    System.out.println(Sign);
                    number.setText(temp_input + sign);
                    input.setText("");
                } catch (Exception e){
                    System.out.println("ButtonMathOperation");
                    count_number--;
                }
                }

            else if (count_number == 2) {
                String temp_input = input.getText();
                second_number = Integer.parseInt(temp_input);
                if(second_number == 0){
                    count_number = 1;
                    input.setText("");

                    return;
                }
                number.setText("");
                input.setText(String.valueOf(Answer(Sign)));
                first_number = Answer(Sign);
                count_number = 0;
            }



        });
    }

    private void ButtonClearOperation(Button clear_operation){
        clear_operation.setOnAction(event -> {
            System.out.println(clear_operation.getText());
            if (clear_operation.getText().equalsIgnoreCase("DEL")) {
                char []ready = new char[input.getText().length() - 1];
                for(int i = 0; i < input.getText().length() - 1; i++) {
                    ready[i] = input.getText().charAt(i);

                }
                String readies = String.valueOf(ready);
                input.setText(readies);

            }
            else if (clear_operation.getText().equalsIgnoreCase("C") || clear_operation.getText().equalsIgnoreCase("CE")) {
                input.setText("");
                first_number = 0;
                second_number = 0;
                count_number = 0;
                number.setText("");

            }
        });

    }

    private void ButtonNumber(Button button){
        button.setOnAction(event -> {
            String temp = button.getText();

            System.out.println(temp);
            Sign(temp);
        });

    }

    private void Sign(String s) {
        input.appendText(s);
    }

    private int Answer(char s) {
        if(s == '+') {
            return first_number + second_number;
        }
        else if (s == '—') {
            return first_number - second_number;
        }
        else if (s == '×'){
            return first_number * second_number;

        }
        else if (s == '÷') {
            return first_number / second_number;
        }

        return 0;
    }










}
