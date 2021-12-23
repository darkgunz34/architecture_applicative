package fr.architecture.model.factory;

import fr.architecture.model.entities.Table;

public final class TableFactory {

    private  TableFactory(){

    }

    public static Table getTable(){
        return new Table();
    }
}
