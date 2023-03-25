package org.demo.model;

import java.io.Serializable;

public abstract class ComparableEntity implements Serializable {
    public abstract Integer getId();
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || this.getClass() != o.getClass()){
            return false;
        }
        ComparableEntity comparedObject = (ComparableEntity) o;
        return getId().equals(comparedObject.getId());
    }
}
