/**
 * Created by Ian on 2016-08-10.
 */
public class RuneWeapon {

    public int sockets;
    public String runecombo;
    public String weaponType;
    public String weaponStats;

    public RuneWeapon (int socketReq, String runecombo, String weaponType, String stats){

        this.sockets = socketReq;
        this.runecombo = runecombo;
        this.weaponStats = stats;
        isPaladinShield(weaponType);
    }

    private void isPaladinShield(String weaponType){
        if (weaponType.contains("Paladin")){
            this.weaponType = "Paladin Shield";
        }
        else{
            this.weaponType = weaponType;
        }
    }
}
