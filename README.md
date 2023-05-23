# pao-project

Proiect - Programare cabinet medical

    *JavaFX, Maven
    *Fisiere csv   

<u>Ce mi-am propus</u>

- sa folosesc JavaFX in loc de meniu in consola
- sa folosesc fisiere csv pentru persistenta in loc de db
- userul sa poata alege o specializare si o data din calendar si sa primeasca inapoi o lista de doctori disponibili (comparable dupa data)
- ultimul pas e sa aleaga doctorul, iar rezultatul ar fi screen cu "Programarea" si optiunea de a o exporta in pdf sau a trimite un mesaj/mail cu ea (ceva de genul asta)
- sa fie niste ecrane decent dpdv design (am vrut sa vad cum e JavaFX)

<u>Ce mi-a iesit pana cum</u>

- mi-am prins un pic urechile in JavaFX (nu este neaparat pretty)
- au fost deasemenea niste probleme cu a folosi fisiere csv in care sa scrii in contextul unui proiect standard JavaFX din Idea. In sensul ca nu prea poti sa scrii in ele daca sunt in folderul `resources`
- un singur screen care afiseaza un tabel cu doctori + un buton care deschide un form prin care poti sa adaugi un doctor, lista updatandu-se automat dupa.
- salvarea datelor si citerea lor folosind fisiere csv

<u>TO DO</u>

- restul ecranelor si functionalitatea pentru appointments.
