# Santa-s-Naughty-or-Nice-List
The project simulates Santa Claus' gift giving based on children's wishes and overall naughtiness quotient

Calugaritoiu Ion-Victor - OOP Project 2022

Proiectul consta in realizarea unei simulari de acordare de cadouri de catre
Mos Craciun. Simularile sunt realizate pe parcursul a mai multor ani, existand
 actualizari succesive ale bazei de date de copii si cadouri.

Entry point-ul simularii se afla in clasa Main, unde este apelata metoda
auxiliara startSimulation din clasa Simulation. In cadrul acestei metode sunt
realizate toate operatiile asupra bazei de date. De asemenea, metoda este
responsabila pentru citirea datelor din fiecare fisier JSON de test, apeland
readData din clasa InputReader pentru fiecare dintre cele 25 de teste. Metoda
readData mapeaza informatiile primite din fisierul JSON la campurile
corespunzatoare claselor din baza de date.

Dupa obtinerea datelor initiale, se apeleaza metoda processRoundZero, care are
rolul de a sterge din lista copiii ale caror varste depasesc 18 ani, de a
initializa campurile de niceScore si budget ale fiecarui copil si de a acorda
cadourile corespunzator.

Acordarea de cadouri se face apeland metoda assignGifts din clasa GiftMachine.
Metoda parcurge lista de copii, iar pentru fiecare copil este construita o noua
lista de cadouri primite. Cadourile sunt acordate in functie de bugetul acordat
fiecarui copil si de preferintele sale.

Actualizarile anuale sunt realizate folosind metoda processAnnualChanges din
clasa YearlyUpdate. Metoda modifica bugetul anual al Mosului si incrementeaza
varsta fiecarui copil existent in baza de date. De asemenea, in functie de
input-ul primit, metoda poate adauga copii noi in baza de date, sa actualizeze
datele unor copii deja existenti (adauga nice scores, preferinte de cadouri)
si sa adauge noi cadouri disponibile pentru Mos Craciun.

Dupa procesarea completa a fiecarui an de simulare, datele obtinute sunt
adaugate la lista de simulari anuale annualChildren din clasa Output. Campul
annualChildren contine o lista de obiecte de tip RoundData, care la randul lor
contin listele finale de copii procesati.

La finalul procesarii fiecarei runde este apelata metoda writeOutput din clasa
OutputWriter, care are rolul de a scrie datele finale in fisierele JSON output.

# UPDATED VERSION

Am adaugat un scor bonus de cumintenie optional, campul niceScoreBonus,
reprezentand procentajul cu care mareste/micsoreaza averageScore-ul deja
calculat.

Fiecarui copil ii este atribuit un elf de o anumita culoare, specificata in
campul Elf. In functie de tipul elfului, bugetul alocat unui copil poate fie
sa creasca fie sa scada cu 30% sau poate sa nu fie alterat in niciun fel.
Calculele sunt realizate in setter-ul pentru campul assignedBudget.
In cazul elfului galben, am parcurs lista de cadouri a mosului si am adaugat
cadourile din categoria preferata a copilului intr-o lista noua possibleGifts.
Am sortat lista dupa pretul cadourilor si am verificat daca cel mai ieftin
cadou se afla pe stoc. Daca cantitatea este diferita de 0, il adaug la lista
de cadouri primite de copil.

Fiecarui update anual ii este precizat modul in care se distribuie cadourile
in acel an. In cazul in care strategia este ID, sortez lista de copii dupa ID
folosind un comparator. Pentru strategia NICE_SCORE, sortez din nou lista de
copii, descendent dupa averageScore-ul fiecarui copil. In cazul in care
strategia anuala este NICE_SCORE_CITY apelez metoda setSortedCitiesByScore
care returneaza un ArrayList de obiecte de tip City(o noua clasa care contine
o lista cu copiii din orasul respectiv), sortate in functie de average score-ul
calculat pentru acel oras. Parcurg lista de City si adaug pe rand la o noua
lista de Child, toti copiii din orase (sortati dupa ID), astfel pastrand
ordinea corecta pentru acordarea cadourilor.

