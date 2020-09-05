package be.vdab.frituurfrida.sessions;

import java.io.Serializable;

public class Deur implements Serializable {

    private final static long serialVersionUID = 1L;

    private final int index;
    private boolean open;
    private final boolean metFriet;

    public Deur(int index, boolean metFriet) {
        this.index = index;
        this.metFriet = metFriet;
    }

    public int getIndex() {
        return index;
    }

    public boolean isOpen(){
        return open;
    }

    public boolean isMetFriet(){
        return metFriet;
    }

    public void open(){
        open = true;
    }
}
