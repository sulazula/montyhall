package org.example;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int NUM_SIM = 15000;
        double winChanceChangeDoor = montyhall(NUM_SIM, true);
        System.out.printf("Шанс на выигрыш со сменой двери: %.2f%%%n\n", winChanceChangeDoor);
        double winChanceStayDoor = montyhall(NUM_SIM, false);
        System.out.printf("Шанс на выигрыш без смены двери: %.2f%%%n\n", winChanceStayDoor);
    }

    public static double montyhall(int numSimulations, boolean switchDoor) {
        int winCount = 0;
        int numSim = 0;
        Map<String, String> map = new HashMap<>();
        Random rand = new Random();

        for (int j = 0; j < numSimulations; j++) {
            numSim++;

            // задаем рандомную дверь с кучей бабла и делаем выбор
            int prizeDoor = rand.nextInt(3);
            int choise = rand.nextInt(3);

            // открытие двери с какашкой ведущим
            int openedDoor;
            do {
                openedDoor = rand.nextInt(3);
            } while (openedDoor == choise || openedDoor == prizeDoor);

            // меняем дверь
            if (switchDoor) {
                for (int i = 0; i < 3; i++) {
                    if (i != openedDoor && i != choise) {
                        choise = i;
                    }
                }
            }

            // проверяем, выиграли ли кучу бабла
            if (choise == prizeDoor) {
                winCount++;
            }
            map.put("Attemp num: " + j, "Chanse: " + (double) winCount / numSim);
        }

        return (double) winCount / numSimulations;
    }
}