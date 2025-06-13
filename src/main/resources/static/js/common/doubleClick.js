let doubleClick = {
    doubleSubmitFlag: false
    , isDouble: function () {
        if (this.doubleSubmitFlag) {
            return this.doubleSubmitFlag;
        } else {
            this.doubleSubmitFlag = true;
            return false;
        }
    }
    , initFlag: function () {
        this.doubleSubmitFlag = false;
    }
};
