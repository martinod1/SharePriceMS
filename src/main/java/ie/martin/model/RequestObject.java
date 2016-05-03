package ie.martin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by martin on 16/02/16.
 */
public class RequestObject implements Serializable{


        public CheckedItem check = null;

        public CheckedItem getCheck() {
                return check;
        }

        public void setCheck(CheckedItem check) {
                this.check = check;
        }

}
