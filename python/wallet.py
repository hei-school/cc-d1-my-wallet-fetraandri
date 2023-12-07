import datetime

# Déclaration du portefeuille
portefeuille = {
    'solde': 0,
}

transactions = []
nombre_retraits = 0

#  ajout de l'argent
def ajouter_argent(montant):
    if montant <= 0:
        print("Le montant doit être supérieur ou égal à 0.")
    else:
        # Augmenter le solde
        portefeuille['solde'] += montant
        enregistrer_transaction("Dépôt", montant)
        print(f"Le solde du portefeuille est maintenant de {portefeuille['solde']} ariary.")

#  retirer de l'argent
def retirer_argent(montant):
    global nombre_retraits
    if montant <= 0:
        print("Le montant doit être supérieur ou égal à 0.")
    elif montant > portefeuille['solde']:
        print("Le montant à retirer est supérieur au solde du portefeuille.")
    elif nombre_retraits >= 3:
        print("Limite de retraits atteinte. Vous ne pouvez pas effectuer plus de 3 retraits.")
    else:
        # Réduire le solde, enregistrer la transaction et mettre à jour le nombre de retraits
        portefeuille['solde'] -= montant
        enregistrer_transaction("Retrait", montant)
        nombre_retraits += 1
        print(f"Le solde du portefeuille est maintenant de {portefeuille['solde']} ariary.")

#  afficher le solde
def afficher_solde():
    print(f"Le solde du portefeuille est de {portefeuille['solde']} ariary.")

#  afficher l'historique
def afficher_historique():
    print("\nHistorique des transactions :")
    for transaction in transactions:
        print(f"{transaction['type']} de {transaction['montant']} ariary le {transaction['date']}")
    print("\n")

#  enregistrer une transaction dans l'historique
def enregistrer_transaction(type_transaction, montant):
    transaction = {
        'type': type_transaction,
        'montant': montant,
        'date': datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
    }
    transactions.append(transaction)

choix = 0

while choix != 5:
    print("Que voulez-vous faire ?")
    print("1. Ajouter de l'argent")
    print("2. Retirer de l'argent")
    print("3. Afficher le solde")
    print("4. Afficher l'historique des transactions")
    print("5. Quitter")

    choix = int(input("Votre choix : "))

    if choix == 1:
        montant_ajout = int(input("Montant à ajouter : "))
        ajouter_argent(montant_ajout)
    elif choix == 2:
        montant_retrait = int(input("Montant à retirer : "))
        retirer_argent(montant_retrait)
    elif choix == 3:
        afficher_solde()
    elif choix == 4:
        afficher_historique()
    elif choix == 5:
        print("Au revoir !")
    else:
        print("Choix incorrect.")
