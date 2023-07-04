package model;

import model.exception.CantGenerateEventException;

public interface Eventable
{
    /**
     * Genera un evento nella mappa e restituisce la cella risultante dell'evento.
     *
     * @param mappa la mappa su cui generare l'evento
     * @throws CantGenerateEventException se non è possibile generare l'evento
     */
    public Cell Event(Map mappa) throws CantGenerateEventException;
}
