package western;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Barman extends Personnage{

    public Barman(String nom){
        super(nom, Boisson.of("vin", Genre.MASCULIN));
        this.bar = "Chez " + getNom();
    }
    public Barman(String nom, String nomBar){
        super(nom, Boisson.of("vin", Genre.MASCULIN));
        this.bar = nomBar;
    }
    private String bar;
    private Map<Personnage, Boisson> clients = new HashMap<>();

    @Override
    public String getNom(){
        return super.getNom();
    }

    public String getBar(){return this.bar;}
    @Override
    public String getPseudo() {
        return this.getNom() + "le barman de " + this.getBar() + " Coco.";
    }

    public void servir(Personnage personne){
        clients.put(personne, personne.getBoisson());
        dire("Et un p'tit verre de " + personne.getBoisson() + " pour" + personne.getPseudo() + " Coco.");
        personne.boire();
    }

    public Map<Personnage, Boisson> getClients(){
        Map<Personnage, Boisson> res = new HashMap<>();
        for(Personnage pers : res.keySet()){
            res.put(pers, pers.getBoisson());
        }
        return res;
    }
}
