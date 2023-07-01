package model;

import model.exception.CantGenerateStateEventException;

public interface Eventable
{
    public Cell Event(Map mappa) throws CantGenerateStateEventException;

    default public void vuoto(Map map, int i, int j)
    {
        map.setVuotoPosition(i,j);
    }
}