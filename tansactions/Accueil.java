package tansactions;
import database.*;
import models.Membre;
import java.lang.reflect.Member;
import java.util.Scanner;

public class Accueil {
    public static void main(String[] args) {
       //BDAutoGeneration bd = new BDAutoGeneration();
        Scanner input = new Scanner(System.in);
        int choice;
        int ch;
        do {
            System.out.print("1: Inscrire\n");
            System.out.print("2: Authentifier\n");
            System.out.print("0: EXIT \n");
            System.out.print("\nEnter Your Menu Choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Nom ");
                    String name = sc.nextLine();
                    System.out.print("Prenom ");
                    String prenom = sc.nextLine();
                    System.out.print("email ");
                    String mail = sc.nextLine();
                    System.out.print("Password ");
                    String pwd = sc.nextLine();
                    System.out.print("age ");
                    int age = sc.nextInt();
                    MemberTr m = new MemberTr();
                    m.inscrire(name, prenom, mail, pwd, age);

                    break;
                case 2:
                    Scanner sc1 = new Scanner(System.in);
                    System.out.print("email ");
                    String email = sc1.nextLine();
                    System.out.print("password ");
                    String password = sc1.nextLine();
                    MemberTr m1 = new MemberTr();
                    Membre mem = m1.authentifier(email, password);
                    System.out.println(mem);
                    if (mem != null) {
                        do {
                            Scanner sc2 = new Scanner(System.in);
                            System.out.print("MENU: \n");
                            System.out.println("1: Envoyer invitation \n");
                            System.out.println("2: Accepter invitation\n");
                            System.out.println("3: Publier Message \n");
                            System.out.println("4: Créer groupe\n");
                            System.out.println("5: Rejoindre groupe  \n");
                            System.out.println("6: Aimer page \n");
                            System.out.println("7: Aimer page \n");
                            System.out.println("0: EXIT\n");
                            System.out.print("\nEnter Your Menu Choice: ");
                            ch = sc2.nextInt();
                            switch (ch) {
                                case 1:
                                    Scanner sc3 = new Scanner(System.in);
                                    System.out.print("Nom du personne");
                                    String nom = sc3.nextLine();
                                    mem.envoyerInvitation(nom);
                                    System.out.println("voila la liste");
                                    mem.listeInvitationEnvoye();
                                    break;
                                default:
                                    System.out.println("Veuillez respecter le menu!");
                            }
                        }while (ch!=0);
                    }
                    break;
                default:
                    System.out.println("Veuillez respecter le menu!");
            }

        }while (choice !=0);
    }
}

