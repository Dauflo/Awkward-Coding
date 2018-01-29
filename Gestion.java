/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author alois
 */
public class Gestion {

    static public void gestion() {
        String mode;
        try {
            System.out.println("Bienvenue dans la methode de cryptage AC, merci de suivre les instructions !");
            System.out.println("1 : Cryptage\n2 : Decryptage\nexit : Sortie");
            Scanner entree1 = new Scanner(System.in);
            mode = entree1.nextLine();
            switch (mode) {
                case "1":
                    Cryptage.cryptage();
                    break;
                case "2":
                    Decryptage.decryptage();
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Donner 1, 2 ou exit\n");
                    gestion();
                    break;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Donner 1, 2 ou exit\n");
            gestion();
        } catch (InputMismatchException ime) {
            System.out.println("Erreur dans le dernier champ renseigne\n");
            gestion();
        } catch (ArrayIndexOutOfBoundsException aie) {
            System.out.println("Erreur dans la saisie de la clef ou de la clef de modulation\n");
            gestion();
        } catch (Exception e) {
            System.out.println("ERROR\n");
            gestion();
        }
    }
    
    static public void GUI() {
    }
}
