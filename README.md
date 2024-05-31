# FEATURES
## Aprovisionnement
1. Choisir ingredient, quantite, date
2. Valider information
2. Mettre a jour stock pour chaque ingredient

21. Nouveau ingredient
    Declenchement du cas d'utilisation "creer ingredient"
22. Si qte < 0 ou date > current_date
   Afficher message d'erreur
23. Si ingredient n'existe pas
    Afficher message d'erreur

NEW APPROV [ingredient_id = 10, qte = 200, date = 25/05/2024]
NEW APPROV [ingredient_name = "new ingredient", qte = 200, date = 25/05/2024]


## Commande client
1. Choisir menu, nombre
2. Verifier stock pour chaque ingredient dans le menu
3. Enregistrer commande
4. Mettre a jour stock pour chaque ingredient
5. Afficher montant total commande

21. Stock insuffisant
    Afficher message d'erreur
22. Menu inexiste
    Afficher message d'erreur

Example
BDD ingredient_of_menu = [
 {menu_id = 1, ingredient_id = 1, qte = 0.5},
 {menu_id = 1, ingredient_id = 2, qte = 0.3},
 {menu_id = 1, ingredient_id = 3, qte = 0.8}
]

stock = [
 { restaurant_id = 10, ingredient_id = 1, qte = 10}
 { restaurant_id = 10, ingredient_id = 2, qte = 0}
 { restaurant_id = 10, ingredient_id = 3, qte = 5}
]

NEW COMMANDE [restaurant_id = 10, menu id = 1, nombre = 2]
 verify: qte_necessaire = [
    ingredient_id = 1 => 2 * 0.5 = 1
    ingredient_id = 2 => 2 * 0.3 = 0.6
    ingredient_id = 3 => 2 * 0.8 = 1.6
]
DISPLAY Stock insuffisant


## Concevoir menu
1. Saisir nom menu, prix et qte de chaque ingredient
3. Enregistrer menu

EXAMPLE
ADD MENU [
name = "Akoho",
price = 2000,
ingredients = [
        {ingredient_id = 1, qte = 0.5}, 
        {ingredient_id = 2, qte = 0.3},
        {ingredient_id = 3, qte = 0.8}
    ]
]

11. nouveau ingredient
    CU "Creer ingredient"