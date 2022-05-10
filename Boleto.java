public class Boleto{

private Carte[] cartes;

private Joueurs[] joueurs;

public Carte[] getCartes(){
    return cartes;
}

public void setCartes(Carte[] cartes){
    this.cartes = cartes;
}

public Joueurs[] getJoueurs(){
    return joueurs;
}

public void setJoueurs(Joueurs[] joueurs){
    this.joueurs = joueurs;
}

public Boleto(){

    this.setCartes(new Carte[52]);
    this.setJoueurs(new Joueurs[4]);

    this.getJoueurs()[0] = new Joueurs("adris");
    this.getJoueurs()[1] = new Joueurs("fernando");
    this.getJoueurs()[2] = new Joueurs("nanque");
    this.getJoueurs()[3] = new Joueurs("ght_ak12");

    int i = 0;
    for (int carte = 1; carte <= 13; carte++){
        for (int signe = 0; signe <= 3; signe++){
            this.getCartes()[i] = new Carte(Integer.toString(carte), signe, carte);
            i++;
        }
    }

    for (int j = 0; j < 50; j++){
        int c1 = (int) (Math.random() * 51 + 0);
        int c2 = (int) (Math.random() * 51 + 0);

        Carte ech = this.getCartes()[c1];
        this.getCartes()[c1] = this.getCartes()[c2];
        this.getCartes()[c2] = ech;
    }

    for (int j = 0; j < 13; j++){
        this.getJoueurs()[0].ajoutCarte(this.getCartes()[4 * j]);
        this.getJoueurs()[1].ajoutCarte(this.getCartes()[4 * j + 1]);
        this.getJoueurs()[2].ajoutCarte(this.getCartes()[4 * j + 2]);
        this.getJoueurs()[3].ajoutCarte(this.getCartes()[4 * j + 3]);
    }

}

private int getIndexPlusGrandeCarte(Carte[] carteJoues, int numeroPremierJoueur) {
    int indexMax = numeroPremierJoueur;
    int signe = carteJoues[numeroPremierJoueur].getSigne();
    for (int i = 0; i < 4; i++) {
        if (i != numeroPremierJoueur && carteJoues[i].getSigne() == signe
                && carteJoues[i].getValeur() > carteJoues[indexMax].getValeur()) {
            indexMax = i;
        }
    }

    return indexMax;
}

private int getNumeroJoueurGagnant() {
    int numeroGagnant = 0;
    for (int i = 1; i < 4; i++) {
        if (this.getJoueurs()[i].getScore() > this.getJoueurs()[numeroGagnant].getScore()) {
            numeroGagnant = i;
        }
    }

    return numeroGagnant;
}

private void sleep(int time)
	{
		try
		{
			Thread.sleep(time);
		}catch(Exception e)
		{
			System.out.println("error");
		}
	}

    private void waiting()
	{
		for(int i=0; i<10; i++)
		{
			System.out.print(". ");
			this.sleep(100);
		}
		System.out.println();
	}

    public static void main(String[] args) {
		
		Boleto belote = new Boleto();
		
		System.out.println("Debut de la partie ");
		belote.waiting();
		System.out.println("Nous allons déterminer qui ouvre la partie ");
		belote.waiting();
		int numeroPremierJoueur = (int) (Math.random() * 3 + 0);
		System.out.println(belote.getJoueurs()[numeroPremierJoueur].getNom()+":\" Ah! C'est Moi\"");
		belote.waiting();

        do {
			System.out.println("\n\nTour numero "+ (13-belote.getJoueurs()[0].getCompteurDeCartes()+1));
			belote.waiting();

            Carte[] carteJoues = new Carte[4];

            carteJoues[numeroPremierJoueur] = belote.getJoueurs()[numeroPremierJoueur].jouerEnPremier();
			System.out.println(belote.getJoueurs()[numeroPremierJoueur].getNom()+": \" "+belote.getJoueurs()[numeroPremierJoueur].jouer(carteJoues[numeroPremierJoueur])+" \"");
			belote.waiting();

            for (int i = 0; i < 4; i++) {
				if (i != numeroPremierJoueur) {
					carteJoues[i] = belote.getJoueurs()[i].play(carteJoues[numeroPremierJoueur].getSigne());
					System.out.println(belote.getJoueurs()[i].getNom()+": \" "+belote.getJoueurs()[i].jouer(carteJoues[i])+" \"");
					belote.waiting();
				}
			}

            int numeroJoueurGagnant = belote.getIndexPlusGrandeCarte(carteJoues, numeroPremierJoueur);
		
			int lastScore = belote.getJoueurs()[numeroJoueurGagnant].getScore();
			belote.getJoueurs()[numeroJoueurGagnant].tourGagnant(carteJoues);

			System.out.println(belote.getJoueurs()[numeroJoueurGagnant].getNom()+": \" Wesh! Suis le gagnant (+"+(belote.getJoueurs()[numeroJoueurGagnant].getScore()-lastScore)+") \"");
			
			numeroPremierJoueur = numeroJoueurGagnant;
			
		} while (belote.getJoueurs()[0].getCompteurDeCartes() > 0);

		System.out.println("\nLa partie est terminée.");
		System.out.println("Nous allons afficher les résultats ");
		belote.waiting();
		
		System.out.println("");
		System.out.println("("+belote.getJoueurs()[0].getNom()+", "+belote.getJoueurs()[0].getScore()+")");
		System.out.println("("+belote.getJoueurs()[1].getNom()+", "+belote.getJoueurs()[1].getScore()+")");
		System.out.println("("+belote.getJoueurs()[2].getNom()+", "+belote.getJoueurs()[2].getScore()+")");
		System.out.println("("+belote.getJoueurs()[3].getNom()+", "+belote.getJoueurs()[3].getScore()+")");
		
		
		int numeroJoueurGagnant = belote.getNumeroJoueurGagnant();

		
		System.out.println("\n"+belote.getJoueurs()[numeroJoueurGagnant].getNom() + " est notre gagnant avec un score de "
				+ belote.getJoueurs()[numeroJoueurGagnant].getScore());

	}




}