const prompt = require('prompt-sync')();

let portefeuille = {
  solde: 0,
};

let transactions = [];
let nombreRetraits = 0; //  le nombre de retraits effectués

// ajout de l'argent
function ajouterArgent(montant) {
  if (montant <= 0) {
    console.log("Le montant doit être supérieur ou égal à 0.");
  } else {
    // Augmenter le solde
    portefeuille.solde += montant;
    enregistrerTransaction("Dépôt", montant);
    console.log("Le solde du portefeuille est maintenant de " + portefeuille.solde + " ariary.");
  }
}

// retirer de l'argent
function retirerArgent(montant) {
  if (montant <= 0) {
    console.log("Le montant doit être supérieur ou égal à 0.");
  } else if (montant > portefeuille.solde) {
    console.log("Le montant à retirer est supérieur au solde du portefeuille.");
  } else if (nombreRetraits >= 3) {
    console.log("Limite de retraits atteinte. Vous ne pouvez pas effectuer plus de 3 retraits.");
  } else {
    // Réduire le solde, enregistrer la transaction et mettre à jour le nombre de retraits
    portefeuille.solde -= montant;
    enregistrerTransaction("Retrait", montant);
    nombreRetraits++;
    console.log("Le solde du portefeuille est maintenant de " + portefeuille.solde + " ariary.");
  }
}

// solde de portefeuille
function afficherSolde() {
  console.log("Le solde du portefeuille est de " + portefeuille.solde + " ariary.");
}

// l'historique
function afficherHistorique() {
  console.log("\nHistorique des transactions :");
  transactions.forEach((transaction) => {
    console.log(`${transaction.type} de ${transaction.montant} ariary le ${transaction.date}`);
  });
  console.log("\n");
}

//  enregistrer une transaction dans l'historique
function enregistrerTransaction(type, montant) {
  let transaction = {
    type: type,
    montant: montant,
    date: new Date().toLocaleString(),
  };
  transactions.push(transaction);
}

let choix;

do {
  console.log("Que voulez-vous faire ?");
  console.log("1. Ajouter de l'argent");
  console.log("2. Retirer de l'argent");
  console.log("3. Afficher le solde");
  console.log("4. Afficher l'historique des transactions");
  console.log("5. Quitter");

  choix = prompt("Votre choix : ");
  choix = parseInt(choix);

  if (choix === 1) {
    let montantAjout = prompt("Montant à ajouter : ");
    montantAjout = parseInt(montantAjout);
    ajouterArgent(montantAjout);
  } else if (choix === 2) {
    let montantRetrait = prompt("Montant à retirer : ");
    montantRetrait = parseInt(montantRetrait);
    retirerArgent(montantRetrait);
  } else if (choix === 3) {
    afficherSolde();
  } else if (choix === 4) {
    afficherHistorique();
  } else if (choix === 5) {
    console.log("Au revoir !");
  } else {
    console.log("Choix incorrect.");
  }
} while (choix !== 5);
