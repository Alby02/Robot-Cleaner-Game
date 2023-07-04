package model;

import model.exception.IllegaInteractnGameException;

public interface Interactable
{
    /**
     * Esegue l'interazione con un oggetto presente nella mappa
     *
     * @throws IllegaInteractnGameException se non è possibile generare l'evento
     */
    public void interact() throws IllegaInteractnGameException;
}
