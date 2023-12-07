import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    //  représenter une transaction
    static class Transaction {
        String type;
        int montant;
        String date;

        Transaction(String type, int montant) {
            this.type = type;
            this.montant = montant;
            this.date = new Date().toString();
        }
    }

    // Variables globales pour le portefeuille, les transactions et les retraits
    static int solde = 0;
    static ArrayList<Transaction> transactions = new ArrayList<>();
    static int nombreRetraits = 0;

    //  ajouter de l'argent au portefeuille
    public static void ajouterArgent(int montant) {
        if (montant > 0) {
            solde += montant;
            enregistrerTransaction("Dépôt", montant);
            System.out.println("Le solde du portefeuille est maintenant de " + solde + " ariary.");
        } else {
            System.out.println("Le montant doit être supérieur ou égal à 0.");
        }
    }

    //  retirer de l'argent du portefeuille
    public static void retirerArgent(int montant) {
        if (montant > 0) {
            if (montant <= solde) {
                solde -= montant;
                enregistrerTransaction("Retrait", montant);
                nombreRetraits++;
                System.out.println("Le solde du portefeuille est maintenant de " + solde + " ariary.");
            } else {
                System.out.println("Le montant à retirer est supérieur au solde du portefeuille.");
            }
        } else {
            System.out.println("Le montant doit être supérieur ou égal à 0.");
        }
    }

    // le solde de portfeuille
    public static void afficherSolde() {
        System.out.println("Le solde du portefeuille est de " + solde + " ariary.");
    }

    //  afficher l'historique des transactions
    public static void afficherHistorique() {
        System.out.println("\nHistorique des transactions :");
        for (Transaction transaction : transactions) {
            System.out.println(transaction.type + " de " + transaction.montant + " ariary le " + transaction.date);
        }
        System.out.println("\n");
    }

    // enregistrer une transaction dans l'historique
    public static void enregistrerTransaction(String type, int montant) {
        Transaction transaction = new Transaction(type, montant);
        transactions.add(transaction);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix = 0;

        while (choix != 5) {
            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ajouter de l'argent");
            System.out.println("2. Retirer de l'argent");
            System.out.println("3. Afficher le solde");
            System.out.println("4. Afficher l'historique des transactions");
            System.out.println("5. Quitter");

            // Lire le choix
            choix = scanner.nextInt();

            // les  menu
            if (choix == 1) {
                System.out.print("Montant à ajouter : ");
                int montantAjout = scanner.nextInt();
                ajouterArgent(montantAjout);
            } else if (choix == 2) {
                System.out.print("Montant à retirer : ");
                int montantRetrait = scanner.nextInt();
                retirerArgent(montantRetrait);
            } else if (choix == 3) {
                afficherSolde();
            } else if (choix == 4) {
                afficherHistorique();
            } else if (choix == 5) {
                System.out.println("Au revoir !");
            } else {
                System.out.println("Choix incorrect.");
            }
        }
    }
}
