<?php

$portefeuille = [
    'solde' => 0,
];

$transactions = [];
$nombreRetraits = 0;

// ajout de l'argent
function ajouterArgent($montant) {
    global $portefeuille, $transactions;
    if ($montant <= 0) {
        echo "Le montant doit être supérieur ou égal à 0.\n";
    } else {
        // Augmenter le solde
        $portefeuille['solde'] += $montant;
        enregistrerTransaction("Dépôt", $montant);
        echo "Le solde du portefeuille est maintenant de {$portefeuille['solde']} ariary.\n";
    }
}

// Fonction pour retirer de l'argent
function retirerArgent($montant) {
    global $portefeuille, $transactions, $nombreRetraits;
    if ($montant <= 0) {
        echo "Le montant doit être supérieur ou égal à 0.\n";
    } elseif ($montant > $portefeuille['solde']) {
        echo "Le montant à retirer est supérieur au solde du portefeuille.\n";
    } elseif ($nombreRetraits >= 3) {
        echo "Limite de retraits atteinte. Vous ne pouvez pas effectuer plus de 3 retraits.\n";
    } else {
        // Réduire le solde, enregistrer la transaction et mettre à jour le nombre de retraits
        $portefeuille['solde'] -= $montant;
        enregistrerTransaction("Retrait", $montant);
        $nombreRetraits++;
        echo "Le solde du portefeuille est maintenant de {$portefeuille['solde']} ariary.\n";
    }
}

//  afficher le solde
function afficherSolde() {
    global $portefeuille;
    echo "Le solde du portefeuille est de {$portefeuille['solde']} ariary.\n";
}

//  afficher l'historique
function afficherHistorique() {
    global $transactions;
    echo "\nHistorique des transactions :\n";
    foreach ($transactions as $transaction) {
        echo "{$transaction['type']} de {$transaction['montant']} ariary le {$transaction['date']}\n";
    }
    echo "\n";
}

//  enregistrer une transaction dans l'historique
function enregistrerTransaction($type, $montant) {
    global $transactions;
    $transaction = [
        'type' => $type,
        'montant' => $montant,
        'date' => date("Y-m-d H:i:s"),
    ];
    $transactions[] = $transaction;
}

$choix = 0;

while ($choix !== 5) {
    echo "Que voulez-vous faire ?\n";
    echo "1. Ajouter de l'argent\n";
    echo "2. Retirer de l'argent\n";
    echo "3. Afficher le solde\n";
    echo "4. Afficher l'historique des transactions\n";
    echo "5. Quitter\n";

    $choix = (int) readline("Votre choix : ");

    switch ($choix) {
        case 1:
            $montantAjout = (int) readline("Montant à ajouter : ");
            ajouterArgent($montantAjout);
            break;
        case 2:
            $montantRetrait = (int) readline("Montant à retirer : ");
            retirerArgent($montantRetrait);
            break;
        case 3:
            afficherSolde();
            break;
        case 4:
            afficherHistorique();
            break;
        case 5:
            echo "Au revoir !\n";
            break;
        default:
            echo "Choix incorrect.\n";
    }
}
?>
