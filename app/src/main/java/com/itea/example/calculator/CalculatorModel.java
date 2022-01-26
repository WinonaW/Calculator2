package com.itea.example.calculator;  //логика работы калькулятора

public class CalculatorModel {
    private int firstArg; //отдельные переменные для хранения аргументов
    private int secondArg;

    private StringBuilder inputStr = new StringBuilder(); //стринг-буфер - класс, в котором накапливается число во время нажатия кнопки

    private int actionSelected; //переменная, которая хранит в себе выбранное действие (плюс, минус, деление, умножение)

    private State state; //состояние, в котором находиться калькулятор в данный момент

    private enum State {  //отдельное представление для состояний
        firstArgInput, // три состояния: первый аргумент
        secondArgInput, //второй
        resultShow //показ результата
    }

    public CalculatorModel () {
        state = State.firstArgInput;
    } //переключение состояния при нажатии кнопок, примитивное разделение компонентов по их назначению

    public void onNumPressed (int buttonId){ //обработчик нажатия цифровой кнопки

        if (state == State.resultShow){
            state = State.firstArgInput;
            inputStr.setLength(0); //в зависимости от состояния: если финальное состояние – показ результата предыдущей операции, то буфер очищается и в него добавляется цифра
        }

        if (inputStr.length() < 9 ) { //если это продолжение набора текущего аргумента, то цифры добавляются в буфер к уже существующим
            switch (buttonId) {
                case R.id.zero:
                    if (inputStr.length() != 0) {
                        inputStr.append("0");
                    }
                    break;
                case R.id.one:
                        inputStr.append("1");
                    break;
                case R.id.two:
                    inputStr.append("2");
                    break;
                case R.id.three:
                    inputStr.append("3");
                    break;
                case R.id.four:
                    inputStr.append("4");
                    break;
                case R.id.five:
                    inputStr.append("5");
                    break;
                case R.id.six:
                    inputStr.append("6");
                    break;
                case R.id.seven:
                    inputStr.append("7");
                    break;
                case R.id.eight:
                    inputStr.append("8");
                    break;
                case R.id.nine:
                    inputStr.append("9");
                    break;
            }
        }
    }

    public void onActionPressed (int actionId) { //функция обработок кнопок, представляет из себя действия (разделить, умножить и т.д.)
                                                 //состоит из двух частей. Эта часть - обработка "=".
        if (actionId == R.id.equals && state == State.secondArgInput) {  //если мы находимся в состоянии ввода второго аргумента и при этом что-то уже введено,
                                                                         //то есть, есть какой-то второй аргумент, ктороый можно использовать.
            secondArg = Integer.parseInt(inputStr.toString()); //извлекаем второй аргумент
            state = State.resultShow;
            inputStr.setLength(0);   //аргумент извлечён
            switch (actionSelected) { //выбираем арифетическое действие - экшн селектед запоминает какое действие выбрано
                case R.id.plus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.multiply:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.division:
                    inputStr.append(firstArg / secondArg);
                    break;

            }

        } else if (inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Integer.parseInt(inputStr.toString());
            state = State.secondArgInput;
            inputStr.setLength(0);

            switch (actionId) {
                case R.id.plus:
                    actionSelected = R.id.plus;
                    break;
                case R.id.minus:
                    actionSelected = R.id.minus;
                    break;
                case R.id.multiply:
                    actionSelected = R.id.multiply;
                    break;
                case R.id.division:
                    actionSelected = R.id.division;
                    break;
            }
    }

    }

    public String getText(){
        return inputStr.toString();
    }

}
