package model;

import model.exception.IllegaInteractnGameException;

public interface Interactable
{
    public void interact() throws IllegaInteractnGameException;
}
