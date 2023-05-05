package western;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class Sherif extends Cowboy{

    public Sherif(String nom){
        super(nom);
    }
    public Sherif(String nom, Boisson boisson){
        super(nom, boisson);
    }

    private ArrayList<HorsLaLoi> wanted;
    private HorsLaLoi ravisseur;


    private Set<HorsLaLoi> captures = new HashSet<>();

    @Override
    public String getNom(){
        return "Sherif " + super.getNom();
    }

    @Override
    public String getPseudo(){return this.getNom();}

    @Override
    public void sePresenter(){
        dire(getNom());
    }

    @Override
    public void capturer(HorsLaLoi horsLaLoi){
        if(isWanted(horsLaLoi)){
            wanted.remove(horsLaLoi);
            dire("Au nom de la loi, "+ horsLaLoi.getPseudo() + ", je t'arrête !");
            horsLaLoi.seFaireCapturer(this);
            captures.add(horsLaLoi);
        }

    }

    public void rechercher(HorsLaLoi horsLaLoi){
        wanted.add(horsLaLoi);
    }

    public ArrayList<HorsLaLoi> getWanted(){
        ArrayList<HorsLaLoi> res = new ArrayList<HorsLaLoi>();
        for(HorsLaLoi hll : wanted){
            res.add(hll);
        }
        return res;
    }

    public boolean isWanted(HorsLaLoi horsLaLoi){
        for(HorsLaLoi hll : wanted){
            if(horsLaLoi == hll){
                return true;
            }
        }
        return false;
    }


}


