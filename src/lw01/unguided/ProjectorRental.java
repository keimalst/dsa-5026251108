package lw01.unguided;

public class ProjectorRental extends Rental {
    public ProjectorRental(String id, int days){ super(id,days); }

    public int calculateCharge(){
        int day = getDays();
        if(day <= 3){
            return (day * 60000) + 20000;
        }
        else{
            return (3 * 60000) + ((day-3) * 45000) + 20000;
        }
    }
    public String label(){ return "Projector"; }

}