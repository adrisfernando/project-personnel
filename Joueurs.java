public class Joueurs {

    private String nom;
    private int score;

    private int compteurDeCartes;

    private Carte[] mainDuJoueur;

    public String getNom() {
		return nom;
	}

    public void setNom(String nom) {
		this.nom = nom;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

    public int getCompteurDeCartes() {
		return compteurDeCartes;
	}

	public void setCompteurDeCartes(int compteurDeCartes) {
		this.compteurDeCartes = compteurDeCartes;
	}

	public Carte[] getMainDuJoueur() {
		return mainDuJoueur;
	}

	public void setMainDuJoueur(Carte[] mainDuJoueur) {
		this.mainDuJoueur = mainDuJoueur;
	}

    public Joueurs(String nom) {
		this.nom = nom;
		this.score = 0;
		this.compteurDeCartes = 0;
		this.mainDuJoueur = new Carte[13];
	}

    public void ajoutCarte(Carte carte)
	{
		this.mainDuJoueur[this.compteurDeCartes] = carte;
		this.compteurDeCartes++;
	}

    public Carte jouerEnPremier()
	{
		 int i=0;
		 do
		 {
			 if(this.getMainDuJoueur()[i] != null)
			 {
				 Carte c = this.getMainDuJoueur()[i];
				 this.getMainDuJoueur()[i] = null;
				 this.compteurDeCartes--;
				 return c;
			 }else
			 {
				 i++;
			 }
		 }while (i<13);
		 
		 return null;
	}

    public Carte play(int signe)
	{
		int i=0;
		do
		{
			Carte c = this.getMainDuJoueur()[i];
			if(c != null && c.getSigne() == signe)
			{
				this.getMainDuJoueur()[i] = null;
				this.compteurDeCartes--;
				return c;		
			}
		
			i++;
		}while(i<13);
        return this.jouerEnPremier();
	}

    public void tourGagnant(Carte[] carte)
	{
		int score=0;
		for(int i=0; i<carte.length; i++)
		{
			score += carte[i].getValeur();
		}
		this.setScore(this.getScore() + score);
	}

    public String jouer(Carte c)
	{
		String phrase = "Je joue la carte ";
		switch(c.getNom())
		{
			case "11": phrase += "'valet'";
			break;
			
			case "12": phrase += "'dame'";
			break;
			
			case "13": phrase += "'roi'";
			break;
			
			default: phrase += "'"+c.getNom()+"'";	
		}

        phrase += " ,";
		
		switch(c.getSigne())
		{
			case 0: phrase += "trèfle";
			break;
			
			case 1: phrase += "pique";
			break;
			
			case 2: phrase += "coeur";
			break;
			
			case 3: phrase += "carreau";
			break;
			
			default: break;
			
		}
		
		return phrase;
	}

}
