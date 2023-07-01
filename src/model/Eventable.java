package model;

import model.exception.CantGenerateEventException;

public interface Eventable
{
    public Cell Event(Map mappa) throws CantGenerateEventException;
}