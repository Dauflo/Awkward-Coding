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
public class Cryptage {
    static public void cryptage() {
        ArrayList motCarac = new ArrayList();
        String motUtilisateur;
        int taileDuBourage;
        System.out.println("Entrer le mot a crypter");
        Scanner entree1 = new Scanner(System.in);
        motUtilisateur = entree1.nextLine();
        System.out.println("Donner la taille de la clef");
        entree1 = new Scanner(System.in);
        taileDuBourage = entree1.nextInt();
        char tableauCaractere[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        
        //String -> ArrayList
        for (int i = 0; i < motUtilisateur.length(); i++) {
            motCarac.add(motUtilisateur.charAt(i));
        }

        //Bourage
        motCarac.add('/');
        int bourageInt;
        for (int i = 0; i < taileDuBourage; i++) {
            bourageInt = (int) (Math.random() * 36);
            motCarac.add(tableauCaractere[bourageInt]);
        }

        //Passage en Ascii
        ArrayList codeCrypte = new ArrayList();
        ArrayList passageAsciiCode = new ArrayList();
        int testLongueur = motCarac.size();
        for (int i = 0; i < testLongueur; i++) {
            char ascii = (char) (motCarac.get(i));
            int asciiDone = (int) (ascii);
            passageAsciiCode.add(asciiDone);
        }

        //Gestion Clef de cryptage
        ArrayList clefCryptage = new ArrayList();
        int Min = 0;
        int Max = 35;
        for (int i = 0; i < testLongueur; i++) {
            int nombreAleatoire = Min + (int) (Math.random() * ((Max - Min) + 1));
            clefCryptage.add(nombreAleatoire);
        }
        
        //Cryptage
        for (int i = 0; i < testLongueur; i++) {
            if (i == 0) {
                codeCrypte.add((int) passageAsciiCode.get(i + 1) + (int) passageAsciiCode.get(i) + (int) clefCryptage.get(i));
            }
            else if (i > 0 && i < testLongueur - 1) {
                codeCrypte.add((int) codeCrypte.get(i - 1) + (int) passageAsciiCode.get(i) + (int) passageAsciiCode.get(i + 1) + (int) clefCryptage.get(i));
            }
            else if (i == testLongueur - 1) {
                codeCrypte.add((int) codeCrypte.get(i - 1) + (int) passageAsciiCode.get(i) + (int) clefCryptage.get(i));
            }
        }
        
        // Modulation
        ArrayList ClefModulation = new ArrayList();
        int tailleCodeCrypte = codeCrypte.size();
        ArrayList codeCrypteFinal = new ArrayList();
        for (int i = 0; i < tailleCodeCrypte; i++) {
            int codeCrypteModulation = (int) codeCrypte.get(i);
            int nbModulation = 0;

            while (codeCrypteModulation >= 36) {
                codeCrypteModulation = codeCrypteModulation - 36;
                nbModulation = nbModulation + 1;
            }
            ClefModulation.add(nbModulation);
            codeCrypteFinal.add(codeCrypteModulation);
        }

        // Rendu final
        System.out.print("\nResultat : ");
        int tailleCodeCrypteFinal = codeCrypteFinal.size();
        for (int i = 0; i < tailleCodeCrypteFinal; i++) {
            System.out.print(tableauCaractere[(int) codeCrypteFinal.get(i)]);
        }
        System.out.println("");

        // Affichage de la clef de cryptage
        int tailleClef = clefCryptage.size();
        System.out.print("La clef est : ");
        for (int i = 0; i < tailleClef; i++) {
            System.out.print(clefCryptage.get(i) + ".");
        }
        System.out.println("");

        // Affichage de la clef de modulation
        System.out.print("La clef de modulation est : ");

        for (int i = 0; i < ClefModulation.size(); i++) {
            System.out.print(ClefModulation.get(i) + ".");
        }
    }
}
