package tansactions;
import database.*;
import models.Groupe;
import models.Membre;
import java.util.Scanner;

public class Accueil {
    public static void main(String[] args) {
   // BDAutoGeneration bd = new BDAutoGeneration();
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
                            System.out.print("1: Envoyer invitation \n");
                            System.out.print("2: Accepter invitation\n");
                            System.out.print("3: Créer page\n");
                            System.out.print("4: Aimer page \n");
                            System.out.print("5: Créer groupe  \n");
                            System.out.print("6: Rejoindre groupe\n");
                            System.out.print("7: Publier Message \n");
                            System.out.print("0: EXIT\n");
                            System.out.print("\nEnter Your Menu Choice: ");
                            ch = sc2.nextInt();
                            switch (ch) {

                                case 1:
                                    Scanner sc3 = new Scanner(System.in);
                                    System.out.print("Nom du personne ");
                                    String nom = sc3.nextLine();
                                    mem.envoyerInvitation(nom);
                                    break;

                                case 2:
                                    Scanner sc6 = new Scanner(System.in);
                                    System.out.println("Liste des invitations: ");
                                    System.out.println(mem.getInvitationRecu());
                                    System.out.println("indice =  : ");
                                    int x = sc6.nextInt();
                                    System.out.println(mem.getInvitationRecu().get(x).getSourceMembre());
                                    mem.accepterInvitation(mem.getInvitationRecu().get(x).getSourceMembre(),x);
                                    break;
                                case 3:
                                    System.out.println("Choisir un genre pour la page a crée (sport, cuisine ,fashion, nouveautés) ");
                                    Scanner sc4 = new Scanner(System.in);
                                    String genre = sc4.nextLine();
                                    System.out.print("Nom du page ");
                                    String nomP = sc4.nextLine();
                                    if (mem.creePage(nomP , genre)) {
                                        System.out.println("votre page est crée avec succée");
                                    }
                                    mem.listePage();
                                     break;
                                case 4 :
                                    System.out.println("nom du page ");
                                    Scanner sc5 = new Scanner(System.in);
                                    String n = sc5.nextLine();
                                    mem.aimerPage(n);
                                    break;
                                case 5:
                                    System.out.println("Choisir l'audiance pour le groupe a crée (prive / publique) ");
                                    Scanner sc7 = new Scanner(System.in);
                                    String privacy = sc7.nextLine();
                                    System.out.print("Nom du groupe ");
                                    String nomG = sc7.nextLine();
                                    if (mem.creeGroupe(nomG, privacy)) {
                                        System.out.println("votre groupe est crée avec succée");
                                    }
                                    break;
                                case 6 :
                                    System.out.printf("liste des groupes disponible \n ");
                                    System.out.println(Groupe.listeGroupeDisponible().get(0).toString());

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
        Connexion.closeConnexion();
    }

}

