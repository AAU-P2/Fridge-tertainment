package fridge.tertainment.DataBase.DTO;

public class BrugerDTO extends DTO1 {
    
    public String Navn;
    public String brugerPassword;

    public BrugerDTO(int _Bruger_id, String _Navn, String _brugerPassword) {
        super.id = _Bruger_id;
        Navn = _Navn;
        brugerPassword = _brugerPassword;
    }
}
