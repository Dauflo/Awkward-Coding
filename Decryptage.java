/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alois
 */
public class Decryptage {

    static public void decryptage() {
        System.out.println("Entrer le code");
        Scanner entree1 = new Scanner(System.in);
        String aDecoder = entree1.nextLine();
        System.out.println("Entrer la clef");
        Scanner entree2 = new Scanner(System.in);
        String clef = entree2.nextLine();
        System.out.println("Entrer la clef de modulation");
        Scanner entree3 = new Scanner(System.in);
        String clefDeModulation = entree3.nextLine();

        //Recuperation de la clef sans les points
        ArrayList clefSansPoint = new ArrayList();
        String remplir = "";
        for (int i = 0; i < clef.length(); i++) {

            if (clef.charAt(i) != '.') {

                remplir = remplir + clef.charAt(i);
            } else {
                clefSansPoint.add(remplir);
                remplir = "";
            }
        }

        //Recuperation de la clef de modulation sans les points
        ArrayList clefModulationSansPoint = new ArrayList();
        String remplirModulation = "";
        for (int i = 0; i < clefDeModulation.length(); i++) {
            if (clefDeModulation.charAt(i) != '.') {
                remplirModulation = remplirModulation + clefDeModulation.charAt(i);
            } else {
                clefModulationSansPoint.add(remplirModulation);
                remplirModulation = "";
            }
        }

        //Code String -> valeur tableau
        ArrayList code = new ArrayList();
        for (int i = 0; i < aDecoder.length(); i++) {
            char valeurTableau = aDecoder.charAt(i);
            switch (valeurTableau) {
                case '0':
                    code.add("0");
                    break;
                case '1':
                    code.add("1");
                    break;
                case '2':
                    code.add("2");
                    break;
                case '3':
                    code.add("3");
                    break;
                case '4':
                    code.add("4");
                    break;
                case '5':
                    code.add("5");
                    break;
                case '6':
                    code.add("6");
                    break;
                case '7':
                    code.add("7");
                    break;
                case '8':
                    code.add("8");
                    break;
                case '9':
                    code.add("9");
                    break;
                case 'a':
                    code.add("10");
                    break;
                case 'b':
                    code.add("11");
                    break;
                case 'c':
                    code.add("12");
                    break;
                case 'd':
                    code.add("13");
                    break;
                case 'e':
                    code.add("14");
                    break;
                case 'f':
                    code.add("15");
                    break;
                case 'g':
                    code.add("16");
                    break;
                case 'h':
                    code.add("17");
                    break;
                case 'i':
                    code.add("18");
                    break;
                case 'j':
                    code.add("19");
                    break;
                case 'k':
                    code.add("20");
                    break;
                case 'l':
                    code.add("21");
                    break;
                case 'm':
                    code.add("22");
                    break;
                case 'n':
                    code.add("23");
                    break;
                case 'o':
                    code.add("24");
                    break;
                case 'p':
                    code.add("25");
                    break;
                case 'q':
                    code.add("26");
                    break;
                case 'r':
                    code.add("27");
                    break;
                case 's':
                    code.add("28");
                    break;
                case 't':
                    code.add("29");
                    break;
                case 'u':
                    code.add("30");
                    break;
                case 'v':
                    code.add("31");
                    break;
                case 'w':
                    code.add("32");
                    break;
                case 'x':
                    code.add("33");
                    break;
                case 'y':
                    code.add("34");
                    break;
                case 'z':
                    code.add("35");
                    break;
            }
        }

        //Demodulation du code
        ArrayList codeDemodule = new ArrayList();
        int nbFinal;
        int tailleclefModulationSansPoint = clefModulationSansPoint.size();
        for (int i = 0; i < tailleclefModulationSansPoint; i++) {
            int nb = Integer.parseInt((String) code.get(i));
            int modulation = Integer.parseInt((String) clefModulationSansPoint.get(i));
            nbFinal = nb + (36 * modulation);
            codeDemodule.add(nbFinal);
        }

        //Decryptage
        int cas = 0;
        int indexCodeMauvaisSens = 0;
        int codeDemoduleSize = codeDemodule.size();
        ArrayList codeMauvaisSens = new ArrayList();
        ArrayList codeBonSens = new ArrayList();
        for (int i = codeDemoduleSize - 1; i != -1; i--) {
            if (i == codeDemodule.size() - 1) {
                cas = 0;
            } else if (i < (codeDemodule.size() - 1) && i > 0) {
                cas = 1;
            } else if (i == 0) {
                cas = 2;
            }
            switch (cas) {
                case 0:
                    codeMauvaisSens.add((int) codeDemodule.get(i) - (int) codeDemodule.get(i - 1) - Integer.parseInt((String) clefSansPoint.get(i)));
                    break;
                case 1:
                    codeMauvaisSens.add((int) codeDemodule.get(i) - (int) codeDemodule.get(i - 1) - Integer.parseInt((String) clefSansPoint.get(i)) - (int) codeMauvaisSens.get(indexCodeMauvaisSens));
                    indexCodeMauvaisSens = indexCodeMauvaisSens + 1;
                    break;
                case 2:
                    codeMauvaisSens.add((int) codeDemodule.get(i) - Integer.parseInt((String) clefSansPoint.get(i)) - (int) codeMauvaisSens.get(indexCodeMauvaisSens));
                    indexCodeMauvaisSens = indexCodeMauvaisSens + 1;
                    break;
            }
        }

        //Remise dans le bpn sens
        for (int i = codeMauvaisSens.size() - 1; i != -1; i--) {
            codeBonSens.add(codeMauvaisSens.get(i));
        }

        //Passage en clair
        ArrayList codeFinal = new ArrayList();
        for (int i = 0; i < codeBonSens.size(); i++) {
            int textAscii = (int) codeBonSens.get(i);
            char text = (char) textAscii;
            codeFinal.add(text);
        }
        System.out.println("");
        for (int i = 0; i < codeFinal.size(); i++) {
            if ((char) codeFinal.get(i) != '/') {
                System.out.print(codeFinal.get(i));
            } else {
                break;
            }
        }
    }
}
