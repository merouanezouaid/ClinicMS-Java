package application;

public class tablePatient{
		int idP;

	    private String SexeP;

	    private String dateNP;


	    private String TelephoneP;

	
	    private String nomP;

	    
	    private String CINP;

	    
	    private String AdresseP;

	    
	    private String PrenomP;

	    
	    private String MutuelleP;

	    
	    private String SituationP;
	    


		private String DateCreationP;
	    
	    private int DocteurID;





		public tablePatient(int idP, String nomP, String prenomP, String sexeP, String cINP, String dateNP, 
				 String telephoneP, String situationP, String mutuelleP, String adresseP, String DateCreationP, int DocteurID) {

			this.idP = idP;
			this.SexeP = sexeP;
			this.dateNP = dateNP;
			this.TelephoneP = telephoneP;
			this.nomP = nomP;
			this.CINP = cINP;
			this.AdresseP = adresseP;
			this.PrenomP = prenomP;
			this.MutuelleP = mutuelleP;
			this.SituationP = situationP;
			this.DateCreationP= DateCreationP;
			this.DocteurID = DocteurID;
		}


		public int getIdP() {
			return idP;
		}


		public void setIdP(int idP) {
			this.idP = idP;
		}


		public String getSexeP() {
			return SexeP;
		}


		public void setSexeP(String sexeP) {
			SexeP = sexeP;
		}


		public String getDateNP() {
			return dateNP;
		}


		public void setDateNP(String dateNP) {
			this.dateNP = dateNP;
		}


		public String getTelephoneP() {
			return TelephoneP;
		}


		public void setTelephoneP(String telephoneP) {
			TelephoneP = telephoneP;
		}


		public String getNomP() {
			return nomP;
		}


		public void setNomP(String nomP) {
			this.nomP = nomP;
		}


		public String getCINP() {
			return CINP;
		}


		public void setCINP(String cINP) {
			CINP = cINP;
		}


		public String getAdresseP() {
			return AdresseP;
		}


		public void setAdresseP(String adresseP) {
			AdresseP = adresseP;
		}


		public String getPrenomP() {
			return PrenomP;
		}


		public void setPrenomP(String prenomP) {
			PrenomP = prenomP;
		}


		public String getMutuelleP() {
			return MutuelleP;
		}


		public void setMutuelleP(String mutuelleP) {
			MutuelleP = mutuelleP;
		}


		public String getSituationP() {
			return SituationP;
		}


		public void setSituationP(String situationP) {
			SituationP = situationP;
		}

		
	    
	    public String getDateCreationP() {
			return DateCreationP;
		}


		public void setDateCreationP(String dateCreationP) {
			DateCreationP = dateCreationP;
		}


		public int getDocteurID() {
			return DocteurID;
		}


		public void setDocteurID(int docteurID) {
			DocteurID = docteurID;
		}




	    
}
