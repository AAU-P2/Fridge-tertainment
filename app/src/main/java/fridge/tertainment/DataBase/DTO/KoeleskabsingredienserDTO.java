package fridge.tertainment.DataBase.DTO;

public class KoeleskabsingredienserDTO extends DTO2 {
    
    public double maengde;
    public String enhed;
    public String dato;

    public KoeleskabsingredienserDTO(int _Bruger_id, int _ingrediens_id, double _maengde, String _enhed, String _dato) {
        super.id1 = _Bruger_id;
        super.id2 = _ingrediens_id;
        maengde = _maengde;
        enhed = _enhed;
        dato = _dato;
    }
}
