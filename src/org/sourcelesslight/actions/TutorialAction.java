package org.sourcelesslight.actions;

public class TutorialAction {

	private String bestTutorialSite;
	private String language;
	
	
	public String execute()
	{
		System.err.println("EXECUTE ÇALIÞTI!");
		
		setBestTutorialSite("Gönderilecek Mesaj");
		System.out.println(getLanguage());
		if(language.equals("java"))
			return "success";
		else
			return "failure";
	}
	
	public String getBestTutorialSite() {
		return bestTutorialSite;
	}
	public void setBestTutorialSite(String bestTutorialSite) {
		this.bestTutorialSite = bestTutorialSite;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
