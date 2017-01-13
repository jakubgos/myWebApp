package com.myapp.Controller.user;

/**
 * Created by Bos on 2017-01-13.
 */
public class commandEditItems {

    Long userItems[];


Long userId;
    public Long[] getUserItems() {
        return userItems;
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserItems(Long[] userItems) {
        this.userItems = userItems;
    }


}
