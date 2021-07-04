/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciopractico.ifelse;

/**
 *
 * @author Guido Caballero
 */
public class Primo {
    private int idPrimo;
    private int nroCircular;

    public Primo() {
    }
    public Primo(int num) {
        this.nroCircular = num;
    }
    public Primo(int idNum, int num) {
        this.idPrimo = idNum;
        this.nroCircular = num;
    }

    public int getIdPrimo() {
        return nroCircular;
    }
    public int getNroCircular() {
        return nroCircular;
    }

    public void setIdPrimo(int idNum) {
        this.nroCircular = idNum;
    }
    public void setNroCircular(int num) {
        this.nroCircular = num;
    }

    @Override
    public String toString() {
        return nroCircular +", ";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.nroCircular;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Primo other = (Primo) obj;
        if (this.nroCircular != other.nroCircular) {
            return false;
        }
        return true;
    }
    
    
}
