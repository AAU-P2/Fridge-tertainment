package fridge.tertainment.DataBase.DTO;

public class BrugerDTO extends DTO1 {
    
    public String Navn;

    public BrugerDTO(int _Bruger_id, String _Navn) {
        super.id = _Bruger_id;
        Navn = _Navn;
    }
}
