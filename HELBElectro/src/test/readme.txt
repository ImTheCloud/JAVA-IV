Situation : 

Dans un magasin, quatre produits sont vendus : café, soupe, glace et coca. L'employé du magasin doit mémoriser le nombre de ventes réalisées pour chacun de ces produits. L'interface utilisateur de l'employé est composée de quatre boutons : deux à gauche pour le café et la soupe, et deux à droite pour la glace et le coca. Lorsque l'employé clique sur l'un des boutons, le nombre de ventes de ce produit augmente d'un, et l'affichage est mis à jour avec cette nouvelle valeur de ventes pour le produit cliqué.

Le programme est structuré selon le design pattern du modèle-vue-contrôleur. Le contrôleur est responsable de la gestion des événements provenant de l'interface utilisateur, ainsi que de la mise à jour de celle-ci lors de changements dans le modèle.

Des tests unitaires ont été effectués sur la classe Produit. Le premier test vérifie que le nombre de ventes d'un nouveau produit est effectivement égal à zéro. Le deuxième test vérifie que, après avoir augmenté le nombre de ventes une fois, le nombre de ventes est bien égal à un.
