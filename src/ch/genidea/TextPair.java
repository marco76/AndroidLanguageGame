package ch.genidea;

/**
 * Created with IntelliJ IDEA.
 * User: marco
 * Date: 7/16/12
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextPair {
   private String language1;
    private String language2;


    public TextPair(String language1, String language2){
      this.language1 = language1;
        this.language2 = language2;
    }

    public String getLanguage1() {
        return language1;
    }

    public void setLanguage1(String language1) {
        this.language1 = language1;
    }

    public String getLanguage2() {
        return language2;
    }

    public void setLanguage2(String language2) {
        this.language2 = language2;
    }
}
